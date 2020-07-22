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
	<div class="contain pd goods" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				
				<div class="gdsWrap">

					<!-- 상품  -->
					<section class="sect product">
						<%@ include file="../pd/inc_pd_gd_center.jsp" %>
						
						<%@ include file="../pd/inc_pd_gd_right.jsp" %>
						
						<%@ include file="../pd/inc_pd_gd_left.jsp" %>
					</section>

					<!-- 연관상품 -->
					<%@ include file="../pd/inc_pd_related.jsp" %>


					<!-- STYLE IN SNS -->
					<%@ include file="../pd/inc_pd_olapic.jsp" %>


					<!-- STYLE IN MLB -->
					<%@ include file="../pd/inc_pd_brand.jsp" %>

						
					<!-- 추천상품 -->
					<section class="mds-section recom">
						<div class="hdt"><span class="tit">이 상품을 구매한 분들이 많이 본 상품</span></div>
						<%@ include file="../_inc/inc_recommend.jsp" %>
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