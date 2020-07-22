<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">MLB 회원 가입을 축하합니다.</td>
						</tr>
						<tr>
							<td height="25"></td>
						</tr>
						<tr>														
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:0 20px; color:#666; word-break:keep-all">												
								MLB의 회원이 되신 ${mbrNm} 고객님께서는 F&F의 패밀리 브랜드인 Discovery Expedition, MLB, Stretch Angels, COLLECTED, JARDIN PERDU의 다양한 서비스 역시 하나의 아이디와 패스워드로 사용하실 수 있습니다
							</td>							
						</tr>
						<tr>
							<td height="58"></td>
						</tr>
						<!-- 가입정보 -->
						<tr>
							<td style="background:#f8f8f8;">
								<table cellspacing="0" cellpadding="0" width="100%">																		
									<tr>
										<td height="76"></td>
									</tr>
									<tr>
										<td align="center" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666">안녕하세요. <strong style="font-font-weight:bold; color:#000">MLB Korea</strong> 입니다.<br><strong style="font-font-weight:bold; color:#000">${mbrNm}</strong> 고객님의 회원가입을 축하드립니다.<br>회원님의 가입정보는 다음과 같습니다.</td>
									</tr>
									<tr>
										<td height="32"></td>
									</tr>
									<tr>
										<td>
											<table cellspacing="0" cellpadding="0" align="center">
												<tr>
													<td align="right" style="font:bold 12px/14px '돋움', dotum, sans-serif;">아이디</td>
													<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;"><span style="margin-left:30px; padding-left:30px;border-left:1px solid #ddd; font:normal 12px/14px '돋움', dotum, sans-serif;">${mbrId}</span></td>
												</tr>
												<tr>
													<td colspan="2" height="20"></td>
												</tr>
												<tr>
													<td align="right" style="font:bold 12px/14px '돋움', dotum, sans-serif;">이메일</td>
													<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;"><span style="margin-left:30px; padding-left:30px;border-left:1px solid #ddd; font:normal 12px/14px '돋움', dotum, sans-serif;">${mbrEmail}</span></td>
												</tr>
											</table>
										</td>
									<tr>
										<td height="76"></td>
									</tr>								
								</table>
							</td>
						</tr>	
						<!-- //가입정보 -->
						<tr>
							<td height="57"></td>
						</tr>
						<tr>
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666;">언제나 고객만족을 위해 노력하는 MLB가 되겠습니다.</td>
						</tr>
