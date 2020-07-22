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
					
					<!-- 판매중인상품이 아님 -->
					<section class="sect stopSale">
						<div class="in">
							<div class="msg">
								<div class="dt">현재 판매중인 상품이 아닙니다.</div>
								<div class="dd">MLB몰을 이용해주셔서 감사합니다.</div>
							</div>
							<div class="bts">
								<a href="javascript:;" class="btn fill lg btnGoMain">메인으로 이동</a>
							</div>
						</div>
					</section>

						
					<!-- 추천상품 -->
					<section class="mds-section recom">
						<div class="hdt"><span class="tit">추천상품</span></div>
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
	
});
</script>
</body>
</html>