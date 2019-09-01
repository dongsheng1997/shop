package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

// ��̨Ȩ��У���������
// ��û�е�½���û��ǲ����Է��ʵ�
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	// ִ�����صķ���
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// �ж�session���Ƿ񱣴��̨�û�����Ϣ
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (adminUser == null) {
			// û�е�½
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();// ����������е�action
			actionSupport.addActionError("��!����û�е�½!û��Ȩ�޷���!");
			return "loginFail";
		}else {
			// ��½��
			return actionInvocation.invoke();
		}
	}

}
