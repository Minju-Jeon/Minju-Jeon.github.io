<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>

<link rel="stylesheet" href="../../css/weather.css">
</head>

<body>
</head>

<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="main container">
		<!-- ##### 메인 상단부 시작 ##### -->
		<section class="main_upper section-padding-80">
			<div class="container">
				<div class="row no-gutters">
					<div class="col-12 col-md-7 col-lg-8 banner ">
						<img class="banner_img"
							src="/img/logo.png">
					</div>

					<div class="col-12 col-md-5 col-lg-4">
						<div class="weather">
							<%@ include file="weather.jsp"%>
						</div>
						<div class="notice_simple">
							<ul>
								<li>공지사항</li>
								<c:forEach var="board" items="${boardlist}" begin="0" end="3">
									<li>${board.title}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- ##### 메인 상단부 종료 ##### -->

		<!-- ##### 메인 하단부 시작 ##### -->
		<!-- <section class="main_under">
			<div class="container">

				<div class="section-heading">
					<div class="line"></div>
				</div>


				<div class="row">
					일정표 약식
					<div class="col-12 col-md-4 schedule_simple">
						<div class="">일정표</div>
					</div>


					식단표 약식
					<div class="col-12 col-md-4 foodtable_simple">
						<div>식단표</div>
					</div>

				</div>

			</div>
		</section> -->
		<!-- ##### 메인 하단부 종료 ##### -->
	</div>
	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
<!-- <script src="js/weather.js"></script> -->

<script type="text/javascript">
	$(function getWeather() {
		var url1 = 'http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst?serviceKey=tZ9sRj8UP4cdaDdZVAXbrCfRKv%2FvEQamHfN3HNJM%2FyMUPZv%2FRxcYSdzDqoJ0GxVt4VZJWOa1ob%2FAX3BSAzDXIg%3D%3D&numOfRows=10&pageNo=1&dataType=json&regId=11B10101';
		var url2 = 'api.openweathermap.org/data/2.5/weather?q=seoul&appid=b2a5e987c96963f6209407e17abbdb9d&units=metric';

		$.ajax({
			url : url2,
			dataType : "json",
			type : "GET",
			async : "false",
			success : function(resp) {
				console.log(resp);
				console.log("현재온도 : " + (resp.main.temp));
				console.log("최고 기온 : " + (resp.main.temp_max));
				console.log("최저 기온 : " + (resp.main.temp_min));
				console.log("날씨 : " + resp.weather[0].main);
				console.log("상세날씨설명 : " + resp.weather[0].description);
				console.log("날씨 이미지 : " + resp.weather[0].icon);
				console.log("구름  : " + (resp.clouds.all) + "%");
			}
		});
	});
</script>

</html>
