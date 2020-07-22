var CHILD_COUNT = 0;
$(document).ready(function() {

	// 확인 버튼 클릭시
	$("#confirmBtn").click(function(){
		joinMember();
	});

	// 취소 버튼 클릭시
	$("#cancelBtn").click(function(){
		callbackId = "cancelBtn";
		confirmLayer(MESSAGES['common.js.ask.cancel']);
	});

	$("button#emailDefaultDomain").next().find("a").click(function(e){
		var txt = "";
		if($(this).text() != MESSAGES['member.js.join.lbl.email.domain']) {
			txt = $(this).text();
		}

		$("input#mbrEmailDomain").val(txt);
		$("input#mbrEmailDomain").trigger("change");
	});

	var form = $("#modifyMbrForm");
	$(form).find(":input[validate]").each(function(i) {
		$(this).on('change', function () {
			var attr = $(this);
			
			if(attr.attr("id") == "mbrEmail" || attr.attr("id") == "mbrEmailDomain") {
				var mbrEmail = $("input#mbrEmail").val();
				var mbrEmailDomain = $("input#mbrEmailDomain").val();
				var emailRe = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d)|(([a-z]|\d)([a-z]|\d|-|\.|_|~)*([a-z]|\d)))\.)+(([a-z])|(([a-z])([a-z]|\d|-|\.|_|~)*([a-z])))\.?$/i;
				var email = mbrEmail + "@" + mbrEmailDomain;
				
				console.log("email : " + email)
				
				$("[name='mbr.mbrEmail']:hidden").val( email );
				
				if(Validator.validateField(form, null, null, null, null, $("#mbrEmail")) == false) {
					return false;
				}
				else if(Validator.validateField(form, null, null, null, null, $("#mbrEmailDomain")) == false) {
					return false;
				}
				else if(Validator.validateField(form, null, null, null, null, $("[name='mbr.mbrEmail']:hidden")) == false) {
					return false;
				}
				else if(mbrEmail != "" && mbrEmailDomain != "") {
					if(emailRe.test(email) == false) {
						attr.closest("td").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg10']);
						errorMsgShow(attr.closest("td").find(".error-msg"));
	
						setTimeoutFocus(attr.attr("id"));
						return false;
					}
					else {
						$.ajax({
							type : "post",
							url : "/member/join/isCheckEmailOthers.json",
							async : false,
							data : {'${_csrf.parameterName}' : '${_csrf.token}','mbr.mbrEmail' : email },
							success : function(args) {
								isCheckEmailCallback(args, attr);
							},
							error : function(e) {
								alertLayer(MESSAGES['common.js.error.msg1']);
							}
						});
					}
				}
				else {
					errorMsgHide(attr.closest("td").find(".error-msg"));
				}
			}
			
			else if(attr.attr("id") == "mobilAreaNo" || attr.attr("id") == "mobilTlofNo" || attr.attr("id") == "mobilTlofWthnNo") {
				$("#mobileNumber:hidden").val( $("input#mobilAreaNo").val() + $("input#mobilTlofNo").val() + $("input#mobilTlofWthnNo").val() );
				
				if(Validator.validateField(form, null, null, null, null, $("input#mobilAreaNo")) == false) {
					return false;
				}
				else if(Validator.validateField(form, null, null, null, null, $("input#mobilTlofNo")) == false) {
					return false;
				}
				else if(Validator.validateField(form, null, null, null, null, $("input#mobilTlofWthnNo")) == false) {
					return false;
				}
				else if( $("#mobileNumber:hidden").val() != "" ) {
					Validator.validateField(form, null, null, null, null, $("#mobileNumber:hidden"));
				}
				else {
					errorMsgHide($("#mobileNumber:hidden").parent().find("span.error-msg"));
				}
			}
			
			else if(attr.attr("id") == "telAreaNo" || attr.attr("id") == "telTlofNo" || attr.attr("id") == "telTlofWthnNo") {
				$("#telNumber:hidden").val( $("input#telAreaNo").val() + $("input#telTlofNo").val() + $("input#telTlofWthnNo").val() );
				if( $("#telNumber:hidden").val() != "" ) {
					Validator.validateField(form, null, null, null, null, $("#telNumber:hidden"));
				}
				else {
					errorMsgHide($("#telNumber:hidden").parent().find("span.error-msg"));
				}
			}
			
		});
	});
	
	$("#modifyMbrForm [name=newPassword], #modifyMbrForm [name=mbrCheckPw]").on('change', function () {
		var attr = $(this);
		
		if(attr.attr("name") == "newPassword") {
		    if(validateMbrPw($("#modifyMbrForm [name=newPassword]")) === false) {
		    	return;
		    }
		}

		else if(attr.attr("name") == "mbrCheckPw") {
		    if(validateMbrCheckPw($("#modifyMbrForm [name=mbrCheckPw]")) === false) {
		    	return;
		    }
		}
	});
	
	
	setChildrenInfo();
	
	$("[name=familyExceptionFlag]:hidden").val("N");
	
	// 비밀번호 변경 팝업에서 사용(비밀번호찾기, 회원정보수정 구분)
	pageType = "modify";
	
});

