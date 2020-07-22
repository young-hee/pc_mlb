package com.plgrim.ncp.cmp.sample;

import java.util.List;

/**
 * 
 * @author Dennis
 *
 */


public interface DennisCommandComponent {

	/**
	 * Emp Grid Data 저장
	 * @param <TestExampleDTO>
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	public <TestExampleDTO> void mergeDennisGridData(List<TestExampleDTO> dataList) throws Exception;
	
}
