<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
</head>
<body>
	<H1>1.최종 주문 내역서</H1>
	<table class="list_view">
		<tbody align=center>
			<tr style="background: #33ff00">
				<td>주문번호</td>
				<td colspan=2 class="fixed">주문상품명</td>
				<td>수량</td>
				<td>주문금액</td>
				<td>배송비</td>
				<td>예상적립금</td>
				<td>주문금액합계</td>
			</tr>
			<tr>
				<c:forEach var="item" items="${myOrderList }">
					<td>${item.order_id }</td>
					<td class="goods_image"><a
						href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">
							<img width="75" alt=""
							src="${contextPath}/thumbnails.do?goods_id=${item.goods_id}&fileName=${item.goods_fileName}">
					</a></td>
					<td>
						<h2>
							<a
								href="${contextPath}/goods/goodsDetail.do?goods_id=${item.goods_id }">${item.goods_title }</a>
						</h2>
					</td>
					<td>
						<h2>${item.order_goods_qty }개<h2>
					</td>
					<td><h2>${item.order_goods_qty *item.goods_sales_price}원
							(10% 할인)</h2></td>
					<td><h2>0원</h2></td>
					<td><h2>${1500 *item.order_goods_qty }원</h2></td>
					<td>
						<h2>${item.order_goods_qty *item.goods_sales_price}원</h2>
					</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="clear"></div>
	<form name="form_order">
		<h1>.결제 실패</h1>
		<biv class="detail_table">
		<table>
			<tbody>
				<tr class="dot_line">
					<td class="fixed_join">결제 코드</td>
					<td><c:out value="${responseCode}" /></td>
				</tr>
				<tr class="dot_line">
					<td class="fixed_join">결제 메시지</td>
					<td><c:out value="${responseMsg}" /></td>
				</tr>
			</tbody>
		</table>
		</div>
	</form>
	<div class="clear"></div>
	<br>
	<br>
	<br>
	<center>
		<br> <br> <a href="${contextPath}/main/main.do"> <img
			width="75" alt=""
			src="${contextPath}/resources/image/btn_shoping_continue.jpg">
		</a>
		<div class="clear"></div>
	</center>
</body>
