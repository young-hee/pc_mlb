
	var YOUR_CALLBACK_URL = document.location.origin;	
	var YOUR_SERVICE_URL = ".pec.com";	
	var YOUR_SERVICE_HOSTNAME = document.location.origin.replace('https', 'http');

	console.log('document.location.origin : '+document.location.origin);
	var state = getUniqState();
	var naverLoginApiUrl = "https://nid.naver.com/oauth2.0/authorize";
	var NAVER_POPUP_RETURN;



	/**
	* oauth 2.0 spec 의 state 값 자동 생성
	* @ignore
	* @returns {*}
	* @private
	*/
	function getUniqState(){
		var stat_str = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) { var r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8); return v.toString(16); });
		return stat_str;
	}

	function naverMemberLogin(){
		state = getUniqState();
		var naverCallbackUrl = YOUR_CALLBACK_URL;
		naverCallbackUrl += "/member/naver/linkNaverLogin.popup";

		naverLoginApiUrl = "https://nid.naver.com/oauth2.0/authorize";
		naverLoginApiUrl += "?response_type=token&client_id="+YOUR_CLIENT_ID;
		naverLoginApiUrl += "&redirect_uri="+naverCallbackUrl;
		naverLoginApiUrl += "&state="+state;
		openCommonPopup(naverLoginApiUrl, '', 450, 532, 'naverMemberLogin');
		return false;
	}

	function naverLogin(){
        alert("준비중입니다.");
        return;

		state = getUniqState();
		var naverCallbackUrl = YOUR_CALLBACK_URL;
		naverCallbackUrl += "/member/naver/linkNaverLogin.popup";

		naverLoginApiUrl = "https://nid.naver.com/oauth2.0/authorize";
		naverLoginApiUrl += "?response_type=token&client_id="+YOUR_CLIENT_ID;
		naverLoginApiUrl += "&redirect_uri="+naverCallbackUrl;
		naverLoginApiUrl += "&state="+state;
		openCommonPopup(naverLoginApiUrl, '', 450, 532, 'naverLogin');
		return false;
	}  

	function linkNaver(){
		state = getUniqState();
		var naverCallbackUrl = YOUR_CALLBACK_URL;
		naverCallbackUrl += "/member/naver/linkNaver.popup";

		naverLoginApiUrl = "https://nid.naver.com/oauth2.0/authorize";
		naverLoginApiUrl += "?response_type=token&client_id="+YOUR_CLIENT_ID;
		naverLoginApiUrl += "&redirect_uri="+naverCallbackUrl;
		naverLoginApiUrl += "&state="+state;
		openCommonPopup(naverLoginApiUrl, '', 450, 532, 'linkNaver');
		return false;
	}

