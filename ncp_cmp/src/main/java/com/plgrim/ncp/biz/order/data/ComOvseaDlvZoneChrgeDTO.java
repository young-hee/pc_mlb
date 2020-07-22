/**
 * @author : generator(generator)
 * @date : 2016-02-02
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.biz.order.data;

import com.plgrim.ncp.base.entities.datasource1.com.ComOvseaDlvZoneChrge;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;


@Data
@EqualsAndHashCode(callSuper=false)
@Alias("comOvseaDlvZoneChrge")
public class ComOvseaDlvZoneChrgeDTO extends ComOvseaDlvZoneChrge{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String godWtSum;
	
	private String ovseaDlvCstEx;
	
	private String ovseaDlvGodWtMax;

	private String dmstcDlvCstEx;
	
	//#32221 로 주석처리
	//private java.util.Date histDt; //#31461 관련 작업 : 주문일을 셋팅하여 주문시 적용된 요율표를 가져온다.
	
	//#32221 로 추가
	private String dlvChrgeHistTurn;
	
}
