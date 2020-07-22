<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopPayModify" class="layer-popup lypopPayModify inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>결제수단 변경</h2>
		<div class="layer-cont od ly-box">					
		
		<!-- 결제수단 -->
		<div class="orderWriteArea">   		
			<div class="orderTotalNb">
				<dl>
					<dt>결제금액</dt>
					<dd><strong>199,000</strong>원</dd>
				</dl>
			</div>	
			<div class="orderPayOptTab">
                  <div class="orderPayOpt">
                   	<ul>
                   		<li>
                   			<span class="rdo-skin"><input type="radio" id="card_payment" name="orderPaytBtn" checked="checked"><span></span></span>
                   			<label for="card_payment" class="orderPayOptSel">신용/체크카드</label>
                   		</li>
                               <li>
                   			<span class="rdo-skin"><input type="radio" id="naver_payment" name="orderPaytBtn"><span></span></span>
                   			<label for="naver_payment" class="orderPayOptSel">네이버페이</label>
                   		</li>                                               
                               <li>
                   			<span class="rdo-skin"><input type="radio" id="virtual_payment" name="orderPaytBtn"><span></span></span>
                   			<label for="virtual_payment" class="orderPayOptSel">실시간 계좌이체</label>
                   		</li>                                               
                   	</ul>
                  </div>
		
		
                  <!-- 신용카드결제 시 유의사항 -->
                  <div class="orderPaytCont" style="display:block">
                   	<h4>신용카드결제 시 유의사항</h4>      
                   	<div class="payBgCont">
	                   	<div class="mbBox payGuide">
	               			<a href="#lypopCardPayCf" class="btn sm d_layer_open">공인인증안내</a>
	               			<a href="#lypopCardPaysf" class="btn sm d_layer_open">안전결제안내</a>
	               			<a href="#lypopCardPayClick" class="btn sm d_layer_open">안심클릭안내</a>
	               		</div>    
						<ul class="text-list01">
	                   		<li>추가적으로 더 궁금하신 사항은 1:1 고객상담으로 문의 주시기 바랍니다.</li>  
	                   	</ul>  
                   	</div>    
                   </div>
                   <!--  //신용카드결제 시 유의사항 -->
		                                
                   <!-- 네이버페이 -->
                   <div class="orderPaytCont">
		              	<h4>네이버페이 이용 안내</h4>
		              	<div class="payBgCont">
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
		              	</div>
                    </div>
                    <!-- // 네이버페이 -->
		
                    <!--  실시간 계좌이체 -->
                    <div class="orderPaytCont">
                    	<h4>구매안전 에스크로 서비스 사용</h4>
                    	<div class="payBgCont">
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
						</div>		
		            </div>
		            <!--  //실시간 계좌이체 -->    
				</div>
           </div>  
           <!-- //결제수단 -->
			
			<!--  button -->
			<div class="btnWrapBox">
				<a href="javascript:;" class="btn w160">취소</a>
				<a href="javascript:;" class="btn fill w160">결제하기</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<!-- 공인인증안내 Layer -->
<article id="lypopCardPayCf" class="layer-popup lypopCardinfo lypopCardPayCf inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2>공인인증안내</h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<p class="fw_bold">2005년 11월 1일부터 공인인증서 사용이 의무화 되었습니다.</p>
				<p>금감원의 전자상거래 안정성 강화정책에 따라 30만원 이상의 모든 신용카드 결제에 공인인증 사용이 의무화됩니다.<br>(단, 30만원 이하 결제시에는 공인인증서 없어도 결제 가능)</p>
				
				 <table class="board-list">
	                <colgroup>                     
	                  <col style="width:260px">
	                  <col style="width:130px">
	                  <col style="width:">
	                </colgroup>
	                <thead>
	           	   		<tr>
	           	   			<th></th>
	           	   			<th>30만원 미만</th>
	           	   			<th>30만원 이상</th>
	       	   			</tr>
	                </thead>
	                <tbody>
	                	<tr>
	                		<th>BC,국민, 우리</th>
	                		<td>안전결제</td>
	                		<td>안전결제+공인인증</td>
	               		</tr>
	                	<tr>
	                		<th>그 외 모든 카드</th>
	                		<td>안전결제</td>
	                		<td>공인인증</td>
	               		</tr>               		
	                </tbody>
	             </table>
	             
	             <p class="txtSize12">그 외 모든 카드 : LG, 삼성, 외환, 신한, 조흥, 롯데, 하나, 한미, 전북, 수협, 제주, 광주은행 카드 등</p>  
			</div>	
			
			<div class="payTxtBox">
				<h3 class="fw_bold">공인인증 발급</h3>
				<p>공인인증서는 인터넷 뱅킹 도는 카드결제 시에 본인임을 확인하는 보안장치로, 다음과 같이 발급 받으실수 있습니다.</p>
				<ul class="issueBox">
					<li><em>01.</em>거래은행방문</li>
					<li><em>02.</em>인터넷<br>뱅킹신청</li>
					<li><em>03.</em>거래은행<br>홈페이지 접속</li>
					<li><em>04.</em>공인인증서<br>다운로드 및 설치</li>
				</ul>
				<ul class="text-list02">
					<li class="fc_gray">신용카드 결제 시 공인인증서는 범용공인인증서(유료발급) 또는 신용카드용 공인인증서만 사용 가능합니다.</li>
					<li>추가적으로 더 궁금한 사항은 1:1 고객상담으로 문의 주시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="javascript:;" class="btn sm gray">1:1 고객상담</a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>
