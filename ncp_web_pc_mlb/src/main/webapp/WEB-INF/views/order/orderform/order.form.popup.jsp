<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!-- layerpopup - 쿠폰선택 -->
<article id="layerPopupCoupon" class="layer-popup lypopCuponSch">
	<section class="layer-popup-cont" tabindex="0" style="width:530px" id="couponViewLayer">
	</section>
	<script id="couponView" type="text/x-jsrender">
		<h2>{{:~messages['order.js.txt.select.coupon']}}</h2>
		<div class="layer-cont ly-box scroll">
			<div class="btnCupon">
				<a href="/mypage/benefit/listCoupon" class="btn sm btnAddCupon">{{:~messages['order.js.txt.coupon.regist']}}</a>
			</div>

			{{if godCpnList.length>0}}
			<div class="CuponArea">		
				<h3 class="title06">{{:~messages['order.js.txt.goods.coupon']}}</h3>
				<table class="board-list">
					<tbody>
					{{for memberCouponList itemVar="~item"}}
					{{if ~item.isGodCpn}}
					<tr>
						<td class="tleft">
	                        <div class="product-info">
								<div class="product-info-img"><img src="{{:~pimg(~item.cartGodInfo.imgUrl,'100x100')}}" alt="{{:~messages['order.js.txt.goods.img']}}" onerror="errorImgShow(this, 100);"></div>
								<div class="product-info-text">
									<div class="product-info-box">
										<p class="product-name">{{:~item.cartGodInfo.god.godNm}}</p>
										<div class="product-price">
											<span>{{:~otool.commaInAmount(~item.cartGodInfo.price)}}{{:~messages['common.js.crncy']}}</span>
										</div>
									</div>
									<div class="product-option">
									{{if ~item.cartGodInfo.bskGod.pckageGodYn === 'Y' && ~item.cartGodInfo.pckageGodTpCd !== 'ADIT_CPST_GOD'}}
										<span>
											{{for ~item.cartGodInfo.bskCpstGodCnnc itemVar="~citem"}}
				 								{{if #getIndex()!==0}} , {{/if}} {{:~citem.godNm}} : {{:~citem.colorCd}}
				 								{{for ~item.cartGodInfo.bskCpstGodItmCnnc itemVar="~innerItem"}}
													{{if ~citem.godNo === ~innerItem.godNo}}
														/ {{:~innerItem.itmNm}}
													{{/if}}
												{{/for}} 
											{{/for}}
										</span>
									{{/if}}
									{{if ~item.cartGodInfo.bskGod.pckageGodYn !== 'Y' || ~item.cartGodInfo.pckageGodTpCd === 'ADIT_CPST_GOD'}}
										<span> {{:~item.cartGodInfo.god.colorCd}} / {{:~item.cartGodInfo.godItm.itmNm}}</span>
									{{/if}}
									</div>
								</div>
							</div>
							<!-- select -->
							<div class="select-style01 d_select selSm selmt20">
								<button type="button" class="d_select_sel" style="width:220px;">
								{{if ~item.cartGodInfo.applyCpn}}
									<span id="prdCpnTxt_{{:~item.cartGodInfo.bskGod.godTurn}}" class="prdCpnTxt">{{:~item.cartGodInfo.applyCpn.prm.prmNm}}</span>
								{{else}}
									<span id="prdCpnTxt_{{:~item.cartGodInfo.bskGod.godTurn}}" class="prdCpnTxt">{{:~messages['order.js.txt.select']}}</span>
								{{/if}}
								</button>
								<ul>
									<li><a href="#" onclick="ordercoupon.applyGodCoupon('','{{:~item.cartGodInfo.bskGod.godTurn}}');">{{:~messages['order.js.txt.select']}}</a></li>
									{{for ~item.goodsCouponResultList itemVar="~coupon"}}
										{{if ~coupon.prm.prmDtlTpCd === 'GOD_CPN' }}
											{{if ~coupon.mbrCpnNo}}
												<li><a href="#" onclick="ordercoupon.applyGodCoupon('{{:~coupon.mbrCpnNo}}','{{:~item.cartGodInfo.bskGod.godTurn}}');">{{:~coupon.prm.prmNm}}</a></li>
											{{else}}
												<li><a href="#" onclick="ordercoupon.applyGodDiscount('{{:~coupon.prm.prmNo}}','{{:~item.cartGodInfo.bskGod.godTurn}}');">{{:~coupon.prm.prmNm}}</a></li>
											{{/if}}
										{{/if}}
									{{/for}}
								</ul>
							</div>
							<!-- //select -->
							<p class="txtTotal pdCupon">
								<strong id="godCpnTurn_{{:~item.cartGodInfo.bskGod.godTurn}}">{{:~otool.commaInAmount(~item.cartGodInfo.checkGod.godCpnDcAmt+~item.cartGodInfo.checkGod.imdtlDcAmt)}}</strong>{{:~messages['common.js.crncy']}}
							</p>
						</td>
					</tr>
					{{/if}}
					{{/for}}
					</tbody>
				</table>
			</div>
			{{/if}}

			{{if bskCpnList.length>0}}
			<div class="CuponArea">	
				<h3 class="title06">{{:~messages['order.js.txt.bsk.coupon']}}</h3>
				<table class="board-list">
					<colgroup>
	                    <col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>
					<tbody>
						<tr>
							<td class="tleft">
								<!-- select -->
								<div class="select-style01 d_select selSm">
									<button type="button" class="d_select_sel" style="width: 360px;">
										{{if bskCpnNm}}
											<span id="bskCpnNm">{{:bskCpnNm}}</span>
										{{else}}
											<span id="bskCpnNm">{{:~messages['order.js.txt.select']}}</span>
										{{/if}}
									</button>
									<ul>
										<li><a href="#" onclick="ordercoupon.applyBskCoupon('');">{{:~messages['order.js.txt.select']}}</a></li>
										{{for bskCpnList itemVar="~coupon"}}
											<li class="godCpnDplctCd_{{:~coupon.prm.godCpnDplctCd}}"><a href="#" onclick="ordercoupon.applyBskCoupon('{{:~coupon.mbrCpnNo}}');">{{:~coupon.prm.prmNm}} {{if ~coupon.prm.godCpnDplctCd == 'IMPS'}}({{:~messages['order.js.txt.coupon.not.dupx']}}){{/if}}</a></li>
										{{/for}}
									</ul>
								</div>
								<!-- //select -->
							</td>
							<td class="txtTotal"><strong class="bskCpnAmt" id="bskCpnAmt">0</strong>{{:~messages['common.js.crncy']}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			{{/if}}

			{{if dlvCpnList.length>0}}
			<div class="CuponArea">	
				<h3 class="title06">{{:~messages['order.js.txt.dlv.free.coupon']}}</h3>
				<table class="board-list">
					<colgroup>
	                    <col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>
					<tbody>
						<tr>
							<td class="tleft">
								<!-- select -->
								<div class="select-style01 d_select selSm">
									<button type="button" class="d_select_sel" style="width: 360px;">
										{{if bskDlvNm}}
											<span>{{:bskDlvNm}}</span>
										{{else}}
											<span>{{:~messages['order.js.txt.select']}}</span>
										{{/if}}
									</button>
									<ul>
										<li><a href="#" onclick="ordercoupon.applyDlvCoupon('');">{{:~messages['order.js.txt.select']}}</a></li>
										{{for dlvCpnList itemVar="~coupon"}}
											<li><a href="#" onclick="ordercoupon.applyDlvCoupon('{{:~coupon.mbrCpnNo}}');">{{:~coupon.prm.prmNm}}</a></li>
										{{/for}}
									</ul>
								</div>
								<!-- //select -->
							</td>
							<td class="txtTotal"><strong class="dlvCpnAmt" id="dlvCpnAmt">0</strong>{{:~messages['common.js.crncy']}}</td>
						</tr>
					</tbody>
				</table>
			</div>
			{{/if}}		


			<div class="CuponArea">
				<table class="board-list total-list">
					<colgroup>
	                	<col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>
					<tbody>                
	                    <tr>    
	                    	<th>{{:~messages['order.js.txt.apply.coupon']}}</th>
	                    	<td class="txtTotal" id="godCpnAmt">{{:~otool.commaInAmount(godCpnAmt)}}{{:~messages['common.js.crncy']}}</td>
                    	</tr>
                    	<tr>    
	                    	<th>{{:~messages['order.js.txt.bsk.apply.coupon']}}</th>
	                    	<td class="txtTotal bskCpnAmt" id="totalBskCpnAmt">{{:~otool.commaInAmount(bskCpnAmt)}}{{:~messages['common.js.crncy']}}</td>
                    	</tr>
                   	</tbody>
                   	<tfoot>
                   		<th>{{:~messages['order.js.txt.totoal.payment.dc.amt']}}</th>
                   		<td class="txtTotal"><strong id="totCpnAmt">{{:~otool.commaInAmount(godCpnAmt+bskCpnAmt+dlvCpnAmt)}}</strong>{{:~messages['common.js.crncy']}}</td>
                   	</tfoot>
				</table>
			</div>

			<div class="lyBtnArea">
				<a href="#" class="btn fill d_layer_close">{{:~messages['order.js.txt.apply.btn']}}</a>
			</div>

		</div>

		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">{{:~messages['order.js.txt.confirm']}}</button>
		</div>
	</script>
</article>
<!-- //layerpopup - 쿠폰선택 -->

<!-- layerpopup - 배송지선택 -->
<article id="layerPopupAddress" class="layer-popup lypopaddrSelect">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2><spring:message code="order.js.txt.select.delivery.location" /></h2>
		<div class="layer-cont ly-box scroll">
		
			<div class="d_tab02">
				<ul class="tab-type01">
					<li class="d_tab02_select on"><a href="#"><spring:message code="order.js.txt.delivery.location.info.1" /></a></li>
					<li class="d_tab02_select"><a href="#"><spring:message code="order.js.txt.recent.delivery.location" /></a></li>
				</ul>
				
				<div id="deliveryInfoViewLayer">
				</div>
				<script id="deliveryInfoView" type="text/x-jsrender">
				<div class="d_tab02_cont" style="display:block;">
					<div class="board-list">
					{{if mbrDlvspList itemVar="~list"}}
					<table>
						<caption>{{:~messages['order.js.txt.caption11']}}</caption>
						<colgroup>
							<col style="width:100px">
							<col style="width:">
							<col style="width:100px">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">{{:~messages['order.js.txt.receiver']}}</th>
								<th scope="col">{{:~messages['order.js.txt.delivery.location.info.1']}}</th>
								<th scope="col">{{:~messages['order.js.txt.select']}}</th>
							</tr>
						</thead>
						<tbody>
							{{for mbrDlvspList itemVar="~item"}}
							<tr>
								<td>
									{{:~item.addrseNm}}<br>{{if ~item.baseDlvspYn=='Y'}}[{{:~messages['order.js.txt.base.location']}}]{{/if}}
								</td>
								<td class="addrBox">
									({{:~item.postNo}})<br>
									{{:~item.baseAddr}}<br>
									{{:~item.detailAddr}}<br>
									{{:~item.mobilAreaNo}}-{{:~item.mobilTlofNo}}-{{:~item.mobilTlofWthnNo}}
								</td>
								<td>
									<a href="#" class="btn sm" onClick="orderform.applyDeliveryInfo('ord',{{:#index}})">{{:~messages['order.js.txt.select']}}</a>
								</td>
							</tr>
							{{/for}}
						</tbody>
					</table>
					{{else}}
						<div class="noResultInfo">{{:~messages['order.js.txt.nolist']}}</div>
					{{/if}}
					</div>
				</div>

				<div class="d_tab02_cont">
					<div class="board-list">
					{{if ordDlvspList itemVar="~list"}}
					<table>
						<caption>{{:~messages['order.js.txt.caption11']}}</caption>
						<colgroup>
							<col style="width:100px">
							<col style="width:">
							<col style="width:100px">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">{{:~messages['order.js.txt.receiver']}}</th>
								<th scope="col">{{:~messages['order.js.txt.delivery.location.info.1']}}</th>
								<th scope="col">{{:~messages['order.js.txt.select']}}</th>
							</tr>
						</thead>
						<tbody>
							{{for ordDlvspList itemVar="~item"}}
							<tr>
								<td>
									{{:~item.addrseNm}}
								</td>
								<td class="addrBox">
									({{:~item.postNo}})<br>
									{{:~item.baseAddr}}<br>
									{{:~item.detailAddr}}<br>
									{{:~item.mobilAreaNo}}-{{:~item.mobilTlofNo}}-{{:~item.mobilTlofWthnNo}}
								</td>
								<td>
									<a href="#" class="btn sm" onClick="orderform.applyDeliveryInfo('mem',{{:#index}})">{{:~messages['order.js.txt.select']}}</a>
								</td>
							</tr>
							{{/for}}
						</tbody>
					</table>
					{{else}}
						<div class="noResultInfo">{{:~messages['order.js.txt.nolist']}}</div>
					{{/if}}
					</div>
				</div>
				</script>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="order.js.txt.confirm" /></button>
		</div>
	</section>		
</article>
<article id="orderConfirmCallback" class="layer-popup layer-type02">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="common.js.confirm" /></h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt"></p>
		</div>
		<div class="btn-wrap03">
			<a href="#" class="btn-style03 d_layer_close"><spring:message code="common.js.cancel" /></a><a href="#" class="btn-style02 commonConfirmBtn"><spring:message code="common.js.confirm" /></a>
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
		<div class="btn-wrap">
			<a href="#" class="btn-style02 d_layer_close"><spring:message code="common.js.close" /></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<article id="lypopCardPayCf" class="layer-popup lypopCardinfo lypopCardPayCf">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info2" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<p class="fw_bold"><spring:message code="order.payment.card.info1.1" /></p>
				<p><spring:message code="order.payment.card.info1.2" /></p>
				
				 <table class="board-list">
	                <colgroup>                     
	                  <col style="width:260px">
	                  <col style="width:130px">
	                  <col style="width:">
	                </colgroup>
	                <thead>
	           	   		<tr>
	           	   			<th></th>
	           	   			<th><spring:message code="order.payment.card.info1.3" /></th>
	           	   			<th><spring:message code="order.payment.card.info1.4" /></th>
	       	   			</tr>
	                </thead>
	                <tbody>
	                	<tr>
	                		<th><spring:message code="order.payment.card.info1.5" /></th>
	                		<td><spring:message code="order.payment.card.info1.6" /></td>
	                		<td><spring:message code="order.payment.card.info1.7" /></td>
	               		</tr>
	                	<tr>
	                		<th><spring:message code="order.payment.card.info1.8" /></th>
	                		<td><spring:message code="order.payment.card.info1.9" /></td>
	                		<td><spring:message code="order.payment.card.info1.10" /></td>
	               		</tr>               		
	                </tbody>
	             </table>
	             
	             <p class="txtSize12"><spring:message code="order.payment.card.info1.11" /></p>  
			</div>	
			
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info1.12" /></h3>
				<p><spring:message code="order.payment.card.info1.13" /></p>
				<ul class="issueBox">
					<li><em>01.</em><spring:message code="order.payment.card.info1.14" /></li>
					<li><em>02.</em><spring:message code="order.payment.card.info1.15" /></li>
					<li><em>03.</em><spring:message code="order.payment.card.info1.16" /></li>
					<li><em>04.</em><spring:message code="order.payment.card.info1.17" /></li>
				</ul>
				<ul class="text-list02">
					<li class="fc_gray"><spring:message code="order.payment.card.info1.18" /></li>
					<li><spring:message code="order.payment.card.info1.19" /></li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<article id="lypopCardPayClick" class="layer-popup lypopCardinfo lypopCardPayClick">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info3" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info2.1" /></h3>
				<p><spring:message code="order.payment.card.info2.2" /></p>
				
				<div class="txtsBox">					
					<h4><spring:message code="order.payment.card.info2.3" /></h4>
					<table class="board-list list_left">
		                <colgroup>                     
		                  <col style="width:110px">
		                  <col style="width:300px">
		                  <col style="width:110px">
		                  <col style="width:">
		                </colgroup>
		                
		                <tbody>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.4" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_samsung.png" alt="삼성카드"></td>
		                		<td><spring:message code="order.payment.card.info2.5" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_citibank.png" alt="한미카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.6" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_keb.png" alt="외환카드"></td>
		                		<td><spring:message code="order.payment.card.info2.7" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_suhyupbank.png" alt="수협카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.8" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_lotte.png" alt="롯데카드"></td>
		                		<td><spring:message code="order.payment.card.info2.9" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_jbbank.png" alt="전북카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.10" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_hyunda.png" alt="현대카드"></td>
		                		<td><spring:message code="order.payment.card.info2.11" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_kjbank.png" alt="광주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.12" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan.png" alt="신한카드"></td>
		                		<td><spring:message code="order.payment.card.info2.13" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_jejubank.png" alt="제주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.14" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_citibank.png" alt="시티카드"></td>
		                		<td><spring:message code="order.payment.card.info2.15" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan.png" alt="조흥카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.16" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinsegae.png" alt="신세계한미카드"></td>
		                		<td><spring:message code="order.payment.card.info2.17" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan_lg.png" alt="신한(구LG)"></td>	                		
		               		</tr>    		
		                </tbody>
		             </table>
	             </div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.18" /></h4>
					<div class="txts">
						<ul class="text-list02 txtTypeGray">
							<li><spring:message code="order.payment.card.info2.19" /></li>
							<li><spring:message code="order.payment.card.info2.20" /></li>
							<li><spring:message code="order.payment.card.info2.21" /></li>							
						</ul>
					</div>
				</div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.22" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.23" />
					</p>
				</div>

				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.24" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.25" />
					</p>
				</div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.26" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.27" />
					</p>
				</div>				
										
				
				<div class="txtsBox">
					<ul class="text-list02">					
						<li><spring:message code="order.payment.card.info1.19" /></li>
					</ul>
				</div>
				
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<article id="lypopCardPaysf" class="layer-popup lypopCardinfo lypopCardPaysf">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info3" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info3.1" /></h3>
				<p class="mgB10"><spring:message code="order.payment.card.info3.2" /></p>
				<p class="mgB10"><spring:message code="order.payment.card.info3.3" /></p>
				<p><spring:message code="order.payment.card.info3.4" /></p>
				
				<ul class="text-list01">
					<li><spring:message code="order.payment.card.info3.5" />
						<ul class="text-list02">
							<li><spring:message code="order.payment.card.info3.6" /></li>
							<li><spring:message code="order.payment.card.info3.7" /></li>
							<li><spring:message code="order.payment.card.info3.8" /></li>							
						</ul>
					</li>
					<li><spring:message code="order.payment.card.info3.9" />
						<ul class="issueBox">
							<li><em>01.</em><spring:message code="order.payment.card.info3.10" /></li>
							<li class="fc_red">
								<span class="chkTxt posFt"><spring:message code="order.payment.card.info3.11" /></span>
								<em>02.</em><spring:message code="order.payment.card.info3.12" />
								<span class="chkTxt posEnd"><spring:message code="order.payment.card.info3.13" /></span>
							</li>
							<li><em>03.</em><spring:message code="order.payment.card.info3.14" /></li>
							<li><em>04.</em><spring:message code="order.payment.card.info3.15" /></li>
						</ul>
						
					</li>
					<li><spring:message code="order.payment.card.info3.16" />
						<div class="btnBanks">
							<a href="http://www.bccard.com" target="_blank" class="btn sm gray bankBc"><spring:message code="order.payment.card.info3.17" /></a>
							<a href="http://kbcard.kbstar.com/quics?asfilecode=5023&_nextPage=page=card&weblog=introAf0" target="_blank" class="btn sm gray bankKb"><spring:message code="order.payment.card.info3.18" /></a>
							<a href="http://ccd.wooribank.com/ccd/psn/isp/wdccd330_01c.jsp?q=C0A8582A189E027590D52B4B499EC3F9A33727D16DBE8F" target="_blank" class="btn sm gray bankUri"><spring:message code="order.payment.card.info3.19" /></a>
						</div>
					
					</li>
				</ul>							
				
				<ul class="text-list02">
					<li class="fc_gray"><spring:message code="order.payment.card.info3.20" /></li>
					<li><spring:message code="order.payment.card.info1.19" /></li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>


<article id="lyPopOrderFail" class="layer-popup lyPopOrderFail">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2><spring:message code="order.popup.fail.ttl" /></h2>
		<div class="layer-cont ly-box">
			<div class="mgBoxSy01"><spring:message code="order.popup.fail.info.1" /></div>
			<div class="mgBoxSy02"><spring:message code="order.popup.fail.info.2" /><br><span class="txtTime"><spring:message code="order.popup.fail.info.3" /></span></div>
			<div class="lyBtnArea">
				<a href="/" class="btn"><spring:message code="order.popup.fail.btn.home" /></a>
				<a href="/cart/goods/list" class="btn"><spring:message code="order.popup.fail.btn.cart" /></a>
				<a href="/order/orderform/new" class="btn fill"><spring:message code="order.popup.fail.btn.ord" /></a>
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<%@ include file="/WEB-INF/views/common/layerpop/zipcode.jsp"%>