<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- //2018-05-23 -->
<acrticle id="container">
	<section id="contents">
	
<form  method="post" id="searchForm" action ="<c:url value='/mypage/order/offline/list'/>" > 
	<input type="hidden" id="srchMonth" name="srchMonth" value ="${searchDTO.srchMonth }">		
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
	
		<h3 class="title02"><spring:message code="mypage.order.offlinelist.ttl" text="매장구매내역"/></h3>
		<dl class="period-wrap period-type02">
			<!-- 기간달력 -->
			<dt><spring:message code="mypage.order.lbl.searchterm" text="조회기간"/></dt>
			<dd>
				<a href="#" class="btn-style01 d_radio_select on" id="rd_3month" onclick="setMonth('3month');return false;"><span><spring:message code="mypage.order.btn.3month" text="3개월"/></span></a>
				<a href="#" class="btn-style01 d_radio_select" id="rd_6month" onclick="setMonth('6month');return false;"><span><spring:message code="mypage.order.btn.6month" text="6개월"/></span></a>
				<input type="text" class="calendar" name="dateStart" id="dateStart" value="${searchDTO.dateStart}"><input type="text" class="calendar" name="dateEnd" id="dateEnd" value="${searchDTO.dateEnd}">
				<a href="#" class="btn-style05" onclick="goOfflinePurchaseList();"><spring:message code="mypage.order.btn.search" text="검색"/></a>
				<p class="calendar-info-txt"><spring:message code="mypage.search.lbl.maxsearch" /></p><!-- 2018-08-13 -->
			</dd>
		</dl>
		<span class="list-num"><spring:message code="mypage.order.list.lbl.total" text="전체"/>(<em class="num">${purchaseListSize}</em><spring:message code="mypage.order.list.lbl.ordercount" text="건"/>)</span>
		<div class="board-list">
			<table>
				<caption><spring:message code="mypage.order.offlinelist.ttl" text="매장구매내역"/>.</caption>
				<colgroup>
					<col style="width:100px;">
					<col>
					<col style="width:150px;">
					<col style="width:150px;">
				</colgroup>
				<thead>
					<tr>
						<th scope="col"><spring:message code="mypage.order.offline.purchase.no" text="번호"/></th>
						<th scope="col"><spring:message code="mypage.order.offline.purchase.goodsname" text="상품명"/></th>
						<th scope="col"><spring:message code="mypage.order.offline.purchase.date" text="상품구입일"/></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
<c:if test="${empty purchaseList }">
					<tr>
						<td colspan="4" class="no-result"><spring:message code="mypage.order.offline.purchase.noresult" text="매장구매내역이 없습니다."/></td>
					</tr>
</c:if>
<c:if test="${not empty purchaseList }">
	<c:forEach var="purchase" items="${purchaseList }" varStatus="status">
					<tr>
						<td>${purchaseListSize - status.index }</td>
						<td class="tleft"><a href="#">${purchase.godNm }</a></td>
						<td>${purchase.purchaseDt }</td>
						<td>
							<div class="btns"></div>
						</td>
					</tr>
	</c:forEach>
</c:if>
				</tbody>
			</table>
		</div>
		<%--
		<div class="page">
			<a href="#" class="first" title="첫 페이지">첫 페이지</a><a href="#" class="prev" title="이전 페이지">이전 페이지</a><span>
				<strong title="현재 페이지">1</strong><a href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a>
			</span><a href="#" class="next" title="다음 페이지">다음 페이지</a><a href="#" class="last" title="마지막 페이지">마지막 페이지</a>
		</div>
		 --%>
</form>
	</section>
</acrticle>

<script>
	$(document).ready(function(){
		if("${searchDTO.dateStart}"==null || "${searchDTO.dateStart}"==""){
			setMonth("3month");
		}
		
		if ("${searchDTO.srchMonth }" == "6month") {
			$("#rd_3month").removeClass("on");
			$("#rd_6month").addClass("on");
		}
	});
	
	function setMonth(val){

		var addMonth = 0;
			
		if (val == "3month") {
			addMonth = -3;
		}
		else if (val == "6month") {
			addMonth = -6;
		}
		
		var today = moment().format('YYYY-MM-DD');
		var fromDate = moment().add(addMonth, 'months').format('YYYY-MM-DD');
		
		$("#dateStart").val(fromDate);
		$("#dateEnd").val(today);
		
		$("#srchMonth").val(val);			
	}	
	
	function goOfflinePurchaseList(){

		// 날자포맷이 yyyy.mm.dd, yyyy-mm-dd, yyyy/mm/dd에 대응 
		var regExp = /[\/.-\s]/g;
		
		// 조회 기간 계산
		var strStartDate = $("#dateStart").val().replace(regExp, "");
		var startDate = new Date(strStartDate.substr(0,4), '' + (parseInt(strStartDate.substr(4,2)) - 1), strStartDate.substr(6,2));
		
		// 1년후 계산
		startDate.setYear(startDate.getFullYear() + 1);

		var strEndDate = $("#dateEnd").val().replace(regExp, "");
		var endDate = new Date(strEndDate.substr(0, 4), '' + (parseInt(strEndDate.substr(4, 2)) - 1), strEndDate.substr(6, 2));
		
		// 조회 기간이 1년 이상일 경우에 메세지 표시
		if (startDate < endDate) {
			alertLayer('<spring:message code="mypage.js.order.list.msg.maxsearch" text="시작일로부터 최대 1년까지 조회가 가능합니다."/>');
			return;
		}		
		
		$("#searchForm").submit();	
	}
</script>