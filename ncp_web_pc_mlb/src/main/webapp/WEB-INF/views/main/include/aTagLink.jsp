<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
 
 
<script type="text/javascript">
$(document).ready(function() {
	
	$(document).on('click',"#mainVisual a,.contents-type01-box02 a,#categoryVisual a,.contents-type01-box01 a,.item-list04 a,.temp-box07 a,.item-list03-cont a,.item-list02-cont a",function(){
 
    	var obj = $(this);
        var conttCnncUrl = obj.attr('conttCnncUrl');
        
        var outptTpCd = obj.attr('outptTpCd');
        
        if(outptTpCd == 'god'){
 
            $('#realForm').attr('action', conttCnncUrl);

            $("#lnbDspCtgryForm").find('input').each(function(i) {     	
            	if($(this).val() !=''){
            		$('#realForm').append($(this));
            	} 	
            });

            var frm = $('#realForm');
         
        	frm.submit();
        	
        }else{
     
            var href = obj.attr('href');
            
            if(href != ''  &&  href != '#'  && href != 'undefined' && href != undefined && href.indexOf('javascript') < 0){
        		   location.href = href;
        	}
            
        	if(conttCnncUrl == ''  || conttCnncUrl == 'undefined' || conttCnncUrl == undefined ){
        		return false;
        	}
        	if(outptTpCd =='POPUP'){
        		 window.open(conttCnncUrl, "_blank", "scrollbars=1,resizable=1,height=562,width=1000");
      
        	}else if(outptTpCd =='NEW_WIN'){
        		window.open(conttCnncUrl, '_blank'); 
        	}else if(outptTpCd =='MOVPOPUP'){
        	
        		 $('#youtueiframe').attr('src', 'https://www.youtube.com/embed/'+conttCnncUrl);
        		 layerPopup.popupOpenNow('#layerYoutue');
        	
        	}else{
        	       location.href = conttCnncUrl;
        	}        	
        }

    });
	$(document).on('click',".youtueiframe",function(){
		 $('#youtueiframe').attr('src', '');
	});
});
	

</script>