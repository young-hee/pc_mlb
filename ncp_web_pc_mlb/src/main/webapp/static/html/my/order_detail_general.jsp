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

			<h2 class="title01">일반주문 상세</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>주문/배송조회 목록</span><strong title="현재 위치">일반주문 상세</strong>
					</p>
				</div> 
				
				<!-- 일반주문 상세  -->
				<div class="orderInfoCon">
					<div class="odSearchResult odPdModify odDetail">			
					
						<!-- 주문번호, 상품 리스트 -->
						<div class="odResulCon">	
							<div class="odResulBox">							
						
								<!-- 주문정보 -->
								<div class="orderNb">
									<span>일반주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>	                              
								</div>	
								<!-- //주문정보 -->
								<div class="btnOdTop">
	                              	<a href="javascript;" class="btn sm gray">전체취소</a>
	                              	<a href="javascript;" class="btn sm fill">1:1문의</a>
                              	</div>	
								<!-- 배송지 -->
								<div class="orderAdd odAddBox">									
									<!-- <strong class="odAddTit">[배송지]</strong> 2018.12.06 삭제-->										
									<dl>
										<dt>받는분</dt>
										<dd>홍길동</dd>
									</dl>
									<dl>
										<dt>주소</dt>
										<dd>서울특별시 강남구 도곡로 117 (역삼동, 옥신타워) 12층 (주)플그림 Inerative UX팀</dd>
									</dl>
									<dl>
										<dt>연락처</dt>
										<dd>010-799-9201</dd>
									</dl>	
									<dl>
										<dt>배송메시지</dt>
										<dd>지하계단 오른쪽 박스에 놔주세요. 감사합니다.</dd>
									</dl>	
									<a href="javascript;" class="btn sm gray btnAddModi">배송지 변경</a>				
								</div>	
								<!-- //배송지 -->		
																
								<!-- 주문상품 리스트  -->
								<table class="board-list orderTable">
		                          <colgroup>
		                                <col>
                                        <col style="width:80px">
                                        <col style="width:114px">
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
		                                  <td>394,000원</td>
		                                  <td>결제완료</td>
		                                  <td><a href="javascript" class="btn gray sm">주문취소</a></td>
		                               </tr>
		                               <!-- //loop -->
		                               <tr>	                                	 
		                                  <td class="tleft">
		                                     <div class="product-info product-free-gift">
                                                   <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                                   <div class="product-info-text">
                                                       <div class="product-info-box">
                                                           <div class="product-more-info"><span class="text-color01">[상품사은품]</span></div>
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
		                                  <td></td>
		                                  <td></td>
		                               </tr>                              
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
		                                  <td>394,000원</td>
		                                  <td>결제완료</td>
		                                  <td><a href="javascript" class="btn gray sm">주문취소</a></td>
		                               </tr>		                               
		                            </tbody>
		                         </table>		                        
		                         <!-- //주문상품 리스트  -->
		                         
                         	</div>	                         	                 
                        </div>
                        <!--  // 주문번호, 상품 리스트 -->    
                        
                        
                        <!--  결제 정보 -->
                        <div class="mgInfoBox">
                        	<h3> 결제 정보</h3>
                        	<table class="tbTotalList">
                        		<caption>결제 정보</caption>
		                        <colgroup>
		                             <col style="width:20%">
                                     <col style="width:">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>
		                          	<tr>
		                          		<th>상품주문가격</th>
		                          		<td  colspan="2">801,000원</td>		                          		
		                          	</tr>
									<tr class="selLineBoxBt">
		                          		<th>배송비</th>
		                          		<td  colspan="2">0원</td>		                          		
		                          	</tr>		                          	
		                          	<tr class="selMgT">
		                          		<th>주문할인</th>
		                          		<td  colspan="2">20,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>쿠폰할인</th>
		                          		<td  colspan="2">10,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>사용 마일리지</th>
		                          		<td  colspan="2">5,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>사용 포인트</th>
		                          		<td colspan="2">7,500원</td>		                          		
		                          	</tr>		                          			                          	
		                        </tbody>
		                        <tfoot>
		                        	<tr class="selLineBoxBt">
		                          		<th>결제금액</th>
		                          		<td>
		                          			<span class="selTxtMgL">실시간 계좌이체</span>
		                          			<span class="selTxtMgL">국민은행(홍길동)</span>
		                          			<span class="selTxtMgL">123045245874</span>	 
		                          		</td>
		                          		<td><strong>10,000</strong>원</td>
		                          	</tr>		                          		
		                          	<tr>
		                          		<td colspan="2"><span class="selTxtMgL">적립예정 마일리지</span></td>
		                          		<td class="fc_red">33,990원</td>
		                          	</tr>
		                        </tfoot>
	                        </table>                        
                        </div>
                        <!--  //결제 정보 -->
                        
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
		                          	<tr>
		                          		<th>포인트 환급</th>
		                          		<td colspan="2">7,500원</td>		                          		
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
				<!--  // 일반주문 상세   -->	
				

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