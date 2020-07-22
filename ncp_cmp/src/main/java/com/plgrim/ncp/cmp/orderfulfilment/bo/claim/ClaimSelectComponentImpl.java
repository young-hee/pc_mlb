package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGod;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExtend;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.biz.claim.data.ClaimBoDTO;
import com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimOrdGftSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimRefundListSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClaimSearchDTO;
import com.plgrim.ncp.biz.claim.data.ClmOrdGodAplPrm;
import com.plgrim.ncp.biz.claim.data.ClmPaySumAmtSearchDTO;
import com.plgrim.ncp.biz.claim.data.RefundClmDlvAmtDTO;
import com.plgrim.ncp.biz.claim.data.RefundResultDTO;
import com.plgrim.ncp.biz.claim.result.ClaimListResult;
import com.plgrim.ncp.biz.claim.result.ClaimRefundListResult;
import com.plgrim.ncp.biz.claim.result.DeliveryOrderGoodResult;
import com.plgrim.ncp.biz.claim.result.OrdDetailClmInfoResult;
import com.plgrim.ncp.biz.claim.service.ClaimDelayService;
import com.plgrim.ncp.biz.claim.service.ClaimOrderGoodLackService;
import com.plgrim.ncp.biz.claim.service.ClaimRefundService;
import com.plgrim.ncp.biz.claim.service.ClaimService;
import com.plgrim.ncp.biz.delivery.data.DlvOrdGodInfoDTO;
import com.plgrim.ncp.biz.delivery.data.LgsDlvspPkupDTO;
import com.plgrim.ncp.biz.delivery.service.DeliveryRuleService;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.delivery.service.DeliveryStatusService;
import com.plgrim.ncp.biz.interfaces.service.InfOrdGodErpDstbService;
import com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.data.SysExchgRtDTO;
import com.plgrim.ncp.biz.order.result.OrdGodForClmResult;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.biz.order.service.OrderBoSelectService;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.pay.data.ClmRefundDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundResultDTO;
import com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO;
import com.plgrim.ncp.biz.pay.service.PayBoService;
import com.plgrim.ncp.biz.promotion.service.PromotionService;
import com.plgrim.ncp.biz.vendor.service.ComDmstcDlvCstPlcService;
import com.plgrim.ncp.commons.service.CodeViewService;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional(value = "transactionManager")
@Component
public class ClaimSelectComponentImpl implements ClaimSelectComponent {

    @Autowired
    OrderBoSelectService orderBoSelectService;

    @Autowired
    PayBoService payBoService;

    @Autowired
    ClaimService claimService;

    @Autowired
    ClaimDelayService claimDelayService;

    @Autowired
    ClaimOrderGoodLackService claimOrderGoodLackService;

    @Autowired
    ClaimRefundService claimRefundService;

    @Autowired
    ComDmstcDlvCstPlcService comDmstcDlvCstPlcService;

    @Autowired
    CodeViewService codeViewService;

    @Autowired
    DeliveryStatusService deliveryStatusService;

    @Autowired
    DeliveryService deliveryService;

    @Autowired
    DeliveryRuleService deliveryRuleService;

    @Autowired
    InfOrdGodErpDstbService infOrdGodErpDstbService;

    @Autowired
    PromotionService promotionService;

    @Autowired
    OrderSelectService orderSelectService;


    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#getOrdGodClmList(com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO)
     */
    @Override
    public List<OrdGodForClmResult> getOrdGodClmList(
            OrdClmAplPrmDTO ordClmAplPrmDTO) {
        return orderBoSelectService.getOrdGodClmList(ordClmAplPrmDTO);
    }

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#getOrdGodClmListCount(com.plgrim.ncp.biz.order.data.OrdClmAplPrmDTO)
     */
    @Override
    public int getOrdGodClmListCount(OrdClmAplPrmDTO ordClmAplPrmDTO) {
        // TODO Auto-generated method stub
        return orderBoSelectService.getOrdGodClmListCount(ordClmAplPrmDTO);
    }

