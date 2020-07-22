<!-- layerpopup -->
<article id="layerPopupGiftInfo" class="layer-popup layer-type02">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="goods.option.popup.gift.guide.ttl"/></h2> <%-- 사은품 안내 --%>
		<div class="layer-popup-wrap02">
			<p class="layer-txt"><spring:message code="goods.option.popup.gift.guide.txt"/></p> <%-- [매장픽업]으로 주문하시는 경우 <br>상품 사은품이 제공되지 않습니다. --%>
		</div>
		<div class="btn-wrap02">
			<a href="#" class="btn fill d_layer_close" onclick="javascript:pickupStorePopupOpen();"><spring:message code="goods.common.btn.ok"/></a> <%-- 확인 --%>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<article id="layerPopupAddBasketComplete" class="layer-popup layer-type02">
	<section class="layer-popup-cont ly-box" tabindex="0" style="width:400px;">
		<h2><spring:message code="goods.option.popup.addbsk.ttl"/></h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt"><spring:message code="goods.option.popup.addbsk.lbl"/></p>
		</div>
		<div class="lyBtnArea">
			<a href="#" class="btn w160 d_layer_close"><spring:message code="goods.option.popup.addbsk.btn1" text="계속 쇼핑하기" /></a>
			<a href="/cart/goods/list" class="btn fill w160"><spring:message code="goods.option.popup.addbsk.btn2"/></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<article id="layerPopupCardInfo" class="layer-popup">
	<section class="layer-popup-cont layer-popup-scroll01" tabindex="0">
		<h2><spring:message code="goods.option.popup.cardinfo.ttl"/></h2>
		<div class="layer-popup-wrap02">
			<div id="cardinfo_content">
			</div>
			<div class="btn-wrap03">
				<a href="#" class="btn-style02 d_layer_close"><spring:message code="goods.common.btn.close"/></a>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<article id="layerPopupMap" class="layer-popup">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="goods.option.popup.store.ttl"/></h2>
		<div class="layer-popup-wrap01">
			<ul class="text-list02 col-type01">
				<li><spring:message code="goods.option.popup.store.lbl.txt1"/></li>
			</ul>
			<dl class="search-wrap03 search-type01">
				<dt><spring:message code="goods.option.popup.store.lbl.size.select"/></dt>
				<dd>
					<div class="select-style02 d_select">
						<button type="button" class="d_select_sel" style="width:160px;"><span><spring:message code="goods.common.lbl.select"/></span></button>
						<ul>
							<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">									
                                <c:choose>
									<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">
										<li><a href="#" onclick="javascript:setItmNoSearchStore('${size.itmNo}')"><c:out value="${size.itmNm}"/></a></li>
									</c:when>
									<c:otherwise>
                  						<li><del><c:out value="${size.itmNm}"/></del></li>
 									</c:otherwise>
								</c:choose>											
							</c:forEach>
						</ul>
						<input type="hidden" name="searchStoreItmNo" id="searchStoreItmNo" value="">
					</div>
				</dd>
				<dt><spring:message code="goods.option.popup.store.lbl.region.select"/></dt>										
				<dd>
					<div class="select-style02 d_select">
						<button type="button" class="d_select_sel" style="width:160px;"><span><spring:message code="goods.common.lbl.all"/></span></button>
						<ul id="sidoList">
							<li><a href="#" onclick="javascript:setSidoCdSearchStore('','store')"><spring:message code="goods.common.lbl.all"/></a></li>										
							<script type="text/javascript">
								$("#sidoList li:not(:first)").remove();									
								var slist = $.parseJSON('${ncpfn:marshallingJson(siList)}');									
								if(slist != null && slist.length != 0){
									for(var i=0; i<slist.length; i++){
										$("#sidoList").append("<li><a href=\"#\" onclick=\"javascript:setSidoCdSearchStore('"+slist[i].cd+"','store')\">"+slist[i].cdNm+"</a></li>");
									}										
								}									
							</script>																	
						</ul>
						<input type="hidden" name="searchStoreSidoCd" id="searchStoreSidoCd" value="">
					</div>
				</dd>
				<dt><spring:message code="goods.option.popup.store.lbl.direct.input"/></dt>
				<dd>
					<div class="search-input03">
						<input type="search" title="매장 검색" id="searchStoreKeyword" maxlength="10"/><button type="button" onclick="javascript:availableStore();"><spring:message code="goods.common.btn.search"/></button>
					</div>						
				</dd>
			</dl>
			<div class="store-list-wrap" id="pdp-store-list-wrap">	 
				<div class="no-result">						
					<spring:message code="goods.option.popup.store.info.lbl.size.noselect"/> <%-- 사이즈를 먼저 선택해 주세요. --%>
				</div>
				<div class="store-list" style="display:none;">
					<ul class="main-ul"></ul>
				</div>
				<div class="store-map" id="map" style="height:100%; display:none;"></div>
			</div>
		</div>
		<div class="btn-wrap">
			<a href="#" class="btn-style02 d_layer_close"><spring:message code="goods.common.btn.close"/></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


<article id="layerPopupSelectShopMap" class="layer-popup lyPopfindStore inlayer" style="z-index:1400">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2><spring:message  code="order.popup.store.ttl"/></h2> <%-- 약도보기 --%>
		<div class="layer-cont ly-box">
			<div class="lystoreMap">
				<div class="map-view-img" id="common_shopMap" style="width:484px; height:100%;"></div>
			</div>		
			<div class="lystoreInfo">	
				<h3 id="common_shopNm">현대울산동구점</h3>
				<dl>
					<dt><spring:message code="goods.js.goods.option.popup.store.lbl.txt5" /></dt>
					<dd id="map-view-address"></dd>
				</dl>
				<dl>
					<dt><spring:message code="goods.js.goods.option.popup.store.lbl.txt6" /></dt>
					<dd id="map-view-tel"></dd>
				</dl>
				<dl>
					<dt><spring:message code="goods.js.goods.option.popup.store.lbl.txt7" /></dt>
					<dd id="map-view-time"></dd>					
				</dl>
			</div>
		</div>		
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


<!-- MLB 상품상세 부분 시작 (20181127_ds) -->

<!-- 쿠폰 다운로드 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popCuponDown.jsp" %>

<!-- 사이즈가이드 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popSizeGuide.jsp" %>

<!-- 핏가이드 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popFitGuide.jsp" %>

<!-- 매장픽업 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popStoreService.jsp" %>

<!-- 상품상세 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popGoodsDtInfo.jsp" %>

<!-- 입고알림서비스 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popGoodsInform.jsp" %>

<!-- 매장픽업 선택 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/popGoodsStore.jsp" %>

<!-- 해외매장 찾기 팝업 -->
<!-- %@ include file="/WEB-INF/views/goods/include/popFindStore.jsp" % -->

<!-- //MLB 상품상세 부분 시작 (20181127_ds) -->
<!-- //layerpopup -->



