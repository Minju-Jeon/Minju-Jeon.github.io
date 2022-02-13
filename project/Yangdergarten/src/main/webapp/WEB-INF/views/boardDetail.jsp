<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/boardDetail.css">
<!-- 아이콘 -->
<script src="https://kit.fontawesome.com/c811b402f1.js"
	crossorigin="anonymous"></script>
</head>

<body>
	<div>
		<%@ include file="/WEB-INF/common/header.jsp"%>
		<c:set var="board" value="${requestScope.board}"></c:set>

		<c:if test="${sessionScope.userId eq board.author}">
			<button class="allbtn" id="btn1" type="button"
				onclick="location.href='BoardAddOrEdit.samb?boardid=${board.boardId}&mode=1&boardindex=${board.boardindex}&title=${board.title}&content=${board.content}' ">
				<i class="fas fa-edit"></i>
			</button>
			<button class="allbtn" id="btn2" type="button"
				onclick="location.href='BoardDeleteOk.samb?boardid=${board.boardId}&boardindex=${board.boardindex}' ">
				<i class="fas fa-trash-alt"></i>
			</button>
		</c:if>

		<c:if test="${not empty sessionScope.userId}">
			<button class="allbtn" id="btn3" type="button"
				onclick="location.href='BoardAddOrEdit.samb?mode=2&boardid=${board.boardId}&boardindex=${board.boardindex}' ">
				<i class="fas fa-reply"></i>

			</button>
		</c:if>
	</div>


	<!--  오리지널
    
        <a href="BoardAddOrEdit.samb?boardid=${board.boardId}&mode=1&boardindex=${board.boardindex}&title=${board.title}&content=${board.content}">수정</a>
    <a href="BoardDeleteOk.samb?boardid=${board.boardId}&boardindex=${board.boardindex}">삭제</a>
<%--    <a href="BoardAddOrEdit.do?mod=2&boardid=${board.boardId}&boardindex=${board.boardindex}&title=${board.title}&depth=${board.depth}&reference=${board.reference}">답글</a>--%>
    <a href="BoardAddOrEdit.samb?mode=2&boardid=${board.boardId}&boardindex=${board.boardindex}">답글</a>
    
     -->

	<!-- 
    <h1 id="author" style="color:black">${board.author}</h1>
    <h2 id="tilte" style="color:black">${board.title}</h2>
    <p id="content">${board.content}</p>
     -->

	<div class="top">

		<div class="container2">
			<header>
				<h1>
					<em>${board.title}</em>
				</h1>
				<h4>
					<strong>${board.author}</strong>
				</h4>
			</header>
			<div class="main-content">
				<hr>
				<p>${board.content}</p>

				<hr>
			</div>

			<!-- 댓글 -->
			<div class="detailBox">
				<div class="titleBox">
					<label><i class="fas fa-comment"> 댓글 </i> </label>
				</div>

				<div class="actionBox">
					<ul class="commentList">
						<li>
							<div class="commentText">
								<table class="fl-table">
									<c:forEach var="reply" items="${replylist}">
										<tr>
											<td>${reply.author}:</td>
											<td>${reply.content}</td>

											<td class="date sub-text">${reply.createDate}</td>
											<c:if test="${reply.author eq sessionScope.userId }">
												<td><a
													href="ReplyDeleteOk.samb?boardindex=${board.boardindex}&no=${reply.no}"><i
														class="fas fa-trash-alt"></i></a></td>
											</c:if>
										</tr>
									</c:forEach>
								</table>
							</div>
						</li>
					</ul>

					<form class="form-inline" role="form"
						action="ReplyOk.samb?boardindex=${board.boardindex}" method="post">
						<div class="form-group">
							<input class="form-control" id="comment" type="text"
								name="content" placeholder="Your comments" />
						</div>
						<div class="form-group">
							<button id="commentbtn" type="submit">글쓰기</button>
						</div>
					</form>


					<!-- 
										<form action="ReplyOk.samb?boardindex=${board.boardindex}" method="post">
						<textarea name="content" class="form-control" id="exampleFormControlTextarea2" rows="2"></textarea>
						<button  class="btn btn-dark mt-3" >댓글등록</button>
					</form>
					
					 -->
				</div>
			</div>


		</div>
	</div>



	<!-- 버릴거 

		<div class="card-header bg-light">
			<i class="fa fa-comment fa"></i> 댓글
		</div>

		<table class="fl-table">

			<tr>
				<th>글쓴이</th>
				<th>내용</th>
				<th>작성날짜</th>
				<th>삭제하기</th>

			</tr>
			<c:forEach var="reply" items="${replylist}">
				<tr>
					<td>${reply.author}</td>
					<td>${reply.content}</td>
					<td>${reply.createDate}</td>
					<c:if test="${reply.author eq sessionScope.userId }">
						<td><a
							href="ReplyDeleteOk.samb?boardindex=${board.boardindex}&no=${reply.no}">delete</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>

		<div class="card mb-2">
			<div class="card-body">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">


						<form action="ReplyOk.samb?boardindex=${board.boardindex}"
							method="post">
							<textarea name="content" class="form-control"
								id="exampleFormControlTextarea2" rows="2"></textarea>
							<button type="submit" class="btn btn-dark mt-3" id="commentbtn">댓글등록</button>
						</form>

					</li>
				</ul>
			</div>
		</div>


 -->

	<!-- 오리지널	

<form action="ReplyOk.samb?boardindex=${board.boardindex}" method="post">
    <input type="text" name="author">
    <input type="text" name="content">
    <button>submit</button>
</form>

 -->

	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>

</html>