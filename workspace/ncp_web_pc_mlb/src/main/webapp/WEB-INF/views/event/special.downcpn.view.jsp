<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/event/event.js?v=${_version}"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
<ncp:prop key="ncp.image.url" var="imageURL"/>

<!-- 컨텐츠 시작 -->
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				<section class="pm_vis_area">
					<div class="vs_html">


<!-------------------- 기획전 소스 START -------------------->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900|Open+Sans&display=swap&subset=korean" rel="stylesheet">
<style type="text/css">
html, body{overflow:visible;overflow-x:hidden;height:auto}
.blind {overflow:hidden; position:absolute; width:1px; height:1px; margin:-1px; padding:0; border:0; clip:rect(0,0,0,0);}
.evt-wrap{position:relative; left:50%; margin-left:-600px; width:1200px; text-align:left}
.evt-wrap *{font-family: 'Noto Sans KR', 'Noto Sans Korean', 'HelveticaNeue', 'AppleSDGothicNeo', sans-serif}
.evt-wrap strong{font-weight:700}
.evt-wrap div{line-height:0}
.evt-wrap .con{position:relative; width:100%}
.evt-wrap .con h3{display:none}
.evt-wrap .con a.download{position:absolute; left:371px; top:972px; width:460px; height:120px; background:blue; opacity:0; text-indent:-9999em}
.evt-wrap .con a.mlb{position:absolute; left:100px; top:1294px; width:486px; height:254px; background:blue; opacity:0; text-indent:-9999em}
.evt-wrap .con a.kids{position:absolute; left:613px; top:1294px; width:486px; height:254px; background:blue; opacity:0; text-indent:-9999em}
.evt-wrap .notice{width:100%; text-align:left; background:#fff; padding:70px 0}
.evt-wrap .notice .inner{display:inline-block; text-align:left}
.evt-wrap .notice .inner:after{content:''; display:block; clear:both}
.evt-wrap .notice .inner h4{float:left; font-size:28px; font-weight:900; color:#4c4c4c; letter-spacing:1px; margin-top:3px}
.evt-wrap .notice .inner ul{float:left; margin-left:47px}
.evt-wrap .notice .inner ul li{font-size:20px; color:#999; line-height:34px; text-align:left; text-indent:-12px; padding-left:12px}
.evt-wrap .notice .inner ul li span{font-size:17px; color:#a5a5a5}
</style>
<div class="evt-wrap">

	<!-- con -->
	<div class="con">
		<h3>온라인전용 2019FW 다운점퍼 20% 할인</h3>
		<a href="javascript:downCoupon();" class="download">쿠폰 다운로드</a>
		<a href="https://www.mlb-korea.com/display/view?dspCtgryNo=MBMA01A04A01&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA01&ctgryNoDpth2=MBMA01A04" class="mlb">MLB 다운점퍼 보러가기</a>
		<a href="https://www.mlb-korea.com/display/view?dspCtgryNo=MBMA05A04A01&currentCtgryDpthCd=3&ctgrySectCd=GNRL_CTGRY&ctgryNoDpth1=MBMA05&ctgryNoDpth2=MBMA05A04" class="kids">MLB KIDS 다운점퍼 보러가기</a>
		<img src="https://prd-static-fnf-online-mall.s3.ap-northeast-2.amazonaws.com/webresource/mlb-korea/images/display/content/promotion/kakaoplus/coupon2.jpg" alt="" />
	</div>
	<!-- //con -->


	<!-- notice -->
	<div class="notice">
		<div class="inner">
			<h4>NOTICE</h4>
			<ul>
				<li>- 온라인몰 전용 쿠폰 다운로드 페이지 입니다.</li>
				<li>- 본 쿠폰은 1ID당 각 1장(MLB,MLB KIDS)씩 발행 가능합니다.</li>
				<li>- 쿠폰 다운로드 버튼을 누르시면, MLB,MLB KIDS 쿠폰이 각 1장 발급되며 주문페이지에서 장바구니 쿠폰으로<br />사용 가능합니다. <span>(다른 쿠폰 중복사용 불가)</span></li>
				<li>- MLB와 MLB KIDS 쿠폰은 각각 발행되며, 각 브랜드의 상품만 사용 가능합니다.</li>
			</ul>
		</div>
	</div>
	<!-- //notice -->

</div>
<!-------------------- //기획전 소스 END -------------------->


				</div>
			</section>
		</main>
	</div>
</div>
<script type="text/javascript">

	var evtNo = "${event.eventExt.evtNo }";
	
	function downCoupon() {
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
			alert("로그인 후 이용 가능합니다.");
			openLayerPopupForLogin();
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			goDownCoupon();
		</sec:authorize>
	}
	
	
	
	//SNS 공유 신청
	var clickFlag = false;
	function goDownCoupon(type) {
		var phoneMobile = type; //공유여부 
		var mbrNm ="";
		
		if(clickFlag) {
			return;
		}
		$.ajax({
			type : "POST",
			async : false, 
			url : "/special/event/downCoupon.json", 		
			data : {'evtNo': evtNo, 'mbr.mbrNm': mbrNm, 'cpnPrmNm':'PR201910290000565,PR201910290000566'},
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				if(data.result.resultCode =='SUCCESS'){
					alert("쿠폰이 발행되었습니다.");
				}
				else if(data.result.resultCode =='NO_MORE_CHANCE'){
					alert("이미 발급받았습니다.");
				} 
				else if(data.result.resultCode =='INVALID_APPLCN_DATE'){
					alert("이벤트 기간이 아닙니다.");
				}
				else if(data.result.resultCode =='ERROR'){ 
					alert("이벤트 참여 처리 중 오류가 발생하였습니다.");
				}
				
				clickFlag = false;
			},
			error: function( pa_data, status, err ) {
				alert("오류가 발생하였습니다. 다시 시도하시길 바랍니다.");
				clickFlag = false;
	        }
		});
	}
</script>


		
