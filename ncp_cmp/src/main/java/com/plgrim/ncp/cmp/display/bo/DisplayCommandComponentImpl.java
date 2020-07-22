/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 7. 31       
 */
package com.plgrim.ncp.cmp.display.bo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetMod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspBrndCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrContt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCnrConttLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgry;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryCnncGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspCtgryRelate;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtBrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtGod;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImg;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtImgLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtr;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspPromtSprtrLang;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRev;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspRevCpst;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrnd;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndDspTgt;
import com.plgrim.ncp.base.entities.datasource1.dsp.DspStrndLang;
import com.plgrim.ncp.base.enums.DisplayEnum;
import com.plgrim.ncp.biz.display.data.DspCnrBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttExt;
import com.plgrim.ncp.biz.display.data.DspCnrConttExtDTO;
import com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrTmplatInfoBoDTO;
import com.plgrim.ncp.biz.display.data.DspCnrTpGrpBoDTO;
import com.plgrim.ncp.biz.display.data.DspConttRevResultDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspCtgryCnncGodExt;
import com.plgrim.ncp.biz.display.data.DspCtgryRelateBoDTO;
import com.plgrim.ncp.biz.display.data.DspImgUploadDTO;
import com.plgrim.ncp.biz.display.data.DspPromtBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodBoDTO;
import com.plgrim.ncp.biz.display.data.DspPromtGodExt;
import com.plgrim.ncp.biz.display.data.DspPromtSprtrBoDTO;
import com.plgrim.ncp.biz.display.data.DspRevBoDTO;
import com.plgrim.ncp.biz.display.data.DspStrndBoDTO;
import com.plgrim.ncp.biz.display.data.DspStrndDspTgtBoDTO;
import com.plgrim.ncp.biz.display.data.DspTmplatBoDTO;
import com.plgrim.ncp.biz.display.exception.DspConttValidException;
import com.plgrim.ncp.biz.display.exception.NotDeletedBrndCtgryMoviException;
import com.plgrim.ncp.biz.display.exception.NotDeletedCnrTmplatException;
import com.plgrim.ncp.biz.display.exception.NotDeletedCnrTpException;
import com.plgrim.ncp.biz.display.exception.NotDeletedPromtGodException;
import com.plgrim.ncp.biz.display.exception.NotDeletedSetException;
import com.plgrim.ncp.biz.display.exception.NotUpdatedDspYnException;
import com.plgrim.ncp.biz.display.result.DspAbTestRevResult;
import com.plgrim.ncp.biz.display.result.DspCnrTmplatInfoCnncResult;
import com.plgrim.ncp.biz.display.service.DisplayCategoryService;
import com.plgrim.ncp.biz.display.service.DisplayCornerContentsService;
import com.plgrim.ncp.biz.display.service.DisplayCornerService;
import com.plgrim.ncp.biz.display.service.DisplayPlanService;
import com.plgrim.ncp.biz.display.service.DisplayRevCommandService;
import com.plgrim.ncp.biz.display.service.DisplayRevSelectService;
import com.plgrim.ncp.biz.display.service.DisplayStrendService;
import com.plgrim.ncp.biz.display.service.DisplayTemplateService;
import com.plgrim.ncp.commons.grid.GridCommandAwareCallback;
import com.plgrim.ncp.commons.grid.GridCommandTemplate;
import com.plgrim.ncp.commons.service.EditorService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.commons.util.CodeUtil.Code;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.exception.NotSupportedUploadFileException;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.utils.XSSUtil;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;

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
 * @author shsunhee.kim
 * @since 2015. 6. 1
 */

