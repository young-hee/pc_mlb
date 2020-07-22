<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopGoodsInform" class="layer-popup lypopGoodsInform">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>상품입고알림 신청</h2>
		<div class="layer-cont ly-box">
		
			<p>상품이 입고되면 입력하신 휴대전화번호로 알림을 보내 드립니다.</p>		
			
			<table class="board-write">
				<colgroup>
					<col style="width:125px">
					<col style="width:px">							
				</colgroup>
				<tbody>
					<!-- loop -->
					<tr>
						<th><label for="ifrSize">사이즈</label></th>
						<td>
							<!-- select -->
							<div class="select-style01 d_select sm">
								<button type="button" id="ifrSize" class="d_select_sel" style="width:152px;"><span>F1</span></button>
								<ul>
									<li><a href="javascript:;">F1</a></li>
									<li><a href="javascript:;">F2</a></li>
								</ul>
							</div>
							<!-- //select -->	
						</td>						
					</tr>	
					<!-- // loop -->
					<tr>
						<th><label for="ifrName">이름</label></th>
						<td><input type="text" id="ifrName" class="input-style01 sm input_required textOnly" disabled="disabled" style="width:150px;" alt="이름" maxlength="100" value="홍길동"></td>						
					</tr>	
					<tr>
						<th><label for="ifrMb01">입고알림 수신<br>휴대전화번호</label></th>
						<td>
							<input type="text" id="ifrMb01" class="input-style01 sm numberOnly" style="width:65px;" maxlength="3">
							<span class="hyphen">-</span>
							<input type="text" class="input-style01 sm numberOnly" id="ifrMb02" style="width:75px;" maxlength="4">
							<span class="hyphen">-</span>
							<input type="text" class="input-style01 sm numberOnly" id="ifrMb03" style="width:75px;" maxlength="4">
						</td>						
					</tr>											
																								
				</tbody>
			</table>	
			
			<p class="txtIcon02 txtGray">입고알림은 신청순으로 발송되며, 재입고 후에도 품절이 될 수 있습니다.</p>					
			
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:;" class="btn w160">취소</a>
				<a href="javascript:;" class="btn fill w160">입고알림 신청</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopGoodsInform'); 
});
</script>
</body>
</html>