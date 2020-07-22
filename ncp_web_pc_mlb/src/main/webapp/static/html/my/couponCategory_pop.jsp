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
							<button type="button" class="d_select_sel" disabled><span>상품명</span></button>
							<ul>
								<li><a href="javascript:;">상품명01</a></li>
								<li><a href="javascript:;">상품명02</a></li>
								<li><a href="javascript:;">상품명03</a></li>
							</ul>
						</div>
						<!-- //select -->
						<input type="text" placeholder="" class="input-style02" disabled />
						<a href="javascript:;" class="btn sm">검색</a>
					</div>
					
				</div>
				<!-- //검색E -->
				
				<div class="board-list">
					<table>
						<caption>쿠폰 적용 가능 상품 리스트, 카테고리, 할인율/액</caption>
						<colgroup>
							<col>
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">카테고리</th>
								<th scope="col">할인율/액</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="2" class="no-result">전체 적용 쿠폰입니다.</td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
								</td>
								<td><b>15%</b></td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
									<a href="javascript:;">2 Depth</a>
									<a href="javascript:;">3 Depth</a>
								</td>
								<td><b>15%</b></td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
									<a href="javascript:;">2 Depth</a>
								</td>
								<td><b>15%</b></td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
								</td>
								<td><b>15%</b></td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
									<a href="javascript:;">2 Depth</a>
									<a href="javascript:;">3 Depth</a>
								</td>
								<td><b>15%</b></td>
							</tr>
							<tr>
								<td class="tleft">
									<a href="javascript:;">1 Depth</a>
									<a href="javascript:;">2 Depth</a>
								</td>
								<td><b>15%</b></td>
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