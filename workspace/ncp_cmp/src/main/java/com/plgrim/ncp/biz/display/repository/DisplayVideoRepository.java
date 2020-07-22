/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      shsunhee.kim
 * @since       2015. 7. 20       
 */
package com.plgrim.ncp.biz.display.repository;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.repository.dsp.DspPromtRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;


/**
 * 쇼퍼블 비디오
 * 
 * <p>
 * 
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author shsunhee.kim
 * @since 2015. 6. 1
 */
@Repository
public class DisplayVideoRepository extends DspPromtRepository{

	

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
	/**
	 * video 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspVideo> selectVideoList() {
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectVideoList");
	}
	*/
	/**
	 * video 상세 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public DspVideo selectVideoDetail(Long videoSn) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("videoSn", String.valueOf(videoSn));
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoDetail", paramData);
	}
	*/
	/**
	 * video 회차 상세 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public DspVideo selectVideoEpisodeDetail(String dspVideoEpisode) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("dspVideoEpisode", dspVideoEpisode);
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoDetail", paramData);
	}
	*/
	/**
	 * video 다음 화 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public DspVideo selectVideoNext(Long videoSn) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("videoSn", String.valueOf(videoSn));
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoNext", paramData);
	}
	*/
	/**
	 * video 이전 화 상세 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @param pageParam [설명]
	 * @return Page [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public DspVideo selectVideoPrev(Long videoSn) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("videoSn", String.valueOf(videoSn));
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoBefore", paramData);
	}
	*/
	/**
	 * video 목록 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	public int selectVideoTotalCount(){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoTotalCount");		
	}
	/**
	 * 큐포인트 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspQuePoint> selectQueList(DspQuePoint dspQuePoint){
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectQueList", dspQuePoint);		
	}
	*/
	/**
	 * 큐포인트 목록 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int selectQueTotalCount(DspQuePoint dspQuePoint){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectQueTotalCount", dspQuePoint);		
	}
	*/
	/**
	 * 상품 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspGoods> selectGoodsList(DspQuePoint dspQuePoint){
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectGoodsList", dspQuePoint);		
	}
	*/
	/**
	 * 상품 목록 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int selectGoodsTotalCount(DspQuePoint dspQuePoint){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectGoodsTotalCount", dspQuePoint);		
	}
	*/
	/**
	 * 비디오 SN 번호
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public long getHistorySn() throws Exception {
		long historySn = getIdGenService().generateDBSequence(getSession1(), "SQ_HISTORY_SN",
                DatabaseType.ORACLE).longValue();
		
		return historySn;
	}
	/**
	 * 좋아요 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public DspVideoLike selectVideoLike(DspVideoLike dspVideoLike){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoLike", dspVideoLike);		
	}
	*/
	/**
	 * 좋아요 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int selectVideoLikeTotalCount(DspVideoLike dspVideoLike){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectVideoLikeTotalCount", dspVideoLike);		
	}
	*/
	/**
	 * 좋아요 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int addVideoLike(DspVideoLike dspVideoLike){
		return getSession1().insert("com.plgrim.ncp.biz.display.addVideoLike", dspVideoLike);		
	}
	*/
	/**
	 * 좋아요 등록 취소
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int addVideoLikeCancel(DspVideoLike dspVideoLike){
		return getSession1().insert("com.plgrim.ncp.biz.display.addVideoLikeCancel", dspVideoLike);		
	}
	*/
	/**
	 * 관련 비디오 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspRelationVideo> selectRelationVideo(DspRelationVideo dspRelationVideo){
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectRelationVideo", dspRelationVideo);		
	}
	*/
	/**
	 * 관련 비디오 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int selectRelationVideoTotalCount(DspRelationVideo dspRelationVideo){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.selectRelationVideoTotalCount", dspRelationVideo);		
	}
	*/
	/**
	 * 
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideo [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspGoods> selectFaceBookList(String video_id){
		HashMap<String, Object> sql_param = new HashMap<String, Object>();
		sql_param.put("video_id", video_id);
		return getSession1().selectList("com.plgrim.ncp.biz.display.selectFaceBookList", sql_param);		
	}
	*/
}
