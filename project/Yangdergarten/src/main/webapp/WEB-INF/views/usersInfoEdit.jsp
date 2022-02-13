<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
	<link rel="stylesheet" href="css/user.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
    <form action="usersEdit.samu" method="get">
		<div class="addSt-page user-page">
				<h3>회원 정보 수정</h3>
		<div class="radius">
        	
        	<c:set var="user" value="${requestScope.user}"/>
        	<table class="table table-striped">
            <tr>
                <td>아이디</td>
            </tr>
            <tr>
                <td><input type="text" name="id" id="id" value="${user.id}" readonly></td>
            </tr>
            <tr>
                <td>비밀번호</td>
            </tr>
            <tr>
                <td><input type="text" name="pwd" id="pwd" value="${user.pwd}"></td>
            </tr>
            <tr>
                <td>학부모 전화번호</td>
            </tr>
            <tr>
                <td><input type="text" name="phone" id="phone" value="${user.phone}"></td>
            </tr>
            <tr>
                <td>원아번호</td>
           	</tr>
           	<tr>
                <td><input type="text" name="studentNum" id="studentNum" value="${user.studentNum}" readonly></td>
            </tr>
            <tr>
                <td>회원 분류</td>
           </tr>
           <tr>
            <td><select name="cnum" id="cnum" class="form-control">
					<option value="0" <c:if test="${user.cname eq '미등록'}">selected</c:if>>미등록</option>
					<option value="1" <c:if test="${user.cname eq '학부모'}">selected</c:if>>학부모</option>
					<option value="2" <c:if test="${user.cname eq '선생님'}">selected</c:if>>선생님</option>
				</select></td>
            </tr>
            <tr>
                <td>
               		<div class="btn_area">
						<button type="submit" class="submit">수정</button>
						<button id="cancelBtn" type="button" class="cancel">취소</button>
					</div>
				
                </td>
            </tr>
        </table>
	</div>
	</div>
    </form>
    <%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<script>
	$('#cancelBtn').click(function(){
		location.href="showUsersList.samu";
	});
</script>
</html>
