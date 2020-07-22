<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<form id="goodsWishlistForm">
	<input type="hidden" id="parameterName"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="wishlstSn" id="wishlstSn" value="${wishlstSn}"/>
	<input type="hidden" name="godTurn" id="godTurn" value="${godTurn}"/>
	<input type="hidden" id="type" value=""><!-- 장바구니 : basket, 주문 : add -->
	<input type="hidden" name="bskGodList[0].godNo" id="bskGodGodNo" value="${goods.godEx.godNo}"/>
	<input type="hidden" name="godNo" value="${goods.godEx.godNo}"/>
	<input type="hidden" name="bskGodList[0].pckageGodYn" id="wishlistPckageGodYn" value="N"/>  
	<input type="hidden"  id="erpGodNo" value="${goods.godEx.erpGodNo}"/>  
</form>
<form id="goodsForm">
	<input type="hidden" id="parameterName"  name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="lastSalePrc" id="lastSalePrc" value="${goods.dspGodPrc.lastSalePrc}"/>
	<input type="hidden" name="godTpCd" id="godTpCd" value="${goods.godEx.godTpCd}"/>
	<input type="hidden" name="totalPrice" id="totalPrice" value=""/>
	<input type="hidden" id="godNm" value="${goods.godEx.godNm}">
	<input type="hidden" id="dsgnGrpNo" value="${goods.godEx.dsgnGrpNo}">
	<input type="hidden" id="brndId" value="${goods.godEx.brndId}">
	<input type="hidden" id="giftPromoAplYn" value="${content.giftPromoAplYn}">		
	<!-- 장바구니 : basket -->		
	<input type="hidden" id="type" value="basket"><!-- 장바구니 : basket, 주문 : add -->
	<input type="hidden" name="god.godNo" id="bskGodGodNo" value="${goods.godEx.godNo}"/>
	<input type="hidden" name="god.itmNo" id="bskGodItmNo" value=""/>
	<input type="hidden" name="god.itmQty" id="bskGodItmQty" value="1"/>
	<input type="hidden" name="god.cpstCnt" id="cpstCnt" value="${fn:length(goods.godCpstGodCnncExList)}"/>		
	<input type="hidden" name="god.dlvSectCd" id="bskGodDlvSectCd" value="GNRL_DLV"/>
	<input type="hidden" name="god.pckageGodYn" id="bskGodPckageGodYn" value="N"/>
	<input type="hidden" name="god.pkupShopSn" id="bskGodPkupShopSn" value=""/>
	<input type="hidden" name="god.brndId" id="bskGodbrndId" value="${goods.godEx.brndId}">
	<input type="hidden" name="callBakcUrl" id="callBakcUrl" value=""/>
	<input type="hidden" name="popUpType" id="popUpType" value=""/>		
	<c:if test="${goods.godEx.resveSaleGodYn eq 'Y'}">
		<input type="hidden" name="resveSaleGodYn" id="resveSaleGodYn" value="Y"/>
	</c:if>
	<input type="hidden" name="naverPayYn" id="naverPayYn" value="N"/>
	<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
    	<input type="hidden" name="loginYn" id="loginYn" value="N"/>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('ROLE_USER')">
		<input type="hidden" name="loginYn" id="loginYn" value="Y"/>
	</sec:authorize>		
</form>

<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">									
 	<c:choose>
		<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">										
			<c:set var="invQty"/>
			<!-- 예약판매 체크 -->
			<c:if test="${goods.godEx.resveSaleGodYn eq 'Y' }">
				<c:set var="invQty" value="${size.resveInvQty}"/>
			</c:if>
			<c:if test="${goods.godEx.resveSaleGodYn eq 'N' }">
				<c:set var="invQty" value="${size.totUsefulInvQty}"/>
			</c:if>										
			<input name="${size.itmNm}" data-index="0" data-inv-qty="${invQty}" data-sale-prearnge-qty="${size.salePrearngeQty }" value="${size.itmNo}"/>
		</c:when>
		<c:otherwise>
 			<input name="${size.itmNm}" disabled="disabled" />
		</c:otherwise>
	</c:choose>											
</c:forEach>