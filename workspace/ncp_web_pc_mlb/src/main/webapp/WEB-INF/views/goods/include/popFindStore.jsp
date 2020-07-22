
<ncp:codes group="SIDO" var="sidoList"/>

<article id="layerPopupFindStoreMap" class="layer-popup findStore-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="goods.option.popup.store.ttl"/></h2>
		<div class="layer-cont scroll">
			
			<div class="findStore-popWrap">
				<%-- <ul class="text-list02 col-type01">
					<li><spring:message code="goods.option.popup.store.lbl.txt2"/></li>
				</ul> --%>

				<c:if test="${pageContext.response.locale.language eq 'zh'}">
				<%-- 온라인매장 S --%>
				<h3 class="title04"><spring:message code="goods.option.popup.store.ttl2" text="해외 제휴 온라인 사이트" /></h3>
				<div class="data-tbl01">
					<table>
						<caption>해외 제휴 온라인 정보표.</caption>
						<colgroup>
							<col style="width:150px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><!--<spring:message code="goods.option.popup.store.lbl.mall" text="쇼핑몰" />-->Tmall</th>
								<td><a href="https://mlbyd.tmall.com/index.htm?spm=a1z10.5-b-s.w5001-21749495316.2.df2557a2oRCRyR&scene=taobao_shop" target="_blank" title="new window">https://www.tmall.com</a></td>
							</tr>
						</tbody>
					</table>
				</div>			
				<%-- 온라인매장 E --%>	
				</c:if>
				
				<!-- 매장검색 S -->
				<dl class="search-wrap03 findStoreRow size-fix">
					<dt><spring:message code="goods.option.popup.store.lbl.brnd.select"/></dt>
					<dd>
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:90px;"><span>MLB</span></button>
							<ul>
								<li><a href="#" onclick="javascript:setBrndSearchStore('M', 'store');">MLB</a></li>
								<li><a href="#" onclick="javascript:setBrndSearchStore('I', 'store');">MLB KIDS</a></li>
							</ul>
							<input type="hidden" name="findStoreShopBrnd" id="findStoreShopBrnd" value="M">
						</div>
					</dd>
					<dt><spring:message code="goods.option.popup.store.lbl.region.select"/></dt>
                    <c:choose>
						 <c:when test="${pageContext.response.locale.language eq 'zh'}">
							 <dd>
						 </c:when>
					     <c:otherwise>
                  			 <dd>
 						  </c:otherwise>
						 </c:choose>		
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:100px;"><span id="spanCntry"><spring:message code="goods.common.lbl.all"/></span></button>
                                <c:choose>
									<c:when test="${pageContext.response.locale.language eq 'zh'}">
									<ul>
									</c:when>
									<c:otherwise>
                  						<ul>
 									</c:otherwise>
								</c:choose>		
					 
								<li><a href="#" onclick="javascript:setCntrySearchStore('' , 'store');"><span><spring:message code="goods.common.lbl.all"/></a></li>
								<c:if test="${pageContext.response.locale.language eq 'ko'}">
									<li><a href="#" onclick="javascript:setCntrySearchStore('kr', 'store');" title="대한민국">대한민국</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('hk', 'store');" title="Hongkong(SAR of China)">Hongkong(SAR of China)</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('mc', 'store');" title="Macau(SAR of China)">Macau(SAR of China)</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('tw', 'store');" title="Taiwan,China">Taiwan,China</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('th', 'store');" title="THAILAND">THAILAND</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('vn', 'store');" title="VIETNAM">VIETNAM</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('cn', 'store');" title="CHINA">CHINA</a></li>
								</c:if>
								<c:if test="${pageContext.response.locale.language eq 'en'}">
									<li><a href="#" onclick="javascript:setCntrySearchStore('cn', 'store');" title="CHINA">CHINA</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('hk', 'store');" title="Hongkong(SAR of China)">Hongkong(SAR of China)</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('kr', 'store');" title="KOREA">KOREA</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('mc', 'store');" title="Macau(SAR of China)">Macau(SAR of China)</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('tw', 'store');" title="Taiwan,China">Taiwan,China</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('th', 'store');" title="THAILAND">THAILAND</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('vn', 'store');" title="VIETNAM">VIETNAM</a></li>
								</c:if>
								<c:if test="${pageContext.response.locale.language eq 'zh'}">
									<li><a href="#" onclick="javascript:setCntrySearchStore('cn', 'store');" title="中國">中國</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('hk', 'store');" title="中國香港">中國香港</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('kr', 'store');" title="大韩民国">大韩民国</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('mc', 'store');" title="中國澳門">中國澳門</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('tw', 'store');" title="中國台灣">中國台灣</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('th', 'store');" title="泰国">泰国</a></li>
									<li><a href="#" onclick="javascript:setCntrySearchStore('vn', 'store');" title="越南">越南</a></li>
								</c:if>
							</ul>
						</div>
						<input type="hidden" name="findStoreCntry" id="findStoreCntry" value="">
						<!-- select -->
						<div class="select-style02 d_select" id="divFindSido">
							<button type="button" id="btnSido" class="d_select_sel" style="width:140px;"><span id="spanSido"><spring:message code="goods.common.lbl.all"/></span></button>
							<ul id="findSidoList">
								<li><a href="#" onclick="javascript:setSidoCdSearchStore('','store')"><spring:message code="goods.common.lbl.all"/></a></li>
								<script type="text/javascript">
									$("#findSidoList li:not(:first)").remove();									
									var sdlist = $.parseJSON('${ncpfn:marshallingJson(sidoList)}');
									if(sdlist != null && sdlist.length != 0){
										for(var j=0; j<sdlist.length; j++){
											$("#findSidoList").append("<li><a href=\"#\" onclick=\"javascript:setSidoCdSearchStore('"+sdlist[j].cd+"','store')\" title=\""+sdlist[j].cdNm+"\">"+sdlist[j].cdNm+"</a></li>");
										}										
									}									
								</script>							
							</ul>
						</div>
						<input type="hidden" name="findStoreSidoCd" id="findStoreSidoCd" value="">
					</dd>
					<dt id="dtShopGbn"><spring:message code="goods.option.popup.store.lbl.shopGbn.select"/></dt>
					<dd>
						<!-- select -->
						<div class="select-style02 d_select" id="divShopGbn">
							<button type="button" class="d_select_sel" style="width:110px;"><span id="spanGbn"><spring:message code="goods.common.lbl.all"/></span></button>
							<ul>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('','store');"><spring:message code="goods.common.lbl.all"/></a></li>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('C','store');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.jic"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.jic"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('A','store');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.bek"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.bek"/></a></li>
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('BF','store');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.dea"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.dea"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('DE','store');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.san"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.san"/></a></li>	
								<li><a href="#" onclick="javascript:setShopGbnSearchStore('ADF','store');" title='<spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.myn"/>'><spring:message code="goods.option.popup.store.pickup.lbl.select.gbn.myn"/></a></li>								
							</ul>
							<input type="hidden" name="findStoreShopGbn" id="findStoreShopGbn" value="">
						</div>
						<!-- //select -->
					</dd>
					<!-- dt><spring:message code="goods.option.popup.store.lbl.name.input"/></dt -->
					<dd>
						<strong><spring:message code="goods.option.popup.store.lbl.name.input"/></strong>
						<div class="search-input03">
							<input type="search" title="매장 검색" id="findStoreShopName" val=""/>
							<button type="button" onclick="javascript:findStoreMap();"><spring:message code="goods.common.btn.search"/></button>
						</div>
					</dd>
				</dl>
				<!--//매장검색 E -->
				
				<h3 class="title04"><spring:message code="goods.option.popup.store.ttl1"/></h3>
				
				<!-- 매장찾기 S -->
				<div class="findStoreListWrap">
					<div class="no-result" style="display:none;"></div>
					<!--매장리스트 S -->
					<div class="findStoreList">
						<ul id="ulfindStore">							
						</ul>
					</div>
					<!--//매장리스트 E -->

					<!--매장지도 S -->
					<div class="findStoreMap">
						<div id="mapFooter" style="height:100%;">
							<!-- 지도 영역 -->
						</div>
					</div>
					<!--//매장지도 E -->
				</div>
				<!--//매장찾기 E -->
				
				<div class="btn-wrap">
					<a href="javascript:;" class="btn fill d_layer_close"><spring:message code="goods.common.btn.close"/></a>
				</div>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="goods.common.btn.close"/></button>
		</div>
	</section>
