package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


// 购物车对象
public class Cart implements Serializable{
	// 购物项集合，map的key就是商品pid，value就是购物项
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	// Cart对象中有个cartItems属性
	public Collection<CartItem> getCartItems() {
		return map.values();// 得到map的values，把它变成单列，为了是方便遍历
	}
	
	// 购物总计
	private double total;
	public double getTotal() {
		return total;
	}
	
	// 购物车的功能
	// 1.将购物车添加到购物车
	public void addCart(CartItem cartItem){
		// 判断购物车是否已经存在该购物项
		// 	存在
		//		数量增加
		//      总计 = 总计 + 购物项小计
		//	不存在
		//		向map中添加购物项
		//      总计 = 总计 + 购物项小计 
		
		//获得商品id
		Integer pid = cartItem.getProduct().getPid();
		// 是否存在购物项
		if (map.containsKey(pid)) {// Map中是否包含pid的key
			// 存在	
			CartItem _cartItem = map.get(pid); // 获得原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());// 设置购物项的数目
		}else {
			// 不存在
			map.put(pid, cartItem);// 向购物车添加购物项
		}
		// 设置总计的值
		total += cartItem.getSubtotal(); //总计加上(添加购物项的)小计
	}
	
	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计-移除购物项小计
		total -= cartItem.getSubtotal();
	}
	
	// 3.清空购物车
	public void clearCart(){
		// 将所有购物项
		map.clear();
		// 将总计设置为0
		total = 0;
	}
	
}
