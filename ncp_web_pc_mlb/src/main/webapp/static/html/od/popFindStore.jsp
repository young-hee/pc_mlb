<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lyPopfindStore" class="layer-popup lyPopfindStore">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2>픽업매장 안내</h2><!-- mod 2018.12.17 -->
		<div class="layer-cont ly-box">
			<div class="lystoreMap"><div class="mapUrl"><!--  map --></div></div>
			<div class="lystoreInfo">
				<h3>역삼직영점</h3>
				<dl>
					<dt>주소</dt>
					<dd>서울시 강남구 역삼동 언주로 540, 1층  </dd>
				</dl>
				<dl>
					<dt>전화번호</dt>
					<dd>02-1250-4479 </dd>
				</dl>
				<dl>
					<dt>영업시간</dt>
					<dd>(평일) 10:30 ~ 20:00</dd>					
				</dl>
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lyPopfindStore'); 
});
</script>
</body>
</html>