<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/member_${pageContext.response.locale.language}.js?v=${_version}"></script>

			<acrticle id="container">
				<section id="contents">
				<ul class="step-section">
					<li class="on"><spring:message code='member.join.lbl.step1' /></li>
					<li><spring:message code='member.join.lbl.step2' /></li>
					<li><spring:message code='member.join.lbl.step3' /></li>
					<li><spring:message code='member.join.lbl.step4' /></li>
				</ul>
				<div class="member-detail-wrap">
					<h3 class="title02"><spring:message code='member.join.subttl.step1' /></h3><%-- 회원가입 --%>
					<p class="txt-sub-info02"><spring:message code='member.certification.lbl.sub.info1' /></p>
					<p class="txt-sub-info01"><spring:message code='member.certification.lbl.sub.info2' /></p>
					<h3 class="title06"><spring:message code='member.certification.subttl.join' /></h3>
					<div class="certification-check-wrap">
						<div>
							<h4><spring:message code='member.certification.lbl.mobile.cert' /></h4><%-- 휴대폰 인증 --%>
							<ul class="text-list01">
								<li><spring:message code='member.certification.lbl.mobile.txt2' /></li>
								<li><spring:message code='member.certification.lbl.mobile.txt3' /></li>
							</ul>
							<div class="btn-wrap">
								<a href="#" class="btn-style02" id="pccCertBtn"><spring:message code='member.certification.btn.mobile.cert' /></a><%-- 휴대폰 인증 --%>
							</div>
						</div>
						<div>
							<h4><spring:message code='member.certification.lbl.ipin.cert' /></h4><%-- 아이핀(iPIN) 인증 --%>
							<strong><spring:message code='member.certification.lbl.ipin.txt1' /></strong>
							<ul class="text-list01">
								<li><spring:message code='member.certification.lbl.ipin.txt2' /></li>
								<li><spring:message code='member.certification.lbl.ipin.txt3' /></li>
								<li><spring:message code='member.certification.lbl.ipin.txt4' /></li>
							</ul>
							<div class="btn-wrap">
								<a href="#" class="btn-style02" id="ipinCertBtn"><spring:message code='member.certification.btn.ipin.cert' /></a><%-- 아이핀 인증 --%>
							</div>
						</div>
					</div>
					
					<%-- F&F 회원혜택 --%>
<%@ include file="/WEB-INF/views/member/include/benefit.info.jsp"%>

					<h3 class="title06"><spring:message code='member.certification.subttl.info' /></h3><%-- 회원가입 안내 --%>
					<ul class="text-list02 col-type01">
						<li><spring:message code='member.certification.lbl.info.txt1' /></li>
						<li><spring:message code='member.certification.lbl.info.txt2' /></li>
						<li><spring:message code='member.certification.lbl.info.txt3' /></li>
					</ul>
					<div class="member-info-box">
						<strong><spring:message code='member.certification.lbl.info.txt4' /></strong>
						<p class=""><spring:message code='member.certification.lbl.info.txt5' /></p>
					</div>
				</div>
				</section>
			</acrticle>

<%@ include file="/WEB-INF/views/member/include/certification.layer.popup.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	
	// 휴대폰 인증 버튼 클릭시
	$("#pccCertBtn").click(function(){
		onClickPcc();
	});
	
	// 아이핀 인증 버튼 클릭시
	$("#ipinCertBtn").click(function(){
		onClickIpin();
	});
	
});

//휴대폰 본인인증
function onClickPcc(){
	$.ajax({
		type : "post",
		url : "/member/certification/setPcc.json",
		data : {"srv":"newjoin", '${_csrf.parameterName}' : '${_csrf.token}'},
		beforeSend: function (request)
        {
          var csrfToken = $("meta[name='_csrf']").attr("content");
          var csrfName = $("meta[name='_csrf_header']").attr("content");
          request.setRequestHeader(csrfName, csrfToken);
        },
		success : function(args) {
 			var params = {"retUrl":args.retPccUrl, "reqInfo":args.reqPccInfo, "certificationDiv":args.certificationDiv, "actionUrl":'https://nice.checkplus.co.kr/CheckPlusSafeModel/checkplus.cb'};
  	    	openCommonPopup('/member/certification/nice', params, 410, 460, 'nice');
		},
		error : function(e) {
			alert(e.responseText);
		}
	});
}

