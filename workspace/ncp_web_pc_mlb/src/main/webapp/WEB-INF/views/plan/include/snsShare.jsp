<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<span class="share ui_share_view" id="btn_promotion_share">
	<ul class="list">
		<li class="weibo"><a href="javascript:;">웨이보</a></li>
		<li class="qq"><a href="javascript:;">QQ</a></li>
		<li class="facebook"><a href="javascript:;">페이스북</a></li>
		<li class="twitter"><a href="javascript:;">트위터</a></li>
		<li class="line"><a href="javascript:;">네이버라인</a></li>
		<li class="url"><a href="javascript:;">URL복사</a></li>
	</ul>
	<a class="bt" href="javascript:;">공유</a>
</span>	

<script>
	$(document).ready(function(){
		// 공유하기 버튼 이벤트
		$(".share.ui_share_view").on("click", "ul.list > li", function() {
			// 페이스북
			if($(this).attr('class').indexOf("facebook") !=-1) {
				jsShareSns('facebook');
			}
			// 트위터
			else if($(this).attr('class').indexOf("twitter") !=-1) {
				jsShareSns('twitter', $("meta[name='og_title']").attr("content"));
			}
			// url 복사
			else if($(this).attr('class').indexOf("url") !=-1){
				var trb= location.href;
				if(trb.indexOf("language=") === -1) {
					trb = trb.indexOf("?") > 0 ? trb + "&language=" + BASE.locale : trb + "?language=" + BASE.locale;
				}
		    	var IE=(document.all)?true:false;
		    	if (IE) {
	            	if(confirm(MESSAGES['common.js.track.txt1']))
	            	window.clipboardData.setData("Text", trb);
	            } else {
	            	temp = prompt(MESSAGES['common.js.track.txt2'], trb);
	            }
			}	
			//  네이버 라인
			else if($(this).attr('class').indexOf("line") !=-1) {
				jsShareSns('naverline');
			}
			else if($(this).attr('class').indexOf("weibo") !=-1) {
				jsShareSns('weibo');
			}
			else if($(this).attr('class').indexOf("qq") !=-1) {
				jsShareSns('qq');
			}
		});
	});
</script>