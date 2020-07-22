
var uiHtml = {
	init:function(){
		this.menuPan.init();

		$("body").append('<a class="btnLinkHtml" href="javascript:;">링크</a>');
		

		$(".mds-section.dp_list_sec .ui_dp_list .list>li .item .thumb a").each(function(index){
		});


		$(".mds-section.dp_list_sec .ui_dp_list .list>li .item .thumb a").attr("href","/static/html/pd/goods.jsp");


		$("html").attr("lang" , $urlParam.lang );

		$(document).on("click",".flexBanner .bts .btnClose",function(){
			setCookie("flexBanner"+window.location.port,"hide",24);
		});

		if( !getCookie("flexBanner"+window.location.port) && $("#flexBanner").length ) {
			$("#flexBanner").show();
			ui.ly.posit();
		}

	},
	menuPan:{
		init:function(){
			this.addEvent();
		},
		addEvent:function(){
			var keyM  =  this.togMenu;
			var keyF2 =  this.togUrl;
			var keyF7 =  this.togMobile;
			var keyF4 =  this.togDev;
			var keyBack =  this.keyBack;
			var fStat = true;
			$(document).on({
				focus: function(){  fStat = false; 
					// console.log("f");
				},
				blur:  function(){	fStat = true;  
					// console.log("t");
				}
			},"textarea , input");

			
			
			$(document).on("keydown",function(event){
				if( fStat === true ){
					if(event.keyCode == 77 ){  keyM();  } // M 키 이벤트
					if(event.keyCode == 113 ){ keyF2(); } //F2 키 이벤트
					if(event.keyCode == 118 ){ keyF7(); } //F7 키 이벤트
					if(event.keyCode == 115 ){ keyF4(); } //F4 키 이벤트
					if(event.keyCode == 8 ){ keyBack(); } //Back 키 이벤트
				}
			});

			$(document).on("click",".btnLinkHtml",this.togMenu);


			$(".tempLink>.pan , .btnLinkHtml").on("click",function(e){
				e.stopPropagation();
			});

		},
		keyBack:function(){
			console.log("뒤로");
			window.history.back();
		},
		togMenu:function(){
				if( $(".tempLink").length ){

					$(".tempLink>.pan").hide();
					$(".tempLink").hide();
					$(".tempLink>a.close").text("☞");
					$(".tempLink").remove();
					//ui.scrHold.using(false);

				}else{
					//ui.scrHold.using(true);
					var list =
					'<div class="tempLink">'+
						'<a class="close" href="javascript:;">☞</a>'+
						'<div class="pan"></div>'+
					'</div>';
					$("body").append(list);

					$(".tempLink>.pan").load("/static/html/cm/linkHtml.jsp .linkHtml", function(){
						uiHtml.menuPan.linkHtmlStat();
					});

					

			

					$(".tempLink>.pan").show();
					$(".tempLink").show();
					$(".tempLink>a.close").text("☜ 메뉴닫기");
					//$("html ,body").scrollTop(0);



				}

		},
		linkHtmlStat:function(){


			$(".linkHtml .stat").each(function(index, element) {
				$(this).wrapInner('<em class="ico"></em>');

				if( $(this).find("em").text() == "ing"){
					$(this).find("em").addClass("ing");
				}
				if( $(this).find("em").text() == "com"){
					$(this).find("em").addClass("com");
					// $(this).append(' <a class="size" href="javascript:;" target="_blank">size</a>');
				}
				if( $(this).find("em").text() == "mod"){
					$(this).find("em").addClass("mod");
				}
				if( $(this).find("em").text() == "검수"){
					$(this).find("em").addClass("chk");
				}
				if( $(this).find("em").text() == "대기"){
					$(this).find("em").addClass("stay");
				}



			});	

			$(".linkHtml td.url").each(function(){
				var link = $(this).text();
				if( link != ''){
					$(this).wrapInner('<a href="'+link+'"></a>' );
				}else{
					$(this).wrapInner('<a href="javascript:;"></a>' );
				}
				$(this).next("td.stat").wrapInner('<a href="'+link+'" target="_blank"></a>' );

				var link_size = link.replace(".jsp",".jsp.jpg").replace("html","images/_temp/size");
				//console.log( link_size  );
				$(this).next("td.stat").find("a.size").attr("href", link_size );
			});

		},
		togUrl:function () { // F2 키
			var tUrl = window.location.href;
			var tPort = window.location.port;
			//console.log(tPort, tUrl);
			if(tPort == "8081"){
				location.href = tUrl.replace(tPort,"8083");
			}
			if(tPort == "8082"){
				location.href = tUrl.replace(tPort,"8084");
			}
			if(tPort == "8083"){
				location.href = tUrl.replace(tPort,"8081");
			}
			if(tPort == "8084"){
				location.href = tUrl.replace(tPort,"8082");
			}
			
			var tHost = window.location.host;
			if(tHost == "dev-www.mlb-korea.com"){
				location.href = tUrl.replace(tHost,"dev-www.stretch-angels.com");
			}
			if(tHost == "dev-m.mlb-korea.com"){
				location.href = tUrl.replace(tHost,"dev-m.stretch-angels.com");
			}
			if(tHost == "dev-www.stretch-angels.com"){
				location.href = tUrl.replace(tHost,"dev-www.mlb-korea.com");
			}
			if(tHost == "dev-m.stretch-angels.com"){
				location.href = tUrl.replace(tHost,"dev-m.mlb-korea.com");
			}

		},
		togMobile:function () {  // F7 키
			var tUrl = window.location.href;
			var tPort = window.location.port;
			//console.log(tPort, tUrl);
			if(tPort == "8081"){
				location.href = tUrl.replace(tPort,"8082");
			}
			if(tPort == "8082"){
				location.href = tUrl.replace(tPort,"8081");
			}
			if(tPort == "8083"){
				location.href = tUrl.replace(tPort,"8084");
			}
			if(tPort == "8084"){
				location.href = tUrl.replace(tPort,"8083");
			}

			var tHost = window.location.host;
			if(tHost == "dev-www.mlb-korea.com"){
				location.href = tUrl.replace(tHost,"dev-m.mlb-korea.com");
			}
			if(tHost == "dev-m.mlb-korea.com"){
				location.href = tUrl.replace(tHost,"dev-www.mlb-korea.com");
			}
			if(tHost == "dev-www.stretch-angels.com"){
				location.href = tUrl.replace(tHost,"dev-m.stretch-angels.com");
			}
			if(tHost == "dev-m.stretch-angels.com"){
				location.href = tUrl.replace(tHost,"dev-www.stretch-angels.com");
			}

		},
		togDev:function () {  // F4 키
			var tUrl = window.location.href;
			var tPort = window.location.port;
			var tHost = window.location.host;
			var tOrg = window.location.origin;
			var tIp = window.location.hostname;
			//console.log(tPort, tUrl);
			if(tPort == "8081"){
				location.href = tUrl.replace(tHost,"dev-www.mlb-korea.com");
			}
			if(tPort == "8082"){
				location.href = tUrl.replace(tHost,"dev-m.mlb-korea.com");
			}
			if(tPort == "8083"){
				location.href = tUrl.replace(tHost,"dev-www.stretch-angels.com");
			}
			if(tPort == "8084"){
				location.href = tUrl.replace(tHost,"dev-m.stretch-angels.com");
			}

			
			if(tOrg == "https://dev-www.mlb-korea.com"){
				location.href = tUrl.replace(tOrg,"http://172.0.0.159:8081");
			}
			if(tOrg == "https://dev-m.mlb-korea.com"){
				location.href = tUrl.replace(tOrg,"http://172.0.0.159:8082");
			}
			if(tOrg == "https://dev-www.stretch-angels.com"){
				location.href = tUrl.replace(tOrg,"http://172.0.0.159:8083");
			}
			if(tOrg == "https://dev-m.stretch-angels.com"){
				location.href = tUrl.replace(tOrg,"http://172.0.0.159:8084");
			}

		}
	}
};

$(document).ready(function(){
	uiHtml.init();
});



//URL에서 파라미터 읽어오기
$urlParam = (function(a) {
	if (a == "") return {};
	var b = {};
	for (var i = 0; i < a.length; i++){
		var p=a[i].split('=');
		if (p.length != 2) continue;
		b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " ")); //공백으로 변환 후 디코딩
		//console.log(p[0]+" = "+b[p[0]])
	}
	return b;
})(window.location.search.substr(1).split('&')); //파라미터 정보만 분리


function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*60*60*1000));
  var expires = "expires="+ d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/static/html";
}
function getCookie(cname) {
  var name = cname + "=";
  var decodedCookie = decodeURIComponent(document.cookie);
  var ca = decodedCookie.split(';');
  for(var i = 0; i <ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}