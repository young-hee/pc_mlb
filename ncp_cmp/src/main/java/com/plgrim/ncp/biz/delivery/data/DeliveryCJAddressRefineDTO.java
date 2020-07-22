package com.plgrim.ncp.biz.delivery.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeliveryCJAddressRefineDTO extends AbstractDTO {

	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    
    
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
    
    /** CJ대한통운 집배권역 정보 */
    private String zipNo;		//우편번호      
    private String manBranId;	//관할점소      
    private String manBranNm;	//관할점소명    
    private String upBranId;	//상위점소      
    private String upBranNm;	//상위점소명    
    private String sidoAddr;	//시도주소      
    private String skkAddr;		//구군주소      
    private String dongAddr;	//동명주소      
    private String endNo;		//도착점코드    
    private String subEndNo;	//서브도착점코드
    private String endNm;		//도착점명      
    private String cldvEmpNm;	//집배사원명    
    private String ferryRgnYn;	//도선여부      
    private String airRgnYn;	//항공여부   
    
    /** CJ대한통운 주소정제 정보 */
    private String clntNum;          		//CJ대한통운 고객ID                               
    private String clntMgmCustCd;    		//CJ대한통운 고객관리거래처코드                   
    private String prngDivCd;        		//예약구분코드 : 일반 (01) / 반품 (02)            
    private String cgoSts;           		//(상품상태: 11(집화) / 91(배달))  
    private String address;          		//보내는분 주소                                   
    private String zipNum;           		//[주소정제] 송화인 우편번호
    private String zipId;            		//[주소정제] 송화인 우편번호ID		               
    private String oldAddress;       		//[주소정제] 수화인 지번주소                                            	                                   
    private String oldAddressDtl;    		//[주소정제] 수화인 지번주소 상세                                                                        
    private String newAddress;       		//[주소정제] 수화인 도로명주소                                          
    private String newAddressDtl;    		//[주소정제] 수화인 도로명주소 상세                                     
    private String etcAddr;          		//[주소정제] 수화인 기타주소 (도로명 주소일 경우 연계되는 지번주소 반환)                                            
    private String shortAddr;        		//[집배권역] 수화인 주소약칭
    private String clsfAddr;         		//[주소정제] 분류를 용이하게 하기 위한 주소약칭                         
    private String cllDlvBranCd;     		//[집배권역] 배달예정점소코드           
    private String cllDlvBranNm;     		//[집배권역] 배달예정점소명             
    private String cllDlcBranShortNm;		//[집배권역] 배달예정점소약칭           
    private String cllDlvEmpNum;     		//[집배권역] 배달예정사원번호           
    private String cllDlvEmpNm;      		//[집배권역] 배달예정사원명             
    private String cllDlvEmpNickNm;  		//[집배권역] 배달예정사원분류코드       
    private String clsfCd;           		//[집배권역] 배달터미널코드 (도착지코드)
    private String clsfNm;           		//[집배권역] 배달터미널명      
    private String subClsfCd;        		//[집배권역] 배달터미널 소분류코드               
    private String rspsDiv;          		//[집배권역] 전담여부
    private String newAddrYn;        		//[계약운임] 도로명주소여부
    private String errorCd;          		//[정제결과] 오류코드
    private String errorMsg;         		//[정제결과] 오류메세지
}
