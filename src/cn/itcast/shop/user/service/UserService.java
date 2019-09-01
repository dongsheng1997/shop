package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.util.MailUtils;
import cn.itcast.shop.util.UUIDUtils;

//�û�ģ��ҵ������
@Transactional //�������
public class UserService {
	// ע��userDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	// ���û�����ѯ�û�������
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// ҵ��㱣������
	public void save(User user) {
		// �����ݴ������ݿ�
		user.setState(0);// 0 ����δ���� 1������
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();// 64������
		user.setCode(code);
		userDao.save(user);
		
		// ���ͼ����ļ�
		MailUtils.sendMail(user.getEmail(), code);
	}

	// ҵ�����ݼ������ѯ�û�
	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	// �޸��û�״̬
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// �û���¼����
	public User login(User user) {
		
		return userDao.login(user);
	}

	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}
}
