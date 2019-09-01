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

// ��̨��Ʒ����action
public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{

	// ģ������ ��Ʒ
	private Product product = new Product();
	public Product getModel() {
		return product;
	}
	
	// ����page����
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPage() {
		return page;
	}
	
	// ����pagefilter����
	private Integer pagefilter;
	public void setPagefilter(Integer pagefilter) {
		this.pagefilter = pagefilter;
	}
	
	// ����ɸѡ����
	private String content;
	public void setContent(String content) {
		System.out.println(content+"----------------------------------");
		this.content = content;
	}
	public String getContent() {
		return content;
	} 
	
	// ����hot����
	private Integer hot;
	public void setHot(Integer hot) {
		this.hot = hot;
	}

	// ����ɸѡ����
	private String content2;
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		System.out.println(content2+"----------------------------------");
		this.content2 = content2;
	}

	// �ļ��ϴ���Ҫ�Ĳ���
	private File upload;// �ϴ����ļ�
	private String uploadFileName;// �����ļ��ϴ����ļ���
	private String uploadContextType;// �����ļ��ϴ����ļ���MIME������
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}

	// ע��service
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	// ע���������service
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	// ע����Ʒservice
	private OrderService orderService;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// ��̨��ѯ������Ʒ
	public String findAll() {
		// ����service��ɲ�ѯ����
		PageBean<Product> pageBean = productService.findByPage(page);
		// ��������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// ҳ����ת
		return "findAll";
	}
	
	// ��ת�����ҳ��
	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	
	// ������Ʒ�ķ���
	public String save() {
		// ����service��ɲ���
		product.setPdate(new Date());
		if (upload != null) { // �ϴ����ļ�
			// ����ļ��ϴ���((���̾���·��))
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			// 
			System.out.println(realPath);
			// ����һ���ļ�
			File diskFile = new File(realPath +"//"+ uploadFileName);
			
			System.out.println(diskFile.getPath()+"---------------------------------------------------------------------");
			// �ļ��ϴ�
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
		// ��������
		productService.save(product);
		return "saveSuccess";
	}
	
	// ɾ����Ʒ
	public String delete() {
		ServletActionContext.getRequest().getSession().setAttribute("fail", null);

		// �Ӷ��������ѯ�Ƿ��������Ʒ
		List<OrderItem> list = orderService.findByPid(product.getPid());
		if (list != null && list.size()>0) {
//			ActionContext.getContext().getValueStack().set("fail", "�Բ���!������Ʒ���Ѿ����붩����!����ɾ��!");
			ServletActionContext.getRequest().getSession().setAttribute("fail", "�Բ���!������Ʒ���Ѿ����붩����!����ɾ��!");
			System.out.println("�Բ���!������Ʒ���Ѿ����붩����!����ɾ��!");
			return "deleteFail";
		}

		// ����pid��ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		// ɾ����ƷͼƬ
		String path = product.getImage();// ·��
		if (path != null) {
			// ����ļ��ϴ���((���̾���·��))
			String realPath = ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(realPath); // ����һ���ļ�
			file.delete();// ɾ������ļ�
		}
		
		// ɾ����Ʒ
		productService.delete(product);
		return "deleteSuccess";
	}
	
	// �༭��Ʒ
	public String edit() {
		//System.out.println(page+"page edit++++++++++++++++++++++++");
		System.out.println(content+"content edit+++++++++++++++++");
		// ����pid��ѯ��Ʒ
		product = productService.findByPid(product.getPid());
		// ��ѯ���ж�������ļ���
		List<CategorySecond> csList = categorySecondService.findAll();
		// �����ݱ��浽ҳ��
		ActionContext.getContext().getValueStack().set("csList", csList);
		// ҳ����ת
		return "editSuccess";
	}
	
	// �޸���Ʒ����
	public String update() {
		//System.out.println(page+"update++++++++++++++++++++++++++++++");
		System.out.println(content+"content update+++++++++++++++++");
		// �޸�ʱ��
		product.setPdate(new Date());
		// �ļ��ϴ�
		if (upload != null) { // �ϴ����ļ�
			// ɾ��ԭ�е�ͼƬ
			String path = product.getImage();
			File file = new File(ServletActionContext.getServletContext().getRealPath("/"+path));
			file.delete();
			
			// ����ļ��ϴ���((���̾���·��))
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			System.out.println(realPath);
			// ��������һ���ļ�
			File diskFile = new File(realPath +"//"+ uploadFileName);
			// �ļ��ϴ�
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			product.setImage("products/"+uploadFileName);
		}
		// �޸���Ʒ���ݵ����ݿ�
		productService.update(product);
		if (content.equals("") || content == null) {
			return "updateSuccess";
		}else {
			System.out.println("ִ�й���");
			System.out.println(content+"filter=============");
			return "updateFilterSuccess";
		}
		
	}
	
	// ����������ɸѡ��Ʒ
	public String filter() {
		// ����service��ɲ�ѯ����
		
		System.out.println(content+"filter =============");
		System.out.println(pagefilter+"filter ===============");
		System.out.println(page+"filter ===============");
		
		System.out.println(content2+"---content2 =============");
		System.out.println(hot+"---hot ===============");
		
		PageBean<Product> pageBean = productService.findByPageFiler(pagefilter,content,Integer.parseInt(content2));
		// ��������ʾ��ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		// ����content
		ActionContext.getContext().getValueStack().set("content", content);
		// ����content2
		ActionContext.getContext().getValueStack().set("content2", content2);
		// ҳ����ת
		return "findAllFilter";
	}
}
