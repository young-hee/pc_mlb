/* Copyright (c) 2013 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 *                       
 */
package com.plgrim.ncp.biz.claim.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.clm.*;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstb;
import com.plgrim.ncp.base.entities.datasource1.inf.InfOrdGodErpDstbRepair;
import com.plgrim.ncp.base.entities.datasource1.lgs.*;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdClmGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodAplPrm;
import com.plgrim.ncp.base.entities.datasource1.sys.SysExcpCd;
import com.plgrim.ncp.base.enums.GoodsEnum.GoodsType;
import com.plgrim.ncp.base.enums.OrderClaimEnum;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.repository.clm.ClmRepository;
import com.plgrim.ncp.base.repository.clm.ClmWrhsGodRepository;
import com.plgrim.ncp.base.repository.com.ComDmstcDlvCstPlcRepository;
import com.plgrim.ncp.base.repository.lgs.*;
import com.plgrim.ncp.base.repository.ord.OrdClmGodCnncRepository;
import com.plgrim.ncp.base.repository.ord.OrdGodRepository;
import com.plgrim.ncp.base.repository.ord.OrdRepository;
import com.plgrim.ncp.biz.claim.data.*;
import com.plgrim.ncp.biz.claim.data.ClmErpTrnsmis;
import com.plgrim.ncp.biz.claim.repository.*;
import com.plgrim.ncp.biz.claim.result.*;
import com.plgrim.ncp.biz.delivery.data.DeliveryOrderGoodDTO;
import com.plgrim.ncp.biz.delivery.repository.DeliveryCommandRepository;
import com.plgrim.ncp.biz.interfaces.repository.InfOrdGodErpDstbExtendRepository;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.biz.pay.repository.PayOrderRepository;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * CodeViewService Implementation
 * @author Park
 *
 */
/**
 * @author chakhan
 *
 */
@Slf4j
@Service
public class ClaimService extends AbstractService {


	@Autowired
	ClmEntityRepository clmEntityRepository;
	
	@Autowired
	ClmErpPntTrnsmisEntityRepository clmErpPntTrnsmisEntityRepository;
	
	@Autowired
	ClmWrhsGodAplPrmEntityRepository clmWrhsGodAplPrmEntityRepository;
	
	@Autowired
	ClmWrhsGodEntityRepository clmWrhsGodEntityRepository;
	
	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ClmRepository  clmRepository;
	
    @Autowired
    LgsDlvspRepository lgsDlvspRepository;// 물류배송지

    @Autowired
    LgsDlvspHistRepository lgsDlvspHistRepository;// 물류배송지 이력

    @Autowired
    LgsDlvRepository lgsDlvRepository;// 물류배송

    @Autowired
    LgsDlvHistRepository lgsDlvHistRepository;// 물류배송 이력
    
    @Autowired
    LgsRtrvlDrctGodRepository lgsRtrvlDrctGodRepository;
    
    @Autowired
    LgsRtrvlDrctGodHistRepository lgsRtrvlDrctGodHistRepository;
    
    @Autowired
    ClmWrhsGodRepository clmWrhsGodRepository;

    @Autowired
    OrdClmGodCnncRepository ordClmGodCnncRepository;
    
    @Autowired
    ComDmstcDlvCstPlcRepository comDmstcDlvCstPlcRepository;
    
    @Autowired
    @Qualifier("sessionTemplate1")
    SqlSession sqlSession1;
	
	@Autowired
	OrdGodRepository ordGodRepository;
    
	//스플릿 프로젝트로 추가2017-03-27
	@Autowired
	OrdRepository ordRepository; // 주문

	// 분배 스플릿 프로젝트로 추가2017-03-27
	@Autowired
	InfOrdGodErpDstbExtendRepository infOrdGodErpDstbExtendRepository;

	//스플릿 프로젝트로 추가2017-03-27
	@Autowired
	PayOrderRepository payOrdRepository;

	//스플릿 프로젝트로 추가2017-03-27
	@Autowired
	OrderSelectRepository orderSelectRepository;

	//스플릿 프로젝트로 추가2017-03-27
	@Autowired
	ClaimBatchRepository claimBatchRepository;

	@Autowired
	DeliveryCommandRepository deliveryCommandRepository;