</article>

<script>

$(document).ready(function(){
	setCntrySearchStore('', 'store');
	findStoreMap();
});

function setSidoCdSearchStore(sidoCd, location){
	if(location == 'pickup'){
		$("#pickupStoreSidoCd").val(sidoCd);
	}
	if(location == '' || location == 'store'){
		$("#findStoreSidoCd").val(sidoCd);
	}
	findStoreMap();
}
function setShopGbnSearchStore(shopGbn, location){
	if(location == 'pickup'){
		$("#pickupStoreShopGbn").val(shopGbn);
	}
	if(location == '' || location == 'store'){
		$("#findStoreShopGbn").val(shopGbn);
	}
	findStoreMap();
}
function setBrndSearchStore(brnd, location){
	if(location == 'pickup'){
	}
	if(location == '' || location == 'store'){
		$("#findStoreShopBrnd").val(brnd);
	}
	setCntrySearchStore('' , 'store');
	$("#spanCntry").html("<spring:message code='goods.common.lbl.all' />");
}
function setCntrySearchStore(cntry, location){
	if(location == 'pickup'){
	}
	if(location == '' || location == 'store'){
		setSidoCdSearchStore('','store');
		$("#spanSido").html("<spring:message code='goods.common.lbl.all' />");
		if(cntry == 'kr') {
			$("#divFindSido").show();
			$("#divShopGbn").show();
			$("#dtShopGbn").show();
		}
		else {
			$("#divFindSido").hide();
			$("#divShopGbn").hide();
			$("#dtShopGbn").hide();
		}
		$("#findStoreShopGbn").val('');
		$("#spanGbn").html("<spring:message code='goods.common.lbl.all' />");
		$("#findStoreCntry").val(cntry);
	}
	
	findStoreMap();
}

