package com.plgrim.ncp.interfaces.crema.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plgrim.ncp.base.abstracts.AbstractSDO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonInclude(value=Include.NON_EMPTY)
public class CremaCategorySDO extends AbstractSDO {

	private static final long serialVersionUID = -7008925163837657028L;

	@JsonProperty("access_token")
	private String accessToken;

	private String id;

	/** 카테고리의 code. 다른 카테고리와 중복되면 안됩니다. */
	private String code;

	/** 이름 */
	private String name;

	/** 상위 카테고리의 id */
	private String parent_category_id;

	/** 상위 카테고리의 code */
	@JsonProperty("parent_category_code")
	private String parentCategoryCode;

	/** 진열 여부, 진열인 경우 visible, 미진열인 경우 hidden */
	private String status;

}