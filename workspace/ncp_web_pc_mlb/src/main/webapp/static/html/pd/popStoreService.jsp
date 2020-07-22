<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopStoreService" class="layer-popup lypopStoreService">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>픽업서비스 제공 매장</h2>
		<div class="layer-cont ly-box scroll">
			<p>매장픽업 서비스는 MLB 온라인몰에서 상품을 구매하신 후, 매장에 방문하여 상품을 수령하는 서비스입니다.</p>
			
			<div class="storeServStep">
				<ul>
					<li><em>STEP.1</em>온라인에서<br>상품 주문</li>
					<li><em>STEP.2</em>[상품준비완료]<br>문자 수신</li>
					<li><em>STEP.3</em>매장 방문 및<br>상품 수령</li>					
				</ul>			
			</div>		

			<div class="store_list">
				<div class="hdt">픽업가능 매장목록</div>
				<ul class="list">
					<li>
						<div class="box">
							<span class="name">신세계면세 강남</span>
							<span class="addr">서울특별시 서초구 신반포로 176 1F MLB  신반포로 176 1F MLB </span>
							<div class="tel">02-2345-6789</div>
							<a href="javascript:;" class="btn gray sm btn-map">지도</a>
						</div>
					</li>
					<li>
						<div class="box">
							<span class="name">신세계면세 강남</span>
							<span class="addr">서울특별시 서초구 신반포로 176 1F MLB  신반포로 176 1F MLB </span>
							<div class="tel">02-2345-6789</div>
							<a href="javascript:;" class="btn gray sm btn-map">지도</a>
						</div>
					</li>
				</ul>
			</div>


			<div class="serviceTxt">
				<ul class="text-list02 txtTypeGray">
					<li>매장픽업 주문의 경우, 사은품을 제공하지 않습니다.</li>
					<li>주문완료와 동시에 주문이 접수되며, 상품준비완료 후, 매장에서 [상품준비완료 안내] 문자를 발송해 드립니다.</li>
					<li>매장픽업 주문 시간 및 매장의 재고 상황에 따라 당일 또는 3일 이내 매장픽업이 가능합니다.</li>
					<li>매장픽업 주문 상품이 준비완료 되었다는 알림(알림톡 또는 SMS) 수신 후, 3일(영업일 기준)까지 픽업하지 않으면, 주문이 자동 취소 됩니다.</li>
					<li>16시 이후 결제되는 주문은 당일 수령이 불가합니다.</li>
					<li>접수 완료 문자 수신 전에 매장을 방문하시면 상품수령이 불가할 수 있습니다.</li>
				</ul>
			</div>
			
			<h3 class="ly_tit">※ AS/교환/반품 관련 유의사항</h3>
			<div class="bgBox">
				<ul class="text-list02 txtTypeGray">
					<li>교환/반품을 원하실 경우, 온라인 쇼핑몰 또는 매장에서의 교환/반품이 가능합니다.</li>
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
	layerPopup.popupOpenNow('#lypopStoreService'); 
});
</script>
</body>
</html>