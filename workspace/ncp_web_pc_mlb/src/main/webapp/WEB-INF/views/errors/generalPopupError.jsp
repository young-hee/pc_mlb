<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <!-- 컨텐츠 시작 -->


    <h1 class="page-header"><spring:message code='common.popup.error.page' /></h1>

    <p>
        ${message}
    </p>
</div>

<!-- 로컬 JS 스크립트 선언 -->
<c:set var="page_js" scope="request">
    <script>
        $(document).ready(function () {
        });
    </script>
</c:set>
