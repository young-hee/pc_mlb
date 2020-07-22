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
 
                    <!-- 장바구니 (일반주문)  -->
                    <div class="orderContents">
                    	<!-- order Type -->
                        <div class="orderType">
                            <ul class="tab-type03">
                                <li class=" on"><a href="/static/html/od/cart.jsp">일반주문<em>(10)</em></a></li>
                                <li><a href="/static/html/od/cart_reserve.jsp">예약주문<em>(0)</em></a></li>
                                <li><a href="/static/html/od/cart_store.jsp">매장픽업주문<em>(0)</em></a></li><!--  2018.11.30 text 수정 -->
                            </ul>
                        </div>
                        <!-- //order Type -->
                        
                        <!-- order Contents : 일반주문 상품 -->
                        <div class="orderInfoArea">
	                       
                            <!-- order list -->
                            <div class="orderTable">
                                <div class="tableTopArea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
                                <table class="board-list">
                                    <colgroup>
                                        <col style="width:35px">
                                        <col style="width:">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:50px">
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
                                            <td><strong class="fw_bold">139,700원</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!-- // loop --><!-- //list01 -->
                                        <!-- list02 -->
                                        <tr>
                                            <td>
                                                <span class="check-skin">
                                                    <input type="checkbox" id="chkReply" checked="checked">
                                                    <span>선택</span>
                                                </span>
                                            </td>
                                            <td class="tleft">
                                                <div class="product-info">
                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd02.png" alt="상품이미지"></a></div>
                                                    <div class="product-info-text">
                                                        <div class="product-info-box">
                                                            <div class="product-more-info"><span class="text-color01">[매장배송]</span></div>
                                                            <p class="product-name"><a href="javascript:;">[남성] 엠보 그라데이션 반팔 티셔츠 그라데이션  반팔 티셔츠  반팔 티셔츠  반팔 티셔츠</a></p>
                                                            <div class="product-price">
                                                                <span>199,000원</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-option">
                                                            <span>YELLOW / 90</span><a href="#lypopPdMod" class="btn_txt btn_modify d_layer_open"><em>변경</em></a>
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
                                             <td><!-- 할인정보  --></td>
                                            <td><strong class="fw_bold">139,700원</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!--// list02 -->
                                        <!-- list03 -->
                                        <tr>
                                            <td>
                                                <span class="check-skin">
                                                    <input type="checkbox" id="chkReply" checked="checked">
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
                                                            <p><!-- <strong>세트상품A:</strong> -->[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82</p><!--  2018.11.30 text 삭제 -->
                                                            <p>
	                                                           	<!-- <strong>세트상품A:</strong> -->[엑소티셔츠] 뉴욕양키스 컬러 웨이브 3단배색 후드티셔츠 3단배색후드티셔츠 WINE / 82  <!--  2018.11.30 text 삭제 -->
	                                                           	<a href="#lypopPdModSet" class="btn_txt btn_modify d_layer_open"><em>변경</em></a>
                                                            </p>
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
                                            <td><!-- 할인정보  --></td>
                                            <td><strong class="fw_bold">139,700원</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!-- //list03 -->
                                        <!-- list04 -->
                                        <tr>
                                            <td>
                                               <!--<span class="check-skin">
                                                    <input type="checkbox" id="chkReply" checked="checked">
                                                    <span>선택</span>
                                                </span> -->
                                            </td>
                                            <td class="tleft">
                                                <div class="product-info product-add-set">
                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
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
                                            <td>
                                                <div class="quantityWrap">
                                                    <button type="button" class="btMinus" name="">뺴기</button>
                                                    <button type="button" class="btPlus" name="">추가</button>
                                                    <input type="number" class="pdNumber" name="" maxlength="5" id="pdNumber" checked="checked" value="1">
                                                </div>
                                                <a href="javascript:;" class="btn_txt btn_num_save"><em>적용</em></a>
                                            </td>
                                             <td><!-- 할인정보  --></td>
                                            <td><strong class="fw_bold">17,000원</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!-- //list04 -->
                                        <!-- list05 -->
                                        <tr>
                                            <td>
                                                <span class="check-skin">
                                                    <input type="checkbox" id="chkReply" checked="checked">
                                                    <span>선택</span>
                                                </span>
                                            </td>
                                            <td class="tleft">
                                                <div class="product-info">
                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
                                                    <div class="product-info-text">
                                                        <div class="product-info-box">
                                                            <p class="product-name"><a href="javascript:;">[남성] 뉴욕양키스 스트라이프 반팔 티셔츠</a></p>
                                                            <div class="product-price">
                                                                <span>189,000원</span>
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
                                             <td>사은품제공
                                                <!-- 사은품 Layer-->
                                                <div class="tooltip-wrap d_dropdown layer_free_gift">
                                                    <button type="button" class="btn_pay_view d_dropdown_sel">사은품 제공</button>
                                                    <div class="tooltip-layer">
                                                    <div class="tooltip-cnt">
                                                        <dl>
                                                            <dt><div class="product-info-img"><span><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></span></div></dt>
                                                            <dd>[사은품] MLB 스트라이프 삭스 증정</dd>
                                                        </dl>
                                                    </div>
                                                    <button type="button" class="tooltip-close d_dropdown_close">닫기</button>
                                                    </div>
                                                </div>
                                                <!-- //사은품 Layer-->                                         </td>
                                            <td><strong class="fw_bold">189,000원</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!-- //list05 -->
                                        <!-- list06 -->
                                        <tr>
                                            <td>
                                                <span class="check-skin">
                                                    <input type="checkbox" id="chkReply" disabled="disabled">
                                                    <span>선택</span>
                                                </span>
                                            </td>
                                            <td class="tleft">
                                                <div class="product-info">
                                                    <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd01.png" alt="상품이미지"></a></div>
                                                    <div class="product-info-text">
                                                        <div class="product-info-box">
                                                            <p class="product-name"><a href="javascript:;">[남성] 뉴욕양키스 스트라이프 반팔 티셔츠</a></p>
                                                            <div class="product-price">
                                                                <span>189,000원</span>
                                                            </div>
                                                        </div>
                                                        <div class="product-option">
                                                            <span>BL / 90</span><a href="#lypopPdMod" class="btn_txt btn_modify d_layer_open"><em>변경</em></a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td> </td>
                                            <td></td>
                                            <td><strong class="fc_black">일시품절</strong></td>
                                            <td><a href="javascript:;" class="btn_list_del">삭제</a></td>
                                        </tr>
                                        <!-- //list06 -->
                                        <tr>
                                            <td colspan="6">
                                                <div class="noProductMs">
                                                    <p>장바구니에 담긴 상품이 없습니다<span>MLB의 특별한 제안을 받아보세요</span></p>
                                                    <div class="linkBox">
                                                        <a href="../dp/list_d3.jsp">MEN</a>
                                                        <a href="../dp/list_d3.jsp">WOMEN</a>
                                                        <a href="../dp/list_d3.jsp">KIDS</a>
                                                        <a href="../dp/list_d3.jsp">CAP</a>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <!-- <th colspan="5"><span>배송비</span></th>
                                            <td>0원</td>-->
                                            <td colspan="6" class="dvTotal"><strong>배송비</strong><em>0원</em></td><!--  2018.12.28 -->
                                        </tr>
                                    </tfoot>
                                </table>
                                <div class="tableBtarea"><a href="javascript:;" class="btn sm gray"><span>선택상품삭제</span></a></div>
                                <div class="btnWrap">
                                    <a href="/static/html/mn/main.jsp" class="btn lg">쇼핑 계속하기</a>
                                </div>
                                <ul class="text-list02 line_t">
                                    <li>회원의 장바구니에 담긴 상품은 <strong>90일</strong>간 보관됩니다.</li>
                                    <!-- <li>30,000원 이상 구매 시 배송비는 무료 입니다.</li> 2019.01.15 삭제-->
                                    <li>매장 배송상품은 일반배송상품 및 사은품과 별도로 배송됩니다.</li>
                                    <!-- li>매장배송/매장주문 상품인 경우 주말 및 공휴일엔 취소/반품/교환 접수가 불가능합니다.</li>
                                    <li>평일 09:00 ~ 18:00에 고객센터를 통한 취소/교환/반품이 가능합니다.</li --> <!-- 2018.11.30 text 삭제 -->
                                </ul>
                            </div>
                            <!-- // order list -->
                            
                           <!-- pay box -->
                           <div class="orderPay d_fix">
                                <div class="orderPayList d_fix_obj">
                                    <h3>결제정보</h3>
                                    <div class="orderPayInfo">
                                        <dl>
                                            <dt>선택상품금액</dt>
                                            <dd>259,000원</dd>                                            
                                            <dt>할인적용금액</dt><!--  2018.11.30 text 수정-->
                                            <dd class="c_r">+2,500원</dd>
                                            <dt>배송비</dt><!-- 2018.11.30 위치 변경 -->
                                            <dd>+0원</dd><!-- 2018.11.30 위치 변경 -->
                                        </dl>
                                        <dl>
                                            <dt>총 주문금액</dt>
                                            <dd><strong>999,999,999</strong>원</dd>
                                        </dl>
                                        <dl>
                                            <dt>적립예상 마일리지</dt>
                                            <dd class="c_r">999,999,999원</dd><!--  2018.11.30 text 수정 -->
                                        </dl>
                                    </div>
                                    <!-- 주문하기 type1 -->
                                    <div class="btn_order">
                                        <a href="order_write_mb.jsp" class="btn lg fill">주문하기</a>
                                    </div>
                                    <!-- // 주문하기 type1 -->
                                    <!-- 주문하기 stype2 
                                    <div class="btn_order btn_two">
                                        <a href="javascript:;" class="btn lg">임직원 주문</a>
                                        <a href="javascript:;" class="btn lg fill">주문하기</a>
                                    </div>
                                     //주문하기 stype2 -->
                                </div>
                            </div>
                            <!-- // pay box -->	                        
	                        
	                   </div>
	                   <!-- //order Contents : 일반주문 상품-->
	                   
					   <!-- 추천상품 -->
                       <div class="recomPdList">
                           <h3>추천상품</h3>
                           <%@ include file="../_inc/inc_recommend.jsp" %>   
                       </div>
                       <!-- //추천상품-->	

	                   
                    </div>
                    <!-- //장바구니 (일반주문) -->

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