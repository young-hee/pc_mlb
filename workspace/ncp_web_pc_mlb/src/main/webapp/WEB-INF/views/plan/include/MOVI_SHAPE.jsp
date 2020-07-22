<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

 <div class="contents-type01-box03">
	<div class="temp-box06">
		 <c:forEach items="${st.dspCnrSetList}" var="setArr" >
    	   <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	            <c:if test="${contt.conttTpCd eq 'MOVI'}">
		               <div class="temp-box06-video">
			                <c:if test="${ contt.moviKndCd eq 'YOUTUBE'}">
			                <iframe width="420" height="315" src="https://www.youtube.com/embed/${contt.moviFileUrl }" frameborder="0" allowfullscreen></iframe>
			                </c:if>
			                <c:if test="${ contt.moviKndCd eq 'VIMEO'}">
<iframe src="https://player.vimeo.com/video/${contt.moviFileUrl }?title=0&byline=0&portrait=0&badge=0" width="640" height="360" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
 			                
			                </c:if>    
		                 </div>
    	            </c:if>
    	            <c:if test="${contt.conttTpCd eq 'TEXT'}">
    	               <p class="temp-box06-${contt.conttSizeCd}">${ contt.conttNm}</p>
    	            </c:if>		    	            
    	   </c:forEach>
    	 </c:forEach>  
	</div>
</div>
  