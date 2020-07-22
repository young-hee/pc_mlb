<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<!--  본인인증 확인결과 -->
<article id="popCertifi" class="layer-popup popCertifi">
	<section class="layer-popup-cont" tabindex="0">
		<h2>본인인증 확인결과</h2>
		<div class="layer-popup-wrap">
			<p class="msg-txt">만 14세 이상 대상자만 회원 가입하실 수 있습니다.</p>
			<p class="msg-txt">회원 탈퇴 후 30일이 경과하지 않아 <br> 재가입 하실 수 없습니다.</p>
			<p class="date-info">가입일  :  <strong>2017.08.24</strong></p>
			<p class="id-info">아이디  :  <strong>abcde**</strong></p>
			<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm">확인</a></div>
			<p class="msg">비회원으로 서비스를 이용하시겠습니까?  <br>  <a class="link" href="javascript:;">쇼핑 계속하기</a></p>
			<!-- 2018-12-19 -->
			<ul class="links">
				<li><a href="javascript:;">FAQ바로가기</a></li>
				<li><a href="javascript:;">카카오톡 상담하기</a></li>
			</ul>
			<!-- 2018-12-19 -->
		</div>
		<div class="bots-info">
			<p><strong>080-807–0012</strong> AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popCertifi'); 
});
</script>

</body>
</html>