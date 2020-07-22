<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- 일반상품 -->
<article id="layerPopupSizeGuide_GNRL_GOD" class="layer-popup lypopSizeGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2><spring:message code="goods.option.popup.size.guide.ttl"/></h2>
		<div class="layer-cont ly-box scroll">
			<p class="txtIcon02 txtGray"><spring:message code="goods.option.popup.size.guide.info"/></p>
			<c:out value="${goods.godNtfcDetail.sizeLktbHtml}" escapeXml="false"/>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<!-- 세트상품 -->
<c:forEach var="cpstCnncGod" items="${goods.godCpstGodCnncExList}">
<article id="layerPopupSizeGuide_${cpstCnncGod.cpstGodNo}" class="layer-popup lypopSizeGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2><spring:message code="goods.option.popup.size.guide.ttl"/></h2>
		<div class="layer-cont ly-box scroll">
			<c:out value="${cpstCnncGod.cpstSizeLktbHtml}" escapeXml="false"/>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>		
</c:forEach>	
