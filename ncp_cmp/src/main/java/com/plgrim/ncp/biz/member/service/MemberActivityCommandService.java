package com.plgrim.ncp.biz.member.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoMtmInqAtchFile;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBukmkBrnd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfcDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGod;
import com.plgrim.ncp.base.repository.god.GodGodEvlAtchFileRepository;
import com.plgrim.ncp.base.repository.god.GodGodEvlRepository;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.base.repository.mbr.MbrBukmkBrndRepository;
import com.plgrim.ncp.base.repository.mbr.MbrSizeClfcDetailRepository;
import com.plgrim.ncp.base.repository.mbr.MbrSizeClfcRepository;
import com.plgrim.ncp.biz.goods.data.GoodsReviewDTO;
import com.plgrim.ncp.biz.helpdesk.repository.HelpdeskRepository;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.biz.member.repository.MemberActivityCommandRepository;
import com.plgrim.ncp.biz.member.repository.MemberBaseSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberBenefitSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberOrderSelectRepository;
import com.plgrim.ncp.biz.member.repository.MemberPersonalInfoSelectRepository;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.FileUploadInfo;
import com.plgrim.ncp.framework.data.FileUploadResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.enums.DatabaseType;

import lombok.extern.slf4j.Slf4j;

/**
 * 회원활동정보 command service
 */
@Service
@Slf4j
public class MemberActivityCommandService extends AbstractService {
 
	@Autowired
	private MemberBenefitCommandService memberBenefitCommandService;

	@Autowired
	private MemberActivityCommandRepository memberActivityCommandRepository;
	
	@Autowired
	private MemberOrderSelectRepository memberOrderSelectRepository;
		
	@Autowired
	private GodGodEvlAtchFileRepository godGodEvlAtchFileRepository;
	
	@Autowired
	private GodGodEvlRepository godGodEvlRepository;
	
	@Autowired
	private MbrBukmkBrndRepository mbrBukmkBrndRepository;
	
	@Autowired
	private MemberPersonalInfoSelectRepository memberPersonalInfoSelectRepository;
	
	@Autowired
	private MbrSizeClfcRepository mbrSizeClfcRepository;
	
	@Autowired
	private MbrSizeClfcDetailRepository mbrSizeClfcDetailRepository;
	
	@Autowired
	MemberBenefitSelectRepository memberBenefitSelectRepository;
	
	@Autowired
	MemberBaseSelectRepository memberBaseSelectRepository;
	
	@Autowired
	HelpdeskRepository helpdeskRepository;
	
	@Autowired
	@Qualifier("sessionTemplate1")
	SqlSession sqlSession1;

	public void updateMyInquiry(MypageMtmFoDTO mypageMtmFoDTO)  {

		if (memberActivityCommandRepository.updateMyInquiry(mypageMtmFoDTO) < 1) {
			throw new RuntimeException("존재하지 않는 글이거나 수정 권한이 없는 글 입니다.");
		}
		;

		Long mtmInqSn = Long.parseLong(mypageMtmFoDTO.getSrchMtmInqSn());
		mypageMtmFoDTO.getCsoMtmInq().setMtmInqSn(mtmInqSn);
		memberActivityCommandRepository.deleteMyOrdGodInquiry(mypageMtmFoDTO);

		if (mypageMtmFoDTO.getOrdGodList() != null) {
			String ordNo = mypageMtmFoDTO.getOrdGodList().get(0).getOrdNo();
			List<OrdGod> ordGodList = memberOrderSelectRepository.selectOrdGodNm(ordNo);

			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MTM_INQ_SN", mtmInqSn);

			for (OrdGod ordGod : mypageMtmFoDTO.getOrdGodList()) {
				if (ordGod.getOrdNo() != null) {
					mypageMtmFoDTO.setMtmInqOrdGodTurn(getIdGenService().generateDBOrder(sqlSession1,
							"CSO_MTM_INQ_ORD_GOD", "MTM_INQ_ORD_GOD_TURN", conditions, DatabaseType.ORACLE));
					mypageMtmFoDTO.setOrdGod(ordGod);
					if (ordGodList != null) {
						for (int i = 0; i < ordGodList.size(); i++) {
							if (ordGod.getOrdGodTurn().equals(ordGodList.get(i).getOrdGodTurn())) {
								ordGod.setGodNm(ordGodList.get(i).getGodNm());
								break;
							}

						}
					}
				}
				memberActivityCommandRepository.insertCsInquiryOrdGod(mypageMtmFoDTO);
			}

		}
	}
	
