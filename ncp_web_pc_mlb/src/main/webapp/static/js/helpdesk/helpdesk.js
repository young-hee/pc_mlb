
$(document).ready(function(){



	//매장찾기
	$("#btn_findStore").click(function(){
		layerPopup.popupOpenNow("#layerPopupMap");
		availableStore();
	});
	$("#searchStoreKeyword").keypress(function(e){
		if(e.keyCode == 13){
			availableStore();
		}
	});




});
//파일첨부
var  filepath;
var  inputPath;
var  lastPath;
var  lastFileName;
var	 fileSize;
var  lastFileSize;
//유효성
var emailValidation =  /^[A-Za-z0-9\_\*\!\.\+\=\-\)\(\&\%\~\)\^]*$/;
var email2Validation = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

var phoneValidation = /^01([0|1|6|7|8|9])$/;
var phone2Validation = /^\d{3,4}$/;
var phone3Validation = /^\d{4}$/;


var hlpValidate = {

	//----------------------------emailSelectBox 넣었을시  유효성 확인 시작 --------------------------
	//                       이메일첫번째id, 이메일두번째id,  밑에 문구id,이메일선택박스 '직접입력'명,플래그 넘
	emailSelectBox: function(emailId,emailId2,emailMsg,defaultBox,flagnum){
		if(emailValidation.test($("#"+emailId).val()) && email2Validation.test($("#"+emailId2).val()) ){
			$("#"+emailMsg).html('');
			flag[flagnum].flagYn='true';
		}
		if($("#"+emailId2).val() == defaultBox){
		 	$("#"+emailId2).val("");
		}

		setMessage(emailMsg, flag[flagnum].flagYn);
	},
	//----------------------------emailSelectBox 넣었을시  유효성 확인 끝 --------------------------
	//----------------------------email 넣었을시  유효성 확인 시작 --------------------------
	// 						이메일첫번째id, 이메일두번째id,밑에 문구id,플래그 넘
	emailValidation: function(emailId,emailId2,emailMsg,flagnum){
		if($("#"+emailId).val() != "" && $("#"+emailId2).val() != "" && $.trim($("#"+emailId2).val()) != "" && $.trim($("#"+emailId).val()) != ""){
			if(emailValidation.test($("#"+emailId).val()) && email2Validation.test($("#"+emailId2).val())){
				$("#"+emailMsg).html('');
				flag[flagnum].flagYn='true';
			}else{
				$("#"+emailMsg).html(MESSAGES['helpdesk.js.common.wrongemail.txt']);// 잘못된 형식의 이메일 입니다.
				flag[flagnum].flagYn='false';
			}
		}else{
	   		$("#"+emailMsg).html(MESSAGES['helpdesk.js.common.emptyemail.txt']); // 메일은 필수입력 입니다.
	   		flag[flagnum].flagYn='false';
		}
		setMessage(emailMsg, flag[flagnum].flagYn);
	},

	//----------------------------email 넣었을시  유효성 확인 끝--------------------------
	//----------------------------연락처  넣었을시  유효성 확인 시작--------------------------
	//  			 	     폰  첫번째id,폰  두번째id,폰  세번째id,밑에 문구id,플래그 넘
	phoneValidation: function(phone1,phone2,phone3,phoneMsg,flagnum){
		if($("#"+phone1).val() != "" && $("#"+phone2).val() != "" &&  $("#"+phone3).val() != "" ){
			if(phoneValidation.test($("#"+phone1).val()) && phone2Validation.test($("#"+phone2).val())
					&& phone3Validation.test($("#"+phone3).val())){
				$("#"+phoneMsg).html('');
				flag[flagnum].flagYn='true';
			}else{
				$("#"+phoneMsg).html(MESSAGES['helpdesk.js.common.wrongcontact.txt']);//잘못된 형식의 연락처 입니다. 확인 후 다시 입력해주세요.
				flag[flagnum].flagYn='false';
			}
		}else{
			$("#"+phoneMsg).html(MESSAGES['helpdesk.js.common.emptycontact.txt']);//연락처는 필수입력 입니다.
			flag[flagnum].flagYn='false';
		}
		setMessage(phoneMsg, flag[flagnum].flagYn);
	},

	//----------------------------연락처 넣었을시  유효성 확인 끝--------------------------
	//----------------------------내용 넣었을시  유효성 확인 시작--------------------------
	//					내용textarea id,  내용 최대글자수,   밑에 문구id, 플래그 넘
	contentValidation: function(contentId,contentLength,contentMsg,flagnum){
		var spcialValidation = /[\/\\\<:>\;]/gi;
		var contentTest = $("#"+contentId).val();

		if($("#"+contentId).val() != "" && $.trim($("#"+contentId).val()) != ""){
			if($("#"+contentId).val().length < contentLength){
				if((spcialValidation.test(contentTest)) == false ){
					$("#"+contentMsg).html('');
					flag[flagnum].flagYn='true';
				}else{
					$("#"+contentMsg).html(MESSAGES['helpdesk.js.common.wrongorg.txt']);//특수문자를 사용할수 없습니다.
					flag[flagnum].flagYn='false';
				}
			}else{
				$("#"+contentMsg).html(MESSAGES['helpdesk.js.common.capacont.txt']);//내용은 1000자 미만 입니다.
				flag[flagnum].flagYn='false';
			}
		}else{
			$("#"+contentMsg).html(MESSAGES['helpdesk.js.common.emptycont.txt']);//내용은 필수입력 입니다.
			flag[flagnum].flagYn='false';
		}
		setMessage(contentMsg, flag[flagnum].flagYn);
	},
	//----------------------------내용 넣었을시  유효성 확인 끝--------------------------
	//----------------------------제목 넣었을시  유효성 확인 시작--------------------------
	//					내용textarea id,  내용 최대글자수,   밑에 문구id, 플래그 넘
	subjectValidation: function(subjectId,subjectLength,subjectMsg,flagnum){
		var spcialValidation = /[\/\\\<:>\;]/gi;
		var contentTest = $("#"+subjectId).val();

		if($("#"+subjectId).val() != "" && $("#"+subjectId).val() != null && $.trim($("#"+subjectId).val()) != ""){
			if($("#"+subjectId).val().length < subjectLength){
				if((spcialValidation.test(contentTest)) == false ){
					$("#"+subjectMsg).html('');
					flag[flagnum].flagYn='true';
				}else{
					$("#"+subjectMsg).html(MESSAGES['helpdesk.js.common.wrongorg.txt']);//특수문자\/:<>는 사용할수 없습니다.
					flag[flagnum].flagYn='false';
				}
			}else{
				$("#"+subjectMsg).html(MESSAGES['helpdesk.js.common.capasub.txt']);//제목은 30자까지 입력 가능합니다.
				flag[flagnum].flagYn='false';
			}
		}else{
			$("#"+subjectMsg).html(MESSAGES['helpdesk.js.common.emptysub.txt']);//제목은 필수입력 입니다.
			flag[flagnum].flagYn='false';
		}
		setMessage(subjectMsg, flag[flagnum].flagYn);
	},
	//----------------------------제목 넣었을시  유효성 확인 끝--------------------------
	//----------------------------단체명 넣었을시  유효성 확인 끝--------------------------
	//					단체명 id,  	내용 최대글자수,   밑에 문구id, 플래그 넘
	orgNameValidation: function(orgNameId,orgNameLength,orgNameMsg,flagnum){
		var spcialValidation = /[\/\\\<:>\;]/gi;
		var contentTest = $("#"+orgNameId).val();

		if($("#"+orgNameId).val() != "" && $.trim($("#"+orgNameId).val()) != ""){
			if($("#"+orgNameId).val().length < orgNameLength){
				if((spcialValidation.test(contentTest)) == false ){
					$("#"+orgNameMsg).html('');
					flag[flagnum].flagYn='true';
				}else{
					$("#"+orgNameMsg).html(MESSAGES['helpdesk.js.common.wrongorg.txt']);//특수문자\/:<>는 사용할수 없습니다.
					flag[flagnum].flagYn='false';
				}
			}else{
				$("#"+orgNameMsg).html(MESSAGES['helpdesk.js.common.capaorg.txt']);//단체명은 20자 미만 입니다.
				flag[flagnum].flagYn='false';
			}
		}else{
			$("#"+orgNameMsg).html(MESSAGES['helpdesk.js.common.emptyorg.txt']);//단체명을 입력해 주세요.
			flag[flagnum].flagYn='false';
		}
		setMessage(orgNameMsg, flag[flagnum].flagYn);
	},

	//----------------------------단체명 넣었을시  유효성 확인 끝--------------------------
	//----------------------------마지막 유효성 플래그 테스트--------------------------
	flagTest: function(){
		//var temp =0;
		for(var i in flag){
			if(flag[i].flagYn == 'false'){
				document.getElementById("pageTop").scrollIntoView();
				return ;
				//temp += 1;
			}
		}
		return true;
	},
	//----------------------------마지막 유효성 플래그 테스트--------------------------

	//<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<공통>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//							이름류 빈칸, 글자수 유효성
	//                      단체명 id, 내용 최대글자수,밑에 문구id, 플래그 넘,출력 메세지
	nameValidation: function(nameId,nameLength,nameMsg,flagnum,emptyOutputMsg,overflowOutputMsg){

		if($("#"+nameId).val() != "" && $("#"+nameId).val() != null && $.trim($("#"+nameId).val()) != ""){
			if($("#"+nameId).val().length < nameLength){
				$("#"+nameMsg).html('');
				flag[flagnum].flagYn='true';
			}else{
				$("#"+nameMsg).html(overflowOutputMsg);
				flag[flagnum].flagYn='false';
			}
		}else{
			$("#"+nameMsg).html(emptyOutputMsg);
			flag[flagnum].flagYn='false';
		}
		setMessage(nameMsg, flag[flagnum].flagYn);
	},
};//hlpValidate

