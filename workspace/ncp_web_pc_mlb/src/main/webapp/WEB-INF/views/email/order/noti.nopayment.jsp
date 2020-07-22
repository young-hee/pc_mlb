<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<tr>
	<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
		구매하신 상품의 무통장 입금 안내입니다.
	</td>
</tr>
<tr>
	<td height="16"></td>
</tr>
<tr>
	<td>
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td width="8%"></td>
					<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#999;">
						구매하신 상품의 무통장 입금 안내 정보는<br>
						<strong style="color:#ff3600; font-weight:normal;"> 마이페이지 &gt; 주문정보 &gt; 주문/배송조회</strong> 에서 확인 가능합니다.
					</td>
				<td width="8%"></td>
			</tr>
		</table>
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
			<tr>
				<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">고객명</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value="${info.cstmrNm}"/></td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">주문번호</th>
				<td align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;"><c:out value="${info.ordNo}"/></td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">주문일자</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value="${info.startOrdDt }"/></td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
		</table>
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
		<a href="<ncp:prop key="ncp.url.pc_MLB.root" />/mypage/order/<c:out value="${info.ordNo}"/>/view" target="_blank" style="display:inline-block; padding:0 50px; background:#000; font:bold 12px/45px '돋움', dotum, sans-serif; color:#fff; text-decoration:none;">주문내역 확인하기</a>
	</td>
</tr>
<tr>
	<td height="62"></td>
</tr>

<%@ include file="/WEB-INF/views/email/order/order.god.info.jspf"%>


<%@ include file="/WEB-INF/views/email/order/order.pay.info.jspf"%>


<tr>
	<td height="1" style="background:#eee;"></td>
</tr>
<tr>
	<td height="62"></td>
</tr>
<tr>
	<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">입금정보</td>
</tr>
<tr>
	<td height="14"></td>
</tr>
<tr>
	<td height="1" style="background:#666;"></td>
</tr>
<tr>
	<td height="22"></td>
</tr>
<tr>
	<td>
		<table cellspacing="0" cellpadding="0" width="100%">
			<c:forEach var="payItem" items="${info.payList }">
				<c:if test="${payItem.mpayMnYn == 'Y' }">
					<tr>
						<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">입금은행정보</th>
						<td align="right" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value="${payItem.bnkNm}"/> 가상계좌 <c:out value="${payItem.bnkAcctNo}"/></td>
					</tr>
					<tr>
						<td colspan="2" height="16"></td>
					</tr>
					<tr>
						<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">입금기한</th>
						<td align="right" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#ff3600;">주문일 기준 1일이내 입금하셔야 주문완료 처리됩니다.</td><!-- 2018-07-28 -->
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</td>
</tr>
<tr>
	<td height="23"></td>
</tr>
<tr>
	<td height="1" style="background:#eee;"></td>
</tr>



