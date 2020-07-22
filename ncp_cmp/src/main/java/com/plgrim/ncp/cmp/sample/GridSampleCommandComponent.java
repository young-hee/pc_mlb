package com.plgrim.ncp.cmp.sample;

import java.util.List;

import com.plgrim.ncp.biz.example.dto.EmpSampleDTO;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * Emp Sample Grid Interface
 * @author Park
 *
 */
public interface GridSampleCommandComponent {

	/**
	 * Emp Grid Data 저장
	 * @param empGridDataList EmpSampleDTO 를 Element로 가지는 List
	 * @throws Exception 
	 */
	public void saveEmpGrid(SystemPK systemPk, List<EmpSampleDTO> empGridDataList) throws Exception;
}
