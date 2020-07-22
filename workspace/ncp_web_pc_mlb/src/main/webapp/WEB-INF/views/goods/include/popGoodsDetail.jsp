
<article id="lypopGoodsDetail" class="layer-popup lypopGoodsDetail">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<div class="layer-cont ly-box">	
		<!-- 일반상품 고시정보 -->
		<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">
			<h2><spring:message code="goods.noti.ttl"/></h2>
			<table class="board-list lyType01">
				<colgroup>
					<col style="width:118px">
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
						<h2 id="productInfo01" class="title03"><spring:message code="goods.noti.ttl"/>(<c:out value="${cpstCnncGod.cpstGodNm}"/>)</h2>
						<table class="board-list lyType01">
							<colgroup>
								<col style="width:118px">
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
		
		<div class="layer-popup-close"><button type="button" class="d_layer_close" >닫기</button></div>
		<!--  button -->
		<div class="lyBtnArea"><a href="javascript:closeLayerPopAndReset('lypopGoodsDetail');" class="btn fill w160"><spring:message code="goods.js.common.btn.ok"/></a></div>
		</div>
		
	</section>
</article>

