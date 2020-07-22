<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>
  <%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>

<!--RecoPick 로그수집 스크립트 -->
<script type="text/javascript">
HashMap = function(){
	 this.hashMap = new Object();
	};   
	HashMap.prototype = {   
	    put : function(key, value){   
	        this.hashMap[key] = value;
	    },   
	    get : function(key){   
	        return this.hashMap[key];
	    },
	    containsKey : function(key){    
	     return key in this.hashMap;
	    },
	    containsValue : function(value){    
	     for(var prop in this.hashMap){
	      if(this.hashMap[prop] == value) return true;
	     }
	     return false;
	    },
	    isEmpty : function(key){    
	     return (this.size() == 0);
	    },
	    clear : function(){   
	     for(var prop in this.hashMap){
	      delete this.hashMap[prop];
	     }
	    },
	    remove : function(key){    
	     delete this.hashMap[key];
	    },
	    keys : function(){   
	        var keys = new Array();   
	        for(var prop in this.hashMap){   
	            keys.push(prop);
	        }   
	        return keys;
	    },
	    values : function(){   
	     var values = new Array();   
	        for(var prop in this.hashMap){   
	         values.push(this.hashMap[prop]);
	        }   
	        return values;
	    },
	    size : function(){
	      var count = 0;
	      for (var prop in this.hashMap) {
	        count++;
	      }
	      return count;
	    }
	};


function addComma(n) {
	 var reg = /(^[+-]?\d+)(\d{3})/;	 
	 n += '';
	 while (reg.test(n)) {
	  n = n.replace(reg, '$1' + ',' + '$2');
	 }
	 return n;
}

function setCookie(c_name,value,exdays)

{

	var exdate=new Date();

	exdate.setDate(exdate.getDate() + exdays);

	var c_value=escape(value) + ((exdays==null) ? "" : "; expires="+exdate.toUTCString());

	document.cookie=c_name + "=" + c_value;

}

function getCookie(c_name)

