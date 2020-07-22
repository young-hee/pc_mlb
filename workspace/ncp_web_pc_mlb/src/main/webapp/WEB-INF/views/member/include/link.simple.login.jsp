<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--
	마이페이지 서브메인에서 사용하는 간편로그인 계정 연결
	다른 계정 연결이 추가적으로 생길 경우를 대비해 감싸는 jsp를 분리함.
--%>
					<h3 class="title08"><spring:message code='mypage.sub.main.link.simple.login' /></h3>
<%@ include file="/WEB-INF/views/member/naver/include/link.naver.jsp"%>
