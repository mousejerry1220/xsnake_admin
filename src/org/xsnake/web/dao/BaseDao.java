package org.xsnake.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.xsnake.web.page.IPage;

public interface BaseDao {

	public SessionFactory getSessionFactory();

	public HibernateTemplate getHibernateTemplate();
	
	public <T> void save(List<T> l);

	public <T> void deleteById(Class<T> c, Serializable id);

	public <T> void deleteAll(List<T> l);

	public <T> T load(Class<T> c, Serializable id);

	public <T> T get(Class<T> c, Serializable id);
	
	public void delete(Object obj);

	public <T> T save(T t);

	public <T> T update(T t);

	public <T> void update(List<T> l);
	
	public void saveOrUpdate(Object t);

	public <T> T findUniqueEntity(String hql);
	
	public <T> T findUniqueEntity(String hql, Object args);

	public <T> T findUniqueEntity(String hql, Object[] args);

	public <T> List<T> findEntity(String hql);

	public <T> List<T> findEntity(String hql, Object args);

	public <T> List<T> findEntity(String hql, Object[] args);

	public <T> List<T> findEntity(String hql, int start, int num);
	
	public <T> List<T> findEntity(final String hql, Object[] args, final int start, final int num);
	
	public void executeHQL(String hql);

	public void executeHQL(String hql, Object[] args);

	public void executeSQL(String sql);

	public void executeSQL(String sql, Object args);

	public void executeSQL(String sql, Object[] args);

	public List<Object[]> findBySql(final String sql);

	public List<Object[]> findBySql(final String sql, Object[] args);

	public List<Object[]> findBySql(final String sql, Object args);

	public List<Object[]> findBySql(String sql, int start, int num);

	public List<Object[]> findBySql(String sql, Object[] args, int start, int num);

	public List<Object[]> findBySql(String sql, Object obj, int start, int num);

	public <T> T findUniqueBySql(String sql, Object[] args);

	public <T> T findUniqueBySql(String sql, Object obj);

	public <T> T findUniqueBySql(String sql);
	
	public IPage search(String hql, int size, int goPage);

	public IPage search(String hql, Object[] args, int size, int goPage);
	
}




