package com.plgrim.ncp.biz.example.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

/**
 * Emp Grid Sample DTO
 * @author Park
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class EmpSampleDTO extends AbstractDTO{
	/**
	 * UID
	 */
    private static final long serialVersionUID = -2343476483916844524L;
    
    /**
     * Grid Row Status
     */
    String rowStatus;

	/**
	 * 사원 번호
	 */
	Integer empno;

	/**
	 * 사원 이름
	 */
	String ename;

	/**
	 * 담당 업무
	 */
	String job;

	/**
	 * 급여
	 */
	Integer sal;

	/**
	 * 부서 번호
	 */
	Integer deptNo;

}
