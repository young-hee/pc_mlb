<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/order/order.util.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/order/cart.js?v=${_version}"></script>
<script type="text/javascript" src="//script.about.co.kr/templates/script/cm/adbay.cart.controller.js"></script>

<script type="text/javascript" src="/javascript/message/cart_${pageContext.response.locale.language}.js?v=${_version}"></script>

<div id="adbay_cart" style="display:none;"></div>

<!-- 컨텐츠 시작 -->
	<div class="contain od list" id="contain">
		<div class="container">
            <jsp:include page="/WEB-INF/views/include/location.jsp" flush="false"/>

			<main class="contents" id="contents">
				<section>
					<div id="cartRegion"></div>
					<form name="cartSearchDTO" id="cartSearchDTO"></form>

                    <ul class="stepInfoBox">
                        <li class="on"><spring:message code="cart.js.txt.title" /></li>
						<li><spring:message code="cart.js.txt.order.payment" /></li>
						<li><spring:message code="cart.js.txt.order.complete" /></li>
                    </ul>

                    <!-- 장바구니 (일반주문)  -->
                    <div class="d_tab02 orderContents">
                    	<!-- order Type -->
                        <div class="orderType">
                            <ul class="tab-type03">
                                <li class="d_tab02_select cart_tab"><a href="#" onClick="cart.searchCartList('GNRL_DLV');" id="btn_GNRL_DLV"><spring:message code="cart.js.txt.general.order" />(<em id="GNRL_DLV_CNT" class="region_cnt">0</em>)</a></li>
								<li class="d_tab02_select cart_tab"><a href="#" onClick="cart.searchCartList('RSV');" id="btn_RSV"><spring:message code="cart.js.txt.rsv.order" />(<em id="RSV_CNT" class="region_cnt">0</em>)</a></li>
								<li class="d_tab02_select cart_tab"><a href="#" onClick="cart.searchCartList('PKUP_DLV');"  id="btn_PKUP_DLV"><spring:message code="cart.js.txt.pickup.order" />(<em id="PKUP_DLV_CNT" class="region_cnt">0</em>)</a></li>
                            </ul>
                        </div>
                        <!-- //order Type -->

                        <!-- order Contents : 일반주문 상품 -->
                        <div class="orderInfoArea d_tab02_cont" style="display:block;">

                            <!-- order list -->
                            <div class="orderTable">
                                <div class="tableTopArea"><a href="#" class="btn sm gray" onclick="cart.deleteCart('GNRL_DLV')"><span><spring:message code="cart.js.txt.select.goods.delete" /></span></a></div>
                                <table class="board-list">
                                    <colgroup>
                                        <col style="width:35px">
                                        <col style="width:">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:50px">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th scope="col">
                                                <span class="check-skin">
                                                  	<input type="checkbox" name="entireCheck" id="GNRL_DLV_entireCheck" value="GNRL_DLV" checked="checked"/>
													<span><spring:message code="cart.js.txt.select" /></span>
                                                </span>
                                            </th>
                                            <th scope="col"><spring:message code="cart.js.txt.goods" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.count" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.promotion" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.order.price" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.delete" /></th>
                                        </tr>
                                    </thead>
                                    <tbody id="GNRL_DLV_ViewLayer">
                                    </tbody>
                                    <%-- product start --%>
									<%@ include file="/WEB-INF/views/cart/goods/cart.list.general.jspf"%>
									<%-- product end --%>
									<tfoot id="GNRL_DLV_cart_foot" style="display:none;">
										<tr>
									    	<td colspan="6" class="dvTotal">
									    		<strong><spring:message code="cart.js.txt.delivery.cost" /></strong>
												<em id="total_GNRL_DLV_dlv_amt">0<spring:message code="common.js.crncy" /></em>
									    	</td>
										</tr>
									</tfoot>
                                </table>
                                <div class="tableBtarea" id="GNRL_DLV_tableBtarea" style="display:none;"><a href="#" class="btn sm gray" onclick="cart.deleteCart('GNRL_DLV')"><span><spring:message code="cart.js.txt.select.goods.delete" /></span></a></div>
                                <div class="btnWrap">
                                    <a href="/" class="btn lg"><spring:message code="cart.js.txt.go.shopping" /></a>
                                </div>
                                <ul class="text-list02 line_t">
                                    <li><spring:message code="cart.js.txt.info.save.term.1.1" /></li>
                                    <%-- <li><spring:message code="cart.js.txt.info.save.term.1.2" /></li> --%>
                                    <li><spring:message code="cart.js.txt.info.save.term.1.3" /></li>
                                </ul>
                            </div>
                            <!-- // order list -->

                           <!-- pay box -->
                           <div class="orderPay d_fix">
								<%@ include file="/WEB-INF/views/cart/goods/cart.info.general.jspf"%>
                           </div>
                           <!-- // pay box -->

	                    </div>
	                    <!-- //order Contents : 일반주문 상품-->

						<!-- order Contents : 예약주문-->
                        <div class="orderInfoArea d_tab02_cont">

                            <!-- order list -->
                            <div class="orderTable">
                                <div class="tableTopArea"><a href="#" class="btn sm gray" onclick="cart.deleteCart('RSV')"><span><spring:message code="cart.js.txt.select.goods.delete" /></span></a></div>
                                <table class="board-list">
                                    <colgroup>
                                        <col style="width:35px">
                                        <col style="width:">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:110px">
                                        <col style="width:50px">
                                    </colgroup>
                                    <thead>
                                        <tr>
                                            <th scope="col">
                                                <span class="check-skin">
                                                  <input type="checkbox" name="entireCheck" id="RSV_entireCheck" value="RSV" checked="checked"/>
												  <span><spring:message code="cart.js.txt.select" /></span>
                                                </span>
                                            </th>
                                            <th scope="col"><spring:message code="cart.js.txt.goods" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.count" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.promotion" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.order.price" /></th>
                                            <th scope="col"><spring:message code="cart.js.txt.delete" /></th>
                                        </tr>
                                    </thead>
                                    <tbody id="RSV_ViewLayer">
                                    </tbody>
                                    <%-- product start --%>
									<%@ include file="/WEB-INF/views/cart/goods/cart.list.rsv.jspf"%>
									<%-- product end --%>
                                    <tfoot id="RSV_cart_foot" style="display:none;">
                                        <tr>
                                        	<td colspan="6" class="dvTotal">
                                        		<strong><spring:message code="cart.js.txt.delivery.cost" /></strong>
									        	<em id="total_RSV_dlv_amt">0<spring:message code="common.js.crncy" /></em>
									        </td>
                                        </tr>
                                    </tfoot>
                                </table>
                                <div class="tableBtarea" id="RSV_tableBtarea" style="display:none;"><a href="#" class="btn sm gray" onclick="cart.deleteCart('RSV')"><span><spring:message code="cart.js.txt.select.goods.delete" /></span></a></div>
                                <div class="btnWrap">
                                    <a href="/" class="btn lg"><spring:message code="cart.js.txt.go.shopping" /></a>
                                </div>
                                <ul class="text-list02 line_t">
                                	<li><spring:message code="cart.js.txt.info.save.rsv.term.1.1" /></li>
                                    <li><spring:message code="cart.js.txt.info.save.rsv.term.1.2" /></li>
                                    <li><spring:message code="cart.js.txt.info.save.rsv.term.1.3" /></li>
                                </ul>
                            </div>
                            <!-- // order list -->

                           <!-- pay box -->
                           <div class="orderPay  d_fix">
                                <%@ include file="/WEB-INF/views/cart/goods/cart.info.rsv.jspf"%>
                           </div>
                           <!-- pay box -->

	                   	</div>
	                    <!-- //order Contents : 예약주문-->

						<!-- 매장픽업주문 -->
	                   	<div class="orderInfoArea full d_tab02_cont" id="PKUP_DLV_ViewLayer">
						</div>
						<%@ include file="/WEB-INF/views/cart/goods/cart.list.pickup.jspf"%>
						<!-- //매장픽업주문-->

					   	<!-- 추천상품 -->
                       	<div class="recomPdList">
                           	<h3>
	                           	<sec:authorize access="isAnonymous() or hasRole('ROLE_GUEST')">
									<spring:message code="cart.js.txt.cart.recomm.title2" />
								</sec:authorize>
								<sec:authorize access="isAuthenticated() and not hasRole('ROLE_GUEST')">
									${_user.mbr.mbrNm}<spring:message code="cart.js.txt.cart.recomm.title" />						
								</sec:authorize>
							</h3>
                           	<div class="recomPdListBoxWrap">
	                           	<div id="recommendProductGNRL_DLV" class="recomPdListBox swiper-container"></div>
	                        	<div id="recommendProductRSV" class="recomPdListBox swiper-container"></div>
	                        	<div id="recommendProductPKUP_DLV" class="recomPdListBox swiper-container"></div>
                        	</div>
                       	</div>
                      	<!-- //추천상품-->

                    </div>
                    <!-- //장바구니 (일반주문) -->

					<!-- data test -->
					<!-- <textarea rows="10" cols="100" id="jsonTEXT"></textarea> -->
					<form name="mainForm" id="mainForm" method="post" action="/">
						<input type="hidden" name="_csrf" content="${_csrf.token}"/>
						<input type="hidden" name="_csrf_header" content="${_csrf.headerName}"/>
					</form>
				</section>
				<div style="display:none;">
					<a href="#layerPopupSoldout" class="btn-style04 d_layer_open" id="popSoldOut"></a>
				</div>
			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->

<jsp:include page="/WEB-INF/views/helpdesk/include/notice.pop.jsp">
    <jsp:param name="searchExpsrScrinSectCd" value="BSK" />
</jsp:include>

<script>
	var wptg_tagscript_vars = wptg_tagscript_vars || [];
	window.criteo_q = window.criteo_q || [];
	var naver_keyword_advertisement = true;

	$(document).ready(function() {
		cart.load('${cartDlvSectCd}');
	});
</script>


<%@ include file="/WEB-INF/views/common/layerpop/shop.view.jsp"%>
<%@ include file="/WEB-INF/views/display/include/goods.option.jsp"%>
<%@ include file="/WEB-INF/views/cart/goods/cart.popup.jsp"%>
