<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>


<article id="lypopGoodsReview" class="layer-popup lypopGoodsReview">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2>상품리뷰</h2>
		<div class="layer-cont ly-box scroll">
		
			<!--  리뷰 별점  -->
			<div class="lyReviewTotal">
				<dl class="reviewTotal">
					<dt>TOTAL 46</dt>
					<dd>
						<div class="starBox star4"><em>(4)</em></div>
						<!--  class로 star0 ~ star5 까지 변경 가능 -->
					</dd>
				</dl>
				<dl class="reviewSize">
					<dt>사이즈</dt>
					<dd>
						<dl class="lineBarBox">
							<dt>작아요</dt>
							<dd><span><em class="bar" style="width:100%"></em></span><em class="percent">100%</em></dd> <!--  style:width 숫자 수정 가능 -->
							<dt>딱 맞아요</dt>
							<dd><span><em class="bar" style="width:95%"></em></span><em class="percent">90%</em></dd> <!--  style:width 숫자 수정 가능 -->
							<dt>커요</dt>
							<dd><span><em class="bar" style="width:10%"></em></span><em class="percent">10%</em></dd> <!--  style:width 숫자 수정 가능 -->	 						
						</dl>
					</dd>
				</dl>
				<dl class="reviewColor">
					<dt>컬러</dt>
					<dd>
						<dl class="lineBarBox">
							<dt>밝아요</dt>
							<dd><span><em class="bar" style="width:80%"></em></span><em class="percent">80%</em></dd> <!--  style:width 숫자 수정 가능 -->
							<dt>화면과 같아요</dt>
							<dd><span><em class="bar" style="width:50%"></em></span><em class="percent">50%</em></dd> <!--  style:width 숫자 수정 가능 -->
							<dt>어두워요</dt>
							<dd><span><em class="bar" style="width:2%"></em></span><em class="percent">2%</em></dd>	 <!--  style:width 숫자 수정 가능 -->						
						</dl>					
					</dd>
				</dl>
			</div>	
			<!-- // 리뷰 별점  -->	
			
			<!--  리뷰 리스트  -->
			<div class="lyReviewDetail">
			
			<div class="d_tab02">
				<ul class="tab-type01">
					<li class="d_tab02_select on"><a href="javascript:;">전체<em>(6)</em></a></li>
					<li class="d_tab02_select"><a href="javascript:;">텍스트리뷰<em>(5)</em></a></li>
					<li class="d_tab02_select"><a href="javascript:;">포토리뷰<em>(1)</em></a></li>
				</ul>
				<!-- 전체 -->
				<div class="d_tab02_cont" style="display:block;">
					<ul class="lyreviewList">
						<!--  Photo Review -->
						<li id="revConAll01" class="rv_toggle">
							<span class="starBox sm star3">3</span>
							<span class="revListName">Dklong**</span>
							<!-- 1910 .revListOpt 구매한 색상, 사이즈 표기 -->
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>
							<p  class="revListTxts">
								<em class="iconPhoto"><img src="/static/images/pd/icon_pd_photo.png" alt=""></em><!-- Photo Review 에만 쓰이는 아이콘 -->
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>
							<a href="#revConAll01" class="btnRevOpen btnRevToggle">열기</a>													
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
								<div class="revPhotos">
									<a href="#lypopGoodsRvPhoto" class="d_layer_open inlayer"><span><img src="/static/images/_temp/goods_thumb_1.png" at=""></span></a>
									<a href="#lypopGoodsRvPhoto" class="d_layer_open inlayer"><span><img src="/static/images/_temp/dp_tag_img_2.png" at=""></span></a>
									<a href="#lypopGoodsRvPhoto" class="d_layer_open inlayer"><span><img src="/static/images/_temp/dp_tag_img_3.png" at=""></span></a>									
								</div>
							</div>	
							<a href="#revConAll01" class="btnRevClose btnRevToggle">닫기</a>					
						</li>	
						<!--  //Photo Review -->					
						<!--  text Review -->
						<li id="revConAll02" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConAll02" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>	
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>					
							<a href="#revConAll02" class="btnRevClose btnRevToggle">닫기</a>
						</li>	
						<!--  //text Review -->				
					</ul>
					
					<!-- 내용 없을 경우 
					<div class="noContArea">
						등록된 리뷰가 없습니다.
					</div>-->
					
					<!--  page List -->
					<%@ include file="../_inc/paging.jsp" %>
					<!-- // page List -->					
				
				</div>
				<!-- //전체 -->
				
				<!-- 텍스트리뷰  -->
				<div class="d_tab02_cont">				
					<ul class="lyreviewList">										
						<!--  text Review -->
						<li id="revConList01" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConList01" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>	
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>					
							<a href="#revConList01" class="btnRevClose btnRevToggle">닫기</a>
						</li>	
						<!--  //text Review -->	
						<li id="revConList02" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConList02" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>	
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>					
							<a href="#revConList02" class="btnRevClose btnRevToggle">닫기</a>
						</li>	
						<li id="revConList03" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConList03" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>						
							<a href="#revConList03" class="btnRevClose btnRevToggle">닫기</a>
						</li>	
						<li id="revConList04" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConList04" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>						
							<a href="#revConList04" class="btnRevClose btnRevToggle">닫기</a>
						</li>	
						<li id="revConList05" class="rv_toggle">
							<span class="starBox sm star5">5</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>	
							<a href="#revConList05" class="btnRevOpen btnRevToggle">열기</a>
							<p class="revListTxts">
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
							</div>						
							<a href="#revConList05" class="btnRevClose btnRevToggle">닫기</a>
						</li>																									
					</ul>
					
					<!--  page List -->
					<%@ include file="../_inc/paging.jsp" %>
					<!-- // page List -->				
				</div>
				<!-- //텍스트리뷰  -->
				
				<!-- 포토리뷰 -->
				<div class="d_tab02_cont">
				
					<ul class="lyreviewList">
						<!--  Photo Review -->
						<li id="revConPhoto01" class="rv_toggle">
							<span class="starBox sm star3">3</span>
							<span class="revListName">Dklong**</span>
							<span class="revListOpt">(컬러:레드 / 사이즈:250)</span>
							<span class="revListDate">2019.02.22</span>
							<p  class="revListTxts">
								<em class="iconPhoto"><img src="/static/images/pd/icon_pd_photo.png" alt=""></em><!-- Photo Review 에만 쓰이는 아이콘 -->
								여자친구 선물로 구입했습니다.전 2015밀포드 베이지 있어서 이쁘단걸 일단 알고있어서 구입했는데 무게가 생각보다 가볍네요.데 95사이즈 주문했구요 잘맞을거 같네요. 좀 아쉬운부분이 20프로 세일할때 왜 못샀는지 모르겠네요ㅠ그 기간에 분명 사이트 접속까지 포인트랑 사은품 주신 가방도 괜찮구요							
							</p>
							<a href="#revConPhoto01" class="btnRevOpen btnRevToggle">열기</a>													
							<div class="revListPhoto">
								<dl>
									<dt>사이즈<dt>
									<dd>딱맞아요</dd>
									<dt>컬러</dt>
									<dd>화면과 같아요</dd>									
								</dl>
								<div class="revPhotos">
									<a href="#lypopGoodsRvPhoto" class="d_layer_open inlayer"><span><img src="/static/images/_temp/dp_tag_img_1.png" at=""></span></a>
									<a href="#lypopGoodsRvPhoto" class="d_layer_open inlayer"><span><img src="/static/images/_temp/dp_tag_img_2.png" at=""></span></a>									
								</div>
							</div>	
							<a href="#revConPhoto01" class="btnRevClose btnRevToggle">닫기</a>					
						</li>
						<!--  //Photo Review -->				
								
					</ul>
					
					<!--  page List -->
					<%@ include file="../_inc/paging.jsp" %>
					<!-- // page List -->	
				
				</div>
				<!-- //포토리뷰 -->
			</div>
			
			</div>
			<!--  //리뷰 리스트  -->
			

			<!--  button -->
			<div class="lyBtnArea">				
				<a href="javascript:;" class="btn fill w160">확인</a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>

