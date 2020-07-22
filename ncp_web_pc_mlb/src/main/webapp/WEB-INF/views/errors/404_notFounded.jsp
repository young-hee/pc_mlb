<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<title>MLB_PC</title>
<link rel="shortcut icon" type="image/x-icon" href="${_resourceURL}static/images/cm/favicon.ico" />
<link rel="icon" type="image/x-icon" href="${_resourceURL}static/images/cm/favicon.png" />

<%@ page import="java.util.*" %>
<%
	Date now = new Date();
	String yy = Integer.toString( now.getYear()+1900 ) ;
	String mm = Integer.toString( now.getMonth() ) ;
	String dd = Integer.toString( now.getDate() ) ;
	String hh = Integer.toString( now.getHours() ) ;
	String nn = Integer.toString( now.getMinutes() ) ;
	String ss = Integer.toString( now.getSeconds() ) ;
	String ver_css =  mm +"-"+ dd +"-"+ hh +"-"+ nn +"-"+ ss ;
%>

<link href="${_resourceURL}static/css/swiper.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<!-- <link href="/static/css/jquery-ui.1.12.1.css?v=<%=ver_css%>" rel="stylesheet" type="text/css"> -->
<link href="${_resourceURL}static/css/bs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/cm.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/ly.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/mn.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/dp.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/pd.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/od.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/my.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/mb.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/se.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/ev.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/cs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="${_resourceURL}static/css/et.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">

<script src="${_resourceURL}static/js/jquery-3.3.1.js"></script>
<script src="${_resourceURL}static/js/jquery-ui.min.js"></script>

<!-- <script src="/static/js/jquery-migrate-1.4.1.js"></script> -->
<!-- <script src="/static/js/jquery-ui-1.12.1.js"></script> -->
<script src="${_resourceURL}static/js/swiper.min.js"></script>
<script src="${_resourceURL}static/js/commonFunction.js"></script>
<script src="${_resourceURL}static/js/common.js"></script>

</head>
<body class="body">
<div class="wrap" id="wrap">	
	
	<!-- error -->
	<div class="errorMsg">
		<h1><img src="${_resourceURL}static/images/et/logo.png" alt="MLB"></h1>
		<p><spring:message code="common.error.txt1" text="이용에 불편을 드려 대단히 죄송합니다."/><br><span><spring:message code="common.error.txt2" text="찾으시려는 페이지가 존재하지 않거나, 현재 사용할 수 없는 페이지입니다."/></span></p>
		<a href="/" class="btn fill xl"><spring:message code="common.error.txt3" text="메인으로 가기"/></a>
	</div>
	
	
</div>

</body>
</html>