package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// 购物车Action
public class CartAction extends ActionSupport{
	// 接收pid
	private Integer pid;
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	// 接收count
	private Integer count;
	public void setCount(Integer count) {
		this.count = count;
	}
	// 注入商品的service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//将购物项添加到购物车
	public String addCart() {
		// 封装一个CartItem对象
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		// 根据pid查询商品
		cartItem.setProduct(productService.findByPid(pid));
		// 将购物项添加到购物车
		// Cart cart = new Cart(); // 每次创建都是新的 以前的都会被干掉 不对
		// 所以应该把cart放在session中 session没有就创建，有就直接取出
		Cart cart = getCart();
		
		cart.addCart(cartItem);
		
		return "addCart";
	}
	
	// 清空购物车的方法
	public String clearCart() {
		// 获得购物车的对象
		Cart cart = getCart();
		// 调用方法清空购物车
		cart.clearCart();
		return "clearCart";
	}
	
	// 从购物车中移除购物项方法
	public String removeCart() {
		// 获得购物车对象
		Cart cart = getCart();
		// 调用购物车删除方法
		cart.removeCart(pid);
		// 返回页面
		return "removeCart";
	}
	
	// 我的购物车执行的方法
	public String myCart() {
		return "myCart";
	}
	
	
	// 获得购物车的方法 ： 从session中获得购物车
	private Cart getCart(){
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		// 如果没有 就new一个并放入sessionn中
		if (cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		// 如果有
		return cart;
	}
}
