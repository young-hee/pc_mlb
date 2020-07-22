<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01">회원정보 수정</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents memberInfoModi-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>회원정보</span><strong title="현재 위치">회원정보 수정</strong>
					</p>
				</div> 
				
				<p class="text-required"><span class="required">*</span> 필수입력 항목</p>
				
				<!-- 1905 번호인증 추가 | .board-write에 'forms' class 추가 -->
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
								<p>asdf</p>
								<!-- 2018.12.12 제거 <a href="javascript:;" class="btn gray sm">비밀번호 변경</a>-->
							</td>
						</tr>
						<!-- 2018.12.12추가 S-->
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="">비밀번호</label></th>
							<td>
								<p>
									<input type="text" class="input-style01" style="width:550px;" placeholder="새 비밀번호 ( 8~12자 영문, 숫자, 특수문자 중 최소 2가지 조합)" />
									<span class="error-msg">비밀번호를 입력해 주세요.</span>
								</p>
								<p>
									<input type="text" class="input-style01" style="width:550px;" placeholder="새 비밀번호 확인" />
									<span class="error-msg">비밀번호가 일치하지 않습니다.</span>
								</p>
							</td>
						</tr>
						<!-- 20118.12.12추가E -->
						<tr>
							<th scope="row"><label for="">이름</label></th>
							<td>
								<p>홍길동</p>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="">생일</label></th>
							<td>
								<p>1980년 08월 05일</p>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteEmail">이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteEmail" class="input-style01" value="abcd" style="width:110px;">
								<span class="at">@</span>
								<input type="text" class="input-style01" style="width:110px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:150px;"><span>직접입력</span></button>
									<ul>
										<li><a href="javascript:;">직접입력</a></li>
										<li><a href="javascript:;">naver.com</a></li>
										<li><a href="javascript:;">daum.net</a></li>
										<li><a href="javascript:;">nate.com</a></li>
										<li><a href="javascript:;">gmail.com</a></li>
										<li><a href="javascript:;">hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:block;">유효한 이메일주소를 입력해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall">휴대전화</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall" class="input-style01" value="010" style="width:64px;" disabled />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="1234" style="width:70px;" disabled />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="5678" style="width:70px;" disabled />
									<!-- 1905 번호인증 추가 : 인증하기 버튼 S -->
									<a class="btn-style04" href="javascript:;" onclick="layerPopup.popupOpenNow('#popCertification')">인증하기</a>
									<!-- 1905 번호인증 추가 : 인증하기 버튼 E -->
								</div>
								<span class="error-msg" style="display:block;">잘못된 휴대전화번호입니다.</span>
								<!-- 1905 번호인증 추가 : 멘트 추가 S -->
								<p class="dsc">휴대전화번호를 변경하시려면 <strong>인증하기</strong>를 눌러 변경해 주세요.</p>
								<!-- 1905 번호인증 추가 : 멘트 추가 E -->
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="boardWriteaddr">주소</label></th>
							<td>
								<input type="text" id="boardWriteaddr" class="input-style01" style="width:240px;">
								<a href="/static/html/my/addressFind_pop.jsp" class="btn-style04">주소찾기</a>
								<input type="text" class="input-style01 mtST10" style="width:550px;">
								<input type="text" class="input-style01 mtST10" style="width:550px;">
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="boardWritechild">자녀정보</label></th>
							<td>
								<!-- 2018.12.12수정추가 S-->
								<ul>
									<li><span><i>홍하나</i><i>2012년 05월 05일</i></span></li>
									<li><span><i>홍하나</i><i>2012년 05월 05일</i></span></li>
								</ul>
								<div class="childPlusWrap">
									<a href="javascript:;" class="btn fill">추가</a>
									<ul class="childPlusBox">
										<li>
											<div class="inputAllST">
												<input type="text" id="boardWritechild" class="input-style01" title="이름" placeholder="이름" style="width:135px;">
												<input type="number" id="" class="input-style01" title="생년" placeholder="생년(4자)" style="width:80px;">
												<!-- select -->
												<div class="select-style01 d_select">
													<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
													<ul>
														<li><a href="javascript:;">1월</a></li>
														<li><a href="javascript:;">2월</a></li>
														<li><a href="javascript:;">3월</a></li>
														<li><a href="javascript:;">4월</a></li>
														<li><a href="javascript:;">5월</a></li>
														<li><a href="javascript:;">6월</a></li>
														<li><a href="javascript:;">7월</a></li>
														<li><a href="javascript:;">8월</a></li>
														<li><a href="javascript:;">9월</a></li>
														<li><a href="javascript:;">10월</a></li>
														<li><a href="javascript:;">11월</a></li>
														<li><a href="javascript:;">12월</a></li>
													</ul>
												</div>
												<!-- //select -->
												<input type="number" id="" class="input-style01" title="일" placeholder="일" style="width:80px;">
											</div>
										</li>
									</ul>
								</div>
								<!-- 20118.12.12수정추가E -->
							</td>
						</tr>
						<tr>
							<th scope="row" class="tbSTvtc-top"><label for="">e-Mail, SMS(알림톡)</label></th>
							<td>
								<div class="chkST-mg">
									<span class="check-skin">
										<input type="checkbox" id="chkReply" checked="checked">
										<span>e-Mail 수신</span>
									</span>
									<label for="chkReply">e-Mail 수신</label>
									<span class="check-skin">
										<input type="checkbox" id="chkReply01">
										<span>SMS(알림톡) 수신</span>
									</span>
									<label for="chkReply01">SMS(알림톡) 수신</label>
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
					<a href="javascript:;" class="btn">취소</a>
					<a href="javascript:;" class="btn fill">확인</a>
				</div>
				
			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->


	<!-- 1905 번호인증 추가 | 인증 레이어팝업 : S -->
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
									<input type="hidden" name="mobileNumber" id="mobileNumber"/>														
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
	<!-- 1905 번호인증 추가 | 인증 레이어팝업 : E -->


	<%@ include file="../_inc/footer.jsp" %>
</div>

<!-- 1905 번호인증 추가 | 인증 약관 레이어팝업 : S -->
<jsp:include page="/WEB-INF/views/member/include/certification.layer.popup.jsp"  flush="false" />	
<jsp:include page="/WEB-INF/views/member/include/join.layer.popup.jsp"  flush="false" />	
<!-- 1905 번호인증 추가 | 인증 약관 레이어팝업 : E -->

<%@ include file="../_inc/bottom.jsp" %>
</body>
</html>