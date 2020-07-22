<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>

			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents memberWithdraw-wrap" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<!-- 회원정보S -->
				<div class="memberSTcont boxCont04">
					<ul>
						<li class="colorBlk">
							<c:set var="levelName" />
							<ncp:codes group="ONLNE_GRD" var ="cdGrd"/>
							<c:forEach var ="cdGrd" items ="${cdGrd}" varStatus="index">
								<c:if test="${memberFoResult.mbrGrd.onlneGrdCd == cdGrd.cd}">
									<c:set var="levelName" value="${cdGrd.cdNm}" />
								</c:if>
							</c:forEach>
							<p>Level</p>
							<strong>${levelName}</strong>
						</li>
						<li>
							<p>마일리지</p>
							<strong>
								<a href="javascript:movePage('/mypage/benefit/listMileage');"  class="text-color01"><fmt:formatNumber value="${memberFoResult.milage}" pattern="#,###" /></a><spring:message code='mypage.order.lbl.currency' />
							</strong>
						</li>
						<li>
							<p>포인트</p>
							<strong>
								<a href="javascript:movePage('/mypage/benefit/listPoint');" class="text-color01"><fmt:formatNumber value="${memberFoResult.mbrWebpntHistExt.usefulAmt}" pattern="#,###" /></a><spring:message code='mypage.order.lbl.currency' />
							</strong>
						</li>
						<li>
							<p>쿠폰</p>
							<strong>
								<a href="javascript:movePage('/mypage/benefit/listCoupon');" class="text-color01"><fmt:formatNumber value="${memberFoResult.cpnCnt}" pattern="#,###" /></a><spring:message code='mypage.member.secession.lbl.txt2' />
							</strong>
						</li>
					</ul>
					<p class="txt13-666">F&F에서 운영하는 모든 패밀리 브랜드로부터 함께 탈퇴 되며 30일 이내 재가입이 불가합니다.<br/>탈퇴 시 보유하고 계신 마일리지, 포인트, 쿠폰은 즉시 자동 소멸되며 이후 다시 확인하실 수 없습니다.<br/>회원약관 및 개인정보 제공, 활용에 관한 약관 동의가 모두 철회됨을 알려드립니다.</p>
					<strong class="txt13-000"><b>F&F Family Brand</b>&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;MLB,MLB KIDS,Discovery Expedition,COLLECTED,JARDIN PERDU,Stretch Angels</strong>
				</div>
				<!-- //회원정보E -->
				
				<hr class="hr-666" />
				
				<!-- 주문/클레임 현황 정보 영역S -->
				<div class="memberODinfo">
					<strong class="txt16-000">고객님께서 F&F 패밀리 사이트에서 현재 거래중인 주문 및 서비스를 확인해 주세요.</strong>
					<p class="txt13-666">구매상품이 배송/교환/반품 중인 상태이거나 클레임 진행중인 상태인 경우 <em>모두 완료된 후 탈퇴 가능합니다.</em></p>
					<div class="memberODSTBox">
						<ul>
							<li>
								<strong>진행중인 주문</strong>
								<p>구매 <span class="text-color01">${memberFoResult.ordCnt}</span>건</p>
								<a href="#" class="btn fill" onclick="movePage('/mypage/order/list'); return false;">주문/배송 현황</a>							
							</li>
							<li>
								<strong>취소/반품/교환</strong>
								<p>취소 <span class="text-color01">${memberFoResult.claimCntCancel}</span>건 / 반품 <span class="text-color01">${memberFoResult.claimCntReturn}</span>건 / 교환 <span class="text-color01">${memberFoResult.claimCntExchange}</span>건</p>
								<a href="#" class="btn fill" onclick="movePage('/mypage/claim/list'); return false;">취소/반품/교환 현황</a>
							</li>
						</ul>
					</div>
					
					<!-- 진행중인 주문/클레임 건이 있어서 탈퇴할 수 없는 경우S -->
					<div class="memberWithdraw-inner" style="display:none;" id="sectionImpossible">
						<span class="error-msg">현재 진행중인 주문 및 클레임이 모두 완료되어야 탈퇴하실 수 있습니다.</span>
					</div>
					<!-- //진행중인 주문/클레임 건이 있어서 탈퇴할 수 없는 경우E -->
					
					
					
					<!-- 탈퇴 가능한 경우S -->
					<div class="memberWithdraw-inner" id="sectionPossible">
					<form id="secessionMemberForm" name="sectionPossible">
					<input type="hidden" name="checkPasswordFlag" value="${checkPasswordFlag}" />
						<div class="contTxtBox">
							<strong>유의사항</strong>
							<ul class="text-list02">
								<li>탈퇴 후 30일간 재가입 방지 및 마일리지 부정사용을 방지하기 위해 CI 등 일부 회원 정보가 보존됩니다.<br/>(회원가입 시 동의하신 개인정보 취급 방침에 명시한 파기절차와 방법에 따라 30일 이에 모두 파기됩니다.)</li>
								<li>전자상거래 이용내역이 있는 회원님은 전자상거래 등에서의 소비자보호에 관한 법률에 의거 교환/반품/환불 및 사후처리(A/S) 등을 위해 회원정보가 관리됩니다.</li>
							</ul>
						</div>
						<div class="memberWithdrawInfo">
							<strong>회원탈퇴 사유</strong>
							<div class="memberWithdrawGray">
								<ul>
									<ncp:codes group="SECSN_RSN" var ="cdGod"/>
									<c:forEach var ="cd" items ="${cdGod}" varStatus="index"  >
									<li>
										<span class="rdo-skin">
											<input type="radio" id="${cd.cd}" name="mbr.secsnResnCd" value="${cd.cd}">
											<span><spring:message code='member.common.choice' /></span>
										</span>
										<label for="${cd.cd}">${cd.cdNm}</label>
									</li>
									</c:forEach>									
								</ul>
								<p class="txt13-666">회원 탈퇴 사유를 입력해 주시면 보다 나은 서비스로 찾아 뵙겠습니다.</p>								
								<textarea id="secsnResnDetailCont" name="mbr.secsnResnDetailCont" cols="30" rows="10" placeholder="<spring:message code='mypage.member.secession.lbl.reason.placeholder' />" style="width:100%; height:188px;"></textarea>
							</div>
						</div>
						
						<div class="btnWrapBox">
							<a href="javascript:;" class="btn" id="secessionRequest">탈퇴 요청</a>
						</div>
					</form>
					</div>
					
					<!-- //탈퇴 가능한 경우E -->
					
					<hr class="hr-ddd" />
					
					<p class="txt13-666">문의 : 고객센터 <b class="txt13-000">080-807-0012</b> 운영시간 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
					
				</div>
				<!-- //주문/클레임 현황 정보 영역E -->

			</main>
			
		</div>
	</div>

			<article id="secessionConfirm" class="layer-popup layer-type02" style="display: none;">
				<section class="layer-popup-cont" tabindex="0">
					<h2><spring:message code='mypage.member.secession.layer.ttl1' /></h2><%-- 회원탈퇴 확인 --%>
					<div class="layer-popup-wrap02">
						<p class="layer-txt03"><spring:message code='mypage.member.secession.layer.confirm.txt1' /></p>
						<p class="layer-txt04"><spring:message code='mypage.member.secession.layer.confirm.txt2' /></p>
					</div>
					<div class="btn-wrap03">
						<a href="#" id="secessionLayerCancel" class="btn lg" onClick="return false;"><spring:message code='common.js.cancel' /></a><%-- 취소 --%>
						<a href="#" id="secessionLayerConfirm" class="btn fill lg" onClick="return false;"><spring:message code='common.js.confirm' /></a><%-- 확인 --%>
					</div>
					<div class="layer-popup-close">
						<button type="button" class="d_layer_close"><spring:message code='common.js.close' /></button><%-- 닫기 --%> 
					</div>
				</section>
			</article>

			<article id="secessionSuccess" class="layer-popup layer-type02" style="display: none;">
				<section class="layer-popup-cont" tabindex="0">
					<h2><spring:message code='mypage.member.secession.layer.ttl2' /></h2><%-- 탈퇴 완료 --%>
					<div class="layer-popup-wrap02">
						<p class="layer-txt"><spring:message code='mypage.member.secession.layer.success.txt1' /></p>
						<p class="layer-txt02"><spring:message code='mypage.member.secession.layer.success.txt2' /></p>
					</div>
					<div class="btn-wrap03">
						<a href="#" id="secessionLayerSuccess" class="btn fill lg" onClick="return false;"><spring:message code='common.js.confirm' /></a><%-- 확인 --%>
					</div>
					<div class="layer-popup-close">
						<button type="button" onClick="javascript:movePage('/main/mall/view');"><spring:message code='common.js.close' /></button><%-- 닫기 --%>
					</div>
				</section>
			</article>

