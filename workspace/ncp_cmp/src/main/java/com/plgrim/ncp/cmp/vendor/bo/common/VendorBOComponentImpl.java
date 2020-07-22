package com.plgrim.ncp.cmp.vendor.bo.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.plgrim.ncp.base.entities.datasource1.com.Com;
import com.plgrim.ncp.base.entities.datasource1.com.ComChrger;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlc;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcHist;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLang;
import com.plgrim.ncp.base.entities.datasource1.com.ComDmstcDlvCstPlcLangHis;
import com.plgrim.ncp.base.enums.vendor.VendorEnum;
import com.plgrim.ncp.biz.vendor.data.VendorGridDTO;
import com.plgrim.ncp.biz.vendor.data.VendorSearchDTO;
import com.plgrim.ncp.biz.vendor.result.ComChrgerResult;
import com.plgrim.ncp.biz.vendor.result.ComDmstcDlvCstPlcResult;
import com.plgrim.ncp.biz.vendor.result.SysAdminListResult;
import com.plgrim.ncp.biz.vendor.result.VendorListResult;
import com.plgrim.ncp.biz.vendor.service.ComChargerService;
import com.plgrim.ncp.biz.vendor.service.ComDmstcDlvCstPlcLangService;
import com.plgrim.ncp.biz.vendor.service.ComDmstcDlvCstPlcService;
import com.plgrim.ncp.biz.vendor.service.ComOvseaDlvCstPlcService;
import com.plgrim.ncp.biz.vendor.service.VendorService;
import com.plgrim.ncp.cmp.vendor.bo.VendorBOComponent;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.data.SystemPK;

/**
 * [클래스 설명]
 *
 * <p>
 *
 * <ul>
 *   <li> [기능1]
 *   <li> [기능2]
 * </ul>.
 *
 * @author yoon.eun
 * @since 2015. 11. 10
 */
@Component
@Transactional(value = "transactionManager")
public class VendorBOComponentImpl implements VendorBOComponent {

	/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 업체 서비스. */
	@Autowired
	VendorService vendorService;

	/** 배송비 정책 서비스. */
	@Autowired
	ComDmstcDlvCstPlcService comDmstcDlvCstPlcService;

	/** 해외배송비 정책 서비스. */
	@Autowired
	ComOvseaDlvCstPlcService comOvseaDlvCstPlcService;
	
	/** 국내배송비 언어 정책 서비스. */
	@Autowired
	ComDmstcDlvCstPlcLangService comDmstcDlvCstPlcLangService;

	/** 업체 브랜드 담당자. */
	@Autowired
	ComChargerService comChargerService;


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
	 * 정산 정보 수정
	 */
	@Override
	public void updateVendor(SystemPK systemPK, Com com) throws Exception {

	   vendorService.updateVendor(com);
	}


