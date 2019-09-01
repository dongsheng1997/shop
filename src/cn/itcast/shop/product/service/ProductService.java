package cn.itcast.shop.product.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

// ��Ʒҵ���
@Transactional  //�޸ġ�ɾ������Ӳ�����Ҫ����
public class ProductService {
	// ע��ProductDao
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	// ��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	// ������ƷId��ѯ��Ʒ
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	// ����һ�������cid���з�ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��
		int limit = 8;
		pageBean.setLimit(limit);
		// �����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�ļ���
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ���ݶ��������ѯ��Ʒ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��
		int limit = 8;
		pageBean.setLimit(limit);
		// �����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ÿҳ��ʾ�ļ���
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// ҵ����ѯ��Ʒ����ҳ��
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʾ������
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܵļ�¼��
		int totalCount;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// �����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ�ļ���
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	// ҵ��㱣����Ʒ
	public void save(Product product) {
		productDao.save(product);
	}

	// ҵ���ɾ����Ʒ
	public void delete(Product product) {
		
		productDao.delete(product);
	}

	// ҵ����޸���Ʒ
	public void update(Product product) {
		productDao.update(product);
	}

	// ҵ������������ɸѡ��Ʒ
	public PageBean<Product> findByPageFiler(Integer page, String content,Integer content2) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// ���õ�ǰҳ��
		pageBean.setPage(page);
		// ����ÿҳ��ʾ������
		int limit = 10;
		pageBean.setLimit(limit);
		// �����ܵļ�¼��
		int totalCount;
		totalCount = productDao.findCountFilter(content,content2);
		System.out.println(totalCount+"---------------------------count---------------------------------");
		pageBean.setTotalCount(totalCount);
		// �����ܵ�ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ�ļ���
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPageFilter(begin,limit,content,content2);
		pageBean.setList(list);
		return pageBean;
	}
	
	// �޸�����
	public void updateNumber(List<OrderItem> list) {
		for(OrderItem orderItem:list){
			Product product = orderItem.getProduct();
			Integer number = product.getNumber();
			Integer count = orderItem.getCount();
			product.setNumber(number-count);
			productDao.updateNumber(product);
		}
	}
	
}
