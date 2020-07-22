<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%--광고영역 --%>
<%-- 타게팅 게이츠 시작  * 공통 태그는 타 태그(아이템, 장바구니, 구매완료, 전환 완료)보다 하단에 위치하여야 합니다. --%>
<div id="targetGateScriptLoader"></div>
<div id="criteoScriptLoader"></div>
<%-- <script src="https://cdn.megadata.co.kr/js/enliple_min2.js" defer="defer"></script> --%>
<script type="text/javascript">
function fnf_appendTargetGateScript(){
	/* var targetGateTag = document.createElement("script");
	targetGateTag.type="text/javascript";
	targetGateTag.async=true;
	targetGateTag.src="//cdn-aitg.widerplanet.com/js/wp_astg_4.0.js";
	$("#targetGateScriptLoader").append(targetGateTag); */
}
function fnf_appendCriteoScript(){
	var criteoTag = document.createElement("script");
	criteoTag.type="text/javascript";
	criteoTag.async=true;
	criteoTag.src="//static.criteo.net/js/ld/ld.js";
	
	$.each(window.criteo_q, function(index, criteodata) {
		if(criteodata.event=='viewList'){
			if(criteodata.item.length==0){
				return;
			}
			if(criteodata.item.length>3){
				criteodata.item = criteodata.item.slice(0,3);
			}
		}
	});
	$("#criteoScriptLoader").append(criteoTag);
}
function fnf_appendMobonScript(){
	/* var rf = new EN();
	rf.setSSL(true);
	rf.sendRf(); */
}
</script>
<%-- 타게팅 게이츠 종료 --%>

<%-- Facebook Pixel Code 19/10/28 신규 광고 집행 스크립트 --%>
<script>
	!function(f,b,e,v,n,t,s)
	{if(f.fbq)return;n=f.fbq=function(){n.callMethod?
	n.callMethod.apply(n,arguments):n.queue.push(arguments)};
	if(!f._fbq)f._fbq=n;n.push=n;n.loaded=!0;n.version='2.0';
	n.queue=[];t=b.createElement(e);t.async=!0;
	t.src=v;s=b.getElementsByTagName(e)[0];
	s.parentNode.insertBefore(t,s)}(window,document,'script',
	'https://connect.facebook.net/en_US/fbevents.js');
	fbq('init', '2420314948195781');
	fbq('init', '396021361339657'); 
	fbq('track', 'PageView');
</script>
<noscript>
	<img height="1" width="1" src="https://www.facebook.com/tr?id=2420314948195781&ev=PageView&noscript=1"/>
	<img height="1" width="1" src="https://www.facebook.com/tr?id=396021361339657&ev=PageView&noscript=1"/>
</noscript>
<%-- End Facebook Pixel Code --%>

<%--naver --%>
<script type="text/javascript" src="//wcs.naver.net/wcslog.js"></script>
<script type="text/javascript">
function fnf_naverKeywordAdvertisement(val,sumAmount){
	if (!wcs_add) var wcs_add={};
	window.wcs_add = {wa:'s_1a7fdf177b81'};

	if (!_nasa) var _nasa={};
	
	if(val){
		_nasa["cnv"] = wcs.cnv(val,sumAmount); // 전환유형, 전환가치 설정해야함. 설치매뉴얼 참고else{}
	}
	
	wcs.inflow("mlb-korea.com");
	wcs_do(_nasa);
}
</script>
<%--naver --%>

<%-- edn start --%>
<c:if test="${empty goods.godEx.godNo}">
	<iframe id="hfrADCheck" src="//adcheck.about.co.kr/mad/prd/view?shopid=mlb" scrolling="no" frameborder="0" width="0" height="0" style="display: none;"></iframe>
</c:if>
<c:if test="${not empty goods.godEx.godNo}">
	<iframe id="hfrADCheck" src="//adcheck.about.co.kr/mad/prd/view?shopid=mlb&goodscode=${goods.godEx.godNo }" scrolling="no" frameborder="0" width="0" height="0" style="display: none;"></iframe>
</c:if> 
<%-- edn end --%>

<%--ga --%>
<script>
	(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
	(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
	m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
	})(window,document,'script','//www.google-analytics.com/analytics.js','ga');
	ga('create', 'UA-29462318-1', 'mlb-korea.com');
	ga('send', 'pageview');
	ga('create', 'UA-29462318-4', 'auto', 'newTracker');
	ga('newTracker.send', 'pageview');
</script>
<%--ga --%>

<!-- Global site tag (gtag.js) - Google Ads: 840871019 -->
<script async src="https://www.googletagmanager.com/gtag/js?id=AW-840871019"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'AW-840871019');
</script>


