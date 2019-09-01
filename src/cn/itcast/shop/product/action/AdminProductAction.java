package cn.itcast.shop.product.action;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.categorysecond.service.CategorySecondService;
import cn.itcast.shop.categorysecond.vo.CategorySecond;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

// 后台商品管理action
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	// 模型驱动 商品
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	// 接收page参数
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPage() {
		return page;
	}
	
	// 接收pagefilter参数
	private Integer pagefilter;
	public void setPagefilter(Integer pagefilter) {
		this.pagefilter = pagefilter;
	}
	
	// 接收筛选内容
	private String content;
	public void setContent(String content) {
		System.out.println(content+"----------------------------------");
		this.content = content;
	}
	public String getContent() {
		return content;
	} 
	
	// 接收hot参数
	private Integer hot;
	public void setHot(Integer hot) {
		this.hot = hot;
	}

	// 接收筛选内容
	private String content2;
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		System.out.println(content2+"----------------------------------");
		this.content2 = content2;
	}

	// 文件上传需要的参数
	private File upload;// 上传的文件
	private String uploadFileName;// 接收文件上传的文件名
	private String uploadContextType;// 接收文件上传的文件的MIME的类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// 注入service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// 注入二级分类service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// 注入商品service
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// 后台查询所有商品
	public String findAll() {
		// 调用service完成查询操作
		PageBean<Product> pageBean = productService.findByPage(page);
		// 将数据显示到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 页面跳转
		return "findAll";
	}
	
	// 跳转到添加页面
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	// 保存商品的方法
	public String save() {
		// 调用service完成操作
		product.setPdate(new Date());
		if (upload != null) { // 上传的文件
			// 获得文件上传的((磁盘绝对路径))
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 
			System.out.println(realPath);
			// 创建一个文件
			File diskFile = new File(realPath +"//"+ uploadFileName);
			
			System.out.println(diskFile.getPath()+"---------------------------------------------------------------------");
			// 文件上传
			System.out.println();
			try {
				FileUtils.copyFile(upload, diskFile);
				/*File file = new File(System.getProperty("user.dir")+"/WebRoot/products/"+uploadFileName); 
				System.out.println(file.getPath()+"=============================");*/
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			product.setImage("products/"+uploadFileName);
		}
		// 保存数据
		productService.save(product);
		return "saveSuccess";
	}
	
	// 删除商品
	public String delete() {
		ServletActionContext.getRequest().getSession().setAttribute("fail", null);

		// 从订单项里查询是否有这个商品
		List<OrderItem> list = orderService.findByPid(product.getPid());
		if (list != null && list.size()>0) {
//			ActionContext.getContext().getValueStack().set("fail", "对不起!您的商品，已经加入订单中!不能删除!");
			ServletActionContext.getRequest().getSession().setAttribute("fail", "对不起!您的商品，已经加入订单中!不能删除!");
			System.out.println("对不起!您的商品，已经加入订单中!不能删除!");
			return "deleteFail";
		}

		// 根据pid查询商品
		product = productService.findByPid(product.getPid());
		// 删除商品图片
		String path = product.getImage();// 路径
		if (path != null) {
			// 获得文件上传的((磁盘绝对路径))
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath); // 创建一个文件
			file.delete();// 删除这个文件
		}
		
		// 删除商品
		productService.delete(product);
		return "deleteSuccess";
	}
	
	// 编辑商品
	public String edit() {
		//System.out.println(page+"page edit++++++++++++++++++++++++");
		System.out.println(content+"content edit+++++++++++++++++");
		// 根据pid查询商品
		product = productService.findByPid(product.getPid());
		// 查询所有二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		// 将数据保存到页面
		ActionContext.getContext().getValueStack().set("csList", csList);
		// 页面跳转
		return "editSuccess";
	}
	
	// 修改商品方法
	public String update() {
		//System.out.println(page+"update++++++++++++++++++++++++++++++");
		System.out.println(content+"content update+++++++++++++++++");
		// 修改时间
		product.setPdate(new Date());
		// 文件上传
		if (upload != null) { // 上传的文件
			// 删除原有的图片
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			// 获得文件上传的((磁盘绝对路径))
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			System.out.println(realPath);
			// 创建本地一个文件
			File diskFile = new File(realPath +"//"+ uploadFileName);
			// 文件上传
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			product.setImage("products/"+uploadFileName);
		}
		// 修改商品数据到数据库
		productService.update(product);
		if (content.equals("") || content == null) {
			return "updateSuccess";
		}else {
			System.out.println("执行过滤");
			System.out.println(content+"filter=============");
			return "updateFilterSuccess";
		}
		
	}
	
	// 根据条件来筛选商品
	public String filter() {
		// 调用service完成查询操作
		
		System.out.println(content+"filter =============");
		System.out.println(pagefilter+"filter ===============");
		System.out.println(page+"filter ===============");
		
		System.out.println(content2+"---content2 =============");
		System.out.println(hot+"---hot ===============");
		
		PageBean<Product> pageBean = productService.findByPageFiler(pagefilter,content,Integer.parseInt(content2));
		// 将数据显示到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// 设置content
		ActionContext.getContext().getValueStack().set("content", content);
		// 设置content2
		ActionContext.getContext().getValueStack().set("content2", content2);
		// 页面跳转
		return "findAllFilter";
	}
}
