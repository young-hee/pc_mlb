<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" id="srchMonth" name="srchMonth" value="${myPage.srchMonth}">
<input type="hidden" name="pageNo"  id="pageNo" value=""/>

<div class="contain my lnblist-Wrap" id="contain">
	<div class="container">

		<h2 class="title01">마일리지</h2>

		<%@ include file="../include/lnb.jspf" %>

		<main class="contents mileageList-wrap" id="contents">

			<div class="location-contents">
				<p class="location">
					<span>Home</span>
					<span>마이페이지</span>
					<span>혜택정보</span>
					<strong title="현재 위치">마일리지</strong>
				</p>
			</div>

			<!-- 마일리지 현황S -->
			<div class="memberSTcont boxCont03">
				<ul>
					<li>
						<p>사용 가능 마일리지</p>
						<strong id="nowPoint"><!-- <span class="text-color01">25,000</span>원 --></strong>
					</li>
					<li>
						<p>적립 예정 마일리지</p>
						<strong id="useTempPoint"><!-- <span class="text-color01">5,000</span>원 --></strong>
					</li>
					<li>
						<p>당월 소멸 예정 마일리지</p>
						<strong id="eliminatePoint"><!-- <span class="text-color01">100</span>원 --></strong>
					</li>
				</ul>
			</div>
			<!-- //마일리지 현황E -->

			<%-- <%@ include file="../_inc/uiDateRange2.jsp" %> --%>
			<dl class="period-wrap period-type02">
				<!-- 기간달력 -->
				<dt>조회기간</dt>
				<dd>
					<a href="javascript:;" class="btn sm d_radio_select on" onclick="setMonth('3month');"><span>3개월</span></a>
					<a href="javascript:;" class="btn sm d_radio_select" onclick="setMonth('6month');"><span>6개월</span></a>
					<input type="text" class="calendar" id="dateStart" value="${myPage.dateStart}" readonly><input type="text" class="calendar" id="dateEnd" value="${myPage.dateEnd}" readonly>
					<a href="javascript:;" class="btn sm" onclick="searchMileageList();return false;">검색</a>
					<p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p><!-- 2018-08-13 -->
				</dd>
			</dl>

			<!-- table info S -->
			<div class="tbst-div">
				<div class="mid fl">
					<span>전체</span>
						(<span class="text-color01" id="mileageCnt"><!-- 100 --></span>건)
				</div>
				<div class="mid fr">
					<a href="#layerPopupMemMile" class="btn fill sm d_layer_open"><span>멤버십 마일리지 카드 등록</span></a>
				</div>
			</div>
			<!-- //table info E -->

			<!-- table S -->
			<div id="includeMileageList">
				<%--마일리지 내역 --%>
			</div>
			<!-- //table E -->

			<%-- <%@ include file="../_inc/paging.jsp" %> --%>

			<!-- 마일리지 적립 사용안내 영역S -->
			<div class="contTxtBox">
				<strong>마일리지 적립 / 사용</strong>
				<ul class="text-list01">
					<li>마일리지 적립 기준은 <a href="/mypage/benefit/membershipRateInfo" class="text-color01">회원혜택안내</a>에서 확인하세요.</li>
					<li>주문 결제 시 5,000원 이상 1,000원 단위로 사용 가능 합니다.</li>
					<li>현금으로 환원되지 않는 비현금성 서비스 입니다.</li>
					<li>주문 시 구매한 상품 가격에 비례하여 분할 적용되며 부분 취소/반품 시 분할 환원 됩니다.</li>
				</ul>
				<strong>마일리지 유효기간</strong>
				<ul class="text-list01">
					<li>적립일로부터 2년으로 사용하지 않은 마일리지는 자동으로 소멸됩니다.</li>
				</ul>
			</div>
			<!-- //마일리지 적립 사용안내 영역E -->

		</main>

	</div>
</div>

<!-- layerpopup - 멤버십 마일리지 카드 등록 -->
<article id="layerPopupMemMile" class="layer-popup mileage-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>멤버십 마일리지 카드 등록</h2>
		<div class="layer-cont scroll">

			<div class="mileage-popWrap">

				<ul class="text-list02">
					<li>멤버십 카드 등록으로 매장에서 적립한 마일리지를  온라인에서 사용하세요.</li>
				</ul>

				<!-- 검색S -->
				<div class="mileageFindSrch">
					<input type="text" placeholder="<spring:message code='mypage.mileage.lbl.mbship4' />"  id="cardNumber" class="input-style02">
					<a href="javascript:;" class="btn sm" onclick="cardRegister();return false;">카드등록</a>
					<div>
						<input type="text" placeholder="인증번호" id="certifyNo" class="input-style02">
						<p>(인증번호가 있는 경우 입력)</p>
					</div>
				</div>
				<!-- //검색E -->

				<ul class="text-list02">
					<li>등록하신 멤버십 카드의 마일리지는 F&F 통합 멤버십의 마일리지로 전환되며, 해당 카드는 이후 매장에서 사용할 수 없습니다.</li>
					<li>온라인 멤버십 번호는 모바일을 통해 마이페이지에 접속 하시면 확인할 수 있습니다.</li>
				</ul>

			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close" onclick="cardRegisterClear();return false;">닫기</button>
		</div>
	</section>
