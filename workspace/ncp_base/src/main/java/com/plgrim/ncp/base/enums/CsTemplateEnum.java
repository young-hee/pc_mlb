package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsTemplateEnum {


	// GOODS_CONSTANTS
	YES("Y"), NO("N");

	/**
	 * 상담 템플릿 종류 코드
	 *
	 * CNSLT_ANS : 상담 답변 템플릿
	 * MTM_INQ_ANS : 1:1 문의 답변 템플릿
	 * TRANS_ANS : 이관 답변 템플릿
	 *
	 */
	public enum cnsltTmplatKndCd {

		CNSLT_ANS, MTM_INQ_ANS, TRANS_ANS;

	}

	/**
	 *이관 대상 코드
	 ㅁ. 상담 이관 대상 : 상담을 타 업체, 업무협업, 상담사 에게 상담을 이관 하는 행위의 구분

	 ㅁ. 이관 대상 : TRANS_TGT
	 >. 상담원 이관 : CNSLR_TRANS
	 >. 업체 이관 : COM_TRANS
	 >. 업무 이관 : JOB_TRANS
	 */

	public enum transTgtCd{
		CNSLR_TRANS, COM_TRANS, JOB_TRANS;
	}

	public enum smsEmailTmplatKndCd{
		SMS, EMAIL;
	}


	private final String text;
	private CsTemplateEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
