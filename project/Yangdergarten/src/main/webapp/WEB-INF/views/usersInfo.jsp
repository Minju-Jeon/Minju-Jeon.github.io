<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
	<link rel="stylesheet" href="css/list.css">
</head>
<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container list">
		<div class="st_list">
			<div class="col-md-12" id="searchStudent">
				<h1>회원 정보</h1><br>
		<c:set var="user" value="${requestScope.user}" />
				<table class="table">
			<tr>
				<td style="width: 100px">아이디</td>
				<td style="width: 100px">${user.id}</td>
			</tr>
			<tr>
				<td style="width: 100px">비밀번호</td>
				<td style="width: 100px">${user.pwd}</td>
			</tr>
			<tr>
				<td style="width: 100px">학부모 전화번호</td>
				<td style="width: 100px">${user.phone}</td>
			</tr>
			<tr>
				<td style="width: 100px">원아번호</td>
				<td style="width: 100px">${user.studentNum}</td>
			</tr>
			<tr>
				<td style="width: 100px">회원분류</td>
				<td style="width: 100px">${user.cname}</td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="UsersList()">목록가기</button>
					<button type="button" onclick="EditPage()">수정하기</button>
					<button type="button" onclick="Delete()">삭제하기</button>
				</td>
			</tr>

		</table>
	</div>
</div>
</div>
		<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
	<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
		<script type="text/javascript">
		function UsersList(){
			window.location.href="showUsersList.samu"
		}
	
		function EditPage() {
			window.location.href = "usersEditPage.samu?id=${user.id}"
		}
		
		function Delete() {
			window.location.href = "usersDelete.samu?id=${user.id}"
		}	

	</script>
</html>
