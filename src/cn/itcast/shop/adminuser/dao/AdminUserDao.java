package cn.itcast.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.adminuser.vo.AdminUser;
import cn.itcast.shop.category.vo.Category;

// ��̨����Ա�ĳ־ò�
public class AdminUserDao extends HibernateDaoSupport{

	//�־ò��¼����
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,adminUser.getUsername(),adminUser.getPassword());
		if (list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	// dao��ѯ�����û�
	public List<AdminUser> findAll() {
		String hql = "from AdminUser";
		List<AdminUser> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size()>0) {
			return list;
		}
		return null;
	}

	// dao�����û�
	public void save(AdminUser adminUser) {
		this.getHibernateTemplate().save(adminUser);
	}

	// ��ѯ�û�ͨ��uid
	public AdminUser findByUid(Integer uid) {
		AdminUser adminUser = this.getHibernateTemplate().get(AdminUser.class, uid);
		return adminUser;
	}

	// �޸��û�
	public void update(AdminUser adminUser) {
		this.getHibernateTemplate().update(adminUser);
	}

	// ɾ���û�
	public void delete(AdminUser adminUser) {
		this.getHibernateTemplate().delete(adminUser);
	}

}
