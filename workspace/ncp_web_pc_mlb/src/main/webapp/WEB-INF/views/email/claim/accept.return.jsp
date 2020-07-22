<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- 상단 text -->
<tr>
    <td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">반품신청이 접수되었습니다.</td><!-- 2019.02.08 문구 수정 -->
</tr>
<tr>
    <td height="15"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px">
        반품 상세 내역은 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 취소/반품/교환</strong> 에서 확인 가능합니다.
    </td>
</tr>
<tr>
    <td height="40"></td>
</tr>
<!-- 주문정보 -->
<tr>
    <td>
        <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td colspan="2" height="1" style="background:#ddd"></td>
            </tr>
            <tr>
                <td colspan="2" height="28"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">고객명</td>
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;">${orderInfo.cstmrNm}</td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">반품신청일시</td>
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><fmt:formatDate pattern="yyyy년 MM월 dd일 HH:mm" value="${clmInfo.clmDt}"/></td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">주문번호</td>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">${orderInfo.ordNo}</td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">클레임번호</td>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">${clmInfo.clmNo}</td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">배송비결제</td>
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><fmt:formatNumber pattern="#,##0" value="${clmInfo.rtgodDlvCst}" />원</td>
            </tr>
            <tr>
                <td colspan="2" height="28"></td>
            </tr>
            <tr>
                <td colspan="2" height="1" style="background:#ddd"></td>
            </tr>
        </table>
    </td>
</tr>
<!--// 주문정보 -->
<tr>
    <td height="57"></td>
</tr>


<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmInfo.lgsDlvspFoExtend}">
    <!-- 반품상품 -->
    <tr>
        <td>
            <table cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">반품상품</td>
                </tr>
                <tr>
                    <td height="18"></td>
                </tr>
                <tr><td style="background:#000" height="1"></td></tr>
                <c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
                    <tr>
                        <!-- 기본 리스트 -->
                        <td style="padding:30px 0 26px">
                            <table cellspacing="0" cellpadding="0" width="100%">
                                <tr>
                                    <td width="100" style="vertical-align:top;">
                                        <span style="width:100px; height:100px; background:#f0f0f0; display:block; overflow:hidden;">
                                            <span style="display:table; height:100px">
                                                <span style="display:table-cell; vertical-align:middle"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/100x100'/>" alt="" width="100" border="0"></span><!-- 상품이미지 -->
                                            </span>
                                        </span>
                                    </td>
                                    <td style="padding:0 20px; vertical-align:top; font:normal 12px/18px '돋움', dotum, sans-serif;">
                                        <c:out value='${clmWrhsGodList.godNm}'/>
                                        <p style="margin:0; padding:3px 0 10px 0"></p>
                                        ${clmWrhsGodList.colorCd} / ${clmWrhsGodList.itmNm}
                                    </td>
                                    <td width="50px" align="center" style="font:normal 12px/14px '돋움', dotum, sans-serif;"">${clmWrhsGodList.clmQty}개</td>
                                </tr>
                                <tr>
                                    <td colspan="3" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px"><ncp:code code='${clmInfo.clmResnCd}' /> / <c:out value="${clmInfo.clmResnCont}" /></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr><td style="background:#ddd" height="1"></td></tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <!-- //반품상품 -->
    <tr>
        <td height="57"></td>
    </tr>
    <!-- 수거정보 -->
    <tr>
        <td>
            <table cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">수거정보</td>
                </tr>
                <tr>
                    <td height="18"></td>
                </tr>
                <tr>
                    <td style="border-top:1px solid #000" height="28px"></td>
                </tr>
                <tr>
                    <td style="font:normal 12px/18px '돋움', dotum, sans-serif;">${listDlvsp.addrseNm}</td>
                </tr>
                <tr>
                    <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px"><c:out value='${listDlvsp.addrseBaseAddr}'/>&nbsp;<c:out value='${listDlvsp.addrseDetailAddr}'/></td>
                </tr>
                <tr>
                    <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px"><a href="tel:${listDlvsp.addrseMobilAreaNo}-${listDlvsp.addrseMobilTlofNo}-${listDlvsp.addrseMobilTlofWthnNo}" style="text-decoration:none; color:#000">${listDlvsp.addrseMobilAreaNo}-${listDlvsp.addrseMobilTlofNo}-${listDlvsp.addrseMobilTlofWthnNo}</a></td>
                </tr>
                <tr>
                    <td style="border-bottom:1px solid #ddd" height="28px"></td>
                </tr>
            </table>
        </td>
    </tr>
    <!-- //수거정보 -->
</c:forEach>


<tr>
    <td height="57"></td>
</tr>
<!--환불정보 -->
<tr>
    <td>
        <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td colspan="2" align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">환불정보</td>
            </tr>
            <tr>
                <td colspan="2" height="18"></td>
            </tr>
            <tr>
                <td colspan="2" style="border-top:1px solid #000" height="28"></td>
            </tr>
            <tr>
                <th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">상품가격</th>
                <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.saleSumAmt}" pattern="#,###" />원</td>
            </tr>
            <tr>
                <td colspan="2" height="18"></td>
            </tr>
            <tr>
                <th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">할인적용</th>
                <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#ff3600">-<fmt:formatNumber value="${clmInfo.bundleDcSumAmt + clmInfo.crsDcSumAmt + clmInfo.godCpnDcSumAmt + clmInfo.bskCpnDcSumAmt}" pattern="#,###" />원</td>
            </tr>
            <tr>
                <td colspan="2" height="18"></td>
            </tr>
            <tr>
                <th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">배송비</th>
                <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.cnclDlvCst - clmInfo.rtgodDlvCst}" pattern="#,###" />원</td>
            </tr>
            <tr>
                <td colspan="2" height="28"></td>
            </tr>
            <tr>
                <td colspan="2" height="1" style="background:#ddd"></td>
            </tr>
            <tr>
                <td colspan="2" height="28"></td>
            </tr>
            <tr>
                <td  colspan="2">
                    <table cellspacing="0" cellpadding="0" width="100%">
                        <tr>
                            <th rowspan="7" width="100" align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif; vertical-align:top">환불금액</th>
                            <th align="left"  width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif;">마일리지 환급</th>
                            <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.unityPntUseSumAmt}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="18"></td>
                        </tr>
                        <tr>
                            <th align="left" width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif;">포인트 환급</th>
                            <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.webpntUseSumAmt}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td colspan="3" height="18"></td>
                        </tr>
                        <tr>
                            <th align="left" width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif; vertical-align:top"><ncp:code code='${orderInfo.payList[0].payMnCd}'/></th>
                            <td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif; vertical-align:top;"><ncp:code code='${clmInfo.rfdBnkCd}'/>
                            	<strong style="font-size:14px; color:#ff3600; padding-left:10px">
		                            <c:if test="${clmInfo.payExchgRtCrncySumAmt > 0}"><fmt:formatNumber value="${clmInfo.payExchgRtCrncySumAmt}" pattern="#,###" /></c:if>
		                            <c:if test="${clmInfo.payExchgRtCrncySumAmt <= 0}">0</c:if>원
		                    	</strong>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="28"></td>
            </tr>
            <tr>
                <td colspan="2" height="1" style="background:#ddd"></td>
            </tr>

        </table>
    </td>
</tr>
<!-- //환불정보 -->