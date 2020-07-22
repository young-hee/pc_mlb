<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<div class="odResulCon">

	<!--  order LIst 01 -->
	<div class="odResulBox">
		<table class="board-list">
			<caption>증빙서류 조회 테이블</caption>
			<colgroup>
				<col style="width:130px">
				<col style="width:">
				<col style="width:136px">
				<col style="width:150px">
				<col style="width:136px">
				<col style="width:146px">
			</colgroup>
			<thead>
    			<th>주문/결제일</th>
    			<th>주문/클레임 번호</th>
    			<th>거래구분</th>
    			<th>결제수단</th>
    			<th>결제상태</th>
    			<th>결제/취소 금액</th>
    			<th>영수증출력</th>
			</thead>
			<tbody>
    			<c:if test="${empty receiptList}">
    				<tr>
    					<td colspan="7" class="no-result">증빙서류 내역이 없습니다.</td>
    				</tr>
    			</c:if>
                <c:forEach var="list" varStatus="status" items="${receiptList}">
    				<tr>
        				<td><c:out value='${list.ordDt}'/></td>
        				<td><c:out value='${list.ordNo}'/></td>
        				<td>
                            <c:if test ="${list.statCd eq 'godsord'}">상품주문</c:if>
                            <c:if test ="${list.statCd eq 'adddlvamt'}">추가배송비</c:if>
                        </td>
        				<td><ncp:code code="${list.payMnCd}"/></td>
        				<td><ncp:code code="${list.dealTpCd}"/></td>
        				<td><fmt:formatNumber value="${list.payCrncyPayAmt}" pattern="#,###" />원</td>
        				<td>
							<c:choose>
                                <c:when test="${list.payMnCd eq 'CREDT_CARD_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openKCPReceipt('card_bill', '${list.pgAprvNo}', '${list.payNo}', '${list.payCrncyPayAmt}')">카드전표</a>
                                </c:when>
                                <c:when test="${list.payMnCd eq 'RLTM_BNK_ACCT_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openKCPReceipt('acnt_bill', '${list.pgAprvNo}', '${list.payNo}', '${list.payCrncyPayAmt}')">거래명세서</a>
                                </c:when>
                                <c:when test="${list.payMnCd eq 'VIRTL_BNK_ACCT_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openKCPReceipt('vcnt_bill', '${list.pgAprvNo}', '${list.payNo}', '${list.payCrncyPayAmt}')">거래명세서</a>
                                </c:when>
                                <c:when test="${list.payMnCd eq 'MOBIL_PON_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openKCPReceipt('mcash_bill', '${list.pgAprvNo}', '${list.payNo}', '${list.payCrncyPayAmt}')">거래명세서</a>
                                </c:when>
                                <c:when test="${list.payMnCd eq 'RLTM_BNK_ACCT_PAY' or list.payMnCd eq 'VIRTL_BNK_ACCT_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openKCPReceipt('cash_bill', '${list.pgAprvNo}', '${list.payNo}', '${list.payCrncyPayAmt}')">현금영수증</a>
                                </c:when>
                                <c:when test="${list.payMnCd eq 'NAVER_PAY'}">
                                    <a href="#" class="btn sm gray" onclick="openNaverpayReceipt('${list.pgAprvNo}')">네이버증빙</a>
                                </c:when>
							</c:choose>
                        </td>
    				</tr>
                </c:forEach>
			</tbody>
		</table>
	</div>

    <c:if test ="${!empty receiptList}">
        <div class="page">
            <!-- 링크 없을때
            <span class="first">첫 페이지</span>
            <span class="prev">이전 페이지</span>
            -->
            <c:if test="${currentPage > 1 }">
                <a href="javascript:goReceiptList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
            </c:if>
            <c:if test="${preFlag }">
                <a href="javascript:goReceiptList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
            </c:if>

            <span>
                <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <strong title="현재 페이지">${i}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:goReceiptList('${i}')" >${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </span>

            <c:if test="${nextFlag }">
                <a href="javascript:goReceiptList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
            </c:if>
            <c:if test="${currentPage < totalPage}">
                <a href="javascript:goReceiptList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
            </c:if>
            <!-- 링크 없을때
            <span class="next">다음 페이지</span>
            <span class="last">마지막 페이지</span>
            -->
        </div>
    </c:if>
</div>

<script>
	$(document).ready(function(){
		setTotalCnt('${totalRow}');
	});
</script>
