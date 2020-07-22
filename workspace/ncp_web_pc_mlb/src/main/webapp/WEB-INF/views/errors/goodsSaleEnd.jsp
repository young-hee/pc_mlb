<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<link rel="stylesheet" type="text/css" href="${_resourceURL}static/css/detail.css?timestamp=20170907" media="all">
<div class="system_error">
	<h3 class="colorDark fontXL font-mid"><spring:message code='goods.txt.renew.goods.error.info1' /></h3>
	<p><spring:message code='goods.txt.renew.goods.error.info2' /></p>
	<div class="btnWrap half">
		<div class="btn on"><a href="/main"><spring:message code='common.go.main' /></a></div>
		<div class="btn"><a href="javascript:history.back(-1);"><spring:message code='common.go.before.page' /></a></div>
	</div>
</div>
 