package cn.itcast.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageHibernateCallback;

// 二级分类管理的dao
public class CategorySecondDao extends HibernateDaoSupport{

	// dao统计二级分类个数的方法
	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// dao分页查询二级分类方法
	public List<CategorySecond> findByPage(int begin, int limit) {
		String hql = "from CategorySecond order by csid desc";
		// 使用离线查询
		List<CategorySecond> list = this.getHibernateTemplate().execute(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// 保存二级分类
	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	// 查询二级分类 根据csid
	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	// 删除二级分类
	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	// 修改二级分类
	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	// dao层查询所有
	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// dao层根据cid查询二级分类
	public List<CategorySecond> findByCid(Integer cid) {
		String hql = "from CategorySecond cs where cs.category.cid = ?";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql,cid);
		return list;
	}

}
