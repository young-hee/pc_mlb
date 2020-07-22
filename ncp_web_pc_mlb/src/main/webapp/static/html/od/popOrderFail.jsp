<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lyPopOrderFail" class="layer-popup lyPopOrderFail">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2>주문실패</h2>
		<div class="layer-cont ly-box">
			<div class="mgBoxSy01">주문 처리에 실패하여<br>주문을 완료하지 못했습니다.</div>
			<div class="mgBoxSy02">재시도를 하시거나 계속 발생하는 경우 <br>고객센터(080-807-0012)로 문의 주시기 바랍니다.<br><span class="txtTime">(평일 오전 9시 ~ 오후 6시: 토/일 공휴일 휴무)</span></div>
			<div class="lyBtnArea">
				<a href="/static/html/mn/main.jsp" class="btn">홈으로 가기</a>
				<a href="cart.jsp" class="btn">장바구니 가기</a>
				<a href="order_write_mb.jsp" class="btn fill">다시 주문하기</a>
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lyPopOrderFail'); 
});
</script>
</body>
</html>