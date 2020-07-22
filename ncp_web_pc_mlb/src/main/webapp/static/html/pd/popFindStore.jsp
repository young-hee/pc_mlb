<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="popFindStore" class="layer-popup popFindStore">
	<section class="layer-popup-cont" tabindex="0">
		<h2>FIND STORE</h2>
		<div class="layer-cont">
			
			<div class="findStore-popWrap">
				<ul class="text-list02 col-type01">
					<li>You can view MLB / MLB KIDS offline stores & online store around the world.</li>
				</ul>
				
				<!-- 매장검색 S -->
				<dl class="search-wrap03 findStoreRow">
					<dt>Brand</dt>
					<dd>
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:100px;"><span>All</span></button>
							<ul>
								<li><a href="javascript:;">MLB</a></li>
								<li><a href="javascript:;">MLB KIDS</a></li>
							</ul>
						</div>
					</dd>
					<dt>Contry/Local</dt>
					<dd>
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:100px;"><span>All</span></button>
							<ul>
								<li><a href="javascript:;">대한민국</a></li>
								<li><a href="javascript:;">중국</a></li>
								<li><a href="javascript:;">대만</a></li>
							</ul>
						</div>
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:120px;"><span>All</span></button>
							<ul>
								<li><a href="javascript:;">서울</a></li>
								<li><a href="javascript:;">경기</a></li>
								<li><a href="javascript:;">인천</a></li>
								<li><a href="javascript:;">경기</a></li>
								<li><a href="javascript:;">강원</a></li>
								<li><a href="javascript:;">충남</a></li>
								<li><a href="javascript:;">대전</a></li>
								<li><a href="javascript:;">충북</a></li>
								<li><a href="javascript:;">부산</a></li>
								<li><a href="javascript:;">울산</a></li>
								<li><a href="javascript:;">대구</a></li>
								<li><a href="javascript:;">경북</a></li>
								<li><a href="javascript:;">경남</a></li>
								<li><a href="javascript:;">전남</a></li>
								<li><a href="javascript:;">광주</a></li>
								<li><a href="javascript:;">전북</a></li>
								<li><a href="javascript:;">제주</a></li>
								<li><a href="javascript:;">홍콩</a></li>
								<li><a href="javascript:;">마카오</a></li>
							</ul>
						</div>
					</dd>
					<dt>Store division</dt>
					<dd>
						<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width:120px;"><span>All</span></button>
							<ul>
								<li><a href="javascript:;">직영점</a></li>
								<li><a href="javascript:;">백화점</a></li>
								<li><a href="javascript:;">대리점</a></li>
								<li><a href="javascript:;">상설점</a></li>
								<li><a href="javascript:;">면세점</a></li>
							</ul>
						</div>
					</dd>
					<dt>Store Name</dt>
					<dd>
						<div class="search-input03">
							<input type="search" style="width:510px;" /><button type="button">Search</button>
						</div>
					</dd>
				</dl>
				<!--//매장검색 E -->
				
				<!-- 매장찾기 S -->
				<div class="findStoreListWrap">
				
					<!--매장리스트 S -->
					<div class="findStoreList">
						<ul>
							<li>
								<div class="storeName">MLB Korea TELFORD</div>
								<ul class="findStoreAddress">
									<li>G32, Telford Plaza, Kowloon Bay, Hong Kong</li>
									<li>+852 2612 0567</li>
									<li><span>office hours : </span>10:00 ~ 22:00</li>
								</ul>
							</li>
							<li>
								<div class="storeName">MLB Korea TELFORD</div>
								<ul class="findStoreAddress">
									<li>G32, Telford Plaza, Kowloon Bay, Hong Kong</li>
									<li>+852 2612 0567</li>
									<li><span>office hours : </span>10:00 ~ 22:00</li>
								</ul>
							</li>
							<li>
								<div class="storeName">MLB Korea TELFORD</div>
								<ul class="findStoreAddress">
									<li>G32, Telford Plaza, Kowloon Bay, Hong Kong</li>
									<li>+852 2612 0567</li>
									<li><span>office hours : </span>10:00 ~ 22:00</li>
								</ul>
							</li>
							<li>
								<div class="storeName">MLB Korea TELFORD</div>
								<ul class="findStoreAddress">
									<li>G32, Telford Plaza, Kowloon Bay, Hong Kong</li>
									<li>+852 2612 0567</li>
									<li><span>office hours : </span>10:00 ~ 22:00</li>
								</ul>
							</li>
						</ul>
					</div>
					<!--//매장리스트 E -->

					<!--매장지도 S -->
					<div class="findStoreMap">
						<div>지도 영역</div>
					</div>
					<!--//매장지도 E -->
				</div>
				<!--//매장찾기 E -->
				

				Overseas Affiliate Online Site



				<div class="btn-wrap">
					<a href="javascript:;" class="btn-style02 d_layer_close">닫기</a>
				</div>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">확인</button>
		</div>
	</section>
