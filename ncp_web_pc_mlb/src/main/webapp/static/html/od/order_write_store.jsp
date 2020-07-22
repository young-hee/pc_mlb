﻿<!DOCTYPE html>
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
                    <h2 class="title01">주문서</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">주문서</strong>
                    </p>
                 </div>
            </div>

			<main class="contents" id="contents">
				<section>
				
                    <ul class="stepInfoBox">
                        <li class="on">장바구니</li>
                        <li class="on">주문서</li>
                        <li>주문완료</li>
                    </ul>
                    
                    <!-- order Contents : 매장픽업 주문서작성 -->
                    <div class="orderContents orderWrite">
                        <div class="orderInfoArea">
    
                            <!-- 주문상품 정보 -->
                            <div class="orderWriteArea">
                                <h3 class="title06">주문상품정보</h3>
                                <div class="orderWrite">
                                    <!-- order list -->
                                    <div class="orderTable">
                                        <table class="board-list">
                                            <colgroup>
                                                <col style="width:">
                                                <col style="width:110px">
                                                <col style="width:110px">
                                                <col style="width:110px">
                                            </colgroup>
                                            <thead>
                                                <tr>
                                                    <th scope="col">상품</th>
                                                    <th scope="col">수량</th>
                                                    <th scope="col">할인/혜택</th>
                                                    <th scope="col">주문금액</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <!-- loop --><!-- list01 -->
                                                <tr>
                                                    <td class="tleft">
                                                        <div class="product-info">
                                                            <div class="product-info-img"><a href="javascript:;"><img src="/static/images/od/sample_pd03.png" alt="상품이미지"></a></div>
                                                            <div class="product-info-text">
                                                                <div class="product-info-box">
                                                                    <!--<div class="product-more-info"><span class="text-color01">[예약배송]</span></div> -->
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
                                                    <td>1</td>
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
                                                </tr>
                                                <!-- // loop --><!-- //list01 -->
                                            </tbody>
                                        </table>
                                    </div>
                                    <!-- // order list -->
                                    <p class="iconTxt01 mt20">매장주문인 경우 사은품이 제공되지 않습니다.</p>
                                </div>
                            </div>
                            <!-- // 주문상품 정보 -->
                            
                            <!-- 할인정보 -->
                            <div class="orderWriteArea">
                                <h3 class="title06">할인정보</h3>
									<div class="order-detail-wrap d_toggle on">
									<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
									<div class="order-detail-content d_toggle_cont">
										<div class="board-write">
											<table>
												<caption>할인정보</caption>
												<colgroup>
													<col style="width:200px;">
													<col>
												</colgroup>
												<tbody>
													<tr>
														<th scope="row"><label for="noMemCupon">쿠폰</label></th>
														<td>
															<input type="text" id="noMemCupon" class="input-style01 input_required textOnly" style="width:255px;" alt="쿠폰" maxlength="270">
                                                            <span class="btnTdArea"><a href="#lypopCuponSch" class="btn d_layer_open">쿠폰조회</a></span>
                                                            <span class="txtSub01">적용된 쿠폰 0건 (적용 가능한 쿠폰 <em>2</em>건)</span>
                                                        </td>
													</tr>
													<tr>
														<th scope="row"><label for="noMemMileage">마일리지</label></th>
														<td>
                                                            <input type="text" id="noMemMileage" class="input-style01 input_required textOnly" style="width:255px;" alt="마일리지" maxlength="270">
                                                            <span class="btnTdArea">
                                                                <a href="javascript:;" class="btn">적용</a>
                                                                <a href="javascript:;" class="btn fill">전체적용</a>
                                                            </span>
                                                            <span class="txtSub01">잔액 <em>2,000,000원</em></span><!--  2018.11.30 text 수정 -->
                                                            <span class="txtSub02 st">보유 마일리지가 5,000원 이상일 경우 1,000원 단위로 사용하실 수 있습니다.</span><!--  2018.11.30 text 수정 -->
														</td>
													</tr>
													<tr>
														<th scope="row"><label for="noMemPoint">포인트</label></th>
														<td>
                                                            <input type="text" id="noMemPoint" class="input-style01 input_required textOnly" style="width:255px;" alt="포인트" maxlength="270">
                                                            <span class="btnTdArea">
                                                                <a href="javascript:;" class="btn">적용</a>
                                                                <a href="javascript:;" class="btn fill">전체적용</a>
                                                            </span>
                                                            <span class="txtSub01">잔액 <em>4,500원</em></span><!--  2018.11.30 text 수정 -->
                                                            <span class="txtSub02 st">보유 포인트가 1,000원 이상일 경우 1,000원 단위로 사용하실 수 있습니다.</span><!--  2018.11.30 text 수정 -->
														</td>
													</tr>													
												</tbody>
											</table>
										</div>	
									</div>
									<button type="button" class="btn-close d_toggle_select"><span>Close</span></button>
								</div> 
                            </div>  
                            <!-- //할인정보 -->
    
                            
                            <!-- 주문자 정보 -->
                            <div class="orderWriteArea">
                                <h3 class="title06">주문자 정보</h3>                           
                               	<div class="order-detail-wrap d_toggle on">
									<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
									<div class="order-detail-content d_toggle_cont">
										<div class="board-write">
											<table>
												<caption>주문자 정보</caption>
												<colgroup>
													<col style="width:200px;">
													<col>
												</colgroup>
												<tbody>
													<tr>
														<th scope="row"><label for="noMemName">주문자명</label> <span class="required">*</span></th>
														<td>
															<input type="text" id="noMemName" class="input-style01 input_required textOnly" style="width:255px;" alt="주문자명" maxlength="100">
                                                            <p class="txtSub01">
                                                                <span class="check-skin">
                                                                    <input type="checkbox" id="checkMemSame" checked="checked">
                                                                    <span>선택</span>
                                                                </span>
                                                                <label for="checkMemSame">회원정보와 동일</label>
                                                            </p>
                                                        </td>
													</tr>
													<tr>
														<th scope="row"><label for="noMemMobile1">휴대전화번호</label> <span class="required">*</span></th><!-- 2018.12.28 문구 수정 -->
														<td>
															<input type="text" id="noMemMobile1" class="input-style01 input_required numberOnly" style="width:65px;" alt="휴대전화번호" maxlength="3"><!-- 2018.12.28 문구 수정 -->
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 input_required numberOnly" id="noMemMobile2" style="width:75px;" alt="휴대전화번호" maxlength="4"><!-- 2018.12.28 문구 수정 -->
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 input_required numberOnly" id="noMemMobile3" style="width:75px;" alt="휴대전화번호" maxlength="4"><!-- 2018.12.28 문구 수정 -->
														</td>
													</tr>
													<tr>
														<th scope="row"><label for="noMemTel1">전화번호</label></th>
														<td>
															<input type="text" id="noMemTel1" class="input-style01 numberOnly" style="width:65px;" maxlength="3">
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly" id="noMemTel2" style="width:75px;" maxlength="4">
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly" id="noMemTel3" style="width:75px;" maxlength="4">
														</td>
													</tr>
													<tr>
														<th scope="row"><label for="noMemEmail1">이메일 주소</label> <span class="required">*</span></th>
														<td>
															<input type="text" id="noMemEmail1" class="input-style01 input_required" style="width:152px;" alt="이메일" maxlength="100">
															<span class="at">@</span>
															<input type="text" id="noMemEmail2" class="input-style01 input_required" style="width:152px;" alt="이메일" maxlength="100">
															<!-- select -->
															<div class="select-style01 d_select">
																<button type="button" class="d_select_sel" style="width:152px;"><span>직접입력</span></button>
																<ul>
																	<li><a href="javascript:;">직접입력</a></li>
																	<li><a href="javascript:;">naver.com</a></li>
																	<li><a href="javascript:;">daum.net</a></li>
																	<li><a href="javascript:;">nate.com</a></li>
																	<li><a href="javascript:;">gmail.com</a></li>
																	<li><a href="javascript:;">Hotmail.com</a></li>
																</ul>
															</div>
															<!-- //select -->
														</td>
													</tr>
												</tbody>
											</table>
										</div>	
									</div>
									<button type="button" class="btn-close d_toggle_select"><span>Close</span></button>
								</div>                
                            </div>  
                            <!-- //주문자 정보 -->
                            
                            <!-- 받는분 및 매장정보 -->
                            <div class="orderWriteArea">
                                <h3  class="title06">받는분 및 매장정보 </h3><!--  2018.11.30 text 수정 -->
								<div class="order-detail-wrap d_toggle on">
									<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
									<div class="order-detail-content d_toggle_cont">									
									
										<div class="board-write">
											<table>
												<caption>받는분 및 매장정보</caption>
												<colgroup>
													<col style="width:200px;">
													<col>
												</colgroup>
												<tbody>
													<tr>
														<th scope="row"><label for="orderName">받는분</label> <span class="required">*</span></th>
														<td>
															<input type="text" id="orderName" class="input-style01 input_required textOnly" alt="받는분" value="홍길동" style="width:373px;" maxlength="20">
											                <p class="txtSub01">
										                        <span class="check-skin info_no_mem" style="">
															  	    <input type="checkbox" id="addSelect">
																    <span>선택</span>
															    </span>
															    <label for="addSelect">회원정보와 동일</label>
										                    </p>
														</td>
													</tr>
													<tr>
														<th scope="row"><label for="ordMobile1">휴대전화번호</label> <span class="required">*</span></th><!-- 2018.12.28 문구 수정 -->
														<td>
															<input type="text" id="ordMobile1" class="input-style01 numberOnly input_required" value="010" style="width:65px;" alt="휴대전화번호" maxlength="3"><!-- 2018.12.28 문구 수정 -->
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly input_required" id="ordMobile2" value="2356" style="width:75px;" alt="휴대전화번호" maxlength="4"><!-- 2018.12.28 문구 수정 -->
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly input_required" id="ordMobile3" value="7778" style="width:75px;" alt="휴대전화번호" maxlength="4"><!-- 2018.12.28 문구 수정 -->
														</td>
													</tr>													
													<tr>
														<th scope="row"><label for="ordTel1">전화번호</label></th>
														<td>
															<input type="text" id="ordTel1" class="input-style01 numberOnly" value="" style="width:65px;" maxlength="3">
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly" id="ordTel2" value="" style="width:75px;" maxlength="4">
															<span class="hyphen">-</span>
															<input type="text" class="input-style01 numberOnly" id="ordTel3" value="" style="width:75px;" maxlength="4">
														</td>
													</tr>													
													<tr>
														<th scope="row">픽업매장정보</th><!--  2018.11.30 text 수정 -->
														<td class="storeInfoSel">
                                                            <p class="StoreInfo">
                                                            	<strong class="name">홍대 직영점</strong>
                                                            	<a href="#lyPopfindStore" class="btnMap"><img src="/static/images/od/icon_location.png" alt="지도보기"></a><br>
                                                            	서울특별시 마포구 와우산로 90(서교동)  <em>/</em>  02-322-9035<br> 
                                                            	영업시간  <em>|</em>  11:30 ~ 22:00
                                                           	</p>
                                                            <p class="checkMg">매장픽업 주문 상품이 준비완료 되었다는 알림(알림톡 또는 SMS) 수신 후, 3일(영업일 기준)까지 픽업하지 않으면,<br>주문이 자동 취소 됩니다.</p><!--  2018.11.30 text 수정 -->
                                                        </td>
													</tr>
												
												</tbody>
											</table>
										</div>										
									</div>
									<button type="button" class="btn-close d_toggle_select"><span>Close</span></button>	
								</div>		            
                            </div>  
                            <!-- //받는분 및 매장정보 -->
                            
                             <!-- 결제수단 -->
                            <div class="orderWriteArea">
                                <h3  class="title06">결제수단</h3>
								<div class="order-detail-wrap d_toggle on">
									<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
									<div class="order-detail-content d_toggle_cont orderPayOptTab">
	                                    <div class="orderPayOpt">
	                                    	<ul>
	                                    		<li>
	                                    			<span class="rdo-skin"><input type="radio" id="card_payment" name="orderPaytBtn" checked="checked"><span></span></span>
	                                    			<label for="card_payment" class="d_tab02_select">신용/체크카드</label>
	                                    		</li>
                                                 <li>
	                                    			<span class="rdo-skin"><input type="radio" id="naver_payment" name="orderPaytBtn"><span></span></span>
	                                    			<label for="naver_payment" class="d_tab02_select">네이버페이</label>
	                                    		</li>
                                               <!-- <li>
	                                    			<span class="rdo-skin"><input type="radio" id="transfer_payment" name="orderPaytBtn"><span></span></span>
	                                    			<label for="transfer_payment" class="d_tab02_select">무통장입금(가상계좌)</label>
	                                    		</li> 2018.12.28 삭제 -->
                                                <li>
	                                    			<span class="rdo-skin"><input type="radio" id="virtual_payment" name="orderPaytBtn"><span></span></span>
	                                    			<label for="virtual_payment" class="d_tab02_select">실시간 계좌이체</label>
	                                    		</li>
                                                <!-- <li>
	                                    			<span class="rdo-skin"><input type="radio" id="mobile_payment" name="orderPaytBtn"><span></span></span>
	                                    			<label for="mobile_payment" class="d_tab02_select">휴대폰결제</label>
	                                    		</li> 2018.12.28 삭제 -->
	                                    	</ul>
                                        </div>
    
                                        <!-- 신용카드결제 시 유의사항 -->
                                        <div class="orderPaytCont" style="display:block">
                                        	<h4>신용카드결제 시 유의사항</h4>      
                                        	<div class="mbBox payGuide">
                                       			<a href="#lypopCardPayCf" class="btn sm">공인인증안내</a>
                                       			<a href="#lypopCardPaysf" class="btn sm">안전결제안내</a>
                                       			<a href="#lypopCardPayClick" class="btn sm">안심클릭안내</a>
                                       		</div>    
											<ul class="text-list01">
                                        		<li>추가적으로 더 궁금하신 사항은 1:1 고객상담으로 문의 주시기 바랍니다.</li>  
                                        	</ul>       
                                        	<p class="orderMgBox">상품 주문 후 사이즈, 색상 변경이 불가능 하오니 주문 시 참고 해 주시기 바랍니다.</p>
                                        </div>
                                        <!--  //신용카드결제 시 유의사항 -->
                                        
                                        <!-- 네이버페이 -->
                                        <div class="orderPaytCont">
                                        	<h4>네이버페이 이용 안내</h4>
                                        	<ul class="text-list01">
                                        		<li>네이버페이는 네이버ID로 별도 앱 설치 없이 신용카드 또는 은행계좌 정보를 등록하여 네이버페이 비밀번호로 결제할 수 있는 간편결제 서비스입니다.</li>
                                        		<li>주문 변경 시 카드사 혜택 및 할부 적용 여부는 해당 카드사 정책에 따라 변경될 수 있습니다.
                                        			<ul class="text-list02">
                                        				<li>결제 가능한 신용카드 : 신한, 삼성, 현대, BC, 국민, 하나, 롯데, NH농협, 씨티</li>
                                        				<li>결제 가능한 은행 : NH농협, 국민, 신한, 우리, 기업, SC제일, 부산, 경남, 수협, 우체국, 미래에셋대우, 광주, 대구, 전북, 새마을금고, 제주은행, 신협, 하나은행</li>
                                        			</ul>
                                        		</li>
                                        		<li>네이버페이 카드 간편결제는 네이버페이에서 제공하는 카드사 별 무이자, 청구할인 혜택을 받을 수 있습니다.</li>
                                        	</ul>
                                        	<p class="orderMgBox">상품 주문 후 사이즈, 색상 변경이 불가능 하오니 주문 시 참고 해 주시기 바랍니다.</p>
                                        </div>
                                        <!-- // 네이버페이 -->
                                        
                                        <!--  무통장입금(가상계좌)  2018.12.28 삭제
                                        <div class="orderPaytCont">
                                        	<h4>구매안전 에스크로 서비스 사용</h4>
                                        	<div class="rdOptBox mbBox">
                                        		<span class="rdo-skin"><input type="radio" id="rdEscroOpt01Y" name="rdEscroOpt01"  checked="checked"><span></span></span>
	                                    		<label for="rdEscroOpt01Y" class="d_tab02_select">구매안전 에스크로 사용</label>
                                        		<span class="rdo-skin"><input type="radio" id="rdEscroOpt01N" name="rdEscroOpt01"><span></span></span>
	                                    		<label for="rdEscroOpt01N" class="d_tab02_select">구매안전 에스크로 사용 안함</label>	                                    		
                                        	</div>
                                        	<ul class="text-list01">
												<li>주문접수 후 입금완료가 되어야 주문 및 배송이 처리 됩니다.</li>
												<li>주문접수 후 입금 완료 시점에 재고가 품절되어 주문이 자동 취소 될 수 있습니다.</li>
												<li>무통장입금(가상계좌) 신청 후 1일(24시간) 이내 입금되지 않으면 주문이 자동 취소됩니다.</li>
												<li>무통장입금(가상계좌) 전에 MY PAGE 주문내역에서 신용/체크카드, 네이버페이, 실시간 계좌이체로 변경하실 수 있습니다.</li>
												<li>입금 시 예금주 명은 ‘㈜ 에프앤에프’ 입니다.</li>
											</ul>
											<p class="orderMgBox">상품 주문 후 사이즈, 색상 변경이 불가능 하오니 주문 시 참고 해 주시기 바랍니다.</p> 
                                        </div>
                                         // 무통장입금(가상계좌) -->
                                        
                                        <!--  실시간 계좌이체  -->
                                        <div class="orderPaytCont">
                                        	<h4>구매안전 에스크로 서비스 사용</h4>
                                        	<div class="rdOptBox mbBox">
                                        		<span class="rdo-skin"><input type="radio" id="rdEscroOpt02Y" name="rdEscroOpt02" checked="checked"><span></span></span>
	                                    		<label for="rdEscroOpt02Y" class="d_tab02_select">구매안전 에스크로 사용</label>
                                        		<span class="rdo-skin"><input type="radio" id="rdEscroOpt02N" name="rdEscroOpt02"><span></span></span>
	                                    		<label for="rdEscroOpt02N" class="d_tab02_select">구매안전 에스크로 사용 안함</label>	                                    		
                                        	</div>
                                        	<ul class="text-list01">
												<li>실시간 계좌이체는 결제와 동시에 입출금이 처리 됩니다.</li>
												<li>이체 수수료는 부과되지 않습니다.</li>												
											</ul>
											<p class="orderMgBox">상품 주문 후 사이즈, 색상 변경이 불가능 하오니 주문 시 참고 해 주시기 바랍니다.</p>                                         
                                        
                                        </div>
                                        <!-- //실시간 계좌이체 -->
                                        
                                        <!--  휴대폰 결제  2018.12.28 삭제
                                        <div class="orderPaytCont">
                                        	<ul class="text-list01">
                                        		<li>휴대폰 결제가능 금액은 각 이동통신사에 설정한 결제한도에 의해 결정됩니다.</li>
                                        		<li>휴대폰 결제금액은 증빙서류 발급에서 제외됩니다. (현금영수증은 휴대폰 요금을 현금 납부하는 경우에만 해당 이동통신사에서 발급합니다.)<br>휴대폰 결제 전체 취소 시, 
                                        			<ul class="text-list02">
                                        				<li>당월 내 취소 요청 건은 즉시 취소됩니다.</li>
                                        				<li>전월 결제 건 취소 시에는 이동통신사의 익월취소 불가 정책에 따라 환불 입력하신 계좌번호로 현금 환불되며 이때, 전월 이동통신비에 상품구매 결제 금액이 청구 됩니다.</li>
                                        			</ul>
                                        		</li>
                                        		<li>핸드폰 소액결제 부분 취소 시, 잔존 대금에 대한 재승인 후 기존에 결제된 전체금액 승인이 취소 처리됩니다.</li>
                                        		<li>잔존 대금이 남은 결제한도액에 못 미치는 경우, 주문이 전체 취소 됩니다.</li>
                                        	</ul>
                                        	<p class="orderMgBox">상품 주문 후 사이즈, 색상 변경이 불가능 하오니 주문 시 참고 해 주시기 바랍니다.</p>                                        
                                        </div>
                                        //휴대폰 결제 -->
                                        
                                        <p class="payOptSave">
                      						<span class="check-skin"><input type="checkbox" id="chkPayOptSame"><span>선택</span></span>
											<label for="chkPayOptSame">다음에도 이 결제수단으로 결제하기</label>                 
                                       </p>
    
									</div>
									<button type="button" class="btn-close d_toggle_select"><span>Close</span></button>	
								</div>	                               
                            </div>  
                            <!-- //결제수단 -->
                            
                           <!-- pay box -->
                           <div class="orderPay d_fix"><!-- d_fix -->
                                <div class="orderPayList d_fix_obj"><!-- d_fix_obj -->
                                    <h3>결제정보</h3>
                                    <div class="orderPayInfo">
                                         <dl>
                                            <dt>선택상품금액</dt>
                                            <dd>259,000원</dd>                                            
                                            <dt>할인적용금액</dt><!--  2018.11.30 text 수정 -->
                                            <dd class="c_r">+2,500원</dd>
                                            <dt>배송비</dt><!--  2018.11.30 위치 수정 -->
                                            <dd>+0원</dd><!--  2018.11.30 위치 수정 -->
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
                                    <div class="chkPayAgree">
                                        <span class="check-skin"><input type="checkbox" id="chkAgreeOk"><span>선택</span></span>
                                        <label for="chkAgreeOk">주문하실 상품, 가격, 배송정보, 할인정보 등을 확인하였으며, 구매에 동의하시겠습니까?(전자상거래법 제 8조 제2항)</label>
                                    </div>
                                    <div class="btn_order">
                                        <a href="javascript:;" class="btn lg fill">결제하기</a>
                                    </div>   
                                </div>
                            </div>
                            <!-- pay box -->
	                           
	                   </div>	 
                    </div>
                    <!-- //order Contents : 매장픽업 주문서작성-->

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
    //radio click tab
    $('.orderPayOpt input[type=radio]').click(function(){
        $('.orderPayOptTab .orderPaytCont').css('display','none');
        $('.orderPayOptTab .orderPaytCont').eq($('.orderPayOpt input[type=radio]').index(this)).css('display','block');
    });
});
</script>
</body>
</html>