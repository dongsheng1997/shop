package cn.itcast.shop.order.adminaction;

import java.util.List;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// ��̨��������action
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	// ģ������
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	// page����
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	// ע��service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// ����ҳ��ѯ�ķ���
	public String findAll() {
		// ��ҳ��ѯ
		PageBean<Order> pageBean = orderService.findByPage(page);
		// ͨ��ֵջ��������
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
		
	}
	
	//���ݶ���id��ѯ������
	public String findOrderItem() {
		// ���ݶ���id��ѯ������
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// ͨ��ֵջ��ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	// ��̨�޸Ķ���״̬
	public String updateState() {
		// ���ݶ���id��ѯ
		Order currOrder = orderService.findByOid(order.getOid());
		// �޸�״̬
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
