<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

 <div class="contents-type01-box01">
	<div class="temp-box01">
		 <c:forEach items="${st.dspCnrSetList}" var="setArr" >
    	   <c:forEach items="${setArr.cnrConttExList}" var="contt" >
    	            <c:if test="${contt.conttTpCd eq 'IMG_BANNER'}">
		               <div class="temp-box01-img01">
			             <a  href="${contt.conttCnncUrl }" onclick="return false;" conttCnncUrl="${contt.conttCnncUrl }"  outptTpCd="${contt.outptTpCd }"  ><img src="${imageURL }${contt.imgFileUrl}/${contt.imgFileNm}" alt=""></a>
		                 </div>
    	            </c:if>
    	            <c:if test="${contt.conttTpCd eq 'TEXT'}">
    	              <div class="temp-box01-${contt.conttSizeCd}">
			            <p>${ contt.conttNm}</p>
		              </div>
    	            </c:if>		    	            
    	   </c:forEach>
    	 </c:forEach>  
	</div>
</div>