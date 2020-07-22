<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%
	java.util.Date now = new java.util.Date();
	String yy = Integer.toString( now.getYear()+1900 ) ;
	String mm = Integer.toString( now.getMonth() ) ;
	String dd = Integer.toString( now.getDate() ) ;
	String hh = Integer.toString( now.getHours() ) ;
	String nn = Integer.toString( now.getMinutes() ) ;
	String ss = Integer.toString( now.getSeconds() ) ;
	String ver_css =  mm +"-"+ dd +"-"+ hh +"-"+ nn +"-"+ ss ;
%>
<link href="${_resourceURL}static/css/bs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
<link href="${_resourceURL}static/css/cm.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
<link href="${_resourceURL}static/css/my.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
<link href="${_resourceURL}static/css/od.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
<style>
body{
	overflow-y: scroll;
	padding-right: 30px;
	margin-right: -30px
}
@media print{
	body{padding-right:0;margin-right:0}
}
</style>
<div class="receipt lnblist-Wrap">
	<div class="page-head">
		<h2 class="title01">거래명세서</h2>
		<button type="button" onclick="window.print();"></button>
	</div>
	<div class="location justify-wrap">
		<p class="right left">조회시간 ${currentDateString}</p>
		<p class="right">주문 No. ${orderInfo.ordNo}</p>
	</div>
	<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
		<c:if test="${status.index eq 0}">
			<c:set value="${listDlvsp.addrseNm}" var="addrseNm"/>
			<c:set value="${listDlvsp.addrseMobilAreaNo}" var="addrseMobilAreaNo"/>
			<c:set value="${listDlvsp.addrseMobilTlofNo}" var="addrseMobilTlofNo"/>
			<c:set value="${listDlvsp.addrseMobilTlofWthnNo}" var="addrseMobilTlofWthnNo"/>
			<c:set value="${listDlvsp.addrseBaseAddr}" var="addrseBaseAddr"/>
			<c:set value="${listDlvsp.addrseDetailAddr}" var="addrseDetailAddr"/>
		</c:if>
	</c:forEach>
	<table class="table-receipt">
		<caption>결제 정보</caption>
		<colgroup>
			<col style="width:10%">
			<col style="width:40%">
			<col style="width:10%">
			<col style="width:40%">
		</colgroup>
		<thead>
			<tr>
				<th>&nbsp;</th>
				<th class="tcenter">주문인 정보</th>
				<th>&nbsp;</th>
				<th class="tcenter">수령인 정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="tright">이름　: </td>
				<td class="tcenter"><c:out value='${orderInfo.cstmrNm}'/></td>
				<td class="tright">이름　: </td>
				<td class="tcenter"><c:out value='${addrseNm}'/></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td class="tright">이메일　: </td> -->
