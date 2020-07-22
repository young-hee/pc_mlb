<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopCuponSch" class="layer-popup lypopCuponSch">
	<section class="layer-popup-cont" tabindex="0" style="width:530px">
		<h2>쿠폰선택</h2>
		<div class="layer-cont ly-box scroll"> <!-- 스크롤 필요시 class=scroll 추가 -->		
			
			<!--  쿠폰 등록 버튼  -->			
			<div class="btnCupon">
				<a href="#javascript" class="btn sm btnAddCupon">쿠폰등록</a>
			</div>
			
			<!--  상품 할인 쿠폰  -->
			<div class="CuponArea mgT0"> <!-- 2018.11.30 mgT0 class 수정  --> 		
		 		<h3 class="title06">상품 할인 쿠폰</h3> 	
				<table class="board-list">	                                                  
	                <tbody>  
	               		<!-- loop -->                       
	                    <tr>
	                        <td class="tleft">
	                            <div class="product-info">
	                                <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
	                                <div class="product-info-text">
	                                   <div class="product-info-box">
	                                        <!-- <div class="product-more-info"><span class="text-color01">[예약배송]</span></div>--><!-- 2018.11.30 삭제 -->
	                                        <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡 뉴욕양키스 러버 와펜 볼캡</a></p>
	                                        <div class="product-price">
	                                            <span>199,000원</span>
	                                        </div>
	                                    </div>
	                                    <div class="product-option"><span>BL / 90</span></div>
	                                </div>
	                            </div>
	                           <!-- select -->
								<div class="select-style01 d_select selSm selmt20">
									<button type="button" class="d_select_sel" style="width:360px;"><span>하이넥 다운베스트 50% 상품쿠폰</span></button><!-- 2018.11.30 style 수정 -->
									<ul>
										<li><a href="javascript:;">하이넥 다운베스트 50% 상품쿠폰</a></li>										
									</ul>
								</div>
								<!-- //select -->
								<p class="txtTotal pdCupon"><strong>999,139,700</strong>원</p>
	                        </td>	                      
	                     </tr>
	                     <!-- //loop -->  
						<tr>
	                        <td class="tleft">
	                            <div class="product-info">
	                                <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
	                                <div class="product-info-text">
	                                   <div class="product-info-box">
	                                        <!-- <div class="product-more-info"><span class="text-color01">[예약배송]</span></div>--><!-- 2018.11.30 삭제 -->
	                                        <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡</a></p>
	                                        <div class="product-price">
	                                            <span>199,000원</span>
	                                        </div>
	                                    </div>
	                                    <div class="product-option"><span>BL / 90</span></div>
	                                </div>
	                            </div>
	                           <!-- select -->
								<div class="select-style01 d_select selSm selmt20">
									<button type="button" class="d_select_sel" style="width:360px;"><span>하이넥 다운베스트 50% 상품쿠폰</span></button><!-- 2018.11.30 style 수정 -->
									<ul>
										<li><a href="javascript:;">하이넥 다운베스트 50% 상품쿠폰</a></li>										
									</ul>
								</div>
								<!-- //select -->
								<p class="txtTotal pdCupon"><strong>139,700</strong>원</p>
	                        </td>	                      
	                     </tr>	                     
	                  </tbody>
	              </table>
           	</div>
           	<!--  //상품 할인 쿠폰  -->
           	
			<!--  장바구니 할인 쿠폰  -->
			<div class="CuponArea">		
		 		<h3 class="title06">장바구니 할인 쿠폰</h3>
				<table class="board-list">
	                <colgroup>
	                    <col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>                                      
	                <tbody>                   
	                    <tr>    
	                    	<td class="tleft">
	                    	 <!-- select -->
								<div class="select-style01 d_select selSm">
									<button type="button" class="d_select_sel" style="width:360px;"><span>쿠폰선택</span></button><!-- 2018.11.30 style 수정 -->
									<ul>
										<li><a href="javascript:;">쿠폰선택1</a></li>										
										<li><a href="javascript:;">쿠폰선택2</a></li>
										<li><a href="javascript:;">쿠폰선택3</a></li>
										<li><a href="javascript:;">쿠폰선택4</a></li>
									</ul>
								</div>
								<!-- //select -->
	                    	</td>
	                    	<td class="txtTotal"><strong>139,700</strong>원</td>
                    	</tr>
                   	</tbody>
               	</table>
           	</div>
           	<!--  //장바구니 할인 쿠폰  -->
           	
			<!--  배송비 무료 쿠폰  2019.01.15 삭제됨
			<div class="CuponArea">		
		 		<h3 class="title06">배송비 무료 쿠폰</h3>
				<table class="board-list">
	                <colgroup>
	                    <col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>                                      
	                <tbody>              
	                    <tr>    
	                    	<td class="tleft">	                    	
								<div class="select-style01 d_select selSm">
									<button type="button" class="d_select_sel" style="width:360px;"><span>쿠폰선택</span></button>
									<ul>
										<li><a href="javascript:;">쿠폰선택1</a></li>										
										<li><a href="javascript:;">쿠폰선택2</a></li>
										<li><a href="javascript:;">쿠폰선택3</a></li>
										<li><a href="javascript:;">쿠폰선택4</a></li>
									</ul>
								</div>
	                    	</td>
	                    	<td class="txtTotal"><strong>0</strong>원</td>
                    	</tr>
                   	</tbody>
               	</table>
           	</div>
           	  //배송비 무료 쿠폰  -->   
           	
			<!-- 총 할인적용예정금액   -->
			<div class="CuponArea">
				<table class="board-list total-list">
	                <colgroup>
	                    <col style="width:380px">
	                    <col style="width:">                                               
	                </colgroup>                                      
	                <tbody>                
	                    <tr>    
	                    	<th>상품쿠폰 적용 금액</th>
	                    	<td class="txtTotal">0원</td>
                    	</tr>
                    	<tr>    
	                    	<th>장바구니 쿠폰 적용 금액</th>
	                    	<td class="txtTotal">0원</td>
                    	</tr>
                    	<tr>    
	                    	<th>배송비 무료쿠폰</th>
	                    	<td class="txtTotal">무료</td><!-- 2019.01.15 수정-->
                    	</tr> 
                   	</tbody>
                   	<tfoot>
                   		<th>총 할인적용 예정금액</th>
                   		<td class="txtTotal"><strong>183,350</strong>원</td>
                   	</tfoot>
               	</table>
           	</div>
           	<!-- //총 할인적용예정금액   -->   
           	
           	<div class="lyBtnArea">
           		<a href="javascript:;" class="btn fill">쿠폰 할인 적용하기</a>
           	</div>      	       	
         
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopCuponSch'); 
});
</script>
</body>
</html>