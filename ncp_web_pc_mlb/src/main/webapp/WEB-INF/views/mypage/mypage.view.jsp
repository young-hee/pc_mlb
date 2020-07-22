<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<script src="https://nsp.pay.naver.com/sdk/js/naverpay.min.js?v=${_version}"></script>
<script type="text/javascript" src="<ncp:prop key="ncp_base.order.kcp.js.url"/>"></script>
<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.pay.js?v=${_version}"></script>
<script type="text/javascript" src="${_resourceURL}static/js/jquery.serializejson.min.js?v=${_version}"></script>


<script>
function goMypageAction(obj) {
	
	var url = "";  
    $("#lnbDspCtgryForm").find('[name=dspCtgryNo]').val("");
    $("#lnbDspCtgryForm").find('[name=currentCtgryDpthCd]').val("");
    $("#lnbDspCtgryForm").find('[name=ctgrySectCd]').val("");

    var ctgryNo = $(obj).attr("ctgryNo");
    var currentCtgryDpthCd = $(obj).attr("currentCtgryDpthCd");
    var ctgrySectCd = $(obj).attr("ctgrySectCd");
    url = $(obj).attr("url");
    
    // (ID - 확인필요)
    // url 작업이 완료되기 전까지 url이 없으면 임시로 팝업 알림.
    if(url == null || url == "null") {
    	alert( "작업중입니다." );
    	return;
    }
    
    $("#lnbDspCtgryForm").find('[name=dspCtgryNo]').val(ctgryNo);
    $("#lnbDspCtgryForm").find('[name=currentCtgryDpthCd]').val(currentCtgryDpthCd);
    $("#lnbDspCtgryForm").find('[name=ctgrySectCd]').val(ctgrySectCd);
 
    if(currentCtgryDpthCd == '1'){
        $("#lnbDspCtgryForm").find('[name=ctgryNoDpth1]').val(ctgryNo);
        $("#lnbDspCtgryForm").find('[name=ctgryNoDpth2]').val("");
        $("#lnbDspCtgryForm").find('[name=ctgryNoDpth3]').val("");
    }else if(currentCtgryDpthCd == '2'){
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth1]').val($(obj).attr("ctgryNoDpth1"));
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth2]').val(ctgryNo);
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth3]').val("");
    }else if(currentCtgryDpthCd == '3'){
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth1]').val($(obj).attr("ctgryNoDpth1"));
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth2]').val($(obj).attr("ctgryNoDpth2"));
    	 $("#lnbDspCtgryForm").find('[name=ctgryNoDpth3]').val(ctgryNo);
	}

    $('#realForm').attr('action', url);

    $("#lnbDspCtgryForm").find('input').each(function(i) {     	
    	if($(this).val() !=''){
    		$('#realForm').append($(this));
    	} 	
    });

    var frm = $('#realForm');
 
	frm.submit();
}

/**
 *  결제수단변경
 */
function openPayMethodChangeLayer(ordNo) {
	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'ordNo' : ordNo};
	$("#layerPopupPayment").load('/mypage/order/payment/edit', strParams);
}

//적용가능상품 조회 Pop
function getLayerPopupMyCouponGood(mbrCpnNo, prmNo, aplGodSecCd) {
	var strParams = null;
	strParams = {'mbrCpnNo' : mbrCpnNo , 'prmNo' : prmNo , 'aplGodSecCd' :  aplGodSecCd};
//  	$("#layerPopupCouponProductDiv").load("<c:url value='/mypage/benefit/include/myCouponGoodList'/>", strParams);
//  	layerPopup.popupOpenNow("#layerPopupCouponProduct");


	$.ajax({
		type : "get",
		url : "/mypage/benefit/include/myCouponGoodList",
		dataType : "html",
		data : strParams,
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			$("#layerPopupCouponProductDiv").empty();
			$("#layerPopupCouponProductDiv").append(args);

			$("[name=searchFor]").eq(0).trigger("click");

			$("[name=listCpnFor]").each(function(index) {
				if(mbrCpnNo == $(this).attr("mbrCpnNo")) {
					setBtnDataForCpn(this);
					return false;
				}
			});

			layerPopup.popupOpenNow("#layerPopupCouponProduct");
		},
		error : function(e) {
			alert(e.responseText);
		}
	});
}

/* 회원등급소개 */
function goMembershipInfo(){
   //location.href='/mypage/benefit/membershipRateInfo';
}

$(document).ready(function() {
	
	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'mypageMainYn' : 'Y'};
  	$("#includeOrderList").load("<c:url value='/mypage/order/include/list.ajax'/>", strParams);
  	$("#myWishList").load("<c:url value='/mypage/wishlist/include/list.ajax'/>", strParams);
  	$("#includeCouponList").load("<c:url value='/mypage/benefit/include/availableCoupon.ajax'/>", strParams);
  	$("#includeInquiryList").load("<c:url value='/mypage/inquiry/include/listInquiry.ajax'/>", strParams);
});

