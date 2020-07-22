<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

		 <c:forEach items="${st.dspCnrSetList}" var="setArr" >	
		    <c:set var="godCount" value="0"/>	
    	   <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	            <c:if test="${contt.conttTpCd eq 'GOD'}">
    	 
	                     <c:set var="godCount" value="${godCount+1 }"/>    
    	            </c:if>    	            
    	   </c:forEach>
    <c:if test="${godCount >5 }">
  
					<div class="contents-type01-box03">
						<div class="temp-box07">
							<div class="item-list06 d_style_slide">
								<div class="swiper-container">
									<ul class="swiper-wrapper">
    	                                      <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	                                               <c:if test="${contt.conttTpCd eq 'GOD'}">
										<li class="swiper-slide">
											<div class="item-img"><a  href="${contt.godUrl }" onclick="return false;" conttCnncUrl="${contt.godUrl }"  outptTpCd="${contt.outptTpCd }">
											    <img src="${imageURL }${contt.imgFrontUrl }" alt="${contt.godNm }"></a></div>
											<div class="item-info">
												<p class="txt01"><a  href="${contt.godUrl }" onclick="return false;" conttCnncUrl="${contt.godUrl }"  outptTpCd="${contt.outptTpCd }">${contt.godNm }</a></p>
											</div>
										</li>
    	                                               </c:if>
    	            
    	                                      </c:forEach>
    	   								</ul>
								</div>
								<div class="item-list-pagination"></div>
								<button type="button" class="item-list-prev">이전</button>
								<button type="button" class="item-list-next">다음</button>
							</div>
 
						</div>
					</div>
 
 
      </c:if>
    	 <c:if test="${godCount <6 }">
 
					<div class="contents-type01-box03">
						<div class="temp-box07">
							<div class="item-list07">
									<ul>
    	                               <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	                                    <c:if test="${contt.conttTpCd eq 'GOD'}">
										<li>
											<div class="item-img"><a  href="${contt.godUrl }" onclick="return false;" conttCnncUrl="${contt.godUrl }"  outptTpCd="${contt.outptTpCd }">
											    <img src="${imageURL }${contt.imgFrontUrl }" alt="${contt.godNm }"></a></div>
											<div class="item-info">
												<p class="txt01"><a  href="${contt.godUrl }" onclick="return false;" conttCnncUrl="${contt.godUrl }"  outptTpCd="${contt.outptTpCd }">${contt.godNm }</a></p>
											</div>
										</li>
    	                                     </c:if>
    	            
    	                                      </c:forEach>
    	   								</ul>
							</div>
 
						</div>
					</div>
     
    	 </c:if>

    	   <c:set var="godCount" value="0"/>
    	 </c:forEach>  
    	 

    	 
 