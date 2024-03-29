package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

// 订单业务层
@Transactional // 调用持久层，需要加上事务
public class OrderService {
	
	// 注入orderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	//保存订单
	public void save(Order order) {
		orderDao.save(order);
	}

	// 我的订单业务层
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页现实的记录数
		Integer limit = 5;
		pageBean.setLimit(limit);
		// 设置总的记录数
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据订单的oid查询订单
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	// 业务层修改订单的操作
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	// 后台分页查询订单
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页条数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总条数
		int totalCount;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage;
		if (totalCount % limit == 0) {
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置list
		int begin = (page-1)*limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	// 业务层根据商品id来查询
	public List<OrderItem> findByPid(Integer pid) {
		return orderDao.findByPid(pid);
	}

	
}
