/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150420
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

/**
 * 상품 옵션
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godOptExtend")
public class GodOptExtend extends GodOpt {
	
	/**
	 * 
	 */
    private static final long serialVersionUID = 4528390669234519588L;
    
    // ERP 상품번호
    private String erpGodNo;
    
    // 옵션리스트 
	private ArrayList<String> optNms;

}
