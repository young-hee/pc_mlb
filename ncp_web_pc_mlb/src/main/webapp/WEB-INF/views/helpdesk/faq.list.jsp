<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

<form id ="gForm" action ="<c:url value ='/helpdesk/faq/list' />" method="get">
    <input type ="hidden" name ="searchCd" value ="${searchCd}" />
    <input type ="hidden" name ="searchAllCd" value ="${searchAllCd}" />
    <input type ="hidden" id ="pageNo" name ="pageNo" value ="" />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    <div class="contain cs" id="contain">
        <div class="container">

            <h2 class="title01">자주 묻는 질문</h2>

            <main class="contents faqList-wrap" id="contents">

                <div class="location-contents">
                    <p class="location">
                        <span>Home</span><span>고객센터</span><strong title="현재 위치">자주 묻는 질문</strong>
                    </p>
                </div>

                <!-- tab S -->
                <ul class="tab-type02 d_tab">
                    <li><a href="javascript:;" id="All" onclick="document.location='/helpdesk/faq/list?searchAllCd='">전체</a></li>
                    <c:forEach var="cd" items="${faqTitles}" varStatus="status">
                        <li>
                            <a href="javascript:;" id ="${cd.cd}" onclick="document.location='/helpdesk/faq/list?searchAllCd=${cd.cd}'"><span><c:out value ="${cd.cdNm}" /></span></a>
                        </li>
                    </c:forEach>
                </ul>
                <!--//tab E -->

                <!-- 검색 S -->
                <div class="search-wrap01">
                    <div class="search-input">
                        <input type="search" title="검색" name="searchFaq" id="searchFaq" value="${searchFaq}" placeholder="궁금하신 내용을 입력해 주세요."/><button type="button" onclick="searchFaqList();">검색</button>
                    </div>
                </div>
                <!--//검색 E -->

                <!-- table S -->
                <div class="board-list accordion-type d_accordion">
                    <div class="boardCount">
                        <span>전체</span> (<span class="text-color01">${totalRow}</span>건)
                    </div>
                    <table>
                        <caption>자주 묻는 질문 Top 10 번호, 분류, 제목 정보표.</caption>
                        <colgroup>
                            <col style="width:100px;">
                            <col style="width:216px;">
                            <col>
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">분류</th>
                                <th scope="col">제목</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty faqList}">
                                <tr>
                                    <td colspan="3" class="no-result">검색결과가 없습니다. <a href="/helpdesk/csInquiry/new">1:1문의</a>를 이용해 주세요.</td>
                                </tr>
                            </c:if>
                            <c:forEach var="lists" varStatus="status" items="${faqList}">
                                <ncp:code var="cd" code="${lists.csoFaq.faqSectCd}" />
                                <tr id="countTr${lists.faqSn}">
                                    <td><c:out value="${lists.rowNo}" /></td>
                                    <td class="tleft"><c:out value="${cd.cdNm}" /></td>
                                    <td class="ask-wrap">
                                        <p class="inquiry d_accordion_select"  group="${lists.faqSn}"><c:out value="${lists.csoFaq.faqSj}" /></p>
                                        <div class="reply">
                                            <c:out value="${lists.csoFaq.faqCont}"  escapeXml=""  />
                                            <c:forEach items="${lists.csoFaqAtchmnfls}" var="faqFileList">
                                                <c:if test="${not empty faqFileList.faqAtchFileUrl }">
                                                    <img src="<ncp:img path="${faqFileList.faqAtchFileUrl}"/>" />
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <c:if test="${not empty faqList}">
                        <div class="page">
                            <!-- 링크 없을때
                            <span class="first">첫 페이지</span>
                            <span class="prev">이전 페이지</span>
                            -->
                            <c:if test="${currentPage <= 1 }">
                                <a href="javascript:emptyMark();" class="first" title="첫 페이지">첫 페이지</a>
                            </c:if>
                            <c:if test="${currentPage > 1 }">
                                <a href="javascript:goFaqList('1')" _onclick="return false;" class="first" title="첫 페이지">첫 페이지</a>
                            </c:if>
                            <c:if test="${!preFlag }">
                                <a href="javascript:emptyMark();" _onclick="return false;" class="prev" title="이전 페이지">이전 페이지</a>
                            </c:if>
                            <c:if test="${preFlag }">
                                <a href="javascript:goFaqList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
                            </c:if>

                            <span>
                                <c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <strong title="현재 페이지">${i}</strong>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="javascript:goFaqList('${i}')">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </span>

                            <c:if test="${nextFlag }">
                                <a href="javascript:goFaqList('${displayNextPage}')"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
                            </c:if>
                            <c:if test="${!nextFlag }">
                                <a href="javascript:emptyMark();"  _onclick="return false;" class="next" title="다음 페이지">다음 페이지</a>
                            </c:if>
                            <c:if test="${currentPage < totalPage}">
                                <a href="javascript:goFaqList('${totalPage}')" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
                            </c:if>
                            <c:if test="${currentPage >= totalPage}">
                                <a href="javascript:emptyMark();" _onclick="return false;" class="last" title="마지막 페이지">마지막 페이지</a>
                            </c:if>
                            <!-- 링크 없을때
                            <span class="next">다음 페이지</span>
                            <span class="last">마지막 페이지</span>
                            -->
                        </div>
                    </c:if>

                    <%-- <%@ include file="../_inc/paging.jsp" %> --%>
                </div>
                <!--//table E -->

            </main>

        </div>
    </div>
</form>
<script>
    var onCheck ="${searchAllCd}";
    $(document).ready(function(){
        if(onCheck){
            $("#"+onCheck).parents('li').addClass('on').siblings().removeClass('on');
        } else {
            $("#All").parents('li').addClass('on').siblings().removeClass('on');
        }

        $('.ask-wrap p').on('click',function(){
            var faqsn =$(this).attr('group');
            viewsCount(faqsn);
        });

    });
    function emptyMark(){
         return;
    }
    function goFaqList(pageNo){
        if(pageNo == ""){
            pageNo = 1;
        }
        $("#gForm").find('#pageNo').val(pageNo);
        $("#gForm").submit();
    }
    function searchFaqList(){
        if($('#searchFaq').val() != ""){$("#gForm").submit();}else{
            //alertLayer(MESSAGES['helpdesk.js.common.notext.msg']);
            //alert('<spring:message code="helpdesk.common.notext.msg"/>')
            alert('검색어를 입력 후, 검색버튼을 눌러 주세요.');
            }

    }
    function viewsCount(faqSn){
        var btnId = $("#countTr"+faqSn).prop("class");
        if(btnId != 'on'){
            $.ajax({
                type : "post",
                url:"/helpdesk/updateFaqListInquiry.json",
                data:{"faqSn":faqSn, '${_csrf.parameterName}' : '${_csrf.token}'},
                success : function(args) {
                },
                error : function(e) {
                    alert(e.responseText);
                }
            });
        }
    }
</script>