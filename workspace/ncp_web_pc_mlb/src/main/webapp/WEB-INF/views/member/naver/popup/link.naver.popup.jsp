<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- default header name is X-CSRF-TOKEN -->
	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />
	<meta name="_csrf.parameter" content="${_csrf.parameterName}" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<div id="naver_id_login" style="display:none;"></div>

<script type="text/javascript" src="${_resourceURL}static/js/min/jquery-3.3.1.min.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/naver/naverLogin_implicit-1.0.2-min.js?v=${_version}" charset="utf-8"></script>
<script type="text/javascript" >var YOUR_CLIENT_ID = '<ncp:prop key="ncp_base.naver.login.mlb.clientid"/>';</script>
<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>

<script type="text/javascript">
	var YOUR_CALLBACK_URL = '<ncp:prop key="ncp.url.pc_MLB.root.secure"/>/member/naver/linkNaver.popup';
	//var naverCallbackUrl = YOUR_CALLBACK_URL;
	//naverCallbackUrl += "/member/naver/linkNaver.popup";

	//var naver_id_login = new naver_id_login(YOUR_CLIENT_ID, YOUR_CALLBACK_URL);
	var naver_id_login = new naver_id_login(YOUR_CLIENT_ID, YOUR_CALLBACK_URL);
	
	if(naver_id_login.oauthParams.error == "access_denied"){
		console.log('error : ' + JSON.stringify(naver_id_login.oauthParams));
		window.close();
	}
	
	// 접근 토큰 값 출력
	var token = naver_id_login.oauthParams.access_token;
	//token = token.substring(0, token.length - 1);

	console.log(naver_id_login.oauthParams.access_token);
	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		var name = '';
		if(naver_id_login.getProfileData('name') == undefined){
			name = naver_id_login.getProfileData('nickname');
		}else{
			name = naver_id_login.getProfileData('name');
		}
	
		var email = naver_id_login.getProfileData('email');
		var naverUserId = naver_id_login.getProfileData('id');
	
		$("#mbrNm").val(name);
	
		$("#mobileNumber").val(email);
		$("#mbrEmail").val(email);
		//네이버ID로 회원가입
		$('#toknId').val(token);
		//네이버ID로 로그인
		$('#loginId').val(naverUserId);
		
		console.log("name : " + name + ", mbrEmail : " + email + ", toknId : " + token + ", naverUserId : " + naverUserId)
	}
</script>

<body onload="javascript:linkNaverAfter();">
	<form id="linkNaverForm" method="post"  >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="refererUrl" value="<c:url value='${refererUrl}' />" />
		<input type="hidden" name="mbr.mbrNm" id="mbrNm" value="" />
		<input type="hidden" name="mbr.mbrEmail" id="mbrEmail" value="" />
		<input type="hidden" name="mbrIdCntc.toknId" id="toknId" value=""/>
		<input type="hidden" name="mbrIdCntc.loginId" id="loginId" value=""/>
		<input type="hidden" name="mbrIdCntc.deleteYn" id="deleteYn" value="N"/>
	</form>	
</body>

<!-- 로컬 JS 스크립트 선언 -->
	<script>
	function linkNaverAfter() {
		console.log("naver link : " + $("#linkNaverForm").serialize())
		$.ajax({
			type : "POST",
			url : '<c:url value="/member/naver/updateNaverLinkInfo.json"/>',
			data : $("#linkNaverForm").serialize(),
			success : function(args) {
				console.log(args)
				if(args.beforeConnect != undefined && args.beforeConnect == 'true'){
					//기존 연결된 계정이 있음
					opener.parent.callbackLinkNaverAfter(false);
					window.close();
					return false;
				}
				else {
					//새로운 연결
					opener.parent.callbackLinkNaverAfter(true);
					window.close(); 
					return false;
				}
			},
			error : function(e) {
				console.log(' error : '+JSON.stringify(e));
				alert('<spring:message code="common.system.error" text="시스템 오류 입니다."/>');
				window.close();
			}
		});
	}
	</script>
</html>


