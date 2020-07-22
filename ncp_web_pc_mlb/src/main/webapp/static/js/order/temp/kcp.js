var issueFlag = true;
var fnfKcpPay = {
	
	kcpMobilePay : function() {
		

        var form = document.order_info;
        
		if(issueFlag){
			issueFlag = false;
			alert('send');
			sessionStorage.injectData = "가나다라마바사";
			$.ajax({
				type:"post"
				,url:"/sample.order/mkcpApprove.json"
				,data : "site_cd=" + form.site_cd.value
	                + "&ordr_idxx=" + form.ordr_idxx.value
	                + "&good_mny=" + form.good_mny.value
	                + "&pay_method=" + form.pay_method.value
	                + "&escw_used=" + form.escw_used.value
	                + "&good_name=" + form.good_name.value
	                + "&response_type=" + form.response_type.value
	                + "&Ret_URL=" + form.Ret_URL.value
				,dataType: "json"
				,async : false
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
					
				}
				,success : function(ret) {
					issueFlag = true;
					

					if( ret.kcpMobileDTO.code == '0000' )
			        {
			            document.getElementById( "approval" ).value = ret.kcpMobileDTO.approvalKey;
			            // 아래 alert는 삭제 해도됨
			            alert("성공적으로 거래가 등록 되었습니다.");
			            PayUrl = ret.kcpMobileDTO.payUrl;
			            
			            document.getElementById( "PayUrl"  ).value = ret.kcpMobileDTO.request_URI;
			            document.getElementById( "traceNo" ).value = ret.kcpMobileDTO.traceNo;
			            
			            call_pay_form();
			            
			            
			        }
			        else
			        {
			            ajax_flag=true;
			            
			            alert("실패 되었습니다.[" + ret.kcpMobileDTO.Message + "]");
			        }
				}
				,error : function(xhr) {
					alert(xhr.responseText);
					//alert(data.message);
					
					issueFlag = true;
				}
				,complete : function(data) {

				}
			});
			
		}
		
	

	},

	kcpMobileApprovePay : function() {
		
	
	    var form = document.pay_form;
	    
		if(issueFlag){
			issueFlag = false;
			alert('send');
			//frm.submit();
		    $.ajax({
				type:"post"
				,url:"/sample.order/kcp.pay.json"
				,data : $("#pay_form").serializeJSON()
				,dataType: "json"
				,async : false
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
					
				}
				,success : function(ret) {
					issueFlag = true;
					alert('callback');
					alert(JSON.stringify(ret));
				}
				,error : function(xhr) {
					alert(xhr.responseText);
					//alert(data.message);
					
					issueFlag = true;
				}
				,complete : function(data) {

				}
			});
			
		}
		
	
	
	}
};
