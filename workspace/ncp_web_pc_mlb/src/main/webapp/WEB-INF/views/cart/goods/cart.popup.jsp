<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!-- layerpopup - 품절 및 수량 부족 안내 -->
<article id="layerPopupSoldout" class="layer-popup layer-type03">
	<section class="layer-popup-cont layer-popup-scroll01" tabindex="0" id="soldoutViewLayer">
	</section>
	<script id="soldoutView" type="text/x-jsrender">	
		<h2>{{:~messages['cart.js.txt.goods.out.info']}}</h2>
		<div class="layer-popup-wrap01">
			<p class="txt-sub-info02">{{:~messages['common.js.caption4']}}</p>
			<div class="board-list02">
				<table>
					<caption>{{:~messages['common.js.caption5']}}</caption>
					<colgroup>
						<col>
						<col style="width:90px;">
						<col style="width:90px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">{{:~messages['cart.js.txt.goods.info']}}</th>
							<th scope="col">{{:~messages['cart.js.txt.avail']}}</th>
							<th scope="col">{{:~messages['cart.js.txt.goods.status']}}</th>
						</tr>
					</thead>
					<tbody>
						{{for cartList itemVar="~item"}}
							{{if (~item.sellYn == 'N' || ~item.invYn == 'N' || ~item.availMinOrdCnt == 'N' || ~item.availMaxOrdCnt == 'N')}}
							<tr>
								<td class="tleft02">
									<div class="product-info-text02">
										<p class="product-name">{{:~item.god.godNm}}</p>
										<div class="product-option">
											{{if ~item.bskGod.pckageGodYn === 'Y' && ~item.pckageGodTpCd !== 'ADIT_CPST_GOD'}}
												<span>
													{{for ~item.bskCpstGodCnnc itemVar="~citem"}}
						 								{{if #getIndex()!==0}} , {{/if}} {{:~citem.godNm}} : {{:~citem.colorNm}}
						 								{{for ~item.bskCpstGodItmCnnc itemVar="~innerItem"}}
															{{if ~citem.godNo === ~innerItem.godNo}}
																/ {{:~innerItem.itmNm}}
															{{/if}}
														{{/for}} 
													{{/for}}
												</span>
											{{/if}}
											{{if ~item.bskGod.pckageGodYn !== 'Y' || ~item.pckageGodTpCd === 'ADIT_CPST_GOD'}}
												<span> {{:~item.god.colorNm}} / {{:~item.godItm.itmNm}}</span>
											{{/if}}
										</div>
									</div>
								</td>
								<td class="text-color01">
									{{if (~item.sellYn == 'N' || ~item.invYn == 'N' || ~item.availMinOrdCnt == 'N' || ~item.availMaxOrdCnt == 'N')}}
										{{:~messages['cart.js.txt.can.not.order']}}
									{{else}}
										{{:~messages['cart.js.txt.can.order']}}
									{{/if}}
								</td>
								<td>
									<p>
										{{if (~item.sellYn != 'N' && ~item.invYn == 'Y')}}
											{{if (~item.godItm.itmStatCd == 'SMTM_SLDOUT')}}
												{{:~messages['cart.js.txt.sold.out.temp']}}
											{{else (~item.godItm.itmStatCd == 'SLDOUT')}}
												{{:~messages['cart.js.txt.sold.out']}}
											{{else (~item.godItm.itmStatCd == 'SALE_END')}}
												{{:~messages['cart.js.txt.saleend']}}			
											{{else}}
												{{:~messages['cart.js.txt.can.not.sell']}}	
											{{/if}}
										{{else (~item.sellYn != 'N' && ~item.invYn != 'Y' && ~item.godItm.itmStatCd == 'SALE_PROGRS')}}
											{{:~messages['cart.js.txt.over.quantity']}}
										{{else (~item.sellYn != 'N' && ~item.invYn == 'Y' && ~item.availMaxOrdCnt != 'Y')}}
											{{:~messages['cart.js.txt.max.overflow']}}
										{{else (~item.sellYn != 'N' && ~item.invYn == 'Y' && ~item.availMinOrdCnt != 'Y')}}
											{{:~messages['cart.js.txt.min.under']}}
										{{else}}
											{{if (~item.godItm.itmStatCd == 'SMTM_SLDOUT')}}
												{{:~messages['cart.js.txt.sold.out.temp']}}
											{{else (~item.godItm.itmStatCd == 'SLDOUT')}}
												{{:~messages['cart.js.txt.sold.out']}}
											{{else (~item.godItm.itmStatCd == 'SALE_END')}}
												{{:~messages['cart.js.txt.saleend']}}
											{{else}}
												{{:~messages['cart.js.txt.can.not.sell']}}	
											{{/if}}
										{{/if}}	
									</p>
								</td>
							</tr>
							{{/if}}
						{{/for}}
					</tbody>
				</table>
			</div>
			<p class="txt-sub-info02">{{:~messages['cart.js.txt.caption6']}}</p>
			<!-- 2018-05-16  -->
			<div class="btn-wrap">
				<a href="javascript:cart.checkFilterOrder();" class="btn-style02 ">{{:~messages['cart.js.txt.confirm']}}</a>
			</div>
			<!-- //2018-05-16  -->
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">{{:~messages['cart.js.txt.close']}}</button>
		</div>
	</script>
</article>

<article id="orderConfirmCallback" class="layer-popup lyPopSample1">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="common.js.confirm" /></h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt"></p>
		</div>
		<div class="layer-cont">
			<div class="btnWrapBox">
				<a href="#" class="btn d_layer_close cancelBtn"><spring:message code="common.js.cancel" /></a>
				<a href="#" class="btn fill d_layer_close confirmBtn"><spring:message code="common.js.confirm" /></a>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">close</button>
		</div>
	</section>
</article>
<article id="orderAlertLayer" class="layer-popup layer-type02">
	<section class="layer-popup-cont" tabindex="0">
		<div class="layer-popup-wrap02">
			<p class="layer-txt"></p>
		</div>
		<div class="btnWrapBox">
			<a href="#" class="btn fill d_layer_close"><spring:message code="common.js.close" /></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>