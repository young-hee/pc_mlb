package com.plgrim.ncp.cmp.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.biz.example.service.DennisCommandService;


/**
 * Grid Example Component Implementation
 * @author Dennis
 *
 */


@Component
public class DennisCommandComponentImpl extends AbstractComponent implements DennisCommandComponent {

	@Autowired 
	DennisCommandService dennisCommandService;
	
	@Override
    public <TestExampleDTO> void mergeDennisGridData(
            List<TestExampleDTO> dataList) throws Exception {

			dennisCommandService.mergeExampleGridData(dataList);
	    
    }

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

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
