package org.xsnake.web.dao;

public interface ICache  {

	<T> T get(CacheQueryCondition key);
	
	void put(CacheQueryCondition key,Object value);
	
}