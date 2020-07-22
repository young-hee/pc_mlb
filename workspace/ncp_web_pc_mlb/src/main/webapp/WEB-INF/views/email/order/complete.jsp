<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<tr>
	<c:if test="${info.dlvMnCd != 'SHOP_PKUP'}">
		<c:if test="empty info.payList">
			<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문이 완료 되었습니다.</td>
		</c:if>
		<c:set var="temp" value="N"/>
		<c:forEach var="payItem" items="${info.payList }">
			<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd != 'DEPST_WAIT'}">
				<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문이 완료 되었습니다.</td>
				<c:set var="temp" value="Y"/>
			</c:if>
			<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd == 'DEPST_WAIT'}">	
				<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문이 접수 완료 되었습니다.</td>
				<c:set var="temp" value="Y"/>
			</c:if>	
		</c:forEach>
		<c:if test="${temp == 'N'}">
			<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문이 완료 되었습니다.</td>
		</c:if>
	</c:if>
	<c:if test="${info.dlvMnCd == 'SHOP_PKUP'}">
		<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">매장 픽업주문이 완료 되었습니다.</td>
	</c:if>
</tr>
<tr>
	<td height="15"></td>
</tr>
<tr>	
	<c:if test="${info.dlvMnCd != 'SHOP_PKUP'}">
		<c:if test="empty info.payList">
			<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
				성공적으로 주문처리 되었습니다. 상품 주문내역과 결제금액을 확인하세요.<br>주문상세 내역 및 배송관련 정보는  <strong style="font-weight:bold; color:#ff3600"> 마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다								
			</td>
		</c:if>
		<c:set var="temp" value="N"/>
		<c:forEach var="payItem" items="${info.payList }">
			<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd != 'DEPST_WAIT'}">
				<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
					성공적으로 주문처리 되었습니다. 상품 주문내역과 결제금액을 확인하세요.<br>주문상세 내역 및 배송관련 정보는  <strong style="font-weight:bold; color:#ff3600"> 마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다								
				</td>
				<c:set var="temp" value="Y"/>
			</c:if>
			<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd == 'DEPST_WAIT'}">	
				<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
					주문접수처리 되었습니다.<br>주문상세 내역 및 배송관련 정보는 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다							
				</td>
				<c:set var="temp" value="Y"/>
			</c:if>	
		</c:forEach>
		<c:if test="${temp == 'N'}">
			<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
					성공적으로 주문처리 되었습니다. 상품 주문내역과 결제금액을 확인하세요.<br>주문상세 내역 및 배송관련 정보는  <strong style="font-weight:bold; color:#ff3600"> 마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다								
				</td>
		</c:if>
	</c:if>
	<c:if test="${info.dlvMnCd == 'SHOP_PKUP'}">
		<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
			<span style="color:#000">[상품준비완료] 안내문자(알림톡)를 수신하신 후 방문하시거나, 해당 매장과 통화하신 후 방문하시기 바랍니다.</span><br>주문상세 내역 및 배송관련 정보는 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다							
		</td>
	</c:if>																			
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
				<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">고객명</td>
				<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${info.cstmrNm}"/></td>
			</tr>
			<tr>
				<td colspan="2" height="20"></td>
			</tr>
			<tr>
				<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">주문일시</td>
				<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${info.startOrdDt }"/></td>
			</tr>
			<tr>
				<td colspan="2" height="20"></td>
			</tr>
			<tr>
				<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px; vertical-align:top">주문번호</td>
				<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">
					<strong style="display:block"><c:out value="${info.ordNo}"/></strong>
					<a href="<ncp:prop key="ncp.url.pc_MLB.root" />/mypage/order/<c:out value="${info.ordNo}"/>/view" target="_blank" style="display:inline-block; padding:9px 20px; margin-top:20px; text-decoration:none; font:normal 12px/14px '돋움', dotum, sans-serif; color:#fff; background:#000">주문상세내역</a>
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
<!-- 결제정보 -->
<%@ include file="/WEB-INF/views/email/order/order.pay.info.jspf"%>
<!-- //결제정보 -->
<c:if test="${info.dlvMnCd != 'SHOP_PKUP'}">
	<c:if test="empty info.payList">
		<tr>
			<td height="57"></td> 
		</tr>	
		<!-- 배송정보 -->
		<%@ include file="/WEB-INF/views/email/order/order.delivery.info.jspf"%>
		<!-- //배송정보 -->
	</c:if>
	<c:forEach var="payItem" items="${info.payList }">
		<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd != 'DEPST_WAIT'}">
			<tr>
				<td height="57"></td> 
			</tr>	
			<!-- 배송정보 -->
			<%@ include file="/WEB-INF/views/email/order/order.delivery.info.jspf"%>
			<!-- //배송정보 -->
		</c:if>
		<c:if test="${payItem.mpayMnYn == 'Y' and payItem.dealTpCd == 'DEPST_WAIT'}">	
			<tr><td height="35"></td></tr>
			<!-- 안내 Text -->
			<tr>
				<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">입금 마감일시까지 입금되지 않은 주문은 자동 취소됩니다.</td>
			</tr>					
			<tr><td height="13"></td></tr>
			<tr>
				<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">무통장입금(가상계좌) 전에 MY PAGE 주문내역에서 신용/체크카드, 네이버페이, 실시간 계좌이체로 변경하실 수 있습니다.</td>
			</tr>
			<tr><td height="13"></td></tr>
			<tr>
				<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">입금 시 예금주 명은 ‘㈜ 에프앤에프’ 입니다.</td>
			</tr>						
			<!-- //안내 Text -->
		</c:if>	
	</c:forEach>
</c:if>
<c:if test="${info.dlvMnCd == 'SHOP_PKUP'}">
	<tr>
		<td height="57"></td> 
	</tr>	
	<!-- 배송정보 -->
	<%@ include file="/WEB-INF/views/email/order/order.delivery.info.jspf"%>
	<!-- //배송정보 -->
</c:if>


