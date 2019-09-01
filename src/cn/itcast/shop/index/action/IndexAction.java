package cn.itcast.shop.index.action;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport{
	// ע��һ������Service Ϊ�˲�ѯһ������
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ע����ƷService Ϊ�˲�ѯ��Ʒ
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	
	// ִ�еķ�����ҳ�ķ���
	public String execute() {
		// ��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		// ��һ���������session��Χ,Ϊ�˶�ȡ
		ActionContext.getContext().getSession().put("cList", cList);
		
		// ��ѯ������Ʒ
		List<Product> hList = productService.findHot();
		// ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		// ��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		// ���浽ֵջ��
		ActionContext.getContext().getValueStack().set("nList", nList);;
		return "index";
	}
	
	
}
