package com.plgrim.ncp.biz.order.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodExtend;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdCpstGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrmExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtend;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.base.repository.ord.OrdCpstGodCnncRepository;
import com.plgrim.ncp.base.repository.ord.OrdGodAplPrmRepository;
import com.plgrim.ncp.base.repository.ord.OrdGodRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.order.data.OrderBoDTO;
import com.plgrim.ncp.biz.order.repository.OrderBoCommandRepository;
import com.plgrim.ncp.biz.order.repository.OrderBoSelectRepository;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.framework.enums.DatabaseType;


public abstract class OrderBoAbstractService extends AbstractService {


	@Autowired
	OrdRepository ordRepository; // 주문

	@Autowired
	OrdGodRepository ordGodRepository;// 주문 상품

	@Autowired
	OrdCpstGodCnncRepository ordCpstGodCnncRepository;// 주문 구성 상품 연결

	@Autowired
	OrdGodAplPrmRepository ordGodAplPrmRepository;// 주문 상품 적용 프로모션

	@Autowired
	MbrRepository mbrRepository;

	@Autowired
	OrderBoSelectRepository orderBoSelectRepository;

	@Autowired
	OrderBoCommandRepository orderBoCommandRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	protected void creatOrder(OrderBoDTO orderDTO) throws Exception {

		String ordNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_ORD", "OD", DatabaseType.ORACLE);

		Ord ord = orderDTO.getOrd();

		// 각 주문유형에 맞처서 세팅
		setOrder(ord);
		String mallId = StringUtils.defaultString(ord.getMallId());
		
		if("".equals(mallId)) {
			mallId = orderDTO.getMallId();
		}

		if (mbrChecK()) {
			Mbr mbr = new Mbr();
			mbr.setMbrNo(ord.getMbrNo());
			mbr = mbrRepository.selectMbr(mbr);
			orderDTO.setMbr(mbr);
			Functions.copyProperties(mbr, ord);

			if(mbr.getMbrNm().length() >=7){
				ord.setCstmrNm(mbr.getMbrNm().substring(0, 6));	
			}else{
				ord.setCstmrNm(mbr.getMbrNm());	
			}
			
			ord.setCstmrEmail(mbr.getMbrEmail());

		}

		ord.setLangCd(OrderClaimEnum.KOR.toString());
		ord.setCrncyCd(OrderClaimEnum.KRW.toString());
		ord.setDvcCd("PC");
		Functions.variAbleSetN(ord);
		
		// 2016.01.20 by Cannon
		// 가상배송완료여부 체크
		if("Y".equals(orderDTO.getAffOrdVirtlDlvComptYn())){
			ord.setVirtlDlvComptYn("Y");
		}
		
		ord.setOrdNo(ordNo);
		orderDTO.setOrdNo(ordNo);
		ord.setMallId(mallId);
		String regtrId = orderDTO.getRegtrId();
		ord.setOrdDt(new Date());
		ord.setRegtrId(regtrId);
		ord.setUdterId(regtrId);

		ord.setUnityPntAccmlSumAmt(BigDecimal.ZERO);
		ord.setEvtPntAccmlSumAmt(BigDecimal.ZERO);
		ord.setEmpDcSumAmt(BigDecimal.ZERO);
		ord.setSvcSumAmt(BigDecimal.ZERO);
		ord.setDlvCstSumAmt(BigDecimal.ZERO);
		ord.setPkagTaxSumAmt(BigDecimal.ZERO);
		ord.setAllDcSumAmt(BigDecimal.ZERO);
		ord.setGodDcSumAmt(BigDecimal.ZERO);
		ord.setBundleDcSumAmt(BigDecimal.ZERO);
		ord.setCrsDcSumAmt(BigDecimal.ZERO);
		ord.setGodCpnDcSumAmt(BigDecimal.ZERO);
		ord.setBskCpnDcSumAmt(BigDecimal.ZERO);
		ord.setDlvCstCpnDcSumAmt(BigDecimal.ZERO);
		ord.setImdtlDcSumAmt(BigDecimal.ZERO);
		ord.setUnityPntUseSumAmt(BigDecimal.ZERO);
		ord.setEvtPntUseSumAmt(BigDecimal.ZERO);
		ord.setWebpntUseSumAmt(BigDecimal.ZERO);
		ord.setMileUseSumAmt(BigDecimal.ZERO);
		ord.setWebpntAccmlSumAmt(BigDecimal.ZERO);
		ord.setMileAccmlSumAmt(BigDecimal.ZERO);

		if (pointYn()) {
			point(orderDTO);
		}

		ordRepository.insertOrd(orderDTO.getOrd());

	}

