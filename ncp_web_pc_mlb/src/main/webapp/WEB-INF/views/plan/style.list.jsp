<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
			 
			 
		<acrticle id="container" class="contents-type01">
				<section id="contents">
					<div class="contents-type01-box01">
						<c:set var="bnrCount" value="0"/>
						<div class="category-visual" id="categoryVisual">
							<div class="swiper-container">
								<ul class="swiper-wrapper">
							 <c:forEach items="${contt.cnrBnrConttList}" var="bnr"  varStatus="i">
								<li class="swiper-slide"><a  href="javascript_:void(0);" onclick="return false;" conttCnncUrl="${bnr.conttCnncUrl }"  outptTpCd="${bnr.outptTpCd }"><img src="${imageURL}${bnr.imgFileUrl}/${bnr.imgFileNm }" alt="${bnr.imgNm }"></a></li>
								<c:set var="bnrCount" value="${bnrCount+1 }"/>
							 </c:forEach>
								</ul>
							</div>
							<div class="category-visual-pagination category-visual-pagination-type02">
								<div class="category-visual-page-inner">
									<div class="category-visual-num"></div>
									<div class="category-visual-control">
										<button type="button" class="category-visual-stop">정지</button>
										<button type="button" class="category-visual-play" style="display: none;">시작</button> 
									</div>
								</div>
							</div>
							<div class="category-visual-prev-next category-prev-next-type02">
								<button type="button" class="category-visual-prev">이전</button>
								<button type="button" class="category-visual-next">다음</button>
							</div>
						</div>
						<script>
						var categoryVisual = new Swiper('#categoryVisual .swiper-container', {
							slidesPerView: 1,
							observer: true,
							observeParents: true,
							pagination: {
								el: '#categoryVisual .category-visual-pagination .category-visual-num',
								clickable: true,
								renderBullet: function (index, className) {
									return '<span class="' + className + '">' + (index + 1) + '</span>';
								}
							},
							navigation: {
								nextEl: '#categoryVisual .category-visual-next',
								prevEl: '#categoryVisual .category-visual-prev'
							},
							autoplay: {
								delay: 7000,
								disableOnInteraction: false
							},
							loop: true
						});
						var bnrCount = "${bnrCount}";
					    if(bnrCount == null || parseInt(bnrCount) <= 1) {
							$('#categoryVisual').find('.category-visual-prev-next').hide();
					    };
						$('#categoryVisual .category-visual-play').click(function() {
							$(this).hide();
							$('#categoryVisual .category-visual-stop').show();
						});
						$('#categoryVisual .category-visual-stop').click(function() {
							$(this).hide();
							$('#categoryVisual .category-visual-play').show();
						});
						</script>
					</div>
					<div class="contents-type01-box03">
 
							<div class="item-list05 item-list05-type01">
							<ul>
							 <c:forEach items="${evt}" var="evt" varStatus="i">
								<li>
									<div class="item-list05-img"><a href="${evt.pcUrl }"><img src="${imageURL }${evt.pcImgFileUrl }" alt="이미지1"></a></div>
									<p class="item-list05-txt01"><a href="${evt.pcUrl }">${evt.evtNm }</a></p>
									<p class="item-list05-txt02">${evt.pcImgExpsrTxt1Cont }</p>
									 <c:if test="${not empty evt.pcImgExpsrTxt2Cont }">
										<p class="item-list05-txt03">${evt.pcImgExpsrTxt2Cont }</p>
									</c:if>
								</li>
 
							 </c:forEach>

							</ul>
						</div>
   					<div class="page">
						<c:if test="${currentPage > 1 }">
							<a class="first" href="javascript:getPage('1');" alt="첫페이지" > </a>
					 
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
                        <a class="next" href="javascript:getPage('${displayNextPage}');" alt="다음페이지" >
                            <span>다음페이지</span>
                        </a>
                    </c:if>
                    <c:if test="${!lastFlag}">
                        <a class="last" href="javascript:getPage('${totalPage}');" alt="마지막페이지" >
                            <span>마지막페이지</span>
                        </a>
                    </c:if>
					 
					</div>
					</div>
					 <%@ include file="/WEB-INF/views/main/include/aTagLink.jsp"%>
				</section>
			</acrticle>	 
<form id="searchPromotionForm" method="get">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  <input type="hidden" name="pageNo" id="pageNo">
  <input type="hidden" name="searchCategory" id="searchCategory" value="${dspPromtScFrDTO.searchCategory }">
</form>

<script type="text/javascript">

$(document).ready(function() {
	$(".tab-type01 li").each(function(){
		if($(this).find('a').attr('id') == $('#searchCategory').val()){
			$(this).addClass('on')
		}
	});

    $(".tab-type01 a").click(function(){
		var frm = $('#searchPromotionForm');
		 $('#searchCategory').val($(this).attr('id'));
	    frm.submit();
    });
 
});
	
	function getPage(pageNo){
	    $('#pageNo').val(pageNo);
	    $('#searchPromotionForm').attr('action', "/style/styleList");
		var frm = $('#searchPromotionForm');
 
	    frm.submit();
 
 	}
</script>