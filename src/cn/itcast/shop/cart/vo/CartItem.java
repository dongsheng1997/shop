package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;

public class CartItem {
	private Product product; // 购物项的商品信息
	private int count; // 购买某种商品数量
	private double subtotal; // 购买某种商品小计
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public double getSubtotal() {
		return count * product.getShop_price();// 通过计算出购物项的小计
	}
	// 不需要设置set方法 因为可以直接可以计算出来
	/*public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}*/
	
}
