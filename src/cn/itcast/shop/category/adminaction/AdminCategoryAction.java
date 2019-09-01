package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 后台一级分类action
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	// 模型驱动 一级分类
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	// 注入service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 注入二级分类service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// 后台执行查询所有的一级分类方法
	public String findAll() {
		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		// 将集合数据显示到页面
		ActionContext.getContext().getValueStack().set("cList", cList);// 值栈是集合
		return "findAll";
	}
	
	// 后台保存一级分类的方法
	public String save() {
		// 调用service保存
		categoryService.save(category);// category中就有cname的值
		// 页面的跳转
		return "saveSuccess";
	}
	
	// 后台删除一级分类的方法
	public String delete() {
		// 接收cid，可以使用模型驱动，删除一级分类，同时删除二级分类，必须先根据id查询 在进行删除
		category = categoryService.findByCid(category.getCid());
		// 删除一级分类
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	// 后台修改一级分类方法
	public String edit() {
		// 接收cid，可以使用模型驱动,查询一级分类
		category = categoryService.findByCid(category.getCid());// 已经存入模型驱动里面
		// 关联二级分类
		List<CategorySecond> csList = categorySecondService.findByCid(category.getCid());
		// 页面跳转
		return "editSuccess";
	}
	
	// 修改一级分类
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
