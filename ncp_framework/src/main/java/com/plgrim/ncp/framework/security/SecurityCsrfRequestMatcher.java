package com.plgrim.ncp.framework.security;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CSRF 적용할 패턴을 정의 한다.
 * 호출 URL이 excludes에 해당이 되면 CSRF를 실행 하지 않는다.
 */
@Slf4j
@Data
public class SecurityCsrfRequestMatcher implements RequestMatcher {

    /* CSRF 대상이되는 HTTP METHOD */
    final static String [] MATCH_METHODS = {"POST", "PUT", "DELETE"};

    /* 모든된 url은 CSRF 검증을 하지 않는다. */
    List<String> excludes = Lists.newArrayList();

    /* true일 경우는 CSRF 검증 한다. */
    @Override
    public boolean matches(HttpServletRequest request) {
    	return false;
//        boolean result = false;
//        String currentMethod = request.getMethod().toUpperCase();
//
//        //대상 http 메서드 검증
//        for (String method : MATCH_METHODS) {
//            if (method.equals(currentMethod)) {
//                result = true;
//                break;
//            }
//        }
//
//        //메서드가 GET일 경우
//        if (!result) {
//            return result;
//        }
//
//        String requestURL = request.getRequestURI() ;
//
//        //해당 url들은 validation 체크를 하지 않는다.
//        for (String url : excludes) {
//            if (requestURL.equals(url)) {
//                result = false;
//                break;
//            }
//        }
//
//        return result;
    }
}
