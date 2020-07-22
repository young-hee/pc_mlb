<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


<section class="layer-popup-cont" tabindex="0" style="width:530px">
	<h2><spring:message code="mypage.order.detail.lbl.delivery.select" text="배송지선택"/></h2>
	<div class="layer-cont ly-box scroll"> <!-- 스크롤 필요시 class=scroll 추가 -->
		<div class="d_tab02">
			<ul class="tab-type01">
				<li class="d_tab02_select on"><a href="#"><spring:message code="mypage.order.detail.lbl.delivery.placeinfo" text="배송지정보"/></a></li>
				<li class="d_tab02_select"><a href="#"><spring:message code="mypage.order.detail.lbl.delivery.latestplace" text="최근배송지"/></a></li>
			</ul>
			
			<!-- 배송지 정보 -->
			<div class="d_tab02_cont" style="display:block;">
				<div class="board-list">
					<c:choose>
						<c:when test="${empty mbrDlvspList}">
							<div class="noResultInfo"><spring:message code="mypage.claim.txt.nolist" /></div>
						</c:when>
						<c:otherwise>
						<table>
							<caption><spring:message code="mypage.order.detail.lbl.delivery.caption" text="배송지선택 - 받는분, 배송지 정보, 선택."/></caption>
							<colgroup>
								<col style="width:100px">
								<col style="width:280px">
								<col style="width:"">
							</colgroup>
							<thead>
								<th scope="col"><spring:message code="mypage.order.detail.lbl.delivery.name2" text="받는분"/></th>
								<th scope="col"><spring:message code="mypage.order.detail.lbl.delivery.placeinfo2" text="배송지 정보"/></th>
								<th scope="col"><spring:message code="mypage.order.btn.select" text="선택"/></th>
							</thead>
							<tbody>
								<c:forEach var="delivery" varStatus="status" items="${mbrDlvspList}">
									<tr data-delv-seq="${delvSeq }"
		                				data-dlvMnCd=""
		                				data-dlvHopeDate=""
		                				data-pkupShopSn=""
		                				data-pkupShopVisitDate=""
		                				data-dlvAdbukTurn="${delivery.dlvAdbukTurn}"
		                				data-addrse-nm="${delivery.addrseNm}"
		                				data-addr-sect-cd="${delivery.dlvAddrSectCd}"
		                				data-addrse-post-no="${delivery.postNo}"
		                				data-addrse-base-addr="${delivery.baseAddr}"
		                				data-addrse-detail-addr="${delivery.detailAddr}"
		                				data-addrse-mobil-nation-no="82"
		                				data-addrse-mobil-no="${delivery.mobilAreaNo}${delivery.mobilTlofNo}${delivery.mobilTlofWthnNo}"
		                				data-addrse-mobil-area-no="${delivery.mobilAreaNo}"
		                				data-addrse-mobil-tlof-no="${delivery.mobilTlofNo}"
		                				data-addrse-mobil-tlof-wthn-no="${delivery.mobilTlofWthnNo}"
		                				data-addrse-tel-nation-no="82"
		                				data-addrse-tel-no="${delivery.telAreaNo}${delivery.telTlofNo}${delivery.telTlofWthnNo}"
		                				data-addrse-tel-area-no="${delivery.telAreaNo}"
		                				data-addrse-tel-tlof-no="${delivery.telTlofNo}"
		                				data-addrse-tel-tlof-wthn-no="${delivery.telTlofWthnNo}"
		                				data-addrse-cty-nm="${delivery.ctyNm}"
		                				data-addrse-lclty-nm="${delivery.lcltyNm}" >
										<td>
											${delivery.addrseNm }<br>
											<c:if test="${delivery.baseDlvspYn eq 'Y' }">[<spring:message code="mypage.order.detail.lbl.delivery.defaultplace" text="기본주소"/>]</c:if>
										</td>
										<td class="addrBox">
											(${delivery.postNo })<br>${delivery.baseAddr }<br>${delivery.detailAddr }<br>
											${delivery.mobilAreaNo}-${delivery.mobilTlofNo}-${delivery.mobilTlofWthnNo}
										</td>
										<td><a href="#" class="btn sm" onclick="confirmDelivery($(this).closest('tr'));"><spring:message code="mypage.order.btn.select" text="선택"/></a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="d_tab02_cont">
				<div class="board-list">
					<c:choose>
						<c:when test="${empty ordDlvspList}">
							<div class="noResultInfo"><spring:message code="mypage.claim.txt.nolist" /></div>
						</c:when>
						<c:otherwise>
							<table>
								<caption><spring:message code="mypage.order.detail.lbl.delivery.caption" text="배송지선택 - 받는분, 배송지 정보, 선택."/></caption>
								<colgroup>
									<col style="width:100px">
									<col style="width:280px">
									<col style="width:"">
								</colgroup>
								<thead>
									<th><spring:message code="mypage.order.detail.lbl.delivery.name2" text="받는분"/></th>
									<th><spring:message code="mypage.order.detail.lbl.delivery.placeinfo2" text="배송지 정보"/></th>
									<th><spring:message code="mypage.order.btn.select" text="선택"/></th>
								</thead>
								<tbody>
									<c:forEach var="delivery" varStatus="status" items="${ordDlvspList}">
										<tr data-delv-seq="${delvSeq }"
			                				data-dlvMnCd=""
			                				data-dlvHopeDate=""
			                				data-pkupShopSn=""
			                				data-pkupShopVisitDate=""
			                				data-dlvAdbukTurn="${delivery.dlvAdbukTurn}"
			                				data-addrse-nm="${delivery.addrseNm}"
			                				data-addr-sect-cd="${delivery.dlvAddrSectCd}"
			                				data-addrse-post-no="${delivery.postNo}"
			                				data-addrse-base-addr="${delivery.baseAddr}"
			                				data-addrse-detail-addr="${delivery.detailAddr}"
			                				data-addrse-mobil-nation-no="82"
			                				data-addrse-mobil-no="${delivery.mobilAreaNo}${delivery.mobilTlofNo}${delivery.mobilTlofWthnNo}"
			                				data-addrse-mobil-area-no="${delivery.mobilAreaNo}"
			                				data-addrse-mobil-tlof-no="${delivery.mobilTlofNo}"
			                				data-addrse-mobil-tlof-wthn-no="${delivery.mobilTlofWthnNo}"
			                				data-addrse-tel-nation-no="82"
			                				data-addrse-tel-no="${delivery.telAreaNo}${delivery.telTlofNo}${delivery.telTlofWthnNo}"
			                				data-addrse-tel-area-no="${delivery.telAreaNo}"
			                				data-addrse-tel-tlof-no="${delivery.telTlofNo}"
			                				data-addrse-tel-tlof-wthn-no="${delivery.telTlofWthnNo}"
			                				data-addrse-cty-nm="${delivery.ctyNm}"
			                				data-addrse-lclty-nm="${delivery.lcltyNm}" >
											<td>
												${delivery.addrseNm }
											</td>
											<td class="addrBox">
												(${delivery.postNo })<br>${delivery.baseAddr }<br>${delivery.detailAddr }<br>
												${delivery.mobilAreaNo}-${delivery.mobilTlofNo}-${delivery.mobilTlofWthnNo}
											</td>
											<td><a href="#" class="btn sm" onclick="confirmDelivery($(this).closest('tr'));"><spring:message code="mypage.order.btn.select" text="선택"/></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	<div class="layer-popup-close">
		<button type="button" class="d_layer_close"><spring:message code="mypage.order.btn.close" text="닫기"/></button>
	</div>
</section>

<script>

	$('#wrap').off('scroll touchmove mousewheel');
	tab02.init();
	
</script>