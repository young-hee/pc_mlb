<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<c:if test="${empty wishlistList}">
	<p class="list-noneinfo">위시리스트 보관 상품이 없습니다.<br/>상품을 위시리스트에 담아 두시면 언제든지 쉽게 상품을 찾으실 수 있습니다.</p>
</c:if>

<c:if test="${!empty wishlistList}">
	<c:if test ="${mypageMainYn ne 'Y'}">
<div class="item-listST">
	</c:if>
	<ul>
		<c:forEach var="list" varStatus="status" items="${wishlistList }">
		<li>
			<div class="item-img">
				<%-- @@@@@@ 업무확인 : 상품 판매 구분 코드 에 따른 노출 확인 @@@@@ --%>
				<c:if test ="${list.godSaleSectCd eq 'SLDOUT'}">
                    <span class="btn fill sm">SOLD OUT</span>
				</c:if>
				<c:if test ="${list.godSaleSectCd eq 'SMTM_SLDOUT' }">
					<span class="btn fill sm">COMING SOON</span>
				</c:if>

				<a  href="${list.godUrl}" >
					<img src="${imageURL}${list.imgUrl}/dims/resize/242x242" alt="${list.godNm}" onerror="errorImgShow(this,'400');">
				</a>
			</div>
			<div class="item-info">
				<p><a href="${list.godUrl}"><c:out value ="${list.godNm}" /></a></p>
				<p>
					<fmt:parseNumber value="${list.csmrPrc}" var="numCsmrPrc" scope="page" />
					<fmt:parseNumber value="${list.rtlPrc}" var="numRtlPrc" scope="page" />
					<c:choose>
						<c:when test="${numCsmrPrc < numRtlPrc}">
							<del><fmt:formatNumber>${list.rtlPrc}</fmt:formatNumber></del>
							<strong><fmt:formatNumber>${list.csmrPrc}</fmt:formatNumber></strong>
						</c:when>
						<c:otherwise>
							<strong><fmt:formatNumber>${list.rtlPrc}</fmt:formatNumber></strong>
						</c:otherwise>
					</c:choose>
				</p>

				<c:if test ="${mypageMainYn ne 'Y'}">
					<div class="cart-bottomBtn">
    					<c:choose>
    						<c:when test ="${list.godSaleSectCd eq 'SMTM_SLDOUT'}">
    							<p>일시품절 상품입니다.</p> <%--  일시품절  --%>
    						</c:when>
    						<c:when test ="${list.godSaleSectCd eq 'SLDOUT'}">
    							<p>품절된 상품입니다.</p> <%--  품절  --%>
    						</c:when>
    						<c:otherwise>
								<a href="#layerPopupOption" data-godno="${list.godNo}" class="btn fill sm d_layer_open"><span>장바구니</span></a> <%--  장바구니  --%>
    						</c:otherwise>
    					</c:choose>
					</div>
				</c:if>
			</div>
			<c:if test ="${mypageMainYn ne 'Y'}">
				<button type="button" class="itemIMG-del" onclick="deleteWishList('${list.wishlstSn}','${list.godTurn}');" >상품 삭제</button> <%-- 상품 삭제 --%>
			</c:if>
		</li>
		</c:forEach>

	</ul>
	<c:if test ="${mypageMainYn ne 'Y'}">
</div>
	</c:if>
</c:if>

<c:if test ="${mypageMainYn ne 'Y'}">
	<c:if test ="${!empty wishlistList}">
	<div class="page">
			<c:if test="${currentPage > 1 }">
				<a class="first" href="javascript:goWishlistList('1')" _onclick="return false;" title="첫 페이지">Prev</a>
			</c:if>
			<c:if test="${preFlag }">
				<a class="prev" href="javascript:goWishlistList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
			</c:if>
			<span>
			<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
					<c:choose>
						<c:when test="${currentPage eq i}">
						<span class="on"><strong title="현재 페이지">${i}</strong></span>
						</c:when>
						<c:otherwise>
						<a href="javascript:goWishlistList('${i}')" ><span title="현재 페이지">${i}</span></a>
						</c:otherwise>
					</c:choose>
			</c:forEach>
			</span>
			<c:if test="${nextFlag }">
				<a class="next" href="javascript:goWishlistList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
			</c:if>
			<c:if test="${currentPage < totalPage}">
			<a class="last" href="javascript:goWishlistList('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
			</c:if>
	</div>
	</c:if>

</c:if>


<form id ="srchForm" action ="" method="post" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="wishlstSn" id="wishlstSn" value=""/>
	<input type="hidden" name="godTurn"   id="godTurn"   value=""/>
</form>

<script type="text/javascript">

	$(document).ready(function(){
		 setWishlistCnt('${totalRow}');

	});

	function setWishlistCnt(wishListCnt){
		$("#wishListCnt").empty();
		$("#wishListCnt").append(wishListCnt);
		$("#totalCnt").val(wishListCnt);

		$("#pageNo").val('${currentPage}');
		$("#totPage").val('${totalPage}');
	}

	/* Wishlist 상품 삭제 */
	function deleteWishList(strWishlstSn, strGodTurn){

		if (confirm('삭제하시겠습니까?') == true) {
			// 삭제 대상 순번 셋팅
			$("#wishlstSn").val(strWishlstSn);
			$("#godTurn").val(strGodTurn);

			// 삭제구분 전체,단건
			$("#deleteAllYn").val("N");

			deleteWishListProc();
		}
	}
</script>