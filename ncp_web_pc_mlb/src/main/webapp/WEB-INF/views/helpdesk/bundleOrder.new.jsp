<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

<script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js"></script>
<%-- <script type="text/javascript" src="/javascript/message/helpdesk_${pageContext.response.locale.language}.js?v=${_version}"></script> --%>

<form name ="gForm" id ="gForm" action ="/helpdesk/bundleOrder/add" method ="post" >
	<input type ="hidden" name="csoOrgztOrdAffInq.inqerEmail" id="inqerEmail" value ="" />
	<input type="hidden"  name="csoOrgztOrdAffInq.inqCont" id="Content" />
	<input type="hidden"  name="csoOrgztOrdAffInq.orgztOrdAffInqTpCd"  value="ORGZT_ORD"/>
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileNm" id="inputfileNm" />
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileUr" id="inputfileUrl"/>
	<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileCp" id="inputfileSize" />

	<div class="contain cs" id="contain">
		<div class="container">

			<h2 class="title01">단체구매문의</h2>

			<main class="contents bundleOrderWrite-wrap" id="contents">

				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span>고객센터</span>
						<strong title="현재 위치">단체구매문의</strong>
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
							<th scope="row"><label for="boardWriteName">단체명</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" name="csoOrgztOrdAffInq.inqOrgztNm" class="input-style01" style="width:590px;" />
								<span id="boardWriteName-msg" class="error-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteName2">담당자명</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName2" name="csoOrgztOrdAffInq.inqerNm" class="input-style01" style="width:590px;" value="${userDetail.mbr.mbrNm }" />
								<span id="boardWriteName2-msg" class="error-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteEmail">답변 이메일</label> <span class="required">*</span></th>
							<td id="emailBox">
								<input type="text" id="boardWriteEmail" class="input-style01" value="${mbrEmail1}" style="width:150px;">
								<span class="at">@</span>
								<input type="text" id="boardWriteEmail2" class="input-style01" style="width:150px;" value="${mbrEmail2}">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:150px;"><span>직접입력</span></button>
									<ul id="emailAddress">
										<li><a href="#">직접입력</a></li>
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
							<th scope="row"><label for="boardWriteContact">휴대폰</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWriteContact" maxlength="3" name="csoOrgztOrdAffInq.inqerMobilAreaNo" class="input-style01" value="${userDetail.mbr.mobilAreaNo}" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" id="boardWriteContact2" maxlength="4"  name="csoOrgztOrdAffInq.inqerMobilTlofNo" class="input-style01" value="${userDetail.mbr.mobilTlofNo}" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" id="boardWriteContact3" maxlength="4"  name="csoOrgztOrdAffInq.inqerMobilTlofWthnNo" class="input-style01" value="${userDetail.mbr.mobilTlofWthnNo}" style="width:70px;" />
								</div>

								<span class="check-skin">
									<input type="checkbox" id="chkAgree" name="csoOrgztOrdAffInq.inqerSmsRecptnYn" value="Y" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkAgree">답변등록여부 수신</label>
								<span class="error-msg" id="boardWriteContact-msg" style="display:none;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent">내용</label> <span class="required">*</span></th>
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="1,000자 미만 (특수문자 &bsol; / : &lt; &gt; &semi; 사용불가)으로 입력해 주세요." style="width:515px; height:150px;"></textarea>
								<div class="clearfix">
									<div clsss="fl">
										<span class="error-msg" id="boardWriteContent-msg" style="display:none;"></span>	<!-- 더 이상 입력하실 수 없습니다. -->
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000" id="counter">0</em>자/1,000자</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="fileSearch">첨부파일</label></th>
							<td>
								<span class="file-search">
									<input type="text" class="input-style01" style="width:443px;" id="fileInputName" readonly>
									<span>
										<input type="file" id="fileSearch" name="files" onChange="javascript:filetest(this);">
										<label for="fileSearch">파일찾기</label>
									</span>
								</span>
								<div class="file-name"></div>
								<ul class="text-list02 col-type02">
									<li>파일은 1개만 첨부할 수 있습니다. (10MB 이하)</li>
									<li>파일형식 : hwp, doc/docx, xls/xlsx, ppt/pptx, jpg, gif, png, pdf</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
				<ul class="text-list02 col-type01">
					<li>단체구매문의는 입력하신 답변 이메일을 통해 답변 드리겠습니다.</li>
				</ul>
				<div class="btn-wrap">
					<a href="#" id="cancleBtn" class="btn lg">취소</a>
					<a href="#" id="regBtn" class="btn lg fill">저장</a>
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
	];

	for(var i in flag){
		flag[i].flagYn = 'false';
	}

	$(document).ready(function(){
		$('#cancleBtn').click(function(){
 			if(confirm('페이지를 이동하시면 작성하신 내용이 삭제됩니다.') == true)
				location.href="/helpdesk/bundleOrder/list";
		});
		$('#regBtn').click(function(){
			if(regProcess()==true){
 				if(confirm('작성하신 내용을  등록하시겠습니까?') == true){
 					if ($('#chkAgree').prop('checked')) {
 						$('#chkAgree').val('Y');
 					} else {
 					 	$('#chkAgree').val('N');
 					}

 					$('#gForm').submit();
 				}
			}else{
				return false;
			}

		});
		//취소
		$('#cancle').click(function(){
			$('.d_layer_close').click();
		});

		$('#cancle2').click(function(){
			$('.d_layer_close').click();
		});
		//단체명 유효성체크
		$('#boardWriteName').on('blur',function(){
			hlpValidate.orgNameValidation("boardWriteName",20,"boardWriteName-msg",0);
		});
		//담당자 유효성 체크
		$('#boardWriteName2').on('blur',function(){

		hlpValidate.nameValidation("boardWriteName2",11,"boardWriteName2-msg",1,"담당자명을 입력해 주세요.","담당자명은 10자까지 가능합니다.");	//담당자명을 입력해 주세요. //담당자명은 10자까지 가능합니다.
		});
		//이메일 셀렉트박스
		$('#emailAddress > li').click(function(){
			$('#boardWriteEmail2').val($(this).text());
			hlpValidate.emailSelectBox("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg","직접입력",2);
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
		hlpValidate.nameValidation("boardWriteName2",11,"boardWriteName2-msg",1,"담당자명을 입력해 주세요.","담당자명은 10자까지 가능합니다.");
		// 메일
		hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",2);
		//연락처
		hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",3);
		//내용
		hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",4);
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
			return false
		}
	}

</script>
