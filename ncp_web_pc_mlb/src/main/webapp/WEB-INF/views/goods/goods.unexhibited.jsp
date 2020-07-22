<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<ncp:prop key="ncp.image.url" var="imageURL"/>
<link rel="stylesheet" href="${_resourceURL}static/css/pd.css">

	<script>
	$(document).ready(function() {
        var option = {godNo:_erpNo, channel : 'pdp'};
        $('#recomPdListBox').viewtogether(option);
    });
	</script>
	
	<!-- 컨텐츠 시작 -->
	<div class="contain pd goods" id="contain">
		<div class="container">

			<main class="contents" id="contents">
				
				<div class="gdsWrap">
					
					<!-- 판매중인상품이 아님 -->
					<section class="sect stopSale">
						<div class="in">
							<div class="msg">
								<div class="dt"><spring:message code="goods.unexhibited.text1"/></div>
								<div class="dd"><spring:message code="goods.unexhibited.text2"/></div>
							</div>
							<div class="bts">
								<a href="/main/mall/view" class="btn fill lg btnGoMain"><spring:message code="goods.unexhibited.text3"/></a>
							</div>
						</div>
					</section>
					<!-- 추천상품 -->
					<section class="mds-section recom">
						<div class="hdt"><span class="tit"><spring:message code="goods.unexhibited.text4"/></span></div>
						<div class="recomPdListBoxWrap">
					  		<div id="recomPdListBox" class="recomPdListBox swiper-container">
					  		</div>
					  	</div>
					</section>

				</div>
				

			</main>
			
		</div>
	</div>
	<!--// 컨텐츠 끝 -->