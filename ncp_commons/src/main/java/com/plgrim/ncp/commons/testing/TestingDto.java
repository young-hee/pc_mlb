package com.plgrim.ncp.commons.testing;


import com.plgrim.ncp.base.abstracts.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestingDto extends AbstractEntity {
	private static final long serialVersionUID = -1713787383929865455L;
	String partitionNo;
	Integer line;
	String type;
	Integer status;
}