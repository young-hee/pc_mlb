<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body class="body">
<div class="wrap" id="wrap">
	<%@ include file="../_inc/header.jsp" %>
	<%@ include file="../_inc/gnb.jsp" %>

	<!-- 컨텐츠 시작 -->
	<div class="contain cs" id="contain">
		<div class="container">
		
			<h2 class="title01">단체구매문의</h2>
			
			<main class="contents bundleOrderWrite-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>고객센터</span><strong title="현재 위치">단체구매문의</strong>
					</p>
				</div> 
				
				<div class="board-write">
					<table summary="단체구매문의 입력">
						<caption>단체구매문의 입력</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteName">단체명</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName" class="input-style01" style="width:590px;" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteName2">담당자명</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteName2" class="input-style01" style="width:590px;" />
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteEmail">답변 이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteEmail" class="input-style01" value="abcd" style="width:150px;">
								<span class="at">@</span>
								<input type="text" class="input-style01" style="width:150px;">
								<!-- select -->
								<div class="select-style01 d_select">
									<button type="button" class="d_select_sel" style="width:150px;"><span>직접입력</span></button>
									<ul>
										<li><a href="javascript:;">직접입력</a></li>
										<li><a href="javascript:;">naver.com</a></li>
										<li><a href="javascript:;">daum.net</a></li>
										<li><a href="javascript:;">nate.com</a></li>
										<li><a href="javascript:;">gmail.com</a></li>
										<li><a href="javascript:;">hotmail.com</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:block;">잘못된 이메일 형식입니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall">휴대폰</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall" class="input-style01" value="" style="width:60px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="" style="width:70px;" />
								</div>

								<span class="check-skin">
									<input type="checkbox" id="chkReply" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkReply">답변등록여부 수신</label>

								<span class="error-msg" style="display:block;">숫자만 입력해야 합니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteContent">내용</label> <span class="required">*</span></th>
							<!-- 20180110 코드수정 -->
							<td>
								<textarea cols="30" rows="10" id="boardWriteContent" placeholder="1,000자 미만 (특수문자 \ / : < > ; 사용불가)으로 입력해 주세요." style="width:515px; height:150px;"></textarea>
								<div class="clearfix">
									<div class="fl">
										<span class="error-msg" style="display:block;">더 이상 입력하실 수 없습니다.</span>
									</div>
									<div class="fr">
										<span class="txt13-999"><em class="txt13-000">0</em>자/1,000자</span>
									</div>
								</div>
							</td>
							<!--// 20180110 코드수정 -->
						</tr>
						<tr>
							<th scope="row"><label for="fileSearch">첨부파일</label></th>
							<!-- 20180110 코드수정 -->
							<td>
								<span class="file-search">
									<input type="text" class="input-style01" style="width:443px;">
									<span>
										<input type="file" id="fileSearch">
										<label for="fileSearch">파일찾기</label>
									</span>
								</span>
								<!-- <div class="file-name">test.png<a href="javascript:;" class="btn-file-delete">삭제</a></div>  -->
								<ul class="text-list02 col-type02">
									<li>파일은 1개만 첨부할 수 있습니다. (10MB 이하)</li>
									<li>파일형식 : hwp, doc/docx, xls/xlsx, ppt/pptx, jpg, gif, png, pdf</li>
								</ul>
							</td>
							<!--// 20180110 코드수정 -->
						</tr>
					</table>
				</div>
				<ul class="text-list02 col-type01">
					<li>단체구매문의는 입력하신 답변 이메일을 통해 답변 드리겠습니다.</li>
				</ul>
				<div class="btn-wrap">
					<a href="javascript:;" class="btn lg">취소</a>
					<a href="javascript:;" class="btn lg fill">저장</a>
				</div>

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->

	<%@ include file="../_inc/footer.jsp" %>
</div>
<%@ include file="../_inc/bottom.jsp" %>
<script>
$(document).ready(function(){
	// 
});
</script>
</body>
</html>