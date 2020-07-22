<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


<!-- 사용 가능한 쿠폰 -->
<c:if test ="${mypageMainYn ne 'Y'}">
<div class="board-list">
</c:if>
    <table>
        <caption>사용 가능한 쿠폰</caption>
        <colgroup>
            <col style="width:100px;">
            <col style="width:100px;">
            <col>
            <col style="width:100px;">
            <col style="width:190px;">
            <col style="width:147px;">
        </colgroup>
        <thead>
            <tr>
                <th scope="col">쿠폰종류</th>
                <th scope="col">적용조건</th>
                <th scope="col">쿠폰명</th>
                <th scope="col">할인</th>
                <th scope="col">사용기간</th>
                <th scope="col">쿠폰적용상품</th>
            </tr>
        </thead>
        <tbody>
            <c:if test ="${empty myCouponList}">
                <tr>
                    <td colspan="6" class="no-result">조회된 쿠폰 내역이 없습니다.</td>
                </tr>
            </c:if>
            <c:forEach var="listCpn" varStatus="status" items="${myCouponList}">
                <tr>
                    <td>
                        <c:if test="${listCpn.cpnKndCd ne 'ONOFLNE_CPN' }">온라인</c:if>
                        <c:if test="${listCpn.cpnKndCd eq 'ONOFLNE_CPN' }">온오프라인</c:if>
                    </td>
                    <td><ncp:code code='${listCpn.cpnTpCd}'/></td>
                    <td class="tleft">
                        <p>${listCpn.prmNm}</p>
                        <c:if test="${fn:trim(listCpn.cpnUseMinPchAmt) ne null and fn:trim(listCpn.cpnUseMinPchAmt)  ne '' and fn:trim(listCpn.cpnUseMinPchAmt)  ne '0' }">
                            <span class="text-color01">최소구매금액 : <fmt:formatNumber value="${listCpn.cpnUseMinPchAmt }" pattern="#,###" />원</span>
                        </c:if>
                        <c:if test="${fn:trim(listCpn.cpnMaxDcPsbAmt) ne null and fn:trim(listCpn.cpnMaxDcPsbAmt)  ne '' and fn:trim(listCpn.cpnMaxDcPsbAmt)  ne '0' }">
                            <span class="text-color01">최대할인금액 : <fmt:formatNumber value="${listCpn.cpnMaxDcPsbAmt }" pattern="#,###" />원</span>
                        </c:if>
                    </td>
                    <td>
                        <span class="text-color01">
                            <fmt:formatNumber value="${listCpn.dcRtAmt}" pattern="#,###" />
                            <c:if test="${listCpn.dcSectCd eq 'FIXRT'}">%</c:if>
                            <c:if test="${listCpn.dcSectCd eq 'FIXAMT'}">원</c:if>
                            <c:if test="${listCpn.dcSectCd eq 'DLV_CST_FREE'}">무료배송</c:if>
                        </span>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${listCpn.usePsbPeriodYn eq 'Y'}">
                                ~ ${listCpn.validEndDate}(${listCpn.validDate}일 남음)
                            </c:when>
                            <c:otherwise>
                                ${listCpn.validBegDate} 부터 사용가능
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td>
                        <c:if test="${listCpn.cpnTpCd eq 'GOD_CPN'}">
                            <div class="tbST-R-BtnBox">
                                <a href="javascript:;" class="btn sm gray" onclick="getLayerPopupMyCouponGood('${listCpn.mbrCpnNo}' , '${listCpn.prmNo}' , '${listCpn.aplGodSecCd}');">적용상품조회</a>
                            </div>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<c:if test ="${mypageMainYn ne 'Y'}">
</div>
</c:if>


<c:if test ="${mypageMainYn ne 'Y'}">
    <c:if test ="${!empty myCouponList}">
        <div class="page">
        	<!-- 링크 없을때
        	<span class="first">첫 페이지</span>
        	<span class="prev">이전 페이지</span>
        	-->
            <c:if test="${currentPage > 1 }">
        	   <a href="javascript:goCouponList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
            </c:if>
            <c:if test="${preFlag }">
        	   <a href="javascript:goCouponList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
            </c:if>
        	<span>
                <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <strong title="현재 페이지">${i}</strong>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:goCouponList('${i}')">${i}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
        	</span>

            <c:if test="${nextFlag }">
        	   <a href="javascript:goCouponList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
            </c:if>
            <c:if test="${currentPage < totalPage}">
        	   <a href="javascript:goCouponList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
            </c:if>
        	<!-- 링크 없을때
        	<span class="next">다음 페이지</span>
        	<span class="last">마지막 페이지</span>
        	-->
        </div>
    </c:if>
    <c:if test ="${empty myCouponList}">
        <!-- empty coupons -->
    </c:if>
</c:if>

<!-- //사용 가능한 쿠폰 -->
<script type="text/javascript" >

	$(document).ready(function(){
		setCouponCnt('${totalRow}');
	});

	// 사용가능쿠폰 건수
	function setCouponCnt(totalRow){
		$("#couponCnt").empty();
		$("#couponCnt").append(totalRow);
	}

	// 적용가능상품 조회 Pop
	function getLayerPopupMyCouponGood(mbrCpnNo, prmNo, aplGodSecCd) {
		var strParams = null;
		strParams = {'mbrCpnNo' : mbrCpnNo , 'prmNo' : prmNo , 'aplGodSecCd' :  aplGodSecCd};
//      	$("#layerPopupCouponProductDiv").load("<c:url value='/mypage/benefit/include/myCouponGoodList'/>", strParams);
//      	layerPopup.popupOpenNow("#layerPopupCouponProduct");


		$.ajax({
			type : "get",
			url : "/mypage/benefit/include/myCouponGoodList",
			dataType : "html",
			data : strParams,
			beforeSend: function (request)
			{
				var csrfToken = $("meta[name='_csrf']").attr("content");
				var csrfName = $("meta[name='_csrf_header']").attr("content");
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(args) {
				$("#layerPopupCouponProductDiv").empty();
				$("#layerPopupCouponProductDiv").append(args);

				$("[name=searchFor]").eq(0).trigger("click");

				$("[name=listCpnFor]").each(function(index) {
					if(mbrCpnNo == $(this).attr("mbrCpnNo")) {
						setBtnDataForCpn(this);
						return false;
					}
				});

				layerPopup.popupOpenNow("#layerPopupCouponProduct");
			},
			error : function(e) {
				alert(e.responseText);
			}
		});
    }
</script>
