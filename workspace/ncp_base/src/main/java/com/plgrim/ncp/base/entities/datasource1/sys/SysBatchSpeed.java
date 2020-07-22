/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : ()
 * @date : 20171211
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.base.entities.datasource1.sys;

import com.plgrim.ncp.base.abstracts.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

/**
 * 페이지 로딩속도 측정
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("sysBatchSpeed")
public class SysBatchSpeed extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 수집 배치 실행 일시	 
	 */
	private java.util.Date batchDt;
	/**
	 * TIMESTAMP	 
	 */
	private java.util.Date elsTimestamp;
	/**
	 * REQUESTID	 
	 */
	private String requestid;
	/**
	 * SID	 
	 */
	private String sid;
	/**
	 * APP	 
	 */
	private String app;
	/**
	 * SYSNO	 
	 */
	private String sysno;
	/**
	 * USERAGENT	 
	 */
	private String useragent;
	/**
	 * REMOTEADDR	 
	 */
	private String remoteaddr;
	/**
	 * REFERER	 
	 */
	private String referer;
	/**
	 * PREADYTIME	 
	 */
	private Double preadytime;
	/**
	 * PLOADTIME
	 */
	private Double ploadtime;

}
