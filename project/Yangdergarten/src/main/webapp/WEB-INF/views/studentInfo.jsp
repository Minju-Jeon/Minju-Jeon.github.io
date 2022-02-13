<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<head>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/list.css">

<!--Bootstrap Table-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>


<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container list">
		<div class="st_list">
			<div class="col-md-12" id="searchStudent">
				<h1>원아 정보</h1><br>
				<c:set var="st" value="${requestScope.student}" />
				<table class="table">
					<tr>
						<td style="width: 100px">원아번호</td>
						<td style="width: 100px">${st.studentNum}</td>
					</tr>
					<tr>
						<td style="width: 100px">원아이름</td>
						<td style="width: 100px">${st.bName}</td>
					</tr>
					<tr>
						<td style="width: 100px">학부모 전화번호</td>
						<td style="width: 100px">${st.phone}</td>
					</tr>
					<tr>
						<td style="width: 100px">소속반</td>
						<td style="width: 100px">${st.bClass}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" onclick="StudentList()">목록가기</button>
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
		function StudentList(){
			window.location.href="StudentList.sams"
		}
	
		function EditPage() {
			window.location.href = "StudentEditPage.sams?studentNum=${st.studentNum}"
		}
		
		function Delete() {
			window.location.href = "StudentDelete.sams?studentNum=${st.studentNum}"
		}
		
	
	</script>
</html>