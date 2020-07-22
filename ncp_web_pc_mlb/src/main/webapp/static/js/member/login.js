$(document).ready(function() {
	$("#login, #popLogin").click(function(){
		var form = $("#loginForm");
		if(Validator.validate(form, null, null, true) == false) {
			return;
		}

		login();
	});
	
	$("#password").keyup(function(e){
		if(e.keyCode == 13) {			
			var form = $("#loginForm");
			if(Validator.validate(form, null, null, true) == false) {
				return;
			}
	
			login();
		}
	});
	
	$("#naverLogin, #popNaverLogin").click(function(){
		naverMemberLogin();
	});
	
	$("#findId, #findPassword").click(function(){
		var param = null;
		if($(this).attr("id") == "findId") {
			param = {
				'${_csrf.parameterName}' : '${_csrf.token}'
				, 'type' : 'ID'
		    };
		}
		else {
			param = {
				'${_csrf.parameterName}' : '${_csrf.token}'
		        , 'type' : 'PASSWORD'
		    };
		}
		movePage('/member/login/viewFind', param);
	});
	
	$("#join").click(function(){
		movePage('/member/join/view');
	});
	
	$("#guestLogin").click(function(){
		var form = $("#guestForm");
		if(Validator.validate(form, null, null, true, $(this).closest("form").find("p.error-msg")) == false) {
			return;
		}

		authGuestRole();
	});
	
	$("input#mobilAreaNo, input#mobilTlofNo, input#mobilTlofWthnNo").change(function(){
		$("[name=cstmrMobilNo]:hidden").val( $("input#mobilAreaNo").val() + $("input#mobilTlofNo").val() + $("input#mobilTlofWthnNo").val() );
	});
	
	$("#guestCounsel").click(function(){
		movePage('/helpdesk/csInquiry/new');
	});
	
	$("#guestGroupCounsel").click(function(){
		movePage('/helpdesk/bundleOrder/new');
	});

	$("#guestAllianceCounsel").click(function(){
		movePage('/helpdesk/affiliateInquiry/new');
	});
	
	$("#connectIdByNaver").click(function(){
		var form = $("#loginFormByNaver");
		if(Validator.validate(form, null, null, true) == false) {
			return;
		}

		connectIdByNaver();
	});
});

function movePage(url, param) {
    var strParams = null;
    if(param != undefined && param != null) {
    	strParams = param;
    }
    else {
    	strParams = {
   			'${_csrf.parameterName}' : '${_csrf.token}'
   		};    	
    }
    jsLinkUrlPost(url, strParams);
}

var loginObj;
function login() {
	var form = $("#loginForm");
	commonFnc.block();
	$.ajax({
		type : "POST",
		url : "/loginProcess",
		data : $("#loginForm").serialize(),
		async : false,
		headers: { 'ajax-login': true },
		success : function(args) {
			//commonFnc.unblock();			
			var obj = $.parseJSON(args);
			if(obj.releaseDrmncy != undefined && obj.releaseDrmncy == "true") {
				loginObj = obj;
				//layerPopup.popupOpenNow("#releaseDrmncyPopup");
				alert("회원님 반갑습니다. 휴면 계정이 해제되었습니다.\n(1년간 온라인 로그인 이력이 없거나, 오프라인 매장 결제 이력이 없는 경우 휴면계정으로 전환됩니다.)");
				callbackReleaseDrmncyAfter();
			}
			else {
				if(obj.loginTarget.indexOf("#") > -1){
					$("#loginTarget").val(obj.loginTarget.substring(0, obj.loginTarget.indexOf("#")));
				}
				else {
					$("#loginTarget").val(obj.loginTarget);
				}
				callbackLoginAfter();
			}
		 },
		 error : function(e) {
			commonFnc.unblock();
			try{
				var text = $.parseJSON(e.responseText);
				$(".error-msg").html(text.failMessage);
			}catch(e){
				//$(".error-msg").text('exception - error-msg');
				$(".error-msg").html("일시적으로 에러가 발생 했습니다. 다시 시도해 주세요.");
			}
			$('input[name=password]').focus();
			$('input[name=password]').val("");
			$(".error-msg").show();
		}
	});
}

function callbackReleaseDrmncyAfter() {
	//closeCommonLayerPopup("releaseDrmncyPopup");
	
	if(loginType != undefined && loginType == "NAVER") {
		linkNaver();
	}
	else {
		if(loginObj.loginTarget.indexOf("#") > -1){
			$("#loginTarget").val(loginObj.loginTarget.substring(0, loginObj.loginTarget.indexOf("#")));
		}
		else {
			$("#loginTarget").val(loginObj.loginTarget);
		}
		callbackLoginAfter();
	}
}

