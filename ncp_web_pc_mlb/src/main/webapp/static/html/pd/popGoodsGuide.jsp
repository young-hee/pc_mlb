<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopGoodsGuide" class="layer-popup lypopGoodsGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>배송/교환/반품/환불</h2>
		<div class="layer-cont ly-box scroll">
			
			<!-- 배송안내  -->
			<h3 class="ly_tit mt0">배송안내</h3>
			<table class="board-list lyType01">
				<colgroup>
					<col style="width:118px">
					<col style="width:">			
				</colgroup>
				<tbody>
					<tr>
						<th>배송업체</th>
						<td>CJ대한통운택배</td>	
					</tr>
					<tr>
						<th>배송정보</th>
						<td>
							평균 결제완료일 기준 2~5일 소요. (토/일/공휴일 제외)<br>
							오전 9시 이전 결제완료 건에 한해 당일 출고<br>
							(단, 물류, 택배사 사정에 따라 차이가 날 수 있습니다.)
						</td>
					</tr>
					<tr>
						<th>배송비</th>
						<td>
							30,000원 이상 결제 시 무료배송<br>
							30,000원 미만 결제 시 2,500원<br>
							* 온라인몰 구매 시 선물 포장 및 쇼핑백 동봉 불가합니다.						
						</td>
					</tr>					
				</tbody>
			</table>
			<!-- // 배송안내 -->
			
			<!-- 교환/반품 안내  -->
			<h3 class="ly_tit">교환/반품 안내</h3>
			<table class="board-list lyType01">
				<colgroup>
					<col style="width:118px">
					<col style="width:">			
				</colgroup>
				<tbody>
					<tr>
						<th>신청기간</th>
						<td>신청 기간 : 상품 수령 후 7일 이내</td>	
					</tr>
					<tr>
						<th>교환/반품 방법</th>
						<td>
							<ul class="text-list02 txtTypeGray">
								<li>배송완료 7일 이내</li>
								<li>마이페이지 > 취소/교환/반품 신청 선택 후 상세 주문내역에서 반품/교환 버튼선택</li>
								<li>교환/ 반품 접수 페이지에서 택배비 결제(선불지급 및 상품과 함께 동봉 불가)</li>
								<li>접수 후 3일 이내 택배기사님이 수거 방문 예정</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th>교환/반품 배송비</th>
						<td>
							<ul class="text-list02 txtTypeGray">
								<li>단순 변심, 사이즈 착오 등 고객에게 귀책 사유가 있는 경우, 배송비는 고객 부담. (교환/전체 반품시 5,000원, 부분 반품시 2,500원)</li>
								<li>상품불량 및 오배송 등의 이유로 교환/반품하실 경우, 배송비는 본사 부담.</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th>반품/교환<br>불가사유</th>
						<td>
							<ul class="text-list02 txtTypeGray">
								<li>교환/반품 요청이 상품 수령하신 날부터 7일을 경과한 경우</li>
								<li>상품 및 구성품 분실 및 취급부주의로 인한 파손/고장/오염된 경우. (착용흔적이나 세탁, 수선, 오염, 택 또는 라벨 제거 및 훼손)</li>
								<li>신발, 잡화 등의 상품 포장, 케이스의 멸실 또는 훼손의 경우</li>
								<li>시간의 경과에 의하여 재판매가 곤란한 경우</li>
								<li>구매확정 된 경우(배송완료일로부터 7일 이후 자동 구매 확정됩니다)</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th>기타</th>
						<td>
							<ul class="text-list02 txtTypeGray">
								<li>교환/환불은 반송제품 확인 후 진행되며 맞교환은 불가합니다</li>
								<li>교환의 경우 물류센터 도착일 기준의 재고를 파악하여, 교환 처리되며 품절로 인해 취소처리 될 수 있습니다.</li>
							</ul> 
						</td>
					</tr>
				</tbody>
			</table>
			<!-- // 교환/반품 안내 -->			
			
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
	layerPopup.popupOpenNow('#lypopGoodsGuide'); 
});
</script>
</body>
</html>