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
		
			<h2 class="title01">마일리지</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents mileageList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>혜택정보</span><strong title="현재 위치">마일리지</strong>
					</p>
				</div> 
				
				<!-- 마일리지 현황S -->
				<div class="memberSTcont boxCont03">
					<ul>
						<li>
							<p>사용 가능 마일리지</p>
							<strong><span class="text-color01">25,000</span>원</strong>
						</li>
						<li>
							<p>적립 예정 마일리지</p>
							<strong><span class="text-color01">5,000</span>원</strong>
						</li>
						<li>
							<p>당월 소멸 예정 마일리지</p>
							<strong><span class="text-color01">100</span>원</strong>
						</li>
					</ul>
				</div>
				<!-- //마일리지 현황E -->
				
				<%@ include file="../_inc/uiDateRange2.jsp" %>
				
				<!-- table info S -->
				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
					<div class="mid fr">
						<a href="/static/html/my/mileage_pop.jsp" class="btn fill sm"><span>멤버십 마일리지 카드 등록</span></a>
					</div>
				</div>
				<!-- //table info E -->
				
				<!-- table S -->
				<div class="board-list">
					<table>
						<caption>일자, 내용, 적립내역, 사용내역</caption>
						<colgroup>
							<col style="width:100px;">
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">일자</th>
								<th scope="col">내용</th>
								<th scope="col">적립내역</th>
								<th scope="col">사용내역</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" class="no-result">조회된 마일리지 내역이 없습니다.</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">1,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)상품구매 시 적립(OD201811010031620)상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">1,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">3,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">4,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">8,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">0</span>
								</td>
								<td>-3,000</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">3,000</span>
								</td>
								<td>-1,000</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">3,000</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">100</span>
								</td>
								<td>0</td>
							</tr>
							<tr>
								<td>2018.11.01</td>
								<td class="tleft">상품구매 시 적립(OD201811010031620)</td>
								<td>
									<span class="text-color01">2,000</span>
								</td>
								<td>0</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //table E -->
				
				<%@ include file="../_inc/paging.jsp" %>
				
				<!-- 마일리지 적립 사용안내 영역S -->
				<div class="contTxtBox">
					<strong>마일리지 적립 / 사용</strong>
					<ul class="text-list01">
						<li>마일리지 적립 기준은 <a href="/static/html/my/ratingBenefit.jsp" class="text-color01">회원혜택안내</a>에서 확인하세요.</li>
						<li>주문 결제 시 5,000원 이상 1,000원 단위로 사용 가능 합니다.</li>
						<li>현금으로 환원되지 않는 비현금성 서비스 입니다.</li>
						<li>주문 시 구매한 상품 가격에 비례하여 분할 적용되며 부분 취소/반품 시 분할 환원 됩니다.</li>
					</ul>
					<strong>마일리지 유효기간</strong>
					<ul class="text-list01">
						<li>적립일로부터 2년으로 사용하지 않은 마일리지는 자동으로 소멸됩니다.</li>
					</ul>
				</div>
				<!-- //마일리지 적립 사용안내 영역E -->

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