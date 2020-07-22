<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.plgrim.ncp.framework.commons.ContextService" %>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>
<script type="text/javascript" src="${_resourceURL}static/js/helpdesk/helpdesk.js"></script>

<form id ="gForm" action ="<c:url value ='/helpdesk/faq/list' />" method="post">

    <div class="contain cs" id="contain">
        <div class="container">

            <h2 class="title01">고객센터</h2>

            <main class="contents csMain-wrap" id="contents">

                <div class="location-contents">
                    <p class="location">
                        <span>Home</span><strong title="현재 위치">고객센터</strong>
                    </p>
                </div>

                <!-- 상단탭 S -->
                <div class="csMainTab">
                    <ul>
                        <li><a href="/helpdesk/notice/list">공지사항</a></li>
                        <li>
                            <c:choose>
                                <c:when test='${_locale eq "ko" }'>
                                    <sec:authorize access="isAnonymous()">
                                        <a href="#" onclick="openLayerPopupForLogin('guestCounsel', '/helpdesk/csInquiry/new'); return false;">1:1 문의</a>
                                    </sec:authorize>
                                    <sec:authorize access="isAuthenticated()">
                                        <a href="/helpdesk/csInquiry/new">1:1 문의</a>
                                    </sec:authorize>
                                </c:when>
                                <c:otherwise>
                                    <a href="/helpdesk/csInquiry/new">1:1 문의</a>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <li><a href="/mypage/benefit/membershipRateInfo">회원혜택안내</a></li>
                        <li><a href="/helpdesk/faq/list?searchAllCd=FAQ_EXCHG_RTGOD_INQ">반품/교환 안내</a></li>
                    </ul>
                </div>
                <!--//상단탭 E -->

                <!-- SEARCH S -->
                <div class="search-wrap02">
                    <label for="searchType">FAQ SEARCH</label>
                    <div class="search-input02">
                        <input type="search" name="searchFaq" id="searchFaq" placeholder="궁금하신 내용을 입력해 주세요."><button type="button" onclick="javascript:searchFaqList();">검색</button>
                    </div>
                    <div>
                        <ul class="word-list">
                            <li><a href="javascript:sendQuick('교환');">교환</a></li>
                            <li><a href="javascript:sendQuick('반품');">반품</a></li>
                            <li><a href="javascript:sendQuick('배송');">배송</a></li>
                            <li><a href="javascript:sendQuick('취소');">취소</a></li>
                            <li><a href="javascript:sendQuick('환불');">환불</a></li>
                            <li><a href="javascript:sendQuick('비밀번호');">비밀번호</a></li>
                        </ul>
                    </div>
                </div>
                <!--//SEARCH E -->

                <!-- 자주 묻는 질문 S -->
                <div class="justify-wrap">
                    <h3 class="title01 left">자주 묻는 질문</h3>
                    <a href="/helpdesk/faq/list" class="btn-style07 right"><span>전체보기</span></a>
                </div>
                <div class="board-list accordion-type d_accordion">
                    <table>
                        <caption>자주 묻는 질문 Top 10 번호, 분류, 제목 정보표.</caption>
                        <colgroup>
                            <col style="width:95px;">
                            <col style="width:230px;">
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
                            <c:if test="${empty faqTop10List}">
                                <tr>
                                    <td colspan="3" class="no-result">검색결과가 없습니다. 1:1 고객상담을 이용해 주시면 빠르고 정확히 알려드리겠습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach var="lists" varStatus="status" items="${faqTop10List}">
                                <ncp:code var="cd" code="${lists.csoFaq.faqSectCd}" />
                                <tr>
                                    <td><c:out value="${status.count }" /></td>
                                    <td class="tleft"><c:out value="${cd.cdNm}" /></td>
                                    <td class="ask-wrap">
                                        <p class="inquiry d_accordion_select">
                                            <c:out value="${lists.csoFaq.faqSj}" />
                                        </p>
                                        <div class="reply">
                                            <c:out value="${lists.csoFaq.faqCont}" escapeXml="" />
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--//자주 묻는 질문 E -->

                <!-- 공지사항 S -->
                <div class="justify-wrap">
                    <h3 class="title01 left">공지사항</h3>
                    <a href="/helpdesk/notice/list" class="btn-style07 right"><span>전체보기</span></a>
                </div>
                <div class="board-list">
                    <table>
                        <caption>공지사항 번호, 구분, 제목, 조회수, 등록일 정보표.</caption>
                        <colgroup>
                            <col style="width:100px;">
                            <col style="width:120px;">
                            <col>
                            <col style="width:120px;">
                            <col style="width:120px;">
                        </colgroup>
                        <thead>
                            <tr>
                                <th scope="col">번호</th>
                                <th scope="col">구분</th>
                                <th scope="col">제목</th>
                                <th scope="col">조회수</th>
                                <th scope="col">등록일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${empty noticeNew5List}">
                                <tr>
                                    <td colspan="5" class="no-result">공지사항이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach var ="notice" varStatus = "status" items ="${noticeNew5List}">
                                <ncp:code code="${notice.sysNoti.notiTpCd}" var="tpcd"/>
                                <tr>
                                    <td><c:out value ="${notice.rnum}"/></td>
                                    <td><c:out value ="${tpcd.cdNm}"/></td>
                                    <td class="tleft"><a href="/helpdesk/notice/view/${notice.sysNoti.notiSn}"><c:out value ="${notice.sysNoti.notiSj}"/></a></td>
                                    <td><c:out value ="${notice.sysNoti.inqireNum}"/></td>
                                    <td><c:out value ="${notice.noticeRegDt}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--//공지사항 E -->

                <!-- CONTACT US S -->
                <div class="justify-wrap">
                    <h3 class="title01 left">CONTACT US</h3>
                </div>
                <div class="csMainTXTBox">
                    <ul>
                        <li>
                            <a href="javascript:;">
                                <strong>전화문의</strong>
                                <p>080-807-0012</p>
                            </a>
                        </li>
                        <li>
                            <a href="javascript:;" onclick="goKakaoTalkOpen()">
                                <strong>카카오톡</strong>
                                <p>상담하기</p>
                            </a>
                        </li>
                        <li>
							<sec:authorize access="isAnonymous()">
								<a href="#" onclick="openLayerPopupForLogin('guestGroupCounsel', '/helpdesk/bundleOrder/list'); return false;"><strong>단체구매</strong><p>문의</p></a>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<a href="/helpdesk/bundleOrder/list"><strong>단체구매</strong><p>문의</p></a>
							</sec:authorize>
                        </li>
                    </ul>
                </div>
                <!--//CONTACT US E -->

            </main>

        </div>
    </div>

</form>

<script>
	function searchFaqList(){
		if($('#searchFaq').val()!=""){
		$("#gForm").submit();
		}else{
		//alert("<spring:message code="helpdesk.common.notext.msg"/>");
			alert('검색어를 입력 후, 검색버튼을 눌러 주세요.');
		}
	}
	function sendQuick(txt){
		var rtxt = encodeURI(txt);
		location.href="/helpdesk/faq/list?searchFaq="+rtxt
	}
</script>
