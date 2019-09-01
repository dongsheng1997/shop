package cn.itcast.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.util.PaymentUtil;
import cn.itcast.shop.util.UUIDUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	// 模型驱动 得到user对象
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	// 属性驱动 得到输入的验证码
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	// 接收支付通道编码
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	// 接收付款成功后的响应数据
	private String r6_Order; //订单id
	private String r3_Amt;// 付款金额
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// 注入userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * 跳转到注册页面的执行方法
	 */
	public String registPage() {
		return "registPage";
	}

	/*
	 * ajax进行异步校验用户名的执行方法
	 */
	public String findByName() throws IOException {
		// 调用service进行查询
		User exitUser = userService.findByUsername(user.getUsername());

		// 获得response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); // 设置字符编码
		if (exitUser != null) {
			// 查询到了，用户名已存在
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		} else {
			// 没有查到，可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	// 用户注册的方法
	public String regist() {
		// 判断验证码，从session获取的图片里的随机值
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) { // equalsIgnoreCase忽略大小写
			this.addActionError("您的验证码输入错误!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功!请去邮箱激活");
		return "msg";
	}

	// 用户激活的方法
	public String active() {
		// 根据激活码查询用户
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			// 激活码错误
			this.addActionMessage("激活码失败：激活码错误!");
		} else {
			// 激活成功
			// 修改用户的状态
			existUser.setState(1);
			existUser.setCode(null);// 保证一个激活码激活一个
			userService.update(existUser); // 修改用户
			this.addActionMessage("激活成功:请去登录");
		}
		return "msg";
	}

	// 跳转到用户登录界面
	public String loginPage() {

		return "loginPage";
	}

	// 点击用户登录方法
	public String login() {
		// 模型驱动自动接收
		System.out.println("进入login方法");
		User existUser = userService.login(user);

		if (existUser == null) {
			// 登陆失败
			this.addActionError("登陆失败:用户名错误或密码错误或用户未激活!"); // 页面回显的错误信息
			return LOGIN; // 登陆失败 再次跳转到登陆界面
		} else {
			// 登陆成功
			// 将用户信息存入session中
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			// 页面跳转
			return "loginSuccess";

		}
	}
	
	// 用户退出的方法
	public String quit() {
		// 销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	// 跳转到会员中心
	public String associator() {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			// 未登陆
			this.addActionError("亲!你还没有登陆!请去登录!");
			return "login";
		} else {
			// 已登录
			return "associator";

		}
	}
	
	// 为会员付款的方法
	public String payAssociator() throws IOException {
		System.out.println(user.getUid()+"===============================");
		// 为订单付款
		String p0_Cmd = "Buy";//业务类型
		String p1_MerId = "10001126856"; //商户编号
		String p2_Order = user.getUid().toString(); // 商品订单号
		String p3_Amt = "0.01";// 付款金额
		String p4_Cur = "CNY"; // 交易的币种
		String p5_Pid = "";//商品名称
		String p6_Pcat = ""; //商品种类
		String p7_Pdesc = "";//商品描述
		String p8_Url = "http://localhost/shop/user_callBack.action";// 支付成功后跳转的页面
		String p9_SAF = ""; // 送货地址
		String pa_MP = "";//商户扩展信息
		String pd_FrpId = this.pd_FrpId; // 支付通道编码
		String pr_NeedResponse = "1";// 应答机制
		
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//签名数据
		// 向易宝出发
		StringBuffer stringBuffer = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		stringBuffer.append("p0_Cmd=").append(p0_Cmd).append("&");
		stringBuffer.append("p1_MerId=").append(p1_MerId).append("&");
		stringBuffer.append("p2_Order=").append(p2_Order).append("&");
		stringBuffer.append("p3_Amt=").append(p3_Amt).append("&");
		stringBuffer.append("p4_Cur=").append(p4_Cur).append("&");
		stringBuffer.append("p5_Pid=").append(p5_Pid).append("&");
		stringBuffer.append("p6_Pcat=").append(p6_Pcat).append("&");
		stringBuffer.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		stringBuffer.append("p8_Url=").append(p8_Url).append("&");
		stringBuffer.append("p9_SAF=").append(p9_SAF).append("&");
		stringBuffer.append("pa_MP=").append(pa_MP).append("&");
		stringBuffer.append("pd_FrpId=").append(pd_FrpId).append("&");
		stringBuffer.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		stringBuffer.append("hmac=").append(hmac);
		
		// 不同服务器，需要重定向到易宝
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	// 付款成功后的转向
	public String callBack() {
		// 修改订单状态，修改状态为已经付款
		User currUser = userService.findByUid(Integer.parseInt(r6_Order));
		currUser.setLevel(1);
		userService.update(currUser);
		// 在页面显示付款成功信息
		this.addActionMessage("订单付款成功：订单编号:"+r6_Order+"付款金额："+r3_Amt);
		return "msg";
	}
	
	// 关于我们
	public String about() {
		return "about";
	}
}
