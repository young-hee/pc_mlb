	function jsShareSns(type, titleParam) {
	    var sns_url = document.URL
	    if(sns_url.indexOf("language=") === -1) {
	    	sns_url = sns_url.indexOf("?") > 0 ? sns_url + "&language=" + BASE.locale : sns_url + "?language=" + BASE.locale;
	    }
 	    var sns_media = $('[name=og_image]').attr("content");
	    var sns_title = "title";
	    var title = encodeURIComponent($("meta[name='og_title']").attr("content"));
    	var pic = $("meta[name='og_image']").attr("content");
    	var desc = encodeURIComponent($("meta[name='og_desc']").attr("content"));
	    if(titleParam != undefined && titleParam != ''){
			sns_title = titleParam;
			sns_title = sns_title.replace('`','\'');
		} else {
			sns_title = sns_title.toString().replace('`','\'');
		}
 		sns_title = encodeURIComponent(sns_title);		
	    var cnrsSnsCd ='';
	    if (type == 'facebook') {
	    	cnrsSnsCd = 'FACEBOOK';
	    	sns_url = encodeURIComponent(sns_url);	
	    	window.open("http://www.facebook.com/sharer.php?u=" + sns_url, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    }  
	    else if(type == 'kakaostory'){
	    	cnrsSnsCd = 'KKOST';
            Kakao.Story.share({
              url: sns_url
            });
	    }
	    else if(type == 'kakaotak'){
	    	cnrsSnsCd = 'KKOTK';
	    }
	    else if(type == 'twitter'){
	    	cnrsSnsCd = 'TWITTER';
	    	sns_url = encodeURIComponent(sns_url);	
	    	window.open("http://www.twitter.com/intent/tweet?text=" + titleParam + "&url=" + sns_url, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    }
	    else if(type == 'naverline'){
	    	cnrsSnsCd = 'NAVERLINE';
	    	sns_url = encodeURIComponent(sns_url);	
	    	window.open("https://social-plugins.line.me/lineit/share?url=" + sns_url, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    }
	    else if(type == 'weibo'){
	    	cnrsSnsCd = "WB";
	    	sns_url = encodeURIComponent(sns_url);
	    	
	    	if("" != pic){
	    		window.open("http://service.weibo.com/share/share.php?url=" + sns_url + "&title=" + title + "&pic=" + pic, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    	}else{
	    		window.open("http://service.weibo.com/share/share.php?url=" + sns_url + "&title=" + title, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    	}
	    }
	    else if(type == 'qq'){
	    	cnrsSnsCd = "QQ";
	    	sns_url = encodeURIComponent(sns_url);
	    	
	    	if("" != pic){
	    		if("" != desc){
	    			window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title + "&pics=" + pic + "&summary=" + desc, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    		}else{
	    			window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title + "&pics=" + pic, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    		}
	    	}else{
	    		window.open("https://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=" + sns_url + "&title=" + title, cnrsSnsCd, "height=500, width=620, scrollbars=yes");
	    	}
	    }

		$.ajax({
            type : "GET",
            url : '/event/snscnrshist.json',
            async : true,
            data : $.param({'${_csrf.parameterName}' : '${_csrf.token}',
            	            'cnrsSnsCd' :cnrsSnsCd,
            	            'cnrsUrl' :sns_url 
            }),
			success : function(data) {
			},
			error: function(xhr) {
		    }
		});	
	}
	 var naverBlog = function(){
	    	this.postWithScrap = function(message, link){
	    	    var url = encodeURI(encodeURIComponent(link));
	    	    var title = encodeURI(message);
	    	    var shareURL = "http://share.naver.com/web/shareView.nhn?url=" + url + "&seviceCode=blog&title=" + title;
	    	    window.open(shareURL, "naverBlog", "height=500, width=620, scrollbars=yes");
 
	        };    
	 }
	 var facebook = function(fbAppId){
	 
	        //페이스북 초기화     
	    	window.fbAsyncInit = function() {
	    		  FB.init({
	    		    appId      : fbAppId,
	    		    cookie     : true,  // enable cookies to allow the server to access 
	    		                        // the session
	    		    xfbml      : true,  // parse social plugins on this page
	    		    version    : 'v3.0' // use version 2.2
	    		  });
	    		
	    		  FB.getLoginStatus(function(response) {
	    		    //statusChangeCallback(response);
	    		  });

	    	};
	    	  
	    	// Load the SDK asynchronously
	    	(function(d, s, id) {
	    	    var js, fjs = d.getElementsByTagName(s)[0];
	    	    if (d.getElementById(id)) return;
	    	    js = d.createElement(s); js.id = id;
	    	    js.src = "//connect.facebook.net/en_US/sdk.js";
	    	    fjs.parentNode.insertBefore(js, fjs);
	    	}(document, 'script', 'facebook-jssdk'));
	    	
	    	//글쓰기
	    	this.post = function(message, link){
	            FB.api('/me/feed','post', {
	            	message:message+'\n'+link, 
	            	app_link : link});
	        };
	    	//스크랩정보와 함께 글쓰기
	    	this.postWithScrap = function(message, link){
	            FB.api('/me/feed','post', {
	            	message:message+'\n'+link, 
	            	app_link : link,
	            	link : link});
	        };      
	        //프로필 이미지 조회
	    	this.getProfileImage = function(callback){
	    		FB.api('/me/picture',function(response){
	    			callback(response.data.url);
	    		});
	    	};
	    	
	        //내정보 조회 me api 호출
	    	this.getName = function(callback){
	    	    FB.api('/me', function(response) {
	    	    	callback(response.name);
	    	    });
	    	}
	    	// 로그인 : 성공 후 콜백 호출
	    	this.login = function(callback){
	            FB.login(function(){
	            	callback();
	            },{scope:'publish_actions'});
	    	}
	    }
	  

		/************************************************************************************************************/
		/** 카카오 스토리 */
		/************************************************************************************************************/
		

	    var kakaostory = function(fbAppId){
	  
	    	//카카오 스토리 초기화
	    	Kakao.init(fbAppId);
	    	
	        this.post = function (message, link){
	  		  
	            Kakao.API.request({
	    	        url: '/v1/api/story/post/note',
	    	        data : {content:message},
	    	        success: function(res) {
	    	       //   alert(JSON.stringify(res));
	    	        },
	    	        fail: function(error) {
	    	          //  alert(JSON.stringify(error))
	    	        }
	            });
	        };	  
	        
	        this.postWithScrap = function (message, link){
	    		  
	            Kakao.API.request( {
	            	  url : '/v1/api/story/linkinfo',
	            	  data : {
	            	    url : link
	            	  }
	            ,
    	        success: function(res) {
    	        //  alert(JSON.stringify(res));
    	        },
    	        fail: function(error) {
    	         //   alert(JSON.stringify(error))
    	        }
	            	}).then(function(res) {
	            	  return Kakao.API.request( {
	            	    url : '/v1/api/story/post/link',
	            	    data : {
	            	        content:message,
	            	        link_info : res
	            	    },
		    	        success: function(res) {
		    	       
			    	     //     alert(JSON.stringify(res));
			    	        },
			    	        fail: function(error) {
			    	        	 
			    	        //  alert(JSON.stringify(error))
			    	        }
	            	  });
	            	}, function (err) {
	            	  // handle error, please.
	            	});            
	        };	 
	        
	    	this.getProfile =  function(callback){
	    		
	    	    var profile;
	    		
	            Kakao.API.request({
	    		    url: '/v1/user/me',
	    		    success: function(res) {
	    		        //profile = res.properties;
	    		        callback(res.properties);
	    		    },
	    		    fail: function(error) {
	    		        alert(JSON.stringify(error))
	    		    }
	            });
	            
	            return profile;
	        };	  
	    	  
	        this.login = function(callback){
	            Kakao.Auth.login({
	                success: function(authObj) {
	    		        callback();
	    		    },
	    		    fail: function(err) {
	    		        //alert(JSON.stringify(err))
	    		    }
	    		 });
	        }    	
	    }

		  