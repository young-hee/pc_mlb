/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : plgrim()
 * @date : 20171213
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.zipcode.data;

import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;
import com.plgrim.ncp.framework.commons.DateService;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 시스템 파일 업로드 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysFileUploadExtend extends SysFileUpload{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 파일 업로드 유형 코드
ㅁ.파일 업로드 유형 : FILE_UPLOAD_TP
   >. 전체 : ALL
   >. 변경 : MOD	 
	 */
	private String fileUploadTpNm;
	
	/**
	 * 진행 상태 코드명
ㅁ.진행 상태 : PROGRS_STAT
   >. 파일업로드성공 : FILE_UPLOAD_SUCCES
   >. 파일업로드실패 : FILE_UPLOAD_FAILR
   >. 임시테이블저장성공 : TMPR_TABLE_SAVE_SUCCES
   >. 임시테이블저장실패 : TMPR_TABLE_SAVE_FAILR
   >. 진행중 : PROGRS
   >. 승인대기 : APRV_WAIT
   >. 승인완료 : APRV_COMPT	 
	 */
	private String progrsStatNm;
	
	private String regDtStr;

    public String getRegDtStr(){
    	if(this.getRegDt() != null) {
    		regDtStr = DateService.parseDefaultFormat(this.getRegDt());
    	}
    	else {
    		regDtStr = "";
    	}
    	
    	return regDtStr;
    }
}
