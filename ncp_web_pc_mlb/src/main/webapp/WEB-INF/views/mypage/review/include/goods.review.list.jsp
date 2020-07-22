<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<input type="hidden" id="review_dateStart" name="dateStart" value="${dateStart}"/>
<input type="hidden" id="review_dateEnd" name="dateEnd" value="${dateEnd}"/>
<input type="hidden" id="review_srchMonth" name="srchMonth" value="${srchMonth}"/>

<div class="tbst-div">
	<div class="mid fl">
		<span>전체</span> (<span class="text-color01">${totalCnt}</span>건)
	</div>
</div>
<hr class="hr-666" />
<div class="goodsreviewView d_accordion">
	<ul>
		<c:forEach var="list" items="${reviewList}">
            <spring:eval var="splitGodEvlCont" expression="list.godGodEvl.godEvlCont.split('\\n')" />
			<li>
				<div class="goodsreviewViewBox">
					<div class="goods-R-ImgBox">
						<a href="${list.godUrl}"><img src="${imageURL}${list.ordGod.lstImgUrl}/dims/resize/100x100" alt="${list.ordGod.godNm}" onerror="errorImgShow(this,'100');"></a>
					</div>
					<div class="goods-R-InfoBox">
						<div>
							<strong><%-- [<ncp:code code="${list.recomendSexNm}" />]  --%><c:out value="${list.ordGod.godNm}"/></strong>
							<strong><span><c:out value="${list.ordGod.colorCd}"/></span><span><c:out value="${list.ordGod.itmNm}"/></span></strong>
						</div>
						<ul>
							<li>
								<div class="review-grade sm">
									<strong class="grade-type0${list.godGodEvl.qltyEvlScore+1}"><p>작성일 : <span><fmt:formatDate value="${list.godGodEvl.godEvlWritngDt}" pattern="yyyy-MM-dd HH:mm"/></span></p></strong>
								</div>
							</li>
							<li>
								<p>
									<c:if test="${list.photoYn eq 'Y'}">
										<span class="imgplusIcon"></span>[포토 리뷰]
									</c:if>
									<c:if test="${list.photoYn ne 'Y'}">
										[텍스트 리뷰]
									</c:if>
									<c:out value="${splitGodEvlCont[0]}" />
								</p>
								<div class="d_accordion_cont">
									<p><c:forEach items="${splitGodEvlCont}" var="line" varStatus="status" begin="1">
                                            ${line}<c:if test="${not status.last}"><br></c:if>
                                        </c:forEach></p>
									<div class="clearfix">
										<div class="fl">
											<ul>
												<li>
													<strong>사이즈</strong><span>
														<c:if test="${list.godGodEvl.sizeEvlCd eq 'SIZE_EVL_SML'}">작아요</c:if> <%-- 작아요 --%>
														<c:if test="${list.godGodEvl.sizeEvlCd eq 'SIZE_EVL_MID'}">딱 맞아요</c:if> <%-- 딱 맞아요 --%>
														<c:if test="${list.godGodEvl.sizeEvlCd eq 'SIZE_EVL_LAG'}">커요</c:if> <%-- 커요 --%>
													</span>
												</li>
												<li>
													<strong>컬러</strong><span>
														<c:if test="${list.godGodEvl.colorEvlCd eq 'COLOR_EVL_BRGT'}">밝아요</c:if> <%-- 밝아요 --%>
														<c:if test="${list.godGodEvl.colorEvlCd eq 'COLOR_EVL_SAMENSS'}">화면과 같아요</c:if> <%-- 화면과 같아요 --%>
														<c:if test="${list.godGodEvl.colorEvlCd eq 'COLOR_EVL_DARK'}">어두워요</c:if> <%-- 어두워요 --%>
													</span>
												</li>
											</ul>
										</div>
										<div class="fr">
											<a href="javascript:;" class="btn sm gray" onclick="javascript:getLayerPopupEditReview('${list.ordGod.ordNo}','${list.ordGod.ordGodTurn}','${list.ordGod.godNo}', '${list.godGodEvl.godEvlTurn}' ,'${list.photoYn}')">수정</span></a>
											<a href="javascript:;" class="btn sm gray" onclick="javascript:deleteReview('${list.ordGod.ordNo}', '${list.ordGod.godNo}', '${list.godGodEvl.godEvlTurn}');">삭제</span></a>
										</div>
									</div>
									<c:if test="${list.photoYn eq 'Y'}">
										<ul class="imgplusMore">
											<c:forEach var="atchFileList" items="${list.godGodEvlAtchFiles}">
												<li>
													<a href="${imageURL}${atchFileList.atchFileUrl}" target="_blank"><img src="${imageURL}${atchFileList.atchFileUrl}" alt=""></a>
												</li>
											</c:forEach>
										</ul>
									</c:if>
								</div>
							</li>
						</ul>
					</div>
					<div class="goods-AC-BtnBox d_accordion_select">
						<a href="javascript:;">보기</a>
					</div>
				</div>
			</li>
		</c:forEach>
		<c:if test="${empty reviewList}">
			<li class="list-noneinfo">
				<p>작성하신 리뷰가 없습니다.</p>
			</li>
		</c:if>
	</ul>
</div>

<c:if test ="${!empty reviewList}">
	<div class="page">
		<!-- 링크 없을때
		<span class="first">첫 페이지</span>
		<span class="prev">이전 페이지</span>
		-->
		<c:if test="${currentPage > 1 }">
			<a href="javascript:getReviewList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
		</c:if>
		<c:if test="${preFlag}">
			<a href="javascript:getReviewList('${displayPrePage}')" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
		</c:if>

		<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<strong title="현재 페이지">${i}</strong>
					</c:when>
					<c:otherwise>
						<a href="javascript:getReviewList('${i}')">${i}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</span>

		<c:if test="${nextFlag}">
			<a href="javascript:getReviewList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
		</c:if>
		<c:if test="${currentPage < totalPage}">
			<a href="javascript:getReviewList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
		</c:if>
		<!-- 링크 없을때
		<span class="next">다음 페이지</span>
		<span class="last">마지막 페이지</span>
		-->
	</div>
</c:if>