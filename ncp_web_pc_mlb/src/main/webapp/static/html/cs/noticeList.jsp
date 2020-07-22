<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain cs" id="contain">
		<div class="container">
		
			<h2 class="title01">공지사항</h2>
			
			<main class="contents noticeList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>고객센터</span><strong title="현재 위치">공지사항</strong>
					</p>
				</div> 
				
				<!-- tab S -->
				<ul class="tab-type02 d_tab">
					<li class="on"><a href="javascript:;">전체</a></li>
					<li><a href="javascript:;">알림/소식</a></li>
					<li><a href="javascript:;">이벤트 당첨</a></li>
				</ul>
				<!--//tab E -->
				
				<!-- 검색 S -->
				<div class="search-wrap01 right">
					<div class="search-input">
						<input type="search" title="검색" placeholder="궁금하신 내용을 입력해 주세요." /><button type="button">검색</button>
					</div>
				</div>
				<!--//검색 E -->
				
				<!-- table S -->
				<div class="board-list">
					<div class="boardCount">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
					<table>
						<caption>공지사항 번호, 구분, 제목, 조회수, 등록일 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:120px;">
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
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
								<td colspan="5" class="no-result">공지사항이 없습니다.</td>
							</tr>
							<tr>
								<td>10</td>
								<td>알림/소식</td>
								<td class="tleft"><a href="javascript:;">MLB 공식몰 리뉴얼 오픈 기념 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>9</td>
								<td>알림/소식</td>
								<td class="tleft"><a href="javascript:;">7월 플러스친구 이벤트 2차 당첨자</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>8</td>
								<td>이벤트 당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>7</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>6</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>5</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>4</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>3</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>2</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
							<tr>
								<td>1</td>
								<td>이벤트당첨</td>
								<td class="tleft"><a href="javascript:;">[당첨자 발표] 설맞이 설선물 이벤트 당첨자 발표</a></td>
								<td>12,345</td>
								<td>2018-02-22</td>
							</tr>
						</tbody>
					</table>
					
					<%@ include file="../_inc/paging.jsp" %>
					
				</div>
				<!--//table E -->

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
</script>
</body>
</html>