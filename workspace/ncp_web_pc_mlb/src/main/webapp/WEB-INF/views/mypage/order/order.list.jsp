<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js?v=${_version}"></script>
<script type="text/javascript" src="<ncp:prop key="ncp_base.order.kcp.js.url"/>"></script>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.pay.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js?v=${_version}"></script>

<input type="hidden" id="srchMonth" name="srchMonth" value="${searchDTO.srchMonth}">
<input type="hidden" id="currentPage" name="currentPage" value="1">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01"><spring:message code="mypage.order.list.ttl" /></h2>
			
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/> 
				
				<!--  주문/배송조회   -->
				<div class="orderInfoCon">
				
					<ul class="text-list02">
						<li><spring:message code="mypage.order.list.lbl.noti0" text="구매확정 후 리뷰 작성시 포인트 증정 해드립니다.(포토 1,000원/텍스트 300원)" /></li>
						<!--<li><spring:message code="mypage.order.list.lbl.noti2" text="2019년 2월 25일 이전 주문내역을 확인하시려면 '이전 주문보기' 버튼을 클릭해주세요." /></li>-->
						
					</ul>
					
					<div class="odSearchOptBox">
						<a href="#" onclick="goFormerlyOrderList();" class="btn sm gray bfOrder" style="overflow:visible">
							<spring:message code="mypage.order.list.btn.beforeorder" />
							<div class="tooltip-wrap d_dropdown03 type2" style="margin-left:5px">
								<i class="tooltip-detail d_dropdown03_sel" title='<spring:message code="mypage.order.list.btn.beforeorder" text="이전 주문보기"/>'>도움말</i>
								<div class="tooltip-layer d_dropdown03_cont" style="padding-bottom:13px">
									<div class="tooltip-cnt">
										<p class="tooltip-info-txt"><spring:message code="mypage.order.list.lbl.noti1" text="2019년 2월 25일 이전 주문내역을 확인하시려면 '이전 주문보기' 버튼을 클릭해주세요." /></p>
									</div>
								</div>
							</div>
						</a>
						<dl class="period-wrap period-type02 clearfix">
							<!-- 기간달력 -->
							<dt class="blind"><spring:message code="mypage.order.list.lbl.ordertype" /></dt>
							<dd class="fl">
								<!-- select -->
								<div class="select-style02 d_select">
									<button type="button" class="d_select_sel" style="width:152px;"><span><spring:message code="mypage.order.list.select.1" /></span></button>
									<ul>
										<li><a href="#" onclick="setOrdTpCd('');"><spring:message code="mypage.order.list.select.1" /></a></li>
										<li><a href="#" onclick="setOrdTpCd('GNRL_ORD');"><spring:message code="mypage.order.list.select.2" /></a></li>
										<li><a href="#" onclick="setOrdTpCd('RESVE_ORD');"><spring:message code="mypage.order.list.select.3" /></a></li>								
										<li><a href="#" onclick="setOrdTpCd('SHOP_PKUP_ORD');"><spring:message code="mypage.order.list.select.4" /></a></li>
										<li><a href="#" onclick="setOrdTpCd('LAG_QTY_ORD');"><spring:message code="mypage.order.list.select.5" /></a></li>
										<input type="hidden" id="ordTpCd" value=""/>
									</ul>
								</div>
								<!-- //select -->
							</dd>
							<dt><spring:message code="mypage.order.lbl.searchterm" /></dt>
							<dd class="fr">
								<a href="#" class="btn sm d_radio_select on" onclick="setMonth('3month');return false;"><span>3개월</span></a>
								<a href="#" class="btn sm d_radio_select" onclick="setMonth('6month');return false;"><span>6개월</span></a>
								<a href="#" class="btn sm d_radio_select" onclick="setMonth('12month');return false;"><span>12개월</span></a>
								<c:choose>
									<c:when test="${searchDTO.dateStart != null && searchDTO.dateStart != ''}">
										<input type="text" class="calendar" id="dateStart" value="${searchDTO.dateStart}" readonly><input type="text" class="calendar" id="dateEnd" value="${searchDTO.dateEnd}" readonly>
									</c:when>
									<c:otherwise>
										<input type="text" class="calendar" id="dateStart" readonly><input type="text" class="calendar" id="dateEnd" readonly>
									</c:otherwise>
								</c:choose>
								<a href="#" class="btn sm" onclick="loadOrderList();return false;"><spring:message code="mypage.order.btn.search" /></a>
								<!-- <p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p> -->
							</dd>
						</dl>						
						 <!--%@ include file="../_inc/uiDateRange2.jsp" %--> 
					</div>
					
					<!-- order Result -->
					<div class="odSearchResult">	
									
						<div class="odResultTop">
							<strong class="listTotal"><spring:message code="mypage.order.list.lbl.orderinfo" />(<em class="fc_red" id="orderCnt">0</em><spring:message code="mypage.order.list.lbl.ordercount" />)</strong>
							<a href="#lypopDvGuide" class="btn sm gray d_layer_open"><spring:message code="mypage.order.list.lbl.status.info" /></a>	
							<a href="#" class="btn sm fill" onclick="mypageorder.goInquiryList();return false;"><spring:message code="mypage.order.list.btn.inquiry" /></a>						
						</div>
					
						<!--  order Result List -->
						<div class="odResulCon" id="includeOrderList">								         	              	                   
                        </div>
                        <!--  //order Result List -->
                        
                        <!--  page List -->
						
						<!-- // page List -->
						
					</div>		
					<!-- //order Result -->		
					
					<ul class="text-list02">
						<li><spring:message code="mypage.order.list.lbl.noti3" text="배송완료 후 구매확정을 하지 않은 경우에는 배송이 완료된 일로부터 7일 경과 후, 8일째 자동으로 구매확정 됩니다." /></li> 
						<li><spring:message code="mypage.order.list.lbl.noti4" text="주문이 구매 확정되면 마일리지가 적립되며 해당상품은 반품/교환을 할 수 없습니다." /></li>
						<li><spring:message code="mypage.order.list.lbl.noti5" text="주문의 취소/교환/반품 신청내역 및 결과는 [취소/교환/반품 조회] 메뉴를 이용해주세요." /></li>
						<li><spring:message code="mypage.order.list.lbl.noti6" text="매장 배송상품은 일반배송상품과 별도로 매장에서 발송됩니다. 사은품은 주문 상품과 별도로 배송될 수 있습니다." /></li>
						<li><spring:message code="mypage.order.list.lbl.noti7" text='상품을 이미 받았는데 "배송중" 이면, 수령확인을 하시면 배송완료 상태로 변경되며 클레임 신청을 할 수 있습니다.' /></li>
						<li><spring:message code="mypage.order.list.lbl.noti8" text="상품별 결제금액은 주문 시 총 결제금액과 다를 수 있으며, 주문번호를 클릭 하시면 해당 주문의 상세정보를 보실 수 있습니다." /></li>
						<!--<li><spring:message code="mypage.order.list.lbl.noti9" text="구매확정 후 리뷰 작성시 포토리뷰: 1,000원, 텍스트리뷰: 300원 포인트 증정 해드립니다." /></li>-->
					</ul>
					
				</div>		
				<!--  //주문/배송조회   -->	
				

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->


