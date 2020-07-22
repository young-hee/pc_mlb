package com.plgrim.ncp.biz.promotion.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrIssuCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.Prm;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmAplPd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpn;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmCpnCrtfcCd;
import com.plgrim.ncp.base.entities.datasource1.prm.PrmLang;
import com.plgrim.ncp.base.enums.PromotionEnum;
import com.plgrim.ncp.base.enums.PromotionEnum.CouponIssueLimitReason;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionAplPeriod;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDatePattern;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDeviceKind;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDplctIssuLmitCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionOrderDiscountType;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionProcedureSect;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionPrstImgUseSect;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionPubliQtyLmitCd;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionStat;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionType;
import com.plgrim.ncp.base.enums.SysInfoEnum.MallIdEnum;
import com.plgrim.ncp.base.repository.prm.PrmAplPdRepository;
import com.plgrim.ncp.base.repository.prm.PrmCpnCrtfcCdRepository;
import com.plgrim.ncp.base.repository.prm.PrmCpnRepository;
import com.plgrim.ncp.base.repository.prm.PrmLangRepository;
import com.plgrim.ncp.base.repository.prm.PrmRepository;
import com.plgrim.ncp.biz.promotion.data.PrmAplPdBoDTO;
import com.plgrim.ncp.biz.promotion.data.PrmExtend;
import com.plgrim.ncp.biz.promotion.data.PromotionBoDTO;
import com.plgrim.ncp.biz.promotion.exception.PromotionStatusChangeException;
import com.plgrim.ncp.biz.promotion.repository.PromotionRepository;
import com.plgrim.ncp.biz.promotion.result.CouponPromotionResult;
import com.plgrim.ncp.biz.promotion.result.MbrIssuCpnResult;
import com.plgrim.ncp.biz.promotion.result.PromotionBoResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.cloud.CloudFileSystem;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.interfaces.promotion.adapter.PromotionAdapter;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueApplyGoodsSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueResultDataSDO;
import com.plgrim.ncp.interfaces.promotion.data.OnOffCouponIssueSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

@Service
public class PromotionService extends AbstractService {

    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PrmRepository prmRepository;

    @Autowired
    private PrmCpnRepository prmCpnRepository;

    @Autowired
    private PrmCpnCrtfcCdRepository prmCpnCrtfcCdRepository;

    @Autowired
    private PrmAplPdRepository prmAplPdRepository;

    @Autowired
    private InterfaceApiCommon interfaceApiCommon;

	@Autowired
	S3FileSystem s3FileSystem;
	
	@Autowired
	private PromotionAdapter promotionAdapter;
	
	@Autowired
	private PrmLangRepository prmLangRepository;
	
    @Value("${ncp_base.cloud.bucketName}")
    private String bucketName;
    
	@Value("${ncp_web_bo.image.promotion.promotion.http.path}")			
	String promotionHttpPath;
	
	@Value("${ncp_web_bo.image.promotion.promotion.upload.path}")
	String promotionUploadPath;    
    
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

    private String getUploadImageUrlPath() {
        return getConfigService().getProperty( "ncp_web_bo.cloud.bucket.pec.temp.folder.path" )+ getDateService().getStringCurrentToday() + "/";
    }
    
    private String getSaveImagePath(String mallId) {
    	mallId = BOSecurityUtil.getAuthMall().get("mallId").toString();
    	return getConfigService().getProperty( "ncp_web_bo.cloud."+mallId+".bucket.image.folder.path" );
    }
    
	/**
	 * 이미지 temp 경로 설정
	 * @param uploadImageType
	 * @return
	 * @throws Exception
	 */
	private String getTempUploadImagePath( String uploadImageType  ) throws Exception{		
		return getConfigService().getProperty( "ncp_base.image."+uploadImageType+".upload.temp.path" );	
	}
    
    /**
     * 기본 프로모션 select - for 유효성체크
     *
     * @param prm
     * @return
     */
    public PrmExtend selectValidPromotionInfo(Prm prm) throws Exception {
        PrmExtend prmEx = promotionRepository.selectValidPromotionInfo(prm);
        return prmEx;
    }

    public List<PromotionBoResult> selectPromotionList(PromotionBoDTO promotionBoDTO) throws Exception {
        List<PromotionBoResult> resultList = promotionRepository.selectPromotionList(promotionBoDTO);
        return resultList;
    }

    public void insertPromotion(PromotionBoDTO promotionBoDTO) throws Exception {

        // 이미지 url 생성 및 이미지를 이동한다.
        if (promotionBoDTO.getSectFileList() != null && !promotionBoDTO.getSectFileList().isEmpty()) {

            this.saveImageFileList(promotionBoDTO);

        }else if(PromotionType.CPN.toString().equals(promotionBoDTO.getPrm().getPrmTpCd()) &&
        		!StringService.isEmpty(promotionBoDTO.getPrmCpn().getNaverEpCpnYn()) &&
        		PromotionEnum.YES.toString().equals(promotionBoDTO.getPrmCpn().getNaverEpCpnYn()) ){

        	String pcCpnImgUrl = promotionBoDTO.getPrmCpn().getPcCpnImgUrl();
            String mobileCpnImgUrl = promotionBoDTO.getPrmCpn().getMobileCpnImgUrl();

        	if(!StringService.isEmpty(pcCpnImgUrl) && !StringService.isEmpty(mobileCpnImgUrl)){
        		promotionBoDTO.getPrmCpn().setPcCpnImgUrl(this.saveCpnImageFile(promotionBoDTO, pcCpnImgUrl, PromotionDeviceKind.PC.toString()));
        		promotionBoDTO.getPrmCpn().setMobileCpnImgUrl(this.saveCpnImageFile(promotionBoDTO, mobileCpnImgUrl, PromotionDeviceKind.MOBILE.toString()));
        	}

        }

        promotionRepository.insertPromotion(promotionBoDTO);

    }

