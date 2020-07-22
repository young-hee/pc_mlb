<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<section class="layer-popup-cont" tabindex="0">
	<h2>
		<c:choose>
			<c:when test="${result.photoYn eq 'Y'}">
				포토 리뷰 수정
			</c:when>
			<c:otherwise>
				텍스트 리뷰 수정
			</c:otherwise>
		</c:choose>
	</h2>
	<div class="layer-cont scroll">

		<div class="Review-popWrap">
			<div class="board-write">
				<form id="editReviewPopupForm" name="editReviewPopupForm" action="/mypage/goods/editReviewAction" method="post" enctype="multipart/form-data">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="hidden" id="godNo" name="godGodEvl.godNo" value="${result.ordGod.godNo }" />
					<input type="hidden" id="skuNo" name="godGodEvl.skuNo" value="${result.ordGod.skuNo }" />
					<input type="hidden" id="ordNo" name="godGodEvl.ordNo" value="${result.ordGod.ordNo }" />
					<input type="hidden" id="godEvlTurn" name="godGodEvl.godEvlTurn" value="${result.godGodEvl.godEvlTurn }" />
					<input type="hidden" id="ordGodTurn" name="godGodEvl.ordGodTurn" value="${result.ordGod.ordGodTurn }" />
					<input type="hidden" id="photoYn" name="photoYn" value="${result.photoYn}" />
					<input type="hidden" id="starScore" name="godGodEvl.qltyEvlScore" value="${result.godGodEvl.qltyEvlScore}" />
					<input type="hidden" id="bstGodEvlYn" name="godGodEvl.bstGodEvlYn" value="N" />
					<input type="hidden" id="ntceYn" name="godGodEvl.ntceYn" value="N" />

					<table summary="텍스트 리뷰 작성">
						<caption>
							<c:choose>
								<c:when test="${result.photoYn eq 'Y'}">
									포토 리뷰 수정
								</c:when>
								<c:otherwise>
									텍스트 리뷰 수정
								</c:otherwise>
							</c:choose>
						</caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<td colspan="2">
								<div class="goodsreviewBox">
									<p class="txt13-666">리뷰 작성 시, 온라인몰에서 사용 가능한 포인트를 적립해 드립니다. (텍스트리뷰 300포인트, 포토리뷰 1,000포인트)</p>
									<div class="goods-R-ImgBox">
										<a href="javascript:;"><img src="${imageURL}${result.ordGod.lstImgUrl}/dims/resize/100x100" alt="${result.ordGod.godNm}" onerror="errorImgShow(this,'100');"></a>
									</div>
									<div class="goods-R-InfoBox">
										<div class="gd-Tpos">
											<strong><%-- [<ncp:code code="${result.recomendSexNm}"/>]  --%><c:out value="${result.ordGod.godNm}"/></strong>
										</div>
										<ul>
											<li><span><c:out value="${result.ordGod.colorCd}"/></span><span><c:out value="${result.ordGod.itmNm}"/></span></li>
											<li>주문일시 : <span><fmt:formatDate value="${result.ord.ordDt}" pattern="yyyy-MM-dd" /></span></li>
										</ul>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">별점</label></th>
							<td>
								<div class="starRev">
								  <a href="javascript:;" id="d_score_sel1" onclick="javascript:setStarScore(1);">별1</a>
								  <a href="javascript:;" id="d_score_sel2" onclick="javascript:setStarScore(2);">별2</a>
								  <a href="javascript:;" id="d_score_sel3" onclick="javascript:setStarScore(3);">별3</a>
								  <a href="javascript:;" id="d_score_sel4" onclick="javascript:setStarScore(4);">별4</a>
								  <a href="javascript:;" id="d_score_sel5" onclick="javascript:setStarScore(5);">별5</a>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">사이즈</label></th>
							<td>
								<div>
									<span class="rdo-skin">
										<input type="radio" id="rdoType01" name="godGodEvl.sizeEvlCd" value="SIZE_EVL_MID" <c:if test="${result.godGodEvl.sizeEvlCd eq 'SIZE_EVL_MID'}">checked="checked"</c:if>>
										<span>딱 맞아요</span>
									</span><label for="rdoType01">딱 맞아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType02" name="godGodEvl.sizeEvlCd" value="SIZE_EVL_SML" <c:if test="${result.godGodEvl.sizeEvlCd eq 'SIZE_EVL_SML'}">checked="checked"</c:if>>
										<span>작아요</span>
									</span><label for="rdoType02">작아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType03" name="godGodEvl.sizeEvlCd" value="SIZE_EVL_LAG" <c:if test="${result.godGodEvl.sizeEvlCd eq 'SIZE_EVL_LAG'}">checked="checked"</c:if>>
										<span>커요</span>
									</span><label for="rdoType03">커요</label>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">컬러</label></th>
							<td>
								<div>
									<span class="rdo-skin">
										<input type="radio" id="rdoType04" name="godGodEvl.colorEvlCd" value="COLOR_EVL_SAMENSS" <c:if test="${result.godGodEvl.colorEvlCd eq 'COLOR_EVL_SAMENSS'}">checked="checked"</c:if>>
										<span>화면과 같아요</span>
									</span><label for="rdoType04">화면과 같아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType05" name="godGodEvl.colorEvlCd" value="COLOR_EVL_BRGT" <c:if test="${result.godGodEvl.colorEvlCd eq 'COLOR_EVL_BRGT'}">checked="checked"</c:if>>
										<span>밝아요</span>
									</span><label for="rdoType05">밝아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType06" name="godGodEvl.colorEvlCd" value="COLOR_EVL_DARK" <c:if test="${result.godGodEvl.colorEvlCd eq 'COLOR_EVL_DARK'}">checked="checked"</c:if>>
										<span>어두워요</span>
									</span><label for="rdoType06">어두워요</label>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">상품리뷰</label></th>
							<td>
								<textarea cols="30" rows="10" name="godGodEvl.godEvlCont" id="godEvlCont" placeholder="특수문자(\ / : < > & 등) 는 사용할 수 없으며,한글 기준 최대 1,000자까지 작성 가능합니다."><c:out value="${result.godGodEvl.godEvlCont}"/></textarea>
								<%-- 특수문자(\ / : < > & 등) 는 사용할 수 없으며,한글 기준 최대 1,000자까지 작성 가능합니다. --%>
								<div class="clearfix">
									<div class="fl">
										<span class="error-msg" id="error_msg_cnt_overflow" style="display:none;">더 이상 입력하실 수 없습니다.</span>
										<span class="error-msg" id="error_msg_special_text" style="display:none;">특수문자는 입력하실 수 없습니다.</span>
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000" id="currentLetterCnt">0</em>자/1,000자</span>
									</div>
								</div>
							</td>
						</tr>
						<c:if test="${result.photoYn eq 'Y'}">
							<tr>
								<th scope="row"><label for="boardWriteContact">사진등록</label></th>
								<td>
									<span class="file-search02 imgFileSrch">
										<span>
											<input type="hidden" id="deletefile1" name="atchFileTurn" value="" />
											<input type="hidden" name="fileNm" id="nmfile1" value="${fn:length(result.godGodEvlAtchFiles) > 0 ? result.godGodEvlAtchFiles[0].atchFileNm : '' }" />
    											<input type="file" name="files" onchange="javascript:checkFileSize(this,1);" id ="file1" accept="image/x-png, image/gif, image/jpeg"/>
    											<label for="file1" id="lbl_file1" style="display: ${not empty result.godGodEvlAtchFiles[0] ? 'none' : 'block'};">파일찾기</label>
											<c:if test="${fn:length(result.godGodEvlAtchFiles) > 0}" >
												<img src="${imageURL}${result.godGodEvlAtchFiles[0].atchFileUrl}" alt="" id="imgfile1" turn ="${result.godGodEvlAtchFiles[0].atchFileTurn}">
												<a href="javascript:;" class="fileDel-Btn" onclick="javascript:deleteImage('file1');" id="btnImgDeletefile1">파일삭제</a>
											</c:if>
										</span>
										<span>
											<input type="hidden" id="deletefile2" name="atchFileTurn" value="" />
											<input type="hidden" name="fileNm" id="nmfile2" value="${fn:length(result.godGodEvlAtchFiles) > 1 ? result.godGodEvlAtchFiles[1].atchFileNm : '' }" />
    											<input type="file" name="files" onchange="javascript:checkFileSize(this,2);" id ="file2" accept="image/x-png, image/gif, image/jpeg"/>
    											<label for="file2" id="lbl_file2" style="display: ${not empty result.godGodEvlAtchFiles[1] ? 'none' : 'block'};">파일찾기</label>
											<c:if test="${fn:length(result.godGodEvlAtchFiles) > 1}" >
												<img src="${imageURL}${result.godGodEvlAtchFiles[1].atchFileUrl}" alt="" id="imgfile2" turn="${result.godGodEvlAtchFiles[1].atchFileTurn}" />
												<a href="javascript:;" class="fileDel-Btn" onclick="javascript:deleteImage('file2');" id="btnImgDeletefile2">파일삭제</a>
											</c:if>
										</span>
										<span>
											<input type="hidden" id="deletefile3" name="atchFileTurn" value="" />
											<input type="hidden" name="fileNm" id="nmfile3" value="${fn:length(result.godGodEvlAtchFiles) > 2 ? result.godGodEvlAtchFiles[2].atchFileNm : '' }" />
    											<input type="file"  name="files" onchange="javascript:checkFileSize(this,3);" id ="file3" accept="image/x-png, image/gif, image/jpeg"/>
    											<label for="file3" id="lbl_file3" style="display: ${not empty result.godGodEvlAtchFiles[2] ? 'none' : 'block'};">파일찾기</label>
											<c:if test="${fn:length(result.godGodEvlAtchFiles) > 2}" >
												<img src="${imageURL}${result.godGodEvlAtchFiles[2].atchFileUrl}" alt="" id="imgfile3" turn="${result.godGodEvlAtchFiles[2].atchFileTurn}" />
												<a href="javascript:;" class="fileDel-Btn" onclick="javascript:deleteImage('file3');" id="btnImgDeletefile3">파일삭제</a>
											</c:if>
										</span>
										<span class="error-msg" style="display:none;">사진을 하나 이상 등록하셔야 합니다.</span>
									</span>
									<span class="txt13-999 pdt05 pdb05">10MB 이하, JPG,PNG,GIF</span>
								</td>
							</tr>
						</c:if>
					</table>
				</form>
			</div>
		</div>

		<div class="btnWrapBox">
			<a href="javascript:;" class="btn fill" onclick="editReviewPopupFormSubmit(); return false;">수정</a>
		</div>

	</div>
	<div class="layer-popup-close">
		<button type="button" class="d_layer_close">닫기</button>
	</div>
