<%@ page import="com.plgrim.ncp.framework.commons.ContextService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>
<ncp:prop key="ncp.url.pc_DX.root" var="URL"/>

<!-- 상단 text -->
<tr>
    <td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">1:1 문의 답변</td>
</tr>
<tr>
    <td height="25"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:0 20px; color:#666; word-break:keep-all">
        안녕하세요. <strong style="font-weight:bold; color:#000">MLB Korea</strong> 입니다.<br><strong style="font-weight:bold; color:#000">${info.inqMbrNm}</strong> 고객님께서 고객센터를 통해 문의하신 글에 대한 답변입니다.<br>문의내역은 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 활동정보 > 1:1문의</strong> 에서 확인 할 수 있습니다.
    </td>
</tr>
<tr>
    <td height="36"></td>
</tr>
<tr>
    <td align="center">
        <a href="<ncp:prop key="ncp.url.pc_MLB.root" />/mypage/inquiry/list" target="_blank" style="display:inline-block; padding:15px 50px; font:bold 12px/14px '돋움', dotum, sans-serif; background:#000; color:#fff; text-decoration:none">1:1문의 바로가기</a>
    </td>
</tr>
<tr>
    <td height="58"></td>
</tr>
<!--  문의 답변 내용 -->
<tr>
    <td>
        <table cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td style="font:bold 14px/20px '돋움', dotum, sans-serif; padding:28px 18px 26px; background:#f8f8f8; color:#000; vertical-align:top">
                    Q. ${info.inqSj}
                    <p style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:16px; margin:0"><strong style="font-weight:bold; padding-right:10px">작성일</strong>${info.inqDt}</p>
                </td>
            </tr>
            <tr>
                <td height="26"></td>
            </tr>
            <tr>
                <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:0 18px 16px; color:#000">
                    <ncp:code code="${info.inqTpCd}" var="tpcd"/>
                    <strong style="font-weight:bold; padding-right:10px">상담분류</strong> <c:out value ="${tpcd.cdNm}"/>
                </td>
            </tr>
            <tr>
                <td style="font:normal 12px/18px '돋움', dotum, sans-serif;color:#666; padding:0 18px 26px">${fn:replace(info.inqCont, newLineChar, "<br>")}</td>
            </tr>
            <tr>
                <td height="1" style="background:#eee"></td>
            </tr>
            <tr>
                <td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:28px 18px 26px; color:#666;  vertical-align:top">
                    <strong style="font-weight:bold;font-size:14px">A.</strong> ${fn:replace(info.inqAnscont, newLineChar, "<br>")}
                    <p style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:16px; margin:0; color:#666"><strong style="font-weight:bold; padding-right:10px">답변일</strong>${info.inqAnsDt}</p>
                </td>
            </tr>
            <tr>
                <td height="1" style="background:#eee"></td>
            </tr>
        </table>
    </td>
</tr>
<!-- // 문의 답변 내용 -->
<tr>
    <td height="57"></td>
</tr>
<tr>
    <td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666;">언제나 고객만족을 위해 노력하는 MLB가 되겠습니다.</td>
</tr>