package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;
import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 后台管理员的action
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

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
	
	// 后台登陆的方法
	public String login() {
		// 调用service完成登陆
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			// 登陆失败
			this.addActionError("亲，您的用户名或密码错误！");
			return "loginFail";
		}else {
			// 登陆成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	
	/*// 查询所用户
	public String findAll() {
		// 查询所有的一级分类
		List<AdminUser> cList = adminUserService.findAll();
		// 将集合数据显示到页面
		ActionContext.getContext().getValueStack().set("cList", cList);// 值栈是集合
		return "findAll";
	}
	
	// 保存用户
	public String save() {
		adminUserService.save(adminUser);
		return "addSuccess";
	}
	
	// 编辑用户
	public String edit() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		System.out.println("持久层"+adminUser);
		return "editSuccess";
	}
	
	// 修改用户
	public String update() {
		adminUserService.update(adminUser);
		return "updateSuccess";
	}
	
	// 删除用户
	public String delete() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		adminUserService.delete(adminUser);
		return "deleteSuccess";
	}*/
	
/*	// 编辑密码
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
	}*/
}
