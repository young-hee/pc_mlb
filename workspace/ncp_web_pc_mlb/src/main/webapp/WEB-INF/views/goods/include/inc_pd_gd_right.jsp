<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<div class="area right">
	<div class="info prds">
		<!-- 예약판매 -->
		<c:if test="${goods.godEx.resveSaleGodYn eq 'Y'}">
			<div class="reserve"><em class="t"><spring:message code="goods.option.lbl.reserve"/></em></div>
		</c:if>
		<div class="name">
			<c:if test="${goods.godEx.brndId eq 'I'}">[KIDS]</c:if>
			<c:out value="${goodsNm}"/>
		</div>
		<span class="code"><c:out value="${goods.godEx.erpGodNo}"/></span>
		<c:if test="${pageContext.response.locale.language eq 'ko'}">
			<div class="cupon_msg"><span style='color:<c:out value="${goods.godEx.colorTagNm}"/>'><c:out value="${goods.godEx.tagNm}"/></span></div>
		</c:if>
		
		<div class="price norm">
			<div class="prc">
				<c:if test="${goods.dspGodPrc.rtlPrc eq goods.dspGodPrc.csmrPrc}">
					<span class="p n"><fmt:formatNumber value="${goods.dspGodPrc.rtlPrc}" type="number"/> <em><spring:message code="goods.common.lbl.won"/></em></span> 
				</c:if>
				<c:if test="${goods.dspGodPrc.rtlPrc ne goods.dspGodPrc.csmrPrc}">
					<span class="p s"><fmt:formatNumber value="${goods.dspGodPrc.rtlPrc}" type="number"/> <em><spring:message code="goods.common.lbl.won"/></em></span> 
					<span class="p n"><fmt:formatNumber value="${goods.dspGodPrc.csmrPrc}" type="number"/> <em><spring:message code="goods.common.lbl.won"/></em></span>
				</c:if>
			</div>
			<!-- 쿠폰가 -->
			<c:if test="${content.prmCpnGod.lastDcAmt > 0}">
				<div class="prc cp">
					<span class="p">
						<!-- 다운로드 -->
						<c:if test="${content.prmCpnGod.cpnIssuMthdCd eq 'DWLD'}">	
							<c:if test="${pageContext.response.locale.language eq 'ko'}">
								<strong><spring:message code="goods.option.lbl.coupon.txt1"/></strong> 
								<fmt:formatNumber value="${content.prmCpnGod.lastDcAplAmt}" type="number"/>
								<em><spring:message code="goods.common.lbl.won"/></em>
							</c:if>
						</c:if>
						<!-- 즉시할인 -->
						<c:if test="${content.prmCpnGod.cpnIssuMthdCd eq 'IMDTL_DC'}">	
							<strong><spring:message code="goods.promotion.lbl.imdtl.sale"/></strong> 
							<fmt:formatNumber value="${content.prmCpnGod.lastDcAplAmt}" type="number"/>
							<em><spring:message code="goods.common.lbl.won"/></em>
						</c:if>	
					</span>
				</div>
			</c:if>
		</div>
		<div class="mile">
			<div>
				<c:if test="${'Y' eq goods.godEx.pntAccmlYn }">	
					<div class="mil">					
						<spring:message code="goods.promotion.lbl.accml.expect.pnt" text="예상마일리지"/>: 
						<span><fmt:formatNumber value="${goods.godEx.pntSavMny }" type="number"/> (<c:out value="${goods.godEx.pntAccmlRt }"/>%)</span>
					</div>																	
				</c:if>
				<!-- tmp | 신규회원쿠폰안내 : S -->
				<c:if test="${_locale eq 'ko' and !empty content.prmCpnDspCtgry}">
					<div class="mil coupon">					
						${content.prmCpnDspCtgry.prmNm}										
					</div>	
				</c:if>
				<div class="mil free"><spring:message code="goods.promotion.lbl.free.ship" text="공식몰 전체품 무료배송"/></div>
				<%-- <c:if test="${_locale eq 'ko'}">
					<div class="mil card text-color01"><spring:message code="goods.promotion.lbl.free.card" text="국민카드 7% 추가 할인 (20만원 이상 결제 시)"/></div>
				</c:if> --%>
			</div>
			<!-- 다운로드 쿠폰인 경우 -->
			<c:if test="${content.prmCpnGod.cpnIssuMthdCd eq 'DWLD'}">
				<c:if test="${pageContext.response.locale.language eq 'ko'}">
					<!-- 로그인 한 유저 : 쿠폰 다운로드 팝업 -->
					<sec:authorize access="hasAnyRole('ROLE_USER')">
						<c:choose>
							<c:when test="${content.prmCpnGod.dcAmt > 0}">
								<div class="bts"><a href="#" id="btn_couponDownload" class="bt-coupon"><fmt:formatNumber value="${content.prmCpnGod.dcAmt }" type="number"/> <spring:message code="goods.js.common.lbl.won"/> <spring:message code="goods.option.lbl.coupon.txt0"/></a></div>
							</c:when>
							<c:otherwise>
								<div class="bts"><a href="#" id="btn_couponDownload" class="bt-coupon"><c:out value="${content.prmCpnGod.dcRt}"/>% <spring:message code="goods.option.lbl.coupon.txt0"/></a></div>
							</c:otherwise>
						</c:choose>
					</sec:authorize>
					<!-- 모르는넘 : 로그인페이지 이동 -->
					<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
						<c:choose>
							<c:when test="${content.prmCpnGod.dcAmt > 0}">
								<div class="bts"><a href="#" onclick="javascript:memberLogin();" class="bt-coupon"><fmt:formatNumber value="${content.prmCpnGod.dcAmt }" type="number"/> <spring:message code="goods.js.common.lbl.won"/> <spring:message code="goods.option.lbl.coupon.txt0"/></a></div>
							</c:when>
							<c:otherwise>
								<div class="bts"><a href="#" onclick="javascript:memberLogin();" class="bt-coupon"><c:out value="${content.prmCpnGod.dcRt}"/>% <spring:message code="goods.option.lbl.coupon.txt0"/></a></div>
							</c:otherwise>
						</c:choose>
					</sec:authorize>
				</c:if>
			</c:if>
		</div>
	</div>
		
	<!-- 일반상품 -->		
	<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">
		<div class="info order">
			<ul class="dlist">
			<li class="color">
				<div class="dt"><spring:message code="goods.option.lbl.color0" text="다른색상/팀"/></div>
				<div class="dd">
					<div class="option-color">
						<c:if test="${fn:length(goods.designColorList) > 0}">
							<c:forEach var="list" items="${goods.designColorList}">
							
										<c:choose>
										    <c:when test="${goods.godEx.erpGodNo eq list.erpGodNo}">
								<a class="current" href="${list.godDetailUrl }" target="_self" title="${list.colorCd}"><img src="${imageURL}${list.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_38x51)%>" alt="${list.colorCd}" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_38x51)%>');"></a>
										     
										    </c:when>
										    <c:otherwise>
								<a  href="${list.godDetailUrl }" target="_self" title="${list.colorCd}"><img src="${imageURL}${list.imgUrl}/dims/resize/<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_38x51)%>" alt="${list.colorCd}" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_38x51)%>');"></a>
										    
										    </c:otherwise>
										</c:choose>
 							</c:forEach>
						</c:if>
						<c:if test="${empty goods.designColorList}">
							<a class="current" href="${list.godDetailUrl }" target="_self" title="${list.colorCd}"><img src="" alt="${list.colorCd}" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_38x51)%>');"/></a>
						</c:if>				
					</div>
				</div>
			</li>
			<li class="size">
				<div class="dt">
					<c:if test="${goods.godEx.godNo ne 'GM0019050713788' && goods.godEx.godNo ne 'GM0019050713787'}">
						<spring:message code="goods.option.lbl.size"/>
					</c:if>
					<c:if test="${goods.godEx.godNo eq 'GM0019050713788' || goods.godEx.godNo eq 'GM0019050713787'}">
						자수번호
					</c:if>
				</div>
				<div class="dd">
					<div class="payment-option-size">
						<!-- 리미티드 개발 --> 
						<c:if test="${goods.godEx.godNo ne 'GM0019050713788' && goods.godEx.godNo ne 'GM0019050713787'}">
							<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">									
	                               <c:choose>
									<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">												
										<c:set var="invQty"/>
										<!-- 예약판매 체크 -->
										<c:if test="${goods.godEx.resveSaleGodYn eq 'Y' }">
											<c:set var="invQty" value="${size.resveInvQty}"/>
										</c:if>
										<c:if test="${goods.godEx.resveSaleGodYn eq 'N' }">
											<c:set var="invQty" value="${size.totUsefulInvQty}"/>
										</c:if>
									
										<button type="button" class="btn-size d_radio_select" onclick="javascript:sizeChange('0','${size.itmNo}','${invQty}','${size.salePrearngeQty }', false);">
											<span><c:out value="${size.itmNm}"/></span>									
										</button>
									</c:when>
									<c:when test="${size.itmStatCd ne 'SALE_END'}">
	                 					<button type="button" class="btn-size d_radio_select" disabled><c:out value="${size.itmNm}"/></button>
									</c:when>
								</c:choose>											
							</c:forEach>
						</c:if>
						
						<c:if test="${goods.godEx.godNo eq 'GM0019050713788' || goods.godEx.godNo eq 'GM0019050713787'}">
							
							<c:if test="${goods.godEx.godNo eq 'GM0019050713788'}">
							<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width: 152px;"><span>자수번호</span></button>
							</c:if>
							
							<c:if test="${goods.godEx.godNo eq 'GM0019050713787'}">
							<div class="select-style02 d_select">
							<button type="button" class="d_select_sel" style="width: 152px;"><span>자수번호</span></button>
							</c:if>
							
								
								<ul>
									<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">
										<c:if test="${size.itmStatCd eq 'SALE_PROGRS'}">
											<li>
												<a href="javascript:;" onclick="sizeChange('0','${size.itmNo}','${invQty}','${size.salePrearngeQty }', false);">
													<c:out value="${size.itmNm}"/>
												</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
						</c:if>
					</div>
					<div class="pdt-guide-box">
						<c:if test="${not empty goods.godNtfcDetail.sizeLktbHtml}">
							<a href="#" id="btn_sizeGuide_GNRL_GOD" class="more-guide"><spring:message code="goods.option.lbl.size.guide"/></a>
						</c:if>					
						<c:if test="${not empty goods.godFitLktbEx.fitLktbHtml}">
							<a href="#" id="btn_fitGuide_GNRL_GOD" class="more-guide2"><spring:message code="goods.lbl.fit.guide"/></a>
						</c:if>
					</div>
					<input type="hidden" name="itmNo" id="itmNo0" value=""/>
					<input type="hidden" name="itmNm" id="itmNm0" value=""/>
				</div>
			</li>
			<c:if test="${not isGoodSoldOut}">
				<li class="amount">
					<div class="dt"><spring:message code="goods.option.lbl.quantity"/></div>
					<div class="dd">
						<div class="quantity-wrap">
							<button type="button" onclick="javascript:changeQty('minus'); return false;" class="pq-minus">빼기</button>
							<button type="button" onclick="javascript:changeQty('add'); return false;" class="pq-plus">추가</button>
							<input type="number" id="qty" title="수량" value="${goods.godEx.minOrdQty}" data-min-ord-qty="${goods.godEx.minOrdQty}" data-max-ord-qty="${goods.godEx.maxOrdQty}" onkeyup="javascript:onlyNumber(this);" onblur="javascript:changeQty('');">
						</div>
					</div>
				</li>						
			</c:if>	
		</ul>
	</div>
	</c:if>
	<!--// 일반상품 -->	
	<!-- 셋트상품 -->	
	<c:if test="${goods.godEx.godTpCd eq 'SET_GOD'}">
		<div class="info order">
			<ul class="dlist">
			<!-- 세트 상품 -->
			<c:forEach var="list" items="${goods.godItmExList}">									
				<input type="hidden" name="masterItmNo" id="masterItemNo" value="${list.itmNo}"/>									
			</c:forEach>
			<c:forEach var="list" items="${goods.godCpstGodCnncExList}" varStatus="status">
				<li class="size">
					<div class="dt"><spring:message code="goods.option.lbl.protuct"/>&nbsp;<c:out value="${status.count}"/><spring:message code="goods.option.lbl.size"/></div>
					<div class="dd">
						<div class="payment-option-size">
							<c:forEach var="size" items="${goods.cpstGodCnncItmExList}">
								<c:if test="${list.cpstGodNo eq size.godNo}">								
	                            	<c:choose>
										<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">												
											<c:set var="invQty"/>
											<button type="button" class="btn-size d_radio_select" onclick="javascript:sizeChange('${status.index}','${size.itmNo}','${size.totUsefulInvQty}','${size.salePrearngeQty }', false);">
												<span><c:out value="${size.itmNm}"/></span>									
											</button>
										</c:when>
										<c:when test="${size.itmStatCd ne 'SALE_END'}">
		                 					<button type="button" class="btn-size d_radio_select" disabled><c:out value="${size.itmNm}"/></button>
										</c:when>
									</c:choose>	
								</c:if>										
							</c:forEach>
						</div>
						<c:if test="${not empty list.cpstSizeLktbHtml}">
							<a href="#layerPopupSizeGuide_${list.cpstGodNo}" class="more-guide d_layer_open" title="도움말"><spring:message code="goods.option.lbl.size.guide"/></a>
						</c:if>
					</div>
					<input type="hidden" name="cpstGodNm" 	id="cpstGodNm${status.index}" value="${list.cpstGodNm}">
					<input type="hidden" name="cpstGodNo" 	id="cpstGodNo${status.index}" value="${list.cpstGodNo}">								
					<input type="hidden" name="cpstGodQty" 	id="cpstGodQty${status.index}" value="1">
					<input type="hidden" name="sortSeq" 	id="sortSeq${status.index }" value="${list.sortSeq}">
					<input type="hidden" name="itmNo" 		id="itmNo${status.index}" value="">
				</li>
			</c:forEach>
			<li class="amount">
				<div class="dt"><spring:message code="goods.option.lbl.quantity"/></div>
				<div class="dd">
					<div class="quantity-wrap">
						<button type="button" onclick="javascript:changeQty('minus'); return false;" class="pq-minus">빼기</button>
						<button type="button" onclick="javascript:changeQty('add'); return false;" class="pq-plus">추가</button>
						<input type="number" id="qty" title="수량" value="${goods.godEx.minOrdQty}" data-min-ord-qty="${goods.godEx.minOrdQty}" data-max-ord-qty="${goods.godEx.maxOrdQty}" onkeyup="javascript:onlyNumber(this);" onblur="javascript:changeQty('');">
					</div>
				</div>
			</li>
			</ul>
		</div>
		<!-- 추가구성품 경우 -->
		<c:set var="loopFlag" value="false"/>
		<c:forEach var="list" items="${goods.godCpstGodCnncExList}" varStatus="status">
			<c:if test="${not loopFlag}">
				<c:if test="${list.aditPchPsbYn eq 'Y'}">
				<div class="info order">
					<ul class="dlist">
						<li class="size">
							<div class="dt"><spring:message code="goods.option.lbl.add.protuct"/></div>
							<div class="dd">
								<div class="payment-option-size">
									<c:forEach var="size" items="${goods.cpstGodCnncItmExList}">
										<c:if test="${list.cpstGodNo eq size.godNo}">								
			                            	<c:choose>
												<c:when test="${size.itmStatCd eq 'SALE_PROGRS'}">												
													<button type="button" class="btn-size d_radio_select" onclick="additSizeChange(this,'${size.itmNo}','${size.totUsefulInvQty}')">
														<span><c:out value="${size.itmNm}"/></span>									
													</button>
												</c:when>
												<c:when test="${size.itmStatCd ne 'SALE_END'}">
				                 					<button type="button" class="btn-size d_radio_select" disabled><c:out value="${size.itmNm}"/></button>
												</c:when>
											</c:choose>	
										</c:if>										
									</c:forEach>
								</div>
								<c:if test="${not empty list.cpstSizeLktbHtml}">
									<a href="#layerPopupSizeGuide_${list.cpstGodNo}" class="more-guide d_layer_open" title="도움말"><spring:message code="goods.option.lbl.size.guide"/></a>
								</c:if>
							</div>
							<input type="hidden" name="additCpstGodNm"  id="additCpstGodNm"  value="${list.cpstGodNm}">
							<input type="hidden" name="additCpstGodNo"  id="additCpstGodNo"  value="${list.cpstGodNo}">								
							<input type="hidden" name="additCsmrPrc"  	id="additCsmrPrc" 	 value="${list.csmrPrc}">								
							<input type="hidden" name="additCpstGodQty" id="additCpstGodQty" value="1">
							<input type="hidden" name="additItmNo" 		id="additItmNo" 	 value="">	
						</li>
						<li class="amount">
							<div class="dt"><spring:message code="goods.option.lbl.quantity"/></div>
							<div class="dd">
								<div class="quantity-wrap">
									<button type="button" onclick="javascript:additChangeQty('minus'); return false;" class="pq-minus">빼기</button>
									<button type="button" onclick="javascript:additChangeQty('add'); return false;" class="pq-plus">추가</button>
									<input type="number" id="additQty" title="수량" value="0" onkeyup="javascript:onlyNumber(this);" onblur="javascript:additChangeQty('');">
								</div>
							</div>
							<c:set var="loopFlag" value="true"/>	
						</li>
					</ul>
				</div>
				</c:if>
			</c:if>
		</c:forEach>
	</c:if>
	<!--// 셋트상품 -->	
	
	<!-- 배송 -->
	<c:if test="${not isGoodSoldOut}">
		<c:set var="dlvCstLevySectCd" value="${goods.comDmstcDlvCstPlc.dlvCstLevySectCd}"/>
		<c:set var="dmstcDlvCst" value="${goods.comDmstcDlvCstPlc.dmstcDlvCst}"/>
		<c:set var="dmstcDlvCstExmStdrAmt" value="${goods.comDmstcDlvCstPlc.dmstcDlvCstExmStdrAmt}"/>
		<div class="info order">
			<ul class="dlist">
				<li class="deli">
					<div class="dt"><spring:message code="goods.option.lbl.dlvr.txt"/></div>
					<div class="dd">
						<ul class="radio-list">
							<c:choose>
								<c:when test="${dlvCstLevySectCd eq 'COND_FREE'}">
									<c:if test="${goods.dspGodPrc.lastSalePrc < dmstcDlvCstExmStdrAmt}">
										<li>											
											<span class="rdo-skin">
												<input type="radio" id="rdoType01" name="dlvSect" value="GNRL_DLV" checked="checked">
												<span>선택</span>
											</span><label for="rdoType02"><spring:message code="goods.option.lbl.dlvr.txt2"/></label>
											<div class="tooltip-wrap d_dropdown">
												<button type="button" class="tooltip-detail d_dropdown_sel" >도움말</button>
												<!-- 툴팁 레이어 -->
												<div class="tooltip-layer d_dropdown_cont">
													<strong class="tooltip-title"><spring:message code="goods.option.lbl.dlvr.info.ttl"/></strong>
													<div class="tooltip-cnt">
														<ul class="text-list02 col-type01 bul-list">
															<spring:message code="goods.option.lbl.dlvr.info2"/>
														</ul>
													</div>
													<button type="button" class="tooltip-close d_dropdown_close">닫기</button>
												</div>
												<!-- //툴팁 레이어 -->
											</div>													
										</li>											
									</c:if>
									<c:if test="${goods.dspGodPrc.lastSalePrc >= dmstcDlvCstExmStdrAmt}">
										<li>
											<span class="rdo-skin">
												<input type="radio" id="rdoType01" name="dlvSect" value="GNRL_DLV" checked="checked">
												<span>선택</span>
											</span><label for="rdoType03"><spring:message code="goods.option.lbl.dlvr.txt1"/></label>
											<div class="tooltip-wrap d_dropdown">
												<button type="button" class="tooltip-detail d_dropdown_sel" >도움말</button>
												<!-- 툴팁 레이어 -->
												<div class="tooltip-layer d_dropdown_cont">
													<strong class="tooltip-title"><spring:message code="goods.option.lbl.dlvr.info.ttl1"/></strong>
													<div class="tooltip-cnt">
														<ul class="text-list02 col-type01 bul-list">
															<spring:message code="goods.option.lbl.dlvr.info1"/>
														</ul>
													</div>
													<button type="button" class="tooltip-close d_dropdown_close">닫기</button>
												</div>
												<!-- //툴팁 레이어 -->
											</div>
										</li>
									</c:if>
								</c:when>
								<c:when test="${dlvCstLevySectCd eq 'FREE'}">
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType01" name="dlvSect" value="GNRL_DLV" checked="checked">
											<span>선택</span>
										</span><label for="rdoType03"><spring:message code="goods.option.lbl.dlvr.txt1"/></label>
										<div class="tooltip-wrap d_dropdown">
											<button type="button" class="tooltip-detail d_dropdown_sel" >도움말</button>
											<!-- 툴팁 레이어 -->
											<div class="tooltip-layer d_dropdown_cont">
												<strong class="tooltip-title"><spring:message code="goods.option.lbl.dlvr.info.ttl1"/></strong>
												<div class="tooltip-cnt">
													<ul class="text-list02 col-type01 bul-list">
														<spring:message code="goods.option.lbl.dlvr.info1"/>
													</ul>
												</div>
												<button type="button" class="tooltip-close d_dropdown_close">닫기</button>
											</div>
											<!-- //툴팁 레이어 -->
										</div>
									</li>
								</c:when>
								<c:when test="${dlvCstLevySectCd eq 'PCHRG'}">
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType01" name="dlvSect" value="GNRL_DLV" checked="checked">
											<span>선택</span>
										</span><label for="rdoType02"><spring:message code="goods.option.lbl.dlvr.txt2"/></label>
										<div class="tooltip-wrap d_dropdown">
											<button type="button" class="tooltip-detail d_dropdown_sel" >도움말</button>
											<!-- 툴팁 레이어 -->
											<div class="tooltip-layer d_dropdown_cont">
												<strong class="tooltip-title"><spring:message code="goods.option.lbl.dlvr.info.ttl"/></strong>
												<div class="tooltip-cnt">
													<ul class="text-list02 col-type01 bul-list">
														<spring:message code="goods.option.lbl.dlvr.info1"/>
													</ul>
												</div>
												<button type="button" class="tooltip-close d_dropdown_close">닫기</button>
											</div>
											<!-- //툴팁 레이어 -->
										</div>			                                    
									</li>			                                    
								</c:when>
							</c:choose>	
							<!-- 매장픽업(예약상품일때 매장픽업 없음) -->
							<c:if test="${goods.godEx.shopPkupPsbYn eq 'Y' and goods.godEx.resveSaleGodYn ne 'Y' and goods.godEx.godTpCd ne 'SET_GOD'}">
								<li>
									<span class="rdo-skin">
										<input type="radio" id="rdoType02" name="dlvSect" value="PKUP_DLV">
										<span>선택</span>
									</span><label for="rdoType04"><spring:message code="goods.option.lbl.dlvr.txt3"/></label>
									<a href="#layerPopupPickupService" class="btn-info d_layer_open">매장픽업 서비스 안내</a>
								</li>						
							</c:if>	
						</ul>
					</div>
				</li>
			</ul>
		</div>
	</c:if>	
	<!--// 배송 -->
	
	<div class="info tool">
		<c:choose>
			<c:when test="${not isGoodSoldOut}">
				<c:if test="${goods.godEx.godTpCd eq 'SET_GOD'}">
					<div class="total">
						<span class="tit"><spring:message code="goods.option.lbl.total"/></span>
						<span class="price"><em class="p" id="totalPrice"><fmt:formatNumber value="${goods.dspGodPrc.lastSalePrc}" type="number"/></em><em class="n"><spring:message code="goods.common.lbl.won"/></em></span>
					</div>
				</c:if>
				<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">
					<c:if test="${goods.godEx.godNo ne 'GM0019050713788' && goods.godEx.godNo ne 'GM0019050713787'}">
					<c:if test="${goods.optSoldYn eq 'Y'}">
						<c:if test="${pageContext.response.locale.language eq 'ko' and goods.godEx.dspYn eq 'Y'}">
							<div class="alim"><a href="#" class="d_layer_open" onclick="popGoodsInform()"><spring:message code="goods.inform.ttl"/></a></div>
						</c:if>
					</c:if>
					</c:if>
				</c:if>
				<c:if test="${pageContext.response.locale.language eq 'ko' and goods.godEx.dspYn eq 'Y'}">
					<div class="bts def">
						<a class="btn xl bt-buy" href="javascript:;" onclick="addOrder('N');"><spring:message code="goods.option.btn.order"/></a>
						<!-- 리미티드 모자 개발 -->
						<c:if test="${goods.godEx.godNo ne 'GM0019050713788' && goods.godEx.godNo ne 'GM0019050713787'}">
							<a class="btn xl bt-cart" href="javascript:;" onclick="goBasket();"><spring:message code="goods.option.btn.bsk"/></a>
						</c:if>
						<c:if test="${goods.godEx.brndGrpId eq 'ML'}">
							<a class="btn xl bt-npay" href="javascript:;" onclick="addOrder('Y');">NPay</a>
						</c:if>
					</div>
				</c:if>
			</c:when>
			<c:otherwise>
				<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">
					<c:if test="${goods.optSoldYn eq 'Y'}">
						<c:if test="${pageContext.response.locale.language eq 'ko'}">
							<div class="alim"><a href="#" class="d_layer_open" onclick="popGoodsInform()"><spring:message code="goods.inform.ttl"/></a></div>
						</c:if>
					</c:if>
				</c:if>			
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${goods.godEx.dspYn eq 'Y'}">
				<c:if test="${goods.godEx.godSaleSectCd eq 'SLDOUT'}">
					<div class="bts one">
						<span class="btn xl bt-sold"><spring:message code="goods.option.btn.soldout"/></span>
					</div>
				</c:if>
				<c:if test="${goods.godEx.godSaleSectCd eq 'SMTM_SLDOUT'}">
					<div class="bts one">
						<span class="btn xl bt-soon"><spring:message code="goods.option.btn.comingup"/></span>
					</div>
				</c:if>	
			</c:when>
			<c:otherwise>
				<div class="bts one">
					<span class="btn xl fill bt-go"><spring:message code="goods.unexhibited.text1"/></span>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- 영,중  : "해외매장 안내" -->
		<c:if test="${pageContext.response.locale.language ne 'ko'}">
			<c:if test="${goods.godEx.dspYn eq 'Y'}">
				<div class="bts one">
					<a class="btn xl fill bt-go d_layer_open" href="#layerPopupFindStoreMap"><spring:message code="goods.option.btn.find.store"/></a>
				</div>
			</c:if>
		</c:if>
	</div>
	
	<div class="info data">
		<span class="hits">
			<span class="share">
				<a class="bt" href="javascript:;" id="btn_goods_share">공유</a>
				<input type="hidden" name="twitterTitle" id="twitterTitle" value="${goodsNm}">
				<ul class="list" id="ulShare">
					<li class="weibo"><a href="javascript:;">weibo</a></li>
					<li class="qq"><a href="javascript:;">QQ</a></li>
					<li class="facebook"><a href="javascript:;" class="btn-share type01"><spring:message code="goods.option.btn.share.alt.txt1"/></a></li>
					<li class="twitter"><a href="javascript:;" class="btn-share type02"><spring:message code="goods.option.btn.share.alt.txt2"/></a></li>
					<li class="line"><a href="javascript:;" class="btn-share type04"><spring:message code="goods.option.btn.share.alt.txt4"/></a></li>
					<li class="url"><a href="javascript:;" class="btn-share type03"><spring:message code="goods.option.btn.share.alt.txt3"/></a></li>
				</ul>
			</span>
			<c:if test="${pageContext.response.locale.language ne 'ko'}">
				<c:if test="${goods.godEx.dspYn eq 'Y'}">
					<span class="cart"> <!-- 2019.01.21 영중문일때  추가 -->
						<a class="bt" href="javascript:;" onclick="goBasket2();"><spring:message code="goods.option.btn.bsk"/></a>
					</span>
				</c:if>
			</c:if>
			<c:if test="${pageContext.response.locale.language eq 'ko'}">
				<c:if test="${goods.godEx.dspYn eq 'Y'}">
					<span class="wish">
						<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
					    	<a href="javascript:memberLogin();" class="bt ${wishListYn}" title="<spring:message code="goods.option.btn.wishlist.alt.txt"/>"></a>
					    	<em id="wishListCount">
						    	<c:if test="${wishListCount > 0}">
						    		${wishListCount }
						    	</c:if>
					    	</em>
						</sec:authorize>
						<sec:authorize access="hasAnyRole('ROLE_USER')">
							<a href="javascript:addWishList(this);" id="addWishList" class="bt ${wishListYn}" title="<spring:message code="goods.option.btn.wishlist.alt.txt"/>"></a>
							<em class="num" id="wishListCount">
								<c:if test="${wishListCount > 0}">
						    		${wishListCount }
						    	</c:if>
							</em>
						</sec:authorize>
					</span>
				</c:if>
			</c:if>
		</span>
		
	</div>

</div>