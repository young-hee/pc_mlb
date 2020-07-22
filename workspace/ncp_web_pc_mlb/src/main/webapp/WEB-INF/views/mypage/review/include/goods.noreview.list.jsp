<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<div class="tbst-div">
	<div class="mid fl">
		<span>전체</span> (<span class="text-color01">${totalCnt}</span>건)
	</div>
</div>
<hr class="hr-666" />
<div class="goodsreviewList">
	<ul>
		<c:forEach var="list" items="${reviewNoList}">
			<li>
				<div class="goodsreviewBox">
					<div class="goods-R-ImgBox">
						<a href="${list.godUrl}"><img src="${imageURL}${list.ordGod.lstImgUrl}/dims/resize/100x100" alt="${list.ordGod.godNm}" onerror="errorImgShow(this,'100');"></a>
					</div>
					<div class="goods-R-InfoBox">
						<div>
							<strong><%-- [<ncp:code code="${list.recomendSexNm}" />]  --%><c:out value="${list.ordGod.godNm}" /></strong>
						</div>
						<ul>
							<li><span><c:out value="${list.ordGod.colorCd}"/></span><span><c:out value="${list.ordGod.itmNm}"/></span></li>
							<li>주문일시 : <span><fmt:formatDate value="${list.ord.ordDt}" pattern="yyyy-MM-dd" /></span></li>
						</ul>
					</div>
					<div class="goods-R-BtnBox">
						<c:choose>
							<c:when test="${list.ordGod.cstmrPchDcsnYn eq 'N'}">
								<a href="javascript:;" class="btn sm gray" onclick="javascript:jsUpdateOrderDecision('${list.ordGod.ordNo}','${list.ordGod.ordGodTurn}')">구매확정하기</a>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${list.textYn eq 'Y'}">
										<a href="javascript:;'" class="btn sm gray disabled">텍스트리뷰 완료</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:;" class="btn sm gray" onclick="javascript:getLayerPopupAddReview('${list.ordGod.ordNo}','${list.ordGod.ordGodTurn}','${list.ordGod.godNo}', 'N')">텍스트리뷰 작성</a>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${list.photoYn eq 'Y'}">
										<a href="javascript:;'" class="btn sm gray disabled">포토리뷰 완료</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:;" class="btn sm gray" onclick="javascript:getLayerPopupAddReview('${list.ordGod.ordNo}','${list.ordGod.ordGodTurn}','${list.ordGod.godNo}', 'Y')">포토리뷰 작성</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</li>
		</c:forEach>
		<c:if test ="${empty reviewNoList}">
			<li class="list-noneinfo">
				<p>리뷰 작성이 가능한 상품이 없습니다.</p>
			</li>
		</c:if>
	</ul>
</div>

<c:if test ="${!empty reviewNoList}">
	<div class="page">
		<!-- 링크 없을때
		<span class="first">첫 페이지</span>
		<span class="prev">이전 페이지</span>
		-->
		<c:if test="${currentPage > 1 }">
			<a href="javascript:getNoReviewList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag}">
			<a href="javascript:getNoReviewList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
		</c:if>

		<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<strong title="현재 페이지">${i}</strong>
					</c:when>
					<c:otherwise>
						<a href="javascript:getNoReviewList('${i}')">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span>

		<c:if test="${nextFlag}">
			<a href="javascript:getNoReviewList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
		</c:if>
		<c:if test="${currentPage < totalPage}">
			<a href="javascript:getNoReviewList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
		</c:if>
		<!-- 링크 없을때
		<span class="next">다음 페이지</span>
		<span class="last">마지막 페이지</span>
		-->
	</div>
</c:if>
<%-- <%@ include file="../_inc/paging.jsp" %> --%>

