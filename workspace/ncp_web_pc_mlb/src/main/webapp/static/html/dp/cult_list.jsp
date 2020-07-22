<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain dp cult list" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				
				<div class="cult_top">
					<span class="tit">CULTURE</span>
				</div>

			
				<div class="dp_cult_menu">
					<ul class="menu">
						<li class="on"><a href="javascript:;">ALL</a></li>
						<li><a href="/">MAGAZINE</a></li>
						<li><a href="javascript:;">INTERVIEW</a></li>
						<li><a href="javascript:;">CELEB</a></li>
						<li><a href="javascript:;">FESTIVAL&PARTY</a></li>
					</ul>
				</div>

				<!-- Visual  -->
				<%@ include file="../dp/inc_dp_visual_cult.jsp" %>

				<div class="dp_cult_list">


					<div class="cult_list" id="cult_list">
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_1.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_2.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img mov"><img src="/static/images/_temp/cult_thumb_3.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_4.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_5.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_6.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_7.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_8.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION 엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION </div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_9.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img mov"><img src="/static/images/_temp/cult_thumb_10.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_11.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
						<div class="box">
							<div class="item">
								<div class="thumb"><a href="/static/html/dp/cult_view.jsp"><span class="img"><img src="/static/images/_temp/cult_thumb_12.png" alt=""></span></a></div>
								<div class="info"><div class="name">엑소의 리얼스타일로 만나 보는 18' WINTER DOWN COLLECTION</div></div>
							</div>
						</div>
					</div>


				</div>

				<!-- <button type="button" class="append-button">더보기</button> -->

			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>

</div>
<%@ include file="../_inc/bottom.jsp" %>
<script src="/static/js/masonry.pkgd.min.js"></script>
<script src="/static/js/imagesloaded.pkgd.min.js"></script>
<script>
$(document).ready(function(){

	$cult_grid = $('#cult_list').masonry({
		itemSelector: '#cult_list .box',
		percentPosition: true,
		gutter: 30,
		transitionDuration: 0
	});
	$cult_grid.imagesLoaded().progress( function() {
	    $cult_grid.masonry('layout');
	}); 
	$(window).on("load resize",function() {
		$cult_grid.masonry('layout');
	});
	 
	appendStat = true ;

	addItemFnc = function(){
		console.log("addItemFnc");	
		$.ajax({
			type: "post",
			url: "/static/html/dp/cult_list_more.jsp",
			dataType: "html",
			success: function(html) {
				window.setTimeout(function(){
					$items = $(html)
					$cult_grid.append( $items ).masonry( 'appended', $items );
					window.setTimeout(function(){
						$cult_grid.masonry('layout');
					},10);
					appendStat = true;
				},500);
			}
		});	
	};

	$("#wrap").on("scroll load", function() {
		var sct = $("#wrap").scrollTop() + $("#wrap").height() - $(".foot").height();
		var cnt = $(".contain").outerHeight();
		// console.log(cnt,sct);
		if (cnt <= sct && appendStat == true) {
			console.log("바닥");
			appendStat = false;
			addItemFnc();			
		}
	});

});
</script>
</body>
</html>