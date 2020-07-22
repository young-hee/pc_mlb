<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<c:if test ="${mypageMainYn ne 'Y'}">
<div class="board-list">
</c:if>
	<!-- 2018-05-24 -->
	<table>
		<caption>1:1 문의 번호, 문의유형, 제목, 작성일, 답변여부</caption>
		<colgroup>
			<col style="width:100px;">
			<col style="width:100px;">
			<col>
			<col style="width:100px;">
			<col style="width:100px;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">문의유형</th>
				<th scope="col">제목</th>
				<th scope="col">작성일</th>
				<th scope="col">답변여부</th>
			</tr>
		</thead>
		<tbody>
			<c:if test ="${empty inquiryList}"> 
			<tr>
				<td colspan="5" class="no-result">상담내역이 없습니다.</td>
			</tr>
			</c:if>
			
			<c:if test ="${!empty inquiryList}">
				<c:forEach var="list" varStatus="status" items="${inquiryList }"> 
					<ncp:code var ="cdTpCd" code ="${list.csoMtmInq.inqTpCd}" />
					<ncp:code var ="cdStatCd" code ="${list.csoMtmInq.ansStatCd}" />
					
				<tr>
					<%-- <td><c:out value="${list.csoMtmInq.mtmInqSn}"/></td> --%>
					<td><c:out value="${list.rowNum}"/></td>
					<td><c:out value="${cdTpCd.cdNm}"/></td>
					<td class="tleft"><a href="#" onclick="javascript:goInquiryDetail('<c:out value ="${list.csoMtmInq.mtmInqSn}" />')"><c:out value="${list.csoMtmInq.inqSj}"/></a></td>
					<td><c:out value ="${list.inqDt}" /></td>
					<td><c:out value ="${cdStatCd.cdNm}"/></td>
				</tr>
				
				</c:forEach>
			</c:if>
			
		</tbody>
	</table>
	<!-- //2018-05-24 -->
<c:if test ="${mypageMainYn ne 'Y'}">
</div>
</c:if>


<c:if test ="${mypageMainYn ne 'Y'}">

<c:if test ="${!empty inquiryList}"> 
	<div class="page">	
			<c:if test="${currentPage > 1 }">
				<a class="first" href="javascript:goInquiryList('1')" _onclick="return false;" title="첫 페이지">Prev</a>
			</c:if>
			<c:if test="${preFlag }">
				<a class="prev" href="javascript:goInquiryList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
			</c:if>
			<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
					<c:choose>
						<c:when test="${currentPage eq i}">
						<span class="on"><strong title="현재 페이지">${i}</strong></span>
						</c:when>
						<c:otherwise>
						<a href="javascript:goInquiryList('${i}')" ><span title="현재 페이지">${i}</span></a>
						</c:otherwise>
					</c:choose>									
			</c:forEach>
			</span>
			<c:if test="${nextFlag }">
				<a class="next" href="javascript:goInquiryList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
			</c:if>
			<c:if test="${currentPage < totalPage}">
			<a class="last" href="javascript:goInquiryList('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
			</c:if>                    
	</div>
</c:if>
<c:if test ="${empty inquiryList}"> </c:if>

</c:if>

<script type="text/javascript" >
	
	$(document).ready(function(){
		 setInquiryCnt('${totalRow}'); 
	});
	
	/* 건수 입력 */
	function setInquiryCnt(inquiryCnt){
		$("#inquiryCnt").empty();
		$("#inquiryCnt").append(inquiryCnt);
	}
	
	/* 1:1 고객상담 상세조회 */
	function goInquiryDetail(obj){
        /* 
		$("input[name='srchMtmInqSn']").val(obj);
        
        $("#srchForm").attr("action","/mypage/inquiry/detail/"+obj);
        $("#srchForm").submit();
         */
        var param = "";
        param += "?" + "dspCtgryNo=DMPA01A02A05";	// 메뉴 유지를 위한 데이터
        param += "&" + "currentCtgryDpthCd=3";		// 메뉴 유지를 위한 데이터
        param += "&" + "ctgrySectCd=GNRL_CTGRY";	// 메뉴 유지를 위한 데이터
        param += "&" + "ctgryNoDpth1=DMPA01";		// 메뉴 유지를 위한 데이터
   	    param += "&" + "ctgryNoDpth2=DMPA01A02";	// 메뉴 유지를 위한 데이터
   	    param += "&" + "ctgryNoDpth3=DMPA01A02A05";	// 메뉴 유지를 위한 데이터
   		location.href ='/mypage/inquiry/detail/'+obj + param;
    }
	
</script>