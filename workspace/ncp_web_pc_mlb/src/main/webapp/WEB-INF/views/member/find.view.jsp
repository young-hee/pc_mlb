<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


	<div class="contain mb idpw" id="contain">		
		<div class="container">

			<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false" />
			<main class="contents" id="contents">
				
				<div class="member-certification-wrap">
					<div class="member-certification-cnt d_tab02">
						<ul class="tab-type05">
							<li class="d_tab02_select"><a href="#" id="findIdTab"><spring:message code='member.find.tab.id' /></a></li><%-- 아이디 찾기 --%>
							<li class="d_tab02_select"><a href="#" id="findPwTab"><spring:message code='member.find.tab.pw' /></a></li><%-- 비밀번호 찾기 --%>
						</ul>
						<div class="d_tab02_cont" style="display:block;">
							<ul class="certification-list">
								<li><a href="#" class="" name="pccCertBtn"><spring:message code='member.certification.btn.mobile.cert' /></a></li><%-- 휴대폰 인증 --%>
								<li><a href="#" class="" name="ipinCertBtn"><spring:message code='member.certification.btn.ipin.cert' /></a></li><%-- 아이핀 인증 --%>
							</ul>
						</div>
						<div class="d_tab02_cont">
							<ul class="certification-list">
								<li><a href="#" class="" name="pccCertBtn"><spring:message code='member.certification.btn.mobile.cert' /></a></li><%-- 휴대폰 인증 --%>
								<li><a href="#" class="" name="ipinCertBtn"><spring:message code='member.certification.btn.ipin.cert' /></a></li><%-- 아이핀 인증 --%>
							</ul>
						</div>
						<h3 class="title06"><spring:message code='member.find.lbl.cert.info' /></h3><%-- 인증방법안내 --%>
						<ul class="text-list02 col-type01">
							<li><spring:message code='member.find.lbl.cert.info.txt1' /></li>
							<li><spring:message code='member.find.lbl.cert.info.txt2' /></li>
						</ul>
					</div>
					<%-- <p class="txt-sub-info01 txt-sub-type04"><spring:message code='member.certification.layer.footer.info' /></p> --%>
					<div class="guds">
						<strong class="cs-number"><spring:message code='member.find.view.txt1' /></strong> <%-- 고객센터 : 080-807-0012 --%>
						<ul class="links">
							<li><a href="/helpdesk/faq/list"><spring:message code='member.find.view.txt3' /></a></li><%-- FAQ바로가기 --%>
							<li><a href="#" onclick="goKakaoTalkOpen(); return false;"><spring:message code='member.find.view.txt4' /></a></li><%-- >카카오톡 상담하기 --%>
						</ul>
						<div class="time"><spring:message code='member.find.view.txt2' /></div><%-- 운영시간 : 평일 오전 9시 ~ 오후 6시 (토/일/공휴일휴무) --%>
					</div>
				</div>
				
			</main>
			
		</div>
	</div>
	
		<jsp:include page="/WEB-INF/views/member/include/certification.layer.popup.jsp"  flush="false" />	
		
		<article id="layerPopupIdFind01" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.find.tab.id' /></h2><%-- 아이디 찾기 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt"><spring:message code='member.find.layer.id.01.txt' /></p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn lg fill btn-login" id="join" onClick="return false;"><spring:message code='member.find.layer.id.01.btn' /></a><%-- 회원가입하기 --%>
				</div>
				
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		
		<!-- 아이디 찾기 -->
		<article id="layerPopupIdFind02" class="layer-popup layer-type02 popIdFind">
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code='member.find.tab.id' /></h2><%-- 아이디 찾기 --%>
				<div class="layer-popup-wrap02">
					<p class="layer-txt"></p><%-- {0} 고객님께서 등록하신 아이디는 {1} 입니다. --%>
					<dl class="join-date-info">
						<dt><spring:message code='member.find.layer.id.02.txt2' /> : </dt><%-- 가입일 --%>
						<dd name="layerPopupJoinDt"></dd>
					</dl>
					<div class="btns"><a href="#" class="btn lg fill btn-login" id="login" onClick="return false;"><spring:message code='member.common.login' /></a></div>
		
					<p class="layer-txt04">
						<spring:message code='member.certification.layer.check.01.txt5' />&nbsp;<%-- 비밀번호를 잊으셨나요? --%>
						<a href="#" id="findPassword" onClick="return false;"><spring:message code='member.certification.layer.check.01.txt6' /></a><%-- 비밀번호 찾기 --%>
					</p>
					<ul class="links">
						<li><a href="/helpdesk/faq/list">FAQ바로가기</a></li>
						<li><a href="#" onclick="goKakaoTalkOpen(); return false;">카카오톡 상담하기</a></li>
					</ul>
				</div>
				<div class="layer-footer-info">
					<p><strong>080-807-0012</strong> 평일 오전 9시 ~ 오후 6시 (토/일/공휴일 휴무)</p>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code='member.common.close' /></button>
				</div>
			</section>
		</article>
		
		<jsp:include page="/WEB-INF/views/member/include/modify.password.layer.popup.jsp" flush="false"></jsp:include>		

