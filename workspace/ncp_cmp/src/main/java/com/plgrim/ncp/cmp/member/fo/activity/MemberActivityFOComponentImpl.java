package com.plgrim.ncp.cmp.member.fo.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.clm.ClmFoExtend;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlAtchFile;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvsp;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspFoExtend;
import com.plgrim.ncp.base.entities.datasource1.lgs.LgsDlvspHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.Mbr;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBnefDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrBukmkBrnd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrDlvsp;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrGrd;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoModHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrPsnlInfoUsef;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfcDetail;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHist;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrWebpntHistExtend;
import com.plgrim.ncp.base.enums.EventEnum;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.base.enums.MemberBenefitEnum;
import com.plgrim.ncp.base.enums.MemberEnum;
import com.plgrim.ncp.base.enums.SysInfoEnum;
import com.plgrim.ncp.base.enums.member.MemberPersonalInfoEnum;
import com.plgrim.ncp.base.repository.god.GodRepository;
import com.plgrim.ncp.biz.delivery.service.DeliveryService;
import com.plgrim.ncp.biz.goods.data.GoodsReviewDTO;
import com.plgrim.ncp.biz.helpdesk.data.HistoryInfoFoDTO;
import com.plgrim.ncp.biz.helpdesk.result.HistoryInfoFoResult;
import com.plgrim.ncp.biz.member.data.MbrBnefDetailExtend;
import com.plgrim.ncp.biz.member.data.MemberFoDTO;
import com.plgrim.ncp.biz.member.data.MemberOrdGodFoDTO;
import com.plgrim.ncp.biz.member.data.MemberSizeClfcDTO;
import com.plgrim.ncp.biz.member.data.MyBrndFoDTO;
import com.plgrim.ncp.biz.member.data.MypageFoDTO;
import com.plgrim.ncp.biz.member.data.MypageMtmFoDTO;
import com.plgrim.ncp.biz.member.data.MypageQnaFoDTO;
import com.plgrim.ncp.biz.member.data.SecurityUserDetail;
import com.plgrim.ncp.biz.member.result.MemberBoResult;
import com.plgrim.ncp.biz.member.result.MemberOrdGodFoResult;
import com.plgrim.ncp.biz.member.result.MyBrndFoResult;
import com.plgrim.ncp.biz.member.result.MypageAlrmFoResult;
import com.plgrim.ncp.biz.member.result.MypageEvtFoResult;
import com.plgrim.ncp.biz.member.result.MypageMainFoResult;
import com.plgrim.ncp.biz.member.result.MypageMtmFoResult;
import com.plgrim.ncp.biz.member.result.MypageMysizeResult;
import com.plgrim.ncp.biz.member.result.MypageQnaFoResult;
import com.plgrim.ncp.biz.member.result.MypageTdGodFoResult;
import com.plgrim.ncp.biz.member.result.MypageWishFoResult;
import com.plgrim.ncp.biz.member.service.MemberActivityCommandService;
import com.plgrim.ncp.biz.member.service.MemberActivitySelectService;
import com.plgrim.ncp.biz.member.service.MemberBaseCommandService;
import com.plgrim.ncp.biz.member.service.MemberBaseSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitApiSelectService;
import com.plgrim.ncp.biz.member.service.MemberBenefitCommandService;
import com.plgrim.ncp.biz.member.service.MemberBenefitSelectService;
import com.plgrim.ncp.biz.member.service.MemberOrderCommandService;
import com.plgrim.ncp.biz.member.service.MemberOrderSelectService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoCommandService;
import com.plgrim.ncp.biz.member.service.MemberPersonalInfoSelectService;
import com.plgrim.ncp.biz.order.data.MypageOrderInfoDTO;
import com.plgrim.ncp.biz.order.result.MypageClaimFoResult;
import com.plgrim.ncp.biz.order.result.MypageOrderFoResult;
import com.plgrim.ncp.biz.order.service.OrderSelectService;
import com.plgrim.ncp.biz.promotion.service.EventService;
import com.plgrim.ncp.cmp.member.fo.MemberActivityFOComponent;
import com.plgrim.ncp.commons.taglib.Functions;
import com.plgrim.ncp.commons.util.CodeUtil;
import com.plgrim.ncp.framework.commons.ContextService;
import com.plgrim.ncp.framework.commons.DateService;
import com.plgrim.ncp.framework.commons.JsonService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;
import com.plgrim.ncp.framework.page.PageParam;
import com.plgrim.ncp.interfaces.order.adapter.OrderAdapter;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseCfmInfoSDO;
import com.plgrim.ncp.interfaces.order.data.OrderFormerlyPurchaseSDO;
import com.plgrim.ncp.interfaces.order.data.OrderOfflinePurchaseSDO;
import com.plgrim.ncp.interfaces.util.AdapterHeader;
import com.plgrim.ncp.interfaces.util.InterfaceApiCommon;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/*import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfc;
import com.plgrim.ncp.base.entities.datasource1.mbr.MbrSizeClfcDetail;*/

@Transactional(value = "transactionManager")
@Component
@Slf4j
public class MemberActivityFOComponentImpl extends AbstractComponent implements MemberActivityFOComponent {
	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */

	@Autowired
	private MemberActivityCommandService memberActivityCommandService;

	@Autowired
	private MemberActivitySelectService memberActivitySelectService;

	@Autowired
	private MemberPersonalInfoSelectService memberPersonalInfoSelectService;

	@Autowired
	private MemberPersonalInfoCommandService memberPersonalInfoCommandService;

	@Autowired
	private MemberBaseCommandService memberBaseCommandService;

	@Autowired
	private MemberBaseSelectService memberBaseSelectService;

	@Autowired
	private MemberBenefitSelectService memberBenefitSelectService;

	@Autowired
	private MemberBenefitCommandService memberBenefitCommandService;

	@Autowired
	private GodRepository godRepository;

    @Autowired
	private EventService eventService;

    @Autowired
    private OrderSelectService orderSelectService;		// 주문

	@Autowired
	private MemberOrderSelectService memberOrderSelectService;

	@Autowired
	private MemberOrderCommandService memberOrderCommandService;

	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	MemberBenefitApiSelectService memberBenefitApiSelectService;

	@Autowired
	InterfaceApiCommon interfaceApiCommon;

	@Autowired
	private OrderAdapter orderAdapter;
	/*
	 * ---------------------------------------------------------------------
	 * Constructors.
	 * ---------------------------------------------------------------------
	 */

	/*
	 * ---------------------------------------------------------------------
	 * public & interface method.
	 * ---------------------------------------------------------------------
	 */
    /**
     * 1:1문의 리스트
     */
    @Override
    public Page<MypageMtmFoResult> selectMyInquiryList(MypageMtmFoDTO mypageMtmFoDTO, PageParam pageParam)  {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
        return memberActivitySelectService.selectMyInquiryList(mypageMtmFoDTO,pageParam);
    }

