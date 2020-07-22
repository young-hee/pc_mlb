package com.plgrim.ncp.commons.auth;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdmin;
import com.plgrim.ncp.base.entities.datasource1.sys.SysShop;
import com.plgrim.ncp.base.repository.com.ComRepository;
import com.plgrim.ncp.base.repository.sys.SysShopRepository;
import com.plgrim.ncp.commons.AuthenticationComponent;
import com.plgrim.ncp.commons.CommonCommandComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.enums.SecurityParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * BO 전용 인증 프로바이더
 */
@Slf4j
public class BOAuthenticationProvider implements AuthenticationProvider {


    @Autowired
    CommonCommandComponent commandComponent;

    @Autowired
    AuthenticationComponent authenticationComponent;

    @Autowired
    IOService ioService;

    @Autowired
    IdGenService idGenService;

    @Autowired
    ComRepository comRepository;

    @Autowired
    SysShopRepository sysShopRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        Authentication auth = null;
        BOSecurityUserDetail userDetail = null;

        try {

            HttpServletRequest request = ContextService.getCurrentRequest();
            String adminId = request.getParameter("userId");
            String adminPwd = request.getParameter("password");
            String boSiteId = request.getParameter("boSiteId");
            boolean isBoSite = BOSecurityUtil.isBoSite();

            String reqUri = ContextService.getCurrentRequest().getRequestURI();

            boolean isEPLogin = false;
            if (("/epsso").equals(reqUri)) {
                userDetail = (BOSecurityUserDetail) authentication.getPrincipal();
                String stk = request.getParameter("epTicket");
                if (StringUtils.isEmpty(stk) || StringUtils.isEmpty(userDetail.getSysAdmin().getAdminId()) || StringUtils.isEmpty(userDetail.getSysAdmin().getShopId())) {
                    return auth;
                }
                adminId = userDetail.getSysAdmin().getAdminId();
                boSiteId = "BO";
                isEPLogin = true;
            }

            //세션 운영자상태 초기화
            if (request.getSession(false) != null) {
                request.getSession().setAttribute("adminStatus", "");
            }

            authenticationComponent.setLoginBoSiteId(boSiteId);

            if (isEPLogin || authenticationComponent.isLoginCheck(adminId, adminPwd)) { //로그인 체크
                /**
                 * BO Site
                 */
                if (BOSecurityUtil.isBoSite()) {

                    userDetail = new BOSecurityUserDetail();

                    //관리자 정보를 설정한다.
                    userDetail.setSysAdmin(authenticationComponent.selectRowSysAdmInfo(adminId));

                    if (StringService.isNotEmpty(userDetail.getSysAdmin().getComId())) {
                        Com com = new Com();
                        com.setComId(userDetail.getSysAdmin().getComId());
                        userDetail.setCom(comRepository.selectCom(com));
                    }

                    if (StringService.isNotEmpty(userDetail.getSysAdmin().getShopId())) {
                        SysShop sys = new SysShop();
                        sys.setShopId(userDetail.getSysAdmin().getShopId());
                        userDetail.setSysShop(sysShopRepository.selectSysShop(sys));
                    }

                    if ("APRV_COMPT".equals(userDetail.getSysAdmin().getAdminStatCd())) {//관리자 승인완료 상태
                        //관리자 권한정보를 설정한다.
                        userDetail.setAuthUrlAct(authenticationComponent.selectAdmAuthUrlAct(adminId));
                        //관리자 권한정보를 설정한다.(동적메뉴)
                        userDetail.setDynamicAuthUrlAct(authenticationComponent.selectAdmDynamicAuthUrlAct(adminId));
                        //관리자 권한정보를 설정한다.(full url 권한체크 메뉴)
                        userDetail.setAuthCheckUrlAct(authenticationComponent.selectAdmAuthCheckUrlAct());

                        //BO, CS 개인정보 취급자 책임과 의무 동의여부
                        userDetail.setStplatAgrYn(authenticationComponent.getStplatAgrYn(adminId, "PSNL_INFO_TRTR_RSPNSBL_DUTY"));

                        //관리자 role부여한다.
                        List<GrantedAuthority> grantedAuths = new ArrayList<>();
                        grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.DEFAULT_ROLE.toString()));
                        grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.ADMIN_ROLE.toString()));

