<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/modify.member.view.js?v=${_version}"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>
		
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents memberInfoModi-wrap" id="contents">
				<form action="<c:url value='/mypage/member/modifyMember'/>" id="modifyMbrForm" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="hidden" name="familyExceptionFlag" value="${familyExceptionFlag}" />
				<input type="hidden" name="targetPath" value="${targetPath}" />
				<input type="hidden" name="mbr.emailRecptnAgrYn" />
				<input type="hidden" name="mbr.smsRecptnAgrYn" />
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<p class="text-required"><span class="required">*</span> 필수입력 항목</p>
				
				<div class="board-write forms">
					<table summary="회원정보 수정 입력">
						<caption>회원정보 수정</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="">아이디</label></th>
							
							<td>
								<p>${mbr.mbrId}</p>
								<%-- <a href="#" class="btn gray sm" onClick="javascript:openPasswordPopup();return false;">비밀번호 변경</a>--%>
							</td>
							
						</tr>						
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="">비밀번호</label></th>
							<td>
								<p>
									<input type="password" class="input-style01" style="width:550px;" placeholder="새 비밀번호 ( 8~12자 영문, 숫자, 특수문자 중 최소 2가지 조합)" id="newPassword" name="newPassword"	validateText="비밀번호"	maxlength="12"/>
									<span class="error-msg"></span>
								</p>
								<p>
									<input type="password" class="input-style01" style="width:550px;" placeholder="새 비밀번호 확인" id="mbrCheckPw" name="mbrCheckPw" validateText="비밀번호 확인"	maxlength="12"/>
									<span class="error-msg"></span>
								</p>
							</td>
						</tr>						
						<tr>
							<th scope="row"><label for="">이름</label></th>
							<td>
								<p>${mbr.mbrNm}</p>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="">생일</label></th>
							<td>
								<p>
									${ fn:substring(mbr.mbrBrthdy,0,4) }년 
									${ fn:substring(mbr.mbrBrthdy,4,6) }월 
									${ fn:substring(mbr.mbrBrthdy,6,8) }일
								</p>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="mbrEmail">이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" class="input-style01" style="width:110px;" id="mbrEmail" title="<spring:message code='member.join.lbl.email.ttl1' />" 
										validate="required;" validateText="<spring:message code='member.join.lbl.email' />" value="${ fn:substringBefore(mbr.mbrEmail, '@') }">
								<span class="at">@</span>
								<input type="text" class="input-style01" style="width:110px;" id="mbrEmailDomain"  title="<spring:message code='member.join.lbl.email.ttl2' />"
										validate="required;" validateText="<spring:message code='member.join.lbl.email' />" value="${ fn:substringAfter(mbr.mbrEmail, '@') }">
								<!-- select -->
								<div class="select-style01 d_select">									
									<button type="button" class="d_select_sel" style="width:150px;" id="emailDefaultDomain"><span><spring:message code='member.join.lbl.email.domain' /></span></button>
									<ul>
										<li><a href="#"><spring:message code='member.join.lbl.email.domain' /></a></li>
										<li><a href="#">naver.com</a></li>
										<li><a href="#">daum.net</a></li>
										<li><a href="#">nate.com</a></li>
										<li><a href="#">gmail.com</a></li>
										<li><a href="#">hotmail.com</a></li>
									</ul>
								</div>
								<span class="error-msg"></span>
								<input type="hidden" name="mbr.mbrEmail" validate="required;email;" validateText="<spring:message code='member.join.lbl.email' />" value="${mbr.mbrEmail}" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="mobilAreaNo">휴대전화</label> <span class="required">*</span></th>
							<td>							
								<div class="inputcallBox">
									<input type="hidden" id="mobileNumber" validate="required;phone;" validateText="<spring:message code='member.join.lbl.mobile' />" value="${mbr.mobilAreaNo}${mbr.mobilTlofNo}${mbr.mobilTlofWthnNo}"/>
									<input type="text" style="width:64px;" id="mobilAreaNo" name="mbr.mobilAreaNo" class="input-style01" title="<spring:message code='member.join.lbl.mobile.ttl1' />"
										value="${mbr.mobilAreaNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="3" readonly/>
									<span class="hyphen">-</span>
									<input type="text" style="width:70px;" id="mobilTlofNo" name="mbr.mobilTlofNo" class="input-style01" title="<spring:message code='member.join.lbl.mobile.ttl2' />"
										value="${mbr.mobilTlofNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="4" readonly/>
									<span class="hyphen">-</span>
									<input type="text" style="width:70px;" id="mobilTlofWthnNo" name="mbr.mobilTlofWthnNo" class="input-style01" title="<spring:message code='member.join.lbl.mobile.ttl3' />"
										value="${mbr.mobilTlofWthnNo}" validate="required;digit;" validateText="<spring:message code='member.join.lbl.mobile' />" maxlength="4" readonly/>
									<!-- 1905 번호인증 추가 : 인증하기 버튼 S -->
									<a class="btn-style04" id="popCertificationBtn"" href="javascript:;" onclick="layerPopup.popupOpenNow('#popCertification')">인증하기</a>
									<!-- 1905 번호인증 추가 : 인증하기 버튼 E -->
								</div>
								<span class="error-msg" style="display:block;"></span>
								<!-- 1905 번호인증 추가 : 멘트 추가 S -->
								<p class="dsc">휴대전화번호를 변경하시려면 <strong>인증하기</strong>를 눌러 변경해 주세요.</p>
								<!-- 1905 번호인증 추가 : 멘트 추가 E -->
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="mbrPostNo">주소</label></th>
							<td>							
								<input type="text" id="mbrPostNo" name="mbr.mbrPostNo" class="input-style01" title="<spring:message code='member.join.lbl.address.ttl1' />" style="width:240px;" value="${mbr.mbrPostNo}" readonly />
								<a href="#" class="btn-style04" onClick="openZipcodePopup();return false;"><spring:message code='member.join.btn.address.find' /></a>
								<input type="text" id="mbrBaseAddr" name="mbr.mbrBaseAddr" class="input-style01 mtST10" title="<spring:message code='member.join.lbl.address.ttl2' />" style="width:550px;" value="${mbr.mbrBaseAddr}" readonly />
								<input type="text" id="mbrDetailAddr" name="mbr.mbrDetailAddr" class="input-style01 mtST10" title="<spring:message code='member.join.lbl.address.ttl3' />" style="width:550px;" value="${mbr.mbrDetailAddr}" />
								<input type="hidden" name="mbr.mbrAddrSectCd" value="${mbr.mbrAddrSectCd}" />
								<span class="error-msg"></span>
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="boardWritechild">자녀정보</label></th>
							<td>
								<ul id="childList">
									
								</ul>	
								
								<div class="childPlusWrap">
									<a href="#" class="btn fill" id="childAddBt" onclick="return false;">추가</a>
									<ul class="childPlusBox">
										<li name="additionalChild" style="display:none">
											<div class="inputAllST">
												<input type="text" id="childrenName1" name="childrenName" class="input-style01" title="이름" placeholder="이름" style="width:135px;" maxlength="20">
												<input type="hidden"  id="childrenBirthDate1" name="childrenBirthDate" />
												<input type="number" id="childrenBirthYear1" name="childrenBirthYear" class="input-style01" title="생년" placeholder="생년(4자)" style="width:80px;" maxlength="4">
												<input type="hidden" id="childrenBirthMonth1" name="childrenBirthMonth">
												<!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
													<ul>
														<li><a href="#" onclick="setChildBirthMonth('01', this)">1월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('02', this)">2월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('03', this)">3월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('04', this)">4월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('05', this)">5월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('06', this)">6월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('07', this)">7월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('08', this)">8월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('09', this)">9월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('10', this)">10월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('11', this)">11월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('12', this)">12월</a></li>
													</ul>
												</div>
												<!-- //select -->
												<input type="number" id="childrenBirthDay1" name="childrenBirthDay" maxlength="2" class="input-style01" title="일" placeholder="일" style="width:80px;">
											</div>
										</li>
										<li name="additionalChild" style="display:none">
											<div class="inputAllST">
												<input type="text" id="childrenName2" name="childrenName" class="input-style01" title="이름" placeholder="이름" style="width:135px;" maxlength="20">
												<input type="hidden"  id="childrenBirthDate2" name="childrenBirthDate" />
												<input type="number" id="childrenBirthYear2" name="childrenBirthYear" class="input-style01" title="생년" placeholder="생년(4자)" style="width:80px;" maxlength="4">
												<input type="hidden" id="childrenBirthMonth2" name="childrenBirthMonth">
												<!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
													<ul>
														<li><a href="#" onclick="setChildBirthMonth('01', this)">1월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('02', this)">2월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('03', this)">3월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('04', this)">4월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('05', this)">5월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('06', this)">6월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('07', this)">7월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('08', this)">8월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('09', this)">9월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('10', this)">10월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('11', this)">11월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('12', this)">12월</a></li>
													</ul>
												</div>
												<!-- //select -->
												<input type="number" id="childrenBirthDay2" name="childrenBirthDay" maxlength="2" class="input-style01" title="일" placeholder="일" style="width:80px;">
											</div>
										</li>
										<li name="additionalChild" style="display:none">
											<div class="inputAllST">
												<input type="text" id="childrenName3" name="childrenName" class="input-style01" title="이름" placeholder="이름" style="width:135px;" maxlength="20">
												<input type="hidden"  id="childrenBirthDate3" name="childrenBirthDate" />
												<input type="number" id="childrenBirthYear3" name="childrenBirthYear" class="input-style01" title="생년" placeholder="생년(4자)" style="width:80px;" maxlength="4">
												<input type="hidden" id="childrenBirthMonth3" name="childrenBirthMonth">
												<!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
													<ul>
														<li><a href="#" onclick="setChildBirthMonth('01', this)">1월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('02', this)">2월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('03', this)">3월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('04', this)">4월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('05', this)">5월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('06', this)">6월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('07', this)">7월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('08', this)">8월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('09', this)">9월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('10', this)">10월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('11', this)">11월</a></li>
														<li><a href="#" onclick="setChildBirthMonth('12', this)">12월</a></li>
													</ul>
												</div>
												<!-- //select -->
												<input type="number" id="childrenBirthDay3" name="childrenBirthDay" maxlength="2" class="input-style01" title="일" placeholder="일" style="width:80px;">
											</div>
										</li>
									</ul>
								</div>								
																				
								<span class="error-msg"></span>		
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="">e-Mail, SMS(알림톡)</label></th>
							<td>
								<div class="chkST-mg">
									<span class="check-skin">
										<input type="checkbox" id="emailRecptnAgrYn" name="emailRecptnAgrYn" value="Y" <c:if test="${mbr.emailRecptnAgrYn == 'Y'}">checked="checked"</c:if>>
										<span>e-Mail 수신</span>
									</span>
									<label for="emailRecptnAgrYn">e-Mail 수신</label>
									<span class="check-skin">
										<input type="checkbox" id="smsRecptnAgrYn" name="smsRecptnAgrYn" value="Y" <c:if test="${mbr.smsRecptnAgrYn == 'Y'}">checked="checked"</c:if>>
										<span>SMS(알림톡) 수신</span>
									</span>
									<label for="smsRecptnAgrYn">SMS(알림톡) 수신</label>
								</div>
								<ul class="text-list02 col-type01">
									<li>e-Mail, SMS(알림톡)을 통한 상품 및 이벤트 정보 수신에 동의합니다.</li>
									<li>거래정보(주문/반품/교환) 관련 e-Mail/SMS(알림톡)은 수신동의 하지 않아도 발송됩니다.</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
				
				<ul class="text-list02 col-type01">
					<li>상기 부가정보 수신 동의는 F&F 브랜드에서 회원에게 이메일, SMS를 활용하여 상품 및 서비스에 대한 제반 마케팅 활동을 하기 위한 회원정보 (개인정보취급 방침 중 “2. 수집하는 개인정보 항목” 기재 항목)제공 및 각 서비스 수신에 대한 사항입니다.</li>
					<li>약관변경이나 공지 등의 고지성 안내 메일은 수신동의 하지않더라도 고객의 권익보호를 위해 발송됩니다.</li>
					<li>제공된 회원정보는 F&F 브랜드가 별도 동의를 득한 경우를 제외하고는 회원탈퇴 후 30일까지 보유합니다.</li>
				</ul>
				
				<div class="btnWrapBox">
					<a href="#" class="btn" id="cancelBtn" onClick="return false;">취소</a>
					<a href="#" class="btn fill" id="confirmBtn" onClick="return false;">확인</a>
				</div>
				</form>
			</main>
		</div>
	</div>

	<!-- 1905 번호인증 추가 | 인증 레이어팝업 : S -->
	<form action="<c:url value='/mypage/member/modifyMember'/>" id="certificationMbrForm" method="post">
	<input type="hidden" name="REQ_SEQ" id="REQ_SEQ"/>
	<input type="hidden" name="RES_SEQ" id="RES_SEQ"/>
	<input type="hidden" name="CERT_END_YN" id="CERT_END_YN" value="N"/>
	<article id="popCertification" class="layer-popup popCertification">
		<section class="layer-popup-cont" tabindex="0">
			<h2>휴대폰 번호 인증</h2>

			<div class="layer-cont sect memInfo">
				<div class="board-write forms">
					<table summary="">
						<caption>휴대폰 번호 인증</caption>
						<colgroup>
							<col width="120">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">이름</th>
								<td>
									<input type="text" class="input-style01 name" placeholder="이름(실명입력)" name="mbrNm" id="mbrNm" validate='required' validateText="이름(실명입력)" />
									
									<span class="rdo-skin">
										<input type="radio" name="frgnrYn" id="frgnrYn1" checked="checked" value="N"><span>선택</span>
									</span>
									<label for="frgnrYn1">내국인</label>
									
									<span class="rdo-skin">
										<input type="radio" name="frgnrYn" id="frgnrYn2" value="Y"><span>선택</span>
									</span>
									<label for="frgnrYn2">외국인</label>
									<span class="error-msg"></span>								
								</td>
							</tr>
							<tr>
								<th scope="row">생년월일</th>
								<td>
									<span class="birth">
										<input type="text" class="input-style01 year" style="width:85px;" placeholder="생년(4자)" name="birthyear" id="birthyear" maxlength="4" validate='birthyear;number'><%-- 생년(4자) --%>
										<input type="hidden" name="birthmonth" id="birthmonth" validate='birthmonth'/>														
										<div class="select-style01 d_select mm">
											<button type="button" class="d_select_sel" style="width:65px;" id="btnMonth"><span>월</span></button>
											<ul>
												<li><a href="#" onclick="setBirthMonth('01')">1</a></li>
												<li><a href="#" onclick="setBirthMonth('02')">2</a></li>
												<li><a href="#" onclick="setBirthMonth('03')">3</a></li>
												<li><a href="#" onclick="setBirthMonth('04')">4</a></li>
												<li><a href="#" onclick="setBirthMonth('05')">5</a></li>
												<li><a href="#" onclick="setBirthMonth('06')">6</a></li>
												<li><a href="#" onclick="setBirthMonth('07')">7</a></li>
												<li><a href="#" onclick="setBirthMonth('08')">8</a></li>
												<li><a href="#" onclick="setBirthMonth('09')">9</a></li>
												<li><a href="#" onclick="setBirthMonth('10')">10</a></li>
												<li><a href="#" onclick="setBirthMonth('11')">11</a></li>
												<li><a href="#" onclick="setBirthMonth('12')">12</a></li>
											</ul>
										</div>
										<input type="text" class="input-style01 dd" style="width:65px;" placeholder="일" name="birthdate" id="birthdate" maxlength="2" validate='birthdate;number' validateText="태어난 일(날짜)">
										<input type="hidden" name="gender" id="gender" validate='gender'/>
										<input type="hidden" name="birthday" id="birthday"/>
									</span>
									<span class="rdo-skin">
										<input type="radio" name="genderRadio" id="gender1" value="1" onclick="setGender()"><span>선택</span>
									</span>
									<label for="gender1">남자</label>
									<span class="rdo-skin">
										<input type="radio" name="genderRadio" id="gender2" value="2" onclick="setGender()"><span>선택</span>
									</span>
									<label for="gender2">여자</label>
									<span class="error-msg"></span>								
								</td>
							</tr>
							<tr>
								<th scope="row">휴대폰번호</th>
								<td class="phone">
									<div class="select-style01 d_select phone0">
										<button type="button" class="d_select_sel" style="width:120px;" id="btnMobileCo"><span>통신사 선택</span></button>
										<ul>
											<li><a href="#" onclick="setMobileco('1'); return false;">SKT</a></li>
											<li><a href="#" onclick="setMobileco('2'); return false;">KT</a></li>
											<li><a href="#" onclick="setMobileco('3'); return false;">LG U+</a></li>
											<li><a href="#" onclick="setMobileco('5'); return false;">SKT 알뜰폰</a></li>
											<li><a href="#" onclick="setMobileco('6'); return false;">KT 알뜰폰</a></li>
											<li><a href="#" onclick="setMobileco('7'); return false;">LG U+ 알뜰폰</a></li>
										</ul>
									</div>
									<input type="hidden" name="mobileCo" id="mobileCo" validate='required' validateText="통신사 선택"/>
									<input type="hidden" name="newMobileNumber" id="newMobileNumber"/>														
									<input type="text" class="input-style01 phone1" style="width:70px;margin-left:10px;" name="phone1" id="phone1" maxlength="3" validate='required' validateText="휴대전화번호">
									<em class="dash">-</em>
									<input type="text" class="input-style01 phone2" style="width:70px;" name="phone2" id="phone2" maxlength="4" validate='required' validateText="휴대전화번호">
									<em class="dash">-</em>
									<input type="text" class="input-style01 phone3" style="width:70px;" name="phone3" id="phone3" maxlength="4" validate='required' validateText="휴대전화번호">

									<span class="error-msg"></span>
								</td>
							</tr>
							<tr>
								<th scope="row">본인인증</th>
								<td>
									<div class="d_toggle togbox">
										<div class="hbox">
											<span class="check-skin">
												<input type="checkbox" id="certAgreeYn" name="certAgreeYn" value="Y" validate='checkval' onclick="setAgree();"><span>선택</span>
											</span>
											<label for="certAgreeYn">본인 인증을 위한 약관 모두 동의</label>
											<a href="javascript:;" class="btn fill sm btnPslCertifi" onclick="goCertification(this); return false;">본인인증</a>
											<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
											<span class="error-msg"></span>
										</div>
										<div class="cbox d_toggle_cont">
											<ul class="list">
												<li>
													<span>
														<span class="check-skin">
															<input type="checkbox" id="chk_policy_1" onclick="setAgree2();"><span>선택</span>
														</span>
														<label for="chk_policy_1">개인정보이용동의</label>
													</span>
													<a href="javascript:;" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin1'); return false;">전문보기</a>
												</li>
												<li>
													<span>
														<span class="check-skin">
															<input type="checkbox" id="chk_policy_2" onclick="setAgree2();"><span>선택</span>
														</span>
														<label for="chk_policy_2">고유식별정보처리 동의</label>
													</span>
													<a href="javascript:;" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin2'); return false;">전문보기</a>
												</li>
												<li>
													<span>
														<span class="check-skin">
															<input type="checkbox" id="chk_policy_3" onclick="setAgree2();"><span>선택</span>
														</span>
														<label for="chk_policy_3">서비스이용약관동의</label>
													</span>
													<a href="javascript:;" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin3'); return false;">전문보기</a>
												</li>
												<li>
													<span>
														<span class="check-skin">
															<input type="checkbox" id="chk_policy_4" onclick="setAgree2();"><span>선택</span>
														</span>
														<label for="chk_policy_4">통신사이용약관 동의</label>
													</span>
													<a href="javascript:;" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin4'); return false;">전문보기</a>
												</li>
												<li style="display:none" id="mCoview">
													<span>
														<span class="check-skin">
															<input type="checkbox" id="chk_policy_5" onclick="setAgree2();"><span>선택</span>
														</span>
														<label for="chk_policy_5">개인정보  제3자 제공동의</label>
													</span>
													<a href="javascript:;" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin5'); return false;">전문보기</a>
												</li>
											</ul>
										</div>
										<!-- 본인 인증번호 입력  -->
										<div class="certifiBox">
											<div class="num">
												<input type="text" class="input-style01" placeholder="인증번호" name="sAuthNo" id="sAuthNo" validateText="인증번호" maxlength="6">
												<span class="time" id="remainTime">3분</span>
											</div>
											<div class="bts">
												<a href="javascript:;" class="btn btnConfirm" onclick="goCertificationEnd(); return false;">확인</a>
												<a href="javascript:;" class="btn fill btnResend" onclick="goCertification(this); return false;">재전송</a>
											</div>
											<ul class="text-list02 col-type01 bul-list">
												<li>3분 이내로 인증번호(6자리)를 입력해 주세요.</li>
												<%-- <li>인증번호가 오지 않으면 <a href="javascript:;" class="link">여기를 눌러주세요.</a></li> --%>
											</ul>
											<span class="error-msg"></span>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>				
			</div>
			<div class="layer-popup-close">
				<button type="button" class="d_layer_close">닫기</button>
			</div>
		</section>
	</article>
	</form>
	<!-- 1905 번호인증 추가 | 인증 레이어팝업 : E -->
	
