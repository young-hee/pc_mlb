/**
 * @package : com.plgrim.ncp.base.entities..sys
 * @author : plgrim()
 * @date : 20171213
 * @version : 1.0
 * @desc : 
 */

package com.plgrim.ncp.biz.zipcode.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.sys.SysFileUpload;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 시스템 파일 업로드 이력
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysFileUploadBoDTO extends AbstractDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /*
     * ---------------------------------------------------------------------
     * Instance fields.
     * ---------------------------------------------------------------------
     */
	
	/**
	 * 시스템 파일 업로드 이력
	 */
	private SysFileUpload sysFileUpload = new SysFileUpload();
	
    /*
     * ---------------------------------------------------------------------
     * Constructors.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * public & interface method.
     * ---------------------------------------------------------------------
     */

    /*
     * ---------------------------------------------------------------------
     * private method.
     * ---------------------------------------------------------------------
     */
}