<article id="fomerlyOrderList" class="layer-popup layer-type03">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="mypage.order.list.lbl.formerly.guide.tlt" text="이전주문보기 안내 팝업"/></h2>
		<div class="layer-popup-wrap01">
			<ul class="text-list02 col-type01">
				<li><spring:message code="mypage.order.list.lbl.formerly.guide.txt1" text="최근 3개월간 회원님의 주문하신 내역이 제공됩니다."/></li>
				<li><spring:message code="mypage.order.list.lbl.formerly.guide.txt2" text="2018년 8월 20일 이전 주문은 ‘이전 주문보기’ 버튼 클릭 시 주문내역을 확인 하실 수 있습니다."/></li>
			</ul>
		</div>
		<div class="btn-wrap">
			<a href="#" class="btn-style02" onclick="goFormerlyOrderList();return false;"><spring:message code="mypage.order.list.btn.formerly.view" text="이전 주문보기"/></a>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="mypage.order.btn.close" text="닫기"/></button>
		</div>
	</section>
</article>
		
<!-- layerpopup - 결제하기 -->
<article id="layerPopupPayment" class="layer-popup lypopPayModify inlayer"></article>
<!-- //layerpopup - 결제하기 완료 -->

<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.compt.include.jsp"%>

<article id="lypopDvGuide" class="layer-popup lypopDvGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:851px">
		<h2><spring:message code="mypage.order.list.lbl.status.info" /></h2>
		<div class="layer-cont ly-box">	
			<div class="dvStepBox">
				<ul>
					<li><p><strong><spring:message code="mypage.order.list.lbl.status.step1" /></strong><spring:message code="mypage.order.list.lbl.status.step1.explanation" /></p></li>
					<li><p><strong><spring:message code="mypage.order.list.lbl.status.step2" /></strong><spring:message code="mypage.order.list.lbl.status.step2.explanation" /></p></li>
					<li><p><strong><spring:message code="mypage.order.list.lbl.status.step3" /></strong><spring:message code="mypage.order.list.lbl.status.step3.explanation" /></p></li><!-- 2018.12.06 수정 -->			
					<li><p><strong><spring:message code="mypage.order.list.lbl.status.step4" /></strong><spring:message code="mypage.order.list.lbl.status.step4.explanation" /></p></li><!-- 2018.12.06 수정 -->	
					<li><p><strong><spring:message code="mypage.order.list.lbl.status.step5" /></strong><spring:message code="mypage.order.list.lbl.status.step5.explanation" /></p></li><!-- 2018.12.06 수정 -->	
				</ul>			
			</div>	
			
			<!--  button -->
			<div class="lyBtnArea"><a href="#" class="btn fill w160 d_layer_close"><spring:message code="common.js.confirm" /></a></div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.popup.jsp"%>
		
