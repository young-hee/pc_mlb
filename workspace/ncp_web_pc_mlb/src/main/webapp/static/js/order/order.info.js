var orderinfo = {
	
	
	load : function(ordNo){
		
		localStorage.clear();
		
		$.ajax({
			type  : 'post',
			url   : "/order/"+ordNo+"/orderInfo.json",
			dataType : 'json',
			async : false,
			data  : "",
			contentType: "application/json",
			beforeSend: function (request)
            {
			  // block layer
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
            },
			success : function(data) {
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
				

				$("#orderInfoViewLayer").html("");
				$("#orderInfoViewLayer").html($("#orderInfoView").render(data,JSRENDER_HELPER));
				
				//naver
	   			fnf_naverKeywordAdvertisement("1",data.info.ord.saleSumAmt);
	   			
				var tgItems = [];
				$.each(data.info.ordGodExtendList, function(index, item) {
		   			var obj = new Object();
		   			obj.i = item.erpGodNo;
		   			obj.t = item.godNm;
		   			obj.p = item.rtlPrc;
		   			obj.q = item.ordQty;
		   			tgItems.push(obj);
		   		});
		   		
		   		
		   		wptg_tagscript_vars.push(
		   			(function() {
		   				return {
		   					wp_hcuid:USER.mbrNo,  	/*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.*주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
		   					ti:"39428",
		   					ty:"PurchaseComplete",
		   					device:"web"
		   					,items:tgItems
		   				};
		   		}));
		   		
		   		fnf_appendTargetGateScript();
		   		
		   		var criItems = [];
		   		
		   		$.each(data.info.ordGodExtendList, function(index, item) {
		   			var obj = new Object();
		   			obj.id = item.erpGodNo;
		   			obj.price = item.rtlPrc;
		   			obj.quantity = item.ordQty;
		   			criItems.push(obj);
		   		});
		   		
		   		window.criteo_q.push( 
		   		        { event: "setAccount", account: 61384 },
		   		        { event: "setSiteType", type: "d" },
		   		        { event: "trackTransaction", id : ordNo, item: criItems }
		   		);
		   		
		   		fnf_appendCriteoScript();
		   		
		   		fbq('track', 'Purchase', {value: data.info.ord.saleSumAmt, currency: 'KRW'});
		   		
		   		// ga
		   		ga('require', 'ecommerce');
		   		ga('ecommerce:addTransaction', {
		   		  'id': ordNo,		// 주문번호 (필수값)
		   		  'affiliation': 'MLB',	        // 가게 이름(쇼핑몰명)   --고정값
		   		  'revenue': data.info.ord.rtlPrcSumAmt,			// 총주문금액
		   		  'shipping': data.info.ord.dlvCstSumAmt,			// 배송비
		   		  'tax': '0'				// 세금(부가세등)
		   		});
		
		   		$.each(data.info.ordGodExtendList, function(index, item) {
		   			var itemDspCtgryNm = item.dspCtgryNm;
		   			var dspCtgryNm = "";
		   			if(itemDspCtgryNm != null && itemDspCtgryNm != "") {
		   				var dspCtgryNms = itemDspCtgryNm.split(">");
		   				// 최하위 카테고리로 세팅.
		   				$(dspCtgryNms).each(function(v, nm) {
		   					dspCtgryNm = nm;
			        	});
		   			}
		   			// 장바구니에 있는 아이템갯수만큼 추가해야 한다.
		   			//ga('bna.ecommerce:addItem', {
		   			ga('ecommerce:addItem', {
		   			  'id': ordNo,		// 주문번호 (필수값) trans있는 id와 같아야함
		   			  'name': item.godNm,			// 상품명 (필수값)
		   			  'sku': item.erpGodNo,      // SKU/code.
		   			  'category': dspCtgryNm,			// 상품의 카테고리
		   			  'price': item.rtlPrc,			// 개별 상품가격
		   			  'quantity': item.ordQty			        // 주문개수
		   			});
		   		});
	
		   		//실제로 GA로 데이터를 보낸다.
		   		ga('ecommerce:send');

		   		// 추가 ga(PC, MB 동일한 ID)
		   		ga('newTracker.require', 'ecommerce');
		   		ga('newTracker.ecommerce:addTransaction', {
		   		  'id': ordNo,		// 주문번호 (필수값)
		   		  'affiliation': 'MLB',	        // 가게 이름(쇼핑몰명)   --고정값
		   		  'revenue': data.info.ord.rtlPrcSumAmt,			// 총주문금액
		   		  'shipping': data.info.ord.dlvCstSumAmt,			// 배송비
		   		  'tax': '0'				// 세금(부가세등)
		   		});
		
		   		$.each(data.info.ordGodExtendList, function(index, item) {
		   			var itemDspCtgryNm = item.dspCtgryNm;
		   			var dspCtgryNm = "";
		   			if(itemDspCtgryNm != null && itemDspCtgryNm != "") {
		   				var dspCtgryNms = itemDspCtgryNm.split(">");
		   				// 최하위 카테고리로 세팅.
		   				$(dspCtgryNms).each(function(v, nm) {
		   					dspCtgryNm = nm;
			        	});
		   			}
		   			// 장바구니에 있는 아이템갯수만큼 추가해야 한다.
		   			//ga('bna.ecommerce:addItem', {
		   			ga('newTracker.ecommerce:addItem', {
		   			  'id': ordNo,		// 주문번호 (필수값) trans있는 id와 같아야함
		   			  'name': item.godNm,			// 상품명 (필수값)
		   			  'sku': item.erpGodNo,      // SKU/code.
		   			  'category': dspCtgryNm,			// 상품의 카테고리
		   			  'price': item.rtlPrc,			// 개별 상품가격
		   			  'quantity': item.ordQty			        // 주문개수
		   			});
		   		});
	
		   		//실제로 GA로 데이터를 보낸다.
		   		ga('newTracker.ecommerce:send');
		   		
		   		// edn	
		   		var adbay = new adbayPurchaseClass("mlb"); //  shopId 입력
		   		var godStr = "";
		   		var productStr = "";
		   		var priceStr = "";
		   		
		   		$.each(data.info.ordGodExtendList, function(index, item) {
		   			if(index!=0){ 
		   				godStr += "|";
		   				productStr += "|";
		   				priceStr += "|";
		   			}
		   			godStr += item.godNo;
	   				productStr += item.ordQty;
	   				priceStr += item.rtlPrc;
		   		});
		   		adbay.setGoods(godStr); // adbay에 등록한 ep 번호 입력 (중복일 경우 '|' 구분)
	   		    adbay.setProduct(productStr); // 한 상품당 개수  (중복일 경우 '|' 구분)
	   		    adbay.setPrice(priceStr); // 상품의 가격  (중복일 경우 '|' 구분)

		   		adbay.send();
		   		
		   	    // Global site tag Event snippet
		   		gtag('event', 'conversion', {'send_to': 'AW-840871019/L_flCJCh5XMQ69j6kAM',
		   			'value' : data.info.ord.saleSumAmt.toFixed(2),	// 전환 가치는 마침표를 소수점 구분 기호로 사용하여 숫자로 전달
		   		    'currency' : 'KRW',
		   		    'transaction_id': ordNo
		   		});
		   		
				var arry = new Array();
				$.each(data.info.ordGodExtendList, function(index, item) {
		   			var itemDspCtgryNm = item.dspCtgryNm;
		   			var dspCtgryNm = "";
		   			var c1 = "";
		   			var c2 = "";
		   			var c3 = "";
		   			if(itemDspCtgryNm != null && itemDspCtgryNm != "") {
		   				var dspCtgryNms = itemDspCtgryNm.split(">");
		   				// 카테고리 세팅.
		   				$(dspCtgryNms).each(function(v, nm) {
		   					if(v == 0) {
		   						c1 = nm;
		   					}
		   					else if(v == 1) {
		   						c2 = nm;
		   					}
		   					else {
		   						c3 = nm;
		   					}
			        	});
		   			}
					var tt = {};
					tt.id = item.erpGodNo;
					tt.c1 = c1;
					tt.c2 = c2;
					tt.c3 = c3;
					tt.count = item.ordQty;
					tt.total_sales = item.rtlPrc;
					arry.push(tt);
				});
				
				recoPick('sendLog','order', arry);
			},
			error : function(xhr) {
				var data = $.parseJSON(xhr.responseText)
				//otool.alert(data.message);
				alert(MESSAGES['order.js.txt.wrong']);
				location.href="/";
			}
		});
		
	}
};


