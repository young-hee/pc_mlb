package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndImg;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2016-09-08.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class DspBrndImgResult extends AbstractResult {
    private SysBrnd sysBrnd;
    private SysBrndImg sysBrndImg1;
    private SysBrndImg sysBrndImg2;
    private SysBrndImg sysBrndImg3;
    private SysBrndImg sysBrndImg4;
}
