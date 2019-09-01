package cn.itcast.shop.product.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

// 商品业务层
@Transactional  //修改、删除、添加操作需要事务
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	// 根据商品Id查询商品
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	// 根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的集合
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据二级分类查询商品
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示记录数
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的集合
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层查询商品带分页的
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的条数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的集合
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}


	// 业务层保存商品
	public void save(Product product) {
		productDao.save(product);
	}

	// 业务层删除商品
	public void delete(Product product) {
		
		productDao.delete(product);
	}

	// 业务层修改商品
	public void update(Product product) {
		productDao.update(product);
	}

	// 业务层根据条件来筛选商品
	public PageBean<Product> findByPageFiler(Integer page, String content,Integer content2) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数
		pageBean.setPage(page);
		// 设置每页显示的条数
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总的记录数
		int totalCount;
		totalCount = productDao.findCountFilter(content,content2);
		System.out.println(totalCount+"---------------------------count---------------------------------");
		pageBean.setTotalCount(totalCount);
		// 设置总的页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			 totalPage = totalCount/limit;
		 }else {
			 totalPage = totalCount/limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示的集合
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPageFilter(begin,limit,content,content2);
		pageBean.setList(list);
		return pageBean;
	}
	
	// 修改数量
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
