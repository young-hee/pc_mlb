package com.plgrim.ncp.interfaces.crema.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value=Include.NON_NULL)
public class CremaUserDTO {

	private String callId;

	private String mallId;

	private List<String> user_codes;

	private List<String> failed_user_codes;

}