<!-- //공인인증안내 Layer -->

<!--  안전결제안내 Layer-->
<article id="lypopCardPayClick" class="layer-popup lypopCardinfo lypopCardPayClick inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2>안심클릭안내</h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold">안심클릭이란?</h3>
				<p>인터넷 쇼핑 시 고객님이 소유하신 카드에 미리 설정해둔 전자상거래용의 별도 비밀번호를 입력하여 카드 사용자 본인을 확인함으로써 온라인 상에서의 카드 무단도용을 방지해주는 서비스입니다.</p>
				
				<div class="txtsBox">					
					<h4>안심클릭 카드 안내</h4>
					<table class="board-list list_left">
		                <colgroup>                     
		                  <col style="width:110px">
		                  <col style="width:300px">
		                  <col style="width:110px">
		                  <col style="width:">
		                </colgroup>
		                
		                <tbody>
		                	<tr>
		                		<td>삼성카드</td>
		                		<td><img src="/static/images/od/card_samsung.png" alt="삼성카드"></td>
		                		<td>한미카드</td>
		                		<td><img src="/static/images/od/card_citibank.png" alt="한미카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>외환카드</td>
		                		<td><img src="/static/images/od/card_keb.png" alt="외환카드"></td>
		                		<td>수협카드</td>
		                		<td><img src="/static/images/od/card_suhyupbank.png" alt="수협카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>롯데카드</td>
		                		<td><img src="/static/images/od/card_lotte.png" alt="롯데카드"></td>
		                		<td>전북카드</td>
		                		<td><img src="/static/images/od/card_jbbank.png" alt="전북카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>현대카드</td>
		                		<td><img src="/static/images/od/card_hyunda.png" alt="현대카드"></td>
		                		<td>광주카드</td>
		                		<td><img src="/static/images/od/card_kjbank.png" alt="광주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>신한카드</td>
		                		<td><img src="/static/images/od/card_shinhan.png" alt="신한카드"></td>
		                		<td>제주카드</td>
		                		<td><img src="/static/images/od/card_jejubank.png" alt="제주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>시티카드</td>
		                		<td><img src="/static/images/od/card_citibank.png" alt="시티카드"></td>
		                		<td>조흥카드</td>
		                		<td><img src="/static/images/od/card_shinhan.png" alt="조흥카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td>신세계한미카드</td>
		                		<td><img src="/static/images/od/card_shinsegae.png" alt="신세계한미카드"></td>
		                		<td>신한(구LG)</td>
		                		<td><img src="/static/images/od/card_shinhan_lg.png" alt="신한(구LG)"></td>	                		
		               		</tr>    		
		                </tbody>
		             </table>
	             </div>
				
				<div class="txtsBox">
					<h4>안심클릭 대상카드</h4>
					<div class="txts">
						<ul class="text-list02 txtTypeGray">
							<li>국민카드/ BC카드/ 우리카드를 제외한 모든 신용카드</li>
							<li>삼성/ 외환/ 롯데/ 현대/ 신한/ 시티/ 신세계한미/ 한미/ 수협/ 전북/ 광주/ 제주/ 조흥/ 신한(구 LG)</li>
							<li>국내전용카드/마스터/JCB 모두 포함</li>							
						</ul>
					</div>
				</div>
				
				<div class="txtsBox">
					<h4>안심클릭 인증방식</h4>
					<p class="txts">
						<em class="fc_black">카드사가 제공하는 팝업창</em>에서 선택가능<br>
						안심클릭, 공인인증 중 1개 선택 (안심클릭만으로 결제 가능)
					</p>
				</div>

				<div class="txtsBox">
					<h4>결제페이지 입력정보식</h4>
					<p class="txts">
						기존:카드번호, 유효기간, 주민번호, 카드비밀번호, 할부기간 입력<br>
						안심클릭 시행 후 : 카드번호, 할부기간만 입력
					</p>
				</div>
				
				<div class="txtsBox">
					<h4>등록방법</h4>
					<p class="txts">주문/결제 페이지에서 카드사에서 제공하는 팝업화면 통해 바로 등록 및 사용 가능<br>
						고객님이 소유한 신용카드의 카드사 홈페이지 통해 등록 가능<br>
						VISA카드를 가지고 계신 고객님들 중, 이미 VISA안심클릭을 등록해 사용하시고 계신 분은 별도의 안심 클릭 등록절차 없이<br>기존대로 결제가 가능하십니다.
					</p>
				</div>				
										
				
				<div class="txtsBox">
					<ul class="text-list02">					
						<li>추가적으로 더 궁금한 사항은 1:1 고객상담으로 문의 주시기 바랍니다.</li>
					</ul>
				</div>
				
			</div>
			
			<div class="btn_custom"><a href="javascript:;" class="btn sm gray">1:1 고객상담</a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>