</section>

<script>

$(document).ready(function(){
	for(var i=1; i<=3; i++){
		if($("#nmfile"+i).val() == ""){
			$("#lbl_file"+i).show();
		}
	}
	var len = '${result.godGodEvl.qltyEvlScore}';
	for(var i=1; i<=len; i++){
		$("#d_score_sel"+i).addClass("on");
	}
	/* if(agent.indexOf("msie") > -1 ){    //IE version
		$("#godEvlCont").text($("#godEvlCont").text().trim());
	}else{    //other browser
		$("#godEvlCont").val($("#godEvlCont").val().trim());
	} */

	var s = document.getElementById("godEvlCont").value;
	s = s.replace(/(^\s*)|(\s*$)/gi,"");
	s = s.replace(/[ ]{2,}/gi," ");
	s = s.replace(/\n /,"\n");
	document.getElementById("godEvlCont").value = s;

});

function setStarScore(score){
	$("#starScore").val(score);
}

var godEvlCont;
$("#godEvlCont").on('keyup', function(){
	if(agent.indexOf("msie") > -1 ){    //IE version
		godEvlCont = $("#godEvlCont").text();
	}else{    //other browser
		godEvlCont = $("#godEvlCont").val();
	}
	if(godEvlCont.length > 1000){
		if(agent.indexOf("msie") > -1 ){    //IE version
			$("#godEvlCont").text(godEvlCont.substring(0,1000));
			godEvlCont = $("#godEvlCont").text();
		}else{    //other browser
			$("#godEvlCont").val(godEvlCont.substring(0,1000));
			godEvlCont = $("#godEvlCont").val();
		}
		$("#error_msg_cnt_overflow").show();
	}else{
		$("#error_msg_cnt_overflow").hide();
	}
	if(specialTextCheck("godEvlCont") == false){
		//특수문자 ￦/ : < > 는 사용할 수 없습니다.
		$("#error_msg_special_text").show();
	}else{
		$("#error_msg_special_text").hide();
	}
	$("#currentLetterCnt").text($.trim(godEvlCont).length);
}).trigger('keyup');

