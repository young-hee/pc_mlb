package com.plgrim.ncp.framework.cloud;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

@Component
@Slf4j
public class HealthCheckBean implements InitializingBean {
    private static final String X_FORWARDED_PORT = "X-Forwarded-Port";
    private static final String X_FORWARDED_PROTO = "X-Forwarded-Proto";
    private static final String X_FORWARDED_FOR = "X-Forwarded-For";
    private static final String PATH_VERSION = "classpath://version.info";
    private static final String PATH_FRAMEWORK_VERSION = "classpath://framework-version.info";
    private static final String PATH_BASE_VERSION = "classpath://base-version.info";
    private static final String PATH_CMP_VERSION = "classpath://cmp-version.info";
    private static final String PATH_COMMONS_VERSION = "classpath://commons-version.info";
    private static final String PATH_CONFIG_VERSION = "classpath://config-version.info";
    private static final String PATH_IF_VERSION = "classpath://if-version.info";

    private static final String MILLIS = " millis";

    private static final String SELECT_1_FROM_DUAL = "SELECT 1 FROM dual";
    
    @Autowired
    private DataSource dataSource1;
    
    @Autowired
    private ApplicationContext ctx;
    
    private String hostName;
    
    private String version;
    private Map<String, String> libVersionMap = new HashMap<>();
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {
        try {
            setHostName();
            
        	try {
				this.version = getVersion(PATH_VERSION);
	        	this.libVersionMap.put("framework", getVersion(PATH_FRAMEWORK_VERSION));
	        	this.libVersionMap.put("base", getVersion(PATH_BASE_VERSION));
	        	this.libVersionMap.put("cmp", getVersion(PATH_CMP_VERSION));
	        	this.libVersionMap.put("commons", getVersion(PATH_COMMONS_VERSION));
	        	this.libVersionMap.put("config", getVersion(PATH_CONFIG_VERSION));
	        	this.libVersionMap.put("if", getVersion(PATH_IF_VERSION));
			} catch (IOException e) {
				log.warn("error occured during called getVersion by each library:{}", e.getMessage(), e);
			}

        	
        } catch (SocketException e) {
            log.warn("Error ouccred during setHostName:{}", e.getMessage(), e);
            this.hostName = "Unknown";
        }
    }

    private void setHostName() throws SocketException {
        Enumeration<NetworkInterface> enet = NetworkInterface.getNetworkInterfaces();

        while (enet.hasMoreElements()) {
            NetworkInterface net = enet.nextElement();
            if (net.isLoopback()) {
                continue;
            }

            Enumeration<InetAddress> eaddr = net.getInetAddresses();

            while (eaddr.hasMoreElements()) {
                InetAddress inet = eaddr.nextElement();

                this.hostName = inet.getCanonicalHostName();
                if (!this.hostName.equalsIgnoreCase(inet.getHostAddress())) {
                    break;
                }
            }
        }
    }
    
    /**
     * print healthcheck data to json format
     * 
     * @param response HttpServletResponse instance
     * @return return healthcheck data
     */
    public Map<String, String> healtcheck(HttpServletRequest request, HttpServletResponse response) {
        long startTime = System.currentTimeMillis();

        Map<String, String> map = new HashMap<>();
        try {
            DateFormat df = new SimpleDateFormat("yyyyMMdd hh:mm:ss.sss");
            
            try (Connection cloudConn = this.dataSource1.getConnection();) {
                long cloudStartTime = System.currentTimeMillis();
                try (Statement cloudStmt = cloudConn.createStatement()) {
                    try (ResultSet cloudRs = cloudStmt.executeQuery(SELECT_1_FROM_DUAL)) {
                        cloudRs.next();
                        String cloudRsResult = cloudRs.getString(1);
                        long cloudEndTime = System.currentTimeMillis();
                        map.put("cloudZoneDB connection test result", cloudRsResult);
                        map.put("cloudZoneDB test elap time", Long.toString(cloudEndTime - cloudStartTime) + MILLIS);
                    }
                }
            }

            long endTime = System.currentTimeMillis();

            map.put("hostname", this.hostName);
            map.put("startTime", df.format(new Date(startTime)));
            map.put("endTime", df.format(new Date(endTime)));
            map.put("elapTime(millisecond)", Long.toString(endTime - startTime) + MILLIS);
            map.put("appliationVersion", this.version);
            map.put("libraryVersion", this.libVersionMap.toString());
            map.put(X_FORWARDED_FOR, request.getHeader(X_FORWARDED_FOR));
            map.put(X_FORWARDED_PROTO, request.getHeader(X_FORWARDED_PROTO));
            map.put(X_FORWARDED_PORT, request.getHeader(X_FORWARDED_PORT));

        } catch (Exception e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            String header = this.hostName + ":" + System.currentTimeMillis();
            log.error(header + ":" + e.getMessage(), e);
            map.put("error", e.getMessage());
        }
        
        return map;
    }
    
    public String getVersion(String path) throws IOException {
        Resource[] resources = ctx.getResources(path);
        if (resources == null || resources.length == 0) {
        	return "version file["+path+"] not found";
        }
        Resource resource = resources[0];

        String version = "";
        if (resource.isReadable()) {
        	List<String> lines = IOUtils.readLines(resource.getInputStream(), "UTF-8");
        	if (lines == null || lines.size() == 0) {
        		return "version file["+path+"] is empty";
        	}
        	version = lines.get(0);
        }
        if (StringUtils.isBlank(version)) {
            return "version information not available.";
        }
        return version;
    }
    
    public String getVersion() {
    	return version;
    }
}
