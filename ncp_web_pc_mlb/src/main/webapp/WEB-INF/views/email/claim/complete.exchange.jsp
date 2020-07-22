<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 
              <tr>
							<td align="center" style="font:bold 20px/24px '돋움', dotum, sans-serif; color:#000;">
								고객님의 교환요청 처리가 완료되어 새로운 상품이 발송됩니다.
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
												고객님의 교환요청 처리가 완료되었으며,<br>
												교환 상세 내역은 <strong style="color:#ff3600; font-weight:bold;"> 마이페이지 &gt; 주문정보 &gt; 취소/반품/교환</strong> 에서 확인 가능합니다.
											</td>
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
							<td height="25"></td>
						</tr>
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">고객명</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${orderInfo.cstmrNm}</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">주문번호</th>
										<td align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">${orderInfo.ordNo}</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">취소일자</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><fmt:formatDate pattern="yyyy.MM.dd" value="${clmInfo.clmDt}"/></td>
									</tr>
 
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">교환사유</th><!-- 2018-07-23 -->
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><ncp:code code='${clmInfo.clmResnCd}'/> / <c:out value='${clmInfo.clmResnCont}'/></td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">추가결제</th>
										<td align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;"><fmt:formatNumber value="${clmInfo.payCrncyPayAmt}" pattern="#,###" />원</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="18"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						<tr>
							<td height="62"></td>
						</tr>
						<tr>
							<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">교환상품</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td height="1" style="background:#000;"></td>
						</tr>
						
						<!-- 2018-07-28 -->
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<td height="28"></td>
									</tr>
									<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmInfo.lgsDlvspFoExtend}">
									<c:forEach var="clmWrhsGodList" varStatus="ordGodstatus" items="${listDlvsp.clmWrhsGodList}">
									<c:if test="${clmWrhsGodList.godTpCd ne 'PCHS_GFT' && clmWrhsGodList.godTpCd ne 'CNVRS_GFT'}">
									<tr>
										<td>
											<table cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<td width="100" valign="top" align="left"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/75x100'/>" alt=""></td>
													<td style="vertical-align:top;">
														<table cellspacing="0" cellpadding="0" width="100%">
															<c:if test="${clmWrhsGodList.dlvShopId ne 'X30004'}">
																<tr>
																	<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif; color:#ff3600;">[매장배송]</td>
																</tr>
															</c:if>
															<tr>
																<td colspan="2" height="3"></td>
															</tr>
															<tr>
																<td align="left"><a href="#" style="font:normal 12px/20px '돋움', dotum, sans-serif;color:#000;text-decoration:none;"><c:out value='${clmWrhsGodList.godNm}'/></a></td>
															</tr>
															<tr>
																<td colspan="2" height="8"></td>
															</tr>
															<tr>
																<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/> / <c:out value='${clmWrhsGodList.clmQty}'/>개</td>
															</tr>
															<tr>
																<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><strong style="font:bold 12px/20px dotum, sans-serif;"><fmt:formatNumber value="${clmWrhsGodList.saleAmt / clmWrhsGodList.clmQty}" pattern="#,###" /></strong>원</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="25" colspan="3"></td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
									<c:if test="${clmWrhsGodList.godTpCd eq 'PCHS_GFT' or clmWrhsGodList.godTpCd eq 'CNVRS_GFT'}">
									<tr>
										<td style="background:#f8f8f8;">
											<table cellspacing="0" cellpadding="0" width="100%">
												<tr>
													<td height="30" colspan="3"></td>
												</tr>
												<tr>
													<td width="32"></td>
													<td width="100" valign="top" align="left"><img src="<ncp:img path='${clmWrhsGodList.lstImgUrl }/dims/resize/75x100'/>" alt=""></td>
													<td style="vertical-align:top;">
														<table cellspacing="0" cellpadding="0" width="100%">
															<tr>
																<td align="left" style="font:normal 12px/14px '돋움', dotum, sans-serif; color:#ff3600;">[상품사은품]</td>
															</tr>
															<tr>
																<td colspan="2" height="3"></td>
															</tr>
															<tr>
																<td align="left"><a href="#" style="font:normal 12px/20px '돋움', dotum, sans-serif;color:#000;text-decoration:none;"><c:out value='${clmWrhsGodList.godNm}'/></a></td>
															</tr>
															<tr>
																<td colspan="2" height="8"></td>
															</tr>
															<tr>
																<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value='${clmWrhsGodList.colorCd}'/> / <c:out value='${clmWrhsGodList.itmNm}'/> / <c:out value='${clmWrhsGodList.clmQty}'/>개</td>
															</tr>
															<tr>
																<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><strong style="font:bold 12px/20px dotum, sans-serif;"><fmt:formatNumber value="${clmWrhsGodList.saleAmt / clmWrhsGodList.clmQty}" pattern="#,###" /></strong>원</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td height="25" colspan="3"></td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
									</c:forEach>
									</c:forEach>
									<tr>
										<td height="28"></td>
									</tr>
								</table>
							</td>
						</tr>
						<!-- //2018-07-28 -->

						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						
						<tr>
							<td height="62"></td>
						</tr>
						<tr>
							<td style="font:bold 14px/24px '돋움', dotum, sans-serif; color:#000;">배송지정보</td>
						</tr>
						<tr>
							<td height="14"></td>
						</tr>
						<tr>
							<td height="1" style="background:#666;"></td>
						</tr>
						<tr>
							<td height="22"></td>
						</tr>
						<c:forEach var="listDlvsp" varStatus="dlvStatus" items="${clmInfo.lgsDlvspFoExtend}">
						<tr>
							<td>
								<table cellspacing="0" cellpadding="0" width="100%">
									<tr>
										<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">주문번호</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${orderInfo.ordNo}</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">배송방법</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">택배</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">주문하신분</th><!-- 2018-07-23 -->
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${orderInfo.cstmrNm}</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">받으시는분</th><!-- 2018-07-23 -->
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${listDlvsp.addrseNm }</td>
									</tr>
									<!-- 2018-07-23 -->
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<!-- //2018-07-23 -->
									<tr>
										<th width="102" valign="top" align="left" style="font:bold 12px/20px '돋움', dotum, sans-serif;">우편번호</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${listDlvsp.addrsePostNo }</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">배송주소</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${listDlvsp.addrseBaseAddr } ${listDlvsp.addrseDetailAddr }</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">배송메세지</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;">${listDlvsp.dlvMemo}</td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">휴대전화</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value='${listDlvsp.addrseMobilAreaNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofNo}'/>-<c:out value='${listDlvsp.addrseMobilTlofWthnNo}'/></td>
									</tr>
									<tr>
										<td colspan="2" height="16"></td>
									</tr>
									<tr>
										<th align="left" valign="top" style="font:bold 12px/20px '돋움', dotum, sans-serif;">전화번호</th>
										<td align="left" style="font:normal 12px/20px '돋움', dotum, sans-serif;"><c:out value='${listDlvsp.addrseTelAreaNo}'/>-<c:out value='${listDlvsp.addrseTelTlofNo}'/>-<c:out value='${listDlvsp.addrseTelTlofWthnNo}'/></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="23"></td>
						</tr>
						<tr>
							<td height="1" style="background:#eee;"></td>
						</tr>
						</c:forEach>