</article>
<style>

/* 매장 찾기 */
.popFindStore .findStore-popWrap .text-list02 {margin-top:30px;}
.popFindStore .findStore-popWrap .search-wrap03 {margin-top:20px; padding:20px 20px 20px 30px; overflow:hidden;}
.popFindStore .findStoreRow {overflow:hidden;}
.popFindStore .findStoreRow dt:nth-child(1),
.popFindStore .findStoreRow dt:nth-child(7) {width:90px; margin-right:0; text-align:left;}
.popFindStore .findStoreRow dt {width:100px; margin-left:0; margin-right:5px; text-align:right; height:30px; line-height:30px;}
.popFindStore .findStoreRow dt,
.popFindStore .findStoreRow dd {float:left;}
.popFindStore .findStoreRow dt:nth-child(7),
.popFindStore .findStoreRow dd:last-child {margin-top:5px;}
.popFindStore .findStoreRow .search-input03 {width:auto; border:0;}
.popFindStore .findStoreRow .search-input03 input {width:510px; border:1px solid #ddd; float:left;}
.popFindStore .findStoreRow .search-input03 button {width:77px; border:1px solid #000; background:transparent; margin-left:5px; float:left; background-color:#fff; color:#000; font-size:12px;}
.popFindStore .findStoreListWrap {display:flex; width:100%; height:295px; margin-top:30px; border:1px solid #ddd; border-width:1px 1px 1px 0; border-top-color:#000;}
.popFindStore .findStoreListWrap .no-result {width:100%; margin-top:120px; font-size:13px; color:#666; text-align:center;}
.popFindStore .findStoreListWrap .findStoreList {width:calc(100% - 400px); height:100%; border-right:1px solid #ddd; overflow-y:auto;}
.popFindStore .findStoreListWrap .findStoreMap {width:400px; height:100%; overflow:hidden;}
.popFindStore .findStoreListWrap .findStoreMap > div {width:100%; height:100%;}
.popFindStore .findStoreListWrap .findStoreList>ul>li {padding:16px 20px 16px; border-bottom:1px solid #ddd;}
.popFindStore .findStoreList>ul>li {padding-left:20px; border-bottom:1px solid #ddd;}
.popFindStore .findStoreList>ul>li:last-child {border-bottom:none;}
.popFindStore .findStoreList>ul>li:nth-child(even) {background-color:#f8f8f8;}
.popFindStore .findStoreList .storeName {font-size:14px; color:#000;}
.popFindStore .findStoreList .findStoreAddress {margin-top:8px;}
.popFindStore .findStoreList .findStoreAddress li {position:relative; min-height:18px; font-size:13px; color:#666; line-height:18px;}
.popFindStore .findStoreList .findStoreAddress li span {}
.popFindStore .findStoreList .justify-wrap {margin-top:8px; font-size:13px;}
.popFindStore .findStoreList a {font-size:13px; color:#000; text-decoration:underline;}


</style>
	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popFindStore'); 
});
</script>
</body>
</html>