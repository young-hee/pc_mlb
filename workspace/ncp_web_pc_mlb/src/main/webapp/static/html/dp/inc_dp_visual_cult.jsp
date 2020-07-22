<section class="mds-section visual cult" id="cultVisual">
	<div class="swiper-container">
		<ul class="swiper-wrapper">
<!-- 			<li class="swiper-slide">
				<div class="vs-video"><iframe class="video_iframe" id="video_iframe_0" src="https://www.youtube.com/embed/peewlTu4FKA?rel=0&enablejsapi=1" frameborder="0" allowfullscreen=""></iframe></div>
			</li> -->
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/dp/mainVisual_pm_1.png" alt=""></a>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/dp/mainVisual_pm_1.png" alt=""></a>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/dp/mainVisual_pm_1.png" alt=""></a>
			</li>
		</ul>
	</div>
	<div class="pagination"></div>
	<div class="navigation">
		<button type="button" class="btnNav prev">이전</button>
		<button type="button" class="btnNav next">다음</button>
	</div>
	
	<script>
	$( document ).ready(function() {
		cultVisual = new Swiper('#cultVisual .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			pagination: {
				el: '#cultVisual .pagination',
				clickable: true
			},
			navigation: {
				nextEl: '#cultVisual .navigation .btnNav.next',
				prevEl: '#cultVisual .navigation .btnNav.prev'
			},
			// autoplay: {
			// 	delay: 10000,
			// 	disableOnInteraction: false
			// },
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: true
		});
	});
	</script>
</section>

