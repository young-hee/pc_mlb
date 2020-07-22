<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopPdChangeModSet" class="layer-popup optionModi-pop lypopPdModSet">
	<section class="layer-popup-cont" tabindex="0">
		<h2>교환품 옵션 선택</h2>
		<div class="layer-cont ly-box">
			
			<div class="options-GoodsBoxPop">

				<div class="options-Goodsimg list-swiper-dotnav">
					<div class="swiper-container">
						<ul class="swiper-wrapper">
							<li class="swiper-slide">
								<div class="item-img">
									<a href="#">
										<img src="/static/images/_temp/goods_thumb_1.png" alt="">
									</a>
								</div>
							</li>
							<li class="swiper-slide">
								<div class="item-img">
									<a href="#">
										<img src="/static/images/_temp/goods_thumb_2.png" alt="">
									</a>
								</div>
							</li>
							<li class="swiper-slide">
								<div class="item-img">
									<a href="#">
										<img src="/static/images/_temp/goods_thumb_3.png" alt="">
									</a>
								</div>
							</li>
							<li class="swiper-slide">
								<div class="item-img">
									<a href="#">
										<img src="/static/images/_temp/goods_thumb_4.png" alt="">
									</a>
								</div>
							</li>
						</ul>
					</div>
					<button type="button" class="item-list-prev">이전</button>
					<button type="button" class="item-list-next">다음</button>
					<div class="swiper-pagination"></div>
				</div>

				<div class="options-GoodsList">
					<ul class="optSelBoxList">
						<li>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</li>
						<li>
							<em>세뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</em>
							<div class="SelBox">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:100%;"><span>7L</span></button>
									<ul>
										<li><a href="javascript:;">7L</a></li>										
									</ul>
								</div>
								<!-- //select -->
							</div>							
						</li>
						<li>
							<em>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</em>
							<div class="SelBox">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:100%;"><span>엑소 백팩 – 30L</span></button>
									<ul>
										<li><a href="javascript:;">엑소 백팩 – 30L</a></li>										
									</ul>
								</div>
								<!-- //select -->
							</div>
						</li>
						<li>
							<em>수량</em>
							<div class="option-Goodscount">
								<button type="button" class="pq-minus">빼기</button>
								<input type="number" id="qty" title="수량" value="1" data-min-ord-qty="1" data-max-ord-qty="999">
								<button type="button" class="pq-plus">추가</button>
							</div>
						</li>							
					</ul>
				</div>
			</div>
			
			<div class="btnWrapBox">			
				<a href="javascript:;" class="btn d_layer_close">닫기</a>
				<a href="javascript:;" class="btn fill">교환품 선택</a>
			</div>
		
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
var dotnavItem = new Swiper('.list-swiper-dotnav .swiper-container', {
	slidesPerView: 1,
	observer: true,
	observeParents: true,
	pagination: {
		el: '.list-swiper-dotnav .swiper-pagination',
		clickable: true,
	},
	navigation: {
		nextEl: '.list-swiper-dotnav .item-list-next',
		prevEl: '.list-swiper-dotnav .item-list-prev'
	}
});
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopPdChangeModSet'); 
});
</script>
</body>
</html>