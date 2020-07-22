package com.plgrim.ncp.biz.member.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefAplTgt;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefPymntHistExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPntIntrlckHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.enums.MemberBenefitEnum.BnefTgtTpCd;
import com.plgrim.ncp.biz.member.data.MbrBnefDetailExtend;
import com.plgrim.ncp.biz.member.data.MemberBenefitBoDTO;
import com.plgrim.ncp.biz.member.data.MemberBenefitDTO;
import com.plgrim.ncp.biz.member.result.MemberBenefitAplTgtGrpCdResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitBoResult;
import com.plgrim.ncp.biz.member.result.MemberBenefitPymntHistResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

/**
 * 회원혜택API select repository
 */
@Repository
public class MemberBenefitApiSelectRepository extends AbstractRepository  {
	/**
     * 회원 혜택 조회
     */
    public List<MbrBnefDetailExtend> selectMemberBenefitList(String bnefSectCd){
        return getSession1().selectList("com.plgrim.ncp.biz.bnef.selectMemberBenefitList", bnefSectCd);
    }
    
    public List<MbrBnefDetailExtend> selectMemberBenefitListAll(){
        return getSession1().selectList("com.plgrim.ncp.biz.bnef.selectMemberBenefitListAll");
    }
    
    /**
     * 회원 혜택 대상 조회
     */
    public List<MbrBnefAplTgt> selectMemberBenefitTargetList(MbrBnef mbrBnef){
        return getSession1().selectList("com.plgrim.ncp.biz.bnef.selectMemberBenefitTargetList", mbrBnef);
    }
    
    /**
	 * 재가입여부 확인 (DI)
	 */
	public String validMbrCrtfcExistDi(String erpCstmrNo){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.validMbrCrtfcExistDi", erpCstmrNo);
	}
	
	/**
     * 공통 재가입여부 확인 (DI)
     */
    public String validCommonMbrCrtfcExistDi(MemberBenefitDTO memberBenefitDTO){
        return getSession1().selectOne("com.plgrim.ncp.biz.bnef.validCommonMbrCrtfcExistDi", memberBenefitDTO);
    }
    
    /**
	 * 회원 혜택 지급 이력 조회.
	 *
	 * @param mbrBnefPymntHist
	 * @return 
	 * @throws exception
	 */
	public MbrBnefPymntHist selectMbrBnefPymntHist(MbrBnefPymntHist mbrBnefPymntHist){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMbrBnefPymntHist", mbrBnefPymntHist);
	}
	
    /**
	 * 회원 혜택 지급 이력 Count
	 * @param mbrBnefPymntHist
	 * @return
	 * @throws Exception
	 */
	public int selectMbrBnefPymntHistCnt(MbrBnefPymntHist mbrBnefPymntHist){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMbrBnefPymntHistCnt", mbrBnefPymntHist);
	}
	
	/**
	 * 회원 혜택 지급 가능 이력 Count
	 * @param mbrBnefPymntHistEx
	 * @return
	 * @throws Exception
	 */
	public int selectMbrBnefPymntPsbHistCnt(MbrBnefPymntHistExtend mbrBnefPymntHistEx){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMbrBnefPymntPsbHistCnt", mbrBnefPymntHistEx);
	}
	
    /**
	 * 회원 포인트 연동 이력 Count
	 * @param mbrPntIntrlckHist
	 * @return
	 * @throws Exception
	 */
	public int selectMbrPntIntrlckHistCnt(MbrPntIntrlckHist mbrPntIntrlckHist){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMbrPntIntrlckHistCnt", mbrPntIntrlckHist);
	}
	
	/**
     * 회원 웹 적립금 건수 Count
     */
    public long selectMemberWebPointListCnt(MbrWebpntHist mbrWebpntHist) {
        return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMemberWebPointListCnt", mbrWebpntHist);
    }
    
    /**
	 * 회원 혜택 피추천인 조회
	 */
	public String selectMbrBnefRecomende(String recomendrMbrNo){
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMbrBnefRecomende", recomendrMbrNo);
	}
	
    /**
     * 회원혜택 건수 조회.
     */
    public long selectMemberBenefitListCount(MemberBenefitBoDTO memberBenefitDTO) {
        return getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitListCount", memberBenefitDTO);
    }

    /**
     * 회원혜택 목록 조회.
     */
    public List<MemberBenefitBoResult> selectMemberBenefitList(MemberBenefitBoDTO memberBenefitDTO) {
        return getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitList", memberBenefitDTO);
    }
    
    /**
     * 회원혜택 엑셀 목록 조회.
     */
	public List<Map<String, Object>> selectMemberBenefitExcelList(MemberBenefitBoDTO excelDTO) {
		
		List<Map<String, Object>> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitExcelList", excelDTO);

		return results;
	}
	
    /**
     * 회원혜택 엑셀 수정이력 조회.
     */
	public List<Map<String, Object>> selectMemberBenefitDetailHistExcelList(MemberBenefitBoDTO excelDTO) {
		
		List<Map<String, Object>> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetailHistExcelList", excelDTO);

		return results;
	}
	
    /**
     * 회원혜택 상세 엑셀 발급내역 조회.
     */
	public List<Map<String, Object>> selectMemberBenefitPymntHistExcelList(MemberBenefitBoDTO excelDTO) {
		
		List<Map<String, Object>> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitPymntHistExcelList", excelDTO);

		return results;
	}	
    
