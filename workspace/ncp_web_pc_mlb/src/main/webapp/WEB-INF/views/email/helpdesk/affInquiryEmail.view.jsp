<%@ page import="com.plgrim.ncp.framework.commons.ContextService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
	<body style="margin:0;">
		<table cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td align="center">
					<table cellspacing="0" cellpadding="0" width="100%" style="max-width:710px">
						<!-- 메일 헤더영역 -->
		
						<!-- //메일 헤더영역 -->
						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
								고객님의 제휴문의에 대한 답변입니다.
							</td>
						</tr>
						<tr>
							<td height="16"></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td width="8%"></td>
											<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#999;">안녕하세요. <strong style="color:#000; font-weight:bold;">${info.orgztInqerNm }</strong> 고객님 제휴 문의에 대해 아래와 같이 답변 드립니다.</td>
										<td width="8%"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="38"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						<tr>
							<td height="50"></td>
						</tr>
						<tr>
							<td style="font:bold 14px/18px '돋움', dotum, sans-serif; color:#000;">제휴문의 내용</td>
						</tr>
						<tr>
							<td height="18"></td>
						</tr>
						<tr>
							<td height="1" style="background:#999;"></td>
						</tr>
						<tr>
							<td height="24"></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">제휴구분</th>
										<ncp:code code="${info.orgztOrdAffInqDetailTpCd }" var="tpcd"/>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value ="${tpcd.cdNm}"/></td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">담당자명</th>
										<td align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;"> ${info.orgztInqerNm }</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">첨부파일</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${info.orgztFileNm }</td>
									</tr>
									<tr>
										<td colspan="2" height="18"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						<tr>
							<td height="28"></td>
						</tr>
						<tr>
							<td>
<!-- 								<img src="${_resourceURL}static/images/mail/mail_thumb_05.png" style="width:100%; height:auto" alt=""> -->
							</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#000;">
								 ${info.orgztInqCont }
							</td>
						</tr>
						<tr>
							<td height="24"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						<tr>
							<td height="62"></td>
						</tr>
						<tr>
							<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">제휴문의 답변</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td height="1" style="background:#000;"></td>
						</tr>
						<tr>
							<td height="25"></td>
						</tr>
						<tr>
							<td style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">${info.orgztAnsCont }</td>
						</tr>
						<tr>
							<td height="25"></td>
						</tr>
						<!-- <tr>
							<td height="1" style="background:#eee;"></td>
						</tr> -->
						<!-- 2018-07-16<tr>
							<td height="60"></td>
						</tr>
						<tr>
							<td valign="top" style="font-size:0;"><img src="${_resourceURL}static/images/mail/mail_banner_01.png" style="width:100%;" alt="배너"></td>
						</tr> -->
						<!-- 메일 푸터영역 -->
						<tr>
							<td height="60"></td>
						</tr>
						<tr>
							<td height="2" style="background:#000;"></td>
						</tr>
						<tr>
							<td height="20"></td>
						</tr>
						<tr>
							<td align="left"><a href="https://discovery-expedition.com" target="_blank"><img src="${_resourceURL}static/images/mail/img_footer_01.png" width="90" height="19" alt="Discovery"></a></td>
						</tr>
						<tr>
							<td height="18"></td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">본 메일은 정보안내 및 서비스 정책관련 고지성 메일로 회원님의 수신동의여부와 상관없이 발송됨을 알려드립니다.</td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">또한 본 메일은 발신 전용으로 회신 되지 않으며, 관련문의는 고객센터를 이용해주시기 바랍니다.</td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">고객센터 : 080 - 807 - 0012 | 평일 오전 9시 ~ 오후 6시 (토·일·공휴일 휴무)</td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">A/S : 02-3474-8914 | E-MAIL : discovery@fnf.co.kr | FAX : 02 - 520 - 0991</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">서울시 강남구 언주로 541 F&amp;F 빌딩 <br> FAX:02-521-2329 | E-mail:member@fnf.co.kr</td>
						</tr>
						<tr>
							<td align="left" style="color:#666; font:normal 11px/18px '돋움', dotum, sans-serif;">Copyright F&amp;F co,Ltd All Right Reserved.</td>
						</tr>
						<tr>
							<td height="60"></td>
						</tr>
						<!-- //메일 푸터영역 -->
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>