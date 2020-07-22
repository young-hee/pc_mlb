<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopaddrSelect" class="layer-popup lypopaddrSelect">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2>배송지선택</h2>
		<div class="layer-cont ly-box scroll"> <!-- 스크롤 필요시 class=scroll 추가 -->
		
			<div class="d_tab02">
				<ul class="tab-type01">
					<li class="d_tab02_select on"><a href="javascript:;">배송지정보</a></li>
					<li class="d_tab02_select"><a href="javascript:;">최근배송지</a></li>				
				</ul>
				
				<!-- 배송지 정보 -->
				<div class="d_tab02_cont" style="display:block;">
					<div class="board-list">
						<table style="display:none">
							<!-- 2018.12.28 -->
							<colgroup>
								<col style="width:100px">
								<col style="width:">
								<col style="width:100px">
							</colgroup>
							<!-- //2018.12.28 -->
							<thead>
								<th>받는분</th>
								<th>배송지 정보</th>
								<th>선택</th>								
							</thead>
							<tbody>
								<!-- loop -->
								<tr>
									<td>홍길동<br>[기본주소]</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<!--// loop -->
								<tr>
									<td>홍길동동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>	
								<tr>
									<td>홍길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<tr>
									<td>홍길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<tr>
									<td>홍길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
							</tbody>
						</table>
						<div class="noResultInfo">배송지 정보가 없습니다.</div><!-- 2019.01.11 추가 -->					
					</div>
					<%@ include file="../_inc/paging.jsp" %>
				</div>
				<!-- //배송지 정보 -->
				
				<!-- 최근 배송지  -->
				<div class="d_tab02_cont">
					<div class="board-list">
						<table>
							<!-- 2018.12.28 -->
							<colgroup>
								<col style="width:100px">
								<col style="width:">
								<col style="width:100px">
							</colgroup>
							<!-- // 2018.12.28 -->
							<thead>
								<th>받는분</th>
								<th>배송지 정보</th>
								<th>선택</th>								
							</thead>
							<tbody>
								<!-- loop -->
								<tr>
									<td>김길동동동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<!--// loop -->
								<tr>
									<td>김길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>	
								<tr>
									<td>김길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<tr>
									<td>김길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
								<tr>
									<td>김길동</td>
									<td class="addrBox">(06138)<br>서울특별시 강남구 선릉로 120(대치동, 개포1,2차 우성아파트)<br>1210동 1501호<br>010-2345-6789</td>
									<td><a href="javascript:;" class="btn sm">선택</a></td>
								</tr>
							</tbody>
						</table>
						<div class="noResultInfo">배송지 정보가 없습니다.</div><!-- 2019.01.11 추가 -->
					</div>	
					<%@ include file="../_inc/paging.jsp" %>			
				</div>	
				<!-- //최근 배송지  -->	
			</div>		
		
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopaddrSelect'); 
});
</script>
</body>
</html>