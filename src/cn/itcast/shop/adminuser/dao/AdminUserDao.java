package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.category.vo.Category;

// 后台管理员的持久层
public class AdminUserDao extends HibernateDaoSupport{

	//持久层登录方法
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	// dao查询所有用户
	public List<AdminUser> findAll() {
		String hql = "from AdminUser";
		List<AdminUser> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// dao保存用户
	public void save(AdminUser adminUser) {
		this.getHibernateTemplate().save(adminUser);
	}

	// 查询用户通过uid
	public AdminUser findByUid(Integer uid) {
		AdminUser adminUser = this.getHibernateTemplate().get(AdminUser.class, uid);
		return adminUser;
	}

	// 修改用户
	public void update(AdminUser adminUser) {
		this.getHibernateTemplate().update(adminUser);
	}

	// 删除用户
	public void delete(AdminUser adminUser) {
		this.getHibernateTemplate().delete(adminUser);
	}

}
