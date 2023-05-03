<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<style>


#container {
	max-width: 700px;
	margin: 0 auto;
	padding: 20px;
	background-color: #FFF;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	margin-top: 0;
}
h4{

	text-align: right;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #CCC;
}

th {
	background-color: #F1F1F1;
	font-weight: bold;
}

.total {
	
	font-weight: bold;
}


</style>
</head>
<body>
	<H1>네이버 결제 성공</H1>
	
	<!-- 카드일때 -->

	<!-- 현금 결제 일때 -->


	<div id="container">
		
		<h1>결제 내역</h1>
		<h4>승인 일시 : ${resultMap.authDateTime}</h4>
		<table>
			<thead>
				<tr>
					<th>날짜</th>
					<th>상품</th>
					<th>금액</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${resultMap.authDateTime}</td>
					<td>${item.goods_title}</td>
					<td>${1500 *item.order_goods_qty} 원</td>
				</tr>
				
				<tr class="total">
					<td>합계</td>
					<td></td>
					<td>${item.order_goods_qty *item.goods_sales_price} 원</td>
				</tr>
			</tbody>
		</table>
		
	</div>
</body>
</html>









