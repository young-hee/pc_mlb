<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup agreeMent-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>개인정보수집 및 이용에 대한 동의</h2>
		<div class="layer-cont">
			
			<div class="agreeMent-popWrap">
				<ul class="text-list02">
					<li>개인정보보호법 제15조 2항에 의한 수집,이용 동의</li>
					<li>이용목적 : MLB 최신정보를 제공하기 위하여 사용</li>
					<li>수집항목 : 이메일</li>
					<li>보유 및 이용기간 : 수신 둉의 거부 시, 즉시 삭제</li>
				</ul>
				<div class="btn-wrap">
					<a href="javascript:;" class="btn lg fill">확인</a>
				</div>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.agreeMent-pop'); 
});
</script>
</body>
</html>