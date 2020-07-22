<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>
			
			<%@ include file="../include/lnb.jspf" %>

			<main class="contents memberPassWord-wrap" id="contents">
				<form id="checkPasswordForm" name="checkPasswordForm">
				<input type="hidden" name="targetUrl" value="${targetUrl}" />
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="checkPasswordFlag" value="${checkPasswordFlag}" />
				<input type="hidden" name="targetPath" value="${targetPath}" />
				
				<input type="text" name="name1" style="display:none;"/><%-- form 안에 password만 있으면 엔터 입력시 이상한 동작을 하게되어 빈 text 작성. --%>
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<div class="board-write">
					<p class="txt13-666"><spring:message code='mypage.member.check.password.txt1' /></p>
					<input type="password" class="input-style01" style="width:400px;" id="password1" name="mbr.mbrPw" placeholder="<spring:message code='member.join.lbl.password' />"
							validate="required;" validateText="<spring:message code='member.join.lbl.password' />">
					<span class="error-msg"></span>					
				</div>
				</form>
				<div class="btnWrapBox">
					<a href="#" class="btn" id="checkPasswordCancel" onClick="return false;"><spring:message code='common.js.cancel' /></a>
					<a href="#" class="btn fill" id="checkPasswordConfirm" onClick="return false;"><spring:message code='common.js.confirm' /></a>
				</div>
				
			</main>
		</div>
	</div>
			
<script type="text/javascript">

document.onkeydown = function (e) { //check if capslock key was pressed in the whole window
    e = e || event;
    if (typeof (window.lastpress) === 'undefined') { window.lastpress = e.timeStamp; }
    if (typeof (window.capsLockEnabled) !== 'undefined') {
        if (e.keyCode == 20 && e.timeStamp > window.lastpress + 50) {
            window.capsLockEnabled = !window.capsLockEnabled;
            //$('#capslockdiv').toggle();
        }
        window.lastpress = e.timeStamp;
        //sometimes this function is called twice when pressing capslock once, so I use the timeStamp to fix the problem
    }

}


$(document).ready(function() {
	// 취소 버튼
	$("#checkPasswordCancel").click(function(){
		movePage('/mypage/view');
	});
	
	// 확인 버튼
	$("#checkPasswordConfirm").click(function(){
		checkPassword();
	});

	$("#password1").keyup(function(e){
		if(e.keyCode == 13) {			
			checkPassword();			
		}
	});
	
	check_capslock_form($('#checkPasswordForm')); //applies the capslock check to all input tags	
	
});


function check_capslock(e) { //check what key was pressed in the form	
    var s = String.fromCharCode(e.keyCode);
    if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
        window.capsLockEnabled = true;
        //$('#capslockdiv').show();
        //console.log("on")
        $("#checkPasswordForm").find(".error-msg").html("Caps Lock이 켜져 있습니다.");
        errorMsgShow($("#checkPasswordForm").find(".error-msg"));
    }
    else {
        window.capsLockEnabled = false;
        //$('#capslockdiv').hide();
        //console.log("off")
        errorMsgHide($("#checkPasswordForm").find(".error-msg"));
    }
}

function check_capslock_form(where) {
    if (!where) { where = $(document); }
    /* where.find('input[name="password"]').each(function () {
        if (this.type != "hidden") {
            $(this).keypress(check_capslock);
        }
    }); */
    
    where.find('input[name="mbr.mbrPw"]').keypress(check_capslock);
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

function checkPassword() {	
	var form = $("#checkPasswordForm");
	if(Validator.validate(form, null, null, true) == false) {
		return;
	}
    
	$.ajax({
		type : "POST",
		url : "/mypage/member/checkPassword.json",
		data : $("#checkPasswordForm").serialize(),
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			// 정상
			if(args.isFlag != undefined && args.isFlag == true) {
				var targetUrl = $("[name=targetUrl]:hidden").val();
				var param = null;
				 param = {
					'${_csrf.parameterName}' : '${_csrf.token}'
			        , 'checkPasswordFlag' : args.checkPasswordFlag
			        /* , 'dspCtgryNo' :  $("#lnbDspCtgryForm").find("#dspCtgryNo").val()					// 메뉴 유지를 위한 데이터
			        , 'currentCtgryDpthCd' :  $("#lnbDspCtgryForm").find("#currentCtgryDpthCd").val()	// 메뉴 유지를 위한 데이터
			        , 'ctgrySectCd' :  $("#lnbDspCtgryForm").find("#ctgrySectCd").val()					// 메뉴 유지를 위한 데이터
			        , 'ctgryNoDpth1' :  $("#lnbDspCtgryForm").find("#ctgryNoDpth1").val()				// 메뉴 유지를 위한 데이터
			        , 'ctgryNoDpth2' :  $("#lnbDspCtgryForm").find("#ctgryNoDpth2").val()				// 메뉴 유지를 위한 데이터
			        , 'ctgryNoDpth3' :  $("#lnbDspCtgryForm").find("#ctgryNoDpth3").val()				// 메뉴 유지를 위한 데이터 */
			        , 'targetPath' : $("#checkPasswordForm").find("[name=targetPath]").val()
			    }; 
				movePage('/mypage/member/' + targetUrl, param);
			}
			// 비정상
			else {
				if(args.failMessage != undefined && args.failMessage != '') {
					$("#password1").next().html(args.failMessage);	
				}
				else {
					$("#password1").next().html(MESSAGES['mypage.js.member.check.password.txt1']);	
				}
				errorMsgShow($("#password1").next());
			}
		},
		error : function(e) {
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

</script>