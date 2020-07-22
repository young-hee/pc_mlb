
<article id="lypopGoodsInform" class="layer-popup lypopGoodsInform">
	<section class="layer-popup-cont" tabindex="0" style="width:529px">
		<h2><spring:message code="goods.inform.ttl"/></h2>
		<div class="layer-cont ly-box">
		
			<input type="hidden" name="ifrGodNo" id="ifrGodNo" value="${goods.godEx.godNo}"/>
			<input type="hidden" name="ifrMbrNo" id="ifrMbrNo" value="${_user.mbr.mbrNo}"/>
			<p><spring:message code="goods.inform.top.dscr"/></p>		
			
			<table class="board-write">
				<colgroup>
					<col style="width:125px">
					<col style="width:px">							
				</colgroup>
				<tbody>
					<!-- loop -->
					<tr>
						<th><label for="ifrSize"><spring:message code="goods.option.lbl.size"/></label></th>
						<td>
							<!-- size select -->
							<div class="select-style01 d_select sm">
								<c:set var="isCountinue" value="Y" />
								<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">		
									<c:if test="${size.itmStatCd eq 'SMTM_SLDOUT'}">
										<c:if test="${isCountinue eq 'Y'}">
											<button type="button" id="ifrSize" class="d_select_sel" style="width:152px;"><span id="informSize"><c:out value="${size.itmNm}"/></span></button>
											<c:set var="isCountinue" value="N" />
										</c:if>
									</c:if>
								</c:forEach>
								<ul>
									<c:forEach var="size" items="${goods.godItmExList}" varStatus="status">		
										<c:if test="${size.itmStatCd eq 'SMTM_SLDOUT'}">
											<li><a href="javascript:;" name="informSizeLi" value="${size.itmNo}"><c:out value="${size.itmNm}"/></a></li>
										</c:if>
									</c:forEach>
								</ul>
							</div>
							<!-- //select -->	
						</td>						
					</tr>	
					<!-- // loop -->  
					<tr>
						<th><label for="ifrName"><spring:message code="goods.inform.cust.name"/></label></th>
						<td><input type="text" id="ifrName" class="input-style01 sm input_required textOnly" disabled="disabled" style="width:150px;" alt="이름" maxlength="100" value="${_user.mbr.mbrNm}" ></td>						
					</tr>	
					<tr>
						<th><label for="ifrMb01"><spring:message code="goods.inform.cust.pNum"/></label></th>
						<td>							
							<input type="text" id="ifrMb01" class="input-style01 sm numberOnly" style="width:65px;" maxlength="3" >
							<span class="hyphen">-</span>
							<input type="text" id="ifrMb02" class="input-style01 sm numberOnly" style="width:75px;" maxlength="4" >
							<span class="hyphen">-</span>
							<input type="text" id="ifrMb03" class="input-style01 sm numberOnly" style="width:75px;" maxlength="4" >
						</td>						
					</tr>											
																								
				</tbody>
			</table>	
			
			<p class="txtIcon02 txtGray"><spring:message code="goods.inform.bottom.dscr"/></p>					
			
			<!--  button -->
			<div class="lyBtnArea">
				<a href="javascript:resetGoodsInform();" class="btn w160"><spring:message code="goods.js.common.btn.cancel"/></a>
				<a href="#" onclick="javascript:goodsInformRequest();" class="btn fill w160"><spring:message code="goods.inform.confirm"/></a>
			</div>
			
		</div>
		<div class="layer-popup-close">
			<button type="button" onclick="javascript:resetGoodsInform();" class="d_layer_close"><spring:message code="goods.js.common.btn.close"/></button>
		</div>
	</section>
</article>
