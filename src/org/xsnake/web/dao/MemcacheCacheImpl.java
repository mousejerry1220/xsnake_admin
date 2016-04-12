package org.xsnake.web.dao;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheCacheImpl extends MemCachedClient implements ICache {
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(QueryCondition key) { 
		return (T)this.get(key.toString());
	}

	@Override
	public void put(QueryCondition key, Object value) {
		this.set(key.toString(), value);
	}

	private String address;
	private String weights; 
	private Integer initConn;
	private Integer minConn;
	private Integer maxConn;
	private Long maxIdle;
	private Boolean nagle;
	private Integer socketConnectTO;
	private Boolean aliveCheck;
	private Boolean failback;
	private Boolean failover;
	private Integer hashingAlg;
	private Long maintSleep;
	private Long maxBusyTime;
	private Integer socketTO;
	
	public void init() {
		SockIOPool pool = SockIOPool.getInstance();
		
		String[] addr = address.split(";");
		pool.setServers(addr);
		
		if(weights != null){
			String[] weightList = weights.split(";");
			Integer[] ws = new Integer[weightList.length];
			for(int i=0;i<weightList.length;i++){
				ws[i] = Integer.parseInt(weightList[i]);
			}
			pool.setWeights(ws);
		}
		if(initConn!=null){
			pool.setInitConn(initConn);
		}
		
		if(minConn!=null){
			pool.setMinConn(minConn);
		}
		
		if(maxConn!=null){ 
			pool.setMaxConn(maxConn);
		}
		
		if(maxIdle !=null){
			pool.setMaxIdle(maxIdle);
		}
		
		if(nagle !=null){
			pool.setNagle(nagle);
		}
		
		if(socketConnectTO!=null){
			pool.setSocketConnectTO(socketConnectTO);
		}
		
		if(aliveCheck!=null){
			pool.setAliveCheck(aliveCheck);
		}
		
		if(failback!=null){
			pool.setFailback(failback);
		}
		
		if(failover!=null){
			pool.setFailover(failover);
		}
		
		if(hashingAlg!=null){
			pool.setHashingAlg(hashingAlg);
		}
		
		if(maintSleep!=null){
			pool.setMaintSleep(maintSleep);
		}
		
		if(maxBusyTime!=null){
			pool.setMaxBusyTime(maxBusyTime);
		}
		
		if(socketTO!=null){
			pool.setSocketTO(socketTO);
		}
		
		pool.initialize();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeights() {
		return weights;
	}

	public void setWeights(String weights) {
		this.weights = weights;
	}

	public Integer getInitConn() {
		return initConn;
	}

	public void setInitConn(Integer initConn) {
		this.initConn = initConn;
	}

	public Integer getMinConn() {
		return minConn;
	}

	public void setMinConn(Integer minConn) {
		this.minConn = minConn;
	}

	public Integer getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(Integer maxConn) {
		this.maxConn = maxConn;
	}

	public Long getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Long maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Boolean getNagle() {
		return nagle;
	}

	public void setNagle(Boolean nagle) {
		this.nagle = nagle;
	}

	public Integer getSocketConnectTO() {
		return socketConnectTO;
	}

	public void setSocketConnectTO(Integer socketConnectTO) {
		this.socketConnectTO = socketConnectTO;
	}

	public Boolean getAliveCheck() {
		return aliveCheck;
	}

	public void setAliveCheck(Boolean aliveCheck) {
		this.aliveCheck = aliveCheck;
	}

	public Boolean getFailback() {
		return failback;
	}

	public void setFailback(Boolean failback) {
		this.failback = failback;
	}

	public Boolean getFailover() {
		return failover;
	}

	public void setFailover(Boolean failover) {
		this.failover = failover;
	}

	public Integer getHashingAlg() {
		return hashingAlg;
	}

	public void setHashingAlg(Integer hashingAlg) {
		this.hashingAlg = hashingAlg;
	}

	public Long getMaintSleep() {
		return maintSleep;
	}

	public void setMaintSleep(Long maintSleep) {
		this.maintSleep = maintSleep;
	}

	public Long getMaxBusyTime() {
		return maxBusyTime;
	}

	public void setMaxBusyTime(Long maxBusyTime) {
		this.maxBusyTime = maxBusyTime;
	}

	public Integer getSocketTO() {
		return socketTO;
	}

	public void setSocketTO(Integer socketTO) {
		this.socketTO = socketTO;
	}
	
}
