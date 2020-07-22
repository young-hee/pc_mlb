<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!-- layerpopup - 옵션 변경 -->
<article id="layerPopupOption" class="layer-popup layer-type04">
	<section class="layer-popup-cont" tabindex="0">
		<h2>옵션 변경</h2>
		<div class="layer-popup-wrap01">
			<div class="product-option-section">
				<!-- 상품이미지 -->
				<div class="product-img-list" id="productImg01">
					<div class="swiper-container">
						<ul class="swiper-wrapper">
							<li class="swiper-slide"><img src="${_resourceURL}static/images/thumb/@option_product01.png" alt="상품이미지1"></li>
							<li class="swiper-slide"><img src="${_resourceURL}static/images/thumb/@option_product02.png" alt="상품이미지2"></li>
							<li class="swiper-slide"><img src="${_resourceURL}static/images/thumb/@option_product01.png" alt="상품이미지3"></li>
						</ul>
						<!-- Add Pagination -->
						<div class="swiper-pagination type01"></div>
						<!-- Add Arrows -->
						<button type="button" class="swiper-button-prev type01">이전</button>
						<button type="button" class="swiper-button-next type01">다음</button>
					</div>
				</div>
				<script>
					var productImg01 = new Swiper('#productImg01 .swiper-container', {
						slidesPerView: 1,
						observer: true,
						observeParents: true,
						pagination: {
							el: '#productImg01 .swiper-pagination'
						},
						navigation: {
							nextEl: '#productImg01 .swiper-button-next',
							prevEl: '#productImg01 .swiper-button-prev'
						}
					});
				</script>
				<!-- //상품이미지 -->

				<!-- 상품정보 -->
				<dl class="product-option-list">
					<dt>키즈팡팡신주머니 &amp;<br />[남성]엠보 그라데이션 반팔 티셔츠</dt>
					<dd>
						<ul class="payment-option">
							<li>
								<em class="title-color">색상</em>
								<div class="payment-option-color">
									<a href="#" class="d_radio_select"><img src="${_resourceURL}static/images/thumb/@option_color_product01.png" alt="흰색"></a><a href="#" class="d_radio_select"><img src="${_resourceURL}static/images/thumb/@option_color_product01.png" alt="흰색"></a>
								</div>
							</li>
							<li>
								<em>사이즈</em>
								<div class="payment-option-size">
									<button type="button" class="btn-size d_radio_select"><span>95</span></button><button type="button" class="btn-size d_radio_select"><span>100</span></button><button type="button" class="btn-size d_radio_select"><span>105</span></button><button type="button" class="btn-size d_radio_select"><span>110</span></button>
								</div>
							</li>
							<li>
								<em>세트1</em>
								<div class="select-style02 d_select">
									<button type="button" class="d_select_sel" style="width: 168px;"><span>선택하세요</span></button>
									<ul>
										<li><a href="#">선택하세요</a></li>
									</ul>
								</div>
							</li>
							<li>
								<em>세트2</em>
								<div class="select-style02 d_select">
									<button type="button" class="d_select_sel" style="width: 168px;"><span>선택하세요</span></button>
									<ul>
										<li><a href="#">선택하세요</a></li>
									</ul>
								</div>
							</li>
							<li>
								<em>수량</em>
								<div class="quantity-wrap">
									<button type="button" class="pq-minus">빼기</button>
									<button type="button" class="pq-plus">추가</button>
									<input type="number" title="수량" value="1">
								</div>
							</li>
						</ul>
					</dd>
				</dl>
				<!-- //상품정보 -->
			</div>
		</div>
		<div class="btn-wrap">
			<a href="#" class="btn-style03">닫기</a>
			<a href="#" class="btn-style02">변경하기</a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>
<!-- //layerpopup - 옵션 변경 -->

<!-- layerpopup - 품절 및 수량 부족 안내 -->
<article id="layerPopupSoldout" class="layer-popup layer-type03">
	<section class="layer-popup-cont" tabindex="0">
		<h2>품절 및 수량 부족 안내</h2>
		<div class="layer-popup-wrap01">
			<p class="txt-sub-info02">아래 상품 중 일부 상품의 품절 및 수량 부족으로 인해 결제가 불가능 한 상태입니다.<br />장바구니에서 삭제 후 나머지 상품을 주문하시겠습니까?</p>
			<div class="board-list02">
				<table>
					<caption>품절 및 수량 부족 안내 - 상품정보, 가능여부, 상품상태.</caption>
					<colgroup>
						<col>
						<col style="width:90px;">
						<col style="width:90px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상품정보</th>
							<th scope="col">가능여부</th>
							<th scope="col">상품상태</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="tleft02">
								<div class="product-info-text02">
									<p class="product-name">비치드라이 크로스백 3개</p>
									<div class="product-option">
										<span>BL / 90</span>
									</div>
								</div>
							</td>
							<td class="text-color01">주문불가</td>
							<td>
								<p>수량부족</p>
							</td>
						</tr>
						<tr>
							<td class="tleft02">
								<div class="product-info-text02">
									<p class="product-name">비치드라이 크로스백 3개</p>
									<div class="product-option">
										<span>BL / 90</span>
									</div>
								</div>
							</td>
							<td class="text-color01">주문불가</td>
							<td>
								<p>최대 주문가능<br />수량 초과</p>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p class="txt-sub-info02">(안내) 묶음할인 및 교차할인은 상품 수량이 변경 될 경우 할인가격이 변동 될 수 있습니다.</p>
		</div>
		<div class="btn-wrap">
			<a href="#" class="btn-style02">확인</a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>
<!-- //layerpopup - 품절 및 수량 부족 안내 -->
