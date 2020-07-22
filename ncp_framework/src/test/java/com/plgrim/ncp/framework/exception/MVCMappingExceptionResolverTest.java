package com.plgrim.ncp.framework.exception;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import com.plgrim.ncp.framework.systems.SystemContext;

public class MVCMappingExceptionResolverTest {

	private SystemContext systemContext;
	private MessageSource ms;
	private MessageSourceAccessor msa;
	private MVCMappingExceptionResolver resolver;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private Object handler;
	private RuntimeException ex;

	@Before
	public void setUp() throws NoSuchMethodException {
		systemContext = new SystemContext();

		ms = mock(MessageSource.class);
		
		msa = new MessageSourceAccessor(ms);

		resolver = new MVCMappingExceptionResolver();
		resolver.setDefualtView("errors/generalError");
		resolver.setDefaultPopupView("errors/generalPopupError");
		resolver.setGoodsView("goodsError");
		resolver.setOrderView("orderError");
		resolver.setSystemContext(systemContext);
		resolver.setMessageSourceAccessor(msa);
		
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handler = new HandlerMethod(request,"getAuthType", null);
		ex = new RuntimeException();
	}

	@Test
	public void defaultViewName() {
		ModelAndView mv = resolver.doResolveException(request, response, handler, ex);
		assertEquals("errors/generalError", mv.getViewName());
		assertNull("RuntimeException에 대해서는 예외 메세지가 준비되어 있지 않다", mv.getModelMap().get("message"));
	}

	@Test
	public void goodsErrorViewName() {
		request.setRequestURI("/public/goods/abc");
		ModelAndView mv = resolver.doResolveException(request, response, handler, ex);
		assertEquals("goodsError", mv.getViewName());
		assertNull("RuntimeException에 대해서는 예외 메세지가 준비되어 있지 않다", mv.getModelMap().get("message"));
	}
	
	@Test
	public void orderErrorViewName() {
		request.setRequestURI("/secured/order/abc");
		ModelAndView mv = resolver.doResolveException(request, response, handler, ex);
		assertEquals("orderError", mv.getViewName());
		assertNull("RuntimeException에 대해서는 예외 메세지가 준비되어 있지 않다", mv.getModelMap().get("message"));
	}
	
	@Test
	public void exceptionClassMessageKey() {
		when(ms.getMessage(eq("CssNoticeNotExistInsertException"), any(Object[].class), any(Locale.class))).thenReturn("등록에 실패하였습니다");
		ModelAndView mv = resolver.doResolveException(request, response, handler, new CssNoticeNotExistInsertException());
		assertEquals("등록에 실패하였습니다", mv.getModelMap().get("message"));
	}
	
	@Test
	public void exceptionClassMessageWithArgument() {
		when(ms.getMessage(eq("CssNoticeNotExistUpdateException"), AdditionalMatchers.aryEq(new String[]{"5"}), any(Locale.class))).thenReturn("5cases have been edited.");
		ModelAndView mv = resolver.doResolveException(request, response, handler, new CssNoticeNotExistUpdateException(5L));
		assertEquals("5cases have been edited.", mv.getModelMap().get("message"));
	}

}

class CssNoticeNotExistInsertException extends NCPException {
}

class CssNoticeNotExistUpdateException extends NCPException {
	public CssNoticeNotExistUpdateException(Long count) {
		super();
		init(new String[]{String.valueOf(count)});
	}
}