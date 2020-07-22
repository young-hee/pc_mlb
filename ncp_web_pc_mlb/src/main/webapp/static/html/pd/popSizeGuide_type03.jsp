<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>

<article id="lypopSizeGuide" class="layer-popup lypopSizeGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>SIZE GUIDE</h2>
		<div class="layer-cont ly-box scroll">
			<p class="txtIcon02 txtGray">아래 사이즈는 해당 브랜드의 표준 상품 상세 사이즈이며, 재는 위치나 방법에 따라 약간의 오차가 있을 수 있습니다.</p>
			
			<!-- **  s : html ** -->
			<h2>SIZE INFO</h2>
			<!-- 도식화 'fr' : S -->
			<div class="fr">
				<img src="http://img.fnf.co.kr/shop4/shoes_illust.jpg" alt="" />
			</div>
			<!-- 도식화 : E -->
			<div class="fl" style="width:560px">
				<p>아래 사이즈는 해당 브랜드의 표준 상품 상세사이즈이며, <br>사이즈는 재는 위치나 방법에 따라 약간의 오차가 있을 수 있습니다.</p>
				<table class="table_type11 shoes">
					<colgroup>
						<!-- 첫번째 열 너비만 지정 -->
						<col style="width:180px" />
					</colgroup>
					<thead>
						<tr>
							<th scope="col">신발사이즈 (mm)</th>
							<th scope="col">발길이 (mm)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">160</th>
							<td>154 ~ 163</td>
						</tr>
						<tr>
							<th scope="row">170</th>
							<td>164 ~ 173</td>
						</tr>
						<tr>
							<th scope="row">180</th>
							<td>174 ~ 183</td>
						</tr>
						<tr>
							<th scope="row">190</th>
							<td>184 ~ 193</td>
						</tr>
						<tr>
							<th scope="row">200</th>
							<td>194 ~ 203</td>
						</tr>
						<tr>
							<th scope="row">210</th>
							<td>204 ~ 213</td>
						</tr>
						<tr>
							<th scope="row">220</th>
							<td>214 ~ 223</td>
						</tr>
						<tr>
							<th scope="row">230</th>
							<td>224 ~ 233</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="clear">&nbsp;</div>
			<!-- **  e : html ** -->																												
			
			<!--  button 2019.01.15 삭제
			<div class="lyBtnArea"><a href="javascript:;" class="btn fill w160">확인</a></div> -->
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopSizeGuide'); 
});
</script>
</body>
</html>