<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/member_${pageContext.response.locale.language}.js?v=${_version}"></script>

			<acrticle id="container">
				<section id="contents">
				<ul class="step-section">
					<li class="on"><spring:message code='member.join.lbl.step1' /></li>
					<li class="on"><spring:message code='member.join.lbl.step2' /></li>
					<li><spring:message code='member.join.lbl.step3' /></li>
					<li><spring:message code='member.join.lbl.step4' /></li>
				</ul>
				<div class="member-detail-wrap">
					<h3 class="title02"><spring:message code='member.join.subttl.step2' /></h3><%-- 약관동의 --%>
					<ul class="text-list01">
						<li><spring:message code='member.terms.lbl.txt1' /></li>
						<li><spring:message code='member.terms.lbl.txt2' /></li>
						<li><spring:message code='member.terms.lbl.txt3' /></li>
					</ul>
					<style>
					</style>
					<div class="member-agree-right">
						<span class="check-skin">
							<input type="checkbox" id="agreeCheckAll">
							<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
						</span>
						<label for="agreeCheckAll"><spring:message code='member.terms.lbl.terms.agree.all' /></label>
					</div>
					<h4 class="title06"><spring:message code='member.terms.lbl.terms.agree.online' /></h4>
					<section class="agree-section">
						<div class="agree-section-content">
							${onlineSiteUsefStplat.stplatCont}
						</div>
					</section>
					<ul class="agree-right">
						<li>
							<span class="rdo-skin">
								<input type="radio" id="onlineSiteUsefStplat_Y" name="onlineSiteUsefStplat" value="Y">
								<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
							</span>
							<label for="onlineSiteUsefStplat_Y"><spring:message code='member.terms.rdo.terms.agree' /></label>
						</li>
						<li>
							<span class="rdo-skin">
								<input type="radio" id="onlineSiteUsefStplat_N" name="onlineSiteUsefStplat" value="N" checked="checked">
								<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
							</span>
							<label for="onlineSiteUsefStplat_N"><spring:message code='member.terms.rdo.terms.not.agree' /></label>
						</li>
					</ul>
					<h4 class="title06"><spring:message code='member.terms.lbl.terms.agree.personal' /></h4>
					<section class="agree-section">
						<div class="agree-section-content">
							${psnlInfoColctUsefAgr.stplatCont}
						</div>
					</section>
					<ul class="agree-right">
						<li>
							<span class="rdo-skin">
								<input type="radio" id="psnlInfoColctUsefAgr_Y" name="psnlInfoColctUsefAgr" value="Y">
								<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
							</span>
							<label for="psnlInfoColctUsefAgr_Y"><spring:message code='member.terms.rdo.terms.agree' /></label>
						</li>
						<li>
							<span class="rdo-skin">
								<input type="radio" id="psnlInfoColctUsefAgr_N" name="psnlInfoColctUsefAgr" value="N" checked="checked">
								<span><spring:message code='member.common.choice' /></span><%-- 선택 --%>
							</span>
							<label for="psnlInfoColctUsefAgr_N"><spring:message code='member.terms.rdo.terms.not.agree' /></label>
						</li>
					</ul>
					<div class="btn-wrap">
						<a href="#" class="btn-style03" id="cancelBtn" onClick="return false;"><spring:message code='common.js.cancel' /></a><%-- 취소 --%>
						<a href="#" class="btn-style02" id="confirmBtn" onClick="return false;"><spring:message code='common.js.confirm' /></a><%-- 확인 --%>
					</div>
				</div>
				</section>
			</acrticle>

<script type="text/javascript">

$(document).ready(function() {
	
	// 서비스 이용약관 및 개인정보 수집 및 이용동의 클릭시
	$("#agreeCheckAll").click(function(){
		if($(this).is(":checked") == true) {
			$("#onlineSiteUsefStplat_Y").prop("checked", true);
			$("#psnlInfoColctUsefAgr_Y").prop("checked", true);
		}
		else {
			$("#onlineSiteUsefStplat_N").prop("checked", true);
			$("#psnlInfoColctUsefAgr_N").prop("checked", true);
		}
	});
	
	// 서비스 이용약관 (필수), 개인정보 수집 및 이용 (필수) 클릭시
	$("[name=onlineSiteUsefStplat], [name=psnlInfoColctUsefAgr]").click(function(){
		if($("[name=onlineSiteUsefStplat]:checked").val() == "Y"
				&& $("[name=psnlInfoColctUsefAgr]:checked").val() == "Y") {
			$("#agreeCheckAll").prop("checked", true);
		}
		else {
			$("#agreeCheckAll").prop("checked", false);
		}
	});
	
	// 확인 버튼 클릭시
	$("#confirmBtn").click(function(){
		if(validateForConfirm() == false) {
			return;
		}
		
		movePage("/member/join/view");
	});
	
	// 이전 버튼 클릭시
	$("#cancelBtn").click(function(){
		movePage("/member/certification/view");
	});
	
});

function validateForConfirm() {
	if($("[name=onlineSiteUsefStplat]:checked").val() != "Y" || $("[name=psnlInfoColctUsefAgr]:checked").val() != "Y") {
		alertLayer(MESSAGES['member.js.terms.lbl.error.msg1']);
		return false;
	}
	return true;
}

function movePage(url) {
	var strParams = null;
	strParams = {
		'${_csrf.parameterName}' : '${_csrf.token}'
		, 'onlineSiteUsefStplat' : $("[name=onlineSiteUsefStplat]:checked").val()
		, 'psnlInfoColctUsefAgr' : $("[name=psnlInfoColctUsefAgr]:checked").val()
		, 'ageChk' : 'Y'
	};
	jsLinkUrlPost(url, strParams);
}

</script>