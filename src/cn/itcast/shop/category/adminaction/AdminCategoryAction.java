package cn.itcast.shop.category.adminaction;

import java.util.List;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// ��̨һ������action
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	// ģ������ һ������
	private Category category = new Category();
	public Category getModel() {
		return category;
	}
	
	// ע��service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	// ע���������service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// ��ִ̨�в�ѯ���е�һ�����෽��
	public String findAll() {
		// ��ѯ���е�һ������
		List<Category> cList = categoryService.findAll();
		// ������������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("cList", cList);// ֵջ�Ǽ���
		return "findAll";
	}
	
	// ��̨����һ������ķ���
	public String save() {
		// ����service����
		categoryService.save(category);// category�о���cname��ֵ
		// ҳ�����ת
		return "saveSuccess";
	}
	
	// ��̨ɾ��һ������ķ���
	public String delete() {
		// ����cid������ʹ��ģ��������ɾ��һ�����࣬ͬʱɾ���������࣬�����ȸ���id��ѯ �ڽ���ɾ��
		category = categoryService.findByCid(category.getCid());
		// ɾ��һ������
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	// ��̨�޸�һ�����෽��
	public String edit() {
		// ����cid������ʹ��ģ������,��ѯһ������
		category = categoryService.findByCid(category.getCid());// �Ѿ�����ģ����������
		// ������������
		List<CategorySecond> csList = categorySecondService.findByCid(category.getCid());
		// ҳ����ת
		return "editSuccess";
	}
	
	// �޸�һ������
	public String update() {
		categoryService.update(category);
		return "updateSuccess";
	}
}