function addPayReload() {
	location.reload();
}
</script>

	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>
		
			<%@ include file="include/lnb.jspf" %>
			
			<main class="contents myPageMain-wrap" id="contents">		
			
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<!-- 회원정보S -->
				<ncp:code var="cdMbrGrd" code="${mbrGrd.onlneGrdCd}"/>
				<c:set var="grdClass" value="" />
				<c:choose>
					<c:when test='${mbrGrd.onlneGrdCd eq "MBM_100" }'>
						<c:set var="grdClass" value="mvpSY" />
					</c:when>
					<c:when test='${mbrGrd.onlneGrdCd eq "MBM_200" }'>
						<c:set var="grdClass" value="goldSY" />
					</c:when>
					<c:when test='${mbrGrd.onlneGrdCd eq "MBM_300" }'>
						<c:set var="grdClass" value="silverSY" />
					</c:when>
					<c:otherwise>
						<c:set var="grdClass" value="rookieSY" />
					</c:otherwise>
				</c:choose>
				<div class="memberSTcont boxCont04">
					<ul>
						<li class="colorBlk <c:out value='${grdClass }' />">
							<p>Level</p>
							<strong><c:out value="${ fn:toUpperCase(cdMbrGrd.cdNm) }"/></strong>
							<a href="#ratingBenefit" onclick="goMembershipInfo();return false;" class="d_layer_open"><span><spring:message code='mypage.main.txt1' /></span></a><%-- 구매등급 혜택보기 --%>
						</li>
						<li>
							<p><spring:message code='mypage.membership.info.lbl.text6' /></p><%-- 마일리지 --%>
							<strong><a href="#" class="text-color01" url="/mypage/benefit/listMileage" onClick="javascript:goMypageAction(this);return false;"><fmt:formatNumber>${mileageInfo.nowPoint}</fmt:formatNumber></a><spring:message code='mypage.order.lbl.currency' /></strong>
						</li>
						<li>
							<p><spring:message code='mypage.membership.info.lbl.text9' /></p><%-- 포인트 --%>
							<strong><a href="#" class="text-color01" ctgrysectcd="GNRL_CTGRY" url="/mypage/benefit/listPoint" onClick="javascript:goMypageAction(this);return false;"><fmt:formatNumber>${purpleCoinInfo.usefulAmt}</fmt:formatNumber></a><spring:message code='mypage.order.lbl.currency' /></strong>
						</li>
						<li>
							<p><spring:message code='mypage.membership.info.lbl.text3' /></p><%-- 쿠폰 --%>
							<strong><a href="#" class="text-color01" ctgrysectcd="GNRL_CTGRY" url="/mypage/benefit/listCoupon" onClick="javascript:goMypageAction(this);return false;"><fmt:formatNumber>${couponCnt}</fmt:formatNumber></a><spring:message code='mypage.member.secession.lbl.txt2' /></strong>
						</li>
					</ul>
				</div>
				<!-- //회원정보E -->
				
				
				<div class="justify-wrap">
					<h3 class="title02 left"><spring:message code='mypage.submain.lbl.orderinfo' /></h3><%-- 주문내역 --%>
					<a href="#" class="btn sm fill right" url="/mypage/order/list" onClick="javascript:goMypageAction(this);return false;"><span><spring:message code='mypage.submain.lbl.allview' /></span></a><%-- 전체보기 --%>
				</div>
				
				<!-- 주문내역 S -->
				<div class="odResulCon" id="includeOrderList">				
					
				</div>
				<!-- //주문내역 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left"><spring:message code='mypage.wishlist.ttl1' /></h3><%-- 위시리스트 --%>
					<a href="#" class="btn sm fill right" url="/mypage/wishlist/list" onClick="javascript:goMypageAction(this);return false;"><span><spring:message code='mypage.submain.lbl.allview' /></span></a>
				</div>
				
				<!-- 위시리스트 S -->
				<div class="item-listST" id="myWishList">
					
				</div>
				<!-- //위시리스트 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left"><spring:message code='mypage.coupon.ttl1' /></h3><%-- 쿠폰함 --%>
					<a href="#" class="btn sm fill right" url="/mypage/benefit/listCoupon" onClick="javascript:goMypageAction(this);return false;"><span><spring:message code='mypage.submain.lbl.allview' /></span></a>
				</div>
				
				<!-- 쿠폰함 S -->
				<div class="board-list" id="includeCouponList">
					
				</div>
				<!-- //쿠폰함 E -->
				
				<div class="justify-wrap">
					<h3 class="title02 left"><spring:message code='mypage.order.list.btn.inquiry2' /></h3><%-- 1:1 문의 --%>
					<a href="#" class="btn sm fill right" url="/mypage/inquiry/list" onClick="javascript:goMypageAction(this);return false;"><span><spring:message code='mypage.submain.lbl.allview' /></span></a>
				</div>
				
				<!-- 1:1 문의내역 S -->
				<div class="board-list" id="includeInquiryList">
					
				</div>
				<!-- //1:1 문의내역 E -->

				<div class="justify-wrap">
					<h3 class="title02 left"><spring:message code='mypage.sub.main.link.simple.login' /></h3><%-- 간편로그인 계정 연결 --%>
				</div>
				
				<!-- 간편로그인 계정 연결 S -->
				<%@ include file="/WEB-INF/views/member/naver/include/link.naver.jsp"%>				
				<!-- //간편로그인 계정 연결 E -->

			</main>
			
		</div>
	</div>
	
    <%@ include file="/WEB-INF/views/mypage/include/membership.info.pop.jsp" %>
    <%@ include file="/WEB-INF/views/mypage/order/include/order.payment.popup.jsp"%>
<!-- layerpopup - 추가결제 완료 -->
<%@ include file="/WEB-INF/views/mypage/order/include/order.payment.compt.include.jsp"%>
<!-- //layerpopup - 추가결제 완료 -->
<!-- layerpopup - 쿠폰 적용 가능 상품 -->
<div id="layerPopupCouponProductDiv">
</div>
<!-- //layerpopup - 쿠폰 적용 가능 상품 -->
<!-- layerpopup - 결제하기 -->
<article id="layerPopupPayment" class="layer-popup lypopPayModify inlayer"></article>
<!-- //layerpopup - 결제하기 완료 -->
