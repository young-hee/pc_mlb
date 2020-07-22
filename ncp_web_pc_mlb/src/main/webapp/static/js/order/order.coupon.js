var ordercoupon = {
	initCount : 0
	,initBtn : function(){
		$("#btnIssueCoupon").click(function(){
			if ($("#issueSerial").val() === "") {
				alert(MESSAGES['order.js.txt.alert.msg3']);
				return false;
			}
			
			ordercoupon.issueCoupon(true);
			
		});
		

				
	}
	,loadCoupon : function(isLayer,isInit){
		
		
		if(!orderform.getOrderInfo().mbr){
			return false;
		}
		
		var obj = new Object();
		var bskGodList = [];
		obj.bskGodList = orderform.getOrderInfo().cartResultList;
		
		obj = orderform.dataInjection(obj);
		
		$.ajax({
			type  : 'post',
			url   : "/order/coupon/list.json",
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
				$.each(info.memberCouponList, function(cindex, citem) {
					$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
						if(citem.bskGod.godTurn==item.bskGod.godTurn){
							citem.cartGodInfo = item;
							return false;
						}
					});
				});
				
				var godCpnList =  [];
				var dlvCpnList =  [];
				var bskCpnList =  [];
				
				
				
				
				
				$.each(info.memberCouponList, function(outerIdx, outerItem) {
					outerItem.isGodCpn = false;
					$.each(outerItem.goodsCouponResultList, function(cindex, citem) {
						var isDuplicate = false;
						if(citem.prm.prmDtlTpCd!='BSK_CPN'&&citem.prm.prmDtlTpCd!='DLV_CST_CPN'){
							outerItem.isGodCpn = true;
							
							$.each(godCpnList, function(index, item) {
								if(citem.mbrCpnNo){
									if(citem.mbrCpnNo===item.mbrCpnNo){
										isDuplicate = true;
										return false;
									}
								}
							});
							if(!isDuplicate){
								godCpnList.push(citem);
							}
						}
					});
					
				});
				
				// 배송비 쿠폰 DLV_CST_CPN
				if(parseInt($("#orderRegion").data("dlv_amount"))>0){
					$.each(info.memberCouponList, function(outerIdx, outerItem) {
						$.each(outerItem.goodsCouponResultList, function(cindex, citem) {
							var isDuplicate = false;
							if(citem.prm.prmDtlTpCd==='DLV_CST_CPN'){
								$.each(dlvCpnList, function(index, item) {
									if(citem.mbrCpnNo===item.mbrCpnNo){
										isDuplicate = true;
										return false;
									}
								});

								if(!isDuplicate){
									dlvCpnList.push(citem);
								}
							}
						});
						
					});
				}



				// 주문 쿠폰 BSK_CPN
				$.each(info.memberCouponList, function(outerIdx, outerItem) {
					$.each(outerItem.goodsCouponResultList, function(cindex, citem) {
						
						var local_cpnUseMinPchAmt = 0;
						
						$.each(orderform.getOrderInfo().cartResultList, function(local_index, local_item) {
							$.each(info.memberCouponList, function(local_outerIdx, local_outerItem) {
								if(local_item.bskGod.godNo==local_outerItem.god.godNo){
									$.each(local_outerItem.goodsCouponResultList, function(local_cindex, local_citem) {
										if(local_citem.mbrCpnNo==citem.mbrCpnNo){
											local_cpnUseMinPchAmt += parseInt(local_item.applyPrice);
										}
									});
								}
							});
						});
							
						var isDuplicate = false;
						
						if(citem.prm.prmDtlTpCd==='BSK_CPN'){
							if(citem.prmCpn.cpnUseMinPchAmt==0||citem.prmCpn.cpnUseMinPchAmt<=local_cpnUseMinPchAmt){
								$.each(bskCpnList, function(index, item) {
									if(citem.mbrCpnNo===item.mbrCpnNo){
										isDuplicate = true;
										return false;
									}
								});
								
								
								if(!isDuplicate){
									bskCpnList.push(citem);
								}
							}
						}
					});
					
				});
				
				info.godCpnList = godCpnList;
				info.dlvCpnList = dlvCpnList;
				info.bskCpnList = bskCpnList;
				
				$("#useableCouponCnt").html(godCpnList.length+dlvCpnList.length+bskCpnList.length);
				
				info.bskCpnNm = '';
				$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
					if(item.applyBskCpn){
						info.bskCpnNm = item.applyBskCpn.prm.prmNm;
						return false;
					}
				});
				info.bskDlvNm = '';
				$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
					if(item.applyDlvCpn){
						info.bskDlvNm = item.applyDlvCpn.prm.prmNm;
						return false;
					}
				});
				
				info.godCpnAmt = parseInt($("#orderRegion").data("godCpnAmt"));
				info.bskCpnAmt = parseInt($("#orderRegion").data("bskCpnAmt"));
				info.dlvCpnAmt = parseInt($("#orderRegion").data("dlvCpnAmt"));
				
				$("#couponText").val(JSON.stringify(info));
				
				
				$("#orderRegion").data("couponInfo",info);
				
				
				if(isLayer){
					$("#couponViewLayer").html("");
					$("#couponViewLayer").html($("#couponView").render(ordercoupon.getCouponInfo(),JSRENDER_HELPER));
					ordercoupon.initBtn();
				}
				
				if(isInit){
					ordercoupon.initDcInfo();
				}
				
			},
			error : function(xhr) {
				// hide block layer
				if(xhr&&xhr.responseText){
					var data = $.parseJSON(xhr.responseText)
					alert(data.message);
					//location.href = '/';
				}else{
					alert(MESSAGES['common.js.session.over']);
					location.href = '/';
				}
				// 오류시 메시지 출력후 어디로든 보내야함
			}
		});
		
	}
	,getCouponInfo : function() {
		return $("#orderRegion").data("couponInfo");
	}
	,issueCoupon : function(){
		$.ajax({
			type:"post"
			,url:"/order/coupon/add.json"
			,data : "issueSerial=" + $('#issueSerial').val()
			,dataType: "json"
			,async : false
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success : function(data) {
				
				
				if (data.resultMsg === "SUCCES") {
					alert(MESSAGES['order.js.txt.alert.msg11']);
					ordercoupon.loadCoupon(true);
				} else if (data.resultMsg === "PRM_STAT_STPGE") {
					alert(MESSAGES['order.js.txt.alert.msg4']);
					ordercoupon.loadCoupon(true);
				} else if (data.resultMsg === "ISMCBTW_OFF") {
					alert(MESSAGES['order.js.txt.alert.msg5']);
					ordercoupon.loadCoupon(true);
				} else if (data.resultMsg === "TOT_ISSU_QTY_EXCESS") {
					alert(MESSAGES['order.js.txt.alert.msg6']);
					return false;
				} else if (data.resultMsg === "IDBY_ISSU_QTY_EXCESS") {
					alert(MESSAGES['order.js.txt.alert.msg7']);
					return false;
				} else if (data.resultMsg === "PROSISSU_SN") {
					alert(MESSAGES['order.js.txt.alert.msg8']);
					return false;
				} else if (data.resultMsg === "SN_EXST_NOT") {
					alert(MESSAGES['order.js.txt.alert.msg5']);
					ordercoupon.loadCoupon(true);
				} else if (data.resultMsg === "ADMIN_INQ") {
					alert(MESSAGES['order.js.txt.alert.msg9']);
					return false;
				} else {
					alert(MESSAGES['order.js.txt.alert.msg5']);
					ordercoupon.loadCoupon(true);
				}
				
				
			}
			,error : function(xhr) {
				var data = $.parseJSON(xhr.responseText)
				alert(data.message);
				//alert(data.message);
				
			}
			,complete : function(data) {
				
			}
		});
	}
	,applyGodCoupon : function(mbrCpnNo,godTurn){
		//alert("mbrCpnNo:"+mbrCpnNo);
		var applyItem;
		$.each(ordercoupon.getCouponInfo().memberCouponList, function(cindex, citem) {
			if(citem.cartGodInfo.bskGod.godTurn==godTurn){
				$.each(citem.goodsCouponResultList, function(index, item) {
					if(item.mbrCpnNo==mbrCpnNo){
						applyItem = item;
						return false;
					}
				});
				return false;
			}
		});
		
		var isDuplicate = false;
		// 중복적용 체크
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.bskGod.godTurn!=godTurn&&item.applyCpn){
				if(item.applyCpn.mbrCpnNo==mbrCpnNo){
					isDuplicate = true;
					return false;
				}
			}
		});
		
		if(isDuplicate){
			if (confirm(MESSAGES['order.js.txt.alert.msg10'])) {
				$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
					if(item.bskGod.godTurn!=godTurn&&item.applyCpn){
						if(item.applyCpn.mbrCpnNo==mbrCpnNo){
			    			delete item.applyCpn;
			    			$("#prdCpnTxt_"+item.bskGod.godTurn).html(MESSAGES['order.js.txt.select']);
							return false;
						}
					}
				});
				//alert('적용된컬럼 리셋');
			}else{
				//alert('해당값 초기화');
				ordercoupon.loadCoupon(true);
				return false;
			}
		}
		
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.bskGod.godTurn==godTurn){
				//alert(applyItem);
				item.applyCpn = applyItem;
			}
		});
		
		//값 변경 때문에 적용
		setTimeout(function() {ordercoupon.postGodCoupon(true);}, 10);
	}
	,applyGodDiscount : function(prmNo,godTurn){
		//alert("prmNo:"+prmNo+",godTurn:"+godTurn);

		var applyItem;
		$.each(ordercoupon.getCouponInfo().memberCouponList, function(cindex, citem) {
			if(citem.cartGodInfo.bskGod.godTurn==godTurn){
				$.each(citem.goodsCouponResultList, function(index, item) {
					if(item.prm.prmNo==prmNo){
						applyItem = item;
						return false;
					}
				});
				return false;
			}
		});
	
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.bskGod.godTurn==godTurn){
				//alert(applyItem);
				item.applyCpn = applyItem;
			}
		});
		
		
		ordercoupon.postGodCoupon(true);	
		
	}
	,postGodCoupon : function(isGod){
		
		if(isGod){
			// 주문쿠폰 리셋
			$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
				delete item.applyBskCpn;
			});
			
			$("#couponAppCartText").val(JSON.stringify(orderform.getOrderInfo().cartResultList));
			
			// bsk reset html
			$("#bskCpnNm").html(MESSAGES['order.js.txt.select']);
		}
		
		//포인트 리셋
		orderform.getOrderInfo().ord.webpntUseSumAmt = 0;
		$("#webPointUse").val(orderform.getOrderInfo().ord.webpntUseSumAmt);
		orderform.getOrderInfo().ord.unityPntUseSumAmt = 0;
		$("#maxMileageUse").val(orderform.getOrderInfo().ord.unityPntUseSumAmt);
		$("#mileageUse").val(orderform.getOrderInfo().ord.unityPntUseSumAmt);
		
		orderform.mixupPayment(true);
	}
	,applyDlvCoupon : function(mbrCpnNo){
		//alert("mbrCpnNo:"+mbrCpnNo);


		$.each(ordercoupon.getCouponInfo().memberCouponList, function(cindex, citem) {
			$.each(citem.goodsCouponResultList, function(index, item) {
				if(item.mbrCpnNo==mbrCpnNo){
					$.each(orderform.getOrderInfo().cartResultList, function(gindex, gitem) {
						if(citem.cartGodInfo.bskGod.godTurn==gitem.bskGod.godTurn){
							gitem.applyDlvCpn = item;
							return false;
						}
					});
					return false;
				}
			});
		});
		if(mbrCpnNo==''||mbrCpnNo==undefined){
			$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
				delete item.applyDlvCpn;
			});
		}
		
		
		ordercoupon.postGodCoupon(false);
		
	}
	,applyBskCoupon : function(mbrCpnNo){
		//alert("mbrCpnNo:"+mbrCpnNo);

		var isGodCpnDplctCd = false;
		$.each(ordercoupon.getCouponInfo().memberCouponList, function(cindex, citem) {
			$.each(citem.goodsCouponResultList, function(index, item) {
				if(item.mbrCpnNo==mbrCpnNo){
					$.each(orderform.getOrderInfo().cartResultList, function(gindex, gitem) {
						if(citem.cartGodInfo.bskGod.godTurn==gitem.bskGod.godTurn){
							gitem.applyBskCpn = item;
							if(item.prm.godCpnDplctCd=='IMPS'){
								isGodCpnDplctCd = true;
							}
							return false;
						}
					});
					return false;
				}
			});
		});
		if(mbrCpnNo==''||mbrCpnNo==undefined){
			$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
				delete item.applyBskCpn;
			});
		}else{
			if(isGodCpnDplctCd){
				$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
					delete item.applyCpn;
					$(".prdCpnTxt").html(MESSAGES['order.js.txt.select']);
				});
			}
		}
		
		//값 변경 때문에 적용
		setTimeout(function() {ordercoupon.postGodCoupon(false);}, 10);

	}
	,initDcInfo : function(){
		
		$.each(orderform.getOrderInfo().cartResultList, function(index, item) {
			if(item.cpnNo){;
				$.each(ordercoupon.getCouponInfo().memberCouponList, function(cindex, citem) {
					if(item.bskGod.godTurn == citem.cartGodInfo.bskGod.godTurn){
						$.each(citem.goodsCouponResultList, function(gcindex, gcitem) {
							if(item.cpnNo==gcitem.prm.prmNo){
								item.applyCpn = gcitem;
								return false;
								
							}
						});
						
					}
				});
			}
		});
		
		ordercoupon.postGodCoupon(true);
		
	}
	,registIssueCoupon : function(){
		if ($("#issueSerial").val() === "") {
			alert(MESSAGES['order.js.txt.alert.msg3']);
			return false;
		}
		
		ordercoupon.issueCoupon(true);

	}
	
};