function checkFileSize(f, i){
	if( $("#file"+i).val() != "" ){
		//파일 사이즈
		var fileSize = document.getElementById("file"+i).files[0].size;
		var maxSize = 10 * 1024 * 1024;
		//파일 확장자
		var ext = $("#file"+i).val().split('.').pop().toLowerCase();
	      if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1 || fileSize > maxSize) {
			 alert("사진 용량 및 형식에 맞지 않습니다.");
			 fileReset(f);
	      }else{
	    	  selectImage(f);
	      }
	}
}

function deleteImage(id){
	var fullFilePath = $("#img"+id).attr("turn");
	$("#delete"+id).val(fullFilePath);
	$("#nm"+id).val("");
	$("#"+id).val("");
	$("#lbl_"+id).show();
	$("#btnImgDelete"+id).remove();
	$("#img"+id).remove();
}
function selectImage(obj){
	var html = "";
	html += '<img src="" alt="" id="img'+obj.id+'" />';
	//html += '<button type="button" class="btn-photo-del" onclick="javascript:deleteImage(\''+obj.id+'\');" id="btnImgDelete'+obj.id+'">삭제</button>';
	html += '<a href="javascript:deleteImage(\''+obj.id+'\');" class="fileDel-Btn" id="btnImgDelete'+obj.id+'">파일삭제</a>'
	$(obj).after(html);
	var opt = {img: $("#img"+obj.id),w: 120,h: 104};
	$('#'+obj.id).setPreview(opt);
	$("#lbl_"+obj.id).hide();
};

