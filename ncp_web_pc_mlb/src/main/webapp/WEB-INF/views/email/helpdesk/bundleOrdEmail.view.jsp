<%@ page import="com.plgrim.ncp.framework.commons.ContextService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<!-- 상단 text -->
<tr>
    <td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">단체구매문의 답변</td>
</tr>
<tr>
    <td height="25"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:0 20px; color:#666; word-break:keep-all">
        안녕하세요. <strong style="font-weight:bold; color:#000">MLB Korea</strong> 입니다.<br><strong style="font-weight:bold; color:#000">${info.orgztInqerNm}</strong> 고객님께서 문의하신 단체구매문의에 대한 답변입니다.
    </td>
</tr>
<tr>
    <td height="58"></td>
</tr>
<!-- 문의 답변 내용 -->
<tr>
    <td>
        <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td style="font:bold 14px/20px '돋움', dotum, sans-serif; padding:28px 18px 26px; background:#f8f8f8; color:#000; vertical-align:top">
                    Q. <c:if test="${not empty info.orgztNm}">(${info.orgztNm}) </c:if>단체구매문의
                    <p style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:16px; margin:0"><strong style="font-weight:bold; padding-right:10px">작성일</strong>${info.orgztRegDt}</p>
                </td>
            </tr>
            <tr>
                <td height="26"></td>
            </tr>
            <tr>
                <td style="font:normal 12px/18px '돋움', dotum, sans-serif;color:#666; padding:0 18px 26px">${fn:replace(info.orgztInqCont, newLineChar, "<br>")}</td>
            </tr>
            <tr>
                <td height="1" style="background:#eee"></td>
            </tr>
            <tr>
                <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:28px 18px 26px; color:#666;  vertical-align:top">
                    <strong style="font-weight:bold;font-size:14px">A.</strong> ${fn:replace(info.orgztAnsCont, newLineChar, "<br>")}
                    <p style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:16px; margin:0; color:#666"><strong style="font-weight:bold; padding-right:10px">답변일</strong>${info.orgztAnsDt}</p>
                </td>
            </tr>
            <tr>
                <td height="1" style="background:#eee"></td>
            </tr>
        </table>
    </td>
</tr>
<!-- //문의 답변 내용 -->
<tr>
    <td height="57"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666;">언제나 고객만족을 위해 노력하는 MLB가 되겠습니다.</td>
</tr>