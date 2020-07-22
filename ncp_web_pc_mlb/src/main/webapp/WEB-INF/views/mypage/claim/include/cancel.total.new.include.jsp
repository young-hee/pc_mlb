<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<%-- TODO validator... --%>
<form name="totalCancelForm" id ="totalCancelForm" action="<c:url value='orderDeliveryLocationChange'/>" method="post" style="display:inline-block;">
	<input type="hidden" id="${_csrf.parameterName}"        name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
	<input type="hidden" id="ordNo"                         name="ordNo" 					value="${totalCancelInfo.ordNo}"/>	  
	<input type="hidden" id="clmResnCd"                     name="clmResnCd" 				value="CSTMR_CHGMIND_CNCL" />	<%-- 클래임 사유 코드 --%>  
	<input type="hidden" id="clmResnCont"                   name="clmResnCont" 				value="" />
	<input type="hidden" id="rfdBnkCd"                      name="rfdBnkCd" 				value="" />	<%-- 환불계좌 은행코드 --%>
	<input type="hidden" id="rfdAcnthldrNm"                 name="rfdAcnthldrNm"            value=""/>	<%-- 환불계좌 예금주명  --%>
	<input type="hidden" id="rfdBnkAcctNo"                  name="rfdBnkAcctNo"             value=""/>	<%-- 환불계좌 번호  --%>
	<input type="hidden" id="refundCheckYn"                 name="refundCheckYn"            value="N"/>
	
	
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2><spring:message code="mypage.order.detail.lbl.totalcancel" text="주문 전체취소"/></h2>
		<div class="layer-cont ly-box">
		
			<table class="board-write selMgSm">
				<caption><spring:message code="mypage.order.detail.lbl.totalcancel" text="주문 전체취소"/></caption>
				<colgroup>
					<col style="width:125px;">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><spring:message code="mypage.order.list.lbl.orderdate" text="주문일"/></th>
						<td>
							<div class="board-write-text">${totalCancelInfo.ordDt}</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><spring:message code="mypage.order.list.lbl.orderno" text="주문번호"/></th>
						<td>
							<div class="board-write-text">${totalCancelInfo.ordNo}</div>
						</td>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}">
				<h3 class="ly_tit"><spring:message code="mypage.claim.lbl.refund.account" text="환불계좌 정보"/></h3>
				<table class="board-write selMgSm">
					<caption><spring:message code="mypage.claim.lbl.refund.account" text="환불계좌 정보"/></caption>
					<colgroup>
						<col style="width:125px;">
						<col>
					</colgroup>
					<tbody>
						<tr>
							<th><label for="bankName"><spring:message code="mypage.claim.lbl.refund.bankname" text="은행명 "/></label> <span class="required">*</span></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select" style="width:100%;">
									<button type="button" id="bankName" class="d_select_sel" style="width:100%;"><span><spring:message code="mypage.order.lbl.select" text="선택하세요"/></span></button>
									<ul>
										<li><a href="#none" onclick="javascript:chgSrchType('rfdBnkCd','${clm.cd}'); false;"><spring:message code="mypage.order.lbl.select" text="선택하세요"/></a></li>
										<ncp:codes group="BNK" var="bnk"/>
							            <c:forEach var="bk" items="${bnk}">
							            	<c:if test="${not empty bk.asstnCd1}">
												<li><a href="#none" onclick="javascript:chgSrchType('rfdBnkCd', '${bk.cd}'); false;">${bk.cdNm}</a></li>
											</c:if>
							            </c:forEach>
									</ul>
								</div>
								<!-- //select -->
							</td>
						</tr>
						<tr>
							<th><label for="rfdAcnthldrNmOd"><spring:message code="mypage.claim.lbl.refund.accountname" text="예금주"/></label> <span class="required">*</span></th>
							<td>
								<div class="board-gap">
									<input type="text" id="rfdAcnthldrNmOd" class="input-style01 sm textOnly" style="width:100%;" maxlength="100">
								</div>
							</td>
						</tr>
						<tr>
							<th><label for="rfdBnkAcctNoOd"><spring:message code="mypage.claim.lbl.refund.accountno" text="계좌번호"/></label> <span class="required">*</span></th>
							<td>
								<div class="board-gap">
									<input type="text" id="rfdBnkAcctNoOd" class="input-style01 sm numberOnly" style="width:100%;" maxlength="100" placeholder="<spring:message code='mypage.claim.lbl.refund.accountno.placeholder' />">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</c:if>
			
			<!--  button -->
			<div class="lyBtnArea">
				<a href="#" class="btn w160 d_layer_close"><spring:message code="mypage.order.btn.cancel" text="취소"/></a>
				<a href="#" class="btn fill w160" id="acceptBtn" onclick="acceptTotalCancel(); return false;"><spring:message code="mypage.order.list.btn.ordercancel" text="주문취소"/></a>
			</div>
		</div>

		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="mypage.order.btn.close" text="닫기"/></button>
		</div>
	</section>
