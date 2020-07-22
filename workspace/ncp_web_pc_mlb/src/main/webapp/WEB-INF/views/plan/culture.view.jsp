<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<!-- 컨텐츠 시작 -->
	<div class="contain dp cult view" id="contain">
		<div class="container">

			<main class="contents" id="contents">
			
				<div class="cult_top">
					<span class="tit">CULTURE</span>
				</div>

				<div class="dp_cult_menu">
					<ul class="menu d_tab">
						<li<c:if test="${empty dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList">ALL</a></li>
						<li<c:if test="${'MAGAZINE' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=MAGAZINE">MAGAZINE</a></li>
						<li<c:if test="${'INTERVIEW' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=INTERVIEW">INTERVIEW</a></li>
						<li<c:if test="${'CELEB' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=CELEB">CELEB</a></li>
						<li<c:if test="${'FESTIVAL_PARTY' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=FESTIVAL_PARTY">FESTIVAL&PARTY</a></li>
					</ul>
				</div>
				
				<!-- Title start -->
				<div class="cult_hd">
					<span class="tit">${culture.dspPromtExtend.promtNm}</span>
					<div class="info">
						<div class="fl"></div>
						<div class="fr">
							<%@ include file="/WEB-INF/views/plan/include/snsShare.jsp"%>					
						</div>
					</div>
				</div>

				<c:if test="${!empty culture.planCornerList}">
					<!-- Image or Html start -->
					<section class="cult_ctns">
					
						<c:forEach items="${culture.planCornerList}" var="cnr">
						
							<c:forEach items="${cnr.dspCnrSetList}" var="set">
								
								<c:forEach items="${set.cnrConttExList}" var="cont">
									
									<c:if test="${cont.conttTpCd eq 'MOVI' and fn:length(set.cnrConttExList) eq 1}">
										<div class="video_area">
											<c:choose>
												<c:when test="${cont.moviKndCd eq 'VIMEO'}">
													<iframe width="1200" height="675" src="https://player.vimeo.com/video/${cont.moviFileUrl}" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
												</c:when>
												<c:otherwise>
													<iframe width="1200" height="675" src="https://www.youtube.com/embed/${cont.moviFileUrl}?rel=0&loop=1&playlist=${cont.moviFileUrl}" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
												</c:otherwise>
											</c:choose>
										</div>
									</c:if>
									
									<c:if test="${cont.conttTpCd eq 'HTML'}">
										${cont.htmlCont}
									</c:if>
									
								</c:forEach>
								
							</c:forEach>
						
						</c:forEach>
						
					</section>
					
				</c:if>

			</main>
			
		</div>
	</div>
	<script>
	
	</script>