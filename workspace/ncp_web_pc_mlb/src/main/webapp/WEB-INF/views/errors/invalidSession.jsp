<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>


	<div class="system_error">
		<h3 class="colorDark">
			<spring:message code='common.double.login' /><br />
			<span><spring:message code='common.thank.customer' /></span>
		</h3>
		<p>
			<spring:message code='common.already.use.relogin' />
		</p>
		<div class="btnWrap half">
			<div class="btn on"><a href="/main" ><spring:message code='common.go.main' /></a></div>
			<!-- 2015-06-10 문구수정 -->
			<div class="btn"><a href="/public/member/login">로그인</a></div>
		</div>
	</div>
<input type="hidden" name="err" value="invalidSession" />

