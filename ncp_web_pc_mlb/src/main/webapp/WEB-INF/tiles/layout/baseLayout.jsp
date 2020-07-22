<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<!DOCTYPE html>
<html lang="${_locale }">
<head>	
	<META http-equiv="Expires" content="0">
	<META http-equiv="Pragma" content="no-cache">
	<META http-equiv="Cache-Control" content="no-cache">

	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta name="_csrf.parameter" content="${_csrf.parameterName}" />
    <meta property="og:url" content="<c:if test="${!empty og_url}"><c:choose>
    									<c:when test="${fn:contains(og_url, '?')}">
    									${og_url}&language=${_locale}
    									</c:when>
    									<c:otherwise>
    									${og_url}?language=${_locale}
    									</c:otherwise>
    								</c:choose></c:if>" />
	<meta property="og:image" name="og_image" content="<ncp:img path='${og_image }'/>"/>
	<meta property="og:image:secure_url" name="og_image_secure_url" content="<ncp:img path='${og_image }'/>"/>
	<meta property="og:title" name="og_title" content='<c:out value="${og_title }"/>'/>
	<meta property="og:type" name="og_type" content="<c:out value="${og_type }"/>" />	
	<meta property="og:description" name="og_desc" content='<c:out value="${og_desc }"/>' />
	
	<meta name="twitter:card" content="summary">
	<meta name="twitter:url" content="<c:if test="${!empty og_url}"><c:choose>
    									<c:when test="${fn:contains(og_url, '?')}">
    									${og_url}&language=${_locale}
    									</c:when>
    									<c:otherwise>
    									${og_url}?language=${_locale}
    									</c:otherwise>
    								</c:choose></c:if>" />
	<meta name="twitter:title" content='<c:out value="${og_title }"/>'>
	<meta name="twitter:description" content='<c:out value="${og_desc }"/>'>
	<meta name="twitter:image" content="<ncp:img path='${og_image }'/>">

	<meta name="naver-site-verification" content="ea30984d57f973090301c054f3c9c3709703e19f"/>
	
	<meta name="google-site-verification" content="CCl-0mtJJBsSx8-o9NXYvWQ_qVresY_UMqIkuOphfcg" />

	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta charset="utf-8">
	<c:choose>
		<c:when test="${_locale == 'zh'}">
			<title>MLB</title>
			<meta name="description" content="MLB" />
			<meta name="keywords" content="MLB" />
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${!empty seo_title}">
					<title><c:out value="${seo_title}"/></title>
					<meta name="description" content='<c:out value="${seo_desc}"/>' />
				</c:when>
				<c:otherwise>
					<title>MLB</title>
					<meta name="description" content="Young Generation의 패션 아이콘. 스트릿 캐주얼 브랜드 MLB 공식 온라인 스토어" />
				</c:otherwise>
			</c:choose>
			<meta name="keywords" content="MLB KOREA,엠엘비,엠엘비 코리아" />
		</c:otherwise>
	</c:choose>
	
	<link rel="shortcut icon" type="image/x-icon" href="${_resourceURL}static/favicon/favicon.ico" />
	<link rel="icon" type="image/x-icon" href="${_resourceURL}static/favicon/favicon.ico" />
	
	<%
		java.util.Date now = new java.util.Date();
		String yy = Integer.toString( now.getYear()+1900 ) ;
		String mm = Integer.toString( now.getMonth() ) ;
		String dd = Integer.toString( now.getDate() ) ;
		String hh = Integer.toString( now.getHours() ) ;
		String nn = Integer.toString( now.getMinutes() ) ;
		String ss = Integer.toString( now.getSeconds() ) ;
		String ver_css =  mm +"-"+ dd +"-"+ hh +"-"+ nn +"-"+ ss ;
	%>
	
	<link href="${_resourceURL}static/css/swiper.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<!-- <link href="${_resourceURL}static/css/jquery-ui.1.12.1.css?v=<%=ver_css%>" rel="stylesheet" type="text/css"> -->
	<link href="${_resourceURL}static/css/bs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/cm.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/ly.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/mn.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/dp.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/pd.css?v=<%=ver_css%>" rel="stylesheet" type="text/css">
	<link href="${_resourceURL}static/css/od.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/my.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/mb.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/se.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/ev.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/cs.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	<link href="${_resourceURL}static/css/et.css?v=<%=ver_css%>" rel="stylesheet" type="text/css" />
	
	<script src="${_resourceURL}static/js/jquery-3.3.1.js"></script>
	<script src="${_resourceURL}static/js/jquery-ui.min.js"></script>
	<script src="${_resourceURL}static/js/min/jsrender-0.9.90.min.js"></script>	
	<script src="${_resourceURL}static/js/min/masonry.pkgd.min.js"></script>
	<script src="${_resourceURL}static/js/validator.js"></script>	
	<script src="${_resourceURL}static/js/min/masonry.pkgd.min.js?v=<%=ver_css%>"></script>
	<script src="${_resourceURL}static/js/min/imagesloaded.pkgd.min.js?v=<%=ver_css%>"></script>	
	<!-- <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> -->
	<script src="${_resourceURL}static/js/sns.js?v=<%=ver_css%>"></script>
	<script src="${_resourceURL}static/js/moment.js?v=<%=ver_css%>"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBvRMB27X0JzAxjAUs26Q32tEfw-E0NUng" async defer></script>
	
	<!-- <script src="/static/js/jquery-migrate-1.4.1.js"></script> -->
	<!-- <script src="/static/js/jquery-ui-1.12.1.js"></script> -->
	<script src="https://player.vimeo.com/api/player.js"></script>
	<script src="${_resourceURL}static/js/swiper.min.js"></script>	
	<script src="${_resourceURL}static/js/common.js?v=<%=ver_css%>"></script>
	<script src="${_resourceURL}static/js/commonFunction.js?v=<%=ver_css%>"></script>	
	
	<script type="text/javascript" src="${_resourceURL}static/js/member/login.js?v=${_version}"></script>
	<script type="text/javascript" src="${_resourceURL}static/js/naver/naverLogin_implicit-1.0.2-min.js" charset="utf-8"></script>
	<script type="text/javascript" >var YOUR_CLIENT_ID = '<ncp:prop key="ncp_base.naver.login.mlb.clientid"/>';</script>	
	<!-- <script src="https://bizmessage.kakao.com/chat/includeScript"></script> -->
	
	<script>
	
	(function(w) {
		// global variables : for user objects
		w.BASE = {
			imageUrl: "${_env.getProperty('ncp.image.url')}",
			webResourceUrl: "${_env.getProperty('ncp.web.resource.url')}",
			locale: "${_locale}",

		    csrf: {
		    	header_key: "X-CSRF-TOKEN",
		        key: "<c:out value="${_csrf.parameterName}"/>",
		        value: "<c:out value="${_csrf.token}"/>"
		    }
		};
		
		w.USER = {
			mbrNo: "${_user.mbr.mbrNo}",
			mbrId: "${_user.mbr.mbrId}",
			mbrNm: "${_user.mbr.mbrNm}",
			mbrSexSectCd: "${_user.mbr.mbrSexSectCd}",
			mbrEmail: "${_user.mbr.mbrEmail}",
		};
		
		w.MESSAGES = {};
		w.JSRENDER_HELPER = {
			messages : MESSAGES
		};
		
	})(window);	
	</script>
	
	<script type="text/javascript" src="/javascript/message/common_${pageContext.response.locale.language}.js?v=${_version}"></script>
	<script type="text/javascript" src="/javascript/message/footer_${pageContext.response.locale.language}.js?v=${_version}"></script>
	<script type="text/javascript" src="/javascript/message/member_${pageContext.response.locale.language}.js?v=${_version}"></script>
	
	<script>
	
	$(document).ready(function(){
		
		var reqUri = document.location.href;
		
		if(reqUri.indexOf("/cart") > -1
				|| reqUri.indexOf("/order") > -1){
			$("#newsLetterEmailDiv").hide();
		}
		
		if(reqUri.indexOf("##") > -1){
			var rUrl = reqUri.split("##");			
			
			var exeFunc = new Function('', rUrl[1] + '()');
			exeFunc();			
		}
		
		$("#newsLetterEmailSubmit").click(function(){
			var emailPattern = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d)|(([a-z]|\d)([a-z]|\d|-|\.|_|~)*([a-z]|\d)))\.)+(([a-z])|(([a-z])([a-z]|\d|-|\.|_|~)*([a-z])))\.?$/i;
			if($("#newsLetterEmail").val() == ""){
				alert(MESSAGES['common.js.email.txt1']);
				$("#newsLetterEmail").focus();
				return;
			}
			if(emailPattern.test($("#newsLetterEmail").val()) == false){
				alert(MESSAGES['common.js.email.txt1']);
				$("#newsLetterEmail").focus();
				return;
			}
			
			layerPopup.popupOpenNow('#bottomEmailPop'); 		
			
		})
		
		// 공유하기 버튼 이벤트 추가 (20181207_ds)
		$("#ulShare").find('li').click(function() {
			// 페이스북
			if($(this).attr('class').indexOf("facebook") !=-1) {
				jsShareSns('facebook');
			}
			// 트위터
			else if($(this).attr('class').indexOf("twitter") !=-1) {
				jsShareSns('twitter', $("meta[name='og_title']").attr("content"));
			}
			// url 복사
			else if($(this).attr('class').indexOf("url") !=-1){
		    	var trb= location.href;
				if(trb.indexOf("language=") === -1) {
					trb = trb.indexOf("?") > 0 ? trb + "&language=" + BASE.locale : trb + "?language=" + BASE.locale;
				}
		    	var IE=(document.all)?true:false;
		    	if (IE) {
		    	if(confirm(MESSAGES['common.js.track.txt1']))
		    	window.clipboardData.setData("Text", trb);
		    	} else {
		    	temp = prompt(MESSAGES['common.js.track.txt2'], trb);
		    	}
			 }	
			//  네이버 라인
			else if($(this).attr('class').indexOf("line") !=-1) {
				jsShareSns('naverline');
			}
			else if($(this).attr('class').indexOf("weibo") !=-1) {
				jsShareSns('weibo');
			}
			else if($(this).attr('class').indexOf("qq") !=-1) {
				jsShareSns('qq');
			}
		});
	})
	
	//뉴스레터 신청
	function agreeEmailNewsLetter(){
		
		
		$.ajax({
			type : "POST",
			url : "/member/guest/newsLetterEmail.json",
			data : {'${_csrf.parameterName}' : '${_csrf.token}', 'applcntEmail' : $("#newsLetterEmail").val() },
			beforeSend: function (request)
			{
				var csrfToken = $("meta[name='_csrf']").attr("content");
				var csrfName = $("meta[name='_csrf_header']").attr("content");
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(args) {
				commonFnc.unblock();
				//alertLayer("뉴스레터 구독신청이 완료되었습니다.\n감사합니다.");
				alert(MESSAGES['common.js.newsletter.end']);
				layerPopup.popupCloseNow('#bottomEmailPop'); 		
			},
			error : function(e) {
				commonFnc.unblock();
				chkBtn = true;
				alertLayer(MESSAGES['common.js.error.msg1']);
			}
		});
	}
	function doGNBMypage(){
		if(getCookie("__NCP_LOCALE__") == "ko"){	
			//jsLinkUrlPost('/mypage/view');
			location.href = "/mypage/view";
		}else{
			var bool = confirm(MESSAGES['common.js.header.msg']);
			
			if(bool){
				changeLocale("ko");
			}
		}
	}
	
	function doGNBJoin(){
		if(getCookie("__NCP_LOCALE__") == "ko"){	
			jsLinkUrlPost('/member/join/view');
		}else{
			var bool = confirm(MESSAGES['common.js.header.msg']);
			
			if(bool){
				changeLocale("ko");
			}
		}
	}
	
	function doGNBLogin(){
		if(getCookie("__NCP_LOCALE__") == "ko"){	
			jsLinkUrlPost('${_env.getProperty("ncp_web_pc_mlb.login.url")}');
		}else{
			var bool = confirm(MESSAGES['common.js.header.msg']);
			
			if(bool){
				changeLocale("ko");
			}
		}
	}
	
	// 로그아웃 처리(POST로 해야함)
	function doLogout(url) {
	    var strParams = null;
	    strParams = {
	        '${_csrf.parameterName}' : '${_csrf.token}'
	        ,'userAction':'logout'
	    };
	    jsLinkUrlPost(url, strParams);
	}
	// 로그인 페이지로 이동
	function memberLogin(targetUrl) {
	 	if(targetUrl == undefined || targetUrl == "") {
	 		targetUrl = document.URL;
	 	}
		var substr = document.URL.indexOf('?');
		
		if(targetUrl.indexOf("loginTarget") > -1) {
			location.href = "/member/login/view?" + targetUrl.substring(targetUrl.indexOf("loginTarget"));
		}
		else {
			location.href = "/member/login/view?loginTarget=" + encodeURI(targetUrl.substring(0,substr)) + encodeURIComponent(targetUrl.substring(substr));	
		}
	}
	
	function callbackLinkNaverAfter(flag) {
		if(flag) {
			layerPopup.popupOpenNow("#naverConnectCompletePopup");
		}
		else{
			layerPopup.popupOpenNow("#naverConnectFailPopup");
		}
	}
	
	function closeNaverConnectCompletePopup(flag) {
		if(flag) {
			closeCommonLayerPopup("naverConnectCompletePopup");
			
			if($("#loginFormByNaver").find("input[name=loginTarget]").val() != ""){
				movePage($("#loginFormByNaver").find("input[name=loginTarget]").val());
			}else{
				window.location.reload();
			}				
		}
		else {
			closeCommonLayerPopup("naverConnectFailPopup");
			
			if($("#loginFormByNaver").find("input[name=loginTarget]").val() != ""){
				movePage($("#loginFormByNaver").find("input[name=loginTarget]").val());
			}else{
				movePage('/main/mall/view');
			}			
		}
	}
	
	/**
	 * 공통 레이어팝업 열기
	 *
	 * title : 상단에 들어갈 제목
	 * msg : 중단에 들어갈 문구
	 * btnNm : 하단의 버튼에 들어갈 버튼명
	 * layerId : popup할 레이어ID
	 */
	function openCommonLayerPopup(title, msg, btnNm, layerId, layerClass) {
		$("article#" + layerId).find("h2").html(title);
		$("article#" + layerId).find(".layer-txt").html(msg);
		$("article#" + layerId).find(".btn-style02").html(btnNm);
		$("article#" + layerId).attr('class', layerClass);

		layerPopup.popupOpenNow("#" + layerId);
	}
	
	function closeCommonLayerPopup(layerId) {
		$("#" + layerId).find("button.d_layer_close").trigger("click");
	}
	
	/**
	 * 공통 confirm 레이어팝업 열기
	 *
	 * title : 상단에 들어갈 제목
	 * msg : 중단에 들어갈 문구
	 * cancelBtnNm : 하단의 버튼에 들어갈 취소 버튼명(클릭시 javascript:callbackConfirmLayer(false); 함수 실행)
	 * confirmBtnNm : 하단의 버튼에 들어갈 확인 버튼명(클릭시 javascript:callbackConfirmLayer(true); 함수 실행)
	 * layerId : popup할 레이어ID
	 */
	function openCommonLayerPopupForConfirm(title, msg, cancelBtnNm, confirmBtnNm, layerId) {
		$("article#" + layerId).find("h2").html(title);
		$("article#" + layerId).find(".layer-txt").html(msg);
		$("article#" + layerId).find(".btn").html(cancelBtnNm);
		$("article#" + layerId).find(".fill").html(confirmBtnNm);
		
		layerPopup.popupOpenNow("#" + layerId);
	}
	 
	//kcp
	function openKcpWindow(){
	 	var status = "width=500 height=450 menubar=no,scrollbars=no,resizable=no,status=no";
	 	var obj = window.open('', 'kcp_pop', status);
	 	document.shop_check.method = "post";
	 	document.shop_check.target = "kcp_pop";
	 	document.shop_check.action = "https://admin.kcp.co.kr/Modules/escrow/kcp_pop.jsp?site_cd=A8A9S";
	 	document.shop_check.submit();
	}
	
	//매장안내
	function layerPopupFindStoreMapBottom(){
		layerPopup.popupOpenNow("#layerPopupFindStoreMap");		
	}
	//카카오톡
	function goKakaoTalkOpen(){
		//window.open("https://accounts.kakao.com/login?continue=https://bizmessage.kakao.com/chat/Yz7ZkOng52mMB9G9o21nsQ?rf=http://www.mlb-korea.com/shop4/shop/goods/goods_main.php");
		var url = "https://bizmessage.kakao.com/chat/open"
		var method = "post";
	    var form = document.createElement("form");
	    form.setAttribute("method", method);
	    form.setAttribute("action", url);
	    form.setAttribute("target", "_blank");
	    
	    var hiddenField = document.createElement("input");
	    hiddenField.setAttribute("type", "hidden");
	    hiddenField.setAttribute("name", "uuid");
	    hiddenField.setAttribute("value", "@엠엘비");
	    form.appendChild(hiddenField);
	   
	    document.body.appendChild(form);	    
	    form.submit();
	}
	
	
	window.onpageshow = function(event) {

	    if ( event.persisted || (window.performance && window.performance.navigation.type == 2)) {
			// Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우
			console.log("back")
			//document.location.reload();
	    }
	}

	</script>
	
	
</head>
<body class="body" oncontextmenu="return false" ondragstart="return false" onselectstart="return false">
 

<jsp:include page="/WEB-INF/views/include/adTop.jsp" flush="false" />


<div class="wrap" id="wrap">
	<tiles:insertAttribute name="header"/>
	
	<tiles:insertAttribute name="gnb"/>
	
	<!-- 컨텐츠 시작 -->
	<tiles:insertAttribute name="contents"/>
	<!--// 컨텐츠 끝 -->
	
	<tiles:insertAttribute name="footer"/>	
</div>

<tiles:insertAttribute name="bottom"/>
<tiles:insertAttribute name="adBottom"/>
</body>
</html>