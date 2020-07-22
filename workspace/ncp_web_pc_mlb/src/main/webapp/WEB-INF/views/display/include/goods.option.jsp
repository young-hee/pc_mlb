<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<script>
function getChangeGod(obj) {
	var godno = $(obj).data("godno");
	if($("#changeOptionForm").data('caller') == 'clm'){
		getChangeOption({godNo:godno,shopId:$("#changeOptionForm").data("shopid")});
	}else{
		getChangeOption( {godNo:godno});
	}

}

function getChangeOption(obj) {

	var defaults = {godNo:'',itmQty:'',shopId:'',caller:''}
	var options = $.extend({},defaults, obj || {});

	if(options.caller =='clm'){
		$("#changeOptionForm").data("caller", "clm");
		$("#changeOptionForm").data("itmqty", options.itmQty);
		$("#changeOptionForm").data("shopid", options.shopId);
	} else if (options.caller == 'wish') {
		$('.btnWrapBox > a.fill').html('장바구니담기');
	}


	$.ajax({
		type : "post",
        url:"/goods/content/getChangeOption.json",
		beforeSend : function(request) {
			var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
			var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
			request.setRequestHeader(csrfName, csrfToken);
		},
		data :options,
        success: function(rt){
        	$('#changeOptionForm').empty();
     	var html = "";
     	html += '<div class="options-Goodsimg list-swiper-dotnav" id="productImg01">';
     	html += '<div class="swiper-container">';

     	html += '<ul class="swiper-wrapper">';

     	if('undefined' == rt.data.iamge || undefined == rt.data.iamge){
         	html += '<li class="swiper-slide"><div class="item-img"><img src="" alt="" onerror="errorImgShow(this, 100);"></div></li>';
     	}else{
         	html += '<li class="swiper-slide"><div class="item-img"><img src="'+BASE.imageUrl+rt.data.iamge.imgUrl+'/dims/resize/146x146" alt="'+rt.data.iamge.godNo+'" onerror="errorImgShow(this, 100);"></div></li>';
     	}

     	$(rt.data.imageList).each(function(v,img) {

     		html += '<li class="swiper-slide"><div class="item-img"><img src="'+BASE.imageUrl+img.imgUrl+'/dims/resize/146x146" alt="'+img.imgDscr+'" onerror="errorImgShow(this, 100);"></div></li>';
     	});

     	html += '</ul>';
     	html += '</div>';
     	html += '	<button type="button" class="item-list-prev">이전</button>';
     	html += '	<button type="button" class="item-list-next">다음</button>';
     	html += '	<div class="swiper-pagination"></div>';

     	html += '</div>';

     	html += '<div class="options-GoodsList">';
     	html += '<ul class="optSelBoxList">';
     	html += '<li>'+rt.data.god.godNm+'</li>';

     	$('#changeOptionForm').append('<input type="hidden"  id="optionGodTpCd" value="'+rt.data.god.godTpCd+'"/>');
        $('#changeOptionForm').append('<input type="hidden"  id="optionGodNo" value="'+rt.data.god.godNo+'"/>');
        $('#changeOptionForm').append('<input type="hidden"  id="colorCd" value="'+rt.data.god.colorCd+'"/>');

     	if(rt.data.god.godTpCd =='SET_GOD'){
     		$('#layerPopupOption').removeClass('lypopPdMod');
     		$('#layerPopupOption').addClass('lypopPdModSet');

     		$(rt.data.cpstList).each(function(v,god) {

 	    		html += '<li> ';
 	    		html += '<em>'+god.cpstGodNm+'</em>';
 	    		html += '<div class="SelBox">';
 	    		html += '<div class="select-style01 d_select">';
 	    		html += '	<button type="button" class="d_select_sel" style="width:100%;"><span><spring:message code="goods.js.common.lbl.select" /></span></button> ';
 	    		html += '	<ul>';
 	    		html += '	<li><a href="#"><spring:message code="goods.js.common.lbl.select" /></a></li>';

 	    		$(rt.data.itemList).each(function(i,itm) {
 	    			  if(god.cpstGodNo == itm.godNo){
 	    				  html += '	<li><a href="#"  data-index="'+v+'" data-itmno="'+itm.itmNo+'" data-itmnm="'+itm.itmNm+'" data-godtpcd="'+rt.data.god.godTpCd+'" data-cpstgodqty="'+itm.totUsefulInvQty+'" onclick="javascript:sizeChange(this);" >'+itm.itmNm+'</a></li>';

 	    			  }

 	    		});

 	    		$('#changeOptionForm').append('<input type="hidden"  id="colorCd'+v+'" value="'+god.colorCd+'"/>');
                $('#changeOptionForm').append('<input type="hidden"  id="cpstGodNm'+v+'" value="'+god.cpstGodNm+'">');
                $('#changeOptionForm').append('<input type="hidden"  id="cpstGodNo'+v+'" value="'+god.cpstGodNo+'">');
                $('#changeOptionForm').append('<input type="hidden" class="cpstGodQty" id="cpstGodQty'+v+'" value="'+1+'">');
                $('#changeOptionForm').append('<input type="hidden"  id="sortSeq'+v+'" value="'+god.sortSeq+'">');
                $('#changeOptionForm').append('<input type="hidden"  id="itmNo'+v+'" value="">');
                $('#changeOptionForm').append('<input type="hidden"  id="itmNm'+v+'" value="">');

				html += '	</ul>';
				html += '</div>';
				html += '</div>';
				html += '</li>';
 	    	});
     	} else {
     		$('#layerPopupOption').removeClass('lypopPdModSet');
     		$('#layerPopupOption').addClass('lypopPdMod');

     		$('#changeOptionForm').append('<input type="hidden"  id="itmNo0" value=""/>');
            $('#changeOptionForm').append('<input type="hidden"  id="itmNm0" value="">');

            html += ' <li>';
 	    	html += '	<em><spring:message code="display.main.text6" /></em>';
 	    	html += '	<div class="option-Goodscolor">';
 	    	$(rt.data.designColorList).each(function(v,dg) {

 	    		html += '<a href="#" data-godno="'+dg.godNo+'" onclick="javascript:getChangeGod(this);"  class="d_radio_select"><img src="'+BASE.imageUrl+dg.imgUrl+'/dims/resize/40x40" alt="'+dg.godNm+'" onerror="errorImgShow(this, 100);"></a>';

 	    	});

 	    	html += '	</div>';
 	    	html += '</li>';

    			html += '<li>';
    			html += '<em><spring:message code="display.review.text10" /></em>';
    			html += '<div class="option-Goodssize">';
 	    	$(rt.data.itemList).each(function(v,itm) {

 	    		if(options.caller != 'clm') {
     	    		if(itm.itmStatCd == 'SALE_PROGRS'){
         	    		html += '<button type="button" class="btn gray xs d_radio_select" data-index="'+0+'" data-itmno="'+itm.itmNo+'" data-itmnm="'+itm.itmNm+'" data-godtpcd="'+rt.data.god.godTpCd+'" onclick="javascript:sizeChange(this);"  value="'+itm.itmNo+'"><span>'+itm.itmNm+'</span></button>';

     	    		}else{
         	    		html += '<button type="button" class="btn gray xs d_radio_select" disabled="" ><span>'+itm.itmNm+'</span></button>';

     	    		}
 	    		}else{
 	    			if(itm.itmStatCd == 'SALE_PROGRS' && itm.totUsefulInvQty > 0){
         	    		html += '<button type="button" class="btn gray xs d_radio_select" data-index="'+0+'" data-itmno="'+itm.itmNo+'" data-itmnm="'+itm.itmNm+'" data-godtpcd="'+rt.data.god.godTpCd+'" onclick="javascript:sizeChange(this);"  value="'+itm.itmNo+'"><span>'+itm.itmNm+'</span></button>';

     	    		}else{
         	    		html += '<button type="button" class="btn gray xs d_radio_select" disabled="" ><span>'+itm.itmNm+'</span></button>';

     	    		}
 	    		}

	    		});
 	    	html += '</div>';
 	    	html += '</li>';
     	}

    	html += '<li>';
    	html += '<em><spring:message code="goods.option.lbl.quantity" /></em>';
    	html += '<div class="option-Goodscount">';
        html += '	<button type="button"  data-godtpcd="'+rt.data.god.godTpCd+'" data-flag="minus" data-min="'+rt.data.god.minOrdQty+'"  data-max="'+rt.data.god.maxOrdQty+'" onclick="javascript:changeQty(this);"   class="pq-minus">빼기</button>';
        if(options.itmQty > 1){
    		html += '		<input type="number"  id="qty" title="수량" value="'+options.itmQty+'" data-min-ord-qty="1" data-max-ord-qty="999">';
    	}else{
    	 	html += '		<input type="number"  id="qty" title="수량" value="1" data-min-ord-qty="1" data-max-ord-qty="999">';
    	}
        html += '	<button type="button"  data-godtpcd="'+rt.data.god.godTpCd+'" data-flag="add" data-min="'+rt.data.god.minOrdQty+'"  data-max="'+rt.data.god.maxOrdQty+'" onclick="javascript:changeQty(this);" class="pq-plus">추가</button>';



    	html += '	</div>';
    	html += '</li>';
    	html += '</ul>';


    	html += '</div>';
     	html += '</div> ';

     	$('.options-GoodsBoxPop').html(html);

     	$('#productImg01 .swiper-container').imagesLoaded().progress( function() {
				var productImg01 = new Swiper('#productImg01 .swiper-container', {
					slidesPerView: 1,
					observer: true,
					observeParents: true,
					pagination: {
						el: '#productImg01 .swiper-pagination',
						clickable: true
					},
					navigation: {
						nextEl: '#productImg01 .item-list-next',
						prevEl: '#productImg01 .item-list-prev'
					}
				});
     	});
        radioSelect.init();
     },
     error: function() {

     }
 });

}

