<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<article id="lypopCardPayCf" class="layer-popup lypopCardinfo lypopCardPayCf inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info2" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<p class="fw_bold"><spring:message code="order.payment.card.info1.1" /></p>
				<p><spring:message code="order.payment.card.info1.2" /></p>
				
				 <table class="board-list">
	                <colgroup>                     
	                  <col style="width:260px">
	                  <col style="width:130px">
	                  <col style="width:">
	                </colgroup>
	                <thead>
	           	   		<tr>
	           	   			<th></th>
	           	   			<th><spring:message code="order.payment.card.info1.3" /></th>
	           	   			<th><spring:message code="order.payment.card.info1.4" /></th>
	       	   			</tr>
	                </thead>
	                <tbody>
	                	<tr>
	                		<th><spring:message code="order.payment.card.info1.5" /></th>
	                		<td><spring:message code="order.payment.card.info1.6" /></td>
	                		<td><spring:message code="order.payment.card.info1.7" /></td>
	               		</tr>
	                	<tr>
	                		<th><spring:message code="order.payment.card.info1.8" /></th>
	                		<td><spring:message code="order.payment.card.info1.9" /></td>
	                		<td><spring:message code="order.payment.card.info1.10" /></td>
	               		</tr>               		
	                </tbody>
	             </table>
	             
	             <p class="txtSize12"><spring:message code="order.payment.card.info1.11" /></p>  
			</div>	
			
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info1.12" /></h3>
				<p><spring:message code="order.payment.card.info1.13" /></p>
				<ul class="issueBox">
					<li><em>01.</em><spring:message code="order.payment.card.info1.14" /></li>
					<li><em>02.</em><spring:message code="order.payment.card.info1.15" /></li>
					<li><em>03.</em><spring:message code="order.payment.card.info1.16" /></li>
					<li><em>04.</em><spring:message code="order.payment.card.info1.17" /></li>
				</ul>
				<ul class="text-list02">
					<li class="fc_gray"><spring:message code="order.payment.card.info1.18" /></li>
					<li><spring:message code="order.payment.card.info1.19" /></li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<article id="lypopCardPayClick" class="layer-popup lypopCardinfo lypopCardPayClick inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info3" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info2.1" /></h3>
				<p><spring:message code="order.payment.card.info2.2" /></p>
				
				<div class="txtsBox">					
					<h4><spring:message code="order.payment.card.info2.3" /></h4>
					<table class="board-list list_left">
		                <colgroup>                     
		                  <col style="width:110px">
		                  <col style="width:300px">
		                  <col style="width:110px">
		                  <col style="width:">
		                </colgroup>
		                
		                <tbody>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.4" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_samsung.png" alt="삼성카드"></td>
		                		<td><spring:message code="order.payment.card.info2.5" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_citibank.png" alt="한미카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.6" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_keb.png" alt="외환카드"></td>
		                		<td><spring:message code="order.payment.card.info2.7" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_suhyupbank.png" alt="수협카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.8" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_lotte.png" alt="롯데카드"></td>
		                		<td><spring:message code="order.payment.card.info2.9" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_jbbank.png" alt="전북카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.10" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_hyunda.png" alt="현대카드"></td>
		                		<td><spring:message code="order.payment.card.info2.11" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_kjbank.png" alt="광주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.12" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan.png" alt="신한카드"></td>
		                		<td><spring:message code="order.payment.card.info2.13" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_jejubank.png" alt="제주카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.14" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_citibank.png" alt="시티카드"></td>
		                		<td><spring:message code="order.payment.card.info2.15" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan.png" alt="조흥카드"></td>	                		
		               		</tr>
		                	<tr>
		                		<td><spring:message code="order.payment.card.info2.16" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinsegae.png" alt="신세계한미카드"></td>
		                		<td><spring:message code="order.payment.card.info2.17" /></td>
		                		<td><img src="${_resourceURL}static/images/od/card_shinhan_lg.png" alt="신한(구LG)"></td>	                		
		               		</tr>    		
		                </tbody>
		             </table>
	             </div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.18" /></h4>
					<div class="txts">
						<ul class="text-list02 txtTypeGray">
							<li><spring:message code="order.payment.card.info2.19" /></li>
							<li><spring:message code="order.payment.card.info2.20" /></li>
							<li><spring:message code="order.payment.card.info2.21" /></li>							
						</ul>
					</div>
				</div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.22" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.23" />
					</p>
				</div>

				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.24" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.25" />
					</p>
				</div>
				
				<div class="txtsBox">
					<h4><spring:message code="order.payment.card.info2.26" /></h4>
					<p class="txts">
						<spring:message code="order.payment.card.info2.27" />
					</p>
				</div>				
										
				
				<div class="txtsBox">
					<ul class="text-list02">					
						<li><spring:message code="order.payment.card.info1.19" /></li>
					</ul>
				</div>
				
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>

