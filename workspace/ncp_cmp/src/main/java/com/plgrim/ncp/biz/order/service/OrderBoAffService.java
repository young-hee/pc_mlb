package com.plgrim.ncp.biz.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.entities.datasource1.god.GodCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodItm;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrd;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrdErr;
import com.plgrim.ncp.base.entities.datasource1.inf.InfAffOrdPrcs;
import com.plgrim.ncp.base.entities.datasource1.inf.InfTmprAffOrd;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.Pay;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.repository.inf.InfAffOrdErrRepository;
import com.plgrim.ncp.base.repository.inf.InfAffOrdPrcsRepository;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.exception.OrderCompleteFailException;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.biz.order.repository.OrderBoSelectRepository;
import com.plgrim.ncp.biz.order.result.OrderBoResult;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

@Slf4j
@Service
public class OrderBoAffService extends OrderBoAbstractService {

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	@Autowired
	OrderBoSelectRepository orderBoSelectRepository;

	@Autowired
	OrderBoCommandRepository orderBoCommandRepository;

	@Autowired
	InfAffOrdErrRepository infAffOrdErrRepository;
	
	@Autowired
	InfAffOrdPrcsRepository infAffOrdPrcsRepository;

	public HashMap<String, Object> insertInfTmprAffOrdTemp(List<InfTmprAffOrd> infTmprAffOrds, String loginId) throws Exception {

		Map<String, String> pkGodMap = Maps.newHashMap();

		int count = infTmprAffOrds.size();
		int std = 500;
		int affOrdTurn = 1;
		int turn = 1;
		int temp = 500;
		boolean flag = false;

		List<InfTmprAffOrd> adds = new ArrayList<InfTmprAffOrd>();

		List<InfTmprAffOrd> set = new ArrayList<InfTmprAffOrd>();
		HashMap<String, Object> map = Maps.newHashMap();

		long affOrdSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_INF_AFF_ORD", DatabaseType.ORACLE);

		for (InfTmprAffOrd infTmprAffOrd : infTmprAffOrds) {
			/*
	         * 1. 수정일자 : 2015-12-24
	         * 2. 수정자 : 김재성 (jskim27)
	         * 3. 요청 SR NO : #15257
	         * 4. 수정 내용 : 상품옵션 정보에 '_' 존재하는 경우 우선 GOD_ITM 정보를 조회해서 유효한 옵션정보 여부를 체크해서 처리
	         */
			OrderBoDTO orderDTO = new OrderBoDTO();
			orderDTO.setGodNo( infTmprAffOrd.getErpGodNo() );
			orderDTO.setItmNo( infTmprAffOrd.getItmNm() );
			int godCount = this.orderBoSelectRepository.getGodCountByItmNm( orderDTO );

			String[] itms = new String[0];

			/*
			 * 주문(발주)정보에 포함된 옵션정보가 패키지용 상품 처리용으로 사용되는 경우,
			 * 아래 itms.length >= 2 조건을 이용해서 처리
			 */
			if ( godCount == 0 ) {
				itms = infTmprAffOrd.getItmNm().split("_");
			}
			if ( log.isDebugEnabled() ) {
				log.debug(">insertInfTmprAffOrdTemp ErpGodNo/ItmNm {}/{}/{}", infTmprAffOrd.getErpGodNo(), infTmprAffOrd.getItmNm(), godCount );
			}

			String cstmrAddr = replace(infTmprAffOrd.getCstmrAddr());
			String dlvMemo = replace(infTmprAffOrd.getDlvMemo());

			infTmprAffOrd.setCstmrAddr(cstmrAddr);
			infTmprAffOrd.setDlvMemo(dlvMemo);
			infTmprAffOrd.setAffOrdSn(affOrdSn);
			infTmprAffOrd.setAffOrdTurn(affOrdTurn);

			if (itms.length >= 2) {

				if (!pkGodMap.containsKey(itms[0]+"&="+infTmprAffOrd.getAffComNm()+"=&"+infTmprAffOrd.getAffGodOrdNo())) {
					affOrdTurn++;
					
					pkGodMap.put(itms[0]+"&="+infTmprAffOrd.getAffComNm()+"=&"+infTmprAffOrd.getAffGodOrdNo(), itms[0]);
					
					InfTmprAffOrd pk = new InfTmprAffOrd();
					BeanUtils.copyProperties(infTmprAffOrd, pk);
					pk.setPckageshapeGodNo(itms[0]);
					pk.setItmNm("F");
					pk.setErpGodNo(itms[0]);
					pk.setAffOrdTurn(affOrdTurn);
					pk.setPckageshapeGodItmNm(pk.getItmNm());
					adds.add(pk);
				}
				infTmprAffOrd.setPckageshapeGodNo(itms[0]);
				infTmprAffOrd.setPckageshapeGodItmNm(infTmprAffOrd.getItmNm());

				infTmprAffOrd.setItmNm(itms[1]);
				set.add(infTmprAffOrd);

			}
			else {
				infTmprAffOrd.setPckageshapeGodItmNm(infTmprAffOrd.getItmNm());
				set.add(infTmprAffOrd);

			}
			if (turn == temp) {


				orderBoCommandRepository.insertInfTmprAffOrdTemp(set);

				if (count - temp < std) {
					flag = true;
				}
				temp = std + temp;
				set.clear();
			}
			else if (flag) {

				if (turn == count) {

					orderBoCommandRepository.insertInfTmprAffOrdTemp(set);
				}

			}
			else if (count < std && turn == count) {
				orderBoCommandRepository.insertInfTmprAffOrdTemp(set);
			}

			affOrdTurn++;
			turn++;
		}

		if (adds.size() > 0) {

			orderBoCommandRepository.insertInfTmprAffOrdTemp(adds);
		}

		InfAffOrd infAffOrd = new InfAffOrd();
		infAffOrd.setRegtrId(loginId);
		orderBoCommandRepository.insertInfAffOrdExcel(infAffOrd);

		OrderBoDTO orderBoDTO = new OrderBoDTO();
		orderBoDTO.setAffOrdSn(affOrdSn);
		List<InfAffOrd> list = orderBoCommandRepository.selectAffCoOrdList(orderBoDTO);
		for (InfAffOrd s : list) {
            
		    List<InfAffOrd> st = orderBoCommandRepository.selectAffAditFeeRt(s);
		    s.setAffComFeeRt(null);
		    s.setAffVrscComFeeRt(null);
		    for (InfAffOrd v : st) {
		        
		        if("AFF_SELR".equals(v.getAffOrdStatCd())){
		            s.setAffComFeeRt(v.getAffComFeeRt());
		            s.setAffFeeEventSn(v.getAffFeeEventSn());
		        }else if("AFF_AGNC".equals(v.getAffOrdStatCd())){
		            s.setAffVrscComFeeRt(v.getAffComFeeRt());
		            s.setVrscComFeeEventSn(v.getAffFeeEventSn());
		            
		        }
		        
		    }
		   if(st != null && st.size() >0){
		       
		       orderBoCommandRepository.updateAffOrdRt(s);
		   }
        }
		map.put("affOrdSn", affOrdSn);
		//map.put("list", orderBoCommandRepository.selectErrExcelAffList());
		return map;

	}


