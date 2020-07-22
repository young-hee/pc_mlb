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
	<div class="contain dp promo" id="contain">
		<div class="container">

			<main class="contents" id="contents">


				<div class="pm_top">
					<span class="tit">PROMOTION</span>
				</div>

				<!-- Visual  -->
				<%@ include file="../dp/inc_dp_visual_pm.jsp" %>

				
				<section class="pm_banner">
					<ul class="list">
						<li><a href="javascript:;"><img src="/static/images/_temp/banner_promo.png" alt=""></a></li>
						<li><a href="javascript:;"><img src="/static/images/_temp/banner_promo.png" alt=""></a></li>
					</ul>
				</section>

				<section class="mds-section pm_list_sec">
					
					<!-- 기획,이벤 탭 -->
					<div class="pm_filter_sort">
						<div class="sort">
							<ul class="list d_tab">
								<li class="on"><a href="javascript:;">ALL</a></li>
								<li><a href="javascript:;">EVENT</a></li>
								<li><a href="javascript:;">PROMOTION</a></li>
							</ul>
						</div>
						<div class="bts">
							<a href="/static/html/cs/noticeView.jsp" class="btn fill sm btnWinner">이벤트 당첨자 공지</a>
						</div>
					</div>	


					<div class="ui_pm_list">
						<ul class="list" id="item_list_area">
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_promo.jsp"><em class="bd">PROMOTION</em><span class="img"><img src="/static/images/_temp/pm_thumb_1.png" alt=""></span></a></div>
									<div class="info">
										<div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_event_kakao.jsp"><em class="bd">EVENT</em><span class="img"><img src="/static/images/_temp/banner_kakao.jpg" alt=""></span></a></div>
									<div class="info">
										<div class="name">카카오 플러스친구 할인쿠폰 증정 이벤트</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_promo.jsp"><em class="bd">PROMOTION</em><span class="img"><img src="/static/images/_temp/goods_thumb_7.png" alt=""></span></a></div>
									<div class="info">
										<div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_event.jsp"><em class="bd">EVENT</em><span class="img"><img src="/static/images/_temp/pm_thumb_2.png" alt=""></span></a></div>
									<div class="info">
										<div class="name">2018 PRIMIUM DOWN </div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_promo.jsp"><em class="bd">PROMOTION</em><span class="img"><img src="/static/images/_temp/pm_thumb_1.png" alt=""></span></a></div>
									<div class="info">
										<div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="/static/html/dp/pm_view_event.jsp"><em class="bd">EVENT</em><span class="img"><img src="/static/images/_temp/pm_thumb_2.png" alt=""></span></a></div>
									<div class="info">
										<div class="name">2018 PRIMIUM DOWN </div>
									</div>
								</div>
							</li>
						</ul>
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
	
	appendStat = true ;

	addItemFnc = function(){
		console.log("addItemFnc");	
		$.ajax({
			type: "post",
			url: "/static/html/dp/pm_list_more.jsp",
			dataType: "html",
			success: function(html) {
				window.setTimeout(function(){
					$("#item_list_area").append(html);
					appendStat = true;
				},500);
			}
		});	
	};

	$("#wrap").on("scroll load", function() {
		var sct = $("#wrap").scrollTop() + $("#wrap").height() - $(".foot").height();
		var cnt = $(".contain").outerHeight();
		// console.log(cnt,sct);
		if (cnt <= sct && appendStat == true) {
			console.log("바닥");
			appendStat = false;
			addItemFnc();			
		}
	});
});
</script>
</body>
</html>