

<section class="mds-section brand">
	<!-- div class="hdt"><span class="tit">STYLE IN MLB</span></div -->
	<c:forEach var="list" items="${content.godNotiExList}">
		<div class="html-box">
			<c:out value="${list.notiCont}" escapeXml="false"/>							
		</div>
	</c:forEach>
</section>
