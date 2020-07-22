<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<h2 id="productInfo03" class="title03"><spring:message code="goods.qna.ttl"/></h2>
	<div class="product-inquiry">
		<p><spring:message code="goods.qna.lbl"/></p>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			<a href="#" onclick="javascript:goGodQna('Y','${goods.godEx.godNo}','${goods.godEx.erpGodNo}'); return false;"><spring:message code="goods.qna.btn.ask"/></a>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">			
			<a href="#" onclick="javascript:goGodQna('N','${goods.godEx.godNo}','${goods.godEx.erpGodNo}'); return false;"><spring:message code="goods.qna.btn.ask"/></a>
		</sec:authorize>				
	</div>	