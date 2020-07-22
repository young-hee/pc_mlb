<section class="mds-section visual pm" id="pmVisual">
	<div class="swiper-container">
		<ul class="swiper-wrapper">
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/dp/mainVisual_pm_1.png" alt=""></a>
			</li>
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
		pmVisual = new Swiper('#pmVisual .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			pagination: {
				el: '#pmVisual .pagination',
				clickable: true
			},
			navigation: {
				nextEl: '#pmVisual .navigation .btnNav.next',
				prevEl: '#pmVisual .navigation .btnNav.prev'
			},
			autoplay: {
				delay: 10000,
				disableOnInteraction: false
			},
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: true
		});
	});
	</script>
</section>

