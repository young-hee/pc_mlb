package com.plgrim.ncp.framework.data;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ExcelFileResult implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1840072418055300259L;

	private Map<Integer, Object> excelMap;

	private Map<Integer, Object> excelErrMap;

	private List<String> ordList;

	private List<String> ordSkuNoList;

	private List<String> rfdList;

	private List<String> rfdSkuNoList;

	private List<String> dplPlgrimOrdList;

	private List<String> dplPlgrimRfdList;

	private Map<String, ArrayList<Integer>> groupkeyMap;

	private int requestCount;

	private int successCount;

	private int failCount;

	private File errFile;

	private String errFilePath;

	private String errFileName;

	private String errCloudFilePath;

	private List<Map<String, List<? extends Object>>> groupList;

	private List<? extends Object> allList;

	private String excelType;
	private String errMsg;
	private String successYn = "Y";
	private String resultCode;

	/* AB TEST, 미리보기용 revSn */
	private Long prvwRevSnPge;
	private Long abTestModRevSnPge;
}
