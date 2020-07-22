package com.plgrim.ncp.biz.display.data;

import com.plgrim.ncp.base.abstracts.AbstractDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class GoodsFinderFoDTO extends AbstractDTO {
/*
	 * ---------------------------------------------------------------------
	 * Instance fields.
	 * ---------------------------------------------------------------------
	 */
	/** 습득자 번호. 번호를 사용하나 자주 생성 되는 번호가 아닌 관계로 따로 포멧이나 Sequence 사용 하지 않음. MAX값+1로 처리 */
	String finderNo;
	/** 상품 번호. 상품 유형 1자리 || 업체 코드 3자리 || YYMMDD || 5자리 Cycle Sequence */
	String godNo;
	/** 질문 순번 */
	String qestnTurn;
	/** 예문 순번 */
	String smpleSntnTurn;
	/** 예문 순번 리스트 */
	List<String> smpleSntnTurnList;
	/** 질문 및 예문 리스트 */
	List<GoodsFinderFoDTO> goodsFinderFoDTOList;
	/** 이미지 사이즈 코드 */
	String imgSizeCd;
	/** 임직원 정보 */
	String empYn;
}
