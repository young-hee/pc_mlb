<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>

<script>
$(document).ready(function(){
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
})
</script>	
		<!-- 로그인 팝업  -->
		<article id="loginPopup" class="layer-popup popLogin">
			<section class="layer-popup-cont" tabindex="0">
				<h2>로그인</h2>
				<div class="layer-popup-wrap">
					<form id ="loginForm" method="post">					
					<input type="hidden" name="accessToken" id="accessToken" value=""/>
					<input type="hidden" name="loginTarget" id="loginTarget" value="<c:out value='${loginTarget}' />"/> 
					<input type="hidden" name="remember-me" id="autologin" value="true">
					<input type="hidden" name="pageMode" value="true"/>
					<input type="hidden" name="loginCallback" id="loginCallback" value=""/>
					<input type="hidden" name="loginActionParam" id="loginActionParam"/>
					
					<div class="msg">
						<p class="p1" style="display:none" id="loginTopTxt1">로그인후 구매하시면<br>쿠폰/포인트/마일리지 등의 혜택을 받을 수 있습니다.</p>				
						<p class="p2" style="display:none" id="loginTopTxt2">로그인 하시면, 문의하신 내용에 대한 답변을 <br> 마이페이지에서 확인하실 수 있습니다.</p>
						<p class="p2" style="display:none" id="loginTopTxt3">로그인 후 이용하실 수 있습니다.</p> 
					</div>
					<div class="inputs">
						<input type="text" class="input-style01" title="<spring:message code='member.login.lbl.id.ttl' />" placeholder="<spring:message code='member.join.lbl.id' />" id="userId" name="userId" value="${UID}"
							validate="required;" validateText="<spring:message code='member.join.lbl.id' />">
						<input type="password" class="input-style01" title="<spring:message code='member.login.lbl.pw.ttl' />" placeholder="<spring:message code='member.join.lbl.password' />" id="password" name="password" value=""
							validate="required;" validateText="<spring:message code='member.join.lbl.password' />">
					</div>
					<p class="login-check-id">
						<span class="check-skin">
							<input type="checkbox" id="chkSaveId" name="chkSaveId" ${UID != null and UID != "" ? "checked" : "" }>
							<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
						</span>
						<label for="chkSaveId"><spring:message code='member.login.chk.id.save' /></label><%-- 아이디 저장 --%>
					</p>
					<p class="error-msg"></p>
					
					<div class="btns bt1">
						<a href="#" class="btn-login btn-style02" id="popLogin" onClick="return false;"><spring:message code='member.login.btn.login' /></a><%-- 로그인 --%>
						<a href="#" class="btn-np btn-npay02" id="popNaverLogin" onClick="return false;"><span><spring:message code='member.login.btn.naverLogin' /></span></a><%-- 네이버 아이디 로그인 --%>						
					</div>
		
					<div class="login-btn-lnk">
						<a href="#" id="findId" onClick="return false;"><spring:message code='member.find.tab.id' /></a><%-- 아이디 찾기 --%>
						<a href="#" id="findPassword" onClick="return false;"><spring:message code='member.find.tab.pw' /></a><%-- 비밀번호 찾기 --%>
						<a href="#" id="join" onClick="return false;"><spring:message code='member.join.subttl.step1' /></a><%-- 회원가입 --%>						
					</div>
		
					<div class="btns bt2">
						<a href="#" class="btn lg gray" id="guestOrder" onClick="return false;" style="display:none;">비회원 주문하기</a>		
						<a href="#" class="btn lg gray" id="guestGroupCounsel" onClick="return false;" style="display:none;">비회원 단체구매 문의</a>
						<a href="#" class="btn lg gray" id="guestCounsel" onClick="return false;" style="display:none;">비회원 문의하기</a>		
					</div>
					
					<%-- 
					<div class="btns bt3">
						
					</div>
					--%>
					<ul class="text-list02 bulList" style="display:none;" id="guestCounselTxt">
						<li>비회원으로 문의하신 내용은 이메일로 답변 드립니다.</li>
					</ul>

					
					</form>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		<!-- 
		<article id="releaseDrmncyPopup" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.login.layer.drmncy.ttl' /></h2><%-- 휴면 계정 해제 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03"><spring:message code='member.login.layer.drmncy.txt1' /></p>
					<p class="layer-txt04"><spring:message code='member.login.layer.drmncy.txt2' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="javascript:callbackReleaseDrmncyAfter('releaseDrmncyPopup');" class="btn-style02"><spring:message code='common.js.confirm' /></a>
				</div>
				<div class="layer-popup-close" style="display:none;">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		 -->

<%-- 광고영역 --%>
<!-- WIDERPLANET  SCRIPT START 2018.8.9 -->
<script type="text/javascript">
var wptg_tagscript_vars = wptg_tagscript_vars || [];
wptg_tagscript_vars.push(
(function() {
    return {
        wp_hcuid:"",
        ti:"39428",
        ty:"Login",
        device:"web",
        items:[{
            i:"로그인",
            t:"로그인",
            p:"1",
            q:"1"
        }]
    };
}));
fnf_appendTargetGateScript();
</script>
<!-- // WIDERPLANET  SCRIPT END 2018.8.9 -->
