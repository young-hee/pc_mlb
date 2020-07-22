

<script type="text/javascript" src="/static/js/modernizr.2.5.3.min.js"></script>

<section class="mds-section look">
	<div class="hdt"><span class="tit">LOOKBOOK</span></div>
	<div class="vs">
		

		<ul class="pg_num">
			<li><a href="javascript:;" class="lk">01</a></li>
			<li><a href="javascript:;" class="lk">02</a></li>
			<li><a href="javascript:;" class="lk">03</a></li>
			<li><a href="javascript:;" class="lk">04</a></li>
			<li><a href="javascript:;" class="lk">05</a></li>
			
			
		</ul>


		
		<div class="flipbook-viewport">
			<div class="flip-container">

				<div class="depth front"></div>
				<div class="flipbook">
					<!-- 이미지사이즈 1300 X 740 -->
					<div class="page" style="background-image:url(/static/images/_temp/pages/0.png)"></div>
					<div class="double" style="background-image:url(/static/images/_temp/pages/1.png)"></div>
					<div class="double" style="background-image:url(/static/images/_temp/pages/2.png)"></div>
					<div class="double" style="background-image:url(/static/images/_temp/pages/3.png)"></div>
					<div class="double" style="background-image:url(/static/images/_temp/pages/4.png)"></div>
					<div class="double" style="background-image:url(/static/images/_temp/pages/5.png)"></div>
				</div>
				<div class="depth back"></div>
				
				<!-- <div class="flip-control">
					<div id="prev"></div>
					<div id="next"></div>
				</div> -->

			</div>
			

			<!-- 2019.02.08 추가 -->
			<div class="look_get">
				<a href="javascript:;" class="btn_get">GET THIS LOOK</a>
			</div>

			<!-- <div class="look_download">
				<a href="javascript:;" class="btn_download"  onclick="download_fnc()">DOWNLOAD</a>
			</div> -->
			
		</div>

		<div class="look_more">
			<a href="/static/html/dp/look.jsp" class="btn_more">Read More</a>
		</div>



		<script type="text/javascript">

		download_fnc = function(){
			var down_pg = $(".mds-section.look .pg_num>li.active").index()+1;
			alert("다운 " + down_pg);
		}
		
		pageDepth = function(idx,totpag){
			var backCls = Math.abs( totpag - idx );
			var frontCls = Math.abs( totpag - backCls -1 );
			var $flip = $(".mds-section.look .flipbook-viewport");
			console.log("프론트"+frontCls , "백"+backCls )
			if (backCls <= 4) {
				$flip.find(".depth.back").attr("class","depth back s"+backCls);
			}
			if (frontCls <= 4 ) {
				$flip.find(".depth.front").attr("class","depth front s"+frontCls);
			}
		}
		pageDepthInit = function(){
			var pgt_len = $(".mds-section.look .pg_num>li").length - 1;
			var $flip = $(".mds-section.look .flipbook-viewport");
			$flip.find(".depth.front").addClass("s0");
			if ( $(".mds-section.look .pg_num>li").length  <= 4 ){
				$flip.find(".depth.back").addClass( "s"+ pgt_len );
			}else{
				$flip.find(".depth.back").addClass( "s4");
			}
		}

		function loadApp() {

			var flipbook = $('.flipbook');

		 	// Check if the CSS was already loaded
			if (flipbook.width()==0 || flipbook.height()==0) {
				setTimeout(loadApp, 10);
				return;
			}

			$('.flipbook .double').scissor();

			// Create the flipbook
			$('.flipbook').turn({
				elevation: 50,
				page:2,
				gradients: true
			});

			$(".flipbook").bind("turning", function(event, page, pageObject) {
				idx = parseInt(page * 0.5);
				console.log(  page, pageObject , idx );

				if(idx == 3){
					$(".mds-section.look .look_download").hide();
				}else{
					$(".mds-section.look .look_download").show();
				}

				$(".mds-section.look .pg_num>li:nth-child("+idx+")").addClass("active").siblings("li").removeClass("active");


				// 마지막 페이지일때 더보기 버튼 보이기
				var totpag = (  $(".flipbook").turn("pages") - 1 ) * 0.5;
				console.log(idx , totpag);
				if (idx >= totpag ) {
					$(".mds-section.look .look_more").show();
				}else{
					$(".mds-section.look .look_more").hide();
				}

				
				pageDepth(idx,totpag);

			});

			pageDepthInit();
			

			$(".mds-section.look .pg_num>li:nth-child(1)").addClass("active");
			// $(".flipbook").turn("page", 2);

			$(".mds-section.look .pg_num>li>a").on("click",function(){
				var idx = $(this).closest("li").index();
				// console.log(idx);
				idx = (idx)*2+1 ;
				$(".flipbook").turn("page", idx +1 );
			});
		}

		// Load the HTML4 version if there's not CSS transform
		yepnope({
			test : Modernizr.csstransforms,
			yep: ['/static/js/turn.js'],
			nope: ['/static/js/turn.html4.min.js'],
			// both: ['css/basic.css'],
			both: ['/static/js/scissor.min.js'],
			complete: loadApp
		});


		</script>

	</div>
</section>