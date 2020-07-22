<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../_inc/meta.jsp" %>
</head>
<body>
<style>
body{    
    overflow-y: scroll;
    padding-right: 30px;
    margin-right: -30px
}
.receipt{width:95%;margin:40px auto}
.receipt .page-head{position:relative;margin-bottom:20px}
.receipt .page-head button{width:25px;height:25px;background:url(/static/images/od/ico_print.png) no-repeat 50% / 100% auto;position:absolute;right:0;top:50%;transform:translate(0, -50%)}
.receipt .location{margin-top:0;padding-bottom:45px}
.receipt table>*>tr>*{font-size:13px;vertical-align:middle;letter-spacing:-.03em}
.receipt table>*>tr>td{color:#555}
.receipt table>*>tr>th{color:#000}
.receipt table *.tleft{text-align:left}
.receipt table *.tright{text-align:right}
.receipt table *.tcenter{text-align:center}
.table-receipt>thead>tr>*{border-bottom:1px solid #ccc}
.table-receipt>*>tr>*{padding:16px 8px}
.receipt .board-list{border-top:0;margin-top:50px}
.receipt .board-list>table>*>tr>*{padding:16px!important}
.receipt .board-list>table>tfoot>tr>*{border-bottom:0}
table.nowrap>*>tr>*{white-space:nowrap;text-align:center;padding:10px 0}
.receipt table.nowrap{margin:40px 0 60px}
.receipt table.nowrap>thead>tr>*{background:#faf8f8}
.receipt p{text-align:center}
@media print{
    body{padding-right:0;margin-right:0}
    .receipt{width:100%;margin-bottom:0}
    .receipt .page-head button{display:none!important}
    .receipt p, .receipt table>*>tr>*{font-size:12px}
    .title01{font-size:20px}
    .location{border-bottom-width:2px}
}
</style>
<div class="receipt lnblist-Wrap">
    <div class="page-head">
        <h2 class="title01">거래명세서</h2>
        <button type="button" onclick="window.print();"></button>
    </div>
    <div class="location justify-wrap">
        <p class="right">주문 No. OD201904190078940</p>
    </div>
<table class="table-receipt">
    <caption>결제 정보</caption>
    <colgroup>
            <col style="width:10%">
            <col style="width:40%">
            <col style="width:10%">
            <col style="width:40%">
    </colgroup>	   
    <thead>
        <tr>
            <th>&nbsp;</th>
            <th class="tcenter">주문인 정보</th>
            <th>&nbsp;</th>
            <th class="tcenter">수령인 정보</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td class="tright">이름　: </td>
            <td class="tcenter">유병용</td>
            <td class="tright">이름　: </td>
            <td class="tcenter">유병용</td>
        </tr>
        <tr>
            <td class="tright">이메일　: </td>
            <td class="tcenter">byyoo@fnf.co.kr</td>
            <td class="tright">이메일　: </td>
            <td class="tcenter">byyoo@fnf.co.kr</td>
        </tr>
        <tr>
            <td class="tright">연락처　: </td>
            <td class="tcenter">010-3245-6639</td>
            <td class="tright">연락처　: </td>
            <td class="tcenter">010-3245-6639</td>
        </tr>
        <tr>
            <td class="tright">주소　: </td>
            <td class="tcenter">06138 서울시 강남구 언주로 541 F&F 빌딩</td>
            <td class="tright">주소　: </td>
            <td class="tcenter">06138 서울시 강남구 언주로 541 F&F 빌딩</td>
        </tr>
    </tbody>
</table>  

    <div class="board-list">
        <table>
            <caption>거래명세서</caption>
            <colgroup>
                <col style="width:60px;" />
                <col />
                <col style="width:100px;" />
                <col style="width:100px;" />
                <col style="width:120px;" />
                <col style="width:130px;" />
            </colgroup>
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">상품정보</th>
                    <th class="tright" scope="col">정가</th>
                    <th class="tright" scope="col">판매단가</th>
                    <th scope="col">옵션 / 수량</th>
                    <th class="tright" scope="col">주문금액</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td class="tleft">시카고 컵스 시그니쳐 스몰로고 맨투맨 31MTD2911-02S</td>
                    <td class="tright">59,000원</td>
                    <td class="tright">24,500원</td>
                    <td>95 / 100개</td>
                    <td class="tright">2,450,000원</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td class="tleft">시카고 컵스 시그니쳐 스몰로고 맨투맨 31MTD2911-02S</td>
                    <td class="tright">59,000원</td>
                    <td class="tright">24,500원</td>
                    <td>95 / 100개</td>
                    <td class="tright">2,450,000원</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td class="tleft">시카고 컵스 시그니쳐 스몰로고 맨투맨 31MTD2911-02S</td>
                    <td class="tright">59,000원</td>
                    <td class="tright">24,500원</td>
                    <td>95 / 100개</td>
                    <td class="tright">2,450,000원</td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th class="tright" colspan="6">합계　: 7,895,000원</th>
                </tr>
            </tfoot>
        </table>
    </div>

    <table class="nowrap">
        <colgroup>
            <col />
            <col style="width:20px" />
            <col />
            <col style="width:20px" />
            <col />
            <col style="width:20px" />
            <col />
            <col style="width:20px" />
            <col />
            <col style="width:20px" />
            <col />
            <col style="width:20px" />
            <col />
        </colgroup>
        <thead>
            <tr>
                <th>총 주문금액</th>
                <td>=</td>
                <td>정가</td>
                <td>-</td>
                <td>쿠폰할인</td>
                <td>-</td>
                <td>기타할인</td>
                <td>-</td>
                <td>마일리지사용</td>
                <td>-</td>
                <td>포인트사용</td>
                <td>+</td>
                <td>배송비</td>
            </tr>
        </thead>
        <tbody>
            <tr>
                <th>7,895,000원</th>
                <td>=</td>
                <td>13,740,000</td>
                <td>-</td>
                <td>0</td>
                <td>-</td>
                <td>5,845,000</td>
                <td>-</td>
                <td>0</td>
                <td>-</td>
                <td>0</td>
                <td>+</td>
                <td>0</td>
            </tr>
        </tbody>
    </table>
    
    <p>본 증빙은 부가가치세법 25조의 규정에 의한 영수증으로 사용이 가능합니다.</p>
</div>
</body>
</html>