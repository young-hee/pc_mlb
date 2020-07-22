<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/naver/naver_login.js?v=${_version}"></script>

<script type="text/javascript">
// link.naver.popup.jsp 화면에서 opener로 호출을 하는데 해당 함수를 login.js에 넣으면 호출이 되지 않음.
function callbackLinkNaverAfter(flag) {
	if(flag) {
		layerPopup.popupOpenNow("#naverConnectCompletePopup");
	}
	else{
		layerPopup.popupOpenNow("#naverConnectFailPopup");
	}
}

function closeNaverConnectCompletePopup(flag) {
	if(flag) {
		closeCommonLayerPopup("naverConnectCompletePopup");
		movePage('/main/mall/view');
	}
	else {
		closeCommonLayerPopup("naverConnectFailPopup");
		movePage('/mypage/view');
	}
}

</script>

	<div class="contain mb join com" id="contain">
		<div class="container">

			<jsp:include page="/WEB-INF/views/include/location.jsp" flush="false" />
			<main class="contents" id="contents">

				<div class="join-com">
					<div class="top_info">
						<div class="hdt">${mbrNm} 회원님 F&F 통합 회원가입을 축하드립니다.</div>
						<div class="ids">회원님의 아이디는 <strong  class="id">${mbrId}</strong> 입니다.</div>
						<div class="txt">
							즐거운 쇼핑하시고 첫 구매 15% 할인 쿠폰과 <br>
							5% 마일리지 Payback 혜택을 받아보세요
						</div>
					</div>
					<div class="btns">
						<ul class="list">
							<li><a href="/" class="btn lg btnGoShop">쇼핑 시작하기</a></li>
							<li><a href="javascript:;" class="btn lg btnNaver" id="linkNaverBtn">네이버 로그인 연동</a></li>
							<li><a href="https://pf.kakao.com/_zKVIxd/" target="_blank" class="btn lg btnPlus">MLB 플러스친구 추가</a></li>
						</ul>
					</div>

					<div class="guds">
						<ul class="text-list02 col-type01 bul-list">
							<li>네이버를 연동하시면 더욱 편리하게 이용하실 수 있습니다.</li>
							<li>MLB 카카오 플러스 친구를 신규 추가하시면 ‘플친 할인 쿠폰’을 드립니다.</li>
							<li>신규가입 쿠폰 및 마일리지가 모두 지급되었습니다. 마이페이지>혜택정보 에서 확인하실 수 있습니다.  <a href="/mypage/view" class="link">확인하러가기</a></li>
						</ul>
					</div>
				</div>
				
			</main>

			
		</div>
	</div>

<%-- 네이버 광고 스크립트 --%>
<script type="text/javascript">
var naver_keyword_advertisement = true;
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#goShoppingBtn").click(function(){
			movePage('/main/mall/view');
		});
		
		$("#linkNaverBtn").click(function(){
			linkNaver();
		});
		
		$("#membershipCertBtn").click(function(){
			membershipCertification();
		});

		// 광고 스크립트
		adScript();
	});

    function movePage(url) {
        var strParams = null;
        strParams = {
            '${_csrf.parameterName}' : '${_csrf.token}'
        };
        jsLinkUrlPost(url, strParams);
    }
    
    function membershipCertification() {
    	movePage('/mypage/benefit/listMileage');
    }

    function adScript() {
		// 페이스북 광고 스크립트
		fbq('track', 'CompleteRegistration');
		
		// 네이버 광고 스크립트
		var _nasa={};
		_nasa["cnv"] = wcs.cnv("2","10"); // 전환유형, 전환가치 설정해야함. 설치매뉴얼 참고 
		fnf_naverKeywordAdvertisement();
    }
    
</script>

<!-- EDN START -->
<script>
    shopid = "discovery"; 
    traceName='JOIN';
    var adbayCallBackUrl = document.location.protocol + '//adcheck.about.co.kr/callBack.html?shopid=' + shopid + '&goodscode=' + traceName;
    var ele = document.getElementById("adbay_callBack");
    if(ele!==null) { ele.parentElement.removeChild(ele); }
    var fileref=document.createElement('iframe');
    fileref.setAttribute("src", adbayCallBackUrl);
    fileref.setAttribute("id", 'adbay_callBack');
    fileref.style.display = 'none';
    if(typeof fileref!="undefined") { document.body.appendChild(fileref); }
</script>
<!-- EDN END -->