package org.xsnake.web.dao;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheImpl implements ICache{
	
	public static final String CACHE_NAME = "default_cache";
	
	private String conf;
	
	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		this.conf = conf;
	}

	CacheManager cm ;
	
	private CacheManager getCacheManager(){
		if(cm == null){
			cm = CacheManager.create(conf);
		}
		return cm;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(QueryCondition key) {
		CacheManager cm = getCacheManager();
		if(cm == null){
			return null;
		}
		Cache c = cm.getCache(CACHE_NAME);
		if(c == null){
			cm.addCache(CACHE_NAME);
			c = cm.getCache(CACHE_NAME);
		}
		Element e = c.get(key.toString());
		return (T)(e==null?null : e.getValue());
	}

	@Override
	public void put(QueryCondition key, Object value) {
		CacheManager cm = getCacheManager();
		if(cm == null){
			return ;
		}
		Cache c = cm.getCache(CACHE_NAME);
		if(c == null){
			cm.addCache(CACHE_NAME);
			c = cm.getCache(CACHE_NAME);
		}
		c.put(new Element(key.toString(),value));
	}

	
}