function callbackLoginAfter() {
	commonFnc.block();
	
	var ppram = {};
	
	if($("#loginActionParam").length > 0){
		ppram = {
				'${_csrf.parameterName}' : '${_csrf.token}',
				'loginActionParam' : $("#loginActionParam").val()
		}
	}else{
		ppram = {'${_csrf.parameterName}' : '${_csrf.token}'};
	}
	
	$.ajax({
		type : "GET",
		data : ppram,
		url : "/member/login/loginAction.json",
		cache : false,
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			//commonFnc.unblock();			
			callbackLogin(args);
		},
		error : function(e) {
			commonFnc.unblock();
			alert(e.responseText);
		}
	});
}

function callbackLogin(args) {
	commonFnc.unblock();		
	var pMpde = $("#loginForm").find("[name=pageMode]").val();
	if((pMpde && (pMpde == false || pMpde == "false")) && $("#loginTarget").val() != undefined && $("#loginTarget").val() != '') {
		var targetUrl = $("#loginTarget").val();
		window.document.location.href = targetUrl;
	}
	else if(pMpde && (pMpde == true || pMpde == "true")) {			
		if($("#loginCallback").val() != undefined && $("#loginCallback").val() != ''){
			closeCommonLayerPopup('loginPopup');
			$("[name='loginYn']").val("Y");
			gnbMiniMyinfo.load(true);
			new Function($("#loginCallback").val())();
		}else{
			window.document.location.reload();
		}
	}
}

function naverLoginCallback(accessToken) {
	$('#accessToken').val(accessToken);
	commonFnc.block();
	$.ajax({
		type : "POST",
		url : "/naverloginProcess",
		data : $("#loginForm").serialize(),
		async : false,
		headers: { 'ajax-login': true },
		success : function(args) {
			//commonFnc.unblock();
			if(args == undefined || args == null || args == "") {
				alertLayer(MESSAGES['common.js.error.msg1']);
				return false;
			}
			else {
				var obj = $.parseJSON(args);
				if(obj.loginTarget.indexOf("#") > -1){
					$("#loginTarget").val(obj.loginTarget.substring(0, obj.loginTarget.indexOf("#")));
				}else{
					$("#loginTarget").val(obj.loginTarget);
				}
				callbackLoginAfter();
			}
		},
		error : function(e) {
			commonFnc.unblock();
			console.log('JSON.stringify : '+JSON.stringify(e));
			
			var obj = $.parseJSON(JSON.stringify(e));
			if(obj.failMessage != undefined && obj.failMessage == "BadCredentials") {
				$(".error-msg").html("네이버 연동에 실패하였습니다.");
			}
			else {
				layerPopup.popupOpenNow("#naverCertCompletePopup");	
			}
		}
	});
}

function authGuestRole(){
	commonFnc.block();
	var form = $("#guestForm");
	$.ajax({
		type : "post",
		url : "/member/guest/authGuestRole.json",
		data : {'${_csrf.parameterName}' : '${_csrf.token}'},
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		async : false,
		success : function(args) {
			commonFnc.unblock();
			isExOrderList();
		},
		error : function(e) {
			commonFnc.unblock();
			alert(e.responseText);
		}
	});
}

function isExOrderList(){
	$.ajax({
		type : "POST",
		url : "/member/guest/isExistOrder.json",
		data : $("#guestForm").serialize(),
		async : false,
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			if(args.orderCount == 0) {
				alertLayer(MESSAGES['member.js.login.lbl.guest.not.found.order']);
				return;
			}
			
			// 비회원 주문조회 화면으로 이동
			window.document.location.href='/mypage/order/' + $("#guestForm [name=ordNo]").val() + '/view';
		},
		error : function(e) {
			alertLayer(MESSAGES['member.js.login.lbl.guest.not.found.order']);
		}
	});
}

var loginType;
function connectIdByNaver() {
	var form = $("#loginFormByNaver");

	$.ajax({
		type : "POST",
		url : "/loginProcess",
		data : $("#loginFormByNaver").serialize(),
		async : false,
		headers: { 'ajax-login': true },
		success : function(args) {
			var obj = $.parseJSON(args);
			if(obj.releaseDrmncy != undefined && obj.releaseDrmncy == "true") {
				loginObj = obj;
				layerPopup.popupOpenNow("#releaseDrmncyPopup");
				loginType = "NAVER";
			}
			else {
				linkNaver();
			}
		},
		error : function(e) {
			try{
				var text = $.parseJSON(e.responseText);
				$("#naverCertCompletePopup .error-msg").html(text.failMessage);
			}catch(e){
				$("#naverCertCompletePopup .error-msg").text('exception - error-msg');
			}
			$('input[name=password]').focus();
			$('input[name=password]').val("");
			$("#naverCertCompletePopup .error-msg").show();
		}
	});
	
}