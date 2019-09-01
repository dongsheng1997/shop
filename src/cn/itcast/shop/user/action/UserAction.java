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

	// ģ������ �õ�user����
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	// �������� �õ��������֤��
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	// ����֧��ͨ������
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	// ���ո���ɹ������Ӧ����
	private String r6_Order; //����id
	private String r3_Amt;// ������
	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}
	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	// ע��userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/*
	 * ��ת��ע��ҳ���ִ�з���
	 */
	public String registPage() {
		return "registPage";
	}

	/*
	 * ajax�����첽У���û�����ִ�з���
	 */
	public String findByName() throws IOException {
		// ����service���в�ѯ
		User exitUser = userService.findByUsername(user.getUsername());

		// ���response������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8"); // �����ַ�����
		if (exitUser != null) {
			// ��ѯ���ˣ��û����Ѵ���
			response.getWriter().println("<font color='red'>�û����Ѵ���</font>");
		} else {
			// û�в鵽������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	// �û�ע��ķ���
	public String regist() {
		// �ж���֤�룬��session��ȡ��ͼƬ������ֵ
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if (!checkcode.equalsIgnoreCase(checkcode1)) { // equalsIgnoreCase���Դ�Сд
			this.addActionError("������֤���������!");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�!��ȥ���伤��");
		return "msg";
	}

	// �û�����ķ���
	public String active() {
		// ���ݼ������ѯ�û�
		User existUser = userService.findByCode(user.getCode());
		if (existUser == null) {
			// ���������
			this.addActionMessage("������ʧ�ܣ����������!");
		} else {
			// ����ɹ�
			// �޸��û���״̬
			existUser.setState(1);
			existUser.setCode(null);// ��֤һ�������뼤��һ��
			userService.update(existUser); // �޸��û�
			this.addActionMessage("����ɹ�:��ȥ��¼");
		}
		return "msg";
	}

	// ��ת���û���¼����
	public String loginPage() {

		return "loginPage";
	}

	// ����û���¼����
	public String login() {
		// ģ�������Զ�����
		System.out.println("����login����");
		User existUser = userService.login(user);

		if (existUser == null) {
			// ��½ʧ��
			this.addActionError("��½ʧ��:�û�����������������û�δ����!"); // ҳ����ԵĴ�����Ϣ
			return LOGIN; // ��½ʧ�� �ٴ���ת����½����
		} else {
			// ��½�ɹ�
			// ���û���Ϣ����session��
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			// ҳ����ת
			return "loginSuccess";

		}
	}
	
	// �û��˳��ķ���
	public String quit() {
		// ����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
	
	// ��ת����Ա����
	public String associator() {
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			// δ��½
			this.addActionError("��!�㻹û�е�½!��ȥ��¼!");
			return "login";
		} else {
			// �ѵ�¼
			return "associator";

		}
	}
	
	// Ϊ��Ա����ķ���
	public String payAssociator() throws IOException {
		System.out.println(user.getUid()+"===============================");
		// Ϊ��������
		String p0_Cmd = "Buy";//ҵ������
		String p1_MerId = "10001126856"; //�̻����
		String p2_Order = user.getUid().toString(); // ��Ʒ������
		String p3_Amt = "0.01";// ������
		String p4_Cur = "CNY"; // ���׵ı���
		String p5_Pid = "";//��Ʒ����
		String p6_Pcat = ""; //��Ʒ����
		String p7_Pdesc = "";//��Ʒ����
		String p8_Url = "http://localhost/shop/user_callBack.action";// ֧���ɹ�����ת��ҳ��
		String p9_SAF = ""; // �ͻ���ַ
		String pa_MP = "";//�̻���չ��Ϣ
		String pd_FrpId = this.pd_FrpId; // ֧��ͨ������
		String pr_NeedResponse = "1";// Ӧ�����
		
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);//ǩ������
		// ���ױ�����
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
		
		// ��ͬ����������Ҫ�ض����ױ�
		ServletActionContext.getResponse().sendRedirect(stringBuffer.toString());
		
		return NONE;
	}
	
	// ����ɹ����ת��
	public String callBack() {
		// �޸Ķ���״̬���޸�״̬Ϊ�Ѿ�����
		User currUser = userService.findByUid(Integer.parseInt(r6_Order));
		currUser.setLevel(1);
		userService.update(currUser);
		// ��ҳ����ʾ����ɹ���Ϣ
		this.addActionMessage("��������ɹ����������:"+r6_Order+"�����"+r3_Amt);
		return "msg";
	}
	
	// ��������
	public String about() {
		return "about";
	}
}
