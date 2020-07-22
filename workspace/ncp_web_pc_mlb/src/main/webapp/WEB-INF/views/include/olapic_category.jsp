<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<c:choose>
	<c:when test='${_locale eq "en"}'>
		<c:set var = "_olapic_locale" value ="" />
		<c:set var = "_olapic_data_instance" value ="b5746501720a64073774cc4f3aed8032" />
		<c:set var = "_olapic_data_apikey" value ="b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b" />
	</c:when>
	<c:when test='${_locale eq "zh"}'>
		<c:set var = "_olapic_locale" value ="zh_CN" />
		<c:set var = "_olapic_data_instance" value ="b5746501720a64073774cc4f3aed8032" />
		<c:set var = "_olapic_data_apikey" value ="b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b" />
	</c:when>
	<c:otherwise>
		<c:set var = "_olapic_locale" value ="ko_KR" />
		<c:set var = "_olapic_data_instance" value ="b5746501720a64073774cc4f3aed8032" />
		<c:set var = "_olapic_data_apikey" value ="b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b" />
	</c:otherwise>
</c:choose>
<script type="text/javascript">
$(document).ready(function(){
	var olapicWidgetParams = (function(a) {
		if (a == "") return {};
		var b = {};
		for (var i = 0; i < a.length; ++i){
			var p=a[i].split('=');
			if (p.length != 2) continue;
			b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
		}
		return b;
	})(window.location.search.substr(1).split('&'));
	var olapicWidget = document.createElement("script");
	olapicWidget.type = "text/javascript";
	olapicWidget.src = "https://photorankstatics-a.akamaihd.net/743d2e78a76dedeb07e0745158547931/static/frontend/latest/build.min.js"
	olapicWidget.charset = "UTF-8";
	olapicWidget.setAttribute('data-olapic', 'olapic_specific_widget');
	olapicWidget.setAttribute('data-instance', '${ _olapic_data_instance }');
	console.log("dspCtgryNo=" + olapicWidgetParams["dspCtgryNo"]);
	
	if(olapicWidgetParams["dspCtgryNo"] != undefined 
			&& olapicWidgetParams["dspCtgryNo"] != null 
			&& olapicWidgetParams["dspCtgryNo"] != "") {
// 		olapicWidget.setAttribute('data-tags', olapicWidgetParams["dspCtgryNo"]);
		olapicWidget.setAttribute('data-tags', "BLACK");
		console.log("olapic data-tags is used, " + olapicWidgetParams["dspCtgryNo"]);
	}else{
		console.log("olapic data-tags is not used.");
	}
	olapicWidget.setAttribute('data-apikey', '${ _olapic_data_apikey }');
// 	//olapicWidget.setAttribute('data-mode', 'development');
	if("${ _olapic_locale }" != "") {
		olapicWidget.setAttribute('data-lang', '${ _olapic_locale }');
	}
	olapicWidget.async = true;
	var olapic = null;
	document.getElementsByTagName("head")[0].appendChild(olapicWidget);
	console.log("_olapic_data_apikey=" + '${ _olapic_data_apikey }');
	console.log("_olapic_data_instance=" + '${ _olapic_data_instance }');
	console.log("call olapic");
});
</script>

<div id="olapic_specific_widget"></div>