<script type="text/javascript">

$(document).ready(function() {
	// 탈퇴 요청 버튼
	$("#secessionRequest").click(function(){
		if(validateForSecession() == false) {
			return;
		}
		
		layerPopup.popupOpenNow("#secessionConfirm");
	});
	
	// 탈퇴 요청 레이어 컨펌 취소 버튼
	$("#secessionLayerCancel").click(function(){
		closeCommonLayerPopup("secessionConfirm");
	});
	
	// 탈퇴 요청 레이어 컨펌 확인 버튼
	$("#secessionLayerConfirm").click(function(){
		secessionRequest();
	});
	
	// 탈퇴 성공 후 확인 버튼
	$("#secessionLayerSuccess").click(function(){
		movePage('/main/mall/view');
	});
	
	var ordCnt = '${memberFoResult.ordCnt}';
	var claimCnt = '${memberFoResult.claimCnt}';
	
	if(ordCnt > 0 || claimCnt > 0) {
		$("#sectionPossible").hide();
		$("#sectionImpossible").show();
	}
});

function movePage(url) {
	var strParams = null;
	strParams = {
		'${_csrf.parameterName}' : '${_csrf.token}'
	};
	jsLinkUrlPost(url, strParams);
}

function validateForSecession() {
	var erpConnectFlag = '${memberFoResult.erpConnectFlag}';
	var ordCnt = '${memberFoResult.ordCnt}';
	var claimCnt = '${memberFoResult.claimCnt}';
	var regExp = /[\\\/\:\<\>\&]/gi;
	
	if(erpConnectFlag != undefined && erpConnectFlag != null && erpConnectFlag == "N") {
		alertLayer(MESSAGES['mypage.js.member.secession.erp.connect.fail']);
		return false;	
	}
	else if($("[name='mbr.secsnResnCd']:checked").val() == undefined) {
		alertLayer(MESSAGES['mypage.js.member.secession.error.msg1']);
		return false;
	}
	else if( (Number(fn_getByteLength($("#secsnResnDetailCont").val())) > 0 && Number(fn_getByteLength($("#secsnResnDetailCont").val())) < 30)
			|| Number(fn_getByteLength($("#secsnResnDetailCont").val())) > 300
			) {
		alertLayer(MESSAGES['mypage.js.member.secession.error.msg2']);
		return false;
	}
	else if(regExp.test($("#secsnResnDetailCont").val()) == true) {
		alertLayer(MESSAGES['mypage.js.member.secession.error.msg3']);
		return false;
	}
	else if(ordCnt > 0 || claimCnt > 0) {
		alertLayer(MESSAGES['mypage.js.member.secession.error.msg4']);
		return false;
	}
	
	return true;
}

function secessionRequest() {
	if(validateForSecession() == false) {
		return;
	}
	$.ajax({
		type : "POST",
		url : "/mypage/member/secessionMember.json",
		data : $("#secessionMemberForm").serialize(),
		beforeSend: function (request)
		{
			var csrfToken = $("meta[name='_csrf']").attr("content");
			var csrfName = $("meta[name='_csrf_header']").attr("content");
			request.setRequestHeader(csrfName, csrfToken);
		},
		success : function(args) {
			closeCommonLayerPopup("secessionConfirm");
			layerPopup.popupOpenNow("#secessionSuccess");
		},
		error : function(e) {
			closeCommonLayerPopup("secessionConfirm");
			alertLayer(MESSAGES['common.js.error.msg1']);
		}
	});
}

</script>