<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!--Bootstrap Table-->
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/list.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container sch_list">
		<h1>원아 검색 목록</h1>
		<div class="btn_area">
			<button type="button" onclick="allListPage()" class="btn2">전체목록</button>
			<button type="button" onclick="addPage()">원아등록</button>
		</div>
		<div class="tb_con">
			<table class="table">
				<thead>
					<tr>
						<th>원아번호</th>
						<th>이름</th>
						<th>학부모 전화번호</th>
						<th>소속반</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="studentList" value="${requestScope.studentList}" />
					<c:forEach var="st" items="${studentList}">
						<tr>
							<td><a href="StudentInfo.sams?studentNum=${st.studentNum}">${st.studentNum}</a></td>
							<td>${st.bName}</td>
							<td>${st.phone}</td>
							<td>${st.bClass}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>

<script type="text/javascript">
	function addPage() {
		window.location.href = "StudentAddPage.sams"
	}
	function allListPage() {
		window.location.href = "StudentList.sams"
	}
</script>

</html>