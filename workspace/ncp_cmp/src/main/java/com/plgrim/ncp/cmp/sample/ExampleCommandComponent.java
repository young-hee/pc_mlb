package com.plgrim.ncp.cmp.sample;

import java.util.List;

/**
 * 
 * @author Louis
 *
 */
public interface ExampleCommandComponent {

	/**
	 * Emp Grid Data 저장
	 * @param <TestExampleDTO>
	 * @param 
	 * @return 
	 * @throws Exception 
	 */
	public <TestExampleDTO> void mergeExampleGridData(List<TestExampleDTO> dataList) throws Exception;
	
	public <TestExampleDTO> void deleteExampleGridData(List<TestExampleDTO> deleteList) throws Exception;

	public <TestExampleDTO> Object createExampleGridData(List<TestExampleDTO> createList) throws Exception;

	public <TestExampleDTO> void updateExampleGridData(List<TestExampleDTO> updateList) throws Exception;

	public <TestExampleDTO> void executeExampleGridData(List<TestExampleDTO> createList, List<TestExampleDTO> updateList) throws Exception;
	
}
