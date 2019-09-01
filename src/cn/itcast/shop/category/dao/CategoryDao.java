package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

// һ������ĳ־ò����
public class CategoryDao extends HibernateDaoSupport{

	// Dao��ѯ����һ�����෽��
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> cList = this.getHibernateTemplate().find(hql);
		return cList;
	}

	// ����һ������
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	// ��ѯһ���������cid
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	
	// ɾ��һ������
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	// �޸�һ������
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
