/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * Revision History
 * Author              Date                         Description
 * ------------------   --------------                  ------------------
 * beyondj2ee                       
 */
package com.plgrim.ncp.framework.page;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Instantiates a new page param.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** default 한페이지에 노출 row수. */
	private static final int pagePerSize = 30;

	// ~ Instance fields. ~~~~~~~~~~~~~~
	/** The pageable. */
	Pageable pageable;

	// ~ Public Methods. ~~~~~~~~~~~~~~~
	/**
	 * 현재 조회 페이지 수. 0 부터 시작함.
	 * 스프링 페이지는 첫페이지를 0부터 시작하기
	 * 때문에 컨트롤러에서 호출할때는 1부터 시작
	 * 그래서 -1를 현재 페이지로 설정 한다.
	 * 
	 *
	 * @param currentPage the current page
	 */
	public PageParam(int currentPage) {
		this.pageable = new PageRequest(currentPage-1, pagePerSize);

	}

	/**
	 * Instantiates a new page param.
	 *
	 * @param currentPage the current page
	 * @param pagePerSize the page per size
	 */
	public PageParam(int currentPage, int pageSize) {
		this.pageable = new PageRequest(currentPage-1, pageSize);
	}

	/**
	 * between 쿼리에서 첫번 째 인덱스.
	 *
	 * @return the begin index
	 */
	public int getBeginIndex() {
		return (int)this.pageable.getOffset() + 1;
	}

	/**
	 * between 쿼리에서 두번 째 인덱스.
	 *
	 * @return the begin index
	 */
	public int getEndIndex() {
		return (getBeginIndex() + pageable.getPageSize()) -1;
	}
	
	int pageRange;
	int currentPage;
	int pagePerRowSize;
	long totalRow;
	int totalPage;
	boolean firstFlag;
	boolean lastFlag;
	boolean preFlag;
	boolean nextFlag;
	int displayPrePage;
	int  displayNextPage;
	int displayBeginPage;
	int displayEndPage;
}
