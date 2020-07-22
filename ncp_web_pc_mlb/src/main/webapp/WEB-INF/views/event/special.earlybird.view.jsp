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
<script type="text/javascript">
CountDownTimer('08/09/2019 10:00:00', 'newcountdown'); // [D] 디데이 설정

function CountDownTimer(dt, id){
var end = new Date(dt);

var _second = 1000;
var _minute = _second * 60;
var _hour = _minute * 60;
var _day = _hour * 24;
var timer;

function showRemaining() {
	var now = new Date();
	var distance = end - now;
	if (distance < 0) {

		clearInterval(timer);

		document.getElementById('counter1').innerHTML = '00';
		document.getElementById('counter2').innerHTML = '00';
		document.getElementById('counter3').innerHTML = '00';
		document.getElementById('counter4').innerHTML = '00';

		//document.getElementById("finish").innerHTML = '';

		return;
	}
	var days = Math.floor(distance / _day);
	var hours = Math.floor((distance % _day) / _hour);
	var minutes = Math.floor((distance % _hour) / _minute);
	var seconds = Math.floor((distance % _minute) / _second);


	if (days < 10){
		days = '0'+days;
	}
	if (hours < 10){
		hours = '0'+hours;
	}
	if (minutes < 10){
		minutes = '0'+minutes;
	}
	if (seconds < 10){
		seconds = '0'+seconds;
	}

	document.getElementById('counter1').innerHTML = days;
	document.getElementById('counter2').innerHTML = hours;
	document.getElementById('counter3').innerHTML = minutes;
	document.getElementById('counter4').innerHTML = seconds;
}

timer = setInterval(showRemaining, 1000);
}


$(function(){
	$('video').prop('muted', true);
		$('#mute-video').click( function (){
		if( $('video').prop('muted') ) {
				$('video').prop('muted', false);
				$('#mute-video').addClass('on');
		} else {
			$('video').prop('muted', true);
			$('#mute-video').removeClass('on');
		}
	});
});

function goSec(y){
	var scroll = y + $('#autoHeight', parent.document).offset().top - $('.gnb', parent.document).height();
	$('html, body', parent.document).stop().animate({scrollTop : scroll}, 1000);
}
</script>

