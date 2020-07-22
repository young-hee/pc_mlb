<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/order/order.util.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/order/order.info.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="//script.about.co.kr/templates/script/cm/adbayBuyController_v.1.1.js"></script>

<script type="text/javascript" src="/javascript/message/order_${pageContext.response.locale.language}.js?v=${_version}"></script>

<%@ include file="/WEB-INF/views/main/include/recoPick.jsp"%>
<!-- 컨텐츠 시작 -->
<div class="contain od list" id="contain">
	<div class="container">
		<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false"/>
		
		<main class="contents"  id="contents">
			<section>
				<ul class="stepInfoBox">
					<li class="on"><spring:message code="cart.js.txt.title" /></li>
					<li class="on"><spring:message code="order.js.txt.order.payment" /></li>
					<li class="on"><spring:message code="order.js.txt.order.complete" /></li>
				</ul>
				
				<div class="orderContents orderComplete" id="orderInfoViewLayer">
				</div>
				<script id="orderInfoView" type="text/x-jsrender">
{{for info.ordGodExtendList itemVar="~item"}}
				<input type="hidden" name="orderGod" erpItmNo="{{:~item.erpGodNo}}" price="{{:~item.rtlPrc}}" disable/>	
				<input type="hidden" name="naverEpItems" itmno="{{:~item.godNo}}" itmnm="{{:~item.godNm}}" price="{{:~item.rtlPrc}}" qty="{{:~item.ordQty}}" disable/>	
{{/for}}
				<div class="orderComCont">
					<div class="orderTxtBox">
						{{if info.ord.ordTpCd=='SHOP_PKUP_ORD'}}
							<strong>{{:~messages['order.js.txt.complete.pickup.title']}}</strong>
							<p>{{:~messages['order.js.txt.complete.info3']}}
						{{else info.ord.ordTpCd=='RESVE_ORD'}}			
							<strong>{{:~messages['order.js.txt.complete.reserve.title']}}</strong>
							<p>{{:~messages['order.js.txt.complete.info2']}}
						{{else}}			
							<strong>{{:~messages['order.js.txt.complete.title']}}</strong>
							<p>{{:~messages['order.js.txt.complete.info1']}}
						{{/if}}
						<br />{{:~messages['order.js.txt.complete.info0']}}</p>
					</div>
					<div class="orderBeneBox">
						{{:~messages['order.js.txt.complete.benefit']}}
					</div>
					<div class="orderNumBox">
	                    	{{:~messages['order.js.txt.complete.ord.no']}} : {{:info.ordNo}}
	            		<a href="/mypage/order/<c:out value="${ordNo}"/>/view" class="btn sm">주문상세내역</a>
	            	</div>
				
				<div class="orderInfoTable">
					<table class="board-write">
						<colgroup>
	                    	<col style="width:170px">
	                        <col style="width:130px">  
	                        <col style="width:225px"> 
	                        <col style="width:">                                              
	                	</colgroup>
						<tbody>
							<tr>
	                        	<th class="titSel">{{:~messages['order.js.txt.complete.ord.date']}}</th>
	                            <td colspan="3">{{:info.ordDate}}</td>
	                    	</tr>
							<tr class="lineB">
	                        	<th class="titSel tMg0">{{:~messages['order.js.txt.complete.ord.goods']}}</th>
	                            <td colspan="3" class="tMg0" id="ordDetail"></td>
								<input type='hidden' id='ordNm1' value='{{:info.ordGodList[0].godNm}}'/>
								<input type='hidden' id='ordSize' value={{:info.ordGodList.length}} />
	                        </tr>
							<tr>
	                            <th rowspan="8" class="titSel lineB">{{:~messages['order.js.txt.complete.pay.info']}}</th>
	                            <th>{{:~messages['order.js.txt.complete.ord.goods.price']}}</th>
	                        	<td colspan="2" class="fs_r">{{:~otool.commaInAmount(info.ord.saleSumAmt)}}{{:~messages['common.js.crncy']}}</td>
	                        </tr>
							<tr>                                            
	                        	<th class="tMg0">{{:~messages['order.js.txt.complete.ord.dc']}}</th>
	                        	<td colspan="2" class="fs_r fc_red tMg0">{{:~otool.commaInAmount(info.ord.bskCpnDcSumAmt+info.ord.bundleDcSumAmt+info.ord.crsDcSumAmt+info.ord.dlvCstCpnDcSumAmt+info.ord.godCpnDcSumAmt)}}{{:~messages['common.js.crncy']}}</td>
	                        </tr>
							<tr>
	                        	<th class="lineB tMg0">{{:~messages['order.js.txt.delivery.cost']}}</th>
	                        	<td class="lineB fs_r tMg0" colspan="2">{{:~otool.commaInAmount(info.ord.dlvCstSumAmt+info.ord.dlvCstCpnDcSumAmt)}}{{:~messages['common.js.crncy']}}</td>
	                    	</tr>
							<tr>
								<th rowspan="5">{{:~messages['order.js.txt.complete.ord.total.pay']}}</th>
								<th class="fc_gray">{{:~messages['order.js.txt.complete.use.mileage']}}</th>
								<td class="fs_r">{{:~otool.commaInAmount(info.ord.unityPntUseSumAmt)}}{{:~messages['common.js.crncy']}}</td>
							</tr>
							<tr>
								<th class="tMg0 fc_gray">{{:~messages['order.js.txt.complete.use.point']}}</th>
								<td class="fs_r tMg0">{{:~otool.commaInAmount(info.ord.webpntUseSumAmt)}}{{:~messages['common.js.crncy']}}</td>
							</tr>
							{{if info.payList itemVar="~pay"}}
							{{for info.payList itemVar="~pay" ~payCodeList=payCodeList}}
								{{if ~pay.payMnCd == 'VIRTL_BNK_ACCT_PAY'}}
									<tr>
										<th class="tMg0 fc_gray">
										{{for ~payCodeList itemVar="~item"}}
											{{if ~pay.payMnCd == ~item.cd}}
												{{:~item.cdNm}}({{:~pay.bnkNm}})
											{{/if}}
										{{/for}}
										</th>
										<td class="fs_r tMg0 totalSel"><strong>{{:~otool.commaInAmount(~pay.payCrncyPayAmt)}}</strong>{{:~messages['common.js.crncy']}}	</td>
									</tr>
									<tr>
										<th class="tMg0 fc_gray">{{:~messages['order.js.txt.complete.msg.4.1']}}</th>
	                                    <td class="fs_r tMg0">{{:~pay.bnkAcctNo}} {{:~pay.bnkNm}}</td>
									</tr>
									<tr class="lineB">
										<th class="tMg0 fc_gray">{{:~messages['order.js.txt.complete.msg.4.2']}}</th>
	                                    <td class="fs_r tMg0">{{:~pay.payTmlmtDt}}</td>
									</tr>
								{{else ~pay.payMnCd == 'RLTM_BNK_ACCT_PAY'}}
									<tr></tr>
									<tr>
										<th class="tMg0 fc_gray">
										{{for ~payCodeList itemVar="~item"}}
											{{if ~pay.payMnCd == ~item.cd}}
												{{:~item.cdNm}}({{:~pay.bnkNm}})
											{{/if}}
										{{/for}}
										</th>
										<td class="fs_r tMg0 totalSel"><strong>{{:~otool.commaInAmount(~pay.payCrncyPayAmt)}}</strong>{{:~messages['common.js.crncy']}}	</td>
									</tr>
									<tr class="lineB">
										<th class="tMg0 fc_gray">{{:~messages['order.js.txt.complete.msg.4.1']}}</th>
	                                    <td class="fs_r tMg0">{{:~pay.bnkAcctNo}} {{:~pay.bnkNm}}</td>
									</tr>
								{{else}}
									<tr></tr>
									<tr></tr>
									<tr class="lineB">
										<th class="tMg0 fc_gray">
										{{for ~payCodeList itemVar="~item"}}
											{{if ~pay.payMnCd == ~item.cd}}
												{{:~item.cdNm}}
											{{/if}}
										{{/for}}
										</th>
										<td class="fs_r tMg0 totalSel"><strong>{{:~otool.commaInAmount(~pay.payCrncyPayAmt)}}</strong>{{:~messages['common.js.crncy']}}	</td>
									</tr>
								{{/if}}
							{{/for}}
							{{else}}
								<tr></tr>
								<tr></tr>
								<tr class="lineB"></tr>
							{{/if}}
							<tr class="lineT">
								<th class="titSel">{{:~messages['order.js.txt.complete.earn.mileage']}}</th>
								<td colspan="3" class="fs_r fc_red">{{:~otool.commaInAmount(info.ord.unityPntAccmlSumAmt)}}{{:~messages['common.js.crncy']}}</td>
							</tr>
						</tbody>

					</table>
				</div>
				



			{{if info.ord.ordTpCd=='SHOP_PKUP_ORD'}}
				<div class="orderInfoTable">
					<table class="board-write">
						<colgroup>
	                    	<col style="width:170px">
	                        <col style="width:">                       
	                    </colgroup>
	                
	                    <tbody>
							<tr>
								<th class="titSel">{{:~messages['order.js.txt.complete.picup.msg.1.4']}}</th>
								<td class="storeInfoSel">
									<p class="StoreInfo">
										<strong class="name">{{:info.sysShop.shopNm}}</strong>
										<a href="#" onclick="~otool.showPopupShop('{{:info.sysShop.shopId}}');"  class="btnMap"><img src="${_resourceURL}static/images/od/icon_location.png" alt="지도보기"></a><br>
										{{:info.sysShop.baseAddr}}  {{if info.sysShop.shopTelAreaNo}}<em>/</em>{{:info.sysShop.shopTelAreaNo}}-{{:info.sysShop.shopTelTlofNo}}-{{:info.sysShop.shopTelTlofWthnNo}}{{/if}}<br>
										{{:~messages['order.js.txt.script.msg4']}}  <em>|</em>  ({{if info.holdyYn=='N'}}{{:~messages['common.js.week.day']}}{{else}}{{:~messages['common.js.holi.day']}}{{/if}}) {{:~otool.timeConvert(info.shopBegHhmm)}} ~ {{:~otool.timeConvert(info.shopEndHhmm)}}
									</p>
									<p class="checkMg">{{:~messages['order.js.txt.complete.picup.msg.1.1']}}</p>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			{{/if}}


			<ul class="text-list02 mt40">
				{{if info.shopDlvYn == 'Y'}}
					<li>{{:~messages['order.js.txt.complete.msg.2.1']}}</li>
				{{/if}}
				{{if info.ord.ordTpCd=='RESVE_ORD'}}
					<li>{{:~messages['order.js.txt.complete.reserve.msg.1']}}</li>
					<li>{{:~messages['order.js.txt.complete.reserve.msg.2']}}</li>
					<li>{{:~messages['order.js.txt.complete.reserve.msg.3']}}</li>
				{{/if}}
				{{if info.ord.ordTpCd=='SHOP_PKUP_ORD'}}
					<li>{{:~messages['order.js.txt.complete.pickup.msg.1']}}</li>
					<li>{{:~messages['order.js.txt.complete.pickup.msg.2']}}</li>
					<li>{{:~messages['order.js.txt.complete.pickup.msg.3']}}</li>
				{{/if}}
				{{for info.payList itemVar="~pay" ~payCodeList=payCodeList}}
					{{if ~pay.payMnCd == 'VIRTL_BNK_ACCT_PAY'}}
						<li>{{:~messages['order.js.txt.complete.bnk.msg.1']}}</li>
						<li>{{:~messages['order.js.txt.complete.bnk.msg.2']}}</li>
						<li>{{:~messages['order.js.txt.complete.bnk.msg.3']}}</li>
					{{/if}}
				{{/for}}
			</ul>

			<div class="btn-wrap">
				<a href="/" class="btn-style03">{{:~messages['order.js.txt.go.shopping']}}</a><a href="/mypage/order/<c:out value="${ordNo}"/>/view" class="btn-style02">{{:~messages['order.js.txt.complete.msg.3.1']}}</a>
			</div>			

 
			</div>
			</script>
			</section>
		</main>
	</div>
