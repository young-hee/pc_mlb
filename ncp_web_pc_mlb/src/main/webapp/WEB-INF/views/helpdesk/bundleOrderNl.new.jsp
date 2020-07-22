<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>
<script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js"></script>
<script type="text/javascript" src="/javascript/message/helpdesk_${pageContext.response.locale.language}.js?v=${_version}"></script>

<form name ="gForm" id ="gForm" action ="<c:url value ='/helpdesk/bundleOrder/add'/>" method ="post" >
	<input type ="hidden" name ="csoOrgztOrdAffInq.inqerEmail" id="inqerEmail" value ="" />
	<input type="hidden"  name="csoOrgztOrdAffInq.inqCont" id="Content" />
	<input type="hidden"  name="csoOrgztOrdAffInq.orgztOrdAffInqTpCd"  value="ORGZT_ORD"/>
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileNm" id="inputfileNm" />
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileUr" id="inputfileUrl"/>
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileCp" id="inputfileSize" />
	<input type="hidden" name="referer" value="${header.referer}" />

	<div class="contain cs" id="contain">
		<div class="container">
			<h2 class="title01"><spring:message code="helpdesk.common.bundle.ttl" text="단체구매문의" /></h2>
			<main class="contents bundleOrderWrite-wrap" id="contents">
				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span><spring:message code="helpdesk.common.location.lbl" text="고객센터" /></span>
						<strong title="현재 위치"><spring:message code="helpdesk.common.bundle.location.lbl" text="단체구매문의" /></strong>
					</p>
				</div>

				<div class="board-write">
					<table summary="단체구매문의 입력">
						<caption>단체구매문의 입력</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName"><spring:message code="helpdesk.bundleadd.orgnm.txt" text="단체명"/></label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" name="csoOrgztOrdAffInq.inqOrgztNm" class="input-style01" style="width:590px;" />
								<span id="boardWriteName-msg" class="error-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteName2"><spring:message code="helpdesk.bundleadd.nm.txt" text="담당자명"/></label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName2" name="csoOrgztOrdAffInq.inqerNm" class="input-style01" style="width:590px;" />
								<span id="boardWriteName2-msg" class="error-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteEmail"><spring:message code="helpdesk.bundleadd.email.txt" text="답변이메일" /></label> <span class="required">*</span></th>
							<td id="emailBox">
								<input type="text" id="boardWriteEmail" class="input-style01" value="" style="width:150px;">
								<span class="at">@</span>
								<input type="text" id="boardWriteEmail2" class="input-style01" style="width:150px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button id="emailSelect" type="button" class="d_select_sel" style="width:150px;"><span><spring:message code="helpdesk.common.email.btn.personally.txt" text="직접입력" /></span></button>
									<ul id='emailAddress'>
										<li><a href="#"><spring:message code="helpdesk.common.email.btn.personally.txt" text="직접입력" /></a></li>
										<li><a href="#">naver.com</a></li>
										<li><a href="#">daum.net</a></li>
										<li><a href="#">nate.com</a></li>
										<li><a href="#">gmail.com</a></li>
										<li><a href="#">hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" id="boardWriteEmail-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact"><spring:message code="helpdesk.bundleadd.contact.txt" text="휴대폰" /></label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWriteContact" name="csoOrgztOrdAffInq.inqerMobilAreaNo" maxlength="3" class="input-style01" value="" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" id="boardWriteContact2" name="csoOrgztOrdAffInq.inqerMobilTlofNo" maxlength="4" class="input-style01" value="" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" id="boardWriteContact3" name="csoOrgztOrdAffInq.inqerMobilTlofWthnNo" maxlength="4" class="input-style01" value="" style="width:70px;" />
								</div>
								<span class="check-skin">
									<input type="checkbox" id="chkAgree" name="csoOrgztOrdAffInq.inqerSmsRecptnYn" value="Y" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkAgree"><spring:message code="helpdesk.common.answeragree.txt" text="답변등록여부 수신" /></label>
								<span class="error-msg" id="boardWriteContact-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent"><spring:message code="helpdesk.bundleadd.content.txt" text="내용"/></label> <span class="required">*</span></th>
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="<spring:message code='helpdesk.bundleadd.content.placeholder.txt' text='1,000자 미만 (특수문자 \ / : < > ; 사용불가)으로 입력해 주세요.' />" style="width:515px; height:150px;"></textarea>
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
							<th scope="row"><label for="fileSearch"><spring:message code="helpdesk.bundleadd.file.txt" text="첨부파일" /></label></th>
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
					<li><spring:message code="helpdesk.bundleadd.info.txt" text="단체구매문의는 입력하신 답변 이메일을 통해 답변 드리겠습니다." /></li>
				</ul>

				<!-- 개인정보 수집 이용에 대한 동의 S -->
				<div class="agreement-Wraper">
					<h3 class="title02"><spring:message code="helpdesk.bundleadd.noneLogin.agreementTerms.txt" text="개인정보 수집 이용에 대한 동의" /></h3>
					<%@ include file="/WEB-INF/views/helpdesk/include/stplatCont.view.jsp" %>
					<div class="txtFR-txt">
						<span class="rdo-skin">
							<input type="radio" id="rdoAgree1" name="rdoType">
							<span><spring:message code="helpdesk.noneLogin.agreementTerms.aree.btn" text="동의합니다." /></span>
						</span>
						<label for="rdoType01"><spring:message code="helpdesk.noneLogin.agreementTerms.aree.btn" text="동의합니다." /></label>

						<span class="rdo-skin">
							<input type="radio" id="rdoAgree2" name="rdoType" checked="checked">
							<span><spring:message code="helpdesk.noneLogin.agreementTerms.disAree.btn" text="동의하지 않습니다." /></span>
						</span>
						<label for="rdoType02"><spring:message code="helpdesk.noneLogin.agreementTerms.disAree.btn" text="동의하지 않습니다." /></label>
					</div>
				</div>
				<!--// 개인정보 수집 이용에 대한 동의 E -->

				<div class="btn-wrap">
					<a href="#" id="cancleBtn" class="btn lg"><spring:message code="helpdesk.common.cancle.btn" text="취소"/></a>
					<a href="#" id="regBtn" class="btn lg fill"><spring:message code="helpdesk.common.reg.btn" text="저장" /></a>
				</div>
			</main>
		</div>
	</div>
