<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup deliveryModi-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>배송지 관리</h2>
		<div class="layer-cont scroll">
			
			<div class="deliveryModi-popWrap">
				<p class="text-required"><span class="required">*</span> 필수입력 항목</p>
				<div class="board-write">
					<table summary="배송지 관리">
						<caption>배송지 관리</caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName">받는분</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" class="input-style02" style="width:145px;">
								<span class="error-msg" style="display:block;">“받는 분” 을 입력해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall">휴대전화</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall" class="input-style02" value="" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:70px;" />
								</div>
								<span class="error-msg" style="display:block;">휴대전화 번호를 확인해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall02">전화번호</label></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall02" class="input-style02" value="" style="width:64px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:70px;" />
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteName">배송지주소</label> <span class="required">*</span></th>
							<td>
								<div>
									<input type="text" id="boardWriteaddr" class="input-style02" style="width:calc(100% - 85px);">
									<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn-style04">주소찾기</a>
									<input type="text" class="input-style02 mtST10" style="width:100%;">
									<input type="text" class="input-style02 mtST10" style="width:100%;">
								</div>
								<div>
								<span class="check-skin">
									<input type="checkbox" id="chkReply" checked="checked">
									<span>기본배송지로 지정</span>
								</span>
								<label for="chkReply">기본배송지로 지정</label>
								</div>
								<span class="error-msg" style="display:block;">배송지주소를 확인해 주세요.</span>
							</td>
						</tr>
					</table>
				</div>
			</div>	
			
			<div class="btnWrapBox">			
				<a href="javascript:;" class="btn ">취소</a>
				<a href="javascript:;" class="btn fill">확인</a>
			</div>
		
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.deliveryModi-pop'); 
});
</script>
</body>
</html>