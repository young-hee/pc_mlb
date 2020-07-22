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


<!-- 선판매 이벤트 : S -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,700,900" rel="stylesheet">
<style>
.event{
    position:relative;
    left:50%;
    transform:translate(-50%, 0);
    width:1920px;
    overflow:hidden;
}
.event *{
    font-family: 'Noto Sans KR', 'Noto Sans Korean', 'HelveticaNeue', 'AppleSDGothicNeo', sans-serif;
}
.event img{
    display:block;
}

.event [class*="pdt0"]{
    position:relative;
}
.event .pdt02{
	margin-top:246px;
}
.event .band{
    position:absolute;
    top:-225px;left:0;width:100%;height:31px;
    background-image:url(https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/txt_band.png);
    background-repeat:repeat-x;
    background-position:100% 0;
    -webkit-animation:marquee 10s infinite linear;
    -moz-animation:marquee 10s infinite linear;
    -o-animation:marquee 10s infinite linear;
    -ms-animation:marquee 10s infinite linear;
    animation:marquee 10s infinite linear;   
}
@-webkit-keyframes marquee{
    0%{background-position:100% 0;}
    100%{background-position:0 0}
}
@-moz-keyframes marquee {
    0%{background-position:100% 0;}
    100%{background-position:0 0}
}
@-ms-keyframes marquee {
    0%{background-position:100% 0;}
    100%{background-position:0 0}
}
@-o-keyframes marquee {
    0%{background-position:100% 0;}
    100%{background-position:0 0}
}
@keyframes marquee {
    0%{background-position:100% 0;}
    100%{background-position:0 0}
}
.event .link{
    position:absolute;
    /* left:50%;transform:translate(-50%, 0); */
    background:rgba(0, 0, 0, 0);
    display:block;
    overflow:hidden;
    text-indent:-9999px;
}
.event .link-img{
    width:912px;height:1026px;
    top:79px;left:736px;
}
.event .link-txt{
    width:656px;height:452px;
    top:303px;left:255px;
}
.event .pdt02 .link-img{
    width: 925px;
    height: 904px;
    top: 197px;
    left: 795px;
}
.event .pdt02 .link-txt{
    top: 316px;
    left: 254px;
}
.event .link-rds{
    top: 1310px;
    left: 200px;
    width: 96px;
    height: 96px;
    border-radius: 31px;
}
.event .pdt02 .link-rds{
    top: 1217px;
}
.tooltip{
    position: absolute;
    height: 28px;
    width: 28px;
    text-indent: -9999px;
    transform: translate(-50%, -50%);
}
.tooltip .point{
    position: absolute;
    top: 50%;
    left: 50%;
    width: 10px;
    height: 10px;
    background: #fff;
    border-radius: 100%;
    transform: translate(-50%, -50%);    
}
.tooltip .pulse{
    width:100%;height:100%;
    border-radius: 100%;
    background:#fff;
    -webkit-animation:scaleout 1.5s infinite ease-in-out;
    -moz-animation:scaleout 1.5s infinite ease-in-out;
    -o-animation:scaleout 1.5s infinite ease-in-out;
    -ms-animation:scaleout 1.5s infinite ease-in-out;
    animation:scaleout 1.5s infinite ease-in-out;
}
@-webkit-keyframes scaleout{
	0%{-webkit-transform:scale(0);-moz-transform:scale(0);-o-transform:scale(0);-ms-transform:scale(0);transform:scale(0)}
	100%{-webkit-transform:scale(1);-moz-transform:scale(1);-o-transform:scale(1);-ms-transform:scale(1);transform:scale(1);opacity:0;filter:alpha(opacity=0)}
}
@-moz-keyframes scaleout {
	0%{-webkit-transform:scale(0);-moz-transform:scale(0);-o-transform:scale(0);-ms-transform:scale(0);transform:scale(0)}
	100%{-webkit-transform:scale(1);-moz-transform:scale(1);-o-transform:scale(1);-ms-transform:scale(1);transform:scale(1);opacity:0;filter:alpha(opacity=0)}
}
@-ms-keyframes scaleout {
	0%{-webkit-transform:scale(0);-moz-transform:scale(0);-o-transform:scale(0);-ms-transform:scale(0);transform:scale(0)}
	100%{-webkit-transform:scale(1);-moz-transform:scale(1);-o-transform:scale(1);-ms-transform:scale(1);transform:scale(1);opacity:0;filter:alpha(opacity=0)}
}
@-o-keyframes scaleout {
	0%{-webkit-transform:scale(0);-moz-transform:scale(0);-o-transform:scale(0);-ms-transform:scale(0);transform:scale(0)}
	100%{-webkit-transform:scale(1);-moz-transform:scale(1);-o-transform:scale(1);-ms-transform:scale(1);transform:scale(1);opacity:0;filter:alpha(opacity=0)}
}
@keyframes scaleout{
	0%{-webkit-transform:scale(0);-moz-transform:scale(0);-o-transform:scale(0);-ms-transform:scale(0);transform:scale(0)}
	100%{-webkit-transform:scale(1);-moz-transform:scale(1);-o-transform:scale(1);-ms-transform:scale(1);transform:scale(1);opacity:0;filter:alpha(opacity=0)}
}
.tooltip img{
    visibility:hidden;
    opacity:0;
    transition:.2s;
    position:absolute;
    left: 100%;
    top: 50%;
    transform: translate(0, -50%);
}
.tooltip:hover img{
    visibility:visible;
    opacity:1;
}
.event .slide-box{
    display: flex;
    justify-content: center;
    position: absolute;
    bottom: 134px;
    left:0;
    right:0;
}
.event .slide-box .swiper-container{
    width:305px;
    margin:0 10px;
    padding-bottom:19px;
}
.event .slide-box .swiper-scrollbar{
    background:none;
    border:1px solid #000;
    border-radius:0;
    bottom: 0;
    left: 0;
    width: 100%;
}
.event .slide-box .swiper-scrollbar-drag{
    background:#000;
    border-radius:0;
}
.event .slide-box .swiper-button-prev{
    background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M0%2C22L22%2C0l2.1%2C2.1L4.2%2C22l19.9%2C19.9L22%2C44L0%2C22L0%2C22L0%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E");
}
.event .slide-box .swiper-button-next{
    background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg%20xmlns%3D'http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg'%20viewBox%3D'0%200%2027%2044'%3E%3Cpath%20d%3D'M27%2C22L27%2C22L5%2C44l-2.1-2.1L22.8%2C22L2.9%2C2.1L5%2C0L27%2C22L27%2C22z'%20fill%3D'%23ffffff'%2F%3E%3C%2Fsvg%3E");
}
.event .slide-box [class*="swiper-button"]{
    width: 15px;
    height: 30px;
    background-size: 100% auto;
    margin-top:0;
    transform:translate(0, -50%);
}
.event .txt-only{
    color:#777;
    font-size:9pt;
    position:absolute;
    top:11px;
    right:17px;
    z-index:2;
}
.event .sec-tit{
    font-size: 31.5pt;
    font-weight: 700;
    margin-bottom: 15px;
}
.event .sec-tit+p{
    font-size:21pt;
    font-weight:300;
}
.event .sec_share{
    border-top: 1px solid #e5e5e5;
    padding: 150px 0 115px;
    margin-top: 86px;
}
.event .sec_share .dsc{
    font-size:12pt;
    color:#999;
    line-height:1.75em;
}
.event .sec_share .icons{
    font-size:0;
    margin: 50px 0 20px;
}
.event .sec_share .icons:after{content:''; display:block; clear:both}
.event .sec_share .icons li{
    display:inline-block;
    margin-left:27px
}
.event .sec_share .icons li:first-child{margin-left:0}
.event .sec_share .icons img{
    width:83px;
    height:auto;
}
.event .sec_style .items{
    display: flex;
    justify-content: space-between;
    align-items: stretch;
    font-size:0;
    background:#000;
    margin-top: 53px;
}
.event .sec_style .group{
    display: inline-flex;
    flex-wrap: wrap;
    align-content: space-between;
    justify-content: space-between;
    font-size:0;
}
.event .sec_style .item{
    display:inline-block;
    position:relative;
}
.event .sec_style .item .cover{
    position:absolute;
    top:0;left:0;
    width:100%;height:100%;
    display:flex;
    flex-direction: column;
    align-items:center;
    justify-content:center;
    opacity:0;
    transition:.3s;
    background:rgba(0, 0, 0, .5);
    text-align:center;
}
.event .sec_style .item .cover:hover{
    opacity:1;
}
.event .sec_style .item .cover strong{
    color:#fff;
    font-size:15pt;
    line-height:1.3em;
    text-transform:uppercase;
}
.event .sec_style .item .cover a{
    display:block;
    width:120px;height:36px;
    line-height:36px;
    font-size:12pt;
    color:#000;
    background:#fff;
    border-radius:10px;
    margin-top:10px;
}
.event .sec_notice{
    text-align:left;
    padding:68px 0 107px;
}
.event .sec_notice>*{
    display:inline-block;
    vertical-align:top;
}
.event .sec_notice dt{
    font-size:15pt;
    font-weight:600;
    margin-left: 317px;
    margin-right: 28px;
}
.event .sec_notice dd{
    font-size:12pt;
    color:#999;
    font-weight:300;
    line-height: 1.625em;
}
.event-modal{
    position:fixed;
    top:0;left:0;
    width:100%;height:100%;
    background:rgba(0, 0, 0, .6);
    display:flex;
    align-items:center;
    justify-content:center;
}
.event-modal .inner{
    position:relative;
}
.event-modal .btn-close{
    position:absolute;
    right:0;top:0;
    width:132px;height:144px;
    background:rgba(0, 0, 0, 0);
}

