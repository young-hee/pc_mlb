<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js"></script>
<script type="text/javascript" src="<ncp:prop key="ncp_base.order.kcp.js.url"/>"></script>

<script type="text/javascript" src="${_resourceURL}static/js/order/order.util.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/order/order.pay.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/order/order.form.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/order/order.coupon.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js?v=${_version}"></script>

<script type="text/javascript" src="/javascript/message/order_${pageContext.response.locale.language}.js?v=${_version}"></script>


<!-- 컨텐츠 시작 -->
<div class="contain od list" id="contain">
	<div class="container">
		<div id="orderRegion"></div>
		<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false"/>

		<main class="contents" id="contents">
			<section>
				<ul class="stepInfoBox">
					<li class="on"><spring:message code="cart.js.txt.title" /></li>
					<li class="on"><spring:message code="order.js.txt.order.payment" /></li>
					<li><spring:message code="order.js.txt.order.complete" /></li>
				</ul>

				<div class="orderContents orderWrite">
					<div class="orderInfoArea orderWrite">

						<!-- 주문상품 정보 -->
                        <div class="orderWriteArea">
							<h3 class="title06"><spring:message code="order.js.txt.order.goods.info" /></h3>

							<div class="orderWrite">
								<!-- order list -->
                                <div class="orderTable">
									<table class="board-list">
										<caption><spring:message code="order.js.txt.caption1" /></caption>
										<colgroup>
											<col style="width:">
                                            <col style="width:110px">
                                            <col style="width:110px">
                                            <col style="width:110px">
										</colgroup>
										<thead>
											<tr>
												<th scope="col"><spring:message code="order.js.txt.goods.info" /></th>
												<th scope="col"><spring:message code="order.js.txt.count" /></th>
												<th scope="col"><spring:message code="order.js.txt.promotion" /></th>
												<th scope="col"><spring:message code="order.js.txt.order.price" /></th>
											</tr>
										</thead>
										<tbody id="goodsViewLayer">
										</tbody>
										<%-- product start --%>
										<%@ include file="/WEB-INF/views/order/orderform/order.form.goods.jspf"%>
										<%-- product end --%>
										<tfoot>
	                                        <tr class="info_not_pickup">
	                                            <td colspan="4" class="dvTotal">
	                                            	<strong><spring:message code="order.js.txt.delivery.cost" /></strong>
	                                            	<em id="dlv_amt_m">0원</em>
	                                            </td>
	                                        </tr>
	                                    </tfoot>
									</table>
								</div>

								<p class="iconTxt01 mt20 PKUP_DLV_layer" style="display:none;"><spring:message code="order.js.txt.caption2" /></p>
								<p class="iconTxt01 mt20 info_no_mem" style="display:none;"><spring:message code="order.txt.nomem.top.caption" /></p>
								<p class="iconTxt01 mt05 info_no_mem" style="display:none;">
									<spring:message code="order.js.txt.caption99" />
								</p>
							</div>
						</div>

						<!-- 약관동의 및 개인정보수집 동의 --><!-- 약관 수정 필요합니다. 최종아님 -->
                        <div class="orderWriteArea info_no_mem">
                        	<h3 class="title06 info_no_mem" style="display:none;"><spring:message code="order.js.txt.caption5" /></h3>
                        	<div class="order-detail-wrap d_toggle on info_no_mem" style="display:none;">
								<%@ include file="/WEB-INF/views/order/orderform/order.form.nomem.jspf"%>
							</div>
						</div>

						<!-- 할인정보 -->
                        <div class="orderWriteArea info_mem">
							<h3 class="title06"><spring:message code="order.js.txt.dc.info" /></h3>
							<div class="order-detail-wrap d_toggle on dcInfo">
								<%@ include file="/WEB-INF/views/order/orderform/order.form.dcinfo.jspf"%>
							</div>
						</div>

						<!-- 주문자 정보 -->
                        <div class="orderWriteArea">
                        	<h3 class="title06"><spring:message code="order.js.txt.orderer.info" /></h3>
                        	<div class="order-detail-wrap d_toggle on">
                        		<%@ include file="/WEB-INF/views/order/orderform/order.form.buyer.jspf"%>
                        	</div>
                        </div>

						<!-- 배송지 정보 -->
                        <div class="orderWriteArea">
                        	<h3 class="title06 info_not_pickup not-delivery"><spring:message code="order.js.txt.delivery.location.info.1" /> <a href="/helpdesk/notice/view/94?searchNoticeCd=&searchNotice="  target="_blank">*‘코로나19’ 배송불가지역 안내</a></h3>
                        	<h3 class="title06 info_pickup not-delivery"><spring:message code="order.js.txt.delivery.location.info.2" /> <a href="/helpdesk/notice/view/94?searchNoticeCd=&searchNotice="  target="_blank">*‘코로나19’ 배송불가지역 안내</a></h3>
							<div class="order-detail-wrap d_toggle on">
								<%@ include file="/WEB-INF/views/order/orderform/order.form.delivery.jspf"%>
							</div>
                        </div>

						<!-- 결제수단 -->
						<div class="orderWriteArea">
							<h3 class="title06 orderPayMethod"><spring:message code="order.js.txt.payment.method" /></h3>
							<div class="order-detail-wrap d_toggle on orderPayMethod">
								<%@ include file="/WEB-INF/views/order/orderform/order.form.payment.jspf"%>
							</div>
						</div>

						<div class="orderPay d_fix">
							<%@ include file="/WEB-INF/views/order/orderform/order.form.pay.info.jspf"%>
						</div>
					</div>

				</div>


				<!-- <div>
					<br/>order<textarea rows="10" cols="100" id="jsonTEXT"></textarea>
					<br/>npay<textarea rows="5" cols="100" id="npayText"></textarea>
					<br/>checkOrder<textarea rows="5" cols="100" id="cordText"></textarea>
					<br/>couponText<textarea rows="5" cols="100" id="couponText"></textarea>
					<br/>couponAppCart<textarea rows="5" cols="100" id="couponAppCartText"></textarea>
				</div> -->


				<div>
					<form name="kcpForm" id="kcpForm"></form>
				</div>
			</section>
		</main>
	</div>
</div>

<div class="ld-bar-wrap"><div class="ld-bar"></div></div>
<%@ include file="/WEB-INF/views/common/layerpop/shop.view.jsp"%>
<%@ include file="/WEB-INF/views/order/orderform/order.form.popup.jsp"%>

<jsp:include page="/WEB-INF/views/helpdesk/include/notice.pop.jsp">
    <jsp:param name="searchExpsrScrinSectCd" value="ORDSH" />
</jsp:include>

<script>

	$(document).ready(function() {


		orderform.load();
		
		$(".agree-link a").click(function(e){
			e.preventDefault();
	        var hrefNm = $(this).attr("href");
	        var object = $(hrefNm);
	        if(object){
	        	var posTop = $(this).closest(".agree-section-content");
	        	posTop.animate({scrollTop: object.offset().top - posTop.offset().top}, 100);
	        }
	    }); 
	});

</script>