	public String returnInsert(ClaimBoDTO claimBoDTO) throws Exception{
   
        String clmNo = getIdGenService().generateDBNumber(sqlSession1, "SQ_CLM", "CL", DatabaseType.ORACLE);
        String udterId = claimBoDTO.getUdterId();
     
        //클레임 마스터 등록
        switchClm(claimBoDTO, clmNo, udterId);

        // 수거지 등록
        int dlvPcupspTurn = claimBoDTO.getClmWrhsGodList().get(0).getDlvPcupspTurn();  
        Map<String, Object> conditions = Maps.newHashMap();        
        conditions.put("ord_no", claimBoDTO.getOrdNo());
        int newDlvPcupspTurn = getIdGenService().generateDBOrder(sqlSession1, "lgs_dlvsp", "dlv_pcupsp_turn", conditions,
                DatabaseType.ORACLE);
       
        switchLgsDlvsp(claimBoDTO, udterId,dlvPcupspTurn,newDlvPcupspTurn,clmNo);

        LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
        
        Map<Integer, Integer> map = Maps.newHashMap();
        
        Map<Integer, List<LgsRtrvlDrctGod>> dlvTurnMap = Maps.newHashMap();
        
        for (ClmWrhsGodExtend ex : claimBoDTO.getClmWrhsGodList()){
       
            OrdClmGodCnnc ordClmGodCnnc  = new OrdClmGodCnnc();
            
            ordClmGodCnnc.setClmNo(ex.getClmNo());
            ordClmGodCnnc.setClmWrhsGodTurn(ex.getClmWrhsGodTurn());
            ordClmGodCnnc = claimRepository.selectOrdClmGodCnncSw(ordClmGodCnnc);
            
            map.put(ex.getClmWrhsGodTurn(),ordClmGodCnnc.getOrdGodTurn());            
         
            lgsRtrvlDrctGod.setOrdNo(ex.getOrdNo());
            lgsRtrvlDrctGod.setClmNo(ex.getClmNo());
            lgsRtrvlDrctGod.setDlvPcupspTurn(dlvPcupspTurn);
            lgsRtrvlDrctGod.setClmWrhsGodTurn(ex.getClmWrhsGodTurn());
            List<LgsRtrvlDrctGod> v =  claimRepository.selectLgsRtrvlDrctGodSwList(lgsRtrvlDrctGod);
            
            for (LgsRtrvlDrctGod t : v) {
                
                
                if (dlvTurnMap.containsKey(t.getDlvTurn())) {
                    
                    List<LgsRtrvlDrctGod>   list = dlvTurnMap.get(t.getDlvTurn());
                    list.add(t);
                    dlvTurnMap.put(t.getDlvTurn(),list);
                }else{
                     
                    List<LgsRtrvlDrctGod>  list = new ArrayList<LgsRtrvlDrctGod>();
                    list.add(t);
                    dlvTurnMap.put(t.getDlvTurn(),list);
                }

            }
            
        }
        Map<Integer, Integer> ordGodTurn = Maps.newHashMap();
        
        switchClmWrhsGod(claimBoDTO, clmNo, udterId, newDlvPcupspTurn, map, ordGodTurn);
        
        switchLgsDlv(claimBoDTO, clmNo, udterId, dlvPcupspTurn, newDlvPcupspTurn, dlvTurnMap, ordGodTurn);
        
        ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
        
        claimReceiptDTO.setOrdNo(claimBoDTO.getOrdNo());
        claimReceiptDTO.setClmNo(clmNo);
        claimReceiptDTO.setAdminId(udterId);
        updateClmWrhsGodToPrcByErp(claimReceiptDTO);
        updateClmToPrcByClmWrhsGod(claimReceiptDTO);
        
        insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);
        return clmNo;
    }



    private void switchLgsDlv(ClaimBoDTO claimBoDTO, String clmNo, String udterId, int dlvPcupspTurn, int newDlvPcupspTurn,
              Map<Integer, List<LgsRtrvlDrctGod>> dlvTurnMap, Map<Integer, Integer> ordGodTurn)
            throws Exception {
        Iterator<Integer> itDlv = dlvTurnMap.keySet().iterator();
        
        int dlvCount = 1;
        while (itDlv.hasNext()) {
            
            Integer key = itDlv.next();
                        
            LgsDlv lgsDlv = new LgsDlv();
            
            lgsDlv.setOrdNo(claimBoDTO.getOrdNo());
            lgsDlv.setDlvTurn(key);
            lgsDlv.setDlvPcupspTurn(dlvPcupspTurn);
           
            lgsDlv =  lgsDlvRepository.selectLgsDlv(lgsDlv);
            lgsDlv.setClmNo(clmNo);
            lgsDlv.setDlvTurn(dlvCount);
            lgsDlv.setDlvPcupspTurn(newDlvPcupspTurn);
            
            // as-is : 교환배송비를 그대로 반품배송비로 전환 (2016.01.11 by Cannon)
            // lgsDlv.setRtgodDlvCst(lgsDlv.getExchgDlvCst());
                       
            if (!"SHOP_PKUP".equals(lgsDlv.getDlvMnCd())
            		&& StringService.isNotEmpty(claimBoDTO.getClmResnCd())
            		&& OrderClaimEnum.ClmRsnEnum.valueOf(claimBoDTO.getClmResnCd()).isMcomRsn() == false){
            	ComDmstcDlvCstPlc comDmstcDlCstvPlcParam	= new ComDmstcDlvCstPlc();
            	comDmstcDlCstvPlcParam.setDmstcDlvCstPlcSn(lgsDlv.getDmstcDlvCstPlcSn());
            	ComDmstcDlvCstPlc comDmstcDlvCstPlcDto		= comDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlc(comDmstcDlCstvPlcParam);
            	
            	lgsDlv.setStdrCrncyAmt(comDmstcDlvCstPlcDto.getBuyerImptRtgodDlvCst());
            	lgsDlv.setPayExchgRtCrncyAmt(comDmstcDlvCstPlcDto.getBuyerImptRtgodDlvCst());
            	lgsDlv.setRealityDlvCst(comDmstcDlvCstPlcDto.getBuyerImptRtgodDlvCst());
            	lgsDlv.setPlcDlvCst(comDmstcDlvCstPlcDto.getBuyerImptRtgodDlvCst());
            	lgsDlv.setCnclDlvCst(new BigDecimal(0));
            	lgsDlv.setRtgodDlvCst(comDmstcDlvCstPlcDto.getBuyerImptRtgodDlvCst());
            	lgsDlv.setExchgDlvCst(new BigDecimal(0));
            }else{
            	lgsDlv.setStdrCrncyAmt(new BigDecimal(0));            	
            	lgsDlv.setPayExchgRtCrncyAmt(new BigDecimal(0));
            	lgsDlv.setRealityDlvCst(new BigDecimal(0));
            	lgsDlv.setPlcDlvCst(new BigDecimal(0));
            	lgsDlv.setCnclDlvCst(new BigDecimal(0));
            	lgsDlv.setRtgodDlvCst(new BigDecimal(0));
            	lgsDlv.setExchgDlvCst(new BigDecimal(0));
            }
            
            lgsDlv.setRegtrId(udterId);
            lgsDlv.setUdterId(udterId);
            lgsDlvRepository.insertLgsDlv(lgsDlv);
            
            LgsDlvHist lgsDlvHist = new LgsDlvHist();

            BeanUtils.copyProperties(lgsDlv, lgsDlvHist);

            lgsDlvHist.setHistDt(new Date());

            lgsDlvHistRepository.insertLgsDlvHist(lgsDlvHist);
            
            
            List<LgsRtrvlDrctGod>  list = dlvTurnMap.get(key);
            int k=0;
            for (LgsRtrvlDrctGod v : list) {
                
                Integer clmWrhsGodTurn = ordGodTurn.get(v.getClmWrhsGodTurn());
                
                String rtrvlDrctGodNo = getIdGenService().generateDBNumber(sqlSession1, "sq_lgs_rtrvl_drct_god", "R", DatabaseType.ORACLE);
                v.setDlvTurn(dlvCount);
                v.setDlvPcupspTurn(newDlvPcupspTurn);
                v.setRtrvlDrctGodNo(rtrvlDrctGodNo);
                v.setClmNo(clmNo);
                v.setClmWrhsGodTurn(clmWrhsGodTurn);
                v.setRtrvlDrctTpCd("RTGOD");
                v.setRegtrId(udterId);
                v.setUdterId(claimBoDTO.getClmWrhsGodList().get(k).getUdterId());  // 교환시회수완료한 처리자 변경
                            
                
                lgsRtrvlDrctGodRepository.insertLgsRtrvlDrctGod(v);
                
                LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist = new LgsRtrvlDrctGodHist();
                
                BeanUtils.copyProperties(v, lgsRtrvlDrctGodHist);

                lgsRtrvlDrctGodHist.setHistTurn(1);
                
                lgsRtrvlDrctGodHistRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
                k++;
            }
            
            dlvCount++;
        }
    }



    private void switchClmWrhsGod(ClaimBoDTO claimBoDTO, String clmNo, String udterId, int newDlvPcupspTurn, Map<Integer, Integer> map,
            Map<Integer, Integer> ordGodTurn) throws Exception {
        Iterator<Integer> it = map.keySet().iterator();
        ClmWrhsGod clmWrhsGod = new ClmWrhsGod();
        OrdClmGodCnnc ordClmGodCnnc = new OrdClmGodCnnc();
        
   
        int count = 1;
        
        while (it.hasNext()) {
            
            Integer key = it.next();
            Integer turn = map.get(key);
            
            clmWrhsGod.setClmNo(claimBoDTO.getClmNo());
            clmWrhsGod.setClmWrhsGodTurn(key);
            clmWrhsGod = clmWrhsGodRepository.selectClmWrhsGod(clmWrhsGod);
            
            clmWrhsGod.setClmNo(clmNo);
            clmWrhsGod.setClmWrhsGodTurn(count);
            clmWrhsGod.setDlvPcupspTurn(newDlvPcupspTurn);
            clmWrhsGod.setRegtrId(udterId);
            clmWrhsGod.setUdterId(udterId);
            clmWrhsGod.setClmResnCd(claimBoDTO.getClmResnCd());
            clmWrhsGod.setCalDt(null);
            clmWrhsGodRepository.insertClmWrhsGod(clmWrhsGod);
            
            ordClmGodCnnc.setOrdNo(clmWrhsGod.getOrdNo());
            ordClmGodCnnc.setOrdGodTurn(turn);
            ordClmGodCnnc.setClmNo(clmNo);
            ordClmGodCnnc.setClmWrhsGodTurn(count);
            ordClmGodCnnc.setGodCnncTpCd("WRHS_GOD_CNNC");
            ordClmGodCnnc.setOrdClmQty(clmWrhsGod.getClmQty());
            ordClmGodCnnc.setOrdClmWthdrawQty(0L);
            ordClmGodCnnc.setRegtrId(udterId);
            ordClmGodCnnc.setUdterId(udterId);
            ordClmGodCnncRepository.insertOrdClmGodCnnc(ordClmGodCnnc);
            
            InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
            infOrdGodErpDstb.setClmNo(claimBoDTO.getClmNo());
            infOrdGodErpDstb.setClmWrhsGodTurn(key);
            
            List<InfOrdGodErpDstb> ls =   claimRepository.selectInfOrdGodErpDstbSwList(infOrdGodErpDstb);
            
            for (InfOrdGodErpDstb r : ls) {
                
                r.setClmNo(clmNo);
                r.setClmWrhsGodTurn(count);
                claimRepository.updateInfOrdGodErpDstbSw(r);
                
            }
            
            ordGodTurn.put(key, count);
            
            count++;
        }
    }



    private void switchLgsDlvsp(ClaimBoDTO claimBoDTO, String udterId,int dlvPcupspTurn ,int newDlvPcupspTurn, String clmNo) throws Exception {
        LgsDlvsp lgsDlvsp = new LgsDlvsp();
        lgsDlvsp.setOrdNo(claimBoDTO.getOrdNo());
        lgsDlvsp.setDlvPcupspTurn(dlvPcupspTurn);
        lgsDlvsp = lgsDlvspRepository.selectLgsDlvsp(lgsDlvsp);

        lgsDlvsp.setClmNo(clmNo);
        lgsDlvsp.setDlvPcupspTurn(newDlvPcupspTurn);
        lgsDlvsp.setRegtrId(udterId);
        lgsDlvsp.setUdterId(udterId);
        lgsDlvspRepository.insertLgsDlvsp(lgsDlvsp);
        
        LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();

        BeanUtils.copyProperties(lgsDlvsp, lgsDlvspHist);

        lgsDlvspHist.setHistDt(new Date());

        // 물류 배송지 이력
        lgsDlvspHistRepository.insertLgsDlvspHist(lgsDlvspHist);
    }



    private void switchClm(ClaimBoDTO claimBoDTO, String clmNo, String udterId) throws Exception {
        Clm clm = new Clm();
        clm.setClmNo(claimBoDTO.getClmNo());
        clm =  clmRepository.selectClm(clm);
        Date date = new Date();
        clm.setClmNo(clmNo);
        clm.setBfClmNo(claimBoDTO.getClmNo());
        clm.setClmStatCd("RCEPT");
        clm.setClmTpCd("RTGOD");
        clm.setClmResnCd(claimBoDTO.getClmResnCd());
        clm.setClmDt(date);             
        clm.setRceptAdminId(udterId);
        clm.setRceptDt(date);
        clm.setComptDt(null);
        clm.setRfdBnkAcctNo(claimBoDTO.getRfdBnkAcctNo());
        clm.setRfdAcnthldrNm(claimBoDTO.getRfdAcnthldrNm());
        clm.setRfdBnkCd(claimBoDTO.getRfdBnkCd());
        clm.setRegtrId(udterId);
        clm.setUdterId(udterId);
        clmRepository.insertClm(clm);
        
        //선진행구분코드 업데이트
        if (clm.getClmPreprogrsSectCd() != null && !clm.getClmPreprogrsSectCd().equals("")
				&& clm.getClmPreprogrsSectCd().equals("PREPROGRS")) {
        	ClaimBoDTO cbDTO = new ClaimBoDTO();
        	cbDTO.setClmNo(clmNo);
        	cbDTO.setClmPreprogrsSectCd("PREPROGRS");
        	cbDTO.setUdterId(udterId);
        	claimRepository.updateClmPreprogrsSectCd(cbDTO);
		}
    }
    
    
	
	/**
	 * 주문번호나 클레임번호로 취소상태가 아닌 클레임이 있는지 체크한다.
	 * @param claimSearchDTO
	 * @throws Exception
	 */
	public int checkExistClmRceptCompt(ClaimSearchDTO claimSearchDTO) throws Exception{		
		return claimRepository.getClmRceptCompt(claimSearchDTO);
    }

	/**
	 * ordNo 로 ord 정보를 clm 테이블에 select insert 한다. 
	 * @param claimSearchDTO
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public String insertClmByOrdGenClmNo(ClaimReceiptDTO claimTotalCancelDTO) throws NumberFormatException, Exception {
		return claimRepository.insertClmByOrdGenClmNo(claimTotalCancelDTO);
    }

	/**
	 * ordNo 로 ordGod 정보를 clmWrhsGod 테이블에 select insert 한다.
	 * @param claimSearchDTO
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertClmWrhsGodByOrdGod(ClaimReceiptDTO claimTotalCancelDTO) throws NumberFormatException, Exception {
		claimRepository.insertClmWrhsGodByOrdGod(claimTotalCancelDTO);
    }
	
	
	//ㄴㄴㄴ

	/**
	 * 부분취소 반품 공통사용 클레임 입고상품 조회
	 * @param clmWrhsGodExtend
	 * @return ClmWrhsGod
	 */
	public ClmWrhsGod selectClmWrhsGod(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmWrhsGod(clmWrhsGodExtend);
    }
	
	public ClmWrhsGod selectClmWrhsGodRt(ClmWrhsGodExtend clmWrhsGodExtend) {
	        return claimRepository.selectClmWrhsGodRt(clmWrhsGodExtend);
	
	}
	/**
	 * 부분취소 반품 공통사용 클레임 입고상품 insert
	 * @param clmWrhsGodEntity
	 * @throws NumberFormatException
	 * @throws Exception	 	
		[Tmall]2015-11-18 : 여태성 - 클레임 입고상품 테이블 insert 후 입고상품순번 리턴하도록 변경
	 */
	public int insertClmWrhsGodForRtrvl(ClmWrhsGodExtend clmWrhsGodExtend) throws NumberFormatException, Exception {
        /* 클레임입고상품순번 */
        Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("clm_no", clmWrhsGodExtend.getClmNo());
		int clmWrhsGodTurn = getIdGenService().generateDBOrder(sqlSession1, "clm_wrhs_god", "clm_wrhs_god_turn", conditions, DatabaseType.ORACLE);
		clmWrhsGodExtend.setClmWrhsGodTurn(clmWrhsGodTurn);
		claimRepository.insertClmWrhsGodForRtrvl(clmWrhsGodExtend);
		return clmWrhsGodTurn;
    }
	
	/**
	 * 부분취소 반품 공통사용 클레임 입고상품 update
	 * @param clmWrhsGodExtend
	 */
	public void updateClmWrhsGod(ClmWrhsGodExtend clmWrhsGodExtend) {
		claimRepository.updateClmWrhsGod(clmWrhsGodExtend);	    
    }
	

	
	
	
	
	
	/**
	 * ordNo 로 ordGod 정보를 clmWrhsGod 테이블에 select insert 한다. 부분취소
	 * @param claimSearchDTO
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertUnitClmWrhsGodByOrdGod(ClaimReceiptDTO claimReceiptDTO) throws NumberFormatException, Exception {
		claimRepository.insertUnitClmWrhsGodByOrdGod(claimReceiptDTO);
    }

	/**
	 * 해당ordNo 로 ordGod 와 clmWrhsGod 를 join 하여 주문클레임상품연결 테이블에 insert 한다. 취소일경우 입고상태로insert
	 * @param claimTotalCancelDTO
	 */
	public void insertOrdClmGodCnnc (ClmWrhsGodExtend clmWrhsGodExtend) throws Exception{
		claimRepository.insertOrdClmGodCnnc(clmWrhsGodExtend);
    }
	
	
	public void updateClmStatCd(Clm clm) throws Exception  {
		claimRepository.updateClmStatCd(clm);
    }
	
	public void updateClmForAllWthdraw(Clm clm) throws Exception  {
		claimRepository.updateClmForAllWthdraw(clm);		
	}
	
	/**
	 * 클레임 주문클레임 적용 프로모션 테이블 insert
	 * @param claimReceiptDTO
	 */
	public void insertOrdClmAplPrmByOrd(ClaimReceiptDTO claimReceiptDTO) {	    
		claimRepository.insertOrdClmAplPrmByOrd(claimReceiptDTO);
    }

	/**
	 * 클레임 클레임 입고상품 적용 프로모션 테이블 insert
	 * @param claimReceiptDTO
	 */
	public void insertClmWrhsGodAplPrmByOrd(ClaimReceiptDTO claimReceiptDTO) {
		claimRepository.insertClmWrhsGodAplPrmByOrd(claimReceiptDTO);	    
    }

	
	/**
	 * 클레임 입고상품 테이블 가격정보 update inf erp 분배 테이블을 join 하여 수량별 가격을  sum 한 정보로 update 한다.
	 * @param claimReceiptDTO
	 */
	public void updateClmWrhsGodToPrcByErp(ClaimReceiptDTO claimReceiptDTO) {
		claimRepository.updateClmWrhsGodToPrcByErp(claimReceiptDTO);
    }

	/**
	 * 클레임 가격정보 update 
	 * (가격정보 클레임입고상품테이블 join 하여 sum 해서 update)
	 * @param claimReceiptDTO
	 */
	public void updateClmToPrcByClmWrhsGod(ClaimReceiptDTO claimReceiptDTO) {
		claimRepository.updateClmToPrcByClmWrhsGod(claimReceiptDTO);
    }
	
	/**
	 * 클레임 가격정보 update
	 * (가격정보 INF_ORD_GOD_ERP_DSTB join 하여 해당 상품 만큼 차감)
	 * @param claimReceiptDTO
	 */
	public void updateRepairClmToPrcByClmWrhsGod(ClaimReceiptDTO claimReceiptDTO) {
		claimRepository.updateRepairClmToPrcByClmWrhsGod(claimReceiptDTO);
    }

	/**
	 * 클레임 배송지별 클레임고상품 결제환율가격을 sum 한 금액을 리턴
	 * @param clmPaySumAmtSearchDTO
	 * @return
	 */
	public RefundResultDTO getClmPaySumAmt(ClmPaySumAmtSearchDTO clmPaySumAmtSearchDTO) {
		
	    return claimRepository.getClmPaySumAmt(clmPaySumAmtSearchDTO);
    }

	
	
	
	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/
	
	
	/**
	 * 클레임등록
	 * 주문을 클레임에 조회후 저장한다. 
	 * @param claimDTO
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertClmForRtrvl(ClaimBoDTO claimDTO) throws NumberFormatException, Exception {
		claimRepository.insertClmForRtrvl(claimDTO);
    }
	
	/**
	 * 클레임입고상품 등록
	 * @param claimDTO
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void clmWrhsGodProcessor(ClaimBoDTO claimDTO) throws NumberFormatException, Exception {

        String clmNo 	= claimDTO.getClmNo();
        String ordNo 	= claimDTO.getOrdNo();
        String regtrId 	= claimDTO.getRegtrId();
		int indexDlvsp	= 0;//교환접수시 교환상품의 클레임입고상품을 조회하기 위한 용도
		int indexGod	= 0;//교환접수시 교환상품의 클레임입고상품을 조회하기 위한 용도

		//세트상품 관련
		int ordGodTurn			= 0;	//주문상품순번
		int clmWrhsGodTurnAnce	= 0;	//세트상품순번-부모
		int ordGodTurnAnce		= 0;	//주문 구성 상품 연결 상품순번
		int ordCpstGodTurn		= 0;	//주문 구성 상품 순번
		String pckageGodTpCd	= "";	//주문 구성 상품 유형
		int sortSeq				= 0;	//주문 정렬 순서
		
        Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("clm_no", clmNo);
		
        //물류배송지 정보 추출
        for (LgsDlvspExtend lgsDlvspExtend : claimDTO.getLgsDlvspList())
        {
			//클레임수거지 인 경우
			if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP"))
			{
				indexGod	= 0;

	        	//배송지순번 추출
	            int dlvPcupspTurn = lgsDlvspExtend.getDlvPcupspTurn();
	
	            //클레임입고상품 정보 추출
	            for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList()) 
	            {

	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] indexDlvsp 	: " + indexDlvsp	+ "<<<<<<<<<<");
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] indexGod 	: " + indexGod 		+ "<<<<<<<<<<");
	            	                
	                /* 클레임입고상품순번 */
	        		int clmWrhsGodTurn = getIdGenService().generateDBOrder(sqlSession1, "clm_wrhs_god", "clm_wrhs_god_turn", conditions, DatabaseType.ORACLE);
	        		//교환접수시 교환상품의 클레임입고상품을 조회하기 위한 용도
	        		lgsDlvspExtend.getClmWrhsGodList().get(indexGod).setClmWrhsGodTurn(clmWrhsGodTurn);

	            	/**
	            	 * 상품 유형 코드
	            	ㅁ. 상품구분 : GOD_TP
	               	>. 일반상품 : GNRL_GOD
	               	>. 사은품 : GFT
	               	>. 세트상품 : SET_GOD
	               	>. 패키지 상품 : PCKAGE_GOD
	               	>. 상품권 : GFCT	 
	            	 */
	                String godTp = clmWrhsGodEntity.getGodTpCd();
	
	                //int mainOrdGodTurn = clmWrhsGodTurn;
	
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmNo 			: " + clmNo 			+ "<<<<<<<<<<");
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] ordNo 			: " + ordNo 			+ "<<<<<<<<<<");
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] godTp 			: " + godTp 			+ "<<<<<<<<<<");
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodTurn	: " + clmWrhsGodTurn 	+ "<<<<<<<<<<");
	        		//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodTurn2	: " + lgsDlvspExtend.getClmWrhsGodList().get(indexGod).getClmWrhsGodTurn()	+ "<<<<<<<<<<");
	
	                clmWrhsGodEntity.setClmNo(clmNo);						/* 클레임 번호CL || YYYYMMDD || 7자리 Cycle Sequence */
	                clmWrhsGodEntity.setClmWrhsGodTurn(clmWrhsGodTurn);		/* 클레임 입고 상품 순번 */
	                clmWrhsGodEntity.setDlvPcupspTurn(dlvPcupspTurn);		/* 배송 수거지 순번 */
	                //clmWrhsGodEntity.setClmResnCd(clmResnCd);				/* 클레임 사유 코드ㅁ. 클레임 사유 : CLM_RSN */					
	                //clmWrhsGodEntity.setClmResnCont(clmResnCont);			/* 클레임 사유 내용 */
	                clmWrhsGodEntity.setRepairYn("N");						/* 수선 여부 */
	                clmWrhsGodEntity.setClmWthdrawSectCd("NRMLT");			/* 클레임 철회 구분 >. 정상 : NRMLT   >. 부분 철회 : PART_WTHDRAW   >. 
	                //clmWrhsGodEntity.setClmQty(clmQty);					/* 클레임 수량 */
	                clmWrhsGodEntity.setRegtrId(regtrId);
	                clmWrhsGodEntity.setOrdNo(ordNo);						/* 주문번호 */
	                //clmWrhsGodEntity.setOrdGodTurn(ordGodTurn);			/* 주문상품순번 */
	
	                //물류회수지시상품의 회수지시수량-물류출고지시상품 기준
	                clmWrhsGodEntity.setRtrvlDrctQty(clmWrhsGodEntity.getClmQty());
	                
	                ClmWrhsGod clmWrhsGod = new ClmWrhsGod();
	                ClmWrhsGod clmWrhsGodForSet = new ClmWrhsGod();
	      		    ClmWrhsCpstGodCnnc clmWrhsCpstGodCnnc = new ClmWrhsCpstGodCnnc();//클레임 입고 구성 상품 연결
	                
	                if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))) 
	                {
	                	//일반상품 또는 구성품
	                    //count = divideGod(claimDTO.getOrd(), qty, divStd, count, infOrdGodErpDstbs, clmWrhsGodEntity);
	                    //ordGodRepository.insertOrdGod(clmWrhsGodEntity);
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] 일반상품 또는 구성품 <<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmNo()         : " + clmWrhsGodEntity.getClmNo()          + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmWrhsGodTurn(): " + clmWrhsGodEntity.getClmWrhsGodTurn() + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getDlvPcupspTurn() : " + clmWrhsGodEntity.getDlvPcupspTurn()  + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmResnCd()     : " + clmWrhsGodEntity.getClmResnCd()      + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmResnCont()   : " + clmWrhsGodEntity.getClmResnCont()    + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getRepairYn()      : " + clmWrhsGodEntity.getRepairYn()       + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmQty()        : " + clmWrhsGodEntity.getClmQty()         + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getRegtrId()       : " + clmWrhsGodEntity.getRegtrId()        + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getOrdNo()         : " + clmWrhsGodEntity.getOrdNo()          + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getOrdGodTurn()    : " + clmWrhsGodEntity.getOrdGodTurn()     + "<<<<<<<<<<");

	                	//ncp2  - 현재 반품배송지가 합쳐질 경우 같은상품, 같은 아이템인 경우 클레임입고상품을 update 하고 있음.
        		    	//요건변경 - 반품배송지가 합쳐질 경우 클레임입고상품 합치지 않는다.
	                	//주문상품의 레코드와 같게 만든다. >> 무조건 생성.
        		    	claimRepository.insertClmWrhsGodForRtrvl(clmWrhsGodEntity);//insert	.

	                	/*
	                	clmWrhsGod = claimRepository.selectClmWrhsGodRt(clmWrhsGodEntity);
	          		    
	        		    if(clmWrhsGod ==null){
	        		    	claimRepository.insertClmWrhsGodForRtrvl(clmWrhsGodEntity);//insert	.
	        		    }else{
	        		    	clmWrhsGodEntity.setClmWrhsGodTurn(clmWrhsGod.getClmWrhsGodTurn());
	        		    	Long qty = clmWrhsGodEntity.getClmQty()+clmWrhsGod.getClmQty();
	        		    	clmWrhsGodEntity.setOrdClmQty(qty);	//clmQty 가 아님
	        		    	claimRepository.updateClmWrhsGod(clmWrhsGodEntity);//update	
	        		    } 
	        		    */