	/**
	 * 배송비 정책 수정.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlc [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	@Override
    public void updateComDmstcDlvCstPlc(@ModelAttribute VendorGridDTO vendorGridDTO) throws Exception {
		ComDmstcDlvCstPlc comDmstcDlvCstPlc = vendorGridDTO.getComDmstcDlvCstPlc();
		ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist = new ComDmstcDlvCstPlcHist();
		
		comDmstcDlvCstPlc.setMallId(vendorGridDTO.getMallId());

		if(comDmstcDlvCstPlc.getRtrvlDrctPsbYn() == null || "".equals(comDmstcDlvCstPlc.getRtrvlDrctPsbYn())){//회수지시 가능 여부
			comDmstcDlvCstPlc.setRtrvlDrctPsbYn("N");
		}

		String loginId = BOSecurityUtil.getLoginId();
		if("null".equals(String.valueOf(comDmstcDlvCstPlc.getDmstcDlvCstPlcSn()))||StringService.isEmpty(String.valueOf(comDmstcDlvCstPlc.getDmstcDlvCstPlcSn()))){
			VendorSearchDTO vendorSearchDTO = new VendorSearchDTO();
			vendorSearchDTO.getCom().setComId(vendorGridDTO.getComId());
			//Long comDmstcDivCnt = comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcListCount(vendorSearchDTO);
			comDmstcDlvCstPlc.setRegtrId(loginId);
			comDmstcDlvCstPlc.setDlvMthdDscr(comDmstcDlvCstPlcService.selectBaseDlvMthdDscr());
			comDmstcDlvCstPlcService.insertComDmstcDlvCstPlc(comDmstcDlvCstPlc);

			comDmstcDlvCstPlc.setDlvPlcNm(comDmstcDlvCstPlc.getDlvPlcNm()+"(해외 배송)");
			comDmstcDlvCstPlc.setOvseaDlvDmstcDlvCstPlcYn("Y");
			comDmstcDlvCstPlcService.insertComDmstcDlvCstPlc(comDmstcDlvCstPlc);
		}else{
			comDmstcDlvCstPlcHist.setDmstcDlvCstPlcSn(comDmstcDlvCstPlc.getDmstcDlvCstPlcSn());
			comDmstcDlvCstPlcService.insertComDmstcDlvCstPlcHist(comDmstcDlvCstPlcHist);
//			if(comDmstcDlvCstPlc.getDlvMthdDscr() != null || comDmstcDlvCstPlc.getGfctDscr() != null){//배송기간 안내문구, 상품권 배송안내문구 있는 경우 일련번호를 삭제 한다.
//				comDmstcDlvCstPlc.setDmstcDlvCstPlcSn(null);
//			}
			// 국내배송정책 안내문구 수정
			comDmstcDlvCstPlcService.updateComDmstcDlvCstPlc(comDmstcDlvCstPlc);
			
			java.lang.Long comDmstcDlvCstPlcSn=comDmstcDlvCstPlc.getDmstcDlvCstPlcSn();
			ComDmstcDlvCstPlcLang comDmstcDlvCstPlcLang=new ComDmstcDlvCstPlcLang();
			comDmstcDlvCstPlcLang.setDmstcDlvCstPlcSn(comDmstcDlvCstPlcSn);
			comDmstcDlvCstPlcLang.setRegtrId(loginId);
			
			ComDmstcDlvCstPlcLangHis comDmstcDlvCstPlcLangHis=new ComDmstcDlvCstPlcLangHis();
			comDmstcDlvCstPlcLangHis.setDmstcDlvCstPlcSn(comDmstcDlvCstPlcSn);
			comDmstcDlvCstPlcLangHis.setRegtrId(loginId);
			
			if ("MCOM".equals(vendorGridDTO.getComTpCd())) {
				
				if (vendorGridDTO.getDlvMthdDscrEng() != null && !vendorGridDTO.getDlvMthdDscrEng().equals("")) {
					
					comDmstcDlvCstPlcLang.setLangCd("ENG");
					comDmstcDlvCstPlcLang.setMobileDlvMthdDscr(vendorGridDTO.getMobileDlvMthdDscrEng());
					comDmstcDlvCstPlcLang.setDlvMthdDscr(vendorGridDTO.getDlvMthdDscrEng());
					
					// 글로벌 해외배송정책 안내문구 수정
					comDmstcDlvCstPlcLangService.updateComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang);
					
					comDmstcDlvCstPlcLangHis.setLangCd("ENG");
					comDmstcDlvCstPlcLangHis.setMobileDlvMthdDscr(vendorGridDTO.getMobileDlvMthdDscrEng());
					comDmstcDlvCstPlcLangHis.setDlvMthdDscr(vendorGridDTO.getDlvMthdDscrEng());					
					// 글로벌 해외배송정책 안내문구 이력등록
					comDmstcDlvCstPlcLangService.insertComDmstcDlvCstPlcLangHis(comDmstcDlvCstPlcLangHis);
				}
				if (vendorGridDTO.getDlvMthdDscrChi() != null && !vendorGridDTO.getDlvMthdDscrChi().equals("")) {
					comDmstcDlvCstPlcLang.setLangCd("CH");
					comDmstcDlvCstPlcLang.setMobileDlvMthdDscr(vendorGridDTO.getMobileDlvMthdDscrChi());
					comDmstcDlvCstPlcLang.setDlvMthdDscr(vendorGridDTO.getDlvMthdDscrChi());
					
					// 중문 해외배송정책 안내문구 수정
					comDmstcDlvCstPlcLangService.updateComDmstcDlvCstPlcLang(comDmstcDlvCstPlcLang);
					
					comDmstcDlvCstPlcLangHis.setLangCd("CH");
					comDmstcDlvCstPlcLangHis.setMobileDlvMthdDscr(vendorGridDTO.getMobileDlvMthdDscrChi());
					comDmstcDlvCstPlcLangHis.setDlvMthdDscr(vendorGridDTO.getDlvMthdDscrChi());					
					// 중문 해외배송정책 안내문구 이력등록
					comDmstcDlvCstPlcLangService.insertComDmstcDlvCstPlcLangHis(comDmstcDlvCstPlcLangHis);
				}
				
			}

			if(!StringService.isEmpty(comDmstcDlvCstPlc.getComId())){
				//배송담당자
				vendorGridDTO.getComChrger().setComId(comDmstcDlvCstPlc.getComId());;
				vendorGridDTO.getComChrger().setChrgerJobCd("MD");
				vendorGridDTO.getComChrger().setUdterId(loginId);
				if(vendorGridDTO.getComChrger().getChrgerTurn() != null && vendorGridDTO.getComChrger().getChrgerTurn() != 0){
					vendorService.updateComChrger(vendorGridDTO.getComChrger());
				}else{
					vendorGridDTO.getComChrger().setRegtrId(loginId);
					vendorService.insertComChrger(vendorGridDTO.getComChrger());
				}

				//반품 담당자
				vendorGridDTO.getRetComChrger().setComId(comDmstcDlvCstPlc.getComId());;
				vendorGridDTO.getRetComChrger().setChrgerJobCd("RTGOD");
				vendorGridDTO.getRetComChrger().setUdterId(loginId);
				if(vendorGridDTO.getRetComChrger().getChrgerTurn() != null && vendorGridDTO.getRetComChrger().getChrgerTurn() != 0){
					vendorService.updateComChrger(vendorGridDTO.getRetComChrger());
				}else{
					vendorGridDTO.getRetComChrger().setRegtrId(loginId);
					vendorService.insertComChrger(vendorGridDTO.getRetComChrger());
				}
			}
		}
    }

	/**
	 * AS 담당자정보 저장/수정
	 */
	@Override
	public void updateAsAdmin(VendorGridDTO vendorGridDTO)throws Exception {
		String loginId = BOSecurityUtil.getLoginId();

		vendorGridDTO.getComChrger().setRegtrId(loginId);
		vendorGridDTO.getComChrger().setUdterId(loginId);
		vendorService.updateComChrger(vendorGridDTO.getComChrger());

		//업체 언어별 담당자 수정(한국어)
		//vendorGridDTO.getComChrger().setLang("KOR");
		//vendorService.updateComLangbyChrger(vendorGridDTO.getComChrger());

		//업체 언어별 담당자 수정(영어)
		vendorGridDTO.getComChrger().setLang("ENG");
		vendorGridDTO.getComChrger().setChrgerNm(vendorGridDTO.getEnChrgerNm());
		vendorService.updateComLangbyChrger(vendorGridDTO.getComChrger());

		//업체 언어별 담당자 수정(중국어)
		vendorGridDTO.getComChrger().setLang("CHI");
		vendorGridDTO.getComChrger().setChrgerNm(vendorGridDTO.getCnChrgerNm());
		vendorService.updateComLangbyChrger(vendorGridDTO.getComChrger());

	}

