<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
   <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>

		<link rel="stylesheet" href="css/intro.css">
	     <jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>
    <style>
    @import
	url("https://fonts.googleapis.com/css?family=Jua&family=Nanum+Gothic:wght@400;700;800&family=Roboto:300,400,500,700,900")
	;
        #map {
            margin: 1%;
            width: 100%;
            height: 500px;
            align-self: center;
        }

        #maptittle {
            width: 100%;
            align-self: center;
            margin: 10px;
            border-bottom: 2px #1b0ce2a4 dashed;
        }
        
        #mapintro {
        	width: 300px;
        	display: inline-block;
			font-size: 1.0rem;
        	
        	
        }
    </style>
</head>


<!-- map -->
<!--
    카카오 AIP 키 : 4845f5070e1c6602a8bc96a8d69e94d7 ( 8090에서만 구동가능)
    학원 위도와 경도 : 37.49981285753118, 127.02902657410843
-->

<body>
 <%@ include file="/WEB-INF/common/header.jsp"%>
   <div class="container">
		<div class="row">
			<div class="heading-title text-center">
				<h3 class="text-uppercase" style="font-family: 'Jua', sans-serif;">오시는 길</h3>
				<br>
				<div id ="mapintro">
				 <img src="img/location.png" style="width:5%;"><a> 주소 : 서울 특별시 강남구 역삼동 819-3 </a>
				 </div>
				 <div id ="mapintro">
				 <img src="img/train.png" style="width:5%;"><a> 지하철 : 강남역11번, 12번출구 도보 5분</a>
				 </div>
			</div>
         </div>
            <div id="map"></div>
            <script type="text/javascript"
                src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4845f5070e1c6602a8bc96a8d69e94d7"></script>
            <script>
                var container = document.getElementById('map');
                var options = {
                    center: new kakao.maps.LatLng(37.49981285753118, 127.02902657410843), // 위도와 경도
                    level: 3 //지도 확대 레벨
                };

                var map = new kakao.maps.Map(container, options); //지도를 생성합니다.

                // 마커가 표시될 위치입니다 
                var markerPosition = new kakao.maps.LatLng(37.49981285753118, 127.02902657410843);

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    position: markerPosition
                });

                // 마커가 지도 위에 표시되도록 설정합니다
                marker.setMap(map);


                // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                var content = '<div class="customoverlay">' +
                    '  <a href="https://map.kakao.com/link/map/11394059" target="_blank">' +
                    '    <span class="title">양선생 유치원</span>' +
                    '  </a>' +
                    '</div>';

                // 커스텀 오버레이가 표시될 위치입니다 
                var position = new kakao.maps.LatLng(37.49981285753118, 127.02902657410843);

                // 커스텀 오버레이를 생성합니다
                var customOverlay = new kakao.maps.CustomOverlay({
                    map: map,
                    position: position,
                    content: content,
                    yAnchor: 0.1
                });
            </script>
        
          </div>
   
   



<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
	 <jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>