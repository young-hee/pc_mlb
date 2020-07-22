package com.plgrim.ncp.biz.callcenter.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;

@Data
@EqualsAndHashCode(callSuper=false)
@Alias("csoCnsltMemoExtendDTO")
public class CsoCnsltMemoExtendDTO extends CsoCnsltMemo {/**
	 * 
	 */
    private static final long serialVersionUID = 1639957035804659770L;

    private String regTrInfo;

}
