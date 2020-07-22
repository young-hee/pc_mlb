package com.plgrim.ncp.cmp.callcenter.fo.helpdesk.faq;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobCd;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefJobDetail;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum.UsefSectCd;
import com.plgrim.ncp.biz.helpdesk.data.HelpdeskFaqFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HelpdeskFaqFoResult;
import com.plgrim.ncp.biz.helpdesk.service.HelpdeskService;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.member.service.MemberActivityCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.cmp.callcenter.fo.helpdesk.CallcenterFaqFOComponent;
import com.plgrim.ncp.commons.result.CodeViewResult;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional(value = "transactionManager")
public class CallcenterFaqFOComponentImpl extends AbstractComponent implements CallcenterFaqFOComponent {

    @Autowired
    private HelpdeskService helpdeskService;

	@Autowired
	MemberPersonalInfoSelectService memberPersonalInfoSelectService;
	
	
	@Autowired
	MemberPersonalInfoCommandService memberPersonalInfoCommandService;
	
	@Autowired
	MemberActivityCommandService memberActivityCommandService;
	
	@Autowired
	MemberOrderSelectService memberOrderSelectService;
	
    /**
     * Faq 리스트
     *
     * @return
     */
    @Override
    public Page<HelpdeskFaqFoResult> selectFaqList(HelpdeskFaqFoDTO helpdeskFaqFoDTO, PageParam pageParam) throws Exception {
        return helpdeskService.selectFaqList(helpdeskFaqFoDTO, pageParam);
    }
    
    public long selectCountFaq(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception{
    	return helpdeskService.selectCountFaq(helpdeskFaqFoDTO);
    }

    /**
     * 고객센터 메인 FAQ top10 리스트
     */
    @Override
    public List<HelpdeskFaqFoResult> selectFaqTop10List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
        return helpdeskService.selectFaqTop10List(helpdeskFaqFoDTO);
    }
    /**
     * 고객센터 메인 FAQ top5 리스트
     */
    @Override
    public List<HelpdeskFaqFoResult> selectFaqTop5List(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
        return helpdeskService.selectFaqTop5List(helpdeskFaqFoDTO);
    }

    @Override
    public void updateFaqInqireCount(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
        helpdeskService.updateFaqInqireCount(helpdeskFaqFoDTO);
    }

    /**
     * faq 타이틀 조회
     */
    @Override
    public List<CodeViewResult> selectFaqTitleList(HelpdeskFaqFoDTO helpdeskFaqFoDTO) throws Exception {
        return helpdeskService.selectFaqTitleList(helpdeskFaqFoDTO);
    }
    
	@Override
	/** 1:1상담 등록	 */
	public void insertCsInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO) {
		
		if( mypageMtmFoDTO.getMbr() != null) {
			//개인정보 이용 이력 등록
			MbrPsnlInfoUsef mpiu = memberPersonalInfoSelectService.setMbrPsnlInfoUsef(pk, null, null, mypageMtmFoDTO.getMbr().getMbrNo());
			
			String usefSectCdInfo = UsefSectCd.PSNL_INFO_STTUS.toString();
			String usefJobCdInfo = UsefJobCd.MTM_INQ_REG.toString();
			
			String[] usefCdInfoDetail = {
					UsefJobDetail.MOBIL_NO.toString(), UsefJobDetail.EMAIL.toString() 
			};
			memberPersonalInfoCommandService.insertPersonalInfoUsef(mpiu, usefSectCdInfo, usefJobCdInfo, usefCdInfoDetail);	
		}
		//문의등록
		memberActivityCommandService.insertCsInquiry(mypageMtmFoDTO);
	}
	
	
	/** 1:1문의 주문리스트	*/
	@Override
	public List<MypageMtmFoResult> selectOrdGodList(SystemPK pk,MypageMtmFoDTO mypageMtmFoDTO)  {
	    // TODO Auto-generated method stub
		return memberOrderSelectService.selectOrdGodList(mypageMtmFoDTO);
	}
	
	
	/** 1:1문의 주문리스트 페이징	*/
	@Override
	 public Page<MypageMtmFoResult> selectInquiryOrdGodList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam){
		
		return memberOrderSelectService.selectInquiryOrdGodList(mypageMtmFoDTO,pageParam);
	}
	/** 1:1문의 주문리스트 페이징 Mo	*/
	@Override
	public Page<MypageMtmFoResult> selectInquiryOrdGodMOList(MypageMtmFoDTO mypageMtmFoDTO,PageParam pageParam){
		return memberOrderSelectService.selectInquiryOrdGodMOList(mypageMtmFoDTO,pageParam);
	}
	
	 /**
   	 * 1:1주문상품 리스트MO 8월20일 이전 내용 확인
   	 */
     public boolean selectInquiryOrdMOListBefor0822(MypageMtmFoDTO mypageMtmFoDTO){
    	 return memberOrderSelectService.selectInquiryOrdMOListBefor0822(mypageMtmFoDTO);
     }
	
	
    
}
