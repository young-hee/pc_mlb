<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<div class="area left">

	<!-- 셋트상품 -->	
	<c:if test="${goods.godEx.godTpCd eq 'SET_GOD'}">
		<c:forEach var="cpstCnncGod" items="${goods.godCpstGodCnncExList}">	
			<c:forEach var="cpstCnncNtfc" items="${goods.godNtfcDetailList}">
				<c:if test="${cpstCnncGod.cpstGodNo eq cpstCnncNtfc.godNo}">
					<c:if test="${cpstCnncGod.sortSeq eq '1'}">
						<div class="info ddts">
							<c:out value="${cpstCnncNtfc.godDetailAditDscr}" escapeXml="false"/>
						</div>
					</c:if>
				</c:if>
			</c:forEach>
		</c:forEach>
	</c:if>
	<!-- 일반상품 -->
	<c:if test="${goods.godEx.godTpCd eq 'GNRL_GOD'}">		
		<div class="info ddts ${(goods.godEx.dsgnGrpNo == '32SHC2911' || goods.godEx.dsgnGrpNo == '32SHC1911') ? 'thin' : ''}">
			<c:if test="${not empty goods.godFitLktbEx.fitLktbHtml}">
				<p><b>[<c:out value="${goods.godFitLktbEx.fitSectCdNm}"/>]</b></p>
			</c:if>
			<c:if test="${not empty goods.godNtfcDetail.godDetailAditDscr }">
				<c:out value="${goods.godNtfcDetail.godDetailAditDscr}" escapeXml="false"/>
			</c:if>
		</div>
	</c:if> 

	<div class="info adds">
		<ul class="list lyBtns">
			<li><a href="#" id="btn_goodsDetail"><span class="t"><spring:message code="goods.tab.noti.ttl"/></span></a></li>
			<li><a href="#" id="btn_goodsReview"><span class="t"><spring:message code="goods.tab.review.ttl"/></span><em class="n" id="reviewTotalCnt"></em></a></li>
			<c:if test="${pageContext.response.locale.language eq 'ko'}">
				<li><a href="#" id="btn_goodsGuide"><span class="t"><spring:message code="goods.dlvr.common.ttl"/></span></a></li>
			</c:if>
		</ul>
	</div>
	<c:if test="${not empty content.promotionList}">
		<div class="info prom">
			<div class="ht"><spring:message code="goods.promotion.lbl.goods.sale.ttl"/></div>
			<ul class="list">
				<c:forEach var="promt" items="${content.promotionList}">
					<li><a href="/special/${promt.promtSn}"><c:out value="${promt.promtNm}"/></a></li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<%-- S : 2020-03-20 Family link add --%>
	<div class="box_family">
	    <a href="" class="link_family"><span class="flag_family">Family</span><span class="text_family">기획전 바로가기</span><i class="link_blt-a"></i></a>
	</div>
	<%-- E : 2020-03-20 Family link add --%>
</div>

<script>
$(document).ready(function(){
	 
	var selTab =".lyTabStyle li"; //팝업안에 menu tab
	var selCont=".layer-cont .lyTabCont"; // 팝업안 menu contents
	var tabCount = 0;
	
	$('.lyBtns li').click(function(){
		layerPopup.popupOpenNow('#lypopGoodsDtInfo');
		tabCount = $('.lyBtns li').index(this);
		//remove
		$(selTab).removeClass('on');
		$(selCont).removeClass('on');		
		//addClass
		$(selTab).eq(tabCount).addClass('on');
		$(selCont).eq(tabCount).addClass('on');		
	});
	
	
	// popup tab 제어
	$(selTab).click(function(){
		//remove
		$(selTab).removeClass('on');
		$(selCont).removeClass('on');
		//addClass
		$(this).addClass('on');		
		$(selCont).eq($(selTab).index(this)).addClass('on');
    });
	
});
</script>