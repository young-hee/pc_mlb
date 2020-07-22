/**
 * @package : com.plgrim.ncp.base.entities..ord
 * @author : plgrim()
 * @date : 20170725
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.ord;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 주문 상품 서비스 상세 연결
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("ordGodSvcDetailCnncExtend")
public class OrdGodSvcDetailCnncExtend extends OrdGodSvcDetailCnnc{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String godNo;

	private String itmNo;

	private String svcTpNm;

}
