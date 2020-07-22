<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopOdAllCancel" class="layer-popup lypopOdAllCancel">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>주문 전체취소</h2>
		<div class="layer-cont ly-box">
		
			<!-- <h3 class="ly_tit mt0">주문전체 취소</h3>  2018.12.06 사가제 -->
			<table class="board-write selMgSm">
				<caption>주문전체 취소</caption>
				<colgroup>
					<col style="width:125px">
					<col style="width:px">							
				</colgroup>
				<tbody>
					<tr>
						<th>주문일</th>
						<td>2018-06-16 17:53:55</td>					
					</tr>
					<tr>
						<th>주문번호</th>
						<td>OD201803167505666</td>					
					</tr>
				</tbody>
			</table>
		
			<h3 class="ly_tit">환불계좌 정보</h3>			
			<table class="board-write selMgSm">
				<caption>환불계좌 정보</caption>
				<colgroup>
					<col style="width:125px">
					<col style="width:px">							
				</colgroup>
				<tbody>
					<!-- loop -->
					<tbody>
                   	<tr>
                   		<th><label for="bankName">은행명</label> <span class="required">*</span></th>
                   		<td>
							<!-- select -->
							<div class="select-style01 d_select" style="width:100%;">
								<button type="button" id="bankName" class="d_select_sel" style="width:100%;"><span>선택하세요</span></button>
								<ul>
									<li><a href="javascript:;">선택하세요</a></li>													
								</ul>
							</div>
							<!-- //select -->		                          		
                   		</td>		                          		
                   	</tr>
                   	<tr>
                   		<th><label for="bankTo">예금주</label><span class="required">*</span></th>
                   		<td><input type="text" id="bankTo" class="input-style01 sm textOnly" style="width:100%;" alt="은행명" maxlength="100"></td>		                          		
                   	</tr>
                   	<tr>
                   		<th><label for="bankNumber">계좌번호</label><span class="required">*</span></th>
                   		<td><input type="text" id="bankNumber" class="input-style01 sm textOnly" style="width:100%;" alt="계좌번호" maxlength="100" placeholder="‘-’을 제외하고 입력해주세요."></td>		                          		
                   	</tr>																	
				</tbody>
			</table>	
				
			
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:;" class="btn w160">취소</a>
				<a href="javascript:;" class="btn fill w160">주문취소</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopOdAllCancel'); 
});
</script>
</body>
</html>