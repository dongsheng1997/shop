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

// ��̨������������action
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	// ģ������
	private CategorySecond categorySecond = new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	// ע��service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// ע��һ��service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ����page
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	
	// ��ѯ�������෽��
	public String findAll() {
		// ��ҳ
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		// ��pageBean�����ݱ��浽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	// ��ת�����ҳ��
	public String addPage() {
		// ��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();
		// ��������ʾ��ҳ��������б���
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "addPageSuccess";
	}
	
	// �����������
	public String save() {
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	// ɾ����������ķ���
	public String delete() {
		// �������ɾ����Ʒ���Ȳ�ѯ��ɾ��������cascade
		// �Ȳ�ѯ
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		// ��ɾ��
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	// �༭��������
	public String edit() {
		// ���ݶ�������id��ѯ��������
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());// csidע�뵽categorySecond��
		// ��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		// ��������ʾ��ҳ��������б���
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	// �޸Ķ�������
	public String update() {
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
	
}
