<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 
					    <div class="sns-share">
						<p class="sns-share-txt01"><spring:message code="display.event.share.text" /></p>
						<div class="btn-wrap">
							   <c:choose>
							       <c:when test="${eventEndYn eq 'Y' }">
							           	  <a  href="javascript:endEventAlert()"  class="btn-style02 btn-kakaostory"><spring:message code="display.event.text5" /></a>
							        	  <a  href="javascript:endEventAlert()" class="btn-style02 btn-facebook"><spring:message code="display.event.text6" /></a>
							       </c:when>
							       <c:otherwise>
							 <sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
							           	  <a  href="javascript:memberLogin()"  class="btn-style02 btn-kakaostory"><spring:message code="display.event.text5" /></a>
							        	  <a  href="javascript:memberLogin()" class="btn-style02 btn-facebook"><spring:message code="display.event.text6" /></a>
 							  </sec:authorize>
					 	       <sec:authorize access="hasAnyRole('ROLE_USER')">
							        	<a  href="javascript:insertSnsAjax('kakaostory')"  class="btn-style02 btn-kakaostory"><spring:message code="display.event.text5" /></a>
							        	<a  href="javascript:insertSnsAjax('facebook')" class="btn-style02 btn-facebook"><spring:message code="display.event.text6" /></a>								
                               </sec:authorize>  
							       </c:otherwise>
							   </c:choose>

	
					   <form id="snsForm" method="post">
					    <input type="hidden" name="snsSectCd"/>
						<input type="hidden" name="evtNo" value="${event.eventExt.evtNo }"/>
						<input type="hidden" name="snsImgUrl" value=""/>
						<input type="hidden" name="snsId" value=""/>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				      </form>
					  </div>
					</div>
<script type="text/javascript">
 
	var snsReplyEvtNo = "${event.eventExt.evtNo }";
	$(document).ready(function(){
 
	});
 
 	function checkReply(){
 		 
 		//종료된 이벤트
 		if('${eventEndYn}'=='Y'){
 			endEventAlert();
 			return;
 		}
 		if('${mbrNo}'==''){
 
 			  location.href = "/member/login/view?loginTarget="+document.URL;
  			return;
  		}
 
 	} 
 	
	function memberLogin(){
	  location.href = "/member/login/view?loginTarget="+encodeURI(document.URL);
	}
 
 
 	function insertSnsAjax(type){
 		checkReply();
		// 응모 대상 체크 : 대상 회원 체크
		if(!checkEventTarget(snsReplyEvtNo)){
			return;
		}
		// 응모 대상 체크 : 응모가능기간 체크
		if(!checkEventEnable(snsReplyEvtNo)){
			return;
		}
		jsShareSns(type);
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
					return;
				}
 			},
			error: function( pa_data, status, err ) {
				alert("error")
			}
 		});
 	}
 
	function endEventAlert(){
		 $('#eventtext').text("<spring:message code='display.event.text11' />");
		 layerPopup.popupOpenNow('#eventalert');
		return;
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