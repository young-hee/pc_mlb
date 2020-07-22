<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>

<button class="btn" onclick="layerPopup.popupOpenNow('#popZipSch'); ">우편번호 팝업 결과없음</button>
<button class="btn" onclick="layerPopup.popupOpenNow('#popZipSch1'); ">우편번호 팝업 결과</button>

<article id="popZipSch" class="layer-popup popZipSch">
	<section class="layer-popup-cont" tabindex="0">
		<h2>우편번호 찾기</h2>

		<div class="layer-cont">

			<div class="search-wrap">
				<div class="search-input04">
					<input type="search" placeholder="" class="input-style02"><button type="button" class="btn sm btn-sch"><span>검색</span></button>
				</div>
			</div>

			<ul class="text-list02">
				<li>도로명 + 건물번호 예) 언주로 541</li>
				<li>읍/면/동/리 + 지번 예) 역삼동 662-9</li>
				<li>건물명 예) 에프앤에프사옥본관</li>
				<li>시/군/구 + 도로명, 동명 또는 건물명 예) 서울시 언주로</li>
				<li>사서함+사서함번호 예) 서울강남우체국사서함 1</li>
			</ul>

			<div class="board-list valign-m">
				<table>
					<caption>우편번호</caption>
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
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<article id="popZipSch1" class="layer-popup popZipSch">
	<section class="layer-popup-cont" tabindex="0">
		<h2>우편번호 찾기</h2>

		<div class="layer-cont">

			<div class="search-wrap">
				<div class="search-input04">
					<input type="search" placeholder="" class="input-style02"><button type="button" class="btn sm btn-sch"><span>검색</span></button>
				</div>
			</div>

			<p class="txt-sub-info01">주소를 클릭하시면 자동입력 됩니다.</p>

			<div class="board-list valign-m">
				<table>
					<caption>우편번호</caption>
					<colgroup>
						<col width="70px">
						<col>
					</colgroup>
					<tbody>						
						<tr>
							<td>08846</td>
							<td class="tleft">
								<ul class="address-result">
									<li><span>도로명</span><a href="#">서울시 종로구 양산2길 10</a></li>
									<li><span>지번</span><a href="#">서울시 종로구 123-45</a></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td>08846</td>
							<td class="tleft">
								<ul class="address-result">
									<li><span>도로명</span><a href="#">서울시 종로구 양산2길 10</a></li>
									<li><span>지번</span><a href="#">서울시 종로구 123-45</a></li>
								</ul>
							</td>
						</tr>
						<tr>
							<td>08846</td>
							<td class="tleft">
								<ul class="address-result">
									<li><span>도로명</span><a href="#">서울시 종로구 양산2길 10</a></li>
									<li><span>지번</span><a href="#">서울시 종로구 123-45</a></li>
								</ul>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<%@ include file="../_inc/paging.jsp" %>

		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popZipSch'); 
});
</script>
</body>
</html>