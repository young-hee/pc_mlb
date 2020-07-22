<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<div class="contain cs" id="contain">
		<div class="container">
		
			<h2 class="title01">약관안내</h2>
			
			<main class="contents agreementList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>고객센터</span><strong title="현재 위치">약관안내</strong>
					</p>
				</div> 
				
				<div class="d_tab02">
					
					<!-- tab S -->
					<ul class="tab-coupon tab-type03 tab-deps02">
						<li class="d_tab02_select on"><a href="javascript:;">서비스이용약관</a></li>
						<li class="d_tab02_select"><a href="javascript:;">개인정보처리방침</a></li>
					</ul>
					<!--//tab E -->
					
					<!-- 서비스이용약관 -->
					<div class="tab-privacy-cont d_tab02_cont" style="display:block;">
						<div class="agree-section-content"><!-- 2018.01.08 추가 -->
							<%@ include file="../et/inc_terms_1.jsp" %>
						</div>
					</div>
					<!-- //서비스이용약관 -->

					<!-- 개인정보처리방침 -->
					<div class="tab-privacy-cont d_tab02_cont d_tab03">
						<div class="agree-section-content" id="agreeV0"><!-- 2018.01.08 추가 -->
							<%@ include file="../et/inc_terms_4.jsp" %><!-- 20190111 변경 -->
						</div>
						<!-- 20190111 추가 -->
						<div class="agree-section-content d_tab03_cont" id="agreeV1" style="display: none;">
							<%@ include file="../et/inc_terms_2.jsp" %><!-- 2018-06-04시행 약관 -->
						</div>
						<!-- //20190111 추가 -->
						<div class="agree-section-content d_tab03_cont" id="agreeV2" style="display: none;"><!-- 2018.01.08 추가 및 변경 -->
							<%@ include file="../et/inc_terms_3.jsp" %><!-- 2018-07-01시행 약관 -->
						</div>
						
						<!-- 개인정보처리방침 select S-->
						<div class="prev-privacy-wrap">
							<div class="select-style01 d_select">
								<button type="button" class="d_select_sel" style="width:590px;"><span>개인정보처리방침 보기</span></button><!-- 2018.01.08 이전 삭제 -->
								<ul>
									<li class="d_tab03_select"><a href="#agreeV0">개인정보처리방침 보기</a></li>
									<li class="d_tab03_select"><a href="#agreeV1">(2018-06-04시행) 변경 전 개인정보처리방침 보기</a></li><!-- 2018.01.08 문구 변경 -->
									<li class="d_tab03_select"><a href="#agreeV2">(2016-07-01시행) 변경 전 개인정보처리방침 보기</a></li><!-- 2018.01.08 문구 변경 -->
								</ul>
							</div>
						</div>
						<!-- //개인정보처리방침 select E -->
						
					</div>
					<!-- //개인정보처리방침 -->
					
				</div>

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
$(document).ready(function(){
	//전체스크롤제외 박스스크롤이동
	$(".agree-link a").click(function(e){
		e.preventDefault();
        var hrefNm = $(this).attr("href");
        var object = $(hrefNm);
        if(object){
        	var posTop = $(this).closest(".agree-section-content");
        	posTop.animate({scrollTop: object.offset().top - posTop.offset().top}, 100);
        }
    });
});
</script>
</body>
</html>