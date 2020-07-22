<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<article id="layerPopupZipcode" class="layer-popup addressFind-pop">
	<section class="layer-popup-cont" tabindex="0">
		<h2><spring:message code="common.popup.zipcode.ttl"/></h2> <%-- 우편번호 찾기 --%>
		<div class="layer-cont scroll">
			
			<div class="addressFind-popWrap" id="zipSearchBefore">
			
				<!-- 검색S -->
				<div class="addressFindSrch">
					<input type="search" class="input-style02" name="srchKeyword" id="srchKeyword" onkeydown="if(event.keyCode == 13) getSearchZipcode()">
					<a href="#" class="btn sm"  onclick="javascript:getSearchZipcode(); return false;"><spring:message code="common.popup.zipcode.btn.search"/></a>
				</div>
				<!-- //검색E -->
				
				
			</div>	
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"></button>
		</div>
	</section>
</article>

<script>
function getSearchZipcode(pageNo){
	
    var keyword = $("#srchKeyword").val();
	if((keyword == "" || keyword.trim() == "")){
		//검색어 입력해 주세요.
		alert("<spring:message code='common.popup.zipcode.msg.alert1'/>");
        $("#srchKeyword").focus();
        return;
	}

    $("#srchKeyword").val(keyword.replace('\'', '`'));
    
    if(pageNo == ""){
		pageNo = 1;
	}
	
	var strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'pageNo' : pageNo, 'srchKeyword' : $("#srchKeyword").val() };
    
	$.ajax({
		type : "POST",
		async : true,
		url : "/common/zipcode/list",
		data : strParams,
		success : function(data) {
			console.log(data)
			//$('#zipcode_list').html(data);
			$("#zipView1").remove();	
			$("#zipView2").remove();	
			$('#zipSearchBefore').append(data);
		},
		error: function(pa_data, status, err) {
            alert("error forward : "+err+" ,status="+status);
        }
	});	
}
</script>