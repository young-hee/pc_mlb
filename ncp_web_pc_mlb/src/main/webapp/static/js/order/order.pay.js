var orderpay = {
	payCount : 0
	,load : function() {
		
	}
	,naverPayCall : function(obj){
		var npayInfo = orderform.getOrderInfo().npayInfo;
		
		var oPay = Naver.Pay.create({
			"mode" : npayInfo.mode // development or production
			,"clientId" : npayInfo.clientId // clientId
			,"openType" : "popup"
			,"onAuthorize" : function(oData) {
				/*
				layer 타입을 사용했을 때 페이지 전환 없이 구현이 가능하도록 지원되며, 그 외의 경우는 returnUrl 로 참조 정보와 함께 redirect 됩니다.
				oData 객체에는 결제 인증 결과와 전달한 returnUrl 정보가 함께 전달되며,
				이 정보는 이후 승인 요청 처리를 위한 정보 (resultCode, resultMessage, returnUrl, paymentId, reserveId 등) 입니다.
				전달되는 값은 https://developer.pay.naver.com/docs/v2/api#payments-payments_window 의 성공 & 실패 응답 값을 참조해주세요.
				 */
				if (oData.resultCode === "Success") {
					
					$("#npayText").val(JSON.stringify(oData));
					obj.pay.pgSectCd = "NAVER_PAY";
					obj.naverPayApprove = oData;
					orderpay.addNew(obj);
				} else {
	
					$("#npayText").val(JSON.stringify(oData));
					//"resultMessage":"userCancel" 고객이 닫은거 임시처리함
				
					if(oData.resultMessage=='userCancel'){
						//otool.alert('결제를 취소하셨습니다. 주문 내용 확인 후 다시 결제해주세요.');
						alert('결제를 취소하셨습니다. 주문 내용 확인 후 다시 결제해주세요.');
					}else if(oData.resultMessage=='webhookFail'){
						//otool.alert('호출 응답 실패');
						alert('호출 응답 실패');
					}else if(oData.resultMessage=='paymentTimeExpire'){
						//otool.alert('결제 가능한 시간이 지났습니다. 주문 내용 확인 후 다시 결제해주세요.');
						alert('결제 가능한 시간이 지났습니다. 주문 내용 확인 후 다시 결제해주세요.');
					}else if(oData.resultMessage=='OwnerAuthFail'){
						//otool.alert('타인 명의 카드는 결제가 불가능합니다. 회원 본인 명의의 카드로 결제해주세요.');
						alert('타인 명의 카드는 결제가 불가능합니다. 회원 본인 명의의 카드로 결제해주세요.');
					}else{
						//otool.alert(oData.resultMessage);
						alert(oData.resultMessage);
					}
				}
				
			}
		});
		//otool.alert($("#orderRegion").data("productName"));
		oPay.open({
			"merchantUserKey" : npayInfo.merchantUserKey
			,"merchantPayKey" : npayInfo.merchantPayKey
			,"productName" : $("#orderRegion").data("productName")
			,"totalPayAmount" : obj.ord.payExchgRtCrncySumAmt
			,"taxScopeAmount" : obj.ord.payExchgRtCrncySumAmt
			,"taxExScopeAmount" : "0"
			,"productCount" : $("#orderRegion").data("totalGodCnt")
			,"returnUrl" : npayInfo.returnUrl
			,"productItems" : orderform.naverProductList
		});
	}
	,kcpPayCall : function(obj){
		var kcpInfo = orderform.getOrderInfo().kcpInfo;
		
		
		
		// 에스크로 사용여부
		var isEscrYn = false;
		$('[name="escrPayYn"]').each(function(){
			if($(this).is(":checked:enabled")&&$(this).val()=='Y'){
				isEscrYn = true;
			}
		});
		
		if(isEscrYn){
			kcpInfo.pay_mod = 'Y';
		}else{
			kcpInfo.pay_mod = 'N';
		}
			
		
		// kcpInfo.pay_method = 선택된 결제수단으로 
		kcpInfo.good_name = $("#orderRegion").data("productName"); 
		kcpInfo.good_mny = obj.ord.payExchgRtCrncySumAmt;
		kcpInfo.buyr_name = obj.ord.cstmrNm;
		kcpInfo.buyr_mail = obj.ord.cstmrEmail;
		kcpInfo.buyr_tel1 = obj.ord.cstmrMobilAreaNo+obj.ord.cstmrMobilTlofNo+obj.ord.cstmrMobilTlofWthnNo;
		kcpInfo.buyr_tel2 = obj.ord.cstmrMobilAreaNo+obj.ord.cstmrMobilTlofNo+obj.ord.cstmrMobilTlofWthnNo;
		
		//escrow yn 에스크로는 일단 주문자 정보로  
		kcpInfo.rcvr_name = obj.ord.cstmrNm; //수신자로 수정할것
		kcpInfo.rcvr_mail = obj.ord.cstmrEmail; 
		kcpInfo.rcvr_tel1 = obj.ord.cstmrMobilAreaNo+obj.ord.cstmrMobilTlofNo+obj.ord.cstmrMobilTlofWthnNo;
		kcpInfo.rcvr_tel2 = obj.ord.cstmrMobilAreaNo+obj.ord.cstmrMobilTlofNo+obj.ord.cstmrMobilTlofWthnNo;
		kcpInfo.rcvr_zipx = obj.lgsDlvspDTOList[0].lgsDlvsp.addrsePostNo;
		kcpInfo.rcvr_add1 = obj.lgsDlvspDTOList[0].lgsDlvsp.addrseBaseAddr;
		kcpInfo.rcvr_add2 =	obj.lgsDlvspDTOList[0].lgsDlvsp.addrseDetailAddr;
		
		kcpInfo.bask_cntx = orderform.getOrderInfo().cartResultList.length;
		
		var chr30 = String.fromCharCode(30);	// ASCII 코드값 30
        var chr31 = String.fromCharCode(31);	// ASCII 코드값 31
        var good_info = "";
        $.each(orderform.getOrderInfo().cartResultList, function(index, item) {
        	good_info += "seq="+(index+1) + chr31 + "ordr_numb="+ escape(item.bskGod.godNo) + chr31 + "good_name="+escape(item.god.godNm) + chr31 + "good_cntx="+ item.bskGod.itmQty + chr31 + "good_amtx="+ item.price;
        	if(index!==(orderform.getOrderInfo().cartResultList.length-1)){
        		good_info += chr30;
        	}
        });
        
        kcpInfo.good_info = good_info;
		
        
        //test
        
		
        kcpInfo.pay_method = $('[name="paymentBtn"]:checked').val();
        //otool.alert(kcpInfo.pay_method);
        $("#kcpForm").html("");
        
		$.each(kcpInfo, function(key, value) {
			
			var iDiv = document.createElement("div");
			$(iDiv).html("");
			//$(iDiv).html(key+"=>");
			var iTag = document.createElement("input");
			iTag.type="hidden";
			iTag.name=key;
			iTag.value=value;
			$(iDiv).append(iTag);
			$("#kcpForm").append(iDiv);
			
		});
		
		$("#orderRegion").data("kcpOrder",obj);
		
		KCP_Pay_Execute( document.kcpForm );
//		var obj = document.createElement("input");
//		obj.type="hidden";
//		obj.name="resveSaleGodYn";
//		obj.value=resveSaleGodYn;
//		$target.append(obj);
		
	}
	,addNew : function(obj){
		fbq('track', 'AddPaymentInfo');		
		otool.block();
		if(orderpay.payCount!==0){
			return false;
		}
		orderpay.payCount++;
		$("#jsonTEXT").val(JSON.stringify(obj));
		
		$.ajax({
			type  : 'post',
			url   : "/order/addnew.json",
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
				
				
				//otool.alert('주문 결제 성공');
				if(obj.pay.pgSectCd == "KCP_PAY"){
					closeEvent();
				}
				window.location.replace('/order/'+data.orderNumber+'/view');
				
			},
			error : function(xhr) {
				// hide block layer
				if(xhr&&xhr.responseText){
					var data = $.parseJSON(xhr.responseText)
					alert(data.message);
				}else{
					alert(MESSAGES['common.js.session.over']);
				}
				// 오류시 메시지 출력후 어디로든 보내야함
				orderpay.payCount = 0;
				otool.unblock();
				if(obj.pay.pgSectCd == "KCP_PAY"){
					closeEvent();
				}
				
				layerPopup.popupOpenNow("#lyPopOrderFail");
			}
		});

	}
	
	
	
	
		
}
// kcp 용 js 관리하지 않음
/* Payplus Plug-in 실행 */
/****************************************************************/
/* m_Completepayment  설명                                      */
/****************************************************************/
/* 인증완료시 재귀 함수                                         */
/* 해당 함수명은 절대 변경하면 안됩니다.                        */
/* 해당 함수의 위치는 payplus.js 보다먼저 선언되어여 합니다.    */
/* Web 방식의 경우 리턴 값이 form 으로 넘어옴                   */
/* EXE 방식의 경우 리턴 값이 json 으로 넘어옴                   */
/****************************************************************/
function m_Completepayment( FormOrJson, closeEvent ) 
{
    var frm = document.kcpForm; 
 
    /********************************************************************/
    /* FormOrJson은 가맹점 임의 활용 금지                               */
    /* frm 값에 FormOrJson 값이 설정 됨 frm 값으로 활용 하셔야 됩니다.  */
    /* FormOrJson 값을 활용 하시려면 기술지원팀으로 문의바랍니다.       */
    /********************************************************************/
    GetField( frm, FormOrJson ); 

    
    if( frm.res_cd.value == "0000" )
    {
	    //otool.alert("결제 승인 요청 전,\n\n반드시 결제창에서 고객님이 결제 인증 완료 후\n\n리턴 받은 ordr_chk 와 업체 측 주문정보를\n\n다시 한번 검증 후 결제 승인 요청하시기 바랍니다."); //업체 연동 시 필수 확인 사항.
    	//otool.alert('kcp 결제 인증');
    	var obj = $("#orderRegion").data("kcpOrder");
    	obj.pay.pgSectCd = "KCP_PAY";
    	obj.kcpDTO = $("#kcpForm").serializeJSON()
    	$("#jsonTEXT").val(JSON.stringify(obj));
    	
    	orderpay.addNew(obj);
    }
    else
    {
        //otool.alert( "[" + frm.res_cd.value + "] " + frm.res_msg.value );
    	alert( "[" + frm.res_cd.value + "] " + frm.res_msg.value );
        $("#orderRegion").data("kcpOrder",null);
        
        closeEvent();
    }
}