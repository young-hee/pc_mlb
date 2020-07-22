<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.delivery.js"></script>	
	
	<section class="layer-popup-cont" tabindex="0">
	<form name="gForm" id="gForm" action="<c:url value='/mypage/member/addDeliveryLocation'/>" method="post">
	<c:if test="${!empty deliveryLocationList[0].mbrDlvsp.dlvAdbukTurn}">
		<input type="hidden" id="dlvAdbukTurn" name="mbrDlvsp.dlvAdbukTurn" value="<c:out value='${deliveryLocationList[0].mbrDlvsp.dlvAdbukTurn}'/>"/>
	</c:if>
	<input type="hidden" id="${_csrf.parameterName}"        name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="telNationNo"        name="mbrDlvsp.telNationNo" value="<c:out value="${deliveryLocationList[0].mbrDlvsp.telNationNo}"/>"/>
	<input type="hidden" id="mobilNationNo"        name="mbrDlvsp.mobilNationNo" value="<c:out value="${deliveryLocationList[0].mbrDlvsp.mobilNationNo}"/>"/>
	<input type="hidden" id="dlvAddrSectCd"        name="mbrDlvsp.dlvAddrSectCd" value="<c:out value="${deliveryLocationList[0].mbrDlvsp.dlvAddrSectCd}"/>"/>
		<h2><spring:message code='mypage.member.delivery.ttl1' text="배송지 관리"/></h2>
		<div class="layer-cont scroll">
			
			<div class="deliveryModi-popWrap">
				<p class="text-required"><span class="required">*</span> <spring:message code='mypage.member.delivery.lbl.txt16' text="필수입력 항목"/></p>
				<div class="board-write">
					<table summary="배송지 관리">
						<caption><spring:message code='mypage.member.delivery.ttl1' text="배송지 관리"/></caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="labelName"><spring:message code='mypage.member.delivery.lbl.txt17' text="받는 분"/></label> <span class="required">*</span></th>
							<td>
								<input type="text" class="input-style02" style="width:145px;" id="labelName" name="mbrDlvsp.addrseNm" value="${deliveryLocationList[0].mbrDlvsp.addrseNm}" style="width:147px;" validate="required;" maxlength="10">
								<span class="error-msg"  id="error-lbName"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="mobilAreaNo"><spring:message code='mypage.member.delivery.lbl.txt18' text="휴대전화"/></label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" class="input-style02" style="width:60px;" id="mobilAreaNo" name="mbrDlvsp.mobilAreaNo" value="${deliveryLocationList[0].mbrDlvsp.mobilAreaNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="3" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" style="width:70px;" id="mobilTlofNo" name="mbrDlvsp.mobilTlofNo" value="${deliveryLocationList[0].mbrDlvsp.mobilTlofNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="4"/>
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" style="width:70px;" id="mobilTlofWthnNo" name="mbrDlvsp.mobilTlofWthnNo" value="${deliveryLocationList[0].mbrDlvsp.mobilTlofWthnNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="4"/>
									<input type="hidden" id="mobileNumber" validate="required;phone;" validateText="<spring:message code='member.join.lbl.mobile' />" value="${deliveryLocationList[0].mbrDlvsp.mobilAreaNo}${deliveryLocationList[0].mbrDlvsp.mobilTlofNo}${deliveryLocationList[0].mbrDlvsp.mobilTlofWthnNo}""/>
								</div>
								<span class="error-msg"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="telAreaNo"><spring:message code='mypage.member.delivery.lbl.txt19' text="전화번호"/></label></th>
							<td>
								<div class="inputcallBox">
									<input type="text"  class="input-style02" style="width:64px;" id="telAreaNo" name="mbrDlvsp.telAreaNo" value="${deliveryLocationList[0].mbrDlvsp.telAreaNo}" validate="digit;" validateText="<spring:message code='member.join.lbl.tel' />" maxlength="3"/>
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" style="width:70px;" id="telTlofNo" name="mbrDlvsp.telTlofNo" value="${deliveryLocationList[0].mbrDlvsp.telTlofNo}" validate="digit;" validateText="<spring:message code='member.join.lbl.tel' />" maxlength="4"/>
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" style="width:70px;" id="telTlofWthnNo" name="mbrDlvsp.telTlofWthnNo" value="${deliveryLocationList[0].mbrDlvsp.telTlofWthnNo}" validate="digit;" validateText="<spring:message code='member.join.lbl.tel' />" maxlength="4" />
									<input type="hidden" id="telNumber" validate="tel;" validateText="<spring:message code='member.join.lbl.tel' />" value="" />
								</div>
								<span class="error-msg"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="dlvspPostNo"><spring:message code='mypage.member.delivery.lbl.txt20' text="배송지주소"/></label> <span class="required">*</span></th>
							<td>
								<div>
									<input type="text" class="input-style02" style="width:calc(100% - 85px);" id="dlvspPostNo" name="mbrDlvsp.postNo" value="${deliveryLocationList[0].mbrDlvsp.postNo}" validate="required;" validateText="<spring:message code='mypage.member.delivery.lbl.txt20' />" readonly>
									<a href="#" class="btn-style04" onClick="openZipcodePopup(); return false;">주소찾기</a>
									<input type="text" class="input-style02 mtST10" style="width:100%;" id="dlvspBaseAddr" name="mbrDlvsp.baseAddr" value="${deliveryLocationList[0].mbrDlvsp.baseAddr}" validate="required;" validateText="<spring:message code='mypage.member.delivery.lbl.txt20' />" readonly>
									<input type="text" class="input-style02 mtST10" style="width:100%;" id="dlvspDetailAddr" name="mbrDlvsp.detailAddr" value="${deliveryLocationList[0].mbrDlvsp.detailAddr}" validate="required;" validateText="<spring:message code='mypage.member.delivery.lbl.txt20' />">									
								</div>
								<div>
								<span class="check-skin">
									<input type="checkbox" id="chkAddr" name="mbrDlvsp.baseDlvspYn" value="Y" <c:if test="${deliveryLocationList[0].mbrDlvsp.baseDlvspYn eq 1}">checked</c:if>>
									<span><spring:message code='mypage.member.delivery.lbl.txt23' text="기본배송지로 지정"/></span>
								</span>
								<label for="chkReply"><spring:message code='mypage.member.delivery.lbl.txt23' text="기본배송지로 지정"/></label>
								</div>
								<span class="error-msg"></span>
							</td>
						</tr>
					</table>
				</div>
			</div>	
			
			<div class="btnWrapBox">			
				<a href="javascript:;" class="btn d_layer_close"><spring:message code='mypage.member.delivery.btn.txt4' text="취소"/></a>
				<a href="#" class="btn fill" onclick="ajxSetDeliveryEditing(); return false;"><spring:message code='mypage.member.delivery.btn.txt5' text="확인"/></a>
			</div>
		
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code='mypage.member.delivery.btn.txt6' text="닫기"/></button>
		</div>
	</form>
	</section>
	

<!-- 로컬 JS 스크립트 선언 -->
<script type="text/javascript">
	
	$(document).ready(function(){
		$("[name='mbrDlvsp.mobilAreaNo'], [name='mbrDlvsp.mobilTlofNo'], [name='mbrDlvsp.mobilTlofWthnNo']").on('change', function () {
			$("#mobileNumber:hidden").val( $("[name='mbrDlvsp.mobilAreaNo']").val() + $("[name='mbrDlvsp.mobilTlofNo']").val() + $("[name='mbrDlvsp.mobilTlofWthnNo']").val() );
		});
		
		$("[name='mbrDlvsp.telAreaNo'], [name='mbrDlvsp.telTlofNo'], [name='mbrDlvsp.telTlofWthnNo']").on('change', function () {
			$("#telNumber:hidden").val( $("[name='mbrDlvsp.telAreaNo']").val() + $("[name='mbrDlvsp.telTlofNo']").val() + $("[name='mbrDlvsp.telTlofWthnNo']").val() );
		});
	});
	
	/***** Start 우편번호 *****/
	// 우편번호 팝업
	function openZipcodePopup() {
	    layerPopup.popupOpenNow("#layerPopupZipcode");
	    
	    var inHtml = "";
		
		inHtml = "<div class='addressFindInner' id='zipView1'> \
			<ul class='text-list02'> \
		<li><spring:message code='common.popup.zipcode.lbl.txt2'/></li> <%-- 도로명 + 건물번호 예)옥신타워 --%> \
		<li><spring:message code='common.popup.zipcode.lbl.txt3'/></li> <%-- 읍/면/동/리 + 지번 예) 도곡로 117 --%>  \
		<li><spring:message code='common.popup.zipcode.lbl.txt4'/></li> <%-- 건물명 예) 서오구 00아파트 --%> \
		<li><spring:message code='common.popup.zipcode.lbl.txt5'/></li> <%-- 사서함+사서함번호 예) 광화문 우체국 사서함 45 --%> \
	</ul> \
	<div class='board-list'> \
		<table summary='우편번호 찾기'> \
			<caption>우편번호 찾기</caption> \
			<colgroup> \
				<col width='70px'> \
				<col> \
			</colgroup> \
			<tbody>	 \
				<tr> \
					<td colspan='2' class='no-result'><spring:message code='common.popup.zipcode.lbl.txt6'/></td> <%-- 검색결과가 없습니다. --%> \
				</tr> \
			</tbody> \
		</table> \
	</div> \
</div>";
			
		$("#zipView1").remove();
		$("#zipView2").remove();	
		$('#zipSearchBefore').append(inHtml);
		$("#srchKeyword").val("");
	}
	
	// 우편번호 주소 세팅
	function setZipcode(zipcode, addr, type) {
		$("#dlvspPostNo").val(zipcode);
	    $("#dlvspBaseAddr").val(addr);
	    $("#dlvspDetailAddr").val(null);
	    $("#dlvAddrSectCd").val(type);
	    closeCommonLayerPopup("layerPopupZipcode");
	    layerPopup.popupOpenNow("#layerPopup25");
	}
	/***** End 우편번호 *****/
	
	/**
	 * 배송지 수정,저장 프로세스
	 */
	function ajxSetDeliveryEditing() {
		var callPage = "${callPage}";
		var form = $("#gForm");
		
		var confirmMsg = "<spring:message code='mypage.member.delivery.lbl.txt24' text='배송지를 수정하시겠습니까?' />";

		<c:if test="${empty deliveryLocationList[0].mbrDlvsp.dlvAdbukTurn}">
		confirmMsg = "<spring:message code='mypage.member.delivery.lbl.txt25' text='배송지를 등록하시겠습니까?' />";
		</c:if>
		
		if(Validator.validate(form)){

			if(confirm(confirmMsg) == false) {
				return false;
			}

			if( $("#dlvAddrSectCd").val() ==""){
				$("#dlvAddrSectCd").val("RD_ADDR");
			}
			
			var strParams = $('#gForm').serialize();

			gForm.submit();
 
		}
	}
	
</script>