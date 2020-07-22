<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="" class="layer-popup orderInfoSearch-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2>주문 찾기</h2>
		<div class="layer-cont scroll">
			
			<div class="orderInfoSearch-popWrap">
				<%@ include file="../_inc/uiDateRange2.jsp" %>
				
				<ul class="text-list02">
					<li>2019년 2월 20일 이후 주문만 조회 가능합니다.</li>
					<li>2019년 2월 20일 이전 주문은 고객센터 (080-807-0012) 로 문의해 주시면 답변해 드리겠습니다.</li>
				</ul>
				
				<div class="board-list">
					<table>
						<caption>주문찾기 주문정보, 주문일자, 상품명, 수량, 결제금액, 주문/배송상태</caption>
						<colgroup>
							<col style="width:40px;">
							<col style="width:130px;">
							<col style="width:80px;">
							<col>
							<col style="width:40px;">
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">주문정보</th>
								<th scope="col">주문일자</th>
								<th scope="col">상품명</th>
								<th scope="col">수량</th>
								<th scope="col">결제금액</th>
								<th scope="col">주문/배송상태</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="7" class="no-result">주문내역이 없습니다.</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" checked="checked" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>OD201810150002503</td>
								<td>2018-03-11</td>
								<td class="tleft">뉴욕양키스 스니커즈 운동화-니트잼머양키스(KNIT JAMMER YANKEES)</td>
								<td>1</td>
								<td><em>149,000</em>원</td>
								<td>결제완료</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>OD201810150002503</td>
								<td>2018-03-11</td>
								<td class="tleft">뉴욕양키스 메가테잎 다운 롱패딩(MEGA TAPE)</td>
								<td>2</td>
								<td><em>158,000</em>원</td>
								<td>결제완료</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>OD201810150002503</td>
								<td>2018-03-11</td>
								<td class="tleft">사은품 2018년 다이어리</td>
								<td>1</td>
								<td><em>0</em>원</td>
								<td>결제완료</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>OD201810150002503</td>
								<td>2018-03-11</td>
								<td class="tleft">[엑소운동화] 뉴욕양키스 스니커즈 - 블로커(BLOCKER)</td>
								<td>2</td>
								<td><em>16,000</em>원</td>
								<td>배송준비중</td>
							</tr>
							<tr>
								<td>
									<span class="rdo-skin">
										<input type="radio" id="" name="rdoType" />
										<span></span>
									</span><label for=""></label>
								</td>
								<td>OD201810150002503</td>
								<td>2018-03-11</td>
								<td class="tleft">[엑소운동화] 뉴욕양키스 스니커즈</td>
								<td>1</td>
								<td><em>8,000</em>원</td>
								<td>결제취소</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<%@ include file="../_inc/paging.jsp" %>
			</div>	
			
			<div class="btnWrapBox">			
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
	layerPopup.popupOpenNow('.orderInfoSearch-pop'); 
});
</script>
</body>
</html>