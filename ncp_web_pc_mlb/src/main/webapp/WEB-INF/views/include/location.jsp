<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

			<div class="location-container">
                <div class="location-contents">
                    <h2 class="title01"><spring:message code='${titleSetKey}' /></h2>
                    <p class="location">                    
	                    <c:forEach items="${locationSet}" var="loc" varStatus="status">
							<c:choose>
								<c:when test="${status.last}">
									<strong><spring:message code='${loc.msgKey}' /></strong>
								</c:when>
								<c:otherwise>
									<span><c:if test="${loc.url != null}"><a href="${loc.url}"></c:if><spring:message code='${loc.msgKey}' /><c:if test="${loc.url != null}"></a></c:if></span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
                    </p>
                 </div>
            </div>