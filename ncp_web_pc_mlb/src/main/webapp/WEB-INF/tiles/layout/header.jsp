<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@page import="com.plgrim.ncp.base.entities.datasource1.mbr.Mbr"%>
<%@page import="com.plgrim.ncp.biz.member.data.SecurityUserDetail"%>
<%@page import="com.plgrim.ncp.framework.commons.ContextService"%>
<%@page import="com.plgrim.ncp.framework.commons.IdGenService"%>
<%
	String mbrNm = "";
	String mbrTpCd = "";
	String mbrBrthdy= "";
	String mbrSexSectCd= "";
	String mbrId= "";
	String mbrOnlineId= "";
	String secession = (String)request.getAttribute("secession");
	if(secession == null || secession == ""){
	    if(ContextService.hasRole()){
			SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
			Mbr mbr = userDetail.getMbr();
			mbrNm = mbr.getMbrNm();
			mbrTpCd = mbr.getMbrTpCd();			
			mbrBrthdy = mbr.getMbrBrthdy();
			mbrSexSectCd = mbr.getMbrSexSectCd();
			mbrId  = IdGenService.generateMD5(mbr.getErpCstmrNo());
			mbrOnlineId = mbr.getMbrId();
	    }
	}
%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
<c:set var="mbrNm" value="<%=mbrNm %>"/>
<c:set var="mbrTpCd" value="<%=mbrTpCd %>"/>
<c:set var="mbrBrthdy" value="<%=mbrBrthdy %>"/>
<c:set var="mbrSexSectCd" value="<%=mbrSexSectCd %>"/>
<c:set var="mbrId" value="<%=mbrId %>"/>
<c:set var="mbrOnlineId" value="<%=mbrOnlineId %>"/>

<script>
	window.cremaAsyncInit = function () {
		var mbrOnlineId = ("" == "${mbrOnlineId}") ? null : "${mbrOnlineId}";
		var mbrNm = ("" == "${mbrNm}") ? null : "${mbrNm}";
		crema.init(mbrOnlineId, mbrNm);
	};
</script>
<script>
	var gnbMiniCart = {
		load : function(){
			$.ajax({
				 type:"post"
				,url:"/cart/goods/gnblist.json"
				,data : {}
				,dataType: "json"
				,async : true
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				}
		       ,success: function(data){		 
			       	if(data.cart != null){
		    			var bsk_ttl_cnt = data.cart.length;
		    			if(bsk_ttl_cnt > 99){
		    				$("#gnbCartCount").html("99+");
		    			}else{
		    				$("#gnbCartCount").html(bsk_ttl_cnt);
		    			}
			       	}else{
			       		$("#gnbCartCount").html("0");
			       	}
		       },
		       error: function() {
		
		       }
		   });	
		}
	}
	
	var gnbMiniMyinfo = {
		load : function(loginFlag) {
			var logoutUrl = '${_env.getProperty("ncp_web_pc_mlb.logout.url") }';
			var html = "";
			html += "<ul class=\"info\">";
			if(loginFlag == false) {
				html += "	<li class=\"login\"><a href=\"#\" onclick=\"doGNBLogin(); return false;\">" + MESSAGES['common.js.header.head.txt5'] + "</a></li>";
				html += "	<li class=\"join\"><a href=\"#\" onclick=\"doGNBJoin(); return false;\">" + MESSAGES['common.js.header.head.txt6'] + "</a></li>";
			}
			else {
				html += "	<li class=\"logout\"><a href=\"#\" onClick=\"doLogout('" + logoutUrl + "'); return false;\">" + MESSAGES['common.js.header.head.txt7'] + "</a></li>";
			}
			html += "	<li class=\"mypage\"><a href=\"#\" onclick=\"doGNBMypage(); return false;\">" + MESSAGES['common.js.header.head.txt8'] + "</a></li>";
			html += "</ul>";

			$("#btn_head_myinfo .info").remove();
			$("#btn_head_myinfo a").after(html);
		}
	}
	
	$(document).ready(function() {

		var cookie = getCookie2("__NCP_LOCALE__");
		if(!cookie) {
			setCookie2("__NCP_LOCALE__", "ko", 360);
		}
		
		gnbMiniCart.load();
		
		<sec:authorize access="isAuthenticated() and not hasRole('ROLE_GUEST')">
		
		$.ajax({
			 type:"post"
			,url:"/mypage/wishlist/gnbListCount.json"
			,data : {}
			,dataType: "json"
			,async : true
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			}
			,success: function(data){
		       	if(data.gnbListCount != null){
	    			var bsk_ttl_cnt = data.gnbListCount.length;
	    			if(bsk_ttl_cnt > 99){
	    				$("#gnbWishCount").html("99+");
	    			}else{
	    				$("#gnbWishCount").html(bsk_ttl_cnt);
	    			}
		       	}
			},
			error: function() {

			}
		});
		
		</sec:authorize>
	});
