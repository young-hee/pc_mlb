<section class="mds-section olapic">
	<div class="hdt"><span class="tit">#MLB NOW</span></div>

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
		olapicWidget.setAttribute('data-instance', 'd9e9046ceaaf9610efc4a6acf50d9e66');
		olapicWidget.setAttribute('data-apikey', 'b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b');
		//olapicWidget.setAttribute('data-mode', 'development');
		if("ko_KR" != "") {
			olapicWidget.setAttribute('data-lang', 'ko_KR');
		}
		olapicWidget.async = true;
		var olapic = null;
		document.getElementsByTagName("head")[0].appendChild(olapicWidget);
		console.log("_olapic_data_instance=" + 'd9e9046ceaaf9610efc4a6acf50d9e66');
		console.log("_olapic_data_apikey=" + 'b27b65c39319e6249e484321f122b655e88a123961a9d00ed3f89e2baee3f64b');
		console.log("call olapic");
	});
	</script>

	<div id="olapic_specific_widget"></div>

	
</section>
