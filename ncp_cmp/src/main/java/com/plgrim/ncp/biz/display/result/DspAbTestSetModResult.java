package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.entities.datasource1.dsp.DspAbTestSetFlter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Alias("dspAbTestSetFlterResult")
public class DspAbTestSetModResult extends DspAbTestSetFlter {

    int modTurn;
    List<DspAbTestAnlResult> dspAbTestAnlResultList;

}