function setMessage(msgId, show) {
	if (show == 'false') {
		$('#' + msgId).css('display', 'block');
	} else {
		$('#' + msgId).css('display', 'none');
	}
}

/* ********************************************/
/* 매장찾기                                				  	  */
/* ********************************************/
function availableStore(){
	var p_sidoCd = $("#searchStoreSidoCd").val();
	var p_srchKeyword = $("#searchStoreKeyword").val().trim();
	$.ajax({
		type : "POST",
		async : false,
		url : "/goods/content/getGoodsShopList.json",
		data : {sidocd:p_sidoCd, srchKeyword:p_srchKeyword},
		success : function(data) {
			if(data.totalCnt > 0){
				$(".store-list-wrap").find(".no-result").hide();
				$(".store-list-wrap").find(".store-list ul.main-ul").empty();
				var el = "", shopNm, baseAddr, telNo, telNo1, telNo2, telNo3, hour, shour, ehour, lttd, lgtd;
				var map = new google.maps.Map(document.getElementById("map"), {
				    zoom: 8,
				    center: {lat:37.532, lng:127.024}, //대한민국
				});
				for(var i=0; i<data.shopList.length; i++){
					el="<li>";
					shopNm = data.shopList[i].shopNm;
					baseAddr = data.shopList[i].baseAddr;
					telNo1 = data.shopList[i].shopTelAreaNo;
					telNo2 = data.shopList[i].shopTelTlofNo;
					telNo3 = data.shopList[i].shopTelTlofWthnNo;
					shour = data.shopList[i].bsnBegHhmm;
					ehour = data.shopList[i].bsnEndHhmm;
					lttd = data.shopList[i].lttd;
					lgtd = data.shopList[i].lgtd;

					if(typeof(shopNm) === "undefined"){shopNm = "";}
					if(typeof(baseAddr) === "undefined"){baseAddr = "";}
					if(typeof(telNo1) === "undefined" || typeof(telNo2) === "undefined" || typeof(telNo3) === "undefined"){telNo = "";}else{telNo=telNo1+"-"+telNo2+"-"+telNo3;}
					if(typeof(shour) === "undefined" || typeof(ehour) === "undefined"){hour = "";}else{hour=shour+"~"+ehour;}
					el+="<div class=\"store-name\">"+shopNm+"</div>";
					el+="<ul class=\"store-address-info\">";
					el+="<li><span>주소</span>"+baseAddr+"</li>";
					el+="<li><span>전화번호</span>"+telNo+"</li>";
					el+="<li><span>영업시간</span>"+hour+"</li></ul>";
					el+="</li>";
					$(".store-list-wrap").find(".store-list ul.main-ul").append(el);

					if(typeof(lttd) !== "undefined" && typeof(lgtd) !== "undefined"){
						   var  marker = new google.maps.Marker({
						    position: {lat: lttd, lng: lgtd},
						    map: map,
						    title:shopNm
						});
					}
				}
				$(".store-list-wrap").find(".store-list").show();
				$(".store-list-wrap").find(".store-map").show();
			}else{
				$(".store-list-wrap").find(".store-list").hide();
				$(".store-list-wrap").find(".store-map").hide();
				$(".store-list-wrap").find(".no-result").text("검색된 목록이 없습니다.");
				$(".store-list-wrap").find(".no-result").show();
			}
		},
		error: function( pa_data, status, err ) {
            alert("error forward : "+err+" ,status="+status);
        }
	});
}

