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
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01">마이페이지</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents myPageMain-wrap" id="contents">
			
				<div class="location-contents">
					<p class="location">
						<span>Home</span><strong title="현재 위치">마이페이지</strong>
					</p>
				</div> 
				
				<!-- 회원정보S -->
				<div class="memberSTcont boxCont04">
					<ul>
						<li class="colorBlk silverSY"><!-- 2018.12.10 rookieSY / goldSY / mvpSY -->
							<p>Level</p>
							<strong>SILVER SLUGGER</strong>
							<a href="javascript:;"><span>구매등급 혜택보기</span></a>
						</li>
						<li>
							<p>마일리지</p>
							<strong><a href="javascript:;" class="text-color01">25,000</a>원</strong>
						</li>
						<li>
							<p>포인트</p>
							<strong><a href="javascript:;" class="text-color01">5,000</a>원</strong>
						</li>
						<li>
							<p>쿠폰</p>
							<strong><a href="javascript:;" class="text-color01">2</a>장</strong>
						</li>
					</ul>
				</div>
				<!-- //회원정보E -->
				
				
				<div class="justify-wrap">
					<h3 class="title02 left">주문내역</h3>
					<a href="javascript:;" class="btn sm fill right"><span>전체보기</span></a>
				</div>
				
				<!-- 주문내역 S -->
				
				
				<!-- 2018.12.10 주문내역리스트내용옮김 S-->
				<div class="odResulCon">								
											
					<!--  order LIst 01 -->
					<div class="odResulBox">
						<div class="orderNb">
							<span>매장픽업주문</span>
	                        <span><em>주문일</em> 2018.10.31 16:09</span>
	                        <span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
						</div>								
						<table class="board-list orderTable">
	                        <colgroup>
	                            <col style="width:">
	                            <col style="width:145px">                              
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
	                                <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">배송추적</a><br></span>
	                                	<span><a href="javascript:;" class="btn sm gray">교환신청</a></span>
	                                	<span><a href="javascript:;" class="btn sm gray">반품신청</a></span>
	                                	<span><a href="javascript:;" class="btn sm gray">구매확정</a></span>
	                                </td>
	                             </tr>
	                             <!-- //loop -->
	                             <tr>
	                                <td class="tleft">
	                                   <div class="product-info product-add-set"><!-- 추가상품 -->
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
	                                <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
	                                </td>
	                             </tr>
								<tr>
	                                <td class="tleft">
	                                   <div class="product-info product-free-gift"><!-- 사은품 -->
                                         <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                         <div class="product-info-text">
                                             <div class="product-info-box">
                                                 <div class="product-more-info"><span class="text-color01">[주문사은품]</span></div>
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
	                                <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
	                                </td>
	                             </tr>	
	                       </tbody>
	                    </table>	
	                </div>	 
	                <!--  // order LIst 01 -->
	                
	                <!--  order LIst 01 -->
					<div class="odResulBox">
						<div class="orderNb">
							<span>일반주문</span>
	                        <span><em>주문일</em> 2018.10.31 16:09</span>
	                        <span><em>주문번호</em> <a href="javascript:;">0D201810610002555</a></span>
						</div>								
						<table class="board-list orderTable">
	                        <colgroup>
	                            <col style="width:">
	                            <col style="width:145px">                              
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
	                                <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
	                                </td>
	                             </tr>
	                             <!-- //loop -->
	                             <tr>
	                                <td class="tleft">
	                                   <div class="product-info product-add-set"><!-- 추가상품 -->
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
	                                <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
	                                </td>
	                             </tr>
								<tr>
	                                <td class="tleft">
	                                   <div class="product-info product-free-gift"><!-- 사은품 -->
                                          <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                          <div class="product-info-text">
                                              <div class="product-info-box">
                                                  <div class="product-more-info"><span class="text-color01">[주문사은품]</span></div>
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
	                               <td class="selBox">
	                                	<span><a href="javascript:;" class="btn sm gray">주문취소</a></span>
	                                </td>
	                             </tr>		                               
	                              
	                       </tbody>
	                    </table>	
	                </div>	 
	                <!--  // order LIst 01 -->    
	                                      	  
	                      	
	                <!--  NO Result -->
					<div class="odResulBox">
						<div class="orderNb noResult">
							주문내역이 없습니다.
						</div>	
					</div> 
					<!--  //NO Result -->             	
                      	                         	                   
                </div>
                     
                
                <!--//2018.12.10 주문내역리스트내용옮김 E-->
                     
                     
				<!-- //주문내역 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left">위시리스트</h3>
					<a href="javascript:;" class="btn sm fill right"><span>전체보기</span></a>
				</div>
				
				<!-- 위시리스트 S -->
				<div class="item-listST">
					<ul>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">[KIDS] 뉴욕 양키스 남여공용 미니 박스 로고 후드 티셔츠</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">[KIDS] 뉴욕 양키스 남여공용 미니 박스 로고 후드 티셔츠</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><span class="btn fill sm">SOLD OUT</span><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">[KIDS] 뉴욕 양키스 남여공용 미니 박스 로고 후드 티셔츠</a></p>
								<p><del>109,000</del><strong>76,300</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<p>품절된 상품입니다.</p>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<li>
							<div class="item-img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
							<div class="item-info">
								<p><a href="javascript:;">[KIDS] 뉴욕 양키스 남여공용 미니 박스 로고 후드 티셔츠</a></p>
								<p><strong>390,000</strong></p>
							</div>
							<div class="cart-bottomBtn">
								<a href="/static/html/my/optionModi_pop.jsp" class="btn fill sm"><span>장바구니</span></a>
							</div>
							<button type="button" class="itemIMG-del">상품 삭제</button>
						</li>
						<!-- <li class="no-result">
							<p>위시리스트 보관 상품이 없습니다.<br/>상품을 위시리스트에 담아 두시면 언제든 쉽게 상품을 찾으실 수 있어요.</p>
						</li> -->
					</ul>
				</div>
				<!-- //위시리스트 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left">쿠폰함</h3>
					<a href="javascript:;" class="btn sm fill right"><span>전체보기</span></a>
				</div>
				
				<!-- 쿠폰함 S -->
				<div class="board-list">
					<table>
						<caption>쿠폰내역 쿠폰종류, 구분, 쿠폰명, 할인, 사용기간, 쿠폰적용상품</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:100px;">
							<col>
							<col style="width:100px;">
							<col style="width:150px;">
							<col style="width:150px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">쿠폰종류</th>
								<th scope="col">구분</th>
								<th scope="col">쿠폰명</th>
								<th scope="col">할인</th>
								<th scope="col">사용기간</th>
								<th scope="col">쿠폰적용상품</th>
							</tr>
						</thead>
						<tbody>
							<!-- <tr>
								<td colspan="6" class="no-result">쿠폰내역이 없습니다.</td>
							</tr> -->
							<tr>
								<td>온라인</td>
								<td>장바구니쿠폰</td>
								<td class="tleft">
									<p>구매금액별 2만원 할인쿠폰</p>
									<p class="text-color01">최소구매금액 : 200,000원</p>
								</td>
								<td><span class="text-color01">20,000원</span></td>
								<td>~ 2018.1.-22 (50일 남음)</td>
								<td></td>
							</tr>
							<tr>
								<td>온라인</td>
								<td>상품쿠폰</td>
								<td class="tleft">
									<p>10% 할인쿠폰</p>
									<p class="text-color01">최대할인금액 : 20,000원</p>
								</td>
								<td><span class="text-color01">10%</span></td>
								<td>~ 2018.1.-22 (50일 남음)</td>
								<td><a href="javascript:;" class="btn sm gray"><span>적용상품조회</span></a></td>
							</tr>
							<tr>
								<td>온라인</td>
								<td>상품쿠폰</td>
								<td class="tleft">
									<p>15% 할인쿠폰</p>
									<p class="text-color01">최소구매금액 : 20,000원</p>
									<p class="text-color01">최대할인금액 : 30,000원</p>
								</td>
								<td><span class="text-color01">15%</span></td>
								<td>2018.12.01부터 사용가능</td>
								<td><a href="javascript:;" class="btn sm gray"><span>적용상품조회</span></a></td>
							</tr>
							<tr>
								<td>온라인</td>
								<td>일반배송비쿠폰</td>
								<td class="tleft">
									<p>50,000원 이상 1,000원 할인 쿠폰</p>
									<p class="text-color01">최소구매금액 : 50,000원</p>
								</td>
								<td><span class="text-color01">1,000원</span></td>
								<td>~ 2018.1.-22 (50일 남음)</td>
								<td></td>
							</tr>
							<tr>
								<td>온오프라인</td>
								<td>장바구니쿠폰</td>
								<td class="tleft">
									<p>단체할인쿠폰</p>
								</td>
								<td><span class="text-color01">30%</span></td>
								<td>~ 2018.1.-22 (50일 남음)</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //쿠폰함 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left">1:1 문의</h3>
					<a href="javascript:;" class="btn sm fill right"><span>전체보기</span></a>
				</div>
				
				<!-- 1:1 문의내역 S -->
				<div class="board-list">
					<table>
						<caption>1:1 문의내역 번호, 문의유형, 제목, 작성일, 답변여부</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:100px;">
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">문의유형</th>
								<th scope="col">제목</th>
								<th scope="col">작성일</th>
								<th scope="col">답변여부</th>
							</tr>
						</thead>
						<tbody>
							<!-- <tr>
								<td colspan="5" class="no-result">상담내역이 없습니다.</td>
							</tr> -->
							<tr>
								<td>5</td>
								<td>기타문의</td>
								<td class="tleft"><a href="javascript:;">쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요 쿠폰이 보이지 않아요</a></td>
								<td>2018.11.02</td>
								<td>답변 전</td>
							</tr>
							<tr>
								<td>4</td>
								<td>환불문의</td>
								<td class="tleft"><a href="javascript:;">휴대폰 결제 환불문의</a></td>
								<td>2018.11.02</td>
								<td>답변 전</td>
							</tr>
							<tr>
								<td>3</td>
								<td>사은품 문의</td>
								<td class="tleft"><a href="javascript:;">사은품이 도착하지 않았습니다</a></td>
								<td>2018.11.02</td>
								<td>답변 전</td>
							</tr>
							<tr>
								<td>2</td>
								<td>A/S 문의</td>
								<td class="tleft"><a href="javascript:;">패딩 단추 결함</a></td>
								<td>2018.11.02</td>
								<td>답변완료</td>
							</tr>
							<tr>
								<td>1</td>
								<td>반품문의</td>
								<td class="tleft"><a href="javascript:;">상품 오배송</a></td>
								<td>2018.11.02</td>
								<td>답변완료</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- //1:1 문의내역 E -->

				<div class="justify-wrap">
					<h3 class="title02 left">간편로그인 계정 연결</h3>
				</div>
				
				<!-- 간편로그인 계정 연결 S -->
				<div class="snsLoginBox">
					<span class="snsNaver">네이버 계정</span>
					<div class="toggleBtn-onff d_toggle_btn">
						<button type="button" class="toggle-on d_toggle_on"><span>ON</span></button>
						<button type="button" class="toggle-off d_toggle_off" style="display: none;"><span>OFF</span></button>
					</div>
					<p class="snsLoginTxt">네이버 아이디 간편로그인 계정 연결 시 네이버 로그인으로 MLB에서 제공하는 다양한 서비스를 안전하고 편리하게 이용하실 수 있습니다.</p>
				</div>
				<!-- //간편로그인 계정 연결 E -->

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