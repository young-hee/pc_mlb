<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/member/login.js"></script>
<script type="text/javascript" src="/javascript/message/mypage_${pageContext.response.locale.language}.js?v=${_version}"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" id="srchMonth" name="srchMonth" value="${myPage.srchMonth}">
<input type="hidden" name="pageNo"  id="pageNo" value=""/>

<div class="contain my lnblist-Wrap" id="contain">
    <div class="container">

        <h2 class="title01">쿠폰함</h2>

        <%@ include file="../include/lnb.jspf" %>

        <main class="contents couponList-wrap" id="contents">

            <div class="location-contents">
                <p class="location">
                    <span>Home</span>
                    <span>마이페이지</span>
                    <span>혜택정보</span>
                    <strong title="현재 위치">쿠폰함</strong>
                </p>
            </div>

            <div class="d_tab02">

                <!-- tab S -->
                <ul class="tab-type01 tab-blockList blockList02">
                    <li class="d_tab02_select on"><a href="javascript:;">사용 가능한 쿠폰</a></li>
                    <li class="d_tab02_select"><a href="javascript:;">사용완료</a></li>
                </ul>
                <!--// tab E -->

                <!-- 사용 가능한 쿠폰 S -->
                <div class="d_tab02_cont" style="display:block;">

                    <!-- table info S -->
                    <div class="tbst-div">
                        <div class="mid fl">
                            <span>전체</span> (<span class="text-color01" id="couponCnt"><!-- 100 --></span>건)
                        </div>
                        <div class="mid fr">
                            <a href="#layerPopupRegCoupon" class="btn fill sm d_layer_open" id="couponRegPop"><span>쿠폰등록</span></a>
                        </div>
                    </div>
                    <!-- //table info E -->

                    <div id="includeCouponList"><!--
                    - 사용 가능한 쿠폰
                    --></div>
                </div>
                <!--// 사용 가능한 쿠폰 E -->

                <!-- 사용완료 / 기간만료 S -->
                <div class="d_tab02_cont">
                    <dl class="period-wrap period-type02">
                        <!-- 기간달력 -->
                        <dt>조회기간</dt>
                        <dd>
                            <a href="#" class="btn sm d_radio_select on" onclick="setMonth('3month');"><span>3개월</span></a>
                            <a href="#" class="btn sm d_radio_select" onclick="setMonth('6month');"><span>6개월</span></a>
                            <input type="text" class="calendar" id="dateStart" value="${myPage.dateStart}" readonly><input type="text" class="calendar" id="dateEnd" value="${myPage.dateEnd}" readonly>
                            <a href="#" class="btn sm" onclick="searchCouponHistList();return false;">검색</a>
                            <p class="calendar-info-txt">시작일로부터 최대 1년 단위로 조회가 가능합니다.</p><!-- 2018-08-13 -->
                        </dd>
                    </dl>

                    <!-- table info S -->
                    <div class="tbst-div">
                        <div class="mid fl">
                            <span>전체</span> (<span class="text-color01" id="couponHistCnt"><!-- 100 --></span>건)
                        </div>
                    </div>
                    <!-- //table info E -->

                    <div id="includeCouponHistList"><!--
                    - 사용완료/기간만료
                    --></div>
                    <%-- <%@ include file="../_inc/paging.jsp" %> --%>
                </div>
                <!--// 사용완료 / 기간만료 E -->

            </div>

            <!-- 쿠폰 발급/사용안내 영역S -->
            <div class="contTxtBox">
                <strong>쿠폰 발급 / 사용</strong>
                <ul class="text-list01">
                    <li>일부 상품은 쿠폰 적용에서 제외될수 있습니다.</li>
                    <li>회원 발급 쿠폰의 내용은 <a href="/mypage/benefit/membershipRateInfo" class="text-color01">회원혜택안내</a>에서 확인하세요.</li>
                    <li>중복불가 쿠폰인 경우 다른 쿠폰과 함께 적용할 수 없습니다.</li>
                    <li>장바구니 쿠폰을 적용한 경우 구매한 상품들의 가격에 비례하여 분할 적용되며, 부분취소/반품 시 분할 할인된 금액을 차감하여 환불됩니다.</li>
                    <li>오프라인 발행한 쿠폰은 쿠폰번호 등록 후 사용해 주세요.</li>
                </ul>
                <strong>쿠폰 소멸</strong>
                <ul class="text-list01">
                    <li>발급 시 부여되는 쿠폰의 사용기간을 따르며, 사용기간이 경과한 쿠폰은 자동으로 소멸됩니다.</li>
                </ul>
            </div>
            <!-- //쿠폰 발급/사용안내 영역E -->

        </main>

    </div>
