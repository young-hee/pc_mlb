$(document).ready(function() {

	// 확인 버튼 클릭시
	$("#confirmBtn").click(function(){
		joinMember();
	}); 

	// 취소 버튼 클릭시
//	$("#cancelBtn").click(function(){
//		confirmLayer(MESSAGES['common.js.ask.cancel']);
//	});

	$("button#emailDefaultDomain").next().find("a").click(function(e){
		var txt = "";
		if($(this).text() != MESSAGES['member.js.join.lbl.email.domain']) {
			txt = $(this).text();
		}

		$("input#mbrEmailDomain").val(txt);
		$("input#mbrEmailDomain").trigger("change");
	});

	var form = $("#joinMbrForm");
	$(form).find(":input[validate]").each(function(i) {
		$(this).on('change', function () {
			var attr = $(this);
			
			if(attr.attr("id") == "mbrId") {
				var mbrId = attr.val();
				var idRe = /^[a-z0-9_]{6,15}$/;

				if (idRe.test(mbrId) == false) {
					attr.closest("li").find(".error-msg").text("6~15자 영문 소문자, 숫자만 사용 가능하며 특수문자는 사용할 수 없습니다.");
					errorMsgShow(attr.closest("li").find(".error-msg"));

					setTimeoutFocus(attr.attr("id"));
					return false;
				}
				
				$.ajax({
					type : "post",
					url : "/member/join/isCheckId.json",
					async : false,
					data : {'${_csrf.parameterName}' : '${_csrf.token}','mbr.mbrId' : mbrId },
					success : function(args) {
						isCheckIdCallback(args, attr);
					},
					error : function(e) {
						alertLayer(MESSAGES['common.js.error.msg1']);
					}
				});
				
			}
			
			else if(attr.attr("id") == "mbrPw") {
			    if(validateMbrPw($("#mbrPw")) === false) {
			    	return;
			    }
			}

			else if(attr.attr("id") == "mbrCheckPw") {
			    if(validateMbrCheckPw($("#mbrCheckPw")) === false) {
			    	return;
			    }
			}
			
			else if(attr.attr("id") == "mbrEmail" || attr.attr("id") == "mbrEmailDomain") {
				var mbrEmail = $("input#mbrEmail").val();
				var mbrEmailDomain = $("input#mbrEmailDomain").val();
				var emailRe = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d)|(([a-z]|\d)([a-z]|\d|-|\.|_|~)*([a-z]|\d)))\.)+(([a-z])|(([a-z])([a-z]|\d|-|\.|_|~)*([a-z])))\.?$/i;
				var email = mbrEmail + "@" + mbrEmailDomain;
				
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
						attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg10']);
						errorMsgShow(attr.closest("li").find(".error-msg"));
	
						setTimeoutFocus(attr.attr("id"));
						return false;
					}
					else {
						$.ajax({
							type : "post",
							url : "/member/join/isCheckEmail.json",
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
					errorMsgHide(attr.closest("li").find(".error-msg"));
				}
			}
			
//			else if(attr.attr("id") == "mobilAreaNo" || attr.attr("id") == "mobilTlofNo" || attr.attr("id") == "mobilTlofWthnNo") {
//				$("#mobileNumber:hidden").val( $("input#mobilAreaNo").val() + $("input#mobilTlofNo").val() + $("input#mobilTlofWthnNo").val() );
//				
//				if(Validator.validateField(form, null, null, null, null, $("input#mobilAreaNo")) == false) {
//					return false;
//				}
//				else if(Validator.validateField(form, null, null, null, null, $("input#mobilTlofNo")) == false) {
//					return false;
//				}
//				else if(Validator.validateField(form, null, null, null, null, $("input#mobilTlofWthnNo")) == false) {
//					return false;
//				}
//				else if( $("#mobileNumber:hidden").val() != "" ) {
//					Validator.validateField(form, null, null, null, null, $("#mobileNumber:hidden"));
//				}
//				else {
//					errorMsgHide($("#mobileNumber:hidden").parent().find("span.error-msg"));
//				}
//			}
			
//			else if(attr.attr("id") == "telAreaNo" || attr.attr("id") == "telTlofNo" || attr.attr("id") == "telTlofWthnNo") {
//				$("#telNumber:hidden").val( $("input#telAreaNo").val() + $("input#telTlofNo").val() + $("input#telTlofWthnNo").val() );
//				if( $("#telNumber:hidden").val() != "" ) {
//					Validator.validateField(form, null, null, null, null, $("#telNumber:hidden"));
//				}
//				else {
//					errorMsgHide($("#telNumber:hidden").parent().find("span.error-msg"));
//				}
//			}
			
		});
	});
	
	// 페이스북 광고 스크립트
	fbq('track', 'Lead');
});

