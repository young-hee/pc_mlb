<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<c:choose>
	<c:when test='${_locale eq "en"}'>
		<c:set var = "_olapic_locale" value ="" />
		<c:set var = "_olapic_data_instance" value ="7cfe60e3e2453f68a3ed7cb834f92e79" />
		<c:set var = "_olapic_data_apikey" value ="b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b" />
	</c:when>
	<c:when test='${_locale eq "zh"}'>
		<c:set var = "_olapic_locale" value ="zh_CN" />
		<c:set var = "_olapic_data_instance" value ="91f0f2af003c22b11a064c4683dd7e5d" />
		<c:set var = "_olapic_data_apikey" value ="0f499dbda3dc7d8d63ba1cc127b8d24f4c8f39d81d4df544d68c225e7e16b4c9" />
	</c:when>
	<c:otherwise>
		<c:set var = "_olapic_locale" value ="ko_KR" />
		<c:set var = "_olapic_data_instance" value ="af84a6d5a231c4095b2a74620a3cd6a9" />
		<c:set var = "_olapic_data_apikey" value ="84ddeb156f96b575f3c235cff2d19a82461e8c150b57df811c493fb713f069e2" />
	</c:otherwise>
</c:choose>
<script type="text/javascript">
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
	olapicWidget.setAttribute('data-apikey', '${ _olapic_data_apikey }');
	//olapicWidget.setAttribute('data-mode', 'development');
	if("${ _olapic_locale }" != "") {
		olapicWidget.setAttribute('data-lang', '${ _olapic_locale }');
	}
	olapicWidget.async = true;
	document.getElementsByTagName("head")[0].appendChild(olapicWidget);
	console.log("_olapic_data_instance=" + '${ _olapic_data_instance }');
	console.log("_olapic_data_apikey=" + '${ _olapic_data_apikey }');
	console.log("call olapic");
</script>
<div class="contain dp promo" id="contain">
	<div class="container">
		<main class="contents" id="contents">
			<section class="mds-section arrival">
				<div class="hdt"><span class="tit">#MLB STYLE in SNS</span></div>
				<div id="olapic_specific_widget"></div>
			</section>
		</main>
	</div>
</div>