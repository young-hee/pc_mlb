/* ********************************************/
/* 1. 공통
/* ********************************************/
var _ordMinQty;
var _ordMaxQty;
var map;
var loginCallback;

$(document).ready(function(){
	
	//상품별 최소/최대구매수량 설정
	_ordMinQty = $("#qty").data("minOrdQty"); 
	_ordMaxQty = $("#qty").data("maxOrdQty");
	
	//상품 리뷰 목록 조회
	getReviewList('1','F','F');
	
	//배송 정책
	$("span[id='dmstcDlvCstExmStdrAmt']").text($("input[id='dmstcDlvCstExmStdrAmt']").val());
	$("span[id='dmstcDlvCst']").text($("input[id='dmstcDlvCst']").val());
	$("span[id='repairDlvCst']").text($("input[id='repairDlvCst']").val());
	$("span[name='dmstcDlvComNm']").text($("input[id='dmstcDlvComNm']").val());
	
	/*
	// 리뷰 팝업
	$("#btn_goodsReview").click(function(){
		layerPopup.popupOpenNow("#lypopGoodsReview");
	});
	// 상품상세 팜업
	$("#btn_goodsDetail").click(function(){
		layerPopup.popupOpenNow('#lypopGoodsDetail');
	});
	*/
	// 쿠폰 다운로드
	$("#btn_couponDownload").click(function(){
		var cpnLen = $("[name^=trCpnList]").length;
		// 하나면 바로 다운로드
		if(cpnLen == 1) {
			goodsCouponDownload($("#prmCpnGodPrmNo").val());
		} 
		// 아니면 팝업
		else {
			layerPopup.popupOpenNow('#lypopCuponDown');
		}
	});
	// 사이즈 가이드_일반
	$("#btn_sizeGuide_GNRL_GOD").click(function(){
		layerPopup.popupOpenNow('#layerPopupSizeGuide_GNRL_GOD');
	});
	// 핏 가이드_일반
	$("#btn_fitGuide_GNRL_GOD").click(function(){
		layerPopup.popupOpenNow('#layerPopupFitGuide_GNRL_GOD');
	});
	// 배송/교환/반품/환불 
	$("#btn_goodsGuide").click(function(){
		layerPopup.popupOpenNow('#lypopGoodsGuide');
	});
	$("#searchStoreKeyword").keypress(function(e){
		if(e.keyCode == 13){
			availableStore();	
		}		
	});
	
	//kc 카트 외부 html 내용 가져오기
	$("#bnt_cardInfo").click(function(){
		/*$("#layerPopupCardInfo").find("#cardinfo_content").load("https://admin8.kcp.co.kr/html/popup/thismonth/html/kcp_pop_up.html",function(response, status, xhr){
			$(this).html($(this).html().replace(/\.\./g,"https://admin8.kcp.co.kr/html/popup/thismonth"));
		});
		layerPopup.popupOpenNow("#layerPopupCardInfo");*/
		var status = "width=750 height=700 menubar=no,scrollbars=yes,resizable=no,status=no";	
		window.open("https://admin8.kcp.co.kr/html/popup/thismonth/html/kcp_pop_up.html", "kcp", status);
	});
	
	$(".info.order").eq(0).find(".size").each(function(index){ 
		var $sizeSelect = $(this).find(".btn-size.d_radio_select").not("[disabled='disabled']");
		if($sizeSelect.length === 1) {
			$sizeSelect.triggerHandler("click");
	    }
	});
});

/**
 * 숫자만 가능하게
 * @param obj
 * @returns
 */
function onlyNumber(obj){
	$(obj).val($(obj).val().replace(/[^0-9]/gi,""));
}

/**
 * 가격 포멧(000,000)
 * @param n
 * @returns
 */
function addComma(n) {
	 var reg = /(^[+-]?\d+)(\d{3})/;	 
	 n += '';
	 while (reg.test(n)) {
	  n = n.replace(reg, '$1' + ',' + '$2');
	 }
	 return n;
}

/**
 * 입력값을 콤마가 포함된 문자열로 변환하여 리턴
 * @param str   숫자
 * @return ret  콤마를 추가한 숫자
*/
function strAddComma(val) {
	var ret;

	//숫자앞에 있는 "0"을 먼저 삭제함. - 2004.9.12
	var numstr = val + "";
	var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
	var arrNumber = numstr.split('.');
	arrNumber[0] += '.';
	do {
			arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
	}
	while (rxSplit.test(arrNumber[0]));

	if (arrNumber.length > 1) {
			ret = arrNumber.join('');
	} else {
			ret = arrNumber[0].split('.')[0];
	}

	return ret;
}


