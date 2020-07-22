<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup photoReview-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>포토 리뷰 작성</h2>
		<div class="layer-cont scroll">
			
			<div class="Review-popWrap">
				<div class="board-write">
					<table summary="텍스트 리뷰 작성">
						<caption>포토 리뷰 작성</caption>
						<colgroup>
							<col style="width:120px;">
							<col>
						</colgroup>
						<tr>
							<td colspan="2">
								<div class="goodsreviewBox">
									<p class="txt13-666">리뷰 작성 시, 온라인몰에서 사용 가능한 포인트를 적립해 드립니다. (텍스트리뷰 300포인트, 포토리뷰 1,000포인트)</p>
									<div class="goods-R-ImgBox">
										<a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a>
									</div>
									<div class="goods-R-InfoBox">
										<div class="gd-Tpos">
											<strong>뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</strong>
										</div>
										<ul>
											<li><span>WHITE</span><span>230</span></li>
											<li>주문일시 : <span>2018년 10월 26일</span></li>
										</ul>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">별점</label></th>
							<td>
								<div class="starRev">
								  <a href="javascript:;" class="on">별1</a>
								  <a href="javascript:;" class="on">별2</a>
								  <a href="javascript:;" class="on">별3</a>
								  <a href="javascript:;" class="on">별4</a>
								  <a href="javascript:;" class="on">별5</a>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">사이즈</label></th>
							<td>
								<div>
									<span class="rdo-skin">
										<input type="radio" id="rdoType01" name="rdoType" checked="checked">
										<span>딱 맞아요</span>
									</span><label for="rdoType01">딱 맞아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType02" name="rdoType">
										<span>작아요</span>
									</span><label for="rdoType02">작아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType03" name="rdoType">
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
										<input type="radio" id="rdoType04" name="rdoType2" checked="checked">
										<span>화면과 같아요</span>
									</span><label for="rdoType04">화면과 같아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType05" name="rdoType2">
										<span>밝아요</span>
									</span><label for="rdoType05">밝아요</label>
									<span class="rdo-skin">
										<input type="radio" id="rdoType06" name="rdoType2">
										<span>어두워요</span>
									</span><label for="rdoType06">어두워요</label>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">상품리뷰</label></th>
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="특수문자 (\ / : < > & 등)는 사용할 수 없으며, 한글기준 최대 1,000자까지 작성 가능합니다."></textarea>
								<div class="clearfix">
									<div class="fl">
										<span class="error-msg" style="display:block;">더 이상 입력하실 수 없습니다.</span>
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000">0</em>자/1,000자</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContact">사진등록</label></th>
							<td>
								<span class="file-search02 imgFileSrch">
									<span>
										<input type="file" id="fileSearch">
										<label for="fileSearch">파일찾기</label>
									</span>
									<span>
										<img src="/static/images/_temp/goods_thumb_3.png" alt="">
										<a href="javascript:;" class="fileDel-Btn">파일삭제</a>
									</span>
									<span>
										<img src="/static/images/_temp/goods_thumb_4.png" alt="">
										<a href="javascript:;" class="fileDel-Btn">파일삭제</a>
									</span>
									<span class="error-msg" style="display:block;">사진을 하나 이상 등록하셔야 합니다.</span>
								</span>
								<span class="txt13-999 pdt05 pdb05">10MB 이하 JPG,PNG,GIF</span>
							</td>
						</tr>
					</table>
				</div>
			</div>	
			
			<div class="btnWrapBox">			
				<a href="javascript:;" class="btn fill">등록</a>
			</div>
		
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('.photoReview-pop'); 
	//별점
	$('.starRev a').click(function(){
	  $(this).parent().children('a').removeClass('on');
	  $(this).addClass('on').prevAll('a').addClass('on');
	  return false;
	});
});
</script>
</body>
</html>