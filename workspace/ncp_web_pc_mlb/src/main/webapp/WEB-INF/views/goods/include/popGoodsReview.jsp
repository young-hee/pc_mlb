﻿<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<c:set var="reviewAverageScoreTotalCnt" value="0" />
<c:set var="reviewAverageScoreAvgQltyScore" value="0" />
<c:if test="${not empty reviewAverageScore.totalCnt}">
	<c:set var="reviewAverageScoreTotalCnt" value="${reviewAverageScore.totalCnt }" />
</c:if>
<input type="hidden" name="totCnt" id="totCnt" value="${reviewAverageScoreTotalCnt}"/>

<!--  리뷰 별점  -->
<div class="lyReviewTotal">
	<dl class="reviewTotal">
		<dt><spring:message code="goods.review.lbl.total"/><em> <c:out value="${reviewAverageScoreTotalCnt}"/></em></dt>
		<dd>
			<c:if test="${not empty reviewAverageScore.avgQltyScore}">
				<c:set var="reviewAverageScoreAvgQltyScore" value="${reviewAverageScore.avgQltyScore }" />
			</c:if>	
			<!--  class로 star0 ~ star5 까지 변경 가능 -->
			<div class="starBox star<c:out value="${reviewAverageScoreAvgQltyScore}"/>"><em>(${reviewAverageScoreAvgQltyScore})</em></div>			
		</dd>
	</dl>				
</div>	
<!-- // 리뷰 별점  -->	

