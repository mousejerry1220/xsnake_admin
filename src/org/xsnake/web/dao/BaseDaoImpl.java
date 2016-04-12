package org.xsnake.web.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.xsnake.web.page.IPage;
import org.xsnake.web.page.PageImpl;

@Service("baseDao")
@SuppressWarnings("unchecked")
public class BaseDaoImpl implements BaseDao ,CacheBaseDao {

	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	ICache cache;

	public HibernateTemplate getHibernateTemplate() {

		return hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private Query createQuery(Session session, String hql, final Object[] args) {
		Query query = session.createQuery(hql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query;
	}

	private SQLQuery createSQLQuery(Session session, String sql, Object[] args) {
		SQLQuery query = session.createSQLQuery(sql);
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		return query;
	}

	@Override
	public <T> void deleteAll(List<T> list) {
		getHibernateTemplate().deleteAll(list);
	}

	@Override
	public <T> void deleteById(Class<T> c, Serializable id) {
		T t = this.load(c, id);
		this.delete(t);
	}

	@Override
	public <T> void save(List<T> l) {
		for (T t : l) {
			getHibernateTemplate().save(t);
		}
	}

	@Override
	public <T> T get(Class<T> c, Serializable k) {
		return (T) this.getHibernateTemplate().get(c, k);
	}

	@Override
	public <T> T load(Class<T> c, Serializable k) {
		return (T) this.getHibernateTemplate().load(c, k);
	}

	@Override
	public <T> T save(T t) {
		return (T) this.getHibernateTemplate().save(t);
	}

	@Override
	public <T> T update(T t) {
		this.getHibernateTemplate().update(t);
		return t;
	}

	@Override
	public <T> void update(List<T> l) {
		for (T t : l) {
			getHibernateTemplate().update(t);
		}
	}

	@Override
	public void saveOrUpdate(Object t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public <T> T findUniqueEntity(final String hql, final Object[] args) {
		HibernateCallback<T> callback = new HibernateCallback<T>() {

			@Override
			public T doInHibernate(Session session) throws HibernateException {
				try {
					Query query = createQuery(session, hql, args);
					return (T) query.uniqueResult();
				} finally {
					session.close();
				}
			}

		};
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public void delete(Object o) {
		this.getHibernateTemplate().delete(o);
	}

	@Override
	public <T> List<T> findEntity(String hql) {
		return (List<T>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public <T> List<T> findEntity(String hql, Object obj) {
		return findEntity(hql, new Object[] { obj });
	}

	@Override
	public <T> List<T> findEntity(final String hql, final Object[] args) {

		HibernateCallback<List<T>> callback = new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				try {
					Query query = createQuery(session, hql, args);
					return query.list();
				} finally {
					session.close();
				}
			}
		};
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public <T> List<T> findEntity(final String hql, final int start,
			final int num) {
		return findEntity(hql, null, start, num);
	}

	@Override
	public <T> List<T> findEntity(final String hql, final Object[] args,
			final int start, final int num) {

		HibernateCallback<List<T>> callback = new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException {
				try {
					Query query = createQuery(session, hql, args);
					query.setFirstResult(start).setMaxResults(num);
					return query.list();
				} finally {
					session.close();
				}
			}
		};

		return getHibernateTemplate().execute(callback);
	}

	public void executeHQL(final String hql) {
		executeHQL(hql,null);
	}

	public void executeHQL(final String hql, final Object[] args) {

		HibernateCallback<Object> callback = new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException {
				try {
					Query query = createQuery(session, hql, args);
					return query.executeUpdate();
				} finally {
					session.close();
				}
			}
		};

		getHibernateTemplate().execute(callback);
	}

	@Override
	public void executeSQL(String sql) {
		executeSQL(sql, null);
	}

	@Override
	public void executeSQL(final String sql, final Object args) {
		executeSQL(sql, new Object[] { args });
	}

	@Override
	public void executeSQL(final String sql, final Object[] args) {
		HibernateCallback<Object> callback = new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery query = createSQLQuery(session, sql, args);
					return query.executeUpdate();
				} finally {
					session.close();
				}
			}
		};
		getHibernateTemplate().execute(callback);
	}