    /**
     * 프로모션 적용기간 insert
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void insertPromotionPeriod(PromotionBoDTO promotionBoDTO) throws Exception {

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String prmStatCd = promotionBoDTO.getPrm().getPrmStatCd();

        // 프로모션 적용기간 - 발급기간 기준
        PrmAplPdBoDTO prmAplPd = new PrmAplPdBoDTO();

        prmAplPd.setAplPdCd("APL_PD");
        prmAplPd.setPrmNo(prmNo);
        prmAplPd.setLoginId(promotionBoDTO.getLoginId());

        prmAplPd.setBegDate(promotionBoDTO.getPrm().getPrmBegDate());                //프로모션 시작일자
        prmAplPd.setEndDate(promotionBoDTO.getPrm().getPrmEndDate());                //프로모션 종료일자

        prmAplPd.setAplDowsmon(promotionBoDTO.getPrm().getPrmAplDowsmon());          //프로모션 적용요일
        prmAplPd.setDatebyBegHour(promotionBoDTO.getPrm().getPrmDatebyBegHour());    // 프로모션 시작시각
        prmAplPd.setDatebyEndHour(promotionBoDTO.getPrm().getPrmDatebyEndHour());    // 프로모션 종료시작

        this.insertPromotionApplyPeriodProcess(prmAplPd);

        // 쿠폰사용기간코드가 기간인 경우.
        if (PromotionStat.APRV_WAIT.toString().equals(prmStatCd)
                && PromotionType.CPN.toString().equals(promotionBoDTO.getPrm().getPrmTpCd())
                && "PD_APPN".equals(promotionBoDTO.getPrmCpn().getCpnUsePdCd())) {

            // 프로모션 적용기간 - 사용기간 기준
            PrmAplPdBoDTO prmCpnUsePd = new PrmAplPdBoDTO();

            prmCpnUsePd.setAplPdCd("USE_PD");
            prmCpnUsePd.setPrmNo(prmNo);
            prmCpnUsePd.setLoginId(promotionBoDTO.getLoginId());

            prmCpnUsePd.setBegDate(promotionBoDTO.getPrmCpn().getCpnUsePdBegDate());                //쿠폰 시작일자
            prmCpnUsePd.setEndDate(promotionBoDTO.getPrmCpn().getCpnUsePdEndDate());                //쿠폰 종료일자

            prmCpnUsePd.setAplDowsmon(promotionBoDTO.getPrmCpn().getCpnUsePsbDowsmon());          //프로모션 적용요일
            prmCpnUsePd.setDatebyBegHour(promotionBoDTO.getPrmCpn().getDatebyUsePsbBegHour());    // 프로모션 시작시각
            prmCpnUsePd.setDatebyEndHour(promotionBoDTO.getPrmCpn().getDatebyUsePsbEndHour());    // 프로모션 종료시작

            this.insertPromotionApplyPeriodProcess(prmCpnUsePd);
        }
        // 추가적립 프로모션 유효기간 저장
        else if (PromotionStat.APRV_WAIT.toString().equals(prmStatCd)
        		// 멤버십 포인트는 유효기간이 필요하지 않는다 하여 삭제 2016.04.16
//        		&& (PromotionType.MBSH_PNT.toString().equals(promotionBoDTO.getPrm().getPrmTpCd()) || PromotionType.WEBPNT.toString().equals(promotionBoDTO.getPrm().getPrmTpCd()))
        		&& PromotionType.WEBPNT.toString().equals(promotionBoDTO.getPrm().getPrmTpCd())
        		) {

            // 프로모션 적용기간 - 사용기간 기준
            PrmAplPdBoDTO prmCpnUsePd = new PrmAplPdBoDTO();

            prmCpnUsePd.setAplPdCd("USE_PD");
            prmCpnUsePd.setPrmNo(prmNo);
            prmCpnUsePd.setLoginId(promotionBoDTO.getLoginId());

            prmCpnUsePd.setBegDate(promotionBoDTO.getPrmEx().getPrmUseBegDate());                //사용 시작일자
            prmCpnUsePd.setEndDate(promotionBoDTO.getPrmEx().getPrmUseEndDate());                //사용 종료일자

            this.insertPromotionApplyPeriodProcess(prmCpnUsePd);
        }
    }

    private void insertPromotionApplyPeriodProcess(PrmAplPdBoDTO prmAplPdBoDTO) throws Exception {

        String prmNo = prmAplPdBoDTO.getPrmNo();
        String loginId = prmAplPdBoDTO.getLoginId();
        String aplPdCd = prmAplPdBoDTO.getAplPdCd();

        String begDate = prmAplPdBoDTO.getBegDate();                //프로모션 시작일자
        String endDate = prmAplPdBoDTO.getEndDate();                //프로모션 종료일자

        String aplDowsmon = prmAplPdBoDTO.getAplDowsmon();          //프로모션 적용요일
        String datebyBegHour = prmAplPdBoDTO.getDatebyBegHour();    // 프로모션 시작시각
        String datebyEndHour = prmAplPdBoDTO.getDatebyEndHour();    // 프로모션 종료시작

        if (StringService.isEmpty(datebyBegHour)) {
            datebyBegHour = "0000";
        }

        if (StringService.isEmpty(datebyEndHour)) {
            datebyEndHour = "2359";
        }

        String sPattern = PromotionDatePattern.SHORT.toString();
        String lPattern = PromotionDatePattern.LONG.toString();

        DateTimeFormatter sDateDtf = DateTimeFormat.forPattern(sPattern);

        int daysBetweenCnt = Days.daysBetween(sDateDtf.parseDateTime(begDate), sDateDtf.parseDateTime(endDate)).getDays();

        if (StringService.isNotEmpty(aplDowsmon)) {

            for (int i = 0; i <= daysBetweenCnt; i++) {

                // +day 일자(yyyyMMdd)
                String foreachDt = sDateDtf.parseDateTime(begDate).plusDays(i).toString(sDateDtf);

                // 요일코드 - 월(1), 화(2), 수(3), 목(4), 금(5), 토(6), 일(7)
                int dowNo = sDateDtf.parseDateTime(foreachDt).getDayOfWeek();

                DateTime begDt = DateTimeFormat.forPattern(lPattern).parseDateTime(foreachDt + datebyBegHour);
                DateTime endDt = DateTimeFormat.forPattern(lPattern).parseDateTime(foreachDt + datebyEndHour);

                if (Integer.parseInt(datebyBegHour) > Integer.parseInt(datebyEndHour)) {
                    endDt = endDt.plusDays(BigDecimal.ONE.intValue());
                }

                for (String strDwm : aplDowsmon.split(PromotionSeparator.DELIMITER.toString())) {

                    if (String.valueOf(dowNo).equals(strDwm)) {

                        PrmAplPd prmAplPd = new PrmAplPd();

                        prmAplPd.setPrmNo(prmNo);
                        prmAplPd.setRegtrId(loginId);
                        prmAplPd.setUdterId(loginId);
                        prmAplPd.setBegDt(begDt.toDate());
                        prmAplPd.setAplPdCd(aplPdCd);

                        if (StringService.isEmpty(prmAplPdBoDTO.getDatebyEndHour())) {
                            endDt = endDt.plusSeconds(59);
                        }

                        prmAplPd.setEndDt(endDt.toDate());

                        // insert 프로모션 적용기간
                        prmAplPdRepository.insertPrmAplPd(prmAplPd);
                    }
                }
            }
        }
        else {

            DateTime begDt = DateTimeFormat.forPattern(lPattern).parseDateTime(begDate + datebyBegHour);
            DateTime endDt = DateTimeFormat.forPattern(lPattern).parseDateTime(endDate + datebyEndHour);

            PrmAplPd prmAplPd = new PrmAplPd();

            prmAplPd.setPrmNo(prmNo);
            prmAplPd.setRegtrId(loginId);
            prmAplPd.setUdterId(loginId);
            prmAplPd.setBegDt(begDt.toDate());
            prmAplPd.setAplPdCd(aplPdCd);

            if (StringService.isEmpty(prmAplPdBoDTO.getDatebyEndHour())) {
                endDt = endDt.plusSeconds(59);
            }

            if (Integer.parseInt(datebyBegHour) > Integer.parseInt(datebyEndHour)) {
                endDt = endDt.plusDays(BigDecimal.ONE.intValue());
            }

            prmAplPd.setEndDt(endDt.toDate());

            // insert 프로모션 적용기간
            prmAplPdRepository.insertPrmAplPd(prmAplPd);
        }
    }

    public int updatePromotionStatus(PromotionBoDTO promotionBoDTO) throws Exception {

        int result = 0;
        String prmStatCd = promotionBoDTO.getPrm().getPrmStatCd();
        String loginId = promotionBoDTO.getLoginId();
        String prmCnclDscr = promotionBoDTO.getPrm().getPrmCnclDscr();

        for (String prmNo : promotionBoDTO.getPrm().getPrmNo().split(PromotionSeparator.DELIMITER.toString())) {

            PromotionBoDTO pbd = new PromotionBoDTO();

            Prm prm = new Prm();
            prm.setPrmNo(prmNo);
            PrmExtend validPrm = promotionRepository.selectValidPromotionInfo(prm);

            String validPrmStatCd = validPrm.getPrmStatCd();
            // DB와 param 상태값이 같으면 처리하지 않는다.
            if (StringService.isNotEmpty(validPrmStatCd) && !prmStatCd.equals(validPrmStatCd)) {

                if (PromotionStat.APRV.toString().equals(prmStatCd)) {

                    if (PromotionType.ORD_DC.toString().equals(validPrm.getPrmTpCd()) && PromotionOrderDiscountType.CRS_DC.toString().equals(validPrm.getPrmDtlTpCd())) {
                        if (validPrm.getCrsDcAplCount() < 1) {
                            //throw new PromotionApplyGoodsRequiredException(null);
                            continue;
                        }
                    } else {
                        if (validPrm.getGodAplCount() < 1) {
                            //throw new PromotionApplyGoodsRequiredException(null);
                            continue;
                        }
                    }
            	}

                prm.setPrmStatCd(prmStatCd);
                prm.setUdterId(loginId);
                prm.setPrmCnclDscr(prmCnclDscr);

                result += promotionRepository.updatePromotionStatus(prm);

                // [20150904] 승인시 프로시져 호출 제외
                if (result > 0 && !PromotionStat.APRV.toString().equals(prmStatCd)) {
                    //프로모션 적용 상품 가격 (배치)프로시져 수동 실행
                    pbd.getPrm().setPrmNo(prmNo);
                    pbd.setIcSect(PromotionProcedureSect.PRM.toString());
                    promotionRepository.updatePromotionGoodsPriceProcedure(pbd);
                    // 즉시포인트 사용여부 적용 프로시져
                    promotionRepository.updateGodImdtlDcProcedure(pbd);

                    String ocResultCd = pbd.getOcResultCd();
                    if (!"0".equals(ocResultCd)) {
                        promotionBoDTO.setOcResultCd(ocResultCd);
                        promotionBoDTO.setOcResultCont(pbd.getOcResultCont());
                    }
                }

                //프로모션 승인 or 발행중지 상태로 변경 했을때 - 상품상세노출 프로모션 생성 프로시져 실행
                if (result > 0 && (PromotionStat.APRV.toString().equals(prmStatCd) || PromotionStat.STPGE.toString().equals(prmStatCd))) {
                	//프로모션 적용 상품 가격 (배치)프로시져 수동 실행
                    pbd.getPrm().setPrmNo(prmNo);
                    pbd.setIcSect(PromotionProcedureSect.PRM.toString());
                    // 상품상세노출 프로모션 생성 프로시져 실행
                    promotionRepository.updateDspGodPrmProcedure(pbd);
                }
            }
        }

        if (result < 1) {
            throw new PromotionStatusChangeException(null);
        }

        return result;
    }

    /**
     * 프로모션 수정
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int updatePromotion(PromotionBoDTO promotionBoDTO) throws Exception {

        // 이미지 url 생성 및 이미지를 이동한다.
        if (promotionBoDTO.getSectFileList() != null && !promotionBoDTO.getSectFileList().isEmpty()) {
            this.saveImageFileList(promotionBoDTO);
        }else if(PromotionType.CPN.toString().equals(promotionBoDTO.getPrm().getPrmTpCd()) &&
        		!StringService.isEmpty(promotionBoDTO.getPrmCpn().getNaverEpCpnYn()) &&
        		PromotionEnum.YES.toString().equals(promotionBoDTO.getPrmCpn().getNaverEpCpnYn()) ){

        	String pcCpnImgUrl = promotionBoDTO.getPrmCpn().getPcCpnImgUrl();
            String mobileCpnImgUrl = promotionBoDTO.getPrmCpn().getMobileCpnImgUrl();

        	if(!StringService.isEmpty(pcCpnImgUrl) ){
        		promotionBoDTO.getPrmCpn().setPcCpnImgUrl(this.saveCpnImageFile(promotionBoDTO, pcCpnImgUrl, PromotionDeviceKind.PC.toString()));
        	}

        	if(!StringService.isEmpty(mobileCpnImgUrl)){
        		promotionBoDTO.getPrmCpn().setMobileCpnImgUrl(this.saveCpnImageFile(promotionBoDTO, mobileCpnImgUrl, PromotionDeviceKind.MOBILE.toString()));
        	}

        }

        if (StringService.isEmpty(promotionBoDTO.getPrm().getRtlPrcAplYn())) {
        	promotionBoDTO.getPrm().setRtlPrcAplYn("N");	// Default : "N"
        }
        
        int result = prmRepository.updatePrm(promotionBoDTO.getPrm());

        return result;
    }

    /**
     * 프로모션 수정(승인용)
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public int updatePromotionAprvKind(PromotionBoDTO promotionBoDTO) throws Exception {

        // 이미지 url 생성 및 이미지를 이동한다.
        if (promotionBoDTO.getSectFileList() != null && !promotionBoDTO.getSectFileList().isEmpty()) {
            this.saveImageFileList(promotionBoDTO);
        }

        int result = promotionRepository.updatePromotionAprvKind(promotionBoDTO);

        //프로모션 적용 상품 가격 (배치)프로시져 수동 실행
        promotionBoDTO.setIcSect(PromotionProcedureSect.PRM.toString());
        promotionRepository.updatePromotionGoodsPriceProcedure(promotionBoDTO);

        return result;
    }

    public int deletePromotionApplyPeriod(PromotionBoDTO promotionBoDTO) throws Exception {

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String prmStatCd = promotionBoDTO.getPrm().getPrmStatCd();

        PrmAplPd prmAplPd = new PrmAplPd();
        prmAplPd.setPrmNo(prmNo);

        if (PromotionStat.APRV.toString().equals(prmStatCd)) {
            prmAplPd.setAplPdCd("APL_PD");
        }

        int result = promotionRepository.deletePromotionApplyPeriod(prmAplPd);
        return result;
    }

    /**
     * 프로모션 적용 상품 가격 (배치)프로시져 수동 실행
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void updatePromotionGoodsPriceProcedure(PromotionBoDTO promotionBoDTO) throws Exception {
        promotionRepository.updatePromotionGoodsPriceProcedure(promotionBoDTO);
    }

    /**
     * 프로모션 즉시포인트 사용여부 적용 프로시져 실행
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void updateGodImdtlDcProcedure(PromotionBoDTO promotionBoDTO) throws Exception {
        promotionRepository.updateGodImdtlDcProcedure(promotionBoDTO);
    }

    /**
     * 발급 쿠폰 사용금지 update
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void updateCouponStopStatus(List<PromotionBoDTO> updateList) throws Exception {

        String loginId = BOSecurityUtil.getLoginId();

        if (updateList != null) {
            for (PromotionBoDTO promotionBoDTO : updateList) {
                //쿠폰 상태 CPN_STAT : 사용중지 : USE_STPGE 셋팅
                promotionBoDTO.getMbrIssuCpn().setCpnStatCd(PromotionEnum.CouponUseStatusCd.USE_STPGE.toString());
                promotionBoDTO.getMbrIssuCpn().setUdterId(loginId);

                promotionRepository.updateCouponStopStatus(promotionBoDTO.getMbrIssuCpn());
            }
        }
    }

    /**
     * 쿠폰 인증 코드 사용 조회 - Interface
     *
     * return - S (사용가능)
     * return - U (사용종료)
     * return - E (실패)
     *
     * @param prmCpnCrtfcCd
     * @throws Exception
     */
    public String selectCpnCrtfcCdChkNum(PrmCpnCrtfcCd prmCpnCrtfcCd) throws Exception {

    	String chkNum = "";
    	String useChk = "";

    	PrmCpnCrtfcCd result = prmCpnCrtfcCdRepository.selectPrmCpnCrtfcCd(prmCpnCrtfcCd);

    	if(result != null){

    	    if(result.getChkNum() == null) chkNum = prmCpnCrtfcCd.getCpnCrtfcCd();
    	    else                           chkNum = prmCpnCrtfcCd.getCpnCrtfcCd() + result.getChkNum();

    		// ERP 인터페이스 헤더설정
    		AdapterHeader adapterHeader = new AdapterHeader();
    		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
    		adapterHeader.setMallId("DXM");
    		adapterHeader.setLangCd("KOR");
    		adapterHeader.setDvcCd("PC");

    	}else{
    		useChk = "E"; //ERROR
    	}

    	return useChk;
    }

