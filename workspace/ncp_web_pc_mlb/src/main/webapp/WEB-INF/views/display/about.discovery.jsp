<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>

<ncp:prop key="ncp.image.url" var="imageURL"/>
<style>
 .brand-visual.brand-visual-type01 {
	background:url(${_resourceURL}static/images/showing/bg_brand01_${_locale}.png) no-repeat 50% 0;
}
.brand-visual.brand-visual-type02 {
	background:url(${_resourceURL}static/images/showing/bg_brand02_${_locale}.png) no-repeat 50% 0;
}
.brand-visual.brand-visual-type03 {
	background:url(${_resourceURL}static/images/showing/bg_brand03_${_locale}.png) no-repeat 50% 0;
}
</style>

 			<div class="location-container">
				<div class="location-contents">
					<h2 class="title01">브랜드</h2>
					<p class="location">
						<span>Home</span><strong title="현재 위치"><spring:message code="display.discovery.text1" /></strong>
					</p>
				</div>
			</div>
			<acrticle id="container">
				<section id="contents">
					<div class="brand-visual brand-visual-type01">
						<ul id="aboutDiscovery">
							<li class="on" ><a  data-class="brand-visual brand-visual-type01" class="expedition" href="javascript_:void(0);" onclick="return false;"><span>DISCOVERY EXPEDITION</span></a></li>
							<li><a data-class="brand-visual brand-visual-type02" class="technology" href="javascript_:void(0);" onclick="return false;"><span>DISCOVERY TECHNOLOGY</span></a></li>
							<li><a data-class="brand-visual brand-visual-type03" class="smartfitguide" href="javascript_:void(0);" onclick="return false;"><span>SMART FIT GUIDE</span></a></li>
						</ul>
					</div>
					
					<div id="expedition" class="brand-cont">
						<p class="brand-txt02">
							<spring:message code="display.discovery.text2" />
						</p>
						<div class="brand-txt03">
							<div class="brand-txt03-img01">
								<img src="${_resourceURL}static/images/showing/img_expedition01.png" alt="이미지1">
							</div>
							<p class="brand-txt03-txt01">
								<spring:message code="display.discovery.text3" />
							</p>
							<p class="brand-txt03-txt02">
								<spring:message code="display.discovery.text4" />
							</p>
						</div>
						<div class="brand-txt04">
							<div class="brand-txt04-img01">
								<img src="${_resourceURL}static/images/showing/img_expedition02.png" alt="이미지1">
							</div>
							<p class="brand-txt04-txt01">
								Discovery Channel
							</p>
							<p class="brand-txt04-txt02">
								<spring:message code="display.discovery.text5" />
							</p>
							<p class="brand-txt04-txt03">
								<spring:message code="display.discovery.text6" />
							</p>
						</div>
					</div>
				
					 <div id="technology" class="brand-cont">
						<dl class="brand-txt">
							<dt><img src="${_resourceURL}static/images/showing/txt_brand01.png" alt="S.U.R.E SYSTEM"></dt>
							<dd><spring:message code="display.discovery.text7" /></dd>
						</dl>
						<dl class="brand-txt brand-txt-type01">
							<dt class="brand-txt-dt01">SMART<br>ULTIMATE<br>REFINED<br>EVLOLUTON</dt>
							<dd><spring:message code="display.discovery.text8" /></dd>
						</dl>
						<dl class="brand-txt brand-txt-type02">
							<dt class="brand-txt-dt02"><span>ABOUT<br>S.U.R.E<br>DEFINITION</span></dt>
							<dd class="brand-txt-dd02"><spring:message code="display.discovery.text9" /></dd>
						</dl>
						<ul class="brand-list">
							<li><img src="${_resourceURL}static/images/showing/img_definition01_${_locale}.png" alt="흡습속건 QUICK DRY : 몸에서 발생한 땀과 습기를 빠르게 흡수하고 외부로 배출하여 신체를 쾌적하게 유지시켜 줍니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition02_${_locale}.png" alt="신축성 STRETCH : 탄력성과 회복력이 뛰어난 소재를 사용하여 편안한 움직임과 활동성을 지원합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition03_${_locale}.png" alt="보온 HEAT : 외부에서 발생한 열을 보관하는 기술과 보온성 소재의 결합을 통하여 따뜻함을 유지시켜 줍니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition04_${_locale}.png" alt="방품 WINDPROOF : 바람의 침투를 막아주어 체온을 유지해주고 활동성을 보장합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition05_${_locale}.png" alt="방수/투습 WATERPROOF/BREATHABLE : 물·비·눈은 막아주고, 몸에서 발생한 습기는 배출시키는 소재와 기술을 통하여 쾌적함을 보장합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition06_${_locale}.png" alt="냉감 COOLING : 소재와 원사구조, 후가공을 통하여 피부의 열을 빠르게 낮추어 시원함을 제공합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition07_${_locale}.png" alt="UV차단 UV PROTECT : 자외선을 차단하는 기능성 원단을 통해 유해한 태양광으로부터 피부를 보호합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition08_${_locale}.png" alt="경량 LIGHTNESS : 동일한 기능이더라도 보다 더 가벼운 소재를 사용하여 편안한 착용감을 제공합니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition09_${_locale}.png" alt="발수 WATER REPELLENT : 소재 가공과 발수막 코딩 기술을 통하여 물과 습기가 스며드는 것을 방지해 줍니다."></li>
							<li><img src="${_resourceURL}static/images/showing/img_definition10_${_locale}.png" alt="향균 소취 ANTI-BACTERIAL DEODORIZATION : 악취의 근원인 박테리아와 땀으로 인한 세균번식을 막아주어 상쾌한 착용감을 제공합니다."></li>
						</ul>
					</div>
					<div  id="smartfitguide"  class="brand-cont">
						<dl class="brand-txt brand-txt-type03">
							<dt class="brand-txt-dt02"><span>SMART FIT<br><em>GUIDE</em></span></dt>
							<dd class="brand-txt-dd02"><spring:message code="display.discovery.text10" /></dd>
						</dl>
						<ul class="brand-list">
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit01_${_locale}.png" alt="COMFORT FIT : 여유로운 스펙으로 편안한 활동성을 제공하는 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit02_${_locale}.png" alt="TIGHT FIT : 전체적으로 타이트하게 밀착되어 바디라인을 살려주는 기능성 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit03_${_locale}.png" alt="JOGGER FIT : 허벅지가 여유있고 밑단으로 내려갈수록 좁아지는 트렌디한 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit04_${_locale}.png" alt="SLIM FIT : 힙라인부터 밑단까지 전체적으로 밀착되는 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit05_${_locale}.png" alt="STRAIGHT FIT : 전체적으로 일자로 떨어지는 베이지 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit06_${_locale}.png" alt="SEMI-LOOSE FIT : 다리 라인을 따라 전박적으로 여유를 준 루즈한 스타일 핏"></li>
							<li><img src="${_resourceURL}static/images/showing/img_smart_fit07_${_locale}.png" alt="SPORTS(3D) FIT : 3D 입체패턴을 적용하여 착용감을 극대화한 테크니컬 핏"></li>
						</ul>
					</div>
				</section>
			</acrticle>


<script type="text/javascript">
 
$(document).ready(function() {
	$('.brand-cont').hide();
	$('#aboutDiscovery a').click(function(){
		$(this).closest('div').removeClass().addClass($(this).data("class"));
		$('#aboutDiscovery li').removeClass('on');
		$(this).closest('li').addClass(' on');
		var clickClass = $(this).attr('class');
		$('.brand-cont').hide();
		$('#'+clickClass).show();
 
 
	});
	
   var filterType = '${filterType}';
   if(filterType != ''){
	   $('.'+filterType).click();   
   }else{
	   $('#expedition').show();
   }
   
   
});
 
 
</script>