//	        		    if(("SET_GOD".equals(clmWrhsGodEntity.getPckageGodTpCd()) || "ADIT_CPST_GOD".equals(clmWrhsGodEntity.getPckageGodTpCd())))
        		    	if(StringService.equalsIgnoreCase(clmWrhsGodEntity.getPckageGodTpCd(), "SET_GOD") || StringService.equalsIgnoreCase(clmWrhsGodEntity.getPckageGodTpCd(), "ADIT_CPST_GOD"))
	        		    {       		    
		        		    if(ordGodTurn == clmWrhsGodEntity.getOrdGodTurnAnce()){
		        		    	
		        		    	//클레임 입고 구성 상품 연결 등록 하기 위한 조회
		        		    	clmWrhsGodForSet = claimRepository.selectClmWrhsGodForSet(clmWrhsGodEntity);
		        		    	
		        		    	//클레임 입고 구성 상품 연결 등록
		        		    	clmWrhsCpstGodCnnc.setClmNo(clmWrhsGodForSet.getClmNo());
		        		    	clmWrhsCpstGodCnnc.setClmWrhsGodTurn(clmWrhsGodTurnAnce);
		        		    	clmWrhsCpstGodCnnc.setClmWrhsCpstGodTurn(clmWrhsGodForSet.getClmWrhsGodTurn());
		        		    	clmWrhsCpstGodCnnc.setPckageGodTpCd(clmWrhsGodEntity.getPckageGodTpCd());
		        		    	clmWrhsCpstGodCnnc.setCpstGodQty(clmWrhsGodForSet.getClmQty());
		        		    	clmWrhsCpstGodCnnc.setSortSeq(sortSeq++);
		        		    	clmWrhsCpstGodCnnc.setRegtrId(regtrId);
		        		    	clmWrhsCpstGodCnnc.setUdterId(regtrId);
	
		        		    	claimRepository.insertClmWrhsCpstGodCnncForRtrvl(clmWrhsCpstGodCnnc);
		        		    	
		        		    } else {
		        		    	//세트상품 저장용 초기화
		                		ordGodTurn = 0;
		                		clmWrhsGodTurnAnce = 0;	
		                		sortSeq = 0;
		        		    }
	        		    } 
	                }
	                else 
	                {
	                	//세트 인 경우
	                	if (godTp.equals(GoodsType.SET_GOD.toString())){
	                		ordGodTurn = clmWrhsGodEntity.getOrdGodTurn();
	                		clmWrhsGodTurnAnce = clmWrhsGodEntity.getClmWrhsGodTurn();
	                	}
	
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] 패키지, 세트, 사은품, 상품권 인 경우 <<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmNo()         : " + clmWrhsGodEntity.getClmNo()          + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmWrhsGodTurn(): " + clmWrhsGodEntity.getClmWrhsGodTurn() + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getDlvPcupspTurn() : " + clmWrhsGodEntity.getDlvPcupspTurn()  + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmResnCd()     : " + clmWrhsGodEntity.getClmResnCd()      + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmResnCont()   : " + clmWrhsGodEntity.getClmResnCont()    + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getRepairYn()      : " + clmWrhsGodEntity.getRepairYn()       + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getClmQty()        : " + clmWrhsGodEntity.getClmQty()         + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getRegtrId()       : " + clmWrhsGodEntity.getRegtrId()        + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getOrdNo()         : " + clmWrhsGodEntity.getOrdNo()          + "<<<<<<<<<<");
						//log.info(">>>>>>>>>> [claimService.clmWrhsGodProcessor] clmWrhsGodEntity.getOrdGodTurn()    : " + clmWrhsGodEntity.getOrdGodTurn()     + "<<<<<<<<<<");

	                	//ncp2  - 현재 반품배송지가 합쳐질 경우 같은상품, 같은 아이템인 경우 클레임입고상품을 update 하고 있음.
        		    	//요건변경 - 반품배송지가 합쳐질 경우 클레임입고상품 합치지 않는다.
	                	//주문상품의 레코드와 같게 만든다. >> 무조건 생성.
        		    	claimRepository.insertClmWrhsGodForRtrvl(clmWrhsGodEntity);//insert	.

	                	/*
	          		    clmWrhsGod = claimRepository.selectClmWrhsGodRt(clmWrhsGodEntity);
	          		    
	        		    if(clmWrhsGod ==null){
	        		    	claimRepository.insertClmWrhsGodForRtrvl(clmWrhsGodEntity);//insert	.
	        		    }else{
	        		    	clmWrhsGodEntity.setClmWrhsGodTurn(clmWrhsGod.getClmWrhsGodTurn());
	        		    	Long qty = clmWrhsGodEntity.getClmQty()+clmWrhsGod.getClmQty();
	        		    	clmWrhsGodEntity.setOrdClmQty(qty);	//clmQty 가 아님
	        		    	claimRepository.updateClmWrhsGod(clmWrhsGodEntity);//update	
	        		    }                
	        		    */

	                }
	                
	                indexGod++;
	
	            }
			}
			
			indexDlvsp++;
        }	        
        		
	}
	
	/**
	 * 주문클레임상품연결 등록
	 * @param claimDTO
	 * @param dlvGbn        //회수지시(반품, 교환) : true , 출고지시(교환) : false
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertOrdClmGodCnncForRtrvl(ClaimBoDTO claimDTO, Boolean dlvGbn) throws NumberFormatException, Exception {
		
        //물류배송지 정보 추출
        for (LgsDlvspExtend lgsDlvspExtend : claimDTO.getLgsDlvspList())
        {
        	//회수지시 관련(반품, 교환) 이면
			if(dlvGbn)
			{
				//클레임수거지 인 경우
				if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_PCUPSP"))
				{
					//클레임입고상품 정보 추출
					for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList()) 
					{
						String godTp = clmWrhsGodEntity.getGodTpCd();

						if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))) 
						{

							clmWrhsGodEntity.setOrdClmQty(clmWrhsGodEntity.getClmQty());
							claimRepository.insertOrdClmGodCnncForRtrvl(clmWrhsGodEntity);//insert	.

						}
						else 
						{

							clmWrhsGodEntity.setOrdClmQty(clmWrhsGodEntity.getClmQty());
							claimRepository.insertOrdClmGodCnncForRtrvl(clmWrhsGodEntity);//insert	.

						}
					}
				
				}
				
			} 
			//출고지시 관련(교환) 이면
			else 
			{
				//클레임배송지 인 경우
				if(StringService.equalsIgnoreCase(lgsDlvspExtend.getDlvPcupspSectCd(), "CLM_DLVSP"))
				{
					//클레임입고상품 정보 추출
					for (ClmWrhsGodExtend clmWrhsGodEntity : lgsDlvspExtend.getClmWrhsGodList()) 
					{
						String godTp = clmWrhsGodEntity.getGodTpCd();
						
						if (!(godTp.equals(GoodsType.SET_GOD.toString()) || godTp.equals(GoodsType.PCKAGE_GOD.toString()))) 
						{

							clmWrhsGodEntity.setOrdClmQty(clmWrhsGodEntity.getClmQty());
							claimRepository.insertOrdClmGodCnncForRtrvl(clmWrhsGodEntity);//insert	.


						}
						else 
						{
	
							clmWrhsGodEntity.setOrdClmQty(clmWrhsGodEntity.getClmQty());
							claimRepository.insertOrdClmGodCnncForRtrvl(clmWrhsGodEntity);//insert	.

						}
					}
				
				}				
				
			}
        	
        }                   
    }

	/**
	 * 주문클레임적용프로모션 등록
	 * @param claimDTO
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertOrdClmAplPrmForRtrvl(ClaimBoDTO claimDTO) throws NumberFormatException, Exception {
		claimRepository.insertOrdClmAplPrmForRtrvl(claimDTO);
    }
	
	
	/**
	 * 주문클레임상품연결 등록
	 * @param clmWrhsGodEntity
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertOrdClmGodCnncForRtrvl(ClmWrhsGodExtend clmWrhsGodEntity) throws NumberFormatException, Exception {
		claimRepository.insertOrdClmGodCnncForRtrvl(clmWrhsGodEntity);
    }
	
	/**
	 * 클레임입고상품적용프로모션 등록
	 * @param claimDTO
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void insertClmWrhsGodAplPrmForRtrvl(ClaimBoDTO claimDTO) throws NumberFormatException, Exception {
		claimRepository.insertClmWrhsGodAplPrmForRtrvl(claimDTO);
    }
	
	/**
	 * 클레임번호 채번
	 * @param claimReturnDTO
	 * @throws Exception
	 */
	public String selectClmNo() throws Exception {
		return claimRepository.selectClmNo();
	}
	
	
	/**
	 * 클레임 메모저장
	 * @param claimReturnDTO
	 * @throws Exception
	 */
	public void updateRtrvlStat(LgsRtrvlDrctGodExtend lgsRtrvlDrctGodExtend) throws Exception {
		claimRepository.updateRtrvlStat(lgsRtrvlDrctGodExtend);
	}
	
	
	
	/**
	 * 클레임 메모저장
	 * @param claimReturnDTO
	 * @throws Exception
	 */
	public void updateCsoCnsltMemo(CsoCnsltMemo csoCnsltMemo) throws Exception {
		claimRepository.updateCsoCnsltMemo(csoCnsltMemo);
	}
	
	/**
	 * 클레임 취소
	 * @param claimReturnSearchDTO
	 * @throws Exception
	 */
	public Map<String, String> selectClaimCheckInfo(ClaimReturnSearchDTO claimReturnSearchDTO) throws Exception {
		return claimRepository.selectClaimCheckInfo(claimReturnSearchDTO);
	}

	/**
	 * 클레임 접수 가능 수량조회
	 * @param claimDTO
	 * @throws Exception
	 */
	public List<Map<String, String>> selectCheckClmQty(ClaimBoDTO claimDTO) throws Exception {
		return claimRepository.selectCheckClmQty(claimDTO);
	}
	
	
	public List<String> selectOrdGodTurn(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectOrdGodTurn(clmWrhsGodExtend);		
    }
	
	
	/**
	 * base 클레임 조회
	 * @param clm
	 * @return
	 * @throws Exception 
	 */
	public Clm selectClm(Clm clm) throws Exception{
		return clmEntityRepository.selectClm(clm);
	}

	
	/**
	 * 주문상세 페이지 클레임 정보
	 * @param claimSearchDTO
	 * @param pageParam
	 * @return
	 */
	public List<OrdDetailClmInfoResult> selectClaimInfo(ClaimSearchDTO claimSearchDTO){
		return claimRepository.selectClaimInfo(claimSearchDTO);
	}

	public long selectClaimCheckInfoCnt(ClaimSearchDTO claimSearchDTO) {
		return claimRepository.selectClaimInfoCnt(claimSearchDTO);
	}
	
	public void updateClmRcept(Clm clm) {	    
		claimRepository.updateClmRcept(clm);
    }
	
	public void updateClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		claimRepository.updateClmWrhsGodWthdraw(clmWrhsGodExtend);
    }
	
	public void updateRepairClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		claimRepository.updateRepairClmWrhsGodWthdraw(clmWrhsGodExtend);
    }


	
	public void updateClmErpWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		claimRepository.updateClmErpWthdraw(clmWrhsGodExtend);
    }
	
	public void updateRtrvlStatWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		claimRepository.updateRtrvlStatWthdraw(clmWrhsGodExtend);
    }

    public void updateRepairRtrvlStatWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		claimRepository.updateRepairRtrvlStatWthdraw(clmWrhsGodExtend);
    }

	public int selectClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmWrhsGodWthdraw(clmWrhsGodExtend);
    }

    public int selectRepairClmWrhsGodWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectRepairClmWrhsGodWthdraw(clmWrhsGodExtend);
    }

	public int selectClmRtrvDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmRtrvDrctWthdraw(clmWrhsGodExtend);
    }
	
	public int selectClmRtrvDrctWthdrawSendErp(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmRtrvDrctWthdrawSendErp(clmWrhsGodExtend);
    }

	public int selectClmRtrvDrct(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmRtrvDrct(clmWrhsGodExtend);
    }

    public int selectClmRtrvDrctWthdrawSwitch(ClmWrhsGodExtend clmWrhsGodExtend) {
        return claimRepository.selectClmRtrvDrctWthdrawSwitch(clmWrhsGodExtend);
    }
	
	public int selectClmDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectClmDrctWthdraw(clmWrhsGodExtend);
    }
	public int selectSwitchClmDrctWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectSwitchClmDrctWthdraw(clmWrhsGodExtend);
    }

	////////////////////K2////////////////////
	
	public int selectOrdErpErpCnt(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectOrdErpErpCnt(clmWrhsGodExtend);
    }	
	
	public void updateRtrvlDstbWthdraw(ClmWrhsGodExtend clmWrhsGodExtend) {	    
		  claimRepository.updateRtrvlDstbWthdraw(clmWrhsGodExtend);
    }
     	
	public ClaimUserInfoDetailResult selectClaimUserInfo(String clmNo) {	 
		  return claimRepository.getClaimUserInfo(clmNo);
    }
    ////////////////////////////////////////////
	
	
