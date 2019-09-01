package cn.itcast.shop.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// 邮件发送工具类
public class MailUtils {
	// 方法 收件人 激活码
	public static void sendMail(String to,String code) {
		// 1.获得一个session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@shop.com", "123456");// 你要用谁发邮件
			}
			
		});
		// 2.创建一个代表邮件的对象Message
		Message message = new MimeMessage(session);
		
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("service@shop.com"));
			// 设置收件人
			// CC 抄送
			// BCC 密送
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 标题
			message.setSubject("来自天堂购物达内商城官方激活邮件");
			// 设置邮件主题
			message.setContent("<h1>天堂购物达内商城官方激活邮件！点下面链接</h1><h3><a href='http://192.168.43.152/shop"
					+ "/user_active.action?code="+code+"'>http://localhost/shop/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
			// 3.发送邮件Transport
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*public static void main(String[] args) {
		sendMail("aaa@shop.com", "1111");
	}*/
}