</div>

<%-- Facebook Pixel Code 19/10/28 신규 광고 집행 스크립트 --%>
<script>
	fbq('track', 'Purchase', {value: 0.00, currency: 'USD'});
</script>
<%-- End Facebook Pixel Code --%>

<!-- WIDERPLANET PURCHASE SCRIPT START 2019.2.8 -->
<div id="wp_tg_cts" style="display:none;"></div>
<script type="text/javascript">
function naverEp() {
	var wptg_tagscript_vars = wptg_tagscript_vars || [];
	
	var orderItems = $("input[name='naverEpItems']");
	var tempItems = [[]];
	for(var i = 0; i < orderItems.length; i++) {
		tempItems[i] = {i:$(orderItems[i]).attr("itmno"), t:$(orderItems[i]).attr("itmnm"), p:$(orderItems[i]).attr("price"), q:$(orderItems[i]).attr("qty")};
	}
	var strItems = JSON.stringify(tempItems);
	//console.log("test : " + JSON.stringify(tempItems));
	wptg_tagscript_vars.push(
	(function() {
	 return {
	  wp_hcuid:"${_user.mbr.mbrNo}",   /*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.
	     *주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
	  ti:"39428",
	  ty:"PurchaseComplete",
	  device:"web"
	  ,items:strItems
	    //{i:"상품 ID", t:"상품명 ", p:"단가 ", q:"수량 "} /* 첫번째 상품  - i:상품 식별번호 (Feed로 제공되는 식별번호와 일치 ) t:상품명  p:단가  q:수량  */
	   //,{i:"상품 ID", t:"상품명 ", p:"단가 ", q:"수량 "} /* 첫번째 상품  - i:상품 식별번호 (Feed로 제공되는 식별번호와 일치 ) t:상품명  p:단가  q:수량  */
	 };
	}));
}
</script>
<script type="text/javascript" async src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js"></script>
<!-- // WIDERPLANET PURCHASE SCRIPT END 2019.2.8 -->
		
