package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LevelInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断session中是否保存后台用户的信息
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		int level = adminUser.getLevel();
		System.out.println(level);
		
		if (level == 0) {
			// 没有权限
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();// 获得正在运行的action
			actionSupport.addActionError("亲!没有权限访问!");
			return "noLevel";
		}else {
			// 有权限
			return actionInvocation.invoke();
		}
	}

}
