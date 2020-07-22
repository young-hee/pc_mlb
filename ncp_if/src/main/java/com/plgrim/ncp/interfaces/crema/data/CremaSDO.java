package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class CremaSDO extends AbstractSDO {

	private static final long serialVersionUID = 1L;

	private String mallId;

	private String[] mallIds;

	private String searchStartDate;

	private String searchEndDate;

}