<script language="JavaScript">
if(localStorage.amountStroageData){
	_TRK_PI = "ODR";
	_TRK_OA = localStorage.amountStroageData;/* Order Amount(s) with ';' Separated */
	
	/* TRACKING SCRIPT BIZMAILER Tracker ver 1.0 *//*X*/
	/* COPYRIGHT 2002-2009 BIZSPRING INC. *//*X*/
	/* DO NOT MODIFY THIS SCRIPT. *//*X*/
	
	/* TRACKING SCRIPT BIZMAILER Tracker ver 1.1 *//*X*//* COPYRIGHT 2002-2018 DAOU INC. *//*X*//* DO NOT MODIFY THIS SCRIPT. *//*X*/var _BM_RK = "";if(typeof(_TRK_RK)!="undefined"&&_TRK_RK!="") _BM_RK=_TRK_RK;var _bm_MK="1491273944636PBqyKRu";var _bm_DOMAIN="trk.bizmailer.co.kr";var _bm_bMSIE=(document.all)?true:false;var _bm_bJS12=(window.screen)?true:false;function _bm_escape(_str){ var str, ch; var bEncURI="N"; eval("try{bEncURI=encodeURI('Y');}catch(_e){}"); if(bEncURI=="Y") str=encodeURI(_str); else str=escape(_str); str=str.split("+").join("%2B"); str=str.split("/").join("%2F"); str=str.split("&").join("%26"); str=str.split("?").join("%3F"); str=str.split(":").join("%3A"); str=str.split("#").join("%23"); return str;}function _bm_make_code(_BM_SERVER){ var du=self.document.location.href; if(du.substr(0,4)=="file") return ""; if(!du) du=""; var tc=""; var prtcl=document.location.protocol.indexOf("https")!=-1?"http://":"http://"; tc=tc+prtcl+_BM_SERVER+"/tracker/site/tracker.do"; tc=tc+"?mk="+_bm_MK; tc=tc+"&du="+_bm_escape(du); if((typeof _TRK_PI)!="undefined"&&_TRK_PI!="") tc=tc+"&pi="+_TRK_PI; else return ""; if((typeof _TRK_OA)!="undefined"&&_TRK_OA!="") {tc=tc+"&oa="+_bm_escape(_TRK_OA);} else { if (document.location.href.indexOf("orderend")>0) { try { _TRK_OA = document.getElementById("mk_totalprice").innerHTML; _TRK_OA = _TRK_OA.replace(/[<][^>]*[>]/gi, "").replace(/[^0-9]/g,'');  } catch (ee) {}  tc=tc+"&oa="+_TRK_OA; } } if((typeof _TRK_RK)!="undefined"&&_TRK_RK!="") tc=tc+"&ci="+_TRK_RK; if((typeof _TRK_SX)!="undefined"&&_TRK_SX!="") tc=tc+"&cx="+_TRK_SX; if((typeof _TRK_AG)!="undefined"&&_TRK_AG!="") tc=tc+"&ca="+_TRK_AG; return tc;}var _bm_code_base=_bm_make_code(_bm_DOMAIN + "");var _bm_img_base=new Image();if((typeof _TRK_PI)!="undefined"&&_TRK_PI!="") { if(_bm_bJS12==true) { if(_bm_bMSIE) {  _bm_img_base.src=_bm_code_base; } else {  setTimeout("_bm_img_base.src=_bm_code_base;",1); } } else { if(_bm_bMSIE) document.write('<div style=\"display: none\">'); document.write('<img src=\"'+_bm_code_base+'\" height=\"0\" width=\"0\">'); if(_bm_bMSIE) document.write('</div>'); }}/*X*//* END OF TRACKING SCRIPT */
}
</script>

