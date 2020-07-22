<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script id="noticePopupTemplate" type="text/x-jsrender">
    <article id="noticePOP-{{:notiSn}}" class="noticeView-pop"
    		style="top: {{:popupUpendLc||0}}px; left: {{:popupLeftLc||0}}px;">
    	<section class="layer-popupinner" tabindex="0">
    		<div class="layer-cont scroll">

    			<!-- 내용 -->
    			{{:pcNotiCont}}
    			<!-- //내용 -->

    		</div>
    		<div class="popWrapbottom">
    			<label class="check-skin">
    				<input type="checkbox" data-noti-sn="{{:notiSn}}" />
    				<span></span>
    				<em>오늘은 그만보기</em>
    			</label>
    		</div>
    		<div class="layer-popup-close">
    			<button type="button" class="d_layer_close" onclick="notiClo(this);">닫기</button>
    		</div>
    	</section>
    </article>
</script>

<script type="text/javascript">
$().ready(function() {
	//layerPopup.popupOpenNow('#noticepop');

	$.ajax('/helpdesk/notice/popupList.ajax', {
    		type: 'get',
    		data: {
    			searchExpsrScrinSectCd: '${param.searchExpsrScrinSectCd}' || 'MAIN'
    		},
    	})
    	.done(function(rt) {
    		$(rt.noticeList).each(function() {
    			if (getCookie('notice_popup_' + this.notiSn)) {
    				return;
    			}
    			if(this.pcNotiCont != null){
	    			$('.wrap').append($('#noticePopupTemplate').render(this));
	    			layerPopup.popupOpenNow('#noticePOP-' + this.notiSn);
	    			setTimeout($.proxy(function() {
	        			$('#noticePOP-' + this.notiSn + " .layer-popupinner")
	        				.width($('#noticePOP-' + this.notiSn + " .layer-cont img").width());
	    			}, this), 1000);
	    			
	    			if ($('.wrap').is(':contains(.noticeView-pop)')) {
	    				$('.contain').off('scroll touchmove mousewheel');
	    			}
    			}
    		});
    	})
    	.fail(function(e) {
    		window.console && console.log(e);
    	});

	$('body').on('change', '[data-noti-sn]', function() {
		if (this.checked) {
			setCookie('notice_popup_' + $(this).data('notiSn'), true, 1);
			$(this).closest('.noticeView-pop')
				.find('.d_layer_close').click();
		}
	});
});

function notiClo(it) {
	$(it).closest(".noticeView-pop").hide();
}
</script>
