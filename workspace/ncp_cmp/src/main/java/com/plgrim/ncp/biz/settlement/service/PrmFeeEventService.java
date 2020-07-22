package com.plgrim.ncp.biz.settlement.service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEvent;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventAplGod;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventBrnd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventCom;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmFeeEventExclsGod;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventBasicFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventExcelDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventFormDTO;
import com.plgrim.ncp.biz.settlement.data.PrmFeeEventSearchDTO;
import com.plgrim.ncp.biz.settlement.exception.AditFeeRtNotExistException;
import com.plgrim.ncp.biz.settlement.exception.EventAplTgtCdNotExistException;
import com.plgrim.ncp.biz.settlement.exception.EventBegEndNotExistException;
import com.plgrim.ncp.biz.settlement.exception.FeeEventNmNotExistException;
import com.plgrim.ncp.biz.settlement.exception.FeeEventTpCdNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventAffVrscComNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventAplGodNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventBrndNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventComNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventExclsGodNotExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventFeeEventSnExistException;
import com.plgrim.ncp.biz.settlement.exception.PrmFeeEventNotExistException;
import com.plgrim.ncp.biz.settlement.exception.UseYnNotExistException;
import com.plgrim.ncp.biz.settlement.repository.SettlementPrmFeeEventRepository;
import com.plgrim.ncp.biz.settlement.result.PrmFeeEventListResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;

