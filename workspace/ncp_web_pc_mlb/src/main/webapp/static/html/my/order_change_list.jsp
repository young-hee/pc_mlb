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

			<h2 class="title01">취소/교환/반품조회 목록</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><strong title="현재 위치">취소/교환/반품조회 목록</strong>
					</p>
				</div> 
				
				<!--  취소/교환/반품조회   -->
				<div class="orderInfoCon">
				
					<ul class="text-list02">
						<li>최근 1년까지의 취소/교환/반품 내역을 조회할 수 있습니다.</li>
						<li>**년 *월 **일 이전 내역을 확인하시려면 '이전 주문보기' 버튼을 클릭해주세요.</li>
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
									<!-- 2018.12.06 항목 수정 -->
									<ul>
										<li><a href="javascript:;">전체</a></li>
										<li><a href="javascript:;">주문취소</a></li>
										<li><a href="javascript:;">교환</a></li>								
										<li><a href="javascript:;">반품</a></li>										
									</ul>
									<!-- //2018.12.06 항목 수정 -->
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
							<a href="javascript:;" class="btn sm gray">교환/반품 절차안내</a>	
							<a href="javascript:;" class="btn sm fill">1:1 고객상담</a>						
						</div>
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst 01 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>주문취소</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> <a href="javascript:;">0D201810610002555</a></span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
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
		                                  <td class="selBox">
		                                  	<span>취소완료</span>
		                                  </td>
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  // order LIst 01 -->    
                         	
							<!--  order LIst 02 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>교환</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
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
		                                  <td class="selBox">
		                                  	<span>교환결제대기</span>
		                                  	<span><a href="javascript:;" class="btn sm gray">추가결제</a></span>		                                  	
		                                  </td>		                                 
		                               </tr>
		                               <!-- //loop -->
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 02 --> 
                         	
							<!--  order LIst 03 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>반품</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> <a href="javascript:;" class="nb">0D201810610003004</a></span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610003004</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
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
		                                  <td class="selBox">
		                                  	<span>반품완료</span>		                                  	
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
                                                           <p>[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p>
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td>1</td>
		                                 <td class="selBox">
		                                  	<span>반품완료</span>		                                  	
		                                  </td>
		                               </tr>		                               
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst 03 -->     
                         	
                         	<!--  order LIst 04 -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>주문취소</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
	                              	<span><em>주문번호</em> <a href="javascript:;" class="nb">0D201810610005094</a></span>
								</div>								
								<table class="board-list orderTable">
		                          <colgroup>
		                              <col style="width:">		                            
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
		                                  <td class="selBox">
		                                  	<span>취소완료</span>		                                  			                                  	
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
									취소/교환/반품 내역이 없습니다.
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
				<!--  //취소/교환/반품조회   -->	
				

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