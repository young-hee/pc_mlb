<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script>
function testPopLogin(){
	openLayerPopupForLogin('guestOrder');
}

function testPopLogin2(){
	openLayerPopupForLogin('guestCounsel');
}

function testPopLogin3(){
	openLayerPopupForLogin();
}

</script>
<div class="contain mn home" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<!-- Visual 슬라이드  -->
				<%@ include file="/static/html/mn/inc_visual.jsp" %>

				<!-- New Arrival -->
				<%@ include file="/static/html/mn/inc_arrival.jsp" %>

				<!-- New Culture -->
				<%@ include file="/static/html/mn/inc_culture.jsp" %>

				<!-- New Arrival -->
				<%@ include file="/static/html/mn/inc_olapic.jsp" %>

				<!-- Shop  -->
				<%@ include file="/static/html/mn/inc_shop.jsp" %>

				<!-- LOOKBOOK  -->
				<%@ include file="/static/html/mn/inc_look.jsp" %>

				<!-- RANKING  -->
				<%@ include file="/static/html/mn/inc_rank.jsp" %>
				
				<section class="mds-section rank">
					<div class="hdt"><span class="tit">TEST</span></div>
					<div>
						<a href="#" onclick="testPopLogin(); return false;">상품상세 popup login</a>
					</div>
					<div>
						<a href="#" onclick="testPopLogin3(); return false;">popup login</a>
					</div>
					<div>
						<a href="#" onclick="testPopLogin2(); return false;">고객센터 popup login</a>
					</div>
					<div>
						<a href="#" onClick="doLogout('${_env.getProperty("ncp_web_pc_mlb.logout.url") }'); return false;">로그아웃</a>
					</div>
					
				</section>

			</main>
			
		</div>
	</div>