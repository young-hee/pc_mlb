/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : jackie(jackie)
 * @date : 20150609
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 주문 ERP 포인트 전송
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ordErpPntTrnsmis")
public class OrdErpPntTrnsmis extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 주문 번호
OD || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String ordNo;
	/**
	 * 주문 ERP 포인트 유형 코드
ㅁ. 주문 ERP 포인트 유형 : ORD_ERP_PNT_TP
   >. 통합 포인트 : UNITY_PNT
   >. 이벤트 포인트 : EVT_PNT
   >. 통합 포인트 임시 삭감 : UNITY_PNT_TMPR_REDCT
   >. 이벤트 포인트 임시 삭감 : EVT_PNT_TMPR_REDCT	 
	 */
	private String ordErpPntTpCd;
	/**
	 * ERP 포인트 전송 코드
ㅁ. ASIS는 아래와 같이 되어 있으나 확인 필요

 
Y:RE 성공
F: RE 실패.
R: OR 성공
E: OR 실패.
X: 해당없음.	 
	 */
	private String erpPntTrnsmisCd;
	/**
	 * ERP 포인트 전송 일시
	 */
	private java.util.Date erpPntTrnsmisDt;
	/**
	 * 등록자 ID
등록한 관리자 번호	 
	 */
	private String regtrId;
	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;
	/**
	 * 수정자 ID
수정한 관리자 번호	 
	 */
	private String udterId;
	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

}
