package com.plgrim.ncp.biz.callcenter.repository;

import java.util.List;
import java.util.Map;

import com.plgrim.ncp.base.entities.datasource1.cso.CsoFaqAtchmnfl;
import com.plgrim.ncp.biz.callcenter.data.FaqGridDTO;
import com.plgrim.ncp.biz.callcenter.data.FaqSearchDTO;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.IdGenService;
import com.plgrim.ncp.framework.enums.DatabaseType;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.plgrim.ncp.biz.callcenter.data.FaqDTO;
import com.plgrim.ncp.biz.callcenter.result.FaqResult;
import com.plgrim.ncp.framework.data.SystemPK;


@Repository
public class FaqRepository extends AbstractRepository{

	@Autowired
	IdGenService idGenService;

	public List<FaqResult> selectListFaq(FaqSearchDTO faqSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.faq.selectListFaq", faqSearchDTO);
	}

	public long selectListFaqTotal(FaqSearchDTO faqSearchDTO) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.faq.selectListFaqTotal", faqSearchDTO);
	}

	public void updateFaqGrid(FaqGridDTO faqGridDTO) {
		faqGridDTO.setUdterId(BOSecurityUtil.getLoginId());
		getSession1().update("com.plgrim.ncp.biz.callcenter.faq.updateFaqGrid", faqGridDTO);
	}

	public void insertFaqWithGenSn(FaqDTO faqDTO) throws Exception {

		Long maxSn = Long.parseLong(idGenService.generateDBSequence(getSession1(), "SQ_CSO_FAQ", DatabaseType.ORACLE) + "");
		//csoCnslt.setCnsltSn(maxSn);
		faqDTO.setFaqSn(maxSn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.faq.insertFaq", faqDTO);
	}

	public void insertFaqAtchmnflWithGenTurn(CsoFaqAtchmnfl csoFaqAtchmnfl) throws Exception{

		Map<String, Object> conditions = Maps.newHashMap();
		conditions.put("FAQ_SN", csoFaqAtchmnfl.getFaqSn());

		int maxTurn = idGenService.generateDBOrder(getSession1(), "CSO_FAQ_ATCHMNFL", "FAQ_ATCHMNFL_TURN", conditions, DatabaseType.ORACLE);
		csoFaqAtchmnfl.setFaqAtchmnflTurn(maxTurn);

		getSession1().insert("com.plgrim.ncp.biz.callcenter.faq.insertFaqAtchmnfl", csoFaqAtchmnfl);
	}

	public FaqResult selectOneFaq(Long faqSn) {
		return getSession1().selectOne("com.plgrim.ncp.biz.callcenter.faq.selectOneFaq", faqSn);
	}


	/**
	 * 사용 TIP 이전글 다음글 조회.
	 *
	 * @param faqSn [설명]
	 * @return List [설명]
	 * @since 2015. 11. 6
	 */
	public List<FaqResult> selectPrevNxtTip(FaqSearchDTO faqSearchDTO) {
		return getSession1().selectList("com.plgrim.ncp.biz.callcenter.faq.selectPrevNxtTip", faqSearchDTO);
	}

	public void updateFaq(FaqDTO faqDTO) {
		getSession1().update("com.plgrim.ncp.biz.callcenter.faq.updateFaq", faqDTO);
	}

	public void deleteFaqGrid(FaqGridDTO faqGridDTO) {
		FaqDTO faqDto = new FaqDTO();
		faqDto.setFaqSn(faqGridDTO.getFaqSn());
		getSession1().delete("com.plgrim.ncp.biz.callcenter.faq.deleteFaqAtchmnfl", faqDto);
		getSession1().delete("com.plgrim.ncp.biz.callcenter.faq.deleteFaqGrid", faqGridDTO);
	}

	public void deleteFaqAtchmnfl(FaqDTO faqDTO) {
		getSession1().delete("com.plgrim.ncp.biz.callcenter.faq.deleteFaqAtchmnfl", faqDTO);
	}
}