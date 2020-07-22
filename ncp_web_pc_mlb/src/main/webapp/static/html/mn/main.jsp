<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain mn home" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<!-- Visual  -->
				<%@ include file="../mn/inc_visual.jsp" %>


				<!-- 2019.02.20 추가 -->
				<section class="mds-section featured" style="padding-bottom:40px;">

					<div class="hdt"><span class="tit">FEATURED</span></div>

					<div class="frame" style="width:1560px; height:560px; margin:auto; position:relative;">

						<a href="javascript:layerPopup.popupOpenNow('#pop4dview_1');" style="position: absolute;left: 23px;top: 422px;z-index: 10;background: rgba(0,0,0,0.0);width: 160px;height: 50px;font-size: 0;">스토리</a>

						<a href="http://www.naver.com" style="position: absolute;right: 78px;top: 365px;z-index: 10;background: rgba(0,0,0,0.0);width: 160px;height: 50px;font-size: 0;">전체보기</a>

						<iframe src="https://4dview.co.kr/link/pfTBTiYzR0ORevsbnsfNGw" style="width:100%; height:100%;padding-bottom:1px" frameborder="0"></iframe>

					</div>

				</section>

				<article id="pop4dview_1" class="layer-popup pop4dview_1">
					<section class="layer-popup-cont" tabindex="0" style="width:auto;">
						<div class="layer-cont">
							<img src="/static/images/mn/FEATURED_ko.png" alt="">
						</div>
						<div class="layer-popup-close">
							<button type="button" class="d_layer_close">닫기</button>
						</div>
					</section>
				</article>


				<!-- New Arrival -->
				<%@ include file="../mn/inc_arrival.jsp" %>

				<!-- Olapic -->
				<%@ include file="../mn/inc_olapic.jsp" %>

				<!-- Shop  -->
				<%@ include file="../mn/inc_shop.jsp" %>

				<!-- LOOKBOOK  -->
				<%@ include file="../mn/inc_look.jsp" %>

				<!-- RANKING  -->
				<%@ include file="../mn/inc_rank.jsp" %>

				<!-- Launch Calendar  -->
				<%@ include file="../mn/inc_launch.jsp" %>

				<!-- Culture -->
				<%@ include file="../mn/inc_culture.jsp" %>

				<!-- Video -->
				<%@ include file="../mn/inc_video.jsp" %>
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
</script>
</body>
</html>