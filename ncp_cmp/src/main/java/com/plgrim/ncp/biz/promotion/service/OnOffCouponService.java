package com.plgrim.ncp.biz.promotion.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.enums.promotion.OnOffCouponEnum;
import com.plgrim.ncp.biz.promotion.repository.OnOffCouponRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OnOffCouponService extends AbstractService {
	
	@Autowired
	OnOffCouponRepository OnOffCouponRepository;
	
	/**
	 * 온오프라인 쿠폰 회원 발행 
	 * 
	 * @param onOffCoupon : enum으로 정의된 쿠폰 종류
	 * @param cpnCrtfcCd : 쿠폰인증코드
	 * @param mbrNo
	 * @param regtrId
	 * @param validBegDate
	 * @param validEndDate
	 * @param cpnPubliDt
	 * @param useYn
	 * @throws Exception
	 */
	public void insertOnOffCouponIssue(OnOffCouponEnum onOffCoupon, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt) throws Exception {
		this.insertOnOffCouponIssue(onOffCoupon.getErpCpnId(), cpnCrtfcCd, mbrNo, regtrId, validBegDate, validEndDate, cpnPubliDt, useYn, useDt, null);
	}
	
	
	/**
	 * 온오프라인 쿠폰 회원 발행 
	 *  - 고객 발급용 (등록자 ID를 회원번호로 등록)
	 *  
	 * @param erpCpnId : ERP 쿠폰 ID
	 * @param cpnCrtfcCd : 쿠폰인증코드
	 * @param mbrNo
	 * @param validBegDate
	 * @param validEndDate
	 * @param cpnPubliDt
	 * @param useYn
	 * @throws Exception
	 */
	public void insertOnOffCouponIssue(String erpCpnId, String cpnCrtfcCd, String mbrNo, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt) throws Exception {
		this.insertOnOffCouponIssue(erpCpnId, cpnCrtfcCd, mbrNo, mbrNo, validBegDate, validEndDate, cpnPubliDt, useYn, useDt, null);
	}
	
	/**
	 * 온오프라인 쿠폰 회원 발행 
	 *  - 고객 발급용 (등록자 ID를 회원번호로 등록)
	 *  
	 * @param erpCpnId : ERP 쿠폰 ID
	 * @param cpnCrtfcCd : 쿠폰인증코드
	 * @param mbrNo
	 * @param validBegDate
	 * @param validEndDate
	 * @param cpnPubliDt
	 * @param useYn
	 * @throws Exception
	 */
	public void insertOnOffCouponIssue(String erpCpnId, String cpnCrtfcCd, String mbrNo, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt, String mallId) throws Exception {
		this.insertOnOffCouponIssue(erpCpnId, cpnCrtfcCd, mbrNo, mbrNo, validBegDate, validEndDate, cpnPubliDt, useYn, useDt, mallId);
	}
	
	/**
	 * 온오프라인 쿠폰 회원 발행
	 * 
	 * @param erpCmpgId : ERP 쿠폰 ID
	 * @param cpnCrtfcCd : 쿠폰인증코드
	 * @param mbrNo
	 * @param regtrId
	 * @param validBegDate
	 * @param validEndDate
	 * @param cpnPubliDt
	 * @param useYn
	 * @param useDt
	 * @param mallId
	 * @throws Exception
	 */
	public void insertOnOffCouponIssue(String erpCpnId, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt, String mallId) throws Exception {
		int result = 0;
		try {
			/**
			 * 인증코드 생성
			 */
			result = OnOffCouponRepository.insertCpnCrtfcCd(erpCpnId, cpnCrtfcCd, regtrId, mallId);
			
			if(result > 0) {
				/**
				 * 회원발행쿠폰 정보 생성
				 */
				result = OnOffCouponRepository.insertMbrIssuCpn(erpCpnId, cpnCrtfcCd, mbrNo, regtrId, validBegDate, validEndDate, cpnPubliDt, useYn, useDt, mallId);
			}
			
			if(result == 0) {
				log.warn("> insertOnOffCouponIssue Failure message : {} : {}", "회원 혜택 온오프라인 쿠폰 발행 실패", "erpCpnId["+erpCpnId+"]");
				throw new Exception();
			}
		}
		catch(Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.warn("> Failure message : {} : {} : {} : {}", this.getClass().getName(), error.toString(), erpCpnId, cpnCrtfcCd);
            throw e;
		}
	}
	
	/**
	 * 온오프라인 쿠폰 회원 발행
	 * 
	 * @param erpCmpgId : ERP 쿠폰 ID
	 * @param cpnCrtfcCd : 쿠폰인증코드
	 * @param mbrNo
	 * @param regtrId
	 * @param validBegDate
	 * @param validEndDate
	 * @param cpnPubliDt
	 * @param useYn
	 * @param useDt
	 * @param prmNo
	 * @throws Exception
	 */
	public void insertOnOffCouponIssueByPrmNo(String erpCpnId, String cpnCrtfcCd, String mbrNo, String regtrId, String validBegDate, String validEndDate, Date cpnPubliDt, String useYn, Date useDt, String mallId, String prmNo) throws Exception {
		int result = 0;
		try {
			/**
			 * 인증코드 생성
			 */
			result = OnOffCouponRepository.insertCpnCrtfcCdByPrmNo(erpCpnId, cpnCrtfcCd, regtrId, prmNo);
			
			if(result > 0) {
				/**
				 * 회원발행쿠폰 정보 생성
				 */				
				result = OnOffCouponRepository.insertMbrIssuCpnByPrmNo(erpCpnId, cpnCrtfcCd, mbrNo, regtrId, validBegDate, validEndDate, cpnPubliDt, useYn, useDt, prmNo);
			}else{
				log.debug("> insertOnOffCouponIssue Failure message : {} : {}", "회원 혜택 온오프라인 쿠폰 발행 실패", "erpCpnId["+erpCpnId+"] / 이미 발급된 쿠폰");				
			}
		}
		catch(Exception e) {
            StringWriter error = new StringWriter();
            e.printStackTrace(new PrintWriter(error));
            log.warn("> Failure message : {} : {} : {} : {}", this.getClass().getName(), error.toString(), erpCpnId, cpnCrtfcCd);
            throw e;
		}
	}
	
	
}
