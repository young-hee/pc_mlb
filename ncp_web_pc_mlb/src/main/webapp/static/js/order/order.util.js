var otool = {
	
	commaInAmount : function(val){
		if(val===undefined|val===''){
			return '';
		}else{
			return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
			//return $.number(val, 2, ",", ".");
		}
	},
	parseAmount : function(val){
		return parseInt(val);
	},
	nullToString : function(val){
		if(val === null || val === undefined || val === 'undefined') {
			return '';
		}
		else {
			return val;
		}
	},
	retain : function(a, b){
	    return $.grep(a, function(i){
	        return $.inArray(i, b) > -1;
	    });
	},
	dateConvert : function(val){
		return val.substr(6,2)+"/"+val.substr(4,2)+"/"+val.substr(0,4);
	},
	timeConvert : function(val){
		return val.substr(0,2)+":"+val.substr(2,2);
	},
	block : function(){
		
		if($('.ld-bar-wrap').html()){
			$('.ld-bar-wrap').show();
		}else{
			$("#wrap").append('<div class="ld-bar-wrap"><div class="ld-bar"></div></div>');
		}
	},
	unblock : function(){
		$('.ld-bar-wrap').hide();
	},
	openMap : function(shopNm){
	
		//$("#searchStoreKeyword").val(shopNm.substring(0,10));
		$("#btn_findStoreFooter").click();
		
	},
	showPopupShop : function(shopId){

 		var commonMap = new google.maps.Map(document.getElementById("common_shopMap"), {
 		    zoom: 16,
 		    center: {lat:37.532, lng:127.024}, //대한민국
 		});
 		
 		$.ajax({
 			type : "POST",
 			async : false,
 			url : "/common/shop/view.json",
 			data : {'${_csrf.parameterName}' : '${_csrf.token}', shopId:shopId}, 
 			dataType : "JSON",
 			success : function(data) {
 				if(data.sysShopExtends != null){
 					var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd, holdyYn, shopBegHhmm, shopEndHhmm;
					shopNm = data.sysShopExtends.shopNm;
					baseAddr = data.sysShopExtends.baseAddr;
					telNo1 = data.sysShopExtends.shopTelAreaNo;
					telNo2 = data.sysShopExtends.shopTelTlofNo;
					telNo3 = data.sysShopExtends.shopTelTlofWthnNo;
					shour = data.sysShopExtends.bsnBegHhmm;
					ehour = data.sysShopExtends.bsnEndHhmm;
					lttd = data.sysShopExtends.lttd;
					lgtd = data.sysShopExtends.lgtd;
					holdyYn = data.sysShopExtends.holdyYn;
					shopBegHhmm = data.sysShopExtends.shopBegHhmm;
					shopEndHhmm = data.sysShopExtends.shopEndHhmm;
					
					if(typeof(shopNm) === "undefined"){shopNm = "";}
					if(typeof(baseAddr) === "undefined"){baseAddr = "";}
					if(typeof(telNo1) === "undefined" || typeof(telNo2) === "undefined" || typeof(telNo3) === "undefined"){telNo = "";}else{telNo=telNo1+"-"+telNo2+"-"+telNo3;}					
					//if(typeof(shour) === "undefined" || typeof(ehour) === "undefined"){hour = "";}else{hour=shour+"~"+ehour;}		
					if(typeof(holdyYn) === "undefined"){holdyYn = "N";}
					if(typeof(shopBegHhmm) === "undefined"){shopBegHhmm = "1000";}
					if(typeof(shopEndHhmm) === "undefined"){shopBegHhmm = "2000";}
					
					if(holdyYn == "N") {
						hour = "(" + MESSAGES['common.js.week.day'] + ")";
					} else {
						hour = "(" + MESSAGES['common.js.holi.day'] + ")";
					}
					
					hour += otool.timeConvert(data.sysShopExtends.shopBegHhmm) + " ~ " + otool.timeConvert(data.sysShopExtends.shopEndHhmm);
					
					$("#common_shopNm").text(shopNm);
					$("#map-view-address").html(baseAddr);
					$("#map-view-tel").html(telNo);
					$("#map-view-time").html(hour);
					//console.log(lttd + " : " + lgtd);
					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){	
						var marker = new google.maps.Marker({
						    position: {lat: lttd, lng: lgtd},
						    map: commonMap,
						    title:shopNm
						});
						commonMap.setCenter(new google.maps.LatLng(lttd, lgtd));
					}
					layerPopup.popupOpenNow("#layerPopupSelectShopMap");
 				}else{
 					alert("<spring:message code='order.popup.store.msg.alert1'/>");
 					//매장 조회 시 에러 발생하였습니다.
 				}
 			},
 			error: function( pa_data, status, err ) {
 	            alert("error forward : "+err+" ,status="+status);
 	        }
 		});		
 	}
	,confirmLayerCallback : function (msg, callback, jsonObject, cancelCallBack, cancelJsonObject) {
		$("#orderConfirmCallback").find(".layer-txt").html(msg);
		
		$('#orderConfirmCallback .confirmBtn').click(function(e){
			e.preventDefault();
			
			if (callback != undefined && typeof callback == 'function') {
				callback(jsonObject);
			} else {
				alertLayer(MESSAGES['common.js.confirm']);
			}
			
			$('#orderConfirmCallback .commonConfirmBtn').unbind("click");
		});
		
		$('#orderConfirmCallback .cancelBtn').click(function(e){
			e.preventDefault();
			
			if (cancelCallBack != undefined && typeof cancelCallBack == 'function') {
				cancelCallBack(cancelJsonObject);
			}
			
			$('#orderConfirmCallback .commonConfirmBtn').unbind("click");
		});
		
		

		layerPopup.popupOpenNow("#orderConfirmCallback");
		
	}
	,alert : function (msg) {
		$("#orderAlertLayer").find(".layer-txt").html(msg);

		layerPopup.popupOpenNow("#orderAlertLayer");
		
	}

	
};
