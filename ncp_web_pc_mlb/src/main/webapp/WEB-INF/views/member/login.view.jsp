<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>

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

function check_capslock(e) { //check what key was pressed in the form	
    var s = String.fromCharCode(e.keyCode);
    if (s.toUpperCase() === s && s.toLowerCase() !== s && !e.shiftKey) {
        window.capsLockEnabled = true;
        //$('#capslockdiv').show();
        //console.log("on")
        $("#loginForm").find("p.error-msg").html("Caps Lock이 켜져 있습니다.");
        errorMsgShow($("#loginForm").find("p.error-msg"));
    }
    else {
        window.capsLockEnabled = false;
        //$('#capslockdiv').hide();
        //console.log("off")
        errorMsgHide($("#loginForm").find("p.error-msg"));
    }
}

function check_capslock_form(where) {
    if (!where) { where = $(document); }
    /* where.find('input[name="password"]').each(function () {
        if (this.type != "hidden") {
            $(this).keypress(check_capslock);
        }
    }); */
    
    where.find('input[name="password"]').keypress(check_capslock);
}

$(document).ready(function(){
	check_capslock_form($('#loginForm')); //applies the capslock check to all input tags	
});


</script>

	<div class="contain mb login" id="contain">		
		<div class="container">

			<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false" />
			<main class="contents" id="contents">					
				<div class="login-wrap">
					<div class="login-cnt d_tab02">
						<ul class="tab-type05">
							<li class="d_tab02_select on"><a href="javascript:;"><spring:message code='member.common.member' /></a></li><%-- 회원 --%>
							<li class="d_tab02_select"><a href="javascript:;"><spring:message code='member.login.tab.guest.list.order' /></a></li><%-- 비회원 (주문조회) --%>
						</ul>
								
						<div class="d_tab02_cont" style="display:block;">
							<form:form id="loginForm" method="post" action='${_env.getProperty("ncp_web_pc_mlb.login.process.url") }'>	
							<input type="hidden" name="accessToken" id="accessToken" value=""/>
							<input type="hidden" name="loginTarget" id="loginTarget" value="<c:out value='${loginTarget}' />"/> 
							<input type="hidden" name="remember-me" id="autologin" value="true">
							<input type="hidden" name="pageMode" value="false"/>		
							<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.id.ttl' />" placeholder="<spring:message code='member.join.lbl.id' />" id="userId" name="userId" value="${UID}"
									validate="required;" validateText="<spring:message code='member.join.lbl.id' />"/>
							<input type="password" class="input-style01" title="<spring:message code='member.login.lbl.pw.ttl' />" placeholder="<spring:message code='member.join.lbl.password' />" id="password" name="password"
									validate="required;" validateText="<spring:message code='member.join.lbl.password'/>"/>
							<p class="login-check-id">
								<span class="check-skin">
									<input type="checkbox" id="chkSaveId" name="chkSaveId" ${UID != null and UID != "" ? "checked" : "" }>
									<span><spring:message code='member.common.choice' /></span> <%--선택 --%>
								</span>
								<label for="chkSaveId"><spring:message code='member.login.chk.id.save' /></label> <%-- 아이디 저장 --%>
							</p>
							<p class="error-msg"></p>
							
							<div class="btn-wrap">
								<a href="#" class="btn-style02" id="login" onClick="return false;"><spring:message code='member.login.btn.login' /></a> <%-- 로그인 --%>
								<a href="#" class="btn-npay02" id="naverLogin" onClick="return false;"><span><spring:message code='member.login.btn.naverLogin' /></span></a><%-- 네이버 아이디 로그인 --%>
							</div>
							<div class="login-btn-lnk">
								<a href="#" id="findId" onClick="return false;"><spring:message code='member.find.tab.id' /></a><%-- 아이디 찾기 --%>
								<a href="#" id="findPassword" onClick="return false;"><spring:message code='member.find.tab.pw' /></a><%-- 비밀번호 찾기 --%>
								<a href="#" id="join" onClick="return false;"><spring:message code='member.join.subttl.step1' /></a><%-- 회원가입 --%>
							</div>
							</form:form>
						</div>
						
						<div class="d_tab02_cont">
							<form:form id="guestForm" method="post" action='${_env.getProperty("ncp_web_pc_mlb.login.process.url") }'>
							<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.guest.order.ttl' />" placeholder="<spring:message code='member.login.lbl.guest.order' />" name="ordNo"
							validate="required;" validateText="<spring:message code='member.login.lbl.guest.order' />" maxlength = "17">
							<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.guest.ttl' />" placeholder="<spring:message code='member.login.lbl.guest' />" name="cstmrNm"
							validate="required;" validateText="<spring:message code='member.login.lbl.guest' />">							
							<div class="phon-wrap">
								<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.guest.phone1' />" id="mobilAreaNo"
								validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength = "3" value="010">
								<span class="hyphen">-</span>
								<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.guest.phone2' />" id="mobilTlofNo"
								validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength = "4">
								<span class="hyphen">-</span>
								<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.guest.phone3' />" id="mobilTlofWthnNo"
								validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength = "4">
								<input type="hidden" name="cstmrMobilNo" validate="required;phone" validateText="<spring:message code='member.join.lbl.mobile' />" />
							</div>							
							<p class="error-msg"></p>
							<div class="btn-wrap">
								<a href="#" class="btn-style02" id="guestLogin" onClick="return false;"><spring:message code='member.login.btn.guest.order.list' /></a><%-- 주문조회 --%>
							</div>
							
							<div class="login-btn-lnk">
								<a href="#" id="guestCounsel" onClick="return false;"><spring:message code='member.login.lbl.guest.counsel' /></a><%-- 비회원 1:1 고객상담 --%>
								<a href="#" id="guestGroupCounsel" onClick="return false;"><spring:message code='member.login.lbl.guest.group.purchase' /></a><%-- 비회원 단체구매 문의 --%>
								<%--<a href="#" id="guestAllianceCounsel" onClick="return false;"><spring:message code='member.login.lbl.guest.alliance' /></a>  비회원 제휴 문의 --%>
								<a href="/helpdesk/faq/list"><spring:message code='member.find.view.txt3' /></a></li><%-- FAQ바로가기 --%>
								<a href="#" onclick="goKakaoTalkOpen(); return false;"><spring:message code='member.find.view.txt4' /></a><%-- >카카오톡 상담하기 --%>
							</div>
							
							<p class="login-cs-info"><spring:message code='member.login.lbl.footer.info' /></p>
							</form:form>
						</div>
						
					</div>
					<!-- 혜택정보 -->
					<jsp:include page="/WEB-INF/views/member/include/benefit.info.jsp" flush="false"/>		
				</div>						
			</main>
			
		</div>
	</div>
