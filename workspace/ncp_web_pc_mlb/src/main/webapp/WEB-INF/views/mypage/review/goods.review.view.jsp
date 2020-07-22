<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/jquery.form.js"></script>

<div class="contain my lnblist-Wrap" id="contain">
	<div class="container">

		<h2 class="title01">상품리뷰</h2>

		<%@ include file="../include/lnb.jspf" %>

		<main class="contents goods-review-wrap" id="contents">

			<div class="location-contents">
				<p class="location">
					<span>Home</span>
					<span>마이페이지</span>
					<span>활동정보</span>
					<strong title="현재 위치">상품리뷰</strong>
				</p>
			</div>

			<p class="txt13-666">리뷰 작성 시, 온라인몰에서 사용 가능한 포인트를 적립해 드립니다. (텍스트리뷰 300포인트, 포토리뷰 1,000포인트 적립)</p>

			<div class="d_tab02">

				<!-- 상품리뷰 tab S -->
				<ul class="tab-type01 tab-blockList blockList02">
					<li class="d_tab02_select on"><a href="javascript:;">상품리뷰 작성</a></li>
					<li class="d_tab02_select"><a href="javascript:;">작성한 상품리뷰</a></li>
				</ul>
				<!--// 상품리뷰 tab E -->

				<!-- 상품리뷰 작성 S -->
				<div class="d_tab02_cont" style="display:block;" id="goodsNoReviewList"></div>
				<!--// 상품리뷰 작성 E -->

				<!-- 작성한 상품리뷰 S -->
				<div class="d_tab02_cont" id="goodsReviewListCont">
					<dl class="period-wrap period-type02">
						<!-- 기간달력 -->
						<dt>조회기간</dt>
						<dd>
							<a href="javascript:;" id="review_month3" onclick="setMonth(3);" class="btn sm d_radio_select on"><span><span>3개월</span></a>
							<a href="javascript:;" id="review_month6" onclick="setMonth(6);" class="btn sm d_radio_select"><span>6개월</span></a>
							<input type="text" class="calendar" id="calendarFrom" readonly><input type="text" class="calendar" id="calendarTo" readonly>
							<a href="javascript:;" class="btn sm" onclick="javascript:searchReviewList();">검색</a>
							<p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p><!-- 2018-08-13 -->
						</dd>
					</dl>
					<div id="goodsReviewList"></div>
				</div>
				<!--// 작성한 상품리뷰 E -->

			</div>

			<div class="contTxtBox">
				<strong>유의사항</strong>
				<ul class="text-list01">
					<li>구매확정하신 상품에 대해서만 리뷰를 작성하실 수 있으며, 구매확정 후 90일이 지난 상품에 대해서는 리뷰를 작성하실 수 없습니다.</li>
					<li>[작성한 상품 리뷰] 페이지에서 작성하신 상품 리뷰를 확인할 수 있습니다.</li>
					<li>상품과 직접적으로 관계가 없는 내용이나 약관 및 법률 등에 위배되는 글은 고객님께 사전 동의없이 미노출될 수 있습니다.</li>
					<li>다음과 같은 경우 리뷰가 제한되거나 관리자의 권한으로 삭제될 수 있습니다.
						<ul class="text-list02">
							<li>상품과 적합하지 않은 콘텐츠나 무관한 사진</li>
							<li>사이트 내 게시된 상품 및 전시 이미지 그대로 사용</li>
							<li>타 회원의 리뷰 도용</li>
							<li>내용이 부적합하거나 비속어 사용</li>
						</ul>
					</li>
					<li>리뷰 작성 후, 적립된 포인트는 온라인 쇼핑몰에서만 사용가능하며, 2년 간 유효합니다. 2년이 경과된 포인트는 익월 1일에 소멸됩니다.
						<a href="/mypage/benefit/listPoint" class="text-color01">포인트 확인하러 가기</a></li>
				</ul>
			</div>

		</main>

	</div>
</div>

<article id="layerPopupAddReview" class="layer-popup photoReview-pop"></article>
<article id="layerPopupEditReview" class="layer-popup photoReview-pop"></article>

<script>

//상품 리뷰 목록 조회
getNoReviewList('1');
getReviewList('1');

