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
			
			<main class="contents oto_inquiryView-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">1:1 문의</strong>
					</p>
				</div> 
				
				<div class="board-list">
					<table>
						<caption>1:1 문의 이메일주소, 핸드폰번호, 제목, 작성일</caption>
						<colgroup>
							<col style="width:100px;">
							<col>
							<col style="width:140px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="3">
									<div class="clearfix">
										<div class="fl">
											<p>
												<em>주문번호 :</em>
												<span>GM0018080600617</span>
											</p>
											<p>
												<em>이메일주소 :</em>
												<span>abc12345@naver.com</span>
											</p>
											<p>
												<em>핸드폰번호 :</em>
												<span>010-123-4567</span>
											</p>
										</div>
										<div class="fr">
											<a href="javascript:;" class="btn sm">수정</a>
											<a href="javascript:;" class="btn sm fill">삭제</a>
										</div>
									</div>
								</th>
							</tr>
							<tr>
								<th scope="col">[취소문의]</th>
								<th scope="col">주문 취소하였으나 신용카드 취소가 안됨</th>
								<th scope="col">작성일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td></td>
								<td>
									<!-- 20190114 추가변경 -->
									<p>너무 옷이 작아요 . 인터넷에서 봤을때 큰줄 알았는데 배송되서 실제로 입어보니 너무작아요..반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게반품 강력하게 반품 강력하게 반품 강력하게</p>
									<div class="bd-listImg"><img src="/static/images/_temp/goods_thumb_3.png" alt="" /></div>
									<span class="bd-listTxT">첨부파일 : <a href="#none">상품오배송사진.jpg</a></span>
									<!-- //20190114 추가변경 -->
								</td>
								<td>2018-10-30 14:00</td>
							</tr>
							<!-- <tr>
								<td></td>
								<td>고객님의 문의 내용을 확인 중에 있습니다. 처리 후 최종 답변을 드리겠습니다.</td>
								<td>2018-10-30 14:00</td>
							</tr> -->
						</tbody>
					</table>
				</div>
				
				<div class="btnWrapBox">
					<a href="/static/html/my/oto_inquiryList.jsp" class="btn">목록</a>
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