<script type="text/javascript">

var type = "<c:out value='${type}' />";
$(document).ready(function() {
	$("#findIdTab, #findPwTab").click(function(){
		if($(this).attr("id") == "findIdTab") {
			type = "ID";
		}
		else {
			type = "PASSWORD";
		}
		
		var param = null;
		param = {
			'${_csrf.parameterName}' : '${_csrf.token}'
	        , 'type' : type
	    };
		movePage('/member/login/viewFind', param);
	});
	
	// 휴대폰 인증 버튼 클릭시
	$("[name=pccCertBtn]").click(function(){
		onClickPcc();
	});
	
	// 아이핀 인증 버튼 클릭시
	$("[name=ipinCertBtn]").click(function(){
		onClickIpin();
	});
	
	$("#login").click(function(){
		movePage('/member/login/view');
	});
	
	$("#findPassword").click(function(){
		var param = null;
		param = {
			'${_csrf.parameterName}' : '${_csrf.token}'
	        , 'type' : 'PASSWORD'
	    };
		movePage('/member/login/viewFind', param);
	});
	
	$("#join").click(function(){
		movePage('/member/join/view');
	});
	
	// 비밀번호 변경 팝업에서 사용(비밀번호찾기, 회원정보수정 구분)
	pageType = "find";

});

// window.onload 는 $(document).ready 보다 뒤에 실행됨.
window.onload = function() {
	if(type == "PASSWORD") {
		$("#findPwTab").parent().addClass("on");
	}
	else {
		$("#findIdTab").parent().addClass("on");
	}	
	
	// 다음 페이지로 이동하였다가 history.back() 을 하였을 경우 그대로 팝업이 나오는 현상이 있어서 history 변경 처리.
	<c:if test="${not empty certificationResult}">
	var param = null;
	param = {
        'type' : type
    };
	history.replaceState(param, $("#container").find(".title01 span").text(), "/member/login/viewFind?" + $.param(param));
	
	// certification.nice.complete.jsp 안에 있는 함수 실행.
	end();
	</c:if>
	
}

function movePage(url, param) {
    var strParams = null;
    if(param != undefined && param != null) {
    	strParams = param;
    }
    else {
    	strParams = {
   			'${_csrf.parameterName}' : '${_csrf.token}'
   		};    	
    }
    jsLinkUrlPost(url, strParams);
}

//휴대폰 본인인증
function onClickPcc(){
	$.ajax({
		type : "post",
		url : "/member/certification/setPcc.json",
		data : {"srv":"find", '${_csrf.parameterName}' : '${_csrf.token}'},
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
		data : {"srv":"find", '${_csrf.parameterName}' : '${_csrf.token}'},
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

function callbackCertifyFindMember(checkCertMbr) {
	if(type == "ID") {
		// 일치하는 회원정보가 없는 경우
		if(checkCertMbr == null || checkCertMbr.mbr == null) {
			layerPopup.popupOpenNow('#layerPopupIdFind01');
		}
		// 일치하는 회원정보가 있는 경우
		else if(checkCertMbr.mbr) {
			if(checkCertMbr.mbr.mbrStatCd == "ACT" || checkCertMbr.mbr.mbrStatCd == "DRMNCY") {
				$("#layerPopupIdFind02").find("[name=layerPopupJoinDt]").text(checkCertMbr.joinDtStr);
				$("#layerPopupIdFind02").find(".layer-txt").html(messageFormat(MESSAGES['member.js.find.layer.id.02.txt1'], checkCertMbr.mbr.mbrNm, checkCertMbr.mbr.mbrId));
				//$("#layerPopupIdFind02").find(".layer-txt").text(checkCertMbr.mbr.mbrNm + " " + MESSAGES['member.js.find.layer.id.02.txt1'] + " " + checkCertMbr.mbr.mbrId + " " + MESSAGES['member.js.find.layer.id.02.txt2']);
				layerPopup.popupOpenNow("#layerPopupIdFind02");
			}
			else if(checkCertMbr.mbr.mbrStatCd == "SECSN") {
				$("#layerPopCertificationCheck01").find("[name=layerPopupSecsnDt]").text(checkCertMbr.SecsnDtStr);
				$("#layerPopCertificationCheck01").find("[name=layerPopupMbrId]").text(checkCertMbr.mbr.mbrId);
				layerPopup.popupOpenNow("#layerPopCertificationCheck01");
			}
		}
	}
	else {
		// 일치하는 회원정보가 없는 경우
		if(checkCertMbr == null || checkCertMbr.mbr == null) {
			$("#layerPopupIdFind01").find("h2").html(MESSAGES['member.js.find.tab.pw']);
			layerPopup.popupOpenNow("#layerPopupIdFind01");
		}
		// 일치하는 회원정보가 있는 경우
		else if(checkCertMbr.mbr) {
			layerPopup.popupOpenNow("#layerPopupModifyPassword");
		}
	}
}

// 비밀번호 변경 후 성공 레이어 팝업에서 확인 버튼 클릭시
function callbackLayerPopupModifyPasswordSuccess() {
	movePage('/member/login/view');
}

</script>