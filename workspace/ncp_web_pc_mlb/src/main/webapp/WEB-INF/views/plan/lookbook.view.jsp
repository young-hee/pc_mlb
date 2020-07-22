<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
	<!-- 컨텐츠 시작 -->
	<div class="contain dp look view" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<section class="mds_look_veiw">
					
					<div class="page_nav">
						<span class="tit"><a href="/lookbook/lookbookList">LOOKBOOK</a></span>
						<div class="navigation">
							<button type="button" class="btnNav prev">이전</button>
							<button type="button" class="btnNav next">다음</button>
						</div>
						<div class="bts"><a href="javascript:void(0);" class="btn sm gray btnGoList"><spring:message code="display.lookbook.list.view" /></a></div>
					</div>
					
					<div class="slide" id="slideLookbook">
						<div class="swiper-container">
							<div class="swiper-wrapper">
							
								<c:forEach items="${lookbookContt}" var="contt">
								
										<div class="swiper-slide look <c:if test="${empty contt.dspCnrConttExtList}"> no_prds </c:if>" data-div="${contt.dspCtgryNo}">
										
											<div class="sldPan" data-title="${contt.dspCtgryNm}" data-url="${_image}${contt.imgFileUrl}/${contt.imgFileNm}" >
											
												<c:if test="${contt.conttTpCd eq 'IMG_BANNER'}">
													
													<div class="pics">
														<img class="vs-img" src="${_image}${contt.imgFileUrl}/${contt.imgFileNm}/dims/resize/1140" alt="${contt.imgAltrtvCont}" >
														<span class="share ui_share_view" id="btn_promotion_share">
															<ul class="list">
																<li class="weibo"><a href="javascript:;">웨이보</a></li>
																<li class="qq"><a href="javascript:;">QQ</a></li>
																<li class="facebook"><a href="javascript:void(0);">페이스북</a></li>
																<li class="twitter" data-li="${contt.dspCtgryNm}"><a href="javascript:void(0);">트위터</a></li>
																<li class="line"><a href="javascript:void(0);">네이버라인</a></li>
																<li class="url"><a href="javascript:void(0);">URL복사</a></li>
															</ul>
															<a class="bt" href="javascript:;">공유</a>
														</span>
													</div>
													
												</c:if>
												
												<div class="prds">
													<ul class="list">
															
														<c:forEach items="${contt.dspCnrConttExtList}" var="godContt">
															
															<li>
																<div class="item" data-div="${godContt.cnrSetSn}-${godContt.conttTurn}">
																	<div class="thumb"><a href="${godContt.godUrl}"><img src="${_image}${godContt.imgFrontUrl}/dims/resize/160x160" alt="${godContt.godNm}" onerror='errorImgShow(this, "160");'></a></div>
																	<div class="info">
																		<div class="name">
																			<c:if test="${_locale eq 'ko'}">
																				<c:if test="${!empty godContt.tagNm}">
																					<span style="color:${godContt.colorTagNm}">${godContt.tagNm}</span>
																				</c:if>
																			</c:if>
																			<c:if test="${godContt.brndId eq 'I'}">
																				<span>[KIDS]</span>
																			</c:if>
																			${godContt.godNm}
																		</div>
																		<div class="prc">
																			<span class="p n">
																				<em><fmt:formatNumber value="${godContt.csmrPrc}" type="number"/><spring:message code="display.main.text1" /></em>
																			</span>
																		</div>
																		<ul class="dlist">
																			<li class="color">
																				<div class="option-color">
																				
																					<c:forTokens items = "${godContt.optValCd2}" delims = "|" var = "color">
																					
																						<c:set var="godNo" value="" />
																						<c:set var="godNm" value="" />
																						<c:set var="godCd" value="" />
																						<c:set var="godImg" value="" />
																						
																						<c:forTokens items = "${color}" delims = "," var = "option" varStatus="status">
																							
																							<c:if test="${status.index eq 0}">
																								<c:set var="godNo" value="${option}" />
																							</c:if>
																							<c:if test="${status.index eq 1}">
																								<c:set var="godNm" value="${option}" />
																							</c:if>
																							<c:if test="${status.index eq 2}">
																								<c:set var="godCd" value="${option}" />
																							</c:if>
																							<c:if test="${status.index eq 3}">
																								<c:set var="godImg" value="${option}" />
																							</c:if>
																							
																						</c:forTokens>
																						
																						<a title="${godCd}" data-a="${godNo}" href="javascript:void(0);" class="btn-color d_radio_select <c:if test="${godContt.godNo eq godNo}">on</c:if>" >
																							<img src="${_image}${godImg}/dims/resize/30x30" alt="${godNm}" onerror='errorImgShow(this, "30");'>
																						</a>
																							
																					</c:forTokens>
																					
																				</div>
																			</li>
																			<li class="size">
																				<div class="option-size">
																					
																					<c:set value="0" var="sizeCnt"/>
																					
																					<c:forTokens items = "${godContt.optValCd1}" delims = "|" var = "size">
																						
																						<c:choose>
																							
																							<c:when test="${fn:contains(size, 'DEL')}">
																								<button type="button" class="btn-size d_radio_select" disabled="disabled">
																									<span>${fn:substringAfter(size, 'DEL')}</span>
																								</button>		
																							</c:when>
																							<c:otherwise>
																								<c:set value="${sizeCnt + 1}" var="sizeCnt"/>
																								<button type="button" class="btn-size d_radio_select <c:if test="${sizeCnt eq 1}">on</c:if>" >
																									<span>${size}</span>
																								</button>		
																							</c:otherwise>
																							
																						</c:choose>

																				    </c:forTokens>
																				</div>
																			</li>
																		</ul>
																		
																		<c:if test="${lookbook.lang eq 'KOR'}">
																			
																			<div class="bts" data-div="${godContt.godUrl}">
																				<a href="javascript:void(0);" class="btn fill sm btnAddCart"><spring:message code="display.lookbook.cart" /></a>
																				
																				<c:set value="0" var="wishCnt"/>
																				<c:set value="N" var="wishYn"/>
																				
																				<c:forTokens items = "${godContt.optValCd3}" delims = "|" var = "wish" varStatus="status">
																					<c:if test="${status.index eq 0}">
																						<c:set value="${wish}" var="wishCnt"/>	
																					</c:if>
																					<c:if test="${status.index eq 1}">
																						<c:set value="${wish}" var="wishYn"/>	
																					</c:if>
																				</c:forTokens>
																				<a href="javascript:void(0);" data-a="${godContt.godNo}" class="btn sm btnWish <c:if test="${wishYn eq 'Y'}">active</c:if>" >위시리스트 담기</a>
																			</div>
																			
																		</c:if>
																		<c:if test="${lookbook.lang ne 'KOR'}">
																			<div class="bts ext">
																				<a href="javascript:void(0);" class="btn fill sm btnAddCart"><spring:message code="display.lookbook.find.store" /></a>
																			</div>
																		</c:if>
																		
																	</div>
																</div>
															</li>
															
														</c:forEach>
														
													</ul>
												</div>
												
											</div>
											
										</div>
									
								</c:forEach>
								
							</div>
						</div>
						<div class="navigation">
							<button type="button" class="btnNav prev">이전</button>
							<button type="button" class="btnNav next">다음</button>
						</div>
					</div>
				</section>
				<div style="display:none;" id="goodsInfoArea">
				
				</div>
				
				<form method="post" id="lookbookListForm" action="/lookbook/lookbookList" style="display:none;">
					<input type="hidden" name="dspCtgryNo" value="${lookbook.upperCategory}" />
				</form>
				
				<script>
				
				$(document).ready(function() {
					
					var loginResult = false;
					
					<sec:authorize access="hasAnyRole('ROLE_USER')">
						loginResult = true;
					</sec:authorize>
					
					if( ${fn:length(lookbookContt) gt 1} ) {
						
						slideLookbook = new Swiper('#slideLookbook .swiper-container', {
							slidesPerView: 1,
							observer: true,
							observeParents: true,
							watchOverflow:true,
							autoHeight:true,
							threshold:20,
							navigation: {
								nextEl: '.mds_look_veiw .navigation .btnNav.next',
								prevEl: '.mds_look_veiw .navigation .btnNav.prev'
							},
							preloadImages: false,
						    lazy: true,
							loop: true,
							on:{
								slideChangeTransitionEnd:function(){
									
									var dspCtgryNo = $(this.el).find(".swiper-slide-active").data("div");
									var url = document.location.href;
									
									if(url.indexOf(dspCtgryNo) === -1) {
										
										url = document.location.pathname + "?dspCtgryNo=" + dspCtgryNo; 
										
										if(history.replaceState) {
											
											history.replaceState(null, null, url);
											
										}
										
									}
									if( $(this.el).find(".swiper-slide-active").hasClass("no_prds") ) {
										$(".mds_look_veiw .slide").addClass("no_prds");
									} else {
										$(".mds_look_veiw .slide").removeClass("no_prds");
									}
									
									$(".mds_look_veiw .slide").addClass("trans");
									
									$("meta[name='og_title']").attr("content", $(this.el).find(".swiper-slide-active").children().data("title"));
									$("meta[name='og_image']").attr("content", $(this.el).find(".swiper-slide-active").children().data("url"));
									
									$("#wrap").scrollTop(0);
									$(".mds_look_veiw .slide .look .sldPan .prds").scrollTop(0);
									
								},
								touchStart:function(){
									$(".mds_look_veiw .slide").removeClass("trans");
								}
							}
						});
						
					}
					
					// 공유하기 버튼 이벤트
					$(".share.ui_share_view").on("click", "ul.list > li", function() {
						// 페이스북
						if($(this).attr('class').indexOf("facebook") !=-1) {
							jsShareSns('facebook');
						}
						// 트위터
						else if($(this).attr('class').indexOf("twitter") !=-1) {
							jsShareSns('twitter', $(this).data("li"));
						}
						// url 복사
						else if($(this).attr('class').indexOf("url") !=-1){
							if(location.href.indexOf("language=") === -1) {
								var trb= history.replaceState ? location.href + "&language=${_locale}" : document.location.pathname + "?dspCtgryNo=" + $(".swiper-slide-active").data("div") + "&language=${_locale}";
							} else {
								var trb= location.href;
							}
					    	var IE=(document.all)?true:false;
					    	if (IE) {
					    	if(confirm(MESSAGES['common.js.track.txt1']))
					    	window.clipboardData.setData("Text", trb);
					    	} else {
					    	temp = prompt(MESSAGES['common.js.track.txt2'], trb);
					    	}
						 }	
						//  네이버 라인
						else if($(this).attr('class').indexOf("line") !=-1) {
							jsShareSns('naverline');
						}
						else if($(this).attr('class').indexOf("weibo") !=-1) {
							jsShareSns('weibo');
						}
						else if($(this).attr('class').indexOf("qq") !=-1) {
							jsShareSns('qq');
						}
					});
					
					$(".btn.sm.gray.btnGoList").on("click", function(){
						
						$("#lookbookListForm").submit();
						
					});
					
					// IE 9버전 이하일경우
					if(!history.replaceState) {
						
						jsShareSns = function(type, titleParam) {
							
							var sns_url = document.location.pathname + "?dspCtgryNo=" + $(".swiper-slide-active").data("div") + "&language=${_locale}";
					 	    var sns_media = $('[name=og_image]').attr("content");
						    var sns_title = "title";
						    var title = encodeURIComponent($("meta[name='og_title']").attr("content"));
					    	var pic = $("meta[name='og_image']").attr("content");
					    	var desc = encodeURIComponent($("meta[name='og_desc']").attr("content"));
						    if(titleParam != undefined && titleParam != ''){
								sns_title = titleParam;
								sns_title = sns_title.replace('`','\'');
							} else {
								sns_title = sns_title.toString().replace('`','\'');
							}
					 		sns_title = encodeURIComponent(sns_title);		
						    var cnrsSnsCd ='';
						    if (type == 'facebook') {
						    	cnrsSnsCd = 'FACEBUK';
						    	sns_url = encodeURIComponent(sns_url);	
						    	window.open("http://www.facebook.com/sharer.php?u=" + sns_url, "FaceBook", "height=500, width=620, scrollbars=yes");
						    }  
						    else if(type == 'kakaostory'){
						    	cnrsSnsCd = 'KKOST';
					            Kakao.Story.share({
					              url: sns_url
					            });
						    }
						    else if(type == 'kakaotak'){
						    	cnrsSnsCd = 'KKOTK';
						    }
						    else if(type == 'twitter'){
						    	cnrsSnsCd = 'TWITTER';
						    	sns_url = encodeURIComponent(sns_url);	
						    	window.open("http://www.twitter.com/intent/tweet?text=" + titleParam + "&url=" + sns_url, "FaceBook", "height=500, width=620, scrollbars=yes");
						    }
						    else if(type == 'naverline'){
						    	cnrsSnsCd = 'NAVERLINE';
						    	sns_url = encodeURIComponent(sns_url);	
						    	window.open("http://line.me/R/msg/text/" + sns_url, "FaceBook", "height=500, width=620, scrollbars=yes");
						    }
						    else if(type == 'weibo'){
						    	sns_url = encodeURIComponent(sns_url);
						    	var title = encodeURIComponent($("meta[name='og_title']").attr("content"));
						    	var pic = $("meta[name='og_image']").attr("content");
						    	
						    	if("" != pic){
						    		window.open("http://service.weibo.com/share/share.php?url=" + sns_url + "&title=" + title + "&pic=" + pic, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
						    	}else{
						    		window.open("http://service.weibo.com/share/share.php?url=" + sns_urll + "&title=" + title, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
						    	}
						    }
						    else if(type == 'qq'){
						    	cnrsSnsCd = "QQ";
						    	sns_url = encodeURIComponent(sns_url);
						    	
						    	if("" != pic){
						    		if("" != desc){
						    			window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title + "&pics=" + pic + "&summary=" + desc, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
						    		}else{
						    			window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title + "&pics=" + pic, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
						    		}
						    	}else{
						    		window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
						    	}
						    }
							
							$.ajax({
					            type : "GET",
					            url : '/event/snscnrshist.json',
					            async : true,
					            data : $.param({'${_csrf.parameterName}' : '${_csrf.token}',
					            	            'cnrsSnsCd' :cnrsSnsCd,
					            	            'cnrsUrl' :sns_url 
					            }),
								success : function(data) {
								},
								error: function(xhr) {
							    }
							});	
							
						} 
						
					}
					
					//상품정보변경
					$(".prds").on("click", ".option-color .btn-color.d_radio_select", function(e) {
						
						var $that = $(this);
						var conttSn = $that.closest(".item").data("div");
						
						if(!$that.hasClass("on")) {
							
							$that.siblings().removeClass("on");
							$that.addClass("on");
						}
							
						$.ajax({
						        type : "GET",
						        url : '/lookbook/godInfo',
						        async : true,
						        data : {
						        	'godNos' : $that.data("a")	
						        },
								success : function(data) {
									var innerHtml = "";
									
									if(data) {
										
										innerHtml = $(data).html();
										$("div[data-div=" + conttSn + "]").empty().append(innerHtml);							
										
									}
									
								},
								error: function(req, status, err) {

								}
						});
							
					//사이즈변경	
					}).on("click", ".option-size .btn-size.d_radio_select", function(e) {
						
						var $that = $(this);
						
						if(!$that.hasClass("on")) {
							
							$that.siblings().removeClass("on");
							$that.addClass("on");
						}
					//장바구니
					}).on("click", ".bts > .btn.fill.sm.btnAddCart", function(e) {
						
						if( BASE.locale !== "ko" ) {
							
							layerPopup.popupOpenNow("#layerPopupFindStoreMap");
							return false;
							
						} 
						
						var sizeOption = $.trim($(this).closest(".item").find(".btn-size.d_radio_select.on").children().text()) || "";
						var erpGodNo = $(this).parent().data("div").split("/").reverse()[0];
						
						if(sizeOption === "") {
							alert("<spring:message code='display.lookbook.cart.not'/>")
							return false;
						}
						
						$.ajax({
					        type : "GET",
					        url : "/lookbook/godDetailInfo",
					        data : {
					        	"erpGodNo" : erpGodNo,
					        	"basketOrWish" : "B"
					        },
					        async : true,
							success : function(data) {
								
								if(data) {

									$("#goodsInfoArea").empty().append($(data).not("#goodsWishlistForm")).children().ready(
											
											function(){
												
												$("#bskGodItmNo").val( $("input[name=" + sizeOption + "]").val() );
												
												goBasket();
												
												$("#goodsInfoArea").empty();
										
											}
											
									);
									
								}
								
							},
							error: function(xhr, status, error) {
								$("#goodsInfoArea").empty();
						    }
						
						});
						
					}).on("click", ".bts > .btn.sm.btnWish", function(e) {
						
						var $that = $(this);
						var godNo = $(this).data("a");
						var erpGodNo = $(this).parent().data("div").split("/").reverse()[0];
						//로그인여부
						if(loginResult) {
							
							//위시리스트에 이미 담겨있는 상품일 경우
							if($that.hasClass("active")) {
								alert("<spring:message code='display.lookbook.wishlist.check'/>");
								return false;
							}
							
							$.ajax({
						        type : "GET",
						        url : "/lookbook/godDetailInfo",
						        data : {
						        	"erpGodNo" : erpGodNo,
						        	"basketOrWish" : "W"
						        },
						        async : true,
								success : function(data) {
									
									if(data) {

										$("#goodsInfoArea").empty().append($(data).not("#goodsForm")).children().ready(
												
												function(){
											
													goodsPixclTrack("AddToWishlist");
													//btn-like d_icon_toggle on
													$.ajax({
														type : "POST",
														async : false,
														url : "/mypage/wishlist/insert.json",
														data : $("#goodsWishlistForm").serialize(),
														success : function(data) {
															
															if(data.msg == 'success'){			
																$("a[data-a=" + godNo + "]").addClass("active");
															   	alert("<spring:message code='display.lookbook.wishlist.add'/>");
															}else{
																alert(data.msg);
															}
														},
														error: function(req, status, err) {
														        alert("error forward : "+err+" ,status="+status);
														    }
													});
												 
													$("#goodsInfoArea").empty();
											
												}
												
										);
										
									}
									
								},
								error: function(req, status, error) {
									$("#goodsInfoArea").empty();
							    }
							
							});
							
						} else {
							//로그인팝업 호출
							openLayerPopupForLogin();
							
						}
						
					});
					
				});
				
				</script>

			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->
	<script>

	/* ********************************************/
	/* 5. 장바구니 담기      	                      */
	/* ********************************************/

	//장바구니 담기
	function goBasket(){	
	   
		//장바구니 데이터 설정
		addBasket();
	}
	
	//장바구니 등록
	function addBasket() {
		
		goodsPixclTrack("AddToCart");
		recoPick('sendLog','basket', {id:$("#erpGodNo").val(), count:$("#bskGodItmQty").val()});

		$.ajax({
			type : "POST",
			async : false,
			url : "/cart/goods/add.json",
			data : $("#goodsForm").serialize(),
			success : function(data, status, req) {
				if(data.msg == 'success'){	
					if(confirm("<spring:message code='display.lookbook.cart.add'/>")) {
						return location.href = "/cart/goods/list";
					}
					gnbMiniCart.load();
				}else{
					return alert(data.errMsg);
				}
				
			},
			error: function(req, status, err) {
				alert("error forward : "+err+" ,status="+status);
	        }
		});	
		
	}

	//로그인
	function memberLogin(){
		openLayerPopupForLogin();	  
	}

	function goodsPixclTrack(eventCode) {	
	 	fbq('track', eventCode);
	 };
	</script>
