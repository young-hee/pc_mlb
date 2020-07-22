
<article id="lypopGoodsGuide" class="layer-popup lypopGoodsGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<c:out value="${goods.comDmstcDlvCstPlc.dlvMthdDscr}" escapeXml="false"/>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="javascript:closeLayerPopAndReset('lypopGoodsGuide');">닫기</button>
		</div>
	    <input type="hidden" id="dmstcDlvCstExmStdrAmt" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.dmstcDlvCstExmStdrAmt}" type="number"/>"/>
	    <input type="hidden" id="dmstcDlvCst" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.dmstcDlvCst}" type="number"/>"/>                 
	    <input type="hidden" id="repairDlvCst" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.repairDlvCst}" type="number"/>"/>                 
		<input type="hidden" id="dmstcDlvComNm" value="<ncp:code code="${goods.comDmstcDlvCstPlc.dlvComCd}"/>"/>
	</section>
</article>

