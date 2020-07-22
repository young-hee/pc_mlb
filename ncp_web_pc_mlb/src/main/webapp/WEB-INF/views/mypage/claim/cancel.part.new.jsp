<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>

	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">
		<form name="frmClaimInfo"   id="frmClaimInfo"   method="post"       action="/" >
			<input type="hidden" id="${_csrf.parameterName}"        name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="hidden" id="ordNo"                         name="ordNo"                  value="${orderInfo.ordNo}"/>
			<input type="hidden" id="clmTpCd"        				name="clmTpCd"      	        value="PART_CNCL" />
			<input type="hidden" id="chkPgIfYn"                     name="pgIfYn"                   value="N">
			<input type="hidden" id="rfdBnkCd"                      name="rfdBnkCd" 				value="" />	<%-- 환불계좌 은행코드 --%>
			<input type="hidden" id="rfdAcnthldrNm"                 name="rfdAcnthldrNm"            value=""/>	<%-- 환불계좌 예금주명  --%>
			<input type="hidden" id="rfdBnkAcctNo"                  name="rfdBnkAcctNo"             value=""/>	<%-- 환불계좌 번호  --%>
			<input type="hidden" id="refundCheckYn"                 name="refundCheckYn"            value="N"/>  <%-- 환불계좌 인증 여부 --%>	

			<h2 class="title01"><spring:message code="mypage.order.cancel.ttl" /></h2>
			
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>  
				
				<!-- 취소상품 선택  -->
				<div class="orderInfoCon">	
					
					<!-- order Result -->
					<div class="odSearchResult odCancel">				
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst  -->
							<div class="odResulBox">
								<div class="orderNb">
									<span><ncp:code code='${orderInfo.ordTpCd}'/></span>
	                              	<span><em><spring:message code="mypage.order.list.lbl.orderdate" /></em> <c:out value='${orderInfo.ordDt}'/></span>
	                              	<span><em><spring:message code="mypage.order.list.lbl.orderno" /></em> <a href="#" onclick="mypageorder.goOrderView('<c:out value="${orderInfo.ordNo}"/>');"><c:out value='${orderInfo.ordNo}'/></a></span>
								</div>	
								
								<h3><spring:message code="mypage.order.cancel.select" /></h3><!--  2018.12.06 위치 수정 -->								
								<table class="board-list orderTable">
		                          <colgroup>
		                                <col style="width:15px">
                                        <col style="width:">
                                        <col style="width:140px">
                                        <col style="width:144px">
		                          </colgroup>	   
		                          <thead>
		                          	<tr>
		                          		<th> 
		                          			<span class="check-skin">
                                                <input type="checkbox" id="chkReply" onchange="allSelect($(this));">
                                                <span><spring:message code="mypage.order.btn.select" /></span>
                                            </span>
                                        </th>
		                          		<th colspan="2"><spring:message code="mypage.claim.cancel.goodsinfo" />/<spring:message code="mypage.claim.cancel.option" /></th>
		                          		<th><spring:message code="mypage.claim.cancel.cancelqty" /></th>
		                          	</tr>
		                          </thead>       
		                          <tbody>
		                          	<c:forEach var="ordGodForRtnClmResult" items="${ordGodForRtnClmResult}" varStatus="mainSts">
		                          		<input type="hidden" name="lgsDlvspList[${mainSts.index}].ordNo" value="${orderInfo.ordNo }"/>
										<input type="hidden" name="lgsDlvspList[${mainSts.index}].dlvPcupspTurn" value="${ordGodForRtnClmResult.dlvPcupspTurn}" >
										<c:forEach var="ordGod" items="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList}" varStatus="subSts">
											<c:if test="${ordGod.wrkQty > 0}">
		                          				<c:choose>
		                          					<%-- 상품 사은품 --%>
														<c:when test="${(ordGod.godTpCd eq 'PCHS_GFT' or ordGod.godTpCd eq 'CNVRS_GFT') and ordGod.prmDtlTpCd eq 'GOD_GFT'}">
															<tr>	                                	  
						                                	  <td>
					                                            <span class="check-skin" style="display:none;">
					                                                <input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" style="display:none;" mainIndex="${mainSts.index}" subIndex="${subSts.index}" godTpCd="${ordGod.godTpCd}" data-isview="N">
					                                                <input type="hidden" value="1" class="dlvGodCnt"/>
					                                                <!-- 주문상품유형 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																	<!-- 주문상품순번 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																	<!-- 출고지시상품순번 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																	<!-- 배송순번 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																	<input type="hidden"  id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" value="CSTMR_CHGMIND_CNCL" class="clmResnCdClass${ordGod.ordGodGftTurn}"/>
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value=""/>
					                                                <span><spring:message code="mypage.order.btn.select" /></span>
					                                            </span>
					                                          </td>                                          
							                                  <td class="tleft">
							                                     <div class="product-info product-add-set">
					                                                   <div class="product-info-img"><a href="javascript:;"><<img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
					                                                   <div class="product-info-text">
					                                                       <div class="product-info-box">
					                                                           <div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.gift" text="사은품"/>]</span></div>
					                                                           <p class="product-name"><a href="javascript:;">${ordGod.godNm}</a></p>
					                                                           <div class="product-price">
					                                                               <span></span>
					                                                           </div>
					                                                       </div>
					                                                       <div class="product-option">
					                                                           <span></span>
					                                                       </div>
					                                                   </div>
					                                               </div>
							                                  </td>
							                                  <td></td>
							                                  <td></td>
							                               </tr>
														</c:when>
														<%-- 주문 사은품 --%>
														<c:when test="${(ordGod.godTpCd eq 'PCHS_GFT' or ordGod.godTpCd eq 'CNVRS_GFT') and ordGod.prmDtlTpCd eq 'ORD_GFT'}">
															<tr>
						                                	  <td>
						                                	  	<input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" style="display:none;" mainIndex="${mainSts.index}" subIndex="${subSts.index}" godTpCd="${ordGod.godTpCd}" data-isview="N"/>
						                                	  	<input type="hidden" value="1" class="dlvGodCnt"/>
																<!-- 주문상품유형 -->
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																<!-- 주문상품순번 -->
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" />
																<!-- 출고지시상품순번 -->
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																<!-- 배송순번 -->
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																<input type="hidden"  id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" value="CSTMR_CHGMIND_CNCL" class="clmResnCdClass${ordGod.ordGodGftTurn}"/>
																<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value="" />
																
						                                	  </td>
							                                  <td class="tleft">
							                                     <div class="product-info product-free-gift">
					                                                   <div class="product-info-img"><a href="javascript:;"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
					                                                   <div class="product-info-text">
					                                                       <div class="product-info-box">
					                                                           <div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.gift" />]</span></div>
					                                                           <p class="product-name"><a href="javascript:;">${ordGod.godNm}</a></p>
					                                                           <div class="product-price">
					                                                               <span></span>
					                                                           </div>
					                                                       </div>
					                                                       <div class="product-option">
					                                                           <span></span>
					                                                       </div>
					                                                   </div>
					                                               </div>
							                                  </td>
							                                  <td></td>
							                                  <td></td>
							                               </tr>
														</c:when>
														<%-- SET_GOD:세트상품, PCKAGE_GOD:패키지 상품 일 경우 --%>
														<c:when test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}">
															<tr style="display: none;">
														   	 <td>
					                                            <span class="check-skin">
					                                                <input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" mainIndex="${mainSts.index}" subIndex="${subSts.index}" godTpCd="${ordGod.godTpCd}" data-isview="N"/>
					                                                <%-- 주문 번호 --%>
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordNo" value="${orderInfo.ordNo }"/>
																	<!-- 입점 구분 코드 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}"/>
																	<!-- 국내 배송비 정책 일련번호 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}"/>
																	<!-- 국내 배송비 정책 일련번호 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}"/>
																	<%-- 클레임 사유코드 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" class="clmResnCd" ance="${ordGod.ordGodTurnAnce}" mainIndex="${mainSts.index}" subIndex="${subSts.index}" value="CSTMR_CHGMIND_CNCL"/>
																	<%-- 주문상품유형 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																	<%-- 주문상품순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" class="ordGodTurn"/>
																	<%-- 출고지시상품순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																	<!-- 상품번호 -->
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godNo" value="${ordGod.godNo}"/>
																	<%-- 배송순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																	<%--취소수량 선택값히든 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty"  value="${ordGod.wrkQty}" class="clmQtyClass"
																		   <c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if> mainIndex="${mainSts.index}" subIndex="${subSts.index}" />
																	<%-- 취소 기타 사유 --%>
																	<%-- <input type="hidden" class="inp_txt"
																			   id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCont"
																			   name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont"
																			   godTpCd="${ordGod.godTpCd}" <c:if test="${ordGod.pckageGodTpCd eq 'SET_GOD' || ordGod.pckageGodTpCd eq 'PCKAGE_GOD'}"> ance="${ordGod.ordGodTurnAnce}"</c:if>  mainIndex="${mainSts.index}" subIndex="${subSts.index}"
																			   maxlength="" value="" disabled="disabled"> --%>
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value="" />
					                                                <span><spring:message code="mypage.order.btn.select" /></span>
					                                            </span>
					                                          </td>
							                                  <td class="tleft">
						                                     	<div class="product-info">
					                                                   <div class="product-info-img"><a href="javascript:;"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
					                                                   <div class="product-info-text">
					                                                       <div class="product-info-box">
					                                                           <p class="product-name"><a href="javascript:;">${ordGod.godNm}</a></p>
					                                                           <div class="product-price">
					                                                               <span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></span>
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
					                                          </td>
							                               </tr>
														</c:when>
														<c:otherwise><%-- 위 상품구분외 모든 경우 --%>
															<tr id="TR_${mainSts.index}_${subSts.index}"
																ordGodTurn="${ordGod.ordGodTurn}" 
																godTpCd="${ordGod.godTpCd}" 
																ance="${ordGod.ordGodTurnAnce}" 
																mainIndex="${mainSts.index}" 
																subIndex="${subSts.index}" 
																wrkQty="${ordGod.wrkQty}">
							                             	 <td>
					                                            <span class="check-skin">
					                                                <input type="checkbox" id="clmVal${mainSts.index}${subSts.index}" name="anceCheck${dlvSts.index}" value="" ance="${ordGod.ordGodTurnAnce}" mainIndex="${mainSts.index}" subIndex="${subSts.index}" godTpCd="${ordGod.godTpCd}" data-isview="Y">
					                                                <span><spring:message code="mypage.order.btn.select" /></span>
					                                                
					                                                <%-- 주문 번호 --%>
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordNo" value="${orderInfo.ordNo }"/>
																	<!-- 입점 구분 코드 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].partmalSectCd" value="${ordGod.partmalSectCd}"/>
																	<!-- 국내 배송비 정책 일련번호 -->
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dmstcDlvCstPlcSn" value="${ordGod.dmstcDlvCstPlcSn}"/>
																	<!-- ncp2 -->
			
																	<%-- 클레임 사유코드 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmResnCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCd" class="clmResnCd"
																		   ance="${ordGod.ordGodTurnAnce}" mainIndex="${mainSts.index}" subIndex="${subSts.index}" value="CSTMR_CHGMIND_CNCL"/>
																	<input type="hidden" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmResnCont" value="" />
																	<%-- 주문상품유형 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}godTpCd" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].godTpCd" value="${ordGod.godTpCd}" />
																	<%-- 주문상품순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}ordGodTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].ordGodTurn" value="${ordGod.ordGodTurn}" class="ordGodTurn"/>
																	<%-- 출고지시상품순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlivyDrctGodNo" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlivyDrctGodNo" value="${ordGod.dlivyDrctGodNo}" />
																	<%-- 배송순번 --%>
																	<input type="hidden" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}dlvTurn" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].dlvTurn" value="${ordGod.dlvTurn}" />
																	
					                                               
					                                            </span>
					                                          </td>
							                                  <td class="tleft">
							                                      <div class="product-info">
							                                          <div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${ordGod.godNo}');"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
							                                          <div class="product-info-text">
							                                              <div class="product-info-box">
							                                              	  <c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">
																				<div class="product-more-info">
																					<span class="text-color01">[<spring:message code="mypage.order.list.lbl.addcomposition" text="추가구성"/>]</span>
																				</div>
																			  </c:if>
							                                                  <p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${ordGod.godNo}');" >${ordGod.godNm}</a></p>
							                                                  <div class="product-price">
							                                                      <span><fmt:formatNumber value="${ordGod.foSaleamt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></span>
							                                                  </div>
							                                              </div>
							                                              <div class="product-option">
							                                                  <c:choose>
																				<c:when test="${ordGod.godTpCd eq 'SET_GOD' || ordGod.godTpCd eq 'PCKAGE_GOD'}">
																					<span>
																						<c:set var="isFirstPkg" value="true" />
																						<c:forEach var="optionList" items="${ordGodForRtnClmResult.ordGodForRtnClmDetailResultList}" varStatus="optStatus">
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
																					<span>${ordGod.colorCd} / ${ordGod.itmNm}</span><br/>
																				</c:otherwise>
																			</c:choose>
							                                              </div>
							                                          </div>
							                                      </div>
							                                  </td>
							                                  <td>${ordGod.wrkQty}</td>
							                                  <td>
					                                             <div class="quantity-wrap">
					                                                 <button type="button" class="pq-minus" onclick="changeQty('${mainSts.index}', '${subSts.index}', -1);"><spring:message code="mypage.claim.btn.subtract" text="빼기"/></button>
					                                                 <button type="button" class="pq-plus" onclick="changeQty('${mainSts.index}', '${subSts.index}', +1);"><spring:message code="mypage.claim.btn.addtion" text="추가"/></button>
					                                                 <input type="number" class="pdNumber clmQtyClass" maxlength="5" id="lgsDlvspList${mainSts.index}clmWrhsGodList${subSts.index}clmQty" name="lgsDlvspList[${mainSts.index}].clmWrhsGodList[${subSts.index}].clmQty"  value="${ordGod.wrkQty}" data-wrkqty="${ordGod.wrkQty}" mainIndex="${mainSts.index}" subIndex="${subSts.index}"/>
					                                             </div>                                             
					                                          </td>
							                               </tr>
														</c:otherwise>
													</c:choose>
												</c:if>
											</c:forEach>
										</c:forEach>			
									</tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst  -->     
                         	                 
                        </div>
                        <!--  //order Result List -->    
                        
                        <div class="mgInfoBox" id="refundInfoDiv">
						</div>
                        
                        <!--  환불계좌  -->
                        <c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}">
                        <div class="mgInfoBox">
                        	<h3><spring:message code="mypage.claim.lbl.refund.account" /></h3>
                        	<table class="tbInputList">
                        		<caption><spring:message code="mypage.claim.lbl.refund.account" /></caption>
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
												<button type="button" id="labelRefund" class="d_select_sel" style="width:410px;"><span><spring:message code="mypage.order.lbl.select" /></span></button>
												<ul>
													<li><a hrf="#none" onclick="javascript:chgBnkType('rfdBnkCd','${clm.cd}'); false;"><spring:message code="mypage.order.lbl.select" text="선택하세요"/></a></li>
													<ncp:codes group="BNK" var="bnk"/>
										            <c:forEach var="bk" items="${bnk}">
										            	<c:if test="${not empty bk.asstnCd1}">
															<li><a href="#none" onclick="javascript:chgBnkType('rfdBnkCd', '${bk.cd}'); false;">${bk.cdNm}</a></li>
														</c:if>
										            </c:forEach>													
												</ul>
											</div>
											<!-- //select -->		                          		
		                          		</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th><label for="bankTo"><spring:message code="mypage.claim.lbl.refund.accountname" /></label><span class="required">*</span></th>
		                          		<td><input type="text" id="rfdAcnthldrNmOd" class="input-style01 textOnly" style="width:410px;" alt="은행명" maxlength="100"></td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th><label for="bankNumber"><spring:message code="mypage.claim.lbl.refund.accountno" text="계좌번호 "/></label><span class="required">*</span></th>
		                          		<td><input type="text" id="rfdBnkAcctNoOd" class="input-style01 numberOnly" style="width:410px;" alt="계좌번호" maxlength="100" placeholder="<spring:message code='mypage.claim.lbl.refund.accountno.placeholder' />"></td>		                          		
		                          	</tr>
	                          	</tbody>
	                        </table>
                        </div>
                        </c:if>
                        <!--  //환불계좌  -->
                        
                        <div class="btnWrapBox">
							<a href="#none" class="btn" onclick="javascript:history.back();"><spring:message code="mypage.order.btn.cancel" /></a>
							<a href="#none" class="btn fill" onclick="acceptUnitCancel();"><spring:message code="mypage.claim.btn.ordercancel" text="주문취소"/></a>
						</div>  
					</div>		
					<!-- //order Result -->		
					
					<ul class="text-list02">
						<li><spring:message code="mypage.claim.cancel.lbl.guide.txt2" /></li>
						<li><spring:message code="mypage.claim.cancel.lbl.guide.txt3" /></li>
						<li><spring:message code="mypage.claim.cancel.lbl.guide.txt4" /></li>
						<li><spring:message code="mypage.claim.cancel.lbl.guide.txt5" /></li>
					</ul>	
					
				</div>		
				<!--  // 취소상품 선택   -->	
				

			</main>
		</form>	
		</div>
	</div>
	<!--// 컨텐츠 끝 -->


