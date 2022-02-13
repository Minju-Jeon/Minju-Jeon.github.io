<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8'>
<meta http-equiv='X-UA-Compatible' content='IE=edge'>
<link rel="stylesheet" href="css/intro.css">
<jsp:include page="/WEB-INF/common/head.jsp"></jsp:include>

</head>




<body>
	<%@ include file="/WEB-INF/common/header.jsp"%>
	<div class="container">
		<div class="row">
			<div class="heading-title text-center">
				<h3 class="text-uppercase">교직원 소개</h3>
				<p class="p-top-30 half-txt">꿈과 희망을 심어주는 교사 <br> 노력하며 연구하는 교사<br>학부모에게 신뢰를 주는 교사<br></p>
				<p class="p-top-30 half-txt">세계화 시대를 주도하는 어린이로 성장 할 수 있도록 바른 인성,
					창의력, 자기 주도적 학습능력을 함양을 목표로 어린이, 교사, 학부모가 함께 협력하여 꿈과 사랑의 장을
					이루어가는 세계 일류 교육을 실천합니다.</p>
			
			
			</div>

			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/ha.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>사랑으로 믿음으로</h4>
							<p>신뢰할 수 있는 어린이집이 되도록 노력하겠습니다.</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>YANG HAYOUNG</h5>
					<span> 원장 </span>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/soo.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>13:00~16:00</h4>
							<p>음악반, 어린이 미술 지도교사</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>YANG SU_JIN</h5>
					<span>음악 교사</span>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/min.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>13:00~16:00</h4>
							<p>미술반, 어린이 미술 지도교사</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>JEON MIN_JU</h5>
					<span>미술 교사</span>
				</div>
			</div>

			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/bum.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>09:00~14:00</h4>
							<p>반일반, 영.유아 교사</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>KIM BEOMSOO</h5>
					<span>햇님반 교사</span>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/jinsoo.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>09:00~18:00</h4>
							<p>종일반, 5세 어린이 교사</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>KIM JINSOO</h5>
					<span>달님반 교사</span>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="team-member">
					<div class="team-img">
						<img src="img/ja.png" alt="team member" class="img-responsive">
					</div>
					<div class="team-hover">
						<div class="desk">
							<h4>09:00~18:00</h4>
							<p>종일반, 3~4세 어린이 교사</p>
						</div>

					</div>
				</div>
				<div class="team-title">
					<h5>CHOI JAYEONG</h5>
					<span>별님반 교사</span>
				</div>
			</div>

		</div>
	</div>







	<%@ include file="/WEB-INF/common/footer.jsp"%>
</body>
<jsp:include page="/WEB-INF/common/jscode.jsp"></jsp:include>
</html>