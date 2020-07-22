<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<article id="lypopCuponDown" class="layer-popup lypopCuponDown">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2><spring:message code="goods.option.lbl.coupon.txt2"/></h2>
		<div class="layer-cont ly-box">		
			
			<table class="board-list lyType01">
				<colgroup>
					<col style="width:125px">
					<col style="width:">
					<col style="width:110px">			
				</colgroup>
				<tbody>
					<input type="hidden" name="cpnCount" id="cpnCount" value="${fn:length(content.prmCpnList)}">
					<input type="hidden" name="prmCpnGodPrmNo" id="prmCpnGodPrmNo" value="${content.prmCpnGod.prmNo}">
					<!-- loop -->
					<c:forEach var="coupon" items="${content.prmCpnList}" varStatus="status">
						<c:if test="${coupon.cpnIssuMthdCd eq 'DWLD' }">
							<tr name="trCpnList">
								<td>
									<c:choose>
										<c:when test="${coupon.dcAmt > 0}">
											<span class="cuponImg"><em><spring:message code="goods.option.lbl.protuct"/><fmt:formatNumber value="${coupon.dcAmt}" type="number"/><spring:message code="goods.common.lbl.won"/></em></span> <%-- 100원 --%>
										</c:when>
										<c:otherwise>
	                  						<span class="cuponImg"><em><spring:message code="goods.option.lbl.protuct"/><fmt:formatNumber value="${coupon.dcRt}" type="number"/>%</em></span> <%-- 10% --%>
	 									</c:otherwise>
									</c:choose>
								</td>	
								<td class="selCuponInfo">
									<p class="cuponName"><c:out value="${coupon.prmNm}"/></p>
									<span class="cuponDate">
										<fmt:parseDate var="prmBegDate" value="${coupon.prmBegDate}" pattern="yyyyMMdd" />
										<fmt:parseDate var="prmEndDate" value="${coupon.prmEndDate}" pattern="yyyyMMdd" />										
										<p><fmt:formatDate value="${prmBegDate}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate value="${prmEndDate}" pattern="yyyy-MM-dd" /></p>
									</span>
								</td>
								<td class="selDown"><a href="#" onclick="javascript:goodsCouponDownload('${coupon.prmNo}');" class="btn sm gray pd20"><span>다운로드</span></a></td>
							</tr>
						</c:if>
					</c:forEach>
					<!-- // loop -->
				</tbody>
			</table>					
			<!--  button --> 
			<div class="lyBtnArea"><a href="javascript:closeLayerPopAndReset('lypopCuponDown');" class="btn fill w160"><spring:message code="goods.common.btn.ok"/></a></div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="javascript:closeLayerPopAndReset('lypopCuponDown');" >닫기</button>
		</div>
	</section>
</article>