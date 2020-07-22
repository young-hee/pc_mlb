
<section class="mds-section related">
<ncp:prop key="ncp.image.url" var="imageURL"/>
<input type="hidden" name="relatedCnt"  id="relatedCnt"	value="${fn:length(relatedGoods)}">	
	
	<div class="hdt"><span class="tit"><spring:message code="goods.relate.ttl"/></span></div>

	<div id="pdRelatedSlide" class="pdRelatedSlide slide">
		<div class="swiper-container">
			<ul class="swiper-wrapper list">
				<!-- loof -->
				<c:forEach var="relatedGood" items="${relatedGoods}" varStatus="status">	
				<li class="swiper-slide">
					<div class="item">
					<div class="thumb"><a href="${relatedGood.godDetailUrl }"><span class="img"><img src="${imageURL}${relatedGood.imgUrl}" alt="${relatedGood.godNm}" onerror="errorImgShow(this,'');"></span></a></div>
						<div class="info">
			                 <div class="name"><a href="${relatedGood.godDetailUrl }"><c:out value="${relatedGood.godNm}"/></a></div>
			                 <div class="prc"> 
			                 	<c:if test="${relatedGood.rtlPrc ne relatedGood.csmrPrc}">
			                 		<s class="s"><fmt:formatNumber value="${relatedGood.rtlPrc}" type="number"/><spring:message code="goods.common.lbl.won"/></s><em class="p"><fmt:formatNumber value="${relatedGood.csmrPrc}" type="number"/><spring:message code="goods.common.lbl.won"/></em>
			                 	</c:if> 
			                 	<c:if test="${relatedGood.rtlPrc eq relatedGood.csmrPrc}">
									<em class="p"><fmt:formatNumber value="${relatedGood.rtlPrc}" type="number"/><spring:message code="goods.common.lbl.won"/></em>		                 	
			                 	</c:if>
			                 </div>
			             </div>
					</div>
				</li>
				</c:forEach>
			</ul>
		</div>
		<div class="navigation" id="divRelateNavi">
			<button type="button" class="btnNav prev">이전</button>
			<button type="button" class="btnNav next">다음</button>
		</div>
	</div>

	<script>
	$( document ).ready(function() {
		/* var relatedCnt = parseInt($("#relatedCnt").val());
		var loopYn = false;
		if(relatedCnt < 5) {
			loop = false;
		} else {
			loop = true;
		} 
		
		pdRelatedSlide = new Swiper('#pdRelatedSlide .swiper-container', {
			slidesPerView: relatedCnt,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			slidesPerGroup:1,
			spaceBetween:30,
			navigation: {
				nextEl: '#pdRelatedSlide .navigation .btnNav.next',
				prevEl: '#pdRelatedSlide .navigation .btnNav.prev'
			},
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: loopYn,
			breakpoints: {
				1280: {
					slidesPerView: relatedCnt,
					spaceBetween:20
				}
			}
		}); */
	});
	</script>
	
</section>
