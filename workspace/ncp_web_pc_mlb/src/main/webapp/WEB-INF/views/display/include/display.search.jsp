<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>


<form id="searchCtgryForm" method="get">
  <input type="hidden" name="dspCtgryNo" value="${dspCtgryScFrDTO.dspCtgryNo }">
  <input type="hidden" name="currentCtgryDpthCd" value="${dspCtgryScFrDTO.currentCtgryDpthCd }">
  <input type="hidden" name="ctgrySectCd" value="${dspCtgryScFrDTO.ctgrySectCd }">  
  <input type="hidden" name="ctgryNoDpth1" value="${dspCtgryScFrDTO.ctgryNoDpth1 }">
  <input type="hidden" name="ctgryNoDpth2" value="${dspCtgryScFrDTO.ctgryNoDpth2 }">
  <input type="hidden" name="ctgryNoDpth3" value="${dspCtgryScFrDTO.ctgryNoDpth3 }">
  <input type="hidden" name="prcStart" id="prcStart" value="${dspCtgryScFrDTO.prcStart }">
  <input type="hidden" name="prcEnd" id="prcEnd" value="${dspCtgryScFrDTO.prcEnd }">
  <input type="hidden" name="sortColumn" id="sortColumn">
  <input type="hidden" name="searchText" id="searchText" value="${dspCtgryScFrDTO.searchText }">
  <input type="hidden" name="pageSize" id="searchPageSize">
  <input type="hidden" name="pageNo" id="searchPageNo">
</form>
 
<script type="text/javascript">
$(document).ready(function() {
	
	if($("#prcEnd").val() !='0'){
		$( "#slider-range" ).slider("values", [$("#prcStart").val(),$("#prcEnd").val()] );	
		$("#amountStart").val($("#prcStart").val());
		$("#amountEnd").val($("#prcEnd").val());
	}
 
	$("#searchFilterBtn ,#itemColumn a ,#searchBtn,#searchBtnRt,.select-style03 a").click(function(){
 
		if($(this).attr('id') != 'searchBtnRt' && $(this).attr('id') != 'searchBtn'){
			$('.sizeChkBox:checked').each(function(i) { 
				$('#searchCtgryForm').append('<input type="hidden" name="searchConditionSizes"  value='+$(this).val()+'>');
		   });
			$("#colorFilters .on").each(function(i) { 
				$('#searchCtgryForm').append('<input type="hidden" name="searchConditionColors"  value='+$(this).val()+'>');
		   });	
		}
		
		
		if($(this).attr('id') == 'searchBtn'){
			fbq('track', 'Search');
			if($.trim($('#searchValue').val()) ==""){
				openCommonLayerPopup("<spring:message code='display.main.text9' />","<spring:message code='display.main.text10' />","<spring:message code='display.main.text11' />","commonLayerPopup");
			
			return false;
			}else{
				$('#searchText').val($('#searchValue').val());
				recoPick('sendLog','search', encodeURIComponent($('#searchValue').val()));	
			}			
		}
		if($(this).attr('id') == 'searchBtnRt' ){
			fbq('track', 'Search');
			if($.trim($('#searchVal').val()) ==""){
				openCommonLayerPopup("<spring:message code='display.main.text9' />","<spring:message code='display.main.text10' />","<spring:message code='display.main.text11' />","commonLayerPopup");
				return false;
			}else{
				$('#searchText').val($('#searchVal').val());
				recoPick('sendLog','search', encodeURIComponent($('#searchVal').val()));				
			}

		}
		goAction({pageNo:1},this);
 
	});
	
	$("#searchValue,#searchVal,#searchSubValue").keydown(function (key) {
 
        if(key.keyCode == 13){ 
        	if($.trim($(this).val()) ==""){
				openCommonLayerPopup("<spring:message code='display.main.text9' />","<spring:message code='display.main.text10' />","<spring:message code='display.main.text11' />","commonLayerPopup");
        		return false;
        	}else{
            	if($(this).attr('id') == 'searchValue'){
                	
            		$("#searchBtn").click();		
            	}else if($(this).attr('id') == 'searchSubValue') {
            		$('#searchValue').val($(this).val());
            		$("#searchBtn").click();
            	}else{
            		$("#searchBtnRt").click();
            	}
            	
        	}
        	
        }
 
    });
    $(".btn-style03").click(function(){
    	$(".sizeChkBox").prop('checked', false) ;
    	$("#colorFilters .on").removeClass('on');
		$( "#slider-range" ).slider("values", [0,300] );	
		$("#amountStart").val(0);
		$("#amountEnd").val(300);
    });

    $(".page a").click(function(){
    	   var pageUrl = document.URL;

    	   if(pageUrl.indexOf('/display') > -1 ){
    			$('.sizeChkBox:checked').each(function(i) { 
    				$('#searchCtgryForm').append('<input type="hidden" name="searchConditionSizes"  value='+$(this).val()+'>');
    		   });
    			$("#colorFilters .on").each(function(i) { 
    				$('#searchCtgryForm').append('<input type="hidden" name="searchConditionColors"  value='+$(this).val()+'>');
    		   });
    		 	goAction({pageNo:$(this).attr("id")});
    	   }
   
    });
	
function goAction(obj,v) {
	$("#prcStart").val($("#amountStart").val());
	$("#prcEnd").val($("#amountEnd").val());
	if($(v).closest('#itemColumn').attr('id') == 'itemColumn'){
 
		$("#sortColumn").val($(v).attr('id'));	
	}else{
		$("#sortColumn").val($("#itemColumn .on").find('a').attr('id'));		
	} 
	$("#searchPageSize").val($("#selectPageSize span input").val());
	$("#searchPageNo").val(obj.pageNo);
	
    var  url = "/display/view";
    
    if( $("#searchCtgryForm").find('[name=currentCtgryDpthCd]').val()  == '1'){
        url = "/display/majorView";
    }
     var cnrsUrl = document.URL;
 
	  var snsFlag = false;
	  if($(v).attr('id') == 'searchBtn' || $(v).attr('id') == 'searchBtnRt'){
		  url = "/display/search";
	  }
	  if(cnrsUrl.indexOf('/search') != -1){
		  url = "/display/search";
	  }
 
	 if($("#searchPageSize").val() ==''){
		 $("#searchPageSize").val('40');  
	 }
	   
	 $('#realForm').attr('action', url);
	  
	 $("#searchCtgryForm").find('input').each(function(i) {     		 
		 if($(this).val() !=''){	    
			 $('#realForm').append($(this));	    	
		 } 		    
	 });
	 
	 var frm = $('#realForm');	 
	 frm.submit();
}
});
	

</script>