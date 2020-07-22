<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
 <%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

	<div class="contain cs" id="contain">
		<div class="container">
			<h2 class="title01"><spring:message code="helpdesk.submain.noti.ttl" text="공지사항" /></h2>
			<main class="contents noticeView-wrap" id="contents">
				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span><spring:message code="helpdesk.common.location.lbl" text="고객센터" /></span>
						<strong title="현재 위치"><spring:message code="helpdesk.common.noit.location.lbl" text="공지사항" /></strong>
					</p>
				</div>

				<!-- table S -->
				<div class="board-view">
					<div class="board-header">
						<ncp:code code="${noticeDetail[0].sysNoti.notiTpCd}" var="tpcd"/>
						<strong>[<c:out value ="${tpcd.cdNm}"/>] <c:out value="${ noticeDetail[0].sysNoti.notiSj}"/></strong>
						<p><b><c:out value="${noticeDetail[0].noticeRegDt}" /></b>(<spring:message code="helpdesk.common.list.sort.count.txt" text="조회수" /> <em><c:out value="${ noticeDetail[0].sysNoti.inqireNum}" /></em>)</p>
					</div>
					<div class="board-cnt">
						${noticeDetail[0].sysNoti.pcNotiCont}
					</div>
				</div>
				<!--//table E -->

				<div class="btn-wrap">
					<a href="/helpdesk/notice/list" class="btn lg fill"><spring:message code="helpdesk.notiveiw.list.btn" text="목록"/></a>
				</div>

			</main>

		</div>
	</div>