function validateMbrPw(attr) {
	/* if(validatePassword(attr.val(), attr) == false) {
		return false;
	}
	else {
		attr.next().hide();
	} */

	var mbrCheckPw = $("#mbrCheckPw").val();
	var newPassword = $("#newPassword").val();
	console.log(mbrCheckPw + ":" + newPassword);
	
	if(validatePassword(attr.val(), attr) == false) {
		return false;
	}else{
		errorMsgHide($("#newPassword").parent().find("span.error-msg"));
	}
	
	if(newPassword != mbrCheckPw) {
		$("#mbrCheckPw").next().text("비밀번호가 일치하지 않습니다.");
		errorMsgShow($("#mbrCheckPw").parent().find("span.error-msg"));

		setTimeoutFocus('mbrCheckPw');
		return false;
	}
	else {
		errorMsgHide($("#mbrCheckPw").parent().find("span.error-msg"));
	}
	
	
	
}

function validateMbrCheckPw(attr) {
	var mbrCheckPw = $("#mbrCheckPw").val();
	var newPassword = $("#newPassword").val();
	
	if(newPassword != mbrCheckPw) {
		$("#mbrCheckPw").parent().next().text("비밀번호가 일치하지 않습니다.");
		errorMsgShow($("#modifyMbrForm [name=mbrCheckPw]").parent().find("span.error-msg"));

		setTimeoutFocus('mbrCheckPw');
		return false;
	}
	else {
		errorMsgHide($("#modifyMbrForm [name=mbrCheckPw]").parent().find("span.error-msg"));
	}
	
}

// 확인 버튼 클릭시
function joinMember() {
    var form = $("#modifyMbrForm");
	if(Validator.validate(form, null, null, true) == false) {
		return;
	}
    
    if(validateMbrEmail($("#mbrEmail")) === false) {
    	return;
    }
    
    //비밀번호 검증
    if($("#newPassword").val() != ""){
	    if(validateMbrPw($("#newPassword")) === false) {
	    	return;
	    }
	    
	    if(validateMbrCheckPw($("#newPassword")) === false) {
	    	return;
	    }
    }
    
	// 주소 검증
	if(isCheckDetailAddr() == false) {
		return;
	}
	
	// 자녀 정보 검증
	if(isCheckFamilyInfo() == false) {
		return;
	}
	
	if($("#emailRecptnAgrYn").is(":checked")){
		$("input[name='mbr.emailRecptnAgrYn']").val("Y");
	}else{
		$("input[name='mbr.emailRecptnAgrYn']").val("N");
	}
	if($("#smsRecptnAgrYn").is(":checked")){
		$("input[name='mbr.smsRecptnAgrYn']").val("Y");		
	}else{
		$("input[name='mbr.smsRecptnAgrYn']").val("N");		
	}
	
	callbackId = "confirmBtn";
	confirmLayer(MESSAGES['common.js.ask.update.save']);

}

function isCheckEmailCallback(args, attr) {
	if(args.isCheckEmail == true || args.isCheckEmail == "true") {
		attr.closest("td").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg11']);
		errorMsgShow(attr.closest("td").find(".error-msg"));
		
		setTimeoutFocus(attr.attr("id"));
	}
	else {
		errorMsgHide(attr.closest("td").find(".error-msg"));
	}
}

function validateMbrEmail(attr) {
	var mbrEmail = $("input#mbrEmail").val();
	var mbrEmailDomain = $("input#mbrEmailDomain").val();
	var emailRe = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d)|(([a-z]|\d)([a-z]|\d|-|\.|_|~)*([a-z]|\d)))\.)+(([a-z])|(([a-z])([a-z]|\d|-|\.|_|~)*([a-z])))\.?$/i;
	var email = mbrEmail + "@" + mbrEmailDomain;
	var isCheckEmail = false;
	$("[name='mbr.mbrEmail']:hidden").val( email );
	if(mbrEmail != "" && mbrEmailDomain != "") {
		if(emailRe.test(email) == false) {
			attr.closest("td").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg10']);
			errorMsgShow(attr.closest("td").find(".error-msg"));

			setTimeoutFocus(attr.attr("id"));
			return false;
		}
		else {
			$.ajax({
				type : "post",
				url : "/member/join/isCheckEmailOthers.json",
				async : false,
				data : {'${_csrf.parameterName}' : '${_csrf.token}','mbr.mbrEmail' : email },
				success : function(args) {
					if(args.isCheckEmail == true || args.isCheckEmail == "true") {
						attr.closest("td").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg11']);
						errorMsgShow(attr.closest("td").find(".error-msg"));
						
						setTimeoutFocus(attr.attr("id"));
					}
					else {
						errorMsgHide(attr.closest("td").find(".error-msg"));
						isCheckEmail = true;
					}
				},
				error : function(e) {
					alertLayer(MESSAGES['common.js.error.msg1']);
				}
			});
		}
	}
	else {
		errorMsgHide(attr.closest("td").find(".error-msg"));
		isCheckEmail = true;
	}
	
	return isCheckEmail;
}

