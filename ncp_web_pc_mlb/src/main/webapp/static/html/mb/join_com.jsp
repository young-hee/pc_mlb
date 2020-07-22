<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain mb join com" id="contain">
		<div class="container">


			<div class="location-container">
                <div class="location-contents">
                    <h2 class="title01">회원가입</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">회원가입</strong>
                    </p>
                 </div>
            </div>
			<main class="contents" id="contents">

				<div class="join-com">
					<div class="top_info">
						<div class="hdt">홍길동 회원님 <br>	F&F 통합 회원가입을 축하드립니다.</div>
						<div class="ids">회원님의 아이디는 <strong  class="id">abcde**</strong> 입니다.</div>
						<div class="txt">
							신규회원 10% 할인쿠폰이 발행되었습니다. <br>
							즐거운 쇼핑하시고 첫 구매 15% 할인 쿠폰과 <br>
							5% 마일리지 Payback 혜택을 받아보세요  
						</div>
					</div>
					<div class="btns">
						<ul class="list">
							<li><a href="/static/html/mn/main.jsp" class="btn lg btnGoShop">쇼핑 시작하기</a></li>
							<li><a href="javascript:;" class="btn lg btnNaver">네이버 로그인 연동</a></li>
							<li><a href="javascript:;" class="btn lg btnPlus">MLB 플러스친구 추가</a></li>
						</ul>
					</div>

					<div class="guds">
						<ul class="text-list02 col-type01 bul-list">
							<li>MLB 카카오 플러스 친구를 신규 추가하시면 ‘플친 할인 쿠폰’을 드립니다.</li>
							<li>네이버를 연동하시면 더욱 편리하게 이용하실 수 있습니다.</li>
							<li>신규가입 쿠폰 및 마일리지가 모두 지급되었습니다. 마이페이지>혜택정보 에서 확인하실 수 있습니다.  <a href="/static/html/my/main.jsp" class="link">확인하러가기</a></li>
						</ul>
					</div>
				</div>
				
			</main>

			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>


</body>
</html>