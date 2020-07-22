package com.plgrim.ncp.framework.partitiondata;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PartitionDataRowMapper implements RowMapper<PartitionData> {

	@Override
	public PartitionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		PartitionData data = new PartitionData();
		data.setPartitionNo(rs.getString("PARTITION_NO"));
		data.setLine(rs.getInt("LINE"));
		data.setType(rs.getString("TYPE"));
		data.setStatus(rs.getInt("STATUS"));
		data.setField01(rs.getString("FIELD01"));
		data.setField02(rs.getString("FIELD02"));
		data.setField03(rs.getString("FIELD03"));
		data.setField04(rs.getString("FIELD04"));
		data.setField05(rs.getString("FIELD05"));
		data.setField06(rs.getString("FIELD06"));
		data.setField07(rs.getString("FIELD07"));
		data.setField08(rs.getString("FIELD08"));
		data.setField09(rs.getString("FIELD09"));
		data.setField10(rs.getString("FIELD10"));
		data.setField11(rs.getString("FIELD11"));
		data.setField12(rs.getString("FIELD12"));
		data.setField13(rs.getString("FIELD13"));
		data.setField14(rs.getString("FIELD14"));
		data.setField15(rs.getString("FIELD15"));
		data.setField16(rs.getString("FIELD16"));
		data.setField17(rs.getString("FIELD17"));
		data.setField18(rs.getString("FIELD18"));
		data.setField19(rs.getString("FIELD19"));
		data.setField20(rs.getString("FIELD20"));
		data.setField21(rs.getString("FIELD21"));
		data.setField22(rs.getString("FIELD22"));
		data.setField23(rs.getString("FIELD23"));
		data.setField24(rs.getString("FIELD24"));
		data.setField25(rs.getString("FIELD25"));
		data.setField26(rs.getString("FIELD26"));
		data.setField27(rs.getString("FIELD27"));
		data.setField28(rs.getString("FIELD28"));
		data.setField29(rs.getString("FIELD29"));
		data.setField30(rs.getString("FIELD30"));
		data.setField31(rs.getString("FIELD31"));
		data.setField32(rs.getString("FIELD32"));
		data.setField33(rs.getString("FIELD33"));
		data.setField34(rs.getString("FIELD34"));
		data.setField35(rs.getString("FIELD35"));
		data.setField36(rs.getString("FIELD36"));
		data.setField37(rs.getString("FIELD37"));
		data.setField38(rs.getString("FIELD38"));
		data.setField39(rs.getString("FIELD39"));
		data.setField40(rs.getString("FIELD40"));
		data.setField41(rs.getString("FIELD41"));
		data.setField42(rs.getString("FIELD42"));
		data.setField43(rs.getString("FIELD43"));
		data.setField44(rs.getString("FIELD44"));
		data.setField45(rs.getString("FIELD45"));
		data.setField46(rs.getString("FIELD46"));
		data.setField47(rs.getString("FIELD47"));
		data.setField48(rs.getString("FIELD48"));
		data.setField49(rs.getString("FIELD49"));
		data.setField50(rs.getString("FIELD50"));
		return data;
	}

}
