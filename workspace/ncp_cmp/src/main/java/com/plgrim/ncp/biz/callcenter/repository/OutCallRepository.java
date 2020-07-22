package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.biz.callcenter.data.OutCallDTO;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.biz.callcenter.data.OutCallGridDTO;
import com.plgrim.ncp.biz.callcenter.data.OutCallSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.OutCallResult;
import com.plgrim.ncp.framework.commons.IdGenService;


@Repository
@Slf4j
public class OutCallRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;


	public List<OutCallResult> selectOutCall(OutCallSearchDTO outCallSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.outcall.selectOutCall", outCallSearchDTO);
	}

	public void insertOutCall(OutCallGridDTO outCallGridDTO) {

		try {

			if(outCallGridDTO.getCnvrsPhone().length() <= 5){
				if(outCallGridDTO.getCnvrsPhone().length()  == 5){
					outCallGridDTO.setCnvrsTlofNo(outCallGridDTO.getCnvrsPhone().substring(0,1));
					outCallGridDTO.setCnvrsTlofWthnNo(outCallGridDTO.getCnvrsPhone().substring(1,5));
				}else if(outCallGridDTO.getCnvrsPhone().length()  == 4){
					outCallGridDTO.setCnvrsTlofWthnNo(outCallGridDTO.getCnvrsPhone());
				}

			}else{
				String[] phoneNum = makePhoneNumber(outCallGridDTO.getCnvrsPhone());

				if(phoneNum != null && !"".equals(phoneNum)){
					outCallGridDTO.setCnvrsNationNo(phoneNum[0]);
					outCallGridDTO.setCnvrsAreaNo(phoneNum[1]);
					outCallGridDTO.setCnvrsTlofNo(phoneNum[2]);
					outCallGridDTO.setCnvrsTlofWthnNo(phoneNum[3]);
				}
			}



			outCallGridDTO.setRegtrId(BOSecurityUtil.getLoginId());
			outCallGridDTO.setUdterId(BOSecurityUtil.getLoginId());

			Map<String, Object> conditions = Maps.newHashMap();

			int phonTurn = idGenService.generateDBOrder(getSession1(), "CSO_CHGCALL_PHON_DETL", "PHON_TURN", conditions, DatabaseType.ORACLE);
			//csoJobRequstOrdGod.setRequstOrdGodTurn(requstOrdGodTurn);

			outCallGridDTO.setPhonTurn(phonTurn);

			getSession1().insert("com.plgrim.ncp.biz.callcenter.outcall.insertOutCall", outCallGridDTO);
		} catch (Exception e) {
			log.error("",e);
		}

	}

	public void updateOutCall(OutCallGridDTO outCallGridDTO) {

		try {

			if(outCallGridDTO.getCnvrsPhone().length() <= 5){
				if(outCallGridDTO.getCnvrsPhone().length()  == 5){
					outCallGridDTO.setCnvrsTlofNo(outCallGridDTO.getCnvrsPhone().substring(0,1));
					outCallGridDTO.setCnvrsTlofWthnNo(outCallGridDTO.getCnvrsPhone().substring(1,5));
				}else if(outCallGridDTO.getCnvrsPhone().length()  == 4){
					outCallGridDTO.setCnvrsTlofWthnNo(outCallGridDTO.getCnvrsPhone());
				}

			}else{
				String[] phoneNum = makePhoneNumber(outCallGridDTO.getCnvrsPhone());

				if(phoneNum != null && !"".equals(phoneNum)){
					outCallGridDTO.setCnvrsNationNo(phoneNum[0]);
					outCallGridDTO.setCnvrsAreaNo(phoneNum[1]);
					outCallGridDTO.setCnvrsTlofNo(phoneNum[2]);
					outCallGridDTO.setCnvrsTlofWthnNo(phoneNum[3]);
				}
			}

			outCallGridDTO.setRegtrId(BOSecurityUtil.getLoginId());
			outCallGridDTO.setUdterId(BOSecurityUtil.getLoginId());

			getSession1().update("com.plgrim.ncp.biz.callcenter.outcall.updateOutCall", outCallGridDTO);
		} catch (Exception e) {
			log.error("",e);
		}
	}

	public void deleteOutCall(OutCallGridDTO outCallGridDTO) {

		try {
			String[] phoneNum = makePhoneNumber(outCallGridDTO.getCnvrsPhone());

			if(phoneNum != null && !"".equals(phoneNum) && phoneNum.length > 4){
				outCallGridDTO.setCnvrsNationNo(phoneNum[0]);
				outCallGridDTO.setCnvrsAreaNo(phoneNum[1]);
				outCallGridDTO.setCnvrsTlofNo(phoneNum[2]);
				outCallGridDTO.setCnvrsTlofWthnNo(phoneNum[3]);
			}else if(phoneNum.length <= 4){
				outCallGridDTO.setCnvrsTlofWthnNo(outCallGridDTO.getCnvrsPhone());
			}

			outCallGridDTO.setUdterId(BOSecurityUtil.getLoginId());

			getSession1().delete("com.plgrim.ncp.biz.callcenter.outcall.deleteOutCall", outCallGridDTO);
		} catch (Exception e) {
			log.error("",e);
		}

	}


	public int selectOutCallTotal(OutCallSearchDTO outCallSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.outcall.selectOutCallTotal", outCallSearchDTO);
	}

	public String[] makePhoneNumber(String phoneNum) throws Exception{



		String[] totalArr = new String[4];
		totalArr[0] = "82";

		if(phoneNum.indexOf("-") == -1){
			if(phoneNum.substring(0, 2) == "02"){
				if(phoneNum.length() == 10){
					totalArr[1] = phoneNum.substring(0,2);
					totalArr[2] = phoneNum.substring(2,6);
					totalArr[3] = phoneNum.substring(6,10);
				}else if(phoneNum.length() == 9){
					totalArr[1] = phoneNum.substring(0,2);
					totalArr[2] = phoneNum.substring(2,5);
					totalArr[3] = phoneNum.substring(5,9);
				}
			}else{
				if(phoneNum.length() == 11){
					totalArr[1] = phoneNum.substring(0,3);
					totalArr[2] = phoneNum.substring(3,7);
					totalArr[3] = phoneNum.substring(7,11);
				}
			}
		}else{
			String[] tempNum = phoneNum.split("-");
			for(int i = 0 ; i < tempNum.length; i++){
				totalArr[i + 1] = tempNum[i];
			}

		}

		return totalArr;
	}

	public List<OutCallResult> selectCtiOutCall() {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.outcall.selectCtiOutCall");
	}

	public long hasOutCallCheck(OutCallDTO outCallDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.outcall.hasOutCallCheck", outCallDTO);
	}
}