<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup emailAgree-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>이메일 무단 수집 거부</h2>
		<div class="layer-cont">
			
			<div class="emailAgree-popWrap">
				<p class="txt-sub-info02">본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그 밖의 기술적 장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반시, 정보통신망법의해 형사처벌됨을 유념하시기 바랍니다. 이메일을 기술적 장치를 사용하여 무단으로 수집, 판매·유통하거나 이를 이용한 자는 [정보통신망이용촉진및정보보호등에관한법률] 제50조의2 규정에 의하여 1천만원 이하의 벌금형에 처해집니다.</p>
				<div class="agree-section">
					<section class="agree-section-content">
						<p>정보통신망법 제 50조의 2 (전자우편주소의 무단 수집행위 등 금지)</p>
						<p class="mtSt10">누구든지 전자우편주소의 수집을 거부하는 의사가 명시된 인터넷 홈페이지에서 자동으로 전자 우편주소를 수집하는 프로그램 그 밖의 기술적 장치를 이용하여 전자우편주소를 수집하여서는 아니 된다.</br> 누구든지 제1항의 규정을 위반하여 수집된 전자우편주소를 판매·유통하여서는 아니된다.</br>누구든지 제1항 및 제2항의 규정에 의하여 수집·판매 및 유통이 금지된 전자우편주소임을 알고 이를 정보전송에 이용하여서는 아니 된다.</p>
					</section>
				</div>
				<div class="btn-wrap">
					<a href="javasccript:;" class="btn lg fill">확인</a>
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
	layerPopup.popupOpenNow('.emailAgree-pop'); 
});
</script>
</body>
</html>