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
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">


				<div class="pm_top">
					<span class="tit">카카오 플러스친구 할인쿠폰 증정 이벤트</span>
					<div class="info">
						<div class="fl">
							<a href="javascript:;" class="btn fill sm btnAllEvent">ALL EVENT</a>
						</div>
						<div class="fr">
							<span class="date">2018.03.26 ~ 2018.04.26</span>
							<span class="share" id="btn_promotion_share">
								<ul class="list">
									<li class="weibo"><a href="javascript:;">웨이보</a></li>
									<li class="qq"><a href="javascript:;">QQ</a></li>
									<li class="facebook"><a href="javascript:;">페이스북</a></li>
									<li class="twitter"><a href="javascript:;">트위터</a></li>
									<li class="line"><a href="javascript:;">네이버라인</a></li>
									<!-- <li class="kakao"><a href="javascript:;">카카오톡</a></li> -->
									<li class="url"><a href="javascript:;">URL복사</a></li>
								</ul>
								<a class="bt" href="javascript:;" >공유</a>
							</span>
						</div>
					</div>
				</div>

	
				<section class="pm_vis_area">

					<div class="vs_html">
						
						<!-- 카카오 플러스친구 신규추가 이벤트 -->
						<style>
						.contain.dp.promo.view .pm_vis_area{ margin-bottom:0px; }
						.contain.dp.promo.view .pm_vis_area .vs_html{ max-width:inherit; }
						.event_kakao{  position:relative; overflow:hidden; text-align:left; background:#fdd303; }
						.event_kakao .imgs{ position:relative; left:50%; margin-left:-1280px; }
						.event_kakao .imgs img{ vertical-align:middle; }
						.event_kakao .btns{ position:absolute; width:1280px; left:50%; top:0px; margin-left:-640px;}
						.event_kakao .btns .btn_kakao{ position:absolute; left:213px; top:1654px; }
						</style>
						<div class="event_kakao">
							<div class="imgs"><img src="/static/images/ev/event_kakao_view.jpg" alt=""></div>
							<div class="btns"><a class="btn_kakao" href="https://pf.kakao.com/_zKVIxd/" target="_blank"><img src="/static/images/ev/event_kakao_view_btn.png" alt="MLB 친구추가+"></a></div>
						</div>
						<!-- 카카오 플러스친구 신규추가 이벤트 -->


					</div>
				</section>



			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>

</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
$(document).ready(function(){
	// 
});
</script>
</body>
</html>