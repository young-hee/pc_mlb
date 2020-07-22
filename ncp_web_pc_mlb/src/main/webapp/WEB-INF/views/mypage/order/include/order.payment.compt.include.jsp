<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<!-- layerpopup - 추가결제 완료 -->
<article id="layerPopupPayComplete" class="layer-popup layer-type02">
	<section class="layer-popup-cont" tabindex="0">
		<h2>결제완료</h2>
		<div class="layer-popup-wrap02">
			<p class="layer-txt03"><spring:message code="mypage.order.detail.lbl.addpay.sucess" text="결제가 정상적으로 완료되었습니다."/></p>
			<p class="layer-txt04" id="payComptOrdNo"><spring:message code="mypage.order.list.lbl.orderno" text="주문번호"/></p>
		</div>
		<div class="btn-wrap03">
			<a href="#" class="btn fill w160 d_layer_close" onclick="addPayReload();"><spring:message code="mypage.order.btn.confirm" text="확인"/></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="addPayReload();"><spring:message code="mypage.order.btn.close" text="닫기"/></button>
		</div>
	</section>
</article>