package com.plgrim.ncp.interfaces.web;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.plgrim.ncp.framework.data.mobile.MobileDevicePlatform;
import com.plgrim.ncp.framework.data.mobile.MobileDeviceType;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.amazonaws.HttpMethod;

import lombok.extern.slf4j.Slf4j;

/**
 * API 서버에 대한 모든 request의 시작 종료 log를 출력하는 filter.
 * <pre>
 * 시작 로그 : start: path:$urlpath, queryString:$query-string, params:$request.getparams, body:$httprequest body
 * * body의 크기가 총 5KB 이하일 때에 한해서, body 전체를 출력한다.
 * 종료 로그 : finished: $urlpath
 * </pre>
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Component
@Slf4j
public class LoggingFilter implements Filter {
	public static final long MAX_LOGGING_BODY_SIZE = 5*1024;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("LoggingFilter initialized:filterConfig:{}", filterConfig);
	}

	@Override
	public void destroy() {
		log.info("LoggingFilter destroyed");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getServletPath() + httpRequest.getPathInfo();

		/**
		 * healthcheck uri logging 제외
		 */
		if(path.contains("/system/healthcheck")) {
			chain.doFilter(httpRequest, response);
			return;
		}

		String queryString = httpRequest.getQueryString();
		Map<String, String[]> paramMap = httpRequest.getParameterMap();
		StringBuilder params = new StringBuilder();
		paramMap.forEach((k, v) -> {
			params.append("[" + k + ":" + ArrayUtils.toString(v) + "], ");
		});
		
		HttpMethod method = HttpMethod.valueOf(httpRequest.getMethod());
		boolean isResetableBody = false;
		if (HttpMethod.POST == method || HttpMethod.PUT == method) {
			MediaType mediaType = MediaType.valueOf(httpRequest.getContentType());
			if (MediaType.APPLICATION_JSON.includes(mediaType)) {
				isResetableBody = true;
			}
			
			String contentLength = httpRequest.getHeader(HttpHeaders.CONTENT_LENGTH);
			long length = NumberUtils.toLong(contentLength);
			if (length < MAX_LOGGING_BODY_SIZE) {
				isResetableBody = true;
			}
		}

		String body = "";
		if (isResetableBody) {
			ResettableStreamHttpServletRequest wrappedRequest = new ResettableStreamHttpServletRequest(
					(HttpServletRequest) httpRequest);
			body = IOUtils.toString(wrappedRequest.getReader());
			wrappedRequest.resetInputStream();
			httpRequest = wrappedRequest;
		}

		Map<String, String> haederMap = new HashMap<String, String>();
		Enumeration headerNames = ((HttpServletRequest) request).getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = ((HttpServletRequest) request).getHeader(key);
			haederMap.put(key, value);
		}

		log.info("start: path:{},\n queryString:{},\n params:{},\n body:{},\n header:{}", path, queryString, params, body, haederMap);

		chain.doFilter(httpRequest, response);

		log.info("finished:{}", path);
	}

	/**
	 * HttpServletRequest.getInputStream에 대해 reset 기능 추가.
	 */
	private static class ResettableStreamHttpServletRequest extends HttpServletRequestWrapper {

		private byte[] rawData;
		private HttpServletRequest request;
		private ResettableServletInputStream servletStream;

		public ResettableStreamHttpServletRequest(HttpServletRequest request) {
			super(request);
			this.request = request;
			this.servletStream = new ResettableServletInputStream();
		}

		public void resetInputStream() {
			servletStream.stream = new ByteArrayInputStream(rawData);
		}

		@Override
		public ServletInputStream getInputStream() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getInputStream());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return servletStream;
		}

		@Override
		public BufferedReader getReader() throws IOException {
			if (rawData == null) {
				rawData = IOUtils.toByteArray(this.request.getInputStream());
				servletStream.stream = new ByteArrayInputStream(rawData);
			}
			return new BufferedReader(new InputStreamReader(servletStream, Charset.forName("UTF-8")));
		}

		private class ResettableServletInputStream extends ServletInputStream {

			private InputStream stream;

			@Override
			public int read() throws IOException {
				return stream.read();
			}
		}
	}
}
