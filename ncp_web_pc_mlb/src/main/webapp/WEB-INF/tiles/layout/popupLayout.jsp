<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width">

	<title>Discovery Expedition</title>
	<link rel="shortcut icon" type="image/x-icon" href="${_resourceURL}static/favicon/favicon.ico" />
	<link rel="icon" type="image/x-icon" href="${_resourceURL}static/favicon/favicon.ico" />

	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta name="_csrf.parameter" content="${_csrf.parameterName}" />

	<script type="text/javascript" src="${_resourceURL}static/js/min/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="${_resourceURL}static/js/common.js"></script>
	<script type="text/javascript" src="${_resourceURL}static/js/commonFunction.js"></script>
</head> 
<html lang="${pageContext.response.locale.language}">
<body>

<tiles:insertAttribute name="body"/>

</body>
</html>
