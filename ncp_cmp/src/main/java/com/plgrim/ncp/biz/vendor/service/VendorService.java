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
 * @since       2015. 5. 8       
 */
package com.plgrim.ncp.biz.vendor.service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.RepositoryHelper;
import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffComBrndHist;
import com.plgrim.ncp.base.entities.datasource1.com.ComAffVrscComBrnd;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.repository.sys.SysAdminRepository;
import com.plgrim.ncp.biz.vendor.data.VendorGoodsOptionDTO;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.VendorRepository;
import com.plgrim.ncp.biz.vendor.result.ComChrgerResult;
import com.plgrim.ncp.biz.vendor.result.SysAdminListResult;
import com.plgrim.ncp.biz.vendor.result.VendorGoodsOptionResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CommonEncryptDecryptUtil;
import com.plgrim.ncp.commons.util.CommonEncryptDecryptUtil.BizType;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 업체
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
@Service
@Slf4j
public class VendorService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 Repository
	 */
	@Autowired
	private VendorRepository vendorRepository;
	
	@Autowired
	private SysAdminRepository sysAdminRepository;
	
	@Autowired
	InterfaceApiCommon interfaceApiCommon;	

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
	
	@Autowired
	private CommonEncryptDecryptUtil commonEncryptDecryptUtil;
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
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public long selectVendorListCount(VendorSearchDTO searchDTO) throws Exception {
		return vendorRepository.selectVendorListCount(searchDTO);
	}
	
	/**
	 * 업체 목록 조회.
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public Page<VendorListResult> selectVendorPageList(VendorSearchDTO searchDTO) throws Exception {
		
		// 페이지 인덱스 셋팅
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorRepository.selectVendorListCount(searchDTO);
		
		// 목록 조회
		List<VendorListResult> results = new ArrayList<VendorListResult>();
		if(totalRow > 0){
			results = vendorRepository.selectVendorList(searchDTO);
		}
		return new PageImpl<VendorListResult>(results, searchDTO.getPageParam().getPageable(), totalRow);		
	}
	
	/**
	 * 업체 목록 조회.(페이징처리없는)
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public List<VendorListResult> selectVendorList(VendorSearchDTO searchDTO) throws Exception {
		
		return vendorRepository.selectVendorList(searchDTO);
	}	
    /**
     * 업체 목록 조회 엑셀.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param searchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectVendorListExcel(VendorSearchDTO searchDTO) throws Exception {
		return vendorRepository.selectVendorListExcel(searchDTO);
	}	
    
    /**
     * 업체상세조회
     * @param com
     * @return
     * @throws Exception
     */
    public Com selectVendor(Com com) throws Exception {
    	return vendorRepository.selectVendor(com);
    }
    
    /**
	 * 판매 제휴사 상세 조회
	 *
	 * @param com the com
	 * @return the com
	 * @throws Exception the exception
	 */
	public List<Com> selectAffCom(Com com) throws Exception{
		return vendorRepository.selectAffCom(com);
	}
	
	/**
	 * 업체 상세 조회.
	 *
	 * @param vendorSearchDTO [설명]
	 * @return Vendor list result [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public VendorListResult selectVendor(VendorSearchDTO vendorSearchDTO) throws Exception {
		Com com = vendorSearchDTO.getCom();
		// 업체 상세건 조회
		VendorListResult results = new VendorListResult();
		 results.setCom(vendorRepository.selectCom(com));
		 getStringService();
		 Com com1= results.getCom();
		 if(com1 != null && StringService.isNotEmpty(com1.getBnkAcctNo()) && com1.getBnkAcctNo().length() > 20) {
			//계좌번호 복호화
	        String decryptedAccNo = commonEncryptDecryptUtil.getDecryptData(com1.getBnkAcctNo(), false, com1.getComId(), BizType.VENDOR);
	        if(StringService.isNotEmpty(decryptedAccNo)) {
	        	results.getCom().setBnkAcctNo(decryptedAccNo);
	        }
		 }
		 String telNo = "";
		 String cellNo = "";
		 String lnmAddrPostNo = "";
		 String rdAddrPostNo = "";
		 
		 telNo = StringService.nvl(com1.getTelNationNo(),"")+"-"
				 + StringService.nvl(com1.getTelAreaNo(),"") +"-"
				 + StringService.nvl(com1.getTelTlofNo(),"") +"-"
				 + StringService.nvl(com1.getTelTlofWthnNo(),"") ;
		 
		 cellNo = StringService.nvl(com1.getChrgerMobilNationNo(),"")+"-"
				 + StringService.nvl(com1.getChrgerMobilAreaNo(),"") +"-"
				 + StringService.nvl(com1.getChrgerMobilTlofNo(),"") +"-"
				 + StringService.nvl(com1.getChrgerMobilTlofWthnNo(),"") ;
			 
		 lnmAddrPostNo = StringService.nvl(com1.getLnmAddrPostNo(),"").replace("-", "");
		 rdAddrPostNo = StringService.nvl(com1.getRdAddrPostNo(),"").replace("-", "");
			 
		 results.setTelNo(telNo);
		 results.setCellNo(cellNo);
		 results.getCom().setLnmAddrPostNo(lnmAddrPostNo);
		 results.getCom().setRdAddrPostNo(rdAddrPostNo);
		 
		return results;
	}	
	
	/**
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public List<VendorListResult> selectVendorAllList(VendorSearchDTO searchDTO) throws Exception {

		searchDTO.setEndIndex(99999);
		searchDTO.setBeginIndex(1);
		return vendorRepository.selectVendorList(searchDTO);
	}	

	/**
	 * 브랜드별 수수료율 리스트 건수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public long selectBrandCommissionListCount(VendorSearchDTO searchDTO) throws Exception {
		return vendorRepository.selectBrandCommissionListCount(searchDTO);
	}
	


	/**
	 * 브랜드별 수수료율 리스트.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public Page<VendorListResult> selectBrandCommissionList(VendorSearchDTO searchDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorRepository.selectBrandCommissionListCount(searchDTO);
		
		// 목록 조회
		List<VendorListResult> results = new ArrayList<VendorListResult>();
		if(totalRow > 0){
			results = vendorRepository.selectBrandCommissionList(searchDTO);
		}
		
		return new PageImpl<VendorListResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}
	
    /**
     * 브랜드별 수수료율 리스트 엑셀.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param searchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectBrandCommissionListExcel(VendorSearchDTO searchDTO) throws Exception {
		return vendorRepository.selectBrandCommissionListExcel(searchDTO);
	}	
	
	public void updateVendor(Com com) throws Exception{
		
		com.setUdterId(BOSecurityUtil.getLoginId());
		if(StringService.isNotEmpty(com.getBnkAcctNo())) {
			//계좌번호 암호화
			String encryptedAccNo = commonEncryptDecryptUtil.getEncryptData(com.getBnkAcctNo(), com.getComId(), BizType.VENDOR);
			if(StringService.isNotEmpty(encryptedAccNo)) {
				com.setBnkAcctNo(encryptedAccNo);
			}
		}
		
		vendorRepository.updateVendor(com);
	}
	
	/**
	 * 업체등록
	 * @param com
	 * @throws Exception
	 */
	public String insertVendor(Com com) throws Exception {
		String comId = vendorRepository.insertVendor(com);
		// 해외배송비 연결
		vendorRepository.updateVendorOvseaCstPlc(com);
		return comId;
	}
	
	/**
	 * 판매제휴사 등록
	 *
	 * @param com the com
	 * @return the string
	 * @throws Exception the exception
	 */
	public int insertAffCom(Com com) throws Exception{
		return vendorRepository.insertAffCom(com);
	}
	

	/**
	 * 브랜드별 수수료율 그리드 수정/저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param <VendorListResult> the generic type
	 * @param dataList [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeBrndCommissionGridData(List<VendorGridDTO> dataList) throws Exception{	
		Iterator<VendorGridDTO> iterator = dataList.iterator();		
		String loginId = BOSecurityUtil.getLoginId();
		while(iterator.hasNext()) {
			
			
			VendorGridDTO vendorGridDTO = iterator.next();
			if("AFF_AGNC".equals(vendorGridDTO.getComTpCd())){//제휴대행사
				vendorGridDTO.getComAffVrscComBrnd().setRegtrId(loginId);
				vendorRepository.mergeComAffVrscComBrnd(vendorGridDTO.getComAffVrscComBrnd());
			
				// 제휴사수수료가 입력되었으면 셋팅
				if(vendorGridDTO.getComAffVrscComBrnd().getAffComFeeRt() != null){
					
					//COM_AFF_COM_BRND_HIST 테이블 제휴사수수료 과거 데이터 조회
					ComAffComBrndHist comAffComBrndHistParam = new ComAffComBrndHist();
					comAffComBrndHistParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
					comAffComBrndHistParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
					comAffComBrndHistParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
					comAffComBrndHistParam.setComBrndHistSectCd("AFF_VRSC_FEE");
					
					//과거이력데이터 조회
					VendorListResult oldData = new VendorListResult();
					oldData = vendorRepository.selectComAffVrscComBrndOldData(comAffComBrndHistParam);
					
					if(oldData != null){
						//과거이력이 있으면 업데이트 및 인설트

						if(vendorGridDTO.getComAffVrscComBrnd().getAffComFeeRt().equals(oldData.getComAffComBrndHist().getModAfHistRt())){
							//입력받은값이 기존데이터와 같으면 패스
						}else{
							//입력받은값과 기존데이터가 다르면 업데이트 및 인설트
							ComAffComBrndHist comAffComBrndHistInsertParam = new ComAffComBrndHist();
							comAffComBrndHistInsertParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
							comAffComBrndHistInsertParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
							comAffComBrndHistInsertParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
							comAffComBrndHistInsertParam.setComBrndHistSectCd("AFF_VRSC_FEE");
							comAffComBrndHistInsertParam.setModBfHistRt(oldData.getComAffComBrndHist().getModAfHistRt());
							comAffComBrndHistInsertParam.setModAfHistRt(vendorGridDTO.getComAffVrscComBrnd().getAffComFeeRt());
							comAffComBrndHistInsertParam.setRegtrId(loginId);
							comAffComBrndHistInsertParam.setUdterId(loginId);
							
							//기존데이터 날짜 업데이트
							vendorRepository.updateComAffVrscComBrnd(comAffComBrndHistInsertParam);
							
							//새이력 저장
							vendorRepository.insertInitComAffComBrndHist(comAffComBrndHistInsertParam);
						}
						
					}else{
						//과거이력이 없으면 새  제휴사수수료 이력 인설트
						ComAffComBrndHist comAffComBrndHistInsertParam = new ComAffComBrndHist();
						comAffComBrndHistInsertParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
						comAffComBrndHistInsertParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
						comAffComBrndHistInsertParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
						comAffComBrndHistInsertParam.setModAfHistRt(vendorGridDTO.getComAffVrscComBrnd().getAffComFeeRt());
						comAffComBrndHistInsertParam.setRegtrId(loginId);
						comAffComBrndHistInsertParam.setUdterId(loginId);
						comAffComBrndHistInsertParam.setComBrndHistSectCd("AFF_VRSC_FEE");
						vendorRepository.insertInitComAffComBrndHist(comAffComBrndHistInsertParam);
						
					}
					
				}
				
				//대행사수수료율이 입력되었으면 셋팅
				if(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComFeeRt() != null){
					
					//COM_AFF_COM_BRND_HIST 테이블 대행사수수료율이 과거 데이터 조회
					ComAffComBrndHist comAffComBrndHistParam = new ComAffComBrndHist();
					comAffComBrndHistParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
					comAffComBrndHistParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
					comAffComBrndHistParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
					comAffComBrndHistParam.setComBrndHistSectCd("AFF_VRSC_COM_FEE");
					
					//과거이력데이터 조회
					VendorListResult oldData = new VendorListResult();
					oldData = vendorRepository.selectComAffVrscComBrndOldData(comAffComBrndHistParam);
					
					if(oldData != null){
						//과거이력이 있으면 업데이트 및 인설트
						
						if(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComFeeRt().equals(oldData.getComAffComBrndHist().getModAfHistRt())){
							//입력받은값이 기존데이터와 같으면 패스
						}else{
							
							//입력받은값과 기존데이터가 다르면 업데이트 및 인설트
							ComAffComBrndHist comAffComBrndHistInsertParam = new ComAffComBrndHist();
							comAffComBrndHistInsertParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
							comAffComBrndHistInsertParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
							comAffComBrndHistInsertParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
							comAffComBrndHistInsertParam.setComBrndHistSectCd("AFF_VRSC_COM_FEE");
							comAffComBrndHistInsertParam.setModBfHistRt(oldData.getComAffComBrndHist().getModAfHistRt());
							comAffComBrndHistInsertParam.setModAfHistRt(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComFeeRt());
							comAffComBrndHistInsertParam.setRegtrId(loginId);
							comAffComBrndHistInsertParam.setUdterId(loginId);
							
							//기존데이터 날짜 업데이트
							vendorRepository.updateComAffVrscComBrnd(comAffComBrndHistInsertParam);
							
							//새이력 저장
							vendorRepository.insertInitComAffComBrndHist(comAffComBrndHistInsertParam);
						}
					}else{
						//과거이력이 없으면 새  제휴사수수료 이력 인설트
						ComAffComBrndHist comAffComBrndHistInsertParam = new ComAffComBrndHist();
						comAffComBrndHistInsertParam.setAffVrscComId(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComId());
						comAffComBrndHistInsertParam.setAffComId(vendorGridDTO.getComAffVrscComBrnd().getAffComId());
						comAffComBrndHistInsertParam.setBrndId(vendorGridDTO.getComAffVrscComBrnd().getBrndId());
						comAffComBrndHistInsertParam.setModAfHistRt(vendorGridDTO.getComAffVrscComBrnd().getAffVrscComFeeRt());
						comAffComBrndHistInsertParam.setRegtrId(loginId);
						comAffComBrndHistInsertParam.setUdterId(loginId);
						comAffComBrndHistInsertParam.setComBrndHistSectCd("AFF_VRSC_COM_FEE");
						vendorRepository.insertInitComAffComBrndHist(comAffComBrndHistInsertParam);
						
					}
					
				}
			
			}else{
				vendorGridDTO.getComBrnd().setUdterId(loginId);
				vendorRepository.mergeBrndCommissionGridData(vendorGridDTO);
			}
			
			
			
			
		}
    }
	
	/**
	 * 브랜드별 수수료율 그리드 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param <VendorGridDTO> the generic type
	 * @param dataList [설명]
	 * @since 2015. 4. 17
	 */
	public void deleteBrndCommissionGridData(List<VendorGridDTO> dataList) {
	
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		while(iterator.hasNext()) {
			VendorGridDTO vendorGridDTO = iterator.next();
			if("AFF_AGNC".equals(vendorGridDTO.getComTpCd())){//제휴대행사
				vendorRepository.deleteComAffVrscComBrnd(vendorGridDTO.getComAffVrscComBrnd());
			}else{
				vendorRepository.deleteBrndCommissionGridData(vendorGridDTO);
			}
		}
    }

	/**
	 * 제휴사별 상품속성관리 리스트.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public Page<VendorListResult> selectComAffVrscComCnncList(VendorSearchDTO searchDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorRepository.selectComAffVrscComCnncListCount(searchDTO);
		
		// 목록 조회
		List<VendorListResult> results = new ArrayList<VendorListResult>();
		if(totalRow > 0){
			results = vendorRepository.selectComAffVrscComCnncList(searchDTO);
		}
		
		return new PageImpl<VendorListResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}	
	
    /**
     * 제휴사별 상품속성관리 리스트 엑셀.
     * 
     * <p/>
     * 
     * [사용 방법 설명].
     *
     * @param searchDTO [설명]
     * @return List [설명]
     * @throws Exception the exception
     * @since 2015. 5. 8
     */
    public List<Map<String, Object>> selectComAffVrscComCnncListExcel(VendorSearchDTO searchDTO) throws Exception {
		return vendorRepository.selectComAffVrscComCnncListExcel(searchDTO);
	}
    
	/**
	 * 제휴사별 상품속성관리 그리드 수정/저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param <VendorListResult> the generic type
	 * @param dataList [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeComAffVrscComCnnc(List<VendorGridDTO> dataList) {
	
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		String loginId = BOSecurityUtil.getLoginId();
		while(iterator.hasNext()) {
			VendorGridDTO vendorGridDTO = iterator.next();
			vendorGridDTO.getComAffVrscComCnnc().setUdterId(loginId);			
			vendorRepository.mergeComAffVrscComCnnc(vendorGridDTO);
		}
    }
	
	/**
	 * 제휴사별 상품속성 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param <VendorListResult> the generic type
	 * @param dataList [설명]
	 * @since 2015. 4. 17
	 */
	public void deleteComAffVrscComCnnc(List<VendorGridDTO> dataList) {
	
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		while(iterator.hasNext()) {
			VendorGridDTO vendorListResult = iterator.next();
			ComAffVrscComBrnd comAffVrscComBrnd = new ComAffVrscComBrnd();
			comAffVrscComBrnd.setAffVrscComId(vendorListResult.getComAffVrscComCnnc().getAffVrscComId());
			comAffVrscComBrnd.setAffComId(vendorListResult.getComAffVrscComCnnc().getAffComId());
			vendorRepository.deleteComAffVrscComBrnd(comAffVrscComBrnd);
			vendorRepository.deleteComAffVrscComCnnc(vendorListResult);
		}
    }	
	
	/**
	 * 운영자 목록.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 14
	 */
	public Page<SysAdminListResult> selectAdminList(VendorSearchDTO searchDTO) throws Exception {
		
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorRepository.selectAdminListCount(searchDTO);
		
		// 목록 조회
		List<SysAdminListResult> results = new ArrayList<SysAdminListResult>();
		if(totalRow > 0){
			results = vendorRepository.selectAdminList(searchDTO);
		}
		
		return new PageImpl<SysAdminListResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}	
	
	/**
	 * 운영자 목록(페이징X)
	 * @param vendorSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminListResult> selectAdminInfoList(VendorSearchDTO vendorSearchDTO) throws Exception {
		return vendorRepository.selectAdminInfoList(vendorSearchDTO);
	}
	
	/**
	 * as 담당자 조회
	 * @param comChrger
	 * @return
	 * @throws Exception
	 */
	public List<ComChrgerResult> selectComChrgerList(ComChrger comChrger) throws Exception {
		return vendorRepository.selectComChrgerList(comChrger);
	}


	/**
	 * 운영자 비밀번호 수정.
	 *
	 * @param dto [설명]
	 * @param loginId [설명]
	 * @param encMbrPw [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 16
	 */
	public void updateSysAdminPasswordBy(SysAdmin sysAdmin, String encMbrPw) throws Exception {
		
		sysAdmin.setUdterId(BOSecurityUtil.getLoginId());
		sysAdmin.setPw(encMbrPw);
		
		vendorRepository.updateAdminPassword(sysAdmin);
//		if(procCnt != 1){
//			throw new MemberBoException("임시 비밀번호 저장에 실패하였습니다.");
//		}
	}
	
	/**
	 * 업체 상품 옵션 엑셀 등록
	 * @param savList
	 * @throws Exception
	 */
	public void insertComGodOptList(List<VendorGoodsOptionDTO> savList) throws Exception {
		if(savList != null && savList.size() > 0){			
			vendorRepository.mergeComGodOptFreeSize((VendorGoodsOptionDTO)savList.get(0));
			for(int i=0; i<savList.size(); i++){				
				VendorGoodsOptionDTO vendorGoodsOptionDTO = (VendorGoodsOptionDTO)savList.get(i);
				vendorRepository.mergeComGodOpt(vendorGoodsOptionDTO);				
				vendorRepository.mergeComGodOptVal(vendorGoodsOptionDTO);
				vendorRepository.insertStdOpt(vendorGoodsOptionDTO);				
				vendorRepository.updateGodOptValSortSeq(vendorGoodsOptionDTO);
			}
		}
		
	}	
	
	/**
	 * 업체 상품 옵션 조회
	 * @param vendorGoodsOptionDTO
	 * @return
	 * @throws Exception
	 */
	 public List<VendorGoodsOptionResult> selectComGodOpt(VendorGoodsOptionDTO vendorGoodsOptionDTO) throws Exception{
		 return vendorRepository.selectComGodOpt(vendorGoodsOptionDTO);
	 }
	 
	 /**
	  * 업체 담당자 등록
	  * @param comChrger
	  * @throws Exception
	  */
	 public void insertComChrger(ComChrger comChrger) throws Exception{

		 vendorRepository.insertComChrger(comChrger);

	 }
	 
	 /**
	  * 업체 담당자 테이블 수정
	  * @param comChrger
	  * @throws Exception
	  */
	 public void updateComChrger(ComChrger comChrger) throws Exception{

		 vendorRepository.updateComChrger(comChrger);

	 }

	/**
	 * 업체 담당자 수정
	 * @param comChrger
	 * @throws Exception
	 */
	public void updateComLangbyChrger(ComChrger comChrger) throws Exception{

		vendorRepository.updateComLangbyChrger(comChrger);

	}

	/**
	 * 인터페이스 헤더셋팅
	 * @param systemPk
	 * @return
	 */
	private AdapterHeader setHeader(SystemPK systemPk){
		// TODO 인터페이스 헤더설정 확인필요!!
		// ERP 인터페이스 헤더설정
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");
		
		return adapterHeader;
	}
	 
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 업체 리스트 수정
	 * @param updateList
	 * @throws Exception
	 */
	public void updateVendorList(List<VendorListResult> updateList) throws Exception{

		for(VendorListResult vendor : updateList){
			Com com = vendor.getCom();
			vendorRepository.updateCom(com);
		}

	};

}
