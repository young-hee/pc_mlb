<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Olapic Widget for MLB Korea</title>
	<base target="_blank" />

	<style>
		html, body {
			width: 100%;
			height: 100%;
			margin: 0;
			padding: 0;
		}
		
	</style>
	<script src="${_resourceURL}static/js/jquery-3.3.1.js"></script>
	<script src="${_resourceURL}static/js/ResizeSensor.js"></script>
	<script src="${_resourceURL}static/js/ElementQueries.js"></script>
</head>
<body onload="checkResize();">

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
		olapicWidget.setAttribute('data-instance', '${ olapicInstance }');
		olapicWidget.setAttribute('data-apikey', '${ olapicApiKey }');
		//olapicWidget.setAttribute('data-mode', 'development');
		if("${ _olapic_locale }" != "") {
			olapicWidget.setAttribute('data-lang', '${ olapicLocale }');
		}
		olapicWidget.async = true;
		
		var olapic = null;
		document.getElementsByTagName("head")[0].appendChild(olapicWidget);
		function checkResize() {
			console.log("checkResize");
			if(		olapic != null
					&& $.type(olapic.getWidgetInstance('olapic_specific_widget')) == "object"
					&& $.type(olapic.getWidgetInstance('olapic_specific_widget').viewer) == "object"
					&& $.type(olapic.getWidgetInstance('olapic_specific_widget').viewer.callBacks) == "object"
					&& $.type(olapic.getWidgetInstance('olapic_specific_widget').viewer.callBacks.olapicViewer2AfterShow) == "function") {
				console.log("olapic setting");
				console.log("checkResize");
				new ResizeSensor($(".olapic-slider-wrapper"), function(){ 
					resize();
				});
				new ResizeSensor($(".olapic-viewer-overlay"), function(){ 
					resize();
				});
				new ResizeSensor($("#olapic-wall-widget"), function(){ 
					resize();
				});
				new ResizeSensor($(document.body), function(){ 
					resize();
				});
				$(".olapic-action-buttons").remove();
				$(".olapic-header-tools").remove();
				resize();
			} else {
				setTimeout(checkResize, 500);
			}
 		}
		
		function resize() {
			try{
				var height = $(".olapic-viewer-overlay").prop("scrollHeight");
				if (height == undefined || height == 0) {
					height = $(".olapic-wall-list-container").prop("scrollHeight");
				}
				console.log("height = " + height);
				parent.resizeIframe(null, height);
			} catch (e) {
				
			}
		}
		
	</script>

	<div id="olapic_specific_widget"></div>
	
</body>
</html>