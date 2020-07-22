/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jhkhan.cha
 * @since       2015. 4. 22       
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import com.plgrim.ncp.framework.data.SystemPK;

/**
 * [클래스 설명]
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author jhkhan.cha
 * @since 2015. 4. 17
 */
public interface ClaimNoticeComponent {

	public void sendOrderAllCancelNotice(String ordNo, SystemPK systemPK, boolean isDirect);
	
	public void sendOrderPartCancelNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect);
	
	public void sendAcceptReturnNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect);
	
	public void sendCompleteReturnNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect);
	
	public void sendAcceptExchangeNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect);
	
	public void sendCompleteExchangeNotice(String ordNo, String clmNo, String waybilNo, SystemPK systemPK, boolean isDirect);
}
