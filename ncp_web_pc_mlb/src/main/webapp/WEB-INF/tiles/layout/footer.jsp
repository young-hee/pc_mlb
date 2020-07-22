<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 

<script>
function doFooterAsk(){
	if(getCookie("__NCP_LOCALE__") == "ko"){	
		jsLinkUrlPost('/helpdesk/bundleOrder/list');
	}else{
		jsLinkUrlPost('/helpdesk/bundleOrder/new');
		/* var bool = confirm(MESSAGES['common.js.header.msg']);
		
		if(bool){
			changeLocale("ko");
		} */
	}
}

function doFooterCenter(){
	if(getCookie("__NCP_LOCALE__") == "ko"){	
		jsLinkUrlPost('/helpdesk');
	}else{		
		var bool = confirm(MESSAGES['footer.js.locale.msg']);
		
		if(bool){
			changeLocale("ko");
		}
	}
}

function doFooterView(){	
	if(getCookie("__NCP_LOCALE__") == "ko"){	
		window.open("http://www.ftc.go.kr/bizCommPop.do?wrkr_no=2148609977&apv_perm_no=2011322016230200086");
	}else{		
		var bool = confirm(MESSAGES['footer.js.locale.msg']);
		
		if(bool){
			changeLocale("ko");
		}
	}
}

function goFamilyUrl(url){
	window.open(url);
}

</script>
<%-- <div class="sec_news_subs" id="newsLetterEmailDiv">
	<div class="in">
		<div class="box">
			<span class="tit"><spring:message code='footer.lbl.newsletter' /></span>뉴스레터 구독
			<span class="ipt">
				<input type="email" class="email" placeholder="E-MAIL 주소입력" id="newsLetterEmail">
				<button type="button" class="btnSch" id="newsLetterEmailSubmit">신청</button>
			</span>
		</div>
	</div>
</div>
 --%>
 
 <!-- 2019.01.15 추가 -->
<c:if test="${_locale eq 'ko' }"> 
<div class="sec_free_deily">
	<div class="msg"><spring:message code='footer.info.txt2' /></div>
