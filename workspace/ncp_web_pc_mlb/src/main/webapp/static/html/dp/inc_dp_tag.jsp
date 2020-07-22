<section class="mds-section tag">
	<div class="hdt"><span class="tit">#SHOPPING TAG</span></div>

	<div id="dpTagSlide" class="slide">
		<div class="swiper-container">
			<ul class="swiper-wrapper list">
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_1.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_1_on.png" alt=""></span>
						</a>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_2.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_2_on.png" alt=""></span>
						</a>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_3.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_3_on.png" alt=""></span>
						</a>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_1.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_1_on.png" alt=""></span>
						</a>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_2.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_2_on.png" alt=""></span>
						</a>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<a href="javascript:;" class="thumb">
							<span class="img"><img src="/static/images/_temp/dp_tag_img_3.png" alt=""></span>
							<span class="img_on"><img src="/static/images/_temp/dp_tag_img_3_on.png" alt=""></span>
						</a>
					</div>
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
		dpTagSlide = new Swiper('#dpTagSlide .swiper-container', {
			slidesPerView: 'auto',
			observer: true,
			observeParents: true,
			watchOverflow:true,
			freeMode:true,
			spaceBetween:20,
			navigation: {
				nextEl: '#dpTagSlide .navigation .btnNav.next',
				prevEl: '#dpTagSlide .navigation .btnNav.prev'
			},
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: false
		});
	});
	</script>	
</section>

