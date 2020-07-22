package com.plgrim.ncp.biz.member.repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrRfdBnkAcct;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

/**
 * 회원주문정보 command repository
 */
@Repository
public class MemberOrderCommandRepository extends AbstractRepository  {
 
	/**
	 * 환불계좌 등록.
	 */
	public void insertMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		this.insertMbrRfdBnkAcct(mbrRfdBnkAcct); 
	}
	
	/**
	 * 대표 환불 계좌 제거.
	 */
	public void updateRprstRefundAccountRevome(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		getSession1().update("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.updateRprstRefundAccountRevome", mbrRfdBnkAcct);
	}
	
	/**
	 * 대표 환불 계좌 설정.
	 */
	public void updateRprstRefundAccountSet(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		getSession1().update("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.updateRprstRefundAccountSet", mbrRfdBnkAcct);
	}
	
	/**
	 * 환불계좌 수정.
	 */
	public int updateMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		return getSession1().update("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.updateMemberRefundAccount", mbrRfdBnkAcct);
	}

	
	/**
	 * 환불계좌 삭제.
	 */
	public int deleteMemberRefundAccount(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		return getSession1().update("com.plgrim.ncp.biz.mbr.rfd.bnk.acct.deleteMemberRefundAccount", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 등록.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public void insertMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		getSession1().insert("com.plgrim.ncp.base.insertMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 수정.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public int updateMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().update("com.plgrim.ncp.base.updateMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
	 * 회원 환불 계좌 삭제.
	 *
	 * @param mbrRfdBnkAcct the MbrRfdBnkAcct
	 * @return the MbrRfdBnkAcct
	 * @throws SQLException the SQL exception
	 */
	public int deleteMbrRfdBnkAcct(MbrRfdBnkAcct mbrRfdBnkAcct)  {
		//업데이트 후에 데이터를 다시 조회 한다.
		return getSession1().delete("com.plgrim.ncp.base.deleteMbrRfdBnkAcct", mbrRfdBnkAcct);
	}
	
	/**
     * 대표배송지 제거.
     *
     * @param mbrDlvsp [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     */
    public int updateBaseDeliveryLocationRevome(MbrDlvsp mbrDlvsp) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.dlvsp.updateBaseDeliveryLocationRevome", mbrDlvsp);
    }
    
    /**
     * 배송지 저장.
     *
     * @param mbrDlvsp [설명]
     * @return Int [설명]
     * @since 2015. 4. 9
     * @the exception
     */
    public int mergeDeliveryLocation(MbrDlvsp mbrDlvsp) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.dlvsp.mergeDeliveryLocation", mbrDlvsp);
    }
    
    /** 회원 배송지 삭제.*/
    public int deleteMbrDlvsp(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrDlvsp", mbrNo);
	}
    
    /**
     * 대표배송지 설정.
     *
     * @param mbrDlvsp [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     */
    public int updateBaseDeliveryLocationSet(MbrDlvsp mbrDlvsp) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.dlvsp.updateBaseDeliveryLocationSet", mbrDlvsp);
    }
    
    /**
     * 배송지 삭제.
     *
     * @param mbrDlvsp [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     */
    public int deleteDeliveryLocation(MbrDlvsp mbrDlvsp) {
        return getSession1().update("com.plgrim.ncp.biz.mbr.dlvsp.deleteDeliveryLocation", mbrDlvsp);
    }
    
    /** 회원 구매 매장 삭제.*/
    public int deleteMbrPchShop(String mbrNo) {
		return getSession1().delete("com.plgrim.ncp.biz.mbr.secession.deleteMbrPchShop", mbrNo);
	}
    
    /**
     * 특정 배송지 기 등록여부 확인
     * @param mbrDlvsp
     * @return
     */
    public boolean hasDeliveryLocation(MbrDlvsp mbrDlvsp) {
        int cnt = getSession1().selectOne("com.plgrim.ncp.biz.mbr.dlvsp.getDeliveryLocationCount", mbrDlvsp);
        if (cnt > 0) {
        	return true;
        } else {
        	return false;
        }
        
    }
    
    /**
     * 배송지 등록.
     *
     * @param mbrDlvsp [설명]
     * @return Int [설명]
     * @since 2015. 3. 25
     * @ 
     */
    public int insertDeliveryLocation(MbrDlvsp mbrDlvsp) {
        return getSession1().insert("com.plgrim.ncp.biz.mbr.dlvsp.insertDeliveryLocation", mbrDlvsp);
    }
}
