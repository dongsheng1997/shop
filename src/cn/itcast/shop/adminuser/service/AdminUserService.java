package cn.itcast.shop.adminuser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;

// ��̨����Ա��ҵ���
@Transactional
public class AdminUserService {

	// ע��dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	// ҵ����½�ķ���
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}

	// ҵ����ѯ�����û�
	public List<AdminUser> findAll() {
		return adminUserDao.findAll();
	}

	// ҵ��㱣���û�
	public void save(AdminUser adminUser) {
		adminUserDao.save(adminUser);
	}

	// ��ѯ�û�ͨ��uid
	public AdminUser findByUid(Integer uid) {
		return adminUserDao.findByUid(uid);
	}

	// ҵ����޸��û�
	public void update(AdminUser adminUser) {
		adminUserDao.update(adminUser);
	}

	// ҵ���ɾ���û�
	public void delete(AdminUser adminUser) {
		adminUserDao.delete(adminUser);
	}

	
}