	/**
	 * 배송비정책 기본배송지 수정
	 */
	@Override
	public void updateComDmstcDlvCstPlcBaseDlv(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception {
		comDmstcDlvCstPlcService.updateComDmstcDlvCstPlcBaseDlv(comDmstcDlvCstPlc);
	}

	/**
	 * 배송비 정책 이력저장.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param comDmstcDlvCstPlcHist [설명]
	 * @since 2015. 4. 28
	 */
	@Override
    public void insertComDmstcDlvCstPlcHist(
            ComDmstcDlvCstPlcHist comDmstcDlvCstPlcHist) throws Exception {
		comDmstcDlvCstPlcService.insertComDmstcDlvCstPlcHist(comDmstcDlvCstPlcHist);

    }

	/**
	 * 도서산간배송비 저장.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	@Override
    public void mergeDlvCstPlcExcp(List<VendorGridDTO> dataList)
            throws Exception {
		comDmstcDlvCstPlcService.mergeDlvCstPlcExcp(dataList);

    }

	/**
	 * 도서산간배송비 삭제.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param vendorListResult [설명]
	 * @since 2015. 4. 17
	 */
	@Override
    public void deleteDlvCstPlcExcp(List<VendorGridDTO> dataList) throws Exception {
		comDmstcDlvCstPlcService.deleteDlvCstPlcExcp(dataList);

    }

	/**
	 * 업체 국내 배송비 정책 삭제
	 * @param dataList
	 * @throws Exception
	 */
	@Override
	public void deleteComDmstcDlvCstPlc(List<VendorGridDTO> dataList) throws Exception {
		comDmstcDlvCstPlcService.deleteComDmstcDlvCstPlc(dataList);
    }


	/**
	 * 업체 브랜드 담당자 저장
	 * @param lists
	 * @param loginId
	 * @throws Exception
	 */
	@Override
    public void saveVendorBrandAdmin(List<ComChrger> lists, String loginId) throws Exception {
		ComChrger cc = new ComChrger();
    	Iterator<ComChrger> iterator = lists.iterator();

    	while(iterator.hasNext()) {
    		cc = iterator.next();
    		comChargerService.mergeCompanyCharger(cc, loginId);
    	}

    }

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.vendor.bo.VendorBOComponent#getVendorList(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.vendor.data.VendorSearchDTO)
	 */
	@Override
	public Page<VendorListResult> getVendorPageList(SystemPK systemPK, VendorSearchDTO vendorSearchDTO) throws Exception {
		return vendorService.selectVendorPageList(vendorSearchDTO);
	}

	/**
	 * 업체 목록 조회 엑셀.
	 *
	 *
	 * @param systemPK [설명]
	 * @param vendorSearchDTO [설명]
	 * @return the vendor list excel
	 * @throws Exception the exception
	 * @since 2015. 5. 8
	 */
	@Override
	public List<Map<String, Object>> getVendorListExcel(SystemPK systemPK,
														VendorSearchDTO vendorSearchDTO) throws Exception {
		// TODO Auto-generated method stub
		return vendorService.selectVendorListExcel(vendorSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.vendor.bo.VendorBOComponent#getVendor(com.plgrim.ncp.framework.data.SystemPK, com.plgrim.ncp.biz.vendor.data.VendorSearchDTO)
	 */
	@Override
	public VendorListResult getVendor(SystemPK systemPK,
									  VendorSearchDTO vendorSearchDTO) throws Exception {

		return vendorService.selectVendor(vendorSearchDTO);
	}

	/**
	 * 배송비 정책 조회 콤보.
	 *
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	@Override
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcCombo(VendorSearchDTO searchDTO) throws Exception{
		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcCombo(searchDTO);
	}

	/**
	 * 배송비 정책 리스트 조회
	 * @param searchDTO
	 * @return
	 * @throws Exception
	 */
	@Override
	public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcList(VendorSearchDTO searchDTO) throws Exception {
		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcList(searchDTO);
	}

	/**
	 * 배송비 정책 조회.
	 *
	 * <p/>
	 *
	 * [사용 방법 설명].
	 *
	 * @param searchDTO [설명]
	 * @return Page [설명]
	 * @throws Exception the exception
	 * @since 2015. 4. 28
	 */
	@Override
	public ComDmstcDlvCstPlcResult selectComDmstcDlvCstPlc(
			VendorSearchDTO searchDTO) throws Exception {

		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlc(searchDTO);
	}

	//배송비 정책 상세 조회. (CS 에서 업체 기본 배송 정책 조회 용)
	@Override
	public List<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcFromCs(
			VendorSearchDTO searchDTO) throws Exception {

		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcFromCs(searchDTO);
	}

	/**
	 * 도서산간배송비 조회.
	 *
	 * @param searchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Page<ComDmstcDlvCstPlcResult> selectComDmstcDlvCstPlcExcp(
			VendorSearchDTO searchDTO) throws Exception {

		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcExcp(searchDTO);
	}

	/**
	 * 운영자 조회.
	 *
	 * @param searchDTO
	 * @return the ComDmstcDlvCstPlcResult
	 * @throws SQLException the SQL exception
	 */
	@Override
	public Page<SysAdminListResult> selectAdminList(VendorSearchDTO searchDTO) throws Exception{

		return vendorService.selectAdminList(searchDTO);
	}

	/**
	 * 운영자 조회(페이징X)
	 * @param vendorSearchDTO
	 * @return
	 * @throws Exception
	 */
	public List<SysAdminListResult> selectAdminInfoList(VendorSearchDTO vendorSearchDTO) throws Exception {
		return vendorService.selectAdminInfoList(vendorSearchDTO);
	}

	/**
	 * as 담당자 조회
	 * @param comChrger
	 * @return
	 * @throws Exception
	 */
	public List<ComChrgerResult> selectComChrgerList(ComChrger comChrger) throws Exception {
		return vendorService.selectComChrgerList(comChrger);
	}


	@Override
	public List<VendorListResult> selectVendorAllList(SystemPK systemPK, VendorSearchDTO searchDTO) throws Exception {
		// TODO Auto-generated method stub
		return vendorService.selectVendorAllList(searchDTO);
	}

	/**
	 * 업체 브랜드 담당자 조회
	 * @param systemPK
	 * @param comId
	 * @param brndId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<ComChrger> selectComChargerList(SystemPK systemPK, String comId, String brndId) throws Exception {
		List<ComChrger> comChargeList = new ArrayList<ComChrger>();

		for(VendorEnum.PartmalComChrgerJob cd : VendorEnum.PartmalComChrgerJob.values()){
			ComChrger cc = new ComChrger();
			cc.setComId(comId);
			cc.setBrndId(brndId);
			cc.setChrgerJobCd(cd.toString());

			ComChrger ccResult = comChargerService.selectCompanyCharger(cc);

			if(ccResult == null ){ ccResult = cc; }
			comChargeList.add(ccResult);
		}
		return comChargeList;
	}

	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

	@Override
	public List<Map<String, Object>> selectComDmstcDlvCstPlcExcpExcel(
			VendorSearchDTO searchDTO) throws Exception {
		// TODO Auto-generated method stub
		return comDmstcDlvCstPlcService.selectComDmstcDlvCstPlcExcpExcel(searchDTO);
	}

	/**
	 * 배송사 Url 구하기
	 * @param comDmstcDlvCstPlc
	 * @return 선택된 배송업체의 url
	 * @throws Exception
	 */
	@Override
	public String getDlvComUrl(ComDmstcDlvCstPlc comDmstcDlvCstPlc) throws Exception{
		return comDmstcDlvCstPlcService.getDlvComUrl(comDmstcDlvCstPlc);
	}


	/*
	 * ---------------------------------------------------------------------
	 * private method.
	 * ---------------------------------------------------------------------
	 */

}