<!--  //안전결제안내 Layer-->

<!--  안전결제안내 Layer-->
<article id="lypopCardPaysf" class="layer-popup lypopCardinfo lypopCardPaysf inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2>안전결제안내</h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold">안전결제(ISP)란?</h3>
				<p class="mgB10">쇼핑몰 이용 시 고객님의 카드번호/비밀번호 등을 입력함으로써 발생될 수 있는 개인정보 유출 및 카드도용 등의 문제점을 차단해 주는 지불수단으로, 인터넷 안전결제 (ISP)비밀번호만으로 안전한 전자상거래를 할 수 있는 서비스입니다.</p>
				<p class="mgB10">BC/국민카드/우리카드사는 모든 전자상거래에 인터넷 안전결제를 의무화 시켰습니다.</p>
				<p>30만원 이상 결제시는 공인인증서가 필요합니다. 공인인증서가 없으신 고객님 공인인증서를 발급 받아주시기 바랍니다.</p>
				
				<ul class="text-list01">
					<li>인터넷 안전결제(ISP) 이런 점이 좋습니다.
						<ul class="text-list02">
							<li>신용카드번호,비밀번호 등 개인정보 유출 및 카드 도용 방지</li>
							<li>인터넷 안전결제(ISP) 인증번호만으로 간단하고 편리한 신용결제</li>
							<li>인터넷 안전결제(ISP) 및 공인인증 확인으로 2중 보호가 되는 안전한 거래</li>							
						</ul>
					</li>
					<li>BC카드/국민카드/우리카드 고객님을 위한 인터넷 안전결제(ISP)
						<ul class="issueBox">
							<li><em>01.</em>주문/결제 시<br>결제수단 선택</li>
							<li class="fc_red">
								<span class="chkTxt posFt">BC카드/국민카드/우리카드<br>안전결제(ISP)선택 시</span>
								<em>02.</em>SP인증<br>비밀번호 신청/ 변경<br>비밀번호 입력/ 결제
								<span class="chkTxt posEnd">30만원 이상 결제 시<br>공유인증 비밀번호 입력</span>
							</li>
							<li><em>03.</em>확인하기</li>
							<li><em>04.</em>주문완료</li>
						</ul>
						
					</li>
					<li>BC카드/국민카드/우리카드 홈페이지에서 인터넷 안전결제(ISP)서비스 등록
						<div class="btnBanks">
							<a href="javascript:;" class="btn sm gray bankBc">BC카드 ISP 신청하기</a>
							<a href="javascript:;" class="btn sm gray bankKb">국민카드 ISP 신청하기</a>
							<a href="javascript:;" class="btn sm gray bankUri">우리카드 ISP 신청하기</a>
						</div>
					
					</li>
				</ul>							
				
				<ul class="text-list02">
					<li class="fc_gray">웹사이트를 통해 신청과 동시에 바로 사용이 가능합니다.</li>
					<li>추가적으로 더 궁금한 사항은 1:1 고객상담으로 문의 주시기 바랍니다.</li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="javascript:;" class="btn sm gray">1:1 고객상담</a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close inlayer">닫기</button>
		</div>
	</section>
</article>
<!--  // 안전결제안내 Layer -->


	
<script>
$(document).ready(function(){
	
	layerPopup.popupOpenNow('#lypopPayModify');	

  //radio click tab
    $('.orderPayOpt input[type=radio]').click(function(){
        $('.orderPayOptTab .orderPaytCont').css('display','none');
        $('.orderPayOptTab .orderPaytCont').eq($('.orderPayOpt input[type=radio]').index(this)).css('display','block');
    }); 

   

});
</script>
</body>
</html>