(function(){
	
	if (window.CommonResponseCode) {
		return;
	}

	function convertMessage(message){
		//return message.replace(/_/g,"\n");
		return message;
	}

	function ResponseCode(groupCode, detailCode, message) {
		this.groupCode = groupCode;
		this.detailCode = detailCode;
		this.message = convertMessage(message);
		
	}
	ResponseCode.prototype.getCode = function() {
		return this.code;
	};

	ResponseCode.prototype.toMessage = function() {
		return this.message
	};

	window.CommonResponseCode = {

	//@formatter:off
			
		CL_00003_CDC반품완료_호출  = new ResponseCode("CL", "00003", "CDC반품완료_호출"),

	//@formatter:on
		
	};
	
}());
