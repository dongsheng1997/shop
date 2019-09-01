package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageHibernateCallback;

// 商品持久层
public class ProductDao extends HibernateDaoSupport{

	// 首页上热门商品查询
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 查询热门的商品，条件就是is_host=1
		criteria.add(Restrictions.eq("is_hot", 1));
		// 新上传的热门商品,倒序排序输出 时间越大
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询  进行分页
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10); // 从0开始，共10条
		return list;
	}

	// 首页上最新商品
	public List<Product> findNew() {
		// 使用离线条件查询
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// 按时间倒序
		criteria.addOrder(Order.desc("pdate"));
		// 执行查询结果  进行分页
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10); // 从0开始，共10条
		
		return list;
	}

	// 根据商品Id查询商品
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 根据分类id查询商品的个数
	public int findCountCid(Integer cid) {
		// 根据商品的二级分类查找一级分类的id
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据分类的id查询商品集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// sql : select p.* from category c,categorysecond cs,product p where c.cid=cs.cid and cs.csid=p.csid and c.cid=2
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		// 分页 ：离线分页，hibernatecallback
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// 根据二级分类查询商品个数
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 根据二级分类查询商品
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// dao层查询商品个数
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 带分页查询所有商品
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}


	// dao层保存商品
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	// dao层删除商品
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	// dao修改商品
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	// dao层查询商品条件个数
	public int findCountFilter(String content,Integer content2) {
		System.out.println("+++++++++++==============");
		String hql = "select count(*) from Product where pname like '%"+content+"%' and is_hot="+content2+"";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// 带分页查询所有商品
	public List<Product> findByPageFilter(int begin, int limit, String content,Integer content2) {
//		String hql = "from Product where pname like '%"+content+"%'"+"order by pdate desc";
		String hql = "from Product where pname like '%"+content+"%' and is_hot="+content2+""+"order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	public void updateNumber(Product product) {
		this.getHibernateTemplate().update(product);
	}
}
