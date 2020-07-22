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
						${event.eventExt.pcHtml }
					</div>
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
													<%-- S : 2020-03-20 Family flag add --%>
                                                    <span class="flag_family">Family</span>
                                                    <%-- E : 2020-03-20 Family flag add --%>
													<span class="img">
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
