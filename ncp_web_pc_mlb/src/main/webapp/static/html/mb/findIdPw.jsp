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
	<div class="contain mb idpw" id="contain">

		
		<div class="container">


			<div class="location-container">
                <div class="location-contents">
                    <h2 class="title01">아이디찾기</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">아이디찾기</strong>
                    </p>
                 </div>
            </div>
			<main class="contents" id="contents">
				
				<div class="member-certification-wrap">
					<div class="member-certification-cnt d_tab02">
						<ul class="tab-type05">
							<li class="d_tab02_select on"><a href="javascript:;">아이디 찾기</a></li>
							<li class="d_tab02_select"><a href="javascript:;">비밀번호 찾기</a></li>
						</ul>
						<div class="d_tab02_cont" style="display:block;">
							<ul class="certification-list">
								<li><a href="/static/html/mb/popIdFind.jsp" class="">휴대폰 인증</a></li>
								<li><a href="/static/html/mb/popIdFind.jsp" class="">아이핀 인증</a></li>
							</ul>
						</div>
						<div class="d_tab02_cont">
							<ul class="certification-list">
								<li><a href="/static/html/mb/popPwFind.jsp" class="">휴대폰 인증</a></li>
								<li><a href="/static/html/mb/popPwFind.jsp" class="">아이핀 인증</a></li>
							</ul>
						</div>
						<h3 class="title06">인증방법안내</h3>
						<ul class="text-list02 col-type01">
							<li>휴대폰 인증 : 고객님 명의의 휴대폰 인증을 통해 본인임을 확인 합니다.</li>
							<li>아이핀(i-PIN) 인증 : 아이핀으로 회원가입 하신 경우, 아이핀 인증을 통해 본인임을 확인합니다.</li>
						</ul>
					</div>

					<!-- 2018-12-19 -->
					<!-- <p class="txt-sub-info01 txt-sub-type04"><strong class="cs-number">고객센터 : 080-807-0012</strong>운영시간 : 평일 오전 9시 ~ 오후 6시 (토/일/공휴일휴무)</p> -->
					<div class="guds">
						<strong class="cs-number">고객센터 : 080-807-0012</strong>  
						<ul class="links">
							<li><a href="javascript:;">FAQ바로가기</a></li>
							<li><a href="javascript:;">카카오톡 상담하기</a></li>
						</ul>
						<div class="time">운영시간 : 평일 오전 9시 ~ 오후 6시 (토/일/공휴일휴무)</div>
					</div>
					<!-- 2018-12-19 -->

				</div>
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->



	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
</script>
</body>
</html>