</style>
<script type="text/javascript">
$(function(){
    var eventSwiper = new Swiper('.slide-box .swiper-container',{
        slidesPerView:1,
        observer: true,
        observeParents: true,
        scrollbar: {
            el: '.swiper-scrollbar',
            hide: false,
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        spaceBetween:0,
        watchOverflow:true,
        preloadImages: false,
    })
})
function openEModal(s){
    if(s == 'close'){
        $('.event-modal').fadeOut();
    }else{
        $('.event-modal').fadeIn();
    }
}
</script>
<div class="event">
    <div class="sec_top">
        <img class="bg" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/sec_top.jpg" alt="" />
        <div class="blind">
            <h2>19FW MLB 메가 다운 EARLY BIRD EVENT</h2>
            <ul>
                <li>20% 즉시할인 - 2019 메가 다운 구매시 상품쿠폰 사용 즉시 할인!</li>
                <li>10% 할인쿠폰 증정 - 공식몰 구매 후 구매확정 시 MLB 상품 10% 할인쿠폰 증정 (MLBKIDS/OULET 상품 제외)</li>
                <li>MLB 메가 멀티백 증정 - MLB 2019 다운 구매시 메가 사이즈 멀티백  증정 (2가지 로고 랜덤 증정)</li>
            </ul>
        </div>
    </div>
    <div class="sec_pdt">
        <div class="pdt01">
            <img class="bg" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/pdt01.jpg?v=2" alt="" />

            <div class="band">&nbsp;</div>

            <a class="link link-img" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50L">&nbsp;</a>
            <a class="link link-txt" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50L">2019 NY 메가 테잎 롱다운 369,000 -> 295,200원 SHOP NOW</a>
            <a class="link link-rds" href="javascript:openEModal()">RDS 오리 솜털 80, 깃털 20, 필파워 600</a>
            
            <div class="blind">
                <p>
                    간편한 스토퍼
                    스트링 고정이 가능한 스토퍼를  사용하여
                    간단히 후드 조절 가능
                </p>
                <p>
                    슬림한 소매핏+이너커프스
                    편안하지만 슬림해보이는 소매핏과 기모 원단의 
                    이너 커프스를 사용하여 보온성 UP!                       
                </p>
                <p>
                    사이드 트임
                    밑단 양옆 트임에 플라스틱 스냅을 사용하여 가볍고,
                    긴 기장에도 불편함 없이 착용 가능                        
                </p>
                <p>
                    Reflective 로고
                    MLB 시그니처 빅로고에 리플렉티브 전사를 사용하여 야간보행시 안전에 도움                        
                </p>
            </div>
            
            <div class="tooltip" style="top: 1625px;left: 1316px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>
            <div class="tooltip" style="top: 1748px;left: 1370px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>

            <div class="tooltip" style="top: 1813px;left: 643px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>
            <div class="tooltip" style="top: 2090px;left: 597px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
                <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/pop_logo_ny.jpg" alt="" />
            </div>

            <div class="tooltip" style="top: 2798px;left: 1439px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>
            
            <div class="tooltip" style="top: 3101px;left: 439px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>

            <h3 class="blind">Colors (전 컬러 남녀공용)</h3>
            <div class="slide-box">
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50L">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-1/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-1/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-1/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-1/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50K">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-2/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-2/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-2/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-2/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50I">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-3/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-3/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-3/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-3/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50R">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-4/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-4/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-4/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/1-4/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
        </div>
        <!-- pdt01 : E -->
        <div class="pdt02">
            <img class="bg" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/pdt02.jpg" alt="" />

            <a class="link link-img" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50L">&nbsp;</a>
            <a class="link link-txt" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50L">2019 NY 메가 테잎 롱다운 369,000 -> 295,200원 SHOP NOW</a>
            <a class="link link-rds" href="javascript:openEModal()">RDS 오리 솜털 80, 깃털 20, 필파워 600</a>
            
            <div class="blind">
                <p>
                    간편한 스토퍼
                    스트링 고정이 가능한 스토퍼를  사용하여
                    간단히 후드 조절 가능
                </p>
                <p>
                    슬림한 소매핏+이너커프스
                    편안하지만 슬림해보이는 소매핏과 기모 원단의 
                    이너 커프스를 사용하여 보온성 UP!                       
                </p>
                <p>
                    사이드 트임
                    밑단 양옆 트임에 플라스틱 스냅을 사용하여 가볍고,
                    긴 기장에도 불편함 없이 착용 가능                        
                </p>
                <p>
                    Reflective 로고
                    MLB 시그니처 빅로고에 리플렉티브 전사를 사용하여 야간보행시 안전에 도움                        
                </p>
            </div>
            
            <div class="tooltip" style="top: 1735px;left: 658px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>
            <div class="tooltip" style="top: 2036px;left: 504px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
                <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/pop_logo_la.jpg" alt="" />
            </div>

            <div class="tooltip" style="top: 1630px;left: 1490px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>

            <div class="tooltip" style="top: 2377px;left: 1081px;">
                <div class="pulse">&nbsp;</div><span class="point">&nbsp;</span>
            </div>

            <h3 class="blind">Colors (전 컬러 남녀공용)</h3>
            <div class="slide-box">
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50L">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-1/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-1/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-1/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-1/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50B">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-2/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-2/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-2/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-2/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-43R">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-3/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-3/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-3/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-3/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50P">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-4/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-4/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-4/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-4/4.jpg" alt="" />
                    </a>
                    <span class="txt-only">WOMEN ONLY</span>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
                <div class="swiper-container">
                    <a class="swiper-wrapper" href="https://www.mlb-korea.com/goods/-/-/31DJ02961-07U">
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-5/1.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-5/2.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-5/3.jpg" alt="" />
                        <img class="swiper-slide" src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/slide/2-5/4.jpg" alt="" />
                    </a>
                    <div class="swiper-scrollbar"></div>
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                </div>
            </div>
        </div>
        <!-- pdt02 : E -->
        <div class="sec_share">
            <h3 class="sec-tit">MLB 메가 다운 출시 SNS 공유하기 이벤트</h3>
            <p>이벤트 페이지를 SNS 공유만 해도 적립금 2,000원 증정!</p>
            <ul class="icons">
                <li><a href="javascript:insertSnsShare('facebook')"><img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_fb.png" alt="페이스북공유하기"></a></li>
                <li><a href="javascript:insertSnsShare('kakaostory')"><img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_kakao.png" alt="카카오스토리공유하기"></a></li>
                <li><a href="javascript:insertSnsShare('naver')"><img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_blog.png" alt="블로기공유하기"></a></li>
                <li><a href="javascript:insertSnsShare('url')"><img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/images/ico_url.png" alt="URL공유하기"></a></li>
            </ul>
            <p class="dsc">
                -회원 전용으로 공유횟수 상관없이 1ID당 1번 증정합니다.<br />
                -이벤트 참여 적립금은 9월 18일 일괄 지급 예정입니다.
            </p>
        </div>
    </div>
    <!-- share : E -->
    <div class="sec_style">
        <h3 class="sec-tit"># 2019 MLB 메가 다운 STYLE</h3>
        <div class="items">
            <div class="item">
                <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style1.jpg" alt="" />
                <div class="cover">
                    <strong>
                        2019<br />
                        ny 메가 테잎 롱다운
                    </strong>
                    <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50L">구매하기</a>
                </div>
            </div>
            <div class="group" style="width:396px">
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style2.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 로고 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJ02961-07U">구매하기</a>
                    </div>
                </div>    
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style3.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 로고 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJ02961-50P">구매하기</a>
                    </div>
                </div>                
            </div>
            <div class="group" style="width:928px">
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style4.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 테잎 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50I">구매하기</a>
                    </div>
                </div>
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style5.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 테잎 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50K">구매하기</a>
                    </div>
                </div>
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style6.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 테잎 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50I">구매하기</a>
                    </div>
                </div>
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style7.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 테잎 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50K">구매하기</a>
                    </div>
                </div>
                <div class="item">
                    <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/style8.jpg" alt="" />
                    <div class="cover">
                        <strong>
                            2019<br />
                            ny 메가 테잎 롱다운
                        </strong>
                        <a href="https://www.mlb-korea.com/goods/-/-/31DJS2961-50L">구매하기</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- style : E -->
    <dl class="sec_notice">
        <dt>NOTICE</dt>
        <dd>
            - 얼리버드 20% 할인쿠폰은 온라인회원 전용이며 구매 시 자동 적용됩니다.(다른 쿠폰과 중복 불가)<br />
            - 다운구매 10% 할인쿠폰은 공식몰  주문 후 구매확정시 9월 20일 일괄 증정해드립니다.<br />
            - 10% 할인쿠폰은 장바구니 쿠폰(중복할인 불가)으로 아울렛/키즈 상품은 할인에서 제외됩니다.
        </dd>
    </dl>
    <!-- notice : E -->
    <a href="https://www.mlb-korea.com/special/event/EV201908070000110/MLBKIDS_MEGA">
        <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/banner_kids.jpg" alt="" />
    </a>
</div>
<div class="event-modal" style="display:none;">
    <div class="inner">
        <img src="https://static.mlb-korea.com/motioneye/2019/20_earlybird/pc/pop_rds.jpg" alt="" />
        <a class="btn-close" href="javascript:openEModal('close')">&nbsp;</a>
    </div>
</div>
<!-- 선판매 이벤트 : E -->

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


		
