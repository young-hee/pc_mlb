<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ include file="/WEB-INF/views/common/layerpop/shop.view.jsp"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js?v=${_version}"></script>
<script type="text/javascript" src="<ncp:prop key="ncp_base.order.kcp.js.url"/>"></script>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.pay.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js?v=${_version}"></script>

	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">
				<c:if test="${myPage.clmYn ne 'Y'}">
					<spring:message code="mypage.order.detail.ttl" text="주문/배송상세"/>
				</c:if>
				<c:if test="${myPage.clmYn eq 'Y'}">
					<%-- <spring:message code="mypage.claim.detail.ttl" text="취소/교환/반품"/> --%>
					<c:if test="${myPage.clmTpCdSearch eq 'PART_CNCL' || myPage.clmTpCdSearch eq 'ALL_CNCL'}">
						<spring:message code="mypage.claim.detail.ttl.cancel" text="취소"/>
					</c:if>
					<c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">
						<spring:message code="mypage.claim.detail.ttl.exchange" text="교환"/>
					</c:if>
					<c:if test="${myPage.clmTpCdSearch eq 'RTGOD'}">
						<spring:message code="mypage.claim.detail.ttl.return" text="반품"/>
					</c:if>
				</c:if>
			</h2>
			
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<!-- 일반주문 상세  -->
				<div class="orderInfoCon">
					<div class="odSearchResult odPdModify odDetail">			
					
						<!-- 주문번호, 상품 리스트 -->
						<div class="odResulCon">	
							<div class="odResulBox">							
						
								<!-- 주문정보 -->
								<div class="orderNb <c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">lineBox</c:if>">
									<span>
										<c:if test="${myPage.clmYn ne 'Y'}">
											<c:choose>
												<c:when test="${orderInfo.ordTpCd  eq 'SHOP_PKUP_ORD'}">
													<spring:message code="mypage.order.list.lbl.shoporder" text="매장주문"/>
												</c:when>
												<c:otherwise>
													<ncp:code code='${orderInfo.ordTpCd}'/>
												</c:otherwise>
											</c:choose>
										</c:if>
										<c:if test="${myPage.clmYn eq 'Y'}">
											<c:if test="${myPage.clmTpCdSearch eq 'PART_CNCL' || myPage.clmTpCdSearch eq 'ALL_CNCL'}">
												<spring:message code="mypage.claim.list.lbl.cancel" text="취소"/>
											</c:if>
											<c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">
												<spring:message code="mypage.claim.list.lbl.exchange" text="교환"/>
											</c:if>
											<c:if test="${myPage.clmTpCdSearch eq 'RTGOD'}">
												<spring:message code="mypage.claim.list.lbl.return" text="반품"/>
											</c:if>
										</c:if>
									</span>
									<c:if test="${myPage.clmYn ne 'Y'}">
										<span><em><spring:message code="mypage.order.list.lbl.orderdate" text="주문일"/></em> <c:out value='${orderInfo.ordDt}'/></span>
									</c:if>
	                              	<c:if test="${myPage.clmYn eq 'Y'}">
                              			<c:if test="${myPage.clmTpCdSearch eq 'PART_CNCL' || myPage.clmTpCdSearch eq 'ALL_CNCL'}">
                              				<span>
	                              				<em><spring:message code="mypage.order.detail.lbl.claim.acceptdate" text="신청일"/></em> 
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmCancels[0].clmReqDt}"/>
											</span>
											<span><em><spring:message code="mypage.order.detail.lbl.claim.no" text="클레임번호"/></em>${myPage.clmNo}</a></span>
										</c:if>
										<c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">
											<span>
	                              				<em><spring:message code="mypage.order.detail.lbl.claim.acceptdate" text="신청일"/></em> 
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmExchanges[0].clmReqDt}"/>
											</span>
											<span><em><spring:message code="mypage.order.detail.lbl.claim.no" text="클레임번호"/></em>${myPage.clmNo}</a></span>
										</c:if>
										<c:if test="${myPage.clmTpCdSearch eq 'RTGOD'}">
											<span>
	                              				<em><spring:message code="mypage.order.detail.lbl.claim.acceptdate" text="신청일"/></em> 
												<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmReturns[0].clmReqDt}"/>
											</span>
											<span><em><spring:message code="mypage.order.detail.lbl.claim.no" text="클레임번호"/></em>${myPage.clmNo}</a></span>
										</c:if>	                              		
	                              	</c:if>
	                              	<span><em><spring:message code="mypage.order.list.lbl.orderno" text="주문번호"/></em> <c:out value='${orderInfo.ordNo}'/></span>	                              
								</div>
									
								<!-- //주문정보 -->
								<div class="btnOdTop">
	                              	<c:if test="${orderInfo.orderCancel.orderCancelYn eq 'Y' && orderInfo.orderCancel.orderCancelCnt eq '0'}">
										<a href="#layerPopupCancelAll" class="btn sm gray d_layer_open" onclick="getLayerPopupTotalCancel('<c:out value="${orderInfo.ordNo}"/>','${orderInfo.ordDt}' ,'${orderInfo.ordStatCd}');"><spring:message code="mypage.order.list.btn.totalcancel" text="전체취소"/></a>
									</c:if>
									<%-- 결제대기(Bo무통장), 입금대기(FO가상계좌),  예약주문입금대기(FO가상계좌 입금대기)--%>
									<c:if test="${orderInfo.ordStatCd eq 'PAY_WAIT' || orderInfo.ordStatCd eq 'DEPST_WAIT' || orderInfo.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
										<a href="#layerPopupPayment" class="btn sm gray d_layer_open" onclick="jsPayMethodChange();"><spring:message code="mypage.order.list.btn.changepay" text="결제수단변경"/></a>
									</c:if>
									<c:if test="${myPage.clmYn eq 'Y'}">
										<c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">
											<c:if test="${clmExchanges[0].clmStatCd eq 'PAY_WAIT'}">
												<a href="#layerPopupPayment" class="btn sm gray d_layer_open" onclick="jsPayMethodAdd('${clmExchanges[0].ordNo}', '${myPage.clmNo}');"><span><spring:message code="mypage.order.detail.btn.addpay" text="배송비결제"/></span></a> <!-- 교환결제대기의 경우 노출 -->
											</c:if>
										</c:if>
										<c:if test="${myPage.clmTpCdSearch eq 'RTGOD'}">
											<c:if test="${clmReturns[0].clmStatCd eq 'PAY_WAIT'}">
												<a href="#layerPopupPayment" class="btn sm gray d_layer_open" onclick="jsPayMethodAdd('${clmReturns[0].ordNo}', '${myPage.clmNo}');"><span><spring:message code="mypage.order.detail.btn.addpay" text="배송비결제"/></span></a> <!-- 교환결제대기의 경우 노출 -->
											</c:if>
										</c:if>
									</c:if>

									<%-- 회원이면서 VIP회원만 거래명세서 활성화 --%>
									<c:if test="${myPage.clmYn ne 'Y' and nmbrYn eq 'N' and vipYn eq 'Y'}">
										<%-- 거래명세서 버튼 활성화 여부를 위해 주문 남은 건수가 있는지 확인 시작 --%>
										<c:set value="0" var="ordQty"/>
										<c:set value="0" var="clmQty"/>
										<c:set value="0" var="realOrdQty"/>
										<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
											<c:forEach var="lgsDlvList" varStatus="lgsDlvStatus" items="${listDlvsp.lgsDlvFoExtendList}">
												<c:forEach var="ordGodList" varStatus="ordGodstatus" items="${lgsDlvList.ordGodList}">
													<c:set value="${ordQty + ordGodList.ordQty}" var="ordQty"/>
													<c:if test="${ordGodList.mainClmStatCd eq 'COMPT'}">
														<c:set value="${clmQty + ordGodList.clmQty}" var="clmQty"/>
													</c:if>
												</c:forEach>
											</c:forEach>
										</c:forEach>
										<c:set value="${ordQty - clmQty}" var="realOrdQty"/>
										<%-- 거래명세서 버튼 활성화 여부를 위해 주문 남은 건수가 있는지 확인 종료 --%>
										
										<c:if test="${myPage.clmYn ne 'Y'}">
											<c:if test="${(orderInfo.ordStatCd ne 'PAY_WAIT' && orderInfo.ordStatCd ne 'DEPST_WAIT' && orderInfo.ordStatCd ne 'RESVE_ORD_DEPST_WAIT' && orderInfo.ordStatCd ne 'ALL_CNCL') && realOrdQty > 0}">
												<a href="#" class="btn sm gray" onclick="popupDealingsDetailedStatement('<c:out value="${orderInfo.ordNo}"/>');return false;"><spring:message code="mypage.order.list.btn.inquiry3" text="거래명세서"/></a>
											</c:if>
										</c:if>
									</c:if>
	                              	<a href="#" class="btn sm fill" onclick="mypageorder.goInquiryList();return false;"><spring:message code="mypage.order.list.btn.inquiry2" text="1:1 문의"/></a>
                              	</div>	
                              	
                              	<c:if test="${myPage.clmYn ne 'Y'}">
                              	<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
									<c:if test="${listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP'}">	<%-- 교환출고목록은 교환정보에서 나오도록 기획변경 --%>
									
										<%-- 매장픽업 주문 --%>
										<c:if test="${orderInfo.ordTpCd eq 'SHOP_PKUP_ORD'}">
			                              	<!-- 배송지 -->
											<div class="orderAdd odAddBox">	
												<c:choose>
												<%-- 클레임 배송지--%>
												<c:when test="${listDlvsp.dlvPcupspSectCd eq 'CLM_DLVSP' and listDlvsp.dlvSectCd eq 'GNRL_DLV'}">
													<dl>
														<dt><spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/></dt>
														<dd><c:out value='${listDlvsp.addrseNm}'/></dd>
													</dl>
													<dl>
														<dt><spring:message code="mypage.order.detail.lbl.delivery.address" text="주소"/></dt>
														<dd><c:out value='${listDlvsp.addrseBaseAddr}'/>&nbsp;<c:out value='${listDlvsp.addrseDetailAddr}'/></dd>
													</dl>
													<dl>
														<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
														<dd>
															<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
															<c:if test="${listDlvsp.addrseTelAreaNo + listDlvsp.addrseTelTlofNo + listDlvsp.addrseTelTlofWthnNo !=''}">
																 | <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
															</c:if>
														</dd>
													</dl>	
												</c:when>
												<%-- 주문 배송지 --%>
												<c:otherwise>				
													<dl>
														<dt><strong>[<spring:message code="mypage.order.detail.lbl.delivery.shoppickup" text="매장픽업"/>]</strong></dt>										
														<dd class="StoreInfo">
				                                           	<strong class="name"><c:out value='${orderInfo.shopNm }'/></strong>
				                                           	<a href="#" class="btnMap" onclick="showPopupShop('${orderInfo.shopId}');return false;"><img src="${_resourceURL}static/images/od/icon_location.png" alt="지도보기"></a><br>
				                                           	<c:out value='${orderInfo.shopBaseAddr }'/>&nbsp;<c:out value='${orderInfo.shopDetailAddr}'/><span class="lineBar"><c:out value='${orderInfo.shopTelTlofWthnNo}'/><!-- <span class="lineBar">(평일)11:30 ~ 22:00</span> 2018.12.06 삭제-->
														</dd>
													</dl>
												</c:otherwise>
												</c:choose>							
											</div>	
											<!-- //배송지 -->	
										</c:if>
										<%-- 일반배송 주문 --%>
										<c:if test="${orderInfo.ordTpCd ne 'SHOP_PKUP_ORD'}">	
											<!-- 배송지 -->
											<div class="orderAdd odAddBox">									
												<!-- <strong class="odAddTit">[배송지]</strong> 2018.12.06 삭제-->										
												<dl>
													<dt><spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/></dt>
													<dd><c:out value='${listDlvsp.addrseNm}'/></dd>
												</dl>
												<dl>
													<dt><spring:message code="mypage.order.detail.lbl.delivery.address" text="주소"/></dt>
													<dd><c:out value='${listDlvsp.addrseBaseAddr}'/>&nbsp;<c:out value='${listDlvsp.addrseDetailAddr}'/></dd>
												</dl>
												<dl>
													<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
													<dd>
														<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
														<c:if test="${listDlvsp.addrseTelAreaNo + listDlvsp.addrseTelTlofNo + listDlvsp.addrseTelTlofWthnNo !=''}">
															 | <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
														</c:if>
													</dd>
												</dl>	
												<dl>
													<c:if test="${not empty listDlvsp.dlvMemo }">
													<dt><spring:message code="mypage.order.detail.lbl.delivery.message" text="배송메시지"/></dt>
													<dd><c:out value='${listDlvsp.dlvMemo}'/></dd>
													</c:if>
												</dl>
												<c:if test="${listDlvsp.dlvChangeYn eq 'Y'  && empty listDlvsp.dmstcWaybilNo && listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP' && orderInfo.ordStatCd ne 'ALL_CNCL'}">	
												<a href="#layerPopupChangeAddress" class="btn sm gray btnAddModi d_layer_open" popName="pop_deliveryChange" onclick="getLayerPopupDeliveryChange('<c:out value="${orderInfo.ordNo}"/>', <c:out value="${listDlvsp.dlvPcupspTurn}"/>); return false;"><spring:message code="mypage.order.detail.btn.delivery.change" text="배송지 수정"/></a>	
												</c:if>				
											</div>	
											<!-- //배송지 -->		
										</c:if>	
															
										<!-- 주문상품 리스트  -->
										<table class="board-list orderTable">
				                        	<colgroup>
				                                <col>
		                                        <col style="width:80px">
		                                        <col style="width:114px">
		                                        <col style="width:90px">
		                                        <col style="width:146px">
				                          	</colgroup>	   
			                         		<tbody>
			                              		<!-- loop -->
			                              		<c:forEach var="lgsDlvList" varStatus="lgsDlvStatus" items="${listDlvsp.lgsDlvFoExtendList}">
													<c:forEach var="ordGodList" varStatus="ordGodstatus" items="${lgsDlvList.ordGodList}">
														<c:set value="${ordGodList.godNo}" var="godNo"/>
														<c:if test="${not empty ordGodList.upGodNo}">
															<c:set value="${ordGodList.upGodNo}" var="godNo"/>
														</c:if>
														<c:if test="${ordGodList.godTpCd ne 'PCHS_GFT' && ordGodList.godTpCd ne 'CNVRS_GFT'}">
															<tr>		                             	 
			                                  					<td class="tleft">
																	<div class="product-info <c:if test="${ordGodList.pckageGodTpCd eq 'ADIT_CPST_GOD'}">set</c:if>">
																		<div class="product-info-img">
																			<a href="javascript:mypageorder.goGoodsInfo('${godNo}');"><img src="<ncp:img path='${ordGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a>
																		</div>
						                                          		<div class="product-info-text">
						                                              		<div class="product-info-box">
						                                              		<c:if test="${ordGodList.dlvShopId ne 'M510' and ordGodList.dlvShopId ne 'I50002' and orderInfo.ordTpCd ne 'SHOP_PKUP_ORD' and ordGodList.godTpCd ne 'SET_GOD'}">
																				<c:set var="hasShopDlv" value="Y" />
																					<div class="product-more-info">
																						<span class="text-color01">[<spring:message code="mypage.order.list.lbl.shopdelivery" text="매장배송"/>]</span>
																					</div>
																			</c:if>
																			<c:if test="${ordGodList.pckageGodTpCd eq 'ADIT_CPST_GOD'}">
																				<div class="product-more-info">
																					<span class="text-color01">[<spring:message code="mypage.order.list.lbl.addcomposition" text="추가구성"/>]</span>
																				</div>
																			</c:if>	
																			<c:if test="${orderInfo.ordTpCd eq 'LAG_QTY_ORD' }" >
																				<div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.detail.lbl.largeqtyorder" text="대량주문"/>]</span></div>
																			</c:if>
						                                                  	<p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${godNo}');"><c:out value='${ordGodList.godNm}'/></a></p>
						                                                  	<div class="product-price">
						                                                    	<span><fmt:formatNumber value="${ordGodList.saleAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
						                                                  	</div>
						                                              	</div>
						                                              	<div class="product-option">
						                                                  	<c:if test="${ordGodList.godTpCd ne 'SET_GOD'}">
																				<span>
																					<c:out value='${ordGodList.colorCd}'/> 
																					 / <c:out value='${ordGodList.itmNm}'/>
																				</span>
																			</c:if>
																			<c:if test="${ordGodList.godTpCd eq 'SET_GOD'}">
																				<span>
																					<c:forEach var="godOptList" varStatus="status" items="${ordGodList.godOptList}">
																						<c:if test="${status.first ne true }">,&nbsp;</c:if>
																						<c:out value='${godOptList.prdlstNm}'/> : <c:out value='${godOptList.colorCd}'/> / <c:out value='${godOptList.itmNm}'/>
																					</c:forEach>
																				</span>
																			</c:if>
						                                              	</div>
						                                          	</div>
						                                      	</div>
						                                  		</td>
			                                  					<td><c:out value='${ordGodList.ordQty}'/></td>
							                                  	<td><fmt:formatNumber value="${ordGodList.saleAmt*(ordGodList.ordQty)}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>
			                                  					<td>
			                                  						<c:if test="${ordGodList.realOrdQty < 1 && orderInfo.ordStatCd ne 'ALL_CNCL' }">
																		-
																	</c:if>
																	<c:if test="${ordGodList.realOrdQty > 0 || orderInfo.ordStatCd eq 'ALL_CNCL'}">
																		<c:choose>
																			<c:when test="${orderInfo.ordStatCd eq 'PAY_WAIT' || orderInfo.ordStatCd eq 'DEPST_WAIT' || orderInfo.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
																				<ncp:code code='DEPST_WAIT'/>
																			</c:when>
																			<c:when test="${orderInfo.ordStatCd eq 'PAY_COMPT'}">
																				<ncp:code code='PAY_COMPT'/>
																			</c:when>
																			<c:when test="${orderInfo.ordStatCd eq 'DLV_PRPARE' || orderInfo.ordStatCd eq 'DLV_PROGRS' || orderInfo.ordStatCd eq 'DLV_COMPT'}">
																				<%-- 상품단위의 상태 노출을 위해 상품별 배송상태를 확인한다. --%>
																				<c:choose>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLV_WAIT' || ordGodList.dlvStatCd eq 'DLIVY_DRCT_WAIT' || ordGodList.dlvStatCd eq 'SHTG_RCEPT'}">
																						<ncp:code code='PAY_COMPT'/>
																					</c:when>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLIVY_DRCT'}">
																						<ncp:code code='DLV_PRPARE'/>
																					</c:when>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLIVY_COMPT'}">
																						<ncp:code code='DLV_PROGRS'/>
																					</c:when>
																					<c:otherwise>
																						<ncp:code code='${ordGodList.dlvStatCd}'/>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:otherwise>
																				<ncp:code code='${orderInfo.ordStatCd}'/>
																			</c:otherwise>
																		</c:choose>
																	</c:if>
																	<c:if test="${ordGodList.clmStatCd eq 'PAY_WAIT'}" >
																		<a href="#layerPopupPayment" class="btn gray sm d_layer_open" ><span><spring:message code="mypage.order.detail.btn.addpay" text="배송비결제"/></span></a>
																	</c:if>	
			                                  					</td>
			                                  					<td class="selBox">
			                                  						<c:choose>
																		<%-- 픽업주문일때 부분취소 가능여부 --%>
																		<c:when test="${orderInfo.ordTpCd  eq 'SHOP_PKUP_ORD'}">
																		<%--  출고지시 || 매장준비완료 --%>
																			<c:if test="${ ordGodList.dlvStatCd ne 'DLV_COMPT'}">
																				<c:if test="${ordGodList.realOrdQty > 0 && ordGodList.godTpCd ne 'PCHS_GFT' && ordGodList.godTpCd ne 'CNVRS_GFT' && listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP' && ordGodList.prmTpCd ne 'ORD_DC'}">
																					<span><a href="#none" class="btn gray sm" onclick="mypageorder.goUnitCancel('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>');   return false;"><spring:message code="mypage.order.list.btn.ordercancel" text="주문취소"/></a></span>
																				</c:if>
																			</c:if>
																		</c:when>
																		<%-- 그외 부분취소 가능조건--%>
																		<c:otherwise>
																			<c:if test="${orderInfo.ordStatCd eq 'PAY_WAIT' || orderInfo.ordStatCd eq 'DEPST_WAIT' || orderInfo.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
																				<span><a href="#layerPopupPayment" class="btn gray sm d_layer_open" onclick="jsPayMethodChange();"><spring:message code="mypage.order.list.btn.changepay" text="결제수단변경"/></a></span>
																			</c:if>
																			<%--  배송대기 ||출고지시 || 결품접수 && 대량주문아닐때 --%>
																			<c:if test="${(ordGodList.dlvStatCd eq 'DLV_WAIT' || ordGodList.dlvStatCd eq 'DLIVY_DRCT_WAIT' || ordGodList.dlvStatCd eq 'SHTG_RCEPT')
																				 && orderInfo.ordTpCd ne 'LAG_QTY_ORD' && orderInfo.ordStatCd ne 'DEPST_WAIT'  && orderInfo.ordStatCd ne 'RESVE_ORD_DEPST_WAIT' && orderInfo.ordStatCd ne 'ALL_CNCL' && listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP'}">
																			<%-- 사은품은 클레임 불가 --%>
																				<c:if test="${ordGodList.realOrdQty > 0 && ordGodList.godTpCd ne 'PCHS_GFT' && ordGodList.godTpCd ne 'CNVRS_GFT' && ordGodList.prmTpCd ne 'ORD_DC'}">
																					<span><a href="#none" class="btn gray sm" onclick="mypageorder.goUnitCancel('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>'); return false;"><spring:message code="mypage.order.list.btn.ordercancel" text="주문취소"/></a></span>
																				</c:if>
																			</c:if>
																		</c:otherwise>
																	</c:choose>
																	<c:if test="${(ordGodList.dlvStatCd eq 'DLIVY_COMPT' || ordGodList.dlvStatCd eq 'DLV_COMPT') &&
																				 	(not empty ordGodList.dmstcWaybilNo || ordGodList.godTpCd eq 'SET_GOD') }">
																		<c:if test="${ordGodList.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
																			<span><a href="#none" class="btn gray sm" onclick="mypageorder.deliveryTracking('<c:out value="${ordGodList.dmstcWaybilNo}"/>', '<c:out value="${ordGodList.cdDscr}"/>','<c:out value="${ordGodList.dlvComCd}"/>');return false;">
																				<spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/>
																			</a></span>
																		</c:if>
																	</c:if>
																	<%--배송중 일경우 상품수령완료 가능 --%>
																	<c:if test="${ordGodList.dlvStatCd eq 'DLIVY_COMPT' && not empty ordGodList.dmstcWaybilNo}">
																		<span><a href="#none" class="btn gray sm" onclick="mypageorder.updateDeliveryStatus('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${ordGodList.dmstcWaybilNo}'/>'); return false;"><spring:message code="mypage.order.list.btn.deliveryconfirm" text="상품수령완료"/></a></span>
																	</c:if>
																	<%--교환 반품의 경우 상품단위로 배송완료된 상품만 가능 사은품(사입 사은품,전환 사은품)은 반품,교환 부분취소 불가  --%>
																	<c:if test="${ordGodList.realOrdQty > 0 && ordGodList.dlvStatCd eq 'DLV_COMPT' && ordGodList.godTpCd ne 'PCHS_GFT'
																		&& ordGodList.godTpCd ne 'CNVRS_GFT' && ordGodList.godTpCd ne 'GFCT' &&  ordGodList.realOrdQty > 0 && orderInfo.ordTpCd ne 'LAG_QTY_ORD' }">
																		<c:if test="${orderInfo.ordStatCd ne 'ALL_CNCL' && ordGodList.clmYn eq 'Y' && ordGodList.cstmrPchDcsnYn ne 'Y'}">
																			<span><a href="#none" class="btn gray sm" onclick="mypageorder.goRequestExchange('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>');return false;"><spring:message code="mypage.order.list.btn.exchange" text="교환신청"/></a></span>
																			<span><a href="#none" class="btn gray sm" onclick="mypageorder.goRequestReturn('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>' ,'<c:out value='${ordGodList.prmTpCd}'/>' );return false;"><spring:message code="mypage.order.list.btn.return" text="반품신청"/></a></span>
																			<span><a href="#none" class="btn gray sm" onclick="mypageorder.updateOrderDecision('${orderInfo.ordNo}', '${ordGodList.ordGodTurn }');"><spring:message code="mypage.order.list.btn.purchaseconfirm" text="구매확정"/></a></span>
																		</c:if>
																	</c:if>
																	<c:if test="${nmbrYn eq 'N' && ordGodList.realOrdQty > 0 && ordGodList.dlvStatCd eq 'DLV_COMPT' && ordGodList.cstmrPchDcsnYn eq 'Y' && ordGodList.reviewApplyTermYn eq 'Y'}">
																		<c:if test="${ordGodList.pckageGodTpCd ne 'ADIT_CPST_GOD' or ordGodList.dspYn ne 'N'}">
																			<span><a href="#none" class="btn gray sm" onclick="jsLinkUrlPost('<c:url value='/mypage/goods/reviewView'/>',{'${_csrf.parameterName}' : '${_csrf.token}'});   return false;"><spring:message code="mypage.order.list.btn.goodsreview" text="리뷰작성"/></a></span>
																		</c:if>
																	</c:if>
			                                  					</td>
								                          	</tr>
								                  		</c:if>
														<c:if test="${ordGodList.gftTpCd eq 'GOD_GFT'}">
															<tr>	                                	 
							                                	<td class="tleft">
							                                    	<div class="product-info product-free-gift">
					                                                	<div class="product-info-img"><a href="#"><img src="<ncp:img path='${ordGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
					                                                   	<div class="product-info-text">
					                                                    	<div class="product-info-box">
					                                                        	<div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.gift" text="사은품"/>]</span></div>
					                                                           	<p class="product-name"><c:out value='${ordGodList.godNm}'/></p>
					                                                           	<div class="product-price">
					                                                           	</div>
					                                                       </div>
					                                                       <div class="product-option">
					                                                       </div>
					                                                   	</div>
					                                               	</div>
							                                  	</td>
							                                  	<td></td>
							                                  	<td></td>
							                                  	<td></td>
							                                  	<td>
							                                  		<c:if test="${(ordGodList.dlvStatCd eq 'DLIVY_COMPT' || ordGodList.dlvStatCd eq 'DLV_COMPT') &&
																				 	(not empty ordGodList.dmstcWaybilNo || ordGodList.godTpCd eq 'SET_GOD') }">
																		<c:if test="${ordGodList.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
																			<a href="#none" class="btn gray sm" onclick="mypageorder.deliveryTracking('<c:out value="${ordGodList.dmstcWaybilNo}"/>', '<c:out value="${ordGodList.cdDscr}"/>','<c:out value="${ordGodList.dlvComCd}"/>');return false;">
																				<span><spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/></span>
																			</a>
																		</c:if>
																	</c:if>
							                                  	</td>
							                               	</tr>
														</c:if>
														<c:if test="${ordGodList.gftTpCd eq 'ORD_GFT'}">
															<tr>	                                	 
							                                	<td class="tleft">
							                                    	<div class="product-info product-free-gift">
					                                                	<div class="product-info-img"><a href="#"><img src="<ncp:img path='${ordGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
					                                                   	<div class="product-info-text">
					                                                    	<div class="product-info-box">
					                                                        	<div class="product-more-info"><span class="text-color01">[<spring:message code="mypage.order.list.lbl.gift" text="사은품"/>]</span></div>
					                                                           	<p class="product-name"><c:out value='${ordGodList.godNm}'/></p>
					                                                           	<div class="product-price">
					                                                           	</div>
					                                                       </div>
					                                                       <div class="product-option">
					                                                       </div>
					                                                   	</div>
					                                               	</div>
							                                  	</td>
							                                  	<td></td>
							                                  	<td></td>
							                                  	<td></td>
							                                  	<td>
							                                  		<c:if test="${(ordGodList.dlvStatCd eq 'DLIVY_COMPT' || ordGodList.dlvStatCd eq 'DLV_COMPT') &&
																				 	(not empty ordGodList.dmstcWaybilNo || ordGodList.godTpCd eq 'SET_GOD') }">
																		<c:if test="${ordGodList.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
																			<a href="#none" class="btn-style01" onclick="mypageorder.deliveryTracking('<c:out value="${ordGodList.dmstcWaybilNo}"/>', '<c:out value="${ordGodList.cdDscr}"/>','<c:out value="${ordGodList.dlvComCd}"/>');return false;">
																				<span><spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/></span>
																			</a>
																		</c:if>
																	</c:if>
							                                  	</td>
							                               	</tr>
														</c:if>
													</c:forEach>
												</c:forEach>
								                <!-- //loop -->
								        	</tbody>
										</table>                        
			                         	<!-- //주문상품 리스트  -->
		                         	
		                         
                         			</c:if>
								</c:forEach>
								</c:if>
								<c:if test="${myPage.clmYn eq 'Y'}">
									<c:if test="${myPage.clmTpCdSearch eq 'PART_CNCL' || myPage.clmTpCdSearch eq 'ALL_CNCL'}">
										<!-- 주문상품 리스트  -->
										<table class="board-list orderTable orderMsg">
				                          <colgroup>
				                                <col>                                      
		                                        <col style="width:90px">
		                                        <col style="width:146px">
				                          </colgroup>	   
				                         <tbody>
				                              <!-- loop -->
				                              <c:forEach var="clmCancelsList" varStatus="clmGodstatus" items="${clmCancels}">
				                              	<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmCancelsList.lgsDlvspFoExtend}">
													<c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
						                              <tr>		                             	 
						                                  <td class="tleft">
						                                      <div class="product-info">
						                                          <div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
						                                          <div class="product-info-text">
						                                              <div class="product-info-box">
						                                                  <p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');">${clmWrhsGodList.godNm}</a></p>
						                                                  <div class="product-price">
						                                                      <span><fmt:formatNumber value="${clmWrhsGodList.saleAmt / clmWrhsGodList.clmQty}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
						                                                  </div>
						                                              </div>
						                                              <div class="product-option">
						                                                  <span>
						                                                  	<c:if test="${clmWrhsGodList.godTpCd ne 'SET_GOD'}">
																				<c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/>
																			</c:if>
																			<c:if test="${clmWrhsGodList.godTpCd eq 'SET_GOD' || clmWrhsGodList.godTpCd eq 'PCKAGE_GOD'}">
																				<c:forEach var="godOptList" varStatus="status" items="${clmWrhsGodList.godOptList}">
																					<c:if test="${status.first ne true }">,&nbsp;</c:if>
																					<c:out value='${godOptList.godNm}'/> : <c:out value='${godOptList.colorCd}'/> / <c:out value='${godOptList.itmNm}'/>
																				</c:forEach>
																			</c:if>
						                                                  </span>
						                                              </div>
						                                          </div>
						                                      </div>
						                                  </td>
						                                  <td><c:out value='${clmWrhsGodList.clmQty}'/></td>		                                 
						                                  <td><spring:message code="mypage.claim.list.lbl.cancel" text="취소"/><ncp:code code='${clmCancelsList.clmStatCd}'/></td>		                                  
						                               </tr>
						                              </c:forEach>
						                      	</c:forEach>
				                              </c:forEach>
				                               <!-- //loop -->                    
				                            </tbody>
				                        </table>		                        
				                        <!-- //주문상품 리스트  -->
				                        </div>
				                        </div>
									</c:if>
									<c:if test="${myPage.clmTpCdSearch eq 'GNRL_EXCHG'}">
										<c:forEach var="clmExchangesList" varStatus="ordGodstatus" items="${clmExchanges}">
											<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
												<c:if test="${listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP'}">
													<!--  교환대상 상품정보 -->
													<div class="mgInfoBox">
					                        			<h3>
					                        				<spring:message code="mypage.claim.detail.exchange.select.goods.info" text="교환대상 상품정보"/>
					                        			</h3>						
														<!-- 주문상품 리스트  -->
														<table class="board-list orderTable orderMsg">
								                        	<colgroup>
				                        						<col style="width:140px">
								                                <col>                                        
						                                        <col style="width:120px">
						                                        <col style="width:120px">
						                                        <col style="width:146px">
								                         	</colgroup>	   
								                         	<tbody>
								                         	<c:forEach var="lgsDlvList" varStatus="lgsDlvStatus" items="${listDlvsp.lgsDlvFoExtendList}">
																<c:forEach var="ordGodList" varStatus="ordGodstatus" items="${lgsDlvList.ordGodList}">
									                              <!-- loop -->
									                              <tr>
									                              	  <c:if test="${ordGodList.godTpCd ne 'PCHS_GFT' && ordGodList.godTpCd ne 'CNVRS_GFT'}">		                             	 
									                                  <td <c:if test="${listDlvsp.dlvPcupspSectCd ne 'CLM_DLVSP'}">colspan="2"</c:if> class="tleft">
									                                      <div class="product-info">
									                                          <div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${ordGodList.godNo}');"><img src="<ncp:img path='${ordGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
									                                          <div class="product-info-text">
									                                              <div class="product-info-box">
									                                                  <p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${ordGodList.godNo}');"><c:out value='${ordGodList.godNm}'/></a></p>
									                                                  <div class="product-price">
									                                                      <span><fmt:formatNumber value="${ordGodList.saleAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
									                                                  </div>
									                                              </div>
									                                              <div class="product-option">
									                                                  <span><c:out value='${ordGodList.colorCd}'/> / <c:out value='${ordGodList.itmNm}'/></span>
									                                              </div>
									                                          </div>
									                                      </div>
									                                  </td>
									                                  <td><c:out value='${ordGodList.ordQty}'/></td>
									                                  <td class="selBox">
									                                  	  <c:choose>
																			<c:when test="${orderInfo.ordStatCd eq 'PAY_WAIT' || orderInfo.ordStatCd eq 'DEPST_WAIT' || orderInfo.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
																				<ncp:code code='DEPST_WAIT'/>
																			</c:when>
																			<c:when test="${orderInfo.ordStatCd eq 'PAY_COMPT'}">
																				<ncp:code code='PAY_COMPT'/>
																			</c:when>
																			<c:when test="${orderInfo.ordStatCd eq 'DLV_PRPARE' || orderInfo.ordStatCd eq 'DLV_PROGRS' || orderInfo.ordStatCd eq 'DLV_COMPT'}">
																				<%-- 상품단위의 상태 노출을 위해 상품별 배송상태를 확인한다. --%>
																				<c:choose>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLV_WAIT' || ordGodList.dlvStatCd eq 'DLIVY_DRCT_WAIT' || ordGodList.dlvStatCd eq 'SHTG_RCEPT'}">
																						<ncp:code code='PAY_COMPT'/>
																					</c:when>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLIVY_DRCT'}">
																						<ncp:code code='DLV_PRPARE'/>
																					</c:when>
																					<c:when test="${ordGodList.dlvStatCd eq 'DLIVY_COMPT'}">
																						<ncp:code code='DLV_PROGRS'/>
																					</c:when>
																					<c:otherwise>
																						<ncp:code code='${ordGodList.dlvStatCd}'/>
																					</c:otherwise>
																				</c:choose>
																			</c:when>
																			<c:otherwise>
																				<ncp:code code='${orderInfo.ordStatCd}'/>
																			</c:otherwise>
																		</c:choose>
									                                  </td>
									                                  <td class="selBox">
									                                  	<c:if test="${(ordGodList.dlvStatCd eq 'DLIVY_COMPT' || ordGodList.dlvStatCd eq 'DLV_COMPT') &&
																					 	(not empty ordGodList.dmstcWaybilNo || ordGodList.godTpCd eq 'SET_GOD') }">
																			<c:if test="${ordGodList.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
																				<span><a href="#none" class="btn sm gray" onclick="mypageorder.deliveryTracking('<c:out value="${ordGodList.dmstcWaybilNo}"/>', '<c:out value="${ordGodList.cdDscr}"/>','<c:out value="${ordGodList.dlvComCd}"/>');return false;">
																					<spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/>
																				</a></span>
																			</c:if>
																		</c:if>
																		<c:if test="${listDlvsp.dlvPcupspSectCd eq 'CLM_DLVSP' && ordGodList.dlvStatCd eq 'DLV_COMPT' && ordGodList.godTpCd ne 'PCHS_GFT' && ordGodList.godTpCd ne 'CNVRS_GFT' && ordGodList.cstmrPchDcsnYn ne 'Y'}">
																			<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestExchange('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>');return false;"><spring:message code="mypage.order.list.btn.exchange" text="교환신청"/></a></span>
																			<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestReturn('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>' ,'clmWrhsGodList.prmTpCd' );return false;"><spring:message code="mypage.order.list.btn.return" text="반품신청"/></a></span>
																			<span><a href="#none" class="btn sm gray" onclick="mypageorder.updateOrderDecision('${orderInfo.ordNo}', '${clmWrhsGodList.ordGodTurn }');"><spring:message code="mypage.order.list.btn.purchaseconfirm" text="구매확정"/></a></span>
																		</c:if>
									                                  </td>
									                                  </c:if>	
									                               </tr>
									                               																			
									                               <!-- //loop -->
									                           	</c:forEach>
								                           	</c:forEach>                  
								                            </tbody>
								                         </table>		                        
								                         <!-- //주문상품 리스트  -->
								                         <!--  수거지 -->
								                         <div class="orderAdd odAddBox">									
															<!-- <strong class="odAddTit">[수거지]</strong> 2018.12.06 삭제 -->										
															<dl>
																<dt>
							                        				<spring:message code="mypage.claim.detail.lbl.delivery.name" text="신청자"/>
																</dt><!-- 2018.12.06 수정 -->
																<dd>${listDlvsp.addrseNm }</dd>
															</dl>
															<dl>
																<dt>
							                        				<spring:message code="mypage.claim.detail.lbl.delivery.address" text="수거지"/>
																</dt><!-- 2018.12.06 수정 -->
																<dd>${listDlvsp.addrseBaseAddr } ${listDlvsp.addrseDetailAddr }</dd>
															</dl>
															<dl>
																<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
																<dd>
																	<c:if test="${listDlvsp.addrseMobilAreaNo ne '' and listDlvsp.addrseMobilAreaNo ne null}">
																		<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
																	</c:if>
																	<c:if test="${listDlvsp.addrseTelAreaNo ne '' and listDlvsp.addrseTelAreaNo ne null}">  
																		| <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
																	</c:if>
																</dd>
															</dl>												
														</div>		
														<!-- // 수거지 -->	
													</div>
													<!-- // 교환대상 상품정보 -->
												</c:if>
											</c:forEach>
											<c:forEach var="listDlvsp" varStatus="status" items="${clmExchangesList.lgsDlvspFoExtend}">
												<div class="mgInfoBox">
				                        			<h3>
				                        				<spring:message code="mypage.claim.detail.exchange.delivery.goods.info" text="교환배송 상품정보"/>
				                        			</h3>						
													<!-- 주문상품 리스트  -->
													<table class="board-list orderTable orderMsg">
							                          	<colgroup>
			                        						<col>                                        
					                                       	<col style="width:120px">
					                                       	<col style="width:120px">
					                                       	<col style="width:146px">
							                         	</colgroup>	   
							                         	<tbody>
							                         		<c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
								                            	<!-- loop -->
								                              	<tr>                             	 
								                                  	<td class="tleft">
								                                      	<div class="product-info">
								                                          	<div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
								                                          	<div class="product-info-text">
								                                              	<div class="product-info-box">
								                                                  	<p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');"><c:out value='${clmWrhsGodList.godNm}'/></a></p>
								                                                  	<div class="product-price">
								                                                      	<span><fmt:formatNumber value="${clmWrhsGodList.saleAmt / clmWrhsGodList.clmQty}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
								                                                  	</div>
								                                              	</div>
								                                              	<div class="product-option">
								                                                  	<span><c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/></span>
								                                              	</div>
								                                          	</div>
								                                      	</div>
								                                  	</td>
								                                  	<td><c:out value='${clmWrhsGodList.clmQty}'/></td>
								                                  	<td class="selBox">
								                                  		<c:choose>
																			<c:when test="${clmWrhsGodList.dlvStatCd eq 'DLV_WAIT' || clmWrhsGodList.dlvStatCd eq 'DLIVY_DRCT_WAIT' || clmWrhsGodList.dlvStatCd eq 'SHTG_RCEPT' || clmWrhsGodList.dlvStatCd eq 'DLIVY_DRCT'}">
																				<ncp:code code='DLV_PRPARE'/>
																			</c:when>
																			<c:when test="${clmWrhsGodList.dlvStatCd eq 'DLIVY_COMPT'}">
																				<ncp:code code='DLV_PROGRS'/>
																			</c:when>
																			<c:otherwise>
																				<ncp:code code='${clmWrhsGodList.dlvStatCd}'/>
																			</c:otherwise>
																		</c:choose>  
								                                  	</td>
								                                  	<td class="selBox">
								                                  	<c:if test="${(clmWrhsGodList.dlvStatCd eq 'DLIVY_COMPT' || clmWrhsGodList.dlvStatCd eq 'DLV_COMPT') &&
																				 	(not empty listDlvsp.dmstcWaybilNo || clmWrhsGodList.godTpCd eq 'SET_GOD') }">
																			<span><a href="#none" class="btn sm gray" onclick="mypageorder.deliveryTracking('<c:out value="${ordGodList.dmstcWaybilNo}"/>', '<c:out value="${ordGodList.cdDscr}"/>','<c:out value="${ordGodList.dlvComCd}"/>');return false;">
																				<spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/>
																			</a></span>
																	</c:if>
																	<c:if test="${clmWrhsGodList.dlvStatCd eq 'DLV_COMPT' && clmWrhsGodList.godTpCd ne 'PCHS_GFT' && clmWrhsGodList.godTpCd ne 'CNVRS_GFT' && clmWrhsGodList.cstmrPchDcsnYn ne 'Y'}">
																		<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestExchange('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>');return false;"><spring:message code="mypage.order.list.btn.exchange" text="교환신청"/></a></span>
																		<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestReturn('<c:out value='${orderInfo.ordNo}'/>', '<c:out value='${listDlvsp.dlvPcupspTurn}'/>' ,'clmWrhsGodList.prmTpCd' );return false;"><spring:message code="mypage.order.list.btn.return" text="반품신청"/></a></span>
																		<span><a href="#none" class="btn sm gray" onclick="mypageorder.updateOrderDecision('${orderInfo.ordNo}', '${clmWrhsGodList.ordGodTurn }');"><spring:message code="mypage.order.list.btn.purchaseconfirm" text="구매확정"/></a></span>
																	</c:if>
								                                  	</td>
								                               </tr>
								                               <tr class="seldMsg2">
								                               		<td colspan="4">
								                               			<span class="tt"><spring:message code="mypage.order.detail.lbl.claim.exchange.cause" text="교환사유"/></span>
								                               			<span class="dd"><ncp:code code='${clmWrhsGodList.clmResnCd}'/> / <c:out value='${clmWrhsGodList.clmResnCont}'/></span>
								                               		</td>								                               		
															   </tr>																			
								                               <!-- //loop -->
								                           	</c:forEach>                
							                            </tbody>
							                         </table>		                        
							                         <!-- //주문상품 리스트  -->
							                         <!--  수거지 -->
							                         <div class="orderAdd odAddBox">									
														<!-- <strong class="odAddTit">[수거지]</strong> 2018.12.06 삭제 -->										
														<dl>
															<dt>
						                        				<spring:message code="mypage.order.detail.lbl.delivery.name" text="받는 분"/>
															</dt><!-- 2018.12.06 수정 -->
															<dd>${listDlvsp.addrseNm }</dd>
														</dl>
														<dl>
															<dt>
						                        				<spring:message code="mypage.order.detail.lbl.delivery.address" text="주소"/>
															</dt><!-- 2018.12.06 수정 -->
															<dd>${listDlvsp.addrseBaseAddr } ${listDlvsp.addrseDetailAddr }</dd>
														</dl>
														<dl>
															<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
															<dd>
																<c:if test="${listDlvsp.addrseMobilAreaNo ne '' and listDlvsp.addrseMobilAreaNo ne null}">
																	<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
																</c:if>
																<c:if test="${listDlvsp.addrseTelAreaNo ne '' and listDlvsp.addrseTelAreaNo ne null}">  
																	| <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
																</c:if>
															</dd>
														</dl>
														<c:if test="${listDlvsp.dlvPcupspSectCd eq 'CLM_DLVSP' and not empty listDlvsp.dlvMemo}">
															<dl>
																<dt><spring:message code="mypage.order.detail.lbl.delivery.message" text="배송메세지"/></dt>
																<dd>${listDlvsp.dlvMemo}</dd>
															</dl>
														</c:if>													
													</div>		
													<!-- // 수거지 -->	
												</div>
											</c:forEach>
										<!--  교환배송비 -->
				                        <div class="mgInfoBox">
				                        	<h3><spring:message code="mypage.claim.exchange.lbl.exchange.delivery" text="교환 배송비"/></h3>
				                        	<table class="tbTotalList">
				                        		<caption><spring:message code="mypage.claim.exchange.lbl.exchange.delivery" text="교환 배송비"/></caption>
						                        <colgroup>
						                             <col style="width:20%">
				                                     <col style="width:">
				                                     <col style="width:">
						                        </colgroup>	   
						                        <tbody>
						                          	<tr>
						                          		<th><spring:message code="mypage.claim.exchange.lbl.delivery.roundtrip" text="교환왕복배송"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmExchangesList.exchgDlvCst}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>                           			                          	
						                        </tbody>
						                        <tfoot>
													<tr>
						                          		<th><spring:message code="mypage.order.lbl.payment.payamount" text="결제금액"/></th>
						                          		<td colspan="2"><strong><fmt:formatNumber value="${clmExchangesList.payCrncyPayAmt}" pattern="#,###" /></strong><spring:message code="mypage.order.lbl.currency" text="원"/></td>
						                          	</tr>			                        	                          		
						                          	<tr class="selBgline">
						                          		<th><spring:message code="mypage.claim.detail.status" text="진행상태"/></th>
						                          		<td colspan="2">
						                          			<ncp:code code='${clmExchangesList.clmStatCd}'/> 
															<c:if test="${clmExchangesList.clmStatCd eq 'COMPT'}">
															 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmExchangesList.comptDt}"/>)
															</c:if>
															<c:if test="${clmExchangesList.clmStatCd ne 'COMPT'}">
															 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmExchangesList.clmDt}"/>)
														    </c:if>
						                          		</td>
						                          	</tr>
						                        </tfoot>
					                        </table>                        
				                        </div>
				                        <!--  //교환배송비 -->
										</c:forEach>
									</c:if>
									<c:if test="${myPage.clmTpCdSearch eq 'RTGOD'}">
										<c:forEach var="clmReturnsList" varStatus="clmGodstatus" items="${clmReturns}">
											<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmReturnsList.lgsDlvspFoExtend}">
											<!-- 주문상품 리스트  -->
											<table class="board-list orderTable orderMsg">
					                          	<colgroup>
					                          		<col style="width:140px">
					                                <col>                                        
			                                        <col style="width:120px">
			                                        <col style="width:120px">
					                          	</colgroup>	   
					                          	<tbody>
					                          		
														<c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
					                              		<!-- loop -->
						                              	<tr>		                             	 
						                                  	<td colspan="2" class="tleft">
						                                      	<div class="product-info">
						                                          	<div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
						                                          		<div class="product-info-text">
						                                             	<div class="product-info-box">
						                                                  	<p class="product-name"><a href="javascript:mypageorder.goGoodsInfo('${clmWrhsGodList.godNo}');">${clmWrhsGodList.godNm}</a></p>
						                                                  	<div class="product-price">
						                                                      	<span><fmt:formatNumber value="${clmWrhsGodList.saleAmt / clmWrhsGodList.clmQty}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
						                                                  	</div>
						                                              	</div>
						                                              	<div class="product-option">
						                                                  	<span>
						                                                  		<c:if test="${clmWrhsGodList.godTpCd ne 'SET_GOD'}">
																					<c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/>
																				</c:if>
																				<c:if test="${clmWrhsGodList.godTpCd eq 'SET_GOD' || clmWrhsGodList.godTpCd eq 'PCKAGE_GOD'}">
																					<c:forEach var="godOptList" varStatus="status" items="${clmWrhsGodList.godOptList}">
																						<c:if test="${status.first ne true }">,&nbsp;</c:if>
																						<c:out value='${godOptList.godNm}'/> : <c:out value='${godOptList.colorCd}'/> / <c:out value='${godOptList.itmNm}'/>
																					</c:forEach>
																				</c:if>
						                                                  	</span>
						                                              	</div>
						                                          	</div>
						                                      	</div>
						                                  	</td>
						                                  	<td><c:out value='${clmWrhsGodList.clmQty}'/></td>
						                                  	<td><spring:message code="mypage.claim.list.lbl.return" text="반품"/><ncp:code code='${clmReturnsList.clmStatCd}'/></td>	
						                               	</tr>
					                               		<tr class="seldMsg">
					                               			<th><spring:message code="mypage.order.detail.lbl.claim.return.cause" text="반품사유"/></th>
					                               			<td colspan="3"><ncp:code code='${clmWrhsGodList.clmResnCd}'/>/<c:out value='${clmWrhsGodList.clmResnCont}'/></td>
														</tr>
					                               		<!-- //loop -->
					                               		</c:forEach>
					                               	                   
					                            </tbody>
					                         </table>		                        
					                        <!-- //주문상품 리스트  -->
										
											</div>
										</div>
										<!--  수거지 정보 -->
				                        <div class="mgInfoBox">
				                        	<h3><spring:message code="mypage.claim.detail.lbl.delivery.pickup.info" text="수거지 정보"/></h3>
											<div class="orderAdd odAddBox">																	
												<dl>
													<dt><spring:message code="mypage.claim.detail.lbl.delivery.name" text="신청자"/></dt><!-- 2018.12.06 수정 -->
													<dd><c:out value='${listDlvsp.addrseNm}'/></dd>
												</dl>
												<dl>
													<dt><spring:message code="mypage.claim.detail.lbl.delivery.address" text="수거지"/></dt><!-- 2018.12.06 수정 -->
													<dd><c:out value='${listDlvsp.addrseBaseAddr}'/>&nbsp;<c:out value='${listDlvsp.addrseDetailAddr}'/></dd>
												</dl>
												<dl>
													<dt><spring:message code="mypage.order.detail.lbl.delivery.phone" text="연락처"/></dt>
													<dd>
														<c:if test="${listDlvsp.addrseMobilAreaNo ne '' and listDlvsp.addrseMobilAreaNo ne null}">
															<c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/>
														</c:if>
														<c:if test="${listDlvsp.addrseTelAreaNo ne '' and listDlvsp.addrseTelAreaNo ne null}">  
															| <c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/>
														</c:if>
													</dd>
												</dl>													
											</div>	
				                       	</div>
				                       	<!-- //수거지 정보 -->    
				                        
										<!--  환불 정보 -->
				                        <div class="mgInfoBox">
				                        	<h3><spring:message code="mypage.claim.detail.ttl.return.pay.info" text="환불 정보"/></h3>
				                        	<table class="tbTotalList">
				                        		<caption><spring:message code="mypage.claim.detail.ttl.return.pay.info" text="환불 정보"/></caption>
						                        <colgroup>
						                             <col style="width:20%">
				                                     <col style="width:">
				                                     <col style="width:">
						                        </colgroup>	   
						                        <tbody>
						                          	<tr>
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.goods" text="상품주문금액"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.saleSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>             	
						                          	<tr>
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.order.discount" text="주문할인"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.bundleDcSumAmt + clmReturnsList.crsDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>
						                          	<tr>
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.coupon.discount" text="쿠폰할인"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.godCpnDcSumAmt + clmReturnsList.bskCpnDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>
						                          	<tr>
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.mileage.refund" text="마일리지 환급"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.unityPntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>
						                          	<tr class="selmgB">
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.point.refund" text="포인트 환급"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.webpntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>	
						                            <tr class="selLineBox">
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.delivery" text="배송비"/></th>
						                          		<td colspan="2"><fmt:formatNumber value="${clmReturnsList.cnclDlvCst - clmReturnsList.rtgodDlvCst}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
						                          	</tr>
						                        </tbody>
						                        <tfoot>
						                        	<tr>
						                          		<th><spring:message code="mypage.order.detail.lbl.payment.refund.amount" text="환불 금액"/></th>
						                          		<td>
						                          			<span class="selTxtMgL"><ncp:code code='${clmReturnsList.payMnCd}'/></span>
						                          			<c:if test="${clmReturnsList.rfdBnkAcctNo ne '' && clmReturnsList.rfdBnkAcctNo ne null}">
							                          			<span class="selTxtMgL"><ncp:code code='${clmReturnsList.rfdBnkCd}'/></span>
							                          			<span class="selTxtMgL"><c:out value='${clmReturnsList.rfdBnkAcctNo}'/></span>	 
						                          			</c:if>
						                          		</td>
						                          		<td>
						                          			<strong>
						                          				<c:if test="${clmReturnsList.payExchgRtCrncySumAmt > 0}"><fmt:formatNumber value="${clmReturnsList.payExchgRtCrncySumAmt}" pattern="#,###" /></c:if>
																<c:if test="${clmReturnsList.payExchgRtCrncySumAmt <= 0}">0</c:if>
						                          			</strong>
						                          			<spring:message code="mypage.order.lbl.currency" text="원"/>
						                          		</td>
						                          	</tr>		                          		
						                          	<tr class="selBg">
						                          		<th><spring:message code="mypage.claim.detail.status" text="진행상태"/></th>
						                          		<td colspan="2">
						                          			<ncp:code code='${clmReturnsList.clmStatCd}'/>
															<c:if test="${clmReturnsList.clmStatCd eq 'COMPT'}">
																 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmReturnsList.comptDt}"/>)
															</c:if>
															<c:if test="${clmReturnsList.clmStatCd ne 'COMPT'}">
															 	 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmReturnsList.clmDt}"/>)
														    </c:if>
						                          		</td>
						                          	</tr>
						                        </tfoot>
					                        </table>                        
				                        </div>
				                        <!--  //환불 정보 -->
				                        </c:forEach>
				                    	</c:forEach>
									</c:if>
								</c:if>
	                        	<!--  // 주문번호, 상품 리스트 -->    
                        
                        	<c:if test="${myPage.clmYn ne 'Y'}">
                        	<!--  결제 정보 -->
                        	<div class="mgInfoBox">
                        		<h3><spring:message code="mypage.order.detail.lbl.payment.info" text="결제 정보"/></h3>
                        		<table class="tbTotalList">
                        			<caption><spring:message code="mypage.order.detail.lbl.payment.info" text="결제 정보"/></caption>
			                        <colgroup>
			                             <col style="width:20%">
	                                     <col style="width:">
	                                     <col style="width:">
			                        </colgroup>	   
		                        	<tbody>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.goods" text="상품금액"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.saleSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
										<tr class="selLineBoxBt">
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.delivery" text="배송비"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.dlvCstSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>		                          	
			                          	<tr class="selMgT">
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.order.discount" text="주문할인"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.bundleDcSumAmt + orderInfo.crsDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.coupon.discount" text="쿠폰할인"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.godCpnDcSumAmt + orderInfo.bskCpnDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.mileage.use" text="사용 마일리지"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.unityPntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.inquiry.use" text="사용 포인트"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${orderInfo.webpntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>		                          			                          	
			                        </tbody>
			                        <tfoot>
			                        	<tr class="selLineBoxBt">
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.total" text="총 결제금액"/></th>
			                          		<td>
			                          			<span class="selTxtMgL"><ncp:code code='${orderInfo.payList[0].payMnCd}'/></span>
			                          			<c:if test="${orderInfo.payList[0].payMnCd eq 'VIRTL_BNK_ACCT_PAY' or orderInfo.payList[0].payMnCd eq 'NON_BNKBOK_PAY' }">
				                          			<span class="selTxtMgL"><c:out value='${orderInfo.payList[0].bnkNm}'/></span>
				                          			<span class="selTxtMgL"><c:out value='${orderInfo.payList[0].bnkAcctNo}'/></span>
			                          			</c:if>	 
			                          		</td>
			                          		<td>
			                          			<strong>
			                          				<c:if test="${orderInfo.payExchgRtCrncySumAmt > 0}"><fmt:formatNumber value="${orderInfo.payExchgRtCrncySumAmt}" pattern="#,###" /></c:if>
													<c:if test="${orderInfo.payExchgRtCrncySumAmt < 1}">0</c:if>
			                          			</strong>
			                          			<spring:message code="mypage.order.lbl.currency" text="원"/>
			                          		</td>
			                          	</tr>		                          		
			                          	<tr>
			                          		<td colspan="2"><span class="selTxtMgL"><spring:message code="mypage.order.detail.lbl.payment.accml.prearnge" text="적립예정"/>&nbsp;<spring:message code="mypage.order.detail.lbl.payment.mileage" text="마일리지"/></span></td>
			                          		<td class="fc_red"><fmt:formatNumber value="${orderInfo.unityPntAccmlSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>
			                          	</tr>
			                        </tfoot>
	                        	</table>                        
                        	</div>
                        	<!--  //결제 정보 -->
                        	</c:if>
                        
						<!--  취소 정보 -->
						<c:if test="${not empty clmCancels}">
							<c:forEach var="clmCancelsList" varStatus="clmStatus" items="${clmCancels}">
                        		<div class="mgInfoBox">
                        			<h3><spring:message code="mypage.order.detail.lbl.claim.cancel.info" text="취소 정보"/></h3>
                        			<table class="tbTotalList">
	                        		<caption><spring:message code="mypage.order.detail.lbl.claim.cancel.info" text="취소 정보"/></caption>
			                        <colgroup>
			                             <col style="width:20%">
	                                     <col style="width:">
	                                     <col style="width:">
			                        </colgroup>	   
			                        <tbody>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.goods" text="상품금액"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.saleSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>             	
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.order.discount" text="주문할인"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.bundleDcSumAmt + clmCancelsList.crsDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.coupon.discount" text="쿠폰할인"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.godCpnDcSumAmt + clmCancelsList.bskCpnDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.mileage.refund" text="마일리지 환급"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.unityPntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.point.refund" text="포인트 환급"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.webpntUseSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>	
			                          	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.delivery" text="배송비"/></th>
			                          		<td colspan="2"><fmt:formatNumber value="${clmCancelsList.clmDlvCstSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>		                          		
			                          	</tr>	                          			                          	
			                        </tbody>
		                        	<tfoot>
			                        	<tr>
			                          		<th><spring:message code="mypage.order.detail.lbl.payment.refund.amount" text="환불 금액"/></th>
			                          		<td>
			                          			<span class="selTxtMgL"><ncp:code code='${orderInfo.payList[0].payMnCd}'/></span>
			                          			<c:if test="${clmCancelsList.rfdBnkAcctNo ne '' && clmCancelsList.rfdBnkAcctNo ne null}">
				                          			<span class="selTxtMgL"><ncp:code code='${clmCancelsList.rfdBnkCd}'/></span>
				                          			<span class="selTxtMgL"><c:out value='${clmCancelsList.rfdBnkAcctNo}'/></span>	
			                          			</c:if>
			                          		</td>
			                          		<td>
			                          			<strong>
			                          				<c:if test="${clmCancelsList.payExchgRtCrncySumAmt > 0}"><fmt:formatNumber value="${clmCancelsList.payExchgRtCrncySumAmt}" pattern="#,###" /></c:if>
													<c:if test="${clmCancelsList.payExchgRtCrncySumAmt <= 0}">0</c:if>
			                          			</strong>
			                          			<spring:message code="mypage.order.lbl.currency" text="원"/>
			                          		</td>
			                          	</tr>		                          		
			                          	<tr class="selBg">
			                          		<th><spring:message code="mypage.order.detail.lbl.claim.step" text="진행단계"/></th>
			                          		<td colspan="2">
			                          			<ncp:code code='${clmCancelsList.clmStatCd}'/> 
												<c:if test="${clmCancelsList.clmStatCd eq 'COMPT'}">
												 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmCancelsList.comptDt}"/>)
												</c:if>
												<c:if test="${clmCancelsList.clmStatCd ne 'COMPT'}">
												 (<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${clmCancelsList.clmDt}"/>)
											    </c:if>
			                          		</td>
			                          	</tr>
		                        	</tfoot>
	                        	</table>                        
                        	</div>
                        	</c:forEach>
						</c:if>
	                    <!--  //취소 정보 -->  
	                    
                        
                        <div class="btnWrapBox">	
                        	<c:if test="${myPage.clmYn ne 'Y'}">				
								<a href="/mypage/order/list" class="btn fill">확인</a>
							</c:if>
							<c:if test="${myPage.clmYn eq 'Y'}">				
								<a href="/mypage/claim/list" class="btn fill">확인</a>
							</c:if>
						</div>
						
						<c:if test="${myPage.clmYn ne 'Y' && orderInfo.ordTpCd  eq 'SHOP_PKUP_ORD'}">
							<div class="mgInfoBox">
	                        	<ul class="text-list02">      
									<li>[상품준비완료] 안내문자(알림톡)를 수신하신 후 방문하시거나, 해당 매장과 통화하신 후 방문하시기 바랍니다.</li>
									<li>상품 수령 시, [상품준비완료]문자 모바일 MLB의 회원 바코드를 반드시 지참해야 합니다.</li>
									<li>16시 이후 결제되는 매장픽업 주문은 당일 수령 할 수 없습니다.</li>
									<li>매장픽업 주문 시, [상품준비완료] 안내문자(알림톡)을 수신하신 익일 영업시간 이내로 상품을 수령하지 않을 경우 주문은 자동으로 취소됩니다.</li>                            
	                            </ul>
	                        </div>
                       	</c:if>
						
					</div>		
				</div>		
				<!--  // 일반주문 상세   -->	
				

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->


	<!-- layerpopup - 배송지 변경 -->
	<article id="layerPopupChangeAddress" class="layer-popup deliveryModi-pop lypopDeliveryMd"></article>
	<!-- //layerpopup - 배송지 변경 -->

	<!-- layerpopup - 배송지선택 -->
	<article id="layerPopupAddress" class="layer-popup layer-type03"></article>
	<!-- //layerpopup - 배송지선택 -->

	<%@ include file="/WEB-INF/views/common/layerpop/zipcode.jsp"%>

	<!-- layerpopup - 주문 전체취소 -->
	<article id="layerPopupCancelAll" class="layer-popup lypopOdAllCancel"><!-- 2018-05-16 -->
	</article>
	<!-- //layerpopup - 주문 전체취소 -->

	<!-- layerpopup - 결제하기 -->
	<article id="layerPopupPayment" class="layer-popup lypopPayModify inlayer"></article>
	<!-- //layerpopup - 결제하기 완료 -->

	<!-- layerpopup - 배송비결제 완료 -->
	<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.compt.include.jsp"%>
	<!-- //layerpopup - 배송비결제 완료 -->
	
	<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.popup.jsp"%>	
<script>


    function getLayerPopupDeliveryChange(ordNo, dlvPcupspTurn, type, ordTpCd) {
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : ordNo ,'dlvPcupspTurn' : dlvPcupspTurn, 'type' : type, 'ordTpCd' : ordTpCd};
		$("#layerPopupChangeAddress").load("<c:url value='/mypage/order/delivery/edit'/>", strParams);
    }
	
    /**
     * 전체취소 레이어 팝업
     */
    function getLayerPopupTotalCancel(ordNo,ordStatCd) {
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'ordNo' : ordNo, 'ordStatCd':ordStatCd};
		$("#layerPopupCancelAll").load("<c:url value='/mypage/claim/cancel/total/new'/>", strParams);
    }

    /**
     *  결제수단변경
     */
    function jsPayMethodChange() {
    	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : '<c:out value="${orderInfo.ordNo}"/>'};
    	$("#layerPopupPayment").load("<c:url value='/mypage/order/payment/edit'/>", strParams);
    }
    
    /**
     *  배송비결제
     */
    function jsPayMethodAdd(ordNo, clmNo) {
    	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : ordNo, 'type' : 'clmDlvAmtPay', 'clmNo' : clmNo};
    	$("#layerPopupPayment").load("<c:url value='/mypage/order/payment/edit'/>", strParams);
    }
    
 	// 상태 변경 후 처리
    function pageReload() {
    	location.reload();
    }

 	function goList() {
 		if ("${myPage.clmYn}" == "Y") {
 			mypageorder.goClaimList();
 		} else {
 			mypageorder.goOrderList();
 		}
 	}
 	
 	// 매장찾기
 	function showPopupShop(shopId){

 		commonMap = new google.maps.Map(document.getElementById("common_shopMap"), {
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
 	
 	function addReOrder(clmNo){
 		$.ajax({
			type:"post"
			,url:"/cart/goods/add/reorder.json"
			,data : "ordNo=${orderInfo.ordNo}&clmNo="+clmNo
			,dataType: "json"
			,async : false
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success : function(ret) {
				location.href = '/order/orderform/new';
			}
			,error : function(xhr) {
				var data = $.parseJSON(xhr.responseText)
				alert(data.message);
			}
			,complete : function(data) {
				
			}
		});
 	}
 	
 	function addPayReload() {
 		pageReload();
    }
 	
 	function popupDealingsDetailedStatement(ordNo) {
//  		var url = '<ncp:prop key="ncp.url.pc_MLB.root.secure"/>' + "/mypage/order/popup/dealingsDetailedStatement.popup" + "?ordNo=" + ordNo;
 		var url = '<ncp:prop key="ncp.url.pc_MLB.root"/>' + "/mypage/order/popup/dealingsDetailedStatement.popup" + "?ordNo=" + ordNo;
 		window.open(url, 'dealDetailStatePopup', 'width=800, height=800, resizable=0, scrollbars=yes, status=0, titlebar=0' );
 	}
</script>