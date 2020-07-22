<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<c:if test ="${mypageMainYn ne 'Y'}">
<div class="odResulCon">
</c:if>
	<c:if test="${empty orderList}">	
		<!--  NO Result -->
		<div class="odResulBox">
			<div class="orderNb noResult">
				<spring:message code="mypage.order.list.lbl.noresult" />
			</div>	
		</div> 
		<!--  //NO Result -->
	</c:if>
	<c:forEach var="ord" varStatus="status" items="${orderList}">
		<div class="odResulBox">
			<div class="orderNb">
				<span><ncp:code code='${ord.ordTpCd}'/></span>
               	<span><em><spring:message code="mypage.order.list.lbl.orderdate" /></em> <c:out value='${ord.ordDt2}'/></span>
               	<span><em><spring:message code="mypage.order.list.lbl.orderno" /></em> <a href="#" onclick="mypageorder.goOrderView('<c:out value="${ord.ordNo}"/>');"><c:out value='${ord.ordNo}'/></a></span>
			</div>
			<table class="board-list orderTable">
				<colgroup>
					<col style="width:">
                    <col style="width:80px">
                    <col style="width:120px">
                    <col style="width:120px">
                    <col style="width:145px">
				</colgroup>
				<tbody>
					<c:forEach var="ordGod" varStatus="status" items="${ord.ordGodList}">
						<c:set value="${ordGod.godNo}" var="godNo"/>
						<c:if test="${not empty ordGod.upGodNo}">
							<c:set value="${ordGod.upGodNo}" var="godNo"/>
						</c:if>
						<c:if test="${ordGod.godTpCd ne 'PCHS_GFT' && ordGod.godTpCd ne 'CNVRS_GFT'}">
							<tr>
								<td class="tleft">
									<div class="product-info <c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">set</c:if>">
										<div class="product-info-img">
											<a href="javascript:mypageorder.goGoodsInfo('${godNo}');">
												<img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);">
											</a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<c:if test="${ordGod.pckageGodTpCd eq 'ADIT_CPST_GOD'}">
													<div class="product-more-info">
														<span class="text-color01">[<spring:message code="mypage.order.list.lbl.addcomposition" />]</span>
													</div>
												</c:if>
												<p class="product-name">
													<a href="javascript:mypageorder.goGoodsInfo('${godNo}');">
														<c:out value='${ordGod.godNm}'/>
													</a>
												</p>
												<div class="product-price">
													<span><fmt:formatNumber value="${ordGod.saleAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></span>
												</div>
											</div>
											<div class="product-option">
												<c:if test="${ordGod.godTpCd eq 'SET_GOD'}">
													<span>
														<c:forEach var="godOpt" varStatus="status" items="${ordGod.godOptList}">
															<c:if test="${status.first ne true }">,&nbsp;</c:if>
															<c:out value='${godOpt.prdlstNm}'/> : <c:out value='${godOpt.colorCd}'/> / <c:out value='${godOpt.itmNm}'/>
														</c:forEach>
													</span>
												</c:if>
												<c:if test="${ordGod.godTpCd ne 'SET_GOD'}">
													<span>
														<c:out value='${ordGod.colorCd}'/> / <c:out value='${ordGod.itmNm}'/>
													</span>
												</c:if>
											</div>
										</div>
									</div>
								</td>
								<td><c:out value='${ordGod.ordQty}'/></td>
								<td>
									<c:choose>
										<c:when test="${ordGod.godTpCd eq 'SET_GOD'}">
										    <fmt:formatNumber value="${ordGod.payAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" />
										</c:when>
										<c:otherwise>
											<fmt:formatNumber value="${ordGod.saleAmt*(ordGod.ordQty)}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" />
										</c:otherwise>
									</c:choose>
								</td>
								<td class="selBox">
									<span>
										<c:if test="${ordGod.realOrdQty < 1 && ord.ordStatCd ne 'ALL_CNCL' }">
											-
										</c:if>
										<c:if test="${ordGod.realOrdQty > 0 || ord.ordStatCd eq 'ALL_CNCL'}">
											<c:choose>
												<c:when test="${ord.ordStatCd eq 'PAY_WAIT' || ord.ordStatCd eq 'DEPST_WAIT' || ord.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
													<ncp:code code='DEPST_WAIT'/>
												</c:when>
												<c:when test="${ord.ordStatCd eq 'PAY_COMPT'}">
													<span class="text-color01"><ncp:code code='PAY_COMPT'/></span>
												</c:when>
												<c:when test="${ord.ordStatCd eq 'DLV_PRPARE' || ord.ordStatCd eq 'DLV_PROGRS' || ord.ordStatCd eq 'DLV_COMPT'}">
													<span class="text-color01">
														<%-- 상품단위의 상태 노출을 위해 상품별 배송상태를 확인한다. --%>
														<c:choose>
															<c:when test="${ordGod.dlvStatCd eq 'DLV_WAIT' || ordGod.dlvStatCd eq 'DLIVY_DRCT_WAIT' || ordGod.dlvStatCd eq 'SHTG_RCEPT'}">
																<ncp:code code='PAY_COMPT'/>
															</c:when>
															<c:when test="${ordGod.dlvStatCd eq 'DLIVY_DRCT'}">
																<ncp:code code='DLV_PRPARE'/>
															</c:when>
															<c:when test="${ordGod.dlvStatCd eq 'DLIVY_COMPT'}">
																<ncp:code code='DLV_PROGRS'/>
															</c:when>
															<c:when test="${ordGod.dlvStatCd eq 'DLV_COMPT'}">
																<ncp:code code='DLV_COMPT'/>
															</c:when>
															<c:otherwise>
																<ncp:code code='${ordGod.dlvStatCd}'/>
															</c:otherwise>
														</c:choose>
													</span>
												</c:when>
												<c:otherwise>
													<ncp:code code='${ord.ordStatCd}'/>
												</c:otherwise>
											</c:choose>
										</c:if>
									</span>
									<%-- 결제 대기,입금대기,예약주문입금대기 일때 결제수단 변경가능 --%>
									<c:if test="${ord.ordStatCd eq 'PAY_WAIT' || ord.ordStatCd eq 'DEPST_WAIT' || ord.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
										<span>
											<a href="#layerPopupPayment" class="btn gray sm d_layer_open" onclick="openPayMethodChangeLayer('${ord.ordNo}');return false;">
												<spring:message code="mypage.order.list.btn.changepay" />
											</a>
										</span>
									</c:if>
								</td>
								<td class="selBox">
									<c:choose>
										<c:when test="${ord.ordTpCd  eq 'SHOP_PKUP_ORD'}">
											<c:if test="${ ordGod.dlvStatCd ne 'DLV_COMPT'}">
												<%-- 사은품은 클레임 불가 --%>
												<c:if test="${ordGod.realOrdQty > 0 && ordGod.godTpCd ne 'PCHS_GFT' && ordGod.godTpCd ne 'CNVRS_GFT' && ordGod.dlvPcupspSectCd ne 'CLM_DLVSP' && ordGod.prmTpCd ne 'ORD_DC'}">
													<span><a href="#none" class="btn sm gray" onclick="mypageorder.goUnitCancel('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dlvPcupspTurn}'/>'); return false;"><spring:message code="mypage.order.list.btn.ordercancel" /></a></span>
												</c:if>
											</c:if>
										</c:when>
										<c:otherwise>
											<c:if test="${(ordGod.dlvStatCd eq 'DLV_WAIT' || ordGod.dlvStatCd eq 'DLIVY_DRCT_WAIT'|| ordGod.dlvStatCd eq 'SHTG_RCEPT')
													&& ord.ordTpCd ne 'LAG_QTY_ORD' && ord.ordStatCd ne 'DEPST_WAIT' && ord.ordStatCd ne 'RESVE_ORD_DEPST_WAIT' && ord.ordStatCd ne 'ALL_CNCL' }">
												<%-- 사은품은 클레임 불가 --%>
												<c:if test="${ordGod.realOrdQty > 0 && ordGod.godTpCd ne 'PCHS_GFT' && ordGod.godTpCd ne 'CNVRS_GFT' && ordGod.dlvPcupspSectCd ne 'CLM_DLVSP' && ordGod.prmTpCd ne 'ORD_DC'}">
													<span><a href="#none" class="btn sm gray" onclick="mypageorder.goUnitCancel('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dlvPcupspTurn}'/>', '<c:out value='${ord.ordDt2}'/>'); return false;"><spring:message code="mypage.order.list.btn.ordercancel" /></a></span>
												</c:if>
											</c:if>
										</c:otherwise>
									</c:choose>
									<%-- 배송추적 버튼 --%>
									<c:if test="${(ordGod.dlvStatCd eq 'DLIVY_COMPT' || ordGod.dlvStatCd eq 'DLV_COMPT') && not empty ordGod.dmstcWaybilNo && ordGod.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
										<%-- TODO : 트래킹 부분 수정 - 우체국택배와 아닌걸로 구분 하자.. --%>
										<span><a href="#none" class="btn sm gray" onclick="mypageorder.deliveryTracking('<c:out value="${ordGod.dmstcWaybilNo}"/>', '<c:out value="${ordGod.cdDscr}"/>','<c:out value="${ordGod.dlvComCd}"/>', '<c:out value="${ordGod.dlvStatCd}"/>');return false;"><spring:message code="mypage.order.list.btn.deliverytracking" /></a></span>
									</c:if>
									<c:if test="${ordGod.dlvStatCd eq 'DLIVY_COMPT' && not empty ordGod.dmstcWaybilNo}">
										<span><a href="#none" class="btn sm gray" onclick="mypageorder.updateDeliveryStatus('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dmstcWaybilNo}'/>'); return false;"><spring:message code="mypage.order.list.btn.deliveryconfirm" /></a></span>
									</c:if>
									<%-- 교환, 반품, 구매확정 버튼 --%>
									<c:if test="${ordGod.realOrdQty > 0 && ordGod.dlvStatCd eq 'DLV_COMPT' && ordGod.godTpCd ne 'PCHS_GFT' && ordGod.godTpCd ne 'CNVRS_GFT' && ordGod.godTpCd ne 'GFCT' && ord.ordTpCd ne 'LAG_QTY_ORD'}">
										<c:if test="${ord.ordStatCd ne 'ALL_CNCL' && ordGod.clmYn eq 'Y' && ordGod.cstmrPchDcsnYn ne 'Y'}">
											<c:if test="${ordGod.godNo ne 'GM0019050713788' && ordGod.godNo ne 'GM0019050713787'}">
												<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestExchange('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dlvPcupspTurn}'/>');return false;"><spring:message code="mypage.order.list.btn.exchange" /></a></span>
											</c:if>
											<c:if test="${ordGod.godNo eq 'GM0019050713788' || ordGod.godNo eq 'GM0019050713787'}">
												<span><a href="javascript:;" class="btn sm gray" onclick="if(confirm('해당상품은 이벤트 상품으로 교환이 불가합니다. \r\n자세한 내용은 이벤트 공지사항 확인해주시기 바랍니다.')) location.href='/special/event/EV201905100000096/Heritage161/view'; else location.href=this.href;"><spring:message code="mypage.order.list.btn.exchange" /></a></span> 
											</c:if>
											<c:if test="${ordGod.godNo ne 'GM0019050713788' && ordGod.godNo ne 'GM0019050713787'}">
												<span><a href="#none" class="btn sm gray" onclick="mypageorder.goRequestReturn('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dlvPcupspTurn}'/>', '<c:out value='${ordGod.prmTpCd}'/>' );return false;"><spring:message code="mypage.order.list.btn.return" /></a></span>
											</c:if>
											<c:if test="${ordGod.godNo eq 'GM0019050713788' || ordGod.godNo eq 'GM0019050713787'}">
												<span><a href="#none" class="btn sm gray" onclick="alert('해당상품은 이벤트 상품으로 불량을 제외한 반품이 불가합니다. \r\n상품불량 반품 진행시 \'확인\'을 눌러주시기 바랍니다.');mypageorder.goRequestReturn('<c:out value='${ord.ordNo}'/>', '<c:out value='${ordGod.dlvPcupspTurn}'/>', '<c:out value='${ordGod.prmTpCd}'/>' );return false;"><spring:message code="mypage.order.list.btn.return" /></a></span> 
											</c:if>
											<span><a href="#none" class="btn sm gray" onclick="mypageorder.updateOrderDecision('${ord.ordNo}', '${ordGod.ordGodTurn }');"><spring:message code="mypage.order.list.btn.purchaseconfirm" /></a></span>
										</c:if>								
									</c:if>
									<%-- 상품리뷰 버튼 --%>
									<c:if test="${ordGod.realOrdQty > 0 && ordGod.dlvStatCd eq 'DLV_COMPT' && ordGod.godEvlCnt == 0 && ordGod.godTpCd ne 'GFCT' && ordGod.cstmrPchDcsnYn eq 'Y' && ordGod.reviewApplyTermYn eq 'Y'}">
										<c:if test="${ordGod.pckageGodTpCd ne 'ADIT_CPST_GOD' or ordGod.dspYn ne 'N'}">
											<span><a class="btn sm gray" href="javascript:mypageorder.goGoodsReview();" ><spring:message code="mypage.order.list.btn.goodsreview" /></a></span>
										</c:if>																					
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${ordGod.gftTpCd eq 'GOD_GFT'}">
							<tr>
								<td class="tleft">
									<div class="product-info product-free-gift">
										<div class="product-info-img"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="사은품이미지" onerror="errorImgShow(this, 100);"></div>
										<div class="product-info-text">
											<div class="product-info-box">
												<div class="product-more-info">
													<span class="text-color01">[<spring:message code="mypage.order.list.lbl.goodsgift" />]</span>
												</div>
												<p class="product-name"><c:out value='${ordGod.godNm}'/></p>
												<div class="product-price">
													<span>0<spring:message code="mypage.order.lbl.currency" text="원"/></span>
												</div>
											</div>
											<c:if test="${not empty ordGod.colorCd || not empty ordGod.itmNm}">
												<div class="product-option">
													<span>
														<c:choose>
															<c:when test="${not empty ordGod.colorCd && not empty ordGod.itmNm}">
																<c:out value='${ordGod.colorCd}'/> / <c:out value='${ordGod.itmNm}'/>
															</c:when>
															<c:when test="${not empty ordGod.colorCd}">
																<c:out value='${ordGod.colorCd}'/>
															</c:when>
															<c:when test="${not empty ordGod.itmNm}">
																<c:out value='${ordGod.itmNm}'/>
															</c:when>
														</c:choose>
													</span>
												</div>
											</c:if>
										</div>
									</div>
								</td>
								<td></td>
                                <td></td>
                                <td class="selBox"></td>
								<td class="selBox">
									<c:if test="${(ordGod.dlvStatCd eq 'DLIVY_COMPT' || ordGod.dlvStatCd eq 'DLV_COMPT') && not empty ordGod.dmstcWaybilNo && ordGod.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
										<ncp:code var="cd" code="${ordGod.dlvComCd}"/>
										<%-- TODO : 트래킹 부분 수정 - 우체국택배와 아닌걸로 구분 하자.. --%>
										<span><a href="#none" class="btn sm gray" onclick="mypageorder.deliveryTracking('<c:out value="${ordGod.dmstcWaybilNo}"/>', '<c:out value="${ordGod.cdDscr}"/>','<c:out value="${ordGod.dlvComCd}"/>', '<c:out value="${ordGod.dlvStatCd}"/>');return false;"><spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/></a></span>
									</c:if>
								</td>
							</tr>
						</c:if>
						<c:if test="${ordGod.gftTpCd eq 'ORD_GFT'}">
							<tr>
								<!-- 주문 사은품 -->
								<td class="tleft">
									<div class="product-info product-free-gift">
										<div class="product-info-img"><img src="<ncp:img path='${ordGod.lstImgUrl }/dims/resize/100x100'/>" alt="사은품이미지" onerror="errorImgShow(this, 100);"></div>
										<div class="product-info-text">
											<div class="product-info-box">
												<div class="product-more-info">
													<span class="text-color01">[<spring:message code="mypage.order.list.lbl.ordergift" />]</span>
												</div>
												<p class="product-name"><c:out value='${ordGod.godNm}'/></p>
												<div class="product-price">
													<span>0<spring:message code="mypage.order.lbl.currency" /></span>
												</div>
											</div>
											<c:if test="${not empty ordGod.colorCd || not empty ordGod.itmNm}">
												<div class="product-option">
													<span>
														<c:choose>
															<c:when test="${not empty ordGod.colorCd && not empty ordGod.itmNm}">
																<c:out value='${ordGod.colorCd}'/> / <c:out value='${ordGod.itmNm}'/>
															</c:when>
															<c:when test="${not empty ordGod.colorCd}">
																<c:out value='${ordGod.colorCd}'/>
															</c:when>
															<c:when test="${not empty ordGod.itmNm}">
																<c:out value='${ordGod.itmNm}'/>
															</c:when>
														</c:choose>
													</span>
												</div>
											</c:if>
										</div>
									</div>
								</td>
								<td></td>
                                <td></td>
                                <td class="selBox"></td>
                                <td class="selBox">
									<c:if test="${(ordGod.dlvStatCd eq 'DLIVY_COMPT' || ordGod.dlvStatCd eq 'DLV_COMPT') && not empty ordGod.dmstcWaybilNo && ordGod.dlivyDrctTpCd  ne 'SHOP_PKUP'}">
										<ncp:code var="cd" code="${ordGod.dlvComCd}"/>
										<%-- TODO : 트래킹 부분 수정 - 우체국택배와 아닌걸로 구분 하자.. --%>
										<span><a href="#none" class="btn sm gray" onclick="mypageorder.deliveryTracking('<c:out value="${ordGod.dmstcWaybilNo}"/>', '<c:out value="${ordGod.cdDscr}"/>','<c:out value="${ordGod.dlvComCd}"/>', '<c:out value="${ordGod.dlvStatCd}"/>');return false;"><spring:message code="mypage.order.list.btn.deliverytracking" text="배송추적"/></a></span>
									</c:if>
								</td>
							</tr>
						</c:if>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:forEach>
<c:if test ="${mypageMainYn ne 'Y'}">
</div>
</c:if>

<c:if test ="${mypageMainYn ne 'Y'}">

<c:if test="${not empty orderList}">

	<div class="page">
		<c:if test="${firstFlag == 'false' }">
			<a href="#" class="first" onclick="goPaging(1);return false;" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag == 'true' }">
			<a href="#" class="prev" onclick="goPaging(${displayPrePage });return false;" title="이전 페이지">이전 페이지</a>
		</c:if>
		<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:if test="${i == currentPage}"><strong title="현재 페이지">${i}</strong></c:if>
				<c:if test="${i != currentPage}">
					<a href="#" onclick="goPaging(${i});return false;" id="${i }">${i}</a>
				</c:if>
			</c:forEach>
		</span>
		<c:if test="${nextFlag == 'true'  }">
			<a class="next" href="#none" onclick="goPaging(${displayNextPage });return false;" title="다음 페이지" id="${displayNextPage }">
			</a>
		</c:if>
		<c:if test="${lastFlag == 'false'}">
			<a class="last" href="#none" onclick="goPaging(${totalPage });return false;" title="마지막 페이지" id="${totalPage }">
			</a>
		</c:if>
	</div>

</c:if>

</c:if>

			
<script>
	
	$(document).ready(function(){
		 setOrderCnt('${totalRow}'); 
	});
	
	function setOrderCnt(orderCnt){
		$("#orderCnt").empty();
		$("#orderCnt").append(orderCnt);
	}

</script>