<%@ include file="/WEB-INF/views/common/layerpop/shop.view.jsp"%>	
<script>
	var wptg_tagscript_vars = wptg_tagscript_vars || [];
	window.criteo_q = window.criteo_q || [];
	var naver_keyword_advertisement = true;
	
	$(document).ready(function() {
	
		orderinfo.load('<c:out value="${ordNo}"/>');
		
		if($("#ordSize").val() > 1) {
			var text = messageFormat(MESSAGES['order.js.txt.complete.ord.goods.detail'], $("#ordNm1").val(), $("#ordSize").val()-1);
			$("#ordDetail").html(text);
		} else {
			$("#ordDetail").html($("#ordNm1").val());
		}
		naverEp();
	});
</script>

<c:choose>
	<c:when test='${_locale eq "en"}'>
		<c:set var = "_olapic_data_apikey" value ="b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b" />
	</c:when>
	<c:when test='${_locale eq "zh"}'>
		<c:set var = "_olapic_data_apikey" value ="0f499dbda3dc7d8d63ba1cc127b8d24f4c8f39d81d4df544d68c225e7e16b4c9" />
	</c:when>
	<c:otherwise>
		<c:set var = "_olapic_data_apikey" value ="84ddeb156f96b575f3c235cff2d19a82461e8c150b57df811c493fb713f069e2" />
	</c:otherwise>
