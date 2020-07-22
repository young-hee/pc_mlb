<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/common_${pageContext.response.locale.language}.js?v=${_version}"></script>
<script type="text/javascript" src="/javascript/message/member_${pageContext.response.locale.language}.js?v=${_version}"></script>
		<!-- 
		<article id="commonLayerPopup" class="layer-popup layer-type02">
			<section class="layer-popup-cont" tabindex="0">
				<h2></h2>
				<div class="layer-popup-wrap02">
					<p class="layer-txt"></p>
				</div>
				<div class="btns"><a href="javascript:closeCommonLayerPopup('commonLayerPopup');" class="btn lg fill btn-confirm"></a></div>				
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>
		-->
		
		<article id="commonLayerPopup" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2></h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt layer-txt"></p>					
					<div class="btns"><a href="javascript:;" class="btn lg fill btn-confirm d_layer_close">확인</a></div>					
				</div>				
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close">닫기</button>
				</div>
			</section>
		</article>
		
		<article id="commonLayerPopupForConfirm" class="layer-popup popCertifi">
			<section class="layer-popup-cont" tabindex="0">
				<h2></h2>
				<div class="layer-popup-wrap">
					<p class="msg-txt layer-txt"></p>
				</div>
				<div class="btn-wrap">
					<a href="javascript:callbackConfirmLayer(false);" class="btn-style03"></a>
					<a href="javascript:callbackConfirmLayer(true);" class="btn-style02"></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"></button>
				</div>
			</section>
		</article>
		
<script type="text/javascript">

/**
 * 공통 레이어팝업 열기
 *
 * title : 상단에 들어갈 제목
 * msg : 중단에 들어갈 문구
 * btnNm : 하단의 버튼에 들어갈 버튼명
 * layerId : popup할 레이어ID
 */
function openCommonLayerPopup(title, msg, btnNm, layerId) {
	$("article#" + layerId).find("h2").html(title);
	$("article#" + layerId).find(".layer-txt").html(msg);
	$("article#" + layerId).find(".btn-style02").html(btnNm);
	
	layerPopup.popupOpenNow("#" + layerId);
}

function closeCommonLayerPopup(layerId) {
	$("#" + layerId).find("button.d_layer_close").trigger("click");
}

/**
 * 공통 confirm 레이어팝업 열기
 *
 * title : 상단에 들어갈 제목
 * msg : 중단에 들어갈 문구
 * cancelBtnNm : 하단의 버튼에 들어갈 취소 버튼명(클릭시 javascript:callbackConfirmLayer(false); 함수 실행)
 * confirmBtnNm : 하단의 버튼에 들어갈 확인 버튼명(클릭시 javascript:callbackConfirmLayer(true); 함수 실행)
 * layerId : popup할 레이어ID
 */
function openCommonLayerPopupForConfirm(title, msg, cancelBtnNm, confirmBtnNm, layerId) {
	$("article#" + layerId).find("h2").html(title);
	$("article#" + layerId).find(".layer-txt").html(msg);
	$("article#" + layerId).find(".btn-style03").html(cancelBtnNm);
	$("article#" + layerId).find(".btn-style02").html(confirmBtnNm);
	
	layerPopup.popupOpenNow("#" + layerId);
}

</script>