<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>

<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
<acrticle id="container"  class="contents-type01">
	<section id="contents">
	      <div class="contents-type01-box01">
					<div class="category-visual" id="categoryVisual">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
							 <c:forEach items="${contt.cnrConttList}" var="bnr" varStatus="i" end="0">
								<li class="swiper-slide"><a href="${bnr.conttCnncUrl }" onclick="return false;" conttCnncUrl="${bnr.conttCnncUrl }"  outptTpCd="${bnr.outptTpCd }" ><img src="${imageURL}${bnr.imgFileUrl}/${bnr.imgFileNm }" alt="${bnr.imgNm }"></a></li>
							 </c:forEach>
							</ul>
						</div>
						<div class="category-visual-pagination">
							<div></div>
						</div>
					</div>
				</div>
				<div class="contents-type01-box02">
					<div class="category-list">
						<h2>BEST ITEM</h2>
						<div id="bestItem" class="item-list03 list-swiper-type">
							<div class="item-list03-cont swiper-container">
								<ul class="swiper-wrapper">
								 <c:forEach items="${contt.cnrGodConttList}" var="god" varStatus="i">
								 	<li class="swiper-slide">
										<div class="item-img">			 
											<a  href="${god.godUrl }" class="goDetail"  onclick="return false;" conttCnncUrl="${god.godUrl }" outptTpCd="god">
												<img src="${imageURL }${god.imgFrontUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_319x426)%>" alt="${god.godNm }" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_319x426)%>');">
											</a>
										</div>
										<div class="item-info">
											<p class="txt01"><a  href="${god.godUrl }" onclick="return false;"   conttCnncUrl="${god.godUrl }" outptTpCd="god" >${god.godNm }</a></p>
	                                <c:choose>
									  <c:when test="${god.rtlPrc > god.lastSalePrc}">
									<p class="txt01"><del><fmt:formatNumber value="${god.rtlPrc}" type="number"/><spring:message code="display.main.text1" /></del><strong><fmt:formatNumber value="${god.lastSalePrc}" type="number"/><spring:message code="display.main.text1" /></strong></p>
									  </c:when>
									  <c:otherwise>
									 <p class="txt01"><strong><fmt:formatNumber value="${god.lastSalePrc}" type="number"/><spring:message code="display.main.text1" /></strong></p>
									  </c:otherwise>
									</c:choose>

											<div class="txt02">
										<ul>
											${god.optValCd1}
										</ul>
											</div>
										</div>
									</li>
								 </c:forEach>															

								</ul>
							</div>
							<button type="button" class="item-list-prev">이전</button>
							<button type="button" class="item-list-next">다음</button>
						</div>
						<script>
							var bestItem = new Swiper('#bestItem .swiper-container', {
								slidesPerGroup: 4,
								slidesPerView: 4,
								observer: true,
								observeParents: true,
								navigation: {
									nextEl: '#bestItem .item-list-next',
									prevEl: '#bestItem .item-list-prev'
								}
							});
						</script>
				<div class="item-sort-filter">
 <%@ include file="/WEB-INF/views/display/include/display.filter.jsp"%>
					</div>
					<div class="item-list03">
					  <div class="item-list03-cont">
						<ul>
						   <c:forEach items="${godList}" var="god" varStatus="i">
						   <input type="hidden" class="criteoGod" value="${god.god.erpGodNo }"/>
						     <li>
								<div class="item-img">
 
									<a href="${god.godUrl }" onclick="return false;" class="goDetail"  conttCnncUrl="${god.godUrl }" outptTpCd="god">
											<img src="${imageURL }${god.imgFrontUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_319x426)%>" alt="${god.god.godNm }" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_319x426)%>');">
									</a>
								</div>
								<div class="item-info">
									<p class="txt01"><a href="${god.godUrl }" class="goDetail" onclick="return false;" conttCnncUrl="${god.godUrl }" outptTpCd="god">${god.god.godNm }</a></p>
	                               <c:choose>
									  <c:when test="${god.dspGodPrc.rtlPrc > god.dspGodPrc.lastSalePrc}">
									<p class="txt01"><del><fmt:formatNumber value="${god.dspGodPrc.rtlPrc  }" type="number"/>원</del><strong><fmt:formatNumber value="${god.dspGodPrc.lastSalePrc }" type="number"/>원</strong></p>
									  </c:when>
									  <c:otherwise>
									 <p class="txt01"><strong><fmt:formatNumber value="${god.dspGodPrc.lastSalePrc }" type="number"/>원</strong></p>
									  </c:otherwise>
									</c:choose>
  
									<div class="txt02">
										<ul>
											${god.optValCd1 }
										</ul>
									</div>
								</div>
							</li>   
						   </c:forEach>
											 
						</ul>
						</div>
					</div>
    <%@ include file="/WEB-INF/views/display/include/display.page.jsp"%>
					</div>
				</div>
	 
 
 <%@ include file="/WEB-INF/views/main/include/aTagLink.jsp"%>
 	</section>
</acrticle>