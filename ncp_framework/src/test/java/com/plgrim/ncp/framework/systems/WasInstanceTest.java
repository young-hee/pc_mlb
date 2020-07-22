package com.plgrim.ncp.framework.systems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

public class WasInstanceTest {

	@Test
	public void admin() {
		MockEnvironment env = new MockEnvironment();
		env.setProperty(WasInstance.WEBLOGIC_NAME, "bo2Amin");
		assertEquals(WasInstance.M1, WasInstance.decideWasInstance(env));
	}

	@Test
	public void m3() {
		MockEnvironment env = new MockEnvironment();
		env.setProperty(WasInstance.WEBLOGIC_NAME, "bo2M3");
		assertEquals(WasInstance.M3, WasInstance.decideWasInstance(env));
	}
	
	@Test
	public void m10_2_digit() {
		MockEnvironment env = new MockEnvironment();
		env.setProperty(WasInstance.WEBLOGIC_NAME, "bo2M10");
		assertEquals(WasInstance.M10, WasInstance.decideWasInstance(env));
	}

	@Test
	public void regexM() {
		Pattern pattern = Pattern.compile(".*M(\\d+)");
		Matcher m = pattern.matcher("bo2M2");
		assertTrue(m.find());
		assertEquals("2", m.group(1));

	}

}
