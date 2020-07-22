package com.plgrim.ncp.web.pc.mlb.crema;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.plgrim.ncp.interfaces.crema.adapter.CremaAdapter;
import com.plgrim.ncp.interfaces.crema.data.CremaUserDTO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/crema")
public class CremaMlbController {

	@Autowired
	private CremaAdapter cremaAdapter;

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/updateCremaMlbUser", method={RequestMethod.POST})
	public String postCremaMlbUser(HttpServletRequest request, @RequestBody CremaUserDTO cremaUserDTO) throws Exception {

		AdapterHeader adapterHeader = new AdapterHeader();

		adapterHeader.setRequestId(UUID.randomUUID().toString().replace("-", ""));
		adapterHeader.setMallId("MBM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("PC");

		cremaUserDTO.setMallId("MBM");
		cremaUserDTO.setCallId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());

		return cremaAdapter.updateCremaUser(cremaUserDTO, adapterHeader);

	}

	/**
	 * SMS 수신여부 동기화
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value="/updateMlbRecptnAgr", method={RequestMethod.POST})
	public String updateMlbRecptnAgr(HttpServletRequest request, @RequestBody CremaUserDTO cremaUserDTO) throws Exception {

		AdapterHeader adapterHeader = new AdapterHeader();

		adapterHeader.setRequestId(UUID.randomUUID().toString().replace("-", ""));
		adapterHeader.setMallId("MBM");
		adapterHeader.setLangCd("KOR");
		adapterHeader.setDvcCd("API");

		cremaUserDTO.setMallId("MBM");
		cremaUserDTO.setCallId(this.getClass().getSimpleName() + this.getClass().getDeclaredMethods()[0].getName());

		return cremaAdapter.updateRecptnAgr(cremaUserDTO, adapterHeader);

	}

}