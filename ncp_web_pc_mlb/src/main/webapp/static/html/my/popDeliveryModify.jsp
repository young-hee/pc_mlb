<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopDeliveryMd" class="layer-popup deliveryModi-pop lypopDeliveryMd">
	<section class="layer-popup-cont" tabindex="0">
		<h2>배송지 변경</h2>
		<div class="layer-cont ly-box">
			
			<div class="deliveryModi-popWrap">
				<p class="text-required"><span class="required">*</span> 필수입력 항목</p>
				<div class="board-write">
					<table summary="배송지 변경">
						<caption>배송지 변경</caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName">받는분</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" class="input-style02" style="width:145px;">
								<a href="../od/popaddrSelect.jsp" class="btn-style04">배송지선택</a>
								<!-- <span class="error-msg" style="display:block;">“받는 분” 을 입력해 주세요.</span> -->
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall">휴대전화</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall" class="input-style02" value="" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:72px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:72px;" />
								</div>
								<!-- <span class="error-msg" style="display:block;">휴대전화 번호를 확인해 주세요.</span>-->
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall02">전화번호</label></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall02" class="input-style02" value="" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:72px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style02" value="" style="width:72px;" />
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteaddr">배송지주소</label> <span class="required">*</span></th>
							<td>
								<div>
									<input type="text" id="boardWriteaddr" class="input-style02" style="width:calc(100% - 85px);">
									<a href="/static/html/my/jaddressFindnon_pop.jsp" class="btn-style04">우편번호</a>
									<input type="text" class="input-style02 mtST10" style="width:100%;">
									<input type="text" class="input-style02 mtST10" style="width:100%;">
								</div>
								<div>
									<span class="check-skin">
										<input type="checkbox" id="chkReply" checked="checked">
										<span>기본배송지로 지정</span>
									</span>
									<label for="chkReply">기본배송지로 지정</label>
									<span class="check-skin">
										<input type="checkbox" id="chkaddList" checked="checked">
										<span>배송지관리 목록에 추가</span>
									</span>
									<label for="chkaddList">배송지관리 목록에 추가</label>									
								</div>
								<!-- <span class="error-msg" style="display:block;">배송지주소를 확인해 주세요.</span>-->
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="deliveryMsg01">배송요청사항</label></th>
							<td>								
								<!-- select -->
								<div class="select-style01 d_select" style="width:100%;">
									<button type="button" class="d_select_sel" id="deliveryMsg01" style="width:100%;"><span>직접입력</span></button>
									<ul>
										<li><a href="javascript:;">직접입력</a></li>
										<li><a href="javascript:;">부재 시 경비실에 맡겨주세요.</a></li>
										<li><a href="javascript:;">부재 시 문 앞에 놓아주세요.</a></li>
										<li><a href="javascript:;">배송 전에 연락주세요.</a></li>
										<!-- <li><a href="javascript:;">빠른 배송 부탁드려요.</a></li>
										<li><a href="javascript:;">배관함에 넣어주세요.</a></li> 2018.12.06 삭제-->
										<li><a href="javascript:;">무인 택배함에 보관해주세요.</a></li>
									</ul>
								</div>
								<!-- //select -->	
								<input type="text" id="deliveryMsg02" class="input-style02 mtST10" style="width:100%;">							
							</td>
						</tr>						
					</table>
				</div>
			</div>	
			
			<div class="btnWrapBox">			
				<a href="javascript:;" class="btn ">취소</a>
				<a href="javascript:;" class="btn fill">배송지 변경</a>
			</div>
		
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.lypopDeliveryMd'); 
});
</script>
</body>
</html>