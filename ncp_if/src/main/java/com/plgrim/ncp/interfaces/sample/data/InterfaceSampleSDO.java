package com.plgrim.ncp.interfaces.sample.data;

import com.plgrim.ncp.base.abstracts.AbstractSDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.Alias;

@EqualsAndHashCode(callSuper = false)
@Alias("interfaceSampleSDO")
@Data
public class InterfaceSampleSDO extends AbstractSDO {

    private static final long serialVersionUID = 1L;


    /**
     * Caller Class Name
     */
    private String callerId;

    /**
     * 결과코드
     */
    private String resultCode;
    /**
     * 결과 메시지
     */
    private String resultContent;

    /**
     * Sample ETC1
     */
    private String ETC1;

    /**
     * Sample ETC2
     */
    private String ETC2;

    /**
     * Sample ETC3
     */
    private String ETC3;

    /**
     * Sample ETC4
     */
    private String ETC4;

    /**
     * Sample ETC5
     */
    private String ETC5;

}
