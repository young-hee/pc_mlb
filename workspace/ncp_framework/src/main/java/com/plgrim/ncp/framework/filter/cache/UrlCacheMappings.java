package com.plgrim.ncp.framework.filter.cache;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.plgrim.ncp.framework.systems.SystemClock;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class UrlCacheMappings {
	@Value("${contentsCache.urlMapping.cfg.location}")
	Resource cfgFileResource;

	@Autowired
	SystemClock clock;

	long 검사주기 = 1000L;

	private long 마지막_검사한_시간;

	private File cfgFile;

	private long 설정파일_마지막_변경된_시간;

	private Set<String> cachedRequests;
//	private Set<String> deniedRequests;

	private ArrayList<Pattern> urls;

	@PostConstruct
	public void init() throws IOException {
		cfgFile = cfgFileResource.getFile();
		log.info("Content Cache config location: {}", cfgFile.getAbsolutePath());
		cachedRequests = Sets.<String> newConcurrentHashSet();
//		deniedRequests = Sets.<String> newConcurrentHashSet();
		checkCfgUpdated();
	}

	public boolean 캐시대상URL인가(HttpServletRequest req) {
		checkCfgUpdated();
		String uri = req.getRequestURI();
		if (cachedRequests.contains(uri)) {
			return true;
		}
		// charles : deniedRequests는 쓸데없이 메모리를 잡아먹는 것이니 삭제처리
//		if (deniedRequests.contains(uri)) {
//			return false;
//		}
		for (Pattern urlPattern : urls) {
			Matcher m = urlPattern.matcher(uri);
			if (m.find()) {
				cachedRequests.add(uri);
				return true;
			}
		}
//		deniedRequests.add(uri);
		return false;
	}

	private void checkCfgUpdated() {
		if (clock.isNotOver(마지막_검사한_시간, 검사주기)) {
			return;
		}
		마지막_검사한_시간 = clock.currentTimeMillis();

		if (cfgFile.lastModified() == 설정파일_마지막_변경된_시간) {
			return;
		}
		log.info("Content cache config has changed. reload it");
		설정파일_마지막_변경된_시간 = cfgFile.lastModified();
		 
		try {
			loadConfig();
		} catch (Exception e) {
			log.error("설정 읽기에 실패했습니다",  e);
			// 설정 읽기에 실패해도  Http  요청 처리가 멈추어서는 않됨
		}
	}

	public void loadConfig() {
		synchronized (this) {
			try {
				reloadUrls();
				clearCaches();
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		}
	}

	private void clearCaches() {
		cachedRequests.clear();
//		deniedRequests.clear();
	}

	private void reloadUrls() throws IOException {
		List<String> lines = Files.readLines(cfgFile, Charset.defaultCharset());
		ArrayList<Pattern> newUrls = new ArrayList<Pattern>();
		for (String urlRegex : lines) {
			if (StringUtils.isBlank(urlRegex)) {
				continue;
			}
			log.info("Content Cache Regex: {}", urlRegex);
			newUrls.add(Pattern.compile(urlRegex.trim()));
		}
		urls = newUrls;
	}
}