</form>

<script>
	
	function chgSrchType(obj,cd){
		$("#"+obj).empty();
		$("#"+obj).val(cd);
	}
	
	function acceptTotalCancel(){

		<%-- 무통장결제 || 핸드폰 결제에 결제달이 틀릴때--%>	
		<c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}"> 
		
			if($("#rfdBnkCd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.bank"/>');
	    		return;
	    	}
			
			if($("#rfdAcnthldrNmOd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountname"/>');
	    		return;
	    	}
	
	    	if($("#rfdBnkAcctNoOd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountno"/>');
	    		return;
	    	}
    	
			<%-- 환불계좌값 --%>
	    	$("input[name=rfdAcnthldrNm]").val($("#rfdAcnthldrNmOd").val());
	    	$("input[name=rfdBnkAcctNo]").val($("#rfdBnkAcctNoOd").val());
		</c:if>
		
		if(!confirm('<spring:message code="mypage.js.order.detail.totalcancel.confirm"/>')){
			return false;
		}
		
		
		var form = $("#totalCancelForm");
			$('.loading').show();
			$("#acceptBtn").attr("disabled", true);
			$("#cancelBtn").attr("disabled", true);
			$.ajax({
				type : "POST",
				url : "<c:url value='/mypage/claim/cancle/total/request.json'/>",
				data : $("#totalCancelForm").serialize(),
				success : function(data) {
					alert('<spring:message code="mypage.js.order.detail.totalcancel.complete"/>');
					//jsOrderInfo('${totalCancelInfo.ordNo}');
					if("${nmbrYn}"=="Y"){
						location.href ="<c:url value='/'/>";
					} else {
						location.href ="<c:url value='/mypage/claim/list'/>";
					}
				},
				error : function(e) {
					alert(e.responseText);
					jsOrderInfo('${totalCancelInfo.ordNo}');
					$("#acceptBtn").attr("disabled", false);
					$("#cancelBtn").attr("disabled", false);
					
				}
			});
		//}
	}

	
	/*
	 * 예금주, 환불계좌 입력체크
	 */
	function fn_checkInputTxt(obj){

		var ele_id = $(obj).attr("id");
		var ele_val = $(obj).val();
		
		if(ele_id == "rfdBnkAcctNoOd"){   <%--환불계좌번호 입력이 잘못되었습니다. (한글/영문/특수문자 입력시)--%>
			
			if(ele_val.match(/[A-Za-zㄱ-ㅎ|ㅏ-ㅣ|가-힣\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g)){
				alert('<spring:message code="mypage.js.order.detail.totalcancel.valid.accountno"/>');
				$("#"+ele_id).val("");
				return false;
			}
			
		}else{ <%--예금주 입력이 잘못되었습니다. (숫자/특수문자 입력시) --%>
			if(ele_val.match(/[0-9\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g)){
				alert('<spring:message code="mypage.js.order.detail.totalcancel.valid.accountname"/>');
				$("#"+ele_id).val("");
				return false;
			}
		}
		
	}
    

    function jsOrderInfo(ordNo) {

    	location.href ="<c:url value='/mypage/order/"+ordNo+"/view'/>";
    }
    
    
	$("#clmResnCont").keyup(function(){
		
		var maxlength = 1000;
		
		var textVal = $(this).val();
		var numChar = $(this).val().length;
		
		if (numChar >= maxlength) {
			$('#clmResnContErrMsg').show();
		} else {
			$('#clmResnContErrMsg').hide();
		}
		
		if(numChar > maxlength){
			
			$('#clmResnContLength').html(addComma(maxlength));
			
		    var str =textVal.substring(0, maxlength);
		    $("#clmResnCont").val(str);
		    
		    return false;
		   
		} else {
			$('#clmResnContLength').html(addComma(numChar));	
		}
	});
    
	function addComma(n) {
		 var reg = /(^[+-]?\d+)(\d{3})/;	 
		 n += '';
		 while (reg.test(n)) {
		  n = n.replace(reg, '$1' + ',' + '$2');
		 }
		 return n;
	}
	
	$(".numberOnly").keyup(function (e) {
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
    });
</script>