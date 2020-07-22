<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

			<acrticle id="container" class="contents-type01">
				<section id="contents">
					  <c:forEach items="${style.planCornerList}" var="st" >
					    <c:choose>
					      <c:when test="${st.dspCnrSectCd eq 'TITLE_SHAPE' }">
					       <%@ include file="/WEB-INF/views/plan/include/TITLE_SHAPE.jsp"%>
					      </c:when>
					      <c:when test="${st.dspCnrSectCd eq 'LEFT_SHAPE' }">
					      <%@ include file="/WEB-INF/views/plan/include/LEFT_SHAPE.jsp"%>
					      </c:when>      
					      <c:when test="${st.dspCnrSectCd eq 'RIGHT_SHAPE' }">
					      <%@ include file="/WEB-INF/views/plan/include/RIGHT_SHAPE.jsp"%>
					      </c:when>				      
					      <c:when test="${st.dspCnrSectCd eq 'WIDE_SHAPE' }">
					      <%@ include file="/WEB-INF/views/plan/include/WIDE_SHAPE.jsp"%>
					      </c:when>				      
					      <c:when test="${st.dspCnrSectCd eq 'HTML_SHAPE' }">
					      <%@ include file="/WEB-INF/views/plan/include/HTML_SHAPE.jsp"%>
					      </c:when>				      
					      <c:when test="${st.dspCnrSectCd eq 'MOVI_SHAPE' }">
					      <%@ include file="/WEB-INF/views/plan/include/MOVI_SHAPE.jsp"%>
					      </c:when>				      
					      <c:when test="${st.dspCnrSectCd eq 'THUMB_SHAPE' }">
					     
					      <%@ include file="/WEB-INF/views/plan/include/THUMB_SHAPE.jsp"%>
					      </c:when>				      
					      <c:otherwise>
					      </c:otherwise>
					    </c:choose>
					
					  </c:forEach>
 					<div class="contents-type01-box03">
						<div class="temp-box09">
							<div class="txt-period">
								<div class="txt-period-inner">
									<p class="txt-period-date"><spring:message code="display.main.text16"/> : <fmt:formatDate value="${style.dspPromtExtend.regDt }" pattern="yyyy-MM-dd"/></p>
									<div class="btn-share-wrap d_toggle_hover">
										<button type="button" class="btn-share btn-action d_toggle_hover_sel" title="공유하기 버튼 보기">공유하기</button>
										<div class="btn-share-layer d_toggle_hover_cont">
											<a href="#" class="btn-share type01" title="face book 공유">새창열림</a>
											<a href="#" class="btn-share type02" title="카카오 스토리 공유">새창열림</a>
											<a href="#" id="viewLink" class="btn-share type03" title="url 공유">새창열림</a>
										</div>
									</div>
								</div>
							</div>
							<div class="btn-wrap">
								<a href="/discoverer/discovererList" class="btn-style02"><spring:message code="display.discoverer.text1" /></a>
							</div>
						</div>
					</div>
					
					<c:if test="${fn:length(style.styleList) > 0 }">
					<div class="contents-type01-box03">
						<div class="temp-box08">
							<p class="temp-box08-txt01"><spring:message code="display.discoverer.text2" /></p>
							<div class="item-list05">
								<ul>
					<c:forEach items="${style.styleList}" var="st" >
									<li>
										<div class="item-list05-img"><a href="/discoverer/${st.evtNo }"><img src="${imageURL }${st.pcImgFileUrl }" alt="${st.evtNm }"></a></div>
										<p class="item-list05-txt01"><a href="#">${st.evtNm }</a></p>
									</li>
					   </c:forEach>			
 
								</ul>
							</div>
						</div>
					</div>					
					</c:if>

					<script>
						$('.d_style_slide').each(function(){
							var $this = $(this).find('.swiper-container'),
								$thisPaing = $(this).find('.item-list-pagination'),
								$thisPrev = $(this).find('.item-list-prev'),
								$thisNext = $(this).find('.item-list-next');
							var itemList06 = new Swiper($this, {
								slidesPerGroup: 1,
								slidesPerView: 'auto',
								spaceBetween: 20,
								loop: true,
								pagination: {
									el: $thisPaing,
									type: 'fraction'
								},
								navigation: {
									nextEl: $thisNext,
									prevEl: $thisPrev
								},
								on: {
									init: function() {
										$this.find('li').eq(this.activeIndex + 2).addClass('on').siblings().removeClass('on'); 
									},
									slideChange: function() {
										$this.find('li').eq(this.activeIndex + 2).addClass('on').siblings().removeClass('on');
									}
								}
							});
						});
					</script>
				</section>
						    <%@ include file="/WEB-INF/views/main/include/aTagLink.jsp"%>
			</acrticle>
			
 