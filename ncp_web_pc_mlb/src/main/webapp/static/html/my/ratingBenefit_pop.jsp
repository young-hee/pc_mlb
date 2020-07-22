<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup ratingBenefit-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>구매등급 혜택보기</h2>
		<div class="layer-cont scroll">
			
			<div class="ratingBenefit-popWrap">
			
				
				<!-- MLB 등급 혜택 안내 S -->
				<div class="ratingBenefit-list ratingBenefitLine">
					<table>
						<caption>회원등급혜택</caption>
						<colgroup>
							<col style="width:130px;">
							<col style="width:160px;">
							<col style="width:160px;">
							<col style="width:160px;">
							<col style="width:160px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">등급조건</th>
								<th scope="col">
									<div>
										<img src="/static/images/my/ratingBenefit-step01.png" alt="ROOKIE">
										<p>신규회원<br/>~ 10만원 미만 구매</p>
									</div>
								</th>
								<th scope="col">
									<div>
										<img src="/static/images/my/ratingBenefit-step02.png" alt="SILVER SLUGGER">
										<p>10만원 이상<br/>~ 30만원 미만 구매</p>
									</div>
								</th>
								<th scope="col">
									<div>
										<img src="/static/images/my/ratingBenefit-step03.png" alt="GOLD GLOVE">
										<p>30만원 이상<br/>~ 50만원 미만 구매</p>
									</div>
								</th>
								<th scope="col">
									<div>
										<img src="/static/images/my/ratingBenefit-step04.png" alt="MVP">
										<p>50 만원 이상 구매</p>
									</div>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th rowspan="2">COUPON</th><!-- 20190114 변경-->
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>10%</p>
										</div>
										<p>온라인 전용 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>온라인 전용 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>온라인 전용 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>20%</p>
										</div>
										<p>온라인 전용 1장</p>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>10%</p>
										</div>
										<p>온/오프라인 통합 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>온/오프라인 통합 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>온/오프라인 통합 1장</p>
									</div>
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<div class="cartDiv">
											<p>20%</p>
										</div>
										<p>온/오프라인 통합 1장</p>
									</div>
								</td>
							</tr>
							<!-- 20190114삭제 <tr>
								<td>&nbsp;</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-Delivery.png" alt="무료배송">
										<p>온라인 전용 1장</p>
									</div>	
								</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-Delivery.png" alt="무료배송">
										<p>온라인 전용 2장</p>
									</div>	
								</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-Delivery.png" alt="무료배송">
										<p>온라인 전용 3장</p>
									</div>	
								</td>
							</tr>-->
							<tr>
								<th>POINT</th>
								<td>&nbsp;</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-point.png" alt="1,000원">
										<p>1,000원</p>
									</div>	
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-point.png" alt="3,000원">
										<p>3,000원</p>
									</div>	
								</td>
								<td>
									<!-- 2018.12.15 -->
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-point.png" alt="5,000원">
										<p>5,000원</p>
									</div>	
								</td>
							</tr>
							<!-- 20180109 삭제<tr>
								<th>+BENEFIT</th>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-gift.png" alt="SPECIAL 선물 증정">
										<p>SPECIAL 선물 증정</p>
									</div>	
								</td>
							</tr> -->
						</tbody>
					</table>
				</div>
				<!--// MLB 등급 혜택 안내 E -->
				
				<!-- 회원 혜택 안내 S 20180109 삭제
				<h3 class="title04">회원 혜택 안내</h3>
				
				<div class="ratingBenefit-list ratingBenefitnone">
					<table>
						<caption>회원 혜택 안내</caption>
						<colgroup>
							<col style="width:130px;">
							<col style="width:160px;">
							<col style="width:160px;">
							<col style="width:160px;">
							<col style="width:160px;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="col">신규가입 혜택</th>
								<th scope="col">
									<div class="CouponST">
										<div class="mileageDiv">
											<p>2,500원</p>
										</div>
										<p>F&F 통합 마일리지</p>
									</div>
								</th>
								<th scope="col">
									<div class="CouponST">
										<div class="cartDiv">
											<p>10%</p>
										</div>
										<p>온/오프라인 통합 1장</p>
									</div>
								</th>
								<th scope="col">
									<div class="CouponST">
										<div class="cartDiv">
											<p>10%</p>
										</div>
										<p>온라인 전용 1장</p>
									</div>
								</th>
								<th scope="col">
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>첫구매 후 온라인 전용 1장</p>
									</div>
								</th>
							</tr>
							<tr>
								<th>생일쿠폰</th>
								<td>&nbsp;</td>
								<td>
									<div class="CouponST">
										<div class="cartDiv">
											<p>15%</p>
										</div>
										<p>(본인) 온/오프라인 통합 1장</p>
									</div>
								</td>
								<td>
									<div class="CouponST">
										<div class="cartDiv">
											<p>10%</p>
										</div>
										<p>(자녀) 온/오프라인 통합 최대 3장</p>
									</div>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<th>MILEAGE</th>
								<td colspan="4">
									<div class="CouponST">
										<div class="mileageDiv">
											<p>5%</p>
										</div>
										<p>결제금액 Payback</p>
									</div>
								</td>
							</tr>
							<tr>
								<th>POINT</th>
								<td>&nbsp;</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-point.png" alt="포토리뷰 작성 1,000원">
										<p>포토리뷰 작성 1,000원</p>
									</div>	
								</td>
								<td>
									<div class="CouponST">
										<img src="/static/images/my/ratingBenefit-point.png" alt="텍스트 리뷰 작성 300원">
										<p>텍스트 리뷰 작성 300원</p>
									</div>	
								</td>
								<td>&nbsp;</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!--// 회원 혜택 안내 E -->
				
				<ul class="text-list02">
					<li>일부 상품은 쿠폰 적용에서 제외될수 있습니다.</li>
					<li>마일리지 5% 적립 혜택은 온라인 전용이며, 오프라인 매장별 적립율이 상이합니다.</li>
					<li>등급산정및 혜택지급은 분기별 1일 오전10시 반영됩니다.</li>
				</ul>
				
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.ratingBenefit-pop'); 
});
</script>
</body>
</html>