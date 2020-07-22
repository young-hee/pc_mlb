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

			<h2 class="title01">상품취소 신청</h2>
			
			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><span>주문/배송조회 목록</span><strong title="현재 위치">상품취소 신청</strong>
					</p>
				</div> 
				
				<!-- 취소상품 선택  -->
				<div class="orderInfoCon">	
					
					<!-- order Result -->
					<div class="odSearchResult odCancel">				
					
						<!--  order Result List -->
						<div class="odResulCon">								
											
							<!--  order LIst  -->
							<div class="odResulBox">
								<div class="orderNb">
									<span>일반주문</span>
	                              	<span><em>주문일</em> 2018.10.31 16:09</span>
	                              	<span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
								</div>	
								
								<h3>취소상품 선택</h3><!--  2018.12.06 위치 수정 -->								
								<table class="board-list orderTable">
		                          <colgroup>
		                                <col style="width:15px">
                                        <col style="width:">
                                        <col style="width:140px">
                                        <col style="width:144px">
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
		                          		<th>취소수량</th>
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
		                               </tr>
		                               <!-- //loop -->
		                               <tr>	                                	  
	                                	  <td>
	                                	  	<!--  2018.12.06 체크박스 추가 -->
                                            <span class="check-skin">
                                                <input type="checkbox" id="chkReply01" checked="checked">
                                                <span>선택</span>
                                            </span>
                                            <!--  // 2018.12.06 체크박스 추가 -->
                                          </td>                                          
		                                  <td class="tleft">
		                                     <div class="product-info product-add-set">
                                                   <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                                   <div class="product-info-text">
                                                       <div class="product-info-box">
                                                           <div class="product-more-info"><span class="text-color01">[추가상품]</span></div>
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
		                                  <td>1</td>
		                                  <td>
                                             <div class="quantityWrap">
                                                 <button type="button" class="btMinus" name="">뺴기</button>
                                                 <button type="button" class="btPlus" name="">추가</button>
                                                 <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
                                             </div>                                             
                                          </td>
		                               </tr>
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
		                               </tr>				
		                              <tr>
		                               	  <td>
                                            <span class="check-skin">
                                                <input type="checkbox" id="chkReply04">
                                                <span>선택</span>
                                            </span>
                                          </td>
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
		                                  <td>1</td>
		                                  <td>
                                             <div class="quantityWrap">
                                                 <button type="button" class="btMinus" name="">뺴기</button>
                                                 <button type="button" class="btPlus" name="">추가</button>
                                                 <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
                                             </div>                                             
                                          </td>
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
                                                           <p>[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p><!-- 2018.12.06 세트상품 문구 삭제 -->
                                                           <p>[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p><!-- 2018.12.06 세트상품 문구 삭제 -->
                                                       </div>
                                                   </div>
                                               </div>
		                                  </td>
		                                  <td>1</td>
		                                  <td>
                                             <div class="quantityWrap">
                                                 <button type="button" class="btMinus" name="">뺴기</button>
                                                 <button type="button" class="btPlus" name="">추가</button>
                                                 <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
                                             </div>                                             
                                          </td>
		                               </tr>		                               
		                            </tbody>
		                         </table>	
                         	</div>	 
                         	<!--  //order LIst  -->     
                         	                 
                        </div>
                        <!--  //order Result List -->    
                        
                        
                        <!--  취소정보 -->
                        <div class="mgInfoBox">
                        	<h3>취소 정보</h3>
                        	<table class="tbTotalList">
                        		<caption>취소 정보</caption>
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
                        <!--  //취소정보 -->
                        
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
							<a href="javascript:;" class="btn fill">주문취소</a>
						</div>  
					</div>		
					<!-- //order Result -->		
					
					<ul class="text-list02">
						<li>주문상품 중 일부만 취소하시면 취소 조건에 따라 이미 사용하신 포인트 및 쿠폰을 일부 돌려받으실 수 있습니다.</li>
						<li> 배송비 무료가 적용된 주문이 취소를 통해 배송비 무료 조건에 위배되는 경우 추가 배송비가 발생 될 수 있습니다.</li>
						<li>에스크로서비스를 선택한 주문은 부분취소, 부분반품이 허용되지 않습니다.</li>
						<li>에스크로서비스를 선택한 주문은 전체취소만 가능하며, 에스크로서비스를 사용한 주문의 전체 반품은 추가결제가 필요합니다.</li>
					</ul>	
					
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