    /**
     * 사용 온라인 쿠폰 복원 (CS)
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void updateCouponRevertStatus(MbrIssuCpn mbrIssuCpn) throws Exception {

        //클레임 step - 쿠폰상태값 : 사용중지
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.USE_STPGE.toString());
        promotionRepository.updateCouponRevertStatus(mbrIssuCpn);

        //쿠폰 복원 step - 쿠폰상태값 : 미사용
        mbrIssuCpn.setCpnStatCd(PromotionEnum.CouponUseStatusCd.NO_USE.toString());
        mbrIssuCpn.setOrdNo(null);
        mbrIssuCpn.setClmNo(null);
        mbrIssuCpn.setCpnUseDt(null);
        promotionRepository.updateCouponRevertStatus(mbrIssuCpn);
    }

    /**
     * 쿠폰 프로모션 발급 가능 체크
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public PromotionBoResult selectCouponIssueUseCheck(PromotionBoDTO promotionBoDTO) throws Exception {

        String chkMsg = CouponIssueLimitReason.SUCCES.toString();
        PromotionBoResult promotionBoResult = new PromotionBoResult();

        //프로모션(쿠폰)상태 체크
        //프로모션 기본 정보
        Prm prmInfo = prmRepository.selectPrm(promotionBoDTO.getPrm());
        promotionBoResult.setPrm(prmInfo);

        //발급수량 제한 개수와 (발급수+발급신청수) 체크
        //프로모션 쿠폰 정보
        PrmCpn prmCpnInfo = prmCpnRepository.selectPrmCpn(promotionBoDTO.getPrmCpn());
        promotionBoResult.setPrmCpn(prmCpnInfo);

        //쿠폰 프로모션 상태 체크
        if (PromotionStat.STPGE.toString().equals(prmInfo.getPrmStatCd())) {
            //chkMsg = "쿠폰 프포모션 상태가 중지 중 입니다.\n\n중지 상태에서는 발급 할 수 없습니다.";
            chkMsg = CouponIssueLimitReason.PRM_STAT_STPGE.toString();
            promotionBoResult.setChkMsg(chkMsg);
            return promotionBoResult;
        }
        //쿠폰 발급 수량 체크
        if (PromotionPubliQtyLmitCd.LMIT.toString().equals(prmCpnInfo.getPubliQtyLmitCd())) {
            //프로모션 기준 발급 된 쿠폰 총 개수 - mbrNo 조건 없음
        	String mbrNo = promotionBoDTO.getMbrIssuCpn().getMbrNo();
        	promotionBoDTO.getMbrIssuCpn().setMbrNo(null);
            int issuCnt = promotionRepository.selectCouponIssueCount(promotionBoDTO.getMbrIssuCpn());
            if (prmCpnInfo.getAllPubliQty() < (issuCnt + promotionBoDTO.getListMbrNo().size())) {
                //chkMsg = "총 발급수량이 초과 되었습니다.";
                chkMsg = CouponIssueLimitReason.TOT_ISSU_QTY_EXCESS.toString();
                promotionBoResult.setChkMsg(chkMsg);
                return promotionBoResult;
            }
            promotionBoDTO.getMbrIssuCpn().setMbrNo(mbrNo);
        }
        //발급 가능 일시 체크
        promotionBoDTO.setPrmAplPd(new PrmAplPd());
        promotionBoDTO.getPrmAplPd().setPrmNo(promotionBoDTO.getPrm().getPrmNo());
        promotionBoDTO.getPrmAplPd().setAplPdCd(PromotionAplPeriod.APL_PD.toString());
        int issueDateCnt = promotionRepository.selectPrmAplPdCount(promotionBoDTO.getPrmAplPd());

        if (issueDateCnt < 1) {
            //chkMsg = "쿠폰 발급 기간이 아닙니다.";
            chkMsg = CouponIssueLimitReason.ISMCBTW_OFF.toString();
            promotionBoResult.setChkMsg(chkMsg);
            return promotionBoResult;
        }

        promotionBoResult.setChkMsg(chkMsg);

        return promotionBoResult;
    }

    /**
     * 회원 쿠폰 발급
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public List<MbrIssuCpnResult> selectCouponIssueCheckResult(PromotionBoDTO promotionBoDTO, PrmCpn prmCpnInfo) throws Exception {

        //발급대상 list 리턴 값
        List<MbrIssuCpnResult> mbrIssuCpnList = new ArrayList<MbrIssuCpnResult>();
        //발급대상 set
        MbrIssuCpnResult mbrIssuCpnInfo = null;
        int issuCnt = 0;
        int mbrChk = 0;

        for (int i = 0; i < promotionBoDTO.getListMbrNo().size(); i++) {

            //발급 대상 회원 set
            promotionBoDTO.getMbrIssuCpn().setMbrNo(promotionBoDTO.getListMbrNo().get(i));

            //개별회원 발급 개수 체크 - mbrNo 조건 있음
            issuCnt = promotionRepository.selectCouponIssueCount(promotionBoDTO.getMbrIssuCpn());

            //결과 값 retrun용 객체 set
            mbrIssuCpnInfo = new MbrIssuCpnResult();
            mbrIssuCpnInfo.setPrmNo(promotionBoDTO.getMbrIssuCpn().getPrmNo());
            mbrIssuCpnInfo.setMbrNo(promotionBoDTO.getMbrIssuCpn().getMbrNo());
            mbrIssuCpnInfo.setCpnCrtfcCd(promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd());

            //회원번호 유효체크
            mbrChk = promotionRepository.selectMemberCheck(promotionBoDTO.getMbrIssuCpn());

            if (mbrChk > 0) {
                //회원ID별 발급 개수 제한 여부
                if (PromotionDplctIssuLmitCd.LMIT.toString().equals(prmCpnInfo.getDplctIssuLmitCd())) {

                    //회원ID별 발급이 제한 수량을 넘어갈때
                    if (prmCpnInfo.getDplctIssuPsbQty() < (issuCnt + 1)) {
                        //chkMsg = "\n\nID당 발급 수량을 초과하는 사용자가 존재합니다.\n발급 수량 초과 대상자는 제외되었습니다.";
                        mbrIssuCpnInfo.setRstCd(CouponIssueLimitReason.IDBY_ISSU_QTY_EXCESS.toString()); //ID당 발급제한 수량 초과
                    }
                    else {
                        //쿠폰 회원 발급 insert
                        promotionRepository.insertCouponIssue(promotionBoDTO.getMbrIssuCpn());
                        //쿠폰 발급 재고 수량 update
                        promotionRepository.updateIssueCouponInvQty(promotionBoDTO.getPrmCpn());

                        mbrIssuCpnInfo.setRstCd(CouponIssueLimitReason.SUCCES.toString()); //성공
                    }
                }
                else {
                    //쿠폰 회원 발급 insert
                    promotionRepository.insertCouponIssue(promotionBoDTO.getMbrIssuCpn());
                    //쿠폰 발급 재고 수량 update
                    promotionRepository.updateIssueCouponInvQty(promotionBoDTO.getPrmCpn());

                    mbrIssuCpnInfo.setRstCd(CouponIssueLimitReason.SUCCES.toString()); //성공
                }
            }
            else {
                //유효하지 않는 회원 번호
                mbrIssuCpnInfo.setRstCd(CouponIssueLimitReason.MBR_INFO_ABSNCE.toString()); //성공
            }

            mbrIssuCpnList.add(mbrIssuCpnInfo);

        }
        return mbrIssuCpnList;
    }

    /**
     * 회원 온/오프 보유 쿠폰 일련번호(난수) 조회
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public List<String> selectMemberOwnCouponCrtfcCd(PromotionBoDTO promotionBoDTO) throws Exception {
        return promotionRepository.selectMemberOwnCouponCrtfcCd(promotionBoDTO);
    }

    /**
     * 쿠폰 일련번호(난수) 조회
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public String selectCouponCrtfcCd(MbrIssuCpn mbrIssuCpn) throws Exception {
        return promotionRepository.selectCouponCrtfcCd(mbrIssuCpn);
    }

    /**
     * 쿠폰 발급 대상 체크 - 몰, 디바이스, 언어, 회원유형, 회원속성
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public int selectCouponIssueTargetCheck(PromotionBoDTO promotionBoDTO) throws Exception {
        return promotionRepository.selectCouponIssueTargetCheck(promotionBoDTO);
    }

    /**
     * 일련번호(난수) 쿠폰 회원 발급 사용 체크
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public PromotionBoResult selectCouponCrtfcCdIssueUseCheck(MbrIssuCpn mbrIssuCpn) throws Exception {
        return promotionRepository.selectCouponCrtfcCdIssueUseCheck(mbrIssuCpn);
    }

    /**
     * 쿠폰 회원 발급 insert
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public void insertCouponIssue(MbrIssuCpn mbrIssuCpn) throws Exception {
        promotionRepository.insertCouponIssue(mbrIssuCpn);
    }

    /**
     * 쿠폰 발급 재고 수량 update
     *
     * @param prmCpn
     * @throws Exception
     */
    public void updateIssueCouponInvQty(PrmCpn prmCpn) throws Exception {
        promotionRepository.updateIssueCouponInvQty(prmCpn);
    }

