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

			<h2 class="title01">상품교환 상세</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>취소/교환/반품조회 목록</span><strong title="현재 위치">상품교환 상세</strong>
					</p>
				</div> 
				
				<!-- 상품교환 상세 -->
				<div class="orderInfoCon">
					<div class="odSearchResult odPdModify odDetail">			
					
						<!-- 주문번호, 상품 리스트 -->
						<div class="odResulCon">	
							<div class="odResulBox">							
						
								<!-- 주문정보 -->
								<div class="orderNb lineBox">
									<span>교환</span>
	                              	<span><em>신청일</em> 2018.10.31 16:09</span>
	                              	<span><em>클레임번호</em> 0D201810610002555</span>	                              
	                              	<span><em>주문번호</em><a href="javascript:;">0D201810610002555</a></span>
								</div>	
								<!-- //주문정보 -->
								<div class="btnOdTop">	                              	
	                              	<a href="javascript;" class="btn sm fill">1:1문의</a>
                              	</div>
										
								<!--  교환대상 상품정보 -->
								<div class="mgInfoBox">
                        			<h3>교환대상 상품정보</h3>						
									<!-- 주문상품 리스트  -->
									<table class="board-list orderTable orderMsg">
			                          <colgroup>
			                          		<col style="width:140px">
			                                <col>                                        
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:146px">
			                          </colgroup>	   
			                         <tbody>
			                              <!-- loop -->
			                              <tr>		                             	 
			                                  <td colspan="2" class="tleft">
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
			                                  <td>수거완료</td>
			                                  <td>
			                                  	<a href="javascript;" class="btn sm gray">배송추적</a>
			                                  </td>	
			                               </tr>
			                               <tr class="seldMsg">
			                               		<th>교환사유</th>
			                               		<td colspan="4">옵션변경(단순변심) / 색상이 모니터에서 보던 것과 많이 달라요.</td>
											</tr>																				
			                               <!-- //loop -->                   
			                            </tbody>
			                         </table>		                        
			                         <!-- //주문상품 리스트  -->
			                         <!--  수거지 -->
			                         <div class="orderAdd odAddBox">									
										<!-- <strong class="odAddTit">[수거지]</strong> 2018.12.06 삭제 -->										
										<dl>
											<dt>신청자</dt><!-- 2018.12.06 수정 -->
											<dd>홍길동</dd>
										</dl>
										<dl>
											<dt>수거지</dt><!-- 2018.12.06 수정 -->
											<dd>서울특별시 강남구 도곡로 117 (역삼동, 옥신타워) 12층 (주)플그림 Inerative UX팀</dd>
										</dl>
										<dl>
											<dt>연락처</dt>
											<dd>010-799-9201</dd>
										</dl>	
										<!-- <dl>
											<dt>배송메시지</dt>
											<dd>지하계단 오른쪽 박스에 놔주세요. 감사합니다.</dd>
										</dl> 2018.12.06 삭제 -->													
									</div>		
									<!-- // 수거지 -->	
								</div>
								<!-- // 교환대상 상품정보 -->
								
								<!--  교환배송 상품정보 -->
								<div class="mgInfoBox">
		                      		<h3>교환배송 상품정보</h3>						
									<!-- 주문상품 리스트  -->
									<table class="board-list orderTable orderMsg">
			                          <colgroup>			                          		
			                               <col>                                        
	                                       <col style="width:120px">
	                                       <col style="width:120px">
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
			                                  <td  class="selBox">
			                                  	<span>배송완료</span>
			                                  	<span><a href="javascript:;" class="btn sm gray">배송추적</a></span>
		                                  	  </td>
			                                  <td class="selBox">
			                                  	<span><a href="javascript:;" class="btn sm gray">구매확정</a></span>
			                                  	<span><a href="javascript:;" class="btn sm gray">교환신청</a></span>
			                                  	<span><a href="javascript:;" class="btn sm gray">반품신청</a></span>
			                                  </td>	
			                               </tr>	
			                               <tr class="seldMsg2">
			                               		<td colspan="4"><span class="tt">교환사유</span> <span class="dd"> 옵션변경(단순변심) / 색상이 모니터에서 보던 것과 많이 달라요.</span></td>
											</tr>			                              																			
			                               <!-- //loop -->                   
			                            </tbody>
			                         </table>		                        
			                         <!-- //주문상품 리스트  -->
			                         <!--  배송지 -->
			                         <div class="orderAdd odAddBox">									
										<!-- <strong class="odAddTit">[배송지]</strong> 2018.12.06 삭제 -->										
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
									</div>		
									<!-- // 배송지 -->	
		                        </div>
		                        <!--  //교환배송 상품정보 -->
								
                         	</div>	                         	                 
                        </div>
                        <!--  // 주문번호, 상품 리스트 -->                          
                         
                        
						<!--  교환배송비 -->
                        <div class="mgInfoBox">
                        	<h3>교환배송비</h3>
                        	<table class="tbTotalList">
                        		<caption>취소 정보</caption>
		                        <colgroup>
		                             <col style="width:20%">
                                     <col style="width:">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>
		                          	<tr>
		                          		<th>교환왕복배송</th>
		                          		<td  colspan="2">5,000원</td>		                          		
		                          	</tr>                           			                          	
		                        </tbody>
		                        <tfoot>
									<tr>
		                          		<th>결제금액</th>
		                          		<td colspan="2"><strong>999,999,999</strong>원</td>
		                          	</tr>			                        
		                        	<tr class="selBg">
		                          		<th>결제정보</th>
		                          		<td>
		                          			<span class="selTxtMgL">실시간 계좌이체</span>
		                          			<span class="selTxtMgL">국민은행(홍길동)</span>
		                          			<span class="selTxtMgL">123045245874</span>	 
		                          		</td>
		                          		<td>2018.10.30</td>
		                          	</tr>		                          		
		                          	<tr class="selBgline">
		                          		<th>진행상태</th>
		                          		<td colspan="2">취소완료 (2018. 11. 03  17:57)</td>
		                          	</tr>
		                        </tfoot>
	                        </table>                        
                        </div>
                        <!--  //교환배송비 -->  
                        
                        <div class="btnWrapBox">					
							<a href="javascript:;" class="btn fill">확인</a>
						</div>
						
					</div>		
				</div>		
				<!--  // 상품교환 상세  -->	
				

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