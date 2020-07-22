<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<%@ page import="com.plgrim.ncp.base.enums.DisplayEnum" %>
 
       <div id="lnb">
   <c:forEach items="${lnbCategorySet}" var="ctgry" varStatus="i">
      <div>
    <h3><a href="#" ctgryDpthCd="1" ctgryNo="${ctgry.dspCtgryNo }">${ctgry.dspCtgryNm }</a></h3>
        <c:forEach items="${ctgry.dxmDspFoCtgryResults}" var="ctgryOne" varStatus="i">
 
            &nbsp; &nbsp;<a href="#" ctgryDpthCd="2" ctgryNo="${ctgryOne.dspCtgryNo }">${ctgryOne.dspCtgryNm }</a> 
       
                <c:forEach items="${ctgryOne.dxmDspFoCtgryResults}" var="ctgryTwo" varStatus="i">
             
            &nbsp; &nbsp; &nbsp; &nbsp;<a href="#" ctgryDpthCd="3" ctgryNo="${ctgryTwo.dspCtgryNo }">${ctgryTwo.dspCtgryNm }</a> 
  
          </c:forEach>
             </br>
          </c:forEach>
    </div>
    </br>
   </c:forEach>
   </div>


<script type="text/javascript">
$("#Lnb a").click(function(){
    goAction(this);
});

function goAction(obj) {

    var url = "";  
    var ctgryNo = $(obj).attr("ctgryNo");
    var ctgryDpthCd = $(obj).attr("ctgryDpthCd");
    url = "/display/view";
    if(ctgryDpthCd =='1'){
        $('#dspCtgryNo').val(ctgryNo);
        
        $('#lnbDspCtgryForm').attr('action', url);
        var frm = $('#lnbDspCtgryForm');

        frm.submit();
    } else{
    	alert("ajax" + ctgryDpthCd);
    }
}
</script>