package com.plgrim.ncp.cmp.product.bo.content;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plgrim.ncp.base.abstracts.AbstractComponent;
import com.plgrim.ncp.base.entities.datasource1.cso.CsoCnsltMemo;
import com.plgrim.ncp.base.entities.datasource1.god.God;
import com.plgrim.ncp.base.entities.datasource1.god.GodGodEvlExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodModel;
import com.plgrim.ncp.base.entities.datasource1.god.GodModelImgExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNoti;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiBrndCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiDspCtgryCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodNotiGodCnnc;
import com.plgrim.ncp.base.entities.datasource1.god.GodRelateExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend;
import com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend;
import com.plgrim.ncp.base.enums.GoodsEnum;
import com.plgrim.ncp.biz.callcenter.service.MemoService;
import com.plgrim.ncp.biz.goods.data.GoodsContentDTO;
import com.plgrim.ncp.biz.goods.data.GoodsDsgnGrpExcelDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelImageDTO;
import com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeGridDTO;
import com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSearchDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSeriesLineDTO;
import com.plgrim.ncp.biz.goods.data.GoodsSeriesLineSerachDTO;
import com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO;
import com.plgrim.ncp.biz.goods.result.GoodsModelResult;
import com.plgrim.ncp.biz.goods.result.GoodsNoticeResult;
import com.plgrim.ncp.biz.goods.result.GoodsResult;
import com.plgrim.ncp.biz.goods.result.GoodsReviewResult;
import com.plgrim.ncp.biz.goods.result.GoodsSeriesLineResult;
import com.plgrim.ncp.biz.goods.result.GoodsTagResult;
import com.plgrim.ncp.biz.goods.service.GoodsContentService;
import com.plgrim.ncp.biz.goods.service.GoodsInfoService;
import com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent;
import com.plgrim.ncp.commons.auth.BOSecurityUserDetail;
import com.plgrim.ncp.commons.service.EditorService;
import com.plgrim.ncp.commons.service.GodPlcService;
import com.plgrim.ncp.commons.util.BOSecurityUtil;
import com.plgrim.ncp.framework.cloud.CloudFileMetadata;
import com.plgrim.ncp.framework.cloud.aws.S3FileSystem;
import com.plgrim.ncp.framework.commons.IOService;
import com.plgrim.ncp.framework.commons.StringService;
import com.plgrim.ncp.framework.utils.Utils;

@Transactional(value = "transactionManager")
@Component
public class GoodsContentBOComponentImpl extends AbstractComponent implements GoodsContentBOComponent {

	@Autowired
	private GodPlcService goodsPolicyService;

	@Autowired
	private GoodsInfoService goodsInfoService;

	@Autowired
	private GoodsContentService goodsContentService;

	@Autowired
	private MemoService memoService;

	@Autowired
	private EditorService editorService;

