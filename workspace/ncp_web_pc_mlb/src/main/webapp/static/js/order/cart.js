var cart = {
	mbrYn : 'N'
	,dlvSectCd : ''
	,cartObj : {}
	,cartShopId : ''
	,cartGodTurn : ''
	,load : function(cartDlvSectCd) {
		fbq('track', 'ViewContent');
		otool.block();
		
	
		$('[name="entireCheck"]').click(function(){
			if($(this).is(":checked:enabled")){
	    		$(this).closest('table').find('[name="godCheck"]').prop("checked",true);
			}else{
				$(this).closest('table').find('[name="godCheck"]').prop("checked",false);
			}
			cart.mixupInit($(this).val());
		});
		
		JSRENDER_HELPER.otool = otool;
		JSRENDER_HELPER.pimg = function(imgUrl,size){
			if(imgUrl){
				return BASE.imageUrl+imgUrl+"/dims/resize/"+size;
			}else{
				return BASE.imageUrl+"/goods/error/no_image.jpg"+"/dims/resize/"+size;
			}
		}
		
		//cart.searchCartList('GNRL_DLV');
		if(cartDlvSectCd){
			$("#btn_"+cartDlvSectCd).click();
		}else{
			$("#btn_GNRL_DLV").click();
		}
		
		cart.advertisementStart();
		
		
	},
	searchCartList : function(dlvSectCd,e){
		
		if(dlvSectCd=='PKUP_DLV'){
			if($("#cartRegion").data("cartInfo")!=null&&$("#cartRegion").data("cartInfo").pkupCartCnt==0){
				//otool.alert(MESSAGES['cart.js.txt.cart.alert.1']);
				alert(MESSAGES['cart.js.txt.cart.alert.1']);
				return false;
			}
		}
		
		$('[name="entireCheck"]').each(function(){
			$(this).prop("checked","checked");
		});
		
		
		//otool.alert(dlvSectCd);
		$.ajax({
			type:"post"
			,url:"/cart/goods/list.json"
			,data : "dlvSectCd="+dlvSectCd
			,dataType: "json"
			,async : false
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success : function(ret) {
				if(dlvSectCd=='PKUP_DLV'&&ret.cart.pkupCartCnt==0){
					$("#btn_GNRL_DLV").click();
					return false;
				}
				cart.buildCart(ret,dlvSectCd);
			}
			,error : function(xhr) {
				otool.unblock();
				var data = $.parseJSON(xhr.responseText);
				alert(data.message);
				//otool.alert(data.message);
				
			}
			,complete : function(data) {
				
			}
		});
	}
	,buildCart : function(info,dlvSectCd){
		
		JSRENDER_HELPER.recomCd = function(cd){
			var value = "";
			$.each(info.recomCdList, function(index, item) {
				if(item.cd==cd){
					value = item.cdNm;
					return false;
				}
			});			
			return value;
		}
		
		$("#jsonTEXT").val(JSON.stringify(info));
		
		
		$("#"+dlvSectCd+"_ViewLayer").html("");
		$("#"+dlvSectCd+"_ViewLayer").html($("#"+dlvSectCd+"_View").render(info.cart,JSRENDER_HELPER));
		
		if(dlvSectCd=='PKUP_DLV'){
			if(!info.cart.cartList){
				location.reload();
			}
		}
		
		if(info.cart.cartList){
			//$("#"+dlvSectCd+"_dlvLimitCostRegion").show();
			$("#"+dlvSectCd+"_cart_foot").show();
		}
		
		cart.mbrYn = info.mbrYn;
		if(info.cartSearchDTO.mbr.mbrAtrbCd!='EMP'){
			$("#"+dlvSectCd+"_emp_order_btn").hide();
			$("#"+dlvSectCd+"_order_btn").show();
		} else {
			$("#"+dlvSectCd+"_emp_order_btn").show();
			$("#"+dlvSectCd+"_order_btn").hide();
		}
		
		$("#cartRegion").data("cartInfo",info.cart);
		
		if(dlvSectCd!='PKUP_DLV'){
			$.each(cart.getCart(), function(index, item) {
				if(index==0){
					$("#"+dlvSectCd+"_dlvLimitCost").html(otool.commaInAmount(item.dmstcDlvCstExmStdrAmt));
					return false;
				}
			});
		}else{
			
			$('[name="entireCheck_pickup"]').click(function(){

			 	if($(this).is(":checked:enabled")){
					if($(this).val()==$(this).closest('table').find('[name="godCheck"]').val()){
						$(this).closest('table').find('[name="godCheck"]').prop("checked",true);
					}
				}else{
					if($(this).val()==$(this).closest('table').find('[name="godCheck"]').val()){
						$(this).closest('table').find('[name="godCheck"]').prop("checked",false);
					}
				}
			});
		}
		
		if(info.cart.pkupCartCnt==0){
			$("#PKUP_DLV_CNT").closest('li').removeClass('d_tab02_select');
		}else{
			$("#PKUP_DLV_CNT").closest('li').removeClass('d_tab02_select');
			$("#PKUP_DLV_CNT").closest('li').addClass('d_tab02_select');
		}

		cart.displayCartCount(info.cart.bskInfo)
		cart.buildCartEvent(dlvSectCd);
		cart.mixupInit(dlvSectCd);
		
		var erpGodNo ="";
		$('.recomPdListBox').each(function(i) {
			$(this).val();
            if(i != 0){
            	erpGodNo += ','+$(this).val();
            }else{
            	erpGodNo = $(this).val();
            }
		 
		});
		
		$('.recomPdListBox').hide();
		$('#recommendProduct'+dlvSectCd).show();
 
		if(erpGodNo != ''){
			 $('#recommendProduct'+dlvSectCd).viewtogether({godNo:erpGodNo, channel : 'cart'});
		}else{
			 $('#recommendProduct'+dlvSectCd).personalized({channel : 'cart'});
		}
 
		otool.unblock();
		
			
	}
	,buildCartEvent : function(dlvSectCd){
		$('#'+dlvSectCd+'_ViewLayer [name="itmFigure"]').blur(function(){
			var godTurn = $(this).attr('id').split('_')[1];
			if(dlvSectCd=='PKUP_DLV'){
				cart.changeQtyCart(godTurn,parseInt($(this).val()),true,$(this).closest('div').find('[name="itmFigureShopId"]').val());
			}else{
				cart.changeQtyCart(godTurn,parseInt($(this).val()),true);
			}
		});
		
		$('#'+dlvSectCd+'_ViewLayer [name="godCheck"]').click(function(){
			cart.mixupInit(dlvSectCd);
		});
		
		if(dlvSectCd!='PKUP_DLV'){
			$("[href='#layerPopupOption']").click(function(){
				var godNo =  $(this).data('godno');
				var itmQty =  $(this).data('itmqty');
				cart.cartGodTurn = $(this).data('godturn');
				cart.cartShopId = '';
				getChangeOption({godNo:godNo,itmQty:itmQty});
			});
			
			cart.dlvSectCd = 'GNRL_DLV'
				
		}else{
			$("[href='#layerPopupOption']").click(function(){
				var godNo =  $(this).data('godno');
				var itmQty =  $(this).data('itmqty');
				var shopId = $(this).data('shopid');
				cart.cartGodTurn = $(this).data('godturn');
				cart.cartShopId = shopId;
				getChangeOption({godNo:godNo,itmQty:itmQty,shopId:shopId});
			});
			cart.dlvSectCd = 'PKUP_DLV'
		}
		
		
	}
	,displayCartCount : function(bskInfo){
		$(".region_cnt").html(0);
		$.each(bskInfo, function(index, item) {
			$("#"+item.dlvSectCd+"_CNT").html(item.godCnt);
		});
	}
	,mixupInit : function(dlvSectCd){
		if(dlvSectCd!='PKUP_DLV'){
			cart.mixup(dlvSectCd,'');
		}else{
			$.each($("#cartRegion").data("cartInfo").cartList, function(index, shopItem) {
				cart.mixup(dlvSectCd,shopItem[0].shopId);
			});
		}
	} 
	,mixup : function(dlvSectCd,shopId){
		var god_amt = 0;
		var dc_amt = 0;
		var dlv_amt = 0;
		var mileage = 0;
		
		var group_god_mount = 0;
		var upper_itemInfo;
		
		
		
		$.each(cart.getCart(shopId), function(index, item) {
			if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
				if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
					|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){

					if(shopId&&item.shopId!=shopId){
						return true;
					}
					
					god_amt += parseInt(item.price)*parseInt(item.bskGod.itmQty);
					item.applyPrice = parseInt(item.price);
					
					// 즉시할인
					if(item.cpnAplyDcAmt>0){
						dc_amt += parseInt(item.cpnAplyDcAmt)*parseInt(item.bskGod.itmQty);
						item.applyPrice -= parseInt(item.cpnAplyDcAmt);
						
						$("#dcLayerBtn_"+item.bskGod.godTurn).show();
						$("#dcLayerBtnOuter_"+item.bskGod.godTurn).show();
					}

					
					if(upper_itemInfo && upper_itemInfo.subGrpSeq != item.subGrpSeq && group_god_mount < parseInt(upper_itemInfo.dmstcDlvCstExmStdrAmt)){
						dlv_amt += parseInt(upper_itemInfo.dmstcDlvCst);
						upper_itemInfo = null;
					}
					
					if(!upper_itemInfo){
						group_god_mount = 0;
						upper_itemInfo = item;
					}
					
					group_god_mount += parseInt(item.applyPrice)*parseInt(item.bskGod.itmQty);
					
	
				}
			}
		});
		
		// bundle mixup
		$.each(cart.getBundleList(), function(midx, mprom) {
			var applyCnt = 0;
			var applyTotal = 0;
			var applyAmt;
			var applyRt;
			$.each(cart.getCart(shopId), function(index, item) {
				if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
					if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
						|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){
						
							if(shopId&&item.shopId!=shopId){
								return true;
							}
							
							if(mprom.prmNo==item.bskPrmNo){
								applyCnt += parseInt(item.bskGod.itmQty);
								applyTotal += parseInt(item.bskGod.itmQty*item.price);
							}
					}
				}
			});
			
			$.each(mprom.prmBundleDcCndList, function(index, item) {
				if(item.bundleDcSectCd=='FIXAMT'){
					// FIXAMT	
					if(item.bundleDcCndMinQty<=applyCnt&&item.bundleDcCndMaxQty>=applyCnt){
						applyAmt = item.bundleDcAmt;
					}
					
				}else{
					// FIXRT
					if(item.bundleDcCndMinQty<=applyCnt&&item.bundleDcCndMaxQty>=applyCnt){
						applyRt = item.bundleDcRt;
					}
				}

			});
			
			if(applyRt){
				applyAmt = 0;
				$.each(cart.getCart(shopId), function(index, item) {
					if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
						if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
							|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){
								if(shopId&&item.shopId!=shopId){
									return true;
								}
								
								if(mprom.prmNo==item.bskPrmNo){
									applyAmt += down(parseInt(item.bskGod.itmQty)*parseInt(item.price)*parseInt(applyRt)*0.01);
								}
						}
					}
				});
			}
			
			if(applyAmt){
				dc_amt += parseInt(applyAmt);
			}
			/*******************************************************************************************************************************
			 * block bundle region 
			 ******************************************************************************************************************************/
			var gradApplyAmt = applyAmt;
			var itemLast;
			//data injection start
			
			//$.each(cart.getBundleGodList(), function(gidx,god) {
			$.each(cart.getCart(shopId), function(index, item) {
				//if(god==item.bskGod.godNo){
				if(mprom.prmNo==item.bskPrmNo){
					if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
						if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
							|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){
								if(shopId&&item.shopId!=shopId){
									return true;
								}
								
								//if(mprom.prmNo==item.bskPrmNo){
									item.bskCheckAmt = down(((item.price)/applyTotal)*applyAmt)*item.bskGod.itmQty;
									gradApplyAmt = gradApplyAmt-item.bskCheckAmt;
									itemLast = item;
								//}
						}
					}
				}
				
			});
			//});
			
			//data injection end
			//display dc info
			
			$.each(cart.getCart(shopId), function(index, item) {
				if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
					
					if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
						|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){
							if(shopId&&item.shopId!=shopId){
								return true;
							}
							
							if(mprom.prmNo==item.bskPrmNo){
								if(itemLast&&itemLast.bskGod.godTurn==item.bskGod.godTurn)item.bskCheckAmt+=gradApplyAmt;
			
								if(applyAmt){
									$("#bskDcInfo_"+item.bskGod.godTurn).find('.left').html(item.bskPrmNm);
									$("#bskDcInfo_"+item.bskGod.godTurn).find('.right').html(otool.commaInAmount(item.bskCheckAmt)+" "+MESSAGES['common.js.crncy']);
									$("#totDcTitleInfo_"+item.bskGod.godTurn).html(otool.commaInAmount((item.bskCheckAmt+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
									
									$("#totDcInfo_"+item.bskGod.godTurn).find('.right').html(otool.commaInAmount((item.bskCheckAmt+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
									
									
									$("#dcLayerBtn_"+item.bskGod.godTurn).show();
									$("#dcLayerBtnOuter_"+item.bskGod.godTurn).show();
								}else{
									if(!item.cpnNo){
										$("#totDcTitleInfo_"+item.bskGod.godTurn).html('');
										$("#dcLayerBtn_"+item.bskGod.godTurn).hide();
										$("#dcLayerBtnOuter_"+item.bskGod.godTurn).hide();
									}
								}
							}
					}else{
						$("#bskDcInfo_"+item.bskGod.godTurn).find('.left').html('');
						$("#bskDcInfo_"+item.bskGod.godTurn).find('.right').html('');
						
						if(!item.cpnNo){
							$("#totDcTitleInfo_"+item.bskGod.godTurn).html('');
							$("#dcLayerBtn_"+item.bskGod.godTurn).hide();
							$("#dcLayerBtnOuter_"+item.bskGod.godTurn).hide();
						}
					}
					
					
					if(!item.bskPrmNo&&!item.cpnNo){
						$("#totDcTitleInfo_"+item.bskGod.godTurn).html('');
						$("#dcLayerBtn_"+item.bskGod.godTurn).hide();
						$("#dcLayerBtnOuter_"+item.bskGod.godTurn).hide();
					}
				}
			});
			/*******************************************************************************************************************************
			 * block bundle region 이아래 로직은 위치해선 안됨 교차만
			 ******************************************************************************************************************************/
		});
		
		//교차
		$.each(cart.getCart(shopId), function(index, item) {
			if(item.bskPrmType=='CRS_DC'){
				$("#bskDcInfo_"+item.bskGod.godTurn).find('.left').html('');
				$("#bskDcInfo_"+item.bskGod.godTurn).find('.right').html('');
				$("#totDcTitleInfo_"+item.bskGod.godTurn).html(otool.commaInAmount(((parseInt(0)*parseInt(item.bskGod.itmQty))+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
				$("#totDcInfo_"+item.bskGod.godTurn).find('.right').html(otool.commaInAmount(((parseInt(0)*parseInt(item.bskGod.itmQty))+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
				$("#dcLayerBtn_"+item.bskGod.godTurn).hide();
			}
				
			dc_amt += parseInt(0)*parseInt(item.bskGod.itmQty);
			item.crsDcAmt = parseInt(0)*parseInt(item.bskGod.itmQty);

			if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")){	
				if(parseInt(item.bskPrmAplyDcAmt)>0&&item.bskPrmType=='CRS_DC'){
					
					if(cart.checkCrossCrsGroup(item.bskPrmNo,item.bskGod.godTurn,item.crsGodGrpCd,shopId)){
						
						$("#bskDcInfo_"+item.bskGod.godTurn).find('.left').html(item.bskPrmNm);
						$("#bskDcInfo_"+item.bskGod.godTurn).find('.right').html(otool.commaInAmount((parseInt(item.bskPrmAplyDcAmt)*parseInt(item.bskGod.itmQty))+(parseInt(item.bskPrmAplyBalancdDcAmt)-parseInt(item.bskPrmAplyDcAmt)))+" "+MESSAGES['common.js.crncy']);
						$("#totDcTitleInfo_"+item.bskGod.godTurn).html(otool.commaInAmount(((parseInt(item.bskPrmAplyDcAmt)*parseInt(item.bskGod.itmQty)+(parseInt(item.bskPrmAplyBalancdDcAmt)-parseInt(item.bskPrmAplyDcAmt)))+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
						
						$("#totDcInfo_"+item.bskGod.godTurn).find('.right').html(otool.commaInAmount(((parseInt(item.bskPrmAplyDcAmt)*parseInt(item.bskGod.itmQty)+(parseInt(item.bskPrmAplyBalancdDcAmt)-parseInt(item.bskPrmAplyDcAmt)))+(item.cpnAplyDcAmt*item.bskGod.itmQty)))+" "+MESSAGES['common.js.crncy']);
						
						
						$("#dcLayerBtn_"+item.bskGod.godTurn).show();
						$("#dcLayerBtnOuter_"+item.bskGod.godTurn).show();
						
						dc_amt += parseInt(item.bskPrmAplyDcAmt)*parseInt(item.bskGod.itmQty)+(parseInt(item.bskPrmAplyBalancdDcAmt)-parseInt(item.bskPrmAplyDcAmt));
						item.crsDcAmt = parseInt(item.bskPrmAplyDcAmt)*parseInt(item.bskGod.itmQty)+(parseInt(item.bskPrmAplyBalancdDcAmt)-parseInt(item.bskPrmAplyDcAmt));
					}
				}
			}
			
			//할인혜택 정보 노출
			/*if(dc_amt>0) {
				$("#dcLayerBtn_"+item.bskGod.godTurn).show();
				$("#dcLayerBtnOuter_"+item.bskGod.godTurn).show();
			}*/
		});
		
		
		
		$.each(cart.getCart(shopId), function(index, item) {
			if (item.sellYn == 'Y' && item.invYn == 'Y'){
				if(!item.bskCheckAmt)item.bskCheckAmt = 0;
				if(!item.cpnAplyDcAmt)item.cpnAplyDcAmt = 0;
				if(!item.crsDcAmt)item.crsDcAmt = 0;
				if(parseInt(item.crsDcAmt)>0){//교차
					$("#item-price_"+item.bskGod.godTurn).html(otool.commaInAmount(item.price*item.bskGod.itmQty-parseInt(item.crsDcAmt)-(parseInt(item.cpnAplyDcAmt)*item.bskGod.itmQty))+MESSAGES['common.js.crncy']);
				}else{
					$("#item-price_"+item.bskGod.godTurn).html(otool.commaInAmount(item.price*item.bskGod.itmQty-parseInt(item.bskCheckAmt)-(parseInt(item.cpnAplyDcAmt)*item.bskGod.itmQty))+MESSAGES['common.js.crncy']);
				}
				
				
				if(item.cpnAplyDcAmt>0||item.bskCheckAmt>0||item.crsDcAmt>0){
					$("#totDcTitleInfo_"+item.bskGod.godTurn).show();
				}else{
					$("#totDcTitleInfo_"+item.bskGod.godTurn).hide();
				}
			}
			
			if(item.bskGod.dlvSectCd==dlvSectCd||( dlvSectCd == 'RSV' && item.bskGod.dlvSectCd== 'GNRL_DLV' )){
				if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")
					|| (item.pckageGodTpCd=='ADIT_CPST_GOD'&&$("#godCheck_"+item.parentGodTurn).is(":checked:enabled")) ){

					if(shopId&&item.shopId!=shopId){
						return true;
					}
					
					
					mileage += down( ((parseInt(item.price)-item.cpnAplyDcAmt)*parseInt(item.bskGod.itmQty)-item.bskCheckAmt-item.crsDcAmt)*item.god.pntAccmlRt*0.01 );
					
	
				}
			}
		});
		
		
		
		
		if(upper_itemInfo && group_god_mount < parseInt(upper_itemInfo.dmstcDlvCstExmStdrAmt)){
			dlv_amt += parseInt(upper_itemInfo.dmstcDlvCst);
			upper_itemInfo = null;
		}
		if(dlv_amt>0){
			$(".free_dlv_cost").hide();
			$(".dlv_cost").show();
		}else{
			$(".free_dlv_cost").show();
			$(".dlv_cost").hide();
		}
		
		var addOnId = "";
		if(shopId){
			addOnId = "_"+shopId;
		}
		
		if( dlvSectCd == 'RSV' || dlvSectCd== 'GNRL_DLV' ){
			$("#"+dlvSectCd+"_god_amt"+addOnId).html(otool.commaInAmount(god_amt)+MESSAGES['common.js.crncy']);
			$("#"+dlvSectCd+"_dc_amt"+addOnId).html("-"+otool.commaInAmount(dc_amt)+MESSAGES['common.js.crncy']);
		} else {
			$("#"+dlvSectCd+"_god_amt"+addOnId).html(otool.commaInAmount(god_amt));
			$("#"+dlvSectCd+"_dc_amt"+addOnId).html("-"+otool.commaInAmount(dc_amt));
		}		
		
		if(dlv_amt > 0) {
			$("#"+dlvSectCd+"_dlv_amt"+addOnId).html("+"+otool.commaInAmount(dlv_amt)+MESSAGES['common.js.crncy']);
			$("#total_"+dlvSectCd+"_dlv_amt"+addOnId).html(otool.commaInAmount(dlv_amt)+MESSAGES['common.js.crncy']);
		} else {
			$("#"+dlvSectCd+"_dlv_amt"+addOnId).html(MESSAGES['cart.js.txt.dlv.free']);
			$("#total_"+dlvSectCd+"_dlv_amt"+addOnId).html(MESSAGES['cart.js.txt.dlv.free']);
		}		
		
		$("#"+dlvSectCd+"_total_amt"+addOnId).html(otool.commaInAmount(god_amt-dc_amt+dlv_amt));
		
		if(cart.mbrYn=='N'){
			mileage = 0;
		}
		$("#"+dlvSectCd+"_mileage"+addOnId).html(otool.commaInAmount(mileage)+MESSAGES['common.js.crncy']);
		
		
	}
	,checkCrossCrsGroup : function(bskPrmNo,godTurn,groupCode,shopId){
		var isCrossCrs = false;

		$.each(cart.getCart(shopId), function(index, item) {
			if( $("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")){	
				if(parseInt(item.bskPrmAplyDcAmt)>0&&item.bskPrmType=='CRS_DC'){
					if(bskPrmNo==item.bskPrmNo&&godTurn!=item.bskGod.godTurn&&groupCode!=item.crsGodGrpCd){
						isCrossCrs = true;
					}
				}
			}
		});
		
		return isCrossCrs;
	}
	,getCart : function(shopId){
		if(!shopId){
			if($("#cartRegion").data("cartInfo").cartList){
				return $("#cartRegion").data("cartInfo").cartList[0];
			}
		}else{
			var shopItemObj;
			if($("#cartRegion").data("cartInfo").cartList){
				$.each($("#cartRegion").data("cartInfo").cartList, function(index, shopItem) {
					if(shopItem[0].shopId==shopId){
						shopItemObj = shopItem;
						return false;
					}
				});
				return shopItemObj;
			}
			
		}
	}
	,getCartItem : function(godTurn,shopId){
		var itmObj;
		$.each(cart.getCart(shopId), function(index, item) {
			if(item.bskGod.godTurn==godTurn){
				itmObj = item;
				return false;
			}
		});
		return itmObj;
	}
	,getBundleList : function(){
		if($("#cartRegion").data("cartInfo").bundlePrmCnd){
			return $("#cartRegion").data("cartInfo").bundlePrmCnd;
		}
	}
	,getBundleGodList : function(){

		if($("#cartRegion").data("cartInfo").bskPrmGodNoList){
			return $("#cartRegion").data("cartInfo").bskPrmGodNoList;
		}
	}
	,orderCart : function(dlvSectCd,shopId,emp){
		//alert(dlvSectCd);
		
		var obj = new Object();
		obj.dlvSectCd = dlvSectCd;
		obj.shopId = shopId;
		var bskGodList = [];
		$.each(cart.getCart(shopId), function(index, item) {
			if(shopId&&item.shopId!=shopId){
				return;
			}
			
			if($("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")){
				bskGodList.push(item.bskGod);
			}
		});
		if(bskGodList.length==0){
			//otool.alert(MESSAGES['cart.js.txt.cart.alert.2']);
			alert(MESSAGES['cart.js.txt.cart.alert.2']);
			return;
		}
		obj.bskGodList = bskGodList;
		if(emp){
			obj.empYn = 'Y';
		}
		
		
		$("#jsonTEXT").val(JSON.stringify(obj));
		
		if(cart.mbrYn=='N'){
			cart.cartObj = obj;
			openLayerPopupForLogin('guestOrder', undefined, "cart.callbackLoginPage()", "false");
			return;
		}
		
		csubmit.checkValid(obj);
		
	}
	,callbackLoginPage : function(){		
		cart.mbrYn = "Y";
		console.log("성공cart1 : " + cart.cartObj)
		console.log("성공cart1 : " + JSON.stringify(cart.cartObj))
		csubmit.checkValid(cart.cartObj);		
	}
	,deleteCart : function(dlvSectCd,godTurn,shopId){
		
		var obj = new Object();
		obj.dlvSectCd = dlvSectCd;
		var bskgodExtendList = [];
		$.each(cart.getCart(shopId), function(index, item) {
			if(godTurn){
				if(item.bskGod.godTurn==godTurn){
					item.bskGod.parentGodTurn = item.parentGodTurn;
					item.bskGod.erpGodNo = item.god.erpGodNo;
					item.bskGod.csmrPrc = item.god.csmrPrc;
					bskgodExtendList.push(item.bskGod);
				}
			}else{
				if($("#godCheck_"+item.bskGod.godTurn).is(":checked:enabled")){
					item.bskGod.parentGodTurn = item.parentGodTurn;
					item.bskGod.erpGodNo = item.god.erpGodNo;
					item.bskGod.csmrPrc = item.god.csmrPrc;
					bskgodExtendList.push(item.bskGod);
				}
			}
		});
		
		if(bskgodExtendList.length==0){
			//otool.alert(MESSAGES['cart.js.txt.cart.alert.2']);
			alert(MESSAGES['cart.js.txt.cart.alert.2']);
			return false;
		}
		obj.bskgodExtendList = bskgodExtendList;
		
//		if(!confirm(MESSAGES['cart.js.txt.cart.alert.3'])){
//			return false;
//		}
		
		//otool.confirmLayerCallback(MESSAGES['cart.js.txt.cart.alert.3'],cart.deleteCartConfirm,obj);
		if(confirm(MESSAGES['cart.js.txt.cart.alert.3'])) {
			cart.deleteCartConfirm(obj);
		}
	
	}
	,deleteCartConfirm : function(obj){
		//$("#jsonTEXT").val(JSON.stringify(obj));

		$.ajax({
			type  : 'post',
			url   : "/cart/goods/delete.json",
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
			success : function(data) {
				// hide block layer
				//otool.alert(JSON.stringify(data));
				cart.searchCartList(obj.dlvSectCd);
				gnbMiniCart.load();
				
				// edn
		   		var adbay = new adbayCartClass('mlb');
		   		$.each(obj.bskgodExtendList, function(index, item) {
	   			    adbay.setGoods(item.godNo,item.itmQty,item.csmrPrc); // 추적, 삭제할 상품번호, 수량, 금액
		   		});
	   			adbay.setMethod('remove'); //장바구니에 상품이 제거 될 경우
	   		    adbay.send();
				
			},
			error : function(xhr) {
				// hide block layer
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(data.message);
			}
		});
	}
	,changeQtyCart : function(godTurn,itmQty,reset,shopId){
		
		var bskGod;
		$.each(cart.getCart(shopId), function(index, item) {
			if(item.bskGod.godTurn==godTurn){
				bskGod = item;
			}
		});
		
		var changeQty;
		if(reset){
			changeQty = itmQty;
		}else{
			changeQty = parseInt($("#itmFigure_"+bskGod.bskGod.godTurn).val())+itmQty;
		}
		 
		
		if(bskGod.god.minOrdQty>changeQty){
			//otool.alert(bskGod.god.minOrdQty+MESSAGES['cart.js.txt.cart.alert.4']);
			alert(bskGod.god.minOrdQty+MESSAGES['cart.js.txt.cart.alert.4']);
			
			if(reset){
				$("#itmFigure_"+bskGod.bskGod.godTurn).val(bskGod.bskGod.itmQty);
				
			}
			return false;
		}
		if(bskGod.god.maxOrdQty<changeQty){
			//otool.alert(bskGod.god.maxOrdQty+MESSAGES['cart.js.txt.cart.alert.5']);
			alert(bskGod.god.maxOrdQty+MESSAGES['cart.js.txt.cart.alert.5']);
			
			if(reset){
				$("#itmFigure_"+bskGod.bskGod.godTurn).val(bskGod.bskGod.itmQty);
			}
			return false;
		}
		
		if(changeQty<1){
			//otool.alert(1+MESSAGES['cart.js.txt.cart.alert.4']);
			alert(1+MESSAGES['cart.js.txt.cart.alert.4']);
			
			if(reset){
				$("#itmFigure_"+bskGod.bskGod.godTurn).val(bskGod.bskGod.itmQty);
			}
			return false;
		}
		
		
		$("#itmFigure_"+bskGod.bskGod.godTurn).val(changeQty);
		
		
	}
	,applyQtyCart : function(dlvSectCd,godTurn,shopId){
		
		var parseStr;
		var baseObject;
		$.each(cart.getCart(shopId), function(index, item) {
			if(item.bskGod.godTurn==godTurn){
				parseStr = JSON.stringify(item);
				
			}
		});
		baseObject = JSON.parse(parseStr);
		if(parseInt($("#itmFigure_"+baseObject.bskGod.godTurn).val())==parseInt(baseObject.bskGod.itmQty)){
			return false;
		}
		
		baseObject.bskGod.itmQty = $("#itmFigure_"+baseObject.bskGod.godTurn).val();
		if(baseObject.bskGod.godTurn!=baseObject.parentGodTurn){
			baseObject.bskGod.pckageGodYn = 'N';
		}
		
				
		
		$.ajax({
			type  : 'post',
			url   : "/cart/goods/changeQuantity.json",
			dataType : 'json',
			async : false,
			data  : JSON.stringify(baseObject.bskGod),
			contentType: "application/json",
			beforeSend: function (request)
            {
			  // block layer
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(data) {
				// hide block layer
				if(data.rtncode==1){
					cart.searchCartList(dlvSectCd);
					$('#wrap').scrollTop(200);
				}else{
					//otool.alert(data.errMsg);
					alert(data.errMsg);
					cart.searchCartList(dlvSectCd);
				}
			},
			error : function(xhr) {
				// hide block layer
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(data.message);
				cart.searchCartList(dlvSectCd);
			}
		});
		
	}
	,checkFilterOrder : function(){
		//otool.confirmLayerCallback(MESSAGES['cart.js.txt.cart.alert.6'],cart.checkFilterOrderCallback);
		if(confirm(MESSAGES['cart.js.txt.cart.alert.6'])) {
			cart.checkFilterOrderCallback();
		}
	}
	,checkFilterOrderCallback : function(){
		var validCartInfo = $("#cartRegion").data("validCartItem");
		var orderCartInfo = $("#cartRegion").data("orderCartItem");
		
		var obj = new Object();
		var bskgodExtendList = [];
		
		var newJson= new Object();
		newJson.dlvSectCd = orderCartInfo.dlvSectCd;
		newJson.bskGodList = [];
		
		$.each(validCartInfo.cartList, function(index, item) {
			if(item.sellYn == 'N' || item.invYn == 'N' || item.availMinOrdCnt == 'N' || item.availMaxOrdCnt == 'N'){
				item.bskGod.parentGodTurn = item.parentGodTurn;
				bskgodExtendList.push(item.bskGod);
			}else{
				$.each(orderCartInfo.bskGodList, function(oindex, oitem) {
					newJson.bskGodList.push(oitem);
				});
			}
		});
		obj.bskgodExtendList = bskgodExtendList;
		$("#jsonTEXT").val(JSON.stringify(obj));

		$.ajax({
			type  : 'post',
			url   : "/cart/goods/delete.json",
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
			success : function(data) {
				csubmit.orderForm(newJson);
				
			},
			error : function(xhr) {
				// hide block layer
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(data.message);
			}
		});
	}
	,changeCart : function(obj) {
	
		var itmObj = cart.getCartItem(obj.sourceGodTurn,obj.god.pkupShopSn);
		
		if(obj.god.pckageGodYn=='Y'){
			obj.god.itmNo = itmObj.bskGod.itmNo;
		}

		
		
		$.ajax({
			type:"post"
			,url:'/cart/goods/addJSON.json'
			,dataType: "json"
			,contentType : 'application/json'
			,async : true
			,data : JSON.stringify(obj)
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
				
			}
			,success : function(data) {
				if(data.rtncode==1){
					if(itmObj.god.resveSaleGodYn=='Y'){
						cart.searchCartList('RSV');
					}else{
						cart.searchCartList(itmObj.bskGod.dlvSectCd);
					}
					$('#wrap').scrollTop(200);
				}else{
					//otool.alert(data.errMsg);
					alert(data.errMsg);
					if(itmObj.god.resveSaleGodYn=='Y'){
						cart.searchCartList('RSV');
					}else{
						cart.searchCartList(itmObj.bskGod.dlvSectCd);
					}
				}
				
			}
			,error : function(xhr) {
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(data.message);
				
				issueFlag = true;
			}
			,complete : function(data) {

			}
		});

	}
	,notEmpOrderCallback : function(){
		cart.orderCart(cart.dlvSectCd,cart.cartShopId);
	}
	,advertisementStart : function(){
		$.ajax({
			 type:"post"
			,url:"/cart/goods/gnblist.json"
			,data : {}
			,dataType: "json"
			,async : true
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
	       ,success: function(data){
	    	   
	    	   	if(data.cart==undefined){
	    	    	return;
	    	    }
	    	   
	    	   	// naver
	   			fnf_naverKeywordAdvertisement("3","5");
	   			
	   			var tgItems = [];
	   		
		   		$.each(data.cart, function(index, item) {
		   			var obj = new Object();
		   			obj.i = item.bskGod.erpGodNo;
		   			obj.t = item.bskGod.godNm;
		   			obj.p = item.bskGod.csmrPrc;
		   			obj.q = item.bskGod.itmQty;
		   			tgItems.push(obj);
		   		});
		   		
		   		
		   		wptg_tagscript_vars.push(
		   			(function() {
		   				return {
		   					wp_hcuid:USER.mbrNo,  	/*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.*주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
		   					ti:"39428",
		   					ty:"Cart",
		   					device:"web"
		   					,items:tgItems
		   				};
		   		}));
		   		
		   		fnf_appendTargetGateScript();
		   		
		   		var criItems = [];
		   		
		   		$.each(data.cart, function(index, item) {
		   			var obj = new Object();
		   			obj.id = item.bskGod.erpGodNo;
		   			obj.price = item.bskGod.csmrPrc;
		   			obj.quantity = item.bskGod.itmQty;
		   			criItems.push(obj);
		   		});
		   		
		   		if(criItems.length>0){
			   		window.criteo_q.push( 
			   		        { event: "setAccount", account: 61384 },
			   		        { event: "setSiteType", type: "d" },
			   		        { event: "viewBasket", item: criItems }
			   		);
			   		
			   		fnf_appendCriteoScript();
		   		}
			   		
		   		// edn
		   		var adbay = new adbayCartClass('mlb');
		   		$.each(data.cart, function(index, item) {
	   			    adbay.setGoods(item.bskGod.godNo,item.bskGod.itmQty,item.bskGod.csmrPrc); // 추적, 삭제할 상품번호, 수량, 금액
		   		});
	   		    adbay.setMethod('add'); //장바구니에 상품이 추가 될 경우
	   		    //adbay.setMethod('remove'); //장바구니에 상품이 제거 될 경우
	   		    adbay.send();
	   		
	   			

	   		
	       },
	       error: function() {
	
	       }
	   });	
		
	}
	
	
};

var csubmit = {
	checkValid : function(jsonData){
		$.ajax({
			type  : 'post',
			url   : "/cart/goods/orderCheck.json",
			dataType : 'json',
			async : false,
			data  : JSON.stringify(jsonData),
			contentType: "application/json",
			beforeSend: function (request)
            {
			  // block layer
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(data) {
				// hide block layer
				
				if(jsonData.empYn=='Y'&&data.cpnCnt==0){
					cart.dlvSectCd = jsonData.dlvSectCd;
					cart.cartShopId = jsonData.shopId;
					//otool.confirmLayerCallback(MESSAGES['cart.js.emp.alert4'],cart.notEmpOrderCallback);
					if(confirm(MESSAGES['cart.js.emp.alert4'])) {
						cart.notEmpOrderCallback();
					}
					
					return;
				}
				
				var isValid = data.isValidOrdGod;
			
				if(isValid){
					csubmit.orderForm(jsonData);
				}else{
					if(data.type == 'PKUP_SHOP'){
						//otool.alert(MESSAGE['cart.js.txt.cart.alert.7']);
						alert(MESSAGE['cart.js.txt.cart.alert.7']);
						return;
					}else{
						$("#soldoutViewLayer").html("");
						$("#soldoutViewLayer").html($("#soldoutView").render(data,JSRENDER_HELPER));
						$("#popSoldOut").click();
						$("#cartRegion").data("validCartItem",data);
						$("#cartRegion").data("orderCartItem",jsonData);
					}
				}
			},
			error : function(xhr) {
				// hide block layer
				var data = $.parseJSON(xhr.responseText);
				//otool.alert(data.message);
				if(data.message=='vip'){
					if(confirm('매장배송 상품은 주문이 불가합니다. 장바구니의 매장배송 상품을 삭제하시겠습니까?')){
						
						var obj = new Object();
						var bskgodExtendList = [];
						
						var newJson= new Object();
						newJson.dlvSectCd = jsonData.dlvSectCd;
						newJson.bskGodList = [];
						
						$.each(cart.getCart(), function(index, item) {
							if(item.shopDlvItm == 'Y'){
								item.bskGod.parentGodTurn = item.parentGodTurn;
								bskgodExtendList.push(item.bskGod);
							}
						});
						obj.bskgodExtendList = bskgodExtendList;
						$("#jsonTEXT").val(JSON.stringify(obj));

						$.ajax({
							type  : 'post',
							url   : "/cart/goods/delete.json",
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
							success : function() {
								$("#btn_"+jsonData.dlvSectCd).click();
								
							},
							error : function(xhr) {
								// hide block layer
								var data = $.parseJSON(xhr.responseText)
								alert(data.message);
							}
						});
					}
				}else{
					alert(data.message);
				}
			}
		});

	}
	,orderForm : function(jsonData){
		$.ajax({
			type:"post"
			,url:"/cart/goods/order.page.json"
			,data : JSON.stringify(jsonData)
			,dataType: "json"
			,async : false
			,contentType: "application/json"
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success : function(ret) {
				//otool.alert(JSON.stringify(ret));
				location.href = ret.redirectUrl;
			}
			,error : function(xhr) {
				var data = $.parseJSON(xhr.responseText)
				alert(data.message);
				//otool.alert(data.message);
				
			}
			,complete : function(data) {
				
			}
		});
	}
	
		
}



function roundDown(nCalcVal) {
	return Math.round(Math.floor(nCalcVal)/10) * 10;
}
function down(nCalcVal) {
	return Math.floor(Math.floor(nCalcVal)/10) * 10;
}
function roundUp(nCalcVal) {
	return Math.ceil(Math.floor(nCalcVal)/10) * 10;
}
function setChangeOption() {
	 
	if(!checkSizeSelected()){
		return false;
	}else{
		$('.d_layer_close').click();
	}
	
   var godTpCd = $('#changeOptionForm').find('#optionGodTpCd').val();
   
   var obj = new Object();
   var god = new Object();
   
   obj.sourceGodTurn = cart.cartGodTurn;
   obj.godTpCd = 'GNRL_DLV';
   
   if($("#qty").val() < 1){
	   $("#qty").val(1);
   }
   
   if('SET_GOD' == godTpCd){
	   var cpstGodList = [];
	   god.godNo = $('#changeOptionForm').find('#optionGodNo').val();	
	   god.itmQty = $('#qty').val();
	   god.pckageGodYn = 'Y';
	   
	   obj.god = god;
	   
	   $('.cpstGodQty').each(function(index){
		   var cpstGod = new Object();
		   cpstGod.godNo = $('#changeOptionForm').find('#cpstGodNo'+index).val();	
		   cpstGod.itmNo = $('#changeOptionForm').find('#itmNo'+index).val();	
		   cpstGod.pckageGodTpCd = 'SET_GOD';
		   cpstGod.sortSeq = $('#changeOptionForm').find('#sortSeq'+index).val();
		   cpstGod.cpstGodQty = 1;
		   cpstGod.itmQty = $('#qty').val();
		   
		   cpstGodList.push(cpstGod);
	   });
	   obj.cpstGodList = cpstGodList;
	   
   }else{
	   god.pckageGodYn = 'N';
	   god.godNo = 	$('#changeOptionForm').find('#optionGodNo').val();		
	   god.itmNo = 	$('#changeOptionForm').find('#itmNo0').val();		
	   god.itmQty =	$('#qty').val();
	   obj.god = god;
   }
   
   if(cart.cartShopId){
	   obj.god.pkupShopSn = cart.cartShopId;
   }
   
   obj.god.dlvSectCd = cart.dlvSectCd;
   
   $("#jsonTEXT").val(JSON.stringify(obj));
   
   cart.changeCart(obj);
	

}