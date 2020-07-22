package com.plgrim.ncp.framework.security;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * 세션 생성/소멸시 이벤트 설정.
 */
public class SecurityHttpSessionEventPublisher extends HttpSessionEventPublisher {

    static Map<String, Object> sessionMap = new ConcurrentHashMap<String, Object>();

    @Override
    public void sessionCreated(HttpSessionEvent event) {

        String sessionId = event.getSession().getId();
        sessionMap.put(sessionId, event.getSession());

        super.sessionCreated(event);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        String sessionId = event.getSession().getId();
        sessionMap.remove(sessionId);

        super.sessionDestroyed(event);
    }

    /* 세션 아이디를 통해서 세션을 가져온다. */
    public static HttpSession getSession(String sessionId) {
        return (HttpSession)sessionMap.get(sessionId);
    }

}
