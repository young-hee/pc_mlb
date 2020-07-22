package com.plgrim.ncp.biz.promotion.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.data.domain.Page;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.evt.Evt;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSnsReply;
import com.plgrim.ncp.base.entities.datasource1.evt.EvtSprtr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
@Data
@EqualsAndHashCode(callSuper=false)
public class EventFoDTO extends AbstractResult{

	/**
	 * 
	 */
    private static final long serialVersionUID = -387938399728489339L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 전시 이벤트 엔티티 */
	private Evt evt;

	private EventExtendsFoDTO eventExt;

	/** 출석이벤트 참여 목록 */
	private List<Integer> attendList;

	/** 경품 목록 */
	private List<EventFreeGiftTurnExtendsFoDTO> freeGiftTurnList;

	/** 이벤트 목록 */
	private List<EventExtendsFoDTO> eventList;

	/** 연관 상품 목록 */
	private List<GoodsListFoDTO> goodsList;

	/** 구분타이틀 목록 */
	private List<EvtSprtr> sprtrList;
	
	/** SNS 댓글 목록 */
	private Page<EvtSnsReply> snsReplyList;
	
	/** 주문 목록 */
	private List<Ord> ordList = new ArrayList<Ord>();
	
	/** 응모여부 */
	private String evtApplYn;
	
	private List<EvtSprtrExt> sprtrExtList;
	
	private List<EvtSprtrExt> textList;
	
	private List<EvtSprtrExt> imgList;
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

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
