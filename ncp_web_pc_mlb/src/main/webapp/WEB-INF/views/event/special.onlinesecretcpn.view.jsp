<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<!-- 컨텐츠 시작 -->
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">


				<!-- div class="pm_top">
					<span class="tit">${event.eventExt.evtNm}</span>
					<div class="info">
						<div class="fl">
							<a href="/event/promotionList?searchCategory=EVENT" class="btn fill sm btnAllEvent">ALL EVENT</a>
						</div>
						<div class="fr">
							<span class="date"><fmt:formatDate value="${event.eventExt.evtBegDt}" pattern="yyyy.MM.dd"/> ~ <fmt:formatDate value="${event.eventExt.evtEndDt}" pattern="yyyy.MM.dd"/></span>
							< %@ include file="/WEB-INF/views/plan/include/snsShare.jsp"% >
						</div>
					</div>
				</div -->

				<section class="pm_vis_area">
					<div class="vs_html">




<!-------------------- 기획전 소스 START -------------------->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title></title>
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700,900|Open+Sans&amp;display=swap&amp;subset=korean" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Roboto:400,900i&amp;display=swap" rel="stylesheet" />
<style type="text/css">
    body {padding:0;margin:0;font-family:'Noto Sans KR', 'Noto Sans Korean', HelveticaNeue,AppleSDGothicNeo,sans-serif}
    #contents{margin:0;width:100%;max-width:100%}
    .location-container {display:none}
    .visual-area {margin-top:0}
    .tab-type06, .title03, .item-list01 {max-width:1280px;margin:0 auto}
    .spot_event-wrap * {font-family:'Noto Sans KR', 'Noto Sans Korean', HelveticaNeue,AppleSDGothicNeo,sans-serif !important;}
    .spot_event-wrap p {margin:0;padding:0}
    .spot_event-wrap .section-a {position:relative;height:860px;background:url('https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-bg.jpg') no-repeat 50% 50%;background-size:cover;box-sizing:border-box}
    .hide_txt * {font-size:0;position:absolute;width:0;height:0;text-indent:-999em}
    .spot_event-wrap .section-a .spot_event-img {text-align:center;padding-top:125px}
    .spot_event-img img {vertical-align:top}
    .spot_event-btn {text-align:center;margin-top:-5px}
    .spot_event-btn a {display:inline-block}
    .spot_event-wrap .section-b {height:70px;background:#fff;text-align:center}
    .spot_event-wrap .section-b .link_area {display:inline-flex}
    .spot_event-wrap .section-b .link_area .link-item {position:relative;margin:20px 113px 0 83px;font-size:20px;color:#000;font-weight:400;text-decoration:none}
    .spot_event-wrap .section-b .link_area .link-item:before {content:'';position:absolute;left:0;right:0;bottom:-2px;height:1px;background-color:#000}
    .spot_event-wrap .section-b .link_area .link-item:after {content:'';position:absolute;right:-30px;top:50%;width:16px;height:12px;margin-top:-6px;background:url('https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/link_blt.png') no-repeat 0 0}
    .spot_event-wrap .section-c {background-color:#f5f5f5;height:200px;text-align:center;padding-top:45px;box-sizing:border-box}
    .spot_event-wrap .txt_type-a {color:#000;font-size:26px;font-weight:700;letter-spacing:5px}
    .spot_event-wrap .txt_type-b {color:#000;font-weight:300;font-size:20px;margin-top:10px;letter-spacing:.5px}
    .spot_event-wrap .txt_type-c {color:#8b8b8b;font-size:18px;letter-spacing:.25px;margin-top:18px}
    .spot_event-wrap .section-d {background-color:#6f6f6f;text-align:center;padding-bottom:75px}
    .spot_event-wrap .section-e {margin-top:10px;text-align:center}
    .spot_event-wrap .section-e .link_area {display:inline-block;white-space:nowrap;font-size:0}
    .spot_event-wrap .section-e .link_area .link-item {display:inline-block}
    .spot_event-wrap .section-e .link_area .link-item ~ .link-item {margin-left:10px}
    .spot_event-wrap .section-f {padding:58px 0 70px}
    .section-f .noti-area {width:1000px;margin:0 auto;overflow:hidden}
    .section-f .noti-area .noti-title {float:left;width:260px;padding-top:12px}
    .section-f .noti-area .noti-contents {overflow:hidden;margin:0;padding:0}
    .section-f .noti-area .noti-contents li {font-size:16px;line-height:35px;color:#a5a5a5;font-weight:400;list-style:none}
    @media (max-width:1580px){
        #container {padding:0}
    }
</style>
<div class="spot_event-wrap">
    <div class="section-a">
        <div class="spot_event-img"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-1.png" alt=""></div>
        <div class="hide_txt">
            <p>MLB/MLB KIDS 멤버십 회원만을 위한 봄맞이 깜짝 쇼핑 쿠폰</p>
            <p>기간 : 20.02.27 ~ 03.12</p>
            <p>10% COUPON</p>
        </div>
        <p class="spot_event-btn"><a href="javascript:downCoupon();"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_btn.jpg" alt="다운로드"></a></p>
    </div>
    <div class="section-b">
        <div class="link_area">
            <a href="/mypage/benefit/listCoupon" class="link-item">보유 쿠폰 전체 확인하기</a>
            <a href="/mypage/view" class="link-item">보유 마일리지/포인트</a>
        </div>
    </div>
    <div class="section-c">
        <p class="txt_type-a">무료 교환/반품 서비스</p>
        <p class="txt_type-b">주문건의 최초 1회에 한해 교환 or  반품 무료 신청이 가능합니다.</p>
        <p class="txt_type-c">기간 : 20.02.25 ~ 03.12</p>
    </div>
    <div class="section-d">
        <div class="spot_event-img"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-2.jpg" alt="판매랭킹 BEST 100"></div>
        <div class="hide_txt">
            <ol>
                <li>1위 빅볼청키 A</li>
                <li>2위 루키 볼캡</li>
                <li>3위 팝콘 베이스볼 점퍼</li>
            </ol>
        </div>
        <p class="spot_event-btn"><a href="/special/2038"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_btn2.jpg" alt="랭킹 전체보기"></a></p>
    </div>
    <div class="section-e">
        <div class="link_area">
            <a href="/special/2037" class="link-item"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-3.jpg" alt="변하지 않는 젊음의 아이콘 premium denim"></a>
            <a href="/special/2027" class="link-item"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-4.jpg" alt="시그니쳐 야구점퍼 컬렉션 baseball jumper"></a>
            <a href="/special/2026" class="link-item"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-5.jpg" alt="키즈 뉴어라이벌 kids new arrival"></a>
        </div>
    </div>
    <div class="section-f">
        <div class="noti-area">
            <div class="noti-title"><img src="https://static.mlb-korea.com/motioneye/2020/spring_offer/pc/spot_event-noti.jpg" alt="NOTICE"></div>
            <ul class="noti-contents" style="text-align: left;">
                <li>- 본 행사는 MLB 회원 대상 전용 이벤트입니다.</li>
                <li>- 로그인 후 다운로드 버튼 클릭 시 온라인 전용 10% 할인쿠폰 1매가 발급됩니다.</li>
                <li>- 다운 받은 10% 할인쿠폰은 주문 시 장바구니 쿠폰으로 적용 가능합니다. (아울렛 상품 및 일부 상품 제외)</li>
                <li>- 마이페이지 > 상품 반품 신청 시 사유를 ‘이벤트’로 선택해주셔야만 교환/반품 처리됩니다.</li>
                <li>- 본 이벤트는 MLB 내부 사정에 의해 변경 또는 중단 될 수 있습니다.</li>
            </ul>
        </div>
    </div>
</div>

<!-------------------- //기획전 소스 END -------------------->
				</section>
				
				<c:if test="${event.eventExt.evtTpCd eq 'SNS_REPLY'}">

					<section class="sns-reply">
	
						<div class="sns-reply-input">
							<p class="sns-reply-txt02"><spring:message code="display.event.reply.notice" /></p>
							<div class="sns-reply-textarea">
								<textarea id="replyCont" cols="30" rows="10" placeholder="<spring:message code="display.event.reply.placeholder" />"></textarea>
								<button type="button" class="sns-reply-register" id="replySave"><spring:message code="display.event.reply.save" /></button>
							</div>
							<p id="spanTextChar" class="sns-reply-txt03">0 / 400 byte</p>
						</div>
						
						<c:import url="/event/snsReplyList">
							<c:param name="beginIndex" value="1"/>
							<c:param name="evtNo" value="${event.eventExt.evtNo}"/>
						</c:import>
						
					</section>
				
				</c:if>
				
				<section class="tabs" id="tabsLink">
					<div class="tab-type06 tab">
						<ul>
						<c:forEach items="${event.sprtrExtList}" var="sprtr" varStatus="status">
							<c:if test="${!empty sprtr.godList}">
							<li>
								<a href="#pm_list_view_${sprtr.sprtrTurn}">
								<c:choose>
									<c:when test="${empty sprtr.sprtrImgFileNm}">
										${sprtr.sprtrNm}
									</c:when>
									<c:otherwise>
										<img src="${_image}${sprtr.sprtrImgFileUrl}/${sprtr.sprtrImgFileNm}" alt="${sprtr.sprtrImgAltrtvCont}"  >
									</c:otherwise>											
								</c:choose>
								</a>
							</li>
							</c:if>
						</c:forEach>
						</ul>
					</div>
				</section>
				
				<c:forEach items="${event.sprtrExtList}" var="sprtr" varStatus='i'>
				
					<c:if test="${!empty sprtr.godList}">
					<section class="pm_list_view" id="pm_list_view_${sprtr.sprtrTurn}">
						<div class="hd">${sprtr.sprtrNm}</div>
						<div class="ui_dp_list">
							<ul class="list">
								<c:forEach items="${sprtr.godList}" var="god" varStatus='i'>
								
									<li>
										<div class="item">
											<div class="thumb">
												<a href="${god.godUrl}">
													<c:if test="${god.godSaleSectCd eq'SLDOUT'}"><em class="sold_out">SOLD OUT</em></c:if>
													<c:if test="${god.godSaleSectCd eq'SMTM_SLDOUT'}"><em class="soon">COMING SOON</em></c:if>
													<span class="img">
													    <%-- S : 2020-03-20 Family flag add --%>
                                                        <span class="flag_family">Family</span>
                                                        <%-- E : 2020-03-20 Family flag add --%>
														<img src="${_image}${god.imgFrontUrl}/dims/resize/285x285" alt="${god.godNm}" onerror='errorImgShow(this, "285");'>
													</span>
												</a>
											</div>
											<div class="info">
												<div class="name">
													<c:if test="${_locale eq 'ko'}">
														<c:if test="${!empty god.tagNm}">
															<span style="color:${god.colorTagNm}">${god.tagNm}</span>
														</c:if>
													</c:if>
													<c:if test="${god.brndId eq 'I'}">
														<span>[KIDS]</span>
													</c:if>
														${god.godNm}
												</div>
												<div class="prc">
													<c:choose>
														<c:when test="${god.rtlPrc ne god.lastSalePrc}">
															<s class="s"><fmt:formatNumber value="${god.rtlPrc}" type="number"/><spring:message code="display.main.text1" /></s> 
															<em class="p"><fmt:formatNumber value="${god.lastSalePrc}" type="number"/><spring:message code="display.main.text1" /></em>		
														</c:when>
														<c:otherwise>
															<em class="p"><fmt:formatNumber value="${god.rtlPrc}" type="number"/><spring:message code="display.main.text1" /></em>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</li>
								
								</c:forEach>
							</ul>
						</div>
					</section>
					</c:if>
					
				</c:forEach>
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<script type="text/javascript">
	
	var evtNo = "${event.eventExt.evtNo }";
	
	function downCoupon() {
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
			alert("로그인 후 이용 가능합니다.");
			openLayerPopupForLogin();
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			goDownCoupon();
		</sec:authorize>
	}
	
	var clickFlag = false;
	function goDownCoupon(type) {
		var phoneMobile = type; //공유여부 
		var mbrNm ="";
		
		if(clickFlag) {
			return;
		}
		$.ajax({
			type : "POST",
			async : false, 
			url : "/special/event/downCoupon.json", 		
			data : {'evtNo': evtNo, 'mbr.mbrNm': mbrNm, 'cpnPrmNm':'PR202002240000617', 'evtDuplctYn':'N'},
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				if(data.result.resultCode =='SUCCESS'){
					alert("쿠폰이 발행되었습니다.");
				}
				else if(data.result.resultCode =='NO_MORE_CHANCE'){
					alert("이미 발급받았습니다.");
				} 
				else if(data.result.resultCode =='INVALID_APPLCN_DATE'){
					alert("이벤트 기간이 아닙니다.");
				}
				else if(data.result.resultCode =='ERROR'){ 
					alert("이벤트 참여 처리 중 오류가 발생하였습니다.");
				}
				
				clickFlag = false;
			},
			error: function( pa_data, status, err ) {
				alert("오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
				clickFlag = false;
	        }
		});
	}
	
		var _maxBytes = 400;
		var snsReplyEvtNo = "${event.eventExt.evtNo}";
		$(document).ready(function(){
			
			$("#replyCont").on("keyup", function(){
			
				var text = $("#replyCont").text();
	 			if(text.length == 0){
	 				text = $("#replyCont").val();
				}
	 			var obj = getByte(text);
	 			if(!checkMax(text)){
	 				 $("#replyCont").val(obj.text);
	 				return;
	 	 		}
				$("#spanTextChar").text(obj.tcount + " / 400 byte");
			
			}).on("focusin", function(){
				
				$(this).attr("placeholder", "");
				
			}).on("focusout", function(){
				
				$(this).attr("placeholder", '<spring:message code="display.event.reply.placeholder" />');
				
			});
			
			//댓글 등록
			$("#replySave").on("click", function(){
				
				if(checkReply()){
					
					insertReplyAjax();
				}
				
			});
			
			if(   $(window).outerHeight()  ==  $("#wrap").outerHeight()  ) {
				
				$("#tabsLink a[href*='#pm_list_view']").on("click",function(e){
					e.preventDefault();
					var thisContID = $(this).attr("href");
					var headHt = $(".header").outerHeight();
					var thisContTop = $(thisContID).offset().top + $("#wrap").scrollTop();
					$("#wrap").scrollTop( thisContTop - headHt );
				});
				
			} else {
				
				$("#tabsLink a[href*='#pm_list_view']").on("click",function(e){
					e.preventDefault();
					var thisContID = $(this).attr("href");
					var headHt = $(".header").outerHeight();
					var thisContTop = $(thisContID).offset().top + $("#wrap").scrollTop();
					$(window).scrollTop( thisContTop - headHt );
				});
				
			}
	 
		});
		
		function checkMax(text){
			if(getByte(text).tcount>_maxBytes){
				return false;
			}else{
				return true;
			}
		}
		
		function checkMax2(text){
			if(getByte(text).tcount>_maxBytes){
	 			alert(_maxBytes+"<spring:message code='display.event.reply.bytes' />");
				return false;
			}else if(getByte(text).tcount == 0){
	 			alert("<spring:message code='display.event.sns.comment.check' />");
				return false;
			}else{
				return true;
			}
		}
		
		/**
		 * 이벤트 응모 가능 여부 체크 : 회원 대상 체크
		 */
		function checkEventTarget(evtNo){
			var success = false;
			$.ajax({
				type : "GET",
				dataType : "json",
				async : false,
				url : '<c:url value="/event/checkEventTarget.json"/>', 		
				data : {'evtNo': evtNo},
				beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				},
				success : function(data) {
					if("NOT_LOGIN"==data.resultCode){
						alert("<spring:message code='display.event.reply.login' />");
					}else if("INVALID_DEVICE"==data.resultCode){
						alert(data.targetDevice+"<spring:message code='display.event.reply.detail' />");
					}else if("INVALID_MEMBER"==data.resultCode){
						alert(data.targetMember+"<spring:message code='display.event.reply.detail' />");
					}else{
						success = true;
					}
				},
				error: function( pa_data, status, err ) {
					alert("checkEventTarget error");
		        }
			});
			
			return success;
		}

		// 이벤트 응모 가능 여부 체크 : 응모가능 기간 체크
		function checkEventEnable(evtNo){
			var success = false;
			$.ajax({
				type : "GET",
				dataType : "json",
				async : false,
				url : '<c:url value="/event/checkEventEnable.json"/>', 		
				data : {'evtNo': evtNo},
				beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				},
				success : function(data) {
					if("NOT_LOGIN"==data.resultCode){
						alert("<spring:message code='display.event.reply.login' />");
					}
					
					if("N"==data.resultDateCode) {
						alert("<spring:message code='display.event.date.check' />");
					}else {
						if("N"==data.resultCode){
							alert("<spring:message code='display.event.sns.shere.check' />");
						}else if("Y"==data.resultCode){
							success = true;
						}
					}
					
				},
				error: function( pa_data, status, err ) {
					alert("checkEventEnable error");
		        }
			});
			
			return success;
		}
		
		function getByte(text){
			 
		    var length = $.trim(text).length;
		    var tcount = 0;
		    var obj = {};
		    var tempText ="";
		    for(var i = 0; i < length; i++){
		      var byteStr = text.charAt(i);
		      if(escape(byteStr).length > 4){
		        tcount += 3;
		      }else{
		        tcount += 1;
		      }
		      if(tcount <=400){
		    	  tempText += byteStr; 
		      }
		      
		    }
		    obj.tcount = tcount;
		    obj.text = tempText;
		    return obj;
		}
		
		function checkReply(){
	 		 
			var evtNo = '${evtNo}';
	 		var message = $('#replyCont').val();
	 		var link =  encodeURI(location.href);
			var hdivCheck = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-zA-Z0-9.,\-\_:=?!@^#$%*\[\]\(\)\+\;\{\}\`\\~\s]*$/;
	 		
			if(!checkMax2(message)){
				$('#replyCont').focus();
	 			return false;
	 		}
	 		// 응모 대상 체크 : 대상 회원 체크
			if(!checkEventTarget(snsReplyEvtNo)){
				return false;
			}
			// 응모 대상 체크 : 응모가능기간 체크
			if(!checkEventEnable(snsReplyEvtNo)){
				return false;
			}
			if (!hdivCheck.test($('#replyCont').val())) {
				$('#replyCont').focus();
	 			alert("<spring:message code='display.event.reply.char' />");
	    		return false;
	    	}  
	 		
	 		//종료된 이벤트
	 		if(${eventEndYn eq 'Y'}){
	 			alert("<spring:message code='dusokat.event.reply.end' />");
	 			return false;
	 		}
	 		if(${empty mbrNo}){
	 			location.href = "/member/login/view?loginTarget="+document.URL;
	  			return false;
	  		}
	 		
	 		return true;
	 	} 
		
		function insertReplyAjax() {
			
			var data = {};
			data["evtNo"] = "${event.eventExt.evtNo}";
			data["${_csrf.parameterName}"] = "${_csrf.token}";
			data["replyCont"] = $('#replyCont').val();
				
		 	$.ajax({
		 	 	type : "POST",
		 	 	dataType : "json",
		 	 	url : "/event/insertReply.json",
		 	 	data : data,
		 		beforeSend : function(request) {
		 			var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
		 			var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
		 			request.setRequestHeader(csrfName, csrfToken);
		 		},
		 	 	success : function(data) {
		 	 		
		 	 		if(data && data.result == "SUCCESS") {
		 	 			
		 	 			alert("<spring:message code='display.event.reply.sucess' />");
		 	 			getPage('1');
		 	 			
		 	 		}else if(data.result=='NOT_LOGIN'){
						
		 				alert("<spring:message code='display.event.reply.login' />");
		 				
		 			}else{
		 				
		 				alert("<spring:message code='display.event.reply.char' />");
		 			
		 			}
		 	 	},
		 		error: function( pa_data, status, err ) {
		 			alert("error")
		 		}
		 	 });	
		 	
		}
		
		function getPage(pageNo){

			$.ajax({
	 			type : "GET",
	 			/* dataType : "json", */
	 			async : true,
	 			url : '<c:url value="/event/snsReplyList"/>',
	 			data : { 'beginIndex' : pageNo, 'evtNo': snsReplyEvtNo },
				beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				},
	 			success : function(data) {
					
	 				if(data) {
	 					
	 					$('#replyCont').val('');
	 					$(".sns-reply-list").empty().append($(data).filter(".sns-reply-list").html());
	 					$(".page").empty().append($(data).filter(".page").html());

	 				}
	 				
	 			},
	 			error: function( pa_data, status, err ) {
	 				alert("error")
	 	        }
	 		});
	 	}
	
	</script>