function setSidoCdSearchStore(sidoCd){
	$("#searchStoreSidoCd").val(sidoCd);
}

//파일첨부 유효성
function filetest(fis){

	if( $("#fileSearch").val() != "" ){
		//파일 사이즈
		var fileSize = document.getElementById("fileSearch").files[0].size;
		var maxSize = 10 * 1024 * 1024;
		//파일 확장자
		var ext = $('#fileSearch').val().split('.').pop().toLowerCase();
	      if($.inArray(ext, ['gif','png','jpg','jpeg','hwp','dox','docx','xls','xlsx','ppt','pptx','pdf']) == -1) {
	    	  alertLayer(MESSAGES['helpdesk.js.common.limitfile.txt']);//gif,png,jpg,jpeg,hwp,dox,docx,xls,xlsx,ppt,pptx 파일만 업로드 할수 있습니다.
	      }else if(fileSize > maxSize){
	    	  alertLayer(MESSAGES['helpdesk.js.common.overflowcapa.txt']);//파일용량이 10MB를 초과했습니다.
	      }else{
	    	  var form = $("#gForm"),
	  		  url = "/helpdesk/fileUploadHelpDesk.json";
	  		  fileFormSubmit(form, url, fis);
	      }
	}

}
//파일 네임 삭제
function fileDelete(){
//	if(confirm(MESSAGES['helpdesk.js.common.canclefile.txt'])==true){//첨부파일을 삭제하시겠습니까?
//		$('.file-name').html('');
//    	$('#fileSearch').removeAttr("disabled");
//
//		 //인풋 히든을 만들어  벨류를 빼야함
//    	 $("#fileSearch").val("");
//	}else{
//		return;
//	}
	$('#confirmBtn').val('file');
	confirmLayer(MESSAGES['helpdesk.js.common.canclefile.txt']);
}
//파일첨부
function fileFormSubmit(form, url, fis){
	var method = "POST";
	form.ajaxSubmit({
    	url: url,
    	mimeType:"multipart/form-data",
    	type:"post",
    	dataType: 'json',
    	//clearForm:true,
    	async : true,
	    error : function(xhr, status){
            if(xhr.status == 503){
                var data = $.parseJSON(xhr.responseText)
                alert(data.message);
            }
            if(xhr.status == 400){
            	alertLayer(MESSAGES['helpdesk.js.common.wrongfilename.txt']);//파일 내용이 잘못되었습니다.
            }
            if(xhr.status == 404){
            	alert('Requested URL not found.');
            }
			if(xhr.status == 500){
				alertLayer(MESSAGES['helpdesk.js.common.cantupload.txt']);//파일을 업로드할 수 없습니다.
            }
	    },
	    success: function(response) {

	    	var str = fis.value;
	    	var fileName = document.getElementById("fileSearch").value.substring(str.lastIndexOf("\\")+1);
	    	///content/helpdesk/34874606952265.xlsx
	    	//var data = window.JSON.parse(response);
	    	console.log(typeof response);
	    	console.log(response.result);
	    	 filepath = response.result.rows[0].filePath;
	    	 inputPath = filepath.split('/');
	    	 lastPath =  inputPath[3]+"/"+inputPath[4]+"/"+response.result.rows[0].fileName;
	    	 //lastFileName	=  response.result.rows[0].fileName; 숫자로 변환된 파일네임
	    	 lastFileName = fileName;
	    	 lastFileSize = document.getElementById("fileSearch").files[0].size;
	    	$('.file-name').html(fileName + "<a href='#' onclick='javascript:fileDelete();' class='btn-file-delete'>삭제</a>");
	    	$('#fileSearch').attr("disabled","true");

	    },
	    complete : function(xhr, status) {
	    	//form.children().find(':file').val('');
	    	//form.children().find('.input-styling').text('');
	    }
	});

}

