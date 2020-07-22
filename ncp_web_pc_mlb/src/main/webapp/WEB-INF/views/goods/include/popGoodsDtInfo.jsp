<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<article id="lypopGoodsDtInfo" class="layer-popup lyPopStyle">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<div class="layer-top">
			<ul class="lyTabStyle">
				<li class="on"><a href="javascript:;"><spring:message code="goods.noti.ttl"/></a></li>
				<li><a href="javascript:;"><spring:message code="goods.review.ttl"/></a></li>
				<c:if test="${pageContext.response.locale.language eq 'ko'}">
					<li><a href="javascript:;"><spring:message code="goods.dlvr.common.ttl"/></a></li>
				</c:if>
			</ul>
		</div>
		<div class="layer-cont ly-box scroll">		
			
			<!-- ** 상품세부정보 -->
			<div class="lypopGoodsDetail lyTabCont on">
				<!-- 일반상품 고시정보 -->
				<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">
					<table class="board-list lyType01">
						<colgroup>
							<col style="width:160px">
							<col style="width:">			
						</colgroup>
						<tbody>
							<!-- 상품정보 : 없으면 안보영 -->
							<c:if test="${not empty goods.godNtfcDetail.godDetailDscr }">	
							<tr>
								<th><spring:message code="goods.noti.lbl.goods.info"/></th>
								<td><c:out value="${goods.godNtfcDetail.godDetailDscr}" escapeXml="false"/></td>	
							</tr>
							</c:if>
							<!-- 소재 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.material"/></th>
								<td><c:out value="${goods.godNtfcDetail.matrDscr}"/></td>
							</tr>
							<!-- 원산지 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.manufacture.country"/></th>
								<td><c:out value="${goods.godNtfcDetail.mnfcturNationNm}"/></td>
							</tr>
							<!-- 출시년도 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.manufacture.year"/></th>
								<td>						
									<c:set var="year" /> 
									<c:set var="month" />					
									<c:if test="${not empty goods.godNtfcDetail.mnfcturDate }">						
										<c:set var="year" value="${fn:substring(goods.godNtfcDetail.mnfcturDate, 0, 4) }"/>
										<c:set var="month" value="${fn:substring(goods.godNtfcDetail.mnfcturDate, 4, 6) }"/>						
									 	<c:out value="${year}"/>.<c:out value="${month}"/>
									</c:if>
								</td>
							</tr>
							<!-- 품질보증기간 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.as.year"/></th>
								<td><spring:message code="goods.noti.lbl.as.year.dscr"/></td>
							</tr>
							<!-- a/s문의 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.as.qna"/></th>
								<td><spring:message code="goods.noti.lbl.as.qna.dscr"/></td>
							</tr>
							<!-- 세탁방벙 -->
							<tr>
								<th><spring:message code="goods.noti.lbl.laundry.method"/></th>
								<td>
									<ul class="wash">
									<c:forEach var="list" items="${goods.godLndrImgList}">
										<c:if test="${list.lndrImgUseYn eq 'Y'}">
											<li>
												<em class="iconWash"><img src="${imageURL}${list.lndrImgFileUrl}" alt="${list.lndrImgAltrtvCont}"></em>
												<div class="txt">${list.lndrImgAltrtvCont}</div>
											</li>
										</c:if>								
									</c:forEach>		
									</ul>								
								</td>
							</tr>
							<!-- 안전인증 : 없음 안 보영 -->
							<c:if test="${not empty goods.godEx.kcCrtfcNo}">				
							<tr>
								<th><spring:message code="goods.noti.lbl.kc"/></th>
								<td>
									<dl class="markInfo">
										<dt><img src="${imageURL}/goods/notification/mark_kc.jpg" alt="<spring:message code="goods.noti.lbl.kc.alt.txt"/>"></dt>
										<dd><spring:message code="goods.noti.lbl.kc.alt.txt"/></dd>
										<dd><spring:message code="goods.noti.lbl.kc.no"/> : ${goods.godEx.kcCrtfcNo}</dd>
									</dl>					
								</td>
							</tr>
							</c:if>
						</tbody>
					</table>
				</c:if>
				<!--// 일반상품 고시정보 -->
				<!-- 세트상품 고시정보 -->
				<c:if test="${goods.godEx.godTpCd eq 'SET_GOD'}">
					<c:forEach var="cpstCnncGod" items="${goods.godCpstGodCnncExList}">								
						<c:forEach var="cpstCnncNtfc" items="${goods.godNtfcDetailList}">
							<c:if test="${cpstCnncGod.cpstGodNo eq cpstCnncNtfc.godNo}">
								<strong class="gdTit"><c:out value="${cpstCnncGod.cpstGodNm}"/></strong><!-- 2018.01.09 상품명 추가 -->
								<table class="board-list lyType01">
									<colgroup>
										<col style="width:160px">
										<col style="width:">			
									</colgroup>
									<tbody>
										<!-- 상품정보 : 없으면 안보영 -->
										<c:if test="${not empty cpstCnncNtfc.godDetailDscr }">	
										<tr>
											<th><spring:message code="goods.noti.lbl.goods.info"/></th>
											<td><c:out value="${cpstCnncNtfc.godDetailDscr}" escapeXml="false"/></td>	
										</tr>
										</c:if>
										<!-- 소재 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.material"/></th>
											<td><c:out value="${cpstCnncNtfc.matrDscr}"/></td>
										</tr>
										<!-- 원산지 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.manufacture.country"/></th>
											<td><c:out value="${cpstCnncNtfc.mnfcturNationNm}"/></td>
										</tr>
										<!-- 출시년도 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.manufacture.year"/></th>
											<td>						
												<c:set var="year" /> 
												<c:set var="month" />					
												<c:if test="${not empty cpstCnncNtfc.mnfcturDate }">						
													<c:set var="year" value="${fn:substring(cpstCnncNtfc.mnfcturDate, 0, 4) }"/>
													<c:set var="month" value="${fn:substring(cpstCnncNtfc.mnfcturDate, 4, 6) }"/>						
												 	<c:out value="${year}"/>.<c:out value="${month}"/>
												</c:if>
											</td>
										</tr>
										<!-- 품질보증기간 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.as.year"/></th>
											<td><spring:message code="goods.noti.lbl.as.year.dscr"/></td>
										</tr>
										<!-- a/s문의 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.as.qna"/></th>
											<td><spring:message code="goods.noti.lbl.as.qna.dscr"/></td>
										</tr>
										<!-- 세탁방벙 -->
										<tr>
											<th><spring:message code="goods.noti.lbl.laundry.method"/></th>
											<td>
												<c:forEach var="list" items="${cpstCnncGod.cpstLndrImgList}">
													<c:if test="${list.lndrImgUseYn eq 'Y'}">
														<em class="iconWash">
															<img src="${imageURL}${list.lndrImgFileUrl}" alt="${list.lndrImgAltrtvCont}">
														</em>
													</c:if>								
												</c:forEach>										
											</td>
										</tr>
										<!-- 안전인증 : 없음 안 보영 -->
										<c:if test="${not empty cpstCnncGod.kcCrtfcNo}">				
										<tr>
											<th><spring:message code="goods.noti.lbl.kc"/></th>
											<td>
												<dl class="markInfo">
													<dt><img src="${imageURL}/goods/notification/mark_kc.jpg" alt="<spring:message code="goods.noti.lbl.kc.alt.txt"/>"></dt>
													<dd><spring:message code="goods.noti.lbl.kc.alt.txt"/></dd>
													<dd><spring:message code="goods.noti.lbl.kc.no"/> : ${cpstCnncGod.kcCrtfcNo}</dd>
												</dl>					
											</td>
										</tr>
										</c:if>
									</tbody>
								</table>
							</c:if>
						</c:forEach>
					</c:forEach>	
				</c:if>
				<!--// 세트상품 고시정보 -->
			</div>
			<!--// ** 상품세부정보 -->
			
			<!-- ** 리뷰 -->
			<div class="lypopGoodsReview lyTabCont">
				<div id="goodsReviewList"></div>
			</div>
			<!-- // ** 리뷰 -->
			
			<!-- ** 배송/교환/반품/환불 -->
			<div class="lypopGoodsGuide lyTabCont">
				<c:out value="${goods.comDmstcDlvCstPlc.dlvMthdDscr}" escapeXml="false"/>
			    <input type="hidden" id="dmstcDlvCstExmStdrAmt" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.dmstcDlvCstExmStdrAmt}" type="number"/>"/>
			    <input type="hidden" id="dmstcDlvCst" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.dmstcDlvCst}" type="number"/>"/>                 
			    <input type="hidden" id="repairDlvCst" value="<fmt:formatNumber value="${goods.comDmstcDlvCstPlc.repairDlvCst}" type="number"/>"/>                 
				<input type="hidden" id="dmstcDlvComNm" value="<ncp:code code="${goods.comDmstcDlvCstPlc.dlvComCd}"/>"/>
			</div>
			<!-- // ** 배송/교환/반품/환불 -->
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="javascript:closeLayerPopAndReset('lypopGoodsDtInfo');">닫기</button>
		</div>
	</section>
</article>

<!--  상품 리뷰 사진 클릭시  사진 Layer 팝업 (sample) -->
<article id="lypopGoodsRvPhoto" class="layer-popup lypopGoodsRvPhoto inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:auto; min-width:300px">
		<h2>상품리뷰 사진</h2>
		<div class="layer-cont ly-box scroll"><!--스크롤 필요시 scroll calss 넣어줌 -->
			<div class="rvPhotoView"><img src="" alt=""></div><!-- 상품이미지 가져오기 -->
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