var agent = navigator.userAgent.toLowerCase();

$(document).ready(function() {

	if($("#review_dateStart").val() == "" || $("#review_dateEnd").val() == ""){
		setMonth(3);
	}
	if($("#review_srchMonth").val() != ""){
		setMonth($("#review_srchMonth").val());
	}
	if(($("#review_dateStart").val() != "" && $("#review_dateEnd").val() != "") && $("#review_srchMonth").val() == ""){
		$("#calendarFrom").val($("#review_dateStart").val());
		$("#calendarTo").val($("#review_dateEnd").val());
	}

	var dateFormat = "yy.mm.dd",
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
		$("#review_month3").removeClass("on");
		$("#review_month6").removeClass("on");
		$("#review_srchMonth").val("");
		return date;
	}

});

/**
 * 상품 리뷰 작성 가능한 목록 조회
 */
function getNoReviewList(pageNo){
	$.ajax({
		type : "POST",
		async : true,
		url : "/mypage/goods/listNoReview",
		data : {'pageNo':pageNo},
		success : function(data) {
			$('#goodsNoReviewList').html(data);
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});
}

/**
 * 상품 리뷰 작성한 목록 조회
 */
function getReviewList(p_pageNo){
	var p_dateStart = $("#review_dateStart").val();
	var p_dateEnd = $("#review_dateEnd").val();
	var p_srchMonth = $("#review_srchMonth").val();
	$.ajax({
		type : "POST",
		async : true,
		url : "/mypage/goods/listReview.ajax",
		data : {'pageNo':p_pageNo, 'dateStart':p_dateStart, 'dateEnd':p_dateEnd, 'srchMonth':p_srchMonth},
		success : function(data) {
			$('#goodsReviewList').html(data);
		},
		error: function(pa_data, status, err) {
			if (pa_data.status == "403") {
				alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                location.reload();
			} else {
            	alert("error forward : "+err+" ,status="+status);
			}
        }
	});
}

function setMonth(val){
	// TODO : 달력 스크립트에서 날짜 넣는 형식 확인.
	val = val || 3;
	
	var addMonth = 0;
	if (val == 3) {
		addMonth = -3;
	}
	else if (val == 6) {
		addMonth = -6;
	}

	var today = moment().format('YYYY.MM.DD');
	var fromDate = moment().add(addMonth, 'months').format('YYYY.MM.DD');

	$("#calendarFrom").val(fromDate);
	$("#calendarTo").val(today);
	$("#review_dateStart").val($("#calendarFrom").val());
	$("#review_dateEnd").val($("#calendarTo").val());
}


function specialTextCheck(id){
    var hdivCheck = /.*[<>/\\￦:&]+.*/gm;
    if (hdivCheck.test($('#'+id).val())) {
        return false;
    }
}

function searchReviewList(){
	if($("#calendarFrom").val() == "" || $("#calendarTo").val() == ""){
		if($("#review_srchMonth").val() != ""){
			setMonth($("#review_srchMonth").val());
		}else{
			alert("검색 시작일과 종료일을 입력해 주세요.");
			//검색 시작일과 종료일을 입력해 주세요.
			return false;
		}
	}
	$("#review_dateStart").val($("#calendarFrom").val());
	$("#review_dateEnd").val($("#calendarTo").val());
	getReviewList('1');
}

/**
 *  상품리뷰 삭제
 */
function deleteReview(ordNo, godNo, godEvlTurn ) {
    var dlvMsg = "삭제하시겠습니까?";

    if(!confirm(dlvMsg)) {
        return false;
    }

    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'ordGod.ordNo' : ordNo , 'ordGod.godNo' :  godNo, 'godGodEvl.godEvlTurn' :  godEvlTurn};

	$.ajax({
		type : "POST",
		async : true,
		url : "/mypage/goods/deleteGoodsReview.json",
		data : strParams,
		success : function(data) {
		//	alert("삭제되었습니다.");
			//삭제되었습니다.
			getReviewList('1');
			//location.reload();
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});
}

function getLayerPopupAddReview(ordNo, ordGodTurn, godNo, photoYn) {
    var strParams = null;
    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'ordGod.ordNo' : ordNo , 'ordGod.ordGodTurn' :  ordGodTurn, 'ordGod.godNo' :  godNo, 'photoYn' : photoYn};
	$("#layerPopupAddReview").load("<c:url value='/mypage/goods/addReview.popup'/>", strParams);
    layerPopup.popupOpenNow("#layerPopupAddReview");
}

