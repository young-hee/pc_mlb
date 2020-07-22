<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<div class="sns-reply-list"> 
		<p class="sns-reply-txt04"><spring:message code="display.event.reply.totCnt" /> <em>(${totalRow})</em></p>
		<ul>
			
			<c:forEach var="list" items="${list}">
				
				<li>
					<div class="sns-reply-id">
						 ${list.snsId}
					</div>
					<div class="sns-reply-cont">
						${list.replyCont}
					</div>
					<div class="sns-reply-date">
						<fmt:formatDate value="${list.regDt}" pattern="yyyy-MM-dd HH:mm:ss"/>
						<c:if test="${!empty mbrNo and mbrNo eq list.regtrId}">
							<button class="sns-reply-del">삭제</button>
							<input type="hidden" name="replySn" value="${list.replySn}"/>
						</c:if>
					</div>
				</li>
					
			</c:forEach>
			
		</ul>
	</div>
	<div class="page">
	
		<c:if test="${currentPage > 1 }">
		
			<a class="first" href="javascript:getPage('1');"  alt="첫페이지" > </a>
	 
		</c:if>
		<c:if test="${preFlag }">
		
			<a class="prev" href="javascript:getPage('${displayPrePage}');" alt="이전페이지" > </a>
	 
		</c:if>
		
		<span>
	    	
	    	<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
	        
	        	<c:if test="${i == currentPage}"><strong>${i}</strong></c:if>
	        	<c:if test="${i != currentPage}">
	            	<a  href="javascript:getPage('${i}');"  >
	               		${i} 
	            	</a>
	        	</c:if>
	    
	    	</c:forEach>
		
		</span>
	    
	    <c:if test="${nextFlag}">
	    
	        <a class="next" href="javascript:getPage('${displayNextPage}');" alt="다음페이지"  >
	            <span>다음페이지</span>
	        </a>
	  
	    </c:if>
	    <c:if test="${!lastFlag}">
	   
	        <a class="last" href="javascript:getPage('${totalPage}');" alt="마지막페이지" >
	            <span>마지막페이지</span>
	        </a>
	  
	    </c:if>
	 
	</div>
	
	<script type="text/javascript">
		//삭제버튼 클릭
		$(".sns-reply-list").on("click", ".sns-reply-del", function(e) {
			
			var replySn = $(this).next().val();
			
			if(confirm("<spring:message code='display.event.comment.delete.check' />")) {
				
				$.ajax({
				 	
					type : "POST",
			 	 	dataType : "json",
			 	 	url : "/event/updateReplyDspYn.json",
			 	 	data : { "replySn" : replySn },
			 		beforeSend : function(request) {
			 			var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
			 			var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
			 			request.setRequestHeader(csrfName, csrfToken);
			 		},
			 	 	success : function(data) {
			 	 		
			 	 		var pageNo = $(".page").find("strong").text();
			 	 		
			 	 		if(data) {
			 	 			
			 	 			if(data.result == "SUCCESS") {
				 	 			
			 	 				pageNo = $(".sns-reply-list").find("li").length > 1 ? pageNo : String(Number(pageNo) - 1);
			 	 				pageNo = pageNo === "0" ? "1" : pageNo;
			 	 				
			 	 				getPage(pageNo);				 	 			
			 	 				
				 	 		}else if(data.result=='NOT_LOGIN'){
								
				 				alert("<spring:message code='display.event.reply.login' />");
				 				
				 			}
			 	 			
			 	 		}
			 	 		
			 	 	},
			 		error: function( pa_data, status, err ) {
			 			alert("error")
			 		}
			 	
				});
				
			}
			
		} );
	
	</script>