<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>传智网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css">
<script src="http://www.webdm.cn/themes/script/jquery.js"></script>
<script>
	function saveCart() {
		document.getElementById("cartFrom").submit();
	}
    $.fn.iVaryVal=function(iSet,CallBack){
        iSet=$.extend({Minus:$('.J_minus'),Add:$('.J_add'),Input:$('.J_input'),Min:1,Max:20},iSet);
        var C=null,O=null;
//插件返回值
        var $CB={};
//增加
        iSet.Add.each(function(i){
            $(this).click(function(){
                O=parseInt(iSet.Input.eq(i).val());
                (O+1<=iSet.Max) || (iSet.Max==null) ? iSet.Input.eq(i).val(O+1) : iSet.Input.eq(i).val(iSet.Max);
//输出当前改变后的值
                $CB.val=iSet.Input.eq(i).val();
                $CB.index=i;
//回调函数
                if (typeof CallBack == 'function') {
                    CallBack($CB.val,$CB.index);
                }
            });
        });
//减少
        iSet.Minus.each(function(i){
            $(this).click(function(){
                O=parseInt(iSet.Input.eq(i).val());
                O-1<iSet.Min ? iSet.Input.eq(i).val(iSet.Min) : iSet.Input.eq(i).val(O-1);
                $CB.val=iSet.Input.eq(i).val();
                $CB.index=i;
//回调函数
                if (typeof CallBack == 'function') {
                    CallBack($CB.val,$CB.index);
                }
            });
        });
//手动
        iSet.Input.bind({
            'click':function(){
                O=parseInt($(this).val());
                $(this).select();
            },
            'keyup':function(e){
                if($(this).val()!=''){
                    C=parseInt($(this).val());
//非负整数判断
                    if(/^[1-9]\d*|0$/.test(C)){
                        $(this).val(C);
                        O=C;
                    }else{
                        $(this).val(O);
                    }
                }
//键盘控制：上右--加，下左--减
                if(e.keyCode==38 || e.keyCode==39){
                    iSet.Add.eq(iSet.Input.index(this)).click();
                }
                if(e.keyCode==37 || e.keyCode==40){
                    iSet.Minus.eq(iSet.Input.index(this)).click();
                }
//输出当前改变后的值
                $CB.val=$(this).val();
                $CB.index=iSet.Input.index(this);
//回调函数
                if (typeof CallBack == 'function') {
                    CallBack($CB.val,$CB.index);
                }
            },
            'blur':function(){
                $(this).trigger('keyup');
                if($(this).val()==''){
                    $(this).val(O);
                }
//判断输入值是否超出最大最小值
                if(iSet.Max){
                    if(O>iSet.Max){
                        $(this).val(iSet.Max);
                    }
                }
                if(O<iSet.Min){
                    $(this).val(iSet.Min);
                }
//输出当前改变后的值
                $CB.val=$(this).val();
                $CB.index=iSet.Input.index(this);
//回调函数
                if (typeof CallBack == 'function') {
                    CallBack($CB.val,$CB.index);
                }
            }
        });
    };
    //调用
    $( function() {
        $('.i_box').iVaryVal({},function(value,index){
        });
    });
</script>
<style type="text/css">
	.number{
		position: relative;
	  	left: 0px;
		top: 0px;
		width: 202px;
		height: 20px;
		background-color: #696866;
		color:white;
		display: inline-block;
		padding-top: 3px;
		padding-left: 100px
	}
</style>
</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a>
				<img width="195px" height="73px" src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg" alt="达内">
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
					<img src="image\r___________renleipic_01/header.jpg" alt="正品保障" title="正品保障" height="50" width="320">
</div>	</div>
	
	<%@ include file="menu.jsp" %>

