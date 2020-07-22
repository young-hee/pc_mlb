<section class="mds-section video">
	<div class="hdt"><span class="tit">#MLB VIDEO</span></div>

	<div id="dpVideoSlide" class="slide">
		<div class="swiper-container">
			<ul class="swiper-wrapper">
				<li class="swiper-slide">
					<div class="vs"><iframe class="video_iframe" id="video_iframe_0" src="https://www.youtube.com/embed/peewlTu4FKA?rel=0&enablejsapi=1" frameborder="0" allowfullscreen=""></iframe></div>
				</li>
				<li class="swiper-slide">
					<div class="vs"><iframe class="video_iframe" id="video_iframe_1" src="https://www.youtube.com/embed/Nu8zkvBPdSM?rel=0&enablejsapi=1" frameborder="0" allowfullscreen=""></iframe></div>
				</li>
				<li class="swiper-slide">
					<div class="vs"><iframe class="video_iframe" id="video_iframe_2" src="https://www.youtube.com/embed/Ek2rUMTL-7k?rel=0&enablejsapi=1" frameborder="0" allowfullscreen=""></iframe></div>
				</li> 
			</ul>
		</div>
		<div class="navigation">
			<button type="button" class="btnNav prev">이전</button>
			<button type="button" class="btnNav next">다음</button>
		</div>
	</div>
	<script>
	$( document ).ready(function() {	

		if ( $('#dpVideoSlide .swiper-container li').length > 1 ) {
			dpVideoSlide = new Swiper('#dpVideoSlide .swiper-container', {
				slidesPerView: 'auto',
				initialSlide:1,
				centeredSlides:true,
				spaceBetween:20,
				observer: true,
				observeParents: true,
				watchOverflow:true,
				simulateTouch:false,
				navigation: {
					nextEl: '#dpVideoSlide .navigation .btnNav.next',
					prevEl: '#dpVideoSlide .navigation .btnNav.prev'
				},
				loop:true,
				autoplay:false
			});
		}

	});


	var tag = document.createElement('script');
	tag.src = "https://www.youtube.com/iframe_api";
	var firstScriptTag = document.getElementsByTagName('script')[0];
	firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);
	function onYouTubeIframeAPIReady(fileName) {
	    player0 = new YT.Player('video_iframe_0', {
	        //events: {
	      	//	'onReady': onPlayerReady
	        //}
	    });
	    player1 = new YT.Player('video_iframe_1', {
	        events: {
	      		'onReady': onPlayerReady
	        }
	    });
	    player2 = new YT.Player('video_iframe_2', {
	        // events: {
	      		// 'onReady': onPlayerReady
	        // }
	    });
	}
	video_stat = true;
	function onPlayerReady(event){
		od = 0
		$(function(){

			$("#wrap").on("load scroll", function(){
				videoStart = $('#video_iframe_1').offset().top - $(window).height() + $('#video_iframe_1').height() + $("#wrap").scrollTop();
				videoEnd = $('#video_iframe_1').offset().top + $("#wrap").scrollTop() ;
				var scrollTop = $("#wrap").scrollTop();
				//console.log(scrollTop , videoStart , videoEnd ,od ,video_stat)
				if(scrollTop > videoStart && scrollTop < videoEnd && od == 0 && video_stat == true){
					if ( video_stat == true ) {

						console.log("재생")
						od = 1;
						event.target.playVideo();
						event.target.mute();			
					}
				}else if((scrollTop < videoStart || scrollTop > videoEnd) && od == 1){
					console.log("정지")
					od = 0;
					event.target.pauseVideo();
					player0.stopVideo();
					player1.stopVideo();
					player2.stopVideo();
				}
			});

			$(document).on("click","#dpVideoSlide .navigation .btnNav",function(){
				event.target.pauseVideo();
				video_stat = false;
				player0.stopVideo();
				player1.stopVideo();
				player2.stopVideo();
			});
		})
	}

	
	</script>
</section>

