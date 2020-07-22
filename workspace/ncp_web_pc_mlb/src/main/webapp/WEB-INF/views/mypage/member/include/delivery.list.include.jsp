<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

				<div class="board-list" id="deliveryListBoard">
					<table>
						<caption><spring:message code='mypage.member.delivery.lbl.txt2' text="배송지 관리 - 사용가능매장, 구분, 쿠폰명, 할인, 사용기간, 쿠폰적용상품"/></caption>
						<colgroup>
							<col style="width:40px;">
							<col style="width:140px;">
							<col>
							<col style="width:145px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col"><spring:message code='mypage.member.delivery.lbl.txt3' text="받는분"/></th>
								<th scope="col"><spring:message code='mypage.member.delivery.lbl.txt4' text="배송지정보"/></th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>

							<c:choose>
								<c:when test="${empty deliveryLocationList}">
									<tr>
										<td colspan="4" class="no-result"><spring:message code='mypage.member.delivery.lbl.txt6' text="등록된 배송지가 없습니다."/></td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach var="list" varStatus="status" items="${deliveryLocationList }">
									<tr>
										<td>
											<span class="rdo-skin">
												<input type="radio" name="dlvAdbukTurn" id="rd<c:out value="${status.count}"/>" value="<c:out value="${list.mbrDlvsp.dlvAdbukTurn}"/>"/>
												<span></span>
											</span><label for="rd<c:out value="${status.count}"/>"></label>
										</td>
										<td>
											<c:if test="${list.mbrDlvsp.baseDlvspYn eq 1}">
											<p>[<spring:message code='mypage.member.delivery.lbl.txt7' text="기본배송지"/>]</p>										
											</c:if>											
											<p><c:out value="${list.mbrDlvsp.addrseNm}"/></p>
										</td>
										<td class="tleft">
											<p><c:out value="${list.mbrDlvsp.baseAddr}"/>&nbsp;<c:out value="${list.mbrDlvsp.detailAddr}"/></p>
											<span class="txt13-666">
												<c:out value="${list.mbrDlvsp.mobilAreaNo}"/>-<c:out value="${list.mbrDlvsp.mobilTlofNo}"/>-<c:out value="${list.mbrDlvsp.mobilTlofWthnNo}"/>
												<c:if test="${list.mbrDlvsp.telAreaNo ne null || list.mbrDlvsp.telTlofNo ne null || list.mbrDlvsp.telTlofWthnNo ne null}" >
													/ <c:if test="${list.mbrDlvsp.telAreaNo ne null}"><c:out value="${list.mbrDlvsp.telAreaNo}"/>-</c:if>
													<c:out value="${list.mbrDlvsp.telTlofNo}"/>-<c:out value="${list.mbrDlvsp.telTlofWthnNo}"/>
												</c:if>
											</span>
										</td>
										<td>
											<div class="tbST-R-BtnBox">
												<a href="#layerPopup25" class="btn sm gray" onclick="getLayerPopupDeliverySetting('modify','<c:out value="${list.mbrDlvsp.dlvAdbukTurn}"/>'); return false;" ><spring:message code='mypage.member.delivery.btn.txt1' text="수정"/></a>
												<c:if test="${list.mbrDlvsp.baseDlvspYn ne 1}">
												<a href="#" class="btn sm gray" onclick="deleteDeliveryLocation('${list.mbrDlvsp.dlvAdbukTurn}','${list.mbrDlvsp.baseDlvspYn}');"><spring:message code='mypage.member.delivery.btn.txt2' text="삭제"/></a>
												</c:if>											
											</div>
										</td>
									</tr>
									</c:forEach>									
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>

				<c:if test ="${!empty deliveryLocationList}"> 
					<div class="page"  id="deliveryListBoardPage">	
							<c:if test="${currentPage > 1 }">
								<a class="first" href="javascript:goDeliveryList('1')" _onclick="return false;" title="첫 페이지">Prev</a>
							</c:if>
							<c:if test="${preFlag }">
								<a class="prev" href="javascript:goDeliveryList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
							</c:if>
							<span>
							<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
									<c:choose>
										<c:when test="${currentPage eq i}">
										<strong title="현재 페이지">${i}</strong>
										</c:when>
										<c:otherwise>
										<a href="javascript:goDeliveryList('${i}')" >${i}</a>
										</c:otherwise>
									</c:choose>									
							</c:forEach>
							</span>
							<c:if test="${nextFlag }">
								<a class="next" href="javascript:goDeliveryList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
							</c:if>
							<c:if test="${currentPage < totalPage}">
							<a class="last" href="javascript:goDeliveryList('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
							</c:if>                    
					</div>
				</c:if>

				<div class="btnWrapBox" id="deliveryListBoardBtn">
					<c:if test="${!empty deliveryLocationList}">
					<a href="#" class="btn" onclick="setUserDeliveryBase(); return false;"><spring:message code='mypage.member.delivery.lbl.txt8' text="기본 배송지 설정"/></a>
					</c:if>
					<a href="#" class="btn fill" onclick="getLayerPopupDeliverySetting('insert'); return false;"><spring:message code='mypage.member.delivery.btn.txt3' text="배송지 추가"/></a>
				</div>
	
	
<script type="text/javascript" >
	
	$(document).ready(function(){
		 setDeliveryCnt('${totalRow}');
		 //setDeliveryCnt('10'); 
	});
	
	/* 건수 입력 */
	function setDeliveryCnt(deliveryCnt){
		$("#deliveryCnt").empty();
		$("#deliveryCnt").append(deliveryCnt);
	}
	
</script>