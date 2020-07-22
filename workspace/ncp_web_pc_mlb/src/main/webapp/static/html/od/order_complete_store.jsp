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
	                    		<strong>매장 수령 주문이 완료되었습니다.</strong>
	                    		<p><em class="fc_red">[상품준비완료] 안내문자(알림톡)를 수신하신 후 방문하시거나, 해당 매장과 통화하신 후 방문하시기 바랍니다.</em><br>주문상세 내역 및 배송관련 정보는 마이페이지 > 주문정보 > 주문/배송 조회에서 확인하실 수 있습니다.</p>
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
	                                    <col style="width:225px"> 
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
	                                    	<td class="fs_r">10,000원</td> <!-- 2018.11.30 text 수정 -->
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
	                                    	<th class="titSel">픽업매장안내</th><!-- 2018.12.18 text 수정 -->
	                                     	<td class="storeInfoSel">	                                           		
	                                   			<p class="StoreInfo">
                                                 	<strong class="name">홍대 직영점</strong>
                                                 	<a href="#lyPopfindStore" class="btnMap d_layer_open"><img src="/static/images/od/icon_location.png" alt="지도보기"></a><br>
                                                 	서울특별시 마포구 와우산로 90(서교동)  <em>/</em>  02-322-9035<br> 
                                                 	영업시간  <em>|</em>  11:30 ~ 22:00
                                               	</p>
	                                   			<p class="checkMg">매장픽업 주문 상품이 준비완료 되었다는 알림(알림톡 또는 SMS) 수신 후, 3일(영업일 기준)까지 <br>수정하지 않으면, 주문이 자동 취소 됩니다.</p> <!-- 2018.11.30 text 수정 -->
	                                     	</td>
	                                    </tr> 
	                                 </tbody>
	                             </table>
	                      	 </div>    
	                      	 <!-- //order list 02 -->    
	                      	          
	                      	 <ul class="text-list02 mt40">
	                      	 	<!-- <li>[상품주문완료] 안내문자(알림톡)를 수신하신 후 방문하시거나, 해당 매장과 통화하신 후 방문하시기 바랍니다.</li> 2018.11.30 삭제 -->
	                            <li>상품 수령 시, [상품준비완료]문자 모바일 MLB의 회원 바코드를 반드시 지참해야 합니다.</li>
	                            <li>16시 이후 결제되는 매장픽업 주문은 당일 수령 할 수 없습니다.</li>
	                            <li>매장픽업 주문 시, [상품준비완료] 안내문자(알림톡)를 수신하신 3일(영업일 기준) 이내로 상품을 수령하지 않을 경우 주문은 자동으로 취소됩니다.</li> <!-- 2018.11.30 text 수정 -->
	                      	 </ul> 
	                      	 
	                      	  <!-- 2019.02.01 버튼추가(개발에서 추가된부분) -->    
	                      	 <div class="btn-wrap">
		                        <a href="/" class="btn-style03">쇼핑 계속하기</a>
		                        <a href="/static/html/od/order_complete_store.jsp" class="btn-style02">주문내역 상세보기</a>
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