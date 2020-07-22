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
	<div class="contain mb join" id="contain">

		
		<div class="container">


			<div class="location-container">
                <div class="location-contents">
                    <h2 class="title01">회원가입</h2>
                    <p class="location">
                    <span><a href="/static/html/mn/main.jsp">Home</a></span><strong title="현재 위치">회원가입</strong>
                    </p>
                 </div>
            </div>
			<main class="contents" id="contents">

				<div class="join-wrap">
					
					<div class="top-info">
						<div class="hdt">F&amp;F 통합회원 가입</div>
						<p class="ss">MLB의 회원이 되시면 동일한 ID와 비밀번호로 <br>F&F 패밀리 브랜드 웹사이트의 다양한 서비스를 안전하고 편리하게 이용하실 수 있습니다.</p>
						<p class="tt"><strong class="t">F&amp;F Family Brand</strong>       MLB, Discovery Expedition, COLLECTED, JARDIN PERDU, Stretch Angels</p>
					</div>

					<!-- 혜택정보 -->
					<%@ include file="../mb/inc_benefit.jsp" %>
					
					<!-- 가입정보 -->
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
													
													<input type="text" class="input-style01 id" style="width:260px;" placeholder="아이디 (6~15자 영문 소문자.숫자)">
													<span class="error-msg" style="display:block;">6~15자 영문 소문자, 숫자만 사용 가능하며 특수문자는 사용할 수 없습니다. </span>
												</li>
												<li>
													<input type="password" class="input-style01 pw" style="width:100%;" placeholder="비밀번호 ( 8~12자 영문, 숫자, 특수문자 중 최소 2가지 조합)">
													<span class="error-msg" style="display:block;">영문, 숫자, 특수문자(!,@#$%^&*)를 두 가지 이상 혼용하여 8~12자 이내로 입력해야 합니다. </span>
												</li>
												<li>
													<input type="password" class="input-style01 pw com" style="width:100%;" placeholder="비밀번호 확인">
												</li>
												<li>
													<input type="text" class="input-style01 name" style="width:260px;" placeholder="이름(실명입력)">
													
													<span class="rdo-skin">
														<input type="radio" name="rdoNameNation" id="rdoNameNation_1" checked="checked"><span>선택</span>
													</span>
													<label for="rdoNameNation_1">내국인</label>
													
													<span class="rdo-skin">
														<input type="radio" name="rdoNameNation" id="rdoNameNation_2"><span>선택</span>
													</span>
													<label for="rdoNameNation_2">외국인</label>
												</li>
												<li>
													<span class="birth">
														<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)">
														<div class="select-style01 d_select mm">
															<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
															<ul>
																<li><a href="javascript:;">1</a></li>
																<li><a href="javascript:;">2</a></li>
															</ul>
														</div>
														<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일">
													</span>
													<span class="rdo-skin">
														<input type="radio" name="rdoGender" id="rdoGender_1" checked="checked"><span>선택</span>
													</span>
													<label for="rdoGender_1">남자</label>
													<span class="rdo-skin">
														<input type="radio" name="rdoGender" id="rdoGender_2"><span>선택</span>
													</span>
													<label for="rdoGender_2">여자</label>
												</li>
												<li>
													<span class="email">
														<input type="text" class="input-style01 email1" style="width:130px;" placeholder="E-mail">
														<em class="att">@</em>
														<input type="text" class="input-style01 email2" style="width:130px;" placeholder="">
														<div class="select-style01 d_select email3">
															<button type="button" class="d_select_sel" style="width:151px;"><span>직접입력</span></button>
															<ul>
																<li><a href="javascript:;">naver.com</a></li>
																<li><a href="javascript:;">daum.net</a></li>
																<li><a href="javascript:;">nate.net</a></li>
																<li><a href="javascript:;">gmail.net</a></li>
																<li><a href="javascript:;">hotmail.net</a></li>
															</ul>
														</div>
													</span>
												</li>
												<li>
													<span class="phone">
														<div class="select-style01 d_select phone0">
															<button type="button" class="d_select_sel" style="width:151px;"><span>통신사 선택</span></button>
															<ul>
																<li><a href="javascript:;">통신사 선택</a></li>
																<li><a href="javascript:;">SKT</a></li>
																<li><a href="javascript:;">KT</a></li>
																<li><a href="javascript:;">LG U+</a></li>
																<li><a href="javascript:;">SKT 알뜰폰</a></li>
																<li><a href="javascript:;">KT 알뜰폰</a></li>
																<li><a href="javascript:;">LG U+ 알뜰폰</a></li>
															</ul>
														</div>
														<input type="text" class="input-style01 phone1" style="width:80px;">
														<em class="dash">-</em>
														<input type="text" class="input-style01 phone2" style="width:80px;">
														<em class="dash">-</em>
														<input type="text" class="input-style01 phone3" style="width:80px;">
													</span>
												</li>
											</ul>
										</td>
									</tr>
									<tr>
										<th scope="row"><label>본인인증</label> <span class="required">*</span></th>
										<td>
											<div class="d_toggle togbox">
												<div class="hbox">
													<span class="check-skin">
														<input type="checkbox" id="chk_psl_certifi_1"><span>선택</span>
													</span>
													<label for="chk_psl_certifi_1">본인 인증을 위한 약관 모두 동의</label>
													<a href="javascript:;" class="btn fill sm btnPslCertifi">본인인증</a>
													<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>

												</div>

												<div class="cbox d_toggle_cont">
													<ul class="list">
														<li>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_1"><span>선택</span>
															</span>
															<label for="chk_policy_1">개인정보이용동의</label>
															<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
														</li>
														<li>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_2"><span>선택</span>
															</span>
															<label for="chk_policy_2">고유식별정보처리 동의</label>
															<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
														</li>
														<li>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_3"><span>선택</span>
															</span>
															<label for="chk_policy_3">서비스이용약관동의</label>
															<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
														</li>
														<li>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_4"><span>선택</span>
															</span>
															<label for="chk_policy_4">통신사이용약관 동의</label>
															<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
														</li>
														<li>
															<span class="check-skin">
																<input type="checkbox" id="chk_policy_5"><span>선택</span>
															</span>
															<label for="chk_policy_5">개인정보  제3자 제공동의</label>
															<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
														</li>
													</ul>
												</div>


												<!-- 본인 인증번호 입력  -->
												<div class="certifiBox">
													<div class="num">
														<input type="text" class="input-style01" placeholder="인증번호">
														<span class="time">2분 50초</span>
													</div>
													<div class="bts">
														<a href="javascript:;" class="btn btnConfirm">확인</a>
														<a href="javascript:;" class="btn fill btnResend">재전송</a>
													</div>
													<ul class="text-list02 col-type01 bul-list">
														<li>3분 이내로 인증번호(6자리)를 입력해 주세요.</li>
														<li>인증번호가 오지 않으면 <a href="javascript:;" class="link">여기를 눌러주세요.</a></li>
													</ul>
												</div>
												
											</div>
										</td>
									</tr>
									<tr>
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
						<div class="d_toggle togbox">
							<div class="hbox">
								<span class="check-skin">
									<input type="checkbox" id="chk_receive_1"><span>선택</span>
								</span>
								<label for="chk_receive_1">MLB 이용약관 및 마케팅정보 수신 모두 동의 (필수/선택포함)</label>
								<a href="/static/html/et/popTermsAgree.jsp" class="btn gray sm btnAllView">전문보기</a>
								<button type="button" class="btn-open d_toggle_select"><span>Open</span></button>
							</div>
							<div class="cbox d_toggle_cont">
								<ul class="list">
									<li class="L1">
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" id="chk_pol_receive_1"><span>선택</span>
											</span>
											<label for="chk_pol_receive_1">서비스 이용약관(필수)</label>
										</span>
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" id="chk_pol_receive_2"><span>선택</span>
											</span>
											<label for="chk_pol_receive_2">개인정보 수집 및 이용 (필수) </label>
										</span>
									</li>
									<li class="L2">
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" id="chk_pol_receive_3"><span>선택</span>
											</span>
											<label for="chk_pol_receive_3">e-Mail 수신</label>
										</span>
										<span class="item">
											<span class="required">*</span>
											<span class="check-skin">
												<input type="checkbox" id="chk_pol_receive_4"><span>선택</span>
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
											<a href="javascript:;" class="btnChildAdd">자녀정보 추가</a>
											<ul class="list">
												<li>
													<div class="child-birth">
														<input type="text" class="input-style01 name" placeholder="이름" style="width:100px;">
														<span class="birth">
															<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)">
															<div class="select-style01 d_select mm">
																<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
																<ul>
																	<li><a href="javascript:;">1</a></li>
																	<li><a href="javascript:;">12</a></li>
																</ul>
															</div>
															<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일">
														</span>
													</div>
												</li>
												<li>
													<div class="child-birth">
														<input type="text" class="input-style01" placeholder="이름" style="width:100px;">
														<span class="birth">
															<input type="text" class="input-style01 year" style="width:80px;" placeholder="생년(4자)">
															<div class="select-style01 d_select mm">
																<button type="button" class="d_select_sel" style="width:80px;"><span>월</span></button>
																<ul>
																	<li><a href="javascript:;">1</a></li>
																	<li><a href="javascript:;">12</a></li>
																</ul>
															</div>
															<input type="text" class="input-style01 dd" style="width:80px;" placeholder="일">
														</span>
													</div>
												</li>
											</ul>
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


					<div class="bot-btns btnList">
						<a href="/static/html/mb/join_com.jsp" class="btn lg fill btnJoin">회원가입</a>
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
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>


</body>
</html>