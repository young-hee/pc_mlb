package com.plgrim.ncp.biz.vendor.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by user on 2015-12-11.
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SysStdOptResult extends AbstractResult {
    private String stdOptCd;                            // 표준 옵션 코드
    private String optNm;                               // 옵션 명
    private String stdOptValCd;                         // 표준 옵션 값 코드
    private String optValNm;                            // 옵션 값 명
    private String useYn;                               // 사용 여부
}
