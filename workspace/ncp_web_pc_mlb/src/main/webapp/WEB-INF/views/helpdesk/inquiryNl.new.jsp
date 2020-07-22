<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

<script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js"></script>
<script type="text/javascript" src="/javascript/message/helpdesk_${pageContext.response.locale.language}.js?v=${_version}"></script>
<form name ="gForm" id ="gForm" action ="<c:url value ='/helpdesk/csInquiry/add'/>" method ="post" >
	<input type ="hidden" name ="csoMtmInq.cstmrEmail" id="cstmrEmail" value ="" />
	<input type="hidden"  name="csoMtmInq.inqTpCd" id="inqTpCd" />
	<input type="hidden"  name="csoMtmInq.inqCont" id="inqContent" />
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileNm" id="inputfileNm" />
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileUrl" id="inputfileUrl"/>
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileCpcty" id="inputfileSize" />
	<input type="hidden" name="referer" value="${header.referer}" />
	<input type="hidden" name="mbrNm" id="mbrNm" />

	<div class="contain my cs lnblist-Wrap" id="contain">
		<div class="container">
			<h2 class="title01"><spring:message code="helpdesk.common.inquiry.ttl" text="1:1 문의" /></h2>

			<main class="contents oto_inquiryWrite-wrap" id="contents">
				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span><spring:message code='helpdesk.common.ttl' text="고객센터" /></span>
						<strong title="현재 위치"><spring:message code='helpdesk.common.inquiry.ttl' text="1:1 문의" /></strong>
					</p>
				</div>

				<div class="board-write">
					<table summary="1:1고객상담 입력">
						<caption><spring:message code='helpdesk.common.inquiry.ttl' text="1:1 문의" /></caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName"><spring:message code='helpdesk.inquiry.writername.txt' text="작성자" /></label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" class="input-style01" name="csoMtmInq.inqCstmrNm" style="width:150px;" />
								<span class="error-msg" id="boardWriteName-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteEmail"><spring:message code='helpdesk.inquiry.email.txt' text="답변 이메일"/></label> <span class="required">*</span></th>
							<td id="emailBox">
								<input type="text" id="boardWriteEmail" class="input-style01" style="width:110px;">
								<span class="at">@</span>
								<input type="text" id="boardWriteEmail2" class="input-style01" style="width:110px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:150px;" id="emailSelect"><span><spring:message code="helpdesk.common.email.btn.personally.txt" text="직접입력" /></span></button>
									<ul id='emailAddress'>
										<li><a href=""><spring:message code="helpdesk.common.email.btn.personally.txt" text="직접입력" /></a></li>
										<li><a href="#">naver.com</a></li>
										<li><a href="#">daum.net</a></li>
										<li><a href="#">nate.com</a></li>
										<li><a href="#">gmail.com</a></li>
										<li><a href="#">hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:none;" id ="boardWriteEmail-msg"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact"><spring:message code="helpdesk.inquiry.contact.txt" text="휴대전화" /></label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" class="input-style01" id="boardWriteContact" name="csoMtmInq.cstmrmobilAreaNo" value="${mobile1 }" style="width:64px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" id="boardWriteContact2" name="csoMtmInq.cstmrmobilTlofNo" value="${mobile2 }" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" id="boardWriteContact3" name="csoMtmInq.cstmrmobilTlofWthnNo" value="${mobile3 }" style="width:70px;" />
								</div>

								<span class="check-skin">
									<input type="checkbox" id="chkAgree" checked="checked" value="Y" name="csoMtmInq.cstmrSmsRecptnYn">
									<span><spring:message code="helpdesk.common.inqcd.btn.defualt.txt" text="선택" /></span>
								</span>
								<label for="chkAgree"><spring:message code="helpdesk.common.answeragree.txt" text="답변등록여부 수신" /></label>
								<span class="error-msg" id="boardWriteContact-msg" style="display:none;"></span>
							</td>
						</tr>
					<c:choose>
						<c:when test="${!empty erpGodNo}">
						<th scope="row"><spring:message code="helpdesk.inquiry.godnum.txt" /></th>
						<td>
							<p class="board-write-text"><c:out value ="${erpGodNo}" /></p>
							<input type='hidden' value="${erpGodNo}" name="csoMtmInq.erpGodNo"/>
							<input type='hidden' value="${godNo}" name="csoMtmInq.godNo"/>
						</td>
						</c:when>
						<c:otherwise>
						<c:if test = '${!empty ordNo}'>
							<tr>
								<th scope="row"><label for="ordNum2"><spring:message code="helpdesk.inquiry.ordnum.txt" text="주문번호" /></label></th>
								<td>
								${ordNo}
								<input type="hidden"  id="ordNumber" name="ordGod.ordNo" value="${ordNo}">
								</td>
							</tr>
						</c:if>
						</c:otherwise>
					</c:choose>
						<tr>
							<th scope="row"><label for="boardWriteSort"><spring:message code="helpdesk.inquiry.inquirycd.txt" text="상담분류" /></label> <span class="required">*</span></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select select_consul_cate">
									<button type="button" id="boardWriteSort" class="d_select_sel" style="width:152px;"><span><spring:message code="helpdesk.common.inqcd.btn.defualt.txt" text="선택" /></span></button>

									<ul>
									<c:choose>
										<c:when test="${pageContext.response.locale.language eq 'ko'}">
											<ncp:codes group ="INQ_TP" var ="inqTp" />
										</c:when>
										<c:otherwise>
											<ncp:codes group ="INQ_TP" var ="inqTp" includeNotUse="false"/>
										</c:otherwise>
									</c:choose>
									<c:forEach var ="cd" items ="${inqTp}">
									<if test = '${cd.cd ne "OUTBND" or cd.cd ne "OUTBND2"}'>
										<li class="selectBox"><a href="#none" onclick="return false;" id="${cd.cd}" class="selCd"><c:out value ="${cd.cdNm}" /><input type="hidden" value="${cd.cd}"/></a></li>
									</if>
									</c:forEach>
									</ul>
								</div>
							<!-- //select -->
							<span class="error-msg" id="boardWriteSort-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteTitle"><spring:message code="helpdesk.inquiry.subject.txt" text="제목"/></label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteTitle" class="input-style01" name='csoMtmInq.inqSj' placeholder="<spring:message code='helpdesk.inquiry.subject.placeholder.txt' text='30자 미만으로 입력해 주세요.' />" style="width:515px;">
								<span class="error-msg" id="boardWriteTitle-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent"><spring:message code="helpdesk.inquiry.content.txt" text="내용"/></label> <span class="required">*</span></th>
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="<spring:message code='helpdesk.inquiry.content.placeholder.txt' text='1,000자 미만 (특수문자 \ / : < > ; 사용불가)으로 입력해 주세요.' />" style="width:515px; height:150px;"></textarea>
								<div class="clearfix">
									<div class="fl">
										<span class="error-msg" id="boardWriteContent-msg" style="display:none;"></span>	<!-- 더 이상 입력하실 수 없습니다. -->
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000" id="counter">0</em>/1,000</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="fileSearch"><spring:message code="helpdesk.inquiry.file.txt" text="첨부파일" /></label></th>
							<td>
								<span class="file-search">
									<input type="text" class="input-style01" style="width:443px;" id="fileInputName" readonly>
									<span>
										<input type="file" id="fileSearch" name="files" onChange="javascript:filetest(this);">
										<label for="fileSearch"><spring:message code="helpdesk.common.filefind.btn" text="파일찾기" /></label>
									</span>
								</span>
								<div class="file-name"></div>
								<ul class="text-list02 col-type02">
									<li><spring:message code="helpdesk.common.file.condition.txt1" text="파일은 1개만 첨부할 수 있습니다. (10MB 이하)" /></li>
									<li><spring:message code="helpdesk.common.file.condition.txt2" text="파일형식 : hwp, doc/docx, xls/xlsx, ppt/pptx, jpg, gif, png, pdf" /></li>
								</ul>
							</td>
						</tr>
					</table>
				</div>

				<ul class="text-list02 col-type01">
					<li><spring:message code="helpdesk.inquiry.noneLogin.txt1" text="비회원으로 문의하신 1:1 고객상담 내용은 입력하신 [답변 이메일 주소]로 알려드리겠습니다." /></li>
				<c:if test='${_locale eq "ko" }'>
					<li><spring:message code="helpdesk.inquiry.noneLogin.txt2" text="회원으로 로그인 후, 1:1 문의를 남겨 주시면 <span class='txt13-000'>마이페이지 > 활동정보 > 1:1 문의</span>에서 확인하실 수 있습니다." /></li>
				</c:if>
				</ul>
				<div class="agreement-Wraper">
					<h3 class="title02"><spring:message code="helpdesk.inquiry.noneLogin.agreementTerms.txt" text="개인정보 수집 이용에 대한 동의" /></h3>

						<%@ include file="/WEB-INF/views/helpdesk/include/stplatCont.view.jsp" %>

					<div class="txtFR-txt">
                        <span class="rdo-skin">
                            <input type="radio" id="rdoType01" name="rdoAgree">
                            <span><spring:message code="helpdesk.common.inqcd.btn.defualt.txt" text="선택" /></span>
                        </span>
                        <label for="rdoType01"><spring:message code="helpdesk.noneLogin.agreementTerms.aree.btn" text="동의합니다." /></label>

                        <span class="rdo-skin">
                            <input type="radio" id="rdoType02" name="rdoAgree" checked="checked">
                            <span><spring:message code="helpdesk.common.inqcd.btn.defualt.txt" text="선택" /></span>
                        </span>
						<label for="rdoType02"><spring:message code="helpdesk.noneLogin.agreementTerms.disAree.btn" text="동의하지 않습니다." /></label>
					</div>
				</div>
			 	<div class="btnWrapBox">
					<a href="#" id="cancleBtn" class="btn btn-style03"><spring:message code="helpdesk.common.cancle.btn" text="취소" /></a>
					<a href="#" id="regBtn" class="btn fill btn-style02"><spring:message code="helpdesk.common.reg.btn" text="저장" /></a>
				</div>
			</main>
		</div>
	</div>