//	/**
//	 * 클레임 취소
//	 * @param claimReturnSearchDTO
//	 * @throws Exception
//	 */
//	public CancelClaimInfoResult selectClaimCheckInfo(ClaimReturnSearchDTO claimReturnSearchDTO) throws Exception {
//		return claimRepository.selectClaimCheckInfo(claimReturnSearchDTO);
//	}

	public void updateRfdBnk(ClmExtend clmExtend) {	    
		claimRepository.updateRfdBnk(clmExtend);
    }

	
	public String selectSeqExchFpkey(String clmNo) {
		return claimRepository.selectSeqExchFpkey(clmNo);
    }
	
	

	/***************************************************************************************************
	 * Khan
	 * *************************************************************************************************/


	/***************************************************************************************************
	 * Henry
	 * 클레임 삭제 관련 서비스
	 * *************************************************************************************************/

	/**
	 * 인터페이스주문상품ERP분배 삭제
	 * @param clmWrhsGod
	 */
	public void deleteInfOrdGodErpDstb(ClmWrhsGodExtend clmWrhsGod) {
		claimRepository.deleteInfOrdGodErpDstb(clmWrhsGod);
	}
	
	/**
	 * 물류 출고지시상품 이력 삭제
	 * @param lgsDlivyDrctGodHist
	 * @return
	 */
	public void deleteLgsDlivyDrctGodHist(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteLgsDlivyDrctGodHist(clmWrhsGod);
	}

	/**
	 * 물류 출고지시상품 삭제
	 * @param lgsDlivyDrctGod
	 * @return
	 */
	public void deleteLgsDlivyDrctGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteLgsDlivyDrctGod(clmWrhsGod);
	}

	/**
	 * 주문상품적용프로모션 삭제
	 * @param ordGodAplPrm
	 * @return
	 */
	public void deleteOrdGodAplPrm(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteOrdGodAplPrm(clmWrhsGod);
	}

	/**
	 * 교환 수정후 접수 관련 삭제대상상품 조회
	 * @param claimSearchDTO
	 * @param pageParam
	 * @return
	 */
	public List<ClmWrhsGodExtend> selectOrdGodListForClm(ClmWrhsGodExtend clmWrhsGod){
		return claimRepository.selectOrdGodListForClm(clmWrhsGod);
	}
	
	/**
	 * 주문구성상품연결 삭제
	 * @param ordGodAplPrm
	 * @return
	 */
	public void deleteOrdCpstGodCnnc(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteOrdCpstGodCnnc(clmWrhsGod);
	}
	
	/**
	 * 주문 클레임 상품연결 삭제 - 출고 상품 연결
	 * @param ordClmGodCnnc
	 * @return
	 */
	public void deleteOrdClmGodCnncDlivy(OrdClmGodCnnc ordClmGodCnnc) throws Exception {
		claimRepository.deleteOrdClmGodCnncDlivy(ordClmGodCnnc);
	}

	/**
	 * 주문상품 삭제
	 * @param ordGod
	 * @return
	 */
	public void deleteOrdGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteOrdGod(clmWrhsGod);
	}

	/**
	 * 물류 회수지시상품 이력 삭제
	 * @param lgsRtrvlDrctGodHist
	 * @return
	 */
	public void deleteLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) throws Exception {
		claimRepository.deleteLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
	}

	/**
	 * 물류 회수지시상품 삭제
	 * @param lgsRtrvlDrctGod
	 * @return
	 */
	public void deleteLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		claimRepository.deleteLgsRtrvlDrctGod(lgsRtrvlDrctGod);
	}	
	
	/**
	 * 클레임 입고상품 적용 프로모션 삭제
	 * @param clmWrhsGodAplPrm
	 * @return
	 */
	public void deleteClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) throws Exception {
		claimRepository.deleteClmWrhsGodAplPrm(clmWrhsGodAplPrm);
	}
	
	/**
	 * 주문 클레임 상품연결 삭제 - 입고 상품 연결
	 * @param ordClmGodCnnc
	 * @return
	 */
	public void deleteOrdClmGodCnncWrhs(OrdClmGodCnnc ordClmGodCnnc) throws Exception {
		claimRepository.deleteOrdClmGodCnncWrhs(ordClmGodCnnc);
	}
	
	/**
	 * 클레임 입고 구성 상품 연결 삭제
	 * @param clmWrhsGod
	 * @return
	 */
	public void deleteClmWrhsCpstGodCnnc(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteClmWrhsCpstGodCnnc(clmWrhsGod);
	}
	
	/**
	 * 클레임 입고상품 삭제
	 * @param clmWrhsGod
	 * @return
	 */
	public void deleteClmWrhsGod(ClmWrhsGodExtend clmWrhsGod) throws Exception {
		claimRepository.deleteClmWrhsGod(clmWrhsGod);
	}

	/**
	 * 물류 배송 이력 삭제
	 * @param lgsDlvHist
	 * @return
	 */
	public void deleteLgsDlvHist(LgsDlvHist lgsDlvHist) throws Exception {
		claimRepository.deleteLgsDlvHist(lgsDlvHist);
	}

	/**
	 * 물류 배송 삭제
	 * @param lgsDlv
	 * @return
	 */
	public void deleteLgsDlv(LgsDlv lgsDlv) throws Exception {
		claimRepository.deleteLgsDlv(lgsDlv);
	}

	/**
	 * 물류 배송지 이력 삭제
	 * @param lgsDlvspHist
	 * @return
	 */
	public void deleteLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception {
		claimRepository.deleteLgsDlvspHist(lgsDlvspHist);
	}

	/**
	 * 물류 배송지 삭제
	 * @param lgsDlvsp
	 * @return
	 */
	public void deleteLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		claimRepository.deleteLgsDlvsp(lgsDlvsp);
	}
		

	/**
	 * 클레임 ERP 전송 삭제
	 * @param clm
	 * @return
	 */
	public void deleteClmErpTrnsmis(Clm clm) throws Exception {
		claimRepository.deleteClmErpTrnsmis(clm);
	}

	/**
	 * 클레임 마스터 삭제
	 * @param clm
	 * @return
	 */
	public void deleteClm(Clm clm) throws Exception {
		claimRepository.deleteClm(clm);
	}




	/**
	 *
	 * @param clmWrhsGod
	 * @return
	 */
	/*public void updateClmWrhsGod(ClmWrhsGod clmWrhsGod) throws Exception {
		claimRepository.updateClmWrhsGod(clmWrhsGod);
	}*/


	/**
	 *
	 * @param ordGod
	 * @return
	 */
	public void updateOrdGod(OrdGod ordGod) throws Exception {
		claimRepository.updateOrdGod(ordGod);
	}

	/**
	 *
	 * @param ordGodAplPrm
	 * @return
	 */
	public void updateOrdGodAplPrm(OrdGodAplPrm ordGodAplPrm) throws Exception {
		claimRepository.updateOrdGodAplPrm(ordGodAplPrm);
	}

	/**
	 *
	 * @param lgsDlvsp
	 * @return
	 */
	public void updateLgsDlvsp(LgsDlvsp lgsDlvsp) throws Exception {
		claimRepository.updateLgsDlvsp(lgsDlvsp);
	}

	/**
	 *
	 * @param lgsDlv
	 * @return
	 */
	public void updateLgsDlv(LgsDlv lgsDlv) throws Exception {
		claimRepository.updateLgsDlv(lgsDlv);
	}

	
	/**
	 *
	 * @param lgsDlivyDrctGod
	 * @return
	 */
	public void updateLgsDlivyDrctGod(LgsDlivyDrctGod lgsDlivyDrctGod) throws Exception {
		claimRepository.updateLgsDlivyDrctGod(lgsDlivyDrctGod);
	}

	/**
	 *
	 * @param ordClmGodCnnc
	 * @return
	 *//*
	public void updateOrdClmGodCnnc(OrdClmGodCnnc ordClmGodCnnc) throws Exception {
		claimRepository.updateOrdClmGodCnnc(ordClmGodCnnc);
	}*/

	/**
	 *
	 * @param clm
	 * @return
	 */
	public void updateClm(Clm clm) throws Exception {
		claimRepository.updateClm(clm);
	}

	/**
	 *
	 * @param clmWrhsGodAplPrm
	 * @return
	 */
	public void updateClmWrhsGodAplPrm(ClmWrhsGodAplPrm clmWrhsGodAplPrm) throws Exception {
		claimRepository.updateClmWrhsGodAplPrm(clmWrhsGodAplPrm);
	}

	/**
	 *
	 * @param lgsRtrvlDrctGod
	 * @return
	 */
	public void updateLgsRtrvlDrctGod(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		claimRepository.updateLgsRtrvlDrctGod(lgsRtrvlDrctGod);
	}
	
	public void updateOrdGodErpForClmWthdraw(String clmNo) throws Exception {
		claimRepository.updateOrdGodErpForClmWthdraw(clmNo);
	}

	/**
	 *
	 * @param lgsDlvspHist
	 * @return
	 */
	public void insertLgsDlvspHist(LgsDlvspHist lgsDlvspHist) throws Exception {
		claimRepository.insertLgsDlvspHist(lgsDlvspHist);
	}

	/**
	 *
	 * @param lgsDlvHist
	 * @return
	 */
	public void insertLgsDlvHist(LgsDlvHist lgsDlvHist) throws Exception {
		claimRepository.insertLgsDlvHist(lgsDlvHist);
	}

	/**
	 *
	 * @param lgsRtrvlDrctGodHist
	 * @return
	 */
	public void insertLgsRtrvlDrctGodHist(LgsRtrvlDrctGodHist lgsRtrvlDrctGodHist) throws Exception {
		claimRepository.insertLgsRtrvlDrctGodHist(lgsRtrvlDrctGodHist);
	}

	/**
	 *
	 * @param lgsDlivyDrctGodHist
	 * @return
	 */
	public void insertLgsDlivyDrctGodHist(LgsDlivyDrctGodHist lgsDlivyDrctGodHist) throws Exception {
		claimRepository.insertLgsDlivyDrctGodHist(lgsDlivyDrctGodHist);
	}

	public int checkResveOrdDate(Ord ord) {
		return claimRepository.checkResveOrdDate(ord);
    }
	
	public void updateClmSumry(String clmNo) throws Exception {
		claimRepository.updateClmSumry(clmNo);
	}
	
	/**
	 * Update clm sumry for repair.
	 *
	 * @param clmNo the clm no
	 * @throws Exception the exception
	 */
	public void updateClmSumryForRepair(String clmNo) throws Exception {
		claimRepository.updateClmSumryForRepair(clmNo);
	}

	public List<OrdGodAplPrm> selectOrdGodAplPrm(ClaimBoDTO claimBoDTO) {
		return claimRepository.selectOrdGodAplPrm(claimBoDTO);
    }

	public List<ClmDlvOrdGodInfoDTO> selectCnclTgtGodGftPrm(ClmDlvOrdGodInfoDTO dto) {
		return claimRepository.selectCnclTgtGodGftPrm(dto);
	}
	
	public List<ClmWrhsGodExtend> selectRtnTgtGodGftPrm(ClmWrhsGodExtend dto) {
		return claimRepository.selectRtnTgtGodGftPrm(dto);
	}
	
	public List<OrdGod> selectOrdGodByOrdGft(OrdGodAplPrm ordGodAplPrmDto) {
		return claimRepository.selectOrdGodByOrdGft(ordGodAplPrmDto);
    }



	public List<ClmWrhsGod> selectClmWrhsGodForOrdGft(
            ClaimOrdGftSearchDTO claimOrdGftSearchDTO) {
		return claimRepository.selectClmWrhsGodForOrdGft(claimOrdGftSearchDTO);
    }	

    public void updateLgsDlvCst(LgsDlv lgsDlv) {
		claimRepository.updateLgsDlvCst(lgsDlv);
    }
    
    public void insertLgsDlvCstForGlovalCancel(LgsDlv lgsDlv) {
    	claimRepository.insertLgsDlvCstForGlovalCancel(lgsDlv);
    }
    
    /**
     * 주문 상품 수량 합계 조회
     * @param ordNo
     * @return
     */
    public int getTotalOrdQty(String ordNo) {
 		return claimRepository.getTotalOrdQty(ordNo);
    }
   
    /**
     * 귀책사유에 따른 클레임 상태코드 확인
     * @param claimBoDTO
     * @return
     */
    public String getClmStatCdByClmRsn(ClaimBoDTO claimBoDTO) {
    	String str = OrderClaimEnum.ClmStatEnum.REQST.toString();
    	if (hasMcomRsn(claimBoDTO) == false) {
    		str = OrderClaimEnum.ClmStatEnum.RCEPT.toString();
    	}
    	return str;
    }
    
    /**
     * 클레임 사유코드 중 자사 귀책인 건이 있는지 여부 확인
     * @param claimDTO
     * @return
     */
    private boolean hasMcomRsn(ClaimBoDTO claimBoDTO) {
    	for(LgsDlvspExtend dlvsp : claimBoDTO.getLgsDlvspList()){
    		for(ClmWrhsGodExtend clmGod : dlvsp.getClmWrhsGodList()){
    			String rsnCd = clmGod.getClmResnCd();
    			// 전체 사유 중 하나라도 자사귀책사유가 있으면 true
    			try {
	    			if (OrderClaimEnum.ClmRsnEnum.valueOf(rsnCd).isMcomRsn()) {
	    				return true;
	    			}
    			} catch (IllegalArgumentException e) {	// 만약 이상한 값이 들어오면 그냥 스킵처리 하도록 한다.
    				continue;
    			}
    		}
    	}
    	
    	return false;
    }
    
    
	public void updateOvseaWaybilNoPop(LgsDlv lgsDlv) {
		claimRepository.updateOvseaWaybilNoPop(lgsDlv);
	}
	
	public List<OrdGod> selectOrdGodListForAllCancel(String ordNo) {
		return claimRepository.selectOrdGodListForAllCancel(ordNo);
	}
	
	/**
	 * 클레임 입고 관련 데이터 생성 (전체취소, 부분취소)
	 * 1. clm_wrhs_god 데이터 생성
	 * 2. ord_clm_god_cnnc 데이터 생성
	 * 
	 * @param ordNo : 주문번호
	 * @param clmNo : 클레임번호
	 * @param ordGodTurn : 주문상품순번
	 * @param clmQty : 클레임수량
	 * @param clmResnCd : 클레임사유
	 * @param clmResnCont : 클레임사유설명
	 * @throws Exception
	 */
	public void createClmWrhsGodForCancelClaim (String ordNo,String clmNo, int ordGodTurn, Long clmQty, String clmResnCd, String clmResnCont ) throws Exception{
		
		/**
		 * 주문 상품 조회
		 */
		OrdGod clmOrdGod = new OrdGod();
		
		clmOrdGod.setOrdNo(ordNo);
		clmOrdGod.setOrdGodTurn(ordGodTurn);
		clmOrdGod = ordGodRepository.selectOrdGod(clmOrdGod);

		/**
		 * 클레임입고데이터 수집
		 */
		ClmWrhsGodExtend clmWrhsGodExtend = new ClmWrhsGodExtend();
		
		clmWrhsGodExtend.setOrdNo(ordNo);
		clmWrhsGodExtend.setOrdGodTurn(ordGodTurn);
		clmWrhsGodExtend.setClmNo(clmNo);
		clmWrhsGodExtend.setClmQty(clmQty);
		clmWrhsGodExtend.setGodNo(clmOrdGod.getGodNo());
		clmWrhsGodExtend.setItmNo(clmOrdGod.getItmNo());
		clmWrhsGodExtend.setDlvPcupspTurn(clmOrdGod.getDlvPcupspTurn());
		if(clmResnCd!=null && !"".equals(clmResnCd)){
			clmWrhsGodExtend.setClmResnCd(clmResnCd);
		}else{
			clmWrhsGodExtend.setClmResnCd("CSTMR_CHGMIND_CNCL");
		}
		clmWrhsGodExtend.setClmResnCont(clmResnCont);
		clmWrhsGodExtend.setRepairYn("N");
		
		this.insertClmWrhsGodForRtrvl(clmWrhsGodExtend);//insert
		
		/**
		 * 주문클레임 상품연결 테이블insert
		 (되어있음)
		 */
		//주문 클레임 상품 연결 테이블에 등록
		clmWrhsGodExtend.setGodCnncTpCd("WRHS_GOD_CNNC");//연결테이블 유형은 입고 (전체취소일경우, 부분취소도 포함)
		this.insertOrdClmGodCnnc(clmWrhsGodExtend);
	}
	
	public int selectClmWrhsGodCountForTempDel(String clmNo) {
		return claimRepository.selectClmWrhsGodCountForTempDel(clmNo);
	}
	
	public int selectRepairClmWrhsGodCount(ClmWrhsGodExtend clmWrhsGodExtend) {
		return claimRepository.selectRepairClmWrhsGodCount(clmWrhsGodExtend);
	}

	public int selectClmWrhsGodCount(String clmNo) {
		return claimRepository.selectClmWrhsGodCount(clmNo);
	}

	public List<ComChrger> selectClmComChrger(Clm clm) {
		return claimRepository.selectClmComChrger(clm);
	}


	/**
	 * #34162 [개발]글로벌 배송정책 변경으로 인한 클레임 처리시 주문상태 반영 기능 개선 요청의 건
	 * 2016-01-24 글로벌 배송파트에서 분리배송 적용관련
	 * 클레임에서도 클레임이 완료되는 시점에 잔여 상품이 모두 배송 완료인지 확인하는 쿼리
	 */
	public boolean selectOrdStatDlvComptYn(String ordNo) {
		return claimRepository.selectOrdStatDlvComptYn(ordNo);
	}

	public String selectBaseDlvComCd() {
		return claimRepository.selectBaseDlvComCd("DXM");
	}
	
	public String selectBaseDlvComCd(String mallId) {
		return claimRepository.selectBaseDlvComCd(mallId);
	}

	public Map<String, String> selectBaseDlvCom(LgsDlv dlv) {
		return claimRepository.selectBaseDlvCom(dlv);
	}

	public void clmErpTrnsmisListData(String ordNo, String clmNo, String resultCd, String clmErpTrnsmisTpCd, String orderType) {


		/*		   >. 사용포인트 임시삭감 : UNITY_PNT_USE_TMPR
		   >. 적립포인트 임시삭감 : UNITY_PNT_ACCML_TMPR
		   >. 구매 이력 증가 전송 : ERP_OR_ERP
		   >. 구매 이력 감소 전송 : ERP_RE_ERP
		   >. 리테일 전송 : RTL_TRNSMIS
		   >. 통합 포인트 사용 확정 : UNITY_PNT_USE_DCSN
		   >. 통합 포인트 적립 확정 : UNITY_PNT_ACCML_DCSN';
*/


		String erpTrnsmisCd = "";

		if("UNITY_PNT_USE_TMPR".equals(clmErpTrnsmisTpCd)
				|| "UNITY_PNT_ACCML_TMPR".equals(clmErpTrnsmisTpCd)
				|| "ONOFLNE_CPN_USE_TMPR".equals(clmErpTrnsmisTpCd)){
/*			Y: RE 성공
			RH: RE HUB 전송
			F: RE 실패.
			R: OR 성공
			OH: OR 허브 전송
			E: OR 실패.
			X: 해당없음.
			N: 대상이지만 미전송*/
			if("OR".equals(orderType)){ //OR
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					erpTrnsmisCd = "R";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpTrnsmisCd = "OH";
				}
			}else{ //RE
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					erpTrnsmisCd = "Y";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpTrnsmisCd = "RH";
				}
			}

		}else{
/*			N: 대상이지만 미전송.
			H: HUB 전송
			X: 해당없음
			E: 전송실패.
			Y: 전송 성공*/
			if("Y".equals(resultCd)){//hub 성공 erp 성공
				erpTrnsmisCd = "Y";
			}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
				erpTrnsmisCd = "H";
			}
		}



		ClmErpTrnsmis totalCancelClmErpTrnsmis = new ClmErpTrnsmis();
		totalCancelClmErpTrnsmis.setClmNo(clmNo);
		totalCancelClmErpTrnsmis.setOrdNo(ordNo);
		totalCancelClmErpTrnsmis.setClmErpTrnsmisTpCd(clmErpTrnsmisTpCd);
		totalCancelClmErpTrnsmis.setErpTrnsmisCd(erpTrnsmisCd);
		//claimBatchComponent.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);
		claimBatchRepository.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);

	}


	public void clmErpTrnsmisListDataBatch(String ordNo, String clmNo, String resultCd, String clmErpTrnsmisTpCd, String orderType) {

		//클레임 배치에서는 orderClaimErpHubAdapter.sendOnlineSaleToHub() 호출시 direct false 로 셋팅하기때문에 성공코드가 와도 H 로 저장한다.

		/*		   >. 사용포인트 임시삭감 : UNITY_PNT_USE_TMPR
		   >. 적립포인트 임시삭감 : UNITY_PNT_ACCML_TMPR
		   >. 구매 이력 증가 전송 : ERP_OR_ERP
		   >. 구매 이력 감소 전송 : ERP_RE_ERP
		   >. 리테일 전송 : RTL_TRNSMIS
		   >. 통합 포인트 사용 확정 : UNITY_PNT_USE_DCSN
		   >. 통합 포인트 적립 확정 : UNITY_PNT_ACCML_DCSN';
*/


		String erpTrnsmisCd = "";

		if("UNITY_PNT_USE_TMPR".equals(clmErpTrnsmisTpCd)
				|| "UNITY_PNT_ACCML_TMPR".equals(clmErpTrnsmisTpCd)
				|| "ONOFLNE_CPN_USE_TMPR".equals(clmErpTrnsmisTpCd)){
/*			Y: RE 성공
			RH: RE HUB 전송
			F: RE 실패.
			R: OR 성공
			OH: OR 허브 전송
			E: OR 실패.
			X: 해당없음.
			N: 대상이지만 미전송*/
			if("OR".equals(orderType)){ //OR
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					//erpTrnsmisCd = "R";
					erpTrnsmisCd = "OH";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpTrnsmisCd = "OH";
				}
			}else{ //RE
				if("Y".equals(resultCd)){//hub 성공 erp 성공
					//erpTrnsmisCd = "Y";
					erpTrnsmisCd = "RH";
				}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
					erpTrnsmisCd = "RH";
				}
			}

		}else{
/*			N: 대상이지만 미전송.
			H: HUB 전송
			X: 해당없음
			E: 전송실패.
			Y: 전송 성공*/
			if("Y".equals(resultCd)){//hub 성공 erp 성공
				//erpTrnsmisCd = "Y";
				erpTrnsmisCd = "H";
			}else if("N".equals(resultCd)){//hub 성공 erp 실패나 미전송
				erpTrnsmisCd = "H";
			}
		}



		ClmErpTrnsmis totalCancelClmErpTrnsmis = new ClmErpTrnsmis();
		totalCancelClmErpTrnsmis.setClmNo(clmNo);
		totalCancelClmErpTrnsmis.setOrdNo(ordNo);
		totalCancelClmErpTrnsmis.setClmErpTrnsmisTpCd(clmErpTrnsmisTpCd);
		totalCancelClmErpTrnsmis.setErpTrnsmisCd(erpTrnsmisCd);
		//claimBatchComponent.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);
		claimBatchRepository.updateClmErpTrnsmis(totalCancelClmErpTrnsmis);

	}


	public ClmErpTrnsmis selectclmErpTrnsmis(ClmErpTrnsmis cst) {
		return claimRepository.selectclmErpTrnsmis(cst);
	}

	/**
	 * 무료수선목록
	 *
	 * @param searchDTO the search dto
	 * @param pageParam the page param
	 * @return the list
	 * @throws Exception the exception
	 */
	public List<ClaimListResult> selectRepairRegList(ClaimListSearchDTO searchDTO, PageParam pageParam) throws Exception {
		return claimRepository.selectRepairRegList(searchDTO, pageParam);
	}


	public RepairResult selectRepairGod(RepairDTO repairDTO) throws Exception {
		return claimRepository.selectRepairGod(repairDTO);
	}

	public int updateRepairClm(Clm clm) throws Exception {
		return claimRepository.updateRepairClm(clm);
	}

	public int updateInfOrdGodErpDstb(InfOrdGodErpDstb infOrdGodErpDstb) throws Exception {
		return claimRepository.updateInfOrdGodErpDstb(infOrdGodErpDstb);
	}

	public RepairResult selectRepairCnt(RepairDTO repairDTO) throws Exception {
		return claimRepository.selectRepairCnt(repairDTO);
	}

	public int updateRepairLgsRdg(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return claimRepository.updateRepairLgsRdg(lgsRtrvlDrctGod);
	}

	public int insertRepairLgsRdgHist(LgsRtrvlDrctGod lgsRtrvlDrctGod) throws Exception {
		return claimRepository.insertRepairLgsRdgHist(lgsRtrvlDrctGod);
	}

	public int updateRepairLgsDdgInfo4DivWayBil(DeliveryOrderGoodDTO deliveryOrderGoodDTO) throws Exception {
		return claimRepository.updateRepairLgsDdgInfo4DivWayBil(deliveryOrderGoodDTO);
	}

	public int updateInfOrdGodErpRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) throws Exception {
		return claimRepository.updateInfOrdGodErpRepair(infOrdGodErpDstbRepair);
	}



	public int selectRcptnoNullCntByClmNo(String clmNo) {
		return claimRepository.selectRcptnoNullCntByClmNo(clmNo);
	}

	public long selectClmByMbr(ClaimBoDTO claimBoDTO) {
		return claimRepository.selectClmByMbr(claimBoDTO);
	}

	/**
	 * 수선 정보 보정 처리
	 * @param repairResult
	 * @param udterId
	 * @throws Exception
	 */
	private void repairDataCorrection(RepairResult repairResult, String udterId) throws Exception {
		RepairDTO searchDTO = new RepairDTO();
		searchDTO.setClmNo(repairResult.getClmNo());
		searchDTO.setQtyTurn(repairResult.getQtyTurn());
		searchDTO.setClmWrhsGodTurn(repairResult.getClmWrhsGodTurn());

		if (!"Y".equals(repairResult.getWrhsAcptYn())) {
			// 접수 진행된 수선 상품에 대해 입고 검수 여부가 N 일 경우 UPDATE
			InfOrdGodErpDstb infOrdGodErpDstb = new InfOrdGodErpDstb();
			infOrdGodErpDstb.setClmNo(repairResult.getClmNo());
			infOrdGodErpDstb.setClmWrhsGodTurn(Integer.parseInt(repairResult.getClmWrhsGodTurn()));
			infOrdGodErpDstb.setQtyTurn(Integer.parseInt(repairResult.getQtyTurn()));
			infOrdGodErpDstb.setWrhsAcptYn("Y");
			infOrdGodErpDstb.setUdterId(udterId);

			claimRepository.updateInfOrdGodErpDstb(infOrdGodErpDstb);
		}

		// 수선 입고 / 완료 갯수 조회
		RepairResult repairCnt = claimRepository.selectRepairCnt(searchDTO);
		if (repairCnt != null && Integer.compare(repairCnt.getWrhsAcptCnt(), repairCnt.getWrhsAcptComptCnt()) == 0) {
			// 전체 수선 상품이 입고완료 될 경우 회수 상태 완료로 변경
			LgsRtrvlDrctGod lgsRtrvlDrctGod = new LgsRtrvlDrctGod();
			lgsRtrvlDrctGod.setRtrvlDrctGodNo(repairResult.getRtrvlDrctGodNo());
			lgsRtrvlDrctGod.setRtrvlStatCd("RTRVL_COMPT");
			lgsRtrvlDrctGod.setUdterId(udterId);
			claimRepository.updateRepairLgsRdg(lgsRtrvlDrctGod);
			claimRepository.insertRepairLgsRdgHist(lgsRtrvlDrctGod);
		}

		if (repairCnt != null && Integer.compare(repairCnt.getRepairTotCnt(), repairCnt.getRepairComptCnt()) == 0) {
			// 전체 수선 상품이 완료 될 경우 클레임 상태 완료로 변경
			Clm clm = new Clm();
			clm.setClmNo(repairResult.getClmNo());
			clm.setUdterId(udterId);

			clm.setClmStatCd("COMPT");
			claimRepository.updateRepairClm(clm);
		}

		DeliveryOrderGoodDTO deliveryOrderGoodDTO = new DeliveryOrderGoodDTO();
		deliveryOrderGoodDTO.setOrdNo(repairResult.getOrdNo());
		deliveryOrderGoodDTO.setUdterId(udterId);
		// 물류출고지시상품' 데이터 전체가 '배송완료', '출고지시취소' 인 경우에 주문상태 업데이트
		deliveryCommandRepository.modifyOrdDlvCompt(deliveryOrderGoodDTO);
	}

	public InfOrdGodErpDstbRepair selectInfOrdGodErpDstbRepair(InfOrdGodErpDstbRepair infOrdGodErpDstbRepair) {
		return claimRepository.selectInfOrdGodErpDstbRepair(infOrdGodErpDstbRepair);
	}

	
	//#46104 배포로 무료반품쿠폰 요건 관련 소스 주석처리 2017-06-22일 운영 반영 후 주석제거예정
	public void updateMbrIssuCoupon(ClmWrhsGodAplPrm clmWrhsGodAplPrm) {
		claimRepository.updateMbrIssuCoupon(clmWrhsGodAplPrm);
	}
	
	
	public ClmGoodsCouponResult selectClmCouponInfo(ClmCouponSearchDTO clmCouponSearchDTO) {
		return claimRepository.selectClmCouponInfo(clmCouponSearchDTO);
	}
	
	public long selectUsedClaimCouponGodCnt(ClmCouponSearchDTO clmCouponSearchDTO) {
		return claimRepository.selectUsedClaimCouponGodCnt(clmCouponSearchDTO);
	}
	
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn) throws Exception {

        //클레임 step - 쿠폰상태값 : 사용중지
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.USE_STPGE.toString());
        claimRepository.updateCouponRevertStatus(mbrIssuCpn);

        //쿠폰 복원 step - 쿠폰상태값 : 미사용
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.NO_USE.toString());
        mbrIssuCpn.setClmNo(null);
        mbrIssuCpn.setCpnUseDt(null);
        claimRepository.updateCouponRevertStatus(mbrIssuCpn);
        
    }
    
    /**
     * 세트구성상품 교환시 세트대표상품 여부 조회
     *
     * @param Map<String, Object> extOrdGod
     * @throws Exception
     */
    public List<OrdGod> selectOrdCpstGodCnncList(Map<String, Object> extOrdGod ) throws Exception{
		return claimRepository.selectOrdCpstGodCnncList(extOrdGod);
	}
    
    /**
     * 세트구성상품 교환시 출고지시 테이블의 세트대표상품 pckage_god_turn 업데이트
     *
     * @param Map<String, Object> extDlivyDrctGod
     * @throws Exception
     */
    public void updateSetPckageGodTurn(Map<String, Object> extDlivyDrctGod) throws Exception{
		claimRepository.updateSetPckageGodTurn(extDlivyDrctGod);
	}
    
    /** [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청 
     *  집하상태코드목록 - 클레임지연조회
     */
	public List<SysExcpCd> selectSysExcpCdList(SysExcpCd sysExcpCd) throws Exception {
		return claimRepository.selectSysExcpCdList(sysExcpCd);
	}
	
	/**
	 * [#48750][개발] 불량상품 고객에 대한 교환/반품 Process 개선 요청
	 * 선진행 구분코드 update
	 */
	public void updateClmPreprogrsSectCd(ClaimBoDTO claimBoDTO) throws Exception  {
		claimRepository.updateClmPreprogrsSectCd(claimBoDTO);
    }
	
	/**
	 * [#53196][온라인고객센터]반품완료 후 생일쿠폰 미복원 오류
	 * 온오프라인 미사용 상태로 보정하는 메소드.
	 */
    public void updateCouponCorrectStatus(MbrIssuCpn mbrIssuCpn) throws Exception {
    	//쿠폰 복원 step - 쿠폰상태값 : 미사용
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.NO_USE.toString());
        claimRepository.updateCouponCorrectStatus(mbrIssuCpn);
    }
    
    /**
     * 클레임 환불오류 건 PG사 전송 대상 여부 N 변경
     * 
     * @return
     * @throws Exception
     */
    public int updateClmRfdErrPgTrnsmisTgtYn(ClaimBoDTO claimBoDTO) throws Exception {    	
    	return claimRepository.updateClmRfdErrPgTrnsmisTgtYn(claimBoDTO);    	
    }
}
