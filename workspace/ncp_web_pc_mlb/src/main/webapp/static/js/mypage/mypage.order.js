var mypageorder = {

	// 부분취소
	goUnitCancel : function(ordNo, dlvPcupspTurn) {
		submitLinkByPost('/mypage/claim/cancel/part/new', {'ordNo' : ordNo, 'dlvPcupspTurn' : dlvPcupspTurn } );
	}   
	
	// 반품접수
	, goRequestReturn : function(ordNo, dlvPcupspTurn, prmTpCd ) {
		submitLinkByPost('/mypage/claim/return/new', {'ordNo' : ordNo ,'dlvPcupspTurn' : dlvPcupspTurn , 'prmTpCd' : prmTpCd});
	} 

	// 교환접수
	, goRequestExchange : function(ordNo, dlvPcupspTurn) {
		submitLinkByPost('/mypage/claim/exchange/new', {'ordNo' : ordNo, 'dlvPcupspTurn' : dlvPcupspTurn} );
	}	

	// 상품상세
	, goGoodsInfo : function(godNo) {
		location.href ='/goods/'+godNo+'/view';
	}

	// 주문상세
	, goOrderView : function(ordNo) {
		location.href ='/mypage/order/'+ordNo+'/view';
	}
	
	// 클레임상세
	, goClaimView : function(ordNo) {
		submitLinkByPost('/mypage/order/'+ordNo+'/view', {'clmYn' : 'Y'} );
	}
	
	, goClaimView : function(ordNo, clmTpCd, clmNo, clmStat) {
		if(clmStat == 'WTHDRAW') {
			alert("철회된 클레임 입니다.");
		} else {
			submitLinkByPost('/mypage/order/'+ordNo+'/view', {'clmYn' : 'Y', 'clmTpCdSearch' : clmTpCd, 'clmNo' : clmNo} );
		}		
	}
	
	// 주문배송조회
	, goOrderList : function() {  
	 	location.href='/mypage/order/list';
	}
	
	// 취소교환반품조회
	, goClaimList : function() {
	 	location.href='/mypage/claim/list';
	}

	// 상품리뷰
	, goGoodsReview : function() {
		location.href ='/mypage/goods/reviewView';
	}

	// 상품수령완료
	, updateDeliveryStatus : function(ordNo, wayBilNo ) {
		var dlvMsg = MESSAGES['mypage.js.order.list.msg.deliveryconfirm1'] + "\n" + MESSAGES['mypage.js.order.list.msg.deliveryconfirm2'];
    
		if(!confirm(dlvMsg)) {
			return false;
		}
    
		strParams = {'ordNo' : ordNo, 'wayBilNo' : wayBilNo};
		
		$.ajax({
			type : "POST",
			url     : '/mypage/order/deliveryStatus/update.json',
			data : strParams,
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				alertLayer(MESSAGES['mypage.js.order.list.msg.deliveryconfirm.success']);
				pageReload();
			},
			error : function(jqXHR, textStatus, error) {
				if(jqXHR.status == "403") {
                    alert(MESSAGES['mypage.js.session.over.relogin']);
                    location.reload();
                } else {
                	alertLayer(jqXHR.responseJSON.message);
                }
			}				
		});	  
	}

	// 구매확정처리
	//[DEXC3-107] 구매확정 버튼 클릭시 안내문구 추가 2018-09-27 brandon start
	, updateOrderDecision : function(ordNo, ordGodTurn ) {
	 
	    	    
	    strParams = { 'ordNo' : ordNo, 'ordGodTurn' : ordGodTurn};
	    
	    //otool.confirmLayerCallback(MESSAGES['mypage.js.order.list.msg.purchaseconfirm'],mypageorder.purchaseconfirmAjax,strParams); 
	    if(confirm(MESSAGES['mypage.js.order.list.msg.purchaseconfirm'])) {
	    	mypageorder.purchaseconfirmAjax(strParams);
		}
	}
	// 구매 확정 처리
	,purchaseconfirmAjax : function(strParams) {
		
		$.ajax({
			type : "POST",
			url     : '/mypage/order/decision/update.json',
			data : strParams,
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				alertLayer(MESSAGES['mypage.js.order.list.msg.purchaseconfirm.success']);
				pageReload();
			},
			error : function(jqXHR, textStatus, error) {
				if(jqXHR.status == "403") {
                    alert(MESSAGES['mypage.js.session.over.relogin']);
                    location.reload();
                } else {
                	alertLayer(jqXHR.responseJSON.message);
                }
		    }				
		});	  
		
	}
	//[DEXC3-107] 구매확정 버튼 클릭시 안내문구 추가 2018-09-27 brandon end

	// 1:1문의
	, goInquiryList : function() {
		location.href ='/helpdesk/csInquiry/new?orderYn=Y';
	}
	
	// 배송추적
	, deliveryTracking : function (waybillNumber, url ,dlvComCd) {
		if(waybillNumber == ""){
			alertLayer(MESSAGES['mypage.js.order.list.msg.noinvoice']);
			return;
		} 
//	    if(dlvComCd == "POSTOFC_HDRY"){
//	    	var params = {};
//			params.waybillNumber = waybillNumber;
//			params.dlvComCd = dlvComCd;
//			window.open('http://service.epost.go.kr/trace.RetrieveRegiPrclDelivTibco.postal?sid1='+waybillNumber+'&displayHeader=N' , 'dlvPopup', 'width=520, height=600, resizable=0, scrollbars=yes, status=0, titlebar=0' );
//	    }else{
	    	if(url == ""){
	    		alertLayer(mypageTxt2);
				return;
			} 
	    	window.open(url + waybillNumber , 'dlvPopup', 'width=580, height=609, resizable=0, scrollbars=no, status=0, titlebar=0' );
//	    }
	}
	
};
