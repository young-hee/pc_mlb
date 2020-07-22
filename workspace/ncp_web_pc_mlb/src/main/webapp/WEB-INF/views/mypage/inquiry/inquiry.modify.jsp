<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
 <%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>
<%--  <%@ include file="/WEB-INF/views/helpdesk/include/inquriy.fileUpload.jsp" %> --%>
 <script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js?v=${_version}"></script>
 <script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js?v=${_version}"></script>
 <%-- <script type="text/javascript" src="/javascript/message/helpdesk_${pageContext.response.locale.language}.js?v=${_version}"></script> --%>

<form name ="gForm" id ="gForm" action ="<c:url value ='/mypage/inquiry/updateMypageInquiry'/>" method ="post" >
	<input type ="hidden" name="csoMtmInq.cstmrEmail" id="cstmrEmail" value ="" />
	<input type="hidden"  name="csoMtmInq.inqTpCd" id="inqTpCd" />
	<input type="hidden"  name="csoMtmInq.inqCont" id="inqContent" />
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileNm" id="inputfileNm"      value="${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileNm}"    />
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileUrl" id="inputfileUrl"    value="${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl}"   />
	<input type="hidden"  name="csoMtmInqAtchFile.mtmInqAtchFileCpcty" id="inputfileSize" value="${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileCpcty}" />
	<input type="hidden"  id="ordNum1" name="ordGod.ordNo" class="input-style01" style="width:443px;">

	<input type="hidden" name="srchMtmInqSn" id="srchMtmInqSn" value="${srchMtmInqSn}"/>


	<div class="contain my cs lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">1:1 문의</h2>

			<%@ include file="../include/lnb.jspf" %>

			<main class="contents oto_inquiryWrite-wrap" id="contents">

					<div class="location-contents">
						<p class="location">
							<span>Home</span>
							<span>마이페이지</span>
							<span>활동정보</span>
							<strong title="현재 위치">1:1 문의</strong>
						</p>
					</div>

				<div class="board-write">
					<table summary="1:1고객상담 입력">
						<caption>1:1 문의</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName">작성자</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" class="input-style01" name="csoMtmInq.inqCstmrNm" value="${inquiryList[0].csoMtmInq.inqCstmrNm}" style="width:150px;" readonly="readonly"/>
								<span class="error-msg" id="mbrNm-null" style="display:block;"></span>
							</td>
						</tr>

						<tr>
							<th scope="row"><label for="boardWriteEmail">답변 이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteEmail" class="input-style01" value="" style="width:110px;">
								<span class="at">@</span>
								<input type="text" id="boardWriteEmail2" class="input-style01" style="width:110px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:150px;" id="emailSelect"><span>직접입력</span></button>
									<ul id='emailAddress'>
										<li><a href="">직접입력</a></li>
										<li><a href="#">naver.com</a></li>
										<li><a href="#">daum.net</a></li>
										<li><a href="#">nate.com</a></li>
										<li><a href="#">gmail.com</a></li>
										<li><a href="#">hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:block;" id ="email-null"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">휴대전화</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" class="input-style01" id="boardWriteContact" name="csoMtmInq.cstmrmobilAreaNo" value="${inquiryList[0].csoMtmInq.cstmrmobilAreaNo}" style="width:64px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" id="boardWriteContact2" name="csoMtmInq.cstmrmobilTlofNo" value="${inquiryList[0].csoMtmInq.cstmrmobilTlofNo}"  style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" id="boardWriteContact3" name="csoMtmInq.cstmrmobilTlofWthnNo" value="${inquiryList[0].csoMtmInq.cstmrmobilTlofWthnNo}" style="width:70px;" />
								</div>

								<span class="check-skin">
									<input type="checkbox" id="chkAgree" name="csoMtmInq.cstmrSmsRecptnYn" value="${inquiryList[0].csoMtmInq.cstmrSmsRecptnYn}" />
									<span>선택</span>
								</span>
								<label for="chkAgree">답변등록여부 수신</label>
								<span class="error-msg" id="phone-error" style="display:block;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="ordNum2">주문번호</label></th>
							<td>
								<input type="text" id="ordNum2" class="input-style01" style="width:515px;" value="${inquiryOrdGodList[0].csoMtmInqOrdGod.ordNo}" disabled="disabled">
								<input type="hidden" id="ordGodTurn" name="ordGod.ordGodTurn" class="input-style01" style="width:443px;" value="${inquiryOrdGodList[0].csoMtmInqOrdGod.ordGodTurn}" >
								<a href="#layerPopup02" class="btn-style04 d_layer_open">주문찾기</a>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteSort">상담분류</label> <span class="required">*</span></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select select_consul_cate">
								<ncp:code var ="cdTpCd" code ="${inquiryList[0].csoMtmInq.inqTpCd}" />
								<button type="button" id="boardWriteSort" class="d_select_sel" style="width:152px;"><span><c:out value ="${cdTpCd.cdNm}" /></span></button>

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
								<span class="error-msg" id="inqCd-null" style="display:block;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteTitle">제목</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteTitle" class="input-style01" name='csoMtmInq.inqSj' value="${inquiryList[0].csoMtmInq.inqSj}" placeholder="30자 미만으로 입력해 주세요." style="width:515px;">
								<span class="error-msg" id="sub-error" style="display:block;"></span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent"><spring:message code="helpdesk.inquiry.content.txt" text="내용"/></label> <span class="required">*</span></th>
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="<spring:message code='helpdesk.inquiry.content.placeholder.txt' text='1,000자 미만 (특수문자 \ / : < > ; 사용불가)으로 입력해 주세요.' />" style="width:515px; height:150px;">${inquiryList[0].csoMtmInq.inqCont}</textarea>
								<div class="clearfix">
									<div class="fl">
										<span class="error-msg" id="content-error" style="display:block;"></span>	<!-- 더 이상 입력하실 수 없습니다. -->
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000" id="counter">0</em>/1000</span>
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
					<li>문의하신 내용에 대한 답변은 <span class="txt13-000">마이페이지 &gt; 활동정보 &gt; 1:1 문의</span>에서 확인하실 수 있습니다.</li>
				</ul>

				<div class="btnWrapBox">
					<a href="#" class="btn" id="cancleBtn">취소</a>
					<a href="#" id="regBtn" class="btn fill">저장</a>
				</div>

			</main>

		</div>
	</div>

