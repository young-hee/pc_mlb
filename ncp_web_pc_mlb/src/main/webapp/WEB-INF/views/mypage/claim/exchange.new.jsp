<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">
			<form name="frmClaimInfo"   id="frmClaimInfo"   method="post"       action="/" >
				<input type="hidden"    id="${_csrf.parameterName}"        name="${_csrf.parameterName}"   value="${_csrf.token}"/>
				<input type="hidden"    id="ordNo"          name="ordNo"        	value="${orderInfo.ordNo }" />
			    <input type="hidden"    id="clmNo"          name="clmExtend.clmNo"  value="" />
			    <input type="hidden"    id="mbrNo"          name="mbrNo"        	value="${mbrNo}" />
			    <input type="hidden"   	id="ordGodTurnStr"  name="ordGodTurnStr"  	value="${ordGodTurnStr}" />
			    <input type="hidden"    id="mbrTp"          name="mbrTp"        	value="${mbrTp}" />
			    <input type="hidden"    id="dlvMnCd"        name="dlvMnCd"        	value="APPN_HDRY" />       <%-- 회수방법 --%>
			    <input type="hidden"    id="clmStatCd"      name="clmStatCd"      	value="REQST" />
			    <input type="hidden"    id="clmTpCd"        name="clmTpCd"      	value="GNRL_EXCHG" />
			    <input type="hidden"    id="dlivyDrctTpCd"  name="dlivyDrctTpCd"    value="EXCHG" />    
			    <input type="hidden" 	id="role"          	name="role"        	        	value="F" />
			    <input type="hidden"   	id="pkupClmYn"      name="clmExtend.pkupClmYn"  	value="N" />
			    <input type="hidden"   	id="pgTrnsmisTgtYn" name="clmExtend.pgTrnsmisTgtYn" value="Y" />	    
			    <input type="hidden"    id="dlvComTrnsmisTgtYn" name="dlvComTrnsmisTgtYn"   value="Y" /> <%-- 택배사 수거지시여부 --%>	    
			    <input type="hidden"    id="adminTpCd"      name="adminTpCd"           		value="" />
			    <input type="hidden"    id="regtrShopId"  	name="regtrShopId"          	value="" />
			
				<!-- 매장 픽업 수거지 변경을 위한 히든-->
				<input type="hidden" name="ord.ordNo"/>
				<input type="hidden" name="ord.ordTpCd"/>
				<input type="hidden" name="ord.cstmrNm"/>
				<input type="hidden" name="type"/>
			
			
				<h2 class="title01"><spring:message code="mypage.order.exchange.ttl" text="상품 교환신청"/></h2>
				
				<%@ include file="../include/lnb.jspf" %>
				
				<main class="contents" id="contents">
					
					<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/> 
					
					<!-- 취소상품 선택  -->
					<div class="orderInfoCon">			
						
						
						<!-- order Result -->
						<div class="odSearchResult odPdModify">				
						
							<!--  order Result List -->
							<div class="odResulCon">								
												
								<!--  order LIst  -->
								<div class="odResulBox">							
							
									<!-- 주문정보 -->
									<div class="orderNb">
										<span><ncp:code code='${orderInfo.ordTpCd}'/></span>
		                              	<span><em><spring:message code="mypage.order.list.lbl.orderdate" text="주문일"/></em> ${orderInfo.ordDt }</span>
		                              	<span><em><spring:message code="mypage.order.list.lbl.orderno" text="주문번호"/></em> ${orderInfo.ordNo }</span>
									</div>	
									<!-- //주문정보 -->	
									
									<c:set var="defaultAddrseNm" value="${dlvspList[0].addrseNm}" />
									<c:set var="defaultAddrseNationCd" value="${dlvspList[0].addrseNationCd}" />
									<c:set var="defaultAddrSectCd" value="${dlvspList[0].addrSectCd}" />
									<c:set var="defaultAddrsePostNo" value="${dlvspList[0].addrsePostNo}" />
									<c:set var="defaultAddrseBaseAddr" value="${dlvspList[0].addrseBaseAddr}" />
									<c:set var="defaultAddrseDetailAddr" value="${dlvspList[0].addrseDetailAddr}" />
									<c:set var="defaultAddrseMobilNationNo" value="${dlvspList[0].addrseMobilNationNo}" />
									<c:set var="defaultAddrseMobilAreaNo" value="${dlvspList[0].addrseMobilAreaNo}" />
									<c:set var="defaultAddrseMobilTlofNo" value="${dlvspList[0].addrseMobilTlofNo}" />
									<c:set var="defaultAddrseMobilTlofWthnNo" value="${dlvspList[0].addrseMobilTlofWthnNo}" />
									<c:set var="defaultAddrseTelNationNo" value="${dlvspList[0].addrseTelNationNo}" />
									<c:set var="defaultAddrseTelAreaNo" value="${dlvspList[0].addrseTelAreaNo}" />
									<c:set var="defaultAddrseTelTlofNo" value="${dlvspList[0].addrseTelTlofNo}" />
									<c:set var="defaultAddrseTelTlofWthnNo" value="${dlvspList[0].addrseTelTlofWthnNo}" />
									
									<c:if test="${orderInfo.ordTpCd eq 'SHOP_PKUP_ORD'}">
										<c:set var="defaultAddrseNm" value="${userDetail.mbrNm}" />
										<c:set var="defaultAddrseNationCd" value="${userDetail.mbrAddrNationCd}" />
										<c:set var="defaultAddrSectCd" value="${userDetail.mbrAddrSectCd}" />
										<c:set var="defaultAddrsePostNo" value="${userDetail.mbrPostNo}" />
										<c:set var="defaultAddrseBaseAddr" value="${userDetail.mbrBaseAddr}" />
										<c:set var="defaultAddrseDetailAddr" value="${userDetail.mbrDetailAddr}" />
										<c:set var="defaultAddrseMobilNationNo" value="${userDetail.mobilNationNo}" />
										<c:set var="defaultAddrseMobilAreaNo" value="${userDetail.mobilAreaNo}" />
										<c:set var="defaultAddrseMobilTlofNo" value="${userDetail.mobilTlofNo}" />
										<c:set var="defaultAddrseMobilTlofWthnNo" value="${userDetail.mobilTlofWthnNo}" />
										<c:set var="defaultAddrseTelNationNo" value="${userDetail.telNationNo}" />
										<c:set var="defaultAddrseTelAreaNo" value="${userDetail.telAreaNo}" />
										<c:set var="defaultAddrseTelTlofNo" value="${userDetail.telTlofNo}" />
										<c:set var="defaultAddrseTelTlofWthnNo" value="${userDetail.telTlofWthnNo}" />
									</c:if>
									
									<c:set var="defaultAddr" value="" />
									<c:if test="${not empty defaultAddrseBaseAddr}">
										<!-- 배송지 -->
										<div class="orderAdd odAddBox">									
											<!-- <strong class="odAddTit">[배송지]</strong> 2018.12.06 삭제 -->										
											<dl>
												<dt><spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/></dt>
												<dd><c:out value='${defaultAddrseNm}'/></dd>
											</dl>
											<dl>
												<dt><spring:message code="mypage.order.detail.lbl.delivery.address" text="주소"/></dt>
												<dd><c:out value='${defaultAddrseBaseAddr}'/> <c:out value='${defaultAddrseDetailAddr}'/></dd>
											</dl>
											<dl>
												<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
												<dd>
													<c:if test="${not empty defaultAddrseMobilAreaNo and not empty defaultAddrseMobilTlofNo and not empty defaultAddrseMobilTlofWthnNo}">
														<c:out value='${defaultAddrseMobilAreaNo}'/>-<c:out value='${defaultAddrseMobilTlofNo}'/>-<c:out value='${defaultAddrseMobilTlofWthnNo}'/>
													</c:if>
													<c:if test="${not empty defaultAddrseTelAreaNo and not empty defaultAddrseTelTlofNo and not empty defaultAddrseTelTlofWthnNo}">
														 | <c:out value='${defaultAddrseTelAreaNo}'/>-<c:out value='${defaultAddrseTelTlofNo}'/>-<c:out value='${defaultAddrseTelTlofWthnNo}'/>
													</c:if>
												</dd>
											</dl>					
										</div>	
										<!-- //배송지 -->
										<c:set var="defaultAddr">
											${defaultAddrseNm} | ${defaultAddrseBaseAddr} | ${defaultAddrseMobilAreaNo}-${defaultAddrseMobilTlofNo}-${defaultAddrseMobilTlofWthnNo}
											<c:if test="${not empty defaultAddrseTelAreaNo}">
												| ${defaultAddrseTelAreaNo}-${defaultAddrseTelTlofNo}-${defaultAddrseTelTlofWthnNo}
											</c:if>											 
										</c:set>
									</c:if>
											
									<!-- 상품 수거지 -->	
									<div class="exchangeAdd odAddBox">
										<dl>
											<dt><label for=""><spring:message code="mypage.order.detail.lbl.delivery.pickupdelivery" text="상품 수거지"/></label></dt>
											<dd>
												<input type="text" id="changePickupPlace" class="input-style01 textOnly" style="width:786px;" alt="상품 수거지" maxlength="100" disabled="disabled" value="<c:out value='${defaultAddr }'/>">
												<a href="#layerPopupChangeAddress" class="btn sm gray btnPosR d_layer_open dlvChange" ordNo="<c:out value='${ordNo }'/>" dlvPcupspTurn="" type='pkupExchange' ordTpCd="<c:out value='${ordTpCd}'/>" >
													<spring:message code="mypage.order.detail.lbl.delivery.pickup.changeinfo" text="수거지 정보변경"/>
												</a>
											</dd>
										</dl>
										<dl>
											<dt><label for="exAdd02"><spring:message code="mypage.order.detail.lbl.delivery.request" text="배송요청사항"/></label></dt>
											<dd>
												<input type="text" class="input-style01" id="shipAddressMsg" style="width:786px;" name="lgsDlvspList[0].dlvMemo" maxlength="100" disabled="disabled" >
											</dd>									
										</dl>								
									</div>
									<!-- //상품 수거지 -->
								
									
									<!-- 교환상품 선택  -->
									<div class="mgInfoBox">
										<h3><spring:message code="mypage.claim.exchange.lbl.select.goods" text="교환상품 선택"/> </h3>
										<table class="board-list orderTable">
				                          	<colgroup>
				                                <col style="width:15px">
		                                        <col>
		                                        <col style="width:100px">
		                                        <col style="width:146px">
		                                        <col style="width:240px">
				                          	</colgroup>	   
				                          	<thead>
					                      		<tr>
					                          		<th> 
					                          			<span class="check-skin">
			                                                <input type="checkbox" id="chkReply" onchange="allSelect($(this));">
			                                                <span><spring:message code="mypage.order.btn.select" text="선택"/></span>
			                                            </span>
			                                        </th>
					                          		<th colspan="2"><spring:message code="mypage.claim.cancel.goodsinfo" text="상품정보"/>/<spring:message code="mypage.claim.cancel.option" text="옵션"/></th>
					                          		<th><spring:message code="mypage.claim.exchange.lbl.exchangeqty" text="교환수량"/>/<spring:message code="mypage.claim.cancel.option" text="옵션"/></th>
					                          		<th><spring:message code="mypage.claim.exchange.lbl.exchangecause" text="교환사유"/> <span class="required">*</span></th>
					                          	</tr>
				                          	</thead>       
				                          	<tbody>
				                          		<c:forEach var="ordGodForRtnClmResult" items="${ordGodForRtnClmResult}" varStatus="mainSts">
				                          			<input type="hidden" name="dlvAdbukTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" />
													<!-- 수거지순번 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvPcupspTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}">												
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvAdbukTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" />
													<!-- 배송수거지구분 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvPcupspSectCd" value="CLM_PCUPSP"/>
													<!-- 쿠폰 번호 -->
							                        <input type="hidden" class="dlvCstCpnNo" name="lgsDlvspList[${mainSts.index}].dlvCstCpnNo" value="">
							                        
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseNm"              value="<c:out value='${defaultAddrseNm}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrsePostNo"          value="<c:out value='${defaultAddrsePostNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseBaseAddr"        value="<c:out value='${defaultAddrseBaseAddr}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseDetailAddr"      value="<c:out value='${defaultAddrseDetailAddr}'/>" />
													
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseMobilNationNo"   value="<c:out value='${defaultAddrseMobilNationNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseMobilAreaNo"     value="<c:out value='${defaultAddrseMobilAreaNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseMobilTlofNo"     value="<c:out value='${defaultAddrseMobilTlofNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseMobilTlofWthnNo" value="<c:out value='${defaultAddrseMobilTlofWthnNo}'/>" />
													
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseTelNationNo"     value="<c:out value='${defaultAddrseTelNationNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseTelAreaNo"       value="<c:out value='${defaultAddrseTelAreaNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseTelTlofNo"       value="<c:out value='${defaultAddrseTelTlofNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseTelTlofWthnNo"   value="<c:out value='${defaultAddrseTelTlofWthnNo}'/>" />
													
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrseNationCd"        value="<c:out value='${defaultAddrseNationCd}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].addrSectCd"            value="<c:out value='${defaultAddrSectCd}'/>" />
													
													<c:set var="dlvspChgYn" value="N" />
								
													<%--픽업 일반수거지주소가 있는경우 수거지정보 변경여부 Y--%>
													<c:if test="${orderInfo.ordTpCd eq 'SHOP_PKUP_ORD'}">
														<c:set var="dlvspChgYn" value="Y" />	
													</c:if>
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvspChgYn" value="${dlvspChgYn}" />
													
													<!-- 회원번호 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].mbrNo" value="${mbrNo}" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvMnCd" value="APPN_HDRY" class="dlvMnCdClass">
						
													<%--회원배송지 추가 체크여부--%>
													<input type="hidden" name="addMbrDlvspCheck" />
													<input type="hidden" name="defaultDelv" value="N"/>
						
													<!-- 수거지순번 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].dlvAdbukTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" class="dlvAdbukTurnClass">
													<!-- 배송수거지구분 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].dlvPcupspSectCd" value="CLM_DLVSP">
													
													<!-- 매장 픽업 수거지 변경을 위한 히든-->
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseNm" 		     value="<c:out value='${defaultAddrseNm}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrsePostNo"		     value="<c:out value='${defaultAddrsePostNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseBaseAddr"	     value="<c:out value='${defaultAddrseBaseAddr}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseDetailAddr"	     value="<c:out value='${defaultAddrseDetailAddr}'/>" />
													
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseMobilNationNo"	 value="<c:out value='${defaultAddrseMobilNationNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseMobilAreaNo"	 value="<c:out value='${defaultAddrseMobilAreaNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseMobilTlofNo"	 value="<c:out value='${defaultAddrseMobilTlofNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseMobilTlofWthnNo" value="<c:out value='${defaultAddrseMobilTlofWthnNo}'/>"/>
													
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseTelNationNo"     value="<c:out value='${defaultAddrseTelNationNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseTelAreaNo"       value="<c:out value='${defaultAddrseTelAreaNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseTelTlofNo"       value="<c:out value='${defaultAddrseTelTlofNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseTelTlofWthnNo"   value="<c:out value='${defaultAddrseTelTlofWthnNo}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrseNationCd"        value="<c:out value='${defaultAddrseNationCd}'/>" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].addrSectCd"            value="<c:out value='${defaultAddrSectCd}'/>" />
													<c:set var="dlvspChgYn2" value="N" />
								
													<%--픽업 일반수거지주소가 있는경우 수거지정보 변경여부 Y--%>
													<c:if test="${orderInfo.ordTpCd eq 'SHOP_PKUP_ORD'}">
														<c:set var="dlvspChgYn2" value="Y" />	
													</c:if>
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].dlvspChgYn" value="${dlvspChgYn2}" />
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].dlvMemo"/>
													
													<!-- 회원번호 -->
													<input type="hidden" name="lgsDlvspList[${mainSts.index + 1}].mbrNo" value="${mbrNo}" >	
													<input type="hidden" name="lgsDlvspList[${mainSts.index+1}].dlvMnCd" value="APPN_HDRY" class="dlvMnCdClass"> 	
													<input type="hidden" name="dlvAdbukTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" />
				                          			
				                          			<c:forEach var="ordGod" items="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList}" varStatus="subSts">
														<c:if test="${ordGod.dlvStatCd eq 'DLV_COMPT' && ordGod.dlvStatCd ne null && ordGod.cstmrPchDcsnYn ne 'Y'}">
															<!-- hidden 셋팅 -->
															<div id="hid${subSts.index}">
																<!-- 주문상품 등록용 ------------------------------------------------------------------------------------------------------------------------------------------------->
																<!-- 주문상품 교환시 등록용 - 주문배송지순번-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}dlvPcupspTurn" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].dlvPcupspTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" />
																<!-- 주문상품 교환시 등록용 - 주문번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}ordNo" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].ordNo" value="${ordGod.ordNo}" />
																<!-- 주문상품 교환시 등록용 - 주문상품순번-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																<!-- 주문상품 교환시 물류배송 등록용 - 국내배송비정책일련번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}dmstcDlvCstPlcSn" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}" />
																<!-- 주문상품 교환시 등록용 - 입점구분코드-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}partmalSectCd" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}" />
																<!-- 주문상품 교환시 등록용 - 상품유형-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																<!-- 주문상품 교환시 등록용 - 상품번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}godNo" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].godNo" value="${ordGod.godNo}" />
																<!-- 주문상품 교환시 등록용 - 단품번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}itmNo" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].itmNo" value="${ordGod.itmNo}" class="newItmNoClass"/>
																<!-- 주문상품 교환시 등록용 - 주문수량-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}ordQty" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].ordQty" value="${ordGod.wrkQty}" class="ordQtyClass"/>
																<!-- 주문상품 교환시 등록용 - 배송매장ID-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}dlvShopId" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].dlvShopId" value="${ordGod.dlvShopId}" />
																<!-- 주문상품 교환시 등록용 - 회원번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}mbrNo" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].mbrNo" value="${ordGod.mbrNo}" />
																<!-- 주문상품 교환시 등록용 - 출고지시상품순번 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																<!-- 주문상품 교환시 등록용 - 패키지상품순번 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}pckageGodTurn" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].pckageGodTurn" value="${ordGod.pckageGodTurn}" />
																
																<input type="hidden" ID="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}lgsItmYn"   name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].lgsItmYn" value="Y" />
																<!-- 상품 연결 유형 - 주문클레임상품연결 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}clmWrhsGodList${subSts.index}godCnncTpCd" name="lgsDlvspList[${mainSts.index + 1}].clmWrhsGodList[${subSts.index}].godCnncTpCd" value="DLIVY_GOD_CNNC" />
																
																
																<!-- 클레임입고상품 등록용 -------------------------------------------------------------------------------------------------------------------------------------------->
																<!-- 클레임입고상품 등록용 - 주문번호 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordNo" value="${ordGod.ordNo}" />
																<!-- 클레임입고상품 등록용 - 주문상품유형 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																<!-- 클레임입고상품 등록용 - 주문상품순번 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																<!-- 클레임입고상품 등록시 물류배송 등록용 - 국내배송비정책일련번호-->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dmstcDlvCstPlcSn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}" />
																
																<!-- 주문상품 교환시 등록용 - 입점구분코드-->
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}" />
																
																<!-- 클레임입고상품 등록용 - 출고지시상품순번 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																<!-- 클레임입고상품 등록용 - 상품번호 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godNo" value="${ordGod.godNo}" />
																
																<!-- 클레임입고상품 등록용 - 단품번호 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}itmNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].itmNo" value="${ordGod.itmNo}" />
																<!-- 클레임입고상품 등록용 - 배송순번 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																<!-- 클레임입고상품 등록용 - 배송매장ID -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvShopId" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvShopId" value="${ordGod.dlvShopId}" />
																<!-- 클레임입고상품 등록용 - 상품 연결 유형 - 주문클레임상품연결 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godCnncTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godCnncTpCd" value="WRHS_GOD_CNNC" />
																<!-- 클레임입고상품 등록용 - 품목명-편의점택배시 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}prdlstNm" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].prdlstNm" value="${ordGod.prdlstNm}" />
																<!-- 클레임입고상품 등록용 - 주결제금액-편의점택배시 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}stdrCrncySumAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].stdrCrncySumAmt" value="${ordGod.stdrCrncySumAmt}" />
																<!-- 클레임입고상품 등록용 - 정소가-편의점택배시 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}rtlPrc" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].rtlPrc" value="${ordGod.rtlPrc}" />
																<!-- 클레임입고상품 등록용 - 실소가-편의점택배시 사용 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}saleAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].saleAmt" value="${ordGod.saleAmt}" />
																<!-- 교환수량 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty" value="${ordGod.wrkQty}" />
																
																<!-- 클레임 사유코드 -->
																<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" value="" class="newClmResnClass"/>
																
																<input type="hidden" id="rtgodDlvCst" name="lgsDlvspList[${mainSts.index}].lgsDlvList[${mainSts.index}].rtgodDlvCst" value="0" />														  
																<input type="hidden" id="exchgDlvCst" name="lgsDlvspList[${mainSts.index + 1}].lgsDlvList[${mainSts.index}].exchgDlvCst" value="0" />
																
																<input type="hidden" id="lgsDlvspList${mainSts.index}lgsDlvList${mainSts.index}cnclDlvCst"  name="lgsDlvspList[${mainSts.index}].lgsDlvList[${mainSts.index}].cnclDlvCst"  value="0" class="number">
										
																<input type="hidden" id="lgsDlvspList${mainSts.index + 1}lgsDlvList${mainSts.index}cnclDlvCst"  name="lgsDlvspList[${mainSts.index + 1}].lgsDlvList[${mainSts.index}].cnclDlvCst"  value="0" class="number">
															</div>
															
															<!-- 세트가 아닌 경우 (구성품 포함)  -->
															<c:if test="${ordGod.pckageGodTpCd ne 'SET_GOD'}">
																<c:if test="${ordGod.wrkQty > 0}">
																	<tr>
									                        			<td>
								                                            <span class="check-skin">
								                                                <input type="checkbox" id="clmVal${subSts.index}" value="" onchange="inputCheck()">
								                                                <span><spring:message code="mypage.order.btn.select" text="선택"/></span>
								                                            </span>
							                                          	</td>
									                                  	<td class="tleft">
									                                      	<div class="product-info <c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">product-free-gift</c:if>">
									                                        	<div class="product-info-img"><a href="#none"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
									                                          	<div class="product-info-text">
									                                            	<div class="product-info-box">
									                                            		<c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">
									                                              			<div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.addcomposition" text="추가구성"/>]</span></div>
									                                              		</c:if>	
									                                                  	<p class="product-name"><a href="#none">${ordGod.godNm}</a></p>
									                                                  	<div class="product-price">
									                                                      	<span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
									                                                  	</div>
									                                              	</div>
									                                              	<div class="product-option">
									                                                  	<span>${ordGod.colorCd} / ${ordGod.itmNm}</span>
									                                              	</div>
									                                          	</div>
									                                      	</div>
									                                  	</td>
									                                  	<td>${ordGod.wrkQty}</td>
									                                  	<td>
										                                  	<a href="#layerPopupOption" data-godno="${ordGod.godNo}" data-itmqty="${ordGod.wrkQty}" data-shopid="${ordGod.changeDlvShopId}" data-mainsts="${mainSts.index}" data-substs="${subSts.index}" class="btn sm fill d_layer_open">
										                                  		<spring:message code="mypage.claim.exchange.lbl.changeoption" text="옵션변경"/>
										                                  	</a>
										                                  	<span class="optTxt">${ordGod.wrkQty} / ${ordGod.colorCd} / ${ordGod.itmNm}</span>
								                                  	  	</td>
									                                  	<td>
											                                <!-- select -->
																			<div class="select-style01 d_select">
																				<button type="button" class="d_select_sel" id="labelComment" style="width:200px;"><span id="resnSpan_${mainSts.index}_${subSts.index}"><spring:message code="mypage.order.lbl.select"/></span></button>
																				<ul>
																					<c:forEach var ="clm" items ="${clmRsn}">
																						<li><a href="#none"  onclick="chgSrchTypeParent('${clm.cd}','lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd', ${subSts.index});false;">${clm.cdNm}</a></li>
																					</c:forEach>													
																				</ul>
																			</div>
																			<!-- //select -->
																			<input type="text" class="input-style01 mgT05" value="" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCont" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" style="width:200px;" maxlength="200">
									                                  	</td>
									                               </tr>
																</c:if>
															</c:if>
															<!-- 셋트 상품 -->
															<c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD'}">
																<!-- 첫번째 셋트 구성품 (셋트상품의 경우 체크박스가 하나만 표시) -->
																<c:if test="${ordGod.sortSeq eq 1}">
																	<tr>
																		<td>
								                                        	<span class="check-skin">
								                                            	<input type="checkbox" id="clmVal${subSts.index}" value="SET" onchange="inputCheck()">
								                                                <span><spring:message code="mypage.order.btn.select" text="선택"/></span>
								                                            </span>
								                                        </td>
										                                <td class="tleft">
									                                    	<div class="product-info">
							                                                	<div class="product-info-img"><a href="#"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
							                                                   	<div class="product-info-text">
							                                                    	<div class="product-info-box">
							                                                        	<p class="product-name"><a href="#">${ordGod.setGodNm}</a></p>
							                                                           	<div class="product-price">
							                                                            	<span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
							                                                           	</div>
							                                                       	</div>
							                                                       	<div class="product-option">
							                                                           	<p>${ordGod.godNm} : ${ordGod.colorCd} / ${ordGod.itmNm}</p>
							                                   	</c:if>  
							                                   	<!-- 두번째 셋트 구성품 -->
																<c:if test="${ordGod.sortSeq eq 2}">
																                  		<!-- 첫번째 셋트 구성품의 체크박스를 선택했을 경우 동시에 두번째 셋트 상품도 체크 되도록 하기 위함 -->	
																						<div style="display:none">
																							<input type="checkbox" id="clmVal${subSts.index}" onchange="inputCheck()">
																						</div>
																						<p>${ordGod.godNm} : ${ordGod.colorCd} / ${ordGod.itmNm}</p>
																					</div>
							                                                   	</div>
							                                               	</div>
										                        		</td>
										                        		<td>${ordGod.wrkQty}</td>
										                        		<td>
										                        			<c:set value="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList[subSts.index -1]}" var="preOrdGod"/>
																			<a href="#layerPopupOption" data-godno="${ordGod.upperGodNo}" data-itmqty="${ordGod.wrkQty}" data-mainsts="${mainSts.index}" data-shopid="${ordGod.changeDlvShopId}" class="btn sm fill d_layer_open"><spring:message code="mypage.claim.exchange.lbl.changeoption" text="옵션변경"/></a>
										                                  	<span class="optTxt" data-colorcd="${preOrdGod.colorCd}" data-substs="${subSts.index - 1}">${preOrdGod.wrkQty} / ${preOrdGod.colorCd} / ${preOrdGod.itmNm}</span>
										                                  	<span class="optTxt" data-colorcd="${ordGod.colorCd}" data-substs="${subSts.index}">${ordGod.wrkQty} / ${ordGod.colorCd} / ${ordGod.itmNm}</span>			                                  
										                        		</td>
										                        		<td>
										                                    <!-- select -->
																			<div class="select-style01 d_select">
																				<button type="button" class="d_select_sel" id="labelComment" style="width:200px;"><span><spring:message code="mypage.order.lbl.select"/></span></button>
																				<ul>
																					<c:forEach var ="clm" items ="${clmRsn}">
																						<li>
																							<a href="#none" onclick="chgSrchTypeParent('${clm.cd}','lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd', ${subSts.index}); 
																                              chgSrchTypeParent('${clm.cd}','lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index -1}clmResnCd', ${subSts.index -1});false;">${clm.cdNm}</a>
																						</li>	
																					</c:forEach>													
																				</ul>
																			</div>
																			<!-- //select -->
																			<input type="text" class="input-style01 mgT05" style="width:200px;" maxlength="200" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index - 1}clmResnCont" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index - 1}].clmResnCont"  value="" onchange="$('#lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index }clmResnCont').val($(this).val());">
																			<input type="hidden" class="input-style01" style="width:310px;" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index }clmResnCont" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont"  value="" />
										                                </td>
										                        	</tr>
							                                    </c:if>                       	
															</c:if>
														</c:if>
													</c:forEach>
				                          		</c:forEach>                           
				                        	</tbody>
				                     	</table>	
			                    	</div>
			                        <!-- //교환상품 선택  -->
	                       		</div>	 
	                         	<!--  //order LIst  -->     
	                         	                 
	                  		</div>
	                        <!--  //order Result List -->    
	                        <div id="refundInfoDiv"></div>
	                        <!--  교환 배송비 -->
	                        <div class="mgInfoBox">
	                        	<h3><spring:message code="mypage.claim.exchange.lbl.exchange.delivery" text="교환 배송비"/></h3>
	                        	<table class="tbTotalList">
	                        		<caption><spring:message code="mypage.claim.exchange.lbl.exchange.delivery" text="교환 배송비"/></caption>
			                        <colgroup>
			                        	<col style="width:50%">
	                                    <col style="width:">
			                        </colgroup>	   
			                        <tbody>	
			                          	<tr class="selmgB">
			                          		<th class="fc_black"><spring:message code="mypage.claim.exchange.lbl.delivery.roundtrip" text="교환왕복배송"/></th>
			                          		<td id="divAmt1">0<spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr class="selLineBox">
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.amount" text="결제 금액"/></th>
			                          		<td><strong id="divAmt2">0</strong><spring:message code="mypage.order.lbl.currency" text="원"/></td>
			                          	</tr>		                          	
			                        </tbody>		                      
		                        </table>                        
	                        </div>
	                        <input type="hidden" id="payCrncyPayAmt" name="payCrncyPayAmt" value="0" />
				            <input type="hidden" id="rtgodDlvCstSum" name="rtgodDlvCstSum" value="0" />
				            <input type="hidden" id="exchgDlvCstSum" name="exchgDlvCstSum" value="0" />
	                        <!--  //교환 배송비 -->
	                        
	                        <div class="btnWrapBox">
								<a href="javascript:history.back();" id="cancelBtn"  class="btn"><spring:message code="mypage.order.btn.cancel" text="취소 "/></a>
								<a href="#" class="btn fill" id="acceptBtn" onclick="acceptRequestForReturn(); return false;"><spring:message code="mypage.claim.exchange.btn.requestexchange" text="교환신청"/></a> <!--  2018.12.06 수정 -->
							</div>
	                        
	                        <!--  주의사항  -->
	                        <div class="mgInfoBox">
	                        	<ul class="text-list02">
	                        		<li><spring:message code="mypage.claim.exchange.lbl.info.txt1" text="고객 단순 변심으로 교환을 한 경우 교환 배송비가 발생합니다."/></li>
									<li><spring:message code="mypage.claim.exchange.lbl.info.txt2" text="신청한 교환의 철회는 고객센터(080-807-0012)를 통해 신청 할 수 있습니다."/></li>
									<li><spring:message code="mypage.claim.exchange.lbl.info.txt3" text="교환/반품 신청은 배송완료일로부터 7일 이내에 신청이 가능합니다."/></li>
									<li><spring:message code="mypage.claim.exchange.lbl.info.txt4" text="지정된 택배를 통한 교환상품 입고가 되지 않는 경우, 교환 요청하신 상품에 이상이 있는 경우 교환처리가 되지 않을 수도 있습니다."/></li>                                                        
	                            </ul>
	                        </div>
	                        <!--  //주의사항  -->
						</div>		
						<!-- //order Result -->							
					</div>		
					<!--  // 취소상품 선택   -->	
				</main>
			</form>
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

