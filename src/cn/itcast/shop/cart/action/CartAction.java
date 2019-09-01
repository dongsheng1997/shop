package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// ���ﳵAction
public class CartAction extends ActionSupport{
	// ����pid
	private Integer pid;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	// ����count
	private Integer count;
	public void setCount(Integer count) {
		this.count = count;
	}
	// ע����Ʒ��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//����������ӵ����ﳵ
	public String addCart() {
		// ��װһ��CartItem����
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		// ����pid��ѯ��Ʒ
		cartItem.setProduct(productService.findByPid(pid));
		// ����������ӵ����ﳵ
		// Cart cart = new Cart(); // ÿ�δ��������µ� ��ǰ�Ķ��ᱻ�ɵ� ����
		// ����Ӧ�ð�cart����session�� sessionû�оʹ������о�ֱ��ȡ��
		Cart cart = getCart();
		
		cart.addCart(cartItem);
		
		return "addCart";
	}
	
	// ��չ��ﳵ�ķ���
	public String clearCart() {
		// ��ù��ﳵ�Ķ���
		Cart cart = getCart();
		// ���÷�����չ��ﳵ
		cart.clearCart();
		return "clearCart";
	}
	
	// �ӹ��ﳵ���Ƴ��������
	public String removeCart() {
		// ��ù��ﳵ����
		Cart cart = getCart();
		// ���ù��ﳵɾ������
		cart.removeCart(pid);
		// ����ҳ��
		return "removeCart";
	}
	
	// �ҵĹ��ﳵִ�еķ���
	public String myCart() {
		return "myCart";
	}
	
	
	// ��ù��ﳵ�ķ��� �� ��session�л�ù��ﳵ
	private Cart getCart(){
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		// ���û�� ��newһ��������sessionn��
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		// �����
		return cart;
	}
}
