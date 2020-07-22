
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<%-- TODO : validator..  --%>

<section class="layer-popup-cont" tabindex="0">

	<form name="gForm" id ="gForm" action="<c:url value='orderDeliveryLocationChange'/>" method="post">
		<input type="hidden" id="${_csrf.parameterName}"        name="${_csrf.parameterName}" 	value="${_csrf.token}"/>
		<input type="hidden" id="ordNo"                         name="ordNo" 					value="<c:out value="${orderDeliveryLocation.ordNo}"/>"/>
		<input type="hidden" id="dlvPcupspTurn"        			name="dlvPcupspTurn" 			value="<c:out value="${orderDeliveryLocation.dlvPcupspTurn}"/>"/>
		
		<%--
		<c:if test="${isMember && type != null && orderDeliveryLocation.dlvPcupspTurn != null}">
			<input type="hidden" id="dlvAdbukTurn"        			name="dlvAdbukTurn" 			value="<c:out value="${orderDeliveryLocation.dlvAdbukTurn}"/>"/>
		</c:if>
		 --%>
		
		<input type="hidden" id="addrSectCd"        			name="addrSectCd"				value="<c:out value="${orderDeliveryLocation.addrSectCd}"/>"/>
		<input type="hidden" id="addrseNationCd"        		name="addrseNationCd"			value="<c:out value="${orderDeliveryLocation.addrseNationCd}"/>"/>
		
		<%-- 픽업주문 일반배송 전환용 --%>
		<input type="hidden" id="type"        					name="type"						value="<c:out value="${type}"/>"/>
		<input type="hidden" id="ordTpCd"        				name="ordTpCd"					value="<c:out value="${ordTpCd}"/>"/>
		
		
		<%--주문자 정보와 동일용--%>
		<input type="hidden" id="mbrAddrNationCd" value="${userDetail.mbrAddrNationCd}"/>
	
	
		<h2><spring:message code="mypage.order.detail.btn.delivery.change" text="배송지 변경"/></h2>
		<div class="layer-cont ly-box">
			<div class="deliveryModi-popWrap">
				<p class="text-required"><span class="required">*</span> <spring:message code="mypage.order.lbl.required" text="필수입력 항목"/></p>
				<div class="board-write">
					<table>
						<caption><spring:message code="mypage.order.detail.btn.delivery.change" text="배송지 변경"/></caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="addrseNm"><spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/></label> <span class="required">*</span></th>
							<td>
								<input type="text" id="addrseNm" name="addrseNm" class="input-style02" value="<c:out value='${orderDeliveryLocation.addrseNm}'/>" style="width:145px;">
								<c:if test="${isMember}">
									<a href="#layerPopupAddress" class="btn-style04 d_layer_open" onclick="getDlvspList('member');"><spring:message code="mypage.order.detail.lbl.delivery.select" text="배송지선택"/></a>
									<span class="check-skin">
										<input type="checkbox" id="mem_same" data-addrse-nm="${userDetail.mbrNm}"
																					data-addrse-nation-cd="${userDetail.mbrAddrNationCd}"
	                                   		                                        data-addr-sect-cd="${userDetail.mbrAddrSectCd}"
		                                                                            data-addrse-post-no="${userDetail.mbrPostNo}"
		                                                                            data-addrse-base-addr="${userDetail.mbrBaseAddr}"
		                                                                            data-addrse-detail-addr="${userDetail.mbrDetailAddr}"
																				    data-addrse-mobil-nation-no="${userDetail.mobilNationNo}"
																				    data-addrse-tel-nation-no="${userDetail.telNationNo}"
																				    data-addrse-tel-area-no="${userDetail.telAreaNo}"
																				    data-addrse-tel-tlof-no="${userDetail.telTlofNo}"
																				    data-addrse-tel-tlof-wthn-no="${userDetail.telTlofWthnNo}"
																				    data-addrse-mobil-area-no="${userDetail.mobilAreaNo}"
																				    data-addrse-mobil-tlof-no="${userDetail.mobilTlofNo}"
																				    data-addrse-mobil-tlof-wthn-no="${userDetail.mobilTlofWthnNo}" 
																				    />
										<span><spring:message code="mypage.order.btn.select" text="선택"/></span>
									</span>
									<label for="mem_same"><spring:message code="mypage.order.detail.lbl.delivery.memsame" text="회원 정보와 동일"/></label>
									<input type="hidden" id="thisDlvAddr" value="" 	data-addrse-nm="${orderDeliveryLocation.addrseNm}"
																					data-addrse-nation-cd="${orderDeliveryLocation.addrseNationCd}"
	                       		                                                   	data-addr-sect-cd="${orderDeliveryLocation.addrSectCd}"
	                       		                                                   	data-addrse-post-no="${orderDeliveryLocation.addrsePostNo}"
	                       		                                                   	data-addrse-base-addr="${orderDeliveryLocation.addrseBaseAddr}"
	                       		                                                   	data-addrse-detail-addr="${orderDeliveryLocation.addrseDetailAddr}"
																					data-addrse-mobil-nation-no="${orderDeliveryLocation.addrseMobilNationNo}"
																					data-addrse-tel-nation-no="${orderDeliveryLocation.addrseTelNationNo}"
																					data-addrse-tel-area-no="${orderDeliveryLocation.addrseTelAreaNo}"
																					data-addrse-tel-tlof-no="${orderDeliveryLocation.addrseTelTlofNo}"
																					data-addrse-tel-tlof-wthn-no="${orderDeliveryLocation.addrseTelTlofWthnNo}"
																					data-addrse-mobil-area-no="${orderDeliveryLocation.addrseMobilAreaNo}"
																					data-addrse-mobil-tlof-no="${orderDeliveryLocation.addrseMobilTlofNo}"
																					data-addrse-mobil-tlof-wthn-no="${orderDeliveryLocation.addrseMobilTlofWthnNo}" 
																					/>
								</c:if>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="labelContact02"><spring:message code="mypage.order.detail.lbl.delivery.mobile" text="휴대전화"/></label> <span class="required">*</span></th><!-- 2018-05-24 -->
							<td>
								<div class="inputcallBox">
									<input type="hidden" id="addrseMobilNationNo" name="addrseMobilNationNo" value="<c:out value="${orderDeliveryLocation.addrseMobilNationNo}"/>"/>
									<input type="text" class="input-style02" id="addrseMobilAreaNo" name="addrseMobilAreaNo" onchange="validateNumber(this);return false;" maxlength="3" value="<c:out value="${orderDeliveryLocation.addrseMobilAreaNo}"/>" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" id="addrseMobilTlofNo" name="addrseMobilTlofNo" onchange="validateNumber(this);return false;" maxlength="4" value="<c:out value="${orderDeliveryLocation.addrseMobilTlofNo}"/>" style="width:72px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" id="addrseMobilTlofWthnNo" name="addrseMobilTlofWthnNo" onchange="validateNumber(this);return false;" maxlength="4" value="<c:out value="${orderDeliveryLocation.addrseMobilTlofWthnNo}"/>" style="width:72px;" />
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="labelContact"><spring:message code="mypage.order.detail.lbl.delivery.phoneno" text="전화번호"/></label></th>
							<td>
								<div class="inputcallBox">
									<input type="hidden" id="addrseTelNationNo" name="addrseTelNationNo" value="<c:out value="${orderDeliveryLocation.addrseTelNationNo}"/>"/>
									<input type="text" class="input-style02" id="addrseTelAreaNo" name="addrseTelAreaNo" onchange="validateNumber(this);return false;" maxlength="3" value="<c:out value="${orderDeliveryLocation.addrseTelAreaNo}"/>" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" id="addrseTelTlofNo" name="addrseTelTlofNo" onchange="validateNumber(this);return false;" maxlength="4" value="<c:out value="${orderDeliveryLocation.addrseTelTlofNo}"/>" style="width:72px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" id="addrseTelTlofWthnNo" name="addrseTelTlofWthnNo" onchange="validateNumber(this);return false;" maxlength="4" value="<c:out value="${orderDeliveryLocation.addrseTelTlofWthnNo}"/>" style="width:72px;" />
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="addrsePostNo"><spring:message code="mypage.order.detail.lbl.delivery.deliveryaddress" text="배송지주소"/></label> <span class="required">*</span></th>
							<td>
								<div>
									<input type="text" class="input-style02" style="width:calc(100% - 85px);" name="addrsePostNo" id="addrsePostNo" readonly value="${orderDeliveryLocation.addrsePostNo }">
									<a href="#" class="btn-style04" onclick="openZipcodePopup();return false;"><spring:message code="mypage.order.detail.lbl.delivery.postno" text="우편번호"/></a>
									<input type="text" class="input-style02 mtST10" style="width:100%;" name="addrseBaseAddr" id="addrseBaseAddr" value="<c:out value="${orderDeliveryLocation.addrseBaseAddr}"/>">
									<input type="text" class="input-style02 mtST10" style="width:100%;" name="addrseDetailAddr" id="addrseDetailAddr" value="<c:out value="${orderDeliveryLocation.addrseDetailAddr}"/>">
								</div>
								<c:if test="${isMember}">
									<div>
										<span class="check-skin">
											<input type="checkbox" name="defaultDelv" id="defaultDelv" value="N">
											<span><spring:message code="mypage.order.detail.lbl.delivery.setdefault" text="기본배송지로 지정"/></span>
										</span>
										<label for="defaultDelv"><spring:message code="mypage.order.detail.lbl.delivery.setdefault" text="기본배송지로 지정"/></label>
		
										<span class="check-skin">
											<input type="checkbox" name="addMbrDlvspCheck" id="addMbrDlvspCheck" value="N">
											<span><spring:message code="mypage.order.detail.lbl.delivery.addmbrdelivery" text="배송지관리 목록에 추가"/></span>
										</span>
										<label for="addMbrDlvspCheck"><spring:message code="mypage.order.detail.lbl.delivery.addmbrdelivery" text="배송지관리 목록에 추가"/></label>
									</div>
								</c:if>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="labelComment"><spring:message code="mypage.order.detail.lbl.delivery.request" text="배송요청사항"/></label></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select" style="width:100%;">
									<button type="button" id="labelComment" class="d_select_sel" style="width:100%;"><span id="memo_title"><spring:message code="mypage.order.detail.lbl.delivery.personally" text="직접입력"/></span></button>
									<ul>
										<li>
											<a href="#" onclick="setDlvMemo('');return false;"><spring:message code="mypage.order.detail.lbl.delivery.personally" text="직접입력"/></a>
											<a href="#" onclick="setDlvMemo('<spring:message code="mypage.order.detail.lbl.delivery.dlvMemo1" text="부재 시 경비실에 맡겨주세요."/>');return false;"><spring:message code="mypage.order.detail.lbl.delivery.dlvMemo1" text="부재 시 경비실에 맡겨주세요."/></a>
											<a href="#" onclick="setDlvMemo('<spring:message code="mypage.order.detail.lbl.delivery.dlvMemo2" text="부재 시 문 앞에 놓아주세요."/>');return false;"><spring:message code="mypage.order.detail.lbl.delivery.dlvMemo2" text="부재 시 문 앞에 놓아주세요."/></a>
											<a href="#" onclick="setDlvMemo('<spring:message code="mypage.order.detail.lbl.delivery.dlvMemo3" text="배송 전에 연락주세요."/>');return false;"><spring:message code="mypage.order.detail.lbl.delivery.dlvMemo3" text="배송 전에 연락주세요."/></a>
											<a href="#" onclick="setDlvMemo('<spring:message code="mypage.order.detail.lbl.delivery.dlvMemo6" text="무인 택배함에 보관해주세요."/>');return false;"><spring:message code="mypage.order.detail.lbl.delivery.dlvMemo6" text="무인 택배함에 보관해주세요."/></a>
											<c:if test="${not empty dlvMemoList }">
											<c:forEach var="dlvMemoInfo" items="${dlvMemoList }">
												<a href="#" onclick="setDlvMemo('${dlvMemoInfo.dlvMemo }');return false;">(<spring:message code="mypage.order.detail.lbl.delivery.latestmessage" text="최근메시지"/>) ${dlvMemoInfo.dlvMemo }</a>
											</c:forEach>
											</c:if>
										</li>
									</ul>
								</div>
								<!-- //select -->
								<input type="text" class="input-style02 mtST10" value="" style="width:100%;" name="dlvMemo" id="dlvMemo" value="${orderDeliveryLocation.dlvMemo}">
							</td>
						</tr>
					</table>
					<!-- //2018-05-16 -->
				</div>
			</div>
			<div class="btnWrapBox">
				<a href="#" class="btn d_layer_close"><spring:message code="mypage.order.btn.cancel" text="취소"/></a>
				<a href="#" class="btn fill" onclick="orderDeliveryLocationChange();return false;"><spring:message code="mypage.order.detail.btn.delivery.change" text="배송지 변경"/></a>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" id="closelayerPopupChangeAddress"><spring:message code="mypage.order.btn.close" text="닫기"/></button>
		</div>
	</form>
