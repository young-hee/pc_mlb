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
 * @since       2015. 4. 28       
 */
package com.plgrim.ncp.biz.vendor.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcExcp;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;
import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvCstPlc;
import com.plgrim.ncp.base.repository.com.ComDmstcDlvCstPlcRepository;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.repository.ComOvseaDlvCstPlcEntityRepository;
import com.plgrim.ncp.biz.vendor.repository.VendorDmstcDlvCstPlcRepository;
import com.plgrim.ncp.biz.vendor.result.ComDmstcDlvCstPlcResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;

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
@Service
public class ComDmstcDlvCstPlcService  extends AbstractService {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	
	/**
	 * 업체 Repository
	 */
	@Autowired
	private VendorDmstcDlvCstPlcRepository vendorDmstcDlvCstPlcRepository;
	
	@Autowired
	private ComDmstcDlvCstPlcRepository comDmstcDlvCstPlcRepository;
	
	@Autowired
	private ComOvseaDlvCstPlcEntityRepository comOvseaDlvCstPlcRepository;

	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;	
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
	 * 배송비 정책 조회 콤보
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcCombo(VendorSearchDTO searchDTO) throws Exception {
		
		// 업체 상세건 조회
		return vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcCombo(searchDTO);
	}
	
	/**
	 * 배송비 정책 리스트 조회
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcList(VendorSearchDTO searchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcListCount(searchDTO);
		
		// 목록 조회
		List<ComDmstcDlvCstPlcResult> results = new ArrayList<ComDmstcDlvCstPlcResult>();
		if(totalRow > 0){
			results = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcList(searchDTO);
		}
		
		return new PageImpl<ComDmstcDlvCstPlcResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	}

	/**
	 * 배송비 정책 건수 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public long selectComDmstcDlvCstPlcListCount(VendorSearchDTO searchDTO) throws Exception {
		return vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcListCount(searchDTO);
	}
	

	/**
	 * 배송비 정책 상세 조회.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public ComDmstcDlvCstPlcResult selectComDmstcDlvCstPlc(VendorSearchDTO searchDTO) throws Exception {
		
		// 업체 상세건 조회
		ComDmstcDlvCstPlcResult results = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlc(searchDTO);
		
		 String telNo = "";
		 String cellNo = "";
		 if(results!=null){
			 telNo = StringService.nvl(results.getChrgerTelNationNo(),"")+"-"
					 + StringService.nvl(results.getChrgerTelAreaNo(),"") +"-"
					 + StringService.nvl(results.getChrgerTelTlofNo(),"") +"-"
					 + StringService.nvl(results.getChrgerTelTlofWthnNo(),"") ;
			 
			 cellNo = StringService.nvl(results.getChrgerMobilNationNo(),"")+"-"
					 + StringService.nvl(results.getChrgerMobilAreaNo(),"") +"-"
					 + StringService.nvl(results.getChrgerMobilTlofNo(),"") +"-"
					 + StringService.nvl(results.getChrgerMobilTlofWthnNo(),"") ;
			 results.setChrgerTelNo(telNo);
			 results.setChrgerMobilNo(cellNo);	
		 }	 
		 

		 
		 telNo = "";
		 cellNo = "";
		 if(results!=null){
			 telNo = StringService.nvl(results.getRetChrgerTelNationNo(),"")+"-"
					 + StringService.nvl(results.getRetChrgerTelAreaNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerTelTlofNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerTelTlofWthnNo(),"") ;
			 
			 cellNo = StringService.nvl(results.getRetChrgerMobilNationNo(),"")+"-"
					 + StringService.nvl(results.getRetChrgerMobilAreaNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerMobilTlofNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerMobilTlofWthnNo(),"") ;
			 
		 
			 results.setRetChrgerTelNo(telNo);
			 results.setRetChrgerMobilNo(cellNo);	
		 }
		return results;

	}
	
	//배송비 정책 상세 조회. (CS 에서 업체 기본 배송 정책 조회 용)
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcFromCs(VendorSearchDTO searchDTO) throws Exception {

		// 업체 상세건 조회
		List<ComDmstcDlvCstPlcResult> resultss = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcFromCs(searchDTO);

		for (ComDmstcDlvCstPlcResult results : resultss) {
			
		
		 String telNo = "";
		 String cellNo = "";
		 if(results!=null){
			 telNo = StringService.nvl(results.getChrgerTelNationNo(),"")+"-"
					 + StringService.nvl(results.getChrgerTelAreaNo(),"") +"-"
					 + StringService.nvl(results.getChrgerTelTlofNo(),"") +"-"
					 + StringService.nvl(results.getChrgerTelTlofWthnNo(),"") ;

			 cellNo = StringService.nvl(results.getChrgerMobilNationNo(),"")+"-"
					 + StringService.nvl(results.getChrgerMobilAreaNo(),"") +"-"
					 + StringService.nvl(results.getChrgerMobilTlofNo(),"") +"-"
					 + StringService.nvl(results.getChrgerMobilTlofWthnNo(),"") ;
			 results.setChrgerTelNo(telNo);
			 results.setChrgerMobilNo(cellNo);
		 }



		 telNo = "";
		 cellNo = "";
		 if(results!=null){
			 telNo = StringService.nvl(results.getRetChrgerTelNationNo(),"")+"-"
					 + StringService.nvl(results.getRetChrgerTelAreaNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerTelTlofNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerTelTlofWthnNo(),"") ;

			 cellNo = StringService.nvl(results.getRetChrgerMobilNationNo(),"")+"-"
					 + StringService.nvl(results.getRetChrgerMobilAreaNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerMobilTlofNo(),"") +"-"
					 + StringService.nvl(results.getRetChrgerMobilTlofWthnNo(),"") ;


			 results.setRetChrgerTelNo(telNo);
			 results.setRetChrgerMobilNo(cellNo);
		 }
		 
		}
		return resultss;

	}

	/**
	 * 배송비 정책 수정.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public int updateComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
		comDmstcDlvCstPlc.setUdterId(BOSecurityUtil.getLoginId());
		return vendorDmstcDlvCstPlcRepository.updateComDmstcDlvCstPlc(comDmstcDlvCstPlc);
	}
	
	/**
	 * 배송비정책 기본배송지 수정
	 * @param comDmstcDlvCstPlc
	 * @throws Exception
	 */
	public void updateComDmstcDlvCstPlcBaseDlv(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{

		comDmstcDlvCstPlc.setUdterId(BOSecurityUtil.getLoginId());
		ComDmstcDlvCstPlc param = new ComDmstcDlvCstPlc();
		param.setUdterId(comDmstcDlvCstPlc.getUdterId());
		param.setComId(comDmstcDlvCstPlc.getComId());
		param.setOvseaDlvDmstcDlvCstPlcYn(comDmstcDlvCstPlc.getOvseaDlvDmstcDlvCstPlcYn());
		param.setBaseDlvCstPlcYn("N");
		vendorDmstcDlvCstPlcRepository.updateComDmstcDlvCstPlcBaseDlv(param);

		comDmstcDlvCstPlc.setBaseDlvCstPlcYn("Y");
		vendorDmstcDlvCstPlcRepository.updateComDmstcDlvCstPlcBaseDlv(comDmstcDlvCstPlc);//기본배송지 등록
		if(!"Y".equals(comDmstcDlvCstPlc.getOvseaDlvDmstcDlvCstPlcYn()))
		{
		vendorDmstcDlvCstPlcRepository.updateGodDlvCstPlcSn(comDmstcDlvCstPlc);//상품 배송정책번호 수정
		}

		ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist = new ComDmstcDlvCstPlcHist();
		comDmstcDlvCstPlcHist.setDmstcDlvCstPlcSn(comDmstcDlvCstPlc.getDmstcDlvCstPlcSn());
		vendorDmstcDlvCstPlcRepository.insertComDmstcDlvCstPlcHist(comDmstcDlvCstPlcHist);
	}

	/**
	 * 배송비 정책 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	public void insertComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
		comDmstcDlvCstPlc.setUdterId(BOSecurityUtil.getLoginId());
		vendorDmstcDlvCstPlcRepository.insertComDmstcDlvCstPlc(comDmstcDlvCstPlc);
	}	
	
	/**
	 * 배송비 정책 이력저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlcHist [설명]
	 * @since 2015. 4. 28
	 */
	public void insertComDmstcDlvCstPlcHist(ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) {
		vendorDmstcDlvCstPlcRepository.insertComDmstcDlvCstPlcHist(comDmstcDlvCstPlcHist);
	}

	/**
	 * 도서산간배송비 조회.
	 *
	 * @param vendorSearchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcExcp(VendorSearchDTO searchDTO) throws Exception {
		RepositoryHelper.makePageEntityIndex(searchDTO, searchDTO.getPageParam());  // 페이지 설정

		// 목록 건수 조회
		long totalRow = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcExcpCount(searchDTO);
		
		// 목록 조회
		List<ComDmstcDlvCstPlcResult> results = new ArrayList<ComDmstcDlvCstPlcResult>();
		if(totalRow > 0){
			results = vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcExcp(searchDTO);
		}
		
		return new PageImpl<ComDmstcDlvCstPlcResult>(results, searchDTO.getPageParam().getPageable(), totalRow);
	} 	
	
    /**
     * 도서산간배송비 조회 엑셀.
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
    public List<Map<String, Object>> selectComDmstcDlvCstPlcExcpExcel(VendorSearchDTO searchDTO) throws Exception {
		return vendorDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlcExcpExcel(searchDTO);
	}		
	
	/**
	 * 도서산간배송비 저장.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void mergeDlvCstPlcExcp(List<VendorGridDTO> dataList) {
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		while(iterator.hasNext()) {

			VendorGridDTO vendorGridDTO = iterator.next();
			vendorGridDTO.getComDmstcDlvCstPlcExcp().setUdterId(BOSecurityUtil.getLoginId());			
			vendorDmstcDlvCstPlcRepository.mergeDlvCstPlcExcp(vendorGridDTO);
		}		

	}  
	
	/**
	 * 도서산간배송비 삭제.
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	public void deleteDlvCstPlcExcp(List<VendorGridDTO> dataList) {
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		while(iterator.hasNext()) {

			vendorDmstcDlvCstPlcRepository.deleteDlvCstPlcExcp(iterator.next().getComDmstcDlvCstPlcExcp());
		}		

	}
	
	/**
	 * 업체 국내 배송비 정책 삭제
	 * @param dataList
	 */
	public void deleteComDmstcDlvCstPlc(List<VendorGridDTO> dataList) {
		Iterator<VendorGridDTO> iterator = dataList.iterator();
		while(iterator.hasNext()) {
			Long dmstcDlvCstPlcSn = iterator.next().getComDmstcDlvCstPlc().getDmstcDlvCstPlcSn();
			
			//ComCrncyDmstcDlvCstHist comCrncyDmstcDlvCstHist = new ComCrncyDmstcDlvCstHist();
			//ComCrncyDmstcDlvCst comCrncyDmstcDlvCst = new ComCrncyDmstcDlvCst();
			ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist = new ComDmstcDlvCstPlcHist();
			ComDmstcDlvCstPlcExcp comDmstcDlvCstPlcExcp = new ComDmstcDlvCstPlcExcp();
			ComDmstcDlvCstPlc comDmstcDlvCstPlc = new ComDmstcDlvCstPlc();
			
			//comCrncyDmstcDlvCstHist.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
			//comCrncyDmstcDlvCst.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
			comDmstcDlvCstPlcHist.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
			comDmstcDlvCstPlcExcp.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);
			comDmstcDlvCstPlc.setDmstcDlvCstPlcSn(dmstcDlvCstPlcSn);

			//테이블 삭제 처리됨.
//			//업체 통화 국내 배송비 이력 삭제
//			vendorDmstcDlvCstPlcRepository.deleteComCrncyDmstcDlvCstHist(comCrncyDmstcDlvCstHist);
//
//			//업체 통화 국내 배송비 삭제
//			vendorDmstcDlvCstPlcRepository.deleteComCrncyDmstcDlvCst(comCrncyDmstcDlvCst);
			//테이블 삭제 처리됨.
			
			//업체 국내 배송비 정책 이력 삭제
			vendorDmstcDlvCstPlcRepository.deleteComDmstcDlvCstPlcHist(comDmstcDlvCstPlcHist);
			
			//업체 국내 배송비 정책 예외 삭제
			vendorDmstcDlvCstPlcRepository.deleteComDmstcDlvCstPlcExcp(comDmstcDlvCstPlcExcp);
			
			//업체 국내 배송비 정책 삭제
			vendorDmstcDlvCstPlcRepository.deleteComDmstcDlvCstPlc(comDmstcDlvCstPlc);
		}		

	}
	
	
	
