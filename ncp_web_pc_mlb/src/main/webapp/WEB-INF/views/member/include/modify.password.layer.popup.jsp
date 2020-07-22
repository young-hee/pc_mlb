<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
		<article id="layerPopupModifyPassword" class="layer-popup layer-type02 popPwFind">
			<section class="layer-popup-cont" tabindex="0">
				<h2>비밀번호 변경</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03">새로운 비밀번호를 입력해주세요.</p>
					<form:form id="modifyPasswordForm">
					<div class="layer-input-box">
						<input type="password" class="input-style01" placeholder="비밀번호" style="width:100%" id="newPassword" name="newPassword" 
						validate="required" validateText="비밀번호"
						maxlength="12">						
						<input type="password" class="input-style01" placeholder="비밀번호 확인" style="width:100%" id="mbrCheckPw" name="mbrCheckPw" 
						validate="required" validateText="비밀번호 확인"
						maxlength="12">														
					</div>
					<span class="error-msg"></span>							
					</form:form>
				</div>
				<div class="btn-wrap">
					<a href="#" id="modifyPasswordConfirm" class="btn lg fill" onClick="return false;">비밀번호 변경하기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		<!-- 
		<article id="layerPopupModifyPasswordSuccess" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>비밀번호 변경</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt">새로운 비밀번호로 변경되었습니다.<br>확인버튼을 누르시면 로그인 페이지로 이동합니다.</p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn lg fill" onClick="javascript:callbackLayerPopupModifyPasswordSuccess();">확인</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		 -->
<script type="text/javascript">
$(document).ready(function() {
	var pageType = "";
	$("#layerPopupModifyPassword [name=newPassword], #layerPopupModifyPassword [name=mbrCheckPw]").on('change', function () {
		var attr = $(this);
		
		if(attr.attr("name") == "newPassword") {
		    if(validateMbrPw($("#layerPopupModifyPassword [name=newPassword]")) === false) {
		    	return;
		    }
		}

		else if(attr.attr("name") == "mbrCheckPw") {
		    if(validateMbrCheckPw($("#layerPopupModifyPassword [name=mbrCheckPw]")) === false) {
		    	return;
		    }
		}
	});
	
	// 비밀번호 찾기 팝업 취소 버튼
	$("#layerPopupModifyPassword").find(".d_layer_close").click(function(){
		// 초기화
		$("#layerPopupModifyPassword [name=newPassword]").val('');
		$("#layerPopupModifyPassword [name=mbrCheckPw]").val('');

		//closeCommonLayerPopup("layerPopupModifyPassword");
	});
	
	// 비밀번호 찾기 팝업 확인 버튼
	$("#modifyPasswordConfirm").click(function(){
		modifyPassword();
	});

});

function modifyPassword() {
	var form = $("#modifyPasswordForm");
	if(Validator.validate(form, null, null, true) == false) {
		return;
	}
	
    if(validateMbrPw($("#layerPopupModifyPassword [name=newPassword]")) === false) {
    	return;
    }

    if(validateMbrCheckPw($("#layerPopupModifyPassword [name=mbrCheckPw]")) === false) {
    	return;
    }
    
	$.ajax({
		type : "POST",
		url : "/member/login/modifyPassword.json",
		data : $("#modifyPasswordForm").serialize(),
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			// 정상
			if(args.isFlag != undefined && args.isFlag == true ) {
				closeCommonLayerPopup("layerPopupModifyPassword");
				if(pageType == 'modify') {
					// 초기화
					$("#layerPopupModifyPassword [name=newPassword]").val('');
					$("#layerPopupModifyPassword [name=mbrCheckPw]").val('');

					$("#layerPopupModifyPasswordSuccess").find(".layer-txt").html(MESSAGES['member.js.find.layer.password.txt3']);
				}
				//layerPopup.popupOpenNow('#layerPopupModifyPasswordSuccess');
				alert("새로운 비밀번호로 변경되었습니다.");
				callbackLayerPopupModifyPasswordSuccess();
			}
			// 비정상
			else {
				alertLayer(MESSAGES['member.js.find.layer.password.error.txt1']);
			}
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

function validateMbrPw(attr) {
	/* if(validatePassword(attr.val(), attr) == false) {
		return false;
	}
	else {
		attr.next().hide();
	} */

	var mbrCheckPw = $("#layerPopupModifyPassword [name=mbrCheckPw]").val();
	var newPassword = $("#layerPopupModifyPassword [name=newPassword]").val();
	if(mbrCheckPw != "") {
		if(newPassword != mbrCheckPw) {
			$("#layerPopupModifyPassword [name=mbrCheckPw]").next().text("비밀번호가 일치하지 않습니다.");
			errorMsgShow($("#layerPopupModifyPassword").find("span.error-msg"));

			setTimeoutFocus('mbrCheckPw');
			return false;
		}
		else {
			errorMsgHide($("#layerPopupModifyPassword").find("span.error-msg"));
		}
	}
}

function validateMbrCheckPw(attr) {
	var mbrCheckPw = $("#layerPopupModifyPassword [name=mbrCheckPw]").val();
	var newPassword = $("#layerPopupModifyPassword [name=newPassword]").val();
	if(mbrCheckPw != "") {
		if(newPassword != mbrCheckPw) {
			$("#layerPopupModifyPassword [name=mbrCheckPw]").parent().next().text("비밀번호가 일치하지 않습니다.");
			errorMsgShow($("#layerPopupModifyPassword").find("span.error-msg"));

			setTimeoutFocus('mbrCheckPw');
			return false;
		}
		else {
			errorMsgHide($("#layerPopupModifyPassword").find("span.error-msg"));
		}
	}
}

</script>