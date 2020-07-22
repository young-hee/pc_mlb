<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!-- 컨텐츠 시작 -->
<div class="contain dp promo" id="contain">
	<div class="container">
		<main class="contents" id="contents">
			<!-- 카테고리 메뉴 -->
			<div class="dp_cate_top">
				<ul class="menu">
					<li>
						<span class="txt">
							<em class="t">
								<c:choose>
									<c:when test="${dspCtgryScFrDTO.currentCtgryDpthCd eq 3}">
										${dpth2Nm}
									</c:when>
									<c:otherwise>
										${fLocation}
									</c:otherwise>
								</c:choose>
							</em><c:if test="${dspCtgryScFrDTO.currentCtgryDpthCd ne 1}"><a href="javascript:;" class="bt">하위메뉴열기</a></c:if></span>
						<c:if test="${dspCtgryScFrDTO.currentCtgryDpthCd ne 1}">
							<div class="subs">
								<ul class="list">
									<li class="on"><a href="javascript:cateLink('${param.ctgryNoDpth2}', '2', '${param.ctgrySectCd}', '${param.ctgryNoDpth1}', '${param.ctgryNoDpth2}');" class="lk"><em class="t">${dpth2Nm}</em> </a></li>
									<li><a href="javascript:cateLink('${param.ctgryNoDpth1}', '1', '${param.ctgrySectCd}', '${param.ctgryNoDpth1}', '${param.ctgryNoDpth1}');" class="lk"><em class="t"><spring:message code="display.category.text.all" /></em> </a></li>
									<c:forEach items="${dpth2}" var="dpth" varStatus="i">
										<c:if test="${param.ctgryNoDpth2 ne dpth.dspCtgryNo}">
											<li><a href="javascript:cateLink('${dpth.dspCtgryNo}', '2', '${dpth.ctgrySectCd}', '${dpth.upperDspCtgryNo}', '${dpth.dspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</li>
					<c:if test="${fn:length(dpth3) > 0}">
						<li>
							<span class="txt">
								<em class="t">
									<c:choose>
										<c:when test="${!empty dpth3Nm}">
											${dpth3Nm}
										</c:when>
										<c:otherwise>
											<spring:message code="display.category.text.all" />
										</c:otherwise>
									</c:choose>
									</em><a href="javascript:;" class="bt">하위메뉴열기</a></span>
							<div class="subs">
								<ul class="list">
									<c:choose>
										<c:when test="${dspCtgryScFrDTO.currentCtgryDpthCd eq 1}">
											<li class="on"><a href="javascript:cateLink('${param.ctgryNoDpth1}', '1', '${param.ctgrySectCd}', '${param.ctgryNoDpth1}', '${param.ctgryNoDpth1}');" class="lk"><em class="t"><spring:message code="display.category.text.all" /></em> </a></li>
											<c:forEach items="${dpth2}" var="dpth">
												<c:if test="${dpth.dspCtgryNo ne dspCtgryScFrDTO.dspCtgryNo}">
													<li><a href="javascript:cateLink('${dpth.dspCtgryNo}', '2', '${dpth.ctgrySectCd}', '${dpth1[0].dspCtgryNo}', '${dpth.dspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em> </a></li>
												</c:if>
											</c:forEach>
										</c:when>
										<c:when test="${dspCtgryScFrDTO.currentCtgryDpthCd eq 2}">
											<li class="on"><a href="javascript:cateLink('${dspCtgryScFrDTO.ctgryNoDpth2}', '2', '${dspCtgryScFrDTO.ctgrySectCd}', '${dspCtgryScFrDTO.ctgryNoDpth1}', '${dspCtgryScFrDTO.ctgryNoDpth2}');" class="lk"><em class="t"><spring:message code="display.category.text.all" /></em></a></li>
											<c:forEach items="${dpth3}" var="dpth">
												<c:if test="${dpth.dspCtgryNo ne dspCtgryScFrDTO.dspCtgryNo}">
													<li><a href="javascript:cateLink('${dpth.dspCtgryNo}', '3', '${dpth.ctgrySectCd}', '${dpth1[0].dspCtgryNo}', '${dpth.upperDspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em> </a></li>
												</c:if>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<c:forEach items="${dpth3}" var="dpth">
												<c:if test="${dpth.dspCtgryNo eq dspCtgryScFrDTO.dspCtgryNo}">
													<li class="on"><a href="javascript:cateLink('${dpth.dspCtgryNo}', '3', '${dpth.ctgrySectCd}', '${dpth1[0].dspCtgryNo}', '${dpth.upperDspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em> </a></li>
												</c:if>
											</c:forEach>
											<li><a href="javascript:cateLink('${dspCtgryScFrDTO.ctgryNoDpth2}', '2', '${dspCtgryScFrDTO.ctgrySectCd}', '${dspCtgryScFrDTO.ctgryNoDpth1}', '${dspCtgryScFrDTO.ctgryNoDpth2}');" class="lk"><em class="t"><spring:message code="display.category.text.all" /></em></a></li>
											<c:forEach items="${dpth3}" var="dpth">
												<c:if test="${dpth.dspCtgryNo ne dspCtgryScFrDTO.dspCtgryNo}">
													<li><a href="javascript:cateLink('${dpth.dspCtgryNo}', '3', '${dpth.ctgrySectCd}', '${dpth1[0].dspCtgryNo}', '${dpth.upperDspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em> </a></li>
												</c:if>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</ul>
							</div>
						</li>
					</c:if>
				</ul>
			</div>
			
			<c:if test="${!empty ctgryContt}">
				<!-- Visual start -->
				<section class="mds-section visual d2" id="dpVisual">
					<div class="swiper-container">
						<ul class="swiper-wrapper">
						
							<c:forEach items="${ctgryContt}" var="contt">
							
								<c:if test="${contt.conttTpCd eq 'IMG_BANNER'}">
								
									<li class="swiper-slide">
										<a href="${contt.conttCnncUrl}"><img class="vs-img" src="${_image}${contt.imgFileUrl}/${contt.imgFileNm}/dims/resize/1920x560" alt="${contt.imgAltrtvCont}" ></a>
									</li>
								
								</c:if>
							
							</c:forEach>
							
						</ul>
					</div>
					<div class="pagination" <c:if test="${fn:length(ctgryContt) eq 1}"> style="display:none;" </c:if>></div>
					<div class="navigation">
						<button type="button" class="btnNav prev" <c:if test="${fn:length(ctgryContt) eq 1}"> style="display:none;" </c:if>>이전</button>
						<button type="button" class="btnNav next" <c:if test="${fn:length(ctgryContt) eq 1}"> style="display:none;" </c:if>>다음</button>
					</div>
					
					<script>
					$( document ).ready(function() {
						dpVisual = new Swiper('#dpVisual .swiper-container', {
							slidesPerView: 1,
							observer: true,
							observeParents: true,
							watchOverflow:true,
							pagination: {
								el: '#dpVisual .pagination',
								clickable: true
							},
							navigation: {
								nextEl: '#dpVisual .navigation .btnNav.next',
								prevEl: '#dpVisual .navigation .btnNav.prev'
							},
							autoplay: ${fn:length(ctgryContt) eq 1}
						  			? false
									: {
										delay: 10000,
										disableOnInteraction: false
									},
							preloadImages: false,
						    // Enable lazy loading
						    lazy: true,
							loop: ${fn:length(ctgryContt) ne 1}
						});
					});
					</script>
				</section>
				<!-- Visual end -->
			</c:if>
			
			<section class="mds-section dp_list_sec">
		 						<div class="dp_filter_sort">
								<div class="sort">
									<ul class="list d_tab">
									
										<ncp:codes group="DSP_GOD_SORT_STDR" var="sortStr" />
				                         <c:forEach items="${sortStr }" var="varSort">
				                              <c:if test="${varSort.cd eq 'SALE_QTY_SEQ'}">
				                                 <li <c:if test="${sortColumn eq varSort.cd || sortColumn eq null || sortColumn eq ''}"> class="on" </c:if> ><a href="javascript:void(0);" id="${varSort.cd }">${varSort.cdNm }</a></li>
				                             </c:if>
				                             <c:if test="${varSort.cd ne 'SALE_QTY_SEQ'}">
				                                 <li <c:if test="${sortColumn eq varSort.cd}"> class="on" </c:if>  ><a href="javascript:void(0);" id="${varSort.cd }">${varSort.cdNm }</a></li>
				                             </c:if>
				                         </c:forEach>
										
									</ul>
								</div>
								<div class="filter">
								
									<%@ include file="/WEB-INF/views/display/include/display.filter.jsp"%>
		
									<div class="pg_num select-style01 d_select">
										<c:choose>
											<c:when test="${_locale eq 'en'}">
												<button type="button" class="d_select_sel" style="width: 152px;"><span><spring:message code="display.main.text17" /> ${dspCtgryScFrDTO.mallPageSize}</span></button>
											</c:when>
											<c:otherwise>
												<button type="button" class="d_select_sel" style="width: 152px;"><span>${dspCtgryScFrDTO.mallPageSize}<spring:message code="display.main.text17" /></span></button>
											</c:otherwise>
										</c:choose>
										<ul>
											<li><a href="javascript:void(0);" class="selectPageSize"><spring:message code="display.main.text2" /><input type="hidden" value="36"></a></li>
											<li><a href="javascript:void(0);" class="selectPageSize"><spring:message code="display.main.text3" /><input type="hidden" value="72"></a></li>
											<li><a href="javascript:void(0);" class="selectPageSize"><spring:message code="display.main.text4" /><input type="hidden" value="96"></a></li>
										</ul>
									</div>
		
								</div>
		
							</div>	
				 <div class="ui_dp_list">
				 	
				 	<c:choose>
				 		
				 		<c:when test="${empty godList}">
						
							<div class="nodata">
								<p class="msg"><spring:message code="display.main.text23" /></p>
							</div>
					
										 		
				 		</c:when>
				 		<c:otherwise>
				 			
				 			<ul class="list">
								<c:forEach items="${godList}" var="god" varStatus="i">
								<input type="hidden" class="criteoGod" value="${god.god.erpGodNo }"/>
									<li>
										<div class="item">
											<div class="thumb">
												<a href="${god.godUrl}">
													<c:if test="${god.god.godSaleSectCd eq 'SLDOUT'}">
														<em class="sold_out">SOLD OUT</em>
													</c:if>
													<c:if test="${god.godBadgeYn eq 'Y'}">
														<%-- S : 2020-03-20 Family flag add --%>
	                                                    <span class="flag_family">Family
	                                                        <span class="flag_family-info">패밀리 룩(성인&키즈) <br>연출 가능한 상품입니다.</span>
	                                                    </span>
	                                                    <%-- E : 2020-03-20 Family flag add --%>
                                                    </c:if>
													<span class="img">
														<img src="${_image}${god.imgFrontUrl}/dims/resize/414x414" alt="${god.god.godNm}" onerror='errorImgShow(this, "414");'<c:if test="${!empty god.detailImgFrontUrl }"> class="first" </c:if> >
														<c:if test="${!empty god.detailImgFrontUrl }">
															<img src="${_image}${god.detailImgFrontUrl}/dims/resize/414x414" alt="${god.god.godNm}" onerror='errorImgShow(this, "414");' class="second">
														</c:if>
													</span>
												</a>
											</div>
											<div class="info">
												<div class="name">
													<c:if test="${_locale eq 'ko'}">
														<c:if test="${!empty god.tagNm}">
															<span style="color:${god.colorTagNm}">${god.tagNm}</span>
														</c:if>
													</c:if>
													<c:if test="${god.brndId eq 'I'}">
														<span>[KIDS]</span>
													</c:if>
													${god.god.godNm}
												</div>
												<div class="prc">
													<c:choose>
														<c:when test="${god.dspGodPrc.csmrPrc lt god.dspGodPrc.rtlPrc}">
															<s class="s"><fmt:formatNumber value="${god.dspGodPrc.rtlPrc}" type="number"/><spring:message code="display.main.text1" /></s> 
															<em class="p"><fmt:formatNumber value="${god.dspGodPrc.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>		
														</c:when>
														<c:otherwise>
															<em class="p"><fmt:formatNumber value="${god.dspGodPrc.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
														</c:otherwise>
													</c:choose>
												 </div>
												 <c:if test="${god.god.godSaleSectCd eq'SMTM_SLDOUT'}">
														<div class="solds"><spring:message code="display.god.commingsoon" /></div>
												 </c:if>
											</div>
										</div>
									</li>
								</c:forEach>
							</ul>
				 			
				 		</c:otherwise>
				 		
				 	</c:choose>
				 		
				</div>
				<%@ include file="/WEB-INF/views/display/include/display.page.jsp"%>
		</section>
		</main>
	</div>
