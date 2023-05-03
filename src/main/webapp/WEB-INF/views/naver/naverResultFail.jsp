<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<title>네이버 결제 실패</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
h1 {
	
	font-size: 2em;
	margin-top: 1em;
	margin-bottom: 0.5em;
}

p {
	
	margin-top: 0;
	margin-bottom: 1em;
}

button {
	display: inline-block;
	padding: 0.5em 5em;
	background-color: #555555;
	color: #fff;
	border: none;
	border-radius: 4px;
	font-size: 1em;
	cursor: pointer;
	transition: background-color 0.2s ease;
}

button:hover {
	opacity: 20%;
}
</style>
<head>
</head>
<body>
	<H1>네이버 결제 실패</H1>
	<br>
	<p id="segee">결제 과정에서 오류가 발생했습니다. 다시 시도해주세요.</p>
	<br>
	<button onclick="history.back()">이전 페이지로 돌아가기</button>
</body>
