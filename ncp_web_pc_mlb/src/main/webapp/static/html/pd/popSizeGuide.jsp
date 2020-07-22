<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>

<article id="lypopSizeGuide" class="layer-popup lypopSizeGuide">
	<section class="layer-popup-cont" tabindex="0">
		<h2>SIZE GUIDE</h2>
		<div class="layer-cont ly-box scroll">
			<p class="txtIcon02 txtGray">아래 사이즈는 해당 브랜드의 표준 상품 상세 사이즈이며, 재는 위치나 방법에 따라 약간의 오차가 있을 수 있습니다.</p>
			<!-- <p class="txtIcon02 txtGray">아래 사이즈는 해당 브랜드의 표준 상품 상세 사이즈이며, 재는 위치나 방법에 따라 약간의 오차가 있을 수 있습니다.</p> -->
			
			<div class="sizeGuideBox">
				<!-- 성인모자일 경우 표시(그 외 비노출) Button-->
				<div class="btnMlbCapGuide"><a href="javascript:;" class="btn sm gray pd20">MLB CAP GUIDE</a></div>
				
				<div class="sizeGuideImg">
					<img src="/static/images/_temp/size_guide_thum.png" alt="상품사이즈"><!-- SIZE : 397 * 397 -->
				</div>
			</div>		
						
			<!--  치수항목  sample -->
			<table class="board-list st01">
				<caption>치수항목</caption>
				<colgroup>
					<col style="width:px">
					<col style="width:60px">
					<col style="width:60px">
					<col style="width:60px">
					<col style="width:60px">
					<col style="width:60px">
				</colgroup>
				<thead>
					<tr>
						<th>치수항목(cm)</th>
						<th>85</th>
						<th>90</th>
						<th>95</th>
						<th>100</th>
						<th>105</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>가슴둘레</th>
						<td>115.0</td>
						<td>120.0</td>
						<td>125.0</td>
						<td>130.0</td>
						<td>135.0</td>						
					</tr>
					<tr>
						<th>총장</th>
						<td>110.0</td>
						<td>113.0</td>
						<td>118.0</td>
						<td>121.0</td>
						<td>124.0</td>						
					</tr>
				</tbody>
			</table>
			<!-- //치수항목  sample -->
			
			<!--  MLB 신발 사이즈표01  -->
			<table class="board-list st02" style="display:none">
				<caption> MLB 신발 사이즈표</caption>
				<colgroup>
					<col style="width:38%">
					<col style="">
				</colgroup>
				<thead>
					<tr class="bgNone">
						<th>신발사이즈(mm)</th>
						<th>발길이(mm)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>230</td>
						<td>211 ~ 220</td>
					</tr>	
					<tr>
						<td>240</td>
						<td>221 ~ 230</td>
					</tr>
					<tr>
						<td>250</td>
						<td>231 ~ 240</td>
					</tr>
					<tr>
						<td>260</td>
						<td>241 ~ 250</td>
					</tr>
					<tr>
						<td>270</td>
						<td>251 ~ 260</td>
					</tr>
					<tr>
						<td>280</td>
						<td>261 ~ 270</td>
					</tr>
					<tr>
						<td>290</td>
						<td>271 ~ 280</td>
					</tr>			
				</tbody>
			</table>
			<!-- // MLB 신발 사이즈표01  -->

			<!--  MLB 신발 사이즈표02  -->
			<table class="board-list st02"  style="display:none">
				<caption> MLB 신발 사이즈표</caption>
				<colgroup>
					<col style="width:38%">
					<col style="">
				</colgroup>
				<thead>
					<tr class="bgNone">
						<th>신발사이즈(mm)</th>
						<th>발길이(mm)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>160</td>
						<td>154 ~ 163</td>
					</tr>	
					<tr>
						<td>170</td>
						<td>164 ~ 173</td>
					</tr>
					<tr>
						<td>180</td>
						<td>174 ~ 183</td>
					</tr>
					<tr>
						<td>190</td>
						<td>184 ~ 193</td>
					</tr>
					<tr>
						<td>200</td>
						<td>194 ~ 203</td>
					</tr>
					<tr>
						<td>210</td>
						<td>204 ~ 213</td>
					</tr>
					<tr>
						<td>220</td>
						<td>214 ~ 223</td>
					</tr>
					<tr>
						<td>230</td>
						<td>224 ~ 233</td>
					</tr>			
				</tbody>
			</table>
			<!-- // MLB 신발 사이즈표02  -->	
			
			<!--  MLB KIDS 공통 모자 사이즈표 -->
			<table class="board-list st01"  style="display:none">
				<caption>MLB KIDS 공통 모자 사이즈표</caption>
				<colgroup>
					<col style="width:px">
					<col style="width:87px">
					<col style="width:87px">
					<col style="width:87px">
					<col style="width:87px">					
				</colgroup>
				<thead>
					<tr>
						<th>사이즈(cm)</th>
						<th>F1</th>
						<th>F2</th>
						<th>F3</th>
						<th>FREE</th>						
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>머리둘레</th>
						<td>49</td>
						<td>49 ~ 51</td>
						<td>53 ~ 55</td>
						<td>52 ~ 55</td>										
					</tr>
					<tr>
						<th>챙 길이</th>
						<td colspan="4">6 ~ 6.5(오차 ±0.3)</td>															
					</tr>
					<tr>
						<th>모자 높이</th>
						<td colspan="4">15 ~ 16.5(오차 ±0.3)</td>										
					</tr>					
				</tbody>
			</table>
			<!--  // MLB KIDS 공통 모자 사이즈표 -->	
			
			<!--  MLB 공통 모자 사이즈표 -->
			<table class="board-list st01"  style="display:none">
				<caption>MLB 공통 모자 사이즈표</caption>
				<colgroup>
					<col style="width:px">
					<col style="width:70px">
					<col style="width:70px">
					<col style="width:70px">
					<col style="width:70px">	
					<col style="width:70px">				
				</colgroup>
				<thead>
					<tr>
						<th>사이즈(cm)</th>
						<th>XS ~ S</th>
						<th>S ~ M</th>
						<th>M ~ XL</th>
						<th>L ~ XXL</th>						
						<th>FREE</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>머리둘레</th>
						<td>55</td>
						<td>57</td>
						<td>59</td>
						<td>61</td>
						<td>57 ~ 59</td>										
					</tr>
					<tr>
						<th>챙 길이</th>
						<td colspan="5">6.8 ~ 7.0(오차 ±0.3)</td>															
					</tr>
					<tr>
						<th>모자 높이</th>
						<td colspan="5">17.5(오차 ±0.2)</td>										
					</tr>					
				</tbody>
			</table>
			<!--  // MLB 공통 모자 사이즈표 -->	
			
			<!-- ACE 1-FIT (스냅백)  -->
			<table class="board-list st01"  style="display:none">
				<caption>스냅백 사이즈 표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>												
						<th>FREE 사이즈</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>58(스냅백 2개를 채웠을 때)</td>															
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>17</td>															
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>11.5</td>															
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>17</td>															
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>7</td>															
					</tr>										
				</tbody>
			</table>
			<!-- // ACE 1-FIT (스냅백)  -->	
			
			<!-- ACE 2-FIT (스냅백)  -->
			<table class="board-list st01"  style="display:none">
				<caption>스냅백 사이즈 표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>												
						<th>FREE 사이즈</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>58(스냅백 2개를 채웠을 때)</td>															
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>13</td>															
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>18</td>															
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>7</td>															
					</tr>										
				</tbody>
			</table>
			<!-- // ACE 2-FIT (스냅백)  -->	
			
			<!-- ACE 3-FIT (스냅백)  -->
			<table class="board-list st01"  style="display:none">
				<caption>스냅백 사이즈 표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>												
						<th>FREE 사이즈</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>58(스냅백 2개를 채웠을 때)</td>															
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>9.4</td>															
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>18</td>															
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>12.7</td>															
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>18</td>															
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>7</td>															
					</tr>										
				</tbody>
			</table>
			<!-- // ACE 3-FIT (스냅백)  -->	
			
			<!-- AD X-FIT (커브조절캡)  -->
			<table class="board-list st01"  style="display:none">
				<caption>커브조절캡 사이즈 표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>												
						<th>FREE 사이즈</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>min 50 ~ max 65</td>															
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>12</td>															
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>17</td>															
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>7</td>															
					</tr
					<tr>
						<th>G</th>
						<td>뒷고리</td>
						<td>16</td>															
					</tr>										
				</tbody>
			</table>
			<!-- //AD X-FIT (커브조절캡)  -->		
			
			<!-- SLIDER-FIT(볼캡) /SLIDER COVER-FIT(커버핏 볼캡)  -->
			<table class="board-list st01"  style="display:none">
				<caption>볼캡/커버핏 볼캡 사이즈 표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:40%">
					<col style="width:40%">
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>												
						<th>FREE 사이즈</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>min 51 ~ max 65</td>															
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>16.5</td>															
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>16.5</td>															
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>12.5</td>															
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>17.5</td>															
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>7</td>															
					</tr
					<tr>
						<th>G</th>
						<td>뒷고리</td>
						<td>16</td>															
					</tr>										
				</tbody>
			</table>
			<!-- // SLIDER-FIT(볼캡) /SLIDER COVER-FIT(커버핏 볼캡)  -->	
			
			<!--  BEANIE HAT(비니햇) -->
			<table class="board-list st01"  style="display:none">
				<caption>BEANIE HAT 사이즈표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:96px">
					<col style="width:96px">
					<col style="width:96px">
					<col style="width:96px">										
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>
						<th>SHORT</th>
						<th>MID</th>
						<th>LONG</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>기장</td>
						<td>15 ~ 17</td>
						<td>20 ~ 22</td>
						<td>22 ~ 24</td>																
					</tr>
					<tr>
						<th>B</th>
						<td>커프스 높이</td>
						<td>6.5 ~ 7</td>
						<td>6.5 ~ 7</td>
						<td>6.5 ~ 7</td>																
					</tr>			
				</tbody>
			</table>
			<!--  //BEANIE HATT(비니햇) -->	
			
			<!--  BUCKET HAT(버킷햇) -->
			<table class="board-list st01"  style="display:none">
				<caption>BUCKET HAT 사이즈표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:26.5%">
					<col style="width:26.5%">
					<col style="width:26.5%">														
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>
						<th>FREE 1</th>
						<th>FREE 2</th>						
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>57</td>
						<td>59</td>											
					</tr>
					<tr>
						<th>B</th>
						<td>윗판 가로너비</td>
						<td>14.5</td>
						<td>15.5</td>																
					</tr>
					<tr>
						<th>C</th>
						<td>윗판 세로너비</td>
						<td>17</td>
						<td>18</td>																
					</tr>
					<tr>
						<th>D</th>
						<td>앞 패널(중앙)</td>
						<td>9</td>
						<td>9</td>																
					</tr>
					<tr>
						<th>E</th>
						<td>앞패널</td>
						<td>31</td>
						<td>31</td>																
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>6</td>
						<td>6</td>																
					</tr>			
				</tbody>
			</table>
			<!--  // BUCKET HATT(버킷햇) -->	
			
			<!--  DOME HAT(돔햇) -->
			<table class="board-list st01"  style="display:none">
				<caption>DOME HAT 사이즈표</caption>
				<colgroup>
					<col style="width:">
					<col style="width:26.5%">
					<col style="width:26.5%">
					<col style="width:26.5%">														
				</colgroup>
				<thead>
					<tr>
						<th>위치</th>
						<th>명칭</th>
						<th>55</th>
						<th>57</th>						
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>57</td>
						<td>59</td>											
					</tr>	
					<tr>
						<th>D</th>
						<td>앞 패널(중앙)</td>
						<td>16</td>
						<td>16.5</td>																
					</tr>
					<tr>
						<th>E</th>
						<td>앞패널(가로)</td>
						<td>10.5</td>
						<td>11</td>																
					</tr>
					<tr>
						<th>F</th>
						<td>챙길이</td>
						<td>6</td>
						<td>6</td>																
					</tr>
				</tbody>
			</table>
			<!-- // DOME HAT(돔햇) -->	
			
			
			<!--  FLEX X-FIT(커브캡) -->
			<table class="board-list st01"  style="display:none">
				<caption>커브캡 사이즈표</caption>
				<colgroup>
					<col style="width:21%">
					<col style="width:">	
					<col style="width:13%">
					<col style="width:13%">
					<col style="width:13%">
					<col style="width:13%">															
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">위치</th>
						<th rowspan="2">명칭</th>
						<th class="lineB">55</th>
						<th class="lineB">57</th>						
						<th class="lineB">59</th>
						<th class="lineB">61</th>
					</tr>
					<tr>						
						<th>(XS-M)</th>
						<th>(S-M)</th>						
						<th>(M-XL)</th>
						<th>(L-XL)</th>
					</tr>					
				</thead>
				<tbody>
					<tr>
						<th>A</th>
						<td>내경(머리둘레)</td>
						<td>55</td>
						<td>57</td>											
						<td>59</td>
						<td>61</td>
					</tr>
					<tr>
						<th>B</th>
						<td>앞 패널(중앙)</td>
						<td>175</td>
						<td>17.5</td>
						<td>17.5</td>
						<td>17.5</td>																
					</tr>
					<tr>
						<th>C</th>
						<td>앞 패널(사이드)</td>
						<td>16.8</td>
						<td>17.3</td>
						<td>17.3</td>
						<td>17.3</td>																						
					</tr>
					<tr>
						<th>D</th>
						<td>뒷 패널(중앙)</td>
						<td>17.5</td>
						<td>18.4</td>
						<td>18.54</td>
						<td>18.9</td>																
					</tr>
					<tr>
						<th>E</th>
						<td>뒷 패널(사이드)</td>
						<td>17.1</td>
						<td>17.6</td>																
						<td>18</td>
						<td>18</td>
					</tr>
					<tr>
						<th>F</th>
						<td>내경(머리둘레)</td>
						<td colspan="4">7</td>												
					</tr>									
								
				</tbody>
			</table>
			<!--  //FLEX X-FIT(커브캡) -->					
			
																															
			
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