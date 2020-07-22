<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- //2018-05-16 -->
<div class="board-list">
	<table>
		<caption>마일리지 내역.</caption><!-- 2018-05-24 --> <%-- 마일리지 내역. --%>
		<colgroup>
			<col style="width:100px;">
			<col>
			<col style="width:150px;">
			<col style="width:150px;">
		</colgroup>
		<thead>
			<tr>
				<th scope="col">일자</th> <%-- 일자 --%>
				<th scope="col">내용</th> <%-- 내용 --%>
				<th scope="col">적립내용</th> <%-- 적립내용 --%>
				<th scope="col">사용내용</th> <%-- 사용내용 --%>
			</tr>
		</thead>
		<tbody>
			<c:if test ="${empty mileageList}">
				<tr>
					<td colspan="4" class="no-result">조회된 마일리지 내역이 없습니다.</td> <%-- 조회된 마일리지 내역이 없습니다. --%>
				</tr>
			</c:if>
			<c:forEach var="list" varStatus="status" items="${mileageList}">
				<tr>
					<td>${list.mileageDate}</td>
					<td class="tleft"><p>${list.mileageNm} <c:if test ="${!empty list.ordNo}">(${list.ordNo}) </c:if> </p></td>
					<%-- <td class="tleft"><p>${list.mileageNm} <c:if test ="${!empty list.ordNo}">(<a href="javascript:jsOrderInfo('${list.ordNo}');" class="order-number">${list.ordNo}</a>) </c:if> </p></td> --%>
					<td>
						<span class="text-color01">
							<fmt:formatNumber value="${list.mileageRestorePoint=='' ? 0:list.mileageRestorePoint}" type="number"  />
							<%-- <fmt:formatNumber value="${list.mileageRestorePoint}" type="number"  /> --%>
						</span>
					</td>
					<td>
						<fmt:formatNumber value="${list.mileageUsePoint=='' ? 0:list.mileageUsePoint}" type="number"  />
						<%-- <fmt:formatNumber value="${list.mileageUsePoint}" type="number"  /> --%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<c:if test ="${!empty mileageList}">
	<div class="page">
		<!-- 링크 없을때
		<span class="first">첫 페이지</span>
		<span class="prev">이전 페이지</span>
		-->
		<c:if test="${currentPage > 1 }">
			<a href="javascript:goMileageList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag }">
			<a href="javascript:goMileageList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
		</c:if>

		<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<strong title="현재 페이지">${i}</strong>
					</c:when>
					<c:otherwise>
						<a href="javascript:goMileageList('${i}')" >${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span>

		<c:if test="${nextFlag }">
			<a href="javascript:goMileageList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
		</c:if>
		<c:if test="${currentPage < totalPage}">
			<a href="javascript:goMileageList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
		</c:if>
		<!-- 링크 없을때
		<span class="next">다음 페이지</span>
		<span class="last">마지막 페이지</span>
		-->
	</div>
</c:if>

<script>
	$(document).ready(function(){
		setMileage(${mileageInfo.nowPoint}, ${mileageInfo.useTempPoint}, ${mileageInfo.eliminatePoint}, ${unityPntUseSumAmt} );

		//마일리지 건수
		setMileageCnt('${mileageCnt}');
	});

</script>