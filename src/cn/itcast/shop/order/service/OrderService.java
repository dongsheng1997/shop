package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

// ����ҵ���
@Transactional // ���ó־ò㣬��Ҫ��������
public class OrderService {
	
	// ע��orderDao
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	//���涩��
	public void save(Order order) {
		orderDao.save(order);
	}

	// �ҵĶ���ҵ���
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʵ�ļ�¼��
		Integer limit = 5;
		pageBean.setLimit(limit);
		// �����ܵļ�¼��
		Integer totalCount = null;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// �����ܵ�ҳ��
		Integer totalPage = null;
		if (totalCount % limit == 0) {
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���
		Integer begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ���ݶ�����oid��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	// ҵ����޸Ķ����Ĳ���
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}

	// ��̨��ҳ��ѯ����
	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ����
		int limit = 10;
		pageBean.setLimit(limit);
		// ����������
		int totalCount;
		totalCount = orderDao.findByCount();
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage;
		if (totalCount % limit == 0) {
			totalPage = totalCount/limit;
		}else {
			totalPage = totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		// ����list
		int begin = (page-1)*limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ҵ�����ݶ���id��ѯ������
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}

	// ҵ��������Ʒid����ѯ
	public List<OrderItem> findByPid(Integer pid) {
		return orderDao.findByPid(pid);
	}

	
}
