package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageHibernateCallback;

// ��Ʒ�־ò�
public class ProductDao extends HibernateDaoSupport{

	// ��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		// ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// ��ѯ���ŵ���Ʒ����������is_host=1
		criteria.add(Restrictions.eq("is_hot", 1));
		// ���ϴ���������Ʒ,����������� ʱ��Խ��
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ  ���з�ҳ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10); // ��0��ʼ����10��
		return list;
	}

	// ��ҳ��������Ʒ
	public List<Product> findNew() {
		// ʹ������������ѯ
		DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		// ��ʱ�䵹��
		criteria.addOrder(Order.desc("pdate"));
		// ִ�в�ѯ���  ���з�ҳ
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10); // ��0��ʼ����10��
		
		return list;
	}

	// ������ƷId��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// ���ݷ���id��ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		// ������Ʒ�Ķ����������һ�������id
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql,cid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݷ����id��ѯ��Ʒ����
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		// sql : select p.* from category c,categorysecond cs,product p where c.cid=cs.cid and cs.csid=p.csid and c.cid=2
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		// ��ҳ �����߷�ҳ��hibernatecallback
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// ���ݶ��������ѯ��Ʒ����
	public int findCountCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,csid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ���ݶ��������ѯ��Ʒ
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// dao���ѯ��Ʒ����
	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ����ҳ��ѯ������Ʒ
	public List<Product> findByPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		List<Product> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql, null, begin, limit));
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}


	// dao�㱣����Ʒ
	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	// dao��ɾ����Ʒ
	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	// dao�޸���Ʒ
	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

	// dao���ѯ��Ʒ��������
	public int findCountFilter(String content,Integer content2) {
		System.out.println("+++++++++++==============");
		String hql = "select count(*) from Product where pname like '%"+content+"%' and is_hot="+content2+"";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	// ����ҳ��ѯ������Ʒ
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
