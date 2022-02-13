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
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>Document</title>

<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>


<link rel="stylesheet" href="css/addboard.css">


<!-- include libraries(jQuery, bootstrap) -->
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css"
	rel="stylesheet">
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script
	src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<!-- include summernote css/js-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote-bs4.js"></script>




<script type="text/javascript">
$(document).ready(function() {
	  $('#summernote').summernote({
	    	placeholder: 'content',
	        height: 400,
	       	width:1100,
	        focus: true, 
	        lang : 'ko-KR',
	        placeholder: '내용을 입력하세요'
	   
	        /*
	      	  toolbar: [ // 원하는 툴바 설정가능
	      	    // [groupName, [list of button]]
	      	    ['style', ['bold', 'italic', 'underline', 'clear']],
	      	    ['font', ['strikethrough', 'superscript', 'subscript']],
	      	    ['fontsize', ['fontsize']],
	      	    ['color', ['color']],
	      	    ['para', ['ul', 'ol', 'paragraph']],
	      	    ['height', ['height']]
	      	  ]
	  */
	  
	        
	  });
	  
	  

	  
	});
	
	
/*
//저장버튼 클릭
$(document).on('click', '#saveBtn', function () {
    saveContent();
  
});
});

//데이터 저장
function saveContent() {
	
    //값 가져오기
    var summernoteContent = $('#summernote').summernote('code');        //썸머노트(설명)
    console.log("summernoteContent : " + summernoteContent);
	
}
*/
</script>



</head>

<body>


	<c:if test="${ mode == '0'}">
		<h1 id="writetitle">글 쓰기</h1>


		<!-- 오리지널
		 <form name="form1" method="post" action="BoardWriteOk.samb?boardid=${boardid}">

            <input type="text" name="author">
            <input type="text" name="title">
            <input type="text" name="content">
            <button type="submit">submit</button>
        </form>
        
		 -->
		<div id="big">


			<form name="form1" method="post"
				action="BoardWriteOk.samb?boardid=${boardid}">

				<div>
					<input id="title" type="text" name="title" placeholder="제목">
					<!-- 		file1 : <input type="file" name="filename1"><br>	   
		file2 : <input type="file" name="filename2"><br> -->

				</div>

				<textarea id="summernote" name="content" rows="10" cols="10"></textarea>


				<input id="submitbtn" type="submit" value="글등록">
				<button id="returnbtn" type="button"
					onclick="location.href='BoardList.samb?boardid=0'">작성취소</button>

			</form>

		</div>




	</c:if>
	<c:if test="${ mode == '1'}">
		<c:set var="board" value="${requestScope.board}"></c:set>
		<h1 id="writetitle">글 수정</h1>

		<div id="big">

			<!-- <h1>${board.boardindex}</h1>  -->
			<form name="form1" method="post"
				action="BoardEditOk.samb?boardindex=${board.boardindex}&boardid=${board.boardId}">

				<input id="change" type="text" name="title" value="${board.title}">
				<textarea id="summernote" name="content" rows="10" cols="10">${board.content}</textarea>
				<button id="submitbtn" type="submit">수정</button>
				<button id="returnbtn" type="button"
					onclick="location.href='BoardList.samb?boardid=0'">작성취소</button>

			</form>
		</div>
	</c:if>



	<c:if test="${ mode == '2'}">
		<c:set var="board" value="${requestScope.board}"></c:set>
		<h1 id="writetitle">답글 쓰기</h1>

		<div style="margin-left: 170px">
			<p>내용</p>
			<input type="text" name="content" value="${board.content}">
		</div>

		<div id="big">


			<form name="form1" method="post"
				action="BoardRewriteOk.samb?depth=${board.depth}&boardindex=${board.boardindex}&boardid=${board.boardId}&title=${board.title}&reference=${board.reference}">

				<div>
					<p>re:${board.title}</p>
					<!-- 		file1 : <input type="file" name="filename1"><br>	   
		file2 : <input type="file" name="filename2"><br> -->

				</div>

				<textarea id="summernote" name="content" rows="10" cols="10"></textarea>


				<input id="submitbtn" type="submit" value="답글달기">
				<button id="returnbtn" type="button"
					onclick="location.href='BoardList.samb?boardid=0'">작성취소</button>

			</form>

		</div>


	</c:if>






</body>

</html>

