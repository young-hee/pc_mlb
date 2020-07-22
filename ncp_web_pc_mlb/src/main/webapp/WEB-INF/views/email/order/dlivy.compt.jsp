<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<tr>
	<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
		고객님의 주문상품이 발송되었습니다.
	</td>
</tr>
<tr>
	<td height="16"></td>
</tr>
<tr>
	<td>
		<table cellspacing="0" cellpadding="0" width="100%">
			<tbody><tr>
				<td width="8%"></td>
					<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#999;">
						마이주문하신 상품이 출고되었습니다.<br>
						배송정보는 <strong style="color:#ff3600; font-weight:normal;">마이페이지 &gt; 주문정보 &gt; 주문/배송조회</strong> 에서 확인 가능합니다.
					</td>
				<td width="8%"></td>
			</tr>
		</tbody></table>
	</td>
</tr>
<tr>
	<td height="38"></td>
</tr>
<tr>
	<td height="1" style="background:#eee;"></td>
</tr>
<tr>
	<td height="25"></td>
</tr>
<tr>
	<td>
		<table cellspacing="0" cellpadding="0" width="100%">
			<tbody>
			<c:forEach var="listDlvsp" varStatus="status" items="${info.lgsDlvspList}">
				<c:forEach var="lgsDlv" varStatus="lgsDlvStatus" items="${listDlvsp.lgsDlvCoList}">
					<tr>
						<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">출고완료일</th>
						<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${lgsDlv.regtrId}</td>
					</tr>
					<tr>
						<td colspan="2" height="16"></td>
					</tr>
					<tr>
						<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">수령예상일</th>
						<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${lgsDlv.udterId} (배송지 상황에 따라 1~2일 지연될 수 있습니다.)</td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td colspan="2" height="16"></td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">배송방법</th> -->
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${empty lgsDlv.dmstcWaybilNo}"> --%>
<!-- 								<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">매장픽업</td> -->
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<!-- 								<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">택배</td> -->
<%-- 							</c:otherwise>	 --%>
<%-- 						</c:choose> --%>
						
<!-- 					</tr> -->
					<tr>
						<td colspan="2" height="16"></td>
					</tr>
					<tr>
						<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">배송사</th>
						<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${lgsDlv.dlvComCd}</td>
					</tr>
					<tr>
						<td colspan="2" height="16"></td>
					</tr>
					<tr>
						<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">운송장번호</th>
						<td align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">${lgsDlv.dmstcWaybilNo}</td>
					</tr>
					<tr>
						<td colspan="2" height="16"></td>
					</tr>
			</c:forEach>
		</c:forEach>
		</tbody></table>
	</td>
</tr>
<tr>
	<td height="18"></td>
</tr>
<tr>
	<td height="1" style="background:#eee;"></td>
</tr>
<tr>
	<td height="30"></td>
</tr>
<tr>
	<td align="center">
		<a href="<ncp:prop key="ncp.url.pc_MLB.root" />/mypage/order/<c:out value="${info.ordNo}"/>/view" style="display:inline-block; padding:0 50px; background:#000; font:bold 12px/45px '돋움', dotum, sans-serif; color:#fff; text-decoration:none;">배송조회 바로가기</a>
	</td>
</tr>
<tr>
	<td height="57"></td>
</tr>
<tr>
	<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">주문상품</td>
</tr>

<%@ include file="/WEB-INF/views/email/order/order.god.info.jspf"%>
<%@ include file="/WEB-INF/views/email/order/order.delivery.info.jspf"%>

