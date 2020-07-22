<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %> 
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>
<script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js"></script>  
<%-- <script type="text/javascript" src="/javascript/message/helpdesk_${pageContext.response.locale.language}.js?v=${_version}"></script> --%>
<form name ="gForm" id ="gForm" action ="<c:url value ='/helpdesk/bundleOrder/add'/>" method ="post" >
<input type ="hidden" name ="csoOrgztOrdAffInq.inqerEmail" id="inqerEmail" value ="" />
<input type="hidden"  name="csoOrgztOrdAffInq.inqCont" id="Content" />
<input type="hidden"  name="csoOrgztOrdAffInq.orgztOrdAffInqTpCd"  value="AFF_INQ"/>
<input type="hidden" name = "csoOrgztOrdAffInq.orgztOrdAffInqDetailTpCd" id="affTpCd"/>
<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileNm" id="inputfileNm" />
<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileUr" id="inputfileUrl"/>
<input type="hidden"  name="csoOrgztOrdAffInqAtchmnfl.orgztOrdAffInqAtchFileCp" id="inputfileSize" />	
 <div class="location-container">  
	<div class="location-contents"> 
<h2 class="title01"><spring:message code="helpdesk.common.ttl" /></h2>
<p class="location">
	<a href="/main/mall/view"><span>Home</span></a>
	<a href="/helpdesk/"><span><spring:message code="helpdesk.common.location.lbl" /></span></a>
	<strong title="현재 위치"><spring:message code="helpdesk.common.affinquiry.location.lbl" /></strong>
</p>	
</div>
</div>
<acrticle id="container" class="">
<section id="contents">
<h3 class="title02"><spring:message code="helpdesk.common.affinquiry.ttl" /></h3>
<p class="txt-sub-info02"><spring:message code="helpdesk.affinquiry.intro.txt" /></p>
<p class="text-required"><span class="required">*</span><spring:message code="helpdesk.common.required.txt" /></p>
<div class="board-write">
	<table summary="1:1고객상담 입력">
		<caption>1:1고객상담 입력</caption>
		<colgroup>
	<col style="width:200px;">
	<col>
</colgroup>
<tbody>
<tr>
	<th scope="row"><spring:message code="helpdesk.affinquiry.TP.txt" /> <span class="required">*</span></th>
	<td>
		<!-- select -->
		<div class="select-style01 d_select">
			<button type="button" id="boardWriteSort" class="d_select_sel" style="width:152px;"><span><spring:message code="helpdesk.common.inqcd.btn.defualt.txt" /></span></button>
			<ul>				
			<c:choose>
				<c:when test="${pageContext.response.locale.language eq 'ko'}">
					<ncp:codes group ="AFF_INQ" var ="orgztTp" />
				</c:when>
				<c:otherwise>
					<ncp:codes group ="AFF_INQ" var ="orgztTp" includeNotUse="false"/>
				</c:otherwise>
			</c:choose>
			<c:forEach var ="cd" items ="${orgztTp}">				 													
			<li class="selectBox"><a href="#none" onclick="return false;" id="${cd.cd}" class="selCd"><c:out value ="${cd.cdNm}" />
			<input type="hidden" value="${cd.cd}"/></a></li>
			</c:forEach>
			</ul>
		</div>
		<span class="error-msg" id="inqCd-null" style="display:block;"></span>
	</td>
</tr>
<tr>
	<th scope="row"><label for="boardCompany"><spring:message code="helpdesk.affinquiry.orgnm.txt" /></label> <span class="required">*</span></th>
	<td>
		<input type="text" id="boardCompany" name="csoOrgztOrdAffInq.inqOrgztNm" class="input-style01" style="width:443px;">
		<span class="error-msg" id="boardCompany-msg" style="display:block;"></span>			
	</td>
</tr>
<tr>
	<th scope="row"><label for="boardCompanyName"><spring:message code="helpdesk.affinquiry.nm.txt" /></label> <span class="required">*</span></th>
	<td>
		<input type="text" id="boardCompanyName"  name="csoOrgztOrdAffInq.inqerNm"  class="input-style01" style="width:443px;">
		<span class="error-msg" id='boardCompanyName-msg' style="display:block;"></span>
		
	</td>
