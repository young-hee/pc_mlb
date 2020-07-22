package com.plgrim.ncp.cmp.sample;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.example.dto.EmpSampleDTO;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * Emp Grid Sample Component Implementation
 * @author Park
 *
 */
@Component
public class GridSampleCommandComponentImpl extends AbstractComponent implements GridSampleCommandComponent {

	@Autowired
	SampleCommandComponent sampleCommandComponent;
	
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.biz.example.GridSampleCommandComponent#saveEmpGrid(java.util.List)
	 */
	@Override
	@Transactional(value = "transactionManager")
	public void saveEmpGrid(SystemPK systemPk, List<EmpSampleDTO> empGridDataList) throws Exception {
		if (empGridDataList != null) {
			Emp emp = null;
			for(EmpSampleDTO empDTO: empGridDataList) {
				emp = new Emp();
				BeanUtils.copyProperties(empDTO, emp);

				if ("inserted".equals(empDTO.getRowStatus())) {
					sampleCommandComponent.addEmp(systemPk, emp);
				}
				else if ("updated".equals(empDTO.getRowStatus())) {
					sampleCommandComponent.modifyEmp(systemPk, emp);
				}
				else if ("deleted".equals(empDTO.getRowStatus())) {
					sampleCommandComponent.removeEmp(systemPk, empDTO.getEmpno());
				}
			}
		}
    }

}
