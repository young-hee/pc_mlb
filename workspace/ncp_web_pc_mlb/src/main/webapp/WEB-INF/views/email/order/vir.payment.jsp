<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">무통장 입금이 확인되었습니다.</td>
						</tr>
						<tr>
							<td height="15"></td>
						</tr>
						<tr>														
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
								주문상세 내역 및 배송관련 정보는 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 주문/배송조회</strong> 에서 확인하실 수 있습니다								
							</td>							
						</tr>
						<tr>
							<td height="40"></td>
						</tr>
						<!-- 주문정보 -->
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">									
									<tr>
										<td colspan="2" height="1" style="background:#ddd"></td>
									</tr>	
									<tr>
										<td colspan="2" height="28"></td>
									</tr>							
									<tr>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">고객명</td>
										<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${info.cstmrNm}"/></td>
									</tr>
									<tr>
										<td colspan="2" height="20"></td>
									</tr>
									<tr>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">주문일시</td>
										<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${info.startOrdDt }"/></td>
									</tr>
									<tr>
										<td colspan="2" height="20"></td>
									</tr>
									<tr>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px; vertical-align:top">주문번호</td>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;"><strong style="display:block"><c:out value="${info.ordNo}"/></strong><a href='${_env.getProperty("ncp.url.mb_MLB.root.secure") }/mypage/order/list' target="_blank" style="display:inline-block; padding:9px 20px; margin-top:20px; text-decoration:none; font:normal 12px/14px '돋움', dotum, sans-serif; color:#fff; background:#000">주문상세내역</a></td>
									</tr>																
									<tr>
										<td colspan="2" height="28"></td>
									</tr>																		
									<tr>
										<td colspan="2" height="1" style="background:#ddd"></td>
									</tr>									
								</table>
							</td>
						</tr>	
						<!-- //주문정보 -->
						<tr>
							<td height="57"></td> 
						</tr>						
						<!-- 주문상품 -->
						<%@ include file="/WEB-INF/views/email/order/order.god.info.jspf"%>	
						<!-- //주문상품 -->
						<tr>
							<td height="57"></td> 
						</tr>	
						<!-- 결제정보 -->
						<%@ include file="/WEB-INF/views/email/order/order.pay.info.jspf"%>
						<!-- //결제정보 -->
						<tr>
							<td height="57"></td> 
						</tr>	
						<!-- 배송정보 -->
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">배송정보</td>
									</tr>
									<tr>
										<td height="18"></td> 
									</tr>
									<tr>
										<td style="border-top:1px solid #000" height="28"></td>
									</tr>
									<c:forEach var="dlvItem" items="${info.lgsDlvspList }">
									<tr>
										<td style="font:normal 12px/18px '돋움', dotum, sans-serif;">${dlvItem.addrseNm }</td>
									</tr>						
									<tr>
										<td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px">${dlvItem.addrsePostNo }<br>${dlvItem.addrseBaseAddr }&nbsp;${dlvItem.addrseDetailAddr }</td>
									</tr>		
																					
									<tr>
										<td style="font:normal 12px/18px '돋움', dotum, sans-serif; padding-top:18px">${dlvItem.dlvMemo }</td>
									</tr>
									</c:forEach>
									<tr>
										<td style="border-bottom:1px solid #ddd" height="28"></td>
									</tr>								
								</table>							
							</td>
						</tr>	
						<!-- //배송정보 -->
						
