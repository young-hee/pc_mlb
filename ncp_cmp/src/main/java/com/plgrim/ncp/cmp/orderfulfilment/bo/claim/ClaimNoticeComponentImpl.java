/* Copyright (c) 2015 plgrim, Inc.
 * All right reserved.
 * http://plgrim.com
 * This software is the confidential and proprietary information of plgrim
 * , Inc. You shall not disclose such Confidential Information and
 * shall use it only in accordance with the terms of the license agreement
 * you entered into with plgrim.
 *
 * ------------------------------------------------------------------------
 * @author      jwcale.kim
 * @since       2015. 4. 17
 */
package com.plgrim.ncp.cmp.orderfulfilment.bo.claim;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmWrhsGodExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.ord.Ord;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.base.repository.mbr.MbrRepository;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.repository.OrderSelectRepository;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.ConfigService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.interfaces.email.adapter.EmailAdapter;
import com.plgrim.ncp.interfaces.email.data.EmailHtmlSDO;
import com.plgrim.ncp.interfaces.mpush.adapter.MPushAdapter;
import com.plgrim.ncp.interfaces.mpush.data.MPushAlimTalkSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Transactional(value = "transactionManager", propagation=Propagation.REQUIRES_NEW)
@Component
public class ClaimNoticeComponentImpl extends AbstractComponent implements ClaimNoticeComponent {
	
	@Autowired
	private ConfigService configService;
	
	@Autowired
	private InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private MailHtmlReaderService mailHtmlReaderService;

	@Autowired
	private MbrRepository mbrRepository;
	
	@Autowired 
	private MemberActivityFOComponent memberActivityFOComponent;
	
	@Autowired
	private MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	private OrderSelectService orderSelectService;
	
	@Autowired
	private OrderSelectRepository orderSelectRepository;
	
	@Autowired
	private EmailAdapter emailAdapter;
	
	@Autowired
	private MPushAdapter mPushAdapter;
	