                        //접근된 사이트 ID를 세션에 담는다.
                        userDetail.setAccessSiteId(authenticationComponent.getBoSiteId());

                        //로그인 시간
                        userDetail.setLoginDt(Calendar.getInstance().getTime());

                        // 즐겨찾기 목록
                        userDetail.setBookmarkList(authenticationComponent.selectAdminBookmarkList(adminId));

                        // userDetail.setAuthBrndList(authBrndList);

                        //담당 몰 정보 설정  ( 데이타 제안 권한 설정때문에 추가됨)
                        userDetail.setAuthMallList(authenticationComponent.selectListAuthAdmMall(userDetail.getSysAdmin()));

                        //담당브랜드 정보 설정  ( 데이타 제안 권한 설정때문에 추가됨)
                        userDetail.setAuthBrndList(authenticationComponent.selectAuthBrndList(userDetail.getSysAdmin()));

                        //PO 담당브랜드 정보 설정  ( PO 데이타 제안 권한 설정때문에 추가됨)
                        userDetail.setPoAuthBrndList(authenticationComponent.selectPoAuthBrndList(userDetail.getSysAdmin()));



                        try {
                            //상단 메뉴 목록
                            userDetail.setTopMenuList(authenticationComponent.getTopMenuList(adminId));
                        } catch (NullPointerException ne) {
                            log.error("SYS_MENU data error! NullPointerException");
                        }

