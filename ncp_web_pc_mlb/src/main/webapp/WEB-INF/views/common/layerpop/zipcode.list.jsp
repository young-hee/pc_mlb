<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<c:choose>
	<c:when test="${not empty zipcodeList}">
		<div class="addressFindInner" id="zipView2">
			<p class="txt13-666"><spring:message code="common.popup.zipcode.lbl.txt7"/></p>
			<div class="board-list">
				<table summary="우편번호 찾기">
					<caption>우편번호 찾기</caption>
					<colgroup>
						<col width="70px">
						<col>
					</colgroup>
					<tbody>				
						<c:forEach var="list" varStatus="status" items="${zipcodeList}">										
						<tr>
							<td>${list.ordno}</td>
							<td>
								<a href="javascript:setZipcode('${list.ordno}', '${list.addrDoro}', 'RD_ADDR');">
									<span><spring:message code="common.popup.zipcode.lbl.txt8"/></span> <%-- 도로명 --%>
									<p>${list.addrDoro}</p>
								</a>
								<a href="javascript:setZipcode('${list.ordno}', '${list.addrDoro}', 'RD_ADDR');">
									<span><spring:message code="common.popup.zipcode.lbl.txt9"/></span> <%-- 지번 --%>
									<p>${list.addrJibun}</p>
								</a>									
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>	
			
			<div class="page">	
					<c:if test="${currentPage > 1 }">
						<a class="first" href="javascript:getSearchZipcode('1')" _onclick="return false;" title="첫 페이지">Prev</a>
					</c:if>
					<c:if test="${preFlag }">
						<a class="prev" href="javascript:getSearchZipcode('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
					</c:if>
					<span>
					<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
							<c:choose>
								<c:when test="${currentPage eq i}">
								<strong title="현재 페이지">${i}</strong>
								</c:when>
								<c:otherwise>
								<a href="javascript:getSearchZipcode('${i}')" >${i}</a>
								</c:otherwise>
							</c:choose>									
					</c:forEach>
					</span>
					<c:if test="${nextFlag }">
						<a class="next" href="javascript:getSearchZipcode('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
					</c:if>
					<c:if test="${currentPage < totalPage}">
					<a class="last" href="javascript:getSearchZipcode('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
					</c:if>                    
			</div>
					
		</div>			
	</c:when>
	<c:otherwise>
		<!-- 검색결과가 없는 경우 화면S -->
		<div class="addressFindInner" id="zipView1">
			<ul class="text-list02">
				<li><spring:message code="common.popup.zipcode.lbl.txt2"/></li> <%-- 도로명 + 건물번호 예)옥신타워 --%>
				<li><spring:message code="common.popup.zipcode.lbl.txt3"/></li> <%-- 읍/면/동/리 + 지번 예) 도곡로 117 --%> 
				<li><spring:message code="common.popup.zipcode.lbl.txt4"/></li> <%-- 건물명 예) 서오구 00아파트 --%>
				<li><spring:message code="common.popup.zipcode.lbl.txt5"/></li> <%-- 사서함+사서함번호 예) 광화문 우체국 사서함 45 --%>
			</ul>
			<div class="board-list">
				<table summary="우편번호 찾기">
					<caption>우편번호 찾기</caption>
					<colgroup>
						<col width="70px">
						<col>
					</colgroup>
					<tbody>	
						<tr>
							<td colspan="2" class="no-result"><spring:message code="common.popup.zipcode.lbl.txt6"/></td> <%-- 검색결과가 없습니다. --%>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- //검색결과가 없는 경우 화면E -->						
	</c:otherwise>
</c:choose>