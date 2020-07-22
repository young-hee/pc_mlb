<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js?v=${_version}"></script>
<script type="text/javascript" src="<ncp:prop key="ncp_base.order.kcp.js.url"/>"></script>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.pay.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js?v=${_version}"></script>


	<!-- 컨텐츠 시작 -->
	<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01"><spring:message code="mypage.order.claimlist.ttl" text="취소/교환/반품 조회"/></h2>
			
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>  
				
				<!--  취소/교환/반품조회   -->
				<div class="orderInfoCon">
				
					<ul class="text-list02">
						<li><spring:message code="mypage.claim.list.lbl.noti1" text="최근 1년까지의 취소/교환/반품 내역을 조회할 수 있습니다."/></li>
						<li><spring:message code="mypage.claim.list.lbl.noti2" text="**년 *월 **일 이전 내역을 확인하시려면 '이전 주문보기' 버튼을 클릭해주세요."/></li>
					</ul>
					
					<div class="odSearchOptBox">
						<form  method="post" id="srchForm" action ="<c:url value='/secured/mypage/listClaim'/>" >
							<input type="hidden" id="srchMonth" name="srchMonth" value ="${myPage.srchMonth }">		
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
							<a href="#" class="btn sm gray bfOrder" onclick="goFormerlyOrderList(); return false;"><spring:message code="mypage.order.list.btn.beforeorder" text="이전 주문보기"/></a>
							<dl class="period-wrap period-type02 clearfix">
								<!-- 기간달력 -->
								<dt class="blind"><spring:message code="mypage.claim.list.claimtype" text="클레임 유형"/></dt>
								<dd class="fl">
									<!-- select -->
									<div class="select-style02 d_select">
										<button type="button" class="d_select_sel" style="width:152px;"><span>전체</span></button>
										<!-- 2018.12.06 항목 수정 -->
										<ul>
											<li><a href="#" onclick="setClmTpCdSearch('');"><spring:message code="mypage.claim.list.select.1" /></a></li>
											<li><a href="#" onclick="setClmTpCdSearch('CNCL');"><spring:message code="mypage.claim.list.select.2" /></a></li>
											<li><a href="#" onclick="setClmTpCdSearch('GNRL_EXCHG');"><spring:message code="mypage.claim.list.select.3" /></a></li>								
											<li><a href="#" onclick="setClmTpCdSearch('RTGOD');"><spring:message code="mypage.claim.list.select.4" /></a></li>
											<input type="hidden" id=clmTpCdSearch value=""/>									
										</ul>
										<!-- //2018.12.06 항목 수정 -->
									</div>
									<!-- //select -->
								</dd>
								<dt><spring:message code="mypage.order.lbl.searchterm" text="조회기간"/></dt>
								<dd class="fr">
									<a href="#none" class="btn sm d_radio_select on" onclick="setMonth('3month');"><span><spring:message code="mypage.order.btn.3month" text="3개월"/></span></a>
									<a href="#none" class="btn sm d_radio_select" onclick="setMonth('6month');"><span><spring:message code="mypage.order.btn.6month" text="6개월"/></span></a>
									<a href="#none" class="btn sm d_radio_select" onclick="setMonth('12month');"><span><spring:message code="mypage.order.btn.12month" text="12개월"/></span></a>
									<input type="text" class="calendar" id="dateStart" name="dateStart" value="${myPage.dateStart}" readonly>
									<input type="text" class="calendar" id="dateEnd" name="dateEnd" value="${myPage.dateEnd}" readonly>
									<a href="#none" class="btn sm" onclick="goMyPageClaimList(); return false;"><spring:message code="mypage.order.btn.search" text="검색"/></a>
									<!-- <p class="calendar-info-txt">시작일로부터 최대 1년단위로 조회가 가능합니다.</p> -->
								</dd>
							</dl>						
							<!--%@ include file="../_inc/uiDateRange2.jsp" %--> 
						</form>
					</div>
					
					<!-- order Result -->
					<div class="odSearchResult">	
									
						<div class="odResultTop">
							<strong class="listTotal"><spring:message code="mypage.order.list.lbl.total" text="전체"/>(<em class="fc_red" id="claimCnt"></em><spring:message code="mypage.order.list.lbl.ordercount" text="건"/>)</strong>
							<a href="#lypopChgGuide" class="btn sm gray d_layer_open"><spring:message code="mypage.claim.list.lbl.status.info" text="교환/반품 절차안내"/></a>	
							<a href="#" class="btn sm fill" onclick="mypageorder.goInquiryList();return false;"><spring:message code="mypage.order.list.btn.inquiry" text="1:1 고객상담"/></a>						
						</div>
						
						<!--  order Result List -->
						<div class="odResulCon" id="includeClaimList">				                         	                   
                        </div>
                        <!--  //order Result List -->
						
					</div>		
					<!-- //order Result -->		

				</div>		
				<!--  //취소/교환/반품조회   -->	

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

