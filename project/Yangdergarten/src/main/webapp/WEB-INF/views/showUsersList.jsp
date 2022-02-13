<%--
  Created by IntelliJ IDEA.
  User: yangsujin
  Date: 2021/09/28
  Time: 1:39 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/list.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container list">
		<div class="user_list">
			<div class="col-md-12" id="searchUsers">
				<h1>회원 목록</h1>

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
							<c:set var="usersDtoList" value="${requestScope.usersList}" />
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
			
			<div class="sch_area">
				<input type="text" name="id" id="id" class="sch_input"
					placeholder="아이디 입력">
				<button type="submit" id="searchBtn">검색하기</button>
			</div>
			
		</div>
	</div>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>

<script type="text/javascript">
	$(function() {
		$('#searchBtn').click(function() {
			$.ajax({
				url : "usersSearch", //"usersSearch.sams",
				type : "GET",
				data : {
					id : $('#id').val()
				},
				dataType : "html",
				success : function(responsedata) {
					let data = responsedata.trim();
					console.log(data);
					$('#searchUsers').html(data);
				}
			});
			//$('#searchStudent').load("StudentSearch.sams?bName="+$('#bName').val());
		});
		
		
		$("#id").keydown(function(keyNum){
			if(keyNum.keyCode == 13){ 
				$.ajax({
					url : "usersSearch", //"usersSearch.sams", 
					type : "GET",
					data : {
						bName : $('#id').val()
					},
					dataType : "html",
					success : function(responsedata) {
						let data = responsedata.trim();
						console.log(data);
						$('#searchUsers').html(data);
					}
				});
			}
		})
	});
</script>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>