    /**
     * 1:1문의 리스트 (시스템정보)
     */
    @Override
    public Page<MypageMtmFoResult> selectMyInquiryList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO,
    		PageParam pageParam) {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(Arrays.asList(pk.getMall()));
        return memberActivitySelectService.selectMyInquiryList(mypageMtmFoDTO,pageParam);
    }

    @Override
    public List<MypageMtmFoResult> selectMyInquiryListMobile(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO)  {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
    	return memberActivitySelectService.selectMyInquiryListMobile(mypageMtmFoDTO);
    }

    @Override
    public List<MypageMtmFoResult> selectMyInquiryListMobileWithMall(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO)  {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(Arrays.asList(systemPk.getMall()));
    	return memberActivitySelectService.selectMyInquiryListMobile(mypageMtmFoDTO);
    }
    
    @Override
    public int selectMyInquiryListMobileCnt(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO)  {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
    	return memberActivitySelectService.selectMyInquiryListMobileCnt(mypageMtmFoDTO);
    }

    @Override
    public int selectMyInquiryListMobileCntWithMall(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO)  {
    	mypageMtmFoDTO.setPlgrimshopMallIdList(Arrays.asList(systemPk.getMall()));
    	return memberActivitySelectService.selectMyInquiryListMobileCnt(mypageMtmFoDTO);
    }


    /**
     * 1:1문의 리스트 상세
     */
    @Override
    public List<MypageMtmFoResult> selectCsInquiryDetail(SystemPK systemPk, MypageMtmFoDTO mypageMtmFoDTO)  {
        return memberActivitySelectService.selectCsInquiryDetail(mypageMtmFoDTO);
    }

    /**
     * Qna 리스트
     */
    @Override
    public Page<MypageQnaFoResult> selectMyQnaList(MypageQnaFoDTO mypageQnaFoDTO,PageParam pageParam)  {
    	mypageQnaFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
        return memberActivitySelectService.selectMyQnaList(mypageQnaFoDTO,pageParam);
    }

    @Override
    public List<MypageQnaFoResult> selectMyQnaListMobile(SystemPK systemPk,MypageQnaFoDTO mypageQnaFoDTO)  {
    	mypageQnaFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
    	return memberActivitySelectService.selectMyQnaListMobile(mypageQnaFoDTO);
    }

    /**
     * Qna 리스트 상세
     */
    @Override
    public List<MypageQnaFoResult> selectMyQnaListDetail(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO)  {
    	mypageQnaFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
        return memberActivitySelectService.selectMyQnaListDetail(mypageQnaFoDTO);
    }

    @Override
    public Page<HistoryInfoFoResult> selectMyLoginHistory(HistoryInfoFoDTO historyInfoFoDTO, PageParam pageParam)
             {
	    return memberBaseSelectService.selectMyLoginHistory(historyInfoFoDTO, pageParam);
    }

    @Override
    public List<MypageMtmFoResult> selectMyInquiryFileList(SystemPK pk,MypageMtmFoDTO mypageMtmFoDTO)  {
		mypageMtmFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
	    return memberActivitySelectService.selectMyInquiryFileList(mypageMtmFoDTO);
    }

    @Override
    public List<MypageMtmFoResult> selectMyInquiryFileListWithMall(SystemPK pk,MypageMtmFoDTO mypageMtmFoDTO)  {
		mypageMtmFoDTO.setPlgrimshopMallIdList(Arrays.asList(pk.getMall()));
	    return memberActivitySelectService.selectMyInquiryFileList(mypageMtmFoDTO);
    }
    
	@Override
    public List<MypageMtmFoResult> selectMyInquiryOrdGodList(SystemPK pk,MypageMtmFoDTO mypageMtmFoDTO)  {
	    return memberActivitySelectService.selectMyInquiryOrdGodList(mypageMtmFoDTO);
    }

	 /**
     * 이벤트 리스트
     */
    @Override
    public Page<MypageEvtFoResult> selectMyEventList(MypageFoDTO dto, PageParam pageParam)  {
        Page<MypageEvtFoResult> eventList = memberActivitySelectService.selectMyEventList(dto, pageParam);
        return eventList;
    }

    /*당첨 이벤트 리스트*/
    @Override
    public long selectMyEventPrizeListCount(MypageFoDTO dto)  {
		return memberActivitySelectService.selectMyEventPrizeListCount(dto);
	}

    /**
	 * Wish List 조회
	 */
	@Override
    public Page<MypageWishFoResult> getMyWishList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberActivitySelectService.getMyWishList(dto, pageParam);
	}


	@Override
	public List<MypageWishFoResult> getMyWishListMobile(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberActivitySelectService.getMyWishListMobile(dto);
	}

	/**
	 * 재입고알람내역 조회
	 */
	@Override
    public Page<MypageAlrmFoResult> getMyAlarmsForStockList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		return memberActivitySelectService.getMyAlarmsForStockList(dto, pageParam);
	}

	/**
	 * 메인 알람 목록 조회
	 */
	@Override
    public List<MypageMainFoResult> getMyPageAlramList(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		dto.setDevice(systemPK.getDevice());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberActivitySelectService.getMyPageAlramList(dto);
	}

	/**
	 * 메인 오늘본상품 조회
	 */
	@Override
    public Page<MypageTdGodFoResult> getMyTodayGodList(SystemPK systemPK, MypageFoDTO dto , PageParam pageParam)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberActivitySelectService.getMyTodayGodList(dto, pageParam);
	}



	@Override
    public Page<MemberOrdGodFoResult> selectMyGoodsReviewList(SystemPK systemPK, MemberOrdGodFoDTO dto, PageParam pageParam)
             {
	    dto.setLang(systemPK.getLang());
	    dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
	    return memberActivitySelectService.selectMyGoodsReviewList(dto, pageParam);
    }
	@Override
	public Page<MemberOrdGodFoResult> selectMyGoodsNoReviewList(SystemPK systemPK, MemberOrdGodFoDTO dto, PageParam pageParam)	 {
		dto.setLang(systemPK.getLang());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberActivitySelectService.selectMyGoodsNoReviewList(dto, pageParam);
	}

	@Override
    public MemberOrdGodFoResult selectMyGoodsReview(SystemPK systemPK, MemberOrdGodFoDTO dto)  {
	    return memberActivitySelectService.selectMyGoodsReview(systemPK,dto);
    }

	@Override
	public MemberOrdGodFoResult selectMyGoodsNoReview(SystemPK systemPK, MemberOrdGodFoDTO dto)  {
		return memberActivitySelectService.selectMyGoodsNoReview(systemPK,dto);
	}
	@Override
	public MemberOrdGodFoResult selectMyGoodsStoreNoReview(SystemPK systemPK, MemberOrdGodFoDTO dto)  {
		return memberActivitySelectService.selectMyGoodsStoreNoReview(systemPK,dto);
	}

	/**
	 * 1:1문의 리스트 카운트
	 */
	@Override
	public long selectMyInquiryListCount(MypageMtmFoDTO mypageMtmFoDTO)  {
		mypageMtmFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberActivitySelectService.selectMyInquiryListCount(mypageMtmFoDTO);
	}

	/**
	 * 1:1문의 리스트 카운트(MLB,SA용)
	 */
	@Override
	public long selectMyInquiryListCount(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO)  {
		mypageMtmFoDTO.setPlgrimshopMallIdList(Arrays.asList(pk.getMall()));
		return memberActivitySelectService.selectMyInquiryListCount(mypageMtmFoDTO);
	}
	
	@Override
	public Page<MyBrndFoResult> selectMyBrndList(MyBrndFoDTO myBrndFoDTO, PageParam pageParam)  {
		return memberActivitySelectService.selectMyBrndList(myBrndFoDTO,pageParam);
	}


	/**
     *  통합몰에서 노출되어야 할 몰 ID리스트 수집
     */
    private List<String> getPlgrimshopMallIdList() {

		List<String> plgrimshopMallIdList = new ArrayList<String>();
		for (SysInfoEnum.PlgrimshopMallEnum mallEnum : SysInfoEnum.PlgrimshopMallEnum.values()){
			plgrimshopMallIdList.add(mallEnum.toString());
		}
		return plgrimshopMallIdList;
    }

    public long selectMyQnaListCount(MypageQnaFoDTO mypageQnaFoDTO)  {
    	return memberActivitySelectService.selectMyQnaListCount(mypageQnaFoDTO);
    }

    @Override
    public int selectMyQnaListMobileCnt(SystemPK systemPk,MypageQnaFoDTO mypageQnaFoDTO)  {
    	mypageQnaFoDTO.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
    	return memberActivitySelectService.selectMyQnaListMobileCnt(mypageQnaFoDTO);
    }

    @Override
    public long selectMyGoodsNoReviewListCount(MemberOrdGodFoDTO dto)  {
    	return memberActivitySelectService.selectMyGoodsNoReviewListCount(dto);
    }

    @Override
    public long checkFirstLogin(Mbr mbr) {
    	return memberActivitySelectService.checkFirstLogin(mbr);
    }

    @Override
    public long checkFirstAppDownload(Mbr mbr) {
    	return memberActivitySelectService.checkFirstAppDownload(mbr);
    }

    /**
	 * 이벤트 응모가능여부
	 *
	 */
    public String selectCampaginEvtInfo(String evtNo)  {
		return eventService.selectCampaginEvtInfo(evtNo);
	}

	@Override
    public long getMyPageAlramListCnt(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		dto.setDevice(systemPK.getDevice());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberActivitySelectService.getMyPageAlramListCnt(dto);
	}

	@Override
	public long selectMyTodayGodListCount(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		return memberActivitySelectService.selectMyTodayGodListCount(dto);
	}

    @Override
    public List<MypageMainFoResult> getMyPageAlramListMb(SystemPK systemPK, MypageFoDTO dto)  {
		dto.setLang(systemPK.getLang());
		dto.setMallId(systemPK.getMall());
		dto.setDevice(systemPK.getDevice());
		dto.setPlgrimshopMallIdList(getPlgrimshopMallIdList());
		return memberActivitySelectService.getMyPageAlramListMb(dto);
	}


    @Override
	public long selectCountHistory(HistoryInfoFoDTO historyInfoDTO)  {
		return memberBaseSelectService.selectCountHistory(historyInfoDTO);
	}

    @Override
	public List<MypageMysizeResult> selectMyBasicSizeList(String mbrNo)  {
		return memberActivitySelectService.selectMyBasicSizeList(mbrNo);
	}

	@Override
	public MypageMysizeResult selectMysizeOne(MemberSizeClfcDTO memberSizeClfcDTO){
		return memberActivitySelectService.selectMysizeOne(memberSizeClfcDTO);
	}

	@Override
	public int selectMysizeCount(String mbrNo){
		return memberActivitySelectService.selectMysizeCount(mbrNo);
	}

	/** Qna 변경 */
	@Override
	public void updateMyQna(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO) {
		memberActivityCommandService.updateMyQna(mypageQnaFoDTO);
	}

	/** Qna 삭제 */
	@Override
	public void deleteMyQna(SystemPK pk, MypageQnaFoDTO mypageQnaFoDTO) {
		memberActivityCommandService.deleteMyQna(mypageQnaFoDTO);
	}

	/** 파일업로드	 */
	@Override
	public void createFile(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO, List files) {
		//개인정보 이용 이력 등록
		MbrPsnlInfoUsef mpiu = memberPersonalInfoSelectService.setMbrPsnlInfoUsef(pk, null, null, mypageMtmFoDTO.getMbr().getMbrNo());

		String usefSectCdInfo = MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_STTUS.toString();
		String usefJobCdInfo = MemberPersonalInfoEnum.UsefJobCd.MTM_INQ_REG.toString();

		String[] usefCdInfoDetail = {
				MemberPersonalInfoEnum.UsefJobDetail.MOBIL_NO.toString(),MemberPersonalInfoEnum.UsefJobDetail.EMAIL.toString()
		};
		memberPersonalInfoCommandService.insertPersonalInfoUsef(mpiu, usefSectCdInfo, usefJobCdInfo, usefCdInfoDetail);
		//파일 등록
		memberActivityCommandService.insertFile(mypageMtmFoDTO, files);
	}

	@Override
	/** 1:1상담 등록	 */
	public void insertCsInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO) {
		//개인정보 이용 이력 등록
		MbrPsnlInfoUsef mpiu = memberPersonalInfoSelectService.setMbrPsnlInfoUsef(pk, null, null, mypageMtmFoDTO.getMbr().getMbrNo());

		String usefSectCdInfo = MemberPersonalInfoEnum.UsefSectCd.PSNL_INFO_STTUS.toString();
		String usefJobCdInfo = MemberPersonalInfoEnum.UsefJobCd.MTM_INQ_REG.toString();

		String[] usefCdInfoDetail = {
				MemberPersonalInfoEnum.UsefJobDetail.MOBIL_NO.toString(), MemberPersonalInfoEnum.UsefJobDetail.EMAIL.toString()
		};
		memberPersonalInfoCommandService.insertPersonalInfoUsef(mpiu, usefSectCdInfo, usefJobCdInfo, usefCdInfoDetail);
		//문의등록
		memberActivityCommandService.insertCsInquiry(mypageMtmFoDTO);
	}

	@Override
	public void deleteMyWishList(MypageFoDTO dto)  {
		memberActivityCommandService.deleteMyWishList(dto);
	}
	@Override
	public void deleteMainMyWishList(MypageFoDTO dto)  {
		memberActivityCommandService.deleteMainMyWishList(dto);
	}

	@Override
	public void deleteAllMyWishList(MypageFoDTO dto)  {
		memberActivityCommandService.deleteAllMyWishList(dto);
	}

	@Override
	public void deleteMyAlarmsForStock(MypageFoDTO dto)  {
		memberActivityCommandService.deleteMyAlarmsForStock(dto);
	}

	@Override
	public void insertMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto, List<MultipartFile> multipartFiles) {
		try {
			memberBaseCommandService.setSystemPK(dto.getGodGodEvl(),pk);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		memberActivityCommandService.insertMyGoodsReview(pk, dto);

		MemberBenefitEnum.BnefSectCd bnefSectCd = MemberBenefitEnum.BnefSectCd.TEXT_GOD_REVW;
		if(multipartFiles !=null && multipartFiles.size() > 0){
			for(MultipartFile mFile : multipartFiles){
				if(!mFile.isEmpty()){
					bnefSectCd = MemberBenefitEnum.BnefSectCd.PHOTO_GOD_REVW;
					break;
				}

			}
			memberActivityCommandService.insertMyGoodsReviewAtchFile(dto, multipartFiles);
		}

		List<MbrBnefDetailExtend> mbrBnefDetailList = memberBenefitApiSelectService.getMemberBenefitList(bnefSectCd);
		MbrBnefDetail mbrBnefDetail = null;
		if(mbrBnefDetailList != null && mbrBnefDetailList.size() > 0){
			mbrBnefDetail = mbrBnefDetailList.get(0);

			Mbr mbr = dto.getMbr();

			// 포인트 지급 관련
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			mbrWebpntHist.setWebpntTpCd("WEBPNT");
			mbrWebpntHist.setWebpntResnCd(mbrBnefDetail.getWebpntResnCd());
			mbrWebpntHist.setWebpntDetailResnCd(mbrBnefDetail.getWebpntDetailResnCd());
			mbrWebpntHist.setWebpntAmt(mbrBnefDetail.getWebpntAmt());
			mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
			mbrWebpntHist.setUseBegDt(new Date());
			mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusDate(new Date(), mbrBnefDetail.getWebpntValidDaycnt()), 1));
			mbrWebpntHist.setResnDscr(CodeUtil.getCode("KOR", mbrBnefDetail.getWebpntDetailResnCd()).getCdNm());
			mbrWebpntHist.setGodNo(dto.getGodGodEvl().getGodNo());
			mbrWebpntHist.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
			//if (Arrays.asList("MBM", "SAM").contains(dto.getMallId())) { // 2019.01.08 포인트 적립시 mall id 추가 (MLB, SA 만)
			mbrWebpntHist.setMallId(dto.getMallId());
			mbrWebpntHist.setOrdNo(dto.getGodGodEvl().getOrdNo());
			//}

			// 적립내역 조회
			List<MbrWebpntHistExtend> upperWebPntList = null;
			if (Arrays.asList("MBM", "SAM").contains(dto.getMallId())) { // 2019.01.10 포인트 조회 mall id 추가 (MLB, SA 만)
				upperWebPntList = memberBenefitSelectService.selectWebPointInfoByCriteria(mbrWebpntHist);
			} else {
				upperWebPntList = memberBenefitSelectService.selectWebPointInfoByGodEvl(mbrWebpntHist);
			}
			// P포인트적립
			if("KOR".equals(pk.getLang())
					&& (mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString()) || mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.ONLINE_MBR.toString()))
					&& upperWebPntList.isEmpty()){	//  다중적립 체크조건 추가

				String app_type = "";
				String dvcType = "MB_";
				if(EventEnum.EventDevice.MOBILE_APP.toString().equals(pk.getDevice())){
					app_type=" clientApp";
				}
				if(EventEnum.EventDevice.PC.toString().equals(pk.getDevice())){
					dvcType="PC_";
				}

				log.info(dvcType+"PLGRIM_ACCML_DCSN_START "+app_type);
				try{
					memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
					log.info(dvcType+"PLGRIM_ACCML_DCSN_SUCCESS "+app_type);
				}catch(Exception e){
					log.info(dvcType+"PLGRIM_ACCML_DCSN_FAIL "+app_type);
					throw e;
				}
			}
		}
	}

	public void insertMyGoodsReviewByApp(SystemPK pk, MemberOrdGodFoDTO dto)  {

		try {
			memberBaseCommandService.setSystemPK(dto.getGodGodEvl(),pk);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		int godEvlTurn = memberActivityCommandService.insertMyGoodsReview(pk, dto);

		BigDecimal pntAmt = new BigDecimal(500);
		if(dto.getFileNames() !=null && dto.getFileNames().size() > 0){
			for(String uploadInfo : dto.getFileNames()){
				if(!uploadInfo.isEmpty()){
					pntAmt = new BigDecimal(1000);
					break;
				}

			}
			memberActivityCommandService.insertMyGoodsReviewAtchFileByApp(dto);
		}

		Mbr mbr = dto.getMbr();

		try {
			insertMbrSizeInfo(pk, dto, godEvlTurn, mbr);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

//	    //나중에 이벤트 포인트 적립으로 변경 할거임
//	    if(mbr.getMbrTpCd().equals(MemberTpCd.UNITY_MBR.toString()) && !StringService.isEmpty(mbr.getErpCstmrNo())){
//
//	    	MbrPntIntrlckHist mbrPntIntrlckHist = new MbrPntIntrlckHist();
//	    	mbrPntIntrlckHist.setMbrNo(mbr.getMbrNo());
//	    	mbrPntIntrlckHist.setPntTpCd("MBSH_PNT");
//	    	mbrPntIntrlckHist.setPntAplResnCd("GOD_EVL_WRITNG_ACCML");
//	    	mbrPntIntrlckHist.setPntAmt(pntAmt);
//	    	mbrPntIntrlckHist.setPntResnDscr("Goods Review");
//	    	mbrPntIntrlckHist.setGodNo(dto.getGodGodEvl().getGodNo());
//	    	mbrPntIntrlckHist.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
//
//	    	BpCbChangeSDO inputBpCbChange = new BpCbChangeSDO();
//	    	inputBpCbChange.setPartner(mbr.getErpCstmrNo());
//	    	inputBpCbChange.setCbType("30"); // 10:적립
//	    	inputBpCbChange.setCbUseAmt(String.valueOf(pntAmt));
//	    	inputBpCbChange.setReasonCb("상품 리뷰 작성");
//
//	    	memberReserveService.processBpCbChange(mbrPntIntrlckHist, inputBpCbChange, mbr.getMbrId());
//	    }

		// P포인트적립
		if("KOR".equals(pk.getLang()) && (mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.UNITY_MBR.toString()) || mbr.getMbrTpCd().equals(MemberEnum.MemberTpCd.ONLINE_MBR.toString()))){
			MbrWebpntHist mbrWebpntHist = new MbrWebpntHist();
			mbrWebpntHist.setMbrNo(mbr.getMbrNo());
			mbrWebpntHist.setWebpntTpCd("WEBPNT");
			mbrWebpntHist.setWebpntResnCd("EVT");
			mbrWebpntHist.setWebpntDetailResnCd("GOD_REV");
			mbrWebpntHist.setWebpntAmt(pntAmt);
			mbrWebpntHist.setWebpntStatCd("ACCML_DCSN");
			mbrWebpntHist.setUseBegDt(new Date());
			mbrWebpntHist.setUseEndDt(DateService.minusDate(DateService.plusYears(new Date(), 1), 1));	// 유효기간 1년
			mbrWebpntHist.setResnDscr("Goods Review");
			mbrWebpntHist.setGodNo(dto.getGodGodEvl().getGodNo());
			mbrWebpntHist.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());

			memberBenefitCommandService.insertWebPoint(mbrWebpntHist);
		}
	}

	@Override
	public void updateMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto, List<MultipartFile> multipartFiles)  {
		memberActivityCommandService.updateMyGoodsReview(pk, dto);

		if ("PC".equals(pk.getDevice())) {
			if (dto.getAtchFileTurn() != null) {
				for (int i = 0; i < dto.getAtchFileTurn().size(); i++) {
					if (getStringService().isNotEmpty(dto.getAtchFileTurn().get(i))) {

						GodGodEvlAtchFile delAtchFile = new GodGodEvlAtchFile();

						delAtchFile.setGodNo(dto.getGodGodEvl().getGodNo());
						delAtchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
						delAtchFile.setAtchFileTurn(Integer.parseInt(dto.getAtchFileTurn().get(i)));
						dto.setGodGodEvlAtchFile(delAtchFile);

						memberActivityCommandService.deleteGodGodEvlAtchFile(dto, multipartFiles);
					}
				}
			}
		} else {

			GodGodEvlAtchFile delAtchFile = new GodGodEvlAtchFile();

			delAtchFile.setGodNo(dto.getGodGodEvl().getGodNo());
			delAtchFile.setGodEvlTurn(dto.getGodGodEvl().getGodEvlTurn());
			dto.setGodGodEvlAtchFile(delAtchFile);

			memberActivityCommandService.deleteGodGodEvlAtchFile(dto, multipartFiles);

		}

		if(multipartFiles !=null && multipartFiles.size() > 0 ){
			memberActivityCommandService.insertMyGoodsReviewAtchFile(dto, multipartFiles);
		}

	}

	@Override
	public void updateMyGoodsReviewByApp(SystemPK pk, MemberOrdGodFoDTO dto)  {
		memberActivityCommandService.updateMyGoodsReview(pk, dto);
		if(dto.getFileNames() !=null && dto.getFileNames().size() > 0){
			memberActivityCommandService.insertMyGoodsReviewAtchFileByApp(dto);
		}

	}


	@Override
	public void deleteMyGoodsReview(SystemPK pk, MemberOrdGodFoDTO dto)  {
		memberActivityCommandService.deleteMyGoodsReview(pk, dto);
	}

	@Override
	public void deleteMyTodayGodList(MypageFoDTO dto)  {
		memberActivityCommandService.deleteMyTodayGodList(dto);
	}

	@Override
	public void deleteAllTodayGoodList(MypageFoDTO dto)  {
		memberActivityCommandService.deleteAllTodayGoodList(dto);
	}

	@Override
	public void deleteMyInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO) {
		memberActivityCommandService.deleteMyInquiry(mypageMtmFoDTO);
	}

	@Override
	public void updateMypageInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO)  {
		memberActivityCommandService.updateMypageInquiry(mypageMtmFoDTO);
	}

	@Override
	public void updateMyInquiry(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO,List<MultipartFile> multipartFiles)  {
		memberActivityCommandService.updateMyInquiry(mypageMtmFoDTO);
		if(multipartFiles !=null && multipartFiles.size() > 0){
			memberActivityCommandService.updateMyFileInquiry(mypageMtmFoDTO, multipartFiles);
		}
	}

	@Override
	public void updateMyInqAnsEvl(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO) {
		memberActivityCommandService.updateMyInqAnsEvl(mypageMtmFoDTO);
	}

	@Override
	public boolean saveMemberSize(String mbrNo, String mbrSizeNm, String height, String weight, String waist, String body) throws Exception {
		return memberActivityCommandService.saveMemberSize(mbrNo, mbrSizeNm, height, weight, waist, body);
	}

	public int deleteMyBrand(SystemPK pk, SecurityUserDetail userDetail, String brndId)  {
		MbrBukmkBrnd mbrBukmkBrnd = new MbrBukmkBrnd();
		mbrBukmkBrnd.setMallId(pk.getMall());
		mbrBukmkBrnd.setMbrNo(userDetail.getMbr().getMbrNo());
		mbrBukmkBrnd.setBrndId(brndId);

		return memberActivityCommandService.deleteMyBrand(mbrBukmkBrnd);
	}

	@Override
	public int addMyBrand(SystemPK pk, SecurityUserDetail userDetail, String brndId)  {
		MbrBukmkBrnd mbrBukmkBrnd = new MbrBukmkBrnd();
		mbrBukmkBrnd.setMallId(pk.getMall());
		mbrBukmkBrnd.setMbrNo(userDetail.getMbr().getMbrNo());
		mbrBukmkBrnd.setBrndId(brndId);
		mbrBukmkBrnd.setRegtrId(userDetail.getMbr().getMbrNo());
		mbrBukmkBrnd.setUdterId(userDetail.getMbr().getMbrNo());

		return memberActivityCommandService.addMyBrand(mbrBukmkBrnd);
	}


	@Override
	public void mergeMbrSizeClfc(MemberSizeClfcDTO memberSizeClfcDTO) {

		try {
			String mbrNo = memberSizeClfcDTO.getMbrNo();
			// 나의 기준 정보 저장
			MbrSizeClfc mbrSizeClfc = new MbrSizeClfc();
			mbrSizeClfc.setMbrNo(mbrNo);
			mbrSizeClfc.setMbrSizeTurn(Integer.parseInt(memberSizeClfcDTO.getMbrSizeTurn()));
			mbrSizeClfc.setMbrSizeNm(memberSizeClfcDTO.getMbrSizeNm());
			mbrSizeClfc.setMbrSizeClfcCd(memberSizeClfcDTO.getMbrSizeClfcCd());
			mbrSizeClfc.setUdterId(memberSizeClfcDTO.getUdterId());
			mbrSizeClfc.setRegtrId(memberSizeClfcDTO.getRegtrId());

			memberActivityCommandService.mergeMbrSizeClfc(mbrSizeClfc);

			// 현재 설정된 정보가 기본 정보일 경우, 이외의 기본정보는 기본정보 미지정으로 설정한다.
			if ("ALL_BASE_SIZE".equals(memberSizeClfcDTO.getMbrSizeClfcCd())
					|| "MEN_BASE_SIZE".equals(memberSizeClfcDTO.getMbrSizeClfcCd())
					|| "WOMEN_BASE_SIZE".equals(memberSizeClfcDTO.getMbrSizeClfcCd())
					|| "KIDS_BASE_SIZE".equals(memberSizeClfcDTO.getMbrSizeClfcCd())) {

				memberActivityCommandService.updateMbrSizeClfcUnds(mbrSizeClfc);
			}
			// 나의 사이즈 정보 상세 저장
			this.mergeMbrSizeClfcDetail(memberSizeClfcDTO);

		} catch(Exception e) {
			log.error("나의 사이즈 저장 시 오류 : ", e);
			throw new RuntimeException(e);
		}
	}

	private void mergeMbrSizeClfcDetail(MemberSizeClfcDTO memberSizeClfcDTO) {

		MbrSizeClfcDetail mbrSizeClfcDetail = new MbrSizeClfcDetail();

		// 회원 사이즈 분류 상세 기본정보 세팅
		mbrSizeClfcDetail.setMbrNo(memberSizeClfcDTO.getMbrNo());
		mbrSizeClfcDetail.setMbrSizeTurn(Integer.parseInt(memberSizeClfcDTO.getMbrSizeTurn()));
		mbrSizeClfcDetail.setRegtrId(memberSizeClfcDTO.getRegtrId());
		mbrSizeClfcDetail.setUdterId(memberSizeClfcDTO.getUdterId());

		// 키 정보 수정
		mbrSizeClfcDetail.setMbrSizeCd("HEIGHT");
		mbrSizeClfcDetail.setSizeVal(memberSizeClfcDTO.getHeight());
		mbrSizeClfcDetail.setSizeUnitCd("CM");
		memberActivityCommandService.mergeMbrSizeClfcDetail(mbrSizeClfcDetail);

		// 허리사이즈 정보 수정
		mbrSizeClfcDetail.setMbrSizeCd("WAIST_GRTH");
		mbrSizeClfcDetail.setSizeVal(memberSizeClfcDTO.getWaist());
		mbrSizeClfcDetail.setSizeUnitCd("INCH");
		memberActivityCommandService.mergeMbrSizeClfcDetail(mbrSizeClfcDetail);

		// 몸무게 정보 수정
		mbrSizeClfcDetail.setMbrSizeCd("BD_WT");
		mbrSizeClfcDetail.setSizeVal(memberSizeClfcDTO.getWeight());
		mbrSizeClfcDetail.setSizeUnitCd("KG");
		memberActivityCommandService.mergeMbrSizeClfcDetail(mbrSizeClfcDetail);

		mbrSizeClfcDetail.setMbrSizeCd("BODY");
		mbrSizeClfcDetail.setSizeVal(memberSizeClfcDTO.getBody());
		mbrSizeClfcDetail.setSizeUnitCd("");
		memberActivityCommandService.mergeMbrSizeClfcDetail(mbrSizeClfcDetail);

	}

	@Override
	public void deleteMbrSizeClfc(String mbrNo, String mbrSizeTurn){
		memberActivityCommandService.deleteMbrSizeClfc(mbrNo, mbrSizeTurn);
	}

	@Override
	public void deleteMbrSizeClfcDetail(String mbrNo, String mbrSizeTurn){
		memberActivityCommandService.deleteMbrSizeClfcDetail(mbrNo, mbrSizeTurn);
	}

	/**
	 * 리뷰 작성 시 회원 사이즈 정보 저장
	 *
	 * @param pk
	 * @param dto
	 * @param godEvlTurn
	 * @param mbr
	 */
	private void insertMbrSizeInfo(SystemPK pk, MemberOrdGodFoDTO dto, int godEvlTurn, Mbr mbr) throws Exception {

		// leaf 카테고리 번호 조회
		String leafDspCtgryNo = "";

	   /* TODO	상품재작업필요 :
	    GoodsSearchFoDTO goodsSearchFoDTO = new GoodsSearchFoDTO();
	    goodsSearchFoDTO.setMallId(pk.getMall());
	    goodsSearchFoDTO.setGodNo(dto.getGodGodEvl().getGodNo());
	    String leafDspCtgryNo = goodsInfoSelectService.selectGodLocationDspCtgryNo(goodsSearchFoDTO);
	    */

		String mbrSizeClfcCd = MemberEnum.MbrSizeClfc.ALL_BASE_SIZE.toString();

		if (StringService.isNotEmpty(leafDspCtgryNo)) {
			// 상품에 매핑된 전시매장 카운트를 조회하여 2개 이상일 경우 추천성별코드를 조회하여 사이즈 구분을 세팅함
			int dspCtgryCnt = 1;

			// TODO	상품재작업필요 : int dspCtgryCnt = goodsInfoSelectService.selectDspCtgryCount(dto.getGodGodEvl().getGodNo());

			if (dspCtgryCnt > 1) {
				God search = new God();
				search.setGodNo(dto.getGodGodEvl().getGodNo());
				God god = godRepository.selectGod(search);

				if (god != null && StringService.isNotEmpty(god.getRecomendSexCd())) {
					if (StringService.equalsIgnoreCase(god.getRecomendSexCd(), GoodsEnum.RecommendSexCd.CMNUSE.toString())) {
						mbrSizeClfcCd = MemberEnum.MbrSizeClfc.ALL_BASE_SIZE.toString();
					}
					else if (StringService.equalsIgnoreCase(god.getRecomendSexCd(), GoodsEnum.RecommendSexCd.WOMEN.toString())) {
						mbrSizeClfcCd = MemberEnum.MbrSizeClfc.WOMEN_BASE_SIZE.toString();
					}
					else if (StringService.equalsIgnoreCase(god.getRecomendSexCd(), GoodsEnum.RecommendSexCd.MEN.toString())) {
						mbrSizeClfcCd = MemberEnum.MbrSizeClfc.MEN_BASE_SIZE.toString();
					}
					else {
						mbrSizeClfcCd = MemberEnum.MbrSizeClfc.KIDS_BASE_SIZE.toString();
					}
				}
			}
			else {
				// 카테고리 유형 체크
				mbrSizeClfcCd = memberPersonalInfoSelectService.selectMbrSizeClfcCd(leafDspCtgryNo);
			}

		}

		// 리뷰와 연결된 회원 사이즈 정보 저장
		GoodsReviewDTO goodsReviewFoDTO = new GoodsReviewDTO();
		goodsReviewFoDTO.setGodNo(dto.getGodGodEvl().getGodNo());
		goodsReviewFoDTO.setGodEvlTurn(godEvlTurn);
		goodsReviewFoDTO.setMbrNo(mbr.getMbrNo());
		goodsReviewFoDTO.setMbrSizeClfcCd(mbrSizeClfcCd);
		goodsReviewFoDTO.setLangCd(pk.getLang());
		memberActivityCommandService.insertMbrSizeInfoConnectedGoodsReview(goodsReviewFoDTO);

	}


	/**
	 * 기본배송지 설정
	 */
	@Override
	public void updateUserDeliveryBase(SystemPK systemPK, MypageFoDTO mypageFoDTO)  {
		// step 1. 대표배송지로 설정된 배송지 확인
		MbrDlvsp mbrDlvsp = new MbrDlvsp();
		mbrDlvsp = mypageFoDTO.getMbrDlvsp();
		mbrDlvsp.setBaseDlvspYn(MemberEnum.YES.toString());

		// 검색을 위한 언어 코드 추가  결함 #26651 수정
		mbrDlvsp.setLang(systemPK.getLang());

		// step 2. 기존 대표 배송지 설정 해제
		memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mbrDlvsp, "");

		// step 3. 대표배송지 설정
		memberOrderCommandService.updateBaseDeliveryLocationSetBy(mbrDlvsp, "");

		if(MemberEnum.YES.toString().equals(mypageFoDTO.getMember())){
			// 회원기본정보 변경(이력포함)
			List<MemberBoResult> memberBoResult = memberOrderSelectService.selectDeliveryLocationList(mbrDlvsp);

			Mbr mbr = new Mbr();
			for (int i = 0; i < memberBoResult.size(); i++) {
				if(mbrDlvsp.getDlvAdbukTurn() == memberBoResult.get(i).getMbrDlvsp().getDlvAdbukTurn()){
					mbr.setMbrNo(memberBoResult.get(i).getMbrDlvsp().getMbrNo()); /* 회원번호 */
					mbr.setMbrBaseAddr(memberBoResult.get(i).getMbrDlvsp().getBaseAddr()); /* 수취인 기본주소 */
					mbr.setMbrAddrSectCd(memberBoResult.get(i).getMbrDlvsp().getDlvAddrSectCd()); /* 주소구분코드 */
					mbr.setMbrDetailAddr(memberBoResult.get(i).getMbrDlvsp().getDetailAddr());/* 수취인 상세주소 */
					mbr.setMbrCtyNm(memberBoResult.get(i).getMbrDlvsp().getCtyNm());/* 수취인 도시명  */
					mbr.setMbrLcltyNm(memberBoResult.get(i).getMbrDlvsp().getLcltyNm());/* 수취인 지방명 */
					mbr.setMbrPostNo(memberBoResult.get(i).getMbrDlvsp().getPostNo());/* 수취인 우편번호 */
					mbr.setMobilNationNo(memberBoResult.get(i).getMbrDlvsp().getMobilNationNo());  /* 수취인 휴대전화 국가번호 */
					mbr.setMobilAreaNo(memberBoResult.get(i).getMbrDlvsp().getMobilAreaNo());  /* 수취인 휴대전화 지역번호 */
					mbr.setMobilTlofNo(memberBoResult.get(i).getMbrDlvsp().getMobilTlofNo());  /* 수취인 휴대전화 국번호 */
					mbr.setMobilTlofWthnNo(memberBoResult.get(i).getMbrDlvsp().getMobilTlofWthnNo());  /* 수취인 휴대전화 국내번호 */
					mbr.setTelNationNo(memberBoResult.get(i).getMbrDlvsp().getTelNationNo());  /* 수취인 전화 국가번호 */
					mbr.setTelAreaNo(memberBoResult.get(i).getMbrDlvsp().getTelAreaNo()); /* 수취인 전화 지역번호 */
					mbr.setTelTlofNo(memberBoResult.get(i).getMbrDlvsp().getTelTlofNo());  /* 수취인 전화 국번호 */
					mbr.setTelTlofWthnNo(memberBoResult.get(i).getMbrDlvsp().getTelTlofWthnNo());  /* 수취인 전화 국내번호 */
					MemberFoDTO dto = new MemberFoDTO();
					dto.setMbr(mbr);

					// step 1. 개인정보이용수정 등록
					MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(dto.getMbr().getMbrNo(), "", mbrDlvsp.getMbrNo(), true);
					String[] codeArr = {
							MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd.MBR_ADDR.toString()					// 주소
							, MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd.MOBIL_NO.toString()					// 휴대전화번호
							, MemberPersonalInfoEnum.MbrPsnlInfoUdtSectCd.PHON_NO.toString()					// 유선전화번호
					};
					mpim.setModLcSectCd(MemberPersonalInfoEnum.MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
					memberPersonalInfoCommandService.insertPersonalInfoForMbr(dto.getMbr(), mpim, codeArr, true);

					// 회원정보 업데이트
					memberBaseCommandService.updateFoMember(dto);

					// 개인정보변경이력 ERP 전송 여부 업데이트 (오류발생되더라도 프로세스에 영향 없도록 처리)
					try{
						memberPersonalInfoCommandService.updatePersonalInfoErpTrnsmisYn(mpim, mbrDlvsp.getMbrNo());
					} catch(Exception e){
						if(log.isInfoEnabled()) log.info("> updatePersonalInfoErpTrnsmisYn Exception : {}", e.getMessage());
					}
					break;
				}
			}
		}
	}

	/**
	 * 배송지 삭제
	 */
	@Override
	public void deleteUserDelivery(SystemPK systemPK, MypageFoDTO mypageFoDTO)  {
		MbrDlvsp mdParam = new MbrDlvsp();

		mdParam = mypageFoDTO.getMbrDlvsp();


		// 개인정보변경이력 SEQ 생성
		MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mdParam.getMbrNo(), "", mdParam.getMbrNo(), true);

		// 배송지 다건 삭제
		boolean isReg = mdParam.getDlvAdbukTurn() == null ? true : false;

		// step 1. 개인정보변경이력 등록
		String[] codeArr = {
				MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()				// 배송지 주소
				, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()				// 배송지 수취인
				, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()				// 배송지 전화 번호
				, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()			// 배송지 휴대전화 번호
		};
		mpim.setModLcSectCd(MemberPersonalInfoEnum.MemberModLcSectCd.ONLNE_MALL.toString()); // 수정이력 수정몰
		memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mdParam, mpim, codeArr, isReg);

		// step 2. 대표배송지 설정값 확인
		if(StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), "1") || StringService.equalsIgnoreCase(mdParam.getBaseDlvspYn(), MemberEnum.YES.toString())){
			mdParam.setBaseDlvspYn(MemberEnum.YES.toString());
		} else {
			mdParam.setBaseDlvspYn(MemberEnum.NO.toString());
		}

		// step 3. 배송지 삭제
		memberOrderCommandService.deleteDeliveryLocationBy(mdParam, "hongsub.lim");
	}

	/**
	 * 배송지 변경시  회원배송지 테이블 추가/수정
	 */
	@Override
	public void updateDeliveryLocationMbrDlvsp(SystemPK systemPK, LgsDlvsp lgsDlvsp , String defaultDelv, String addMbrDlvspCheck)  {

		SecurityUserDetail userDetail = (SecurityUserDetail) ContextService.getCurrentUserDetail();

		Mbr mbr = userDetail.getMbr();

		if(defaultDelv == null){
			defaultDelv="N";
		}
		if(addMbrDlvspCheck == null){
			addMbrDlvspCheck ="N";
		}

		if ("Y".equalsIgnoreCase(defaultDelv) ||
				"Y".equalsIgnoreCase(addMbrDlvspCheck)) {
			MbrDlvsp mbrDlvsp = new MbrDlvsp();
			try {
				Functions.copyProperties2(mbrDlvsp, lgsDlvsp);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			mbrDlvsp.setDlvAddrSectCd(lgsDlvsp.getAddrSectCd());
			mbrDlvsp.setMbrNo(mbr.getMbrNo());
			mbrDlvsp.setRegtrId(mbr.getMbrNo());
			mbrDlvsp.setRegDt(lgsDlvsp.getRegDt()); //등록일시
			mbrDlvsp.setUdterId(mbr.getMbrNo());
			mbrDlvsp.setUdtDt(lgsDlvsp.getRegDt()); //수정일시
			mbrDlvsp.setLang(systemPK.getLang());

			//동일 회원배송지 존재여부 체크 존재하면 그 배송지 순번 가져옴
			//존재하면 수정, 없으면 수정
			mbrDlvsp.setDlvAdbukTurn(memberOrderSelectService.getMbrDeliveryAdbukTurn(mbrDlvsp));


			String mbrId = mbr.getMbrNo();

			if ("Y".equalsIgnoreCase(defaultDelv)) {
				mbrDlvsp.setBaseDlvspYn("Y");
				memberOrderCommandService.updateBaseDeliveryLocationRevomeBy(mbrDlvsp, mbrId);
			}

			if ("null".equals(String.valueOf(mbrDlvsp.getDlvAdbukTurn()))) {//insert하는경우
				mbrDlvsp.setBaseDlvspYn(defaultDelv);
			}

			memberOrderCommandService.mergeDeliveryLocationBy(mbrDlvsp, mbrId);


			boolean isReg = mbrDlvsp.getDlvAdbukTurn() == null ? true : false;


			// 개인정보 저장
			MbrPsnlInfoModHist mpim = memberPersonalInfoCommandService.setMbrPsnlInfoModHist(mbr.getMbrNo(), "", mbrId, true);


			// step 1. 개인정보변경이력 등록
			String[] codeArr = {
					MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDR.toString()                // 배송지 주소
					, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_ADDRSE.toString()                // 배송지 수취인
					, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_TEL_NO.toString()                // 배송지 전화 번호
					, MemberPersonalInfoEnum.MbrDlvspPsnlInfoUdtSectCd.DLVSP_MOBIL_NO.toString()            // 배송지 휴대전화 번호
			};
			memberPersonalInfoCommandService.insertPersonalInfoForMbrDlvsp(mbrDlvsp, mpim, codeArr, isReg);

		}

	}


	@Override
	public boolean hasDeliveryLocation(MbrDlvsp mbrDlvsp)  {
		return memberOrderCommandService.hasDeliveryLocation(mbrDlvsp);
	}

	@Override
	public List<MypageMtmFoResult> selectOrdGodList(SystemPK pk, MypageMtmFoDTO mypageMtmFoDTO)  {
		// TODO Auto-generated method stub
		return memberOrderSelectService.selectOrdGodList(mypageMtmFoDTO);
	}

	/**
	 * 회원 주문 배송지 조회(픽업주문 일반 배송 전환용).
	 */
	@Override
	public LgsDlvsp selectPkupOrderDeliveryLocationPop(SystemPK systemPK, MbrDlvsp mbrDlvsp)  {
		LgsDlvsp lgsDlvsp =  new LgsDlvsp();
		List<MemberBoResult> memberBoResult = memberOrderSelectService.selectDeliveryLocationList(mbrDlvsp);

		if(memberBoResult.size() > 0){
			lgsDlvsp.setAddrseNm(memberBoResult.get(0).getMbrDlvsp().getAddrseNm()); /* 수취인명 */
			lgsDlvsp.setAddrseBaseAddr(memberBoResult.get(0).getMbrDlvsp().getBaseAddr()); /* 수취인 기본주소 */
			lgsDlvsp.setAddrSectCd(memberBoResult.get(0).getMbrDlvsp().getDlvAddrSectCd()); /* 주소구분코드 */
			lgsDlvsp.setAddrseNationCd(memberBoResult.get(0).getMbrDlvsp().getNationCd());/* 수취인 국가코드 */
			lgsDlvsp.setAddrseDetailAddr(memberBoResult.get(0).getMbrDlvsp().getDetailAddr());/* 수취인 상세주소 */
			lgsDlvsp.setAddrsePostNo(memberBoResult.get(0).getMbrDlvsp().getPostNo());/* 수취인 우편번호 */
			lgsDlvsp.setAddrseMobilNationNo(memberBoResult.get(0).getMbrDlvsp().getMobilNationNo());  /* 수취인 휴대전화 국가번호 */
			lgsDlvsp.setAddrseMobilAreaNo(memberBoResult.get(0).getMbrDlvsp().getMobilAreaNo());  /* 수취인 휴대전화 지역번호 */
			lgsDlvsp.setAddrseMobilTlofNo(memberBoResult.get(0).getMbrDlvsp().getMobilTlofNo());  /* 수취인 휴대전화 국번호 */
			lgsDlvsp.setAddrseMobilTlofWthnNo(memberBoResult.get(0).getMbrDlvsp().getMobilTlofWthnNo());  /* 수취인 휴대전화 국내번호 */
			lgsDlvsp.setAddrseTelNationNo(memberBoResult.get(0).getMbrDlvsp().getTelNationNo());  /* 수취인 전화 국가번호 */
			lgsDlvsp.setAddrseTelAreaNo(memberBoResult.get(0).getMbrDlvsp().getTelAreaNo()); /* 수취인 전화 지역번호 */
			lgsDlvsp.setAddrseTelTlofNo(memberBoResult.get(0).getMbrDlvsp().getTelTlofNo());  /* 수취인 전화 국번호 */
			lgsDlvsp.setAddrseTelTlofWthnNo(memberBoResult.get(0).getMbrDlvsp().getTelTlofWthnNo());  /* 수취인 전화 국내번호 */
		}


		return lgsDlvsp;
	}


	/**
	 * 회원 주문 배송지 조회.
	 */
	@Override
	public LgsDlvsp selectOrderDeliveryLocationPop(SystemPK systemPK, LgsDlvspFoExtend lgsDlvspFoExtend)  {
		try {
			return orderSelectService.selectOrderDeliveryLocationPop(lgsDlvspFoExtend);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public LgsDlvspFoExtend selectOrderDeliveryLocationPopGlobal(SystemPK systemPK, LgsDlvspFoExtend lgsDlvspFoExtend)  {

		//ncp 3차 ********************************************************************************************

		lgsDlvspFoExtend.setLang(systemPK.getLang());
		try {
			return orderSelectService.selectOrderDeliveryLocationPopGlobal(lgsDlvspFoExtend);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//ncp 3차 ********************************************************************************************

	}

	/**
	 * 회원 주문 조회 상세.
	 */
	@Override
	public MypageOrderInfoDTO selectFoOrderInfo(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {

		//ncp 3차 ********************************************************************************************
		try {
			mypageOrderInfoDTO.setLang(systemPK.getLang());

			return orderSelectService.selectFoOrderInfo(mypageOrderInfoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//ncp 3차 ********************************************************************************************

	}

	/**
	 * 취소 반품 교환시 환불계좌 여부
	 */
	@Override
	public MypageOrderInfoDTO selectPayFoMobilPonPayRfd(SystemPK systemPK, String ordNo)  {
		try {
			return orderSelectService.selectPayFoMobilPonPayRfd(ordNo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 주문 상세 클레임.
	 */
	@Override
	public List<ClmFoExtend> selectClmFoList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {

		try {
			mypageOrderInfoDTO.setLang(systemPK.getLang());
			mypageOrderInfoDTO.setMallId(systemPK.getMall());

			return orderSelectService.selectClmFoList(mypageOrderInfoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 회원 배송추적 정보 조회.
	 */
	@Override
	public List<String[]> selectHanjinTracking(SystemPK systemPK, MypageFoDTO mypageFoDTO)  {


		String result = memberOrderSelectService.selectHanjinTracking(mypageFoDTO);
		List<String[]> hanjinTrackingList = new ArrayList<>();
		String[] hanjinTracking = result.split(";<br>;");

		for (int i = 0; i < hanjinTracking.length; i++) {
			hanjinTrackingList.add(hanjinTracking[i].split(";"));
		}
		if(hanjinTrackingList.size()>0){
			hanjinTrackingList.remove(0);

		}
		return hanjinTrackingList;
	}


	/**
	 * 회원 영수증 조회.
	 */
	@Override
	public Page<MypageOrderFoResult> selectFoReceiptList(SystemPK systemPK, MypageOrderInfoDTO dto, PageParam pageParam)  {
		dto.setMallId(systemPK.getMall());
		try {
			return orderSelectService.selectFoReceiptList(dto, pageParam);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<MypageOrderFoResult> selectFoReceiptList(SystemPK systemPK,  MypageOrderInfoDTO dto)  {
		dto.setMallId(systemPK.getMall());
		try {
			return orderSelectService.selectFoReceiptList(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String selectMultiLgsDlvYn(SystemPK systemPK, String ordNo)  {
		try {
			return orderSelectService.selectMultiLgsDlvYn(ordNo);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int selectFoOrderListCount(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {
		try {
			return orderSelectService.selectFoOrderListCount(mypageOrderInfoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 주문 조회.
	 */
	@Override
	public Page<MypageOrderFoResult> selectFoOrderList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam)  {

		try {
			if (mypageOrderInfoDTO != null
					&& StringService.isNotEmpty(mypageOrderInfoDTO.getDateStart())
					&& StringService.isNotEmpty(mypageOrderInfoDTO.getDateEnd())) {

				// 기간조회 조건이 1년이 넘어가는지 체크
				if (!checkOrderClaimSearchCondition(mypageOrderInfoDTO)) {

					// 넘어간다면 종료일 기준 1년 전 날짜를 시작일자에 강제 세팅
					SimpleDateFormat sdf = null;
					if (mypageOrderInfoDTO.getDateEnd().indexOf('.') > -1) {
						// 모바일 날짜 포맷
						sdf = new SimpleDateFormat("yyyy.MM.dd");
					} else if(mypageOrderInfoDTO.getDateEnd().indexOf('-') > -1) {
						// PC 날자 포맷
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					} else {
						sdf = new SimpleDateFormat("yyyyMMdd");
					}

					Calendar cal = Calendar.getInstance();
					Date edate = sdf.parse(mypageOrderInfoDTO.getDateEnd());
					cal.setTime(edate);
					cal.add(Calendar.YEAR, -1);

					Date stddate = cal.getTime();

					mypageOrderInfoDTO.setDateStart(sdf.format(stddate));
				}

			}

			mypageOrderInfoDTO.setLang(systemPK.getLang());
			mypageOrderInfoDTO.setMallId(systemPK.getMall());

			return orderSelectService.selectFoOrderList(mypageOrderInfoDTO, pageParam);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 클레임 내역 조회.
	 */
	@Override
	public Page<MypageClaimFoResult> selectFoClaimList(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO, PageParam pageParam)  {

		try {
			mypageOrderInfoDTO.setLang(systemPK.getLang());
			mypageOrderInfoDTO.setMallId(systemPK.getMall());

			return orderSelectService.selectFoClaimList(mypageOrderInfoDTO, pageParam);

		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}

	}


	/**
	 * 회원 결제수단 변경 조회.
	 */
	@Override
	public MypageOrderInfoDTO selectPayMethodChange(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {
		try {
			return orderSelectService.selectPayMethodChange(mypageOrderInfoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 픽업에서 일반배송으로 변경시 추가 배송비 조회
	 */
	@Override
	public List<MypageOrderInfoDTO> selectDlvCstSumAmt(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {
		try {
			return orderSelectService.selectDlvCstSumAmt(mypageOrderInfoDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 주문상품 옵션 조회
	 */
	@Override
	public List<MypageOrderFoResult> selectOrdGoodOption(SystemPK systemPK, MypageOrderInfoDTO dto)  {
		dto.setLang(systemPK.getLang());
		try {
			return orderSelectService.selectOrdGoodOption(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 주문세트상품 옵션 조회
	 */
	@Override
	public List<MypageOrderFoResult> selectOrdSetOption(SystemPK systemPK, MypageOrderInfoDTO dto)  {
		dto.setLang(systemPK.getLang());
		try {
			return orderSelectService.selectOrdSetOption(dto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * 픽업에서 일반배송으로 변경가능여부
	 */
	@Override
	public String selectDlvYn(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO)  {
		MypageOrderInfoDTO mypageDlvYn = memberOrderSelectService.getPickupDeliveryChangeYn(mypageOrderInfoDTO);
		return mypageDlvYn.getDlvYn();
	}

	/**
	 * 픽업에서 일반배송으로 변경가능여부 ( 옵션 판매상태 기준 : #47710 픽업판매중인 상품이 있는 경우 일반배송으로 전환 불가 )
	 */
	@Override
	public MypageOrderInfoDTO getPickupDeliveryChangeYnInfo(SystemPK systemPK, MypageOrderInfoDTO mypageOrderInfoDTO) {
		MypageOrderInfoDTO pickupDeliveryChangeYnInfo = memberOrderSelectService.getPickupDeliveryChangeYn(mypageOrderInfoDTO);
		if( "Y".equals(pickupDeliveryChangeYnInfo.getDlvYn()) ) {
			pickupDeliveryChangeYnInfo.setPickupDlvChgItmStatYn(memberOrderSelectService.getPickupDeliveryChangeYnByItmStat(mypageOrderInfoDTO.getOrdNo()));
		}else {
			pickupDeliveryChangeYnInfo.setPickupDlvChgItmStatYn("N");
		}
		return pickupDeliveryChangeYnInfo;
	}


	@Override
	public List<MbrSizeClfc> selectMbrSizeClfcInfo(MbrSizeClfc search) {
		return memberPersonalInfoSelectService.selectMbrSizeClfcInfo(search);
	}




	private boolean checkOrderClaimSearchCondition(MypageOrderInfoDTO mypageOrderInfoDTO) {
		try {
			Calendar cal = Calendar.getInstance();

			// 포맷 체크 PC는 날짜 포맷이 - 이므로 로직 추가함 (2016-10-05)
			SimpleDateFormat sdf = null;
			if (mypageOrderInfoDTO.getDateEnd().indexOf('.') > -1) {
				// 모바일 날짜 포맷
				sdf = new SimpleDateFormat("yyyy.MM.dd");
			} else if(mypageOrderInfoDTO.getDateEnd().indexOf('-') > -1) {
				// PC 날자 포맷
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} else {
				sdf = new SimpleDateFormat("yyyyMMdd");
			}

			Date edate = sdf.parse(mypageOrderInfoDTO.getDateEnd());

			cal.setTime(edate);
			cal.add(Calendar.YEAR, -1);
			Date stddate = cal.getTime();

			Date sdate = sdf.parse(mypageOrderInfoDTO.getDateStart());

			if (stddate.after(sdate)) {
				return false;
			}

			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 회원 배송지 조회.
	 */
	@Override
	public List<MemberBoResult> getDeliveryLocationList(SystemPK systemPK, MbrDlvsp mbrDlvsp, String loginId)  {

		if(StringService.isEmpty(mbrDlvsp.getLang())){
			mbrDlvsp.setLang(systemPK.getLang());
		}

		return memberOrderSelectService.selectDeliveryLocationList(mbrDlvsp);

	}

	/**
	 * 회원 배송지 조회 Page.
	 */
	@Override
	public Page<MemberBoResult> getDeliveryLocationPageList(SystemPK systemPK, MbrDlvsp mbrDlvsp, PageParam pageParam)  {
		if(StringService.isEmpty(mbrDlvsp.getLang())){
			mbrDlvsp.setLang(systemPK.getLang());
		}
		return memberOrderSelectService.selectDeliveryLocationPageList(mbrDlvsp , pageParam);
	}

	/**
     * 회원 주문 배송지 변경.
     */
    @Override
    public void updateDeliveryLocationChange(SystemPK systemPK, LgsDlvsp lgsDlvsp)  {
    	// 주문 배송지 업데이트
    	deliveryService.updateDeliveryLocationChange(lgsDlvsp);

    	// 배송지 변경 이력테이블 업데이트
    	LgsDlvspHist lgsDlvspHist = new LgsDlvspHist();
    	lgsDlvspHist.setOrdNo(lgsDlvsp.getOrdNo());
    	lgsDlvspHist.setDlvPcupspTurn(lgsDlvsp.getDlvPcupspTurn());

    	try {
			deliveryService.insertFoLgsDlvspHist(lgsDlvspHist);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }

	@Override
	public long selectMyGoodsReviewListCount(MemberOrdGodFoDTO dto) {
		return memberActivitySelectService.selectMyGoodsReviewListCount(dto);
	}

	@Override
	public int deleteMyGoodsReviewNtceYn(MemberOrdGodFoDTO dto) {
		return memberActivityCommandService.deleteMyGoodsReviewNtceYn(dto);
	}
	@Override
	public MypageWishFoResult selectGodWishList(MypageFoDTO dto)  {

		return memberActivitySelectService.selectGodWishList(dto);
	}
	@Override
	public MypageWishFoResult selectGodWishListCount(MypageFoDTO dto)  {

		return memberActivitySelectService.selectGodWishListCount(dto);
	}

	/**
     * 오프라인 구매내역 조회 - IF-ORD-05
     *
     * @param systemPK
     * @param sdo
     * @return
     */
	@Override
    public OrderFormerlyPurchaseSDO getFormerlyPurchaseList(SystemPK systemPK, OrderFormerlyPurchaseSDO sdo) {

		OrderFormerlyPurchaseSDO result = new OrderFormerlyPurchaseSDO();

		try {

			AdapterHeader header = new AdapterHeader();
			header.setRequestId(interfaceApiCommon.getRequestId());
			header.setLangCd(systemPK.getLang());
			header.setMallId(systemPK.getMall());
			header.setDvcCd(systemPK.getDevice());

			String jsonResult = orderAdapter.formerlyPurchaseList(sdo, header);
			result = JsonService.unmarshalling(jsonResult, OrderFormerlyPurchaseSDO.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return result;
    }

	/**
     * 이전구매내역조회 - IF-ORD-05
     *
     * @param systemPK
     * @param sdo
     * @return
     */
	@Override
    public OrderOfflinePurchaseSDO getOfflinePurchaseList(SystemPK systemPK, OrderOfflinePurchaseSDO sdo) {

		OrderOfflinePurchaseSDO result = new OrderOfflinePurchaseSDO();

		try {

			AdapterHeader header = new AdapterHeader();
			header.setRequestId(interfaceApiCommon.getRequestId());
			header.setLangCd(systemPK.getLang());
			header.setMallId(systemPK.getMall());
			header.setDvcCd(systemPK.getDevice());

			String jsonResult = orderAdapter.offlinePurchaseList(sdo, header);
			result = JsonService.unmarshalling(jsonResult, OrderOfflinePurchaseSDO.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return result;
    }

	/**
     * 이전주문 구매확정여부 조회 - IF-ORD-10
     *
     * @param systemPK
     * @param sdo
     * @return
     */
	@Override
    public OrderFormerlyPurchaseCfmInfoSDO formerlyPurchaseConfirmInfo(SystemPK systemPK, OrderFormerlyPurchaseCfmInfoSDO sdo) {

		OrderFormerlyPurchaseCfmInfoSDO result = new OrderFormerlyPurchaseCfmInfoSDO();

		try {

			AdapterHeader header = new AdapterHeader();
			header.setRequestId(interfaceApiCommon.getRequestId());
			header.setLangCd(systemPK.getLang());
			header.setMallId(systemPK.getMall());
			header.setDvcCd(systemPK.getDevice());

			String jsonResult = orderAdapter.formerlyPurchaseConfirmInfo(sdo, header);
			result = JsonService.unmarshalling(jsonResult, OrderFormerlyPurchaseCfmInfoSDO.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return result;
    }

	/**
	 * 회원 등급 조회
	 *
	 * @param mbrGrd
	 * @return mbrGrd
	 */
	public MbrGrd selectMbrGrd(MbrGrd mbrGrd) {
		try {
			return memberBaseSelectService.selectMbrGrd(mbrGrd);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
