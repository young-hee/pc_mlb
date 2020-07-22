/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      tktaeki.kim
 * @since       2015. 3. 24       
 */
package com.plgrim.ncp.cmp.sample;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.sample.service.SampleService;

/**
 * 샘플 컴포넌트 구현체.
 *
 * @author tktaeki.kim
 * @since 2015. 2. 26
 */
@Slf4j
@Component
@Transactional(value="transactionManager") 
public class TranCommandComponentImpl extends AbstractComponent implements
        TranCommandComponent {

	/** 샘플 서비스. */
	@Autowired
	SampleService sampleService;
	
	@Override
    public List<Dept> transaction(Emp empT, Dept dept) throws Exception {
		
		Dept deptPut = null;
		List<Dept> deptList = new ArrayList<Dept>();
		
		for(int i = 1 ; i < 11 ; i++){
			try{
				deptPut = new Dept();
				deptPut.setDeptNo(i); 
				deptPut.setDName("DEPT"+i);
				//subFunction(deptPut);
				sampleService.createDept(null, deptPut);
				deptList.add(deptPut);
				
			}catch(Exception e){
				log.warn("tran rollback");
			}
		}
		
		return deptList;
	    
    }
	
	@Override 
	public void subFunction(Dept dept) throws Exception {
		Emp emp = null;
		
	    for(int i = 0 ; i < 2 ; i++){
	    	emp = new Emp();
	    	emp.setEmpno(Integer.parseInt(dept.getDeptNo()+"0"+i));
	    	emp.setEname("Name"+i);
	    	emp.setJob("job"+i);
	    	emp.setSal(i);
	    	emp.setDeptNo(0);
	    	sampleService.createEmp(null, emp);
	    }
    }
	
	

	
}
