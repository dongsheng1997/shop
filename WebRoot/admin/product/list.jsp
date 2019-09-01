<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
	<HEAD>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="${pageContext.request.contextPath}/css/Style1.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
		<script type="text/javascript">
			function addProduct(){
				window.location.href = "${pageContext.request.contextPath}/adminProduct_addPage.action";
			}
			function delOne(pid,page){
				var sure = window.confirm("确定删除吗?");
				if(sure){
					window.location.href="${pageContext.request.contextPath}/adminProduct_delete.action?pid="+pid+"&page="+page;
				}
			}
		</script>
	</HEAD>
	<body>
		<br>
			<table cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
				
					<tr>
						<td class="ta_01" align="center" bgColor="#afd1f3">
							<strong>商品列表</strong>
						</td>
						
					</tr>
					<if test="#session.fail != null">
						<tr>
							<td class="ta_01" align="center" bgColor="#afd1f3">
								<font color="red"><s:property value="#session.fail"/></font>
								
							</td>
						</tr>
					</if>
					
					<tr>
						
						<td class="ta_01" >
							<div>
								<form action="${pageContext.request.contextPath}/adminProduct_filter.action" style="ppadding-left: 30px">
									商品名称:<input type="hidden" name="pagefilter" value="1"/>
									<input type="text" name="content" value="<s:property value="content"/>">
									&nbsp;&nbsp;&nbsp;&nbsp;
									热门:<input type="hidden" name="hot" value="1"/>
									
									<input type="radio" name="content2" value="1" <s:if test="content2==null">checked="checked"</s:if> <s:if test="content2==1">checked="checked"</s:if>>是
									<input type="radio" name="content2" value="0" <s:if test="content2==0">checked="checked"</s:if>>否
									
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="submit" value="筛选">
								</form>
								
								<div align="right" style="margin-top: -37px">
									<button type="button" id="add" name="add" value="添加" class="button_add" onclick="addProduct()">
									&#28155;&#21152;
									</button>								
								</div>
							</div>
								
							

						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#f5fafe">
							<table cellspacing="0" cellpadding="1" rules="all"
								bordercolor="gray" border="1" id="DataGrid1"
								style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

									<td align="center" width="12%">
										序号
									</td>
									<td align="center" width="15%">
										商品图片
									</td>
									<td align="center" width="15%">
										商品名称
									</td>
									<td align="center" width="15%">
										商品价格
									</td>
									<td align="center" width="15%">
										是否热门
									</td>
									<td align="center" width="15%">
										库存
									</td>
									<td width="7%" align="center">
										编辑
									</td>
									<td width="7%" align="center">
										删除
									</td>
								</tr>
								<!-- 
									cList 往值栈里存的名字 
									status 对象可以判断当前元素的位置 -->
								<s:iterator var="product" value="pageBean.list" status="status">
										<tr onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#F5FAFE';">
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="12%">
												<s:property value="#status.count"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<img alt="商品图片" width="40" height="45" src="${pageContext.request.contextPath}/<s:property value="#product.image"/>">
											</td>
											
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<s:property value="#product.pname"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<s:property value="#product.shop_price"/>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<s:if test="#product.is_hot == 1">
													是
												</s:if>
												<s:else>
													否
												</s:else>
											</td>
											<td style="CURSOR: hand; HEIGHT: 22px" align="center"
												width="15%">
												<s:property value="#product.number"/>
											</td>
											<s:if test="content==null ">
												<td align="center" style="HEIGHT: 22px">
													<a href="${pageContext.request.contextPath}/adminProduct_edit.action?pid=<s:property value="#product.pid"/>&page=<s:property value="pageBean.page"/>">
														<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
													</a>
												</td>
												<td align="center" style="HEIGHT: 22px">
													<a href="javascript:delOne(<s:property value="#product.pid"/>,<s:property value="pageBean.page"/>)">
														<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
														<s:property value="pageBean.page"/>
													</a>
												</td>
											</s:if>
											
											
											<s:else>
												<td align="center" style="HEIGHT: 22px">
													<a href="${pageContext.request.contextPath}/adminProduct_edit.action?
														pid=<s:property value="#product.pid"/>&page=<s:property value="pageBean.page"/>&content=<s:property value="content"/>">
														<img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand">
														<s:property value="pageBean.page"/>
														<s:property value="content"/>
													</a>
												</td>
												<td align="center" style="HEIGHT: 22px">
													<a href="javascript:delOne(<s:property value="#product.pid"/>,<s:property value="pageBean.page"/>)">
														<img src="${pageContext.request.contextPath}/images/i_del.gif" width="16" height="16" border="0" style="CURSOR: hand">
														<s:property value="pageBean.page"/>
													</a>
												</td>
											</s:else>
										</tr>
									</s:iterator>	
							</table>
						</td>
					</tr>
					
					<!-- 没筛选 -->
					<s:if test="content == null || content2 == null">
					<tr align="center">
						<td class="ta_01" align="center" bgColor="#afd1f3">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="pageBean.page != 1">
								<a href="${pageContext.request.contextPath }/adminProduct_findAll.action?page=1">首页</a>|
								<a href="${pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page-1"/>">上一页</a>|
							</s:if>
							
							<s:iterator var="i" begin="1" end="pageBean.totalPage">
								<!-- 点击不是当前页 -->
								<s:if test="pageBean.page != #i">
									<a href="${pageContext.request.contextPath}/adminProduct_findAll.action?page=<s:property value="#i"/>"><s:property value="#i"/></a>
								</s:if>
								<!-- 点击是当前页 -->
								<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
								</s:else>
							</s:iterator>
							
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.page+1"/>">下一页</a>|
								<a href="${pageContext.request.contextPath }/adminProduct_findAll.action?page=<s:property value="pageBean.totalPage"/>">尾页</a>
							</s:if>
						</TD>
					</tr>
					</s:if>
					<!-- 筛选 -->
					<s:else>
					<tr align="center">
						<td class="ta_01" align="center" bgColor="#afd1f3">
							第<s:property value="pageBean.page"/>/<s:property value="pageBean.totalPage"/>页&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="pageBean.page != 1">
								<a href="${pageContext.request.contextPath }/adminProduct_filter.action?pagefilter=1&content=<s:property value="content"/>&content2=<s:property value="content2"/>">首页</a>|
								<a href="${pageContext.request.contextPath }/adminProduct_filter.action?pagefilter=<s:property value="pageBean.page-1"/>&content=<s:property value="content"/>&content2=<s:property value="content2"/>">上一页</a>|
							</s:if>
							
							<s:iterator var="i" begin="1" end="pageBean.totalPage">
								<!-- 点击不是当前页 -->
								<s:if test="pageBean.page != #i">
									<a href="${pageContext.request.contextPath}/adminProduct_filter.action?pagefilter=<s:property value="#i"/>&content=<s:property value="content"/>&content2=<s:property value="content2"/>"><s:property value="#i"/></a>
								</s:if>
								<!-- 点击是当前页 -->
								<s:else>
									<span class="currentPage"><s:property value="#i"/></span>
								</s:else>
							</s:iterator>
							
							<s:if test="pageBean.page != pageBean.totalPage">
								<a href="${pageContext.request.contextPath }/adminProduct_filter.action?pagefilter=<s:property value="pageBean.page+1"/>&content=<s:property value="content"/>&content2=<s:property value="content2"/>">下一页</a>|
								<a href="${pageContext.request.contextPath }/adminProduct_filter.action?pagefilter=<s:property value="pageBean.totalPage"/>&content=<s:property value="content"/>&content2=<s:property value="content2"/>">尾页</a>
							</s:if>
						</TD>
					</tr>
					</s:else>
				</TBODY>
			</table>
	</body>
</HTML>

