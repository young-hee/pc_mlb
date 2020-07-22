<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
				
		<!--  본인인증 확인결과 -->
		<article id="layerPopCertificationCheck01" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2>본인인증 확인결과</h2>
				<div class="layer-popup-wrap">					
					<p class="msg-txt">회원 탈퇴 후 30일이 경과하지 않아 <br> 재가입 하실 수 없습니다.</p>
					<p class="date-info">탈퇴일  :  <strong name="layerPopupSecsnDt"></strong></p>					
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>
					<p class="msg">비회원으로 서비스를 이용하시겠습니까?  <br>  <a class="link" href="/">쇼핑 계속하기</a></p>
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="bots-info">
					<p><strong>080-807-0012</strong> 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
		<!-- 이미가입한 회원 -->
		<article id="layerPopCertificationCheck02" class="layer-popup popCertifiReady">
			<section class="layer-popup-cont" tabindex="0">
				<h2>본인인증 확인결과</h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt">고객님은 이미 가입되어 있습니다.</p>
					<p class="date-info">가입일  :  <strong name="layerPopupJoinDt"></strong></p>
					<p class="id-info">아이디  :  <strong name="layerPopupMbrId"></strong></p>
					<div class="btns"><a href='${_env.getProperty("ncp_web_pc_mlb.login.url") }' class="btn lg fill btn-login">로그인</a></div>
					<p class="msg">비밀번호를 잊으셨나요?  <a class="link" href="#" onclick="moveFindPwd();">비밀번호 찾기</a></p>
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="bots-info">
					<p><strong>080-807-0012</strong> 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>

		<!--  본인인증 확인결과 -->
		<article id="layerPopCertificationCheck03" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2>본인인증 확인결과</h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt">만 14세 이상 대상자만 회원 가입하실 수 있습니다.</p>			
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>		
					<p class="msg">비회원으로 서비스를 이용하시겠습니까?  <br>  <a class="link" href="/">쇼핑 계속하기</a></p>
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="bots-info">
					<p><strong>080-807-0012</strong> 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>

		<!--  본인인증 확인결과 -->
		<article id="layerPopCertificationCheck04" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2>본인인증 확인결과</h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt">본인 인증정보가 일치하지 않아 <br> 휴대전화를 변경하실 수 없습니다.</p>			
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>		
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="bots-info">
					<p><strong>080-807-0012</strong> 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>

		<!--  본인인증 확인결과 -->
		<article id="layerPopCertificationCheck05" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2>본인인증 확인결과</h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt">휴대폰 가입자 정보와 회원정보 정보가 일치하지 않아 수정이 불가합니다.</p>			
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>		
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="bots-info">
					<p><strong>080-807-0012</strong> 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		<script>
		function moveFindPwd(){
			var param = null;
			
			param = {
				'${_csrf.parameterName}' : '${_csrf.token}'
		        , 'type' : 'PASSWORD'
		    };
			
			jsLinkUrlPost('/member/login/viewFind', param);
		}		
		</script>
