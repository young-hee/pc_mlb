/**
 * @package : com.plgrim.ncp.base.entities..mbr
 * @author : jackie(jackie)
 * @date : 20150618
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.promotion.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.StringUtils;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionDatePattern;
import com.plgrim.ncp.base.enums.PromotionEnum.PromotionSeparator;

/**
 * 회원 발급 쿠폰
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class MbrIssuCpnExtend extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 회원 명 
	 */
	private String mbrNm;
	
	/**
	 * 회원 ID	 
	 */
	private String mbrId;
	
	/**
	 * ERP 고객번호
	 */
	private String erpCstmrNo;

	/**
	 * 쿠폰인증코드 (온오프라인 쿠폰의 경우 ERP 쿠폰발행번호)
	 */
	private String cpnCrtfcCd;
	
	/**
	 * 시작 일시	 
	 */
	private String begDate;

	/**
	 * 마감 일시	 
	 */
	private String endDate;

	/**
	 * 시작 일시 str
	 */
	private String begDtStr;

	/**
	 * 마감 일시 str
	 */
	private String endDtStr;

	/**
	 * 발급 일시 str
	 */
	private String cpnPubliDtStr;

    /**
     * 발급관리자ID
     */
	private String issuAdminId;

    /**
     * 발급관리자명
     */
	private String issuAdminNm;

    /**
     * 발급관리자 이름(ID)
     */
	public String getIssuAdminNmIdStr(){
		
		String rst = "";
		
		if(this.getIssuAdminNm() != null && this.getIssuAdminId() != null){
			rst = this.getIssuAdminNm() + "(" + this.getIssuAdminId() + ")";
		} 
		
		return rst;
	}
	
    public String getBegDtStr(){
    	
    	String pbd = this.getBegDate();
    	
        String sp = PromotionDatePattern.SHORT.toString();
        String svp = PromotionDatePattern.SHORT_VIEW.toString();
        DateTimeFormatter spDtf = DateTimeFormat.forPattern(sp);
        DateTimeFormatter svpDtf = DateTimeFormat.forPattern(svp);

        if (StringUtils.isEmpty(pbd)) {
            pbd = PromotionSeparator.DASH.toString();
        }else{
            String pbdTrim = StringUtils.trimWhitespace(pbd);
            DateTime pbdDt = spDtf.parseDateTime(pbdTrim);
            pbd = svpDtf.print(pbdDt);
        }
    	
        begDtStr = pbd;
    	
    	return begDtStr;
    }

    public String getEndDtStr(){
    	
    	String pbd = this.getEndDate();

        String sp = PromotionDatePattern.SHORT.toString();
        String svp = PromotionDatePattern.SHORT_VIEW.toString();
        DateTimeFormatter spDtf = DateTimeFormat.forPattern(sp);
        DateTimeFormatter svpDtf = DateTimeFormat.forPattern(svp);

        if (StringUtils.isEmpty(pbd)) {
            pbd = PromotionSeparator.DASH.toString();
        }else{
            String pbdTrim = StringUtils.trimWhitespace(pbd);
            DateTime pbdDt = spDtf.parseDateTime(pbdTrim);
            pbd = svpDtf.print(pbdDt);
        }
    	
        endDtStr = pbd;
    	
    	return endDtStr;
    }

}
