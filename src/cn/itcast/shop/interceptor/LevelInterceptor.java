package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LevelInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// �ж�session���Ƿ񱣴��̨�û�����Ϣ
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		int level = adminUser.getLevel();
		System.out.println(level);
		
		if (level == 0) {
			// û��Ȩ��
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();// ����������е�action
			actionSupport.addActionError("��!û��Ȩ�޷���!");
			return "noLevel";
		}else {
			// ��Ȩ��
			return actionInvocation.invoke();
		}
	}

}
