<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script language="JavaScript">
	//window.onload 는 $(document).ready 보다 뒤에 실행됨.
	window.onload = function() {
		end();
	}
    function end() {
    	var lang = "${pageContext.response.locale.language}";
        //휴대폰인증 실패의 경우 result 가 'N'
        var certResult = '${certificationResult}';
        if ("Y" != certResult) {
        	if(lang == undefined || lang == "ko") {
        		alert( "본인인증에 실패하였습니다. 확인 후 다시 시도하시길 바랍니다." );
        	}
        	else {
        		alert( "Your verification failed. Please check and try again." );
        	}
            self.close();
            return;
        }

        var checkCertMbr = '${checkCertMbr}';
        checkCertMbr = (checkCertMbr != '' ? $.parseJSON(checkCertMbr) : '');

        var srv = '${srv}';
        srv = (srv != '' ? $.parseJSON('${srv}') : '');

        var is14 = '${is14}';
        is14 = (is14 != '' ? $.parseJSON('${is14}') : false);

        var memberFailERPIF = '${memberFailERPIF}';
        var joinPossibility = '${joinPossibility}';
        var possibilityCode = '${possibilityCode}';
        
        if(memberFailERPIF != undefined && memberFailERPIF == "true") {
        	alert( "현재 연동 서비스 제공이 원활하지 못해\n일시적으로 진행이 불가합니다.\n이용에 불편을 드려 죄송합니다." );
        }
        else {
	        try {
				if('newjoin' === srv) {
					window.opener.callbackCertifyJoinMember(checkCertMbr, is14, joinPossibility, possibilityCode);
				}
				else if('find' === srv) {
					window.opener.callbackCertifyFindMember(checkCertMbr);
				}
			} catch (e) {
				alert("※보안 수준 변경 필요\n->인터넷 옵션 설정\n->보안 탭 이동\n->인터넷 또는 로컬 인트라넷 아이콘 click\n->보호 모드 사용 check or uncheck\n->브라우져 재시작");
			}
	    }

		window.opener = 'self';
		window.open('', '_parent', '');
 		window.close();
    }
</script>
