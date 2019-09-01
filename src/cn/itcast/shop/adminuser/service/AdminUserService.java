package cn.itcast.shop.adminuser.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.adminuser.dao.AdminUserDao;
import cn.itcast.shop.adminuser.vo.AdminUser;

// 后台管理员的业务层
@Transactional
public class AdminUserService {

	// 注入dao
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	// 业务层登陆的方法
	public AdminUser login(AdminUser adminUser) {
		
		return adminUserDao.login(adminUser);
	}

	// 业务层查询所有用户
	public List<AdminUser> findAll() {
		return adminUserDao.findAll();
	}

	// 业务层保存用户
	public void save(AdminUser adminUser) {
		adminUserDao.save(adminUser);
	}

	// 查询用户通过uid
	public AdminUser findByUid(Integer uid) {
		return adminUserDao.findByUid(uid);
	}

	// 业务层修改用户
	public void update(AdminUser adminUser) {
		adminUserDao.update(adminUser);
	}

	// 业务层删除用户
	public void delete(AdminUser adminUser) {
		adminUserDao.delete(adminUser);
	}

	
}
