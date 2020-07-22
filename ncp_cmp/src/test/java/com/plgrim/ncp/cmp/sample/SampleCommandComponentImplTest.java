package com.plgrim.ncp.cmp.sample;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
import com.plgrim.ncp.biz.claim.data.ClaimReceiptDTO;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/META-INF/spring/base.all.xml" })
@ActiveProfiles("local")
@Slf4j
public class SampleCommandComponentImplTest {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	static {
		System.setProperty("system.id", "ncp_base");
	}
	
	@Autowired
	SampleCommandComponent component;
	
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
	
	
	public void transaction () throws Exception {
		
		
		Dept dept = new Dept();
		dept.setDeptNo(90);
		dept.setDName("test");
		
		
//		#{empno},
//		#{ename},
//		#{job},
//		#{sal},
//		#{deptNo}
		
		
		Emp emp = new Emp();
		emp.setEmpno(999);
		emp.setEname("ename");
		emp.setJob("job");
		emp.setSal(1000);
		emp.setDeptNo(10);
		
		
		component.transaction2(new SystemPK(), emp, dept);
		
		
				
//		sampleService.createDept(systemPk, dept);
//		sampleService.createEmp(systemPk, emp);
		
		
	}
	
	
	@Test
    public void setTotalCancelDTO(){
		ClaimReceiptDTO claimReceiptDTO = new ClaimReceiptDTO();
		//claimReceiptDTO.setClmResnCd("TEST");
        claimReceiptDTO.setClmStatCd("RCEPT"); //접수 RCEPT
        claimReceiptDTO.setClmTpCd("ALL_CNCL"); //전체취소 ALL_CNCL
        //claimReceiptDTO.setClmResnCont("TOTAL CANCEL");
        /*
         * 취소사유는 as-is에서 단순변심 SIMPL_CHGMIND
         * 	- 미결제 시 '자동주문취소' 배치에서는 'PAY_DELAY'(결제지연) 으로 입력
         */
        claimReceiptDTO.setClmResnCd( StringService.isNotEmpty( claimReceiptDTO.getClmResnCd() ) 
        		? claimReceiptDTO.getClmResnCd() : "CSTMR_CHGMIND_CNCL");
        log.debug(claimReceiptDTO.getClmResnCd());
    }
	
	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