	@Override
	public void sendOrderAllCancelNotice(String ordNo, SystemPK systemPK, boolean isDirect){
	
		String callerMethod = "sendOrderAllCancelNotice";
		
		Ord ord = orderSelectRepository.selectOrderInfo(ordNo);
		
		Mbr mbr = this.getMbrInfo(ord);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		try {
			log.debug("send email AllCancel {}",ordNo);
			
			//mallId		
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/order/"+ordNo+"/allCancel", "");
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/order/"+ordNo+"/allCancel", "");
			
			this.sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 구매상품 취소 안내입니다", systemPK);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.warn("ERROR !!! sending order mail >>> ", e);
		}
		
		try {
			
			String clmReturns[] = {"ALL_CNCL"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, "", systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(ord.getOrdDt()));
				params.add(3, clmFoExtend.getOrdNo());
				params.add(4, this.getGoodsNames(clmFoExtend));
				params.add(5, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(6, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, new SimpleDateFormat("yyyy-MM-dd").format(ord.getOrdDt()));
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}			
			
			//this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, "DXM_CLAIM_03", params, isDirect);
			String msgId = mallId + "_CLAIM_03";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void sendOrderPartCancelNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect) {
		
		String callerMethod = "sendOrderPartCancelNotice";
		
		Ord ord = orderSelectRepository.selectOrderInfo(ordNo);
		
		Mbr mbr = this.getMbrInfo(ord);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		try {
			log.debug("send email PartCancel {}",ordNo);
			
			//mallId
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/claim/acceptCancel", "clmNo=" + clmNo);
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/claim/acceptCancel", "clmNo=" + clmNo);
			this.sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 구매상품 취소 안내입니다", systemPK);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			log.warn("ERROR !!! sending order mail >>> ", e);
		}
		
		try {
			
			String clmReturns[] = {"PART_CNCL", "ALL_CNCL"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, clmNo, systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(ord.getOrdDt()));
				params.add(3, clmFoExtend.getOrdNo());
				params.add(4, this.getGoodsNames(clmFoExtend));
				params.add(5, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(6, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, new SimpleDateFormat("yyyy-MM-dd").format(ord.getOrdDt()));
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}
			
			String msgId = mallId + "_CLAIM_03";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void sendAcceptReturnNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect) {
		
		String callerMethod = "sendAcceptReturnNotice";
		
		Ord ord = orderSelectService.selectOrdEntity(ordNo);
		
		Mbr mbr = this.getMbrInfo(ord);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		try {
			//mallId
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/claim/acceptReturn", "clmNo=" + clmNo);
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/claim/acceptReturn", "clmNo=" + clmNo);
			this.sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 구매상품 반품 안내입니다.", systemPK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		try {
			
			String clmReturns[] = {"RTGOD"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, clmNo, systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(ord.getMallId())) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				params.add(3, clmFoExtend.getOrdNo());
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(4, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(ord.getMallId())) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				params.add(3, clmFoExtend.getOrdNo());
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(4, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, clmFoExtend.getOrdNo());
				params.add(2, this.getGoodsNames(clmFoExtend));
				params.add(3, new SimpleDateFormat("yyyy-MM-dd").format(clmFoExtend.getClmDt()));
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(4, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				//mallId
				/*params.add(5, configService.getProperty("ncp.url.mb_DX.root")+ "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
	 
				this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, "DXM_CLAIM_02", params, isDirect);*/
				params.add(5, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}			
			
			String msgId = mallId + "_CLAIM_02";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void sendCompleteReturnNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect) {
		
		String callerMethod = "sendCompleteReturnNotice";
		
		Ord ord = orderSelectService.selectOrdEntity(ordNo);
		
		Mbr mbr = this.getMbrInfo(ord);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		try {
			//mallId
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/claim/completeReturn", "clmNo=" + clmNo);
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/claim/completeReturn", "clmNo=" + clmNo);
			
			if(!"MBM".equals(mallId) && !"SAM".equals(mallId)) {
				this.sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 구매상품 반품 안내입니다.", systemPK);
			}			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		try {
			
			String clmReturns[] = {"RTGOD"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, clmNo, systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getClmNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getClmNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				params.add(5, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, new SimpleDateFormat("yyyy-MM-dd").format(ord.getOrdDt()));
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, comma(clmFoExtend.getPayExchgRtCrncySumAmt().toString()));
				//mallId
				//params.add(5, configService.getProperty("ncp.url.mb_DX.root")+ "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
				params.add(5, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}
			
			//this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, "DXM_CLAIM_01", params, isDirect);
			String msgId = mallId + "_CLAIM_01";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public void sendAcceptExchangeNotice(String ordNo, String clmNo, SystemPK systemPK, boolean isDirect) {
		
		String callerMethod = "sendAcceptExchangeNotice";
		
		Ord ord = orderSelectService.selectOrdEntity(ordNo);
		
		Mbr mbr = this.getMbrInfo(ord);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		try {
			//mallId
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/claim/acceptExchange", "clmNo=" + clmNo);
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/claim/acceptExchange", "clmNo=" + clmNo);
			sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 교환접수 안내입니다.", systemPK);

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		try {
			
			String clmReturns[] = {"GNRL_EXCHG"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, clmNo, systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(ord.getMallId())) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(ord.getMallId())) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, clmFoExtend.getOrdNo());
				params.add(2, this.getGoodsNames(clmFoExtend));
				params.add(3, new SimpleDateFormat("yyyy-MM-dd").format(clmFoExtend.getClmDt()));
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(4, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				//mallId
				//params.add(5, configService.getProperty("ncp.url.mb_DX.root")+ "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
				params.add(5, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}
			
			
 
			//this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, "DXM_CLAIM_04", params, isDirect);
			String msgId = mallId + "_CLAIM_04";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

	}

	@Override
	public void sendCompleteExchangeNotice(String ordNo, String clmNo, String waybilNo, SystemPK systemPK, boolean isDirect) {
		
		String callerMethod = "sendCompleteExchangeNotice";
		
		Ord ord = orderSelectService.selectOrdEntity(ordNo);
		
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		//mallId
		if(systemPK == null) {
			systemPK = new SystemPK();
			systemPK.setMall(mallId);
			systemPK.setLang("KOR");
			systemPK.setDevice("BATCH");
		}
		
		Mbr mbr = this.getMbrInfo(ord);
		
		try {
			//mallId
			//String mailbody = mailHtmlReaderService.readHTML(getConfigService().getProperty("ncp.url.pc_DX.root.secure") + "/email/claim/completeExchange", "clmNo=" + clmNo);
			String mailbody = mailHtmlReaderService.readHTML(getPcUrl(mallId) + "/email/claim/completeExchange", "clmNo=" + clmNo);
			
			if(!"MBM".equals(mallId) && !"SAM".equals(mallId)) {
				sendClaimMailCommon(ord, mailbody, callerMethod, " 고객님의 교환처리 안내입니다.", systemPK);
			}			

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		try {
			
			String clmReturns[] = {"GNRL_EXCHG"};
			ClmFoExtend clmFoExtend = this.selectClaimInfo(ordNo, clmNo, systemPK, clmReturns);

			ArrayList<String> params = new ArrayList<>();
			
			if("MBM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.mlb.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(5, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				params.add(6, waybilNo);
				params.add(7, getMbUrl(mallId) + "/mypage/claim/list");
			} else if("SAM".equals(mallId)) {
				params.add(0, getConfigService().getProperty("ncp_base.talk.sa.shop.name"));
				params.add(1, mbr.getMbrNm());
				params.add(2, clmFoExtend.getOrdNo());
				params.add(3, this.getGoodsNames(clmFoExtend));
				params.add(4, new SimpleDateFormat("yyyy/MM/dd").format(clmFoExtend.getClmDt()));
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(5, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				params.add(6, waybilNo);
				params.add(7, getMbUrl(mallId) + "/mypage/claim/list");
			} else {
				params.add(0, mbr.getMbrNm());
				params.add(1, clmFoExtend.getOrdNo());
				params.add(2, this.getGoodsNames(clmFoExtend));
				params.add(3, new SimpleDateFormat("yyyy-MM-dd").format(clmFoExtend.getClmDt()));
				LgsDlvspFoExtend lgsDlvsp = clmFoExtend.getLgsDlvspFoExtend().get(0);
				params.add(4, lgsDlvsp.getAddrseBaseAddr() + lgsDlvsp.getAddrseDetailAddr());
				params.add(5, waybilNo);
				//mallId
				//params.add(6, configService.getProperty("ncp.url.mb_DX.root")+ "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
				params.add(6, getMbUrl(mallId) + "/mypage/order/"+ord.getOrdNo()+"/view?clmYn=Y");
			}
			
 
			//this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, "DXM_CLAIM_05", params, isDirect);
			String msgId = mallId + "_CLAIM_05";
			this.sendClaimAlimTalkCommon(systemPK, mbr, callerMethod, msgId, params, isDirect, mallId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	private Mbr getMbrInfo(Ord ord) {
		
		if ("NMBR".equals(ord.getMbrTpCd()) == false) {
			
			Mbr mbr = new Mbr();
			mbr.setMbrNo(ord.getMbrNo());
			
			mbr = mbrRepository.selectMbr(mbr);
			
			return mbr;
			
		} else {
			
			Mbr mbr = new Mbr();
			
			mbr.setMbrNo("NMBR");
			
			mbr.setMobilAreaNo(ord.getCstmrMobilAreaNo());
			mbr.setMobilTlofNo(ord.getCstmrMobilTlofNo());
			mbr.setMobilTlofWthnNo(ord.getCstmrMobilTlofWthnNo());
			
			mbr.setMbrNm(ord.getCstmrNm());
			
			return mbr;
		}
	}

	/**
	 * 클레임 알림톡 전송 공통
	 * 
	 * @param systemPK
	 * @param mbr
	 * @param params
	 */
	private void sendClaimAlimTalkCommon(SystemPK systemPK, Mbr mbr, String callerMethod, String msgKey, ArrayList<String> params, boolean isDirect, String mallId) {
		
		if (StringService.isEmpty(mbr.getMobilAreaNo())) {
			return;
		}
			
		AdapterHeader adapterHeader = getAdapterHeader(systemPK);
		
		MPushAlimTalkSDO mPushAlimTalkSDO = new MPushAlimTalkSDO();
		
		if(isDirect) {
			mPushAlimTalkSDO.setSendDirectFlag(true);
		}
		
		mPushAlimTalkSDO.setMbrNo(mbr.getMbrNo());
		mPushAlimTalkSDO.setReceiveMobileNo(mbr.getMobilAreaNo() + mbr.getMobilTlofNo() + mbr.getMobilTlofWthnNo());
		mPushAlimTalkSDO.setMallId(mallId);
		mPushAlimTalkSDO.setCallerId(this.getClass().getName() + callerMethod);
		mPushAlimTalkSDO.setMsgKey(msgKey);
		mPushAlimTalkSDO.setParams(params);
		
		mPushAdapter.sendAlimTalk(mPushAlimTalkSDO, adapterHeader);
	}
	
	/**
	 * 알림톡에 사용할 상품명 가져오기. ([상품명] 외 X건)
	 * 
	 * @param clmFoExtend
	 * @return
	 */
	private String getGoodsNames(ClmFoExtend clmFoExtend) {
		int count = 0;
		String godNm = "";
		for (LgsDlvspFoExtend v : clmFoExtend.getLgsDlvspFoExtend()) {
			for (ClmWrhsGodExtend v2 : v.getClmWrhsGodList()) {
				
				if(count == 0){
					godNm = "[" +CodeUtil.getCode("KOR",v2.getRecomendSexCd()).getCdNm() +"]"+ v2.getGodNm();
				}
				count++;
			}
		}
		
		if(count > 1){
			count = count -1;
			godNm = godNm +"외 "+count+"건";
		}
		return godNm;
	}
	
	/**
	 * 클레임 정보 조회
	 * 
	 * @param ordNo
	 * @param clmNo
	 * @param systemPK
	 * @param clmTypes
	 * @return
	 */
	private ClmFoExtend selectClaimInfo(String ordNo, String clmNo, SystemPK systemPK, String[] clmTypes) {
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		
		mypageOrderInfoDTO.setOrdNo(ordNo);
		mypageOrderInfoDTO.setClmNo(clmNo);
		mypageOrderInfoDTO.setClmTpCd(clmTypes);
		
		List<ClmFoExtend> resultClmReturns = memberActivityFOComponent.selectClmFoList(systemPK, mypageOrderInfoDTO);
		
		ClmFoExtend clmFoExtend = new ClmFoExtend();
		
		if(resultClmReturns != null && resultClmReturns.size() >0){
			clmFoExtend = resultClmReturns.get(0);
		}
		
		return clmFoExtend;
	}
	
	/**
	 * 클레임 메일 전송 공통
	 * 
	 * @param ord
	 * @param mailbody
	 * @param callerMethod
	 * @param subjectSuffix
	 * @param systemPK
	 * @throws Exception
	 */
	private void sendClaimMailCommon(Ord ord, String mailbody, String callerMethod, String subjectSuffix, SystemPK systemPK) throws Exception {
		
		if (StringService.isEmpty(ord.getCstmrEmail())) {
			return;
		}
		
		//mallId
		String mallId = StringUtils.defaultString(ord.getMallId(), "DXM");
		
		String mallEmail = "DISCOVERY <discovery@fnf.co.kr>";
		String mallSubject = "[Discovery Expedition] ";
		
		if("MBM".equals(mallId)) {
			mallEmail = getConfigService().getProperty("ncp_base.mlb.mall.email.address");
			mallSubject = getConfigService().getProperty("ncp_base.mlb.mall.email.subject") + " ";
		} else if("SAM".equals(mallId)) {
			mallEmail = getConfigService().getProperty("ncp_base.sa.mall.email.address");
			mallSubject = getConfigService().getProperty("ncp_base.sa.mall.email.subject") + " ";
		}
		
		EmailHtmlSDO emailHtmlSDO = new EmailHtmlSDO();
		emailHtmlSDO.setCallerId(getClass().getName() + callerMethod);
		emailHtmlSDO.setMallEmail(mallEmail);
		emailHtmlSDO.setMbrEmail(ord.getCstmrEmail());
		emailHtmlSDO.setSubject(mallSubject + ord.getCstmrNm() + subjectSuffix);
		emailHtmlSDO.setHtmlBody(mailbody);
		
		AdapterHeader adapterHeader = getAdapterHeader(systemPK);

		log.info(emailAdapter.sendHtmlEmail(emailHtmlSDO, adapterHeader));
		
		this.insertMemberPersonalEmailInfo(systemPK, ord);
	}

	private AdapterHeader getAdapterHeader(SystemPK systemPK) {
		AdapterHeader adapterHeader = new AdapterHeader();
		adapterHeader.setRequestId(interfaceApiCommon.getRequestId());
		adapterHeader.setMallId(systemPK.getMall());
		adapterHeader.setLangCd(systemPK.getLang());
		adapterHeader.setDvcCd(systemPK.getDevice());
		return adapterHeader;
	}
	
	/**
	 * 이메일 개인정보 이력저장 
	 * 
	 * @param systemPK
	 * @param ord
	 * @throws Exception
	 */
	private void insertMemberPersonalEmailInfo(SystemPK systemPK, Ord ord) throws Exception {
		if (!StringService.isEmpty(ord.getMbrNo())) {
			String[][] usefCdInfo = { // 구분, 업무, 업무상세
					{UsefSectCd.PSNL_INFO_TRTMNT_CNSGN_DETL.toString(), UsefJobCd.SND_EMAIL.toString(), UsefJobDetail.CLM_DETAIL.toString()}
			};
			this.insertMemberPersonalInfo(systemPK, ord.getMbrNo(), usefCdInfo);
		}
	}
	
	/**
	 * 개인정보 이력저장
	 * 
	 * @param systemPK
	 * @param mbrNo
	 * @param usefCdInfo
	 * @throws Exception
	 */
	private void insertMemberPersonalInfo(SystemPK systemPK, String mbrNo, String[][] usefCdInfo) throws Exception {
		String[] target = {mbrNo};
		memberPersonalInfoCommandService.insertMemberPersonalInfo(systemPK
				, usefCdInfo				// 개인정보 코드 정보(구분, 업무, 업무상세)
				, target					// 이용대상 : 회원
				, null						// 유입 일련번호
				, null						// 메뉴 일련번호
				, mbrNo						// mbr_no
				);
	}
	
	private String comma(String value) {
		Long inValues = Long.parseLong(value);
		DecimalFormat Commas = new DecimalFormat("#,###");
		return (String) Commas.format(inValues);
	}
	
	private String getPcUrl(String mallId) {
		String pcUrl = getConfigService().getProperty("ncp.url.pc_DX.root.secure");
		
		if("MBM".equals(mallId)) {
			pcUrl = getConfigService().getProperty("ncp.url.pc_MLB.root.secure");
		} else if("SAM".equals(mallId)) {
			pcUrl = getConfigService().getProperty("ncp.url.pc_SA.root.secure");
		}
		
		return pcUrl;
	}
	
	private String getMbUrl(String mallId) {
		String mbUrl = configService.getProperty("ncp.url.mb_DX.root");

		if("MBM".equals(mallId)) {
			mbUrl = configService.getProperty("ncp.url.mb_MLB.root");
		} else if("SAM".equals(mallId)) {
			mbUrl = configService.getProperty("ncp.url.mb_SA.root");
		}
		
		return mbUrl;
	}
}
