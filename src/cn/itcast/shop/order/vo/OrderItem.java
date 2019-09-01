package cn.itcast.shop.order.vo;

import cn.itcast.shop.product.vo.Product;

// 订单项实体类
public class OrderItem {

	private Integer itemid;
	private Integer count;// 数量
	private Double subtotal;// 小计
	private Product product;// 商品
	private Order order;// 订单
	
	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
