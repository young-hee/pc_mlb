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
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01">1:1 문의</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents oto_inquiryWrite-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">1:1 문의</strong>
					</p>
				</div> 
				
				<div class="board-write">
					<table summary="1:1고객상담 입력">
						<caption>1:1문의</caption>
						<colgroup>
							<col style="width:200px;">
							<col>
						</colgroup>
						<tr>
							<th scope="row"><label for="boardWriteEmail">답변 이메일</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteEmail" class="input-style01" value="abcd" style="width:110px;">
								<span class="at">@</span>
								<input type="text" class="input-style01" style="width:110px;">
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
								<span class="error-msg" style="display:block;">유효한 이메일주소를 입력해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWritecall">휴대전화</label> <span class="required">*</span></th>
							<td>
								<div class="inputcallBox">
									<input type="text" id="boardWritecall" class="input-style01" value="010" style="width:64px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="1234" style="width:70px;" />
									<span class="hyphen">-</span>
									<input type="text" class="input-style01" value="5678" style="width:70px;" />
								</div>

								<span class="check-skin">
									<input type="checkbox" id="chkReply" checked="checked">
									<span>선택</span>
								</span>
								<label for="chkReply">답변등록여부 수신</label>

								<span class="error-msg" style="display:block;">잘못된 휴대전화번호입니다.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteSort">상담분류</label> <span class="required">*</span></th>
							<td>
								<!-- select -->
								<div class="select-style01 d_select select_consul_cate">
									<button type="button" id="boardWriteSort" class="d_select_sel" style="width:245px;"><span>선택</span></button>
									<ul>
										<li><a href="javascript:;">선택</a></li>
										<li><a href="javascript:;">배송지연/불만</a></li>
										<li><a href="javascript:;">반품문의</a></li>
										<li><a href="javascript:;">A/S문의</a></li>
										<li><a href="javascript:;">환불문의</a></li>
										<li><a href="javascript:;">주문결제문의</a></li>
										<li><a href="javascript:;">회원정보문의</a></li>
										<li><a href="javascript:;">취소문의</a></li>
										<li><a href="javascript:;">교환문의</a></li>
										<li><a href="javascript:;">상품정보문의</a></li>
										<li><a href="javascript:;">기타문의</a></li>
										<li><a href="javascript:;">이벤트문의</a></li>
									</ul>
								</div>
								<!-- //select -->
								<span class="error-msg" style="display:block;">상담분류를 선택해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><label for="boardWriteTitle">제목</label> <span class="required">*</span></th>
							<td>
								<input type="text" id="boardWriteTitle" class="input-style01" placeholder="30자 미만으로 입력해 주세요." style="width:515px;">
								<span class="error-msg" style="display:block;">30자 미만으로 입력해 주세요.</span>
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
									<li>파일은 1개만 첨부할 수 있습니다. (100MB 이하)</li>
									<li>파일형식 : hwp, doc/docx, xls/xlsx, ppt/pptx, jpg, gif, png, pdf</li>
								</ul>
							</td>
						</tr>
					</table>
				</div>
				
				<ul class="text-list02 col-type01">
					<li>비회원으로 문의하신 1:1 문의 내용은 입력하신 [답변이메일]로 알려 드리겠습니다.</li>
					<li>문의하신 내용에 대한 답변은 <span class="txt13-000">마이페이지 > 활동정보 > 1:1 문의</span>에서 확인하실 수 있습니다.</li>
				</ul>
				
				<div class="agreement-Wraper">
					<h3 class="title02">개인정보 수집 이용에 대한 동의</h3>
					
					<!-- 20190131 추가 -->
					<section class="agree-section-content">
					
						<%@ include file="../et/popTerms_bundle_ko.jsp" %>
					
					</section>
					<!-- //20190131 추가 -->
					
					<div class="txtFR-txt">
						<span class="rdo-skin">
							<input type="radio" id="rdoType01" name="rdoType" checked="checked">
							<span>동의합니다.</span>
						</span><label for="rdoType01">동의합니다.</label>
	
						<span class="rdo-skin">
							<input type="radio" id="rdoType02" name="rdoType">
							<span>동의하지 않습니다.</span>
						</span><label for="rdoType02">동의하지 않습니다.</label>
					</div>
				</div>
				
				<div class="btnWrapBox">
					<a href="/static/html/my/oto_inquiryList.jsp" class="btn">취소</a>
					<a href="javascript:;" class="btn fill">저장</a>
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