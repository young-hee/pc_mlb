<section class="mds-section arrival">
	<div class="hdt"><span class="tit">NEW ARRIVAL</span></div>

	<div id="mainNewArrival" class="slide">
		<div class="swiper-container">
			<ul class="swiper-wrapper">
				<li class="swiper-slide">



					<div class="vs" style="position:relative;">
						<div style="position: absolute; left: 20px; top: 100px;"><img height="94" src="https://static.mlb-korea.com/images/display/category/MTP/A01/A01/contents/15.KakaoTalk_20190218_143832977.gif" width="230"></div>
						<img src="https://static.mlb-korea.com/images/display/category/MTP/A01/A01/contents/1.New-Arrival_gif.png" alt=""  class="vs-img">
						<a href="http://www.naver.com" style="position: absolute;left: 20px;top: 436px;z-index: 10;background: rgba(0,0,0,0.0);width: 160px;height: 50px;font-size: 0;">링크</a>
					</div>



				</li>
				<li class="swiper-slide">
					<div class="vs">
						<a href="http://www.naver.com"><img src="/static/images/_temp/mainArrival_1.png" alt="" class="vs-img"></a><!-- NEW ARRIVAL  링크 추가 -->
					</div>
				</li>
				<li class="swiper-slide">
					<div class="vs">
						<img src="/static/images/_temp/mainArrival_1.png" usemap="#image-map">
						<map name="image-map">
						    <area target="" alt="" title="" href="javascript:layerPopup.popupOpenNow('#popNewArrival_1');" coords="22,450,181,502" shape="rect">
						</map>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="vs">
						<img src="/static/images/_temp/mainArrival_1.png" alt="" class="vs-img">
						<a href="javascript:layerPopup.popupOpenNow('#popNewArrival_1');" style="position: absolute;left: 20px;top: 449px;z-index: 10;background: rgba(0,0,0,0.0);width: 160px;height: 50px;font-size: 0;">링크</a>
					</div>
				</li>
			</ul>
		</div>
		<div class="pagination"></div>
		<div class="navigation">
			<button type="button" class="btnNav prev">이전</button>
			<button type="button" class="btnNav next">다음</button>
		</div>
	</div>

	<script>
	$( document ).ready(function() {
		mainNewArrival = new Swiper('#mainNewArrival .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			pagination: {
				el: '#mainNewArrival .pagination',
				clickable: true
			},
			navigation: {
				nextEl: '#mainNewArrival .navigation .btnNav.next',
				prevEl: '#mainNewArrival .navigation .btnNav.prev'
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


