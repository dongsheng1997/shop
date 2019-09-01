package cn.itcast.shop.categorysecond.service;

import java.util.List;

import cn.itcast.shop.categorysecond.dao.CategorySecondDao;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.util.PageBean;


// ������������service
public class CategorySecondService {

	// ע��dao
	private CategorySecondDao categorySecondDao;
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}
	
	// ҵ����ҳ��ѯ��������ķ���
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʾ�ļ�¼��
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܼ�¼��
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// �����ܵ�ҳ��
		 int totalPage = 0;
		 if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		 pageBean.setTotalPage(totalPage);
		 // ����ÿҳ��ʾ���ݵļ���
		 int begin = (page - 1) * limit;
		 List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		 pageBean.setList(list);
		return pageBean;
	}

	// ҵ��㱣���������
	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	// ҵ������csid��ѯ��������
	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	// ҵ���ɾ����������
	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	// ҵ����޸Ķ�������
	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	// ҵ����ѯ���ж�������
	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}

	// ҵ������cid��ѯ��������
	public List<CategorySecond> findByCid(Integer cid) {
		return categorySecondDao.findByCid(cid);
	}
}
