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

			<h2 class="title01">주문취소 상세</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>취소/교환/반품조회 목록</span><strong title="현재 위치">주문취소 상세</strong>
					</p>
				</div> 
				
				<!-- 주문취소 상세  -->
				<div class="orderInfoCon">
					<div class="odSearchResult odPdModify odDetail">			
					
						<!-- 주문번호, 상품 리스트 -->
						<div class="odResulCon">	
							<div class="odResulBox">							
						
								<!-- 주문정보 -->
								<div class="orderNb">
									<span>주문취소</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> <a href="javascript:;">0D201810610002555</a></span><!-- 2018.12.06 추가 -->
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>	                              
								</div>	
								<!-- //주문정보 -->
								<div class="btnOdTop">
	                              	<!--<a href="javascript;" class="btn sm gray">전체취소</a> -->
	                              	<a href="javascript;" class="btn sm fill">1:1문의</a>
                              	</div>					
								<!-- 주문상품 리스트  -->
								<table class="board-list orderTable orderMsg">
		                          <colgroup>
		                                <col>                                      
                                        <col style="width:90px">
                                        <col style="width:146px">
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
		                                  <td>취소완료</td>		                                  
		                               </tr>
		                               <!-- //loop -->                    
		                            </tbody>
		                         </table>		                        
		                         <!-- //주문상품 리스트  -->
		                         
                         	</div>	                         	                 
                        </div>
                        <!--  // 주문번호, 상품 리스트 -->    
                        
						<!--  취소 정보 -->
                        <div class="mgInfoBox">
                        	<h3> 취소 정보</h3>
                        	<table class="tbTotalList">
                        		<caption>취소 정보</caption>
		                        <colgroup>
		                             <col style="width:20%">
                                     <col style="width:">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>
		                          	<tr>
		                          		<th>상품주문금액</th>
		                          		<td  colspan="2">801,000원</td>		                          		
		                          	</tr>             	
		                          	<tr>
		                          		<th>주문할인</th>
		                          		<td  colspan="2">20,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>쿠폰할인</th>
		                          		<td  colspan="2">10,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>마일리지 환급</th>
		                          		<td  colspan="2">5,000원</td>		                          		
		                          	</tr>
		                          	<tr class="selmgB">
		                          		<th>포인트 환급</th>
		                          		<td colspan="2">7,500원</td>		                          		
		                          	</tr>	
		                          	<tr class="selLineBox">
		                          		<th>배송비</th>
		                          		<td colspan="2">0원</td>		                          		
		                          	</tr>
		                        </tbody>
		                        <tfoot>
		                        	<tr>
		                          		<th>환불금액</th>
		                          		<td>
		                          			<span class="selTxtMgL">실시간 계좌이체</span>
		                          			<span class="selTxtMgL">국민은행(홍길동)</span>
		                          			<span class="selTxtMgL">123045245874</span>	 
		                          		</td>
		                          		<td><strong>999,999,999</strong>원</td>
		                          	</tr>		                          		
		                          	<tr class="selBg">
		                          		<th>진행상태</th>
		                          		<td colspan="2">취소완료 (2018. 11. 03  17:57)</td>
		                          	</tr>
		                        </tfoot>
	                        </table>                        
                        </div>
                        <!--  //취소 정보 -->  
                        
                        <div class="btnWrapBox">					
							<a href="javascript:;" class="btn fill">확인</a>
						</div>
						
					</div>		
				</div>		
				<!--  // 주문취소 상세   -->	

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