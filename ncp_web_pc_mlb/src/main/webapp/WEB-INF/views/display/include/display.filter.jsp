<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<div class="ui_dp_fillter d_dropdown">
	<button type="button" class="bt_fillter open d_dropdown_sel"><span>FILTER</span></button>
	<div class="filter-layer">
		<div class="tops">
			<button type="button" class="bt_fillter close d_dropdown_close" title="FILTER CLOSE"><span>FILTER</span></button>
		</div>
		<div class="ctns">
			<div class="filter-search">
				<table>
					<colgroup>
						<col>
						<col>
					</colgroup>
					<tbody>
						<c:set var="checkbox" value="0" />
						<!-- 브랜드는 검색리스트 페이지에서만  나옵니다. -->
						<c:if test="${!empty dspCtgryScFrDTO.searchText}">
							<tr class="brand">
								<th><span><spring:message code="display.main.text21" /></span></th>
								<td>
									<div class="brandPan">
										<ul class="list">
											<li>
												<span class="check-skin">
													<input type="checkbox" id="brand_1" value="M" <c:if test="${dspCtgryScFrDTO.searchConditionBrandId eq 'M'}"> checked="checked"</c:if> > 
													<span>선택</span> 
												</span> 
												<label for="brand_1">WOMEN/MEN</label>
											</li>
											<li>
												<span class="check-skin">
													<input type="checkbox" id="brand_2" value="I" <c:if test="${dspCtgryScFrDTO.searchConditionBrandId eq 'I'}"> checked="checked"</c:if> > 
													<span>선택</span> 
												</span> 
												<label for="brand_2">KIDS</label>
											</li>
											<li>
                                                <span class="check-skin">
                                                    <input type="checkbox" id="brand_3" value="F" <c:if test="${dspCtgryScFrDTO.searchConditionBrandId eq 'I'}"> checked="checked"</c:if> >
                                                    <span>선택</span>
                                                </span>
                                                <label for="brand_3">FAMILY</label>
                                            </li>
										</ul>
									</div>
								</td>
							</tr>
						</c:if>
						<tr class="team" <c:if test="${empty americanTeamFilters and empty nationalTeamFilters}">style="display:none;"</c:if> >
							<th ><span><spring:message code="display.main.text22" /></span></th>
							<td>
								
								<c:if test="${!empty americanTeamFilters}">
									
									<div class="leagPan am">
										<span class="hd">AMERICAN <br>LEAGUE</span>
										<ul class="list">
											<c:forEach items="${americanTeamFilters}" var="american">
												<li title="${american.teamAltNm}">
													<c:forEach items="${searchTeamCd }" var="searchTeamCd" >
												 		<c:if test="${american.teamCd eq searchTeamCd}">
												 			<c:set var="checkbox" value="1" />
												 		</c:if>
												 	</c:forEach>
													<button type="button" class="bt d_icon_toggle<c:if test="${checkbox eq '1' }"> on</c:if>" value="${american.teamCd }" >
														<img src="${_resourceURL}static/images/dp/leag_a_${american.teamCd}.png?v=${_version}/dims/resize/57x57" alt="${american.teamAltNm}" >
													</button>
												</li>
												<c:set var="checkbox" value="0" />
											</c:forEach>
										</ul>
									</div>
									
								</c:if>
								<c:if test="${!empty nationalTeamFilters}">
									
									<div class="leagPan na">
										<span class="hd">NATIONAL <br>LEAGUE</span>
										<ul class="list">
											<c:forEach items="${nationalTeamFilters}" var="national">
												<li title="${national.teamAltNm}">
													<c:forEach items="${searchTeamCd }" var="searchTeamCd" >
												 		<c:if test="${national.teamCd eq searchTeamCd}">
												 			<c:set var="checkbox" value="1" />
												 		</c:if>
												 	</c:forEach>
													<button type="button" class="bt d_icon_toggle<c:if test="${checkbox eq '1' }"> on</c:if>" value="${national.teamCd }" >
														<img src="${_resourceURL}static/images/dp/leag_n_${national.teamCd}.png?v=${_version}/dims/resize/57x57" alt="${national.teamAltNm}" >
													</button>
												</li>
												<c:set var="checkbox" value="0" />
											</c:forEach>
										</ul>
									</div>
									
								</c:if>
								
							</td>
						</tr>
						<tr class="color">
							<th><span><spring:message code="display.main.text6" /></span></th>
							<td>
								<div class="colorPan">
									<ul class="list">
										
									 <c:forEach items="${colorFilters }" var="color" varStatus='status'>
										 <li>
										  <c:forEach items="${searchColor }" var="searchColor" >
											  <c:if test="${color.optValCd1 eq searchColor}">
											    <c:set var="checkbox" value="1" />
											  </c:if>
											  
											 </c:forEach>
											 <c:choose>
											   <c:when test="${ checkbox eq '1'}">
												  <button type="button" class="bt d_icon_toggle on"   value="${color.optValCd1 }"><img src="${_resourceURL}static/images/dp/color_${color.optValCd1 }.png?v=${_version}" alt="${color.optValCd1 }" ></button>
											   </c:when>
											   <c:otherwise>
											 	  <button type="button" class="bt d_icon_toggle"  value="${color.optValCd1 }"><img src="${_resourceURL}static/images/dp/color_${color.optValCd1 }.png?v=${_version}" alt="${color.optValCd1 }" ></button>
											   </c:otherwise>
											 </c:choose>
											<span class="name"><em>${color.colorStyleCd }</em></span>
										</li>
										 <c:set var="checkbox" value="0" />
									</c:forEach>
										
									</ul>
								</div>

							</td>
						</tr>
						<tr class="size">
							<th><span><spring:message code="display.main.text5" /></span></th>
							<td>
								
								<c:choose>
									<c:when test="${empty dspCtgryScFrDTO.lowerCtgryList and !empty dspCtgryScFrDTO.searchText}">
										<div class="sizePan">
												 
											 <c:forEach items="${sizeFilters}" var="sizeFilter" varStatus="status">
											 	
											 	<ul class="list">
											 
												 	<c:forEach items="${sizeFilter}" var="size" varStatus="rawStatus">
												 	
												 		<li>
												 			<span class="check-skin">
												 				<c:forEach items="${searchSize }" var="searchSize" >
												 					<c:if test="${size.optValCd1 eq searchSize}">
												 						<c:set var="checkbox" value="1" />
												 					</c:if>
												 				</c:forEach>
												 				<input type="checkbox" value="${size.optValCd1}" id="size${status.count}_${rawStatus.count}"<c:if test="${checkbox eq '1' }"> checked="checked"</c:if> > <span>선택</span> 
												 			</span> 
												 			<label for="size${status.count}_${rawStatus.count}">${size.optValCd1}</label>
												 		</li>
												 		<c:set var="checkbox" value="0" />
													
													 </c:forEach>	
											
											 	</ul>
											 
											 </c:forEach>
										
										</div>
										
									</c:when>
									<c:when test="${empty dspCtgryScFrDTO.lowerCtgryList and empty dspCtgryScFrDTO.searchText}">
										<div class="sizePan">
											
											 <ul class="list">
											 
												 <c:forEach items="${sizeFilters}" var="sizeFilter" varStatus="status">
											 
												 	<c:forEach items="${sizeFilter}" var="size" varStatus="rawStatus">
												 	
												 		<li>
												 			<span class="check-skin">
												 				<c:forEach items="${searchSize }" var="searchSize" >
												 					<c:if test="${size.optValCd1 eq searchSize}">
												 						<c:set var="checkbox" value="1" />
												 					</c:if>
												 				</c:forEach>
												 				<input type="checkbox" value="${size.optValCd1}" id="size${status.count}_${rawStatus.count}"<c:if test="${checkbox eq '1' }"> checked="checked"</c:if> > <span>선택</span> 
												 			</span> 
												 			<label for="size${status.count}_${rawStatus.count}">${size.optValCd1}</label>
												 		</li>
												 		<c:set var="checkbox" value="0" />
													
													 </c:forEach>	
											 
											 	</c:forEach>
											
											 </ul>
											
										</div>
										
									</c:when>
									<c:otherwise>
										<c:forEach items="${dspCtgryScFrDTO.lowerCtgryList}" var="lowerCtgry" varStatus="upperStatus">

											<c:if test="${!empty lowerCtgry.dspCtgryGodFoResult.sizeFilters[0]}">
												
												<div class="sizePan">
													<span class="hd">${lowerCtgry.dspCtgryNm}</span>
													<ul class="list" data-ul="${lowerCtgry.dspCtgryNo}">
														<c:forEach items="${lowerCtgry.sizeFilters}" var="sizeFilter" varStatus="status">
															<c:forEach items="${sizeFilter}" var="size" varStatus="lowerStatus">
																<li>
																	<span class="check-skin">
																		<c:forEach items="${dspCtgryScFrDTO.dspCtgryScFrDTOList}" var="dspCtgryScFrDTOList">
																			
																			<c:if test="${dspCtgryScFrDTOList.dspCtgryNo eq lowerCtgry.dspCtgryNo}">
	
																				<c:forEach items="${dspCtgryScFrDTOList.searchConditionSizes}" var="searchConditionSize">
																				
																					<c:if test="${size.optValCd1 eq searchConditionSize}">
											 											<c:set var="checkbox" value="1" />
											 										</c:if>	
																				
																				</c:forEach>
	
																			</c:if>
																		
																		</c:forEach>
																		<input type="checkbox" id="size${upperStatus.count}_${status.count}_${lowerStatus.count}" value="${size.optValCd1}" <c:if test="${checkbox eq '1' }"> checked="checked"</c:if> > <span>선택</span> 
																	</span> 
																	<label for="size${upperStatus.count}_${status.count}_${lowerStatus.count}">${size.optValCd1}</label>
																</li>
																<c:set var="checkbox" value="0" />
															</c:forEach> 
														</c:forEach>
													</ul>
												</div>
																					
											</c:if>
										
										</c:forEach>
									
									</c:otherwise>
								</c:choose>

							</td>
						</tr>
						<tr class="price">
							<th><span><spring:message code="display.main.text7" /></span></th>
							<td>
								<div class="pricePan">
									<div class="filter-price">
										<div class="filter-price-range">
											<div id="slider-range"></div>
										</div>
										<div class="filter-price-view">
											<input type="number" class="input-style01" id="amountStart" readonly="readonly"> <spring:message code="display.main.text18" /> ~
											<input type="number" class="input-style01" id="amountEnd" readonly="readonly"> <spring:message code="display.main.text18" />
										</div>
										<script>
											$( function() {
												$( "#slider-range" ).slider({
													range: true,
													min: 0,
													max: 300,
													step: 1,
													values: [ ${dspCtgryScFrDTO.prcStart} || 0, ${dspCtgryScFrDTO.prcEnd} || 300 ],
													slide: function( event, ui ) {
														$('#amountStart').val(+ ui.values[ 0 ]);
														$('#amountEnd').val(+ ui.values[ 1 ]);
													}
												});
												$('#amountStart').val($('#slider-range').slider('values', 0 ));
												$('#amountEnd').val($('#slider-range').slider('values', 1 ));
											} );
										</script>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btnList">
				<a href="javascript:;" class="btn btnRest"><spring:message code="display.main.text8" /></a> 
				<a href="javascript:;" class="btn fill btnSch"><spring:message code="display.main.text9" /></a>
			</div>
		</div>
	</div>
