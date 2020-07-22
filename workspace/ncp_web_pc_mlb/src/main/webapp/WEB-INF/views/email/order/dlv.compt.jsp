<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- 상단 text -->
<tr>
    <td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문하신 상품이 배송완료 되었습니다.</td>
</tr>
<tr>
    <td height="15"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">
        고객님의 주문배송이 완료되었습니다.<br>주문배송 상세 내역은 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 주문/배송조회 </strong> 에서 확인하실 수 있습니다  <br>구매하신 상품에 만족하셨다면 구매확정 처리 부탁드립니다.
    </td>
</tr>
<tr>
    <td height="26"></td>
</tr>
<tr>
    <td align="center">
        <a href="<ncp:prop key="ncp.url.pc_MLB.root" />/mypage/order/<c:out value="${info.ordNo}"/>/view" target="_blank" style="display:inline-block; padding:15px 50px; font:bold 12px/14px '돋움', dotum, sans-serif; background:#000; color:#fff; text-decoration:none">구매확정 바로가기</a>
    </td>
</tr>
<tr>
    <td height="26"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666;">배송완료 7일 이후 자동으로 구매확정 처리 됩니다.<br>구매확정 후에 마일리지가 적립되며, 반품/교환은 할 수 없습니다.</td>
</tr>
<tr>
    <td height="36"></td>
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
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">고객명</td>
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;">${info.cstmrNm}</td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
            <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">주문일시</td>
                <td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><fmt:formatDate value="${info.ordDt}" pattern="yyyy년 MM월 dd일 HH:mm" /></td>
            </tr>
            <tr>
                <td colspan="2" height="20"></td>
            </tr>
                                                <tr>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px; vertical-align:top">주문번호</td>
                <td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;"><strong style="display:block">${info.ordNo}</strong></td>
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

<!-- 주문상품 -->
<%@ include file="/WEB-INF/views/email/order/order.god.info.jspf"%>
<!-- //주문상품 -->

<tr>
    <td height="57"></td>
</tr>

<!--결제정보 -->
<%@ include file="/WEB-INF/views/email/order/order.pay.info.jspf"%>
<!-- //결제정보 -->

<tr><td height="35"></td></tr>
<!-- 안내 Text -->
<tr>
    <td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL}static/images/mail/icon_txt.png') no-repeat 0 6px">구매확정 후에 마일리지가 적립되며, 반품/교환은 불가능 합니다.</td>
</tr>
<tr><td height="13"></td></tr>
<tr>
    <td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL}static/images/mail/icon_txt.png') no-repeat 0 6px">구매 후 텍스트 리뷰 또는 포토 리뷰 작성에 따라 포인트가 적립 됩니다.(텍스트 리뷰 100P, 포토리뷰 500P)</td>
</tr>
<!-- //안내 Text -->