/* ********************************************/
/* 2. 상품상세     		                    	  */
/* ********************************************/
/**
 * 수량 선택
 * 
 * @param flag null/add/minus
 */
   
function changeQty(flag){
	var godTpCd = $("#godTpCd").val(),
		qty = $("#qty").val();
	
	if(qty == ""){
		$("#qty").val(_ordMinQty);
		return false;
	}
		
	if(!checkSizeSelected()){
		return false;
	}	

	if(flag == "add"){
		qty = parseInt(qty)+1;
	} 
	if(flag == "minus") {
		qty = parseInt(qty)-1;
	}	
		
	if(qty < _ordMinQty){
	   //주문 가능한 최소 수량입니다.
	   alert(MESSAGES["goods.js.goods.msg.alert1"]+ " " + _ordMinQty);
	   $("#qty").val(_ordMinQty);
	   return false;
	}	
	
	if(qty > _ordMaxQty){
	   //주문 가능한 최대수량을 확인해주세요. 최대+N
	   alert(MESSAGES["goods.js.goods.msg.alert3"] + " " + _ordMaxQty);		
	   $("#qty").val(_ordMaxQty);
	   return false;
	} 
	
	$("#qty").val(qty);
	
	if(godTpCd == "SET_GOD"){
	   var totalPrice = _godPrice * qty;	   
	   //	추가 구성품 가격 계산
	   var additQty = 0;
	   if($("#additQty").val() > 0) {
		   additQty = $("#additQty").val();
	   }
	   if(parseInt(additQty) != 0){
		   totalPrice = totalPrice + parseInt($("#additCsmrPrc").val()) * additQty;
	   }	   
	   $(".total").find("#totalPrice").text(addComma(totalPrice));
	}	
	
}


//추가 구성상품 수량 변경시	
function additChangeQty(flag){
	if(!checkSizeSelected()){
		return false;
	}
	var max = $("#additCpstGodQty").val();
	var len = $("[name^=itmNo]").length;
	if($("#additItmNo").val() && $("#additItmNo").val() != ""){
		for(var i=0; i < len; i++){
			if($("#additItmNo").val() == $("#itmNo"+i).val()){
				max = getSetGodMinQty()-parseInt($("#qty").val());
				break;
			}			
		}
		var additQty = $("#additQty").val();
		var additTobeQty = 0;
		if(additQty == ""){
			$("#additQty").val(0);
			return false;
		}
		if(flag == "add"){
			additTobeQty = parseInt(additQty)+1;
		}else if(flag == "minus"){       
			additTobeQty = parseInt(additQty)-1;       
		if(additTobeQty < 0){
		   //주문 가능한 최소 수량입니다.
		   alert(MESSAGES["goods.js.goods.msg.alert1"]);
		   additTobeQty = additQty;
		   return false;
		   }       
		}else{       
		   if(additQty < 0){
			   //주문 가능한 최소 수량입니다.
			   alert(MESSAGES["goods.js.goods.msg.alert1"]);
			   additTobeQty = 0;
			   return false;
		   }else{
			   additTobeQty = additQty;
		   }		   
		}	
		if(max > 0 && additTobeQty > max){
		   //주문 가능한 최대수량을 확인해주세요. 최대+N
		   alert(MESSAGES["goods.js.goods.msg.alert3"] + " " + max);
		   additTobeQty = max;
		   return false;
		}
		$("#additQty").val(additTobeQty);
		
		var totalPrice = _godPrice * parseInt($("#qty").val()) + parseInt($("#additCsmrPrc").val()) * additTobeQty;
		$(".total").find("#totalPrice").text(addComma(totalPrice));
	}else{
		//추가구성 상품 옵션 선택해주세요.
		alert(MESSAGES["goods.js.goods.msg.alert4"]);		
		return false;
	}
}


// 세트상품 최소 구매 수량
function getSetGodMinQty(){
	var len = $("[name^=cpstGodQty]").length;
	var setGodMinQty = 1;
	
	if(len > 0){
	   setGodMinQty = parseInt($("#cpstGodQty0").val());
	}
	for(var i=1; i < len; i++){
	   var itmCpstGodQty = parseInt($("#cpstGodQty"+i).val());
	   if(setGodMinQty > itmCpstGodQty){
		   setGodMinQty = itmCpstGodQty;
	   }
	}
	return setGodMinQty;
}