// 주소 입력시 상세주소 검증
function isCheckDetailAddr() {
    var mbrPostNo = $("input#mbrPostNo").val();
    var mbrBaseAddr = $("input#mbrBaseAddr").val();
    var mbrDetailAddr = $.trim($("input#mbrDetailAddr").val());
    
    if(mbrPostNo.length > 0  &&  mbrBaseAddr.length > 0 && mbrDetailAddr.length < 1) {
    	$("input#mbrDetailAddr").parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg14']);
		errorMsgShow($("input#mbrDetailAddr").parent().find(".error-msg"));

		setTimeoutFocus("mbrDetailAddr");
        return false;
    }
    else {
    	errorMsgHide($("input#mbrDetailAddr").parent().find(".error-msg"));
    }
    
    return true;
}

// 자녀 정보 검증
function isCheckFamilyInfo() {
	var $additionalChild = $("[name=additionalChild]");
	var childrenName = "";
	var childrenBirthYear = "";
	var childrenBirthMonth = "";
	var childrenBirthDay = "";
	var flag = true;
	var obj;
	$.each($additionalChild, function(index, item) {
		obj = $(this);
		
		if(obj.is(":visible")){
					
			childrenName = obj.find("input[name^='childrenName']").val();
			childrenBirthYear = obj.find("input[name^='childrenBirthYear']").val();
			childrenBirthMonth = obj.find("input[name^='childrenBirthMonth']").val();
			childrenBirthDay = obj.find("input[name^='childrenBirthDay']").val();
						
			if(childrenName != ""){
				
				if(childrenBirthMonth.length == 1) {
					childrenBirthMonth = "0"+childrenBirthMonth;
				}
				if(childrenBirthDay.length == 1) {
					childrenBirthDay = "0"+childrenBirthDay;
				}
				
				if(childrenBirthYear == "" || childrenBirthMonth == "" || childrenBirthDay == ""){					
					obj.parent().parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg12']);
					errorMsgShow(obj.parent().parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				if(maxLengthCheck(obj.find("input[name^='childrenName']").attr("id"), obj.find("input[name^='childrenName']").attr("maxlength")) == false) {					
					obj.parent().parent().parent().find(".error-msg").text(messageFormat(MESSAGES['member.js.join.lbl.error.msg15'], obj.find("input[name^='childrenName']").attr("maxlength")));
					errorMsgShow(obj.parent().parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				if( isValidDate(childrenBirthYear+childrenBirthMonth+childrenBirthDay) == false ) {					
					obj.parent().parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg13']);
					errorMsgShow(obj.parent().parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				obj.find("input[name^='childrenBirthDay']").val(childrenBirthDay);
				obj.find("input[name^='childrenBirthDate']").val(childrenBirthYear+childrenBirthMonth+childrenBirthDay);
			}			
			
		}
	});
	
	if(flag) {
		errorMsgHide(obj.parent().parent().parent().find(".error-msg"));
	}
	
	return flag;
	/*
	var $additionalChild = $("[name=additionalChild]");
	var childrenName = "";
	var childrenBirthYear = "";
	var childrenBirthMonth = "";
	var childrenBirthDay = "";
	var flag = true;
	var obj;
	$.each($additionalChild, function(index, item) {
		obj = $(this);
		childrenName = obj.find("input[id^='childrenName']").val();
		childrenBirthYear = obj.find("input[id^='childrenBirthYear']").val();
		childrenBirthMonth = obj.find("input[id^='childrenBirthMonth']").val();
		childrenBirthDay = obj.find("input[id^='childrenBirthDay']").val();
		
		if( !(childrenName != "" && childrenBirthYear != "" && childrenBirthMonth != "" && childrenBirthDay != "")
				&& !(childrenName == "" && childrenBirthYear == "" && childrenBirthMonth == "" && childrenBirthDay == "")
				) {
			obj.parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg12']);
			errorMsgShow(obj.parent().parent().find(".error-msg"));
			flag = false;
			return false;
		}
		else if(maxLengthCheck(obj.find("input[id^='childrenName']").attr("id"), obj.find("input[id^='childrenName']").attr("maxlength")) == false) {
			obj.parent().parent().find(".error-msg").text(messageFormat(MESSAGES['member.js.join.lbl.error.msg15'], obj.find("input[id^='childrenName']").attr("maxlength")));
			errorMsgShow(obj.parent().parent().find(".error-msg"));
			flag = false;
			return false;
		}
		else if( !(childrenName == "" && childrenBirthYear == "" && childrenBirthMonth == "" && childrenBirthDay == "") ) {
			if(childrenBirthMonth.length == 1) {
				childrenBirthMonth = "0"+childrenBirthMonth;
			}
			if(childrenBirthDay.length == 1) {
				childrenBirthDay = "0"+childrenBirthDay;
			}
			if( isValidDate(childrenBirthYear+childrenBirthMonth+childrenBirthDay) == false ) {
				obj.parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg13']);
				errorMsgShow(obj.parent().parent().find(".error-msg"));
				flag = false;
				return false;
			}
		}
	});

	if(flag) {
		errorMsgHide(obj.parent().parent().find(".error-msg"));
	}
	
	return flag;
	*/
}

var chkBtn = true;
var callbackId = "";
function callbackConfirmLayer(flag) {
	// 확인 버튼
	if(callbackId == "confirmBtn") {
		if(flag) {
			if(chkBtn == false) {
		    	return;
			}
			else {
				chkBtn = false;
			}		    
			var form = $("#modifyMbrForm");
			$.ajax({
				type : "POST",
				url : "/member/join/isCheckEmailOthers.json",
				data : {'${_csrf.parameterName}' : '${_csrf.token}', 'mbr.mbrEmail' : $('#mbrEmail').val() },
				beforeSend: function (request)
				{
					var csrfToken = $("meta[name='_csrf']").attr("content");
					var csrfName = $("meta[name='_csrf_header']").attr("content");
					request.setRequestHeader(csrfName, csrfToken);
				},
				success : function(args) {					
					if(args.isCheckEmail == true || args.isCheckEmail == "true") {
						alertLayer(MESSAGES['member.js.join.lbl.error.msg11']);
						chkBtn = true;
					}
					else {
						form.submit();
					}
				},
				error : function(e) {
					chkBtn = true;
					alertLayer(MESSAGES['common.js.error.msg1']);
				}
			});
		}
		else {
			if(chkBtn == false) {
		    	return;
			}
			closeConfirmLayer();
		}
	}
	// 취소 버튼
	else if(callbackId == "cancelBtn") {
		if(flag) {
			$.ajax({
				type : "POST",
				url : "/member/join/removeCert.json",
				data : {'${_csrf.parameterName}' : '${_csrf.token}'},
				beforeSend: function (request)
				{
					var csrfToken = $("meta[name='_csrf']").attr("content");
					var csrfName = $("meta[name='_csrf_header']").attr("content");
					request.setRequestHeader(csrfName, csrfToken);
				},
				success : function(args) {
					if($("#modifyMbrForm").find("[name=targetPath]").val() != "") {
						movePage($("#modifyMbrForm").find("[name=targetPath]").val());
					}
					else {
						movePage("/mypage/view");
					}
				},
				error : function(e) {
					alertLayer(MESSAGES['common.js.error.msg1']);
				}
			});
		}
		else {
			closeConfirmLayer();
		}
	}
}

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

function setChildrenInfo() {
	$.ajax({
		type : "POST",
		url : "/mypage/member/getMemberInformation.json",
		data : {'${_csrf.parameterName}' : '${_csrf.token}'},
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			$("[name=familyExceptionFlag]:hidden").val(args.familyExceptionFlag);
			var familyList = null;
			if(args.memberInformationSDO != undefined && args.memberInformationSDO != null) {
				familyList = args.memberInformationSDO.FAMILY_LIST;
				var childrenBirthYear = "";
				var childrenBirthMonth = "";
				var childrenBirthDay = "";
				var familyBirthStr;
				//console.log(familyList)		
				//console.log(familyList.length)		
				if(typeof familyList != "undefined"){
					CHILD_COUNT = familyList.length;
				}
				
				var iHtml = "";
				$.each(familyList, function(index, item) {					
					var familyBirth = item.FAMILY_BIRTH;
					try {
						
						familyBirthStr = item.FAMILY_BIRTH;
						childrenBirthYear = familyBirthStr.substring(0,4);
						childrenBirthMonth = familyBirthStr.substring(4,6);
						childrenBirthDay = familyBirthStr.substring(6,8);
						
						iHtml += "<li><span><i>" + item.FAMILY_NAME + "</i><i>" + childrenBirthYear + "년 " + childrenBirthMonth + "월 " + childrenBirthDay + "일</i></span></li>";
						
						
						$("#childrenName" + (index+1)).val(item.FAMILY_NAME);
						$("#childrenName" + (index+1)).attr("readonly", "readonly");
						$("#childrenBirthYear" + (index+1)).val(childrenBirthYear);
						$("#childrenBirthYear" + (index+1)).attr("readonly", "readonly");
						$("#childrenBirthMonth" + (index+1)).val(childrenBirthMonth);
						$("#childrenBirthDay" + (index+1)).val(childrenBirthDay);
						$("#childrenBirthDay" + (index+1)).attr("readonly", "readonly");
						$("#childrenBirthDate" + (index+1)).val(familyBirthStr);
						
						//$("[name=additionalChild]").hide();
						
						/*
						familyBirthStr = familyBirth.split("-");
						
						childrenBirthYear = familyBirthStr[0];
						childrenBirthMonth = familyBirthStr[1];
						childrenBirthDay = familyBirthStr[2];
						if(childrenBirthMonth.length == 1) childrenBirthMonth = "0" + childrenBirthMonth;
						if(childrenBirthDay.length == 1) childrenBirthDay = "0" + childrenBirthDay;
						
						$("#childrenBirthYear" + (index+1)).val(childrenBirthYear);
						$("#childrenBirthMonth" + (index+1)).val(childrenBirthMonth);
						$("#childrenBirthDay" + (index+1)).val(childrenBirthDay);
						*/
						
					}
					catch(e) {
						// Ignore
					}
				});
				
				$("#childList").html(iHtml);
				
			}
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		},
		complete : function(){
			/*if(CHILD_COUNT == 0){
				$("[name='additionalChild']").eq(0).show();
			}else if(CHILD_COUNT == 1){
				$("[name='additionalChild']").eq(1).show();
			}else if(CHILD_COUNT == 2){
				$("[name='additionalChild']").eq(2).show();
			}else{
				$("#childAddBt").hide();
			}*/
									
			if(CHILD_COUNT == 0){
				$("[name='additionalChild']").eq(0).show();
			}else if(CHILD_COUNT == 3){
				$("#childAddBt").hide();
			}
			
			$("#childAddBt").click(function(){
				if(CHILD_COUNT == 0){
					if(!$("[name='additionalChild']").eq(0).is(":visible")){
						$("[name='additionalChild']").eq(0).show();
					}else if(!$("[name='additionalChild']").eq(1).is(":visible")){
						$("[name='additionalChild']").eq(1).show();
					}else if(!$("[name='additionalChild']").eq(2).is(":visible")){
						$("[name='additionalChild']").eq(2).show();
						$(this).hide();
					}
				}else if(CHILD_COUNT == 1){
					if(!$("[name='additionalChild']").eq(1).is(":visible")){
						$("[name='additionalChild']").eq(1).show();
					}else if(!$("[name='additionalChild']").eq(2).is(":visible")){
						$("[name='additionalChild']").eq(2).show();
						$(this).hide();
					}
				}else if(CHILD_COUNT == 2){
					if(!$("[name='additionalChild']").eq(2).is(":visible")){
						$("[name='additionalChild']").eq(2).show();
						$(this).hide();
					}
				}else{
					return false;
				}
				
			})
		}
	});
}

// 비밀번호 변경 팝업
//function openPasswordPopup() {
//	$("#layerPopupModifyPassword").find("h2").text(MESSAGES['mypage.js.member.btn.modify.password']);
//	layerPopup.popupOpenNow("#layerPopupModifyPassword");
//}

// 비밀번호 변경 후 성공 레이어 팝업에서 확인 버튼 클릭시
function callbackLayerPopupModifyPasswordSuccess() {
	closeCommonLayerPopup("layerPopupModifyPasswordSuccess");
}

// 우편번호 주소 세팅
function setZipcode(zipcode, addr, type) {
	$("#mbrPostNo").val(zipcode);
    $("#mbrBaseAddr").val(addr);
    $("[name='mbr.mbrAddrSectCd'").val(type);
    closeCommonLayerPopup("layerPopupZipcode");
}

//본인인증
var SetTime = 179;	// 최초 설정 시간(기본 : 초)
var tid;
function goCertification(obj){
	
	var form = $("#certificationMbrForm");
	
	if(Validator.validateField(form, null, null, true, null, $("#mbrNm")) == false) {
		return;
	}
	if(Validator.validateField(form, null, null, true, null, $("#birthyear")) == false) {
		return;
	}
	if(Validator.validateField(form, null, null, true, null, $("#birthmonth")) == false) {
		return;
	}
	if(Validator.validateField(form, null, null, true, null, $("#birthdate")) == false) {
		return;
	}
	
	var bDate = $("#birthdate").val()  < 10 ? ("0"+$("#birthdate").val()) : $("#birthdate").val();	
	$("#birthday").val($("#birthyear").val()+$("#birthmonth").val()+bDate);	
	$("#birthday").attr("validate", "required;date");
	if(Validator.validateField(form, null, null, null, null, $("#birthday")) == false) {
		return;
	}
	
	if(Validator.validateField(form, null, null, true, null, $("input[name=gender]")) == false) {
		return;
	}

	if(Validator.validateField(form, null, null, true, null, $("#mobileCo")) == false) {
		return;
	}

	if(Validator.validateField(form, null, null, true, null, $("#phone1")) == false) {
		return;
	}
	
	if(Validator.validateField(form, null, null, true, null, $("#phone2")) == false) {
		return;
	}
	
	if(Validator.validateField(form, null, null, true, null, $("#phone3")) == false) {
		return;
	}
	
	$("#newMobileNumber").val($("#phone1").val()+$("#phone2").val()+$("#phone3").val());	
	$("#newMobileNumber").attr("validate", "required;phone");
	$("#newMobileNumber").attr("validateText", "휴대폰 번호");
	
	if(Validator.validateField(form, null, null, true, null, $("#newMobileNumber")) == false) {
		return;
	}
	
	if(Validator.validateField(form, null, null, null, null, $("#certAgreeYn")) == false) {
		return;
	}
	
	setGender();
	
	if(confirm("본인인증 하시겠습니까?")){
	
		var sendData = {
				birthday : $("#birthday").val(),
				gender : $("#gender").val(),
				mbrNm : $("#mbrNm").val(),
				mobileCo : $("#mobileCo").val(),
				mobileNumber : $("#newMobileNumber").val()
		}
		$.ajax({
			type : "POST",
			url : "/member/certification/sendMobileCertSms.json",
			data : sendData,
			beforeSend: function (request)
			{
				var csrfToken = $("meta[name='_csrf']").attr("content");
				var csrfName = $("meta[name='_csrf_header']").attr("content");
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(args) {
				console.log(args)
				
				//클릭방지
				if(!$(obj).hasClass("btnResend")){
					//재전송 버튼은 막지 않음
					$(obj).attr("onclick", "return fasle");
					//$(obj).hide();
				}		
				
				$("#mbrNm").attr("readonly", "readonly");
				$("input:radio[name='frgnrYn']").attr("onclick", "return false;");
				
				$("#birthyear").attr("readonly", "readonly");
				$("#btnMonth").prop("disabled", true);
				$("#birthdate").attr("readonly", "readonly");
				$("input:radio[name='genderRadio']").attr("onclick", "return false;");
				
				$("#btnMobileCo").prop("disabled", true);
				$("#phone1").attr("readonly", "readonly");
				$("#phone2").attr("readonly", "readonly");
				$("#phone3").attr("readonly", "readonly");
				
				
				if(args.RETURN_CODE === "0000"){
					$("#REQ_SEQ").val(args.REQ_SEQ);
					$("#RES_SEQ").val(args.RES_SEQ);
					
					$("#certAfter").show();
					$("#sAuthNo").attr("validate", "required");
					$("#REQ_SEQ").attr("validate", "required");
					$("#RES_SEQ").attr("validate", "required");
					
					SetTime = 179;// 설정 시간(기본 : 초)
					if(tid){
						clearInterval(tid);		// 타이머 해제
					}
					tid=setInterval('msg_time()',1000)
					
					$("#sAuthNo").focus();
					
				}else{
					alert(args.RETURN_MESSAGE);
					
					$("#mbrNm").removeAttr("readonly");
					$("input:radio[name='frgnrYn']").attr("onclick", "");
					
					$("#birthyear").removeAttr("readonly");
					$("#btnMonth").prop("disabled", false);
					$("#birthdate").removeAttr("readonly");
					$("input:radio[name='genderRadio']").attr("onclick", "setGender()");
					
					$("#btnMobileCo").prop("disabled", false);
					$("#phone1").removeAttr("readonly");
					$("#phone2").removeAttr("readonly");
					$("#phone3").removeAttr("readonly");
					
					$(obj).attr("onclick", "goCertification(this); return false;");
				}
			},
			error : function(e) {
				alertLayer(MESSAGES['common.js.error.msg1']);
			}
		});
		
		
	}
}
function msg_time() {	
	// 1초씩 카운트
	var m = Math.floor(SetTime / 60) + "분 " + (SetTime % 60) + "초";	// 남은 시간 계산		
	//document.all.ViewTimer.innerHTML = msg;		// div 영역에 보여줌
	$("#remainTime").html(m);
	SetTime--;					// 1초씩 감소	
	if (SetTime < 0) {			// 시간이 종료 되었으면..	
		$("#remainTime").html("0초");
		clearInterval(tid);		// 타이머 해제		
	}	
}
function setBirthMonth(val){
	$("#birthmonth").val(val);
}
function setMobileco(val){
	$("#mobileCo").val(val);
	if(val == "6" || val == "7"){
		//KT 알뜰폰, LG U+ 알뜰폰
		$("#mCoview").show();
	}else{
		$("#mCoview").hide();
	}
}
function setGender(){
	
	var val = $("input[name=genderRadio]:checked").val();
	var bir = $("#birthyear").val();
	var frgnrYn = $("input[name=frgnrYn]:checked").val();
	
	if(val != "" && bir != "" && frgnrYn != ""){
		if(val == "1"){	//남자			
			if(frgnrYn == "Y"){ //외국인				
				if(bir < 1900){
					$("#gender").val("9");
				}else if(bir >= 1900 && bir < 2000){
					$("#gender").val("5");
				}else{
					$("#gender").val("7");
				}
			}else{	//내국
				if(bir < 2000){
					$("#gender").val("1");
				}else{
					$("#gender").val("3");
				}
			}
		}else{	//여자
			if(frgnrYn == "Y"){ //외국인				
				if(bir < 2000){
					$("#gender").val("6");
				}else{
					$("#gender").val("8");
				}
			}else{	//내국
				if(bir < 1900){
					$("#gender").val("0");
				}else if(bir >= 1900 && bir < 2000){
					$("#gender").val("2");
				}else{
					$("#gender").val("4");
				}
			}
		}
	}else{
		$("#gender").val(val);
	}	
}
function setAgree(){
	if($("#certAgreeYn").is(":checked")){
		$("#chk_policy_1").prop("checked", true);
		$("#chk_policy_2").prop("checked", true);
		$("#chk_policy_3").prop("checked", true);
		$("#chk_policy_4").prop("checked", true);
		$("#chk_policy_5").prop("checked", true);
	}else{
		$("#chk_policy_1").prop("checked", false);
		$("#chk_policy_2").prop("checked", false);
		$("#chk_policy_3").prop("checked", false);
		$("#chk_policy_4").prop("checked", false);
		$("#chk_policy_5").prop("checked", false);
	}
}
function setAgree2(){
	if($("#mobileCo").val() == "6" || $("#mobileCo").val() == "7"){
		if($("#chk_policy_1").is(":checked") && $("#chk_policy_2").is(":checked") && $("#chk_policy_3").is(":checked") && $("#chk_policy_4").is(":checked")&& $("#chk_policy_5").is(":checked")){
			$("#certAgreeYn").prop("checked", true);
		}else{
			$("#certAgreeYn").prop("checked", false);
		}
	}else{
		if($("#chk_policy_1").is(":checked") && $("#chk_policy_2").is(":checked") && $("#chk_policy_3").is(":checked") && $("#chk_policy_4").is(":checked")){
			$("#certAgreeYn").prop("checked", true);
		}else{
			$("#certAgreeYn").prop("checked", false);
		}
	}
	
}
function setCheck(){
	if($("#chk_receive_1").is(":checked")){
		$("#onlineSiteUsefStplat").prop("checked", true);
		$("#psnlInfoColctUsefAgr").prop("checked", true);
		$("#emailRecptnAgrYn").prop("checked", true);
		$("#smsRecptnAgrYn").prop("checked", true);
	}else{
		$("#onlineSiteUsefStplat").prop("checked", false);
		$("#psnlInfoColctUsefAgr").prop("checked", false);
		$("#emailRecptnAgrYn").prop("checked", false);
		$("#smsRecptnAgrYn").prop("checked", false);
	}
}
function setCheck2(){
	if($("#onlineSiteUsefStplat").is(":checked") && $("#psnlInfoColctUsefAgr").is(":checked") && $("#emailRecptnAgrYn").is(":checked") && $("#smsRecptnAgrYn").is(":checked")){
		$("#chk_receive_1").prop("checked", true);
	}else{
		$("#chk_receive_1").prop("checked", false);
	}
}

function goCertificationEnd(){
	
	if (SetTime < 0) {
		alert("인증 대기 시간이 초과 하였습니다. 다시 시도해 주세요");
		return;
	}
	
	var form = $("#certificationMbrForm");
	
	if(Validator.validateField(form, null, null, null, null, $("#REQ_SEQ")) == false) {
		return;
	}
	if(Validator.validateField(form, null, null, null, null, $("#RES_SEQ")) == false) {
		return;
	}
	if(Validator.validateField(form, null, null, null, null, $("#sAuthNo")) == false) {
		return;
	}
	
	var sendData = {
			REQ_SEQ : $("#REQ_SEQ").val(),
			RES_SEQ : $("#RES_SEQ").val(),
			sAuthNo : $("#sAuthNo").val(),
			birthday : $("#birthday").val(),
			gender : $("#gender").val(),
			mbrNm : $("#mbrNm").val(),
			mobileCo : $("#mobileCo").val(),
			mobileNumber : $("#newMobileNumber").val(),
			frgnrYn : $("input:radio[name='frgnrYn']:checked").val()
	}
	$.ajax({
		type : "POST",
		url : "/member/certification/sendMobileCertSmsCheck.json",
		data : sendData,
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			console.log(args)
			if(args.RETURN_CODE === "0000" && args.certificationResult == "Y"){
				callbackCertifyJoinMember(args);
			}else{
				alertLayer(args.RETURN_MESSAGE, '', '', '','inlayer');
			}
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});	
}

function callbackCertifyJoinMember(args) {
	var checkCertMbr = (args.checkCertMbr != null ) ? JSON.parse(args.checkCertMbr) : "";
	var is14 = args.is14;
	var joinPossibility = args.joinPossibility;
	var possibilityCode = args.possibilityCode;
	var pcc = args.pcc;	
		
	// 이미 가입된 회원이 있을 경우
	if(checkCertMbr.mbr && args.checkSameMbrYn == "Y") {
		alert("인증이 완료되었습니다.");
		$("#REQ_SEQ").remove();
		$("#RES_SEQ").remove();
		$("#CERT_END_YN").val("Y");			
		
		$("#mbrNm").prop("disabled", true);	
		$("#birthyear").prop("disabled", true);	
		$("#birthmonth").prop("disabled", true);	
		$("#birthdate").prop("disabled", true);	
		$("#genderRadio").prop("disabled", true);	
		//$("#frgnrYn").prop("readonly", true);	
		//$("#frgnrYn").attr("onclick", "return false");
		$("#frgnrYn").attr("onfocus", "this.initialSelect=this.selectedIndex");	
		$("#frgnrYn").attr("onchange", "this.selectedIndex=this.initialSelect");	
			
		$("#mobileCo").prop("disabled", true);	
		$("#newMobileNumber").prop("disabled", true);	
		$("#certAgreeYn").prop("readonly", true);	
		$("#certAgreeYn").attr("onclick", "return false");
		
		//$("#certBtn").hide();	
		//$("#certEndBtn").show();
		//$("#certProcess").hide();
		
		var mobilAreaNo = "";
		var mobilTlofNo = "";
		var mobilTlofWthnNo = "";
		if(pcc.cellNo.length < 11){
			mobilAreaNo = pcc.cellNo.substring(0,3);
			mobilTlofNo = pcc.cellNo.substring(3,6);
			mobilTlofWthnNo = pcc.cellNo.substring(6,10);
		}else{
			mobilAreaNo = pcc.cellNo.substring(0,3);
			mobilTlofNo = pcc.cellNo.substring(3,7);
			mobilTlofWthnNo = pcc.cellNo.substring(7,11);
		}
		
		$("#mobilAreaNo").val(mobilAreaNo);
		$("#mobilTlofNo").val(mobilTlofNo);
		$("#mobilTlofWthnNo").val(mobilTlofWthnNo);
		$("#mobileNumber").val(pcc.cellNo);
		
		$("#popCertificationBtn").text("인증완료").prop("onclick", "");
		layerPopup.popupCloseNow('#popCertification');
	} else if(checkCertMbr.mbr && args.checkSameMbrYn == "N") {
		layerPopup.popupOpenNow('#layerPopCertificationCheck05');
	} else {
		layerPopup.popupOpenNow('#layerPopCertificationCheck04');
	}
	
}
function joinTermView(){
	
	if($.trim($("#popTermsAgreeCon1").html()) == ""){	
		$.ajax({
			type : "POST",
			url : "/member/join/getTerms.json",
			data : "",
			beforeSend: function (request)
			{
				var csrfToken = $("meta[name='_csrf']").attr("content");
				var csrfName = $("meta[name='_csrf_header']").attr("content");
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(args) {
				//console.log(args)
				layerPopup.popupOpenNow('#popTermsAgree');
				$("#popTermsAgreeCon1").html(args.onlineSiteUsefStplat.stplatCont);
				$("#popTermsAgreeCon2").html(args.psnlInfoColctUsefAgr.stplatCont);
				$(".agree-link a").click(function(e){
					e.preventDefault();
			        var hrefNm = $(this).attr("href");
			        var object = $(hrefNm);
			        if(object){
			        	var posTop = $(this).closest(".agree-section-content");
			        	posTop.animate({scrollTop: object.offset().top - posTop.offset().top}, 100);
			        }
			    });
			},
			error : function(e) {
				alertLayer(MESSAGES['common.js.error.msg1']);
			}
		});	
	}else{
		layerPopup.popupOpenNow('#popTermsAgree');
		$(".agree-link a").click(function(e){
			e.preventDefault();
	        var hrefNm = $(this).attr("href");
	        var object = $(hrefNm);
	        if(object){
	        	var posTop = $(this).closest(".agree-section-content");
	        	posTop.animate({scrollTop: object.offset().top - posTop.offset().top}, 100);
	        }
	    });
	}	
}

function setChildBirthMonth(val, obj){
	$(obj).parent().parent().parent().parent().find("input[name='childrenBirthMonth']").val(val);
}
