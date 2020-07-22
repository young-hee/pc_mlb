<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopCuponDown" class="layer-popup lypopCuponDown">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>쿠폰다운로드</h2>
		<div class="layer-cont ly-box">		
			
			<table class="board-list lyType01">
				<colgroup>
					<col style="width:125px">
					<col style="width:">
					<col style="width:110px">			
				</colgroup>
				<tbody>
					<!-- loop -->
					<tr>
						<td><span class="cuponImg"><em>상품 10%</em></span></th>
						<td class="selCuponInfo">
							<p class="cuponName">3월 쿠폰</p>
							<span class="cuponDate">2019.02.01 ~ 2019.02.20</span>
						</td>	
						<td class="selDown"><a href="#javascript:;" class="btn sm gray pd20">다운로드</a></td>
					</tr>	
					<!-- // loop -->
					<tr>
						<td><span class="cuponImg"><em>상품 5%</em></span></th>
						<td class="selCuponInfo">
							<p class="cuponName">홈페이지 리뉴얼 기념 ‘전지적 참가 리뉴얼 참여 이벤트’ 상품 쿠폰</p>
							<span class="cuponDate">2019.02.01 ~ 2019.02.20</span>
						</td>	
						<td class="selDown"><a href="#javascript:;" class="btn sm gray pd20">다운로드</a></td>
					</tr>
					<tr>
						<td><span class="cuponImg"><em>상품 15%</em></span></th>
						<td class="selCuponInfo">
							<p class="cuponName">리뉴얼 기념 ‘2019 S/S Collection’기념 상품 쿠폰</p>
							<span class="cuponDate">2019.02.01 ~ 2019.02.20</span>
						</td>	
						<td class="selDown"><a href="#javascript:;" class="btn sm gray pd20">다운로드</a></td>
					</tr>																				
				</tbody>
			</table>					
			
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
	layerPopup.popupOpenNow('#lypopCuponDown'); 
});
</script>
</body>
</html>