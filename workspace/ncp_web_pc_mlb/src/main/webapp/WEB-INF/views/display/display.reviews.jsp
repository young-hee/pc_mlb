<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
			<div class="location-container">
				<div class="location-contents">
					<h2 class="title01"><spring:message code="display.review.text1" /></h2>
					<p class="location">
						<span>Home</span><strong title="현재 위치"><spring:message code="display.review.text2" /></strong>
					</p>
				</div>
			</div>
 <acrticle id="container"  >
<section id="contents">

					<div class="review-info">
						<p class="review-txt01"><spring:message code="display.review.text3"  arguments="${totalRow}"/></p>
						<p class="review-txt02"> <spring:message code="display.review.text4" /></p>
					</div>
					<c:if test="${fn:length(best) > 0 }">
					<h3 class="title08">BEST REVIEW</h3>
					<div id="bestReview" class="item-list08">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<c:forEach items="${best}" var="bst" >
								<li class="swiper-slide">
									<div class="item-img"><a href="#layerPopupText" class="d_layer_open"><span><img src="${imageURL }${bst.imgFrontUrl}" alt="${bst.godNm }"></span></a></div>
									<div class="item-info">
										<p class="txt01"><a href="#layerPopupText" class="d_layer_open">${bst.godEvlCont }</a></p>
										<p class="txt02">${bst.mbrId }/ <fmt:formatDate value="${bst.regDt }" pattern="yyyy-MM-dd"/></p>
									</div>
			                      		 <div class="reviewSub"   style="display: none;">
			                      			<div class="review-detail-cont">
			                      				<div class="review-detail-img">
			                      					<a href="${bst.godUrl }"><img src="${imageURL }${bst.imgFrontUrl}" alt="${bst.godNm }" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_940x1253)%>');"></a>
			                      				</div>
			                      				<div class="review-detail-info">
			                      					<dl class="review-detail-txt01">
			                      						<dt><spring:message code="display.review.text5" /> : </dt>
			                      						<dd>${bst.mbrId }</dd>
			                      						<dt><spring:message code="display.review.text6" /> : </dt>
			                      						<dd><fmt:formatDate value="${bst.regDt }" pattern="yyyy-MM-dd"/></dd>
			                      					</dl>
			                      					<p class="review-detail-txt02">
			                      						<a href="${bst.godUrl }">${bst.godNm }</a>
			                      					</p>
			                      					<p class="review-detail-txt03">
			                      			         <c:choose>
													  <c:when test="${bst.rtlPrc > bst.lastSalePrc}">
												     <del><fmt:formatNumber value="${bst.rtlPrc }" type="number"/><spring:message code="display.main.text1" /></del><strong><fmt:formatNumber value="${bst.lastSalePrc }" type="number"/><spring:message code="display.main.text1" /></strong> 
													  </c:when>
													  <c:otherwise>
													  <strong><fmt:formatNumber value="${bst.lastSalePrc }" type="number"/><spring:message code="display.main.text1" /></strong> 
													  </c:otherwise>
													</c:choose>
 
			                      					</p>
			                      					<p class="review-detail-txt04">${bst.colorCd } / ${bst.itmNm } / ${bst.ordQty }<spring:message code="display.review.text7" /></p>
			                      				</div>
			                      				<div class="review-detail-rating">
			                      					<p class="review-detail-txt05"><strong class="grade-type0${bst.qltyEvlScore+1}"><spring:message code="display.review.text8" /><em>${bst.qltyEvlScore }<spring:message code="display.review.text9" /></em></strong></p>
			                      					<dl class="review-detail-txt06">
			                      						<dt><spring:message code="display.review.text10" /></dt>
			                      						<dd>${bst.sizeEvlCd }</dd>
			                      						<dt><spring:message code="display.review.text11" /></dt>
			                      						<dd>${bst.colorEvlCd }</dd>
			                      					</dl>
			                      				</div>
			                      			</div>
			                      			<div class="review-detail-txt07">
			                      				${bst.godEvlCont }
			                      				<c:forEach items="${bst.godGodEvlAtchFileList}" var="file" >
			                      				<img src="${imageURL }${file.atchFileUrl}" alt="${file.atchFileNm}">
			                      				</c:forEach>
			    
			                      			</div>
			                      	 </div>
			                      						
								</li>									
							 </c:forEach>

							</ul>
						</div>
						<button type="button" class="item-list-prev">이전</button>
						<button type="button" class="item-list-next">다음</button>
					</div>
					<script>
						var bestReview = new Swiper('#bestReview .swiper-container', {
							slidesPerGroup: 3,
							slidesPerView: 'auto',
							spaceBetween: 40,
							observer: true,
							observeParents: true,
							navigation: {
								nextEl: '#bestReview .item-list-next',
								prevEl: '#bestReview .item-list-prev'
							}
						});
					</script>
					</c:if>
				  <ul id="ctgryPage" class="tab-type04 tab-type04-n6 d_tab">
				   <li ><a href="javascript_:void(0);" onclick="return false;" id="DXM"><spring:message code="display.review.text13" /></a></li>
                  <c:forEach items="${bestCategory}" var="category" >
                  <li><a href="javascript_:void(0);" onclick="return false;" id="${category.dspCtgryNo}">${category.dspCtgryNm }</a></li>
                  </c:forEach>
                  </ul>
					<div class="item-list08 item-list08-type02">
						<ul>
							
							     <c:forEach items="${reviews}" var="god" >
							<li>
									<div class="item-img"><a href="#layerPopupText" class="d_layer_open"><span><img src="${imageURL }${god.imgFrontUrl}" alt="${god.godNm }"></span></a></div>
									<div class="item-info">
										<p class="txt01"><a href="#layerPopupText" class="d_layer_open">${god.godEvlCont }</a></p>
										<p class="txt02">${god.mbrId }/ <fmt:formatDate value="${god.regDt }" pattern="yyyy-MM-dd"/></p>
									</div>
			                      		 <div class="reviewSub" style="display: none;">
			                      			<div class="review-detail-cont">
			                      				<div class="review-detail-img">
			                      					<a href="${god.godUrl }"><img src="${imageURL }${god.imgFrontUrl}" alt="${god.godNm }" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_940x1253)%>');"></a>
			                      				</div>
			                      				<div class="review-detail-info">
			                      					<dl class="review-detail-txt01">
			                      						<dt><spring:message code="display.review.text5" /> : </dt>
			                      						<dd>${god.mbrId }</dd>
			                      						<dt><spring:message code="display.review.text6" /> : </dt>
			                      						<dd><fmt:formatDate value="${god.regDt }" pattern="yyyy-MM-dd"/></dd>
			                      					</dl>
			                      					<p class="review-detail-txt02">
			                      						<a href="${god.godUrl }">${god.godNm }</a>
			                      					</p>
			                      					<p class="review-detail-txt03">
			                      			         <c:choose>
													  <c:when test="${god.rtlPrc > god.lastSalePrc}">
												     <del><fmt:formatNumber value="${god.rtlPrc }" type="number"/><spring:message code="display.main.text1" /></del><strong><fmt:formatNumber value="${god.lastSalePrc }" type="number"/><spring:message code="display.main.text1" /></strong> 
													  </c:when>
													  <c:otherwise>
													  <strong><fmt:formatNumber value="${god.lastSalePrc }" type="number"/><spring:message code="display.main.text1" /></strong> 
													  </c:otherwise>
													</c:choose>
			                 
			                      					</p>
			                      					<p class="review-detail-txt04">${god.colorCd } / ${god.itmNm } / ${god.ordQty }<spring:message code="display.review.text7" /></p>
			                      				</div>
			                      				<div class="review-detail-rating">
			                      					<p class="review-detail-txt05"><strong class="grade-type0${god.qltyEvlScore+1}"><spring:message code="display.review.text8" /><em>${god.qltyEvlScore }<spring:message code="display.review.text9" /></em></strong></p>
			                      					<dl class="review-detail-txt06">
			                      						<dt><spring:message code="display.review.text10" /></dt>
			                      						<dd>${god.sizeEvlCd }</dd>
			                      						<dt><spring:message code="display.review.text11" /></dt>
			                      						<dd>${god.colorEvlCd }</dd>
			                      					</dl>
			                      				</div>
			                      			</div>
			                      			<div class="review-detail-txt07">
			                      				${god.godEvlCont }
			                      				<c:forEach items="${god.godGodEvlAtchFileList}" var="file" >
			                      				<img src="${imageURL }${file.atchFileUrl}" alt="${file.atchFileNm}">
			                      				</c:forEach>
			    
			                      			</div>
			                      	 </div>
							</li>
								</c:forEach>
						
 
						</ul>
					</div>
   					<div class="page">
						<c:if test="${currentPage > 1 }">
							<a class="first" href="javascript:getPage('1');"   alt="첫페이지"  > </a>
					 
						</c:if>
							<c:if test="${preFlag }">
						<a class="prev" href="#none" onclick="return false;" alt="이전페이지" id="${displayPrePage}"> </a>
					 
						</c:if>
							<span>
                    <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                        <c:if test="${i == currentPage}"><strong>${i}</strong></c:if>
                        <c:if test="${i != currentPage}">
                            <a href="javascript:getPage('${i}');">
                               ${i} 
                            </a>
                        </c:if>
                    </c:forEach>
						</span>
                    <c:if test="${nextFlag}">
                        <a class="next"  href="javascript:getPage('${displayNextPage}');"  alt="다음페이지"  >
                            <span>다음페이지</span>
                        </a>
                    </c:if>
                    <c:if test="${!lastFlag}">
                        <a class="last" href="javascript:getPage('${totalPage}');"  alt="마지막페이지" >
                            <span>마지막페이지</span>
                        </a>
                    </c:if>
					</div>
        </section>
    </acrticle>

		<article id="layerPopupText" class="layer-popup">
			<section class="layer-popup-cont layer-popup-scroll01" tabindex="0">
				<h2><spring:message code="display.review.text12" /></h2>
				<div class="layer-popup-wrap01">
					<div class="popup-review-detail">

					</div>
					<div class="btn-wrap">
						<a href="#" class="btn-style02 d_layer_close"><spring:message code="display.main.text11" /></a>
					</div>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code="display.main.text11" /></button>
				</div>
			</section>
		</article>
