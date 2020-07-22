<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>
					
	<input type="hidden" name="dlvAdbukTurn" id="dlvAdbukTurn" value=""/>
	<input type="hidden" name="callbackType"  id="callbackType" value=""/>
		
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">
		
			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>

			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents deliveryList-wrap" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<div class="tbst-div">
					<div class="mid fl">
						<span><spring:message code='mypage.inquiry.lbl.txt4' text="전체"/></span> (<span class="text-color01"  id="deliveryCnt">0</span><spring:message code='mypage.inquiry.lbl.txt5' text="건"/>)
					</div>
				</div>				
				

			</main>
			
		</div>
	</div>

<article id="layerPopup25" class="layer-popup deliveryModi-pop">
					
</article>
<!-- 우편번호 -->
<%@ include file="/WEB-INF/views/common/layerpop/zipcode.jsp"%>

<!-- 로컬 JS 스크립트 선언 -->
<script type="text/javascript">

	$(document).ready(function(){
		
		// 배송지 목록
		goDeliveryList();
		
		// 주소찾기 '확인','X'		
		/* $("#layerPopupZipcode .d_layer_close").click( function(){
		    layerPopup.popupOpenNow("#layerPopup25");
		}); */
	});
		
	function goDeliveryList(pageNo){
		
		$("#deliveryListBoard").remove();
		$("#deliveryListBoardPage").remove();
		$("#deliveryListBoardBtn").remove();
		
		if(pageNo == ""){
			pageNo = 1;
		}
		
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'pageNo' : pageNo };
// 		$("#includeDeliveryList").load("<c:url value='/mypage/member/include/deliveryLocationList'/>", strParams);
		$.ajax({
            type : "POST",
            url  : '/mypage/member/include/deliveryLocationList.ajax',
            data : strParams,
            success : function(data) {
                //$("#includeDeliveryList").html(data);
                $("#contents").append(data);
            },
            error : function(jqXHR, textStatus, error) {
            	if(jqXHR.status == "403") {
                	alert("<spring:message code='common.session.over.relogin'/>");
                	location.reload();
            	} else {
					alert("<spring:message code='common.system.error'/>");
				}
            }
		});
	}

	/**
	 * 팝업창 열기 - 배송지 추가 / 배송지 수정
	 */
	function getLayerPopupDeliverySetting(type , dlvAdbukTurn) {
		
		errorMsgHide($("#deliveryLocationInfo").parent().find("span.error-msg"));
		
		var strParams = null;
		if(type == "insert") {
			strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'type' : type };
		} else if(type == "modify") {
			strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'type' : type , 'dlvAdbukTurn' : dlvAdbukTurn };
		} else {
			return false;
		}

		$.ajax({
	        type:"POST",
	        url:"/mypage/member/deliveryLocationPop.ajax",
	        data : strParams,
	        success: function(data){	        	
	        	$('#layerPopup25').html("");
	    		$('#layerPopup25').html(data);
	    		layerPopup.popupOpenNow('#layerPopup25'); 	    		
	        },
	        error: function() {
            	if(jqXHR.status == "403") {
                	alert("<spring:message code='common.session.over.relogin'/>");
                	location.reload();
            	} else {
            		alert("<spring:message code='mypage.member.delivery.lbl.txt9' text='배송지 조회 오류' />");
				}
	        }
	    });
	}

	/**
	 * 배송지 삭제
	 */
	function deleteDeliveryLocation(dlvAdbukTurn, baseDlvspYn) {

		// callbackType 삭제
		$("#callbackType").val("delete");
		
		// 삭제 대상 순번 셋팅
		$("#dlvAdbukTurn").val(dlvAdbukTurn);
		
		if (baseDlvspYn == 1) {
			alertLayer(MESSAGES['mypage.js.member.delivery.msg.base.delete']);
		}else{
			/*
			 * commonFunction.js
			 * @param confirmBtnNm(클릭시 javascript:callbackConfirmLayer(true); 함수 실행)
			 */
			confirmLayer(MESSAGES['mypage.js.member.delivery.msg.delete.confirm']); /* 배송지를 삭제하시겠습니까? */
		}
	}
	
	/**
	 * 팝업창 열기 - 기본배송지 설정
	 */
	function setUserDeliveryBase() {

		$("#callbackType").val("base");
		
		if(!$('input[name=dlvAdbukTurn]').is(":checked")){
			alertLayer(MESSAGES['mypage.js.member.delivery.msg.choice']);
			return false;
		}

		/*
		 * commonFunction.js
		 * @param confirmBtnNm(클릭시 javascript:callbackConfirmLayer(true); 함수 실행)
		 */
		confirmLayer(MESSAGES['mypage.js.member.delivery.msg.base.setup']); /*기본배송지로 설정하시겠습니까?*/
	}
	
	/**
	 * confirmLayer에 대한 callback
	 */
	function callbackConfirmLayer(flag) {
		
		var callbackType = $("#callbackType").val();
		
		if(flag) {
			
			if(callbackType == "delete") {
				
				var strParams = { "mbrDlvsp.dlvAdbukTurn" : $("#dlvAdbukTurn").val() ,'${_csrf.parameterName}' : '${_csrf.token}'};
				$.ajax({
					type : "post",
					url : "<c:url value='/mypage/member/deleteDeliveryLocation.ajax'/>",
					data : strParams,
					success : function(args) {
						//alertLayer(MESSAGES['mypage.js.member.delivery.msg.delete.success']);
						//goDeliveryList();
						alert(MESSAGES['mypage.js.member.delivery.msg.delete.success'])
						location.reload();
					},
					error : function(e) {
		            	if(jqXHR.status == "403") {
		                	alert("<spring:message code='common.session.over.relogin'/>");
		                	location.reload();
		            	} else {
							alert("<spring:message code='common.system.error'/>");
						}
					}
				});
				
			}
			else if(callbackType == "base") {
				
				var strParams = {"member" : "N"  ,"mbrDlvsp.dlvAdbukTurn" : $("input:radio[name='dlvAdbukTurn']:checked").val(),'${_csrf.parameterName}' : '${_csrf.token}'};
				$.ajax({
					type : "post",
					url : "<c:url value='/mypage/member/setUserDeliveryBase.ajax'/>",
					data : strParams,
					success : function(args) {
						//alertLayer(MESSAGES['mypage.js.member.delivery.msg.setup.success']);
						//goDeliveryList();
						alert(MESSAGES['mypage.js.member.delivery.msg.setup.success']);
						location.reload();
					},
					error : function(e) {
		            	if(jqXHR.status == "403") {
		                	alert("<spring:message code='common.session.over.relogin'/>");
		                	location.reload();
		            	} else {
							alert("<spring:message code='common.system.error'/>");
						}
					}
				});
			}
			
		}else{
			closeConfirmLayer();
		}
	}
</script>