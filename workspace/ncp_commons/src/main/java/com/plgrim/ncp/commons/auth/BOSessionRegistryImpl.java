package com.plgrim.ncp.commons.auth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.util.Assert;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

import lombok.extern.slf4j.Slf4j;

/**
 * SessionRegistry
 */
@Slf4j
public class BOSessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent> {
	// ~ Instance fields
	// ================================================================================================

	protected final Log logger = LogFactory.getLog(SessionRegistryImpl.class);

	/** <principal:Object,SessionIdSet> */
	private final ConcurrentMap<Object, Set<String>> principals = new ConcurrentHashMap<Object, Set<String>>();
	/** <sessionId:Object,SessionInformation> */
	private final Map<String, SessionInformation> sessionIds = new ConcurrentHashMap<String, SessionInformation>();
	
	@Autowired
    AuthenticationComponent authenticationComponent;

	// ~ Methods
	// ========================================================================================================

	public List<Object> getAllPrincipals() {
		return new ArrayList<Object>(principals.keySet());
	}

	public List<SessionInformation> getAllSessions(Object principal,
			boolean includeExpiredSessions) {
		final Set<String> sessionsUsedByPrincipal = principals.get(principal);

		if (sessionsUsedByPrincipal == null) {
			return Collections.emptyList();
		}

		List<SessionInformation> list = new ArrayList<SessionInformation>(
				sessionsUsedByPrincipal.size());

		for (String sessionId : sessionsUsedByPrincipal) {
			SessionInformation sessionInformation = getSessionInformation(sessionId);

			if (sessionInformation == null) {
				continue;
			}

			if (includeExpiredSessions || !sessionInformation.isExpired()) {
				list.add(sessionInformation);
			}
		}

		return list;
	}
	
	public SessionInformation getSessionInformation(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		
		SessionInformation info = sessionIds.get(sessionId);
		
		//DB 에 저장된 sessionId 와 비교후 일치하지 않으면 Expire 처리
		if(checkMultiLogin(sessionId)) {
			if(info != null)
				info.expireNow();
		}
		
		return info;
	}
	
	/**
	 * DB 에 저장된 sessionId 와 일치 여부 조회(logout 처리)
	 */
	public boolean checkMultiLogin(String sessionId) {
		try {
			if(isRequireLoginCheck() == false){
				return false;
			}
			
			if(isExcludeIdLoginCheck() == false){
				return false;
			}
			
			SysAdmin sysAdmin = authenticationComponent.selectRowSysAdmInfo(BOSecurityUtil.getCurrentUserDetail().getSysAdmin().getAdminId());
			if(sysAdmin != null && sysAdmin.getAdminId() != null) {
				log.debug(" >>> checkMultiLogin adminId / old-sessionId / new-sessionId : {} / {} / {}"
						, sysAdmin.getAdminId() 
						, sessionId
						, sysAdmin.getSesionId());
				
				//BO,PO 사이트 동일아이디 중복로그인 체크
				if(StringService.isNotEmpty(sysAdmin.getSesionId()) && !sessionId.equals(sysAdmin.getSesionId())) {
					log.info(" >>> checkMultiLogin session expired : adminId / old-sessionId / new-sessionId : {} / {} / {}"
							, sysAdmin.getAdminId() 
							, sessionId
							, sysAdmin.getSesionId());
					return true;
				}
			}
			return false;
		} catch (Exception ex) {
			log.warn("", ex);
			return false;
		}
	}

	/**
	 * 로그인 여부 체크
	 */
	private boolean isRequireLoginCheck() {
		return BOSecurityUtil.hasRole() && (BOSecurityUtil.isBoSite() || BOSecurityUtil.isPoSite()) && BOSecurityUtil.getCurrentUserDetail().getSysAdmin() != null;
	}
	
	/**
	 * 중복로그인 허용 아이디 체크
	 */
	private boolean isExcludeIdLoginCheck() {
		boolean chk = true;
		DynamicStringProperty excludeId = DynamicPropertyFactory.getInstance().getStringProperty("bo_multi_login_exclude_id", "");
		log.debug(">>> checkMultiLogin excludeId : {}", excludeId.getValue());
		if(!StringService.isEmpty(excludeId.getValue())) {
			String[] arrExcludeId = excludeId.getValue().split(",");
			if(arrExcludeId != null && arrExcludeId.length > 0) {
				for(int i=0; i<arrExcludeId.length; i++){
					if(arrExcludeId[i].equals(BOSecurityUtil.getCurrentUserDetail().getSysAdmin().getAdminId())) {
						return false;
					}
				}
			}
		}
		
		return chk;
	}

	public void onApplicationEvent(SessionDestroyedEvent event) {
		String sessionId = event.getId();
		removeSessionInformation(sessionId);
	}

	public void refreshLastRequest(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");

		SessionInformation info = getSessionInformation(sessionId);

		if (info != null) {
			info.refreshLastRequest();
		}
	}

	public void registerNewSession(String sessionId, Object principal) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");
		Assert.notNull(principal, "Principal required as per interface contract");

		if (logger.isDebugEnabled()) {
			logger.debug("Registering session " + sessionId + ", for principal "
					+ principal);
		}

		if (getSessionInformation(sessionId) != null) {
			removeSessionInformation(sessionId);
		}

		sessionIds.put(sessionId,
				new SessionInformation(principal, sessionId, new Date()));

		Set<String> sessionsUsedByPrincipal = principals.get(principal);

		if (sessionsUsedByPrincipal == null) {
			sessionsUsedByPrincipal = new CopyOnWriteArraySet<String>();
			Set<String> prevSessionsUsedByPrincipal = principals.putIfAbsent(principal,
					sessionsUsedByPrincipal);
			if (prevSessionsUsedByPrincipal != null) {
				sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
			}
		}

		sessionsUsedByPrincipal.add(sessionId);

		if (logger.isTraceEnabled()) {
			logger.trace("Sessions used by '" + principal + "' : "
					+ sessionsUsedByPrincipal);
		}
	}

	public void removeSessionInformation(String sessionId) {
		Assert.hasText(sessionId, "SessionId required as per interface contract");

		SessionInformation info = getSessionInformation(sessionId);

		if (info == null) {
			return;
		}

		if (logger.isTraceEnabled()) {
			logger.debug("Removing session " + sessionId
					+ " from set of registered sessions");
		}

		sessionIds.remove(sessionId);

		Set<String> sessionsUsedByPrincipal = principals.get(info.getPrincipal());

		if (sessionsUsedByPrincipal == null) {
			return;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Removing session " + sessionId
					+ " from principal's set of registered sessions");
		}

		sessionsUsedByPrincipal.remove(sessionId);

		if (sessionsUsedByPrincipal.isEmpty()) {
			// No need to keep object in principals Map anymore
			if (logger.isDebugEnabled()) {
				logger.debug("Removing principal " + info.getPrincipal()
						+ " from registry");
			}
			principals.remove(info.getPrincipal());
		}

		if (logger.isTraceEnabled()) {
			logger.trace("Sessions used by '" + info.getPrincipal() + "' : "
					+ sessionsUsedByPrincipal);
		}
	}

}
