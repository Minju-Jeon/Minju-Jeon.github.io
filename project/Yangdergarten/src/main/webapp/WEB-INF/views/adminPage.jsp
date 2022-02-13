<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<title>관리자 페이지</title>
<link rel="stylesheet" href="css/jstyle.css">
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container">
		<div class="admin_con">
			<a href="showUsersList.samu"> <img src="img/studentList.png"
				style="width: 250px" alt="회원목록">

			</a> <a href="StudentList.sams"> <img src="img/girl.png"
				style="width: 250px" alt="원아등록">
			</a> <a href="GoWetherChart.samb"> <img src="img/statistics.png"
				style="width: 250px" alt="날씨정보">
			</a>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>