</tr>
<tr>
	<th scope="row"><label for="boardWriteContact"><spring:message code="helpdesk.affinquiry.contact.txt" /></label> <span class="required">*</span></th>
	<td class='phoneBox'>
		<input type="text" id="boardWriteContact" maxlength="3"   name="csoOrgztOrdAffInq.inqerMobilAreaNo" class="input-style01" style="width:65px;" value="${userDetail.mbr.mobilAreaNo }" />
		<span class="hyphen">-</span>
		<input type="text" id="boardWriteContact2" maxlength="4"  name="csoOrgztOrdAffInq.inqerMobilTlofNo"   class="input-style01" style="width:75px;" value="${userDetail.mbr.mobilTlofNo }" />
		<span class="hyphen">-</span>
		<input type="text" id="boardWriteContact3"  maxlength="4"  name="csoOrgztOrdAffInq.inqerMobilTlofWthnNo" class="input-style01" style="width:75px;" value="${userDetail.mbr.mobilTlofWthnNo }" />

		<span class="check-skin">
			<input type="checkbox" id="chkReply" name="csoOrgztOrdAffInq.inqerSmsRecptnYn" checked="checked" value="Y">
			<span>선택</span>
		</span>
		<label for="chkReply"><spring:message code="helpdesk.common.answeragree.txt" /></label>

		<span class="error-msg"  id="boardWriteContact-msg" style="display:block;"></span>
	</td>
</tr>
<tr>
	<th scope="row"><label for="boardWriteEmail"><spring:message code="helpdesk.affinquiry.email.txt" /></label> <span class="required">*</span></th>
	<td  class='emailBox'>
		<input type="text" id="boardWriteEmail" class="input-style01" value="${mbrEmail1}" style="width:152px;">
		<span class="at">@</span>
		<input type="text" id="boardWriteEmail2"  class="input-style01"  value="${mbrEmail2}" style="width:152px;">
		<!-- select -->
		<div class="select-style01 d_select">
			<button id="emailSelect" type="button" class="d_select_sel" style="width:152px;"><span><spring:message code="helpdesk.common.email.btn.personally.txt" /></span></button>
			<ul id='emailAddress'>
				<li><a href="#"><spring:message code="helpdesk.common.email.btn.personally.txt" /></a></li>
				<li><a href="#">naver.com</a></li>
				<li><a href="#">daum.net</a></li>
				<li><a href="#">nate.com</a></li>
				<li><a href="#">gmail.com</a></li>
				<li><a href="#">hotmail.com</a></li>
			</ul>
		</div>
		<!-- //select -->
		<span class="error-msg" id ="boardWriteEmail-msg" style="display:block;"></span>
	</td>
</tr>
<tr>
	<th scope="row"><label for="boardWriteContent"><spring:message code="helpdesk.affinquiry.content.txt" /></label> <span class="required">*</span></th>
	<td>
		<div>
			<textarea cols="30" rows="10" id="boardWriteContent" placeholder="<spring:message code="helpdesk.affinquiry.content.placeholder.txt" />" style="width:587px; height:190px;"></textarea>
			<span class="error-msg" id="boardWriteContent-msg" style="display:block;"></span>
		</div>
	</td>
</tr>
<tr>
	<th scope="row"><label for="fileSearch"><spring:message code="helpdesk.affinquiry.file.txt" /></label></th>
	<td>
		<span class="file-search">
			<input id="fileInputName" type="text" class="input-style01" style="width:443px;" readonly>
			<span>
				<input type="file" name="files" id="fileSearch" onChange="javascript:filetest(this);">
				<label for="fileSearch"><spring:message code="helpdesk.common.filefind.btn" /></label>
			</span>
		</span>
		<div class="file-name"></div>
		<ul class="text-list02 col-type01">
			<li><spring:message code="helpdesk.common.file.condition.txt1" /></li>
			<li><spring:message code="helpdesk.common.file.condition.txt2" /></li>
		</ul>
	</td>
</tr>
</tbody>
</table>
</div>
<ul class="text-list02 col-type01">
	<li><spring:message code="helpdesk.affinquiry.answer.txt" /></li>
</ul>
<div class="btn-wrap">
	<a href="#" id="cancleBtn" class="btn-style03"><spring:message code="helpdesk.common.cancle.btn" /></a>
	<a href="#" id="regBtn"  class="btn-style02"><spring:message code="helpdesk.common.reg.btn" /></a>
</div>	

