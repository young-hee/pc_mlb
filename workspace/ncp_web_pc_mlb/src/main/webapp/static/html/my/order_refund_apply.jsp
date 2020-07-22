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

			<h2 class="title01">반품 신청</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>주문/배송조회 목록</span><strong title="현재 위치">반품신청</strong>
					</p>
				</div> 
				
				<!-- 취소상품 선택  -->
				<div class="orderInfoCon">			
					
					
					<!-- order Result -->
					<div class="odSearchResult odPdModify">				
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst  -->
							<div class="odResulBox">							
						
								<!-- 주문정보 -->
								<div class="orderNb">
									<span>매장픽업주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>	
								<!-- //주문정보 -->	
								<!-- 배송지 -->
								<div class="orderAdd odAddBox">									
									<dl>
										<dt><strong>[수령매장]</strong></dt>										
										<dd class="StoreInfo">
                                           	<strong class="name">홍대 직영점</strong>
                                           	<a href="#lyPopfindStore" class="btnMap d_layer_open"><img src="/static/images/od/icon_location.png" alt="지도보기"></a><br>
                                           	서울특별시 마포구 와우산로 90(서교동)<span class="lineBar">02-322-9035</span><!-- 영업시간<span class="lineBar">11:30 ~ 22:00</span> 2018.12.06 삭제-->
										</dd>
									</dl>						
								</div>	
								<!-- //배송지 -->		
								<!-- 상품 수거지 -->	
								<div class="exchangeAdd odAddBox">
									<dl>
										<dt><label for="">상품 수거지</label></dt>
										<dd>
											<input type="text" id="exAdd01" class="input-style01 textOnly" style="width:786px;" alt="상품 수거지" maxlength="100" disabled="disabled" value="서울특별시 강남구 도곡로 117 (역삼동, 옥신타워) 12층 (주)플그림 Inerative UX팀">
											<a href="javascript:;" class="btn sm gray btnPosR">수거지 정보변경</a>
										</dd>
									</dl>
									<dl>
										<dt><label for="exAdd02">배송요청사항</label></dt>
										<dd>
											<!-- select -->
											<div class="select-style01 d_select">
												<button type="button" class="d_select_sel" id="exAdd02" style="width:786px;"><span>직접입력</span></button>
												<ul id="memo_region">
													<li><a href="javascript:;">직접입력</a></li>
													<li><a href="javascript:;">부재 시 경비실에 맡겨주세요.</a></li>
													<li><a href="javascript:;">부재 시 문 앞에 놓아주세요.</a></li>
													<li><a href="javascript:;">배송 전에 연락주세요.</a></li>
													<li><a href="javascript:;">빠른 배송 부탁드려요.</a></li>
													<li><a href="javascript:;">배관함에 넣어주세요.</a></li>
													<li><a href="javascript:;">무인 택배함에 보관해주세요.</a></li>
												</ul>
											</div>
											<!-- //select -->
										</dd>
										<dd>
											<input type="text" class="input-style01" value="" id="exAdd03" style="width:786px;" maxlength="200">
										</dd>									
									</dl>								
								</div>
								<!-- //상품 수거지 -->
							
								
								<!-- 반품상품 선택  -->
								<div class="mgInfoBox">
									<h3>반품상품 선택 </h3>
									<table class="board-list orderTable">
			                          <colgroup>
			                                <col style="width:15px">
	                                        <col>
	                                        <col style="width:100px">
	                                        <col style="width:146px">
	                                        <col style="width:240px">
			                          </colgroup>	   
			                          <thead>
			                          	<tr>
			                          		<th> 
			                          			<span class="check-skin">
	                                                <input type="checkbox" id="chkReply" checked="checked">
	                                                <span>선택</span>
	                                            </span>
	                                        </th>
			                          		<th colspan="2">상품정보/옵션</th>
			                          		<th>반품수량</th>
			                          		<th>반품사유 <span class="required">*</span></th>
			                          	</tr>
			                          </thead>       
			                          <tbody>
			                              <!-- loop -->
			                              <tr>
			                             	 <td>
	                                            <span class="check-skin">
	                                                <input type="checkbox" id="chkReply01" checked="checked">
	                                                <span>선택</span>
	                                            </span>
	                                          </td>
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
			                                  <td>
			                                  	<div class="quantityWrap">
                                                 	<button type="button" class="btMinus" name="">뺴기</button>
                                                	<button type="button" class="btPlus" name="">추가</button>
                                                	<input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="2">
                                            	</div> 
		                                  	  </td>
			                                  <td>
				                                <!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" id="" style="width:200px;"><span>선택하세요</span></button>
													<ul>
														<li><a href="javascript:;">선택하세요</a></li>														
													</ul>
												</div>
												<!-- //select -->
												<input type="text" class="input-style01 mgT05" value="" id="" style="width:200px;" maxlength="200">
			                                  </td>
			                               </tr>
			                               <!-- //loop -->
			                               <tr>
		                                	  <td></td>
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
			                               </tr>                              
										   <tr>
										   	 <td>
	                                            <span class="check-skin">
	                                                <input type="checkbox" id="chkReply05">
	                                                <span>선택</span>
	                                            </span>
	                                          </td>
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
			                                  <td>
												<div class="quantityWrap">
                                                 	<button type="button" class="btMinus" name="">뺴기</button>
                                                	<button type="button" class="btPlus" name="">추가</button>
                                                	<input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="2">
                                            	</div> 		                                  
			                                  </td>
			                                  <td>
			                                    <!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" id="" style="width:200px;"><span>선택하세요</span></button>
													<ul>
														<li><a href="javascript:;">선택하세요</a></li>														
													</ul>
												</div>
												<!-- //select -->
												<input type="text" class="input-style01 mgT05" value="" id="" style="width:200px;" maxlength="200">
			                                  </td>
			                               </tr>		                               
			                            </tbody>
			                         </table>	
		                         </div>
		                         <!-- //반품상품 선택  -->
                         	</div>	 
                         	<!--  //order LIst  -->     
                         	                 
                        </div>
                        <!--  //order Result List -->    
                        
                        
                        <!--  반품 정보 -->
                        <div class="mgInfoBox">
                        	<h3>반품 정보</h3>
                        	<table class="tbTotalList">
                        		<caption>반품 정보</caption>
		                        <colgroup>
		                             <col style="width:50%">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>
		                          	<tr>
		                          		<th>상품주문금액</th>
		                          		<td>801,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>주문할인</th>
		                          		<td>20,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>쿠폰할인</th>
		                          		<td>10,000원</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th>마일리지 환급</th>
		                          		<td>5,000원</td>		                          		
		                          	</tr>
		                          	<tr class="selmgB">
		                          		<th>포인트 환급</th>
		                          		<td>7,500원</td>		                          		
		                          	</tr>
		                          	<tr class="selLineBox">
		                          		<th>배송비</th>
		                          		<td>0원</td>		                          		
		                          	</tr>		                          	
		                        </tbody>
		                        <tfoot>
		                        	<tr>
		                          		<th>환불금액</th>
		                          		<td><strong>999,999,999</strong>원</td>
		                          	</tr>
		                        </tfoot>
	                        </table>                        
                        </div>
                        <!--  //반품 정보 -->
                        
						<!--  환불계좌  -->
                        <div class="mgInfoBox">
                        	<h3>환불계좌 정보</h3>
                        	<table class="tbInputList">
                        		<caption>환불계좌</caption>
		                        <colgroup>
		                             <col style="width:100px">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>
		                          	<tr>
		                          		<th><label for="bankName">은행명</label> <span class="required">*</span></th>
		                          		<td>
											<!-- select -->
											<div class="select-style01 d_select">
												<button type="button" id="bankName" class="d_select_sel" style="width:410px;"><span>선택하세요</span></button>
												<ul>
													<li><a href="javascript:;">선택하세요</a></li>													
												</ul>
											</div>
											<!-- //select -->		                          		
		                          		</td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th><label for="bankTo">예금주</label><span class="required">*</span></th>
		                          		<td><input type="text" id="bankTo" class="input-style01 textOnly" style="width:410px;" alt="은행명" maxlength="100"></td>		                          		
		                          	</tr>
		                          	<tr>
		                          		<th><label for="bankNumber">계좌번호</label><span class="required">*</span></th>
		                          		<td><input type="text" id="bankNumber" class="input-style01 textOnly" style="width:410px;" alt="계좌번호" maxlength="100" placeholder="‘-’을 제외하고 입력해주세요."></td>		                          		
		                          	</tr>
	                          	</tbody>
	                        </table>
                        </div>
                        <!--  //환불계좌  -->                        
                        
                        <div class="btnWrapBox">
							<a href="javascript:;" class="btn">취소</a>
							<a href="javascript:;" class="btn fill">반품신청</a>
						</div>
                        
                        <!--  주의사항  -->
                        <div class="mgInfoBox">
                        	<ul class="text-list02">  
								<li>일부 상품만 반품신청 하시면 고객센터에서 확인 후, 반품 접수 처리 됩니다.</li>
								<li>주문상품 중 일부만 반품하시면 이미 사용하신 마일리지, 포인트는 주문금액 중 반품 금액 비율 로 나누어 복원됩니다.</li>
								<li>장바구니 쿠폰 등 주문 전체에 사용된 쿠폰은 일부 반품 시, 복원되지 않을 수 있습니다.</li>
								<li>묶음배송으로 인해 배송비 무료가 적용된 경우 추가 배송비가 발생 될 수 있습니다.</li>
								<li>반품 시 수거지 주소를 변경할 수 있습니다. 변경하고 싶은 주소가 없으실 때는 마이페이지 > 배송지관리에서 먼저 배송지추가를 하시기 바랍니다.</li><!-- 2018.12.06 수정 -->
								<li>교환/반품 신청은 배송완료일로부터 7일 이내에 신청이 가능합니다.</li>
								<li>에스크로서비스를 선택한 주문은 전체 취소만 가능합니다.</li> 
                            </ul>
                        </div>
                        <!--  //주의사항  -->
                       
						
					</div>		
					<!-- //order Result -->							
					
					
				</div>		
				<!--  // 취소상품 선택   -->	
				

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