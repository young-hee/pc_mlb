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

<script type="text/javascript" src="${_resourceURL}static/js/min/jquery-3.3.1.min.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/naver/naverLogin_implicit-1.0.2-min.js?v=${_version}" charset="utf-8"></script>
<script type="text/javascript" >var YOUR_CLIENT_ID = '<ncp:prop key="ncp_base.naver.login.mlb.clientid"/>';</script>
<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>

<script type="text/javascript">
	var YOUR_CALLBACK_URL = '<ncp:prop key="ncp.url.pc_MLB.root.secure"/>';
	var naverCallbackUrl = YOUR_CALLBACK_URL;
	naverCallbackUrl += '/member/naver/linkNaver.popup';
  	var naver_id_login = new naver_id_login(YOUR_CLIENT_ID, naverCallbackUrl);

	if(naver_id_login.oauthParams.error == "access_denied"){
		console.log('error : ' + JSON.stringify(naver_id_login.oauthParams));
		self.close();
	}
  	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");

	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		// 접근 토큰 값 출력
		var token = naver_id_login.oauthParams.access_token;
		//token = token.substring(0, token.length - 1);
	  
// 	  	console.log(token);
// 		var txt = "";
// 		txt += "id : " + naver_id_login.getProfileData('id') + "\n";
// 		txt += "email : " + naver_id_login.getProfileData('email') + "\n";
// 		alert( txt );
// 		alert(naver_id_login.getProfileData('id'));
// 		alert(naver_id_login.getProfileData('name'));
// 		alert(naver_id_login.getProfileData('email'));
// 		alert(naver_id_login.getProfileData('nickname'));
// 		alert(naver_id_login.getProfileData('age'));
// 		console.log(JSON.stringify(inner_profileParams));

		opener.naverLoginCallback(token);
		self.close();		
	}
</script>

<body>
</body>

</html>
