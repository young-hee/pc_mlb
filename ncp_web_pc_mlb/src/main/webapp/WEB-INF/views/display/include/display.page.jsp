 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

				<c:if test="${!empty godList}">
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
                            <a  href="javascript:getPage('${i}');">
                               ${i} 
                            </a>
                        </c:if>
                    </c:forEach>
						</span>
                    <c:if test="${nextFlag}">
                        <a class="next" href="javascript:getPage('${displayNextPage}');" alt="다음페이지" >
                            <span>다음페이지</span>
                        </a>
                    </c:if>
                    <c:if test="${!lastFlag}">
                        <a class="last" href="javascript:getPage('${totalPage}');" alt="마지막페이지" >
                            <span>마지막페이지</span>
                        </a>
                    </c:if>
					 
					</div>
				</c:if>
				
<script type="text/javascript">
	var arry = new Array();
	$('.criteoGod').each(function(i) {
		if($(this).val() != ''){				
			arry.push($(this).val());
		}
		
	});
 
	window.criteo_q = window.criteo_q || [];
	window.criteo_q.push(
	        { event: "setAccount", account: 61384 },
	        
	        { event: "setSiteType", type: "d" },
	        { event: "viewList" ,item: arry}
	);
	fnf_appendCriteoScript();
	</script>				