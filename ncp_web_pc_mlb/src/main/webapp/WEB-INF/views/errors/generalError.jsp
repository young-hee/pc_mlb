<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<meta name="viewport" content="width=device-width" >
<link rel="stylesheet" href="${_resourceURL}static/css/base.css">
<link rel="stylesheet" href="${_resourceURL}static/css/common.css">
<script type="text/javascript" src="${_resourceURL}static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/swiper.min.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/common.js"></script>
<title>Discovery</title>

<article class="error-wrap">
	<h1><a href="/"><img src="${_resourceURL}static/images/common/logo.png" alt="Discovery EXPEDITION"></a></h1>
	<p class="error-txt01">I AM A DISCOVERER!</p>
	<div class="error-inner">
		<p class="error-txt02"><spring:message code="common.sorry.inconven" /> </p>
		<p class="error-txt03"><spring:message code="common.general.error" /> </p>
	</div>
	<div class="btn-wrap">
		<a href="javascript:history.back(-1);" class="btn-style03"><spring:message code='common.go.before.page' /></a>
		<a href="/" class="btn-style02"><spring:message code='common.go.main' /></a>
	</div>
</article>
<input type="hidden" name="err" value="generalError" />
