package com.plgrim.ncp.biz.vendor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.com.ComReqstAtchmnfl;
import com.plgrim.ncp.base.repository.com.ComReqstAtchmnflRepository;
import com.plgrim.ncp.base.repository.com.ComReqstRepository;
import com.plgrim.ncp.biz.vendor.data.VendorBrndSearchDTO;
import com.plgrim.ncp.biz.vendor.data.VendorReqstDTO;
import com.plgrim.ncp.biz.vendor.data.VendorReqstRegDTO;
import com.plgrim.ncp.biz.vendor.exception.VendorRequestNotExistUpdateException;
import com.plgrim.ncp.biz.vendor.repository.VendorRequestRepository;
import com.plgrim.ncp.biz.vendor.result.VendorReqstResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;
import com.google.common.collect.Maps;

@Service
public class VendorRequestService extends AbstractService{

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	VendorRequestRepository vendorRequestRepository;
	
	@Autowired
	ComReqstRepository comReqstRepository;
	
	@Autowired
	ComReqstAtchmnflRepository comReqstAtchmnflRepository;
	
	@Autowired
    IdGenService idGenService;
	
	 @Autowired
	 @Qualifier("sessionTemplate1")
	 private SqlSession session1;
	 
	 private final String CONFIG_VENDOR_UPLOAD_FILE_PATH = "ncp_web_fo.image.vendor.upload.path";

	 private final String CONFIG_VENDOR_UPLOAD_HTTP_PATH = "ncp_web_fo.image.vendor.http.path";

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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */
	 
	 /**
	  *	 입점 신청 목록 조회 
	  * 
	 * @param vendorReqstDTO
	 * @param pageParam
	 * @return
	 * @throws Exception
	 * @since 2015. 11. 13.
	  */
	public Page<VendorReqstResult> selectVendorRequestList(VendorReqstDTO vendorReqstDTO, PageParam pageParam) throws Exception{
		return vendorRequestRepository.selectVendorRequestList(vendorReqstDTO, pageParam);
	}
	
	/**
	 *	입점 신청 수정 
	 * 
	* @param vendorReqstList
	* @throws Exception
	* @since 2015. 11. 13.
	 */
	public void updateVendorRequest(List<VendorReqstDTO> vendorReqstList) throws Exception {
		int result = 0; 
		String loginId = BOSecurityUtil.getLoginId();
		if(vendorReqstList != null && vendorReqstList.size() > 0) {
			for(VendorReqstDTO vr : vendorReqstList){
				vr.getComReqst().setLoginId(loginId);
				//처리 상태가 '처리완료' 일때
				if(vr.getComReqst().getComReqstPrcsStatCd().equals("PRCS_COMPT")){
					VendorReqstResult rs = vendorRequestRepository.getVendorRequest(vr);
					if(!rs.getComReqst().getComReqstPrcsStatCd().equals("PRCS_COMPT")){
						vr.getComReqst().setPrcsComptAdminId(loginId);
						vendorRequestRepository.updateVendorPrcsCompt(vr);
					}
				}
				result += vendorRequestRepository.updateVendorRequest(vr);
			}
			if(result < 0){
				throw new VendorRequestNotExistUpdateException(null);
			}
		}
	}
	
	/**
	 *	입점 신청글 상세 조회 
	 * 
	* @param vendorReqstDTO
	* @return
	* @throws Exception
	* @since 2015. 11. 13.
	 */
	public VendorReqstResult getVendorRequest(VendorReqstDTO vendorReqstDTO) throws Exception {
		return vendorRequestRepository.getVendorRequest(vendorReqstDTO);
	}
	
