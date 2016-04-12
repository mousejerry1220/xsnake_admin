package org.xsnake.web.dao;

public interface ICache  {

	<T> T get(QueryCondition key);
	
	void put(QueryCondition key,Object value);
	
}