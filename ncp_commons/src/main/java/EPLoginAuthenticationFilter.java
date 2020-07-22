

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class EPLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final static String DEFAULT_TOKEN_KEY_NAME = "epTicket";

	String ssoTicketParameterName = DEFAULT_TOKEN_KEY_NAME;

	private boolean postOnly = true;
	
	private String targetUrl = "";
	@Autowired
    AuthenticationComponent authenticationComponent;

	protected EPLoginAuthenticationFilter() {
		super(new AntPathRequestMatcher("/epsso", "GET"));
		log.info("EPLoginAuthenticationFilter AntPathRequestMatcher");
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		log.info("EPLoginAuthenticationFilter authentication is requested");
		if (postOnly && !request.getMethod().equals("GET")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		String ticket = obtainEPTicket(request);
		log.info("Requested ticket: {}", ticket);
		if (StringUtils.isEmpty(ticket)) {
			log.error("epsso ticket is null.");
			throw new BadCredentialsException("Authentication ticket is null.");
		}
		
		String libFile = "erpsecu.dll";
		String libFileLocation = "C:\\work\\tools\\apache-tomcat-7.0.65\\lib\\";
		String certFile = "ERPLogonTicketKeypair-cert.cert";
		String certFileLocation = "C:\\work\\tools\\apache-tomcat-7.0.65\\lib\\";
		if(System.getProperty("os.name").startsWith("Win")){
			libFile = "erpsecu.dll";
			libFileLocation = "C:\\work\\tools\\apache-tomcat-7.0.65\\lib\\";
			certFileLocation = "C:\\work\\tools\\apache-tomcat-7.0.65\\lib\\";
		}else{
			libFile = "liberpcrypto.so";
			libFileLocation = "";
			certFileLocation = "/cont/singlesso/";
		}
		UsernamePasswordAuthenticationToken authRequest =  null;
		SSO2Ticket.init(new StringBuffer(libFileLocation).append(libFile).toString());
		Object [] o;
		o=null;
		String user = null;
		String Sysid = null;
		String Client = null;
		String PrtUsr = null;

		try {
			byte[] keyfile = SSO2Ticket.getBytesFromFile(new StringBuffer(certFileLocation).append(certFile).toString());
			SSO2Ticket.loadKey(keyfile,null,0,1);
			
			// Validate logon ticket.
			o = SSO2Ticket.evalLogonTicket (ticket, null, null);
			
			if(o!=null){
				user   = (String)o[0]; //First element is the ERP system user
				Sysid  = (String)o[1]; //Second element is the id of the issuing system
				Client = (String)o[2]; //Third element is the client of the issuing system
				PrtUsr = (String)o[4]; //Portal user
				byte[] cert_;
				
				//The forth element is the certificate in byte
				//representation, to get its contents we need
				//to first convert it into a cert structure
				X509Certificate cert=null;
				if(o.length > 3){
					cert_ = (byte[]) o[3];
					CertificateFactory cf = CertificateFactory.getInstance("X.509");
					//cert = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(cert_));
				}else{
					cert = null;
				}  
			}else{
				log.error("ssoticket decrypt is null.");
				throw new BadCredentialsException("ssoticket decrypt is null.");
			}
			if(StringUtils.isEmpty(PrtUsr)){
				log.error("PrtUsr is null.");
				throw new BadCredentialsException("PrtUsr is null.");
			}
			BOSecurityUserDetail userDetail = new BOSecurityUserDetail();
			SysAdmin sysadmin = new SysAdmin();
			authenticationComponent.setLoginBoSiteId("BO");
        	String adminId = authenticationComponent.isLoginCheckEPSSO(PrtUsr);
        	if(StringUtils.isEmpty(adminId)){
				log.error("EP SSO Admin Id Is Empty!");
				throw new BadCredentialsException("EP SSO Admin Id Is Empty!");
        	}
			sysadmin.setAdminId(adminId);
			sysadmin.setShopId(PrtUsr);
        	//관리자 정보를 설정한다.
            userDetail.setSysAdmin(sysadmin);

			// Allow subclasses to set the "details" property
			authRequest = new UsernamePasswordAuthenticationToken(userDetail,null);
			setDetails(request, authRequest);
			Authentication result = this.getAuthenticationManager().authenticate(authRequest);
			if(result == null){
				throw new BadCredentialsException("Bad credentials!");
			}
			return result;
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception repositoryProblem) {
			log.error("EPLoginAuthenticationFilter failed", repositoryProblem);
			throw new AuthenticationServiceException("EPLoginAuthenticationFilter failed", repositoryProblem);
		}
	}

	protected String obtainEPTicket(HttpServletRequest request) {
		return request.getParameter(ssoTicketParameterName);
	}
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		request.getRequestDispatcher(targetUrl).forward(request, response);
	}
	protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}
}
