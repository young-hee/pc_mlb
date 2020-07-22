<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.plgrim.ncp.base.enums.GoodsEnum"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<ncp:prop key="ncp.image.url" var="imageURL"/>
 
							<c:forEach items="${contt}" var="conArr" >
							     <div class="main-cotents-item">
							         <h2>${conArr.dspCnrNm }</h2>
							          <c:forEach items="${conArr.dspCnrSetList}" var="setArr" >
							           <div class="main-item-view">   
							            <c:set var="godCount" value="0"/>
							             <c:forEach items="${setArr.cnrConttExList}" var="contt"  varStatus="loop" >							              
							                   <c:if test="${contt.conttTpCd eq 'IMG_BANNER'}">
							                   		 <div class="main-item-img"> <a   href="${contt.conttCnncUrl }" onclick="return false;" conttCnncUrl="${contt.conttCnncUrl }"  outptTpCd="${contt.outptTpCd }" ><img src="${imageURL }${contt.imgFileUrl}/${contt.imgFileNm }" alt=""></a> </div> 
							                   </c:if>		
							                    <c:if test="${contt.conttTpCd eq 'HTML'}">
							                     ${contt.htmlCont }
							                    </c:if>
							                   <c:if test="${contt.conttTpCd eq 'TEXT'}">
							                    <p class="main-item-${contt.conttSizeCd}">${ contt.conttNm} </p>
							                   </c:if>	
							                   <c:if test="${contt.conttTpCd eq 'MOVI'}">
								               	<div class="main-item-img">
								               		<a href="javascript_:void(0);" onclick="return false;" conttCnncUrl="${contt.moviFileUrl }"  outptTpCd="MOVPOPUP"   >
								               			<img src="${imageURL}${contt.moviImgUrl}/${contt.moviImgFileNm}" alt="">
								               			<span>
								               				<img src="${_resourceURL}static/images/showing/btn_play.png" alt="play">
								               			</span>
								               		</a>
								               	</div>
								               	</c:if>
							                   
							                   <c:if test="${contt.conttTpCd eq 'GOD'}">
							                    <c:if test="${godCount eq 0}">
							                    	 <div class="main-item-list02">
										                <div class="swiper-container">
											               <ul class="swiper-wrapper">
											                <c:set var="godCount" value="1"/>      
							                    </c:if>
							                         <li class="swiper-slide">
													<div class="item-img"><a href="${contt.godUrl }" onclick="return false;" conttCnncUrl="${contt.godUrl }"  outptTpCd="${contt.outptTpCd }"  ><img src="${imageURL }${contt.imgFrontUrl }" alt="${contt.godNm }" onerror="errorImgShow(this,'<%=String.valueOf(GoodsEnum.GoodsImageReSizeCd.IMG_107x143)%>');"></a></div>
													<div class="item-info">
														<p class="txt01"><strong><fmt:formatNumber value="${contt.lastSalePrc }" type="number"/><spring:message code="display.main.text1" /></strong></p>
													</div>
												    </li>
								                  <c:if test="${loop.last}">
								                  		   </ul>
										               </div>
										               <button type="button" class="item-list-prev">이전</button>
										               <button type="button" class="item-list-next">다음</button>
									                </div>
									                <c:set var="godCount" value="0"/>
								                  </c:if>
							                   </c:if>	
							                   			                
							            </c:forEach>
							           </div>
							         </c:forEach>
							     </div>
							</c:forEach>

 
 