<!-- layerpopup - 배송지 변경 -->
<article id="layerPopupChangeAddress" class="layer-popup deliveryModi-pop lypopDeliveryMd"></article>
<!-- //layerpopup - 배송지 변경 -->

<!-- layerpopup - 배송지선택 -->
<article id="layerPopupAddress" class="layer-popup lypopaddrSelect"></article>
<!-- //layerpopup - 배송지선택 -->

<%@ include file="/WEB-INF/views/common/layerpop/zipcode.jsp"%>

<script>

	/**
	 * 배송지 변경 레이어 팝업
	 */
	
	$('.dlvChange').click(function () {
	    dlvChange = $(this);
	
	    var ordNo = $(this).attr('ordNo');
	    
	    var dlvPcupspTurn = $('input[name="lgsDlvspList[0].dlvPcupspTurn"]').val();            
	
	    var type = $(this).attr('type');
	    var ordTpCd = $(this).attr('ordTpCd');
	
	    var strParams = {
	        '${_csrf.parameterName}': '${_csrf.token}',
	        'ordNo': ordNo,
	        'dlvPcupspTurn': dlvPcupspTurn,
	        'type': type,
	        'ordTpCd': ordTpCd
	    };
	
	    $("#layerPopupChangeAddress").load("<c:url value='/mypage/order/delivery/edit'/>", strParams);
	});
	
	<%-- 클레임 수량, 클레임 사유 선택 이벤트 --%>
	function chgSrchTypeParent(code,obj,seq){
		$("#hid"+seq+" #"+obj).val(code);
		
		var cont = obj.replace("clmResnCd","clmResnCont");
		
		if(code=='COLOR_INFO_DFFRNC'){
			$("#"+cont).prop("placeholder","옵션변경을 눌러 옵션을 변경해주세요")
			
			
			$("#"+cont).closest('tr').find('.d_layer_open').click();
		}else{
			$("#"+cont).prop("placeholder","")
			$("#"+cont).closest('tr').find('.d_layer_open').click();
		}
		if($("input:checkbox[id='clmVal"+seq+"']").prop("checked") == false){
			$("input:checkbox[id='clmVal"+seq+"']").click();
		}
    	
    	inputCheck();
	}

	function allSelect(obj) {
		$("input:checkbox[id^='clmVal']").each(function(index, item){
			$(item).prop("checked", obj.prop("checked"));
		});

		inputCheck();
	}
	
	<%-- 체크박스 선택시 체크된 로우만 환불금 계산을 하기 위해 --%>
	function inputCheck(){
		var cnt = 0;
		var totsize = $("input:checkbox[id^='clmVal']").length;
		
		for(var i=0; i < totsize ; i++){
			if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
				cnt++;
			}
		}
		
		$('input:hidden').attr("disabled",false); // 히든값 전체 사용여부 초기화

		if(cnt > 0){
			for(var i=0; i < totsize ; i++){
				if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
					// 세트의 경우 세트구성품 전체 체크
					if($("input:checkbox[id='clmVal"+i+"']").val() == "SET"){
						$("input:checkbox[id='clmVal"+(i+1)+"']").prop("checked",true);		
					}
				}
			}
			

			for(var i=0; i < totsize ; i++){
				if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
					$("#hid"+i).find('input:hidden').attr("disabled",false);
				}else{
					$("#hid"+i).find('input:hidden').attr("disabled",true);
				}
			}
			
	    	var strParams = $("#frmClaimInfo").serialize();

 			$.ajax({
				type : "POST",
				url     : "/mypage/claim/include/refundInfo",
				data : strParams,
				success : function(data) {
					$("#refundInfoDiv").html(data);

					var totalExchgDlvCst = 0;
					$('#refundInfoDiv').find($('input[name$="exchgDlvCst"]')).each(function (index, item){

						totalExchgDlvCst += parseInt($(item).val());
					});

					$('#exchgDlvCst').val(totalExchgDlvCst);
					// 총 배송비
					$('#payCrncyPayAmt').val(totalExchgDlvCst);
					$('#rtgodDlvCstSum').val();
					$('#exchgDlvCstSum').val();

					$('#divAmt1').html(strAddComma(totalExchgDlvCst) + '<spring:message code="mypage.order.lbl.currency"/>');
					$('#divAmt2').html(strAddComma(totalExchgDlvCst));
				},
				error : function(e) {
					alert("조회 실패");
				}
			});
		}
	}
			
	<%-- 클레임 사유 일괄적용--%>
	function clmCheck(){
		
		$('span[id^="resnSpan"]').each(function(){
			var str = $('span[id^="resnSpan"]:first').html();
			if($("#chkReason").prop("checked")){
				$(this).html(str);
			}
		});
		
		if($("#chkReason").prop("checked")){
			var totsize = $("input:checkbox[id^='clmVal']").length;
			
			for(var i=0; i < totsize ; i++){
				$("#hid"+i+" #lgsDlvspList0clmWrhsGodList"+i+"clmResnCd").val($("#hid0 #lgsDlvspList0clmWrhsGodList0clmResnCd").val());
			}

			for(var i=0; i < totsize ; i++){
				$("#lgsDlvspList0clmWrhsGodList"+i+"clmResnCont").val($("#lgsDlvspList0clmWrhsGodList0clmResnCont").val());
			}
		}
	}
	
	<%-- 교환 신청 버튼 --%>
    function acceptRequestForReturn(){ 
    
    	if ($('input[name="lgsDlvspList[0].addrsePostNo"]').val() == '') {
    		alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.delivery"/>');
            return false;
    	}
    		
    	var chkVal = true;
    	
    	$.each($('.newClmResnClass'),function(index,val){	    	
			if($('input:checkbox[id^="clmVal'+index+'"]').prop("checked")){				
				if($('#hid'+index+' #lgsDlvspList0clmWrhsGodList'+index+'clmResnCd').val() == null || $('#hid'+index+' #lgsDlvspList0clmWrhsGodList'+index+'clmResnCd').val() == undefined || $('#hid'+index+' #lgsDlvspList0clmWrhsGodList'+index+'clmResnCd').val() == ""){
					alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.cause"/>');
					chkVal = false;
					return;//each를 빠져나온다.
				}
			}
    	});
    	
    	if(!chkVal){
    		return;//함수를 빠져나온다.
    	}
    	
    	$.each($('.newItmNoClass'),function(index,val){
			if($('input:checkbox[id^="clmVal'+index+'"]').prop("checked")){
					if($('#hid'+index+' #lgsDlvspList1clmWrhsGodList'+index+'itmNo').val() == null || $('#hid'+index+' #lgsDlvspList1clmWrhsGodList'+index+'itmNo').val() == undefined|| $('#hid'+index+' #lgsDlvspList1clmWrhsGodList'+index+'itmNo').val() == ""){
					alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.size"/>');
					chkVal = false;
					return;//each를 빠져나온다.
				}
			}
    	});
    	
    	if(!chkVal){
    		return;//함수를 빠져나온다.
    	}

    	var totsize = $("input:checkbox[id^='clmVal']").length;
    	var cnt = 0;

		for(var i=0; i < totsize ; i++){
			if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
				cnt++;
			}
		}

		if(cnt < 1){
			alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.goods"/>');
			return;
		}
		
		inputCheck();
		
		var resnCdflag = false;
		var resnContflag = false;
		var itmNoFlg = false;
		
		if(cnt > 0){
			for(var i=0; i < totsize ; i++){
				if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
					// 세트의 경우 세트구성품 전체 체크
					if($("input:checkbox[id='clmVal"+i+"']").val() == "SET"){
						$("input:checkbox[id='clmVal"+(i+1)+"']").prop("checked",true);		
					}
				}
			}
			

			for(var i=0; i < totsize ; i++){
				if($("input:checkbox[id='clmVal"+i+"']").prop("checked")){
				
					if ($('#hid'+i+' #lgsDlvspList0clmWrhsGodList'+i+'clmResnCd').val() == null || $('#hid'+i+' #lgsDlvspList0clmWrhsGodList'+i+'clmResnCd').val() == ""){
						resnCdflag = true;
					} else if ($('#lgsDlvspList0clmWrhsGodList'+i+'clmResnCont').val() == null || $('#lgsDlvspList0clmWrhsGodList'+i+'clmResnCont').val() == ""){
						resnContflag = true;
					} else {
						$("#hid"+i).find('input:hidden').attr("disabled",false);
					}

					if($('#hid'+i+' #lgsDlvspList1clmWrhsGodList'+i+'itmNo').val() == null || $('#hid'+i+' #lgsDlvspList1clmWrhsGodList'+i+'itmNo').val() == ""){
						// 세트일 경우 체크
						if($("input:checkbox[id='clmVal"+(i-1)+"']").val() == "SET"){
							$("input:checkbox[id='clmVal"+(i-1)+"']").prop("checked",false);
						}
						itmNoFlg = true;
					}else{						
						$("#hid"+i).find('input:hidden').attr("disabled",false);
					}
				}else{
					$("#hid"+i).find('input:hidden').attr("disabled",true);
				}
			}
			
			// 전체 선택시 클레임사유 선택안한 없는 경우가 발생할때
			if(resnCdflag){
				alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.cause"/>');
				return false;
			}
			
			if(resnContflag){
				alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.detailcause"/>');
				return false;
			}
			
			if(itmNoFlg){
				alert('<spring:message code="mypage.js.claim.exchange.msg.noselect.size"/>');
				return false; 
			}
		}
		
		var confirm_msg = '<spring:message code="mypage.js.claim.exchange.msg.acceptexchange"/>' + " \n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>';
		
		if( parseInt($('#payCrncyPayAmt').val()) > 0){
			confirm_msg = '<spring:message code="mypage.js.claim.exchange.msg.addpay"/>' + "\n" + '<spring:message code="mypage.js.claim.exchange.msg.confirmexchange"/>';
		}

		if(!confirm(confirm_msg)) {
			return false;
		}
		
		$('input[name="lgsDlvspList[1].dlvMemo"]').val($('input[name="lgsDlvspList[0].dlvMemo"]').val());
		
		var strParams = $("#frmClaimInfo").serialize();
		
    	//$('.loading').show();
    	
    	$.ajax({
			type : "POST",
			url     : "<c:url value='/mypage/claim/exchange/request.json'/>",
			data : strParams,
			success : function(data) {
				alert('<spring:message code="mypage.js.claim.exchange.msg.complete"/>');
				var strParams = null;
			 	strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};  
			 	
			 	if("${nmbrYn}"!="Y"){
			 		location.href="<c:url value='/mypage/claim/list'/>";
			 	}else{
			 		location.href="<c:url value='/mypage/claim/list'/>";
			 	}
			 	if( parseInt($('#payCrncyPayAmt').val()) > 0){
			 		
			 		if(!confirm('<spring:message code="mypage.js.claim.cancel.msg.delivery.addpay" arguments="' + $('#payCrncyPayAmt').val() + '"/>' + "\n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>')) {
						return false;
					}
					// TODO : 추가결제..
				 	//getPayMethodChangePop('<c:url value='/secured/order/payMethodChangePop'/>',{'ordNo' : '${ordNo}' , 'type' : 'clmDlvAmtPay', 'clmNo' : data.clmNo});
			 	} 
			},
			error : function(xhr, sataus, e) {
				$('.loading').hide();
				alert(xhr.responseJSON.message);
			}				
		});	    	
    }		
    
	function goOrderList(strNmbrYn) {
	 	if(strNmbrYn!="Y"){
	 		location.href="<c:url value='/mypage/order/list'/>";
	 	}else{//TODO : 주문상세로..
	 		location.href="<c:url value='/mypage/order/${orderInfo.ordNo }/view'/>";
	 	}
	}
    
    /**
     * 입력값을 콤마가 포함된 문자열로 변환하여 리턴
     * @param str   숫자
     * @return ret  콤마를 추가한 숫자
    */
    function strAddComma(val) {
    	var ret;

    	//숫자앞에 있는 "0"을 먼저 삭제함. - 2004.9.12
    	var numstr = val + "";
    	var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');
    	var arrNumber = numstr.split('.');
    	arrNumber[0] += '.';
    	do {
    			arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2');
    	}
    	while (rxSplit.test(arrNumber[0]));

    	if (arrNumber.length > 1) {
    			ret = arrNumber.join('');
    	} else {
    			ret = arrNumber[0].split('.')[0];
    	}

    	return ret;
    }

	function goInquiryList() {
		location.href ="<c:url value='/mypage/inquiry/list'/>";
	}
	
	var $target;
	/* 옵션 변경 */
	$(document).ready(function() {
		 
		$("[href='#layerPopupOption']").click(function(){
			var godNo =  $(this).data('godno');
			var itmQty =  $(this).data('itmqty');  
			var shopId =  $(this).data('shopid');  
		 	
			$target = $(this);
			getChangeOption({godNo:godNo,itmQty:itmQty,shopId:shopId,caller:"clm"});
		});
	});
	
	function setChangeOption() {
		
		if(!checkSizeSelected()){
			return false;
		}else{
			$('.d_layer_close').click();
		}
		
       var godTpCd = $('#changeOptionForm').find('#optionGodTpCd').val();
       var mainsts = Number($target.data('mainsts'));
       var dlvMainsts = Number($target.data('mainsts')) + 1;
       var pcupMainsts = $target.data('mainsts');
	   
       if('SET_GOD' == godTpCd){
    	   var godNo = 	$('#changeOptionForm').find('#optionGodNo').val();	
    	   var qty = 	$('#qty').val();
    	   
    	   var text = "";
    	   var setSubsts;
    	   
    	   $('.cpstGodQty').each(function(index){
    		   
				var cpstGodNo = $('#changeOptionForm').find('#cpstGodNo'+index).val();	
				var cpstGodNm = $('#changeOptionForm').find('#cpstGodNm'+index).val();	
    			var itmNm = $('#changeOptionForm').find('#itmNm'+index).val();	
    			var itmNo = $('#changeOptionForm').find('#itmNo'+index).val();
    			
    			var $cpst = null;
    			
    			if (index == 0) {
    				$cpst = $target.closest('td').find('span').first();
    				setSubsts = $cpst.data('substs'); 
    			} else if (index == 1) {
    				$cpst = $target.closest('td').find('span').last();
    			}
    			
    			var substs = $cpst.data('substs');
    			
				$('#lgsDlvspList' + dlvMainsts + 'clmWrhsGodList' + substs + 'itmNo').val(itmNo);
   			   
				$('#lgsDlvspList' + pcupMainsts + 'clmWrhsGodList' + substs + 'clmQty').val(qty);
				$('#lgsDlvspList' + dlvMainsts + 'clmWrhsGodList' + substs + 'ordQty').val(qty);
    			   
    			var colorCd = $cpst.data('colorcd');
    			
    			$cpst.html(qty+" / "+colorCd+" / "+itmNm);
    			
    			if (index == 0) {
    				text += $('#lgsDlvspList' + mainsts + 'clmWrhsGodList' + substs + 'clmResnCont').closest('tr').find('.product-option').find('p').text() + " -> ";
    				text += colorCd+" / "+itmNm + " ,";
    			}else{
    				text += colorCd+" / "+itmNm;	
    			}
    			$('#lgsDlvspList' + mainsts + 'clmWrhsGodList' + substs + 'clmResnCont').val(text);	
    			
    	   });
    	   $('#lgsDlvspList' + mainsts + 'clmWrhsGodList' + setSubsts + 'clmResnCont').val(text);
	   }else{
	 
		   var substs = $target.data('substs');
		   
		   var godNo = 	$('#changeOptionForm').find('#optionGodNo').val();		
		   var itmNo = 	$('#changeOptionForm').find('#itmNo0').val();	
		   var itmNm = 	$('#changeOptionForm').find('#itmNm0').val();	
		   var colorCd = 	$('#changeOptionForm').find('#colorCd').val();	
		   var qty = 	$('#qty').val();
		  
		   $('#lgsDlvspList' + dlvMainsts + 'clmWrhsGodList' + substs + 'godNo').val(godNo);
		   $('#lgsDlvspList' + dlvMainsts + 'clmWrhsGodList' + substs + 'itmNo').val(itmNo);
		   
		   $('#lgsDlvspList' + pcupMainsts + 'clmWrhsGodList' + substs + 'clmQty').val(qty);
		   $('#lgsDlvspList' + dlvMainsts + 'clmWrhsGodList' + substs + 'ordQty').val(qty);
		   
		   $target.closest('td').find('span').text(qty+" / "+colorCd+" / "+itmNm);
		   
		   $('#lgsDlvspList' + mainsts + 'clmWrhsGodList' + substs + 'clmResnCont').val($('#lgsDlvspList' + mainsts + 'clmWrhsGodList' + substs + 'clmResnCont').closest('tr').find('.product-option').find('span').text()+ " -> " + colorCd+" / "+itmNm);
		   
		   
	   }
		
	}	
</script>
 <%@ include file="/WEB-INF/views/display/include/goods.option.jsp"%>