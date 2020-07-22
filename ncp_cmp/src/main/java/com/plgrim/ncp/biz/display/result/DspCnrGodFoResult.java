package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.biz.display.data.DspCtgryResultFoDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Created by user on 2016-08-12.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCnrGodFoResult extends AbstractResult {

    DspCtgryResultFoDTO dspCtgryGod;

    List<DspCnrFrResult> dspCtgryCnrList;

    String dspCtgryNo;
}
