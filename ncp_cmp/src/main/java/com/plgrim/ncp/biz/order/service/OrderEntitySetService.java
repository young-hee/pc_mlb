package com.plgrim.ncp.biz.order.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbExtends;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlv;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtends;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.enums.OrderEnum;
import com.plgrim.ncp.biz.delivery.repository.DeliverySelectRepository;
import com.plgrim.ncp.biz.order.data.LgsDlvspDTO;
import com.plgrim.ncp.biz.order.data.OrderDTO;
import com.plgrim.ncp.commons.data.order.KcpReturnDTO;
import com.plgrim.ncp.commons.data.order.NaverReturnDTO;
import com.plgrim.ncp.framework.commons.StringService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderEntitySetService extends AbstractComponent {

    @Autowired
    OrderCommandService orderCommandService;

    @Autowired
    DeliverySelectRepository deliverySelectRepository;
    
	public Ord makeOrd(OrderDTO orderDTO, Map<String, String> resultMap) throws Exception {
		// data 구성
		// a. from newOrder.jsp
		// b. from orderCommandComponent
		// c. no use
		Ord ord = orderDTO.getOrd();
		Mbr mbr = orderDTO.getMbr();

		String ordStatCd = OrderEnum.ORD_STAT_PAY_COMPT.toString();
		String currency = OrderEnum.KRW.toString();
		String payMnCd = orderDTO.getPay().getPayMnCd();

		if(OrderEnum.PG_SECT_CD.KCP_PAY.toString().equals(orderDTO.getPay().getPgSectCd())
				&&orderDTO.getKcpDTO()!=null&&OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
		//if (OrderEnum.VIRTL_BNK_ACCT_PAY.toString().equals(payMnCd)) {
			ordStatCd = "DEPST_WAIT";

			// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			/* 예약주문상태 제거
			if (!StringService.isEmpty(ord.getResveOrdPaySectCd())) {
				ordStatCd = "RESVE_ORD_DEPST_WAIT";
			}
			*/
		}
		else {
			// 예약장바구니 기능 추가  : by cannon (2016.04.18)
			/* 예약주문상태 제거
			if ("ALL_PAY".equals(ord.getResveOrdPaySectCd())) {
				ordStatCd = "RESVE_ORD_PAY_COMPT";
			}
			else if ("PART_PAY".equals(ord.getResveOrdPaySectCd())) {
				ordStatCd = "RESVE_ORD_DEPST_WAIT";
			}
			*/
		}


//		// TODO - check! 중국어 알리페이
//		if ("ENG".equals(orderDTO.getLang())) {
//			currency = "USD";
//		}
//		else if ("CHI".equals(orderDTO.getLang())) {
//			currency = "CNY";
//		}

		// (1).주문정보
		if (StringService.isEmpty(ord.getOrdNo())) {
			ord.setOrdNo(orderCommandService.selectDBNumber("SQ_ORD", "OD"));
			ord.setOrdStatCd(ordStatCd);
			// ord.setOrdTpCd(ordTpCd); -a
		}
		ord.setOrdDt(orderDTO.getRegDt());

		// (2).주문자기본정보
		ord.setMbrTpCd(StringService.isEmpty(mbr.getMbrTpCd()) ? "NMBR" : mbr.getMbrTpCd());
		ord.setMbrNo(mbr.getMbrNo());
		ord.setMbrAtrbCd(mbr.getMbrAtrbCd());
		ord.setMbrEmplNo(mbr.getMbrEmplNo());
		// ord.setTelOrdAdminId(telOrdAdminId);	-c
		// ord.setCstmrNm(cstmrNm);			-a
		// ord.setCstmrEmail(cstmrEmail);	-a

		// (3).주문자주소정보
		ord.setPostNo(mbr.getMbrPostNo());
		ord.setBaseAddr(mbr.getMbrBaseAddr());
		ord.setDetailAddr(mbr.getMbrDetailAddr());
//		if (!StringService.isEmpty(mbr.getMbrTpCd())) {
//			ord.setCstmrMobilNationNo(mbr.getMobilNationNo());
//			ord.setCstmrMobilAreaNo(mbr.getMobilAreaNo());
//			ord.setCstmrMobilTlofNo(mbr.getMobilTlofNo());
//			ord.setCstmrMobilTlofWthnNo(mbr.getMobilTlofWthnNo());
//			ord.setCstmrTelNationNo(mbr.getTelNationNo());
//			ord.setCstmrTelAreaNo(mbr.getTelAreaNo());
//			ord.setCstmrTelTlofNo(mbr.getTelTlofNo());
//			ord.setCstmrTelTlofWthnNo(mbr.getTelTlofWthnNo());
//		}

		// (4).주문금액정보 - 마지막에 update
		ord.setCrncyCd(currency);
//		ord.setStdrCrncySumAmt(BigDecimal.ZERO);
//		ord.setPayExchgRtCrncySumAmt(BigDecimal.ZERO);
		ord.setRtlPrcSumAmt(BigDecimal.ZERO);
		ord.setSaleSumAmt(BigDecimal.ZERO);
//		ord.setSvcSumAmt(svcSumAmt);
//		ord.setDlvCstSumAmt(dlvCstSumAmt);
//
//		// (5).할인금액정보
//		ord.setGodDcSumAmt(godDcSumAmt);
//		ord.setEmpDcSumAmt(empDcSumAmt);
//		ord.setGodCpnDcSumAmt(godCpnDcSumAmt);
//		ord.setBskCpnDcSumAmt(bskCpnDcSumAmt);
//		ord.setDlvCstCpnDcSumAmt(dlvCstCpnDcSumAmt);
//
//		ord.setUnityPntUseSumAmt(unityPntUseSumAmt);
//		ord.setEvtPntUseSumAmt(evtPntUseSumAmt);
//		ord.setWebpntUseSumAmt(webpntUseSumAmt);
//
//		// (6).적립금정보
//		 ord.setUnityPntAccmlAmt(unityPntAccmlAmt);
//		 ord.setEvtPntAccmlAmt(evtPntAccmlAmt);
//		 ord.setWebpntAccmlAmt(webpntAccmlAmt);

		// (7).유입정보
		ord.setLangCd(orderDTO.getLang());
		ord.setMallId(orderDTO.getMallId());
		ord.setDvcCd(orderDTO.getDevice());
		if (orderDTO.getInflowSn() > 0L) {
			ord.setInflowSn(orderDTO.getInflowSn());
		}

		// (8).인증정보
		// ord.setSelfCrtfcSectCd(selfCrtfcSectCd); -b
		// ord.setRlnmCrtfcDi(rlnmCrtfcDi);			-b
		// ord.setRlnmCrtfcCi(rlnmCrtfcCi);			-b
		// ord.setRlnmCrtfcIpin(rlnmCrtfcIpin);		-b
		// ord.setB2eEmplCrtfcCd(b2eEmplCrtfcCd); 	-b

		// (9).제휴업체정보
		// ord.setSaleAffComOrdNo(saleAffComOrdNo);	-c
		// ord.setSaleAffComId(saleAffComId);		-c
		// ord.setRelayAffComId(relayAffComId);		-c
		// ord.setAdvrtsAffComId(advrtsAffComId);	-c

		// (10).예약판매정보
//		ord.setResveOrdPaySectCd(resveOrdPaySectCd);	-a
//		ord.setResveOrdPartPayAmt(resveOrdPartPayAmt);	-a
//		ord.setResveOrdPayClosDt(resveOrdPayClosDt);	-a
//		ord.setResveOrdDlivyPrearngeDate(resveOrdDlivyPrearngeDate);	-c
//		ord.setResveOrdDlivyAprvCd("APRV_WAIT");		-a

		// (11).기타정보
		ord.setGodSumry(orderDTO.getGodSummary());
		/*
		if (resultMap != null && !StringService.isEmpty(resultMap.get("cash_bill_no"))) {
			ord.setRcptfrReqstCd("CASH_RCPTFR_PUBLI");
			ord.setRcptfrPrcsCd("CASH_RCPTFR_PUBLI_RCEPT");
			ord.setRcptfrPrposCd("P".equals(resultMap.get("business_type")) ? "INDCT" : "EXPNDTR_PRUF");
			ord.setRcptfrAprvNo(resultMap.get("cash_bill_no")); // resultMap.get("cash_approval_no");
		}
		*/
		// ord.setRcptfrReqstCd("CASH_RCPTFR_PUBLI");
		// ord.setRcptfrPrcsCd("CASH_RCPTFR_PUBLI_RCEPT");
		// ord.setRcptfrPrposCd("P".equals(resultMap.get("business_type")) ?"INDCT" : "EXPNDTR_PRUF");
		// ord.setRcptfrAprvNo(resultMap.get("cash_bill_no"));
		
		ord.setDepstWaitCnclYn("N");
		ord.setLagQtyOrdDcsnYn("N");
		ord.setRegtrId(orderDTO.getRegtrId());
		ord.setRegDt(orderDTO.getRegDt());
		ord.setUdterId(orderDTO.getRegtrId());
		ord.setUdtDt(orderDTO.getRegDt());

		return ord;
	}

	public LgsDlvsp makeLgsDlvsp(OrderDTO orderDTO, LgsDlvspDTO lgsDlvspDTO) throws Exception {
		LgsDlvsp lgsDlvsp = lgsDlvspDTO.getLgsDlvsp();
		Ord ord = orderDTO.getOrd();
		String ordNo = ord.getOrdNo();

		lgsDlvsp.setOrdNo(ordNo);
		lgsDlvsp.setDlvPcupspTurn(orderCommandService.selectDeliverySequence(ordNo));
		lgsDlvsp.setDlvPcupspSectCd("ORD_DLVSP");
		// lgsDlvsp.setDlvHopeDate(dlvHopeDate); // -> 화면에서 없어짐
		lgsDlvsp.setAddrseNationCd("KR");	// TODO - 회원배송지 코드 세팅과 국내외 배송구분에 따른 값 세팅 필요.
		lgsDlvsp.setRegtrId(orderDTO.getRegtrId());
		lgsDlvsp.setRegDt(orderDTO.getRegDt());
		lgsDlvsp.setUdterId(orderDTO.getRegtrId());
		lgsDlvsp.setUdtDt(orderDTO.getRegDt());

		return lgsDlvsp;
	}

	public OrdGodExtends makeOrdGod(Ord ord, Map<String, String> column) throws Exception {
		BigDecimal bAllDc = new BigDecimal(column.get("godDc")).add(new BigDecimal(column.get("ordDc")))
						.add(new BigDecimal(column.get("godCpn"))).add(new BigDecimal(column.get("ordCpn")));

		OrdGodExtends ordGodExtends = new OrdGodExtends();
		ordGodExtends.setOrdNo(ord.getOrdNo());
		ordGodExtends.setOrdGodTurn(Integer.parseInt(column.get("godTurn")));
		ordGodExtends.setDlvPcupspTurn(Integer.parseInt(column.get("dlvPcupspTurn")));
		ordGodExtends.setGodNo(column.get("godNo"));
		ordGodExtends.setItmNo(column.get("itmNo"));
//		ordGod.setGodHistTurn(java.lang.Integer godHistTurn);
//		ordGod.setItmHistTurn(java.lang.Integer itmHistTurn);
		ordGodExtends.setGodNm(column.get("godNm"));
//		ordGod.setMobileGodNm(String mobileGodNm);
//		ordGod.setItmNm(String itmNm);
//		ordGod.setColorNm(String colorNm);
		ordGodExtends.setColorCd(column.get("colorCd"));
//		ordGod.setClorChipImgUrl(column.get("chipUrl"));
		ordGodExtends.setErpGodNo(column.get("erpGodNo"));
		ordGodExtends.setSkuNo(column.get("skuNo"));
		ordGodExtends.setBrndId(column.get("brndId"));

		// 2015-10-23  입점 구분 코드 추가 [AshA]
		ordGodExtends.setPartmalSectCd(column.get("partmalSectCd"));
		// 2015-11-18  상품 구분 코드 추가 [AshA]
		ordGodExtends.setGodTpCd(column.get("godTpCd"));

		ordGodExtends.setLstImgUrl(column.get("imgUrl"));
//		ordGod.setComId(String comId);
		ordGodExtends.setDmstcDlvCstPlcSn(Long.parseLong(column.get("dlvPolicy1")));
//		ordGod.setOvseaDlvCstPlcSn(column.get("dlvPolicy2"));
//		ordGod.setOvseaDlvDmstcDlvCstPlcSn(column.get("dlvPolicy3"));
		ordGodExtends.setSaleShopId(column.get("saleShopId"));
		
		
		ordGodExtends.setOrdQty(Long.parseLong(column.get("ordQty")));
		ordGodExtends.setCrncyCd(ord.getCrncyCd());

		BigDecimal bRowPayPrc = new BigDecimal(column.get("rowPayPrc"));
		if (bRowPayPrc.compareTo(BigDecimal.ZERO) < 0) {
			bRowPayPrc = BigDecimal.ZERO;
		}

		ordGodExtends.setStdrCrncyAmt(bRowPayPrc);
		ordGodExtends.setPayExchgRtCrncyAmt(bRowPayPrc);
		ordGodExtends.setRtlPrc(new BigDecimal(column.get("unitRtlPrc")));
		ordGodExtends.setSaleAmt(new BigDecimal(column.get("salePrc")));
		ordGodExtends.setWebDcAmt(new BigDecimal(column.get("webDc")));
		ordGodExtends.setEmpDcAmt(new BigDecimal(column.get("empDc")));
		ordGodExtends.setAllDcAmt(bAllDc);
		ordGodExtends.setImdtlDcAmt(new BigDecimal(column.get("imdtlUsePnt")));
		ordGodExtends.setGodDcAmt(new BigDecimal(column.get("gnrlGodDc")));
		//ordGodExtends.setSignlDcAmt(new BigDecimal(column.get("signlDc")));
		//ordGodExtends.setB2eSpslDcAmt(new BigDecimal(column.get("b2eSpslDc")));
		ordGodExtends.setBundleDcAmt(new BigDecimal(column.get("bundleDc")));
		ordGodExtends.setCrsDcAmt(new BigDecimal(column.get("crsDc")));
		ordGodExtends.setGodCpnDcAmt(new BigDecimal(column.get("godCpn")));
		ordGodExtends.setBskCpnDcAmt(new BigDecimal(column.get("ordCpn")));
		ordGodExtends.setUnityPntUseAmt(new BigDecimal(column.get("usePnt")));
//		ordGod.setEvtPntUseAmt(java.math.BigDecimal evtPntUseAmt);
        ordGodExtends.setWebpntUseAmt(new BigDecimal(column.get("useWebpnt"))); // P포인트
		ordGodExtends.setUnityPntAccmlAmt(new BigDecimal(column.get("savePnt")).add(new BigDecimal(column.get("addSavePnt"))));
//		ordGod.setEvtPntAccmlAmt(java.math.BigDecimal evtPntAccmlAmt);
        ordGodExtends.setWebpntAccmlAmt(new BigDecimal(column.get("saveWebpnt")).add(new BigDecimal(column.get("addSaveWebpnt")))); // P포인트
//		ordGod.setCalDt(java.util.Date calDt);

        
        if(column.get("hoffInvOrdQty")!=null){
        	ordGodExtends.setHoffInvOrdQty(Long.parseLong(column.get("hoffInvOrdQty")));
        }else{
        	ordGodExtends.setHoffInvOrdQty(0L);
        }
		ordGodExtends.setRegtrId(ord.getRegtrId());
		ordGodExtends.setRegDt(ord.getRegDt());
		ordGodExtends.setUdterId(ord.getRegtrId());
		ordGodExtends.setUdtDt(ord.getRegDt());
		ordGodExtends.setMallId(ord.getMallId());

		ordGodExtends.setInvManageYn(column.get("invManageYn"));
		ordGodExtends.setLmttInvYn(column.get("lmttInvYn"));
		ordGodExtends.setBasicPackYn(column.get("basicPack"));
		ordGodExtends.setPckageGodTpCd(column.get("pckageGodTpCd")); // '17 8.17 추가

		return ordGodExtends;
	}

	public List<OrdGodAplPrmExtends> makeOrdGodAplPrm(OrdGod ordGod, Map<String, String> column) throws Exception {
		List<OrdGodAplPrmExtends> promoList = new ArrayList<OrdGodAplPrmExtends>();

		OrdGodAplPrmExtends ordGodAplPrm = new OrdGodAplPrmExtends();
		ordGodAplPrm.setOrdNo(ordGod.getOrdNo());
		ordGodAplPrm.setOrdGodTurn(ordGod.getOrdGodTurn());
		ordGodAplPrm.setAplTpCd("APL");
		ordGodAplPrm.setAplQty(Long.parseLong(column.get("ordQty")));
		ordGodAplPrm.setRegtrId(ordGod.getRegtrId());
		ordGodAplPrm.setRegDt(ordGod.getRegDt());
		ordGodAplPrm.setUdterId(ordGod.getRegtrId());
		ordGodAplPrm.setUdtDt(ordGod.getRegDt());
		ordGodAplPrm.setGodNo(ordGod.getGodNo());

		// ordGodAplPrm.setOrdGodGftTurn(java.lang.Integer ordGodGftTurn);
		// ordGodAplPrm.setOrdGodGftNm(String ordGodGftNm);

		if (!StringService.isEmpty(column.get("godDcPrmNo"))) {
			String godDcType = "SUBD_GOD_DC";
			if ("B2E".equals(column.get("prcType"))) {
				godDcType = "B2E_SPSL";
			}
			else if ("SINGLE".equals(column.get("prcType"))) {
				godDcType = "SIGNL_SPSL";
			}
			ordGodAplPrm.setPrmNo(column.get("godDcPrmNo"));
			ordGodAplPrm.setPrmTpCd("GOD_DC");
			ordGodAplPrm.setPrmDtlTpCd(godDcType);
			ordGodAplPrm.setAplUnitCd("GOD");
			ordGodAplPrm.setAplAmt(new BigDecimal(column.get("godDc")));

			OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
			BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
			promoList.add(tmpPrm);
		}
		if (!StringService.isEmpty(column.get("ordDcPrmNo"))) {
			String ordDcType = "BUNDLE_DC";
			if (!"0".equals(column.get("crsDc"))) {
				ordDcType = "CRS_DC";
			}
			ordGodAplPrm.setPrmNo(column.get("ordDcPrmNo"));
			ordGodAplPrm.setPrmTpCd("ORD_DC");
			ordGodAplPrm.setPrmDtlTpCd(ordDcType);
			ordGodAplPrm.setAplUnitCd("ORD");
			ordGodAplPrm.setAplAmt(new BigDecimal(column.get("ordDc")));

			OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
			BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
			promoList.add(tmpPrm);
		}
		if (!StringService.isEmpty(column.get("godCpnPrmNo"))) {
			ordGodAplPrm.setPrmNo(column.get("godCpnPrmNo"));
			ordGodAplPrm.setPrmTpCd("CPN");
			ordGodAplPrm.setPrmDtlTpCd("GOD_CPN");
			ordGodAplPrm.setAplUnitCd("GOD");
			ordGodAplPrm.setAplAmt(new BigDecimal(column.get("godCpn")));
			ordGodAplPrm.setMbrCpnNo(column.get("godCpnCpnNo"));

			OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
			BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
			promoList.add(tmpPrm);
		}
		if (!StringService.isEmpty(column.get("ordCpnPrmNo"))) {
			ordGodAplPrm.setPrmNo(column.get("ordCpnPrmNo"));
			ordGodAplPrm.setPrmTpCd("CPN");
			ordGodAplPrm.setPrmDtlTpCd("BSK_CPN");
			ordGodAplPrm.setAplUnitCd("ORD");
			ordGodAplPrm.setAplAmt(new BigDecimal(column.get("ordCpn")));
			ordGodAplPrm.setMbrCpnNo(column.get("ordCpnCpnNo"));
			ordGodAplPrm.setCpnCrtfcCd(column.get("cpnCrtfcCd"));
			ordGodAplPrm.setOffLineCoupon("1".equals(column.get("isOffLineCoupon")) ? true : false);

			OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
			BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
			promoList.add(tmpPrm);
		}
		if (!StringService.isEmpty(column.get("addSavePrmNo"))) {
			ordGodAplPrm.setPrmNo(column.get("addSavePrmNo"));
			ordGodAplPrm.setPrmTpCd("MBSH_PNT");
			ordGodAplPrm.setPrmDtlTpCd("ADIT_SAV");
			ordGodAplPrm.setAplUnitCd("GOD");
			ordGodAplPrm.setAplAmt(new BigDecimal(column.get("addSavePnt")));

			OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
			BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
			promoList.add(tmpPrm);
		}
        // P포인트
        if (!StringService.isEmpty(column.get("addSaveWebPrmNo"))) {
            ordGodAplPrm.setPrmNo(column.get("addSaveWebPrmNo"));
            ordGodAplPrm.setPrmTpCd("WEBPNT");
            ordGodAplPrm.setPrmDtlTpCd("ADIT_SAV");
            ordGodAplPrm.setAplUnitCd("GOD");
            ordGodAplPrm.setAplAmt(new BigDecimal(column.get("addSaveWebpnt")));

            OrdGodAplPrmExtends tmpPrm = new OrdGodAplPrmExtends();
            BeanUtils.copyProperties(ordGodAplPrm, tmpPrm);
            promoList.add(tmpPrm);
        }
		return promoList;
	}

	public LgsDlv makeLgsDlv(LgsDlvsp lgsDlvsp, OrdGod paramOrdGod, LgsDlvspDTO lgsDlvspDTO) throws Exception {

		LgsDlv lgsDlv = new LgsDlv();
		lgsDlv.setOrdNo(paramOrdGod.getOrdNo());
		lgsDlv.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
		lgsDlv.setDlvTurn(orderCommandService.selectDeliverySequence(paramOrdGod.getOrdNo(), lgsDlvsp.getDlvPcupspTurn()));
//		lgsDlv.setClmNo(clmNo);

		// (1).배송비 정책
		lgsDlv.setDmstcDlvCstPlcSn(paramOrdGod.getDmstcDlvCstPlcSn());
//		lgsDlv.setOvseaDlvDmstcDlvCstPlcSn(paramOrdGod.getOvseaDlvDmstcDlvCstPlcSn());
		lgsDlv.setOvseaDlvCstPlcSn(paramOrdGod.getOvseaDlvCstPlcSn());
		/* get from god join com_dmstc_dlv_cst_plc
		lgsDlv.setDlvCstBndMbdCd(paramOrdGod.getDlvCstBndMbdCd());
		lgsDlv.setDlvComCd(dlvComCd);
		*/

		// (2).운송장번호 TODO - don't insert
//		lgsDlv.setDmstcWaybilNo(dmstcWaybilNo);
//		lgsDlv.setDmstcWaybilNoRegDt(dmstcWaybilNoRegDt);
//		lgsDlv.setOvseaWaybilNo(ovseaWaybilNo);
//		lgsDlv.setOvseaWaybilNoRegDt(ovseaWaybilNoRegDt);

		// (3).배송비정보
		lgsDlv.setCrncyCd(paramOrdGod.getCrncyCd());
		/* TODO - check!
		lgsDlv.setStdrCrncyAmt(stdrCrncyAmt);
		lgsDlv.setPayExchgRtCrncyAmt(payExchgRtCrncyAmt);
		lgsDlv.setRealityDlvCst(realityDlvCst);
		lgsDlv.setPlcDlvCst(plcDlvCst);
		*/
		if (!StringService.isEmpty(lgsDlvspDTO.getDeliveryCouponNo())) {
			lgsDlv.setDlvCstCpnDcAmt(lgsDlvspDTO.getDeliveryFee());
		}

		// (4).운송장번호 ERP 전송 - don't insert
		lgsDlv.setWaybilNoErpTrnsmisCd("N");
//		lgsDlv.setWaybilNoErpTrnsmisErrCont(waybilNoErpTrnsmisErrCont);
//		lgsDlv.setWaybilNoErpTrnsmisDt(waybilNoErpTrnsmisDt);

		// (5).배송연계
//TODO 추후 처리 변경
//		lgsDlv.setDlvComTrnsmisTgtYn("PKUP_DLV".equals(lgsDlvsp.getDlvMnCd()) ? "N" : "Y");
		lgsDlv.setDlvComTrnsmisYn("N");
//		lgsDlv.setDlvComTrnsmisDt("N");- don't insert
		lgsDlv.setMailSndYn("N");

		lgsDlv.setRegtrId(paramOrdGod.getRegtrId());
		lgsDlv.setRegDt(paramOrdGod.getRegDt());
		lgsDlv.setUdterId(paramOrdGod.getRegtrId());
		lgsDlv.setUdtDt(paramOrdGod.getRegDt());

		return lgsDlv;
	}

	public Pay makePay(Pay pay, Map<String, String> resultMap, Ord ord) throws Exception {
		String payMnCd = pay.getPayMnCd();

		// (1).결제기본정보
		// pay.setPayNo(this.selectDBNumber("SQ_PAY", "ST"));

		pay.setDealTpCd(!"VIRTL_BNK_ACCT_PAY".equals(payMnCd) ? "PAY_COMPT" : "DEPST_WAIT");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		if (!StringService.isEmpty(resultMap.get("approval_ymdhms"))) {
			pay.setPayDt(sdf.parse(resultMap.get("approval_ymdhms")));
		}
		if (!StringService.isEmpty(resultMap.get("income_limit_ymd"))) {
			// TODO - mb 테스트 계정에서 입금기한일은 null 롤 리턴된다. 임시로 null 처리함. 실운영에서 모바일 입금기한일 데이터 확인해야함.
			String limitDt = "";
			if("null".equals(resultMap.get("income_limit_ymd"))) {
				limitDt = StringService.substring(sdf.format(new Date()), 0,8);
			} else {
				limitDt = resultMap.get("income_limit_ymd");
			}
			pay.setPayTmlmtDt(sdf.parse(limitDt + "235959"));
			//pay.setPayTmlmtDt(sdf.parse(resultMap.get("income_limit_ymd") + "235959"));

		}

		// (3).PG정보
		String pgSectCd = "PG";
		if ("PG".equals(payMnCd)) {
			pgSectCd = "PG";
		}
		
		pay.setPgAprvNo(resultMap.get("seq_no"));	// seq_no, tid, tid
		pay.setPgDealNo(resultMap.get("method_no"));	// sApprovalNo, userKey, authCode
		pay.setPgSectCd(pgSectCd);
		pay.setPgStoreId(resultMap.get("store_id"));
		pay.setPgCrsKey(resultMap.get("store_key"));

		String sSaveAmt = StringService.nvl(resultMap.get("save_amt"), "0");
		pay.setCardSvDealYn("N"); // 카드세이브거래
		pay.setEscrYn("N");
		if (sSaveAmt.compareTo("0") > 0) {
			pay.setCardSvDealYn("Y"); // 카드세이브거래
		}
		if ("Y".equals(resultMap.get("escrow_yn"))) {
			pay.setEscrYn("Y");
		}

		// (4).가상계좌정보
		pay.setAcnthldrNm(resultMap.get("account_nm"));
		pay.setBnkCd(resultMap.get("bank_id"));
		pay.setBnkNm(resultMap.get("bank_nm"));
		pay.setBnkAcctNo(resultMap.get("account_no"));
		// pay.setVirtlBnkAcctValidDt(resultMap.get("account_nm"));

		pay.setRegtrId(ord.getRegtrId());
		pay.setRegDt(ord.getRegDt());
		pay.setUdterId(ord.getRegtrId());
		pay.setUdtDt(ord.getRegDt());
		return pay;
	}
	
	public Pay makeDummyPay(Pay pay, Ord ord) throws Exception {
		String payMnCd = pay.getPayMnCd();

		// (1).결제기본정보
		// pay.setPayNo(this.selectDBNumber("SQ_PAY", "ST"));

		pay.setDealTpCd(!"VIRTL_BNK_ACCT_PAY".equals(payMnCd) ? "PAY_COMPT" : "DEPST_WAIT");
		
		Date dt = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if (!StringService.isEmpty(sdf.format(dt))) {
			pay.setPayDt(sdf.parse(sdf.format(dt)));
		}
		/*
		if (!StringService.isEmpty(resultMap.get("income_limit_ymd"))) {
			// TODO - mb 테스트 계정에서 입금기한일은 null 롤 리턴된다. 임시로 null 처리함. 실운영에서 모바일 입금기한일 데이터 확인해야함.
			String limitDt = "";
			if("null".equals(resultMap.get("income_limit_ymd"))) {
				limitDt = StringService.substring(sdf.format(new Date()), 0,8);
			} else {
				limitDt = resultMap.get("income_limit_ymd");
			}
			pay.setPayTmlmtDt(sdf.parse(limitDt + "235959"));
			//pay.setPayTmlmtDt(sdf.parse(resultMap.get("income_limit_ymd") + "235959"));

		}
		*/
		
		if ("VIRTL_BNK_ACCT_PAY".equals(payMnCd)) {
			pay.setPayTmlmtDt(sdf.parse("29991231" + "235959"));
		}

		// (3).PG정보
		String pgSectCd = "PG";
		if ("PG".equals(payMnCd)) {
			pgSectCd = "PG";
		}
	
		pay.setPgAprvNo(sdf.format(dt));	// seq_no, tid, tid
		pay.setPgDealNo(sdf.format(dt));	// sApprovalNo, userKey, authCode
		pay.setPgSectCd(pgSectCd);
		pay.setPgStoreId("plgrim_2018");
		pay.setPgCrsKey("plgrim_2018");

		String sSaveAmt = "0";
		pay.setCardSvDealYn("N"); // 카드세이브거래
		pay.setEscrYn("N");
		if (sSaveAmt.compareTo("0") > 0) {
			pay.setCardSvDealYn("Y"); // 카드세이브거래
		}
		pay.setEscrYn("N");
		/*
		if ("Y".equals(resultMap.get("escrow_yn"))) {
			pay.setEscrYn("Y");
		}
		*/

		// (4).가상계좌정보
		if ("VIRTL_BNK_ACCT_PAY".equals(payMnCd)) {
			pay.setAcnthldrNm("플그림");
			pay.setBnkCd("01");
			pay.setBnkNm("신한은행");
			pay.setBnkAcctNo("12345678");
		}

		pay.setRegtrId(ord.getRegtrId());
		pay.setRegDt(ord.getRegDt());
		pay.setUdterId(ord.getRegtrId());
		pay.setUdtDt(ord.getRegDt());
		return pay;
	}
	
	public void makeNaverPay(OrderDTO orderDTO,NaverReturnDTO naverReturnDTO) throws Exception {
		Pay pay = orderDTO.getPay();
		Ord ord = orderDTO.getOrd();
		
		pay.setPayMnCd(OrderEnum.PAY_MN_CD.NAVER_PAY.toString());
		pay.setPgAprvNo(orderDTO.getNaverPayApprove().getPaymentId());
		if(naverReturnDTO.getBody()!=null&&naverReturnDTO.getBody().getDetail()!=null&&naverReturnDTO.getBody().getDetail().getMerchantPayKey()!=null){
			pay.setPayNo(naverReturnDTO.getBody().getDetail().getMerchantPayKey());
			if(naverReturnDTO.getBody().getDetail().getTotalPayAmount()!=null&&naverReturnDTO.getBody().getDetail().getTotalPayAmount().longValue()>0){
				orderDTO.getNaverPayApprove().setTempTotalPayment(naverReturnDTO.getBody().getDetail().getTotalPayAmount());
			}
		}

		pay.setDealTpCd(OrderEnum.ORD_STAT_PAY_COMPT.toString());
		
		Date dt = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if (!StringService.isEmpty(sdf.format(dt))) {
			pay.setPayDt(sdf.parse(sdf.format(dt)));
		}

		
		//pay.setPgSectCd(pgSectCd);
		//mall 분리
		log.debug("Mall ID : " + orderDTO.getMallId());
		if("MBM".equals(orderDTO.getMallId())) {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientId"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.naverPay.mlb.clientSecret"));
		} else if("SAM".equals(orderDTO.getMallId())) {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.naverPay.sa.clientId"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.naverPay.sa.clientSecret"));
		} else {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.naverPay.clientId"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.naverPay.clientSecret"));
		}
		
		
		pay.setCardSvDealYn("N"); // 카드세이브거래
		pay.setEscrYn("N");
		
		pay.setRegtrId(ord.getRegtrId());
		pay.setRegDt(ord.getRegDt());
		pay.setUdterId(ord.getRegtrId());
		pay.setUdtDt(ord.getRegDt());
	}
	
	public void makeKcpPay(OrderDTO orderDTO,KcpReturnDTO kcpReturnDTO) throws Exception {
		Pay pay = orderDTO.getPay();
		Ord ord = orderDTO.getOrd();
		
		pay.setPayNo(orderDTO.getKcpDTO().getOrdr_idxx());
		pay.setPgAprvNo(kcpReturnDTO.getTno());

		orderDTO.getKcpDTO().setTno(kcpReturnDTO.getTno());
		
		if(OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
			pay.setDealTpCd(OrderEnum.ORD_STAT_DEPST_WAIT.toString());
			pay.setPayMnCd(OrderEnum.kcpPayComp.VIRTL_BNK_ACCT_PAY.getLocalValue());
			
			pay.setBnkCd(kcpReturnDTO.getBankCode());
			pay.setBnkNm(kcpReturnDTO.getBankName());
			
			pay.setAcnthldrNm(kcpReturnDTO.getDepositor());
			pay.setBnkAcctNo(kcpReturnDTO.getAccount());
			
			if(kcpReturnDTO.getVaDate()!=null&&kcpReturnDTO.getVaDate().length()>0){
				SimpleDateFormat kcpTimeSdf = new SimpleDateFormat("yyyyMMddHHmmss");
				pay.setPayTmlmtDt(kcpTimeSdf.parse(kcpReturnDTO.getVaDate()));
			}
		}else{
			pay.setDealTpCd(OrderEnum.ORD_STAT_PAY_COMPT.toString());
			if(OrderEnum.kcpPayComp.CREDT_CARD_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
				pay.setPayMnCd(OrderEnum.kcpPayComp.CREDT_CARD_PAY.getLocalValue());
				
				pay.setPayCardNm(kcpReturnDTO.getCardName());
				pay.setPartCnclPsbYn(kcpReturnDTO.getPartcancYn());
				
				
			}else if(OrderEnum.kcpPayComp.RLTM_BNK_ACCT_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
				pay.setPayMnCd(OrderEnum.kcpPayComp.RLTM_BNK_ACCT_PAY.getLocalValue());
				
				pay.setBnkCd(kcpReturnDTO.getBankCode());
				pay.setBnkNm(kcpReturnDTO.getBankName());
				
			}else if(OrderEnum.kcpPayComp.MOBIL_PON_PAY.getKcpValue().equals(orderDTO.getKcpDTO().getUse_pay_method())){
				pay.setPayMnCd(OrderEnum.kcpPayComp.MOBIL_PON_PAY.getLocalValue());
				
				
			}
			
		}
		
		Date dt = new Date(System.currentTimeMillis());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		if (!StringService.isEmpty(sdf.format(dt))) {
			pay.setPayDt(sdf.parse(sdf.format(dt)));
		}

		
		//pay.setPgSectCd(pgSectCd);
		//mall 분리
		log.debug("Mall ID : " + orderDTO.getMallId());
		if("MBM".equals(orderDTO.getMallId())) {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.cd"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.key"));
			String cstrno = deliverySelectRepository.getVipList();
			Mbr mbr = orderDTO.getMbr();   
            if(mbr!=null&&mbr.getErpCstmrNo()!=null&&cstrno.indexOf(mbr.getErpCstmrNo())!=-1){
    			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.cd"));
    			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.kcp.mlb.site.vip.key"));
            }
			

		} else if("SAM".equals(orderDTO.getMallId())) {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.kcp.sa.site.cd"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.kcp.sa.site.key"));
		} else {
			pay.setPgStoreId(getConfigService().getProperty("ncp_base.order.kcp.site.cd"));
			pay.setPgCrsKey(getConfigService().getProperty("ncp_base.order.kcp.site.key"));
		}
		


		pay.setCardSvDealYn("N"); // 카드세이브거래
		if(kcpReturnDTO.getEscwYn()!=null&&!"".equals(kcpReturnDTO.getEscwYn())){
			pay.setEscrYn(kcpReturnDTO.getEscwYn());
			
			if ("Y".equals(kcpReturnDTO.getEscwYn())) {	// 에스크로의 경우 부분취소 불가
				pay.setPartCnclPsbYn("N");
			}
			
		}else{
			pay.setEscrYn("N");
		}
		
		pay.setRegtrId(ord.getRegtrId());
		pay.setRegDt(ord.getRegDt());
		pay.setUdterId(ord.getRegtrId());
		pay.setUdtDt(ord.getRegDt());
		
	}
	
	public void makeAfterKcpPay(OrderDTO orderDTO, KcpReturnDTO kcpReturnDTO) throws Exception {
		
	}

	private BigDecimal makeRound(BigDecimal bTmpAmt, BigDecimal bOrdGodCnt, int movePoint) {
		// >>> 100.123 => 10.0123 => 10 => 100
		// >>> 100.499 => 10.0499 => 10 => 100
		// >>> 100.501 => 10.0501 => 10 => 100
		// >>> 104.123 => 10.4123 => 10 => 100
		// >>> 104.499 => 10.4499 => 10 => 100
		// >>> 104.601 => 10.4601 => 10 => 100
		// >>> 105.123 => 10.5123 => 11 => 110
		// >>> 105.499 => 10.5499 => 11 => 110
		// >>> 105.501 => 10.5501 => 11 => 110
		BigDecimal bUnitAmt = bTmpAmt.divide(bOrdGodCnt);
		return bUnitAmt.movePointLeft(movePoint).setScale(0, RoundingMode.HALF_UP).movePointRight(movePoint);
	}

