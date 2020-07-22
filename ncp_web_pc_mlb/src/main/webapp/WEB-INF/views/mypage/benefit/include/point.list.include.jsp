<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<div class="board-list">
    <table>
        <caption>포인트</caption>
        <colgroup>
            <col style="width:100px;">
            <col>
            <col style="width:100px;">
            <col style="width:100px;">
            <col style="width:190px;">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">일자</th>
                <th scope="col">내용</th>
                <th scope="col">적립내역</th>
                <th scope="col">사용내역</th>
                <th scope="col">유효기간</th>
            </tr>
        </thead>
        <tbody>
            <c:if test ="${empty purpleCoinList}">
                <tr>
                    <td colspan="5" class="no-result">조회된 포인트 내역이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="list" varStatus="status" items="${purpleCoinList}">
                <tr>
                    <td>${list.mbrWebpntHistExt.occurDtStr}</td>
                    <td class="tleft">
                        <c:choose>
                            <c:when test ="${empty list.mbrWebpntHistExt.resnDscr}" >${list.mbrWebpntHistExt.webpntResnNm}</c:when>
                            <c:when test ="${empty list.mbrWebpntHistExt.webpntResnNm}" >${list.mbrWebpntHistExt.resnDscr}</c:when>
                            <c:otherwise>${list.mbrWebpntHistExt.webpntResnNm} / ${list.mbrWebpntHistExt.resnDscr}</c:otherwise>
                        </c:choose>
                        <c:if test="${list.mbrWebpntHistExt.webpntResnCd eq 'PCH'}">(<a href="javascript:jsOrderInfo('${list.mbrWebpntHistExt.ordNo}');" class="order-number">${list.mbrWebpntHistExt.ordNo}</a>)</c:if>
                    </td>
                    <td>
                        <span class="text-color01"><fmt:formatNumber value="${list.mbrWebpntHistExt.accmlAmt=='' ? 0:list.mbrWebpntHistExt.accmlAmt}" type="number"  /></span>
                    </td>
                    <td><fmt:formatNumber value="${empty list.mbrWebpntHistExt.useAmt ? 0 : list.mbrWebpntHistExt.useAmt * -1}" type="number"  /></td>
                    <td>${list.mbrWebpntHistExt.useBegDtStr} ~ ${list.mbrWebpntHistExt.useEndDtStr}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<c:if test ="${!empty purpleCoinList}">
    <div class="page">
        <!-- 링크 없을때
        <span class="first">첫 페이지</span>
        <span class="prev">이전 페이지</span>
        -->
        <c:if test="${currentPage > 1 }">
            <a href="javascript:goPointList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
        </c:if>
        <c:if test="${preFlag }">
            <a href="javascript:goPointList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
        </c:if>

        <span>
            <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <strong title="현재 페이지">${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:goPointList('${i}')">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </span>

        <c:if test="${nextFlag }">
            <a href="javascript:goPointList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
        </c:if>
        <c:if test="${currentPage < totalPage}">
            <a href="javascript:goPointList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
        </c:if>
        <!-- 링크 없을때
        <span class="next">다음 페이지</span>
        <span class="last">마지막 페이지</span>
        -->
    </div>
</c:if>
<c:if test ="${empty purpleCoinList}">
    <%--Empty Paging --%>
</c:if>

<script>
    $(document).ready(function(){
        setPointCnt('${totalRow}');
    });
</script>
