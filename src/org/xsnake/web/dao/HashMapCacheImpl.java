package org.xsnake.web.dao;

import java.util.HashMap;

public class HashMapCacheImpl extends HashMap<String, Object> implements ICache {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(QueryCondition key) {
		return (T)this.get(key.toString());
	}

	@Override
	public void put(QueryCondition key, Object value) {
		this.put(key.toString(), value);
	}

}
