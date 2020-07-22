<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<c:choose>
    <%-- 클레임배송비 결제 --%>
    <c:when test='${orderInfo.type eq "clmDlvAmtPay"}'>
        <form action="<c:url value='/mypage/claim/payment/addpay.json'/>" name="orderDTO" id="orderDTO" method="post" style="display:inline-block;">
        <input type="hidden" id="type"       name="type"     value="clmDlvAmtPay"/>
        <input type="hidden" id="clmNo"      name="clmNo"     value="${orderInfo.clmNo}"/>
        <input type="hidden" id="payNo"	value="${orderInfo.payNo}"/>
        <input type="hidden"  id="payNoType" value="U" />
        <input type="hidden" id="addPayClmTpCd" name="addPayClmTpCd" value="${orderInfo.addPayClmTpCd }"/>
    </c:when>
    <%-- (대량주문 ||  전화주문) &&  결제대기 --%>
    <c:when test='${orderInfo.ordTpCd eq "LAG_QTY_ORD" && orderInfo.ordStatCd eq "DEPST_WAIT"}'>
        <form action="<c:url value='/mypage/order/payment/paylagqtyord'/>" name="orderDTO" id="orderDTO" method="post" style="display:inline-block;">
        <input type="hidden" id="type"       name="type"     value="updaePayMethodChange"/>
        <input type="hidden" id="payNo"	value=""/>
        <input type="hidden"  id="payNoType" value="I" />
    </c:when>
    <%-- 가상계좌 || 예약주문--%>
    <c:when test='${((orderInfo.ordTpCd eq "GNRL_ORD" || orderInfo.ordTpCd eq "SHOP_PKUP_ORD" || orderInfo.ordTpCd eq "GFCT_ORD" || orderInfo.ordTpCd eq "RESVE_ORD") && orderInfo.ordStatCd eq "DEPST_WAIT" ) }'>
        <form action="<c:url value='/mypage/order/payment/changepay'/>" name="orderDTO" id="orderDTO" method="post" style="display:inline-block;">
        <input type="hidden" id="type"       name="type"    value="rerunPayMethodChange"/>
        <input type="hidden" id="payNo"	value=""/>
        <input type="hidden"  id="payNoType" value="I" />
    </c:when>
