<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopDvGuide" class="layer-popup lypopDvGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:851px">
		<h2>주문배송 절차 안내</h2>
		<div class="layer-cont ly-box">	
			<div class="dvStepBox">
				<ul>
					<li><p><strong>입금대기</strong>결제수단변경, 배송지 변경이 가능합니다. 주문 후 1일 이내에 입금하셔야 합니다.</p></li>
					<li><p><strong>결제완료</strong>주문의 결제를 완료하여, MLB에서 주문정보를 확인하는 단계입니다.</p></li>
					<li><p><strong>배송준비</strong>MLB에서 주문에 대한 배송을 준비중입니다.</p></li><!-- 2018.12.06 수정 -->			
					<li><p><strong>배송중</strong>주문하신 상품이 발송되어 고객님께 배송중 입니다.</p></li><!-- 2018.12.06 수정 -->	
					<li><p><strong>배송완료</strong>수령하신 상품을 구매확정 하시면 마일리지가 적립됩니다.</p></li><!-- 2018.12.06 수정 -->	
				</ul>			
			</div>	
			
			<!--  button -->
			<div class="lyBtnArea"><a href="javascript:;" class="btn fill w160">확인</a></div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopDvGuide'); 
});
</script>
</body>
</html>