<style>
.location-container{display:none}
.visual-area{margin:0}
#contents{width:100%;max-width:none;margin:0 0 220px;padding:0;}
#contents .tab-type06, #contents>.title03, #contents>.item-list01{width:1280px;max-width:1280px;margin-left:auto;margin-right:auto;}
.sr_only {font-size:0; text-indent:-9999px;}
.earlybird .top_visual{position:relative;	height:920px;	background:url(http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/visual.jpg) top center no-repeat}
.earlybird .top_visual .logo{position:absolute; left:0; top:80px; width:100%}
.earlybird .top_visual .tit{position:absolute; left:0; top:212px; width:100%}
.earlybird .top_visual .newcountdown{position:absolute; left:50%; top:575px; width:520px; height:120px; margin-left:-260px}
.earlybird .top_visual .newcountdown:after{display:block; clear:both; content:"";}
.earlybird .top_visual .newcountdown > div{float:left; display:inline-block; width:25%; text-align:center;}
.earlybird .top_visual .newcountdown > div span{position:relative; color:#fff; font-size:70px;}
.earlybird .top_visual .newcountdown > div span:after{position:absolute; right:100px; top:50%; width:5px; height:16px; margin-top:-8px; background:url(http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/bg_dot.png) no-repeat; content:"";}
.earlybird .top_visual .newcountdown > div:first-child span:after{background:none;}
.earlybird .top_visual .newcountdown > div p{color:#838383; font-size:17px; font-weight:700;}
.earlybird .top_visual .period{position:absolute; left:0; top:710px; width:100%}
.earlybird .top_visual .wheel{position:absolute; left:50%; bottom:53px; width:29px; margin-left:-15px}
.earlybird .top_visual .wheel img{-webkit-animation: scroll-down ease-in-out 0.7s infinite; animation: scroll-down ease-in-out 0.7s infinite}
@keyframes scroll-down{
	0%{transform: translateY(-15px)}
	50%{transform: translateY(0px)}
	100%{transform: translateY(-15px)}
}
.earlybird .sns_share h3,
.earlybird .special_gift h3{font-size:44px; font-weight:900; letter-spacing:-.01em}
.earlybird .sns_share{position:relative; width:1280px; height:490px;	margin:0 auto; color:#000; border-bottom:1px solid #d6d6d6; text-align:left}
.earlybird .sns_share .info{position:absolute; left:78px; top:140px}
.earlybird .sns_share .info p.t_txt{font-size:34px; line-height:44px; letter-spacing:-1.5px; font-weight:300; margin:15px 0 40px}
.earlybird .sns_share .info .point_txt{color:#999; font-size:16px; line-height:28px; letter-spacing: -.01em;}
.earlybird .sns_share ul.icons{position:absolute; right:85px; top:285px}
.earlybird .sns_share ul.icons:after{content:''; display:block; clear:both}
.earlybird .sns_share ul.icons li{float:left; margin-left:27px}
.earlybird .sns_share ul.icons li:first-child{margin-left:0}
.earlybird .special_gift{position:relative; width:1280px; height:527px; margin:0 auto; color:#000; background:url(http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/coffee.jpg) no-repeat 1034px 105px; text-align:left}
.earlybird .special_gift .info{position:absolute; left:78px; top:92px}
.earlybird .special_gift .info p.t_txt{font-size:34px; line-height:44px; letter-spacing:-1.5px; font-weight:300; margin:15px 0 40px}
.earlybird .special_gift .info dl{display:inline-block; margin:0 auto; padding:10px 30px; border:1px solid #000; border-radius:24px;letter-spacing:0}
.earlybird .special_gift .info dl dt,
.earlybird .special_gift .info dl dd{display:inline-block; text-align:center; font-size:18px;}
.earlybird .special_gift .info dl dt{margin-right:5px; font-weight:700;}
.earlybird .special_gift .info dl dd{font-weight:300}
.earlybird .special_gift .info dl dd.day{margin-right:30px;}
.earlybird .sms_wrap h3.band_tit{width:100%; height:70px; background:url(http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/band_tit.jpg) top center no-repeat; text-indent:-9999em}
.earlybird .sms_wrap .inner{width:960px; margin:0 auto;}
.earlybird .sms_wrap .inner strong{display:block; padding:145px 0 75px; border-bottom:4px solid #000; color:#000; font-size:46px; text-align:center; letter-spacing:-0.92px;font-weight:600; line-height:60px}
.earlybird .sms_wrap .inner strong span{font-weight:300}
.earlybird .sms_wrap .inner p.sub_txt{padding:90px 0 80px; color:#000; font-size:33px; text-align:center; font-weight:300}
.earlybird .sms_wrap .sms_form{width:658px; margin:0 auto;}
.earlybird .sms_wrap .sms_form label{padding-right:42px; color:#777; font-size:24px;}
.earlybird .sms_wrap .sms_form input{height:56px; line-height:56px; padding:0 23px; border:2px solid #e5e5e5; color:#000; font-size:24px;}
.earlybird .sms_wrap .sms_form .input.name input{width:488px; margin-bottom:15px;}
.earlybird .sms_wrap .sms_form .input.number input{width:160px;}
.earlybird .sms_wrap .sms_form .input.number input#ifrMb01{width:129px;}
.earlybird .sms_wrap .sms_form .input.number input#num{width:129px;}
.earlybird .sms_wrap .sms_form .input.number span{display:inline-block; width:14px; text-align:center;}
.earlybird .sms_wrap .privacy{margin-top:80px;}
.earlybird .sms_wrap .privacy .policy_wrap{height:163px; margin-bottom:20px; padding:43px 48px; text-align:left; border:2px solid #e5e5e5; background:#f8f8f8; overflow-y:scroll;box-sizing:border-box;}
.earlybird .sms_wrap .privacy .policy_wrap h4{padding-bottom:15px; color:#000; font-size:20px;}
.earlybird .sms_wrap .privacy .policy_wrap p{padding-bottom:30px; color:#777; font-size:16px; line-height:24px;}
.earlybird .sms_wrap .privacy .policy_wrap p.sub{padding-bottom:10px;}
.earlybird .sms_wrap .privacy .policy_wrap table{width:100%; table-layout:fixed; margin-bottom:57px; border-bottom:1px solid #b8b8b8;}
.earlybird .sms_wrap .privacy .policy_wrap table tr>*{padding:5px; white-space:normal; word-break:break-word; word-wrap:break-word;}
.earlybird .sms_wrap .privacy .policy_wrap table tr.nowrap>*{white-space:nowrap; letter-spacing:-.05em;}
.earlybird .sms_wrap .privacy .policy_wrap table th{text-align:center; background:#999; color:#fff; font-size:16px;}
.earlybird .sms_wrap .privacy .policy_wrap table td{color:#888; border-left:1px solid #e6e6e6; border-top:1px solid #e6e6e6; font-size:14px;}
.earlybird .sms_wrap .privacy .policy_wrap table.center td{text-align:center;}
.earlybird .sms_wrap .privacy .policy_wrap table td.none,
.earlybird .sms_wrap .privacy .policy_wrap table tr>td:first-child{border-left:0;}
.earlybird .sms_wrap .privacy .form_chk{position:relative; text-align:left}
.earlybird .sms_wrap .privacy .form_chk span{display:inline-block;padding-bottom:7px;border-bottom:1px solid #666; margin-left:50px}
.earlybird .sms_wrap .privacy .form_chk span a{display:inline-block; color:#000; font-size:20px;}
.earlybird .sms_wrap .privacy .form_chk p.chk{position:absolute; top:0; right:0;}
.earlybird .sms_wrap .privacy .form_chk .checkbox{position:relative; overflow:hidden; vertical-align:middle;}
.earlybird .sms_wrap .privacy .form_chk .checkbox{position:absolute; left:-1px; overflow:hidden; width:1px; height:1px; padding:0; border:0; visibility:hidden;}
.earlybird .sms_wrap .privacy .form_chk .checkbox+label{display:inline-block; height:30px; padding-left:40px; color:#000; font-size:22px; line-height:30px; background:url(http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/checkbox.jpg) no-repeat left 0;}
.earlybird .sms_wrap .privacy .form_chk .checkbox:checked+label{background-position:0 -30px;}
.earlybird .evt_notice{width:960px; margin:120px auto 0; text-align:left}
.earlybird .evt_notice h5{padding-bottom:15px; color:#000; font-size:24px; font-weight:500;}
.earlybird .evt_notice ul li{position:relative; font-size:18px; color:#777; padding-left:10px; line-height:30px; font-weight:300; letter-spacing: -.02em;}
.earlybird .evt_notice ul li:before{position:absolute; left:0; top:14px; width:5px; height:1px; background:#777; content:"";}
.earlybird .btn a {display:inline-block; width:360px; height:80px; line-height:80px; border:1px solid #fff; background:#000; color:#fff; font-size:24px;}
.earlybird .btn_confirm{width:960px; margin:70px auto 0; text-align:center;}
</style>

<div class="earlybird">
	<div class="top_visual">
		<!-- 동영상 플레이 (추후 변경 예정) -->
		<div class="video_wrap"></div>
		<!-- // 동영상 플레이 (추후 변경 예정) -->

		<div class="logo"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/logo.png" alt="MLB & MLBKIDS" /></div>
		<div class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/visual_tit.png" alt="19FW MLB 메가 다운 EARLY BIRD EVENT" /></div>

		<!-- D-DAY 노출 -->
		<div class="newcountdown">
			<div>
				<span id="counter1"></span>
				<p>DAYS</p>
			</div>
			<div>
				<span id="counter2"></span>
				<p>HOURS</p>
			</div>
			<div>
				<span id="counter3"></span>
				<p>MINUTES</p>
			</div>
			<div>
				<span id="counter4"></span>
				<p>SEC</p>
			</div>
		</div>
		<!-- // D-DAY 노출 -->

		<div class="period"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/period.png" alt="2019.08.09 10AM OPEN" /></div>
		<div class="wheel"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/arrow.png" alt="" /></div>
	</div>

	<div class="sns_share">
		<div class="info">
			<h3>SNS 공유하기 이벤트</h3>
			<p class="t_txt">이벤트 페이지를 SNS에 공유만 해도<br />적립금 2,000원 증정!</p>
			<span class="point_txt">
				-회원 전용으로 공유횟수 상관없이 1ID당 1번 증정합니다.<br />
				-이벤트 참여 적립금은 8월 9일 일괄 지급 예정입니다.
			</span>
		</div>
		<ul class="icons">
			<li><a href="javascript:insertSnsShare('facebook')"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_fb.png" alt="페이스북공유하기" /></a></li>
			<li><a href="javascript:insertSnsShare('kakaostory')"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_kakao.png" alt="카카오스토리공유하기" /></a></li>
			<li><a href="javascript:insertSnsShare('naver')"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_blog.png" alt="블로기공유하기" /></a></li>
			<li><a href="javascript:insertSnsShare('url')"><img src="http://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_url.png" alt="URL공유하기" /></a></li>
		</ul>
	</div>

	<div class="special_gift">
		<div class="info">
			<h3>알림신청 GIFT</h3>
			<p class="t_txt">다운 선판매 행사 알림 신청자 중 100명을 추첨하여<br>스타벅스 아메리카노를 보내 드립니다.</p>
			<dl>
				<dt>기간 : </dt>
				<dd class="day">~ 8/8일까지</dd>
				<dt>당첨자 공지 : </dt>
				<dd>8/13(개별연락)</dd>
			</dl>
		</div>
	</div>

	<div id="link" class="sms_wrap">
		<h3 class="band_tit">2019 F/W DOWN EARLY BIRD SMS</h3>
		<div class="inner">
			<strong>MLB<span>&</span>MLBKIDS<br />다운 선판매 오픈 알림 신청 작성</strong>
			<p class="sub_txt">19F/W 다운 얼리버드 판매 오픈 당일 행사 알림 SMS를 보내 드립니다. </p>

			<!-- 신청 정보 입력 -->
			<div class="sms_form">
				<form action="javascript:void(0);return false;" method="POST" id="register_form" name="register_form">
					<fieldset>
						<legend>알람 신청 폼</legend>
						<div class="input name">
							<label for="name">신청자 이름</label>
							<input type="text" id="appliNm">
						</div>
						<div class="input number">
							<label for="num">휴대폰 번호</label>
							<input type="tel" class="phone_number" name="mobile_number1" id="ifrMb01" style="margin-left:0;" title="휴대폰 통신사 번호 입력창" maxlength="3">
							<span>-</span>
							<input type="tel" class="phone_number" name="mobile_number2" id="ifrMb02" title="휴대폰 번호 중간 입력창" maxlength="4">
							<span>-</span>
							<input type="tel" class="phone_number" name="mobile_number3" id="ifrMb03" title="휴대폰 번호 마지막 입력창" maxlength="4">
						</div>
					</fieldset>
				</form>
			</div>
			<!-- // 신청 정보 입력 -->

			<!-- 개인정보취급방침 -->
			<div class="privacy">
				<div class="policy_wrap">
					<h4 id="policySec02">[제2조] 개인정보의 수집 및 이용목적</h4>
					<p>회사는 다음의 목적을 위해 개인정보를 수집합니다. 회사가 수집한 개인정보는 다음의 목적 이외의 용도로는 사용하지 않으며, 이용 목적이 변경될 경우 회사는 개인정보보호법 등 관련 법령에 따라 고객으로부터 별도의 동의를 받는 등 필요한 조치를 이행할 것 입니다.</p>
					<p>1) 회원관리(회원가입을 하는 경우에 한함) 회원 가입 시 본인확인 절차에 이용, 고지사항 전달, 청구서, 정확한 상품 배송지 확보, 불만처리, 개인식별 등 상담요청에 대한 회신<br>
					2) 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산 콘텐츠 제공, 물품(상품 또는 경품) 배송 또는 청구서 등 발송, 금융거래, 본인인증, 구매 및 대금결제, 요금추심, 신상품이나 이벤트 정보안내<br>
					3) 이벤트 정보 안내 등 광고성 정보 전달 및 서비스 품질 향상을 위한 각종 설문조사<br>
					4) 단, “회원”이 서비스 혜택 정보 제공을 원치 않는다는 의사를 명확히 밝히는 경우 정보 제공을 하지 않으며, 이 경우 “회원”에게 정보 미 제공으로 인한 불이익이 발생하더라도 “회사”가 책임지지 않습니다.</p>

					<h4 id="policySec03">[제3조] 비회원 고객 개인정보 처리</h4>
					<p>1) 회사는 회원 고객뿐만 아니라 비회원 고객 또한 물품 및 서비스 상품 구매할 수 있으며, 비회원 주문의 경우 배송 및 대금결제, 상품 배송에 반드시 필요한 개인정보만을 본인에게 요청하고 있습니다.<br>
					2) 회사는 비회원 고객으로 구매한 비회원 고객이 입력한 지불인 정보 및 수령인 정보는 대금 결제 및 상품 배송에 관련한 용도 외에는 다른 어떠한 용도로도 이용되지 않습니다.</p>

					<h4 id="policySec04">[제4조] 개인정보 보유/이용기간</h4>
					<p>이용자의 개인정보는 원칙적으로 개인정보의 수집목적 또는 제공받은 목적이 달성되면 지체 없이 파기합니다. 다만, 상법, 전자상거래 등에서의 소비자보호에 관한 법률 등 관계법령의 규정에 의하여 보존할 필요가 있는 경우 “회사”는 관계법령에서 정한 일정한 기간 동안 “회원”정보를 보관합니다. 이 경우 “회사”는 보관하는 정보를 그 보관의 목적으로만 이용하며 보존기간은 아래와 같습니다</p>

					<p class="sub">1) “회사” 내부 방침에 의한 정보보유 사유</p>

					<table class="table center" cellpadding="0" cellspacing="0" border="0">
						<tbody>
						<tr class="nowrap">
							<th scope="col" width="33.33%">구분</th>
							<th scope="col" width="33.33%">보존이유</th>
							<th scope="col" width="auto">보존기간</th>
						</tr>
						<tr>
							<td>부정이용기록</td>
							<td>부정 이용 방지</td>
							<td>1년</td>
						</tr>
						</tbody>
					</table>

					<p class="sub">2) 관련법령에 의한 정보보유 사유</p>

					<table class="table center" cellpadding="0" cellspacing="0" border="0">
						<tbody>
						<tr class="nowrap">
							<th scope="col" width="40%">구분</th>
							<th scope="col" width="40%">보존이유</th>
							<th scope="col" width="auto">보존기간</th>
						</tr>
						<tr>
							<td>방문에 관한 기록</td>
							<td>통신비밀보호법</td>
							<td>3개월</td>
						</tr>
						<tr>
							<td>본인 확인에 관한 기록</td>
							<td>정보통신 이용촉진 및 정보보호 등에 관한 법률</td>
							<td>6개월</td>
						</tr>
						<tr>
							<td>계약 또는 청약철회 등에 관한 기록</td>
							<td>전자상거래 등에서의 소비자보호에 관한 법률</td>
							<td>5년</td>
						</tr>
						<tr>
							<td>대금결제 및 재화 등의 공급에 관한 기록</td>
							<td>전자상거래 등에서의 소비자보호에 관한 법률</td>
							<td>5년</td>
						</tr>
						<tr>
							<td>소비자의 불만 또는 분쟁처리에 관한 기록</td>
							<td>전자상거래 등에서의 소비자보호에 관한 법률</td>
							<td>3년</td>
						</tr>
						</tbody>
					</table>

					<p class="sub">3) 개인정보 유효기간 시행</p>
					<p class="sub">회사는 「정보통신망 이용촉진 및 정보보호 등에 관한 법률」 제29조 제2항 및 동법 시행령 제16조의 규정에 따라 1년간 이용내역이 없는 회원의 개인정보를 회사는 법정보관기간 경과 후 파기하며, 이에 따라 회원은 회원자격을 상실할 수 있습니다. 또한, 파기 30일 전까지 개인정보가 파기되는 사실과 기간 만료 일 및 해당 개인정보의 항목을 전자우편•서면•모사전송•전화 또는 이와 유사한 방법 중 어느 하나의 방법으로 회원에게 알립니다.</p>
				</div>
				<div class="form_chk">
					<span><a href="https://m.mlb-korea.com/common/information/termsAndConditions?agreementNo=2" target="_blank">+ 전문 보기</a></span>
					<p class="chk">
							<input type="checkbox" name="agree_flag" class="checkbox" id="check" value="Y" checked="checked">
						<label for="check">수신동의</label>
					</p>
				</div>
			</div>
			<!-- // 개인정보취급방침 -->
		</div>
	</div>

	<div class="evt_notice">
		<h5>SMS 발송 안내</h5>
		<ul>
			<li>얼리버드 오픈 알림을 신청하시면 8월 9일 행사 오픈 당일 SMS를 받으실 수 있습니다.</li>
			<li>수신동의 여부는 회원/비회원 관계 없이 이번 행사 알림 및 경품 지급 시에만 사용되며 안내 SMS 발송 및 경품 발송 이후 신청 정보는<br />파기 됩니다.</li>
		</ul>
	</div>
	<div class="btn btn_sms btn_confirm">
		<a href="javascript:;" onclick="earlyBirdRequest();">알림 신청</a> <!-- //[D] 알림 신청 버튼 -->
	</div>
</div>
<!-------------------- //기획전 소스 END -------------------->

					</div>
				</section>
				

			</main>
			
		</div>
	</div>
<script type="text/javascript">
 
	var evtNo = "${event.eventExt.evtNo }";
	$(document).ready(function(){
 
		$("#joinEvent").click(function(){
	 
			joinEvent(evtNo,'<c:url value="/special/event/joinEventRenewal01.json"/>');
		});
		$("#joinEvent2").click(function(){
			 
			joinEvent(evtNo,'<c:url value="/special/event/joinEventRenewal02.json"/>');
		});
		$("#eventalert .d_layer_close").click(function(){
		//	location.reload();
		});
	});
 
	function memberLogin(){
	  location.href = "/member/login/view?loginTarget="+encodeURI(document.URL);
	}
 
  
	// 이벤트 응모 가능 여부 체크 : 응모가능 기간 체크
	function joinEvent(evtNo,url){
		var success = false;
		$.ajax({
			type : "GET",
			//dataType : "json",
			async : false,
			url : url, 		
			data : {'evtNo': evtNo},
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
			success : function(data) {
				if(data.result.resultCode =='NOT_LOGIN'){
					layerPopup.popupOpenNow('#layerPopupNotLogin');
				}else{
					$('#eventtext').text(data.result.resultMsg);
					layerPopup.popupOpenNow('#eventalert');
				}
			},
			error: function( pa_data, status, err ) {
				alert("checkEventEnable error");
	        }
		});
		
		return success;
	}
</script>
		<article id="layerPopupNotLogin" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>구매 이벤트</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt03">로그인 후 참여 가능합니다. 로그인 하시겠습니까?</p>
					 
				</div>
				<div class="btn-wrap03">
			    <a href="javascript:memberLogin()" class="btn-style02">로그인 하기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
	    <article id="eventalert" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2>구매 이벤트</h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt" id="eventtext"> </p>
				</div>
				<div class="btn-wrap03">
					<a href="#" class="btn-style02 d_layer_close">닫기</a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
<script type="text/javascript">

	var evtNo = "${event.eventExt.evtNo }";

	function earlyBirdRequest() {
		validEarlyBirdRequest();
	}

	function validEarlyBirdRequest(){
		if($("#appliNm").val()==""){
			alert("이름, 휴대폰번호 입력 후 신청 가능합니다.");
			$('#appliNm').focus();
 			return false;
		}
		if($("#ifrMb01").val()==""||$("#ifrMb02").val()==""||$("#ifrMb03").val()==""){
			alert("이름, 휴대폰번호 입력 후 신청 가능합니다.");
			
			if($("#ifrMb01").val()==""){
				$('#ifrMb01').focus();
			}else if($("#ifrMb02").val()==""){
				$('#ifrMb02').focus();
			}else{
				$('#ifrMb03').focus();
			}
			return false;
		}
		if($("#check").is(":checked")==false){
			alert("수신동의 후 신청이 가능합니다.");
			$('#check').focus();
			return false;
		}
		
		goEarlyBirdRequest();
	}
	
	// 입고알림신청
	var clickFlag = false;
	function goEarlyBirdRequest() {
		
		var mobilAreaNo = $("#ifrMb01").val();
		var mobilTlofNo = $("#ifrMb02").val();
		var mobilTlofWthnNo = $("#ifrMb03").val();
		var applicantNm = $('#appliNm').val();
		var num1 = /[0-9]{2,3}/;
		var num2 = /[0-9]{3,4}/;
		var num3 = /[0-9]{4}/;
		
		var phoneMobile = mobilAreaNo+'-'+mobilTlofNo+'-'+mobilTlofWthnNo;
		var mbrNm = $("#appliNm").val();
		
		// phone number check
		if(!(num1.test(mobilAreaNo) && num2.test(mobilTlofNo) && num3.test(mobilTlofWthnNo) )) {
			alert('유효한 휴대전화번호를 입력해 주세요.');
			return;
		}
		
		if(clickFlag) {
			return;
		}
		$.ajax({
			type : "POST",
			async : false,
			url : "/special/event/earlyBirdRequest.json", 		
			data : {'evtNo': evtNo, 'mbr.mbrNm': mbrNm, 'phoneMobile':phoneMobile },
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
				pointClickFlag = true;
			},
			success : function(data) {
				if(data.result.resultCode =='SUCCESS'){
					alert(data.result.resultMessage);
					$("#ifrMb01").val("");
					$("#ifrMb02").val("");
					$("#ifrMb03").val("");
					$("#appliNm").val("");
				}
				else if(data.result.resultCode =='NO_MORE_CHANCE'){
					alert(data.result.resultMessage);
				}
				else if(data.result.resultCode =='INVALID_APPLCN_DATE'){
					alert("이벤트 기간이 아닙니다.");
				}
				else {
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
	
	
	function insertSnsShare(type) {
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
			alert("로그인 후 이용 가능합니다.");
			openLayerPopupForLogin();
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
			goSnsShare(type);
		</sec:authorize>
	}
	
	
	function checkType(type){
		if(type=='url'){
			var trb= location.href;
			var IE=(document.all)?true:false;
			if (IE) {
				if(confirm("이 글의 트랙백 주소를 클립보드에 복사하시겠습니까?")) window.clipboardData.setData("Text", trb);
			} else {
				temp = prompt("이 글의 트랙백 주소입니다. Ctrl+C를 눌러 클립보드로 복사하세요", trb);
			}
		}else if(type=='naver'){
			var url= document.location.href;
			window.open('http://share.naver.com/web/shareView.nhn?url='+encodeURIComponent(url)+'&title='+encodeURIComponent(document.title), 'naversharedialog', 'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=600,width=600');
			
		}else{
			jsShareSns(type);
		}
	}
	
	//SNS 공유 신청
	var clickFlag = false;
	function goSnsShare(type) {
		
		var phoneMobile = type; //공유여부 
		var mbrNm ="";
		
		if(clickFlag) {
			return;
		}
		$.ajax({
			type : "POST",
			async : false, 
			url : "/special/event/earlyBirdRequest.json", 		
			data : {'evtNo': evtNo, 'mbr.mbrNm': mbrNm, 'phoneMobile':phoneMobile },
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
				pointClickFlag = true;
			},
			success : function(data) {
				if(data.result.resultCode =='SUCCESS'){
					checkType(type);
				}
				else if(data.result.resultCode =='NO_MORE_CHANCE'){
					checkType(type);
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
<!-- 1907 선판매 티징 : E -->    



		
