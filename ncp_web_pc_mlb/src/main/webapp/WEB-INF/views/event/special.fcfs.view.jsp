<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

			<acrticle id="container" class="contents-pm-type01">
				<section id="contents">
					<div class="pm-event-wrap">
						<div class="pm-event-box01">
							<div class="pm-event-cnt01">
								<span class="pm-event-logo01"><img src="${_resourceURL}static/images/promotion/img_promotion_11.png" alt="Event 01"></span>
								<span class="pm-event-logo02"><img src="${_resourceURL}static/images/promotion/img_promotion_12.png" alt="For Your New Adventure"></span>
								<img src="${_resourceURL}static/images/promotion/img_promotion_01.png" alt="디스커버리 리뉴얼 오픈기념 이벤트 NEW DISCOVERY For Your New Adventure">
								<img src="${_resourceURL}static/images/promotion/img_promotion_02.png" alt="신규가입회원을 위한 3,000만원 블라인드 당첨 이벤트">
								<ul class="pm-event-product-list">
									<li>매일 1명 전동 킥보드 ‘나인봇 ES2’</li>
									<li>매일 1명 여행지원바우처 50만원권 </li>
									<li>매일 196명 배스킨라빈스 싱글레귤러</li>
									<li>매일 1명 애플‘에어팟’</li>
									<li>매일1명 디스커버리 캐리어 ‘프레스티지 알파’</li>
								</ul>
							</div>
							<div class="pm-event-cnt02">
								<img src="${_resourceURL}static/images/promotion/img_promotion_04.png" alt="새롭게 태어난 디스커버리의 세계로 오신 당신을 환영합니다. 리뉴얼 오픈 이벤트 기간동안 신규회원 2800분께 총 3천만원 상당의 특별한 감사 선물을 드립니다">
							</div>
							<img src="${_resourceURL}static/images/promotion/img_promotion_05.png" alt="뉴 디스커버러 매일 200명 선착순 블라인드 당첨">
						</div>
						<%-- DEXC3-83 요청으로 주석처리 --%>