<!-- layerpopup - 결제하기 -->
<article id="layerPopupPayment" class="layer-popup lypopPayModify inlayer"></article>
<!-- //layerpopup - 결제하기 완료 -->

<!-- layerpopup - 추가결제 완료 -->
<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.compt.include.jsp"%>
<!-- //layerpopup - 추가결제 완료 -->

<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.popup.jsp"%>

<!-- 교환/반품 절차 안내 -->
<article id="lypopChgGuide" class="layer-popup lypopChgGuide">
	<section class="layer-popup-cont" tabindex="0" style="width:851px">
		<h2><spring:message code="mypage.claim.list.lbl.status.info" text="교환/반품 절차안내"/></h2>
		<div class="layer-cont ly-box d_tab02">	
			<ul class="tab-type01">
				<li class="d_tab02_select on"><a href="javascript:;"><spring:message code="mypage.claim.list.lbl.exchange.status.info" text="교환 절차 안내"/></a></li>
				<li class="d_tab02_select"><a href="javascript:;"><spring:message code="mypage.claim.list.lbl.return.status.info" text="반품 절차 안내"/></a></li>				
			</ul>
			<!-- 교환절차안내 -->
			<div class="d_tab02_cont" style="display:block">
				<div class="dvStepBox d_tab02">
					<ul>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.exchange.status.step1" text="교환신청"/></strong><spring:message code="mypage.claim.list.lbl.exchange.status.step1.explanation" /></p></li>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.exchange.status.step2" /></strong><spring:message code="mypage.claim.list.lbl.exchange.status.step2.explanation" /></p></li>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.exchange.status.step4" /></strong><spring:message code="mypage.claim.list.lbl.exchange.status.step4.explanation" /></p></li>					
						<li><p><strong><spring:message code="mypage.claim.list.lbl.exchange.status.step5" /></strong><spring:message code="mypage.claim.list.lbl.exchange.status.step5.explanation" /></p></li>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.exchange.status.step6" /></strong><spring:message code="mypage.claim.list.lbl.exchange.status.step6.explanation" /></p></li>
					</ul>			
				</div>	
			</div>
			<!-- //교환절차안내 -->
			<!-- 반품절차안내  -->
			<div class="d_tab02_cont">
				<div class="dvStepBox d_tab02 step04">
					<ul>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.return.status.step1" /></strong><spring:message code="mypage.claim.list.lbl.return.status.step1.explanation" /></p></li>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.return.status.step2" /></strong><spring:message code="mypage.claim.list.lbl.return.status.step2.explanation" /></p></li>
						<li><p><strong><spring:message code="mypage.claim.list.lbl.return.status.step3" /></strong><spring:message code="mypage.claim.list.lbl.return.status.step3.explanation" /></p></li>					
						<li><p><strong><spring:message code="mypage.claim.list.lbl.return.status.step4" /></strong><spring:message code="mypage.claim.list.lbl.return.status.step4.explanation" /></p></li>						
					</ul>			
				</div>	
			</div>	
			<!--// 반품절차안내  -->		
			
			
			<!--  button -->
			<div class="lyBtnArea"><a href="#" class="btn fill w160 d_layer_close"><spring:message code="common.js.confirm" /></a></div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>
