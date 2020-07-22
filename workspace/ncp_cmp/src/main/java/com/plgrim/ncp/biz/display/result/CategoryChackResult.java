package com.plgrim.ncp.biz.display.result;

import com.plgrim.ncp.base.abstracts.AbstractResult;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class CategoryChackResult extends AbstractResult{


	String men;

	String women;

	String kids;

	int ctgrycount;

}
