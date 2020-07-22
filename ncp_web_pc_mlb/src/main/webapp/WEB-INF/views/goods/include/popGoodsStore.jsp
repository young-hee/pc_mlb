
<article id="layerPopupPickupStore" class="layer-popup lypopGoodsStore">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="goods.option.popup.store.pickup.lbl.ttl"/></h2>
		<div class="layer-cont ly-box scroll">
		
			<div class="searchStoreBox" style='display:none'>
				<dl>
					<dt><label for=""><spring:message code="goods.option.popup.store.pickup.lbl.select.loc"/></label></dt>
					<dd>
						<div class="select-style01 d_select sm">
							<button type="button" class="d_select_sel" style="width:150px;"><span><spring:message code="goods.common.lbl.all"/></span></button>
							<ul id="pickupSidoList">
								<li><a href="#" onclick="javascript:setSidoCdSearchStore('','pickup')"><spring:message code="goods.common.lbl.all"/></a></li>
								<script type="text/javascript">
									$("#pickupSidoList li:not(:first)").remove();									
									var slist = $.parseJSON('${ncpfn:marshallingJson(siList)}');									
									if(slist != null && slist.length != 0){
										for(var i=0; i<slist.length; i++){
											$("#pickupSidoList").append("<li><a href=\"#\" onclick=\"javascript:setSidoCdSearchStore('"+slist[i].cd+"','pickup')\">"+slist[i].cdNm+"</a></li>");
										}										
									}									
								</script>								
							</ul>
						</div>
						<input type="hidden" name="pickupStoreSidoCd" id="pickupStoreSidoCd" value="">
					</dd>
					<dt><label for=""><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn"/></label></dt>
					<dd>
						<div class="select-style01 d_select sm">
							<button type="button" class="d_select_sel" style="width:100px;"><span><spring:message code="goods.common.lbl.all"/></span></button>
							<ul>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('','pickup');"><spring:message code="goods.common.lbl.all"/></a></li>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('C','pickup');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.jic"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.jic"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('A','pickup');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.bek"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.bek"/></a></li>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('BF','pickup');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.dea"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.dea"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('DE','pickup');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.san"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.san"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('ADF','pickup');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.myn"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.myn"/></a></li>								
							</ul>
							<input type="hidden" name="pickupStoreShopGbn" id="pickupStoreShopGbn" value="">
						</div>
					</dd>
					<dt><label for="stName"><spring:message code="goods.option.popup.store.pickup.lbl.select.name"/></label></dt>
					<dd>
						<input type="text" id="pickupSearchStoreKeyword" class="input-style01 sm input_required textOnly" style="width:268px;" alt="매장명" maxlength="100">
						<a href="javascript:pickupStore();" class="btn sm pd20"><spring:message code="goods.common.btn.search"/></a>
					</dd>										
				</dl>
			</div>	
				
			<div class="storeList">
				<p class="total">총 <strong><em class="num"></em></strong>건</p>
				<table class="board-list lyType02" id="tablePickupStore">
					<colgroup>
						<col style="width:50px">
						<col style="width:140px">
						<col style="width:">
						<col style="width:100px">
						<col style="width:100px">											
					</colgroup>
					<thead>
						<tr>
							<th><spring:message code="goods.js.goods.option.popup.store.lbl.txt1"/></th>
							<th><spring:message code="goods.option.popup.store.pickup.lbl.select.name"/></th>
							<th><spring:message code="goods.js.goods.option.popup.store.lbl.txt5"/></th>
							<th><spring:message code="goods.js.goods.option.popup.store.lbl.txt6"/></th>
							<th><spring:message code="goods.js.goods.option.popup.store.lbl.txt7"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<!-- 검색 결과 없을경우 -->
								<div class="selNoResult"><spring:message code="goods.js.goods.option.popup.store.lbl.txt2"/></div>
							</td>
						</tr>			
																									
					</tbody>
				</table>	
			</div>
			
			<ul class="text-list02 txtTypeGray">				
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li1"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li2"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li3"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li4"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li5"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li6"/></li>
				<li><spring:message code="goods.option.popup.store.pickup.lbl.ul1.li7"/></li>
			</ul>
				
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:;" class="btn w160 d_layer_close" onclick="javascript:closeLayerPopAndReset('lypopGoodsReview');"><spring:message code="goods.common.btn.cancel"/></a>
				<a href="javascript:;" class="btn fill w160" onclick="javascript:choicePickupStore();"><spring:message code="goods.common.btn.select"/></a>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="javascript:closeLayerPopAndReset('lypopGoodsReview');"><spring:message code="goods.common.btn.close"/></button>
		</div>
	</section>
</article>

