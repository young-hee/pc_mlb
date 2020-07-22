<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


		<article id="naverConnectCompletePopup" class="layer-popup layer-type02 popNaverCerti">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.naver.lbl.login.connect.txt1' /></h2><%-- 네이버 로그인 연결 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03"><spring:message code='member.naver.lbl.login.connect.txt2' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn fill lg" onclick="closeNaverConnectCompletePopup(true);return false;"><spring:message code='common.js.confirm' /></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>

		<article id="naverConnectFailPopup" class="layer-popup layer-type02 popNaverCerti">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.naver.lbl.login.connect.txt1' /></h2><%-- 네이버 로그인 연결 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03"><spring:message code='member.naver.lbl.login.connect.txt3' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn fill lg" onclick="closeNaverConnectCompletePopup(false); return false;"><spring:message code='common.js.confirm' /></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>

		<article id="naverCertCompletePopup" class="layer-popup layer-type02 popNaverCerti">
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
					<a href="#" class="btn fill lg" id="connectIdByNaver" onClick="return false;"><spring:message code='member.naver.btn.cert.complete.txt' /></a><%-- 계정 연결하기 --%>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>

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
		
		<article id="commonLayerPopup" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2></h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt layer-txt"></p>					
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>					
				</div>				
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
		
		<article id="commonLayerPopupForConfirm" class="layer-popup popConfirm">
			<section class="layer-popup-cont" tabindex="0">
				<h2></h2>
				<div class="layer-popup-wrap">
					<p class="layer-txt">
					</p>
				</div>
				<div class="btn-wrap">
					<a href="javascript:callbackConfirmLayer(false);" class="btn"></a>
					<a href="javascript:callbackConfirmLayer(true);" class="btn fill"></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>


		<article id="bottomEmailDis" class="layer-popup emailAgree-pop">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='common.bottom.email.txt1' text='이메일 무단 수집 거부' /></h2>
				<div class="layer-cont">
					
					<div class="emailAgree-popWrap">
						<p class="txt-sub-info02"><spring:message code='common.bottom.email.txt2'/></p>
						<div class="agree-section">
							<section class="agree-section-content">
								<p><spring:message code='common.bottom.email.txt3'/></p>
								<p class="mtSt10"><spring:message code='common.bottom.email.txt4'/></p>
							</section>
						</div>
						<div class="btn-wrap">
							<a href="javasccript:;" class="btn lg fill d_layer_close"><spring:message code='common.js.confirm' text='확인' /></a>
						</div>
					</div>
					
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		
		<article id="bottomEmailPop" class="layer-popup agreeMent-pop">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='common.bottom.newsletter.txt1' text='개인정보수집 및 이용에 대한 동의' /></h2>
				<div class="layer-cont">
					
					<div class="agreeMent-popWrap">
						<ul class="text-list02">
							<li><spring:message code='common.bottom.newsletter.txt2' text='개인정보보호법 제15조 2항에 의한 수집,이용 동의' /></li>
							<li><spring:message code='common.bottom.newsletter.txt3' text='이용목적 : STRETCH ANGELS 최신정보를 제공하기 위하여 사용' /></li>
							<li><spring:message code='common.bottom.newsletter.txt4' text='수집항목 : 이메일' /></li>
							<li><spring:message code='common.bottom.newsletter.txt5' text='보유 및 이용기간 : 수신 둉의 거부 시, 즉시 삭제' /></li>
						</ul>
						<div class="btn-wrap">
							<a href="#" class="btn lg fill" onclick="agreeEmailNewsLetter(); return false;"><spring:message code='common.js.confirm' text='확인' /></a>
						</div>
					</div>
					
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>
		
		<!-- 해외매장 찾기 팝업 -->
		<%@ include file="/WEB-INF/views/goods/include/popFindStore.jsp" %>

