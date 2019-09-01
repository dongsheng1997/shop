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

		<div class="span24">
		
			<form id="orderForm" action="${pageContext.request.contextPath }/user_payAssociator.action" method="post">
				<input type="hidden" name="uid" value="<s:property value="#session.existUser.uid"/>"/><!-- 隐藏标签 -->
				<div class="span24">
					<p>
							开通时长：<input id="one" type="radio" name="asstime" style="margin-top: -2px"/>一个月&nbsp;&nbsp;&nbsp;<input id="two" type="radio" name="asstime"  style="margin-top: -2px"/>两个月&nbsp;&nbsp;&nbsp;<input id="three" type="radio" name="asstime" style="margin-top: -2px"/>三个月&nbsp;&nbsp;&nbsp;
								
								<input id="six" type="radio" name="asstime" style="margin-top: -2px"/>六个月&nbsp;&nbsp;&nbsp;<input id="nine" type="radio" name="asstime" style="margin-top: -2px"/>九个月&nbsp;&nbsp;&nbsp;<input id="year" type="radio" name="asstime" style="margin-top: -2px"/>一年<br />
							<font size="10" color="red">开通会员将会享受全场<strong color="red">8</strong>折优惠</font>
							</dl>
							<div class="total">
								<em id="promotion"></em>
								金额: <strong id="effectivePrice"></strong>
							</div>
						</p>
						<hr />
						<p>
							选择银行：<br/>
							<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
							<img src="${pageContext.request.contextPath }/bank_img/icbc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
							<img src="${pageContext.request.contextPath }/bank_img/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							
							<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
							<img src="${pageContext.request.contextPath }/bank_img/abc.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
							<img src="${pageContext.request.contextPath }/bank_img/bcc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>平安银行
							<img src="${pageContext.request.contextPath }/bank_img/pingan.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
							<img src="${pageContext.request.contextPath }/bank_img/ccb.bmp" align="middle"/>
							<br/>
							<input type="radio" name="pd_FrpId" value="CEB-NET-B2C"/>光大银行
							<img src="${pageContext.request.contextPath }/bank_img/guangda.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="pd_FrpId" value="CMBCHINA-NET-B2C"/>招商银行
							<img src="${pageContext.request.contextPath }/bank_img/cmb.bmp" align="middle"/>
						</p>
						<hr />
						<p style="text-align:right">
							<a href="javascript:document.getElementById('orderForm').submit();">
								<img src="${pageContext.request.contextPath }/images/finalbutton.gif" width="204" height="51" border="0" />
							</a>
						</p>
				</div>
			</form>
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