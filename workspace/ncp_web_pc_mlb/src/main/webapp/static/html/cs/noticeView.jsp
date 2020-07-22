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
	<div class="contain cs" id="contain">
		<div class="container">
		
			<h2 class="title01">공지사항</h2>
			
			<main class="contents noticeView-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>고객센터</span><strong title="현재 위치">공지사항</strong>
					</p>
				</div> 
				
				<!-- table S -->
				<div class="board-view">
					<div class="board-header">
						<strong>[이벤트 당첨] MLB 공식몰 리뉴얼 오픈 기념 이벤트 당첨자 발표</strong>
						<p><b>2018.11.05</b>(조회수 <em>12,345</em>)</p>
					</div>
					<div class="board-cnt">
						안녕하세요. MLB입니다.<br/><br/>
						MLB 공식몰 리뉴얼 오픈 기념 이벤트에 참여해주신 모든 분들께 감사드리며, 스타벅스 아메리카노와 베스킨라빈스 싱글킹 기프티콘을 받으실 2차 당첨자 공지 드립니다.<br/>
						축하합니다!<br/><br/>
						해당 기프티콘은 8월 23일 오후, 일괄 발송 드릴 예정입니다.<br/>
						회원정보의 핸드폰 번호로 발송되며 오기재로 인한 재발송은 불가하오니, 이 점 미리 확인 부탁드립니다.<br/><br/><br/>
						[스타벅스 아메리카노]<br/>
						<8월 9일 당첨자><br/>
						zoung1***<br/>
						jisoo0***<br/>
						mau***<br/>
						km0***<br/>
						dbwls6***<br/>
						ahn1***<br/>
						olllhy***<br/>
						emily0***<br/>
						zmdk0***
					</div>
				</div>
				<!--//table E -->
				
				<div class="btn-wrap">
					<a href="javascript:;" class="btn lg fill">목록</a>
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