    /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param prmCpnCrtfcCd
     * @throws Exception
     */
    public void insertCouponCrtfcCdIssue(PrmCpnCrtfcCd prmCpnCrtfcCd) throws Exception {
        promotionRepository.insertCouponCrtfcCdIssue(prmCpnCrtfcCd);
    }

    /**
     * 쿠폰 인증(난수)코드 발급 insert
     *
     * @param prmCpnCrtfcCd
     * @throws Exception
     */
    public void insertCouponCrtfcCode(PrmCpnCrtfcCd prmCpnCrtfcCd) throws Exception {
        promotionRepository.insertCouponCrtfcCode(prmCpnCrtfcCd);
    }
    
    /**
     * 쿠폰 발급 유형 코드 update
     *
     * @param prmCpn
     * @throws Exception
     */
    public void updateCouponCrtfcCdTp(PrmCpn prmCpn) throws Exception {
        promotionRepository.updateCouponCrtfcCdTp(prmCpn);
    }


    /**
     *
    *	프로모션조회
    * @param prmCpn
    * @throws Exception
    * @since 2015. 9. 14.
     */
    public Prm selectPrm(Prm prm) throws Exception {
    	return prmRepository.selectPrm(prm);
    }

    /**
     * 기획전 쿠폰다운로드 유효성 체크
     *
     * @param prm
     * @return
     * @throws Exception
     */
    public PrmExtend selectValidPromtCpnDown(Prm prm) throws Exception {
        PrmExtend prmEx = promotionRepository.selectValidPromtCpnDown(prm);
        return prmEx;
    }

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */

