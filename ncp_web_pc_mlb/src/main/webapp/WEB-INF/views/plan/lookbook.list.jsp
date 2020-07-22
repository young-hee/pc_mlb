<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
	<!-- 컨텐츠 시작 -->
	<!-- 컨텐츠 시작 -->
	<div class="contain dp look" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<div class="look_hd"><span class="tit">LOOKBOOK</span></div>
				<div class="mds_look_main">

					<nav class="look_lnb">
						<ul class="menu">
							
							<c:set var="upperCategory" value="" />
							<c:set var="fLocation" value="" />
							<c:set var="dspCtgryNo" value="" />
							<c:set var="ctgryDpthCd" value="" />
						
							<c:forEach items="${lookbook.mallDspFoCtgryResults}" var="look" varStatus="status">
							
								<c:if test="${status.index eq 0 and empty param.dspCtgryNo}">
									<c:set var="upperCategory" value="MBMA07" />
									<c:set var="fLocation" value="${look.dspCtgryNm}" />
									<c:set var="dspCtgryNo" value="${look.dspCtgryNo}" />
									<c:set var="ctgryDpthCd" value="2" />
								</c:if>
								<c:if test="${!empty param.dspCtgryNo and param.dspCtgryNo eq look.dspCtgryNo}">
									<c:set var="upperCategory" value="MBMA07" />
									<c:set var="fLocation" value="${look.dspCtgryNm}" />
									<c:set var="ctgryDpthCd" value="2" />
								</c:if>
								
								<li>
									<a href="javascript:lookbookLink('MBMA07', '${look.dspCtgryNm}', '${look.dspCtgryNo}', '2');">${look.dspCtgryNm}</a>
									<ul>
									
										<c:forEach items="${look.mallDspFoCtgryResults}" var="subLook">
										
											<c:if test="${!empty param.dspCtgryNo and param.dspCtgryNo eq subLook.dspCtgryNo}">
												<c:set var="upperCategory" value="${look.dspCtgryNo}" />
												<c:set var="fLocation" value="${look.dspCtgryNm}" />
												<c:set var="ctgryDpthCd" value="3" />
											</c:if>
																						
											<li>
												<a href="javascript:lookbookLink('${look.dspCtgryNo}', '${look.dspCtgryNm}', '${subLook.dspCtgryNo}', '3');">
													<span>${subLook.dspCtgryNm}</span>
												</a>
											</li>
											
										</c:forEach>
										
									</ul>
								</li>
							
							</c:forEach>
							
						</ul> 
					</nav>
					
					<c:choose>
					
						<c:when test="${empty param.dspCtgryNo}">
							<c:import url="/lookbook/lookbookConttList">
								<c:param name="upperCategory" value="${upperCategory}"/>
								<c:param name="fLocation" value="${fLocation}"/>
								<c:param name="dspCtgryNo" value="${dspCtgryNo}"/>
								<c:param name="ctgryDpthCd" value="${ctgryDpthCd}"/>
							</c:import>
						</c:when>
						<c:otherwise>
							<c:import url="/lookbook/lookbookConttList">
								<c:param name="upperCategory" value="${upperCategory}"/>
								<c:param name="fLocation" value="${fLocation}"/>
								<c:param name="ctgryDpthCd" value="${ctgryDpthCd}"/>
							</c:import>
						</c:otherwise>
						
					</c:choose>
					

				</div>

			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->
	
	<script type="text/javascript">
		
		var currentAjax = {};
	
		//룩북 카테고리 이동
		function lookbookLink( upperCategory, fLocation, dspCtgryNo, ctgryDpthCd ) {
			
			$.ajax({
	 			type : "POST",
	 			async : true,
	 			url : '<c:url value="/lookbook/lookbookConttList"/>',
	 			beforeSend : function(request) {
					
					if(currentAjax.hasOwnProperty(this.url)) {
						request.abort();
					}

					currentAjax[this.url] = request;
					
				},
	 			data : {
	 				  'upperCategory' : upperCategory
	 				, 'fLocation' : fLocation
	 				, 'dspCtgryNo' : dspCtgryNo
	 				, 'ctgryDpthCd' : ctgryDpthCd
	 			},
	 			success : function(data) {
					
	 				if(data) {
	 					
	 					$("section.ctns").fadeOut(400, function(){
	 						
	 						$(this).empty().append($(data).html()).fadeIn(400);
	 						
	 						$("#wrap").scrollTop(0);
								 						
	 					});
	 					
	 				}
	 				
	 				delete currentAjax[this.url];	
	 				
	 			},
	 			error: function( pa_data, status, err ) {
	 				
	 				if(currentAjax.hasOwnProperty(this.url)) {
			        	delete currentAjax[this.url];
	 				}
	 	        
	 			}
	 		
			});
			
		}
	
	</script>