</form>



	<!-- layerpopup -->
	<article id="layerPopup02" class="layer-popup orderInfoSearch-pop">
		<section class="layer-popup-cont" tabindex="0">
			<h2>주문 찾기</h2>
			<div class="layer-cont scroll">

				<div class="orderInfoSearch-popWrap">
					<dl class="period-wrap period-type02">
						<!-- 기간달력 -->
						<dt>조회기간</dt>
						<dd>
							<script>
								$(document).ready(function(){
									var dateFormat = "yyyy.mm.dd",
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
							<a href="#" id="month3btn" class="btn sm d_radio_select on" onclick="inputCalendar3();"><span>3개월</span></a>
							<a href="#" id="month6btn" class="btn sm d_radio_select" onclick="inputCalendar6();"><span>6개월</span></a>
							<!-- <a href="#" id="month12btn" class="btn sm d_radio_select" onclick="inputCalendar12();"><span>12개월</span></a> -->
							<input type="text" class="calendar" id="calendarFrom" readonly><input type="text" class="calendar" id="calendarTo" readonly>
							<a href="#" class="btn sm" id="btnlistsearch">검색</a>
							<p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p><!-- 2018-08-13 -->
						</dd>
					</dl>

					<ul class="text-list02">
						<li>2019년 2월 20일 이후 주문만 조회 가능합니다.</li>
						<li>2019년 2월 20일 이전 주문은 고객센터 (080-807-0012) 로 문의해 주시면 답변해 드리겠습니다.</li>
					</ul>

					<div id="ordGodList">
						<div class="board-list">
							<table>
								<caption>주문찾기 주문정보, 주문일자, 상품명, 수량, 결제금액, 주문/배송상태</caption>
								<colgroup>
									<col style="width:40px;">
									<col style="width:130px;">
									<col style="width:80px;">
									<col>
									<col style="width:40px;">
									<col style="width:100px;">
									<col style="width:100px;">
								</colgroup>
								<thead>
									<tr>
										<th scope="col"></th>
										<th scope="col">주문정보</th>
										<th scope="col">주문일자</th>
										<th scope="col">상품명</th>
										<th scope="col">수량</th>
										<th scope="col">결제금액</th>
										<th scope="col">주문/배송상태</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan="7" class="no-result">주문내역이 없습니다.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="btnWrapBox">
					<a href="#" id="btnSelectOrdNo" class="btn fill" >확인</a>
				</div>

			</div>
			<div class="layer-popup-close">
				<button type="button" class="d_layer_close">닫기</button>
			</div>
		</section>
	</article>
	<!-- //layerpopup -->

	<form id="fTempImage" name="fTempImage" method="POST" style="display:none;" > <input type="hidden" /></form>



<script type="text/javascript">
	var flag = [
		{flagNm:'boardWriteEmail'},
		{flagNm:'boardWriteContact'},
		{flagNm:'inqCd'},
		{flagNm:'boardWriteTitle'},
		{flagNm:'boardWriteContent'},
	];

	for(var i in flag){
		flag[i].flagYn = 'false';
	}


	$(document).ready(function(){
		// 내용
		var contents = $('#boardWriteContent').val();
		$('#counter').text(contents.length);

		// 이메일 셋팅
		var email = '${inquiryList[0].csoMtmInq.cstmrEmail}' ;
		var emailStr = email.split('@');
		$('#boardWriteEmail').val(emailStr[0]);
		$('#boardWriteEmail2').val(emailStr[1]);

		// file
		var atchFileNm = '${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileNm}';
		if (typeof(atchFileNm) != 'undefined' && atchFileNm != '') {
			$('.file-name').html(atchFileNm + "<a href='#' onclick='javascript:fileDelete();' class='btn-file-delete'>삭제</a>");
		    $('#fileSearch').attr("disabled","true");
		}
	    // 주문번호
	    $('#ordNum1').val('${inquiryOrdGodList[0].csoMtmInqOrdGod.ordNo}');


		// 취소버튼
		$('#cancleBtn').click(function(){

/* 			$('#confirmBtn').val("cancleModefy");
			confirmLayer(MESSAGES['helpdesk.js.popup.cancleconfirm.aks.txt']); */
			if (confirm('페이지를 이동하시면 작성하신 내용이 삭제됩니다.') == true) {
				//location.href="/mypage/inquiry/list";
				location.href ='/mypage/inquiry/detail/'+ $('#srchMtmInqSn').val();
			} else {
				return false;
			}
		});

		// 저장버튼
		$('#regBtn').click(function(){
/* 			if(regProcess()==true){
				$('#confirmBtn').val("Modefy");
				confirmLayer(MESSAGES['helpdesk.js.popup.regconfirm.aks.txt']);
 			}else{
				return false;
			} */
			if(regProcess() == true && confirm('작성하신 내용을 등록하시겠습니까?') == true){
				//alert("1:1 문의가 등록되었습니다.");
				if ($('#chkAgree').prop('checked')) {
					$('#chkAgree').val('Y');
				} else {
				 	$('#chkAgree').val('N');
				}

				$('#gForm').submit();
			}else{
				return;
			}
		});


		//캘린더 디폴트3개월전 값
		var to = new Date();
		var from = new Date();
		var dd = to.getDate();
		var mm = to.getMonth()+1; //January is 0!
		var yyyy = to.getFullYear();
		if(dd<10) {
		    dd='0'+dd
		}
		if(mm<10) {
		    mm='0'+mm
		}
		to = yyyy+'.'+mm+'.'+dd;
		$('#calendarTo').val(to);

		from.setMonth(from.getMonth() - 3);
		var year = from.getFullYear();
		var month = from.getMonth() + 1;
		var day = from.getDate();
		if(day<10) {
			day='0'+day
		}
		if(month<10) {
			month='0'+month
		}
		from= year+'.'+month+'.'+day;
		//to.setMonth(to.getMonth()-3);
		$('#calendarFrom').val(from);

		//주무번호 찾아보기
		$('#btnlistsearch').click(function(){
			//달력 8월 20 일 전 눌렀을경우 alert
// 			var processTime = $('#calendarFrom').val();
// 			var split1= processTime.split('-');
// 			if(split1[0] < '2019'){
// 				if(split1[1].substr(0,1) <= '0' ){
// 					if(split1[1].substr(1,1)<'8'){
// 						$('#period-error1').text('<spring:message code="helpdesk.inquiry.alert.txt1" />');
// 						$('#period-error2').text('<spring:message code="helpdesk.inquiry.alert.txt2" />');
// 						$('#calendarFrom').val(from);
// 						return false;
// 					}else{
// 						if(split1[1].substr(1,1)=='8'){
// 							if(split1[2].substr(0,1)<'2'){
// 								$('#period-error1').text('<spring:message code="helpdesk.inquiry.alert.txt1" />');
// 								$('#period-error2').text('<spring:message code="helpdesk.inquiry.alert.txt2" />');
// 								$('#calendarFrom').val(from);
// 								return false;
// 							}else if(split1[2].substr(0,1)=='2'){//22일부터 제외
// 								if(split1[2].substr(1,1)<'2'){
// 									$('#period-error1').text('<spring:message code="helpdesk.inquiry.alert.txt1" />');
// 									$('#period-error2').text('<spring:message code="helpdesk.inquiry.alert.txt2" />');
// 									$('#calendarFrom').val(from);
// 									return false;
// 								}else{
// 									$('#period-error1').text('');
// 									$('#period-error2').text('');
// 								}
// 							}else{
// 								$('#period-error1').text('');
// 								$('#period-error2').text('');
// 							}
// 						}
// 					}
// 				}else{
// 					$('#period-error1').text('');
// 					$('#period-error2').text('');
// 				}
// 			}else{
// 				$('#period-error1').text('');
// 				$('#period-error2').text('');
// 			}

			var inputtest='${userDetail.mbr.mbrNo}';
	 		var  param = {pageNo:$('#pageNo').val()};
				 param['mbrNo'] = inputtest;
			if($('#calendarFrom').val() != "" && $('#calendarTo').val() != ""){
				var cf = $('#calendarFrom').val();
				var ct = $('#calendarTo').val();
				var bFrom = cf.split('.');
				var bTo = ct.split('.');

				var aFrom = bFrom[0]+"/"+bFrom[1]+"/"+bFrom[2];
				var aTo = bTo[0]+"/"+bTo[1]+"/"+bTo[2];

				var parsedDay=parseInt(bTo[2]);// To 일 문자에서 숫자로변환
				var parsedMonth =parseInt(bTo[1]);// TO 월 문자에서 숫자로변환
				var actualTo = new Date(bTo[0],parsedMonth-1,parsedDay+1);

				param['calendarFrom'] = aFrom;
				param['calendarTo'] = actualTo;
			}
			$.ajax({
		        type:"POST",
		        url:"/helpdesk/csInquiry/ord/list.ajax",
		        data : param,
		        success: function(data){
		    		$('#ordGodList').html(data);

		    		return;
		        },
		        error: function() {
		        	alert('세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.');
		        }
		    });
		});
		//찾아보기 레이어 팝업 날짜 시작


		//주문번호 선택후 확인 시작
		$('#btnSelectOrdNo').click(function(){
			var test =$('input:radio[name=rdoOrder]:checked').val();
			$('#ordNum2').val(test);//인풋박스 보여주기용
			$('#ordNum1').val(test);//파라미터
			$('#ordGodTurn').val($('input:radio[name=rdoOrder]:checked').attr('group'));
			$('.d_layer_close').click();
		});
		//이메일 선택박스
		$('#emailAddress > li').click(function(){
			$('#boardWriteEmail2').val($(this).text());
			hlpValidate.emailSelectBox("boardWriteEmail","boardWriteEmail2","email-null","직접입력",0);
		});
		//이메일 입력
	 	$('.emailBox > input').on('blur',function(){
	 		 if($('#boardWriteEmail').val()!=""&& $('#boardWriteEmail2').val()!=""){
				hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","email-null",0);
	 		 }
		});
		//연락처 입력
		$('.phoneBox > input').on('blur',function(){
			if($('#boardWriteContact').val()!="" && $('#boardWriteContact2').val()!="" && $('#boardWriteContact3').val()!="" ){
				hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","phone-error",1);
			}
		});
		//상담분류
		$('.selectBox').click(function(){
			if( $('.selectBox > a').val() == MESSAGES['helpdesk.js.common.inqcd.btn.defualt.txt']){
				$("#inqCd-null").html('상담분류를 선택해 주세요.');
				flag[2].flagYn='false';
			}else{
				$("#inqCd-null").html('');
				flag[2].flagYn='true';
			}
		});
		//내용
		$('#boardWriteContent').on('blur',function(){
			hlpValidate.contentValidation("boardWriteContent",1000,"content-error",4);
		});
		$('#boardWriteContent').on('keyup',function(){
			var content = $(this).val();
			$('#counter').html(content.length);
		});
		//제목
		$('#boardWriteTitle').on('blur',function(){
			hlpValidate.subjectValidation("boardWriteTitle",31,"sub-error",3);
		});

		// 답변 여부 알림 수신
		if ($('#chkAgree').val() == 'Y') {
			$('#chkAgree').prop('checked', true);
		} else {
			$('#chkAgree').prop('checked', false);
		}
	});

	function regProcess() {
		// 메일
		hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","email-null",0);
		//연락처
		hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","phone-error",1);
		//제목
		hlpValidate.subjectValidation("boardWriteTitle",31,"sub-error",3);
		//내용
		hlpValidate.contentValidation("boardWriteContent",1000,"content-error",4);
		//상담분류
		if( $('#boardWriteSort > span').text() == "선택"){
			$("#inqCd-null").html('상담분류를 선택해 주세요.');
			flag[2].flagYn='false';
		}else{
			$("#inqCd-null").html('');
			$('#boardWriteSort > span').text()
			flag[2].flagYn='true';
		}
		//플래그 테스트
		var submitTest = hlpValidate.flagTest();
		if(submitTest){

			//상품분류선택 입력
			var inqTpCdLen = $("#boardWriteSort").find("input").length;
			if( inqTpCdLen > 0 ){
				$("#inqTpCd").val($("#boardWriteSort input").val());
			}else{
				$("#inqTpCd").val('${inquiryList[0].csoMtmInq.inqTpCd}');
			}

			$('#cstmrEmail').val($('#boardWriteEmail').val()+'@'+$('#boardWriteEmail2').val());
			//$("#inqTpCd").val($("#boardWriteSort input").val());
			$("#inqContent").val($('#boardWriteContent').val());

			if(typeof(lastFileName)!="undefined" && lastFileName!="" ){
				$("#inputfileNm").val(lastFileName);
			}
			if(typeof(lastPath)!=="undefined" && lastPath!=""){
				$("#inputfileUrl").val(lastPath);
			}
			if(typeof(lastFileSize)!=="undefined" && lastFileSize!=""){
				$("#inputfileSize").val(lastFileSize);
			}

			return true;
		}else{
			return false;

		}
	}

	function inputCalendar3(){
		var to = new Date();
		var from = new Date();
		var dd = to.getDate();
		var mm = to.getMonth()+1; //January is 0!
		var yyyy = to.getFullYear();
		if(dd<10) {
		    dd='0'+dd
		}
		if(mm<10) {
		    mm='0'+mm
		}
		to = yyyy+'.'+mm+'.'+dd;
		$('#calendarTo').val(to);

		from.setMonth(from.getMonth() - 3);
		var year = from.getFullYear();
		var month = from.getMonth() + 1;
		var day = from.getDate();
		if(day<10) {
			day='0'+day
		}
		if(month<10) {
			month='0'+month
		}
		from= year+'.'+month+'.'+day;
		//to.setMonth(to.getMonth()-3);
		$('#calendarFrom').val(from);
	}
	function inputCalendar6(){
		var to = new Date();
		var from = new Date();
		var dd = to.getDate();
		var mm = to.getMonth()+1; //January is 0!
		var yyyy = to.getFullYear();
		if(dd<10) {
		    dd='0'+dd
		}
		if(mm<10) {
		    mm='0'+mm
		}
		to = yyyy+'.'+mm+'.'+dd;
		$('#calendarTo').val(to);

		from.setMonth(from.getMonth() - 6);
		var year = from.getFullYear();
		var month = from.getMonth() + 1;
		var day = from.getDate();
		if(day<10) {
			day='0'+day
		}
		if(month<10) {
			month='0'+month
		}
		from= year+'.'+month+'.'+day;
		//to.setMonth(to.getMonth()-3);
		$('#calendarFrom').val(from);
	}
	function inputCalendar12(){
		var to = new Date();
		var from = new Date();
		var dd = to.getDate();
		var mm = to.getMonth()+1; //January is 0!
		var yyyy = to.getFullYear();
		if(dd<10) {
		    dd='0'+dd
		}
		if(mm<10) {
		    mm='0'+mm
		}
		to = yyyy+'.'+mm+'.'+dd;
		$('#calendarTo').val(to);

		from.setMonth(from.getMonth() - 12);
		var year = from.getFullYear();
		var month = from.getMonth() + 1;
		var day = from.getDate();
		if(day<10) {
			day='0'+day
		}
		if(month<10) {
			month='0'+month
		}
		from= year+'.'+month+'.'+day;
		$('#calendarFrom').val(from);
	}
</script>
