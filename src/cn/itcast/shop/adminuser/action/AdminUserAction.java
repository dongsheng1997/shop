package cn.itcast.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;
import cn.itcast.shop.adminuser.service.AdminUserService;
import cn.itcast.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// ��̨����Ա��action
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

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
	
	// ��̨��½�ķ���
	public String login() {
		// ����service��ɵ�½
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			// ��½ʧ��
			this.addActionError("�ף������û������������");
			return "loginFail";
		}else {
			// ��½�ɹ�
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	
	/*// ��ѯ���û�
	public String findAll() {
		// ��ѯ���е�һ������
		List<AdminUser> cList = adminUserService.findAll();
		// ������������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("cList", cList);// ֵջ�Ǽ���
		return "findAll";
	}
	
	// �����û�
	public String save() {
		adminUserService.save(adminUser);
		return "addSuccess";
	}
	
	// �༭�û�
	public String edit() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		System.out.println("�־ò�"+adminUser);
		return "editSuccess";
	}
	
	// �޸��û�
	public String update() {
		adminUserService.update(adminUser);
		return "updateSuccess";
	}
	
	// ɾ���û�
	public String delete() {
		adminUser = adminUserService.findByUid(adminUser.getUid());
		adminUserService.delete(adminUser);
		return "deleteSuccess";
	}*/
	
/*	// �༭����
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
	}*/
}
