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
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">주문/배송조회 목록</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><strong title="현재 위치">주문/배송조회 목록</strong>
					</p>
				</div> 
				
				<!--  주문/배송조회   -->
				<div class="orderInfoCon">
				
					<ul class="text-list02">
						<li>**년 *월 **일 이전 주문내역을 확인하시려면 '이전 주문보기' 버튼을 클릭해주세요.</li>
						<li>조회기간은 최대 1년 입니다.</li>
					</ul>
					
					<div class="odSearchOptBox">
						<a href="javascript:;" class="btn sm gray bfOrder">이전 주문보기</a>
						<dl class="period-wrap period-type02 clearfix">
							<!-- 기간달력 -->
							<dt class="blind">주문 유형</dt>
							<dd class="fl">
								<!-- select -->
								<div class="select-style02 d_select">
									<button type="button" class="d_select_sel" style="width:152px;"><span>전체</span></button>
									<ul>
										<li><a href="javascript:;">전체</a></li>
										<li><a href="javascript:;">일반주문</a></li>
										<li><a href="javascript:;">예약주문</a></li>								
										<li><a href="javascript:;">매장픽업주문</a></li>
										<li><a href="javascript:;">대량주문</a></li>
									</ul>
								</div>
								<!-- //select -->
							</dd>
							<dt>조회기간</dt>
							<dd class="fr">
								<a href="javascript:;" class="btn sm d_radio_select on"><span>3개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select"><span>6개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select"><span>12개월</span></a>
								<input type="text" class="calendar" id="calendarFrom"><input type="text" class="calendar" id="calendarTo">
								<a href="javascript:;" class="btn sm">검색</a>
								<!-- <p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p> -->
							</dd>
						</dl>						
						 <!--%@ include file="../_inc/uiDateRange2.jsp" %--> 
					</div>
					
					<!-- order Result -->
					<div class="odSearchResult">	
									
						<div class="odResultTop">
							<strong class="listTotal">주문내역(<em class="fc_red">5</em>건)</strong>
							<a href="javascript:;" class="btn sm gray">주문배송 절차안내</a>	
							<a href="javascript:;" class="btn sm fill">1:1 고객상담</a>						
						</div>
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst 01 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>일반주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">
		                              <col style="width:80px">
		                              <col style="width:120px">
		                              <col style="width:120px">
		                              <col style="width:145px">                              
		                          </colgroup>	          
		                          <tbody>
		                              <!-- loop -->
		                              <tr>
		                                  <td class="tleft">
		                                      <div class="product-info">
		                                          <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
		                                          <div class="product-info-text">
		                                              <div class="product-info-box">
		                                                  <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡</a></p>
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
		                                  <td>2</td>
		                                  <td>398,000원</td>
		                                  <td class="selBox"><span>결제완료</span></td>
		                                  <td class="selBox">
		                                  	<span><a href="javascript:;" class="btn sm gray">주문취소</a><br></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">배송지 변경</a></span>
		                                  </td>
		                               </tr>
		                               <!-- //loop -->
		                               <tr>
		                                  <td class="tleft">
		                                     <div class="product-info product-add-set"><!-- 추가상품 -->
                                                   <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                                   <div class="product-info-text">
                                                       <div class="product-info-box">
                                                           <div class="product-more-info"><span class="text-color01">[추가상품]</span></div>
                                                           <p class="product-name"><a href="javascript:;">[엑소시우민가방] 도토리가방</a></p>
                                                           <div class="product-price">
                                                               <span>17,000원</span>
                                                           </div>
                                                       </div>
                                                       <div class="product-option">
                                                           <span>NY / 7L</span>
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td>2</td>
		                                  <td>398,000원</td>
		                                  <td class="selBox"><span>결제완료</span></td>
		                                  <td class="selBox">
		                                  	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">배송지 변경</a></span>
		                                  </td>
		                               </tr>
 										<tr>
		                                  <td class="tleft">
		                                     <div class="product-info product-free-gift"><!-- 사은품 -->
                                                   <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                                   <div class="product-info-text">
                                                       <div class="product-info-box">
                                                           <div class="product-more-info"><span class="text-color01">[주문사은품]</span></div>
                                                           <p class="product-name"><a href="javascript:;">[엑소시우민가방] 도토리가방</a></p>
                                                           <div class="product-price">
                                                               <span>17,000원</span>
                                                           </div>
                                                       </div>
                                                       <div class="product-option">
                                                           <span>NY / 7L</span>
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td></td>
		                                  <td></td>
		                                  <td class="selBox"></td>
		                                  <td class="selBox"></td>
		                               </tr>		                               
		                               
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  // order LIst 01 -->    
                         	
							<!--  order LIst 02 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>예약주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">
		                              <col style="width:80px">
		                              <col style="width:120px">
		                              <col style="width:120px">
		                              <col style="width:145px">                              
		                          </colgroup>	          
		                          <tbody>
		                              <!-- loop -->
		                              <tr>
		                                  <td class="tleft">
		                                      <div class="product-info">
		                                          <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
		                                          <div class="product-info-text">
		                                              <div class="product-info-box">
		                                                  <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡</a></p>
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
		                                  <td>2</td>
		                                  <td>398,000원</td>
		                                  <td class="selBox">
		                                  	<span>입금대기</span>
		                                  	<span><a href="javascript:;" class="btn gray sm">결제수단변경</a></span>
		                                  </td>
		                                  <td class="selBox"></td>
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 02 --> 
                         	
							<!--  order LIst 03 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>매장픽업주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610003004</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">
		                              <col style="width:80px">
		                              <col style="width:120px">
		                              <col style="width:120px">
		                              <col style="width:145px">                              
		                          </colgroup>	          
		                          <tbody>
		                              <!-- loop -->
		                              <tr>
		                                  <td class="tleft">
		                                      <div class="product-info">
		                                          <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd04.png" alt="상품이미지"></a></div>
		                                          <div class="product-info-text">
		                                              <div class="product-info-box">
		                                                  <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡뉴욕양키스 러버 와펜 볼캡뉴욕양키스 러버 와펜 볼캡</a></p>
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
		                                  <td>2</td>
		                                  <td>398,000원</td>
		                                  <td class="selBox">
		                                  	<span>배송완료</span>		                                  	
		                                  </td>
		                                  <td class="selBox">
		                                  	<span><a href="javascript:;" class="btn sm gray">배송추적</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">교환신청</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">반품신청</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">구매확정</a></span>
		                                  </td>
		                               </tr>
		                               <!-- //loop -->
									   <tr>
		                                  <td class="tleft">
	                                     	<div class="product-info">
                                                   <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
                                                   <div class="product-info-text">
                                                       <div class="product-info-box">
                                                           <p class="product-name"><a href="javascript:;">[MLB] [엑소트레이닝집업] 뉴욕양키스 스트라이프 배색 테잎배색 트레이닝 집업</a></p>
                                                           <div class="product-price">
                                                               <span>199,000원</span>
                                                           </div>
                                                       </div>
                                                       <div class="product-option">
                                                           <p>[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p>
                                                           <p>엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p>
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td>1</td>
		                                  <td>20,000원</td>
		                                  <td class="selBox">
		                                  	<span>배송완료</span>		                                  	
		                                  </td>
		                                  <td class="selBox">
		                                  	<span><a href="javascript:;" class="btn sm gray">배송추적</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">교환신청</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">반품신청</a></span>
		                                  	<span><a href="javascript:;" class="btn sm gray">구매확정</a></span>
		                                  </td>
		                               </tr>		                               
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 03 -->        
                         	
							<!--  order LIst 04 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>예약주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">
		                              <col style="width:80px">
		                              <col style="width:120px">
		                              <col style="width:120px">
		                              <col style="width:145px">                              
		                          </colgroup>	          
		                          <tbody>
		                              <!-- loop -->
		                              <tr>
		                                  <td class="tleft">
		                                      <div class="product-info">
		                                          <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
		                                          <div class="product-info-text">
		                                              <div class="product-info-box">
		                                                  <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡</a></p>
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
		                                  <td>2</td>
		                                  <td>398,000원</td>
		                                  <td class="selBox">
		                                  	<span>결제완료</span>		                                  	
		                                  </td>
		                                  <td class="selBox">
		                                  	<span><a href="javascript:;" class="btn gray sm">주문취소</a></span>
		                                  	<span><a href="javascript:;" class="btn gray sm">배송지변경</a></span>
		                                  </td>
		                               </tr>
		                               <!-- //loop -->		                   
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 04 -->                         	  
                         	
                         	<!--  NO Result -->
							<div class="odResulBox">
								<div class="orderNb noResult">
									주문내역이 없습니다.
								</div>	
							</div> 
							<!--  //NO Result -->             	
                         	                         	                   
                        </div>
                        <!--  //order Result List -->
                        
                        <!--  page List -->
						<%@ include file="../_inc/paging.jsp" %>
						<!-- // page List -->
						
					</div>		
					<!-- //order Result -->		
					
					<ul class="text-list02">
						<li>배송완료 후 구매확정을 하지 않은 경우에는 배송이 완료된 일로부터 7일 경과 후, 8일째 자동으로 구매확정 됩니다.</li> 
						<li>주문이 구매 확정되면 마일리지가 적립되며 해당상품은 반품/교환을 할 수 없습니다.</li>
						<li>주문의 취소/교환/반품 신청내역 및 결과는 [취소/교환/반품 조회] 메뉴를 이용해주세요.</li>
						<li>매장 배송상품은 일반배송상품과 별도로 매장에서 발송됩니다. 사은품은 주문 상품과 별도로 배송될 수 있습니다.</li>
						<li>상품을 이미 받았는데 "배송중" 이면, 수령확인을 하시면 배송완료 상태로 변경되며 클레임 신청을 할 수 있습니다.</li>
						<li>상품별 결제금액은 주문 시 총 결제금액과 다를 수 있으며, 주문번호를 클릭 하시면 해당 주문의 상세정보를 보실 수 있습니다.</li>
						<li>구매확정 후 리뷰 작성시 포토리뷰: 1,000원, 텍스트리뷰: 300원 포인트 증정 해드립니다.</li>
					</ul>
					
				</div>		
				<!--  //주문/배송조회   -->	
				

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