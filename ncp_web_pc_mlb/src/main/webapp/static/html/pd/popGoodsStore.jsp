<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopGoodsStore" class="layer-popup lypopGoodsStore">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2>상품픽업 매장선택</h2>
		<div class="layer-cont ly-box scroll">
		
			<div class="searchStoreBox">
				<dl>
					<dt><label for="">지역</label></dt>
					<dd>
						<!-- select -->
						<div class="select-style01 d_select sm">
							<button type="button" id="" class="d_select_sel" style="width:100px;"><span>전체</span></button>
							<ul>
								<li><a href="javascript:;">전체</a></li>								
							</ul>
						</div>
						<!-- //select -->
					</dd>
					<dt><label for="">매장구분</label></dt>
					<dd>
						<!-- select -->
						<div class="select-style01 d_select sm">
							<button type="button" id="" class="d_select_sel" style="width:100px;"><span>전체</span></button>
							<ul>
								<li><a href="javascript:;">전체</a></li>								
							</ul>
						</div>
						<!-- //select -->
					</dd>
					<dt><label for="stName">매장명</label></dt>
					<dd>
						<input type="text" id="stName" class="input-style01 sm input_required textOnly" style="width:318px;" alt="매장명" maxlength="100">
						<a href="javascript:;" class="btn sm pd20">검색</a>
					</dd>										
				</dl>
			</div>	
				
			<div class="storeList">
				<p class="total">총 <strong>105</strong>건</p>
				<table class="board-list lyType02">
					<colgroup>
						<col style="width:50px">
						<col style="width:140px">
						<col style="width:">
						<col style="width:100px">
						<col style="width:100px">											
					</colgroup>
					<thead>
						<tr>
							<th>선택</th>
							<th>매장명</th>
							<th>주소</th>
							<th>전화번호</th>
							<th>영업시간</th>
						</tr>
					</thead>
					<tbody>
						<!-- loop -->
						<tr>
							<td>
								<span class="rdo-skin"><input type="radio" id="storeSel01" name="orderStSelect" checked="checked"><span></span></span>
		                    	<label for="storeSel01" class="orderPayOptSel txtNone">선택</label>
							</td>	
							<td>현대면세 무역센터점</td>
							<td class="txtLeft">서울시 강남구 영동대로 82길 19 항진빌딩 F10 MLB</td>
							<td>02-2142-6427</td>
							<td>09:00 ~ 20:30</td>
						</tr>
						<!-- // loop -->	
						<tr>
							<td>
								<span class="rdo-skin"><input type="radio" id="storeSel02" name="orderStSelect" checked="checked"><span></span></span>
		                    	<label for="storeSel02" class="orderPayOptSel txtNone">선택</label>
							</td>	
							<td>신세계면세 강남</td>
							<td class="txtLeft">서울특별시 서초구 신반포로 176 1F MLB</td>
							<td>02-2142-6427</td>
							<td>09:00 ~ 20:30</td>
						</tr>	
						<tr>
							<td>
								<span class="rdo-skin"><input type="radio" id="storeSel03" name="orderStSelect" checked="checked"><span></span></span>
		                    	<label for="storeSel03" class="orderPayOptSel txtNone">선택</label>
							</td>	
							<td>홍대 직영점</td>
							<td class="txtLeft">서울특별시 마포구 와우산로 90(서교동)</td>
							<td>02-2142-6427</td>
							<td>09:00 ~ 20:30</td>
						</tr>	
						<tr>
							<td>
								<span class="rdo-skin"><input type="radio" id="storeSel04" name="orderStSelect" checked="checked"><span></span></span>
		                    	<label for="storeSel04" class="orderPayOptSel txtNone">선택</label>
							</td>	
							<td>롯데아울렛 군산</td>
							<td class="txtLeft">전북 군산시 조촌로 130 (롯데몰 군산점 2F)</td>
							<td>02-2142-6427</td>
							<td>09:00 ~ 20:30</td>
						</tr>	
						<tr>
							<td>
								<span class="rdo-skin"><input type="radio" id="storeSel05" name="orderStSelect" checked="checked"><span></span></span>
		                    	<label for="storeSel05" class="orderPayOptSel txtNone">선택</label>
							</td>	
							<td>신세계면세 강남</td>
							<td class="txtLeft">서울시 강남구 영동대로 82길 19 항진빌딩 F10 MLB</td>
							<td>02-2142-6427</td>
							<td>09:00 ~ 20:30</td>
						</tr>	
						<tr>
							<td colspan="5">
								<!-- 검색 결과 없을경우 -->
								<div class="selNoResult">검색결과가 없습니다.</div>
							</td>
						</tr>			
																									
					</tbody>
				</table>	
			
				<!--  page List -->
				<%@ include file="../_inc/paging.jsp" %>
				<!-- // page List -->
			</div>
			
			<ul class="text-list02 txtTypeGray">				
				<li>매장픽업 주문의 경우, 사은품을 제공하지 않습니다.</li>
				<li>주문완료와 동시에 주문이 접수되며, 상품준비완료 후, 매장에서 [상품준비완료 안내] 문자를 발송해 드립니다.</li>
				<li>매장픽업 주문 시간 및 매장의 재고 상황에 따라 당일 또는 3일 이내 매장픽업이 가능합니다.</li>
				<li>매장픽업 주문 상품이 준비완료 되었다는 알림(알림톡 또는 SMS) 수신 후, 3일(영업일 기준)까지 픽업하지 않으면, 주문이 자동 취소 됩니다.</li>
				<li>반드시 [상품준비완료 안내] 문자를 수신하신 후, 방문하시거나, 해당 매장과 통화하신 후, 방문하시기 바랍니다.</li>
				<li>16시 이후 결제되는 주문은 당일 수령이 불가합니다.</li>
				<li>접수 완료 문자 수신 전에 매장을 방문하시면 상품수령이 불가할 수 있습니다.</li>		
			</ul>
				
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:;" class="btn w160">취소</a>
				<a href="javascript:;" class="btn fill w160">선택</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopGoodsStore'); 
});
</script>
</body>
</html>