</div>

<script>

	$( document ).ready(function() {
		
		//버튼 초기화
		$(".btn.btnRest").on("click", function() {
			
			$(".filter-layer").find("button.on").removeClass("on");							    //야구팀, 컬로 체크박스 초기화
			$(".filter-layer").find("input[type='checkbox']").prop("checked", false);			//브랜드, 사이즈 체크박스 초기화
			
			$("#slider-range").slider({values : [0, 300]});										//가격 슬라이드 초기화
			$('#amountStart').val($('#slider-range').slider('values', 0 ));						//검색 최저가격 초기화
			$('#amountEnd').val($('#slider-range').slider('values', 1 ));						//검색 최대가격 초기화
			
		});
		
		//검색 버튼
		$(".btn.fill.btnSch").on("click", function() {

			var searchConditionBrandId = "";												//검색필터 : 브랜드ID
			var searchConditionTeamCd = "";													//검색필터 : 팀코드
			var searchConditionColor = "";													//검색필터 : 색상
			var searchConditionSize = "";													//검색필터 : 사이즈
			var searchConditionDspCtgrySize = "";											//검색필터 : 하위카테고리별 사이즈
			var prcStart = $("#amountStart").val();											//검색필터 : 시작가격
			var prcEnd = $("#amountEnd").val();												//검색필터 : 끝가격
			
			var $ctgrySearchForm = $("#ctgrySearchForm");									//검색하기위한form
			
			//선택한 브랜드
			if($(".brand").find("input[type='checkbox']").filter(":checked").length === 1) {
				
				searchConditionBrandId += "<input name=\"searchConditionBrandId\" value=" + $(".brand").find("input[type='checkbox']").filter(":checked").val() + " />";
				
			}
			
			//선택한 팀코드
			$(".team").find("button.on").each(function(index, obj) {
				
				searchConditionTeamCd += "<input name=\"searchConditionTeamCds\" value=" + obj.value + " />";
			
			});
			//선택한 색상
			$(".color").find("button.on").each(function(index, obj) {
				
				searchConditionColor += "<input name=\"searchConditionColors\" value=" + obj.value + " />";
				
			});
			//하위카테고리가 없을경우
			if(${empty dspCtgryScFrDTO.lowerCtgryList}) {
				//선택한 사이즈
				$(".size").find("input[type='checkbox']").filter(":checked").each(function(index, obj) {
					
					searchConditionSize += "<input name=\"searchConditionSizes\" value=" + obj.value + " />";
					
				});
			//하위카테고리 있을 경우 카테고리별 사이즈 파라미터로 셋팅	
			} else {
				//선택한 카테고리
				$(".size").find("ul.list").each(function(index, obj) {
					var paramValue = { 
							"dspCtgryNo" : $(obj).data("ul"),
							"searchConditionSizes" : []
					};
					//선택한 사이즈
					$(obj).find("input[type='checkbox']").filter(":checked").each(function(index, sizeObj) {
						
						paramValue.searchConditionSizes.push(sizeObj.value);
						
					});
					
					searchConditionDspCtgrySize += paramValue.searchConditionSizes.length > 0 ? "<input name=\"searchConditionDspCtgrySizes\" value=" + encodeURIComponent(JSON.stringify(paramValue)) + " >" : "";
					
				});
			}

			//선택한 시작가격
			if($ctgrySearchForm.children("input[name='prcStart']").length > 0) {
				
				$ctgrySearchForm.children("input[name='prcStart']").val(prcStart);
				
			} else {
				
				$ctgrySearchForm.append("<input name=\"prcStart\" value=" + prcStart + " />");
				
			}
			//선택한 끝가격
			if($ctgrySearchForm.children("input[name='prcEnd']").length > 0) {
				
				$ctgrySearchForm.children("input[name='prcEnd']").val(prcEnd);
				
			} else {
				
				$ctgrySearchForm.append("<input name=\"prcEnd\" value=" + prcEnd + " />");
				
			}
			
			//기존 검색필터조건 삭제
			$ctgrySearchForm.children("input[name='searchConditionBrandId']").remove();			//기존 브랜드 검색조건 삭제
			$ctgrySearchForm.children("input[name='searchConditionTeamCds']").remove();			//기존 팀코드 검색조건 삭제
			$ctgrySearchForm.children("input[name='searchConditionColors']").remove();			//기존 색장 검색조건 삭제
			$ctgrySearchForm.children("input[name='searchConditionSizes']").remove();			//기존 사이즈 검색조건 삭제
			$ctgrySearchForm.children("input[name='searchConditionDspCtgrySizes']").remove();	//기존 하위 카테고리별 사이즈 검색조건 삭제
			
			//input 생성
			$ctgrySearchForm.append( searchConditionBrandId )									//브랜드 검색조건 추가
							.append( searchConditionTeamCd )						            //팀코드 검색조건 추가             
							.append( searchConditionColor )                                     //색장 검색조건 추가              
							.append( searchConditionSize )                                      //사이즈 검색조건 추가             
							.append( searchConditionDspCtgrySize );                             //하위 카테고리별 사이즈 검색조건 추가    
							
			$ctgrySearchForm.submit();
			
		});
		
	} );

</script>