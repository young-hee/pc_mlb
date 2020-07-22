package com.plgrim.ncp.framework.partitiondata;

import lombok.Data;

/**
 * 파티션화 된 임시 작업 데이터를 표현하는 클래스
 * 
 * @author narusas
 *
 */
@Data
public class PartitionData {

	public final static int INITIAL_STATUS = 0;
	public final static int COMLETE_STATUS = 99;

	String partitionNo;
	int line;
	String type;
	int status;

	PartitionData header;

	String field01;
	String field02;
	String field03;
	String field04;
	String field05;
	String field06;
	String field07;
	String field08;
	String field09;
	String field10;
	String field11;
	String field12;
	String field13;
	String field14;
	String field15;
	String field16;
	String field17;
	String field18;
	String field19;
	String field20;
	String field21;
	String field22;
	String field23;
	String field24;
	String field25;
	String field26;
	String field27;
	String field28;
	String field29;
	String field30;
	String field31;
	String field32;
	String field33;
	String field34;
	String field35;
	String field36;
	String field37;
	String field38;
	String field39;
	String field40;
	String field41;
	String field42;
	String field43;
	String field44;
	String field45;
	String field46;
	String field47;
	String field48;
	String field49;
	String field50;

	/**
	 * 상태를 업그레이드 시킨다
	 */
	public void upgradeStatus() {
		status++;
	}

	/**
	 * 주어진 상태와 일치하는지 여부를 반환
	 * 
	 * @param targetStatus
	 * @return
	 */
	public boolean matchStatus(int targetStatus) {
		return status == targetStatus;
	}

	/**
	 * 상태를 완료 상태로 변경한다
	 */
	public void complete() {
		status = COMLETE_STATUS;
	}

	

	public static PartitionData header(String partitionNo, String... fields) {
		PartitionData header = new PartitionData();
		header.setPartitionNo(partitionNo);
		header.setLine(0);
		header.setStatus(INITIAL_STATUS);
		header.setType("H");
		header.fillFields(fields);
		return header;
	}

	/**
	 * 헤더가 없는 최초 데이터 생성 팩토리 메소드
	 * 
	 * @param partitionNo
	 * @param fields
	 * @return
	 */
	public static PartitionData first(String partitionNo, String... fields) {
		PartitionData header = new PartitionData();
		header.setPartitionNo(partitionNo);
		header.setLine(0);
		header.setStatus(INITIAL_STATUS);
		header.setType("D");
		header.fillFields(fields);
		return header;
	}

	/**
	 * 헤더를 가지는 데이터 생성 팩토리 메소드
	 * 
	 * @param fields
	 * @return
	 */
	public PartitionData nextData(String... fields) {
		PartitionData next = new PartitionData();
		next.setPartitionNo(partitionNo);
		next.setLine(getLine() + 1);
		next.setStatus(INITIAL_STATUS);
		next.setType("D");
		if ("H".equals(getType())) {
			next.setHeader(this);
		} else if (getHeader() != null) {
			next.setHeader(getHeader());
		}
		next.fillFields(fields);
		return next;
	}

	private void fillFields(String[] fields) {
		String[] all = new String[50];
		
		System.arraycopy(fields, 0, all, 0, fields.length);

		field01 = all[1 - 1];
		field02 = all[2 - 1];
		field03 = all[3 - 1];
		field04 = all[4 - 1];
		field05 = all[5 - 1];
		field06 = all[6 - 1];
		field07 = all[7 - 1];
		field08 = all[8 - 1];
		field09 = all[9 - 1];
		field10 = all[10 - 1];
		field11 = all[11 - 1];
		field12 = all[12 - 1];
		field13 = all[13 - 1];
		field14 = all[14 - 1];
		field15 = all[15 - 1];
		field16 = all[16 - 1];
		field17 = all[17 - 1];
		field18 = all[18 - 1];
		field19 = all[19 - 1];
		field20 = all[20 - 1];
		field21 = all[21 - 1];
		field22 = all[22 - 1];
		field23 = all[23 - 1];
		field24 = all[24 - 1];
		field25 = all[25 - 1];
		field26 = all[26 - 1];
		field27 = all[27 - 1];
		field28 = all[28 - 1];
		field29 = all[29 - 1];
		field30 = all[30 - 1];
		field31 = all[31 - 1];
		field32 = all[32 - 1];
		field33 = all[33 - 1];
		field34 = all[34 - 1];
		field35 = all[35 - 1];
		field36 = all[36 - 1];
		field37 = all[37 - 1];
		field38 = all[38 - 1];
		field39 = all[39 - 1];
		field40 = all[40 - 1];
		field41 = all[41 - 1];
		field42 = all[42 - 1];
		field43 = all[43 - 1];
		field44 = all[44 - 1];
		field45 = all[45 - 1];
		field46 = all[46 - 1];
		field47 = all[47 - 1];
		field48 = all[48 - 1];
		field49 = all[49 - 1];
		field50 = all[50 - 1];

	}
}
