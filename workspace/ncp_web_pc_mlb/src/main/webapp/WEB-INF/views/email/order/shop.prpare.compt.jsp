<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<tr>
	<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
		고객님의 주문상품이 ${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].shopNm}점에 준비되었습니다.
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
			<tbody><tr>
				<td colspan="2" style="font:normal 12px/14px '돋움', dotum, sans-serif;">Discovery Expedition ${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].shopNm}점의 매장위치입니다.</td>
			</tr>
			<tr>
				<td colspan="2" height="25"></td>
			</tr>
			<tr>
				<td colspan="2"><!-- 710 × 318 퍼블데로함 -->
					<img src="<ncp:img path='${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].shopImgUrl}/dims/resize/710x318'/>" width="100%" alt="">
				</td>
			</tr>
			<tr>
				<td colspan="2" height="23"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">매장주소</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].shopAddr}</td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">전화번호</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].shopTel}</td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">영업시간</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].wkdyHhmm}</td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
			<tr>
				<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">픽업시간</th>
				<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"> <strong style="color:#ff3600; font-weight:bold;">${info.lgsDlvspList[0].lgsDlvList[0].ordGodList[0].pickDate}</strong>까지 매장 미방문 시, 주문이 자동 취소 됩니다. <br>매장 수령일과 매장 휴무일이 겹칠 수 있으니 매장에 미리 연락 후 매장 수령일을 확인하세요.</td>
			</tr>
			<tr>
				<td colspan="2" height="16"></td>
			</tr>
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
	<td height="57"></td>
</tr>
<tr>
	<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">주문상품</td>
</tr>

<%@ include file="/WEB-INF/views/email/order/order.god.info.jspf"%>