function sizeChange(obj){

	var godTpCd = $(obj).data("godtpcd");
	var index = $(obj).data("index");
	var itmno = $(obj).data("itmno");
	var itmnm = $(obj).data("itmnm");
	$('#changeOptionForm').find("#itmNo"+index).val(itmno);
	$('#changeOptionForm').find("#itmNm"+index).val(itmnm);
	if($("#changeOptionForm").data('caller') == 'clm'){
		var qty = parseInt($("#changeOptionForm").data("itmqty"));
		$("#qty").val(qty);
	}else{
		$("#qty").val("1");
	}

	if(godTpCd =='SET_GOD'){
		var cpstGodQty = $(obj).data("cpstgodqty");
		$('#changeOptionForm').find("#cpstGodQty"+index).val(cpstGodQty);
	}
}

function changeQty(obj){

	var godTpCd = $(obj).data("godtpcd");
	var flag = $(obj).data("flag");
	var min = $(obj).data("min");
	var max = $(obj).data("max");

	if(!checkSizeSelected(godTpCd)){
		return false;
	}

	if(max == 0){
	   max = 999;
	}

	var _itmMaxQty = 999;

	if($("#changeOptionForm").data('caller') == 'clm'){
		_itmMaxQty = parseInt($("#changeOptionForm").data("itmqty"));
	}

	if(max  >  _itmMaxQty){
	   max = _itmMaxQty;
	}

	if(godTpCd == "SET_GOD"){
		max = getSedGodMinQty();
		if($("#additItmNo").val() && $("#additItmNo").val() != ""){
			var len = $("[name^=itmNo]").length;
			for(var i=0; i < len; i++){
				if($("#additItmNo").val() == $("#itmNo"+i).val()){
					max = getSedGodMinQty()-parseInt($("#additQty").val());
					break;
				}
			}
		}
	}

	var qty = $("#qty").val();
	if(qty == ""){
		$("#qty").val(1);
		return false;
	}
	qty = parseInt(qty);

	var tobeQty = 0;

	if(flag == "add"){
		tobeQty = parseInt(qty)+1;
	}else if(flag == "minus"){
		tobeQty = parseInt(qty)-1;
	if(tobeQty < 1){
	   //주문 가능한 최소 수량입니다.
	   alert('<spring:message code="goods.js.goods.msg.alert1" />');

	   tobeQty = qty;
	   }
	}else{
	   if(qty < 1){
		   //주문 가능한 최소 수량입니다.
		      alert('<spring:message code="goods.js.goods.msg.alert1" />');
		   tobeQty = 0;
	   }else{
		   tobeQty = qty;
	   }
	}

	if(min > 0 && tobeQty < min){
	   //주문 가능한 최소수량을 확인해주세요. 최소+N
	    alert('<spring:message code="goods.js.goods.msg.alert2" />' + " " + min);

	   tobeQty = min;
	}else if(max > 0 && tobeQty > max){
	   //주문 가능한 최대수량을 확인해주세요. 최대+N
	       alert('<spring:message code="goods.js.goods.msg.alert3" />' + " " + max);

	   tobeQty = max;
	}

	$("#qty").val(tobeQty);

}
function checkSizeSelected(godTpCd){
	var noselect = true;
	var cpstGodNm;

	$("[id^=itmNo]").each(function(index){
		if ($(this).val() == ""){
		    $("#qty").val("1");
			if(godTpCd == "SET_GOD"){
				cpstGodNm = $("#cpstGodNm"+index).val();
				noselect = false;
				return false;
			}else{
				noselect = false;
			}
		}
	});

	if(!noselect){
		if(godTpCd == 'SET_GOD'){

			alert('<spring:message code="goods.js.goods.msg.alert7" arguments="'+cpstGodNm+'"/>');

		}else{
			//옵션을 선택해주세요
		   alert('<spring:message code="goods.js.goods.msg.alert8" />');

		}
		return false;
	}
	return true;
}
function getSedGodMinQty(){
	var len = $(".cpstGodQty").length;
	var setGodMinQty = 1;

	if(len > 0){
	   setGodMinQty = parseInt($("#cpstGodQty0").val());
	}
	for(var i=1; i < len; i++){
	   var itmCpstGodQty = parseInt($("#cpstGodQty"+i).val());
	   if(setGodMinQty > itmCpstGodQty){
		   setGodMinQty = itmCpstGodQty;
	   }
	}
	return setGodMinQty;
}
</script>
		<form id="changeOptionForm" > </form>

		<!-- layerpopup - 옵션 변경 -->
		<article id="layerPopupOption" class="layer-popup optionModi-pop lypopPdMod"><!-- 2018-11-23 -->
			<section class="layer-popup-cont" tabindex="0">
				<h2><spring:message code="display.main.text19" /></h2><!-- 2018-11-23 -->
				<div class="layer-cont"><!-- 2018-11-23 -->
					<div class="options-GoodsBoxPop">

					</div>
				</div>
				<div class="btnWrapBox">
					<a href="#" class="btn d_layer_close"><spring:message code="display.main.text11" /></a>
					<a href="#"  onclick="javascript:setChangeOption();" class="btn fill"><spring:message code="display.main.text20" /></a>
				</div>
				<div class="layer-popup-close">
					<button type="button" class="d_layer_close"><spring:message code="display.main.text11" /></button>
				</div>
			</section>
		</article>
		<!-- //layerpopup - 옵션 변경 -->