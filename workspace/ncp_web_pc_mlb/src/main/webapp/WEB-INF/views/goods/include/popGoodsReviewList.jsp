<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<c:choose>
	<c:when test="${not empty reviewRows}">	
		<ul class="lyreviewList">
			<c:forEach var="review" items="${reviewRows}" varStatus="status">
				<spring:eval var="splitGodEvlCont" expression="review.godEvlCont.split('\\n')" />
				<li id="revConList_${status.index}" class="rv_toggle">
					<span class="starBox sm star<c:out value="${review.qltyEvlScore}"/>">3</span>
					<span class="revListName"><c:out value="${review.mbrId}"/></span>
					<span class="revListOpt">(<spring:message code="goods.review.lbl.color"/>:${review.colorCd} / <spring:message code="goods.review.lbl.size"/>:${review.itmNm} )</span>
					
					<c:if test="${fn:length(review.godGodEvlAtchFileList) > 0}">
						<span class="icon-photo-review"><spring:message code="goods.review.lbl.image"/></span>
					</c:if>
					<span class="revListDate"><fmt:formatDate value="${review.godEvlWritngDt}" pattern="yyyy.MM.dd"/></span>
					<!-- button type="button" class="btnRevOpen btnRevToggle"><span>Open</span></button -->
					<a href="#revConList_${status.index}" class="btnRevOpen btnRevToggle">Open</a>
					
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
					<!-- button type="button" class="btnRevClose btnRevToggle"><span>Close</span></button -->
					<a href="#revConList_${status.index}" class="btnRevClose btnRevToggle">닫기</a>
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

<script>
	$(document).ready(function(){
		$('#reviewTotalCnt').text("("+$("#totCnt").val()+")");
		
		//button Click 숨겨진 내용보기
		$( ".btnRevToggle" ).click(function() {
			var fname=$(this).parent();	
			  if(fname.hasClass('on')){
				  fname.removeClass('on');
			  }else{
				  fname.addClass('on');
			  }
		});
		
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