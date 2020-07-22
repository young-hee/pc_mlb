<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
			<acrticle id="container" class="contents-pm-type01">
				<section id="contents">
					<div class="pm-event-wrap02">
						<div class="pm-event-box01">
							<div class="pm-event-cnt-box-01"><img src="${_resourceURL}static/images/promotion/img_promotion02_01.png" alt="리뉴얼 오픈기념 이벤트 02"></div>
							<div class="pm-event-cnt-box-02"><img src="${_resourceURL}static/images/promotion/img_promotion02_02.png" alt="For Your New Adventure For Your New Adventure"></div>
						</div>
						<div class="pm-event-box02">
							<div class="pm-event-cnt-box-01">
								<img src="${_resourceURL}static/images/promotion/img_promotion02_03.png?v=${_version}" alt="구매회원을 위한 10,000,000 포인트 적립 이벤트 기간 : 9. 17 ~ 9. 30 ( 포인트 일괄 지급 10. 10 )">
							</div>
							<div class="pm-event-cnt-box-02">
								<strong><img src="${_resourceURL}static/images/promotion/img_promotion02_04.png" alt="첫 구매 선착순 응모자 500명 즉석 포인트 당첨"></strong>
								<ul>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_05.png" alt="500,000Point 첫구매 회원 중 10st 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_06.png" alt="1,000,000Point 첫구매 회원 중 100th 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_07.png" alt="2,000,000Point 첫구매 회원 중 500th 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_08.png" alt="7,000Point 첫구매 회원 선착순 응모자 497명"></li>
								</ul>
								<div class="pm-event-btn-wrap"><a href="#" id="joinEvent"><img src="${_resourceURL}static/images/promotion/btn_promotion02_01.png" alt="응모하기"></a></div>
							</div>
							<div class="pm-event-cnt-box-02">
								<strong><img src="${_resourceURL}static/images/promotion/img_promotion02_09.png" alt="재구매 재구매 회원 중 10st 응모자"></strong>
								<ul>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_10.png" alt="재구매 회원 중 10st 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_11.png" alt="재구매 회원 중 100th 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_12.png" alt="1,000,000Point 재구매 회원 중 500th 응모자"></li>
									<li><img src="${_resourceURL}static/images/promotion/img_promotion02_13.png" alt="5,000Point 재구매 회원 선착순 응모자 497명"></li>
								</ul>
								<div class="pm-event-btn-wrap"><a href="#" id="joinEvent2"><img src="${_resourceURL}static/images/promotion/btn_promotion02_02.png" alt="응모하기"></a></div>
							</div>
							<ul class="pm-event-list">
								<li>본 행사는 디스커버리 온라인 전용 행사로 이벤트 기간동안 모든 구매회원 대상(구매확정)으로 혜택이 지급됩니다. </li>
								<li>1~500번째 첫구매 응모자 및 1~500번째 재구매 응모자는 응모와 동시에 당첨 결과를 확인할 수 있으며, 주문취소 및 반품 시 최종 당첨자에서 제외될 수 있습니다.</li>
								<li>첫구매 및 재구매 당첨은 중복 가능하나 부정한 방법으로 당첨을 조작하는 경우에는 당첨자가 될 수 없습니다.</li>
							    <li>본 이벤트는 당사 내부 사정에 의해 내용 변경 및 조기 종료될 수 있습니다.</li>
							</ul>
							<div class="pm-event-banner01">
								<a href="#"><img src="${_resourceURL}static/images/promotion/banner_promotion_01.png" alt="18F/W DOWN EARLY BIRD UP TO 20% OFF"></a>
							</div>
						</div>
					</div>
				</section>
			</acrticle>
<script type="text/javascript">
 
	var evtNo = "${event.eventExt.evtNo }";
	$(document).ready(function(){
 
		$("#joinEvent").click(function(){
	 
			joinEvent(evtNo,'<c:url value="/special/event/joinEventRenewal01.json"/>');
		});
		$("#joinEvent2").click(function(){
			 
			joinEvent(evtNo,'<c:url value="/special/event/joinEventRenewal02.json"/>');
		});
		$("#eventalert .d_layer_close").click(function(){
		//	location.reload();
		});
	});
 
	function memberLogin(){
	  location.href = "/member/login/view?loginTarget="+encodeURI(document.URL);
	}
 
  
	// 이벤트 응모 가능 여부 체크 : 응모가능 기간 체크
	function joinEvent(evtNo,url){
		var success = false;
		$.ajax({
			type : "GET",
			//dataType : "json",
			async : false,
			url : url, 		
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
				<h2>구매 이벤트</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03">로그인 후 참여 가능합니다. 로그인 하시겠습니까?</p>
					 
				</div>
				<div class="btn-wrap03">
			    <a href="javascript:memberLogin()" class="btn-style02">로그인 하기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
	    <article id="eventalert" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>구매 이벤트</h2>
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
