<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<div class="board-list">
    <table>
        <caption>쿠폰 내역</caption>
        <colgroup>
            <col style="width:100px;">
            <col style="width:100px;">
            <col>
            <col style="width:100px;">
            <col style="width:100px;">
            <col style="width:170px;">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">쿠폰종류</th>
                <th scope="col">적용조건</th>
                <th scope="col">쿠폰명</th>
                <th scope="col">할인</th>
                <th scope="col">사용일자</th>
                <th scope="col">주문정보</th>
            </tr>
        </thead>
        <tbody>
            <c:if test ="${empty myCouponHistList}">
                <tr>
                    <td colspan="6" class="no-result">조회된 쿠폰 내역이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="list" varStatus="status" items="${myCouponHistList}">
                <tr>
                    <td>
                        <c:if test="${list.cpnKndCd ne 'ONOFLNE_CPN' }">온라인</c:if>
                        <c:if test="${list.cpnKndCd eq 'ONOFLNE_CPN' }">온오프라인</c:if>
                    </td>
                    <td><ncp:code code='${list.cpnTpCd}'/></td>
                    <td class="tleft">
                        <p>${list.prmNm }</p>
                    </td>
                    <td>
                        <span class="text-color01">
                            <fmt:formatNumber value="${list.dcRtAmt}" pattern="#,###" />
                            <c:if test="${list.dcSectCd eq 'FIXRT'}">%</c:if>
                            <c:if test="${list.dcSectCd eq 'FIXAMT'}">원</c:if>
                            <c:if test="${list.dcSectCd eq 'DLV_CST_FREE'}">무료배송</c:if>
                        </span>
                    </td>
                    <td>${list.cpnUseDt}</td>
                    <td>
                        <div class="tbST-R-BtnBox">
                            <c:choose>
                                <c:when test="${!empty list.ordNo}">
                                    <a href="javascript:jsOrderInfo('${list.ordNo}');" class="btn sm gray">주문사용내역보기</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="javascript:;" class="btn sm gray disabled">사용기간만료</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<c:if test ="${!empty myCouponHistList}">
    <div class="page">
        <!-- 링크 없을때
        <span class="first">첫 페이지</span>
        <span class="prev">이전 페이지</span>
        -->
        <c:if test="${currentPage > 1 }">
            <a class="first" href="javascript:goCouponHistList('1')" _onclick="return false;" title="첫 페이지">첫 페이지</a>
        </c:if>
        <c:if test="${preFlag }">
            <a class="prev" href="javascript:goCouponHistList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
        </c:if>

        <span>
            <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <strong title="현재 페이지">${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:goCouponHistList('${i}')" >${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </span>

        <c:if test="${nextFlag }">
            <a class="next" href="javascript:goCouponHistList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
        </c:if>
        <c:if test="${currentPage < totalPage}">
            <a class="last" href="javascript:goCouponHistList('${totalPage}')" _onclick="return false;" title="마지막 페이지">마지막 페이지</a>
        </c:if>
        <!-- 링크 없을때
        <span class="next">다음 페이지</span>
        <span class="last">마지막 페이지</span>
        -->
    </div>
</c:if>
<c:if test ="${empty myCouponHistList}">
</c:if>

<!-- // 사용완료/기간만료 -->

<script>
    $(document).ready(function(){
        setCouponHistCnt('${totalRow}');
    });

</script>
