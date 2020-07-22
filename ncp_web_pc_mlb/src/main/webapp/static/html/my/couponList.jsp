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

			<h2 class="title01">쿠폰함</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents couponList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>혜택정보</span><strong title="현재 위치">쿠폰함</strong>
					</p>
				</div> 
				
				<div class="d_tab02">
				
					<!-- tab S -->
					<ul class="tab-type01 tab-blockList blockList02">
						<li class="d_tab02_select on"><a href="javascript:;">사용 가능한 쿠폰</a></li>
						<li class="d_tab02_select"><a href="javascript:;">사용완료 / 기간만료</a></li>
					</ul>
					<!--// tab E -->
					
					<!-- 사용 가능한 쿠폰 S -->
					<div class="d_tab02_cont" style="display:block;">
					
						<!-- table info S -->
						<div class="tbst-div">
							<div class="mid fl">
								<span>전체</span> (<span class="text-color01">100</span>건)
							</div>
							<div class="mid fr">
								<a href="/static/html/my/couponRegister_pop.jsp" class="btn fill sm"><span>쿠폰등록</span></a>
							</div>
						</div>
						<!-- //table info E -->

						<!-- table S -->
						<div class="board-list">
							<table>
								<caption>쿠폰종류, 적용조건, 쿠폰명, 할인, 사용기간, 쿠폰적용상품</caption>
								<colgroup>
									<col style="width:100px;">
									<col style="width:100px;">
									<col>
									<col style="width:100px;">
									<col style="width:190px;">
									<col style="width:147px;">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">쿠폰종류</th>
										<th scope="col">적용조건</th>
										<th scope="col">쿠폰명</th>
										<th scope="col">할인</th>
										<th scope="col">사용기간</th>
										<th scope="col">쿠폰적용상품</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="6" class="no-result">조회된 쿠폰 내역이 없습니다.</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>구매금액별 2만원 할인쿠폰</p>
											<span class="text-color01">최소구매금액 : 200,000원</span>
										</td>
										<td>
											<span class="text-color01">20,000원</span>
										</td>
										<td>~ 2018.1.-22 (50일 남음)</td>
										<td></td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>10% 할인쿠폰</p>
											<span class="text-color01">최대할인금액 : 20,000원</span>
										</td>
										<td>
											<span class="text-color01">10%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="/static/html/my/coupongoods_pop.jsp" class="btn sm gray">적용상품조회</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>15% 할인쿠폰</p>
											<span class="text-color01">최소구매금액 : 20,000원</span>
											<span class="text-color01">최대할인금액 : 30,000원</span>
										</td>
										<td>
											<span class="text-color01">15%</span>
										</td>
										<td>2018.12.01부터 사용가능</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="/static/html/my/coupongoods_pop.jsp" class="btn sm gray">적용상품조회</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>일반배송비</td>
										<td class="tleft">
											<p>50,000원 이상 1,000원 할인 쿠폰</p>
											<span class="text-color01">최소구매금액 : 50,000원</span>
										</td>
										<td>
											<span class="text-color01">1,000원</span>
										</td>
										<td>~ 2018.11.12 (10일 남음)</td>
										<td></td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td></td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="/static/html/my/coupongoods_pop.jsp" class="btn sm gray">적용상품조회</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="/static/html/my/coupongoods_pop.jsp" class="btn sm gray">적용상품조회</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td></td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td></td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>~ 2019.01.08 (67일 남음)</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- //table E -->
						
						<%@ include file="../_inc/paging.jsp" %>
						
					</div>
					<!--// 사용 가능한 쿠폰 E -->
					
					<!-- 사용완료 / 기간만료 S -->
					<div class="d_tab02_cont">
					
						<%@ include file="../_inc/uiDateRange2.jsp" %>
						
						<!-- table info S -->
						<div class="tbst-div">
							<div class="mid fl">
								<span>전체</span> (<span class="text-color01">100</span>건)
							</div>
						</div>
						<!-- //table info E -->
						
						<!-- table S -->
						<div class="board-list">
							<table>
								<caption>쿠폰종류, 적용조건, 쿠폰명, 할인, 사용일자, 주문정보</caption>
								<colgroup>
									<col style="width:100px;">
									<col style="width:100px;">
									<col>
									<col style="width:100px;">
									<col style="width:100px;">
									<col style="width:170px;">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">쿠폰종류</th>
										<th scope="col">적용조건</th>
										<th scope="col">쿠폰명</th>
										<th scope="col">할인</th>
										<th scope="col">사용일자</th>
										<th scope="col">주문정보</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="6" class="no-result">조회된 쿠폰 내역이 없습니다.</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>구매금액별 2만원 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">20,000원</span>
										</td>
										<td>2018.11.03</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>10% 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">10%</span>
										</td>
										<td>2018.09.30</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>15% 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">15%</span>
										</td>
										<td>2018.08.15</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray disabled">사용기간만료</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>일반배송비</td>
										<td class="tleft">
											<p>50,000원 이상 1,000원 할인 쿠폰</p>
										</td>
										<td>
											<span class="text-color01">1,000원</span>
										</td>
										<td>2018.09.01</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>2018.08.15</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>구매금액별 2만원 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">20,000원</span>
										</td>
										<td>2018.11.03</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>10% 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">10%</span>
										</td>
										<td>2018.09.30</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>상품</td>
										<td class="tleft">
											<p>15% 할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">15%</span>
										</td>
										<td>2018.08.15</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray disabled">사용기간만료</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온라인</td>
										<td>일반배송비</td>
										<td class="tleft">
											<p>50,000원 이상 1,000원 할인 쿠폰</p>
										</td>
										<td>
											<span class="text-color01">1,000원</span>
										</td>
										<td>2018.09.01</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
									<tr>
										<td>온오프라인</td>
										<td>장바구니</td>
										<td class="tleft">
											<p>단체할인쿠폰</p>
										</td>
										<td>
											<span class="text-color01">30%</span>
										</td>
										<td>2018.08.15</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="javascript:;" class="btn sm gray">주문사용내역보기</a>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- //table E -->
						
						<%@ include file="../_inc/paging.jsp" %>
						
					</div>
					<!--// 사용완료 / 기간만료 E --> 
					
				</div>
				
				<!-- 쿠폰 발급/사용안내 영역S -->
				<div class="contTxtBox">
					<strong>쿠폰 발급/ 사용</strong>
					<ul class="text-list01">
						<li>일부 상품은 쿠폰 적용에서 제외될수 있습니다.</li>
						<li>회원 발급 쿠폰의 내용은 <a href="/static/html/my/ratingBenefit.jsp" class="text-color01">회원혜택안내</a>에서 확인하세요.</li>
						<li>중복불가 쿠폰인 경우 다른 쿠폰과 함께 적용할 수 없습니다.</li>
						<li>장바구니 쿠폰을 적용한 경우 구매한 상품들의 가격에 비례하여 분할 적용되며, 부분취소/반품 시 분할 할인된 금액을 차감하여 환불됩니다.</li>
						<li>오프라인 발행한 쿠폰은 쿠폰번호 등록 후 사용해 주세요.</li>
					</ul>
					<strong>쿠폰 소멸</strong>
					<ul class="text-list01">
						<li>발급 시 부여되는 쿠폰의 사용기간을 따르며, 사용기간이 경과한 쿠폰은 자동으로 소멸됩니다.</li>
					</ul>
				</div>
				<!-- //쿠폰 발급/사용안내 영역E -->

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