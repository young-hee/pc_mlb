package com.plgrim.ncp.framework.filter.cache;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.plgrim.ncp.framework.systems.SystemContext;

import lombok.Cleanup;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 비로그인 사용자에게  HTML 화 된 캐시 컨텐츠를 보여주는  필터   
 *
 *     
 * @formatter:on
 * @author narusas
 *
 */
@Slf4j
@Component
@Data
@ManagedResource
public class AnonymouseContentsCacheFilter extends GenericFilterBean {

	@Autowired
	UrlCacheMappings mappings;

	@Autowired
	ContentsCache cache;

	@Autowired
	SystemContext systemContext;

	boolean 캐시사용하지않음 = false;

	protected void initFilterBean() throws ServletException {
		System.currentTimeMillis();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		if (캐시사용하지않음 || 로그인한_사용자인가(req) || 캐시대상URL이_아닌가(req)) {
			chain.doFilter(request, response);
			return;
		}
		String 캐시Key = 캐시Key(req);
		CacheItem cachedData = cache.getCache(캐시Key);
		if (cachedData != null && cachedData.isNotEmpty()) {
			log.debug("캐시된 컨텐츠를 전송합니다. Key: {}", 캐시Key);
			write(response, cachedData);
			return;
		}

		@Cleanup
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		CacheResponseWrapper cacheResponse = new CacheResponseWrapper(res, bout);
		try {
			chain.doFilter(request, cacheResponse);
		} finally {
			byte[] data = bout.toByteArray();
			CacheItem item = CacheItem.from(data, cacheResponse);
			if (cacheResponse.getStatus() == 200) {
				cache.putCache(캐시Key, item);
			}
			write(response, item);
		}
	}

	void write(ServletResponse response, CacheItem item) throws IOException {
		if (item.isEmpty()) {
			return;
		}
		response.setContentType(item.getContentType());
		response.setContentLength(item.getContentLength());
		response.getOutputStream().write(item.getData());
	}

	String 캐시Key(HttpServletRequest req) {
		String host = req.getHeader("host");
		String uri = req.getRequestURI();
		String queryStr = req.getQueryString();
		String cacheKey = host + uri + "?" + queryStr;
		return cacheKey;
	}

	boolean 캐시대상URL인가(HttpServletRequest req) {
		return mappings.캐시대상URL인가(req);
	}

	boolean 캐시대상URL이_아닌가(HttpServletRequest req) {
		return !캐시대상URL인가(req);
	}

	// 세션에 SecurityContext 가 있는지를 확인하는것이 가장 빠른 체크 로직이기 때문에 처음에 수행한다
	boolean 로그인한_사용자인가(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return false;
		}
		Object value = session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);

		return value != null;
	}
}
