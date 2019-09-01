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
	// 模型驱动 商品
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	// 属性驱动 一级缓存id
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	
	// 属性驱动 当前页数
	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	
	// 属性驱动 二级分类id
	private Integer csid;
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public Integer getCsid() {
		return csid;
	}

	// 注入ProductService
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// 注入一级分类的service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 根据商品的Id查找商品
	public String findByPid() {
		// 调用Service方法完成查询
		product = productService.findByPid(product.getPid()); // 不用新建对象，模型驱动 直接在栈顶
		return "findByPid";
	}
	
	//根据分类的id查询商品
	public String findByCid() {
		// List<Category> list = categoryService.findAll();
		// 可以不用查询 因为session里有category集合
		
		// 根据一级分类查询商品，带分页
		PageBean<Product> pageBean = productService.findByPageCid(cid,page); 
		// 将pagebean存入到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findByCid";
	}
	
	//根据二级分类查询商品
	public String findByCsid() {
		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		// 将pagebean存入到值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
	
}