</section>

<script>

	$(document).ready(function() {
		
		unlockWheel();
		
		if("${orderDeliveryLocation.addrseTelNationNo}"==null || "${orderDeliveryLocation.addrseTelNationNo}"==""){
			$("#addrseTelNationNo").val("82");
		}
		
		if("${orderDeliveryLocation.addrseMobilNationNo}"==null || "${orderDeliveryLocation.addrseMobilNationNo}"==""){
			$("#addrseMobilNationNo").val("82");
		}

	});
	
	
	$('#defaultDelv').click(function(){
	
		if ($('#defaultDelv').is(':checked')) {
			$('#defaultDelv').prop('checked', true);
			$('#defaultDelv').val("Y");
		}else{
			$('#defaultDelv').prop('checked', false);
			$('#defaultDelv').val("N");
		}
	});
	
	$('#addMbrDlvspCheck').click(function(){
	
		if ($('#addMbrDlvspCheck').is(':checked')) {
			$('#addMbrDlvspCheck').val("Y");
			$('#addMbrDlvspCheck').prop('checked', true);
		}else{
			$('#addMbrDlvspCheck').val("N");
			$('#addMbrDlvspCheck').prop('checked', false);
		}
	});
	
	
	$('#mem_same').on('click', function(event){
		
	    if($(this).is(':checked')){
	    		
			$('#addrseNm').val($(this).data('addrseNm'));
			$('#addrseNationCd').val($(this).data('addrseNationCd'));

			$('#addrSectCd').val($(this).data('addrSectCd'));
			$('#addrsePostNo').val($(this).data('addrsePostNo'));
			$('#addrseBaseAddr').val($(this).data('addrseBaseAddr'));
			$('#addrseDetailAddr').val($(this).data('addrseDetailAddr'));

			$('#addrseMobilNationNo').val($(this).data('addrseMobilNationNo'));
			$('#addrseTelNationNo').val($(this).data('addrseTelNationNo'));

			$('#addrseTelAreaNo').val($(this).data('addrseTelAreaNo'));
			$('#addrseTelTlofNo').val($(this).data('addrseTelTlofNo'));
			$('#addrseTelTlofWthnNo').val($(this).data('addrseTelTlofWthnNo'));
			
			$('#addrseMobilAreaNo').val($(this).data('addrseMobilAreaNo'));
			$('#addrseMobilTlofNo').val($(this).data('addrseMobilTlofNo'));
			$('#addrseMobilTlofWthnNo').val($(this).data('addrseMobilTlofWthnNo'));

	    } else {
	    	
			$('#addrseNm').val($('#thisDlvAddr').data('addrseNm'));
			$('#addrseNationCd').val($('#thisDlvAddr').data('addrseNationCd'));
	
			$('#addrSectCd').val($('#thisDlvAddr').data('addrSectCd'));
			$('#addrsePostNo').val($('#thisDlvAddr').data('addrsePostNo'));
			$('#addrseBaseAddr').val($('#thisDlvAddr').data('addrseBaseAddr'));
			$('#addrseDetailAddr').val($('#thisDlvAddr').data('addrseDetailAddr'));
	
			$('#addrseTelNationNo').val($('#thisDlvAddr').data('addrseTelNationNo'));
			$('#addrseMobilNationNo').val($('#thisDlvAddr').data('addrseMobilNationNo'));
	
	
			$('#addrseTelAreaNo').val($('#thisDlvAddr').data('addrseTelAreaNo'));
			$('#addrseTelTlofNo').val($('#thisDlvAddr').data('addrseTelTlofNo'));
			$('#addrseTelTlofWthnNo').val($('#thisDlvAddr').data('addrseTelTlofWthnNo'));
			
			$('#addrseMobilAreaNo').val($('#thisDlvAddr').data('addrseMobilAreaNo'));
			$('#addrseMobilTlofNo').val($('#thisDlvAddr').data('addrseMobilTlofNo'));
			$('#addrseMobilTlofWthnNo').val($('#thisDlvAddr').data('addrseMobilTlofWthnNo'));
	    }
	});
	

	function confirmDelivery(obj) {
		$('#addrseNm').val($(obj).data('addrseNm'));

		$('#addrSectCd').val($(obj).data('addrSectCd'));
		$('#addrsePostNo').val($(obj).data('addrsePostNo'));
		$('#addrseBaseAddr').val($(obj).data('addrseBaseAddr'));
		$('#addrseDetailAddr').val($(obj).data('addrseDetailAddr'));

		$('#addrseMobilNationNo').val($(obj).data('addrseMobilNationNo'));
		$('#addrseTelNationNo').val($(obj).data('addrseTelNationNo'));

		$('#addrseTelAreaNo').val($(obj).data('addrseTelAreaNo'));
		$('#addrseMobilTlofNo').val($(obj).data('addrseMobilTlofNo'));
		$('#addrseMobilTlofWthnNo').val($(obj).data('addrseMobilTlofWthnNo'));
		
		$('#addrseMobilAreaNo').val($(obj).data('addrseMobilAreaNo'));
		$('#addrseMobilTlofNo').val($(obj).data('addrseMobilTlofNo'));
		$('#addrseMobilTlofWthnNo').val($(obj).data('addrseMobilTlofWthnNo'));
		
		changePopup("layerPopupChangeAddress");
	}
	
	
	/**
	 * 배송지 수정,저장 프로세스
	 */
	function orderDeliveryLocationChange() {
		
		var form = $("#gForm");
	
	    	var strParams = $('form[name=gForm]').serialize();
	    	
	    	if($('#addrseNm').val() === '') {
				alert("받는분을 입력해주세요.");
				return false;
			}
			
			if($('#addrsePostNo').val() === '' || $('#addrseBaseAddr').val() === '' || $('#addrseDetailAddr').val() === '') {
				alert("배송지주소를 입력해주세요.");
				return false;
			}
			
			if($('#addrseMobilAreaNo').val() === '' || $('#addrseMobilTlofNo').val() === '' || $('#addrseMobilTlofWthnNo').val() === '') {
				alert("휴대전화를 입력해주세요.");
				return false;
			}
	
			<%-- 반품상품 수거지 변경 --%>
			<c:if test="${type eq 'returnChange'}">
	
			$('input[name="ord.ordNo"]').val('${orderDeliveryLocation.ordNo}');
			$('input[name="ord.ordTpCd"]').val('${ordTpCd}');
			$('input[name="ord.cstmrNm"]').val($("#addrseNm").val());
			$('input[name="type"]').val('returnChange');
	
			if ( typeof $("#dlvAdbukTurn").val() != "undefined" ) {
				$('input[name="lgsDlvspList[0].dlvAdbukTurn"]').val($("#dlvAdbukTurn").val());
			}
	
			$('input[name="lgsDlvspList[0].addrseNm"]').val($("#addrseNm").val());
			$('input[name="lgsDlvspList[0].addrsePostNo"]').val($("#addrsePostNo").val());
			$('input[name="lgsDlvspList[0].addrseBaseAddr"]').val($("#addrseBaseAddr").val());
			$('input[name="lgsDlvspList[0].addrseDetailAddr"]').val($("#addrseDetailAddr").val());
	
			$('input[name="lgsDlvspList[0].addrseMobilNationNo"]').val($("#addrseMobilNationNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilAreaNo"]').val($("#addrseMobilAreaNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilTlofNo"]').val($("#addrseMobilTlofNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilTlofWthnNo"]').val($("#addrseMobilTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[0].addrseTelNationNo"]').val($("#addrseTelNationNo").val());
			$('input[name="lgsDlvspList[0].addrseTelAreaNo"]').val($("#addrseTelAreaNo").val());
			$('input[name="lgsDlvspList[0].addrseTelTlofNo"]').val($("#addrseTelTlofNo").val());
			$('input[name="lgsDlvspList[0].addrseTelTlofWthnNo"]').val($("#addrseTelTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[0].dlvMemo"]').val($("#dlvMemo").val());
			$('input[name="lgsDlvspList[0].addrseNationCd"]').val($("#addrseNationCd").val());
			$('input[name="lgsDlvspList[0].addrSectCd"]').val($("#addrSectCd").val());
	
			$('input[name="addMbrDlvspCheck"]').val($("#addMbrDlvspCheck").val());
			$('input[name="defaultDelv"]').val($("#defaultDelv").val());
	
			$('input[name="lgsDlvspList[0].dlvspChgYn"]').val("Y");
			
			var txtChangePickupPlace = "";
			if($("#addrseNm").val() != "") {
				txtChangePickupPlace += $("#addrseNm").val();
				
			}	
			if($("#addrseBaseAddr").val() != '') {
				txtChangePickupPlace += "|"
									+ $("#addrseBaseAddr").val()+"\t"+$("#addrseDetailAddr").val();				
			}
			if($("#addrseMobilAreaNo").val() != '') {
				txtChangePickupPlace += "|"
									+ $("#addrseMobilAreaNo").val()
									+ "-"
									+ $("#addrseMobilTlofNo").val()
									+ "-"
									+ $("#addrseMobilTlofWthnNo").val();
			}
			if($("#addrseTelAreaNo").val() != "") {
				txtChangePickupPlace += "|"
									+ $("#addrseTelAreaNo").val()
									+ "-"
									+ $("#addrseTelTlofNo").val()
									+ "-"
									+ $("#addrseTelTlofWthnNo").val();
			}
			
			
			$("#changePickupPlace").val(txtChangePickupPlace);

			
			$('#closelayerPopupChangeAddress').click();	// 레이어 닫기
			inputCheck();
	
			</c:if>
	
			<%-- 교환신청 수거지 변경 --%>
			<c:if test="${type eq 'pkupExchange'}">
	
			$('input[name="ord.ordNo"]').val('${orderDeliveryLocation.ordNo}');
			$('input[name="ord.ordTpCd"]').val('${ordTpCd}');
			$('input[name="ord.cstmrNm"]').val($("#addrseNm").val());
			$('input[name="type"]').val('pkupExchange');
	
			$('input[name="lgsDlvspList[0].addrseNm"]').val($("#addrseNm").val());
			$('input[name="lgsDlvspList[0].addrsePostNo"]').val($("#addrsePostNo").val());
			$('input[name="lgsDlvspList[0].addrseBaseAddr"]').val($("#addrseBaseAddr").val());
			$('input[name="lgsDlvspList[0].addrseDetailAddr"]').val($("#addrseDetailAddr").val());
	
			$('input[name="lgsDlvspList[0].addrseMobilNationNo"]').val($("#addrseMobilNationNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilAreaNo"]').val($("#addrseMobilAreaNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilTlofNo"]').val($("#addrseMobilTlofNo").val());
			$('input[name="lgsDlvspList[0].addrseMobilTlofWthnNo"]').val($("#addrseMobilTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[0].addrseTelNationNo"]').val($("#addrseTelNationNo").val());
			$('input[name="lgsDlvspList[0].addrseTelAreaNo"]').val($("#addrseTelAreaNo").val());
			$('input[name="lgsDlvspList[0].addrseTelTlofNo"]').val($("#addrseTelTlofNo").val());
			$('input[name="lgsDlvspList[0].addrseTelTlofWthnNo"]').val($("#addrseTelTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[0].dlvMemo"]').val($("#dlvMemo").val());
			$('input[name="lgsDlvspList[0].addrseNationCd"]').val($("#addrseNationCd").val());
			$('input[name="lgsDlvspList[0].addrSectCd"]').val($("#addrSectCd").val());
	
			$('input[name="lgsDlvspList[0].dlvspChgYn"]').val("Y");
	
			$('input[name="addMbrDlvspCheck"]').val($("#addMbrDlvspCheck").val());
			$('input[name="defaultDelv"]').val($("#defaultDelv").val());
	
			$('input[name="lgsDlvspList[1].addrseNm"]').val($("#addrseNm").val());
			$('input[name="lgsDlvspList[1].addrsePostNo"]').val($("#addrsePostNo").val());
			$('input[name="lgsDlvspList[1].addrseBaseAddr"]').val($("#addrseBaseAddr").val());
			$('input[name="lgsDlvspList[1].addrseDetailAddr"]').val($("#addrseDetailAddr").val());
	
			$('input[name="lgsDlvspList[1].addrseMobilNationNo"]').val($("#addrseMobilNationNo").val());
			$('input[name="lgsDlvspList[1].addrseMobilAreaNo"]').val($("#addrseMobilAreaNo").val());
			$('input[name="lgsDlvspList[1].addrseMobilTlofNo"]').val($("#addrseMobilTlofNo").val());
			$('input[name="lgsDlvspList[1].addrseMobilTlofWthnNo"]').val($("#addrseMobilTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[1].addrseTelNationNo"]').val($("#addrseTelNationNo").val());
			$('input[name="lgsDlvspList[1].addrseTelAreaNo"]').val($("#addrseTelAreaNo").val());
			$('input[name="lgsDlvspList[1].addrseTelTlofNo"]').val($("#addrseTelTlofNo").val());
			$('input[name="lgsDlvspList[1].addrseTelTlofWthnNo"]').val($("#addrseTelTlofWthnNo").val());
	
			$('input[name="lgsDlvspList[1].dlvMemo"]').val($("#dlvMemo").val());
			$('input[name="lgsDlvspList[1].addrseNationCd"]').val($("#addrseNationCd").val());
			$('input[name="lgsDlvspList[1].addrSectCd"]').val($("#addrSectCd").val());
	
			$('input[name="lgsDlvspList[1].dlvspChgYn"]').val("Y");
	
			var txtChangePickupPlace = "";
			if($("#addrseNm").val() != "") {
				txtChangePickupPlace += $("#addrseNm").val();
				
			}	
			if($("#addrseBaseAddr").val() != '') {
				txtChangePickupPlace += "|"
									+ $("#addrseBaseAddr").val()+"\t"+$("#addrseDetailAddr").val();				
			}
			if($("#addrseMobilAreaNo").val() != '') {
				txtChangePickupPlace += "|"
									+ $("#addrseMobilAreaNo").val()
									+ "-"
									+ $("#addrseMobilTlofNo").val()
									+ "-"
									+ $("#addrseMobilTlofWthnNo").val();
			}
			if($("#addrseTelAreaNo").val() != "") {
				txtChangePickupPlace += "|"
									+ $("#addrseTelAreaNo").val()
									+ "-"
									+ $("#addrseTelTlofNo").val()
									+ "-"
									+ $("#addrseTelTlofWthnNo").val();
			}
			
			$("#changePickupPlace").val(txtChangePickupPlace);
	
			$('#closelayerPopupChangeAddress').click();	// 레이어 닫기
	
			</c:if>
	
	    	<%-- 그외 일반 배송지 변경--%>
			<c:if test="${type ne 'pkupReturnChange' && type ne 'pkupExchange' && type ne 'returnChange'}">
			
	    	$.ajax({
				type : "POST",
				url : '<c:url value="/mypage/order/delivery/update.json"/>',
				data : strParams,
				beforeSend: function (request)
	            {
	              var csrfToken = $("meta[name='_csrf']").attr("content");
	              var csrfName = $("meta[name='_csrf_header']").attr("content");
	              request.setRequestHeader(csrfName, csrfToken);
	            },
				success : function(args) {
					alert("배송지가 변경되었습니다.");
					jsOrderInfo('<c:out value="${orderDeliveryLocation.ordNo}"/>');
				},
				error : function(e) {
					alert(e.responseText);
				}
			});
	    	</c:if>
		
	}
	
	
	function jsOrderInfo(ordNo) {
		location.href ="/mypage/order/"+ordNo+"/view";
	}

	function openZipcodePopup(){
		changePopup("layerPopupZipcode");
	}
	
	 
	function setZipcode(ordno, addr, type){
		
		$('#addrSectCd').val(type);
		$('#addrsePostNo').val(ordno);
		$('#addrseBaseAddr').val(addr);
		
		changePopup("layerPopupChangeAddress");
	}
	 
	function zipcodePop(zipcodeId, baseAddrId , detailAddrId){
		var params = new Object();
		params.zipcodeId = zipcodeId;
		params.baseAddrId = baseAddrId;
		params.detailAddrId = detailAddrId;
		openCommonPopup('/public/member/searchZipcode.popup' , params,626,680, 'zipcode');
	}

	function getDlvspList(delvFlag) {
		
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'delvFlag' : delvFlag, 'pagePerRow' : '5'};
		
	    $("#layerPopupAddress").load("<c:url value='/mypage/order/delivery/list'/>", strParams);
	}
	
	function changePopup(id) {
		layerPopup.popupOpenNow("#" + id);
		unlockWheel();
	}
	
	function unlockWheel() {
		$('#wrap').off('scroll touchmove mousewheel');
	}
	
	function setDlvMemo(val){
		
		if(val==''){
			$("#memo_title").html('<spring:message code="mypage.order.detail.lbl.delivery.personally" text="직접입력"/>');
			$("#dlvMemo").prop("readonly","");
		}else{
			$("#memo_title").html(val);
			$("#dlvMemo").prop("readonly","readonly");
		}
		
		$("#dlvMemo").val(val);
	}

	function validateNumber(element) {

		var reg = /[^0-9]/g;
		var rst = reg.test(element.value);
		if (rst) {
			alert("숫자만 입력해주세요.");
			$("#"+element.id).focus();
		}
	}
</script>