<script>
//우편번호 팝업
function openZipcodePopup() {
    layerPopup.popupOpenNow("#layerPopupZipcode");
        
    var inHtml = "";
	
	inHtml = "<div class='addressFindInner' id='zipView1'> \
		<ul class='text-list02'> \
	<li><spring:message code='common.popup.zipcode.lbl.txt2'/></li> \
	<li><spring:message code='common.popup.zipcode.lbl.txt3'/></li> \
	<li><spring:message code='common.popup.zipcode.lbl.txt4'/></li> \
	<li><spring:message code='common.popup.zipcode.lbl.txt5'/></li> \
</ul> \
<div class='board-list'> \
	<table summary='우편번호 찾기'> \
		<caption>우편번호 찾기</caption> \
		<colgroup> \
			<col width='70px'> \
			<col> \
		</colgroup> \
		<tbody>	 \
			<tr> \
				<td colspan='2' class='no-result'><spring:message code='common.popup.zipcode.lbl.txt6'/></td> \
			</tr> \
		</tbody> \
	</table> \
</div> \
</div>";
		
	$("#zipView1").remove();
	$("#zipView2").remove();	
	$('#zipSearchBefore').append(inHtml);
	$("#srchKeyword").val("");
}
</script>
<%--@ include file="/WEB-INF/views/member/include/modify.password.layer.popup.jsp"--%>

<%@ include file="/WEB-INF/views/common/layerpop/zipcode.jsp"%>

<!-- 1905 번호인증 추가 | 인증 약관 레이어팝업 : S -->
<jsp:include page="/WEB-INF/views/member/include/certification.layer.popup.jsp"  flush="false" />	
<jsp:include page="/WEB-INF/views/member/include/join.layer.popup.jsp"  flush="false" />	
<!-- 1905 번호인증 추가 | 인증 약관 레이어팝업 : E -->