</form>

	<!-- layerpopup -->
	<article id="layerPopup02" class="layer-popup">
		<section  class="layer-popup-cont"  tabindex="0">
			<h2><spring:message code="helpdesk.popup.selectord.ttl" text="주문번호조회" /></h2>
			<div class="layer-popup-wrap01">
				<dl class="period-wrap">
					<!-- 기간달력 -->
					<dt><spring:message code="helpdesk.popup.selectord.selectterm.txt" text="조회기간" /></dt>
					<dd>
						<script>
						$(document).ready(function(){
							var dateFormat = "yyyy-mm-dd",
							from = $( "#calendarFrom" ).datepicker().on( "change", function() {
								to.datepicker( "option", "minDate", getDate( this ) );
							}),
							to = $( "#calendarTo" ).datepicker().on( "change", function() {
								from.datepicker( "option", "maxDate", getDate( this ) );
							});

							function getDate( element ) {
								var date;
								try {
									date = $.datepicker.parseDate( dateFormat, element.value );
								} catch( error ) {
									date = null;
								}
								return date;
							}
						});
						</script>
					 	<input type="text" class="calendar" id="calendarFrom" value="" readonly>
						<input type="text" class="calendar" id="calendarTo" value="" readonly>
						<a href="#" class="btn-style05" id="btnlistsearch"><spring:message code="helpdesk.common.button.search.btn" text="검색" /></a>
					</dd>
				</dl>
				<span class="" id="period-error1"style="display:block;"><spring:message code="helpdesk.inquiry.alert.txt1" text="2019년 2월 20일 이후 주문만 조회 가능합니다." /></span>
				<span class="" id="period-error2"style="display:block;"><spring:message code="helpdesk.inquiry.alert.txt2" text="2019년 2월 20일 이전 주문은 고객센터 (080-807-0012) 로 문의해 주시면 답변해 드리겠습니다." /></span>
				<div id="ordGodList">
					<div class="board-list">
						<table>
							<caption>주문번호 조회 - 주문번호, 주문일자, 상품명, 수량, 결제금액, 주문/배송상태.</caption>
							<colgroup>
								<col style="width:40px;">
								<col style="width:130px;">
								<col style="width:80px;">
								<col>
								<col style="width:50px;">
								<col style="width:100px;">
								<col style="width:100px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col"></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordNo.txt" text="주문번호" /></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordDate.txt" text="주문일자" /></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordNm.txt" text="상품명" /></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordCnt.txt" text="수량" /></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.ordQnt.txt" text="결제금액" /></th>
									<th scope="col"><spring:message code="helpdesk.popup.selectord.list.sort.dlvState.txt" text="주문/배송상태" /></th>
								</tr>
							</thead>
							<tbody >
								<tr>
									<td colspan="7" class="no-result"><spring:message code="helpdesk.popup.selectord.list.noResult.txt" text="주문내역이 없습니다." /></td>
								</tr>

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="btn-wrap">
				<a href="#" id="btnSelectOrdNo"  class="btn-style02"><spring:message code="helpdesk.common.agree.btn" text="확인" /></a>
			</div>
			<div class="layer-popup-close">
				<button type="button" class="d_layer_close"><spring:message code="helpdesk.common.close.btn" text="닫기" /></button>
			</div>
		</section>
	</article>

	<script type="text/javascript">

	var flag = [
		{flagNm:'email'},
		{flagNm:'phone'},
		{flagNm:'inqCd'},
		{flagNm:'sub'},
		{flagNm:'content'},
		//{flagNm:'agree'}
		{flagNm:'name'},
	];
	for(var i in flag){
		flag[i].flagYn = 'false';
	}
	$(document).ready(function() {

		$('#cancleBtn').click(function(){
			if (confirm('<spring:message code="helpdesk.popup.inquiry.noneLogin.cancelconfirm.txt" text="페이지를 이동하시면 작성하신 내용이 삭제됩니다." />') == true) {
				location.href=$('#gForm > input[name=referer]').val();
			} else {
				return false;
			}
		});
		$('#regBtn').click(function(){
			if(regProcess() == true){
				if($('input:radio[id=rdoType02]').is(':checked')){
					alert('<spring:message code="helpdesk.popup.noneLogin.agreementTerms.suggestion.txt" text="개인정보 수집 이용에 동의해 주시기 바랍니다. 고객님의 개인정보는 약관에 의거하여 안전하게 보호됩니다." />');
					return false;
				}
				if (confirm(MESSAGES['helpdesk.js.popup.regconfirm.aks.txt']) == true){
					alert('<spring:message code="helpdesk.popup.inquiry.noneLogin.regconfirm.txt" text="1:1 문의가 등록되었습니다." />');

					if ($('#chkAgree').prop('checked')) {
						$('#chkAgree').val('Y');
					} else {
					 	$('#chkAgree').val('N');
					}

					$('#gForm').submit();
				}
			}else{
				return;
			}

		});

		//이메일 박스 넣을시
		$('#emailAddress > li').click(function(){
			$('#boardWriteEmail2').val($(this).text());
			hlpValidate.emailSelectBox("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",MESSAGES['helpdesk.js.common.email.btn.personally.txt'],0);
		});
		//이메일
		 $('#emailBox > input').on('blur',function(){
			 if($('#boardWriteEmail').val()!=""&& $('#boardWriteEmail2').val()!=""){
				hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",0);
			 }
 		});


		//연락처
		$('.inputcallBox > input').on('blur',function(){
			if($('#boardWriteContact').val()!="" && $('#boardWriteContact2').val()!="" && $('#boardWriteContact3').val()!="" ){
				hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",1);
			}
		});
		//상담분류
		$('.selectBox').click(function(){
			if( $('.selectBox > a').val() == MESSAGES['helpdesk.js.common.inqcd.btn.defualt.txt']){
				$("#boardWriteSort-msg").html('<spring:message code="helpdesk.common.emptytpcd.txt" />');	//상담분류는 필수입력 입니다.
				$("#boardWriteSort-msg").css('display', 'block');
				flag[2].flagYn='false';
			}else{
				$("#boardWriteSort-msg").html('');
				$("#boardWriteSort-msg").css('display', 'none');
				flag[2].flagYn='true';
			}
		});
		//내용
		$('#boardWriteContent').on('blur',function(){
			hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",4);
			var content = $(this).val();
			$('#counter').html(content.length);
		});
		$('#boardWriteContent').on('keyup',function(){
			var content = $(this).val();
			$('#counter').html(content.length);
		});

		//제목
		$('#boardWriteTitle').on('blur',function(){
			hlpValidate.subjectValidation("boardWriteTitle",31,"boardWriteTitle-msg",3);
		});
		//취소
		$('#cancle3').click(function(){
			$('.d_layer_close').click();
		});

		// 작성자
		$('#boardWriteName').on('blur',function(){
			hlpValidate.nameValidation("boardWriteName",11,"boardWriteName-msg",5,"<spring:message code='helpdesk.inquiry.writername.msg' text='작성자명을 입력해주세요'/>","");
		});
	});

	function regProcess(){
		// 작성자
		hlpValidate.nameValidation("boardWriteName",11,"boardWriteName-msg",5,"<spring:message code='helpdesk.inquiry.writername.msg' text='작성자명을 입력해주세요'/>","");

		// 메일
		hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",0);
		//연락처
		hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",1);
		//제목
		hlpValidate.subjectValidation("boardWriteTitle",31,"boardWriteTitle-msg",3);
		//내용
		hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",4);
		//상담분류
		if( $('#boardWriteSort > span').text() == MESSAGES['helpdesk.js.common.inqcd.btn.defualt.txt']){
			$("#boardWriteSort-msg").html('<spring:message code="helpdesk.common.emptytpcd.txt" text="상담분류를 선택해 주세요." />');
			$("#boardWriteSort-msg").css('display', 'block');
			flag[2].flagYn='false';
		}else{
			$("#boardWriteSort-msg").html('');
			$("#boardWriteSort-msg").css('display', 'none');
			/* $('#boardWriteSort > span').text() */
			flag[2].flagYn='true';
		}
		//플래그 테스트
		var submitTest = hlpValidate.flagTest();
		if(submitTest){
			$('#mbrNm').val($('#boardWriteName').val());
			$('#cstmrEmail').val($('#boardWriteEmail').val()+'@'+$('#boardWriteEmail2').val());
			$("#inqTpCd").val($("#boardWriteSort input").val());
			$("#inqContent").val($('#boardWriteContent').val());
			$("#inputfileNm").val(lastFileName);
			$("#inputfileUrl").val(lastPath);
			$("#inputfileSize").val(lastFileSize);

			return true;
		}else{

			return false;
		}
	}
</script>
