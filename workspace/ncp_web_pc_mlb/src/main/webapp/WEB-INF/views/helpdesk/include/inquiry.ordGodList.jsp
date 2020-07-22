<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<div class="board-list">
 <form id ="ogForm" action ="<c:url value ='/helpdesk/csInquiry/ord/list' />" method ="get">
<input type ="hidden" id ="pageNo" name ="pageNo" value ="" />
<div >
	<table>
		<caption>주문번호 조회 - 주문번호, 주문일자, 상품명, 수량, 결제금액, 주문/배송상태.</caption>
		<colgroup>
			<col style="width:40px;">
			<col style="width:130px;">
			<col style="width:80px;">
			<col>
			<col style="width:50px;">
			<col style="width:100px;">
			<col style="width:100px;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col"></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordNo.txt" /></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordDate.txt" /></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordNm.txt" /></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordCnt.txt" /></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordQnt.txt" /></th>
				<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.dlvState.txt" /></th>
			</tr>
		</thead>
		<tbody id="ordGodList">
		<c:if test="${empty ordGodList}">
			<tr>
				<td colspan="7" class="no-result"><spring:message code="helpdesk.popup.selectord.list.noResult.txt" /></td>
			</tr>
			</c:if>
			<c:if test="${!empty ordGodList}">
			<c:forEach var="lists" items="${ordGodList}" >
			<tr>
				<td>
				<span class="rdo-skin">
					<input type="radio" name="rdoOrder" value="${ lists.ord.ordNo}" group="${ lists.ordGod.ordGodTurn}">
					<span>선택</span>
				</span>
				<label></label>
				</td>
				<td>${ lists.ord.ordNo}</td>
				<td>${ lists.ordDt}</td>
				<td class="tleft">${ lists.ordGod.godNm}</td>
				<td>${ lists.ordGod.ordQty}</td>
				<td><fmt:formatNumber value="${ lists.ordGod.saleAmt}" type="number"/></td>
				<td>
					<span>
						<c:if test="${lists.ordGod.ordQty < 1 && lists.ord.ordStatCd ne 'ALL_CNCL' }">-</c:if>
						<c:if test="${lists.ordGod.ordQty > 0 || lists.ord.ordStatCd eq 'ALL_CNCL'}">
							<c:choose>
								<c:when test="${lists.ord.ordStatCd eq 'PAY_WAIT' || lists.ord.ordStatCd eq 'DEPST_WAIT' || lists.ord.ordStatCd eq 'RESVE_ORD_DEPST_WAIT'}">
									<ncp:code code='DEPST_WAIT'/>
								</c:when>
								<c:when test="${lists.ord.ordStatCd eq 'PAY_COMPT'}">
									<ncp:code code='PAY_COMPT'/>
								</c:when>
								<c:when test="${lists.ord.ordStatCd eq 'DLV_PRPARE' || lists.ord.ordStatCd eq 'DLV_PROGRS' || lists.ord.ordStatCd eq 'DLV_COMPT'}">
									<%-- 상품단위의 상태 노출을 위해 상품별 배송상태를 확인한다. --%>
									<c:choose>
										<c:when test="${lists.ordGod.dlvStatCd eq 'DLV_WAIT' || lists.ordGod.dlvStatCd eq 'DLIVY_DRCT_WAIT' || lists.ordGod.dlvStatCd eq 'SHTG_RCEPT'}">
											<ncp:code code='PAY_COMPT'/>
										</c:when>
										<c:when test="${lists.ordGod.dlvStatCd eq 'DLIVY_DRCT'}">
											<ncp:code code='DLV_PRPARE'/>
										</c:when>
										<c:when test="${lists.ordGod.dlvStatCd eq 'DLIVY_COMPT'}">
											<ncp:code code='DLV_PROGRS'/>
										</c:when>
										<c:when test="${lists.ordGod.dlvStatCd eq 'DLV_COMPT'}">
											<ncp:code code='DLV_COMPT'/>
										</c:when>
										<c:otherwise>
											<ncp:code code='${lists.ordGod.dlvStatCd}'/>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<ncp:code code='${lists.ord.ordStatCd}'/>
								</c:otherwise>
							</c:choose>
						</c:if>
					</span>
				</td>
			</tr>
			</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<c:if test ="${!empty ordGodList}">
	<div class="page">
		<c:if test="${currentPage > 1 }">
			<a class="first" href="javascript:goNoticeList('1')" _onclick="return false;" title="첫 페이지">Prev</a>
		</c:if>
		<c:if test="${preFlag }">
			<a class="prev" href="javascript:goNoticeList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
		</c:if>
		<span>
		<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:choose>
					<c:when test="${currentPage eq i}">
					<span class="on"><strong title="현재 페이지">${i}</strong></span>
					</c:when>
					<c:otherwise>
					<a href="javascript:goNoticeList('${i}')" ><span title="현재 페이지">${i}</span></a>
					</c:otherwise>
				</c:choose>
		</c:forEach>
		</span>
		<c:if test="${nextFlag }">
			<a class="next" href="javascript:goNoticeList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
		</c:if>
		<c:if test="${currentPage < totalPage}">
		<a class="last" href="javascript:goNoticeList('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
		</c:if>
	</div>
</c:if>
<c:if test ="${empty ordGodList}">
</c:if>
 </form>
</div>
<script>
	$(document).ready(function(){
		$("#noticeList").attr("class","sel");

	});

	function goNoticeList(pageNo){
		if(pageNo == ""){
			pageNo = 1;
		}
		$("#ogForm").find('#pageNo').val(pageNo);
		$('#btnlistsearch').click();
	}
</script>