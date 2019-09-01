package cn.itcast.shop.categorysecond.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 后台管理二级分类的action
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	// 模型驱动
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	// 注入service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// 注入一级service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// 接收page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	// 查询二级分类方法
	public String findAll() {
		// 分页
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// 将pageBean的数据保存到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	// 跳转到添加页面
	public String addPage() {
		// 查询所有的一级分类
		List<Category> cList = categoryService.findAll();
		// 把数据显示到页面的下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	// 保存二级分类
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	// 删除二级分类的方法
	public String delete() {
		// 如果级联删除商品，先查询再删除，配置cascade
		// 先查询
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// 再删除
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	// 编辑二级分类
	public String edit() {
		// 根据二级分类id查询二级分类
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());// csid注入到categorySecond中
		// 查询所有一级分类
		List<Category> cList = categoryService.findAll();
		// 把数据显示到页面的下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	// 修改二级分类
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	
}
