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
	<div class="contain dp look view" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<section class="mds_look_veiw">
					
					<div class="page_nav">
						<span class="tit"><a href="javascript:;">LOOKBOOK</a></span>
						<div class="navigation">
							<button type="button" class="btnNav prev">이전</button>
							<button type="button" class="btnNav next">다음</button>
						</div>
						<div class="bts"><a href="/static/html/dp/look_list.jsp" class="btn sm gray btnGoList">목록보기</a></div>
					</div>
					
					<div class="slide" id="slideLookbook">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<div class="swiper-slide look">
									<%@ include file="../dp/inc_look_slide1.jsp" %>
								</div>
								<div class="swiper-slide look no_prds"> <!-- no_prds -->
									<%@ include file="../dp/inc_look_slide2.jsp" %> 
								</div>
								<div class="swiper-slide look">
									<%@ include file="../dp/inc_look_slide1.jsp" %>
								</div>
								<div class="swiper-slide look">
									<%@ include file="../dp/inc_look_slide2.jsp" %>
								</div>
							</div>
						</div>
						<div class="navigation">
							<button type="button" class="btnNav prev">이전</button>
							<button type="button" class="btnNav next">다음</button>
						</div>
					</div>

				</section>
				<script>
				
				$(document).ready(function() {
					
					watchNoPrdFnc = function(rIndex){
						var nIndex = $(".mds_look_veiw .slide .swiper-slide.no_prds").data("swiper-slide-index");
						console.log( rIndex , nIndex );
						if( rIndex == nIndex  ) {
							console.log("no_prds");
							$(".mds_look_veiw .slide").addClass("no_prds");
						}else{
							$(".mds_look_veiw .slide").removeClass("no_prds");
						}
						$(".mds_look_veiw .slide").addClass("trans");
						
					}
					if ( $(".mds_look_veiw .slide .look").length <= 1 ){
						$(".mds_look_veiw .slide .navigation").hide();
					}else{
						// http://idangero.us/swiper/api/
						slideLookbook = new Swiper('#slideLookbook .swiper-container', {
							slidesPerView: 1,
							observer: true,
							observeParents: true,
							watchOverflow:true,
							autoHeight:true,
							threshold:20,
							navigation: {
								nextEl: '.mds_look_veiw .navigation .btnNav.next',
								prevEl: '.mds_look_veiw .navigation .btnNav.prev'
							},
							preloadImages: false,
						    lazy: true,
							loop: true,
							on:{
								slideChangeTransitionEnd:function(){
									$("#wrap").scrollTop(0);
									$(".mds_look_veiw .slide .look .sldPan .prds").scrollTop(0);
									var realIndex = $(".mds_look_veiw .slide .swiper-slide.swiper-slide-active").data("swiper-slide-index");
									watchNoPrdFnc(realIndex);
								},
								touchStart:function(){
									$(".mds_look_veiw .slide").removeClass("trans");
								}
							}
						});
					}
				});
				
				</script>

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