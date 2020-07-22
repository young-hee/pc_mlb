package com.plgrim.ncp.framework.responsecode.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.StaticMessageSource;

import com.plgrim.ncp.framework.messages.MessageUtils;
import com.plgrim.ncp.framework.responsecode.ResponseCode;
import com.plgrim.ncp.framework.responsecode.ResponseException;
import com.plgrim.ncp.framework.responsecode.common.CommonResponseCode;
import com.plgrim.ncp.framework.systems.BusinessType;

public class CommonResponseCodeTest {
	ResponseCode code1 = CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다;
	ResponseCode code2 = CommonResponseCode.SC_45000_요청한_입력값_X0_이_유효하지_않습니다;

	@Before
	public void setUp() throws Exception {
		initMessageSource();
	}

	/*
	 * Message Format 기능을 MessageSource에 위임하고 있기 때문에 의존성 주입함 
	 */
	private void initMessageSource() throws Exception {
		StaticMessageSource ms = new StaticMessageSource();
		MessageUtils mu = new MessageUtils();
		mu.setMessageSource(ms);
		mu.afterPropertiesSet();
	}

	@Test
	public void basic() {
		assertEquals("SC_50000", code1.getCode());
		assertEquals(BusinessType.COMMONS, code1.getBusinessType());
		assertEquals("50000", code1.getCodeNo());
	}

	@Test
	public void noArgsToMessage() {
		assertEquals("[SC_50000] 시스템 오류가 발생하였습니다", code1.toMessage());
		assertEquals("시스템 오류가 발생하였습니다", code1.toRawMessage());
	}

	@Test
	public void noArgsToException() {
		ResponseException ex = code1.toException();
		assertEquals("[SC_50000] 시스템 오류가 발생하였습니다", ex.getMessage());
		assertEquals(CommonResponseCode.SC_50000_시스템_오류가_발생하였습니다, ex.getResponseCode());
	}

	@Test
	public void withArgsToMessageWithoutParam() {
		assertEquals("[SC_45000] 요청한 입력값 {0} 이 유효하지 않습니다", code2.toMessage());
		assertEquals("요청한 입력값 {0} 이 유효하지 않습니다", code2.toRawMessage());
	}

	@Test
	public void withArgsToMessageWithParam() {
		assertEquals("요청한 입력값 ABC 이 유효하지 않습니다", code2.toRawMessage("ABC"));
		assertEquals("[SC_45000] 요청한 입력값 ABC 이 유효하지 않습니다", code2.toMessage("ABC"));
	}
	
	@Test
	public void nestedException() {
		RuntimeException rex = new RuntimeException("here");
		ResponseException ex = code1.toException(rex);
		assertEquals(rex, ex.getCause());
	}
}
