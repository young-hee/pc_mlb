package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoGodInq;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.repository.cso.CsoCnsltRepository;
import com.plgrim.ncp.biz.callcenter.result.MailAnsResult;
import com.plgrim.ncp.biz.callcenter.result.MailImgResult;
import com.plgrim.ncp.biz.callcenter.result.MailTemplateResult;
import com.plgrim.ncp.biz.callcenter.result.SelectNotMemberResult;


@Repository
public class MailTemplateRepository extends CsoCnsltRepository {

	public MailTemplateResult selectInqryInfo(String mtmInqSn) {
		
		MailTemplateResult mailTemplateResult = new MailTemplateResult();
		//문의 내용을 조회한다
		mailTemplateResult = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mailTemplate.selectInqryMailInfo", mtmInqSn);
		
		//답변 내용을 조회한다
		List<MailAnsResult> mailAnsResult = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mailTemplate.selectInqryMailAns", mtmInqSn);
		mailTemplateResult.setMailAns(mailAnsResult);
		
		//주문상품 이미지를 조회한다
		List<MailImgResult> mailImgResult = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mailTemplate.selectInqryMailImg", mtmInqSn);
		mailTemplateResult.setMailImg(mailImgResult);
		
		return mailTemplateResult;
	}
	
	public MailTemplateResult selectMemberGoodsQnaInfo(CsoGodInq csoGodInq) {
		
		MailTemplateResult mailTemplateResult = new MailTemplateResult();
		//상품QNA 문의 내용을 조회한다
		mailTemplateResult = getSession1().selectOne("com.plgrim.ncp.biz.callcenter.mailTemplate.selectMemberGoodsQnaMailInfo", csoGodInq.getGodInqSn());
		
		//상품QNA 답변 내용을 조회한다
		List<MailAnsResult> mailAnsResult = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mailTemplate.selectMemberGoodsQnaMailAns", csoGodInq.getGodInqSn());
		mailTemplateResult.setMailAns(mailAnsResult);
		
		//상품QNA 이미지를 조회한다
		List<MailImgResult> mailImgResult = getSession1().selectList("com.plgrim.ncp.biz.callcenter.mailTemplate.selectMemberGoodsQnaMailImg", csoGodInq);
		mailTemplateResult.setMailImg(mailImgResult);
		
		return mailTemplateResult;
	}
}