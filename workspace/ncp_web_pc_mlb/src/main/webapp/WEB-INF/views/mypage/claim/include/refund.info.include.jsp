<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
				
<%-- 부분취소S --%>
<c:if test="${clmTpCd eq 'PART_CNCL' }">

	<c:set var="refundMile" value="0" />
	<c:set var="refundPnt" value="0" />
	
	<c:forEach items="${clmRefundDTO.refundResultDTO }" var="list" varStatus="status">
		<c:if test="${list.paymncd eq 'MBSH_PNT_PAY'}">
			<c:set var="refundMile" value="${refundMile + list.prc}" />
		</c:if>
		<c:if test="${list.paymncd eq 'WEB_PNT_PAY'}">
			<c:set var="refundPnt" value="${refundPnt + list.prc}" />
		</c:if>
	</c:forEach>
					
	<h3><spring:message code="mypage.order.detail.lbl.claim.cancel.info" /></h3>
	<table class="tbTotalList">
		<caption><spring:message code="mypage.order.detail.lbl.claim.cancel.info" /></caption>
       	<colgroup>
        	<col style="width:50%">
            <col style="width:">
       	</colgroup>
		<tbody>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.goods" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.salePrcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.order.discount" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.bundleDcSumAmt + clmRefundDTO.crsDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.coupon.discount" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.godCpnDcSumAmt + clmRefundDTO.bskCpnDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.mileage.refund" /></th>
				<td><c:if test="${refundMile > 0 }">+</c:if><fmt:formatNumber value="${refundMile}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr class="selmgB">
				<th><spring:message code="mypage.order.detail.lbl.payment.point.refund" /></th>
				<td><c:if test="${refundPnt > 0 }">+</c:if><fmt:formatNumber value="${refundPnt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr class="selLineBox">
				<th><spring:message code="mypage.order.detail.lbl.payment.delivery" text="배송비"/></th>
				<td><fmt:formatNumber value="${clmRefundDTO.refundDlvFee}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
		</tbody>
		<tfoot>
        	<tr>
          		<th><spring:message code="mypage.order.detail.lbl.payment.refund.amount" text="환불 금액"/></th>
          		<td>
          			<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt < 0}">
						<strong>0</strong>
					</c:if>
					<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt >= 0}">
						<strong><fmt:formatNumber value="${clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt }" pattern="#,###" /></strong>
					</c:if>
					<spring:message code="mypage.order.lbl.currency" text="원"/>
          		</td>
          	</tr>
          	<c:if test="${clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt < 0}">
				<tr>
					<th></th>
					<td>
						(<spring:message code="mypage.order.detail.lbl.payment.addpay.amount" text="추가 결제금액"/> : <fmt:formatNumber value="${(clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt) * -1 }" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/>)
					</td>
				</tr>
			</c:if>
        </tfoot>
	</table>
	
	<%-- 주문 사은품 UI 처리 --%>
	<c:forEach var="gift" items="${clmRefundDTO.ordGftList}">
		<script>
			$('#P_ORD_GFT_${gift.ordGodTurn}').show();
		</script>
	</c:forEach>
	
</c:if>
<%-- 부분취소E --%>