    @Override
    public List<Pay> selectOrdClmPay(Pay pay){
        List<Pay> ordPayList = payBoService.selectOrdClmPay(pay);//주결제
        return ordPayList;
    }


    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#selectOrdGodForClmRefund(com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO)
     */
    @Override
    public ClmRefundDTO selectOrdGodForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) throws Exception {
        // TODO Auto-generated method stub
        //return payBoService.selectInfOrdGodErpDstbForClmRefund(clmRefundSearchDTO);

        //1.ord_god 테이블에서 base 데이터를 뽑아온다.
        OrdClmAplPrmDTO ordClmAplPrmDTO = new OrdClmAplPrmDTO();
        List<String> ordGodTurnList = null;
        ordClmAplPrmDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());

        if(clmRefundSearchDTO.getOrdGodList()!=null&&clmRefundSearchDTO.getOrdGodList().size()!=0){
            ordGodTurnList = new ArrayList<String>();
            for(OrdGodExtend ordGod : clmRefundSearchDTO.getOrdGodList()){
                ordGodTurnList.add(ordGod.getOrdGodTurn()+"");
            }
        }

        ordClmAplPrmDTO.setOrdGodTurnList(ordGodTurnList);
        List<OrdGod> ordGodBaseList = orderBoSelectService.selectOrdGodTypeCheck(ordClmAplPrmDTO);		   // base info list 추출

        Pay pay = new Pay();
        pay.setDealTpCd("PAY_COMPT");	//결제완료
        pay.setPayTpCd("ORD");//주문시 결제정보
        pay.setOrdNo(clmRefundSearchDTO.getOrdNo());
        List<Pay> ordPayList = payBoService.selectOrdClmPay(pay);//주결제


        //2.base 데이터로 각 실제 결제금액 할인금액 포인트 사용금액을 sum 해서 지역변수(DTO)에 담는다.
        //전체취소일 경우 base list를 모두 항목별로 sum 해서 결과 DTO에 set한다.
        //부분취소일경우 clmRefundSearchDTO.getOrdGodList().size() 가 0이 아니면
        //getOrdGodList() 만큼 for 문 돌면서 base 를수량으로 나눠 수량1개당 가격을 구해서 받은 파라메터 수량으로 곱한다
        RefundResultDTO refundResultDTO = new RefundResultDTO();
        refundResultDTO  = getRefundResult(ordGodBaseList,ordGodTurnList,clmRefundSearchDTO,ordPayList);



        //3.주문할인이 깨지는지 체크해서 깨지면 환불금액에서 차감한다. 장바구니 쿠폰 > 쿠폰테이블 >쿠폰사용최소결제금액
        //구매금액이 최소 결제금액보다 작으면 장바구니쿠폰 적용 취소하고 환불금액에서 차감
        //주문번호로 해당주문이 적용받은 장바구니 쿠폰이 있다면 쿠폰정보를 받는다.
        //기획 확인결과 부분취소에서 장바구니 쿠폰의 조건이 깨져도 할인 유지!!!

        //5.무료배송여부 확인 로직  4.배송비 환불 추출 /  무료배송쿠폰 > 쿠폰테이블 >쿠폰사용최소결제금액
        //구매금액이 최소 결제금액보다 작으면 환불금에서 배송비 차감
        //무료배송쿠폰 확인
        //자사 업체의 무료배송정책 깨지는지 확인
        //유료배송이면 //한 배송지에 대한 모든 상품이 취소되었다면 배송비 환불해줌
        //무료배송이면 //배송지 별 잔여 결제금액을 구해서 국내배송/해외배송 에 따라 배송비 면제 결제금액과 비교하여 무료배송이 깨지면 환불금액에서 차감
        refundResultDTO = getDlvCstRefundAmt(refundResultDTO,clmRefundSearchDTO,ordGodTurnList);






        //화면에 보내줄 DTO List<ClmRefundResultDTO> 로 가공해서 리턴한다.
        ClmPaySumAmtSearchDTO clmPaySumAmtSearchDTO = new ClmPaySumAmtSearchDTO();
        clmPaySumAmtSearchDTO.setClmTpCd("PART_CNCL");
        clmPaySumAmtSearchDTO.setClmStatCd("COMPT");
        clmPaySumAmtSearchDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
        //(주문번호로 클레임 부분취소 완료된 클레임번호 추출하여 연결테이블 조회해서 상품 순번과 클래임 수량 구해서 이전 클레임가격 추출하여 sum)
        RefundResultDTO befordRefundDTO = claimService.getClmPaySumAmt(clmPaySumAmtSearchDTO);

        List<ClmRefundResultDTO> result = new ArrayList<ClmRefundResultDTO>();
        ClmRefundResultDTO clmRefundResultDTO = null;
        int refundPrdFee=0;	//실제 결제금액 + 포인트 결제금액(상품결제환불금액)
        for(Pay p : ordPayList){
            clmRefundResultDTO = new ClmRefundResultDTO();
            clmRefundResultDTO.setPaymncdNm(codeViewService.getCode("PAY_MN", p.getPayMnCd(), "KOR").getCdNm());//결제수단
            clmRefundResultDTO.setPaymncd(p.getPayMnCd());//결제수단
            if(null == p.getUpperPayNo() || "".equals(p.getUpperPayNo())){//주결제금액
                clmRefundResultDTO.setPaycrncypayamt(p.getPayCrncyPayAmt()+"");	//최초결제금액
                clmRefundResultDTO.setRemainpayamt(""+(p.getPayCrncyPayAmt().intValue()-Integer.parseInt(befordRefundDTO.getPayExchgRtCrncyAmtSum())-Integer.parseInt(refundResultDTO.getPayExchgRtCrncyAmtSum())));	//잔여결제금액
                clmRefundResultDTO.setBeforePayamt(befordRefundDTO.getPayExchgRtCrncyAmtSum());	//이전취소금액
                clmRefundResultDTO.setPrc(refundResultDTO.getPayExchgRtCrncyAmtSum());	//환불금액
            }else if("MBSH_PNT_PAY".equals(p.getPayMnCd())){	//맴버쉽포인트
                clmRefundResultDTO.setPaycrncypayamt(p.getPayCrncyPayAmt()+"");	//최초결제금액
                clmRefundResultDTO.setRemainpayamt(""+(p.getPayCrncyPayAmt().intValue()-Integer.parseInt(befordRefundDTO.getUnityPntUseAmtSum())-Integer.parseInt(refundResultDTO.getUnityPntUseAmtSum())));	//잔여결제금액
                clmRefundResultDTO.setBeforePayamt(befordRefundDTO.getUnityPntUseAmtSum());	//이전취소금액
                clmRefundResultDTO.setPrc(refundResultDTO.getUnityPntUseAmtSum());	//환불금액
            }
            result.add(clmRefundResultDTO);
        }
        refundPrdFee += Integer.parseInt(refundResultDTO.getPayExchgRtCrncyAmtSum()) + Integer.parseInt(refundResultDTO.getUnityPntUseAmtSum());
        ClmRefundDTO clmRefundDTO = new ClmRefundDTO();
        clmRefundDTO.setRefundResultDTO(result);
        clmRefundDTO.setRefundDlvFee(refundResultDTO.getRefundDlvFee());
        clmRefundDTO.setRefundPrdFee(refundPrdFee+"");
        return clmRefundDTO;
    }


    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#selectOrdGodErpForClmRefund(com.plgrim.ncp.biz.pay.data.ClmRefundSearchDTO)
     */
    @Override
    public ClmRefundDTO selectOrdGodErpForClmRefund(ClmRefundSearchDTO clmRefundSearchDTO) throws Exception {

        //1.ord_god 테이블에서 base 데이터를 뽑아온다.
        OrdClmAplPrmDTO ordClmAplPrmDTO = new OrdClmAplPrmDTO();
        List<String> ordGodTurnList = new ArrayList<String>();;
        ordClmAplPrmDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
        List<InfOrdGodErpDstbExtend> ordGodBaseList = new ArrayList<InfOrdGodErpDstbExtend>();//		!!  BASE LIST
        InfOrdGodErpDstbExtend searchInfOrdGodErpDstb = new InfOrdGodErpDstbExtend();
        searchInfOrdGodErpDstb.setOrdNo(clmRefundSearchDTO.getOrdNo());
        searchInfOrdGodErpDstb.setClmNo(clmRefundSearchDTO.getClmNo());

        if(clmRefundSearchDTO.getOrdGodList()!=null&&clmRefundSearchDTO.getOrdGodList().size()!=0){

            //클레임접수 대상의 주문상품 리스트
            for(OrdGodExtend ordGod : clmRefundSearchDTO.getOrdGodList()){

            	//주문상품순번 리스트 생성
                ordGodTurnList.add(ordGod.getOrdGodTurn()+"");

                searchInfOrdGodErpDstb.setOrdGodTurn(ordGod.getOrdGodTurn());
                searchInfOrdGodErpDstb.setDlivyDrctGodNo(ordGod.getDlivyDrctGodNo());
                searchInfOrdGodErpDstb.setQty(ordGod.getOrdQty()+"");

                //부분취소 - 주문번호, 상품순번, 출고지시번호, 수량
                //인터페이스주문상품ERP 분배 기준 리스트 생성
                ordGodBaseList.addAll( infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb));
            }
        }else{

        	//전체취소 - 주문번호로 조회
        	ordGodBaseList.addAll( infOrdGodErpDstbService.selectInfOrdGodErpDstbForRefund(searchInfOrdGodErpDstb));
        }

        //주문상품순번 세팅
        ordClmAplPrmDTO.setOrdGodTurnList(ordGodTurnList);



        Pay pay = new Pay();
        pay.setDealTpCd("PAY_COMPT");	//결제완료
        pay.setPayTpCd("ORD");			//주문시 결제정보
        pay.setOrdNo(clmRefundSearchDTO.getOrdNo());
        //해당 주문번호의 결제완료인 주문결제정보 조회.
        List<Pay> ordPayList = payBoService.selectOrdClmPay(pay);


        Ord ord = orderSelectService.selectOrdEntity(clmRefundSearchDTO.getOrdNo());

        // 각 환불 금액을 합산하여 계산하며, 배송비 환불여부를 위한 배송지별 결제환불금액을 계산해온다.
        RefundResultDTO refundResultDTO = new RefundResultDTO();
        refundResultDTO  = getRefundResultByOrdGodErp(ordGodBaseList,clmRefundSearchDTO,ordPayList,ord);

        //5.무료배송여부 확인 로직  4.배송비 환불 추출 /  무료배송쿠폰 > 쿠폰테이블 >쿠폰사용최소결제금액
        //구매금액이 최소 결제금액보다 작으면 환불금에서 배송비 차감
        //무료배송쿠폰 확인
        //자사 업체의 무료배송정책 깨지는지 확인
        //유료배송이면 //한 배송지에 대한 모든 상품이 취소되었다면 배송비 환불해줌
        //무료배송이면 //배송지 별 잔여 결제금액을 구해서 배송비 면제 결제금액과 비교하여 무료배송이 깨지면 환불금액에서 차감

        int remainCnt = orderSelectService.selectAplPrmGodDlvFeeCnt(clmRefundSearchDTO.getOrdNo());
        if( ord.getDlvCstCpnDcSumAmt().intValue() > 0 && remainCnt == 0){//해당주문번호로 주문상품적용프로모션에 상품배송비쿠폰이 없으면 조건 추가필요
        	//배송비쿠폰 PG사배송비청구금액 0원 강제처리
        	refundResultDTO.setRefundDlvFee("0");
        }
        else{
        	refundResultDTO = getDlvCstRefundAmtByOrdGodErp(refundResultDTO,clmRefundSearchDTO,ordGodTurnList);
        }

        //화면에 보내줄 DTO List<ClmRefundResultDTO> 로 가공해서 리턴한다.
        ClmPaySumAmtSearchDTO clmPaySumAmtSearchDTO = new ClmPaySumAmtSearchDTO();
        clmPaySumAmtSearchDTO.setClmTpCd("PART_CNCL");
        clmPaySumAmtSearchDTO.setClmStatCd("COMPT");
        clmPaySumAmtSearchDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
        //(주문번호로 클레임 부분취소 완료된 클레임번호 추출하여 연결테이블 조회해서 상품 순번과 클래임 수량 구해서 이전 클레임가격 추출하여 sum)
        RefundResultDTO befordRefundDTO = claimService.getClmPaySumAmt(clmPaySumAmtSearchDTO);

        List<ClmRefundResultDTO> result = new ArrayList<ClmRefundResultDTO>();
        ClmRefundResultDTO clmRefundResultDTO = null;
        int refundPrdFee=0;	//실제 결제금액 + 포인트 결제금액(상품결제환불금액)
        BigDecimal refundPrdFeeEx = new BigDecimal("0");	//실제 결제금액 + 포인트 결제금액(상품결제환불금액)

        for(Pay p : ordPayList){
            clmRefundResultDTO = new ClmRefundResultDTO();
            clmRefundResultDTO.setPaymncdNm(codeViewService.getCode("PAY_MN", p.getPayMnCd(), "KOR").getCdNm());//결제수단
            clmRefundResultDTO.setPaymncd(p.getPayMnCd());//결제수단
            clmRefundResultDTO.setMpayMnYn(p.getMpayMnYn());
            if("Y".equals(p.getMpayMnYn())){//주결제
            	int mainPayWithDlvFee = Integer.parseInt(refundResultDTO.getRefundDlvFee());

                clmRefundResultDTO.setPaycrncypayamt(p.getPayCrncyPayAmt()+"");	//최초결제금액
                clmRefundResultDTO.setRemainpayamt(String.valueOf(p.getPayCrncyPayAmt().intValue()-Integer.parseInt(befordRefundDTO.getStdrCrncyUntPrcSum())-(Integer.parseInt(refundResultDTO.getPayExchgRtCrncyAmtSum())+mainPayWithDlvFee)));	//잔여결제금액
                clmRefundResultDTO.setBeforePayamt(befordRefundDTO.getStdrCrncyUntPrcSum());	//이전취소금액
                clmRefundResultDTO.setPrc((Integer.parseInt(refundResultDTO.getPayExchgRtCrncyAmtSum())+mainPayWithDlvFee)+"");	//환불금액
                result.add(clmRefundResultDTO);
            }else if("MBSH_PNT_PAY".equals(p.getPayMnCd())){	//마일리지
                clmRefundResultDTO.setPaycrncypayamt(p.getPayCrncyPayAmt()+"");	//최초결제금액
                clmRefundResultDTO.setRemainpayamt(""+(p.getPayCrncyPayAmt().intValue()-Integer.parseInt(befordRefundDTO.getUnityPntUseAmtSum())-Integer.parseInt(refundResultDTO.getUnityPntUseAmtSum())));	//잔여결제금액
                clmRefundResultDTO.setBeforePayamt(befordRefundDTO.getUnityPntUseAmtSum());	//이전취소금액
                clmRefundResultDTO.setPrc(refundResultDTO.getUnityPntUseAmtSum());	//환불금액
                result.add(clmRefundResultDTO);
            }else if("WEB_PNT_PAY".equals(p.getPayMnCd())){	//웹포인트
                clmRefundResultDTO.setPaycrncypayamt(p.getPayCrncyPayAmt()+"");	//최초결제금액
                clmRefundResultDTO.setRemainpayamt(""+(p.getPayCrncyPayAmt().intValue()-Integer.parseInt(befordRefundDTO.getWebpntUseAmtSum())-Integer.parseInt(refundResultDTO.getWebpntUseAmtSum())));	//잔여결제금액
                clmRefundResultDTO.setBeforePayamt(befordRefundDTO.getWebpntUseAmtSum());    //이전취소금액
                clmRefundResultDTO.setPrc(refundResultDTO.getWebpntUseAmtSum());	//환불금액
                result.add(clmRefundResultDTO);
            }

        }

        refundPrdFee += Integer.parseInt(refundResultDTO.getStdrCrncyUntPrcSum()) + Integer.parseInt(refundResultDTO.getUnityPntUseAmtSum()) + Integer.parseInt(refundResultDTO.getWebpntUseAmtSum());
        refundPrdFeeEx.add(new BigDecimal(refundResultDTO.getPayExchgRtCrncyAmtSum()));

        ClmRefundDTO clmRefundDTO = new ClmRefundDTO();
        
        clmRefundDTO.setStdrCrncySumAmt(Integer.parseInt(refundResultDTO.getStdrCrncyUntPrcSum()));
        clmRefundDTO.setRefundDlvFee(refundResultDTO.getRefundDlvFee());
        clmRefundDTO.setRefundPrdFee(refundPrdFee+"");
        clmRefundDTO.setUnityPntAccmlAmtSum(refundResultDTO.getUnityPntAccmlAmtSum());		// 멤버십포인트 적립취소
        clmRefundDTO.setWebpntAccmlUntPrcSum(refundResultDTO.getWebpntAccmlUntPrcSum());	// P포인트 적립취소
        clmRefundDTO.setUnityPntUseAmtSum(refundResultDTO.getUnityPntUseAmtSum());			// 멤버십포인트 환급

        clmRefundDTO.setSalePrcSumAmt(refundResultDTO.getISalePrcSumAmt());
        clmRefundDTO.setBundleDcSumAmt(refundResultDTO.getIBundleDcSumAmt());
        clmRefundDTO.setCrsDcSumAmt(refundResultDTO.getICrsDcSumAmt());
        clmRefundDTO.setGodCpnDcSumAmt(refundResultDTO.getIGodCpnDcSumAmt());
        clmRefundDTO.setBskCpnDcSumAmt(refundResultDTO.getIBskCpnDcSumAmt());
        
        clmRefundDTO.setRefundResultDTO(result);


        return clmRefundDTO;
    }






   /*
    * (non-Javadoc)
    * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#selectLgsDlvspPkup(com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp)
    */
    public LgsDlvspPkupDTO selectLgsDlvspPkup(LgsDlvspExtend lgsDlvsp){
    	return deliveryService.selectLgsDlvspPkup(lgsDlvsp);
    }



    /**
     * ri 단위로 a 를 반올림 한다.
     * @param a
     * @param ri
     * @return
     */
    private int roundIntMethod(double a,double ri){
    	double reint = Math.ceil(a/ri)*ri;
        return (int) (reint);
    }

    /**
     * baseList 로 각 금액을 계산해서 추출한다.
     * @param ordGodBaseList
     * @param ordGodTurnList
     * @param clmRefundSearchDTO
     * @return
     */
    private RefundResultDTO getRefundResult(List<OrdGod> ordGodBaseList,List<String> ordGodTurnList,ClmRefundSearchDTO clmRefundSearchDTO,List<Pay> ordPayList){
        RefundResultDTO refundResultDTO = new RefundResultDTO();
        int payExchgRtCrncyAmtSum = 0;	//결제금액
        int unityPntUseAmtSum = 0;	//통합포인트 맴버쉽포인트
        int bskCpnDcAmtSum = 0;	//장바구니쿠폰

        RefundClmDlvAmtDTO refundClmDlvAmtDTO = new RefundClmDlvAmtDTO();
        List<RefundClmDlvAmtDTO> refundClmDlvAmtDTOList = new ArrayList<RefundClmDlvAmtDTO>();

        int dlvPcupspTurn = -1;
        int clmQty = 0;	//클레임 상품 수량 지역변수
        int roundInt = 10;	//가격 반올림 할 단위
        int rownum = 0;
        if(ordGodTurnList !=null && ordGodTurnList.size()!=0){//부분취소
            for(OrdGod ordGod : ordGodBaseList){
                rownum++;
                for(OrdGod ordGodForQty : clmRefundSearchDTO.getOrdGodList()){
                    if(ordGod.getOrdGodTurn().equals(ordGodForQty.getOrdGodTurn())){
                        clmQty = ordGodForQty.getOrdQty().intValue();	//파라메터로 받은 해당 ordGodTurn 의 클레임 접수 상품의 수량
                    }
                }
                //한 ordGod의 수량만큼 가격을 나누고 클래임 접수 수량만큼 곱해서 한 상품의 환불금액을 누적시킨다.
                payExchgRtCrncyAmtSum += roundIntMethod((ordGod.getPayExchgRtCrncyAmt().intValue() / ordGod.getOrdQty().intValue())*(clmQty),roundInt);
                unityPntUseAmtSum += roundIntMethod((ordGod.getUnityPntUseAmt().intValue() / ordGod.getOrdQty().intValue())*(clmQty),roundInt);
                bskCpnDcAmtSum += roundIntMethod((ordGod.getBskCpnDcAmt().intValue() / ordGod.getOrdQty().intValue())*(clmQty),roundInt);

                if(dlvPcupspTurn==-1){
                    dlvPcupspTurn = ordGod.getDlvPcupspTurn();
                }

                if(dlvPcupspTurn!=ordGod.getDlvPcupspTurn()){
                    refundClmDlvAmtDTOList.add(refundClmDlvAmtDTO);
                    refundClmDlvAmtDTO = new RefundClmDlvAmtDTO();
                    dlvPcupspTurn = ordGod.getDlvPcupspTurn();
                }

                refundClmDlvAmtDTO.setDlvPcupspTurn(ordGod.getDlvPcupspTurn()); //배송지 순번 셋팅
                refundClmDlvAmtDTO.setClmDlvAmt(payExchgRtCrncyAmtSum+refundClmDlvAmtDTO.getClmDlvAmt());
                if(ordGodBaseList.size()==rownum){//마지막DTO를 add 해준다.
                    refundClmDlvAmtDTOList.add(refundClmDlvAmtDTO);
                }
                refundResultDTO.setClmDlvAmtDTOList(refundClmDlvAmtDTOList);//배송지별 환불해줄 금액



            }
        }else{//전체취소
            payExchgRtCrncyAmtSum=0;
            unityPntUseAmtSum=0;
            bskCpnDcAmtSum=0;
            for(Pay ordPay : ordPayList){
                if("".equals(ordPay.getUpperPayNo()) || ordPay.getUpperPayNo()==null){//주결제
                    payExchgRtCrncyAmtSum=ordPay.getPayCrncyPayAmt().intValue();
                }else if("UNITY_SAV_MNY_PAY".equals(ordPay.getPayMnCd())){//통합포인트 맴버쉽포인트
                    unityPntUseAmtSum=ordPay.getPayCrncyPayAmt().intValue();
                }
            }
        }

        refundResultDTO.setPayExchgRtCrncyAmtSum(payExchgRtCrncyAmtSum+"");//환불금액 주결제
        refundResultDTO.setUnityPntUseAmtSum(unityPntUseAmtSum+"");//환불금액 맴버쉽포인트
        refundResultDTO.setBskCpnDcAmtSum(bskCpnDcAmtSum+"");
        return refundResultDTO;
    }

    /**
     * 한 배송지의 ord_god에서 getPayExchgRtCrncyAmt + 포인트 사용금액 을 sum 한 금액을 리턴해준다.
     * @param ordGodDlvList
     * @return
     */
    private int getOrdGodDlvAmtSum(List<OrdGod> ordGodDlvList) {
        int payExchgRtCrncyAmt = 0;
        int unityPntUseAmt = 0;
        int evtPntUseAmt = 0;
        for(OrdGod dto : ordGodDlvList){
            payExchgRtCrncyAmt += dto.getPayExchgRtCrncyAmt().intValue();
            unityPntUseAmt += dto.getUnityPntUseAmt().intValue();
            evtPntUseAmt += dto.getEvtPntUseAmt().intValue();
        }
        return payExchgRtCrncyAmt+unityPntUseAmt+evtPntUseAmt;
    }

    /**
     * 환불 배송비를 구한다.
     * @param refundResultDTO
     * @param clmRefundSearchDTO
     * @param ordGodTurnList
     * @return
     * @throws Exception
     */
    private RefundResultDTO getDlvCstRefundAmt(RefundResultDTO refundResultDTO,ClmRefundSearchDTO clmRefundSearchDTO,List<String> ordGodTurnList) throws Exception{

        int dlvFee = 0;
        int ordPayAmt = 0;
        int finalAmt = 0;
        if(ordGodTurnList!=null&&ordGodTurnList.size()!=0){//부분취소

            OrderBoDTO orderBoDTO= new OrderBoDTO();
            orderBoDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
            List<LgsDlvspExtend> lgsDlvspExtendList = orderBoSelectService.selectDlvspList(orderBoDTO);
            for(LgsDlvspExtend dto : lgsDlvspExtendList){

                //해당 주문의 이전 부분취소 완료된 클레임의 금액을 구해온다.
                ClmPaySumAmtSearchDTO clmPaySumAmtSearchDTO = new ClmPaySumAmtSearchDTO();
                clmPaySumAmtSearchDTO.setClmTpCd("PART_CNCL");
                clmPaySumAmtSearchDTO.setClmStatCd("COMPT");
                clmPaySumAmtSearchDTO.setDlvPcupspTurn(dto.getDlvPcupspTurn()+"");
                clmPaySumAmtSearchDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
                RefundResultDTO befordRefundDTO = claimService.getClmPaySumAmt(clmPaySumAmtSearchDTO);//(주문번호로 클레임 부분취소 완료된 클레임번호 추출하여 연결테이블 조회해서 상품 순번과 클래임 수량 구해서 이전 클레임가격 추출하여 sum)
                int clmPaySumAmt = Integer.parseInt(befordRefundDTO.getPayExchgRtCrncyAmtSum());
                //배송지별 (ord_god로 결제금액 sum) - (주문번호로 클레임 부분취소 완료된 클레임번호 추출하여 연결테이블 조회해서 상품 순번과 클래임 수량 구해서 이전 클레임가격 추출하여 sum) < 이게 현제 결제금액
                OrdClmAplPrmDTO ordClmAplPrmDTO2 = new OrdClmAplPrmDTO();
                ordClmAplPrmDTO2.setOrdNo(clmRefundSearchDTO.getOrdNo());
                ordClmAplPrmDTO2.setDlvPcupspTurn(dto.getDlvPcupspTurn()+"");
                List<OrdGod> ordGodDlvList = orderBoSelectService.selectOrdGodTypeCheck(ordClmAplPrmDTO2);
                ordPayAmt = getOrdGodDlvAmtSum(ordGodDlvList);//배송지별 (ord_god로 결제금액 sum)

                if(dto.getRealityDlvCst() == null){
                	dto.setRealityDlvCst(0);
                }
                if(dto.getDlvCstCpnDcAmt()==null){
                	dto.setDlvCstCpnDcAmt(0);
                }
                if(dto.getRealityDlvCst()==0 && dto.getDlvCstCpnDcAmt()==0){ //무료배송이면서 무료배송 쿠폰이 아니라 정책으로 무료배송이 된 경우 (쿠폰은 부분취소에서는 복원 안해준다.)
                    for(RefundClmDlvAmtDTO dlvClmAmt : refundResultDTO.getClmDlvAmtDTOList()){	// 배송지 수 만큼 돈다.
                        if(dlvClmAmt.getDlvPcupspTurn()==dto.getDlvPcupspTurn()){	//
                            finalAmt = ordPayAmt - clmPaySumAmt - dlvClmAmt.getClmDlvAmt();//최종 결제금액(최초결제금액-이전클래임으로 차감된결제금액-앞으로 차감될 결제금액)
                            if(finalAmt<=0){//한 배송지에 모든 상품이 취소된경우
                                dlvFee += dto.getRealityDlvCst();	//배송비 환불금액에 더한다.
                            }else{//정책확인해서 깨지면 환불금에서 차감 ex)배송비-2500
                                if("KR".equals(dto.getAddrseNationCd())){//국내배송
                                    ComDmstcDlvCstPlc comDmstcDlvCstPlc = new ComDmstcDlvCstPlc();
                                    comDmstcDlvCstPlc.setDmstcDlvCstPlcSn(dto.getDmstcDlvCstPlcSn());
                                    comDmstcDlvCstPlc = comDmstcDlvCstPlcService.selectComDmstcDlvCstPlc(comDmstcDlvCstPlc);
                                    if(comDmstcDlvCstPlc.getDmstcDlvCstExmStdrAmt().intValue() > finalAmt){//최종 결제금액보다 국내 배송비 면제 기준금액이 크면 정책이 깨진거임
                                        dlvFee += dto.getRealityDlvCst()*-1;	//환불배송비에 -1 을 곱해준다.
                                    }
                                }else{//해외배송
                                    ComOvseaDlvCstPlc comOvseaDlvCstPlc = new ComOvseaDlvCstPlc();
                                    comOvseaDlvCstPlc.setOvseaDlvCstPlcSn(dto.getOvseaDlvCstPlcSn());
                                    comOvseaDlvCstPlc = comDmstcDlvCstPlcService.selectComOvseaDlvCstPlc(comOvseaDlvCstPlc);
                                }
                            }
                        }
                    }
                    //현제 결제금액 - 현제 클레임 신청 해서 환불될 금액 = 최종 결제금액
                    //최종 결제금액 - 배송비 면제 정책 결제금액 = 양수면 무료 유지 음수면 환불금액에서 차감

                }else{//유료배송이면
                    for(RefundClmDlvAmtDTO dlvClmAmt : refundResultDTO.getClmDlvAmtDTOList()){	// 배송지 수 만큼 돈다.
                        if(dlvClmAmt.getDlvPcupspTurn()==dto.getDlvPcupspTurn()){	//
                            finalAmt = ordPayAmt - clmPaySumAmt - dlvClmAmt.getClmDlvAmt();//최종 결제금액(최초결제금액-이전클래임으로 차감된결제금액-앞으로 차감될 결제금액)
                            if(finalAmt<=0){//한 배송지에 모든 상품이 취소된경우
                                dlvFee += dto.getRealityDlvCst();	//배송비 환불금액에 더한다.
                            }
                        }
                    }
                }
            }
        }else{//전체취소
            //무료배송//배송비가 0원이면 환불배송비도 0원
            //유료배송//배송비가 있으면 배송지별 합쳐서 배송비 환불
            OrderBoDTO orderBoDTO= new OrderBoDTO();
            orderBoDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
            List<LgsDlvspExtend> lgsDlvspExtendList = orderBoSelectService.selectDlvspList(orderBoDTO);
            for(LgsDlvspExtend dto : lgsDlvspExtendList){
                if(dto.getRealityDlvCst()!=null){
                    dlvFee += dto.getRealityDlvCst();
                }
            }
        }
        refundResultDTO.setRefundDlvFee(dlvFee+"");
        return refundResultDTO;

    }


    @Override
    public HashMap<String, Object> claimValidation(ClaimSearchDTO claimSearchDTO) throws Exception {


    	Ord ord = orderSelectService.selectOrdEntity(claimSearchDTO.getOrdNo());

        HashMap<String, Object> map = Maps.newHashMap();
        map.put("RT", "F");
        if (claimSearchDTO.getClaimType().equals("cancel")) {

            int clmCnt = claimService.checkExistClmRceptCompt(claimSearchDTO);
            if (clmCnt > 0) {

                map.put("MSG", "해당주문은 이미 클레임이 존재합니다.");
                return map;

            }

            List<DlvOrdGodInfoDTO> infoList = new ArrayList<DlvOrdGodInfoDTO>();
            DlvOrdGodInfoDTO dlvOrdGodInfoDTO = new DlvOrdGodInfoDTO();
            dlvOrdGodInfoDTO.setOrdNo(claimSearchDTO.getOrdNo());
            infoList.add(dlvOrdGodInfoDTO);
            String dlvYn = deliveryStatusService.checkDeliveryYnAboutOrder(infoList);

            if (dlvYn.equalsIgnoreCase("Y")) {
                map.put("MSG", "주문 상품중 배송중인 상품이 존재합니다. 반품 접수하세요.");
                return map;
            }

        }
        else if (claimSearchDTO.getClaimType().equals("partCancel")) {

            List<OrdGodExtend> ordGodTurns = claimSearchDTO.getOrdGodExtends();

            if (ordGodTurns == null || ordGodTurns.size() < 1) {

                map.put("MSG", "부분취소할 상품을 선택해주세요.");
                return map;
            }

            // 부분취소시 배송지 한곳만 선택하도록 로직 추가 (2016.01.11 by Cannon)
            int temp= 0;
            for (OrdGodExtend ordGodTurn : ordGodTurns) {
	    		if(temp != 0 && ordGodTurn.getDlvPcupspTurn() != temp){
	    		     map.put("MSG", "부분취소시 배송지 한곳만 선택해서 할수 있습니다.");
	    		     return map;
	    		}
	    		temp = ordGodTurn.getDlvPcupspTurn();
            }

            OrderBoDTO orderDTO = new OrderBoDTO();
            orderDTO.setOrdNo(claimSearchDTO.getOrdNo());

            OrderBoResult orderBoResult = orderBoSelectService.selectOrdDCCount(orderDTO);

            if (orderBoResult != null && orderBoResult.getOrdStatCd().equals("ALL_CNCL")) {

                map.put("MSG", "해당 주문은 전체취소된 주문입니다.");
                return map;
            }

/*            if (orderBoResult != null && orderBoResult.getCount() > 0) {

                map.put("MSG", "주문할인이 적용된 주문은 전체취소 후 재결제 하셔야합니다.");
                return map;
            }*/
            HashMap<Integer, Integer> rt = Maps.newHashMap();

            HashMap<String, OrdGodExtend> god = Maps.newHashMap();
            HashMap<Integer, Integer> godMap = Maps.newHashMap();
            for (OrdGodExtend ordGodTurn : ordGodTurns) {

                rt.put(ordGodTurn.getDlvPcupspTurn(), ordGodTurn.getDlvPcupspTurn());

                god.put(ordGodTurn.getDlvPcupspTurn() + "=" + ordGodTurn.getOrdGodTurn(), ordGodTurn);

                godMap.put(ordGodTurn.getOrdGodTurn(), ordGodTurn.getOrdGodTurn());
            }

            HashMap<Integer,  HashMap<Integer, Integer> > pkGod = Maps.newHashMap();

            List<OrdGodExtend> ordGodExtends = orderBoSelectService.selectOrdGodList(orderDTO);

            for (OrdGodExtend ordGodExtend : ordGodExtends) {

            	if(ordGodExtend.getGodTpCd().equals("GNRL_GOD")){

                    int qty = ordGodExtend.getDlivyDrctQty() - ordGodExtend.getDlivyDrctCnclQty()-ordGodExtend.getRtrvlDrctQty();
                    if (qty >  0 && ordGodExtend.getPkGodTurn() != null) {

            			if (!pkGod.containsKey(ordGodExtend.getPkGodTurn() )) {

            				HashMap<Integer,Integer> gMap = Maps.newHashMap();

            				gMap.put(ordGodExtend.getOrdGodTurn(),ordGodExtend.getOrdGodTurn());
            				pkGod.put(ordGodExtend.getPkGodTurn(), gMap);
            			}else{
            				HashMap<Integer,Integer> gMap = pkGod.get(ordGodExtend.getPkGodTurn());
            				gMap.put(ordGodExtend.getOrdGodTurn(),ordGodExtend.getOrdGodTurn());
            				pkGod.put(ordGodExtend.getPkGodTurn(), gMap);

            			}
                    }

            	}

            }

            Iterator<Integer> it = rt.keySet().iterator();
            HashMap<Integer, Integer> tempPk = Maps.newHashMap();
            while (it.hasNext()) {
                Integer dlvPcupspTurn = it.next();

                orderDTO.setDlvPcupspTurn(dlvPcupspTurn);
                List<OrdGodExtend> v = orderSelectService.selectCoOrdGodList(orderDTO);

                for (OrdGodExtend ordGodExtend : v) {

                    OrdGodExtend exMap = god.get(ordGodExtend.getDlvPcupspTurn() + "=" + ordGodExtend.getOrdGodTurn());

                    if (exMap != null) {

                    	if(ordGodExtend.getGodTpCd().equals("GNRL_GOD")){

                            if (ordGodExtend.getDlvStatCd().equals("DLIVY_COMPT")
                            		|| ordGodExtend.getDlvStatCd().equals("DLV_COMPT")
                            		|| ordGodExtend.getDlvStatCd().equals("3PL_DLV")
                            		|| ordGodExtend.getDlvStatCd().equals("3PL_WRHS_COMPT")
                            		) {

                                map.put("MSG", "출고완료 이후의 상품이 존재합니다. 반품 접수하세요.");
                                return map;
                            }


                         if (ordGodExtend.getDlvPcupspSectCd().equals("CLM_DLVSP")) {

                                map.put("MSG", "교환출고 상품의 취소는 교환상세팝업에서 철회 기능을 통해 취소하시기 바랍니다.");
                                return map;
                            }

                    	}
            			if (!godMap.containsKey(ordGodExtend.getPkGodTurn() ) && ordGodExtend.getPkGodTurn() != null) {

               		     map.put("MSG", "부분취소시 패키지 상품이 포함된경우 마스터 상품도 포함되어야 합니다.");
               		     return map;
            			}else if(ordGodExtend.getPkGodTurn() != null) {

            				tempPk.put(ordGodExtend.getPkGodTurn() ,ordGodExtend.getPkGodTurn() );

            			}else if(ordGodExtend.getGodTpCd().equals("SET_GOD") || ordGodExtend.getGodTpCd().equals("PCKAGE_GOD")) {

            				tempPk.put(ordGodExtend.getOrdGodTurn() ,ordGodExtend.getOrdGodTurn() );
            			}


            			if (ordGodExtend.getGodTpCd().equals("GNRL_GOD") && ordGodExtend.getPkGodTurn() != null) {

            				HashMap<Integer,Integer> gMap = pkGod.get(ordGodExtend.getPkGodTurn());
            				gMap.put(ordGodExtend.getOrdGodTurn(),0);

            			}
                    }
                }
            } //end while

            Iterator<Integer> pkIt = tempPk.keySet().iterator();

            while (pkIt.hasNext()) {

            	Integer pkGodTurn = pkIt.next();
            	HashMap<Integer,Integer> gMap = pkGod.get(pkGodTurn);

            	 Iterator<Integer> skIt = gMap.keySet().iterator();

            	 while (skIt.hasNext()) {

            		 Integer godTurn = skIt.next();

            		 if(gMap.get(godTurn) > 0){

             		     map.put("MSG", "부분취소시 패키지 상품이 포함된경우 구성 상품도 포함되어야 합니다.");
             		     return map;
            		 }

            	 }
            }

        }
        else if (claimSearchDTO.getClaimType().equals("returned") || claimSearchDTO.getClaimType().equals("exchange")
                || claimSearchDTO.getClaimType().equals("ofExchange")) {

            List<OrdGodExtend> ordGodTurns = claimSearchDTO.getOrdGodExtends();

            if (ordGodTurns == null || ordGodTurns.size() < 1) {

            	if(claimSearchDTO.getClaimType().equals("returned")){
            		map.put("MSG", "반품할 상품을 선택해주세요.");
            	}else{
            		map.put("MSG", "교환할 상품을 선택해주세요.");
            	}

                return map;
            }

            HashMap<Integer, Integer> rt = Maps.newHashMap();

            HashMap<String, OrdGodExtend> god = Maps.newHashMap();
            HashMap<Integer, Integer> godMap = Maps.newHashMap();
            int temp= 0;

            //#34294 [개발]글로벌 BO/CSO 반품 접수 구분 로 추가 2017-01-06
            boolean ovseaDlvStatYn = false; // 초기값 : 글로벌 해외반품 여부
            boolean ovseaDmstDlvStatYn = false; // 초기값 : 글로벌 국내반품 여부


            for (OrdGodExtend ordGodTurn : ordGodTurns) {

            	if(!claimSearchDTO.getClaimType().equals("returned")){//반품이 아니면

            		if(temp != 0 && ordGodTurn.getDlvPcupspTurn() != temp){

            		     map.put("MSG", "교환은 배송지 한곳만 선택해서 할수 있습니다.");
            		     return map;
            		}

            		temp = ordGodTurn.getDlvPcupspTurn();
            	}else{//반품이면
            		if(!"KOR".equalsIgnoreCase(ord.getLangCd()) && "DXM".equals(ord.getMallId())){//글로벌일경우
            			//#34294 [개발]글로벌 BO/CSO 반품 접수 구분 로 추가 2017-01-06


            			if("DLIVY_COMPT".equals(ordGodTurn.getDlvStatCd())){//반품일경우 출고완료만 국내반품
            				ovseaDmstDlvStatYn = true;
            			}else{//3PL_DLV , DLV_COMPT 는 해외반품
            				ovseaDlvStatYn = true;
            			}

            			if(ovseaDlvStatYn && ovseaDmstDlvStatYn){
            				map.put("MSG", "글로벌 주문의 경우 국내 반품과 해외 반품 클레임을 동시 접수할 수 없습니다. 확인 후 처리 해주세요.");
            				return map;
            			}
            		}
            	}
                rt.put(ordGodTurn.getDlvPcupspTurn(), ordGodTurn.getDlvPcupspTurn());

                god.put(ordGodTurn.getDlvPcupspTurn() + "=" + ordGodTurn.getOrdGodTurn(), ordGodTurn);

                godMap.put(ordGodTurn.getOrdGodTurn(), ordGodTurn.getOrdGodTurn());
            }

            OrderBoDTO orderDTO = new OrderBoDTO();
            orderDTO.setOrdNo(claimSearchDTO.getOrdNo());


            HashMap<Integer,  HashMap<Integer, Integer> > pkGod = Maps.newHashMap();

            if(claimSearchDTO.getClaimType().equals("returned")){

                List<OrdGodExtend> ordGodExtends = orderBoSelectService.selectOrdGodList(orderDTO);

                for (OrdGodExtend ordGodExtend : ordGodExtends) {

                	if(ordGodExtend.getGodTpCd().equals("GNRL_GOD")){

                        int qty = ordGodExtend.getDlivyDrctQty() - ordGodExtend.getDlivyDrctCnclQty()-ordGodExtend.getRtrvlDrctQty();
                        if (qty >  0 && ordGodExtend.getPkGodTurn() != null) {

                			if (!pkGod.containsKey(ordGodExtend.getPkGodTurn() )) {

                				HashMap<Integer,Integer> gMap = Maps.newHashMap();

                				gMap.put(ordGodExtend.getOrdGodTurn(),ordGodExtend.getOrdGodTurn());
                				pkGod.put(ordGodExtend.getPkGodTurn(), gMap);
                			}else{
                				HashMap<Integer,Integer> gMap = pkGod.get(ordGodExtend.getPkGodTurn());
                				gMap.put(ordGodExtend.getOrdGodTurn(),ordGodExtend.getOrdGodTurn());
                				pkGod.put(ordGodExtend.getPkGodTurn(), gMap);

                			}
                        }

                	}

                }

            }

            Iterator<Integer> it = rt.keySet().iterator();
            HashMap<Integer, Integer> tempPk = Maps.newHashMap();
            while (it.hasNext()) {
                Integer dlvPcupspTurn = it.next();

                orderDTO.setDlvPcupspTurn(dlvPcupspTurn);
                List<OrdGodExtend> ordGodExtends = orderSelectService.selectCoOrdGodList(orderDTO);

                for (OrdGodExtend ordGodExtend : ordGodExtends) {

                    OrdGodExtend exMap = god.get(ordGodExtend.getDlvPcupspTurn() + "=" + ordGodExtend.getOrdGodTurn());

                    if (exMap != null) {

                    	if(ordGodExtend.getGodTpCd().equals("GNRL_GOD")){

                    		if(!"KOR".equalsIgnoreCase(ord.getLangCd()) && "DXM".equals(ord.getMallId())){//글로벌 추가
                                if ((!"DLIVY_COMPT".equals(ordGodExtend.getDlvStatCd())
                                		&& !"DLV_COMPT".equals(ordGodExtend.getDlvStatCd())
                                		&& !"3PL_DLV".equals(ordGodExtend.getDlvStatCd())
                                		&& !"3PL_WRHS_COMPT".equals(ordGodExtend.getDlvStatCd())
                                		)) {

                                    map.put("MSG", "출고완료 이후의 상품만 접수가능합니다.");
                                    return map;
                                }

                    		}else{
                        	    if (!ordGodExtend.getDlvStatCd().equals("DLV_COMPT")) {

                                    map.put("MSG", "배송완료인 경우만 상품 접수가능합니다.");
                                    return map;

                        	    }
                    		}




                            int qty = ordGodExtend.getDlivyDrctQty() - ordGodExtend.getDlivyDrctCnclQty()-ordGodExtend.getRtrvlDrctQty() - Integer.parseInt(ordGodExtend.getRepairDrctQty()); //무료수선부분 추가
                            if (qty <= 0) {
                                map.put("MSG", "클레임 접수 가능수량이 없습니다.");
                                return map;
                            }


                    	}
                        if(claimSearchDTO.getClaimType().equals("returned")){


                            //k2 2016.02.25 교환상품을 반품시 교환클레임 완료인 경우만 반품접수 가능
                        	 if("GNRL_EXCHG".equals(ordGodExtend.getClmTpCd()) && (!"COMPT".equals(ordGodExtend.getClmStatCd()) && (!"WTHDRAW".equals(ordGodExtend.getClmStatCd()))))  {
                               	 map.put("MSG", "교환출고상품입니다. 클레임 완료후 반품접수가 가능합니다.");
                            	 return map;
                            }


                			if (!godMap.containsKey(ordGodExtend.getPkGodTurn() ) && ordGodExtend.getPkGodTurn() != null) {

                    		     map.put("MSG", "반품시 패키지 상품이 포함된경우 마스터 상품도 포함되어야 합니다.");
                    		     return map;
                 			}else if(ordGodExtend.getPkGodTurn() != null) {

                 				tempPk.put(ordGodExtend.getPkGodTurn() ,ordGodExtend.getPkGodTurn() );

                 			}else if(ordGodExtend.getGodTpCd().equals("SET_GOD") || ordGodExtend.getGodTpCd().equals("PCKAGE_GOD")) {

                 				tempPk.put(ordGodExtend.getOrdGodTurn() ,ordGodExtend.getOrdGodTurn() );
                 			}


                			if (ordGodExtend.getGodTpCd().equals("GNRL_GOD") && ordGodExtend.getPkGodTurn() != null) {

                				HashMap<Integer,Integer> gMap = pkGod.get(ordGodExtend.getPkGodTurn());
                				gMap.put(ordGodExtend.getOrdGodTurn(),0);

                			}
                        }
                    }
                }
            } //end while


            if(claimSearchDTO.getClaimType().equals("returned")){

                Iterator<Integer> pkIt = tempPk.keySet().iterator();

                while (pkIt.hasNext()) {

                	Integer pkGodTurn = pkIt.next();
                	HashMap<Integer,Integer> gMap = pkGod.get(pkGodTurn);

                	 Iterator<Integer> skIt = gMap.keySet().iterator();

                	 while (skIt.hasNext()) {

                		 Integer godTurn = skIt.next();

                		 if(gMap.get(godTurn) > 0){

                 		     map.put("MSG", "반품시 패키지 상품이 포함된경우 구성 상품도 포함되어야 합니다.");
                 		     return map;
                		 }

                	 }
                }

            }


        }
        else {

            map.put("MSG", "존재하지 않는 클레임 유형");
            return map;
        }
        map.put("RT", "S");
        return map;
    }

    /*
     * (non-Javadoc)
     * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#selectClaimInfo(com.plgrim.ncp.biz.claim.data.ClaimSearchDTO)
     */
    @Override
    public List<OrdDetailClmInfoResult> selectClaimInfo(ClaimSearchDTO claimSearchDTO) throws Exception{
        return claimService.selectClaimInfo(claimSearchDTO);
    }

    @Override
    public long selectClaimInfoCnt(ClaimSearchDTO claimSearchDTO) {
        return claimService.selectClaimCheckInfoCnt(claimSearchDTO);
    }

    @Override
    public List<ClaimListResult> selectClaimDelayList(ClaimListSearchDTO claimListSearchDTO) {
        return claimDelayService.selectClaimDelayList(claimListSearchDTO);
    }

    @Override
    public long selectClaimDelayListTotal(ClaimListSearchDTO claimListSearchDTO) {
        return claimDelayService.selectClaimDelayListTotal(claimListSearchDTO);
    }

    @Override
    public List<DeliveryOrderGoodResult> selectOrderGoodLackList(ClaimListSearchDTO claimListSearchDTO) {
        return claimOrderGoodLackService.selectOrderGoodLackList(claimListSearchDTO);
    }

    @Override
    public List<ClaimListResult> selectOrderGoodLackListForExcel(ClaimListSearchDTO claimListSearchDTO) {
    	return claimOrderGoodLackService.selectOrderGoodLackListForExcel(claimListSearchDTO);
    }

    @Override
    public long selectOrderGoodLackListTotal(ClaimListSearchDTO claimListSearchDTO) {
        return claimOrderGoodLackService.selectOrderGoodLackListTotal(claimListSearchDTO);
    }

    @Override
    public List<ClaimRefundListResult> selectClaimRefundList(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
        return claimRefundService.selectClaimRefundList(claimRefundListSearchDTO);
    }

    @Override
    public long selectClaimRefundListTotal(ClaimRefundListSearchDTO claimRefundListSearchDTO) {
        return claimRefundService.selectClaimRefundListTotal(claimRefundListSearchDTO);
    }





    /**
     * baseList 로 각 금액을 계산해서 추출한다.
     * @param ordGodBaseList
     * @param ordGodTurnList
     * @param clmRefundSearchDTO
     * @return
     */
    private RefundResultDTO getRefundResultByOrdGodErp(List<InfOrdGodErpDstbExtend> ordGodBaseList, ClmRefundSearchDTO clmRefundSearchDTO,List<Pay> ordPayList,Ord ord){
        
    	RefundResultDTO refundResultDTO = new RefundResultDTO();
    	
        float payExchgRtCrncyAmtSum = 0;	//결제금액
        BigDecimal payExchgRtCrncyAmtSumBd = BigDecimal.ZERO;
        float stdrCrncyUntPrcSum = 0;
        
        int unityPntUseAmtSum = 0;	// 통합포인트 사용
        int webpntUseUntPrcSum = 0; // 웹포인트 사용
        int bskCpnDcAmtSum = 0;	//장바구니쿠폰
        int unityPntAccmlAmtSum = 0;	// 통합포인트 적립
        int webpntAccmlUntPrcSum = 0; // 웹포인트 적립

        RefundClmDlvAmtDTO refundClmDlvAmtDTO = new RefundClmDlvAmtDTO();
        List<RefundClmDlvAmtDTO> refundClmDlvAmtDTOList = new ArrayList<RefundClmDlvAmtDTO>();

        int dlvPcupspTurn = -1;
        int dlvTurn = -1;
        int rownum = 0;
        
        int iSalePrcSumAmt = 0;	// 판매가
        int iBundleDcSumAmt = 0;	// 묶음할인
        int iCrsDcSumAmt = 0;	// 교차할인
        int iGodCpnDcSumAmt = 0;	// 상품쿠폰
        int iBskCpnDcSumAmt = 0;	// 장바구니쿠폰
        
        for(InfOrdGodErpDstbExtend infOrdGodErpDstbExtend : ordGodBaseList){
            rownum++;

            payExchgRtCrncyAmtSum += infOrdGodErpDstbExtend.getPayExchgRtCrncyUntPrc().floatValue();
            payExchgRtCrncyAmtSumBd = payExchgRtCrncyAmtSumBd.add(infOrdGodErpDstbExtend.getPayExchgRtCrncyUntPrc());
            stdrCrncyUntPrcSum += infOrdGodErpDstbExtend.getStdrCrncyUntPrc().floatValue();
            unityPntUseAmtSum += infOrdGodErpDstbExtend.getUnityPntUseUntPrc().intValue();
            webpntUseUntPrcSum += infOrdGodErpDstbExtend.getWebpntUseUntPrc().intValue();
            bskCpnDcAmtSum += infOrdGodErpDstbExtend.getBskCpnDcUntPrc().intValue();
            unityPntAccmlAmtSum += infOrdGodErpDstbExtend.getUnityPntAccmlUntPrc().intValue();
            webpntAccmlUntPrcSum += infOrdGodErpDstbExtend.getWebpntAccmlUntPrc().intValue();

            iSalePrcSumAmt += infOrdGodErpDstbExtend.getSaleUntPrc().intValue();
            iBundleDcSumAmt += infOrdGodErpDstbExtend.getBundleDcUntPrc().intValue();
            iCrsDcSumAmt += infOrdGodErpDstbExtend.getCrsDcUntPrc().intValue();
            iGodCpnDcSumAmt += infOrdGodErpDstbExtend.getGodCpnDcUntPrc().intValue();
            iBskCpnDcSumAmt += infOrdGodErpDstbExtend.getBskCpnDcUntPrc().intValue();
            
            if(dlvPcupspTurn == -1){
                dlvPcupspTurn = Integer.parseInt(infOrdGodErpDstbExtend.getDlvPcupspTurn());
                dlvTurn = infOrdGodErpDstbExtend.getDlvTurn().intValue();
            }

            if(dlvPcupspTurn != Integer.parseInt(infOrdGodErpDstbExtend.getDlvPcupspTurn())
            		|| (dlvPcupspTurn == Integer.parseInt(infOrdGodErpDstbExtend.getDlvPcupspTurn()) && dlvTurn != infOrdGodErpDstbExtend.getDlvTurn().intValue())){
                refundClmDlvAmtDTOList.add(refundClmDlvAmtDTO);
                refundClmDlvAmtDTO = new RefundClmDlvAmtDTO();
                dlvPcupspTurn = Integer.parseInt(infOrdGodErpDstbExtend.getDlvPcupspTurn());
                dlvTurn = infOrdGodErpDstbExtend.getDlvTurn().intValue();
            }

            //배송지 순번 셋팅
            refundClmDlvAmtDTO.setDlvPcupspTurn(Integer.parseInt(infOrdGodErpDstbExtend.getDlvPcupspTurn()));
            refundClmDlvAmtDTO.setDlvTurn(infOrdGodErpDstbExtend.getDlvTurn());

            //배송지 별 환불금 결제 환율금액
            refundClmDlvAmtDTO.setClmDlvAmt(infOrdGodErpDstbExtend.getPayExchgRtCrncyUntPrc().intValue()
            		+infOrdGodErpDstbExtend.getUnityPntUseUntPrc().intValue()
            		+refundClmDlvAmtDTO.getClmDlvAmt());

            if(ordGodBaseList.size()==rownum){//마지막DTO를 add 해준다.
                refundClmDlvAmtDTOList.add(refundClmDlvAmtDTO);
            }
        }

        //배송지별 환불해줄 금액
        refundResultDTO.setClmDlvAmtDTOList(refundClmDlvAmtDTOList);
        refundResultDTO.setPayExchgRtCrncyAmtSum(String.valueOf((int)payExchgRtCrncyAmtSum));//환불금액
        refundResultDTO.setStdrCrncyUntPrcSum(String.valueOf((int) (stdrCrncyUntPrcSum)));//환불금액
        refundResultDTO.setUnityPntUseAmtSum(unityPntUseAmtSum+"");//환불금액 통합포인트
        refundResultDTO.setWebpntUseAmtSum(webpntUseUntPrcSum+"");//환불금액 웹포인트
        refundResultDTO.setBskCpnDcAmtSum(bskCpnDcAmtSum+"");
        refundResultDTO.setUnityPntAccmlAmtSum(unityPntAccmlAmtSum+"");//적립취소금액 통합포인트
        refundResultDTO.setWebpntAccmlUntPrcSum(webpntAccmlUntPrcSum+"");//적립취소금액 웹포인트

        refundResultDTO.setISalePrcSumAmt(iSalePrcSumAmt);
        refundResultDTO.setIBundleDcSumAmt(iBundleDcSumAmt);
        refundResultDTO.setICrsDcSumAmt(iCrsDcSumAmt);
        refundResultDTO.setIGodCpnDcSumAmt(iGodCpnDcSumAmt);
        refundResultDTO.setIBskCpnDcSumAmt(iBskCpnDcSumAmt);
        
        return refundResultDTO;
    }

    /**
     * 환불 배송비를 구한다.
     * 
     * @param refundResultDTO
     * @param clmRefundSearchDTO
     * @param ordGodTurnList
     * @return
     * @throws Exception
     */
    private RefundResultDTO getDlvCstRefundAmtByOrdGodErp(RefundResultDTO refundResultDTO,ClmRefundSearchDTO clmRefundSearchDTO,List<String> ordGodTurnList) throws Exception{

        int dlvFee = 0;

        Ord ord = orderSelectService.selectOrdEntity(clmRefundSearchDTO.getOrdNo());
        SysExchgRtDTO sysExchgRtDTO = this.selectExchangeRt(ord.getCrncyCd(),ord.getExchgRtAplBegDt());

        // 반품
        if("RTGOD".equals(clmRefundSearchDTO.getClmTpCd())){
        	try {

        		dlvFee += Integer.parseInt(clmRefundSearchDTO.getReturnDlvAmt());
        		dlvFee = dlvFee * -1;

            }catch (Exception e) {log.info("clmRefundSearchDTO.getReturnDlvAmt() is null : 반품접수화면에서 배송비 안넘어옴");}

        // 부분취소
        }else if (ordGodTurnList!=null && ordGodTurnList.size()!=0){

            // 취소 배송비 정보 조회
            List<LgsDlvFoExtend> cnclLgsDlvspList = new ArrayList<LgsDlvFoExtend>();

           	cnclLgsDlvspList = deliveryRuleService.getCnclClmLgsDlvList(clmRefundSearchDTO.getLgsDlvspList().get(0).getClmWrhsGodList());

            java.math.BigDecimal realityDlvCst = BigDecimal.valueOf(0);
            java.math.BigDecimal realityDlvCstEx = BigDecimal.valueOf(0);

            java.math.BigDecimal payExchgRtCrncyAmt = BigDecimal.valueOf(0);

            for(LgsDlvFoExtend lgsDlv : cnclLgsDlvspList)
            {
				if(lgsDlv.getCnclDlvCst() == null) {
					lgsDlv.setCnclDlvCst(BigDecimal.valueOf(0));
				}
				
				if(lgsDlv.getRtgodDlvCst() == null) {
					lgsDlv.setRtgodDlvCst(BigDecimal.valueOf(0));
				}
				
				if(lgsDlv.getOriRealityDlvCst().intValue() > 0){
					// 유료배송 -> 전체취소 이거나 유료배송 일 경우
					realityDlvCst = realityDlvCst.add(lgsDlv.getCnclDlvCst());
					
    				if(null!=lgsDlv.getCnclDlvCst() && lgsDlv.getCnclDlvCst().intValue()!=0){
    					realityDlvCstEx = realityDlvCstEx.add(lgsDlv.getPayExchgRtCrncyAmt());
    				}
    				
					
				} else {
					// 무료배송 -> 유료배송 일 경우
					realityDlvCst = realityDlvCst.subtract(lgsDlv.getRealityDlvCst());
					if(lgsDlv.getRealityDlvCst().intValue()<0){
						sysExchgRtDTO.setOriAmt(lgsDlv.getRealityDlvCst().multiply(new BigDecimal("-1"))+"");
					}else{
						sysExchgRtDTO.setOriAmt(lgsDlv.getRealityDlvCst()+"");
					}

					try {
    					if(lgsDlv.getRealityDlvCst().intValue()<0){
    						realityDlvCstEx = realityDlvCstEx.subtract(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)).multiply(new BigDecimal("-1")));
    					}else{
    						realityDlvCstEx = realityDlvCstEx.subtract(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
    					}

					}catch (Exception e) {}

					if(null!=lgsDlv.getPayExchgRtCrncyAmt()){
						payExchgRtCrncyAmt = payExchgRtCrncyAmt.subtract(lgsDlv.getPayExchgRtCrncyAmt());
					}

				}

            }

            dlvFee = realityDlvCst.intValue();

        // 전체취소
        } else {
            //무료배송//배송비가 0원이면 환불배송비도 0원
            //유료배송//배송비가 있으면 배송지별 합쳐서 배송비 환불
            OrderBoDTO orderBoDTO= new OrderBoDTO();
            orderBoDTO.setOrdNo(clmRefundSearchDTO.getOrdNo());
            List<LgsDlvspExtend> lgsDlvspExtendList = orderBoSelectService.selectDlvspList(orderBoDTO);
            for(LgsDlvspExtend dto : lgsDlvspExtendList){
                if(dto.getRealityDlvCst()!=null){
                    dlvFee += dto.getRealityDlvCst();
                }
            }
        }

        refundResultDTO.setRefundDlvFee(dlvFee+"");
        return refundResultDTO;

    }

    /**
     * 클레임으로 DB에 생성되어있는 환불정보를 가져오는 컴포넌트
     */
	@Override
    public ClmRefundDTO getClmRefundInfoList(String clmNo) throws Exception {
		ClmRefundDTO clmRefundDTO = new ClmRefundDTO();
		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		List<LgsDlv> lgsDlv = new ArrayList<LgsDlv>();
		float dlvCst = 0;
		float prdCst = 0;
		clm = claimService.selectClm(clm);
		lgsDlv = deliveryService.selectLgsDlvByClmNo(clmNo);

		for(LgsDlv dlv : lgsDlv){
			dlvCst = dlv.getCnclDlvCst().floatValue() - dlv.getRtgodDlvCst().floatValue();
		}

		if(clm.getPayExchgRtCrncySumAmt()!=null){
			prdCst = clm.getPayExchgRtCrncySumAmt().floatValue()-dlvCst;
		}

		clmRefundDTO.setRefundDlvFee(dlvCst+"");//환불배송비
		clmRefundDTO.setRefundPrdFee(prdCst+"");//상품환불비
		clmRefundDTO.setRefundResultDTO(payBoService.getClmRefundInfoList(clmNo));//결제타입별 환불정보

	    return clmRefundDTO;
    }


    /**
     * ncp 3차
     * 클레임으로 DB에 생성되어있는 환불정보를 가져오는 컴포넌트
     */
	@Override
    public ClmRefundDTO getClmRefundInfoListEx(String clmNo) throws Exception {
		ClmRefundDTO clmRefundDTO = new ClmRefundDTO();
		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		List<LgsDlv> lgsDlv = new ArrayList<LgsDlv>();
		int cnclDlvCst = 0;
		BigDecimal cnclDlvCstEx = BigDecimal.ZERO;
		int prdCst = 0;
		BigDecimal prdCstEx = BigDecimal.ZERO;
		clm = claimService.selectClm(clm);
		lgsDlv = deliveryService.selectLgsDlvByClmNo(clmNo);

		for(LgsDlv dlv : lgsDlv){
			cnclDlvCst += dlv.getCnclDlvCst().intValue();
		}

        SysExchgRtDTO sysExchgRtDTO = this.selectExchangeRt(clm.getCrncyCd(),clm.getExchgRtAplBegDt());

        for(LgsDlv dlv : lgsDlv){
            //취소배송비 환율적용하여 누적

			sysExchgRtDTO.setOriAmt(dlv.getCnclDlvCst()+"");
			cnclDlvCstEx = cnclDlvCstEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
		}

		if(clm.getStdrCrncySumAmt()!=null){
			prdCst = clm.getStdrCrncySumAmt().intValue()-cnclDlvCst;
		}
		if(clm.getPayExchgRtCrncySumAmt()!=null){
			prdCstEx = clm.getPayExchgRtCrncySumAmt().subtract(cnclDlvCstEx);
		}

		BigDecimal refundSumFeeEx = new BigDecimal("0");
		refundSumFeeEx = cnclDlvCstEx.add(prdCstEx);
		clmRefundDTO.setRefundDlvFee(cnclDlvCst+"");//환불배송비
		clmRefundDTO.setRefundDlvFeeEx(cnclDlvCstEx+"");//환불배송비
		clmRefundDTO.setRefundPrdFee(prdCst+"");//상품환불비
		clmRefundDTO.setRefundPrdFeeEx(prdCstEx+"");//상품환불비
		clmRefundDTO.setRefundSumFeeEx(refundSumFeeEx.toString());
        clmRefundDTO.setRefundResultDTO(payBoService.getClmRefundInfoList(clmNo));//결제타입별 환불정보

	    return clmRefundDTO;
    }



	@Override
    public List<PayExtend> selectOrdClmPayExtend(PayExtend payExtend) {

	    return payBoService.selectOrdClmPayExtend(payExtend);
    }

	@Override
    public Clm selectClm(Clm clm) throws Exception {
		return claimService.selectClm(clm);
    }

    /**
	 * 클레임 접수화면에서 취소배송비계산
	 * ncp2
	 */
	@Override
    public List<LgsDlvFoExtend> selectCnclClmLgsDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {
		return deliveryRuleService.getCnclClmLgsDlvList(claimBoDTO.getLgsDlvspList().get(0).getClmWrhsGodList());
    }



	/**
	 * 클레임 접수화면에서 반품배송비계산
	 */
	@Override
    public List<LgsDlvspExtend> selectRtgodDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {

		String callTp 		= claimBoDTO.getCallTp();		//호출유형
		String ordTp 		= claimBoDTO.getOrdTp();		//주문유형
    	String adminTpCd	= claimBoDTO.getAdminTpCd();	//운영자 유형 코드

		LgsDlvspExtend lgsDlvspExtendDTO = new LgsDlvspExtend();
        List<LgsDlvspExtend> lgsDlvspExtendListDTO = new ArrayList<LgsDlvspExtend>();

		int index = 0;//취소배송비, 반품배송비, 교환배송비 저장용
		int dlvspAdd = 0;//배송지 증가용
		int dlvAdbukTurnBefore = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
		int dlvAdbukTurnNow = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)


        ClmWrhsGodExtend clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
        List<ClmWrhsGodExtend> clmWrhsGodExtendListDTO = new ArrayList<ClmWrhsGodExtend>();


        //물류배송지 정보 추출
        for (LgsDlvspExtend lgsDlvspExtend : claimBoDTO.getLgsDlvspList())
        {

        	lgsDlvspExtendDTO = new LgsDlvspExtend();

        	//클레임수거지 인 경우
        	if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP"))
        	{

        		//반품시 수거지가 같은경우 수거지를 한 곳으로 하기위한 로직
				if(index == 0){
					//화면에서 입력받은 배송지순번 추출
					dlvAdbukTurnBefore = lgsDlvspExtend.getDlvAdbukTurn();
				} else {
					dlvAdbukTurnNow = lgsDlvspExtend.getDlvAdbukTurn();
				}

				//이전수거지 순번과 현재수거지 순번이 같으면
				if(dlvAdbukTurnBefore == dlvAdbukTurnNow) {

        			//수거지순번이 같은경우 상품리스트 Merge.

            		//클레임입고상품 정보 추출
            		for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
            		{
            			clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
            			Functions.copyProperties(clmWrhsGodEntity, clmWrhsGodExtendDTO);
            			clmWrhsGodExtendListDTO.add(clmWrhsGodExtendDTO);
            		}

            		//기존 수거지에 상품리스트 추가
            		lgsDlvspExtendListDTO.get(dlvspAdd).setClmWrhsGodList(clmWrhsGodExtendListDTO);

            		dlvAdbukTurnNow = dlvAdbukTurnBefore;

        		} else {

        			//수거지순번이 다른경우 상품리스트 그대로 전달.
        			clmWrhsGodExtendListDTO = new ArrayList<ClmWrhsGodExtend>();

        			if(index != 0)
        			{
        				dlvspAdd++;
        			}

        			lgsDlvspExtendDTO.setDlvAdbukTurn(lgsDlvspExtend.getDlvAdbukTurn());
        			lgsDlvspExtendDTO.setDlvMnCd(lgsDlvspExtend.getDlvMnCd());
                    lgsDlvspExtendDTO.setDlvPcupspSectCd(lgsDlvspExtend.getDlvPcupspSectCd());
                    lgsDlvspExtendDTO.setDlvCstCpnNo(lgsDlvspExtend.getDlvCstCpnNo());

					lgsDlvspExtendListDTO.add(lgsDlvspExtendDTO);	//물류배송지 추가

            		//클레임입고상품 정보 추출
            		for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
            		{
            			clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
            			Functions.copyProperties(clmWrhsGodEntity, clmWrhsGodExtendDTO);
            			clmWrhsGodExtendListDTO.add(clmWrhsGodExtendDTO);
            		}

            		//추가된 수거지정보에 상품리스트 등록
            		lgsDlvspExtendListDTO.get(dlvspAdd).setClmWrhsGodList(clmWrhsGodExtendListDTO);

        		}

				index++;
        	}

        }

        List<LgsDlvspExtend> resultList = new ArrayList<LgsDlvspExtend>();

       	resultList = deliveryRuleService.getRtClmLgsDlvList(lgsDlvspExtendListDTO, callTp, ordTp, adminTpCd);

        return resultList;
    }

    /**
     * 클레임 접수화면에서 교환배송비계산
     * 
     */
    @Override
    public List<LgsDlvspExtend> selectExchgDlvCstCal(ClaimBoDTO claimBoDTO, SystemPK systemPK) throws Exception {

        String callTp 		= claimBoDTO.getCallTp();		//호출유형
        String ordTp 		= claimBoDTO.getOrdTp();		//주문유형
        String adminTpCd	= claimBoDTO.getAdminTpCd();	//운영자 유형 코드
        String clmTpCd  	= claimBoDTO.getClmTpCd();	    //클레임 유형 코드
        String role  	    = claimBoDTO.getRole();	        //FO/BO 구분, FO : F, BO : B

        return deliveryRuleService.getExchgClmLgsDlvList(claimBoDTO.getLgsDlvspList(), callTp, ordTp, adminTpCd, clmTpCd, role);

//        List<LgsDlvspExtend> lgsDlvspExtendResultListDTO = new ArrayList<LgsDlvspExtend>();
//
//        String callTp 		= claimBoDTO.getCallTp();		//호출유형
//        String ordTp 		= claimBoDTO.getOrdTp();		//주문유형
//        String adminTpCd	= claimBoDTO.getAdminTpCd();	//운영자 유형 코드
//
//        LgsDlvspExtend lgsDlvspExtendDTO = new LgsDlvspExtend();
//        List<LgsDlvspExtend> lgsDlvspExtendListDTO = new ArrayList<LgsDlvspExtend>();
//
////        int index = 0;//취소배송비, 반품배송비, 교환배송비 저장용
//        int dlvspAdd = 0;//배송지 증가용
////        int dlvAdbukTurnBefore = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
////        int dlvAdbukTurnNow = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
////        int dlvPcupspTurn = 0;//배송지 중복체크용(같은 배송지인 경우 1번만 수행)
//
//
//        //물류배송지 정보 추출
//        for (LgsDlvspExtend lgsDlvspExtend : claimBoDTO.getLgsDlvspList())
//        {
//            //물류배송지 정보 초기화
//            lgsDlvspExtendDTO = new LgsDlvspExtend();
//            //물류배송지리스트 정보 초기화
//            lgsDlvspExtendListDTO = new ArrayList<LgsDlvspExtend>();
//
//            //상품정보 초기화
//            ClmWrhsGodExtend clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
//            //상품리스트 정보 초기화
//            List<ClmWrhsGodExtend> clmWrhsGodExtendListDTO = new ArrayList<ClmWrhsGodExtend>();
//
//            //클레임수거지 인 경우
//            if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP"))
//            {
//                //회수용 배송지 정보
//                lgsDlvspExtendDTO.setDlvAdbukTurn(lgsDlvspExtend.getDlvAdbukTurn());
//                lgsDlvspExtendDTO.setDlvMnCd(lgsDlvspExtend.getDlvMnCd());
//                lgsDlvspExtendDTO.setDlvPcupspSectCd(lgsDlvspExtend.getDlvPcupspSectCd());
//
//                lgsDlvspExtendListDTO.add(lgsDlvspExtendDTO);	//물류배송지 추가
//
//                //클레임입고상품 정보 추출
//                for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
//                {
//                    clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
//                    Functions.copyProperties(clmWrhsGodEntity, clmWrhsGodExtendDTO);
//                    clmWrhsGodExtendListDTO.add(clmWrhsGodExtendDTO);
//                }
//
//                //추가된 수거지정보에 상품리스트 등록
//                lgsDlvspExtendListDTO.get(dlvspAdd).setClmWrhsGodList(clmWrhsGodExtendListDTO);
//
//                lgsDlvspExtendResultListDTO.addAll(deliveryRuleService.getRtClmLgsDlvList(lgsDlvspExtendListDTO, callTp, ordTp, adminTpCd));
//
//            } else if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_DLVSP")){
//                //출고용 배송지 정보
//                lgsDlvspExtendDTO.setDlvAdbukTurn(lgsDlvspExtend.getDlvAdbukTurn());
//                lgsDlvspExtendDTO.setDlvMnCd(lgsDlvspExtend.getDlvMnCd());
//                lgsDlvspExtendDTO.setDlvPcupspSectCd(lgsDlvspExtend.getDlvPcupspSectCd());
//
//                lgsDlvspExtendListDTO.add(lgsDlvspExtendDTO);	//물류배송지 추가
//
//                //클레임입고상품 정보 추출
//                for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList())
//                {
//                    clmWrhsGodExtendDTO = new ClmWrhsGodExtend();
//                    Functions.copyProperties(clmWrhsGodEntity, clmWrhsGodExtendDTO);
//                    clmWrhsGodExtendListDTO.add(clmWrhsGodExtendDTO);
//                }
//
//                //추가된 수거지정보에 상품리스트 등록
//                lgsDlvspExtendListDTO.get(dlvspAdd).setClmWrhsGodList(clmWrhsGodExtendListDTO);
//
//                lgsDlvspExtendResultListDTO.addAll(deliveryRuleService.getExchgClmLgsDlvList(lgsDlvspExtendListDTO, callTp, ordTp, adminTpCd));
//            }
//        }
//
//        return lgsDlvspExtendResultListDTO;

    }


	/**
	 * selectExchangeRt(String crncyCd) 이거 오버로딩
	 * 환율적용시작일 키값으로 주문서에서 적용되었던 환율정보를 구해온다.
	 * @param crncyCd
	 * @param exchgRtAplBegDtStr
	 * @return
	 */
    @Override
	public SysExchgRtDTO selectExchangeRt(String crncyCd,String exchgRtAplBegDtStr) {

		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		//주문서에서 적용된 시스템 결제 환율 정보를 가져온다.
		sysExchgRtDTO = orderSelectService.selectExchangeRt(crncyCd,exchgRtAplBegDtStr);
		return sysExchgRtDTO;

	}

    @Override
	public SysExchgRtDTO selectExchangeRt(String crncyCd,Date exchgRtAplBegDt) {

		SysExchgRtDTO sysExchgRtDTO = new SysExchgRtDTO();
		//주문서에서 적용된 시스템 결제 환율 정보를 가져온다.
		sysExchgRtDTO = orderSelectService.selectExchangeRt(crncyCd,exchgRtAplBegDt);
		return sysExchgRtDTO;

	}

    /**
     * 클레임 상세 페이지 배송비 팝업
     */
	@Override
	public ClmRefundDTO selectDlvFeeShow(String clmNo, String clmTp) throws Exception {


    	int dmstDlvFee = 0;
    	int ovseaDlvFee = 0;
    	int refundDlvFee = 0;
    	BigDecimal dmstDlvFeeEx = BigDecimal.ZERO;
    	BigDecimal ovseaDlvFeeEx = BigDecimal.ZERO;
    	BigDecimal refundDlvFeeEx = BigDecimal.ZERO;

		ClmRefundDTO clmRefundDTO = new ClmRefundDTO();
		List<LgsDlv> lgsDlvList = new ArrayList<LgsDlv>();

		//클레임번호로 물류배송 조회
		lgsDlvList = deliveryService.selectLgsDlvByClmNo(clmNo);

		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		clm = claimService.selectClm(clm);




		SysExchgRtDTO sysExchgRtDTO = this.selectExchangeRt(clm.getCrncyCd(),clm.getExchgRtAplBegDt());
		for(LgsDlv dto : lgsDlvList){

			if("C".equalsIgnoreCase(clmTp)){//취소배송비
				if(null == dto.getOvseaDlvCstPlcSn()){//국내배송비

					//한화취소배송비
					dmstDlvFee += dto.getCnclDlvCst().intValue();

					//달러취소배송비
					sysExchgRtDTO.setOriAmt(dto.getCnclDlvCst()+"");
					dmstDlvFeeEx = dmstDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));

					//합계취소배송비 한화
					//합계취소배송비 달러
					refundDlvFee += dto.getCnclDlvCst().intValue();
					refundDlvFeeEx = refundDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
				}else{//해외배송비

					//한화취소배송비
					ovseaDlvFee += dto.getCnclDlvCst().intValue();

					//달러취소배송비
					sysExchgRtDTO.setOriAmt(dto.getCnclDlvCst()+"");
					ovseaDlvFeeEx = ovseaDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));

					//합계취소배송비 한화
					//합계취소배송비 달러
					refundDlvFee += dto.getCnclDlvCst().intValue();
					refundDlvFeeEx = refundDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
				}
			}else{//반품배송비
				if(null == dto.getOvseaDlvCstPlcSn()){//국내배송비

					//한화취소배송비
					dmstDlvFee += dto.getRtgodDlvCst().intValue();

					//달러취소배송비
					sysExchgRtDTO.setOriAmt(dto.getRtgodDlvCst()+"");
					dmstDlvFeeEx = dmstDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));

					//합계취소배송비 한화
					//합계취소배송비 달러
					refundDlvFee += dto.getRtgodDlvCst().intValue();
					refundDlvFeeEx = refundDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
				}else{//해외배송비

					//한화취소배송비
					ovseaDlvFee += dto.getRtgodDlvCst().intValue();

					//달러취소배송비
					sysExchgRtDTO.setOriAmt(dto.getRtgodDlvCst()+"");
					ovseaDlvFeeEx = ovseaDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));

					//합계취소배송비 한화
					//합계취소배송비 달러
					refundDlvFee += dto.getRtgodDlvCst().intValue();
					refundDlvFeeEx = refundDlvFeeEx.add(new BigDecimal(orderSelectService.applyExchangeRt(sysExchgRtDTO)));
				}
			}

		}

		clmRefundDTO.setDmstDlvFee(dmstDlvFee+"");
		clmRefundDTO.setOvseaDlvFee(ovseaDlvFee+"");
		clmRefundDTO.setRefundDlvFee(refundDlvFee+"");
		clmRefundDTO.setDmstDlvFeeEx(dmstDlvFeeEx.toString());
		clmRefundDTO.setOvseaDlvFeeEx(ovseaDlvFeeEx.toString());
		clmRefundDTO.setRefundDlvFeeEx(refundDlvFeeEx.toString());

		return clmRefundDTO;

	}

    @Override
    public List<LgsDlv> selectLgsDlvByClmNo(String clmNo) throws Exception {
        return deliveryService.selectLgsDlvByClmNo(clmNo);
    }

	@Override
	public BigDecimal selectAllDcExForOrd(String ordNo) throws Exception {
		return infOrdGodErpDstbService.selectAllDcExForOrd(ordNo);
	}

	@Override
	public BigDecimal selectAllDcExForClm(String clmNo) throws Exception {
		return infOrdGodErpDstbService.selectAllDcExForClm(clmNo);
	}

	@Override
	public String applyExchangeRt(SysExchgRtDTO sysExchgRtDTO) throws Exception {
		return orderSelectService.applyExchangeRt(sysExchgRtDTO);
	}


    @Override
    public List<LgsDlv> selectLgsDlvByOrdNo(String ordNo) throws Exception {
        return deliveryService.selectLgsDlvByOrdNo(ordNo);
    }

    /**
     * 국내 기본 배송정책 가져오기
     * @return String
     * @throws Exception
     */
    @Override
    public String selectBaseDlvComCd(String mallId) throws Exception {
        return claimService.selectBaseDlvComCd(mallId);
    }

    @Override
    public Map<String, String> selectBaseDlvCom(String lang) throws Exception {
        //return claimService.selectBaseDlvCom(lang);
    	return selectBaseDlvCom(lang, "DXM");
    }
    
    @Override
    public Map<String, String> selectBaseDlvCom(String lang, String mallId) throws Exception {
    	LgsDlv dlv = new LgsDlv();
    	dlv.setLang(lang);
    	dlv.setMallId(mallId);
        return claimService.selectBaseDlvCom(dlv);
    }


  	/* (non-Javadoc)
	   * @see com.plgrim.ncp.cmp.orderfulfilment.bo.claim.ClaimSelectComponent#selectRepairRegList(com.plgrim.ncp.biz.claim.data.ClaimListSearchDTO, com.plgrim.ncp.framework.page.PageParam)
	   */
	  //무료수선 목록 조회
  	public List<ClaimListResult> selectRepairRegList(ClaimListSearchDTO searchDTO, PageParam pageParam) throws Exception {
  		List<ClaimListResult> results =claimService.selectRepairRegList(searchDTO, pageParam);
  		return results;
    }

	@Override
	public Map<Long, ComDmstcDlvCstPlcExtend> getDeliveryRuleMapForRepair(String clmTpCd) throws Exception {
		 return deliveryRuleService.getDeliveryRuleMapForRepair(clmTpCd);
	}
	
    /** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 
     *  집하상태코드목록 - 클레임지연조회  
     */
	@Override
	public List<SysExcpCd> selectSysExcpCdList(SysExcpCd sysExcpCd) throws Exception {
		return claimService.selectSysExcpCdList(sysExcpCd);
	}
}
