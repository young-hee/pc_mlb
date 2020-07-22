<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="popTermsAgree" class="layer-popup popTermsAgree">
	<section class="layer-popup-cont" tabindex="0">
		<h2>약관동의</h2>
		<div class="layer-cont scroll">
			<div class="top_info">
				<ul class="text-list02 col-type01 list">
					<li>2011년 2월 19일 부터 F&F의 모든 패밀리 브랜드가 통합회원제를 실시함에 따라, 본 사이트에서 회원가입을 하시면 F&F의 통합회원이 됩니다.</li>
					<li>F&F의 통합회원이 되시면 동일한 ID와 비밀번호로 F&F 패밀리 브랜드 웹사이트의 다양한 서비스를 안전하고 편리하게 이용하실 수 있습니다.</li>
					<li>서비스 이용약관, 개인정보 수집 및 이용동의를 읽고 동의 후에 회원가입을 하실 수 있습니다.</li>
					<li>회원님의 정보는 당사의 기술적/관리적 보호조치에 의해 철저히 보호됩니다.</li>
				</ul>
			</div>

			<div class="termsBox">
				
				<div class="hdt">서비스 이용 약관</div>
				<div class="agree-section-content"><!-- 2018.01.08변경 -->
					<%@ include file="../et/inc_terms_1.jsp" %>
				</div>
				
				<div class="hdt">개인정보 수집 및 이용 약관</div>
				<div class="agree-section-content"><!-- 2018.01.08변경 -->
					<%@ include file="../et/inc_terms_2.jsp" %>
				</div>
				
			</div>


		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popTermsAgree'); 
	//20190212 수정//전체스크롤제외 박스스크롤이동
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