<!--  상품 리뷰 사진 클릭시  사진 Layer 팝업 (sample) -->
<article id="lypopGoodsRvPhoto" class="layer-popup lypopGoodsRvPhoto inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:auto; min-width:300px">
		<h2>상품리뷰 사진</h2>
		<div class="layer-cont ly-box scroll"><!--스크롤 필요시 scroll calss 넣어줌 -->
			<div class="rvPhotoView"><img src="" alt=""></div> <!-- 상품이미지 가져오기 -->
		</div>
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close">닫기</button>
		</div>
	</section>
</article>


	
<script>
$(document).ready(function(){
	layerPopup.popupOpenNow('#lypopGoodsReview'); 
	//layerPopup.popupOpenNow('#lypopGoodsRvPhoto');
	
	//button Click 숨겨진 내용보기
	$( ".btnRevToggle" ).on('click', function() {
		var fname=$(this).parent();	
			if(fname.hasClass('on')){
				fname.removeClass('on');
			}else{
				fname.addClass('on');
			}
	});
	$(document).on('click', '.revListTxts', function(){
		$(this).parent().find('.btnRevToggle')[0].click();
	})
	
	//이미지경로 레이어팝업 띄우기
	$(".revPhotos a").click(function() {		
		$(this).find('img').each(function(){
			var imgUrl = $(this).attr('src');
			var imgView=$('#lypopGoodsRvPhoto .rvPhotoView img');		
			imgView.attr('src',imgUrl);			
		});
	});		
	
});
</script>
</body>
</html>