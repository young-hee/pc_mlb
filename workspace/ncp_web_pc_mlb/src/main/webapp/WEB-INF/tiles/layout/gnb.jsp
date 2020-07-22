<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<nav class="gnb">
	<div class="pan">
		<div class="menu">
			<ul class="list">
				<c:forEach var="gnbCate" items="${GNBCategoryList.category1}" varStatus="status">
					<c:if test="${status.index+1 le 6}">
						<li>
							<a href="javascript:cateLink('${gnbCate.ctgryInfo.dspCtgryNo}', '1', '${gnbCate.ctgryInfo.ctgrySectCd}', '${gnbCate.ctgryInfo.dspCtgryNo}');">${gnbCate.ctgryInfo.dspCtgryNm }</a>
							<ul>
								<c:forEach var="gnbCate2" items="${GNBCategoryList.category2}" varStatus="status2">
									<c:if test='${gnbCate2.ctgryInfo.upperDspCtgryNo eq gnbCate.ctgryInfo.dspCtgryNo }'>		
										<li><a href="javascript:cateLink('${gnbCate2.ctgryInfo.dspCtgryNo}', '2', '${gnbCate2.ctgryInfo.ctgrySectCd}', '${gnbCate2.ctgryInfo.upperDspCtgryNo}', '${gnbCate2.ctgryInfo.dspCtgryNo}');">${gnbCate2.ctgryInfo.dspCtgryNm }</a></li>
									</c:if>
								</c:forEach>					
							</ul>
						</li>
					</c:if>
				</c:forEach>				
			</ul>
		</div>
		<div class="link">
			<ul class="list">
				<li><a href="/event/promotionList">PROMOTION/EVENT</a></li>
				<li><a href="/display/view?dspCtgryNo=MBMA06&currentCtgryDpthCd=1&ctgrySectCd=OTLT_CTGRY&ctgryNoDpth1=MBMA06">OUTLET</a></li>
				<li><a href="/display/majorView?dspCtgryNo=MBMA12&currentCtgryDpthCd=1&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA12">FAMILY</a></li>
				<li><a href="/lookbook/lookbookList">LOOKBOOK</a></li>
				<li><a href="/culture/cultureList">CULTURE</a></li>
				<li><a href="/olapic/view?olapicForceRender">#STYLE in SNS</a></li>
			</ul>
		</div>
		<div class="banner">
			<ul class="list">
				<c:forEach var="gnbBnr" items="${GNBCommonBanner.gnbBnr}">	
					<li><a href="${gnbBnr.conttCnncUrl}"><img class="img" src="${_image}${gnbBnr.imgFileUrl}/${gnbBnr.imgFileNm}/dims/resize/216x144" alt="${gnbBnr.imgAltrtvCont}"></a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</nav>

