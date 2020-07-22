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
		
			<h2 class="title01">1:1 문의</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents oto_inquiryList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">1:1 문의</strong>
					</p>
				</div> 
				
				<p class="txt13-666">고객상담에 대한 내역과 답변을 확인하실 수 있습니다.<br/><em>고객센터 080-807-0012</em> (평일 AM 9시~ PM 6시 : 토/일 공휴일 휴무)</p>
				
				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
					<div class="mid fr">
						<a href="/static/html/my/oto_inquiryWrite.jsp" class="btn fill sm fdlr30"><span>1:1 문의 등록</span></a>
					</div>
				</div>
				
				<div class="board-list">
					<table>
						<caption>1:1 문의 번호, 문의유형, 제목, 작성일, 답변여부</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:100px;">
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">문의유형</th>
								<th scope="col">제목</th>
								<th scope="col">작성일</th>
								<th scope="col">답변여부</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="5" class="no-result">상담내역이 없습니다.</td>
							</tr>
							<tr>
								<td>10</td>
								<td>취소문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요..반품 강력하게 반품 강력하게 반품 강력하게</a></td>
								<td>2018-10-30</td>
								<td>답변대기</td>
							</tr>
							<tr>
								<td>9</td>
								<td>A/S문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 .</a></td>
								<td>2018-10-30</td>
								<td>답변대기</td>
							</tr>
							<tr>
								<td>8</td>
								<td>반품문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요</a></td>
								<td>2018-10-30</td>
								<td>답변 전</td>
							</tr>
							<tr>
								<td>7</td>
								<td>반품문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 .</a></td>
								<td>2018-10-30</td>
								<td>답변대기</td>
							</tr>
							<tr>
								<td>6</td>
								<td>반품문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요..</a></td>
								<td>2018-10-30</td>
								<td>답변 전</td>
							</tr>
							<tr>
								<td>5</td>
								<td>취소문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요.</a></td>
								<td>2018-10-30</td>
								<td>답변완료</td>
							</tr>
							<tr>
								<td>4</td>
								<td>A/S문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데...</a></td>
								<td>2018-10-30</td>
								<td>답변완료</td>
							</tr>
							<tr>
								<td>3</td>
								<td>A/S문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데...</a></td>
								<td>2018-10-30</td>
								<td>답변대기</td>
							</tr>
							<tr>
								<td>2</td>
								<td>취소문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데...</a></td>
								<td>2018-10-30</td>
								<td>답변완료</td>
							</tr>
							<tr>
								<td>1</td>
								<td>취소문의</td>
								<td class="tleft"><a href="/static/html/my/oto_inquiryView.jsp">너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요..</a></td>
								<td>2018-10-30</td>
								<td>답변대기</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<%@ include file="../_inc/paging.jsp" %>

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