<script>
	
	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};

	function goPaging(pageNo){
        
		if(pageNo == ""){
            pageNo = 1;
        }
		
		$("#currentPage").val(pageNo);
		
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
		
		//주문 유형
		var ordTpCd = $("#ordTpCd").val();
		
        strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'currentPage' : pageNo, 'dateStart' : strStartDate ,'dateEnd' :  strEndDate, 'ordTpCd' : ordTpCd};
        
        $.ajax({
            type : "POST",
            url     : '/mypage/order/include/list.ajax',
            data : strParams,
            success : function(data) {
                $("#includeOrderList").html(data);
                $('#wrap').scrollTop(0);
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
	
	$("#includeOrderList").load("<c:url value='/mypage/order/include/list.ajax'/>", strParams);

	$(document).ready(function(){
		if("${searchDTO.dateStart}"==null || "${searchDTO.dateStart}"==""){
			
			setMonth("3month");
		}
		
		//이전주문보기 팝업
		//layerPopup.popupOpenNow("#fomerlyOrderList");
	});

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
	}
	
	function setOrdTpCd(val){
		$("#ordTpCd").val(val);
	}
	
	function loadOrderList() {

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
		
		//주문 유형
		var ordTpCd = $("#ordTpCd").val();
		
		strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : strStartDate ,'dateEnd' :  strEndDate, 'ordTpCd' : ordTpCd};	
		
		$.ajax({
            type : "POST",
            url     : '/mypage/order/include/list.ajax',
            data : strParams,
            success : function(data) {
                $("#includeOrderList").html(data);
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
		
    /**
     *  결제수단변경
     */
    function openPayMethodChangeLayer(ordNo) {
    	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : ordNo};
    	$("#layerPopupPayment").load('/mypage/order/payment/edit', strParams);
    }
    
    // 상태 변경 후 처리
    function pageReload() {
    	goPaging($("#currentPage").val());
    }
    
    function goFormerlyOrderList() {
    	var param = "";
    	param += "?" + "dspCtgryNo=DMPA01A01A01";	// 메뉴 유지를 위한 데이터
	    param += "&" + "currentCtgryDpthCd=3";		// 메뉴 유지를 위한 데이터
	    param += "&" + "ctgrySectCd=GNRL_CTGRY";	// 메뉴 유지를 위한 데이터
	    param += "&" + "ctgryNoDpth1=DMPA01";		// 메뉴 유지를 위한 데이터
	    param += "&" + "ctgryNoDpth2=DMPA01A01";	// 메뉴 유지를 위한 데이터
	    param += "&" + "ctgryNoDpth3=DMPA01A01A01";	// 메뉴 유지를 위한 데이터
		location.href ='/mypage/order/formerly/list' + param;
	}
</script>