	private String replace(String str) {

		String s = str;
		s = StringService.replace(s, "<", "");
		s = StringService.replace(s, ">", "");
		s = StringService.replace(s, "\"", "");
		s = StringService.replace(s, "'", "");
		s = StringService.replace(s, "\n", "");

		return s;

	};

	public void insertOrder(OrderBoDTO orderDTO) throws Exception {

		creatOrder(orderDTO);
	}
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr) throws Exception {
		
		infAffOrdErrRepository.insertInfAffOrdErr(infAffOrdErr);
	}
	
	public void insertInfAffOrdErr(InfAffOrdErr infAffOrdErr,InfAffOrd infAffOrd) throws Exception {

		if(infAffOrdErr.getAffOrdErrCont().equals("AFF_INV_ERR")){
			
		
		OrdGodExtend ordGodEntity = new OrdGodExtend();
		
		ordGodEntity.setGodNo(infAffOrd.getGodNo());
		ordGodEntity.setItmNo(infAffOrd.getItmNo());
		
		GodExtend god = orderBoSelectRepository.selectBoOrdGodItmHist(ordGodEntity);
		
		String affOrdErrCont ="";
		
		boolean falg = false;
		if(god == null){
	
			affOrdErrCont ="존재 하지 않는 상품"; 
			falg = true;
		}else{
			
			String invManageYn = god.getInvManageYn();		
			
			// 제휴대행사 요청에 의해 판매종료 인 상품도 주문등록 
/*			if(!(god.getGodAprvSectCd().equals(GoodsAprvState.APRV_COMPT.toString()) 
					&& god.getItmStatCd().equals(GoodsSaleState.SALE_PROGRS.toString())) ){
				
				affOrdErrCont ="판매가 종료된 상품입니다."; 
				falg = true;
			}
			 */
				
			if (invManageYn() || invManageYn != null && invManageYn.equals("Y")) {
				
				if (god.getLmttInvYn() != null && god.getLmttInvYn().equals("Y")) {

					if (god.getAffComLmttInvQty() < infAffOrd.getQty()) {
						affOrdErrCont ="판매할수 있는 한정 재고 수량이 부족합니다."; 
						falg = true;
					}
				}
				else {

					long safeInvQty = 0;

					if (god.getSafeInvUseYn().equals("Y")) {

						safeInvQty = god.getSafeInvQty();
					}
					// 실재고 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)

					long qty = god.getTotUsefulInvQty() - god.getSalePrearngeQty() - safeInvQty;

					if (qty < infAffOrd.getQty()) {

						affOrdErrCont ="판매할수 있는 재고 수량이 부족합니다."; 
						falg = true;
					}

				}
				
			}
			
		}
		if(falg){
		
			infAffOrd.setOrdNo(null);
			infAffOrd.setAffOrdStatCd("ORD_CNCL_WAIT");
			infAffOrd.setAffOrdStatCont(affOrdErrCont);
			infAffOrd.setSldoutYn("Y");
			updateAffOrd(infAffOrd);		
			infAffOrdErr.setAffOrdErrCont(affOrdErrCont);	
			infAffOrdErrRepository.insertInfAffOrdErr(infAffOrdErr);			
		}else{
			
			infAffOrd.setOrdNo(null);
			infAffOrd.setAffOrdStatCd("ORD_WAIT");
			updateAffOrd(infAffOrd);
			
		}

		
		}else{
		    	          
            deleteAffOrd(infAffOrd);          
            infAffOrdErrRepository.insertInfAffOrdErr(infAffOrdErr);    
		}
		
	
	}

	
	public void updateAffOrd(InfAffOrd infAffOrd) throws Exception {
		orderBoCommandRepository.updateAffOrd(infAffOrd);

	}

	public void deleteAffOrd(InfAffOrd infAffOrd) throws Exception {
		orderBoCommandRepository.deleteAffOrd(infAffOrd);

	}

	public OrderBoDTO getOrderBoDTO(OrdExtend extend) throws Exception {

		OrderBoDTO dto = new OrderBoDTO();


		dto.setRegtrId(extend.getRegtrId());
		HashMap<String, LgsDlvspExtend> map = Maps.newHashMap();
		HashMap<String, List<OrdGodExtend>> godMap = Maps.newHashMap();

		HashMap<String, OrdGodExtend> giftMap = Maps.newHashMap();

		String godNo = "";

		
		InfAffOrdPrcs infAffOrdPrcs= new InfAffOrdPrcs();
		Functions.variAbleSetN(infAffOrdPrcs);
		
		try {
	        for (InfAffOrd infAffOrd : extend.getInfAffOrdList()) {

	            BeanUtils.copyProperties(infAffOrd, infAffOrdPrcs);
	            
	            infAffOrdPrcs.setAffOrdDt(new Date());
	            infAffOrdPrcsRepository.insertInfAffOrdPrcs(infAffOrdPrcs);
	            
	            List<OrdGodExtend> ordGodList = new ArrayList<OrdGodExtend>();
	            List<OrdGodExtend> ordGodCnncList = new ArrayList<OrdGodExtend>();
	            OrdGodExtend ordGodEntity = new OrdGodExtend();
	            OrdGodExtend gift = new OrdGodExtend();
	            LgsDlvspExtend dlvspEntity = new LgsDlvspExtend();

	            String key = lgsDlvsp(dlvspEntity, infAffOrd);


	            ordGod(ordGodEntity, infAffOrd, gift);

	            if (giftMap.containsKey(gift.getGodNo())) {
	                OrdGodExtend ex = giftMap.get(gift.getGodNo());
	                ex.setOrdQty(ex.getOrdQty() + gift.getOrdQty());
	                ex.setPayExchgRtCrncyAmt(ex.getPayExchgRtCrncyAmt().add(gift.getPayExchgRtCrncyAmt()));
	            }
	            else {
	                godNo += "," + gift.getGodNo();

	                giftMap.put(gift.getGodNo(), gift);
	            }

	            if (!map.containsKey(key)) {
	                map.put(key, dlvspEntity);
	            }

	            LgsDlvspExtend dlvsp = map.get(key);

	            //패키지형 상품 마스터 이거나 일반 상품 
	            if (!StringService.isNotEmpty(infAffOrd.getPckageshapeGodNo()) || infAffOrd.getPckageshapeGodNo().equals(infAffOrd.getGodNo())) {

	                if (dlvsp.getOrdGodList() == null) {
	                    
	                    if (StringService.isNotEmpty(infAffOrd.getPckageshapeGodNo())
	                            && infAffOrd.getPckageshapeGodNo().equals(infAffOrd.getGodNo())) {

	                        godMap.put(infAffOrd.getPckageshapeGodNo(), new ArrayList<OrdGodExtend>());

	                    }

	                }
	                else {

	                    if (StringService.isNotEmpty(infAffOrd.getPckageshapeGodNo())
	                            && infAffOrd.getPckageshapeGodNo().equals(infAffOrd.getGodNo())) {
	                        godMap.put(infAffOrd.getPckageshapeGodNo(), new ArrayList<OrdGodExtend>());
	                    }
	                    
	                    ordGodList = dlvsp.getOrdGodList();
	                }
	                ordGodList.add(ordGodEntity);
	                dlvsp.setOrdGodList(ordGodList);

	            }
	            else {
	                ordGodCnncList = godMap.get(infAffOrd.getPckageshapeGodNo());

	                ordGodCnncList.add(ordGodEntity);
	                godMap.put(infAffOrd.getPckageshapeGodNo(), ordGodCnncList);

	            }


	        }            
        }
        catch (Exception e) {
            
            if(e.getMessage() ==null){
                throw new OrderCompleteFailException("java.lang.NullPointerException ::페키지 상품일 경우가큼");
            }else{
                throw new OrderCompleteFailException(e.getMessage());     
            }
           
       
        }



		OrderBoDTO sdto = new OrderBoDTO();
		sdto.setAffComId(extend.getAffComId());
		sdto.setAffComAplCd("AFF_COM_APL");
		List<OrderBoResult> prms = orderBoSelectRepository.selectAffTgtPrmList(sdto);

		String[] godNos = godNo.substring(1).split(",");
		sdto.setGodNos(godNos);

		String ordGftPchStdrCd = "";
		String prmDtlTpCd = "";
		Long ordGftAplCndQty = null;
		BigDecimal ordGftAplCndAmt = null;
		int qty = 0;
		int amt = 0;
		boolean flag = false;
		List<OrdGodAplPrmExtend> ordApls = new ArrayList<OrdGodAplPrmExtend>();
		OrdGodAplPrmExtend ordGodAplPrm = null;

		HashMap<String, List<OrdGodAplPrmExtend>> aplMap = Maps.newHashMap();
		HashMap<String, List<OrdGodAplPrmExtend>> tempOrdGift = Maps.newHashMap();
		HashMap<String, List<OrdGodAplPrmExtend>> ordGift = Maps.newHashMap();

		List<OrdGodAplPrmExtend> apls = new ArrayList<OrdGodAplPrmExtend>();
		String saleShopCd = extend.getSaleShopCd();
		for (OrderBoResult orderBoResult : prms) {

			sdto.setPrmNo(orderBoResult.getPrmNo());

			List<OrderBoResult> gifts = orderBoSelectRepository.selectAffGftList(sdto);

			ordGftPchStdrCd = "";
			prmDtlTpCd = "";
			ordGftAplCndQty = null;
			ordGftAplCndAmt = null;
			qty = 0;
			amt = 0;

			//적용 상품 정보
			for (OrderBoResult gift : gifts) {

				List<OrderBoResult> giftGod = orderBoSelectRepository.selectAffGftGodList(sdto);
				apls = new ArrayList<OrdGodAplPrmExtend>();

				ordGftPchStdrCd = gift.getOrdGftPchStdrCd();
				ordGftAplCndQty = gift.getOrdGftAplCndQty();
				ordGftAplCndAmt = gift.getOrdGftAplCndAmt();
				prmDtlTpCd = gift.getPrmDtlTpCd();
				if (prmDtlTpCd.equals("GOD_GFT")) {
					for (OrderBoResult god : giftGod) {

						ordGodAplPrm = new OrdGodAplPrmExtend();
						BeanUtils.copyProperties(gift, ordGodAplPrm);
						ordGodAplPrm.setAplTpCd("APL");
						ordGodAplPrm.setAplUnitCd("GOD");
						ordGodAplPrm.setGftGodNo(god.getGftGodNo());
						ordGodAplPrm.setSaleShopCd(saleShopCd);
						if (aplMap.containsKey(gift.getGodNo())) {

							apls = aplMap.get(gift.getGodNo());

							OrdGodExtend ex = giftMap.get(gift.getGodNo());
							ordGodAplPrm.setAplQty(ex.getOrdQty());
							apls.add(ordGodAplPrm);

						}
						else {
							OrdGodExtend ex = giftMap.get(gift.getGodNo());
							ordGodAplPrm.setAplQty(ex.getOrdQty());
							apls.add(ordGodAplPrm);
							aplMap.put(gift.getGodNo(), apls);
						}
					}
				}
				else {

					//사은품 리스트
					OrdGodExtend ex = giftMap.get(gift.getGodNo());
					qty += ex.getOrdQty();
					amt += ex.getPayExchgRtCrncyAmt().intValue();
					for (OrderBoResult god : giftGod) {

						ordGodAplPrm = new OrdGodAplPrmExtend();
						BeanUtils.copyProperties(gift, ordGodAplPrm);
						ordGodAplPrm.setAplTpCd("APL");
						ordGodAplPrm.setAplUnitCd("ORD");
						ordGodAplPrm.setGftGodNo(god.getGftGodNo());
						ordGodAplPrm.setSaleShopCd(saleShopCd);
						ordGodAplPrm.setAplQty(ex.getOrdQty());
						ordGodAplPrm.setAplAmt(ex.getPayExchgRtCrncyAmt());
						if (tempOrdGift.containsKey(gift.getGodNo())) {
							apls = tempOrdGift.get(gift.getGodNo());
							apls.add(ordGodAplPrm);

						}
						else {
							apls.add(ordGodAplPrm);
							tempOrdGift.put(gift.getGodNo(), apls);
						}
					}


				}

			}

			if (prmDtlTpCd.equals("ORD_GFT")) {

				// 금액 기준
				if (ordGftPchStdrCd.equals("AMT_STDR")) {

					if (ordGftAplCndAmt.intValue() <= amt) {
						flag = true;
					}

				}
				else {

					if (ordGftAplCndQty <= qty) {
						flag = true;
					}

				}

				if (flag) {
					List<OrderBoResult> giftGod = orderBoSelectRepository.selectAffGftGodList(sdto);

					for (OrderBoResult god : giftGod) {
						OrdGodAplPrmExtend ordGodApl = new OrdGodAplPrmExtend();
						BeanUtils.copyProperties(god, ordGodApl);
						ordGodApl.setSaleShopCd(saleShopCd);
						ordGodApl.setPrmNo(orderBoResult.getPrmNo());
						ordGodApl.setAplQty(1l);
						ordApls.add(ordGodApl);

					}

					Iterator<String> it = tempOrdGift.keySet().iterator();

					while (it.hasNext()) {
						String key = it.next();

						if (ordGift.containsKey(key)) {

							apls = ordGift.get(key);

							apls.addAll(tempOrdGift.get(key));
						}
						else {
							ordGift.put(key, tempOrdGift.get(key));

						}

					}
				}

				flag = false;
			}


		}


		dto.setOrd(extend);
		//배송지
		List<LgsDlvspExtend> lgsDlvspList = new ArrayList<LgsDlvspExtend>(map.values());
		List<OrdGodExtend> ordGodCnncList = new ArrayList<OrdGodExtend>();
		List<OrdGodAplPrmExtend> gifts = new ArrayList<OrdGodAplPrmExtend>();
		List<OrdGodAplPrmExtend> ordGifts = new ArrayList<OrdGodAplPrmExtend>();
		//주문 사은품은 첫 배송지에.
		lgsDlvspList.get(0).setGifts(ordApls);

		for (LgsDlvspExtend lgsDlvspExtend : lgsDlvspList) {

			for (OrdGodExtend ordGodExtend : lgsDlvspExtend.getOrdGodList()) {

				ordGodCnncList = godMap.get(ordGodExtend.getGodNo());

				gifts = aplMap.get(ordGodExtend.getGodNo());
				ordGodExtend.setGifts(gifts);

				ordGifts = ordGift.get(ordGodExtend.getGodNo());
				ordGodExtend.setOrdGifts(ordGifts);
				if (ordGodCnncList != null) {
					for (OrdGodExtend ex : ordGodCnncList) {

						gifts = aplMap.get(ex.getGodNo());
						ex.setGifts(gifts);
						ordGifts = ordGift.get(ex.getGodNo());
						ex.setOrdGifts(ordGifts);
					}
				}

				ordGodExtend.setOrdGodList(ordGodCnncList);
			}
		}

		dto.setLgsDlvspList(lgsDlvspList);

		Pay pay = new Pay();
		pay(pay, extend);
		List<Pay> payList = new ArrayList<Pay>();
		payList.add(pay);
		dto.setPayList(payList);

		return dto;


	}



	@Override
	void setOrder(Ord ord) {
		ord.setMbrTpCd("NMBR");
		//ord.setOrdStatCd(OrderClaimEnum.ORD_STAT_PAY_COMPT.toString());
//		ord.setOrdTpCd(OrderClaimEnum.ORD_TP_CD_AFF_ORD.toString());
		
		// 2016.01.20 by Cannon
		// 가상배송완료인 경우 주문상태는 배송완료
		if("Y".equals(ord.getVirtlDlvComptYn())){
			ord.setOrdStatCd(OrderClaimEnum.ORD_STAT_DLV_COMPT.toString());
		}else{
//			ord.setOrdStatCd(OrderClaimEnum.ORD_STAT_PAY_COMPT.toString());
		}		
	}
	private String lgsDlvsp(LgsDlvspExtend dlvspEntity, InfAffOrd infAffOrd) {

		String baseAddr = infAffOrd.getCstmrBaseAddr().trim();
		String detailAddr = infAffOrd.getCstmrDetailAddr().trim();

		dlvspEntity.setAddrseNm(infAffOrd.getCstmrNm());
		dlvspEntity.setMbrCheck(false);
		if (infAffOrd.getCstmrPostNo() != null) {
			dlvspEntity.setAddrsePostNo(infAffOrd.getCstmrPostNo().trim());
		}

		dlvspEntity.setAddrseBaseAddr(baseAddr);
		dlvspEntity.setAddrseDetailAddr(detailAddr);
		dlvspEntity.setAddrseMobilNationNo(infAffOrd.getMobilNationNo());
		dlvspEntity.setAddrseMobilAreaNo(infAffOrd.getMobilAreaNo());
		dlvspEntity.setAddrseMobilTlofNo(infAffOrd.getMobilTlofNo());
		dlvspEntity.setAddrseMobilTlofWthnNo(infAffOrd.getMobilTlofWthnNo());

		dlvspEntity.setAddrseTelNationNo(infAffOrd.getTelNationNo());
		dlvspEntity.setAddrseTelAreaNo(infAffOrd.getTelAreaNo());
		dlvspEntity.setAddrseTelTlofNo(infAffOrd.getTelTlofNo());
		dlvspEntity.setAddrseTelTlofWthnNo(infAffOrd.getTelTlofWthnNo());
		dlvspEntity.setDlvMemo(infAffOrd.getDlvMemo());
		dlvspEntity.setAddrseNationCd("KR");
		dlvspEntity.setDlvSectCd("GNRL_DLV");
//TODO 추후 처리 변경
		//dlvspEntity.setDlvMnCd("HDRY");
		dlvspEntity.setAddrSectCd("LNM_ADDR");
		return baseAddr + detailAddr;

	}

	private void ordGod(OrdGodExtend ordGodEntity, InfAffOrd infAffOrd, OrdGodExtend gift) {

		gift.setOrdQty(infAffOrd.getQty());
		gift.setGodNo(infAffOrd.getGodNo());
		gift.setPayExchgRtCrncyAmt(infAffOrd.getAffComSalePrc());

		ordGodEntity.setOrdQty(infAffOrd.getQty());
		ordGodEntity.setGodNo(infAffOrd.getGodNo());
		ordGodEntity.setItmNo(infAffOrd.getItmNo());
		ordGodEntity.setSaleShopCd(infAffOrd.getSaleShopNo());
		ordGodEntity.setCrncyCd("KRW");
		ordGodEntity.setStdrCrncyAmt(infAffOrd.getAffComSalePrc());
		ordGodEntity.setPayExchgRtCrncyAmt(infAffOrd.getAffComSalePrc());
		ordGodEntity.setSaleAmt(infAffOrd.getAffComSalePrc());
		ordGodEntity.setSaleUntPrc(infAffOrd.getAffComSalePrc());
		ordGodEntity.setAffOrdTurn(infAffOrd.getAffOrdTurn());
		ordGodEntity.setAffItmOrdNo(infAffOrd.getAffItmOrdNo()); 
		ordGodEntity.setAffOrdSn(infAffOrd.getAffOrdSn()); 
		ordGodEntity.setAffComOrdGodNo(infAffOrd.getAffItmOrdNo()); 
		ordGodEntity.setAffComNm(infAffOrd.getAffComNm());
		long dcAmt = infAffOrd.getRtlPrc().longValue()*infAffOrd.getQty() -infAffOrd.getAffComSalePrc().longValue() ;
		String webDcAmt = String.valueOf(dcAmt);
		ordGodEntity.setWebDcAmt(new BigDecimal(webDcAmt));
		
		
	}

	private void pay(Pay pay, OrdExtend extend) {

		pay.setPayMnCd("NON_BNKBOK_PAY");
		pay.setDealTpCd(OrderClaimEnum.ORD_STAT_PAY_COMPT.toString());
		pay.setStdrCrncyPayAmt(extend.getStdrCrncySumAmt());
		pay.setPayCrncyPayAmt(extend.getPayExchgRtCrncySumAmt());
		pay.setPayCrncyCd("KRW");
		pay.setPayTpCd("ORD");
	}
	public List<OrdExtend> selectAffOrd(OrderBoDTO orderBoDTO) throws Exception {

		return orderBoCommandRepository.selectAffOrd(orderBoDTO);

	}

	@Override
	boolean mbrChecK() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean pointYn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean invManageYn() {
		// 재고 체크 사용여부
		return false;
	}

	@Override
	boolean godChecK() {

		return true;
	}

	@Override
	void invProcessor(OrdGodExtend ordGodEntity) throws Exception {

		//재고관리 여부
		String invManageYn = ordGodEntity.getInvManageYn();
		String resveSaleGodYn = ordGodEntity.getResveSaleGodYn();
		long salePrearngeQty = ordGodEntity.getOrdQty();

		// 사은품 제외
		if (!(ordGodEntity.getGodTpCd().equals(GoodsType.PCHS_GFT.toString()) || ordGodEntity.getGodTpCd().equals(
				GoodsType.CNVRS_GFT.toString()))) {

			// 제휴대행사 요청에 의해 판매종료 인 상품도 주문등록 
/*			if(!(ordGodEntity.getGodAprvSectCd().equals(GoodsAprvState.APRV_COMPT.toString()) 
					&& ordGodEntity.getItmStatCd().equals(GoodsSaleState.SALE_PROGRS.toString())) ){
				
				throw new OrderCompleteFailException("AFF_INV_ERR");
			}*/

			if( invManageYn()){
				//재고 관리를 할경우 
				if (invManageYn != null && invManageYn.equals("Y")) {

					if (resveSaleGodYn != null && resveSaleGodYn.equals("Y")) {

						if (ordGodEntity.getResveInvQty() < salePrearngeQty) {
							throw new OrderCompleteFailException("AFF_INV_ERR");

						}

					}//한정 재고가 Y 인경우
					else if (ordGodEntity.getLmttInvYn() != null && ordGodEntity.getLmttInvYn().equals("Y")) {

						if (ordGodEntity.getAffComLmttInvQty() < salePrearngeQty) {
							throw new OrderCompleteFailException("AFF_INV_ERR");
						}
					}
					else {

						long safeInvQty = 0;

						if (ordGodEntity.getSafeInvUseYn().equals("Y")) {

							safeInvQty = ordGodEntity.getSafeInvQty();
						}
						// 실재고 = 총 가용 재고 수량 - 판매 예정 수 - IF(안전 재고 사용여부=Y,안전 재고 수,0)

						long qty = ordGodEntity.getTotUsefulInvQty() - ordGodEntity.getSalePrearngeQty() - safeInvQty;

						if (qty < salePrearngeQty) {

							throw new OrderCompleteFailException("AFF_INV_ERR");
						}

					}
				}		
				
				GodItm godItm = new GodItm();
				godItm.setGodNo(ordGodEntity.getGodNo());
				godItm.setItmNo(ordGodEntity.getItmNo());

				godItm.setSalePrearngeQty(salePrearngeQty);
				godItm.setUdterId(ordGodEntity.getRegtrId());

				if (ordGodEntity.getLmttInvYn().equals("Y")) {

					godItm.setAffComLmttInvQty(-salePrearngeQty);
				}
				orderBoCommandRepository.updateGoodsItmOrd(godItm);
			}


		}
		
	}


    @Override
    void pckageshapeChk(OrdGodExtend ordGodEntity) throws Exception {
        if (ordGodEntity.getOrdGodList() == null || ordGodEntity.getOrdGodList().size() == 0 ) {
            throw new OrderCompleteFailException("pckageshapeChk:EMPTY");
        }
        
        OrderBoDTO orderBoDTO = new OrderBoDTO();
        orderBoDTO.setGodNo(ordGodEntity.getGodNo());
        List<GodCpstGodCnnc> gs =  orderBoCommandRepository.selectAffPckageshape(orderBoDTO);
        
        
        Map<String, String> pkGodMap = Maps.newHashMap();
        
        for (GodCpstGodCnnc godCpstGodCnnc : gs) {
            
            pkGodMap.put(godCpstGodCnnc.getCpstGodNo(), godCpstGodCnnc.getCpstGodNo());
            
        }
        
        for (OrdGodExtend ex : ordGodEntity.getOrdGodList()) {
            
            if (pkGodMap.containsKey(ex.getGodNo())) {
            
                pkGodMap.put(ex.getGodNo(),"S");
            }
                     
        }
        
        Iterator<String> it = pkGodMap.keySet().iterator();
        
        String rt = "";
        String rtn = "";
        String godNo = "";
        while (it.hasNext()) {
            String key = it.next();

            rt = pkGodMap.get(key);
            
            if(!"S".equals(rt)){
                rtn = "F";
                godNo += ","+rt;
        
            }

        }
        
        if("F".equals(rtn)){
            
            throw new OrderCompleteFailException("pckageshapeChk:"+godNo+"필수 구성 상품이 존재하지 않습니다.");
        }
        
    }
}
