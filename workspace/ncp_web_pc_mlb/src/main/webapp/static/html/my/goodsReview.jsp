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

			<h2 class="title01">상품리뷰</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents goods-review-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">상품리뷰</strong>
					</p>
				</div> 
				
				<p class="txt13-666">리뷰 작성 시, 온라인몰에서 사용 가능한 포인트를 적립해 드립니다. (텍스트리뷰 300포인트, 포토리뷰 1,000포인트 적립)</p>
				
				<div class="d_tab02">
				
					<!-- 상품리뷰 tab S -->
					<ul class="tab-type01 tab-blockList blockList02">
						<li class="d_tab02_select on"><a href="javascript:;">상품리뷰 작성</a></li>
						<li class="d_tab02_select"><a href="javascript:;">작성한 상품리뷰</a></li>
					</ul>
					<!--// 상품리뷰 tab E -->
					
					<!-- 상품리뷰 작성 S -->
					<div class="d_tab02_cont" style="display:block;">
						<div class="tbst-div">
							<div class="mid fl">
								<span>전체</span> (<span class="text-color01">100</span>건)
							</div>
						</div>
						<hr class="hr-666" />
						<div class="goodsreviewList">
							<ul>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray disabled">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray disabled">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="javascript:;" class="btn sm gray">구매확정하기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray disabled">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="/static/html/my/textReview_pop.jsp" class="btn sm gray disabled">텍스트리뷰 작성</a>
											<a href="/static/html/my/photoReview_pop.jsp" class="btn sm gray">포토리뷰 작성</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
											</div>
											<ul>
												<li><span>WHITE</span><span>230</span></li>
												<li>주문일시 : <span>2018년 10월 26일</span></li>
											</ul>
										</div>
										<div class="goods-R-BtnBox">
											<a href="javascript:;" class="btn sm gray">구매확정하기</a>
										</div>
									</div>
								</li>
								<li class="list-noneinfo">
									<p>리뷰 작성이 가능한 상품이 없습니다.</p>
								</li>
							</ul>
						</div>
						
						<%@ include file="../_inc/paging.jsp" %>
						
					</div>
					<!--// 상품리뷰 작성 E -->
					
					<!-- 작성한 상품리뷰 S -->
					<div class="d_tab02_cont">
						<%@ include file="../_inc/uiDateRange2.jsp" %>
						<div class="tbst-div">
							<div class="mid fl">
								<span>전체</span> (<span class="text-color01">100</span>건)
							</div>
						</div>
						<hr class="hr-666" />
						<div class="goodsreviewView d_accordion">
							<ul>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
														<ul class="imgplusMore">
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
															</li>
														</ul>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
														<ul class="imgplusMore">
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
															</li>
														</ul>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
														<ul class="imgplusMore">
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
															</li>
														</ul>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li>
									<div class="goodsreviewViewBox">
										<div class="goods-R-ImgBox">
											<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
										</div>
										<div class="goods-R-InfoBox">
											<div>
												<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
												<strong><span>WHITE</span><span>230</span></strong>
											</div>
											<ul>
												<li>
													<div class="review-grade sm">
														<strong class="grade-type05"><p>주문일시 : <span>2018년 10월 26일</span></p></strong>
													</div>
												</li>
												<li>
													<p><span class="imgplusIcon"></span>[포토 리뷰] 친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여</p>
													<div class="d_accordion_cont">
														<p>친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.친구한테 선물했는데 너무너무 맘에 들어합니다~너무 이쁘네여.</p>
														<div class="clearfix">
															<div class="fl">
																<ul>
																	<li>
																		<strong>사이즈</strong><span>딱 맞아요</span>
																	</li>
																	<li>
																		<strong>컬러</strong><span>화면과 같아요</span>
																	</li>
																</ul>
															</div>
															<div class="fr">
																<a href="javascript:;" class="btn sm gray">수정</span></a>
																<a href="javascript:;" class="btn sm gray">삭제</span></a>
															</div>
														</div>
														<ul class="imgplusMore">
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_2.png" alt=""></a>
															</li>
															<li>
																<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_3.png" alt=""></a>
															</li>
														</ul>
													</div>
												</li>
											</ul>
										</div>
										<div class="goods-AC-BtnBox d_accordion_select">
											<a href="javascript:;">보기</a>
										</div>
									</div>
								</li>
								<li class="list-noneinfo">
									<p>작성하신 리뷰가 없습니다.</p>
								</li>
							</ul>
						</div>
						
						<%@ include file="../_inc/paging.jsp" %>
						
					</div>
					<!--// 작성한 상품리뷰 E --> 
					
				</div>
				
				<div class="contTxtBox">
					<strong>유의사항</strong>
					<ul class="text-list01">
						<li>구매확정하신 상품에 대해서만 리뷰를 작성하실 수 있으며, 구매확정 후 90일이 지난 상품에 대해서는 리뷰를 작성하실 수 없습니다.</li>
						<li>[작성한 상품 리뷰] 페이지에서 작성하신 상품 리뷰를 확인할 수 있습니다.</li>
						<li>상품과 직접적으로 관계가 없는 내용이나 약관 및 법률 등에 위배되는 글은 고객님께 사전 동의없이 미노출될 수 있습니다.</li>
						<li>다음과 같은 경우 리뷰가 제한되거나 관리자의 권한으로 삭제될 수 있습니다.
							<ul class="text-list02">
								<li>상품과 적합하지 않은 콘텐츠나 무관한 사진</li>
								<li>사이트 내 게시된 상품 및 전시 이미지 그대로 사용</li>
								<li>타 회원의 리뷰 도용</li>
								<li>내용이 부적합하거나 비속어 사용</li>
							</ul>
						</li>
						<li>리뷰 작성 후, 적립된 포인트는 온라인 쇼핑몰에서만 사용가능하며, 2년 간 유효합니다. 2년이 경과된 포인트는 익월 1일에 소멸됩니다.<a href="javascript:;" class="text-color01">포인트 확인하러 가기</a></li>
					</ul>
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