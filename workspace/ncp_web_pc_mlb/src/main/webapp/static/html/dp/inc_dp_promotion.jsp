<section class="mds-section promotion">
	<div class="hdt"><span class="tit">MLB PROMOTION</span></div>
	<div id="dpPromotionSlide" class="slide">

		<div class="swiper-container">
			<ul class="swiper-wrapper">
				<li class="swiper-slide">
					<div class="vs"><img src="/static/images/_temp/dp_promotion_img_1.png" alt="" usemap="#Map_vs1" class="vs-img"></div>
					<map name="Map_vs1" id="Map_vs1">
						<area shape="rect" coords="419,59,660,470" href="javascript:;" />
					</map>
				</li>
				<li class="swiper-slide">
					<div class="vs"><img src="/static/images/_temp/dp_promotion_img_1.png" alt="" usemap="#Map_vs2" class="vs-img"></div>
					<map name="Map_vs2" id="Map_vs2">
						<area shape="rect" coords="419,59,660,470" href="javascript:;" />
					</map>
				</li>
				<li class="swiper-slide">
					<div class="vs"><img src="/static/images/_temp/dp_promotion_img_1.png" alt="" usemap="#Map_vs3" class="vs-img"></div>
					<map name="Map_vs3" id="Map_vs3">
						<area shape="rect" coords="419,59,660,470" href="javascript:;" />
					</map>
				</li>
				<li class="swiper-slide">
					<div class="vs"><img src="/static/images/_temp/dp_promotion_img_1.png" alt="" usemap="#Map_vs4" class="vs-img"></div>
					<map name="Map_vs4" id="Map_vs4">
						<area shape="rect" coords="419,59,660,470" href="javascript:;" />
					</map>
				</li>
				<li class="swiper-slide">
					<div class="vs"><img src="/static/images/_temp/dp_promotion_img_1.png" alt="" usemap="#Map_vs5" class="vs-img"></div>
					<map name="Map_vs5" id="Map_vs5">
						<area shape="rect" coords="419,59,660,470" href="javascript:;" />
					</map>
				</li>
			</ul>
		</div>
		<div class="navigation">
			<button type="button" class="btnNav prev">이전</button>
			<button type="button" class="btnNav next">다음</button>
		</div>
	</div>

	<script>
	$( document ).ready(function() {
		dpPromotionSlide = new Swiper('#dpPromotionSlide .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			navigation: {
				nextEl: '#dpPromotionSlide .navigation .btnNav.next',
				prevEl: '#dpPromotionSlide .navigation .btnNav.prev'
			},
			autoplay:false,
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: true
		});
	});
	</script>	

</section>

