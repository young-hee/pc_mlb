<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ include file="/WEB-INF/views/common/layerpop/shop.view.jsp"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">
			<form name="frmClaimInfo" id="frmClaimInfo" method="post" action="/">
			    <input type="hidden" id="${_csrf.parameterName}" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <input type="hidden" id="ordNo" name="ordNo" value="${orderInfo.ordNo }"/>
			    <input type="hidden" id="ordTpCd" name="ordTpCd" value="${ordTpCd}"/>
			    <input type="hidden" id="clmNo" name="clmExtend.clmNo" value=""/>
			    <input type="hidden" id="mbrNo" name="mbrNo" value="${mbrNo}"/>
			    <input type="hidden" id="ordGodTurnStr" name="ordGodTurnStr" value="${ordGodTurnStr}"/>
			    <input type="hidden" id="mbrTp" name="mbrTp" value="${mbrTp}"/>
			    <input type="hidden" id="role" name="role" value="F"/>
			
			    <input type="hidden" id="clmStatCd" name="clmStatCd" value="REQST"/>
			    <input type="hidden" id="clmTpCd" name="clmTpCd" value="RTGOD"/>
			    <input type="hidden" id="dlivyDrctTpCd" name="dlivyDrctTpCd" value="RTGOD"/>
			
			    <input type="hidden" name="clmExtend.virtlRtgodYn" value="N"/>
			    <input type="hidden" name="repairRtgodYn" value="N"/>
			    <input type="hidden" name="clmExtend.pgTrnsmisTgtYn" value="Y"/>
			    <input type="hidden" name="dlvComTrnsmisTgtYn" value="Y"/>
			    <input type="hidden" id="rfdBnkCd" name="clmExtend.rfdBnkCd" value=""/> <%-- 환불계좌 은행코드 --%>
			    <input type="hidden" id="rfdAcnthldrNm" name="clmExtend.rfdAcnthldrNm" value=""/> <%-- 환불계좌 예금주명  --%>
			    <input type="hidden" id="rfdBnkAcctNo" name="clmExtend.rfdBnkAcctNo" value=""/> <%-- 환불계좌 번호  --%>
			    <input type="hidden" id="refundCheckYn" name="refundCheckYn" value="N"/> <%-- 환불계좌 인증 여부 --%>
			    <input type="hidden" id="ordCstGbn" name="ordCstGbn" value="${ordCstGbn}"/>
			    <input type="hidden" id="pkupClmYn" name="clmExtend.pkupClmYn" value="N"/>
			
			    <input type="hidden" id="adminTpCd" name="adminTpCd" value=""/>
			    <input type="hidden" id="regtrShopId" name="regtrShopId" value=""/>
		    
				<h2 class="title01"><spring:message code="mypage.order.return.ttl" text="상품 반품신청"/></h2>
				
				<%@ include file="../include/lnb.jspf" %>
				
				<main class="contents" id="contents">
					
					<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>  
					
					<!-- 반품상품 선택  -->
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
									
									<c:forEach var="listDlvsp" items="${dlvspList}" varStatus="dlvSts">
										<c:if test="${listDlvsp.clmCnt > 0}">
											<input type="hidden" name="ord.ordNo"/>
											<input type="hidden" name="ord.ordTpCd"/>
											<input type="hidden" name="ord.cstmrNm"/>
											<input type="hidden" name="type"/>
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseNm"              value="<c:out value='${listDlvsp.addrseNm}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrsePostNo"          value="<c:out value='${listDlvsp.addrsePostNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseBaseAddr"        value="<c:out value='${listDlvsp.addrseBaseAddr}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseDetailAddr"      value="<c:out value='${listDlvsp.addrseDetailAddr}'/>" />
											
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseMobilNationNo"   value="<c:out value='${listDlvsp.addrseMobilNationNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseMobilAreaNo"     value="<c:out value='${listDlvsp.addrseMobilAreaNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseMobilTlofNo"     value="<c:out value='${listDlvsp.addrseMobilTlofNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseMobilTlofWthnNo" value="<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>" />
											
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseTelNationNo"     value="<c:out value='${listDlvsp.addrseTelNationNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseTelAreaNo"       value="<c:out value='${listDlvsp.addrseTelAreaNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseTelTlofNo"       value="<c:out value='${listDlvsp.addrseTelTlofNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseTelTlofWthnNo"   value="<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrseNationCd"        value="<c:out value='${listDlvsp.addrseNationCd}'/>" />
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].addrSectCd"            value="<c:out value='${listDlvsp.addrSectCd}'/>" />
											<%--회원배송지 추가 체크여부--%>
											<input type="hidden" name="addMbrDlvspCheck" />
											<input type="hidden" name="defaultDelv" value="N"/>
					
											<input type="hidden" name="lgsDlvspList[${dlvSts.index}].dlvspChgYn" value="N"/>
											<c:set var="defaultAddr" value="" />
											<c:if test="${orderInfo.ordTpCd ne 'SHOP_PKUP_ORD'}">
												<c:set var="defaultAddr">
													${listDlvsp.addrseNm} | ${listDlvsp.addr} | ${listDlvsp.addrseMobilAreaNo}-${listDlvsp.addrseMobilTlofNo}-${listDlvsp.addrseMobilTlofWthnNo}
													<c:if test="${not empty listDlvsp.addrseTelAreaNo}">
														| ${listDlvsp.addrseTelAreaNo}-${listDlvsp.addrseTelTlofNo}-${listDlvsp.addrseTelTlofWthnNo}
													</c:if>
												</c:set>
												<div class="orderAdd odAddBox">																	
													<dl>
														<dt><spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/></dt>
														<dd><c:out value='${listDlvsp.addrseNm}'/></dd>
													</dl>
													<%--일반수거지주소가 있는경우 일반수거지주소 노출--%>
													<c:if test="${listDlvsp.addr ne listDlvsp.addrShop}">
														<dl>
															<dt><spring:message code="mypage.order.detail.lbl.delivery.address" text="주소"/></dt>
															<dd><c:out value='${listDlvsp.addr}'/></dd>
														</dl>
													</c:if>
													<dl>
														<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
														<dd>
															<c:if test="${not empty listDlvsp.addrseMobilAreaNo and not empty listDlvsp.addrseMobilTlofNo and not empty listDlvsp.addrseMobilTlofWthnNo}">
																<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
															</c:if>
															<c:if test="${not empty listDlvsp.addrseTelAreaNo and not empty listDlvsp.addrseTelTlofNo and not empty listDlvsp.addrseTelTlofWthnNo}">
																 | <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
															</c:if>
														</dd>
													</dl>					
												</div>
												
											</c:if>
											<c:if test="${orderInfo.ordTpCd eq 'SHOP_PKUP_ORD'}">
												<!-- 배송지 -->
												<div class="orderAdd odAddBox">									
													<dl>
														<dt><strong>[<spring:message code="mypage.order.detail.lbl.delivery.shoppickup" text="매장픽업"/>]</strong></dt>										
														<dd class="StoreInfo">
				                                           	<strong class="name"><c:out value='${orderInfo.shopNm }'/></strong>
				                                           	<a href="#" class="btnMap" onclick="showPopupShop('${orderInfo.shopId}');return false;"><img src="${_resourceURL}static/images/od/icon_location.png" alt="지도보기"></a><br>
				                                           	${orderInfo.shopBaseAddr}${orderInfo.shopDetailAddr}<span class="lineBar"><c:out value='${orderInfo.shopTelTlofWthnNo}'/></span><!-- 영업시간<span class="lineBar">11:30 ~ 22:00</span> 2018.12.06 삭제-->
														</dd>
													</dl>						
												</div>	
												<!-- //배송지 -->
											</c:if>
											
											<!-- 상품 수거지 -->	
											<div class="exchangeAdd odAddBox">
												<dl>
													<dt><label for="changePickupPlace"><spring:message code="mypage.order.detail.lbl.delivery.changepickupdelivery" text="변경수거지"/></label></dt>
													<dd>
														<input type="text" id="changePickupPlace" class="input-style01 textOnly" style="width:786px;" alt="상품 수거지" maxlength="100" disabled="disabled" value="<c:out value='${defaultAddr }'/>">
														<a href="#layerPopupChangeAddress" class="btn sm gray btnPosR d_layer_open dlvChange" ordNo="<c:out value='${ordNo }'/>" dlvPcupspTurn="" type='returnChange' ordTpCd="<c:out value='${ordTpCd}'/>">
															<spring:message code="mypage.order.detail.lbl.delivery.pickup.changeinfo" text="수거지 정보변경"/>
														</a>
													</dd>
												</dl>
												<dl>
													<dt><label for="exAdd02"><spring:message code="mypage.order.detail.lbl.delivery.request" text="배송요청사항"/></label></dt>
													<dd>
														<input type="text" class="input-style01" value="" name="lgsDlvspList[${dlvSts.index}].dlvMemo" id="dlvMemo1" disabled="disabled" style="width:786px;" maxlength="200">
													</dd>									
												</dl>								
											</div>
											<!-- //상품 수거지 -->
											
											<!-- 반품상품 선택  -->
											<div class="mgInfoBox">
												<h3><spring:message code="mypage.order.return.select" text="반품상품 선택"/> </h3>
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
				                                                <input type="checkbox" id="chk${dlvSts.index}" value="모두 체크" onchange="allSelect($(this));">
				                                                <span><spring:message code="mypage.order.btn.select" text="선택"/></span>
				                                            </span>
				                                        </th>
						                          		<th colspan="2"><spring:message code="mypage.claim.cancel.goodsinfo" text="상품정보"/>/<spring:message code="mypage.claim.cancel.option" text="옵션"/></th>
						                          		<th><spring:message code="mypage.claim.return.lbl.returnqty" text="반품수량"/></th>
						                          		<th><spring:message code="mypage.claim.return.lbl.returncause" text="반품사유 "/> <span class="required">*</span></th>
						                          	</tr>
						                          </thead>       
						                          <tbody>
						                          	<c:forEach var="ordGodForRtnClmResult" items="${ordGodForRtnClmResult}" varStatus="mainSts">
						                          		<c:if test="${listDlvsp.dlvPcupspTurn eq ordGodForRtnClmResult.dlvPcupspTurn}">
						                          			<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvPcupspTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}">
							                                <!-- 수거지순번 -->
							                                <input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvAdbukTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" class="dlvAdbukTurnClass">
							                                <!-- 배송수거지구분 -->
							                                <input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvPcupspSectCd" value="CLM_PCUPSP">
							                                <!-- 회원번호 -->
							                                <input type="hidden" name="lgsDlvspList[${mainSts.index}].mbrNo" value="${mbrNo}">
							                                <!-- 환불 배송비 -->
							                                <input type="hidden" id="lgsDlvspList${mainSts.index}lgsDlvList${mainSts.index}cnclDlvCst" name="lgsDlvspList[${mainSts.index}].lgsDlvList[${mainSts.index}].cnclDlvCst" value="0">
							                                <!-- 반품 배송비 -->
							                                <input type="hidden" id="lgsDlvspList${mainSts.index}lgsDlvList${mainSts.index}rtgodDlvCst" name="lgsDlvspList[${mainSts.index}].lgsDlvList[${mainSts.index}].rtgodDlvCst" value="0">
							                                <!-- 회수방법 -->
							                                <input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvMnCd" value="APPN_HDRY" class="dlvMnCdClass">
							                                <!-- 반품쿠폰 번호 -->
									                        <input type="hidden" class="dlvCstCpnNo" name="lgsDlvspList[${mainSts.index}].dlvCstCpnNo" value="">
									                        
									                        <c:forEach var="ordGod" items="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList}" varStatus="subSts">
																<c:if test="${ordGod.dlvStatCd eq 'DLV_COMPT' && ordGod.dlvStatCd ne null && ordGod.clmYn eq 'Y' && ordGod.wrkQty > 0 && ordGod.cstmrPchDcsnYn ne 'Y'}">
																	<c:choose>
																		<%-- 상품사은품 --%>
																		<c:when test="${(ordGod.godTpCd eq 'PCHS_GFT' or ordGod.godTpCd eq 'CNVRS_GFT') && ordGod.prmDtlTpCd eq 'GOD_GFT'}">
																			<tr>
										                                		<td>
										                                			<input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" style="display:none;" godtpcd="${ordGod.godTpCd }" data-isview="N" />
										                                			<input type="hidden" value="1" class="dlvGodCnt"/>
																					<!-- 주문상품유형 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																					<!-- 주문상품순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																					<!-- 출고지시상품순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																					<!-- 배송순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																					<input type="hidden"  id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" value="" class="clmResnCdClass${ordGod.ordGodGftTurn}"/>
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value="" />
										                                		</td>
											                                  	<td class="tleft">
											                                     	<div class="product-info product-free-gift">
									                                                   <div class="product-info-img"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></div>
									                                                   <div class="product-info-text">
									                                                       <div class="product-info-box">
									                                                           <div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.goodsgift" text="상품사은품"/>]</span></div>
									                                                           <p class="product-name"><a href="#">${ordGod.godNm}</a></p>
									                                                           <div class="product-price">
									                                                               <span>0<spring:message code="mypage.order.lbl.currency" text="원"/></span>
									                                                           </div>
									                                                       </div>
									                                                       <div class="product-option">
									                                                           <span>${ordGod.colorCd} / ${ordGod.itmNm}</span>
									                                                       </div>
									                                                   </div>
									                                               </div>
											                                  	</td>
											                                  	<td></td>
											                                  	<td>
											                                  		<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty" value="${ordGod.wrkQty}" maxVal="${ordGod.wrkQty}" class="GOD_GFT_${ordGod.ordGodGftTurn}" />
											                                  	</td>
											                                  	<td></td>
											                               	</tr>
																		</c:when>
																		<%-- 주문사은품 --%>
																		<c:when test="${(ordGod.godTpCd eq 'PCHS_GFT' or ordGod.godTpCd eq 'CNVRS_GFT') && ordGod.prmDtlTpCd eq 'ORD_GFT'}">
																			<tr>
					                                	  						<td>
					                                	  							<input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" style="display:none;" godtpcd="${ordGod.godTpCd }" data-isview="N"/>
																					<input type="hidden" value="1" class="dlvGodCnt"/>
																					<!-- 주문상품유형 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																					<!-- 주문상품순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																					<!-- 출고지시상품순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																					<!-- 배송순번 -->
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																					<input type="hidden"  id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" value="" class="clmResnCdClass${ordGod.ordGodGftTurn}"/>
																					<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value="" />
					                                	  						</td>
						                                  						<td class="tleft">
						                                     						<div class="product-info product-free-gift">
				                                                   						<div class="product-info-img"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></div>
				                                                   						<div class="product-info-text">
				                                                      						<div class="product-info-box">
				                                                           						<div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.ordergift" text="주문사은품"/>]</span></div>
				                                                           						<p class="product-name"><a href="#">${ordGod.godNm}</a></p>
				                                                           						<div class="product-price">
				                                                               						<span>0<spring:message code="mypage.order.lbl.currency" text="원"/></span>
				                                                           						</div>
				                                                       						</div>
				                                                       						<div class="product-option">
				                                                           						<span>${ordGod.colorCd} / ${ordGod.itmNm}</span>
				                                                       						</div>
				                                                   						</div>
				                                               						</div>
						                                  						</td>
						                                  						<td></td>
						                                  						<td></td>
						                                  						<td></td>
						                               						</tr>
																		</c:when>
																		<c:when test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}">
																			<tr>
						                             	 						<td>
				                                            						<span class="check-skin">
				                                                						<input class="checkBox" type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" 
					                                                                    	<c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> godtpcd="${ordGod.godTpCd }" data-isview="N"/>
					
					                                                            		<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordNo" value="${ordNo }"/>
					
							                                                            <!-- ncp2 -->
							                                                            <!-- 입점 구분 코드 -->
							                                                            <input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}"/>
							                                                            <!-- 국내 배송비 정책 일련번호 -->
							                                                            <input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}"/>
							                                                            <!-- ncp2 -->
					
					
							                                                            <%-- 클레임 사유코드 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" class="clmResnCd"
							                                                                   <c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> mainIndex="${mainSts.index}" subIndex="${subSts.index}" value=""/>
							                                                            <%-- 주문상품유형 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}"/>
							                                                            <%-- 주문상품순번 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" class="ordGodTurn"/>
							                                                            <%-- 출고지시상품순번 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}"/>
							                                                            <!-- 상품번호 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godNo" value="${ordGod.godNo}"/>

							                                                            <!-- 단품번호 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}itmNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].itmNo" value="${ordGod.itmNo}"/>
							                                                            <%-- 배송순번 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}"/>
							                                                            <!-- 배송매장ID -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvShopId" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvShopId" value="${ordGod.dlvShopId}"/>
							
							                                                            <%--취소수량 선택값히든 --%>
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty" value="${ordGod.wrkQty}" class="clmQtyClass"
																							<c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> mainIndex="${mainSts.index}" subIndex="${subSts.index}" />
																						
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}wrkQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].wrkQty" value="${ordGod.wrkQty}" class="wrkQtyClass"/>
							
							                                                            <!-- 상품 연결 유형 - 주문클레임상품연결 사용 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godCnncTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godCnncTpCd" value="WRHS_GOD_CNNC"/>
							                                                            <!-- 품목명-편의점택배시 사용 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}prdlstNm" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].prdlstNm" value="${ordGod.prdlstNm}"/>
							                                                            <!-- 주결제금액-편의점택배시 사용 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}stdrCrncySumAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].stdrCrncySumAmt" value="${ordGod.stdrCrncySumAmt}"/>
							                                                            <!-- 정소가-편의점택배시 사용 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}rtlPrc" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].rtlPrc" value="${ordGod.rtlPrc}"/>
							                                                            <!-- 실소가-편의점택배시 사용 -->
							                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}saleAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].saleAmt" value="${ordGod.saleAmt}"/>
				                                            						</span>
				                                          						</td>
						                                  						<td class="tleft">
						                                      						<div class="product-info">
							                                          					<div class="product-info-img"><a href="#"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
							                                          					<div class="product-info-text">
											                                              	<div class="product-info-box">
											                                                	<p class="product-name"><a href="#">${ordGod.godNm}</a></p>
											                                                  	<div class="product-price">
											                                                      	<span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###"/><spring:message code="mypage.order.lbl.currency" text="원"/></span>
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
						                                  							<c:set var="clmQtySum" value="${clmQtySum + ordGod.wrkQty}"/> 
					                                  	  						</td>
						                                  						<td>
							                                						<input type="hidden" class="inp_txt" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCont" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" maxlength="" value="" disabled="disabled" mainIndex="${mainSts.index}" subIndex="${subSts.index}">
						                                  						</td>
						                               						</tr>
																		</c:when>
																		<c:otherwise>
																			<c:if test="${ordGod.wrkQty > 0}">
																				<tr id="TR_${mainSts.index}_${subSts.index}"
																					ordGodTurn="${ordGod.ordGodTurn}" 
																					godTpCd="${ordGod.godTpCd}" 
																					<c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if>
																					mainIndex="${mainSts.index}" 
																					subIndex="${subSts.index}" 
																					wrkQty="${ordGod.wrkQty}">
												                             	 	<td>
											                                            <span class="check-skin">
											                                                <input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" mainIndex="${mainSts.index}" subIndex="${subSts.index}" godtpcd="${ordGod.godTpCd }"
																								<c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> data-isview="Y">
											                                                <span><spring:message code="mypage.order.btn.select" text="선택"/></span>
											                                                <input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordNo" value="${orderInfo.ordNo }"/>
								                                                            <!-- 입점 구분 코드 -->
								                                                            <input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}"/>
								                                                            <!-- 국내 배송비 정책 일련번호 -->
								                                                            <input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}"/>
								                                                            <%-- 클레임 사유코드 --%>
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" class="clmResnCd"
																								<c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> mainIndex="${mainSts.index}" subIndex="${subSts.index}" value=""/>
								                                                            <%-- 주문상품유형 --%>
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}"/>
								                                                            <%-- 주문상품순번 --%>
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" class="ordGodTurn"/>
								                                                            <%-- 출고지시상품순번 --%>
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}"/>
								                                                            <!-- 상품번호 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godNo" value="${ordGod.godNo}"/>
																							<!-- 단품번호 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}itmNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].itmNo" value="${ordGod.itmNo}"/>
								                                                            <%-- 배송순번 --%>
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}"/>
								                                                            <!-- 배송매장ID -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvShopId" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvShopId" value="${ordGod.dlvShopId}"/>
								
																							<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}wrkQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].wrkQty" value="${ordGod.wrkQty}" class="wrkQtyClass"/>
						
								                                                            <!-- 상품 연결 유형 - 주문클레임상품연결 사용 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godCnncTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godCnncTpCd" value="WRHS_GOD_CNNC"/>
								                                                            <!-- 품목명-편의점택배시 사용 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}prdlstNm" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].prdlstNm" value="${ordGod.prdlstNm}"/>
								                                                            <!-- 주결제금액-편의점택배시 사용 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}stdrCrncySumAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].stdrCrncySumAmt" value="${ordGod.stdrCrncySumAmt}"/>
								                                                            <!-- 정소가-편의점택배시 사용 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}rtlPrc" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].rtlPrc" value="${ordGod.rtlPrc}"/>
								                                                            <!-- 실소가-편의점택배시 사용 -->
								                                                            <input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}saleAmt" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].saleAmt" value="${ordGod.saleAmt}"/>
											                                            </span>
										                                          	</td>
												                                  	<td class="tleft">
												                                      	<div class="product-info <c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">product-free-gift</c:if>">
												                                          	<div class="product-info-img"><a href="#"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
												                                          	<div class="product-info-text">
												                                              	<div class="product-info-box">
												                                                  	<p class="product-name"><a href="#">${ordGod.godNm}</a></p>
												                                                  	<div class="product-price">
												                                                      	<span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###"/><spring:message code="mypage.order.lbl.currency" text="원"/></span>
												                                                  	</div>
												                                              	</div>
												                                              	<div class="product-option">
												                                              		<c:choose>
												                                              			<c:when test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}">
												                                              				<span>
																												<c:set var="isFirstPkg" value="true" />
																												<c:forEach var="optionList" items="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList}" varStatus="status">
																													<c:if test="${optionList.pckageGodTpCd eq 'SET_GOD' || optionList.pckageGodTpCd eq 'PCKAGE_GOD'}">
																														<c:if test="${ordGod.ordGodTurnAnce eq optionList.ordGodTurnAnce}">
																															<c:if test="${isFirstPkg eq false}">,&nbsp;</c:if>
																															${optionList.godNm} : ${optionList.colorCd} / ${optionList.itmNm}
																															<c:set var="isFirstPkg" value="false" />
																														</c:if>
																													</c:if>
																												</c:forEach>
																											</span>
												                                              			</c:when>
												                                              			<c:otherwise>
																											<span>${ordGod.colorCd} / ${ordGod.itmNm}</span>
																										</c:otherwise>
												                                              		</c:choose>
												                                                  	<c:set var="clmQtySum" value="${clmQtySum + ordGod.wrkQty}"/>
												                                              	</div>
												                                          	</div>
												                                      	</div>
												                                  	</td>
												                                  	<td>${ordGod.wrkQty}</td>
												                                  	<td>
												                                  		<div class="quantityWrap">
										                                                 	<button type="button" class="btMinus" onclick="changeQty('${mainSts.index}', '${subSts.index}', -1);"><spring:message code="mypage.claim.btn.subtract" text="빼기"/></button>
										                                                	<button type="button" class="btPlus" onclick="changeQty('${mainSts.index}', '${subSts.index}', +1);"><spring:message code="mypage.claim.btn.addtion" text="추가"/></button>
										                                                	<input type="number" class="pdNumber clmQtyClass" maxlength="5" value="${ordGod.wrkQty}"  data-wrkqty="${ordGod.wrkQty}"
										                                                		id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmQty"
										                                                		name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty"
																								<c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> 
																								mainIndex="${mainSts.index}" 
																								subIndex="${subSts.index}">
									                                            		</div> 
											                                  	  	</td>
												                                  	<td>
														                                <!-- select -->
																						<div class="select-style01 d_select">
																							<button type="button" class="d_select_sel" id="labelComment" style="width:200px;"><span id="selRsnTxt${mainSts.index}${subSts.index}"><spring:message code="mypage.order.lbl.select"/></span></button>
																							<ul>
																								<c:forEach var="clm" items="${clmRsn}">
																									<li><a href="#none" class="selClmRsnCd" godTpCd="${ordGod.godTpCd}" <c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if>  mainIndex="${mainSts.index}" subIndex="${subSts.index}" clmCd="${clm.cd}">${clm.cdNm}</a></li>
							                                                                    </c:forEach>														
																							</ul>
																						</div>
																						<!-- //select -->
																						<input type="text" class="input-style01 mgT05 clmResnCont" style="width:200px;" maxlength="200"
																							id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCont" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont"
                                                                       						godTpCd="${ordGod.godTpCd}" <c:if test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if>  mainIndex="${mainSts.index}" subIndex="${subSts.index}">
												                                  	</td>
												                               </tr>
																			</c:if>
																		</c:otherwise>
																		
																	</c:choose>
																</c:if>
															</c:forEach>
						                          		</c:if>	
						                          	</c:forEach>
						                                                             
						                     	</tbody>
						                	</table>	
					                	</div>
					                    <!-- //반품상품 선택  -->
											
										</c:if>
									</c:forEach>
										
									<input type="hidden" name="returnDlvAmt" id="returnDlvAmt" value="0">
									
									
	                         	</div>	 
	                         	<!--  //order LIst  -->     
	                         	                 
	                        </div>
	                        <!--  //order Result List -->    
	                        
	                        <div class="mgInfoBox" id="refundInfoDiv">
							</div>
	                        
	                        
	                        <c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}">
							<!--  환불계좌  -->
	                        <div class="mgInfoBox">
	                        	<h3><spring:message code="mypage.claim.lbl.refund.account" text="환불계좌 정보"/></h3>
	                        	<table class="tbInputList">
	                        		<caption><spring:message code="mypage.claim.lbl.refund.account" text="환불계좌 정보"/></caption>
			                        <colgroup>
			                             <col style="width:100px">
	                                     <col style="width:">
			                        </colgroup>	   
			                        <tbody>
			                          	<tr>
			                          		<th><label for="bankName"><spring:message code="mypage.claim.lbl.refund.bankname" text="은행명 "/></label> <span class="required">*</span></th>
			                          		<td>
												<!-- select -->
												<div class="select-style01 d_select">
													<button type="button" id="bankName" class="d_select_sel" style="width:410px;"><span>선택하세요</span></button>
													<ul>
														<li><a href="#none" onclick="javascript:chgBnkType('','rfdBnkCd'); false;"><spring:message code="mypage.order.lbl.select" text="선택하세요"/></a></li>
														<ncp:codes group="BNK" var="bnk"/>
											            <c:forEach var="bk" items="${bnk}">
											            	<c:if test="${not empty bk.asstnCd1}">
																<li><a href="#none" onclick="javascript:chgBnkType('${bk.cd}','rfdBnkCd'); false;">${bk.cdNm}</a></li>
															</c:if>
											            </c:forEach>													
													</ul>
												</div>
												<!-- //select -->		                          		
			                          		</td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><label for="bankTo"><spring:message code="mypage.claim.lbl.refund.accountname" text="예금주 "/></label><span class="required">*</span></th>
			                          		<td><input type="text" id="rfdAcnthldrNmOd" class="input-style01 textOnly" style="width:410px;" alt="예금주" maxlength="100"></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><label for="bankNumber"><spring:message code="mypage.claim.lbl.refund.accountno" text="계좌번호 "/></label><span class="required">*</span></th>
			                          		<td><input type="text" id="bankNumber" class="input-style01 numberOnly" style="width:410px;" alt="계좌번호" maxlength="100" placeholder="<spring:message code='mypage.claim.lbl.refund.accountno.placeholder' />"></td>		                          		
			                          	</tr>
		                          	</tbody>
		                        </table>
	                        </div>
	                        <!--  //환불계좌  -->      
	                        </c:if>                  
	                        
	                        <div class="btnWrapBox">
								<a href="#none" class="btn" onclick="goOrderList('${nmbrYn}');return false;"><spring:message code="mypage.order.btn.cancel" text="취소 "/></a>
								<a href="#none" class="btn fill" onclick="acceptRequestForReturn(); return false;"><spring:message code="mypage.claim.return.btn.requestreturn" text="반품신청"/></a>
							</div>
	                        
	                        <!--  주의사항  -->
	                        <div class="mgInfoBox">
	                        	<ul class="text-list02">  
	                        		<li><spring:message code="mypage.claim.return.lbl.info.txt1" text="일부 상품만 반품신청 하시면 고객센터에서 확인 후, 반품 접수 처리 됩니다."/></li>
									<li><spring:message code="mypage.claim.return.lbl.info.txt2" text="주문상품 중 일부만 반품하시면 이미 사용하신 마일리지, 포인트는 주문금액 중 반품 금액 비율 로 나누어 복원됩니다."/></li>
									<li><spring:message code="mypage.claim.return.lbl.info.txt3" text="장바구니 쿠폰 등 주문 전체에 사용된 쿠폰은 일부 반품 시, 복원되지 않을 수 있습니다."/></li>
									<li><spring:message code="mypage.claim.return.lbl.info.txt4" text="묶음배송으로 인해 배송비 무료가 적용된 경우 추가 배송비가 발생 될 수 있습니다."/></li>
									<li><spring:message code="mypage.claim.return.lbl.info.txt6" text="교환/반품 신청은 배송완료일로부터 7일 이내에 신청이 가능합니다."/></li>
									<li><spring:message code="mypage.claim.return.lbl.info.txt7" text="에스크로서비스를 선택한 주문은 전체 취소만 가능합니다."/></li>
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
    	
        $('.selClmRsnCd').click(function (){
            var godTpCd = $(this).attr("godTpCd");
            var clmCd = $(this).attr("clmCd");
            var ance = $(this).attr("ance");
            var midx = $(this).attr("mainIndex");
            var sidx = $(this).attr("subIndex");

            if(godTpCd != null && godTpCd == "SET_GOD" || godTpCd == "PCKAGE_GOD"){
                /* 패키지 상품 일 경우 구성 상품 여부를 확인하여 같은 사유 값 지정 */
                $('.clmResnCd').each(function (index){
                    if(ance == $(this).attr('ance')){
                        var midx2 = $(this).attr("mainIndex");
                        var sidx2 = $(this).attr("subIndex");

                        $('#lgsDlvspList'+midx2+'clmWrhsGodList'+sidx2+'clmResnCd').val(clmCd);
                    }
                });
            }

            /* 일반 및 세트 or 패키지 마스터 상품 일 경우 */
            $('#lgsDlvspList'+midx+'clmWrhsGodList'+sidx+'clmResnCd').val(clmCd);

            if($("input:checkbox[id='clmVal"+midx+sidx+"']").prop("checked") == false){
    			$("input:checkbox[id='clmVal"+midx+sidx+"']").click();
    		}
            
            if($('#clmVal'+midx+sidx).prop('checked')){
                inputCheck();
            }
        });

        $('.clmResnCont').focusout(function (){
            var godTpCd = $(this).attr("godTpCd");
            var ance = $(this).attr("ance");
            var midx = $(this).attr("mainIndex");
            var sidx = $(this).attr("subIndex");
            var val = $(this).val();

            if(godTpCd != null && godTpCd == "SET_GOD" || godTpCd == "PCKAGE_GOD"){
                /* 패키지 상품 일 경우 구성 상품 여부를 확인하여 같은 사유 값 지정 */
                $('.clmResnCd').each(function (index){
                    if(ance == $(this).attr('ance')){
                        var midx2 = $(this).attr("mainIndex");
                        var sidx2 = $(this).attr("subIndex");

                        $('#lgsDlvspList'+midx2+'clmWrhsGodList'+sidx2+"clmResnCont").val(val);
                    }
                });
            }
            /* 일반 및 세트 or 패키지 마스터 상품 일 경우 */
            $('#lgsDlvspList'+midx+'clmWrhsGodList'+sidx+"clmResnCont").val(val);
        });

    	function changeQty(midx, sidx, chg) {

    		var obj = $('#TR_' + midx + '_' + sidx);
    		var wrkQty = $(obj).attr("wrkQty");
    		
    		var qtyField = $('#lgsDlvspList'+midx+'clmWrhsGodList'+sidx+'clmQty');
    		
    		var qty = Number($(qtyField).val()) + Number(chg);
    		
    		<%-- 
    		if ("${prmTpCd}" == "ORD_DC") {
    			if (wrkQty != qty) {
    				alert("주문단위 할인이 존재합니다. 전체취소 하시기 바랍니다.");
    				$(qtyField).val(wrkQty);
    				return;
    			}
    		}
    		--%>
    		
    		setQty(obj, qty);
    		
    	}
    	
    	function setQty(obj, qty) {
    		
    		var godTpCd = $(obj).attr("godTpCd");
    		
    		var midx = $(obj).attr("mainIndex");
    		var sidx = $(obj).attr("subIndex");
    		
    		var wrkQty = $(obj).attr("wrkQty");
    		
    		var qtyField = $('#lgsDlvspList'+midx+'clmWrhsGodList'+sidx+'clmQty');
    		
    		if (qty < 1) {
    			$(qtyField).val(1);
    			return;
    		}
    		
    		if (Number(qty) > Number(wrkQty)) {
    			$(qtyField).val(wrkQty);
    			return;
    		}
    		
    		if(godTpCd != null && godTpCd == "SET_GOD" || godTpCd == "PCKAGE_GOD"){
    			/* 패키지 상품 일 경우 구성 상품 여부를 확인하여 같은 사유 값 지정 */
    			$('.clmQtyClass').each(function (index){
    				if(ance == $(obj).attr('ance')){
    					var midx2 = $(obj).attr("mainIndex");
    					var sidx2 = $(obj).attr("subIndex");

    					$('#lgsDlvspList'+midx2+'clmWrhsGodList'+sidx2+'clmQty').val(qty);
    				}
    			});
    		}
    		
    		var ordGodTurn = $(obj).attr("ordGodTurn");
    		
    		$(qtyField).val(qty);
    		
    		$('.GOD_GFT_' + ordGodTurn).each(function (index){	// 상품사은품 수량 처리
    			$(this).val(qty);
    		});
    		
    		if($('#clmVal'+midx+sidx).prop('checked')){
    			inputCheck();
    		}
    	}
    	
    	$('.clmQtyClass').change(function (){
    		var obj = $('#TR_' + $(this).attr("mainIndex") + '_' + $(this).attr("subIndex"));
    		setQty(obj, $(this).val());
    	});

        <%-- 체크박스 선택시 체크된 로우만 환불금 계산을 하기 위해 --%>
        function inputCheck() {
            
            if ($("input:checkbox[id^='clmVal']:checked").length > 0) {
                //반품접수총수량
                var wrkQtySum = 0;
                var ob = $(this);
                $(".wrkQtyClass").each(function (index, item) {
                    wrkQtySum += $(".wrkQtyClass").val() * 1;
                });
                var clmQtySum = 0;
                //반품접수총수량

                $(".clmQtyClass").each(function (index, item) {
                    clmQtySum += $(".clmQtyClass").val() * 1;
                });

                $("input:checkbox[id^='clmVal']").each(function (index, item) {
                    if ($(item).prop("checked")) {
                    	
                    	// 클레임 사유 선택 했을때는 값전송 가능하게 변경
                        $(item).nextAll('input:hidden').attr("disabled", false);
                        var ordGodTurn = $(item).nextAll("input:hidden[class='ordGodTurn']").val();

                        $(".ordGodGftTurn" + ordGodTurn).each(function (index, item) {

                            $(item).nextAll('input:hidden').attr("disabled", false);
                        });
                        
                    } else {
                        //체크 안된 체크 박스는 값전송 불가
                        $(item).nextAll('input:hidden').attr("disabled", true);
                        var ordGodTurn = $(item).nextAll("input:hidden[class='ordGodTurn']").val();
                        $(".ordGodGftTurn" + ordGodTurn).each(function (index, item) {
                            $(item).nextAll('input:hidden').attr("disabled", true);
                        });
                    }
                });
                
                $("input:checkbox[id^='clmVal']:checked").each(function (index, item) {
                    // 사유에 따른 배송비 계산
                    if ($(item).nextAll('input:hidden[id$=clmResnCd]').val() == "SIMPL_CHGMIND"
                            || $(item).nextAll('input:hidden[id$=clmResnCd]').val() == "OPT_CHG"
                            || $(item).nextAll('input:hidden[id$=clmResnCd]').val() == "QTY_MOD"
                    ) {
                        $("#clmStatCd").val("RCEPT");
                    } else {
                        $("#clmStatCd").val("REQST");
                    }
                });

                var strParams = $("#frmClaimInfo").serialize();

                $.ajax({
                    type: "POST",
                    url: "<c:url value='/mypage/claim/include/refundInfo'/>",
                    data: strParams,
                    beforeSend: function (request) {
                        var csrfToken = $("meta[name='_csrf']").attr("content");
                        var csrfName = $("meta[name='_csrf_header']").attr("content");
                        request.setRequestHeader(csrfName, csrfToken);
                        $("#acceptBtn").attr("disabled", true);
                    },
                    success: function (data) {
                    	
                    	$(".P_ORD_GFT").each(function(){
    						$(this).hide();
    					});
                    	
                        $("#refundInfoDiv").html(data);

                        var totalRtgodDlvCst = 0;
                        $('#refundInfoDiv').find($('input[name$="rtgodDlvCst"]')).each(function (index, item){
                            totalRtgodDlvCst += parseInt($(item).val());
                        });

                        $('#rtgodDlvCstClass').html(totalRtgodDlvCst);

                        var totalCnclDlvCst = 0;
                        $('#refundInfoDiv').find($('input[name$="cnclDlvCst"]')).each(function (index, item){
                            totalCnclDlvCst += parseInt($(item).val());
                        });

                        $('#cnclDlvCstClass').html(totalCnclDlvCst);                        

                        $("#acceptBtn").attr("disabled", false);
                    },
                    error: function (e) {
                        alert("조회 실패");
                        $("#acceptBtn").attr("disabled", false);
                    }

                });

            } else {
                $('#refundInfoDiv').html("");
            }

        }

        <%-- 클레임 사유 일괄적용--%>
    	function clmCheck(){
    		$('span[id^=selRsnTxt]').each(function(){
    			var str = $('span[id^=selRsnTxt]:first').html();
    			if($('#chkReason').prop("checked")){
    				$(this).html(str);
    			}
    		});
    		if($('#chkReason').prop("checked")){
    			
    			var resnCd = $("#lgsDlvspList0clmWrhsGodList0clmResnCd").val();
    			
    			$("input:checkbox[id^='clmVal']").each(function(index, item){
    				$(item).nextAll('input:hidden[id$=clmResnCd]').val(resnCd);
    			});
    			
    			var resnCont = $('.clmResnCont:first').val();
    			
    			$('input[id$=clmResnCont]').each(function(index, item){
    				$(item).val(resnCont);
    			});
    		}		
    	}

        <%-- 반품 신청 버튼 --%>
        function acceptRequestForReturn() {
        	
        	if ("${orderInfo.escrYn}" == "Y") {
        		
    	    	var isAll = true;
    	    	
    	    	$('input:checkbox[id^="clmVal"]').each(function(){
    	    		if ($(this).data('isview') == 'N') {{
    	    			return;
    	    		}}
    	    		
    	    		if ($(this).prop('checked') == false) {
    	    			isAll = false;
    	    		}
    	    		
    	    		var obj = $('#lgsDlvspList' + $(this).attr("mainIndex") + 'clmWrhsGodList' + $(this).attr("subIndex") + 'clmQty');
    	    		
    	    		if ($(obj).data('wrkqty') != $(obj).val()) {
    	    			isAll = false;
    	    		}
    	    	});
    	    	
    	    	if (isAll == false) {
    	    		alert('<spring:message code="mypage.js.claim.cancel.msg.impossible.partreturn"/>');
    	    		return;
    	    	}
        	}	
        
            var flag = false;
            var checked = 0;
            var dlvspChgCnt = 0;
            var dlvspListSize = parseInt(${dlvspList.size()});

            for (var i = 0 ; i < dlvspListSize ; i++){
                if($("input:checkbox[id^='clmVal"+i+"']:checked").length > 0){
                    checked++;
                    if($("input:hidden[name$='lgsDlvspList[" + i + "].dlvspChgYn']").val() == 'Y') {
                        dlvspChgCnt++;
                    }
                }
            }

            if(checked > 1 && dlvspChgCnt > 0){
                if(checked != dlvspChgCnt){
                    alert('<spring:message code="mypage.js.claim.return.msg.noselect.otherdelivery"/>');
                    return false;
                }
            }
    
            <%-- 매장픽업 주문이고, 수거지를 변경 안했을 경우 --%>
			if("${ordTpCd}" == "SHOP_PKUP_ORD"){
	    		if($('input[name="lgsDlvspList[0].dlvspChgYn"]').val() == 'N'){
					alert('<spring:message code="mypage.js.claim.return.msg.noselect.delivery"/>');
					return false;
				}
			}

            <%-- 무통장결제 || 핸드폰 결제에 결제달이 틀릴때--%>
            <c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}">
 			
 			if ($("#rfdAcnthldrNmOd").val() == "") {
                alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountname"/>');
                return;
            }

            if ($("#rfdBnkCd").val() == "") {
                alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.bank"/>');
                return;
            }

            if ($("#rfdBnkAcctNoOd").val() == "") {
                alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountno"/>');
                return;
            }
            </c:if>

            inputCheck();

            if ($("input:checkbox[id^='clmVal']:checked").length < 1) {
                alert('<spring:message code="mypage.js.claim.return.msg.noselect.goods"/>');
                return;
            }
           
            var resnCdflag = false;
            var resnContflag = false;
            
            if ($("input:checkbox[id^='clmVal']:checked").length > 0) {
            	
                $("input:checkbox[id^='clmVal']").each(function (index, item) {
                    if ($(item).prop("checked")) {
                    	
                        // 클레임사유 선택안했을때
                        if ($(item).nextAll('input:hidden[id$=clmResnCd]').val() == null || $(item).nextAll('input:hidden[id$=clmResnCd]').val() == "") {
                            resnCdflag = true;
                            
                        } else if($('#lgsDlvspList' + $(item).attr('mainIndex') + 'clmWrhsGodList' + $(item).attr('subIndex') + 'clmResnCont').val() == '') {
    						resnContflag = true;
                        }
                    } 
                });
                
                // 전체 선택시 클레임사유 선택안한 없는 경우가 발생할때
                if (resnCdflag) {
                    alert('<spring:message code="mypage.js.claim.return.msg.noselect.cause"/>');		// 반품사유를 선택하세요.
                    return false;
                }
                
                if(resnContflag){
    				alert('<spring:message code="mypage.js.claim.return.msg.noselect.detailcause"/>');	// 반품 상세 사유를 입력해주세요.
    				return false;
    			}
            }
            
            var confirm_msg = '<spring:message code="mypage.js.claim.return.msg.acceptreturn"/>' + "\n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>';
            if(parseInt($('#rtgodDlvCstClass').text()) > 0){
            	confirm_msg = MESSAGES['mypage.js.claim.return.msg.addpay.acceptreturn'];
            }

            if (!confirm(confirm_msg)) {
                return false;
            }

            <%-- 무통장결제 || 핸드폰 결제에 결제달이 틀릴때--%>
            <c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}">
            <%-- 환불계좌값 --%>
				$("#rfdAcnthldrNm").val($("#rfdAcnthldrNmOd").val());
		    	$("#rfdBnkAcctNo").val($("#rfdBnkAcctNoOd").val());
            </c:if>
            
            var strParams = $("#frmClaimInfo").serialize();
            $('.loading').show();
            
            $.ajax({
                type: "POST",
                url: "<c:url value='/mypage/claim/return/request.json'/>",
                data: strParams,
                beforeSend: function (request) {
                    var csrfToken = $("meta[name='_csrf']").attr("content");
                    var csrfName = $("meta[name='_csrf_header']").attr("content");
                    request.setRequestHeader(csrfName, csrfToken);
                    $("#acceptBtn").attr("disabled", true);
                    $("#cancelBtn").attr("disabled", true);
                },

                success: function (data) {
                    alert('<spring:message code="mypage.js.claim.return.msg.complete"/>');
                    var strParams = null;
                    strParams = {'${_csrf.parameterName}': '${_csrf.token}'};

                    if ($("#mainPayRefundAmount").val() < 0) {

				 		if("${nmbrYn}"!="Y"){
					 		location.href="<c:url value='/mypage/claim/list'/>";
					 	}else{
					 		location.href="<c:url value='/mypage/claim/list'/>";
					 	}
				 		
				 		if(!confirm('<spring:message code="mypage.js.claim.cancel.msg.delivery.addpay" arguments="' + $('#mainPayRefundAmount').val() * -1 + '"/>' + "\n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>')) {
							return false;
						}

					 	getPayMethodChangePop('<c:url value='/secured/order/payMethodChangePop'/>',{'ordNo' : '${ordNo}' , 'type' : 'clmDlvAmtPay', 'clmNo' : data.clmNo});

			    	} else {
                    if ("${nmbrYn}" != "Y") {
                        location.href = "<c:url value='/mypage/claim/list'/>";
                    } else {
                        location.href = "<c:url value='/mypage/order/${orderInfo.ordNo }/view'/>";
                    }
			    	}
                },
                error: function (xhr, sataus, e) {
                    $('.loading').hide();
                    alert(xhr.responseJSON.message);
                    $("#acceptBtn").attr("disabled", false);
                    $("#cancelBtn").attr("disabled", false);
                }
            });

        }

        <%-- 환불계좌 셀렉트 박스 변경시--%>
        function chgBnkType(code, obj) {
            $("#" + obj).val(code);
        }

        <%-- 숫자입력 방지 --%>
        function check() {
            var str = $("#rfdAcnthldrNm").val();

            for (var i = 0; i < str.length; i++) {
                if (str.charCodeAt(i) >= 48 && str.charCodeAt(i) <= 57 || (str.charCodeAt(i) > 32 && str.charCodeAt(i) < 48) || (str.charCodeAt(i) > 57 && str.charCodeAt(i) < 65) || (str.charCodeAt(i) > 90 && str.charCodeAt(i) < 97)) {
                    $("#rfdAcnthldrNm").val("");
                    return false;
                }
            }
        }

        function goOrderList(strNmbrYn) {
    	 	if(strNmbrYn!="Y"){
    	 		location.href="<c:url value='/mypage/order/list'/>";
    	 	}else{
    	 		location.href="<c:url value='/mypage/order/${orderInfo.ordNo }/view'/>";
    	 	}
    	}

        function refundChange() {
            $("#refundCheckYn").val("N");
        }

        function addComma(n) {
            var reg = /(^[+-]?\d+)(\d{3})/;
            n += '';

            while (reg.test(n)) {
                n = n.replace(reg, '$1' + ',' + '$2');
            }

            return n;
        }

    	function allSelect(obj) {
    		$("input:checkbox[id^='clmVal']").each(function(index, item){
    			if ($(item).attr("godtpcd") != 'PCHS_GFT' && $(item).attr("godtpcd") != 'CNVRS_GFT') {
    				$(item).prop("checked", obj.prop("checked"));	
    			}
    		});

    		inputCheck();
    	}
    	
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

        $("input:checkbox[id^='clmVal']").click(function () {
            var ance = $(this).attr('ance');
			var checkThis = $(this);
            if (ance) {
				$("input:checkbox[id^='clmVal']").each(function(index){
					var tmpThis = $(this).closest(".checkWrap");
					if(checkThis.prop("checked")){
						if ($(this).attr('ance') == ance) {
							$(this).prop("checked", true);
						}
					} else {
                        if ($(this).attr('ance') == ance) {
                            $(this).prop("checked", false);
                        }

					}
				});
			}
            
            inputCheck();
        });
        
     // 매장찾기
     	function showPopupShop(shopId){

     		var commonMap = new google.maps.Map(document.getElementById("common_shopMap"), {
     		    zoom: 16,
     		    center: {lat:37.532, lng:127.024}, //대한민국
     		});
     		
     		$.ajax({
     			type : "POST",
     			async : false,
     			url : "/common/shop/view.json",
     			data : {'${_csrf.parameterName}' : '${_csrf.token}', shopId:shopId}, 
     			dataType : "JSON",
     			success : function(data) {
     				if(data.sysShopExtends != null){
     					var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd;
    					shopNm = data.sysShopExtends.shopNm;
    					baseAddr = data.sysShopExtends.baseAddr;
    					telNo1 = data.sysShopExtends.shopTelAreaNo;
    					telNo2 = data.sysShopExtends.shopTelTlofNo;
    					telNo3 = data.sysShopExtends.shopTelTlofWthnNo;
    					shour = data.sysShopExtends.bsnBegHhmm;
    					ehour = data.sysShopExtends.bsnEndHhmm;
    					lttd = data.sysShopExtends.lttd;
    					lgtd = data.sysShopExtends.lgtd;
    					if(typeof(shopNm) === "undefined"){shopNm = "";}
    					if(typeof(baseAddr) === "undefined"){baseAddr = "";}
    					if(typeof(telNo1) === "undefined" || typeof(telNo2) === "undefined" || typeof(telNo3) === "undefined"){telNo = "";}else{telNo=telNo1+"-"+telNo2+"-"+telNo3;}					
    					if(typeof(shour) === "undefined" || typeof(ehour) === "undefined"){hour = "";}else{hour=shour+"~"+ehour;}					
    					$("#common_shopNm").text(shopNm);
    					$("#map-view-address").append(baseAddr);
    					$("#map-view-tel").append(telNo);
    					$("#map-view-time").append(hour);
    					//console.log(lttd + " : " + lgtd);
    					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){	
    						var marker = new google.maps.Marker({
    						    position: {lat: lttd, lng: lgtd},
    						    map: commonMap,
    						    title:shopNm
    						});
    						commonMap.setCenter(new google.maps.LatLng(lttd, lgtd));
    					}
    					layerPopup.popupOpenNow("#layerPopupSelectShopMap");
     				}else{
     					alert("<spring:message code='order.popup.store.msg.alert1'/>");
     					//매장 조회 시 에러 발생하였습니다.
     				}
     			},
     			error: function( pa_data, status, err ) {
     	            alert("error forward : "+err+" ,status="+status);
     	        }
     		});		
     	} 	
     
     	$(".numberOnly").keyup(function (e) {
    		if (!(e.keyCode >=37 && e.keyCode<=40)) {
    			var inputVal = $(this).val();
    			$(this).val(inputVal.replace(/[^0-9]/gi,''));
    		}
        });
    </script>