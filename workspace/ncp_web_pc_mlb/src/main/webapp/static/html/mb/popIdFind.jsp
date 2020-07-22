<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<!-- 아이디 찾기 -->
<article id="popIdFind" class="layer-popup layer-type02 popIdFind">
	<section class="layer-popup-cont" tabindex="0">
		<h2>아이디 찾기</h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt"><strong>홍길동</strong> 고객님께서 등록하신 아이디는 <strong>abcde**</strong> 입니다.</p>
			<dl class="join-date-info">
				<dt>가입일 : </dt>
				<dd>2017.08.24</dd>
			</dl>
			<div class="btns"><a href="javascript:;" class="btn lg fill btn-login">로그인</a></div>

			<p class="layer-txt04">비밀번호를 잊으셨나요?  <a href="javascript:;">비밀번호 찾기</a></p>

			<!-- 2018-12-19 -->
			<ul class="links">
				<li><a href="javascript:;">FAQ바로가기</a></li>
				<li><a href="javascript:;">카카오톡 상담하기</a></li>
			</ul>
			<!-- 2018-12-19 -->
			
		</div>
		<div class="layer-footer-info">
			<p><strong>080-807-0012</strong> 평일 오전 9시 ~ 오후 6시 (토/일/공휴일 휴무)</p>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popIdFind'); 
});
</script>

</body>
</html>