	public void updateMypageInquiry(MypageMtmFoDTO mypageMtmFoDTO)  {

		if (memberActivityCommandRepository.updateMyInquiry(mypageMtmFoDTO) < 1) {
			throw new RuntimeException("존재하지 않는 글이거나 수정 권한이 없는 글 입니다.");
		}
		;

		
		Long mtmInqSn = Long.parseLong(mypageMtmFoDTO.getSrchMtmInqSn());
		
		mypageMtmFoDTO.getCsoMtmInq().setMtmInqSn(mtmInqSn);
		mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqSn(mtmInqSn);

		//1:1문의 첨부파일 삭제
		memberActivityCommandRepository.deleteMyFileInquiry(mypageMtmFoDTO);

		//1:1문의 첨부파일 등록(첨부파일이 있는지 확인하고 넣어주어야함)
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("MTM_INQ_SN",mtmInqSn);
		
		if(mypageMtmFoDTO.getCsoMtmInqAtchFile().getMtmInqAtchFileNm() != null && mypageMtmFoDTO.getCsoMtmInqAtchFile().getMtmInqAtchFileNm() != "" ){
			mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ATCH_FILE","MTM_INQ_ATCH_FILE_TURN", conditions, DatabaseType.ORACLE));
			memberActivityCommandRepository.insertCsInquiryFile(mypageMtmFoDTO);
		}
		

		memberActivityCommandRepository.deleteMyOrdGodInquiry(mypageMtmFoDTO);

		if (mypageMtmFoDTO.getOrdGod() != null) {
			String ordNo = mypageMtmFoDTO.getOrdGod().getOrdNo();
			
			List<OrdGod> ordGodList = memberOrderSelectRepository.selectOrdGodNm(ordNo);

			/*Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MTM_INQ_SN", mtmInqSn);*/

			for (OrdGod ordGod : mypageMtmFoDTO.getOrdGodList()) {
				if (ordGod.getOrdNo() != null) {
					mypageMtmFoDTO.setMtmInqOrdGodTurn(getIdGenService().generateDBOrder(sqlSession1,
							"CSO_MTM_INQ_ORD_GOD", "MTM_INQ_ORD_GOD_TURN", conditions, DatabaseType.ORACLE));
					mypageMtmFoDTO.setOrdGod(ordGod);
					if (ordGodList != null) {
						for (int i = 0; i < ordGodList.size(); i++) {
							if (ordGod.getOrdGodTurn().equals(ordGodList.get(i).getOrdGodTurn())) {
								ordGod.setGodNm(ordGodList.get(i).getGodNm());
								break;
							}

						}
					}
				}
				memberActivityCommandRepository.insertCsInquiryOrdGod(mypageMtmFoDTO);
			}
			
		}
	}

	public void updateMyFileInquiry(MypageMtmFoDTO mypageMtmFoDTO, List files)  {
		String[] excludeExtensions = { "asp", "js", "jsp", "php", "exe", "bat" };

		getIoService().availableUploadExcludeExtension(files, excludeExtensions);
		String timeStamp = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
		// timeStamp = timeStamp.substring(2);
		String path = timeStamp + "/";
		FileUploadResult fileUploadResult = getIoService().fileUploadAutoFileName(files,
				getConfigService().getProperty("ncp_base.spring.mvc.upload.inquiry") + path, excludeExtensions);
		List<FileUploadInfo> infoList = fileUploadResult.getRows();
		Map<String, Object> conditions;

		if (mypageMtmFoDTO.getDeleteFileNm() != null && mypageMtmFoDTO.getDeleteFileNm().size() > 0) {
			CsoMtmInqAtchFile delAtchFile = new CsoMtmInqAtchFile();
			delAtchFile.setMtmInqSn(mypageMtmFoDTO.getCsoMtmInq().getMtmInqSn());
			/*
			 * delAtchFile.setMtmInqAtchFileTurn(mypageMtmFoDTO.
			 * getCsoMtmInqAtchFile().getMtmInqAtchFileTurn());
			 */
			mypageMtmFoDTO.setCsoMtmInqAtchFile(delAtchFile);
			memberActivityCommandRepository.deleteMyFileInquiry(mypageMtmFoDTO);
		}

		for (FileUploadInfo uploadInfo : infoList) {

			CsoMtmInqAtchFile atchFile = new CsoMtmInqAtchFile();
			conditions = Maps.newHashMap();
			conditions.put("MTM_INQ_SN", mypageMtmFoDTO.getCsoMtmInq().getMtmInqSn());
			int atchFileTurn = getIdGenService().generateDBOrder(sqlSession1, "CSO_MTM_INQ_ATCH_FILE",
					"MTM_INQ_ATCH_FILE_TURN", conditions, DatabaseType.ORACLE);
			atchFile.setMtmInqSn(mypageMtmFoDTO.getCsoMtmInq().getMtmInqSn());
			atchFile.setMtmInqAtchFileTurn(atchFileTurn);
			atchFile.setMtmInqAtchFileUrl("/inquiry/" + path + uploadInfo.getFileName());
			atchFile.setMtmInqAtchFileNm(uploadInfo.getOrgFileName() + "." + uploadInfo.getExtension());
			atchFile.setMtmInqAtchFileCpcty(BigDecimal.valueOf(uploadInfo.getFileSize()));
			atchFile.setUdterId(mypageMtmFoDTO.getMbrId());
			memberActivityCommandRepository.insertCsoMtmInqAtchFile(atchFile);

		}
	}