//	private BigDecimal makeLastAmt(BigDecimal bTmpAmt, BigDecimal bOrdGodCnt, int movePoint) {
//		BigDecimal prevSum = makeRound(bTmpAmt, bOrdGodCnt, movePoint).multiply(bOrdGodCnt.subtract(new BigDecimal(1)));
//		return bTmpAmt.subtract(prevSum);
//	}

	public List<InfOrdGodErpDstbExtends> makeInfOrdGodErpDstb(OrdGodExtends ordGodExtends) throws Exception {
		List<InfOrdGodErpDstbExtends> infErpList = new ArrayList<InfOrdGodErpDstbExtends>();

		InfOrdGodErpDstbExtends erp = new InfOrdGodErpDstbExtends();

		erp.setOrdNo(ordGodExtends.getOrdNo());
		erp.setOrdGodTurn(ordGodExtends.getOrdGodTurn());

		erp.setCrncyCd(ordGodExtends.getCrncyCd());
		erp.setSkuNo(ordGodExtends.getSkuNo());
		erp.setRtlPrc(ordGodExtends.getRtlPrc());
		erp.setGiftYn(ordGodExtends.isGift() ? "Y" : "N");

		/**
		 * ERP 연동 대상 여부
		 * ㅁ. 입점 업체의 상품일 경우 N
		 */
		// 2015-11-23 자사 상품인 경우에만 ERP 연동 처리 [AshA]
		if ("MCOM".equals(ordGodExtends.getPartmalSectCd())) {
			erp.setErpIntrlckTgtYn("Y");
		} else {
			erp.setErpIntrlckTgtYn("N");
		}

		erp.setClmErpIntrlckYn("N");
		erp.setCntrDlivyCnfirmYn("N");
		erp.setDlivyDrctTgtYn("N");
		erp.setDlivyDrctYn("N");
		erp.setResveRcptfrCreatYn("N");
		erp.setResveRcptfrDlivyYn("N");
		erp.setResveRcptfrDcsnYn("N");
		erp.setErpResveRcptfrCreatYn("N");
		erp.setErpResveRcptfrRecreatYn("N");
		erp.setErpResveRcptfrCnclYn("N");
		erp.setRepairComptYn("N");
		erp.setRtgodShopInvMvmtYn("N");
		erp.setRtgodRcptfrCreatYn("N");
		erp.setCpErrYn("N");
		erp.setStErrYn("N");
		erp.setErpGodSnModYn("N");
		erp.setDlivyAcptYn("N");
		erp.setWrhsAcptYn("N");


		int ordQty = (int) (long) ordGodExtends.getOrdQty();
		BigDecimal bOrdQty = new BigDecimal(ordQty);
		for (int i = 1; i <= ordQty; i++) {
			boolean isLast = (i == ordQty);

			erp.setOrdGodQtyTurn(i);

			erp.setStdrCrncyUntPrc(this.countDivision(ordGodExtends.getStdrCrncyAmt(), bOrdQty, isLast));
			erp.setPayExchgRtCrncyUntPrc(this.countDivision(ordGodExtends.getPayExchgRtCrncyAmt(), bOrdQty, isLast));
			erp.setSaleUntPrc(this.countDivision(ordGodExtends.getSaleAmt(), bOrdQty, isLast));
			erp.setWebDcUntPrc(this.countDivision(ordGodExtends.getWebDcAmt(), bOrdQty, isLast));
			erp.setEmpDcUntPrc(this.countDivision(ordGodExtends.getEmpDcAmt(), bOrdQty, isLast));
			erp.setImdtlDcUntPrc(this.countDivision(ordGodExtends.getImdtlDcAmt(), bOrdQty, isLast));
			erp.setGodDcUntPrc(this.countDivision(ordGodExtends.getGodDcAmt(), bOrdQty, isLast));
			//erp.setSignlDcUntPrc(this.countDivision(ordGodExtends.getSignlDcAmt(), bOrdQty, isLast));
			//erp.setB2eSpslDcUntPrc(this.countDivision(ordGodExtends.getB2eSpslDcAmt(), bOrdQty, isLast));
			erp.setBundleDcUntPrc(this.countDivision(ordGodExtends.getBundleDcAmt(), bOrdQty, isLast));
			erp.setCrsDcUntPrc(this.countDivision(ordGodExtends.getCrsDcAmt(), bOrdQty, isLast));
			erp.setGodCpnDcUntPrc(this.countDivision(ordGodExtends.getGodCpnDcAmt(), bOrdQty, isLast));
			erp.setBskCpnDcUntPrc(this.countDivision(ordGodExtends.getBskCpnDcAmt(), bOrdQty, isLast));
			erp.setUnityPntUseUntPrc(this.countDivision(ordGodExtends.getUnityPntUseAmt(), bOrdQty, isLast));
			erp.setUnityPntAccmlUntPrc(this.countDivision(ordGodExtends.getUnityPntAccmlAmt(), bOrdQty, isLast));
            erp.setWebpntUseUntPrc(this.countDivision(ordGodExtends.getWebpntUseAmt(), bOrdQty, isLast));
            erp.setWebpntAccmlUntPrc(this.countDivision(ordGodExtends.getWebpntAccmlAmt(), bOrdQty, isLast));

            InfOrdGodErpDstbExtends tmpErp = new InfOrdGodErpDstbExtends();
			BeanUtils.copyProperties(erp ,tmpErp);
			infErpList.add(tmpErp);
		}

		//포인트,P포인트사용금액체크 - 수량 분배시 내림에 따른 판매가보다 큰 포인트사용금액 발생, 총포인트사용금액에는 이상없음
		this.checkDistributePointOver(infErpList);

		//분배대상이 아닌 계산에 의해 구해야될 금액 (판매가, 결제대상금액)
		for (InfOrdGodErpDstbExtends infErp : infErpList )
		{
			infErp.setSaleUntPrc( infErp.getRtlPrc().subtract(infErp.getWebDcUntPrc())
													.subtract(infErp.getEmpDcUntPrc()));

			infErp.setStdrCrncyUntPrc( infErp.getSaleUntPrc().subtract(infErp.getImdtlDcUntPrc())
															 .subtract(infErp.getGodDcUntPrc())
															 //.subtract(infErp.getSignlDcUntPrc())
															 //.subtract(infErp.getB2eSpslDcUntPrc())
															 .subtract(infErp.getBundleDcUntPrc())
															 .subtract(infErp.getCrsDcUntPrc())
															 .subtract(infErp.getGodCpnDcUntPrc())
															 .subtract(infErp.getBskCpnDcUntPrc())
															 .subtract(infErp.getUnityPntUseUntPrc())
															 .subtract(infErp.getWebpntUseUntPrc())	);

			infErp.setPayExchgRtCrncyUntPrc( infErp.getStdrCrncyUntPrc() );
		}

		return infErpList;
	}

	/**
	 * 수량당 분배가
	 * @param rowPrc BigDecimal target price
	 * @param goodsCount BigDecimal goods count
	 * @param idx int looping sequence
	 * @return BigDecimal
	 */
	private BigDecimal countDivision(BigDecimal rowPrc, BigDecimal goodsCount, boolean isLast) {
		// 수량당 분할가(1원자리 반올림) = 타겟가격 / 수량
		BigDecimal bTmpPrc = rowPrc.divide(goodsCount, 10, RoundingMode.DOWN);
		BigDecimal bUnitPrc = this.rounddown(bTmpPrc, 1); // 1원자리 내림 (마지막금액 마이너스발생 방지)
		if (isLast) {
			bUnitPrc = bUnitPrc.add(rowPrc.subtract(bUnitPrc.multiply(goodsCount)));
		}
		return bUnitPrc;
	}

	private BigDecimal halfup(BigDecimal bTempAmt, int movePoint) {
		// >>> 100.123 => 10.0123 => 10 => 100
		// >>> 100.499 => 10.0499 => 10 => 100
		// >>> 100.501 => 10.0501 => 10 => 100
		// >>> 104.123 => 10.4123 => 10 => 100
		// >>> 104.499 => 10.4499 => 10 => 100
		// >>> 104.601 => 10.4601 => 10 => 100
		// >>> 105.123 => 10.5123 => 11 => 110
		// >>> 105.499 => 10.5499 => 11 => 110
		// >>> 105.501 => 10.5501 => 11 => 110
		return bTempAmt.movePointLeft(movePoint).setScale(0, RoundingMode.HALF_UP).movePointRight(movePoint);
	}

	private BigDecimal rounddown(BigDecimal bTempAmt, int movePoint){
		return bTempAmt.movePointLeft(movePoint).setScale(0, RoundingMode.DOWN).movePointRight(movePoint);
	}

	private void checkDistributePointOver(List<InfOrdGodErpDstbExtends> infErpList) {

		int ordQty				= infErpList.size();
		boolean isWebPntUse		= (infErpList.get(0).getWebpntUseUntPrc() != null && infErpList.get(0).getWebpntUseUntPrc().compareTo(BigDecimal.ZERO)>0) ? true : false;
		boolean isUnityPntUse	= (infErpList.get(0).getUnityPntUseUntPrc() != null && infErpList.get(0).getUnityPntUseUntPrc().compareTo(BigDecimal.ZERO)>0) ? true : false;;

		if(ordQty < 2 || (!isWebPntUse && !isUnityPntUse)) {	return;	}


		//마지막수량 금액체크
		{
			BigDecimal bAllDcAmt 		= infErpList.get(ordQty-1).getWebDcUntPrc()
															  	 	 .add(infErpList.get(ordQty-1).getEmpDcUntPrc())
															  	 	 .add(infErpList.get(ordQty-1).getGodDcUntPrc())
															  	 	 //.add(infErpList.get(ordQty-1).getSignlDcUntPrc())
															  	 	 //.add(infErpList.get(ordQty-1).getB2eSpslDcUntPrc())
															  	 	 .add(infErpList.get(ordQty-1).getBundleDcUntPrc())
															  	 	 .add(infErpList.get(ordQty-1).getCrsDcUntPrc())
															  	 	 .add(infErpList.get(ordQty-1).getGodCpnDcUntPrc())
															  	 	 .add(infErpList.get(ordQty-1).getBskCpnDcUntPrc());

			BigDecimal bPntUseAmt		= infErpList.get(ordQty-1).getUnityPntUseUntPrc()
																	.add(infErpList.get(ordQty-1).getImdtlDcUntPrc())
																	.add(infErpList.get(ordQty-1).getWebpntUseUntPrc());

			BigDecimal bRtlAmt			= infErpList.get(ordQty-1).getRtlPrc();

			BigDecimal bAdjustAmt		= bRtlAmt.subtract( bAllDcAmt.add(bPntUseAmt) );

			if(bAdjustAmt.compareTo(BigDecimal.ZERO)<0){
				bAdjustAmt = bAdjustAmt.abs();
				BigDecimal[] bDivideAndRemainder = bAdjustAmt.divideAndRemainder(new BigDecimal(ordQty-1));
				if(isUnityPntUse){
					for(int i=0; i<ordQty-1; i++){
						infErpList.get(i).setUnityPntUseUntPrc(  infErpList.get(i).getUnityPntUseUntPrc().add(bDivideAndRemainder[0])  );
					}
					if(bDivideAndRemainder[1].compareTo(BigDecimal.ZERO)!=0){
						infErpList.get(0).setUnityPntUseUntPrc(  infErpList.get(0).getUnityPntUseUntPrc().add(bDivideAndRemainder[1])  );
					}
					infErpList.get(ordQty-1).setUnityPntUseUntPrc( infErpList.get(ordQty-1).getUnityPntUseUntPrc().subtract(bAdjustAmt) );
				}else{
					for(int i=0; i<ordQty-1; i++){
						infErpList.get(i).setWebpntUseUntPrc(  infErpList.get(i).getWebpntUseUntPrc().add(bDivideAndRemainder[0])  );
					}
					if(bDivideAndRemainder[1].compareTo(BigDecimal.ZERO)!=0){
						infErpList.get(0).setWebpntUseUntPrc( infErpList.get(0).getWebpntUseUntPrc().add(bDivideAndRemainder[1]) );
					}
					infErpList.get(ordQty-1).setWebpntUseUntPrc( infErpList.get(ordQty-1).getWebpntUseUntPrc().subtract(bAdjustAmt) );
				}

			}
		}

	}


}
