<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/mypage/mypage.order.js?v=${_version}"></script>

<div class="contain my od lnblist-Wrap" id="contain">
		<div class="container">

			<h2 class="title01"><spring:message code='${titleSetKey}' /></h2>
			
			<%@ include file="../include/lnb.jspf" %>
			
			<main class="contents" id="contents">
				
				<jsp:include page="/WEB-INF/views/include/location2.jsp" flush="false"/>
				
				<!--  이전주문 목록   -->
				<div class="orderInfoCon orderHistory">
					
					<!-- order Result -->
					<div class="odSearchResult">	
									
						<div class="odResultTop">
							<strong class="listTotal"><spring:message code="mypage.order.list.lbl.orderinfo" text="주문내역 "/>(<em class="fc_red">${purchaseListSize}</em><spring:message code="mypage.order.list.lbl.ordercount" text="건"/>)</strong>							
							<a href="#" class="btn sm fill" onclick="mypageorder.goInquiryList();return false;"><spring:message code="mypage.order.list.btn.inquiry" text="1:1 고객상담"/></a>						
						</div>
					
						<!--  order Result List -->
						<div class="odResulCon">		
							<c:choose>
								<c:when test="${empty purchaseList }">
									<!--  NO Result -->
									<div class="odResulBox">
										<div class="orderNb noResult">
											<spring:message code="mypage.order.list.lbl.noresult" text="구매내역이 없습니다."/>
										</div>	
									</div> 
									<!--  //NO Result -->     	
								</c:when>
								<c:otherwise>
								<c:set var="preOrdNo" value="" />
									<c:forEach var="purchase" items="${purchaseList }" varStatus="status">
										<div class="odResulBox">
											<div class="orderNb">									
				                              	<span><em><spring:message code="mypage.order.list.lbl.orderdate" text="주문일"/></em> ${purchase.ordDt }</span>	                              	
				                              	<span><em><spring:message code="mypage.order.list.lbl.orderno" text="주문번호"/></em> <a href="#" onclick="goPrevOrder('${purchase.ordNo }', '${purchase.ordSite }'); return false;">${purchase.ordNo }</a></span>
											</div>								
											<table class="board-list orderTable">
					                          <colgroup>
					                              <col style="width:">		                            
					                              <col style="width:90px">
					                              <col style="width:120px">
					                              <col style="width:145px">                                
					                          </colgroup>	          
					                          <tbody>
					                              <!-- loop -->
					                              <tr>
					                                  <td class="tleft">
					                                      <div class="product-info">
					                                          <div class="product-info-img"><a href="javascript:;"><img src="${purchase.godImgUrl }" alt="${purchase.godNm }" width="100" height="100" onerror='errorImgShow(this, "100");'></a></div>
					                                          <div class="product-info-text">
					                                              <div class="product-info-box">
					                                                  <p class="product-name"><a href="javascript:;">${purchase.godNm }</a></p>
					                                                  <div class="product-price">
					                                                      <span><fmt:formatNumber value="${purchase.godPrc }" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></span>
					                                                  </div>
					                                              </div>
					                                              <div class="product-option">
					                                                  <span>${purchase.optNm }</span>
					                                              </div>
					                                          </div>
					                                      </div>
					                                  </td>
					                                  <td>${purchase.ordQty }</td>		                          
					                                  <td><fmt:formatNumber value="${purchase.ordAmt }" pattern="#,###" /><spring:message code="mypage.order.lbl.currency" text="원"/></td>
					                                  <td>${purchase.ordStatCd }</td>
					                               </tr>
					                               <!-- //loop -->
					                            </tbody>
					                         </table>	
			                         	</div>	
									</c:forEach>								
								</c:otherwise>
							</c:choose>                         	                         	                   
                        </div>
                        <!--  //order Result List -->
                        
                        <!--  page List -->
                        <%-- 
						<div class="page">
							<!-- 링크 없을때
							<span class="first">첫 페이지</span>
							<span class="prev">이전 페이지</span>
							-->
							<a href="javascript:;" class="first" title="첫 페이지">첫 페이지</a>
							<a href="javascript:;" class="prev" title="이전 페이지">이전 페이지</a>
						
							<span>
								<strong title="현재 페이지">1</strong>
								<a href="javascript:;">2</a>
								<a href="javascript:;">3</a>
								<a href="javascript:;">4</a>
								<a href="javascript:;">5</a>
								<a href="javascript:;">6</a>
								<a href="javascript:;">7</a>
								<a href="javascript:;">8</a>
								<a href="javascript:;">9</a>
								<a href="javascript:;">10</a>
							</span>
						
							<a href="javascript:;" class="next" title="다음 페이지">다음 페이지</a>
							<a href="javascript:;" class="last" title="마지막 페이지">마지막 페이지</a>
							<!-- 링크 없을때
							<span class="next">다음 페이지</span>
							<span class="last">마지막 페이지</span>
							-->
						</div>
						--%>
						<!-- // page List -->
						
					</div>		
					<!-- //order Result -->		
					
				</div>		
				<!--  //이전주문 목록   -->	
				

			</main>
			
		</div>
	</div>

<ncp:prop key="ncp.url.pc_MLB.order.prev" var="prevUrl"/>
<ncp:prop key="ncp.url.pc_MLB_KIDS.order.prev" var="prevKidsUrl"/>
<ncp:prop key="ncp.url.mb_MLB.order.prev" var="prevUrlMb"/>

<form action="${prevUrl}" method="post" id="prevOrdForm" target="preview">
	<input type="hidden" name="ordno" id="prevOrdNo"/> 
</form>

<script>
	function goPrevOrder(ordNo, ordSite) {
		var ua = navigator.userAgent;
		var popupname="preview";
		window.open("",popupname,"width=850,height=600,scrollbars=yes,resizable=no,status=no,toolbar=no,menubar=no,location=no,top=50,left=50");
		if(isMobile(ua)) {
			$("#prevOrdForm").prop("action", "${prevUrlMb}");
		} else {
			if(ordSite == "I"){
				//mlb kids	
				$("#prevOrdForm").prop("action", "${prevKidsUrl}");
			}else{
				$("#prevOrdForm").prop("action", "${prevUrl}");
			}
		}
		
		$("#prevOrdNo").val(ordNo);
		
		
		$("#prevOrdForm").target  =popupname;
		$("#prevOrdForm").submit();
	}
	
	function isMobile(ua) {
		var ua = navigator.userAgent;
		
		if(ua.match('LG | SAMSUNG | Samsung | iPhone | iPod | Android | Windows CE | BlackBerry | Symbian | Windows Phone | webOS | Opera Mini | Opera Mobi | POLARIS | IEMobile | lgtelecom | nokia | SonyEricsson') != null) {
			return true;
		}
		
		return false;
	}
</script>