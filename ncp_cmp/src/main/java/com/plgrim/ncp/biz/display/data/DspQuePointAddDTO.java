package com.plgrim.ncp.biz.display.data;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

@Data
@EqualsAndHashCode(callSuper=false)
public class DspQuePointAddDTO extends AbstractDTO {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	private static final long serialVersionUID = 5890646694257941504L;
	
	//que point 리스트
	List<String> que_sn;
	List<String> video_sn;
	List<String> video_id;
	List<String> time;
	List<String> x;
	List<String> y;
	List<String> del_fg;
}
