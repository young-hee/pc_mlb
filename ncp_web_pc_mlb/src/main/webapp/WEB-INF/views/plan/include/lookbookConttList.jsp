<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

	<section class="ctns" style="min-height:600px;">
		<div class="look_cate">
			<ul class="menu">
				
				<li><span class="txt"><em class="t">${lookbook.FLocation} SEASON</em></span></li>
				
				<c:if test="${lookbook.ctgryDpthCd ne '2'}">
				
					<li>
						<span class="txt"><em class="t">${lookbook.dspCtgryNm}</em><a href="javascript:;" class="bt">하위메뉴열기</a></span>
						<div class="subs">
							<a href="javascript:;" class="close">닫기</a>
							<ul class="list">
									
								<li>
									<a href="javascript:lookbookLink('${lookbook.upperCategory}', '${lookbook.FLocation}', '${lookbook.dspCtgryNo}', '3');" class="lk">
										<em class="t">${lookbook.dspCtgryNm}</em>
									</a>
								</li>
									
								<c:forEach items="${lookbook.lowerCtgryList}" var="contt" varStatus="status">
									
									<li>
										<a href="javascript:lookbookLink('${lookbook.upperCategory}', '${lookbook.FLocation}', '${contt.dspCtgryNo}', '3');" class="lk">
											<em class="t">${contt.dspCtgryNm}</em>
										</a>
									</li>
									
								</c:forEach>
								
							</ul>
						</div>
					</li>
				
				</c:if>
				
			</ul>
		</div>
		
		<c:choose>
			
			<c:when test="${lookbook.ctgryDpthCd eq '2'}">
				
				<div class="look_banner">
					<ul class="list">
					
						<c:forEach items="${lookbookContt}" var="contt">
						
							<c:if test="${!empty contt.imgFileUrl and !empty contt.imgFileNm}">
								
								<li>
									<a  href= <c:if test="${!empty contt.conttCnncUrl}"> "javascript:lookbookLink('${lookbook.dspCtgryNo}', '${lookbook.FLocation}', '${contt.conttCnncUrl}', '3');" </c:if> >
										<span class="banner">
											<img src="${_image}${contt.imgFileUrl}/${contt.imgFileNm}/dims/resize/1520x600" alt="${contt.imgAltrtvCont}"  />
										</span>
									</a>
								</li>
								
							</c:if>
							
						</c:forEach>
		
					</ul>
				</div>
				
			</c:when>
			<c:otherwise>
			
				<div class="look_list">
					<ul class="list">
					
						<c:forEach items="${lookbookContt}" var="contt">
						
							<c:if test="${!empty contt.rprstImgFileUrl and !empty contt.rprstImgFileNm}">
								
								<li>
									<div class="thumb">
										<a href="/lookbook/${contt.upperDspCtgryNo}/view?dspCtgryNo=${contt.dspCtgryNo}">
											<span class="img">
												<img src="${_image}${contt.rprstImgFileUrl}/${contt.rprstImgFileNm}/dims/resize/487x600" alt="${contt.rprstImgAltrtvCont}" >
											</span>
										</a>
									</div>
								</li>
								
							</c:if>
	
						</c:forEach>
						
					</ul>
				</div>
			
			</c:otherwise>
			
		</c:choose>
		
	</section>
	
	<script type="text/javascript">
	
	</script>