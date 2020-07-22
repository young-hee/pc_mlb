<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup coupongoods-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>쿠폰 적용 가능 상품</h2>
		<div class="layer-cont scroll">
			
			<div class="coupongoods-popWrap">
			
				<!-- 검색S -->
				<div class="couponFindSrch">
					<!-- select -->
					<div class="select-style02 d_select">
						<button type="button" class="d_select_sel"><span>2018년 다운 컬렉션 30% 특별세일 쿠폰</span></button>
						<ul>
							<li><a href="javascript:;">2018년 다운 컬렉션 30% 특별세일 쿠폰01</a></li>
							<li><a href="javascript:;">2018년 다운 컬렉션 30% 특별세일 쿠폰02</a></li>
							<li><a href="javascript:;">2018년 다운 컬렉션 30% 특별세일 쿠폰03</a></li>
						</ul>
					</div>
					<!-- //select -->
					<div>
						<!-- select -->
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel"><span>상품명</span></button>
							<ul>
								<li><a href="javascript:;">상품명01</a></li>
								<li><a href="javascript:;">상품명02</a></li>
								<li><a href="javascript:;">상품명03</a></li>
							</ul>
						</div>
						<!-- //select -->
						<input type="text" placeholder="" class="input-style02">
						<a href="javascript:;" class="btn sm">검색</a>
					</div>
					
				</div>
				<!-- //검색E -->
				
				<div class="board-list">
					<table>
						<caption>쿠폰 적용 가능 상품 리스트, 상품명, 상품금액, 쿠폰적용가격</caption>
						<colgroup>
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">상품명</th>
								<th scope="col">상품금액</th>
								<th scope="col">쿠폰적용가격</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="3" class="no-result">전체 적용 쿠폰입니다.</td>
							</tr>
							<tr>
								<td>
									<div class="product-info">
										<div class="product-info-img">
											<a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
											</div>
										</div>
									</div>
								</td>
								<td>999,999,000원</td>
								<td><b>9,999,000</b>원</td>
							</tr>
							<tr>
								<td>
									<div class="product-info">
										<div class="product-info-img">
											<a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
											</div>
										</div>
									</div>
								</td>
								<td>999,999,000원</td>
								<td><b>9,999,000</b>원</td>
							</tr>
							<tr>
								<td>
									<div class="product-info">
										<div class="product-info-img">
											<a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
											</div>
										</div>
									</div>
								</td>
								<td>999,999,000원</td>
								<td><b>9,999,000</b>원</td>
							</tr>
							<tr>
								<td>
									<div class="product-info">
										<div class="product-info-img">
											<a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
											</div>
										</div>
									</div>
								</td>
								<td>999,999,000원</td>
								<td><b>9,999,000</b>원</td>
							</tr>
							<tr>
								<td>
									<div class="product-info">
										<div class="product-info-img">
											<a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a>
										</div>
										<div class="product-info-text">
											<div class="product-info-box">
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
											</div>
										</div>
									</div>
								</td>
								<td>999,999,000원</td>
								<td><b>9,999,000</b>원</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<%@ include file="../_inc/paging.jsp" %>
				
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.coupongoods-pop'); 
});
</script>
</body>
</html>