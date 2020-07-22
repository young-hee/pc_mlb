<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.aws.cloudfront.image.url" var="imageURL"/>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" id="srchMonth" name="srchMonth" value="${myPage.srchMonth}">
<input type="hidden" name="pageNo"  id="pageNo" value=""/>

<div class="contain my lnblist-Wrap" id="contain">
	<div class="container">

		<h2 class="title01">포인트</h2>

		<%@ include file="../include/lnb.jspf" %>

		<main class="contents pointList-wrap" id="contents">

			<div class="location-contents">
				<p class="location">
					<span>Home</span>
					<span>마이페이지</span>
					<span>혜택정보</span>
					<strong title="현재 위치">포인트</strong>
				</p>
			</div>

			<!-- 포인트 현황S -->
			<div class="memberSTcont boxCont02">
				<ul>
					<li>
						<p>사용 가능 포인트</p>
						<strong><span class="text-color01"><fmt:formatNumber>${purpleCoinInfo.usefulAmt}</fmt:formatNumber></span>원</strong>
					</li>
					<li>
						<p>당월 소멸 예정 포인트</p>
						<strong><span class="text-color01"><fmt:formatNumber>${purpleCoinInfo.extshPrearngeAmt}</fmt:formatNumber></span>원</strong>
					</li>
				</ul>
			</div>
			<!-- //포인트 현황E -->

			<dl class="period-wrap period-type02">
				<!-- 기간달력 -->
				<dt>조회기간</dt>
				<dd>
					<a href="#" class="btn sm d_radio_select on" onclick="setMonth('3month');"><span>3개월</span></a>
					<a href="#" class="btn sm d_radio_select" onclick="setMonth('6month');"><span>6개월</span></a>
					<c:choose>
						<c:when test="${myPage.dateStart != null && myPage.dateStart != ''}">
							<input type="text" class="calendar" id="dateStart" value="${myPage.dateStart}" readonly><input type="text" class="calendar" id="dateEnd" value="${myPage.dateEnd}" readonly>
						</c:when>
						<c:otherwise>
							<input type="text" class="calendar" id="dateStart" readonly><input type="text" class="calendar" id="dateEnd" readonly>
						</c:otherwise>
					</c:choose>
					<a href="#" class="btn sm" onclick="searchPointList();return false;">검색</a>
					<p class="calendar-info-txt">시작일로부터 최대 1년 단위로 조회가 가능합니다.</p><!-- 2018-08-13 -->
				</dd>
			</dl>

			<!-- table info S -->
			<div class="tbst-div">
				<div class="mid fl">
					<span>전체</span> (<span class="text-color01" id="pointCnt"><!-- 100 --></span>건)
				</div>
			</div>
			<!-- //table info E -->

			<%--P포인트내역 목록--%>
			<div id="includePointList"></div>

			<%-- <%@ include file="../_inc/paging.jsp" %> --%>

			<!-- 포인트 적립 사용안내 영역S -->
			<div class="contTxtBox">
				<strong>포인트 적립 / 사용</strong>
				<ul class="text-list01">
					<li>포인트 적립 기준은 <a href="/mypage/benefit/membershipRateInfo" class="text-color01">회원혜택안내</a>에서 확인하세요.</li>
					<li>주문 결제 시 1,000원 이상 1,000원 단위로 사용 가능 합니다.</li>
					<li>현금으로 환원되지 않는 비현금성 서비스 입니다.</li>
					<li>주문 시 구매한 상품 가격에 비례하여 분할 적용되며 부분 취소/반품 시 분할 환원 됩니다.</li>
				</ul>
				<strong>포인트 유효기간</strong>
				<ul class="text-list01">
					<li>적립 시 부여되는 포인트 별 유효기간을 따르며, 유효기간이 경과한 포인트는 자동으로 소멸됩니다.</li>
				</ul>
			</div>
			<!-- //포인트 적립 사용안내 영역E -->

		</main>

	</div>
</div>


<script type="text/javascript">
	function goPointList(pageNo){

		if(pageNo == ""){
            pageNo = 1;
        }

        //strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'currentPage' : pageNo};
        strParams = {
			'${_csrf.parameterName}' : '${_csrf.token}' , 
			'dateStart' : $("#dateStart").val().replace(/\./g, ''),
			'dateEnd' :  $("#dateEnd").val().replace(/\./g, ''), 
			'currentPage' : pageNo
        };

        $.ajax({
    		type : "POST",
    		async : true,
    		url : "/mypage/benefit/include/listPoint.ajax",
    		data : strParams,
    		success : function(data) {
    			$('#includePointList').html(data);
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

	$(document).ready(function(){
		if("${myPage.dateStart}"==null || "${myPage.dateStart}"==""){
			setMonth("3month");
		}
		searchPointList();
	});

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

	function searchPointList() {

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
			//alert("<spring:message code='mypage.point.btn.txt2' text='시작일로부터 최대 1년까지 조회가 가능합니다.'/>");
			//alertLayer(MESSAGES['mypage.js.order.list.msg.maxsearch']);
			alert("시작일로부터 최대 1년까지 조회가 가능합니다.");
			return;
		}

		//var regExp = /[\s]/g;

		strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : $("#dateStart").val().replace(regExp, "") ,'dateEnd' :  $("#dateEnd").val().replace(regExp, "")};

		$.ajax({
    		type : "POST",
    		async : true,
    		url : "/mypage/benefit/include/listPoint.ajax",
    		data : strParams,
    		success : function(data) {
    			$('#includePointList').html(data);
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

	function setPointCnt(pointCnt){
		$("#pointCnt").empty();
		$("#pointCnt").append(pointCnt);
	}

	/*
	var dateFormat = "yymmdd",
	from = $( "#dateEnd" ).datepicker().on( "change", function() {
		to.datepicker( "option", "minDate", getDate( this ) );
	}),
	to = $( "#dateStart" ).datepicker().on( "change", function() {
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
	*/


/* 회원등급소개 */
function goMembershipInfo(){
   location.href='/mypage/benefit/membershipRateInfo';
}

// 주문내역상세조회
function jsOrderInfo(ordNo) {
	location.href ="/mypage/order/" + ordNo + "/view";
}

</script>