<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/user.css">

<!--Bootstrap Table-->
</head>


<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<form action="StudentEdit.sams" method="get">
		<div class="addSt-page user-page">
				<h3>원아정보수정</h3>
		<div class="radius">
		
			<c:set var="st" value="${requestScope.student}" />
			<table class="table table-striped">
				<tr>
					<td>원아 번호</td>
				</tr>
				<tr>
					<td><input type="text" name="studentNum" id="studentNum" value="${st.studentNum}" readonly></td>
				</tr>
				<tr>
					<td>원아 이름</td>
				</tr>
				<tr>
					<td><input type="text" name="bName" id="bName" value="${st.bName}"></td>
				</tr>
				<tr>
					<td>학부모 전화번호</td>
				</tr>
				<tr>
					<td><input type="text" name="phone" id="phone" value="${st.phone}" readonly></td>
				</tr>
				<tr>
					<td>원아 반</td>
				</tr>
				<tr>
					<td><select name="bClassNum" id="bClassNum" class="form-control">
							<option value="0" <c:if test="${st.bClass eq '햇님반'}">selected</c:if>>햇님반</option>
							<option value="1" <c:if test="${st.bClass eq '달님반'}">selected</c:if>>달님반</option>
							<option value="2" <c:if test="${st.bClass eq '별님반'}">selected</c:if>>별님반</option>
					</select></td>
				</tr>
				<tr>
					<td>
					<div class="btn_area">
							<!-- <button type="submit" class="submit">등록</button> -->
							<button type="submit" class="submit">등록</button>
							<button type="reset" class="cancel">취소</button>
						</div>
				
					</td>
				</tr>
			</table>
		</div>
		</div>
		</form>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>