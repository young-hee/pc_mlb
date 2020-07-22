<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup addressFind-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>우편번호 찾기</h2>
		<div class="layer-cont scroll">
			
			<div class="addressFind-popWrap">
			
				<!-- 검색S -->
				<div class="addressFindSrch">
					<input type="search" placeholder="" class="input-style02">
					<a href="javascript:;" class="btn sm">검색</a>
				</div>
				<!-- //검색E -->
				
				<!-- 검색결과가 없는 경우 화면S -->
				<div class="addressFindInner" style="display:none;">
					<ul class="text-list02">
						<li>도로명 + 건물번호 예) 언주로 541</li>
						<li>읍/면/동/리 + 지번 예) 역삼동 662-9</li>
						<li>건물명 예) 에프앤에프사옥본관</li>
						<li>시/군/구 + 도로명, 동명 또는 건물명 예) 서울시 언주로</li>
						<li>사서함+사서함번호 예) 서울강남우체국사서함 1</li>
					</ul>
					<div class="board-list">
						<table summary="우편번호 찾기">
							<caption>우편번호 찾기</caption>
							<colgroup>
								<col width="70px">
								<col>
							</colgroup>
							<tbody>	
								<tr>
									<td colspan="2" class="no-result">검색결과가 없습니다.</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<!-- //검색결과가 없는 경우 화면E -->
				
				<!-- 검색결과가 있는 경우 화면S -->
				<div class="addressFindInner">
					<p class="txt13-666">주소를 클릭하시면 자동입력 됩니다.</p>
					<div class="board-list">
						<table summary="우편번호 찾기">
							<caption>우편번호 찾기</caption>
							<colgroup>
								<col width="70px">
								<col>
							</colgroup>
							<tbody>	
								<!-- <tr>
									<td colspan="2" class="no-result">검색결과가 없습니다.</td>
								</tr> -->
								<tr>
									<td>08846</td>
									<td>
										<a href="javascript:;">
											<span>도로명</span>
											<p>서울특별시 강남구 언주로 541 (역삼동, 에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
										<a href="javascript:;">
											<span>지번</span>
											<p>서울특별시 강남구 역삼동 662-10 (에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
									</td>
								</tr>
								<tr>
									<td>08846</td>
									<td>
										<a href="javascript:;">
											<span>도로명</span>
											<p>서울특별시 강남구 언주로 541 (역삼동, 에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
										<a href="javascript:;">
											<span>지번</span>
											<p>서울특별시 강남구 역삼동 662-10 (에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
									</td>
								</tr>
								<tr>
									<td>08846</td>
									<td>
										<a href="javascript:;">
											<span>도로명</span>
											<p>서울특별시 강남구 언주로 541 (역삼동, 에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
										<a href="javascript:;">
											<span>지번</span>
											<p>서울특별시 강남구 역삼동 662-10 (에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프에프앤에프)</p>
										</a>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<%@ include file="../_inc/paging.jsp" %>
				</div>
				<!-- //검색결과가 있는 경우 화면E -->
				
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.addressFind-pop'); 
});
</script>
</body>
</html>