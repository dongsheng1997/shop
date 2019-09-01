package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageHibernateCallback;

// 订单持久层
public class OrderDao extends HibernateDaoSupport{

	// 保存订单
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// dao我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql,uid);
		if (list != null && list.size()>0) {
			return list.get(0).intValue(); // 统计个数就一个值
		}
		return null;
	}

	// dao我的订单的查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// 持久层修改数据
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// 查询订单个数
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 带分页查询
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
			return list;
	}

	 // dao根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,oid);
		return list;
	}

	// dao根据商品id来查询
	public List<OrderItem> findByPid(Integer pid) {
		String hql = "from OrderItem oi where oi.product.pid=?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql,pid);	
		return list;
	}
	
	
}