</div><div class="container productContent">
		<div class="span6">
			<div class="hotProductCategory">
					<s:iterator var="c" value="#session.cList"><!-- 遍历一级分类的列表 -->
						<dl>
							<dt> 
								<a href="${pageContext.request.contextPath}/product_findByCid.action?cid=<s:property value="#c.cid"/>&page=1"><s:property value="#c.cname"/></a>
							</dt>
									<s:iterator var="cs" value="#c.categorySeconds"><!-- 遍历二级分类的列表 -->
										<dd>
											<a href="${pageContext.request.contextPath}/product_findByCsid.action?csid=<s:property value="#cs.csid"/>&page=1"><s:property value="#cs.csname"/></a>
										</dd>
									</s:iterator>
						</dl>
					</s:iterator>		
			</div>
			

		</div>
		<div class="span18 last">
			
			<div class="productImage">
					<a title="" style="outline-style: none; text-decoration: none;" id="zoom" href="http://image/r___________renleipic_01/bigPic1ea8f1c9-8b8e-4262-8ca9-690912434692.jpg" rel="gallery">
						<div class="zoomPad"><img style="opacity: 1;" title="" class="medium" src="${pageContext.request.contextPath }/<s:property value="model.image"/>"><div style="display: block; top: 0px; left: 162px; width: 0px; height: 0px; position: absolute; border-width: 1px;" class="zoomPup"></div><div style="position: absolute; z-index: 5001; left: 312px; top: 0px; display: block;" class="zoomWindow"><div style="width: 368px;" class="zoomWrapper"><div style="width: 100%; position: absolute; display: none;" class="zoomWrapperTitle"></div><div style="width: 0%; height: 0px;" class="zoomWrapperImage"><img src="%E5%B0%9A%E9%83%BD%E6%AF%94%E6%8B%89%E5%A5%B3%E8%A3%852013%E5%A4%8F%E8%A3%85%E6%96%B0%E6%AC%BE%E8%95%BE%E4%B8%9D%E8%BF%9E%E8%A1%A3%E8%A3%99%20%E9%9F%A9%E7%89%88%E4%BF%AE%E8%BA%AB%E9%9B%AA%E7%BA%BA%E6%89%93%E5%BA%95%E8%A3%99%E5%AD%90%20%E6%98%A5%E6%AC%BE%20-%20Powered%20By%20Mango%20Team_files/6d53c211-2325-41ed-8696-d8fbceb1c199-large.jpg" style="position: absolute; border: 0px none; display: block; left: -432px; top: 0px;"></div></div></div><div style="visibility: hidden; top: 129.5px; left: 106px; position: absolute;" class="zoomPreload">Loading zoom</div></div>
						
					</a> <!--  -->
					<span class="number">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仅剩<s:property value="model.number"/>件
					</span>
				
			</div>
			
			<div class="name"><s:property value="model.pname"/></div><!-- 取出模型(model)驱动的数据 -->
			<div class="sn">
				<div>编号：<s:property value="model.pid"/></div><!-- 取出模型(model)驱动的数据 -->
			</div>
			<div class="info">
				<dl>
					<dt>商城价:</dt>
					<dd>
						<strong>￥：<s:property value="model.shop_price"/>元/份</strong><!-- 取出模型(model)驱动的数据 -->
							参 考 价：
							<del>￥<s:property value="model.market_price"/>元/份</del><!-- 取出模型(model)驱动的数据 -->
					</dd>
				</dl>
					<dl>
						<dt>促销:</dt>
						<dd>
								<a target="_blank" title="限时抢购 (2014-07-30 ~ 2015-01-01)">限时抢购</a>
						</dd>
					</dl>
					<dl>
						<dt>    </dt>
						<dd>
							<span>    </span>
						</dd>
					</dl>
			</div>
			<form id="cartFrom" action="${pageContext.request.contextPath }/cart_addCart.action" method="post">
				<input type="hidden" name="pid" value="<s:property value="model.pid"/>"/>
				<div class="action">
					
						<dl class="quantity">
							<dt>购买数量:</dt>
							<dd>
								<input id="count" name="count" value="1" maxlength="4" onpaste="return false;" type="text" class="J_input">
								<div>
									<span id="increase" class="J_add increase"></span>
									<span id="decrease" class="J_minus decrease"></span>
								</div>
							</dd>
							<dd>
								件
							</dd>
						</dl>
					<div class="buy">
							<input id="addCart" class="addCart" value="加入购物车" type="button" <s:if test="model.number != 0">onclick="saveCart()"</s:if>>
				
					</div>
					
				</div>
			</form>
			<div id="bar" class="bar">
				<ul>
						<li id="introductionTab">
							<a href="#introduction">商品介绍</a>
						</li>
						
				</ul>
			</div>
				
				<div id="introduction" name="introduction" class="introduction">
					<div class="title">
						<strong><s:property value="model.pdesc"/></strong>
					</div>
					<div>
						<img src="${pageContext.request.contextPath }/<s:property value="model.image"/>">
					</div>
				</div>
				
				
				
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
</div>
</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a href="#">关于我们</a>
						|
					</li>
					<li>
						<a href="#">联系我们</a>
						|
					</li>
					<li>
						<a href="#">诚聘英才</a>
						|
					</li>
					<li>
						<a href="#">法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >SHOP++官网</a>
						|
					</li>
					<li>
						<a>SHOP++论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright © 2018-2018 网上商城 版权所有</div>
	</div>
</div>
</body>
</html>