<!-- 일반교환S -->
<c:if test="${clmTpCd eq 'GNRL_EXCHG' }">
	<!-- 수거지별 -->
	<c:forEach var="lgsDlvspList" items="${lgsDlvspList}" varStatus="mainSts">

		<!-- 수거지별 물류배송 -->
		<c:forEach var="lgsDlvList" items="${lgsDlvspList.lgsDlvList}" varStatus="subSts">
			<c:choose>
				<c:when test="${lgsDlvspList.dlvPcupspSectCd eq 'CLM_PCUPSP'}">

					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvAdbukTurn" value="${lgsDlvspList.dlvAdbukTurn}" style="width:100%;">
					<c:choose>
						<c:when test="${subSts.index == 0}">
							<!-- 배송 수거지 구분 코드 -->
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvPcupspSectCd" value="${lgsDlvspList.dlvPcupspSectCd}" style="width:100%;">

							<!-- 수거지 배송수단코드 -->
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvMnCd" value="${lgsDlvspList.dlvMnCd}" style="width:100%;">

						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>

					<!-- 배송비 정책 일련번호 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dmstcDlvCstPlcSn" value="${lgsDlvList.dmstcDlvCstPlcSn}" style="width:100%;">
					<!-- 주문번호 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].ordNo" value="${lgsDlvList.ordNo}" style="width:100%;">
					<!-- 통화코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].crncyCd" value="${lgsDlvList.crncyCd}" style="width:100%;">
					<!-- 기준 통화 금액 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].stdrCrncyAmt" value="${lgsDlvList.stdrCrncyAmt}" style="width:100%;">
					<!-- 결제 환율 통화 금액 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].payExchgRtCrncyAmt" value="${lgsDlvList.payExchgRtCrncyAmt}" style="width:100%;">
					<!-- 배송비 부담 주체 코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstBndMbdCd" value="${lgsDlvList.dlvCstBndMbdCd}" style="width:100%;">
					<%-- 정책 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].plcDlvCst" id="plcDlvCst${lgsDlvspList.dlvPcupspTurn}"  value="${lgsDlvList.plcDlvCst}" class="number plcDlvCstClass" style="width:100%;">
					<%-- 취소 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].cnclDlvCst" id="cnclDlvCst${lgsDlvspList.dlvPcupspTurn}"  value="${lgsDlvList.cnclDlvCst}" class="cnclDlvCstClass">
					<!-- 반품배송비 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].rtgodDlvCst" value="${lgsDlvList.rtgodDlvCst}" class="number rtgodDlvCstClass" style="width:100%;">
					<%-- 교환 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].exchgDlvCst" value="${lgsDlvList.exchgDlvCst}" class="number exchgDlvCstClass" style="width:100%;">
					<%-- 실제 배송비--%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].realityDlvCst" id="realityDlvCst${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.realityDlvCst}" class="realityDlvCstClass">
					<%--배송 업체 코드(회수 배송업체는 한진택배만 사용) --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvComCd" value="${dlvCom.dlvComCd}" style="width:100%;">
					
					<!-- 반품쿠폰 번호 -->
                   	<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstCpnNo" value="${lgsDlvList.dlvCstCpnNo}">
					<!-- 쿠폰 적용 할인 금액 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstCpnDcAmt" id="dlvCstCpnDcAmt${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.dlvCstCpnDcAmt}" class="number">

				</c:when>
				<c:otherwise>

					<!-- 배송지순번 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvAdbukTurn" value="${lgsDlvspList.dlvAdbukTurn}" style="width:100%;">

					<c:choose>
						<c:when test="${subSts.index == 0}">
							<!-- 배송 수거지 구분 코드 -->
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvPcupspSectCd" value="${lgsDlvspList.dlvPcupspSectCd}" style="width:100%;">

							<!-- 배송지 배송수단코드 -->
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvMnCd" value="${lgsDlvspList.dlvMnCd}" style="width:100%;">
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<!-- 배송비 정책 일련번호 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dmstcDlvCstPlcSn" value="${lgsDlvList.dmstcDlvCstPlcSn}" style="width:100%;">
					<!-- 주문번호 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].ordNo" value="${lgsDlvList.ordNo}" style="width:100%;">
					<!-- 통화코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].crncyCd" value="${lgsDlvList.crncyCd}" style="width:100%;">
					<!-- 기준 통화 금액 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].stdrCrncyAmt" value="${lgsDlvList.stdrCrncyAmt}" style="width:100%;">
					<!-- 결제 환율 통화 금액 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].payExchgRtCrncyAmt" value="${lgsDlvList.payExchgRtCrncyAmt}" style="width:100%;">
					<!-- 배송비 부담 주체 코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstBndMbdCd" value="${lgsDlvList.dlvCstBndMbdCd}" style="width:100%;">
					<%-- 정책 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].plcDlvCst" id="plcDlvCst${lgsDlvspList.dlvPcupspTurn}"  value="${lgsDlvList.plcDlvCst}" class="number plcDlvCstClass" style="width:100%;">
					<%-- 취소 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].cnclDlvCst" id="cnclDlvCst${lgsDlvspList.dlvPcupspTurn}"  value="${lgsDlvList.cnclDlvCst}" class="cnclDlvCstClass">
					<!-- 반품배송비 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].rtgodDlvCst" value="${lgsDlvList.rtgodDlvCst}" class="number rtgodDlvCstClass" style="width:100%;">
					<%-- 교환 배송비 --%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].exchgDlvCst" value="${lgsDlvList.exchgDlvCst}" class="number exchgDlvCstClass" style="width:100%;">
					<%-- 실제 배송비--%>
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].realityDlvCst" id="realityDlvCst${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.realityDlvCst}" class="realityDlvCstClass">
					<!-- 배송 업체 코드 -->
					<!-- 지정 택배인 경우 택배사 기본 정책 조회 후 입력(자사), 택배인 경우 기존 주문의 택배사 조회후 입력(입점사) -->
					<c:choose>
						<c:when test="${lgsDlvspList.dlvMnCd eq 'APPN_HDRY'}">
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvComCd" value="${dlvCom.dlvComCd}" style="width:100%;">
						</c:when>
						<c:otherwise>
							<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvComCd" value="${lgsDlvList.dlvComCd}" style="width:100%;">
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:forEach>
</c:if>
<!-- 일반교환E -->