</script>
<div class="head">

	<c:if test="${!empty GNBCommonBanner.topBnr}">
	<section class="flexBanner" id="flexBanner" style="background-color: ${GNBCommonBanner.topBnr[0].imgBcrnColorCd};">
		<div class="in">
			<div class="ctn">
				<div class="banner"><a href="${GNBCommonBanner.topBnr[0].conttCnncUrl}"><img src="${_image}${GNBCommonBanner.topBnr[0].imgFileUrl}/${GNBCommonBanner.topBnr[0].imgFileNm}/dims/resize/1920" alt="${GNBCommonBanner.topBnr[0].imgAltrtvCont}"></a></div>
			</div>
			<div class="bts">
				<button class="btnClose" onclick="controlCookie2('topBnr')">닫기</button>
				<div class="msg"><spring:message code='common.header.banner.txt1' text='오늘 그만보기'/></div>
			</div>
		</div>
	</section>
	</c:if>
<script type="text/javascript">
var cookie = getCookie2("topBnr");
if("" == cookie) {
	$("#flexBanner").show();
}else{
	$("#flexBanner").hide();
}

function controlCookie2( name ) {
	setCookie2( name, "true", 1 );
}

//24시간 기준 쿠키 설정하기  
//expiredays 후의 클릭한 시간까지 쿠키 설정  
function setCookie2( name, value, expiredays ) {   
	var todayDate = new Date();   
	todayDate.setDate( todayDate.getDate() + expiredays );
	todayDate.setHours(00);
	todayDate.setMinutes(00);
	todayDate.setSeconds(00);
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"   
}  

//쿠키 가져오기  
function getCookie2( name ) {  
   var nameOfCookie = name + "=";  
   var x = 0;  
   while ( x <= document.cookie.length )  
   {  
       var y = (x+nameOfCookie.length);  
       if ( document.cookie.substring( x, y ) == nameOfCookie ) {  
           if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )  
               endOfCookie = document.cookie.length;  
           return unescape( document.cookie.substring( y, endOfCookie ) );  
       }  
       x = document.cookie.indexOf( " ", x ) + 1;  
       if ( x == 0 )  
           break;  
   }  
   return "";  
}

function goGNBCart(){
	
	if(getCookie("__NCP_LOCALE__") == "ko"){	
		jsLinkUrlPost("/cart/goods/list");
	}else{
		var bool = confirm(MESSAGES['common.js.header.msg']);
		
		if(bool){
			changeLocale("ko");
		}
	}
}

function goGNBwish(){
	
	if(getCookie("__NCP_LOCALE__") == "ko"){	
		jsLinkUrlPost("/mypage/wishlist/list");
	}else{
		var bool = confirm(MESSAGES['common.js.header.msg']);
		
		if(bool){
			changeLocale("ko");
		}
	}
}

