package cn.itcast.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;


// ���ﳵ����
public class Cart implements Serializable{
	// ������ϣ�map��key������Ʒpid��value���ǹ�����
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	// Cart�������и�cartItems����
	public Collection<CartItem> getCartItems() {
		return map.values();// �õ�map��values��������ɵ��У�Ϊ���Ƿ������
	}
	
	// �����ܼ�
	private double total;
	public double getTotal() {
		return total;
	}
	
	// ���ﳵ�Ĺ���
	// 1.�����ﳵ��ӵ����ﳵ
	public void addCart(CartItem cartItem){
		// �жϹ��ﳵ�Ƿ��Ѿ����ڸù�����
		// 	����
		//		��������
		//      �ܼ� = �ܼ� + ������С��
		//	������
		//		��map����ӹ�����
		//      �ܼ� = �ܼ� + ������С�� 
		
		//�����Ʒid
		Integer pid = cartItem.getProduct().getPid();
		// �Ƿ���ڹ�����
		if (map.containsKey(pid)) {// Map���Ƿ����pid��key
			// ����	
			CartItem _cartItem = map.get(pid); // ���ԭ���Ĺ�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());// ���ù��������Ŀ
		}else {
			// ������
			map.put(pid, cartItem);// ���ﳵ��ӹ�����
		}
		// �����ܼƵ�ֵ
		total += cartItem.getSubtotal(); //�ܼƼ���(��ӹ������)С��
	}
	
	// 2.�ӹ��ﳵ�Ƴ�������
	public void removeCart(Integer pid) {
		// ���������Ƴ����ﳵ
		CartItem cartItem = map.remove(pid);
		// �ܼ� = �ܼ�-�Ƴ�������С��
		total -= cartItem.getSubtotal();
	}
	
	// 3.��չ��ﳵ
	public void clearCart(){
		// �����й�����
		map.clear();
		// ���ܼ�����Ϊ0
		total = 0;
	}
	
}
