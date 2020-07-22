<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- 상단 text -->
<tr>
    <td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">교환신청이 접수되었습니다.</td><!-- 2019.02.08 문구 수정 -->
</tr>
<tr>
    <td height="15"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px">
        교환 상세 내역은 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 취소/반품/교환</strong> 에서 확인 가능합니다.
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
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:124px;">교환신청일시</td>
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
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.exchgDlvCst}" pattern="#,###" />원</td>
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
<!-- //주문정보 -->
<tr>
    <td height="57"></td>
</tr>

<!-- 교환상품 -->
<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmInfo.lgsDlvspFoExtend}">
    <tr>
        <td>
            <table cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">교환상품</td>
                </tr>
                <tr>
                    <td height="18"></td>
                </tr>
                <tr><td style="background:#000" height="1"></td></tr>

                <c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
                    <c:if test="${clmWrhsGodList.godTpCd ne 'PCHS_GFT' && clmWrhsGodList.godTpCd ne 'CNVRS_GFT'}">
                        <tr>
                            <!-- 기본 리스트 -->
                            <td style="padding:30px 0 26px">
                                <table cellspacing="0" cellpadding="0" width="100%">
                                    <tr>
                                        <td width="100" style="vertical-align:top;">
                                            <span style="width:100px; height:100px; background:#f0f0f0; display:block; overflow:hidden;">
                                                <span style="display:table; height:100px">
                                                    <span style="display:table-cell; vertical-align:middle"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/75x100'/>" alt="" width="100" border="0"></span><!-- 상품이미지 -->
                                                </span>
                                            </span>
                                        </td>
                                        <td style="padding:0 20px; vertical-align:top; font:normal 12px/18px '돋움', dotum, sans-serif;">
                                            <c:out value='${clmWrhsGodList.godNm}'/>
                                            <p style="margin:0; padding:3px 0 10px 0"></p>
                                            <c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/>
                                        </td>
                                        <td width="50px" align="center" style="font:normal 12px/14px '돋움', dotum, sans-serif;""><c:out value='${clmWrhsGodList.clmQty}' />개</td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px"><ncp:code code='${clmInfo.clmResnCd}' /> / <c:out value='${clmInfo.clmResnCont}'/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr><td style="background:#ddd" height="1"></td></tr>
                    </c:if>
                </c:forEach>
                
            </table>
        </td>
    </tr>
    <!-- //교환상품 -->
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

<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
    <c:if test="${listDlvsp.dlvPcupspSectCd eq 'CLM_DLVSP' and listDlvsp.dlvSectCd eq 'GNRL_DLV'}">
        <tr>
            <td height="57"></td>
        </tr>
        <!-- 배송정보 -->
        <tr>
            <td>
                <table cellspacing="0" cellpadding="0" width="100%">
                    <tr>
                        <td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">배송정보</td>
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
                    <c:if test="${not empty listDlvsp.dlvMemo}">
                        <tr>
                            <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px"><c:out value="${listDlvsp.dlvMemo}" /></td>
                        </tr>
                    </c:if>
                    <tr>
                        <td style="border-bottom:1px solid #ddd" height="28px"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <!-- //배송정보 -->
    </c:if>
</c:forEach>
