package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 상담 상태 코드
		 ㅁ. 상담의 처리여부는 처리상태로 관리한다
		 >. 처리 대기 : 상담 내용 입력 후 ‘처리대기’ 버튼으로 저장
		 >. 처리 완료 : 상담 내용 입력 후 ‘처리완료’ 버튼으로 저장

		 ㅁ. 상담 상태 : CNSLT_STAT
		 >. 처리 대기 : PRCS_WAIT
		 >. 임시 저장 : TMPR_SAVE
		 >. 처리 완료 : PROC_COMPT
		 */
		public enum cnsltStat {

			PRCS_WAIT, TMPR_SAVE, PROC_COMPT;

		}


	public enum mallId {
		DXM
	}


	/**
	 * 언어 코드
	 * ㅁ. 코드 테이블 규칙에 따라 대문자를 사용한 ISO 639 표준에 따른다
	 * <p/>
	 * ㅁ. 언어 : LANG
	 * >. 한국어 : KOR
	 * >. 중국어 : CHI
	 * >. 영어 : ENG
	 * >. 일본어 : JPN
	 */
	public enum langCd {
		KOR, CHI, ENG, JPN
	}

		private final String text;

		private CsoCnsltEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