/**
 * 사이즈 선택에 따른 최대구매수량 설정
 * 	index : 단품 순번
 * 	itmNo : 단품 번호
 *	invQty : 가용수량
 *	salePrearngeQty : 판매예정수량
 *	isSetGod : 세트 여부
 */
function sizeChange(index, itmNo, invQty, salePrearngeQty, isSetGod){	
	$("#itmNo"+index).val(itmNo);	
	$("#qty").val(_ordMinQty);	
	var itmQty = parseInt(invQty) - parseInt(salePrearngeQty);	
	if(isSetGod){
		$("#cpstGodQty"+index).val(itmQty);		
		var setMinQty = getSetGodMinQty();		
		if(_ordMaxQty > setMinQty){
			_ordMaxQty = _ordMaxQty;	
		}		
	}else{		
		if(_ordMaxQty > itmQty){
			_ordMaxQty = itmQty;
		}
	}
}

/**
 * 추가 상품 사이즈 선택
 */
function additSizeChange(e, additItmNo, additCpstGodQty){
	/*if(!checkSizeSelected()){
		$(e).addClass("d_no_option");
		return false;
	}*/
	$("#additItmNo").val(additItmNo);
	$("#additCpstGodQty").val(additCpstGodQty);
	$("#additQty").val("0");
}

/**
 * 상품쿠폰 다운로드
 */
