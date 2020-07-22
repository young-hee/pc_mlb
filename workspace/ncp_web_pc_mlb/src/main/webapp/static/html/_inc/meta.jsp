<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="utf-8">
<meta name="viewport" content="width=1300">
<title>MLB_PC</title>
<link rel="shortcut icon" type="image/x-icon" href="/static/images/cm/favicon.ico">
<link rel="icon" type="image/x-icon" href="/static/images/cm/favicon.png">

<%@ page import="java.util.*" %>
<%
	Date now = new Date();
	String yy = Integer.toString( now.getYear()+1900 ) ;
	String mm = Integer.toString( now.getMonth()+1 ) ;
	String dd = Integer.toString( now.getDate() ) ;
	String hh = Integer.toString( now.getHours() ) ;
	String nn = Integer.toString( now.getMinutes() ) ;
	String ss = Integer.toString( now.getSeconds() ) ;
	String ver_css =  yy +"-"+ mm +"-"+ dd +"-"+ hh +"-"+ nn +"-"+ ss ;
%>

<link href="/static/css/swiper.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/bs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/cm.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/ly.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/mn.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/dp.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/pd.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/od.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/my.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/mb.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/se.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/ev.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/cs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
<link href="/static/css/et.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">

<script src="/static/js/jquery-3.3.1.js"></script>
<script src="/static/js/jquery-ui.min.js"></script>
<script src="/static/js/swiper.min.js"></script>
<script src="/static/js/commonFunction.js"></script>
<script src="/static/js/common.js"></script>


<script src="/static/html/cm/html.js"></script> <!-- 개발페이지에서는 html.js를 임포트 하지 말아주세요 -->