package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.base.enums.CsTemplateEnum;
import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.data.CsTemplateSearchDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateGridDTO;
import com.plgrim.ncp.biz.callcenter.data.EmailSmsTemplateSearchDTO;
import com.plgrim.ncp.biz.callcenter.result.CsTemplateResult;
import com.plgrim.ncp.biz.callcenter.result.EmailSmsTemplateResult;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.plgrim.ncp.framework.page.PageParam;


@Repository
public class CsTemplateRepository extends AbstractRepository {

	@Autowired
	IdGenService idGenService;

	public void insertCsTemplatetWithGenSn(CsTemplateDTO csTemplateDTO) throws Exception {
		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_CNSLT_TMPLAT", DatabaseType.ORACLE) + "");
		csTemplateDTO.setCnsltTmplatSn(maxSn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.cstemplate.insertCsTemplate", csTemplateDTO);
		
	}

	public List<CsTemplateResult> selectCsTemplateList(CsTemplateSearchDTO csTemplateSearchDTO, PageParam pageParam) {
	    // TODO Auto-generated method stub
	    
		List<CsTemplateResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.cstemplate.selectCsTemplateList", csTemplateSearchDTO);
		
		for(int i = 0 ; i < list.size(); i++){
			
			String cnsltTmplatKndCd = list.get(i).getCnsltTmplatKndCd();
			String cnsltTmplatKndNm = "";
			
			if(cnsltTmplatKndCd != null && !"".equals(cnsltTmplatKndCd)){
				if(cnsltTmplatKndCd.equals(CsTemplateEnum.cnsltTmplatKndCd.CNSLT_ANS.toString())){
					cnsltTmplatKndNm = list.get(i).getInqTpNm() + " > " + list.get(i).getInqDetailTpNm();
				}else if(cnsltTmplatKndCd.equals(CsTemplateEnum.cnsltTmplatKndCd.MTM_INQ_ANS.toString())){
					cnsltTmplatKndNm = list.get(i).getMtmInqAnsTpNm() + " > " + list.get(i).getMtmInqAnsDetailTpNm();
				}else if(cnsltTmplatKndCd.equals(CsTemplateEnum.cnsltTmplatKndCd.TRANS_ANS.toString())){
					if(list.get(i).getChrgJobTpNm() == null && list.get(i).getTransRequstTpNm() == null){
						cnsltTmplatKndNm = list.get(i).getTransTgtNm();
					}else{
						cnsltTmplatKndNm = list.get(i).getTransTgtNm() + " > " + list.get(i).getChrgJobTpNm() + " > " + list.get(i).getTransRequstTpNm();
					}

				}
				
				list.get(i).setCnsltTmplatAnsTp(cnsltTmplatKndNm);
			}
			
		}
		
		return list; 
    }

	public long selectCsTemplateListTotal(CsTemplateSearchDTO csTemplateSearchDTO) {
	    // TODO Auto-generated method stub
	    return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.cstemplate.selectCsTemplateListTotal", csTemplateSearchDTO);
    }

	public void updateCsTemplateGrid(CsTemplateGridDTO csTemplateGridDTO) {
	    // TODO Auto-generated method stub
		
		csTemplateGridDTO.setUdterId(BOSecurityUtil.getLoginId());

		getSession1().update("com.plgrim.ncp.biz.callcenter.cstemplate.updateCsTemplateGrid", csTemplateGridDTO);
	    
    }
	
	public CsTemplateResult selectCsTemplateDetail(CsTemplateSearchDTO csTemplateSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.cstemplate.selectCsTemplateDetail", csTemplateSearchDTO);
    }
	
	public void updateCsTemplate(CsTemplateDTO csTemplateDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.cstemplate.updateCsTemplate", csTemplateDTO);
	}
	
	public void insertEmailSmsTemplateWithGenSn(EmailSmsTemplateDTO emailSmsTemplateDTO) throws Exception {
		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_SMS_EMAIL_TMPLAT", DatabaseType.ORACLE)+"");
		emailSmsTemplateDTO.setSmsEmailTmplatSn(maxSn);
		
		getSession1().insert("com.plgrim.ncp.biz.callcenter.cstemplate.insertEmailSmsTemplate", emailSmsTemplateDTO);
	}
	
	public void updateEmailsmsTemplate(EmailSmsTemplateDTO emailSmsTemplateDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.cstemplate.updateEmailsmsTemplate", emailSmsTemplateDTO);
	}

	public List<EmailSmsTemplateResult> selectEmailSmsTemplateList(EmailSmsTemplateSearchDTO emailSmsTemplateSearchDTO, PageParam pageParam) {
	    
		List<EmailSmsTemplateResult> list = getSession1().selectList("com.plgrim.ncp.biz.callcenter.cstemplate.selectEmailSmsTemplateList", emailSmsTemplateSearchDTO);
		
		for(int i = 0 ; i < list.size(); i++){
			String SmsEmailTmplatKndCd = list.get(i).getSmsEmailTmplatKndCd();
			String smsEmailTp = "";
			
			if(SmsEmailTmplatKndCd != null && !"".equals(SmsEmailTmplatKndCd)){
				if(CsTemplateEnum.smsEmailTmplatKndCd.SMS.toString().equals(SmsEmailTmplatKndCd)){
					smsEmailTp = list.get(i).getSmsClfcNm() + " > " + list.get(i).getSmsClfcDetailNm();
				}else if(CsTemplateEnum.smsEmailTmplatKndCd.EMAIL.toString().equals(SmsEmailTmplatKndCd)){
					smsEmailTp = list.get(i).getEmailClfcNm();
				}
				
				list.get(i).setSmsEmailTp(smsEmailTp);
			}
		}
	    return list;
    }
	
	public long selectEmailSmsTemplateListTotal(EmailSmsTemplateSearchDTO emailSmsTemplateSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.cstemplate.selectEmailSmsTemplateListTotal", emailSmsTemplateSearchDTO);
	}
	
	public void updateEmailSmsTemplateGrid(EmailSmsTemplateGridDTO emailSmsTemplateGridDTO) {
	    // TODO Auto-generated method stub
		
		emailSmsTemplateGridDTO.setUdterId(BOSecurityUtil.getLoginId());
		
		getSession1().update("com.plgrim.ncp.biz.callcenter.cstemplate.updateEmailSmsTemplateGrid", emailSmsTemplateGridDTO);	    
    }
	
	public EmailSmsTemplateResult selectEmailSmsTemplateDetail(EmailSmsTemplateSearchDTO emailSmsTemplateSearchDTO) throws Exception {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.cstemplate.selectEmailSmsTemplateDetail", emailSmsTemplateSearchDTO);
	}

	public long templateNameDuplicateCheck(EmailSmsTemplateDTO emailSmsTemplateDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.cstemplate.templateNameDuplicateCheck", emailSmsTemplateDTO);
	}
}