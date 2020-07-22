<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 
					
					<div class="sns-reply">
						<p class="sns-reply-txt01">
						<div id="kakaostory-share-button"></div>
						<spring:message code="display.event.notice" />
							<span>
							   <c:choose>
							       <c:when test="${eventEndYn eq 'Y' }">
							        	<a href="#" onclick="javascript:endEventAlert()"><img src="${_resourceURL}static/images/common/icon_kakaostory01.png" alt="카카오스토리"></a>
							        	<a href="#" onclick="javascript:endEventAlert()"><img src="${_resourceURL}static/images/common/icon_facebook01.png" alt="페이스북"></a>
							        		
							       </c:when>
							       <c:otherwise>
							        	<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
							           	  <a  href="javascript:memberLogin()"><img src="${_resourceURL}static/images/common/icon_kakaostory01.png" alt="카카오스토리"></a>
							        	  <a  href="javascript:memberLogin()"><img src="${_resourceURL}static/images/common/icon_facebook01.png" alt="페이스북"></a>
							         
							        	</sec:authorize>
					 	        	<sec:authorize access="hasAnyRole('ROLE_USER')">
							        	<a  href="javascript:loginSns('kakaostory')"><img src="${_resourceURL}static/images/common/icon_kakaostory01.png" alt="카카오스토리"></a>
							        	<a  href="javascript:loginSns('facebook')"><img src="${_resourceURL}static/images/common/icon_facebook01.png" alt="페이스북"></a>								
                             
                                  </sec:authorize>  
							       </c:otherwise>
							   </c:choose>
							  

							</span>
						</p>
						<form id="snsForm" method="post">
						<div class="sns-reply-input">
							<p class="sns-reply-txt02">- <spring:message code="display.event.warning" /></p>
							<div class="sns-reply-textarea">
						
						<input type="hidden" name="snsSectCd"/>
						<input type="hidden" name="evtNo" value="${event.eventExt.evtNo }"/>
						<input type="hidden" name="snsImgUrl" value=""/>
						<input type="hidden" name="snsId" value=""/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				 
					
		                 <textarea cols="30" rows="10"  placeholder="<spring:message code="display.event.reply" />" onclick="checkReply()" name="replyCont" id="replyCont"></textarea>
							   <c:choose>
							       <c:when test="${eventEndYn eq 'Y' }">
								       <button type="button" class="sns-reply-register" onclick="javascript:endEventAlert(); return false;" ><spring:message code="display.event.register" /></button>
							       </c:when>
							       <c:otherwise>
 					                  	<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
							          <a  href="javascript:memberLogin()"> <button type="button" class="sns-reply-register" ><spring:message code="display.event.register" /></button> </a>

							        	</sec:authorize>
							        	<sec:authorize access="hasAnyRole('ROLE_USER')">
					                       <button type="button" class="sns-reply-register" onclick="javascript:reply2(); return false;" ><spring:message code="display.event.register" /></button>
								
							        	</sec:authorize>  
							       </c:otherwise>
							   </c:choose>
 				      
						
						
							</div>
							<p class="sns-reply-txt03"><span  id="spanTextChar">0</span>/ 400 byte</p>
						</div>
							</form>
						<div class="sns-list">
 
					   </div>
 
