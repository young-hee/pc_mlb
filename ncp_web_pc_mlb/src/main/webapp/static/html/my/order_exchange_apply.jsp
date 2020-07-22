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

			<h2 class="title01">상품교환 신청</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>주문/배송조회 목록</span><strong title="현재 위치">상품교환 신청</strong>
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
									<span>일반주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>	
								<!-- //주문정보 -->	
								<!-- 배송지 -->
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
													<!-- <li><a href="javascript:;">빠른 배송 부탁드려요.</a></li>
													<li><a href="javascript:;">배관함에 넣어주세요.</a></li> 2018.12.06 삭제-->
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
							
								
								<!-- 교환상품 선택  -->
								<div class="mgInfoBox">
									<h3>교환상품 선택 </h3>
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
			                          		<th>교환수량/옵션</th>
			                          		<th>교환사유 <span class="required">*</span></th>
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
			                                  	<a href="javascript:;" class="btn sm fill">옵션변경</a>
			                                  	<span class="optTxt">1 / GREEN / 240</span>
			                                  	<span class="optTxt">2 / NAVY / 95</span>
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
												<a href="javascript:;" class="btn sm fill">옵션변경</a>
			                                  	<span class="optTxt">1 / GREEN / 240</span>
			                                  	<span class="optTxt">2 / NAVY / 95</span>			                                  
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
		                         <!-- //교환상품 선택  -->
                         	</div>	 
                         	<!--  //order LIst  -->     
                         	                 
                        </div>
                        <!--  //order Result List -->    
                        
                        
                        <!--  교환 배송비 -->
                        <div class="mgInfoBox">
                        	<h3>교환 배송비</h3>
                        	<table class="tbTotalList">
                        		<caption>교환 배송비</caption>
		                        <colgroup>
		                             <col style="width:50%">
                                     <col style="width:">
		                        </colgroup>	   
		                        <tbody>	
		                          	<tr class="selmgB">
		                          		<th class="fc_black">교환왕복배송</th>
		                          		<td>0원</td>		                          		
		                          	</tr>
		                          	<tr class="selLineBox">
		                          		<th>결제금액</th>
		                          		<td><strong>999,999,999</strong>원</td>
		                          	</tr>		                          	
		                        </tbody>		                      
	                        </table>                        
                        </div>
                        <!--  //교환 배송비 -->
                        
                        <div class="btnWrapBox">
							<a href="javascript:;" class="btn">취소</a>
							<a href="javascript:;" class="btn fill">교환신청</a> <!--  2018.12.06 수정 -->
						</div>
                        
                        <!--  주의사항  -->
                        <div class="mgInfoBox">
                        	<ul class="text-list02">                               
                                <li>고객 단순 변심으로 교환을 한 경우 교환 배송비가 발생합니다.</li> 
								<li>신청한 교환의 철회는 고객센터(080-807-0012)를 통해 신청 할 수 있습니다.</li> 
								<li>교환/반품 신청은 배송완료일로부터 7일 이내에 신청이 가능합니다.</li> 
								<li>지정된 택배를 통한 교환상품 입고가 되지 않는 경우, 교환 요청하신 상품에 이상이 있는 경우 교환처리가 되지 않을 수도 있습니다.</li>                           
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