var logoutUrl = '${_env.getProperty("ncp_web_pc_mlb.logout.url") }';
function makeGNBMyinfo(loginFlag) {
	var html = "";
	html += "<ul class=\"info\">";
	if(loginFlag == false) {
		html += "	<li class=\"login\"><a href=\"#\" onclick=\"doGNBLogin(); return false;\">" + MESSAGES['common.js.header.head.txt5'] + "</a></li>";
		html += "	<li class=\"join\"><a href=\"#\" onclick=\"doGNBJoin(); return false;\">" + MESSAGES['common.js.header.head.txt6'] + "</a></li>";
	}
	else {
		html += "	<li class=\"logout\"><a href=\"#\" onClick=\"doLogout('" + logoutUrl + "'); return false;\">" + MESSAGES['common.js.header.head.txt7'] + "</a></li>";
	}
	html += "	<li class=\"mypage\"><a href=\"#\" onclick=\"doGNBMypage(); return false;\">" + MESSAGES['common.js.header.head.txt8'] + "</a></li>";
	html += "</ul>";

	$("#btn_head_myinfo .info").remove();
	$("#btn_head_myinfo a").after(html);
}
</script>
	<header class="header">
		<div class="btnGnb"><a class="bt" href="javascript:;"><spring:message code='common.header.head.txt1' text='메뉴'/></a></div>
		<div class="selLang" id="btn_selLang">
			<ul class="list">
				<c:choose>
					<c:when test='${_locale eq "ko"}'>
				<li class="kr"><a href="#" onclick="changeLocale('ko'); return false;"><span>KR</span></a></li>
				<li class="en"><a href="#" onclick="changeLocale('en'); return false;"><span>EN</span></a></li>
				<li class="cn"><a href="#" onclick="changeLocale('zh'); return false;"><span>CN</span></a></li>
					</c:when>
					<c:when test='${_locale eq "en"}'>
				<li class="en"><a href="#" onclick="changeLocale('en'); return false;"><span>EN</span></a></li>
				<li class="kr"><a href="#" onclick="changeLocale('ko'); return false;"><span>KR</span></a></li>				
				<li class="cn"><a href="#" onclick="changeLocale('zh'); return false;"><span>CN</span></a></li>	
					</c:when>
					<c:otherwise>
				<li class="cn"><a href="#" onclick="changeLocale('zh'); return false;"><span>CN</span></a></li>	
				<li class="en"><a href="#" onclick="changeLocale('en'); return false;"><span>EN</span></a></li>
				<li class="kr"><a href="#" onclick="changeLocale('ko'); return false;"><span>KR</span></a></li>					
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		<h1 class="logo"><a href="/">MLB</a></h1>
		<div class="nav">
			<ul class="list">
				<li class="sch"><a href="javascript:;" id="btn_gb_sch_box"><spring:message code='common.header.head.txt2' text='검색'/></a></li>
				<li class="cart"><a href="#" onclick="goGNBCart(); return false;"><spring:message code='common.header.head.txt3' text='장바구니'/> <em class="n" id="gnbCartCount">0</em></a></li>
				<li class="wish"><a href="#" onclick="goGNBwish(); return false;">wish <em class="n" id="gnbWishCount">0</em></a></li>
				<li class="mem" id="btn_head_myinfo"><a href="javascript:;"><spring:message code='common.header.head.txt4' text='회원'/></a>
					<ul class="info">
						<sec:authorize access="isAnonymous() or hasRole('ROLE_GUEST')">
						<li class="login"><a href='#' onclick="doGNBLogin(); return false;"><spring:message code='common.header.head.txt5' text='로그인'/></a></li>
						<li class="join"><a href="#" onclick="doGNBJoin(); return false;"><spring:message code='common.header.head.txt6' text='회원가입'/></a></li>
						</sec:authorize>
						<sec:authorize access="isAuthenticated() and not hasRole('ROLE_GUEST')">						
						<li class="logout"><a href='#' onClick="doLogout('${_env.getProperty("ncp_web_pc_mlb.logout.url") }'); return false;"><spring:message code='common.header.head.txt7' text='로그아웃'/></a></li>						
						</sec:authorize>						
						<li class="mypage"><a href="#" onclick="doGNBMypage(); return false;"><spring:message code='common.header.head.txt8' text='마이페이지'/></a></li>
					</ul>
				</li>
			</ul>
		</div>
		
		<!-- MLB X 디즈니 콜라보 -->
		<div class="disney"><a href="/special/2001"><img src="http://static.mlb-korea.com/motioneye/2019/40_disney/pc/disney1.png" alt="" class="crt" /><img src="http://static.mlb-korea.com/motioneye/2019/40_disney/pc/disney2.png" alt="" /></a></div>
		<style type="text/css">
		.disney{position:absolute; left:149px; top:0}
		.disney img.crt{position:absolute; left:0; top:3px; animation:disney 1.7s infinite}
		@keyframes disney{
			0%{transform:rotate(-10deg)}
			50%{transform:rotate(10deg)}
			100%{transform:rotate(-10deg)}
		}
		</style>
		<!-- //MLB X 디즈니 콜라보 -->
		
	</header>
</div>