	/**
	 * 입점 신청 등록
	 * 
	* @param vendorReqstRegDTO
	* @throws Exception
	* @since 2015. 11. 13.
	 */
	public void insertVendorRequest(VendorReqstRegDTO vendorReqstRegDTO) throws Exception{
		Long comReqstSn = Long.parseLong(idGenService.generateDBSequence(session1, "SQ_COM_REQST", DatabaseType.ORACLE) + "");
//		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();
//        String mbrId = userDetail.getMbr().getMbrId();
//		
//		vendorReqstRegDTO.setLoginId(mbrId);
		
		// 입점신청 일련번호
		vendorReqstRegDTO.getComReqst().setComReqstSn(comReqstSn);
		
		// 신청자 연락처 
		vendorReqstRegDTO.getComReqst().setApplcntTelNationNo("82");
		
		String[] tel = vendorReqstRegDTO.getApplcntTel().split("-");

		vendorReqstRegDTO.getComReqst().setApplcntTelAreaNo(tel[0]);
		vendorReqstRegDTO.getComReqst().setApplcntTelTlofNo(tel[1]);
		vendorReqstRegDTO.getComReqst().setApplcntTelTlofWthnNo(tel[2]);
		
		// 사업자 등록번호
		String brn = vendorReqstRegDTO.getComReqst().getBmanRegNo();
		vendorReqstRegDTO.getComReqst().setBmanRegNo(brn.substring(0,2)+"-"+brn.substring(3,4)+"-"+brn.substring(5));
		
		// 처리 상태 df. 처리대기
		vendorReqstRegDTO.getComReqst().setComReqstPrcsStatCd("PRCS_WAIT");
		
		comReqstRepository.insertComReqst(vendorReqstRegDTO.getComReqst());
		
		// 입점 신청 약관 동의 정보
		vendorReqstRegDTO.getComReqstStplatAgr().setComReqstSn(comReqstSn);
		vendorReqstRegDTO.getComReqstStplatAgr().setStplatCd("PSNL_INFO_COLCT_USEF_AGR");
		
//		comReqstStplatAgr.setRegtrId(mbrId);
//		comReqstStplatAgr.setUdterId(mbrId);
		
		// 입점 신청 첨부 파일 저장
		if(vendorReqstRegDTO.getUpload() != null && vendorReqstRegDTO.getUpload().size() > 0) {
			saveFile(vendorReqstRegDTO);
		}
	}
	
	/**
     * 첨부파일 저장.
     *
     * @param session1 [설명]
     * @param cssNoticeDTO [설명]
     * @throws Exception the exception
     * @since 2015. 8. 31
     */
    private void saveFile(VendorReqstRegDTO vendorReqstRegDTO) throws Exception {
    	
        FileUploadResult fileUploadResult = uploadFiles(vendorReqstRegDTO.getUpload());
        BigDecimal d;
        //	첨부 파일 순번 조건절 세팅
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("COM_REQST_SN", vendorReqstRegDTO.getComReqst().getComReqstSn());
        int result = 0;
        
        if (fileUploadResult != null && fileUploadResult.getFileCnt() > 0) {
            for (FileUploadInfo item : fileUploadResult.getRows()) {
                ComReqstAtchmnfl comReqstAtchmnfl = new ComReqstAtchmnfl();

                // 첨부파일 순번 세팅
                int atchmnflTurn = idGenService.generateDBOrder(session1, "COM_REQST_ATCHMNFL", "ATCHMNFL_TURN", conditions, DatabaseType.ORACLE);
                comReqstAtchmnfl.setAtchmnflTurn(atchmnflTurn);

                // 첨부파일이 들어간 입점 신청글 번호 세팅
                comReqstAtchmnfl.setComReqstSn(vendorReqstRegDTO.getComReqst().getComReqstSn());

                // 첨부파일 등록/수정자 세팅
                comReqstAtchmnfl.setRegtrId(vendorReqstRegDTO.getComReqst().getRegtrId());
                comReqstAtchmnfl.setUdterId(vendorReqstRegDTO.getComReqst().getUdterId());

                // 첨부파일 정보 세팅
                comReqstAtchmnfl.setAtchFileUrl(getConfigService().getProperty(CONFIG_VENDOR_UPLOAD_HTTP_PATH));
                comReqstAtchmnfl.setAtchFileNm(item.getOrgFileName() + "." + item.getExtension());
                
                comReqstAtchmnfl.setAtchFileCpcty(d = new BigDecimal(item.getFileSize()));
                // 첨부파일 DB 등록
                comReqstAtchmnflRepository.insertComReqstAtchmnfl(comReqstAtchmnfl);
            }
        }
    }
	
	 /**
     * 첨부파일 등록.
     *
     * @param files [설명]
     * @return File upload result [설명]
     * @throws Exception the exception
     * @since 2015. 8. 7
     */
    public FileUploadResult uploadFiles(List<MultipartFile> files) throws Exception {
        // 전체 업로드 확장자 체크를 한다.
        // excludeExtensions 포함된 데이터는 업로드 할 수 없다.
        String[] excludeExtensions = { "jsp", "php", "exe", "bat" };

        // 시스템 파일명을 자동으로 부여하여 업로드한다.
        FileUploadResult fileUploadResult = getIoService().fileUploadOverWrite(files,
                getConfigService().getProperty(CONFIG_VENDOR_UPLOAD_FILE_PATH), excludeExtensions);

        return fileUploadResult;
    }
}