</article>


<script type="text/javascript">

	$(document).ready(function(){

		setMonth("3month");

		//var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : $("#dateStart").val() ,'dateEnd' :  $("#dateEnd").val()};

		goMileageList();

		/* if("${myPage.dateStart}"==null || "${myPage.dateStart}"==""){

			setMonth("3month");
		} */
	});

	function goMileageList(pageNo){

		if(pageNo == ""){
            pageNo = 1;
        }

        strParams = {
			'${_csrf.parameterName}' : '${_csrf.token}' ,
			'dateStart' : $("#dateStart").val().replace(/\./g, '-') ,
			'dateEnd' :  $("#dateEnd").val().replace(/\./g, '-') , 
			'currentPage' : pageNo
        };

        $.ajax({
    		type : "POST",
    		async : true,
    		url : "/mypage/benefit/include/listMileage.ajax",
    		data : strParams,
    		success : function(data) {
    			$('#includeMileageList').html(data);
    		},
    		error: function(pa_data, status, err) {
    			if (pa_data.status == "403") {
    				alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                    location.reload();
    			} else {
                	//alert("error forward : "+err+" ,status="+status);
                	alert('시스템 오류입니다.');
    			}
            }
    	});
    }


	function setMonth(val){
		// TODO : 달력 스크립트에서 날짜 넣는 형식 확인.

		var addMonth = 0;
		if (val == "3month") {
			addMonth = -3;
		}
		else if (val == "6month") {
			addMonth = -6;
		}

		var today = moment().format('YYYY.MM.DD');
		var fromDate = moment().add(addMonth, 'months').format('YYYY.MM.DD');

		$("#dateStart").val(fromDate);
		$("#dateEnd").val(today);
	}

	function searchMileageList() {

		// 날자포맷이 yyyy.mm.dd, yyyy-mm-dd, yyyy/mm/dd에 대응
		var regExp = /[\/.-\s]/g;

		// 조회 기간 계산
		var strStartDate = $("#dateStart").val().replace(regExp, "");
		var startDate = new Date(strStartDate.substr(0,4), '' + (parseInt(strStartDate.substr(4,2)) - 1), strStartDate.substr(6,2));

		// 1년후 계산
		startDate.setYear(startDate.getFullYear() + 1);

		var strEndDate = $("#dateEnd").val().replace(regExp, "");
		var endDate = new Date(strEndDate.substr(0, 4), '' + (parseInt(strEndDate.substr(4, 2)) - 1), strEndDate.substr(6, 2));

		// 조회 기간이 1년 이상일 경우에 메세지 표시
		if (startDate < endDate) {
			alert('시작일로부터 최대 1년까지 조회가 가능합니다.');
			return;
		}

		goMileageList();
	}

	// 멤버십 카드등록
	function cardRegister(){

		/* 카드번호입력 시 validate 체크 */
		var cpnNo = $('#cardNumber').val();
		var reCpnNo = cpnNo.replace(/ /gi,'');

		if(cpnNo==null || cpnNo=="") {
			alert("카드번호를 등록해 주세요.");
			//alertLayer(MESSAGES['mypage.js.mileage.msg.member.cardregister']);
			return false;
		}

		if(cpnNo.length != reCpnNo.length){
			alert("잘못된 형식의 카드번호 입력입니다.");
			//alertLayer(MESSAGES['mypage.js.mileage.msg.member.cardnumber']);
			return false;
		}
		/* //카드번호입력 시 validate 체크 */

		if( confirm("F&F 통합 멤버십 마일리지로 전환 시 등록한 카드는 이후 매장에서 사용할 수 없습니다.\n등록 하시겠습니까?") ) {
			/* 카드 등록시 모바일 어플리케이션에서 신규 멤버십 카드가 발급됩니다.<br/>등록하신 카드는 매장에서 사용이 불가합니다.<br/>등록하시겠습니까? */
			$.ajax({
				type:"post"
				,url:"/mypage/benefit/memberCard.json"
				,data : { membershipCardNo: $('#cardNumber').val(), certifyNo: $('#certifyNo').val() }
				,dataType: "json"
				,async : true
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				}
				,success : function(data) {

					//closeConfirmLayer();

					if (data.resultCd === "00") {

						alert("F&F 통합 멤버십 마일리지로 전환되었습니다.");
						//alertLayer(MESSAGES['mypage.js.mileage.msg.member.card.confirm']);

						$('.d_layer_close').click();

						//리스트조회
						goMileageList();

					} else if (data.resultCd === "99") {

						//$("#cardNo-null").html("<spring:message code='mypage.mileage.lbl.txt10' />"); // 입력하신 카드 번호가 일치하지 않습니다. 확인 후 다시 등록해 주세요.
						alert('유효한 카드 번호가 아닙니다. 다시 입력해 주세요.');
						$('#cardNumber').val(null);
						$('#certifyNo').val(null);

					} else {
						alert("오류가 발생 했습니다. 시스템 관리자에게 문의하십시요.");
						$('.d_layer_close').click();
					}
				}
				,error : function(xhr) {
					//alert(xhr.responseText);
					alert('시스템오류 입니다.');
				}

			});
		}

		/*
		 * commonFunction.js
		 * @param confirmBtnNm(클릭시 javascript:callbackConfirmLayer(true); 함수 실행)
		*/
		//confirmLayer(MESSAGES['mypage.js.mileage.msg.member.cardregister.confirm']); /* 카드 등록시 모바일 어플리케이션에서 신규 멤버십 카드가 발급됩니다.<br/>등록하신 카드는 매장에서 사용이 불가합니다.<br/>등록하시겠습니까? */

	}

	/*** 레이어 팝업 적용 시 사용
	// confirmLayer에 대한 callback
	function callbackConfirmLayer(flag) {

		if(flag) {

			$.ajax({
				type:"post"
				,url:"/mypage/benefit/memberCard.json"
				,data : "membershipCardNo=" + $('#cardNumber').val()
				,dataType: "json"
				,async : true
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
				}
				,success : function(data) {

					closeConfirmLayer();

					if (data.resultCd === "00") {

						//alert("등록하신 쿠폰이 정상 발급되었습니다. 내역을 확인해주세요.");
						alertLayer(MESSAGES['mypage.js.mileage.msg.member.card.confirm']);

						//$('.d_layer_close').click();

						//리스트조회
						goMileageList();

					} else if (data.resultCd === "99") {

						$("#cardNo-null").html("<spring:message code='mypage.mileage.lbl.txt10' />"); // 입력하신 카드 번호가 일치하지 않습니다. 확인 후 다시 등록해 주세요.
						$('#cardNumber').val(null);

					} else {
						alert("<spring:message code='mypage.mileage.lbl.txt11'  text='오류가 발생 했습니다. 시스템 관리자에게 문의하십시요.' />");
						$('.d_layer_close').click();
					}
				}
				,error : function(xhr) {
					alert(xhr.responseText);
				}

			});

		}else{
			closeConfirmLayer();
		}
	}
	***/

	// 사용
	function setMileage(nowPoint, useTempPoint, eliminatePoint, unityPntUseSumAmt){

		$("#nowPoint").empty();
		$("#useTempPoint").empty();
		$("#eliminatePoint").empty();

		var newNowPoint = numberFormat(nowPoint);
		var newUseTempPoint = numberFormat( useTempPoint - unityPntUseSumAmt );
		var newEliminatePoint = numberFormat(eliminatePoint);

		$("#nowPoint").append('<span class="text-color01">' + newNowPoint +'</span> 원');
		$("#useTempPoint").append('<span class="text-color01">' + newUseTempPoint +'</span> 원');
		$("#eliminatePoint").append('<span class="text-color01">' + newEliminatePoint +'</span> 원');
	}

	//마일리지 건수
	function setMileageCnt(mileageCnt){
		$("#mileageCnt").empty();
		$("#mileageCnt").text(mileageCnt);
	}

	//숫자포맷(#,###,###)
	function numberFormat(num) {
	    return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	}

	/* 회원등급소개 */
	function goMembershipInfo(pageNo){
		location.href='/mypage/benefit/membershipRateInfo';
	}

	/* 맴버십 카드등록 초기화 */
	function cardRegisterClear(){

		$("#cardRegisterOk").prop("style", "display:none");
		$("#cardRegistInfo").prop("style", "display:block");
		$("#cardNo-null").html('');
		$('#cardNumber').val(null);
		$('#certifyNo').val(null);

	}

	// 주문내역상세조회
	function jsOrderInfo(ordNo) {
		location.href ="<c:url value='/mypage/order/"+ordNo+"/view'/>";
	}

</script>