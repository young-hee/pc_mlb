var mypagepay = {
	
	payCount : 0
	
	,load : function() {
		
	}

	,setMypageParameter : function(frmObj, data, ordTpCd) {
		if (!$("[name='pay.payNo']", frmObj).length) {
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf.parameter']").attr("content");
			$(frmObj).append('<input type="hidden" name="' + csrfName + '" value="' + csrfToken + '" />');
	
			$(frmObj).append('<input type="hidden" name="pay.payNo" />');
			$(frmObj).append('<input type="hidden" name="ord.ordTpCd" />');
			$(frmObj).append('<input type="hidden" name="ord.payExchgRtCrncySumAmt" />');
			$(frmObj).append('<input type="hidden" name="ord.stdrCrncySumAmt" />');
	
			if (ordTpCd == 'RESVE_ORD') {
				$(frmObj).append('<input type="hidden" name="ord.resveOrdPaySectCd" />');
				$(frmObj).append('<input type="hidden" name="ord.resveOrdPartPayAmt" />');
				$(frmObj).append('<input type="hidden" name="ord.resveOrdDlivyPrearngeDate"/>');
				$(frmObj).append('<input type="hidden" name="ord.resveOrdDlivyAprvCd" />');
			}
		}
	
		$('input[name="pay.payNo"]').val(data.payNo);
		$('input[name="ord.ordTpCd"]').val(ordTpCd);
		$('input[name="ord.payExchgRtCrncySumAmt"]').val(data.totAmt);
		$('input[name="ord.stdrCrncySumAmt"]').val(data.totAmt);
		
	}
	
	,payment : function(frmObj, data) {
		// 로딩바와 더블클릭 방지 추가합시다.
		if (data.payNo.indexOf('ST') < 0) {
			alert('결제번호에 오류가 있습니다.');
			return false;
		}
		if (data.totAmt > 0) {
			var payMnCd = $('input:radio[name="pay.payMnCd"]:checked').val();
			if (payMnCd == 'NAVER_PAY') {
				mypagepay.naverPayCall(frmObj, data);
			} else {
				mypagepay.kcpPayCall(frmObj, data);
			}
		} else {
			frmObj.submit();
		}
	}
	
	,naverPayCall : function(frmObj, obj){
		var npayInfo = obj.npayInfo;
		
		
		var oPay = Naver.Pay.create({
			"mode" : npayInfo.mode // development or production
			,"clientId" : npayInfo.clientId // clientId
			,"openType" : "layer"
			,"onAuthorize" : function(oData) {
				/*
				layer 타입을 사용했을 때 페이지 전환 없이 구현이 가능하도록 지원되며, 그 외의 경우는 returnUrl 로 참조 정보와 함께 redirect 됩니다.
				oData 객체에는 결제 인증 결과와 전달한 returnUrl 정보가 함께 전달되며,
				이 정보는 이후 승인 요청 처리를 위한 정보 (resultCode, resultMessage, returnUrl, paymentId, reserveId 등) 입니다.
				전달되는 값은 https://developer.pay.naver.com/docs/v2/api#payments-payments_window 의 성공 & 실패 응답 값을 참조해주세요.
				 */
				if (oData.resultCode === "Success") {
					
					$('input[name="pay.pgSectCd"]').val("NAVER_PAY");
					$('input[name="naverPayApprove.paymentId"]').val(oData.paymentId);

					mypagepay.paySubmit(obj.ordNo, function(){
					});
					
				} else {
					if(oData.resultMessage=='userCancel'){
						alertLayer('결제를 취소하셨습니다. 주문 내용 확인 후 다시 결제해주세요.');
					}else if(oData.resultMessage=='webhookFail'){
						alertLayer('호출 응답 실패');
					}else if(oData.resultMessage=='paymentTimeExpire'){
						alertLayer('결제 가능한 시간이 지났습니다. 주문 내용 확인 후 다시 결제해주세요.');
					}else if(oData.resultMessage=='OwnerAuthFail'){
						alertLayer('타인 명의 카드는 결제가 불가능합니다. 회원 본인 명의의 카드로 결제해주세요.');
					}else{
						alertLayer(oData.resultMessage);
					}
				}
				
			}
		});
		//alert($("#orderRegion").data("productName"));
		oPay.open({
			"merchantUserKey" : npayInfo.merchantUserKey
			,"merchantPayKey" : npayInfo.merchantPayKey
			,"productName" : obj.itemName
			,"totalPayAmount" : obj.totAmt
			,"taxScopeAmount" : obj.totAmt
			,"taxExScopeAmount" : "0"
			,"returnUrl" : npayInfo.returnUrl
		});
	}
	,kcpPayCall : function(frmObj, obj){
		
		var kcpInfo = obj.kcpInfo;

		// 에스크로 사용여부
		kcpInfo.pay_mod = obj.escrYn;
		
		// kcpInfo.pay_method = 선택된 결제수단으로 
		kcpInfo.good_name = obj.itemName; 
		kcpInfo.good_mny = obj.totAmt;
		kcpInfo.buyr_name = obj.buyerNm;
		kcpInfo.buyr_mail = obj.mbrEmail;
		kcpInfo.buyr_tel1 = obj.buyrTel;
		kcpInfo.buyr_tel2 = obj.buyrTel;
		
		//escrow yn 에스크로는 일단 주문자 정보로  
		kcpInfo.rcvr_name = obj.recpNm;
		kcpInfo.rcvr_mail = obj.mbrEmail; 
		kcpInfo.rcvr_tel1 = obj.recpTel;
		kcpInfo.rcvr_tel2 = obj.recpTel;
		kcpInfo.rcvr_zipx = obj.postNo;
		kcpInfo.rcvr_add1 = obj.addr1;
		kcpInfo.rcvr_add2 =	obj.addr2;
		
		kcpInfo.bask_cntx = obj.itemCnt;
		
		var chr30 = String.fromCharCode(30);	// ASCII 코드값 30
        var chr31 = String.fromCharCode(31);	// ASCII 코드값 31
        var good_info = "결제수단변경"; // 일단 넣자.. 상품정보 가져와서 처리해야할듯..
//        $.each(orderform.getOrderInfo().cartResultList, function(index, item) {
//        	good_info += "seq="+(index+1) + chr31 + "ordr_numb="+ escape(item.bskGod.godNo) + chr31 + "good_name="+escape(item.god.godNm) + chr31 + "good_cntx="+ item.bskGod.itmQty + chr31 + "good_amtx="+ item.price;
//        	if(index!==(orderform.getOrderInfo().cartResultList.length-1)){
//        		good_info += chr30;
//        	}
//        });
        
        kcpInfo.good_info = good_info;
		
        var payMnCd = $('input:radio[name="pay.payMnCd"]:checked').val();
        if (payMnCd == 'CREDT_CARD_PAY') {
        	kcpInfo.pay_method = "100000000000";
        } else if(payMnCd == 'RLTM_BNK_ACCT_PAY') {
        	kcpInfo.pay_method = "010000000000";
        }
        
        $("#kcpForm").html("");
        
		$.each(kcpInfo, function(key, value) {
			var iDiv = document.createElement("div");
			var iTag = document.createElement("input");
			iTag.type="hidden";
			iTag.name=key;
			iTag.value=value;
			$(iDiv).append(iTag);
			$("#kcpForm").append(iDiv);
			
		});
		KCP_Pay_Execute( document.getElementById("kcpForm") );
	}
	, paySubmit : function(ordNo, func) {
		if(mypagepay.payCount!==0){
			return false;
		}
		mypagepay.payCount++;
		
		var form = $("#orderDTO");
    	var strParams = form.serialize(); 
    	
    	
    	$.ajax({
    		type : "POST",
    		url     : $(form).prop("action"),
			data : $(form).serializeJSON(),
			dataType : "JSON",
			beforeSend: function (request)
            {
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(data) {
				func();
				$("#payComptOrdNo").html(ordNo);
				layerPopup.popupOpenNow("#layerPopupPayComplete");
			},
			error : function(jqXHR, textStatus, errorThrown) {
				alertLayer("결제를 실패하였습니다.");
				mypagepay.payCount = 0;
				//alertLayer(jqXHR.responseJSON.message);
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
	
    var frm = document.getElementById("kcpForm"); 
 
    /********************************************************************/
    /* FormOrJson은 가맹점 임의 활용 금지                               */
    /* frm 값에 FormOrJson 값이 설정 됨 frm 값으로 활용 하셔야 됩니다.  */
    /* FormOrJson 값을 활용 하시려면 기술지원팀으로 문의바랍니다.       */
    /********************************************************************/
    GetField( frm, FormOrJson ); 

    
    if( frm.res_cd.value == "0000" )
    {
    	$('input[name="pay.pgSectCd"]').val("KCP_PAY");
    	
    	$('input[name="kcpDTO.req_tx"]').val(frm.req_tx.value);
    	$('input[name="kcpDTO.enc_data"]').val(frm.enc_data.value);
    	$('input[name="kcpDTO.enc_info"]').val(frm.enc_info.value);
    	$('input[name="kcpDTO.tran_cd"]').val(frm.tran_cd.value);
    	$('input[name="kcpDTO.ordr_idxx"]').val(frm.ordr_idxx.value);
    	$('input[name="kcpDTO.use_pay_method"]').val(frm.use_pay_method.value);

    	mypagepay.paySubmit(document.getElementById("orderDTO").ordNo.value, closeEvent);
    	closeEvent();
    }
    else
    {
        alert( "[" + frm.res_cd.value + "] " + frm.res_msg.value );
        closeEvent();
    }
}