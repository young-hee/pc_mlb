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

			<h2 class="title01">증빙서류 조회</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><strong title="현재 위치">증빙서류 조회</strong>
					</p>
				</div> 
				
				<!--  증빙서류 조회   -->
				<div class="orderInfoCon orderDc">
					<div class="odSearchOptBox mgt0Box">						
						<dl class="period-wrap period-type02">							
							<dt>조회기간</dt>
							<dd>
								<a href="javascript:;" class="btn sm d_radio_select on"><span>3개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select"><span>6개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select"><span>12개월</span></a>
								<input type="text" class="calendar" id="calendarFrom"><input type="text" class="calendar" id="calendarTo">
								<a href="javascript:;" class="btn sm">검색</a>								
							</dd>
						</dl>						
						 <!--%@ include file="../_inc/uiDateRange2.jsp" %--> 
					</div>
					
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
								<table class="board-list">
								  <caption>증빙서류 조회 테이블</caption>
		                          <colgroup>
		                              <col style="width:130px">		                            
		                              <col style="width:">
		                              <col style="width:136px">
		                              <col style="width:150px">  
		                              <col style="width:136px">  
		                              <col style="width:146px">                                
		                          </colgroup>	
		                          <thead> 
		                          	<th>주문/결제일</th>
		                          	<th>주문/클레임 번호</th>
		                          	<th>거래구분</th>
		                          	<th>결제수단</th>
		                          	<th>결제상태</th>
		                          	<th>결제/취소 금액</th>
		                          	<th>영수증출력</th>   
		                          </thead>      
		                          <tbody>
		                              <!-- loop -->
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>추가배송비</td>
		                              	<td>신용카드</td>
		                              	<td>결제완료</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">카드전표</a></td>		                                  
		                              </tr>
		                              <!-- //loop -->
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주문</td>
		                              	<td>네이버페이</td>
		                              	<td>전체취소</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">네이버증빙</a></td>		                                  
		                              </tr>
 									  <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주분</td>
		                              	<td>실시간 계좌이체</td>
		                              	<td>부분취소</td>
		                              	<td>589,111원</td>
		                              	<td><a href="#" class="btn sm gray">거래명세서</a></td>		                                  
		                              </tr>	
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>추가배송비</td>
		                              	<td>신용카드</td>
		                              	<td>결제완료</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">카드전표</a></td>		                                  
		                              </tr>
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주문</td>
		                              	<td>네이버페이</td>
		                              	<td>전체취소</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">네이버증빙</a></td>		                                  
		                              </tr>
 									  <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주분</td>
		                              	<td>실시간 계좌이체</td>
		                              	<td>부분취소</td>
		                              	<td>589,111원</td>
		                              	<td><a href="#" class="btn sm gray">거래명세서</a></td>		                                  
		                              </tr>
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>추가배송비</td>
		                              	<td>신용카드</td>
		                              	<td>결제완료</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">카드전표</a></td>		                                  
		                              </tr>		                              
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주문</td>
		                              	<td>네이버페이</td>
		                              	<td>전체취소</td>
		                              	<td>1,589,111원</td>
		                              	<td><a href="#" class="btn sm gray">네이버증빙</a></td>		                                  
		                              </tr>
 									  <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주분</td>
		                              	<td>실시간 계좌이체</td>
		                              	<td>부분취소</td>
		                              	<td>589,111원</td>
		                              	<td><a href="#" class="btn sm gray">거래명세서</a></td>		                                  
		                              </tr>
		                              <tr>
		                              	<td>2017.08.08</td>
		                              	<td>0D201810610002555</td>
		                              	<td>상품주분</td>
		                              	<td>실시간 계좌이체</td>
		                              	<td>부분취소</td>
		                              	<td>589,111원</td>
		                              	<td><a href="#" class="btn sm gray">거래명세서</a></td>		                                  
		                              </tr>				                              
		                          </tbody>
		                       </table>	
                         	</div>	 
                         	<!--  // order LIst 01 -->    
                         	
                         	<!--  NO Result -->
							<div class="odResulBox" style="display:none">
								<div class="orderNb noResult">
									증빙서류 내역이 없습니다.
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
						<li>구매확정 이후 현금영수증 발행 정보를 전달하므로 국세청 사이트에서는 즉시 확인이 되지 않을 수 있습니다.</li>
						<li>증빙서 류 발급은 "구매확정" 완료된 건에 대해 조회 및 인쇄가 가능합니다.</li>
						<li>휴대전화 결제금액은 증빙서류 발급에서 제외됩니다.(현금영수증은 휴대전화  요금을 현금 납부하는 경우에만 해당 이동통신사에서 발급합니다.)</li>
					</ul>	
					
				</div>		
				<!--  // 증빙서류 조회   -->	
				

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