package com.plgrim.ncp.cmp.product.bo.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodColor;
import com.plgrim.ncp.base.entities.datasource1.god.GodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrDscr;
import com.plgrim.ncp.base.entities.datasource1.god.GodLndrImg;
import com.plgrim.ncp.base.entities.datasource1.inf.InfGodItmExtend;
import com.plgrim.ncp.base.entities.datasource1.sys.SysAdminBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrnd;
import com.plgrim.ncp.base.entities.datasource1.sys.SysBrndPrdlst;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsErpResult;
import com.plgrim.ncp.biz.goods.result.GoodsErpSizeChartResult;
import com.plgrim.ncp.biz.goods.service.GoodsCategoryService;
import com.plgrim.ncp.biz.goods.service.GoodsErpService;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.cmp.product.bo.GoodsErpBOComponent;
import com.plgrim.ncp.commons.service.GodPlcService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;

@Transactional(value = "transactionManager")
@Component
public class GoodsErpBOComponentImpl extends AbstractComponent implements GoodsErpBOComponent {
	
	@Autowired
	private GodPlcService goodsPolicyService;
	
	@Autowired
	private GoodsErpService goodsErpService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private GoodsCategoryService goodsCategoryService;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsErpBOComponent#searchErpGoodsGridList(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public Page<GoodsErpResult> searchErpGoodsGridList(GoodsSearchDTO goodsSearchDTO) {
		return goodsErpService.searchErpGoodsGridList(goodsSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsErpBOComponent#getErpGoodsData(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public GoodsErpResult getErpGoodsData(GoodsSearchDTO goodsSearchDTO) {
		
		/**
		 * GOD 조회
		 * 
		 * INF_GOD_ITM 조회
		 * 
		 * SYS_ADMIN_BRND 조회
		 * 
		 * OPTION 조회
		 * 
		 * SYS_BRND_PRDLST 조회
		 * 
		 * SYS_BRND 조회
		 *
		 * GOD_COLOR 조회
		 * 
		 * LAUNDRY 조회
		 * 
		 * MATERIALS 조회(혼용율)
		 * 
		 * SIZE GUIDE 조회(조견표)
		 * 
		 */
		
		GoodsErpResult result = new GoodsErpResult();				
		try {
			String erpGodNo = goodsSearchDTO.getErpGodNo();
			
			//	GOD 조회
			goodsSearchDTO.setErpGodNo(erpGodNo);
			God god = goodsInfoService.getGoods(goodsSearchDTO);
			
			if(!Utils.empty(god)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				result.setRstMsg("등록 된 상품입니다.");
				return result;
			}
			
			//	INF_GOD_ITM 조회
			InfGodItmExtend erpGod = goodsErpService.getErpGoods(erpGodNo);
			
			if(Utils.empty(erpGod)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				result.setRstMsg("ERP 상품 정보가 없습니다.");
				return result;
			}
			result.setInfGodItmEx(erpGod);
			
			//	SYS_ADMIN_BRND 조회
			String brndId = erpGod.getBrndId();
			if(!"SYS_OPRTR".equals(goodsSearchDTO.getAdminTpCd())) {				
				String adminId = goodsSearchDTO.getLoginId();
				SysAdminBrnd adminBrnd = goodsErpService.getSysAdminBrnd(adminId, brndId);
				
				if(Utils.empty(adminBrnd)) {
					result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
					result.setRstMsg("권한이 없는 브랜드 상품입니다.");
					return result;					
				}
			}
			
			// OPT 조회
			List<GodItmExtend> itmList = goodsErpService.getErpGoodsItem(goodsSearchDTO);
			result.setItmList(itmList);			
			
			if(Utils.empty(itmList)) {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
				result.setRstMsg("옵션 정보가 없습니다.");
				return result;
			}
			
			//	SYS_BRND_PRDLST 조회			
			String prdlstCd = erpGod.getPrdlstCd();
						
			SysBrndPrdlst brndPrdlst = goodsCategoryService.getSystemBrandProductlist(brndId, prdlstCd);
			result.setBrndPrdlst(brndPrdlst);
			
			//	SYS_BRND 조회			
			SysBrnd sysBrnd = goodsErpService.getSystemBrand(brndId);
			result.setSysBrnd(sysBrnd);
			
			//	GOD_COLOR 조회
			// MLB 일 경우 컬러코드를 => 팀 / 컬러로 쪼개 _ ds
			if("ML".equals(sysBrnd.getUpperBrndId())) {
				String mlbColorCd = erpGod.getColorCd();
				if(mlbColorCd.length() >= 3) {
					erpGod.setTeamCd(mlbColorCd.substring(0,2));
					erpGod.setColorCd(mlbColorCd.substring(mlbColorCd.length()-1, mlbColorCd.length()));
				}
			}
			String colorCd = erpGod.getColorCd();
			String brndGrpId = sysBrnd.getUpperBrndId();
			GodColor godColor = goodsErpService.getGoodsColor(colorCd, brndGrpId);
			result.setGodColor(godColor);
			
			//	LAUNDRY 조회
			String lndrCd = erpGod.getLndrCd();
			GodLndr godLndr = goodsErpService.getGoodsLaundry(lndrCd);
			result.setGodLndr(godLndr);

			//	LAUNDRY DSCR 조회
			List<GodLndrDscr> godLndrDscrList = goodsErpService.getGoodsLaundryDescriptionList(lndrCd);
			result.setGodLndrDscrList(godLndrDscrList);
			
			//	LAUNDRY IMAGE 조회
			goodsSearchDTO.setLndrCd(lndrCd);
			goodsSearchDTO.setBrndGrpId(brndGrpId);
			List<GodLndrImg> godLndrImgList = goodsErpService.getGoodsLaundryImageList(goodsSearchDTO);
			result.setGodLndrImgList(godLndrImgList);
			
			//	SIZE GUIDE 조회(조견표) 조회
			String sizeGdImgUrl = "";
			if(!Utils.empty(brndPrdlst) && StringService.isNotEmpty(brndPrdlst.getSizeGdImgFileUrl())) {
				sizeGdImgUrl = goodsPolicyService.getBrandCloudFrontUrl(brndId)+brndPrdlst.getSizeGdImgFileUrl();	 
			}
			GoodsErpSizeChartResult sizeChart = goodsErpService.getErpGoodsSizeChart(erpGod.getDsgnGrpNo(), prdlstCd, brndId, sizeGdImgUrl, itmList, brndGrpId, erpGod);
			//	PC
			result.setSizeLktb(sizeChart.getSizeLktb());
			result.setSizeLktbEng(sizeChart.getSizeLktbEng());
			result.setSizeLktbChi(sizeChart.getSizeLktbChi());
			//	MOBILE
			result.setMobileSizeLktb(sizeChart.getMobileSizeLktb());
			result.setMobileSizeLktbEng(sizeChart.getMobileSizeLktbEng());
			result.setMobileSizeLktbChi(sizeChart.getMobileSizeLktbChi());
			
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsErpBOComponent#getErpGoodsUploadExcelTemplate(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public List<Map<String, String>> getErpGoodsUploadExcelTemplate(GoodsSearchDTO goodsSearchDTO) {
		return goodsErpService.getErpGoodsUploadExcelTemplate(goodsSearchDTO);
	}

}
