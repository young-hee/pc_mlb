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
	<div class="contain mb login" id="contain">

		
		<div class="container">


			<div class="location-container">
                <div class="location-contents">
                    <h2 class="title01">로그인</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">로그인</strong>
                    </p>
                 </div>
            </div>
			<main class="contents" id="contents">
				
				<div class="login-wrap">
					<div class="login-cnt d_tab02">
						<ul class="tab-type05">
							<li class="d_tab02_select on"><a href="javascript:;">회원</a></li>
							<li class="d_tab02_select"><a href="javascript:;">비회원 (주문조회)</a></li>
						</ul>
						<div class="d_tab02_cont" style="display:block;">
							<input type="text" class="input-style01" title="아이디 입력창" placeholder="아이디">
							<input type="password" class="input-style01" title="비밀번호 입력창" placeholder="비밀번호">
							<p class="login-check-id">
								<span class="check-skin">
									<input type="checkbox" id="chkReply" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkReply">아이디 저장</label>
							</p>
							<p class="error-msg" style="display:block;">아이디 / 비밀번호가 일치하지 않습니다.</p>
							
							<div class="btn-wrap">
								<a href="javascript:;" class="btn-style02">로그인</a>
								<a href="/static/html/mb/popNaverCerti.jsp" class="btn-npay02"><span>네이버로 로그인</span></a>
							</div>
							<div class="login-btn-lnk">
								<a href="/static/html/mb/findIdPw.jsp">아이디 찾기</a>
								<a href="/static/html/mb/findIdPw.jsp">비밀번호 찾기</a>
								<a href="/static/html/mb/join.jsp">회원가입</a>
							</div>
						</div>
						<div class="d_tab02_cont">
							<input type="text" class="input-style01" title="주문번호 입력창" placeholder="주문번호(13자리)">
							<input type="text" class="input-style01" title="이름 입력창" placeholder="이름">
							<div class="phon-wrap">
								<input type="text" class="input-style01" title="휴대전화 첫번째 입력창" placeholder="010"><span class="hyphen">-</span><input type="text" class="input-style01" title="휴대전화 가운데 입력창"><span class="hyphen">-</span><input type="text" class="input-style01" title="휴대전화 마지막 입력창"><!-- 2018-05-24 -->
							</div>
							<p class="error-msg" style="display:block;">주문번호를 입력해 주세요.</p>
							<div class="btn-wrap">
								<a href="javascript:;" class="btn-style02">주문조회</a>
							</div>
							
							<div class="login-btn-lnk">
								<a href="javascript:;">비회원 1:1 고객상담</a><a href="javascript:;">비회원 단체구매 문의</a><a href="javascript:;">FAQ바로가기</a><a href="javascript:;">카카오톡 상담하기</a><!-- 2018-12-19 -->
							</div>
							
							<p class="login-cs-info"><strong>080-807-0012</strong> 평일 오전 9시 ~ 오후 6시 (토/일/공휴일 휴무)</p>
						</div>
					</div>

					
					<div class="member-benefit-list">
						<h3 class="title04">MLB 회원혜택</h3>
						<ul>
							<li class="deps01">
								<strong>신규회원 2종 쿠폰</strong>
								<p>온오프라인 통합 10% 할인쿠폰, 온라인 전용 10% 할인쿠폰을 드립니다.</p>
							</li>
							<li class="deps02">
								<strong>첫 구매 후 15% 쿠폰</strong>
								<p>첫 구매 후 구매 확정을 완료한 고객님께 15% 할인쿠폰을 드립니다.</p>
								<strong>생일 쿠폰</strong>
								<p>가입하신 고객님의 생일에 15%, 등록한 자녀분의 생일에 10% 할인 쿠폰을 드립니다.</p>
							</li>
							<li class="deps03">
								<strong>구매금액 5% Payback 서비스</strong>
								<p>공식 온라인 쇼핑몰에서 구매하시면 구매금액의 5%를 마일리지로 적립해 드립니다.</p>
							</li>
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