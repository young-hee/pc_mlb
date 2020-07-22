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
		
			<h2 class="title01">고객센터</h2>
			
			<main class="contents csMain-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><strong title="현재 위치">고객센터</strong>
					</p>
				</div> 
				
				<!-- 상단탭 S -->
				<div class="csMainTab">
					<ul>
						<li><a href="javascript:;">공지사항</a></li>
						<li><a href="javascript:;">1:1 문의</a></li>
						<li><a href="javascript:;">회원혜택안내</a></li>
						<li><a href="javascript:;">반품/교환 안내</a></li>
					</ul>
				</div>
				<!--//상단탭 E -->
				
				<!-- SEARCH S -->
				<div class="search-wrap02">
					<label for="searchType">FAQ SEARCH</label>
					<div class="search-input02">
						<input type="search" id="searchType" placeholder="궁금하신 내용을 입력해 주세요."><button type="button">검색</button>
					</div>
					<div>
						<ul class="word-list">
							<li><a href="javascript:;">교환</a></li>
							<li><a href="javascript:;">반품</a></li>
							<li><a href="javascript:;">배송</a></li>
							<li><a href="javascript:;">취소</a></li>
							<li><a href="javascript:;">환불</a></li>
							<li><a href="javascript:;">비밀번호</a></li>
						</ul>
					</div>
				</div>
				<!--//SEARCH E -->
				
				<!-- 자주 묻는 질문 S -->
				<div class="justify-wrap">
					<h3 class="title01 left">자주 묻는 질문</h3>
					<a href="javascript:;" class="btn-style07 right"><span>전체보기</span></a>
				</div>
				<div class="board-list accordion-type d_accordion">
					<table>
						<caption>자주 묻는 질문 Top 10 번호, 분류, 제목 정보표.</caption>
						<colgroup>
							<col style="width:95px;">
							<col style="width:230px;">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">분류</th>
								<th scope="col">제목</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td class="tleft">주문/입금결제</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">주문한 상품이 품절일 경우 어떻게 되나요?</p>
									<div class="reply">
										주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?
									</div>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td class="tleft">상품문의</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">제품의 수선을 맡기고 싶은데 방법을 알려 주세요.</p>
									<div class="reply">
										제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.
									</div>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td class="tleft">취소/반품/환불/교환</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">반품이 가능한 경우는 어떤 경우인가요?</p>
									<div class="reply">
										반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?
									</div>
								</td>
							</tr>
							<tr>
								<td>4</td>
								<td class="tleft">이벤트/세일</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.</p>
									<div class="reply">
										이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.
									</div>
								</td>
							</tr>
							<tr>
								<td>5</td>
								<td class="tleft">멤버쉽</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">멤버십 등급 기준을 알고싶어요.</p>
									<div class="reply">
										멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.
									</div>
								</td>
							</tr>
							<tr>
								<td>6</td>
								<td class="tleft">배송</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">[매장픽업] 일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.</p>
									<div class="reply">
										[매장픽업] 일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.
									</div>
								</td>
							</tr>
							<tr>
								<td>7</td>
								<td class="tleft">매장/기타</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">CS Center 상담시간은 어떻게 되나요?</p>
									<div class="reply">
										CS Center 상담시간은 어떻게 되나요?
									</div>
								</td>
							</tr>
							<tr>
								<td>8</td>
								<td class="tleft">회원정보</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">회원가입을 해야 구매할 수 있나요?</p>
									<div class="reply">
										회원가입을 해야 구매할 수 있나요?
									</div>
								</td>
							</tr>
							<tr>
								<td>9</td>
								<td class="tleft">마일리지/포인트/쿠폰문의</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">마일리지의 소멸 기준이 어떻게 되나요?</p>
									<div class="reply">
										마일리지의 소멸 기준이 어떻게 되나요?
									</div>
								</td>
							</tr>
							<tr>
								<td>10</td>
								<td class="tleft">주문/입금결제</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">전화로 주문이 가능한가요?</p>
									<div class="reply">
										전화로 주문이 가능한가요?
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--//자주 묻는 질문 E -->
				
				<!-- 공지사항 S -->
				<div class="justify-wrap">
					<h3 class="title01 left">공지사항</h3>
					<a href="javascript:;" class="btn-style07 right"><span>전체보기</span></a>
				</div>
				<div class="board-list">
					<table>
						<caption>공지사항 번호, 구분, 제목, 조회수, 등록일 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:120px;">
							<col>
							<col style="width:120px;">
							<col style="width:120px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">구분</th>
								<th scope="col">제목</th>
								<th scope="col">조회수</th>
								<th scope="col">등록일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>5</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">MLB 공식몰 리뉴얼 오픈 기념 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018.11.05</td>
							</tr>
							<tr>
								<td>4</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">7월 플러스친구 이벤트 2차 당첨자</a></td>
								<td>12,345</td>
								<td>2018.11.05</td>
							</tr>
							<tr>
								<td>3</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">MLB KIDS 홈페이지 통합</a></td>
								<td>12,345</td>
								<td>2018.11.05</td>
							</tr>
							<tr>
								<td>2</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">MLB KIDS 공식몰 리뉴얼 오픈 기념 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018.11.05</td>
							</tr>
							<tr>
								<td>1</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">해시태그 이벤트 당첨자 안내</a></td>
								<td>12,345</td>
								<td>2018.11.05</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--//공지사항 E -->
				
				<!-- CONTACT US S -->
				<div class="justify-wrap">
					<h3 class="title01 left">CONTACT US</h3>
				</div>
				<div class="csMainTXTBox">
					<ul>
						<li>
							<a href="javascript:;">
								<strong>전화문의</strong>
								<p>080-807-0012</p>
							</a>
						</li>
						<li>
							<a href="javascript:;">
								<strong>카카오톡</strong>
								<p>상담하기</p>
							</a>
						</li>
						<li>
							<a href="javascript:;">
								<strong>단체구매</strong>
								<p>문의</p>
							</a>
						</li>
					</ul>
				</div>
				<!--//CONTACT US E -->

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