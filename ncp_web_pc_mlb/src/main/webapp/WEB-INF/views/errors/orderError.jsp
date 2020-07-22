<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<link rel="stylesheet" type="text/css" href="${_resourceURL}static/css/order.css" media="all">

				<div class="location">
					<div class="bracket">
						<a class="tlt" href="/">HOME</a>
					</div>
					<div class="bracket">
						<a class="tlt" href="/secured/order/new"><spring:message code='common.order.fail' /></a>
					</div>
				</div>

				<h2 class="subConTlt"><span><spring:message code='common.order.fail' /></span></h2>
				<div class="step_wrap step1">
					<div class="step_state">
						<span><spring:message code="cart.txt.title" /></span>
						<span><spring:message code="order.txt.title1" /></span>
						<span class="last on"><spring:message code='common.order.fail' /></span>
					</div>
				</div>

				<div class="order_fail">
					<div class="cont_box">
						<h3 class="colorDark">
							<spring:message code='common.order.fail.msg1' htmlEscape="false" />
							<br />
							<c:if test="${message ne 'orderError'}">
							<br />
							${message}
							</c:if>
						</h3>
						<p class="colorLight fontL">
							<spring:message code='common.repeat.error.call.center' />
							<br><br>
							<span class="customerCenter">
								0000-0000
							</span>
						</p>
					</div>
				</div>
