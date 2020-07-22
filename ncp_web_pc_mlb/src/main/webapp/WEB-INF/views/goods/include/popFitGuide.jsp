<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- 일반상품 핏 가이드 팝업 -->
<article id="layerPopupFitGuide_GNRL_GOD" class="layer-popup lypopFitGuide">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="goods.layer.popup.fit.guide.ttl"/></h2>
		<div class="layer-cont ly-box scroll">								
			<c:out value="${goods.godFitLktbEx.fitLktbHtml}" escapeXml="false"/>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="goods.common.btn.close"/></button>
		</div>
	</section>
</article>
<!-- //핏 가이드 팝업 -->
