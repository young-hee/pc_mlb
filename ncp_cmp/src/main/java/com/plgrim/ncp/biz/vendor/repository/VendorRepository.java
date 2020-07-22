/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 17       
 */
package com.plgrim.ncp.biz.vendor.repository;

import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComBrndHist;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComCnnc;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.repository.com.ComRepository;
import com.plgrim.ncp.biz.vendor.data.SysStdOptSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComChrgerResult;
import com.plgrim.ncp.biz.vendor.result.SysAdminListResult;
import com.plgrim.ncp.biz.vendor.result.SysStdOptResult;
import com.plgrim.ncp.biz.vendor.result.VendorGoodsOptionResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.commons.result.ComPopupResult;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author sy59.gim
 * @since 2015. 3. 24
 */
/**
 * @author user
 *
 */
@Repository
@Slf4j
public class VendorRepository extends ComRepository  {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
	 * 업체 목록 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectVendorListCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectVendorListCount", vendorSearchDTO);
	}
	
	/**
	 * 업체 목록 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<VendorListResult> selectVendorList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectVendorList", vendorSearchDTO);
	}
    
	/**
	 * 업체 목록 조회 엑셀.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<Map<String, Object>> selectVendorListExcel(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectVendorListExcel", vendorSearchDTO);
	}    
    
    /**
     * 엄체 상세조회
     * @param com
     * @return
     * @throws Exception
     */
    public Com selectVendor(Com com) {
    	return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectVendor", com);    	
    }
    
    /**
	 * 판매 제휴사 상세 조회
	 *
	 * @param com the com
	 * @return the com
	 * @throws Exception the exception
	 */
	public List<Com> selectAffCom(Com com) throws Exception{
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectAffCom", com); 
	}
    
	/**
	 * 업체 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 9
	 */
	public void updateVendor(Com com) {
		getSession1().insert("com.plgrim.ncp.biz.vendor.updateVendor", com);
	}
	
	/**
	 * 업체 등록
	 * @param com
	 * @return
	 * @throws Exception
	 */
	public String insertVendor(Com com) {
		getSession1().insert("com.plgrim.ncp.biz.vendor.insertVendor", com);
		return com.getComId();
	}
	
	/**
	 * 판매제휴사 등록
	 * @param com
	 * @return
	 * @throws Exception
	 */
	public int insertAffCom(Com com) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.insertAffCom", com);
	}

	/*업체등록시 해외배송기본정책 등록*/
	public int updateVendorOvseaCstPlc(Com com) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateVendorOvseaCstPlc", com);
	}

	

	/**
	 * 브랜드별 수수료율 리스트 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectBrandCommissionListCount(VendorSearchDTO vendorSearchDTO) {
    	long cnt;
    	if("AFF_AGNC".equals(vendorSearchDTO.getComTpCd())){//제휴대행사
    		cnt = getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComAffVrscComBrndCount", vendorSearchDTO);
    	}else{
    		cnt = getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectBrandCommissionListCount", vendorSearchDTO);
    	}
		return cnt;
	}
	
	/**
	 * 브랜드별 수수료율 리스트.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<VendorListResult> selectBrandCommissionList(VendorSearchDTO vendorSearchDTO) {
    	List<VendorListResult> result;
    	if("AFF_AGNC".equals(vendorSearchDTO.getComTpCd())){//제휴대행사
    		result = getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComAffVrscComBrnd", vendorSearchDTO);
    	}else{
    		result = getSession1().selectList("com.plgrim.ncp.biz.vendor.selectBrandCommissionList", vendorSearchDTO);
    	}
		return result; 
	}
    
	/**
	 * 브랜드별 수수료율 리스트 엑셀.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<Map<String, Object>> selectBrandCommissionListExcel(VendorSearchDTO vendorSearchDTO) {
    	List<Map<String, Object>> result;
    	if("AFF_AGNC".equals(vendorSearchDTO.getComTpCd())){//제휴대행사
    		result = getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComAffVrscComBrndExcel", vendorSearchDTO);
    	}else{
    		result = getSession1().selectList("com.plgrim.ncp.biz.vendor.selectBrandCommissionListExcel", vendorSearchDTO);
    	}
		return result;
	}    
	/**
	 * 브랜드별 수수료율 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeBrndCommissionGridData(VendorGridDTO vendorListResult) {
		//Com com = vendorListResult.getCom();
		//getSession1().update("com.plgrim.ncp.biz.vendor.mergeVendorGridData", com);
		getSession1().insert("com.plgrim.ncp.biz.vendor.mergeBrndCommission", vendorListResult);
		
		//History 갱신
		long cnt = getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComBrndHist", vendorListResult);
		//1. 기존 데이터 존재 시 ->  이력 종료 일시 - 1초 로 변경
		if(cnt>0){
			//삭제시도 수수료율 변경이 가능하므로 기존로직대로 로그 남긴다.
			getSession1().update("com.plgrim.ncp.biz.vendor.updateComBrndHist", vendorListResult);
		}
		//2. 수정/추가 시 브랜드 수수료율 이력 테이블 추가(삭제여부(Y) : 종료일자를 현재일자로, (N) : 종료일자를 2099/12/31로 저장)
		getSession1().insert("com.plgrim.ncp.biz.vendor.insertComBrndHist", vendorListResult);
	}  
	
	/**
	 * 브랜드별 수수료율 삭제. (물리적 삭제는 처리 안하기로 결정되면 사용 안하는 로직)
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void deleteBrndCommissionGridData(VendorGridDTO vendorListResult) {
		//Com com = vendorListResult.getCom();
		//getSession1().update("com.plgrim.ncp.biz.vendor.mergeVendorGridData", com);
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteBrndCommissionChrger", vendorListResult);
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteBrndCommission", vendorListResult);
		
		//History 갱신
		long cnt = getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComBrndHist", vendorListResult);
		//1. 기존 데이터 존재 시 -> 종료일자를 현재시간으로
		if(cnt>0){
			getSession1().update("com.plgrim.ncp.biz.vendor.updateComBrndHist", vendorListResult);
		}
	}
	
	/**
	 * 제휴대행사 브랜드 수수료 등록
	 * @param vendorListResult
	 */
	public void mergeComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		getSession1().insert("com.plgrim.ncp.biz.vendor.mergeComAffVrscComBrnd", comAffVrscComBrnd);
	}  
	
	/**
	 * 제휴대행사 브랜드 수수료 삭제
	 * @param vendorListResult
	 */
	public void deleteComAffVrscComBrnd(ComAffVrscComBrnd comAffVrscComBrnd) {
		getSession1().delete("com.plgrim.ncp.biz.vendor.deleteComAffVrscComBrnd", comAffVrscComBrnd);
	}
	
	/**
	 * 제휴사별 상품속성관리 리스트 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectComAffVrscComCnncListCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComAffVrscComCnncListCount", vendorSearchDTO);
	}
	
	/**
	 * 제휴사별 상품속성관리 리스트.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<VendorListResult> selectComAffVrscComCnncList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComAffVrscComCnncList", vendorSearchDTO);
	}
    
	/**
	 * 제휴사별 상품속성관리 리스트 엑셀.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<Map<String, Object>> selectComAffVrscComCnncListExcel(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComAffVrscComCnncListExcel", vendorSearchDTO);
	}    
	/**
	 *  제휴사별 상품속성 저장/수정
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeComAffVrscComCnnc(VendorGridDTO vendorListResult) {
		getSession1().insert("com.plgrim.ncp.biz.vendor.mergeComAffVrscComCnnc", vendorListResult);
	} 		
	
	/**
	 * 제휴사별 상품속성 삭제
	 * @param vendorListResult
	 */
	public void deleteComAffVrscComCnnc(VendorGridDTO vendorListResult) {
		ComAffVrscComCnnc comAffVrscComCnnc = vendorListResult.getComAffVrscComCnnc();
		getSession1().delete("com.plgrim.ncp.base.deleteComAffVrscComCnnc", comAffVrscComCnnc);
	} 
	
	
	/**
	 * 운영자 목록 건수 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Long [설명]
	 * @since 2015. 3. 24
	 */
    public long selectAdminListCount(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectAdminListCount", vendorSearchDTO);
	}
	
	/**
	 * 운영자 목록 리스트.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return List [설명]
	 * @since 2015. 3. 24
	 */
    public List<SysAdminListResult> selectAdminList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectAdminList", vendorSearchDTO);
	}	
    
    /**
     * 운영자 목록 리스트(페이징X)
     * @param vendorSearchDTO
     * @return
     */
    public List<SysAdminListResult> selectAdminInfoList(VendorSearchDTO vendorSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectAdminInfoList", vendorSearchDTO);
	}	
    
	/**
	 * 운영자 비밀번호 초기화.
	 *
	 * @param mbr [설명]
	 * @return Int [설명]
	 * @since 2015. 3. 25
	 */
    public int updateAdminPassword(SysAdmin sysAdmin) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateAdminPassword", sysAdmin);
	}
    
    /**
     * 업체 상품 옵션 등록
     * @param vendorGoodsOptionDTO
     * @return
     */
    public int mergeComGodOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.mergeComGodOpt", vendorGoodsOptionDTO);
	}
    
    /**
     * 업체 상품 옵션 프리사이즈 등록
     * @param vendorGoodsOptionDTO
     * @return
     */
    public int mergeComGodOptFreeSize(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.mergeComGodOptFreeSize", vendorGoodsOptionDTO);
	}
    
    /**
     * 업체 상품 옵션 값 등록
     * @param vendorGoodsOptionDTO
     * @return
     */
    public int mergeComGodOptVal(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.mergeComGodOptVal", vendorGoodsOptionDTO);
	}

	/**
	 * 입점 업체 상품 옵션 표준 연결 테이블 등록
	 * @param vendorGoodsOptionDTO
	 * @return
	 */
	public int insertStdOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
		return getSession1().insert("com.plgrim.ncp.biz.vendor.insertStdOpt", vendorGoodsOptionDTO);
	}

    /**
     * 업체 상품 옵션 조회
     * @param vendorGoodsOptionDTO
     * @return
     */
    public List<VendorGoodsOptionResult> selectComGodOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComGodOpt", vendorGoodsOptionDTO);
	}
	
    /**
     * 업체 상품 옵션 순서 수정
     * @param vendorGoodsOptionDTO
     * @return
     */    
    public int updateGodOptValSortSeq(VendorGoodsOptionDTO vendorGoodsOptionDTO) {
    	return getSession1().update("com.plgrim.ncp.biz.vendor.updateGodOptValSortSeq",vendorGoodsOptionDTO);	    	
    }
    
    /**
     * 업체 담당자 등록
     * @param comChrger
     * @return
     * @throws Exception
     */
    public int insertComChrger(ComChrger comChrger) {
    	return getSession1().insert("com.plgrim.ncp.biz.vendor.insertComChrger",comChrger);
    }
    
    /**
     * 업체 담당자 수정
     * @param comChrger
     * @return
     * @throws Exception
     */
    public int updateComChrger(ComChrger comChrger) {
    	return getSession1().update("com.plgrim.ncp.biz.vendor.updateComChrger",comChrger);
    }

    /**
     * 업체 언어별 담당자 수정
     * @param comChrger
     * @return
     * @throws Exception
     */
    public int updateComLangbyChrger(ComChrger comChrger) {
    	return getSession1().update("com.plgrim.ncp.biz.vendor.updateComLangbyChrger",comChrger);
    }

    /**
     * 제휴대행사 - 제휴사별 상품속성에 포함된 제휴사 조회
     * @param paramData
     * @return
     * @throws Exception
     */
	public List<ComPopupResult> selectAffComList( VendorSearchDTO vendorSearchDTO) {		
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectAffComList", vendorSearchDTO);
	}
    
	
	/**
	 * 업체 담당자 상세 조회.
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public ComChrger selectComChrger(ComChrger comChrger) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComChrger", comChrger);
	}

	/**
	 * 업체 담당자 상세 조회리스트.
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public  List<ComChrgerResult> selectComChrgerList(ComChrger comChrger) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectComChrgerList", comChrger);
	}

	/**
	 * 업체 담당자 상세 조회(팝업).
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public ComChrger selectComChrgerPopup(ComChrger comChrger) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComChrgerPopup", comChrger);
	}

	/**
	 * 업체 언어별 담당자 상세 조회(팝업).
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public ComChrgerResult selectComLangbyChrger(ComChrger comChrger) {
		return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComLangbyChrger", comChrger);
	}

	/**
	 * COM_ID로 브랜드 조회
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 */
	public  List<ComChrgerResult> selectBrndIdList(ComChrger comChrger) {
		return getSession1().selectList("com.plgrim.ncp.biz.vendor.selectBrndIdList", comChrger);
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	public Page<SysStdOptResult> selectSysStdOpt(SysStdOptSearchDTO sysStdOptSearchDTO, PageParam pageParam) {
		// 페이지 설정
		RepositoryHelper.makePageEntityIndex(sysStdOptSearchDTO, pageParam);

		// 공지사항 리스트 조회
		List<SysStdOptResult> resultList = getSession1().selectList("com.plgrim.ncp.biz.vendor.selectSysStdOpt", sysStdOptSearchDTO);

		// 공지사항 게시글 갯수 조회
		long totalRow = getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectSysStdOptTotal", sysStdOptSearchDTO);
		return new PageImpl<SysStdOptResult>(resultList, pageParam.getPageable(), totalRow);

	}
	
	  /**
	    * 업체제휴 업체브랜드이력 업데이트
	    * @param comAffVrscComBrnd
	    * @since 2015. 12. 18.
	    */
	    public void updateComAffVrscComBrnd(ComAffComBrndHist comAffVrscComBrnd) {
			getSession1().insert("com.plgrim.ncp.biz.vendor.updateComAffVrscComBrnd", comAffVrscComBrnd);
		}  
	
	/**
	* 업체제휴 업체브랜드이력 초기저장
	* @param comAffVrscComBrnd
	* @since 2015. 12. 17.
	*/
	public void insertInitComAffComBrndHist(ComAffComBrndHist comAffVrscComBrnd) {
		getSession1().insert("com.plgrim.ncp.biz.vendor.insertInitComAffComBrndHist", comAffVrscComBrnd);
	}  
	
	/**
	    * 제휴사수수료 과거 이력 데이터 조회
	    * 
	    * @param comAffVrscComBrnd
	    * @return
	    * @since 2015. 12. 18.
	    */
	    public VendorListResult selectComAffVrscComBrndOldData(ComAffComBrndHist comAffVrscComBrnd) {
	    	
			return getSession1().selectOne("com.plgrim.ncp.biz.vendor.selectComAffVrscComBrndOldData", comAffVrscComBrnd);
		}
	    
	/**
	 * 업체 I/F 데이터 카운트 (참조입력용)
	 *
	 * @param comChrger the ComChrger
	 * @return the ComChrger
	 * @throws SQLException the SQL exception
	 * split method
	 * 2017.02.22
	 */
	public int getComCnt(VendorSearchDTO vendorSearchDTO) {
		Integer cnt = getSession1().selectOne("com.plgrim.ncp.biz.vendor.getComCnt", vendorSearchDTO.getCom());
		return cnt;
	}

	/**
	 * 업체 수정.
	 *
	 * @param com the Com
	 * @throws SQLException the SQL exception
	 */
	public int updateCom(Com com) {
		return getSession1().update("com.plgrim.ncp.biz.vendor.updateCom", com);
	}
}