<article id="lypopCardPaysf" class="layer-popup lypopCardinfo lypopCardPaysf inlayer">
	<section class="layer-popup-cont" tabindex="0" style="width:850px">
		<h2><spring:message code="order.payment.info3" /></h2>
		<div class="layer-cont ly-box scroll"><!-- 스크롤 필요시 scroll -->
		
			<div class="payTxtBox">
				<h3 class="fw_bold"><spring:message code="order.payment.card.info3.1" /></h3>
				<p class="mgB10"><spring:message code="order.payment.card.info3.2" /></p>
				<p class="mgB10"><spring:message code="order.payment.card.info3.3" /></p>
				<p><spring:message code="order.payment.card.info3.4" /></p>
				
				<ul class="text-list01">
					<li><spring:message code="order.payment.card.info3.5" />
						<ul class="text-list02">
							<li><spring:message code="order.payment.card.info3.6" /></li>
							<li><spring:message code="order.payment.card.info3.7" /></li>
							<li><spring:message code="order.payment.card.info3.8" /></li>							
						</ul>
					</li>
					<li><spring:message code="order.payment.card.info3.9" />
						<ul class="issueBox">
							<li><em>01.</em><spring:message code="order.payment.card.info3.10" /></li>
							<li class="fc_red">
								<span class="chkTxt posFt"><spring:message code="order.payment.card.info3.11" /></span>
								<em>02.</em><spring:message code="order.payment.card.info3.12" />
								<span class="chkTxt posEnd"><spring:message code="order.payment.card.info3.13" /></span>
							</li>
							<li><em>03.</em><spring:message code="order.payment.card.info3.14" /></li>
							<li><em>04.</em><spring:message code="order.payment.card.info3.15" /></li>
						</ul>
						
					</li>
					<li><spring:message code="order.payment.card.info3.16" />
						<div class="btnBanks">
							<a href="http://www.bccard.com" target="_blank" class="btn sm gray bankBc"><spring:message code="order.payment.card.info3.17" /></a>
							<a href="http://kbcard.kbstar.com/quics?asfilecode=5023&_nextPage=page=card&weblog=introAf0" target="_blank" class="btn sm gray bankKb"><spring:message code="order.payment.card.info3.18" /></a>
							<a href="http://ccd.wooribank.com/ccd/psn/isp/wdccd330_01c.jsp?q=C0A8582A189E027590D52B4B499EC3F9A33727D16DBE8F" target="_blank" class="btn sm gray bankUri"><spring:message code="order.payment.card.info3.19" /></a>
						</div>
					
					</li>
				</ul>							
				
				<ul class="text-list02">
					<li class="fc_gray"><spring:message code="order.payment.card.info3.20" /></li>
					<li><spring:message code="order.payment.card.info1.19" /></li>
				</ul>
			</div>
			
			<div class="btn_custom"><a href="/helpdesk" class="btn sm gray"><spring:message code="order.payment.card.info1.20" /></a></div>
					
		</div> 
		<div class="layer-popup-close">
			<button type="button" class="d_layer_close"><spring:message code="common.js.close" /></button>
		</div>
	</section>
</article>
