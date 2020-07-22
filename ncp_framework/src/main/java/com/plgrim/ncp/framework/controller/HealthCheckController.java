package com.plgrim.ncp.framework.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.plgrim.ncp.framework.cloud.HealthCheckBean;

/**
 * system 처리 관련 controller
 * 
 * @author Chulhui Park <charles@plgrim.com>
 *
 */
@Controller
@RequestMapping("/system")
public class HealthCheckController {
	@Autowired
	private HealthCheckBean healthCheckBean;
	
	/**
     * print healthcheck data to json format
     * <br>
     * ELB와 같은 로드 밸런스에서 각 was의 정상 실행여부를 체크
     * 
     * @param HttpServletRequest
     * @param HttpServletResponse
     * @return return healthcheck data
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/healthcheck" }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public Map<String, String> healtcheck(HttpServletRequest request, HttpServletResponse response) {
        return healthCheckBean.healtcheck(request, response);
    }

}
