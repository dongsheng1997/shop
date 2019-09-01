package cn.itcast.shop.order.adminaction;

import java.util.List;

import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 后台订单管理action
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	// 模型驱动
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	// page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	// 注入service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// 带分页查询的方法
	public String findAll() {
		// 分页查询
		PageBean<Order> pageBean = orderService.findByPage(page);
		// 通过值栈保存数据
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
		
	}
	
	//根据订单id查询订单项
	public String findOrderItem() {
		// 根据订单id查询订单项
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		// 通过值栈显示到页面
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	// 后台修改订单状态
	public String updateState() {
		// 根据订单id查询
		Order currOrder = orderService.findByOid(order.getOid());
		// 修改状态
		currOrder.setState(3);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