                        auth = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);

                    } else {//관리자 승인완료 상태가 아닌경우
                        request.getSession().setAttribute("adminStatus", userDetail.getSysAdmin().getAdminStatCd());
                    }
                    //==============================================================
                    //PO Site
                    //==============================================================
                } else {

                    userDetail = new BOSecurityUserDetail();

                    //관리자 정보를 설정한다.
                    userDetail.setSysAdmin(authenticationComponent.selectRowSysAdmInfo(adminId));
                    userDetail.setPoSysAdmin(userDetail.getSysAdmin());

                    if (StringService.isNotEmpty(userDetail.getPoSysAdmin().getComId())) {
                        Com com = new Com();
                        com.setComId(userDetail.getPoSysAdmin().getComId());
                        userDetail.setPoCom(comRepository.selectCom(com));
                    }

                    if (StringService.isNotEmpty(userDetail.getPoSysAdmin().getShopId())) {
                        SysShop sys = new SysShop();
                        sys.setShopId(userDetail.getPoSysAdmin().getShopId());
                        userDetail.setPoSysShop(sysShopRepository.selectSysShop(sys));
                    }

                    if ("APRV_COMPT".equals(userDetail.getPoSysAdmin().getAdminStatCd())) {//관리자 승인완료 상태
                        //관리자 권한정보를 설정한다.
                        userDetail.setPoAuthUrlAct(authenticationComponent.selectAdmAuthUrlAct(adminId));
                        //관리자 권한정보를 설정한다. (동적메뉴)
                        userDetail.setPoDynamicAuthUrlAct(authenticationComponent.selectAdmDynamicAuthUrlAct(adminId));
                        //관리자 권한정보를 설정한다.(full url 권한체크 메뉴)
                        userDetail.setPoAuthCheckUrlAct(authenticationComponent.selectAdmAuthCheckUrlAct());


                        //BO, CS 개인정보 취급자 책임과 의무 동의여부
                        userDetail.setPoStplatAgrYn(authenticationComponent.getStplatAgrYn(adminId, "PSNL_INFO_TRTR_RSPNSBL_DUTY"));

                        //관리자 role부여한다.
                        List<GrantedAuthority> grantedAuths = new ArrayList<>();
                        grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.DEFAULT_ROLE.toString()));
                        grantedAuths.add(new SimpleGrantedAuthority(SecurityParam.ADMIN_ROLE.toString()));

                        //접근된 사이트 ID를 세션에 담는다.
                        userDetail.setPoAccessSiteId(authenticationComponent.getBoSiteId());

                        //로그인 시간
                        userDetail.setPoLoginDt(Calendar.getInstance().getTime());

                        // 즐겨찾기 목록
                        userDetail.setPoBookmarkList(authenticationComponent.selectAdminBookmarkList(adminId));

                        // userDetail.setAuthBrndList(authBrndList);

                        //담당브랜드 정보 설정  ( 데이타 제안 권한 설정때문에 추가됨)
                        userDetail.setPoAuthBrndList2(authenticationComponent.selectAuthBrndList(userDetail.getPoSysAdmin()));

                        //PO 담당브랜드 정보 설정  ( PO 데이타 제안 권한 설정때문에 추가됨)
                        userDetail.setPoPoAuthBrndList2(authenticationComponent.selectPoAuthBrndList(userDetail.getPoSysAdmin()));

                        try {
                            //상단 메뉴 목록
                            userDetail.setPoTopMenuList(authenticationComponent.getTopMenuList(adminId));
                        } catch (NullPointerException ne) {
                            log.error("SYS_MENU data error! NullPointerException");
                        }

                        auth = new UsernamePasswordAuthenticationToken(userDetail, null, grantedAuths);

                    } else {//관리자 승인완료 상태가 아닌경우
                        request.getSession().setAttribute("adminStatus", userDetail.getPoSysAdmin().getAdminStatCd());
                    }

                }

                request.getSession().setAttribute("loginFailCnt", 0);

            } else {

                log.info("=================================================");
                log.info("log-in fail log...");
                log.info("=================================================");
                log.info("Module :: " + (BOSecurityUtil.isBoSite() ? "BO" : "PO"));
                log.info("isLogin :: " + BOSecurityUtil.isLogin());
                log.info("hasRole :: " + BOSecurityUtil.hasRole());
                log.info("getCurrentUserDetail :: " + BOSecurityUtil.getCurrentUserDetail());
                log.info("SecurityContextHolder: {}", SecurityContextHolder.getContext());
                log.info("SecurityContextHolder authenticatiion: {}", SecurityContextHolder.getContext() == null ? null : SecurityContextHolder.getContext().getAuthentication());
                log.info("SecurityContextHolder authenticatiion.getPrincipal(): {}", SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                log.info("SecurityContextHolder authenticatiion.getCredentials(): {}", SecurityContextHolder.getContext().getAuthentication() == null ? null : SecurityContextHolder.getContext().getAuthentication().getCredentials());
                log.info("ID :: " + adminId);
                log.info("PWD :: " + adminPwd);
                log.info("BoSiteId :: " + boSiteId);

                //ID 존재 시 로그인 실패횟수 구하기
                SysAdmin sysAdmin = null;
                if (BOSecurityUtil.isBoSite()) {
                    sysAdmin = authenticationComponent.selectBoLoginIdInfo(adminId);
                } else {
                    sysAdmin = authenticationComponent.selectPoLoginIdInfo(adminId);
                }
                request.getSession().setAttribute("loginFailCnt", 0);
                if (sysAdmin == null) {
                    request.getSession().setAttribute("loginFailCnt", 1);
                } else {
                    Integer failCnt = (Integer) sysAdmin.getLoginFailrCount();
                    log.info("failCnt :: " + failCnt);
                    request.getSession().setAttribute("loginFailCnt", failCnt);
                    if (failCnt == 5) {    //비밀번호 초기화
                        String mbrPw = IdGenService.generateTempPassword();
                        String encMbrPw = IdGenService.generateSHA256(mbrPw);
                        authenticationComponent.updateFailrCnt5Init(adminId, mbrPw, encMbrPw);
                        log.info("password initilized...");
                    }
                }
                log.info("=================================================");
            }

        } catch (Exception ex) {
            log.error("{}", ex);
            throw new AuthenticationServiceException("Credentials could not be verified", ex);

        }

        //인증이 실패할 경우 null을 리턴 한다.
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