<script>

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

		setQty(obj, qty);
		
	}
       
	function setQty(obj, qty) {
		
		var godTpCd = $(obj).attr("godTpCd");
		
		var ance = $(obj).attr("ance");
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
			
			var ance = 
			/* 패키지 상품 일 경우 구성 상품 여부를 확인하여 같은 사유 값 지정 */
			$('.clmQtyClass').each(function (index){
				if(ance == $(this).attr('ance')){
					var midx2 = $(this).attr("mainIndex");
					var sidx2 = $(this).attr("subIndex");

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
	
	function allSelect(obj) {
		$("input:checkbox[id^='clmVal']").each(function(index, item){
			
			var godTpCd = $(item).attr("godTpCd");

			if (godTpCd == 'PCHS_GFT' || godTpCd == 'CNVRS_GFT') {
				return;
			}
			
			$(item).prop("checked", obj.prop("checked"));
		});

		inputCheck();
	}

	<%-- 체크박스 선택시 체크된 로우만 환불금 계산을 하기 위해 --%>
	function inputCheck(){

		if($('input:checkbox[id^="clmVal"]:checked').length > 0){
			
			$("input:checkbox[id^='clmVal']").each(function(index, item){
				
				if($(item).prop("checked")){
					// 클레임 사유 선택 했을때는 값전송 가능하게 변경
					$(item).nextAll('input:hidden').attr("disabled",false);
				    var ordGodTurn = $(item).nextAll("input:hidden[class='ordGodTurn']").val();
				    
				    $(".ordGodGftTurn"+ordGodTurn).each(function(index, item){
				    	$(item).nextAll('input:hidden').attr("disabled",false);
				    });
				}else{
					//체크 안된 체크 박스는 값전송 불가
					$(item).nextAll('input:hidden').attr("disabled",true);
					 var ordGodTurn = $(item).nextAll("input:hidden[class='ordGodTurn']").val();
					 $(".ordGodGftTurn"+ordGodTurn).each(function(index, item){
						 
				    	$(item).nextAll('input:hidden').attr("disabled",true);
				    });
				}
			});

	    	var strParams = $("#frmClaimInfo").serialize(); 
	    	
 			$.ajax({
				type : "POST",
				url     : "<c:url value='/mypage/claim/include/refundInfo'/>",
				data : strParams,
				beforeSend: function (request)
	            {
	              var csrfToken = $("meta[name='_csrf']").attr("content");
	              var csrfName = $("meta[name='_csrf_header']").attr("content");
	              request.setRequestHeader(csrfName, csrfToken);
	              $("#acceptBtn").attr("disabled" , true);
	            },
				success : function(data) {
					
					$(".P_ORD_GFT").each(function(){
						$(this).hide();
					});
					
					$("#refundInfoDiv").html(data);
					$("#acceptBtn").attr("disabled" , false);
				},
				error : function(jqXHR, textStatus, errorThrown) {
						alert("조회 실패");
						$("#acceptBtn").attr("disabled", false);
				}				
			});
 			
		}else{
			$('#refundInfoDiv').html("");
			$(".P_ORD_GFT").each(function(){
				$(this).hide();
			});
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
	
	<%-- 주문취소 신청 버튼 --%>
    function acceptUnitCancel() {
    	
		if ("${prmTpCd}" == "ORD_DC" || "${orderInfo.escrYn}" == "Y") {
    		
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
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.impossible.partcancel"/>');
	    		return;
	    	}
    	}
    	
    	if (inputCheck() == false) {
    		return;
    	}
    	
		var strParams = $("#frmClaimInfo").serialize();
    	
		if($('input:checkbox[id^="clmVal"]:checked').length < 1){
			alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.goods"/>');
			return;
		}
       	
		var resnCdflag = false;
        var resnContflag = false;
        
        if ($("input:checkbox[id^='clmVal']:checked").length > 0) {
        	
            $("input:checkbox[id^='clmVal']").each(function (index, item) {
                if ($(item).prop("checked")) {
                	
                    // 클레임사유 선택안했을때
                    if ($(item).nextAll('input:hidden[id$=clmResnCd]').val() == null || $(item).nextAll('input:hidden[id$=clmResnCd]').val() == "") {

                    	//$(item).prop("checked", false);
                        resnCdflag = true;
                        
                    } else if($('#lgsDlvspList' + $(item).attr('mainIndex') + 'clmWrhsGodList' + $(item).attr('subIndex') + 'clmResnCont').val() == '') {

                    	//$(item).prop("checked",false);
						resnContflag = true;
						
                    }
                    
                } 
            });
            
            // 전체 선택시 클레임사유 선택안한 없는 경우가 발생할때
            if (resnCdflag) {
                alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.cause"/>');		// 취소사유를 선택하세요.
                return false;
            }
            
            if(resnContflag){
				alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.detailcause"/>');	// 취소 상세 사유를 입력해주세요.
				return false;
			}
            
        }
        
		<%-- 무통장결제 || 핸드폰 결제에 결제달이 틀릴때--%>	
		<c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}"> 
			if($("#rfdAcnthldrNmOd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountname"/>');
	    		return;
	    	}
	    	
	    	if($("#rfdBnkCd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.bank"/>');
	    		return;
	    	}
	
	    	if($("#rfdBnkAcctNoOd").val() == "") {
	    		alert('<spring:message code="mypage.js.claim.cancel.msg.noselect.accountno"/>');
	    		return;
	    	}
    	</c:if>
		
    	if(!confirm('<spring:message code="mypage.js.claim.cancel.msg.acceptcancel"/>' + "\n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>')) {
			return false;
		}
    	
		<%-- 무통장결제 || 핸드폰 결제에 결제달이 틀릴때--%>	
		<c:if test="${mobilPonPayRfd.rfdYn eq 'Y'}"> 			
			<%-- 환불계좌값 --%>
	    	$("input[name=rfdAcnthldrNm]").val($("#rfdAcnthldrNmOd").val());
	    	$("input[name=rfdBnkAcctNo]").val($("#rfdBnkAcctNoOd").val());
    	</c:if>
    	
    	var strParams = $("#frmClaimInfo").serialize();
    	$('.loading').show();

    	// TODO : 후속처리 수정 필요
			$.ajax({
			type : "POST",
			url     : "<c:url value='/mypage/claim/cancle/part/request.json'/>",
			data : strParams,
			beforeSend: function (request)
            {
              var csrfToken = $("meta[name='_csrf']").attr("content");
              var csrfName = $("meta[name='_csrf_header']").attr("content");
              request.setRequestHeader(csrfName, csrfToken);
              $("#acceptBtn").attr("disabled" , true);
              $("#cancelBtn").attr("disabled" , true);
              
            },
			success : function(data) {
				alert('<spring:message code="mypage.js.claim.cancel.msg.complete"/>');
				var strParams = null;
			 	strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};  
			 	//jsLinkUrlPost("<c:url value='/secured/mypage/listOrder'/>",strParams);	

			 	if ($("#mainPayRefundAmount").val() < 0) {

			 		if("${nmbrYn}"=="Y"){
				 		location.href="<c:url value='/'/>";
				 	}else{
				 		location.href="<c:url value='/mypage/claim/list'/>";
				 	}

		    		if(!confirm('<spring:message code="mypage.js.claim.cancel.msg.delivery.addpay" arguments="' + $('#mainPayRefundAmount').val() * -1 + '"/>' + "\n" + '<spring:message code="mypage.js.claim.cancel.msg.continue"/>')) {
						return false;
					}
					// TODO : 추가결제...
				 	//getPayMethodChangePop('<c:url value='/secured/order/payMethodChangePop'/>',{'ordNo' : '${ordNo}' , 'type' : 'clmDlvAmtPay', 'clmNo' : data.result});

		    	} else {
				 	if("${nmbrYn}"=="Y"){
				 		location.href="<c:url value='/'/>";
				 	}else{
				 		location.href="<c:url value='/mypage/claim/list'/>";
				 	}
				}

			},
			error : function(jqXHR, textStatus, error) {
				$('.loading').hide();
				alert(jqXHR.responseJSON.message);
				$("#acceptBtn").attr("disabled" , false);
				$("#cancelBtn").attr("disabled" , false);
		    }				
		});	    	
    	
    }		
	
	<%-- 환불계좌 셀렉트 박스 변경시--%>		
	function chgBnkType(obj, code){
		$("#"+obj).val(code);
	}	
	
	<%-- 숫자입력 방지 --%>
	function check(){
		var str = $("#rfdAcnthldrNm").val();
	 
		for(var i=0; i<str.length; i++){
			if(str.charCodeAt(i) >= 48 && str.charCodeAt(i) <=57 || (str.charCodeAt(i) > 32 && str.charCodeAt(i) < 48) || (str.charCodeAt(i) > 57 && str.charCodeAt(i) < 65) || (str.charCodeAt(i) > 90 && str.charCodeAt(i) < 97)){
				$("#rfdAcnthldrNm").val("");
				return false;
			}	 
		}
	}
	
	function goOrderList(strNmbrYn) {
	 	if(strNmbrYn!="Y"){
	 		location.href="<c:url value='/mypage/order/list'/>";
	 	}else{
	 		location.href="<c:url value='/mypage/order/${orderInfo.ordNo}/view'/>";
	 	}
	 	
	}
	
	function refundChange() {
	  	$("#refundCheckYn").val("N");
	}

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
	
	$(".numberOnly").keyup(function (e) {
		if (!(e.keyCode >=37 && e.keyCode<=40)) {
			var inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^0-9]/gi,''));
		}
    });
</script>