	public void point(OrdGodExtend ordGodEntity, God god, Ord ord) {

		long rt = 0;

		rt = ordGodEntity.getStdrCrncyAmt().intValue() * god.getPntAccmlRt().intValue() * ordGodEntity.getOrdQty();

		if (god.getPntAccmlYn() != null && god.getPntAccmlYn().equals("Y")) {
			ord.setUnityPntAccmlSumAmt(ord.getUnityPntAccmlSumAmt().add(new BigDecimal(String.valueOf(rt))));

		}


	}

	public void point(OrderBoDTO orderDTO) throws Exception {

		GodExtend god = new GodExtend();
		GodExtend subGod = new GodExtend();

		for (LgsDlvspExtend lgsDlvspExtend : orderDTO.getLgsDlvspList()) {

			for (OrdGodExtend ordGodEntity : lgsDlvspExtend.getOrdGodList()) {

				god = orderBoSelectRepository.selectBoOrdGodItmHist(ordGodEntity);
				BeanUtils.copyProperties(god, ordGodEntity);

				String godTp = ordGodEntity.getGodTpCd();



				if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))) {

					point(ordGodEntity, god, orderDTO.getOrd());

				}

				// 구성 상품
				if (godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString())) {

					if (ordGodEntity.getOrdGodList() != null) {

						for (OrdGodExtend sub : ordGodEntity.getOrdGodList()) {

							subGod = orderBoSelectRepository.selectBoOrdGodItmHist(sub);
							BeanUtils.copyProperties(subGod, sub);

							point(ordGodEntity, subGod, orderDTO.getOrd());
						}

					}

				}


			}
		}


	}


	public void ordGodProcessor(OrderBoDTO orderDTO, String regtrId)
			throws Exception {

		String ordNo = orderDTO.getOrd().getOrdNo();
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("ord_no", ordNo);
		long qty = orderDTO.getGodTotQty();
		int divStd = 100; // 금액 분배 기준100단위로
		int count = 1;
		int ordGodTurn = 1;
		int aplPrmTurn = 1;
		List<InfOrdGodErpDstb> infOrdGodErpDstbs = new ArrayList<InfOrdGodErpDstb>();

		List<OrdGodExtend> affOrdGodList = new ArrayList<OrdGodExtend>();
		
		Map<String, OrdGodAplPrmExtend> ordGiftMap = Maps.newHashMap();

		for (LgsDlvspExtend lgsDlvspExtend : orderDTO.getLgsDlvspList()) {

			int dlvPcupspTurn = lgsDlvspExtend.getDlvPcupspTurn();
			List<OrdGodExtend> ordGodList = new ArrayList<OrdGodExtend>();

			if (lgsDlvspExtend.getGifts() != null) {
				for (OrdGodAplPrmExtend ordGodAplPrm : lgsDlvspExtend.getGifts()) {

					ordGodAplPrm.setOrdGodGftTurn(ordGodTurn);
					ordGiftMap.put(ordGodAplPrm.getPrmNo() + "==" + ordGodAplPrm.getGftGodNo(), ordGodAplPrm);

					OrdGodExtend gift = ordGiftGodProcessor(regtrId, ordNo, ordGodTurn, infOrdGodErpDstbs, dlvPcupspTurn, ordGodAplPrm);
					gift.setOrdGiftYn("Y");
					ordGodList.add(gift);
					ordGodTurn++;
				}

				if (ordGodList.size() > 0) {
					lgsDlvspExtend.setOrdGodGifList(ordGodList);
				}

			}

		}

		for (LgsDlvspExtend lgsDlvspExtend : orderDTO.getLgsDlvspList()) {

			int dlvPcupspTurn = lgsDlvspExtend.getDlvPcupspTurn();
			List<OrdGodExtend> ordGodList = new ArrayList<OrdGodExtend>();

			for (OrdGodExtend ordGodEntity : lgsDlvspExtend.getOrdGodList()) {
				
				if (orderDTO.isGodSelect()) {
					GodExtend god = orderBoSelectRepository.selectBoOrdGodItmHist(ordGodEntity);
					BeanUtils.copyProperties(god, ordGodEntity);
				}
				
				// 2015-12-21 입점업체 확인여부 디폴트 'N' 입력 [AshA] 
				ordGodEntity.setPartmalComCnfirmYn("N");
				// 2015-12-21 주문제작 상품 여부 디폴트 'N' 입력 [AshA]
				ordGodEntity.setOrdmkGodYn("N");

				String godTp = ordGodEntity.getGodTpCd();

				int mainOrdGodTurn = ordGodTurn;

				ordGodEntity.setOrdNo(ordNo);
				ordGodEntity.setOrdGodTurn(ordGodTurn);
				ordGodEntity.setDlvPcupspTurn(dlvPcupspTurn);
				ordGodEntity.setRegtrId(regtrId);
				ordGodEntity.setUdterId(regtrId);
				ordGodEntity.setCrncyCd("KRW");

				if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))) {

					count = divideGod(orderDTO.getOrd(), qty, divStd, count, infOrdGodErpDstbs, ordGodEntity);
					/*
					 * 1. 수정일자   : 2016-02-26
					 * 2. 수정자     : 김재성 (jskim27)
					 * 3. 요청 SR NO : #17045
					 * 4. 수정내용   : [제휴] '주문상품' 데이터 생성 시 '입점업체수수료율' 등록
					 *		- ORD_GOD 데이터 생성 시, GOD 테이블에서 조회해서 INSERT
					 */
					//ordGodRepository.insertOrdGod(ordGodEntity);
					this.orderBoCommandRepository.insertOrdGod(ordGodEntity);

					affOrdGodList.add(ordGodEntity);
					if (godChecK()) {

						invProcessor(ordGodEntity);
					}
				}
				else {
				    /*
					 * 1. 수정일자   : 2016-02-26
					 * 2. 수정자     : 김재성 (jskim27)
					 * 3. 요청 SR NO : #17045
					 * 4. 수정내용   : [제휴] '주문상품' 데이터 생성 시 '입점업체수수료율' 등록
					 *		- ORD_GOD 데이터 생성 시, GOD 테이블에서 조회해서 INSERT
					 */
					//ordGodRepository.insertOrdGod(ordGodEntity);
					this.orderBoCommandRepository.insertOrdGod(ordGodEntity);

					affOrdGodList.add(ordGodEntity);
				}

				if (ordGodEntity.getGifts() != null) {
					for (OrdGodAplPrmExtend ordGodAplPrm : ordGodEntity.getGifts()) {
						ordGodTurn++;
						long sn = ordGodEntity.getDmstcDlvCstPlcSn();
						ordGodAplPrm.setDmstcDlvCstPlcSn(sn);
						ordGodAplPrm.setAplQty(ordGodEntity.getOrdQty());
						ordGodAplPrm.setAplAmt(ordGodEntity.getPayExchgRtCrncyAmt() );
						OrdGodExtend gift = giftGodProcessor(regtrId, ordNo, ordGodTurn, aplPrmTurn, infOrdGodErpDstbs, dlvPcupspTurn,
								mainOrdGodTurn, ordGodAplPrm);


						ordGodList.add(gift);
						aplPrmTurn++;
					}
				}

				if (ordGodEntity.getOrdGifts() != null) {

					for (OrdGodAplPrmExtend ordGodAplPrm : ordGodEntity.getOrdGifts()) {

						OrdGodAplPrmExtend ordGodApl = ordGiftMap.get(ordGodAplPrm.getPrmNo() + "==" + ordGodAplPrm.getGftGodNo());
						ordGodAplPrm.setAplQty(ordGodEntity.getOrdQty());
						ordGodAplPrm.setAplAmt(ordGodEntity.getPayExchgRtCrncyAmt() );
						ordGodAplPrmProcessor(regtrId, ordNo, ordGodApl.getOrdGodGftTurn(), aplPrmTurn, ordGodTurn, ordGodAplPrm);

						aplPrmTurn++;
					}

				}
				// 구성 상품
				if (godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString())) {

					if (ordGodEntity.getOrdGodList() != null) {

						long sn = ordGodEntity.getDmstcDlvCstPlcSn();
						for (OrdGodExtend sub : ordGodEntity.getOrdGodList()) {
							ordGodTurn++;
							
							if (orderDTO.isGodSelect()) {
								God subGod = orderBoSelectRepository.selectBoOrdGodItmHist(sub);
								BeanUtils.copyProperties(subGod, sub);
							}
							
							// 2015-12-21 입점업체 확인여부 디폴트 'N' 입력 [AshA] 
							sub.setPartmalComCnfirmYn("N");
							// 2015-12-21 주문제작 상품 여부 디폴트 'N' 입력 [AshA]
							sub.setOrdmkGodYn("N");

							sub.setOrdNo(ordNo);
							sub.setRegtrId(regtrId);
							sub.setUdterId(regtrId);
							sub.setOrdGodTurn(ordGodTurn);
							sub.setDlvPcupspTurn(dlvPcupspTurn);
							sub.setCrncyCd("KRW");
							count = divideGod(orderDTO.getOrd(), qty, divStd, count, infOrdGodErpDstbs, sub);
							sub.setDmstcDlvCstPlcSn(sn);
							/*
							 * 1. 수정일자   : 2016-02-26
							 * 2. 수정자     : 김재성 (jskim27)
							 * 3. 요청 SR NO : #17045
							 * 4. 수정내용   : [제휴] '주문상품' 데이터 생성 시 '입점업체수수료율' 등록
							 *		- ORD_GOD 데이터 생성 시, GOD 테이블에서 조회해서 INSERT
							 */
							//ordGodRepository.insertOrdGod(sub);
							this.orderBoCommandRepository.insertOrdGod(sub);

							affOrdGodList.add(sub);
							if (godChecK()) {

								invProcessor(sub);
							}
							OrdCpstGodCnnc ordCpstGodCnnc = new OrdCpstGodCnnc();
							ordCpstGodCnnc.setOrdNo(ordNo);
							ordCpstGodCnnc.setOrdGodTurn(mainOrdGodTurn);
							ordCpstGodCnnc.setOrdCpstGodTurn(ordGodTurn);
							ordCpstGodCnnc.setCpstGodQty(sub.getOrdQty());
							ordCpstGodCnnc.setRegtrId(regtrId);
							ordCpstGodCnnc.setUdterId(regtrId);
							/*
                             * 패키지형상품 유형 정보 세팅
                             *  - 세트상품(SET_GOD), 패키지 상품(PCKAGE_GOD)
                             *  - 추가 구성 상품(ADIT_CPST_GOD) 유형은 제휴주문데이터 생성 시 사용안함
                             */
							ordCpstGodCnnc.setPckageGodTpCd( godTp );

							// 주문 구성 상품 순번
							ordCpstGodCnncRepository.insertOrdCpstGodCnnc(ordCpstGodCnnc);

							if (sub.getGifts() != null) {
								for (OrdGodAplPrmExtend ordGodAplPrm : ordGodEntity.getGifts()) {
									ordGodTurn++;
									ordGodAplPrm.setDmstcDlvCstPlcSn(sn);
									ordGodAplPrm.setAplQty(ordGodEntity.getOrdQty());
									ordGodAplPrm.setAplAmt(ordGodEntity.getPayExchgRtCrncyAmt() );
									OrdGodExtend gift = giftGodProcessor(regtrId, ordNo, ordGodTurn, aplPrmTurn, infOrdGodErpDstbs,
											dlvPcupspTurn, sub.getOrdGodTurn(), ordGodAplPrm);

									ordGodList.add(gift);
									aplPrmTurn++;
								}
							}

							if (sub.getOrdGifts() != null) {

								for (OrdGodAplPrmExtend ordGodAplPrm : sub.getOrdGifts()) {

									OrdGodAplPrmExtend ordGodApl = ordGiftMap.get(ordGodAplPrm.getPrmNo() + "=="
											+ ordGodAplPrm.getGftGodNo());
									ordGodAplPrm.setAplQty(ordGodEntity.getOrdQty());
									ordGodAplPrm.setAplAmt(ordGodEntity.getPayExchgRtCrncyAmt() );
									ordGodAplPrmProcessor(regtrId, ordNo, ordGodApl.getOrdGodGftTurn(), aplPrmTurn, sub.getOrdGodTurn(),
											ordGodAplPrm);

									aplPrmTurn++;
								}

							}

						}

					}
					pckageshapeChk(ordGodEntity);
				}

				ordGodTurn++;
			}
			if (ordGodList.size() > 0) {

				lgsDlvspExtend.getOrdGodList().addAll(ordGodList);
			}

			if (lgsDlvspExtend.getOrdGodGifList() != null && lgsDlvspExtend.getOrdGodGifList().size() > 0) {
				lgsDlvspExtend.getOrdGodList().addAll(lgsDlvspExtend.getOrdGodGifList());
			}
		}

		orderDTO.setOrdGodList(affOrdGodList);
		orderDTO.setInfOrdGodErpDstbList(infOrdGodErpDstbs);

	}

	private OrdGodExtend ordGiftGodProcessor(String regtrId, String ordNo, int ordGodTurn,
			List<InfOrdGodErpDstb> infOrdGodErpDstbs, int dlvPcupspTurn, OrdGodAplPrmExtend ordGodAplPrm)
					throws Exception, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		OrdGodExtend gift = baseGiftGodProcessor(regtrId, ordNo, ordGodTurn, infOrdGodErpDstbs, dlvPcupspTurn, ordGodAplPrm);
		return gift;
	}

	private OrdGodExtend giftGodProcessor(String regtrId, String ordNo, int ordGodTurn, int aplPrmTurn,
			List<InfOrdGodErpDstb> infOrdGodErpDstbs, int dlvPcupspTurn, Integer mainOrdGodTurn, OrdGodAplPrmExtend ordGodAplPrm)
					throws Exception, ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		OrdGodExtend gift = baseGiftGodProcessor(regtrId, ordNo, ordGodTurn, infOrdGodErpDstbs, dlvPcupspTurn, ordGodAplPrm);

		ordGodAplPrmProcessor(regtrId, ordNo, ordGodTurn, aplPrmTurn, mainOrdGodTurn, ordGodAplPrm);

		return gift;
	}

	private OrdGodExtend baseGiftGodProcessor(String regtrId, String ordNo, int ordGodTurn, List<InfOrdGodErpDstb> infOrdGodErpDstbs,
			int dlvPcupspTurn, OrdGodAplPrmExtend ordGodAplPrm) throws Exception, ClassNotFoundException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		GodExtend god = orderBoSelectRepository.selectBoOrdGodGift(ordGodAplPrm);
		OrdGodExtend gift = new OrdGodExtend();

		BeanUtils.copyProperties(god, gift);

		gift.setOrdNo(ordNo);
		gift.setOrdGodTurn(ordGodTurn);
		gift.setDlvPcupspTurn(dlvPcupspTurn);
		gift.setRegtrId(regtrId);
		gift.setUdterId(regtrId);
		gift.setStdrCrncyAmt(new BigDecimal("0"));
		gift.setPayExchgRtCrncyAmt(new BigDecimal("0"));
		gift.setSaleAmt(new BigDecimal("0"));
		gift.setOrdQty(ordGodAplPrm.getAplQty());
		gift.setCrncyCd("KRW");
		gift.setSaleShopCd(ordGodAplPrm.getSaleShopCd());
		if(ordGodAplPrm.getDmstcDlvCstPlcSn() >0){
		
			gift.setDmstcDlvCstPlcSn(ordGodAplPrm.getDmstcDlvCstPlcSn());
		}
		
		// 2015-12-21 입점업체 확인여부 디폴트 'N' 입력 [AshA] 
		gift.setPartmalComCnfirmYn("N");
		// 2015-12-21 주문제작 상품 여부 디폴트 'N' 입력 [AshA]
		gift.setOrdmkGodYn("N");
		
		ordGodRepository.insertOrdGod(gift);

		if (godChecK()) {

			invProcessor(gift);
		}
		InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
		BeanUtils.copyProperties(gift, infOrdGodErpDstb);
		infOrdGodErpDstb.setStdrCrncyUntPrc(gift.getStdrCrncyAmt());
		infOrdGodErpDstb.setPayExchgRtCrncyUntPrc(gift.getPayExchgRtCrncyAmt());
		Functions.variAbleSetN(infOrdGodErpDstb);
		infOrdGodErpDstb.setCrncyCd(OrderClaimEnum.KOR.toString());
		for (int i = 0; i < ordGodAplPrm.getAplQty(); i++) {

			infOrdGodErpDstbs.add(infOrdGodErpDstb);

		}
		return gift;
	}


	private void ordGodAplPrmProcessor(String regtrId, String ordNo, int ordGodGftTurn, int aplPrmTurn, int ordGodTurn,
			OrdGodAplPrmExtend ordGodAplPrm) throws Exception {
		ordGodAplPrm.setOrdNo(ordNo);
		ordGodAplPrm.setAplPrmTurn(aplPrmTurn);
		ordGodAplPrm.setOrdGodGftTurn(ordGodGftTurn);
		ordGodAplPrm.setOrdGodTurn(ordGodTurn);
		ordGodAplPrm.setRegtrId(regtrId);
		ordGodAplPrm.setUdterId(regtrId);

		ordGodAplPrmRepository.insertOrdGodAplPrm(ordGodAplPrm);
	}



	private int divideGod(Ord ord, long qty, int divStd, int index,
			List<InfOrdGodErpDstb> infOrdGodErpDstbs,
			OrdGodExtend godCnnc) throws Exception {

		int count = index;
		BigDecimal empDcUntSumPrc = new BigDecimal("0");

		BigDecimal bskCpnDcUntSumPrc = new BigDecimal("0");
		BigDecimal unityPntUseUntSumPrc = new BigDecimal("0");
		BigDecimal evtPntUseUntSumPrc = new BigDecimal("0");

		BigDecimal empDcUntPrc = null;
		BigDecimal bskCpnDcUntPrc = null;
		BigDecimal unityPntUseUntPrc = null;
		BigDecimal evtPntUseUntPrc = null;
		BigDecimal stdrCrncyAmt = null;
		BigDecimal payExchgRtCrncyAmt = null;
		BigDecimal saleUntPrc = null;

		BigDecimal webDcSumAmt = new BigDecimal("0");

		BigDecimal multiplicand = new BigDecimal(godCnnc.getOrdQty());
		for (int i = 1; i <= multiplicand.intValue(); i++) {

			empDcUntPrc = divide(ord.getEmpDcSumAmt(), divStd, qty, count);
			bskCpnDcUntPrc = divide(ord.getBskCpnDcSumAmt(), divStd, qty, count);
			unityPntUseUntPrc = divide(ord.getUnityPntUseSumAmt(), divStd, qty, count);
			evtPntUseUntPrc = divide(ord.getEvtPntUseSumAmt(), divStd, qty, count);

			stdrCrncyAmt = divide(godCnnc.getStdrCrncyAmt(), 10, multiplicand.intValue(), i);
			payExchgRtCrncyAmt = divide(godCnnc.getPayExchgRtCrncyAmt(), 10, multiplicand.intValue(), i);
			saleUntPrc = divide(godCnnc.getSaleUntPrc(), 10, multiplicand.intValue(), i);
			

			BigDecimal webDcAmt = divide(godCnnc.getWebDcAmt(), 10, multiplicand.intValue(), i);

			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			BeanUtils.copyProperties(godCnnc, infOrdGodErpDstb);
			infOrdGodErpDstb.setStdrCrncyUntPrc(stdrCrncyAmt);
			infOrdGodErpDstb.setPayExchgRtCrncyUntPrc(payExchgRtCrncyAmt);
			infOrdGodErpDstb.setSaleUntPrc(saleUntPrc);
			infOrdGodErpDstb.setEmpDcUntPrc(empDcUntPrc);
			infOrdGodErpDstb.setBskCpnDcUntPrc(bskCpnDcUntPrc);
			infOrdGodErpDstb.setUnityPntUseUntPrc(unityPntUseUntPrc);
			infOrdGodErpDstb.setEvtPntUseUntPrc(evtPntUseUntPrc);

			infOrdGodErpDstb.setWebDcUntPrc(webDcAmt);

			Functions.variAbleSetN(infOrdGodErpDstb);

			infOrdGodErpDstb.setCrncyCd("KRW");

			/*
        	 * 2015-11-12 ERP 연동 대상 여부 추가 [AshA]
        	 * ㅁ. 입점 업체의 상품일 경우 N
        	 */
			infOrdGodErpDstb.setErpIntrlckTgtYn(GoodsEnum.GoodsPartmal.MCOM.toString().equals(godCnnc.getPartmalSectCd()) ? "Y" : "N");
            
			infOrdGodErpDstbs.add(infOrdGodErpDstb);

			empDcUntSumPrc = empDcUntSumPrc.add(empDcUntPrc);
			bskCpnDcUntSumPrc = bskCpnDcUntSumPrc.add(bskCpnDcUntPrc);
			unityPntUseUntSumPrc = unityPntUseUntSumPrc.add(unityPntUseUntPrc);
			evtPntUseUntSumPrc = evtPntUseUntSumPrc.add(evtPntUseUntPrc);

			webDcSumAmt = webDcSumAmt.add(webDcAmt);

			count++;

		}

		godCnnc.setEmpDcAmt(empDcUntSumPrc);
		godCnnc.setBskCpnDcAmt(bskCpnDcUntSumPrc);
		godCnnc.setUnityPntUseAmt(unityPntUseUntSumPrc);
		godCnnc.setEvtPntUseAmt(evtPntUseUntSumPrc);
		//godCnnc.setWebDcAmt(webDcSumAmt);
		return count;
	}


	public BigDecimal divide(BigDecimal amt, int divStd, long qty, int dlvspCount) {

		BigDecimal rt = null;

		if (amt != null && amt.intValue() > 0) {

			if ((amt.intValue() / divStd) / qty > 0) {

				if (dlvspCount == qty) {

					rt = new BigDecimal(amt.intValue() - ((amt.intValue() / divStd / qty * divStd) * (qty - 1)));

				}
				else {

					rt = new BigDecimal((amt.intValue() / divStd) / qty * divStd);
				}

			}
			else {

				if (dlvspCount == qty) {

					rt = amt;
				}
				else {

					rt = new BigDecimal("0");
				}

			}

		}
		else {

			rt = new BigDecimal("0");
		}
		return rt;


	}

	abstract void setOrder(Ord ord);

	abstract boolean mbrChecK();

	abstract boolean pointYn();

	abstract boolean invManageYn();

	abstract boolean godChecK();
	
	abstract void invProcessor(OrdGodExtend ordGodEntity) throws Exception;
	
	abstract void pckageshapeChk(OrdGodExtend ordGodEntity) throws Exception;
}