</div>
<!--// 컨텐츠 끝 -->

<form method="get" id="ctgrySearchForm" action="/display/view" style="display:none;">
	<input type="hidden" name="dspCtgryNo" 		 	value="${dspCtgryScFrDTO.dspCtgryNo}" />
	<input type="hidden" name="currentCtgryDpthCd" 	value="${dspCtgryScFrDTO.currentCtgryDpthCd}" />
	<input type="hidden" name="ctgrySectCd" 		value="${dspCtgryScFrDTO.ctgrySectCd}" />
	<input type="hidden" name="ctgryNoDpth1" 		value="${dspCtgryScFrDTO.ctgryNoDpth1}" />
	<c:if test="${!empty dspCtgryScFrDTO.ctgryNoDpth2}">
		<input type="hidden" name="ctgryNoDpth2" 		 value="${dspCtgryScFrDTO.ctgryNoDpth2}" />
	</c:if>
	<c:if test="${!empty dspCtgryScFrDTO.ctgryNoDpth3}">
		<input type="hidden" name="ctgryNoDpth3" 		 value="${dspCtgryScFrDTO.ctgryNoDpth3}" />
	</c:if>
	<c:if test="${!empty dspCtgryScFrDTO.mallPageSize}">
		<input type="hidden" name="mallPageSize" 		 value="${dspCtgryScFrDTO.mallPageSize}" />
	</c:if>
	<c:if test="${!empty dspCtgryScFrDTO.sortColumn}">
		<input type="hidden" name="sortColumn" 		 	 value="${dspCtgryScFrDTO.sortColumn}" />
	</c:if>
	<c:if test="${!empty dspCtgryScFrDTO.prcStart}">
		<input type="hidden" name="prcStart" 		 	 value="${dspCtgryScFrDTO.prcStart}" />
	</c:if>
	<c:if test="${!empty dspCtgryScFrDTO.prcEnd}">
		<input type="hidden" name="prcEnd" 		 	 	 value="${dspCtgryScFrDTO.prcEnd}" />
	</c:if>
	<c:forEach items="${searchTeamCd }" var="searchTeamCd" >
		<input type="hidden" name="searchConditionTeamCds" value="${searchTeamCd}" />
	</c:forEach>
	<c:forEach items="${searchColor }" var="searchColor" >
		<input type="hidden" name="searchConditionColors" value="${searchColor}" />
	</c:forEach>
	<c:forEach items="${searchSize }" var="searchSize" >
		<input type="hidden" name="searchConditionSizes" value="${searchSize}" />
	</c:forEach>				
