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
	<div class="contain dp look list" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<div class="look_hd"><span class="tit">LOOKBOOK</span></div>
				<div class="mds_look_main">

					<%@ include file="../dp/look_lnb.jsp" %>

					<section class="ctns">
						<div class="look_cate">
							<ul class="menu">
								<li><span class="txt"><em class="t">2019SS SEASON</em></span></li>
								<li>
									<span class="txt"><em class="t">[MLB X EXO] WINTER DOWN COLLECTION</em><a href="javascript:;" class="bt">하위메뉴열기</a></span>
									<div class="subs">
										<a href="javascript:;" class="close">닫기</a>
										<ul class="list">
											<li><a href="/static/html/dp/look_list.jsp" class="lk"><em class="t">[MLB X EXO] WINTER DOWN COLLECTION</em></a></li>
											<li><a href="/static/html/dp/look_list.jsp" class="lk"><em class="t">2019 Hit MEGALOGO</em></a></li>
											<li><a href="/static/html/dp/look_list.jsp" class="lk"><em class="t">[MLB X EXO] WINTER DOWN COLLECTION</em></a></li>
											<li><a href="/static/html/dp/look_list.jsp" class="lk"><em class="t">2019 MLB KIDS</em></a></li>
										</ul>
									</div>
								</li>
							</ul>
						</div>
						<div class="look_list">
							<ul class="list">
								<li><div class="thumb"><a href="/static/html/dp/look_view.jsp"><span class="img"><img src="/static/images/_temp/dp_look_thumb_1.png" alt=""></span></a></div></li>
								<li><div class="thumb"><a href="/static/html/dp/look_view.jsp"><span class="img"><img src="/static/images/_temp/dp_look_thumb_2.png" alt=""></span></a></div></li>
								<li><div class="thumb"><a href="/static/html/dp/look_view.jsp"><span class="img"><img src="/static/images/_temp/dp_look_thumb_3.png" alt=""></span></a></div></li>
								<li><div class="thumb"><a href="/static/html/dp/look_view.jsp"><span class="img"><img src="/static/images/_temp/dp_look_thumb_4.png" alt=""></span></a></div></li>
								<li><div class="thumb"><a href="/static/html/dp/look_view.jsp"><span class="img"><img src="/static/images/_temp/dp_look_thumb_5.png" alt=""></span></a></div></li>
							</ul>
						</div>
					</section>
	
				</div>

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