<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>



<!-- 네이버 인증 완료 팝업  -->
<article id="popNaverCerti" class="layer-popup layer-type02 popNaverCerti">
	<section class="layer-popup-cont" tabindex="0">
		<h2>네이버 인증 완료</h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt03">네이버인증이 완료되었습니다.</p>
			<p class="layer-txt04">회원님의 네이버 간편 로그인을 위해 <br>F&amp;F 계정을 입력해주시면<br>네이버 아이디로 간편 로그인 하실 수 있습니다.</p>
			<div class="layer-input-box">
				<input type="text" class="input-style01" placeholder="아이디">
				<input type="password" class="input-style01" placeholder="비밀번호">
			</div>
			<p class="error-msg" style="display:block;">아이디/비밀번호를 정확히 입력했는지 확인해 주세요.</p>
		</div>
		<div class="btn-wrap03">
			<a href="javascript:;" class="btn fill lg">계정 연결하기</a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popNaverCerti'); 
});
</script>

</body>
</html>