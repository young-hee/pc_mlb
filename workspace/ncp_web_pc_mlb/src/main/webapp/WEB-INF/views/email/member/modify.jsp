<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>



						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">고객님의 회원정보가 변경되었습니다.</td>
						</tr>
						<tr>
							<td height="25"></td>
						</tr>
						<tr>														
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; padding:0 20px; color:#666; word-break:keep-all">												
								안녕하세요. <strong style="font-weight:bold; color:#000">MLB Korea</strong> 입니다.<br><strong style="font-weight:bold; color:#000">${mbrNm}</strong> 고객님의 회원정보가 ‘${modifyDt}’ 에 변경되었습니다.<br>회원님의 변경된 회원정보는  <strong style="font-weight:bold; color:#ff3600"> 마이페이지 > 회원정보 > 회원정보 수정</strong> 에서 확인 할 수 있습니다.
							</td>							
						</tr>
						<tr>
							<td height="36"></td>
						</tr>
						<tr>
							<td align="center">
								<a href="${modifyUrl}" target="_blank" style="display:inline-block; padding:15px 35px; font:bold 12px/14px '돋움', dotum, sans-serif; background:#000; color:#fff; text-decoration:none">회원정보 수정 바로가기</a>
							</td>
						</tr>						
						<tr>
							<td height="60"></td>
						</tr>
						<tr>
							<td colspan="2" height="1" style="background:#ddd"></td>
						</tr>	
						<tr>
							<td height="57"></td>
						</tr>					
						<tr>
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">회원정보 수정을 하지 않았음에도 본 메일을 수신하신 경우 고객센터로 연락 주시면 확인해 드리겠습니다.<br>언제나 고객만족을 위해 노력하는 MLB가 되겠습니다.</td>
						</tr>			
					
					
