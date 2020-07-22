<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/jsp-header.jspf"%>

<script type="text/javascript" src="${_resourceURL}static/js/validator.js"></script>
<script type="text/javascript" src="${_resourceURL}static/js/member/login.js"></script>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<input type="hidden" name="pageNo"  id="pageNo" value=""/>

<!-- layerpopup - 쿠폰 적용 가능 상품 -->
<article id="layerPopupCouponProduct" class="layer-popup coupongoods-pop">
    <section class="layer-popup-cont" tabindex="0">
        <h2>쿠폰 적용 가능 상품</h2>
        <div class="layer-cont scroll">

            <div class="coupongoods-popWrap">

                <!-- 검색S -->
                <div class="couponFindSrch">
                    <!-- select -->
                    <div class="select-style02 d_select">
                        <button type="button" class="d_select_sel" id="cpnSelectedBtn" mbrCpnNo="" prmNo="" aplGodSecCd=""><span><!-- 2018년 다운 컬렉션 30% 특별세일 쿠폰 --></span></button>
                        <ul>
                            <c:forEach var="listCpnFor" varStatus="status" items="${myCouponListForCouponName}">
                                <li><a href="javascript:;" name="listCpnFor" onclick="javascript:setBtnDataForCpn(this);return false;" mbrCpnNo="${listCpnFor.mbrCpnNo}" prmNo="${listCpnFor.prmNo}" aplGodSecCd="${listCpnFor.aplGodSecCd}">${listCpnFor.prmNm}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <!-- //select -->
                    <div>
                        <!-- select -->
                        <div class="select-style02 d_select">
                            <button type="button" class="d_select_sel" name="srchType"><span><!-- 상품명 --></span></button>
                            <ul>
                                <li><a href="javascript:;" name="searchFor" onclick="javascript:setBtnDataForSearch(this);return false;" value="goodNm">상품명</a></li>
                                <li><a href="javascript:;" name="searchFor" onclick="javascript:setBtnDataForSearch(this);return false;" value="goodNo">상품코드</a></li>
                            </ul>
                        </div>
                        <!-- //select -->
                        <input type="text" placeholder="" class="input-style02" name="srchKeyword">
                        <a href="javascript:;" class="btn sm" onclick="javascript:getCouponGoodList();return false;">검색</a>
                    </div>

                </div>
                <!-- //검색E -->

                <div class="board-list02" id="includeCouponGoodList">
                </div>

            </div>
        </div>
        <div class="layer-popup-close">
            <button type="button" class="d_layer_close">닫기</button>
        </div>
    </section>
</article>
<!-- //layerpopup - 쿠폰 적용 가능 상품 -->

<script type="text/javascript">

    var strParams = {'${_csrf.parameterName}' : '${_csrf.token}'};

    $(document).ready(function(){
        // 쿠폰 적용 가능 상품 조회
        getCouponGoodList();
    });

    // 쿠폰명 세팅
    function setBtnDataForCpn(obj) {
        $(obj).parent().parent().parent().find("button").attr("mbrCpnNo", $(obj).attr("mbrCpnNo"));
        $(obj).parent().parent().parent().find("button").attr("prmNo", $(obj).attr("prmNo"));
        $(obj).parent().parent().parent().find("button").attr("aplGodSecCd", $(obj).attr("aplGodSecCd"));
        $(obj).parent().parent().parent().find("button").find("span").html($(obj).text());
    }

    // 검색조건 세팅
    function setBtnDataForSearch(obj) {
        $(obj).parent().parent().parent().find("button").attr("value", $(obj).attr("value"));
        $(obj).parent().parent().parent().find("button").find("span").html($(obj).text());
    }

    // 쿠폰 적용 가능 상품 조회
    function getCouponGoodList(currentPage){
        if(currentPage == ""){
            currentPage = 1;
        }

        strParams = {
                '${_csrf.parameterName}' : '${_csrf.token}',
                'currentPage' : currentPage,
                'mbrCpnNo' : $("#layerPopupCouponProduct").find("#cpnSelectedBtn").attr("mbrCpnNo"),
                'prmNo' : $("#layerPopupCouponProduct").find("#cpnSelectedBtn").attr("prmNo"),
                'aplGodSecCd' : $("#layerPopupCouponProduct").find("#cpnSelectedBtn").attr("aplGodSecCd"),
                'srchType' : $("#layerPopupCouponProduct").find("[name=srchType]").val(),
                'srchKeyword' : $("#layerPopupCouponProduct").find("[name=srchKeyword]").val()
                };
        $("#includeCouponGoodList").load("/mypage/benefit/include/myCouponGoodListInclude", strParams);
    }

    // 상품 상세 이동
    function goGoodsInfo(godNo) {
        location.href ='/goods/'+godNo+'/view';
    }

</script>