    /**
     * DB에 저장할 url 생성 / 이미지를 이동한다.
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    private int saveImageFileList(PromotionBoDTO promotionBoDTO) throws Exception {

        int moveResult = 0;

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String rprstImgUseSectCd = promotionBoDTO.getPrm().getRprstImgUseSectCd();

        String lang = "KOR";

        List<String> tempFileNameList = promotionBoDTO.getTempFileList();
        List<String> tempFileSectList = promotionBoDTO.getSectFileList();

        String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();

        if (tempFileSectList != null && tempFileSectList.size() > 0) {
            for (int i = 0; i < tempFileSectList.size(); i++) {

                String tempFileName = tempFileNameList.get(i).toString();

                int indexOF = tempFileName.lastIndexOf(PromotionSeparator.DOT.toString());
                String extension = tempFileName.substring(indexOF);

                String finalServFileName = null;

                String httpFilePath;

                if (PromotionDeviceKind.MOBILE.toString().equals(tempFileSectList.get(i).toString().toUpperCase())) {

                    String servFileName = prmNo + PromotionSeparator.UNDER_BAR.toString() + PromotionDeviceKind.MOBILE.toString()
                            + PromotionSeparator.UNDER_BAR.toString() + lang + PromotionSeparator.UNDER_BAR.toString() + suffix + extension;

                    finalServFileName = this.getPromotionMiddleUploadPath(PromotionSeparator.SLASH.toString()) + servFileName;
                    httpFilePath = promotionHttpPath + finalServFileName;

                    promotionBoDTO.getPrm().setRprstImgMobileUrl(httpFilePath);

                }
                else {

                    String servFileName = prmNo + PromotionSeparator.UNDER_BAR.toString() + PromotionDeviceKind.PC.toString()
                            + PromotionSeparator.UNDER_BAR.toString() + lang + PromotionSeparator.UNDER_BAR.toString() + suffix + extension;

                    finalServFileName = this.getPromotionMiddleUploadPath(PromotionSeparator.SLASH.toString()) + servFileName;
                    httpFilePath = promotionHttpPath + finalServFileName;

                    promotionBoDTO.getPrm().setRprstImgPcUrl(httpFilePath);

                    if (PromotionPrstImgUseSect.PC_MOBILE_UNITY_USE.toString().equals(rprstImgUseSectCd)) {
                        promotionBoDTO.getPrm().setRprstImgMobileUrl(null);
                    }
                }

                File servFile = new File(promotionUploadPath, finalServFileName);

                if (servFile.exists()) {
                    IOService.deleteFile(promotionUploadPath, finalServFileName);
                }

				String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + tempFileName;
				String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + promotionHttpPath + finalServFileName;			
 
				s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);		
                
                moveResult++;
            }
        }
        return moveResult;
    }

    /**
     * DB에 저장할 url 생성 / 이미지를 이동한다.
     *
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    private String saveCpnImageFile(PromotionBoDTO promotionBoDTO, String imgUrl, String gubun) throws Exception {

    	String httpFilePath = "";

        String prmNo = promotionBoDTO.getPrm().getPrmNo();
        String lang = "KOR";

        String suffix = DateService.getStringCurrentToday() + DateService.getStringCurrentHourMinuteSecond();

        if(!StringService.isEmpty(imgUrl)){

            int indexOF = imgUrl.lastIndexOf(PromotionSeparator.DOT.toString());
            String extension = imgUrl.substring(indexOF);

            String finalServFileName = null;

            String servFileName = prmNo + PromotionSeparator.UNDER_BAR.toString() + gubun
                    + PromotionSeparator.UNDER_BAR.toString() + lang + PromotionSeparator.UNDER_BAR.toString() + suffix + extension;

            finalServFileName = this.getPromotionMiddleUploadPath(PromotionSeparator.SLASH.toString()) + servFileName;
            httpFilePath = promotionHttpPath + finalServFileName;

            File servFile = new File(promotionUploadPath, finalServFileName);

            if (servFile.exists()) {
                IOService.deleteFile(promotionUploadPath, finalServFileName);
            }
			String tempResourcePath = this.bucketName + ":"+ getUploadImageUrlPath() + imgUrl;
			String commitResourcePath = this.bucketName + ":"+getSaveImagePath("DXM") + promotionHttpPath + finalServFileName;			

			s3FileSystem.move(tempResourcePath, commitResourcePath, CloudFileSystem.Permission.PUBLIC);	
        }

        return httpFilePath;

    }

    /**
     * 프로모션 중간 PATH
     *
     * @return String - /yyyy/mm/dd/
     * @throws Exception
     */
    private String getPromotionMiddleUploadPath(String separator) throws Exception {
        String promotionMiddlePath = separator + DateService.getStringCurrentYear() + separator + DateService.getStringCurrentMonth()
                + separator + DateService.getStringCurrentDay() + separator;
        return promotionMiddlePath;
    }