function callbackConfirmLayer(flag){
	// file =파일네임 삭제 , cancle 등록취소 , save 저장 , myPageCancle 등록취소후 마이페이지 이동
	if($('#confirmBtn').val()=="cancle"){// 취소 누를시
		if(flag){
			closeConfirmLayer();
			location.href="/helpdesk/";

		}else{
			closeConfirmLayer();
			return;

		}
	}else if($('#confirmBtn').val()=="save"){
		if(flag){
			closeConfirmLayer();
			$('#gForm').submit();
		}else{
			closeConfirmLayer();
			return;
		}
	}else if($('#confirmBtn').val()=="file"){
		if(flag){
			closeConfirmLayer();
			$('.file-name').html('');
	    	$('#fileSearch').removeAttr("disabled");

			 //인풋 히든을 만들어  벨류를 빼야함
	    	 $("#fileSearch").val("");

	    	 $('#inputfileNm').val('');
	    	 $('#inputfileUrl').val('');
	    	 $('#inputfileSize').val('');
		}else{
			closeConfirmLayer();
			return;
		}
	}else if($('#confirmBtn').val()=="myPageCancle"){
		if(flag){
		closeConfirmLayer();
		location.href="/mypage/inquiry/list";
		}else{
		closeConfirmLayer();
		}
	}
	/* 1:1문의수정 추가 2018.08.09 ryan Start */
	else if($('#confirmBtn').val()=="cancleModefy"){
		if(flag){
			closeConfirmLayer();
			var srchMtmInqSn = $('#srchMtmInqSn').val();

			//location.href="/mypage/inquiry/detail/"+srchMtmInqSn;

			var param = "";
            param += "?" + "dspCtgryNo=DMPA01A02A05";	// 메뉴 유지를 위한 데이터
            param += "&" + "currentCtgryDpthCd=3";		// 메뉴 유지를 위한 데이터
            param += "&" + "ctgrySectCd=GNRL_CTGRY";	// 메뉴 유지를 위한 데이터
            param += "&" + "ctgryNoDpth1=DMPA01";		// 메뉴 유지를 위한 데이터
       	    param += "&" + "ctgryNoDpth2=DMPA01A02";	// 메뉴 유지를 위한 데이터
       	    param += "&" + "ctgryNoDpth3=DMPA01A02A05";	// 메뉴 유지를 위한 데이터

       		location.href ='/mypage/inquiry/detail/'+ srchMtmInqSn + param;
		}else{
			closeConfirmLayer();
		}
	}else if($('#confirmBtn').val()=="Modefy"){
		if(flag){
			closeConfirmLayer();
			$('#gForm').submit();
		}else{
			closeConfirmLayer();
		}
	}
	/* 1:1문의수정 추가 2018.08.09 ryan End */

}


