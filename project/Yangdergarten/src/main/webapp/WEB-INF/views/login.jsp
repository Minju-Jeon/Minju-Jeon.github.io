<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.Connection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/user.css">
<title>Document</title>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>

	<div class="login-page user-page">

		<div class="form">
			<h3>로그인</h3>
			<form class="login-form" action="login.samu" method="POST">
				<p>아이디</p>
				<input type="text" name="id" id="id" />
				<p>비밀번호</p>
				<input type="password" name="pwd" id="pwd" />
				<button type="submit" id="login_btn">로그인</button>

				<p class="message">처음 오셨나요?</p>
				<button type="button" onclick="location.href='showSignUp.samu'">회원가입
					페이지로</button>
			</form>

		</div>
	</div>


	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>

<!-- 비밀번호 창에서 엔터시 로그인 -->
<script type="text/javascript">
	$(function() {
		$("#pwd").keydown(function(keyNum) {
			if (keyNum.keyCode == 13) {
				$("#login_btn").click()
			}
		})
	});
</script>
</html>
