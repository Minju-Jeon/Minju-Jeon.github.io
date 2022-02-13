<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/user.css">
<title>Document</title>
</head>
<body>

	<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
	<div class="register-page user-page">
		<div class="form">
			<h3>회원가입</h3>
			<form class="register-form" action="signup.samu" method="post">

				<p>아이디</p>
				<input type="text" name="id" id="id">
				<p class="chid"></p>
				
				<p>비밀번호</p>
				<input type="password" name="pwd" id="pwd">
				<p class="chpwd"></p>

				<p>비밀번호 확인</p>
				<input type="password" name="pwd_ch" id="pwd_ch">
				<p class="chpwd_ch"></p>


				<p>휴대전화 번호</p>
				<input type="text" name="phone" id="phone" placeholder="000-0000-0000">
				<p class="chphone"></p>
				<table id="bList"></table>
				
				<p>원아 번호</p>
				<input type="text" name="studentNum" id="studentNum">

				<button type="submit">가입하기</button>

				<p class="message">이미 가입한 회원이신가요?</p>
				<button type="button" onclick="location.href='showLogin.samu'">로그인 페이지로</button>
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
<script src="login.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js "></script>
<script src="signUp.js"></script>
</html>