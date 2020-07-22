package com.plgrim.ncp.framework.alarm;

import static org.junit.Assert.*;

import org.junit.Test;

public class NcpSmsAlarmServiceTest {

	@Test
	public void test() {
		NcpSmsAlarmService service = new NcpSmsAlarmService();
		service.setIfServerUrl("http://stg.if.plgrimshop.com/if");
		service.alarm("CB Message Test");
	}

}
