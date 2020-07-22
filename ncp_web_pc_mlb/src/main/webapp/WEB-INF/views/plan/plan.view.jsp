<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<!-- 컨텐츠 시작 -->
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<!-- Title start -->
				<!-- div class="pm_top">
					<span class="tit">${plan.dspPromtExtend.promtNm}</span>
					<div class="info">
						<div class="fl"></div>
						<div class="fr">
							<span class="date"><fmt:formatDate value="${plan.dspPromtExtend.dspBegDt }" pattern="yyyy.MM.dd"/> ~  <fmt:formatDate value="${plan.dspPromtExtend.dspEndDt}" pattern="yyyy.MM.dd"/></span>
							< %@ include file="/WEB-INF/views/plan/include/snsShare.jsp"% >			
						</div>
					</div>
				</div -->

				<c:if test="${!empty plan.planCornerList}">
					<!-- Image or Html start -->
					<section class="pm_vis_area">
					
						<c:set value="0" var="contCnt"/>
					
						<c:forEach items="${plan.planCornerList}" var="cnr">
						
							<c:forEach items="${cnr.dspCnrSetList}" var="set">
								
								<c:forEach items="${set.cnrConttExList}" var="cont" varStatus="status">
								
									<c:if test="${status.index eq 0}">
										
										<c:if test="${cont.conttTpCd eq 'IMG_BANNER' and contCnt lt 1}">
										
											<div class="vs_img">
												<!-- <p align="center">이미지영역</p> -->
												<img src="${_image}${cont.imgFileUrl}/${cont.imgFileNm}/dims/resize/1200" alt="${cont.imgAltrtvCont}" >
											</div>
											
										</c:if>
										<c:if test="${cont.conttTpCd eq 'HTML' and contCnt lt 1}">
										
											<div class="vs_html">
												${cont.htmlCont}
											</div>
											
										</c:if>
										
										<c:set value="${contCnt + 1}" var="contCnt"/>
										
									</c:if>
									
								</c:forEach>
								
							</c:forEach>
						
						</c:forEach>
						
					</section>
					
				</c:if>
				
				<c:if test="${!empty plan.sprtrList}">
					
					<c:set value="0" var="godCnt"/>
										
					<c:forEach items="${plan.sprtrList}" var="sprtr" varStatus="status">
					
						<c:if test="${!empty sprtr.godList}">
							<c:set value="${godCnt + 1}" var="godCnt"/>
						</c:if>
					
					</c:forEach>
					
					<c:if test="${godCnt gt 0}">
					
						<!-- God Tab start -->
						<section class="tabs" id="tabsLink">
								
							<div class="tab-type06 tab">
								<ul>
									
									<c:forEach items="${plan.sprtrList}" var="sprtr" varStatus="status">
										
										<c:if test="${!empty sprtr.godList}">
											
											<li>
												<a href="#pm_list_view_${sprtr.sprtrTurn}">
													<c:choose>
														<c:when test="${empty sprtr.sprtrImgFileNm}">
															${sprtr.sprtrNm}
														</c:when>
														<c:otherwise>
															<img src="${_image}${sprtr.sprtrImgFileUrl}/${sprtr.sprtrImgFileNm}" alt="${sprtr.sprtrImgAltrtvCont}"  >
														</c:otherwise>											
													</c:choose>
												</a>
											</li>
											
										</c:if>
										
									</c:forEach>
									
								</ul>
							</div>
							
						</section>
						
						<c:forEach items="${plan.sprtrList}" var="sprtr" varStatus="status">
						
							<c:if test="${!empty sprtr.godList}">
							
								<section class="pm_list_view" id="pm_list_view_${sprtr.sprtrTurn}">
									<div class="hd">${sprtr.sprtrNm}</div>
									<div class="ui_dp_list">
										<ul class="list">
										
											<c:forEach items="${sprtr.godList}" var="god">
											
												<li>
													<div class="item">
														<div class="thumb">
															<a href="${god.godUrl}">
																<c:if test="${god.godSaleSectCd eq 'SLDOUT'}">
																	<em class="sold_out">SOLD OUT</em>
																</c:if>
																<%-- S : 2020-03-20 Family flag add --%>
                                                                <span class="flag_family">Family</span>
                                                                <%-- E : 2020-03-20 Family flag add --%>
																<span class="img">
																	<img src="${_image}${god.imgFrontUrl}/dims/resize/285x285" alt="${god.godNm}" onerror='errorImgShow(this, "285");'>
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
																${god.godNm}
															</div>
															<div class="prc">
																<c:choose>
																	<c:when test="${god.csmrPrc lt god.rtlPrc}">
																		<s class="s"><fmt:formatNumber value="${god.rtlPrc}" type="number"/><spring:message code="display.main.text1" /></s> 
																		<em class="p"><fmt:formatNumber value="${god.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>		
																	</c:when>
																	<c:otherwise>
																		<em class="p"><fmt:formatNumber value="${god.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
																	</c:otherwise>
																</c:choose>
															 </div>
															<c:if test="${god.godSaleSectCd eq'SMTM_SLDOUT'}">
																	<div class="solds"><spring:message code="display.god.commingsoon" /></div>
															</c:if>
														</div>
													</div>
												</li>
											
											</c:forEach>
											
										</ul>
									</div>
								</section>
							
							</c:if>
						
						</c:forEach>
					
					</c:if>
				
				</c:if>

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->
	
	<script>
		$(document).ready(function(){
			
			if(   $(window).outerHeight()  ==  $("#wrap").outerHeight()  ) {
				
				$("#tabsLink a[href*='#pm_list_view']").on("click",function(e){
					e.preventDefault();
					var thisContID = $(this).attr("href");
					var headHt = $(".header").outerHeight();
					var thisContTop = $(thisContID).offset().top + $("#wrap").scrollTop();
					$("#wrap").scrollTop( thisContTop - headHt );
				});
				
			} else {
				
				$("#tabsLink a[href*='#pm_list_view']").on("click",function(e){
					e.preventDefault();
					var thisContID = $(this).attr("href");
					var headHt = $(".header").outerHeight();
					var thisContTop = $(thisContID).offset().top + $("#wrap").scrollTop();
					$(window).scrollTop( thisContTop - headHt );
				});
				
			}
			
			
		});
	</script>
			
 