/** The Constant log. */
@Slf4j
@Transactional(value = "transactionManager")
@Component
public class DisplayCommandComponentImpl extends AbstractComponent implements DisplayCommandComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	@Autowired
	S3FileSystem s3FileSystem;
	@Autowired
	DisplayTemplateService displayTemplateService;

	@Autowired
	DisplayCornerService cornerService;

	@Autowired
	DisplayCategoryService displayCategoryService;

	@Autowired
	DisplayCornerContentsService displayCornerContensService;

	@Autowired
	DisplayPlanService displayPlanService;

	@Autowired
	DisplayStrendService displayStrendService;

	@Autowired
	DisplayRevSelectService displayRevSelectService;

	@Autowired
	DisplayRevCommandService displayRevCommandService;
	
	@Autowired
	EditorService editorService;

	// 이미지 업로드 경로 - mall cloud front
	@Value("${ncp_web_bo.image.display.category.upload.path}")
	String categoryUploadPath;

	@Value("${ncp_base.cloud.bucketName}")
	private String bucketName;

	// DB 저장 용- mall cloud front
	@Value("${ncp_web_bo.image.display.category.http.path}")
	String categoryHttpPath;

	@Value("${ncp_web_bo.image.display.plan.upload.path}")
	String planUploadPath;

	@Value("${ncp_web_bo.image.display.plan.http.path}")
	String planHttpPath;

	@Value("${ncp_web_bo.image.display.strnd.upload.path}")
	String strndUploadPath;

	@Value("${ncp_web_bo.image.display.strnd.http.path}")
	String strndHttpPath;

	@Value("${ncp_web_bo.image.display.contents.path}")
	String contentPath;

	@Value("${ncp_web_bo.image.display.sprtr.path}")
	String sprtrPath;

	@Value("${ncp_web_bo.image.display.brnd.movi.path}")
	String brndMoviPath;

	@Value("${ncp_web_bo.image.display.upload.path}")
	String uploadPath;

	private @Value("${ncp.image.url}") String imageSvcPath;

	@Value("${ncp_base.display.revision.base}")
	Long baseRevSn;

	@Autowired
	private InterfaceApiCommon interfaceApiCommon;

	@Autowired
	@Qualifier("sessionTemplate1")
	private SqlSession session1;

	String getUploadImageUrlPath() {
		return getConfigService().getProperty("ncp_web_bo.cloud.bucket.pec.temp.folder.path")
				+ getDateService().getStringCurrentToday() + "/";
	}

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
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.TemplateCommandComponent#addTemplate(com.
	 * plgrim.ncp.biz.display.data.DspTmplatDTO)
	 */
	@Override
	public long addTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception {
		return displayTemplateService.insertTemplate(dspTmplatDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.TemplateCommandComponent#modifyTemplate(com.
	 * plgrim.ncp.biz.display.data.DspTmplatDTO)
	 */
	@Override
	public int modifyTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception {
		return displayTemplateService.updateTemplate(dspTmplatDTO);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.TemplateCommandComponent#removeTemplate(com.
	 * plgrim.ncp.biz.display.data.DspTmplatDTO)
	 */
	@Override
	public int removeTemplate(DspTmplatBoDTO dspTmplatDTO) throws Exception {
		return displayTemplateService.deleteTemplate(dspTmplatDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#removeTemplateList(
	 * java.util.List)
	 */
	@Override
	public int removeTemplateList(List<DspTmplatBoDTO> list) {
		try {
			return displayTemplateService.deleteTemplateList(list);

		} catch (NotDeletedCnrTmplatException ve) {
			throw new NotDeletedCnrTmplatException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.TemplateCommandComponent#addTemplateCorner(
	 * java.util.List)
	 */
	@Override
	public int addTemplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> addList) {
		try {
			return displayTemplateService.insertTemplateCorner(tmplatSn, addList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.TemplateCommandComponent#modifyTemplateCorner(
	 * java.util.List)
	 */
	@Override
	public int modifyTemplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> updList) {
		try {
			return displayTemplateService.updateTemplateCorner(tmplatSn, updList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.TemplateCommandComponent#removeTmplateCorner(
	 * java.util.List)
	 */
	@Override
	public int removeTmplateCorner(Long tmplatSn, List<DspCnrTmplatInfoBoDTO> list) {
		try {
			return displayTemplateService.deleteTemplateCorner(tmplatSn, list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveTmplateCorner(
	 * java.util.List, java.util.List)
	 */
	@Override
	public int saveTmplateCorner(List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList) {
		try {
			int result = 0;
			result = displayTemplateService.insertTemplateCorner(insList);
			result += displayTemplateService.updateTemplateCorner(updList);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#addCorner(com.plgrim.
	 * ncp.biz.display.data.DspCnrDTO)
	 */
	@Override
	public long addCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerService.insertCorner(dspCnrDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#modifyCorner(com.plgrim
	 * .ncp.biz.display.data.DspCnrDTO)
	 */
	@Override
	public int modifyCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerService.updateCorner(dspCnrDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#removeCorner(com.plgrim
	 * .ncp.biz.display.data.DspCnrDTO)
	 */
	@Override
	public int removeCorner(DspCnrBoDTO dspCnrDTO) throws Exception {
		return cornerService.deleteCorner(dspCnrDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#addCornerType(java.util
	 * .List)
	 */
	@Override
	public void addCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list) {
		try {
			cornerService.insertCornerType(cnrSn, upperCnrTpGrpSn, list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#modifyCornerType(java.
	 * util.List)
	 */
	@Override
	public void modifyCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list) {
		try {
			cornerService.updateCornerType(cnrSn, upperCnrTpGrpSn, list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.CornerCommandComponent#removeCornerType(java.
	 * util.List)
	 */
	@Override
	public void removeCornerType(Long cnrSn, Long upperCnrTpGrpSn, List<DspCnrTpGrpBoDTO> list) {
		try {
			cornerService.deleteCornerType(cnrSn, upperCnrTpGrpSn, list);
		} catch (NotDeletedCnrTpException ve) {
			throw new NotDeletedCnrTpException(null);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#modifyCornerList(
	 * java.util.List)
	 */
	@Override
	public void modifyCornerList(List<DspCnrBoDTO> list) {
		try {
			cornerService.updateCornerList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#removeCornerList(
	 * java.util.List)
	 */
	@Override
	public void removeCornerList(List<DspCnrBoDTO> list) {
		try {
			cornerService.deleteCornerList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveCornerType(java
	 * .util.List, java.util.List)
	 */
	@Override
	public void saveCornerType(List<DspCnrTpGrpBoDTO> insList, List<DspCnrTpGrpBoDTO> updList) {
		try {
			cornerService.insertCornerType(insList);
			cornerService.updateCornerType(updList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertCategoryInfo(
	 * com.plgrim.ncp.biz.display.data.DspCtgryBoDTO, java.util.List)
	 */
	@Override
	public String insertCategoryInfo(DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile) {
		try {
			DspCtgry dspCtgry = dspCtgryBoDTO.getDspCtgry();

			// 카테고리 번호 생성
			String upperDspCtgryNo = dspCtgryBoDTO.getUpperDspCtgryNo();
			String dspCtgryNo = null;
			if (upperDspCtgryNo == null || "".equals(upperDspCtgryNo) || "0".equals(upperDspCtgryNo)) {
				// 최상위카테고리 등록
				dspCtgryNo = dspCtgryBoDTO.getDspCtgryNo();
				dspCtgry.setDspCtgryNo(dspCtgryNo);
			} else {
				// 하위카테고리 등록
				dspCtgry.setUpperDspCtgryNo(upperDspCtgryNo);

				dspCtgryNo = displayCategoryService.selectDspCtgryNo(dspCtgryBoDTO.getUpperDspCtgryNo());
				dspCtgry.setDspCtgryNo(dspCtgryNo);
			}

			// 카테고리 Depth
			if (dspCtgry.getCtgryDpthCd() == null || "".equals(dspCtgry.getCtgryDpthCd())) {
				dspCtgry.setCtgryDpthCd("0");
			} else {
				dspCtgry.setCtgryDpthCd("" + (Integer.parseInt(dspCtgry.getCtgryDpthCd()) + 1));
			}

			// 카테고리명 디폴트(KOR)
			String[] arCtgryNm = dspCtgryBoDTO.getArDspCtgryNm();
			String[] arLangCd = dspCtgryBoDTO.getArLangCdNm();

			if (StringService.isNotEmpty(arCtgryNm[0])) {
				dspCtgry.setDspCtgryNm(arCtgryNm[0]);
			}

			// Meta Tag 정보(KOR)
			String[] arMetaSjNm = dspCtgryBoDTO.getArMetaSjNm();
			String[] arMetaDscrCont = dspCtgryBoDTO.getArMetaDscrCont();
			String[] arMetaKwd = dspCtgryBoDTO.getArMetaKwd();

			if (StringService.isNotEmpty(arMetaSjNm[0])) {
				dspCtgry.setMetaSjNm(arMetaSjNm[0]);
			}
			if (StringService.isNotEmpty(arMetaDscrCont[0])) {
				dspCtgry.setMetaDscrCont(arMetaDscrCont[0]);
			}
			if (StringService.isNotEmpty(arMetaKwd[0])) {
				dspCtgry.setMetaKwd(arMetaKwd[0]);
			}

			// 대표이미지 업로드(KOR)
			String[] arRprstImgFileNm = dspCtgryBoDTO.getArRprstImgFileNm();
			String[] arRprstImgAltrtvCont = dspCtgryBoDTO.getArRprstImgAltrtvCont();

			String dirPath = "";
			int idx = 0;
			while (idx < dspCtgryNo.length()) {
				dirPath += StringService.mid(dspCtgryNo, idx, 3) + "/";
				idx = idx + 3;
			}
			dirPath = StringService.substring(dirPath, 0, dirPath.length() - 1);

			String fileUrl = categoryHttpPath + "/" + dirPath;
			String uploadRealPath = categoryUploadPath + "/" + dirPath;
			String fileName = "";
			String fileExt = "";

			String[] realFileName = null;
			if (arLangCd != null) {
				realFileName = new String[arLangCd.length];
			}

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
					if (arRprstImgFileNm[0].indexOf("_") < 0) {
						fileName = dspCtgry.getDspCtgryNo() + "_" + arLangCd[0] + "_" + this.getCurrTimeForImg() + "."
								+ fileExt;
					} else {
						fileName = arRprstImgFileNm[0];
					}
					dspCtgry.setRprstImgFileNm(fileName);
					dspCtgry.setRprstImgFileUrl(fileUrl);

					realFileName[0] = fileName;
					// realFilePath.add(fileUrl);
				} else {
					dspCtgry.setRprstImgFileNm("");
					dspCtgry.setRprstImgFileUrl("");

					realFileName[0] = "";
				}
			}

			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspCtgry.setRprstImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}

			displayCategoryService.insertDspCtgry(dspCtgry);

			DspRevCpst dspRevCpst = dspCtgryBoDTO.getDspRevCpst();
			// Long pcTmplatSn = dspRevCpst.getPcTmplatSn();
			// Long mbTmplatSn = dspRevCpst.getMobileTmplatSn();

			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn("N");
			}

			dspRevCpst.setRevSn(baseRevSn);
			dspRevCpst.setDspCtgryNo(dspCtgry.getDspCtgryNo());
			dspRevCpst.setUseYn(DisplayEnum.YES.toString());
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 코너템플릿 연결테이블 저장
			displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgryNo, null, DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(),
					dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgryNo, null,
						DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(), dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			DspCtgryLang[] arDspCtgryLang = new DspCtgryLang[arLangCd.length];
			// 영어 중국어 카테고리명
			for (int i = 1; i < arCtgryNm.length; i++) {
				if (StringService.isNotEmpty(arCtgryNm[i])) {
					if (arDspCtgryLang[i] == null) {
						arDspCtgryLang[i] = new DspCtgryLang();
					}
					arDspCtgryLang[i].setDspCtgryNo(dspCtgryNo);
					arDspCtgryLang[i].setLangCd(arLangCd[i]);
					arDspCtgryLang[i].setDspCtgryNm(arCtgryNm[i]);
				}
			}

			// Mate Tag 정보 (영어/중국어)
			if ((arMetaSjNm != null && arMetaSjNm.length > 0) || (arMetaDscrCont != null && arMetaDscrCont.length > 0)
					|| (arMetaKwd != null && arMetaKwd.length > 0)) {
				for (int i = 1; i < arLangCd.length; i++) {
					DspCtgryLang dspCtgryLang = new DspCtgryLang();
					dspCtgryLang.setLangCd(arLangCd[i]);
					dspCtgryLang.setDspCtgryNo(dspCtgry.getDspCtgryNo());

					if (StringService.isNotEmpty(arMetaSjNm[i])) {
						dspCtgryLang.setMetaSjNm(arMetaSjNm[i]);
					}
					if (StringService.isNotEmpty(arMetaDscrCont[i])) {
						dspCtgryLang.setMetaDscrCont(arMetaDscrCont[i]);
					}
					if (StringService.isNotEmpty(arMetaKwd[i])) {
						dspCtgryLang.setMetaKwd(arMetaKwd[i]);
					}

					if (displayCategoryService.selectDspCtgryLang(dspCtgryLang) != null) {
						displayCategoryService.updateDspCtgryLangMetaTag(dspCtgryLang);
					} else {
						displayCategoryService.insertDspCtgryLangMetaTag(dspCtgryLang);
					}

				}
			}

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						if (arDspCtgryLang[i] == null) {
							arDspCtgryLang[i] = new DspCtgryLang();
						}
						arDspCtgryLang[i].setDspCtgryNo(dspCtgryNo);
						arDspCtgryLang[i].setLangCd(arLangCd[i]);

						// real 파일명, 경로생성
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
						if (arRprstImgFileNm[i].indexOf("_") < 0) {
							fileName = dspCtgry.getDspCtgryNo() + "_" + arLangCd[i] + "_" + this.getCurrTimeForImg()
									+ "." + fileExt;
						} else {
							fileName = arRprstImgFileNm[i];
						}

						arDspCtgryLang[i].setRprstImgFileNm(fileName);
						arDspCtgryLang[i].setRprstImgFileUrl(fileUrl);

						arDspCtgryLang[i].setRegtrId(dspCtgry.getRegtrId());

						realFileName[i] = fileName;

						if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
							arDspCtgryLang[i].setRprstImgAltrtvCont(arRprstImgAltrtvCont[i]);
						}

					} else {
						realFileName[i] = "";
					}
				}
			}

			for (int i = 1; i < arDspCtgryLang.length; i++) {
				if (arDspCtgryLang[i] != null) {
					arDspCtgryLang[i].setRegtrId(dspCtgry.getRegtrId());
					arDspCtgryLang[i].setUdterId(dspCtgry.getRegtrId());
					displayCategoryService.saveDspCtgryLang(arDspCtgryLang[i]);
				}
			}

			// 실제 운영 파일 move
			if (rprstImgFile != null && rprstImgFile.size() > 0) {
				int moveFileCnt = moveTempImage(uploadRealPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);

			}
			log.info(CommonResponseCode.DP_00017_CTGRY_카테고리_등록_성공.toMessage() + "ctgryNo: {}", dspCtgryNo);
			return dspCtgryNo;

		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40011_CTGRY_카테고리_등록_실패.toMessage() + "dspCtgryBoDTO: {}", dspCtgryBoDTO);
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateCategoryInfo(
	 * com.plgrim.ncp.biz.display.data.DspCtgryBoDTO, java.util.List)
	 */
	@Override
	public int updateCategoryInfo(DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile) {
		try {
			int result = 0;
			DspCtgry dspCtgry = dspCtgryBoDTO.getDspCtgry();

			// 카테고리명 디폴트(KOR)
			String[] arCtgryNm = dspCtgryBoDTO.getArDspCtgryNm();
			String[] arLangCd = dspCtgryBoDTO.getArLangCdNm();

			if (StringService.isNotEmpty(arCtgryNm[0])) {
				dspCtgry.setDspCtgryNm(arCtgryNm[0]);
			}

			// Meta Tag 정보(KOR)
			String[] arMetaSjNm = dspCtgryBoDTO.getArMetaSjNm();
			String[] arMetaDscrCont = dspCtgryBoDTO.getArMetaDscrCont();
			String[] arMetaKwd = dspCtgryBoDTO.getArMetaKwd();

			if (StringService.isNotEmpty(arMetaSjNm[0])) {
				dspCtgry.setMetaSjNm(arMetaSjNm[0]);
			}
			if (StringService.isNotEmpty(arMetaDscrCont[0])) {
				dspCtgry.setMetaDscrCont(arMetaDscrCont[0]);
			}
			if (StringService.isNotEmpty(arMetaKwd[0])) {
				dspCtgry.setMetaKwd(arMetaKwd[0]);
			}

			// 대표이미지 업로드(KOR)
			String[] arRprstImgFileNm = dspCtgryBoDTO.getArRprstImgFileNm();
			String[] arRprstImgFileUrl = dspCtgryBoDTO.getArRprstImgFileUrl();

			String dirPath = "";
			int idx = 0;
			while (idx < dspCtgry.getDspCtgryNo().length()) {
				dirPath += StringService.mid(dspCtgry.getDspCtgryNo(), idx, 3) + "/";
				idx = idx + 3;
			}
			dirPath = StringService.substring(dirPath, 0, dirPath.length() - 1);

			String fileUrl = categoryHttpPath + "/" + dirPath;
			String uploadRealPath = categoryUploadPath + "/" + dirPath;

			String fileName = "";
			String fileExt = "";
			String[] realFileName = new String[arLangCd.length];
			// List<String> realFilePath = new ArrayList<String>();

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);

					if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
						fileName = arRprstImgFileNm[0];
						fileUrl = arRprstImgFileUrl[0];
					} else {
						fileName = dspCtgry.getDspCtgryNo() + "_" + arLangCd[0] + "_" + this.getCurrTimeForImg() + "."
								+ fileExt;
						fileUrl = categoryHttpPath + "/" + dirPath;
					}

					dspCtgry.setRprstImgFileNm(fileName);
					dspCtgry.setRprstImgFileUrl(fileUrl);

					realFileName[0] = fileName;
					// realFilePath.add(fileUrl);
				} else {
					dspCtgry.setRprstImgFileNm("");
					dspCtgry.setRprstImgFileUrl("");
					realFileName[0] = "";
				}

			}

			String[] arRprstImgAltrtvCont = dspCtgryBoDTO.getArRprstImgAltrtvCont();
			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspCtgry.setRprstImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}

			DspRevCpst dspRevCpst = dspCtgryBoDTO.getDspRevCpst();
			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn("N");
			}

			// 전시 개정 구성 테이블 저장 : 기본 리비전 담아올것 pc, mb , 이전 yn도 구해올것
			dspRevCpst.setDspCtgryNo(dspCtgry.getDspCtgryNo());
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 코너템플릿 연결테이블 저장
			displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgry.getDspCtgryNo(), null,
					DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(), dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgry.getDspCtgryNo(), null,
						DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(), dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			// 08.19 추가 전시여부 수정시
			// 1) 상위가 'Y'일 경우만 수정가능
			// 2) 'N'으로 수정 시 하위 전체를 'N'으로 수정
			dspCtgry.setUpperDspCtgryNo(dspCtgryBoDTO.getUpperDspCtgryNo());
			displayCategoryService.updateDspYnCtgry(dspCtgry);

			// 통계용영문카테고리명확인
			String stt = dspCtgry.getSttCtgryNm();
			if (StringService.isNotEmpty(stt)) {
				Pattern regexp = Pattern.compile("[\\s\\W]");
				stt = regexp.matcher(stt).replaceAll("-");
				dspCtgry.setSttCtgryNm(stt);
			}

			result = displayCategoryService.updateDspCtgryInfo(dspCtgry);

			// 카테고리명 (영어/중국어)
			for (int i = 1; i < arCtgryNm.length; i++) {
				DspCtgryLang lang = new DspCtgryLang();
				lang.setDspCtgryNo(dspCtgry.getDspCtgryNo());
				lang.setLangCd(arLangCd[i]);
				lang.setDspCtgryNm(arCtgryNm[i]);

				displayCategoryService.saveDspCtgryLang(lang);
			}

			// Mate Tag 정보 (영어/중국어)
			if ((arMetaSjNm != null && arMetaSjNm.length > 0) || (arMetaDscrCont != null && arMetaDscrCont.length > 0)
					|| (arMetaKwd != null && arMetaKwd.length > 0)) {
				for (int i = 1; i < arLangCd.length; i++) {
					DspCtgryLang dspCtgryLang = new DspCtgryLang();
					dspCtgryLang.setLangCd(arLangCd[i]);
					dspCtgryLang.setDspCtgryNo(dspCtgry.getDspCtgryNo());

					if (StringService.isNotEmpty(arMetaSjNm[i])) {
						dspCtgryLang.setMetaSjNm(arMetaSjNm[i]);
					}
					if (StringService.isNotEmpty(arMetaDscrCont[i])) {
						dspCtgryLang.setMetaDscrCont(arMetaDscrCont[i]);
					}
					if (StringService.isNotEmpty(arMetaKwd[i])) {
						dspCtgryLang.setMetaKwd(arMetaKwd[i]);
					}

					if (displayCategoryService.selectDspCtgryLang(dspCtgryLang) != null) {
						displayCategoryService.updateDspCtgryLangMetaTag(dspCtgryLang);
					} else {
						displayCategoryService.insertDspCtgryLangMetaTag(dspCtgryLang);
					}

				}
			}

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					DspCtgryLang lang = new DspCtgryLang();
					lang.setDspCtgryNo(dspCtgry.getDspCtgryNo());
					lang.setLangCd(arLangCd[i]);
					lang.setDspCtgryNm(arCtgryNm[i]);
					lang.setUdterId(dspCtgry.getUdterId());

					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);

						// if(arRprstImgFileNm[i].indexOf("_") < 0) {
						// fileName = dspCtgry.getDspCtgryNo() + "_"+
						// arLangCd[i] + "_"+ this.getCurrTimeForImg() + "." +
						// fileExt;
						// }
						// else {
						// fileName = arRprstImgFileNm[i];
						// }
						if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
							fileName = arRprstImgFileNm[i];
							fileUrl = arRprstImgFileUrl[i];
						} else {
							fileName = dspCtgry.getDspCtgryNo() + "_" + arLangCd[i] + "_" + this.getCurrTimeForImg()
									+ "." + fileExt;
							fileUrl = categoryHttpPath + "/" + dirPath;
						}

						lang.setRprstImgFileNm(fileName);
						lang.setRprstImgFileUrl(fileUrl);
						realFileName[i] = fileName;
					} else {
						lang.setRprstImgFileNm("");
						lang.setRprstImgFileUrl("");
						realFileName[i] = "";
					}

					if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						lang.setRprstImgAltrtvCont(arRprstImgAltrtvCont[i]);
					}

					displayCategoryService.saveDspCtgryLang(lang);
				}
			}

			// 실제 운영 파일 move
			if (rprstImgFile != null && rprstImgFile.size() > 0) {
				int moveFileCnt = moveTempImage(uploadRealPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);

			}

			log.info(CommonResponseCode.DP_00018_CTGRY_카테고리_수정_성공.toMessage() + "dspCtgryNo: {}",
					dspCtgry.getDspCtgryNo());

			return result;
		} catch (NotUpdatedDspYnException ve) {
			log.info(CommonResponseCode.DP_40013_CTGRY_카테고리_수정_실패_상위카테고리전시여부.toMessage() + "dspCtgryBoDTO: {}",
					dspCtgryBoDTO);
			throw new NotUpdatedDspYnException(null);
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40012_CTGRY_카테고리_수정_실패.toMessage() + "dspCtgryBoDTO: {}", dspCtgryBoDTO);
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deleteCategoryInfo(
	 * com.plgrim.ncp.biz.display.data.DspCtgryBoDTO)
	 */
	@Override
	public int deleteCategoryInfo(DspCtgryBoDTO dspCtgryBoDTO) {
		try {
			return displayCategoryService.deleteCategoryInfo(dspCtgryBoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateCtgryList(
	 * java.util.List)
	 */
	@Override
	public void updateCtgryList(List<DspCtgryBoDTO> list) throws Exception {
		try {
			displayCategoryService.updateCategoryList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deleteCtgryList(
	 * java.util.List)
	 */
	@Override
	public void deleteCtgryList(List<DspCtgryBoDTO> list) {
		try {
			displayCategoryService.deleteCategoryList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveCtgryCnncGodList(java.util.List)
	 */
	@Override
	public void saveCtgryCnncGodList(List<DspCtgryCnncGodBoDTO> updList) {
		try {
			displayCategoryService.updateCategoryGodList(updList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deleteCtgryCnncGodList(java.util.List)
	 */
	@Override
	public int deleteCtgryCnncGodList(List<DspCtgryCnncGodBoDTO> list) {
		try {
			return displayCategoryService.deleteCategoryGodList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#copyCtgryCnncGod(
	 * com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO)
	 */
	@Override
	public int copyCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) {
		try {
			return displayCategoryService.copyCtgryCnncGod(dspCtgryCnncGodBoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#addCtgryCnncGodList
	 * (com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO)
	 */
	@Override
	public String[] addCtgryCnncGodList(List<DspCtgryCnncGod> list) {
		try {
			return displayCategoryService.insertCategoryGodList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateSortSeqGod(
	 * java.util.List, java.lang.String)
	 */
	@Override
	public List<DspCtgryCnncGodExt> updateSortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo) {
		try {
			List<DspCtgryCnncGodExt> errList = displayCategoryService.updateSortSeqGod(list, dspCtgryNo);

			return errList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveCornerSetList(
	 * java.util.List, java.util.List)
	 */
	@Override
	public int saveCornerSetList(List<DspCnrConttScBoDTO> insList, List<DspCnrConttScBoDTO> updList) {
		try {
			int result = 0;
			displayCornerContensService.insertCornerSet(insList);
			displayCornerContensService.updateCornerSet(updList);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveCornerSetListRev(java.util.List, java.util.List)
	 */
	@Override
	public int saveCornerSetListRev(List<DspCnrConttScBoDTO> insList, List<DspCnrConttScBoDTO> updList) {
		try {
			int result = 1;
			String scPrvwSn = null;
			if (insList != null && insList.size() > 0) {
				scPrvwSn = insList.get(0).getScPrvwSn();
			}

			if (scPrvwSn == null && updList != null && updList.size() > 0) {
				scPrvwSn = updList.get(0).getScPrvwSn();
			}

			// 미리보기 리비전 번호가 있을 경우 미리보기 리비전에도 저장한다. - (코너, 세트)
			if (StringService.isNotEmpty(scPrvwSn)) {
				Iterator<DspCnrConttScBoDTO> it = insList.iterator();
				while (it.hasNext()) {
					DspCnrConttScBoDTO dccDTO = it.next();
					scPrvwSn = dccDTO.getScPrvwSn();
					if (StringService.isNotEmpty(scPrvwSn)) {
						dccDTO.getDspCnrSet().setRevSn(Long.parseLong(dccDTO.getScPrvwSn()));
					}
				}

				Iterator<DspCnrConttScBoDTO> uit = updList.iterator();
				while (uit.hasNext()) {
					DspCnrConttScBoDTO dccDTO = uit.next();
					scPrvwSn = dccDTO.getScPrvwSn();
					if (StringService.isNotEmpty(scPrvwSn)) {
						dccDTO.getDspCnrSet().setRevSn(Long.parseLong(dccDTO.getScPrvwSn()));
					}
				}

				displayCornerContensService.insertCornerSetRev(insList);
				displayCornerContensService.updateCornerSet(updList);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deleteCornerSetList
	 * (java.util.List)
	 */
	@Override
	public void deleteCornerSetList(List<DspCnrConttScBoDTO> list) throws Exception {
		try {
			displayCornerContensService.deleteCornerSet(list);
		} catch (NotDeletedSetException ve) {
			throw new NotDeletedSetException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deleteCornerSetListRev(java.util.List)
	 */
	@Override
	public void deleteCornerSetListRev(List<DspCnrConttScBoDTO> list) {
		try {
			String scPrvwSn = null;
			if (list != null && list.size() > 0) {
				scPrvwSn = list.get(0).getScPrvwSn();
			}

			// 미리보기 리비전 번호가 있을 경우 미리보기 리비전에도 저장한다. - (코너, 세트)
			if (StringService.isNotEmpty(scPrvwSn)) {
				Iterator<DspCnrConttScBoDTO> it = list.iterator();
				while (it.hasNext()) {
					DspCnrConttScBoDTO dccDTO = it.next();
					scPrvwSn = dccDTO.getScPrvwSn();
					if (StringService.isNotEmpty(scPrvwSn)) {
						dccDTO.getDspCnrSet().setRevSn(Long.parseLong(dccDTO.getScPrvwSn()));
					}
				}

				displayCornerContensService.deleteCornerSet(list);
			}
		} catch (NotDeletedSetException ve) {
			throw new NotDeletedSetException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertCornerContent
	 * (com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public int insertCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile) {
		try {
			int result = 0;
			Integer conttTurn = null;
			if (dspCnrConttScBoDTO != null) {

				String loginId = BOSecurityUtil.getLoginId();

				DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				dspCnrContt.setRegtrId(loginId);
				dspCnrContt.setUdterId(loginId);

				String conttTp = dspCnrContt.getConttTpCd();

				if (StringService.isNotEmpty(conttTp)) {

					// 컨텐트 순번
					conttTurn = displayCornerContensService.getConttTurn(dspCnrContt);
					dspCnrContt.setConttTurn(conttTurn);

					String[] arLangCd = dspCnrConttScBoDTO.getArLangCd();

					Long scRevSn = dspCnrContt.getRevSn();
					Long scCnrSn = dspCnrContt.getCnrSn();
					Long scCnrSetSn = dspCnrContt.getCnrSetSn();

					// 전시기간
					String inBegDt = dspCnrConttScBoDTO.getInBegDt();
					String inEndDt = dspCnrConttScBoDTO.getInEndDt();
					SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					dspCnrContt.setDspBegDt(simplDateFormat.parse(inBegDt));
					dspCnrContt.setDspEndDt(simplDateFormat.parse(inEndDt));

					// HTML
					if (conttTp.equals(DisplayEnum.CONTT_TP.HTML.toString())) {
						String mallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
						String[] path = getCnrConttFilePath(dspCnrConttScBoDTO);
						String uploadPath = path[1];
						String commitResourcePath = this.bucketName + ":" + getSaveImagePath(mallId) + uploadPath;
						
						String htmlCont = dspCnrConttScBoDTO.getArHtmlCont()[0];
						if(StringService.contains(htmlCont, "uncommited")) {
							htmlCont = editorService.commitContentsImagesFromTemp(htmlCont, commitResourcePath, true);
						}
						
						dspCnrContt.setHtmlCont(htmlCont);
						displayCornerContensService.insertCornerContent(dspCnrContt);

						String[] arHtmlCont = dspCnrConttScBoDTO.getArHtmlCont();
						
						// 컨텐츠 언어 등록
						if (arHtmlCont != null && arHtmlCont.length > 1) {
							for (int i = 1; i < arHtmlCont.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								if (StringService.isNotEmpty(arHtmlCont[i])) {
									lang.setHtmlSj(dspCnrContt.getHtmlSj());
									
									htmlCont = arHtmlCont[i];
									if(StringService.contains(htmlCont, "uncommited")) {
										htmlCont = editorService.commitContentsImagesFromTemp(htmlCont, commitResourcePath, true);
									}
									
									lang.setHtmlCont(htmlCont);
									lang.setRegtrId(loginId);
									lang.setUdterId(loginId);

									displayCornerContensService.insertCornerContentLang(lang);
								}
							}
						}
						result++;
					}
					// 이미지배너, 동영상
					else if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
							|| conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {

						String[] path = getCnrConttFilePath(dspCnrConttScBoDTO);
						String fileUrl = path[0];
						String uploadPath = path[1];

						String[] arRprstImgFileNm = dspCnrConttScBoDTO.getArRprstImgFileNm();
						String[] arRprstImgFileUrl = dspCnrConttScBoDTO.getArRprstImgFileUrl();
						String[] arRprstImgAltrtvCont = dspCnrConttScBoDTO.getArRprstImgAltrtvCont();

						String[] arImgOvFileNm = dspCnrConttScBoDTO.getArImgOvFileNm();
						String[] arImgOvFileUrl = dspCnrConttScBoDTO.getArImgOvFileUrl();
						String[] arImgOvAltrtvCont = dspCnrConttScBoDTO.getArImgOvAltrtvCont();

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						String[] arImgMapCont = dspCnrConttScBoDTO.getArImgMapCont();
						String[] arImgDscr = dspCnrConttScBoDTO.getArImgDscr();

						String fileName = "";
						String fileExt = "";
						String[] realFileName = new String[arLangCd.length];

						if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
							// real 파일명, 경로생성
							if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
								fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
								// 코너번호_코너셋트번호_컨텐츠순번_언어코드.ext
								// if(arRprstImgFileNm[0].indexOf("_") < 0){
								// fileName = scCnrSn.toString() + "_" +
								// scCnrSetSn.toString() + "_" +
								// conttTurn.toString() + "_" + arLangCd[0] +
								// "_"+ this.getCurrTimeForImg() + "." +
								// fileExt;
								// } else {
								// fileName = arRprstImgFileNm[0];
								// }
								if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
									fileName = arRprstImgFileNm[0];
									fileUrl = arRprstImgFileUrl[0];
								} else {
									fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
											+ conttTurn.toString() + "_" + arLangCd[0] + "_" + this.getCurrTimeForImg()
											+ "." + fileExt;
									fileUrl = path[0];
								}

								if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
									dspCnrContt.setImgFileNm(fileName);
									dspCnrContt.setImgFileUrl(fileUrl);
								} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
									dspCnrContt.setMoviImgFileNm(fileName);
									dspCnrContt.setMoviImgUrl(fileUrl);
								}

								realFileName[0] = fileName;
							} else {
								dspCnrContt.setImgFileNm("");
								dspCnrContt.setImgFileUrl("");

								realFileName[0] = "";
							}
						}

						String[] realOvFileName = new String[arLangCd.length];

						if (arImgOvFileNm != null && arImgOvFileNm.length > 0) {
							// real 파일명, 경로생성
							if (StringService.isNotEmpty(arImgOvFileNm[0])) {
								fileExt = arImgOvFileNm[0].substring(arImgOvFileNm[0].lastIndexOf(".") + 1);
								// 코너번호_코너셋트번호_컨텐츠순번_언어코드_OV.ext
								// if(arImgOvFileNm[0].indexOf("_") < 0){
								// fileName = scCnrSn.toString() + "_" +
								// scCnrSetSn.toString() + "_" +
								// conttTurn.toString() + "_" + arLangCd[0]
								// +"_OV" + "_"+ this.getCurrTimeForImg() + "."
								// + fileExt;
								// } else {
								// fileName = arImgOvFileNm[0];
								// }
								if (StringService.isNotEmpty(arImgOvFileUrl[0])) {
									fileName = arImgOvFileNm[0];
									fileUrl = arImgOvFileUrl[0];
								} else {
									fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
											+ conttTurn.toString() + "_" + arLangCd[0] + "_OV" + "_"
											+ this.getCurrTimeForImg() + "." + fileExt;
									fileUrl = path[0];
								}

								dspCnrContt.setImgOvFileNm(fileName);
								dspCnrContt.setImgOvFileUrl(fileUrl);

								realOvFileName[0] = fileName;
							} else {
								dspCnrContt.setImgOvFileNm("");
								dspCnrContt.setImgOvFileUrl("");

								realOvFileName[0] = "";
							}
						}

						if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
							if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
								dspCnrContt.setImgAltrtvCont(arRprstImgAltrtvCont[0]);
							} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
								dspCnrContt.setMoviImgAltrtvCont(arRprstImgAltrtvCont[0]);
							}

						}

						if (arImgOvAltrtvCont != null && StringService.isNotEmpty(arImgOvAltrtvCont[0])) {
							dspCnrContt.setImgOvAltrtvCont(arImgOvAltrtvCont[0]);
						}

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						if (arImgMapCont != null && StringService.isNotEmpty(arImgMapCont[0])) {
							dspCnrContt.setImgMapCont(arImgMapCont[0]);
						}

						if (arImgDscr != null && StringService.isNotEmpty(arImgDscr[0])) {
							dspCnrContt.setImgDscr(arImgDscr[0]);
						}

						// 한국어 등록
						displayCornerContensService.insertCornerContent(dspCnrContt);

						// 컨텐트 언어 등록
						if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
							for (int i = 1; i < arRprstImgFileNm.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
									// real 파일명, 경로생성
									fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
									// if(arRprstImgFileNm[i].indexOf("_") < 0){
									// fileName = scCnrSn.toString() + "_" +
									// scCnrSetSn.toString() + "_" +
									// conttTurn.toString() + "_" + arLangCd[i]
									// + "_"+ this.getCurrTimeForImg() + "." +
									// fileExt;
									// } else {
									// fileName = arRprstImgFileNm[i];
									// }
									if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
										fileName = arRprstImgFileNm[i];
										fileUrl = arRprstImgFileUrl[i];
									} else {
										fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
												+ conttTurn.toString() + "_" + arLangCd[i] + "_"
												+ this.getCurrTimeForImg() + "." + fileExt;
										fileUrl = path[0];
									}

									if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
										lang.setImgFileNm(fileName);
										lang.setImgFileUrl(fileUrl);
									} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
										lang.setMoviImgFileNm(fileName);
										lang.setMoviImgUrl(fileUrl);
									}

									realFileName[i] = fileName;
								} else {
									realFileName[i] = "";
								}

								if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
									if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
										lang.setImgAltrtvCont(arRprstImgAltrtvCont[i]);
									} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
										lang.setMoviImgAltrtvCont(arRprstImgAltrtvCont[i]);
									}
								}
								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								if (StringService.isNotEmpty(arRprstImgFileNm[i])
										|| StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
									displayCornerContensService.insertCornerContentLang(lang);
								}

							}
						}
						for (int j = 0; j < arLangCd.length; j++) {

							if (arLangCd[j].equals("ENG") || arLangCd[j].equals("CHI")) {
								if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
									DspCnrConttLang lang = new DspCnrConttLang();
									BeanUtils.copyProperties(dspCnrContt, lang);
									lang.setRevSn(scRevSn);
									lang.setCnrSn(scCnrSn);
									lang.setCnrSetSn(scCnrSetSn);
									lang.setConttTurn(conttTurn);
									lang.setLangCd(arLangCd[j]);
									lang.setRegtrId(loginId);
									lang.setUdterId(loginId);
									displayCornerContensService.saveConttLangImg(lang);
								}
							}

						}
						// 마우스오버이미지 추가
						if (arImgOvFileNm != null && arImgOvFileNm.length > 1) {
							for (int i = 1; i < arImgOvFileNm.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								if (StringService.isNotEmpty(arImgOvFileNm[i])) {

									// real 파일명, 경로생성
									fileExt = arImgOvFileNm[i].substring(arImgOvFileNm[i].lastIndexOf(".") + 1);
									// if(arImgOvFileNm[i].indexOf("_") < 0){
									// fileName = scCnrSn.toString() + "_" +
									// scCnrSetSn.toString() + "_" +
									// conttTurn.toString() + "_" + arLangCd[i]
									// + "_OV" + "_"+ this.getCurrTimeForImg() +
									// "." + fileExt;
									// } else {
									// fileName = arImgOvFileNm[i];
									// }
									if (StringService.isNotEmpty(arImgOvFileUrl[i])) {
										fileName = arImgOvFileNm[i];
										fileUrl = arImgOvFileUrl[i];
									} else {
										fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
												+ conttTurn.toString() + "_" + arLangCd[i] + "_OV" + "_"
												+ this.getCurrTimeForImg() + "." + fileExt;
										fileUrl = path[0];
									}

									lang.setImgOvFileNm(fileName);
									lang.setImgOvFileUrl(fileUrl);

									realOvFileName[i] = fileName;
								} else {
									realOvFileName[i] = "";
								}

								if (StringService.isNotEmpty(arImgOvAltrtvCont[i])) {
									lang.setImgOvAltrtvCont(arImgOvAltrtvCont[i]);
								}
								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								if (StringService.isNotEmpty(arImgOvFileNm[i])
										|| StringService.isNotEmpty(arImgOvAltrtvCont[i])) {
									displayCornerContensService.saveConttLangOvImg(lang);
								}
							}
						}

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						// 이미지 맵 & 이미지 설명 다국어 추가 등록
						if ((arImgMapCont != null && arImgMapCont.length > 1)
								|| (arImgDscr != null && arImgDscr.length > 1)) {
							for (int i = 1; i < arLangCd.length; i++) {

								DspCnrConttLang lang = new DspCnrConttLang();

								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								// base 프로젝트 단 DTO 추가 수정
								if (arImgMapCont != null && StringService.isNotEmpty(arImgMapCont[i])) {
									lang.setImgMapCont(arImgMapCont[i]);
								}

								if (arImgDscr != null && StringService.isNotEmpty(arImgDscr[i])) {
									lang.setImgDscr(arImgDscr[i]);
								}

								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								// 추가된 컬럼에 대해서 쿼리문 변경
								if ((arImgMapCont != null && StringService.isNotEmpty(arImgMapCont[i]))
										|| (arImgDscr != null && StringService.isNotEmpty(arImgDscr[i]))) {
									displayCornerContensService.saveConttLangContents(lang);
								}
							}
						}

						result++;

						if (rprstImgFile != null && rprstImgFile.size() > 0) {
							int moveFileCnt = this.moveTempImage(uploadPath, realFileName, fileUrl,
									getUploadImageUrlPath(), arRprstImgFileNm);

							if (arImgOvFileNm != null) {
								moveFileCnt += this.moveTempImage(uploadPath, realOvFileName, fileUrl,
										getUploadImageUrlPath(), arImgOvFileNm);
							}
							if (rprstImgFile.size() != moveFileCnt) {
								result = 0;
							}
						}

					}

					// 전시대상 설정 디폴트 insert
					DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO = new DspCnrConttDspTgtBoDTO();
					dspCnrConttDspTgtBoDTO.setScRevSn(scRevSn);
					dspCnrConttDspTgtBoDTO.setScCnrSn(scCnrSn);
					dspCnrConttDspTgtBoDTO.setScCnrSetSn(scCnrSetSn);
					List<Integer> conttTurnList = new ArrayList<Integer>();
					conttTurnList.add(conttTurn);
					dspCnrConttDspTgtBoDTO.setScConttTurn(conttTurnList);
					dspCnrConttDspTgtBoDTO.setLang(dspCnrConttScBoDTO.getLang());
					dspCnrConttDspTgtBoDTO.setMallId(dspCnrConttScBoDTO.getMallId());

					// 전시대상설정 - 다국어
					List<String> arTgtLangCd = dspCnrConttScBoDTO.getArTgtLangCd();
					dspCnrConttDspTgtBoDTO.setArLangCd(arTgtLangCd);

					displayCornerContensService.saveContentDspTgt(dspCnrConttDspTgtBoDTO);

				}
			}

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateCornerContent
	 * (com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public void updateCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile) {
		try {
			int result = 0;
			Integer conttTurn = null;
			if (dspCnrConttScBoDTO != null) {

				String loginId = BOSecurityUtil.getLoginId();

				DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				dspCnrContt.setUdterId(loginId);

				String conttTp = dspCnrContt.getConttTpCd();

				if (StringService.isNotEmpty(conttTp)) {

					// 컨텐트 순번
					conttTurn = dspCnrContt.getConttTurn();

					// 전시기간
					String inBegDt = dspCnrConttScBoDTO.getInBegDt();
					String inEndDt = dspCnrConttScBoDTO.getInEndDt();
					SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					dspCnrContt.setDspBegDt(simplDateFormat.parse(inBegDt));
					dspCnrContt.setDspEndDt(simplDateFormat.parse(inEndDt));

					// HTML
					if (conttTp.equals(DisplayEnum.CONTT_TP.HTML.toString())) {
						String mallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
						String[] path = getCnrConttFilePath(dspCnrConttScBoDTO);
						String uploadPath = path[1];
						String commitResourcePath = this.bucketName + ":" + getSaveImagePath(mallId) + uploadPath;
						
						String htmlCont = dspCnrConttScBoDTO.getArHtmlCont()[0];
						if(StringService.contains(htmlCont, "uncommited")) {
							htmlCont = editorService.commitContentsImagesFromTemp(htmlCont, commitResourcePath, true);
						}
						
						String[] arLangCd = dspCnrConttScBoDTO.getArLangCd();

						dspCnrContt.setHtmlCont(htmlCont);
						displayCornerContensService.updateCornerContent(dspCnrContt);

						String[] arHtmlCont = dspCnrConttScBoDTO.getArHtmlCont();
						// 컨텐츠 언어 등록
						if (arHtmlCont != null && arHtmlCont.length > 1) {
							for (int i = 1; i < arHtmlCont.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(dspCnrContt.getRevSn());
								lang.setCnrSn(dspCnrContt.getCnrSn());
								lang.setCnrSetSn(dspCnrContt.getCnrSetSn());
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								lang.setHtmlSj(dspCnrContt.getHtmlSj());
								
								htmlCont = arHtmlCont[i];
								if(StringService.contains(htmlCont, "uncommited")) {
									htmlCont = editorService.commitContentsImagesFromTemp(htmlCont, commitResourcePath, true);
								}
								
								lang.setHtmlCont(htmlCont);
								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								DspCnrConttLang data = displayCornerContensService.selectDspCnrConttLang(lang);
								if (data != null) {
									displayCornerContensService.updateCornerContentLang(lang);
								} else {
									displayCornerContensService.insertCornerContentLang(lang);
								}
							}
						}
						result++;
					}
					// 이미지배너
					else if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
							|| conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {

						String[] arLangCd = dspCnrConttScBoDTO.getArLangCd();

						Long scRevSn = dspCnrContt.getRevSn();
						Long scCnrSn = dspCnrContt.getCnrSn();
						Long scCnrSetSn = dspCnrContt.getCnrSetSn();

						String[] path = getCnrConttFilePath(dspCnrConttScBoDTO);
						String fileUrl = path[0];
						String uploadPath = path[1];

						String[] arRprstImgFileNm = dspCnrConttScBoDTO.getArRprstImgFileNm();
						String[] arRprstImgFileUrl = dspCnrConttScBoDTO.getArRprstImgFileUrl();
						String[] arRprstImgAltrtvCont = dspCnrConttScBoDTO.getArRprstImgAltrtvCont();

						String[] arImgOvFileNm = dspCnrConttScBoDTO.getArImgOvFileNm();
						String[] arImgOvFileUrl = dspCnrConttScBoDTO.getArImgOvFileUrl();
						String[] arImgOvAltrtvCont = dspCnrConttScBoDTO.getArImgOvAltrtvCont();

						String fileName = "";
						String fileExt = "";
						String[] realFileName = new String[arLangCd.length];

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						String[] arImgMapCont = dspCnrConttScBoDTO.getArImgMapCont();
						String[] arImgDscr = dspCnrConttScBoDTO.getArImgDscr();

						if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
							// real 파일명, 경로생성
							if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
								fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
								// 코너번호_코너셋트번호_컨텐츠순번_언어코드.ext
								// if(arRprstImgFileNm[0].indexOf("_") < 0) {
								// fileName = scCnrSn.toString() + "_" +
								// scCnrSetSn.toString() + "_" +
								// conttTurn.toString() + "_" + arLangCd[0] +
								// "_"+ this.getCurrTimeForImg() + "." +
								// fileExt;
								// }
								// else {
								// fileName = arRprstImgFileNm[0];
								// }

								if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
									fileName = arRprstImgFileNm[0];
									fileUrl = arRprstImgFileUrl[0];
								} else {
									fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
											+ conttTurn.toString() + "_" + arLangCd[0] + "_" + this.getCurrTimeForImg()
											+ "." + fileExt;
									fileUrl = path[0];
								}

								if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
									dspCnrContt.setImgFileNm(fileName);
									dspCnrContt.setImgFileUrl(fileUrl);
								} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
									dspCnrContt.setMoviImgFileNm(fileName);
									dspCnrContt.setMoviImgUrl(fileUrl);
								}

								realFileName[0] = fileName;
							} else {
								dspCnrContt.setImgFileNm("");
								dspCnrContt.setImgFileUrl("");

								realFileName[0] = "";
							}
						}

						// 마우스오버이미지 추가
						String[] realOvFileName = new String[arLangCd.length];

						if (arImgOvFileNm != null && arImgOvFileNm.length > 0) {
							// real 파일명, 경로생성
							if (StringService.isNotEmpty(arImgOvFileNm[0])) {
								fileExt = arImgOvFileNm[0].substring(arImgOvFileNm[0].lastIndexOf(".") + 1);
								// 코너번호_코너셋트번호_컨텐츠순번_언어코드_OV.ext
								// if(arImgOvFileNm[0].indexOf("_") < 0) {
								// fileName = scCnrSn.toString() + "_" +
								// scCnrSetSn.toString() + "_" +
								// conttTurn.toString() + "_" + arLangCd[0]
								// +"_OV" + "_"+ this.getCurrTimeForImg() + "."
								// + fileExt;
								// }
								// else {
								// fileName = arImgOvFileNm[0];
								// }
								if (StringService.isNotEmpty(arImgOvFileUrl[0])) {
									fileName = arImgOvFileNm[0];
									fileUrl = arImgOvFileUrl[0];
								} else {
									fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
											+ conttTurn.toString() + "_" + arLangCd[0] + "_OV" + "_"
											+ this.getCurrTimeForImg() + "." + fileExt;
									fileUrl = path[0];
								}

								dspCnrContt.setImgOvFileNm(fileName);
								dspCnrContt.setImgOvFileUrl(fileUrl);

								realOvFileName[0] = fileName;
							} else {
								dspCnrContt.setImgOvFileNm("");
								dspCnrContt.setImgOvFileUrl("");

								realOvFileName[0] = "";
							}
						}

						if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {

							if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
								dspCnrContt.setImgAltrtvCont(arRprstImgAltrtvCont[0]);
							} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
								dspCnrContt.setMoviImgAltrtvCont(arRprstImgAltrtvCont[0]);
							}

						}

						// 마우스오버이미지 대체텍스트 추가
						if (arImgOvAltrtvCont != null && StringService.isNotEmpty(arImgOvAltrtvCont[0])) {
							dspCnrContt.setImgOvAltrtvCont(arImgOvAltrtvCont[0]);
						}

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						if (arImgMapCont != null && StringService.isNotEmpty(arImgMapCont[0])) {
							dspCnrContt.setImgMapCont(arImgMapCont[0]);
						}

						if (arImgDscr != null && StringService.isNotEmpty(arImgDscr[0])) {
							dspCnrContt.setImgDscr(arImgDscr[0]);
						}

						// 한국어 수정
						displayCornerContensService.updateCornerContent(dspCnrContt);

						// 컨텐트 언어 등록
						if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
							for (int i = 1; i < arRprstImgFileNm.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								if (StringService.isNotEmpty(arRprstImgFileNm[i])) {

									// real 파일명, 경로생성
									fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);

									// if(arRprstImgFileNm[i].indexOf("_") < 0)
									// {
									// fileName = scCnrSn.toString() + "_" +
									// scCnrSetSn.toString() + "_" +
									// conttTurn.toString() + "_" + arLangCd[i]
									// + "_"+ this.getCurrTimeForImg() + "." +
									// fileExt;
									// }
									// else {
									// fileName = arRprstImgFileNm[i];
									// }
									if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
										fileName = arRprstImgFileNm[i];
										fileUrl = arRprstImgFileUrl[i];
									} else {
										fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
												+ conttTurn.toString() + "_" + arLangCd[i] + "_"
												+ this.getCurrTimeForImg() + "." + fileExt;
										fileUrl = path[0];
									}

									if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
										lang.setImgFileNm(fileName);
										lang.setImgFileUrl(fileUrl);
									} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
										lang.setMoviImgFileNm(fileName);
										lang.setMoviImgUrl(fileUrl);
									}

									realFileName[i] = fileName;
								} else {
									realFileName[i] = "";
								}

								if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
									if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
										if (conttTp.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())) {
											lang.setImgAltrtvCont(arRprstImgAltrtvCont[i]);
										} else if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {
											lang.setMoviImgAltrtvCont(arRprstImgAltrtvCont[i]);
										}
									}

								}
								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								// if(StringService.isNotEmpty(arRprstImgFileNm[i])||StringService.isNotEmpty(arRprstImgAltrtvCont[i]))
								// {
								displayCornerContensService.saveConttLangImg(lang);
								// }
							}
						}

						for (int j = 0; j < arLangCd.length; j++) {

							if (arLangCd[j].equals("ENG") || arLangCd[j].equals("CHI")) {
								if (conttTp.equals(DisplayEnum.CONTT_TP.MOVI.toString())) {

									DspCnrConttLang lang = new DspCnrConttLang();
									BeanUtils.copyProperties(dspCnrContt, lang);
									lang.setRevSn(scRevSn);
									lang.setCnrSn(scCnrSn);
									lang.setCnrSetSn(scCnrSetSn);
									lang.setConttTurn(conttTurn);
									lang.setLangCd(arLangCd[j]);
									lang.setRegtrId(loginId);
									lang.setUdterId(loginId);
									displayCornerContensService.saveConttLangImg(lang);
								}
							}

						}
						// 마우스오버이미지 추가
						if (arImgOvFileNm != null && arImgOvFileNm.length > 1) {
							for (int i = 1; i < arImgOvFileNm.length; i++) {
								DspCnrConttLang lang = new DspCnrConttLang();
								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								if (StringService.isNotEmpty(arImgOvFileNm[i])) {

									// real 파일명, 경로생성
									fileExt = arImgOvFileNm[i].substring(arImgOvFileNm[i].lastIndexOf(".") + 1);

									// if(arImgOvFileNm[i].indexOf("_") < 0) {
									// fileName = scCnrSn.toString() + "_" +
									// scCnrSetSn.toString() + "_" +
									// conttTurn.toString() + "_" + arLangCd[i]
									// + "_OV" + "_"+ this.getCurrTimeForImg() +
									// "." + fileExt;
									// }
									// else {
									// fileName = arImgOvFileNm[i];
									// }
									if (StringService.isNotEmpty(arImgOvFileUrl[i])) {
										fileName = arImgOvFileNm[i];
										fileUrl = arImgOvFileUrl[i];
									} else {
										fileName = scCnrSn.toString() + "_" + scCnrSetSn.toString() + "_"
												+ conttTurn.toString() + "_" + arLangCd[i] + "_OV" + "_"
												+ this.getCurrTimeForImg() + "." + fileExt;
										fileUrl = path[0];
									}

									lang.setImgOvFileNm(fileName);
									lang.setImgOvFileUrl(fileUrl);

									realOvFileName[i] = fileName;
								} else {
									realOvFileName[i] = "";
								}

								if (StringService.isNotEmpty(arImgOvAltrtvCont[i])) {
									lang.setImgOvAltrtvCont(arImgOvAltrtvCont[i]);
								}
								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								// if(StringService.isNotEmpty(arImgOvFileNm[i])||StringService.isNotEmpty(arImgOvAltrtvCont[i]))
								// {
								displayCornerContensService.saveConttLangOvImg(lang);
								// }
							}
						}

						// 20160512_김병철_sr#19452 [PLGRIM SHOP 메인개편 추가 컬럼 다국어 처리]
						// 이미지 맵 & 이미지 설명 다국어 추가 등록
						if ((arImgMapCont != null && arImgMapCont.length > 1)
								|| (arImgDscr != null && arImgDscr.length > 1)) {
							for (int i = 1; i < arLangCd.length; i++) {

								DspCnrConttLang lang = new DspCnrConttLang();

								lang.setRevSn(scRevSn);
								lang.setCnrSn(scCnrSn);
								lang.setCnrSetSn(scCnrSetSn);
								lang.setConttTurn(conttTurn);
								lang.setLangCd(arLangCd[i]);

								// base 프로젝트 단 DTO 추가 수정
								if (arImgMapCont != null && StringService.isNotEmpty(arImgMapCont[i])) {
									lang.setImgMapCont(arImgMapCont[i]);
								}

								if (arImgDscr != null && StringService.isNotEmpty(arImgDscr[i])) {
									lang.setImgDscr(arImgDscr[i]);
								}

								lang.setRegtrId(loginId);
								lang.setUdterId(loginId);

								// 추가된 컬럼에 대해서 쿼리문 변경
								// if(StringService.isNotEmpty(arImgMapCont[i])
								// || StringService.isNotEmpty(arImgDscr[i])) {
								displayCornerContensService.saveConttLangContents(lang);
								// }
							}
						}

						result++;

						if (rprstImgFile != null && rprstImgFile.size() > 0) {
							int moveFileCnt = this.moveTempImage(uploadPath, realFileName, fileUrl,
									getUploadImageUrlPath(), arRprstImgFileNm);

							if (arImgOvFileNm != null) {
								moveFileCnt += this.moveTempImage(uploadPath, realOvFileName, fileUrl,
										getUploadImageUrlPath(), arImgOvFileNm);
							}
							if (rprstImgFile.size() != moveFileCnt) {
								result = 0;
							}
						}
					}

					// 전시대상설정 - 다국어
					List<String> arTgtLangCd = dspCnrConttScBoDTO.getArTgtLangCd();
					DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO = new DspCnrConttDspTgtBoDTO();
					DspCnrConttDspTgt dspCnrConttDspTgt = new DspCnrConttDspTgt();
					dspCnrConttDspTgt.setRevSn(dspCnrContt.getRevSn());
					dspCnrConttDspTgt.setCnrSn(dspCnrContt.getCnrSn());
					dspCnrConttDspTgt.setCnrSetSn(dspCnrContt.getCnrSetSn());
					dspCnrConttDspTgt.setConttTurn(dspCnrContt.getConttTurn());
					dspCnrConttDspTgt.setDspTgtTpCd(DisplayEnum.DSP_TGT_TP.LANG.toString());
					dspCnrConttDspTgtBoDTO.setDspCnrConttDspTgt(dspCnrConttDspTgt);
					dspCnrConttDspTgtBoDTO.setArLangCd(arTgtLangCd);

					displayCornerContensService.addContentDspTgt(dspCnrConttDspTgtBoDTO);
				}
			}

			// return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveCornerContentsList(java.util.List)
	 */
	@Override
	public void updateCornerContentsList(List<DspCnrConttScBoDTO> list) {
		try {
			displayCornerContensService.updateCornerContentList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveCornerContentsList(java.util.List, java.util.List)
	 */
	@Override
	public void saveCornerContentsList(List<DspCnrConttScBoDTO> instList, List<DspCnrConttScBoDTO> updList) {
		try {
			// 등록 contt, lang
			displayCornerContensService.insertCornerContentGridList(instList);

			// 수정 contt, lang
			displayCornerContensService.updateCornerContentList(updList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * savePreviewCornerContentsList(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public Long savePreviewCornerContentsList(DspCnrConttScBoDTO dspCnrConttScBoDTO,
			List<DspCnrConttScBoDTO> gridList) {
		try {
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			// 미리보기 리비전 Set
			if (gridList != null && gridList.size() > 0) {
				for (DspCnrConttScBoDTO d : gridList) {
					d.setScPrvwSn(prvwRevSnPge.toString());
				}
			}

			GridCommandTemplate template = new GridCommandTemplate();
			template.execute(gridList, new GridCommandAwareCallback<DspCnrConttScBoDTO>() {
				@Override
				public void commandHandler(List<DspCnrConttScBoDTO> createList, List<DspCnrConttScBoDTO> updateList,
						List<DspCnrConttScBoDTO> deleteList, List<DspCnrConttScBoDTO> dataList) throws Exception {
					saveCornerContentsList(createList, updateList);
				}
			});

			return prvwRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * insertCornerContentList(java.util.List)
	 */
	@Override
	public String[] insertCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrConttExt> list) {
		try {
			return displayCornerContensService.insertCornerContentList(dspCnrConttScBoDTO, list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deleteCornerContentsList(java.util.List)
	 */
	@Override
	public void deleteCornerContentsList(List<DspCnrConttScBoDTO> list) {
		try {
			displayCornerContensService.deleteCornerContent(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveContentDspTgt(
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public void saveContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO) {
		try {
			String mallId = dspCnrConttDspTgtBoDTO.getMallId();
			String scConttTpCd = dspCnrConttDspTgtBoDTO.getScConttTpCd();
			if ((StringService.isNotEmpty(mallId) && mallId.equals("DXM")) && (StringService.isNotEmpty(scConttTpCd)
					&& (scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
							|| scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString())))) {
				displayCornerContensService.validContentLangDspTgt(dspCnrConttDspTgtBoDTO);
			}

			displayCornerContensService.saveContentDspTgt(dspCnrConttDspTgtBoDTO);
		} catch (DspConttValidException ve) {
			throw new DspConttValidException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updatePlanList(java
	 * .util.List)
	 */
	@Override
	public void updatePlanList(List<DspPromtBoDTO> list) {
		try {
			displayPlanService.updatePlanList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updatePromtAprvStat
	 * (java.util.List)
	 */
	public void updatePromtAprvStat(List<DspPromtBoDTO> list) {
		try {
			displayPlanService.updatePromtAprvStat(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertPromtInfo(com
	 * .plgrim.ncp.biz.display.data.DspPromtBoDTO)
	 */
	@Override
	public long insertPromtInfo(DspPromtBoDTO dspPromtBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspPromt
			DspPromt dspPromt = dspPromtBoDTO.getDspPromt();
			dspPromt.setRegtrId(loginId);
			dspPromt.setUdterId(loginId);

			// 몰구분
			String scAplMallId = dspPromtBoDTO.getScAplMallId();

			// 언어코드
			String[] arLangCdNm = dspPromtBoDTO.getArLangCdNm();
			// 기획전명
			String[] arPromtNm = dspPromtBoDTO.getArPromtNm();
			// 기획전 설명
			String[] arPromtDscr = dspPromtBoDTO.getArPromtDscr();
			// 브랜드목록
			// List<String> arMdBrndCd = dspPromtBoDTO.getArMdBrndCd();
			String mdBrndCd = dspPromtBoDTO.getMdBrndCd();

			// 1단이미지
			// PC 대표이미지
			String[] arPcImgAltrtvCont = dspPromtBoDTO.getArPcImgAltrtvCont();
			String[] arPcImgFileNm = dspPromtBoDTO.getArPcImgFileNm();
			String[] arPcImgFileUrl = dspPromtBoDTO.getArPcImgFileUrl();

			// Mobile 대표이미지
			String[] arMobileImgAltrtvCont = dspPromtBoDTO.getArMobileImgAltrtvCont();
			String[] arMobileImgFileNm = dspPromtBoDTO.getArMobileImgFileNm();
			String[] arMobileImgFileUrl = dspPromtBoDTO.getArMobileImgFileUrl();

			// 2단이미지
			// PC 대표이미지
			String[] ar2pcedPcImgAltrtvCont = dspPromtBoDTO.getAr2pcePcImgAltrtvCont();
			String[] ar2pcePcImgFileNm = dspPromtBoDTO.getAr2pcePcImgFileNm();
			String[] ar2pcePcImgFileUrl = dspPromtBoDTO.getAr2pcePcImgFileUrl();

			// Mobile 대표이미지
			String[] ar2pceMobileImgAltrtvCont = dspPromtBoDTO.getAr2pceMobileImgAltrtvCont();
			String[] ar2pceMobileImgFileNm = dspPromtBoDTO.getAr2pceMobileImgFileNm();
			String[] ar2pceMobileImgFileUrl = dspPromtBoDTO.getAr2pceMobileImgFileUrl();

			// 상품상세용 이미지 - PC
			String[] arPcGodImgAltrtvCont = dspPromtBoDTO.getArPcGodImgAltrtvCont();
			String[] arPcGodImgFileNm = dspPromtBoDTO.getArPcGodImgFileNm();
			String[] arPcGodImgFileUrl = dspPromtBoDTO.getArPcGodImgFileUrl();

			// 상품상세용 이미지 - Mobile
			String[] arMobileGodImgAltrtvCont = dspPromtBoDTO.getArMobileGodImgAltrtvCont();
			String[] arMobileGodImgFileNm = dspPromtBoDTO.getArMobileGodImgFileNm();
			String[] arMobileGodImgFileUrl = dspPromtBoDTO.getArMobileGodImgFileUrl();

			// 전시대상
			List<String> arTgtLangCd = dspPromtBoDTO.getArTgtLangCd();
			List<String> arGodEvlExpsrYn = dspPromtBoDTO.getArGodEvlExpsrYn();

			List<String> arDvcCd = dspPromtBoDTO.getArDvcCd();
			List<String> arTgtMbrAtrbCd = dspPromtBoDTO.getArTgtMbrAtrbCd();
			List<String> arTgtMbrTpCd = dspPromtBoDTO.getArTgtMbrTpCd();
			String tgtGrpco = dspPromtBoDTO.getTgtGrpco();
			// 그룹사 그리드
			List<DspPromtDspTgtBoDTO> grpCoList = dspPromtBoDTO.getGrpCoList();

			// tempFileList
			List<String> tempFileList = dspPromtBoDTO.getTempFileList();

			// 0. 기획전 번호 생성
			long promtSn = displayPlanService.getPromtSn();

			// 1. 기본정보 등록-dsp_promt
			// 1.0 기획전번호
			dspPromt.setPromtSn(promtSn);
			// 1.1 전시기간
			String inBegDt = dspPromtBoDTO.getInBegDt();
			String inEndDt = dspPromtBoDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			dspPromt.setDspBegDt(simplDateFormat.parse(inBegDt));
			dspPromt.setDspEndDt(simplDateFormat.parse(inEndDt));
			// 기획전명
			dspPromt.setPromtNm(XSSUtil.stripXSS(arPromtNm[0]));
			dspPromt.setPromtDscr(XSSUtil.stripXSS(arPromtDscr[0]));

			DspRevCpst dspRevCpst = dspPromtBoDTO.getDspRevCpst();

			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn(DisplayEnum.NO.toString());
			}
			dspPromt.setImgGodEvlPrioExpsrYn(DisplayEnum.NO.toString());
			displayPlanService.insertDspPromt(dspPromt);

			dspRevCpst.setRevSn(baseRevSn);
			dspRevCpst.setPromtSn(dspPromt.getPromtSn());
			dspRevCpst.setUseYn(DisplayEnum.YES.toString());
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 템플릿코너저장
			displayTemplateService.saveCnrTmplatInfoCnnc(promtSn + "", null, DisplayEnum.TMPLAT_TP.PROMT.toString(),
					dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(promtSn + "", null, DisplayEnum.TMPLAT_TP.PROMT.toString(),
						dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			// 대표이미지 -PC
			String[] path = getRprstImgFilePath(promtSn, "PROMT");
			String fileUrl = path[0];
			String uploadPath = path[1];

			// 1단이미지
			// PC 기획전 목록 이미지
			String[] pcRealFileName = new String[arLangCdNm.length];
			pcRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString(), "PC");

			// Mobile 기획전 목록 이미지
			String[] mobileRealFileName = new String[arLangCdNm.length];
			mobileRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString(), "MOBILE");

			// 2단이미지
			// PC 기획전 목록 이미지
			String[] pc2pceRealFileName = new String[arLangCdNm.length];
			pc2pceRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString(), "PC");

			// Mobile 기획전 목록 이미지
			String[] mobile2pceRealFileName = new String[arLangCdNm.length];
			mobile2pceRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString(), "MOBILE");

			// PC 상품상세 이미지
			String[] pcGodRealFileName = new String[arLangCdNm.length];
			pcGodRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString(), "PC");

			// Mobile 상품상세 이미지
			String[] mobileGodRealFileName = new String[arLangCdNm.length];
			mobileGodRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString(), "MOBILE");

			// 2. 기획전명 언어 등록 -PC (merge)
			for (int i = 1; i < arPromtNm.length; i++) {
				if (StringService.isNotEmpty(arPromtNm[i]) || StringService.isNotEmpty(arPromtDscr[i])) {
					DspPromtLang lang = new DspPromtLang();
					lang.setPromtSn(promtSn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setPromtNm(XSSUtil.stripXSS(arPromtNm[i]));
					lang.setPromtDscr(XSSUtil.stripXSS(arPromtDscr[i]));

					displayPlanService.updateDspPromtLangNm(lang);
				}
			}

			// 4. 기획전 브랜드 등록 (delete -> insert)
			// if(arMdBrndCd != null && arMdBrndCd.size()>0) {
			// displayPlanService.insertDspPromtBrnd(arMdBrndCd, promtSn);
			// }
			if (mdBrndCd != null && !mdBrndCd.equals("")) {
				List<String> arMdBrndCd = new ArrayList<String>();
				arMdBrndCd.add(mdBrndCd);
				displayPlanService.insertDspPromtBrnd(arMdBrndCd, promtSn);
			}

			// 5. 기획전 전시대상 등록 (delete -> insert)
			// displayPlanService.deleteDspPromtDspTgtInfo(dspPromtDspTgt);

			String lang = dspPromtBoDTO.getLang();
			// 전시대상 - 몰ID
			List<String> arTgtMall = new ArrayList<String>();
			if (StringService.isNotEmpty(scAplMallId)) {
				arTgtMall.add(scAplMallId);
			} else {
				arTgtMall.add(DisplayEnum.MALL.toString());
			}
			displayPlanService.insertDspPromtDspTgt(arTgtMall, DisplayEnum.DSP_TGT_TP.MALL_ID.toString(), promtSn,
					grpCoList);

			// 전시대상 - 언어
			if (arTgtLangCd != null && arTgtLangCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtLangCd, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			} else {
				List<String> arTgtLang = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
					arTgtLang.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtLang, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			}

			// 리뷰노출 여부
			if (arGodEvlExpsrYn != null && arGodEvlExpsrYn.size() > 0) {
				displayPlanService.updateGodEvlExpsrY(arGodEvlExpsrYn, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			}

			// 전시대상 - 디바이스
			if (arDvcCd != null && arDvcCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arDvcCd, DisplayEnum.DSP_TGT_TP.DVC.toString(), promtSn,
						grpCoList);
			} else {
				List<String> arDvc = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.DVC.toString(), lang)) {
					arDvc.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arDvc, DisplayEnum.DSP_TGT_TP.DVC.toString(), promtSn,
						grpCoList);
			}

			// 전시대상 - 회원 속성
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.contains("GRPCO")) {
				arTgtMbrAtrbCd.remove("GRPCO");
				arTgtMbrAtrbCd.add(tgtGrpco);
			}
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtMbrAtrbCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						promtSn, grpCoList);
			} else {
				List<String> arTgtMbrAtrb = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB_DATA.toString(), lang)) {
					arTgtMbrAtrb.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtMbrAtrb, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						promtSn, grpCoList);
			}

			// 전시대상 - 회원 유형
			if (arTgtMbrTpCd != null && arTgtMbrTpCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtMbrTpCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						promtSn, grpCoList);
			} else {
				List<String> arTgtMbrTp = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(), lang)) {
					arTgtMbrTp.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtMbrTp, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						promtSn, grpCoList);
			}

			// 6. 이미지파일 운영 경로로 이동 pc, mobile
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, pcRealFileName, fileUrl, getUploadImageUrlPath(),
						arPcImgFileNm);
			}

			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, mobileRealFileName, fileUrl, getUploadImageUrlPath(),
						arMobileImgFileNm);

			}

			if (ar2pcePcImgFileNm != null && ar2pcePcImgFileNm.length > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, pc2pceRealFileName, fileUrl, getUploadImageUrlPath(),
						ar2pcePcImgFileNm);
			}

			if (ar2pceMobileImgFileNm != null && ar2pceMobileImgFileNm.length > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, mobile2pceRealFileName, fileUrl, getUploadImageUrlPath(),
						ar2pceMobileImgFileNm);

			}

			if (arPcGodImgFileNm != null && arPcGodImgFileNm.length > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, pcGodRealFileName, fileUrl, getUploadImageUrlPath(),
						arPcGodImgFileNm);
			}

			if (arMobileGodImgFileNm != null && arMobileGodImgFileNm.length > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, mobileGodRealFileName, fileUrl, getUploadImageUrlPath(),
						arMobileGodImgFileNm);

			}

			return promtSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updatePromtInfo(com
	 * .plgrim.ncp.biz.display.data.DspPromtBoDTO)
	 */
	@Override
	public void updatePromtInfo(DspPromtBoDTO dspPromtBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspPromt
			DspPromt dspPromt = dspPromtBoDTO.getDspPromt();
			dspPromt.setUdterId(loginId);

			// 언어코드
			String[] arLangCdNm = dspPromtBoDTO.getArLangCdNm();
			// 기획전명
			String[] arPromtNm = dspPromtBoDTO.getArPromtNm();
			String[] arPromtDscr = dspPromtBoDTO.getArPromtDscr();
			// 브랜드목록
			String mdBrndCd = dspPromtBoDTO.getMdBrndCd();

			String rprst1pceImgYn = dspPromtBoDTO.getRprst1pceImgYn();
			String rprst2pceImgYn = dspPromtBoDTO.getRprst2pceImgYn();
			String godImgYn = dspPromtBoDTO.getGodImgYn();

			// 1단이미지
			// PC 대표이미지
			String[] arPcImgAltrtvCont = dspPromtBoDTO.getArPcImgAltrtvCont();
			String[] arPcImgFileNm = dspPromtBoDTO.getArPcImgFileNm();
			String[] arPcImgFileUrl = dspPromtBoDTO.getArPcImgFileUrl();

			// Mobile 대표이미지
			String[] arMobileImgAltrtvCont = dspPromtBoDTO.getArMobileImgAltrtvCont();
			String[] arMobileImgFileNm = dspPromtBoDTO.getArMobileImgFileNm();
			String[] arMobileImgFileUrl = dspPromtBoDTO.getArMobileImgFileUrl();

			// 2단이미지
			// PC 대표이미지
			String[] ar2pcePcImgAltrtvCont = dspPromtBoDTO.getAr2pcePcImgAltrtvCont();
			String[] ar2pcePcImgFileNm = dspPromtBoDTO.getAr2pcePcImgFileNm();
			String[] ar2pcePcImgFileUrl = dspPromtBoDTO.getAr2pcePcImgFileUrl();

			// Mobile 대표이미지
			String[] ar2pceMobileImgAltrtvCont = dspPromtBoDTO.getAr2pceMobileImgAltrtvCont();
			String[] ar2pceMobileImgFileNm = dspPromtBoDTO.getAr2pceMobileImgFileNm();
			String[] ar2pceMobileImgFileUrl = dspPromtBoDTO.getAr2pceMobileImgFileUrl();

			// 상품상세용 이미지 - PC
			String[] arPcGodImgAltrtvCont = dspPromtBoDTO.getArPcGodImgAltrtvCont();
			String[] arPcGodImgFileNm = dspPromtBoDTO.getArPcGodImgFileNm();
			String[] arPcGodImgFileUrl = dspPromtBoDTO.getArPcGodImgFileUrl();

			// 상품상세용 이미지 - Mobile
			String[] arMobileGodImgAltrtvCont = dspPromtBoDTO.getArMobileGodImgAltrtvCont();
			String[] arMobileGodImgFileNm = dspPromtBoDTO.getArMobileGodImgFileNm();
			String[] arMobileGodImgFileUrl = dspPromtBoDTO.getArMobileGodImgFileUrl();

			// 전시대상
			List<String> arTgtLangCd = dspPromtBoDTO.getArTgtLangCd();
			List<String> arDvcCd = dspPromtBoDTO.getArDvcCd();
			List<String> arTgtMbrAtrbCd = dspPromtBoDTO.getArTgtMbrAtrbCd();
			List<String> arTgtMbrTpCd = dspPromtBoDTO.getArTgtMbrTpCd();
			String tgtGrpco = dspPromtBoDTO.getTgtGrpco();
			List<String> arGodEvlExpsrYn = dspPromtBoDTO.getArGodEvlExpsrYn();
			// 그룹사 그리드
			List<DspPromtDspTgtBoDTO> grpCoList = dspPromtBoDTO.getGrpCoList();

			// tempFileList
			List<String> tempFileList = dspPromtBoDTO.getTempFileList();

			// 0. 기획전 번호 조회
			long promtSn = dspPromt.getPromtSn();

			// 1. 기본정보 수정-dsp_promt
			// 1.1 전시기간
			String inBegDt = dspPromtBoDTO.getInBegDt();
			String inEndDt = dspPromtBoDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			dspPromt.setDspBegDt(simplDateFormat.parse(inBegDt));
			dspPromt.setDspEndDt(simplDateFormat.parse(inEndDt));
			// 기획전명
			dspPromt.setPromtNm(XSSUtil.stripXSS(arPromtNm[0]));
			dspPromt.setPromtDscr(XSSUtil.stripXSS(arPromtDscr[0]));

			displayPlanService.updateDspPromt(dspPromt);

			// 2. 기획전명 언어 등록 -PC (merge)
			for (int i = 1; i < arLangCdNm.length; i++) {
				if (StringService.isNotEmpty(arPromtNm[i]) || StringService.isNotEmpty(arPromtDscr[i])) {
					DspPromtLang lang = new DspPromtLang();
					lang.setPromtSn(promtSn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setPromtNm(XSSUtil.stripXSS(arPromtNm[i]));
					lang.setPromtDscr(XSSUtil.stripXSS(arPromtDscr[i]));

					displayPlanService.updateDspPromtLangNm(lang);
				}
			}

			DspRevCpst dspRevCpst = dspPromtBoDTO.getDspRevCpst();
			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn(DisplayEnum.NO.toString());
			}

			dspRevCpst.setPromtSn(dspPromt.getPromtSn());
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 템플릿코너저장
			displayTemplateService.saveCnrTmplatInfoCnnc(promtSn + "", null, DisplayEnum.TMPLAT_TP.PROMT.toString(),
					dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(promtSn + "", null, DisplayEnum.TMPLAT_TP.PROMT.toString(),
						dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			// 대표이미지 -PC
			String[] path = getRprstImgFilePath(promtSn, "PROMT");
			String fileUrl = path[0];
			String uploadPath = path[1];

			// 1단
			// PC 기획전 목록 이미지
			String[] pcRealFileName = new String[arLangCdNm.length];
			pcRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString(), "PC");

			// Mobile 기획전 목록 이미지
			String[] mobileRealFileName = new String[arLangCdNm.length];
			if (StringService.isNotEmpty(rprst1pceImgYn) && rprst1pceImgYn.equals("Y")) {
				mobileRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
						DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString(), "MOBILE");
			}

			// 2단
			// PC 기획전 목록 이미지
			String[] pc2pceRealFileName = new String[arLangCdNm.length];
			pc2pceRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString(), "PC");

			// Mobile 기획전 목록 이미지
			String[] mobile2pceRealFileName = new String[arLangCdNm.length];
			if (StringService.isNotEmpty(rprst2pceImgYn) && rprst2pceImgYn.equals("Y")) {
				mobile2pceRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
						DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString(), "MOBILE");
			}

			// PC 상품상세 이미지
			String[] pcGodRealFileName = new String[arLangCdNm.length];
			pcGodRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
					DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString(), "PC");

			// Mobile 상품상세 이미지
			String[] mobileGodRealFileName = new String[arLangCdNm.length];
			if (StringService.isNotEmpty(godImgYn) && godImgYn.equals("Y")) {
				mobileGodRealFileName = setPromtImg(dspPromtBoDTO, path, arLangCdNm,
						DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString(), "MOBILE");
			}

			// 4. 기획전 브랜드 등록 (delete -> insert)
			if (mdBrndCd != null && !mdBrndCd.equals("")) {
				DspPromtBrnd dspPromtBrnd = new DspPromtBrnd();
				dspPromtBrnd.setPromtSn(promtSn);
				displayPlanService.deleteDspPromtBrndInfo(dspPromtBrnd);

				List<String> arMdBrndCd = new ArrayList<String>();
				arMdBrndCd.add(mdBrndCd);
				displayPlanService.insertDspPromtBrnd(arMdBrndCd, promtSn);
			}

			// 5. 기획전 전시대상 등록 (delete -> insert)
			DspPromtDspTgt dspPromtDspTgt = new DspPromtDspTgt();
			dspPromtDspTgt.setPromtSn(promtSn);
			displayPlanService.deleteDspPromtDspTgtInfo(dspPromtDspTgt);

			String scAplMallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
			String lang = dspPromtBoDTO.getLang();
			// 전시대상 - 몰ID
			List<String> arTgtMall = new ArrayList<String>();
			if (StringService.isNotEmpty(scAplMallId)) {
				arTgtMall.add(scAplMallId);
			} else {
				arTgtMall.add(DisplayEnum.MALL.toString());
			}
			displayPlanService.insertDspPromtDspTgt(arTgtMall, DisplayEnum.DSP_TGT_TP.MALL_ID.toString(), promtSn,
					grpCoList);

			// 전시대상 - 언어
			if (arTgtLangCd != null && arTgtLangCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtLangCd, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			} else {
				List<String> arTgtLang = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
					arTgtLang.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtLang, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			}

			// 리뷰노출 여부
			if (arGodEvlExpsrYn != null && arGodEvlExpsrYn.size() > 0) {
				displayPlanService.updateGodEvlExpsrN(arGodEvlExpsrYn, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
				displayPlanService.updateGodEvlExpsrY(arGodEvlExpsrYn, DisplayEnum.DSP_TGT_TP.LANG.toString(), promtSn,
						grpCoList);
			}

			// 전시대상 - 디바이스
			if (arDvcCd != null && arDvcCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arDvcCd, DisplayEnum.DSP_TGT_TP.DVC.toString(), promtSn,
						grpCoList);
			} else {
				List<String> arDvc = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.DVC.toString(), lang)) {
					arDvc.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arDvc, DisplayEnum.DSP_TGT_TP.DVC.toString(), promtSn,
						grpCoList);
			}

			// 전시대상 - 회원 속성
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.contains("GRPCO")) {
				arTgtMbrAtrbCd.remove("GRPCO");
				arTgtMbrAtrbCd.add(tgtGrpco);
			}
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtMbrAtrbCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						promtSn, grpCoList);
			} else {
				List<String> arTgtMbrAtrb = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB_DATA.toString(), lang)) {
					arTgtMbrAtrb.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtMbrAtrb, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						promtSn, grpCoList);
			}

			// 전시대상 - 회원 유형
			if (arTgtMbrTpCd != null && arTgtMbrTpCd.size() > 0) {
				displayPlanService.insertDspPromtDspTgt(arTgtMbrTpCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						promtSn, grpCoList);
			} else {
				List<String> arTgtMbrTp = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(), lang)) {
					arTgtMbrTp.add(cd.getCd());
				}
				displayPlanService.insertDspPromtDspTgt(arTgtMbrTp, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						promtSn, grpCoList);
			}

			// 6. 이미지파일 운영 경로로 이동 pc, mobile
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, pcRealFileName, fileUrl, getUploadImageUrlPath(),
						arPcImgFileNm);
			}

			if (StringService.isNotEmpty(rprst1pceImgYn) && rprst1pceImgYn.equals(DisplayEnum.YES.toString())) {
				if (tempFileList != null && tempFileList.size() > 0) {
					moveFileCnt += this.moveTempImage(uploadPath, mobileRealFileName, fileUrl, getUploadImageUrlPath(),
							arMobileImgFileNm);
				}
			}

			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, pc2pceRealFileName, fileUrl, getUploadImageUrlPath(),
						ar2pcePcImgFileNm);
			}

			if (StringService.isNotEmpty(rprst2pceImgYn) && rprst2pceImgYn.equals(DisplayEnum.YES.toString())) {
				if (tempFileList != null && tempFileList.size() > 0) {
					moveFileCnt += this.moveTempImage(uploadPath, mobile2pceRealFileName, fileUrl,
							getUploadImageUrlPath(), ar2pceMobileImgFileNm);
				}
			}

			if (arPcGodImgFileNm != null && arPcGodImgFileNm.length > 0) {
				if (tempFileList != null && tempFileList.size() > 0) {
					moveFileCnt += this.moveTempImage(uploadPath, pcGodRealFileName, fileUrl, getUploadImageUrlPath(),
							arPcGodImgFileNm);
				}
			}

			if (StringService.isNotEmpty(godImgYn) && godImgYn.equals(DisplayEnum.YES.toString())) {
				if (arMobileGodImgFileNm != null && arMobileGodImgFileNm.length > 0) {
					if (tempFileList != null && tempFileList.size() > 0) {
						moveFileCnt += this.moveTempImage(uploadPath, mobileGodRealFileName, fileUrl,
								getUploadImageUrlPath(), arMobileGodImgFileNm);
					}

				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * insertPromtSprtrInfo(com.plgrim.ncp.biz.display.data.DspPromtSprtrBoDTO)
	 */
	@Override
	public void insertPromtSprtrInfo(DspPromtSprtrBoDTO dpsPromtSprtrBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspPromtSprtr
			DspPromtSprtr dspPromtSprtr = dpsPromtSprtrBoDTO.getDspPromtSprtr();
			dspPromtSprtr.setRegtrId(loginId);
			dspPromtSprtr.setUdterId(loginId);

			// 언어코드
			String[] arLangCdNm = dpsPromtSprtrBoDTO.getArLangCdNm();
			// 기획전 구분타이틀명
			String[] arSprtrNm = dpsPromtSprtrBoDTO.getArSprtrNm();

			// 이미지
			String[] arRprstImgAltrtvCont = dpsPromtSprtrBoDTO.getArRprstImgAltrtvCont();
			String[] arRprstImgFileNm = dpsPromtSprtrBoDTO.getArRprstImgFileNm();
			String[] arRprstImgFileUrl = dpsPromtSprtrBoDTO.getArRprstImgFileUrl();

			// tempFileList
			List<String> tempFileList = dpsPromtSprtrBoDTO.getTempFileList();

			// 0. 기획전 구분타이틀 순번 번호 생성
			long promtSn = dspPromtSprtr.getPromtSn();
			Integer sprtrTurn = displayPlanService.getSprtrTurn(promtSn);
			dspPromtSprtr.setSprtrTurn(sprtrTurn);

			// 기획전 구분타이틀 텍스트 - 한국어
			dspPromtSprtr.setSprtrNm(arSprtrNm[0]);

			// 기획전 구분타이틀 이미지 - 한국어
			String[] path = getRprstImgFilePath(promtSn, "PROMT");
			String fileUrl = path[0] + sprtrPath;
			String uploadPath = path[1] + sprtrPath;

			String fileName = "";
			String fileExt = "";
			String[] realFileName = new String[arLangCdNm.length];

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
					// 기획전번호_구분자순번_언어코드.ext
					// if(arRprstImgFileNm[0].indexOf("_") < 0) {
					// fileName = promtSn + "_" + sprtrTurn + "_" +
					// arLangCdNm[0] + "_" + this.getCurrTimeForImg() + "." +
					// fileExt;
					// }
					// else {
					// fileName = arRprstImgFileNm[0];
					// }
					if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
						fileName = arRprstImgFileNm[0];
						fileUrl = arRprstImgFileUrl[0];
					} else {
						fileName = promtSn + "_" + sprtrTurn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg()
								+ "." + fileExt;
						fileUrl = path[0] + sprtrPath;
					}
					dspPromtSprtr.setSprtrImgFileNm(fileName);
					dspPromtSprtr.setSprtrImgFileUrl(fileUrl);

					realFileName[0] = fileName;
				} else {
					dspPromtSprtr.setSprtrImgFileNm("");
					dspPromtSprtr.setSprtrImgFileUrl("");

					realFileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspPromtSprtr.setSprtrImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}
			displayPlanService.insertDspPromtSprtr(dspPromtSprtr);

			// 기획전 구분타이틀 언어 - 텍스트 merge
			for (int i = 1; i < arSprtrNm.length; i++) {
				if (StringService.isNotEmpty(arSprtrNm[i])) {
					DspPromtSprtrLang lang = new DspPromtSprtrLang();
					lang.setPromtSn(promtSn);
					lang.setSprtrTurn(sprtrTurn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setSprtrNm(arSprtrNm[i]);
					lang.setRegtrId(loginId);
					lang.setUdterId(loginId);

					displayPlanService.saveDspPromtSprtrLangInfo(lang);
				}
			}

			// 기획전 구분타이틀 언어 - 이미지 merge
			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					DspPromtSprtrLang lang = new DspPromtSprtrLang();
					lang.setPromtSn(promtSn);
					lang.setSprtrTurn(sprtrTurn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setSprtrNm(arSprtrNm[i]);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
						// 기획전번호_구분타이틀순번_언어코드.ext
						// if(arRprstImgFileNm[i].indexOf("_") < 0) {
						// fileName = promtSn + "_"+ sprtrTurn +"_" +
						// arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "."
						// + fileExt;
						// }
						// else {
						// fileName = arRprstImgFileNm[i];
						// }
						if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
							fileName = arRprstImgFileNm[i];
							fileUrl = arRprstImgFileUrl[i];
						} else {
							fileName = promtSn + "_" + sprtrTurn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg()
									+ "." + fileExt;
							fileUrl = path[0] + sprtrPath;
						}
						lang.setSprtrImgFileNm(fileName);
						lang.setSprtrImgFileUrl(fileUrl);

						realFileName[i] = fileName;
					} else {
						realFileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						lang.setSprtrImgAltrtvCont(arRprstImgAltrtvCont[i]);
					}
					lang.setRegtrId(loginId);
					lang.setUdterId(loginId);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])
							|| StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						displayPlanService.saveDspPromtSprtrLangInfo(lang);
					}
				}
			}

			// 6. 이미지파일 운영 경로로 이동
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updatePromtSprtrInfo(com.plgrim.ncp.biz.display.data.DspPromtSprtrBoDTO)
	 */
	@Override
	public void updatePromtSprtrInfo(DspPromtSprtrBoDTO dspPromtSprtrBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspPromtSprtr
			DspPromtSprtr dspPromtSprtr = dspPromtSprtrBoDTO.getDspPromtSprtr();
			dspPromtSprtr.setUdterId(loginId);

			// 언어코드
			String[] arLangCdNm = dspPromtSprtrBoDTO.getArLangCdNm();
			// 기획전명
			String[] arSprtrNm = dspPromtSprtrBoDTO.getArSprtrNm();

			// 구분타이틀 이미지
			String[] arRprstImgAltrtvCont = dspPromtSprtrBoDTO.getArRprstImgAltrtvCont();
			String[] arRprstImgFileNm = dspPromtSprtrBoDTO.getArRprstImgFileNm();
			String[] arRprstImgFileUrl = dspPromtSprtrBoDTO.getArRprstImgFileUrl();

			// tempFileList
			List<String> tempFileList = dspPromtSprtrBoDTO.getTempFileList();

			// 0. 기획전 번호 조회
			long promtSn = dspPromtSprtr.getPromtSn();
			Integer sprtrTurn = dspPromtSprtr.getSprtrTurn();

			// 1. 기본정보 수정-dsp_promt_Sprtr
			// 구분타이틀명 - KOR
			dspPromtSprtr.setSprtrNm(arSprtrNm[0]);

			// 구분타이틀 이미지 - KOR
			String[] path = getRprstImgFilePath(promtSn, "PROMT");
			String fileUrl = path[0] + sprtrPath;
			String uploadPath = path[1] + sprtrPath;

			String fileName = "";
			String fileExt = "";
			String[] realFileName = new String[arLangCdNm.length];

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
					// 기획전번호_언어코드.ext
					// if(arRprstImgFileNm[0].indexOf("_") < 0) {
					// fileName = promtSn + "_"+ sprtrTurn +"_" + arLangCdNm[0]
					// + "_" + this.getCurrTimeForImg() + "." + fileExt;
					// }
					// else {
					// fileName = arRprstImgFileNm[0];
					// }
					if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
						fileName = arRprstImgFileNm[0];
						fileUrl = arRprstImgFileUrl[0];
					} else {
						fileName = promtSn + "_" + sprtrTurn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg()
								+ "." + fileExt;
						fileUrl = path[0] + sprtrPath;
					}
					dspPromtSprtr.setSprtrImgFileNm(fileName);
					dspPromtSprtr.setSprtrImgFileUrl(fileUrl);

					realFileName[0] = fileName;
				} else {
					dspPromtSprtr.setSprtrImgFileNm("");
					dspPromtSprtr.setSprtrImgFileUrl("");

					realFileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspPromtSprtr.setSprtrImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}

			displayPlanService.updateDspPromtSprtr(dspPromtSprtr);

			// 2. 기획전 구분타이틀 텍스트 언어 등록 merge
			for (int i = 1; i < arSprtrNm.length; i++) {
				// if(StringService.isNotEmpty(arSprtrNm[i])) {
				DspPromtSprtrLang lang = new DspPromtSprtrLang();
				lang.setPromtSn(promtSn);
				lang.setSprtrTurn(sprtrTurn);
				lang.setLangCd(arLangCdNm[i]);
				lang.setSprtrNm(arSprtrNm[i]);

				displayPlanService.saveDspPromtSprtrLangInfo(lang);
				// }
			}

			// 3. 기획전 구분타이틀 이미지 언어 등록 merge
			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					DspPromtSprtrLang lang = new DspPromtSprtrLang();
					lang.setPromtSn(promtSn);
					lang.setSprtrTurn(sprtrTurn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setSprtrNm(arSprtrNm[i]);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
						// 기획전번호_언어코드.ext
						// if(arRprstImgFileNm[i].indexOf("_") < 0) {
						// fileName = promtSn + "_"+ sprtrTurn +"_" +
						// arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "."
						// + fileExt;
						// }
						// else {
						// fileName = arRprstImgFileNm[i];
						// }
						if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
							fileName = arRprstImgFileNm[i];
							fileUrl = arRprstImgFileUrl[i];
						} else {
							fileName = promtSn + "_" + sprtrTurn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg()
									+ "." + fileExt;
							fileUrl = path[0] + sprtrPath;
						}
						lang.setSprtrImgFileNm(fileName);
						lang.setSprtrImgFileUrl(fileUrl);

						realFileName[i] = fileName;
					} else {
						realFileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						lang.setSprtrImgAltrtvCont(arRprstImgAltrtvCont[i]);
					}
					lang.setUdterId(loginId);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])
							|| StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						displayPlanService.saveDspPromtSprtrLangInfo(lang);
					}
				}
			}

			// 이미지파일 운영 경로로 이동
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updatePromtSprtrList(java.util.List)
	 */
	@Override
	public void updatePromtSprtrList(List<DspPromtSprtrBoDTO> list) {
		try {
			displayPlanService.updatePromtSprtrList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deletePromtSprtrList(java.util.List)
	 */
	@Override
	public void deletePromtSprtrList(List<DspPromtSprtrBoDTO> list) {
		try {
			displayPlanService.deletePromtSprtrList(list);
		} catch (NotDeletedPromtGodException ve) {
			throw new NotDeletedPromtGodException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertPromtGodList(
	 * java.util.List)
	 */
	@Override
	public String[] insertPromtGodList(List<DspPromtGod> list) {
		try {
			String[] result = displayPlanService.insertPromtGodList(list);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updatePromtGodList(
	 * java.util.List)
	 */
	@Override
	public void updatePromtGodList(List<DspPromtGodBoDTO> list) {
		try {
			displayPlanService.updatePromtGodList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updatePromtGodList(
	 * java.util.List)
	 */
	@Override
	public void updatePromtGodReviewSortCd(DspPromtBoDTO dspPromtBoDTO) throws Exception {
		displayPlanService.updatePromtGodReviewSortCd(dspPromtBoDTO);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deletePromtGodList(
	 * java.util.List)
	 */
	@Override
	public void deletePromtGodList(List<DspPromtGodBoDTO> list) {
		try {
			displayPlanService.deletePromtGodList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateSortSeqPromtGod(java.util.List, java.lang.Long, java.lang.Integer)
	 */
	@Override
	public List<DspPromtGodExt> updateSortSeqPromtGod(List<DspPromtGod> list, Long promtSn, Integer sprtrTurn) {
		try {
			List<DspPromtGodExt> errList = displayPlanService.updateSortSeqPromtGod(list, promtSn, sprtrTurn);
			return errList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateStrndList(
	 * java.util.List)
	 */
	@Override
	public void updateStrndList(List<DspStrndBoDTO> list) {
		try {
			displayStrendService.updateStrndList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deleteStrndList(
	 * java.util.List)
	 */
	@Override
	public void deleteStrndList(List<DspStrndBoDTO> list) {
		try {
			displayStrendService.deleteStrndList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertPromtInfo(com
	 * .plgrim.ncp.biz.display.data.DspPromtBoDTO)
	 */
	@Override
	public long insertStrndInfo(DspStrndBoDTO dspStrndBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspStrnd
			DspStrnd dspStrnd = dspStrndBoDTO.getDspStrnd();
			dspStrnd.setRegtrId(loginId);
			dspStrnd.setUdterId(loginId);

			// 언어코드
			String[] arLangCdNm = dspStrndBoDTO.getArLangCdNm();
			// S-TREND명
			String[] arStrndNm = dspStrndBoDTO.getArStrndNm();
			// S-TREND설명
			String[] arStrndDscr = dspStrndBoDTO.getArStrndDscr();

			// 대표이미지
			String[] arRprstImgAltrtvCont = dspStrndBoDTO.getArRprstImgAltrtvCont();
			String[] arRprstImgFileNm = dspStrndBoDTO.getArRprstImgFileNm();
			String[] arRprstImgFileUrl = dspStrndBoDTO.getArRprstImgFileUrl();
			// tempFileList
			List<String> tempFileList = dspStrndBoDTO.getTempFileList();

			// 대표이미지2
			String[] arRprstImg2AltrtvCont = dspStrndBoDTO.getArRprstImg2AltrtvCont();
			String[] arRprstImg2FileNm = dspStrndBoDTO.getArRprstImg2FileNm();
			String[] arRprstImg2FileUrl = dspStrndBoDTO.getArRprstImg2FileUrl();

			// 몰구분
			String scAplMallId = dspStrndBoDTO.getScAplMallId();
			// 전시대상
			List<String> arTgtLangCd = dspStrndBoDTO.getArTgtLangCd();
			List<String> arDvcCd = dspStrndBoDTO.getArDvcCd();
			List<String> arTgtMbrAtrbCd = dspStrndBoDTO.getArTgtMbrAtrbCd();
			List<String> arTgtMbrTpCd = dspStrndBoDTO.getArTgtMbrTpCd();
			String tgtGrpco = dspStrndBoDTO.getTgtGrpco();
			// 그룹사 그리드
			List<DspStrndDspTgtBoDTO> grpCoList = dspStrndBoDTO.getGrpCoList();

			// 0. S-TREND 번호 생성
			long strndSn = displayStrendService.getStrndSn();

			// 1. 기본정보 등록-dsp_strnd
			// 1.0 S-TREND번호
			dspStrnd.setStrndSn(strndSn);
			// 1.1 전시기간
			String inBegDt = dspStrndBoDTO.getInBegDt();
			String inEndDt = dspStrndBoDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			dspStrnd.setDspBegDt(simplDateFormat.parse(inBegDt));
			dspStrnd.setDspEndDt(simplDateFormat.parse(inEndDt));
			// S-TREND명
			dspStrnd.setStrndNm(arStrndNm[0]);
			// S-TREND설명
			dspStrnd.setStrndDscr(arStrndDscr[0]);

			// 대표이미지
			String[] path = getRprstImgFilePath(strndSn, "STRND");
			String fileUrl = path[0];
			String uploadPath = path[1];

			String fileName = "";
			String fileExt = "";
			String[] realFileName = new String[arLangCdNm.length];

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
					// S-TREND번호_언어코드.ext

					// if(arRprstImgFileNm[0].indexOf("_") < 0) {
					// fileName = strndSn + "_"+ arLangCdNm[0] + "_" +
					// this.getCurrTimeForImg() + "." + fileExt;
					// }
					// else {
					// fileName = arRprstImgFileNm[0];
					// }
					if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
						fileName = arRprstImgFileNm[0];
						fileUrl = arRprstImgFileUrl[0];
					} else {
						fileName = strndSn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg() + "." + fileExt;
						fileUrl = path[0];
					}
					dspStrnd.setRprstImgFileNm(fileName);
					dspStrnd.setRprstImgFileUrl(fileUrl);

					realFileName[0] = fileName;
				} else {
					dspStrnd.setRprstImgFileNm("");
					dspStrnd.setRprstImgFileUrl("");

					realFileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspStrnd.setRprstImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}

			String[] real2FileName = new String[arLangCdNm.length];

			if (arRprstImg2FileNm != null && arRprstImg2FileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImg2FileNm[0])) {
					fileExt = arRprstImg2FileNm[0].substring(arRprstImg2FileNm[0].lastIndexOf(".") + 1);

					if (StringService.isNotEmpty(arRprstImg2FileUrl[0])) {
						fileName = arRprstImg2FileNm[0];
						fileUrl = arRprstImg2FileUrl[0];
					} else {
						fileName = "IMG2_" + strndSn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg() + "."
								+ fileExt;
						fileUrl = path[0];
					}
					dspStrnd.setRprstImg2FileNm(fileName);
					dspStrnd.setRprstImg2FileUrl(fileUrl);

					real2FileName[0] = fileName;
				} else {
					dspStrnd.setRprstImg2FileNm("");
					dspStrnd.setRprstImg2FileUrl("");

					real2FileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImg2AltrtvCont != null && StringService.isNotEmpty(arRprstImg2AltrtvCont[0])) {
				dspStrnd.setRprstImg2AltrtvCont(arRprstImg2AltrtvCont[0]);
			}

			displayStrendService.insertDspStrnd(dspStrnd);

			DspRevCpst dspRevCpst = dspStrndBoDTO.getDspRevCpst();

			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn(DisplayEnum.NO.toString());
			}

			dspRevCpst.setRevSn(baseRevSn);
			dspRevCpst.setStrndSn(strndSn);
			dspRevCpst.setUseYn(DisplayEnum.YES.toString());
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 템플릿코너저장
			displayTemplateService.saveCnrTmplatInfoCnnc(strndSn + "", null, DisplayEnum.TMPLAT_TP.STRND.toString(),
					dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(strndSn + "", null, DisplayEnum.TMPLAT_TP.STRND.toString(),
						dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			// 2. S-TREND명 언어 등록 (merge)
			for (int i = 1; i < arStrndNm.length; i++) {
				DspStrndLang lang = new DspStrndLang();
				lang.setStrndSn(strndSn);
				lang.setRegtrId(loginId);
				lang.setUdterId(loginId);
				lang.setLangCd(arLangCdNm[i]);
				lang.setStrndNm(arStrndNm[i]);

				displayStrendService.updateDspStrndLangNm(lang);
			}
			// ** S-TREND설명 언어 등록 (merge)
			for (int i = 1; i < arStrndDscr.length; i++) {
				DspStrndLang lang = new DspStrndLang();
				lang.setStrndSn(strndSn);
				lang.setUdterId(loginId);
				lang.setLangCd(arLangCdNm[i]);
				lang.setStrndNm(arStrndNm[i]);
				lang.setStrndDscr(arStrndDscr[i]);

				displayStrendService.updateDspStrndLangNm(lang);
			}

			// 3. S-TREND이미지 언어별 -(merge)
			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					DspStrndLang lang = new DspStrndLang();
					lang.setStrndSn(strndSn);
					lang.setUdterId(loginId);
					lang.setStrndNm(arStrndNm[i]);
					lang.setStrndDscr(arStrndDscr[i]);
					lang.setLangCd(arLangCdNm[i]);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
						// S-TREND번호_언어코드.ext
						// if(arRprstImgFileNm[i].indexOf("_") < 0) {
						// fileName = strndSn + "_"+ arLangCdNm[i] + "_" +
						// this.getCurrTimeForImg() + "." + fileExt;
						// }
						// else {
						// fileName = arRprstImgFileNm[i];
						// }
						if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
							fileName = arRprstImgFileNm[i];
							fileUrl = arRprstImgFileUrl[i];
						} else {
							fileName = strndSn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "." + fileExt;
							fileUrl = path[0];
						}
						lang.setRprstImgFileNm(fileName);
						lang.setRprstImgFileUrl(fileUrl);

						realFileName[i] = fileName;
					} else {
						realFileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						lang.setRprstImgAltrtvCont(arRprstImgAltrtvCont[i]);
					}
					lang.setRegtrId(loginId);
					lang.setUdterId(loginId);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])
							|| StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						displayStrendService.updateDspStrndLangImg(lang);
					}
					// else {
					// if(StringService.isEmpty(arStrndNm[i])) {
					// displayStrendService.deleteDspStrndLang(lang);
					// }
					// }
				}
			}

			if (arRprstImg2FileNm != null && arRprstImg2FileNm.length > 1) {
				for (int i = 1; i < arRprstImg2FileNm.length; i++) {
					DspStrndLang lang = new DspStrndLang();
					lang.setStrndSn(strndSn);
					lang.setUdterId(loginId);
					lang.setLangCd(arLangCdNm[i]);

					if (StringService.isNotEmpty(arRprstImg2FileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImg2FileNm[i].substring(arRprstImg2FileNm[i].lastIndexOf(".") + 1);

						if (StringService.isNotEmpty(arRprstImg2FileUrl[i])) {
							fileName = arRprstImg2FileNm[i];
							fileUrl = arRprstImg2FileUrl[i];
						} else {
							fileName = "IMG2_" + strndSn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "."
									+ fileExt;
							fileUrl = path[0];
						}
						lang.setRprstImg2FileNm(fileName);
						lang.setRprstImg2FileUrl(fileUrl);

						real2FileName[i] = fileName;
					} else {
						real2FileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImg2AltrtvCont[i])) {
						lang.setRprstImg2AltrtvCont(arRprstImg2AltrtvCont[i]);
					}
					lang.setRegtrId(loginId);
					lang.setUdterId(loginId);

					if (StringService.isNotEmpty(arRprstImg2FileNm[i])
							|| StringService.isNotEmpty(arRprstImg2AltrtvCont[i])) {
						displayStrendService.updateDspStrndLangImg2(lang);
					}
					// else {
					// if(StringService.isEmpty(arStrndNm[i])) {
					// displayStrendService.deleteDspStrndLang(lang);
					// }
					// }
				}
			}

			// 전시대상 - 몰ID
			List<String> arTgtMall = new ArrayList<String>();
			if (StringService.isNotEmpty(scAplMallId)) {
				arTgtMall.add(scAplMallId);
			} else {
				arTgtMall.add(DisplayEnum.MALL.toString());
			}
			displayStrendService.insertDspStrndDspTgt(arTgtMall, DisplayEnum.DSP_TGT_TP.MALL_ID.toString(), strndSn,
					grpCoList);

			String lang = dspStrndBoDTO.getLang();
			// 전시대상 - 언어
			if (arTgtLangCd != null && arTgtLangCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtLangCd, DisplayEnum.DSP_TGT_TP.LANG.toString(), strndSn,
						grpCoList);
			} else {
				List<String> arTgtLang = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
					arTgtLang.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtLang, DisplayEnum.DSP_TGT_TP.LANG.toString(), strndSn,
						grpCoList);
			}

			// 전시대상 - 디바이스
			if (arDvcCd != null && arDvcCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arDvcCd, DisplayEnum.DSP_TGT_TP.DVC.toString(), strndSn,
						grpCoList);
			} else {
				List<String> arDvc = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.DVC.toString(), lang)) {
					arDvc.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arDvc, DisplayEnum.DSP_TGT_TP.DVC.toString(), strndSn,
						grpCoList);
			}

			// 전시대상 - 회원 속성
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.contains("GRPCO")) {
				arTgtMbrAtrbCd.remove("GRPCO");
				arTgtMbrAtrbCd.add(tgtGrpco);
			}
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtMbrAtrbCd,
						DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(), strndSn, grpCoList);
			} else {
				List<String> arTgtMbrAtrb = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB_DATA.toString(), lang)) {
					arTgtMbrAtrb.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtMbrAtrb, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						strndSn, grpCoList);
			}

			// 전시대상 - 회원 유형
			if (arTgtMbrTpCd != null && arTgtMbrTpCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtMbrTpCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						strndSn, grpCoList);
			} else {
				List<String> arTgtMbrTp = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(), lang)) {
					arTgtMbrTp.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtMbrTp, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						strndSn, grpCoList);
			}

			// 6. 이미지파일 운영 경로로 이동 pc, mobile
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);
			}

			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, real2FileName, fileUrl, getUploadImageUrlPath(),
						arRprstImg2FileNm);
			}

			return strndSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateStrndInfo(com
	 * .plgrim.ncp.biz.display.data.DspStrndBoDTO)
	 */
	@Override
	public void updateStrndInfo(DspStrndBoDTO dspStrndBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// dspStrnd
			DspStrnd dspStrnd = dspStrndBoDTO.getDspStrnd();
			dspStrnd.setUdterId(loginId);

			// 언어코드
			String[] arLangCdNm = dspStrndBoDTO.getArLangCdNm();
			// 기획전명
			String[] arStrndNm = dspStrndBoDTO.getArStrndNm();
			// 기획전설명
			String[] arStrndDscr = dspStrndBoDTO.getArStrndDscr();

			// 대표이미지
			String[] arRprstImgAltrtvCont = dspStrndBoDTO.getArRprstImgAltrtvCont();
			String[] arRprstImgFileNm = dspStrndBoDTO.getArRprstImgFileNm();
			String[] arRprstImgFileUrl = dspStrndBoDTO.getArRprstImgFileUrl();

			// 대표이미지 추가
			String[] arRprstImg2AltrtvCont = dspStrndBoDTO.getArRprstImg2AltrtvCont();
			String[] arRprstImg2FileNm = dspStrndBoDTO.getArRprstImg2FileNm();
			String[] arRprstImg2FileUrl = dspStrndBoDTO.getArRprstImg2FileUrl();

			// tempFileList
			List<String> tempFileList = dspStrndBoDTO.getTempFileList();

			// 전시대상
			List<String> arTgtLangCd = dspStrndBoDTO.getArTgtLangCd();
			List<String> arDvcCd = dspStrndBoDTO.getArDvcCd();
			List<String> arTgtMbrAtrbCd = dspStrndBoDTO.getArTgtMbrAtrbCd();
			List<String> arTgtMbrTpCd = dspStrndBoDTO.getArTgtMbrTpCd();
			String tgtGrpco = dspStrndBoDTO.getTgtGrpco();
			// 그룹사 그리드
			List<DspStrndDspTgtBoDTO> grpCoList = dspStrndBoDTO.getGrpCoList();

			// 0. 기획전 번호 조회
			long strndSn = dspStrnd.getStrndSn();

			// 1. 기본정보 수정-dsp_strnd
			// 1.1 전시기간
			String inBegDt = dspStrndBoDTO.getInBegDt();
			String inEndDt = dspStrndBoDTO.getInEndDt();
			SimpleDateFormat simplDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			dspStrnd.setDspBegDt(simplDateFormat.parse(inBegDt));
			dspStrnd.setDspEndDt(simplDateFormat.parse(inEndDt));
			// S-TEND명
			dspStrnd.setStrndNm(arStrndNm[0]);
			dspStrnd.setStrndDscr(arStrndDscr[0]);

			// 대표이미지
			String[] path = getRprstImgFilePath(strndSn, "STRND");
			String fileUrl = path[0];
			String uploadPath = path[1];

			String fileName = "";
			String fileExt = "";
			String[] realFileName = new String[arLangCdNm.length];

			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImgFileNm[0])) {
					fileExt = arRprstImgFileNm[0].substring(arRprstImgFileNm[0].lastIndexOf(".") + 1);
					// S-TEND_언어코드.ext
					// if(arRprstImgFileNm[0].indexOf("_") < 0) {
					// fileName = strndSn + "_"+ arLangCdNm[0] + "_" +
					// this.getCurrTimeForImg() + "." + fileExt;
					// }
					// else {
					// fileName = arRprstImgFileNm[0];
					// }

					if (StringService.isNotEmpty(arRprstImgFileUrl[0])) {
						fileName = arRprstImgFileNm[0];
						fileUrl = arRprstImgFileUrl[0];
					} else {
						fileName = strndSn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg() + "." + fileExt;
						fileUrl = path[0];
					}
					dspStrnd.setRprstImgFileNm(fileName);
					dspStrnd.setRprstImgFileUrl(fileUrl);

					realFileName[0] = fileName;
				} else {
					dspStrnd.setRprstImgFileNm("");
					dspStrnd.setRprstImgFileUrl("");

					realFileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImgAltrtvCont != null && StringService.isNotEmpty(arRprstImgAltrtvCont[0])) {
				dspStrnd.setRprstImgAltrtvCont(arRprstImgAltrtvCont[0]);
			}

			String[] real2FileName = new String[arLangCdNm.length];

			if (arRprstImg2FileNm != null && arRprstImg2FileNm.length > 0) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arRprstImg2FileNm[0])) {
					fileExt = arRprstImg2FileNm[0].substring(arRprstImg2FileNm[0].lastIndexOf(".") + 1);

					if (StringService.isNotEmpty(arRprstImg2FileUrl[0])) {
						fileName = arRprstImg2FileNm[0];
						fileUrl = arRprstImg2FileUrl[0];
					} else {
						fileName = "IMG2_" + strndSn + "_" + arLangCdNm[0] + "_" + this.getCurrTimeForImg() + "."
								+ fileExt;
						fileUrl = path[0];
					}
					dspStrnd.setRprstImg2FileNm(fileName);
					dspStrnd.setRprstImg2FileUrl(fileUrl);

					real2FileName[0] = fileName;
				} else {
					dspStrnd.setRprstImg2FileNm("");
					dspStrnd.setRprstImg2FileUrl("");

					real2FileName[0] = "";
				}
			}
			// 대체텍스트
			if (arRprstImg2AltrtvCont != null && StringService.isNotEmpty(arRprstImg2AltrtvCont[0])) {
				dspStrnd.setRprstImg2AltrtvCont(arRprstImg2AltrtvCont[0]);
			}

			displayStrendService.updateDspStrnd(dspStrnd);

			DspRevCpst dspRevCpst = dspStrndBoDTO.getDspRevCpst();
			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpst.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpst.setMobileTmplatSn(dspRevCpst.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpst.getPcMobileTmplatIndUseYn())) {
				dspRevCpst.setPcMobileTmplatIndUseYn(DisplayEnum.NO.toString());
			}

			dspRevCpst.setStrndSn(strndSn);
			displayRevCommandService.saveDspRevCpst(dspRevCpst);

			// 템플릿코너저장
			displayTemplateService.saveCnrTmplatInfoCnnc(strndSn + "", null, DisplayEnum.TMPLAT_TP.STRND.toString(),
					dspRevCpst.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(strndSn + "", null, DisplayEnum.TMPLAT_TP.STRND.toString(),
						dspRevCpst.getMobileTmplatSn(), baseRevSn);
			}

			// 2. S-TREND명 언어 등록 (merge)
			for (int i = 1; i < arStrndNm.length; i++) {
				DspStrndLang lang = new DspStrndLang();
				lang.setStrndSn(strndSn);
				lang.setLangCd(arLangCdNm[i]);
				lang.setStrndNm(arStrndNm[i]);

				displayStrendService.updateDspStrndLangNm(lang);
			}
			// ** S-TREND설명 언어 등록 (merge)
			for (int i = 1; i < arStrndDscr.length; i++) {
				DspStrndLang lang = new DspStrndLang();
				lang.setStrndSn(strndSn);
				lang.setLangCd(arLangCdNm[i]);
				lang.setStrndNm(arStrndNm[i]);
				lang.setStrndDscr(arStrndDscr[i]);

				displayStrendService.updateDspStrndLangNm(lang);
			}

			// 3. S-TREND이미지 언어별 (merge)
			if (arRprstImgFileNm != null && arRprstImgFileNm.length > 1) {
				for (int i = 1; i < arRprstImgFileNm.length; i++) {
					DspStrndLang lang = new DspStrndLang();
					lang.setStrndSn(strndSn);
					lang.setLangCd(arLangCdNm[i]);
					lang.setStrndNm(arStrndNm[i]);
					lang.setStrndDscr(arStrndDscr[i]);

					if (StringService.isNotEmpty(arRprstImgFileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImgFileNm[i].substring(arRprstImgFileNm[i].lastIndexOf(".") + 1);
						// S-TREND번호_언어코드.ext
						// if(arRprstImgFileNm[i].indexOf("_") < 0) {
						// fileName = strndSn + "_"+ arLangCdNm[i] + "_" +
						// this.getCurrTimeForImg() + "." + fileExt;
						// }
						// else {
						// fileName = arRprstImgFileNm[i];
						// }
						if (StringService.isNotEmpty(arRprstImgFileUrl[i])) {
							fileName = arRprstImgFileNm[i];
							fileUrl = arRprstImgFileUrl[i];
						} else {
							fileName = strndSn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "." + fileExt;
							fileUrl = path[0];
						}
						lang.setRprstImgFileNm(fileName);
						lang.setRprstImgFileUrl(fileUrl);

						realFileName[i] = fileName;
					} else {
						realFileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImgAltrtvCont[i])) {
						lang.setRprstImgAltrtvCont(arRprstImgAltrtvCont[i]);
					}
					lang.setUdterId(loginId);

					displayStrendService.updateDspStrndLangImg(lang);
				}
			}

			// 3. S-TREND이미지 언어별 (merge)
			if (arRprstImg2FileNm != null && arRprstImg2FileNm.length > 1) {
				for (int i = 1; i < arRprstImg2FileNm.length; i++) {
					DspStrndLang lang = new DspStrndLang();
					lang.setStrndSn(strndSn);
					lang.setLangCd(arLangCdNm[i]);

					if (StringService.isNotEmpty(arRprstImg2FileNm[i])) {
						// real 파일명, 경로생성
						fileExt = arRprstImg2FileNm[i].substring(arRprstImg2FileNm[i].lastIndexOf(".") + 1);

						if (StringService.isNotEmpty(arRprstImg2FileUrl[i])) {
							fileName = arRprstImg2FileNm[i];
							fileUrl = arRprstImg2FileUrl[i];
						} else {
							fileName = "IMG2_" + strndSn + "_" + arLangCdNm[i] + "_" + this.getCurrTimeForImg() + "."
									+ fileExt;
							fileUrl = path[0];
						}
						lang.setRprstImg2FileNm(fileName);
						lang.setRprstImg2FileUrl(fileUrl);

						real2FileName[i] = fileName;
					} else {
						real2FileName[i] = "";
					}

					if (StringService.isNotEmpty(arRprstImg2AltrtvCont[i])) {
						lang.setRprstImg2AltrtvCont(arRprstImg2AltrtvCont[i]);
					}
					lang.setUdterId(loginId);

					displayStrendService.updateDspStrndLangImg2(lang);
				}
			}

			for (int i = 1; i < arRprstImg2FileNm.length; i++) {
				DspStrndLang lang = new DspStrndLang();
				lang.setStrndSn(strndSn);
				lang.setLangCd(arLangCdNm[i]);

				if (StringService.isEmpty(arStrndNm[i]) && StringService.isEmpty(arRprstImgFileNm[i])
						&& StringService.isEmpty(arRprstImg2FileNm[i])) {
					displayStrendService.deleteDspStrndLang(lang);
				}
			}

			DspStrndDspTgt dspStrndDspTgt = new DspStrndDspTgt();
			dspStrndDspTgt.setStrndSn(strndSn);
			displayStrendService.deleteDspStrndDspTgtInfo(dspStrndDspTgt);

			String scAplMallId = dspStrndBoDTO.getScAplMallId();
			String lang = dspStrndBoDTO.getLang();
			// 전시대상 - 몰ID
			List<String> arTgtMall = new ArrayList<String>();
			if (StringService.isNotEmpty(scAplMallId)) {
				arTgtMall.add(scAplMallId);
			} else {
				arTgtMall.add(DisplayEnum.MALL.toString());
			}
			displayStrendService.insertDspStrndDspTgt(arTgtMall, DisplayEnum.DSP_TGT_TP.MALL_ID.toString(), strndSn,
					grpCoList);

			// 전시대상 - 언어
			if (arTgtLangCd != null && arTgtLangCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtLangCd, DisplayEnum.DSP_TGT_TP.LANG.toString(), strndSn,
						grpCoList);
			} else {
				List<String> arTgtLang = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.LANG.toString(), lang)) {
					arTgtLang.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtLang, DisplayEnum.DSP_TGT_TP.LANG.toString(), strndSn,
						grpCoList);
			}

			// 전시대상 - 디바이스
			if (arDvcCd != null && arDvcCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arDvcCd, DisplayEnum.DSP_TGT_TP.DVC.toString(), strndSn,
						grpCoList);
			} else {
				List<String> arDvc = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.DVC.toString(), lang)) {
					arDvc.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arDvc, DisplayEnum.DSP_TGT_TP.DVC.toString(), strndSn,
						grpCoList);
			}

			// 전시대상 - 회원 속성
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.contains("GRPCO")) {
				arTgtMbrAtrbCd.remove("GRPCO");
				arTgtMbrAtrbCd.add(tgtGrpco);
			}
			if (arTgtMbrAtrbCd != null && arTgtMbrAtrbCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtMbrAtrbCd,
						DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(), strndSn, grpCoList);
			} else {
				List<String> arTgtMbrAtrb = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB_DATA.toString(), lang)) {
					arTgtMbrAtrb.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtMbrAtrb, DisplayEnum.DSP_TGT_TP.TGT_MBR_ATRB.toString(),
						strndSn, grpCoList);
			}

			// 전시대상 - 회원 유형
			if (arTgtMbrTpCd != null && arTgtMbrTpCd.size() > 0) {
				displayStrendService.insertDspStrndDspTgt(arTgtMbrTpCd, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						strndSn, grpCoList);
			} else {
				List<String> arTgtMbrTp = new ArrayList<String>();

				for (Code cd : CodeUtil.getCodes(DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(), lang)) {
					arTgtMbrTp.add(cd.getCd());
				}
				displayStrendService.insertDspStrndDspTgt(arTgtMbrTp, DisplayEnum.DSP_TGT_TP.TGT_MBR_TP.toString(),
						strndSn, grpCoList);
			}

			// 6. 이미지파일 운영 경로로 이동
			int moveFileCnt = 0;
			if (tempFileList != null && tempFileList.size() > 0) {
				moveFileCnt += this.moveTempImage(uploadPath, realFileName, fileUrl, getUploadImageUrlPath(),
						arRprstImgFileNm);

			}
			// 추가이미지
			if (arRprstImg2FileNm != null && arRprstImg2FileNm.length > 0) {
				if (tempFileList != null && tempFileList.size() > 0) {
					moveFileCnt += this.moveTempImage(uploadPath, real2FileName, fileUrl, getUploadImageUrlPath(),
							arRprstImg2FileNm);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#addCtgryRelate(com.
	 * plgrim.ncp.biz.display.data.DspCtgryRelateBoDTO)
	 */
	@Override
	public int addCtgryRelate(DspCtgryRelateBoDTO dspCtgryRelateBoDTO) {
		try {
			int res = 0;
			String loginId = BOSecurityUtil.getLoginId();
			String scDspCtgryNo = dspCtgryRelateBoDTO.getScDspCtgryNo();
			List<String> scRelateDspCtgryNo = dspCtgryRelateBoDTO.getScRelateDspCtgryNo();

			for (String relateDspCtgryNo : scRelateDspCtgryNo) {

				DspCtgryRelate dspCtgryRelate = new DspCtgryRelate();
				dspCtgryRelate.setDspCtgryNo(scDspCtgryNo);
				dspCtgryRelate.setRelateDspCtgryNo(relateDspCtgryNo);
				dspCtgryRelate.setRegtrId(loginId);
				dspCtgryRelate.setUdterId(loginId);

				displayCategoryService.insertCtgryRelate(dspCtgryRelate);

				res++;
			}

			return res;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#deleteCtgryRelate(
	 * java.util.List)
	 */
	@Override
	public void deleteCtgryRelate(List<DspCtgryRelateBoDTO> list) {
		try {
			for (DspCtgryRelateBoDTO dspCtgryRelateBoDTO : list) {
				DspCtgryRelate dspCtgryRelate = dspCtgryRelateBoDTO.getDspCtgryRelate();

				displayCategoryService.deleteCtgryRelate(dspCtgryRelate);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#insertImageUpload(
	 * java.util.List)
	 */
	@Override
	public List<String> insertImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			dspImgUploadDTO.setLoginId(loginId);

			// 업로드 가능한 확장자인지 확인
			int failCnt = 0;
			for (int i = 0; i < dspImgUploadDTO.getUploadImage().size(); i++) {
				String fileName = dspImgUploadDTO.getUploadImage().get(i).getOriginalFilename();
				int indexOF = fileName.lastIndexOf(".");
				String extension = fileName.substring(indexOF);
				if (!extension.equals(".jpg") && !extension.equals(".png") && !extension.equals(".gif")) {
					failCnt++;
				}
			}
			if (failCnt > 0) {
				throw new NotSupportedUploadFileException(null);
			} else {
				return displayCornerContensService.insertImageUpload(session1, dspImgUploadDTO);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> updateImageUpload(DspImgUploadDTO dspImgUploadDTO) throws Exception {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			dspImgUploadDTO.setLoginId(loginId);
			// 업로드 가능한 확장자인지 확인
			int failCnt = 0;
			for (int i = 0; i < dspImgUploadDTO.getUploadImage().size(); i++) {
				String fileName = dspImgUploadDTO.getUploadImage().get(i).getOriginalFilename();
				int indexOF = fileName.lastIndexOf(".");
				String extension = fileName.substring(indexOF);
				if (!extension.equals(".jpg") && !extension.equals(".png") && !extension.equals(".gif")) {
					failCnt++;
				}
			}
			if (failCnt > 0) {
				throw new NotSupportedUploadFileException(null);
			} else {
				return displayCornerContensService.updateImageUpload(session1, dspImgUploadDTO);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#isExistFile(com.
	 * plgrim.ncp.biz.display.data.DspImgUploadDTO)
	 */
	@Override
	public Boolean isExistFile(DspImgUploadDTO dspImgUploadDTO) throws Exception {
		return displayCornerContensService.isExistFile(dspImgUploadDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateSortSeqCnrConttGod(java.util.List, java.lang.Long, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public List<DspCnrConttExt> updateSortSeqCnrConttGod(List<DspCnrContt> list, Long cnrSn, Long cnrSetSn,
			Long revSn) {
		try {
			return displayCornerContensService.updateSortSeqCnrConttGod(list, cnrSn, cnrSetSn, revSn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateAbTestModSortSeqCnrConttGod(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List, java.lang.Long, java.lang.Long)
	 */
	@Override
	public DspCnrConttExtDTO updateAbTestModSortSeqCnrConttGod(DspCnrConttScBoDTO dspCnrConttScBoDTO,
			List<DspCnrContt> list, Long cnrSn, Long cnrSetSn) {
		DspCnrConttExtDTO result = new DspCnrConttExtDTO();
		try {
			// 리비전 복사
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn());

			List<DspCnrConttExt> erList = displayCornerContensService.updateSortSeqCnrConttGod(list, cnrSn, cnrSetSn,
					abTestModRevSn);

			result.setErList(erList);
			result.setAbTestModRevSnPge(abTestModRevSn);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updatePrevSortSeqCnrConttGod(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List, java.lang.Long, java.lang.Long)
	 */
	@Override
	public DspCnrConttExtDTO updatePrevSortSeqCnrConttGod(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<DspCnrContt> list,
			Long cnrSn, Long cnrSetSn) {
		DspCnrConttExtDTO result = new DspCnrConttExtDTO();
		try {
			// 리비전 복사
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			List<DspCnrConttExt> erList = displayCornerContensService.updateSortSeqCnrConttGod(list, cnrSn, cnrSetSn,
					prvwRevSnPge);

			result.setErList(erList);
			result.setPrvwRevSnPge(prvwRevSnPge);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * insertPromtSprtrGodExcelUpload(java.util.List)
	 */
	@Override
	public List<DspPromtSprtr> insertPromtSprtrGodExcelUpload(List<DspPromtSprtr> list) {
		try {
			List<DspPromtSprtr> errList = null;
			DspPromtSprtr dspPromtSprtr1 = new DspPromtSprtr();
			if (!(list.isEmpty())) {
				String loginId = BOSecurityUtil.getLoginId();
				errList = new ArrayList<DspPromtSprtr>();

				for (DspPromtSprtr dspPromtSprtr : list) {
					Long promtSn = dspPromtSprtr.getPromtSn();
					if (promtSn != null && promtSn > 0) {
						int promtSnCnt = displayPlanService.selectDspPromtSnCnt(promtSn);

						if (promtSnCnt > 0) {
							Integer sprtrTurn = displayPlanService.getSprtrTurn(promtSn);

							dspPromtSprtr.setSprtrTurn(sprtrTurn);
							// dspPromtSprtr.setSortSeq(dspPromtSprtr.getSprtrSortSeq());
							dspPromtSprtr.setRegtrId(loginId);
							dspPromtSprtr.setUdterId(loginId);
							dspPromtSprtr.setDspYn("Y");
							displayPlanService.insertDspPromtSprtr(dspPromtSprtr);
							/*
							 * //String[] godNos =
							 * StringService.split(dspPromtSprtr.getGodNos(),
							 * "/" );
							 * displayPlanService.insertPromtGodList(promtSn,
							 * sprtrTurn, godNos);
							 */
						} else {
							dspPromtSprtr1 = new DspPromtSprtr();
							dspPromtSprtr1.setPromtSn(promtSn);
							// dspPromtSprtr1.setValidText("등록된 기획번호가 아닙니다.");
							errList.add(dspPromtSprtr1);
						}

					} else {
						dspPromtSprtr1 = new DspPromtSprtr();
						dspPromtSprtr1.setPromtSn(promtSn);
						// dspPromtSprtr1.setValidText("기획번호가 없습니다.");
						errList.add(dspPromtSprtr1);
					}
				}
			}

			return errList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	// 2016/07 (UX/UI)
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateBrndCategoryInfo(com.plgrim.ncp.biz.display.data.DspCtgryBoDTO,
	 * java.util.List)
	 */
	@Override
	public int updateBrndCategoryInfo(DspCtgryBoDTO dspCtgryBoDTO, List<String> rprstImgFile) {
		try {
			int result = 0;
			DspCtgry dspCtgry = dspCtgryBoDTO.getDspCtgry();
			DspBrndCtgry dspBrndCtgry = dspCtgryBoDTO.getDspBrndCtgry();

			// 카테고리명 디폴트(KOR)
			String[] arCtgryNm = dspCtgryBoDTO.getArDspCtgryNm();

			if (StringService.isNotEmpty(arCtgryNm[0])) {
				dspCtgry.setDspCtgryNm(arCtgryNm[0]);
				dspBrndCtgry.setDspCtgryNm(arCtgryNm[0]);
			}

			dspBrndCtgry.setDspCtgryNo(dspCtgryBoDTO.getDspCtgryNo());
			dspBrndCtgry.setDspBrndId(dspCtgryBoDTO.getScDspBrndId());

			// 08.19 추가 전시여부 수정시
			// 1) 상위가 'Y'일 경우만 수정가능
			// 2) 'N'으로 수정 시 하위 전체를 'N'으로 수정
			dspCtgry.setUpperDspCtgryNo(dspCtgryBoDTO.getUpperDspCtgryNo());
			displayCategoryService.updateDspYnBrndCtgry(dspCtgryBoDTO);

			displayCategoryService.updateDspBrndCtgryInfo(dspCtgryBoDTO);

			DspRevCpst dspRevCpstBrnd = dspCtgryBoDTO.getDspRevCpstBrnd();
			if (dspRevCpstBrnd.getRevSn() == null || dspRevCpstBrnd.getRevSn() == 0)
				dspRevCpstBrnd.setRevSn(baseRevSn);
			// 템플릿이 PC + Mobile일 경우 mobileTmplatSn에도 같은값으로 입력
			String pcMobileYn = dspRevCpstBrnd.getPcMobileTmplatIndUseYn();
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.NO.toString())) {
				dspRevCpstBrnd.setMobileTmplatSn(dspRevCpstBrnd.getPcTmplatSn());
			}

			// PC_MOBILE_TMPLAT_IND_USE_YN
			if (StringService.isEmpty(dspRevCpstBrnd.getPcMobileTmplatIndUseYn())) {
				dspRevCpstBrnd.setPcMobileTmplatIndUseYn("N");
			}

			dspRevCpstBrnd.setDspBrndCtgryNo(dspCtgryBoDTO.getDspCtgryNo());
			dspRevCpstBrnd.setDspBrndId(dspCtgryBoDTO.getScDspBrndId());
			dspRevCpstBrnd.setUseYn(DisplayEnum.YES.toString());
			displayRevCommandService.saveDspRevCpst(dspRevCpstBrnd);

			// 코너템플릿 연결테이블 저장
			displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgryBoDTO.getDspCtgryNo(), dspCtgryBoDTO.getScDspBrndId(),
					DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(), dspRevCpstBrnd.getPcTmplatSn(), baseRevSn);
			if (StringService.isNotEmpty(pcMobileYn) && pcMobileYn.equals(DisplayEnum.YES.toString())) {
				displayTemplateService.saveCnrTmplatInfoCnnc(dspCtgryBoDTO.getDspCtgryNo(),
						dspCtgryBoDTO.getScDspBrndId(), DisplayEnum.TMPLAT_TP.DSP_CTGRY.toString(),
						dspRevCpstBrnd.getMobileTmplatSn(), baseRevSn);
			}

			return result;
		} catch (NotUpdatedDspYnException ve) {
			throw new NotUpdatedDspYnException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateBrndCtgryList
	 * (java.util.List)
	 */
	@Override
	public void updateBrndCtgryList(List<DspCtgryBoDTO> list) {
		try {
			displayCategoryService.updateBrndCategoryList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateBrndCtgrySortSeqGod(java.util.List, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public List<DspCtgryCnncGodExt> updateBrndCtgrySortSeqGod(List<DspCtgryCnncGod> list, String dspCtgryNo,
			String scDspBrndId) {
		try {
			List<DspCtgryCnncGodExt> errList = displayCategoryService.updateBrndCtgrySortSeqGod(list, dspCtgryNo,
					scDspBrndId);

			return errList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveCnrCnncList(
	 * java.util.List, java.util.List)
	 */
	@Override
	public int saveCnrCnncList(List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList) {
		try {
			int result = 0;
			result = displayTemplateService.insertTemplateCorner(insList);
			result += displayTemplateService.updateTemplateCorner(updList);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#saveCnrCnncListRev(
	 * java.util.List, java.util.List)
	 */
	@Override
	public int saveCnrCnncListRev(List<DspCnrTmplatInfoBoDTO> insList, List<DspCnrTmplatInfoBoDTO> updList) {
		try {
			int result = 0;
			String scPrvwSn = null;
			if (insList != null && insList.size() > 0) {
				scPrvwSn = insList.get(0).getScPrvwSn();
			}

			if (scPrvwSn == null && updList != null && updList.size() > 0) {
				scPrvwSn = updList.get(0).getScPrvwSn();
			}

			// 미리보기 리비전 번호가 있을 경우 미리보기 리비전에도 저장한다. - (코너, 세트)
			if (StringService.isNotEmpty(scPrvwSn)) {
				Iterator<DspCnrTmplatInfoBoDTO> it = insList.iterator();
				while (it.hasNext()) {
					DspCnrTmplatInfoBoDTO dctiDTO = it.next();
					scPrvwSn = dctiDTO.getScPrvwSn();
					if (StringService.isNotEmpty(scPrvwSn)) {
						dctiDTO.getDspCnrTmplatInfoCnnc().setRevSn(Long.parseLong(dctiDTO.getScPrvwSn()));
					}
				}

				Iterator<DspCnrTmplatInfoBoDTO> itu = updList.iterator();
				while (itu.hasNext()) {
					DspCnrTmplatInfoBoDTO dctiDTO = itu.next();
					scPrvwSn = dctiDTO.getScPrvwSn();
					if (StringService.isNotEmpty(scPrvwSn)) {
						dctiDTO.getDspCnrTmplatInfoCnnc().setRevSn(Long.parseLong(dctiDTO.getScPrvwSn()));
						DspCnrTmplatInfoCnncResult resOne = displayTemplateService
								.selectCnrTmplatInfoCnncOne(dctiDTO.getDspCnrTmplatInfoCnnc());
						if (resOne != null) {
							dctiDTO.getDspCnrTmplatInfoCnnc()
									.setDspCnrTmplatTurn(resOne.getDspCnrTmplatInfoCnnc().getDspCnrTmplatTurn());
						} else {
							throw new Exception();
						}
					}
				}

				if (StringService.isNotEmpty(scPrvwSn)) {
					result = displayTemplateService.insertTemplateCorner(insList);
					result += displayTemplateService.updateTemplateCorner(updList);
				}
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#moveCtgryCnncGod(
	 * com.plgrim.ncp.biz.display.data.DspCtgryCnncGodBoDTO)
	 */
	@Override
	public int moveCtgryCnncGod(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			// 로그인 아이디
			DspCtgryCnncGod dspCtgryCnncGod = new DspCtgryCnncGod();
			dspCtgryCnncGod.setRegtrId(loginId);
			dspCtgryCnncGod.setUdterId(loginId);
			dspCtgryCnncGodBoDTO.setDspCtgryCnncGod(dspCtgryCnncGod);

			return displayCategoryService.moveCtgryCnncGod(dspCtgryCnncGodBoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * selectOutletCtgForCopy(com.plgrim.ncp.biz.display.data.
	 * DspCtgryCnncGodBoDTO)
	 */
	@Override
	public Integer selectOutletCtgForCopy(DspCtgryCnncGodBoDTO dspCtgryCnncGodBoDTO) throws Exception {
		return displayCategoryService.selectOutletCtgForCopy(dspCtgryCnncGodBoDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateCtgSortSeq(
	 * java.util.List)
	 */
	@Override
	public void updateCtgSortSeq(List<DspCtgryBoDTO> list) {
		try {
			displayCategoryService.updateCtgSortSeq(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#updateCtgGodSortSeq
	 * (java.util.List)
	 */
	@Override
	public void updateCtgGodSortSeq(List<DspCtgryCnncGodBoDTO> list) {
		try {
			displayCategoryService.updateCtgGodSortSeq(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#savePromtImg(com.
	 * plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public void savePromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			int result = 0;
			Integer conttTurn = null;
			if (dspCnrConttScBoDTO != null) {

				String loginId = BOSecurityUtil.getLoginId();

				// 기획전 이미지 조회 1단/2단
				DspPromtImg scDspPromtImg = new DspPromtImg();
				if (StringService.isNotEmpty(dspCnrConttScBoDTO.getImgPromtSn())) {
					scDspPromtImg.setPromtSn(Long.parseLong(dspCnrConttScBoDTO.getImgPromtSn()));
				}
				if (StringService.isNotEmpty(dspCnrConttScBoDTO.getSelectImg())) {
					if ("1".equals(dspCnrConttScBoDTO.getSelectImg())) {
						scDspPromtImg.setPromtImgSectCd(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString());
					} else if ("2".equals(dspCnrConttScBoDTO.getSelectImg())) {
						scDspPromtImg.setPromtImgSectCd(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString());
					}
				} else {
					scDspPromtImg.setPromtImgSectCd(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString());
				}
				DspPromtImg promtImg = displayPlanService.selectDspPromtImg(scDspPromtImg);
				List<DspPromtImgLang> promtImgLangList = displayPlanService.selectDspPromtImgLangList(scDspPromtImg);

				// dspCnrContt insert
				DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
				dspCnrContt.setRegtrId(loginId);
				dspCnrContt.setUdterId(loginId);

				// 컨텐트 순번
				conttTurn = displayCornerContensService.getConttTurn(dspCnrContt);
				dspCnrContt.setConttTurn(conttTurn);

				// 이미지배너
				String[] path = getCnrConttFilePath(dspCnrConttScBoDTO);
				String fileUrl = path[0];
				String destPath = path[1];
				String fileExt = "";
				String fileName = "";

				List<String> arTgtLangCd = new ArrayList<String>();

				if (StringService.isNotEmpty(promtImg.getPcImgFileNm())) {
					fileExt = promtImg.getPcImgFileNm().substring(promtImg.getPcImgFileNm().lastIndexOf(".") + 1);
					fileName = dspCnrContt.getCnrSn().toString() + "_" + dspCnrContt.getCnrSetSn().toString() + "_"
							+ conttTurn.toString() + "_" + "KOR" + "_" + this.getCurrTimeForImg() + "." + fileExt;

					dspCnrContt.setImgFileNm(fileName);
					dspCnrContt.setImgFileUrl(fileUrl);
					dspCnrContt.setImgAltrtvCont(promtImg.getPcImgAltrtvCont());

					String txt1 = promtImg.getPcImgExpsrTxt1Cont();
					String txt2 = promtImg.getPcImgExpsrTxt2Cont();
					String txt = "";
					if (StringService.isNotEmpty(txt1)) {
						txt += txt1;
						if (StringService.isNotEmpty(txt2)) {
							txt += "<br/>" + txt2;
						}
					} else {
						if (StringService.isNotEmpty(txt2)) {
							txt += txt2;
						}
					}
					dspCnrContt.setImgDscr(txt);

					arTgtLangCd.add("KOR");
				}

				dspCnrContt.setImgNm(
						"[기획전" + dspCnrConttScBoDTO.getSelectImg() + "단이미지]_" + dspCnrConttScBoDTO.getImgPromtSn());
				dspCnrContt.setConttCnncUrl("/special/" + dspCnrConttScBoDTO.getImgPromtSn() + "/view");
				displayCornerContensService.insertCornerContent(dspCnrContt);

				if (StringService.isNotEmpty(promtImg.getPcImgFileNm())) {
					// 기획전이미지경로
					String srcPath = uploadPath + promtImg.getPcImgFileUrl();
					copyPromtImgContt(destPath, fileName, srcPath, promtImg.getPcImgFileNm());
				}

				// dspCnrConttlang insert
				for (DspPromtImgLang lang : promtImgLangList) {

					if (StringService.isNotEmpty(lang.getPcImgFileNm())) {
						DspCnrConttLang dspCnrConttLang = new DspCnrConttLang();
						dspCnrConttLang.setRegtrId(loginId);
						dspCnrConttLang.setUdterId(loginId);

						dspCnrConttLang.setRevSn(dspCnrContt.getRevSn());
						dspCnrConttLang.setCnrSn(dspCnrContt.getCnrSn());
						dspCnrConttLang.setCnrSetSn(dspCnrContt.getCnrSetSn());
						dspCnrConttLang.setConttTurn(conttTurn);
						dspCnrConttLang.setLangCd(lang.getLangCd());

						// 이미지배너
						fileExt = lang.getPcImgFileNm().substring(lang.getPcImgFileNm().lastIndexOf(".") + 1);
						fileName = dspCnrContt.getCnrSn().toString() + "_" + dspCnrContt.getCnrSetSn().toString() + "_"
								+ conttTurn.toString() + "_" + lang.getLangCd() + "_" + this.getCurrTimeForImg() + "."
								+ fileExt;

						dspCnrConttLang.setImgFileNm(fileName);
						dspCnrConttLang.setImgFileUrl(fileUrl);
						dspCnrConttLang.setImgAltrtvCont(lang.getPcImgAltrtvCont());
						String txt1Lang = lang.getPcImgExpsrTxt1Cont();
						String txt2Lang = lang.getPcImgExpsrTxt2Cont();
						String txtLang = "";
						if (StringService.isNotEmpty(txt1Lang)) {
							txtLang += txt1Lang;
							if (StringService.isNotEmpty(txt2Lang)) {
								txtLang += "<br/>" + txt2Lang;
							}
						} else {
							if (StringService.isNotEmpty(txt2Lang)) {
								txtLang += txt2Lang;
							}
						}
						dspCnrConttLang.setImgDscr(txtLang);

						arTgtLangCd.add(lang.getLangCd());

						displayCornerContensService.insertCornerContentLang(dspCnrConttLang);
						String srcPath = uploadPath + lang.getPcImgFileUrl();
						copyPromtImgContt(destPath, fileName, srcPath, lang.getPcImgFileNm());
					}
				}

				// 전시대상 저장
				// 전시대상 설정 디폴트 insert
				DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO = new DspCnrConttDspTgtBoDTO();
				dspCnrConttDspTgtBoDTO.setScRevSn(dspCnrContt.getRevSn());
				dspCnrConttDspTgtBoDTO.setScCnrSn(dspCnrContt.getCnrSn());
				dspCnrConttDspTgtBoDTO.setScCnrSetSn(dspCnrContt.getCnrSetSn());
				List<Integer> conttTurnList = new ArrayList<Integer>();
				conttTurnList.add(conttTurn);
				dspCnrConttDspTgtBoDTO.setScConttTurn(conttTurnList);
				dspCnrConttDspTgtBoDTO.setLang(dspCnrConttScBoDTO.getLang());
				dspCnrConttDspTgtBoDTO.setMallId(dspCnrConttScBoDTO.getMallId());

				// 전시대상설정 - 다국어(디폴트 한국어)
				dspCnrConttDspTgtBoDTO.setArLangCd(arTgtLangCd);

				displayCornerContensService.saveContentDspTgt(dspCnrConttDspTgtBoDTO);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * copyPreviewRevisionSn(com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long copyPreviewRevisionSn(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			dspCnrConttScBoDTO.setScAdminId(loginId);
			dspCnrConttScBoDTO.setScRevSectCd("PRVW");

			Long prvwRevSnPge = null;

			// 최초 임시저장일 경우 페이지미리보기 리비전 삭제 후 베이스 리비전 복사
			if (dspCnrConttScBoDTO.getScRevSn() != null && dspCnrConttScBoDTO.getScRevSn().equals(baseRevSn)) {
				// 관리자의 미리보기 리비전 조회
				Long prvwRevSnAdm = displayRevSelectService.selectRevByAdmin(dspCnrConttScBoDTO);
				// 1) dsp_rev에 로그인한 관리자의 미리보기 없을 경우 insert => rev_sn
				if (prvwRevSnAdm == null || prvwRevSnAdm == 0) {
					// BO 접속 관리자의 미리보기 리비전 등록
					DspRevBoDTO dspRevDTO = new DspRevBoDTO();
					DspRev dspRev = new DspRev();
					dspRev.setRevNm("미리보기 리비전");
					dspRev.setAdminId(loginId);
					dspRev.setRegtrId(loginId);
					dspRev.setUdterId(loginId);
					dspRev.setRevSectCd(dspCnrConttScBoDTO.getScRevSectCd());
					dspRev.setUseYn("Y");
					dspRevDTO.setDspRev(dspRev);

					prvwRevSnPge = displayRevCommandService.insertRevInfo(dspRevDTO);
				} else {
					prvwRevSnPge = prvwRevSnAdm;

					displayRevCommandService.deleteRevPageContt(dspCnrConttScBoDTO, baseRevSn, prvwRevSnPge);
				}

				// 2) 해당 페이지의 base rev.를 모두 미리보기 rev.로 복사
				displayRevCommandService.copyRevCnrConttInfo(dspCnrConttScBoDTO, baseRevSn, prvwRevSnPge);
			} else {
				prvwRevSnPge = dspCnrConttScBoDTO.getScRevSn();
			}

			log.info(
					CommonResponseCode.DP_00019_CONTT_임시저장_미리보기_컨텐츠_복사_성공.toMessage()
							+ ", scDspBrndId:{}, scDspCtgryNo:{}, scPromtSn:{}, scStrndSn:{}, scEvtNo:{}, prvwRevSnPge:{}",
					dspCnrConttScBoDTO.getScDspBrndId(), dspCnrConttScBoDTO.getScDspCtgryNo(),
					dspCnrConttScBoDTO.getScPromtSn(), dspCnrConttScBoDTO.getScStrndSn(),
					dspCnrConttScBoDTO.getScEvtNo(), prvwRevSnPge);
			return prvwRevSnPge;
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40014_CONTT_임시저장_미리보기_컨텐츠_복사_실패.toMessage() + "dspCnrConttScBoDTO: {}",
					dspCnrConttScBoDTO);
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * savePreviewCornerContent(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public Long savePreviewCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile) {
		try {
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
			dspCnrContt.setRevSn(prvwRevSnPge); // 미리보기 리비전 Set
			if (dspCnrContt.getConttTurn() == null || dspCnrContt.getConttTurn() == 0) {
				// 1.1) 신규 등록 컨텐츠일 경우 insert
				int result = this.insertCornerContent(dspCnrConttScBoDTO, rprstImgFile);
			} else {
				// 1.2) 수정 컨텐츠일 경우 update
				this.updateCornerContent(dspCnrConttScBoDTO, rprstImgFile);
			}

			return prvwRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updatePrevCornerContentsList(java.util.List)
	 */
	@Override
	public Long updatePrevCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			// 미리보기 리비전 Set
			DspCnrConttScBoDTO dto = list.get(0);
			dto.setScPrvwSn(prvwRevSnPge.toString());

			displayCornerContensService.updateCornerContentList(list);

			return prvwRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deletePrevCornerContentsList(java.util.List,
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long deletePrevCornerContentsList(List<DspCnrConttScBoDTO> list, DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			// 미리보기 리비전 Set
			DspCnrConttScBoDTO dto = list.get(0);
			dto.setScPrvwSn(prvwRevSnPge.toString());

			displayCornerContensService.deleteCornerContent(list);

			return prvwRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * savePrevContentDspTgt(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttDspTgtBoDTO)
	 */
	@Override
	public Long savePrevContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO) {
		try {
			DspCnrConttScBoDTO dspCnrConttScBoDTO = new DspCnrConttScBoDTO();
			dspCnrConttScBoDTO.setScRevSn(dspCnrConttDspTgtBoDTO.getScRevSn());
			dspCnrConttScBoDTO.setScDspCtgryNo(dspCnrConttDspTgtBoDTO.getScDspCtgryNo());
			dspCnrConttScBoDTO.setScDspBrndId(dspCnrConttDspTgtBoDTO.getScDspBrndId());
			dspCnrConttScBoDTO.setScPromtSn(dspCnrConttDspTgtBoDTO.getScPromtSn());
			dspCnrConttScBoDTO.setScStrndSn(dspCnrConttDspTgtBoDTO.getScStrndSn());
			dspCnrConttScBoDTO.setScEvtNo(dspCnrConttDspTgtBoDTO.getScEvtNo());
			dspCnrConttScBoDTO.setScTmplatSn(dspCnrConttDspTgtBoDTO.getScTmplatSn());
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			// 미리보기 리비전 Set
			dspCnrConttDspTgtBoDTO.setScRevSn(prvwRevSnPge);

			String mallId = dspCnrConttDspTgtBoDTO.getMallId();
			String scConttTpCd = dspCnrConttDspTgtBoDTO.getScConttTpCd();
			if ((StringService.isNotEmpty(mallId) && mallId.equals("DXM")) && (StringService.isNotEmpty(scConttTpCd)
					&& (scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
							|| scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString())))) {
				displayCornerContensService.validContentLangDspTgt(dspCnrConttDspTgtBoDTO);
			}

			displayCornerContensService.saveContentDspTgt(dspCnrConttDspTgtBoDTO);

			return prvwRevSnPge;
		} catch (DspConttValidException ve) {
			throw new DspConttValidException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#savePrevPromtImg(
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long savePrevPromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);

			DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();
			dspCnrContt.setRevSn(prvwRevSnPge); // 미리보기 리비전 Set

			this.savePromtImg(dspCnrConttScBoDTO);

			return prvwRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * insertPreviewCornerContentList(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public DspConttRevResultDTO insertPreviewCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO,
			List<DspCnrConttExt> list) {
		try {
			DspConttRevResultDTO resultDTO = new DspConttRevResultDTO();

			Long prvwRevSnPge = this.copyPreviewRevisionSn(dspCnrConttScBoDTO);
			resultDTO.setRevSnPge(prvwRevSnPge);

			// 미리보기 리비전 Set
			if (list != null && list.size() > 0) {
				for (DspCnrContt d : list) {
					d.setRevSn(prvwRevSnPge);
				}
			}

			String[] res = displayCornerContensService.insertCornerContentList(dspCnrConttScBoDTO, list);
			resultDTO.setResult(res);

			return resultDTO;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#copyAbTestModRevSn(
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO, java.lang.Long)
	 */
	@Override
	public Long copyAbTestModRevSn(DspCnrConttScBoDTO dspCnrConttScBoDTO, Long oldRevSn) {
		try {
			String loginId = BOSecurityUtil.getLoginId();

			Long newContRevSn = null;
			dspCnrConttScBoDTO.setScAdminId(loginId);
			String scAbTestSn = dspCnrConttScBoDTO.getScAbTestSn();
			String scAbTestSetTurn = dspCnrConttScBoDTO.getScAbTestSetTurn();
			String scAbTestModTurn = dspCnrConttScBoDTO.getScAbTestModTurn();

			// select dsp_ab_test_set
			Integer maxModTurn = 0;
			Integer scModTurn = 0;
			if (StringService.isNotEmpty(scAbTestModTurn)) {
				scModTurn = Integer.parseInt(scAbTestModTurn);
				maxModTurn = scModTurn + 1;
			}

			Date curDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date endDt = sdf.parse(dspCnrConttScBoDTO.getAbTestEndDt());

			DspAbTestSetMod dspAbTestSetMod = new DspAbTestSetMod();
			dspAbTestSetMod.setAbTestSn(Long.parseLong(scAbTestSn));
			dspAbTestSetMod.setSetTurn(Integer.parseInt(scAbTestSetTurn));
			dspAbTestSetMod.setRegtrId(loginId);
			dspAbTestSetMod.setUdterId(loginId);

			// 이전 mod의 종료일자를 현재로 update
			dspAbTestSetMod.setModTurn(scModTurn);
			dspAbTestSetMod.setEndDt(curDate);
			displayRevCommandService.updateDspAbTestSetModInfo(dspAbTestSetMod);

			// 신규 mod의 시작일자를 현재로, 종료일자를 abtest종료일자로 한다.
			dspAbTestSetMod.setModTurn(maxModTurn);
			dspAbTestSetMod.setModResnCont(dspCnrConttScBoDTO.getScModResnCont());
			dspAbTestSetMod.setBegDt(curDate);
			dspAbTestSetMod.setEndDt(endDt);
			displayRevCommandService.insertDspAbTestSetModInfo(dspAbTestSetMod);

			// select dsp_ab_test_rev
			dspCnrConttScBoDTO.setScRevSectCd(DisplayEnum.BO_CONTT_VIEW_TYPE.AB_TEST.toString());
			List<DspAbTestRevResult> curRevList = displayRevSelectService
					.selectDspAbTestRevListByMod(dspCnrConttScBoDTO);
			for (DspAbTestRevResult dspAbTestRevResult : curRevList) {
				// insert dsp_rev
				DspRev dspRev = dspAbTestRevResult.getDspRev();
				DspRevBoDTO dspRevDTO = new DspRevBoDTO();
				dspRev.setUseYn("N");
				dspRev.setRegtrId(loginId);
				dspRev.setUdterId(loginId);
				dspRevDTO.setDspRev(dspRev);
				Long newRevSn = displayRevCommandService.insertRevInfo(dspRevDTO);

				DspAbTestRev dspAbTestRev = dspAbTestRevResult.getDspAbTestRev();

				DspRevCpst dspRevCpst = dspAbTestRevResult.getDspRevCpst();
				dspRevCpst.setRevSn(newRevSn);
				dspRevCpst.setUseYn("N");
				dspRevCpst.setRegtrId(loginId);
				dspRevCpst.setUdterId(loginId);
				displayRevCommandService.insertDspRevCpstInfo(dspRevCpst);

				// contents 복사
				String scDvcSectCd = dspAbTestRev.getDvcSectCd();
				if (StringService.isNotEmpty(scDvcSectCd) && "MOBILE".equals(scDvcSectCd)) {
					dspCnrConttScBoDTO.setScTmplatSn(String.valueOf(dspRevCpst.getMobileTmplatSn()));
				} else {
					dspCnrConttScBoDTO.setScTmplatSn(String.valueOf(dspRevCpst.getPcTmplatSn()));
				}
				dspCnrConttScBoDTO.setRevCpstCopyYn("Y");
				displayRevCommandService.copyRevCnrConttInfo(dspCnrConttScBoDTO, dspAbTestRev.getRevSn(), newRevSn);

				// 수정중인 컨텐츠의 신규리비전을 리턴하기 위함.
				if (oldRevSn.equals(dspAbTestRev.getRevSn())) {
					newContRevSn = newRevSn;
				}

				// insert dsp_ab_test_rev
				dspAbTestRev.setRevSn(newRevSn);
				dspAbTestRev.setModTurn(maxModTurn);
				dspAbTestRev.setRegtrId(loginId);
				dspAbTestRev.setUdterId(loginId);
				displayRevCommandService.insertAbTestRevInfo(dspAbTestRev);

			}

			log.info(
					CommonResponseCode.DP_00020_CONTT_ABTEST_미리보기_컨텐츠_복사_성공.toMessage()
							+ ", scDspBrndId:{}, scDspCtgryNo:{}, scPromtSn:{}, scStrndSn:{}, scEvtNo:{}, newContRevSn:{}",
					dspCnrConttScBoDTO.getScDspBrndId(), dspCnrConttScBoDTO.getScDspCtgryNo(),
					dspCnrConttScBoDTO.getScPromtSn(), dspCnrConttScBoDTO.getScStrndSn(),
					dspCnrConttScBoDTO.getScEvtNo(), newContRevSn);

			// list 의 구 revSn을 newRevSn으로 변경하기 위함.
			return newContRevSn;
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40015_CONTT_ABTEST_미리보기_컨텐츠_복사_실패.toMessage() + ", dspCnrConttScBoDTO:{}",
					dspCnrConttScBoDTO);
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveAbTestModCornerContent(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public Long saveAbTestModCornerContent(DspCnrConttScBoDTO dspCnrConttScBoDTO, List<String> rprstImgFile) {
		try {
			DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();

			// dspCnrConttScBoDTO.setScModResnCont(dspCnrConttScBoDTO.getScModResnCont());
			// dspCnrConttScBoDTO.setAbTestEndDt(dspCnrConttScBoDTO.getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, dspCnrContt.getRevSn());

			dspCnrContt.setRevSn(abTestModRevSn);
			if (dspCnrContt.getConttTurn() == null || dspCnrContt.getConttTurn() == 0) {
				// 1.1) 신규 등록 컨텐츠일 경우 insert
				int result = this.insertCornerContent(dspCnrConttScBoDTO, rprstImgFile);
			} else {
				// 1.2) 수정 컨텐츠일 경우 update
				this.updateCornerContent(dspCnrConttScBoDTO, rprstImgFile);
			}

			return abTestModRevSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * updateAbTestModCornerContentsList(java.util.List,
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long updateAbTestModCornerContentsList(List<DspCnrConttScBoDTO> list,
			DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			dspCnrConttScBoDTO.setScModResnCont(list.get(0).getScModResnCont());
			dspCnrConttScBoDTO.setAbTestEndDt(list.get(0).getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, list.get(0).getDspCnrContt().getRevSn());

			if (list != null && list.size() > 0) {
				for (DspCnrConttScBoDTO dto : list) {
					DspCnrContt dspCnrContt = dto.getDspCnrContt();
					dspCnrContt.setRevSn(abTestModRevSn);
				}
			}

			displayCornerContensService.updateCornerContentList(list);

			return abTestModRevSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * deleteAbTestModCornerContentsList(java.util.List,
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long deleteAbTestModCornerContentsList(List<DspCnrConttScBoDTO> list,
			DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			dspCnrConttScBoDTO.setScModResnCont(list.get(0).getScModResnCont());
			dspCnrConttScBoDTO.setAbTestEndDt(list.get(0).getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, list.get(0).getDspCnrContt().getRevSn());

			if (list != null && list.size() > 0) {
				for (DspCnrConttScBoDTO dto : list) {
					DspCnrContt dspCnrContt = dto.getDspCnrContt();
					dspCnrContt.setRevSn(abTestModRevSn);
				}
			}

			displayCornerContensService.deleteCornerContent(list);

			return abTestModRevSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveAbTestModContentDspTgt(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttDspTgtBoDTO)
	 */
	@Override
	public Long saveAbTestModContentDspTgt(DspCnrConttDspTgtBoDTO dspCnrConttDspTgtBoDTO) {
		try {
			DspCnrConttScBoDTO dspCnrConttScBoDTO = new DspCnrConttScBoDTO();
			dspCnrConttScBoDTO.setScRevSn(dspCnrConttDspTgtBoDTO.getScRevSn());
			dspCnrConttScBoDTO.setScDspCtgryNo(dspCnrConttDspTgtBoDTO.getScDspCtgryNo());
			dspCnrConttScBoDTO.setScDspBrndId(dspCnrConttDspTgtBoDTO.getScDspBrndId());
			dspCnrConttScBoDTO.setScPromtSn(dspCnrConttDspTgtBoDTO.getScPromtSn());
			dspCnrConttScBoDTO.setScStrndSn(dspCnrConttDspTgtBoDTO.getScStrndSn());
			dspCnrConttScBoDTO.setScEvtNo(dspCnrConttDspTgtBoDTO.getScEvtNo());

			dspCnrConttScBoDTO.setScAbTestSn(dspCnrConttDspTgtBoDTO.getScAbTestSn());
			dspCnrConttScBoDTO.setScAbTestSetTurn(dspCnrConttDspTgtBoDTO.getScAbTestSetTurn());
			dspCnrConttScBoDTO.setScAbTestModTurn(dspCnrConttDspTgtBoDTO.getScAbTestModTurn());
			dspCnrConttScBoDTO.setScModResnCont(dspCnrConttDspTgtBoDTO.getScModResnCont());
			dspCnrConttScBoDTO.setAbTestEndDt(dspCnrConttDspTgtBoDTO.getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, dspCnrConttDspTgtBoDTO.getScRevSn());

			// 리비전 Set
			dspCnrConttDspTgtBoDTO.setScRevSn(abTestModRevSn);

			String mallId = dspCnrConttDspTgtBoDTO.getMallId();
			String scConttTpCd = dspCnrConttDspTgtBoDTO.getScConttTpCd();
			if ((StringService.isNotEmpty(mallId) && mallId.equals("DXM")) && (StringService.isNotEmpty(scConttTpCd)
					&& (scConttTpCd.equals(DisplayEnum.CONTT_TP.IMG_BANNER.toString())
							|| scConttTpCd.equals(DisplayEnum.CONTT_TP.HTML.toString())))) {
				displayCornerContensService.validContentLangDspTgt(dspCnrConttDspTgtBoDTO);
			}

			displayCornerContensService.saveContentDspTgt(dspCnrConttDspTgtBoDTO);

			return abTestModRevSn;
		} catch (DspConttValidException ve) {
			throw new DspConttValidException(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveAbTestModPromtImg(com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public Long saveAbTestModPromtImg(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			DspCnrContt dspCnrContt = dspCnrConttScBoDTO.getDspCnrContt();

			// dspCnrConttScBoDTO.setScModResnCont(dspCnrConttScBoDTO.getScModResnCont());
			// dspCnrConttScBoDTO.setAbTestEndDt(dspCnrConttScBoDTO.getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, dspCnrContt.getRevSn());
			dspCnrContt.setRevSn(abTestModRevSn);

			this.savePromtImg(dspCnrConttScBoDTO);

			return abTestModRevSn;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * saveAbTestModCornerContentsList(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public Long saveAbTestModCornerContentsList(DspCnrConttScBoDTO dspCnrConttScBoDTO,
			List<DspCnrConttScBoDTO> gridList) {
		try {
			dspCnrConttScBoDTO.setScModResnCont(gridList.get(0).getScModResnCont());
			dspCnrConttScBoDTO.setAbTestEndDt(gridList.get(0).getAbTestEndDt());
			Long abTestModRevSnPge = this.copyAbTestModRevSn(dspCnrConttScBoDTO,
					gridList.get(0).getDspCnrContt().getRevSn());

			// ABTEST 리비전 Set
			if (gridList != null && gridList.size() > 0) {
				for (DspCnrConttScBoDTO d : gridList) {
					DspCnrContt contt = d.getDspCnrContt();
					contt.setRevSn(abTestModRevSnPge);
				}
			}

			GridCommandTemplate template = new GridCommandTemplate();
			template.execute(gridList, new GridCommandAwareCallback<DspCnrConttScBoDTO>() {
				@Override
				public void commandHandler(List<DspCnrConttScBoDTO> createList, List<DspCnrConttScBoDTO> updateList,
						List<DspCnrConttScBoDTO> deleteList, List<DspCnrConttScBoDTO> dataList) throws Exception {
					saveCornerContentsList(createList, updateList);
				}
			});

			return abTestModRevSnPge;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#
	 * insertAbTestModCornerContentList(com.plgrim.ncp.biz.display.data.
	 * DspCnrConttScBoDTO, java.util.List)
	 */
	@Override
	public DspConttRevResultDTO insertAbTestModCornerContentList(DspCnrConttScBoDTO dspCnrConttScBoDTO,
			List<DspCnrConttExt> list) {
		try {
			DspConttRevResultDTO resultDTO = new DspConttRevResultDTO();

			dspCnrConttScBoDTO.setScModResnCont(dspCnrConttScBoDTO.getScModResnCont());
			dspCnrConttScBoDTO.setAbTestEndDt(dspCnrConttScBoDTO.getAbTestEndDt());
			Long abTestModRevSn = this.copyAbTestModRevSn(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn());
			// ABTEST 리비전 Set
			if (list != null && list.size() > 0) {
				for (DspCnrContt d : list) {
					d.setRevSn(abTestModRevSn);
				}
			}
			resultDTO.setRevSnPge(abTestModRevSn);

			String[] res = displayCornerContensService.insertCornerContentList(dspCnrConttScBoDTO, list);
			resultDTO.setResult(res);

			return resultDTO;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.plgrim.ncp.cmp.display.bo.DisplayCommandComponent#savePreviewToBase(
	 * com.plgrim.ncp.biz.display.data.DspCnrConttScBoDTO)
	 */
	@Override
	public void savePreviewToBase(DspCnrConttScBoDTO dspCnrConttScBoDTO) {
		try {
			String loginId = BOSecurityUtil.getLoginId();
			dspCnrConttScBoDTO.setScAdminId(loginId);

			dspCnrConttScBoDTO.setCnrCopyYn("Y");
			// base 리비전의 해당 코너의 모든 컨텐츠 삭제
			displayRevCommandService.deleteRevPageContt(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn(), baseRevSn);

			// 미리보기 리비전의 해당 코너 모든 컨텐츠를 조회하여 insert
			displayRevCommandService.copyRevCnrConttInfo(dspCnrConttScBoDTO, dspCnrConttScBoDTO.getScRevSn(),
					baseRevSn);

			// 미리보기 리비전 삭제
			dspCnrConttScBoDTO.setCnrCopyYn("N");
			displayRevCommandService.deleteRevPageContt(dspCnrConttScBoDTO, baseRevSn, dspCnrConttScBoDTO.getScRevSn());

			log.info(
					CommonResponseCode.DP_00021_CONTT_미리보기_운영적용_성공.toMessage()
							+ ", scDspBrndId:{}, scDspCtgryNo:{}, scPromtSn:{}, scStrndSn:{}, scEvtNo:{}, prwvRevSn:{}",
					dspCnrConttScBoDTO.getScDspBrndId(), dspCnrConttScBoDTO.getScDspCtgryNo(),
					dspCnrConttScBoDTO.getScPromtSn(), dspCnrConttScBoDTO.getScStrndSn(),
					dspCnrConttScBoDTO.getScEvtNo(), dspCnrConttScBoDTO.getScRevSn());
		} catch (Exception e) {
			log.info(CommonResponseCode.DP_40016_CONTT_미리보기_운영적용_실패.toMessage() + ", dspCnrConttScBoDTO:{}",
					dspCnrConttScBoDTO);
			throw new RuntimeException(e);
		}
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	/**
	 * 기획전 이미지 설정
	 * 
	 * @param dspPromtBoDTO
	 * @param path
	 * @param arLangCd
	 * @param promtImgSectCd
	 * @param dvc
	 * @return
	 */
	private String[] setPromtImg(DspPromtBoDTO dspPromtBoDTO, String[] path, String[] arLangCd, String promtImgSectCd,
			String dvc) {
		try {
			String fileExt = null;
			String fileName = null;
			String fileUrl = path[0];

			String[] realFileName = new String[arLangCd.length];

			DspPromt dspPromt = dspPromtBoDTO.getDspPromt();
			Long promtSn = dspPromt.getPromtSn();

			String pcMobileImgIndUseYn = null;
			String[] arImgAltrtvCont = new String[arLangCd.length];
			String[] arImgFileNm = new String[arLangCd.length];
			String[] arImgFileUrl = new String[arLangCd.length];
			String[] arImgExpsrTxt1Cont = new String[arLangCd.length];
			String[] arImgExpsrTxt2Cont = new String[arLangCd.length];

			if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString())) {
				pcMobileImgIndUseYn = dspPromtBoDTO.getRprst1pceImgYn();
				if (dvc != null && "PC".equals(dvc)) {
					arImgAltrtvCont = dspPromtBoDTO.getArPcImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getArPcImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getArPcImgFileUrl();
					arImgExpsrTxt1Cont = dspPromtBoDTO.getArPcImgExpsrTxt1Cont();
					arImgExpsrTxt2Cont = dspPromtBoDTO.getArPcImgExpsrTxt2Cont();
				} else {
					arImgAltrtvCont = dspPromtBoDTO.getArMobileImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getArMobileImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getArMobileImgFileUrl();
					arImgExpsrTxt1Cont = dspPromtBoDTO.getArMobileImgExpsrTxt1Cont();
					arImgExpsrTxt2Cont = dspPromtBoDTO.getArMobileImgExpsrTxt2Cont();
				}
			} else if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString())) {
				pcMobileImgIndUseYn = dspPromtBoDTO.getRprst2pceImgYn();
				if (dvc != null && "PC".equals(dvc)) {
					arImgAltrtvCont = dspPromtBoDTO.getAr2pcePcImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getAr2pcePcImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getAr2pcePcImgFileUrl();
					arImgExpsrTxt1Cont = dspPromtBoDTO.getAr2pcePcImgExpsrTxt1Cont();
					arImgExpsrTxt2Cont = dspPromtBoDTO.getAr2pcePcImgExpsrTxt2Cont();
				} else {
					arImgAltrtvCont = dspPromtBoDTO.getAr2pceMobileImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getAr2pceMobileImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getAr2pceMobileImgFileUrl();
					arImgExpsrTxt1Cont = dspPromtBoDTO.getAr2pceMobileImgExpsrTxt1Cont();
					arImgExpsrTxt2Cont = dspPromtBoDTO.getAr2pceMobileImgExpsrTxt2Cont();
				}
			} else if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString())) {
				pcMobileImgIndUseYn = dspPromtBoDTO.getGodImgYn();
				if (dvc != null && "PC".equals(dvc)) {
					arImgAltrtvCont = dspPromtBoDTO.getArPcGodImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getArPcGodImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getArPcGodImgFileUrl();
				} else {
					arImgAltrtvCont = dspPromtBoDTO.getArMobileGodImgAltrtvCont();
					arImgFileNm = dspPromtBoDTO.getArMobileGodImgFileNm();
					arImgFileUrl = dspPromtBoDTO.getArMobileGodImgFileUrl();
				}
			}

			for (int i = 0; i < arImgFileNm.length; i++) {
				// real 파일명, 경로생성
				if (StringService.isNotEmpty(arImgFileNm[i])) {
					fileExt = arImgFileNm[i].substring(arImgFileNm[i].lastIndexOf(".") + 1);
					if (StringService.isNotEmpty(arImgFileUrl[i])) {
						fileName = arImgFileNm[i];
						fileUrl = arImgFileUrl[i];
					} else {
						String tp = "";
						if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_1PCE.toString())) {
							if (dvc != null && "PC".equals(dvc)) {
								tp = "_1PCE_PC_";
							} else {
								tp = "_1PCE_MOBILE_";
							}
						} else if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.RPRST_IMG_2PCE.toString())) {
							if (dvc != null && "PC".equals(dvc)) {
								tp = "_2PCE_PC_";
							} else {
								tp = "_2PCE_MOBILE_";
							}
						} else if (promtImgSectCd.equals(DisplayEnum.PROMT_IMG_SECT.PROMT_GOD_IMG.toString())) {
							if (dvc != null && "PC".equals(dvc)) {
								tp = "_PC_GOD_";
							} else {
								tp = "_MOBILE_GOD_";
							}
						}
						fileName = promtSn + tp + arLangCd[i] + "_" + this.getCurrTimeForImg() + "." + fileExt;
						fileUrl = path[0];
					}
					realFileName[i] = fileName;
				} else {
					fileName = "";
					fileUrl = "";

					realFileName[i] = "";
				}

				if (i == 0) {
					DspPromtImg dspPromtImg = new DspPromtImg();
					dspPromtImg.setPromtSn(dspPromt.getPromtSn());
					dspPromtImg.setPromtImgSectCd(promtImgSectCd);

					dspPromtImg.setPcMobileImgIndUseYn(pcMobileImgIndUseYn);
					if (dvc != null && "PC".equals(dvc)) {
						dspPromtImg.setPcImgFileNm(fileName);
						dspPromtImg.setPcImgFileUrl(fileUrl);
						dspPromtImg.setPcImgAltrtvCont(arImgAltrtvCont[i]);
						dspPromtImg.setPcImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
						dspPromtImg.setPcImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

						if (pcMobileImgIndUseYn.equals("N")) {
							dspPromtImg.setMobileImgFileNm(fileName);
							dspPromtImg.setMobileImgFileUrl(fileUrl);
							dspPromtImg.setMobileImgAltrtvCont(arImgAltrtvCont[i]);
							dspPromtImg.setMobileImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
							dspPromtImg.setMobileImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

							displayPlanService.saveDspPromtImg(dspPromtImg, "PC_MOBILE");
						} else {
							displayPlanService.saveDspPromtImg(dspPromtImg, "PC");
						}
					} else {
						dspPromtImg.setMobileImgFileNm(fileName);
						dspPromtImg.setMobileImgFileUrl(fileUrl);
						dspPromtImg.setMobileImgAltrtvCont(arImgAltrtvCont[i]);
						dspPromtImg.setMobileImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
						dspPromtImg.setMobileImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

						displayPlanService.saveDspPromtImg(dspPromtImg, "MOBILE");
					}
				} else {
					DspPromtImgLang dspPromtImgLang = new DspPromtImgLang();
					dspPromtImgLang.setPromtSn(dspPromt.getPromtSn());
					dspPromtImgLang.setPromtImgSectCd(promtImgSectCd);
					dspPromtImgLang.setLangCd(arLangCd[i]);

					// dspPromtImgLang.setPcMobileImgIndUseYn(pcMobileImgIndUseYn);
					if (dvc != null && "PC".equals(dvc)) {
						dspPromtImgLang.setPcImgFileNm(fileName);
						dspPromtImgLang.setPcImgFileUrl(fileUrl);
						dspPromtImgLang.setPcImgAltrtvCont(arImgAltrtvCont[i]);
						dspPromtImgLang.setPcImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
						dspPromtImgLang.setPcImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

						if (pcMobileImgIndUseYn.equals("N")) {
							dspPromtImgLang.setMobileImgFileNm(fileName);
							dspPromtImgLang.setMobileImgFileUrl(fileUrl);
							dspPromtImgLang.setMobileImgAltrtvCont(arImgAltrtvCont[i]);
							dspPromtImgLang.setMobileImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
							dspPromtImgLang.setMobileImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

							displayPlanService.saveDspPromtImgLang(dspPromtImgLang, "PC_MOBILE");
						} else {
							displayPlanService.saveDspPromtImgLang(dspPromtImgLang, "PC");
						}
					} else {
						dspPromtImgLang.setMobileImgFileNm(fileName);
						dspPromtImgLang.setMobileImgFileUrl(fileUrl);
						dspPromtImgLang.setMobileImgAltrtvCont(arImgAltrtvCont[i]);
						dspPromtImgLang.setMobileImgExpsrTxt1Cont(arImgExpsrTxt1Cont[i]);
						dspPromtImgLang.setMobileImgExpsrTxt2Cont(arImgExpsrTxt2Cont[i]);

						displayPlanService.saveDspPromtImgLang(dspPromtImgLang, "MOBILE");
					}

				}

			}

			return realFileName;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 코너 연결 파일 경로 가져오기
	 * 
	 * @param dspCnrConttScBoDTO
	 * @return
	 * @throws Exception
	 */
	private String[] getCnrConttFilePath(DspCnrConttScBoDTO dspCnrConttScBoDTO) throws Exception {
		String[] path = new String[2];

		String dspCtgryNo = dspCnrConttScBoDTO.getScDspCtgryNo();
		String promtSn = dspCnrConttScBoDTO.getScPromtSn();
		String strndSn = dspCnrConttScBoDTO.getScStrndSn();

		String dirPath = "";

		String fileUrl = "";
		String uploadPath = "";

		if (StringService.isNotEmpty(dspCtgryNo)) {
			int idx = 0;
			while (idx < dspCtgryNo.length()) {
				dirPath += StringService.mid(dspCtgryNo, idx, 3) + "/";
				idx = idx + 3;
			}
			dirPath = StringService.substring(dirPath, 0, dirPath.length() - 1);

			fileUrl = categoryHttpPath + "/" + dirPath + contentPath;
			uploadPath = categoryUploadPath + "/" + dirPath + contentPath;
		} else if (StringService.isNotEmpty(promtSn)) {
			dirPath += (Long.parseLong(promtSn) % 100);
			fileUrl = planHttpPath + "/" + dirPath + "/" + promtSn + contentPath;
			uploadPath = planUploadPath + "/" + dirPath + "/" + promtSn + contentPath;
		} else if (StringService.isNotEmpty(strndSn)) {
			dirPath += (Long.parseLong(strndSn) % 100);
			fileUrl = strndHttpPath + "/" + dirPath + "/" + strndSn + contentPath;
			uploadPath = strndUploadPath + "/" + dirPath + "/" + strndSn + contentPath;
		}

		path[0] = fileUrl;
		path[1] = uploadPath;

		return path;
	}

	/**
	 * 대표 이미지 파일 경로 가져오기
	 * 
	 * @param key
	 * @param tp
	 * @return
	 * @throws Exception
	 */
	private String[] getRprstImgFilePath(Object key, String tp) throws Exception {

		String[] path = new String[2];

		String dirPath = "";
		String fileUrl = "";
		String uploadPath = "";
		long prKey = 0;

		if (tp.equals("PROMT")) {
			prKey = Long.parseLong(key.toString());
			dirPath = "" + (prKey % 100);
			fileUrl = planHttpPath + "/" + dirPath + "/" + prKey;
			uploadPath = planUploadPath + "/" + dirPath + "/" + prKey;

		} else if (tp.equals("CTGRY")) {
			int idx = 0;
			while (idx < key.toString().length()) {
				dirPath += StringService.mid(key.toString(), idx, 3) + "/";
				idx = idx + 3;
			}
			dirPath = StringService.substring(dirPath, 0, dirPath.length() - 1);

			fileUrl = categoryHttpPath + "/" + dirPath;
			uploadPath = categoryUploadPath + "/" + dirPath;

		} else if (tp.equals("STRND")) {
			prKey = Long.parseLong(key.toString());
			dirPath = "" + (prKey % 100);
			fileUrl = strndHttpPath + "/" + dirPath + "/" + prKey;
			uploadPath = strndUploadPath + "/" + dirPath + "/" + prKey;

		}

		path[0] = fileUrl;
		path[1] = uploadPath;

		return path;
	}

	/**
	 * 운영폴더로 파일 이동
	 * 
	 * @param realFilePath
	 * @param realFileName
	 * @param realFileUrl
	 * @param imgTempPath
	 * @param tempFileNameList
	 * @return
	 */
	private int moveTempImage(String realFilePath, String[] realFileName, String realFileUrl, String imgTempPath,
			String[] tempFileNameList) {

		int moveFileCnt = 0;

		for (int i = 0; i < realFileName.length; i++) {
			if (StringService.isNotEmpty(tempFileNameList[i])) {
				String fileName = StringService.replace(tempFileNameList[i], "..", "");

				if (!"".equals(fileName)) {

					String rFileName = realFileName[i];

					if (!fileName.equals(rFileName)) {
						if (realFilePath != null && !"".equals(realFilePath) && rFileName != null
								&& !"".equals(rFileName)) {
							String tempResourcePath = this.bucketName + ":" + imgTempPath + fileName;
							String mallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
							String commitResourcePath = this.bucketName + ":" + getSaveImagePath(mallId) + realFilePath
									+ "/" + rFileName;

							s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);

							moveFileCnt++;

						}
					}
				}
			}
		}
		return moveFileCnt;
	}

	String getSaveImagePath(String mallId) {
		return getConfigService().getProperty("ncp_web_bo.cloud." + mallId + ".bucket.image.folder.path");
	}

	/**
	 * 이미지 컨텐츠 복사하기
	 * 
	 * @param realFilePath
	 * @param realFileName
	 * @param srcPath
	 * @param srcFileName
	 * @throws Exception
	 */
	private void copyPromtImgContt(String realFilePath, String realFileName, String srcPath, String srcFileName)
			throws Exception {

		if (StringService.isNotEmpty(srcFileName)) {

			File srcFile = new File(srcPath, srcFileName);
			String realFile = realFileName;
			if (realFilePath != null && !"".equals(realFilePath) && realFile != null && !"".equals(realFile)) {
				File imageFile = new File(realFilePath, realFile);

				if (imageFile.exists()) {
					IOService.deleteFile(realFilePath, realFile);
				}
				IOService.copyFile(srcFile, imageFile);

			}
		}

	}

	/**
	 * CDN 이미지 퍼징
	 * 
	 * @param imgFileUrl
	 * @throws Exception
	 */
	private void cdnPerge(String imgFileUrl) throws Exception {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId("DXM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		// CdnSDO cdnSDO = new CdnSDO();
		// cdnSDO.setUser("cii");
		// cdnSDO.setPass("!Q@W#E");
		// cdnSDO.setPad(imageSvcPath);
		// cdnSDO.setType("item");
		// cdnSDO.setOutput("json");
		// cdnSDO.setCountryCode("KR");
		//
		// List<String> mails = new ArrayList<>();
		// cdnSDO.setMailTo(mails);
		//
		// List<String> paths = new ArrayList<>();
		// paths.add(imageSvcPath + imgFileUrl);
		//
		// cdnSDO.setPath(paths);
		// cdnSDO.setCallerId(this.getClass().getName() + ".sampleCdnAPI");
		// cdnAdapter.doPurge(cdnSDO, adapterHeader) ;
	}

	/**
	 * 이미지 파일명을 위한 현재 시간 가져오기
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getCurrTimeForImg() throws Exception {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDate = simpleDateFormat.format(date);
		return currentDate;
	}

}