<!--// 교환/반품 절차 안내 -->

	<script>
		
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};
	
		$("#includeClaimList").load("<c:url value='/mypage/claim/include/list.ajax'/>", strParams);
		
		$(document).ready(function(){
			
			if("${myPage.dateStart}"==null || "${myPage.dateStart}"==""){
				setMonth("3month");
			}else if("${myPage.srchMonth}"!=null &&"${myPage.srchMonth}"!=""){
				setMonth("${myPage.srchMonth}");
			}
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
			
			$("#srchMonth").val(val);		
		
		}	
			
		function goMyPageClaimList(){
			
			// 날자포맷이 yyyy.mm.dd, yyyy-mm-dd, yyyy/mm/dd에 대응 
			var regExp = /[\/.-]/g;
			
			// 조회 기간 계산
			var strStartDate = $("#dateStart").val().replace(regExp, "");
			var startDate = new Date(strStartDate.substr(0,4), '' + (parseInt(strStartDate.substr(4,2)) - 1), strStartDate.substr(6,2));
			
			// 1년후 계산
			startDate.setYear(startDate.getFullYear() + 1);
	
			var strEndDate = $("#dateEnd").val().replace(regExp, "");
			var endDate = new Date(strEndDate.substr(0, 4), '' + (parseInt(strEndDate.substr(4, 2)) - 1), strEndDate.substr(6, 2));
			
			// 조회 기간이 이년 이상일 경우에 메세지 표시
			if (startDate < endDate) {
				alertLayer('<spring:message code="mypage.js.order.list.msg.maxsearch"/>');
				return;
			}
			
			strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : $("#dateStart").val().replace(regExp, "") ,'dateEnd' :  $("#dateEnd").val().replace(regExp, "") , 'clmTpCdSearch' :  $("#clmTpCdSearch").val()};	
			
			$.ajax({
	            type : "POST",
	            url     : '/mypage/claim/include/list.ajax',
	            data : strParams,
	            success : function(data) {
	                $("#includeClaimList").html(data);
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
	
		function goPaging(pageNo){
	        
			if(pageNo == ""){
	            pageNo = 1;
	        }
	
			// 날자포맷이 yyyy.mm.dd, yyyy-mm-dd, yyyy/mm/dd에 대응 
			var regExp = /[\/.-]/g;
			
			// 조회 기간 계산
			var strStartDate = $("#dateStart").val().replace(regExp, "");
			var startDate = new Date(strStartDate.substr(0,4), '' + (parseInt(strStartDate.substr(4,2)) - 1), strStartDate.substr(6,2));
			
			// 1년후 계산
			startDate.setYear(startDate.getFullYear() + 1);
	
			var strEndDate = $("#dateEnd").val().replace(regExp, "");
			var endDate = new Date(strEndDate.substr(0, 4), '' + (parseInt(strEndDate.substr(4, 2)) - 1), strEndDate.substr(6, 2));
			
			// 조회 기간이 이년 이상일 경우에 메세지 표시
			if (startDate < endDate) {
				alertLayer('<spring:message code="mypage.js.order.list.msg.maxsearch"/>');
				return;
			}
			
	        strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'currentPage' : pageNo, 'dateStart' : $("#dateStart").val().replace(regExp, "") ,'dateEnd' :  $("#dateEnd").val().replace(regExp, "")};
	        
	        $.ajax({
	            type : "POST",
	            url     : '/mypage/claim/include/list.ajax',
	            data : strParams,
	            success : function(data) {
	                $("#includeClaimList").html(data);
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
	    
		function jsOrderInfo(ordNo) {
		
			location.href ="<c:url value='/mypage/order/"+ordNo+"/view'/>";
		}
		
	    /**
	     *  추가결제
	     */
	    function jsPayMethodAdd(ordNo, clmNo) {
	    	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : ordNo, 'type' : 'clmDlvAmtPay', 'clmNo' : clmNo};
	    	$("#layerPopupPayment").load("<c:url value='/mypage/order/payment/edit'/>", strParams);
	    }
	    
	    function setClaimCnt(claimCnt){
			$("#claimCnt").empty();
			$("#claimCnt").append(claimCnt);
		}
	    
	    function goFormerlyOrderList() {
			location.href ='/mypage/order/formerly/list';
		}
	    
	    function setClmTpCdSearch(val){
			$("#clmTpCdSearch").val(val);
		}
	    
	    function addPayReload() {
	    	goMyPageClaimList();
	    }
	</script>