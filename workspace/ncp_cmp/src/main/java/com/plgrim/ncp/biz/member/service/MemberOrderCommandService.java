package com.plgrim.ncp.biz.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;
import com.plgrim.ncp.biz.member.repository.MemberOrderCommandRepository;

import lombok.extern.slf4j.Slf4j;
 
/**
 * 회원주문정보 command service
 */
@Service
@Slf4j
public class MemberOrderCommandService extends AbstractService {

	@Autowired
	MemberOrderCommandRepository memberOrderCommandRepository;
	
	/**
	 * 환불 계좌 등록.
	 */
	public void insertMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		memberOrderCommandRepository.insertMemberRefundAccount(mbrRfdBnkAcct);
	}
	
	/**
	 * 대표 환불 계좌 제거.
	 */
	public void updateRprstRefundAccountRevome(MbrRfdBnkAcct mbrRfdBnkAcct, String loginId)  {
		mbrRfdBnkAcct.setUdterId(loginId);
		memberOrderCommandRepository.updateRprstRefundAccountRevome(mbrRfdBnkAcct);
	}
	
	/**
	 * 대표 환불 계좌 설정.
	 */
	public void updateRprstRefundAccountSet(MbrRfdBnkAcct mbrRfdBnkAcct, String loginId)  {
		mbrRfdBnkAcct.setUdterId(loginId);
		memberOrderCommandRepository.updateRprstRefundAccountSet(mbrRfdBnkAcct);
	}
	
	/**
	 * 환불 계좌 수정.
	 */
	public void updateMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		memberOrderCommandRepository.updateMemberRefundAccount(mbrRfdBnkAcct);
	}
	
	/**
	 * 환불 계좌 삭제.
	 */
	public void deleteMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		memberOrderCommandRepository.deleteMemberRefundAccount(mbrRfdBnkAcct);
	}
	
	/**
	 * 대표배송지 해지.
	 *
	 * @param mbrDlvsp
	 *            회원 배송지 Entity
	 * @param loginId
	 *            로그인ID(처리자)
	 * @since 2015. 4. 9
	 */
	public void updateBaseDeliveryLocationRevomeBy(MbrDlvsp mbrDlvsp, String loginId) {
		mbrDlvsp.setUdterId(loginId);
		memberOrderCommandRepository.updateBaseDeliveryLocationRevome(mbrDlvsp);
	}

	/**
	 * 배송지 목록 저장.
	 *
	 * @param listDTO 추가/수정된 배송지 목록
	 * @param loginId 로그인ID(처리자)
	 * @ the exception
	 * @since 2015. 4. 9
	 */
	public void mergeDeliveryLocationBy(MbrDlvsp mbrDlvsp, String loginId)  {
		mbrDlvsp.setUdterId(loginId);

		if(mbrDlvsp.getNationCd().equals("KR")
				|| mbrDlvsp.getNationCd().equalsIgnoreCase("KOR")) {
			mbrDlvsp.setNationCd("kr");
		}


		if(mbrDlvsp.getMobilNationNo().indexOf("+") > -1 ){
			mbrDlvsp.setMobilNationNo(mbrDlvsp.getMobilNationNo().replace("+","").trim());
		}
		memberOrderCommandRepository.mergeDeliveryLocation(mbrDlvsp);
	}

	/**
	 * 대표배송지 설정.
	 *
	 * @param mbrDlvsp
	 *            회원 배송지 Entity
	 * @param loginId
	 *            로그인ID(처리자)
	 * @since 2015. 4. 9
	 */
	public void updateBaseDeliveryLocationSetBy(MbrDlvsp mbrDlvsp, String loginId) {
		mbrDlvsp.setUdterId(loginId);
		memberOrderCommandRepository.updateBaseDeliveryLocationSet(mbrDlvsp);
	}

	/**
	 * 배송지 삭제.
	 */
	public void deleteDeliveryLocationBy(MbrDlvsp mbrDlvsp, String loginId) {
		mbrDlvsp.setUdterId(loginId);
		memberOrderCommandRepository.deleteDeliveryLocation(mbrDlvsp);
	}
	
	/**
	 * 특정 배송지 기 등록여부 확인
	 * 
	 * @param mbrDlvsp
	 * @return
	 */
	public boolean hasDeliveryLocation(MbrDlvsp mbrDlvsp)  {
		return memberOrderCommandRepository.hasDeliveryLocation(mbrDlvsp);
	}

	/**
	 * 배송지 추가.
	 *
	 * @param mbrDlvsp 회원 배송지 Entity
	 * @param loginId 로그인ID(처리자)
	 * @ the exception
	 * @since 2015. 4. 9
	 */
	public void insertDeliveryLocationBy(MbrDlvsp mbrDlvsp, String loginId)  {
		mbrDlvsp.setUdterId(loginId);
		if(mbrDlvsp.getNationCd().equals("KR")
				|| mbrDlvsp.getNationCd().equalsIgnoreCase("KOR")) {
			mbrDlvsp.setNationCd("kr");
		}

		if(mbrDlvsp.getMobilNationNo().indexOf("+") > -1 ){
			mbrDlvsp.setMobilNationNo(mbrDlvsp.getMobilNationNo().replace("+","").trim());
		}

		memberOrderCommandRepository.insertDeliveryLocation(mbrDlvsp);
	}
}
