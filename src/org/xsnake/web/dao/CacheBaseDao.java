package org.xsnake.web.dao;

import java.io.Serializable;
import java.util.List;

public interface CacheBaseDao {
	
	public <T> T $get(Class<T> c, Serializable id);
	
	public <T> T $findUniqueEntity(String hql);
	
	public <T> T $findUniqueEntity(String hql, Object args);

	public <T> T $findUniqueEntity(String hql, Object[] args);
	
	public <T> T $findUniqueBySql(String sql, Object[] args);

	public <T> T $findUniqueBySql(String sql, Object obj);

	public <T> T $findUniqueBySql(String sql);
	
	public List<Object[]> $findBySql(final String sql);

	public List<Object[]> $findBySql(final String sql, Object[] args);

	public List<Object[]> $findBySql(final String sql, Object args);

	public List<Object[]> $findBySql(String sql, int start, int num);

	public List<Object[]> $findBySql(String sql, Object[] args, int start, int num);

	public List<Object[]> $findBySql(String sql, Object obj, int start, int num);
	
	public <T> List<T> $findEntity(String hql);

	public <T> List<T> $findEntity(String hql, Object args);

	public <T> List<T> $findEntity(String hql, Object[] args);

	public <T> List<T> $findEntity(String hql, int start, int num);

	public <T> List<T> $findEntity(final String hql, Object[] args, final int start, final int num);

}
