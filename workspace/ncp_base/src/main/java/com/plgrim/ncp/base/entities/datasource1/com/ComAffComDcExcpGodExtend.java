/**
 * @package : com.plgrim.ncp.base.entities..com
 * @author : jackie(jackie)
 * @date : 20150511
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.com;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 업체 제휴 대행 업체 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comAffComDcExcpGodExtend")
public class ComAffComDcExcpGodExtend extends ComAffComDcExcpGod{
	
	/**
	 * 
	 */
    private static final long serialVersionUID = -8634515176330181536L;
	
    private int tcnt;
    private String comId;
    private String comNm;
    private String erpGodNo;
    private String godNm;
    private String exYn;
    private String rtlPrc;
    private String csmrPrc;
    private String resveCsmrPrc;
    private String curAffComUntPrc;
    private String udterNm;
	
	/**
	 * userId
	 */
	private String userId;
	
	/**
	 * 임직원 예외가	 
	 */
	private long empExcpPrc;
	
	/**
	 * SFC 예외가	 
	 */
	private long sfcExcpPrc;
	
	/**
	 * 블루베리 예외가	 
	 */
	private long blueExcpPrc;
	
	/**
	 * 이지웰 예외가	 
	 */
	private long ezwExcpPrc;
	
	/**
	 * 웰스토리 예외가	 
	 */
	private long wsExcpPrc;

	/**
	 * 제휴대행사명
	 */
	private String affVrscComNm;
	
	/**
     * 제휴 대행 업체's
     */    
    private String[] affVrscComIds;

    /**
     * 제휴 업체's
     */    
    private String[] affDcExcpComIds;
	
	/**
	 * 일괄등록처리 결과메세지
	 */
	private String validText;

}
