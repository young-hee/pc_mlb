<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<form id ="srchForm" action ="<c:url value ='/mypage/todaygood/list' />" method="post" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="todayGodSn"  id="todayGodSn"  value=""/>
	<input type="hidden" name="pageNo"      id="pageNo"      value=""/>
	<input type="hidden" name="totalCnt"    id="totalCnt"    value=""/>
	<input type="hidden" name="totPage"     id="totPage"     value=""/>
	<input type="hidden" name="deleteAllYn" id="deleteAllYn" value=""/>

	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">최근 본 상품</h2>

			<%-- <jsp:include page="../include/lnb.jsp" flush="true" /> --%>
			<%@ include file="../include/lnb.jspf" %>

			<main class="contents latestGoods-wrap" id="contents">

				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span>마이페이지</span>
						<span>활동정보</span>
						<strong title="현재 위치">최근 본 상품</strong>
					</p>
				</div>

				<p class="txt13-666">최근 7일 이내 보신 상품 리스트를 확인하실 수 있습니다.</p>

				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01" id="todaygoodCnt"></span>건)
					</div>
					<div class="mid fr">
						<a href="javascript:;" onclick="javascript:deleteAllTodayGoodList();return false;" class="btn fill sm"><span>전체삭제</span></a>
					</div>
				</div>

				<hr class="hr-666" />

				<%--최근본상품 목록--%>
				<div id="includetodayGoodList">
					<%-- <%@ include file="../_inc/paging.jsp" %> --%>
				</div>

				<hr class="hr-ddd" />

				<div class="mdGoodsProduct-List">
					<p>${_user.mbr.mbrNm}고객님을 위한 추천상품입니다.</p>
					<%@ include file="/WEB-INF/views/mypage/include/inc_recommend.jsp" %>
				</div>
			</main>
		</div>
	</div>
</form>

<script>

	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};
	$("#includetodayGoodList").load("<c:url value='/mypage/todaygood/include/list'/>", strParams);

	function goTdgodList(pageNo){

		if(pageNo == ""){
	        pageNo = 1;
	    }

	    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'pageNo' : pageNo};

	    $("#includetodayGoodList").load("<c:url value='/mypage/todaygood/include/list'/>", strParams);
	}

	function setTodaygoodCnt(todaygoodCnt){
		$("#todaygoodCnt").empty();
		$("#todaygoodCnt").append(todaygoodCnt);
		$("#totalCnt").val(todaygoodCnt);
	}


	/* 최근본상품 전체 삭제 */
	function deleteAllTodayGoodList(){
		var totalCnt = $("#totalCnt").val();

		if(totalCnt > 0){
			if (confirm('전체 삭제하시겠습니까?') == true) {
				// 삭제구분 전체,단건
				$("#deleteAllYn").val("Y");

				deleteTodayGoodListProc();
			} else {
				return false;
			}
		}
	}

	function deleteTodayGoodListProc() {
		var pageNo   = $("#pageNo").val() ;
		var totalCnt = $("#totalCnt").val() ;
		var totPage  = $("#totPage").val() ;
		var modYn    = (totalCnt-1) % 8 ;

		var deleteAllYn = $("#deleteAllYn").val();

		if(modYn==0 && pageNo==totPage){
			pageNo = pageNo -1
		}

		var parmUrl="";
		if(deleteAllYn == "Y"){
			parmUrl = "/mypage/todaygood/deleteAllTodayGoodList";
			pageNo = 1
		}else{
			parmUrl = "/mypage/todaygood/deleteGodTodayGoodList.json";
		}

		$.ajax({
			type  : "POST",
			async : false,
			url   : parmUrl,
			data  : $("#srchForm").serialize(),
			success : function(data) {
				alert('삭제되었습니다.');
				// 리스트조회
				goTdgodList(pageNo);
			},
			error: function(pa_data, status, err) {
	            alert("error forward : "+err+" ,status="+status);
	        }
		});
	}
</script>
