<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/member/join.js?v=${_version}"></script>


	<div class="contain mb join" id="contain">	
		<div class="container">
		
			<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false" />
			<main class="contents" id="contents">

				<div class="join-wrap">
					
					<div class="top-info">
						<div class="hdt">F&amp;F 통합회원 가입</div>
						<p class="ss">MLB의 회원이 되시면 동일한 ID와 비밀번호로 <br>F&F 패밀리 브랜드 웹사이트의 다양한 서비스를 안전하고 편리하게 이용하실 수 있습니다.</p>
						<p class="tt"><strong class="t">F&amp;F Family Brand</strong>       MLB, Discovery Expedition, COLLECTED, JARDIN PERDU, Stretch Angels</p>
					</div>

					<!-- 혜택정보 -->
					<div class="member-benefit-list-join">  <!-- 20190211 변경 -->
						<h3 class="title04">MLB 회원혜택</h3>
						<ul>
							<li class="deps01">
								<strong>가입축하쿠폰</strong>
							</li>
							<li class="deps02">
								<strong>첫구매 감사쿠폰</strong>
							</li>
							<li class="deps03">
								<strong>생일쿠폰</strong>
							</li>
							<li class="deps04">
								<strong>마일리지 적립</strong>
							</li>
						</ul>
					</div>
	
					
					<!-- 가입정보 -->
					<form:form name="joinMbrForm" id="joinMbrForm" action="/member/join/add" method="post">
					<input type="hidden" name="REQ_SEQ" id="REQ_SEQ"/>
					<input type="hidden" name="RES_SEQ" id="RES_SEQ"/>
					<input type="hidden" name="CERT_END_YN" id="CERT_END_YN" value="N"/>
					<input type="hidden" name="mbr.emailRecptnAgrYn" id="mbr.emailRecptnAgrYn" value="N"/>
					<input type="hidden" name="mbr.smsRecptnAgrYn" id="mbr.smsRecptnAgrYn" value="N"/>
					<section class="sect memInfo">						
						<div class="hdt"><span class="tit">가입정보</span> <span class="tt"><em class="required">*</em> 필수입력 항목</span></div>
						<div class="board-write forms">							
							<table summary="개인정보입력">
								<caption>개인정보입력</caption>
								<colgroup>
									<col width="200">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label>기본정보</label> <span class="required">*</span></th>
										<td>
											<ul class="dt-list">
												<li>													
													<input type="text" class="input-style01 id" style="width:260px;" placeholder="아이디 (6~15자 영문 소문자.숫자)" id="mbrId" name="mbr.mbrId" validate="required;mbrid" validateText="아이디 (6~15자 영문 소문자.숫자)" maxlength="15">
													<span class="error-msg"></span>													
												</li>
												<li>													
													<input type="password" class="input-style01 pw" style="width:100%;" placeholder="비밀번호 ( 8~12자 영문, 숫자, 특수문자 중 최소 2가지 조합)" id="mbrPw" name="mbr.mbrPw" validate="required" validateText="비밀번호 ( 8~12자 영문, 숫자, 특수문자 중 최소 2가지 조합)" maxlength="12">
													<span class="error-msg"></span>
												</li>
												<li>												
													<input type="password" class="input-style01 pw com" style="width:100%;" placeholder="비밀번호 확인" id="mbrCheckPw" validate="required" maxlength="12" validateText="비밀번호 확인">
													<span class="error-msg"></span>
												</li>
												<li id="mbrNmAfter">													
													<input type="text" class="input-style01 name" placeholder="이름(실명입력)" name="mbrNm" id="mbrNm" validate='required' validateText="이름(실명입력)" /><%-- 이름(실명입력) --%>
													
													<span class="rdo-skin">
														<input type="radio" name="frgnrYn" id="frgnrYn1" checked="checked" value="N"><span>선택</span>
													</span>
													<label for="frgnrYn1">내국인</label>
													
													<span class="rdo-skin">
														<input type="radio" name="frgnrYn" id="frgnrYn2" value="Y"><span>선택</span>
													</span>
													<label for="frgnrYn2">외국인</label>
													<span class="error-msg"></span>
												</li>
												<li id="birthAfter">													
													<span class="birth">
														<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)" name="birthyear" id="birthyear" maxlength="4" validate='birthyear;number'><%-- 생년(4자) --%>
														<input type="hidden" name="birthmonth" id="birthmonth" validate='birthmonth'/>														
														<div class="select-style01 d_select mm">
															<button type="button" class="d_select_sel" style="width:80px;" id="btnMonth"><span>월</span></button>
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
														<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일" name="birthdate" id="birthdate" maxlength="2" validate='birthdate;number' validateText="태어난 일(날짜)">
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
												</li>
												<li>													
													<span class="email">
														<input type="text" class="input-style01 email1" style="width:130px;" placeholder="E-mail" id="mbrEmail" validate="required" validateText="E-mail">
														<em class="att">@</em>
														<input type="text" class="input-style01 email2" style="width:130px;" placeholder="" id="mbrEmailDomain" validate="required" validateText="E-mail">
														<div class="select-style01 d_select email3">
															<button type="button" class="d_select_sel" id="emailDefaultDomain" style="width:151px;"><span>직접입력</span></button>
															<ul>																
																<li><a href="#">naver.com</a></li>
																<li><a href="#">daum.net</a></li>
																<li><a href="#">nate.com</a></li>
																<li><a href="#">gmail.com</a></li>
																<li><a href="#">hotmail.com</a></li>
															</ul>
														</div>
														<input type="hidden" name="mbr.mbrEmail" validate="required;email;" validateText="E-mail" />
													</span>
													<span class="error-msg"></span>													
												</li>
												<li id="phoneAfter">													
													<span class="phone">
														<div class="select-style01 d_select phone0">
															<button type="button" class="d_select_sel" style="width:151px;" id="btnMobileCo"><span>통신사 선택</span></button>
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
														<input type="hidden" name="mobileNumber" id="mobileNumber"/>														
														<input type="text" class="input-style01 phone1" style="width:80px;" name="phone1" id="phone1" maxlength="3" validate='required' validateText="휴대전화번호">
														<em class="dash">-</em>
														<input type="text" class="input-style01 phone2" style="width:80px;" name="phone2" id="phone2" maxlength="4" validate='required' validateText="휴대전화번호">
														<em class="dash">-</em>
														<input type="text" class="input-style01 phone3" style="width:80px;" name="phone3" id="phone3" maxlength="4" validate='required' validateText="휴대전화번호">
													</span>
													<span class="error-msg"></span>
												</li>
											</ul>
										</td>
									</tr>
									<tr id="certProcess">
										<th scope="row"><label>본인인증</label> <span class="required">*</span></th>
										<td>
											<div class="d_toggle togbox">
												<div class="hbox">
													<span class="required">*</span>
													<span class="check-skin">
														<input type="checkbox" id="certAgreeYn" name="certAgreeYn" value="Y" validate='checkval' onclick="setAgree();"><span>선택</span>
													</span>
													<label for="certAgreeYn">본인 인증을 위한 약관 모두 동의</label>
													<a href="#" class="btn fill sm btnPslCertifi" onclick="goCertification(this); return false;">본인인증</a>
													<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
													<span class="error-msg"></span>
												</div>												

												<div class="cbox d_toggle_cont">
													<ul class="list">
														<li>
															<span class="required">*</span>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_1" onclick="setAgree2();"><span>선택</span>
															</span>
															<label for="chk_policy_1">개인정보이용동의</label>
															<a href="#" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin1'); return false;">전문보기</a>
														</li>
														<li>
															<span class="required">*</span>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_2" onclick="setAgree2();"><span>선택</span>
															</span>
															<label for="chk_policy_2">고유식별정보처리 동의</label>
															<a href="#" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin2'); return false;">전문보기</a>
														</li>
														<li>
															<span class="required">*</span>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_3" onclick="setAgree2();"><span>선택</span>
															</span>
															<label for="chk_policy_3">서비스이용약관동의</label>
															<a href="#" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin3'); return false;">전문보기</a>
														</li>
														<li>
															<span class="required">*</span>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_4" onclick="setAgree2();"><span>선택</span>
															</span>
															<label for="chk_policy_4">통신사이용약관 동의</label>
															<a href="#" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin4'); return false;">전문보기</a>
														</li>
														<li style="display:none" id="mCoview">
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_5"><span>선택</span>
															</span>
															<label for="chk_policy_5">개인정보  제3자 제공동의</label>
															<a href="#" class="btn gray sm btnAllView" onclick="layerPopup.popupOpenNow('#popJoin5'); return false;">전문보기</a>
														</li>
													</ul>
												</div>
												
												<!-- 본인 인증번호 입력  -->
												<div class="certifiBox" id="certAfter" style="display:none">
													<span class="required">*</span>
													<div class="num">
														<input type="text" class="input-style01" placeholder="인증번호" name="sAuthNo" id="sAuthNo" validateText="인증번호" maxlength="6">
														<span class="time" id="remainTime">3분</span>
													</div>
													<div class="bts">
														<a href="#" class="btn btnConfirm" onclick="goCertificationEnd(); return false;">확인</a>
														<a href="#" class="btn fill btnResend" onclick="goCertification(this); return false;">재전송</a>
													</div>
													<ul class="text-list02 col-type01 bul-list">
														<li>3분 이내로 인증번호(6자리)를 입력해 주세요.</li>
														<!-- <li>인증번호가 오지 않으면 <a href="javascript:;" class="link">여기를 눌러주세요.</a></li> -->
													</ul>
													<span class="error-msg"></span>
												</div>
												
											</div>
										</td>
									</tr>
									<tr  id="certProcessEnd" style="display:none">
										<th scope="row"><label>본인인증</label> <span class="required">*</span></th>
										<td>
											<div class="togbox">
												<span class="btn fill sm btnPslCertifi">본인인증 완료</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</section>

					<!-- 마켓팅수신 -->
					<section class="sect receive">
						<div class="d_toggle togbox" id="termToggle">
							<div class="hbox">
								<span class="check-skin">
									<input type="checkbox" id="chk_receive_1" onclick="setCheck()"><span>선택</span>
								</span>
								<label for="chk_receive_1">MLB 이용약관 및 마케팅정보 수신 모두 동의</label>
								<a href="#" class="btn gray sm btnAllView" onclick="joinTermView(); return false;">전문보기</a>
								<button type="button" class="btn-open d_toggle_select" id="termBtn"><span>Open</span></button>
							</div>
							<div class="cbox d_toggle_cont">
								<ul class="list">
									<li class="L1">
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" name="onlineSiteUsefStplat" id="onlineSiteUsefStplat" value="Y" onclick="setCheck2()"><span>선택</span>												
											</span>
											<label for="chk_pol_receive_1">서비스 이용약관(필수)</label>
										</span>
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" name="psnlInfoColctUsefAgr" id="psnlInfoColctUsefAgr" value="Y" onclick="setCheck2()"><span>선택</span>												
											</span>
											<label for="chk_pol_receive_2">개인정보 수집 및 이용 (필수)</label>		
										</span>
									</li>
									<li class="L2">
										<span class="item">											
											<span class="check-skin">
												<input type="checkbox" name="emailRecptnAgrYn" id="emailRecptnAgrYn" value="Y" onclick="setCheck2()"><span>선택</span>
											</span>
											<label for="chk_pol_receive_3">e-Mail 수신</label>
										</span>
										<span class="item">											
											<span class="check-skin">
												<input type="checkbox" name="smsRecptnAgrYn" id="smsRecptnAgrYn" value="Y" onclick="setCheck2()"><span>선택</span>
											</span>
											<label for="chk_pol_receive_4">SMS(알림톡) 수신</label>
										</span>
										<ul class="text-list02 col-type01 bul-list">
											<li>e-Mail, SMS(알림톡)을 통한 상품 및 이벤트 정보 수신에 동의합니다.</li>
											<li>거래정보(주문/반품/교환) 관련 e-Mail/SMS(알림톡)은 수신동의 하지 않아도 발송됩니다.</li>
										</ul>
									</li>															
								</ul>
								<div class="gud">
									<ul class="text-list02 col-type01 bul-list">
										<li>상기 부가정보 수신 동의는 F&F 브랜드에서 회원에게 이메일, SMS를 활용하여 상품 및 서비스에 대한 제반 마케팅 활동을 하기 위한 회원정보   (개인정보취급 방침 중 “2. 수집하는 개인정보 항목” 기재 항목)제공 및 각 서비스 수신에 대한 사항입니다.</li>
										<li>약관변경이나 공지 등의 고지성 안내 메일은 수신동의 하지않더라도 고객의 권익보호를 위해 발송됩니다.</li>
										<li>제공된 회원정보는 F&F 브랜드가 별도 동의를 득한 경우를 제외하고는 회원탈퇴 후 30일까지 보유합니다.</li>
									</ul>
								</div>
							</div>
						</div>

					</section>
					
					<!-- 부가정보 -->
					<section class="sect addition">
						<div class="hdt"><span class="tit">부가정보입력(선택)</span></div>
						<div class="board-write adds">
							<table>
								<colgroup>
									<col width="200">
									<col>
								</colgroup>
								<tbody>
									<tr>
										<th scope="row"><label>자녀정보</label></th>
										<td>
											<a href="#" class="btnChildAdd" onclick="addChildView(this); return false;">자녀정보 추가</a>
											<ul class="list">
												<li name="additionalChild">
													<div class="child-birth">
														<input type="text" class="input-style01 name" placeholder="이름" style="width:100px;" id="childrenName1" name="childrenName" maxlength="20">
														<input type="hidden" name="childrenBirthDate" />
														<span class="birth">
															<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)" id="childrenBirthYear1" name="childrenBirthYear" maxlength="4">
															<input type="hidden" id="childrenBirthMonth1" name="childrenBirthMonth">
															<div class="select-style01 d_select mm">
																<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
																<ul>
																	<li><a href="#" onclick="setChildBirthMonth('01', this)">1</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('02', this)">2</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('03', this)">3</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('04', this)">4</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('05', this)">5</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('06', this)">6</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('07', this)">7</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('08', this)">8</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('09', this)">9</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('10', this)">10</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('11', this)">11</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('12', this)">12</a></li>
																</ul>
															</div>
															<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일" id="childrenBirthDay1" name="childrenBirthDay" maxlength="2">
														</span>
													</div>
												</li>
												<li id="addChild1" style="display:none" name="additionalChild">
													<div class="child-birth">
														<input type="text" class="input-style01 name" placeholder="이름" style="width:100px;" id="childrenName2" name="childrenName" maxlength="20">
														<input type="hidden" name="childrenBirthDate" />
														<span class="birth">
															<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)" id="childrenBirthYear2" name="childrenBirthYear" maxlength="4">
															<input type="hidden" id="childrenBirthMonth2" name="childrenBirthMonth">
															<div class="select-style01 d_select mm">
																<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
																<ul>
																	<li><a href="#" onclick="setChildBirthMonth('01', this)">1</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('02', this)">2</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('03', this)">3</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('04', this)">4</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('05', this)">5</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('06', this)">6</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('07', this)">7</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('08', this)">8</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('09', this)">9</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('10', this)">10</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('11', this)">11</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('12', this)">12</a></li>
																</ul>
															</div>
															<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일" id="childrenBirthDay2" name="childrenBirthDay" maxlength="2">
														</span>
													</div>
												</li>
												<li id="addChild2" style="display:none" name="additionalChild">
													<div class="child-birth">
														<input type="text" class="input-style01 name" placeholder="이름" style="width:100px;" id="childrenName3" name="childrenName" maxlength="20">
														<input type="hidden" name="childrenBirthDate" />
														<span class="birth">
															<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)" id="childrenBirthYear3" name="childrenBirthYear" maxlength="4">
															<input type="hidden" id="childrenBirthMonth3" name="childrenBirthMonth">
															<div class="select-style01 d_select mm">
																<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
																<ul>
																	<li><a href="#" onclick="setChildBirthMonth('01', this)">1</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('02', this)">2</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('03', this)">3</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('04', this)">4</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('05', this)">5</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('06', this)">6</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('07', this)">7</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('08', this)">8</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('09', this)">9</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('10', this)">10</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('11', this)">11</a></li>
																	<li><a href="#" onclick="setChildBirthMonth('12', this)">12</a></li>
																</ul>
															</div>
															<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일" id="childrenBirthDay3" name="childrenBirthDay" maxlength="2">
														</span>
													</div>
												</li>												
											</ul>
											<span class="error-msg"></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<ul class="text-list02 col-type01 bul-list">
							<li>자녀가 있으신 경우 기입해 주시면 자녀생일쿠폰을 드립니다.</li>
							<li>자녀는3명까지 입력 가능하며, 입력하신 정보는 수정 및 삭제가 불가능합니다.</li>
						</ul>
					</section>
					</form:form>

					<div class="bot-btns btnList">
						<a href="#" class="btn lg fill btnJoin" id="confirmBtn" onClick="return false;">회원가입</a>
					</div>
					
					
					<section class="sect bot-info">
						<div class="hd">F&F membership은 회원님의 소중한 개인정보를 안전하게 관리하고 있습니다.</div>
		  				<ul class="text-list02 col-type01 bul-list">
							<li>
								2017년 8월 18일 부터 시행되는 정보통신망 이용 촉진 및 정보보호 등에 관한 법률(이하 정보통신망법) 제 23조의 2 ”주민등록번호의 사용 
  								제한”에 따라 모든 서비스에서 주민등록번호를 입력 받지 않습니다. 이에 따라 가입하실 때는 주민등록번호를 입력하는 실명인증 휴대폰을 
  								이용한 본인인증이 필요합니다.
  							</li>
						</ul>
					</section>


				</div>
				
				
			</main>
			
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/views/member/include/certification.layer.popup.jsp"  flush="false" />	
	<jsp:include page="/WEB-INF/views/member/include/join.layer.popup.jsp"  flush="false" />	
<%-- 광고영역 --%>
<!-- WIDERPLANET  SCRIPT START 2018.8.9 -->
<script type="text/javascript">
var wptg_tagscript_vars = wptg_tagscript_vars || [];
wptg_tagscript_vars.push(
(function() {
    return {
        wp_hcuid:"",
        ti:"39428",
        ty:"Join",
        device:"web",
        items:[{
            i:"회원 가입",
            t:"회원 가입",
            p:"1",
            q:"1"
        }]
    };
}));
fnf_appendTargetGateScript();
</script>
<!-- // WIDERPLANET  SCRIPT END 2018.8.9 -->
