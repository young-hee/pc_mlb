<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<html lang="ko">
	<head>
		<title></title>
		<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta charset="UTF-8">
	</head>

	<body style="margin:0;">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center">
					<table cellspacing="0" cellpadding="0" width="100%" style="max-width:710px">
						<tiles:insertAttribute name="top" />
						<tiles:insertAttribute name="body" />
						<tiles:insertAttribute name="bottom" />
					</table>
				</td>
			</tr>
		</table>
	</body>	
</html>
