<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 <!-- 컨텐츠 시작 -->
	<div class="contain mn home" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<c:set value="0" var="mainCnt"/>

				<c:forEach items="${mainTmplat}" var="tmplat" varStatus="mainStatus">

					<c:set value="${mainCnt + 1}" var="mainCnt"/>
					
					<!-- FEATURED 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'stg' ) and tmplat.key eq '20010' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'stg' ) and tmplat.key eq '20010' and !empty tmplat.value ) }">
					
						<c:set value="${tmplat.value}" var="featuredContt"/>

						<section class="mds-section arrival">
							<div class="hdt"><span class="tit">${featuredContt[0].dspCnrNm}</span></div>
												
							<div id="mainFeatured" class="slide">
								<div class="swiper-container">
									<ul class="swiper-wrapper">
										<c:forEach items="${featuredContt}" var="featured" varStatus="status">
											<li class="swiper-slide">
												<c:choose>
												    <c:when test="${featured.conttTpCd eq 'IMG_BANNER'}">
														<div class="vs">
															<a href="${featured.conttCnncUrl}">
																<img src="${_image}${featured.imgFileUrl}/${featured.imgFileNm}/dims/resize/1160x560" alt="${featured.imgAltrtvCont}" usemap="#Map_vs2" class="vs-img">
															</a>
														</div>
														<c:if test="${!empty featured.imgMapCont}">
															${featured.imgMapCont}
															<input type="hidden" name="imgOvFileUrl" value="${_image}${featured.imgOvFileUrl}/${featured.imgOvFileNm}/dims/resize/1200x645" />
															<input type="hidden" name="imgOvFileAlt" value="${featured.imgAltrtvCont}" />
														</c:if>
													</c:when>
													<c:when test="${featured.conttTpCd eq 'HTML'}">														
														${featured.htmlCont}
													</c:when>
													<c:otherwise>
														<div class="vs">
															 <c:choose>
									                        	<c:when test="${featured.moviKndCd eq 'VIMEO'}">
									                            	<iframe class="video_iframe" width="1200" height="600" src="https://player.vimeo.com/video/${featured.moviFileUrl}" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
									                        	</c:when>
									                        	<c:otherwise>
									                            	<iframe class="video_iframe" width="1200" height="600" src="https://www.youtube.com/embed/${featured.moviFileUrl}?rel=0&loop=1&playlist=${featured.moviFileUrl}" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
									                        	</c:otherwise>
									                    	</c:choose>
								                        </div>
													</c:otherwise>
												</c:choose>
											</li>
										</c:forEach>

									</ul>
								</div>
								<div class="pagination" <c:if test="${fn:length(featuredContt) eq 1}"> style="display:none;" </c:if>></div>
								<div class="navigation">
									<button type="button" class="btnNav prev" <c:if test="${fn:length(featuredContt) eq 1}"> style="display:none;" </c:if>>이전</button>
									<button type="button" class="btnNav next" <c:if test="${fn:length(featuredContt) eq 1}"> style="display:none;" </c:if>>다음</button>
								</div>
							</div>												
						</section>
						
						<!-- 메인 FEATURED 팝업 -->						
						<article id="popFeatured" class="layer-popup popFeatured pop_1">
							<section class="layer-popup-cont" tabindex="0" style="width:auto;">
								<div class="layer-cont">
									<img usemap="#Map_vs2" id="popFeaturedImg" class="vs-img">
								</div>
								<div class="layer-popup-close">
									<button type="button" class="d_layer_close">닫기</button>
								</div>
							</section>
						</article>												
						
						<script type="text/javascript">
							
							$("#mainFeatured").on('click', 'area', function(e) {
								e.preventDefault();
								
								var $that = $( this );
								var $img = $("#popFeatured").find("img");
								var imgOvFileUrl = "";
								var imgOvFileAlt = "";
								
								if($("#mainFeatured").find("li.swiper-slide").length > 1) {
									
									imgOvFileUrl = $("#mainFeatured .swiper-slide.swiper-slide-active").find("input[name='imgOvFileUrl']").val();
									imgOvFileAlt = $("#mainFeatured .swiper-slide.swiper-slide-active").closest("li.swiper-slide").find("input[name='imgOvFileAlt']").val();
									
								} else {
									
									imgOvFileUrl = $("#mainFeatured .swiper-slide").find("input[name='imgOvFileUrl']").val();
									imgOvFileAlt = $("#mainFeatured .swiper-slide").closest("li.swiper-slide").find("input[name='imgOvFileAlt']").val();
									
								}
								
								$img.attr("src", imgOvFileUrl);
								$img.attr("alt", imgOvFileAlt);
								
								$img.on("load", function() {
									layerPopup.popupOpenNow("#popFeatured");	
								});
								
							});
						
						</script>						
											
					</c:if>

					<!-- NEW ARRIVAL 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10112' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10112' and !empty tmplat.value ) }">
						<c:set value="${tmplat.value}" var="arrivalContt"/>

						<section class="mds-section arrival">

							<div class="hdt"><span class="tit">NEW ARRIVAL</span></div>

							<div id="mainNewArrival" class="slide">
								<div class="swiper-container">
									<ul class="swiper-wrapper">

										<c:forEach items="${arrivalContt}" var="arrival" varStatus="status">

											<li class="swiper-slide">

												<c:choose>
												    <c:when test="${arrival.conttTpCd eq 'IMG_BANNER'}">
														<div class="vs">
															<a href="${arrival.conttCnncUrl}">
																<img src="${_image}${arrival.imgFileUrl}/${arrival.imgFileNm}/dims/resize/1160x560" alt="${arrival.imgAltrtvCont}" usemap="#Map_vs1" class="vs-img">
															</a>
														</div>
														<c:if test="${!empty arrival.imgMapCont}">
															${arrival.imgMapCont}
															<%-- <map name="Map_vs1" id="Map_vs1">
																<area shape="rect" coords="47,478,208,530" href="javascript:layerPopup.popupOpenNow('#popNewArrival_${status.count}');" />
															</map> --%>
															<input type="hidden" name="imgOvFileUrl" value="${_image}${arrival.imgOvFileUrl}/${arrival.imgOvFileNm}/dims/resize/1200x645" />
															<input type="hidden" name="imgOvFileAlt" value="${arrival.imgAltrtvCont}" />
														</c:if>
													</c:when>
													<c:when test="${arrival.conttTpCd eq 'HTML'}">
														${arrival.htmlCont}
													</c:when>
													<c:otherwise>
														<div class="vs">
															 <c:choose>
									                        	<c:when test="${arrival.moviKndCd eq 'VIMEO'}">
									                            	<iframe class="video_iframe" width="1200" height="600" src="https://player.vimeo.com/video/${arrival.moviFileUrl}" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
									                        	</c:when>
									                        	<c:otherwise>
									                            	<iframe class="video_iframe" width="1200" height="600" src="https://www.youtube.com/embed/${arrival.moviFileUrl}?rel=0&loop=1&playlist=${arrival.moviFileUrl}" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
									                        	</c:otherwise>
									                    	</c:choose>
								                        </div>
													</c:otherwise>
												</c:choose>

											</li>

										</c:forEach>

									</ul>
								</div>
								<div class="pagination" <c:if test="${fn:length(arrivalContt) eq 1}"> style="display:none;" </c:if>></div>
								<div class="navigation">
									<button type="button" class="btnNav prev" <c:if test="${fn:length(arrivalContt) eq 1}"> style="display:none;" </c:if>>이전</button>
									<button type="button" class="btnNav next" <c:if test="${fn:length(arrivalContt) eq 1}"> style="display:none;" </c:if>>다음</button>
								</div>
							</div>

						</section>
						
						<!-- 메인 New Arrival 팝업 -->
						<article id="popNewArrival" class="layer-popup popNewArrival pop_1">
							<section class="layer-popup-cont" tabindex="0">
								<div class="layer-cont">
									<img usemap="#Map_vs1" class="vs-img">
								</div>
								<div class="layer-popup-close">
									<button type="button" class="d_layer_close">닫기</button>
								</div>
							</section>
						</article>
						
						<script type="text/javascript">
							
							$("#mainNewArrival").on('click', 'area', function(e) {
	
								e.preventDefault();
								
								var $that = $( this );
								var $img = $("#popNewArrival").find("img");
								var imgOvFileUrl = "";
								var imgOvFileAlt = "";
								
								if($("#mainNewArrival").find("li.swiper-slide").length > 1) {
									
									imgOvFileUrl = $("#mainNewArrival .swiper-slide.swiper-slide-active").find("input[name='imgOvFileUrl']").val();
									imgOvFileAlt = $("#mainNewArrival .swiper-slide.swiper-slide-active").closest("li.swiper-slide").find("input[name='imgOvFileAlt']").val();
									
								} else {
									
									imgOvFileUrl = $("#mainNewArrival .swiper-slide").find("input[name='imgOvFileUrl']").val();
									imgOvFileAlt = $("#mainNewArrival .swiper-slide").closest("li.swiper-slide").find("input[name='imgOvFileAlt']").val();
									
								}
								
								$img.attr("src", imgOvFileUrl);
								$img.attr("alt", imgOvFileAlt);
								
								$img.on("load", function() {
									layerPopup.popupOpenNow("#popNewArrival");	
								});
								
							});
						
						</script>

					</c:if>
					
					<c:if test="${mainCnt eq '5'}">
						 <!-- OLAPIC 5번째 시작 -->
						<c:set value="${mainCnt + 1}" var="mainCnt"/>
						<section class="mds-section olapic">
							<div class="hdt"><span class="tit">#MLB STYLE in SNS</span></div>
							<%@ include file="/WEB-INF/views/include/olapic.jsp"%>
						</section>
					</c:if>

					<c:if test="${mainCnt eq '8' and (fn:length(krList) != 0)}">
						<section class="mds-section rank">
							<div class="hdt"><span class="tit">RANKING</span></div>
							<div class="tabs">
								<div class="uiTabPan">
									<c:if test="${fn:length(krList) > 0}">
										<div class="panel cont active" id="tabMainRank_1">
											<div class="slide">
												<div class="swiper-container">
													<ul class="swiper-wrapper list">
														
														<c:forEach items="${krList}" var="kr" varStatus="status">
															<li class="swiper-slide">
																<div class="item">
																	<div class="thumb"><a href="${kr.godUrl}"><em class="num"><fmt:formatNumber value="${status.count}" pattern="00" /></em><span class="img"><img src="${_image}${kr.imgFrontUrl}/dims/resize/414x414" onerror="errorImgShow(this, '414');" alt="${kr.god.godNm}"></span></a></div>
																	<div class="info">
														                 <div class="name">
														                 	<c:if test="${kr.brndId eq 'I'}">
														                 		<span>[KIDS]</span>
														                 	</c:if>
														                 	${kr.god.godNm}
														                 </div>
														                 <div class="prc"><em class="p"><fmt:formatNumber value="${kr.dspGodPrc.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em></div>
														             </div>
																</div>
															</li>
														</c:forEach>
											
													</ul>
													<div class="navigation">
														<button type="button" class="btnNav prev">이전</button>
														<button type="button" class="btnNav next">다음</button>
													</div>
												</div>
											</div>
										</div>
									</c:if>
								</div>
							</div>
						</section>
					</c:if>

					<!-- VISUAL 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10111' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10111' and !empty tmplat.value ) }">
						<c:set value="${tmplat.value}" var="visualContt"/>

						<section class="mds-section visual mn" id="mainVisual">

							<div class="swiper-container">
								<ul class="swiper-wrapper">
									<c:forEach items="${visualContt}" var="visual">
										<li class="swiper-slide">
											<c:choose>
											    <c:when test="${visual.conttTpCd eq 'HTML'}">
													${visual.htmlCont }
												</c:when>
												<c:otherwise>
													<a href="${visual.conttCnncUrl}"><img class="vs-img" src="${_image}${visual.imgFileUrl}/${visual.imgFileNm}/dims/resize/1920x850" alt="${visual.imgAltrtvCont}"></a>
													${visual.imgMapCont}
												</c:otherwise>
											</c:choose>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="pagination" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>></div>
							<div class="navigation">
								<button type="button" class="btnNav prev" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>>이전</button>
								<button type="button" class="btnNav next" <c:if test="${fn:length(visualContt) eq 1}"> style="display:none;" </c:if>>다음</button>
							</div>

						</section>

					</c:if>

					<!-- SHOP 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10113' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10113' and !empty tmplat.value ) }">
						<c:set value="${tmplat.value}" var="shopContt"/>

						<c:set value="0" var="imgCnt"/>
						<c:set value="0" var="godCnt"/>

						<c:forEach items="${shopContt}" var="shop">

							<c:if test="${shop.conttTpCd eq 'IMG_BANNER'}">
								<c:set value="${imgCnt + 1}" var="imgCnt"/>
							</c:if>
							<c:if test="${shop.conttTpCd eq 'GOD'}">
								<c:set value="${godCnt + 1}" var="godCnt"/>
							</c:if>

						</c:forEach>

						<c:if test="${imgCnt ge 1 and godCnt ge 4}">

							<section class="mds-section shop">

								<div class="hdt"><span class="tit">COLLECTION</span></div>
								<c:if test="${!empty shopContt and shopContt[0].conttTpCd eq 'IMG_BANNER'}">
									<div class="vs">
										<a href="${shopContt[0].conttCnncUrl}">
											<img class="vs-img" src="${_image}${shopContt[0].imgFileUrl}/${shopContt[0].imgFileNm}/dims/resize/1920x720" alt="${shopContt[0].imgAltrtvCont}">
										</a>
									</div>
								</c:if>

								<div class="goods">
									<ul class="list">

										<c:set value="0" var="godCnt"/>

										<c:forEach items="${shopContt}" var="shop" varStatus="status">

											<c:if test="${shop.conttTpCd eq 'GOD' and godCnt lt 4}">

												<c:set value="${godCnt + 1}" var="godCnt"/>

												<li>
													<div class="item">
														<div class="thumb"><a href="${shop.godUrl}">
															<span class="img">
																<img src="${_image}${shop.imgFrontUrl}/dims/resize/414x414" alt="${shop.godNm}" onerror="errorImgShow(this, '600');"<c:if test="${!empty shop.detailImgFrontUrl }"> class="first" </c:if>>
																<c:if test="${!empty shop.detailImgFrontUrl }">
																	<img src="${_image}${shop.detailImgFrontUrl}/dims/resize/414x414" alt="${shop.godNm}" onerror="errorImgShow(this, '600');" class="second">
																</c:if>
															</span></a></div>
														<div class="info">
															<div class="name">
																<c:if test="${shop.brndId eq 'I'}">
																	<span>[KIDS]</span>
																</c:if>
																${shop.godNm}
															</div>
															<div class="prc">
																<c:choose>
																	<c:when test="${shop.csmrPrc lt shop.rtlPrc}">
																		<em class="p"><fmt:formatNumber value="${shop.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
																	</c:when>
																	<c:otherwise>
																		<em class="p"><fmt:formatNumber value="${shop.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</div>
												</li>
											</c:if>

										</c:forEach>

									</ul>
								</div>

							</section>

						</c:if>

					</c:if>
					<!-- LOOK BOOK 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10114' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10114' and !empty tmplat.value ) }">
						<c:set value="${tmplat.value}" var="lookContt"/>

						<script type="text/javascript" src="${_resourceURL}static/js/modernizr.2.5.3.min.js"></script>

						<section class="mds-section look">
							<div class="hdt"><span class="tit">LOOKBOOK</span></div>
							<div class="vs">


								<ul class="pg_num">
									<c:forEach items="${lookContt}" var="look" varStatus="status">
										<c:if test="${look.conttTpCd eq 'IMG_BANNER'}">
											<li><a href="javascript:;" class="lk">0${status.count}</a></li>
										</c:if>
									</c:forEach>
								</ul>

								<div class="flipbook-viewport">
									<div class="flip-container">
										<div class="depth front"></div>
										<div class="flipbook">
											<!-- 이미지사이즈 1300 X 740 -->
											<div class="page" style="background-image:url(/static/images/_temp/pages/0.png)"></div>
											<c:forEach items="${lookContt}" var="look" varStatus="status">
												<c:if test="${look.conttTpCd eq 'IMG_BANNER'}">
													<div class="double" style="background-image:url(${_image}${look.imgFileUrl}/${look.imgFileNm}/dims/resize/1300x740)" data-idx="${status.index}">
													</div>
												</c:if>
											</c:forEach>
										</div>
										<div class="depth back"></div>
										<div class="flip-control">
											<!-- <div id="prev">
											</div>
											<div id="next">
											</div> -->
										</div>
									</div>
									<div class="look_get" style="display:none;">
										<a href="javascript:;" class="btn_get"  onclick="download_fnc()">GET THIS LOOK</a>
									</div>
								</div>

								<div class="look_more">
									<a href="/lookbook/lookbookList" class="btn_more">Read More</a>
								</div>

								<script type="text/javascript">

								var down_pg = [];
								var down_index = [];
								<c:forEach items="${lookContt}" var="look" varStatus="status">
									down_pg.push("${look.conttCnncUrl}");
									<c:if test="${!empty look.conttCnncUrl}">
										down_index.push(${status.count});
									</c:if>
								</c:forEach>
								var download_fnc = function(e){
									window.location = down_pg[$(".mds-section.look .pg_num>li.active").index()];
								};
								
								pageDepth = function(idx,totpag){
									var backCls = totpag - idx;
									var frontCls = totpag - backCls -1 ;
									var $flip = $(".mds-section.look .flipbook-viewport");
									if (backCls <= 4) {
										$flip.find(".depth.back").attr("class","depth back s"+backCls);
									}
									if (frontCls <= 4 ) {
										$flip.find(".depth.front").attr("class","depth front s"+frontCls);
									}
								};
								pageDepthInit = function(){
									var pgt_len = $(".mds-section.look .pg_num>li").length - 1;
									var $flip = $(".mds-section.look .flipbook-viewport");
									$flip.find(".depth.front").addClass("s0");
									if ( $(".mds-section.look .pg_num>li").length  <= 4 ){
										$flip.find(".depth.back").addClass( "s"+ pgt_len );
									}else{
										$flip.find(".depth.back").addClass( "s4");
									}
								};
								/* 20181221 추가 */
								$.fn.extend({
									scissor : function() {
							            this.each(function() {
							            	var $that = $(this);
							                var b = $that
							                  , c = {
							                    width: b.width() / 2,
							                    height: b.height(),
							                    overflow: "hidden"
							                }
							                  , f = b.clone(!0)
							                  , d = $("<div />", {
							                    css: c
							                })
							                  , e = $("<div />", {
							                    css: c
							                });
							                b.after(d);
							                d.after(e);
							                b.css({
							                    marginLeft: 0
							                }).appendTo(d);
							                f.css({
							                    marginLeft: -c.width
							                }).appendTo(e);
							            });
							            return this
						        	}
								});
								function loadApp() {

									var flipbook = $('.flipbook');

								 	// Check if the CSS was already loaded
									if (flipbook.width()==0 || flipbook.height()==0) {
										setTimeout(loadApp, 10);
										return;
									}
								 	
								 	if($(".flipbook .double").length === 1) {
								 		$(".mds-section.look .look_more").show();
								 	}
								 	

									$('.flipbook .double').scissor();

									// Create the flipbook
									$('.flipbook').turn({
											// Elevation
											elevation: 50,
											page:2,
											// Enable gradients
											gradients: true,
											// Auto center this flipbook
											// autoCenter: true
									});
									
									//$('.flipbook').on('mouseover', (function(){$('.flipbook').turn('peel', 'br');}));

									$(".flipbook").bind("turning", function(event, page, pageObject) {
										
										var oddPageNo = pageObject[1];
										
										if (page == 1){
											return false;
										}
										
										idx = parseInt(page * 0.5);
										
										if( down_index.indexOf(idx) !== -1 ){
											$(".mds-section.look .look_get").show();
										}else{
											$(".mds-section.look .look_get").hide();
										}
										
										$(".mds-section.look .pg_num>li:nth-child("+idx+")").addClass("active").siblings("li").removeClass("active");
										
										// 마지막 페이지일때 더보기 버튼 보이기
										var totpag = (  $(".flipbook").turn("pages") - 1 ) * 0.5;
										if (idx >= totpag ) {
											$(".mds-section.look .look_more").show();
										}else{
											$(".mds-section.look .look_more").hide();
										}

										
										pageDepth(idx,totpag);
										
									});
									
									pageDepthInit();

									$(".flipbook").bind("start", function(event, pageObject) {
										if (pageObject.page == 1){
											event.preventDefault();
										}
									});

									$(".mds-section.look .pg_num>li:nth-child(1)").addClass("active");

									$(".mds-section.look .pg_num>li>a").on("click",function(){
										var idx = $(this).closest("li").index();
										idx = (idx)*2+1;
										$(".flipbook").turn("page", idx +1 );
									});
									
									/* $("#prev").click(function(e){
										e.preventDefault();
										$(".flipbook").turn("previous");
									});

									$("#next").click(function(e){
										e.preventDefault();
										$(".flipbook").turn("next");
									}); */
									if( down_index.indexOf(1) !== -1 ){
										$(".mds-section.look .look_get").show();
									}
									
								}

								// Load the HTML4 version if there's not CSS transform

								yepnope({
									test : Modernizr.csstransforms,
									yep: ['${_resourceURL}static/js/turn.js'],
									nope: ['${_resourceURL}static/js/turn.html4.min.js'],
									// both: ['css/basic.css'],
									//both: ['/static/js/scissor.min.js'],	/* 20181221 주석처리 */
									complete: loadApp
								});


								</script>

							</div>
						</section>
					</c:if>

					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10199' and !empty tmplat.value and !empty mainTmplat['10200'] )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10199' and !empty tmplat.value and !empty mainTmplat['10200'] ) }">
					
						<!-- Launch  -->
						<c:choose>
							<c:when test="${ env eq 'local' or env eq 'dev' }">
								<c:set value="${mainTmplat['200']}" var="launchTitle"/>
							</c:when>
							<c:otherwise>
								<c:set value="${mainTmplat['10200']}" var="launchTitle"/>
							</c:otherwise>
						</c:choose>
						
						<c:set value="${tmplat.value}" var="launchContt"/>
						<c:set value="0" var="imgCnt"/>
						<c:set value="0" var="cnrSetCnt"/>
						<c:set value="" var="cnrSetSn"/>
						
						<c:forEach items="${launchContt}" var="lch" varStatus="stauts">
							<c:if test="${lch.conttTpCd eq 'IMG_BANNER' and lch.cnrSetSn ne cnrSetSn}">
								<c:set value="${lch.cnrSetSn}" var="cnrSetSn"/>
								<c:set value="${cnrSetCnt + 1}" var="cnrSetCnt"/>
								<c:set value="${imgCnt + 1}" var="imgCnt"/>
							</c:if>
							
						</c:forEach>
						
						<c:if test="${cnrSetCnt ge 3 and imgCnt ge 3}">
						
							<section class="mds-section launch">
	
								<div class="hdt"><span class="tit">${launchTitle[0].conttNm}</span></div>
							
								<div class="ctns">
									<ul class="list">
										
										<c:set value="" var="cnrSetSn"/>
										<c:set value="0" var="contCnt"/>
										
										<c:forEach items="${launchContt}" var="lch" varStatus="status">
										
											<c:if test="${lch.conttTpCd eq 'IMG_BANNER' and contCnt lt 3 and lch.cnrSetSn ne cnrSetSn}">
												
												<c:set value="${lch.cnrSetSn}" var="cnrSetSn"/>
												<c:set value="${contCnt + 1}" var="contCnt"/>
										
												<li>
													<div class="item">
														<div class="thumb">
															<c:choose>
																<c:when test="${empty lch.conttCnncUrl}">
																	<span class="img"><img src="${_image}${lch.imgFileUrl}/${lch.imgFileNm}/dims/resize/560x300" alt="${lch.imgAltrtvCont}" ></span>
																</c:when>
																<c:otherwise>
																	<a href="${lch.conttCnncUrl}"><span class="img"><img src="${_image}${lch.imgFileUrl}/${lch.imgFileNm}/dims/resize/560x300" alt="${lch.imgAltrtvCont}" ></span></a>																	
																</c:otherwise>
															</c:choose>
														</div>
														
														<c:if test="${launchContt[status.index + 1].conttTpCd eq 'TEXT'}">
															
															<div class="info">
																<div class="tit">${launchContt[status.index + 1].conttNm }</div>
																<c:if test="${!empty launchContt[status.index + 2] and launchContt[status.index + 2].conttTpCd eq 'TEXT'}">
																	<div class="txt">${launchContt[status.index + 2].conttNm }</div>
																</c:if>
															</div>
															
														</c:if>
														
													</div>
												</li>
										
											</c:if>
											
										</c:forEach>
									
									</ul>
								</div>
								
							</section>
						
						</c:if>
					
					</c:if>
					<!-- CULTULE 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10115' and ( !empty mainTmplat['10115'] or !empty mainTmplat['10116'] or !empty mainTmplat['10117'] or !empty mainTmplat['10118'] ) )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10115' and ( !empty mainTmplat['10115'] or !empty mainTmplat['10116'] or !empty mainTmplat['10117'] or !empty mainTmplat['10118'] ) ) }">
							
							<c:choose>
								<c:when test="${ env eq 'local' or env eq 'dev' }">
									<c:set value="${mainTmplat['115']}" var="magazine"/>
									<c:set value="${mainTmplat['116']}" var="interview"/>
									<c:set value="${mainTmplat['117']}" var="celeb"/>
									<c:set value="${mainTmplat['118']}" var="festival"/>
								</c:when>
								<c:otherwise>
									<c:set value="${mainTmplat['10115']}" var="magazine"/>
									<c:set value="${mainTmplat['10116']}" var="interview"/>
									<c:set value="${mainTmplat['10117']}" var="celeb"/>
									<c:set value="${mainTmplat['10118']}" var="festival"/>
								</c:otherwise>
							</c:choose>

							<c:set value="0" var="magImgCnt"/>
							<c:set value="0" var="magTxtCnt"/>
							<c:set value="0" var="itvImgCnt"/>
							<c:set value="0" var="itvTxtCnt"/>
							<c:set value="0" var="celImgCnt"/>
							<c:set value="0" var="celTxtCnt"/>
							<c:set value="0" var="fesImgCnt"/>
							<c:set value="0" var="fesTxtCnt"/>
							
							<c:forEach items="${magazine}" var="mag">

								<c:if test="${mag.conttTpCd eq 'IMG_BANNER'}">
									<c:set value="${magImgCnt + 1}" var="magImgCnt"/>
								</c:if>
								<c:if test="${mag.conttTpCd eq 'TEXT'}">
									<c:set value="${magTxtCnt + 1}" var="magTxtCnt"/>
								</c:if>

							</c:forEach>
							<c:forEach items="${interview}" var="itv">

								<c:if test="${itv.conttTpCd eq 'IMG_BANNER'}">
									<c:set value="${itvImgCnt + 1}" var="itvImgCnt"/>
								</c:if>
								<c:if test="${itv.conttTpCd eq 'TEXT'}">
									<c:set value="${itvTxtCnt + 1}" var="itvTxtCnt"/>
								</c:if>

							</c:forEach>
							<c:forEach items="${celeb}" var="cel">

								<c:if test="${cel.conttTpCd eq 'IMG_BANNER'}">
									<c:set value="${celImgCnt + 1}" var="celImgCnt"/>
								</c:if>
								<c:if test="${cel.conttTpCd eq 'TEXT'}">
									<c:set value="${celTxtCnt + 1}" var="celTxtCnt"/>
								</c:if>

							</c:forEach>
							<c:forEach items="${festival}" var="fes">

								<c:if test="${fes.conttTpCd eq 'IMG_BANNER'}">
									<c:set value="${fesImgCnt + 1}" var="fesImgCnt"/>
								</c:if>
								<c:if test="${fes.conttTpCd eq 'TEXT'}">
									<c:set value="${fesTxtCnt + 1}" var="fesTxtCnt"/>
								</c:if>

							</c:forEach>

							<c:if test="${(magImgCnt ge 3 and magTxtCnt ge 3) or (itvImgCnt ge 3 and itvTxtCnt ge 3) or (celImgCnt ge 3 and celTxtCnt ge 3) or (fesImgCnt ge 3 and fesTxtCnt ge 3)}">

								<section class="mds-section culture">
									<div class="hdt"><span class="tit">MLB STORY</span></div>
									<div class="tabs">

										<ul class="menu uiTabNav">
											<c:if test="${magImgCnt ge 3 and magTxtCnt ge 3}">
												<li class="active">
													<a href="#tabMainCulture_1">
														<span>MAGAZINE</span>
													</a>
												</li>
											</c:if>
											<c:if test="${itvImgCnt ge 3 and itvTxtCnt ge 3}">
												<li <c:if test="${magImgCnt lt 3 or magTxtCnt lt 3}"> class="active" </c:if> >
													<a href="#tabMainCulture_2">
														<span>INTERVIEW</span>
													</a>
												</li>
											</c:if>
											<c:if test="${celImgCnt ge 3 and celTxtCnt ge 3}">
												<li <c:if test="${(magImgCnt lt 3 or magTxtCnt lt 3) and (itvImgCnt lt 3 or itvTxtCnt lt 3)}"> class="active" </c:if> >
													<a href="#tabMainCulture_3">
														<span>CELEB</span>
													</a>
												</li>
											</c:if>
											<c:if test="${fesImgCnt ge 3 and fesTxtCnt ge 3}">
												<li <c:if test="${(magImgCnt lt 3 or magTxtCnt lt 3) and (itvImgCnt lt 3 or itvTxtCnt lt 3) and (celImgCnt lt 3 or celTxtCnt lt 3)}"> class="active" </c:if> >
													<a href="#tabMainCulture_4">
														<span>FESTIVAL&PARTY</span>
													</a>
												</li>
											</c:if>
										</ul>
										<div class="uiTabPan">

											<c:if test="${magImgCnt ge 3 and magTxtCnt ge 3}">
												<!-- MAGAZINE 시작 -->
												<div class="panel cont active" id="tabMainCulture_1">
													<ul class="list">

														<c:set value="0" var="contCnt"/>

														<c:forEach items="${magazine}" var="mag" varStatus="status">

															<c:if test="${mag.conttTpCd eq 'IMG_BANNER' and contCnt lt 3}">

																<c:set value="${contCnt + 1}" var="contCnt"/>

																<li>
																	<div class="item">
																		<div class="thumb">
																			<a href="${mag.conttCnncUrl}"><span class="img"><img src="${_image}${mag.imgFileUrl}/${mag.imgFileNm}/dims/resize/560x560" alt="${mag.imgAltrtvCont}"></span></a>
																		</div>
																		<div class="info">
																			<c:if test="${!empty magazine[status.index + 1] and magazine[status.index + 1].conttTpCd eq 'TEXT'}">
																				<div class="txt">${magazine[status.index + 1].conttNm }</div>
																			</c:if>
																		</div>
																	</div>
																</li>

															</c:if>

														</c:forEach>

													</ul>
													<div class="more_load">
														<a href="/culture/cultureList?promtExpsrSectCd=MAGAZINE" class="btn-more">More</a>
													</div>
												</div>

											</c:if>
											<c:if test="${itvImgCnt ge 3 and itvTxtCnt ge 3}">
												<!-- INTERVIEW 시작 -->
												<div class="panel cont <c:if test="${magImgCnt lt 3 or magTxtCnt lt 3}"> active </c:if>" id="tabMainCulture_2">
													<ul class="list">

														<c:set value="0" var="contCnt"/>

														<c:forEach items="${interview}" var="itv" varStatus="status">

															<c:if test="${itv.conttTpCd eq 'IMG_BANNER' and contCnt lt 3}">

																<c:set value="${contCnt + 1}" var="contCnt"/>

																<li>
																	<div class="item">
																		<div class="thumb">
																			<a href="${itv.conttCnncUrl}"><span class="img"><img src="${_image}${itv.imgFileUrl}/${itv.imgFileNm}/dims/resize/560x560" alt="${itv.imgAltrtvCont}"></span></a>
																		</div>
																		<div class="info">
																			<c:if test="${!empty interview[status.index - 1] and interview[status.index - 1].conttTpCd eq 'TEXT'}">
																				<div class="txt">${interview[status.index - 1].conttNm }</div>
																			</c:if>
																		</div>
																	</div>
																</li>

															</c:if>

														</c:forEach>

													</ul>
													<div class="more_load">
														<a href="/culture/cultureList?promtExpsrSectCd=INTERVIEW" class="btn-more">More</a>
													</div>
												</div>

											</c:if>
											<c:if test="${celImgCnt ge 3 and celTxtCnt ge 3}">
												<!-- CELEB 시작 -->
												<div class="panel cont <c:if test="${(magImgCnt lt 3 or magTxtCnt lt 3) and (itvImgCnt lt 3 or itvTxtCnt lt 3)}"> active </c:if>" id="tabMainCulture_3">
													<ul class="list">

														<c:set value="0" var="contCnt"/>

														<c:forEach items="${celeb}" var="cel" varStatus="status">

															<c:if test="${cel.conttTpCd eq 'IMG_BANNER' and contCnt lt 3}">

																<c:set value="${contCnt + 1}" var="contCnt"/>

																<li>
																	<div class="item">
																		<div class="thumb">
																			<a href="${cel.conttCnncUrl}"><span class="img"><img src="${_image}${cel.imgFileUrl}/${cel.imgFileNm}/dims/resize/560x560" alt="${cel.imgAltrtvCont}"></span></a>
																		</div>
																		<div class="info">
																			<c:if test="${!empty celeb[status.index - 1] and celeb[status.index - 1].conttTpCd eq 'TEXT'}">
																				<div class="txt">${celeb[status.index - 1].conttNm }</div>
																			</c:if>
																		</div>
																	</div>
																</li>

															</c:if>

														</c:forEach>

													</ul>
													<div class="more_load">
														<a href="/culture/cultureList?promtExpsrSectCd=CELEB" class="btn-more">More</a>
													</div>
												</div>

											</c:if>
											<c:if test="${fesImgCnt ge 3 and fesTxtCnt ge 3}">
												<!-- FESTIVAL 시작 -->
												<div class="panel cont <c:if test="${(magImgCnt lt 3 or magTxtCnt lt 3) and (itvImgCnt lt 3 or itvTxtCnt lt 3) and (celImgCnt lt 3 or celTxtCnt lt 3)}"> active </c:if>" id="tabMainCulture_4">
													<ul class="list">

														<c:set value="0" var="contCnt"/>

														<c:forEach items="${festival}" var="fes" varStatus="status">

															<c:if test="${fes.conttTpCd eq 'IMG_BANNER' and contCnt lt 3}">

																<c:set value="${contCnt + 1}" var="contCnt"/>

																<li>
																	<div class="item">
																		<div class="thumb">
																			<a href="${fes.conttCnncUrl}"><span class="img"><img src="${_image}${fes.imgFileUrl}/${fes.imgFileNm}/dims/resize/560x560" alt="${fes.imgAltrtvCont}"></span></a>
																		</div>
																		<div class="info">
																			<c:if test="${!empty festival[status.index - 1] and festival[status.index - 1].conttTpCd eq 'TEXT'}">
																				<div class="txt">${festival[status.index - 1].conttNm }</div>
																			</c:if>
																		</div>
																	</div>
																</li>

															</c:if>

														</c:forEach>

													</ul>
													<div class="more_load">
														<a href="/culture/cultureList?promtExpsrSectCd=FESTIVAL_PARTY" class="btn-more">More</a>
													</div>
												</div>

											</c:if>
										</div>
									</div>

								</section>

							</c:if>

					</c:if>
					<!-- VIDEO 시작 -->
					<c:if test="${ ( ( env eq 'local' or env eq 'dev' ) and tmplat.key eq '10119' and !empty tmplat.value )
									or ( ( env ne 'local' and env ne 'dev' ) and tmplat.key eq '10119' and !empty tmplat.value ) }">
									
						<c:set value="${tmplat.value}" var="video"/>

						<section class="mds-section video">
							<div class="hdt"><span class="tit">#MLB VIDEO</span></div>
							<div id="mainVideo" class="slide">
								<div class="swiper-container">
								    <ul class="swiper-wrapper">
								        <c:forEach items="${video}" var="vdo" varStatus="status">
								            <li class="swiper-slide">
								                <div class="vs">
								                    <c:choose>
								                        <c:when test="${vdo.moviKndCd eq 'VIMEO'}">
								                            <iframe class="video_iframe" src="https://player.vimeo.com/video/${vdo.moviFileUrl}" frameborder="0" allowfullscreen=""></iframe>
								                        </c:when>
								                        <c:otherwise>
								                            <iframe class="video_iframe" src="https://www.youtube.com/embed/${vdo.moviFileUrl}?rel=0&enablejsapi=1&loop=1&playlist=${vdo.moviFileUrl}" frameborder="0" allowfullscreen=""></iframe>
								                        </c:otherwise>
								                    </c:choose>
								                </div>
								            </li>
								        </c:forEach>
								    </ul>
								</div>
								<div class="navigation" <c:if test="${fn:length(video) eq 1}"> style="display:none;" </c:if>>
									<button type="button" class="btnNav prev" <c:if test="${fn:length(video) eq 1}"> style="display:none;" </c:if>>이전</button>
									<button type="button" class="btnNav next" <c:if test="${fn:length(video) eq 1}"> style="display:none;" </c:if>>다음</button>
								</div>
							</div>

						</section>

					</c:if>

				</c:forEach>

			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->

