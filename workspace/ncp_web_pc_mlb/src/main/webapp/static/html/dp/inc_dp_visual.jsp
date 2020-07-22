<section class="mds-section visual d1" id="dpVisual">
	<div class="swiper-container">
		<ul class="swiper-wrapper">
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/mainVisual_1.png" alt=""></a>
				<div class="desc sd1">
					<div class="tit">MEGA LOGO</div>
					<div class="txt">JUST ARRIVED.</div>
					<div class="bts">
						<a href="javascript:;" class="btn xl btn-story">Story</a>
						<a href="javascript:;" class="btn xl fill btn-collect">Collection</a>
					</div>
				</div>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/mainVisual_2.png" alt=""></a>
				<div class="desc sd2">
					<div class="tit">MEGA LOGO <br>TREND  IS <br> GOING ON</div>
					<div class="txt">MLB는 2018FW 시즌,본래 의류에서만 주로 사용하던 <br>메가 사이즈로고 모자에 ㅔ적용시켜  새로운 패션 아이템으로 <br> 재해석한 메가로고 시리즈를 선보였습니다.</div>
				</div>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/mainVisual_1.png" alt=""></a>
				<div class="desc sd1">
					<div class="tit">MEGA LOGO</div>
					<div class="txt">JUST ARRIVED.</div>
					<div class="bts">
						<a href="javascript:;" class="btn xl btn-story">Story</a>
						<a href="javascript:;" class="btn xl fill btn-collect">Collection</a>
					</div>
				</div>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/mainVisual_2.png" alt=""></a>
				<div class="desc sd2">
					<div class="tit">MEGA LOGO <br>TREND  IS <br> GOING ON</div>
					<div class="txt">MLB는 2018FW 시즌,본래 의류에서만 주로 사용하던 <br>메가 사이즈로고 모자에 ㅔ적용시켜  새로운 패션 아이템으로 <br> 재해석한 메가로고 시리즈를 선보였습니다.</div>
				</div>
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
		dpVisual = new Swiper('#dpVisual .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			pagination: {
				el: '#dpVisual .pagination',
				clickable: true
			},
			watchOverflow:true,
			navigation: {
				nextEl: '#dpVisual .navigation .btnNav.next',
				prevEl: '#dpVisual .navigation .btnNav.prev'
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

