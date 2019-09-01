package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageHibernateCallback;

// �����־ò�
public class OrderDao extends HibernateDaoSupport{

	// ���涩��
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// dao�ҵĶ����ĸ���ͳ��
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue(); // ͳ�Ƹ�����һ��ֵ
		}
		return null;
	}

	// dao�ҵĶ����Ĳ�ѯ
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// �־ò��޸�����
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// ��ѯ��������
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// ����ҳ��ѯ
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
			return list;
	}

	 // dao���ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		return list;
	}

	// dao������Ʒid����ѯ
	public List<OrderItem> findByPid(Integer pid) {
		String hql = "from OrderItem oi where oi.product.pid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,pid);	
		return list;
	}
	
	
}
