﻿<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="popTermsEt_2" class="layer-popup popTermsEt E2">
	<section class="layer-popup-cont" tabindex="0">
		<h2>고유식별정보 처리 동의</h2>
		<div class="layer-cont">
			
			<div class="d_tab02 tab_box">
				<ul class="tab-type01">
					<li class="d_tab02_select on"><a href="javascript:;">SKT</a></li>
					<li class="d_tab02_select"><a href="javascript:;">KT</a></li>
					<li class="d_tab02_select"><a href="javascript:;">LG U+</a></li>
				</ul>
				<div class="d_tab02_cont" style="display: block;">
					<div class="termsBox">
						<div class="ctn">
							<div class="agree-section-content">
								<%@ include file="../et/inc_tel_2_sk.jsp" %>
							</div>
						</div>
					</div>
				</div>
				<div class="d_tab02_cont" style="display: none;">
					<div class="termsBox">
						<div class="ctn">
							<div class="agree-section-content">
								<%@ include file="../et/inc_tel_2_kt.jsp" %>
							</div>
						</div>
					</div>
				</div>
				<div class="d_tab02_cont on" style="display: none;">
					<div class="termsBox">
						<div class="ctn">
							<div class="agree-section-content">
								<%@ include file="../et/inc_tel_2_lg.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#popTermsEt_2');
});
</script>
</body>
</html>