<!--  리뷰 리스트  -->
<div class="lyReviewDetail">

	<input type="hidden" id="reviewFormPhotoYn" value="">
	<input type="hidden" id="reviewFormSort" value="">

	<div class="d_tab02">
		<ul class="tab-type01" id="ul_all_photo_text">
			<li class="d_tab02_select on">
				<a href="#" onclick="javascript:getReviewList('1','','A'); return false;"><spring:message code="goods.common.lbl.all"/>
					<em>(<c:if test="${empty reviewAverageScore.totalCnt }">0</c:if><c:if test="${not empty reviewAverageScore.totalCnt }"><c:out value="${reviewAverageScore.totalCnt}"/></c:if>)</em>
				</a>
			</li>
			<li class="d_tab02_select">
				<a href="#" onclick="javascript:getReviewList('1','${goodsReviewSearchDTO.sortFlag}','N'); return false;"><spring:message code="goods.review.lbl.text"/>
					<em>(<c:out value="${reviewAverageScore.totalCnt-reviewAverageScore.atchFileCnt}"/>)</em>
				</a>
			</li>
			<li class="d_tab02_select">
				<a href="#" onclick="javascript:getReviewList('1','${goodsReviewSearchDTO.sortFlag}','Y'); return false;"><spring:message code="goods.review.lbl.image"/>
					<em>(<c:if test="${empty reviewAverageScore.atchFileCnt }">0</c:if><c:if test="${not empty reviewAverageScore.atchFileCnt }"><c:out value="${reviewAverageScore.atchFileCnt}"/></c:if>)</em>						
				</a>
			</li>
		</ul>
		<!-- 전체 -->
		<div class="d_tab02_cont" style="display:block;" id="searchGoodsReviewList">
			<c:choose>
				<c:when test="${not empty reviewRows}">	
					<ul class="lyreviewList">
						<c:forEach var="review" items="${reviewRows}" varStatus="status">
							<spring:eval var="splitGodEvlCont" expression="review.godEvlCont.split('\\n')" />
							<li id="revConAll_${status.index}" class="rv_toggle">
								<span class="starBox sm star<c:out value="${review.qltyEvlScore}"/>"><c:out value="${review.qltyEvlScore}"/></span>
								<span class="revListName"><c:out value="${review.mbrId}"/></span>
								<span class="revListOpt">(<spring:message code="goods.review.lbl.color"/>:${review.colorCd} / <spring:message code="goods.review.lbl.size"/>:${review.itmNm} )</span>
								<c:if test="${fn:length(review.godGodEvlAtchFileList) > 0}">
									<span class="icon-photo-review"><spring:message code="goods.review.lbl.image"/></span>
								</c:if>
								<span class="revListDate"><fmt:formatDate value="${review.godEvlWritngDt}" pattern="yyyy.MM.dd"/></span>
								<a href="#revConAll_${status.index}" class="btnRevOpen btnRevToggle">Open</a>
								
								<p class="revListTxts">
									<c:forEach items="${splitGodEvlCont}" var="line" varStatus="status1" >
                                        ${line}<c:if test="${not status1.last}"><br></c:if>
                                    </c:forEach>
								</p>
									
								<div class="revListPhoto">
									<dl>
										<c:if test="${not empty review.sizeEvlCd }">	
											<dt><spring:message code="goods.option.lbl.size"/></dt>
											<dd>
												 ${review.sizeEvlCd}
											</dd> 
										</c:if>
										<c:if test="${not empty review.colorEvlCd }">	
											<dt><spring:message code="goods.option.lbl.color1"/></dt>
											<dd>
												${review.colorEvlCd}
											</dd>
										</c:if>
									</dl>
									<c:forEach var="atchFileList" items="${review.godGodEvlAtchFileList}">
										<div class="revPhotos"><img src="${imageURL}${atchFileList.atchFileUrl}" style="width:100%; height:auto;"></div>										
									</c:forEach>									
								</div>
								<a href="#revConAll_${status.index}" class="btnRevClose btnRevToggle">닫기</a>
							</li>	
						</c:forEach>
					</ul>
					<div class="page">						
						<c:if test="${currentPage > 1 }">
							<a class="first" href="#none" onclick="javascript:getReviewList('1','${goodsReviewSearchDTO.sortFlag}','${goodsReviewSearchDTO.photoReviewYn}'); return false;" title="첫 페이지">첫 페이지</a>
						</c:if>
						<c:if test="${preFlag }">
							<a class="prev" href="#none" onclick="javascript:getReviewList('${displayPrePage}','${goodsReviewSearchDTO.sortFlag}','${goodsReviewSearchDTO.photoReviewYn}'); return false;" title="이전 페이지">이전 페이지</a>
						</c:if>
						<span>
							<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
									<c:choose>
										<c:when test="${currentPage eq i}">
											<span class="on"><strong title="현재 페이지">${i}</strong></span>
										</c:when>
										<c:otherwise>
											<a href="#none" onclick="javascript:getReviewList('${i}','${goodsReviewSearchDTO.sortFlag}','${goodsReviewSearchDTO.photoReviewYn}'); return false;"><span title="현재 페이지">${i}</span></a>
										</c:otherwise>
									</c:choose>									
							</c:forEach>
						</span>
						<c:if test="${nextFlag }">
							<a class="next" href="#none" onclick="javascript:getReviewList('${displayNextPage}','${goodsReviewSearchDTO.sortFlag}','${goodsReviewSearchDTO.photoReviewYn}'); return false;" title="다음 페이지">다음 페이지</a>
						</c:if>
						<c:if test="${currentPage < totalPage}">
							<a class="last" href="#none" onclick="javascript:getReviewList('${totalPage}','${goodsReviewSearchDTO.sortFlag}','${goodsReviewSearchDTO.photoReviewYn}'); return false;" title="마지막 페이지">마지막 페이지</a>
						</c:if>                    
					</div>
				
				</c:when>
				<c:otherwise>
					<ul class="product-review-list d_accordion">
						<li><center><spring:message code="goods.review.lbl.empty"/></center></li>
					</ul>
				</c:otherwise>
			</c:choose>	
		</div>
	</div>
</div>
<!--  //리뷰 리스트  -->

<script>
	$(document).ready(function(){
		$('#reviewTotalCnt').text("("+$("#totCnt").val()+")");
		
		//button Click 숨겨진 내용보기
		$( ".btnRevToggle" ).on('click', function() {
			var fname=$(this).parent();	
			  if(fname.hasClass('on')){
				  fname.removeClass('on');
			  }else{
				  fname.addClass('on');
			  }
		});
		$('#searchGoodsReviewList').on('click', '.revListTxts', function(){
			$(this).parent().find('.btnRevToggle')[0].click();
		})

		
		//이미지경로 레이어팝업 띄우기
		$(".revPhotos a").click(function() {		
			$(this).find('img').each(function(){
				var imgUrl = $(this).attr('src');
				var imgView=$('#lypopGoodsRvPhoto .rvPhotoView img');		
				imgView.attr('src',imgUrl);			
			});
		});	
		
	});
</script>