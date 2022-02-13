<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang='en'>
<head>
	<meta charset='utf-8' />
	<style>
		.fc-event {
			margin-top:0.3rem;
			cursor:move;
		}
	</style>
	
	<script>
		window.contextPath = '${pageContext.request.contextPath}'
	</script>
	
	
	<!-- Bootstrap -->
	<link href='https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.css' rel='stylesheet' />
	<link href='https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.13.1/css/all.css' rel='stylesheet'>
	
	<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- FullCalendar -->
	<link href='css/calmain.css' rel='stylesheet' />
	<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
	<script src='${pageContext.request.contextPath}/js/calmain.js'></script>
	<script src="${pageContext.request.contextPath}/js/ko.js"></script>
	
	<!-- moment.js -->
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>

</head>
<body>
	<div id='external-events' style="float:left; width:15%; padding-left:1rem; margin-right:1.5rem; margin-top:10rem">
		<input type="text" id="newEvent" placeholder="새로운 일정..."/>
		
		<p>
			<strong>일정을 추가하세요!</strong>
		</p>

		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main' id='newEventDiv'></div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>신나는 가을 소풍</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>재밌는 촉감 놀이!</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>할로윈 과자파티!</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>철도 박물관에 가요!</div>
		</div>
		<div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
			<div class='fc-event-main'>2차 프로젝트가 끝났어요!!HO!</div>
		</div>

		<p>
			<input type='checkbox' id='drop-remove' />
			<label for='drop-remove'>remove after drop</label>
		</p>
	</div>
	
	<div style="float:left; width: 75%">
		<div style="width: 100%; height: 3rem; display: inline-flex; margin-bottom: 2rem; margin-top: 2rem">
			<div style="width: 60%; text-align: right; font-weight: bold; color: #03a9f4; font-size: 2rem">
			양선생 어린이집 일정</div>
			<div id="Btns" style="width: 40%; text-align: right">
				<button id="deleteBtn">삭제</button>
				<button id="saveBtn">전체저장</button>
			</div>
		</div>
		<div id='calendar-container'>
			<div id='calendar'></div>
		</div>
	</div>
</body>
<script>
	<c:set var="userKind" value="${sessionScope.userKind}"/>
		var userK = '<c:out value="${userKind}"/>';

		if(userK != 2){
			$('#external-events').hide();
			$('#Btns').hide();
		}else{
			$('#external-events').show();
			$('#Btns').show();
		}
	</script>
</html>