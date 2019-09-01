package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

// 一级分类的持久层对象
public class CategoryDao extends HibernateDaoSupport{

	// Dao查询所有一级分类方法
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> cList = this.getHibernateTemplate().find(hql);
		return cList;
	}

	// 保存一级分类
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	// 查询一级分类根据cid
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}
	
	// 删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	// 修改一级分类
	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
