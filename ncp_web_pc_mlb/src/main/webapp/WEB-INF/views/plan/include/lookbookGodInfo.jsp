<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<div class="item" data-div="${godContt.cnrSetSn}-${godContt.conttTurn}">
		<div class="thumb"><a href="${godContt.godUrl}"><img src="${_image}${godContt.imgFrontUrl}/dims/resize/160x160" alt="${godContt.godNm}" onerror='errorImgShow(this, "160");'></a></div>
		<div class="info">
			<div class="name">
				<c:if test="${_locale eq 'ko'}">
					<c:if test="${!empty godContt.tagNm}">
						<span style="color:${godContt.colorTagNm}">${godContt.tagNm}</span>
					</c:if>
				</c:if>
				<c:if test="${godContt.brndId eq 'I'}">
					<span>[KIDS]</span>
				</c:if>
				${godContt.godNm}
			</div>
			<div class="prc">
				<span class="p n">
					<em><fmt:formatNumber value="${godContt.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
				</span>
			</div>
			<ul class="dlist">
				<li class="color">
					<div class="option-color">
					
						<c:forTokens items = "${godContt.optValCd2}" delims = "|" var = "color">
						
							<c:set var="godNo" value="" />
							<c:set var="godNm" value="" />
							<c:set var="godCd" value="" />
							<c:set var="godImg" value="" />
							
							<c:forTokens items = "${color}" delims = "," var = "option" varStatus="status">
								
								<c:if test="${status.index eq 0}">
									<c:set var="godNo" value="${option}" />
								</c:if>
								<c:if test="${status.index eq 1}">
									<c:set var="godNm" value="${option}" />
								</c:if>
								<c:if test="${status.index eq 2}">
									<c:set var="godCd" value="${option}" />
								</c:if>
								<c:if test="${status.index eq 3}">
									<c:set var="godImg" value="${option}" />
								</c:if>
								
							</c:forTokens>
							
							<a title="${godCd}" data-a="${godNo}" href="javascript:void(0);" class="btn-color d_radio_select <c:if test="${godContt.godNo eq godNo}">on</c:if>" >
								<img src="${_image}${godImg}/dims/resize/30x30" alt="${godNm}" onerror='errorImgShow(this, "30");'>
							</a>
								
						</c:forTokens>
						
					</div>
				</li>
				<li class="size">
					<div class="option-size">
						
						<c:set value="0" var="sizeCnt"/>
						
						<c:forTokens items = "${godContt.optValCd1}" delims = "|" var = "size">
							
							<c:choose>
								
								<c:when test="${fn:contains(size, 'DEL')}">
									<button type="button" class="btn-size d_radio_select" disabled="disabled">
										<span>${fn:substringAfter(size, 'DEL')}</span>
									</button>		
								</c:when>
								<c:otherwise>
									<c:set value="${sizeCnt + 1}" var="sizeCnt"/>
									<button type="button" class="btn-size d_radio_select <c:if test="${sizeCnt eq 1}">on</c:if>" >
										<span>${size}</span>
									</button>		
								</c:otherwise>
								
							</c:choose>
	
					    </c:forTokens>
					</div>
				</li>
			</ul>
			
			<c:if test="${lookbook.lang eq 'KOR'}">
				<div class="bts" data-div="${godContt.godUrl}">
					<a href="javascript:void(0);" class="btn fill sm btnAddCart"><spring:message code="display.lookbook.cart" /></a>
					
					<c:set value="0" var="wishCnt"/>
					<c:set value="N" var="wishYn"/>
					
					<c:forTokens items = "${godContt.optValCd3}" delims = "|" var = "wish" varStatus="status">
						<c:if test="${status.index eq 0}">
							<c:set value="${wish}" var="wishCnt"/>	
						</c:if>
						<c:if test="${status.index eq 1}">
							<c:set value="${wish}" var="wishYn"/>	
						</c:if>
					</c:forTokens>
					<a href="javascript:void(0);" data-a="${godContt.godNo}" class="btn sm btnWish <c:if test="${wishYn eq 'Y'}">active</c:if>" >위시리스트 담기</a>
				</div>
			</c:if>
			<c:if test="${lookbook.lang ne 'KOR'}">
				<div class="bts ext">
					<a href="javascript:void(0);" class="btn fill sm btnAddCart"><spring:message code="display.lookbook.find.store" /></a>
				</div>
			</c:if>
			
		</div>
	</div>