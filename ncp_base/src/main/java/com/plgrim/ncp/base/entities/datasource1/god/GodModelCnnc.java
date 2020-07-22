/**
 * @package : com.plgrim.ncp.base.entities..god
 * @author : Neps(양태훈)
 * @date : 20150518
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.god;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 상품 디자인 그룹
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("godModelCnnc")
public class GodModelCnnc extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String godNo;
	
	private String modelNo;
	
	private String csltSizeAplYn;
	
	private String pantsSizeAplYn;
	
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