</c:choose>
<script type="text/javascript" data="olapic-checkout">
$(document).ready(function() {
	//==== Olapic Require: DO NOT CHANGE
	var olapicRequireCheckoutScript=(function(oHead){var onError=function(){throw new URIError('Olapic checkout script could not be loaded');};return function(olapicScriptSrc,onLoadCallback){var oScript=document.createElement('script');oScript.type='text\/javascript';oScript.src=olapicScriptSrc;oScript.async=true;oScript.onerror=onError;if(onLoadCallback){if(oScript.addEventListener){oScript.addEventListener('load',onLoadCallback,false);}else if(oScript.readyState){oScript.onreadystatechange=function(){if(!this.readyState||this.readyState==='loaded'||this.readyState==='complete'){onLoadCallback();}};}else{oScript.attachEvent('load',onLoadCallback);}}
	oHead.appendChild(oScript);};})(document.head||document.getElementsByTagName('head')[0]);
	
	// ==== Checkout Code:
	olapicRequireCheckoutScript('//photorankstatics-a.akamaihd.net/static/frontend/checkout/olapic.checkout.helper.js', function(){
	    // Initialization
	    olapicCheckout.init('${ _olapic_data_apikey }');
	
	    // Add the Products: Product loop starts. This is where you will store each product purchased info
		var products = $("input[name='orderGod']");
	    for(var i = 0; i < products.length; i++) {
		    olapicCheckout.addProduct($(products[i]).attr("erpItmNo"), $(products[i]).attr("price"));
	    }
	    // Product loop ends.
	
	    // Add the metadata/attributes
	    olapicCheckout.addAttribute('transactionId', '<c:out value="${ordNo}"/>');
	    olapicCheckout.addAttribute('currencyCode', 'KRW');
	    // Add Segmentation Values
	    olapicCheckout.addSegment('channel', 'PC');
	    // Send the information
	    if(products.length > 0) {
		    olapicCheckout.execute();
	    }
	});
});
</script>
