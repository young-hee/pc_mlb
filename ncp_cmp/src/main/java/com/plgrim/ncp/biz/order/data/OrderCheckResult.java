package com.plgrim.ncp.biz.order.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodExtends;
import com.plgrim.ncp.commons.data.AbstractOrderDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@JsonInclude(value=Include.NON_EMPTY)
@EqualsAndHashCode(callSuper = false)
public class OrderCheckResult extends AbstractOrderDTO {

	List<OrdGodExtends> ordGodExtendsList;

}
