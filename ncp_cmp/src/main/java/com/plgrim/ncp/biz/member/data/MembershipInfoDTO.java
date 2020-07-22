package com.plgrim.ncp.biz.member.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import lombok.Data;

/**
 * Created by user on 2016-08-30.
 */
@Data
public class MembershipInfoDTO extends AbstractDTO {
    Mbr mbr;
    String empYn = "N";
}
