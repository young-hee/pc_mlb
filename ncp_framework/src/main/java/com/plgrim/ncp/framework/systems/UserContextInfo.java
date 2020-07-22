package com.plgrim.ncp.framework.systems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserContextInfo {
	Integer birthYear;
	UserGender gender;
	ResidenceArea area;

//	/**
//	 * 구매 횟수
//	 */
//	Integer totalPurchaseCount;
//
//	/**
//	 * 구매 총액
//	 */
//	Integer totalPurchaseAmount;

	public boolean isEmpty() {
		return birthYear == null && gender == null;
	}

}