{

	var i,x,y,ARRcookies=document.cookie.split(";");

	for (i=0;i<ARRcookies.length;i++)

	{

	  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));

	  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);

	  x=x.replace(/^\s+|\s+$/g,"");

	  if (x==c_name)

		{

		return unescape(y);

		}

	  }

}
 
  (function(w,d,n,s,e,o) {
     w[n]=w[n]||function(){(w[n].q=w[n].q||[]).push(arguments)};
     e=d.createElement(s);e.async=1;e.charset='utf-8';e.src='//static.recopick.com/dist/production.min.js';
     o=d.getElementsByTagName(s)[0];o.parentNode.insertBefore(e,o);
   })(window, document, 'recoPick', 'script');
   recoPick('service', 'mlb-korea.com');
 
   recoPick('fetchUID', function (uid) {
	   setCookie('recopick_uid',uid,365);
 
	   });
 
   if('${mbrId}' != ''){
 
	   var mbrBrthdy = '${mbrBrthdy}';
	   var mbrSexSectCd = '${mbrSexSectCd}';
	   if(mbrBrthdy !=''){
		   mbrBrthdy = mbrBrthdy.substr(0,4);
	   }else{
		   mbrBrthdy ='2000';
	   }
	   if(mbrSexSectCd =='MALE'){
		   mbrSexSectCd = 'M';
	   }else{
		   mbrSexSectCd ='F';
	   }
	   recoPick('setMID','${mbrId}');
	   recoPick('setUserInfo',{ birthyear:mbrBrthdy, gender:mbrSexSectCd});
   }else{  
   }
   
   var pageUrl = document.URL;
   if(pageUrl.indexOf('/goods') > -1 ){
	   recoPick('sendLog','view', {id:'${goods.godEx.erpGodNo}', c1:'${dspCtgryScFrDTO.ctgryNoDpthNm1 }', c2:'${dspCtgryScFrDTO.ctgryNoDpthNm2 }', c3:'${dspCtgryScFrDTO.ctgryNoDpthNm3 }'});	   
   }else if (pageUrl.indexOf('/order') > -1 ){
	   //주문 완료 페이지 별도 작업
   }else{
	   recoPick('sendLog','visit');
   }
   
   function recoPickClicklog(obj) {
	var erpNo =    $(obj).data("erpgodno");
	var clicklogLink  = hashMap.get(erpNo);
	var godurl =   $(obj).data("godurl");
	
 	   $.ajax({
			type : "GET",
	        url:clicklogLink,
	        dataType: 'jsonp',
	        success: function(data){
	        },
	        error: function() {
	    
	        }
	    });
	location.href = godurl;
   }
   
   var imageURL = '${imageURL }';
   var hashMap = new HashMap();
   function recoPickView(el,data) {
 
	  var count = 0;
	   $(data).each(function(v,god) {
 
		 if(god.length >0){
			 $(god).each(function(i,s) {
				  hashMap.put(s.id, s.clicklog_link);
		          if('undefined' == s.score || undefined == s.score ){
		     		 $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].score"  value="-1">');        	  
		          }else{
		        	  $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].score"  value='+s.score+'>');  
		          }
		          $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].method"  value='+s.method+'>'); 
		          $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].id"  value='+s.id+'>');  
		          count++;
			 });
  
		 }else{
			   hashMap.put(god.id, god.clicklog_link);
		          if('undefined' == god.score || undefined == god.score ){
			     		 $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].score"  value="-1">');        	  
			          }else{
			        	  $('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].score"  value='+god.score+'>');  
			          }
		  		$('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].method"  value='+god.method+'>');
				$('#recoPickForm').append('<input type="hidden" name="recoPicks['+count+'].id"  value='+god.id+'>');
		 }
		 count++;
		});
	var html = "";
 
	   $.ajax({
			type : "post",
	        url:"/display/recoPick.json",
			beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);
			},
	    	data : $("#recoPickForm").serialize(),
	        success: function(data){
	      		if(typeof data.godList != "undefined" && data.godList.length > 0){
	      			html += "<ul class=\"swiper-wrapper\">";
		        	var count = 0 ;
		        	$(data.godList).each(function(v,god) {
		        		if(count < 10){
		        			html += "<li class=\"swiper-slide\">";                                        
					        html += "	<div class=\"pd_img\"><a href=\"#\" data-erpgodno=\"" + god.god.erpGodNo + "\" data-godurl=\"" + god.godUrl + "\" onclick=\"javascript:recoPickClicklog(this);\"\">";
					        if("SLDOUT" == god.god.godSaleSectCd){
					        	html += "	 <em class=\"sold_out\">SOLD OUT</em>";	
					        }else if("SMTM_SLDOUT" == god.god.godSaleSectCd){
					        	html += "	 <em class=\"soon\">COMING SOON</em>";
					        }
					        html += "	 <img src=\"" + imageURL+god.imgFrontUrl + "/dims/resize/414x414\" alt=\"" + god.god.godNm + "\" onerror=\"errorImgShow(this, '600x600');\"></a></div>";
					        html += "    <div class=\"info\">";
					        html += "    	<div class=\"name\">";
					        <c:if test="${_locale eq 'ko'}">
					        if(null != god.tagNm){
						        html += "    <span style=\"color:" + god.colorTagNm +"\">" + god.tagNm + "</span>";
					        }
					        </c:if>
					        if("I" == god.brndId){
						        html += "   <span>[KIDS]</span>";
					        }
					        html += "    	" + god.god.godNm + "</div>";
					        html += "    	<div class=\"prc\">";
					        if(god.dspGodPrc.rtlPrc > god.dspGodPrc.csmrPrc){
					            html += "    		<s class=\"s\">" + addComma(god.dspGodPrc.rtlPrc) + "<spring:message code="display.main.text1"/></s>";
					            html += "    		<em class=\"p\">" + addComma(god.dspGodPrc.csmrPrc) + "<spring:message code="display.main.text1"/></em>";
					        }else{
					            html += "    		<em class=\"p\">" + addComma(god.dspGodPrc.csmrPrc) + "<spring:message code="display.main.text1"/></em>";
					        }
					        html += "    	</div>";
					        html += " 	</div>";
					        html += " </li>";	
		        		}

		        	    count ++;
		        	});
		        	html += "</ul>";
			        html +=	"<div class=\"btn_list_arrow\">";
				    html += "	<a href=\"javascript:;\" class=\"btn_prev\">이전</a>";
				    html += "	<a href=\"javascript:;\" class=\"btn_next\">다음</a>";
				    html += "</div>";
		       
		        	el.html(html);
		        	
				    var recomPdListBox = new Swiper("#recomPdListBox, #recommendProductGNRL_DLV, #recommendProductRSV, #recommendProductPKUP_DLV", {
					      slidesPerView: 4,
					      slidesPerGroup: 1,
					      spaceBetween: 22,
					      loop:true,
						    navigation: {
						    nextEl: '.btn_next',
						    prevEl: '.btn_prev'
						    },
					      breakpoints: {
					        1280: {
					          slidesPerView: 3,
					          spaceBetween: 20
					        }
					      }
					    });
	      		}else{
	      			el.parents(".recom").hide();
	      		}
	        },
	        error: function() {

	        }
	    });
   } 
 

   (function($){
	   
	   var service_id = '2326';	
	   //1) 함께본상품  
	   $.fn.viewtogether   = function(opts){
			 
		   return this.each(function(){
			   var options = $.extend({}, $.fn.defaults, opts || {}); 
			   var dxmUid = getCookie('recopick_uid');
			   var $el = $(this);  
		 
			   var url = "https://api.recopick.com/v1/recommendations/item/"+service_id+"/"+dxmUid+"/"+encodeURIComponent(options.godNo)+"?type=viewtogether&limit="+options.limit+"&channel="+options.channel;
			   $('#recoPickForm').empty();
			   $.ajax({
			        url:url,
			        dataType: 'jsonp',
			        success: function(data){
			        
			        	var godNos = options.godNo.split(',');
			        	var godArry = new Array();
			        	$(godNos).each(function(v,god) {
				     
			        		godArry.push(data[god]);
			        	 
			        	});
			 
			        	recoPickStatistical($el ,godArry);
	 
			        },
			        error: function() {
	  
			        }
			    });
 
			   
			   }); 
		   }; 
	   //개인화 추천
	   $.fn.personalized  = function(opts){
		   return this.each(function(){
			   var options = $.extend({}, $.fn.defaults, opts || {}); 
			   var dxmUid = getCookie('recopick_uid');
			   var $el = $(this);  
			   var url = "https://api.recopick.com/v1/recommendations/user/"+service_id+"/"+dxmUid+"?type=realtime_api&limit="+options.limit+"&channel="+options.channel;
			 
			   if('${mbrId}' != ''){
				   url = "https://api.recopick.com/v1/recommendations/user/"+service_id+"/"+dxmUid+"?mid="+'${mbrId}'+"&type=realtime_api&limit="+options.limit+"&channel="+options.channel;

			   }
 
			   $('#recoPickForm').empty();
			   
			   $.ajax({
			        url:url,
			        dataType: 'jsonp',
			        success: function(data){
	  					if(data.length > 0){
			    			recoPickStatistical($el ,data);
			        	}else{
	  						$el.parents(".recom").hide();
			        	}
			        },
			        error: function() {
			        	$el.parents(".recom").hide();
			        }
			    });
 
			   
			   }); 
		   }; 
		   //대체 로직 API
	   recoPickStatistical = function($el,opts){
		 
		   var options = $.fn.defaults ; 
		   var dxmUid = getCookie('recopick_uid');
		 
		   var url = options.url+'/'+dxmUid+'?type='+options.type+'&limit='+options.limit+"&channel="+options.channel;

		   $.ajax({
		        url:url,
		        dataType: 'jsonp',		       
		        success: function(data){
 
		        	$(data).each(function(v,god) {
		        		opts.push(god);
		        		
		        	});
		        	recoPickView($el,opts);
		        },
		        error: function() {
 
		        }
		    });

		   }; 
 
		   $.fn.defaults = { 
				   service_id : service_id, 
				   url : 'https://api.recopick.com/v1/recommendations/most/'+service_id,
				   limit : 20 ,
				   godNo :'',
				   type:'view'
				   } 
		   
   })(jQuery);

</script>

<form id="recoPickForm" method="get" >
 
</form>

<!--RecoPick 로그수집 스크립트 -->