	/**
	 * 프로모션 다국어 저장
	 * 
	 * @param promotionBoDTO
	 * @throws Exception
	 */
	public void insertPrmLang(PromotionBoDTO promotionBoDTO) throws Exception {
    	PrmLang prmLang = new PrmLang();

		// 영문다국어 처리
		if( promotionBoDTO.getPrmCpnEx().getCpnNmEng() != null && !"".equals(promotionBoDTO.getPrmCpnEx().getCpnNmEng()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.ENG.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmCpnEx().getCpnNmEng());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(promotionBoDTO.getPrm().getRegtrId()); // 등록자
			prmLang.setUdterId(promotionBoDTO.getPrm().getRegtrId()); // 수정자

			prmLangRepository.insertPrmLang(prmLang);
		}

		// 중문 다국어 처리
		if( promotionBoDTO.getPrmCpnEx().getCpnNmChi() != null && !"".equals(promotionBoDTO.getPrmCpnEx().getCpnNmChi()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.CHI.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmCpnEx().getCpnNmChi());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(promotionBoDTO.getPrm().getRegtrId()); // 등록자
			prmLang.setUdterId(promotionBoDTO.getPrm().getRegtrId()); // 수정자

			prmLangRepository.insertPrmLang(prmLang);
		}
	}
    
