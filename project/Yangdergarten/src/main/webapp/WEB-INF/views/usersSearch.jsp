<%--
  Created by IntelliJ IDEA.
  User: yangsujin
  Date: 2021/09/28
  Time: 8:36 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
</head>
<body>
	<div class="sch_list noMargin">
		<h1>회원 검색 목록</h1>

		<div class="btn_area">
			<button type="button" onclick="allListPage()" class="btn2">전체목록</button>
		</div>
		<div class="tb_con">
			<table class="table">
				<thead>
					<tr>
						<th>아이디</th>
						<th>비밀번호</th>
						<th>전화번호</th>
						<th>원아번호</th>
						<th>권한</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="usersDtoList" value="${requestScope.usersDtoList}" />
					<c:forEach var="users" items="${usersDtoList}">
						<tr>
							<td><b><a href="./showUsersInfo.samu?id=${users.id}">${users.id}</a></b></td>
							<td>${users.pwd}</td>
							<td>${users.phone}</td>
							<td>${users.studentNum}</td>
							<td>${users.cname}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">

	function allListPage() {
		window.location.href = "showUsersList.samu"
	}
</script>
</html>
