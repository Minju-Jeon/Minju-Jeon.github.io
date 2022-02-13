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
<link rel="stylesheet" href="css/list.css">
<link rel="stylesheet" href="css/board.css">
<!-- <link rel="stylesheet" href="css/boardMain.css">  -->

<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container board">
		<div class="loadMap_area">
			<%@ include file="/WEB-INF/common/loadMap.jsp"%>
		</div>
		<div class="list contents_area">

			<c:if test="${boardid == '0'}">
				<h1>공지사항</h1>
			</c:if>
			<c:if test="${boardid == '1'}">
				<h1>우리반 소식</h1>
			</c:if>
			<c:if test="${boardid == '2'}">
				<h1>학부모 공간</h1>
			</c:if>
		</div>
		<a id="write" href="BoardAddOrEdit.samb?boardid=${boardid}&mode=0">글쓰기</a>

		<div class="table-wrapper">
			<table class="fl-table">
				<thead>
					<tr>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach var="board" items="${boardlist}">
						<tr>
							<td>
								<%--                        <a href="BoardContent.do?boardid=${boardid}&boardindex=${board.boardindex}&depth=${board.depth}&reference=${board.reference}">--%>
								<a style="text-align: start;"
								href="BoardContent.samb?boardindex=${board.boardindex}&boardid=${boardid}">
									${board.title} </a>
							</td>

							<td>${board.author}</td>


							<td>${board.updateDate}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>


		<div class="search">
			<form action="SearchBoard.samb?boardid=${boardid}" method="post">


				<select name="searchmode" id="searchmode">
					<option value="0">이름</option>
					<option value="1">제목</option>
				</select> <input type="text" name="searchvar" id="searchvar">
				<button id="searchbtn" type="submit">검색</button>
			</form>
			<c:set var="pager" value="${requestScope.pager.toString()}"></c:set>
			<div>
				<ul class="pagination">${pager}</ul>
			</div>


		</div>
	</div>
	<%@ include file="/WEB-INF/common/footer.jsp"%>


</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>
