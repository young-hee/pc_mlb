<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<article id="naverConnectCompletePopup" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.naver.lbl.login.connect.txt1' /></h2><%-- 네이버 로그인 연결 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt"><spring:message code='member.naver.lbl.login.connect.txt2' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="javascript:closeNaverConnectCompletePopup(true);" class="btn-style02"><spring:message code='common.js.confirm' /></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>

		<article id="naverConnectFailPopup" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.naver.lbl.login.connect.txt1' /></h2><%-- 네이버 로그인 연결 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt"><spring:message code='member.naver.lbl.login.connect.txt3' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="javascript:closeNaverConnectCompletePopup(true);" class="btn-style02"><spring:message code='common.js.confirm' /></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>

		<article id="naverCertCompletePopup" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.naver.lbl.cert.complete.txt1' /></h2><%-- 네이버 인증 완료 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03"><spring:message code='member.naver.lbl.cert.complete.txt2' /></p>
					<p class="layer-txt04"><spring:message code='member.naver.lbl.cert.complete.txt3' /></p>
					<form id ="loginFormByNaver" method="post">
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<input type="hidden" name="loginTarget" value="<c:out value='${loginTarget}' />"/> 
						<input type="hidden" name="remember-me" value="true">
						<input type="hidden" name="pageMode" value="false"/>
					<div class="layer-input-box">
						<input type="text" class="input-style01" placeholder="<spring:message code='member.join.lbl.id' />" id="userIdByNaver" name="userId"
						validate="required;" validateText="<spring:message code='member.join.lbl.id' />" />
						<input type="password" class="input-style01" placeholder="<spring:message code='member.join.lbl.password' />" id="passwordByNaver" name="password"
						validate="required;" validateText="<spring:message code='member.join.lbl.password' />" />
					</div>
					<p class="error-msg"></p>
					</form>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn-style02" id="connectIdByNaver" onClick="return false;"><spring:message code='member.naver.btn.cert.complete.txt' /></a><%-- 계정 연결하기 --%>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
