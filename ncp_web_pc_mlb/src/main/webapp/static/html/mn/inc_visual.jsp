<section class="mds-section visual mn" id="mainVisual">
	<div class="swiper-container">
		<ul class="swiper-wrapper">
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/mainVisual_1.png" alt=""></a>
				<div class="desc sd1">
					<div class="tit">MEGA LOGO TREND  IS <br> GOING ON</div>
					<div class="txt">MLB는 2018 FW 시즌, 본래 의류에서만 주로 사용하던 <br>메가 사이즈 로고를 모자에 적용시켜 새로운 패션 아이템으로 <br>재해석한 메가로고 시리즈를 선보였습니다.</div>
					<div class="bts">
						<a href="javascript:;" class="btn xl btn-story">Story</a>
						<a href="javascript:;" class="btn xl fill btn-collect">Collection</a>
					</div>
				</div>
			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/1920_850_1.jpg" alt=""></a>
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
				

				<div class="desc sd1" style="z-index:20;top:270px;">
					<div class="tit">BIG BALL CHUNKY</div>
					<div class="txt">6센치 키높이와 업그레이드된 디자인의 어글리슈즈</div>
					<div class="bts">
						<a href="http://www.naver.com" class="btn xl btn-story">Story</a>
						<a href="http://www.daum.net" class="btn xl fill btn-collect">Collection</a>
					</div>
				</div>
				<div style="padding:45.3125% 0 0 0;position:relative; max-height:850px; overflow:hidden;">
					<div style="position:absolute;top:0;left:0;width:100%;height:100%;z-index:10;"></div>
					<iframe allowfullscreen="" frameborder="0" id="videoIframe" mozallowfullscreen="" src="https://player.vimeo.com/video/317919360?background=1" style="position:absolute;top:0;left:0;width:100%;height:100%;" webkitallowfullscreen=""></iframe>
				</div>
				<script src="https://player.vimeo.com/api/player.js"></script>


			</li>
			<li class="swiper-slide">
				<a href="javascript:;"><img class="vs-img" src="/static/images/mn/1920_850_2.jpg" alt=""></a>
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
	$(document).ready(function() {
		mainVisual = new Swiper('#mainVisual .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			autoHeight:true,
			pagination: {
				el: '#mainVisual .pagination',
				clickable: true
			},
			navigation: {
				nextEl: '#mainVisual .navigation .btnNav.next',
				prevEl: '#mainVisual .navigation .btnNav.prev'
			},
			autoplay: {
				delay: 60000,
				disableOnInteraction: false
			},
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: true
		});
	});

					$(document).ready(function() {
					   // var videoIframe = document.getElementById('videoIframe');
					   // var eventPlayer = new Vimeo.Player(videoIframe);


					   // mainVisual.on('slideChange', function(){

					   //    var idx = visualSwiper.activeIndex
					   //    if($('#videoIframe').parent().index()-1 == idx){
					   //       eventPlayer.play();
					   //    }else{
					   //       eventPlayer.pause();
					   //    }
					   // })

					});
	</script>
	
</section>

