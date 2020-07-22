/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.mbr;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.apache.ibatis.type.Alias;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

/**
 * 회원 배송지
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Alias("mbrDlvsp")
public class MbrDlvsp extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 회원 번호
	 * ㅁ. 회원 가입시 부여되는 고유한 관리 번호
	 *    >. MB || YYYYMMDD || 7자리 Cycle	 
	 */
	private String mbrNo;


	/**
	 * 배송 주소록 순번
	 * ㅁ. 회원별 주소록의 MAX값을 계산하여 발급하도록 한다.
	 *     >. "회원 배송지 정보.주소록 순번"의 MAX값 + 1로 등록 되도록 한다.
	 *    >. 숫자로 최대 5자리 " 99999" 로 관리 한다.
	 *     (ORACLE 기준)	 
	 */
	private java.lang.Integer dlvAdbukTurn;


	/**
	 * 주소록 명
	 * ㅁ. 회원의 주소의 사용용도에 대한 이름
	 * 
	 * ㅁ. 회원의 단순입력으로 관리 함	 
	 */
	private String adbukNm;


	/**
	 * 수취인 명
	 * ㅁ. 회원의 주문자 또는 수령자에 대한 이름을 관리 함	 
	 */
	private String addrseNm;


	/**
	 * 기본 배송지 여부
	 * ㅁ. 구매한 상품의 주소지 여부
	 *    >. 기본배송지 : Y
	 *    >. (일반) 배송지 : N	 
	 */
	private String baseDlvspYn;


	/**
	 * 배송 주소 구분 코드
	 * ㅁ. 기존 지번주소 체계인지, 도로명 주소인지 구분하기 위함
	 * 
	 * ㅁ. 주소 구분 : ADDR_SECT
	 *    >. 도로명주소 : RD_ADDR
	 *    >. 지번주소 : LNM_ADDR
	 *    >. 해외주소 : OVSEA_ADDR	 
	 */
	private String dlvAddrSectCd;


	/**
	 * 국가 코드
	 * ㅁ. 물품의 배송 대상 국가로써 관리 함
	 *    >. ISO 3166-1 기준
	 * 
	 * ㅁ, 국가 코드 : NATION
	 *    >. 국가별 상세코드는 ISO 3166-1 기준로 등록 관리 됨	 
	 */
	private String nationCd;


	/**
	 * 우편번호
	 * ㅁ. 신규 우편번호 적용을 고려하여 6자리로 적용 관리 함	 
	 */
	private String postNo;


	/**
	 * 기본주소
	 * ㅁ. 우편 번호로 확인 되는 주소로 기본주소의 의미를 가짐.	 
	 */
	private String baseAddr;


	/**
	 * 상세주소
	 * ㅁ. 회원의 개별적 지번, 건물의 층수 등을 의미 함.	 
	 */
	private String detailAddr;


	/**
	 * 도시 명
	 * 해외 배송시 입력하는 도시 명	 
	 */
	private String ctyNm;


	/**
	 * 지방 명
	 * 해외 배송시 입력하는 주(state)/지방(province) 등 지역 명	 
	 */
	private String lcltyNm;


	/**
	 * 전화 국가번호	 
	 */
	private String telNationNo;


	/**
	 * 전화 지역번호	 
	 */
	private String telAreaNo;


	/**
	 * 전화 국번호	 
	 */
	private String telTlofNo;


	/**
	 * 전화 국내번호	 
	 */
	private String telTlofWthnNo;


	/**
	 * 휴대전화 국가번호	 
	 */
	private String mobilNationNo;


	/**
	 * 휴대전화 지역번호	 
	 */
	private String mobilAreaNo;


	/**
	 * 휴대전화 국번호	 
	 */
	private String mobilTlofNo;


	/**
	 * 휴대전화 국내번호	 
	 */
	private String mobilTlofWthnNo;


	/**
	 * 배송 이메일
	 * ㅁ. 회원 주소록의 회원이름에 대한 이메일 주소를 관리 함
	 *    >. 상품 배송시 배송 상태를 고객에게 알리기 위한 이멜일 주소로 정의 함	 
	 */
	private String dlvEmail;


	/**
	 * 배송 비고
	 * ㅁ. 배송시 특기 사항에 대한 내용을 관리 함	 
	 */
	private String dlvRm;


	/**
	 * 등록자 ID
	 * 등록한 관리자 번호	 
	 */
	private String regtrId;


	/**
	 * 등록 일시	 
	 */
	private java.util.Date regDt;


	/**
	 * 수정자 ID
	 * 수정한 관리자 번호	 
	 */
	private String udterId;


	/**
	 * 수정 일시	 
	 */
	private java.util.Date udtDt;

	
}