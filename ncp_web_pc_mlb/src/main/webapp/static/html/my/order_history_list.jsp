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

			<h2 class="title01">이전주문 목록</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><strong title="현재 위치">이전주문 목록</strong>
					</p>
				</div> 
				
				<!--  이전주문 목록   -->
				<div class="orderInfoCon orderHistory">
					
					<!-- order Result -->
					<div class="odSearchResult">	
									
						<div class="odResultTop">
							<strong class="listTotal">주문내역(<em class="fc_red">5</em>건)</strong>							
							<a href="javascript:;" class="btn sm fill">1:1 고객상담</a>						
						</div>
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst 01 -->
							<div class="odResulBox">
								<div class="orderNb">									
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>	                              	
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
		                              <col style="width:90px">
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
		                                  <td>9,999,999원</td>
		                                  <td>결제완료</td>
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  // order LIst 01 -->    
                         	
							<!--  order LIst 02 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span><em>주문일</em> 2018.10.31 16:09</span>	                              	
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
		                              <col style="width:90px">
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
		                                  <td>1</td>		                          
		                                  <td>100,999원</td>
		                                  <td>배송완료</td>	                                 
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 02 --> 
                         	
							<!--  order LIst 03 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span><em>주문일</em> 2018.10.31 16:09</span>	                              	
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
		                              <col style="width:90px">
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
		                                  <td>1</td>
		                                  <td>70,999원</td>
		                                  <td>반품완료</td>                   
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
                                                           <p>[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p>
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td>3</td>		                          
		                                  <td>200,400원</td>
		                                  <td>배송완료</td>
		                               </tr>		                               
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 03 -->     
                         	
                         	<!--  order LIst 04 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span><em>주문일</em> 2018.10.31 16:09</span>	                              	
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
		                              <col style="width:90px">
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
		                                  <td>1</td>		                          
		                                  <td>345,400원</td>
		                                  <td>배송완료</td>	                                 
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 04 -->      
                         	
                         	<!--  NO Result -->
							<div class="odResulBox">
								<div class="orderNb noResult">
									이전주문 내역이 없습니다.
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
					
				
					
				</div>		
				<!--  //이전주문 목록   -->	
				

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