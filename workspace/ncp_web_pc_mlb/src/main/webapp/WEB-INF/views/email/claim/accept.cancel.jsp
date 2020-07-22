<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
						<tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">주문취소 신청이 접수 되었습니다.</td> <!-- 2019.02.08 문구 수정 -->
						</tr>
						<tr>
							<td height="15"></td>
						</tr>
						<tr>											
							<td align="center" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding:0 20px; word-break:keep-all">			
								고객님의 주문취소 신청이 접수 되었으며, 결제금액이 환불될 예정입니다.<br>주문 취소 내역은 <strong style="font-weight:bold; color:#ff3600">마이페이지 > 주문정보 > 취소/반품/교환</strong> 에서 확인 가능합니다. 
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
										<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${orderInfo.cstmrNm}"/></td>
									</tr>
									<tr>
										<td colspan="2" height="20"></td>
									</tr>
									<tr>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px;">주문일시</td>
										<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif;"><c:out value="${orderInfo.ordDt}"/></td>
									</tr>
									<tr>
										<td colspan="2" height="20"></td>
									</tr>
																		<tr>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif; width:100px; vertical-align:top">주문번호</td>
										<td align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;"><strong style="display:block"><c:out value="${orderInfo.ordNo}"/></strong></td>
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
						<tr>
							<td height="57"></td> 
						</tr>						
						<!-- 2018-07-28 -->
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">주문상품</td>
									</tr>
									<tr>
										<td height="18"></td> 
									</tr>
									<tr><td style="background:#000" height="1"></td></tr>
									<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmInfo.lgsDlvspFoExtend}">
									<c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
									<c:if test="${clmWrhsGodList.godTpCd ne 'PCHS_GFT' && clmWrhsGodList.godTpCd ne 'CNVRS_GFT'}">
									<tr>
										<td style="padding:30px 0">
											<table cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<td width="100" style="vertical-align:top;">
														<span style="width:100px; height:100px; background:#f0f0f0; display:block; overflow:hidden;">
															<span style="display:table; height:100px">
																<span style="display:table-cell; vertical-align:middle"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/75x100'/>" alt=""></span><!-- 상품이미지 -->
															</span>
														</span>
													</td>
													<td style="padding:0 20px; vertical-align:top; font:normal 12px/18px '돋움', dotum, sans-serif;">
														<c:out value='${clmWrhsGodList.godNm}'/>
														<p style="margin:0; padding:3px 0 10px 0"></p>
														<c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/>
													</td>
													<td width="50px" align="center" style="font:normal 12px/14px '돋움', dotum, sans-serif;""><c:out value='${clmWrhsGodList.clmQty}'/>개</td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
									<c:if test="${clmWrhsGodList.godTpCd eq 'PCHS_GFT' or clmWrhsGodList.godTpCd eq 'CNVRS_GFT'}">
									<tr>
										<td style="padding:30px 0">
											<table cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<td width="100" style="vertical-align:top;">
														<span style="width:100px; height:100px; background:#f0f0f0; display:block; overflow:hidden;">
															<span style="display:table; height:100px">
																<span style="display:table-cell; vertical-align:middle"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/75x100'/>" alt=""></span><!-- 상품이미지 -->
															</span>
														</span>
													</td>
													<td style="padding:0 20px; vertical-align:top; font:normal 12px/18px '돋움', dotum, sans-serif;">
														<c:out value='${clmWrhsGodList.godNm}'/>
														<p style="margin:0; padding:3px 0 10px 0"></p>
														<c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/>
													</td>
													<td width="50px" align="center" style="font:normal 12px/14px '돋움', dotum, sans-serif;""><c:out value='${clmWrhsGodList.clmQty}'/>개</td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
									</c:forEach>
									</c:forEach>
									<tr><td style="background:#ddd" height="1"></td></tr>
								</table>
							</td>
						</tr>
						<!-- //2018-07-28 -->
						<tr>
							<td height="57"></td> 
						</tr>	
						<!--취소정보 -->
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td colspan="2" align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif;">취소정보</td>
									</tr>
									<tr>
										<td colspan="2" height="18"></td> 
									</tr>
									<tr>
										<td colspan="2" style="border-top:1px solid #000" height="28"></td>
									</tr>
									<tr>
										<th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">상품가격</th>
										<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.saleSumAmt}" pattern="#,###"/>원</td>
									</tr>
									<tr>
										<td colspan="2" height="18"></td> 
									</tr>
									<tr>
										<th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">할인적용</th>
										<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#ff3600">-<fmt:formatNumber value="${clmInfo.allDcSumAmt}" pattern="#,###"/>원</td>
									</tr>
									<tr>
										<td colspan="2" height="18"></td> 
									</tr>
									<tr>
										<th align="left" style="font:bold 12px/14px '돋움', dotum, sans-serif;">배송비</th>
										<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.cnclDlvCst - clmInfo.rtgodDlvCst}" pattern="#,###"/>원</td>
									</tr>
									<tr>
										<td colspan="2" height="28"></td>
									</tr>
									<tr>
										<td colspan="2" height="1" style="background:#ddd"></td>
									</tr>
									<tr>
										<td colspan="2" height="28"></td>
									</tr>

									<tr>
										<td  colspan="2">
											<table cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<th rowspan="7" width="100" align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif; vertical-align:top">환급금액</th>
													<th align="left"  width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif;">마일리지 환급</th>
													<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.unityPntUseSumAmt}" pattern="#,###"/>원</td>
												</tr>
												<tr>
													<td colspan="3" height="18"></td>
												</tr>
												<tr>													
													<th align="left" width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif;">포인트 환급</th>
													<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.webpntUseSumAmt}" pattern="#,###"/>원</td>
												</tr>	
												<tr>
													<td colspan="3" height="18"></td>
												</tr>
												<c:forEach var="payItem" items="${orderInfo.payList }">
												<c:if test="${payItem.dealTpCd != 'DEPST_WAIT'}">
												<tr>													
													<th align="left" width="110" style="font:bold 12px/18px '돋움', dotum, sans-serif; vertical-align:top"><ncp:code code='${payItem.payMnCd}'/></th>
													<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif; vertical-align:top;">${payItem.bnkAcctNo }<strong style="font-size:14px; color:#ff3600; padding-left:10px"><fmt:formatNumber value="${payItem.stdrCrncyPayAmt}" pattern="#,###"/>원</strong></td>
												</tr>	
												</c:if>	
												<c:if test="${payItem.dealTpCd == 'DEPST_WAIT'}">
													<tr>
														<td  colspan="2">
															<table cellspacing="0" cellpadding="0" width="100%">
																<tr>
																	<th rowspan="7" width="100" align="left" style="font:bold 14px/18px '돋움', dotum, sans-serif; vertical-align:top">환급금액</th>															
																	<td align="right" style="font:normal 12px/18px '돋움', dotum, sans-serif;">0원</td>
																</tr>																
															</table>
														</td>
													</tr>		
												</c:if>	
												</c:forEach>						
											</table>
										</td>
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
						<!-- //취소정보 -->
						<tr><td height="35"></td></tr>
						<!-- 안내 Text -->
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">환불은 환불 승인일로부터 3~5일(영업일 기준) 후 진행됩니다. 정확한 환불일자는 해당 금융기관에서 확인하실 수 있습니다.</td>
						</tr>					
						<tr><td height="13"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">주문취소 완료 후 신용카드 결제는 승인취소, 계좌이체 및 무통장입금은 입금계좌 또는 결제 시 지정한 고객님의 환불계좌로 환불 처리됩니다.</td>
						</tr>
						<tr><td height="13"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">승인취소 및 환불처리 내역은 3영업일 이후 해당 금융기관에서 확인이 가능합니다.</td>
						</tr>
						<!-- 네이버페이 환불 안내문구 : S -->
						<tr><td height="26"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666">네이버페이 간편 결제</td>
						</tr>
						<tr><td height="13"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">간편 신용카드/체크카드 : 취소 완료 후 3~5영업일 이후 환불(승인/매입 구분 불가)</td>
						</tr>
						<tr><td height="13"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">간편 계좌이체 : 취소 완료 즉시 환불(단, 은행 정기점검시간등에는 환불 실패)</td>
						</tr>
						<tr><td height="13"></td></tr>
						<tr>
							<td style="font:normal 12px/18px '돋움', dotum, sans-serif; color:#666; padding-left:10px; background:url('${_resourceURL }static/images/mail/icon_txt.png') no-repeat 0 6px">포인트 : 취소 완료 즉시 환불</td>
						</tr>
						<!-- 네이버페이 환불 안내문구 : E -->
						<!-- //안내 Text -->	