<!-- 				<td class="tcenter">byyoo@fnf.co.kr</td> -->
<!-- 				<td class="tright">이메일　: </td> -->
<!-- 				<td class="tcenter">byyoo@fnf.co.kr</td> -->
<!-- 			</tr> -->
			<tr>
				<td class="tright">연락처　: </td>
				<td class="tcenter"><c:out value='${orderInfo.cstmrMobilAreaNo}'/>-<c:out value='${orderInfo.cstmrMobilTlofNo}'/>-<c:out value='${orderInfo.cstmrMobilTlofWthnNo}'/></td>
				<td class="tright">연락처　: </td>
				<td class="tcenter"><c:out value='${addrseMobilAreaNo}'/>-<c:out value='${addrseMobilTlofNo}'/>-<c:out value='${addrseMobilTlofWthnNo}'/></td>
			</tr>
			<tr>
				<td class="tright">이메일　: </td>
				<td class="tcenter"><c:out value='${orderInfo.cstmrEmail}'/></td>
				<td class="tright">주소　: </td>
				<td class="tcenter"><c:out value='${addrseBaseAddr}'/>&nbsp;<c:out value='${addrseDetailAddr}'/></td>
			</tr>
		</tbody>
	</table>

	<div class="board-list">
		<table>
			<caption>거래명세서</caption>
			<colgroup>
				<col style="width:60px;" />
				<col />
				<col style="width:100px;" />
				<col style="width:100px;" />
				<col style="width:120px;" />
				<col style="width:130px;" />
			</colgroup>
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">상품정보</th>
					<th class="tright" scope="col">정가</th>
					<th class="tright" scope="col">판매단가</th>
					<th scope="col">옵션 / 수량</th>
					<th class="tright" scope="col">주문금액</th>
				</tr>
			</thead>
			<tbody>
			<c:set value="0" var="ordGodNumber"/>
			<c:set value="0" var="realSaleSumAmt"/>
			<c:set value="0" var="realRtlPrc"/>
			<c:set value="0" var="clmPayExchgRtCrncySumAmt"/>
			<c:set value="0" var="clmGodCpnDcSumAmt"/>
			<c:set value="0" var="clmBskCpnDcSumAmt"/>
			<c:set value="0" var="clmWebDcSumAmt"/>
			<c:set value="0" var="clmBundleDcSumAmt"/>
			<c:set value="0" var="clmCrsDcSumAmt"/>
			<c:set value="0" var="clmUnityPntUseSumAmt"/>
			<c:set value="0" var="clmWebpntUseSumAmt"/>
			<c:set value="0" var="clmDlvCstSumAmt"/>

			<c:forEach var="listDlvsp" varStatus="status" items="${orderInfo.lgsDlvspFoExtend}">
			<c:forEach var="lgsDlvList" varStatus="lgsDlvStatus" items="${listDlvsp.lgsDlvFoExtendList}">
				<c:forEach var="ordGodList" varStatus="ordGodstatus" items="${lgsDlvList.ordGodList}">
					<c:if test="${empty ordGodList.gftTpCd and ordGodList.clmTpCd ne 'GNRL_EXCHG'}">
						<c:set value="${ordGodNumber+1}" var="ordGodNumber"/>
						<c:set value="0" var="clmQty"/>
						<c:if test="${ordGodList.mainClmStatCd eq 'COMPT' or ordGodList.clmStatCd eq 'COMPT'}">
							<c:set value="${clmPayExchgRtCrncySumAmt+(ordGodList.clmPayExchgRtCrncySumAmt)}" var="clmPayExchgRtCrncySumAmt"/>
							<c:set value="${clmGodCpnDcSumAmt+(ordGodList.clmGodCpnDcSumAmt)}" var="clmGodCpnDcSumAmt"/>
							<c:set value="${clmBskCpnDcSumAmt+(ordGodList.clmBskCpnDcSumAmt)}" var="clmBskCpnDcSumAmt"/>
							<c:set value="${clmWebDcSumAmt+(ordGodList.clmWebDcSumAmt)}" var="clmWebDcSumAmt"/>
							<c:set value="${clmBundleDcSumAmt+(ordGodList.clmBundleDcSumAmt)}" var="clmBundleDcSumAmt"/>
							<c:set value="${clmCrsDcSumAmt+(ordGodList.clmCrsDcSumAmt)}" var="clmCrsDcSumAmt"/>
							<c:set value="${clmUnityPntUseSumAmt+(ordGodList.clmUnityPntUseSumAmt)}" var="clmUnityPntUseSumAmt"/>
							<c:set value="${clmWebpntUseSumAmt+(ordGodList.clmWebpntUseSumAmt)}" var="clmWebpntUseSumAmt"/>
							<c:set value="${clmDlvCstSumAmt+(ordGodList.clmDlvCstSumAmt)}" var="clmDlvCstSumAmt"/>
							<c:set value="${ordGodList.clmQty}" var="clmQty"/>
						</c:if>
						<c:if test="${(ordGodList.ordQty - clmQty) gt 0}">
							<c:set value="${realRtlPrc+(ordGodList.rtlPrc*(ordGodList.ordQty - clmQty))}" var="realRtlPrc"/>
							<c:set value="${realSaleSumAmt+(ordGodList.saleAmt*(ordGodList.ordQty - clmQty))}" var="realSaleSumAmt"/>
							<tr>
								<td>${ordGodNumber}</td>
								<td class="tleft"><c:out value='${ordGodList.godNm}'/> &nbsp; <c:out value='${ordGodList.erpGodNo}'/></td>
								<td class="tright"><fmt:formatNumber value="${ordGodList.rtlPrc}" pattern="#,###" />원</td>
								<td class="tright"><fmt:formatNumber value="${ordGodList.saleAmt}" pattern="#,###" />원</td>
								<td><c:out value='${ordGodList.itmNm}'/> / <c:out value='${ordGodList.ordQty - clmQty}'/>개</td>
								<td class="tright"><fmt:formatNumber value="${ordGodList.saleAmt * (ordGodList.ordQty - clmQty)}" pattern="#,###" />원</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</c:forEach>
			</c:forEach>

			</tbody>
			<tfoot>
				<tr>
					<th class="tright" colspan="6">합계　: <fmt:formatNumber value="${realSaleSumAmt}" pattern="#,###" />원</th>
				</tr>
			</tfoot>
		</table>
	</div>

	<table class="nowrap">
		<colgroup>
			<col />
			<col style="width:20px" />
			<col />
			<col style="width:20px" />
			<col />
			<col style="width:20px" />
			<col />
			<col style="width:20px" />
			<col />
			<col style="width:20px" />
			<col />
			<col style="width:20px" />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>총 결제금액</th>
				<td>=</td>
				<td>정가</td>
				<td>-</td>
				<td>쿠폰할인</td>
				<td>-</td>
				<td>기타할인</td>
				<td>-</td>
				<td>마일리지사용</td>
				<td>-</td>
				<td>포인트사용</td>
				<td>+</td>
				<td>배송비</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th><fmt:formatNumber value="${orderInfo.payExchgRtCrncySumAmt - clmPayExchgRtCrncySumAmt}" pattern="#,###" />원</th><%-- 총 결제금액 --%>
				<td>=</td>
				<td><fmt:formatNumber value="${realRtlPrc}" pattern="#,###" /></td><%-- 정가 --%>
				<td>-</td>
				<td><fmt:formatNumber value="${(orderInfo.godCpnDcSumAmt + orderInfo.bskCpnDcSumAmt) - (clmGodCpnDcSumAmt + clmBskCpnDcSumAmt)}" pattern="#,###" /></td><%-- 쿠폰할인 --%>
				<td>-</td>
				<td><fmt:formatNumber value="${(orderInfo.webDcSumAmt + orderInfo.bundleDcSumAmt + orderInfo.crsDcSumAmt) - (clmWebDcSumAmt + clmBundleDcSumAmt + clmCrsDcSumAmt)}" pattern="#,###" /></td><%-- 기타할인 --%>
				<td>-</td>
				<td><fmt:formatNumber value="${orderInfo.unityPntUseSumAmt - clmUnityPntUseSumAmt}" pattern="#,###" /></td><%-- 마일리지사용 --%>
				<td>-</td>
				<td><fmt:formatNumber value="${orderInfo.webpntUseSumAmt - clmWebpntUseSumAmt}" pattern="#,###" /></td><%-- 포인트사용 --%>
				<td>+</td>
				<td><fmt:formatNumber value="${orderInfo.dlvCstSumAmt + (clmDlvCstSumAmt * -1)}" pattern="#,###" /></td><%-- 배송비 --%>
			</tr>
		</tbody>
	</table>
	<p>(주)F&F</p>
</div>
