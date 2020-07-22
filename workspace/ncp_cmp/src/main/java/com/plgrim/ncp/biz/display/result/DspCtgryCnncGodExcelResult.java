package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2017-05-11.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspCtgryCnncGodExcelResult extends AbstractResult {
    String dspCtgryNo;

    String dspCtgryNm;

    String godNo;

    String erpGodNo;

    String godNm;

    String failReson;
}
