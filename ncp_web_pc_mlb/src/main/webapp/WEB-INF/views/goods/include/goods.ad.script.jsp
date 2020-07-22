<!-- CRITEO  SCRIPT START 2018.8.10 -->
<script type="text/javascript">
/* 
	Criteo AD script	
	사이트 Type(모바일 페이지는 "m", 태블릿 페이지는 "t", 일반 웹 사이트는 "d"로 삽입)	
*/
window.criteo_q = window.criteo_q || [];
window.criteo_q.push(  
        { event: "setAccount", account: "61384" },        
        { event: "setSiteType", type: "d" },
        { event: "viewItem", item: _erpNo }
);
fnf_appendCriteoScript();
</script>
<!-- //CRITEO  SCRIPT START 2018.8.10 -->

<!-- WIDERPLANET  SCRIPT START 2018.8.10 -->
<div id="wp_tg_cts" style="display:none;"></div>
<script type="text/javascript">
var wptg_tagscript_vars = wptg_tagscript_vars || [];
wptg_tagscript_vars.push(
(function() {
	return {
		wp_hcuid:USER.mbrNo,  	/*고객넘버 등 Unique ID (ex. 로그인  ID, 고객넘버 등 )를 암호화하여 대입.
		*주의 : 로그인 하지 않은 사용자는 어떠한 값도 대입하지 않습니다.*/
		ti:"39428",
		ty:"Item",
		device:"web",
		items:[{i:_erpNo, t:_godNm}] /* i:<상품 식별번호  (Feed로 제공되는 상품코드와 일치하여야 합니다 .) t:상품명  */
		};
}));
fnf_appendTargetGateScript();
</script>
<!-- // WIDERPLANET  SCRIPT END 2018.8.10 -->

<!-- PIXEL  SCRIPT START 2018.8.10 -->
<script type="text/javascript">
/* 
	웹사이트 행동 : 표준 이벤트 코드
	콘텐츠 조회 : ViewContent
	검색 : Search
	장바구니 담기 : AddToCart
	위시리스트 추가 : AddToWishlist
	결제 시작 : InitiateCheckout
	결제 정보 추가 : AddPaymentInfo {value: '0.00', currency: 'USD'}
	구매 : Purchase
	READ : Lead
	등록 완료 : CompleteRegistration
 */
 goodsPixclTrack("ViewContent");
 function goodsPixclTrack(eventCode) {	
 	fbq('track', eventCode);
 };
</script>
<!-- PIXEL  SCRIPT START 2018.8.10 -->