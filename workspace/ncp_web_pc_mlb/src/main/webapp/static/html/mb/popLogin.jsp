<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>



<!-- 로그인 팝업  -->
<article id="popLogin" class="layer-popup popLogin">


	<!-- if 주문 로그인  -->
	<section class="layer-popup-cont" tabindex="0">
		<h2>로그인</h2>
		<div class="layer-popup-wrap">
			<div class="msg">
				<p>로그인후 구매하시면<br>쿠폰/쿠폰/포인트/마일리지 등의 혜택을 받을 수 있습니다.</p>
			</div>
			<div class="inputs">
				<input type="text" class="input-style01" title="아이디 입력창" placeholder="아이디">
				<input type="password" class="input-style01" title="비밀번호 입력창" placeholder="비밀번호">
			</div>
			<p class="login-check-id">
				<span class="check-skin">
					<input type="checkbox" id="chkReply" checked="checked">
					<span>선택</span>
				</span>
				<label for="chkReply">아이디 저장</label>
			</p>
			<p class="error-msg" style="display:block;">아이디 / 비밀번호가 일치하지 않습니다.</p>
			
			<div class="btns bt1">
				<a href="javascript:;" class="btn-login btn-style02">로그인</a>
				<a href="javascript:;" class="btn-np btn-npay02"><span>네이버로 로그인</span></a>
			</div>

		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>


	<!-- if 고객센터 로그인  -->
	<section class="layer-popup-cont" tabindex="0">
		<h2>로그인</h2>
		<div class="layer-popup-wrap">
			<div class="msg">
				<p>로그인 하시면, 문의하신 내용에 대한 답변을 <br> 마이페이지에서 확인하실 수 있습니다.</p>
			</div>
			<div class="inputs">
				<input type="text" class="input-style01" title="아이디 입력창" placeholder="아이디">
				<input type="password" class="input-style01" title="비밀번호 입력창" placeholder="비밀번호">
			</div>
			<p class="login-check-id">
				<span class="check-skin">
					<input type="checkbox" id="chkReply" checked="checked">
					<span>선택</span>
				</span>
				<label for="chkReply">아이디 저장</label>
			</p>
			<p class="error-msg" style="display:block;">아이디 / 비밀번호가 일치하지 않습니다.</p>
			
			<div class="btns bt1">
				<a href="javascript:;" class="btn-login btn-style02">로그인</a>
				<a href="javascript:;" class="btn-np btn-npay02"><span>네이버로 로그인</span></a>
			</div>

			<div class="login-btn-lnk">
				<a href="/static/html/mb/findIdPw.jsp">아이디 찾기</a>
				<a href="/static/html/mb/findIdPw.jsp">비밀번호 찾기</a>
				<a href="javascript:;">회원가입</a>
			</div>

			<div class="btns bt3">
				<a href="javascript:;" class="btn lg gray">비회원 문의하기</a>
			</div>

			<ul class="text-list02 bulList">
				<li>비회원으로 문의하신 내용은 이메일로 답변 드립니다.</li>
			</ul>

		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>




	<!-- if 일반로그인  -->
	<section class="layer-popup-cont" tabindex="0">
		<h2>로그인</h2>
		<div class="layer-popup-wrap">
			<div class="msg">
				<p>로그인 후 이용하실 수 있습니다.</p> 
			</div>
			<div class="inputs">
				<input type="text" class="input-style01" title="아이디 입력창" placeholder="아이디">
				<input type="password" class="input-style01" title="비밀번호 입력창" placeholder="비밀번호">
			</div>
			<p class="login-check-id">
				<span class="check-skin">
					<input type="checkbox" id="chkReply" checked="checked">
					<span>선택</span>
				</span>
				<label for="chkReply">아이디 저장</label>
			</p>
			<p class="error-msg" style="display:block;">아이디 / 비밀번호가 일치하지 않습니다.</p>
			
			<div class="btns bt1">
				<a href="javascript:;" class="btn-login btn-style02">로그인</a>
				<a href="javascript:;" class="btn-np btn-npay02"><span>네이버로 로그인</span></a>
			</div>

			<div class="login-btn-lnk">
				<a href="/static/html/mb/findIdPw.jsp">아이디 찾기</a>
				<a href="/static/html/mb/findIdPw.jsp">비밀번호 찾기</a>
				<a href="javascript:;">회원가입</a>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>




</article>


<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popLogin'); 
});
</script>

</body>
</html>