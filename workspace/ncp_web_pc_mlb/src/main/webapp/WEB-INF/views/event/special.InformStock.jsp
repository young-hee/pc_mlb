<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/event/event.js?v=${_version}"></script>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<div class="contain dp promo view" id="contain" style="padding-top: 52px; min-height:642px;">
<div class="container">
<main class="contents" id="contents">
<section class="pm_vis_area">
<div class="vs_html">
<!-------------------- 기획전 소스 START -------------------->
<link rel="stylesheet" type="text/css" href="https://static.mlb-korea.com/motioneye/common/js/pagepiling/jquery.pagepiling.css" />
<script type="text/javascript" src="https://static.mlb-korea.com/motioneye/common/js/pagepiling/jquery.pagepiling.min.js"></script>
<style type="text/css">
#wrap{overflow:hidden}
.top_cate_box,
.pm_top,
.sec_free_deily,
.foot{display:none}
.contain{padding-bottom:0 !important}
.container{padding-top:0 !important}
.pm_vis_area{margin-bottom:0 !important}
.vs_html{max-width:100% !important}
.event{position:relative; width:100%; height:100%}
.event strong{font-weight:700}
.event .wheel{position:fixed; left:0; bottom:27px; width:100%; height:56px; transition:opacity 0.5s ease; z-index:1000}
.event .wheel.off{opacity:0}
.event .wheel img {
	-webkit-animation: scroll-down ease-in-out 1s infinite;
	animation: scroll-down ease-in-out 1s infinite;
}
@keyframes scroll-down {
		0%{ transform: translateY(-8px); }
		50%{ transform: translateY(0px); }
		100%{ transform: translateY(-8px); }
}
.event .sec1{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec1_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec1 .con{position:absolute; left:0; top:50%; margin-top:-20.312vw; width:100%}
.event .sec1 .con .top{display:inline-block; width:36.458vw; height:30px; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec1_line.png) center no-repeat; background-size:100%}
.event .sec1 .con .top p{display:inline-block; font-size:1.145vw; font-weight:700; color:#a38e74; line-height:30px; background:#060606; letter-spacing:5px; padding:0 20px}
.event .sec1 .con .top p img{width:3.020vw; vertical-align:middle; margin-bottom:6px}
.event .sec1 .con p.tit{margin:2.083vw 0}
.event .sec1 .con p.tit img{width:36.458%}
.event .sec1 .con p.txt img{width:36.458%}
.event .sec2{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec2_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec2 img{width:100%}
.event .sec2 p.tit{opacity:0; transition:opacity 1s}
.event .sec2 p.tit img{width:26.927vw}
.event .sec2 .txt{position:relative; height:5.3125vw; margin:2.864vw 0 4.427vw}
.event .sec2 .txt p{position:absolute; left:0; top:50px; width:100%; font-size:1.041vw; line-height:1.822vw; color:#fff; letter-spacing:0.5px; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec2 p.txt strong{font-weight:700}
.event .sec2 .box{position:relative; width:100%; height:13.489vw}
.event .sec2 .box .inner{position:absolute; left:0; top:50px; width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec2_box.png) center top no-repeat; background-size:auto 100%; padding-top:6.25vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec2 .box .inner p{font-size:0.885vw; line-height:1.406vw}
.event .sec2.active p.tit{opacity:1; transition:opacity 1s 0.7s}
.event .sec2.active .txt p{opacity:1; top:0; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec2.active .box .inner{opacity:1; top:0; transition:opacity 1s 1.7s ease, top 1s 1.7s ease}
.event .sec3{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec3_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec3 .con{text-align:left; margin-left:58.854%; width:31.25%}
.event .sec3 p.tit{opacity:0; transition:opacity 1s}
.event .sec3 p.tit img{width:100%}
.event .sec3 .txt{position:relative; height:5.3125vw; margin-top:1.822vw}
.event .sec3 .txt p{position:absolute; left:0; top:30px; font-size:1.041vw; color:#fff; line-height:1.822vw; letter-spacing:0.5px; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec3.active p.tit{opacity:1; transition:opacity 1s 0.7s}
.event .sec3.active .txt p{opacity:1; top:0; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec4{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec4_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec4 .con{position:absolute; left:16.406%; top:50%; margin-top:-14.84375vw; text-align:left; width:31.25%}
.event .sec4 p.tit{opacity:0; transition:opacity 1s}
.event .sec4 p.tit img{width:100%}
.event .sec4 .txt{position:relative; height:5.3125vw; margin-top:1.822vw}
.event .sec4 .txt p{position:absolute; left:0; top:30px; font-size:1.041vw; color:#fff; line-height:1.822vw; letter-spacing:0.5px; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec4.active p.tit{opacity:1; transition:opacity 1s 0.7s}
.event .sec4.active .txt p{opacity:1; top:0; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec5{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec5_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec5 .con{position:absolute; left:59.114%; top:50%; margin-top:-18.229vw; text-align:left; width:31.25%}
.event .sec5 p.tit{opacity:0; transition:opacity 1s}
.event .sec5 p.tit img{width:100%}
.event .sec5 .txt{position:relative; height:5.3125vw; margin-top:1.822vw}
.event .sec5 .txt p{position:absolute; left:0; top:30px; font-size:1.041vw; color:#fff; line-height:1.822vw; letter-spacing:0.5px; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec5.active p.tit{opacity:1; transition:opacity 1s 0.7s}
.event .sec5.active .txt p{opacity:1; top:0; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec6{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec6_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec6 img{width:100%}
.event .sec6 .item1 .img{position:absolute; left:19.947%; top:60%; width:25.677%; margin-top:-21.875vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec6 .item1 .tooltip{position:absolute; left:34.375%; top:60%; margin-top:-11.562vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec6 .item1 .tooltip_v{position:absolute; left:23.958%; top:50%; width:25%; margin-top:-17.187vw}
.event .sec6 .item2 .img{position:absolute; left:48.750%; top:60%; width:31.302%; margin-top:-1.614vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec6 .item2 .tooltip{position:absolute; left:61.510%; top:60%; margin-top:12.447vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec6 .item2 .tooltip_v{position:absolute; left:51.093%; top:50%; width:25%; margin-top:6.822vw}
.event .sec6.active .item1 .img{top:50%; opacity:1; transition:opacity 1s 0.7s ease, top 1s 0.7s ease}
.event .sec6.active .item1 .tooltip{top:50%; opacity:1; transition:opacity 1s 0.7s ease, top 1s 0.7s ease}
.event .sec6.active .item2 .img{top:50%; opacity:1; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec6.active .item2 .tooltip{top:50%; opacity:1; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec7{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec7_bg.jpg) center no-repeat; background-size:cover; overflow:hidden}
.event .sec7 img{width:100%}
.event .sec7 .item1 .img{position:absolute; left:13.073%; top:60%; width:39.167%; margin-top:-22.968vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item1 .tooltip.tt1{position:absolute; left:32.343%; top:60%; margin-top:-16.645vw; z-index:200; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item1 .tooltip_v.tt1{position:absolute; left:20.885%; top:50%; width:26.562%; margin-top:-22.270vw}
.event .sec7 .item1 .tooltip.tt2{position:absolute; left:24.322%; top:60%; margin-top:-11.666vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item1 .tooltip_v.tt2{position:absolute; left:14.947%; top:50%; width:22.916%; margin-top:-17.291vw}
.event .sec7 .item2 .img{position:absolute; left:48.281%; top:60%; width:25.990%; margin-top:3.645vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item2 .img2{position:absolute; left:68.854%; top:60%; width:13.854%; margin-top:-8.229vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item2 .tooltip{position:absolute; left:60.520%; top:60%; margin-top:8.854vw; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec7 .item2 .tooltip_v{position:absolute; left:51.093%; top:50%; width:22.916%; margin-top:3.229vw}
.event .sec7.active .item1 .img{top:50%; opacity:1; transition:opacity 1s 0.7s ease, top 1s 0.7s ease}
.event .sec7.active .item1 .tooltip.tt1{top:50%; opacity:1; transition:opacity 1s 0.7s ease, top 1s 0.7s ease}
.event .sec7.active .item1 .tooltip.tt2{top:50%; opacity:1; transition:opacity 1s 0.7s ease, top 1s 0.7s ease}
.event .sec7.active .item2 .img2{top:50%; opacity:1; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec7.active .item2 .img{top:50%; opacity:1; transition:opacity 1s 1.5s ease, top 1s 1.5s ease}
.event .sec7.active .item2 .tooltip{top:50%; opacity:1; transition:opacity 1s 1.5s ease, top 1s 1.5s ease}
.event .sec8{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec8_bg.jpg) center no-repeat; background-size:cover}
.event .sec8 .con{position:absolute; left:19.635%; top:50%; margin-top:-15.885vw; text-align:left; width:31.25%}
.event .sec8 p.tit{opacity:0; transition:opacity 1s}
.event .sec8 p.tit img{width:100%}
.event .sec8 .txt{position:relative; height:1.822vw; margin-top:1.562vw}
.event .sec8 .txt p{position:absolute; left:0; top:30px; font-size:1.041vw; color:#fff; line-height:1.822vw; letter-spacing:0.5px; opacity:0; transition:opacity 1.0s, top 1.0s}
.event .sec8.active p.tit{opacity:1; transition:opacity 1s 0.7s}
.event .sec8.active .txt p{opacity:1; top:0; transition:opacity 1s 1.2s ease, top 1s 1.2s ease}
.event .sec9{width:100%; height:100%; background:url(http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec9_bg.jpg) center no-repeat; background-size:cover}
.event .sec9 img{width:100%}
.event .sec9 .con p.tit{position:absolute; left:0; top:50%; width:100%; margin-top:-20.76041666666667vw}
.event .sec9 .con p.tit img{width:40.833%}
.event .sec9 .con .red .img{position:absolute; left:19.010%; top:50%; width:30%; margin-top:-17.3435vw}
.event .sec9 .con .red .buy{position:absolute; left:28.697%; top:50%; width:10.625%; margin-top:8.541vw}
.event .sec9 .con .red .tooltip{position:absolute; left:41.510%; top:50%; margin-top:0.5208vw}
.event .sec9 .con .red .tooltip_v{position:absolute; left:28.020%; top:50%; width:29.166%; margin-top:-19.583vw}
.event .sec9 .con .red .tooltip_v .txt{padding-top:12.552vw}
.event .sec9 .con .red .tooltip_v .txt strong{display:block; font-size:1.25vw; line-height:1.5625vw; letter-spacing:0.5px; margin-bottom:0.416vw}
.event .sec9 .con .red .tooltip_v .txt p{font-size:1.041vw; line-height:1.5625vw; letter-spacing:0.5px}
.event .sec9 .con .sold_out{position:absolute; left:20.520%; top:50%; width:26.979%; margin-top:-14.322vw; z-index:1000}
.event .sec9 .con .navy .img{position:absolute; right:19.010%; top:50%; width:30%; margin-top:-17.3435vw}
.event .sec9 .con .navy .noti{position:absolute; left:60.572%; top:50%; width:10.885%; margin-top:8.541vw; z-index:1100}
.event .sec9 .con .navy .coming{position:absolute; right:20.520%; top:50%; width:26.979%; margin-top:-14.322vw; z-index:1000}
.event .sec9 .con .navy .buy{position:absolute; right:28.697%; top:50%; width:10.625%; margin-top:8.541vw}
.event .sec9 .con .navy .tooltip{position:absolute; left:73.437%; top:50%; margin-top:0.5208vw; z-index:1200}
.event .sec9 .con .navy .tooltip_v{position:absolute; left:51.197%; top:50%; width:29.166%; margin-top:-19.583vw; z-index:1300}
.event .sec9 .con .navy .tooltip_v .txt{padding-top:12.552vw}
.event .sec9 .con .navy .tooltip_v .txt strong{display:block; font-size:1.25vw; line-height:1.5625vw; letter-spacing:0.5px; margin-bottom:0.416vw}
.event .sec9 .con .navy .tooltip_v .txt p{font-size:1.041vw; line-height:1.5625vw; letter-spacing:0.5px}
.event .sec9 .con .navy .sold_out{left:auto;right:20.520%}
.event .sec9 .notice{position:absolute; left:50%; bottom:1.700%; width:37.239%; margin-left:-18.619%; text-align:center} /*715*/
.event .sec9 .notice dl{display:inline-block; text-align:left}
.event .sec9 .notice dl:after{content:''; display:block; clear:both}
.event .sec9 .notice dl dt{position:relative; float:left; width:8.020vw; font-size:1.562vw; font-weight:700; color:#312c2b; padding-top:1.370vw}
.event .sec9 .notice dl dd{float:left}
.event .sec9 .notice dl dd li{font-size:0.885vw; line-height:1.154vw; color:#312c2b;}
.event .tooltip{position:absolute; width:4.166%; height:4.166vw; opacity:1; transition:opacity 0.5s ease}
.event .tooltip.on{opacity:1; transition:opacity 0.5s ease}
.event .tooltip .pulse{width:100%; height:100%; border-radius:100%; background:#fff; animation:scaleout 1.5s infinite ease-in-out}
.event .tooltip .point{position:absolute; top:50%; left:50%; width:22.5%; height:22.5%; margin:-11.25% 0 0 -11.25%; background:#fff; border-radius:100%; text-indent:-9999em}
@keyframes scaleout{
	0%{transform:scale(0)}
	100%{transform:scale(1); opacity:0;filter:alpha(opacity=0)}
}
.event .tooltip_v{position:absolute; left:0; top:0; opacity:0; z-index:100; transition:opacity 0.5s ease}
.event .tooltip_v.on{opacity:1}
.event .tooltip_v .txt{position:absolute; left:0; top:0; width:100%; height:89.430%}
.event .tooltip_v .txt .inner{display:table; width:100%; height:100%}
.event .tooltip_v .txt .inner p{display:table-cell; vertical-align:middle; font-size:1.041vw; line-height:1.562vw}
</style>
<script type="text/javascript">
$(function(){
	$('#pagepiling').pagepiling({
		anchors: ['1', '2', '3', '4', '5', '6', '7', '8', '9' ],
		navigation: false,
		afterLoad: function(anchorLink, index){
		},
		onLeave: function(index, nextIndex, direction){
			if(nextIndex == 9)
			{
				$(".event .wheel").addClass("off");
			}else{
				$(".event .wheel").removeClass("off");
			}
			$(".event .tooltip_v").removeClass("on");
		},
	});

	$(".tooltip a.point").click(function(e){
		e.preventDefault();
		if(!$(this).parent().next(".tooltip_v").hasClass("on"))
		{
			$(".event .tooltip_v").removeClass("on");
			$(this).parent().next(".tooltip_v").addClass("on");
		}else{
			$(".event .tooltip_v").removeClass("on");
		}
	});

	if($(".head").css("display") == "block")
	{
		head_h = $(".head").height();
	}
	resize_page();

	$(".head .btnClose").click(function(){
		head_h = $(".head .header").height();
		resize_page();
	});
});


// resize
$(window).resize(function(){
	resize_page();
});

var head_h = 0;
function resize_page()
{
	$(".event").height( $(window).height() - head_h );
}
</script>

<div class="event" id="pagepiling">

	<!-- sec1 -->
	<div class="section sec1">
		<div class="con">
			<div class="top">
				<p>전세계 <img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec1_161.png " alt="161" /> 개 한정판</p>
			</div>
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec1_tit.png " alt="161 HERITAGE CAP LIMITED EDITION" /></p>
			<p class="txt"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec1_txt.png " alt="161 St, NEW YORK, USA Since the 1800s" /></p>
		</div>
	</div>
	<!-- //sec1 -->


	<!-- sec2 -->
	<div class="section sec2">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec2_tit.png" alt="161 HERITIAGE SERIES LIMITED EDITION" /></p>
			<div class="txt">
				<p>
					161 HERITIAGE CAP은 1800년대 MLB의 선수들이 쓰던 모자를 헤리티지 감성으로 그대로 재현한 디자인입니다.<br />
					NEWYORK YANKEES 구단의 *<strong>COOPERSTOWN</strong> 로고 디자인을 모티브로 사용하였고,<br />
					구단의 오리지널 야구장 주소인 161번가에서 착안하여, 모자에 각 하나의 숫자만 자수로 새겨 한정판의 특별함을 더했습니다.
				</p>
			</div>
			<div class="box">
				<div class="inner">
					<p>
						*<strong>COOPERSTOWN</strong>은 MLB의 최초 발생지로 상징적인 의미가 있습니다.<br />
						쿠퍼스타운 시리즈 로고는 전통 있는 구단의 최초 팀로고와 캐릭터를 모티브로 사용함으로써,<br />
						MLB의 오리지널리티와 헤리티지를 보여줄 수 있는 라인입니다.
					</p>
				</div>
			</div>
		</div>
	</div>
	<!-- //sec2 -->


	<!-- sec3 -->
	<div class="section sec3">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec3_tit.png" alt="NUMBERED # 161" /></p>
			<div class="txt">
				<p>
					구단의 오리지널 야구장 주소 161번가에서 따온 숫자 161을 모티브로<br />
					161개의 숫자를 모자의 측면에 하나하나 자수로 새겨<br />
					161개의 헤리티지 캡이 탄생했습니다.
				</p>
			</div>
		</div>
	</div>
	<!-- //sec3 -->


	<!-- sec4 -->
	<div class="section sec4">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec4_tit.png" alt="NUMBERED # 161" /></p>
			<div class="txt">
				<p>
					1800년대의 오리지널 야구모자를 그대로 재현하기 위해<br />
					소재, 색상, 형태, 로고 등 헤리티지 감성을 담아<br />
					현 시대에 맞게 디자인을 재해석했습니다.
				</p>
			</div>
		</div>
	</div>
	<!-- //sec4 -->


	<!-- sec5 -->
	<div class="section sec5">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec5_tit.png" alt="NUMBERED # 161" /></p>
			<div class="txt">
				<p>
					1부터 161까지의 숫자가 새겨진 리미티드 에디션 중<br />
					나만을 위한 숫자를 선택해서<br />
					세상에 단 하나뿐인 캡을 소장할 수 있습니다.
				</p>
			</div>
		</div>
	</div>
	<!-- //sec5 -->


	<!-- sec6 -->
	<div class="section sec6">

		<div class="item1">
			<div class="img">
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec6_img1.jpg" alt="" />
			</div>
			<div class="tooltip">
				<div class="pulse">&nbsp;</div>
				<a class="point" href="#">button</a>
			</div>
			<div class="tooltip_v">
				<div class="txt">
					<div class="inner">
						<p>펠트지 소재의 쿠퍼스타운 양키스 원포인트 로고</p>
					</div>
				</div>
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_480.png" alt="" />
			</div>
		</div>

		<div class="item2">
			<div class="img">
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec6_img2.jpg" alt="" />
			</div>
			<div class="tooltip">
				<div class="pulse">&nbsp;</div>
				<a class="point" href="#">button</a>
			</div>
			<div class="tooltip_v">
				<div class="txt">
					<div class="inner">
						<p>헤리티지 감성을 살린 엔틱 골드 금속 디테일</p>
					</div>
				</div>
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_480.png" alt="" />
			</div>
		</div>

	</div>
	<!-- //sec6 -->


	<!-- sec7 -->
	<div class="section sec7">

		<div class="item1">
			<div class="img">
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec7_img1.jpg" alt="" />
			</div>

			<div class="tooltip tt1">
				<div class="pulse">&nbsp;</div>
				<a class="point" href="#">button</a>
			</div>
			<div class="tooltip_v tt1">
				<div class="txt">
					<div class="inner">
						<p>뉴욕양키스 팀의 유니폼에서 착안한 스트라이프 패턴</p>
					</div>
				</div>
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_510.png" alt="" />
			</div>

			<div class="tooltip tt2">
				<div class="pulse">&nbsp;</div>
				<a class="point" href="#">button</a>
			</div>
			<div class="tooltip_v tt2">
				<div class="txt">
					<div class="inner">
						<p>1800년대 선수들이 쓰던 야구모자의<br />헤리티지 감성을 그대로 재현한 형태</p>
					</div>
				</div>
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_440.png" alt="" />
			</div>

		</div>

		<div class="item2">
			<div class="img">
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec7_img2.jpg" alt="" />
			</div>
			<div class="img2">
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec7_img3.jpg" alt="" />
			</div>
			<div class="tooltip">
				<div class="pulse">&nbsp;</div>
				<a class="point" href="#">button</a>
			</div>
			<div class="tooltip_v">
				<div class="txt">
					<div class="inner">
						<p>모자 안쪽에 쿠퍼스타운 TAG과 라벨 구성</p>
					</div>
				</div>
				<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_440.png" alt="" />
			</div>
		</div>

	</div>
	<!-- //sec7 -->


	<!-- sec8 -->
	<div class="section sec8">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec8_tit.png" alt="SPECIAL PACKAGE" /></p>
			<div class="txt">
				<p>한정판을 위해 특별 제작한 콜렉션 박스& 패키지가 함께 제공됩니다.</p>
			</div>
		</div>
	</div>
	<!-- //sec8 -->


	<!-- sec9 -->
	<div class="section sec9">
		<div class="con">
			<p class="tit"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec9_tit.png" alt="161 HERITAGE CAP COLLECTION" /></p>



			<!-- 01.RED VER --------------------------------------------------------------------------------------->
			<div class="red">
				<div class="img"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec9_red.png" alt="" /></div>

				<!-- BUY NOW -->
				<a href="https://www.mlb-korea.com/goods/뉴욕-양키스/캡/32CPYZ911-50R" class="buy"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/btn_buy.png" alt="BUY NOW" /></a>
				<!-- //BUY NOW -->


				<!-- 툴팁 (SOLD OUT시 주석처리) -->
				<%-- <div class="tooltip">
					<div class="pulse">&nbsp;</div>
					<a class="point" href="#">button</a>
				</div>
				<div class="tooltip_v">
					<div class="txt">
						<strong>한정판의 특별함을 더하다!</strong>
						<p>1~161 숫자의 자수를 모자의 측면에 자수로 새겨<br />한정판 중 본인이 선택한 숫자는 오직 하나만 존재한다</p>
					</div>
					<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_red.png" alt="" />
				</div> --%>
				<!-- //툴팁 (SOLD OUT시 주석처리) -->


				<!-- SOLD OUT -->
				<div class="sold_out"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sold_out.png" alt="" /></div>
				<!-- //SOLD OUT -->

			</div>
			<!-- //01.RED VER --------------------------------------------------------------------------------------->



			<!-- 02.NAVY VER --------------------------------------------------------------------------------------->
			<div class="navy">
				<div class="img"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sec9_navy.png" alt="" /></div>

				<!-- 입고 알림 신청 -->
				<!-- <a href="javascript:alert('상품입고알림 신청 팝업')" class="noti"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/btn_noti.png" alt="입고 알림 신청" /></a>
				<div class="coming"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/coming.png" alt="" /></div> -->
				<!-- //입고 알림 신청 -->

				<!-- BUY NOW -->
				<a href="https://www.mlb-korea.com/goods/뉴욕-양키스/운동모/32CPYZ911-50N" class="buy"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/btn_buy.png" alt="BUY NOW" /></a>
				<!-- //BUY NOW -->

				<!-- 툴팁 (SOLD OUT시 주석처리) -->
				<%-- <div class="tooltip">
					<div class="pulse">&nbsp;</div>
					<a class="point" href="#">button</a>
				</div>
				<div class="tooltip_v">
					<div class="txt">
						<strong>한정판의 특별함을 더하다!</strong>
						<p>1~161 숫자의 자수를 모자의 측면에 자수로 새겨<br />한정판 중 본인이 선택한 숫자는 오직 하나만 존재한다</p>
					</div>
					<img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/tooltip_navy.png" alt="" />
				</div> --%>
				<!-- //툴팁 (SOLD OUT시 주석처리) -->

				<!-- SOLD OUT -->
				<div class="sold_out"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/sold_out.png" alt="" /></div>
				<!-- //SOLD OUT -->

			</div>
			<!-- //02.NAVY VER --------------------------------------------------------------------------------------->


			<div class="notice">
				<dl>
					<dt>NOTICE</dt>
					<dd>
						<ul>
							<li>- 온라인 단독 상품으로 오프라인 매장에서는 구매 불가합니다.</li>
							<li>- 무통장 입금으로 구매하실 경우 재고 확보가 어려워 결제시 품절이 될 수 있습니다.</li>
							<li>- 이벤트 상품으로 교환 및 반품 불가합니다.</li>
							<li>- id당 1개의 상품만 구매 가능하므로 장바구니 담기 구매는 불가합니다.</li>
						</ul>
					</dd>
				</dl>
			</div>


		</div>
	</div>
	<!-- //sec9 -->


	<!-- wheel -->
	<div class="wheel"><img src="http://static.mlb-korea.com/motioneye/2019/09_161_heritage_cap/images/wheel.png" alt="" /></div>
	<!-- //wheel -->

</div>
<!-------------------- //기획전 소스 END -------------------->
</div>
</section>
</main>
</div>
</div>

<article id="lypopGoodsInform" class="layer-popup lypopGoodsInform">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2>상품입고알림 신청</h2>
		<div class="layer-cont ly-box">
		
			<p>상품이 입고되면 입력하신 휴대전화번호로 알림을 보내 드립니다.</p>		
			
			<table class="board-write">
				<colgroup>
					<col style="width:125px">
					<col style="width:px">							
				</colgroup>
				<tbody>
					<tr>
						<th><label for="ifrName">이름</label></th>
						<td><input type="text" id="ifrName" class="input-style01 sm input_required textOnly" disabled="disabled" style="width:150px;" alt="이름" maxlength="100" value="${mbrNm}"></td>						
					</tr>	
					<tr>
						<th><label for="ifrMb01">입고알림 수신<br>휴대전화번호</label></th>
						<td>
							<input type="text" id="ifrMb01" class="input-style01 sm numberOnly" style="width:65px;" maxlength="3">
							<span class="hyphen">-</span>
							<input type="text" class="input-style01 sm numberOnly" id="ifrMb02" style="width:75px;" maxlength="4">
							<span class="hyphen">-</span>
							<input type="text" class="input-style01 sm numberOnly" id="ifrMb03" style="width:75px;" maxlength="4">
						</td>						
					</tr>											
																								
				</tbody>
			</table>	
			
			<p class="txtIcon02 txtGray">입고알림은 신청순으로 발송되며, 재입고 후에도 품절이 될 수 있습니다.</p>					
			
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:;" onclick="javascript:resetInformRequest();" class="btn w160">취소</a>
				<a href="javascript:;" onclick="javascript:stockInformRequest();" class="btn fill w160">입고알림 신청</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" onclick="javascript:resetInformRequest();" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<script type="text/javascript">
 
	var evtNo = "${event.eventExt.evtNo }";
	var mbrNm = "${mbrNm }";
	
	function ReqInformStock() {
		<sec:authorize access="hasAnyRole('ROLE_ANONYMOUS,ROLE_GUEST')">
		alert("로그인 후 이용 가능합니다.");
		openLayerPopupForLogin();
		</sec:authorize>
		<sec:authorize access="hasAnyRole('ROLE_USER')">
		layerPopup.popupOpenNow('#lypopGoodsInform'); 
		</sec:authorize>
	}
	
	
	//입고알림취소
	function resetInformRequest() {
		$("#ifrMb01").val("");
		$("#ifrMb02").val("");
		$("#ifrMb03").val("");
		
		$('[name=informSizeLi]').each(function(index){
			var itmNm = $(this).text();
			$("#informSize").text(itmNm);
			return false;
		});
		
		layerPopup.popupCloseNow('#lypopGoodsInform');
	}
	
	
	// 입고알림신청
	var clickFlag = false;
	function stockInformRequest() {
		
		var mobilAreaNo = $("#ifrMb01").val();
		var mobilTlofNo = $("#ifrMb02").val();
		var mobilTlofWthnNo = $("#ifrMb03").val();
		var num1 = /[0-9]{2,3}/;
		var num2 = /[0-9]{3,4}/;
		var num3 = /[0-9]{4}/;
		
		var phoneMobile = mobilAreaNo+mobilTlofNo+mobilTlofWthnNo;
		
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
			url : "/special/event/stockInformRequest.json", 		
			data : {'evtNo': evtNo, 'mbrNm': mbrNm, 'phoneMobile':phoneMobile },
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
				pointClickFlag = true;
			},
			success : function(data) {
				if(data.msg =='success'){
					alert("입고알림이 신청되었습니다.");
					$("#ifrMb01").val("");
					$("#ifrMb02").val("");
					$("#ifrMb03").val("");
					layerPopup.popupCloseNow('#lypopGoodsInform');
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
</script>