$.fn.setPreview = function(opt){
	var defaultOpt = {
		inputFile: $(this),
		img: null,
		w: 120,
		h: 104
	};
	$.extend(defaultOpt, opt);

	if (!defaultOpt.inputFile || !defaultOpt.img) return;

	var inputFile = defaultOpt.inputFile.get(0);
	var img       = defaultOpt.img.get(0);

	// FileReader
	if (window.FileReader) {
		if (!inputFile.files[0].type.match(/image\//)) return;

		// preview
		try {
			var reader = new FileReader();
			reader.onload = function(e){
				img.src = e.target.result;
				img.style.width  = defaultOpt.w+'px';
				img.style.height = defaultOpt.h+'px';

			};
			reader.readAsDataURL(inputFile.files[0]);
		} catch (e) {
			// exception...
		}
		// img.filters (MSIE)
	} else if (img.filters) {
		inputFile.select();
		inputFile.blur();
		var imgSrc = document.selection.createRange().text;
		img.src ="";
		img.style.width  = defaultOpt.w+'px';
		img.style.height = defaultOpt.h+'px';
		img.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='fi" + "le://" + imgSrc + "', sizingMethod='scale')";

		// no support
	} else {
		// Safari5, ...
	}
};

function fileReset(file){
	var agent = navigator.userAgent.toLowerCase();
	if(agent.indexOf("msie") > -1 ){    //IE version
		$(file).replaceWith( $(file).clone(true) );
	}else{    //other browser
		$(file).val("");
	}
}

</script>