@Service
public class PrmFeeEventService extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;
	
	@Autowired
	SettlementPrmFeeEventRepository settlementPrmFeeEventRepository;

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * 수수료행사관리 리스트
	 * @param settlementSearchDTO
	 * @return
	 * @throws Exception
	 */
	public Page<PrmFeeEventListResult> selectPrmFeeEventList(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(prmFeeEventSearchDTO, prmFeeEventSearchDTO.getPageParam());  // 페이지 설정
	      
		// 목록 건수 조회
		long totalRow = settlementPrmFeeEventRepository.selectPrmFeeEventListCount(prmFeeEventSearchDTO);
		  
		// 목록 조회
		List<PrmFeeEventListResult> results = new ArrayList<PrmFeeEventListResult>();
		if(totalRow > 0){
			results = settlementPrmFeeEventRepository.selectPrmFeeEventList(prmFeeEventSearchDTO);
		}
		  
		return new PageImpl<PrmFeeEventListResult>(results, prmFeeEventSearchDTO.getPageParam().getPageable(), totalRow);
	}
	
	/**
	 * 수수료행사관리 리스트 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPrmFeeEventListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return settlementPrmFeeEventRepository.selectPrmFeeEventListExcel(prmFeeEventSearchDTO);
	}
	
	/**
	 * 수수료행사관리 상세리스트 엑셀
	 * @param prmFeeEventSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> selectPrmFeeEventDetailListExcel(PrmFeeEventSearchDTO prmFeeEventSearchDTO) throws Exception {
		return settlementPrmFeeEventRepository.selectPrmFeeEventDetailListExcel(prmFeeEventSearchDTO);
	}

	/**
     * 수수료행사 정보 저장
     * @param prmFeeEventFormDTO
     * @throws Exception
     */
    public void insertPrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();
    	
    	PrmFeeEvent prmFeeEvent = new PrmFeeEvent();
    	prmFeeEvent.setFeeEventTpCd(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getFeeEventTpCd());
    	prmFeeEvent.setFeeEventNm(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getFeeEventNm());
    	prmFeeEvent.setAditFeeRt(new BigDecimal(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getAditFeeRt()));
    	prmFeeEvent.setUseYn(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getUseYn());
    	prmFeeEvent.setEventAplTgtCd(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getEventAplTgtCd());
    	prmFeeEvent.setRegtrId(loginId);
    	prmFeeEvent.setUdterId(loginId);

    	prmFeeEventFormDTO.setPrmFeeEvent(prmFeeEvent);    	
    	settlementPrmFeeEventRepository.insertPrmFeeEvent(prmFeeEventFormDTO);//마스터
    	
    	//수수료행사 업체 저장
    	if(prmFeeEventFormDTO.getComList() != null && prmFeeEventFormDTO.getComList().size() > 0){
    		for(int i=0; i<prmFeeEventFormDTO.getComList().size(); i++){
    			PrmFeeEventCom prmFeeEventCom = new PrmFeeEventCom();
    	    	prmFeeEventCom.setComId(((Com)prmFeeEventFormDTO.getComList().get(i)).getComId());
    	    	prmFeeEventCom.setUseYn("Y");
    	    	prmFeeEventCom.setRegtrId(loginId);
    	    	prmFeeEventCom.setUdterId(loginId);
    	    	prmFeeEventCom.setAffVrscComId(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getAffAgncCom());
    	    	prmFeeEventFormDTO.setPrmFeeEventCom(prmFeeEventCom);
    	    	settlementPrmFeeEventRepository.insertPrmFeeEventCom(prmFeeEventFormDTO);
    		}
    	}
    	
    	//수수료행사 브랜드 저장
    	if(prmFeeEventFormDTO.getBrandList() != null && prmFeeEventFormDTO.getBrandList().size() > 0){
    		for(int i=0; i<prmFeeEventFormDTO.getBrandList().size(); i++){
    			PrmFeeEventBrnd prmFeeEventBrnd = new PrmFeeEventBrnd();
    			prmFeeEventBrnd.setBrndId(prmFeeEventFormDTO.getBrandList().get(i));
    			prmFeeEventBrnd.setUseYn("Y");
    			prmFeeEventBrnd.setRegtrId(loginId);
    			prmFeeEventBrnd.setUdterId(loginId);
    			prmFeeEventFormDTO.setPrmFeeEventBrnd(prmFeeEventBrnd);    			
    	    	settlementPrmFeeEventRepository.insertPrmFeeEventBrnd(prmFeeEventFormDTO);
    		}
    	}
    	
    	//수수료행사 상품 저장
    	if(prmFeeEventFormDTO.getAplGodList() != null && prmFeeEventFormDTO.getAplGodList().size() > 0){
    		for(int i=0; i<prmFeeEventFormDTO.getAplGodList().size(); i++){
    			PrmFeeEventAplGod prmFeeEventAplGod = new PrmFeeEventAplGod();
    			prmFeeEventAplGod.setAplGodNo(((God)prmFeeEventFormDTO.getAplGodList().get(i)).getGodNo());
    	    	prmFeeEventAplGod.setUseYn("Y");
    	    	prmFeeEventAplGod.setRegtrId(loginId);
    	    	prmFeeEventAplGod.setUdterId(loginId);
    	    	prmFeeEventFormDTO.setPrmFeeEventAplGod(prmFeeEventAplGod);
    	    	settlementPrmFeeEventRepository.insertPrmFeeEventAplGod(prmFeeEventFormDTO);
    		}
    	}
    	
    	//수수료행사 예외상품 저장
    	if(prmFeeEventFormDTO.getExclsGodList() != null && prmFeeEventFormDTO.getExclsGodList().size() > 0){
    		for(int i=0; i<prmFeeEventFormDTO.getExclsGodList().size(); i++){
    			PrmFeeEventExclsGod prmFeeEventExclsGod = new PrmFeeEventExclsGod();
    			prmFeeEventExclsGod.setExclsGodNo(((God)prmFeeEventFormDTO.getExclsGodList().get(i)).getGodNo());
    	    	prmFeeEventExclsGod.setUseYn("Y");
    	    	prmFeeEventExclsGod.setRegtrId(loginId);
    	    	prmFeeEventExclsGod.setUdterId(loginId);
    	    	prmFeeEventFormDTO.setPrmFeeEventExclsGod(prmFeeEventExclsGod);
    	    	settlementPrmFeeEventRepository.insertPrmFeeEventExclsGod(prmFeeEventFormDTO);
    		}
    	}
    }
    
    /**
     * 수수료행사 저장
     *
     * @param PrmFeeEventBasicFormDTO
     * @throws Exception the exception
     */
    public void updatePrmFeeEventList(PrmFeeEventBasicFormDTO prmFeeEventBasicFormDTO)throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();

    	prmFeeEventBasicFormDTO.setUdterId(loginId);
    	settlementPrmFeeEventRepository.updatePrmFeeEventList(prmFeeEventBasicFormDTO);
    	
    }
    
    /**
     * 수수료행사 상세팝업 저장
     *
     * @param prmFeeEventFormDTO the prm fee event form dto
     * @throws Exception the exception
     */
    public void updatePrmFeeEvent(PrmFeeEventFormDTO prmFeeEventFormDTO)throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();
    	
    	prmFeeEventFormDTO.setUdterId(loginId);
    	settlementPrmFeeEventRepository.updatePrmFeeEvent(prmFeeEventFormDTO);
    	
    	//수수료행사 상품 삭제
    	if(prmFeeEventFormDTO.getAplGodList() != null && prmFeeEventFormDTO.getAplGodList().size() > 0){
    		settlementPrmFeeEventRepository.deletePrmFeeEventAplGod(prmFeeEventFormDTO);
    	}
    	
    	//수수료행사 예외상품 삭제, 저장
    	if(prmFeeEventFormDTO.getExclsGodList() != null && prmFeeEventFormDTO.getExclsGodList().size() > 0){
    		settlementPrmFeeEventRepository.deletePrmfeeEventExclsGod(prmFeeEventFormDTO);
    		
    		for(int i=0; i<prmFeeEventFormDTO.getExclsGodList().size(); i++){
    			PrmFeeEventExclsGod prmFeeEventExclsGod = new PrmFeeEventExclsGod();
    			prmFeeEventExclsGod.setFeeEventSn(Long.parseLong(prmFeeEventFormDTO.getPrmFeeEventBasicFormDTO().getFeeEventSn()));
    			prmFeeEventExclsGod.setExclsGodNo(((God)prmFeeEventFormDTO.getExclsGodList().get(i)).getGodNo());
    			prmFeeEventExclsGod.setUseYn("Y");
    			prmFeeEventExclsGod.setRegtrId(loginId);
    			settlementPrmFeeEventRepository.mergePrmFeeEventExclsGod(prmFeeEventExclsGod);
    		}
    	}
    	
    }
    
    public void insertExcelPrmFeeEvent(PrmFeeEventExcelDTO prmFeeEventExcelDTO, String loginId) throws Exception {
    	String feeEventTpCd = prmFeeEventExcelDTO.getFeeEventTpCd();
    	String feeEventNm = prmFeeEventExcelDTO.getFeeEventNm();
    	String aditFeeRt = prmFeeEventExcelDTO.getAditFeeRt();
    	String useYn = prmFeeEventExcelDTO.getUseYn();
    	String eventAplTgtCd = prmFeeEventExcelDTO.getEventAplTgtCd();
    	
    	if (feeEventTpCd != null && feeEventTpCd != ""){
    	}else 
    		throw new FeeEventTpCdNotExistException(null);		// 업체구분을 입력해주세요.

    	if(feeEventNm != null && feeEventNm != "")	{
    	}else 
    		throw new FeeEventNmNotExistException(null);		// 행사명을 입력해주세요.

    	if(aditFeeRt != null && aditFeeRt != ""){
    	}else 
    		throw new AditFeeRtNotExistException(null);		// 추가 수수료율을 입력해주세요. 

    	if(useYn != null && useYn != ""){
    	}else 
    		throw new UseYnNotExistException(null);		// 사용여부를 입력해주세요.

		if(eventAplTgtCd != null && eventAplTgtCd != ""){
			eventAplTgtCd = eventAplTgtCd.toUpperCase();
			if("BRND".equals(eventAplTgtCd) || "GOD".equals(eventAplTgtCd) ){
			} else {
	    		throw new EventAplTgtCdNotExistException(null);		// 행사대상을 입력해주세요.
			}
    	}else 
    		throw new EventAplTgtCdNotExistException(null);		// 행사대상을 입력해주세요.
    	
    	PrmFeeEvent prmFeeEvent = new PrmFeeEvent();
    	prmFeeEvent.setFeeEventTpCd(feeEventTpCd);
    	prmFeeEvent.setFeeEventNm(feeEventNm);
    	prmFeeEvent.setAditFeeRt(new BigDecimal(aditFeeRt));
    	prmFeeEvent.setUseYn(useYn);
    	prmFeeEvent.setEventAplTgtCd(eventAplTgtCd);
    	prmFeeEvent.setRegtrId(loginId);
    	prmFeeEvent.setUdterId(loginId);
    	
    	PrmFeeEventFormDTO prmFeeEventFormDTO = new PrmFeeEventFormDTO();
    	
    	if(prmFeeEventExcelDTO.getEventBegDt() != null /*&& String. prmFeeEventExcelDTO.getEventBegDt() */){
    		if(prmFeeEventExcelDTO.getEventEndDt() != null /*&& prmFeeEventExcelDTO.getEventEndDt() != ""*/){
    			DateFormat sdFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    			String begDt = sdFormat.format(prmFeeEventExcelDTO.getEventBegDt());
    			String endDt = sdFormat.format(prmFeeEventExcelDTO.getEventEndDt());
    			
    			PrmFeeEventBasicFormDTO prmFeeEventBasicFormDTO = new PrmFeeEventBasicFormDTO();
    			prmFeeEventBasicFormDTO.setEventBegDt(begDt);
    			prmFeeEventBasicFormDTO.setEventEndDt(endDt);
    			
    			prmFeeEventFormDTO.setPrmFeeEventBasicFormDTO(prmFeeEventBasicFormDTO);
    			prmFeeEventFormDTO.setPrmFeeEvent(prmFeeEvent);    	
    		} else {
    			throw new EventBegEndNotExistException(null);
    		}
    	} else {
			throw new EventBegEndNotExistException(null);
		}
    	
    	//마스터
    	int prmFeeEventCnt = settlementPrmFeeEventRepository.insertPrmFeeEvent(prmFeeEventFormDTO);
    	if(prmFeeEventCnt > 0){
    	} else {
    		throw new PrmFeeEventNotExistException(null);
    	}
    	
    	if(prmFeeEventExcelDTO.getComId() != null && prmFeeEventExcelDTO.getComId() != ""){
    		prmFeeEventExcelDTO.setComId(prmFeeEventExcelDTO.getComId().replace(" ", ""));
    		String[] comIdList = prmFeeEventExcelDTO.getComId().split(",");
    		
    		//수수료행사 업체 저장
    		if(comIdList != null && comIdList.length > 0){
    			for(int i=0 ; i < comIdList.length ; i++){
	    			PrmFeeEventCom prmFeeEventCom = new PrmFeeEventCom();
	    			String affVrscComId = prmFeeEventExcelDTO.getAffVrscComId();
	    			if(affVrscComId != null && affVrscComId != ""){
	    				prmFeeEventCom.setAffVrscComId(affVrscComId);
	    			} else {
	    				throw new PrmFeeEventAffVrscComNotExistException(null);
	    			}
	    	    	prmFeeEventCom.setComId(comIdList[i]);
	    	    	prmFeeEventCom.setUseYn(prmFeeEventExcelDTO.getUseYn());
	    	    	prmFeeEventCom.setRegtrId(loginId);
	    	    	prmFeeEventCom.setUdterId(loginId);
	    	    	prmFeeEventFormDTO.setPrmFeeEventCom(prmFeeEventCom);
	    	    	
	    	    	int selectPrmFeeEventComCnt = settlementPrmFeeEventRepository.selectPrmFeeEventCom(prmFeeEventCom);
	    	    	if (selectPrmFeeEventComCnt > 0){
	    	    		int insertPrmFeeEventComCnt = settlementPrmFeeEventRepository.insertPrmFeeEventCom(prmFeeEventFormDTO);
	    	    		if (insertPrmFeeEventComCnt > 0){
	    	    		} else {
	    	    			// 업체 ID를 확인 해 주세요.
		    	    		throw new PrmFeeEventComNotExistException(null);
	    	    		}
	    	    	} else {
	    	    		// 업체 ID를 확인 해 주세요.
	    	    		throw new PrmFeeEventComNotExistException(null);
	    	    	}
    			}
    		} else {
        		// 업체 ID를 확인 해 주세요.
        		throw new PrmFeeEventComNotExistException(null);
    		}
    	} else {
    		// 업체 ID를 확인 해 주세요.
    		throw new PrmFeeEventComNotExistException(null);
    	}
    	
    	if(prmFeeEventExcelDTO.getEventAplTgtCd() != null && prmFeeEventExcelDTO.getEventAplTgtCd() != ""){
    		if("BRND".equals(prmFeeEventExcelDTO.getEventAplTgtCd().toUpperCase())){
    			//수수료행사 브랜드 저장
    			if(prmFeeEventExcelDTO.getBrndId() != null && prmFeeEventExcelDTO.getBrndId() != ""){
    				prmFeeEventExcelDTO.setBrndId(prmFeeEventExcelDTO.getBrndId().replace(" ", ""));
    				String[] brndIdList = prmFeeEventExcelDTO.getBrndId().split(",");
    				if(brndIdList != null && brndIdList.length > 0){
    					for(int i=0 ; i < brndIdList.length ; i++){
    						PrmFeeEventBrnd prmFeeEventBrnd = new PrmFeeEventBrnd();
    						prmFeeEventBrnd.setBrndId(brndIdList[i]);
    						prmFeeEventBrnd.setUseYn(prmFeeEventExcelDTO.getUseYn());
    						prmFeeEventBrnd.setRegtrId(loginId);
    						prmFeeEventBrnd.setUdterId(loginId);
    						prmFeeEventFormDTO.setPrmFeeEventBrnd(prmFeeEventBrnd); 
    						
    						int selectPrmFeeEventBrndCnt = settlementPrmFeeEventRepository.selectPrmFeeEventBrnd(prmFeeEventBrnd);
    		    	    	if (selectPrmFeeEventBrndCnt > 0){
    		    	    		int insertPrmFeeEventBrndCnt = settlementPrmFeeEventRepository.insertPrmFeeEventBrnd(prmFeeEventFormDTO);
    		    	    		if (insertPrmFeeEventBrndCnt > 0){
    		    	    		} else {
    		    	    			// 브랜드 코드를 확인해주세요.
    		    	    			throw new PrmFeeEventBrndNotExistException(null);
    		    	    		}
    		    	    	} else {
		    	    			// 브랜드 코드를 확인해주세요.
    		    	    		throw new PrmFeeEventBrndNotExistException(null);
    		    	    	}
    					}
    				} else {
    					// 브랜드 코드를 확인해주세요.
        	    		throw new PrmFeeEventBrndNotExistException(null);
    				}
    			} else {
    				// 브랜드 코드를 확인해주세요.
    	    		throw new PrmFeeEventBrndNotExistException(null);
    			}
    			
    			//수수료행사 예외상품 저장
    			if(prmFeeEventExcelDTO.getExclsErpNo() != null && prmFeeEventExcelDTO.getExclsErpNo() != ""){
    				prmFeeEventExcelDTO.setExclsErpNo(prmFeeEventExcelDTO.getExclsErpNo().replace(" ", ""));
    				String[] exclsErpList = prmFeeEventExcelDTO.getExclsErpNo().split(",");
    				if(exclsErpList != null && exclsErpList.length > 0){
    					for(int i=0; i < exclsErpList.length ; i++){
    						God god = new God();
    						god.setErpGodNo(exclsErpList[i]);
    						
    						god = settlementPrmFeeEventRepository.selectGod(god);
    		    	    	if (god != null && god.getGodNo() != null && god.getGodNo() != ""){
    		    	    		PrmFeeEventExclsGod prmFeeEventExclsGod = new PrmFeeEventExclsGod();
    		    	    		prmFeeEventExclsGod.setExclsGodNo(god.getGodNo());
    		    	    		prmFeeEventExclsGod.setUseYn(prmFeeEventExcelDTO.getUseYn());
    		    	    		prmFeeEventExclsGod.setRegtrId(loginId);
    		    	    		prmFeeEventExclsGod.setUdterId(loginId);
    		    	    		prmFeeEventFormDTO.setPrmFeeEventExclsGod(prmFeeEventExclsGod);
    		    	    		int insertprmFeeEventExclsGodCnt = settlementPrmFeeEventRepository.insertPrmFeeEventExclsGod(prmFeeEventFormDTO);
    		    	    		if (insertprmFeeEventExclsGodCnt > 0){
    		    	    		} else {
    		    	    			// 상품 코드를 확인해주세요.
        		    	    		throw new PrmFeeEventExclsGodNotExistException(null);
    		    	    		}
    		    	    	} else {
    		    	    		// 상품 코드를 확인해주세요.
    		    	    		throw new PrmFeeEventExclsGodNotExistException(null);
    		    	    	}
    					}
    				} else {
    					// 상품 코드를 확인해주세요.
        	    		throw new PrmFeeEventExclsGodNotExistException(null);
    				}
    			}
    		}
    		
    		if("GOD".equals(prmFeeEventExcelDTO.getEventAplTgtCd().toUpperCase())){
    			//수수료행사 상품 저장
    			if(prmFeeEventExcelDTO.getAplErpNo() != null && prmFeeEventExcelDTO.getAplErpNo() != ""){
    				prmFeeEventExcelDTO.setAplErpNo(prmFeeEventExcelDTO.getAplErpNo().replace(" ", ""));
    				String[] aplErpList = prmFeeEventExcelDTO.getAplErpNo().split(",");
    				if(aplErpList != null && aplErpList.length > 0){
    					for(int i=0 ; i < aplErpList.length ; i++){
    						God god = new God();
    						god.setErpGodNo(aplErpList[i]);
    						
    						god = settlementPrmFeeEventRepository.selectGod(god);
    		    	    	if (god != null && god.getGodNo() != null && god.getGodNo() != ""){
    		    	    		PrmFeeEventAplGod prmFeeEventAplGod = new PrmFeeEventAplGod();
    		    	    		prmFeeEventAplGod.setAplGodNo(god.getGodNo());
    		    	    		prmFeeEventAplGod.setUseYn(prmFeeEventExcelDTO.getUseYn());
    		    	    		prmFeeEventAplGod.setRegtrId(loginId);
    		    	    		prmFeeEventAplGod.setUdterId(loginId);
    		    	    		prmFeeEventFormDTO.setPrmFeeEventAplGod(prmFeeEventAplGod);
    		    	    		int insertprmFeeEventExclsGodCnt = settlementPrmFeeEventRepository.insertPrmFeeEventAplGod(prmFeeEventFormDTO);
    		    	    		if (insertprmFeeEventExclsGodCnt > 0){
    		    	    		} else {
    		    	    			// 상품 코드를 확인해주세요.
    		    	    			throw new PrmFeeEventAplGodNotExistException(null);
    		    	    		}
    		    	    	} else {
    		    	    		// 상품 코드를 확인해주세요.
		    	    			throw new PrmFeeEventAplGodNotExistException(null);
    		    	    	}
    					}
    				} else {
    					// 상품 코드를 확인해주세요.
        	    		throw new PrmFeeEventAplGodNotExistException(null);
    				}
    			} else {
    				// 상품 코드를 확인해주세요.
    	    		throw new PrmFeeEventAplGodNotExistException(null);
    			}
    		}
    	}
    }
    
    /**
     * 수수료행사 (대용량)상품정보 저장
     * @param prmFeeEventFormDTO
     * @throws Exception
     */
    public void insertExcelPrmFeeEventGoods(PrmFeeEventExcelDTO prmFeeEventExcelDTO, String loginId, Long feeEventSn)throws Exception {
    	
    	//수수료행사 (대용량)상품정보 저장
    	if(feeEventSn != null){
			if(prmFeeEventExcelDTO.getAplGodNo() != null && prmFeeEventExcelDTO.getAplGodNo() != ""){
				prmFeeEventExcelDTO.setAplGodNo(prmFeeEventExcelDTO.getAplGodNo().replace(" ", ""));
				String[] aplGodNoList = prmFeeEventExcelDTO.getAplGodNo().split(",");
				if(aplGodNoList != null && aplGodNoList.length > 0){
					PrmFeeEventFormDTO prmFeeEventFormDTO = new PrmFeeEventFormDTO();
					PrmFeeEventAplGod prmFeeEventAplGod = new PrmFeeEventAplGod();
					for(int i=0 ; i < aplGodNoList.length ; i++){
						prmFeeEventAplGod.setFeeEventSn(feeEventSn);
						prmFeeEventAplGod.setAplGodNo(aplGodNoList[i]);
						prmFeeEventAplGod.setUseYn("Y");
						prmFeeEventAplGod.setRegtrId(loginId);
						prmFeeEventAplGod.setUdterId(loginId);
						prmFeeEventFormDTO.setPrmFeeEventAplGod(prmFeeEventAplGod);
						int insertprmFeeEventExclsGodCnt = settlementPrmFeeEventRepository.insertPrmFeeEventGoods(prmFeeEventFormDTO);
						if (insertprmFeeEventExclsGodCnt > 0){
						} else {
							// 상품 코드를 확인해주세요.
							throw new PrmFeeEventAplGodNotExistException(null);
						}
					}
				} else {
					// 상품 코드를 확인해주세요.
					throw new PrmFeeEventAplGodNotExistException(null);
				}
			} else {
				// 상품 코드를 확인해주세요.
				throw new PrmFeeEventAplGodNotExistException(null);
			}
    	} else {
			// 행사일련 번호를 확인해주세요.
			throw new PrmFeeEventFeeEventSnExistException(null);
		}
    }

}
