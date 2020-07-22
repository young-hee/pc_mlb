/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : jackie(jackie)
 * @date : 20150420
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 상품 언어별 상품 명
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godLangbyGodNmExtend")
public class GodLangbyGodNmExtend extends GodLangbyGodNm{
	private static final long serialVersionUID = -7753596110807657848L;

	/**
	 * 번역상태명
	 */
	private String trsltStatCdNm;
    /**
     * 해외전시상태명
     */
	private String ovseaDspStatCdNm;
}
