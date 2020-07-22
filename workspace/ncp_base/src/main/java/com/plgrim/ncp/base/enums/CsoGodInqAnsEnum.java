package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoGodInqAnsEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");


	/**
	 *
	 ㅁ. 답변 상태 : ANS_STAT
	 >. 답변 대기 : ANS_WAIT
	 >. 확인 중 : CNFIRM_PROGRS
	 >. 답변 완료 : ANS_COMPT';
	 *
	 *
	 */

	public enum detailAnsStatCd {

		ANS_BF, ANS_WAIT, ANS_COMPT;

	}

	private final String text;

	private CsoGodInqAnsEnum(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