</form>

<script>
	$(document).ready(function(){
		$(".list.cate li a").each(function(idx){
			if($(this).attr("href").indexOf("${dspCtgryScFrDTO.ctgryNoDpth1}") > 0){
				$(this).parent().addClass("on");
			}
		});
	});
	
	//페이지 사이즈, 정렬 변경
	$(".d_select, .sort").on("click", "li a", function(){

		var paramName = $(this).closest("div.sort").length > 0 ? "sortColumn" : "mallPageSize";
		var paramValue = paramName === "mallPageSize" ? $(this).children("input").val() : this.id;
		var $ctgrySearchForm = $("#ctgrySearchForm");

		if($ctgrySearchForm.children("[name=" + paramName + "]").length > 0) {
			
			$ctgrySearchForm.children("[name=" + paramName + "]").val(paramValue);
			
		} else {
			
			$ctgrySearchForm.append("<input name=" + paramName + " value=" + paramValue + " />");
			
		}
		
		$ctgrySearchForm.submit();
		
	});
	
	//페이징
	function getPage(pageNo) {
		
		var $ctgrySearchForm = $("#ctgrySearchForm");

			
		$ctgrySearchForm.append("<input name=\"pageNo\" value=" + pageNo + " />");
			
		
		$ctgrySearchForm.submit();
		
	}
	
</script>
