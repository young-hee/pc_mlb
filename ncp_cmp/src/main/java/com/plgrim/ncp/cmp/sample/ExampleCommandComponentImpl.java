package com.plgrim.ncp.cmp.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.example.service.ExampleCommandService;

/**
 * Grid Example Component Implementation
 * @author Louis
 *
 */
@Component
public class ExampleCommandComponentImpl extends AbstractComponent implements ExampleCommandComponent {

	@Autowired
	ExampleCommandService exampleCommandService;

	@Override
	public <TestExampleDTO> void mergeExampleGridData(
	        List<TestExampleDTO> dataList) throws Exception {

		exampleCommandService.mergeExampleGridData(dataList);
	}

	@Override
	public <TestExampleDTO> List<TestExampleDTO> createExampleGridData(
	        List<TestExampleDTO> createList) throws Exception {

		exampleCommandService.createExampleGridData(createList);

		return createList;
	}

	@Override
	public <TestExampleDTO> void updateExampleGridData(
	        List<TestExampleDTO> updateList) throws Exception {
		
		exampleCommandService.updateExampleGridData(updateList);
	}
	
	@Override
	public <TestExampleDTO> void deleteExampleGridData(
	        List<TestExampleDTO> deleteList) throws Exception {

		exampleCommandService.deleteExampleGridData(deleteList);
	}

	@Override
	@Transactional(value = "transactionManager")
    public <TestExampleDTO> void executeExampleGridData(List<TestExampleDTO> createList, List<TestExampleDTO> updateList) throws Exception {
		exampleCommandService.createExampleGridData(createList);
		
		exampleCommandService.updateExampleGridData(updateList);
    }
}