function getLayerPopupEditReview(ordNo, ordGodTurn, godNo, godEvlTurn, photoYn) {
    var strParams = null;
    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'ordGod.ordNo' : ordNo , 'ordGod.ordGodTurn' :  ordGodTurn, 'ordGod.godNo' :  godNo, 'godGodEvl.godEvlTurn' : godEvlTurn, 'photoYn' : photoYn};
	$("#layerPopupEditReview").load("<c:url value='/mypage/goods/editReview.popup'/>", strParams);
    layerPopup.popupOpenNow("#layerPopupEditReview");
}

/**
 *  구매확정처리
 */
function jsUpdateOrderDecision(ordNo, ordGodTurn ) {
    var dlvMsg = "구매확정하시면 마일리지가 적립되며 반품 및 교환이 불가합니다.<br/>구매확정하시겠습니까?";
    //구매를 확정 처리하시겠습니까?

    if(!confirm(dlvMsg)) {
        return false;
    }

    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'ordNo' : ordNo, 'ordGodTurn' : ordGodTurn};

	$.ajax({
		type : "POST",
		url  : "<c:url value='/mypage/order/decision/update.json'/>",
		data : strParams,
		success : function(data) {
			//alert("구매가 확정 되었습니다.");
			//구매가 확정 되었습니다.
			location.reload();
		},
		error : function(jqXHR, textStatus, error) {
			$('.loading').hide();
			alert(jqXHR.responseJSON.message);
	    }
	});
}

/* 상품리뷰 등록하기 */
function addReviewPopupFormSubmit(){
	var submtchk = 1;
	/* 특수문자 체크  */
	if(specialTextCheck("godEvlCont") == false){
		alert("특수문자는 입력하실 수 없습니다.");
		//특수문자 ￦/ : < > 는 사용할 수 없습니다.
		return false;
	}

	/* 별점,사이즈,컬러 입력 필수값으로 수정 */
	var	qltyEvlScore=$('#starScore').val();
	var radioSize =$('input[name="godGodEvl.sizeEvlCd"]').is(":checked");
	var radioColor = $('input[name="godGodEvl.colorEvlCd"]').is(":checked");

	if(!qltyEvlScore){
		alert('품질에 대해 평가해 주세요');
		return false;
	}
	if(!radioSize){
		alert('사이즈에 대해 평가해 주세요');
		return false;
	}
	if(!radioColor){
		alert('컬러에 대해 평가해 주세요.');
		return false;
	}
	var godEvlCont = "";
	if(agent.indexOf("msie") > -1 ){    //IE version
		godEvlCont = $("#godEvlCont").text();
	}else{    //other browser
		godEvlCont = $("#godEvlCont").val();
	}
	if(godEvlCont == null || godEvlCont == ""){
		alert("상품리뷰를 입력해 주세요.");
		return false;
	}else if(godEvlCont.length < 5){
		alert("최소 5자 이상 입력하셔야 합니다.");
		return false;
	}else if(godEvlCont.length > 1000){
		alert("더 이상 입력하실 수 없습니다.");
		return false;
	}
	if($("#photoYn").val() == 'Y'){
		if($("#file1").val() == "" && $("#file2").val() == "" && $("#file3").val() == ""){
			alert("사진을 하나 이상 등록하셔야 합니다.");
			return false;
		}
	}
/* 	if(!confirm("<spring:message code='mypage.goods.review.msg.alert6'/>")){
		//리뷰를 등록하시겠습니까?
		return false;
	} */
	var message = godEvlCont;
	var url = '/mypage/goods/addReviewAction.json';
	var form = $("#addReviewPopupForm");
	submtchk ++;
	if (submtchk == 2){
		form.ajaxSubmit({
			url:url,
			mimeType:"multipart/form-data",
			type:"POST",
			async:false,
			clearForm:true,
			beforeSubmit: function (data,form,option) {
			},
			success: function(response, status){
				//alert("<spring:message code='mypage.goods.review.msg.alert7'/>");
//				alert('등록되었습니다.');
				//location.reload();
//				$("#layerPopupAddReview").hide();
//				$('#container').off('scroll touchmove mousewheel');
//				$("#layerPopupAddReview").html("");
//				getNoReviewList('1');
//				getReviewList('1');
//				$("#tab_noreview").removeClass("on");
//				$("#tab_review").addClass("on");
//				$("#goodsNoReviewList").hide();
//				$("#goodsReviewListCont").show();

				//alert('등록되었습니다.');
				
				getNoReviewList('1');
				getReviewList('1');
				
				$('.d_tab02 .d_tab02_select:nth-child(2) a').click()
				$('#layerPopupAddReview .d_layer_close').click();
				$("#layerPopupAddReview").html("");
				
				submtchk=1;
			},
			error: function(e){
				alert("상품리뷰 등록시 오류가 발생하였습니다.");
			}
		});
	}
}

