<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/member/login.js"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="pageNo"  id="pageNo" value=""/>

<div class="board-list">
    <table>
        <caption>쿠폰 적용 가능 상품</caption>
        <colgroup>
            <col>
            <col style="width:100px;">
            <col style="width:100px;">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">상품명</th>
                <th scope="col">상품금액</th>
                <th scope="col">쿠폰적용가격</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${empty myCouponGoodList}">
                <tr>
                    <td colspan="3" class="no-result">적용 가능한 상품이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="myCouponGoodList" varStatus="status" items="${myCouponGoodList}">
                <c:if test="${myCouponGoodList.aplGodSectCd eq 'ALL'}">
                    <tr>
                        <td colspan="3" class="no-result">전체 적용 쿠폰입니다.</td>
                    </tr>
                </c:if>
                <c:if test="${myCouponGoodList.aplGodSectCd ne 'ALL'}">
                    <c:if test="${myCouponGoodList.aplGodSectCd eq 'GOD' or myCouponGoodList.aplGodSectCd eq 'DSP_CTGRY'}">
                        <tr>
                            <td>
                                <div class="product-info">
                                    <div class="product-info-img">
                                        <c:if test="${not empty myCouponGoodList.imgUrl}">
                                            <a href="javascript:goGoodsInfo('${myCouponGoodList.godNo}');"><img src="<ncp:img path='${myCouponGoodList.imgUrl}/dims/resize/75x100'/>" alt="상품이미지"></a>
                                        </c:if>
                                    </div>
                                    <div class="product-info-text">
                                        <div class="product-info-box">
                                            <p class="product-name"><a href="javascript:goGoodsInfo('${myCouponGoodList.godNo}');">${myCouponGoodList.godFullNm}</a></p>
                                        </div>
                                    </div>
                                </div>
                            </td>
                            <td><fmt:formatNumber value="${myCouponGoodList.lastSalePrc}" pattern="#,###" />원</td>
                            <td><b><fmt:formatNumber value="${myCouponGoodList.dcCpnPrc}" pattern="#,###" /></b>원</td>
                        </tr>
                    </c:if>
                    <c:if test="${myCouponGoodList.aplGodSectCd eq 'BRND'}">
                        <tr>
                            <td class="tleft" colspan="2">
                                ${myCouponGoodList.cpnBrandNm}
                            </td>
                            <!-- <td></td> -->
                            <c:if test="${myCouponGoodList.dcSectCd eq 'FIXAMT'}">
                                <td><b><fmt:formatNumber value="${myCouponGoodList.dcRtAmt}" pattern="#,###" /></b>원</td>
                            </c:if>
                            <c:if test="${myCouponGoodList.dcSectCd eq 'FIXRT'}">
                                <td><b><fmt:formatNumber value="${myCouponGoodList.dcRtAmt}" pattern="#,###" /></b>%</td>
                            </c:if>
                        </tr>
                    </c:if>
                </c:if>
            </c:forEach>
        </tbody>
    </table>
</div>

<c:if test ="${not empty myCouponGoodList}">
    <div class="page">
        <!-- 링크 없을때
        <span class="first">첫 페이지</span>
        <span class="prev">이전 페이지</span>
        -->
        <c:if test="${currentPage > 1 }">
            <a href="javascript:getCouponGoodList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
        </c:if>
        <c:if test="${preFlag }">
            <a href="javascript:getCouponGoodList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
        </c:if>

        <span>
            <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <strong title="현재 페이지">${i}</strong>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:getCouponGoodList('${i}')">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </span>

        <c:if test="${nextFlag }">
            <a href="javascript:getCouponGoodList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
        </c:if>
        <c:if test="${currentPage < totalPage}">
            <a href="javascript:getCouponGoodList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
        </c:if>
        <!-- 링크 없을때
        <span class="next">다음 페이지</span>
        <span class="last">마지막 페이지</span>
        -->
    </div>
</c:if>
