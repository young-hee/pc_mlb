var issueFlag = true;
var fnfNpay = {
	
	npay : function(jparam) {
		
		
		if(issueFlag){
			issueFlag = false;
			alert('send');
			
			$.ajax({
				type:"post"
				,url:"/sample.order/npay.json"
				,data : jparam
				,dataType: "json"
				,async : true
				,beforeSend : function(request) {
					var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
					var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
					request.setRequestHeader(csrfName, csrfToken);
					
				}
				,success : function(ret) {
					issueFlag = true;
					alert('callback');
					alert(JSON.parse(ret).kcpMobileDTO.Code);
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
