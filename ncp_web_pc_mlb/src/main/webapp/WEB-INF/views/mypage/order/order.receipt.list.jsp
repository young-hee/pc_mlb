<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<form  method="post" id="srchForm" action ="<c:url value='/secured/mypage/listReceipt'/>" >
	<input type="hidden" id="srchMonth" name="srchMonth" value ="${myPage.srchMonth }">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" id="pageNo" name="pageNo" value="" />


	<!-- //2018-05-23 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">증빙서류 조회</h2>

			<%@ include file="../include/lnb.jspf" %>

			<main class="contents" id="contents">

				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>주문정보</span><strong title="현재 위치">증빙서류 조회</strong>
					</p>
				</div>

				<!--  증빙서류 조회   -->
				<div class="orderInfoCon orderDc">
					<div class="odSearchOptBox mgt0Box">
						<dl class="period-wrap period-type02">
							<dt>조회기간</dt>
							<dd>
								<a href="javascript:;" class="btn sm d_radio_select on" onclick="setMonth('3month')"><span>3개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select" onclick="setMonth('6month')"><span>6개월</span></a>
								<a href="javascript:;" class="btn sm d_radio_select" onclick="setMonth('12month')"><span>12개월</span></a>
								<input type="text" class="calendar" id="dateStart" name="dateStart" value="${myPage.dateStart}" readonly><input type="text" class="calendar" id="dateEnd" name="dateEnd" value="${myPage.dateEnd}" readonly>
								<a href="javascript:;" class="btn sm" onclick="goReceiptList('1');return false;">검색</a>
							</dd>
						</dl>
						 <!--%@ include file="../_inc/uiDateRange2.jsp" %-->
					</div>

					<!-- order Result -->
					<div class="odSearchResult">
						<div class="odResultTop">
							<strong class="listTotal">주문내역(<em class="fc_red" id="totalCnt"><!-- 5 --></em>건)</strong>
							<a href="/helpdesk/csInquiry/new" class="btn sm fill">1:1 고객상담</a>
						</div>

						<div id="receiptListDiv"></div>
					</div>
					<!-- //order Result -->

					<ul class="text-list02">
						<li>구매확정 이후 현금영수증 발행 정보를 전달하므로 국세청 사이트에서는 즉시 확인이 되지 않을 수 있습니다.</li>
						<li>증빙서 류 발급은 "구매확정" 완료된 건에 대해 조회 및 인쇄가 가능합니다.</li>
						<li>휴대전화 결제금액은 증빙서류 발급에서 제외됩니다.(현금영수증은 휴대전화  요금을 현금 납부하는 경우에만 해당 이동통신사에서 발급합니다.)</li>
					</ul>

				</div>
				<!--  // 증빙서류 조회   -->


			</main>

		</div>
	</div>
</form>

<ncp:prop key="ncp_base.order.kcp.receipt.url" var="kcpUrl" />
<ncp:prop key="ncp_base.order.naverPay.payment.detail.url" var="naverpayUrl" />

<script>

	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};

	$(document).ready(function(){
        if ("${myPage.dateStart}" == null || "${myPage.dateStart}" == "") {
            setMonth("3month");
        } else if ("${myPage.srchMonth}" != null && "${myPage.srchMonth}" != "") {
            setMonth("${myPage.srchMonth}");
        }
	});

	$("#receiptListDiv").load("<c:url value='/mypage/order/receipt/include/list.ajax'/>", strParams);

	function setMonth(val){

		var addMonth = 0;
		if (val == "3month") {
			addMonth = -3;
		}
		else if (val == "6month") {
			addMonth = -6;
		}
		else if (val == "12month") {
			addMonth = -12;
		}

		var today = moment().format('YYYY.MM.DD');
		var fromDate = moment().add(addMonth, 'months').format('YYYY.MM.DD');

		$("#dateStart").val(fromDate);
		$("#dateEnd").val(today);

		$("#srchMonth").val(val);

	}

	// KCP 카드전표/거래명세서 조회
	function openKCPReceipt(cmd, tno, ordNo, amt) {
		// 영문일 경우 _eng 추가
		var width = 470;
		var height = 815;

		if (cmd === 'card_bill' ) {
			height = 695;
		} else if (cmd === 'cash_bill') {
			width = 420;
			height = 670;
		}

        var status = "toolbar=no,location=directories=status=yes,menubar=scrollbars=resizable=width=" + 470 + ",height=" + height;
        var url = "${kcpUrl}?cmd=" + cmd + "&tno=" + tno + "&order_no=" + ordNo + "&trade_mony=" + amt;
        window.open(url, "popupIssue", status);
    }

	function openNaverpayReceipt(tno) {
		window.open("${naverpayUrl}" + tno);
	}

    function goReceiptList(pageNo){

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
			alertLayer('<spring:message code="mypage.js.order.list.msg.maxsearch" text="시작일로부터 최대 1년까지 조회가 가능합니다."/>');
			return;
		}

		if(pageNo == ""){
            pageNo = 1;
        }

		$("#pageNo").val(pageNo);

        strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'pageNo' : pageNo, 'dateStart' : $("#dateStart").val() ,'dateEnd' :  $("#dateEnd").val()};
        $.ajax({
    		type : "POST",
    		url     : '/mypage/order/receipt/include/list.ajax',
    		data : strParams,
    		success : function(data) {
    			$("#receiptListDiv").html(data);
    		},
    		error : function(jqXHR, textStatus, error) {
    			if(jqXHR.status == "403") {
    				alert("<spring:message code='common.session.over.relogin'/>");
    				location.reload();
    			} else {
    				alert("<spring:message code='common.system.error'/>");
    			}
    		}
        });
    }

	function setTotalCnt(totalCnt){
		$("#totalCnt").empty();
		$("#totalCnt").append(totalCnt);
	}

</script>