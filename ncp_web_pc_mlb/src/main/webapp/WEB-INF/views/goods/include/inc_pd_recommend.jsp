					
<section class="mds-section recom">
	<div class="hdt"><span class="tit"><spring:message code="goods.recommend.lbl.info"/></span></div>
	<script>
	$(document).ready(function() {
        var option = {godNo:_erpNo, channel : 'pdp'};
        $('#recomPdListBox').viewtogether(option);
    });
	</script>
	<div class="recomPdListBoxWrap">
  		<div id="recomPdListBox" class="recomPdListBox swiper-container">
  		</div>
  	</div>
</section>
