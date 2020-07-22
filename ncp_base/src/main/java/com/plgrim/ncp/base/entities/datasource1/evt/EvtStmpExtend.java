/**
 * @package : com.plgrim.ncp.base.entities..evt
 * @author : vito(vito)
 * @date : 20151014
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.evt;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 이벤트 스탬프
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EvtStmpExtend extends EvtStmp {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 이벤트 참여항목 count
     */
    private int partcptnContCount;
    
    private String selectType;

}
