var orderform = {
	naverProductList : []
	,load : function() {
		
		// 픽셀 페이스북
		fbq('track', 'InitiateCheckout');
		
		otool.block();
		
		$(".info_no_mem").hide();
		
		//otool.alert('ee');
		JSRENDER_HELPER.otool = otool;
		JSRENDER_HELPER.pimg = function(imgUrl,size){
			if(imgUrl){
				if(size){
					return BASE.imageUrl+imgUrl+"/dims/resize/"+size;
				}else{
					BASE.imageUrl+imgUrl;
				}
			}else{
				if(size){
					return BASE.imageUrl+"/goods/error/no_image.jpg"+"/dims/resize/"+size;
				}else{
					return "";
				}
			}
		}
		
		$(".orderPaytCont").hide();
		
		$('[name="paymentBtn"]').click(function(){
			$(".orderPaytCont").hide();
			$(".orderPaytCont").find('input').prop('disabled','disabled');
			$(".orderPaytCont").find('select').prop('disabled','disabled');
			$("#order-payment-content_"+$(this).val()).show();
			$("#order-payment-content_"+$(this).val()).find('input').prop('disabled','');
			$("#order-payment-content_"+$(this).val()).find('select').prop('disabled','');
			
			$('[name="escrPayYn"]').each(function(){
				if($(this).is(":enabled")&&$(this).val()=='N'){
					$(this).prop("checked","checked");
				}
				if(orderform.getOrderInfo().cartResultList[0].bskGod.dlvSectCd==='PKUP_DLV'){
					$(".info_not_pickup").hide();
					$(".info_not_pickup").find('input').prop('disabled','disabled');
				}
			});
			
		});
		
		
		$('.btnCard').click(function(){
			$('.card-info-box').hide();
			$('#'+$(this).prop('id')+"_layer").show();
			$('.btnCard').removeClass('on');
			$(this).addClass('on');
		});
		
		$("#checkOrderToMemberInfo").click(function(){
			if($(this).prop("checked")){
				if(orderform.getOrderInfo().mbr.mbrNo){
					$("#noMemName").val(orderform.getOrderInfo().mbr.mbrNm);
					
					$("#noMemTel1").val(orderform.getOrderInfo().mbr.telAreaNo);
					$("#noMemTel2").val(orderform.getOrderInfo().mbr.telTlofNo);
					$("#noMemTel3").val(orderform.getOrderInfo().mbr.telTlofWthnNo);
					
					$("#noMemMobile1").val(orderform.getOrderInfo().mbr.mobilAreaNo);
					$("#noMemMobile2").val(orderform.getOrderInfo().mbr.mobilTlofNo);
					$("#noMemMobile3").val(orderform.getOrderInfo().mbr.mobilTlofWthnNo);
					
					if(orderform.getOrderInfo().mbr.mbrEmail){
						$("#noMemEmail1").val(orderform.getOrderInfo().mbr.mbrEmail.split('@')[0])
						$("#noMemEmail2").val(orderform.getOrderInfo().mbr.mbrEmail.split('@')[1]);	
					}				
					
				}
			}else{
				$("#noMemName").val("");
				$("#noMemTel1").val("");
				$("#noMemTel2").val("");
				$("#noMemTel3").val("");
				$("#noMemMobile1").val("");
				$("#noMemMobile2").val("");
				$("#noMemMobile3").val("");
				$("#noMemEmail1").val("")
				$("#noMemEmail2").val("");	
			}
		});
		$("#checkOrderToNoMemberInfo").click(function(){
			if($(this).prop("checked")){
				$("#ordererName").val($("#noMemName").val());
				$("#ordTel1").val($("#noMemTel1").val());
				$("#ordTel2").val($("#noMemTel2").val());
				$("#ordTel3").val($("#noMemTel3").val());
				$("#ordMobile1").val($("#noMemMobile1").val());
				$("#ordMobile2").val($("#noMemMobile2").val());
				$("#ordMobile3").val($("#noMemMobile3").val());
				

			}else{
				$("#ordererName").val('');$("#ordTel1").val('');$("#ordTel2").val('');$("#ordTel3").val('');
				$("#ordMobile1").val('');$("#ordMobile2").val('');$("#ordMobile3").val('');
				//$("#postAddr").val('');$("#baseAddr").val('');$("#detailAddr").val('');
			}
		});
		
		
		
		orderform.initBtn();
		
		orderform.loadOrderInfo();
		
		
		
	}
	,initBtn : function(){
		$("#btnWebPointApplyEnt").click(function(){
			orderform.getOrderInfo().ord.webpntUseSumAmt = 0;
			orderform.mixupPayment(false);
			orderform.useWebPoint(parseInt($("#orderRegion").data("maxWebPointUse")));
			
			
		});
		$("#btnWebPointApply").click(function(){
			orderform.getOrderInfo().ord.webpntUseSumAmt = 0;
			orderform.mixupPayment(false);
			orderform.useWebPoint(orderform.getNumberOnly($("#webPointUse").val()));
		});

		$("#btnMileageApplyEnt").click(function(){
			orderform.getOrderInfo().ord.unityPntUseSumAmt = 0;
			orderform.mixupPayment(false);
			orderform.useMileage(parseInt($("#orderRegion").data("maxMileageUse")));
			
			
		});
		$("#btnMileageApply").click(function(){
			orderform.getOrderInfo().ord.unityPntUseSumAmt = 0;
			orderform.mixupPayment(false);
			orderform.useMileage(orderform.getNumberOnly($("#mileageUse").val()));
		});

		$("#btnLoadCoupons").click(function(){
			orderform.mixupPayment(false);
			ordercoupon.loadCoupon(true);
		});
		$("#btnLoadDelivery").click(function(){
			orderform.loadDeliveryInfo();
		});
		
		
		$(".numberOnly").keyup(function (e) {
			if (!(e.keyCode >=37 && e.keyCode<=40)) {
				var inputVal = $(this).val();
				$(this).val(inputVal.replace(/[^0-9]/gi,''));
			}
	    });
		
		$(".textOnly").keyup(function (e) {
			
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]/gi,''));
			
	    });
		
				
	}
	,getNumberOnly : function(val){
		
		return val.replace(/[^0-9]/gi,'');
		
	}
	,useWebPoint : function(val){
		if(val==''||val==undefined){
			val = 0;
		}
		val = parseInt(val);
		//otool.alert(val);
		if(val>parseInt(orderform.getOrderInfo().webPoint)){
			//otool.alert(MESSAGES['order.js.txt.alert.msg1']);
			alert(MESSAGES['order.js.txt.alert.msg1']);
			$("#webPointUse").val(0);
			orderform.mixupPayment(true);
			return false;
		}
	
		if(val<1000){
			//otool.alert(MESSAGES['order.js.txt.alert.msg2']);
			alert(MESSAGES['order.js.txt.alert.msg2']);
			$("#webPointUse").val(0);
			orderform.mixupPayment(true);
			return false;
		}
		
		if(orderform.getOrderInfo().ord.cashRemain==parseInt(orderform.getOrderInfo().ord.unityPntUseSumAmt)){
			val = 0;
		}
		
		if(orderform.getOrderInfo().ord.cashRemain-parseInt(orderform.getOrderInfo().ord.unityPntUseSumAmt)<val){
			val = orderform.getOrderInfo().ord.cashRemain-parseInt(orderform.getOrderInfo().ord.unityPntUseSumAmt);
		}
		
		if(parseInt($("#orderRegion").data("maxWebPointUse"))<=val){
			val = parseInt($("#orderRegion").data("maxWebPointUse"));
		}
		
		if(val<0){
			val = 0;
		}
		val = parseInt(val/1000)*1000;
		orderform.getOrderInfo().ord.webpntUseSumAmt = val;
		$("#webPointUse").val(otool.commaInAmount(orderform.getOrderInfo().ord.webpntUseSumAmt));
		orderform.mixupPayment(true);
		
	}
	,useMileage : function(val){
		if(val==''||val==undefined){
			val = 0;
		}
		val = parseInt(val);
		
		
		
		if(val>parseInt(orderform.getOrderInfo().memberMileage.NOW_POINT)){
			//otool.alert(MESSAGES['order.js.txt.alert.msg.m.1']);
			alert(MESSAGES['order.js.txt.alert.msg.m.1']);
			$("#mileageUse").val(0);
			orderform.mixupPayment(true);
			return false;		
		}
	
		if(orderform.getOrderInfo().memberMileage.NOW_POINT<5000||val<1000){
			//otool.alert(MESSAGES['order.js.txt.alert.msg.m.2']);
			alert(MESSAGES['order.js.txt.alert.msg.m.2']);
			$("#mileageUse").val(0);
			orderform.mixupPayment(true);
			return false;
		}
		
		if(orderform.getOrderInfo().ord.cashRemain==parseInt(orderform.getOrderInfo().ord.webpntUseSumAmt)){
			val = 0;
		}
		
		if(orderform.getOrderInfo().ord.cashRemain-parseInt(orderform.getOrderInfo().ord.webpntUseSumAmt)<val){
			val = orderform.getOrderInfo().ord.cashRemain-parseInt(orderform.getOrderInfo().ord.webpntUseSumAmt);
		}
		
		
		if(parseInt($("#orderRegion").data("maxMileageUse"))<=val){
			val = parseInt($("#orderRegion").data("maxMileageUse"));
		}

		if(val<0){
			val = 0;
		}
		val = parseInt(val/1000)*1000;
		orderform.getOrderInfo().ord.unityPntUseSumAmt = val;
		$("#mileageUse").val(otool.commaInAmount(orderform.getOrderInfo().ord.unityPntUseSumAmt));
		orderform.mixupPayment(true);
		
	}
	,loadOrderInfo : function(){
		$.ajax({
			type:"post"
			,url:"/order/orderform/load.json"
			,data : "" // cartnation?
			,dataType: "json"
			,async : false
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success : function(ret) {
				//otool.alert(JSON.stringify(ret));
				ret.ord = new Object();
				$("#jsonTEXT").val(JSON.stringify(ret));
				$("#orderRegion").data("orderInfo",ret);
				
				if(ret.vipYn=='Y'){
					$.each(ret.cartResultList, function(index, item) {
						if(item.shopDlvItm=='Y'){
							alert('매장배송 상품은 주문이 불가합니다');
							location.href='/cart/goods/list';
							return false;
						}
					});	
				}
				orderform.buildOrder();
				orderform.readyForPay();
				orderform.mixupPayment(true);
				
				//orderpay.load();
				
				ordercoupon.loadCoupon(false,true);
				
				
				
				otool.unblock();
			}
			,error : function(xhr) {
				if(xhr&&xhr.responseText){
					var data = $.parseJSON(xhr.responseText)
					alert(data.message);
					location.href = '/';
				}else{
					alert(MESSAGES['common.js.session.over']);
					location.href = '/';
				}
				
			}
			,complete : function(data) {
				
			}
		});
	}
	,getOrderInfo : function(){
		return $("#orderRegion").data("orderInfo");
	}
	,buildOrder : function(){
		
		// type layer
		$("."+orderform.getOrderInfo().dlvSectCd+"_layer").show();
		if(!orderform.getOrderInfo().mbr.mbrNo){
			$(".info_no_mem").show();
			$(".info_mem").hide();
			$(".info_mem").find('input').prop('disabled','disabled');
		}else{
			$(".info_mem").show();
			$(".info_no_mem").find('input').prop('disabled','disabled');
		}

		
		JSRENDER_HELPER.recomCd = function(cd){
			var value = "";
			$.each(orderform.getOrderInfo().recomCdList, function(index, item) {
				if(item.cd==cd){
					value = item.cdNm;
					return false;
				}
			});			
			return value;
		}

		
		if(orderform.getOrderInfo().cartResultList.length>0){
			//rsv
//			if(orderform.getOrderInfo().cartResultList[0].god.resveSaleGodYn=='Y'){
//				$("#virtual_payment").closest('li').hide();
//				$("#virtual_payment").prop('disabled','disabled');
//			}
			if(orderform.getOrderInfo().cartResultList[0].prcType=='EMP'){
				$(".not_emp").hide();
			}
			
			if(orderform.getOrderInfo().cartResultList[0].bskGod.dlvSectCd==='PKUP_DLV'){
				
				$("#virtual_payment").closest('li').hide();
				$("#virtual_payment").prop('disabled','disabled');
				
				$("#mobile_payment").closest('li').hide();
				$("#mlbile_payment").prop('disabled','disabled');
				
				$("#dlvTypeName").html(MESSAGES['order.js.txt.script.msg1']);
				$("#title_delivery").html(MESSAGES['order.js.txt.script.msg2']);
				$(".info_not_pickup").hide();
				$(".info_not_pickup").find('input').prop('disabled','disabled');
				var shopInfo = "<strong class='name'>";
				shopInfo += orderform.getOrderInfo().cartResultList[0].shopNm+"</strong> <a href='#' onClick='otool.showPopupShop(\""+orderform.getOrderInfo().cartResultList[0].shopId+"\");'  class='btnMap'><img src='" + BASE.webResourceUrl + "static/images/od/icon_location.png' alt='"+MESSAGES['order.js.txt.script.msg3']+"'></a><br>";
				shopInfo += orderform.getOrderInfo().cartResultList[0].shopAddr;
				if(orderform.getOrderInfo().cartResultList[0].shopTelAreaNo){
					shopInfo += "  <em>/</em>  "+orderform.getOrderInfo().cartResultList[0].shopTelAreaNo+"-"+orderform.getOrderInfo().cartResultList[0].shopTelTlofNo+"-"+orderform.getOrderInfo().cartResultList[0].shopTelTlofWthnNo;
				}
				shopInfo += "<br>";
				shopInfo += MESSAGES['order.js.txt.script.msg4']+"  <em> | </em>  ";
				if(orderform.getOrderInfo().cartResultList[0].holdyYn=='N'){
					shopInfo += MESSAGES['order.js.txt.week.day'];
				}else{
					shopInfo += MESSAGES['order.js.txt.holi.day'];
				}
				shopInfo += otool.timeConvert(orderform.getOrderInfo().cartResultList[0].shopBegHhmm) + " ~ "+otool.timeConvert(orderform.getOrderInfo().cartResultList[0].shopEndHhmm)
				$("#info_pickup_shop").html(shopInfo);

			}else{
				$(".info_pickup").hide();
				$(".info_pickup").find('input').prop('disabled','disabled');
			}
		}
		
		$("#goodsViewLayer").html("");
		$("#goodsViewLayer").html($("#goodsView").render(orderform.getOrderInfo(),JSRENDER_HELPER));
		
		
		$.each($('[name="shopDlvItm"]'), function(index, item) {
			if($(this).val()){
				$(".shop_dlv").show();
				return false;
			}
		});
		
		
		
		
		// mbmer info
		if(!orderform.getOrderInfo().mbr.mbrNo){
			$(".dcInfo").hide();
		}else{
			$("#checkOrderToMemberInfo").click();
			
		}
		orderform.setMemberInfo();
		
		if(orderform.getOrderInfo().dlvMemoList){
			$.each(orderform.getOrderInfo().dlvMemoList, function(index, item) {
				$("#memo_region").append("<li><a href='#' onclick='orderform.setMemo(\""+item.dlvMemo+"\")'>(최근메시지)"+item.dlvMemo+"</a></li>");
			});
		}
		
		$.each(orderform.getOrderInfo().bankCodeList, function(index, item) {
			$("#rfdBnkCdListViewLayer").append('<li><a href="#" onclick="orderform.setBnkCd(\''+item.cd+'\',\''+item.cdNm+'\')">'+item.cdNm+'</a></li>');
		});
		
		
	}
	,setBnkCd : function(val,nm){
		$("#bnk_title").html(nm);
		$("#rfdBnkCd").val(val);
	}
	,setMemo : function(val){
		
		if(val==''){
			$("#memo_title").html(MESSAGES['order.js.txt.just.input']);
			$("#dlvMemo").prop("readonly","");
		}else{
			$("#memo_title").html(val);
			$("#dlvMemo").prop("readonly","readonly");
		}
		
		$("#dlvMemo").val(val);
	}
	,readyForPay : function(){
		orderform.getOrderInfo().ord.webpntUseSumAmt = 0;
		orderform.getOrderInfo().ord.unityPntUseSumAmt = 0;
		orderform.getOrderInfo().ord.cashRemain = 0;
		

		// prefer payemnt method
		if(orderform.getOrderInfo().mbrPreferPayMn){
			$.each($('[name="paymentBtn"]'), function(index, item) {
				if($(this).val()==orderform.getOrderInfo().mbrPreferPayMn){
					$(this).click();
					return false;
				}
			});
		}
		
		
		if($('[name="paymentBtn"]:checked').val()==undefined||$('[name="paymentBtn"]:checked').val()==''){
			$("#card_payment").click();
		}
		
		
		//wep 
		if(orderform.getOrderInfo().memberMileage&&orderform.getOrderInfo().memberMileage.NOW_POINT>0){
			$("#mileageBalance").html(otool.commaInAmount(orderform.getOrderInfo().memberMileage.NOW_POINT)+MESSAGES['common.js.crncy']);
		}else{
			$("#mileageBalance").html(otool.commaInAmount(0)+MESSAGES['common.js.crncy']);
		}
		$("#webPointBalance").html(otool.commaInAmount(orderform.getOrderInfo().webPoint)+MESSAGES['common.js.crncy']);
	}
	,mixupPayment : function(check){
		var god_amt = 0;
		var dc_amt = 0;
		var pnt_remain_amt = 0;
		var dc_god_amt = 0;
		var dc_dlv_amt = 0;
		var dc_bsk_amt = 0;
		var dlv_amt = 0;
		var mileage = 0;
		
		var group_god_mount = 0;
		var upper_itemInfo;
		var local_dmstcDlvCstExmStdrAmt = 0;
		var local_dmstcDlvCst = 0;
		
		var maxWebPointUse = 0;
		var maxMileageUse = 0;
		var maxMileAccml = 0;
		
		
		// 쿠폰 적용금액 계산
		
		
		// 상품 속성
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {

			if(item.checkGod){
				god_amt += parseInt(item.price)*parseInt(item.bskGod.itmQty);
				item.applyPrice = parseInt(item.checkGod.saleAmt)-parseInt(item.checkGod.allDcAmt);
				dc_amt += parseInt(item.checkGod.allDcAmt)+parseInt(item.checkGod.webpntUseAmt)+parseInt(item.checkGod.unityPntUseAmt);

				pnt_remain_amt += parseInt(item.checkGod.allDcAmt);
				dc_god_amt += parseInt(item.checkGod.godCpnDcAmt)+parseInt(item.checkGod.imdtlDcAmt);
				
				$("#godCpnTurn_"+item.bskGod.godTurn).html(otool.commaInAmount(parseInt(item.checkGod.godCpnDcAmt)+parseInt(item.checkGod.imdtlDcAmt)));
				
				dc_bsk_amt += parseInt(item.checkGod.bskCpnDcAmt);
				mileage += parseInt(item.checkGod.unityPntAccmlAmt);
				item.bskPrice = parseInt(item.checkGod.bskCpnDcAmt);
			}else{
				god_amt += parseInt(item.price)*parseInt(item.bskGod.itmQty);
				item.applyPrice = parseInt(item.price)*parseInt(item.bskGod.itmQty);
				item.bskPrice = 0;
			}
			


			if(upper_itemInfo && upper_itemInfo.grpSeq != item.grpSeq ){
				if(group_god_mount < parseInt(upper_itemInfo.dmstcDlvCstExmStdrAmt)){
					dlv_amt += upper_itemInfo.dmstcDlvCst;
					$("#dlvGrpSeq_"+upper_itemInfo.grpSeq).html(otool.commaInAmount(upper_itemInfo.dmstcDlvCst)+MESSAGES['common.js.crncy']);
				}else{
					$("#dlvGrpSeq_"+upper_itemInfo.grpSeq).html(MESSAGES['order.js.txt.script.msg5']);
				}
				upper_itemInfo = null;
			}
			
			if(!upper_itemInfo){
				group_god_mount = 0;
				upper_itemInfo = item;
//				local_dmstcDlvCst = item.dmstcDlvCst;
//				local_dmstcDlvCstExmStdrAmt = item.dmstcDlvCstExmStdrAmt;
			}
			
			group_god_mount += parseInt(item.applyPrice);
		});
		
		
		if(upper_itemInfo){
			if(group_god_mount < parseInt(upper_itemInfo.dmstcDlvCstExmStdrAmt)){
				dlv_amt += upper_itemInfo.dmstcDlvCst;
				$("#dlvGrpSeq_"+upper_itemInfo.grpSeq).html(otool.commaInAmount(upper_itemInfo.dmstcDlvCst)+MESSAGES['common.js.crncy']);
			}else{
				$("#dlvGrpSeq_"+upper_itemInfo.grpSeq).html(MESSAGES['order.js.txt.script.msg5']);
			}
			upper_itemInfo = null;
		}

		// 무료 배송
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.applyDlvCpn){
				dc_amt += dlv_amt;
				pnt_remain_amt += dlv_amt;
				dc_dlv_amt += dlv_amt;
				return false;
			}
		});
		
		// 할인금액 total start
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.god.webpntAccmlYn==='Y'){
				maxMileAccml += parseInt(item.applyPrice);
			}
			if(item.god.webpntUseYn==='Y'){
				maxWebPointUse += parseInt(item.applyPrice);
				if(parseInt(orderform.getOrderInfo().webPoint)<maxWebPointUse){
					maxWebPointUse = parseInt(orderform.getOrderInfo().webPoint);
				}
			}
			if(item.god.pntUseYn==='Y'){
				maxMileageUse += parseInt(item.applyPrice);
				if(orderform.getOrderInfo().memberMileage&&parseInt(orderform.getOrderInfo().memberMileage.NOW_POINT)<maxMileageUse){
					maxMileageUse = parseInt(orderform.getOrderInfo().memberMileage.NOW_POINT);
				}
			}
		});
		
		
		
		$("#god_amt").html(otool.commaInAmount(god_amt)+MESSAGES['common.js.crncy']);
		
		if(dlv_amt > 0) {
			$("#dlv_amt").html("+"+otool.commaInAmount(dlv_amt)+MESSAGES['common.js.crncy']);
			$("#dlv_amt_m").html(otool.commaInAmount(dlv_amt)+MESSAGES['common.js.crncy']);
		} else {
			$("#dlv_amt").html(MESSAGES['order.js.txt.dlv.free']);
			$("#dlv_amt_m").html(MESSAGES['order.js.txt.dlv.free']);
		}		
		
		$("#dc_amt").html("-"+otool.commaInAmount(dc_amt)+MESSAGES['common.js.crncy']);
		
		
		
		$("#total_amount").html(otool.commaInAmount(god_amt-dc_amt+dlv_amt));
		$("#orderRegion").data("total_amount",parseInt(god_amt-dc_amt+dlv_amt));
		$("#orderRegion").data("dlv_amount",parseInt(dlv_amt));
		
		
		//orderform.getOrderInfo().ord.cashRemain = parseInt(god_amt-pnt_remain_amt+dlv_amt);
		orderform.getOrderInfo().ord.cashRemain = parseInt(god_amt-pnt_remain_amt);
		
		$("#mileage").html(otool.commaInAmount(mileage)+MESSAGES['common.js.crncy']);
		
		$("#orderRegion").data("mile_amount",parseInt(mileage));
		
		$("#orderRegion").data("maxWebPointUse",parseInt(maxWebPointUse));
		$("#orderRegion").data("maxMileageUse",parseInt(maxMileageUse));
		$("#orderRegion").data("maxMileAccml",parseInt(maxMileAccml));
		
		//alert("total_amount : " + $("#orderRegion").data("total_amount"));
		if($("#orderRegion").data("total_amount")===0){
			$(".orderPayMethod").hide();
			/*$("#refundVirtual_region").hide();
			$("#refundVirtual_region").find('input').prop('disabled','disabled');*/
			$("#paymentSaveRegion").hide();
			$("#paymentSaveRegion").find('input').prop('disabled','disabled');
			
		}else{
			$(".orderPayMethod").show();
			$("#paymentSaveRegion").show();
			$("#paymentSaveRegion").find('input').prop('disabled','');
			
			$.each($('[name="paymentBtn"]'), function(index, item) {
				if($(this).is(":checked")){
					$(this).click();
					return false;
				}
			});
			
			
		}
		
		$("#godCpnAmt").html(otool.commaInAmount(dc_god_amt)+MESSAGES['common.js.crncy']);
		//$(".bskCpnAmt").html(otool.commaInAmount(dc_bsk_amt)+MESSAGES['common.js.crncy']);
		$("#bskCpnAmt").html(otool.commaInAmount(dc_bsk_amt));
		$("#totalBskCpnAmt").html(otool.commaInAmount(dc_bsk_amt)+MESSAGES['common.js.crncy']);
		//$(".dlvCpnAmt").html(otool.commaInAmount(dc_dlv_amt)+MESSAGES['common.js.crncy']);
		$("#dlvCpnAmt").html(otool.commaInAmount(dc_dlv_amt));
		$("#totalDlvCpnAmt").html(otool.commaInAmount(dc_dlv_amt)+MESSAGES['common.js.crncy']);
		
		$("#orderRegion").data("godCpnAmt",parseInt(dc_god_amt));
		$("#orderRegion").data("bskCpnAmt",parseInt(dc_bsk_amt));
		$("#orderRegion").data("dlvCpnAmt",parseInt(dc_dlv_amt));
		$("#totCpnAmt").html(otool.commaInAmount(dc_god_amt+dc_bsk_amt+dc_dlv_amt));
		$("#labelCoupon").val(otool.commaInAmount(dc_god_amt+dc_bsk_amt+dc_dlv_amt));
		
		
		if(check){
			localStorage.amountStroageData = god_amt;
			orderform.checkOrder();
		}
		
		orderform.displayCouponText();
		
	}
	,addOrder : function(){
		
		$(".textOnly").each(function() {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]/gi,''));
	    });

		
		var isRequired = false;
		var message = "";
		$('.input_required:enabled').each(function(){
			if($(this).prop('type')=='checkbox' || $(this).prop('type')=='radio'){
				if(!$(this).is(":checked")){
					isRequired = true;
					message = $(this).attr('alt')+MESSAGES['order.js.txt.event.alert1'];
					this.focus();
					return false;
				}
			}else{
				if(this.value===''||this.value===undefined){
					isRequired = true;
					message = $(this).attr('alt')+MESSAGES['order.js.txt.event.alert2'];
					this.focus();
					return false;
				} else if($(this).attr('minlength')){
					if($(this).attr('minlength') > $(this).val().length) {
						isRequired = true;
						message = $(this).attr('alt')+MESSAGES['order.js.txt.event.alert3'];
						this.focus();
						return false;
					}
				}
			}
		});
		
		if(isRequired){
			//otool.alert(message);
			alert(message);
			return;
		}
		
		var obj = new Object();
		var bskGodList = [];
		obj.bskGodList = orderform.getOrderInfo().cartResultList;
		
		obj = orderform.dataInjection(obj,true);

		if(obj.ord.payExchgRtCrncySumAmt>0){
			if(obj.ord.payExchgRtCrncySumAmt<100){
				//otool.alert(MESSAGES['order.js.txt.minimum_price']);
				alert(MESSAGES['order.js.txt.minimum_price']);
				return;
			}
			// naver or kcp or nomal
			if($("#naver_payment").is(":checked")){
				orderpay.naverPayCall(obj);
			}else{
				orderpay.kcpPayCall(obj);
				//orderpay.addNew(obj);
			}
		}else{ // pg결제없을때
			orderpay.addNew(obj);
		}
		
		
	}
	,checkOrder : function(){
		
		var obj = new Object();
		var bskGodList = [];
		obj.bskGodList = orderform.getOrderInfo().cartResultList;
		
		obj = orderform.dataInjection(obj);
		
		$.ajax({
			type  : 'post',
			url   : "/order/checkOrder.json",
			dataType : 'json',
			async : false,
			data  : JSON.stringify(obj),
			contentType: "application/json",
			beforeSend: function (request)
            {
			  // block layer
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(info) {
				// hide block layer
				$("#cordText").val(JSON.stringify(info));
				orderform.orderCheckTransaction(info);
			},
			error : function(xhr) {
				// hide block layer
				if(xhr&&xhr.responseText){
					var data = $.parseJSON(xhr.responseText)
					alert(data.message);
					//location.href = '/';
					ordercoupon.postGodCoupon(true);
				}else{
					alert(MESSAGES['common.js.session.over']);
					location.href = '/';
				}
			}
		});
		
	}
	,orderCheckTransaction :  function(info){
		//같은 set 상품이 있을 경우를 위해 일부 로직 변경 
		$.each(info.orderInfo.ordGodExtendsList, function(index, item) {			
			$.each(orderform.getOrderInfo().cartResultList, function(rindex, ritem) {
				if(item.godTpCd == "SET_GOD") {
					if(ritem.bskGod.godNo==item.godNo && ritem.bskGod.itmNo==item.itmNo
							&& ritem.god.godTpCd==item.godTpCd && ritem.dlvGrpRnum==item.ordGodTurn){
						ritem.checkGod = item;
						return false;
					}	
				} else {
					if(ritem.bskGod.godNo==item.godNo && ritem.bskGod.itmNo==item.itmNo
							&& ritem.god.godTpCd==item.godTpCd ){
						ritem.checkGod = item;
						return false;
					}
				}
				
			});
		});
		$("#jsonTEXT").val(JSON.stringify(orderform.getOrderInfo()));
		$("#goodsViewLayer").html("");
		$("#goodsViewLayer").html($("#goodsView").render(orderform.getOrderInfo(),JSRENDER_HELPER));
		orderform.mixupPayment(false);
		
		
		
		
	}
	,dataInjection : function(obj,newOrder){
		
		//배송
		var lgsDlvspDTOList = [];
		var lgsDlvspDTO = new Object();
		var lgsDlvsp = new Object();
		
		if(!obj.bskGodList[0].shopId){
			lgsDlvsp.dlvSectCd = "GNRL_DLV";
			lgsDlvsp.addrsePostNo = $("#postAddr").val();
			lgsDlvsp.addrseNm = $("#ordererName").val();
			lgsDlvsp.addrseBaseAddr = $("#baseAddr").val();
			lgsDlvsp.addrseDetailAddr = $("#detailAddr").val();
			
			lgsDlvsp.addrseMobilNationNo = '082';
			lgsDlvsp.addrseMobilAreaNo = $("#ordMobile1").val();
			lgsDlvsp.addrseMobilTlofNo = $("#ordMobile2").val();
			lgsDlvsp.addrseMobilTlofWthnNo = $("#ordMobile3").val();
			lgsDlvsp.addrseTelAreaNo = $("#ordTel1").val();
			lgsDlvsp.addrseTelTlofNo = $("#ordTel2").val();
			lgsDlvsp.addrseTelTlofWthnNo = $("#ordTel3").val();
			
			if($("#dlvAddrSectCd").val()){
				lgsDlvsp.addrSectCd = $("#dlvAddrSectCd").val();
			}
			
			if($("#checkBaseAddress").is(":checked")){
				lgsDlvspDTO.defaultDelv = "Y";
			}
			
			
			if($("#checkRegistAddress").is(":checked")){
				lgsDlvspDTO.addMbrDlvspCheck = "Y";
			}
			
			lgsDlvsp.dlvMemo = $("#dlvMemo").val();
		}else{ // 
			
			lgsDlvsp.dlvSectCd = "PKUP_DLV";
			
			
			lgsDlvsp.addrseNm = $("#ordererName").val();
			
			lgsDlvsp.addrsePostNo = '';
			lgsDlvsp.addrseBaseAddr = orderform.getOrderInfo().cartResultList[0].shopAddr;
			lgsDlvsp.addrseDetailAddr = '';
			lgsDlvsp.addrSectCd = "LNM_ADDR";
			
			lgsDlvsp.addrseMobilNationNo = '082';
			lgsDlvsp.addrseMobilAreaNo = $("#ordMobile1").val();
			lgsDlvsp.addrseMobilTlofNo = $("#ordMobile2").val();
			lgsDlvsp.addrseMobilTlofWthnNo = $("#ordMobile3").val();
			lgsDlvsp.addrseTelAreaNo = $("#ordTel1").val();
			lgsDlvsp.addrseTelTlofNo = $("#ordTel2").val();
			lgsDlvsp.addrseTelTlofWthnNo = $("#ordTel3").val();
			
			
			lgsDlvsp.pkupShopId = obj.bskGodList[0].shopId;
			lgsDlvspDTO.dlvMnCd = 'SHOP_PKUP';
			
		}
		lgsDlvspDTO.lgsDlvsp = lgsDlvsp;
		
		
		
		
		var totalGodCnt = 0;
		var ordGodDTOList = [];
		orderform.naverProductList = [];
		
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(index===0){
//				if(orderform.getOrderInfo().cartResultList.length===1){
					$("#orderRegion").data("productName",item.god.godNm);
//				}else{
//					$("#orderRegion").data("productName",item.god.godNm" 외"+(orderform.getOrderInfo().cartResultList.length-1));
//				}
			}
			
			var ordGodDTO = new Object();
			ordGodDTO.bskNo = item.bskGod.bskNo;
			ordGodDTO.godTurn = item.bskGod.godTurn;
			ordGodDTO.price = item.god.csmrPrc;
			
			ordGodDTO.ordGod = item.god;
			
			ordGodDTO.ordGod.saleShopCd = '1111';
			ordGodDTO.ordGod.ordQty = item.bskGod.itmQty;
			totalGodCnt += parseInt(item.bskGod.itmQty);
			ordGodDTO.ordGod.itmNo = item.bskGod.itmNo;
			

			ordGodDTOList.push(ordGodDTO);
			
	
			var npObj = new Object();
			npObj.categoryType = 'PRODUCT';
			npObj.categoryId = 'GENERAL';
			npObj.uid = item.god.godNo;
			npObj.name = item.god.godNm;
			npObj.count = item.bskGod.itmQty;
            
			orderform.naverProductList.push(npObj);
		});

		$("#orderRegion").data("totalGodCnt",totalGodCnt);
		
		lgsDlvspDTO.ordGodDTOList = ordGodDTOList;
		
		//쿠폰
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.applyDlvCpn){
				lgsDlvspDTO.deliveryCouponNo = item.applyDlvCpn.mbrCpnNo;
				return false;
			}
		});

		var couponDTOList = [];
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			
			var couponDTO = new Object();
			
			couponDTO.bskNo = item.bskGod.bskNo;
			couponDTO.godTurn = item.bskGod.godTurn;

			couponDTO.ordGod = item.god;
			couponDTO.ordGod.itmNo = item.bskGod.itmNo;
			couponDTO.ordGod.ordQty = item.bskGod.itmQty;

			//couponDTO.price = item.price;
			couponDTO.godNo = item.bskGod.godNo;


			
			if(item.applyCpn){
				couponDTO.goodsPrmNo = item.applyCpn.prm.prmNo;
				couponDTO.goodsCouponNo = item.applyCpn.mbrCpnNo;
			}
	
			if(item.applyBskCpn){
				couponDTO.orderCouponNo = item.applyBskCpn.mbrCpnNo;
			}
			
			couponDTOList.push(couponDTO);
		});
		
		
		lgsDlvspDTO.couponDTOList = couponDTOList;
		lgsDlvspDTOList.push(lgsDlvspDTO);
		obj.lgsDlvspDTOList =  lgsDlvspDTOList;
		
		if(newOrder){
			//otool.alert($("#orderRegion").data("total_amount"));
		}
		
		var ord = new Object();
		obj.ord = ord;
		
		obj.ord.stdrCrncySumAmt = parseInt($("#orderRegion").data("total_amount"));
		obj.ord.payExchgRtCrncySumAmt = parseInt($("#orderRegion").data("total_amount"));
		
		if(orderform.getOrderInfo().mbr.mbrNo){
			obj.ord.webpntUseSumAmt = orderform.getOrderInfo().ord.webpntUseSumAmt;
			obj.ord.unityPntUseSumAmt = orderform.getOrderInfo().ord.unityPntUseSumAmt;
			obj.ord.mileAccmlSumAmt = parseInt($("#orderRegion").data("mile_amount"));
		}

		obj.ord.cstmrNm = $("#noMemName").val();
		obj.ord.cstmrEmail = $.trim($("#noMemEmail1").val())+"@"+$.trim($("#noMemEmail2").val());
		obj.ord.cstmrMobilAreaNo = $("#noMemMobile1").val();
		obj.ord.cstmrMobilTlofNo = $("#noMemMobile2").val();
		obj.ord.cstmrMobilTlofWthnNo = $("#noMemMobile3").val();
		obj.ord.cstmrTelAreaNo = $("#noMemTel1").val();
		obj.ord.cstmrTelTlofNo = $("#noMemTel2").val();
		obj.ord.cstmrTelTlofWthnNo = $("#noMemTel3").val();
		
		
		
		// 결제
		var pay = new Object();
		obj.pay = pay;
		
		if( $("#rfdBnkCd").is(":enabled") ){
			obj.pay.rfdBnkCd = $("#rfdBnkCd").val();
			obj.pay.rfdBnkAcctNo = $("#rfdBnkAcctNo").val();
			obj.pay.rfdAcnthldrNm = $("#rfdAcnthldrNm").val();
		}
		
		if($("#preferPayemntMethod").is(":checked")){
			obj.mbrPreferPayMn = $('[name="paymentBtn"]:checked').val(); 
		}

		
		return obj;
	}
	,displayCouponText : function(){
		if(!orderform.getOrderInfo().mbr){
			return false;
		}
		
		
		var couponCnt = 0;
		var applyCouponCnt = 0;
		if(ordercoupon.getCouponInfo()){
			
			var isDeliveryCpn = false;
			var isBskCpn = false;
			
			$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
				if(item.applyCpn){
					applyCouponCnt++;
				}
				if(item.applyDlvCpn){
					isDeliveryCpn = true;
				}
				if(item.applyBskCpn){
					isBskCpn = true;
				}
			});
			if(isDeliveryCpn)applyCouponCnt++;
			if(isBskCpn)applyCouponCnt++;
			
			$("#useCouponCnt").html(applyCouponCnt);
		}
		
	}
	,loadDeliveryInfo : function(){
		
		
		$.ajax({
			type  : 'post',
			url   : "/order/delivery/list",
			dataType : 'json',
			async : false,
			data  : '',
			contentType: "application/json",
			beforeSend: function (request)
            {
			  // block layer
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(info) {
				// hide block layer
				$("#orderRegion").data("dlvInfo",info);
				
				$("#deliveryInfoViewLayer").html("");
				$("#deliveryInfoViewLayer").html($("#deliveryInfoView").render(info,JSRENDER_HELPER));
				
			},
			error : function(xhr) {
				// hide block layer
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(data.message);
				// 오류시 메시지 출력후 어디로든 보내야함
			}
		});
	}
	,applyDeliveryInfo : function(type,indexParam){
		var dlvInfo = $("#orderRegion").data("dlvInfo");
		
		if(type!='mem'){
			$.each(dlvInfo.mbrDlvspList, function(index, item) {
				if(index==indexParam){
					$("#ordererName").val(item.addrseNm);
					$("#ordTel1").val(item.telAreaNo);
					$("#ordTel2").val(item.telTlofNo);
					$("#ordTel3").val(item.telTlofWthnNo);
					$("#ordMobile1").val(item.mobilAreaNo);
					$("#ordMobile2").val(item.mobilTlofNo);
					$("#ordMobile3").val(item.mobilTlofWthnNo);
					$("#postAddr").val(item.postNo);
					$("#baseAddr").val(item.baseAddr);
					$("#detailAddr").val(item.detailAddr);
					$("#dlvAddrSectCd").val(item.dlvAddrSectCd);
					$(".d_layer_close").click();
					return false;
				}
			});
		}else{
			$.each(dlvInfo.ordDlvspList, function(index, item) {
				if(index==indexParam){
					$("#ordererName").val(item.addrseNm);
					$("#ordTel1").val(item.telAreaNo);
					$("#ordTel2").val(item.telTlofNo);
					$("#ordTel3").val(item.telTlofWthnNo);
					$("#ordMobile1").val(item.mobilAreaNo);
					$("#ordMobile2").val(item.mobilTlofNo);
					$("#ordMobile3").val(item.mobilTlofWthnNo);
					$("#postAddr").val(item.postNo);
					$("#baseAddr").val(item.baseAddr);
					$("#detailAddr").val(item.detailAddr);
					$("#dlvAddrSectCd").val(item.dlvAddrSectCd);
					$(".d_layer_close").click();
					return false;
				}
			});
		}
		
	}
	,setMemberInfo : function(){
		
		if(orderform.getOrderInfo().mbr.mbrNo){
			$("#noMemName").val(orderform.getOrderInfo().mbr.mbrNm);
			if(orderform.getOrderInfo().mbr.mbrEmail){
				$("#noMemEmail1").val(orderform.getOrderInfo().mbr.mbrEmail.split('@')[0])
				$("#noMemEmail2").val(orderform.getOrderInfo().mbr.mbrEmail.split('@')[1]);	
			}
			$("#noMemMobile1").val(orderform.getOrderInfo().mbr.mobilAreaNo);
			$("#noMemMobile2").val(orderform.getOrderInfo().mbr.mobilTlofNo);
			$("#noMemMobile3").val(orderform.getOrderInfo().mbr.mobilTlofWthnNo);
			$("#noMemTel1").val(orderform.getOrderInfo().mbr.telAreaNo);
			$("#noMemTel2").val(orderform.getOrderInfo().mbr.telTlofNo);
			$("#noMemTel3").val(orderform.getOrderInfo().mbr.telTlofWthnNo);
			
			if(orderform.getOrderInfo().delv != undefined && orderform.getOrderInfo().delv.baseDlvspYn == "Y") {
				$("#ordererName").val(orderform.getOrderInfo().delv.addrseNm);
				$("#ordTel1").val(orderform.getOrderInfo().delv.telAreaNo);
				$("#ordTel2").val(orderform.getOrderInfo().delv.telTlofNo);
				$("#ordTel3").val(orderform.getOrderInfo().delv.telTlofWthnNo);
				$("#ordMobile1").val(orderform.getOrderInfo().delv.mobilAreaNo);
				$("#ordMobile2").val(orderform.getOrderInfo().delv.mobilTlofNo);
				$("#ordMobile3").val(orderform.getOrderInfo().delv.mobilTlofWthnNo);
				$("#postAddr").val(orderform.getOrderInfo().delv.postNo);
				$("#baseAddr").val(orderform.getOrderInfo().delv.baseAddr);
				$("#detailAddr").val(orderform.getOrderInfo().delv.detailAddr);
				$("#dlvAddrSectCd").val(orderform.getOrderInfo().delv.dlvAddrSectCd);
			}
			
		}
		
	}
		
};

//1단위 올림
var ceil10 = function(val) {
	return Math.ceil(nvl(val) * 0.1) * 10;
};

//10단위 올림
var ceil100 = function(val) {
	return Math.ceil(nvl(val) * 0.01) * 100;
};

//1단위 반올림
var round10 = function(val) {
	return Math.round(nvl(val) * 0.1) * 10;
};

// 10단위 반올림
var round100 = function(val) {
	return Math.round(nvl(val) * 0.01) * 100;
};

//10단위 절사
var floor100 = function(val) {
	return Math.floor(nvl(val) * 0.01) * 100;
};


function setZipcode(ordno, addr, type){
	
    $("#postAddr").val(ordno);
	$("#baseAddr").val(addr);
	$("#dlvAddrSectCd").val(type);
	$(".d_layer_close").click();
}

