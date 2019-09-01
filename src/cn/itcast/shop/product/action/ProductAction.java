package cn.itcast.shop.product.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	// ģ������ ��Ʒ
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	// �������� һ������id
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	
	// �������� ��ǰҳ��
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	
	// �������� ��������id
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCsid() {
		return csid;
	}

	// ע��ProductService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// ע��һ�������service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ������Ʒ��Id������Ʒ
	public String findByPid() {
		// ����Service������ɲ�ѯ
		product = productService.findByPid(product.getPid()); // �����½�����ģ������ ֱ����ջ��
		return "findByPid";
	}
	
	//���ݷ����id��ѯ��Ʒ
	public String findByCid() {
		// List<Category> list = categoryService.findAll();
		// ���Բ��ò�ѯ ��Ϊsession����category����
		
		// ����һ�������ѯ��Ʒ������ҳ
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); 
		// ��pagebean���뵽ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
	}
	
	//���ݶ��������ѯ��Ʒ
	public String findByCsid() {
		// ���ݶ��������ѯ��Ʒ
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		// ��pagebean���뵽ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
}
