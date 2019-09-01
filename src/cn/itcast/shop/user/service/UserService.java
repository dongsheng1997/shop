package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.util.MailUtils;
import cn.itcast.shop.util.UUIDUtils;

//用户模块业务层代码
@Transactional //事务管理
public class UserService {
	// 注入userDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// 按用户名查询用户方法：
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 业务层保存数据
	public void save(User user) {
		// 将数据存入数据库
		user.setState(0);// 0 代表未激活 1代表激活
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();// 64个长度
		user.setCode(code);
		userDao.save(user);
		
		// 发送激活文件
		MailUtils.sendMail(user.getEmail(), code);
	}

	// 业务层根据激活码查询用户
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	// 修改用户状态
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 用户登录方法
	public User login(User user) {
		
		return userDao.login(user);
	}

	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
}
