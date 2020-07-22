/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 7. 13       
 */
package com.plgrim.ncp.commons.repository;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvl;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysMall;
import com.plgrim.ncp.commons.data.GodEvlDTO;
import com.plgrim.ncp.commons.result.GodEvlResult;

/**
 * 상품평관리 Repository
 * @author ed
 *
 */
@Slf4j
@Repository
public class GodEvlRepository extends AbstractRepository {
	
	/**
	 * 상품평관리 목록 조회.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<GodEvlResult> selectGodEvlList( GodEvlDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.godevl.selectGodEvlList", paramData);
	}
	
	/**
	 * 상품평관리 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectGodEvlListCount( GodEvlDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.godevl.selectGodEvlListCount", paramData);
    }
	
	/**
	 * 상품평관리 목록 조회 엑셀.
	 *
	 * @param paramData [설명]
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<Map<String, Object>> selectGodEvlListExcel( GodEvlDTO paramData) throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.godevl.selectGodEvlListExcel", paramData);
	}
	
	/**
	 * 상품평관리 상세 조회.
	 *
	 * @param paramData [설명]
	 * @return Sys cmmn noti result
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public GodEvlResult selectGodEvlDetail( GodEvlDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.godevl.selectGodEvlDetail", paramData);
	}
	
	/**
	 * 상품평관리 첨부파일 등록.
	 *
	 * @param paramData [설명]
	 * @return int
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int insertGodGodEvlAtchFile(GodGodEvlAtchFile paramData) throws Exception {
		return getSession1().insert("com.plgrim.ncp.commons.godevl.insertGodGodEvlAtchFile", paramData);
	}
	
	/**
	 * 상품평관리 수정.
	 *
	 * @param paramData [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public int updateGodEvl(GodGodEvl paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.godevl.updateGodEvl", paramData);
    }
	
	/**
	 * 베스트상품평노출순서 변경
	 * 
	 * 20160526_주동민_sr#20343 [상품평 관리 화면 조회값 및 컬럼값 추가 요청]
	 * @param paramData
	 * @return
	 * @throws Exception
	 */
	public int updateGodEvlSortSeq(GodGodEvl paramData) throws Exception {
		return getSession1().update("com.plgrim.ncp.commons.godevl.updateGodEvlSortSeq", paramData);
    }
	/**
	 * 몰 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysMall> selectSysMallList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.godevl.selectSysMallList");
	}
	
	/**
	 * 브랜드 목록 조회.
	 *
	 * @return List [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public List<SysBrnd> selectSysBrndList() throws Exception {
		return getSession1().selectList("com.plgrim.ncp.commons.godevl.selectSysBrndList");
	}
	
	/**
	 * 상품평관리 베스트 목록 조회 개수.
	 *
	 * @param paramData [설명]
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 29
	 */
	public long selectBestGodEvlListCount( GodEvlDTO paramData) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.commons.godevl.selectBestGodEvlListCount", paramData);
    }
}





