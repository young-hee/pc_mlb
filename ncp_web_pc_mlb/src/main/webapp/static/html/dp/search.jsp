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
	<div class="contain dp list search" id="contain">
		<div class="container">

			<main class="contents" id="contents">




				<div class="dp_sch_box">
					<div class="in">
						<form class="box" action="/static/html/dp/search.jsp">
							<span class="keyword"><input type="text" class="key" value="엑소 멋진모자"></span>
							<button type="submit" class="btnSch">검색</button>
						</form>
						

						<div class="result_msg">
							<p><em class="t">엑소 멋진모자</em>에 대한 검색결과가 없습니다. <br> 다른 검색어를 입력하시거나 철자, 띄어쓰기를 확인해 보세요.</p>
						</div>

						

					</div>
				</div>

				<%@ include file="../pd/inc_pd_recommend.jsp" %>



				<div class="result_total"><em class="t">검색결과 TOTAL</em> <em class="n">(704)</em></div>

				<section class="mds-section dp_list_sec">
					
					<!-- 상품 필터,정렬 -->
					<%@ include file="../dp/inc_ui_filter_sort.jsp" %>

					<div class="ui_dp_list">
						<ul class="list">
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="sold_out">SOLD OUT</em><span class="img"><img src="/static/images/_temp/goods_thumb_1.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_2.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="soon">COMING SOON</em><span class="img"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="sold_out">SOLD OUT</em><span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><span class="img"><img src="/static/images/_temp/goods_thumb_5.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_6.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="sold_out">SOLD OUT</em><span class="img"><img src="/static/images/_temp/goods_thumb_1.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_2.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="soon">COMING SOON</em><span class="img"><img src="/static/images/_temp/goods_thumb_2.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_3.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><em class="sold_out">SOLD OUT</em><span class="img"><img src="/static/images/_temp/goods_thumb_4.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
							<li>
								<div class="item">
									<div class="thumb"><a href="javascript:;"><span class="img"><img src="/static/images/_temp/goods_thumb_6.png" class="first" alt=""><img src="/static/images/_temp/goods_thumb_5.png" class="second" alt=""></span></a></div>
									<div class="info">
										<div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
										<div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
									</div>
								</div>
							</li>
						</ul>
					</div>


					<%@ include file="../_inc/paging.jsp" %>
				</section>




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