function findStoreMap(){
	var p_brndCd = $("#layerPopupFindStoreMap").find("#findStoreShopBrnd").val();
	var p_cntryCd = $("#layerPopupFindStoreMap").find("#findStoreCntry").val();
	var p_sidoCd = $("#layerPopupFindStoreMap").find("#findStoreSidoCd").val();
	var p_shopGbn = $("#layerPopupFindStoreMap").find("#findStoreShopGbn").val();
	var p_srchKeyword = $("#layerPopupFindStoreMap").find("#findStoreShopName").val().trim();
	
	/* 특수문자 체크  */	
    var hdivCheck = /.*[<>/\\￦:]+.*/gm;    
    if (hdivCheck.test(p_srchKeyword)) {    	
    	//특수문자 ￦/ : < > 는 사용할 수 없습니다.    	
//    	alert("<spring:message code='display.js.footer.msg.alert1'/>");
//        return false;
    }	
    if(p_srchKeyword.length > 10){
    	//10자까지 입력하실 수 있습니다.    	
//    	alert("<spring:message code='display.js.footer.msg.alert2'/>");
//        return false;
    }
    
	mapFooter = new google.maps.Map(document.getElementById("mapFooter"), {
	    zoom: 16,
	    center: {lat:37.532, lng:127.024}, //대한민국
	});
	
	$.ajax({
		type : "POST",
		async : false,
		url : "/goods/content/getGoodsShopList.json",
		data : {brndId:p_brndCd, cntryCd:p_cntryCd, sidocd:p_sidoCd, shopType:p_shopGbn, srchKeyword:p_srchKeyword}, 
		success : function(data) {
			if(data.totalCnt > 0){
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".no-result").hide();
				$("#ulfindStore").empty();
				var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd;
				var isDefault = false;
				for(var i=0; i<data.shopList.length; i++){				
					el="<li>";
					shopNm = data.shopList[i].shopNm;
					baseAddr = data.shopList[i].baseAddr;
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					detailAddr = data.shopList[i].detailAddr;
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					telNo1 = data.shopList[i].shopTelAreaNo;
					telNo2 = data.shopList[i].shopTelTlofNo;
					telNo3 = data.shopList[i].shopTelTlofWthnNo;
					shour = data.shopList[i].bsnBegHhmm;
					ehour = data.shopList[i].bsnEndHhmm;
					lttd = data.shopList[i].lttd;
					lgtd = data.shopList[i].lgtd;
					
					if(typeof(shopNm) === "undefined"){shopNm = "";}
					if(typeof(baseAddr) === "undefined"){baseAddr = "";}
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					if(typeof(detailAddr) === "undefined"){detailAddr = "";}
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					if(typeof(telNo1) === "undefined" || typeof(telNo2) === "undefined" || typeof(telNo3) === "undefined"){telNo = "";}else{telNo=telNo1+"-"+telNo2+"-"+telNo3;}					
					if(typeof(shour) === "undefined" || typeof(ehour) === "undefined"){hour = "";}else{hour=shour+"~"+ehour;}
					
					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){
						el+="<div class=\"store-name\"><a href=\"#\" onclick=\"javascript:setLocationStoreFooter("+lttd+", "+lgtd+")\">"+shopNm+"</a></div>";
					}else{
						el+="<div class=\"store-name\">"+shopNm+"</div>";
					}					
					
					el+="<ul class=\"store-address-info\">";
					el+="<li>"+baseAddr+"&nbsp;"+detailAddr+"</li>";
					el+="<li>"+telNo+"</li>";
					el+="<li><span><spring:message code='goods.js.goods.option.popup.store.lbl.txt7'/></span> "+hour+"</li></ul>";
					el+="</li>";
										
					//Default : 역삼직영점 매장   
					if(data.shopList[i].shopId == "X30008"){						
						$("#ulfindStore").prepend(el);
						mapFooter.setCenter(new google.maps.LatLng(lttd, lgtd));
						isDefault = true;
					}else{
						$("#ulfindStore").append(el);
					}
					
					if(isDefault == false){
						mapFooter.setCenter(new google.maps.LatLng(data.shopList[0].lttd, data.shopList[0].lgtd));												
					}
										
					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){										
						var marker = new google.maps.Marker({
						    position: {lat: lttd, lng: lgtd},
						    map: mapFooter,
						    title:shopNm
						});				
					}				
				}				
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".findStoreList").show();
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".findStoreMap").show();				
			}else{
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".findStoreList").hide();
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".findStoreMap").hide();
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".no-result").text("<spring:message code='goods.js.goods.option.popup.store.lbl.txt9'/>");
				$("#layerPopupFindStoreMap").find(".findStoreListWrap").find(".no-result").show();				
			}		
		},
		error: function( pa_data, status, err ) {
            alert("error forward : "+err+" ,status="+status);
        }
	});		
}

var mapFooter;

function setLocationStoreFooter(lttd, lgtd){
	mapFooter.setCenter(new google.maps.LatLng(lttd, lgtd));
}

</script>

