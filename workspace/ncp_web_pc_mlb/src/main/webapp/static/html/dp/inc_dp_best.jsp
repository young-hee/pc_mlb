<section class="mds-section best">
	
	<div class="hdt"><span class="tit">BEST ITEM</span></div>

	<div id="dpBestSlide" class="slide">
		<div class="swiper-container">
			<ul class="swiper-wrapper list">
				<li class="swiper-slide">
					<div class="item">
						<div class="thumb">
							<a href="/static/html/pd/goods.jsp"><em class="sold_out">SOLD OUT</em>
								<span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span>
							</a>
						</div>
						<div class="info">
			               <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
			               <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
			           </div>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<div class="thumb">
							<a href="/static/html/pd/goods.jsp"><em class="sold_out">SOLD OUT</em>
								<span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span>
							</a>
						</div>
						<div class="info">
							<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
							<div class="prc"><em class="p">59,000</em></div>
						</div>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<div class="thumb">
							<a href="/static/html/pd/goods.jsp"><em class="sold_out">SOLD OUT</em>
								<span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span>
							</a>
						</div>
						<div class="info">
							<div class="name">뉴욕양키스 액센트 커브조절캡</div>
							<div class="prc"><em class="p">59,000</em></div>
						</div>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<div class="thumb">
							<a href="/static/html/pd/goods.jsp"><em class="sold_out">SOLD OUT</em>
								<span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span>
							</a>
						</div>
						<div class="info">
			               <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
			               <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
			           </div>
					</div>
				</li>
				<li class="swiper-slide">
					<div class="item">
						<div class="thumb">
							<a href="/static/html/pd/goods.jsp"><em class="sold_out">SOLD OUT</em>
								<span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span>
							</a>
						</div>
						<div class="info">
			               <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
			               <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
			           </div>
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
		dpBestSlide = new Swiper('#dpBestSlide .swiper-container', {
			slidesPerView: 5,
			observer: true,
			observeParents: true,
			// centeredSlides:true,
			spaceBetween:30,
			watchOverflow:true,
			navigation: {
				nextEl: '#dpBestSlide .navigation .btnNav.next',
				prevEl: '#dpBestSlide .navigation .btnNav.prev'
			},
			preloadImages: false,
		    lazy: true,
			loop: true,
			breakpoints: {
				1280: {
					slidesPerView: 3,
					spaceBetween:20
				}
			}
		});
	});
	</script>
	
</section>

