package com.plgrim.ncp.framework.responsecode.common;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCodeValidator;

import scala.actors.threadpool.Arrays;

public class CommonResponseCodeValidatorTest {

	@Test
	public void test() {
		CommonResponseCodeValidator validator = new CommonResponseCodeValidator();
		validator.afterPropertiesSet();
	}
	
	
	public static void main(String[] args) {
		CommonResponseCode[] codes = CommonResponseCode.values();
		Arrays.sort(codes, new Comparator<CommonResponseCode>(){

			@Override
			public int compare(CommonResponseCode o1, CommonResponseCode o2) {
				return o1.toMessage().compareTo(o2.toMessage());
			}});
		for (CommonResponseCode code : codes) {
			System.out.println(code.getBusinessType().getCode()+","+code.getCodeNo()+","+code.toRawMessage());
		}
	}
	

}
