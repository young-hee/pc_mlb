
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
	<!-- 컨텐츠 시작 -->
	<div class="contain dp list dep1" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				
				<div class="dp_cate_top">
				<ul class="menu">
					<li><span class="txt"><em class="t">${dpth1Nm}</em></span></li>
					<li>
						<span class="txt"><em class="t"><spring:message code="display.category.text.all" /></em><a href="javascript:;" class="bt">하위메뉴열기</a></span>
						<div class="subs">
							<ul class="list">
								<li class="on"><a href="javascript:cateLink('${param.dspCtgryNo}', '1', '${param.ctgrySectCd}', '${param.ctgryNoDpth1}');" class="lk"><em class="t"><spring:message code="display.category.text.all" /></em> </a></li>
								<c:forEach items="${dpth2}" var="dpth" varStatus="i">
									<li><a href="javascript:cateLink('${dpth.dspCtgryNo}', '2', '${dpth.ctgrySectCd}', '${dpth.upperDspCtgryNo}', '${dpth.dspCtgryNo}');" class="lk"><em class="t">${dpth.dspCtgryNm}</em> </a></li>
								</c:forEach>
							</ul>
						</div>
					</li>
				</ul>
			</div>
				
				<c:forEach items="${ctgryTmplat}" var="tmplat" varStatus="ctgryStatus">
				
					<c:if test="${((env eq 'local' or env eq 'dev') and tmplat.key eq '127' and !empty tmplat.value)
									or ((env ne 'local' and env ne 'dev') and tmplat.key eq '10127' and !empty tmplat.value)}">
						
						<!-- Visual  -->
						<c:set value="${tmplat.value}" var="visualContt"/>
					
						<section class="mds-section visual d1" id="dpVisual">
							<div class="swiper-container">
								<ul class="swiper-wrapper">
								
									<c:forEach items="${visualContt}" var="visual">
										
										<li class="swiper-slide">
											<a href="${visual.conttCnncUrl}"><img class="vs-img" src="${_image}${visual.imgFileUrl}/${visual.imgFileNm}/dims/resize/1920x850" alt="${visual.imgAltrtvCont}"  ></a>
										</li>
									
									</c:forEach>

								</ul>
							</div>
							<div class="pagination" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>></div>
							<div class="navigation">
								<button type="button" class="btnNav prev" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>>이전</button>
								<button type="button" class="btnNav next" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>>다음</button>
							</div>
							
						</section>
					
					</c:if>
					<c:if test="${((env eq 'local' or env eq 'dev') and tmplat.key eq '128' and !empty tmplat.value  and fn:length(tmplat.value) ge 5)
									or ((env ne 'local' and env ne 'dev') and tmplat.key eq '10128' and !empty tmplat.value and fn:length(tmplat.value) ge 5)}">
						
						<!-- Best Item  -->
						<c:set value="${tmplat.value}" var="bestContt"/>
					
						<section class="mds-section best">
	
							<div class="hdt"><span class="tit">BEST ITEM</span></div>
						
							<div id="dpBestSlide" class="slide">
								<div class="swiper-container">
									<ul class="swiper-wrapper list">
									
										<c:forEach items="${bestContt}" var="best" varStatus="status">
										
											<c:if test="${best.conttTpCd eq 'GOD'}">
												
												<li class="swiper-slide">
													<div class="item">
														<div class="thumb">
															<%-- <c:if test="${best.godBadgeYn eq 'Y'}">
																S : 2020-03-20 Family flag add
			                                                    <span class="flag_family">Family
			                                                        <span class="flag_family-info">패밀리 룩(성인&키즈) <br>연출 가능한 상품입니다.</span>
			                                                    </span>
			                                                    E : 2020-03-20 Family flag add
		                                                    </c:if> --%>
															<a href="${best.godUrl}">
																<span class="img">
																	<img src="${_image}${best.imgFrontUrl}/dims/resize/315x315" alt="${best.godNm}" onerror='errorImgShow(this, "315");'<c:if test="${!empty best.detailImgFrontUrl }"> class="first" </c:if>>
																	<c:if test="${!empty best.detailImgFrontUrl }">
																		<img src="${_image}${best.detailImgFrontUrl}/dims/resize/384x384" alt="${best.godNm}" onerror='errorImgShow(this, "384");' class="second">
																	</c:if>
																</span>
															</a>
														</div>
														<div class="info">
															<div class="name">
																<c:if test="${best.brndId eq 'I'}">
																	<span>[KIDS]</span>
																</c:if>
																${best.godNm}
															</div>
															<div class="prc">
																<c:choose>
																	<c:when test="${best.csmrPrc lt best.rtlPrc}">
																		<em class="p"><fmt:formatNumber value="${best.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>		
																	</c:when>
																	<c:otherwise>
																		<em class="p"><fmt:formatNumber value="${best.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</div>
												</li>
											
											</c:if>
										
										</c:forEach>
										
									</ul>
								</div>
								<div class="navigation">
									<button type="button" class="btnNav prev" <c:if test="${fn:length(bestContt) eq 5}"> style="display:none;" </c:if>>이전</button>
									<button type="button" class="btnNav next" <c:if test="${fn:length(bestContt) eq 5}"> style="display:none;" </c:if>>다음</button>
								</div>
							</div>
						
						</section>
					</c:if>
				</c:forEach>
				
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
														<img src="${_image}${god.imgFrontUrl}/dims/resize/414x414" alt="${god.god.godNm}" onerror='errorImgShow(this, "414");'<c:if test="${!empty god.detailImgFrontUrl }"> class="first" </c:if>>
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
<form method="get" id="ctgrySearchForm" action="/display/majorView" style="display:none;">
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
	
		$( document ).ready(function() {
			if(${fn:length(ctgryTmplat['127']) gt 1 or fn:length(ctgryTmplat['10127']) gt 1}) {
				
				<!-- Visual  -->
				dpVisual = new Swiper('#dpVisual .swiper-container', {
					slidesPerView: 1,
					observer: true,
					observeParents: true,
					pagination: {
						el: '#dpVisual .pagination',
						clickable: true
					},
					navigation: {
						nextEl: '#dpVisual .navigation .btnNav.next',
						prevEl: '#dpVisual .navigation .btnNav.prev'
					},
					autoplay:  {
								delay: 10000,
								disableOnInteraction: false
							},
					preloadImages: false,
				    // Enable lazy loading
				    lazy: true,
					loop: true
				});
				
			}
			
			<!-- Best Item  -->
			dpBestSlide = new Swiper('#dpBestSlide .swiper-container', {
				slidesPerView: 5,
				observer: true,
				observeParents: true,
				// centeredSlides:true,
				spaceBetween:30,
				watchOverflow:true,
				navigation: {
					nextEl: '#dpBestSlide .navigation .btnNav.next',
					prevEl: '#dpBestSlide .navigation .btnNav.prev'
				},
				preloadImages: false,
			    lazy: true,
				loop: true,
				breakpoints: {
					1280: {
						slidesPerView: 3,
						spaceBetween:20
					}
				}
			});
			
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

<%--
<c:if test="${dspCtgryScFrDTO.dspCtgryNo eq 'MBMA05'}">
<div class="tempEventPop">
	<div class="inner">
		<div class="blind">
			<h3>심려를 끼쳐드려 죄송합니다.</h3>
			<p>최근 논란이 된 불미스러운 일에 대해 불편함을 느끼셨을 MLB키즈 고객님들 그리고 네티즌 분들께 심려를 끼쳐드려 진심으로 사과드리며 해당사건의 경위와 사후 조치를 설명드립니다.</p>
		</div>
		<a href="https://www.mlb-korea.com/helpdesk/notice/view/86?searchNoticeCd=&searchNotice="><img src="https://static.mlb-korea.com/images/display/content/tmp/2019_pop_apology2/pc.png?v=2" alt="" /></a>
		<button class="notoday" type="button" onclick="ClosetempEventPop()">오늘 그만 보기</button>
	</div>
</div>
<style>
.tempEventPop{
	display:none;
	position:fixed;
	/* top:50%;left:50%;
	transform:translate(-50%, -50%); */
	top: 78px;
    left: 28px;
	z-index:999;	
}
.tempEventPop .inner{
	position:relative;
}
.tempEventPop .notoday{
    position: absolute;
    top: 0;
    right: 0;
    width: 15%;
    height: 0;
    padding: 15% 0 0;
    background: rgba(0, 0, 0, 0);
    text-indent: -9999px;
    overflow: hidden;
}
</style>
<script type="text/javascript">
	function ClosetempEventPop(){
		setCookie2( "tempEventPop", "true", 300 );
		$('.tempEventPop').fadeOut('slow');
	}
	$(function(){
		var cookie = getCookie2("tempEventPop");
		if("" == cookie) {
			$('.tempEventPop').fadeIn(function(){
				setCookie2( "tempEventPop", "true", 300 );
			});
		}
	})	
</script>
</c:if>
 --%>