function goodsCouponDownload(prmNo){
	var url = "/goods/content/addMemberCoupon.json";

	$.ajax({
		type : "POST",
		async : false,
		url : url,
		data : {prmNo : prmNo},
		success : function(data) {
			if(data.msg == 'success'){				
				//alert(data.successMsg);
				alert(MESSAGES["goods.js.goods.msg.alert11"]);
				closeLayerPopAndReset('lypopCuponDown');
			}else{
				//alert(data.errMsg);
				alert(MESSAGES["goods.js.goods.msg.alert12"]);
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}

/**
 * 입고알림 서비스 팝업
 */
function popGoodsInform(){
	// 로그인 팝업
	if($("#loginYn").val() == 'N'){			
		openLayerPopupForLogin();
		return false;
	}
	// 로그인한 회원 팝업
	else {
		layerPopup.popupOpenNow('#lypopGoodsInform');
	}
}
/**
 * 입고알림 서비스 신청
 */
function goodsInformRequest(){
	var url = "/goods/content/addGoodsInform.json";
	
	var godNo = $("#ifrGodNo").val();
	var optNm = $("#informSize").text();
	var itmNo = "";
	$('[name=informSizeLi]').each(function(index){
		var itmNm = $(this).text();
		if(optNm == itmNm) {
			itmNo = $(this).attr("value");
		}
	});
	
	var mbrNo = $("#ifrMbrNo").val();
	var ntcnSectCd = "MOBIL_NTCN";
	var mobilAreaNo = $("#ifrMb01").val();
	var mobilTlofNo = $("#ifrMb02").val();
	var mobilTlofWthnNo = $("#ifrMb03").val();
	var ntcnComptYn = "N";
	var deleteYn = "N";
	var stplatIemAgrYn = "N";
	
	var num1 = /[0-9]{2,3}/;
	var num2 = /[0-9]{3,4}/;
	var num3 = /[0-9]{4}/;
	
	// phone number check
	if(!(num1.test(mobilAreaNo) && num2.test(mobilTlofNo) && num3.test(mobilTlofWthnNo) )) {
		alert(MESSAGES['goods.js.goods.msg.alert13']);
		return;
	}
	// size null check
	if(itmNo.trim().length <= 0) {
		alert(MESSAGES['goods.js.goods.msg.alert14']);
		return;
	}
	
	$.ajax({
		type : "POST",
		async : false,
		url : url,
		data : {godNo:godNo, itmNo:itmNo, mbrNo:mbrNo, ntcnSectCd:ntcnSectCd, mobilAreaNo:mobilAreaNo, mobilTlofNo:mobilTlofNo, mobilTlofWthnNo:mobilTlofWthnNo, ntcnComptYn:ntcnComptYn, deleteYn:deleteYn, stplatIemAgrYn:stplatIemAgrYn}, 
		success : function(data) {
			if(data.msg == 'success'){				
				alert(MESSAGES["goods.js.goods.msg.alert15"]);
				resetGoodsInform();
			}else{
				alert(MESSAGES["common.system.error"]);
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}
function resetGoodsInform(){
	$("#ifrMb01").val("");
	$("#ifrMb02").val("");
	$("#ifrMb03").val("");
	
	$('[name=informSizeLi]').each(function(index){
		var itmNm = $(this).text();
		$("#informSize").text(itmNm);
		return false;
	});
	
	layerPopup.popupCloseNow('#lypopGoodsInform');
}

/* ********************************************/
/* 3. QnA                                     */
/* ********************************************/



/* ********************************************/
/* 4. 매장찾기                          	  */
/* ********************************************/
function availableStore(){
	if($("#searchStoreItmNo").val() == ""){
		//사이즈 선택해 주세요.
		alert(MESSAGES["goods.js.goods.msg.alert5"]);
		return false;
	}
	
	var p_itmNo = $("#searchStoreItmNo").val();
	var p_sidoCd = $("#searchStoreSidoCd").val();
	var p_srchKeyword = $("#searchStoreKeyword").val().trim();
	
	if(p_srchKeyword != ""){
		var regEx = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi; 
		if(p_srchKeyword.length > 10){
			alert(MESSAGES["goods.js.goods.msg.alert9"]);
			return false;
		}
		if(regEx.test(p_srchKeyword)){
			alert(MESSAGES["goods.js.goods.msg.alert10"]);
			return false;
		}
	}
	
	map = new google.maps.Map(document.getElementById("map"), {
	    zoom: 16,
	    center: {lat:37.532, lng:127.024}, //대한민국
	});	
	
	$.ajax({
		type : "POST",
		async : false,
		url : "/goods/content/getGoodsShopList.json",
		data : {itmNo:p_itmNo, sidocd:p_sidoCd, srchKeyword:p_srchKeyword}, 
		success : function(data) {
			$("#pdp-store-list-wrap").find(".no-result").hide();
			$("#pdp-store-list-wrap").find(".store-map").hide();
			$("#pdp-store-list-wrap").find(".store-list").hide();
			$("#pdp-store-list-wrap").find(".store-list ul.main-ul").empty();
			
			var elCnt = 0;
			if(data.totalCnt > 0){	
				var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd;				
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
						el+="<div class=\"store-name\"><a href=\"#\" onclick=\"javascript:setLocationStore("+lttd+", "+lgtd+")\">"+shopNm+"</a></div>";
					}else{
						el+="<div class=\"store-name\">"+shopNm+"</div>";
					}					
					
					el+="<ul class=\"store-address-info\">";
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt5']+"</span>"+baseAddr+"&nbsp;"+detailAddr+"</li>"; //주소
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt6']+"</span>"+telNo+"</li>"; //전화번호
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt7']+"</span>"+hour+"</li></ul>"; //영업시간
					el+="</li>";

					if(data.shopList[i].pkupShopYn == "Y"){
						$(".store-list-wrap").find(".store-list ul.main-ul").append(el);
						elCnt++;
					}
					
					map.setCenter(new google.maps.LatLng(data.shopList[0].lttd, data.shopList[0].lgtd));
					
					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){										
						var marker = new google.maps.Marker({
						    position: {lat: lttd, lng: lgtd},
						    map: map,
						    title:shopNm
						});				
					}				
				}				
			}			
			
			if(elCnt > 0){
				$("#pdp-store-list-wrap").find(".store-list").show();
				$("#pdp-store-list-wrap").find(".store-map").show();				
			}else{
				$("#pdp-store-list-wrap").find(".no-result").text(MESSAGES['goods.js.goods.option.popup.store.lbl.txt2']); //검색된 목록이 없습니다.
				$("#pdp-store-list-wrap").find(".no-result").show();				
			}			
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});		
}

function setItmNoSearchStore(itmNo){
	$("#searchStoreItmNo").val(itmNo);
}

function setLocationStore(lttd, lgtd){
	map.setCenter(new google.maps.LatLng(lttd, lgtd));
}

//픽업할 매장 선택
function choicePickupStore(){
	if(_pickupStoreCnt > 0){
		 var shopId = $("input[name='radioPickupStore']:checked").val();
		 $("#bskGodPkupShopSn").val(shopId);
		 $("#layerPopupPickupStore").hide();
		 var gubun = $("#type").val();
		 if(gubun == "basket"){			 
			 addBasket(); 
		 }else{
			 orderNow();
		 }
	}else{
		//alert('매장을 선택하세요.');
		alert(MESSAGES["goods.js.goods.msg.alert6"]);
	}
}

function pickupStore(){	
	var p_itmNo = $("#bskGodItmNo").val();
	var p_sidoCd = $("#pickupStoreSidoCd").val();
	var p_shopType = $("#pickupStoreShopGbn").val();
	var p_srchKeyword = $("#pickupSearchStoreKeyword").val().trim();
	var p_brndId = $("#brndId").val();
    var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd, recptPdrgSect, googleUrl;
	$.ajax({
		type : "POST",
		async : false,
		url : "/goods/content/getGoodsShopList.json",
		data : {itmNo:p_itmNo, sidocd:p_sidoCd, shopType:p_shopType, srchKeyword:p_srchKeyword, brndId:p_brndId}, 
		success : function(data) {
			_pickupStoreCnt = data.totalCnt;			
			if(data.totalCnt > 0){
				$("#tablePickupStore tbody").empty();							
				for(var i=0; i<data.shopList.length; i++){
					el="<tr><td><span class=\"rdo-skin\">";
					if(i == 0){
						el+="<input type=\"radio\" name=\"radioPickupStore\" checked=\"checked\" value="+data.shopList[i].shopId+"><span></span></span>";
					}else{
						el+="<input type=\"radio\" name=\"radioPickupStore\" value="+data.shopList[i].shopId+"><span></span></span>";
					}
					//goods.js.common.btn.select - 선택
					// el+="<span>"+MESSAGES['goods.js.common.btn.select']+"</span></span></td><td class=\"tleft\"><div class=\"store-list\"><ul><li>"
					el+="<label for=\"storeSel01\" class=\"orderPayOptSel txtNone\">"+MESSAGES['goods.js.common.btn.select']+"</label></td>"
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
					if(data.shopList[i].recptPdrgSect == 1){
						recptPdrgSect = MESSAGES['goods.js.goods.option.popup.store.lbl.txt3']; //당일~1일 내 수령 가능
					}else{
						recptPdrgSect = MESSAGES['goods.js.goods.option.popup.store.lbl.txt4']; //1일~2일 내 수령 가능
					}
                    googleUrl = "#";
                    if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){                                        
                        googleUrl = "https://www.google.com/maps/search/?api=1&query="+lttd+","+lgtd;
                    }
					if(typeof(shopNm) === "undefined"){shopNm = "";}
					if(typeof(baseAddr) === "undefined"){baseAddr = "";}
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					if(typeof(detailAddr) === "undefined"){detailAddr = "";}
					/* [DEXC3-120]주소 2 노출 요청 2018-09-27 Brandon */
					if(typeof(telNo1) === "undefined" || typeof(telNo2) === "undefined" || typeof(telNo3) === "undefined"){telNo = "";}else{telNo=telNo1+"-"+telNo2+"-"+telNo3;}					
					if(typeof(shour) === "undefined" || typeof(ehour) === "undefined"){hour = "";}else{hour=shour+"~"+ehour;}
					/*
					el+="<div class=\"store-name\">"+shopNm+"</div>";
					el+="<ul class=\"store-address-info\">";
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt5']+"</span>"+baseAddr+"&nbsp;"+detailAddr+"</li>"; 	//주소
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt6']+"</span>"+telNo+"</li>"; 		//전화번호
					el+="<li><span>"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt7']+"</span>"+hour+"</li></ul>"; 	//영업시간
					el+="<div class=\"justify-wrap\"><a href=\""+googleUrl+"\" target=\"_blank\">"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt8']+"</a></div></li></ul></div></td>"; //매장위치 확인
					el+="<td><span class=\"text-color02\">"+recptPdrgSect+"</span></td></tr>";	
					*/
					el+="<td>"+shopNm+"</td>";
					el+="<td class=\"txtLeft\"><a href='javascript:;' onclick='javascript:showPopupShop(this);' data-shopnm='"+shopNm+"' data-baseaddr='"+baseAddr+"' data-telno='"+telNo+"' data-hour='"+hour+"' data-lttd='"+lttd+"' data-lgtd='"+lgtd+"'  data-shopnm='"+shopNm+"' >"+baseAddr+"&nbsp;"+detailAddr+"</a></td>"; 	//주소
					el+="<td>"+telNo+"</td>"; 		//전화번호
					el+="<td>"+hour+"</td>"; 		//영업시간
					// el+="<td><div class=\"justify-wrap\"><a href=\""+googleUrl+"\" target=\"_blank\">"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt8']+"</a></div></td>"; //매장위치 확인
					el+="</tr>";	
					
					if(data.shopList[i].pkupShopYn == "Y"){						
						$("#tablePickupStore tbody").append(el);				
					}
				}
				$("#layerPopupPickupStore").find(".num").text($("#tablePickupStore tbody").find("tr").length);
			} else {
				$("#tablePickupStore tbody").empty();
				el="<tr><td colspan=\"5\"><div class=\"selNoResult\">"+MESSAGES['goods.js.goods.option.popup.store.lbl.txt2']+"</div></td></tr>"; //검색된 목록이 없습니다.
				$("#tablePickupStore tbody").append(el);
				$("#layerPopupPickupStore").find(".num").text("0");
			}		
		},
		error: function(pa_data, status, err) {
			_pickupStoreCnt = 0;
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}

function showPopupShop(mapObj) {

	var commonMap = new google.maps.Map(document.getElementById("common_shopMap"), {
		    zoom: 16,
		    center: {lat:37.532, lng:127.024}, //대한민국
			});
		
	var lttd = parseFloat($(mapObj).data("lttd"));
	var lgtd = parseFloat($(mapObj).data("lgtd"));
	  
	$("#common_shopNm").text( $(mapObj).data("shopnm") );
	$("#map-view-address").html( $(mapObj).data("baseaddr") );
	$("#map-view-tel").html( $(mapObj).data("telno") );
	$("#map-view-time").html( $(mapObj).data("hour") );
	
	if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){	
		var marker = new google.maps.Marker({
		    position: {lat: lttd, lng: lgtd},
		    map: commonMap,
		    title:$(mapObj).data("shopnm")
		});
		commonMap.setCenter(new google.maps.LatLng(lttd, lgtd));
	}
	layerPopup.popupOpenNow("#layerPopupSelectShopMap");
}


/* ********************************************/
/* 5. 상품리뷰    	                              */
/* ********************************************/

/**
 * 상품 리뷰 목록 조회
 */
function getReviewList(pageNo, sortFlag, photoReviewYn){		
	var _sortFlag = "";
	var _photoReviewYn = "";
	if(sortFlag == 'F' && photoReviewYn == 'F'){
		_sortFlag = "";
		_photoReviewYn = "";
	}else{
		if(sortFlag == '' && photoReviewYn == ''){
			$("#reviewFormPhotoYn").val('');
			$("#reviewFormSort").val('');		
		}
		if(sortFlag != '' && photoReviewYn == ''){		
			$("#reviewFormSort").val(sortFlag);		
		}
		if(sortFlag == '' && photoReviewYn != ''){		
			$("#reviewFormPhotoYn").val(photoReviewYn);
		}
		_sortFlag = $("#reviewFormSort").val();
		_photoReviewYn = $("#reviewFormPhotoYn").val();		
	}
	
	$("#ul_all_photo_text li").removeClass("on");		
	if(_photoReviewYn == 'A'){				
		$("#ul_all_photo_text li:eq(0)").addClass("on");			
	}
	if(_photoReviewYn == 'N'){
		$("#ul_all_photo_text li:eq(1)").addClass("on");			
	}
	if(_photoReviewYn == 'Y'){
		$("#ul_all_photo_text li:eq(2)").addClass("on");			
	}	
	
	$.ajax({
		type : "GET",
		async : true,
		url : "/goods/content/listReview.ajax",
		data : {'pageNo':pageNo, 'sortFlag':_sortFlag, 'photoReviewYn':_photoReviewYn,'godNo':_godNo},
		success : function(data) {
			if(_sortFlag == '' && _photoReviewYn == ''){
				$('#goodsReviewList').html(data);
			}else{
				$('#searchGoodsReviewList').html(data);
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}


/* ********************************************/
/* 5. 주문, 장바구니 담기      	                      */
/* ********************************************/

function addOrder(naverPayYn){		
	if(checkSizeSelected()){
		$("#naverPayYn").val(naverPayYn);
		var uri = document.location.pathname;
		if($("#loginYn").val() == 'N'){		
			loginCallback = "addOrderStep2()";		
			openLayerPopupForLogin('guestOrder', uri, loginCallback);
			return false;
		}else{
			addOrderStep2();
		}
	}	
}

function addOrderStep2(){
	var dlvSect = $("[name=dlvSect]:checked").val();
	var giftPromoAplYn = $("#giftPromoAplYn").val();	
	if(!dlvSect || dlvSect == null || dlvSect == ""){
		dlvSect = "GNRL_DLV";
	}
    $("#type").val("add");
	$("#bskGodDlvSectCd").val(dlvSect);
	fomDataSet();
	if(dlvSect == "PKUP_DLV"){
		if(giftPromoAplYn == 'Y'){				
			layerPopup.popupOpenNow("#layerPopupGiftInfo");
		}else{
			pickupStorePopupOpen();
		}
	}else{
		orderNow();
	}
}

//주문
function orderNow(){
	goodsPixclTrack("AddToCart");
	$.ajax({
		type : "POST",
		async : false,
		url : "/cart/goods/add/orderNow.json",
		data : $('#goodsForm').serialize(), 
		beforeSend: function (request)
        {
          var csrfToken = $("meta[name='_csrf']").attr("content");
          var csrfName = $("meta[name='_csrf_header']").attr("content");
          request.setRequestHeader(csrfName, csrfToken);
        },
		success : function(data) {			
			if(data.msg == 'success'){
				location.href="/order/orderform/new";				
				return;
			}else{
				alert(data.errMsg);				
				if(data.rtncode < 0){
					location.reload();
				}
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});
}

function pickupStorePopupOpen(){
	layerPopup.popupOpenNow("#layerPopupPickupStore");
	pickupStore();
}

/**
 * 옵션 선택 여부 체크
 * @returns {Boolean}
 */
function checkSizeSelected(){
	var noselect = true;
	var godTpCd = $("#godTpCd").val();
	var cpstGodNm;
	
	$("[id^=itmNo]").each(function(index){	
		if ($(this).val() == ""){
		    $("#qty").val(_ordMinQty);
			if(godTpCd == "SET_GOD"){
				cpstGodNm = $("#cpstGodNm"+index).val();
				noselect = false;
				return false;
			}else{
				noselect = false;
			}
		}
	});
	
	if(!noselect){
		if(godTpCd == 'SET_GOD'){
			//alert("["+cpstGodNm+"] 옵션을 선택해주세요");
			alert(messageFormat(MESSAGES["goods.js.goods.msg.alert7"], cpstGodNm));			
		}else{
			//옵션을 선택해주세요
			alert(MESSAGES['goods.js.goods.msg.alert8']);						
		}
		return false;
	}
	return true;
}

//장바구니 담기
function goBasket(){	
	var dlvSect = $("[name=dlvSect]:checked").val();
	if(!dlvSect || dlvSect == null || dlvSect == ""){
		dlvSect = "GNRL_DLV";
	}
    $("#type").val("basket");
	$("#bskGodDlvSectCd").val(dlvSect);
	if(checkSizeSelected()){
		//장바구니 데이터 설정
		fomDataSet();
		if(dlvSect == "PKUP_DLV"){
			layerPopup.popupOpenNow("#layerPopupPickupStore");
			pickupStore();			
		}else{			
			addBasket();
		}		
	}
}

function goBasket2(){
	var bool = confirm(MESSAGES['footer.js.locale.msg']);
	
	if(bool){
		var exdays = 360;
		var d = new Date();
	    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
	    var expires = "expires="+d.toUTCString();
	    
		document.cookie = "__NCP_LOCALE__"+"="+"ko"+";"+expires + ";path=/";
		location.reload();
	}
}

//장바구니 등록
function addBasket(){
	goodsPixclTrack("AddToCart");
	recoPick('sendLog','basket', {id:$("#erpGodNo").val(), count:$("#qty").val()});
	
	$.ajax({
		type : "POST",
		async : false,
		url : "/cart/goods/add.json",
		data : $("#goodsForm").serialize(),
		success : function(data) {
			if(data.msg == 'success'){				
				layerPopup.popupOpenNow('#layerPopupAddBasketComplete');	
				gnbMiniCart.load();
			}else{
				alert(data.errMsg);
				if(data.rtncode < 0){
					location.reload();
				}
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}

//장바구니, 주문시 form 데이타 셋팅
function fomDataSet(){
	var godTpCd = $("#godTpCd").val();
	$("#basketDiv").html("");
	if(godTpCd == "SET_GOD"){
		var obj;
		var len = $("[name=itmNo]").length;
		var masterItmNo = $("#masterItemNo").val();
		var qty = $('#qty').val();
		$("#bskGodItmQty").val(qty);
		$('[name=itmNo]').each(function(index){
			var itmNo = $(this).val();
			if(itmNo.length > 0){
				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].godNo").val($("#cpstGodNo"+index).val());
				$("#basketDiv").append(obj);
				
				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].itmNo").val(itmNo);
				$("#basketDiv").append(obj);
				
				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].pckageGodTpCd").val(godTpCd);
				$("#basketDiv").append(obj);

				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].sortSeq").val($("#sortSeq"+index).val());
				$("#basketDiv").append(obj);				
				
				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].cpstGodQty").val(1);
				$('#basketDiv').append(obj);		
				
				obj = document.createElement("input"); 
				$(obj).prop("type","hidden").prop("name","cpstGodList["+index+"].itmQty").val(qty);
				$('#basketDiv').append(obj);		
			}
		});
		if($("#additItmNo").val() && $("#additItmNo").val() != "" && $('#additQty').val() > 0){
			obj = document.createElement("input");
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].godNo").val($("#additCpstGodNo").val());
			$("#basketDiv").append(obj);
			
			obj = document.createElement("input"); 
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].itmNo").val($("#additItmNo").val());
			$("#basketDiv").append(obj);
			
			obj = document.createElement("input"); 
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].pckageGodTpCd").val("ADIT_CPST_GOD");
			$("#basketDiv").append(obj);
			
			obj = document.createElement("input"); 
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].sortSeq").val(len+1);
			$("#basketDiv").append(obj);				
			
			obj = document.createElement("input"); 
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].cpstGodQty").val(1);
			$('#basketDiv').append(obj);

			obj = document.createElement("input"); 
			$(obj).prop("type","hidden").prop("name","cpstGodList["+len+"].itmQty").val($('#additQty').val());
			$('#basketDiv').append(obj);
			
		}		
		$('#bskGodItmNo').val(masterItmNo);
		$('#bskGodPckageGodYn').val('Y');
	}else{
		$("#bskGodItmNo").val($("#itmNo0").val());
		$("#bskGodItmQty").val($("#qty").val());
	}
}
//로그인
function memberLogin(){
	openLayerPopupForLogin('guestCoupon');	  
}
//위시리스트 등록
function addWishList(obj){
	goodsPixclTrack("AddToWishlist");
	//btn-like d_icon_toggle on
	var url="";
	var wishListAdd ="Y";
	
	if($('#addWishList').attr('class').indexOf(' active') != -1 ){

		url = "/mypage/wishlist/deleteGodWishList.json";
		wishListAdd ="N";
	} else {
		var godTpCd = $("#godTpCd").val();
		if(godTpCd == "SET_GOD"){
			$('#wishlistPckageGodYn').val('Y');
			$("#wishlistItmNo").val($("#masterItemNo").val());	
		}else{
			$("#wishlistItmNo").val($("#itmNo0").val());
		}
		$("#wishlistItmQty").val($("#qty").val());
		url = "/mypage/wishlist/insert.json";
	}
 
	$.ajax({
		type : "POST",
		async : false,
		url : url,
		data : $("#goodsWishlistForm").serialize(),
		success : function(data) {
			var wishListCount = parseInt($('#wishListCount').text());
			if(!wishListCount) wishListCount = 0; 
			if(wishListAdd =='Y'){	
				if(data.msg == 'success'){				
				   $('#wishlstSn').val(data.wishlstSn);
				   $('#godTurn').val(data.godTurn);
				   $('#addWishList').addClass(' active');
			 
				   wishListCount = wishListCount+1;
				   alertLayer(MESSAGES['goods.js.goods.msg.alert16']);	
				}else{
					alertLayer(MESSAGES['goods.js.goods.msg.alert17']);
				}
			}else{
			 
				  wishListCount =   wishListCount-1;
				  $('#addWishList').removeClass('active');
				
			}
			$('#wishListCount').text(wishListCount);
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}

function goGodQna(loginYn, godNo, erpGodNo){
	if(loginYn == 'Y'){
		window.location.href = "/helpdesk/csInquiry/new?godNo="+godNo+"&erpGodNo="+erpGodNo;
	}else{
		window.location.href = "/member/login/view?loginTarget=/helpdesk/csInquiry/new"+encodeURIComponent("?godNo="+godNo+"&erpGodNo="+erpGodNo);
	}
}

function closeLayerPopAndReset(layerId){
	layerPopup.popupCloseNow('#'+layerId);
}