	@Autowired
	private S3FileSystem s3FileSystem;

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchTagReserveList(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public Page<GoodsTagResult> searchTagReserveList(GoodsTagSearchDTO goodsTagSearchDTO) {
		return goodsContentService.searchTagReserveList(goodsTagSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertTagReserve(com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend)
	 */
	@Override
	public GoodsTagResult insertTagReserve(GodTagResveExtend godTagResveExtend) {
		return goodsContentService.insertTagReserve(godTagResveExtend);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#updateTagReserve(com.plgrim.ncp.base.entities.datasource1.god.GodTagResveExtend)
	 */
	@Override
	public GoodsTagResult updateTagReserve(GodTagResveExtend godTagResveExtend) {
		return goodsContentService.updateTagReserve(godTagResveExtend);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getTagReserve(com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO)
	 */
	@Override
	public GoodsTagResult getTagReserve(GoodsTagSearchDTO goodsTagSearchDTO) {
		return goodsContentService.getTagReserve(goodsTagSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getTagReserveGoodsList(com.plgrim.ncp.biz.goods.data.GoodsTagSearchDTO)
	 */
	@Override
	public Page<GoodsTagResult> getTagReserveGoodsList(GoodsTagSearchDTO goodsTagSearchDTO) {
		return goodsContentService.getTagReserveGoodsList(goodsTagSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#updateTagReserveGoods(com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend)
	 */
	@Override
	public GoodsTagResult updateTagReserveGoods(GodTagResveCnncExtend godTagResveCnncExtend) {
		return goodsContentService.updateTagReserveGoods(godTagResveCnncExtend);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#deleteTagReserveGoods(com.plgrim.ncp.base.entities.datasource1.god.GodTagResveCnncExtend)
	 */
	@Override
	public GoodsTagResult deleteTagReserveGoods(GodTagResveCnncExtend godTagResveCnncExtend) {
		return goodsContentService.deleteTagReserveGoods(godTagResveCnncExtend);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchNoticeList(com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO)
	 */
	@Override
	public Page<GoodsNoticeResult> searchNoticeList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return goodsContentService.searchNoticeList(goodsNoticeSearchDTO);
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#updateNoticeList(com.plgrim.ncp.biz.goods.data.GoodsNoticeGridDTO)
	 */
	@Override
	public GoodsNoticeResult updateNoticeList(GoodsNoticeGridDTO goodsNoticeGridDTO) {
		GoodsNoticeResult result = new GoodsNoticeResult();

		if(!goodsNoticeGridDTO.getGoodsNoticeGridList().isEmpty()) {
			int totCnt = goodsNoticeGridDTO.getGoodsNoticeGridList().size();
			int udtCnt = 0;
			for(GoodsNoticeDTO content : goodsNoticeGridDTO.getGoodsNoticeGridList()) {
				GodNoti godNoti = new GodNoti();
				BeanUtils.copyProperties(content.getGodNotiEx(), godNoti);
				godNoti.setUdterId(BOSecurityUtil.getLoginId());
				goodsContentService.updateNoticeList(godNoti);
				udtCnt++;
			}
			if(totCnt == udtCnt) {
				result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
			}else {
				result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertNotice(com.plgrim.ncp.biz.goods.data.GoodsNoticeDTO)
	 */
	@Override
	public GoodsNoticeResult insertNotice(GoodsNoticeDTO goodsNoticeDTO) throws Exception {
		GodNoti godNoti = new GodNoti();
		BeanUtils.copyProperties(goodsNoticeDTO.getGodNotiEx(), godNoti);

		String loginId = BOSecurityUtil.getLoginId();
		godNoti.setRegtrId(loginId);
		godNoti.setUdterId(loginId);

		String commitResourcePath = goodsPolicyService.getNoticeImageUploadPath(godNoti.getMallId());
		String notiCont = godNoti.getNotiCont();
		String mobileNotiCont = godNoti.getMobileNotiCont();

		//	이미지 무브
		if(StringService.contains(notiCont, "uncommited")) {
			notiCont = editorService.commitContentsImagesFromTemp(notiCont, commitResourcePath);
			godNoti.setNotiCont(notiCont);
		}
		if(StringService.contains(mobileNotiCont, "uncommited")) {
			mobileNotiCont = editorService.commitContentsImagesFromTemp(mobileNotiCont, commitResourcePath);
			godNoti.setMobileNotiCont(mobileNotiCont);
		}

		GoodsNoticeResult result = goodsContentService.insertNotice(godNoti);

		if(String.valueOf(GoodsEnum.SUCCESS_CODE).equals(result.getRstCd())) {
			this.updateNoticeConnected(godNoti, goodsNoticeDTO.getGodNotiExList(), loginId, String.valueOf(GoodsEnum.NO));			
		}

		return result;
	}


	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getNotice(com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO)
	 */
	@Override
	public GoodsNoticeResult getNotice(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return goodsContentService.getNotice(goodsNoticeSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getNoticeTargetList(com.plgrim.ncp.biz.goods.data.GoodsNoticeSearchDTO)
	 */
	@Override
	public Page<GoodsNoticeResult> getNoticeTargetList(GoodsNoticeSearchDTO goodsNoticeSearchDTO) {
		return goodsContentService.getNoticeTargetList(goodsNoticeSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#deleteNoticeTarget(com.plgrim.ncp.biz.goods.data.GoodsNoticeDTO)
	 */
	@Override
	public GoodsNoticeResult deleteNoticeTarget(GoodsNoticeDTO goodsNoticeDTO) {
		GoodsNoticeResult result = new GoodsNoticeResult();
		String loginId = BOSecurityUtil.getLoginId();
		try {			
			this.updateNoticeConnected(goodsNoticeDTO.getGodNotiEx(), goodsNoticeDTO.getGodNotiExList(), loginId, String.valueOf(GoodsEnum.YES));
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#updateNotice(com.plgrim.ncp.biz.goods.data.GoodsNoticeDTO)
	 */
	@Override
	public GoodsNoticeResult updateNotice(GoodsNoticeDTO goodsNoticeDTO) throws Exception{
		GodNoti godNoti = new GodNoti();
		BeanUtils.copyProperties(goodsNoticeDTO.getGodNotiEx(), godNoti);

		String loginId = BOSecurityUtil.getLoginId();
		godNoti.setUdterId(loginId);

		String commitResourcePath = goodsPolicyService.getNoticeImageUploadPath(godNoti.getMallId());
		String notiCont = godNoti.getNotiCont();
		String mobileNotiCont = godNoti.getMobileNotiCont();

		//	이미지 무브
		if(StringService.contains(notiCont, "uncommited")) {
			notiCont = editorService.commitContentsImagesFromTemp(notiCont, commitResourcePath);
			godNoti.setNotiCont(notiCont);
		}

		if(StringService.contains(mobileNotiCont, "uncommited")) {
			mobileNotiCont = editorService.commitContentsImagesFromTemp(mobileNotiCont, commitResourcePath);
			godNoti.setMobileNotiCont(mobileNotiCont);
		}

		GoodsNoticeResult result = goodsContentService.updateNotice(godNoti);

		if(String.valueOf(GoodsEnum.SUCCESS_CODE).equals(result.getRstCd())) {			
			this.updateNoticeConnected(godNoti, goodsNoticeDTO.getGodNotiExList(), loginId, String.valueOf(GoodsEnum.NO));			
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchSeriesLineList(com.plgrim.ncp.biz.goods.data.GoodsSeriesLineSerachDTO)
	 */
	@Override
	public Page<GoodsSeriesLineResult> searchSeriesLineList(GoodsSeriesLineSerachDTO seriesSerachDTO) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#updateSeriesLine(java.util.List)
	 */
	@Override
	public void updateSeriesLine(List<GoodsSeriesLineDTO> seriesDTO) {

	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchModelList(com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO)
	 */
	@Override
	public Page<GoodsModelResult> searchModelList(GoodsModelSearchDTO goodsModelSearchDTO) {
		return goodsContentService.searchModelList(goodsModelSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getModel(com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO)
	 */
	@Override
	public GoodsModelResult getModel(GoodsModelSearchDTO goodsModelSearchDTO) {
		return goodsContentService.getModel(goodsModelSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertModel(com.plgrim.ncp.biz.goods.data.GoodsModelImageDTO)
	 */
	@Override
	public GoodsModelResult insertModel(GoodsModelImageDTO goodsModelImageDTO) {
	       /**
	        *   상품 모델 등록
	        *   1. Model 등록 체크
	        *       -> 등록된 모델이면 수정
	        *
	        *   2. Model No 생성
	        *   3. GOD_MODEL 등록
	        *   4. GOD_MODEL_SIZE 등록
	        *   5. GOD_MODEL_IMG 등록
	        *   6. GOD_MODEL 상세 조회
	        */

        GodModel godModel = new GodModel();
        GoodsModelResult modelResult = new GoodsModelResult();

        String modelNo = goodsModelImageDTO.getGodModel().getModelNo();
        godModel = goodsContentService.getModel(modelNo);

        // 1. Model 등록 체크
        if(godModel != null){

        	// 모델 정보 업데이트
        	goodsContentService.updateModel(goodsModelImageDTO);

        	// 모델 사이즈 정보 업데이트
        	goodsContentService.updateModelSizeList(goodsModelImageDTO);

        }else {
            // 2. Model No 생성
            modelNo = goodsContentService.createModelNo();
            goodsModelImageDTO.getGodModel().setModelNo(modelNo);

            // 3. GOD_MODEL 등록
            goodsContentService.insertModel(goodsModelImageDTO);

            // 4. GOD_MODEL_SIZE 등록
            goodsContentService.insertModelSizeList(goodsModelImageDTO);

        }
        // 5. GOD_MODEL_IMG 등록
        goodsContentService.insertModelImgList(goodsModelImageDTO);

        // 6. GOD_MODEL 상세 조회
        GoodsModelSearchDTO modelSearchDTO = new GoodsModelSearchDTO();
        modelSearchDTO.setModelNo(modelNo);
        modelResult = goodsContentService.getModel(modelSearchDTO);

		return modelResult;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchModel(com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO)
	 */
	@Override
	public Page<GodModelImgExtend> searchModel(GoodsModelSearchDTO goodsModelSearchDTO) {
		return goodsContentService.searchModel(goodsModelSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getBrandModel(com.plgrim.ncp.biz.goods.data.GoodsModelSearchDTO)
	 */
	@Override
	public GodModelImgExtend getBrandModel(GoodsModelSearchDTO goodsModelSearchDTO) {
		return goodsContentService.getBrandModel(goodsModelSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertGoodsMemo(com.plgrim.ncp.biz.goods.data.GoodsContentDTO, java.lang.String)
	 */
	@Override
	public GoodsResult insertGoodsMemo(GoodsContentDTO goodsContentDTO, String loginId) {
		GoodsResult result = new GoodsResult();
		CsoCnsltMemo csoCnsltMemo = goodsContentDTO.getCsoCnsltMemo();
		Long memoSn = csoCnsltMemo.getMemoSn();

		try {
			if(Utils.empty(memoSn)) {
				csoCnsltMemo.setRegtrId(loginId);
				memoService.insertCsoCnsltMemo(csoCnsltMemo);
			}else {
				csoCnsltMemo.setUdterId(loginId);
				memoService.updateCsoCnsltMemo(csoCnsltMemo);
			}
			result.setRstCd(String.valueOf(GoodsEnum.SUCCESS_CODE));
		} catch (Exception e) {
			result.setRstCd(String.valueOf(GoodsEnum.FAIL_CODE));
			throw new RuntimeException();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertGoodsImageZipUpload(java.lang.String)
	 */
	@Override
	public GoodsResult insertGoodsImageZipUpload(String targetPath) {
		GoodsResult result = new GoodsResult();
		int reqCnt = 0;
		int rstCnt = 0;
		StringBuffer msg = new StringBuffer();

		Collection<CloudFileMetadata> objectList = s3FileSystem.getListObjects(targetPath);
		String godNo = "";
		
		for(CloudFileMetadata object : objectList) {
			//	File
			if(object.getLength() > 0) {
				reqCnt++;
				godNo = this.insertGoodsImage(object, msg);
				if(godNo != null && !"".equals(godNo)) {
					rstCnt++;
				}else {
					msg.append(System.getProperty("line.separator"));
				}
			}
		}
		
		if(godNo != null && !"".equals(godNo)) {
			// 이미지 순서 재 조정
			goodsContentService.updateGoodsImageTurn(godNo, BOSecurityUtil.getCurrentUserDetail().getSysAdmin().getAdminId());
		}
		
		result.setReqCnt(reqCnt);
		result.setRstCnt(rstCnt);
		result.setRstMsg(msg.toString());
		return result;
	}

	private String insertGoodsImage(CloudFileMetadata object, StringBuffer msg) {
		boolean result = true;
		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();

		String resource = "/"+object.getPath();
		String[] key = StringService.split(resource, "/");
		String fileName = key[key.length - 1];
		String extension = IOService.getExtension(fileName);

		String[] excludeExtensions = { "jsp", "php", "exe", "bat", "asp"};

		extLoop : for (String excludeExtension : excludeExtensions) {
			if (excludeExtension.equalsIgnoreCase(extension)) {
				result = false;
				break extLoop;
			}
		}

		if(!result) {
			msg.append(fileName + " : 파일 확장자가 잘못되었습니다.");
			return "";
		}

		Pattern filePattern = Pattern.compile("^([A-Z0-9\\-{\\s+}])+-([a-z0-9]{1,3})$");
		Matcher matcher = filePattern.matcher(IOService.removeExtension(fileName));

		if(!matcher.matches()) {
			msg.append(fileName +" : 파일명 규칙이 잘못되었습니다.");
			return "";
		}

		String[] imageInfo = StringService.split(IOService.removeExtension(fileName), "-");
		if(imageInfo.length != 3) {
			msg.append(fileName +" : 파일명 규칙이 잘못되었습니다.");
			return "";
		}

		String erpGodNo = imageInfo[0]+"-"+imageInfo[1];
		String imageType = StringService.left(imageInfo[2], 1);
		String tempTurn = StringService.mid(imageInfo[2], 1, imageInfo[2].length());
		if(StringService.isEmpty(tempTurn)) {
			tempTurn = String.valueOf(GoodsEnum.Number.ONE);
		}
		int imageTurn = Integer.parseInt(tempTurn);

		if(!StringService.containsIgnoreCase("l", imageType) && !StringService.containsIgnoreCase("m", imageType) && !StringService.containsIgnoreCase("d", imageType)) {
			msg.append(fileName + " : 파일명 규칙이 잘못되었습니다.");
			return "";
		}

		if((StringService.containsIgnoreCase("l", imageType) || StringService.containsIgnoreCase("m", imageType)) && !String.valueOf(GoodsEnum.Number.ONE).equals(tempTurn)) {
			msg.append(fileName + " : 대표 이미지는 1개만 등록 됩니다.");
			return "";
		}

		GoodsSearchDTO searchDTO = new GoodsSearchDTO();
		searchDTO.setErpGodNo(erpGodNo);

		God god = goodsInfoService.getGoods(searchDTO);

		if(Utils.empty(god)) {
			msg.append(fileName + " : 존재하지 않는 상품입니다.");
			return "";
		}

		if(result) {
			GodImgExtend imgEx = new GodImgExtend();

			imgEx.setImgTpCd(imageType);
			imgEx.setImgTurn(imageTurn);
			imgEx.setTempFileName(fileName);
			imgEx.setImageUploadType(String.valueOf(GoodsEnum.GoodsUploadImageType.MASS));
			imgEx.setTempResourcePath(resource);
			imgEx.setFileExt(extension);
			imgEx.setRegtrId(user.getSysAdmin().getAdminId());
			imgEx.setUdterId(user.getSysAdmin().getAdminId());
			goodsPolicyService.setGoodsImageDefaultPolicy(god, imgEx);

			goodsContentService.insertGoodsImage(imgEx);
		}

		String godNo = "";
		if(result) {
			godNo = god.getGodNo();
		}
		
		return godNo;
	}

	/**
	 * 상품 연관 상품 조회
	 *
	 * @param godNo
	 * @return
	 */
	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#getGoodsRelateList(java.lang.String)
	 */
	public List<GodRelateExtend> getGoodsRelateList(String godNo) {
		return goodsContentService.getGoodsRelateList(godNo);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#searchDsgnGrpsGridList(com.plgrim.ncp.biz.goods.data.GoodsSearchDTO)
	 */
	@Override
	public Page<GoodsReviewResult> searchDsgnGrpsGridList(GoodsSearchDTO goodsSearchDTO) {
		return goodsContentService.searchDsgnGrpsGridList(goodsSearchDTO);
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#deleteDsgnGrpList(java.util.List)
	 */
	@Override
	public void deleteDsgnGrpList(List<GoodsSearchDTO> gridList) {
		try {
			goodsContentService.deleteDsgnGrpList(gridList);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.plgrim.ncp.cmp.product.bo.GoodsContentBOComponent#insertDsgnGrpCnnGoods(java.util.List)
	 */

	@Override
	public List<GoodsDsgnGrpExcelDTO> insertDsgnGrpCnnGoods(List<GoodsDsgnGrpExcelDTO> list) {
		List<GoodsDsgnGrpExcelDTO> resultList = null;

		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();
		String adminId = user.getSysAdmin().getAdminId();		
		Map<String, List<GoodsDsgnGrpExcelDTO>> map = new HashMap<String, List<GoodsDsgnGrpExcelDTO>>();
		Map<String, String> brandCode = new HashMap<String, String>();
		brandCode.put("I", "I");
		brandCode.put("M", "M");
		brandCode.put("A", "A");
		brandCode.put("X", "X");
		
		if(!Utils.empty(list)) {
			
			for(GoodsDsgnGrpExcelDTO excelData : list) {				
			 String grpCnncNo = excelData.getGrpCnncNo();				
				if(StringService.isEmpty(grpCnncNo)){
					grpCnncNo ="Null";
				}				
				if(map.containsKey(grpCnncNo)){
					List<GoodsDsgnGrpExcelDTO> add =map.get(grpCnncNo);
					add.add(excelData);
					map.put(grpCnncNo, add);
				}else{
					List<GoodsDsgnGrpExcelDTO> add =new ArrayList<>();
					add.add(excelData);
					map.put(grpCnncNo, add);					
				}
			}

			resultList = new ArrayList<GoodsDsgnGrpExcelDTO>();
			
			Iterator<String> it =  map.keySet().iterator();
			GodGodEvlExtend godGodEvlExtend;
			int count = 0;
			while (it.hasNext()) {
				String key = (String) it.next();				
				String  dsgnGrpNo ="";
				excelLoop : for(GoodsDsgnGrpExcelDTO excelData : map.get(key)) {
					godGodEvlExtend = new GodGodEvlExtend();
					String brndId 				= excelData.getBrndId();
			
					if("Null".equals(key)){
						excelData.setValidText("디자인 코드는 필수입력항목입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					
					if(count == 0){
						godGodEvlExtend.setBrndId(brndId);
						GodGodEvlExtend v =	goodsContentService.getDsgnGrpCnnKey(godGodEvlExtend);
						dsgnGrpNo = v.getDsgnGrpNo();
					}
					
				 
					String dsgnGrpCnncNo 			= excelData.getDsgnGrpCnncNo();
	 
					if(Utils.empty(dsgnGrpCnncNo)) {
						excelData.setValidText("연결대상 디자인 코드는 필수입력항목입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					
	 
					if(dsgnGrpCnncNo.length() >9){
						excelData.setValidText("연결대상 디자인 코드는 9자리 까지만 입력 가능합니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					 
					if(Utils.empty(brndId)) {
						excelData.setValidText("브랜드 코드는 필수입력항목입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					
					brndId = brndId.toUpperCase();
					
					if(!brandCode.containsKey(brndId)){
						excelData.setValidText("존재 하지 않는 브랜드 코드입니다.");
						resultList.add(excelData);
						continue excelLoop;
					}
					
					
					godGodEvlExtend.setDsgnGrpCnncNo(dsgnGrpCnncNo);
					godGodEvlExtend =	goodsContentService.getDsgnGrpCnnGoods(godGodEvlExtend);
					
					if(godGodEvlExtend != null){
						excelData.setValidText("이미 등록되어 있습니다.");
						resultList.add(excelData);
						continue excelLoop;
					}

					godGodEvlExtend = new GodGodEvlExtend();
					godGodEvlExtend.setDsgnGrpNo(dsgnGrpNo);
					godGodEvlExtend.setDsgnGrpCnncNo(dsgnGrpCnncNo);
					godGodEvlExtend.setBrndId(brndId);
					godGodEvlExtend.setRegtrId(adminId);
					
					goodsContentService.insertDsgnGrpCnnGoods(godGodEvlExtend);
					count++;
				}
				
				count = 0;
			}
 
		}
		return resultList;
	}

	@Override
	public List<GoodsDsgnGrpExcelDTO> addDsgnGrpNos(GoodsSearchDTO goodsSearchDTO) {
		BOSecurityUserDetail user = BOSecurityUtil.getCurrentUserDetail();
		String adminId = user.getSysAdmin().getAdminId();	
		List<GoodsDsgnGrpExcelDTO> resultList = new ArrayList<GoodsDsgnGrpExcelDTO>();
		String dsgnGrpNo = goodsSearchDTO.getDsgnGrpNo();
		String brndId = goodsSearchDTO.getBrndId();
		excelLoop :for (int i = 0; i < goodsSearchDTO.getDsgnGrpNos().length; i++) {
			
			String dsgnGrpCnncNo = goodsSearchDTO.getDsgnGrpNos()[i];
			GoodsDsgnGrpExcelDTO excelData = new GoodsDsgnGrpExcelDTO();
			excelData.setDsgnGrpCnncNo(dsgnGrpCnncNo);
			if(Utils.empty(dsgnGrpCnncNo)) {
				excelData.setValidText("공백은 등록 할수 없습니다.");
				resultList.add(excelData);
				continue excelLoop;
			}
			if(dsgnGrpCnncNo.length() >9){

				excelData.setValidText("연결대상 디자인 코드는 9자리 까지만 입력 가능합니다.");
				resultList.add(excelData);
				continue excelLoop;
			}
			GodGodEvlExtend	godGodEvlExtend = new GodGodEvlExtend();
			godGodEvlExtend.setDsgnGrpCnncNo(dsgnGrpCnncNo);
			godGodEvlExtend =	goodsContentService.getDsgnGrpCnnGoods(godGodEvlExtend);
			
			if(godGodEvlExtend != null){
				excelData.setValidText("이미 등록되어 있습니다.");
				resultList.add(excelData);
				continue excelLoop;
			}
			godGodEvlExtend = new GodGodEvlExtend();
			godGodEvlExtend.setDsgnGrpNo(dsgnGrpNo);
			godGodEvlExtend.setDsgnGrpCnncNo(dsgnGrpCnncNo);
			godGodEvlExtend.setBrndId(brndId);
			godGodEvlExtend.setRegtrId(adminId);
			
			goodsContentService.insertDsgnGrpCnnGoods(godGodEvlExtend);
		}
		return resultList;
	}
	
	
	private void updateNoticeConnected(GodNoti godNoti, List<GodNotiExtend> godNotiExList, String loginId, String deleteYn){		
		if(!godNotiExList.isEmpty()){			
			for(GodNotiExtend godNotiEx : godNotiExList) {
				if(String.valueOf(GoodsEnum.GodNotSectCd.BRND_NOTI).equals(godNoti.getGodNotiSectCd())) {
					GodNotiBrndCnnc godNotiBrndCnnc = new GodNotiBrndCnnc();
					godNotiBrndCnnc.setGodNotiSn(godNoti.getGodNotiSn());
					godNotiBrndCnnc.setBrndId(godNotiEx.getBrndId());
					godNotiBrndCnnc.setDeleteYn(deleteYn);
					godNotiBrndCnnc.setRegtrId(loginId);
					godNotiBrndCnnc.setUdterId(loginId);
					goodsContentService.updateNoticeBrandConnected(godNotiBrndCnnc);
				}		
				if(String.valueOf(GoodsEnum.GodNotSectCd.GOD_NOTI).equals(godNoti.getGodNotiSectCd())) {
					GodNotiGodCnnc godNotiGodCnnc = new GodNotiGodCnnc();
					godNotiGodCnnc.setGodNotiSn(godNoti.getGodNotiSn());
					godNotiGodCnnc.setGodNo(godNotiEx.getGodNo());
					godNotiGodCnnc.setDeleteYn(deleteYn);
					godNotiGodCnnc.setRegtrId(loginId);
					godNotiGodCnnc.setUdterId(loginId);
					goodsContentService.updateNoticeGoodsConnected(godNotiGodCnnc);
				}			
				if(String.valueOf(GoodsEnum.GodNotSectCd.DSP_CTGRY_NOTI).equals(godNoti.getGodNotiSectCd())) {
					GodNotiDspCtgryCnnc godNotiDspCtgryCnnc = new GodNotiDspCtgryCnnc();
					godNotiDspCtgryCnnc.setGodNotiSn(godNoti.getGodNotiSn());
					godNotiDspCtgryCnnc.setDspCtgryNo(godNotiEx.getDspCtgryNo());
					godNotiDspCtgryCnnc.setDeleteYn(deleteYn);
					godNotiDspCtgryCnnc.setRegtrId(loginId);					
					godNotiDspCtgryCnnc.setUdterId(loginId);					
					goodsContentService.updateNoticeDisplayCategoryConnected(godNotiDspCtgryCnnc);
				}		
			}
		}		
	}

	@Override
	public List<GoodsReviewResult> searchDsgnGrpsView(GoodsSearchDTO goodsSearchDTO) {
		return goodsContentService.searchDsgnGrpsView(goodsSearchDTO);
	}

}
