<div class="area center">
	<div class="photos"> 
		<div id="pdPhotoSlide" class="pdPhoto">
			<div class="swiper-container">
				<ul class="swiper-wrapper list">
					<li class="mov swiper-slide">
						<div class="item">
							<video class="video" controls muted loop  disablepictureinpicture="" controlslist="nodownload">
								<source src="/static/images/_temp/video.mp4" type="video/mp4">
							</video>
						</div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/_temp/pd_img_1.png" data-big="/static/images/_temp/pd_img_1_big.png" alt=""></a></div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/cm/no_img_600.png" data-big="/static/images/cm/no_img_600.png" alt=""></a></div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/_temp/pd_img_3.png" data-big="/static/images/_temp/pd_img_3.png" alt=""></a></div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/_temp/pd_img_4.png" data-big="/static/images/_temp/pd_img_4.png" alt=""></a></div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/_temp/pd_img_5.png" data-big="/static/images/_temp/pd_img_5.png" alt=""></a></div>
					</li>
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);"><img src="/static/images/_temp/pd_img_6.png" data-big="/static/images/_temp/pd_img_6.png" alt=""></a></div>
					</li>
				</ul>
			</div>
			<div class="navigation">
				<button type="button" class="btnNav prev">이전</button>
				<button type="button" class="btnNav next">다음</button>
			</div>
		</div>
		<div id="pdThumbSlide" class="pdThumb">
			<div class="swiper-container">
				<ul class="swiper-wrapper list">
					<li class="mov swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/pd/mov_img.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/_temp/pd_img_1.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/cm/no_img_100.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/_temp/pd_img_3.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/_temp/pd_img_4.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/_temp/pd_img_5.png" alt=""></a></li>
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="/static/images/_temp/pd_img_6.png" alt=""></a></li>
				</ul>
			</div>
			<div class="navigation">
				<button type="button" class="btnNav prev">이전</button>
				<button type="button" class="btnNav next">다음</button>
			</div>
		</div>
	</div>

	<script>
	$( document ).ready(function() {

	    // pdPhotoSlide = new Swiper('#pdPhotoSlide .swiper-container', {
	    //   spaceBetween: 10,
	    //   loop:true,
	    //   loopedSlides: 5,
	    //   effect:'fade',
	    //   navigation: {
	    //     nextEl: '#pdThumbSlide .btnNav.next',
	    //     prevEl: '#pdThumbSlide .btnNav.prev',
	    //   }

	    // });
	    // pdThumbSlide = new Swiper('#pdThumbSlide .swiper-container', {
	    //   spaceBetween: 10,
	    //   slidesPerView: 5,
	    //   spaceBetween:5,
	    //   touchRatio: 0.2,
	    //   loop: true,
	    //   loopedSlides: 5,
	    //   slideToClickedSlide: true,
	    // });
	    // pdPhotoSlide.controller.control = pdThumbSlide;
	    // pdThumbSlide.controller.control = pdPhotoSlide;
	});
	</script>
</div>