	public void deleteMyInquiry(MypageMtmFoDTO mypageMtmFoDTO)  {
		memberActivityCommandRepository.deleteMyInquiry(mypageMtmFoDTO);
	}

	public void updateMyInqAnsEvl(MypageMtmFoDTO mypageMtmFoDTO)  {
		memberActivityCommandRepository.updateMyInqAnsEvl(mypageMtmFoDTO);
	}

	/** Qna 삭제 */
	public void deleteMyQna(MypageQnaFoDTO mypageQnaFoDTO)  {
		memberActivityCommandRepository.deleteMyQna(mypageQnaFoDTO);
	}

	/** Qna 변경 */
	public void updateMyQna(MypageQnaFoDTO mypageQnaFoDTO)  {
		memberActivityCommandRepository.updateMyQna(mypageQnaFoDTO);
	}

	public boolean saveMemberSize(String mbrNo, String mbrSizeNm, String height, String weight, String waist, String body) throws Exception {
		boolean isPointEarned = false;
		
		int mbrSizeTurn = 0;
		
		MbrSizeClfc mbrSizeSearch = new MbrSizeClfc();
		mbrSizeSearch.setMbrNo(mbrNo);
		mbrSizeSearch.setMbrSizeClfcCd("ALL_BASE_SIZE");
		List<MbrSizeClfc> baseSizeInfo = memberPersonalInfoSelectRepository.selectMbrSizeClfcInfo(mbrSizeSearch);
		
		if (baseSizeInfo != null && baseSizeInfo.size() > 0) {	// 전체상품 기본사이즈가 없다는 뜻은 사이즈정보가 아예 없다는 뜻.
			mbrSizeTurn = baseSizeInfo.get(0).getMbrSizeTurn();
		} else {
			mbrSizeTurn = 1;
		}
		
		if (mbrSizeNm != null && mbrSizeNm != "") {
			
			MbrSizeClfc mbrSizeClfc = new MbrSizeClfc();
			mbrSizeClfc.setMbrNo(mbrNo);
			mbrSizeClfc.setMbrSizeTurn(mbrSizeTurn);
			mbrSizeClfc.setMbrSizeClfcCd("ALL_BASE_SIZE");
			mbrSizeClfc.setMbrSizeNm(mbrSizeNm);
			mbrSizeClfc.setRegtrId(mbrNo);
			mbrSizeClfc.setUdterId(mbrNo);
		
			MbrSizeClfc mbrSizeClfcResult = mbrSizeClfcRepository.selectMbrSizeClfc(mbrSizeClfc);
			
			if (mbrSizeClfcResult == null) {
				mbrSizeClfcRepository.insertMbrSizeClfc(mbrSizeClfc);
			} else {
				mbrSizeClfcRepository.updateMbrSizeClfc(mbrSizeClfc);
			}
			
		}
		
		if (height != null && height != "") {
			
			MbrSizeClfcDetail mbrSizeClfcDetail = new MbrSizeClfcDetail();
			mbrSizeClfcDetail.setMbrNo(mbrNo);
			mbrSizeClfcDetail.setMbrSizeTurn(mbrSizeTurn);
			mbrSizeClfcDetail.setMbrSizeCd("HEIGHT");
			mbrSizeClfcDetail.setSizeVal(height);
			mbrSizeClfcDetail.setSizeUnitCd("CM");
			mbrSizeClfcDetail.setRegtrId(mbrNo);
			mbrSizeClfcDetail.setUdterId(mbrNo);
			
			MbrSizeClfcDetail mbrSizeClfcDetailResult = mbrSizeClfcDetailRepository.selectMbrSizeClfcDetail(mbrSizeClfcDetail);
			
			if (mbrSizeClfcDetailResult == null) {
				mbrSizeClfcDetailRepository.insertMbrSizeClfcDetail(mbrSizeClfcDetail);
			} else {
				mbrSizeClfcDetailRepository.insertMbrSizeClfcDetail(mbrSizeClfcDetail);
			}
		}

		if (weight != null && weight != "") {
			MbrSizeClfcDetail mbrSizeClfcDetail = new MbrSizeClfcDetail();
			mbrSizeClfcDetail.setMbrNo(mbrNo);
			mbrSizeClfcDetail.setMbrSizeTurn(mbrSizeTurn);
			mbrSizeClfcDetail.setMbrSizeCd("BD_WT");
			mbrSizeClfcDetail.setSizeUnitCd("KG");
			mbrSizeClfcDetail.setSizeVal(weight);
			mbrSizeClfcDetail.setRegtrId(mbrNo);
			mbrSizeClfcDetail.setUdterId(mbrNo);
			
			MbrSizeClfcDetail mbrSizeClfcDetailResult = mbrSizeClfcDetailRepository.selectMbrSizeClfcDetail(mbrSizeClfcDetail);
			
			if (mbrSizeClfcDetailResult == null) {
				mbrSizeClfcDetailRepository.insertMbrSizeClfcDetail(mbrSizeClfcDetail);
			} else {
				mbrSizeClfcDetailRepository.updateMbrSizeClfcDetail(mbrSizeClfcDetail);
			}
		}
		
		if (waist != null && waist != null) {
			MbrSizeClfcDetail mbrSizeClfcDetail = new MbrSizeClfcDetail();
			mbrSizeClfcDetail.setMbrNo(mbrNo);
			mbrSizeClfcDetail.setMbrSizeTurn(mbrSizeTurn);
			mbrSizeClfcDetail.setMbrSizeCd("WAIST_GRTH");
			mbrSizeClfcDetail.setSizeUnitCd("INCH");
			mbrSizeClfcDetail.setSizeVal(waist);
			mbrSizeClfcDetail.setRegtrId(mbrNo);
			mbrSizeClfcDetail.setUdterId(mbrNo);
			
			MbrSizeClfcDetail mbrSizeClfcDetailResult = mbrSizeClfcDetailRepository.selectMbrSizeClfcDetail(mbrSizeClfcDetail);
			
			if (mbrSizeClfcDetailResult == null) {
				mbrSizeClfcDetailRepository.insertMbrSizeClfcDetail(mbrSizeClfcDetail);
			} else {
				mbrSizeClfcDetailRepository.updateMbrSizeClfcDetail(mbrSizeClfcDetail);
			}
		}

		if (body != null && body != "") {
			MbrSizeClfcDetail mbrSizeClfcDetail = new MbrSizeClfcDetail();
			mbrSizeClfcDetail.setMbrNo(mbrNo);
			mbrSizeClfcDetail.setMbrSizeTurn(mbrSizeTurn);
			mbrSizeClfcDetail.setMbrSizeCd("BODY");
			mbrSizeClfcDetail.setSizeVal(body);
			mbrSizeClfcDetail.setRegtrId(mbrNo);
			mbrSizeClfcDetail.setUdterId(mbrNo);

			MbrSizeClfcDetail mbrSizeClfcDetailResult = mbrSizeClfcDetailRepository.selectMbrSizeClfcDetail(mbrSizeClfcDetail);
			
			if (mbrSizeClfcDetailResult == null) {
				mbrSizeClfcDetailRepository.insertMbrSizeClfcDetail(mbrSizeClfcDetail);
			} else {
				mbrSizeClfcDetailRepository.updateMbrSizeClfcDetail(mbrSizeClfcDetail);
			}
		}
		
        MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
        mbrWebpntHist.setMbrNo(mbrNo);
        mbrWebpntHist.setWebpntTpCd("WEBPNT");
        mbrWebpntHist.setWebpntDetailResnCd("MBR_SIZE_REG");
        mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
        int cnt = memberBenefitSelectRepository.selectWebPntResnCdCount(mbrWebpntHist);

        if (cnt == 0) {
            int inputSizeCnt = memberBaseSelectRepository.selectMbrSizeAllInputCount(mbrNo);

            if (inputSizeCnt == 1) {
                BigDecimal pntAmt = new BigDecimal(500);

                mbrWebpntHist.setWebpntResnCd("EVT");
                mbrWebpntHist.setWebpntAmt(pntAmt);
                mbrWebpntHist.setUseBegDt(new Date());
                mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusYears(new Date(), 1), 1)); // 유효기간
                // 1년
                mbrWebpntHist.setResnDscr("나의사이즈 최초1회 입력");

                memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
                isPointEarned = true;
            }
        }
		
		return isPointEarned;
		
	}

	public void insertMyGoodsReviewAtchFile(MemberOrdGodFoDTO dto, List<MultipartFile> files)  {
		// 전체 업로드 확장자 체크를 한다.
		String[] excludeExtensions = { "asp", "js", "jsp", "php", "exe", "bat" };

		// 확장자를 체크한다. 불가 확장자이며 NotSupportedUploadFileException 발생시킨다.
		IOService.availableUploadExcludeExtension(files, excludeExtensions);
		
		/*
		 * 시스템 파일명을 자동으로 부여하여 업로드한다.
		 */
		
		FileUploadResult fileUploadResult = IOService.fileUploadAutoFileName(files,  getConfigService().getProperty("ncp.web.images.path")+"/goods/review/"+DateService.getStringCurrentToday(), excludeExtensions);
		List<FileUploadInfo> infoList = fileUploadResult.getRows();
		Map<String, Object> conditions;

		for (FileUploadInfo uploadInfo : infoList) {
			GodGodEvlAtchFile atchFile = new GodGodEvlAtchFile();
			conditions = Maps.newHashMap();
			conditions.put("GOD_NO", dto.getGodGodEvl().getGodNo());
			conditions.put("GOD_EVL_TURN", dto.getGodGodEvl().getGodEvlTurn());
			int atchFileTurn = getIdGenService().generateDBOrder(sqlSession1, "GOD_GOD_EVL_ATCH_FILE", "ATCH_FILE_TURN",
					conditions, DatabaseType.ORACLE);

			atchFile.setGodNo(dto.getGodGodEvl().getGodNo());
			atchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
			atchFile.setAtchFileTurn(atchFileTurn);
			atchFile.setAtchFileUrl("/goods/review/"+DateService.getStringCurrentToday() + "/" + uploadInfo.getFileName());
			atchFile.setAtchFileNm(uploadInfo.getOrgFileName() + "." + uploadInfo.getExtension());
			atchFile.setRegtrId(dto.getMbr().getMbrNo());
			atchFile.setUdterId(dto.getMbr().getMbrNo());

			log.info(uploadInfo.getOrgFileName());
			godGodEvlAtchFileRepository.insertGodGodEvlAtchFile(atchFile);
		}
	}

	public void insertMyGoodsReviewAtchFileByApp(MemberOrdGodFoDTO dto)  {

		log.info("insertMyGoodsReviewAtchFileByApp : ===========================");
		log.info("MemberOrdGodFoDTO : {}", dto);
		log.info("dto.getFileNames() : {}", dto.getFileNames().size());

		GodGodEvlAtchFile delAtchFile = new GodGodEvlAtchFile();
		delAtchFile.setGodNo(dto.getGodGodEvl().getGodNo());
		delAtchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
		dto.setGodGodEvlAtchFile(delAtchFile);
		memberActivityCommandRepository.deleteGodGodEvlAtchFile(dto);

		Map<String, Object> conditions;
		// String path = dto.getFilePath();
		// for(String uploadInfo : dto.getFileNames()){
		for (int i = 0; i < dto.getFileNames().size(); i++) {

			log.info("dto.getFilePaths().get(i)========== : {}", dto.getFilePaths().get(i));
			log.info("dto.getFilePaths().get(i)========== : {}", dto.getFileNames().get(i));
			String fileName = dto.getFileNames().get(i);

			if (fileName != null && fileName.length() > 0) {
				GodGodEvlAtchFile atchFile = new GodGodEvlAtchFile();
				conditions = Maps.newHashMap();
				conditions.put("GOD_NO", dto.getGodGodEvl().getGodNo());
				conditions.put("GOD_EVL_TURN", dto.getGodGodEvl().getGodEvlTurn());
				int atchFileTurn = getIdGenService().generateDBOrder(sqlSession1, "GOD_GOD_EVL_ATCH_FILE",
						"ATCH_FILE_TURN", conditions, DatabaseType.ORACLE);

				atchFile.setGodNo(dto.getGodGodEvl().getGodNo());
				atchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
				atchFile.setAtchFileTurn(atchFileTurn);
				atchFile.setAtchFileUrl("/god_god_evl/" + dto.getFilePaths().get(i) + fileName);
				atchFile.setAtchFileNm(fileName);
				atchFile.setRegtrId(dto.getMbr().getMbrNo());
				atchFile.setUdterId(dto.getMbr().getMbrNo());

				godGodEvlAtchFileRepository.insertGodGodEvlAtchFile(atchFile);
			}
		}
	}

	public void updateMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto)  {
		memberActivityCommandRepository.updateMyGoodsReview(dto.getGodGodEvl());
	}

	public int deleteGodGodEvlAtchFile(MemberOrdGodFoDTO dto)  {
		return memberActivityCommandRepository.deleteGodGodEvlAtchFile(dto);
	}

	public void deleteMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto)  {
		GodGodEvlAtchFile delAtchFile = new GodGodEvlAtchFile();
		delAtchFile.setGodNo(dto.getGodGodEvl().getGodNo());
		delAtchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
		dto.setGodGodEvlAtchFile(delAtchFile);
		memberActivityCommandRepository.deleteGodGodEvlAtchFile(dto);

		godGodEvlRepository.deleteGodGodEvl(dto.getGodGodEvl());
	}

	/**
	 * WishList 삭제
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public int deleteMyWishList(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteMyWishList(dto);
	}
	public int deleteMainMyWishList(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteMainMyWishList(dto);
	}
	/**
	 * WishList 전체삭제
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public int deleteAllMyWishList(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteAllMyWishList(dto);
	}

	/**
	 * 최근 본 상품 삭제
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public int deleteMyTodayGodList(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteMyTodayGodList(dto);
	}

	/**
	 * 최근 본 상품 전체 삭제
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public int deleteAllTodayGoodList(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteAllTodayGoodList(dto);
	}
	
	/**
	 * 재입고 알림 삭제
	 *
	 * @param MypageFoDTO
	 *            [설명]
	 * @return List [설명]
	 * @
	 *             the exception
	 * @since 2015. 5. 6
	 */
	public int deleteMyAlarmsForStock(MypageFoDTO dto)  {
		return memberActivityCommandRepository.deleteMyAlarmsForStock(dto);
	}

	/**1:1상담 파일 업로드 */
	public void insertFile(MypageMtmFoDTO mypageMtmFoDTO, List files) {

		Long mtmInqSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_CSO_MTM_INQ",DatabaseType.ORACLE);
		mypageMtmFoDTO.getCsoMtmInq().setMtmInqSn(mtmInqSn);

		CsoMtmInqAtchFile csoMtmInqAtchFile = new CsoMtmInqAtchFile();
		mypageMtmFoDTO.setCsoMtmInqAtchFile(csoMtmInqAtchFile);
		//1:1문의 등록
		memberActivityCommandRepository.insertCsInquiry(mypageMtmFoDTO);

		if(mypageMtmFoDTO.getOrdGodList() != null){
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MTM_INQ_SN",mtmInqSn);
			for(OrdGod ordGod : mypageMtmFoDTO.getOrdGodList()){

				mypageMtmFoDTO.setMtmInqOrdGodTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ORD_GOD","MTM_INQ_ORD_GOD_TURN",conditions,DatabaseType.ORACLE));
				mypageMtmFoDTO.setOrdGod(ordGod);
				//1:1 주문상품 등록
				memberActivityCommandRepository.insertCsInquiryOrdGod(mypageMtmFoDTO);

			}
		}

		// 2016.02.17 by cannon : 파일확장자 추가(asp, js)
		String[] excludeExtensions = {"asp", "js", "jsp", "php", "exe", "bat"};
		//전체 업로드 확장자 체크
		getIoService().availableUploadExcludeExtension(files, excludeExtensions);
		String timeStamp = new SimpleDateFormat("yy/MM/dd").format(Calendar.getInstance().getTime());
		String path = timeStamp + "/";
		FileUploadResult  fileUploadResult = getIoService().fileUploadAutoFileName(files,  getConfigService().getProperty("ncp_base.spring.mvc.upload.inquiry")+path, excludeExtensions);

		if(fileUploadResult.getFileCnt() > 0){
			Map<String, Object> conditions = Maps.newHashMap();
			conditions.put("MTM_INQ_SN",mtmInqSn);
			List<FileUploadInfo> rows = fileUploadResult.getRows();

			for (FileUploadInfo fileInfo : rows) {

				mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ATCH_FILE","MTM_INQ_ATCH_FILE_TURN", conditions, DatabaseType.ORACLE));
				mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileNm(fileInfo.getOrgFileName()+"."+fileInfo.getExtension());
				mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileUrl("/inquiry/" + path + fileInfo.getFileName());
				mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileCpcty(BigDecimal.valueOf(fileInfo.getFileSize()));
				mypageMtmFoDTO.getCsoMtmInqAtchFile().setUdterId(mypageMtmFoDTO.getMbrId());
				//파일 등록
				memberActivityCommandRepository.insertCsInquiryFile(mypageMtmFoDTO);
			}


		}

	}

	/** 1:1상담 등록	 
	 * @throws Exception */
	public void insertCsInquiry(MypageMtmFoDTO mypageMtmFoDTO){

		Long mtmInqSn = getIdGenService().generateDBSequence(sqlSession1, "SQ_CSO_MTM_INQ",DatabaseType.ORACLE);
		mypageMtmFoDTO.getCsoMtmInq().setMtmInqSn(mtmInqSn);

		//1:1문의 등록
		memberActivityCommandRepository.insertCsInquiry(mypageMtmFoDTO);
		
		
		//1:1문의 첨부파일 등록(첨부파일이 있는지 확인하고 넣어주어야함)
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("MTM_INQ_SN",mtmInqSn);// 의심이 좀 간다
		if(mypageMtmFoDTO.getCsoMtmInqAtchFile().getMtmInqAtchFileNm() != null && mypageMtmFoDTO.getCsoMtmInqAtchFile().getMtmInqAtchFileNm() != "" ){
		mypageMtmFoDTO.getCsoMtmInqAtchFile().setMtmInqAtchFileTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ATCH_FILE","MTM_INQ_ATCH_FILE_TURN", conditions, DatabaseType.ORACLE));
		memberActivityCommandRepository.insertCsInquiryFile(mypageMtmFoDTO);
		}
				
		if( mypageMtmFoDTO.getMbr() != null) {
			if(mypageMtmFoDTO.getCsoMtmInq().getErpGodNo()==null||mypageMtmFoDTO.getCsoMtmInq().getErpGodNo()==""){
				if(mypageMtmFoDTO.getOrdGodList() != null){
	
				String ordNo = mypageMtmFoDTO.getOrdGodList().get(0).getOrdNo();
				List<OrdGod> ordGodList = memberOrderSelectRepository.selectOrdGodNm(ordNo);
	
				Map<String, Object> conditions2 = Maps.newHashMap();
				for(OrdGod ordGod : mypageMtmFoDTO.getOrdGodList()){
					if(!StringService.isEmpty(ordGod.getOrdNo()) ){
						mypageMtmFoDTO.setMtmInqOrdGodTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ORD_GOD","MTM_INQ_ORD_GOD_TURN",conditions2,DatabaseType.ORACLE));
						mypageMtmFoDTO.setOrdGod(ordGod);
	
						if(ordGodList != null){
							for(int i = 0 ; i < ordGodList.size(); i++){
								if(ordGod.getOrdGodTurn().equals(ordGodList.get(i).getOrdGodTurn())){
									ordGod.setGodNm(ordGodList.get(i).getGodNm());
									break;
								}
							}
						}
						memberActivityCommandRepository.insertCsInquiryOrdGod(mypageMtmFoDTO);
					}
				 }
			   }
			}
			
		}else if(mypageMtmFoDTO.getMbr() ==null){
			if(mypageMtmFoDTO.getCsoMtmInq().getErpGodNo()==null||mypageMtmFoDTO.getCsoMtmInq().getErpGodNo()==""){
				SecurityContext context = SecurityContextHolder.getContext();	
				if(!context.getAuthentication().getPrincipal().equals("anonymousUser")){				
					String ordNo = mypageMtmFoDTO.getOrdGodList().get(0).getOrdNo();
					List<OrdGod> ordGodList = memberOrderSelectRepository.selectOrdGodNm(ordNo);
					Map<String, Object> conditions2 = Maps.newHashMap();
					for(OrdGod ordGod : mypageMtmFoDTO.getOrdGodList()){
						if(!StringService.isEmpty(ordGod.getOrdNo()) ){
							mypageMtmFoDTO.setMtmInqOrdGodTurn(getIdGenService().generateDBOrder(sqlSession1,"CSO_MTM_INQ_ORD_GOD","MTM_INQ_ORD_GOD_TURN",conditions2,DatabaseType.ORACLE));
							mypageMtmFoDTO.setOrdGod(ordGod);
							ordGod.setOrdGodTurn(1);
							if(ordGodList != null){
								for(int i = 0 ; i < ordGodList.size(); i++){
									if(ordGod.getOrdGodTurn().equals(ordGodList.get(i).getOrdGodTurn())){
										ordGod.setGodNm(ordGodList.get(i).getGodNm());
										break;
									}
								}
							}
							memberActivityCommandRepository.insertCsInquiryOrdGod(mypageMtmFoDTO);
						}
					 }					
				}	
			}
			//비회원 약관 동의	
			Map<String,Object> map= new HashMap<String,Object>();
			Long nmbrStplatAgrSn =getIdGenService().generateDBSequence(sqlSession1, "SQ_CSO_NMBR_STPLAT_AGR",DatabaseType.ORACLE);//약관동의 일련번호
			map.put("nmbrStplatAgrSn", nmbrStplatAgrSn);
			map.put("mtmInqSn", mtmInqSn);
			map.put("stplatCd", "NMBR_PSNL_INFO_COLCT_USEF_AGR");
			map.put("stplatIemAgrYn", "Y");
			map.put("udterId", "nonInqMember");
			map.put("regtrId", "nonInqMember");
			try {
				helpdeskRepository.insertNmbrStplatAgr(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}		
	}
	
	public int insertMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto) {	
		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("GOD_NO", dto.getGodGodEvl().getGodNo());
		int godEvlTurn = getIdGenService().generateDBOrder(sqlSession1,
		        "GOD_GOD_EVL", "GOD_EVL_TURN", conditions, DatabaseType.ORACLE);

		//dto.getGodGodEvl().setGodEvlTurn(godEvlTurn);
		GodGodEvl evl = dto.getGodGodEvl();
		evl.setGodEvlTurn(godEvlTurn);
		evl.setUserTrackingId(dto.getUserTrackingId());

		//memberActivityCommandRepository.insertMyGoodsReview(dto.getGodGodEvl());
		memberActivityCommandRepository.insertMyGoodsReview(evl);
		
		return godEvlTurn;
	}
	
	public void deleteGodGodEvlAtchFile(MemberOrdGodFoDTO dto,List files) {
		memberActivityCommandRepository.deleteGodGodEvlAtchFile(dto);
	}
	
	/**
	 * Delete my brand.
	 *
	 * @param mbrBukmkBrnd the mbr bukmk brnd
	 * @return the int
	 * @ the exception
	 */
	public int deleteMyBrand(MbrBukmkBrnd mbrBukmkBrnd)  {
		return mbrBukmkBrndRepository.deleteMbrBukmkBrnd(mbrBukmkBrnd);
	}
	
	/**
	 * 마이브랜드 추가
	 *
	 * @param mbrBukmkBrnd the mbr bukmk brnd
	 * @return the int
	 * @ the exception
	 */
	public int addMyBrand(MbrBukmkBrnd mbrBukmkBrnd)  {
		mbrBukmkBrndRepository.insertMbrBukmkBrnd(mbrBukmkBrnd);
		
		MbrBukmkBrnd res = mbrBukmkBrndRepository.selectMbrBukmkBrnd(mbrBukmkBrnd);
		
		if(res!=null) {
			return 1; 
		}else {
			return 0;
		}
	}
	
	
	public void mergeMbrSizeClfc(MbrSizeClfc mbrSizeClfc) {
		memberActivityCommandRepository.mergeMbrSizeClfc(mbrSizeClfc);
	}
	
	public void updateMbrSizeClfcUnds(MbrSizeClfc mbrSizeClfc) {
		memberActivityCommandRepository.updateMbrSizeClfcUnds(mbrSizeClfc);
	}	
	
	public void mergeMbrSizeClfcDetail(MbrSizeClfcDetail mbrSizeClfcDetail) {
		memberActivityCommandRepository.mergeMbrSizeClfcDetail(mbrSizeClfcDetail);
	}
	
	public void deleteMbrSizeClfc(String mbrNo, String mbrSizeTurn) {
		memberActivityCommandRepository.deleteMbrSizeClfc(mbrNo, mbrSizeTurn);
	}
	
	public void deleteMbrSizeClfcDetail(String mbrNo, String mbrSizeTurn) {
		memberActivityCommandRepository.deleteMbrSizeClfcDetail(mbrNo, mbrSizeTurn);
	}
	
	/**
	 * 상품리뷰에 연결된 회원 사이즈 정보 저장
	 * 
	 * @param dto
	 * @return
	 */
	public int insertMbrSizeInfoConnectedGoodsReview(GoodsReviewDTO dto) {
		return memberActivityCommandRepository.insertMbrSizeInfoConnectedGoodsReview(dto);
	}
	
	/**
	 * 상품평 삭제
	 * 
	 * @param dto
	 * @return
	 */
	public int deleteMyGoodsReviewNtceYn(MemberOrdGodFoDTO dto)  {
		return memberActivityCommandRepository.deleteMyGoodsReviewNtceYn(dto);
	}	
	
}
