<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<form name="gForm" id="gForm" method="post" action="${bizNice.actionUrl}" >
</form>

<script language="JavaScript">
	//window.onload 는 $(document).ready 보다 뒤에 실행됨.
	window.onload = function() {
		end();
	}

	function end() {
		var certificationDiv = "${bizNice.certificationDiv}";
		var gForm = $("#gForm");
		// 휴대폰 본인인증
		if(certificationDiv === "PCC") {
			addHidden(gForm, "m", "checkplusSerivce");
			addHidden(gForm, "EncodeData", "${bizNice.reqInfo }");
		}
		// IPIN 인증
		else if(certificationDiv === "IPIN") {
			addHidden(gForm, "enc_data", "${bizNice.reqInfo }");
		}
		else {
			alert("잘못된 접근입니다. [" + certificationDiv + "]");
		}
		
		document.gForm.submit();
	}

	function addHidden(f, key, value) {
		var el = document.createElement("INPUT");
		el.type = "hidden";
		el.name = key;
		el.value = value;

		f.append(el);
	}

</script>
