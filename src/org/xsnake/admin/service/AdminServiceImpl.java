package org.xsnake.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Service;
import org.xsnake.remote.connector.ZookeeperConnector;

@Service
public class AdminServiceImpl implements AdminService {

	ZookeeperConnector connector;
	
	List<ServiceData> serviceList = new ArrayList<ServiceData>();
	
	ServiceWatcher serviceWatcher;
	
	public static final String SERVICE_PATH = "/xsnake/service";
	
	public AdminServiceImpl(){
		
		connector = ZookeeperConnector.getConnector("127.0.0.1:2181", 10, new Watcher() {
			@Override
			public void process(WatchedEvent event) {
				if (event.getState() == Event.KeeperState.SyncConnected) {
					init();
                }
			}
		});
		
		serviceWatcher = new ServiceWatcher(connector, SERVICE_PATH, serviceList);
	}
	
	private void init(){
		try { 
			if(connector.getZooKeeper().exists(SERVICE_PATH, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					try {
						List<String> list = connector.getZooKeeper().getChildren(SERVICE_PATH, serviceWatcher);
						serviceWatcher.format(list);
					} catch (KeeperException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			})!=null){
				List<String> list = connector.getZooKeeper().getChildren(SERVICE_PATH, serviceWatcher);
				serviceWatcher.format(list);
			}
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void restart(String serverId) {
		
	}

	@Override
	public void restartAll() {
		
	}

	@Override
	public List<ServiceData> getServiceList() {
		return serviceList;
	}

}
