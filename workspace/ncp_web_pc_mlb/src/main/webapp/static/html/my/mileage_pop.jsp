<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup mileage-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>멤버십 마일리지 카드 등록</h2>
		<div class="layer-cont scroll">
			
			<div class="mileage-popWrap">
			
				<ul class="text-list02">
					<li>멤버십 카드 등록으로 매장에서 적립한 마일리지를  온라인에서 사용하세요.</li>
				</ul>
				
				<!-- 검색S -->
				<div class="mileageFindSrch">
					<input type="text" placeholder="카드번호를 입력해주세요." class="input-style02">
					<a href="javascript:;" class="btn sm">카드등록</a>
					<div>
						<input type="text" placeholder="인증번호" class="input-style02">
						<p>(인증번호가 있는 경우 입력)</p>
					</div>
				</div>
				<!-- //검색E -->
				
				<ul class="text-list02">
					<li>등록하신 멤버십 카드의 마일리지는 <span class="text-color01">F&F 통합 멤버십의 마일리지로 전환</span>되며, 해당 카드는 이후 매장에서 사용할 수 없습니다.</li>
					<li>온라인 멤버십 번호는 모바일을 통해 마이페이지에 접속 하시면 확인할 수 있습니다.</li>
				</ul>
				
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.mileage-pop'); 
});
</script>
</body>
</html>