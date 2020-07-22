<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.plgrim.ncp.framework.commons.ContextService" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

	<div class="contain cs" id="contain">
		<div class="container">

			<h2 class="title01">단체구매문의</h2>

			<main class="contents bundleOrder-wrap" id="contents">

				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span>고객센터</span>
						<strong title="현재 위치">단체구매문의</strong>
					</p>
				</div>

				<div class="bundleOrderBox">
					<div class="bundleOrderProcessStep">
						<ol>
							<li><span>Step 1</span>구매문의접수</li>
							<li><span>Step 2</span>본사 확인</li>
							<li><span>Step 3</span>개별 연락</li>
							<li><span>Step 4</span>구매완료</li>
						</ol>
					</div>
					<ul class="text-list02 col-type01">
						<li>
							팀/단체복 주문 문의 : Tel. 080-807-0012<span></span>
							문의시간 : 평일 09:00 ~ 18:00<span></span>
							점심시간 : 12:00~13:00 (주말/공휴일 휴무)
						</li>
						<li>유의사항 : 단체구매문의를 접수하시면 담당자가 입력하신 핸드폰번호 또는 이메일주소로 개별 연락드립니다.</li>
					</ul>
				</div>

				<div class="btn-wrap">
					<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
					   <a href="/helpdesk/bundleOrder/new" class="btn lg">비회원 문의하기</a>
					   <a href="#" class="btn lg fill" onclick="openLayerPopupForLogin('guestGroupCounsel', '/helpdesk/bundleOrder/new'); return false;"><spring:message code='footer.lbl.menu3' /></a>
					</sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                       <a href="/helpdesk/bundleOrder/new" class="btn lg fill"><spring:message code='footer.lbl.menu3' /></a>
                    </sec:authorize>
				</div>

			</main>

		</div>
	</div>