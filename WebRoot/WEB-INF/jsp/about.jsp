<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>订单页面</title>
<link href="${pageContext.request.contextPath }/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath }/css/cart.css" rel="stylesheet" type="text/css"/>
<script>
        window.onload = function () {
            one = document.getElementById('one');
            two = document.getElementById('two');
            three = document.getElementById('three');
            six = document.getElementById('six');
            nine = document.getElementById('nine');
            year = document.getElementById('year');
            box = document.getElementById('effectivePrice');
            
            one.onclick = function () {
                box.innerHTML = '¥333';
            };
            two.onclick = function () {
                box.innerHTML = '¥666';
            };
            three.onclick = function () {
                box.innerHTML = '¥999';
            };
            six.onclick = function () {
                box.innerHTML = '¥1332';
            };
            nine.onclick = function () {
                box.innerHTML = '¥1665';
            };
            year.onclick = function () {
                box.innerHTML = '¥1998';
            };
        };
    </script>

</head>
<body>

<div class="container header">
	<div class="span5">
		<div class="logo">
			<a href="./网上商城/index.htm">
				<img width="195px" height="73px" src="${pageContext.request.contextPath }/image/r___________renleipic_01/logo.jpg" alt="达内"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath }/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	
</div>
	
	<%@ include file="menu.jsp" %>
	
</div>	

<div class="container cart">
	content
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