/* 상품리뷰 수정하기 */
function editReviewPopupFormSubmit(){
	var submtchk = 1;
	/* 특수문자 체크  */
	if(specialTextCheck("godEvlCont") == false){
		alert("특수문자는 입력하실 수 없습니다.");
		return false;
	}

	/* 별점,사이즈,컬러 입력 필수값으로 수정 */
	var	qltyEvlScore=$('#starScore').val();
	var radioSize =$('input[name="godGodEvl.sizeEvlCd"]').is(":checked");
	var radioColor = $('input[name="godGodEvl.colorEvlCd"]').is(":checked");

	if(!qltyEvlScore){
		alert('품질에 대해 평가해 주세요');
		return false;
	}
	if(!radioSize){
		alert('사이즈에 대해 평가해 주세요');
		return false;
	}
	if(!radioColor){
		alert('컬러에 대해 평가해 주세요.');
		return false;
	}
	var godEvlCont = "";
	if(agent.indexOf("msie") > -1 ){    //IE version
		godEvlCont = $("#godEvlCont").text();
	}else{    //other browser
		godEvlCont = $("#godEvlCont").val();
	}
	if(godEvlCont == null || godEvlCont == ""){
		alert("상품리뷰를 입력해 주세요.");
		return false;
	}else if(godEvlCont.length < 5){
		alert("최소 5자 이상 입력하셔야 합니다.");
		return false;
	}else if(godEvlCont.length > 1000){
		alert("더 이상 입력하실 수 없습니다.");
		return false;
	}
	if($("#photoYn").val() == 'Y'){
		if($("#nmfile1").val() == "" && $("#nmfile2").val() == "" && $("#nmfile3").val() == ""){
			if($("#file1").val() == "" && $("#file2").val() == "" && $("#file3").val() == ""){
				alert("사진을 하나 이상 등록하셔야 합니다.");
				return false;
			}
		}
	}
/* 	if(!confirm("<spring:message code='mypage.goods.review.msg.alert11'/>")){
		//리뷰를 수정하시겠습니까?
		return false;
	} */
	var message = godEvlCont;
	var url = '/mypage/goods/editReviewAction.json';
	var form = $("#editReviewPopupForm");
	submtchk ++;
	if (submtchk == 2){
		form.ajaxSubmit({
			url:url,
			mimeType:"multipart/form-data",
			type:"POST",
			async:false,
			clearForm:true,
			beforeSubmit: function (data,form,option) {
			},
			success: function(response, status){
				//alert("수정되었습니다.");
				//location.reload();
				
				getReviewList('1');
				
				$('#layerPopupEditReview .d_layer_close').click();
				$("#layerPopupEditReview").html("");
				
				submtchk=1;
			},
			error: function(e){
				alert("상품리뷰 수정시 오류가 발생하였습니다.");
			}
		});
	}
}

//별점
$('#layerPopupAddReview, #layerPopupEditReview').on('click', '.starRev a', function() {
    $(this).parent().children('a').removeClass('on');
    $(this).addClass('on').prevAll('a').addClass('on');
    return false;
});
</script>