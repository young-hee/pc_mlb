package com.plgrim.ncp.commons.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.plgrim.ncp.base.abstracts.AbstractRepository;
import com.google.common.collect.ImmutableMap;

@Repository
public class MemberOnlinePurchaseHistoryRepository extends AbstractRepository {
	public Integer getMemberPurchaseAmount(String mbrNo, String queryFrom, String queryTo) {
		Map dto = ImmutableMap.<String, Object>of("mbrNo", mbrNo, "queryFrom", convertToDate(queryFrom), "queryTo",
				convertToDate(queryTo));
		return getSession1().selectOne("com.plgrim.ncp.commons.humuson.mbronlinepurchase.getMemberPurchaseAmount", dto);
	}

	public Integer getMemberPurchaseCount(String mbrNo, String queryFrom, String queryTo) {
		Map dto = ImmutableMap.<String, Object>of("mbrNo", mbrNo, "queryFrom", convertToDate(queryFrom), "queryTo",
				convertToDate(queryTo));
		return getSession1().selectOne("com.plgrim.ncp.commons.humuson.mbronlinepurchase.getMemberPurchaseCount", dto);
	}

	Date convertToDate(String dateStr) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
}