</div>
</c:if>
<div class="foot">
	<div class="footer">
		<div class="menu">
			<ul class="list">
				<li><a href="/helpdesk/notice/list"><spring:message code='footer.lbl.menu1' /></a></li><%-- 공지사항 --%>
				<li><a href="#" onclick="layerPopupFindStoreMapBottom(); return false;"><spring:message code='footer.lbl.menu2' /></a></li><%-- 매장안내 --%>
				<li><a href="#" onclick="doFooterAsk(); return false;"><spring:message code='footer.lbl.menu3' /></a></li><%-- 단체구매문의 --%>		
				<li><a href="#" onclick="doFooterCenter(); return false;"><spring:message code='footer.lbl.menu4' /></a></li><%-- 고객센터 --%>
				<li>
					<c:choose>
						<c:when test='${_locale eq "ko" }'>
							<sec:authorize access="isAnonymous() or hasRole('ROLE_GUEST')">
							<a href="#" onclick="openLayerPopupForLogin('guestCounsel', '/helpdesk/csInquiry/new'); return false;"><spring:message code='footer.lbl.menu5' /></a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated() and not hasRole('ROLE_GUEST')">			
							<a href="/helpdesk/csInquiry/new"><spring:message code='footer.lbl.menu5' /></a>
							</sec:authorize>					
						</c:when>
						<c:otherwise>
							<a href="/helpdesk/csInquiry/new"><spring:message code='footer.lbl.menu5' /></a>
						</c:otherwise>
					</c:choose>					
				</li><%-- 1:1문의 --%>
			</ul>
		</div>
		<div class="company">
			<address class="address">
				<p><spring:message code='footer.lbl.addr1' /></p>
				<p><spring:message code='footer.lbl.addr2' /></p>
				<p><spring:message code='footer.lbl.addr3' /></p>
			</address>
			<div class="copy">
				<p><spring:message code='display.footer.msg1' /></p>
				<p><spring:message code='display.footer.msg2' /></p>
			</div>
		</div>

		<div class="infos">
			<div class="sns">
				<ul class="list">
					<li class="instagram"><a href="https://www.Instagram.com/mlbkorea/" target="_blank">INSTAGRAM</a></li>
					<li class="instakids"><a href="https://www.Instagram.com/mlbkids_kr/" target="_blank">INSTAGRAM Kids</a></li>
					<li class="youtube"><a href="https://www.youtube.com/user/MLBKOREA" target="_blank">YOUTUBE</a></li>
					<li class="weibo"><a href="https://www.weibo.com/mlbkorea" target="_blank">WEIBO</a></li>
					<li class="blog"><a href="https://blog.naver.com/mlbcrew" target="_blank">BLOG</a></li>					
				</ul>
			</div>
			<div class="cs">
				<strong class="tt">CS CENTER</strong> <span class="tel">080-807-0012</span>
			</div>
			<div class="tm">
				<p><spring:message code='display.footer.msg3' /></p>
				<p><spring:message code='display.footer.msg4' /></p>
				<p>E-MAIL : mlb@fnf.co.kr</p>
			</div>
		</div>

		<div class="bots">
			<c:if test='${_locale eq "ko" }'>
			<div class="link">
				<ul class="list">
					<li><a href="/common/information/termsAndConditions?agreementNo=2"><spring:message code='footer.lbl.menu6' /></a></li><%-- 개인정보처리방침 --%>
					<li><a href="/common/information/termsAndConditions?agreementNo=1"><spring:message code='footer.lbl.menu7' /></a></li><%-- 이용약관 --%>
					<li><a href="#" onclick="layerPopup.popupOpenNow('#bottomEmailDis'); return false;"><spring:message code='footer.lbl.menu8' /></a></li><%-- 이메일 무단 수집 거부 --%>
					<li><a href="/etc/sns/agreement/view">SNS 저작물 이용 동의서</a></li><%-- SNS 저작물 이용 동의서 --%>
				</ul>
				<%-- <span class="kcp"><a href="#" class="btn-kcp" onclick="openKcpWindow(); return false;"><spring:message code='footer.lbl.menu9' /></a></span> --%><%-- KCP 안전구매서비스 기업 --%>
			</div>
			</c:if>
			<div class="family">
				<span class="tt">FAMILY SITE</span>
				<div class="select-style01 d_select family-site">
					<button type="button" class="d_select_sel"><span>SELECT</span></button>
					<ul>
						<li><a href="javascript:;" onclick="goFamilyUrl('http://www.jardinperdu.com/');">JARDIN PERDU</a></li>
						<li><a href="javascript:;" onclick="goFamilyUrl('http://www.collected.co.kr/');">COLLECTED</a></li>
						<li><a href="javascript:;" onclick="goFamilyUrl('http://www.discovery-expedition.com');">Discovery</a></li>
						<li><a href="javascript:;" onclick="goFamilyUrl('http://www.stretch-angels.com/');">Stretch Angels</a></li>
					</ul>
				</div>
			</div>
		</div>

	</div>
</div>

<form name="shop_check" method="post" target="kcp_pop" action="https://admin.kcp.co.kr/Modules/escrow/kcp_pop.jsp?site_cd=A8A9S">
	<input type="hidden" name="site_cd" value="A8A9S">
</form>

<nav class="nav_bot">
	<div class="menu">
		<ul class="list">
			<li class="top"><a class="bt" href="javascript:;">TOP</a></li>
			<li class="recent">
				<a class="bt" href="javascript:;"><spring:message code='display.footer.msg5' /></a><%-- 최근본상품 --%>
			</li>
		</ul>
		<div class="box">
			<c:choose>
				<c:when test='${empty GNBlistTdGod}'>
					<div class="nodata">
						<p class="t"><spring:message code='display.footer.msg6' /></p><%-- 최근 본 상품이 없습니다. --%>
					</div>
				</c:when>
				<c:otherwise>
					<div class="tit"></div>
					<div class="swiper-container slide" id="recentGoodsSlide">
						<ul class="swiper-wrapper">
							<c:forEach var="data" items="${GNBlistTdGod }" varStatus="status">											
							<li class="swiper-slide">
								<span class="item-img">									
									<a href="${data.godUrl}">
										<img src="${_image}${data.imgUrl}/dims/resize/70x70" alt="${(empty data.godNm) ? data.sbj : data.godNm}" onerror='errorImgShow(this, "70");'>
									</a>
								</span>
							</li>
							</c:forEach>
						</ul>
					</div>
					<button type="button" class="btNav prev">PREV</button>
					<button type="button" class="btNav next">NEXT</button>
				</c:otherwise>
			</c:choose>					
		</div>
	</div>
</nav>