    /**
     * 회원혜택 상세조회.
     */    
    public MemberBenefitBoResult selectMemberBenefitDetail(MemberBenefitBoDTO dto) {
    	
    	MemberBenefitBoResult result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetail", dto);
		
        // 프로모션 적용범위
        if (result.getMbrBnefAplTgtList() != null) {

            for (MbrBnefAplTgt mbat : result.getMbrBnefAplTgtList()) {
                if (BnefTgtTpCd.LANG.toString().equals(mbat.getBnefTgtTpCd())) {
                    if (result.getLangList() == null) {
                        result.setLangList(new ArrayList<String>());
                    }
                    result.getLangList().add(mbat.getLangCd());
                }
                else if (BnefTgtTpCd.DVC.toString().equals(mbat.getBnefTgtTpCd())) {
                    if (result.getDeviceList() == null) {
                        result.setDeviceList(new ArrayList<String>());
                    }
                    result.getDeviceList().add(mbat.getDvcCd());
                }
                else if (BnefTgtTpCd.MALL_ID.toString().equals(mbat.getBnefTgtTpCd())) {
                    if (result.getMallList() == null) {
                        result.setMallList(new ArrayList<String>());
                    }
                    result.getMallList().add(mbat.getMallId());
                }
                else if (BnefTgtTpCd.MOBILE_APP_SECT.toString().equals(mbat.getBnefTgtTpCd())) {
                    if (result.getMobileAppList() == null) {
                        result.setMobileAppList(new ArrayList<String>());
                    }
                    result.getMobileAppList().add(mbat.getMobileAppSectCd());
                }
                else if (BnefTgtTpCd.CHNNL_RECPTN_AGR_SECT.toString().equals(mbat.getBnefTgtTpCd())) {
                    if (result.getChnnlRecptnAgrSectList() == null) {
                        result.setChnnlRecptnAgrSectList(new ArrayList<String>());
                    }
                    result.getChnnlRecptnAgrSectList().add(mbat.getChnnlRecptnAgrSectCd());
                }                 
            }
        }
        
        // 프로모션 적용대상
        if (result.getMbrBnefAplTgtList() != null) {

            for (MbrBnefAplTgt mbat : result.getMbrBnefAplTgtList()) {
                if (StringService.isNotEmpty(mbat.getTgtMbrTpCd())) {
                    if (result.getMemberTypeList() == null) {
                        result.setMemberTypeList(new ArrayList<String>());
                    }
                    result.getMemberTypeList().add(mbat.getTgtMbrTpCd());
                }
                else if (StringService.isNotEmpty(mbat.getTgtMbrAtrbCd())) {
                    if (result.getMemberAttributeList() == null) {
                        result.setMemberAttributeList(new ArrayList<String>());
                    }
                    result.getMemberAttributeList().add(mbat.getTgtMbrAtrbCd());
                }
            }
        }        
    	
		return result;
	}
    
	/**
	 * 회원혜택 지급 혜택 목록 조회.
	 */	
	public Page<MemberBenefitBoResult> selectMemberBenefitDetailList(MemberBenefitBoDTO dto ,PageParam pageParam) {
		
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberBenefitBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetailList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetailListCount", dto);
		return new PageImpl<MemberBenefitBoResult>(results, pageParam.getPageable(), totalRow);
		
	}
	
	/**
	 * 회원혜택 상세 수정이력 목록 조회.
	 */	
	public Page<MemberBenefitBoResult> selectMemberBenefitDetailHistList(MemberBenefitBoDTO dto ,PageParam pageParam) {
		
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberBenefitBoResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetailHistList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitDetailHistListCount", dto);
		return new PageImpl<MemberBenefitBoResult>(results, pageParam.getPageable(), totalRow);
		
	}   	
	
	/**
	 * 회원혜택 상세 발급내역 목록 조회.
	 */	
	public Page<MemberBenefitPymntHistResult> selectMemberBenefitPymntHistList(MemberBenefitBoDTO dto ,PageParam pageParam) {
		
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberBenefitPymntHistResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitPymntHistList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMemberBenefitPymntHistListCount", dto);
		return new PageImpl<MemberBenefitPymntHistResult>(results, pageParam.getPageable(), totalRow);
		
	}   		
	
	/**
	 * 회원혜택 상세 그룹사 목록 조회.
	 */	
	public Page<MemberBenefitAplTgtGrpCdResult> selectMbrBnefAplTgtGrpCdList(MemberBenefitBoDTO dto ,PageParam pageParam) {
		
		RepositoryHelper.makePageEntityIndex(dto, pageParam);
		List<MemberBenefitAplTgtGrpCdResult> results = getSession1().selectList("com.plgrim.ncp.biz.mbr.benefit.selectMbrBnefAplTgtGrpCdList", dto);
		long totalRow =  getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.selectMbrBnefAplTgtGrpCdListCount", dto);
		return new PageImpl<MemberBenefitAplTgtGrpCdResult>(results, pageParam.getPageable(), totalRow);		
	}
	
    /**
     * 회원혜택 중복체크.
     */    
    public int checkMemberBenefitKey(MemberBenefitBoDTO dto) {
    	int result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.checkMemberBenefitKey", dto);
		return result;
    }

    /**
     * 회원혜택 지급혜택 중복체크. 
     */    
    public int checkMemberBenefitDetailDup(MemberBenefitBoDTO dto) {
    	int result = getSession1().selectOne("com.plgrim.ncp.biz.mbr.benefit.checkMemberBenefitDetailDup", dto);
		return result;
    }    
    
    /**
     * 회원 혜택 단건 조회
     */
    public MbrBnefDetail selectMemberBenefitInfo(MbrBnefDetail mbrBnefDetail) {
        return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMemberBenefitInfo", mbrBnefDetail);
    }

    /**
     * 회원 등급 변경 혜택 중복 체크
     */
	public int selectMemberBenefitGrdCount(MbrBnefDetailExtend mbrBnefDetailExtend) {
		return getSession1().selectOne("com.plgrim.ncp.biz.bnef.selectMemberBenefitGrdCount", mbrBnefDetailExtend);
	}
}