</c:choose>

	<input type="hidden" id="${_csrf.parameterName}"        name="${_csrf.parameterName}"   value="${_csrf.token}"/>
	<input type="hidden" id="ordNo"        name="ord.ordNo"   value="${orderInfo.ordNo}"/>
	<input type="hidden" id="chkVirPayMnCd"        name="ord.chkVirPayMnCd"  value="${orderInfo.payMnCd}"/>
	<input type="hidden" id="cstmrNm" name="ord.cstmrNm" value="${orderInfo.cstmrNm}"/>

	<%-- NAVER PAY 파라메터 --%>
	<input type="hidden" name="pay.pgSectCd" id="pay.pgSectCd" value=""/>
	<input type="hidden" name="naverPayApprove.paymentId" id="naverPayApprove.paymentId" value=""/>

	<%-- KCP 파라메터 --%>
	<input type="hidden" name="kcpDTO.req_tx" 			id="kcpDTO.req_tx" 			value=""/>
	<input type="hidden" name="kcpDTO.enc_data" 		id="kcpDTO.enc_data" 		value=""/>
	<input type="hidden" name="kcpDTO.enc_info" 		id="kcpDTO.enc_info" 		value=""/>
	<input type="hidden" name="kcpDTO.tran_cd" 			id="kcpDTO.tran_cd" 		value=""/>
	<input type="hidden" name="kcpDTO.ordr_idxx" 		id="kcpDTO.ordr_idxx" 		value=""/>
	<input type="hidden" name="kcpDTO.use_pay_method" 	id="kcpDTO.use_pay_method" 	value=""/>
	

			<!-- layerpopup - 결제수단 변경 -->
			<section class="layer-popup-cont" tabindex="0" style="width:529px">
				<h2>
					
					<c:choose>
       					<c:when test='${orderInfo.type eq "clmDlvAmtPay"}'>
       						<spring:message code="mypage.order.detail.btn.addpay"/>
       					</c:when>
       					<c:otherwise>
       						<spring:message code="mypage.order.lbl.payment.change.pay" />
       					</c:otherwise>
       				</c:choose>
				</h2>
				<div class="layer-cont od ly-box">
					
					<!-- 결제수단 -->
					<div class="orderWriteArea">
						<div class="orderTotalNb">
							<dl>
								<dt><spring:message code="mypage.order.lbl.payment.payamount" /></dt>
								<dd><strong><fmt:formatNumber value="${orderInfo.payExchgRtCrncySumAmt}" pattern="#,###" /></strong><spring:message code="mypage.order.lbl.currency" /></dd>
							</dl>
						</div>
						<div class="orderPayOptTab">
							<div class="orderPayOpt">
								<ul>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdPay01" name=pay.payMnCd checked="checked" value="CREDT_CARD_PAY" >
											<span><spring:message code="mypage.order.lbl.select" /></span>
										</span>
										<label for="card_payment" class="orderPayOptSel"><spring:message code="mypage.order.lbl.payment.creditcard"/></label>
									</li>
									<li id="li_naver_payment">
										<span class="rdo-skin">
											<input type="radio" id="rdPay02" name="pay.payMnCd" value="NAVER_PAY" >
											<span><spring:message code="mypage.order.lbl.select" /></span>
										</span>
										<label for="naver_payment" class="orderPayOptSel"><spring:message code="mypage.order.lbl.payment.naverpay"/></label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdPay03" name="pay.payMnCd" value="RLTM_BNK_ACCT_PAY" >
											<span><spring:message code="mypage.order.lbl.select" /></span>
										</span>
										<label for="virtual_payment" class="orderPayOptSel"><spring:message code="mypage.order.lbl.payment.rltmbnk"/></label>
									</li>
								</ul>
							</div>
							
							<!-- 신용카드결제 시 유의사항 -->
		                  	<div class="orderPaytCont" style="display:block">
			                   	<h4><spring:message code="mypage.order.lbl.payment.creditcard.info"/></h4>      
			                   	<div class="payBgCont">
				                   	<div class="mbBox payGuide">
				               			<a href="#lypopCardPayCf" class="btn sm d_layer_open"><spring:message code="order.payment.info2" /></a>
				               			<a href="#lypopCardPaysf" class="btn sm d_layer_open"><spring:message code="order.payment.info3" /></a>
				               			<a href="#lypopCardPayClick" class="btn sm d_layer_open"><spring:message code="order.payment.html.info5" /></a>
				               		</div>    
									<ul class="text-list01">
				                   		<li><spring:message code="order.payment.info4" /></li>  
				                   	</ul>  
			                   	</div>    
		                   	</div>
		                   	<!--  //신용카드결제 시 유의사항 -->
		                   	
		                   	<!-- 결제수단 - 네이버페이 : str -->
							<div class="orderPaytCont">
								<h4><spring:message code="mypage.order.lbl.payment.naverpay.info" /></h4>
				              	<div class="payBgCont">
					              	<ul class="text-list01">
					              		<li><spring:message code="mypage.order.lbl.payment.naverpay.info1" /></li>
					              		<li><spring:message code="mypage.order.lbl.payment.naverpay.info2" />
					              			<ul class="text-list02">
					              				<li><spring:message code="mypage.order.lbl.payment.naverpay.info3" /></li>
					              				<li><spring:message code="mypage.order.lbl.payment.naverpay.info4" /></li>
					              			</ul>
					              		</li>
					              		<li><spring:message code="mypage.order.lbl.payment.naverpay.info5" /></li>
					              	</ul>
				              	</div>
							</div>
							<!-- //결제수단 - 네이버페이 : end -->
							
							<!-- 결제수단 - 실시간 계좌이체 : str -->
							<div class="orderPaytCont" >
								<h4><spring:message code="order.payment.escrow.msg1" /></h4>
								<div class="payBgCont">
									<div class="rdOptBox mbBox">
										<span class="rdo-skin">
											<input type="radio" id="rdoEscrow01" name="escrPayYn" value="Y">
											<span><spring:message code="order.js.txt.select" /></span>
										</span>
										<label for="rdEscroOpt02Y" class="d_tab02_select"><spring:message code="order.payment.escrow.msg2" /></label>
							
										<span class="rdo-skin">
											<input type="radio" id="rdoEscrow02" name="escrPayYn" value="N" checked="checked">
											<span><spring:message code="order.js.txt.select" /></span>
										</span>
										<label for="rdEscroOpt02N" class="d_tab02_select"><spring:message code="order.payment.escrow.msg3" /></label>
									</div>
									<spring:message code="order.payment.html.info5.3" />
								</div>
							</div>
							<!-- //결제수단 - 실시간 계좌이체 : end -->
						</div>
	
						<div class="btnWrapBox">
							<a href="#" class="btn w160 d_layer_close"><spring:message code=""/><spring:message code="mypage.order.btn.cancel"/></a>
							<a href="#" class="btn fill w160" onclick="javascript:doPayment(); return false;"><spring:message code=""/><spring:message code="mypage.order.btn.confirm"/></a>
						</div>
				
					</div>
					
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code=""/><spring:message code="mypage.order.btn.close"/></button>
				</div>
			</section>
		<!-- //layerpopup - 결제하기 -->
