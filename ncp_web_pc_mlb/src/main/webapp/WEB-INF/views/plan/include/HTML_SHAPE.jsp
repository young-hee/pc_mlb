<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
 
 <div class="contents-type01-box03">
	<div class="temp-box05">
		 <c:forEach items="${st.dspCnrSetList}" var="setArr" >
    	   <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	            <c:if test="${contt.conttTpCd eq 'HTML'}">
                           ${contt.htmlCont }
    	            </c:if>
    	            
    	   </c:forEach>
    	 </c:forEach>  
	</div>
</div>
 
 