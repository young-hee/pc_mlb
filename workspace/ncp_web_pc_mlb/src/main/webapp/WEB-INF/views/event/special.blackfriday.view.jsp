<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/event/event.js?v=${_version}"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<!-- 컨텐츠 시작 -->
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<section class="pm_vis_area">
					<div class="vs_html">


<!-------------------- 기획전 소스 START -------------------->
<style type="text/css">
html, body{overflow:visible;overflow-x:hidden;height:auto}
.blind {overflow:hidden; position:absolute; width:1px; height:1px; margin:-1px; padding:0; border:0; clip:rect(0,0,0,0);}
.evt-wrap{position:relative; left:50%; margin-left:-960px; width:1920px; text-align:center}
.evt-wrap a.button{position:absolute; left:50%; top:810px; display:block; margin-left:-240px}
</style>
<div class="evt-wrap">
	<div class="blind">
		<h3>MLB | MLB KIDS BLACK FRIDAY</h3>
		<em>엠엘비 블랙프라이데이 회원  선입장!</em>
		<p>특별한 가격의 한정수량 상품들을 가장 먼저 만나보세요.</p>
		<p>PRE-OPEN! 19.11.21 ~ 11.25(5일간)</p>
	</div>

	<a href="javascript:enterBlackFriday();" class="button"><img src="http://static.mlb-korea.com/motioneye/2019/34_black_friday/pc/btn.png" alt="회원 선입장" /></a>

	<h4 class="blind">NOTICE</h4>
	<ul class="blind">
		<li>- 본 행사는 MLB 온라인 회원 전용 이벤트입니다.</li>
		<li>- 행사 기간동안 접속량이 많아 연결이 다소 지연될 수 있습니다.</li>
		<li>- 본 이벤트는 MLB 내부 사정에 의해 변경 또는 중단 될 수 있습니다.</li>
	</ul>
	<img src="http://static.mlb-korea.com/motioneye/2019/34_black_friday/pc/content.jpg" alt="" />
</div>
<!-------------------- //기획전 소스 END -------------------->

				</div>
			</section>
		</main>
	</div>
</div>
<script type="text/javascript">
 
	var evtNo = "${event.eventExt.evtNo }";
	
	function enterBlackFriday() {
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
			alert("로그인 후 이용 가능합니다. \n아직 회원이 아니라면 가입 후 이용해주세요.");
			//openLayerPopupForLogin();
			openLayerPopupForLogin('guestCounsel', '/event/EV201911190000125/GNRL');
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			blackFridayJoinEvent();
		</sec:authorize>
	}
	
	
	//입장
	function blackFridayJoinEvent() {
		window.document.location.href="/event/EV201911190000125/GNRL";
	}	
</script>
