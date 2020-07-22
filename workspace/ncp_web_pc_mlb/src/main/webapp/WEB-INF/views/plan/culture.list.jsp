<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
	<!-- 컨텐츠 시작 -->
	<div class="contain dp cult list" id="contain">
		<div class="container">
			<main class="contents" id="contents">
				<div class="cult_top">
					<span class="tit">CULTURE</span>
				</div>
			
				<div class="dp_cult_menu">
					<ul class="menu">
						<li<c:if test="${empty dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList">ALL</a></li>
						<li<c:if test="${'MAGAZINE' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=MAGAZINE">MAGAZINE</a></li>
						<li<c:if test="${'INTERVIEW' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=INTERVIEW">INTERVIEW</a></li>
						<li<c:if test="${'CELEB' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=CELEB">CELEB</a></li>
						<li<c:if test="${'FESTIVAL_PARTY' eq dspPromtScFrDTO.promtExpsrSectCd}"> class="on"</c:if>><a href="/culture/cultureList?promtExpsrSectCd=FESTIVAL_PARTY">FESTIVAL&PARTY</a></li>
					</ul>
				</div>

				<!-- Visual  -->
				<c:if test="${!empty contt.cnrBnrConttList}">
					<section class="mds-section visual cult" id="pmVisual">
						<div class="swiper-container">
							<ul class="swiper-wrapper">
								<c:forEach items="${contt.cnrBnrConttList}" var="cont">
									<li class="swiper-slide">
										<a href="${cont.conttCnncUrl}"><img class="vs-img" src="${_image}${cont.imgFileUrl}/${cont.imgFileNm}/dims/resize/1920x580" alt="${cont.imgAltrtvCont}"></a>
									</li>
								</c:forEach>
							</ul>
						</div>
						<c:if test="${fn:length(contt.cnrBnrConttList) > 1}">
							<div class="pagination"></div>
							<div class="navigation">
								<button type="button" class="btnNav prev">이전</button>
								<button type="button" class="btnNav next">다음</button>
							</div>
							
							<script>
							$( document ).ready(function() {
								pmVisual = new Swiper('#pmVisual .swiper-container', {
									slidesPerView: 1,
									observer: true,
									observeParents: true,
									watchOverflow:true,
									pagination: {
										el: '#pmVisual .pagination',
										clickable: true
									},
									navigation: {
										nextEl: '#pmVisual .navigation .btnNav.next',
										prevEl: '#pmVisual .navigation .btnNav.prev'
									},
									autoplay: {
										delay: 10000,
										disableOnInteraction: false
									},
									preloadImages: false,
								    // Enable lazy loading
								    lazy: true,
									loop: true
								});
							});
							</script>
						</c:if>
					</section>
				</c:if>
				<div class="dp_cult_list">
					<div class="cult_list" id="cult_list">
						<c:forEach items="${evt}" var="cultureCont">
							<div class="box">
								<div class="item">
									<div class="thumb"><a href="${cultureCont.pcUrl}"><span class="img"><img src="${_image}${cultureCont.pcImgFileUrl}/dims/resize/408" alt="${cultureCont.pcImgAltrtvCont}" onerror='errorImgShow(this, "408");'></span></a></div>
									<div class="info"><div class="name">${cultureCont.evtNm}</div></div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</main>
		</div>
	</div>
<script>
$(document).ready(function(){
	var totalPage = ${totalPage};
	var currentPage = ${currentPage};
	var promtExpsrSectCd = "${dspPromtScFrDTO.promtExpsrSectCd}";
	
	var appendStat = true;
	$cult_grid = $('#cult_list').masonry({
		itemSelector: '#cult_list .box',
		percentPosition: true,
		gutter: 30,
		transitionDuration: 0
	});
	$cult_grid.imagesLoaded().progress( function() {
	    $cult_grid.masonry('layout');
	}); 
	$(window).resize(function() {
		$('#cult_list').masonry('layout');
	});
	 
	//  https://masonry.desandro.com/methods.html#additems


	addItemFnc = function(){
		if(appendStat && totalPage != currentPage){
			currentPage += 1;
			$.ajax({
				type : "post",
		        url:"/culture/cultureListPage.json",
				beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
					appendStat = false;
				},
		    	data : {"promtExpsrSectCd": promtExpsrSectCd, "pageNo" : currentPage},
		        success: function(data){
		        	var str = "";
		        	var cultureCont = data.evt;
		        	$(cultureCont).each(function() {
		        		str += "<div class=\"box\">";
		        		str += "	<div class=\"item\">";
		        		str += "		<div class=\"thumb\"><a href=\"" + this.pcUrl + "\"><span class=\"img\"><img src=\"" + imageURL + this.pcImgFileUrl + "/dims/resize/408\" alt=\"" + this.pcImgAltrtvCont + "\" onerror=\"errorImgShow(this, '408');\" ></span></a></div>";
		        		str += "		<div class=\"info\"><div class=\"name\">" + this.evtNm + "</div></div>";
		        		str += "	</div>";
		        		str += "</div>";
		        	});
		        	var $items = $(str);
		        	
		        	$cult_grid.append( $items ).masonry( 'appended', $items );
		        	
		    		$items.find("img").on("load", function( e ) {
		    			
		    			$cult_grid.masonry('layout');
		    			appendStat = true;
		    			
		    		} );
		    		
		        },
		        error: function() {

		        }
		    });
		}else{
			appendStat = false;
		}
	};

	$('.append-button').on( 'click', function() {
		addItemFnc();
	});


	$("#wrap").on("scroll load", function() {
		var sct = $("#wrap").scrollTop() + $("#wrap").height() - $(".foot").height();
		var cnt = $(".container").outerHeight();
		
		if (cnt <= sct ) {
			if ( appendStat == true) {
				addItemFnc();
			}
		}
	});
});
</script>