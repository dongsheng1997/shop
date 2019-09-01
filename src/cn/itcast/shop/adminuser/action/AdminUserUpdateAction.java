package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserUpdateAction extends ActionSupport implements ModelDriven<AdminUser>{

	// 模型驱动 用户
	private AdminUser adminUser = new AdminUser();
	public AdminUser getModel() {
		System.out.println("模型驱动"+adminUser);
		return adminUser;
	}
	
	// 注入service
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	// 编辑密码
	public String editPassword() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		return "editPasswordSuccess";
	}
	
	// 修改密码
	public String updatePassword() {
		adminUserService.update(adminUser);
		return "updatePasswordSuccess";
	}
	
	// 退出系统
	public String quit() {
		// 销毁session
		ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", null);
		return "quitSuccess";
	}
}