<form id="searchPromotionForm" method="get">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="hidden" name="pageNo" id="pageNo">
  <input type="hidden" name="dspCtgryNo" id="dspCtgryNo"  value="${dspCtgryScFrDTO.dspCtgryNo }">
</form>

<script type="text/javascript">
$('#searchPromotionForm').attr('action', "/display/reviewsList");

$(document).ready(function() {
 	$(".d_layer_open").click(function(){
		 $('.popup-review-detail').html('');
		 $('.popup-review-detail').html($(this).closest('li').find('.reviewSub').html());

	});		
    $("#ctgryPage a").click(function(){	
    	$('#searchPromotionForm').find('#dspCtgryNo').val($(this).attr('id'));
    	var frm = $('#searchPromotionForm');		
 
    	frm.submit();
    
    });
 
      $("#ctgryPage a").each(function(){	
    	 if($(this).attr('id') == '${dspCtgryScFrDTO.dspCtgryNo}'){
    		 $(this).closest('li').addClass('on');
    	 } 
 
    });
   if('${dspCtgryScFrDTO.dspCtgryNo}' ==''){
	   $("#ctgryPage").find('#DXM').closest('li').addClass('on');
   }
});
 
	function getPage(pageNo){
	    $('#pageNo').val(pageNo);
		
		var frm = $('#searchPromotionForm');
 
	    frm.submit();
 
 	}
</script>