    /**
     * 쿠폰명 다국어 저장 merge
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void mergeCouponNmMlang(PromotionBoDTO promotionBoDTO) throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();

    	PrmLang prmLang = new PrmLang();

    	// 저장 전 삭제
    	prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());

    	promotionRepository.deletePrmLang(prmLang);

		// 영문다국어 처리
		if( promotionBoDTO.getPrmCpnEx().getCpnNmEng() != null && !"".equals(promotionBoDTO.getPrmCpnEx().getCpnNmEng()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.ENG.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmCpnEx().getCpnNmEng());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(loginId); // 등록자
			prmLang.setUdterId(loginId); // 수정자

			promotionRepository.insertPrmlang(prmLang);
		}

		// 중문 다국어 처리
		if( promotionBoDTO.getPrmCpnEx().getCpnNmChi() != null && !"".equals(promotionBoDTO.getPrmCpnEx().getCpnNmChi()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.CHI.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmCpnEx().getCpnNmChi());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(loginId); // 등록자
			prmLang.setUdterId(loginId); // 수정자

			promotionRepository.insertPrmlang(prmLang);
		}
    }
    
    /**
     * 프로모션명 다국어 저장 merge
     *
     * @param promotionBoDTO
     * @throws Exception
     */
    public void mergePrmNmMlang(PromotionBoDTO promotionBoDTO) throws Exception {
    	String loginId = BOSecurityUtil.getLoginId();

    	PrmLang prmLang = new PrmLang();

    	// 저장 전 삭제
    	prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());

    	promotionRepository.deletePrmLang(prmLang);

