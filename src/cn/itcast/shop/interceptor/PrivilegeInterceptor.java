package cn.itcast.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

// 后台权限校验的拦截器
// 对没有登陆的用户是不可以访问的
public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	// 执行拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断session中是否保存后台用户的信息
		AdminUser adminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (adminUser == null) {
			// 没有登陆
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();// 获得正在运行的action
			actionSupport.addActionError("亲!您还没有登陆!没有权限访问!");
			return "loginFail";
		}else {
			// 登陆过
			return actionInvocation.invoke();
		}
	}

}