	@Override
	public List<Object[]> findBySql(final String sql) {
		return findBySql(sql, null);
	}

	@Override
	public List<Object[]> findBySql(final String sql, final Object[] args) {
		HibernateCallback<List<Object[]>> callback = new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery query = createSQLQuery(session, sql, args);
					return query.list();
				} finally {
					session.close();
				}
			}
		};

		return getHibernateTemplate().execute(callback);
	}

	@Override
	public List<Object[]> findBySql(String sql, Object args) {
		return findBySql(sql, new Object[] { args });
	}

	@Override
	public List<Object[]> findBySql(final String sql, final Object[] args,
			final int start, final int num) {
		HibernateCallback<List<Object[]>> callback = new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session)
					throws HibernateException {
				try {
					SQLQuery query = createSQLQuery(session, sql, args);
					query.setFirstResult(start).setMaxResults(num);
					return query.list();
				} finally {
					session.close();
				}
			}
		};

		return getHibernateTemplate().execute(callback);
	}

	@Override
	public List<Object[]> findBySql(String sql, int start, int num) {
		return findBySql(sql, null, start, num);
	}

	@Override
	public List<Object[]> findBySql(String sql, Object obj, int start, int num) {
		return findBySql(sql, new Object[] { obj }, start, num);
	}

	@Override
	public <T> T findUniqueBySql(final String sql, final Object[] args) {
		HibernateCallback<T> callback = new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery query = createSQLQuery(session, sql, args);
					return (T) query.uniqueResult();
				} finally {
					session.close();
				}

			}
		};
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public <T> T findUniqueBySql(String sql, Object obj) {
		return findUniqueBySql(sql, new Object[] { obj });
	}

	@Override
	public <T> T findUniqueBySql(String sql) {
		return findUniqueBySql(sql, null);
	}

	@Override
	public <T> T findUniqueEntity(String hql) {
		return findUniqueEntity(hql, null);
	}

	@Override
	public <T> T findUniqueEntity(String hql, Object args) {
		return findUniqueEntity(hql, new Object[] { args });
	}

	public <T> T uniqueFindBySql(final String sql, final Object[] args) {

		HibernateCallback<T> callback = new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException {
				try {
					SQLQuery query = createSQLQuery(session, sql, args);
					return (T) query.uniqueResult();
				} finally {
					session.close();
				}

			}
		};

		return getHibernateTemplate().execute(callback);
	}

	@Override
	public IPage search(String hql, int size, int goPage) {
		return this.searchForPager(hql, null, size, goPage);
	}

	@Override
	public IPage search(String hql, Object[] args, int size, int goPage) {
		return this.searchForPager(hql, args, size, goPage);
	}

	/**
	 * 此方法相比searchForPager方法，可以再hql语句中使用SELECT子句(2013/8/3)
	 */
	public IPage searchForPager(String hql, Object[] args, int pageLength, int currentPage) {
		try {
			String thql = null;
			if (hql.indexOf("select") == -1) {
				thql = "select count(*) " + hql;
			} else {
				thql = "select count(*) " + hql.substring(hql.indexOf("from"));
			}
			if (hql.indexOf("order by") > -1) {
				thql = thql.substring(0, thql.indexOf("order by"));
			}
			Long _count = findUniqueEntity(thql, args);
			int count = _count.intValue();
			return searchForPager(hql, args, pageLength, currentPage, count);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private IPage searchForPager(String hql, Object[] args, int pageLength,
			int currentPage, int count) {
		try {
			List<Object> results = findEntity(hql, args, (currentPage - 1) * pageLength, pageLength);
			return new PageImpl(results, currentPage, pageLength, count);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T $get(Class<T> c, Serializable id) {
		T t = cache.get(new CacheQueryCondition(c,id));
		if(t == null){
			t = get(c,id);
			cache.put(new CacheQueryCondition(c,id), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> T $findUniqueEntity(String hql) {
		T t = cache.get(new CacheQueryCondition(hql));
		if(t == null){
			t = findUniqueEntity(hql);
			cache.put(new CacheQueryCondition(hql), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> T $findUniqueEntity(String hql, Object args) {
		T t = cache.get(new CacheQueryCondition(hql,args));
		if(t == null){
			t = findUniqueEntity(hql,args);
			cache.put(new CacheQueryCondition(hql,args), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> T $findUniqueEntity(String hql, Object[] args) {
		T t = cache.get(new CacheQueryCondition(hql,args));
		if(t == null){
			t = findUniqueEntity(hql,args);
			cache.put(new CacheQueryCondition(hql,args), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> List<T> $findEntity(String hql) {
		List<T> l = cache.get(new CacheQueryCondition(hql));
		if(l == null){
			l = findEntity(hql);
			cache.put(new CacheQueryCondition(hql), l);
			return l;
		}
		return l;
	}

	@Override
	public <T> List<T> $findEntity(String hql, Object args) {
		List<T> l = cache.get(new CacheQueryCondition(hql,args));
		if(l == null){
			l = findEntity(hql,args);
			cache.put(new CacheQueryCondition(hql,args), l);
			return l;
		}
		return l;
	}

	@Override
	public <T> List<T> $findEntity(String hql, Object[] args) {
		List<T> l = cache.get(new CacheQueryCondition(hql,args));
		if(l == null){
			l = findEntity(hql,args);
			cache.put(new CacheQueryCondition(hql,args), l);
			return l;
		}
		return l;
	}

	@Override
	public <T> List<T> $findEntity(String hql, int start, int num) {
		List<T> l = cache.get(new CacheQueryCondition(hql,start,num));
		if(l == null){
			l = findEntity(hql,start,num);
			cache.put(new CacheQueryCondition(hql,start,num), l);
			return l;
		}
		return l;
	}

	@Override
	public <T> List<T> $findEntity(String hql, Object[] args, int start, int num) {
		List<T> l = cache.get(new CacheQueryCondition(hql,args,start,num));
		if(l == null){
			l = findEntity(hql,args,start,num);
			cache.put(new CacheQueryCondition(hql,args,start,num), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql));
		if(l == null){
			l = findBySql(sql);
			cache.put(new CacheQueryCondition(sql), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql, Object[] args) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql,args));
		if(l == null){
			l = findBySql(sql,args);
			cache.put(new CacheQueryCondition(sql,args), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql, Object args) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql,args));
		if(l == null){
			l = findBySql(sql,args);
			cache.put(new CacheQueryCondition(sql,args), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql, int start, int num) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql,start,num));
		if(l == null){
			l = findBySql(sql,start,num);
			cache.put(new CacheQueryCondition(sql,start,num), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql, Object[] args, int start, int num) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql,args,start,num));
		if(l == null){
			l = findBySql(sql,args,start,num);
			cache.put(new CacheQueryCondition(sql,args,start,num), l);
			return l;
		}
		return l;
	}

	@Override
	public List<Object[]> $findBySql(String sql, Object args, int start, int num) {
		List<Object[]> l = cache.get(new CacheQueryCondition(sql,args,start,num));
		if(l == null){
			l = findBySql(sql,args,start,num);
			cache.put(new CacheQueryCondition(sql,args,start,num), l);
			return l;
		}
		return l;
	}

	@Override
	public <T> T $findUniqueBySql(String sql, Object[] args) {
		T t = cache.get(new CacheQueryCondition(sql,args));
		if(t == null){
			t = findUniqueBySql(sql,args);
			cache.put(new CacheQueryCondition(sql,args), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> T $findUniqueBySql(String sql, Object args) {
		T t = cache.get(new CacheQueryCondition(sql,args));
		if(t == null){
			t = findUniqueBySql(sql,args);
			cache.put(new CacheQueryCondition(sql,args), t);
			return t;
		}
		return t;
	}

	@Override
	public <T> T $findUniqueBySql(String sql) {
		T t = cache.get(new CacheQueryCondition(sql));
		if(t == null){
			t = findUniqueBySql(sql);
			cache.put(new CacheQueryCondition(sql), t);
			return t;
		}
		return t;
	}

}
