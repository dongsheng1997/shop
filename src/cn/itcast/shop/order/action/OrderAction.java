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

// ����action
public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	// ע��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// ģ������ ����
	private Order order = new Order();
	public Order getModel() {
		return order;
	}
	
	// ��������page
	private Integer page;
	public void setPage(int page) {
		this.page = page;
	}
	
	// ����֧��ͨ������
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	// ���ո���ɹ������Ӧ����
	private String r6_Order; //����id
	private String r3_Amt;// ������
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// ע��orderSerivice
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	
	// ���ɶ����ķ���
	public String save() {
		// 1.�������ݵ����ݿ�
		// �������ݲ�ȫ����
		order.setOrdertime(new Date());
		// 1.1 ״̬
		order.setState(1);// 1 δ���� 2 �Ѿ����δ���� 3 �Ѿ���������δȷ���ջ� 4�������
		// 1.2 �ܼ�
		// �ܼƵ������ǹ��ﳵ����Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		// û��ȥ���ﳵ ��ֱ��ȥ����ҳ��
		if (cart == null) {
			this.addActionError("����û�й������ȥ����!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 1.3 ���ö����Ķ�����
		for(CartItem cartItem:cart.getCartItems()){// �������ﳵ����Ĺ�����
			OrderItem orderItem = new OrderItem(); // ����һ��������
			orderItem.setCount(cartItem.getCount());// �����������
			orderItem.setSubtotal(cartItem.getSubtotal());// ������С��
			orderItem.setProduct(cartItem.getProduct());// �����Ǹ���Ʒ
			orderItem.setOrder(order); // һ��������ֻ����һ������
			
			order.getOrderItems().add(orderItem); // ����ÿһ��������
		}
		// 1.4���ö��������û�
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("����û�е�¼����ȥ��¼��");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.������������ʾ��ҳ����
		// ͨ��ֵջ�ķ�ʽ:��ΪOrder��ʾ�Ķ������ģ��������ʹ�õĶ���
		// 3.��չ��ﳵ
		cart.clearCart();
		return "saveSuccess";
	}
	
	// �ҵĶ�����ѯ
	public String findByUid() {
		// �����û�id��ѯ
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		// ����service
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		// ����ҳ������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	// ���ݶ�����oid��ѯ����
	public String findByOid() {
		order = orderService.findByOid(order.getOid());// ģ��������ע��
		return "findByOidSuccess";
	}
	
	// Ϊ��������ķ���
	public String payOrder() throws IOException {
		// �޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// Ϊ��������
		String p0_Cmd = "Buy";//ҵ������
		String p1_MerId = "10001126856"; //�̻����
		String p2_Order = order.getOid().toString(); // ��Ʒ������
		String p3_Amt = "0.01";// ������
		String p4_Cur = "CNY"; // ���׵ı���
		String p5_Pid = "";//��Ʒ����
		String p6_Pcat = ""; //��Ʒ����
		String p7_Pdesc = "";//��Ʒ����
		String p8_Url = "http://localhost/shop/order_callBack.action";// ֧���ɹ�����ת��ҳ��
		String p9_SAF = ""; // �ͻ���ַ
		String pa_MP = "";//�̻���չ��Ϣ
		String pd_FrpId = this.pd_FrpId; // ֧��ͨ������
		String pr_NeedResponse = "1";// Ӧ�����
		
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//ǩ������
		// ���ױ�����
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
		
		// ��ͬ����������Ҫ�ض����ױ�
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	// ����ɹ����ת��
	public String callBack() {
		// �޸Ķ���״̬���޸�״̬Ϊ�Ѿ�����
		Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));// ��ѯ
		currOrder.setState(2);
		orderService.update(currOrder);
		// ��ҳ����ʾ����ɹ���Ϣ
		this.addActionMessage("��������ɹ����������:"+r6_Order+"�����"+r3_Amt);
		List<OrderItem> list = orderService.findOrderItem(Integer.parseInt(r6_Order));
		productService.updateNumber(list);
		return "msg";
	}
	
	// ȷ���ջ� ���޸Ķ���״̬
	public String updateState() {
		// ���ݶ���id��ѯ����
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		
		return "updateStateSuccess";
	}
	
}