</form>

<script>
var flag = [
	{flagNm:'orgName'},
	{flagNm:'name'},
	{flagNm:'email'},
	{flagNm:'phone'},
	{flagNm:'content'},
	//{flagNm:'agree'}
];
for(var i in flag){
	flag[i].flagYn = 'false';
}
$(document).ready(function(){

	$('#cancleBtn').click(function(){
		$('#confirmBtn').val("cancle");
 		if(confirm("<spring:message code='helpdesk.popup.bundleOrder.noneLogin.cancelconfirm.txt' text='페이지를 이동하시면 작성하신 내용이 삭제됩니다.' />") == true)
 			location.href=$('#gForm > input[name=referer]').val();
	});
	$('#regBtn').click(function(){
		if(regProcess() == true){
			if($('input:radio[id=rdoAgree2]').is(':checked')){
				alert('<spring:message code="helpdesk.popup.noneLogin.agreementTerms.suggestion.txt" text="개인정보 수집 이용에 동의해주시기 바랍니다. 고객님의 개인정보는 약관에 의거하여 안전하게 보호됩니다." />');
				return false;
			}else{
				$('#confirmBtn').val("save");
				if (confirm(MESSAGES['helpdesk.js.popup.regconfirm.aks.txt']) == true) {	// 작성하신 내용을 등록하시겠습니까?
					if ($('#chkAgree').prop('checked')) {
						$('#chkAgree').val('Y');
					} else {
					 	$('#chkAgree').val('N');
					}

					$('#gForm').submit();
				}
			}
		}else{
			return;
		}
	});
	//취소
	$('#cancle').click(function(){
		$('.d_layer_close').click();
	});

	$('#cancle2').click(function(){
		$('.d_layer_close').click();
	});
	$('#cancle3').click(function(){
		$('.d_layer_close').click();
	});
	//단체명 유효성체크
	$('#boardWriteName').on('blur',function(){
		hlpValidate.orgNameValidation("boardWriteName",20,"boardWriteName-msg",0);
	});
	//담당자 유효성 체크
	$('#boardWriteName2').on('blur',function(){
		hlpValidate.nameValidation("boardWriteName2",11,"boardWriteName2-msg",1,MESSAGES['helpdesk.js.common.emptyorgname.txt'],MESSAGES['helpdesk.js.common.limitorgname.txt']);
	});
	//이메일 셀렉트박스
	$('#emailAddress > li').click(function(){
		$('#boardWriteEmail2').val($(this).text());
		hlpValidate.emailSelectBox("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",MESSAGES['helpdesk.js.common.email.btn.personally.txt'],2);
	});
	//이메일 유효성 체크
	$('#emailBox > input').on('blur',function(){
		 if($('#boardWriteEmail').val()!=""&& $('#boardWriteEmail2').val()!=""){
			hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",2);
		 }
	});
	//연락처 유효성 체크
	$('.inputcallBox > input').on('blur',function(){
		if($('#boardWriteContact').val()!="" && $('#boardWriteContact2').val()!="" && $('#boardWriteContact3').val()!="" ){
			hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",3);
		}
	});
	//내용 유효성 체크
	$('#boardWriteContent').on('blur',function(){
		hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",4);
		var content = $(this).val();
		$('#counter').html(content.length);
	});
	$('#boardWriteContent').on('keyup',function(){
		var content = $(this).val();
		$('#counter').html(content.length);
	});
});
function regProcess(){
	//단체명
	hlpValidate.orgNameValidation("boardWriteName",20,"boardWriteName-msg",0);
	//담당자 유효성 체크
	hlpValidate.nameValidation("boardWriteName2",11,"boardWriteName2-msg",1,MESSAGES['helpdesk.js.common.emptyorgname.txt'],MESSAGES['helpdesk.js.common.limitorgname.txt']);
	// 메일
	hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",2);
	//연락처
	hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",3);
	//내용
	hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",4);
	//동의 라디오버튼
	//검사
	var submitTest = hlpValidate.flagTest();
	if(submitTest){
		$('#inqerEmail').val($('#boardWriteEmail').val()+'@'+$('#boardWriteEmail2').val());
		$("#Content").val($('#boardWriteContent').val());
		$("#inputfileNm").val(lastFileName);
		$("#inputfileUrl").val(lastPath);
		$("#inputfileSize").val(lastFileSize);
		return true;
	}else{

		return false;
	}
}

</script>
