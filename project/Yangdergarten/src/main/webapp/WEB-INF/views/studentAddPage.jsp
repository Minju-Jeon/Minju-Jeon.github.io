<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<!--Bootstrap Table-->
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/user.css">

<title>관리자 - 원아등록</title>
</head>

<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
 <form id="sadd" action="StudentAdd.sams" method="get">
	<div class="addSt-page user-page">

		<h3>원아등록</h3>
		<div class="radius">

			<table class="table table-striped">

				<tr>
					<td>원생 등록번호</td>
				</tr>
				<tr>
					<td><input type="text" name="studentNum" id="studentNum"></td>
				</tr>
				<tr>
					<td>학부모 전화번호</td>
				</tr>
				<tr>
						<td><input type="text" name="phone" id="phone" placeholder="000-0000-0000"></td>
				</tr>
				<tr>
					<td>원생 이름</td>
				</tr>
				<tr>
					<td><input type="text" name="bName" id="bName"></td>
				</tr>
				<tr>
					<td>원생 반</td>
				</tr>
				<tr>
					<td><select name="bClassNum" id="bClassNum">
							<option value="0">햇님반</option>
							<option value="1">달님반</option>
							<option value="2">별님반</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><div class="btn_area">
							<!-- <button type="submit" class="submit">등록</button> -->
							<button type="submit" class="submit">등록</button>
							<button type="reset" class="cancel">취소</button>
						</div></td>
				</tr>
			</table>
		</div>
	</div>
	</form>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>

</html>