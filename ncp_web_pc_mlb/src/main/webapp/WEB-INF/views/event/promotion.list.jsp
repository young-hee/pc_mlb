<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<!-- 컨텐츠 시작 -->
	<div class="contain dp promo" id="contain">
		<div class="container">

			<main class="contents" id="contents">


				<div class="pm_top">
					<span class="tit">PROMOTION</span>
				</div>
				
				<c:if test="${!empty contt.cnrBnrConttList}">
					
					<!-- Visual  -->
					<section class="mds-section visual pm" id="pmVisual">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
							
								<c:forEach items="${contt.cnrBnrConttList}" var="visual" varStatus="status">
									<li class="swiper-slide">
										<a href="${visual.conttCnncUrl }">
											<img class="vs-img" src="${_image}${visual.imgFileUrl}/${visual.imgFileNm}/dims/resize/1920x560" alt="${visual.imgAltrtvCont}">
										</a>
									</li>
								</c:forEach>
								
							</ul>
						</div>
						<div class="pagination" <c:if test="${fn:length(contt.cnrBnrConttList) eq 1}"> style="display:none;" </c:if> ></div>
						<div class="navigation">
							<button type="button" class="btnNav prev" <c:if test="${fn:length(contt.cnrBnrConttList) eq 1}"> style="display:none;" </c:if> >이전</button>
							<button type="button" class="btnNav next" <c:if test="${fn:length(contt.cnrBnrConttList) eq 1}"> style="display:none;" </c:if> >다음</button>
						</div>
					</section>
				
				</c:if>

				<c:if test="${!empty contt.cnrConttList and fn:length(contt.cnrConttList) ge 2}">
					
					<section class="pm_banner">
						<ul class="list">
						
							<c:forEach items="${contt.cnrConttList}" var="banner" varStatus="status">
								
								<c:if test="${status.count le 2}">
									<li>
										<a href="${banner.conttCnncUrl}">
											<img src="${_image}${banner.imgFileUrl}/${banner.imgFileNm}/dims/resize/590x395" alt="${banner.imgAltrtvCont}">
										</a>
									</li>								
								</c:if>
							
							</c:forEach>
							
						</ul>
					</section>
					
				</c:if>
				
				<c:if test="${!empty evt}">
				
					<section class="mds-section pm_list_sec">
						<!-- 기획,이벤 탭 -->
						<div class="pm_filter_sort">
							<div class="sort">
								<ul class="list d_tab">
									<li <c:if test="${dspPromtScFrDTO.searchCategory eq 'ALL'}"> class="on" </c:if> data-li="ALL" >
										<a href="javascript:void(0);">ALL</a>
									</li>
									<li <c:if test="${dspPromtScFrDTO.searchCategory eq 'EVENT'}"> class="on" </c:if> data-li="EVENT" >
										<a href="javascript:void(0);">EVENT</a>
									</li>
									<li <c:if test="${dspPromtScFrDTO.searchCategory eq 'PLAN'}"> class="on" </c:if> data-li="PLAN" >
										<a href="javascript:void(0);">PROMOTION</a>
									</li>
								</ul>
							</div>
							<div class="bts">
								<a href="/helpdesk/notice/list?searchNoticeCd=EVT_PRIZE" class="btn fill sm btnWinner"><spring:message code="display.event.manager.notice" /></a>
							</div>
						</div>	
	
	
						<div class="ui_pm_list">
							<ul class="list">
								
								<c:forEach items="${evt}" var="evt" varStatus="status">
									<c:if test="${!empty evt.evtNm}">
										
										<li>
											<div class="item">
												<div class="thumb">
													<a href="${evt.pcUrl}">
														
														<c:choose>
															<c:when test="${evt.evtTycd eq 'PROMT'}">
																<em class="bd">PROMOTION</em>	
															</c:when>
															<c:otherwise>
																<em class="bd">EVENT</em>
															</c:otherwise>
														</c:choose>
														
														<span class="img">
															<img src="${_image}${evt.pcImgFileUrl}/dims/resize/387x387" alt="${evt.pcImgAltrtvCont}" onerror='errorImgShow(this, "387");' >
														</span>
														
													</a>
												</div>
												<div class="info">
													<div class="name">${evt.evtNm}</div>
												</div>
											</div>
										</li>
										
									</c:if>
									
								</c:forEach>
								
							</ul>
						</div>
	
					</section>
				
				</c:if> 
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->
	
