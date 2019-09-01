package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.user.vo.User;

// �û�ģ��־ò����
public class UserDao extends HibernateDaoSupport{
	
	//�����Ʋ�ѯ�Ƿ��и��û���
	public User findByUsername(String username) {
		String hql="from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if (list != null && list.size() > 0) {
			return list.get(0); // ����user����
		}
		return null;
	}

	// ע���û��������ݿ����ʵ��
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	// �־ò���ݼ������ѯ�û�
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if (list != null && list.size() > 0) {
			return list.get(0); // ����user����
		}
		return null;
	}

	// �޸��û�״̬
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	// �û���¼�ķ���
	public User login(User user) {
		String hql = "from User where username=? and password=? and state=?";
		List<User>list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if (list != null && list.size() > 0) {
			return list.get(0); // ����user����
		}
		return null;
	}

	// dao������û�id��ѯ�û�
	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}
	
}