		// 영문다국어 처리
		if( promotionBoDTO.getPrmEx().getPrmNmEng() != null && !"".equals(promotionBoDTO.getPrmEx().getPrmNmEng()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.ENG.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmEx().getPrmNmEng());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(loginId); // 등록자
			prmLang.setUdterId(loginId); // 수정자

			promotionRepository.insertPrmlang(prmLang);
		}

		// 중문 다국어 처리
		if( promotionBoDTO.getPrmEx().getPrmNmChi() != null && !"".equals(promotionBoDTO.getPrmEx().getPrmNmChi()) ){
			// PRM_LANG
			prmLang.setPrmNo(promotionBoDTO.getPrm().getPrmNo());
			prmLang.setLangCd(PromotionEnum.langCd.CHI.toString());
			prmLang.setPrmNm(promotionBoDTO.getPrmEx().getPrmNmChi());
			prmLang.setRprstImgUseSectCd("");
			prmLang.setRprstImgPcUrl("");
			prmLang.setRprstImgPcAltrtvCont("");
			prmLang.setRprstImgMobileUrl("");
			prmLang.setRprstImgMobileAltrtvCont("");
			prmLang.setRegtrId(loginId); // 등록자
			prmLang.setUdterId(loginId); // 수정자

			promotionRepository.insertPrmlang(prmLang);
		}
    }
    
    /**
     * 쿠폰 적용 대상 디바이스 조회
     * @param promotionBoDTO
     * @return
     * @throws Exception
     */
    public String selectCpnIssuTgtDvc(PromotionBoDTO promotionBoDTO) throws Exception {
        return promotionRepository.selectCpnIssuTgtDvc(promotionBoDTO);
    }

    /**
     * 상품상세노출 프로모션 생성 프로시져 실행
     */
    public int updateDspGodPrmProcedure(PromotionBoDTO promotionBoDTO) throws Exception {
		return promotionRepository.updateDspGodPrmProcedure(promotionBoDTO);
	}


    /**
     * 온오프라인 프로모션 정보 수정
     * @param prm
     * @return
     * @throws Exception
     */
    public int updateOnOffPrm(Prm prm) throws Exception{
    	return promotionRepository.updateOnOffPrm(prm);
    }

    /**
     * 온오프라인 프로모션 쿠폰 정보 수정
     * @param prmCpn
     * @return
     * @throws Exception
     */
    public int updateOnOffPrmCpn(PrmCpn prmCpn) throws Exception{
    	return promotionRepository.updateOnOffPrmCpn(prmCpn);
    }


    public CouponPromotionResult selectFirstAppCpnInfo(PrmCpn prmCpn) throws Exception {
        return promotionRepository.selectFirstAppCpnInfo(prmCpn);
    }

    /**
     * (회원) 쿠폰 발급 Count
     *
     * @param mbrIssuCpn
     * @throws Exception
     */
    public int selectCouponIssueCount(MbrIssuCpn mbrIssuCpn) throws Exception {
        return promotionRepository.selectCouponIssueCount(mbrIssuCpn);
    }

    /**
     * 온오프라인쿠폰 쿠폰 발급가능 검증
     * 
     * @param promotionBoDTO
     * @return onOffCouponIssueSDO
     * @throws Exception
     */
	public OnOffCouponIssueSDO validOnOffCouponForIssue(PromotionBoDTO promotionBoDTO) throws Exception  {
		String requestTypeCd = "00";
		
		return issueOnOffCoupon(promotionBoDTO, requestTypeCd);	
	}
	
    /**
     * 온오프라인쿠폰 쿠폰 발급
     * 
     * @param promotionBoDTO
     * @return onOffCouponIssueSDO
     * @throws Exception
     */
	public OnOffCouponIssueSDO issueOnOffCoupon(PromotionBoDTO promotionBoDTO) throws Exception  {
		String requestTypeCd = "01";
		
		return issueOnOffCoupon(promotionBoDTO, requestTypeCd);
	}
	
    /**
     * 온오프라인쿠폰 쿠폰 발급을 위한 정보 조회
     * 
     * @param promotionBoDTO
     * @return onOffCouponIssueSDO
     * @throws Exception
     */
	public OnOffCouponIssueSDO getInfoForissueOnOffCoupon(PromotionBoDTO promotionBoDTO) throws Exception  {
		String requestTypeCd = "02";
		
		return issueOnOffCoupon(promotionBoDTO, requestTypeCd);
	}
	
    /**
     * 온오프라인쿠폰 쿠폰 발급 및 발급가능 검증
     * 
     * @param promotionBoDTO
     * @param requestTypeCd
     * @return onOffCouponIssueSDO
     * @throws Exception
     */
	public OnOffCouponIssueSDO issueOnOffCoupon(PromotionBoDTO promotionBoDTO, String requestTypeCd) throws Exception  {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(this.interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(promotionBoDTO.getMallId());
		adapterHeader.setLangCd(promotionBoDTO.getLang());
		adapterHeader.setDvcCd(promotionBoDTO.getDevice());	
		
		String brand = "";
		if(MallIdEnum.DXM_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
			brand = "X";
		}
		else if(MallIdEnum.MLB_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
			brand = "M";
		}
		else if(MallIdEnum.SA_MALL_ID.toString().equals(promotionBoDTO.getMallId())) {
			brand = "A";
		}
		
		OnOffCouponIssueSDO onOffCouponIssueSDO = promotionAdapter.issueOnOffCoupon(brand, promotionBoDTO.getMbr().getErpCstmrNo(), promotionBoDTO.getMbrIssuCpn().getCpnCrtfcCd(), requestTypeCd, adapterHeader);		
		
		return onOffCouponIssueSDO;
	}
	
	/**
	 * 캠페인아이디로 프로모션 번호 찾기
	 * 
	 * @param promotionBoDTO
	 * @return String
	 */
    public String selectPrmNoByErpCmpgId(PromotionBoDTO promotionBoDTO)  {
        return promotionRepository.selectPrmNoByErpCmpgId(promotionBoDTO);
    }
}

