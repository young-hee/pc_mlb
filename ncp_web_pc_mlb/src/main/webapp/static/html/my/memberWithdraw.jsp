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
		
			<h2 class="title01">회원탈퇴</h2>

			<%@ include file="../_inc/lnb.jsp" %>
			
			<main class="contents memberWithdraw-wrap" id="contents">
				
				<div class="location-contents">
					<p class="location">
						<span>Home</span><span>마이페이지</span><span>회원정보</span><strong title="현재 위치">회원탈퇴</strong>
					</p>
				</div> 
				
				<!-- 회원정보S -->
				<div class="memberSTcont boxCont04">
					<ul>
						<li class="colorBlk">
							<p>Level</p>
							<strong>ROOKIE</strong>
						</li>
						<li>
							<p>마일리지</p>
							<strong><a href="javascript:;" class="text-color01">25,000</a>원</strong>
						</li>
						<li>
							<p>포인트</p>
							<strong><a href="javascript:;" class="text-color01">5,000</a>원</strong>
						</li>
						<li>
							<p>쿠폰</p>
							<strong><a href="javascript:;" class="text-color01">2</a>장</strong>
						</li>
					</ul>
					<p class="txt13-666">F&F에서 운영하는 모든 패밀리 브랜드로부터 함께 탈퇴 되며 30일 이내 재가입이 불가합니다.<br/>탈퇴 시 보유하고 계신 마일리지, 포인트, 쿠폰은 즉시 자동 소멸되며 이후 다시 확인하실 수 없습니다.<br/>회원약관 및 개인정보 제공, 활용에 관한 약관 동의가 모두 철회됨을 알려드립니다.</p>
					<strong class="txt13-000"><b>F&F Family Brand</b>&nbsp;&nbsp;&nbsp;>&nbsp;&nbsp;MLB,MLB KIDS,Discovery Expedition,COLLECTED,JARDIN PERDU,Stretch Angels</strong>
				</div>
				<!-- //회원정보E -->
				
				<hr class="hr-666" />
				
				<!-- 주문/클레임 현황 정보 영역S -->
				<div class="memberODinfo">
					<strong class="txt16-000">고객님께서 F&F 패밀리 사이트에서 현재 거래중인 주문 및 서비스를 확인해 주세요.</strong>
					<p class="txt13-666">구매상품이 배송/교환/반품 중인 상태이거나 클레임 진행중인 상태인 경우 <em>모두 완료된 후 탈퇴 가능합니다.</em></p>
					<div class="memberODSTBox">
						<ul>
							<li>
								<strong>진행중인 주문</strong>
								<p>구매 <span class="text-color01">2</span>건</p>
								<a href="javascript:;" class="btn fill">주문/배송 현황</a>
							</li>
							<li>
								<strong>취소/반품/교환</strong>
								<p>취소 <span class="text-color01">2</span>건 / 반품 <span class="text-color01">2</span>건 / 교환 <span class="text-color01">2</span>건</p>
								<a href="javascript:;" class="btn fill">취소/반품/교환 현황</a>
							</li>
						</ul>
					</div>
					
					<!-- 진행중인 주문/클레임 건이 있어서 탈퇴할 수 없는 경우S -->
					<div class="memberWithdraw-inner" style="display:none;">
						<span class="error-msg">현재 진행중인 주문 및 클레임이 모두 완료되어야 탈퇴하실 수 있습니다.</span>
					</div>
					<!-- //진행중인 주문/클레임 건이 있어서 탈퇴할 수 없는 경우E -->
					
					<!-- 탈퇴 가능한 경우S -->
					<div class="memberWithdraw-inner">
						<div class="contTxtBox">
							<strong>유의사항</strong>
							<ul class="text-list02">
								<li>탈퇴 후 30일간 재가입 방지 및 마일리지 부정사용을 방지하기 위해 CI 등 일부 회원 정보가 보존됩니다.<br/>(회원가입 시 동의하신 개인정보 취급 방침에 명시한 파기절차와 방법에 따라 30일 이에 모두 파기됩니다.)</li>
								<li>전자상거래 이용내역이 있는 회원님은 전자상거래 등에서의 소비자보호에 관한 법률에 의거 교환/반품/환불 및 사후처리(A/S) 등을 위해 회원정보가 관리됩니다.</li>
							</ul>
						</div>
						<div class="memberWithdrawInfo">
							<strong>회원탈퇴 사유</strong>
							<div class="memberWithdrawGray">
								<ul>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType01" name="rdoType" checked="checked">
											<span>배송불만</span>
										</span>
										<label for="rdoType01">배송불만</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType02" name="rdoType">
											<span>이용빈도 낮음</span>
										</span>
										<label for="rdoType02">이용빈도 낮음</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType03" name="rdoType">
											<span>A/S 불만</span>
										</span>
										<label for="rdoType03">A/S 불만</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType04" name="rdoType">
											<span>상품의 다양성/가격 품질 불만</span>
										</span>
										<label for="rdoType04">상품의 다양성/가격 품질 불만</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType05" name="rdoType">
											<span>개인정보유출 우려</span>
										</span>
										<label for="rdoType05">개인정보유출 우려</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType06" name="rdoType">
											<span>쇼핑몰 시스템 불만</span>
										</span>
										<label for="rdoType06">쇼핑몰 시스템 불만</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType07" name="rdoType">
											<span>교환/환불 불만</span>
										</span>
										<label for="rdoType07">교환/환불 불만</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType08" name="rdoType">
											<span>회원특혜/쇼핑혜택 낮음</span>
										</span>
										<label for="rdoType08">회원특혜/쇼핑혜택 낮음</label>
									</li>
									<li>
										<span class="rdo-skin">
											<input type="radio" id="rdoType09" name="rdoType">
											<span>기타</span>
										</span>
										<label for="rdoType09">기타</label>
									</li>
								</ul>
								<p class="txt13-666">회원 탈퇴 사유를 입력해 주시면 보다 나은 서비스로 찾아 뵙겠습니다.</p>
								<textarea cols="30" rows="10" id="" placeholder="특수문자( / : < > & 등) 는 사용할 수 없으며, 한글 기준 최대 100자까지 작성 가능합니다."></textarea>
							</div>
						</div>
						
						<div class="btnWrapBox">
							<a href="javascript:;" class="btn">탈퇴 요청</a>
						</div>
					</div>
					<!-- //탈퇴 가능한 경우E -->
					
					<hr class="hr-ddd" />
					
					<p class="txt13-666">문의 : 고객센터 <b class="txt13-000">080-807-0012</b> 운영시간 평일 AM 9시 ~ PM 6시 (토/일/공휴일 휴무)</p>
					
				</div>
				<!-- //주문/클레임 현황 정보 영역E -->

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