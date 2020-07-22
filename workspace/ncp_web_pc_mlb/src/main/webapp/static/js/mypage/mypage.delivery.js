$(document).ready(function() {

	var form = $("#gForm");
	$(form).find(":input[validate]").each(function(i) {
		
		var attr = $(this);
		var inputId   = attr.attr("id"); // Input ID
		var elInputId = "#" + inputId;   // el
		
		$(this).on('keyup', function () {
			
			// 받는사람 문자열 자리수 체크
			if(inputId == "labelName" ) {
				
				if( $(elInputId).val().length > 10 ) {
					
					$("#error-lbName").html(MESSAGES['mypage.js.member.delivery.error.msg1']);
					errorMsgShow($(elInputId).parent().find("span.error-msg"));
				}
				else if( $(elInputId).val().length > 0 ) {
					$("#error-lbName").html("") ; 
					errorMsgHide($(elInputId).parent().find("span.error-msg"));
				}
			}
			
			// 핸드폰, 전화번호 숫자만 입력 가능
			else if( inputId == "mobilAreaNo" || inputId == "mobilTlofNo" || inputId == "mobilTlofWthnNo" ||
				inputId == "telAreaNo"   || inputId == "telTlofNo"   || inputId == "telTlofWthnNo") {
				
				var numChk = $(elInputId).val();
				
				if( $(elInputId).val() != "" || $(elInputId).val() != null ) {
					var newNumChk = numChk.toString().replace(/[^0-9]/gi, "");
					$(elInputId).val(newNumChk);
				}
				
				if( inputId == "mobilAreaNo" || inputId == "mobilTlofNo" || inputId == "mobilTlofWthnNo" ){
					
					if( $(elInputId).val() == "" ) {
						Validator.validateField(form, null, null, true, null, $(elInputId));
					}else{
						errorMsgHide($(elInputId).parent().find("span.error-msg"));
					}
				}
			}
			// 상세주소
			else if( inputId == "dlvspDetailAddr" ){
				
				if( $(elInputId).val() == "" ) {
					Validator.validateField(form, null, null, true, null, $(elInputId));
				}else{
					errorMsgHide($(elInputId).parent().find("span.error-msg"));
				}
			}
			
			
		});
		
		
		$(this).on('change', function () {
			
			// 필수값 null 체크
			if(inputId == "labelName" 
				|| inputId == "mobilAreaNo" || inputId == "mobilTlofNo" || inputId == "mobilTlofWthnNo" || inputId == "dlvspDetailAddr" ) {
				
				if( $(elInputId).val() == "" ) {
					Validator.validateField(form, null, null, true, null, $(elInputId));
				}
				else {
					errorMsgHide($(elInputId).parent().find("span.error-msg"));
				}
				
				
				if($("input#mobilAreaNo").val() != "" && $("input#mobilTlofNo").val() != "" && $("input#mobilTlofWthnNo").val() != ""){
					$("#mobileNumber:hidden").val( $("input#mobilAreaNo").val() + $("input#mobilTlofNo").val() + $("input#mobilTlofWthnNo").val() );
					
					if( $("#mobileNumber:hidden").val() != "" ) {
						Validator.validateField(form, null, null, true, null, $("#mobileNumber:hidden"));
					}
					else {
						errorMsgHide($("#mobileNumber:hidden").parent().find("span.error-msg"));
					}
				}
			}
			else if(inputId == "telAreaNo" || inputId == "telTlofNo" || inputId == "telTlofWthnNo") {
				
				$("#telNumber:hidden").val( $("input#telAreaNo").val() + $("input#telTlofNo").val() + $("input#telTlofWthnNo").val() );
				
				if( $("#telNumber:hidden").val() != "" ) {
					Validator.validateField(form, null, null, null, null, $("#telNumber:hidden"));
				}
				else {
					errorMsgHide($("#telNumber:hidden").parent().find("span.error-msg"));
				}
			}
		});
		
		
		$(this).on('focusout', function () {
			
			if(inputId == "labelName" ) {
				if( $(elInputId).val() == "" ) {
					Validator.validateField(form, null, null, true, null, $(elInputId));
				}
			}
		});
		
	
	});
	// (form).find - END -
	
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

    var form = $("#gform");
	if(Validator.validate(form, null, null, true) == false) {
		chkBtn = true;
		return;
	}
	
	// 주소 검증
	if(isCheckDetailAddr() == false) {
		chkBtn = true;
		return;
	}
}

// 주소 입력시 상세주소 검증
function isCheckDetailAddr() {
    var dlvspPostNo = $("input#dlvspPostNo").val();
    var dlvspBaseAddr = $("input#dlvspBaseAddr").val();
    var dlvspDetailAddr = $.trim($("input#dlvspDetailAddr").val());
    
    if(dlvspPostNo.length > 0  &&  dlvspBaseAddr.length > 0 && dlvspDetailAddr.length < 1) {
    	$("input#dlvspDetailAddr").parent().find(".error-msg").text(MESSAGES['mypage.member.delivery.lbl.txt20']);
		errorMsgShow($("input#dlvspDetailAddr").parent().find(".error-msg"));

		setTimeoutFocus("dlvspDetailAddr");
        return false;
    }
    else {
    	errorMsgHide($("input#dlvspDetailAddr").parent().find(".error-msg"));
    }
    
    return true;
}

