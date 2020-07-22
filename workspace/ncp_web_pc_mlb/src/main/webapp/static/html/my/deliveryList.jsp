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
		
			<h2 class="title01">배송지관리</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents deliveryList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>회원정보</span><strong title="현재 위치">배송지관리</strong>
					</p>
				</div> 
				
				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
				</div>
				
				<div class="board-list">
					<table>
						<caption>배송지관리, 받는분, 배송지정보</caption>
						<colgroup>
							<col style="width:40px;">
							<col style="width:140px;">
							<col>
							<col style="width:145px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">받는분</th>
								<th scope="col">배송지정보</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" class="no-result">등록된 배송지가 없습니다.</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" checked="checked" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>
									<p>[기본배송지]</p>
									<p>홍길동</p>
								</td>
								<td class="tleft">
									<p>강남구 언주로 541 에프앤에프빌딩 5층</p>
									<span class="txt13-666">010-5656-8887</span>
								</td>
								<td>
									<div class="tbST-R-BtnBox">
										<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn sm gray">수정</a>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>
									<p>홍길동</p>
								</td>
								<td class="tleft">
									<p>강남구 언주로 541 에프앤에프빌딩 5층</p>
									<span class="txt13-666">010-5656-8887</span>
								</td>
								<td>
									<div class="tbST-R-BtnBox">
										<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn sm gray">수정</a>
										<a href="javascript:;" class="btn sm gray">삭제</a>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>
									<p>홍길동</p>
								</td>
								<td class="tleft">
									<p>강남구 언주로 541 에프앤에프빌딩 5층</p>
									<span class="txt13-666">010-5656-8887</span>
								</td>
								<td>
									<div class="tbST-R-BtnBox">
										<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn sm gray">수정</a>
										<a href="javascript:;" class="btn sm gray">삭제</a>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>
									<p>홍길동</p>
								</td>
								<td class="tleft">
									<p>강남구 언주로 541 에프앤에프빌딩 5층</p>
									<span class="txt13-666">010-5656-8887</span>
								</td>
								<td>
									<div class="tbST-R-BtnBox">
										<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn sm gray">수정</a>
										<a href="javascript:;" class="btn sm gray">삭제</a>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>
									<p>홍길동</p>
								</td>
								<td class="tleft">
									<p>강남구 언주로 541 에프앤에프빌딩 5층</p>
									<span class="txt13-666">010-5656-8887</span>
								</td>
								<td>
									<div class="tbST-R-BtnBox">
										<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn sm gray">수정</a>
										<a href="javascript:;" class="btn sm gray">삭제</a>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<%@ include file="../_inc/paging.jsp" %>
				
				<div class="btnWrapBox">
					<a href="javascript:;" class="btn">기본 배송지 설정</a>
					<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn fill">배송지 추가</a>
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