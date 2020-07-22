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
	<div class="contain od list" id="contain">
		<div class="container">
            <div class="location-container">
                <div class="location-contents">
                    <h2 class="title01">주문완료</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">주문완료</strong>
                    </p>
                 </div>
            </div>

			<main class="contents" id="contents">
				<section>
				
                    <ul class="stepInfoBox">
                        <li class="on">장바구니</li>
                        <li class="on">주문서</li>
                        <li class="on">주문완료</li>
                    </ul>
                    
                    <!-- order Contents : 주문완료 -->
                    <div class="orderContents orderComplete">                              
                         <div class="orderComCont">
                         
                         	<div class="orderTxtBox">
                         		<strong>주문이 예약 완료되었습니다.</strong>
                         		<p>성공적으로 주문 예약 되었습니다. 상품 주문내역과 결제금액을 확인하세요.<br>주문상세 내역 및 배송관련 정보는 마이페이지 > 주문정보 > 주문/배송 조회에서 확인하실 수 있습니다.</p>
                         	</div>
                         	
                         	<div class="orderNumBox">
                         		주문번호 : OD15211910877186
                         		<a href="javascript:;" class="btn sm">주문상세내역</a>
                         	</div>
                           
                             <!-- order list 01 -->
                             <div class="orderInfoTable">
                                 <table class="board-write">
                                    <!-- 2018.12.28 간격수정 -->
	                                <colgroup>
	                                    <col style="width:170px">
	                                    <col style="width:130px">  
	                                    <col style="width:230px"> 
	                                    <col style="width:">                                              
	                                </colgroup>
	                                <!-- //2018.12.28 간격수정 -->
                     
                                     <tbody>                                                
                                         <tr>
                                         	<th class="titSel">주문일시</th>
                                          	<td colspan="3">2018년 10월 24일 15:00</td>
                                         </tr>
                                         <tr  class="lineB">
                                         	<th class="titSel tMg0">주문내역</th>
                                         	<td colspan="3" class="tMg0">LA 다저스 메가로고 롱패딩 외 1건</td>
                                         </tr>
                                         <tr>
                                         	<th rowspan="6" class="titSel lineB">결제내역</th>
                                         	<th>상품가격</th>
                                         	<td colspan="2" class="fs_r">428,000원</td>
                                         </tr>
										 <tr>                                            
                                         	<th class="tMg0">할인적용</th>
                                         	<td colspan="2" class="fs_r fc_red tMg0">-42,000원</td>
                                         </tr>
										 <tr>
                                         	<th class="lineB tMg0">배송비</th>
                                         	<td class="lineB fs_r tMg0" colspan="2">0원</td>
                                         </tr> 
                                         <tr>
                                         	<th rowspan="3">총 결제금액</th> <!-- 2019.02.01 lineB class 삭제 -->
                                         	<th class="fc_gray">마일리지</th>
                                         	<td class="fs_r">10,000원</td><!-- 2018.11.30 text 수정 -->
                                         </tr>   
										 <tr>
										 	<th class="tMg0 fc_gray">포인트</th>
                                         	<td class="fs_r tMg0">13,000원</td> <!-- 2018.11.30 text 수정 -->
                                         </tr>  
                                        <tr><!-- 2019.02.01 lineB class 삭제 --> 
										 	<th class="tMg0 fc_gray">신용카드</th>
                                         	<td class="fs_r tMg0 totalSel"><strong>365,200</strong>원</td>
                                         </tr>                                             
                                         <tr class="lineT"> <!-- 2019.02.01 lineT class 추가 -->
                                         	<th class="titSel">적립예정 마일리지</th>
                                          	<td colspan="3" class="fs_r fc_red">18,260원</td><!-- 2018.11.30 text 수정 -->
                                         </tr>  
                                     </tbody>                     
                                 </table>
                             </div>
                             <!-- // order list 01 -->
                             
                             <!-- order list 02 -->                                
							<div class="orderInfoTable">
                                 <table class="board-write">
                                     <colgroup>
                                         <col style="width:170px">
                                         <col style="width:">                       
                                     </colgroup>
                     
                                     <tbody>                                                
                                         <tr>
                                         	<th class="titSel">배송지정보</th>
                                          	<td class="addrInfoTxt">
                                         		<p>박지영  010-1234-3333</p> <!-- 2018.11.30 수정 -->
												<p>(12345)<br>서울특별시 강남구 테헤란로 20길 9, 동궁빌딩 6층</p>
												<p>부재시, 경비실에 맡겨주세요.</p>
                                          	</td>
                                         </tr> 
                                      </tbody>
                                  </table>
                           	 </div>    
                           	 <!-- //order list 02 -->      
                           	        
                           	 <ul class="text-list02 mt40">
                                 <li>예약상품은 발매 시 결제순서대로 출고됩니다.</li>
                                  <li>예약상품의 배송이 시작되면 별도 알림(e-Mail, 알림톡)을 보내드립니다.</li>
                                 <li>예약상품은 사정에 의하여 발매 연기 및 취소가 될 수 있으며 상품 입고 시기에 따라 출고가 당겨지거나 연기될 수 있습니다.</li>
                                  <!--  무통장 입금일때 보여줌 2018.11.30 수정 -->
                                 <li>입금 마감일시까지 입금되지 않은 주문은 자동 취소됩니다.</li>
                                 <li>무통장입금(가상계좌)전에 MY PAGE 주문내역에서 신용/체크카드, 네이버페이, 실시간 계좌이체로 변경하실 수 있습니다.</li>
                                 <li>입금 시 예금주 명은'(주)에프에프' 입니다.</li>
                                  <!--  //무통장 입금일때 보여줌 2018.11.30 수정 -->
                           	 </ul>
                           	 
                           	  <!-- 2019.02.01 버튼추가(개발에서 추가된부분) -->    
	                      	 <div class="btn-wrap">
		                        <a href="/" class="btn-style03">쇼핑 계속하기</a>
		                        <a href="/static/html/od/order_complete_reserve.jsp" class="btn-style02">주문내역 상세보기</a>
	                        </div>       
                           	   
                         </div>   
                   </div>
                   <!-- //order Contents : 주문완료 -->
                
				</section>
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