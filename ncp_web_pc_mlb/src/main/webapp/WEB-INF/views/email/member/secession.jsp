<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
								회원탈퇴 요청이 처리되었습니다.
							</td>
						</tr>
						<tr>
							<td height="16"></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tbody><tr>
										<td width="8%"></td>
											<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#999;">
												<strong style="color:#000; font-weight:bold;">${mbrNm}</strong> 고객님의 통합회원 탈퇴처리가 완료되었습니다. 그동안 저희 F&F의 서비스를 이용해 주셔서 감사드리며, 앞으로 F&F는 고객님이 더 나은 쇼핑을 즐기실 수 있도록 노력하겠습니다.
											</td>
										<td width="8%"></td>
									</tr>
								</tbody></table>
							</td>
						</tr>
						<tr>
							<td height="52"></td>
						</tr>
						<tr>
							<td style="background:#f8f8f8;">
								<table cellspacing="0" cellpadding="0" width="100%">
									<tbody><tr>
										<td height="76"></td>
									</tr>
									<tr>
										<td align="center" style="font:bold 12px/14px '돋움', dotum, sans-serif;">아이디<span style="margin-left:30px; padding-left:30px;border-left:1px solid #ddd; font:normal 12px/14px '돋움', dotum, sans-serif;">${mbrId}</span></td>
									</tr>
									<tr>
										<td height="26px"></td>
									</tr>
									<tr>
										<td align="center" style="font:bold 12px/14px '돋움', dotum, sans-serif;">탈퇴일<span style="margin-left:30px; padding-left:30px;border-left:1px solid #ddd; font:normal 12px/14px '돋움', dotum, sans-serif;">${secsnDt}</span></td>
									</tr>
									<tr>
										<td height="76"></td>
									</tr>
								</tbody></table>
							</td>
						</tr>
						<tr>
							<td height="18"></td>
						</tr>
						<tr>
							<td style="font:normal 12px/20px '돋움', dotum, sans-serif;">탈퇴 후 <strong style="color:#ff3600;font-weight:normal;">30</strong>일 동안은 <strong style="color:#ff3600;font-weight:normal;">재가입</strong> 하실 수 없음을 알려드리며,보유하신 <strong style="color:#ff3600;font-weight:normal;">마일리지/쿠폰/포인트</strong>는 모두 자동 소멸됩니다.</td>
						</tr>