var chkBtn = true;

// 확인 버튼 클릭시
function joinMember() {		
    if(chkBtn == false) {
        return;
    }
    else {
        chkBtn = false;
    }

    var form = $("#joinMbrForm");
        
	if(Validator.validate(form, null, null, true) == false) {
		chkBtn = true;
		return;
	}
	
	if($("#CERT_END_YN").val() === "N"){
		alert("본인인증을 먼저 완료해 주세요.");
		chkBtn = true;
		return;
	}
	
	if(!$("#onlineSiteUsefStplat").is(":checked") || !$("#psnlInfoColctUsefAgr").is(":checked")){
		//$("#termBtn").click();
		$("#termToggle").removeClass("on").addClass("on");
		alertLayer("이용약관과 개인정보 처리방침에 동의 하셔야<br>서비스를 이용하실 수 있습니다.");
		chkBtn = true;
    	return;
	}
        
    if(validateMbrId($("#mbrId")) === false) {
    	chkBtn = true;
    	return;
    }
        
    if(validateMbrPw($("#mbrPw")) === false) {
    	chkBtn = true;
    	return;
    }
    
    
    if(validateMbrCheckPw($("#mbrCheckPw")) === false) {
    	chkBtn = true;
    	return;
    }
    
    if(validateMbrEmail($("#mbrEmail")) === false) {
    	chkBtn = true;
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
    
	// 주소 검증
//	if(isCheckDetailAddr() == false) {
//		chkBtn = true;
//		return;
//	}
	
	// 자녀 정보 검증
	if(isCheckFamilyInfo() == false) {
		chkBtn = true;
		return;
	}
	
	commonFnc.block();
	$.ajax({
		type : "POST",
		url : "/member/join/joinMemberCheck.json",
		data : {'${_csrf.parameterName}' : '${_csrf.token}', 'mbr.mbrEmail' : $("[name='mbr.mbrEmail']:hidden").val() },
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			commonFnc.unblock();
			if(args.duplication == true ) {
				alertLayer(MESSAGES['member.js.join.lbl.error.msg1']);
				chkBtn = true;
			}
			else if(args.isCheckEmail == true ) {
				alertLayer(MESSAGES['member.js.join.lbl.error.msg11']);
				chkBtn = true;
			}
			else {
				console.log("준비완료");
				form.submit();				
			}
		},
		error : function(e) {
			commonFnc.unblock();
			chkBtn = true;
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

function isCheckIdCallback(args, attr) {
	if(args.isCheckId == true || args.isCheckId == "true") {
		attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg3']);
		errorMsgShow(attr.closest("li").find(".error-msg"));

		setTimeoutFocus(attr.attr("id"));
	}
	else {
		errorMsgHide(attr.closest("li").find(".error-msg"));
	}
}

function isCheckEmailCallback(args, attr) {
	if(args.isCheckEmail == true || args.isCheckEmail == "true") {
		attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg11']);
		errorMsgShow(attr.closest("li").find(".error-msg"));
		
		setTimeoutFocus(attr.attr("id"));
	}
	else {
		errorMsgHide(attr.closest("li").find(".error-msg"));
	}
}

function validateMbrId(attr) {
	var mbrId = attr.val();
	var idRe = /^[a-z0-9_]{6,15}$/;

	if (idRe.test(mbrId) == false) {
		attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg2']);
		errorMsgShow(attr.closest("li").find(".error-msg"));

		setTimeoutFocus(attr.attr("id"));
		return false;
	}

	var isCheckId = false;
	$.ajax({
		type : "post",
		url : "/member/join/isCheckId.json",
		async : false,
		data : {'${_csrf.parameterName}' : '${_csrf.token}','mbr.mbrId' : mbrId },
		success : function(args) {
			if(args.isCheckId == true || args.isCheckId == "true") {
				attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg3']);
				errorMsgShow(attr.closest("li").find(".error-msg"));

				setTimeoutFocus(attr.attr("id"));
			}
			else {
				errorMsgHide(attr.closest("li").find(".error-msg"));
				isCheckId = true;
			}
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});

	return isCheckId;
}

function validateMbrPw(attr) {
	if(validatePassword(attr.val(), attr) == false) {
		return false;
	}
	else {
		attr.closest("li").find(".error-msg").hide();
	}

	var mbrCheckPw = $("input#mbrCheckPw").val();
	var mbrPw = $("input#mbrPw").val();
	if(mbrCheckPw != "") {
		if(mbrPw != mbrCheckPw) {
			$("input#mbrCheckPw").closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg6']);
			errorMsgShow($("input#mbrCheckPw").closest("li").find(".error-msg"));

			setTimeoutFocus('mbrCheckPw');
			return false;
		}
		else {
			errorMsgHide($("input#mbrCheckPw").closest("li").find(".error-msg"));
		}
	}
}

function validateMbrCheckPw(attr) {
	var mbrCheckPw = $("input#mbrCheckPw").val();
	var mbrPw = $("input#mbrPw").val();
	if(mbrCheckPw != "") {
		if(mbrPw != mbrCheckPw) {
			$("input#mbrCheckPw").closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg6']);
			errorMsgShow($("input#mbrCheckPw").closest("li").find(".error-msg"));

			setTimeoutFocus('mbrCheckPw');
			return false;
		}
		else {
			errorMsgHide($("input#mbrCheckPw").closest("li").find(".error-msg"));
		}
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
			attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg10']);
			errorMsgShow(attr.closest("li").find(".error-msg"));

			setTimeoutFocus(attr.attr("id"));
			return false;
		}
		else {
			$.ajax({
				type : "post",
				url : "/member/join/isCheckEmail.json",
				async : false,
				data : {'${_csrf.parameterName}' : '${_csrf.token}','mbr.mbrEmail' : email },
				success : function(args) {
					if(args.isCheckEmail == true || args.isCheckEmail == "true") {
						attr.closest("li").find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg11']);
						errorMsgShow(attr.closest("li").find(".error-msg"));
						
						setTimeoutFocus(attr.attr("id"));
					}
					else {
						errorMsgHide(attr.closest("li").find(".error-msg"));
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
		errorMsgHide(attr.closest("li").find(".error-msg"));
		isCheckEmail = true;
	}
	
	return isCheckEmail;
}

//주소 입력시 상세주소 검증
/*function isCheckDetailAddr() {
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
}*/

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
					obj.parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg12']);
					errorMsgShow(obj.parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				if(maxLengthCheck(obj.find("input[name^='childrenName']").attr("id"), obj.find("input[name^='childrenName']").attr("maxlength")) == false) {					
					obj.parent().parent().find(".error-msg").text(messageFormat(MESSAGES['member.js.join.lbl.error.msg15'], obj.find("input[name^='childrenName']").attr("maxlength")));
					errorMsgShow(obj.parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				if( isValidDate(childrenBirthYear+childrenBirthMonth+childrenBirthDay) == false ) {					
					obj.parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg13']);
					errorMsgShow(obj.parent().parent().find(".error-msg"));
					flag = false;
					return false;
				}
				
				obj.find("input[name^='childrenBirthDay']").val(childrenBirthDay);
				obj.find("input[name^='childrenBirthDate']").val(childrenBirthYear+childrenBirthMonth+childrenBirthDay);
			}
			
			/*if( !(childrenName != "" && childrenBirthYear != "" && childrenBirthMonth != "" && childrenBirthDay != "")
					&& !(childrenName == "" && childrenBirthYear == "" && childrenBirthMonth == "" && childrenBirthDay == "")
					) {
				obj.parent().parent().find(".error-msg").text(MESSAGES['member.js.join.lbl.error.msg12']);
				errorMsgShow(obj.parent().parent().find(".error-msg"));
				flag = false;
				return false;
			}
			else if(maxLengthCheck(obj.find("input[name^='childrenName']").attr("id"), obj.find("input[name^='childrenName']").attr("maxlength")) == false) {
				obj.parent().parent().find(".error-msg").text(messageFormat(MESSAGES['member.js.join.lbl.error.msg15'], obj.find("input[name^='childrenName']").attr("maxlength")));
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
			}*/
		}
	});
	
	if(flag) {
		errorMsgHide(obj.parent().parent().find(".error-msg"));
	}
	
	return flag;
}

function callbackConfirmLayer(flag) {
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
				window.location.href = "/main/mall/view";
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

//우편번호 팝업
function openZipcodePopup() {
    layerPopup.popupOpenNow("#layerPopupZipcode");
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
	
	var form = $("#joinMbrForm");
	
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
	
	$("#mobileNumber").val($("#phone1").val()+$("#phone2").val()+$("#phone3").val());	
	$("#mobileNumber").attr("validate", "required;phone");
	$("#mobileNumber").attr("validateText", "휴대폰 번호");
	if(Validator.validateField(form, null, null, true, null, $("#mobileNumber")) == false) {
		return;
	}
	
	if(Validator.validateField(form, null, null, true, null, $("#mobileCo")) == false) {
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
				mobileNumber : $("#mobileNumber").val()
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
	
	var form = $("#joinMbrForm");
	
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
			mobileNumber : $("#mobileNumber").val(),
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
				alertLayer(args.RETURN_MESSAGE);
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
	
	alert("인증이 완료되었습니다.");
	$("#REQ_SEQ").remove();
	$("#RES_SEQ").remove();
	$("#CERT_END_YN").val("Y");			
	
	if(pcc.fgnGbn == "Y"){
		$("#mbrNmAfter").html(pcc.name + " (외국인)");	
	}else{
		$("#mbrNmAfter").html(pcc.name + " (내국인)");	
	}
	
	if(pcc.sex == "9" || pcc.sex == "5" || pcc.sex == "7" || pcc.sex == "1" || pcc.sex == "3"){
		$("#birthAfter").html(pcc.birYMD.substring(0, 4) + "년 " + pcc.birYMD.substring(4, 6) + "월 " + pcc.birYMD.substring(6) + "일" + " (남자)");
	}else{
		$("#birthAfter").html(pcc.birYMD.substring(0, 4) + "년 " + pcc.birYMD.substring(4, 6) + "월 " + pcc.birYMD.substring(6) + "일" + " (여자)");
	}
	
	if(pcc.cellCorp == "1"){
		$("#phoneAfter").html(pcc.cellNo + " (SKT)");			
	}else if(pcc.cellCorp == "2"){
		$("#phoneAfter").html(pcc.cellNo + " (KT)");	
	}else if(pcc.cellCorp == "3"){
		$("#phoneAfter").html(pcc.cellNo + " (LG U+)");	
	}else if(pcc.cellCorp == "4"){
		$("#phoneAfter").html(pcc.cellNo + " (SKT 알뜰폰)");	
	}else if(pcc.cellCorp == "5"){
		$("#phoneAfter").html(pcc.cellNo + " (KT 알뜰폰)");	
	}else{
		$("#phoneAfter").html(pcc.cellNo + " (LG U+ 알뜰폰)");	
	}
	
		
	$("#certProcess").hide();
	$("#certProcessEnd").show();
	
		
	/*// 14세 미만인 경우
	if(!is14) {
		$("#layerPopCertificationCheck03").find(".layer-txt").html(MESSAGES['member.js.certification.layer.check.03.txt1']);
		layerPopup.popupOpenNow('#layerPopCertificationCheck03');
	}
	// 이미 가입된 회원이 있을 경우
	else if(checkCertMbr.mbr) {
		if(checkCertMbr.mbr.mbrStatCd == "ACT" || checkCertMbr.mbr.mbrStatCd == "DRMNCY") {
			$("#layerPopCertificationCheck02").find("[name=layerPopupJoinDt]").html(checkCertMbr.joinDtStr);
			$("#layerPopCertificationCheck02").find("[name=layerPopupMbrId]").html(checkCertMbr.mbr.mbrId);
			layerPopup.popupOpenNow('#layerPopCertificationCheck02');
		}
		// 탈퇴 30일 이내
		else if(checkCertMbr.mbr.mbrStatCd == "SECSN") {			
			$("#layerPopCertificationCheck01").find("[name=layerPopupSecsnDt]").html(checkCertMbr.secsnDtStr);
			layerPopup.popupOpenNow('#layerPopCertificationCheck01');			
		}
	}
	// 회원 가입이 불가한 경우(탈퇴 30일 이내, 직권탈퇴)
	else if(joinPossibility == "false") {
		if(possibilityCode != undefined && possibilityCode != null && possibilityCode == "T") {
			$("#layerPopCertificationCheck01").find(".join-date-info").hide();
			layerPopup.popupOpenNow('#layerPopCertificationCheck01');
		}
		else {
			$("#layerPopCertificationCheck03").find(".layer-txt").html(MESSAGES['member.js.certification.layer.check.03.txt2']);
			layerPopup.popupOpenNow('#layerPopCertificationCheck03');
		}
	}
	else {
		alert("인증이 완료되었습니다.");
		$("#REQ_SEQ").remove();
		$("#RES_SEQ").remove();
		$("#CERT_END_YN").val("Y");			
		
		if(pcc.fgnGbn == "Y"){
			$("#mbrNmAfter").html(pcc.name + " (외국인)");	
		}else{
			$("#mbrNmAfter").html(pcc.name + " (내국인)");	
		}
		
		if(pcc.sex == "9" || pcc.sex == "5" || pcc.sex == "7" || pcc.sex == "1" || pcc.sex == "3"){
			$("#birthAfter").html(pcc.birYMD.substring(0, 4) + "년 " + pcc.birYMD.substring(4, 6) + "월 " + pcc.birYMD.substring(6) + "일" + " (남자)");
		}else{
			$("#birthAfter").html(pcc.birYMD.substring(0, 4) + "년 " + pcc.birYMD.substring(4, 6) + "월 " + pcc.birYMD.substring(6) + "일" + " (여자)");
		}
		
		if(pcc.cellCorp == "1"){
			$("#phoneAfter").html(pcc.cellNo + " (SKT)");			
		}else if(pcc.cellCorp == "2"){
			$("#phoneAfter").html(pcc.cellNo + " (KT)");	
		}else if(pcc.cellCorp == "3"){
			$("#phoneAfter").html(pcc.cellNo + " (LG U+)");	
		}else if(pcc.cellCorp == "4"){
			$("#phoneAfter").html(pcc.cellNo + " (SKT 알뜰폰)");	
		}else if(pcc.cellCorp == "5"){
			$("#phoneAfter").html(pcc.cellNo + " (KT 알뜰폰)");	
		}else{
			$("#phoneAfter").html(pcc.cellNo + " (LG U+ 알뜰폰)");	
		}
		
			
		$("#certProcess").hide();
		$("#certProcessEnd").show();
				
	}*/
	
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
			},
			error : function(e) {
				alertLayer(MESSAGES['common.js.error.msg1']);
			}
		});	
	}else{
		layerPopup.popupOpenNow('#popTermsAgree');
	}	
}
function addChildView(obj){
	if($("#addChild1").is(":visible")){
		$("#addChild2").show();
		$(obj).hide();
	}else{
		$("#addChild1").show();
	}	
}
function setChildBirthMonth(val, obj){
	$(obj).parent().parent().parent().parent().find("input[name='childrenBirthMonth']").val(val);
}