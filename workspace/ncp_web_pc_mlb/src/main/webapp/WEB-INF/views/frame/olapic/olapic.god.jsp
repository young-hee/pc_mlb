<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<c:forEach items="${goodsList}" var="goods">
	<c:if test="${ !empty goods.godEx.erpGodNo}">
<li goodsli="${goods.godEx.erpGodNo}">
	<div class="product-list-item">
		<div class="product-list-item-image">
			<a 
<c:forEach items="${ goods.designColorList }" var="dGod">
	<c:if test="${ dGod.imgUrl eq goods.godImg.imgUrl }">
			href="${ dGod.godDetailUrl }" 
	</c:if>			
</c:forEach>			
			goodslink="${goods.godEx.erpGodNo}">
				<img src="<ncp:img path='${goods.godImg.imgUrl}'/>/dims/resize/160x160" 
					onerror="errorImgShow(this,'160x160');"
					style="width: 100%;background-color: #eeeeee;">
			</a>
		</div>
		<div class="product-list-item-title">
			<p>${goods.godEx.godNm}</p>
			<p class="price">
	<c:if test="${goods.dspGodPrc.rtlPrc ne goods.dspGodPrc.csmrPrc}">
				<span class="origin-price"><fmt:formatNumber value="${goods.dspGodPrc.rtlPrc}" type="number"/><spring:message code="goods.common.lbl.won"/></span>
	</c:if>
				<span class="sale-price"><fmt:formatNumber value="${goods.dspGodPrc.salePrc}" type="number" /><spring:message code="goods.common.lbl.won"/></span>
			</p>
			<ul>
	<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">
				<li class="option-size-li">
					<div class="option-size on">									
		<c:choose>
			<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">												
				<c:set var="invQty"/>
				<c:if test="${goods.godEx.resveSaleGodYn eq 'Y' }">
					<c:set var="invQty" value="${size.resveInvQty}"/>
				</c:if>
				<c:if test="${goods.godEx.resveSaleGodYn eq 'N' }">
					<c:set var="invQty" value="${size.totUsefulInvQty}"/>
				</c:if>
						<button type="button" class="btn-size d_radio_select" onclick="sizeChangeOfOlapic('${ goods.godEx.erpGodNo }','${size.itmNo}', this);">
							<span><c:out value="${size.itmNm}"/></span>
						</button>
			</c:when>
			<c:otherwise>
						<button type="button" class="btn-size d_radio_select" disabled>
							<span><c:out value="${size.itmNm}"/></span>
						</button>
			</c:otherwise>
		</c:choose>											
					</div>
				</li>
	</c:forEach>
			</ul>
		<c:choose>	
			<c:when test="${goods.godEx.godSaleSectCd eq 'SLDOUT'}">
			<a href="#" class="btn fill sm btnAddCart" style="cursor:text;">SOLD OUT</a>
			</c:when>
			<c:when test="${goods.godEx.godSaleSectCd eq 'SMTM_SLDOUT'}">
			<a href="#" class="btn fill sm btnAddCart" style="cursor:text;">COMMING SOON</a>
			</c:when>
			<c:otherwise>
			<input type="hidden" name="itmNo" erpGodNo="${ goods.godEx.erpGodNo }" value="" />
			<a href="javascript:void(0)" onclick="addBasketFromOlapic('${ goods.godEx.erpGodNo }');"
				class="btn fill sm btnAddCart">ADD TO CART</a>
			</c:otherwise>
		</c:choose>
<form id="goodsForm_${ goods.godEx.erpGodNo }">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="lastSalePrc" value="${goods.dspGodPrc.lastSalePrc}"/>
		<input type="hidden" name="godTpCd" value="${goods.godEx.godTpCd}"/>
		<input type="hidden" name="totalPrice" value=""/>
		<input type="hidden" id="godNm_${ goods.godEx.erpGodNo }" value="${goods.godEx.godNm}">
		<input type="hidden" id="dsgnGrpNo_${ goods.godEx.erpGodNo }" value="${goods.godEx.dsgnGrpNo}">
		<input type="hidden" id="brndId_${ goods.godEx.erpGodNo }" value="${goods.godEx.brndId}">
		<input type="hidden" id="giftPromoAplYn_${ goods.godEx.erpGodNo }" value="${content.giftPromoAplYn}">		
		<!-- 장바구니 : basket -->		
		<input type="hidden" name="type" value="basket"><!-- 장바구니 : basket, 주문 : add -->
		<input type="hidden" name="god.godNo" value="${goods.godEx.godNo}"/>
		<input type="hidden" name="god.itmNo" value=""/>
		<input type="hidden" name="god.itmQty" value="1"/>
		<input type="hidden" name="god.cpstCnt" value="${fn:length(goods.godCpstGodCnncExList)}"/>		
		<input type="hidden" name="god.dlvSectCd" value="GNRL_DLV"/>
		<input type="hidden" name="god.pckageGodYn" value="N"/>
		<input type="hidden" name="god.pkupShopSn" value=""/>
		<input type="hidden" name="god.brndId" value="${goods.godEx.brndId}">
		<c:if test="${goods.godEx.resveSaleGodYn eq 'Y'}">
			<input type="hidden" name="resveSaleGodYn" value="Y"/>
		</c:if>
		<input type="hidden" name="naverPayYn" value="N"/>
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
	    	<input type="hidden" name="loginYn" value="N"/>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			<input type="hidden" name="loginYn" value="Y"/>
		</sec:authorize>
	</form>
		</div>
	</div>
</li>
	</c:if>
</c:forEach>