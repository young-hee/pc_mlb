package com.plgrim.ncp.commons.service;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.plgrim.ncp.base.abstracts.AbstractService;
import com.plgrim.ncp.commons.data.ZipcodeDTO;
import com.plgrim.ncp.commons.repository.ZipcodeRepository;
import com.plgrim.ncp.commons.result.ZipcodeResult;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ZipcodeService extends AbstractService {
	
	@Autowired
	ZipcodeRepository zipcodeRepository;
	
	/**
	 * 우편번호 조회
	 *
	 * @param ZipcodeDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO) throws Exception {
		String srchKeyword = zipcodeDTO.getSrchKeyword();

		/**
		 * 읍/면/동 + 리 + 지번 검색을 위해 수정
		 */
		HashMap<String, String> keyworkds = getValue(srchKeyword);
		zipcodeDTO.setKeyword1(keyworkds.get("keyword1"));
		zipcodeDTO.setKeyword2(keyworkds.get("keyword2"));
		zipcodeDTO.setKeyword3(keyworkds.get("keyword3"));
		zipcodeDTO.setKeyword4(keyworkds.get("keyword4"));

		
		return zipcodeRepository.selectSearchZipcode(zipcodeDTO);
	}
	
	/**
	 * 우편번호 조회
	 *
	 * @param ZipcodeDTO [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 6
	 */
	public Page<ZipcodeResult> getSearchZipcode(ZipcodeDTO zipcodeDTO, PageParam pageParam) throws Exception {
		String srchKeyword = zipcodeDTO.getSrchKeyword();

		/**
		 * 읍/면/동 + 리 + 지번 검색을 위해 수정
		 */
		HashMap<String, String> keyworkds = getValue(srchKeyword);
		zipcodeDTO.setKeyword1(keyworkds.get("keyword1"));
		zipcodeDTO.setKeyword2(keyworkds.get("keyword2"));
		zipcodeDTO.setKeyword3(keyworkds.get("keyword3"));
		zipcodeDTO.setKeyword4(keyworkds.get("keyword4"));

		
		return zipcodeRepository.selectSearchZipcode(zipcodeDTO, pageParam);
	}
	
	private HashMap<String, String> getValue(String sText) {
		sText = sText.replaceAll(" ", "");
		String strTmp = sText.replaceAll("[0-9,\\-]+", "");

		if(StringService.isNotEmpty(strTmp)){
			int lastStrIndex = sText.lastIndexOf(strTmp.substring(strTmp.length() - 1, strTmp.length()));
			sText = sText.substring(0, lastStrIndex + 1) + " " + sText.substring(lastStrIndex + 1, sText.length());
		}

		sText = sText.replaceAll("-", " ").replaceAll("^ +| +$|( )+", "$1");

		HashMap<String, String> ret = new HashMap<String, String>();
		StringTokenizer mainTk = new StringTokenizer(sText, " ");
		int i = 1;

		while (mainTk != null && mainTk.hasMoreTokens()) {

			String mainKeyword = mainTk.nextToken();

			ret.put("keyword" +i, mainKeyword);
			i++;
		}

		return ret;
	}
}
