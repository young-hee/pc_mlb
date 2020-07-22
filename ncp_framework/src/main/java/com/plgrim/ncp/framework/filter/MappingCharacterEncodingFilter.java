package com.plgrim.ncp.framework.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 기본 동작은 CharacterEncodingFilter 와 동일한 기능이지만, 특정 URL 에 대해 특정 Encoding 을 사용할수 있는
 * 기능을 추가했다.
 * 
 * @author narusas
 *
 */
@Data
@Slf4j
public class MappingCharacterEncodingFilter extends OncePerRequestFilter {

	String encoding;

	boolean forceEncoding = false;

	String mapping;

	Map<String, String> rules = new HashMap<>();

	@Override
	protected void initFilterBean() throws ServletException {
		if (StringUtils.isBlank(mapping)) {
			log.info("No encoding-url mapping rules");
			return;
		}

		try {

			ObjectMapper om = new ObjectMapper();
			Map<String, List<String>> map = om.readValue(mapping, Map.class);
			log.debug("{}", map);
			Iterator<Entry<String, List<String>>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<java.lang.String, java.util.List<java.lang.String>> entry = (Map.Entry<java.lang.String, java.util.List<java.lang.String>>) it
						.next();
				String enc = entry.getKey();
				List<String> urls = entry.getValue();
				for (String url : urls) {
					rules.put(url, enc);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid encoding-url mapping rule", e);
		}
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		if (StringUtils.isNotEmpty(requestURI) && rules.containsKey(requestURI)) {
			request.setCharacterEncoding(rules.get(requestURI));
		} else if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
			request.setCharacterEncoding(this.encoding);
			if (this.forceEncoding) {
				response.setCharacterEncoding(this.encoding);
			}
		}
		filterChain.doFilter(request, response);
	}
}
