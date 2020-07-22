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
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01">위시리스트</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents wishList-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">위시리스트</strong>
					</p>
				</div> 
				
				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01">100</span>건)
					</div>
					<div class="mid fr">
						<a href="javascript:;" class="btn fill sm"><span>전체선택</span></a>
					</div>
				</div>
				
				<hr class="hr-666" />
				
				<div class="item-listST">
					<ul>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">[KIDS] 뉴욕 양키스 남여공용 미니 박스 로고 후드 티셔츠</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">#daily MLB 트레이닝 조거 팬츠</a></p>
								<p><strong>790,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><span class="btn fill sm">SOLD OUT</span><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 블랙팬서 표범 맨투맨</a></p>
								<p><del>109,000</del><strong>76,300</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<p>일시품절 상품입니다.</p>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)니트잼머양키스(KNIT JAMMER YANKEES)</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 메가테잎 다운 롱패딩(MEGA TAPE)</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 빅로고 하프 배색 맨투맨</a></p>
								<p><strong>790,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><span class="btn fill sm">SOLD OUT</span><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)니트잼머양키스(KNIT JAMMER YANKEES)</a></p>
								<p><del>109,000</del><strong>76,300</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<p>품절된 상품입니다.</p>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">뉴욕양키스 MLBlized 후드티셔츠</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						
						<!-- <li class="list-noneinfo">
							<p>위시리스트 보관 상품이 없습니다.<br/>상품을 위시리스트에 담아 두시면 언제든지 쉽게 상품을 찾으실 수 있습니다.</p>
						</li> -->
					</ul>
				</div>
				
				<%@ include file="../_inc/paging.jsp" %>
				
				<hr class="hr-ddd" />
				
				<div class="mdGoodsProduct-List">
					<p>이 상품을 본 고객이 함께 본 상품</p>

					<%@ include file="../_inc/inc_recommend.jsp" %> 
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