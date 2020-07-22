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
                    <h2 class="title01">장바구니</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">장바구니</strong>
                    </p>
                 </div>
            </div>

			<main class="contents" id="contents">
				<section>
				
                    <ul class="stepInfoBox">
                        <li class="on">장바구니</li>
                        <li>주문서</li>
                        <li>주문완료</li>
                    </ul>
                    
                    <!-- 장바구니(매장픽업주문)  -->
                    <div class="orderContents">
                    	<!-- order Type -->
                        <div class="orderType">
                            <ul class="tab-type03 ">
                                <li><a href="cart.jsp">일반주문<em>(10)</em></a></li>
                                <li><a href="cart_reserve.jsp">예약주문<em>(0)</em></a></li>
                                <li class="on"><a href="cart_store.jsp">매장픽업주문<em>(0)</em></a></li><!--  2018.11.30 text 수정 -->
                            </ul>
                        </div>
                        <!-- //order Type -->
                        
                        <!-- order Contents : 매장픽업주문 -->
                        <div class="orderInfoArea full">
	                       
                            <!-- order list -->
                            <div class="orderTable orderStore">
                            
                            	<!-- store list 1 -->
                            	<div class="storeArea">    
	                                <div class="storeName">
	                                	<strong>역삼(직영)점(1)</strong>
	                                	<a href="#lyPopfindStore" class="btnMap d_layer_open"><img src="/static/images/od/icon_location.png" alt="지도보기"></a>
	                                	<span>02-553-3773  /  (평일) 10:30 ~ 22:00</span>
	                                </div>
	                                <div class="tableTopArea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
	                                <table class="board-list">
	                                    <colgroup>
	                                        <col style="width:35px">
	                                        <col style="width:">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:60px">
	                                    </colgroup>
	                                    <thead>
	                                        <tr>
	                                            <th scope="col">
	                                                <span class="check-skin">
	                                                  <input type="checkbox" id="chkReply" checked="checked">
	                                                  <span>선택</span>
	                                                </span>
	                                            </th>
	                                            <th scope="col">상품</th>
	                                            <th scope="col">수량</th>
	                                            <th scope="col">할인/혜택</th>
	                                            <th scope="col">매장픽업정보</th><!--  2018.11.30 text 수정 -->
	                                            <th scope="col">주문금액</th>
	                                            <th scope="col">삭제</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                        <!-- loop --><!-- list01 -->
	                                        <tr>
	                                            <td>
	                                                <span class="check-skin">
	                                                    <input type="checkbox" id="chkReply" checked="checked">
	                                                    <span>선택</span>
	                                                </span>
	                                            </td>
	                                            <td class="tleft">
	                                                <div class="product-info">
	                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
	                                                    <div class="product-info-text">
	                                                        <div class="product-info-box">
	                                                            <!-- <div class="product-more-info"><span class="text-color01">[예약배송]</span></div> 2018.11.30 수정 삭제 -->
	                                                            <p class="product-name"><a href="javascript:;">[CAP] 뉴욕양키스 러버 와펜 볼캡</a></p>
	                                                            <div class="product-price">
	                                                                <span>199,000원</span>
	                                                            </div>
	                                                        </div>
	                                                        <div class="product-option">
	                                                            <span>BL / 90</span><a href="#lypopPdMod" class="btn_txt btn_modify d_layer_open"><em>변경</em></a>
	                                                        </div>
	                                                    </div>
	                                                </div>
	                                            </td>
	                                            <td>
	                                                <div class="quantityWrap">
	                                                    <button type="button" class="btMinus" name="">뺴기</button>
	                                                    <button type="button" class="btPlus" name="">추가</button>
	                                                    <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
	                                                </div>
	                                                <a href="javascript:;" class="btn_txt btn_num_save"><em>적용</em></a>
	                                            </td>
	                                            <td>
	                                                100,000원
	                                                <!-- 할인정보 Layer-->
	                                                <div class="tooltip-wrap d_dropdown layer_discount">
	                                                    <button type="button" class="btn_pay_view d_dropdown_sel">할인정보</button>
	                                                    <div class="tooltip-layer">
	                                                    <div class="tooltip-cnt">
	                                                        <dl><dt>즉시할인쿠폰 (10%)X1</dt><dd>19,900원</dd></dl>
	                                                        <dl><dt class="fw_bold">총 할인금액</dt><dd class="fw_bold">19,900원</dd></dl>
	                                                    </div>
	                                                    <button type="button" class="tooltip-close d_dropdown_close">닫기</button>
	                                                    </div>
	                                                </div>
	                                                <!-- //할인정보 Layer-->
	                                            </td>
	                                            <td>당일픽업 가능</td>
	                                            <td><strong class="fw_bold">139,700원</strong></td>
	                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
	                                        </tr>
	                                        <!-- // loop --><!-- //list01 -->
	                                    </tbody>
	                                    <tfoot>
	                                        <tr>
	                                            <td colspan="3" class="pdPaySel"><dl><dt>상품금액</dt><dd><strong>999,999,000</strong>원</dd></dl></td>
	                                            <td colspan="2" class="disPaySel"><dl><dt>할인적용금액</dt><dd><strong>-10,000</strong>원</dd></dl></td>
	                                            <td colspan="2" class="orderPaySel"><dl><dt>주문금액</dt><dd><strong>94,589,000</strong>원</dd></dl></td>
	                                        </tr>
	                                        <tr>
	                                            <td colspan="7" class="sel_r">총 예상 적립마일리지 6,960원</td><!--  2018.11.30 text 수정 -->
	                                        </tr>
	                                    </tfoot>
	                                </table>
	                                <div class="tableBtarea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
	                                <div class="btnWrap">
	                                    <a href="/static/html/mn/main.jsp" class="btn lg">쇼핑 계속하기</a>
	                                    <a href="order_write_store.jsp" class="btn lg fill">주문하기</a>
	                                </div>
	                            </div>
	                            <!-- //store list 1 -->
	                            
                               	<!-- store list 2 -->
                               	<div class="storeArea">    
	                                <div class="storeName">
	                                	<strong>역삼(직영)점(2)</strong>
	                                	<a href="#lyPopfindStore" class="btnMap d_layer_open"><img src="/static/images/od/icon_location.png" alt="지도보기"></a>
	                                	<span>02-553-3773  /  (평일) 10:30 ~ 22:00</span>
	                                </div>
	                                <div class="tableTopArea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
	                                <table class="board-list">
	                                    <colgroup>
	                                        <col style="width:35px">
	                                        <col style="width:">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:120px">
	                                        <col style="width:60px">
	                                    </colgroup>
	                                    <thead>
	                                        <tr>
	                                            <th scope="col">
	                                                <span class="check-skin">
	                                                  <input type="checkbox" id="chkReply" checked="checked">
	                                                  <span>선택</span>
	                                                </span>
	                                            </th>
	                                            <th scope="col">상품</th>
	                                            <th scope="col">수량</th>
	                                            <th scope="col">할인/혜택</th>
	                                            <th scope="col">매장픽업정보</th><!--  2018.11.30 text 수정 -->
	                                            <th scope="col">주문금액</th>
	                                            <th scope="col">삭제</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                        <!-- loop --><!-- list01 -->
	                                        <tr>
	                                            <td>
	                                                <span class="check-skin">
	                                                    <input type="checkbox" id="chkReply" checked="checked">
	                                                    <span>선택</span>
	                                                </span>
	                                            </td>
	                                            <td class="tleft">
	                                                <div class="product-info">
	                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
	                                                    <div class="product-info-text">
	                                                        <div class="product-info-box">
	                                                            <!-- <div class="product-more-info"><span class="text-color01">[예약배송]</span></div> 2018.11.30 삭제 -->
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
	                                            <td>
	                                                <div class="quantityWrap">
	                                                    <button type="button" class="btMinus" name="">뺴기</button>
	                                                    <button type="button" class="btPlus" name="">추가</button>
	                                                    <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
	                                                </div>
	                                                <a href="javascript:;" class="btn_txt btn_num_save"><em>적용</em></a>
	                                            </td>
	                                            <td>
	                                                100,000원
	                                                <!-- 할인정보 Layer-->
	                                                <div class="tooltip-wrap d_dropdown layer_discount">
	                                                    <button type="button" class="btn_pay_view d_dropdown_sel">할인정보</button>
	                                                    <div class="tooltip-layer">
	                                                    <div class="tooltip-cnt">
	                                                        <dl><dt>즉시할인쿠폰 (10%)X1</dt><dd>19,900원</dd></dl>
	                                                        <dl><dt class="fw_bold">총 할인금액</dt><dd class="fw_bold">19,900원</dd></dl>
	                                                    </div>
	                                                    <button type="button" class="tooltip-close d_dropdown_close">닫기</button>
	                                                    </div>
	                                                </div>
	                                                <!-- //할인정보 Layer-->
	                                            </td>
	                                            <td>당일픽업 가능</td>
	                                            <td><strong class="fw_bold">139,700원</strong></td>
	                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
	                                        </tr>
	                                        <!-- // loop --><!-- //list01 -->
	                                    </tbody>
	                                    <tfoot>
	                                        <tr>
	                                            <td colspan="3" class="pdPaySel"><dl><dt>상품금액</dt><dd><strong>999,999,000</strong>원</dd></dl></td>
	                                            <td colspan="2" class="disPaySel"><dl><dt>할인적용금액</dt><dd><strong>-10,000</strong>원</dd></dl></td>
	                                            <td colspan="2" class="orderPaySel"><dl><dt>주문금액</dt><dd><strong>94,589,000</strong>원</dd></dl></td>
	                                        </tr>
	                                        <tr>
	                                            <td colspan="7" class="sel_r">총 예상 적립마일리지 6,960원</td><!--  2018.11.30 text 수정 -->
	                                        </tr>
	                                    </tfoot>
	                                </table>
	                                <div class="tableBtarea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
	                                <div class="btnWrap">
	                                    <a href="javascript:;" class="btn lg">쇼핑 계속하기</a>
	                                    <a href="order_write_store.jsp" class="btn lg fill">주문하기</a>
	                                </div>
	                            </div>
	                            <!-- //store list 2 -->
    
                                <ul class="text-list02 line_t">
                                    <li>회원의 장바구니에 담긴 상품은 <strong>90일</strong>간 보관됩니다.</li>
                                    <li>매장픽업 주문을 한 경우, 사은품은 제공되지 않습니다.</li><!--  2018.11.30 text 수정 -->
                                    <li>매장픽업 준비완료 알림(알림톡 또는 SMS)를 수신 후, 상품 수령이 가능합니다.</li><!--  2018.11.30 text 수정 -->
                                    <!--<li>매장픽업 안내 이후, 다음날(영업일 기준)까지 수령하지 않는 주문은 자동 취소처리 됩니다.</li>--><!--  2018.11.30 text 삭제 -->
                                </ul>
                            </div>
                            <!-- // order list -->
	                            
	                   </div>
	                   <!-- //order Contents : 매장픽업주문 -->
	                   
					    <!-- 추천상품 -->
                       <div class="recomPdList">
                           <h3>추천상품</h3>
                           <%@ include file="../_inc/inc_recommend.jsp" %>   
                       </div>
                       <!-- //추천상품-->	                  
	                   
                    </div>
                    <!-- // 장바구니(매장픽업주문)  -->

				</section>
			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
</body>
</html>