package com.plgrim.ncp.biz.display.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.repository.dsp.DspPromtRepository;
import com.plgrim.ncp.framework.enums.DatabaseType;

@Repository
public class DisplayShoppableRepository extends DspPromtRepository {

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
	public List<DspVideo> selectShoppableVideoList() {
		return getSession1().selectList("com.plgrim.ncp.biz.display.shoppable.selectShoppableVideoList", null);
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
	public DspShoppableVideo selectShoppableVideoDetail(String videoId) {
		Map<String,String> paramData = new HashMap<String,String>();
		paramData.put("videoId", String.valueOf(videoId));
		
		return getSession1().selectOne("com.plgrim.ncp.biz.display.shoppable.selectShoppableVideoDetail", paramData);
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
	public int selectShoppableVideoTotalCount(){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.shoppable.selectShoppableVideoTotalCount", null);		
	}
	
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
	public long getVideoSn() throws Exception {
		long videoSn = getIdGenService().generateDBSequence(getSession1(), "SQ_DSP_VIDEO",
                DatabaseType.ORACLE).longValue();
		
		return videoSn;
	}
	
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
	public long getRelaVideoSn() throws Exception {
		long videoSn = getIdGenService().generateDBSequence(getSession1(), "SQ_RELATION_SN",
                DatabaseType.ORACLE).longValue();
		
		return videoSn;
	}
	/**
	 * Que Point SN 번호
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public long getQuePointSn() throws Exception {
		long videoSn = getIdGenService().generateDBSequence(getSession1(), "SQ_QUE_SN",
                DatabaseType.ORACLE).longValue();
		
		return videoSn;
	}
	/**
	 * Que Point SN 번호
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @return Long [설명]
	 * @throws Exception the exception
	 * @since 2015. 5. 28
	 */
	public long getGoodsSn() throws Exception {
		long videoSn = getIdGenService().generateDBSequence(getSession1(), "SQ_GOODS_SN",
                DatabaseType.ORACLE).longValue();
		
		return videoSn;
	}
	/**
	 * video 등록
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
	public int addShoppableVideo(DspShoppableVideo dspShoppableVideo){
		return getSession1().insert("com.plgrim.ncp.biz.display.shoppable.addShoppableVideo", dspShoppableVideo);		
	}
	*/
	/**
	 * video 갱신
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
	public int updateShoppableVideo(DspShoppableVideo dspShoppableVideo){
		return getSession1().insert("com.plgrim.ncp.biz.display.shoppable.updateShoppableVideo", dspShoppableVideo);		
	}
	*/
	/**
	 * 큐포인트 삭제
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoTitle [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	public int deleteShoppableQuePoint(Map<String,String> paramData){
		return getSession1().delete("com.plgrim.ncp.biz.display.shoppable.deleteShoppableQuePoint", paramData);		
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
	public List<DspShoppableQuePoint> selectShoppableQuePointList(DspShoppableQuePoint dspShoppableQuePoint){
		return getSession1().selectList("com.plgrim.ncp.biz.display.shoppable.selectShoppableQuePointList", dspShoppableQuePoint);		
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
	public int selectShoppableQuePointTotalCount(DspShoppableQuePoint dspShoppableQuePoint){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.shoppable.selectShoppableQuePointTotalCount", dspShoppableQuePoint);		
	}
	*/
	/**
	 * 상품 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspGoods [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int addShoppableQuePoint(DspShoppableQuePoint dspShoppableQuePoint){
		return getSession1().insert("com.plgrim.ncp.biz.display.shoppable.addShoppableQuePoint", dspShoppableQuePoint);		
	}
	*/
	/**
	 * 상품 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspGoods [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int updateShoppableQuePoint(DspShoppableQuePoint dspShoppableQuePoint){
		return getSession1().insert("com.plgrim.ncp.biz.display.shoppable.updateShoppableQuePoint", dspShoppableQuePoint);		
	}
	*/
	
	/**
	 * 비디오 좋아요 리스트
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspVideoLike> selectVideoLike(DspVideoLike dspVideoLike){
		return getSession1().selectList("com.plgrim.ncp.biz.display.shoppable.selectVideoLike", dspVideoLike);		
	}
	*/
	/**
	 * 비디오 좋아요 목록 총 Count
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int selectVideoLikeTotalCount(DspVideoLike dspVideoLike){
		return getSession1().selectOne("com.plgrim.ncp.biz.display.shoppable.selectVideoLikeTotalCount", dspVideoLike);		
	}
	*/
	/**
	 * 비디오 좋아요 등록
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int addVideoLike(DspVideoLike dspVideoLike){
		return getSession1().insert("com.plgrim.ncp.biz.display.shoppable.addVideoLike", dspVideoLike);		
	}
	*/
	/**
	 * 비디오 좋아요 취소
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public int addVideoLikeCancel(DspVideoLike dspVideoLike){
		return getSession1().update("com.plgrim.ncp.biz.display.shoppable.addVideoLikeCancel", dspVideoLike);		
	}
	*/
	/**
	 * 브랜드 전체 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	public List<String> selectAllBrand(Map<String,String> paramData){
		return getSession1().selectList("com.plgrim.ncp.biz.display.shoppable.selectAllBrand", paramData);		
	}
	
	/**
	 * 상품 전체 목록 조회
	 * 
	 * <p/>
	 * 
	 * [사용 방법 설명].
	 *
	 * @param dspVideoLike [설명]
	 * @return Int [설명]
	 * @since 2015. 9. 2
	 */
	/*
	public List<DspShoppableQuePoint> selectAllGoods(Map<String,String> paramData){
		return getSession1().selectList("com.plgrim.ncp.biz.display.shoppable.selectAllGoods", paramData);		
	}
	*/
}
