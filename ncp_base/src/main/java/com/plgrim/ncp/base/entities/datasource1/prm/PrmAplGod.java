/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.prm;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 프로모션 적용 상품
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("prmAplGod")
public class PrmAplGod extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 프로모션 번호
	 * ㅁ. PR || YYYYMMDD || 7자리 Cycle Sequence	 
	 */
	private String prmNo;


	/**
	 * 적용 순번	 
	 */
	private java.lang.Integer aplTurn;


	/**
	 * 상품 적용 여부
	 * ㅁ. Y 인 경우
	 *    >. 포함하고자하는 범위를 적용
	 * 
	 * ㅁ. N 인 경우
	 *    >. 전체 범위에서 제외하고자 하는 대상을 선택	 
	 */
	private String godAplYn;


	/**
	 * 적용 상품 구분 코드
	 * ㅁ. 적용 상품 구분 : APL_GOD_SECT
	 *    >. 전체 : ALL
	 *    >. 브랜드 : BRND
	 *    >. 전시 카테고리 : DSP_CTGRY
	 *    >. 상품 : GOD
	 *    >. 시즌 : SESON	 
	 */
	private String aplGodSectCd;


	/**
	 * 브랜드 ID
	 * ㅁ. 온라인에서 사용하는 브랜드 ID	 
	 */
	private String brndId;


	/**
	 * 전시 카테고리 번호
	 * 전시매장번호
	 * 전시매장코드(3)+대카(3)+중카(3)+소카(3)+ 세카(3) +세세카(3)
	 * 전시매장유형:Select Item, 브랜드매장	 
	 */
	private String dspCtgryNo;


	/**
	 * 상품 번호
	 * ㅁ. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence	 
	 */
	private String godNo;


	/**
	 * 시즌 코드
	 * ㅁ. 시즌 : SESON
	 *    >. 전체 : ALL
	 *    >. 2012 S/S : 2012SS
	 *    >. 2012 F/W : 2012FW
	 *    >. 2012 ALL : 2012ASS
	 *    >. 2013 S/S : 2013SS
	 *    >. 2013 F/W : 2013FW
	 *    >. 2013 ALL : 2013ASS
	 *    >. 2014 S/S : 2014SS
	 *    >. 2014 F/W : 2014FW
	 *    >. 2014 ALL : 2014ASS
	 *    >. 2015 S/S : 2015SS
	 *    >. 2015 F/W : 2015FW
	 *    >. 2015 ALL : 2015ASS
	 *    >. 2016 S/S : 2016SS
	 *    >. 2016 F/W : 2016FW
	 *    >. 2016 ALL : 2016ASS
	 *    >. 2017 S/S : 2017SS
	 *    >. 2017 F/W : 2017FW
	 *    >. 2017 ALL : 2017ASS
	 *    >. 2018 S/S : 2018SS
	 *    >. 2018 F/W : 2018FW
	 *    >. 2018 ALL : 2018ASS
	 * 
	 * ㅁ. 연도가 도래 할때 마다 추가 해주어야 함.
	 * ㅁ. 상품 체이블에서 사용되는 시즌코드랑 같은 값이 아니고 제조년도코드+시즌그룹코드를 합친 데이터이다.	 
	 */
	private String sesonCd;


	/**
	 * 등록자 ID	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}