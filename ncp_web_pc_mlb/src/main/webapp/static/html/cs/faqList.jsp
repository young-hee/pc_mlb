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
		
			<h2 class="title01">자주 묻는 질문</h2>
			
			<main class="contents faqList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>고객센터</span><strong title="현재 위치">자주 묻는 질문</strong>
					</p>
				</div> 
				
				<!-- tab S -->
				<ul class="tab-type02 d_tab">
					<li class="on"><a href="javascript:;">전체</a></li>
					<li><a href="javascript:;">주문/입금결제</a></li>
					<li><a href="javascript:;">상품문의</a></li>
					<li><a href="javascript:;">취소/반품/환불/교환</a></li>
					<li><a href="javascript:;">이벤트/세일</a></li>
					<li><a href="javascript:;">멤버쉽</a></li>
					<li><a href="javascript:;">배송</a></li>
					<li><a href="javascript:;">회원정보</a></li>
					<li><a href="javascript:;">마일리지/포인트/쿠폰문의</a></li>
					<li><a href="javascript:;">매장/기타</a></li>
				</ul>
				<!--//tab E -->
				
				<!-- 검색 S -->
				<div class="search-wrap01">
					<div class="search-input">
						<input type="search" title="검색" placeholder="궁금하신 내용을 입력해 주세요."/><button type="button">검색</button>
					</div>
				</div>
				<!--//검색 E -->
				
				<!-- table S -->
				<div class="board-list accordion-type d_accordion">
					<div class="boardCount">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
					<table>
						<caption>자주 묻는 질문 Top 10 번호, 분류, 제목 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:216px;">
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
								<td colspan="3" class="no-result">검색결과가 없습니다. <a href="javascript:;">1:1문의</a>를 이용해 주세요.</td>
							</tr>
							<tr>
								<td>1</td>
								<td class="tleft">주문/입금결제</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">주문한 상품이 품절일 경우 어떻게 되나요?</p>
									<div class="reply">주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?주문한 상품이 품절일 경우 어떻게 되나요?</div>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td class="tleft">상품문의</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">제품의 수선을 맡기고 싶은데 방법을 알려 주세요.</p>
									<div class="reply">제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.제품의 수선을 맡기고 싶은데 방법을 알려 주세요.</div>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td class="tleft">취소/반품/환불/교환</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">반품이 가능한 경우는 어떤 경우인가요?</p>
									<div class="reply">반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?반품이 가능한 경우는 어떤 경우인가요?</div>
								</td>
							</tr>
							<tr>
								<td>4</td>
								<td class="tleft">이벤트/세일</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.</p>
									<div class="reply">이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.이벤트 당첨발표 시 당첨자 확인 방법 알려주세요.</div>
								</td>
							</tr>
							<tr>
								<td>5</td>
								<td class="tleft">멤버쉽</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">멤버십 등급 기준을 알고싶어요.</p>
									<div class="reply">멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.멤버십 등급 기준을 알고싶어요.</div>
								</td>
							</tr>
							<tr>
								<td>6</td>
								<td class="tleft">배송</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">[매장픽업] 일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.</p>
									<div class="reply">일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.일반배송 상품과 매장픽업 주문 상품을 같이 주문할 수 있나요.</div>
								</td>
							</tr>
							<tr>
								<td>7</td>
								<td class="tleft">매장/기타</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">CS Center 상담시간은 어떻게 되나요?</p>
									<div class="reply">CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?CS Center 상담시간은 어떻게 되나요?</div>
								</td>
							</tr>
							<tr>
								<td>8</td>
								<td class="tleft">회원정보</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">회원가입을 해야 구매할 수 있나요?</p>
									<div class="reply">회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?회원가입을 해야 구매할 수 있나요?</div>
								</td>
							</tr>
							<tr>
								<td>9</td>
								<td class="tleft">마일리지/포인트/쿠폰문의</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">마일리지의 소멸 기준이 어떻게 되나요?</p>
									<div class="reply">마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?마일리지의 소멸 기준이 어떻게 되나요?</div>
								</td>
							</tr>
							<tr>
								<td>10</td>
								<td class="tleft">주문/입금결제</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">전화로 주문이 가능한가요?</p>
									<div class="reply">전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?전화로 주문이 가능한가요?</div>
								</td>
							</tr>
						</tbody>
					</table>
					
					<%@ include file="../_inc/paging.jsp" %>
				</div>
				<!--//table E -->
				
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