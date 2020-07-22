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
	<div class="contain dp look" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<div class="look_hd"><span class="tit">LOOKBOOK</span></div>
				<div class="mds_look_main">

					<%@ include file="../dp/look_lnb.jsp" %>

					<section class="ctns">
						<div class="look_cate">
							<ul class="menu">
								<li><span class="txt"><em class="t">2019SS SEASON</em></span></li>
							</ul>
						</div>
						<div class="look_banner">
							<ul class="list">
								<li><a href="/static/html/dp/look_list.jsp"><span class="banner"><img src="/static/images/_temp/dp_look_mn_1.png" alt=""></span></a></li>
								<li><a href="/static/html/dp/look_list.jsp"><span class="banner"><img src="/static/images/_temp/dp_look_mn_2.png" alt=""></span></a></li>
								<li><a href="/static/html/dp/look_list.jsp"><span class="banner"><img src="/static/images/_temp/dp_look_mn_3.png" alt=""></span></a></li>
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