<!-- NOTICE POPUP -->
<jsp:include page="/WEB-INF/views/helpdesk/include/notice.pop.jsp">
    <jsp:param name="searchExpsrScrinSectCd" value="MAIN" />
</jsp:include>
<!--// NOTICE POPUP -->

	<script>
		$(document).ready(function() {

			if(${fn:length(mainTmplat['111']) gt 1 or fn:length(mainTmplat['10111']) gt 1}){

				//main visual slide
				mainVisual = new Swiper('#mainVisual .swiper-container', {
					slidesPerView: 1,
					observer: true,
					observeParents: true,
					pagination: {
						el: '#mainVisual .pagination',
						clickable: true
					},
					navigation: {
						nextEl: '#mainVisual .navigation .btnNav.next',
						prevEl: '#mainVisual .navigation .btnNav.prev'
					},
					autoplay: {
						delay: 29300,
						disableOnInteraction: false
					},
					preloadImages: false,
				    // Enable lazy loading
				    lazy: true,
					loop: true
				});

			}

			if(${fn:length(mainTmplat['20010']) gt 1}){

				//new Featured slide
				mainFeatured = new Swiper('#mainFeatured .swiper-container', {
					slidesPerView: 1,
					observer: true,
					observeParents: true,
					pagination: {
						el: '#mainFeatured .pagination',
						clickable: true
					},
					navigation: {
						nextEl: '#mainFeatured .navigation .btnNav.next',
						prevEl: '#mainFeatured .navigation .btnNav.prev'
					},
					autoplay:false,
					preloadImages: false,
				    // Enable lazy loading
				    lazy: true,
					loop: true
				});

			}			
			
			if(${fn:length(mainTmplat['112']) gt 1 or fn:length(mainTmplat['10112']) gt 1}){

				//new Arrival slide
				mainNewArrival = new Swiper('#mainNewArrival .swiper-container', {
					slidesPerView: 1,
					observer: true,
					observeParents: true,
					pagination: {
						el: '#mainNewArrival .pagination',
						clickable: true
					},
					navigation: {
						nextEl: '#mainNewArrival .navigation .btnNav.next',
						prevEl: '#mainNewArrival .navigation .btnNav.prev'
					},
					autoplay:false,
					preloadImages: false,
				    // Enable lazy loading
				    lazy: true,
					loop: true
				});

			}

			/* if(${fn:length(mainTmplat['119']) gt 1}){

				//video slide
				mainVideo = new Swiper('#mainVideo .swiper-container', {
					slidesPerView: 'auto',
					initialSlide:0,
					centeredSlides:true,
					spaceBetween:20,
					observer: true,
					observeParents: true,
					navigation: {
						nextEl: '#mainVideo .navigation .btnNav.next',
						prevEl: '#mainVideo .navigation .btnNav.prev'
					},
					autoplay:false,
					loop: true
					//preloadImages: false,
				    // Enable lazy loading
				    //lazy: true,
					//
				});

			} */


		});

	</script>
	
	
<script type="text/javascript">
//criteo 호출 메인
window.criteo_q = window.criteo_q || [];
window.criteo_q.push(
        { event: "setAccount", account: 61384 },
        
        { event: "setSiteType", type: "d" },
        { event: "viewHome" }
);
fnf_appendCriteoScript();
</script> 	
<!-- cre.ma / 공통 스크립트 (PC) / 스크립트를 수정할 경우 연락주세요 (support@cre.ma) -->
<script>(function(i,s,o,g,r,a,m){if(s.getElementById(g)){return};a=s.createElement(o),m=s.getElementsByTagName(o)[0];a.id=g;a.async=1;a.src=r;m.parentNode.insertBefore(a,m)})(window,document,'script','crema-jssdk','//widgets.cre.ma/mlb-korea.com/init.js');</script>