// 아이핀 본인인증
function onClickIpin(){
	$.ajax({
		type : "post",
		url : "/member/certification/setIpin.json",
		data : {"srv":"newjoin", '${_csrf.parameterName}' : '${_csrf.token}'},
		beforeSend: function (request)
        {
          var csrfToken = $("meta[name='_csrf']").attr("content");
          var csrfName = $("meta[name='_csrf_header']").attr("content");
          request.setRequestHeader(csrfName, csrfToken);
        },
		success : function(args) {
			var params = {"retUrl":args.retIpinUrl, "reqInfo":args.reqIpinInfo, "certificationDiv":args.certificationDiv, "actionUrl":'https://cert.vno.co.kr/ipin.cb'};
// 			var params = {"retUrl":args.retIpinUrl, "reqInfo":args.reqIpinInfo, "certificationDiv":args.certificationDiv, "actionUrl":'https://cert.vno.co.kr/IPIN/pubmain.cb'};
	    	openCommonPopup('/member/certification/nice', params, 410, 460, 'nice');
		},
		error : function(e) {
			alert(e.responseText);
		}
	});
}

function callbackCertifyJoinMember(checkCertMbr, is14, joinPossibility, possibilityCode) {
	
	// 14세 미만인 경우
	if(!is14) {
		$("#layerPopCertificationCheck03").find(".layer-txt").html(MESSAGES['member.js.certification.layer.check.03.txt1']);
		layerPopup.popupOpenNow('#layerPopCertificationCheck03');
	}
	// 이미 가입된 회원이 있을 경우
	else if(checkCertMbr.mbr) {
		if(checkCertMbr.mbr.mbrStatCd == "ACT" || checkCertMbr.mbr.mbrStatCd == "DRMNCY") {
			$("#layerPopCertificationCheck02").find("[name=layerPopupJoinDt]").html(checkCertMbr.joinDtStr);
			$("#layerPopCertificationCheck02").find("[name=layerPopupMbrId]").html(checkCertMbr.mbr.mbrId);
			layerPopup.popupOpenNow('#layerPopCertificationCheck02');
		}
		// 탈퇴 30일 이내
		else if(checkCertMbr.mbr.mbrStatCd == "SECSN") {
			$("#layerPopCertificationCheck01").find("[name=layerPopupSecsnDt]").html(checkCertMbr.secsnDtStr);
			layerPopup.popupOpenNow('#layerPopCertificationCheck01');
		}
	}
	// 회원 가입이 불가한 경우(탈퇴 30일 이내, 직권탈퇴)
	else if(joinPossibility == "false") {
		if(possibilityCode != undefined && possibilityCode != null && possibilityCode == "T") {
			$("#layerPopCertificationCheck01").find(".join-date-info").hide();
			layerPopup.popupOpenNow('#layerPopCertificationCheck01');
		}
		else {
			$("#layerPopCertificationCheck03").find(".layer-txt").html(MESSAGES['member.js.certification.layer.check.03.txt2']);
			layerPopup.popupOpenNow('#layerPopCertificationCheck03');
		}
	}
	else {
		movePage('/member/join/viewTerms');
	}
}

function movePage(url) {
    var strParams = null;
    strParams = {
        '${_csrf.parameterName}' : '${_csrf.token}'
    };
    jsLinkUrlPost(url, strParams);
}

function movePageToFind(type) {
	var param = null;
	param = {
		'${_csrf.parameterName}' : '${_csrf.token}'
		, 'type' : type
    };
	jsLinkUrlPost('/member/login/viewFind', param);
}

</script>