package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;

/**
 * Created by Jieun on 2016-09-22.
 */
@Data
public class MysizeDTO  extends AbstractDTO {

    private String mbrNo;

    private String weight;

    private String height;
    
    private String waist;
    
    private String body;

    /**
     * 회원 사이즈 분류 코드
     */
    private String mbrSizeClfcCd;
}
