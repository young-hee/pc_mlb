<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp_base.env" var="env"/>
<script type="text/javascript">
function cpnDownKakao(){
	<c:choose>
		<c:when test="${env eq 'local' or env eq 'dev'}">
			const prmNo = "PR201901280000390";
		</c:when>
		<c:when test="${env eq 'stg'}">
			const prmNo = "PR201901290000660";
		</c:when>
		<c:when test="${env eq 'prod'}">
			const prmNo = "PR201902140000454";
		</c:when>
	</c:choose>
	var url = "/goods/content/addMemberCoupon.json";

	$.ajax({
		type : "POST",
		async : false,
		url : url,
		data : {prmNo : prmNo},
		success : function(data) {
			if(data.msg == 'success'){				
				alert(data.successMsg);
			}else{
				alert(data.errMsg);
			}
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}
</script>
	<!-- 컨텐츠 시작 -->
	<div class="contain dp promo view" id="contain">
		<div class="container">

			<main class="contents" id="contents">

				<div class="event_kakao_down">
					
					<div class="event_kakao_down">
						<div class="imgs"><img src="${_resourceURL}static/images/ev/event_kakao_down.jpg" alt=""></div>
						<div class="btns">
							<sec:authorize access="isAnonymous() or hasRole('ROLE_GUEST')">
							<a class="btn_kakao" href="#" onclick="openLayerPopupForLogin(); return false;"><img src="${_resourceURL}static/images/ev/event_kakao_down_btn.png" alt="쿠폰 다운로드 받기"></a>
							</sec:authorize>							
							<sec:authorize access="isAuthenticated() and not hasRole('ROLE_GUEST')">
							<a class="btn_kakao" href="#" onclick="cpnDownKakao(); return false;"><img src="${_resourceURL}static/images/ev/event_kakao_down_btn.png" alt="쿠폰 다운로드 받기"></a>
							</sec:authorize>	
							
						</div>
					</div>

				</div>

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->