<form method="get" id="ctgrySearchForm" action="/event/promotionList" style="display:none;">
	<input type="hidden" name="searchCategory" value="${dspPromtScFrDTO.searchCategory}" />
</form>

<script type="text/javascript">

	$(document).ready(function(){
		
		var totalRowCnt = ${totalRow} || 0;
		var currentPage = ${currentPage} || 1;
		var currentAjax = {};
		
		<!-- Visual  -->
		pmVisual = new Swiper('#pmVisual .swiper-container', {
			slidesPerView: 1,
			observer: true,
			observeParents: true,
			watchOverflow:true,
			pagination: {
				el: '#pmVisual .pagination',
				clickable: true
			},
			navigation: {
				nextEl: '#pmVisual .navigation .btnNav.next',
				prevEl: '#pmVisual .navigation .btnNav.prev'
			},
			autoplay: ${fn:length(contt.cnrBnrConttList) eq 1}
  					? false
					: {
						delay: 10000,
						disableOnInteraction: false
					},
			preloadImages: false,
		    // Enable lazy loading
		    lazy: true,
			loop: ${fn:length(contt.cnrBnrConttList) ne 1}
		});
		
		<!-- 기획전 이벤트별 조회 -->
		$(".sort").on("click", "li", function(e) {
			
			var searchCategory = $(this).data("li");
			
			$("#ctgrySearchForm").children().val(searchCategory);
			$("#ctgrySearchForm").submit();
			
		});
		
		$("#wrap").on("scroll load", function() {
			var sct = $("#wrap").scrollTop() + $("#wrap").height() - $(".foot").height();
			var cnt = $(".contain").outerHeight();
			var currentRowCnt = $(".ui_pm_list").children(".list").children().length;

			if (cnt <= sct && currentRowCnt < totalRowCnt ) {
				
				$.ajax({
					 type:"post"
					,url:"/event/promotionList.json"
					,data : {
						  pageNo : currentPage + 1
						, searchCategory : "${dspPromtScFrDTO.searchCategory}"
					}
					,dataType: "json"
					,async : true
					,beforeSend : function(request) {
						
						if(currentAjax.hasOwnProperty(this.url)) {
							request.abort();
						}

						currentAjax[this.url] = request;
						
					}
			        ,success: function(result){
						
			        	var evtArr = result.evt;
			        	var imgUrl = "${_image}";
			        	var innerHtml = "";

			        	if(evtArr && evtArr.length) {

			        		evtArr.forEach(function(evt, index) {

			        			if(evt.evtNm) {
			        				
			        				evt.pcUrl 			= evt.pcUrl || "";
			        				evt.pcImgAltrtvCont = evt.pcImgAltrtvCont || "";
			        				
			        				innerHtml +=	"<li>";
					        		innerHtml += 		"<div class=\"item\">";
					        		innerHtml +=			"<div class=\"thumb\">";
					        		innerHtml +=				"<a href=" + evt.pcUrl + ">";
									
					        		if(evt.evtTycd === "PROMT") {
					        			
					        			innerHtml +=					"<em class=\"bd\">PROMOTION</em>";
					        			
					        		} else {
					        			
					        			innerHtml +=					"<em class=\"bd\">EVENT</em>";
					        			
					        		}
					        		
					        		innerHtml +=					"<span class=\"img\">";
					        		innerHtml +=						"<img src=" + imgUrl + evt.pcImgFileUrl + "/dims/resize/387x387" + "  alt=\"" + evt.pcImgAltrtvCont + "\" onerror=\"errorImgShow(this, '387');\" >";
					        		innerHtml +=					"</span>";
					        		innerHtml +=				"</a>";
					        		innerHtml +=			"</div>";
					        		innerHtml +=			"<div class=\"info\">";
					        		innerHtml +=				"<div class=\"name\">" + evt.evtNm + "</div>";
					        		innerHtml +=			"</div>";
					        		innerHtml +=		"</div>";
					        		innerHtml +=	"</li>";
			        				
			        			}
			        			
			        		});
			        		
			        	}
			        	
			        	$(".ui_pm_list").children(".list").append(innerHtml);
				       	currentPage = result.currentPage;
			        	delete currentAjax[this.url];	
			        
			        },
			        error: function() {
						
			        	if(currentAjax.hasOwnProperty(this.url)) {
				        	delete currentAjax[this.url];
						}
			        	
			        }
			   });

			}
		});
		
	});
	
</script>