<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopChgGuide" class="layer-popup lypopChgGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:851px">
		<h2>교환/반품 절차 안내</h2>
		<div class="layer-cont ly-box d_tab02">	
			<ul class="tab-type01">
				<li class="d_tab02_select on"><a href="javascript:;">교환절차안내</a></li>
				<li class="d_tab02_select"><a href="javascript:;">반품절차안내</a></li>				
			</ul>
			<!-- 교환절차안내 -->
			<div class="d_tab02_cont" style="display:block">
				<div class="dvStepBox d_tab02">
					<ul>
						<li><p><strong>교환신청</strong>교환 신청 페이지에서 옵션변경 및 사유 선택후 신청해주시기바랍니다.</p></li>
						<li><p><strong>교환접수</strong>교환신청이 접수되어 택배사에 교환 수거를 요청해드립니다.</p></li>
						<li><p><strong>교환상품 수거/확인</strong>CJ택배 기사님이 상품 을 수거해 갑니다. MLB에서 수거된 상품의 상태를 확인합니다.</p></li>					
						<li><p><strong>교환상품 발송</strong>교환된 새로운 상품을 배송합니다</p></li>
						<li><p><strong>교환완료</strong>교환상품을 수령하면 교환이 완료됩니다.</p></li>
					</ul>			
				</div>	
			</div>
			<!-- //교환절차안내 -->
			<!-- 반품절차안내  -->
			<div class="d_tab02_cont">
				<div class="dvStepBox d_tab02 step04">
					<ul>
						<li><p><strong>반품신청</strong>반품 신청 페이지에서 사유 선택 및 입력후 신청해주시기 바랍니다.</p></li>
						<li><p><strong>반품접수</strong>반품신청이 접수되어 택배 사에 반품수거를 요청해 드립니다.</p></li>
						<li><p><strong>반품 수거/확인</strong>CJ택배 기사님이 상품을 수거해 갑니다. MLB에서 수거된 상품의 상태를 확인합니다.</p></li>					
						<li><p><strong>반품완료</strong>환불처리가 완료된 후 반품완료 처리됩니다.</p></li>						
					</ul>			
				</div>	
			</div>	
			<!--// 반품절차안내  -->		
			
			
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
	layerPopup.popupOpenNow('#lypopChgGuide'); 
});
</script>
</body>
</html>