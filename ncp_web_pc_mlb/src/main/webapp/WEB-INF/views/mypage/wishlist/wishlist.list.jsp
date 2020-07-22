<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<form id ="srchForm" action ="<c:url value ='/mypage/wishlist/list' />" method="post" >
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="wishlstSn" id="wishlstSn" value=""/>
	<input type="hidden" name="godTurn"   id="godTurn"   value=""/>
	<input type="hidden" name="pageNo"    id="pageNo"    value=""/>
	<input type="hidden" name="totalCnt"  id="totalCnt"  value=""/>
	<input type="hidden" name="totPage"   id="totPage"   value=""/>
	<input type="hidden" name="deleteAllYn"   id="deleteAllYn"   value=""/>
	<input type="hidden" id="selectedItemNo"/>
	<!-- 컨텐츠 시작 -->
	<div class="contain my lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01">위시리스트</h2>

			<%@ include file="../include/lnb.jspf" %>

			<main class="contents wishList-wrap" id="contents">

				<div class="location-contents">
					<p class="location">
						<!-- <span>Home</span><span>마이페이지</span><span>활동정보</span><strong title="현재 위치">위시리스트</strong> -->
						<span>Home</span>
						<span>마이페이지</span>
						<span>활동정보</span>
						<strong title="현재 위치">위시리스트</strong>

					</p>
				</div>

				<div class="tbst-div">
					<div class="mid fl">
						<span>전체</span> (<span class="text-color01"><em class="num" id="wishListCnt"></em></span>건)
					</div>
					<div class="mid fr">
						<a href="#" class="btn fill sm" onclick="javascript:deleteAllWishList(); return false;"><span>전체삭제</span></a>
					</div>
				</div>

				<hr class="hr-666" />

				<div id="includeWishList"></div>

				<hr class="hr-ddd" />

				<div class="mdGoodsProduct-List">
					<p>${_user.mbr.mbrNm}고객님을 위한 추천상품입니다.</p>
                    <%@ include file="/WEB-INF/views/mypage/include/inc_recommend.jsp" %>
				</div>

			</main>

		</div>
	</div>
	<!--// 컨텐츠 끝 -->
</form>

