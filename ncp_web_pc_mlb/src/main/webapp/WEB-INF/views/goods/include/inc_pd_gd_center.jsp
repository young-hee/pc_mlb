
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<div class="area center">
	<div class="photos">
		<div id="pdPhotoSlide" class="pdPhoto">
			<div class="swiper-container">
				<ul class="swiper-wrapper list">
					<!-- 동영상 -->	
					<c:if test="${not empty goods.godMovi.moviUrl}">
						<c:set var="len" value="${fn:length(goods.godMovi.moviUrl)}"/>
						<c:set var="moviExt" value="${fn:substring(goods.godMovi.moviUrl,len-3,len)}"/>			
						<c:if test="${goods.godMovi.moviUseYn eq 'Y'}">
							<li class="mov swiper-slide">
								<div class="item">
									<video class="video" controls="controls" controls autoplay muted loop disablepictureinpicture="" controlslist="nodownload">
										<source src="${imageURL}${goods.godMovi.moviUrl}" type="video/${moviExt}">
									</video>
								</div>
							</li>
						</c:if>	
					</c:if>

					<input type="hidden" name="len"		id="len"	value="${len}">	
					<input type="hidden" name="imgCnt"  id="imgCnt"	value="${fn:length(goods.godImgList)}">	
					
					<!-- 대표컷 -->					
					<li class="pic swiper-slide">
						<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);">
							<img src="${imageURL}${goods.godImg.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_634x634)%>" data-big="${imageURL}${goods.godImg.imgUrl}" alt="${goodsNm}" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_634x634)%>');">
						</a></div>
					</li>
					<!-- 상세컷 -->
					<c:forEach var="images" items="${goods.godImgList}">
						<c:if test="${'DETAIL' eq images.imgTpCd}">
							<li class="pic swiper-slide">
								<div class="item"><a href="javascript:;" onclick="ui.pd.goods_zoom.open(this);">
									<img src="${imageURL}${images.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_634x634)%>" data-big="${imageURL}${images.imgUrl}" alt="${goodsNm}" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_634x634)%>');">
								</a></div>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="navigation" id="divNavi">
				<button type="button" class="btnNav prev">이전</button>
				<button type="button" class="btnNav next">다음</button>
			</div>
		</div>
		<div id="pdThumbSlide" class="pdThumb">
			<div class="swiper-container">
				<ul class="swiper-wrapper list">
					<!-- 동영상 -->
					<c:if test="${not empty goods.godMovi.moviUrl}">
						<c:if test="${goods.godMovi.moviUseYn eq 'Y'}">
							<li class="mov swiper-slide">
								<a href="javascript:;" class="item"><img src="${_resourceURL}static/images/pd/mov_img.png?v=${_version}" alt=""></a>
							</li>
						</c:if>	
					</c:if>
					<!-- 대표컷 -->
					<li class="pic swiper-slide"><a href="javascript:;" class="item"><img src="${imageURL}${goods.godImg.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_70x70)%>" alt=""></a></li>
					<c:forEach var="images" items="${goods.godImgList}">
						<c:if test="${'DETAIL' eq images.imgTpCd}">
							<li class="pic swiper-slide">
								<a href="javascript:;" class="item"><img src="${imageURL}${images.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_70x70)%>" alt=""></a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
			<div class="navigation" id="divNavi">
				<button type="button" class="btnNav prev">이전</button>
				<button type="button" class="btnNav next">다음</button>
			</div>
		</div>


	</div>

	<script>
	$( document ).ready(function() {
		/*
		var src_big;
		goods_zoom = {
			open:function(el){
				src_big = $(el).find("img").attr("data-big");
				if ( $("#popGoodsZoom").length == false ) {
					$("body").append(this.pop);
				}
				$("#popGoodsZoom .layer-cont img.big").attr("src",src_big);
				layerPopup.popupOpenNow('#popGoodsZoom'); 
			},
			close:function(){
				layerPopup.popupCloseNow('#popGoodsZoom');
			},
			pop:'<article id="popGoodsZoom" class="layer-popup popGoodsZoom">'+
					'<section class="layer-popup-cont" tabindex="0">'+
						'<div class="layer-cont">'+
						'	<img src="'+src_big+'" class="big" onclick="goods_zoom.close();">	 '+
						'</div>'+
						'<div class="layer-popup-close">'+
							'<button type="button" class="d_layer_close">닫기</button>'+
						'</div>'+
					'</section>'+
				'</article>'
		} 
		
		
		var slideLength = 0;
		if($("#len").val() > 0) {
			slideLength++;
		}
		slideLength = slideLength + parseInt($("#imgCnt").val());
		
		if(slideLength < 6) {
			$("#divNavi").hide();
		} else {
			$("#divNavi").show();
		}

	    pdPhotoSlide = new Swiper('#pdPhotoSlide .swiper-container', {
	      spaceBetween: 10,
	      loop:true,
	      loopedSlides: slideLength,
	      effect:'fade',
	      navigation: {
	        nextEl: '#pdThumbSlide .btnNav.next',
	        prevEl: '#pdThumbSlide .btnNav.prev',
	      }

	    });
	    pdThumbSlide = new Swiper('#pdThumbSlide .swiper-container', {
	      spaceBetween: 10,
	      slidesPerView: slideLength,
	      spaceBetween:5,
	      touchRatio: 0.2,
	      loop: true,
	      loopedSlides: slideLength,
	      slideToClickedSlide: true,
	    });
	    pdPhotoSlide.controller.control = pdThumbSlide;
	    pdThumbSlide.controller.control = pdPhotoSlide;
	    */
	});
	</script>
</div>