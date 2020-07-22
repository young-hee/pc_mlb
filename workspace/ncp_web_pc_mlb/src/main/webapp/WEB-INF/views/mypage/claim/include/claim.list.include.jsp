<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.aws.cloudfront.web.resource.url" var="resourceURL"/>


	<c:if test="${empty claimList}">
		<div class="odResulBox">
			<div class="orderNb noResult">
				<spring:message code="mypage.claim.list.lbl.noresult" text="취소/교환/반품 내역이 없습니다."/>
			</div>	
		</div>
	</c:if>
	<c:if test="${not empty claimList}">
		<c:forEach var="list" varStatus="status" items="${claimList}">
			<div class="odResulBox">
				<div class="orderNb">
					<span>
						<c:if test="${list.clmTpCd eq 'PART_CNCL' || list.clmTpCd eq 'ALL_CNCL'}">
							<spring:message code="mypage.claim.list.lbl.cancel" text="취소"/>
						</c:if>
						<c:if test="${list.clmTpCd eq 'GNRL_EXCHG'}">
							<spring:message code="mypage.claim.list.lbl.exchange" text="교환"/>
						</c:if>
						<c:if test="${list.clmTpCd eq 'RTGOD'}">
							<spring:message code="mypage.claim.list.lbl.return" text="반품"/>
						</c:if>
					</span>
                   	<span><em><spring:message code="mypage.order.detail.lbl.claim.acceptdate" text="신청일"/></em> ${list.clmDt}</span>
                   	<span><em><spring:message code="mypage.order.detail.lbl.claim.no" text="클레임번호"/></em> <a href="#none" class="nb" onclick="mypageorder.goClaimView('<c:out value="${list.ordNo}"/>', '<c:out value="${list.clmTpCd}"/>', '<c:out value="${list.clmNo}"/>', '<c:out value="${list.clmStatCd}"/>'); return false;">${list.clmNo}</a></span>
                   	<span><em><spring:message code="mypage.order.receipt.lbl.orderno" text="주문번호"/></em> <a href="#none" class="nb" onclick="mypageorder.goOrderView('<c:out value="${list.ordNo}"/>'); return false;">${list.ordNo}</a></span>
				</div>								
				<table class="board-list orderTable">
	                <colgroup>
	                    <col style="width:">		                            
	                    <col style="width:120px">
	                    <col style="width:145px">                              
	                </colgroup>	          
	                <tbody>
	                    <!-- loop -->
	                    <c:forEach var="ordGodList" varStatus="statusGod" items="${list.ordGodList}">
	                    	<tr>
                               <td class="tleft">
                                   <div class="product-info">
                                       <div class="product-info-img"><a href="javascript:mypageorder.goGoodsInfo('${ordGodList.godNo}');"><img src="<ncp:img path='${ordGodList.lstImgUrl }/dims/resize/100x100'/>" alt="상품이미지" onerror="errorImgShow(this, 100);"></a></div>
                                       <div class="product-info-text">
                                           <div class="product-info-box">
                                               <p class="product-name">
                                               		<a href="javascript:mypageorder.goGoodsInfo('${ordGodList.godNo}');"> ${ordGodList.godNm}</a>
                                               </p>
                                               <div class="product-price">
                                                   <span><fmt:formatNumber value="${ordGodList.saleAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
                                               </div>
                                           </div>
                                           <div class="product-option">
                                               <span>
                                               		<c:if test="${ordGodList.godTpCd ne 'SET_GOD'}">
														<c:out value='${ordGodList.colorCd}'/> / <c:out value='${ordGodList.itmNm}'/>
													</c:if>
													<c:if test="${ordGodList.godTpCd eq 'SET_GOD' || ordGodList.godTpCd eq 'PCKAGE_GOD'}">
														<c:forEach var="godOptList" varStatus="status" items="${ordGodList.godOptList}">
															<c:if test="${status.first ne true }">,&nbsp;</c:if>
															<c:out value='${godOptList.godNm}'/> : <c:out value='${godOptList.colorCd}'/> / <c:out value='${godOptList.itmNm}'/>
														</c:forEach>
													</c:if>
                                               </span>
                                           </div>
                                       </div>
                                   </div>
                               </td>
                               <td><c:out value='${ordGodList.clmQty}'/></td>
                               <td class="selBox">
                               		<span>
                               			<c:if test="${list.clmTpCd eq 'PART_CNCL' || list.clmTpCd eq 'ALL_CNCL'}">
											<spring:message code="mypage.claim.list.lbl.cancel" text="취소"/><ncp:code code='${list.clmStatCd}'/>
										</c:if>
										<c:if test="${list.clmTpCd eq 'GNRL_EXCHG'}">
											<spring:message code="mypage.claim.list.lbl.exchange" text="교환"/><ncp:code code='${list.clmStatCd}'/>
										</c:if>
										<c:if test="${list.clmTpCd eq 'RTGOD'}">
											<spring:message code="mypage.claim.list.lbl.return" text="반품"/><ncp:code code='${list.clmStatCd}'/>
										</c:if>
                               		</span>	
                               		<c:if test="${(list.clmTpCd eq 'GNRL_EXCHG' || list.clmTpCd eq 'RTGOD' || list.clmTpCd eq 'PART_CNCL') && list.dealTpCd eq 'PAY_WAIT' && list.clmStatCd ne 'WTHDRAW'}">
										<span>
											<a href="#layerPopupPayment" class="btn sm gray d_layer_open" onclick="jsPayMethodAdd('${list.ordNo}', '${list.clmNo}'); return false;"><spring:message code="mypage.order.detail.btn.addpay" text="추가결제"/></a>
										<span>
									</c:if>	                                  	
                               </td>		                                 
                            </tr>
	                    </c:forEach>
                    </tbody>
	         	</table>	
	    	</div>
		</c:forEach>
	</c:if>


<c:if test="${not empty claimList}">
	<div class="page">
		<c:if test="${firstFlag == 'false' }">
			<a href="#" class="first" onclick="goPaging(1);" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag == 'true' }">
			<a href="#" class="prev" onclick="goPaging(${displayPrePage });" title="이전 페이지">이전 페이지</a>
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
			<a class="next" href="#none" onclick="goPaging(${displayNextPage });" title="다음 페이지" id="${displayNextPage }">
			</a>
		</c:if>
		<c:if test="${lastFlag == 'false'}">
			<a class="last" href="#none" onclick="goPaging(${totalPage });" title="마지막 페이지" id="${totalPage }">
			</a>
		</c:if>
	</div>
</c:if>

<script>
	
	$(document).ready(function(){
		 setClaimCnt('${totalRow}'); 
	});
	
</script>