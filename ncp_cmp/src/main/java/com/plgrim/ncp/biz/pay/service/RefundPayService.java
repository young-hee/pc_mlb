package com.plgrim.ncp.biz.pay.service;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.pay.PayEscr;
import com.plgrim.ncp.base.entities.datasource1.pay.PayExtend;
import com.plgrim.ncp.base.entities.datasource1.pay.PayRfd;
import com.plgrim.ncp.base.repository.pay.PayEscrRepository;

import com.plgrim.ncp.biz.claim.data.RefundPayDTO;
import com.plgrim.ncp.biz.claim.repository.ClaimBatchRepository;
import com.plgrim.ncp.biz.claim.repository.ClaimRepository;
import com.plgrim.ncp.biz.promotion.data.MbrIssuCpnExtend;

@Slf4j
@Service
public class RefundPayService extends AbstractService {
	
	@Autowired
	private ClaimBatchRepository claimBatchRepository;
	
	@Autowired
	private ClaimRepository claimRepository;
	
	@Autowired
	private PayEscrRepository payEscrRepository;
	
	public void updateAddPayByPartCncl(RefundPayDTO refundPayDTO) {
		claimRepository.updateAddPayByPartCncl(refundPayDTO);
	}
	
    public RefundPayDTO getMobileRePayInfo(String payNo) {
		return claimRepository.getMobileRePayInfo(payNo);
    }
    
    public String getRefundPayYn(String payNo) {
    	return claimRepository.getRefundPayYn(payNo);
    }
    
    public void updateTradeReauthNo(RefundPayDTO refundPayDTO) throws Exception {
    	claimRepository.updateTradeReauthNo(refundPayDTO);
    }

    public String getRepayGodCpn(PayExtend payDto) {
		return claimBatchRepository.getRepayGodCpn(payDto);
    }
    
    public PayEscr selectPayEscr(String payNo) {

    	PayEscr payEscr = new PayEscr();
    	payEscr.setPayNo(payNo);
    	
    	return payEscrRepository.selectPayEscr(payEscr);
    }
    
    public void updateEscrStatCd(RefundPayDTO refundPayDTO) {
    	claimRepository.updateEscrStatCd(refundPayDTO);
    }
    
    public MbrIssuCpnExtend selectRestoreOnoffCpnInfo(String mbrCpnNo) {
    	return claimRepository.selectRestoreOnoffCpnInfo(mbrCpnNo);
    }
    
    public void updatePayRfdStat(PayRfd payRfd) {
    	claimRepository.updatePayRfdStat(payRfd);
    }
    
    public String getKcpRefundBnkCd(String cd) {
    	return claimRepository.getKcpRefundBnkCd(cd);
    }
}
