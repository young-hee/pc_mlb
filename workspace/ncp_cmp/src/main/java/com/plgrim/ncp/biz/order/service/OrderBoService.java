package com.plgrim.ncp.biz.order.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.god.GodShopItmInvExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.*;
import com.plgrim.ncp.base.entities.datasource1.ord.*;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShopBrnd;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.repository.lgs.*;
import com.plgrim.ncp.base.repository.ord.OrdGodOptModRepository;
import com.plgrim.ncp.base.repository.ord.OrdGodRepository;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.biz.order.repository.OrderBoSelectRepository;
import com.plgrim.ncp.biz.order.repository.OrderCommandRepository;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderBoService extends AbstractService {

	private static final Logger log = LoggerFactory
	        .getLogger(OrderBoService.class);
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	@Autowired
	OrderBoCommandRepository orderBoCommandRepository;

	@Autowired
	OrderBoSelectRepository orderBoSelectRepository;

	@Autowired
	OrdGodOptModRepository ordGodOptModRepository;

	@Autowired
	OrdGodRepository ordGodRepository;// 주문 상품

	@Autowired
	OrderCommandRepository orderCommandRepository;

	@Autowired
	LgsDlvspRepository lgsDlvspRepository;// 물류배송지

	@Autowired
	LgsDlvspHistRepository lgsDlvspHistRepository;// 물류배송지 이력

	@Autowired
	LgsDlvRepository lgsDlvRepository;// 물류배송

	@Autowired
	LgsDlvHistRepository lgsDlvHistRepository;// 물류배송 이력

	@Autowired
	LgsDlivyDrctGodRepository lgsDlivyDrctGodRepository;// 물류 출고지시 상품

	@Autowired
	LgsDlivyDrctGodHistRepository lgsDlivyDrctGodHistRepository;// 물류/ 출고지시상품이력
	
	@Autowired
	OrderSelectService orderSelectService;

	/** 주문상태 수정 */
	public int updatelagQtyOrdDcsn(Ord ord) throws Exception {
		return orderBoCommandRepository.updatelagQtyOrdDcsn(ord);
	}

	/** 입금확정완료 */
	public int confirmDeposit(Ord ord) throws Exception {
		return orderBoCommandRepository.confirmDeposit(ord);
	}

	/** 주문상태 수정 */
	public int updateOrdStatCd(Ord ord)  throws Exception {
		int retValue = 0;
		boolean flag = true;

		if ( "DLV_PRPARE".equals(ord.getOrdStatCd()) || "DLV_PROGRS".equals(ord.getOrdStatCd()) || "DLV_COMPT".equals(ord.getOrdStatCd()) ) {
			String ordStatCd = orderBoSelectRepository.getOrdStatCd(ord);

			if ( "DLV_PRPARE".equals(ord.getOrdStatCd()) && ("DLV_PROGRS".equals(ordStatCd) || "DLV_COMPT".equals(ordStatCd)) ) {
				flag = false;
			}

			if ( "DLV_PROGRS".equals(ord.getOrdStatCd()) && "DLV_COMPT".equals(ordStatCd)) {
				flag = false;
			}

			if ("DLV_COMPT".equals(ordStatCd)) {
				flag = false;
			}
		}

		if (flag) {
			retValue = orderBoCommandRepository.updateOrdStatCd(ord);
		}

		return retValue;
	}

	/** 주문상태 배송완료 수정 */
	public int updateOrdStatAboutCompt(Ord ord)  throws Exception {
		int retValue = 0;
		boolean flag = true;

		if ( "DLV_PRPARE".equals(ord.getOrdStatCd()) || "DLV_PROGRS".equals(ord.getOrdStatCd()) || "DLV_COMPT".equals(ord.getOrdStatCd()) ) {
			String ordStatCd = orderBoSelectRepository.getOrdStatCd(ord);

			if ( "DLV_PRPARE".equals(ord.getOrdStatCd()) && ("DLV_PROGRS".equals(ordStatCd) || "DLV_COMPT".equals(ordStatCd)) ) {
				flag = false;
			}

			if ( "DLV_PROGRS".equals(ord.getOrdStatCd()) && "DLV_COMPT".equals(ordStatCd)) {
				flag = false;
			}

			if ("DLV_COMPT".equals(ordStatCd)) {
				flag = false;
			}
		}

		if (flag) {
			retValue = orderBoCommandRepository.updateOrdStatAboutCompt(ord);
		}

		return retValue;
	}

	public int virtlDlvCompt(Ord ord) throws Exception {
		return orderBoCommandRepository.virtlDlvCompt(ord);
	}
	
	public int virtlDlvCompt4PayWait(Ord ord) throws Exception {
		return orderBoCommandRepository.virtlDlvCompt4PayWait(ord);
	}

	public void updateGoodsItmOrd(GodItm godItm) throws Exception {
		orderBoCommandRepository.updateGoodsItmOrd(godItm);
	}

	public void updateGoodsShopItmInvOrd(GodShopItmInvExtend godShopItmInvExtend) throws Exception {
		orderBoCommandRepository.updateGoodsShopItmInvOrd(godShopItmInvExtend);
	}


	public void optionChange(OrderBoDTO orderDTO, GodExtend godExtend) throws Exception {

		String regtrId = orderDTO.getRegtrId();

		OrdGodExtend ordGodExtend = new OrdGodExtend();
		ordGodExtend.setGodNo(orderDTO.getGodNo());
		ordGodExtend.setItmNo(orderDTO.getItmNo());
		ordGodExtend.setOrdNo(orderDTO.getOrdNo());
		ordGodExtend.setOrdGodTurn(orderDTO.getOrdGodTurn());
		ordGodExtend.setItmNm(godExtend.getItmNm());
		ordGodExtend.setItmHistTurn(godExtend.getItmHistTurn());

		ordGodExtend.setSkuNo(godExtend.getSkuNo());

		String modBfItmNo = ordGodRepository.selectOrdGod(ordGodExtend).getItmNo();

		ordGodExtend.setUdterId(regtrId);
		orderBoCommandRepository.updateOptionChange(ordGodExtend);

		String optModNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_ORD_GOD_OPT_MOD", "OC", DatabaseType.ORACLE);

		OrdGodOptMod ordGodOptMod = new OrdGodOptMod();

		ordGodOptMod.setOptModNo(optModNo);
		ordGodOptMod.setOrdNo(orderDTO.getOrdNo());
		ordGodOptMod.setOrdGodTurn(orderDTO.getOrdGodTurn());
		ordGodOptMod.setModBfItmNo(modBfItmNo);
		ordGodOptMod.setModAfItmNo(orderDTO.getItmNo());
		ordGodOptMod.setPchhistRedctTrnsmisYn("N");
		ordGodOptMod.setPchhistAditTrnsmisYn("N");
		ordGodOptMod.setUdterId(regtrId);
		ordGodOptMod.setRegtrId(regtrId);
		ordGodOptModRepository.insertOrdGodOptMod(ordGodOptMod);




	}



	/**
	 * 주문 - 교환접수 시 주문상품 등록
	 */
	public void insertOrdGodForClm(OrdGodExtend ordGodExtend) throws Exception {
		orderBoCommandRepository.insertOrdGodForClm(ordGodExtend);
	}


	/**
	 * 주문 - 교환접수 시 주문상품적용프로모션 등록
	 */
	public void insertOrdGodAplPrmForClm(OrdGodAplPrmExtend ordGodAplPrmExtend) throws Exception {
		orderBoCommandRepository.insertOrdGodAplPrmForClm(ordGodAplPrmExtend);
	}


	/**
	 * 주문 - 교환접수 시 구성상품연결 등록
	 */
	public void insertOrdCpstGodCnncForClm(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		orderBoCommandRepository.insertOrdCpstGodCnncForClm(ordCpstGodCnnc);
	}
	
	/**
	 * 주문 - 교환접수 시 주문상품적용프로모션 등록
	 */
	public void insertOrdGodAplPrmForClmList(OrdGodExtend ordGodExtend, Map<String, Object> conditions, String regtrId) throws Exception {

		//String prmTpCd 	= "";
		String prmDtlTpCd	= "";
		int newAplPrmTurn 	= 0;
		BigDecimal aplAmt 	= new BigDecimal("0");  /* 적용금액 */
		BigDecimal payExchgRtCrncyUntSumPrc	= new BigDecimal("0");  /* 결제 환율 통화 단가 */
		BigDecimal ordQty	= new BigDecimal("0");  /* 클레임수량 */

		OrdGodAplPrm ordGodAplPrm = null;

		OrdGodAplPrmExtend selOrdGodAplPrm = new OrdGodAplPrmExtend();
		selOrdGodAplPrm.setOrdNo(ordGodExtend.getOrdNo());
		selOrdGodAplPrm.setOrdGodTurn(ordGodExtend.getOrdGodTurn());

        List<OrdGodAplPrmExtend> ordGodAplPrmList = orderBoCommandRepository.selectOrdGodAplPrmForClmList(selOrdGodAplPrm);
		for (OrdGodAplPrmExtend ordGodAplPrmExtend : ordGodAplPrmList) 
		{

			//prmTpCd 	= ordGodAplPrmExtend.getPrmTpCd();
			prmDtlTpCd 	= ordGodAplPrmExtend.getPrmDtlTpCd();

			if(StringService.equalsIgnoreCase(prmDtlTpCd, "GOD_CPN"))
			{
				//상품쿠폰
				aplAmt = ordGodExtend.getGodCpnDcAmt();				
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "BSK_CPN")) {
				//장바구니쿠폰
				aplAmt = ordGodExtend.getBskCpnDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "SUBD_GOD_DC")) {
				//상품할인
				aplAmt = ordGodExtend.getGodDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "SIGNL_SPSL")) {
				//Single특판
				//aplAmt = ordGodExtend.getSignlDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "B2E_SPSL")) {
				//B2E특판
				//aplAmt = ordGodExtend.getB2eSpslDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "BUNDLE_DC")) {
				//묶음할인
				//aplAmt = ordGodExtend.getB2eSpslDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "CRS_DC")) {
				//교차할인
				aplAmt = ordGodExtend.getCrsDcAmt();
			} else if(StringService.equalsIgnoreCase(prmDtlTpCd, "ADIT_SAV")) {
				//추가적립
				aplAmt = ordGodExtend.getUnityPntAccmlAmt();
			} else {
				//payExchgRtCrncyUntSumPrc / 수량
				payExchgRtCrncyUntSumPrc = ordGodExtend.getPayExchgRtCrncyAmt();
				ordQty = new BigDecimal(ordGodExtend.getOrdQty());
				
				aplAmt = payExchgRtCrncyUntSumPrc.divide(ordQty, 1, BigDecimal.ROUND_UP);
			}

			ordGodAplPrm = new OrdGodAplPrm();
			newAplPrmTurn = getIdGenService().generateDBOrder(sqlSession1, "ord_god_apl_prm", "apl_prm_turn", conditions, DatabaseType.ORACLE);

            ordGodAplPrm.setOrdNo(              ordGodAplPrmExtend.getOrdNo()                           );  //주문번호
            ordGodAplPrm.setAplPrmTurn(         newAplPrmTurn                                           );  //적용 프로모션 순번
            ordGodAplPrm.setPrmNo(              ordGodAplPrmExtend.getPrmNo()                           );  //프로모션 번호
            ordGodAplPrm.setAplTpCd(            ordGodAplPrmExtend.getAplTpCd()                         );  //적용 유형 코드
            ordGodAplPrm.setAplUnitCd(          ordGodAplPrmExtend.getAplUnitCd()                       );  //적용 단위 코드
            ordGodAplPrm.setPrmTpCd(            ordGodAplPrmExtend.getPrmTpCd()                         );  //프로모션 유형 코드
            ordGodAplPrm.setPrmDtlTpCd(         ordGodAplPrmExtend.getPrmDtlTpCd()                      );  //프로모션 세부 유형 코드
            ordGodAplPrm.setAplAmt(             aplAmt                                                  );  //적용 금액
            ordGodAplPrm.setOrdGodTurn(         ordGodExtend.getNewOrdGodTurn()                     	);  //주문 상품 순번
            ordGodAplPrm.setAplQty(             ordGodExtend.getOrdQty()                          		);  //적용 수량
            ordGodAplPrm.setOrdGodGftTurn(      ordGodAplPrmExtend.getOrdGodGftTurn()                   );  //주문 상품 사은품 순번
            ordGodAplPrm.setOrdGodGftNm(        ordGodAplPrmExtend.getOrdGodGftNm()                     );  //주문 상품 사은품 명
            ordGodAplPrm.setMbrCpnNo(           ordGodAplPrmExtend.getMbrCpnNo()                        );
            ordGodAplPrm.setRegtrId(            regtrId                         						);
            ordGodAplPrm.setUdterId(            regtrId                         						);
	
            orderCommandRepository.insertOrdGodAplPrm(ordGodAplPrm);
		}
				
	}
	
	public  List<GodShopItmInvExtend> pkupOrderChange(LgsDlvspExtend lgsDlvsp, List<OrdGodExtend> ordGodExtends, SysShopBrnd sysShopBrnd,
													  HashMap<String, String> drctGodMap, SystemPK systemPk) throws Exception {
		String regtrId =  lgsDlvsp.getUdterId();
		
		int cst = 0;
		// 2015.9.25, Harris, 실배송비 계산로직 (Front 로직임, BO는 배송비 부과안함.) 
		if(lgsDlvsp.getRealityDlvCst() != null){
			cst = lgsDlvsp.getRealityDlvCst();
		}
		
		lgsDlvsp.setDlvPcupspSectCd("ORD_DLVSP");
		lgsDlvsp.setDlvSectCd("GNRL_DLV");
		
		// 2015.10.1, Harris, 지정택배로 정정 ( 픽업주문->일반주문 변경시, 지정택배로 지정되어야함.)
		/*lgsDlvsp.setDlvMnCd("HDRY");
		배송 수단 코드
		사용코드 : DLV_MN    배송 수단
		HDRY    택배
		APPN_HDRY    지정 택배
		SHOP_PKUP    매장픽업
		DHL    DHL
		OVSEA_POST    해외우편
		GNRL_POST    일반우편
		RGIST_POST    등기우편
		CVNSTOR_HDRY    편의점택배
		*/
//TODO 추후 처리 변경
//		lgsDlvsp.setDlvMnCd("APPN_HDRY");
		lgsDlvsp.setAddrSectCd("RD_ADDR"); // 2015.10.1, Harris, 픽업주문의 일반배송의 주소는 도로명 주소로 고정함.
		lgsDlvsp.setRegtrId(regtrId);
		lgsDlvsp.setUdterId(regtrId);
		
		log.info("물류배송지정보 {}",lgsDlvsp);
		
		orderBoCommandRepository.pkUpdateLgsDlvsp(lgsDlvsp);
		
		LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();

		BeanUtils.copyProperties(lgsDlvsp, lgsDlvspHist);

		lgsDlvspHist.setHistDt(new Date());

		// 물류 배송지 이력
		lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);
		
		
		
		
		Map<Long, List<OrdGodExtend>> lgsDlvMap = Maps.newHashMap();

		int dlvCstPlcSnCt = 0;
		for (OrdGodExtend ordGodEntity : ordGodExtends) {

			if (lgsDlvMap.containsKey(ordGodEntity.getDmstcDlvCstPlcSn())) {

				List<OrdGodExtend> list = lgsDlvMap.get(ordGodEntity.getDmstcDlvCstPlcSn());
				list.add(ordGodEntity);

			}
			else {

				List<OrdGodExtend> list = new ArrayList<OrdGodExtend>();
				list.add(ordGodEntity);
				lgsDlvMap.put(ordGodEntity.getDmstcDlvCstPlcSn(), list);
				dlvCstPlcSnCt++;
								
			}

		}

		Iterator<Long> it = lgsDlvMap.keySet().iterator();

		int itCount = 0;
	
		LgsDlv lgsDlv = null;
		LgsDlivyDrctGod lgsDlivyDrctGod = null;
		OrdGod ordGod = null;
		List<GodShopItmInvExtend> godShopItmInvExtendList =  new ArrayList<GodShopItmInvExtend>();
		
		while (it.hasNext()) {
			Long key = it.next();

			List<OrdGodExtend> list = lgsDlvMap.get(key);
			
			lgsDlv = new LgsDlv();
			Functions.variAbleSetN(lgsDlv);
			lgsDlv.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());
			lgsDlv.setOrdNo(lgsDlvsp.getOrdNo());
			lgsDlv.setRegtrId(regtrId);
			lgsDlv.setUdterId(regtrId);
			lgsDlv.setCrncyCd("KRW");
			if(itCount == 0 && cst > 0){
			    
                lgsDlv.setStdrCrncyAmt(new BigDecimal(String.valueOf(cst)));
                lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(String.valueOf(cst)));
                lgsDlv.setRealityDlvCst(new BigDecimal(String.valueOf(cst)));
			}else{
	            lgsDlv.setStdrCrncyAmt(new BigDecimal("0"));
	            lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal("0"));
	            lgsDlv.setRealityDlvCst(new BigDecimal("0"));			    
			    
			}
			

			lgsDlv.setWaybilNoErpTrnsmisCd(GoodsEnum.NO.toString());
			lgsDlv.setDlvCstBndMbdCd("CSTMR");
			lgsDlv.setDmstcDlvCstPlcSn(key);
			lgsDlv.setDmstcWaybilNo(null);
			lgsDlv.setDmstcWaybilNoRegDt(null);
			
			//2015.10.1, Harris, 픽업주문->일반배송전환시 지정택배(한진)으로 설정함.
			//2017.02.21 택배사 배송 정책 조회해서 업체코드 설정함. Written by Henry.
			String mallId = orderSelectService.selectOrdMallId(lgsDlvsp.getOrdNo());
			String dlvComCd = orderBoSelectRepository.selectBaseDlvComCd(mallId);
			lgsDlv.setDlvComCd(dlvComCd);

			// 2015-12-21 기본값 셋팅 [AshA] 
			lgsDlv.setDlvMnCd("HDRY");
			
			int dlvTurn = 0;
			if(dlvCstPlcSnCt == 1 || itCount == 0){
				
			    dlvTurn = 1;
				lgsDlv.setDlvTurn(1);

				// 물류배송
				lgsDlvRepository.updateLgsDlv(lgsDlv);	
				
			}else{
				
				Map<String, Object> conditions = Maps.newHashMap();	
				conditions.put("ord_no",lgsDlvsp.getOrdNo());
				conditions.put("dlv_pcupsp_turn", lgsDlvsp.getDlvPcupspTurn());				
				dlvTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlv", "dlv_turn", conditions, DatabaseType.ORACLE);
				
				lgsDlv.setDlvTurn(dlvTurn);
				// 물류배송
				lgsDlvRepository.insertLgsDlv(lgsDlv);
			}
			
			LgsDlvHist lgsDlvHist = new LgsDlvHist();

			BeanUtils.copyProperties(lgsDlv, lgsDlvHist);

			lgsDlvHist.setHistDt(new Date());
			lgsDlvHistRepository.insertLgsDlvHist(lgsDlvHist);
		
			for (OrdGodExtend ordGodExtend : list) {
            
                lgsDlivyDrctGod = new LgsDlivyDrctGod();
                
                lgsDlivyDrctGod.setDlivyDrctGodNo(ordGodExtend.getDlivyDrctGodNo());
                lgsDlivyDrctGod = lgsDlivyDrctGodRepository.selectLgsDlivyDrctGod(lgsDlivyDrctGod);
                
                lgsDlivyDrctGod.setDlvTurn(dlvTurn);
                String yn = drctGodMap.get(ordGodExtend.getDlivyDrctGodNo());
//                if( "N".equals(yn) || "N".equals(sysShopBrnd.getDlvShopYn())){
                log.debug("ordGodExtend :: "+ordGodExtend);
                log.info("The value of 'pkupOrderChange' is [{}::{}::{}::{}::{}].",
						ordGodExtend.getDlivyDrctGodNo(), lgsDlivyDrctGod.getDlvShopId(), lgsDlivyDrctGod.getDlivyShopId(), lgsDlivyDrctGod.getDlvStatCd(), yn);

				if ( ! StringService.equalsIgnoreCase("DLIVY_DRCT_CNCL", lgsDlivyDrctGod.getDlvStatCd() ) ) {
					/*
					 * 1. 물류센터(CDC)에서 픽업매장으로 재고가 내려가는 경우, '출고', '미출고' 상태
					 * 	- 픽업매장에 재고가 없는 경우
					 * 2. 해당 픽업매장이 '배송매장' 이 아닌 경우
					 */
					if ( StringService.equalsIgnoreCase("N", yn) ) {

						lgsDlivyDrctGod.setDlvStatCd("DLV_WAIT");
						lgsDlivyDrctGod.setDlvShopId("B031");
						lgsDlivyDrctGod.setDlivyDrctYn("N");
						lgsDlivyDrctGod.setDlivyDrctDt(new Date());

						GodShopItmInvExtend ge = new GodShopItmInvExtend();
						long cnlQty = 0;

						if (lgsDlivyDrctGod.getDlivyDrctCnclQty() != null) {
							cnlQty = lgsDlivyDrctGod.getDlivyDrctCnclQty();
						}

						long dlvQty = lgsDlivyDrctGod.getDlivyDrctQty() - cnlQty;
						ge.setShopId("B031");
						ge.setItmNo(ordGodExtend.getItmNo());
						ge.setSalePrearngeQtyInt((int) dlvQty);
						ge.setUdterId(regtrId);

						// 2015-11-11 입점업체 구분 추가 [AshA]
						ge.setPartmalSectCd(ordGodExtend.getPartmalSectCd());
						godShopItmInvExtendList.add(ge);

						/*
						 * 위에서 yn 값이 'N' 인 경우로 제한되므로 아래 조건은 성립안됨
						 */
						/*
						if(!lgsDlivyDrctGod.getDlvStatCd().equals("SHTG_RCEPT") && !"N".equals(yn) ){

							GodShopItmInvExtend v  = new GodShopItmInvExtend();
							v.setShopId(sysShopBrnd.getShopId());
							v.setItmNo(ordGodExtend.getItmNo());
							v.setSalePrearngeQtyInt(-(int)dlvQty);
							v.setUdterId(regtrId);

							// 2015-11-11 입점업체 구분 추가 [AshA]
							v.setPartmalSectCd(ordGodExtend.getPartmalSectCd());

							godShopItmInvExtendList.add(v);
						}*/
					}
					else {
						/*
						 * 해당 픽업매장 출고건
						 * 	- '베송매장'을 겸하고 있으므로 자체 출고가능
						 */
						if ( StringService.equalsIgnoreCase("B031", lgsDlivyDrctGod.getDlvShopId() ) ) {
							lgsDlivyDrctGod.setDlvStatCd("DLV_WAIT");
						}
						else {
							lgsDlivyDrctGod.setDlvStatCd("DLIVY_DRCT");

							/*
							 * 1. 수정일자	: 2016-08-09
							 * 2. 수정자	    : 김재성 (jskim27)
							 * 3. 요청 SR NO	: #24789
							 * 4. 수정 내용	: [배정] 픽업주문에서 일반주문 변경시 [물류 자동 배정 이력] 생성
							 * 		- 통계데이터 활용을 위해서 생성 필요
							 * 		- 1. 물류출고지시상품(LGS_DLIVY_DRCT_GOD) 테이블의 배정구분코드('ASGN_COUNT'), 배정횟수('ASGN_COUNT') 수정
							 */
							/*
							 * 1. 물류출고지시상품(LGS_DLIVY_DRCT_GOD) 테이블의 배정구분코드('ASGN_COUNT'), 배정횟수('ASGN_COUNT') 수정
							 */
							int asgnCount = 0;
							lgsDlivyDrctGod.setAsgnSectCd("ENFRC_ASGN");
							// 이전에 '배정'된 횟수정보를 조회해서 + 1
							try {
								asgnCount = lgsDlivyDrctGod.getAsgnCount();
							}
							catch(Exception e){}

							lgsDlivyDrctGod.setAsgnCount(asgnCount + 1);
						}
						lgsDlivyDrctGod.setDlivyDrctDt(new Date());
					}
				}
				
                lgsDlivyDrctGod.setDlivyDrctTgtYn("N");
                lgsDlivyDrctGod.setDlivyDrctTpCd("ORD");
				/*
				 * DLIVY_SHOP_ID(출고매장ID) 초기화
				 * - 픽업주문인경우 재고가 물류센터에서 픽업매장으로 내려갈때 사용
				 * - 따라서 '일반주문' 으로 변경 시 삭제
				 */
                lgsDlivyDrctGod.setDlivyShopId(null);
                lgsDlivyDrctGod.setUdterId(regtrId);
                lgsDlivyDrctGod.setDlivyDrctGrpDgre(null);
                lgsDlivyDrctGod.setDlivyDrctUserGrpDgre(null);
                lgsDlivyDrctGodRepository.updateLgsDlivyDrctGod(lgsDlivyDrctGod);
                
                LgsDlivyDrctGodHist lgsDlivyDrctGodHist = new LgsDlivyDrctGodHist();
                BeanUtils.copyProperties(lgsDlivyDrctGod, lgsDlivyDrctGodHist);
                Map<String, Object> conditions = Maps.newHashMap();
                conditions.put("dlivy_drct_god_no", ordGodExtend.getDlivyDrctGodNo());

                int histTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlivy_drct_god_hist", "hist_turn", conditions,
                        DatabaseType.ORACLE);
                lgsDlivyDrctGodHist.setHistTurn(histTurn);
                lgsDlivyDrctGodHist.setRegtrId(regtrId);
                
                lgsDlivyDrctGodHistRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
				ordGod = ordGodRepository.selectOrdGod(ordGodExtend);
				ordGod.setDmstcDlvCstPlcSn(ordGodExtend.getDmstcDlvCstPlcSn());
				ordGod.setUdterId(regtrId);
				ordGodRepository.updateOrdGod(ordGod);
            }
			
			
			itCount++;
		}
		
		
		return godShopItmInvExtendList;
	}
	
	/**
	 * 세트상품 교환접수시 세트대표상품 등록
	 */
	public void insertSetExchgGodForClm(OrdGodExtend ordGodExtend) throws Exception {
		orderBoCommandRepository.insertSetExchgGodForClm(ordGodExtend);
	}
	
	/**
	 * 교환접수 시 구성상품연결 등록
	 */
	public void insertSetExchgCpstGodCnnc(OrdCpstGodCnnc ordCpstGodCnnc) throws Exception {
		orderBoCommandRepository.insertSetExchgCpstGodCnnc(ordCpstGodCnnc);
	}
	
	/** 결품 수정 */
	public int updateShopShortage(OrdGodInv ordGodInv)  throws Exception {
		int retValue = 0;
		boolean flag = true;

		Ord ord = new Ord();
		ord.setOrdNo(ordGodInv.getOrdNo());
		String ordStatCd = orderBoSelectRepository.getOrdStatCd(ord);

		if (("DLV_PROGRS".equals(ordStatCd) || "DLV_COMPT".equals(ordStatCd)) ) {
			flag = false;
		}
		

		if (flag) {
			orderCommandRepository.updateShortageOrderGod(ordGodInv);
		}

		return retValue;
	}
	

}