</form>
</section>
</acrticle>
<script type="text/javascript">		
var flag = [
	{flagNm:'name'},
	{flagNm:'phone'},
	{flagNm:'email'},
	{flagNm:'content'},
	{flagNm:'company'},
	{flagNm:'inqCd'}
];			
for(var i in flag){
	flag[i].flagYn = 'false';
}	
	$(document).ready(function() {
		$('#cancleBtn').click(function(){
// 			if(confirm('<spring:message code="helpdesk.popup.cancleconfirm.aks.txt" />')==true)
// 				location.href="/helpdesk/";
			$('#confirmBtn').val("cancle");
			confirmLayer(MESSAGES['helpdesk.js.popup.cancleconfirm.aks.txt']);	
		});		
		$('#regBtn').click(function(){
			if(regProcess()==true){
// 				if(confirm('<spring:message code="helpdesk.popup.regconfirm.aks.txt" />')==true	){
// 					$('#gForm').submit();
// 				}	
				$('#confirmBtn').val("save");
				confirmLayer(MESSAGES['helpdesk.js.popup.regconfirm.aks.txt']);
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
		//달력 관련
		var dateFormat = "yy - mm - dd",
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
		//담당자 유효성 체크
		$('#boardCompanyName').on('blur',function(){					
			hlpValidate.nameValidation("boardCompanyName",11,"boardCompanyName-msg",0,MESSAGES['helpdesk.js.common.emptyorgname.txt'],MESSAGES['helpdesk.js.common.limitorgname.txt']);//담당자명을 입력해 주세요.//담당자명은 10자까지 가능합니다.		
		});								
		// 회사 유효성 체크
		$('#boardCompany').on('blur',function(){					
			hlpValidate.nameValidation("boardCompany",21,"boardCompany-msg",4,MESSAGES['helpdesk.js.common.emptycompname.txt'],MESSAGES['helpdesk.js.common.limitcompname.txt']);//회사명을 입력해 주세요.//회사명은 20자까지 가능합니다.	
		});					
		//연락처 유효성 체크
		$('.phoneBox > input').on('blur',function(){
			if($('#boardWriteContact').val()!="" && $('#boardWriteContact2').val()!="" && $('#boardWriteContact3').val()!="" ){
			hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",1);		
			}
		});			
		//이메일 셀렉트박스
		$('#emailAddress > li').click(function(){	 		
			$('#boardWriteEmail2').val($(this).text());
			hlpValidate.emailSelectBox("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",MESSAGES['helpdesk.js.common.email.btn.personally.txt'],2);	//직접입력
		});			
		//이메일 유효성 체크
		$('.emailBox > input').on('blur',function(){
			 if($('#boardWriteEmail').val()!=""&& $('#boardWriteEmail2').val()!=""){
			hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",2);	
			 }
		});				
		//내용 유효성 체크						
		$('#boardWriteContent').on('blur',function(){			
			hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",3);										
		});
		//제휴 구분
		$('.selectBox').click(function(){			
		if( $('.selectBox > a').val() == MESSAGES['helpdesk.js.common.inqcd.btn.defualt.txt']){	
		$("#inqCd-null").html('<spring:message code="helpdesk.affinquiry.orgnm.txt2" />');	
		flag[5].flagYn='false';				
		}else{			
		$("#inqCd-null").html('');	
		flag[5].flagYn='true';	
		}			
		});
	
});		
	function regProcess(){
		//제휴문의
		if( $('#boardWriteSort > span').text() == MESSAGES['helpdesk.js.common.inqcd.btn.defualt.txt']){	
			$("#inqCd-null").html('<spring:message code="helpdesk.affinquiry.orgnm.txt2" />');
			flag[5].flagYn='false';			
		}else{
			$("#inqCd-null").html('');	
			$('#boardWriteSort > span').text()
			flag[5].flagYn='true';			
		}					
		// 회사 유효성 체크						
		hlpValidate.nameValidation("boardCompany",21,"boardCompany-msg",4,MESSAGES['helpdesk.js.common.emptycompname.txt'],MESSAGES['helpdesk.js.common.limitcompname.txt']);	//회사명을 입력해 주세요.//회사명은 20자까지 가능합니다.			
		//담당자 유효성 체크						
		hlpValidate.nameValidation("boardCompanyName",11,"boardCompanyName-msg",0,MESSAGES['helpdesk.js.common.emptyorgname.txt'],MESSAGES['helpdesk.js.common.limitorgname.txt']);	//담당자명을 입력해 주세요.//담당자명은 10자까지 가능합니다.						
		//연락처							
		hlpValidate.phoneValidation("boardWriteContact","boardWriteContact2","boardWriteContact3","boardWriteContact-msg",1);																
		// 메일
		hlpValidate.emailValidation("boardWriteEmail","boardWriteEmail2","boardWriteEmail-msg",2);																								
		//내용
		hlpValidate.contentValidation("boardWriteContent",1000,"boardWriteContent-msg",3);										
		//검사
		var submitTest = hlpValidate.flagTest();		
		if(submitTest){						
			$('#inqerEmail').val($('#boardWriteEmail').val()+'@'+$('#boardWriteEmail2').val());													
			$("#Content").val($('#boardWriteContent').val());						
			$("#affTpCd").val($("#boardWriteSort input").val());
			$("#inputfileNm").val(lastFileName);
			$("#inputfileUrl").val(lastPath);
			$("#inputfileSize").val(lastFileSize);
			return true;					
		}else{
			return false;	 
		}	
	}
</script>