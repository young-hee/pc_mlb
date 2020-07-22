<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>





<!-- 비밀번호 변경 -->
<article id="popPwFind" class="layer-popup layer-type02 popPwFind">
	<section class="layer-popup-cont" tabindex="0">
		<h2>비밀번호 변경</h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt03">새로운 비밀번호를 입력해주세요.</p>
			
			<div class="layer-input-box">
				<input type="password" class="input-style01" placeholder="비밀번호" style="width:100%">
				<input type="password" class="input-style01" placeholder="비밀번호 확인" style="width:100%">
				<span class="error-msg" style="display:block;">비밀번호가 일치하지 않습니다.</span>
			</div>
		</div>
		<div class="btn-wrap">
			<a href="javascript:;" class="btn lg fill">비밀번호 변경하기</a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>



<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popPwFind'); 
});
</script>

</body>
</html>