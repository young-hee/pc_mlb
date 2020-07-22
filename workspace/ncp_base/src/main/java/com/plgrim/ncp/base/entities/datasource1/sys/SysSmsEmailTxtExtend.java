/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : plgrim()
 * @date : 20170731
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 시스템 SMS 이메일 문구
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysSmsEmailTxtExtend")
public class SysSmsEmailTxtExtend extends SysSmsEmailTxt{

	/*
	* 알림톡 문구
	* */
	private String alimTlakMsg;

	/*
	* SMS 문구
	* */
	private String smsMsg;
}
