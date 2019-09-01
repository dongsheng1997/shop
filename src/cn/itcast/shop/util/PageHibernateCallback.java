package cn.itcast.shop.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

public class PageHibernateCallback<T> implements HibernateCallback<List<T>>{
	
	private String hql;
	private Object[] params;
	private int startIndex;
	private int pageSize;
	
	
	public PageHibernateCallback(String hql, Object[] params,
			int startIndex, int pageSize) {
		super();
		this.hql = hql;
		this.params = params;
		this.startIndex = startIndex;
		this.pageSize = pageSize;
	}


	
	public List<T> doInHibernate(Session session) throws HibernateException,
			SQLException {
		//1 执行hql语句
		Query query = session.createQuery(hql);
		//2 实际参数
		if(params != null){
			for(int i = 0 ; i < params.length ; i++){// 遍历数组
				query.setParameter(i, params[i]); // 设置参数
			}
		}
		//3 分页
		query.setFirstResult(startIndex);// 开始
		query.setMaxResults(pageSize);// 几条
		
		return query.list();
	}

}