<div class="top_cate_box">

	<div class="menu">
		<ul class="list cate">
			<%--
			<li><a data-pan="top_cate_pan_1" href="javascript:cateLink('MBMA01', '1', 'GNRL_CTGRY', 'MBMA01');">MEN</a></li>
			<li><a data-pan="top_cate_pan_2" href="javascript:cateLink('MBMA02', '1', 'GNRL_CTGRY', 'MBMA02');">WOMEN</a></li>
			<li><a data-pan="top_cate_pan_3" href="javascript:cateLink('MBMA03', '1', 'GNRL_CTGRY', 'MBMA03');">CAP</a></li>
			<li><a data-pan="top_cate_pan_4" href="javascript:cateLink('MBMA04', '1', 'GNRL_CTGRY', 'MBMA04');">ACC</a></li>
			<li><a data-pan="top_cate_pan_5" href="javascript:cateLink('MBMA05', '1', 'GNRL_CTGRY', 'MBMA05');">KIDS</a></li>

			<!-- 1903 아울렛 : E -->
			<li><a data-pan="top_cate_pan_6" href="javascript:cateLink('MBMA06', '1', 'OTLT_CTGRY', 'MBMA06', 'MBMA06');">OUTLET</a></li>
			<!-- 1903 아울렛 : E -->
			--%>
			<c:forEach var="gnbCate1" items="${GNBCategoryList.category1}" varStatus="status">
			<li><a data-pan="top_cate_pan_${status.index+1 }" href="javascript:cateLink('${gnbCate1.ctgryInfo.dspCtgryNo}', '1', '${gnbCate1.ctgryInfo.ctgrySectCd}', '${gnbCate1.ctgryInfo.dspCtgryNo}');">${gnbCate1.ctgryInfo.dspCtgryNm }</a></li>
			</c:forEach>
		</ul>
		<ul class="list spc">
			<li><a href="/lookbook/lookbookList">LOOKBOOK</a></li>
			<li><a href="/culture/cultureList">CULTURE</a></li>
			<li><a href="/olapic/view?olapicForceRender">#STYLE in SNS</a></li>
		</ul>	
	</div>

	<c:forEach var="gnbCate1" items="${GNBCategoryList.category1}" varStatus="status">
	<div class="pan top_cate_pan_${status.index+1 }" id="top_cate_pan_${status.index+1 }">
		<c:if test="${status.index+1 ne 8}">
		<div class="cate">
			<ul class="list">
				<c:forEach var="gnbCate2" items="${GNBCategoryList.category2}" varStatus="status2">
					<c:if test='${gnbCate2.ctgryInfo.upperDspCtgryNo eq  gnbCate1.ctgryInfo.dspCtgryNo and gnbCate2.ctgryInfo.leafCtgryYn eq "Y" and !(gnbCate2.ctgryInfo.dspCtgryNo eq "MBMA10A01" or gnbCate2.ctgryInfo.dspCtgryNo eq "MBMA10A02")}'>
					<li><a href="javascript:cateLink('${gnbCate2.ctgryInfo.dspCtgryNo}', '2', '${gnbCate2.ctgryInfo.ctgrySectCd}', '${gnbCate2.ctgryInfo.upperDspCtgryNo}', '${gnbCate2.ctgryInfo.dspCtgryNo}');">${gnbCate2.ctgryInfo.dspCtgryNm }</a></li>
					</c:if>
				</c:forEach>
				<!-- 캡가이드 하드코딩 추가 : S -->
				<c:if test="${gnbCate1.ctgryInfo.dspCtgryNm eq 'CAP'}">
					<li style="margin-top:30px"><a href="/special/387">CAP GUIDE</a></li>
				</c:if>
				<!-- 캡가이드 하드코딩 추가 : S -->
			</ul>
		</div>
		</c:if>		
		<div class="depth">
			<ul class="list">
				<c:forEach var="gnbCate2" items="${GNBCategoryList.category2}" varStatus="status21">
					<c:if test='${(gnbCate2.ctgryInfo.upperDspCtgryNo eq  gnbCate1.ctgryInfo.dspCtgryNo and gnbCate2.ctgryInfo.leafCtgryYn ne "Y") or (gnbCate2.ctgryInfo.upperDspCtgryNo eq  gnbCate1.ctgryInfo.dspCtgryNo and (gnbCate2.ctgryInfo.dspCtgryNo eq "MBMA10A01" or gnbCate2.ctgryInfo.dspCtgryNo eq "MBMA10A02"))}'>
						<li>
							<a href="javascript:cateLink('${gnbCate2.ctgryInfo.dspCtgryNo}', '2', '${gnbCate2.ctgryInfo.ctgrySectCd}', '${gnbCate2.ctgryInfo.upperDspCtgryNo}', '${gnbCate2.ctgryInfo.dspCtgryNo}');">${gnbCate2.ctgryInfo.dspCtgryNm }</a>
							<ul>
								<c:forEach var="gnbCate3" items="${GNBCategoryList.category3}" varStatus="status3">
									<c:if test='${gnbCate3.ctgryInfo.upperDspCtgryNo eq  gnbCate2.ctgryInfo.dspCtgryNo}'>
										<li><a href="javascript:cateLink('${gnbCate3.ctgryInfo.dspCtgryNo}', '3', '${gnbCate3.ctgryInfo.ctgrySectCd}', '${gnbCate3.dspCtgryNo1}', '${gnbCate3.ctgryInfo.upperDspCtgryNo}');">${gnbCate3.ctgryInfo.dspCtgryNm }</a></li>
									</c:if>
								</c:forEach>								
							</ul>
						</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<c:choose>
			<c:when test="${status.index eq 0 and !empty GNBCommonBanner.menLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.menLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.menLayerBnr[0].imgFileUrl}/${GNBCommonBanner.menLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.menLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 1 and !empty GNBCommonBanner.womenLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.womenLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.womenLayerBnr[0].imgFileUrl}/${GNBCommonBanner.womenLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.womenLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 2 and !empty GNBCommonBanner.capLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.capLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.capLayerBnr[0].imgFileUrl}/${GNBCommonBanner.capLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.capLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 3 and !empty GNBCommonBanner.shoesLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.shoesLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.shoesLayerBnr[0].imgFileUrl}/${GNBCommonBanner.shoesLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.shoesLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 4 and !empty GNBCommonBanner.accLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.accLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.accLayerBnr[0].imgFileUrl}/${GNBCommonBanner.accLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.accLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 5 and !empty GNBCommonBanner.kidsLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.kidsLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.kidsLayerBnr[0].imgFileUrl}/${GNBCommonBanner.kidsLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.kidsLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
			<c:when test="${status.index eq 6 and !empty GNBCommonBanner.outletLayerBnr[0]}">
				<div class="banner">
					<a href="${GNBCommonBanner.outletLayerBnr[0].conttCnncUrl}"><img class="img" src="${_image}${GNBCommonBanner.outletLayerBnr[0].imgFileUrl}/${GNBCommonBanner.outletLayerBnr[0].imgFileNm}/dims/resize/400x268" alt="${GNBCommonBanner.outletLayerBnr[0].imgAltrtvCont}"></a>
				</div>
			</c:when>
		</c:choose>
	</div>
	</c:forEach>
