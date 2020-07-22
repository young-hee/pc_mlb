<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>


<style>
html,body{ overflow:initial; }
#wrap{ overflow:initial; }
#contain{ position:static; }
.device{ border:#ddd solid 1px; border-radius:4px; padding:10px; font-size:12px;}
.device h3{ font-weight:bold; }
.popLayerSample1 .popBody{ width:600px; }
.bxslider li{ float: left; width:200px; }
.codeBox{ position:relative; margin:10px 0; }
.codeBox .bts{/* position:absolute; right:10px; top:10px; */ padding:5px 0;}
.codeBox .bts .btn{ border-color:#bbbbbb; color:#555555; border-radius:3px; height:24px; line-height:24px; padding:0px 7px ; }
.codeBox .code{ line-height:20px; color:#541717; font-size:11px; background-color:#fbfbfb; border:#dddddd solid 1px; border-radius:3px; padding:0 5px; margin:0px; font-family:"Consolas"; }
div.syntaxhighlighter{ margin:5px 0px !important; }
.syntaxhighlighter table{ width:initial !important; }
.syntaxhighlighter table td.gutter .line,
.syntaxhighlighter table td.code .line {
  min-width:14px; padding:0.2em 0.5em 0.2em 0.5em !important;
}
.syntaxhighlighter a, .syntaxhighlighter div, .syntaxhighlighter code, .syntaxhighlighter table, .syntaxhighlighter table td, .syntaxhighlighter table tr, .syntaxhighlighter table tbody, .syntaxhighlighter table thead, .syntaxhighlighter table caption, .syntaxhighlighter textarea{ font-size:12px !important; }
</style>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain xx guide" id="contain">
		<div class="container">
			<div class="location-container">
				<div class="location-contents">
					<h2 class="title01">가이드페이지</h2>
					<p class="location">
						<span><a href="/static/html/mn/main.jsp">Home</a></span><span>가이드페이지</span><span>주문정보</span><strong title="현재 위치">주문/배송상세</strong>
					</p>
				</div>
			</div>
			<main class="contents" id="contents">
				<p>&nbsp;</p>
				<div class="device">
					<h3>User Agent</h3>
					<div id="userAgent" class="color-blue"></div>
					<div id="cssStatus" class="color-red"></div>
					<div id="browserStatus" class="color-orange"></div>
				</div>
				<p>&nbsp;</p>


				<br><br><h1 class="title01" style="color:red">타이틀</h1><br><br>
				<h2 class="title01">고객센터 .title01</h2>
				<h3 class="title02">자주하는 질문 .title02</h3>

				<br><br><br><br><h1 class="title01" style="color:red">로케이션바</h1>
				<p class="location">
					<span>Home</span><span>가나다</span><span>가나다</span><strong title="현재 위치">고객센터</strong>
				</p>



				
				<br><br><h1 class="title01" style="color:red">페이징</h1><br><br>
				
				<%@ include file="../_inc/paging.jsp" %>

				<br><br><h1 class="title01" style="color:red">버튼</h1>
				<h2 class="title04" style="color:#999">확인 취소 버튼</h2>

				<div>
					<a href="javascript:;" class="btn xs">버튼 .btn.xs</a>
					<a href="javascript:;" class="btn sm">버튼 .btn.sm</a>
					<a href="javascript:;" class="btn">버튼 .btn</a>
					<a href="javascript:;" class="btn lg">버튼 .btn.lg</a>
					<a href="javascript:;" class="btn xl">버튼 .btn.xl</a>
				</div>
				<div>
					<a href="javascript:;" class="btn white">버튼 .btn</a>
					<a href="javascript:;" class="btn gray">버튼 .btn.gray</a>
					<a href="javascript:;" class="btn fill">버튼 .btn.fill</a>
					<a href="javascript:;" class="btn orange">버튼 .btn.orange</a>
					<a href="javascript:;" class="btn orange fill">버튼 .btn.orange.fill</a>
				</div>
				<div>
					<a href="javascript:;" class="btn white lg btn-confirm">확인</a>
					<a href="javascript:;" class="btn gray xs btn-cancel">취소</a>
					<a href="javascript:;" class="btn fill sm btn-order">주문</a>
					<a href="javascript:;" class="btn orange fill btn-goMain">메인가기</a>
				</div>


				<br><br><h1 class="title01" style="color:red">탭 스타일</h1>
				<h2 class="title04" style="color:#999">tab-type01</h2><br>
				<ul class="tab-type01 d_tab">
					<li><a href="javascript:;">전체</a></li>
					<li class="on"><a href="javascript:;">알림/소식</a></li>
					<li><a href="javascript:;">이벤트 당첨</a></li>
				</ul>

				<h2 class="title04" style="color:#999">tab-type02</h2>
				<ul class="tab-type02 d_tab">
					<li><a href="javascript:;">전체</a></li>
					<li class="on"><a href="javascript:;">회원정보</a></li>
					<li><a href="javascript:;">멤버쉽</a></li>
					<li><a href="javascript:;">상품문의</a></li>
					<li><a href="javascript:;">주문/입금결제</a></li>
					<li><a href="javascript:;">마일리지/포인트/쿠폰문의</a></li>
					<li><a href="javascript:;">배송</a></li>
					<li><a href="javascript:;">취소/반품/환불/교환</a></li>
					<li><a href="javascript:;">이벤트/세일</a></li>
					<li><a href="javascript:;">매장기타</a></li>
				</ul>

				<h2 class="title04" style="color:#999">tab-type03</h2>
				<ul class="tab-type03 d_tab">
					<li class="on"><a href="javascript:;">전체</a></li>
					<li><a href="javascript:;">알림/소식</a></li>
					<li><a href="javascript:;">이벤트 당첨</a></li>
				</ul>


				<h2 class="title04" style="color:#999">tab-type04</h2><br>
				<ul class="tab-type04 d_tab">
					<li class="on"><a href="javascript:;">전체</a></li>
					<li><a href="javascript:;">알림/소식</a></li>
					<li><a href="javascript:;">이벤트 당첨</a></li>
				</ul>


				<h2 class="title04" style="color:#999">tab-type05</h2>
				<ul class="tab-type05 d_tab">
					<li class="on"><a href="javascript:;">전체</a></li>
					<li><a href="javascript:;">알림/소식</a></li>
				</ul>


				<h2 class="title04" style="color:#999">tab-type06</h2>
				<div class="tab-type06">
					<ul>
						<li><a href="#anchor01">TOP</a></li>
						<li><a href="#anchor02">BOTTOM</a></li>
						<li><a href="#anchor03">SHOES</a></li>
						<li><a href="#anchor04">KIDS</a></li>
						<li><a href="#anchor05">WOMEN</a></li>
						<li><a href="#anchor06">ACC</a></li>
						<li><a href="#anchor07">STYLE</a></li>
					</ul>
				</div>

				
				<br><br><h2 class="title04" style="color:#999">d_tab02</h2>
				<div class="d_tab02">
					<ul class="tab-type01">
						<li class="d_tab02_select on"><a href="javascript:;">전체</a></li>
						<li class="d_tab02_select"><a href="javascript:;">알림/소식</a></li>
						<li class="d_tab02_select"><a href="javascript:;">이벤트 당첨</a></li>
					</ul>
					<div class="d_tab02_cont" style="display:block;">탭내용 1</div>
					<div class="d_tab02_cont">탭내용 2</div>
					<div class="d_tab02_cont">탭내용 3</div>
				</div>

				<br><br><h2 class="title04" style="color:#999">.uiTabNav .uiTabPan</h2>
				<ul class="uiTabNav tab-type01">
					<li class="active"><a href="#tabSample_1">탭1</a></li>
					<li><a href="#tabSample_2">탭2</a></li>
					<li><a href="#tabSample_3">탭2</a></li>
				</ul>
				<div class="uiTabPan">
					<div class="panel active" id="tabSample_1">탭 내용 1</div>
					<div class="panel" id="tabSample_3">탭 내용 3</div>
					<div class="panel" id="tabSample_2">탭 내용 2</div>
				</div>


				<br><br><h1 class="title01" style="color:red">좌우정렬</h1>

				<div class="clearfix">
					<div class="fl">
						<a href="javascript:;" class="btn">쓰기</span></a>
					</div>
					<div class="fr">
						<a href="javascript:;" class="btn">쓰기</span></a>
					</div>
				</div>


				<div class="justify-wrap">
					<h3 class="title02 left">자주하는 질문 Top 10</h3>
					<a href="javascript:;" class="btn-style01 right"><span>자주묻는질문 바로가기</span></a>
				</div>


				<h3 class="title02">블릿 리스트</h3>
				<ul class="text-list01">
					<li>네이버페이는 .text-list01</li>
					<li>주문 변경 시 카드사 혜택 및 할부 적용 여부는 해당 카드사..</li>
					<li>네이버페이 카드 간편결제는 네이버페이에서 제공하는 네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는</li>
				</ul>
				<ul class="text-list02">
					<li>네이버페이는 .text-list02</li>
					<li>주문 변경 시 카드사 혜택 및 할부 적용 여부는 해당 카드사..</li>
					<li>네이버페이 카드 간편결제는 네이버페이에서 제공하는 네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는네이버페이 카드 간편결제는 네이버페이에서 제공하는</li>
				</ul>
				<ul class="text-list03">
					<li>1) 가나다 text-list03</li>
					<li>2) 가나다</li>
					<li>3) 가나다</li>
				</ul>





				<br><br><h1 class="title01" style="color:red">별점</h1>

				<div class="review-grade">
					<strong class="grade-type05">별 평<em>(4)</em>점</strong>
				</div>

				<div class="review-grade sm">
					<strong class="grade-type05">별 평<em>(4)</em>점</strong>
				</div>


				<br><br><h1 class="title01" style="color:red">input 타입</h1>
				<h2 class="title04" style="color:#999">text, password style height 40</h2><br>
				<div>
					<input type="text" class="input-style01" title="타이틀" placeholder="타이틀 입력" style="width:500px;">   <br><br>
					<input type="password" class="input-style01" title="타이틀" placeholder="패스워드 입력" style="width:500px;">
				</div>
				<h2 class="title04" style="color:#999">text, password style height 30</h2><br>
				<div>
					<input type="text" class="input-style02" title="타이틀" placeholder="타이틀 입력" style="width:500px;">    <br><br>
					<input type="password" class="input-style02" title="타이틀" placeholder="패스워드 입력" style="width:500px;">
				</div>
				<h2 class="title04" style="color:#999">체크박스</h2><br>
				<div>
					<span class="check-skin">
						<input type="checkbox" id="chkReply" checked="checked">
						<span>선택</span>
					</span>
					<label for="chkReply">선택</label>
					<span class="check-skin">
						<input type="checkbox" id="chkReply01">
						<span>선택</span>
					</span>
					<label for="chkReply01">선택</label>
					<span class="check-skin">
						<input type="checkbox" id="chkReply02" disabled>
						<span>선택</span>
					</span>
					<label for="chkReply02">선택</label>
				</div>
				<h2 class="title04" style="color:#999">라디오 박스</h2><br>
				<div>
					<span class="rdo-skin">
						<input type="radio" id="rdoType01" name="rdoType" checked="checked">
						<span>선택</span>
					</span><label for="rdoType01">선택</label>

					<span class="rdo-skin">
						<input type="radio" id="rdoType02" name="rdoType">
						<span>선택</span>
					</span><label for="rdoType02">선택</label>

					<!-- <span class="rdo-skin">
						<input type="radio" id="rdoType03" name="rdoType" disabled>
						<span>선택</span>
					</span><label for="rdoType03">선택</label> -->
				</div>
				<h2 class="title04" style="color:#999">셀렉트박스</h2><br>
				<!-- select -->
				<div class="select-style01 d_select">
					<button type="button" class="d_select_sel" style="width: 152px;"><span>선택</span></button>
					<ul>
						<li><a href="javascript:;">배송지연/불만</a></li>
						<li><a href="javascript:;">반품문의</a></li>
						<li><a href="javascript:;">A/S문의</a></li>
						<li><a href="javascript:;">환불문의</a></li>
						<li><a href="javascript:;">주문결제문의</a></li>
						<li><a href="javascript:;">회원정보문의</a></li>
						<li><a href="javascript:;">취소문의</a></li>
						<li><a href="javascript:;">교환문의</a></li>
						<li><a href="javascript:;">상품정보문의</a></li>
						<li><a href="javascript:;">기타문의</a></li>
						<li><a href="javascript:;">이벤트문의</a></li>
					</ul>
				</div>



				<div class="select-style02 d_select">
					<button type="button" class="d_select_sel" style="width: 152px;"><span>선택선택선택선택선택선택선택</span></button>
					<ul>
						<li><a href="javascript:;">배송지연/불만배송지연/불만배송지연/불만배송지연/불만</a></li>
						<li><a href="javascript:;">반품문의</a></li>
						<li><a href="javascript:;">A/S문의</a></li>
						<li><a href="javascript:;">환불문의</a></li>
						<li><a href="javascript:;">주문결제문의</a></li>
						<li><a href="javascript:;">회원정보문의</a></li>
						<li><a href="javascript:;">취소문의</a></li>
						<li><a href="javascript:;">교환문의</a></li>
						<li><a href="javascript:;">상품정보문의</a></li>
						<li><a href="javascript:;">기타문의</a></li>
						<li><a href="javascript:;">이벤트문의</a></li>
					</ul>
				</div>


				<div class="select-style03 d_select">
					<button type="button" class="d_select_sel" style="width: 152px;"><span>선택선택선택선택선택선택선택</span></button>
					<ul>
						<li><a href="javascript:;">배송지연/불만배송지연/불만배송지연/불만배송지연/불만</a></li>
						<li><a href="javascript:;">반품문의</a></li>
						<li><a href="javascript:;">A/S문의</a></li>
						<li><a href="javascript:;">환불문의</a></li>
						<li><a href="javascript:;">주문결제문의</a></li>
						<li><a href="javascript:;">회원정보문의</a></li>
						<li><a href="javascript:;">취소문의</a></li>
						<li><a href="javascript:;">교환문의</a></li>
						<li><a href="javascript:;">상품정보문의</a></li>
						<li><a href="javascript:;">기타문의</a></li>
						<li><a href="javascript:;">이벤트문의</a></li>
					</ul>
				</div>
				<!-- //select -->
				
				<h2 class="title04" style="color:#999">수량</h2><br>
				<div class="quantity-wrap">
					<button type="button" class="pq-minus">빼기</button>
					<button type="button" class="pq-plus">추가</button>
					<input type="number" title="수량" value="1">
				</div>

				<h2 class="title04" style="color:#999">파일찾기</h2><br>
				<!-- file search -->
				<span class="file-search">
					<input type="text" class="input-style01" readonly="readonly" style="width:443px;">
					<span>
						<input type="file" id="fileSearch">
						<label for="fileSearch">파일찾기</label>
					</span>
				</span>
				<!-- //file search -->

				<h2 class="title04" style="color:#999">달력</h2><br>
				<!-- calendar -->
				<input type="text" class="calendar">
				<!-- //calendar -->

				<h2 class="title04" style="color:#999">기간달력</h2><br>
				<script>
					// $(document).ready(function() {
					// 	var dateFormat = "yy-mm-dd",
					// 	from = $( "#calendarFrom" ).datepicker().on( "change", function() {
					// 		to.datepicker( "option", "minDate", getDate( this ) );
					// 	}),
					// 	to = $( "#calendarTo" ).datepicker().on( "change", function() {
					// 		from.datepicker( "option", "maxDate", getDate( this ) );
					// 	});

					// 	function getDate( element ) {
					// 		var date;
					// 		try {
					// 			date = $.datepicker.parseDate( dateFormat, element.value );
					// 		} catch( error ) {
					// 			date = null;
					// 		}

					// 		return date;
					// 	}
					// });
				</script>
				
				<%@ include file="../_inc/uiDateRange2.jsp" %>
				<br>
				<%@ include file="../_inc/uiDateRange.jsp" %>

				<br><br><br><br><h1 class="title01" style="color:red">슬라이드</h1><br><br>
				<div class="swiper-container slideSample" id="slideSample">
					<ul class="swiper-wrapper">
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></span></a></li>
						<li class="swiper-slide"><a href="javascript:;"><span class="item-img"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></span></a></li>
					</ul>
					<button type="button" class="btNav prev">이전</button>
					<button type="button" class="btNav next">다음</button>
				</div>
				<style>
					.slideSample{}
					.slideSample img{ width:100%; }
					.slideSample .btNav{ background: url(/static/images/cm/btn_swiper_sample.png) no-repeat;position:absolute; top:50%; width: 40px; height: 80px; margin-top:-40px; z-index:10;  font-size: 0;}
					.slideSample .btNav.prev {	left: 0;}
					.slideSample .btNav.next {	right: 0; background-position: 0 -80px;}
					.slideSample .btNav.prev.swiper-button-disabled {	background-position: 0 -160px;	opacity: 1;}
					.slideSample .btNav.next.swiper-button-disabled {	background-position: 0 -240px;	opacity: 1;}
				</style>
				<script>
					// http://idangero.us/swiper/api/
					var slideSample = new Swiper('#slideSample', {
						slidesPerGroup: 4,
						slidesPerView: 4,
						navigation: {
							nextEl: '.btNav.next',
							prevEl: '.btNav.prev'
						}
					});
				</script>


				<br><br><br><br><h1 class="title01" style="color:red">테이블 리스트</h1><br><br>
				<h2 class="title04" style="color:#999">board-list</h2><br>
				<div class="board-list">
					<table>
						<caption>자주하는 질문 Top 10 번호, 구분, 제목, 조회수, 등록일 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:119px;">
							<col>
							<col style="width:150px;">
							<col style="width:150px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">구분</th>
								<th scope="col">제목</th>
								<th scope="col">조회수</th>
								<th scope="col">등록일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="5" class="no-result">공지사항 검색결과가 없습니다.</td>
							</tr>
							<tr>
								<td>5</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>1254</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>4</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>1254</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>3</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>1254</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>2</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>1254</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>1</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>1254</td>
								<td>2018-02-22</td>
							</tr>
						</tbody>
					</table>
				</div>
				<h2 class="title04" style="color:#999">board-list02</h2><br>
				<div class="board-list02">
					<table>
						<caption>주문/배송조회 리스트</caption>
						<colgroup>
							<col>
							<col style="width:150px;">
							<col style="width:150px;">
							<col style="width:150px;">
							<col style="width:150px;">
						</colgroup>
						<thead>
							<tr>
								<th colspan="5" class="order-info-header">
									<ul>
										<li>매장주문</li>
										<li><span>주문일</span>2018-03-19 09:53</li>
										<li><span>주문번호</span><a href="javascript:;">OD1521191087718</a></li>
									</ul>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tleft02">
									<div class="product-info">
										<div class="product-info-img"><a href="javascript:;"><img src="http://img.fnf.co.kr/PARTS/X/18S/thumbnail/DWRT6N831-MG-l-16623200-m.png" alt="상품이미지"></a></div>
										<div class="product-info-text">
											<div class="product-info-box">
												<div class="product-more-info">
													<span class="text-color01">[예약주문]</span>
												</div>
												<p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠</a></p>
												<div class="product-price">
													<span>199,000원</span>
												</div>
											</div>
											<div class="product-option">
												<span>BL / 90</span>
											</div>
										</div>
									</div>
								</td>
								<td><span class="text-quantity">수량</span><span class="text-quantity">1</span></td>
								<td>9,999,000원</td>
								<td><p class="text-status">입금대기</p><a href="javascript:;" class="btn-style01"><span>결제수단변경</span></a></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>




				<br><br><h1 class="title01" style="color:red">토글 테이블 리스트</h1><br><br>

				<div class="d_toggle">
					<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
					<div class="d_toggle_cont">
						sdfsddsfsf
					</div>
				</div>
				<div class="d_toggle">
					<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
					<div class="d_toggle_cont">
						sdfsddsfsf
					</div>
				</div>

				<br><br><h1 class="title01" style="color:red">아코디언 리스트</h1><br><br>
				<div class="d_accordion">
					<ul>
						<li>
							<a href="javascript:;" class="d_accordion_select">제목</a>
							<div class="d_accordion_cont">
								내용내용내용내용내용내용내용
							</div>
						</li>
						<li>
							<a href="javascript:;" class="d_accordion_select">제목</a>
							<div class="d_accordion_cont">
								내용내용내용내용내용내용내용
							</div>
						</li>
					</ul>
				</div>

				<br><br><h1 class="title01" style="color:red">아코디언 테이블 리스트</h1><br><br>
				<div class="board-list accordion-type d_accordion">
					<table>
						<caption>자주하는 질문 Top 10 번호, 분류, 제목 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:216px;">
							<col>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">분류</th>
								<th scope="col">제목</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="3" class="no-result">검색결과가 없습니다. 1:1 고객상담을 이용해 주시면 빠르고 정확히 알려드리겠습니다.</td>
							</tr>
							<tr>
								<td>10</td>
								<td class="tleft">이벤트당첨</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select"><span>질문</span>회원가입을 해야 구매할 수 있나요?</p>
									<div class="reply"><span>답변</span>고객님께서 주문하신 주문서가 상품 준비중이라면 ‘My Page’의 취소/반품/교환에서 바로 취소처리가 가능합니다. 하지만, 제품이 출고가 된 후에는 취소가 어려우며 반품으로 처리되고 고객 변심에 의한 주문취소일 경우 왕복 배송비는 고객님께서 부담해 주셔야 합니다.</div>
								</td>
							</tr>
							<tr>
								<td>9</td>
								<td class="tleft">주문/입금결제</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">회원이어야만 주문할 수 있나요? </p>
									<div class="reply">고객님께서 주문하신 주문서가 상품 준비중이라면 ‘My Page’의 취소/반품/교환에서 바로 취소처리가 가능합니다. 하지만, 제품이 출고가 된 후에는 취소가 어려우며 반품으로 처리되고 고객 변심에 의한 주문취소일 경우 왕복 배송비는 고객님께서 부담해 주셔야 합니다.</div>
								</td>
							</tr>
							<tr>
								<td>1</td>
								<td class="tleft">이벤트당첨</td>
								<td class="ask-wrap">
									<p class="inquiry d_accordion_select">외국인도 가입이 되나요?</p>
									<div class="reply">고객님께서 주문하신 주문서가 상품 준비중이라면 ‘My Page’의 취소/반품/교환에서 바로 취소처리가 가능합니다. 하지만, 제품이 출고가 된 후에는 취소가 어려우며 반품으로 처리되고 고객 변심에 의한 주문취소일 경우 왕복 배송비는 고객님께서 부담해 주셔야 합니다.</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<br><br><h1 class="title01" style="color:red">테이블 리스트 상세보기</h1><br><br>
				<div class="board-view">
					<div class="board-header">
						<strong>.board-view [공지] 디스커버리 7번째 원정대 최종 합격자 발표</strong>
						<p><span>구분 :<em>알림/소식</em></span><span>등록일 :<em>2018-02-22</em></span><span>조회수 :<em>1518</em></span></p>
					</div>
					<div class="board-cnt">
						안녕하세요. 디스커버리입니다. <br>
						설 연휴 배송 안내 드립니다.<br><br>
						2/13(화) 오전 9시까지 결제완료된 건에 한해 2/14(수)까지 출고,<br>
						이후 주문 건은 2/19(월)부터 순차배송 되오니 이 점 양해 바랍니다.  <br><br>
					</div>
				</div>
				<ul class="view-page">
					<li>
						<span>이전글</span><a href="javascript:;">[공지] ONE DAY PRICE 이벤트 조기 종료 안내 </a>
					</li>
					<li>
						<span>다음글</span><a href="javascript:;">[당첨자 발표] 12월 연말감사 이벤트 </a>
					</li>
				</ul>

				<br><br><h1 class="title01" style="color:red">테이블 입력표</h1><br><br>
				<p class="text-required"><span class="required">*</span> 는 필수입력 항목입니다</p>
				<div class="board-write">
					<table summary="1:1고객상담 입력">
						<caption>1:1고객상담 입력</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteEmail">답변 이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteEmail" class="input-style01" value="abcd" style="width:152px;">
								<span class="at">@</span>
								<input type="text" class="input-style01" style="width:152px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:152px;"><span>직접입력</span></button>
									<ul>
										<li><a href="javascript:;">직접입력</a></li>
										<li><a href="javascript:;">naver.com</a></li>
										<li><a href="javascript:;">daum.net</a></li>
										<li><a href="javascript:;">nate.com</a></li>
										<li><a href="javascript:;">gmail.com</a></li>
										<li><a href="javascript:;">Hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<div class="search-input">
									<input type="search" title="공지사항 검색"><button type="button">검색</button>
								</div>
								<span class="error-msg" style="display:block;">잘못된 이메일 형식입니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">휴대전화</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteContact" class="input-style01" style="width:65px;" />
								<span class="hyphen">-</span>
								<input type="text" class="input-style01" style="width:75px;" />
								<span class="hyphen">-</span>
								<input type="text" class="input-style01" style="width:75px;" />

								<span class="check-skin">
									<input type="checkbox" id="chkReply" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkReply">답변등록여부 수신</label>

								<span class="error-msg" style="display:block;">숫자만 입력해야 합니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteNumber">주문번호</label></th>
							<td>
								<input type="text" id="boardWriteNumber" class="input-style01" style="width:443px;">
								<a href="#layerPopup01" class="btn-style04 d_layer_open">찾아보기</a>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteSort">상담분류</label> <span class="required">*</span></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select select_consul_cate">
									<button type="button" id="boardWriteSort" class="d_select_sel" style="width:152px;"><span>선택</span></button>
									<ul>
										<li><a href="javascript:;">선택</a></li>
										<li><a href="javascript:;">배송지연/불만</a></li>
										<li><a href="javascript:;">반품문의</a></li>
										<li><a href="javascript:;">A/S문의</a></li>
										<li><a href="javascript:;">환불문의</a></li>
										<li><a href="javascript:;">주문결제문의</a></li>
										<li><a href="javascript:;">회원정보문의</a></li>
										<li><a href="javascript:;">취소문의</a></li>
										<li><a href="javascript:;">교환문의</a></li>
										<li><a href="javascript:;">상품정보문의</a></li>
										<li><a href="javascript:;">기타문의</a></li>
										<li><a href="javascript:;">이벤트문의</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:block;">상담분류는 필수입력 입니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteTitle">제목</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteTitle" class="input-style01" placeholder="제목입력은 30자 미만입니다." style="width:587px;">
								<span class="error-msg" style="display:block;">제목은 필수 입력 입니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent">내용</label> <span class="required">*</span></th>
							<td>
								<div>
									<textarea cols="30" rows="10" id="boardWriteContent" placeholder="내용입력은 1000자 미만입니다. (특수문자 \ / : &lt; &gt; 는 사용할 수 없습니다.)" style="width:587px; height:190px;"></textarea>
									<span class="error-msg" style="display:block;">더 이상 입력하실 수 없습니다.</span>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="fileSearch">첨부파일</label></th>
							<td>
								<span class="file-search">
									<input type="text" class="input-style01" style="width:443px;">
									<span>
										<input type="file" id="fileSearch">
										<label for="fileSearch">파일찾기</label>
									</span>
								</span>
								<ul class="text-list02 col-type01">
									<li>첨부 파일은 1개만 가능하며, 10Mb 이하의 파일만 업로드 가능합니다.</li>
									<li>업로드 가능한 파일형식은 [hwp, doc/docx, xls/xlsx, ppt/pptx, jpg, gif, png, pdf] 입니다.</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>

				<br><br><h1 class="title01" style="color:red">상품 리스트</h1><br><br>



				
				<h2 class="title04" style="color:#999">네이버 관련 버튼</h2><br>
				<div>
					<a href="javascript:;" class="btn-npay02"><span>네이버로 로그인</span></a>
					<a href="javascript:;" class="btn-npay03"><span>네이버 아이디 연결</span></a>
				</div>
				<h2 class="title04" style="color:#999">테이블 안에 들어가는 버튼</h2> <br>
				
				
				<h2 class="title04" style="color:#999">sns 공유 버튼</h2> <br>
				<div>
					<a href="javascript:;" class="btn-style02 btn-kakaostory">카카오스토리로 공유하기</a> <a href="javascript:;" class="btn-style02 btn-facebook">페이스북으로 공유하기</a>
				</div>
				<br><br><h1 class="title01" style="color:red">layerpopup</h1><br>
				layerPopup.popupOpenNow('#lyPopSample2'); <br>		
				layerPopup.popupCloseNow('#layerPopup01');<br>
				<div class="btn-wrap">
					<a href="javascript:;" onclick="layerPopup.popupOpenNow('#lyPopSample1')" class="btn">레이어팝업</a>
					<a href="javascript:;" onclick="layerPopup.popupOpenNow('#lyPopSample2')" class="btn">레이어팝업 내부 스크롤</a>
					<a href="javascript:;" onclick="layerPopup.popupOpenNow('#lyPopSample3')" class="btn">레이어팝업 작은넘</a>

					<a href="#lyPopSample1" class="btn d_layer_open">레이어팝업</a>
					<a href="#lyPopSample2" class="btn d_layer_open">레이어팝업 내부 스크롤</a>

				</div>
				<h2 class="title04" style="color:#999">툴팁 버튼</h2><br>
				<div style="text-align:center">
					<div class="tooltip-wrap d_dropdown">
						<button type="button" class="tooltip-detail d_dropdown_sel" title="배송안내 도움말">도움말</button>
						<div class="tooltip-layer d_dropdown_cont">
							<strong class="tooltip-title">배송 안내</strong>
							<div class="tooltip-cnt">
								<p class="tooltip-info-txt">50,000원 이상 구매 시 무료배송 <br>배송비 2,500원은 주문 시 합산되어 결제됩니다. <br>할인 적용으로 결제금액 변경 시, 무료배송이 적용되지 않을 수 있습니다.</p>
							</div>
							<button type="button" class="tooltip-close d_dropdown_close">닫기</button>
						</div>
					</div>
				</div>
				
				<br><br><br><br>
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


var html = document.documentElement;
function showInfo() {
	$('#cssStatus').html(html.className);
	$('#browserStatus').html('Client Size : ' + html.clientWidth + 'x' + html.clientHeight + ', Orientation : ' + window.orientation);
}
$(function() {
	$('#userAgent').html(navigator.userAgent);
	showInfo();
	$(document.body).bind('orientationchange', showInfo);
	$(window).bind('orientationchange', showInfo);
	$(window).bind('resize', showInfo);
});
</script>
</body>
</html>