</div>

<!-- layerpopup - 쿠폰 등록 -->
<article id="layerPopupRegCoupon" class="layer-popup couponRegister-pop">
    <section class="layer-popup-cont" tabindex="0">
        <h2>쿠폰등록</h2>
        <div class="layer-cont scroll">

            <div class="couponRegister-popWrap">

                <!-- 검색S -->
                <div class="mileageFindSrch">
                    <input type="text" id="couponNumber" placeholder="쿠폰번호 입력해주세요." class="input-style02">
                    <a href="javascript:;" class="btn sm" onclick="couponRegister();return false;">쿠폰등록</a>
                </div>
                <!-- //검색E -->

                <ul class="text-list02">
                    <li>등록된 쿠폰은 사용 가능한 쿠폰 목록에서 확인할 수 있습니다.</li>
                </ul>

            </div>
        </div>
        <div class="layer-popup-close">
            <button type="button" class="d_layer_close">닫기</button>
        </div>
    </section>
</article>
<!-- //layerpopup - 쿠폰 등록 -->

<script type="text/javascript">

    setMonth("3month");
    var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};

    $(document).ready(function(){

        // 사용가능쿠폰 조회
        goCouponList();
        // 사용완료/기간만료 조회
        goCouponHistList();

        //쿠폰번호 초기화
        $('#couponRegPop').click(function(){
            $('#couponNumber').val(null);
        });

    });

    // 사용가능쿠폰 조회 function
    function goCouponList(currentPage){
        if(currentPage == ""){
            currentPage = 1;
        }

        strParams = {'${_csrf.parameterName}' : '${_csrf.token}', 'currentPage' : currentPage};

        $.ajax({
            type : "POST",
            async : true,
            url : "/mypage/benefit/include/availableCoupon.ajax",
            data : strParams,
            success : function(data) {
                $('#includeCouponList').html(data);
            },
            error: function(pa_data, status, err) {
                if (pa_data.status == "403") {
                    alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                    location.reload();
                } else {
                    alert("error forward : "+err+" ,status="+status);
                }
            }
        });
    }

    // 사용완료/기간만료 조회 function
    function goCouponHistList(pageNo){
        if(pageNo == ""){
            pageNo = 1;
        }
        strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : $("#dateStart").val() ,'dateEnd' :  $("#dateEnd").val() , 'currentPage' : pageNo};

        $.ajax({
            type : "POST",
            async : true,
            url : "/mypage/benefit/include/doneUsingCoupon.ajax",
            data : strParams,
            success : function(data) {
                $('#includeCouponHistList').html(data);
            },
            error: function(pa_data, status, err) {
                if (pa_data.status == "403") {
                    alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                    location.reload();
                } else {
                    alert("error forward : "+err+" ,status="+status);
                }
            }
        });
    }

    function setMonth(val){
        // TODO : 달력 스크립트에서 날짜 넣는 형식 확인.

        var addMonth = 0;
        if (val == "3month") {
            addMonth = -3;
        }
        else if (val == "6month") {
            addMonth = -6;
        }

        var today = moment().format('YYYY.MM.DD');
        var fromDate = moment().add(addMonth, 'months').format('YYYY.MM.DD');

        $("#dateStart").val(fromDate);
        $("#dateEnd").val(today);
    }

    // 기간 검색
    function searchCouponHistList() {

        // 날자포맷이 yyyy.mm.dd, yyyy-mm-dd, yyyy/mm/dd에 대응
        var regExp = /[\/.-\s]/g;

        // 조회 기간 계산
        var strStartDate = $("#dateStart").val().replace(regExp, "");
        var startDate = new Date(strStartDate.substr(0,4), '' + (parseInt(strStartDate.substr(4,2)) - 1), strStartDate.substr(6,2));

        // 1년후 계산
        startDate.setYear(startDate.getFullYear() + 1);

        var strEndDate = $("#dateEnd").val().replace(regExp, "");
        var endDate = new Date(strEndDate.substr(0, 4), '' + (parseInt(strEndDate.substr(4, 2)) - 1), strEndDate.substr(6, 2));

        // 조회 기간이 1년 이상일 경우에 메세지 표시
        if (startDate < endDate) {
           	alert('시작일로부터 최대 1년 단위로 조회가 가능합니다.');
            return;
        }

        var regExp = /[\s]/g;

        strParams = {'${_csrf.parameterName}' : '${_csrf.token}' , 'dateStart' : $("#dateStart").val().replace(regExp, "") ,'dateEnd' :  $("#dateEnd").val().replace(regExp, "")};

        $.ajax({
            type : "POST",
            async : true,
            url : "/mypage/benefit/include/doneUsingCoupon.ajax",
            data : strParams,
            success : function(data) {
                $('#includeCouponHistList').html(data);
            },
            error: function(pa_data, status, err) {
                if (pa_data.status == "403") {
                	alert("세션이 만료 되었습니다. 다시 로그인 하시기 바랍니다.");
                    location.reload();
                } else {
                    alert("error forward : "+err+" ,status="+status);
                }
            }
        });
    }

    // 사용완료/기간만료 건수
    function setCouponHistCnt(couponHistCnt){
        $("#couponHistCnt").empty();
        $("#couponHistCnt").append(couponHistCnt);
    }




    // 주문내역상세조회
    function jsOrderInfo(ordNo) {
        location.href ="/mypage/order/"+ordNo+"/view";
    }


    // 쿠폰등록
    function couponRegister(){

        /* 쿠폰입력 시 validate 체크 */
        var cpnNo = $('#couponNumber').val();
        var reCpnNo = cpnNo.replace(/ /gi,'');

        if(cpnNo==null || cpnNo=="") {
            alert("쿠폰번호를 입력해주세요.");
            //alertLayer(MESSAGES['mypage.js.coupon.msg.number.input']);
            return false;
        }

        if(cpnNo.length != reCpnNo.length){
            alert("유효한 쿠폰 번호가 아닙니다. 다시 입력해 주세요.");
            //alertLayer(MESSAGES['mypage.js.coupon.msg.number.mis.type']);
            return false;
        }
        /* //쿠폰입력 시 validate 체크 */


        if(confirm("입력하신 쿠폰을 등록하시겠습니까?")) {

            $.ajax({
                type:"post"
                ,url:"/mypage/benefit/issueCoupon.json"
                ,data : "cpnCrtfcCd=" + $('#couponNumber').val()
                ,dataType: "json"
                ,async : true
                ,beforeSend : function(request) {
                    var csrfToken = $('meta[name="_csrf"]').attr('content') || '';
                    var csrfName = $('meta[name="_csrf_header"]').attr('content') || '';
                    request.setRequestHeader(csrfName, csrfToken);
                }
                ,success : function(data) {
                    $('.d_layer_close').click();

                    if (data.resultMsg === "SUCCES") {
                        alert("쿠폰이 등록되었습니다.\n사용 가능한 쿠폰 목록을 확인해 주세요.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.success']);
                        goCouponList();
                    } else if (data.resultMsg === "PRM_STAT_STPGE") {
                        alert("존재하지 않는 쿠폰번호입니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.inexistente']);
                        goCouponList();
                    } else if (data.resultMsg === "ISMCBTW_OFF") {
                        alert("존재하지 않는 쿠폰번호입니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.inexistente']);
                        goCouponList();
                    } else if (data.resultMsg === "TOT_ISSU_QTY_EXCESS") {
                        alert("총 발급수량이 초과되었습니다");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.exceed.total']);
                        return false;
                    } else if (data.resultMsg === "IDBY_ISSU_QTY_EXCESS") {
                        alert("발급수량이 초과되었습니다");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.exceed']);
                        return false;
                    } else if (data.resultMsg === "PROSISSU_SN") {
                        alert("이미발급된 쿠폰번호입니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.already.issue']);
                        return false;
                    } else if (data.resultMsg === "SN_EXST_NOT") {
                        alert("존재하지 않는 쿠폰번호입니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.inexistente']);
                        goCouponList();
                    } else if (data.resultMsg === "ADMIN_INQ") {
                        alert("관리자에게 문의해주시기 바랍니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.admin.inquiry']);
                        return false;
                    } else {
                        alert("존재하지 않는 쿠폰번호입니다.");
                        //alertLayer(MESSAGES['mypage.js.coupon.msg.register.inexistente']);
                        goCouponList();
                    }
                }
                ,error : function(xhr) {
                    alert(xhr.responseText || '처리 중 오류가 발생하였습니다. 다시시도해 주세요.');
                }
                ,complete : function(data) {
                }
            });
        }
    }

</script>

<!-- layerpopup - 쿠폰 적용 가능 상품 -->
<div id="layerPopupCouponProductDiv">
</div>
<!-- //layerpopup - 쿠폰 적용 가능 상품 -->
