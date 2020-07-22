
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
<meta property="recopick:title" content="${goods.godEx.godNm}">
<meta property="recopick:image" content="${imageURL}${goods.godImg.imgUrl}">
<meta property="recopick:price" content="${goods.dspGodPrc.rtlPrc}">
<meta property="recopick:price:currency" content="KRW">
<meta property="recopick:description" content="${goods.godEx.godNm}">

<c:if test="${goods.dspGodPrc.rtlPrc > goods.dspGodPrc.salePrc}">
<meta property="recopick:sale_price" content="${goods.dspGodPrc.salePrc}">
<meta property="recopick:sale_price:currency" content="KRW">
</c:if>

<script type="text/javascript" src="${_resourceURL}static/js/goods.js?v=${_version}"></script>
<script type="text/javascript" src="/javascript/message/goods_${pageContext.response.locale.language}.js?v=${_version}"></script>
<script>
	var	_erpNo = '${goods.godEx.erpGodNo}';
	var _godNo = '${goods.godEx.godNo}';
	var _godNm = '${goods.godEx.godNm}';
	var _godPrice = parseInt('${goods.dspGodPrc.lastSalePrc}');
	var _pickupStoreCnt = 0;
	
	$(document).ready(function() {			
		// $('#recommendProduct').viewtogether({godNo:_erpNo});
	});
</script>

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
	<!-- 장바구니 : basket, 주문 : add -->	
	<input type="hidden" id="type" value="">
	<input type="hidden" name="god.godNo" id="bskGodGodNo" value="${goods.godEx.godNo}"/>
	<input type="hidden" name="god.itmNo" id="bskGodItmNo" value=""/>
	<input type="hidden" name="god.itmQty" id="bskGodItmQty" value="1"/>
	<input type="hidden" name="god.cpstCnt" id="cpstCnt" value="${fn:length(goods.godCpstGodCnncExList)}"/>	
	<input type="hidden" name="god.dlvSectCd" id="bskGodDlvSectCd" value=""/>
	<input type="hidden" name="god.pckageGodYn" id="bskGodPckageGodYn" value="N"/>
	<input type="hidden" name="god.pkupShopSn" id="bskGodPkupShopSn" value=""/>
	<input type="hidden" name="god.brndId" id="bskGodbrndId" value="${goods.godEx.brndId}">
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
	<%-- 세트, 추가구성 상품--%>	
	<div id="basketDiv">
	
	</div>			
</form>
	
<ncp:codes group="SIDO" var="siList"/>
<c:set var="goodsNm" value="${goods.godEx.godNm }"/>
<c:set var="isGoodSoldOut" value="${goods.godEx.godSaleSectCd eq 'SLDOUT' or goods.godEx.godSaleSectCd eq 'SMTM_SLDOUT' or 'Y' eq isBoPreview}"/>
	
<acrticle id="container">
	<section id="contents" class="product-detail">
	
	<!-- 컨텐츠 시작  -->	
	<div class="contain pd goods" id="contain">
		<div class="container">

			<main class="contents" id="contents">
			
				<div class="gdsWrap">
					
					<!-- 상품  -->
					<section class="sect product">
						<%@ include file="/WEB-INF/views/goods/include/inc_pd_gd_center.jsp" %>
						
						<%@ include file="/WEB-INF/views/goods/include/inc_pd_gd_right.jsp" %>
						
						<%@ include file="/WEB-INF/views/goods/include/inc_pd_gd_left.jsp" %>
					</section>

					<!-- STYLE IN MLB(브랜드상품공지사항) -->
					<c:if test="${fn:length(content.godNotiExList) > 0}">
						<%@ include file="/WEB-INF/views/goods/include/inc_pd_brand.jsp" %>
					</c:if>
					
					<!-- STYLE IN SNS(OLAPIC) -->
					<%@ include file="/WEB-INF/views/include/olapic_prd.jsp" %>

					<!-- 연관상품(ERP 세트상품 + 동일 Depth) -->
					<c:if test="${not empty relatedGoods}">
						<%@ include file="/WEB-INF/views/goods/include/inc_pd_related.jsp" %>
					</c:if>

					<!-- 추천상품(RECOPIC) -->
					<%@ include file="/WEB-INF/views/goods/include/inc_pd_recommend.jsp" %>

				</div>
				
	
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	</section>
</acrticle>

<!-- 상세 레이어 팝업 -->
<%@ include file="/WEB-INF/views/goods/include/goods.layer.popup.jsp"%>	

<!-- 상세 광고 스크립트 -->
<%@ include file="/WEB-INF/views/goods/include/goods.ad.script.jsp"%>
	
<!-- cre.ma / 상품 번호를 가져오기 위한 가상 위젯 / 스크립트를 수정할 경우 연락주세요 (support@cre.ma) -->
<div class="crema-product-reviews" data-product-code="${goods.godEx.erpGodNo}" style="display: none !important;"></div>

<!-- cre.ma / 공통 스크립트 (PC) / 스크립트를 수정할 경우 연락주세요 (support@cre.ma) -->
<script>(function(i,s,o,g,r,a,m){if(s.getElementById(g)){return};a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.id=g;a.async=1;a.src=r;m.parentNode.insertBefore(a,m)})(window,document,'script','crema-jssdk','//widgets.cre.ma/mlb-korea.com/init.js');</script>