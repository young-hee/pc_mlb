<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>

	<form id ="srchForm" action ="<c:url value ='/mypage/inquiry/list' />" method="post" >
		<input type ="hidden" name ="srchMtmInqSn" value ="" />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" name="pageNo"  id="pageNo" value=""/>

		<!-- 컨텐츠 시작 -->
		<div class="contain my lnblist-Wrap" id="contain">
			<div class="container">

				<h2 class="title01">1:1 문의</h2>

				<%@ include file="../include/lnb.jspf" %>

				<main class="contents oto_inquiryList-wrap" id="contents">

					<div class="location-contents">
						<p class="location">
							<span>Home</span>
							<span>마이페이지</span>
							<span>활동정보</span>
							<strong title="현재 위치">1:1 문의</strong>
						</p>
					</div>

					<p class="txt13-666">고객상담에 대한 내역과 답변을 확인하실 수 있습니다.<br/><em>고객센터 080-807-0012</em> (평일 AM 9시 ~ PM 6시 : 토/일 공휴일 휴무)</p>

					<div class="tbst-div">
						<div class="mid fl">
							<span>전체</span> (<span class="text-color01"><em class="num" id="inquiryCnt"></em></span>건)
						</div>
						<div class="mid fr">
							<a href="#" class="btn fill sm fdlr30 btn-style07 right" onclick="javascript:goInquiryNew()"><span>1:1 문의 등록</span></a>
						</div>
					</div>

					<%--1:1고객상담 목록--%>
					<div id="includeInquiryList"></div>
				</main>
			</div>
		</div>
	</form>

<script type="text/javascript" >

	$(document).ready(function(){

		goInquiryList();
	});

	function goInquiryList(pageNo){
		if(pageNo == ""){
			pageNo = 1;
		}

		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'pageNo' : pageNo };

		$.ajax({
            type : "POST",
            url     : '/mypage/inquiry/include/listInquiry.ajax',
            data : strParams,
            success : function(data) {
                $("#includeInquiryList").html(data);
                console.log(data);
                $('#wrap').scrollTop(0);
            },
            error : function(jqXHR, textStatus, error) {
                if(jqXHR.status == "403") {
                    alert("세션 만료");
                    location.reload();
                } else {
                    alert("시스템 오류 입니다.");
                }
            }
        });
	}

	/* 1:1 고객상담 신규입력 */
	function goInquiryNew(){
        $("#srchForm").attr("action","/helpdesk/csInquiry/new");
        $("#srchForm").submit();
    }

</script>