<!-- 반품S -->
<c:if test="${clmTpCd eq 'RTGOD' }">
	
	<h3><spring:message code="mypage.order.detail.lbl.claim.return.info" /></h3>
	
	<c:forEach var="lgsDlvspList" items="${lgsDlvspList}" varStatus="mainSts">
		<!-- 수거지별 물류배송 -->
		<c:forEach var="lgsDlvList" items="${lgsDlvspList.lgsDlvList}" varStatus="subSts">
			<!-- 수거지순번 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvAdbukTurn" value="${lgsDlvspList.dlvAdbukTurn}" style="width:100%;">
			<c:choose>
				<c:when test="${subSts.index == 0}">
					<!-- 배송 수거지 구분 코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvPcupspSectCd" value="${lgsDlvspList.dlvPcupspSectCd}" style="width:100%;">
					<!-- 배송지 배송수단코드 -->
					<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].dlvMnCd" value="${lgsDlvspList.dlvMnCd}" style="width:100%;">
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
			<!-- 국내 배송비 정책 일련번호 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dmstcDlvCstPlcSn" value="${lgsDlvList.dmstcDlvCstPlcSn}" style="width:100%;">
			<!-- 주문번호 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].ordNo" value="${lgsDlvList.ordNo}" style="width:100%;">
			<!-- 통화코드 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].crncyCd" value="${lgsDlvList.crncyCd}" style="width:100%;">
			<!-- 기준 통화 금액 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].stdrCrncyAmt" value="${lgsDlvList.stdrCrncyAmt}" style="width:100%;">
			<!-- 결제 환율 통화 금액 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].payExchgRtCrncyAmt" value="${lgsDlvList.payExchgRtCrncyAmt}" style="width:100%;">
			<!-- 교환 배송비 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].exchgDlvCst" value="${lgsDlvList.exchgDlvCst}" style="width:100%;">
			<!-- 배송비 부담 주체 코드 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstBndMbdCd" value="${lgsDlvList.dlvCstBndMbdCd}" style="width:100%;">
	
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].plcDlvCst" id="plcDlvCst${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.plcDlvCst}" class="number plcDlvCstClass" style="width:100%;">
	
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].cnclDlvCst" id="cnclDlvCst${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.cnclDlvCst}" class="cnclDlvCstClass">
	
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].rtgodDlvCst" value="${lgsDlvList.rtgodDlvCst}" class="number rtgodDlvCstClass" style="width:100%;">
	
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].realityDlvCst" id="realityDlvCst${ordGodList.dlvPcupspTurn}"  value="${lgsDlvList.realityDlvCst}" class="realityDlvCstClass">
	
			<%--배송 업체 코드(회수 배송업체는 한진택배만 사용) --%>
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvComCd" value="${dlvCom.dlvComCd}" >
	
			<!-- 반품쿠폰 번호 -->
	        <input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstCpnNo" value="${lgsDlvList.dlvCstCpnNo}">
			<!-- 쿠폰 적용 할인 금액 -->
			<input type="hidden" readOnly name="lgsDlvspTmpList[${mainSts.index}].lgsDlvList[${subSts.index}].dlvCstCpnDcAmt" id="dlvCstCpnDcAmt${lgsDlvspList.dlvPcupspTurn}" value="${lgsDlvList.dlvCstCpnDcAmt}" class="number">
		</c:forEach>
	</c:forEach>
	
	<c:set var="refundMile" value="0" />
	<c:set var="refundPnt" value="0" />
	
	<c:forEach items="${clmRefundDTO.refundResultDTO }" var="list" varStatus="status">
		<c:choose>
			<c:when test="${list.paymncd eq 'MBSH_PNT_PAY'}">
				<c:set var="refundMile" value="${refundMile + list.prc}" />
			</c:when>
			<c:when test="${list.paymncd eq 'WEB_PNT_PAY'}">
				<c:set var="refundPnt" value="${refundPnt + list.prc}" />
			</c:when>
		</c:choose>
	</c:forEach>
					
	<table class="tbTotalList">
		<caption><spring:message code="mypage.order.detail.lbl.claim.return.info" /></caption>
        <colgroup>
             <col style="width:50%">
             <col style="width:">
        </colgroup>
		<tbody>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.goods" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.salePrcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
				<input type="hidden" id="refundPrdDlvFee" name="refundPrdDlvFee" value="${clmRefundDTO.refundDlvFee+clmRefundDTO.refundPrdFee }"/>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.order.discount" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.bundleDcSumAmt + clmRefundDTO.crsDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.coupon.discount" /></th>
				<td><fmt:formatNumber value="${clmRefundDTO.godCpnDcSumAmt + clmRefundDTO.bskCpnDcSumAmt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr>
				<th><spring:message code="mypage.order.detail.lbl.payment.mileage.refund" /></th>
				<td><c:if test="${refundMile > 0 }">+</c:if><fmt:formatNumber value="${refundMile}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td><!-- 2018-05-24 -->
			</tr>
			<tr class="selmgB">
				<th><spring:message code="mypage.order.detail.lbl.payment.point.refund" /></th>
				<td><c:if test="${refundPnt > 0 }">+</c:if><fmt:formatNumber value="${refundPnt}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" /></td>
			</tr>
			<tr class="selLineBox">
				<th><spring:message code="mypage.order.detail.lbl.payment.delivery" text="배송비"/></th>
				<td>
					<fmt:formatNumber value="${clmRefundDTO.refundDlvFee}" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/>
				</td>
				<input type="hidden" id="cnclDlvCstAjax" value="${clmRefundDTO.orderDlvAmt}">
			</tr>

		<%-- <div class="claim-info-list claim-total">
			<th><strong><spring:message code="mypage.order.detail.lbl.payment.refund.amount" text="환불 금액"/></strong></th>
			<div class="right">
				<p class="total">
					<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt < 0}">
						<strong>0</strong>
					</c:if>
					<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt >= 0}">
						<strong><fmt:formatNumber value="${clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt }" pattern="#,###" /></strong>
					</c:if>
					<spring:message code="mypage.order.lbl.currency" text="원"/>
				</p>
				<span class="text-color01"><spring:message code="mypage.order.detail.lbl.payment.mileage.accml.cancel" text="마일리지 적립취소"/> <c:if test="${clmRefundDTO.unityPntAccmlAmtSum > 0}">-</c:if><fmt:formatNumber value="${clmRefundDTO.unityPntAccmlAmtSum}" pattern="#,###" />M</span><!-- 2018-06-19 -->
			</div>
		</div> --%>

		</tbody>
		<tfoot>
			<tr>
           		<th><spring:message code="mypage.order.detail.lbl.payment.refund.amount" /></th>
           		<td>
           			<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt < 0}">
						<strong>0</strong>
					</c:if>
					<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt >= 0}">
						<strong><fmt:formatNumber value="${clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt }" pattern="#,###" /></strong>
					</c:if>
					<spring:message code="mypage.order.lbl.currency" text="원"/>
           		</td>
           	</tr>
			<c:if test="${clmRefundDTO.refundDlvFee + clmRefundDTO.stdrCrncySumAmt < 0}">
				<tr>
					<th></th>
					<td>
						(<spring:message code="mypage.order.detail.lbl.payment.addpay.amount" /> : <fmt:formatNumber value="${(clmRefundDTO.refundDlvFee+clmRefundDTO.stdrCrncySumAmt) * -1 }" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/>)
					</td>
				</tr>
			</c:if>
		</tfoot>
	</table>
	
	<c:forEach items="${clmRefundDTO.refundResultDTO }" var="list" varStatus="status">
		<c:if test="${list.mpayMnYn eq 'Y' }">
			<input type="hidden" id="mainPayRefundAmount" name="mainPayRefundAmount" value="${list.prc}"/>
		</c:if>
		<input type="hidden" id="" name="refundResultDTO[${status.index}].paymncd" value="${list.paymncd}"/>
		<input type="hidden" id="" name="refundResultDTO[${status.index}].paycrncypayamt" value="${list.paycrncypayamt }"/>
		<input type="hidden" id="" name="refundResultDTO[${status.index}].remainpayamt" value="${list.remainpayamt }"/>
		<input type="hidden" id="" name="refundResultDTO[${status.index}].beforePayamt" value="${list.beforePayamt }"/>
		<input type="hidden" id="prc" value="${list.prc }" name="refundResultDTO[${status.index}].prc">
	</c:forEach>
					
	<%-- 주문 사은품 UI 처리 --%>
	<c:forEach var="gift" items="${clmRefundDTO.ordGftList}">
		<script>
			$('#P_ORD_GFT_${gift.ordGodTurn}').show();
		</script>
	</c:forEach>
</c:if>
<!-- 반품E -->