package com.plgrim.ncp.commons.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QdlvDTO extends AbstractDTO {
	private static final long serialVersionUID = 5836627492662374731L;

	/**
	 * 업체아이디
	 */
	private String comId;
}
