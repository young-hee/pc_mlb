package com.plgrim.ncp.cmp.sample;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.sample.Dept;
import com.plgrim.ncp.base.entities.datasource1.sample.Emp;
public interface TranCommandComponent {

	public List<Dept> transaction(Emp emp, Dept dept) throws Exception ;
	
	public void subFunction(Dept dept) throws Exception ;
}
