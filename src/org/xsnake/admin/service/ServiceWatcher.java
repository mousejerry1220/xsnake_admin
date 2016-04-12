package org.xsnake.admin.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;
import org.xsnake.remote.connector.ZookeeperConnector;

import com.google.gson.Gson;

public class ServiceWatcher implements Watcher{

	private List<ServiceData> serviceList = null;
	private ZookeeperConnector connector = null;
	private String path;
	
	public ServiceWatcher(final ZookeeperConnector connector,final String path,final List<ServiceData> list){
		this.serviceList = list;
		this.connector = connector;
		this.path = path;
	}
	
	@Override
	public void process(WatchedEvent event) {
		if(event.getType() == EventType.NodeChildrenChanged){
			try {
				List<String> list = connector.getZooKeeper().getChildren(path, this);
				format(list);
			} catch (InterruptedException | KeeperException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static class RunStatusWatcher implements Watcher{
		private String node;
		private ZooKeeper zooKeeper;
		private ServiceData serviceData;
		
		protected RunStatusWatcher(String node,ZooKeeper zooKeeper,ServiceData serviceData){
			this.node = node;
			this.zooKeeper = zooKeeper;
			this.serviceData = serviceData;
		}
		
		@Override
		public void process(WatchedEvent event) {
			if(event.getType() == EventType.NodeChildrenChanged){
				try {
					List<String> list = zooKeeper.getChildren(node, this);
					serviceData.setRun(list.size() > 0);
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static class InterfaceInfoWatcher implements Watcher{
		private String node;
		private ZooKeeper zooKeeper;
		private ServiceData serviceData;
		
		protected InterfaceInfoWatcher(String node,ZooKeeper zooKeeper,ServiceData serviceData){
			this.node = node;
			this.zooKeeper = zooKeeper;
			this.serviceData = serviceData;
		}
		
		@Override
		public void process(WatchedEvent event) {
			if(event.getType() == EventType.NodeDataChanged){
				String interfaceInfo = null;
				try {
					interfaceInfo = new String(zooKeeper.getData(node,this, null));
				} catch (KeeperException | InterruptedException e) {
					e.printStackTrace();
				}
				Gson gson = new Gson();
				@SuppressWarnings("rawtypes")
				Map map = gson.fromJson(interfaceInfo, Map.class);
				
				String maxVersion = String.valueOf(map.get("maxVersion"));
				serviceData.setMaxVersion(maxVersion);
				
				Date startupDate =new Date(Long.parseLong(String.valueOf(map.get("startupDate"))));
				serviceData.setLastStartupDate(startupDate);
			}
		}
	}
	
	public void format(List<String> list) throws KeeperException, InterruptedException{ 
		serviceList.clear();
		if(list == null){
			return;
		}
		for(String interfaceName : list){
			ServiceData data = new ServiceData();
			data.setInterfaceName(interfaceName);

			List<String> versionList = connector.getZooKeeper().getChildren(path+"/"+interfaceName, null);
			data.setVersionCount(versionList.size());

			String interfaceInfo = new String(connector.getZooKeeper().getData(path+"/"+interfaceName, new InterfaceInfoWatcher(path+"/"+interfaceName, connector.getZooKeeper(), data), null));
			
			Gson gson = new Gson();
			@SuppressWarnings("rawtypes")
			Map map = gson.fromJson(interfaceInfo, Map.class);
			
			String maxVersion = String.valueOf(map.get("maxVersion"));
			data.setMaxVersion(maxVersion);
			
			Date startupDate =new Date(Long.parseLong(String.valueOf(map.get("startupDate"))));
			data.setLastStartupDate(startupDate);
			
			List<String> instanceList = connector.getZooKeeper().getChildren(path+"/"+interfaceName+"/"+maxVersion, new RunStatusWatcher(path+"/"+interfaceName+"/"+maxVersion,connector.getZooKeeper(),data));
			data.setRun(instanceList.size() > 0);
			
			serviceList.add(data);
		}
	}

}