<!-- 						<div class="pm-event-box02"> -->
<!-- 							<div class="pm-event-cnt01"> -->
<%-- 								<img src="${_resourceURL}static/images/promotion/img_promotion_06.png" alt="NEXT EVENT  09.10 ~ 09.16 구매회원 1,000만 포인트 적립 행사"> --%>
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="pm-event-box03">
							<ul class="pm-event-product-list02">
								<li>이벤트 기간 : 09.03 ~ 09.16 (매일 오전 10시 응모 오픈)</li>
								<li>이벤트 대상 : 이벤트 기간동안 신규가입회원 매일 오전 10시 선착순 200명(SMS/수신동의자 한함)</li>
								<li>당첨자 공지 : 9월 20일 공지사항 게시판</li>
							</ul>
							<div class="pm-event-btn">
								<a href="#" id="joinEvent"><img src="${_resourceURL}static/images/promotion/btn_promotion_01.png" alt="응모하기"></a>
							</div>
							<div class="pm-event-cnt01">
								<img src="${_resourceURL}static/images/promotion/img_promotion_08.png" alt="매일 오전 10시 응모하기 오픈!">
							</div>
							<div class="pm-event-btn">
								<span>

							        	<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
							        	 <a href="/member/join/view"><img src="${_resourceURL}static/images/promotion/btn_promotion_02.png" alt="지금 신규가입 하기"></a>
 									     <a  href=javascript:memberLogin()><img src="${_resourceURL}static/images/promotion/btn_promotion_03.png" alt="SMS/E-Mail 수신동의 하기"></a>
							        	</sec:authorize>
					 	        	<sec:authorize access="hasAnyRole('ROLE_USER')">
					 	        	  <a href="javascript:memberJoin()"><img src="${_resourceURL}static/images/promotion/btn_promotion_02.png" alt="지금 신규가입 하기"></a>
		 							  <a href="/mypage/member/modifyMemberView?targetPath=/special/event/${event.eventExt.evtNo }/RenewalEvent01"><img src="${_resourceURL}static/images/promotion/btn_promotion_03.png" alt="SMS/E-Mail 수신동의 하기"></a>

                                  </sec:authorize>  
								</span>
							</div>
							<ul class="pm-event-product-list03">
								<li><strong>매일 1st 응모자</strong>신규가입 회원 중 매일 첫번째 응모자 디스커버리 신상 캐리어 ‘프레스티지 알파’ 22인치</li>
								<li><strong>매일 50th 응모자</strong>신규가입 회원 중 매일 50번째 응모자 애플 ‘에어팟’</li>
								<li><strong>매일 100th 응모자</strong>신규가입 회원 중 매일 100번째 응모자 여행지원 바우처 50만원</li>
								<li><strong>매일 200th 응모자</strong>신규가입 회원 중 매일 200번째 응모자 전동 킥보드 ‘나인봇 ES2’</li>
								<li><strong>매일 196명 응모자</strong>신규가입 회원 중 매일 196명 응모자 베스킨라빈스 싱귤레귤러 증정</li>
							</ul>
							<ul class="pm-event-product-list04">
								<li>본 행사는 디스커버리 온라인 전용 행사로 이벤트 기간동안 마케팅 수신동의한 신규가입회원에 한해 혜택이 지급되며 회원 ID당 1회 응모 가능합니다.</li>
								<li>매일 1~200번째 응모자는 응모와 동시에 당첨 결과를 확인할 수 있으며, 응모 후 탈퇴/ 마케팅 수신동의 미동의 및 부정한 방법으로 당첨을 조작하는 경우에는 당첨자가 될 수 없습니다.</li>
								<li>스마트 캐리어, 애플 에어팟, 여행지원 바우처 및 전동 킥보드 당첨 시 경품 시가의 22%에 해당하는 제세공과금은 당첨자 부담이며 미납부 시에는 당첨이 취소됩니다.</li>
								<li>본 이벤트는 디스커버리 내부 사정에 의해 변경 또는 중단 될 수 있습니다.</li>
							</ul>
						</div>
					</div>
				</section>
			</acrticle>
<script type="text/javascript">
 
	var evtNo = "${event.eventExt.evtNo }";
	$(document).ready(function(){
 
		$("#joinEvent").click(function(){
	 
			joinEvent(evtNo);
		});
		
		$("#eventalert .d_layer_close").click(function(){
			//location.reload();
		});
	});
 
	function memberLogin(){
	  location.href = "/member/login/view?loginTarget="+encodeURI(document.URL);
	}
	
	function memberJoin(){
		$('#eventtext').text("이미 회원이십니다");
		layerPopup.popupOpenNow('#eventalert');
	}
  
	// 이벤트 응모 가능 여부 체크 : 응모가능 기간 체크
	function joinEvent(evtNo){
		var success = false;
		$.ajax({
			type : "GET",
			//dataType : "json",
			async : false,
			url : '<c:url value="/special/event/joinEvent.json"/>', 		
			data : {'evtNo': evtNo},
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				if(data.result.resultCode =='NOT_LOGIN'){
					layerPopup.popupOpenNow('#layerPopupNotLogin');
				}else{
					$('#eventtext').text(data.result.resultMsg);
					layerPopup.popupOpenNow('#eventalert');
				}
			},
			error: function( pa_data, status, err ) {
				alert("checkEventEnable error");
	        }
		});
		
		return success;
	}
</script>
		<article id="layerPopupNotLogin" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>선착순 이벤트</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03">이벤트 기간에 신규화원으로 가입하신 회원만 로그인 후 참여 가능합니다. <br> 회원가입/로그인 하시겠습니까?</p>
					 
				</div>
				<div class="btn-wrap03">
					<a href="/member/join/view" class="btn-style02">회원가입하러 가기</a><a href="javascript:memberLogin()" class="btn-style02">로그인 하기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
	    <article id="eventalert" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>선착순 이벤트</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt" id="eventtext"> </p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn-style02 d_layer_close">닫기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
