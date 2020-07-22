<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

				<div class="snsLoginBox">
					<span class="snsNaver"><spring:message code='mypage.sub.main.link.naver.txt1' /></span><%-- 네이버 계정 --%>
					<div class="toggleBtn-onff d_toggle_btn">
						<button type="button" class="toggle-on d_toggle_on" id="naverBtnOn" name="naverBtn" style="display: none;"><span>ON</span></button>
						<button type="button" class="toggle-off d_toggle_off" id="naverBtnOff" name="naverBtn"><span>OFF</span></button>
					</div>
					<p class="snsLoginTxt"><spring:message code='mypage.sub.main.link.naver.txt2' /></p><%-- 네이버 아이디 간편로그인 계정 연결 시 네이버 로그인으로 MLB에서 제공하는 다양한 서비스를 안전하고 편리하게 이용하실 수 있습니다. --%>
				</div>				
					

	<form id="linkNaverForm" method="post"  >
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="refererUrl" value="<c:url value='${refererUrl}' />" />
		<input type="hidden" name="mbr.mbrNm" id="mbrNm" value="" />
		<input type="hidden" name="mbr.mbrEmail" id="mbrEmail" value="" />
		<input type="hidden" name="mbrIdCntc.toknId" id="toknId" value=""/>
		<input type="hidden" name="mbrIdCntc.loginId" id="loginId" value=""/>
		<input type="hidden" name="mbrIdCntc.deleteYn" id="deleteYn" value="N"/>
	</form>	

<script type="text/javascript">
$(document).ready(function() {
	
	getLinkNaverStatus();
	
	// toggle event 제거
	$(".d_toggle_btn").off();
	
	$("[name=naverBtn]").off().click(function() {
		
		// 현재 OFF 이므로 연결 팝업
		if($(this).attr("id") == "naverBtnOff") {
			linkNaver();
		}
		// 현재 ON 이므로 해제 confirm
		else {
			confirmLayer(MESSAGES['mypage.js.sub.main.link.naver.msg2']);
		}
		
	});
	
});

// 네이버 계정 연결 상태 확인
function getLinkNaverStatus() {
	$.ajax({
		type : "POST",
		url : "/member/naver/linkNaverStatus.json",
		data : "",
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			// 연결중
			//console.log("===" + JSON.stringify(args))
			if(args.linkNaverFlag != undefined && (args.linkNaverFlag == "true" || args.linkNaverFlag == true) ) {
				naverBtnOnOff(true);
			}
		},
		error : function(e) {
			
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

function naverBtnOnOff(flag) {
	if(flag) {
		$("#naverBtnOn").show();
        $("#naverBtnOff").hide();
	}
	else {
		$("#naverBtnOn").hide();
        $("#naverBtnOff").show();
	}
}

function unlinkNaver() {
	
	$("[name='mbrIdCntc.deleteYn']:hidden").val("Y");
	
	$.ajax({
		type : "POST",
		url : '<c:url value="/member/naver/updateNaverLinkInfo.json"/>',
		data : $("#linkNaverForm").serialize(),
		success : function(args) {
			alertLayer(MESSAGES['mypage.js.sub.main.link.naver.msg1']);
			naverBtnOnOff(false);
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

function callbackConfirmLayer(flag) {
	if(flag) {
		unlinkNaver();
	}
	else {
		closeConfirmLayer();
	}
}


function closeNaverConnectCompletePopup(flag) {
	if(flag) {
		closeCommonLayerPopup("naverConnectCompletePopup");
		naverBtnOnOff(true);
	}
	else {
		closeCommonLayerPopup("naverConnectFailPopup");
	}
}

</script>