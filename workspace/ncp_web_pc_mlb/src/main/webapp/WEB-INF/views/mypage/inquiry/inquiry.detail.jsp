<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>
<% pageContext.setAttribute("newLineChar", "\n"); %>

<%-- <ncp:prop key="ncp.aws.cloudfront.image.url" var="imageURL"/> --%>
<%-- <ncp:prop key="ncp.image.url" var="imageURL"/> --%>
<ncp:prop key="ncp.cdn.url" var="imageURL"/>


<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

    <form id ="srchForm" action ="<c:url value ='/mypage/inquiry/updateMyInquiryList' />" method="post" >
        <input type ="hidden" name ="srchMtmInqSn" value ="${srchMtmInqSn}" />
        <input type="hidden" name="mtmInqSn" value="${srchMtmInqSn}"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="hidden" name="pageNo"  id="pageNo" value=""/>

        <div class="contain my lnblist-Wrap" id="contain">
            <div class="container">

                <h2 class="title01">1:1 문의</h2>

                <%@ include file="../include/lnb.jspf" %>

                <main class="contents oto_inquiryView-wrap" id="contents">

                    <div class="location-contents">
                        <p class="location">
                            <span>Home</span>
                            <span>마이페이지</span>
                            <span>활동정보</span>
                            <strong title="현재 위치">1:1 문의</strong>
                        </p>
                    </div>

                    <div class="board-list">
                        <table>
                            <caption>1:1 문의 이메일주소, 핸드폰번호, 제목, 작성일</caption>
                            <colgroup>
                                <col style="width:100px;">
                                <col>
                                <col style="width:140px;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th scope="col" colspan="3">
                                        <div class="clearfix">
                                            <div class="fl">
                                                <c:if test="${not empty inquiryOrdGodList}">
                                                    <p>
                                                        <em>주문번호 :</em>
                                                        <span><c:out value="${inquiryOrdGodList[0].csoMtmInqOrdGod.ordNo}"/></span>
                                                    </p>
                                                </c:if>
                                                <p>
                                                    <em>이메일주소 :</em>
                                                    <span><c:out value="${inquiryList[0].csoMtmInq.cstmrEmail}"/></span>
                                                </p>
                                                <p>
                                                    <em>핸드폰번호 :</em>
                                                    <span><c:out value="${inquiryList[0].csoMtmInq.cstmrmobilAreaNo}-${inquiryList[0].csoMtmInq.cstmrmobilTlofNo}-${inquiryList[0].csoMtmInq.cstmrmobilTlofWthnNo}" /></span>
                                                </p>
                                            </div>
                                            <c:if test="${empty inquiryDetail[0].csoMtmInqAns.ansSj}">
                                                <div class="fr">
                                                    <a href="#" class="btn sm" onclick="javascript:updateMyInquiryList('<c:out value="${srchMtmInqSn}"/>');">수정</a>
                                                    <a href="#" class="btn sm fill" onclick="javascript:deleteMyInquiryList('<c:out value="${srchMtmInqSn}"/>');">삭제</a>
                                                </div>
                                            </c:if>
                                        </div>
                                    </th>
                                </tr>
                                <tr>
                                    <ncp:code var="cdTpCd" code="${inquiryList[0].csoMtmInq.inqTpCd}"/>
                                    <th scope="col">[<c:out value="${cdTpCd.cdNm}"/>]</th>
                                    <th scope="col"><c:out value="${inquiryList[0].csoMtmInq.inqSj}"/></th>
                                    <th scope="col">작성일</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td></td>
                                    <td>
                                    	${fn:replace(inquiryList[0].csoMtmInq.inqCont, newLineChar, '<br/>')}
                                    	<c:if test="${ ( fn:indexOf( fn:toLowerCase(inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl), '.gif' ) > 0 )
								               || ( fn:indexOf( fn:toLowerCase(inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl), '.png' ) > 0 )
								               || ( fn:indexOf( fn:toLowerCase(inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl), '.jpg' ) > 0 )
								               || ( fn:indexOf( fn:toLowerCase(inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl), '.jpeg' ) > 0 ) }" >
										<div class="bd-listImg"><img src="${imageURL}/${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl}" alt="첨부파일"></div>
										</c:if>
										<c:if test="${not empty inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileNm}" >
											<span class="bd-listTxT">
												<spring:message code='mypage.inquiry.lbl.txt29' text="첨부파일"/> :
												<a href="${imageURL}/${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileUrl}">${inquiryFileList[0].csoMtmInqAtchFile.mtmInqAtchFileNm}</a>
											</span>
										</c:if>
                                    </td>
                                    <td>${inquiryList[0].inqDt}</td>
                                </tr>
                                <c:if test="${not empty inquiryDetail[0].csoMtmInqAns.ansSj}">
                                <tr>
                                    <td></td>
                                    <td>${fn:replace(inquiryDetail[0].csoMtmInqAns.ansCont, newLineChar, '<br/>')}</td>
                                    <td>${inquiryDetail[0].ansRegDt}</td>
                                </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>

                    <div class="btnWrapBox">
                        <a href="#" class="btn" onclick="javascript:goInquiryList('${pageNo}');">목록</a>
                    </div>

                </main>

            </div>
        </div>

    </form>


<script type="text/javascript" >

        /* 1:1 문의 목록조회 */
        function goInquiryList(pageNo){

            $("#srchForm").attr("action","/mypage/inquiry/list");
            $("#srchForm").submit();

            //location.href='/secured/mypage/myInquiryListDetail/'+val;
        }

        function updateMyInquiryList(obj) {
            /*
            $("input[name='srchMtmInqSn']").val(obj);
            $("#srchForm").submit();
            */

            var param = "";
            param += "?" + "srchMtmInqSn=" + obj ;
            param += "&" + "dspCtgryNo=DMPA01A02A05";	// 메뉴 유지를 위한 데이터
            param += "&" + "currentCtgryDpthCd=3";		// 메뉴 유지를 위한 데이터
            param += "&" + "ctgrySectCd=GNRL_CTGRY";	// 메뉴 유지를 위한 데이터
            param += "&" + "ctgryNoDpth1=DMPA01";		// 메뉴 유지를 위한 데이터
               param += "&" + "ctgryNoDpth2=DMPA01A02";	// 메뉴 유지를 위한 데이터
               param += "&" + "ctgryNoDpth3=DMPA01A02A05";	// 메뉴 유지를 위한 데이터

               location.href ='/mypage/inquiry/updateMyInquiryList' + param;
        }

        /* 1:1 문의 삭제 */
        function deleteMyInquiryList(srchMtmInqSn) {
            if (confirm('문의하신 내용을 삭제하시겠습니까?') == true) {
                $("#srchMtmInqSn").val(srchMtmInqSn);
                $("#srchForm").attr("action" , "<c:url value='/mypage/inquiry/deleteMyInquiry' />");
                $("#srchForm").submit();
            } else {
                return false;
            }
        }
</script>