</div>

<!-- 검색영역 -->
<div class="gb_sch_box">
	<div class="in">
		<form:form class="box" action="/display/search" method="get" onsubmit="searchTextCheck(this);">
			<span class="tit">SEARCH</span>
			<span class="keyword"><input type="text" name="searchText" class="key" value="" autocomplete="off"></span>
			<button type="submit" class="btnSch">검색</button>
		</form:form>
	</div>
</div>

<script type="text/javascript">
	//검색  
	function searchTextCheck(that) {
		
		var $searchInput = $(that).find("input[name='searchText']");
		var searchText = $searchInput.val();
		var altMsg = "<spring:message code='display.search.result.alert' />";
		
		if(!$.trim(searchText).length) {
			
			alert(altMsg);
			$searchInput.focus();
			event.preventDefault();
			
		}
		
		return true;
		
	}
</script>

<form id="lnbDspCtgryForm" method="get" >
  <input type="hidden" name="dspCtgryNo" id="dspCtgryNo" value="${dspCtgryScFrDTO.dspCtgryNo }">
  <input type="hidden" name="currentCtgryDpthCd" id="currentCtgryDpthCd" value="${dspCtgryScFrDTO.currentCtgryDpthCd }">
  <input type="hidden" name="ctgrySectCd" id="ctgrySectCd" value="${dspCtgryScFrDTO.ctgrySectCd }">
  <input type="hidden" name="ctgryNoDpth1" id="ctgryNoDpth1" value="${dspCtgryScFrDTO.ctgryNoDpth1 }">
  <input type="hidden" name="ctgryNoDpth2" id="ctgryNoDpth2" value="${dspCtgryScFrDTO.ctgryNoDpth2 }">
  <input type="hidden" name="ctgryNoDpth3" id="ctgryNoDpth3" value="${dspCtgryScFrDTO.ctgryNoDpth3 }">
</form>

<form id="realForm" method="get" ></form>

<%@ include file="/WEB-INF/views/main/include/recoPick.jsp"%>