package com.plgrim.ncp.base.enums;

/**
 * 
 * 업무 이관 Enum
 * 
 */
public enum CsoCnsltTmplatEnum {


	// GOODS_CONSTANTS
		YES("Y"), NO("N");

		/**
		 * 상담 템플릿 종류 코드
		 * ㅁ. 상담 템플릿 종류 : CNSLT_TMPLAT_KND
		 * >. 상담 답변 : CNSLT_ANS
		 * >. 1:1문의 답변 : MTM_INQ_ANS
		 * >. 이관 답변 : TRANS_ANS
		 */
		public enum cnsltTmplatKnd {

			CNSLT_ANS, MTM_INQ_ANS, TRANS_ANS;

		}

		/**
		 * 사용 여부
		 * Y, N
		 */
		public  enum useYn{
			Y, N;
		}


		private final String text;

		private CsoCnsltTmplatEnum(final String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}

}
