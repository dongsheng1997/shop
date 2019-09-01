package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserUpdateAction extends ActionSupport implements ModelDriven<AdminUser>{

	// ģ������ �û�
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		System.out.println("ģ������"+adminUser);
		return adminUser;
	}
	
	// ע��service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// �༭����
	public String editPassword() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		return "editPasswordSuccess";
	}
	
	// �޸�����
	public String updatePassword() {
		adminUserService.update(adminUser);
		return "updatePasswordSuccess";
	}
	
	// �˳�ϵͳ
	public String quit() {
		// ����session
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", null);
		return "quitSuccess";
	}
}
