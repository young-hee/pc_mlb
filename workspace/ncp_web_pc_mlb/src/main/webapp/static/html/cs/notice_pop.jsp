<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko" class=" js csstransforms csstransforms3d">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body isFlexBn mn home">
<div class="wrap" id="wrap">


<article id="noticepop" class="noticeView-pop" style="left:100px; top:100px;">
	<section class="layer-popupinner" tabindex="0">
		<div class="layer-cont scroll">
		
			<!-- 내용 -->
			<img class="vs-img" style="border-radius:100%; width:200px; height:200px;" src="https://dev-static.mlb-korea.com/images/display/category/MTP/A01/A01/contents/111_235_12_KOR_20181218164203.jpg/dims/resize/1920x850" alt="JULIE 테스트">
			테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트
			<!-- //내용 -->
		
		</div>
		<div class="popWrapbottom">
			<label class="check-skin">
				<input type="checkbox" id="" name="" checked="checked" />
				<span></span>
				<em>오늘은 그만보기</em>
			</label>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="notiClo()">닫기</button>
		</div>
	</section>
</article>

<div class="contain mn home">
<%@ include file="../mn/inc_visual.jsp" %>
<%@ include file="../mn/inc_arrival.jsp" %></div>
</div>
<script>
//공지사항닫기
function notiClo() {
	$(".d_layer_close").closest(".noticeView-pop").hide();
}
$(document).ready(function(){
	layerPopup.popupOpenNow('#noticepop');
	//공지사항 백스크롤휠 off
	if($(".wrap").find(".noticeView-pop")) {
		$('.contain').off('scroll touchmove mousewheel');
	}
	//공지팝업 이미지 사이즈만큼 넓이체크 20190124
	$(".noticeView-pop .layer-popupinner").width($(".noticeView-pop .layer-cont img").width());
});
</script>
</body>
</html>