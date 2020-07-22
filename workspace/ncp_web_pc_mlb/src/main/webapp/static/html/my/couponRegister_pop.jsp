<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup couponRegister-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>쿠폰등록</h2>
		<div class="layer-cont scroll">
			
			<div class="couponRegister-popWrap">
			
				<!-- 검색S -->
				<div class="mileageFindSrch">
					<input type="text" placeholder="쿠폰번호를 입력해 주세요." class="input-style02">
					<a href="javascript:;" class="btn sm">쿠폰등록</a>
				</div>
				<!-- //검색E -->
				
				<ul class="text-list02">
					<li>등록된 쿠폰은 사용 가능한 쿠폰 목록에서 확인할 수 있습니다.</li>
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
	layerPopup.popupOpenNow('.couponRegister-pop'); 
});
</script>
</body>
</html>