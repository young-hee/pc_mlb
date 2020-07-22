<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<div class="item-listST">
	<ul>
		<c:forEach var="list" varStatus="status" items="${listTdGod }">
			<li>
				<div class="item-img">
					<c:if test="${list.godSaleSectCd eq 'SLDOUT'}">
						<span class="btn fill sm">SOLD OUT</span>
					</c:if>
					<c:if test="${list.godSaleSectCd eq 'SMTM_SLDOUT'}">
						<span class="btn fill sm">COMING SOON</span>
					</c:if>
					<a href="${list.godUrl}"><img src="${imageURL}${list.imgUrl}/dims/resize/242x242" alt="" onerror="errorImgShow(this,'400');"></a>
				</div>
				<div class="item-info">
					<p><a href="${list.godUrl}"><c:out value ="${list.godNm}" /></a></p>
					<p>
						<fmt:parseNumber value="${list.csmrPrc}" var="numCsmrPrc" scope="page" />
						<fmt:parseNumber value="${list.rtlPrc}" var="numRtlPrc" scope="page" />
						<c:choose>
							<c:when test="${numCsmrPrc < numRtlPrc}">
								<del><fmt:formatNumber>${list.rtlPrc}</fmt:formatNumber></del>
								<strong><fmt:formatNumber>${list.csmrPrc}</fmt:formatNumber></strong>
							</c:when>
							<c:otherwise>
								<strong><fmt:formatNumber>${list.rtlPrc}</fmt:formatNumber></strong>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<button type="button" class="itemIMG-del" onclick="deleteTodayGoodList('${list.todayGodSn}');"><spring:message code='mypage.todaygood.btn.goods.delete' /></button>
			</li>
		</c:forEach>

		<c:if test="${empty listTdGod}">
			<li class="list-noneinfo">
				<p>최근 본 상품이 없습니다.<br/>
				원하는 상품이 있는지, 놓치고 있는 상품은 없는지 구경해 보세요.</p>
			</li>
		</c:if>
	</ul>
</div>


<c:if test ="${!empty listTdGod}">
	<div class="page">
		<!-- 링크 없을때
		<span class="first">첫 페이지</span>
		<span class="prev">이전 페이지</span>
		-->
		<c:if test="${currentPage > 1 }">
			<a href="javascript:goTdgodList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag}">
			<a href="javascript:goTdgodList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
		</c:if>

		<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<strong title="현재 페이지">${i}</strong>
					</c:when>
					<c:otherwise>
						<a href="javascript:goTdgodList('${i}')">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span>

		<c:if test="${nextFlag}">
			<a href="javascript:goTdgodList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
		</c:if>
		<c:if test="${currentPage < totalPage}">
			<a href="javascript:goTdgodList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
		</c:if>
		<!-- 링크 없을때
		<span class="next">다음 페이지</span>
		<span class="last">마지막 페이지</span>
		-->
	</div>
</c:if>

<c:if test ="${empty listTdGod}">

</c:if>

<script>
	$(document).ready(function(){
		setTodaygoodCnt('${totalRow}');

		$("#pageNo").val('${currentPage}');
		$("#totPage").val('${totalPage}');
	});

	/* 최근본상품 삭제 */
	function deleteTodayGoodList(todayGodSn){
		if (confirm('삭제하시겠습니까?') == true) {
			// 삭제 대상 순번 셋팅
			$("#todayGodSn").val(todayGodSn);

			// 삭제구분 전체,단건
			$("#deleteAllYn").val("N");

			deleteTodayGoodListProc();
		}
	}

</script>