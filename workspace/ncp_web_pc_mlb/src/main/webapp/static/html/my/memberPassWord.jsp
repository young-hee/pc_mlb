<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01">비밀번호 확인</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents memberPassWord-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>회원정보</span><strong title="현재 위치">비밀번호 확인</strong>
					</p>
				</div> 
				
				<div class="board-write">
					<p class="txt13-666">회원님의 개인정보보호를 위한 본인 확인절차를 위해<br/>비밀번호를 입력해 주세요.</p>
					<input type="password" class="input-style01" title="비밀번호" placeholder="비밀번호" style="width:400px;">
					<span class="error-msg" style="display:block;">“비밀번호”를 입력해 주세요.</span>
				</div>
				
				<div class="btnWrapBox">
					<a href="javascript:;" class="btn">취소</a>
					<a href="javascript:;" class="btn fill">확인</a>
				</div>
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
$(document).ready(function(){
	// 
});
</script>
</body>
</html>