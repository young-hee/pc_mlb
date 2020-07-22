package com.plgrim.ncp.framework.messages;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.servlet.LocaleResolver;

import com.google.common.collect.Sets;
import com.plgrim.ncp.framework.commons.CollectionService;

import lombok.extern.slf4j.Slf4j;

/**
 * getAllMessage 기능을 추가한 MessageSource
 * TODO : createCache에 spring cache 적용 검토 필요
 * 
 * @author Chulhui Park <charles@plgrim.com>
 */
@Slf4j
public class NCPReloadableMessageSource extends ReloadableResourceBundleMessageSource {

    private LocaleResolver localeResolver;
    
    private ResourceBundleScanner scanner = new ResourceBundleScanner();


    public void setBasenamesWithWildcard(String... basenames) throws IOException {
    	if (basenames == null) {
    		log.warn("ignoring setBasenamesWithWildcard : basenames is null");
    		return;
    	}
    	
    	LinkedList<String> basenameList = new LinkedList<>();
    	
    	for (String basename : basenames) {
    		basenameList.addAll(scanner.getResources(basename));
    	}
    	
    	String[] newBasenames = basenameList.stream().toArray(String[]::new);
    	super.setBasenames(newBasenames);
    }
    
	public void setLocaleResolver(LocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

	private Set<String> getAllMessageKeys(String keyPrefix, Locale locale) {
        Set<String> keys = new LinkedHashSet<>();
        
        if (log.isDebugEnabled()) {
            log.debug("getAllMessageKeys: keyPrefix[{}], locale[{}]", keyPrefix, locale);
        }
        
        PropertiesHolder propHolder = this.getMergedProperties(locale);
        Properties prop = propHolder.getProperties();
        for (String key : prop.stringPropertyNames()) {
            if (key.startsWith(keyPrefix)) {
                keys.add(key);
            }
        }
        
        return keys;
    }
    
    /**
     * create cache with keyPrefix and locale
     * 
     * @param keyPrefix key prefix in MessageSource key name
     * @param request for locale
     * @return return all message in given key prefix
     */
    private Map<String, String> createCache(String keyPrefix, Locale locale) {
        Map<String, String> messageMap = new HashMap<>();
        Set<String> keys = getAllMessageKeys(keyPrefix, locale);
        
        for(String key : keys) {
            String message = getMessage(key, null, locale);
            messageMap.put(key, message);
        }
        
        return messageMap;
    }
    
    /**
     * get all messages with keyPrefix and locale
     * 
     * @param keyPrefix key prefix in MessageSource key name
     * @param locale locale
     * @return return all message in given key prefix with locale
     */
    public Map<String, String> getAllMessages(String keyPrefix, Locale locale) {
        return createCache(keyPrefix, locale);
    }
    
    /**
     * get all messages of given keyPrefix in MessageSource
     * 
     * @param keyPrefix key prefix in MessageSource key name
     * @param request for locale
     * @return return all message in given key prefix
     */
    public Map<String, String> getAllMessages(String keyPrefix, HttpServletRequest request) {
        Locale locale = localeResolver.resolveLocale(request);
        return getAllMessages(keyPrefix, locale);
    }
    
    private static class ResourceBundleScanner {
    	/* 메세지 파일 확장자 명*/
    	final static String INCLUDE_EXTENSION = ".properties";
    	
    	/* 메세지 타입 검사 문자열*/
    	final static String LAST_INDEX_CHAR = "_";

    	static String messageRootDirectory = "/META-INF";
    	
    	/*
    	 * ---------------------------------------------------------------------
    	 * Constructors.
    	 * ---------------------------------------------------------------------
    	 */
    	/*
    	 * ---------------------------------------------------------------------
    	 * public & interface method.
    	 * ---------------------------------------------------------------------
    	 */

    	/**
    	 * 지정된 Ant 스타일의 파일패턴을 분석 해서 해당 파일들을 리스트 형태로 리턴 한다. <br/>
    	 * getResources는 팩토리빈 생성 메서드.
    	 * 
    	 * <p/>
    	 * 
    	 * getResources(/WEB-INF/messages/**);
    	 * 
    	 * 
    	 *  WEB-INF/messages/Message
            WEB-INF/messages/order/message_a
    		WEB-INF/messages/product/message"
    	 * 
    	 *
    	 * @param locations 리소스 경로 배열
    	 * @return List 모든 리소스 경로
    	 * @throws IOException 
    	 * @since 2015. 3. 13
    	 */
    	public List<String> getResources(String wildcardBase) throws IOException {
    		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    		Resource[] resources = resolver.getResources(wildcardBase);

    		Set<String> locationSet = Sets.newHashSet();

    		for (Resource resource : resources) {

    			String location = resource.getDescription();

    			if (StringUtils.contains(location, INCLUDE_EXTENSION)) {

    				location = StringUtils.replace(location,"\\" , "/" );
    				int index =  StringUtils.indexOf(location, messageRootDirectory);

    				if (index > 0) {

    					//"_{언어셋}.properties를 삭제 한다."
    					location = location.substring(index);
    					location = StringUtils.substringBefore(location, LAST_INDEX_CHAR);
    					location = location.substring(1);
    					location = "classpath:"+location.trim();
    					locationSet.add(location);
    				}
    			}
    		}

    		StringBuffer message = new StringBuffer();
    		message.append("\n\n######################################################\n")
    				.append("# Message Resource Information \n")
    				.append("######################################################\n");
    		log.info(message.toString());
    		for (String path : locationSet) {
    			log.info(path);
    		}

    		message.setLength(0);
    		message.append("\n\n######################################################\n")
    				.append("# URL Mapping Information \n")
    				.append("######################################################\n");

    		log.info(message.toString());

    		return CollectionService.setToList(locationSet);
    	}

    }
}
