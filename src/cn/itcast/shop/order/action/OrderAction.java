package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.ietf.jgss.Oid;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.util.PageBean;
import cn.itcast.shop.util.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 订单action
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	// 注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 模型驱动 订单
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	// 属性驱动page
	private Integer page;
	public void setPage(int page) {
		this.page = page;
	}
	
	// 接收支付通道编码
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	// 接收付款成功后的响应数据
	private String r6_Order; //订单id
	private String r3_Amt;// 付款金额
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// 注入orderSerivice
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	// 生成订单的方法
	public String save() {
		// 1.保存数据到数据库
		// 订单数据补全操作
		order.setOrdertime(new Date());
		// 1.1 状态
		order.setState(1);// 1 未付款 2 已经付款，未发货 3 已经发货，但未确定收货 4交易完成
		// 1.2 总计
		// 总计的数据是购物车的信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		// 没有去购物车 就直接去订单页面
		if (cart == null) {
			this.addActionError("您还没有购物，请先去购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 1.3 设置订单的订单项
		for(CartItem cartItem:cart.getCartItems()){// 遍历购物车里面的购物项
			OrderItem orderItem = new OrderItem(); // 创建一个订单项
			orderItem.setCount(cartItem.getCount());// 购物项的数量
			orderItem.setSubtotal(cartItem.getSubtotal());// 购物项小计
			orderItem.setProduct(cartItem.getProduct());// 购买那个商品
			orderItem.setOrder(order); // 一个订单项只属于一个订单
			
			order.getOrderItems().add(orderItem); // 保存每一个订单项
		}
		// 1.4设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("您还没有登录，请去登录！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.将订单对象显示到页面上
		// 通过值栈的方式:因为Order显示的对象就是模型驱动的使用的对象
		// 3.清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	
	// 我的订单查询
	public String findByUid() {
		// 根据用户id查询
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// 调用service
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		// 将分页数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	// 根据订单的oid查询订单
	public String findByOid() {
		order = orderService.findByOid(order.getOid());// 模型驱动已注入
		return "findByOidSuccess";
	}
	
	// 为订单付款的方法
	public String payOrder() throws IOException {
		// 修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 为订单付款
		String p0_Cmd = "Buy";//业务类型
		String p1_MerId = "10001126856"; //商户编号
		String p2_Order = order.getOid().toString(); // 商品订单号
		String p3_Amt = "0.01";// 付款金额
		String p4_Cur = "CNY"; // 交易的币种
		String p5_Pid = "";//商品名称
		String p6_Pcat = ""; //商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = "http://localhost/shop/order_callBack.action";// 支付成功后跳转的页面
		String p9_SAF = ""; // 送货地址
		String pa_MP = "";//商户扩展信息
		String pd_FrpId = this.pd_FrpId; // 支付通道编码
		String pr_NeedResponse = "1";// 应答机制
		
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//签名数据
		// 向易宝出发
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		
		// 不同服务器，需要重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	// 付款成功后的转向
	public String callBack() {
		// 修改订单状态，修改状态为已经付款
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));// 查询
		currOrder.setState(2);
		orderService.update(currOrder);
		// 在页面显示付款成功信息
		this.addActionMessage("订单付款成功：订单编号:"+r6_Order+"付款金额："+r3_Amt);
		List<OrderItem> list = orderService.findOrderItem(Integer.parseInt(r6_Order));
		productService.updateNumber(list);
		return "msg";
	}
	
	// 确认收货 ：修改订单状态
	public String updateState() {
		// 根据订单id查询订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		
		return "updateStateSuccess";
	}
	
}
