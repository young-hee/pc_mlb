/**
 * @author : Generator(Generator)
 * @date : 2018-05-23
 * @version : 1.0
 * @desc :  generated by gradle
 */

package com.plgrim.ncp.base.entities.datasource1.dsp;

import com.plgrim.ncp.base.abstracts.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

 
@Data
@EqualsAndHashCode(callSuper=false)
public class RecoPick extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 구매전환율이 높은 상품 (type=purchase_rate)
	//추천된 상품의 id
	private String id;
	
	//추천 노출 수
	private String viewAndReco;
	
	//구매수
	private String order;
	
	// 구매전환율
	private String rate;
	
	// 추천클릭률이 높은 상품 (type=reco_click_rate)
	private String viewCount;	//추천 노출 수	 
	
	private String clickCount;	//추천 클릭 수	 
	
	private String clickRate;	//추천클릭률
	
	private String  reco;	//추천 유입 수	 
	
	private String clicklogLink;
	
	private String clicklogRedirectLink;
	
	private String score;
	
	private String method;
}
