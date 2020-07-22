package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.biz.display.data.DspFinderBoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsFinderErrorResult extends AbstractResult {

    private static final long serialVersionUID = -2752248806043804337L;

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	List<DspFinderBoResult> noRegisterGod = new ArrayList<>(); //삭제할수 없는 품번
	List<DspFinderBoResult> changeUseYnGod = new ArrayList<>();//삭제는 되지만 사용여부 변경 상품




}
