/**
 * @package : com.plgrim.ncp.base.entities..mbr
 * @author : ()
 * @date : 20160524
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.mbr;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 회원 ID 연계 확장
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrIdCntcExtend")
public class MbrIdCntcExtend extends MbrIdCntc{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mbrId;

}
