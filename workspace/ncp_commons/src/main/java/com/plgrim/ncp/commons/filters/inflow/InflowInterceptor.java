package com.plgrim.ncp.commons.filters.inflow;

import com.plgrim.ncp.base.entities.datasource1.sys.SysInflow;
import com.plgrim.ncp.base.enums.SessionEnum;
import com.plgrim.ncp.commons.service.SysInflowService;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class InflowInterceptor implements HandlerInterceptor {

    @Autowired
    SysInflowService sysInflowService;

    @Autowired
    IdGenService idGenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String serviceId = ServletRequestUtils.getStringParameter(request, "inflow", "");
        HttpSession session = request.getSession();
        String orgComId = String.valueOf(session.getAttribute(String.valueOf(SessionEnum.ADVT_AFF_COM_ID)));

        if (StringService.isNotEmpty(serviceId) && !serviceId.equals(orgComId)) {
            /**
             * 유입 번호 설정
             */
            try {
                SystemPK systemPK = idGenService.getAutoGeneratorSystemPK(request); // System PK

                SysInflow inflowDTO = new SysInflow();
                inflowDTO.setAdvtAffComId(serviceId);
                inflowDTO.setMallId(systemPK.getMall());
                SysInflow sysInflow = sysInflowService.selectSysInflow(inflowDTO);

                session.setAttribute(String.valueOf(SessionEnum.INFLOW_SN), sysInflow.getInflowSn().toString());
                session.setAttribute(String.valueOf(SessionEnum.ADVT_AFF_COM_ID), serviceId);
            } catch (Exception e) {
                log.debug("inflow session setting error : {}", e);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
