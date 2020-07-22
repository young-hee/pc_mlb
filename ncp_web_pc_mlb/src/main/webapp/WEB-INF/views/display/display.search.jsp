<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!-- 컨텐츠 시작 -->
<div class="contain dp list search" id="contain">
	<div class="container">
		<main class="contents" id="contents">
			<!-- 카테고리 메뉴 -->
			<c:choose>
				<c:when test="${!empty godList}">
					<div class="dp_sch_box">
						<div class="in">
							<form class="box" action="/static/html/dp/search.jsp">
								<span class="keyword"><input type="text" class="key" value="${dspCtgryScFrDTO.searchText}"></span>
								<button type="button" class="btnSch"><spring:message code='display.main.text9' /></button>
							</form>
						</div>
					</div>
					<div class="result_total"><em class="t"><spring:message code='display.main.text12' /> TOTAL</em> <em class="n">(${totalRow})</em></div>
				
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
					</div>
					<%@ include file="/WEB-INF/views/display/include/display.page.jsp"%>
					</section>
				</c:when>
				<c:otherwise>
					<script>
						$(document).ready(function() {
							$("#recomPdListBox").personalized({channel:'search'});
						});
					</script>
					<div class="dp_sch_box">
						<div class="in">
							<form class="box" action="/static/html/dp/search.jsp">
								<span class="keyword"><input type="text" class="key" value="${dspCtgryScFrDTO.searchText}"></span>
								<button type="button" class="btnSch"><spring:message code='display.main.text9' /></button>
							</form>
							<c:if test="${empty godList}">
								<div class="result_msg">
									<p><spring:message code='display.main.text13' arguments="<em class='t'>${dspCtgryScFrDTO.searchText}</em>"/></p>
								</div>
							</c:if>
						</div>
					</div>
					<section class="mds-section recom">
						<div class="hdt"><span class="tit"><spring:message code='display.search.text.reco' /></span></div>
						<!--  추천 상품 공통 -->
						<div class="recomPdListBoxWrap">
							<div id="recomPdListBox" class="recomPdListBox swiper-container">
						     
							</div>
						</div>
					</section>
				</c:otherwise>
			</c:choose>
		</main>
	</div>
</div>
<!--// 컨텐츠 끝 -->

<form method="get" id="ctgrySearchForm" action="/display/search" style="display:none;">
	<input type="hidden" name="searchText"			value="${dspCtgryScFrDTO.searchText}" />
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
	<c:if test="${!empty dspCtgryScFrDTO.searchConditionBrandId}">
		<input type="hidden" name="searchConditionBrandId"  value="${dspCtgryScFrDTO.searchConditionBrandId}" />
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
	
	//검색어변경
	$(".dp_sch_box").on("click", ".btnSch", ctgrySearchTextFunc)
					.on("keydown", ".key", function (e){

						if(e.keyCode === 13) {

							ctgrySearchTextFunc();
							e.preventDefault();
						
						}
						
					});
		
	
	function ctgrySearchTextFunc() {
		
		var $ctgrySearchForm = $("#ctgrySearchForm");
		var $searchInput = $(".dp_sch_box").find(".key");
		var searchText = $searchInput.val();
		var altMsg = "<spring:message code='display.search.result.alert' />";
		
		if(!$.trim(searchText).length) {
			
			alert(altMsg);
			$searchInput.focus();
			return false;
			
		}
		
		$ctgrySearchForm.children("input[name='searchText']").siblings().remove();
		$ctgrySearchForm.children("input[name='searchText']").val(searchText);
		
		$ctgrySearchForm.submit();
		
	}
	
	//페이징
	function getPage(pageNo) {
		
		var $ctgrySearchForm = $("#ctgrySearchForm");

			
		$ctgrySearchForm.append("<input name=\"pageNo\" value=" + pageNo + " />");
			
		
		$ctgrySearchForm.submit();
		
	}
	
	
</script>