<script type="text/javascript">
 
    var facebook = new facebook('${fbAppId}');
	var kakaostory = new kakaostory('${kakaoApiKey}');
	var naverBlog = new naverBlog();
	var _maxBytes = 400;
	var snsReplyEvtNo = "${event.eventExt.evtNo }";
	$(document).ready(function(){
		getPage("1");
		$("#replyCont").keyup(function(){
			var text = $("#replyCont").text();
 			if(text.length == 0){
 				text = $("#replyCont").val();
			}
 			var obj = getByte(text);
 			if(!checkMax(text)){
 				 $("#replyCont").val(obj.text);
 				return;
 	 		}
			$("#spanTextChar").html(obj.tcount);
		});
 
	});
 	function checkReply(){
 		 
 		//종료된 이벤트
 		if('${eventEndYn}'=='Y'){
 			endEventAlert();
 			return false;
 		}
 		if('${mbrNo}'==''){
 			  location.href = "/member/login/view?loginTarget="+document.URL;
  			return false;
  		}
 		else if($('[name=snsSectCd]').val()==''){
 			$('#eventtext').text("<spring:message code='display.event.text8' />");
 
 			layerPopup.popupOpenNow('#eventalert');
  			return false;
  		}
 		return true;
 	} 
	function memberLogin(){
	  location.href = "/member/login/view?loginTarget="+encodeURI(document.URL);
	}
 	function reply2(){
 		var evtNo = '${evtNo}';
 		var message = $('[name=replyCont]').val();
		if(!checkMax2(message)){
 			return false;
 		}
		// 응모 대상 체크 : 대상 회원 체크
		if(!checkEventTarget(snsReplyEvtNo)){
			return;
		}
		// 응모 대상 체크 : 응모가능기간 체크
		if(!checkEventEnable(snsReplyEvtNo)){
			return;
		}
		var link =  encodeURI(location.href);

		var hdivCheck = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-zA-Z0-9.,\-\_:=?!@^#$%*\[\]\(\)\+\;\{\}\`\\~\s]*$/;
    	
    	if (!hdivCheck.test($('[name=replyCont]').val())) {
 			$('#eventtext').text("<spring:message code='display.event.text9' />");
            layerPopup.popupOpenNow('#eventalert');
    		return false;
    	}     	
		//DB에 입력
		
		if(checkReply()){
	    	// SNS에 글쓰기
			if($('[name=snsSectCd]').val()=='FACEBUK'){
				facebook.postWithScrap(message, link); 
			}
			else if($('[name=snsSectCd]').val()=='KKO_STORY'){
	 
				kakaostory.postWithScrap(message, link);
			}else if($('[name=snsSectCd]').val()=='NAVERBLOG'){
	 
			    naverBlog.postWithScrap(message, link);
			} 
	 		insertReplyAjax();
		}
		

 	}

	function checkMax(text){
		if(getByte(text).tcount>_maxBytes){
 			$('#eventtext').text(_maxBytes+"<spring:message code='display.event.text10' />");
            layerPopup.popupOpenNow('#eventalert');
			return false;
		}else{
			return true;
		}
	}
	
	function checkMax2(text){
		if(getByte(text).tcount>_maxBytes){
 			$('#eventtext').text(_maxBytes+"<spring:message code='display.event.text10' />");
            layerPopup.popupOpenNow('#eventalert');
			return false;
		}else if(getByte(text).tcount == 0){
 			$('#eventtext').text("<spring:message code='display.event.text12' />");
            layerPopup.popupOpenNow('#eventalert');
			return false;
		}else{
			return true;
		}
	}
	
 	function insertReplyAjax(){
 		if(checkReply()){
 			$.ajax({
 	 			type : "POST",
 	 			dataType : "json",
 	 			url : "/event/insertReply.json",
 	 			data : $('#snsForm').serialize(),
 				beforeSend : function(request) {
 					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
 					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
 					request.setRequestHeader(csrfName, csrfToken);
 				},
 	 			success : function(data) {
 	 				
 					if(data.result == undefined){
 			 			$('#eventtext').text("<spring:message code='display.event.text9' />");
 			            layerPopup.popupOpenNow('#eventalert');
 						return false;
 					}
 					
 					if(data.result=='NOT_LOGIN'){
 						getPage("1");
 		 
 					}else{
 						getPage("1");
 					}
 	 			},
 				error: function( pa_data, status, err ) {
 					alert("error")
 				}
 	 		});	
 		}
 	}
 	function getPage(pageNo){

		$.ajax({
 			type : "GET",
 			/* dataType : "json", */
 			async : true,
 			url : '<c:url value="/event/snsReplyList"/>',
 			data : {'beginIndex':pageNo, 'evtNo':'${event.eventExt.evtNo}'},
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
 			success : function(data) {
 
 				$('.sns-list').html(data);
 				$('[name=replyCont]').val('');
 				$("#spanTextChar").html(0);
 			},
 			error: function( pa_data, status, err ) {
 				alert("error")
 	        }
 		});
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

	function endEventAlert(){
		 $('#eventtext').text("<spring:message code='display.event.text11' />");
		 layerPopup.popupOpenNow('#eventalert');
		return;
	}
 	// sns로그인
    function loginSns(type){
    	if(type=="facebook"){
    		var url = encodeURIComponent(document.URL+"?facebook=Y");
    		//location.href="https://www.facebook.com/v2.2/dialog/oauth?client_id=260987257781468&redirect_uri="+url ;
            facebook.login(function(){
 
    			// snsId 조회
        	   facebook.getName(function(id){
    			   
        		   if(!('undefined' == id || undefined == id || '' == id) ){
       			 	$('[name=snsSectCd]').val("FACEBUK");		   
       		       }
        		   
    			});
    		});
    	}else if(type=="kakaostory"){
    		kakaostory.login(function(){
 
    			// 프로필 정보 조회
                var profile = kakaostory.getProfile(function(profile){
         		   if(!('undefined' == profile.nickname || undefined == profile.nickname || '' == profile.nickname) ){
          			 	$('[name=snsSectCd]').val("KKO_STORY");		   
          		       }
                });

    		});
    	} 

    }
 	/**
	 * 이벤트 응모 가능 여부 체크 : 회원 대상 체크
	 */
	function checkEventTarget(evtNo){
		var success = false;
		$.ajax({
			type : "GET",
			//dataType : "json",
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
					 $('#eventtext').text("<spring:message code='event.detail.text2' />");
					 layerPopup.popupOpenNow('#eventalert');
				}else if("INVALID_DEVICE"==data.resultCode){
					 $('#eventtext').text(data.targetDevice+"<spring:message code='event.detail.text3' />");
					 layerPopup.popupOpenNow('#eventalert');
	 
				}else if("INVALID_MEMBER"==data.resultCode){
					 $('#eventtext').text(data.targetMember+"<spring:message code='event.detail.text3' />");
					 layerPopup.popupOpenNow('#eventalert');
	 
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
			//dataType : "json",
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
					 $('#eventtext').text("<spring:message code='event.detail.text2' />");
					 layerPopup.popupOpenNow('#eventalert');
				}else if("N"==data.resultCode){
					 $('#eventtext').text("<spring:message code='event.detail.text4' />");
					 layerPopup.popupOpenNow('#eventalert');
				}else if("Y"==data.resultCode){
					success = true;
				}
			},
			error: function( pa_data, status, err ) {
				alert("checkEventEnable error");
	        }
		});
		
		return success;
	}
</script>