<!--  추천 상품 공통 -->
<div class="recomPdListBoxWrap">
  <div id="recomPdListBox" class="recomPdListBox swiper-container">
     <ul class="swiper-wrapper">
         <li class="swiper-slide">                                        
             <div class="pd_img"><a href="javascript:;"><em class="sold_out">SOLD OUT</em><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/cm/no_img_400.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">                                        
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_6.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_7.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">                                        
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">                                        
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_1.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span>[KIDS]</span>뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
         <li class="swiper-slide">
             <div class="pd_img"><a href="javascript:;"><img src="/static/images/_temp/goods_thumb_4.png" alt=""></a></div>
             <div class="info">
                 <div class="name"><span style="color:#ff3800">[EXO엑소 모자]</span> 뉴욕양키스 액센트 커브조절캡</div>
                 <div class="prc"> <s class="s">49,000원</s> <em class="p">59,000원</em></div>
             </div>
         </li>
                                          
                                               
  	 </ul>
     <div class="btn_list_arrow">
         <a href="javascript:;" class="btn_prev">이전</a>
         <a href="javascript:;" class="btn_next">다음</a>
     </div>
  </div>
</div>
<script>
$(document).ready(function(){
    // http://idangero.us/swiper/api/
    var recomPdListBox = new Swiper('#recomPdListBox', {
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
});
</script>