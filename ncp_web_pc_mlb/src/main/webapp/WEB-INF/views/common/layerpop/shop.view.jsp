<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<article id="layerPopupSelectShopMap" class="layer-popup lyPopfindStore">
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