<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopCardPayCf" class="layer-popup lypopCardinfo lypopCardPayCf">
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


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopCardPayCf'); 
});
</script>
</body>
</html>