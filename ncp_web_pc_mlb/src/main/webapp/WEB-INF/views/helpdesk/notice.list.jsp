<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf" %>
<%@ include file="/WEB-INF/views/helpdesk/include/topDiv.jsp"%>

	<form id ="gForm" action ="<c:url value ='/helpdesk/notice/list' />" method="get">
	<input type ="hidden" id ="pageNo" name="pageNo" value ="" />
	<input type ="hidden" id ="searchNoticeCd" name="searchNoticeCd" value ="${searchNoticeCd}" />

	<div class="contain cs" id="contain">
		<div class="container">
			<h2 class="title01"><spring:message code="helpdesk.submain.noti.ttl" text="공지사항" /></h2>

			<main class="contents noticeList-wrap" id="contents">
				<div class="location-contents">
					<p class="location">
						<span>Home</span>
						<span><spring:message code="helpdesk.common.location.lbl" text="고객센터" /></span>
						<strong title="현재 위치"><spring:message code="helpdesk.common.noit.location.lbl" text="공지사항" /></strong>
					</p>
				</div>

				<!-- tab S -->
				<ul class="tab-type02 d_tab">
					<li><a id="All" href="#" onclick="document.location='/helpdesk/notice/list?searchNoticeCd=' "><spring:message code="helpdesk.noti.cd.lbl" text="전체" /></a></li>
	 				<c:forEach var="cd" items="${noticeTitles}" varStatus="status">
       				<li>
						<a  href="#none" id ="${cd.cd}"  onclick="document.location='/helpdesk/notice/list?searchNoticeCd=${cd.cd}'"><span><c:out value ="${cd.cdNm}" /></span></a>
       				</li>
	       			</c:forEach>
				</ul>
				<!--//tab E -->

				<!-- 검색 S -->
				<div class="search-wrap01 right">
					<div class="search-input">
						<input type="search" title="<spring:message code='helpdesk.common.button.search.btn' text='검색' />" placeholder="<spring:message code='helpdesk.noti.search.placeholder.txt' text='궁금하신 내용을 입력해 주세요.' />" id="searchNotice" name="searchNotice" value="${searchNotice}"/>
						<button type="button" onclick="searchNoticeList();"><spring:message code="helpdesk.common.button.search.btn" text="검색" /></button>
					</div>
				</div>
				<!--//검색 E -->

				<!-- table S -->
				<div class="board-list">
					<div class="boardCount">
						<span><spring:message code="helpdesk.noti.list.lbl.total" text="전체" /></span> (<span class="text-color01"><em class="num" id="notiListCnt"></em></span><spring:message code="helpdesk.noti.list.lbl.noticount" text="건" />)
					</div>
					<table>
						<caption>공지사항 번호, 구분, 제목, 조회수, 등록일 정보표.</caption>
						<colgroup>
							<col style="width:100px;">
							<col style="width:120px;">
							<col>
							<col style="width:100px;">
							<col style="width:100px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><spring:message code="helpdesk.common.list.sort.no.txt" text="번호" /></th>
								<th scope="col"><spring:message code="helpdesk.common.list.sort.TP.txt" text="구분" /></th>
								<th scope="col"><spring:message code="helpdesk.common.list.sort.sub.txt" text="제목" /></th>
								<th scope="col"><spring:message code="helpdesk.common.list.sort.count.txt" text="조회수" /></th>
								<th scope="col"><spring:message code="helpdesk.common.list.sort.regdt.txt" text="등록일" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test = "${!empty noticeList}">
									<c:forEach var="notice" varStatus="status" items="${noticeList}">
										<tr>
											<td class="num"><span><c:out value="${notice.rnum}"/></span></td>
											<td class="notiTpCd"><span>
												<ncp:code code="${notice.sysNoti.notiTpCd}" var="tpcd"/>
												<c:out value="${tpcd.cdNm}"/>
											</td></span>
											<td class="tleft">
												<span><a href="<c:url value='/helpdesk/notice/view/${notice.sysNoti.notiSn}?searchNoticeCd=${searchNoticeCd}&searchNotice=${searchNotice}'/>"><c:out value ="${notice.sysNoti.notiSj}"/></a></span>
											</td>
											<td class="inqirenum">
												<c:out value="${notice.sysNoti.inqireNum}"/>
											</td>
											<td class="date"><c:out value="${notice.noticeRegDt}" /></td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="5" class="no-result"><spring:message code="helpdesk.submain.noti.nonoti.msg" text="공지사항이 없습니다." /></td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
					<c:if test="${!empty noticeList}">
						<div class="page">
							<c:if test="${currentPage > 1 }">
								<a class="first" href="javascript:goNoticeList('1')" _onclick="return false;" title="첫 페이지">Prev</a>
							</c:if>
							<c:if test="${preFlag }">
								<a class="prev" href="javascript:goNoticeList('${displayPrePage}')" _onclick="return false;" title="이전 페이지">이전 페이지</a>
							</c:if>
							<span>
							<c:forEach var="i" begin="${displayBeginPage}" end="${displayEndPage}">
								<c:choose>
									<c:when test="${currentPage eq i}">
										<span class="on"><strong title="현재 페이지">${i}</strong></span>
									</c:when>
									<c:otherwise>
										<a href="javascript:goNoticeList('${i}')" ><span title="현재 페이지">${i}</span></a>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							</span>
							<c:if test="${nextFlag }">
								<a class="next" href="javascript:goNoticeList('${displayNextPage}')"  _onclick="return false;" title="다음 페이지">다음 페이지</a>
							</c:if>
							<c:if test="${currentPage < totalPage}">
							<a class="last" href="javascript:goNoticeList('${totalPage}')" _onclick="return false;" title="마지막 페이지">Next</a>
							</c:if>
						</div>
					</c:if>
				</div>
				<!--//table E -->

			</main>

		</div>
	</div>
	</form>
	<script>
	var onCheck ="${searchNoticeCd}";

	$(document).ready(function(){
		 setNotiListCnt('${totalRow}');

	});

	function setNotiListCnt(notiListCnt){
		$("#notiListCnt").empty();
		$("#notiListCnt").append(notiListCnt);
	}

	if(onCheck){
		$("#"+onCheck).parents('li').addClass('on').siblings().removeClass('on');
	} else {
		$("#All").parents('li').addClass('on').siblings().removeClass('on');
	}
	function emptyMark(){
		 return;
	}
	$(document).ready(function(){
		$("#noticeList").attr("class","sel");
	});
	function setUrl(url){
		var strParams = null;
	    strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};
		jsLinkUrlPost(url, strParams);
	}
	function goNoticeList(pageNo){
		if(pageNo == ""){
			pageNo = 1;
		}
		$("#gForm").find('#pageNo').val(pageNo);
		$("#gForm").submit();
	}
	function searchNoticeList(){
		if($('#searchNotice').val()=="") {
			alert('<spring:message code="helpdesk.common.notext.msg"/>');
		} else {
			//encodeURI($("#searchNotice").val(),"UTF-8");
			//$('#searchNotice').val(encodeURI($("#searchNotice").val(),"UTF-8"));
			$("#gForm").submit();
		}
	}
	</script>
