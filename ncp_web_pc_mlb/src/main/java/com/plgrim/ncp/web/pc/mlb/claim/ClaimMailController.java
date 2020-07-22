package com.plgrim.ncp.web.pc.mlb.claim;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.plgrim.ncp.web.pc.mlb.order.OrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.plgrim.ncp.base.entities.datasource1.clm.Clm;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.ord.OrdGodFoExtend;
import com.plgrim.ncp.base.repository.clm.ClmRepository;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.cmp.display.fo.PlanComponent;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.commons.service.MailHtmlReaderService;
import com.plgrim.ncp.framework.data.SystemPK;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/email/claim")
public class ClaimMailController extends OrderController {

	@Autowired 
	MailHtmlReaderService mailHtmlReaderService;
 
	@Autowired 
	PlanComponent planComponent;
	@Autowired 
	ClmRepository clmRepository;
	
	@Autowired 
	MemberActivityFOComponent memberActivityFOComponent;
	
	@RequestMapping(value = "/acceptCancel")
    public String acceptCancel(Model model, HttpServletRequest request,String clmNo) throws Exception {
		
		String clms[] = {"PART_CNCL", "ALL_CNCL"};
		
		this.getClaimInfo(model, request, clmNo, clms);
 
        return "tiles:email/claim/accept.cancel";
    }
	
	@RequestMapping(value = "/acceptReturn")
    public String acceptReturn(Model model, HttpServletRequest request,String clmNo) throws Exception {
		
		String clms[] = {"RTGOD"};
		
		this.getClaimInfo(model, request, clmNo, clms);
 
        return "tiles:email/claim/accept.return";
    }
	
	@RequestMapping(value = "/completeReturn")
    public String completeReturn(Model model, HttpServletRequest request,String clmNo) throws Exception {
		
		String clms[] = {"RTGOD"};
		
		this.getClaimInfo(model, request, clmNo, clms);
 
        return "tiles:email/claim/complete.return";
    }
	
	@RequestMapping(value = "/acceptExchange")
    public String acceptExchange(Model model, HttpServletRequest request,String clmNo) throws Exception {
		
		String clms[] = {"GNRL_EXCHG"};
		
		this.getClaimInfo(model, request, clmNo, clms);
 
        return "tiles:email/claim/accept.exchange";
    }
	
	@RequestMapping(value = "/completeExchange")
    public String completeExchange(Model model, HttpServletRequest request,String clmNo) throws Exception {
		
		String clms[] = {"GNRL_EXCHG"};
		
		this.getClaimInfo(model, request, clmNo, clms);
 
        return "tiles:email/claim/complete.exchange";
    }
	
	private void getClaimInfo(Model model, HttpServletRequest request, String clmNo, String[] clms) {
		SystemPK pk = getIdGenService().getAutoGeneratorSystemPK(request);
		log.debug("{}",  request.getRequestURI());
		
		Clm clm = new Clm();
		clm.setClmNo(clmNo);
		clm = clmRepository.selectClm(clm);
		MypageOrderInfoDTO mypageOrderInfoDTO = new MypageOrderInfoDTO();
		mypageOrderInfoDTO.setOrdNo(clm.getOrdNo());
 
		MypageOrderInfoDTO orderInfo = memberActivityFOComponent.selectFoOrderInfo(pk, mypageOrderInfoDTO);
		
		// 배송지별 동일 출고지 여부 체크
		for(LgsDlvspFoExtend lgsDlvspFoExtend: orderInfo.getLgsDlvspFoExtend()) {
			
			List<String> shopList = new ArrayList<String>();
			
			for(LgsDlvFoExtend lgsDlv : lgsDlvspFoExtend.getLgsDlvFoExtendList()) {
				for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()) {
					if (ordGod.getDlvShopId() == null) {
						continue;
					}
					
					if (shopList.contains(ordGod.getDlvShopId())) {
						continue;
					}
					
					shopList.add(ordGod.getDlvShopId());
				}
			}
			
			if (shopList.size() > 1) {
				lgsDlvspFoExtend.setPlaceDiffYN("Y");
			} else {
				lgsDlvspFoExtend.setPlaceDiffYN("N");
			}
			
			log.debug("lgsDlvspFoExtend.getPlaceDiffYN : " + lgsDlvspFoExtend.getPlaceDiffYN());
		}

		int clmCnt = 0;
		for(LgsDlvspFoExtend lgsDlvsp : orderInfo.getLgsDlvspFoExtend()){
			for(LgsDlvFoExtend lgsDlv : lgsDlvsp.getLgsDlvFoExtendList()){
				for(OrdGodFoExtend ordGod : lgsDlv.getOrdGodList()){
					if("Y".equals(ordGod.getClmYn()) 
							&& ordGod.getRealOrdQty() > 0 
							&& "EXCHG".equals(ordGod.getDlivyDrctTpCd()) == false){
						clmCnt++;
					}
				}
			}
		}

		orderInfo.setClmCnt(clmCnt);

		// 주문 상세 조회
		model.addAttribute("orderInfo", orderInfo);
 
		mypageOrderInfoDTO.setClmTpCd(clms);
		mypageOrderInfoDTO.setClmNo(clmNo);
		List<ClmFoExtend> resultClmList = memberActivityFOComponent.selectClmFoList(pk, mypageOrderInfoDTO);
 
		model.addAttribute("clmInfo", resultClmList.get(0));
	}
}