<script type="text/javascript">

	$(document).ready(function(){
		var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};
		$("#includeWishList").load("<c:url value='/mypage/wishlist/include/list.ajax'/>", strParams);

		/* 장바구니담기 옵션팝업*/
		$(document).on('click',"[href='#layerPopupOption']",function(){

			var godNo =  $(this).data('godno');
			getItemNo(godNo);

			getChangeOption({godNo:godNo,caller:"wish"});
			//$("#layerPopupOption").css("display","flex");
		});
	});


	function goWishlistList(pageNo){

		if(pageNo == ""){
	        pageNo = 1;
	    }

	    strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'pageNo' : pageNo};

	    $.ajax({
            type : "POST",
            url     : '/mypage/wishlist/include/list.ajax',
            data : strParams,
            success : function(data) {
                $("#includeWishList").html(data);
            },
            error : function(jqXHR, textStatus, error) {
                if(jqXHR.status == "403") {
                    alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                    location.reload();
                } else {
                    alert("시스템 오류 입니다.");
                }
            }
        });
	}

	/* Wishlist 전체 삭제 */
	function deleteAllWishList(){
		var totalCnt = $("#totalCnt").val();

		if(totalCnt > 0){
			if (confirm('전체 삭제하시겠습니까?') == true) {
				// 삭제구분 전체,단건
				$("#deleteAllYn").val("Y");

				deleteWishListProc();
			} else {
				return false;
			}
		}
	}

	function deleteWishListProc() {
		var pageNo   = $("#pageNo").val() ;
		var totalCnt = $("#totalCnt").val() ;
		var totPage  = $("#totPage").val() ;
		var modYn    = (totalCnt-1) % 8 ;

		var deleteAllYn = $("#deleteAllYn").val();

		if(modYn==0 && pageNo==totPage){
			pageNo = pageNo - 1;
		}

		var parmUrl="";
		if(deleteAllYn == "Y"){
			parmUrl = "/mypage/wishlist/deleteAllMyWishList";
			pageNo = 1;
		}else{
			parmUrl = "/mypage/wishlist/deleteGodWishList.json";
		}

		if(pageNo < 1) {
			pageNo = 1;
		}

		$.ajax({
			type  : "POST",
			async : false,
			url   : parmUrl,
			data  : $("#srchForm").serialize(),
			success : function(data) {
				alert('삭제되었습니다.');
				goWishlistList(pageNo);
			},
			error: function(pa_data, status, err) {
	            alert("error forward : "+err+" ,status="+status);
	        }
		});
	}

	/* 장바구니담기 옵션팝업 - 담기*/
	function setChangeOption() {

		if(!checkSizeSelected()){
			return false;
		}else{
			$('.d_layer_close').click();
		}

	   var godTpCd = $('#changeOptionForm').find('#optionGodTpCd').val();

	   var obj = new Object();
	   var god = new Object();

	   //obj.sourceGodTurn = cart.cartGodTurn;
	   obj.godTpCd = 'GNRL_DLV';

	   if('SET_GOD' == godTpCd){
		   var cpstGodList = [];
		   god.godNo = $('#changeOptionForm').find('#optionGodNo').val();
		   god.itmQty = $('#qty').val();
		   god.pckageGodYn = 'Y';
		   god.itmNo = $('#selectedItemNo').val();
		   obj.god = god;

		   $('.cpstGodQty').each(function(index){
			   var cpstGod = {};
			   cpstGod.godNo = $('#changeOptionForm').find('#cpstGodNo'+index).val();
			   cpstGod.itmNo = $('#changeOptionForm').find('#itmNo'+index).val();
			   cpstGod.pckageGodTpCd = 'SET_GOD';
			   cpstGod.sortSeq = $('#changeOptionForm').find('#sortSeq'+index).val();
			   cpstGod.cpstGodQty = $('#qty').val();

			   cpstGodList.push(cpstGod);
		   });
		   obj.cpstGodList = cpstGodList;

	   }else{
		   god.pckageGodYn = 'N';
		   god.godNo = 	$('#changeOptionForm').find('#optionGodNo').val();
		   god.itmNo = 	$('#changeOptionForm').find('#itmNo0').val();
		   god.itmQty =	$('#qty').val();
		   obj.god = god;
	   }

	   // 배송구분 : 일반
	   obj.god.dlvSectCd = "GNRL_DLV";

	   // 장바구니담기
	   changeCart(obj);

	}

	function changeCart(obj) {

		$.ajax({
			type:"post"
			,url:'/cart/goods/addJSON.json'
			,dataType: "json"
			,contentType : 'application/json'
			,async : true
			,data : JSON.stringify(obj)
			,beforeSend : function(request) {
				var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
				var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
				request.setRequestHeader(csrfName, csrfToken);

			}
			,success : function(data) {
				if(data.rtncode==1){

					//layerPopup.popupOpenNow('#layerPopupAddBasketComplete');
					if (confirm('장바구니에 추가되었습니다.\n장바구니로 이동하시겠습니까?')) {
						window.location.href = '/cart/goods/list';
					}

				}else{
					alert(data.errMsg);
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

	function getItemNo(godNo) {
		var itmNo = "";
		$.ajax({
			url:"/mypage/wishlist/getItemNo?godNo=" + godNo
			, type:"get"
			, dataType:"text"
			, success:function(data) {
				var itemNo = data.split(" ");
				$('#selectedItemNo').val(itemNo[0]);
			}
			, error:function(jqXHR) {
				alert(jqXHR.responseText)
			}
		});
	}

</script>
<%@ include file="/WEB-INF/views/display/include/goods.option.jsp"%>