	/************************************External Layer Start***************************************************************/
	/**
	 * base 에 있는 업체 국내 배송비 정책 테이블을 일련번호로 조회하는 repo를 호출
	 * @param comDmstcDlvCstPlc
	 * @return
	 * @throws Exception 
	 */
    public ComDmstcDlvCstPlc selectComDmstcDlvCstPlc(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception {
		return comDmstcDlvCstPlcRepository.selectComDmstcDlvCstPlc(comDmstcDlvCstPlc);
	}
    
    /**
     * base 에 있는 업체 해외 배송비 정책 테이블을 일련번호로 조회하는 repo를 호출
     * @param comOvseaDlvCstPlc
     * @return
     * @throws Exception
     */
    public ComOvseaDlvCstPlc selectComOvseaDlvCstPlc(ComOvseaDlvCstPlc comOvseaDlvCstPlc) throws Exception {
		return comOvseaDlvCstPlcRepository.selectComOvseaDlvCstPlc(comOvseaDlvCstPlc);
	}
    
    /**
	 * 배송사 Url
	 * @param searchDTO
	 * @param request
	 * @return
	 * @throws Exception
	 */
    public String getDlvComUrl(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
    	//return comOvseaDlvCstPlcRepository.getDlvComUrl(comDmstcDlvCstPlc);
    	return "";
    }

    public String selectBaseDlvMthdDscr() throws Exception {
    	return vendorDmstcDlvCstPlcRepository.selectBaseDlvMthdDscr();
	}
    /************************************External Layer End***************************************************************/
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
