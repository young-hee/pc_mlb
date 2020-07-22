<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<div class="contain cs" id="contain">
		<div class="container">
		
			<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false" />
			<main class="contents agreementList-wrap" id="contents">
				
				<div class="d_tab02">
					
					<!-- tab S -->
					<ul class="tab-coupon tab-type03 tab-deps02">
						<li class="d_tab02_select"><a href="javascript:;" id="agreement1"><spring:message code="common.terms.ttl3"/></a></li><%-- 서비스이용약관 --%>
						<li class="d_tab02_select"><a href="javascript:;" id="agreement2"><spring:message code="common.terms.ttl4"/></a></li><%-- 개인정보처리방침 --%>
					</ul>
					<!--//tab E -->
					
					<!-- 서비스이용약관 -->
					<div class="tab-privacy-cont d_tab02_cont" style="display:block;">
						<section class="agree-section-content">
							<c:out value="${onlineSiteUsefStplat.stplatCont}" escapeXml="false"/>
						</section>
					</div>
					<!-- //서비스이용약관 -->
					
					<!-- 개인정보처리방침 -->
					<div class="tab-privacy-cont d_tab02_cont d_tab03">
						<div class="agree-section-content"  id="agreeV0">
							<c:out value="${psnlInfoTrtmntPolicy.stplatCont}" escapeXml="false"/>
						</div>
						<c:if test="${not empty sysStplatHistExtendsList}">
							<c:forEach var="list" items="${sysStplatHistExtendsList}" varStatus="status">	
								<div class="agree-section-content d_tab03_cont" id="agreeV${status.count}" style="display: none;">
									<c:out value="${list.stplatCont}" escapeXml="false"/>
								</div>
							</c:forEach>
						</c:if>
						
						<!-- 개인정보처리방침 select S-->
						<div class="prev-privacy-wrap">
							<div class="select-style01 d_select">
								<button type="button" class="d_select_sel" style="width:590px;"><span><spring:message code="common.terms.tt20" text="개인정보처리방침 보기"/></span></button>
								<ul>
									<c:if test="${not empty sysStplatHistExtendsList}">
										<li class="d_tab03_select"><a href="#agreeV0"><c:out value="${psnlInfoTrtmntPolicy.stplatDscr}"/></a></li>
										<c:forEach var="list" items="${sysStplatHistExtendsList}" varStatus="status">		
											<li class="d_tab03_select"><a href="#agreeV${status.count}">(<fmt:formatDate value="${list.histDt}" pattern="yyyy-MM-dd"/><spring:message code="common.agreement.lbl.txt2"/>) <c:out value="${list.stplatDscr}"/></a></li>
										</c:forEach>
									</c:if>									
								</ul>
							</div>
						</div>
						<!-- //개인정보처리방침 select E -->
						
					</div>
					<!-- //개인정보처리방침 -->
					
				</div>

			</main>
			
		</div>
	</div>

<script>

$(document).ready(function(){
	if("${agreementNo}" == "1"){
		$("#agreement1").click();
	}else{
		$("#agreement2").click();
	}
	
	//전체스크롤제외 박스스크롤이동
	$(".agree-link a").click(function(e){
		e.preventDefault();
        var hrefNm = $(this).attr("href");
        var object = $(hrefNm);
        if(object){
        	var posTop = $(this).closest(".agree-section-content");
        	posTop.animate({scrollTop: object.offset().top - posTop.offset().top}, 100);
        }
    });
});

/* function changeTermsConditions(index){
	$("#agreementCont").html($("#stplatHisCont"+index).html());
} */
</script>
