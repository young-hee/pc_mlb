<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 
 
						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
								MLB-KOREA 포인트를 지금 사용하세요.
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
										<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#999;">
											고객님의 소유 포인트 중 일부가 <strong style="color:#010101;">${info.expirationDateYear}년 ${info.expirationDateMonth}월</strong> 소멸될 예정입니다. <br>
											자세한 내용은 <strong style="color:#ff3600;">마이페이지 &gt; 혜택정보 &gt; 포인트</strong> 에서 확인하실 수 있습니다.
										</td>
										<td width="8%"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="36"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						<tr>
							<td height="24"></td>
						</tr>
						<tr>
							<td align="center" style="font:bold 12px/24px '돋움', dotum, sans-serif; color:#000;">
								고객님의 MLB-KOREA 포인트 현황입니다.
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
											<td align="center" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#000;">
												적립 포인트는 2년간 유효하며, 2년이 지난 포인트는 익일에 자동소멸 됩니다. <br>모아두신 포인트 확인하시고 사용하시기 바랍니다.
											</td>
										<td width="8%"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="27"></td>
						</tr>
						<tr>
							<td height="1" style="background:#999;"></td>
						</tr>
						<tr>
							<td style="background:#f8f8f8;">
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td colspan="4" height="16"></td>
									</tr>
									<tr>
										<td width="27"></td>
										<td align="left" valign="top" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">사용가능 포인트 </td>
										<td align="right" style="font:bold 12px/20px '돋움', dotum, sans-serif; color:#000"><fmt:formatNumber value="${info.usefulAmt}" pattern="#,###"/>P</td>
										<td width="27"></td>
									</tr>
									<tr>
										<td colspan="4" height="16"></td>
									</tr>
									<tr>
										<td width="27"></td>
										<td align="left" valign="top" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">소멸예정 포인트 <span style="color:#ff3600">(${info.expirationDate})</span></td>
										<td align="right" style="font:bold 12px/20px '돋움', dotum, sans-serif; color:#000;">-<fmt:formatNumber value="${info.extshPrearngeAmt}" pattern="#,###"/>P</td>
										<td width="27"></td>
									</tr>
									<tr>
										<td colspan="4" height="21"></td>
									</tr>
									<tr>
										<td height="1" colspan="4" style="background:#eee;"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="60"></td>
						</tr>
						<tr>
							<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">포인트 사용안내</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td height="1" style="background:#666;"></td>
						</tr>
						<tr>
							<td height="24"></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td valign="top" width="12" style="font-size:0;"><img src="${_resourceURL}static/images/mail/bl_mail_list_01.png" width="2" height="11" alt="아이콘"></td>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">모아두신 포인트는 온라인 쇼핑몰에서만 사용 가능합니다.</td>
									</tr>
									<tr>
										<td valign="top" width="12" style="font-size:0;"><img src="${_resourceURL}static/images/mail/bl_mail_list_01.png" width="2" height="11" alt="아이콘"></td>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">포인트는 비 현금성으로, 현금으로 환원되지 않습니다.</td>
									</tr>
									<tr>
										<td valign="top" width="12" style="font-size:0;"><img src="${_resourceURL}static/images/mail/bl_mail_list_01.png" width="2" height="11" alt="아이콘"></td>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">리뷰작성을 통한 포인트 획득의 경우, 구매확정 후 90일내에 리뷰를 쓰셔야 포인트가 지급됩니다.</td>
									</tr>
									<tr>
										<td valign="top" width="12" style="font-size:0;"><img src="${_resourceURL}static/images/mail/bl_mail_list_01.png" width="2" height="11" alt="아이콘"></td>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif; color:#666;">상기 포인트내역의 산출 시점과 메일 발송 시점이 다를 수 있어, 실제 포인트 내역에 오차가 있을 수 있을 수 있습니다. </td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="24"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>

