package com.plgrim.ncp.interfaces.sample.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Alias("interfaceSampleListSDO")
@Data
public class InterfaceSampleListSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;

    private String callerId;

    private List<InterfaceSampleSDO> sampleSDOList;
}
