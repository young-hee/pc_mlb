<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lyPopSample4" class="layer-popup lyPopSample4">
	<section class="layer-popup-cont" tabindex="0">
		<h2>레이어팝업 내부 스크롤</h2>
		<div class="layer-cont scroll">
				
			<%@ include file="../_inc/paging.jsp" %>

			<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	
			<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	<br>내용	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lyPopSample4'); 
});
</script>
</body>
</html>