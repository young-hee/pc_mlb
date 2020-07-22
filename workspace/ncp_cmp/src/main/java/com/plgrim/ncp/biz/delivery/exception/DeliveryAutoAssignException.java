/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      sy59.gim
 * @since       2015. 6. 19       
 */
package com.plgrim.ncp.biz.delivery.exception;

import com.plgrim.ncp.framework.exception.NCPException;

/**
 * 
 * @author sy59.gim
 * @since 2015. 5. 20
 */
public class DeliveryAutoAssignException extends NCPException {


    /** UID. */
    private static final long serialVersionUID = -5868125514798217255L;

	/**
	 * Instantiates a new delivery auto assign impossible exception.
	 *
	 * @param params [설명]
	 */
	public DeliveryAutoAssignException(String[] params) {
		super.init(params);
	}
/*
	private LgsAutoAsgnExtend lgsAutoAsgnExtend;
	private List<LgsAutoAsgnExtend> lgsAutoAsgnExtendList;

	*//**
	 * Instantiates a new delivery auto assign impossible exception.
	 *
	 * @param params [설명]
	 *//*
	public DeliveryAutoAssignException(LgsAutoAsgnExtend param) {

		this.lgsAutoAsgnExtend = param;
	}

	public LgsAutoAsgnExtend getLgsAutoAsgnExtend() {
		return this.lgsAutoAsgnExtend;
	}
	
	public DeliveryAutoAssignException(List<LgsAutoAsgnExtend> list) {
		
		this.lgsAutoAsgnExtendList = list;
	}
	
	public List<LgsAutoAsgnExtend> getLgsAutoAsgnExtendList() {
		return this.lgsAutoAsgnExtendList;
	}
*/	
}