</form>
<form id="kcpForm" style="display:inline-block;"></form>
<script>
    
    function doPayment() {
    
    	var data = {
    			ordNo : '${orderInfo.ordNo}' ,
    			type : $("#payNoType").val() ,
    			payNo : $("#payNo").val(),
    			'${_csrf.parameterName}' : '${_csrf.token}'
    	};

    	<%-- TODO 로딩바 --%>
		$.ajax({
			type : "POST" ,
			url : "/mypage/order/payment/pginfo.json" ,
			data : data ,
			dataType : "json" ,
			success : function(jsonData) {
				
				var new_payNo = jsonData.payNo;
				
				$("#payNo").val(new_payNo);
				
				var escrYn = "N";
				
				$('[name="escrPayYn"]').each(function(){
					if($(this).is(":checked:enabled")){
						escrYn = $(this).val();
					}
				});
				
				var data = {  
					  ordNo :       '${orderInfo.ordNo}'
                       , totAmt :      '${orderInfo.payExchgRtCrncySumAmt}' 
                       , itemCode :    '${orderInfo.itmNoStr}'
                       , itemName :    "${orderInfo.godNm}외${orderInfo.itemNoCnt}건"
                       , itemCnt :     '${orderInfo.itemNoCnt}'
                       , mbrId :       '${mbrId}'
                       , mbrEmail :    '${mbrEmail}'
                       , buyerNm :     '${orderInfo.cstmrNm}'
                       , buyrTel :     '${orderInfo.cstmrMobilAreaNo}${orderInfo.cstmrMobilTlofNo}${orderInfo.cstmrMobilTlofWthnNo}'
                       , recpNm :      '${orderInfo.lgsDlvspFoExtend[0].addrseNm}'
                       , recpTel :     '${orderInfo.lgsDlvspFoExtend[0].addrseMobilAreaNo}${orderInfo.lgsDlvspFoExtend[0].addrseMobilTlofNo}${orderInfo.lgsDlvspFoExtend[0].addrseMobilTlofWthnNo}'
                       , postNo :      '${orderInfo.lgsDlvspFoExtend[0].addrsePostNo}'
                       , addr1 :       '${orderInfo.lgsDlvspFoExtend[0].addrseBaseAddr}'
                       , addr2 :       '${orderInfo.lgsDlvspFoExtend[0].addrseDetailAddr}'
					   , payNo : new_payNo
					   , type : '${type}'
					   , ordNo : '${orderInfo.ordNo}'
					   , escrYn : escrYn
                      };
				
				data.npayInfo = jsonData.npayInfo;
				data.kcpInfo = jsonData.kcpInfo;
				
	            mypagepay.setMypageParameter(document.orderDTO, data, '${orderInfo.ordTpCd}');
	            mypagepay.payment(document.orderDTO, data);		
			},
			error : function(e){
				alert(e.responseText);
			}
		});

    }
    
    
    var secureSubmit = function(formObject) {
        var csrfToken = $("meta[name='_csrf']").attr("content");
        var csrfName = $("meta[name='_csrf_header']").attr("content");
        $(formObject).append('<input type="hidden" name="'+csrfName+'" value="'+csrfToken+'" />');
        $(formObject).submit();
    }   
    
    function changeCardInfo(obj, div) {
    	$('.card-info-list a').removeClass('on');
    	$(obj).addClass('on');
    	$('.order-payment-content .card-info-box').hide();
    	$("#" + div).show();	
    }
    
    $(document).ready(function(){
    	toggle.init();
    	
    	$('.orderPayOpt input[type=radio]').click(function(){
            $('.orderPayOptTab .orderPaytCont').css('display','none');
            $('.orderPayOptTab .orderPaytCont').eq($('.orderPayOpt input[type=radio]').index(this)).css('display','block');
        });
    });
    
</script>